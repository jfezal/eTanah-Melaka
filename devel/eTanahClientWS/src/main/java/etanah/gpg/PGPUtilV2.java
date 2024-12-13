package etanah.gpg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.util.Collection;
import java.util.Iterator;

import org.bouncycastle.bcpg.ArmoredInputStream;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;

/**
 * A simple utility class that encrypts/decrypts public key based
 * encryption files.
 * <p>
 * To encrypt a file: KeyBasedFileProcessor -e [-a|-ai] fileName publicKeyFile.<br>
 * If -a is specified the output file will be "ascii-armored".
 * If -i is specified the output file will be have integrity checking added.
 * <p>
 * To decrypt: KeyBasedFileProcessor -d fileName secretKeyFile passPhrase.
 * <p>
 * Note 1: this example will silently overwrite files, nor does it pay any attention to
 * the specification of "_CONSOLE" in the filename. It also expects that a single pass phrase
 * will have been used.
 * <p>
 * Note 2: if an empty file name has been specified in the literal data object contained in the
 * encrypted packet a file with the name filename.out will be generated in the current working directory.
 */
public class PGPUtilV2
{
	static {
	    Security.addProvider(new BouncyCastleProvider());
	}
	
    public static void decryptFile(
        String inputFileName,
        String keyFileName,
        char[] passwd,
        String defaultFileName)
        throws IOException, NoSuchProviderException
    {
        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
        FileOutputStream fout = new FileOutputStream(defaultFileName);
        decryptFile(in, fout, keyIn, passwd);
        keyIn.close();
        in.close();
    }

    /**
     * decrypt the passed in message stream
     */
    public static void decryptFile(InputStream in, OutputStream fOut, InputStream publicKeyIn, char[] passwd)
        throws IOException, NoSuchProviderException
    {
       // in = PGPUtil.getDecoderStream(in);
        
        try
        {
        	in = PGPUtil.getDecoderStream(in);

            PGPObjectFactory pgpF = new PGPObjectFactory(in, null);
            PGPEncryptedDataList enc;

            Object o = pgpF.nextObject();
            //
            // the first object might be a PGP marker packet.
            //
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }
            
            //
            // find the secret key
            //
            Iterator<PGPEncryptedData>  it = enc.getEncryptedDataObjects();
            PGPPrivateKey               sKey = null;
            PGPPublicKeyEncryptedData   pbe = null;
            PGPSecretKeyRingCollection  pgpSec = new PGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(publicKeyIn), new JcaKeyFingerprintCalculator());

            while (sKey == null && it.hasNext())
            {
                pbe = (PGPPublicKeyEncryptedData)it.next();
                
                sKey = PGPExampleUtil.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }
            
            if (sKey == null)
            {
                throw new IllegalArgumentException("secret key for message not found.");
            }
    
            InputStream         clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
            
            JcaPGPObjectFactory    plainFact = new JcaPGPObjectFactory(clear);
            
            Object              message = plainFact.nextObject();
    
            if (message instanceof PGPCompressedData)
            {
                PGPCompressedData   cData = (PGPCompressedData)message;
                JcaPGPObjectFactory    pgpFact = new JcaPGPObjectFactory(cData.getDataStream());
                
                message = pgpFact.nextObject();
            }
            
            if (message instanceof PGPLiteralData)
            {
                PGPLiteralData ld = (PGPLiteralData)message;

                InputStream unc = ld.getInputStream();
                ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

                Streams.pipeAll(unc, actualOutput);

                actualOutput.close();
                
                byte[] output = actualOutput.toByteArray();
                
                fOut.write(output);
                fOut.flush();
                fOut.close();
            }
            else if (message instanceof PGPOnePassSignatureList)
            {
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            }
            else
            {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }

            if (pbe.isIntegrityProtected())
            {
                if (!pbe.verify())
                {
                    System.err.println("message failed integrity check");
                }
                else
                {
                    System.err.println("message integrity check passed");
                }
            }
            else
            {
                System.err.println("no message integrity check");
            }
        }
        catch (PGPException e)
        {
            System.err.println(e);
            if (e.getUnderlyingException() != null)
            {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }
    
    public static void decryptAndVerify(InputStream in, OutputStream fOut, InputStream publicKeyIn, char[] passwd) throws IOException, SignatureException, PGPException, NoSuchProviderException {
    	InputStream inTemp = in;
    	//in = PGPUtil.getDecoderStream(in);
    	//in = new ArmoredInputStream(in);
    	
        PGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
        PGPEncryptedDataList enc;

        Object o = pgpF.nextObject();
        //
        // the first object might be a PGP marker packet.
        //
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } 
        else if (o instanceof PGPSecretKeyRing) {           
           
           PGPPrivateKey sKey = null;
           PBESecretKeyDecryptor decryptort = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(passwd);
           PGPSecretKey psKey = ((PGPSecretKeyRing) o).getSecretKey();
           
           if (psKey != null) {
               sKey = psKey.extractPrivateKey(decryptort);
           }
           System.out.println(sKey);
           enc = (PGPEncryptedDataList) o;
        }
        else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }
        
//        System.out.println(enc.size());
//        
//        PGPSecretKey pgpSec2 = PGPExampleUtil.readSecretKey(publicKeyIn);
//        PBESecretKeyDecryptor keyDecryptor = passwd == null ? null : new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(passwd);
//        
        //PGPSecretKey psKey = pgpSec2.getSecretKey(pbe.getKeyID());
//        PGPPrivateKey sKey2 = pgpSec2.extractPrivateKey(keyDecryptor);
//        System.out.println("skey2:"+sKey2);
//        
//        Iterator it2 = enc.getEncryptedDataObjects();
//        PGPPublicKeyEncryptedData pbe2 = null;
//
//        boolean found = false;
//        while (!found && it2.hasNext()) {
//            pbe2 = (PGPPublicKeyEncryptedData) it2.next();
//            PGPSecretKey psKey = pgpSec2.getSecretKey(pbe2.getKeyID());
//System.out.println("what is is:"+pbe2.getKeyID());
//            if (pbe2.getKeyID() == pgpSec2.getKeyID()) {
//                found = true;
//            }
//        }
//
//        if (!found) {
//            throw new IllegalArgumentException("secret key for message not found.ssss");
//        }



        //				
        // find the secret key
        //
        Iterator<PGPEncryptedData> it = enc.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;
        while (sKey == null && it.hasNext()) {
        	System.out.println("ada data enc");
            pbe = (PGPPublicKeyEncryptedData) it.next();
            //sKey = PGPUtilV2.findSecretKey(publicKeyIn, pbe.getKeyID(), passwd);
            PGPSecretKeyRingCollection  pgpSec = new PGPSecretKeyRingCollection(
                  PGPUtil.getDecoderStream(publicKeyIn), new JcaKeyFingerprintCalculator());
            sKey = PGPExampleUtil.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
//            PBESecretKeyDecryptor decryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(passwd);
//            PGPSecretKeyRingCollection  pgpSec = new PGPSecretKeyRingCollection(
//                    PGPUtil.getDecoderStream(publicKeyIn), new JcaKeyFingerprintCalculator());
//            PGPSecretKey psKey = pgpSec.getSecretKey(pbe.getKeyID());
//            if (psKey != null) {
//            	System.out.println("masuk sini");
//                sKey = psKey.extractPrivateKey(decryptor);
//                break;
//            }
        }
        
        InputStream clear=null;
        if (sKey == null) {
        	//decryptFile(inTemp, fOut, publicKeyIn, passwd);
        	// clear = pbe.getDataStream(
        	//		  new JcePublicKeyDataDecryptorFactoryBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).build(sKey2));
            throw new IllegalArgumentException("Unable to find secret key to decrypt the message");
        }
        else
        	clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));

        PGPObjectFactory plainFact = new PGPObjectFactory(clear, null);
        
        System.out.println(plainFact);

        Object message;

        PGPOnePassSignatureList onePassSignatureList = null;
        PGPSignatureList signatureList = null;
        PGPCompressedData compressedData;

        message = plainFact.nextObject();
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();

        while (message != null) {
           // __l.trace(message.toString());
            if (message instanceof PGPCompressedData) {
                compressedData = (PGPCompressedData) message;
                plainFact = new PGPObjectFactory(compressedData.getDataStream(), null);
                message = plainFact.nextObject();
            }

            if (message instanceof PGPLiteralData) {
                // have to read it and keep it somewhere.
                Streams.pipeAll(((PGPLiteralData) message).getInputStream(), actualOutput);
            } else if (message instanceof PGPOnePassSignatureList) {
                onePassSignatureList = (PGPOnePassSignatureList) message;
            } else if (message instanceof PGPSignatureList) {
                signatureList = (PGPSignatureList) message;
            } else {
                throw new PGPException("message unknown message type.");
            }
            message = plainFact.nextObject();
        }
        actualOutput.close();
       // PGPPublicKey publicKey = null;
        byte[] output = actualOutput.toByteArray();
        
        fOut.write(output);
        fOut.flush();
        fOut.close();
      
    }
    
    public static String gpgDecrypt(String file, String passphrase,
            String outputfile, String privateKey) {
        String result = null;
        //StringBuilder commandRegKey = new StringBuilder("gpg --import ").append(privateKey);
        StringBuilder command = new StringBuilder(
                "gpg2 ").append(" --output ").append(outputfile)
        .append(" --batch --passphrase ").append(passphrase)
        .append(" --decrypt ").append(file);
        System.out.println("DECRYPT COMMAND: " + command.toString());
        try {
        	//Process gpgRegisterKey = Runtime.getRuntime().exec(commandRegKey.toString());
            Process gpgProcess = Runtime.getRuntime().exec(command.toString());

            BufferedReader gpgOutput = new BufferedReader(
                    new InputStreamReader(gpgProcess.getInputStream()));
            BufferedReader gpgErrorOutput = new BufferedReader(
                    new InputStreamReader(gpgProcess.getErrorStream()));
            BufferedWriter gpgInput = new BufferedWriter(
                    new OutputStreamWriter(gpgProcess.getOutputStream()));

            boolean executing = true;

            while (executing) {
                try {
                    if (gpgErrorOutput.ready()) {
                        result = getStreamText(gpgErrorOutput);
                        System.err.println(result);
                        break;
                    } else if (gpgOutput.ready()) {
                        result = getStreamText(gpgOutput);
                    }
                    String line = null;
                    while ((line = gpgOutput.readLine()) != null) {
                        System.out.println(line);
                    }
                    int exitValue = gpgProcess.exitValue();
                    System.out.println("EXIT: " + exitValue);

                    executing = false;
                } catch (IllegalThreadStateException e) {
                    System.out.println("Not yet ready.  Stream status: "
                            + gpgOutput.ready() + ", error: "
                            + gpgErrorOutput.ready());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        System.err.println("This thread has insomnia: "
                                + e1.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err
                    .println("Unable to execute GPG decrypt command via command line: "
                            + e.getMessage());
        }

        return result;
    }

    private static String getStreamText(BufferedReader reader)
            throws IOException {
        StringBuilder result = new StringBuilder();
        try {
            while (reader.ready()) {
                result.append(reader.readLine());
                if (reader.ready()) {
                    result.append("\n");
                }
            }
        } catch (IOException ioe) {
            System.err.println("Error while reading the stream: "
                    + ioe.getMessage());
            throw ioe;
        }
        return result.toString();
    }
    
    /**
	 * Load a secret key ring collection from keyIn and find the secret key corresponding to
	 * keyID if it exists.
	 *
	 * @param keyIn input stream representing a key ring collection.
	 * @param keyID keyID we want.
	 * @param pass passphrase to decrypt secret key with.
	 * @return
	 * @throws IOException
	 * @throws PGPException
	 * @throws NoSuchProviderException
	 */
	public static PGPPrivateKey findSecretKey(InputStream keyIn, long keyID, char[] pass)
			throws IOException, PGPException, NoSuchProviderException
	{
		
		PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
              PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());

		PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);

//		if (pgpSecKey == null) {
//			return null;
//		}

		PBESecretKeyDecryptor a = new JcePBESecretKeyDecryptorBuilder(new JcaPGPDigestCalculatorProviderBuilder().setProvider("BC").build()).setProvider("BC").build(pass);

		return pgpSecKey.extractPrivateKey(a);
	}

    public static void encryptFile(
        String          outputFileName,
        String          inputFileName,
        String          encKeyFileName,
        boolean         armor,
        boolean         withIntegrityCheck)
        throws IOException, NoSuchProviderException, PGPException
    {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        PGPPublicKey encKey = PGPExampleUtil.readPublicKey(encKeyFileName);
        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
        out.close();
    }

    public static void encryptFile(
        OutputStream    out,
        String          fileName,
        PGPPublicKey    encKey,
        boolean         armor,
        boolean         withIntegrityCheck)
        throws IOException, NoSuchProviderException
    {
        if (armor)
        {
            out = new ArmoredOutputStream(out);
        }

        try
        {
            byte[] bytes = PGPExampleUtil.compressFile(fileName, CompressionAlgorithmTags.ZIP);

            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));

            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));

            OutputStream cOut = encGen.open(out, bytes.length);

            cOut.write(bytes);
            cOut.close();

            if (armor)
            {
                out.close();
            }
        }
        catch (PGPException e)
        {
            System.err.println(e);
            if (e.getUnderlyingException() != null)
            {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }

    public static void main(
        String[] args)
        throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        if (args.length == 0)
        {
            System.err.println("usage: KeyBasedFileProcessor -e|-d [-a|ai] file [secretKeyFile passPhrase|pubKeyFile]");
            return;
        }

        if (args[0].equals("-e"))
        {
            if (args[1].equals("-a") || args[1].equals("-ai") || args[1].equals("-ia"))
            {
                encryptFile(args[2] + ".asc", args[2], args[3], true, (args[1].indexOf('i') > 0));
            }
            else if (args[1].equals("-i"))
            {
                encryptFile(args[2] + ".bpg", args[2], args[3], false, true);
            }
            else
            {
                encryptFile(args[1] + ".bpg", args[1], args[2], false, false);
            }
        }
        else if (args[0].equals("-d"))
        {
            decryptFile(args[1], args[2], args[3].toCharArray(), new File(args[1]).getName() + ".out");
        }
        else
        {
            System.err.println("usage: KeyBasedFileProcessor -d|-e [-a|ai] file [secretKeyFile passPhrase|pubKeyFile]");
        }
    }
}

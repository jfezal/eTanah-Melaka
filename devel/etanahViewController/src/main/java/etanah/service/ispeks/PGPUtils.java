//package com.john.pgpfile;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.security.NoSuchProviderException;
//import java.security.SecureRandom;
//import java.security.Security;
//import java.util.Collection;
//import java.util.Iterator;
//
//import org.bouncycastle.bcpg.ArmoredOutputStream;
//import org.bouncycastle.crypto.InvalidCipherTextException;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.openpgp.PGPCompressedData;
//import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
//import org.bouncycastle.openpgp.PGPEncryptedData;
//import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
//import org.bouncycastle.openpgp.PGPEncryptedDataList;
//import org.bouncycastle.openpgp.PGPException;
//import org.bouncycastle.openpgp.PGPLiteralData;
//import org.bouncycastle.openpgp.PGPObjectFactory;
//import org.bouncycastle.openpgp.PGPOnePassSignatureList;
//import org.bouncycastle.openpgp.PGPPrivateKey;
//import org.bouncycastle.openpgp.PGPPublicKey;
//import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
//import org.bouncycastle.openpgp.PGPPublicKeyRing;
//import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
//import org.bouncycastle.openpgp.PGPSecretKey;
//import org.bouncycastle.openpgp.PGPSecretKeyRing;
//import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
//import org.bouncycastle.openpgp.PGPUtil;
//import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
//import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
// 
///**
// * Taken from org.bouncycastle.openpgp.examples
// *
// * @author seamans
// *
// */
//public class PGPUtils {
// 
//    @SuppressWarnings("unchecked")
//    public static PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException {
//        in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
// 
//        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection((Collection<PGPPublicKeyRing>) in);
// 
//        //
//        // we just loop through the collection till we find a key suitable for encryption, in the real
//        // world you would probably want to be a bit smarter about this.
//        //
//        PGPPublicKey key = null;
// 
//        //
//        // iterate through the key rings.
//        //
//        Iterator<PGPPublicKeyRing> rIt = pgpPub.getKeyRings();
// 
//        while (key == null && rIt.hasNext()) {
//            PGPPublicKeyRing kRing = rIt.next();
//            Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
//            while (key == null && kIt.hasNext()) {
//                PGPPublicKey k = kIt.next();
// 
//                if (k.isEncryptionKey()) {
//                    key = k;
//                }
//            }
//        }
// 
//        if (key == null) {
//            throw new IllegalArgumentException("Can't find encryption key in key ring.");
//        }
// 
//        return key;
//    }
// 
//    /**
//     * Load a secret key ring collection from keyIn and find the secret key corresponding to
//     * keyID if it exists.
//     *
//     * @param keyIn input stream representing a key ring collection.
//     * @param keyID keyID we want.
//     * @param pass passphrase to decrypt secret key with.
//     * @return
//     * @throws IOException
//     * @throws PGPException
//     * @throws NoSuchProviderException
//     */
//    private static PGPPrivateKey findSecretKey(InputStream keyIn, long keyID, char[] pass)
//        throws IOException, PGPException, NoSuchProviderException
//    {
//        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
//            (Collection<PGPSecretKeyRing>) org.bouncycastle.openpgp.PGPUtil.getDecoderStream(keyIn));
// 
//        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);
// 
//        if (pgpSecKey == null) {
//            return null;
//        }
// 
//        return pgpSecKey.extractPrivateKey(pass, "BC");
//    }
// 
//    /**
//     * decrypt the passed in message stream
//     */
//    @SuppressWarnings("unchecked")
//    public static void decryptFile(InputStream in, OutputStream out, InputStream keyIn, char[] passwd)
//        throws Exception
//    {
//        Security.addProvider(new BouncyCastleProvider());
// 
//        in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
// 
//        PGPObjectFactory pgpF = new PGPObjectFactory(in);
//        PGPEncryptedDataList enc;
// 
//        Object o = pgpF.nextObject();
//        //
//        // the first object might be a PGP marker packet.
//        //
//        if (o instanceof  PGPEncryptedDataList) {
//            enc = (PGPEncryptedDataList) o;
//        } else {
//            enc = (PGPEncryptedDataList) pgpF.nextObject();
//        }
// 
//        //
//        // find the secret key
//        //
//        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
//        PGPPrivateKey sKey = null;
//        PGPPublicKeyEncryptedData pbe = null;
// 
//        while (sKey == null && it.hasNext()) {
//            pbe = it.next();
// 
//            sKey = findSecretKey(keyIn, pbe.getKeyID(), passwd);
//        }
// 
//        if (sKey == null) {
//            throw new IllegalArgumentException("Secret key for message not found.");
//        }
// 
//        InputStream clear = pbe.getDataStream(sKey, "BC");
// 
//        PGPObjectFactory plainFact = new PGPObjectFactory(clear);
// 
//        Object message = plainFact.nextObject();
// 
//        if (message instanceof  PGPCompressedData) {
//            PGPCompressedData cData = (PGPCompressedData) message;
//            PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
// 
//            message = pgpFact.nextObject();
//        }
// 
//        if (message instanceof  PGPLiteralData) {
//            PGPLiteralData ld = (PGPLiteralData) message;
// 
//            InputStream unc = ld.getInputStream();
//            int ch;
// 
//            while ((ch = unc.read()) >= 0) {
//                out.write(ch);
//            }
//        } else if (message instanceof  PGPOnePassSignatureList) {
//        	message = plainFact.nextObject();
//        	
//        	PGPLiteralData ld = (PGPLiteralData) message;
//        	 
//            InputStream unc = ld.getInputStream();
//            int ch;
// 
//            while ((ch = unc.read()) >= 0) {
//                out.write(ch);
//            }
//            //throw new PGPException("Encrypted message contains a signed message - not literal data.");
//        } else {
//            throw new PGPException("Message is not a simple encrypted file - type unknown.");
//        }
// 
//        if (pbe.isIntegrityProtected()) {
//            if (!pbe.verify()) {
//                throw new PGPException("Message failed integrity check");
//            }
//        }
//    }
//    
//    public static PGPSecretKey readSecretKeyFromCol(InputStream in, long keyId) throws IOException, PGPException {
//    	in = PGPUtil.getDecoderStream(in);
//    	PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(in, new BcKeyFingerprintCalculator());
//
//    	PGPSecretKey key = pgpSec.getSecretKey(keyId);
//
//    	if (key == null) {
//    	    throw new IllegalArgumentException("Can't find encryption key in key ring.");
//    	}
//    	return key;
//    	}
//    
//    @SuppressWarnings("rawtypes")
//    public static PGPPublicKey readPublicKeyFromCol(InputStream in) throws IOException, PGPException {
//        in = PGPUtil.getDecoderStream(in);
//        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(in, new BcKeyFingerprintCalculator());
//        PGPPublicKey key = null;
//        Iterator rIt = pgpPub.getKeyRings();
//        while (key == null && rIt.hasNext()) {
//            PGPPublicKeyRing kRing = (PGPPublicKeyRing) rIt.next();
//            Iterator kIt = kRing.getPublicKeys();
//            while (key == null && kIt.hasNext()) {
//                PGPPublicKey k = (PGPPublicKey) kIt.next();
//                if (k.isEncryptionKey()) {
//                    key = k;
//                }
//            }
//        }
//        if (key == null) {
//            throw new IllegalArgumentException("Can't find encryption key in key ring.");
//        }
//        return key;
//    }
//    
//    public void decryptFile(InputStream in, InputStream secKeyIn, InputStream pubKeyIn, char[] pass) throws IOException, PGPException, InvalidCipherTextException {
//        Security.addProvider(new BouncyCastleProvider());
//
//        PGPPublicKey pubKey = readPublicKeyFromCol(pubKeyIn);
//
//        PGPSecretKey secKey = readSecretKeyFromCol(secKeyIn, pubKey.getKeyID());
//
//        in = PGPUtil.getDecoderStream(in);
//
//        JcaPGPObjectFactory pgpFact;
//
//
//        PGPObjectFactory pgpF = new PGPObjectFactory(in, new BcKeyFingerprintCalculator());
//
//        Object o = pgpF.nextObject();
//        PGPEncryptedDataList encList;
//
//        if (o instanceof PGPEncryptedDataList) {
//
//            encList = (PGPEncryptedDataList) o;
//
//        } else {
//
//            encList = (PGPEncryptedDataList) pgpF.nextObject();
//
//        }
//
//        Iterator<PGPPublicKeyEncryptedData> itt = encList.getEncryptedDataObjects();
//        PGPPrivateKey sKey = null;
//        PGPPublicKeyEncryptedData encP = null;
//        while (sKey == null && itt.hasNext()) {
//            encP = itt.next();
//            secKey = readSecretKeyFromCol(new FileInputStream("PrivateKey.asc"), encP.getKeyID());
//            sKey = secKey.extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass));
//        }
//        if (sKey == null) {
//            throw new IllegalArgumentException("Secret key for message not found.");
//        }
//
//        InputStream clear = encP.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));
//
//        pgpFact = new JcaPGPObjectFactory(clear);
//
//        PGPCompressedData c1 = (PGPCompressedData) pgpFact.nextObject();
//
//        pgpFact = new JcaPGPObjectFactory(c1.getDataStream());
//
//        PGPLiteralData ld = (PGPLiteralData) pgpFact.nextObject();
//        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
//
//        InputStream inLd = ld.getDataStream();
//
//        int ch;
//        while ((ch = inLd.read()) >= 0) {
//            bOut.write(ch);
//        }
//
//        //System.out.println(bOut.toString());
//
//        bOut.writeTo(new FileOutputStream(ld.getFileName()));
//        //return bOut;
//
//    }
// 
//    public static void encryptFile(OutputStream out, String fileName,
//        PGPPublicKey encKey, boolean armor, boolean withIntegrityCheck)
//        throws IOException, NoSuchProviderException, PGPException
//    {
//        Security.addProvider(new BouncyCastleProvider());
// 
//        if (armor) {
//            out = new ArmoredOutputStream(out);
//        }
// 
//        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
// 
//        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(
//            PGPCompressedData.ZIP);
// 
//        org.bouncycastle.openpgp.PGPUtil.writeFileToLiteralData(comData.open(bOut),
//            PGPLiteralData.BINARY, new File(fileName));
// 
//        comData.close();
// 
//        PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(
//            PGPEncryptedData.CAST5, withIntegrityCheck,
//            new SecureRandom(), "BC");
// 
//        cPk.addMethod(encKey);
// 
//        byte[] bytes = bOut.toByteArray();
// 
//        OutputStream cOut = cPk.open(out, bytes.length);
// 
//        cOut.write(bytes);
// 
//        cOut.close();
// 
//        out.close();
//    }
// 
//}

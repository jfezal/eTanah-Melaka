package etanah.gpg.pgpfile;

import java.io.FileReader;
import java.io.IOException;
import java.security.Security;
import java.util.Properties;

public class MyTest {
	
	private Properties prop;
	
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public void initProp(){
		FileReader reader;
		try {
			reader = new FileReader("files.properties");
			prop=new Properties();  
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public void testEncrypt() {
		PGPFileProcessor pfp = new PGPFileProcessor();
		if(this.prop==null) {
			pfp.setKeyFile("D:/laragon/www/test_pgp/gpg/pubring.gpg");
			pfp.setInputFile("D:/laragon/www/4_24_PPL_20201104150405.txt");
			pfp.setOutputFile("D:/laragon/www/test_pgp/4_24_PPL_20201104150405.gpg");
			pfp.setPassphrase("");
		}else {
			pfp.setKeyFile(prop.getProperty("publicKeyFile"));
			pfp.setInputFile(prop.getProperty("publicInputFile"));
			pfp.setOutputFile(prop.getProperty("publicOutputFile"));
			pfp.setPassphrase("");
		}
		
		try {
			if(pfp.encrypt()) {
				System.out.println("successful encrypt!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testDecrypt() {
		PGPFileProcessor pfp = new PGPFileProcessor();
		if(this.prop==null) {
			pfp.setKeyFile("D:/laragon/www/test_pgp/gpg/secring.gpg");
			pfp.setInputFile("D:/laragon/www/test_pgp/4_24_PPL_20201104150405.gpg");
			pfp.setOutputFile("D:/laragon/www/test_pgp/4_24_PPL_20201104150405.txt");
			pfp.setPassphrase("abc!@3");
		}else {
			pfp.setKeyFile(prop.getProperty("privateKeyFile"));
			pfp.setInputFile(prop.getProperty("privateInputFile"));
			pfp.setOutputFile(prop.getProperty("privateOutputFile"));
			pfp.setPassphrase(prop.getProperty("privatePassPhrase"));
		}
		
		try {
			if(pfp.decrypt()) {
				System.out.println("successful decrypt!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		String param1 = "3";
		String param2 = "2";
		MyTest my = new MyTest();
		
		if(args.length>0)
			param1 = args[0]; 
		if(args.length==2)
			param2 = args[1];  
		
		if(param2.equals("2"))
			my.initProp();
			
		
		if(param1.equals("1"))
			my.testEncrypt();
		else if(param1.equals("2"))
			my.testDecrypt();
		else if(param1.equals("3")) {
			my.testEncrypt();
			my.testDecrypt();
		}
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.gpg.pgpfile;

import etanah.etapp.ws.form.StatusForm;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.jws.WebService;

/**
 *
 * @author mohd.faidzal
 */
@WebService(endpointInterface = "etanah.gpg.pgpfile.DecryptService")
public class DecryptServiceImpl implements DecryptService {
private Properties prop;

    @Override
    public StatusForm decrypt(String encryptPath, String decryptedPath) {
        StatusForm s = new StatusForm();
        initProp();
        PGPFileProcessor pfp = new PGPFileProcessor();
        if (this.prop == null) {
            pfp.setKeyFile("D:/laragon/www/test_pgp/gpg/secring.gpg");
            pfp.setInputFile("D:/laragon/www/test_pgp/4_24_PPL_20201104150405.gpg");
            pfp.setOutputFile("D:/laragon/www/test_pgp/4_24_PPL_20201104150405.txt");
            pfp.setPassphrase("abc!@3");
        } else {
            pfp.setKeyFile(prop.getProperty("privateKeyFile"));
            pfp.setInputFile(encryptPath);
            pfp.setOutputFile(decryptedPath);
            pfp.setPassphrase(prop.getProperty("privatePassPhrase"));
        }

        try {
            System.out.println(" encryptPath"+encryptPath);
                        System.out.println("decryptedPath"+decryptedPath);
            if (pfp.decrypt()) {
                System.out.println("successful decrypt!!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
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

    @Override
    public String status() {
return "true";    }
}

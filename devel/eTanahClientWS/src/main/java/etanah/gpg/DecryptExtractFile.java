/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.gpg;

import etanah.ws.ispeks.client.WsIspeks;
import etanah.ws.ispeks.client.WsIspeksPortType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author mohd.faidzal
 */
public class DecryptExtractFile {

    private Properties prop;

    public void processExtract(String kat, String path) throws FileNotFoundException, ParseException {

        WsIspeks service = new WsIspeks();
        WsIspeksPortType port = service.getWsIspeksHttpPort();
        System.out.println("filename"+path);
        Boolean a = port.extractToDatabase(kat, path);
        if(a){
                    System.out.println("Succeed+"+path);

        }else{
        System.out.println("failed+"+path);
        }

    }

    public String processDecrypt(String pathNFS, String fileName) {
        String namewoexe = FilenameUtils.removeExtension(fileName);
        initProp();
        PGPFileProcessor pfp = new PGPFileProcessor();
        String encryptPath = pathNFS + File.separator + fileName;
        String decryptedPath = pathNFS + File.separator + namewoexe + ".txt";

        pfp.setKeyFile(prop.getProperty("privateKeyFile"));
        pfp.setInputFile(encryptPath);
        pfp.setOutputFile(decryptedPath);
        pfp.setPassphrase(prop.getProperty("privatePassPhrase"));

        try {
            System.out.println(" encryptPath" + encryptPath);
            System.out.println("decryptedPath" + decryptedPath);
            if (pfp.decrypt()) {
                System.out.println("successful decrypt!!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decryptedPath;
    }

    public void initProp() {
        FileReader reader;
        try {
            reader = new FileReader("tiles.properties");
            prop = new Properties();
            prop.load(reader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

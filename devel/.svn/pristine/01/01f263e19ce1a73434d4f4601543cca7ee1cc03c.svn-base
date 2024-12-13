/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import etanah.IspeksConfig;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class IspeksEncryptDecryptService {

    @Inject
    IspeksConfig ispeksConfig;
    private static final Logger LOG = Logger.getLogger(IspeksEncryptDecryptService.class);

    public IspeksStatus encrypt() {
        IspeksStatus status = new IspeksStatus();
        PGPFileProcessor pfp = new PGPFileProcessor();

        pfp.setKeyFile(ispeksConfig.getProperty("publicKeyFile"));
        pfp.setInputFile(ispeksConfig.getProperty("publicInputFile"));
        pfp.setOutputFile(ispeksConfig.getProperty("publicOutputFile"));
        pfp.setPassphrase("");
        try {
            if (pfp.encrypt()) {
                System.out.println("successful encrypt!!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOG.info("Encryption: " + e.toString());
        }
        return status;
    }

    public IspeksStatus encrypt(IspeksStatus status, String pathFileSource, String pathFileEncrypted) {
        PGPFileProcessor pfp = new PGPFileProcessor();

        pfp.setKeyFile(ispeksConfig.getProperty("publicKeyFile"));
        pfp.setInputFile(pathFileSource);
        pfp.setOutputFile(pathFileEncrypted);
        pfp.setPassphrase("");

        try {
            if (pfp.encrypt()) {
                System.out.println("successful encrypt!!");
                status.setPathIDD(pathFileSource);
                status.setPathGPG(pathFileEncrypted);
//                                status.setStatusIdd("Y");
                status.setStatusGPG("Y");
            }
        } catch (Exception e) {
            status.setPathGPG(pathFileEncrypted);
//                                status.setStatusIdd("Y");
            status.setStatusGPG("T");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }

    public IspeksStatus decrypt(String pathFileSource, String pathFileDecrypted) {
LOG.info("PATH pathFileSource" + pathFileSource);
            LOG.info("PATH pathFileDecrypted" + pathFileDecrypted);
        IspeksStatus status = new IspeksStatus();
        PGPFileProcessor pfp = new PGPFileProcessor();

        pfp.setKeyFile(ispeksConfig.getProperty("privateKeyFile"));
        pfp.setInputFile(pathFileSource);
        pfp.setOutputFile(pathFileDecrypted);
        pfp.setPassphrase(ispeksConfig.getProperty("privatePassPhrase"));
LOG.info("privatePassPhrase" + pfp.getPassphrase());
        try {
            if (pfp.decrypt()) {
                System.out.println("successful decrypt!!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.jcraft.jsch.ChannelSftp;
import static com.jcraft.jsch.ChannelSftp.LsEntrySelector.CONTINUE;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import etanah.IspeksConfig;
import etanah.integration.ispeks.DecryptService;
import etanah.integration.ispeks.DecryptServiceImplService;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksFileProses;
import etanah.view.stripes.ispeks.FileUtilForm;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class IspeksSftpService {
    
    @Inject
    IspeksConfig ispeksConfig;
    @Inject
    IspeksEncryptDecryptService ispeksEncryptDecryptService;
    @Inject
    IspeksService ispeksService;
    String host;
    String username;
    Integer port;
    String password;
    String identity;
    Integer session_timeout = 5000;
    Integer channel_timeout = 10000;
    Boolean isSSH = Boolean.FALSE;
    String localFolder;
    String remoteFolderPath;
    private static final Logger LOG = Logger.getLogger(IspeksSftpService.class);
    
    public IspeksStatus transfer(IspeksStatus i) {
        host = ispeksConfig.getProperty("remote_host");
        username = ispeksConfig.getProperty("username");
        port = Integer.valueOf(ispeksConfig.getProperty("remote_port"));
        password = ispeksConfig.getProperty("password");
        identity = ispeksConfig.getProperty("identity");
        remoteFolderPath = ispeksConfig.getProperty("remote_folder_in");
        
        SFTPFileTransfer sftp = new SFTPFileTransfer(host, port, username, password, identity, session_timeout, channel_timeout, isSSH);
        try {
            LOG.info("PATH GPG Local" + i.pathGPG);
            LOG.info("PATH GPG Remote" + remoteFolderPath + File.separator + i.GPGFileName);
            sftp.uploadSftpFromPath(i.pathGPG, remoteFolderPath + File.separator + i.GPGFileName);
            i.setStatusSftp("Y");
        } catch (Exception ex) {
            LOG.info("Error SFTP" + ex);
            i.setStatusSftp("T");
            
        }
        return i;
    }
    
    public IspeksStatus download(String fileName, Pengguna pengguna) {
        IspeksStatus i = new IspeksStatus();
        host = ispeksConfig.getProperty("remote_host");
        username = ispeksConfig.getProperty("username");
        port = Integer.valueOf(ispeksConfig.getProperty("remote_port"));
        password = ispeksConfig.getProperty("password");
        identity = ispeksConfig.getProperty("identity");
        remoteFolderPath = ispeksConfig.getProperty("remote_folder_out");
        localFolder = ispeksConfig.getProperty("local_folder");
        String urlClient = ispeksConfig.getProperty("ispeks.client");
        SFTPFileTransfer sftp = new SFTPFileTransfer(host, port, username, password, identity, session_timeout, channel_timeout, isSSH);
        String namewoexe = "";
        try {
            LOG.info("PATH Local" + localFolder + File.separator + fileName);
            LOG.info("PATH Remote" + remoteFolderPath + File.separator + fileName);
            sftp.downloadSftpFromPath(remoteFolderPath, fileName, localFolder + File.separator + fileName);
//        i.setStatusSftp("Y");
            namewoexe = FilenameUtils.removeExtension(fileName);
//            ispeksEncryptDecryptService.decrypt(localFolder + File.separator + fileName, localFolder + File.separator + namewoexe+".txt");

            URL url = new URL(urlClient);            
            DecryptServiceImplService client = new DecryptServiceImplService(url,new QName("http://pgpfile.gpg.etanah/", "DecryptServiceImplService"));
            DecryptService service = client.getDecryptServiceImplPort();
            service.decrypt(localFolder + File.separator + fileName, localFolder + File.separator + namewoexe + ".txt");
//                       ispeksEncryptDecryptService.decrypt(localFolder + File.separator + fileName, localFolder + File.separator + namewoexe+".txt");

        } catch (Exception ex) {
            LOG.info("Error SFTP" + ex);
//                i.setStatusSftp("T");

        } finally {
            System.out.println("finally block executed");
            IspeksFileProses file = new IspeksFileProses();
            file.setNamaFail(namewoexe + ".txt");
            file.setTarikhMuatTurun(new Date());
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new Date());
            file.setInfoAudit(infoAudit);
            ispeksService.simpanIspeksFileProses(file);
        }
        return i;
    }
    
    public List<FileUtilForm> getListFileFromRemote() throws JSchException {
        host = ispeksConfig.getProperty("remote_host");
        username = ispeksConfig.getProperty("username");
        port = Integer.valueOf(ispeksConfig.getProperty("remote_port"));
        password = ispeksConfig.getProperty("password");
        identity = ispeksConfig.getProperty("identity");
        remoteFolderPath = ispeksConfig.getProperty("remote_folder_out");
        localFolder = ispeksConfig.getProperty("local_folder");
        SFTPFileTransfer sftp = new SFTPFileTransfer(host, port, username, password, identity, session_timeout, channel_timeout, isSSH);
        
        return sftp.list(remoteFolderPath);
    }
    
    public List<FileUtilForm> getListFileToProcess() {
        localFolder = ispeksConfig.getProperty("local_folder");
        List<FileUtilForm> list = new ArrayList<FileUtilForm>();
//        File dirPath = new File(localFolder);
//        File filesList[] = dirPath.listFiles();
//        if (filesList != null) {
//            for (File file : filesList) {
//                if (file.isFile()) {
//                    FileUtilForm e = new FileUtilForm();
//                    e.setFileName(file.getName());
//                    list.add(e);
//                }
//            }
//        }
        
        list = ispeksService.findAllList();
        return list;
    }
}

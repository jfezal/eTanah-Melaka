/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sftpapp;

import com.jcraft.jsch.JSchException;
import etanah.gpg.DecryptExtractFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class CopyNFS {

    private String remote_host = "192.168.45.110";
    private String username = "ETANAH";
    private String password = "00030";
    private int remote_port = 22;
    private int session_timeout = 100000;
    private int channel_timeout = 5000;
    private String identity = "";
    private boolean isSSH = false;
    private String remoteFolderPath = "/comm/ETANAH/Out/";
    private String localFolder = "/nfs_apps/ISPEKS/IN/";

    public void copy() throws JSchException {
        SFTPFileTransfer sftpp = new SFTPFileTransfer(remote_host, remote_port, username, password, identity, session_timeout, channel_timeout, isSSH);
        List<FileUtilForm> list = sftpp.list(remoteFolderPath);
        try {
            System.out.println(new Date() + "Total File to process is" + list.size());
            int f = 0;
            Calendar cal = Calendar.getInstance();
            int tahun = cal.get(Calendar.YEAR);
            int bulan = cal.get(Calendar.MONTH) + 1;
            int hari = cal.get(Calendar.DATE);
            for (FileUtilForm fi : list) {
                f++;
                String fileName = fi.getFileName();
                String path = localFolder + File.separator + tahun + File.separator + bulan + File.separator + hari + File.separator + fileName;
                sftpp.downloadSftpFromPath(remoteFolderPath, fileName, path);
                sftpp.archiveRemove(remoteFolderPath, fileName);
                File file = new File(path);
                DecryptExtractFile dec = new DecryptExtractFile();
                String localPathTxt = dec.processDecrypt(localFolder, file.getName());
//                     String localPathTxt = localFolder+File.separator+fileName;
                String kategori = "";
                if (localPathTxt.contains("RSP")) {
                    kategori = "r";
                } else if (localPathTxt.contains("CTL")) {
                    kategori = "c";
                } else if (localPathTxt.contains("BPJ")) {
                    kategori = "b";
                }

                dec.processExtract(kategori, localPathTxt);
                System.out.println("Extract completed for file " + f);
            }
            System.out.println(new Date() + "Task Completed " + list.size());
        } catch (Exception ex) {
            Logger.getLogger(CopyNFS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

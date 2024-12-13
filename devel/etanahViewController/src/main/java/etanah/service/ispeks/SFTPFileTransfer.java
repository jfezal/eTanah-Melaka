package etanah.service.ispeks;

import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.jcraft.jsch.*;
import static com.jcraft.jsch.ChannelSftp.LsEntrySelector.CONTINUE;
import etanah.view.stripes.ispeks.FileUtilForm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class SFTPFileTransfer {

    private String remote_host;
    private String username;
    private String password;
    private int remote_port;
    private int session_timeout;
    private int channel_timeout;
    private String identity;
    private boolean isSSH;
    private ArrayList<UploadFileForm> listUploadFile;

    public boolean isSSH() {
        return isSSH;
    }

    public void setSSH(boolean isSSH) {
        this.isSSH = isSSH;
    }

    public ArrayList<UploadFileForm> getListUploadFile() {
        return listUploadFile;
    }

    public void setListUploadFile(ArrayList<UploadFileForm> listUploadFile) {
        this.listUploadFile = listUploadFile;
    }

    public SFTPFileTransfer(String host, int port, String username, String password, String identity, int session_timeout, int channel_timeout, boolean isSSH) {
        this.remote_host = host;
        this.username = username;
        this.remote_port = port;
        this.password = password;
        this.identity = identity;
        this.session_timeout = session_timeout;
        this.channel_timeout = channel_timeout;
        this.isSSH = isSSH;

        listUploadFile = new ArrayList<UploadFileForm>();
    }

    @SuppressWarnings("unused")
    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        Session jschSession = jsch.getSession(username, remote_host, remote_port);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public ChannelSftp setupJschSsh() throws JSchException {

        JSch jsch = new JSch();

        //local private key
        jsch.addIdentity(identity);
        Session jschSession = jsch.getSession(username, remote_host, remote_port);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.connect();

        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public boolean uploadSftpFromPath(String localFile, String sftpFile) throws JSchException, SftpException {
        ChannelSftp channelSftp = null;
        try {
            if (isSSH) {
                channelSftp = setupJschSsh();
            } else {
                channelSftp = setupJsch();
            }
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }

        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }
        try {
            channelSftp.put(localFile, sftpFile);

        } catch (SftpException e) {
            // throw the exception
            throw e;
        }
        channelSftp.exit();
        return true;
    }

    public boolean uploadSftpFromInputStream(InputStream localFile, String sftpFile) throws JSchException, SftpException {
        ChannelSftp channelSftp = null;
        try {
            if (isSSH) {
                channelSftp = setupJschSsh();
            } else {
                channelSftp = setupJsch();
            }
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }
        try {
            channelSftp.put(localFile, sftpFile);

        } catch (SftpException e) {
            // throw the exception
            throw e;
        }
        channelSftp.exit();
        return true;
    }

    public void uploadSftpByDirectory(File dirPath, String remoteFolder) throws JSchException, SftpException {
        listUploadFile = new ArrayList<UploadFileForm>();
        File filesList[] = dirPath.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {

                String remoteFile = remoteFolder + file.getName();
                String localFile = file.getPath();

                this.uploadSftpFromPath(localFile, remoteFile);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date now = new Date();

                UploadFileForm muf = new UploadFileForm();
                muf.setLocalFileLocation(localFile);
                muf.setSftpFileLocation(remoteFile);
                muf.setFileName(file.getName());
                muf.setUploadAt(sdf.format(now));

                listUploadFile.add(muf);

            } else {
                uploadSftpByDirectory(file, remoteFolder);
            }
        }
    }

    public void downloadSftpFromPath(String remoteFolderPath,String remote, String local) throws JSchException, SftpException {
        ChannelSftp channelSftp = null;
        try {
            if (isSSH) {
                channelSftp = setupJschSsh();
            } else {
                channelSftp = setupJsch();
            }
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }

        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }
        try {
            channelSftp.cd(remoteFolderPath);
            File file = new File(local);
            file.getParentFile().mkdirs();
            channelSftp.get(remote, file.getAbsolutePath());

        } catch (SftpException e) {
e.printStackTrace();
throw e;
        }
        channelSftp.exit();
    }

    public List<FileUtilForm> list(String SFTPWORKINGDIR) throws JSchException {
        List<FileUtilForm> list = new ArrayList<FileUtilForm>();
        ChannelSftp channelSftp = null;
        try {
            if (isSSH) {
                channelSftp = setupJschSsh();
            } else {
                channelSftp = setupJsch();
            }
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }

        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
            throw e;
        }
        try {
//            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTPWORKINGDIR);
            final List<String> filelist = new ArrayList<String>();
            ChannelSftp.LsEntrySelector selector = new ChannelSftp.LsEntrySelector() {
                public int select(ChannelSftp.LsEntry entry) {
                    final String filename = entry.getFilename();
                    if (filename.equals(".") || filename.equals("..")) {
                        return CONTINUE;
                    }
                    if (entry.getAttrs().isLink()) {
                        filelist.add(filename);
                    } else if (entry.getAttrs().isDir()) {
                        //if (keepDirectory(filename)) {
                        filelist.add(entry.getFilename());
                        //}
                    } else {
                        //if (keepFile(filename)) {
                        filelist.add(entry.getFilename());
                        //}
                    }
                    return CONTINUE;
                }
            };
            channelSftp.ls(SFTPWORKINGDIR, selector);
            for (int i = 0; i < filelist.size(); i++) {
                System.out.println(filelist.get(i));
                FileUtilForm e = new FileUtilForm();
                e.setFileName(filelist.get(i));
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            channelSftp.exit();

        }
        return list;
    }

}

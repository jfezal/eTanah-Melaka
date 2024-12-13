package etanah.sftpapp;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class SFTPProcessor {
	private String remote_host;
	private String username;
	private String password;
	private int remote_port;
	private int session_timeout;
	private int channel_timeout;
	private String remoteFolder;
	private String localFolder;
	private String identity;
	private boolean isSSH;
	private ArrayList<UploadFileForm> listUploadFile;
	
	public String getRemote_host() {
		return remote_host;
	}
	public void setRemote_host(String remote_host) {
		this.remote_host = remote_host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRemote_port() {
		return remote_port;
	}
	public void setRemote_port(int remote_port) {
		this.remote_port = remote_port;
	}
	public int getSession_timeout() {
		return session_timeout;
	}
	public void setSession_timeout(int session_timeout) {
		this.session_timeout = session_timeout;
	}
	public int getChannel_timeout() {
		return channel_timeout;
	}
	public void setChannel_timeout(int channel_timeout) {
		this.channel_timeout = channel_timeout;
	}
	public String getRemoteFolder() {
		return remoteFolder;
	}
	public void setRemoteFolder(String remoteFolder) {
		this.remoteFolder = remoteFolder;
	}
	public String getLocalFolder() {
		return localFolder;
	}
	public void setLocalFolder(String localFolder) {
		this.localFolder = localFolder;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
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
	
	public boolean uploadDirectory() throws Exception {
		
		try {

			SFTPFileTransfer sftp = new SFTPFileTransfer(remote_host, remote_port, username, password, identity, session_timeout, channel_timeout, isSSH);
			
			File folder = new File(localFolder);
			sftp.uploadSftpByDirectory(folder, remoteFolder);
			this.setListUploadFile(sftp.getListUploadFile());
			
		} catch (Exception e) {

			e.printStackTrace();

		}

		return true;
	}
	
	public boolean uploadFromPath(String localFile, String sftpFile) {
		
		try {

			SFTPFileTransfer sftp = new SFTPFileTransfer(remote_host, remote_port, username, password, identity, session_timeout, channel_timeout, isSSH);
			sftp.uploadSftpFromPath(localFile, sftpFile);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;
	}
	
	public boolean uploadFromInputStream(InputStream localFile, String sftpFile) {
		try {

			SFTPFileTransfer sftp = new SFTPFileTransfer(remote_host, remote_port, username, password, identity, session_timeout, channel_timeout, isSSH);
			sftp.uploadSftpFromInputStream(localFile, sftpFile);
		} catch (Exception e) {

			e.printStackTrace();

		}
		return true;
	}

}

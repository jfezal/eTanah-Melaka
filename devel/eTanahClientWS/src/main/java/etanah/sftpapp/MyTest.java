package etanah.sftpapp;

import com.jcraft.jsch.JSchException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MyTest {
	
	private Properties prop;
	
	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	

	
	public void testUploadFolder() {
		SFTPProcessor sftpp = new SFTPProcessor();
		if(this.prop==null) {
			sftpp.setUsername("etanah");
			sftpp.setPassword("etanah123");
			sftpp.setIdentity("");
			sftpp.setChannel_timeout(10000);
			sftpp.setSession_timeout(5000);
			sftpp.setRemote_host("kjsb.zapto.org");
			sftpp.setRemote_port(9002);
			sftpp.setRemoteFolder("/home/etanah/test/");
			sftpp.setLocalFolder("C:/nfs_apps/test/2");
			sftpp.setSSH(false);
		}else {
			sftpp.setUsername(prop.getProperty("username"));
			sftpp.setPassword(prop.getProperty("password"));
			sftpp.setIdentity(prop.getProperty("identity"));
			sftpp.setChannel_timeout(Integer.valueOf(prop.getProperty("channel_timeout")));
			sftpp.setSession_timeout(Integer.valueOf(prop.getProperty("session_timeout")));
			sftpp.setRemote_host(prop.getProperty("remote_host"));
			sftpp.setRemote_port(Integer.valueOf(prop.getProperty("remote_port")));
			sftpp.setRemoteFolder(prop.getProperty("remote_folder"));
			sftpp.setLocalFolder(prop.getProperty("local_folder"));
			sftpp.setSSH(Boolean.valueOf(prop.getProperty("isSSH")));
		}
		
		try {
			if(sftpp.uploadDirectory()) {
				ArrayList<UploadFileForm> listUploadFile = sftpp.getListUploadFile();
				
				for (UploadFileForm muf : listUploadFile) {
		            System.out.println("local:"+muf.getLocalFileLocation());
		            System.out.println("remote:"+muf.getSftpFileLocation());
		            System.out.println("file:"+muf.getFileName());
		            System.out.println("upload at:"+muf.getUploadAt());
		        }
				System.out.println("Done");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws JSchException {
		// TODO Auto-generated method stub
		MyTest mt = new MyTest();
//		mt.initProp();
//		mt.testUploadFolder();
//                mt.testDownloadFolder();
CopyNFS cop = new CopyNFS();
cop.copy();

	}

    private void testDownloadFolder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

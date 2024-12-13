package etanah.service.ispeks;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class IspeksTransferFileService {
	
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
			reader = new FileReader("ispeks.properties");
			prop=new Properties();  
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void testUploadFolder() {
		SFTPProcessor sftpp = new SFTPProcessor();
		if(this.prop==null) {
			sftpp.setUsername("root");
			sftpp.setPassword("@mt1550lut10n");
			sftpp.setIdentity("D:/laragon/www/id_rsa");
			sftpp.setChannel_timeout(10000);
			sftpp.setSession_timeout(5000);
			sftpp.setRemote_host("192.168.0.20");
			sftpp.setRemote_port(22);
			sftpp.setRemoteFolder("/home/amtis/upload/");
			sftpp.setLocalFolder("D:/laragon/www/test_pgp");
			sftpp.setSSH(true);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IspeksTransferFileService mt = new IspeksTransferFileService();
		mt.initProp();
		mt.testUploadFolder();

	}

}

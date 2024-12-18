package com.file.test;

import etanah.client.carianenq.ArrayOfAnyType;
import etanah.client.carianenq.ArrayOfString;
import etanah.client.carianenq.CarianEnquiry;
import etanah.client.carianenq.CarianEnquiryPortType;
import etanah.ws.agent.AccountAgentService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
//
//import localhost._8080.carianenquiry.ArrayOfString;
//import localhost._8080.carianenquiry.CarianEnquiry;
//import localhost._8080.carianenquiry.CarianEnquiryPortType;
//
//import org.w3._2001.xmlschema.ArrayOfAnyType;
//
//import etanah.ws.agent.AccountAgentService;
//import localhost._8080.carianenquiry.ArrayOfBase64Binary;

public class UtilTest {

	public UtilTest() {
		
	System.out.println("Comes to cosntructor ");
	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
	String d=df.format(new Date());
	System.out.println("[Date Parse :"+d+"]");
	String idHakmilik="050101HSD00150104";
	String idTransac="20111129105328978";
	// String path = getReportFormCore(idHakmilik, idTransac);
	byte[] s=getReportFormCore(idHakmilik, idTransac);
	System.out.println("Comes to cosntructor 1 ");
	 String url = "jdbc:oracle:thin:@10.0.4.38:1521:etanah";
     String username = "portal";
     String password = "portal123";
     Connection conn = null;
     PreparedStatement ps =null;
     String sql="insert into testblob (name,txnfile) values (?,?) ";
     InputStream is =null;
     try{
		     Class.forName("oracle.jdbc.driver.OracleDriver");
		     conn = DriverManager.getConnection(url, username, password);
		     System.out.println("Comes to cosntructor 2 ");
		     ps=conn.prepareStatement(sql);
		     ps.setString(1, "test1.pdf");
		     if(s!=null)
		       is = new ByteArrayInputStream(s);
		     System.out.println("Comes to cosntructor 3 ");
	         //ps.setBinaryStream(2, is,s.length);
		     ps.setBinaryStream(2, null);
	         int rs = ps.executeUpdate();
	         System.out.println("Comes to cosntructor 4");
	         System.out.println("[Table Update Returns :"+rs+"]");
	         
     }catch(Exception e){
    	 e.printStackTrace();
     }finally{
     	if(is !=null)
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

	}

	public static void main(String[] args) {
		 new UtilTest();
	}
	
	public byte[] getReportFormCore(String idHakmilik, String transac) {
        byte[] report = new byte[10 * 1024];  // 1 MB size
        ArrayOfString aos = new ArrayOfString();
        aos.getString().add(idHakmilik);
        CarianEnquiry service = new CarianEnquiry();
        CarianEnquiryPortType port = service.getCarianEnquiryHttpPort();
        ArrayOfAnyType aoat = port.getSijil(aos, "04", "CSHM", "ptspoc1");
         String fizikalName = null;
        for (int i = 0; i < aoat.getAnyType().size(); i++) {
            report = (byte[]) aoat.getAnyType().get(0);
             OutputStream out = null;
             //fizikalName = "D:/wsclient/" + transac + ".pdf";
           
            try {
//                out.write(report);
            } catch (Exception ex) {
                Logger.getLogger(AccountAgentService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        System.out.println("[From Core File Bytes :"+report+"]");
        return null;
    }

}

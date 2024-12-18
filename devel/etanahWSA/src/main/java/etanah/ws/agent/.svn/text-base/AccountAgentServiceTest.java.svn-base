/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.agent;

import etanah.client.bayar.BayaranOnline;
import etanah.client.bayar.BayaranOnlinePortType;
import etanah.ws.URLClient;
import etanah.ws.utility.StatusInfo;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 *
 * @author mohd.faidzal
 */
public class AccountAgentServiceTest {

    public StatusInfo updatePortalStage(String accountNo, BigDecimal amaun, String fpxNo,
            String transactionNo, String paymentTime, String paymentType, String resitNo, String dimasuk) throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        StatusInfo si = new StatusInfo();
        BayaranOnline service = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType conn = service.getBayaranOnlineHttpPort();
        try {
            si.setFunctionName("updateAccount");
            si.setStatusCode("SUCCESS");
            si.setStatusMessage("QuitRentAgentService: The account has been updated successfully");

            System.out.println("QuitRentAgent.checkAccount: NullPointerException.");
            si.setNoresit(conn.updateUserAccount(accountNo, amaun, transactionNo, "1", paymentTime, paymentType, fpxNo, dimasuk));
            System.out.println("QuitRentAgent.checkAccount: NullPointerException.");

        } catch (Exception x) {

        }
        conn.updateUserAccount(accountNo, amaun, transactionNo, "1", paymentTime, paymentType, fpxNo, dimasuk);
        return si;
    }

}

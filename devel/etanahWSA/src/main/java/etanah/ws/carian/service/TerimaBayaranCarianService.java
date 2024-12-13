/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.carian.service;

import etanah.client.carian.CarianPersendirianOnline;
import etanah.client.carian.CarianPersendirianOnlinePortType;
import etanah.ws.CarianPersendirianService;
import etanah.ws.URLClient;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 *
 * @author john
 */
public class TerimaBayaranCarianService {

    public String updateCarianAccount(String accountNo, String transId, String paymentDateTime, BigDecimal amaun, String idPengguna, String resitNo, String namaPenyerah) throws MalformedURLException {
        String flag = "success";
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        Logger.getLogger(TerimaBayaranCarianService.class.getName()).log(Level.SEVERE, "updateCarianAccount()" + u.carianpersendirian);

        try {
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
            CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
            port.updateCarianAccount(accountNo, transId, paymentDateTime, amaun, idPengguna, resitNo, namaPenyerah);
            return flag;
        } catch (Exception ex) {
            return "fail";
        }
    }

}

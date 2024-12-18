/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.agent;

import etanah.client.bayar.ArrayOfAccountInfo;
import etanah.client.bayar.BayaranOnline;
import etanah.client.bayar.BayaranOnlinePortType;
import etanah.ws.URLClient;
import etanah.ws.account.AccountInfo;
import etanah.ws.account.CarianAkaunInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;

/**
 *
 * @author faizah
 */
public class CarianAgentService {

    public CarianAkaunInfo carianHakmilik(String idHakmilik) throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.bayaranonline);
        CarianAkaunInfo info = new CarianAkaunInfo();
        BayaranOnline service = new BayaranOnline(url,new QName("http://localhost:8080/bayaranOnlineWS", "Bayaran_Online"));
        BayaranOnlinePortType port = service.getBayaranOnlineHttpPort();
        ArrayOfAccountInfo accountInfo = port.checkAccount(idHakmilik, idHakmilik);
        List<etanah.client.bayar.AccountInfo> list = accountInfo.getAccountInfo();
        for (etanah.client.bayar.AccountInfo s : list) {
            info.setAlamat(s.getAlamat().getValue());
            info.setIdHakmilik(idHakmilik);
            info.setNamaPemilik(s.getNamaPemilik().getValue());

        }
        return info;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.carian.service;

import com.etanah.portal.carian.UtilKodView;
import etanah.client.carian.ArrayOfUtilKod;
import etanah.client.carian.CarianPersendirianOnline;
import etanah.client.carian.CarianPersendirianOnlinePortType;
import etanah.client.carian.UtilKod;
import etanah.ws.URLClient;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;

/**
 *
 * @author john
 */
public class ListService {

    public List<UtilKodView> listKodDaerah() throws MalformedURLException {
//        List<UtilKodView> list = new ArrayList<UtilKodView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfUtilKod at = port.listKodDaerah();
        return setKod(at);
    }

    public List<UtilKodView> listKodMukim(String kodDaerah) throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));

        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        return setKod(port.listKodMukim(kodDaerah));
    }

    public List<UtilKodView> listKodSeksyen(String kodBpm) throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        return setKod(port.listKodSeksyen(kodBpm));
    }

    public List<UtilKodView> listKodLot() throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);

        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        return setKod(port.listKodLot());
    }

    public List<UtilKodView> listKodHakmilik() throws MalformedURLException {
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        return setKod(port.listKodHakmilik());
    }

    private List<UtilKodView> setKod(ArrayOfUtilKod at) {
        List<UtilKodView> list = new ArrayList<UtilKodView>();
        for (UtilKod as : at.getUtilKod()) {
            UtilKodView view = new UtilKodView();
            view.setKod(as.getKod().getValue());
            view.setNama(as.getNama().getValue());
            list.add(view);
        }
        return list;
    }
}

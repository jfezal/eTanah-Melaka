/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.carian.service;

import com.etanah.portal.carian.CarianInfoView;
import etanah.client.carian.ArrayOfCarianInfo;
import etanah.client.carian.CarianInfo;
import etanah.client.carian.CarianPersendirianOnline;
import etanah.client.carian.CarianPersendirianOnlinePortType;
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
public class CarianHakmilikService {

    public List<CarianInfoView> checkAccount(String accountNo, String idHakmilik) throws MalformedURLException {
        List<CarianInfoView> info = new ArrayList<CarianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfCarianInfo ai = port.checkAccount(accountNo.toUpperCase(), idHakmilik.toUpperCase());

        for (CarianInfo c : ai.getCarianInfo()) {
            CarianInfoView view = setValue(c);
            info.add(view);
        }

        return info;
    }

    public List<CarianInfoView> checkAccountByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot) throws MalformedURLException {
        List<CarianInfoView> info = new ArrayList<CarianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfCarianInfo ai = port.checkAccountByNoLot(daerah, bpm, seksyen, kodLot, noLot);
        for (CarianInfo c : ai.getCarianInfo()) {
            CarianInfoView view = setValue(c);
            info.add(view);
        }
        return info;
    }

    public List<CarianInfoView> checkAccountByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik) throws MalformedURLException {
        List<CarianInfoView> info = new ArrayList<CarianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfCarianInfo ai = port.checkAccountByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik);
        for (CarianInfo c : ai.getCarianInfo()) {
            CarianInfoView view = setValue(c);
            info.add(view);
        }
        return info;
    }

    public CarianInfoView setValue(CarianInfo ci) {
        CarianInfoView view = new CarianInfoView();
        view.setAkaunBatal(ci.getAkaunBatal().getValue());
        view.setAlamat(ci.getAlamat().getValue());
        view.setBandarpekanmukim(ci.getBandarpekanmukim().getValue());
        view.setIdHakmilik(ci.getIdHakmilik().getValue());
        view.setJenisHakmilik(ci.getJenisHakmilik().getValue());
        view.setNamaPemilik(ci.getNamaPemilik().getValue());
        view.setNoAkaun(ci.getNoAkaun().getValue());
        view.setNoHakmilik(ci.getNoHakmilik().getValue());
        view.setNoBangunan(ci.getNoBangunan().getValue());
        view.setNoTingkat(ci.getNoTingkat().getValue());
        view.setNoPetak(ci.getNoPetak().getValue());
        view.setKodDaerah(ci.getKodDaerah().getValue());
        view.setKodCaw(ci.getKodCaw().getValue());
        view.setKodCawValue(ci.getKodCawValue().getValue());
        return view;
    }

    public List<CarianInfoView> checkAccountStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) throws MalformedURLException {
        List<CarianInfoView> info = new ArrayList<CarianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfCarianInfo ai = port.checkAccountStrataByNoLot(daerah, bpm, seksyen, kodLot, noLot, noBangunan, noTingkat, noPetak);
        for (CarianInfo c : ai.getCarianInfo()) {
            CarianInfoView view = setValue(c);
            info.add(view);
        }
        return info;
    }

    public List<CarianInfoView> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) throws MalformedURLException {
        List<CarianInfoView> info = new ArrayList<CarianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url,new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfCarianInfo ai = port.checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik, noBangunan, noTingkat, noPetak);
        for (CarianInfo c : ai.getCarianInfo()) {
            CarianInfoView view = setValue(c);
            info.add(view);
        }
        return info;
    }
}

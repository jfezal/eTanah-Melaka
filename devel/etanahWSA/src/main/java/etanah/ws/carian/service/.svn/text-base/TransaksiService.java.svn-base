/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.carian.service;

import com.etanah.portal.carian.DokumenCarianPersendirianInfoView;
import com.etanah.portal.carian.DokumenInfoView;
import etanah.client.carian.ArrayOfDokumenCarianPersendirian;
import etanah.client.carian.CarianPersendirianOnline;
import etanah.client.carian.CarianPersendirianOnlinePortType;
import etanah.client.carian.DokumenCarianPersendirian;
import etanah.client.carian.DokumenInfo;
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
public class TransaksiService {

    public List<DokumenCarianPersendirianInfoView> senaraiDokumen(String idPengguna, String tarikhmula, String tarikhtamat) throws MalformedURLException {
        List<DokumenCarianPersendirianInfoView> list = new ArrayList<DokumenCarianPersendirianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfDokumenCarianPersendirian ar = port.senaraiDokumen(idPengguna, tarikhmula, tarikhtamat);
        for (DokumenCarianPersendirian a : ar.getDokumenCarianPersendirian()) {
            DokumenCarianPersendirianInfoView aa = new DokumenCarianPersendirianInfoView();
            aa.setBilPaparan(a.getBilPaparan().getValue());
            aa.setIdHakmilik(a.getIdHakmilik().getValue());
            aa.setIdPortalTransaksi(a.getIdPortalTransaksi().getValue());
            aa.setTarikh(a.getTarikh().getValue());
            list.add(aa);
        }
        return list;
    }
    
    public List<DokumenCarianPersendirianInfoView> senaraiDokumenIdHakmilik(String idPengguna, String idHakmilik) throws MalformedURLException {
        List<DokumenCarianPersendirianInfoView> list = new ArrayList<DokumenCarianPersendirianInfoView>();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        ArrayOfDokumenCarianPersendirian ar = port.senaraiDokumenIdHakmilik(idPengguna, idHakmilik);
        for (DokumenCarianPersendirian a : ar.getDokumenCarianPersendirian()) {
            DokumenCarianPersendirianInfoView aa = new DokumenCarianPersendirianInfoView();
            aa.setBilPaparan(a.getBilPaparan().getValue());
            aa.setIdHakmilik(a.getIdHakmilik().getValue());
            aa.setIdPortalTransaksi(a.getIdPortalTransaksi().getValue());
            aa.setTarikh(a.getTarikh().getValue());
            list.add(aa);
        }
        return list;
    }

    public DokumenInfoView muatturundokumen(Long id) throws MalformedURLException {
        DokumenInfoView view = new DokumenInfoView();
        URLClient u = new URLClient();
        URL url = new URL(u.carianpersendirian);
        CarianPersendirianOnline service = new CarianPersendirianOnline(url, new QName("http://localhost:8080/carianPersendirianOnlineWS", "CarianPersendirian_Online"));
        CarianPersendirianOnlinePortType port = service.getCarianPersendirianOnlineHttpPort();
        DokumenInfo info = port.muatturundokumen(id);
        view.setBytes(info.getBytes().getValue());
        view.setDocType(info.getDocType().getValue());
        view.setFileName(info.getFileName().getValue());
        return view;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.etappclient.service;

import com.google.inject.Inject;
import etanah.MyeTappConfig;
import etanah.etapp.etappclient.form.DokumenForm;
import etanah.etapp.etappclient.form.HakmilikForm;
import etanah.etapp.etappclient.form.PermohonanForm;
import etanah.integration.myetapp.EtappClientService;
import etanah.integration.myetapp.EtappClientServiceImplService;
import etanah.integration.myetapp.StatusForm;
//import etanah.etapp.ws.StatusForm;
import etanah.view.etapp.ws.EtappLogService;
//import intg.etapp.EtappClientService;
//import intg.etapp.EtappClientServiceImplServiceClient;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author mohd.faidzal
 */
public class Sek8Service {
    @Inject
    EtappLogService logservice;
        @Inject
MyeTappConfig myeTappConfig;
    String username;
    String password;
//    String urlClient = myeTappConfig.getProperty("myetapp.client");


    public StatusForm borangC(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

        URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
        permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);
        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        s = port.hantarMMK(idPermohonan, jsonStr);
//        s.setCode(res.getCode());
//        s.setDescription(res.getDescription());
//        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek8 : Penghanataran Borang C = " + idPermohonan, jsonStr, "", s.getCode());

        return s;
    }

    public StatusForm borangD(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

        URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
                permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);

        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        s = port.hantarBorangD(idPermohonan, jsonStr);
//        s.setCode(res.getCode());
//        s.setDescription(res.getDescription());
//        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek8 : Penghantaran Maklumat Endorsan Borang D = " + idPermohonan, jsonStr, "", s.getCode());

        return s;
    }

    public StatusForm borangK(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

       URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
                permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);

        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        s = port.hantarBorangK(idPermohonan, jsonStr);
//        s.setCode(res.getCode());
//        s.setDescription(res.getDescription());
//        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek8 : Penghantaran Maklumat Endorsan Borang K = " + idPermohonan, jsonStr, "", s.getCode());

        return s;
    }

    public StatusForm sijilPembebasanUkur(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

       URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
         EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
                permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);

        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        s = port.hantarSijilUkur(idPermohonan, jsonStr);
//        s.setCode(res.getCode());
//        s.setDescription(res.getDescription());
//        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek8 : Penghantaran Sijil Pembebasan Ukur = " + idPermohonan, jsonStr, "", s.getCode());

        return s;
    }

    public StatusForm maklumanPU(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

        URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
                permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);

        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        s = port.hantarHakmilikSambungan(idPermohonan, jsonStr);
        
//        s.setCode(res.getCode());
//        s.setDescription(res.getDescription());
//        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek8 : Penghantaran Sijil Pembebasan Ukur = " + idPermohonan, jsonStr, "", s.getCode());

        return s;
    }
}

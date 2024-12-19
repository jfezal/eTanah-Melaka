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
import etanah.model.InfoAudit;
import etanah.model.etapp.EtappLog;
import etanah.view.etapp.ws.EtappLogService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author mohd.faidzal
 */
public class Sek4Service {
@Inject
MyeTappConfig myeTappConfig;
    @Inject
    EtappLogService logservice;
//    String url = "http://192.168.0.10:9999/ws/etapp";
    String username;
    String password;


    public StatusForm borangA(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //PERMOHONAN BORANG A (SEKSYEN 4)
            String urlClient = myeTappConfig.getProperty("myetapp.client");

         URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);

        StatusForm res = port.hantarBorangA(idPermohonan, jsonStr);
        s.setCode(res.getCode());
        s.setDescription(res.getDescription());
        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek4 : Penghantaran Borang A = " + idPermohonan, jsonStr, "", res.getCode());
        System.out.println("ssasdasdasdasd" + s.getDescription() + s.getDetail() + idPermohonan);
        return s;
    }

    public StatusForm borangB(String idPermohonan, PermohonanForm f, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        //Permohonan Borang B Dan Pewartaan (Seksyen4
            String urlClient = myeTappConfig.getProperty("myetapp.client");

URL url = new URL(urlClient);
        StatusForm s = new StatusForm();
        EtappClientServiceImplService service = new EtappClientServiceImplService(url,new QName("http://ws.etapp.etanah/", "EtappClientServiceImplService"));
        EtappClientService port = service.getEtappClientServiceImplPort();
        SharedService sh = new SharedService();
        PermohonanForm permohonan = sh.setPermohonan(f);
        permohonan = sh.setlistDokumen(permohonan, listDokumenForm);
        permohonan = sh.setlistHakmilik(permohonan, listHakmilikForm);
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(permohonan);
        StatusForm res = port.hantarBorangB(idPermohonan, jsonStr);
        s.setCode(res.getCode());
        s.setDescription(res.getDescription());
        s.setDetail(res.getDetail());
        logservice.insertLog("ACQ", "1", "Sek4 : Penghantaran Borang B = " + idPermohonan, jsonStr, "", res.getCode());
        System.out.println("ssasdasdasdasd" + s.getDescription() + s.getDetail() + idPermohonan);

        return s;
    }
}

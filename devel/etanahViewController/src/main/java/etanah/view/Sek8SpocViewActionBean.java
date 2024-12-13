/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.view.stripes.tugasanutiliti.FormStatMMKN;
import java.io.IOException;
import java.util.Map;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONObject;

/**
 *
 * @author mohd.faidzal
 */

@UrlBinding("/spoc/sek8")
public class Sek8SpocViewActionBean extends AbleActionBean {

    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    PermohonanRujukanLuarService serviceBaru;
    
    
    Permohonan permohonan;
    HakmilikPermohonan hakmilikPermohonan;
    PermohonanPengambilan permohonanAmbil ;
    

    public Resolution findIdMohon() throws IOException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        permohonanAmbil = serviceBaru.findByidP(idPermohonan);
        MaklumatSek4Form form = new MaklumatSek4Form();
        ObjectMapper map = new ObjectMapper();
        form = pengambilanAPTService.setValueMaklumatSek4Form(idPermohonan);

        map.writeValueAsString(form);
//        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", map.writeValueAsString(form));
//        return new StreamingResolution("application/json", "{ \"success\": " + idPermohonan + " }");
    }
        public Resolution findIdMohonSeksyen8() throws IOException {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        permohonanAmbil = serviceBaru.findByidP(idPermohonan);
        MaklumatSek4Form form = new MaklumatSek4Form();
        ObjectMapper map = new ObjectMapper();
        form = pengambilanAPTService.setValueMaklumatSek8Form(idPermohonan);

        map.writeValueAsString(form);
//        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", map.writeValueAsString(form));
//        return new StreamingResolution("application/json", "{ \"success\": " + idPermohonan + " }");
    }
}

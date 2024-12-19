/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.tugasanutiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.KodUrusan;
import etanah.model.TugasanUtiliti;
import etanah.service.dev.integrationflow.TugasanUtilitiService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zipzap
 */
@UrlBinding("/tugasan/mmkn")
public class TugasanMMKActionBean extends AbleActionBean {

    @Inject
    TugasanUtilitiService tugasanUtilitiService;
    String idPermohonan;
    String kodUrusan;
    List<TugasanUtiliti> listPermohonan;
    List<KodUrusan> listKodUrusan;

    @DefaultHandler
    public Resolution showForm() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        listPermohonan = tugasanUtilitiService.findListTugasan(ctx.getUser().getIdPengguna());
        return new JSP("tugasan/tugasan_mmkn.jsp");
    }
     public Resolution cari() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        listPermohonan = tugasanUtilitiService.findListTugasan(ctx.getUser().getIdPengguna(),idPermohonan,kodUrusan);
        return new JSP("tugasan/tugasan_mmkn.jsp");
    }

    public Resolution jumlahStat() throws IOException {
        FormStatMMKN form = new FormStatMMKN();
        form = tugasanUtilitiService.setStatistikPermohonan();
        String value[] = {form.getTotalSek4(), form.getTotalSek8(), form.getTotalAduan(), form.getMyetappSek4(), form.getMyetappSek8()};
        String label[] = {"Sek 4", "Sek 8", "Aduan Kerosakan", "MyeTapp Sek 4", "MyeTapp Sek 8"};
        return new StreamingResolution("text/plain", gen(value, label));
    }

    public Resolution jumlahStatLulus() throws IOException {
        FormStatMMKN form = new FormStatMMKN();
        form = tugasanUtilitiService.setStatistiLulusTolak();
        String value[] = {form.getLulus(), form.getTolak(), form.getKiv()};
        String label[] = {"LULUS", "TOLAK", "KIV"};
        return new StreamingResolution("text/plain", gen(value, label));
    }

    public String gen(String value[], String label[]) {
        String complete = "";

        String d = "[";

        String dd = "]";
        for (int i = 0; i < value.length; i++) {
            String a = "{ y: " + value[i] + ", label: \"" + label[i] + "\" }";
            String b = "";
            if (i == 0) {
                b = a;
                complete = a;
            } else {
                b = complete + "," + a;
            }
            complete = b;
        }
        complete = d + complete + dd;
        return complete;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<TugasanUtiliti> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<TugasanUtiliti> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KodUrusan> getListKodUrusan() {
        String []list = {"SEK4","SEK4A","",""};
        listKodUrusan = tugasanUtilitiService.senaraiUrusan(list);
        return listKodUrusan;
    }

    public void setListKodUrusan(List<KodUrusan> listKodUrusan) {
        this.listKodUrusan = listKodUrusan;
    }
    
    

}

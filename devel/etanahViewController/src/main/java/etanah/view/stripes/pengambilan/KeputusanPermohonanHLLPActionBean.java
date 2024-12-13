/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


@UrlBinding("/pengambilan/keputusanHLLP")
public class KeputusanPermohonanHLLPActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PengambilanMMKService service;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodRujukan kodRujukan;
    private KodCawangan cawangan;


    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String keputusan;
    String jam;
    String minit;
    String saat;
    String ampm;
    String ampmDisplay;
    String keputusanDisplay;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/keputusanPermohonanHLLP.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKeputusan() != null) {
                        keputusan = permohonan.getKeputusan().getKod();
                        System.out.println("------keputusan---"+keputusan);
                        keputusanDisplay = permohonan.getKeputusan().getNama();
            }
        }
    }

    public Resolution simpanMesyuarat() {

        if (keputusan == null)
        {
               if (permohonanRujukanLuar == null) {

                if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }
    }else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            cawangan = permohonan.getCawangan();
            service.simpanPermohonan(permohonan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
    }
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/keputusanPermohonanHLLP.jsp").addParameter("tab", "true");
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getAmpmDisplay() {
        return ampmDisplay;
    }

    public void setAmpmDisplay(String ampmDisplay) {
        this.ampmDisplay = ampmDisplay;
    }

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author muhammad.amin
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;

@UrlBinding("/consent/mesyuarat")
public class KemasukanTarikhDanKeputusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String keputusan;
    String keputusanDisplay;
    String tarikhMesyuarat;
    private String idHakmilik;
    String jam;
    String minit;
    String ampm;
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("viewTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        getContext().getRequest().setAttribute("viewBilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTempat", Boolean.TRUE);
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanNoSijil", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showBicaraTanahAdat() {
        getContext().getRequest().setAttribute("tanahAdat", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparanBicaraTanahAdat() {
        getContext().getRequest().setAttribute("tanahAdat", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getKeputusan() != null) {
                keputusan = permohonan.getKeputusan().getKod();
                keputusanDisplay = permohonan.getKeputusan().getNama();
            }

            StringBuilder sb = new StringBuilder();
            if (!permohonan.getSenaraiHakmilik().isEmpty()) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    if (hp == null) {
                        continue;
                    }
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHakmilik = sb.toString();

            if (!permohonan.getSenaraiRujukanLuar().isEmpty()) {
                permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                if (permohonanRujukanLuar.getTarikhSidang() != null) {
                    //FOR TARIKH BICARA URUSAN TANAH ADAT
                    tarikhMesyuarat = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                    jam = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                    minit = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                    ampm = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                }
            }
        }
    }

    public Resolution simpanMesyuarat() {

        if (permohonanRujukanLuar == null || keputusan == null) {

            if (permohonanRujukanLuar.getNoSidang() == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
            } else if (permohonanRujukanLuar.getNoRujukan() == null) {
                addSimpleError("Sila Masukkan Nombor Sijil.");
            } else if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();

            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);
            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTiadaResult() {

        if (permohonanRujukanLuar.getNoSidang() == null) {

            addSimpleError("Sila Masukkan Bilangan Mesyuarat.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (tarikhMesyuarat != null) {
                try {
                    permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(KemasukanTarikhDanKeputusanActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);
            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanTiadaResult", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");

    }

    public Resolution simpanNoSijil() {

        if (permohonanRujukanLuar.getNoRujukan() == null) {

            addSimpleError("Sila Masukkan Nombor Sijil.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (tarikhMesyuarat != null) {
                try {
                    permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                } catch (java.text.ParseException ex) {
                    Logger.getLogger(KemasukanTarikhDanKeputusanActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);
            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("viewBilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTempat", Boolean.TRUE);
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanNoSijil", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");

    }

    public Resolution simpanDate() {

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanDate", Boolean.TRUE);
        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan() {

        if (keputusan == null) {
            addSimpleError("Sila Masukkan Keputusan.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);

            permohonan.setInfoAudit(infoAudit);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (permohonanRujukanLuar != null) {
                permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getUlasan());
            }
            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);
            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("viewTarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasan2() {

        if (keputusan == null) {

            addSimpleError("Sila Masukkan Keputusan.");

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            conService.simpanPermohonan(permohonan);

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            if (permohonanRujukanLuar != null) {
                permohonanRujLuarTemp.setUlasan(permohonanRujukanLuar.getUlasan());
            }
            permohonanRujLuarTemp.setCawangan(peng.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);
            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanUlasan2", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTanahAdat() {
        if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {
            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat");
            }
            if (permohonanRujukanLuar == null) {
                addSimpleError("Sila Masukkan Tempat Mesyuarat");
            }
        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();
            infoAudit = new InfoAudit();

            if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }

            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":00 " + ampm;

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf2.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(KemasukanTarikhDanKeputusanActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            permohonanRujLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());
            permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);

            conService.simpanRujukanLuar(permohonanRujLuarTemp);
            tarikhMesyuarat = sdf2.format(permohonanRujLuarTemp.getTarikhSidang()).substring(0, 10);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("tanahAdat", Boolean.TRUE);

        return new JSP("consent/kemasukan_tarikh_dan_keputusan.jsp").addParameter("tab", "true");
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

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
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
}



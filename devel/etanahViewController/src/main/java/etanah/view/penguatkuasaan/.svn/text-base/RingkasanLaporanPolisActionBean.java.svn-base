/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;

import etanah.model.KodCaraPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Date;
import etanah.model.KodAgensi;
import etanah.model.KodDokumen;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.Dokumen;
import etanah.dao.DokumenDAO;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import etanah.service.ConsentPtdService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.EnforceService;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurshahida.radzi
 */
@UrlBinding("/penguatkuasaan/ringkasan_laporan_polis")
public class RingkasanLaporanPolisActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RingkasanLaporanPolisActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodAgensiDAO agensiDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    private Permohonan permohonan;
    private String noRujukan;
    private String tarikhRujukan;
    private String catatan;
    private KodAgensi agensi;
    private String lokasiAgensi;
    private String idRujukan;
    private String idPermohonan;
    private String kodAgensiDiPilih;
    private Pengguna pguna;
    private String idLapor;
    private KodCawangan cawangan;
    private KodDokumen kodDokumenRujukan;
    private KodCaraPermohonan caraPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private long idDokumen;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String kodNegeri;

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public KodDokumen getKodDokumenRujukan() {
        return kodDokumenRujukan;
    }

    public void setKodDokumenRujukan(KodDokumen kodDokumenRujukan) {
        this.kodDokumenRujukan = kodDokumenRujukan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String getLokasiAgensi() {
        return lokasiAgensi;
    }

    public void setLokasiAgensi(String lokasiAgensi) {
        this.lokasiAgensi = lokasiAgensi;
    }

    public KodAgensi getAgensi() {
        return agensi;
    }

    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKodAgensiDiPilih() {
        return kodAgensiDiPilih;
    }

    public void setKodAgensiDiPilih(String kodAgensiDiPilih) {
        this.kodAgensiDiPilih = kodAgensiDiPilih;
    }



    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/ringkasan_laporan_polis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/ringkasan_laporan_polis_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeri = conf.getProperty("kodNegeri");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (permohonan.getKodUrusan().getKod().equals("427") || permohonan.getKodUrusan().getKod().equals("428")) {
                        permohonanRujukanLuar = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
                    } else {
                        permohonanRujukanLuar = enforceService.findLaporanPolisByIdpermohonan2(idPermohonan);
                    }
                } else {
                    permohonanRujukanLuar = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
                }

                if (permohonanRujukanLuar != null) {
                    if (permohonanRujukanLuar.getTarikhRujukan() != null) {

                        tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                        jam = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(11, 13);
                        if (jam.charAt(0) == '0') {
                            jam = jam.substring(1);
                        }
                        minit = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(14, 16);
                        ampm = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(17, 19);
                    }

                    kodAgensiDiPilih = permohonanRujukanLuar.getAgensi().getKod();
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    public Resolution refreshpage() {
        return new RedirectResolution(RingkasanLaporanPolisActionBean.class, "locate");
    }

    public Resolution simpan() {
        kodNegeri = conf.getProperty("kodNegeri");
        String result = null;
        noRujukan = getContext().getRequest().getParameter("permohonanRujukanLuar.noRujukan");
        catatan = getContext().getRequest().getParameter("permohonanRujukanLuar.catatan");
        lokasiAgensi = getContext().getRequest().getParameter("permohonanRujukanLuar.lokasiAgensi");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();


        System.out.println("permohonan :" + permohonan.getIdPermohonan());

//        if (permohonanRujukanLuar.getIdRujukan() == 0) {
        if (permohonanRujukanLuar == null) {
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuar = new PermohonanRujukanLuar();
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        permohonanRujukanLuar.setPermohonan(permohonan);
        // permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenRujukan);
        permohonanRujukanLuar.setCatatan(catatan);
        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
        permohonanRujukanLuar.setLokasiAgensi(lokasiAgensi);
        permohonanRujukanLuar.setNoRujukan(noRujukan);

        KodRujukan kr = new KodRujukan();
        kr.setKod("NF");
        permohonanRujukanLuar.setKodRujukan(kr);

        if (kodNegeri.equals("04")) {
            KodAgensi ka = new KodAgensi();
            ka.setKod("0302");
            permohonanRujukanLuar.setAgensi(ka);
        } else if (kodNegeri.equals("05")) {
//            KodAgensi ka = agensiDAO.findById(getContext().getRequest().getParameter("permohonanRujukanLuar.agensi.kod"));
//            ka.setKod(getContext().getRequest().getParameter("agensi.kod"));
            permohonanRujukanLuar.setAgensi(agensiDAO.findById(kodAgensiDiPilih));
            logger.debug("kod agensi ::: " + kodAgensiDiPilih );
//            permohonanRujukanLuar.setAgensi(agensi);
        }

        permohonanRujukanLuar.setInfoAudit(ia);
        tarikhRujukan = tarikhRujukan + " " + jam + ":" + minit + " " + ampm;
        logger.debug("tarikhRujukan :" + tarikhRujukan);
        //  tx.commit();



        try {
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        } catch (Exception ex) {
            logger.error(ex);
        }

//        permohonanRujukanLuar.setTempat(tempat);

        enforceService.simpanRujukan(permohonanRujukanLuar);
        tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/ringkasan_laporan_polis.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            permohonanRujukanLuar.setDokumen(null);
            InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            enforceService.simpanRujukan(permohonanRujukanLuar);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(RingkasanLaporanPolisActionBean.class, "locate");
    }
//        enforceService.simpanPermohonan(permohonan);
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//         return new JSP("penguatkuasaan/ringkasan_laporan_polis.jsp").addParameter("tab", "true");
//    }
}

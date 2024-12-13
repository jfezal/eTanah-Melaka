/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Siti Fariza
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/utiliti_ringkasan_kes")
public class UtilitiRingkasanKesActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiRingkasanKesActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    EnforceService enforceService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    LelongService lelongService;
    private Permohonan permohonan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String idPermohonan;
    private long idLaporUlas;
    private String ulasan;
    private KodCawangan cawangan;
    private String kodDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private Long idDokumen;
    private Pengguna peng;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    class RingkasanKesCache implements Serializable {

        Permohonan permohonan;
        PermohonanLaporanUlasan permohonanLaporanUlasan;
        Long idDokumen;
    }

    @DefaultHandler
    public Resolution findRksnKes() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/utiliti_ringkasan_kes.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        getUrusanfromSession();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanLaporanUlasan = enforceService.findRingkasanKesByJenisUlasan(idPermohonan, "rgksn_kes");

                if (permohonanLaporanUlasan == null) {
                    LOG.info("ringkasan kes null");
                    //1) find mohon ulasan for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                    if (permohonan.getPermohonanSebelum() != null) {
                        System.out.println("permohonan sebelum : " + permohonan.getPermohonanSebelum().getIdPermohonan());
                        permohonanLaporanUlasan = enforceService.findRingkasanKesByJenisUlasan(permohonan.getPermohonanSebelum().getIdPermohonan(), "rgksn_kes");
                    }

                }

                if (permohonanLaporanUlasan != null) {
                    LOG.info("ringkasan kes not null");
                    idLaporUlas = permohonanLaporanUlasan.getIdLaporUlas();
                    ulasan = permohonanLaporanUlasan.getUlasan();
                }
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
        System.out.println("idPermohonan rehydrate-------------3 : " + idPermohonan);
    }

    public Resolution simpan() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        System.out.println("---idPermohonan----: " + idPermohonan);
        System.out.println("---ulasan----: " + ulasan);

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanLaporanUlasan = enforceService.findRingkasanKesByJenisUlasan(idPermohonan, "rgksn_kes");

            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

            if (permohonanLaporanUlasan == null) {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = permohonanLaporanUlasan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }

            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(ia);
            permohonanLaporanUlasan.setCawangan(peng.getKodCawangan());
            permohonanLaporanUlasan.setJenisUlasan("rgksn_kes");
            KodDokumen kd = new KodDokumen();
            kd.setKod("RKP");
            permohonanLaporanUlasan.setKodDokumen(kd);
            if (ulasan == null || ulasan.isEmpty()) {
                addSimpleError("Sila masukkan ulasan.");
            } else {
                permohonanLaporanUlasan.setUlasan(ulasan);
            }
            enforceService.simpanRingkasanKes(permohonanLaporanUlasan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            System.out.println("idPermohonan telah di simpan : " + idPermohonan);
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/utiliti_ringkasan_kes.jsp");


    }

    public Resolution genReport() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code = "";
        gen = "ENFRK_NS.rdf";
        code = "RKP";
        permohonan = permohonanDAO.findById(idPermohonan);
        try {
            lelongService.reportGen(permohonan, gen, code, peng);
            addSimpleMessage("Dokumen telah berjaya dijana.");
        } catch (Exception ex) {
            addSimpleError("Dokumen Tidak Dapat Dijana.");
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }

        //get dokument to view report        

        if (permohonan != null) {
            if (!permohonan.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                for (KandunganFolder kf : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                    if (kf.getDokumen().getKodDokumen() != null) {
                        if (kf.getDokumen().getKodDokumen().getKod().equals("RKP")) {
                            senaraiKandungan.add(kf);
                            System.out.println("---Id Doc to view current report----: " + senaraiKandungan.get(0).getDokumen().getIdDokumen());
                        }
                    }
                }
            }
        }
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/utiliti_ringkasan_kes.jsp");
        
    }

     public Resolution searchNoSerahan() {
//        logger.info("------------searchNoSerahan-------------");
        System.out.println("idPermohonan rehydrate------------1 : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanLaporanUlasan = enforceService.findRingkasanKesByJenisUlasan(idPermohonan, "rgksn_kes");
            if (permohonanLaporanUlasan != null) {
                LOG.info("ringkasan kes not null");
                idPermohonan = permohonanLaporanUlasan.getPermohonan().getIdPermohonan();
                idLaporUlas = permohonanLaporanUlasan.getIdLaporUlas();
                ulasan = permohonanLaporanUlasan.getUlasan();
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan" + idPermohonan + " tidak dijumpai.");
            }
        } else {
            idPermohonanNotExist = true;
            addSimpleError("Permohonan" + idPermohonan + " tidak dijumpai.");
        }
        System.out.println("idPermohonan rehydrate-----------2 : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/utiliti_ringkasan_kes.jsp");
    }

//    public final void getUrusanfromSession() {
//        LOG.debug("get id permohonan from session");
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        try {
//            RingkasanKesCache u = (RingkasanKesCache) ctx.getWorkData();
//            if (u != null) {
//                permohonan = u.permohonan;
//                permohonanLaporanUlasan = u.permohonanLaporanUlasan;
//                idDokumen = u.idDokumen;
//                if (permohonan != null) {
//                    idPermohonan = permohonan.getIdPermohonan();
//                    LOG.info("idPermohonan getUrusanfromSession : " + idPermohonan);
//                    LOG.info("idDokumen getUrusanfromSession : " + idDokumen);
//                }
//            } else {
//                LOG.debug("no data in session");
//            }
//        } catch (Exception ex) {
//            LOG.error(ex);
//            ctx.removeWorkdata();
//        }
//
//    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdLaporUlas() {
        return idLaporUlas;
    }

    public void setIdLaporUlas(long idLaporUlas) {
        this.idLaporUlas = idLaporUlas;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import java.math.BigDecimal;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;

import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTuntut;
import etanah.model.Kompaun;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_pengurangan_kompaun")
public class UtilitiKompaunActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiKompaunActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
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
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    DokumenDAO dokumenDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private Long idDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> senaraiKompaunUpdate = new ArrayList<Kompaun>();
    private String amaunBaru;
    private Kompaun kompaun;
    private String idOks;
    private AduanOrangKenaSyak oks;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    private String tempohHari;
    private boolean newAmaunUpdated = Boolean.FALSE;
    private String idKompaun;

    class KompaunCache implements Serializable {

        Permohonan permohonan;
        Kompaun kompaun;
    }

    @DefaultHandler
    public Resolution findKompaun() {
        logger.info("------------findKompaun-------------");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kompaun.jsp");
    }

    public Resolution cariSemula() {
        logger.info("------------cariSemula-------------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        idPermohonan = "";
        senaraiKompaun.clear();
        senaraiKompaunUpdate.clear();

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kompaun.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKompaun = new ArrayList<Kompaun>();
        getUrusanfromSession();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaun(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun masa rehydrate: " + senaraiKompaun.size());

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
        System.out.println("idPermohonan1 rehydrate : " + idPermohonan);
    }

    public Resolution searchKompaun() {
        logger.info("------------searchKompaun-------------");
        System.out.println("idPermohonan : " + idPermohonan);
        //clear data in session before search new data
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaun(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun : " + senaraiKompaun.size());

                saveToSession(ctx);

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
                senaraiKompaun.clear();
                senaraiKompaunUpdate.clear();
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kompaun.jsp");
    }

    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            KompaunCache u = (KompaunCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                kompaun = u.kompaun;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();
                    System.out.println("idPermohonan getUrusanfromSession : " + idPermohonan);
                }
            } else {
                logger.debug("no data in session");
            }
        } catch (Exception ex) {
            logger.error(ex);
            ctx.removeWorkdata();
        }

    }

    public final void saveToSession(etanahActionBeanContext ctx) {
        logger.debug("saveToSession");
        KompaunCache u = (KompaunCache) ctx.getWorkData();
        if (u == null) {
            u = new KompaunCache();
        }
        u.permohonan = permohonan;
        System.out.println("saveToSession u.permohonan : " + u.permohonan.getIdPermohonan());

        ctx.setWorkData(u);
    }

    public Resolution simpanMuktamadKompaun() {
        logger.info("------------simpanMuktamadKompaun--------------");

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        try {
            logger.info("size senaraiKompaun masa simpan: " + senaraiKompaun.size());
            for (int i = 0; i < senaraiKompaun.size(); i++) {

                amaunBaru = getContext().getRequest().getParameter("amaunBaru" + i);
                System.out.println("amaunBaru ============ : " + amaunBaru);
                idOks = getContext().getRequest().getParameter("idOks" + i);
                tempohHari = getContext().getRequest().getParameter("tempohHari" + i);
                idKompaun = getContext().getRequest().getParameter("idKompaun" + i);


                if (StringUtils.isNotBlank(amaunBaru)) {
                    System.out.println("amaunBaru" + amaunBaru);
                    BigDecimal amaunMuktamad = new BigDecimal(amaunBaru);

                    Kompaun k = kompaunDAO.findById(Long.parseLong(idKompaun));
                    if (k != null) {
                        //update kod_status_terima
                        InfoAudit ia = new InfoAudit();

                        ia = k.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        k.setStatusTerima(kodStatusTerimaDAO.findById("BT"));
                        k.setInfoAudit(ia);
                        enforcementService.simpanKompaun(k);
                    }

                    oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));

                    /* save table PermohonanTuntutKos*/
                    ptk = new PermohonanTuntutanKos();
                    ptk.setCawangan(pengguna.getKodCawangan());
                    ptk.setPermohonan(permohonan);
                    ptk.setItem("BAYARAN KOMPAUN");
                    ptk.setAmaunTuntutan(BigDecimal.ZERO);
                    KodTransaksi kt = new KodTransaksi();
                    kt.setKod("20027");
                    ptk.setKodTransaksi(kt);
                    KodTuntut ktu = new KodTuntut();
                    ktu.setKod("K01");
                    ptk.setKodTuntut(ktu);
                    ptk.setInfoAudit(info);
                    ptk.setAmaunTuntutan(amaunMuktamad);
                    enforceService.simpanBayaran(ptk);

                    /* save table Kompaun*/
                    kompaun = new Kompaun();
                    kompaun.setInfoAudit(info);
                    kompaun.setPermohonan(permohonan);
                    kompaun.setCawangan(pengguna.getKodCawangan());
                    kompaun.setAmaun(amaunMuktamad);
                    kompaun.setNoRujukan("KOMPAUN OKS");
                    kompaun.setOrangKenaSyak(oks);
                    kompaun.setIsuKepada(oks.getNama());
                    kompaun.setNoPengenalan(oks.getNoPengenalan());
                    kompaun.setTempohHari(Integer.parseInt(tempohHari));
                    kompaun.setPermohonanTuntutanKos(ptk);

                    enforcementService.simpanKompaun(kompaun);

                    //update column no rujukan by set no rujukan = id kompaun

                    kompaun.setNoRujukan(String.valueOf(kompaun.getIdKompaun()));
                    kompaunDAO.saveOrUpdate(kompaun);

                    Date c1 = kompaun.getInfoAudit().getTarikhMasuk();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(c1);
                    cal.add(Calendar.DATE, kompaun.getTempohHari());

                    String tarikhAkhirBayar = sdf.format(cal.getTime());
                    System.out.println("tarikh akhir bayar : " + tarikhAkhirBayar);

                    KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
                    kodTransTuntut = enforceService.findKodTransTuntutByKod("ENFKOMPN");

                    /* save table PermohonanTuntutan*/
                    pt = new PermohonanTuntutan();
                    pt.setCawangan(pengguna.getKodCawangan());
                    pt.setPermohonan(permohonan);
                    pt.setInfoAudit(info);
                    pt.setKodTransaksiTuntut(kodTransTuntut);
                    pt.setTarikhTuntutan(new java.util.Date());
                    pt.setTempoh(Integer.parseInt(tempohHari));
                    pt.setTarikhAkhirBayaran(sdf.parse(tarikhAkhirBayar));
                    enforceService.simpanPermohonanTuntutan(pt);

                    /* save table PermohonanTuntutanButiran*/
                    ptb = new PermohonanTuntutanButiran();
                    ptb.setPermohonanTuntutan(pt);
                    ptb.setPermohonanTuntutanKos(ptk);
                    ptb.setInfoAudit(info);
                    enforceService.simpanPermohonanTuntutanButiran(ptb);

                    // generate report

//                    String[] params = new String[]{"p_id_mohon"};
//                    String[] values = new String[]{permohonan.getIdPermohonan()};
//                    String path = "";
//                    String dokumenPath = conf.getProperty("document.path");
//                    Dokumen d = null;
//                    KodDokumen kd = null;
//
//                    FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
//                    String reportName = "";
//
//                    kd = kodDokumenDAO.findById("9");
//                    if ("04".equals(conf.getProperty("kodNegeri"))) {
//                        reportName = "ENFB9_MLK.rdf";
//                    } else {
//                        reportName = "ENFB9_NS.rdf";
//                    }
//                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                    idDokumen = d.getIdDokumen();
//                    logger.info("-------id dokumen : --------" + idDokumen);

                    newAmaunUpdated = true;

                    if (kompaun != null) {
                        Kompaun tempKompaun = new Kompaun();
                        tempKompaun = enforcementService.findNewKompaun(kompaun.getIdKompaun());

                        System.out.println("id kompaun : [" + i + "]" + kompaun.getIdKompaun());
                        System.out.println("tempkompaun : " + tempKompaun);

//                        if (tempKompaun != null) {
//                            System.out.println("temp kompuan not null");
//                            tempKompaun.setIdKompaun(tempKompaun.getIdKompaun());
//                            tempKompaun.setNoRujukan(tempKompaun.getNoRujukan());
//                            tempKompaun.setIsuKepada(tempKompaun.getIsuKepada());
//                            tempKompaun.setNoPengenalan(tempKompaun.getNoPengenalan());
//                            tempKompaun.setAmaun(tempKompaun.getAmaun());
//                        }
                        senaraiKompaunUpdate.add(tempKompaun);
                    }

                    System.out.println("size senaraiKompaunUpdate :" + senaraiKompaunUpdate.size());


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kompaun.jsp");
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            logger.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        logger.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Resolution deleteSelected() {
        logger.info("------------deleteSelected--------------");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

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
        return new RedirectResolution(UtilitiKompaunActionBean.class, "locate");
    }

    public Resolution refreshpage() {
        logger.info("------------refreshpage--------------");
        rehydrate();
        return new RedirectResolution(UtilitiKompaunActionBean.class, "locate");
    }

    public Resolution reload() {
        logger.info("------------reload-------------");
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("reload id mohon : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaun(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun masa reload: " + senaraiKompaun.size());
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kompaun.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public String getAmaunBaru() {
        return amaunBaru;
    }

    public void setAmaunBaru(String amaunBaru) {
        this.amaunBaru = amaunBaru;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public PermohonanTuntutan getPt() {
        return pt;
    }

    public void setPt(PermohonanTuntutan pt) {
        this.pt = pt;
    }

    public PermohonanTuntutanButiran getPtb() {
        return ptb;
    }

    public void setPtb(PermohonanTuntutanButiran ptb) {
        this.ptb = ptb;
    }

    public PermohonanTuntutanKos getPtk() {
        return ptk;
    }

    public void setPtk(PermohonanTuntutanKos ptk) {
        this.ptk = ptk;
    }

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }

    public boolean isNewAmaunUpdated() {
        return newAmaunUpdated;
    }

    public void setNewAmaunUpdated(boolean newAmaunUpdated) {
        this.newAmaunUpdated = newAmaunUpdated;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public List<Kompaun> getSenaraiKompaunUpdate() {
        return senaraiKompaunUpdate;
    }

    public void setSenaraiKompaunUpdate(List<Kompaun> senaraiKompaunUpdate) {
        this.senaraiKompaunUpdate = senaraiKompaunUpdate;
    }
}




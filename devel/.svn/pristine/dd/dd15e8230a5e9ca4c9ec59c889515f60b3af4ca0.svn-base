/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodJabatan;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.PenghantarNotis;
import etanah.service.common.LelongService;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import java.io.Serializable;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.DokumenDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Notis;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;

import java.io.File;

/**
 *
 * @author ctzainal
 */
@UrlBinding("/penguatkuasaan/utiliti_notis_penyampaian")
public class UtilitiNotisPenyampaianActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiNotisPenyampaianActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
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
    NotisDAO notisDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    private static final Logger LOG = Logger.getLogger(UtilitiNotisPenyampaianActionBean.class);
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private List<Notis> listNotis;
    private String tajuk;
    private String recordCount;
    private Notis notis;
    private String kandungan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String idKandungan;
    private Long idDokumen;
    private String statusPenyampaian;
    private String caraPenghantaran;
    private String penghantarNotis;
    private String jenisNotis;
    private Dokumen dokumen;
    private String tarikhHantar;
    private String tarikhTerima;
    private String catatan;
    private String namaTampal;
    private String tempatTampal;
    private String tarikhTampal;
    private String idNotis;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    FileBean fileToBeUploaded;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private String penerimaNotis;

    class UrusanCache implements Serializable {

        Notis notis;
        String idPermohonan;
    }

    @DefaultHandler
    public Resolution findNotisPenyampaian() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("findNotisPenyampaian id mohon : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
    }

    public Resolution notisPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        return new JSP("penguatkuasaan/utiliti_popup_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupUpload() {
        LOG.info("idNotis :" + idNotis);
        return new JSP("penguatkuasaan/utiliti_notis_penyampaian_upload_doc.jsp").addParameter("popup", "true");
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
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                if (listNotis.size() != 0) {
                    notis = listNotis.get(0);
                    recordCount = String.valueOf(listNotis.size());
                }


            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        getUrusanfromSession();
        System.out.println("rehydrate id mohon : " + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);


            if (permohonan != null) {
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                logger.info("------------listNotis------------- :" + listNotis.size());
                if (listNotis.size() != 0) {
                    notis = listNotis.get(0);
                    recordCount = String.valueOf(listNotis.size());
                }


            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
    }

    public Resolution searchNotisPenyampaian() {
        logger.info("------------searchNotisPenyampaian-------------");
        System.out.println("idPermohonan : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);


            if (permohonan != null) {
                listNotis = new ArrayList<Notis>();
                listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
                if (listNotis.size() != 0) {
                    notis = listNotis.get(0);
                    recordCount = String.valueOf(listNotis.size());
                }


            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");


    }

    public Resolution simpan() {
        System.out.println("masuk sini");
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        System.out.println("id permohonan yang dimasukkan:" + idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        KodStatusTerima kodTerima = new KodStatusTerima();
        kodTerima = kodStatusTerimaDAO.findById(statusPenyampaian);

        KodCaraPenghantaran caraHantar = new KodCaraPenghantaran();
        caraHantar = kodCaraPenghantaranDAO.findById(caraPenghantaran);

        PenghantarNotis hantarNotis = new PenghantarNotis();
        hantarNotis = penghantarNotisDAO.findById(Integer.parseInt(penghantarNotis));

        KodNotis kod = new KodNotis();
        kod = kodNotisDAO.findById(jenisNotis);

        KodKlasifikasi kodKlasifikasi = new KodKlasifikasi();
        kodKlasifikasi.setKod(1);

        dokumen = new Dokumen();
        KodDokumen dokNotis = new KodDokumen();

        dokNotis.setKod(kod.getKod());
        //dokumen.setKodDokumen(dokNotis);
        dokumen.setTajuk(kod.getNama());
        dokumen.setKlasifikasi(kodKlasifikasi);

        dokumen.setNoVersi("1.0");
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new java.util.Date());
        iaPermohonan.setDimasukOleh(pengguna);
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);


        System.out.println("masuk sini3");
        notis = new Notis();
        notis.setPermohonan(permohonan);
        notis.setDokumenNotis(dokumen);
        notis.setInfoAudit(ia);
        notis.setJabatan(permohonan.getKodUrusan().getJabatan());
        notis.setKodStatusTerima(kodTerima);
        notis.setKodCaraPenghantaran(caraHantar);
        notis.setPenghantarNotis(hantarNotis);
        notis.setKodNotis(kod);
        notis.setCatatanPenerimaan(catatan);
        notis.setPenerimaNotis(penerimaNotis);
        notis.setNamaTampal(namaTampal);
        notis.setTempatTampal(tempatTampal);
        notis.setTarikhTampal(new java.util.Date());
        notis.setTarikhNotis(new java.util.Date());
        if (tarikhTampal != null) {
            try {
                notis.setTarikhTampal(dateF.parse(tarikhTampal));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhHantar != null) {
            try {
                notis.setTarikhHantar(dateF.parse(tarikhHantar));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhTerima != null) {
            try {
                notis.setTarikhTerima(dateF.parse(tarikhTerima));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        enforcementService.simpanNotisPenguatkuasaan(notis);

//        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
        return findNotisPenyampaian();
    }

    public Resolution deleteSingle() {
        idNotis = getContext().getRequest().getParameter("idNotis");
        notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
        dokumen = enforcementService.findDokumenByIdDokumen(notis.getDokumenNotis().getIdDokumen());
        System.out.println("idNotis ialah--------" + idNotis);
        if (notis != null) {
            enforcementService.deleteNotis(notis);
        }
        if (dokumen != null) {
            enforcementService.deleteDokumen(dokumen);
        }

        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
    }

    public Resolution editNotisPopup() {
        idNotis = getContext().getRequest().getParameter("idNotis");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idNotis != null) {
            LOG.info("idNotis :" + idNotis);
            notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
            if (notis != null) {
                jenisNotis = notis.getKodNotis().getKod();
                penghantarNotis = Integer.toString(notis.getPenghantarNotis().getIdPenghantarNotis());
                statusPenyampaian = notis.getKodStatusTerima().getKod();
                caraPenghantaran = notis.getKodCaraPenghantaran().getKod();
                if (notis.getTarikhHantar() != null) {
                    tarikhHantar = dateF.format(notis.getTarikhHantar());
                }

                if (notis.getTarikhTerima() != null) {
                    tarikhTerima = dateF.format(notis.getTarikhTerima());
                }

                if (notis.getTarikhTampal() != null) {
                    tarikhTampal = dateF.format(notis.getTarikhTampal());
                }
                
                tempatTampal = notis.getTempatTampal();
                namaTampal = notis.getNamaTampal();
                catatan = notis.getCatatanPenerimaan();
                penerimaNotis = notis.getPenerimaNotis();
            }
        }

        return new JSP("penguatkuasaan/utiliti_popup_edit_notis.jsp").addParameter("popup", "true");
    }

    public Resolution editNotis() throws ParseException {
        logger.info("------------edit Notis 2-------------");
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("id permohonan------------" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("id mohon yaw----------" + permohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        jenisNotis = getContext().getRequest().getParameter("jenisNotis");
        penghantarNotis = getContext().getRequest().getParameter("penghantarNotis");
        statusPenyampaian = getContext().getRequest().getParameter("statusPenyampaian");
        caraPenghantaran = getContext().getRequest().getParameter("caraPenghantaran");

        KodStatusTerima kodTerima = new KodStatusTerima();
        kodTerima = kodStatusTerimaDAO.findById(statusPenyampaian);

        KodCaraPenghantaran caraHantar = new KodCaraPenghantaran();
        caraHantar = kodCaraPenghantaranDAO.findById(caraPenghantaran);

        PenghantarNotis hantarNotis = new PenghantarNotis();
        hantarNotis = penghantarNotisDAO.findById(Integer.parseInt(penghantarNotis));

        KodKlasifikasi kodKlasifikasi = new KodKlasifikasi();
        kodKlasifikasi.setKod(1);

        KodNotis kod = new KodNotis();
        kod = kodNotisDAO.findById(jenisNotis);

        notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
//        notis = notisDAO.findById(Long.parseLong(idNotis));

        dokumen = new Dokumen();
        dokumen.setIdDokumen(notis.getDokumenNotis().getIdDokumen());
        notis.setPermohonan(permohonan);
        notis.setDokumenNotis(dokumen);
        notis.setKodNotis(kod);
        notis.setKodStatusTerima(kodTerima);
        notis.setJabatan(permohonan.getKodUrusan().getJabatan());
        notis.setKodStatusTerima(kodTerima);
        notis.setKodCaraPenghantaran(caraHantar);
        notis.setPenghantarNotis(hantarNotis);
        notis.setCatatanPenerimaan(catatan);
        notis.setNamaTampal(namaTampal);
        notis.setTempatTampal(tempatTampal);
        notis.setPenerimaNotis(penerimaNotis);
        notis.setTarikhTampal(new java.util.Date());
        notis.setTarikhNotis(new java.util.Date());
        if (tarikhTampal != null) {
            try {
                notis.setTarikhTampal(dateF.parse(tarikhTampal));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhHantar != null) {
            try {
                notis.setTarikhHantar(dateF.parse(tarikhHantar));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }
        if (tarikhTerima != null) {
            try {
                notis.setTarikhTerima(dateF.parse(tarikhTerima));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        }

        InfoAudit ia = notis.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanNotisPenguatkuasaan(notis);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return findNotisPenyampaian();
    }

    public Resolution processUploadDoc() {
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        LOG.info("idNotis----------------- : " + fname);
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("processUploadDoc : idPermohonan : " + idPermohonan);
        DateUtil du = new DateUtil();

        try {
            System.out.println("checking");
            if (pengguna != null && fname != null && fileToBeUploaded != null) {
                System.out.println("doing checking");
                String kodCawangan = pengguna.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Bukti Penyampaian Untuk -" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = notis.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                notis.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                notis.setInfoAudit(ia);
                lelongService.updateNotis(notis);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);

        return new JSP("penguatkuasaan/utiliti_notis_penyampaian_upload_doc.jsp").addParameter("popup", "true");
//        return findNotisPenyampaian();
    }

    public Resolution popupScan() {

        LOG.info("idNotis ialah:" + idNotis);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        //etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        //Pengguna p = ctx.getUser();
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // try {
        if (p != null && fname != null) {
            Dokumen doc = new Dokumen();
            Notis dtn = notisDAO.findById(Long.parseLong(fname));
            if (dtn.getBuktiPenerimaan() != null) {
                doc = dtn.getBuktiPenerimaan();
                ia = dtn.getBuktiPenerimaan().getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(new java.util.Date());
            }
            doc.setKodDokumen(kd);
            doc.setTajuk("Salinan Penghantaran-" + fname);
            doc.setNoVersi("1.0");
            doc.setKlasifikasi(kk);
            doc.setFormat("application/pdf/image/gif");
            doc.setInfoAudit(ia);
            idDokumen = lelongService.simpanOrUpdateDokumen(doc);
            LOG.info("idDoc :" + idDokumen);
            // update at DasarTuntutanNotis
            ia = dtn.getInfoAudit();
            ia.setDiKemaskiniOleh(p);
            ia.setTarikhKemaskini(new java.util.Date());
            dtn.setBuktiPenerimaan(dokumenDAO.findById(idDokumen));
            dtn.setInfoAudit(ia);
            System.out.println("sblm update notis");
            KodJabatan kodJabatan = new KodJabatan();
            kodJabatan.setKod("8");
            dtn.setJabatan(kodJabatan);
            lelongService.updateNotis(dtn);
            System.out.println("slps update notis");
        } else {
            LOG.error("parameter tidak mencukupi.");
        }

        return new JSP("penguatkuasaan/notis_siasatan_scan_doc.jsp").addParameter("popup", "true");
    }

    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            UrusanCache u = (UrusanCache) ctx.getWorkData();
            if (u != null) {
                idPermohonan = u.idPermohonan;
                System.out.println("id mohon u.idPermohonan : " + idPermohonan);
                notis = u.notis;
                if (notis != null) {
                    idPermohonan = notis.getPermohonan().getIdPermohonan();
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
        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
        u.idPermohonan = idPermohonan;
        System.out.println("saveToSession :  u.idPermohonan = idPermohonan : " + u.idPermohonan);
        u.notis = notis;
        System.out.println("saveToSession u.permohonan : " + u.notis.getPermohonan().getIdPermohonan());

        ctx.setWorkData(u);
    }

    public Resolution refreshpage() {
        logger.debug("------------refreshpage--------------");
        rehydrate();
        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
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

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getCaraPenghantaran() {
        return caraPenghantaran;
    }

    public void setCaraPenghantaran(String caraPenghantaran) {
        this.caraPenghantaran = caraPenghantaran;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getJenisNotis() {
        return jenisNotis;
    }

    public void setJenisNotis(String jenisNotis) {
        this.jenisNotis = jenisNotis;
    }

    public String getNamaTampal() {
        return namaTampal;
    }

    public void setNamaTampal(String namaTampal) {
        this.namaTampal = namaTampal;
    }

    public String getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(String penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public String getStatusPenyampaian() {
        return statusPenyampaian;
    }

    public void setStatusPenyampaian(String statusPenyampaian) {
        this.statusPenyampaian = statusPenyampaian;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTempatTampal() {
        return tempatTampal;
    }

    public void setTempatTampal(String tempatTampal) {
        this.tempatTampal = tempatTampal;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public String getPenerimaNotis() {
        return penerimaNotis;
    }

    public void setPenerimaNotis(String penerimaNotis) {
        this.penerimaNotis = penerimaNotis;
    }
}

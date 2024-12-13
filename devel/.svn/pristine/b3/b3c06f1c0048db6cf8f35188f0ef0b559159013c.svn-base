/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodDokumen;
import etanah.model.KodJabatan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/notis_bukti_penyampaian")
public class NotisBuktiPenyampaianActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    NotisDAO notisDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private static final Logger LOG = Logger.getLogger(NotisBuktiPenyampaianActionBean.class);
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String idPermohonan;
    private String idNotis;
    private Notis notis;
    private List<Notis> listNotis;
    private String jenisNotis;
    private String penghantarNotis;
    private String statusPenyampaian;
    private String caraPenghantaran;
    private String tarikhHantar;
    private String tarikhTerima;
    private String catatan;
    private String namaTampal;
    private String tempatTampal;
    private String tarikhTampal;
    private String penerimaNotis;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private Dokumen dokumen;
    FileBean fileToBeUploaded;
    private long idDokumen;
    private String noPengenalan;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/notis_bukti_penyampaian.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/notis_bukti_penyampaian_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            listNotis = enforcementService.findNotisByIDPermohonan(idPermohonan);
        }
        kodNegeri = conf.getProperty("kodNegeri");

    }

    public Resolution simpan() {
        LOG.info("---------simpan-----------");
        System.out.println("masuk sini");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("id permohonan:" + idPermohonan);

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
        dokumen.setTajuk(kod.getNama());
        dokumen.setKlasifikasi(kodKlasifikasi);

        dokumen.setNoVersi("1.0");
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new java.util.Date());
        iaPermohonan.setDimasukOleh(pengguna);
        dokumen.setInfoAudit(iaPermohonan);
        dokumenDAO.saveOrUpdate(dokumen);

        LOG.info("---------penerimaNotis -----------:" + penerimaNotis);
        
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
        notis.setNamaTampal(namaTampal);
        notis.setTempatTampal(tempatTampal);
        notis.setPenerimaNotis(penerimaNotis);
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

        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new RedirectResolution(NotisBuktiPenyampaianActionBean.class, "locate");
    }

    public Resolution notisPopup() {
        return new JSP("penguatkuasaan/popup_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupUpload() {
        LOG.info("idNotis :" + idNotis);
        return new JSP("penguatkuasaan/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("BPN"); // FIXME : BPN = Bukti Penyampaian Notis
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idNotis;
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
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
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);

                KandunganFolder kf = kandunganFolderService.findByDokumen(doc, permohonan.getFolderDokumen());
                if (kf == null) {
                    kf = new KandunganFolder();
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    ia = kf.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                }

                kf.setFolder(permohonan.getFolderDokumen());
                kf.setDokumen(doc);
                kf.setInfoAudit(ia);
                kandunganFolderService.saveOrUpdate(kf);

                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                dtn.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                dtn.setInfoAudit(ia);
                lelongService.updateNotis(dtn);
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

        return new JSP("penguatkuasaan/notis_siasatan_upload_doc.jsp").addParameter("popup", "true");
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
//  } catch (Exception ex) {
//  LOG.error("Fatal protocol violation: " + ex.getMessage());
//  ex.printStackTrace(); // comment this for production
//  }
        return new JSP("penguatkuasaan/notis_siasatan_scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new RedirectResolution(NotisBuktiPenyampaianActionBean.class, "locate");
    }

    public Resolution viewNotisDetail() {
        LOG.debug("viewNotisDetail");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idNotis != null) {
            LOG.info("idNotis :" + idNotis);
            notis = notisDAO.findById(Long.parseLong(idNotis));
            if (notis != null) {
                jenisNotis = notis.getKodNotis().getNama();
                penghantarNotis = notis.getPenghantarNotis().getNama();
                statusPenyampaian = notis.getKodStatusTerima().getNama();
                caraPenghantaran = notis.getKodCaraPenghantaran().getNama();
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
                penerimaNotis = notis.getPenerimaNotis();
                catatan = notis.getCatatanPenerimaan();
                if (notis.getPenghantarNotis() != null) {
                    noPengenalan = notis.getPenghantarNotis().getNoPengenalan();
                }
            }
        }
        return new JSP("penguatkuasaan/view_notis_detail.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        notis = enforcementService.findnotisByIdNotis(Long.parseLong(idNotis));
        dokumen = enforcementService.findDokumenByIdDokumen(notis.getDokumenNotis().getIdDokumen());

        if (notis != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            notis.setInfoAudit(ia);
            enforcementService.deleteNotis(notis);
        }
        if (dokumen != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            dokumen.setInfoAudit(ia);
            enforcementService.deleteDokumen(dokumen);
        }

        return new RedirectResolution(NotisBuktiPenyampaianActionBean.class, "locate");
    }

    public Resolution editNotisPopup() {
        LOG.info("---------editNotisPopup-----------");
        idNotis = getContext().getRequest().getParameter("idNotis");
        if (idNotis != null) {
            LOG.info("idNotis :" + idNotis);
            notis = notisDAO.findById(Long.parseLong(idNotis));
            if (notis != null) {
                jenisNotis = notis.getKodNotis().getNama();
                penghantarNotis = notis.getPenghantarNotis().getNama();
                statusPenyampaian = notis.getKodStatusTerima().getNama();
                caraPenghantaran = notis.getKodCaraPenghantaran().getNama();
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
                penerimaNotis = notis.getPenerimaNotis();
                LOG.info("---------editNotisPopup----------- penerimaNotis : " + penerimaNotis);
                catatan = notis.getCatatanPenerimaan();
                noPengenalan = notis.getPenghantarNotis().getNoPengenalan();
            }
        }

        return new JSP("penguatkuasaan/popup_edit_notis.jsp").addParameter("popup", "true");
    }

    public Resolution editNotis() throws ParseException {
        LOG.info("---------editNotis-----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idNotis = getContext().getRequest().getParameter("idNotis");
        jenisNotis = getContext().getRequest().getParameter("kodNotis.kod");
        penghantarNotis = getContext().getRequest().getParameter("penghantarNotis.idPenghantarNotis");
        statusPenyampaian = getContext().getRequest().getParameter("kodStatusTerima.kod");
        caraPenghantaran = getContext().getRequest().getParameter("kodCaraPenghantaran.kod");

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

        notis = notisDAO.findById(Long.parseLong(idNotis));

        LOG.info("---------penerimaNotis edit-----------:" + penerimaNotis);

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
        notis.setPenerimaNotis(penerimaNotis);
        notis.setTempatTampal(tempatTampal);
        notis.setTarikhNotis(new java.util.Date());
        if (tarikhTampal != null) {
            try {
                notis.setTarikhTampal(dateF.parse(tarikhTampal));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        } else {
            notis.setTarikhTampal(null);
        }
        if (tarikhHantar != null) {
            try {
                notis.setTarikhHantar(dateF.parse(tarikhHantar));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        } else {
            notis.setTarikhHantar(null);
        }
        if (tarikhTerima != null) {
            try {
                notis.setTarikhTerima(dateF.parse(tarikhTerima));
            } catch (ParseException ex) {
                System.out.println("Got error" + ex);
            }
        } else {
            notis.setTarikhTerima(null);
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
        return new RedirectResolution(NotisBuktiPenyampaianActionBean.class, "locate");
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNamaTampal() {
        return namaTampal;
    }

    public void setNamaTampal(String namaTampal) {
        this.namaTampal = namaTampal;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public String getTempatTampal() {
        return tempatTampal;
    }

    public void setTempatTampal(String tempatTampal) {
        this.tempatTampal = tempatTampal;
    }

    public String getPenerimaNotis() {
        return penerimaNotis;
    }

    public void setPenerimaNotis(String penerimaNotis) {
        this.penerimaNotis = penerimaNotis;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

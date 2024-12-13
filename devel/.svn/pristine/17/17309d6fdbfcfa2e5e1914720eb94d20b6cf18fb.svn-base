/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_diari_siasatan_IO")
public class UtilitiDiariSiasatanIOActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiDiariSiasatanIOActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
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
    private Permohonan permohonan;
    private String idPermohonan;
    private List<PermohonanKertasKandungan> listButiranDiariSiasatan;
    private Pengguna pengguna;
    private PermohonanKertas diariSiasatan;
    private String tajuk;
    private String recordCount;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private String jam;
    private String minit;
    private String ampm;
    private String kandungan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String idKandungan;
    private Long idDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private boolean authorizedUser = Boolean.FALSE;

    class DiariSiasatanCache implements Serializable {

        Permohonan permohonan;
        PermohonanKertas diariSiasatan;
        Long idDokumen;
    }

    @DefaultHandler
    public Resolution findDiariSiasatan() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan_IO.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        getUrusanfromSession();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                diariSiasatan = enforceService.findByIdKrts(idPermohonan, "DIAIO");

                if (diariSiasatan.getInfoAudit().getDimasukOleh().getNama().equalsIgnoreCase(pengguna.getNama())) {
                    authorizedUser = true;
                }

                if (authorizedUser == false) {
                    diariSiasatan = null;
                    addSimpleError("Anda tidak dibenarkan untuk mengakses maklumat ini.");
                } else {
                    listButiranDiariSiasatan = new ArrayList<PermohonanKertasKandungan>();
                    if (diariSiasatan != null) {
                        tajuk = diariSiasatan.getTajuk();
                        listButiranDiariSiasatan = enforceService.findByIdKertas2(diariSiasatan.getIdKertas());
                        if (listButiranDiariSiasatan != null) {
                            recordCount = String.valueOf(listButiranDiariSiasatan.size());

                        }


                    }
                }

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
        System.out.println("idPermohonan1 rehydrate : " + idPermohonan);
    }

    public Resolution searchDiariSiasatan() {
        logger.info("------------searchDiariSiasatan-------------");
        System.out.println("idPermohonan : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                diariSiasatan = enforceService.findByIdKrts(idPermohonan, "DIAIO");

                if (diariSiasatan.getInfoAudit().getDimasukOleh().getNama().equalsIgnoreCase(pengguna.getNama())) {
                    authorizedUser = true;
                }

                if (authorizedUser == false) {
                    diariSiasatan = null;
                    addSimpleError("Anda tidak dibenarkan untuk mengakses maklumat ini.");
                } else {
                    listButiranDiariSiasatan = new ArrayList<PermohonanKertasKandungan>();
                    if (diariSiasatan != null) {
                        tajuk = diariSiasatan.getTajuk();
                        listButiranDiariSiasatan = enforceService.findByIdKertas2(diariSiasatan.getIdKertas());
                        if (listButiranDiariSiasatan != null) {
                            recordCount = String.valueOf(listButiranDiariSiasatan.size());

                        }


                    }
                }

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan_IO.jsp");


    }

    public Resolution simpanDiariSiasatan() {
        logger.info("------------simpanDiariSiasatan------------");
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                diariSiasatan = enforceService.findByIdKrts(idPermohonan, "DIAIO");
                if (diariSiasatan != null) {
                    ia = diariSiasatan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());
                } else {
                    diariSiasatan = new PermohonanKertas();
                    ia.setTarikhMasuk(new Date());
                    ia.setDimasukOleh(pengguna);
                }

                diariSiasatan.setPermohonan(permohonan);
                diariSiasatan.setInfoAudit(ia);
                diariSiasatan.setTajuk(tajuk);
                diariSiasatan.setCawangan(pengguna.getKodCawangan());
                diariSiasatan.setKodDokumen(kodDokumenDAO.findById("DIAIO"));
                enforceService.simpanPermohonanKertas(diariSiasatan);

                int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
                listButiranDiariSiasatan = enforceService.findByIdKertas2(diariSiasatan.getIdKertas());
                for (int i = 0; i < row; i++) {
                    if (listButiranDiariSiasatan.size() != 0 && i < listButiranDiariSiasatan.size()) {
                        Long id = listButiranDiariSiasatan.get(i).getIdKandungan();
                        if (id != null) {
                            permohonanKertasKandungan = enforceService.findByIdKandungan(id);
                        }
                    } else {
                        permohonanKertasKandungan = new PermohonanKertasKandungan();
                    }

                    String tarikh = getContext().getRequest().getParameter("tarikh" + i);
                    jam = getContext().getRequest().getParameter("jam" + i);
                    minit = getContext().getRequest().getParameter("minit" + i);
                    ampm = getContext().getRequest().getParameter("ampm" + i);
                    tarikh = tarikh + " " + jam + ":" + minit + " " + ampm;
                    kandungan = getContext().getRequest().getParameter("kandungan" + i);
                    permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan.setKertas(diariSiasatan);
                    permohonanKertasKandungan.setKandungan(kandungan);
                    permohonanKertasKandungan.setInfoAudit(ia);
                    permohonanKertasKandungan.setBil(i);

                    try {
                        if (tarikh != null) {
                            permohonanKertasKandungan.setTarikhButiran(sdf.parse(tarikh));
                        }
                    } catch (ParseException ex) {
                        logger.error(ex);
                    }
                    enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

                    // generate report

                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{permohonan.getIdPermohonan()};
                    String path = "";
                    String dokumenPath = conf.getProperty("document.path");
                    Dokumen d = null;
                    KodDokumen kd = null;

                    FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    String reportName = "";

                    kd = kodDokumenDAO.findById("DIAIO");
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        reportName = "ENFDSIO_MLK.rdf";
                    } else {
                        reportName = "ENFDSIO_NS.rdf";
                    }
                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    idDokumen = d.getIdDokumen();
                    logger.info("-------id dokumen : --------" + idDokumen);
                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");

            } else {
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai");
            }

        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }

//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan.jsp");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new RedirectResolution(UtilitiDiariSiasatanIOActionBean.class, "locate");
    }

    public Resolution deleteDiari() {
        logger.info("------------deleteDiari-----------");

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        System.out.println("idKertas:" + idKandungan);
        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
                System.out.println("kertas kandungan : " + permohonanKertasKandungan);
                if (permohonanKertasKandungan != null) {
                    enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan_IO.jsp");
    }

    public Resolution deleteSingle() {
        logger.info("------------deleteDiari-----------");

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        System.out.println("idKertas:" + idKandungan);
        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
                System.out.println("kertas kandungan : " + permohonanKertasKandungan);
                if (permohonanKertasKandungan != null) {
                    enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");

        return new RedirectResolution(UtilitiDiariSiasatanIOActionBean.class, "locate");
    }

    public Resolution reload() {
        logger.info("------------reload-------------");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("reload id mohon : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                diariSiasatan = enforceService.findByIdKrts(idPermohonan, "DIAIO");
                listButiranDiariSiasatan = new ArrayList<PermohonanKertasKandungan>();
                if (diariSiasatan != null) {
                    tajuk = diariSiasatan.getTajuk();
                    listButiranDiariSiasatan = enforceService.findByIdKertas2(diariSiasatan.getIdKertas());
                    if (listButiranDiariSiasatan != null) {
                        recordCount = String.valueOf(listButiranDiariSiasatan.size());

                    }
                }
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan_IO.jsp");
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

    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            DiariSiasatanCache u = (DiariSiasatanCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                diariSiasatan = u.diariSiasatan;
                idDokumen = u.idDokumen;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();
                    logger.info("idPermohonan getUrusanfromSession : " + idPermohonan);
                    logger.info("idDokumen getUrusanfromSession : " + idDokumen);
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
        DiariSiasatanCache u = (DiariSiasatanCache) ctx.getWorkData();
        if (u == null) {
            u = new DiariSiasatanCache();
        }
        u.permohonan = permohonan;
        u.diariSiasatan = diariSiasatan;
        u.idDokumen = idDokumen;

        logger.debug(":::: idDokumen DS" + idDokumen);
        logger.debug("saveToSession u.permohonan : " + u.permohonan.getIdPermohonan());
        logger.debug("saveToSession u.diariSiasatan : " + u.diariSiasatan.getPermohonan().getIdPermohonan());

        ctx.setWorkData(u);
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public PermohonanKertas getDiariSiasatan() {
        return diariSiasatan;
    }

    public void setDiariSiasatan(PermohonanKertas diariSiasatan) {
        this.diariSiasatan = diariSiasatan;
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

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public List<PermohonanKertasKandungan> getListButiranDiariSiasatan() {
        return listButiranDiariSiasatan;
    }

    public void setListButiranDiariSiasatan(List<PermohonanKertasKandungan> listButiranDiariSiasatan) {
        this.listButiranDiariSiasatan = listButiranDiariSiasatan;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
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

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public boolean isAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(boolean authorizedUser) {
        this.authorizedUser = authorizedUser;
    }
}

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
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PenyerahBarangOperasiDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.PenyerahBarangOperasi;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.io.Serializable;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/utiliti_no_serahan")
public class UtilitiNoSerahanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiNoSerahanActionBean.class);
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
    @Inject
    PenyerahBarangOperasiDAO penyerahBarangOperasiDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    DokumenDAO dokumenDAO;
    private OperasiPenguatkuasaan operasi;
    private KodJenisPengenalan jenisPengenalanPenerima;
    private PenyerahBarangOperasi penyerahBarangOperasi;
    private PegawaiPenyiasat pegawaiPenyiasat;
    private List<PegawaiPenyiasat> senaraiPegawaiPenyiasat;
    private String noPerserahan;
    private String namaPenerima;
    private String kodJP;
    private String noPengenalanPenerima;
    private String jawatanPenerima;
    private Date tarikhPerserahan;
    private String tempatPerserahan;
    private String jam;
    private String minit;
    private String ampm;
    private String idOperasi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private Permohonan permohonan;
    private String idPermohonan;
    private List<PermohonanKertasKandungan> listButiranDiariSiasatan;
    private Pengguna pengguna;
    private PermohonanKertas diariSiasatan;
    private String tajuk;
    private String recordCount;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private String idKandungan;
    private Long idDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private String kandungan;

    class NoSiasatanCache implements Serializable {

        PenyerahBarangOperasi penyerahBarangOperasi;
        Long idDokumen;
        PegawaiPenyiasat pegawaiPenyiasat;
        Permohonan permohonan;
        List<PegawaiPenyiasat> senaraiPegawaiPenyiasat;
    }

    @DefaultHandler
    public Resolution findNoSerahan() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        getUrusanfromSession();

        if (idPermohonan != null && noPerserahan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan, permohonan.getIdPermohonan());
            senaraiPegawaiPenyiasat = enforceService.findPegawaiPenyiasat(penyerahBarangOperasi.getOperasi().getIdOperasi(), permohonan.getIdPermohonan());

            if (penyerahBarangOperasi != null) {
                noPerserahan = penyerahBarangOperasi.getNoPerserahan();
                namaPenerima = penyerahBarangOperasi.getNamaPenerima();
                if (penyerahBarangOperasi.getJenisPengenalanPenerima().getKod() != null) {
                    kodJP = penyerahBarangOperasi.getJenisPengenalanPenerima().getKod();
                }

                noPengenalanPenerima = penyerahBarangOperasi.getNoPengenalanPenerima();
                jawatanPenerima = penyerahBarangOperasi.getJawatanPenerima();
                tarikhPerserahan = penyerahBarangOperasi.getTarikhPerserahan();
                tempatPerserahan = penyerahBarangOperasi.getTempatPerserahan();

            } else {
                idPermohonanNotExist = true;
                addSimpleError(
                        "No Serahan " + noPerserahan + " tidak dijumpai.");
            }

        }

        System.out.println("No serahan rehydrate : " + noPerserahan);
    }

    public Resolution searchNoSerahan() {
        logger.info("------------searchNoSerahan-------------");
        System.out.println("noPerserahan :" + noPerserahan);
        if (idPermohonan != null && noPerserahan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan, permohonan.getIdPermohonan());

            if (penyerahBarangOperasi != null) {
                idPermohonan = permohonan.getIdPermohonan();
                System.out.println("idPermohonan" + idPermohonan);
                noPerserahan = penyerahBarangOperasi.getNoPerserahan();
                System.out.println("noPerserahan" + noPerserahan);
                senaraiPegawaiPenyiasat = enforceService.findPegawaiPenyiasat(penyerahBarangOperasi.getOperasi().getIdOperasi(), permohonan.getIdPermohonan());
                System.out.println("saiz lalala" + senaraiPegawaiPenyiasat.size());
                System.out.println("idOperasi lalala" + penyerahBarangOperasi.getOperasi().getIdOperasi());
            }

        } else {
            idPermohonanNotExist = true;
            addSimpleError("Permohonan atau No Serahan " + idPermohonan + noPerserahan + " tidak dijumpai.");
        }

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
    }

    public Resolution simpanMaklumatPenerima() {
//        senaraiPegawaiPenyiasat = enforceService.findPegawaiPenyiasat(penyerahBarangOperasi.getOperasi().getIdOperasi());
        logger.info("------------simpanMaklumatPenerima------------");
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        if (noPerserahan != null) {
//            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan, permohonan.getIdPermohonan());
            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan);

            if (penyerahBarangOperasi != null) {

                if (penyerahBarangOperasi != null) {
                    ia = penyerahBarangOperasi.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());


                } else {
                    penyerahBarangOperasi = new PenyerahBarangOperasi();
                    ia.setTarikhMasuk(new Date());
                    ia.setDimasukOleh(pengguna);


                }

                penyerahBarangOperasi.setNamaPenerima(namaPenerima);

                if (kodJP != null) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan();
                    kjp.setKod(kodJP);
                    penyerahBarangOperasi.setJenisPengenalanPenerima(kjp);
                }

                penyerahBarangOperasi.setNoPengenalanPenerima(noPengenalanPenerima);
                penyerahBarangOperasi.setJawatanPenerima(jawatanPenerima);

                String tarikh = getContext().getRequest().getParameter("tarikh");
                jam = getContext().getRequest().getParameter("jam");
                minit = getContext().getRequest().getParameter("minit");
                ampm = getContext().getRequest().getParameter("ampm");
                tarikh = tarikh + " " + jam + ":" + minit + " " + ampm;

                try {
                    if (tarikh != null) {
                        penyerahBarangOperasi.setTarikhPerserahan(sdf.parse(tarikh));


                    }
                } catch (ParseException ex) {
                    logger.error(ex);


                }

                penyerahBarangOperasi.setTempatPerserahan(tempatPerserahan);
                enforceService.simpanPenyerahBarangOperasi(penyerahBarangOperasi);

            } else {
                idPermohonanNotExist = true;
                addSimpleError(
                        "No Serahan " + noPerserahan + " tidak dijumpai.");
            }
            // generate report

            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            kd = kodDokumenDAO.findById("SERAH");
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportName = "ENFSERAH_MLK.rdf";
            } else {
                reportName = "ENFSERAH_NS.rdf";
            }
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            idDokumen = d.getIdDokumen();
            logger.info("-------id dokumen : --------" + idDokumen);


            addSimpleMessage("Maklumat telah berjaya disimpan dan dokumen serahan telah dijana.");

        } else {
            addSimpleError("Sila masukkan No Serahan.");
        }

//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_diari_siasatan.jsp");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new RedirectResolution(UtilitiNoSerahanActionBean.class, "locate");
    }

//    public Resolution deleteSerahan() {
//        logger.info("------------deleteSerahan-----------");
//
//        noPerserahan = getContext().getRequest().getParameter("idKandungan");
//        System.out.println("noPerserahan:" + noPerserahan);
//
//
//        try {
//            if (noPerserahan != null) {
//                penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan);
//                System.out.println("penyerahBarangOperasi : " + penyerahBarangOperasi);
//                enforceService.deletePenyerah(penyerahBarangOperasi);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            addSimpleError(
//                    "kesalahan dalam menghapuskan rekod");
//
//
//        }
//        addSimpleMessage("Rekod berjaya dihapuskan");
//
//
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
//
//
//    }
    public Resolution reload() {
        logger.info("------------reload-------------");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("reload id mohon : " + idPermohonan);
        logger.info("------------searchNoSerahan-------------");
        System.out.println("noPerserahan :" + noPerserahan);

        if (noPerserahan != null) {
//            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan);
            penyerahBarangOperasi = enforceService.findNoSerah(noPerserahan, permohonan.getIdPermohonan());
            if (penyerahBarangOperasi != null) {
                penyerahBarangOperasi = new PenyerahBarangOperasi();
                noPerserahan = penyerahBarangOperasi.getNoPerserahan();
            } else {
                idPermohonanNotExist = true;
                addSimpleError(
                        "No Serahan " + noPerserahan + " tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila masukkan No Serahan.");

        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
    }

    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();


        try {
            NoSiasatanCache u = (NoSiasatanCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                pegawaiPenyiasat = u.pegawaiPenyiasat;
                penyerahBarangOperasi = u.penyerahBarangOperasi;
                idDokumen = u.idDokumen;
                if (penyerahBarangOperasi != null) {
                    noPerserahan = penyerahBarangOperasi.getNoPerserahan();
                    System.out.println("noPerserahan getUrusanfromSession : " + noPerserahan);
                }

                if (pegawaiPenyiasat != null) {
                    namaPenerima = pegawaiPenyiasat.getNama();
                    kodJP = pegawaiPenyiasat.getJenisPengenalan().getKod();
                    noPengenalanPenerima = pegawaiPenyiasat.getNoPengenalan();
                    jawatanPenerima = pegawaiPenyiasat.getJawatan();
                    tarikhPerserahan = penyerahBarangOperasi.getTarikhPerserahan();
                    tempatPerserahan = penyerahBarangOperasi.getTempatPerserahan();
                }

                senaraiPegawaiPenyiasat = u.senaraiPegawaiPenyiasat;
                System.out.println("id dokumen :::::" + idDokumen);
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
        NoSiasatanCache u = (NoSiasatanCache) ctx.getWorkData();
        if (u == null) {
            u = new NoSiasatanCache();
        }
        u.permohonan = permohonan;
        u.idDokumen = idDokumen;
        u.penyerahBarangOperasi = penyerahBarangOperasi;
        u.pegawaiPenyiasat = pegawaiPenyiasat;
        u.senaraiPegawaiPenyiasat = senaraiPegawaiPenyiasat;
        System.out.println("saveToSession u.penyerahBarangOperasi : " + u.penyerahBarangOperasi.getNoPerserahan());
        ctx.setWorkData(u);

    }

    public Resolution carian() {

        namaPenerima = getContext().getRequest().getParameter("namaPenerima");
        logger.debug("Nama penerima :" + namaPenerima);
        Long idPegawai = Long.getLong(namaPenerima);
        PegawaiPenyiasat pegawai = new PegawaiPenyiasat();
        pegawai = pegawaiPenyiasatDAO.findById(idPegawai);
        if (pegawai != null) {
//        kodJP = getContext().getRequest().getParameter("kodJP");
//        noPengenalanPenerima = getContext().getRequest().getParameter("noPengenalanPenerima");
//        jawatanPenerima = getContext().getRequest().getParameter("jawatanPenerima");
            kodJP = pegawai.getJenisPengenalan().getKod();
            noPengenalanPenerima = pegawai.getNoPengenalan();
            jawatanPenerima = pegawai.getJawatan();
            if (namaPenerima != null && !namaPenerima.isEmpty()) {
                penyerahBarangOperasi.setNamaPenerima(pegawai.getNama());
            }
            if (kodJP != null && !kodJP.isEmpty()) {
                penyerahBarangOperasi.setJenisPengenalanPenerima(pegawai.getJenisPengenalan());
            }
            if (noPengenalanPenerima != null && !noPengenalanPenerima.isEmpty()) {
                penyerahBarangOperasi.setNoPengenalanPenerima(pegawai.getNoPengenalan());
            }
            if (jawatanPenerima != null && !jawatanPenerima.isEmpty()) {
                penyerahBarangOperasi.setJawatanPenerima(pegawai.getJawatan());
            }
        }

//        return new JSP("penguatkuasaan/carian_no_serahan.jsp").addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
    }

    public Resolution findPengguna() {
        String id = getContext().getRequest().getParameter("id");
        pegawaiPenyiasat = pegawaiPenyiasatDAO.findById(Long.parseLong(id));

        if (pegawaiPenyiasat != null) {
            namaPenerima = pegawaiPenyiasat.getNama();
            kodJP = pegawaiPenyiasat.getJenisPengenalan().getKod();
            noPengenalanPenerima = pegawaiPenyiasat.getNoPengenalan();
            jawatanPenerima = pegawaiPenyiasat.getJawatan();
            System.out.println("nama : " + noPerserahan);
            searchNoSerahanTest(noPerserahan);
//            this.setNamaPenerima(namaPenerima);
//            this.setKodJP(kodJP);
//            this.setNoPengenalanPenerima(noPengenalanPenerima);
//            this.setJawatanPenerima(jawatanPenerima);

            System.out.println("nama : " + namaPenerima);
            System.out.println("Jenis Pengenalan : " + kodJP);
            System.out.println("No Pengenalan : " + noPengenalanPenerima);
            System.out.println("jawatan : " + jawatanPenerima);


        }
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        saveToSession(ctx);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_no_serahan.jsp");
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

    public void searchNoSerahanTest(String noPs) {
        logger.info("------------searchNoSerahan-------------");
        System.out.println("noPerserahan :" + noPs);

        if (noPs != null) {
            penyerahBarangOperasi = enforceService.findNoSerah(noPs);
            if (penyerahBarangOperasi != null) {
                noPerserahan = penyerahBarangOperasi.getNoPerserahan();
                senaraiPegawaiPenyiasat = enforceService.findPegawaiPenyiasat(penyerahBarangOperasi.getOperasi().getIdOperasi(), pegawaiPenyiasat.getPermohonan().getIdPermohonan());
                System.out.println("saiz lalala" + senaraiPegawaiPenyiasat.size());
                System.out.println("idOperasi lalala" + penyerahBarangOperasi.getOperasi().getIdOperasi());
            }
        }
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

    public String getNoPerserahan() {
        return noPerserahan;
    }

    public void setNoPerserahan(String noPerserahan) {
        this.noPerserahan = noPerserahan;
    }

    public PenyerahBarangOperasi getPenyerahBarangOperasi() {
        return penyerahBarangOperasi;
    }

    public void setPenyerahBarangOperasi(PenyerahBarangOperasi penyerahBarangOperasi) {
        this.penyerahBarangOperasi = penyerahBarangOperasi;
    }

    public String getJawatanPenerima() {
        return jawatanPenerima;
    }

    public void setJawatanPenerima(String jawatanPenerima) {
        this.jawatanPenerima = jawatanPenerima;
    }

    public KodJenisPengenalan getJenisPengenalanPenerima() {
        return jenisPengenalanPenerima;
    }

    public void setJenisPengenalanPenerima(KodJenisPengenalan jenisPengenalanPenerima) {
        this.jenisPengenalanPenerima = jenisPengenalanPenerima;
    }

    public String getKodJP() {
        return kodJP;
    }

    public void setKodJP(String kodJP) {
        this.kodJP = kodJP;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public Date getTarikhPerserahan() {
        return tarikhPerserahan;
    }

    public void setTarikhPerserahan(Date tarikhPerserahan) {
        this.tarikhPerserahan = tarikhPerserahan;
    }

    public String getTempatPerserahan() {
        return tempatPerserahan;
    }

    public void setTempatPerserahan(String tempatPerserahan) {
        this.tempatPerserahan = tempatPerserahan;
    }

    public List<PegawaiPenyiasat> getSenaraiPegawaiPenyiasat() {
        return senaraiPegawaiPenyiasat;
    }

    public void setSenaraiPegawaiPenyiasat(List<PegawaiPenyiasat> senaraiPegawaiPenyiasat) {
        this.senaraiPegawaiPenyiasat = senaraiPegawaiPenyiasat;
    }

    public OperasiPenguatkuasaan getOperasi() {
        return operasi;
    }

    public void setOperasi(OperasiPenguatkuasaan operasi) {
        this.operasi = operasi;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public PegawaiPenyiasat getPegawaiPenyiasat() {
        return pegawaiPenyiasat;
    }

    public void setPegawaiPenyiasat(PegawaiPenyiasat pegawaiPenyiasat) {
        this.pegawaiPenyiasat = pegawaiPenyiasat;
    }
}

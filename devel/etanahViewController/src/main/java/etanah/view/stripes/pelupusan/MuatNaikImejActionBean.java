/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import etanah.view.strata.*;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.service.StrataPtService;
import etanah.service.PembangunanService;
import etanah.service.LaporanTanahService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.io.File;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;
import java.util.Date;
import org.hibernate.Transaction;
import org.hibernate.Session;
import etanah.model.KodKegunaanRuangUdara;
import etanah.dao.KodKegunaanRuangUdaraDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import java.text.SimpleDateFormat;

/**
 *
 * @author Srinivas
 */
@UrlBinding("/pelupusan/muat_naik_imej")
public class MuatNaikImejActionBean extends BasicTabActionBean {

    private PermohonanStrata pemilik;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodKegunaanRuangUdaraDAO kodKegunaanRuangUdaraDAO;
    private Permohonan permohonan;
    private ImejLaporan imejLaporanUP;
    private LaporanTanah laporanTanah;
    private String sempandanUtaraJenis;
    private String sempadanUtaraNoLot;
    private String sempadanUtaraKegunaan;
    private String sempadanSelatanJenis;
    private String sempadanSelatanNoLot;
    private String sempadanSelatanKegunaan;
    private String sempadanTimurJenis;
    private String sempadanTimurNoLot;
    private String sempadanTimurKegunaan;
    private String sempadanBaratJenis;
    private String sempadanBaratNoLot;
    private String sempadanBaratKegunaan;
    private String adaBangunan;
    private String strukturTambahanJenis;
    private String strukturTambahanKedudukan;
    private String strukturTambahanKedudukanTerlunjur;
    private String simpanjln;
    private String simpanlaluan;
    private String keadaanBangunan;
    private List<ImejLaporan> utaraDoc = new ArrayList();
    private List<ImejLaporan> selatanDoc = new ArrayList();
    private List<ImejLaporan> timurDoc = new ArrayList();
    private List<ImejLaporan> baratDoc = new ArrayList();
    private List<ImejLaporan> senaraiImejLaporan;
    private String imageFolderPath;
    private static final Logger LOG = Logger.getLogger(MuatNaikImejActionBean.class);
    private Dokumen dokumen;
    private FileBean fileToBeUpload;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private String strKodGunaRuangUdara;
    private List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara;
    private String tarikhSiasatan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String catatan = "";
    String msg;
    String _PAGE_IMEJ_POP_ = "pelupusan/common/muat_naik_imej_pop.jsp";
    String _PopUp_ = "pelupusan/common/muat_imej_popup.jsp";
    String _Main = "pelupusan/common/muat_naik_imej.jsp";

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);

        if (laporanTanah != null) {

            String dokumenPath = conf.getProperty("document.path");
            imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
            LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
            FolderDokumen fd = permohonan.getFolderDokumen();

            String docPath = conf.getProperty("document.path");

            senaraiImejLaporan = new ArrayList<ImejLaporan>();
            senaraiImejLaporan = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan());
            System.out.println("###### senaraiImejLaporan getsize :" + senaraiImejLaporan.size());

        } else {
            keadaanBangunan = "KEKAL";
        }
    }

    @DefaultHandler
    public Resolution showForm() {
//        return new JSP("pembangunan/common/maklumat_imej.jsp").addParameter("tab", "true");
        return new JSP("pelupusan/common/maklumat_naik_imej.jsp").addParameter("tab", "true");
    }

    public Resolution showMuatNaikImej() {
        LOG.info("###### showMuatNaikImej");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("###### showMuatNaikImej---idPermohonan-----:" + idPermohonan);
        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);
        LOG.info("###### showMuatNaikImej---laporanTanah-----:" + laporanTanah);
        if (laporanTanah == null) {
            msg = "Sila pastikan Laporan Tanah lengkap di isi sebelum muat naik imej.";
            addSimpleError(msg);
            getContext().getRequest().setAttribute("cheackingLT", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("cheackingLT", Boolean.TRUE);
        }
        return new JSP(_Main).addParameter("tab", "true");
    }

    public Resolution reload() {
        LOG.info("###### reload");
//bak        return new RedirectResolution(MuatNaikImejActionBean.class, "showMuatNaikImej");
        return new JSP(_Main).addParameter("tab", "true");
    }

    public Resolution showRefresh() {
        LOG.info("###### showRefresh");
        getContext().getRequest().setAttribute("cheackingLT", Boolean.TRUE);
        rehydrate();
//        return new JSP("pembangunan/common/muat_naik_imej.jsp").addParameter("popup", "true");
        return new RedirectResolution(MuatNaikImejActionBean.class, "showMuatNaikImej");
    }

//    public Resolution simpanBangunan1() throws ParseException {
//
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//
//
//        String error = "";
//        String msg = "";
//
//        permohonan = permohonanService.findById(idPermohonan);
//        laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);
//
//        LOG.info("------laporanTanah----" + laporanTanah);
//
//        if (laporanTanah == null) {
//
//            LOG.info("--if----laporanTanah----" + laporanTanah);
//
//            laporanTanah = new LaporanTanah();
//            laporanTanah.setPermohonan(permohonan);
//            InfoAudit infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            laporanTanah.setInfoAudit(infoAudit);
//        } else {
//            LOG.info("--else----laporanTanah----" + laporanTanah);
//
//            InfoAudit ia = laporanTanah.getInfoAudit();
//            ia.setDiKemaskiniOleh(peng);
//            ia.setTarikhKemaskini(new java.util.Date());
//            laporanTanah.setInfoAudit(ia);
//        }
//
//        laporanTanah.setSempadanUtaraJenis(sempandanUtaraJenis);
//        laporanTanah.setSempadanUtaraNoLot(sempadanUtaraNoLot);
//        laporanTanah.setSempadanUtaraKegunaan(sempadanUtaraKegunaan);
//        laporanTanah.setSempadanTimurJenis(sempadanSelatanJenis);
//        laporanTanah.setSempadanSelatanNoLot(sempadanSelatanNoLot);
//        laporanTanah.setSempadanSelatanKegunaan(sempadanSelatanKegunaan);
//        laporanTanah.setSempadanSelatanJenis(sempadanSelatanJenis);
//        laporanTanah.setSempadanTimurJenis(sempadanTimurJenis);
//        laporanTanah.setSempadanTimurNoLot(sempadanTimurNoLot);
//        laporanTanah.setSempadanTimurKegunaan(sempadanTimurKegunaan);
//        laporanTanah.setSempadanBaratJenis(sempadanBaratJenis);
//        laporanTanah.setSempadanBaratNoLot(sempadanBaratNoLot);
//        laporanTanah.setSempadanBaratKegunaan(sempadanBaratKegunaan);
//        laporanTanah.setAdaBangunan(adaBangunan.charAt(0));
//        laporanTanah.setJenisBangunan(jenisBangunan);
//        laporanTanah.setKeadaanTanah(keadaanBangunan);
//        laporanTanah.setTarikhSiasat(sdf.parse(tarikhSiasatan));
////        laporanTanah.setStrukturTambahanJenis(strukturTambahanJenis);
////        laporanTanah.setStrukturTambahanKedudukan(strukturTambahanKedudukan);
//
////
////        if ((simpanjln != null) && (simpanlaluan != null) && (!simpanjln.equals("")) && (!simpanlaluan.equals(""))) {
////            laporanTanah.setStrukturTambahanKedudukanTerlunjur("Simpanan jalan dan simpanan laluan");
////
////        } else if ((simpanjln != null) && (!simpanjln.equals(""))) {
////            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanjln);
////        } else if ((simpanlaluan != null) && (!simpanlaluan.equals(""))) {
////            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanlaluan);
////
////        }
//        //laporanTanah  = new LaporanTanah();
//        LOG.info("--before save----");
//        strService.SimpanKand(laporanTanah);
//        LOG.info("--save----");
//        msg = "Maklumat telah berjaya disimpan.";
//        addSimpleMessage(msg);
////        return new JSP("strata/Ruang_Udara/maklumat_Bangunan.jsp").addParameter("tab", "true");
//        return new RedirectResolution(MuatNaikImejActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
//
//    }
    //added for delete images in laporan_tanah
    public Resolution hapusImej() {
        LOG.info("###### hapusImej");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Long dokumenId = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        permohonan = permohonanDAO.findById(idPermohonan);
        Long folderId = 0L;

        if (permohonan.getFolderDokumen() != null) {
            folderId = permohonan.getFolderDokumen().getFolderId();
        }

        LOG.info("---------------idPermohonan:--------" + idPermohonan);
        LOG.info("---------------idDokumen:--------" + dokumenId);
        try {
            dokumen = dokumenDAO.findById(dokumenId);
            String docPath = conf.getProperty("document.path");
            File file = new File(docPath + dokumen.getNamaFizikal());
            file.delete();
            pembangunanService.deleteImejLaporanByIdDokumen(dokumenId);
            pembangunanService.deleteKandunganFolderByIdDokumen(folderId, dokumenId);
            pembangunanService.deleteDokumenCapaianByIdDokumen(dokumenId);
            pembangunanService.deleteAll2(dokumen);
            LOG.info("---------------Deleted idDokumen is::--------" + dokumenId);
            String msg = "Imej telah berjaya dipadamkan.";
            addSimpleMessage(msg);
            return new RedirectResolution(MuatNaikImejActionBean.class, "showMuatNaikImej");
        } catch (Exception e) {
            LOG.info("###### Execption :" + e.getMessage());
            e.printStackTrace();
            addSimpleError("Fail tidak dapat dihapus. Sila Hubungi Pentabir Sistem.");
            return new RedirectResolution(MuatNaikImejActionBean.class, "showMuatNaikImej");
        }
    }

    public Resolution muatNaik() throws Exception {
        Logger.getLogger(MuatNaikImejActionBean.class).info("simpanMuatNaik::start");
//        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenPath = conf.getProperty("document.path");
        catatan = getContext().getRequest().getParameter("catatan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("###### idPermohonan :" + idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);

        if (catatan.isEmpty()) {
            addSimpleError("Sila masukan catatan/ulasan Imej.");
        } else {

            if (fileToBeUpload != null) {
                try {
                    String fileName = fileToBeUpload.getFileName();
                    String contentType = fileToBeUpload.getContentType();
                    LOG.debug("###fileName :" + fileName + " contentType :" + contentType);
                    LOG.debug("content type = " + contentType);
                    permohonan = permohonanDAO.findById(idPermohonan);
                    DMSUtil dmsUtil = new DMSUtil();

                    if (permohonan != null) {
                        Dokumen doc = pembangunanService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
                        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                        updatePathDokumen(fizikalPath, doc.getIdDokumen(), fileToBeUpload.getContentType(), catatan, pguna);
//                        updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), contentType);
                    }

//                    System.out.println("no null::" + fileToBeUpload.getContentType());
//                    dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
//                    FileUtil fileUtil = new FileUtil(dokumenPath);
//                    LOG.info("###### fileUtil :" + fileUtil.toString());
////                    Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, Long.parseLong(folderId), ia, permohonan);
////                    dokumenId = String.valueOf(doc.getIdDokumen());
//                    LOG.info("###### dokumenId :" + dokumenId);
//                    fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
//                    String fizikalPath = folderId + File.separator + dokumenId;
//                    LOG.info("###### fizikalPath :" + fizikalPath);
//                    updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan, pguna);

                    LOG.info("simpanMuatNaik::finish");
                    addSimpleMessage("Muat naik fail berjaya.");
                    
                } catch (Exception ex) {
                    Logger.getLogger(MuatNaikImejActionBean.class).error(ex);
                }
            } else {
                addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            }
        }

//        rehydrate();
//        return new JSP("pembangunan/common/muat_naik_imej.jsp").addParameter("tab", "true");
        return new JSP(_PopUp_).addParameter("popup", "true");
//        return new RedirectResolution(MuatNaikImejActionBean.class, "showMuatNaikImej");


    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        LOG.info("###### updatePathDok");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution uploadDoc() {
        LOG.info("###### upluadDoc");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        long fId = permohonan.getFolderDokumen().getFolderId();
        long dId = permohonan.getFolderDokumen().getSenaraiKandungan().get(0).getIdKandunganFolder();
        FolderDokumen folderDokumen = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        return new JSP(_PopUp_).addParameter("popup", "true");
    }

    public Resolution viewImejPopup() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idDok = getContext().getRequest().getParameter("idDokumen");
        LOG.info("###### viewImejPopup idPermohonan : " + idPermohonan);
        LOG.info("###### viewImejPopup idDok : " + idDok);

        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);
        dokumen = dokumenDAO.findById(Long.valueOf(idDok));
        imejLaporan = imejLaporanDAO.findById(Long.valueOf(idDok));

        imejLaporanUP = new ImejLaporan();

        imejLaporanUP = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan(), dokumen.getIdDokumen());
        LOG.info("###### viewImejPopup imejLaporanUP : " + imejLaporanUP);
        return new JSP(_PAGE_IMEJ_POP_).addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan, Pengguna pguna) {
        System.out.println("updatePathDokumen");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null) {
            d.setTajuk(catatan);
        }

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejLaporan.setCawangan(pguna.getKodCawangan());
        imejLaporan.setDokumen(d);
        imejLaporan.setLaporanTanah(laporanTanah);
        imejLaporan.setCatatan(catatan);

        imejLaporan.setInfoAudit(ia);
        pembangunanService.saveImej(imejLaporan);

        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public List<ImejLaporan> getUtaraDoc() {
        return utaraDoc;
    }

    public void setUtaraDoc(List<ImejLaporan> utaraDoc) {
        this.utaraDoc = utaraDoc;
    }

    public List<ImejLaporan> getBaratDoc() {
        return baratDoc;
    }

    public void setBaratDoc(List<ImejLaporan> baratDoc) {
        this.baratDoc = baratDoc;
    }

    public List<ImejLaporan> getSelatanDoc() {
        return selatanDoc;
    }

    public void setSelatanDoc(List<ImejLaporan> selatanDoc) {
        this.selatanDoc = selatanDoc;
    }

    public List<ImejLaporan> getTimurDoc() {
        return timurDoc;
    }

    public void setTimurDoc(List<ImejLaporan> timurDoc) {
        this.timurDoc = timurDoc;
    }

    public String getKeadaanBangunan() {
        return keadaanBangunan;
    }

    public void setKeadaanBangunan(String keadaanBangunan) {
        this.keadaanBangunan = keadaanBangunan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public List<KodKegunaanRuangUdara> getSenaraikodGunaRuangUdara() {
        return senaraikodGunaRuangUdara;
    }

    public void setSenaraikodGunaRuangUdara(List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara) {
        this.senaraikodGunaRuangUdara = senaraikodGunaRuangUdara;
    }

    public String getStrKodGunaRuangUdara() {
        return strKodGunaRuangUdara;
    }

    public void setStrKodGunaRuangUdara(String strKodGunaRuangUdara) {
        this.strKodGunaRuangUdara = strKodGunaRuangUdara;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTarikhSiasatan() {
        return tarikhSiasatan;
    }

    public void setTarikhSiasatan(String tarikhSiasatan) {
        this.tarikhSiasatan = tarikhSiasatan;
    }

    public String getSimpanlaluan() {
        return simpanlaluan;
    }

    public void setSimpanlaluan(String simpanlaluan) {
        this.simpanlaluan = simpanlaluan;
    }

    public String getSimpanjln() {
        return simpanjln;
    }

    public void setSimpanjln(String simpanjln) {
        this.simpanjln = simpanjln;
    }
    private String k;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }
    private String t;

    public String getStrukturTambahanJenis() {
        return strukturTambahanJenis;
    }

    public void setStrukturTambahanJenis(String strukturTambahanJenis) {
        this.strukturTambahanJenis = strukturTambahanJenis;
    }

    public String getStrukturTambahanKedudukan() {
        return strukturTambahanKedudukan;
    }

    public void setStrukturTambahanKedudukan(String strukturTambahanKedudukan) {
        this.strukturTambahanKedudukan = strukturTambahanKedudukan;
    }

    public String getStrukturTambahanKedudukanTerlunjur() {
        return strukturTambahanKedudukanTerlunjur;
    }

    public void setStrukturTambahanKedudukanTerlunjur(String strukturTambahanKedudukanTerlunjur) {
        this.strukturTambahanKedudukanTerlunjur = strukturTambahanKedudukanTerlunjur;
    }

    public String getSempadanBaratJenis() {
        return sempadanBaratJenis;
    }

    public void setSempadanBaratJenis(String sempadanBaratJenis) {
        this.sempadanBaratJenis = sempadanBaratJenis;
    }

    public String getSempadanBaratKegunaan() {
        return sempadanBaratKegunaan;
    }

    public void setSempadanBaratKegunaan(String sempadanBaratKegunaan) {
        this.sempadanBaratKegunaan = sempadanBaratKegunaan;
    }

    public String getSempadanBaratNoLot() {
        return sempadanBaratNoLot;
    }

    public void setSempadanBaratNoLot(String sempadanBaratNoLot) {
        this.sempadanBaratNoLot = sempadanBaratNoLot;
    }

    public String getSempadanTimurJenis() {
        return sempadanTimurJenis;
    }

    public void setSempadanTimurJenis(String sempadanTimurJenis) {
        this.sempadanTimurJenis = sempadanTimurJenis;
    }

    public String getSempadanTimurKegunaan() {
        return sempadanTimurKegunaan;
    }

    public void setSempadanTimurKegunaan(String sempadanTimurKegunaan) {
        this.sempadanTimurKegunaan = sempadanTimurKegunaan;
    }

    public String getSempadanTimurNoLot() {
        return sempadanTimurNoLot;
    }

    public void setSempadanTimurNoLot(String sempadanTimurNoLot) {
        this.sempadanTimurNoLot = sempadanTimurNoLot;
    }

    public String getSempadanSelatanKegunaan() {
        return sempadanSelatanKegunaan;
    }

    public void setSempadanSelatanKegunaan(String sempadanSelatanKegunaan) {
        this.sempadanSelatanKegunaan = sempadanSelatanKegunaan;
    }

    public String getSempadanSelatanNoLot() {
        return sempadanSelatanNoLot;
    }

    public void setSempadanSelatanNoLot(String sempadanSelatanNoLot) {
        this.sempadanSelatanNoLot = sempadanSelatanNoLot;
    }

    public String getSempadanSelatanJenis() {
        return sempadanSelatanJenis;
    }

    public void setSempadanSelatanJenis(String sempadanSelatanJenis) {
        this.sempadanSelatanJenis = sempadanSelatanJenis;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }
    private String jenisBangunan;

    public String getAdaBangunan() {
        return adaBangunan;
    }

    public void setAdaBangunan(String adaBangunan) {
        this.adaBangunan = adaBangunan;
    }

    public String getSempandanUtaraJenis() {
        return sempandanUtaraJenis;
    }

    public void setSempandanUtaraJenis(String sempandanUtaraJenis) {
        this.sempandanUtaraJenis = sempandanUtaraJenis;
    }

    public String getSempadanUtaraKegunaan() {
        return sempadanUtaraKegunaan;
    }

    public void setSempadanUtaraKegunaan(String sempadanUtaraKegunaan) {
        this.sempadanUtaraKegunaan = sempadanUtaraKegunaan;
    }

    public String getSempadanUtaraNoLot() {
        return sempadanUtaraNoLot;
    }

    public void setSempadanUtaraNoLot(String sempadanUtaraNoLot) {
        this.sempadanUtaraNoLot = sempadanUtaraNoLot;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanStrata getPemilik() {
        return pemilik;
    }

    public void setPemilik(PermohonanStrata pemilik) {
        this.pemilik = pemilik;
    }

    public String getImageFolderPath() {
        return imageFolderPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }

    public List<ImejLaporan> getSenaraiImejLaporan() {
        return senaraiImejLaporan;
    }

    public void setSenaraiImejLaporan(List<ImejLaporan> senaraiImejLaporan) {
        this.senaraiImejLaporan = senaraiImejLaporan;
    }

    public ImejLaporan getImejLaporanUP() {
        return imejLaporanUP;
    }

    public void setImejLaporanUP(ImejLaporan imejLaporanUP) {
        this.imejLaporanUP = imejLaporanUP;
    }

    public String getMsg() {
        return msg;
    }
}

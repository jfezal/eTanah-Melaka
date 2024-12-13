/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanStrata;
import etanah.service.StrataPtService;
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
import etanah.model.KandunganFolder;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;
import java.util.Date;
import etanah.model.ImejLaporanStrata;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;
import org.hibernate.Session;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.KodKegunaanRuangUdara;
import etanah.dao.KodKegunaanRuangUdaraDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
@UrlBinding("/strata/maklumat_bangunan")
public class MaklumatBangunanActionBean extends BasicTabActionBean {

    private PermohonanStrata pemilik;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
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
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodKegunaanRuangUdaraDAO kodKegunaanRuangUdaraDAO;
    private Permohonan permohonan;
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
    private static final Logger LOG = Logger.getLogger(MaklumatBangunanActionBean.class);
    private Dokumen dokumen;
    private FileBean fileToBeUpload;
//    private ImejLaporanStrata imejStrata = new ImejLaporanStrata();
    private ImejLaporan imejLaporan = new ImejLaporan();
    String catatan = "";
    private String strKodGunaRuangUdara;
    private List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara;
    private String tarikhSiasatan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pemilik = strService.findID(idPermohonan);
        senaraikodGunaRuangUdara = kodKegunaanRuangUdaraDAO.findAll();
        laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {

            sempandanUtaraJenis = laporanTanah.getSempadanUtaraJenis();
            sempadanUtaraNoLot = laporanTanah.getSempadanUtaraNoLot();
            sempadanUtaraKegunaan = laporanTanah.getSempadanUtaraKegunaan();
            sempadanSelatanJenis = laporanTanah.getSempadanSelatanJenis();
            sempadanSelatanNoLot = laporanTanah.getSempadanSelatanNoLot();
            sempadanSelatanKegunaan = laporanTanah.getSempadanSelatanKegunaan();
            sempadanTimurJenis = laporanTanah.getSempadanTimurJenis();
            sempadanTimurNoLot = laporanTanah.getSempadanTimurNoLot();
            sempadanTimurKegunaan = laporanTanah.getSempadanTimurKegunaan();
            sempadanBaratJenis = laporanTanah.getSempadanBaratJenis();
            sempadanBaratNoLot = laporanTanah.getSempadanBaratNoLot();
            sempadanBaratKegunaan = laporanTanah.getSempadanBaratKegunaan();
            adaBangunan = String.valueOf(laporanTanah.getAdaBangunan());
            jenisBangunan = laporanTanah.getJenisBangunan();
            keadaanBangunan = laporanTanah.getKeadaanTanah();
            strukturTambahanJenis = laporanTanah.getStrukturTambahanJenis();
            strukturTambahanKedudukan = laporanTanah.getStrukturTambahanKedudukan();
            if (laporanTanah.getTarikhSiasat() != null) {
                tarikhSiasatan = sdf.format(laporanTanah.getTarikhSiasat());
            }

            if ((laporanTanah.getStrukturTambahanKedudukanTerlunjur() != null) && (!laporanTanah.getStrukturTambahanKedudukanTerlunjur().equals(""))) {
                if (laporanTanah.getStrukturTambahanKedudukanTerlunjur().equals("Simpanan jalan dan simpanan laluan")) {
                    simpanjln = "Simpanan Jalan";
                    simpanlaluan = "Simpanan Laluan";
                } else if (laporanTanah.getStrukturTambahanKedudukanTerlunjur().equals("Simpanan Jalan")) {
                    simpanjln = laporanTanah.getStrukturTambahanKedudukanTerlunjur();
                } else if (laporanTanah.getStrukturTambahanKedudukanTerlunjur().equals("Simpanan Laluan")) {
                    simpanlaluan = laporanTanah.getStrukturTambahanKedudukanTerlunjur();
                }
            }

            utaraDoc = comm.getListDocL('U', laporanTanah.getIdLaporan());
            selatanDoc = comm.getListDocL('S', laporanTanah.getIdLaporan());
            timurDoc = comm.getListDocL('T', laporanTanah.getIdLaporan());
            baratDoc = comm.getListDocL('B', laporanTanah.getIdLaporan());

            // added by Murali  Getting Image Path
            String dokumenPath = conf.getProperty("document.path");
            imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
            LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
            FolderDokumen fd = permohonan.getFolderDokumen();

            String docPath = conf.getProperty("document.path");

            senaraiImejLaporan = new ArrayList<ImejLaporan>();
            senaraiImejLaporan = strService.findImejlaporan(laporanTanah.getIdLaporan());

        } else {
            keadaanBangunan = "KEKAL";
        }


    }

    @DefaultHandler
    public Resolution showForm() {

        LOG.info("------showForm----");
        return new JSP("strata/Ruang_Udara/maklumat_Bangunan.jsp").addParameter("tab", "true");
    }

    public Resolution showMuatNaikImej() {

        LOG.info("------showMuatNaikImej----");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/muat_naik_imej.jsp").addParameter("tab", "true");
    }

    public Resolution refresh() {

        LOG.info("------refresh----");
        tarikhSiasatan = null;
        sempadanUtaraNoLot = null;
        sempadanUtaraKegunaan = null;
        sempadanSelatanNoLot = null;
        sempadanSelatanKegunaan = null;
        sempadanTimurNoLot = null;
        sempadanTimurKegunaan = null;
        sempadanBaratNoLot = null;
        sempadanBaratKegunaan = null;
        adaBangunan = null;
        jenisBangunan = null;
        keadaanBangunan = null;

        /* new request : if Isi Semula = delete maklumat bangunan */
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            LOG.info("=======idPermohonan=====" + idPermohonan);
            strService.deleteMaklumatBgnn(idPermohonan);
        }

        return new JSP("strata/Ruang_Udara/maklumat_Bangunan.jsp").addParameter("tab", "true");
    }

    public Resolution showMuatNaikImejReadOnly() {

        LOG.info("------showMuatNaikImejReadOnly----");
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/Ruang_Udara/muat_naik_imej.jsp").addParameter("tab", "true");
    }

    public Resolution reload() {
        LOG.info("------reload----");
        return new RedirectResolution(MaklumatBangunanActionBean.class, "showMuatNaikImej");
    }

    public Resolution showRefresh() {
        LOG.info("------showRefresh----");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/muat_naik_imej.jsp").addParameter("popup", "true");
    }

    public Resolution simpanBangunan1() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        String error = "";
        String msg = "";

        permohonan = permohonanService.findById(idPermohonan);
        laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);

        LOG.info("------laporanTanah----" + laporanTanah);

        if (laporanTanah == null) {

            LOG.info("--if----laporanTanah----" + laporanTanah);

            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            laporanTanah.setInfoAudit(infoAudit);
        } else {
            LOG.info("--else----laporanTanah----" + laporanTanah);

            InfoAudit ia = laporanTanah.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            laporanTanah.setInfoAudit(ia);
        }

        laporanTanah.setSempadanUtaraJenis(sempandanUtaraJenis);
        laporanTanah.setSempadanUtaraNoLot(sempadanUtaraNoLot);
        laporanTanah.setSempadanUtaraKegunaan(sempadanUtaraKegunaan);
        laporanTanah.setSempadanTimurJenis(sempadanSelatanJenis);
        laporanTanah.setSempadanSelatanNoLot(sempadanSelatanNoLot);
        laporanTanah.setSempadanSelatanKegunaan(sempadanSelatanKegunaan);
        laporanTanah.setSempadanSelatanJenis(sempadanSelatanJenis);
        laporanTanah.setSempadanTimurJenis(sempadanTimurJenis);
        laporanTanah.setSempadanTimurNoLot(sempadanTimurNoLot);
        laporanTanah.setSempadanTimurKegunaan(sempadanTimurKegunaan);
        laporanTanah.setSempadanBaratJenis(sempadanBaratJenis);
        laporanTanah.setSempadanBaratNoLot(sempadanBaratNoLot);
        laporanTanah.setSempadanBaratKegunaan(sempadanBaratKegunaan);
        laporanTanah.setAdaBangunan(adaBangunan.charAt(0));
        laporanTanah.setJenisBangunan(jenisBangunan);
        laporanTanah.setKeadaanTanah(keadaanBangunan);
        laporanTanah.setTarikhSiasat(sdf.parse(tarikhSiasatan));
//        laporanTanah.setStrukturTambahanJenis(strukturTambahanJenis);
//        laporanTanah.setStrukturTambahanKedudukan(strukturTambahanKedudukan);

//
//        if ((simpanjln != null) && (simpanlaluan != null) && (!simpanjln.equals("")) && (!simpanlaluan.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur("Simpanan jalan dan simpanan laluan");
//
//        } else if ((simpanjln != null) && (!simpanjln.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanjln);
//        } else if ((simpanlaluan != null) && (!simpanlaluan.equals(""))) {
//            laporanTanah.setStrukturTambahanKedudukanTerlunjur(simpanlaluan);
//
//        }
        //laporanTanah  = new LaporanTanah();
        LOG.info("--before save----");
        strService.SimpanKand(laporanTanah);
        LOG.info("--save----");
        msg = "Maklumat telah berjaya disimpan.";
        addSimpleMessage(msg);
//        return new JSP("strata/Ruang_Udara/maklumat_Bangunan.jsp").addParameter("tab", "true");
        return new RedirectResolution(MaklumatBangunanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }
    //added for delete images in laporan_tanah

    public Resolution hapusImej() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Long folderId = 0L;
        if (permohonan.getFolderDokumen() != null) {
            folderId = permohonan.getFolderDokumen().getFolderId();
        }

        LOG.info("---------------idPermohonan:--------" + idPermohonan);
        Long dokumenId = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));
        LOG.info("---------------idDokumen:--------" + dokumenId);
        dokumen = dokumenDAO.findById(dokumenId);
        String docPath = conf.getProperty("document.path");
        File file = new File(docPath + dokumen.getNamaFizikal());
        file.delete();
        strService.deleteImejLaporanByIdDokumen(dokumenId);
        strService.deleteKandunganFolderByIdDokumen(folderId, dokumenId);
        strService.deleteDokumenCapaianByIdDokumen(dokumenId);
        strService.deleteAll2(dokumen);
        LOG.info("---------------Deleted idDokumen is::--------" + dokumenId);
        String msg = "Imej telah berjaya dipadamkan.";
        addSimpleMessage(msg);
        return new RedirectResolution(MaklumatBangunanActionBean.class, "showMuatNaikImej");
    }

    public Resolution muatNaik() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);

        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());

        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
        if (fileToBeUpload == null) {
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
        } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".JPG") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png") || fileToBeUpload.getFileName().endsWith(".gif"))) {
            LOG.info("----------------fileToBeUpload---else-----------:" + fileToBeUpload.getFileName());
            addSimpleError("Sila pilih fail imej dalam format *.jpg, *.bmp, *.png, *.gif untuk dimuatnaik.");
            return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
        }
        LOG.info("----------------fileToBeUpload--------------:" + fileToBeUpload.getFileName()); //End

        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

        imejLaporan.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejLaporan.setCawangan(peng.getKodCawangan());
        imejLaporan.setDokumen(doc);
        imejLaporan.setLaporanTanah(laporanTanah);
        imejLaporan.setCatatan(catatan);
        LOG.info("LOKASI" + lokasi);
        char c = 0;
        if (conf.getProperty("kodNegeri").equals("04")) {
            if (StringUtils.isNotEmpty(lokasi)) {
                c = (lokasi).charAt(0);
                imejLaporan.setPandanganImej(c);
            }

            imejLaporan.setInfoAudit(ia);
            comm.saveImej(imejLaporan);
            LOG.info("simpanMuatNaik::finish");
            addSimpleMessage("Muat naik fail berjaya.");
        } else { //Code for Negeri 9 add by ida 20092013
            LaporanTanah lt = strService.findByIDMohon(permohonan.getIdPermohonan());
            if (lt != null) {
                if (StringUtils.isNotEmpty(lokasi)) {
                    c = (lokasi).charAt(0);
                    imejLaporan.setPandanganImej(c);
                }

                imejLaporan.setInfoAudit(ia);
                comm.saveImej(imejLaporan);
                LOG.info("simpanMuatNaik::finish");
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                addSimpleError("Muat naik fail tidak berjaya. Sila isikan maklumat pada tab Siasatan Tanah.");

            }
        }
        rehydrate();
        return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");

    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
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
        String idPihak = getContext().getRequest().getParameter("idPihak");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Ruang_Udara/muatnaik.jsp").addParameter("popup", "true");
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
}

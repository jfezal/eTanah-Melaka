/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;
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
 * @author nordiyana
 */
@UrlBinding("/pengambilan/ImejLaporanTanah")
public class ImejLaporanTanahActionBean extends BasicTabActionBean{
    
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
    private static final Logger LOG = Logger.getLogger(ImejLaporanTanahTerdahuluActionBean.class);
    private Dokumen dokumen;
    private FileBean fileToBeUpload;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private String strKodGunaRuangUdara;
    private List<KodKegunaanRuangUdara> senaraikodGunaRuangUdara;
    private String tarikhSiasatan;
    private String idSblm;
    private Permohonan permohonanSebelum;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String catatan = "";
    String msg;
    String _PAGE_IMEJ_POP_ = "pengambilan/negerisembilan/penarikanbalik/muat_naik_imej_pop.jsp";
    String _PopUp_ = "pembangunan/common/muatnaik.jsp";
    String _Main = "pengambilan/negerisembilan/penarikanbalik/muat_naik_imej.jsp";

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);
            if (laporanTanah != null) {

                    String dokumenPath = conf.getProperty("document.path");
                    imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonanSebelum.getFolderDokumen().getFolderId() + File.separator;
                    LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
                    FolderDokumen fd = permohonan.getFolderDokumen();

                    String docPath = conf.getProperty("document.path");

                    senaraiImejLaporan = new ArrayList<ImejLaporan>();
                    senaraiImejLaporan = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan());
                    System.out.println("###### senaraiImejLaporan getsize :" + senaraiImejLaporan.size());
                    System.out.println("idPermohonan" + idPermohonan);
                    System.out.println("permohonanSebelum " + permohonanSebelum);
                } else {
                    keadaanBangunan = "KEKAL";
                }
        }

    
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/penarikanbalik/maklumat_naik_imej.jsp").addParameter("tab", "true");
    }

    public Resolution showMuatNaikImej() {
        LOG.info("###### showMuatNaikImej");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        LOG.info("###### showMuatNaikImej---idPermohonan-----:" + idSblm);
        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idSblm);
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
        return new JSP(_Main).addParameter("tab", "true");
    }

    public Resolution showRefresh() {
        LOG.info("###### showRefresh");
        getContext().getRequest().setAttribute("cheackingLT", Boolean.TRUE);
        rehydrate();
        return new RedirectResolution(ImejLaporanTanahTerdahuluActionBean.class, "showMuatNaikImej");
    }


    public Resolution muatNaik() throws Exception {
        Logger.getLogger(ImejLaporanTanahTerdahuluActionBean.class).info("simpanMuatNaik::start");
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
                    }

                    LOG.info("simpanMuatNaik::finish");
                    addSimpleMessage("Muat naik fail berjaya.");
                } catch (Exception ex) {
                    Logger.getLogger(ImejLaporanTanahTerdahuluActionBean.class).error(ex);
                }
            } else {
                addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            }
        }
        return new JSP(_PopUp_).addParameter("popup", "true");
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
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        String idDok = getContext().getRequest().getParameter("idDokumen");
        LOG.info("###### viewImejPopup idPermohonan : " + idSblm);
        LOG.info("###### viewImejPopup idDok : " + idDok);

        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idSblm);
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

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }
    
}

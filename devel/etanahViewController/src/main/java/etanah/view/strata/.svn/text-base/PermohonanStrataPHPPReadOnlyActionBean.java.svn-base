/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodHartaBersamaDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.ImejLaporanStrata;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodHartaBersama;
import etanah.model.KodNegeri;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanStrata;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPihakKepentinganService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/permohonanStrata_PHPPRedonly")
public class PermohonanStrataPHPPReadOnlyActionBean extends BasicTabActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private Permohonan permohonan;
    private PermohonanStrata pemilik;
    private BangunanTingkat bangunanTingkat;
    private String pengurusanJenisPengenalan;
    private String pengurusanKodNegeri;
    private String negeri;
    private KodCawangan cawangan;
    private List<PermohonanBangunan> pBangunanL;
    private String idPermohonan;
    private List<BangunanTingkat> petakL;
    private int bil_bgnn;
    private int bil_petak;
    private static final Logger LOG = Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class);
    private String perakuanKosRendahTarikhKeluar;
    private String cfTarikhKeluar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Dokumen> listdokumen;
    private Pengguna pguna;
    private String format;
    private Long idDokumen;
    private Date tarikhMasuk;
    private SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
    private List<ImejLaporanStrata> utaraDoc = new ArrayList();
    private List<ImejLaporanStrata> selatanDoc = new ArrayList();
    private List<ImejLaporanStrata> timurDoc = new ArrayList();
    private List<ImejLaporanStrata> baratDoc = new ArrayList();
    DateFormat formatter;
    @Inject
    DokumenService dokumenService;
    ImejLaporanStrata utara;
    ImejLaporanStrata barat;
    ImejLaporanStrata timur;
    ImejLaporanStrata selatan;
    Dokumen dokumen;
    FileBean fileToBeUpload;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    CommonService comm;
    //added by murali
    KodHartaBersamaDAO kodHartaBersamaDAO;
    List<HakmilikPihakBerkepentingan> list;
    List<PermohonanStrata> mohonStrata;
    ImejLaporanStrata imejStrata = new ImejLaporanStrata();
    String catatan = "";
    private List<SenaraiKemudahan> listKemudahan = new ArrayList();
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    String mhnStrataKemudahan = "";
    private List listHarga = new ArrayList();
    String imageFolderPath;
    private List<KandunganFolder> senaraiKandungan;
    private boolean checkByidSblm = false;
    //added by murali
    private KodHartaBersama kodHartaBersama;
    private List<KodHartaBersama> senaraikodHartaBersama;
    private String catatan1;
    //added
    private Date tarikhSiasatan;
    private String laporanLokasi;
    private char laporanUntukKediaman;
    private char laporanUntukPerniagaan;
    private char laporanUntukPejabat;
    private String laporanKegunaanLain;
    private char laporanKosRendah;
    private String cfNoSijil;
    private String laporankeadaanTanah;
    private Integer laporanBilBangunan;
    private Integer laporanBilPetak;
    private Integer laporanBilBangunanProvisional;
    private Integer laporanBilPetakProvisional;
    private String laporanBandarTerdekat;
    //added by murali
    private LaporanTanah laporanTanah;
    private String laporanBilPetakTanah;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private String gunaBarat;
    private String gunaTimur;
    private String gunaSelatan;
    private String gunaUtara;
    private String lotBarat;
    private String lotTimur;
    private String lotSelatan;
    private String lotUtara;
    private List<ImejLaporan> senaraiImejLaporan;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private String strIdMh;

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getLaporanBandarTerdekat() {
        return laporanBandarTerdekat;
    }

    public void setLaporanBandarTerdekat(String laporanBandarTerdekat) {
        this.laporanBandarTerdekat = laporanBandarTerdekat;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public KodHartaBersama getKodHartaBersama() {
        return kodHartaBersama;
    }

    public void setKodHartaBersama(KodHartaBersama kodHartaBersama) {
        this.kodHartaBersama = kodHartaBersama;
    }

    public List<KodHartaBersama> getSenaraikodHartaBersama() {
        return senaraikodHartaBersama;
    }

    public void setSenaraikodHartaBersama(List<KodHartaBersama> senaraikodHartaBersama) {
        this.senaraikodHartaBersama = senaraikodHartaBersama;
    }

    public boolean isCheckByidSblm() {
        return checkByidSblm;
    }

    public void setCheckByidSblm(boolean checkByidSblm) {
        this.checkByidSblm = checkByidSblm;
    }

    public List<ImejLaporanStrata> getBaratDoc() {
        return baratDoc;
    }

    public void setBaratDoc(List<ImejLaporanStrata> baratDoc) {
        this.baratDoc = baratDoc;
    }

    public List<ImejLaporanStrata> getSelatanDoc() {
        return selatanDoc;
    }

    public void setSelatanDoc(List<ImejLaporanStrata> selatanDoc) {
        this.selatanDoc = selatanDoc;
    }

    public List<ImejLaporanStrata> getTimurDoc() {
        return timurDoc;
    }

    public void setTimurDoc(List<ImejLaporanStrata> timurDoc) {
        this.timurDoc = timurDoc;
    }

    public List<ImejLaporanStrata> getUtaraDoc() {
        return utaraDoc;
    }

    public void setUtaraDoc(List<ImejLaporanStrata> utaraDoc) {
        this.utaraDoc = utaraDoc;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public ImejLaporanStrata getBarat() {
        return barat;
    }

    public void setBarat(ImejLaporanStrata barat) {
        this.barat = barat;
    }

    public ImejLaporanStrata getImejStrata() {
        return imejStrata;
    }

    public void setImejStrata(ImejLaporanStrata imejStrata) {
        this.imejStrata = imejStrata;
    }

    public ImejLaporanStrata getSelatan() {
        return selatan;
    }

    public void setSelatan(ImejLaporanStrata selatan) {
        this.selatan = selatan;
    }

    public ImejLaporanStrata getTimur() {
        return timur;
    }

    public void setTimur(ImejLaporanStrata timur) {
        this.timur = timur;
    }

    public ImejLaporanStrata getUtara() {
        return utara;
    }

    public void setUtara(ImejLaporanStrata utara) {
        this.utara = utara;
    }

    public List<HakmilikPihakBerkepentingan> getList() {
        return list;
    }

    public void setList(List<HakmilikPihakBerkepentingan> list) {
        this.list = list;
    }

    public List<PermohonanStrata> getMohonStrata() {
        return mohonStrata;
    }

    public void setMohonStrata(List<PermohonanStrata> mohonStrata) {
        this.mohonStrata = mohonStrata;
    }

    public BangunanTingkat getBangunanTingkat() {
        return bangunanTingkat;
    }

    public void setBangunanTingkat(BangunanTingkat bangunanTingkat) {
        this.bangunanTingkat = bangunanTingkat;
    }

    public BangunanTingkatDAO getBangunanTingkatDAO() {
        return bangunanTingkatDAO;
    }

    public void setBangunanTingkatDAO(BangunanTingkatDAO bangunanTingkatDAO) {
        this.bangunanTingkatDAO = bangunanTingkatDAO;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public PembangunanService getDevService() {
        return devService;
    }

    public void setDevService(PembangunanService devService) {
        this.devService = devService;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public PermohonanBangunanDAO getPermohonanBangunanDAO() {
        return permohonanBangunanDAO;
    }

    public void setPermohonanBangunanDAO(PermohonanBangunanDAO permohonanBangunanDAO) {
        this.permohonanBangunanDAO = permohonanBangunanDAO;
    }

    public PermohonanStrataDAO getPermohonanStrataDAO() {
        return permohonanStrataDAO;
    }

    public void setPermohonanStrataDAO(PermohonanStrataDAO permohonanStrataDAO) {
        this.permohonanStrataDAO = permohonanStrataDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    public SimpleDateFormat getSd() {
        return sd;
    }

    public void setSd(SimpleDateFormat sd) {
        this.sd = sd;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<Dokumen> getListdokumen() {
        return listdokumen;
    }

    public void setListdokumen(List<Dokumen> listdokumen) {
        this.listdokumen = listdokumen;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getCfTarikhKeluar() {
        return cfTarikhKeluar;
    }

    public void setCfTarikhKeluar(String cfTarikhKeluar) {
        this.cfTarikhKeluar = cfTarikhKeluar;
    }

    public String getPerakuanKosRendahTarikhKeluar() {
        return perakuanKosRendahTarikhKeluar;
    }

    public void setPerakuanKosRendahTarikhKeluar(String perakuanKosRendahTarikhKeluar) {
        this.perakuanKosRendahTarikhKeluar = perakuanKosRendahTarikhKeluar;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public int getBil_petak() {
        return bil_petak;
    }

    public void setBil_petak(int bil_petak) {
        this.bil_petak = bil_petak;
    }

    public int getBil_bgnn() {
        return bil_bgnn;
    }

    public void setBil_bgnn(int bil_bgnn) {
        this.bil_bgnn = bil_bgnn;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public String getPengurusanJenisPengenalan() {
        return pengurusanJenisPengenalan;
    }

    public void setPengurusanJenisPengenalan(String pengurusanJenisPengenalan) {
        this.pengurusanJenisPengenalan = pengurusanJenisPengenalan;
    }

    public String getPengurusanKodNegeri() {
        return pengurusanKodNegeri;
    }

    public void setPengurusanKodNegeri(String pengurusanKodNegeri) {
        this.pengurusanKodNegeri = pengurusanKodNegeri;
    }

    public PermohonanStrata getPemilik() {
        return pemilik;
    }

    public void setPemilik(PermohonanStrata pemilik) {
        this.pemilik = pemilik;
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

    public List<BangunanTingkat> getPetakL() {
        return petakL;
    }

    public void setPetakL(List<BangunanTingkat> petakL) {
        this.petakL = petakL;
    }

    public String getCfNoSijil() {
        return cfNoSijil;
    }

    public void setCfNoSijil(String cfNoSijil) {
        this.cfNoSijil = cfNoSijil;
    }

    public String getLaporanKegunaanLain() {
        return laporanKegunaanLain;
    }

    public void setLaporanKegunaanLain(String laporanKegunaanLain) {
        this.laporanKegunaanLain = laporanKegunaanLain;
    }

    public String getLaporanLokasi() {
        return laporanLokasi;
    }

    public void setLaporanLokasi(String laporanLokasi) {
        this.laporanLokasi = laporanLokasi;
    }

    public String getLaporankeadaanTanah() {
        return laporankeadaanTanah;
    }

    public void setLaporankeadaanTanah(String laporankeadaanTanah) {
        this.laporankeadaanTanah = laporankeadaanTanah;
    }

    public Date getTarikhSiasatan() {
        return tarikhSiasatan;
    }

    public void setTarikhSiasatan(Date tarikhSiasatan) {
        this.tarikhSiasatan = tarikhSiasatan;
    }

    public int getLaporanBilBangunan() {
        return laporanBilBangunan;
    }

    public void setLaporanBilBangunan(int laporanBilBangunan) {
        this.laporanBilBangunan = laporanBilBangunan;
    }

    public int getLaporanBilBangunanProvisional() {
        return laporanBilBangunanProvisional;
    }

    public void setLaporanBilBangunanProvisional(int laporanBilBangunanProvisional) {
        this.laporanBilBangunanProvisional = laporanBilBangunanProvisional;
    }

    public int getLaporanBilPetak() {
        return laporanBilPetak;
    }

    public void setLaporanBilPetak(int laporanBilPetak) {
        this.laporanBilPetak = laporanBilPetak;
    }

    public int getLaporanBilPetakProvisional() {
        return laporanBilPetakProvisional;
    }

    public void setLaporanBilPetakProvisional(int laporanBilPetakProvisional) {
        this.laporanBilPetakProvisional = laporanBilPetakProvisional;
    }

    public char getLaporanKosRendah() {
        return laporanKosRendah;
    }

    public void setLaporanKosRendah(char laporanKosRendah) {
        this.laporanKosRendah = laporanKosRendah;
    }

    public char getLaporanUntukKediaman() {
        return laporanUntukKediaman;
    }

    public void setLaporanUntukKediaman(char laporanUntukKediaman) {
        this.laporanUntukKediaman = laporanUntukKediaman;
    }

    public char getLaporanUntukPejabat() {
        return laporanUntukPejabat;
    }

    public void setLaporanUntukPejabat(char laporanUntukPejabat) {
        this.laporanUntukPejabat = laporanUntukPejabat;
    }

    public char getLaporanUntukPerniagaan() {
        return laporanUntukPerniagaan;
    }

    public void setLaporanUntukPerniagaan(char laporanUntukPerniagaan) {
        this.laporanUntukPerniagaan = laporanUntukPerniagaan;
    }

    public String getLaporanBilPetakTanah() {
        return laporanBilPetakTanah;
    }

    public void setLaporanBilPetakTanah(String laporanBilPetakTanah) {
        this.laporanBilPetakTanah = laporanBilPetakTanah;
    }

    
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_pemilik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        checkByidSblm = false;        
        return new JSP("strata/pbbm/laporan_tanah_PHPP.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        tarikhMasuk = new java.util.Date();
        formatter = new SimpleDateFormat("dd/mm/yyyy");
        formatter.format(tarikhMasuk);
        listdokumen = dokumenService.getDokumenByIdPenguna(pguna.getNama(), tarikhMasuk);
        if (pemilik != null) {
            utaraDoc = comm.getListDoc('U', pemilik.getIdStrata());
            selatanDoc = comm.getListDoc('S', pemilik.getIdStrata());
            timurDoc = comm.getListDoc('T', pemilik.getIdStrata());
            baratDoc = comm.getListDoc('B', pemilik.getIdStrata());
        }
        return new JSP("strata/pbbm/laporan_sempadan.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution showForm4() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pbbm/terimalaporan_tanah_PHPP.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        return new JSP("strata/pbbm/terimalaporan_sempadan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        permohonan = permohonanDAO.findById(idPermohonan);
        pemilik = strService.findID(idPermohonan);
        if (list == null) {
            error = "Sila isikan maklumat pemilik terlebih dahulu";
            LOG.info(error);
            return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        } else {
            if (pemilik != null) {
                if ((pemilik.getPerakuanKosRendahTarikhKeluar() != null) && (pemilik.getCfTarikhKeluar() != null)) {
                    perakuanKosRendahTarikhKeluar = sdf.format(pemilik.getPerakuanKosRendahTarikhKeluar());
                    cfTarikhKeluar = sdf.format(pemilik.getCfTarikhKeluar());
                }
            }
            return new JSP("strata/kos_rendah/maklumat_bangunan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution showForm7() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        checkByidSblm = true;
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_pemilik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonStrata = null;
        Long idMh1 = 0L;
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new Date());
        LOG.info("IDPERMOHONAN: " + idPermohonan);
        if (idPermohonan != null) {
            String[] name = {"permohonan"};
            Object[] object = {permohonan};
            permohonan = permohonanDAO.findById(idPermohonan);
            if (checkByidSblm) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonan = permohonan.getPermohonanSebelum();
                }
            }
            if (!permohonan.getSenaraiHakmilik().isEmpty()) {
                list = hakmilikPihakKepentinganService.findPihakActiveByHakmilik((permohonan.getSenaraiHakmilik().get(0)).getHakmilik());
            }

            //pemilik = strService.findID(permohonan.getIdPermohonan());            
            strIdMh = getContext().getRequest().getParameter("idMh");
            LOG.info("---------------strIdMh:--------" + strIdMh);
            if (strIdMh == null) {
                idMh1 = permohonan.getSenaraiHakmilik().get(0).getId();
            } else {
                idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
            }
            LOG.info("---------------idMh1:--------" + idMh1);
            pemilik = strService.findIDByidMH(idPermohonan, idMh1);
            LOG.info("-----------Permohonan Strata:--------" + pemilik);

            listKemudahan.clear();
            //mohonHakmilik = strService.findMohonHakmilik(permohonan.getIdPermohonan());           
            mohonHakmilik = strService.findByIdHakmilikIdPermohonan(idPermohonan, permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            LOG.info("-----------mohonHakmilik:--------" + mohonHakmilik);

            if (pemilik == null) {
                pemilik = new PermohonanStrata();
                if (mohonHakmilik != null) {
                    pemilik.setLaporanLokasi(mohonHakmilik.getHakmilik().getLokasi());
                } else {
                    String error = "Tiada Id Hakmilik";
                    LOG.info(error);
                }

                pemilik.setPermohonan(permohonan);
                HakmilikPermohonan mohonHakmilik1 = new HakmilikPermohonan();
                mohonHakmilik1 = hakmilikPermohonanDAO.findById(idMh1);
                pemilik.setHakmilikPermohonan(mohonHakmilik1);
                pemilik.setCawangan(permohonan.getCawangan());
                pemilik.setInfoAudit(infoAudit);
                pemilik.setNama("-");
                pemilik.setPemilikNama("-");
                strService.simpanPemilik(pemilik);
            }

            // from kod_harta_bersama
            senaraikodHartaBersama = new ArrayList<KodHartaBersama>();
            senaraikodHartaBersama = strService.findHartaBersamaByNama();

            // for catatan1
            catatan1 = pemilik.getLaporanKemudahan11();

            //added            
            //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
            laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
            LOG.debug("----------laporanTanah----------::" + laporanTanah);
            if (laporanTanah != null) {
                tarikhSiasatan = laporanTanah.getTarikhSiasat();
                gunaBarat = laporanTanah.getSempadanBaratKegunaan();
                gunaTimur = laporanTanah.getSempadanTimurKegunaan();
                gunaSelatan = laporanTanah.getSempadanSelatanKegunaan();
                gunaUtara = laporanTanah.getSempadanUtaraKegunaan();
                lotBarat = laporanTanah.getSempadanBaratNoLot();
                lotTimur = laporanTanah.getSempadanTimurNoLot();
                lotSelatan = laporanTanah.getSempadanSelatanNoLot();
                lotUtara = laporanTanah.getSempadanUtaraNoLot();
                LOG.debug("----------laporanTanah-----tarikhSiasatan-----::" + tarikhSiasatan);
            }
            laporanLokasi = pemilik.getLaporanLokasi();
            laporanBandarTerdekat = pemilik.getLaporanBandarTerdekat();
            if (pemilik.getLaporanUntukKediaman() != null) {
                laporanUntukKediaman = pemilik.getLaporanUntukKediaman();
            }
            if (pemilik.getLaporanUntukPerniagaan() != null) {
                laporanUntukPerniagaan = pemilik.getLaporanUntukPerniagaan();
            }
            if (pemilik.getLaporanUntukPejabat() != null) {
                laporanUntukPejabat = pemilik.getLaporanUntukPejabat();
            }
            laporanKegunaanLain = pemilik.getLaporanKegunaanLain();
            if (pemilik.getLaporanKosRendah() != null) {
                laporanKosRendah = pemilik.getLaporanKosRendah();
            }
            cfNoSijil = pemilik.getCfNoSijil();
            laporankeadaanTanah = pemilik.getLaporankeadaanTanah();
            if (pemilik.getLaporanBilBangunan() != null) {
                laporanBilBangunan = pemilik.getLaporanBilBangunan();
            }
            if (pemilik.getLaporanUntukKediaman() != null) {
                laporanBilPetak = pemilik.getLaporanBilPetak();
            }
            if (pemilik.getLaporanBilBangunanProvisional() != null) {
                laporanBilBangunanProvisional = pemilik.getLaporanBilBangunanProvisional();
            }
            if (pemilik.getLaporanBilPetakProvisional() != null) {
                laporanBilPetakProvisional = pemilik.getLaporanBilPetakProvisional();
            }
            if (pemilik.getLaporanBilPetakTanah() != null) {
                laporanBilPetakTanah = pemilik.getLaporanBilPetakTanah().toString();
            }

        }

        // added by Murali  Getting Image Path
        String dokumenPath = conf.getProperty("document.path");
        imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
        LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
        FolderDokumen fd = permohonan.getFolderDokumen();
        String docPath = conf.getProperty("document.path");
        senaraiImejLaporan = new ArrayList<ImejLaporan>();
        senaraiImejLaporan = strService.findImejlaporan(laporanTanah.getIdLaporan());

        senaraiKandungan = new ArrayList<KandunganFolder>();
        if (fd != null) {
            List<KandunganFolder> senarai = fd.getSenaraiKandungan();

            for (KandunganFolder kandunganFolder : senarai) {
                if (kandunganFolder == null || kandunganFolder.getDokumen() == null) {
                    continue;
                }
                Dokumen d = kandunganFolder.getDokumen();
                if (d.getFormat() != null && d.getFormat().startsWith("image")) {
                    senaraiKandungan.add(kandunganFolder);
                }
            }
        }

        //to get mohonhakmilik list
        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        //senaraiHakmilikPermohonan = strService.findIdHakmilikSort(idPermohonan);
        LOG.info("----------HakmilikPermohonan List size--------------: " + senaraiHakmilikPermohonan.size());
    }

    public Resolution simpanPemilik() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";

        if (pemilik == null) {
            error = "Sila isikan maklumat pemilik yang mandatori";
            return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        } else {
            KodNegeri kodNegeri = new KodNegeri();
            kodNegeri.setKod(negeri);
            cawangan = permohonan.getCawangan();
            pemilik.setPermohonan(permohonan);
            pemilik.setInfoAudit(infoAudit);
            pemilik.setCawangan(cawangan);
            pemilik.setNegeri(kodNegeri);
            strService.simpanPemilik(pemilik);
            msg = "Maklumat telah berjaya disimpan.";
        }
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution updatePemilik() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";
        pemilik = strService.findID(idPermohonan);

        KodNegeri kodNegeri = new KodNegeri();
        kodNegeri.setKod(negeri);
        cawangan = permohonan.getCawangan();
        pemilik.setPermohonan(permohonan);
        pemilik.setInfoAudit(infoAudit);
        pemilik.setCawangan(cawangan);
        pemilik.setNegeri(kodNegeri);
        simpanListKemudahan();
        strService.updatePemilik(pemilik);
        msg = "Maklumat telah berjaya dikemaskini.";

        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution addKemudahan() {

        LOG.info("Size : " + listKemudahan.size());
        return new JSP("strata/common/kemasukan_kemudahan.jsp").addParameter("popup", "true");
    }

    public Resolution updateListKemudahan() {
        String error = null;
        String msg = null;
        String index = (String) getContext().getRequest().getParameter("index");
        listKemudahan.remove(Integer.parseInt(index) - 1);
        pemilik.setLaporanKemudahan1("");
        pemilik.setLaporanKemudahan2("");
        pemilik.setLaporanKemudahan3("");
        pemilik.setLaporanKemudahan4("");
        pemilik.setLaporanKemudahan5("");
        pemilik.setLaporanKemudahan6("");
        pemilik.setLaporanKemudahan7("");
        pemilik.setLaporanKemudahan8("");
        pemilik.setLaporanKemudahan9("");
        pemilik.setLaporanKemudahan10("");
        pemilik.setLaporanKemudahan11("");
        pemilik.setLaporanKemudahan12("");

        simpanListKemudahan();
        rehydrate();
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public void simpanListKemudahan() {
        for (int i = 0; i < listKemudahan.size(); i++) {
            SenaraiKemudahan sk = new SenaraiKemudahan();
            sk = listKemudahan.get(i);
            switch (i + 1) {
                case 1:
                    pemilik.setLaporanKemudahan1(sk.getKemudahan());
                    break;
                case 2:
                    pemilik.setLaporanKemudahan2(sk.getKemudahan());
                    break;
                case 3:
                    pemilik.setLaporanKemudahan3(sk.getKemudahan());
                    break;
                case 4:
                    pemilik.setLaporanKemudahan4(sk.getKemudahan());
                    break;
                case 5:
                    pemilik.setLaporanKemudahan5(sk.getKemudahan());
                    break;
                case 6:
                    pemilik.setLaporanKemudahan6(sk.getKemudahan());
                    break;
                case 7:
                    pemilik.setLaporanKemudahan7(sk.getKemudahan());
                    break;
                case 8:
                    pemilik.setLaporanKemudahan8(sk.getKemudahan());
                    break;
                case 9:
                    pemilik.setLaporanKemudahan9(sk.getKemudahan());
                    break;
                case 10:
                    pemilik.setLaporanKemudahan10(sk.getKemudahan());
                    break;
                case 11:
                    pemilik.setLaporanKemudahan11(sk.getKemudahan());
                    break;
            }

        }
        strService.simpanPemilik(pemilik);
    }

    public Resolution tambahKemudahan() {
        String error = "";
        String msg = "";
        SenaraiKemudahan sk = new SenaraiKemudahan();
        sk.setKemudahan(mhnStrataKemudahan);
        listKemudahan.add(sk);
        simpanListKemudahan();
        msg = "Maklumat berjaya dismpan";

        addSimpleMessage("Maklumat berjaya ");
        rehydrate();
        return new JSP("strata/pbbm/laporan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution reload() {
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2");
    }

    public Resolution reload2() {
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm3");
    }

    public Resolution simpanLaporanTanah() {

        LOG.info("---------simpanLaporanTanah:--------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());


        //pemilik = strService.findID(idPermohonan);
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        pemilik = strService.findIDByidMH(idPermohonan, idMh1);
        LOG.info("-----------Permohonan Strata:--------" + pemilik);
        if (pemilik == null) {
            pemilik = new PermohonanStrata();
        }
        cawangan = permohonan.getCawangan();
        pemilik.setPermohonan(permohonan);
        pemilik.setCawangan(cawangan);
        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setTarikhLaporan(new java.util.Date());
        //added
        HakmilikPermohonan mohonHakmilik1 = new HakmilikPermohonan();
        mohonHakmilik1 = hakmilikPermohonanDAO.findById(idMh1);
        pemilik.setHakmilikPermohonan(mohonHakmilik1);
        pemilik.setLaporanLokasi(laporanLokasi);
        pemilik.setLaporanUntukKediaman(laporanUntukKediaman);
        pemilik.setLaporanUntukPerniagaan(laporanUntukPerniagaan);
        pemilik.setLaporanUntukPejabat(laporanUntukPejabat);
        pemilik.setLaporanKegunaanLain(laporanKegunaanLain);
        pemilik.setLaporanKosRendah(laporanKosRendah);
        pemilik.setCfNoSijil(cfNoSijil);
        pemilik.setLaporanBandarTerdekat(laporanBandarTerdekat);
        pemilik.setLaporankeadaanTanah(laporankeadaanTanah);
        pemilik.setLaporanBilBangunan(laporanBilBangunan);
        pemilik.setLaporanBilPetak(laporanBilPetak);
        pemilik.setLaporanBilBangunanProvisional(laporanBilBangunanProvisional);
        pemilik.setLaporanBilPetakProvisional(laporanBilPetakProvisional);
        if (laporanBilPetakTanah != null) {
            pemilik.setLaporanBilPetakTanah(Integer.parseInt(laporanBilPetakTanah));
        }

        //end
        strService.SimpanLaporanTanah(pemilik);


        //Saving in Mohon_Strata

        LOG.debug("----------Saving in Mohon Strata----------::");

        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
            KodHartaBersama kodHartaBersama = new KodHartaBersama();
            kodHartaBersama = senaraikodHartaBersama.get(i);
            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
            if (i == 0) {
                pemilik.setLaporanKemudahan1(kod);
            } else if (i == 1) {
                pemilik.setLaporanKemudahan2(kod);
            } else if (i == 2) {
                pemilik.setLaporanKemudahan3(kod);
            } else if (i == 3) {
                pemilik.setLaporanKemudahan4(kod);
            } else if (i == 4) {
                pemilik.setLaporanKemudahan5(kod);
            } else if (i == 5) {
                pemilik.setLaporanKemudahan6(kod);
            } else if (i == 6) {
                pemilik.setLaporanKemudahan7(kod);
            } else if (i == 7) {
                pemilik.setLaporanKemudahan8(kod);
            } else if (i == 8) {
                pemilik.setLaporanKemudahan9(kod);
            } else if (i == 9) {
                pemilik.setLaporanKemudahan10(kod);
                pemilik.setLaporanKemudahan11(catatan1);
            }
            strService.SimpanLaporanTanah(pemilik);
        }

        /* Saving in Mohon_lapor_tanah
        added by murali @NS 17-07-2012 */
        LOG.debug("----------Saving in mohon_lapor_tanah----------::");
        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        LOG.debug("----------laporanTanah----------::" + laporanTanah);
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
        }
        laporanTanah.setPermohonan(permohonan);
        laporanTanah.setHakmilikPermohonan(mohonHakmilik1);
        laporanTanah.setInfoAudit(infoAudit);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        //sempadan
        laporanTanah.setSempadanBaratNoLot(lotBarat);
        laporanTanah.setSempadanUtaraNoLot(lotUtara);
        laporanTanah.setSempadanTimurNoLot(lotTimur);
        laporanTanah.setSempadanSelatanNoLot(lotSelatan);
        laporanTanah.setSempadanBaratKegunaan(gunaBarat);
        laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
        laporanTanah.setSempadanTimurKegunaan(gunaTimur);
        laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
        strService.simpanLaporan(laporanTanah);

        msg = "Maklumat telah berjaya disimpan.";
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution updateLaporanTanah() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());


        //pemilik = strService.findID(idPermohonan);
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        pemilik = strService.findIDByidMH(idPermohonan, idMh1);
        LOG.info("-----------Permohonan Strata:--------" + pemilik);
        cawangan = permohonan.getCawangan();
        HakmilikPermohonan mohonHakmilik1 = new HakmilikPermohonan();
        mohonHakmilik1 = hakmilikPermohonanDAO.findById(idMh1);
        pemilik.setHakmilikPermohonan(mohonHakmilik1);
        pemilik.setPermohonan(permohonan);
        pemilik.setCawangan(cawangan);
        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setTarikhLaporan(new java.util.Date());
        //added
        pemilik.setLaporanLokasi(laporanLokasi);
        pemilik.setLaporanUntukKediaman(laporanUntukKediaman);
        pemilik.setLaporanUntukPerniagaan(laporanUntukPerniagaan);
        pemilik.setLaporanUntukPejabat(laporanUntukPejabat);
        pemilik.setLaporanKegunaanLain(laporanKegunaanLain);
        pemilik.setLaporanKosRendah(laporanKosRendah);
        pemilik.setCfNoSijil(cfNoSijil);
        pemilik.setLaporanBandarTerdekat(laporanBandarTerdekat);
        pemilik.setLaporankeadaanTanah(laporankeadaanTanah);
        pemilik.setLaporanBilBangunan(laporanBilBangunan);
        pemilik.setLaporanBilPetak(laporanBilPetak);
        pemilik.setLaporanBilBangunanProvisional(laporanBilBangunanProvisional);
        pemilik.setLaporanBilPetakProvisional(laporanBilPetakProvisional);
        pemilik.setLaporanBilPetakTanah(Integer.parseInt(laporanBilPetakTanah));
        simpanListKemudahan();
        strService.updateLaporanTanah(pemilik);

        //Saving in Mohon_Strata

        LOG.debug("----------Saving in Mohon Strata----------::");

        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
            KodHartaBersama kodHartaBersama = new KodHartaBersama();
            kodHartaBersama = senaraikodHartaBersama.get(i);
            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
            if (i == 0) {
                pemilik.setLaporanKemudahan1(kod);
            } else if (i == 1) {
                pemilik.setLaporanKemudahan2(kod);
            } else if (i == 2) {
                pemilik.setLaporanKemudahan3(kod);
            } else if (i == 3) {
                pemilik.setLaporanKemudahan4(kod);
            } else if (i == 4) {
                pemilik.setLaporanKemudahan5(kod);
            } else if (i == 5) {
                pemilik.setLaporanKemudahan6(kod);
            } else if (i == 6) {
                pemilik.setLaporanKemudahan7(kod);
            } else if (i == 7) {
                pemilik.setLaporanKemudahan8(kod);
            } else if (i == 8) {
                pemilik.setLaporanKemudahan9(kod);
            } else if (i == 9) {
                pemilik.setLaporanKemudahan10(kod);
                pemilik.setLaporanKemudahan11(catatan1);
            }
            strService.SimpanLaporanTanah(pemilik);
        }

        /* Saving in Mohon_lapor_tanah
        added by murali @NS 17-07-2012 */
        LOG.debug("----------Saving in mohon_lapor_tanah----------::");
        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        LOG.debug("----------laporanTanah----------::" + laporanTanah);
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
        }
        laporanTanah.setPermohonan(permohonan);
        laporanTanah.setHakmilikPermohonan(mohonHakmilik1);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        laporanTanah.setInfoAudit(infoAudit);
        //sempadan
        laporanTanah.setSempadanBaratNoLot(lotBarat);
        laporanTanah.setSempadanUtaraNoLot(lotUtara);
        laporanTanah.setSempadanTimurNoLot(lotTimur);
        laporanTanah.setSempadanSelatanNoLot(lotSelatan);
        laporanTanah.setSempadanBaratKegunaan(gunaBarat);
        laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
        laporanTanah.setSempadanTimurKegunaan(gunaTimur);
        laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
        strService.simpanLaporan(laporanTanah);

        msg = "Maklumat telah berjaya diKemaskini.";

        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution resetForm() {
        LOG.debug("----------Reset Form----------::");
        LOG.info("--clearing laporan tanah Form--:");
        tarikhSiasatan = null;
        laporanLokasi = "";
        laporanUntukKediaman = 'N';
        laporanUntukPerniagaan = 'N';
        laporanUntukPejabat = 'N';
        laporanKegunaanLain = "";
        laporanKosRendah = 'N';
        cfNoSijil = "";
        laporankeadaanTanah = "";
        laporanBandarTerdekat = "";
        laporanBilBangunan = null;
        laporanBilPetak = null;
        laporanBilBangunanProvisional = null;
        laporanBilPetakTanah = null;

        LOG.info("--clearing sempadan Form--:");
        lotUtara = "";
        gunaUtara = "";
        lotSelatan = "";
        gunaSelatan = "";
        lotTimur = "";
        gunaTimur = "";
        lotBarat = "";
        gunaBarat = "";

        //pemilik = strService.findID(permohonan.getIdPermohonan());
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        pemilik = strService.findIDByidMH(idPermohonan, idMh1);
        LOG.info("-----------Permohonan Strata1:--------" + pemilik);
        if ((pemilik != null) && (pemilik.getLaporanBilPetakProvisional() != null)) {
            laporanBilPetakProvisional = null;
        }

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        //pemilik = strService.findID(idPermohonan);
        pemilik = strService.findIDByidMH(idPermohonan, idMh1);
        LOG.info("-----------Permohonan Strata2:--------" + pemilik);
        if (pemilik == null) {
            pemilik = new PermohonanStrata();
        }
        cawangan = permohonan.getCawangan();
        pemilik.setPermohonan(permohonan);
        pemilik.setCawangan(cawangan);
        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setTarikhLaporan(new java.util.Date());
        pemilik.setLaporanLokasi("");
        pemilik.setLaporanUntukKediaman(null);
        pemilik.setLaporanUntukPerniagaan(null);
        pemilik.setLaporanUntukPejabat(null);
        pemilik.setLaporanKegunaanLain("");
        pemilik.setLaporanKosRendah(null);
        pemilik.setCfNoSijil("");
        pemilik.setLaporankeadaanTanah("");
        pemilik.setLaporanBandarTerdekat("");
        pemilik.setLaporanBilBangunan(null);
        pemilik.setLaporanBilPetak(null);
        pemilik.setLaporanBilBangunanProvisional(null);
        pemilik.setLaporanBilPetakProvisional(null);
        pemilik.setLaporanBilPetakTanah(null);
        strService.SimpanLaporanTanah(pemilik);


        //laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);
        LOG.debug("----------laporanTanah----------::" + laporanTanah);
        if (laporanTanah != null) {
            laporanTanah.setTarikhSiasat(null);
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah.setSempadanBaratNoLot(lotBarat);
            laporanTanah.setSempadanUtaraNoLot(lotUtara);
            laporanTanah.setSempadanTimurNoLot(lotTimur);
            laporanTanah.setSempadanSelatanNoLot(lotSelatan);
            laporanTanah.setSempadanBaratKegunaan(gunaBarat);
            laporanTanah.setSempadanUtaraKegunaan(gunaUtara);
            laporanTanah.setSempadanTimurKegunaan(gunaTimur);
            laporanTanah.setSempadanSelatanKegunaan(gunaSelatan);
            strService.simpanLaporan(laporanTanah);
        }

        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
            KodHartaBersama kodHartaBersama = new KodHartaBersama();
            kodHartaBersama = senaraikodHartaBersama.get(i);
            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
            if (i == 0) {
                pemilik.setLaporanKemudahan1(null);
            } else if (i == 1) {
                pemilik.setLaporanKemudahan2(null);
            } else if (i == 2) {
                pemilik.setLaporanKemudahan3(null);
            } else if (i == 3) {
                pemilik.setLaporanKemudahan4(null);
            } else if (i == 4) {
                pemilik.setLaporanKemudahan5(null);
            } else if (i == 5) {
                pemilik.setLaporanKemudahan6(null);
            } else if (i == 6) {
                pemilik.setLaporanKemudahan7(null);
            } else if (i == 7) {
                pemilik.setLaporanKemudahan8(null);
            } else if (i == 8) {
                pemilik.setLaporanKemudahan9(null);
            } else if (i == 9) {
                pemilik.setLaporanKemudahan10(null);
                pemilik.setLaporanKemudahan11("");
            }

            strService.SimpanLaporanTanah(pemilik);
        }        
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2");
    }   

    public Resolution hakdetails() {
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        LOG.info("-------------hakdetails1:--------");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pbbm/terimalaporan_tanah_PHPP.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSempadan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        strService.SimpanSempadan(pemilik);

        msg = "Maklumat telah berjaya disimpan.";
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm3").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution simpanBngn() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setTarikhKemaskini(new java.util.Date());

        pemilik = strService.findID(idPermohonan);

        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
        pemilik.setPerakuanKosRendahTarikhKeluar(sdf.parse(perakuanKosRendahTarikhKeluar));
        strService.SimpanBngnKosRendah(pemilik);

        msg = "Maklumat telah berjaya disimpan.";

        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm6").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution updateBngn() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setTarikhKemaskini(new java.util.Date());

        pemilik = strService.findID(idPermohonan);

        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setCfTarikhKeluar(sdf.parse(cfTarikhKeluar));
        pemilik.setPerakuanKosRendahTarikhKeluar(sdf.parse(perakuanKosRendahTarikhKeluar));
        strService.updateBngnKosRendah(pemilik);

        msg = "Maklumat telah berjaya disimpan.";

        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm6").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution muatNaik() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        //laporanTanah = strService.getLaporanTanahByIdPermohonan(idPermohonan);
        laporanTanah = strService.findLaporanTanahByIdMH(idPermohonan, idMh1);

        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());

        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
        if (fileToBeUpload == null) {
            addSimpleError("Sila pilih fail imej untuk dimuatnaik.");
            return new JSP("strata/pecahPetak/muatnaik.jsp").addParameter("popup", "true");
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
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejLaporan.setPandanganImej(c);
        }

        imejLaporan.setInfoAudit(ia);
        comm.saveImej(imejLaporan);
        LOG.info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        rehydrate();
        return new JSP("strata/pbbm/muatnaik_PHPP.jsp").addParameter("popup", "true");
    }

    public Resolution showRefresh() {
        LOG.info("------showRefresh----");
        Long idMh1 = Long.parseLong(getContext().getRequest().getParameter("idMh"));
        LOG.info("---------------idMh1:--------" + idMh1);
        hakdetails();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/laporan_tanah_PHPP.jsp").addParameter("popup", "true");
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
        return new JSP("strata/pbbm/muatnaik_PHPP.jsp").addParameter("popup", "true");
    }

    public Resolution uploadDoc1() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        return new JSP("strata/muatnaik.jsp").addParameter("popup", "true");
    }

    //added
    public Resolution processimejUpload() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");

        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info(" PARAMETER: " + getContext().getRequest().getParameter("lokasi"));
        String error = "";
        String msg = "";
        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info("simpanMuatNaik::start");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

        //save image in Imageformat

        FileBean fileToBeUpload1 = fileToBeUpload;

        if (fileToBeUpload1 != null) {

            try {
                File dirFile = new File(dokumenPath + "/" + fileToBeUpload1.getFileName());
                fileToBeUpload1.save(dirFile);
            } catch (IOException e) {
                e.getMessage();

            }
        }



        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info("KOD CAWANGAN" + peng.getKodCawangan().getKod());
        imejStrata.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejStrata.setCawangan(peng.getKodCawangan());
        imejStrata.setDokumen(doc);
        imejStrata.setPermohonanStrata(pemilik);
        imejStrata.setCatatan(catatan);
        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info("LOKASI" + lokasi);
        char c = 0;
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejStrata.setPandanganImej(c);
        }

        imejStrata.setInfoAudit(ia);
        comm.saveImej(imejStrata);
        Logger.getLogger(PermohonanStrataPHPPReadOnlyActionBean.class).info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");

        return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true");
    }

    public Resolution muatNaikImej() {

        return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true");
    }

    //added for delete images in laporan_tanah
    public Resolution hapusImej() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
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
        return new RedirectResolution(PermohonanStrataPHPPReadOnlyActionBean.class, "showForm2");
    }

    public List<SenaraiKemudahan> getListKemudahan() {
        return listKemudahan;
    }

    public void setListKemudahan(List<SenaraiKemudahan> listKemudahan) {
        this.listKemudahan = listKemudahan;
    }

    public String getMhnStrataKemudahan() {
        return mhnStrataKemudahan;
    }

    public void setMhnStrataKemudahan(String mhnStrataKemudahan) {
        this.mhnStrataKemudahan = mhnStrataKemudahan;
    }

    public List getListHarga() {
        return listHarga;
    }

    public void setListHarga(List listHarga) {
        this.listHarga = listHarga;
    }

    public String getImageFolderPath() {
        return imageFolderPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getGunaBarat() {
        return gunaBarat;
    }

    public void setGunaBarat(String gunaBarat) {
        this.gunaBarat = gunaBarat;
    }

    public String getGunaSelatan() {
        return gunaSelatan;
    }

    public void setGunaSelatan(String gunaSelatan) {
        this.gunaSelatan = gunaSelatan;
    }

    public String getGunaTimur() {
        return gunaTimur;
    }

    public void setGunaTimur(String gunaTimur) {
        this.gunaTimur = gunaTimur;
    }

    public String getGunaUtara() {
        return gunaUtara;
    }

    public void setGunaUtara(String gunaUtara) {
        this.gunaUtara = gunaUtara;
    }

    public String getLotBarat() {
        return lotBarat;
    }

    public void setLotBarat(String lotBarat) {
        this.lotBarat = lotBarat;
    }

    public String getLotSelatan() {
        return lotSelatan;
    }

    public void setLotSelatan(String lotSelatan) {
        this.lotSelatan = lotSelatan;
    }

    public String getLotTimur() {
        return lotTimur;
    }

    public void setLotTimur(String lotTimur) {
        this.lotTimur = lotTimur;
    }

    public String getLotUtara() {
        return lotUtara;
    }

    public void setLotUtara(String lotUtara) {
        this.lotUtara = lotUtara;
    }

    public List<ImejLaporan> getSenaraiImejLaporan() {
        return senaraiImejLaporan;
    }

    public void setSenaraiImejLaporan(List<ImejLaporan> senaraiImejLaporan) {
        this.senaraiImejLaporan = senaraiImejLaporan;
    }

    public String getStrIdMh() {
        return strIdMh;
    }

    public void setStrIdMh(String strIdMh) {
        this.strIdMh = strIdMh;
    }
    
}

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
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodHartaBersamaDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
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
@UrlBinding("/strata/permohonanStrata")
public class PermohonanStrataActionBean extends BasicTabActionBean {

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
    private static final Logger LOG = Logger.getLogger(PermohonanStrataActionBean.class);
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
    private String kodNegeri;
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
    KodHartaBersamaDAO kodHartaBersamaDAO;
    List<HakmilikPihakBerkepentingan> list;
    List<PermohonanStrata> mohonStrata;
    ImejLaporanStrata imejStrata = new ImejLaporanStrata();
    String catatan = "";
    private List<SenaraiKemudahan> listKemudahan = new ArrayList();
    private List<String> kodHB = new ArrayList();
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    String mhnStrataKemudahan = "";
    private List listHarga = new ArrayList();
    String imageFolderPath;
    private List<KandunganFolder> senaraiKandungan;
    private boolean checkByidSblm = false;
    private KodHartaBersama kodHartaBersama;
    private List<KodHartaBersama> senaraikodHartaBersama;
    private String catatan1;
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
    private LaporanTanah laporanTanah;
    private String laporanBilPetakTanah;
    private String lainLainOnly;
    private String laporanKemudahan1;
    private String laporanKemudahan2;
    private String laporanKemudahan3;
    private String laporanKemudahan4;
    private String laporanKemudahan5;
    private String laporanKemudahan6;
    private String laporanKemudahan7;
    private String laporanKemudahan8;
    private String laporanKemudahan9;
    private String laporanKemudahan10;
    private String laporanKemudahan11;
    private String laporanKemudahan12;
    private String chk;
    private String chk2;
    private boolean hidBlokSem = Boolean.FALSE;
    private boolean hidBlokSem2 = Boolean.FALSE;
    private boolean hidBlokSemB = Boolean.FALSE;

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

    @DefaultHandler
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
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            LOG.info("PBBD -------- " + hidBlokSem2);
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBSS")) {
                hidBlokSemB = true;
            }
            if (permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                hidBlokSem = true;
                hidBlokSemB = true;
            }
            if (permohonan.getKodUrusan().getKod().equals("PBBD") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                hidBlokSem2 = true;

                pBangunanL = strService.findByIDMohonBlok(permohonan.getIdPermohonan());

                if (pBangunanL.size() != 0) {
                    hidBlokSemB = true;
                }
            }

            if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                    List<PermohonanBangunan> blok = strService.findByIDPermohonan1(permohonan.getIdPermohonan());

                    int totalblok = 0;
                    int totalpetak = 0;
                    int totalpetaktanah = 0;
                    String jenis;

                    for (PermohonanBangunan cekblok : blok) {
                        jenis = cekblok.getKodKategoriBangunan().getKod();

                        if (jenis.equals("B")) {
                            totalblok = totalblok + 1;
                            totalpetak = totalpetak + cekblok.getBilanganPetak();
                        }
                        if (jenis.equals("L")) {
                            totalpetaktanah = totalpetaktanah + cekblok.getBilanganPetak();
                        }
                    }
                    
                    LOG.info("chk-----::" + totalblok); 
                    LOG.info("chk tanah-----::" + totalpetaktanah); 
                    
                    if (totalblok != 0){
                        hidBlokSemB = true;
                    }
                    
                    if (totalpetaktanah != 0){
                        hidBlokSem2 = true;
                    }
                }
            }

        } else {
            hidBlokSem = true;
            hidBlokSem2 = true;
        }

        checkByidSblm = false;
        return new JSP("strata/pbbm/laporan_tanah.jsp").addParameter("tab", "true");
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

        permohonan = permohonanDAO.findById(idPermohonan);
//        if (permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
//            hidBlokSem = true;
//        }

        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            LOG.info("PBBD -------- " + hidBlokSem2);
            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBSS")) {
                hidBlokSemB = true;
            }
            if (permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                hidBlokSem = true;
                hidBlokSemB = true;
            }
            if (permohonan.getKodUrusan().getKod().equals("PBBD") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                hidBlokSem2 = true;

                pBangunanL = strService.findByIDMohonBlok(permohonan.getIdPermohonan());

                if (pBangunanL.size() != 0) {
                    hidBlokSemB = true;
                }
            }

        } else {
            hidBlokSem = true;
            hidBlokSem2 = true;
        }

        checkByidSblm = false;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("kodNegeri: " + kodNegeri);

        return new JSP("strata/pbbm/terimalaporan_tanah.jsp").addParameter("tab", "true");
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
            return new RedirectResolution(PermohonanStrataActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
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
            pemilik = strService.findID(permohonan.getIdPermohonan());

            listKemudahan.clear();
            mohonHakmilik = strService.findMohonHakmilik(permohonan.getIdPermohonan());
            if (pemilik == null) {
                pemilik = new PermohonanStrata();
                if (mohonHakmilik != null) {
                    pemilik.setLaporanLokasi(mohonHakmilik.getHakmilik().getLokasi());
                } else {
                    String error = "Tiada Id Hakmilik";
                    LOG.info(error);
                }

                pemilik.setPermohonan(permohonan);
                pemilik.setCawangan(permohonan.getCawangan());
                pemilik.setInfoAudit(infoAudit);
                pemilik.setNama("-");
                pemilik.setPemilikNama("-");
                strService.simpanPemilik(pemilik);
            }
            // from kod_harta_bersama
            senaraikodHartaBersama = new ArrayList<KodHartaBersama>();
            senaraikodHartaBersama = strService.findHartaBersamaByNama();
            KodHartaBersama kodHB = strService.getLainlainOnly();
            lainLainOnly = kodHB.getNama();
            // for catatan1
            catatan1 = pemilik.getLaporanKemudahan12();

//            //Add by SyafiqAzizi
//            if (pemilik.getLaporanKemudahan1() != null) {
//                String string = pemilik.getLaporanKemudahan1();
//                String[] parts = string.split(", ");
////            List<String> kodHartaBsama = new ArrayList<String>();
//                for (int b = 0; b < senaraikodHartaBersama.size(); b++) {
//                    String kodharta = senaraikodHartaBersama.get(b).getNama();
//                    for (String parts2 : parts) {
//                        if (kodharta.equals(parts2)) {
//                            LOG.info("kodsharta-----------::" + kodharta + "||---parts-------------::" + parts2);
//                            if (b == 0) {
//                                laporanKemudahan1 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 1) {
//                                laporanKemudahan2 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 2) {
//                                laporanKemudahan3 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 3) {
//                                laporanKemudahan4 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 4) {
//                                laporanKemudahan5 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 5) {
//                                laporanKemudahan6 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 6) {
//                                laporanKemudahan7 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 7) {
//                                laporanKemudahan8 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 8) {
//                                laporanKemudahan9 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 9) {
//                                laporanKemudahan10 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            } else if (b == 10) {
//                                laporanKemudahan11 = parts2;
//                                LOG.info(b + "----parts2-----::" + parts2);
//                            }
//
//                        }
//                        if (b == 11) {
//                            if (!kodharta.equals(parts2)) {
//                                if (pemilik.getNoPAB() != null) {
//                                    laporanKemudahan12 = parts2;
//                                    catatan1 = parts2;
//                                    LOG.info(b + "----parts2-----::" + parts2);
//                                }
//                            }
//                        }
//                    }
//                }
//            }

            //added            
            laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
            LOG.debug("----------laporanTanah----------::" + laporanTanah);
            if (laporanTanah != null) {
                tarikhSiasatan = laporanTanah.getTarikhSiasat();
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
            chk2 = pemilik.getLaporanKegunaanLain();
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

        String dokumenPath = conf.getProperty("document.path");
        imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
        LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
        FolderDokumen fd = permohonan.getFolderDokumen();
        String docPath = conf.getProperty("document.path");
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
            return new RedirectResolution(PermohonanStrataActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
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
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

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

        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

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
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);



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
                case 12:
                    pemilik.setLaporanKemudahan12(sk.getKemudahan());
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
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm2");
    }

    public Resolution reload2() {
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm3");
    }

    public Resolution simpanLaporanTanah() {

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


        pemilik = strService.findID(idPermohonan);
        if (pemilik == null) {
            pemilik = new PermohonanStrata();
        }
        cawangan = permohonan.getCawangan();
        pemilik.setPermohonan(permohonan);
        pemilik.setCawangan(cawangan);
        pemilik.setInfoAudit(infoAudit);
        pemilik.setLaporanOleh(peng);
        pemilik.setTarikhLaporan(new java.util.Date());
        pemilik.setLaporanLokasi(laporanLokasi);
        pemilik.setLaporanUntukKediaman(laporanUntukKediaman);
        pemilik.setLaporanUntukPerniagaan(laporanUntukPerniagaan);
        pemilik.setLaporanUntukPejabat(laporanUntukPejabat);
        LOG.info("chk-----::" + chk);
        if (chk == null) {
            pemilik.setLaporanKegunaanLain(null);
        } else {
            pemilik.setLaporanKegunaanLain(laporanKegunaanLain);
        }
        pemilik.setLaporanKosRendah(laporanKosRendah);
        pemilik.setCfNoSijil(cfNoSijil);
        pemilik.setLaporanBandarTerdekat(laporanBandarTerdekat);
        pemilik.setLaporankeadaanTanah(laporankeadaanTanah);
        if (laporanBilBangunan != null) {
            pemilik.setLaporanBilBangunan(laporanBilBangunan);
        } else if (laporanBilBangunan == null) {
            pemilik.setLaporanBilBangunan(null);
        }
        if (laporanBilPetak != null) {
            pemilik.setLaporanBilPetak(laporanBilPetak);
        } else if (laporanBilPetak == null) {
            pemilik.setLaporanBilPetak(0);
        }
        pemilik.setLaporanBilBangunanProvisional(laporanBilBangunanProvisional);
        pemilik.setLaporanBilPetakProvisional(laporanBilPetakProvisional);
        if (laporanBilPetakTanah != null) {
            pemilik.setLaporanBilPetakTanah(Integer.parseInt(laporanBilPetakTanah));
        } else if (laporanBilPetakTanah == null) {
            pemilik.setLaporanBilPetakTanah(0);
        }
        strService.SimpanLaporanTanah(pemilik);

        LOG.debug("----------Saving in Mohon Strata----------::");
//        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
//            KodHartaBersama kodHartaBersama = new KodHartaBersama();
//            kodHartaBersama = senaraikodHartaBersama.get(i);
//            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
//            if (i == 0) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan1(kod);
//            } else if (i == 1) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan2(kod);
//            } else if (i == 2) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan3(kod);
//            } else if (i == 3) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan4(kod);
//            } else if (i == 4) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan5(kod);
//            } else if (i == 5) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan6(kod);
//            } else if (i == 6) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan7(kod);
//            } else if (i == 7) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan8(kod);
//            } else if (i == 8) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan9(kod);
//            } else if (i == 9) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan10(kod);
//            } else if (i == 10) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan11(kod);
//            } else if (i == 11) {
//                if (kod != null) {
//                    if (catatan != null) {
//                        kodHB.add(catatan1);
//                        pemilik.setNoPAB(catatan1);
//                    }
//                } else if (kod == null) {
//                    pemilik.setNoPAB(null);
//                }
//            }
//
//        }
//        String kods = "";
//        for (int f = 0; f < kodHB.size(); f++) {
//            String kod = kodHB.get(f);
//            if (f == 0) {
//                kods += kod;
//            } else {
//                kods += ", " + kod;
//            }
//        }
//        LOG.info("kods------------:::" + kods);
//        pemilik.setLaporanKemudahan1(kods);
//        strService.SimpanLaporanTanah(pemilik);
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
            } else if (i == 10) {
                pemilik.setLaporanKemudahan11(kod);
            } else if (i == 11) {
                if (kod != null) {
                    pemilik.setLaporanKemudahan12(catatan1);
                } else if (kod == null) {
                    pemilik.setLaporanKemudahan12(null);
                }
            }
            strService.SimpanLaporanTanah(pemilik);
        }
        /* Saving in Mohon_lapor_tanah
         added by murali @NS 17-07-2012 */
        LOG.debug("----------Saving in mohon_lapor_tanah----------::");
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        LOG.debug("----------laporanTanah----------::" + laporanTanah);
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
        }
        laporanTanah.setPermohonan(permohonan);
        laporanTanah.setHakmilikPermohonan(mohonHakmilik);
        laporanTanah.setInfoAudit(infoAudit);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        strService.simpanLaporan(laporanTanah);

        msg = "Maklumat telah berjaya disimpan.";

        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
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


        pemilik = strService.findID(idPermohonan);
        cawangan = permohonan.getCawangan();
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
        LOG.info("chk-----::" + chk);
        if (chk == null) {
            pemilik.setLaporanKegunaanLain(null);
        } else {
            pemilik.setLaporanKegunaanLain(laporanKegunaanLain);
        }
        pemilik.setLaporanKosRendah(laporanKosRendah);
        pemilik.setCfNoSijil(cfNoSijil);
        pemilik.setLaporanBandarTerdekat(laporanBandarTerdekat);
        pemilik.setLaporankeadaanTanah(laporankeadaanTanah);
        if (laporanBilBangunan != null) {
            pemilik.setLaporanBilBangunan(laporanBilBangunan);
        } else if (laporanBilBangunan == null) {
            pemilik.setLaporanBilBangunan(0);
        }
        if (laporanBilPetak != null) {
            pemilik.setLaporanBilPetak(laporanBilPetak);
        } else if (laporanBilPetak != null) {
            pemilik.setLaporanBilPetak(0);
        }
        pemilik.setLaporanBilBangunanProvisional(laporanBilBangunanProvisional);
        pemilik.setLaporanBilPetakProvisional(laporanBilPetakProvisional);
        if (laporanBilPetakTanah != null) {
            pemilik.setLaporanBilPetakTanah(Integer.parseInt(laporanBilPetakTanah));
        } else if (laporanBilPetakTanah == null) {
            pemilik.setLaporanBilPetakTanah(0);
        }
        simpanListKemudahan();
        strService.updateLaporanTanah(pemilik);

        LOG.debug("----------Saving in Mohon Strata----------::");

//        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
//            KodHartaBersama kodHartaBersama = new KodHartaBersama();
//            kodHartaBersama = senaraikodHartaBersama.get(i);
//            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
//            if (i == 0) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan1(kod);
//            } else if (i == 1) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan2(kod);
//            } else if (i == 2) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan3(kod);
//            } else if (i == 3) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan4(kod);
//            } else if (i == 4) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan5(kod);
//            } else if (i == 5) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan6(kod);
//            } else if (i == 6) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan7(kod);
//            } else if (i == 7) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan8(kod);
//            } else if (i == 8) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan9(kod);
//            } else if (i == 9) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan10(kod);
//            } else if (i == 10) {
//                if (kod != null) {
//                    kodHB.add(kod);
//                }
////                pemilik.setLaporanKemudahan11(kod);
//            } else if (i == 11) {
//                if (kod != null) {
//                    if (catatan != null) {
//                        kodHB.add(catatan1);
//                        pemilik.setNoPAB(catatan1);
//                    }
//                } else if (kod == null) {
//                    pemilik.setNoPAB(null);
//                }
//            }
//
//        }
//        String kods = "";
//        for (int f = 0; f < kodHB.size(); f++) {
//            String kod = kodHB.get(f);
//            if (f == 0) {
//                kods += kod;
//            } else {
//                kods += ", " + kod;
//            }
//        }
//        LOG.info("kods------------:::" + kods);
//        pemilik.setLaporanKemudahan1(kods);
//        strService.SimpanLaporanTanah(pemilik);
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
            } else if (i == 10) {
                pemilik.setLaporanKemudahan11(kod);
            } else if (i == 11) {
                if (kod != null) {
                    pemilik.setLaporanKemudahan12(catatan1);
                } else if (kod == null) {
                    pemilik.setLaporanKemudahan12(null);
                }
            }
            strService.SimpanLaporanTanah(pemilik);
        }
        /* Saving in Mohon_lapor_tanah
         added by murali @NS 17-07-2012 */
        LOG.debug("----------Saving in mohon_lapor_tanah----------::");
        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        LOG.debug("----------laporanTanah----------::" + laporanTanah);
        if (laporanTanah == null) {
            laporanTanah = new LaporanTanah();
        }
        laporanTanah.setPermohonan(permohonan);
        laporanTanah.setHakmilikPermohonan(mohonHakmilik);
        laporanTanah.setTarikhSiasat(tarikhSiasatan);
        laporanTanah.setInfoAudit(infoAudit);
        strService.simpanLaporan(laporanTanah);

        msg = "Maklumat telah berjaya dikemaskini.";


        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution resetForm() {
        LOG.debug("----------Reset Form----------::");
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

        pemilik = strService.findID(permohonan.getIdPermohonan());
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

        pemilik = strService.findID(idPermohonan);
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
        pemilik.setNoPAB(null);
        pemilik.setLaporankeadaanTanah("");
        pemilik.setLaporanBandarTerdekat("");
        pemilik.setLaporanBilBangunan(null);
        pemilik.setLaporanBilPetak(null);
        pemilik.setLaporanBilBangunanProvisional(null);
        pemilik.setLaporanBilPetakProvisional(null);
        pemilik.setLaporanBilPetakTanah(null);
        strService.SimpanLaporanTanah(pemilik);


        laporanTanah = strService.findLaporanTanahByIdPermohonan(idPermohonan);
        if (laporanTanah != null) {
            laporanTanah.setTarikhSiasat(null);
            laporanTanah.setInfoAudit(infoAudit);
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
            } else if (i == 10) {
                pemilik.setLaporanKemudahan12(null);
            } else if (i == 11) {
//                pemilik.setLaporanKemudahan13(null);
            }
            strService.SimpanLaporanTanah(pemilik);
        }
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm2");
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
        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm3").addParameter("error", error).addParameter("message", msg);

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

        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm6").addParameter("error", error).addParameter("message", msg);

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

        return new RedirectResolution(PermohonanStrataActionBean.class, "showForm6").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution muatNaik() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");

        Logger.getLogger(PermohonanStrataActionBean.class).info(" PARAMETER: " + getContext().getRequest().getParameter("lokasi"));
        String error = "";
        String msg = "";
        Logger.getLogger(PermohonanStrataActionBean.class).info("simpanMuatNaik::start");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        // InfoAudit ia = strService.getInfo(peng);
        // String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        Logger.getLogger(PermohonanStrataActionBean.class).info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();
//s
        if (fileToBeUpload == null) {
            if (StringUtils.isBlank(lokasi)) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
            } else {
                addSimpleError("Please select file to be Upload.");
                return new JSP("strata/muatnaik.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
            }

        } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".jpeg") || fileToBeUpload.getFileName().endsWith(".png"))) {

            if (StringUtils.isBlank(lokasi)) {
                addSimpleError("Please select valid Image.");
                return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
            } else {
                addSimpleError("Please select valid Image.");
                return new JSP("strata/muatnaik.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
            }
        }

        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
        Logger.getLogger(PermohonanStrataActionBean.class).info("KOD CAWANGAN" + peng.getKodCawangan().getKod());
        imejStrata.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejStrata.setCawangan(peng.getKodCawangan());
        imejStrata.setDokumen(doc);
        imejStrata.setPermohonanStrata(pemilik);
        imejStrata.setCatatan(catatan);
        Logger.getLogger(PermohonanStrataActionBean.class).info("LOKASI" + lokasi);
        char c = 0;
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejStrata.setPandanganImej(c);
        }

        imejStrata.setInfoAudit(ia);
        comm.saveImej(imejStrata);
        Logger.getLogger(PermohonanStrataActionBean.class).info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");
        if (StringUtils.isBlank(lokasi)) {
            return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
        } else {
            return new JSP("strata/muatnaik.jsp").addParameter("popup", "true").addParameter("lokasi", lokasi);
        }
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
        return new JSP("strata/muatnaik.jsp").addParameter("popup", "true");
    }

    public Resolution uploadDoc1() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        return new JSP("strata/muatnaik.jsp").addParameter("popup", "true");
    }

    //added
    public Resolution processimejUpload() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String lokasi = getContext().getRequest().getParameter("lokasi");

        Logger.getLogger(PermohonanStrataActionBean.class).info(" PARAMETER: " + getContext().getRequest().getParameter("lokasi"));
        String error = "";
        String msg = "";
        Logger.getLogger(PermohonanStrataActionBean.class).info("simpanMuatNaik::start");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(pguna);
        String dokumenPath = conf.getProperty("document.path");
//        String dokumenPath = "E:/etanahNew/etanahViewController/src/main/webapp/images/up/";
        permohonan = permohonanDAO.findById(idPermohonan);
        Logger.getLogger(PermohonanStrataActionBean.class).info("INI ADALAH FOLDER DOKUMEN" + permohonan.getFolderDokumen().getFolderId());
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

        //save image in Imageformat

        // FileBean fileToBeUpload1=new FileBean(new File(fileToBeUpload.getFileName()),fileToBeUpload.getContentType(),fileToBeUpload.getFileName());// <editor-fold defaultstate="collapsed" desc="comment">
        FileBean fileToBeUpload1 = fileToBeUpload;

        if (fileToBeUpload1 != null) {

            try {
                // File dirFile = new File(dokumenPath+"/"+ permohonan.getFolderDokumen().getFolderId()+"/"+fileToBeUpload1.getFileName());
                File dirFile = new File(dokumenPath + "/" + fileToBeUpload1.getFileName());
                fileToBeUpload1.save(dirFile);
            } catch (IOException e) {
                e.getMessage();

            }
        }



        Dokumen doc = comm.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

        Logger.getLogger(PermohonanStrataActionBean.class).info("KOD CAWANGAN" + peng.getKodCawangan().getKod());
        imejStrata.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
        imejStrata.setCawangan(peng.getKodCawangan());
        imejStrata.setDokumen(doc);
        imejStrata.setPermohonanStrata(pemilik);
        imejStrata.setCatatan(catatan);
        Logger.getLogger(PermohonanStrataActionBean.class).info("LOKASI" + lokasi);
        char c = 0;
        if (StringUtils.isNotEmpty(lokasi)) {
            c = (lokasi).charAt(0);
            imejStrata.setPandanganImej(c);
        }
        imejStrata.setInfoAudit(ia);
        comm.saveImej(imejStrata);
        Logger.getLogger(PermohonanStrataActionBean.class).info("simpanMuatNaik::finish");
        addSimpleMessage("Muat naik fail berjaya.");

        return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true");
    }

    public Resolution muatNaikImej() {
        return new JSP("strata/pbbm/muat_naikimej.jsp").addParameter("popup", "true");
    }

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
        strService.deleteMohonStrataImejByIdDokumen(dokumenId);
        strService.deleteKandunganFolderByIdDokumen(folderId, dokumenId);
        strService.deleteDokumenCapaianByIdDokumen(dokumenId);
        strService.deleteAll2(dokumen);
        LOG.info("---------------Deleted idDokumen is::--------" + dokumenId);
        return new JSP("strata/pbbm/laporan_tanah.jsp").addParameter("tab", "true");
    }

    public List<SenaraiKemudahan> getListKemudahan() {
        return listKemudahan;
    }

    public boolean isHidBlokSem2() {
        return hidBlokSem2;
    }

    public void setHidBlokSem2(boolean hidBlokSem2) {
        this.hidBlokSem2 = hidBlokSem2;
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

    public boolean isHidBlokSem() {
        return hidBlokSem;
    }

    public void setHidBlokSem(boolean hidBlokSem) {
        this.hidBlokSem = hidBlokSem;
    }

    public String getLainLainOnly() {
        return lainLainOnly;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public void setLainLainOnly(String lainLainOnly) {
        this.lainLainOnly = lainLainOnly;
    }

    /**
     * @return the kodHB
     */
    public List<String> getKodHB() {
        return kodHB;
    }

    /**
     * @param kodHB the kodHB to set
     */
    public void setKodHB(List<String> kodHB) {
        this.kodHB = kodHB;
    }

    /**
     * @return the laporanKemudahan1
     */
    public String getLaporanKemudahan1() {
        return laporanKemudahan1;
    }

    /**
     * @param laporanKemudahan1 the laporanKemudahan1 to set
     */
    public void setLaporanKemudahan1(String laporanKemudahan1) {
        this.laporanKemudahan1 = laporanKemudahan1;
    }

    /**
     * @return the laporanKemudahan2
     */
    public String getLaporanKemudahan2() {
        return laporanKemudahan2;
    }

    /**
     * @param laporanKemudahan2 the laporanKemudahan2 to set
     */
    public void setLaporanKemudahan2(String laporanKemudahan2) {
        this.laporanKemudahan2 = laporanKemudahan2;
    }

    /**
     * @return the laporanKemudahan3
     */
    public String getLaporanKemudahan3() {
        return laporanKemudahan3;
    }

    /**
     * @param laporanKemudahan3 the laporanKemudahan3 to set
     */
    public void setLaporanKemudahan3(String laporanKemudahan3) {
        this.laporanKemudahan3 = laporanKemudahan3;
    }

    /**
     * @return the laporanKemudahan4
     */
    public String getLaporanKemudahan4() {
        return laporanKemudahan4;
    }

    /**
     * @param laporanKemudahan4 the laporanKemudahan4 to set
     */
    public void setLaporanKemudahan4(String laporanKemudahan4) {
        this.laporanKemudahan4 = laporanKemudahan4;
    }

    /**
     * @return the laporanKemudahan5
     */
    public String getLaporanKemudahan5() {
        return laporanKemudahan5;
    }

    /**
     * @param laporanKemudahan5 the laporanKemudahan5 to set
     */
    public void setLaporanKemudahan5(String laporanKemudahan5) {
        this.laporanKemudahan5 = laporanKemudahan5;
    }

    /**
     * @return the laporanKemudahan6
     */
    public String getLaporanKemudahan6() {
        return laporanKemudahan6;
    }

    /**
     * @param laporanKemudahan6 the laporanKemudahan6 to set
     */
    public void setLaporanKemudahan6(String laporanKemudahan6) {
        this.laporanKemudahan6 = laporanKemudahan6;
    }

    /**
     * @return the laporanKemudahan7
     */
    public String getLaporanKemudahan7() {
        return laporanKemudahan7;
    }

    /**
     * @param laporanKemudahan7 the laporanKemudahan7 to set
     */
    public void setLaporanKemudahan7(String laporanKemudahan7) {
        this.laporanKemudahan7 = laporanKemudahan7;
    }

    /**
     * @return the laporanKemudahan8
     */
    public String getLaporanKemudahan8() {
        return laporanKemudahan8;
    }

    /**
     * @param laporanKemudahan8 the laporanKemudahan8 to set
     */
    public void setLaporanKemudahan8(String laporanKemudahan8) {
        this.laporanKemudahan8 = laporanKemudahan8;
    }

    /**
     * @return the laporanKemudahan9
     */
    public String getLaporanKemudahan9() {
        return laporanKemudahan9;
    }

    public boolean isHidBlokSemB() {
        return hidBlokSemB;
    }

    public void setHidBlokSemB(boolean hidBlokSemB) {
        this.hidBlokSemB = hidBlokSemB;
    }

    /**
     * @param laporanKemudahan9 the laporanKemudahan9 to set
     */
    public void setLaporanKemudahan9(String laporanKemudahan9) {
        this.laporanKemudahan9 = laporanKemudahan9;
    }

    /**
     * @return the laporanKemudahan10
     */
    public String getLaporanKemudahan10() {
        return laporanKemudahan10;
    }

    /**
     * @param laporanKemudahan10 the laporanKemudahan10 to set
     */
    public void setLaporanKemudahan10(String laporanKemudahan10) {
        this.laporanKemudahan10 = laporanKemudahan10;
    }

    /**
     * @return the laporanKemudahan11
     */
    public String getLaporanKemudahan11() {
        return laporanKemudahan11;
    }

    /**
     * @param laporanKemudahan11 the laporanKemudahan11 to set
     */
    public void setLaporanKemudahan11(String laporanKemudahan11) {
        this.laporanKemudahan11 = laporanKemudahan11;
    }

    /**
     * @return the laporanKemudahan12
     */
    public String getLaporanKemudahan12() {
        return laporanKemudahan12;
    }

    /**
     * @param laporanKemudahan12 the laporanKemudahan12 to set
     */
    public void setLaporanKemudahan12(String laporanKemudahan12) {
        this.laporanKemudahan12 = laporanKemudahan12;
    }

    /**
     * @return the chk
     */
    public String getChk() {
        return chk;
    }

    /**
     * @param chk the chk to set
     */
    public void setChk(String chk) {
        this.chk = chk;
    }

    /**
     * @return the chk2
     */
    public String getChk2() {
        return chk2;
    }

    /**
     * @param chk2 the chk2 to set
     */
    public void setChk2(String chk2) {
        this.chk2 = chk2;
    }
}

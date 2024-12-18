/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanLokasiDAO;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.AduanStrataDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Alamat;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPemilikan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.StrataPtService;
import java.text.ParseException;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.UrusanValue;
import etanah.workflow.WorkFlowService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.AduanSiasatan;
import etanah.model.AduanStrata;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import java.text.SimpleDateFormat;
import etanah.service.StrataPtService;
import org.apache.log4j.Logger;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.UrusanDokumen;
import etanah.service.KaunterService;
import etanah.service.common.LelongService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author wan.fairul Modified By Murali 06-07-2011
 */
@UrlBinding("/strata/kuatkuasa")
public class PenguatkuasaanStrataActionBean extends BasicTabActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    StrataPtService ptService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private AduanLokasiDAO aduanLokasiDAO;
    @Inject
    private AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    AduanStrataDAO aduanStrataDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private Permohonan permohonan;
    private AduanLokasi aduanLokasi;
    private KodCawangan cawangan;
    private KodCaraPermohonan caraPermohonan;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodPemilikan pemilikan;
    private String kodDaerah;
    private String daerah;
    private String sebab;
    private String kod;
    private String noLot;
    private String lokasi;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private String penyerahNoPengenalan;
    private String kodCawangan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private KodNegeri penyerahNegeri;
    private String penyerahEmail;
    private String penyerahNoTelefon1;
    private String id;
    private String nama;
    private Alamat alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeriO;
    private String noPengenalan;
    private String noTelefon1;
    private List<KodCaraPermohonan> senaraiKodCaraPermohonan;
    private List<KodBandarPekanMukim> senaraiKodBandarPekanMukim;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<KodUrusan> senaraiUrusan;
    private ArrayList<UrusanValue> senaraiPermohonan = new ArrayList<UrusanValue>();
    private ArrayList<OksValue> senaraiOKS = new ArrayList<OksValue>();
    private String beanFlag = "kemasukan_aduan";
    private AduanSiasatan aduanSiasatan;
    private List<AduanSiasatan> senaraiAduanSiasatan;
    private String tarikhSiasat;
    private String tajuk;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger logger = Logger.getLogger(PenguatkuasaanStrataActionBean.class);
    private String idSiasatan;
    private KodDokumen kd;
    private PermohonanKertas permohonankertas;
    private String jam;
    private String minit;
    private String masa;
    private String tarikhSiasatt;
    private String tarikhSiasaat;
    private String idKandungan;
    private PermohonanKertasKandungan permohonankertasKandungan;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private AduanStrata aduanStrata;
    private String aduanStratahakmilik;
    private Hakmilik aduanStratahakmilik1;
    private String cfNoSijil;
    private Date cfTarikhKeluar;
    private Date tarikhTandatanganPerjanjianJualBeli;
    private Hakmilik hakmilik;
    private Pengguna pguna;
    private long idDokumen;
    private List<Permohonan> listMohon;
    private KodUrusan kodUrusan;
    // Documen_Tambah_page :Start
    private List<KandunganFolder> senaraiKandungan1 = new ArrayList<KandunganFolder>();
    private FolderDokumen folderDokumen;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KodDokumen> KodDokumenNotRequired = new ArrayList<KodDokumen>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private String negeri;
    private HakmilikPermohonan hakmilikPermohonan;
    FileBean fileToBeUpload;
    private List<KodUrusan> senaraiUrusan2;
    private String dokumenID;
    private String folderID;

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return strService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<KandunganFolder> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<KandunganFolder> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    //End
    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdSiasatan() {
        return idSiasatan;
    }

    public void setIdSiasatan(String idSiasatan) {
        this.idSiasatan = idSiasatan;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getTarikhSiasaat() {
        return tarikhSiasaat;
    }

    public void setTarikhSiasaat(String tarikhSiasaat) {
        this.tarikhSiasaat = tarikhSiasaat;
    }

    public String getTarikhSiasatt() {
        return tarikhSiasatt;
    }

    public void setTarikhSiasatt(String tarikhSiasatt) {
        this.tarikhSiasatt = tarikhSiasatt;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public PermohonanKertas getPermohonankertas() {
        return permohonankertas;
    }

    public void setPermohonankertas(PermohonanKertas permohonankertas) {
        this.permohonankertas = permohonankertas;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeriO() {
        return negeriO;
    }

    public void setNegeriO(String negeriO) {
        this.negeriO = negeriO;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNoTelefon1() {
        return penyerahNoTelefon1;
    }

    public void setPenyerahNoTelefon1(String penyerahNoTelefon1) {
        this.penyerahNoTelefon1 = penyerahNoTelefon1;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getBeanFlag() {
        return beanFlag;
    }

    public void setBeanFlag(String beanFlag) {
        this.beanFlag = beanFlag;
    }

    public ArrayList<OksValue> getSenaraiOKS() {
        return senaraiOKS;
    }

    public void setSenaraiOKS(ArrayList<OksValue> senaraiOKS) {
        this.senaraiOKS = senaraiOKS;
    }

    public ArrayList<UrusanValue> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(ArrayList<UrusanValue> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return senaraiKodBandarPekanMukim;
    }

    public void setSenaraiKodBandarPekanMukim(List<KodBandarPekanMukim> senaraiKodBandarPekanMukim) {
        this.senaraiKodBandarPekanMukim = senaraiKodBandarPekanMukim;
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return senaraiKodCaraPermohonan;
    }

    public void setSenaraiKodCaraPermohonan(List<KodCaraPermohonan> senaraiKodCaraPermohonan) {
        this.senaraiKodCaraPermohonan = senaraiKodCaraPermohonan;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public AduanSiasatan getAduanSiasatan() {
        return aduanSiasatan;
    }

    public void setAduanSiasatan(AduanSiasatan aduanSiasatan) {
        this.aduanSiasatan = aduanSiasatan;
    }

//    public Date getTarikhSiasat() {
//        return tarikhSiasat;
//    }
//
//    public void setTarikhSiasat(Date tarikhSiasat) {
//        this.tarikhSiasat = tarikhSiasat;
//    }
    public String getTarikhSiasat() {
        return tarikhSiasat;
    }

    public void setTarikhSiasat(String tarikhSiasat) {
        this.tarikhSiasat = tarikhSiasat;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public List<AduanSiasatan> getSenaraiAduanSiasatan() {
        return senaraiAduanSiasatan;
    }

    public void setSenaraiAduanSiasatan(List<AduanSiasatan> senaraiAduanSiasatan) {
        this.senaraiAduanSiasatan = senaraiAduanSiasatan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getCfNoSijil() {
        return cfNoSijil;
    }

    public void setCfNoSijil(String cfNoSijil) {
        this.cfNoSijil = cfNoSijil;
    }

    public Date getCfTarikhKeluar() {
        return cfTarikhKeluar;
    }

    public void setCfTarikhKeluar(Date cfTarikhKeluar) {
        this.cfTarikhKeluar = cfTarikhKeluar;
    }

    public Date getTarikhTandatanganPerjanjianJualBeli() {
        return tarikhTandatanganPerjanjianJualBeli;
    }

    public void setTarikhTandatanganPerjanjianJualBeli(Date tarikhTandatanganPerjanjianJualBeli) {
        this.tarikhTandatanganPerjanjianJualBeli = tarikhTandatanganPerjanjianJualBeli;
    }

    public GeneratorIdPermohonan getIdPermohonanGenerator() {
        return idPermohonanGenerator;
    }

    public void setIdPermohonanGenerator(GeneratorIdPermohonan idPermohonanGenerator) {
        this.idPermohonanGenerator = idPermohonanGenerator;
    }

    public KaunterService getKaunterService() {
        return kaunterService;
    }

    public void setKaunterService(KaunterService kaunterService) {
        this.kaunterService = kaunterService;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<KodUrusan> getSenaraiUrusan2() {
        return senaraiUrusan2;
    }

    public void setSenaraiUrusan2(List<KodUrusan> senaraiUrusan2) {
        this.senaraiUrusan2 = senaraiUrusan2;
    }

    /**
     * @return the dokumenID
     */
    public String getDokumenID() {
        return dokumenID;
    }

    /**
     * @param dokumenID the dokumenID to set
     */
    public void setDokumenID(String dokumenID) {
        this.dokumenID = dokumenID;
    }

    /**
     * @return the folderID
     */
    public String getFolderID() {
        return folderID;
    }

    /**
     * @param folderID the folderID to set
     */
    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    class UrusanCache implements Serializable {

        ArrayList<UrusanValue> senaraiPermohonan;
        ArrayList<OksValue> senaraiOKS;
        //  ArrayList<LokasiKejadianValue> senaraiLokasiKejadian;
        Permohonan permohonan;
        AduanStrata aduanStrata;
    }

    @DefaultHandler
    public Resolution setAduan() {
//        getOksfromSession();
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/kemasukan_aduan.jsp");
    }

    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        aduanLokasi = ptService.getAduanLokasi(permohonan.getIdPermohonan());
        return new JSP("strata/kuatkuasa/viewKemasukan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------Rehydrate()---------------::");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (kodDaerah != null) {
            kodDaerah = pguna.getKodCawangan().getDaerah().getKod();
        }
        getContext().getRequest().setAttribute("kodDaerah", kodDaerah);
        if (daerah != null) {
            daerah = pguna.getKodCawangan().getDaerah().getNama();
        }
        getContext().getRequest().setAttribute("daerah", daerah);
        getContext().getRequest().setAttribute("sebab", sebab);
        senaraiKodCaraPermohonan = strService.getSenaraiKodCaraPermohonan();
        senaraiKodBandarPekanMukim = strService.getSenaraiKodBandarPekanMukim();
        if (cawangan != null) {
            cawangan = pguna.getKodCawangan();

            if (cawangan.getDaerah() != null) {
                String bandarPekanMukim1 = cawangan.getDaerah().getKod();
                if (StringUtils.isNotBlank(bandarPekanMukim1)) {
                    setListBandarPekanMukim(strService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
                }
            }
        }
        senaraiUrusan = strService.getSenaraiUrusan();

        //start
        logger.debug("----------senaraiUrusan-------------" + senaraiUrusan);

        senaraiUrusan = strService.getSenaraiUrusan();
        senaraiUrusan2 = new ArrayList<KodUrusan>();

        logger.debug("----------senaraiUrusan-------------" + senaraiUrusan);
        KodUrusan kodUrusan1 = new KodUrusan();
        kodUrusan1 = kodUrusanDAO.findById("P8");
        senaraiUrusan2.add(kodUrusan1);
        logger.debug("----------senaraiUrusan1-------------::" + senaraiUrusan2);
        int i;
        for (i = 0; i < senaraiUrusan.size(); i++) {
            senaraiUrusan2.add(senaraiUrusan.get(i));
        }

        //End

        getUrusanfromSession();

        //For Dokumen_Tambah_Page:Start
        senaraiKandungan1 = new ArrayList<KandunganFolder>();

        String ids = getContext().getRequest().getParameter("folder.idFolder");
        if (ids != null && ids.length() > 0) {
            folderDokumen = folderDokumenDAO.findById(Long.valueOf(ids));
        } else {
            ids = getContext().getRequest().getParameter("permohonan.idPermohonan");
            logger.debug("----------ids:Permohonan-------------" + ids);
            if (ids != null && ids.length() > 0) {
                logger.debug("----------permohonan-------------" + permohonan);
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen != null) {
                    if (folderDokumen.getSenaraiKandungan() != null) {
                        for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                            kf.setDokumen(d);
                            senaraiKandungan1.add(kf);
                        }
                    }
                }
            }
        }

        negeri = conf.getProperty("kodNegeri");

        //End
    }

    public Resolution sessionSave() {

        logger.debug("----------Kemasukan_Aduan Form Details Saving In Session-----------::");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);


        if (permohonan != null) {
            folderDokumen = permohonan.getFolderDokumen();
            if (folderDokumen != null) {
                if (folderDokumen.getSenaraiKandungan() != null) {
                    for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                        if (kf == null || kf.getDokumen() == null) {
                            continue;
                        }
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        d.setInfoAudit(ia);
                        kf.setDokumen(d);
                        senaraiKandungan1.add(kf);
                    }
                }
                permohonan.setFolderDokumen(folderDokumen);
            }
        }

        kodUrusan = new KodUrusan();
        if (getContext().getRequest().getParameter("kodUrusan.kod").equals("")) {
            kodUrusan = kodUrusanDAO.findById("ADUAN");
        } else {
            kodUrusan = kodUrusanDAO.findById(getContext().getRequest().getParameter("kodUrusan.kod"));
        }

        if (permohonan == null) {
            if (conf.getProperty("kodNegeri").equals("05")) {
                idPermohonan = idPermohonanGenerator.generate(ctx.getKodNegeri(), pengguna.getKodCawangan(), kodUrusan);
            }
            if (conf.getProperty("kodNegeri").equals("04")) {
                if (aduanStratahakmilik != null) {
                    Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
                    idPermohonan = idPermohonanGenerator.generate(ctx.getKodNegeri(), hk.getCawangan(), kodUrusan);
                }
            }
            permohonan = new Permohonan();
        }

        logger.debug("----------Saving Permohon in Session----------::");
        permohonan.setStatus("TA");
        permohonan.setIdPermohonan(idPermohonan);

        if (conf.getProperty("kodNegeri").equals("05")) {
            Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
            permohonan.setCawangan(hk.getCawangan());
        } else {
            Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
            permohonan.setCawangan(hk.getCawangan());
        }
        permohonan.setKodUrusan(kodUrusan);
        permohonan.setCaraPermohonan(caraPermohonan);
        permohonan.setSebab(sebab);
        logger.debug("-------------penyerahNama Saving to Session----------::" + penyerahNama);
        permohonan.setPenyerahNama(penyerahNama);
        permohonan.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
        permohonan.setPenyerahNoPengenalan(penyerahNoPengenalan);
        permohonan.setPenyerahAlamat1(penyerahAlamat1);
        permohonan.setPenyerahAlamat2(penyerahAlamat2);
        permohonan.setPenyerahAlamat3(penyerahAlamat3);
        permohonan.setPenyerahAlamat4(penyerahAlamat4);
        permohonan.setPenyerahPoskod(penyerahPoskod);
        permohonan.setPenyerahNegeri(penyerahNegeri);
        permohonan.setPenyerahNoTelefon1(penyerahNoTelefon1);
        permohonan.setPenyerahEmail(penyerahEmail);
        permohonan.setInfoAudit(ia);

        // Saving in Aduan_Strata
        logger.debug("----------Saving ADUAN_STRATA in Session----------::");
        aduanStrata = new AduanStrata();
        aduanStrata.setPermohonan(permohonan);
        aduanStrata.setCawangan(caw);
        if (aduanStratahakmilik != null) {
            Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
            logger.debug("----------Id Hakmilik----------::" + hk);

            logger.info("------noLot------" + hk.getNoLot());
            aduanStrata.setNoLot(hk.getNoLot());
            aduanStrata.setKodLot(hk.getLot());
            aduanStrata.setCawangan(hk.getCawangan());

            if (hk != null) {
                aduanStrata.setHakmilik(hk);
            } else {
                addSimpleError("ID Hakmilik tidak wujud. Sila masukkan ID Hakmilik sekali lagi.");
                return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/kemasukan_aduan.jsp");
            }
        }
        aduanStrata.setCfNoSijil(cfNoSijil);
        aduanStrata.setCfTarikhKeluar(cfTarikhKeluar);
        aduanStrata.setTarikhTandatanganPerjanjianJualBeli(tarikhTandatanganPerjanjianJualBeli);
        saveToSession(ctx);
        addSimpleMessage("ID Aduan : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/kemasukan_dokumen_tambahan.jsp");
    }

    // For kemasukan_dokumen_tambahan : START
    public Resolution addDocForm() {
        logger.debug("------------adding Documen-------------");

//        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
        senaraiKodUrusan.add("P8");
        senaraiKodUrusan.add("P22A");
        senaraiKodUrusan.add("P14A");
        senaraiKodUrusan.add("P22B");
        if (negeri.equals("04")) {
            senaraiKodUrusan.add("P40A");
        }

        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
            KandunganFolder kf = new KandunganFolder();
            kandunganFolderTamb.add(kf);
        }
        return new JSP("strata/kuatkuasa/kemasukan_tambahan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        logger.debug("------------simpan Dokumen Tambahan----------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        if (folderDokumen != null) {
            akf = folderDokumen.getSenaraiKandungan();
        } else {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonan.getIdPermohonan());

            permohonan.setFolderDokumen(fd);
            folderDokumen = permohonan.getFolderDokumen();
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            KandunganFolder f = kandunganFolderTamb.get(0);
            Dokumen d = f.getDokumen();
            String c = f.getCatatan();

            KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());

            if ((kd != null && !kd.getKod().equals("0"))
                    || (c != null && c.trim().length() > 0)) {
                d.setInfoAudit(ia);
                KodDokumen kDok = kodDokumenDAO.findById(kd.getKod());
                d.setTajuk(kd == null ? "KIV" : kDok.getNama());
                d.setNoVersi("1.0");
                d.setKodDokumen(kd);
                d.setKlasifikasi(klasifikasiAm);
                dokumenDAO.save(d);
                f.setFolder(folderDokumen);
                f.setInfoAudit(ia);
                f.setDokumen(d);
                akf.add(f);
            }
            if (akf.size() > 0) {
                folderDokumen.setSenaraiKandungan(akf);
            }

            folderDokumen.setInfoAudit(ia);
            folderDokumenDAO.save(folderDokumen);
//            kandunganFolderDAO.save(f);

            tx.commit();

            permohonan.setFolderDokumen(folderDokumen);
            saveToSession(ctx);

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(PenguatkuasaanStrataActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public Resolution reload() {
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/kemasukan_dokumen_tambahan.jsp");
    }

    public Resolution deleteSelected() {
        logger.debug("-----------deleting Selected Dokumen---------");
        String[] ids = getContext().getRequest().getParameterValues("chkbox");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Long id1 = Long.parseLong(id);
                Dokumen dok1 = dokumenDAO.findById(id1);
                dokumenDAO.delete(dok1);

                List<KandunganFolder> akf = folderDokumen.getSenaraiKandungan();
                for (Iterator<KandunganFolder> it = akf.iterator(); it.hasNext();) {
                    KandunganFolder fd1 = it.next();
                    if (id1 == fd1.getDokumen().getIdDokumen()) {
                        it.remove();
                        break;
                    }
                }
                folderDokumen.setSenaraiKandungan(akf);
            }
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
        return new RedirectResolution(PenguatkuasaanStrataActionBean.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
    }

    public Resolution setKembali() {
        logger.debug("----------Back to Kemasukan Aduan----------::");
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/kemasukan_aduan.jsp").addParameter("senaraiKandungan1", senaraiKandungan1);
    }

    public Resolution save() {

        logger.debug("-------Saving Data in Permohon and Aduan Strata and HakmilikPermohonan------::");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();

        logger.debug("-------Saving Data in Permohon-------::");
        try {

            //comented on 04-10-2011
            permohonan.setInfoAudit(ia);
            if (permohonan.getFolderDokumen() == null) {
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk(permohonan.getIdPermohonan());
                fd.setInfoAudit(ia);
                folderDokumenDAO.save(fd);
                permohonan.setFolderDokumen(fd);
            }
            permohonanDAO.save(permohonan);
            logger.debug("-------Data Saved in Permohon-------::");

            //comented on 04-10-2011
            logger.debug("-------Saving Data in Aduan Strata-------::");
            aduanStrata.setInfoAudit(ia);
            aduanStrataDAO.save(aduanStrata);
            logger.debug("-------Data Saved in Aduan Strata-------::");
            //end

            logger.debug("-------Saving Data in Mohon Hakmilik-------::");
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setPermohonan(permohonan);
            if (aduanStratahakmilik != null) {
                Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
                hakmilikPermohonan.setHakmilik(hk);
            }
            hakmilikPermohonanDAO.save(hakmilikPermohonan);
            logger.debug("-------Data Saved in Mohon Hakmilik-------::");

            logger.debug("kodUrusan :" + permohonan.getKodUrusan().getKod());

            // For Bpel :Start

            if (!permohonan.getKodUrusan().getKod().equals("ADUAN")) {

                KodUrusan kodUrusan = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (kodUrusan.getKePTG() == 'Y') {
                        WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                                idPermohonan, pengguna.getKodCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                                kodUrusan.getNama());
                    } else if (kodUrusan.getKePTG() == 'T') {
                        WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                                idPermohonan, pengguna.getKodCawangan().getKod(), pengguna.getIdPengguna(),
                                kodUrusan.getNama());
                    }
                }

                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (aduanStratahakmilik != null) {
                        Hakmilik hk = hakmilikDAO.findById(aduanStratahakmilik);
                        if (kodUrusan.getKePTG() == 'Y') {
                            WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                                    idPermohonan, hk.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                                    kodUrusan.getNama());
                        } else if (kodUrusan.getKePTG() == 'T') {
                            WorkFlowService.initiateTask(kodUrusan.getIdWorkflow(),
                                    idPermohonan, hk.getCawangan().getKod(), pengguna.getIdPengguna(),
                                    kodUrusan.getNama());
                        }
                    }
                }
            }

            //End

            UrusanValue uv = new UrusanValue();
            uv.setKodUrusan(kodUrusan.getKod());
            uv.setIdPermohonan(idPermohonan);
            senaraiPermohonan.add(uv);

            tx.commit();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            logger.info("----------Generating Report Started---------::");
            genReport();
            logger.info("--------Generating Report finished-------::");
        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        } finally {
            resetAll();
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/tamat.jsp");
    }

    public final void saveToSession(etanahActionBeanContext ctx) {

        logger.debug("-------saving in Session-----");

        UrusanCache u = (UrusanCache) ctx.getWorkData();
        if (u == null) {
            u = new UrusanCache();
        }
//        u.senaraiOKS = senaraiOKS;
        // u.senaraiLokasiKejadian = senaraiLokasiKejadian;
        u.permohonan = permohonan;
        u.aduanStrata = aduanStrata;

        ctx.setWorkData(u);
    }

    //get data from session
    public final void getUrusanfromSession() {

        logger.debug("---------Back to Kemasukan_Aduan---------::");
        logger.debug("---------gettind Data From Session---------::");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        UrusanCache u = (UrusanCache) ctx.getWorkData();

        if (u != null) {
            logger.debug("---------Permohonan details from session---------::");
            permohonan = u.permohonan;
            if (permohonan != null) {
                idPermohonan = permohonan.getIdPermohonan();
                caraPermohonan = permohonan.getCaraPermohonan();
                sebab = permohonan.getSebab();
                kodUrusan = permohonan.getKodUrusan();
                penyerahNoPengenalan = permohonan.getPenyerahNoPengenalan();
                penyerahNama = permohonan.getPenyerahNama();
                logger.debug("--------penyerahNama get from getUrusanfromSession()-----::" + penyerahNama);
                penyerahAlamat1 = permohonan.getPenyerahAlamat1();
                penyerahAlamat2 = permohonan.getPenyerahAlamat2();
                penyerahAlamat3 = permohonan.getPenyerahAlamat3();
                penyerahAlamat4 = permohonan.getPenyerahAlamat4();
                penyerahPoskod = permohonan.getPenyerahPoskod();
                penyerahNegeri = permohonan.getPenyerahNegeri();
                penyerahEmail = permohonan.getPenyerahEmail();
                penyerahNoTelefon1 = permohonan.getPenyerahNoTelefon1();
                folderDokumen = permohonan.getFolderDokumen();
            }
            logger.debug("---------Aduan Strata details from session---------::");
            aduanStrata = u.aduanStrata;
            if (aduanStrata != null) {
                aduanStratahakmilik = aduanStrata.getHakmilik().getIdHakmilik();
                cfNoSijil = aduanStrata.getCfNoSijil();
                cfTarikhKeluar = aduanStrata.getCfTarikhKeluar();
                tarikhTandatanganPerjanjianJualBeli = aduanStrata.getTarikhTandatanganPerjanjianJualBeli();
            }
        } else {
            logger.debug("-----------No data in session-----------::");
        }
    }

    //END
    public void resetAll() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        caraPermohonan = null;
        sebab = null;
        penyerahJenisPengenalan = null;
        penyerahNoPengenalan = null;
        penyerahNama = null;
        penyerahAlamat1 = penyerahAlamat2 = penyerahAlamat3 = penyerahAlamat4 = penyerahPoskod = null;
        penyerahNegeri = null;
        penyerahEmail = null;
        penyerahNoTelefon1 = null;
        bandarPekanMukim = null;
        pemilikan = null;
        lokasi = null;
    }

    //murali
    public Resolution genReport() {

        logger.debug("---------Report Generation Started--------::");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.debug("---------generate report start--------::");
//        String msg = "Laporan telah dijana. Sila Klik Butang Papar dan Cetak.";
        negeri = conf.getProperty("kodNegeri");
        //default report = NS
        String gen = "STRResitAkuanPenerimaan_NS.rdf";
        if (negeri.equals("04")) {
            gen = "STRResitAkuanPenerimaan_MLK.rdf";
        } else {
            gen = "STRResitAkuanPenerimaan_NS.rdf";
        }

        String code = "RAPST";
        logger.debug("idPermohonan : " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        try {
            logger.debug("-------generating Report From XML------::");
            Dokumen doc = lelongService.reportGen2(permohonan, gen, code, pengguna);
            logger.debug("-------Dokumen doc------::" + doc);
            idDokumen = doc.getIdDokumen();
            logger.debug("-------idDokumen------::" + idDokumen);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        logger.debug("--------generating Report is finished::");
        logger.debug("idDokumen : " + idDokumen);
        listMohon = new ArrayList<Permohonan>();
        listMohon.add(permohonan);
        logger.debug("listMohon : " + listMohon.size());
        addSimpleMessage("Aduan yang diterima telah dijana. Sila Klik Butang Papar Aduan untuk  semak dan cetak aduan yang telah dijana.");
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/tamat.jsp");
        // return new JSP("strata/kuatkuasa/tamat.jsp");
    }

    public Resolution muatNaikForm1() {

        return new JSP("strata/kuatkuasa/muat_naik_kaunter.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        System.out.println("------------processUpload------------::");
        dokumenID = getContext().getRequest().getParameter("dokumenId");
        folderID = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");

        DMSUtil dmsUtil = new DMSUtil();

        String catatan = getContext().getRequest().getParameter("catatan");
        System.out.println("fileToBeUpload " + fileToBeUpload);
        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();
                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileToBeUpload.getFileName();
                System.out.println("------------fizikalPath------------::" + fizikalPath);
                System.out.println("------------fileToBeUpload------------::" + fileToBeUpload.getContentType());
                System.out.println("------------dmsUtil------------::" + dmsUtil);
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderID;
                System.out.println("------------dmsUtil.getDMSPath(permohonan)------------::" + dmsUtil.getDMSPath(permohonan));
                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                System.out.println("------------fileUtil------------::" + fileUtil);
                System.out.println("------------fileToBeUpload.getInputStream()-----------" + fileToBeUpload.getInputStream());
                fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                System.out.println("------------folderId------------::" + folderID);
                System.out.println("------------dokumenID------------::" + dokumenID);
                //String fizikalPath = folderID + File.separator + dokumenID;
                updatePathDokumen(fizikalPath, Long.parseLong(dokumenID), fileToBeUpload.getContentType(), catatan);
                addSimpleMessage("Muat naik fail berjaya.");
            } catch (Exception ex) {
                Logger.getLogger(PenguatkuasaanStrataActionBean.class).error(ex);
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } else {
            addSimpleError("Muat naik fail tidak berjaya.");
        }
        return new JSP("strata/kuatkuasa/muat_naik_kaunter.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan) {
        System.out.println("------------updatePathDokumen------------::");
        System.out.println("------------namaFizikal------------::" + namaFizikal);
        System.out.println("------------format------------::" + format);
        System.out.println("------------catatan------------::" + catatan);
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null && StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    //Murali
    public Resolution showForm2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("strata/kuatkuasa/diari_penyiasatan.jsp").addParameter("tab", "true");
    }

    //Murali
    public Resolution tambahSiasatan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("strata/kuatkuasa/tambah_siasatan.jsp").addParameter("popup", "true");
    }

    //Murali
    public Resolution simpanSiasatan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("DIARI");
        permohonankertas = strService.findKertasByKod(idPermohonan, "DIARI");

        if (permohonankertas != null) {
            infoAudit = permohonankertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            strService.simpanPermohonanKertas(permohonankertas);

            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            kertasKdgn.setKertas(permohonankertas);
            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setBil(permohonankertas.getSenaraiKandungan().size() + 1);

            String strMasa = tarikhSiasat + " " + jam + ":" + minit + ":00 " + masa;
            Date tarikh = sdf1.parse(strMasa);
            kertasKdgn.setTarikhButiran(tarikh);
            kertasKdgn.setKandungan(tajuk);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            strService.simpanPermohonanKertasKandungan(kertasKdgn);

        } else {
            permohonankertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonankertas.setInfoAudit(infoAudit);
            permohonankertas.setPermohonan(permohonan);
            permohonankertas.setCawangan(permohonan.getCawangan());
            permohonankertas.setTajuk("Diari Siasatan");
            permohonankertas.setKodDokumen(kd);
            strService.simpanPermohonanKertas(permohonankertas);

            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            kertasKdgn.setKertas(permohonankertas);
            kertasKdgn.setInfoAudit(infoAudit);
            String strMasa = tarikhSiasat + " " + jam + ":" + minit + ":00" + masa;
            Date tarikh = sdf1.parse(strMasa);
            kertasKdgn.setTarikhButiran(tarikh);
            kertasKdgn.setKandungan(tajuk);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            strService.simpanPermohonanKertasKandungan(kertasKdgn);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new JSP("strata/kuatkuasa/diari_penyiasatan.jsp").addParameter("tab", "true");

    }

    //Murali
    public Resolution kemaskiniSiasatan() {

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        tajuk = getContext().getRequest().getParameter("tajuk");
        permohonankertasKandungan = permohonanKertasKandunganDAO.findById(Long.valueOf(idKandungan));
        tarikhSiasat = sdf1.format(permohonankertasKandungan.getTarikhButiran()).substring(0, 10);
        System.out.println("-------tarikhSiasat------" + tarikhSiasat);
        jam = sdf1.format(permohonankertasKandungan.getTarikhButiran()).substring(11, 13);
        System.out.println("-------jam------" + jam);
        minit = sdf1.format(permohonankertasKandungan.getTarikhButiran()).substring(14, 16);
        System.out.println("-------minit------" + minit);
        masa = sdf1.format(permohonankertasKandungan.getTarikhButiran()).substring(20, 22);
        System.out.println("-------masa------" + masa);
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("strata/kuatkuasa/kemaskini_siasatan.jsp").addParameter("popup", "true");
    }

    //Murali
    public Resolution simpanKemaskiniSiasatan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan kertasKandunganTemp = new PermohonanKertasKandungan();
        kertasKandunganTemp = permohonanKertasKandunganDAO.findById(permohonankertasKandungan.getIdKandungan());
        infoAudit = kertasKandunganTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        kertasKandunganTemp.setInfoAudit(infoAudit);
        kertasKandunganTemp.setTarikhButiran(permohonankertasKandungan.getTarikhButiran());
        kertasKandunganTemp.setKandungan(permohonankertasKandungan.getKandungan());
        try {
            String strMasa = tarikhSiasat + " " + jam + ":" + minit + ":00 " + masa;
            System.out.println("-------strMasa------::" + strMasa);
            Date tarikh = sdf1.parse(strMasa);
            System.out.println("-------tarikh------::" + tarikh);
            kertasKandunganTemp.setTarikhButiran(tarikh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        strService.simpanPermohonanKertasKandungan(kertasKandunganTemp);
        rehydrate();
        addSimpleMessage("Data Telah Berjaya dikemaskini");
        return new JSP("strata/kuatkuasa/diari_penyiasatan.jsp").addParameter("tab", "true");

    }

    //Murali
    public Resolution hapusSiasatan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idKandungan = getContext().getRequest().getParameter("idKandungan");
        if (idKandungan != null && !idKandungan.equals("")) {
            permohonankertasKandungan = permohonanKertasKandunganDAO.findById(Long.valueOf(idKandungan));
            strService.deleteKertasKandungan(permohonankertasKandungan);
        }
        rehydrate();
        return new JSP("strata/kuatkuasa/diari_penyiasatan.jsp").addParameter("tab", "true");
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public StrataPtService getPtService() {
        return ptService;
    }

    public void setPtService(StrataPtService ptService) {
        this.ptService = ptService;
    }

    public PermohonanKertasKandungan getPermohonankertasKandungan() {
        return permohonankertasKandungan;
    }

    public void setPermohonankertasKandungan(PermohonanKertasKandungan permohonankertasKandungan) {
        this.permohonankertasKandungan = permohonankertasKandungan;
    }

    public AduanStrata getAduanStrata() {
        return aduanStrata;
    }

    public void setAduanStrata(AduanStrata aduanStrata) {
        this.aduanStrata = aduanStrata;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PenguatkuasaanStrataActionBean.logger = logger;
    }

    public String getAduanStratahakmilik() {
        return aduanStratahakmilik;
    }

    public void setAduanStratahakmilik(String aduanStratahakmilik) {
        this.aduanStratahakmilik = aduanStratahakmilik;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }
}

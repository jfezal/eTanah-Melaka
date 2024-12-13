/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import etanah.model.KodKeputusan;
//import oracle.bpel.services.common.concurrent.Task;
import etanah.model.LaporanTanah;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.Pihak;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
//import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.PelupusanPtService;
import etanah.service.RegService;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Vector;
import oracle.bpel.services.workflow.task.model.Task;


/**
 *
 * @author Murali
 */
@UrlBinding("/pelupusan/kertas_mmkNS")
public class KertasMMKNSActionBean extends AbleActionBean {
    Logger logger = Logger.getLogger(DrafMMKNActionBean.class);
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    private Permohonan permohonan;
    @Inject
    PelupusanService pservice;
//    @Inject
//    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    BPelService service;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    // private Permohonan permohonan;
    private Pengguna peng;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasKandungan;
    private KodDokumen kd;
    private static final Logger LOG = Logger.getLogger(KertasMMKNSActionBean.class);
    private String tajuk;
    private Pemohon pemohon;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanPermitItem permohonanPermitItem;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonanJKTD;
    private String idPermohonan;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan3;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG2;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List<PermohonanRujukanLuar> senaraiLaporanKandunganRujukLuar;
    private PermohonanLaporanPelan  permohonanLaporPelan;
    private Vector senaraiLaporanKandunganUtil;
    private String index;
    private String kodSyaratNyata;
    private String kodSktn;
    private String kodKategori;
    private String kod;
    private String tujuan;
    private String ulasanjb1;
    private String ulasanjb2;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String syaratNyata2;
    private String kodSekatanKepentingan;
    private String sekatKpntgn2;
    private String hurianpentadbir1;
    private String hurianpentadbir2;
    private String syortolak;
    private KodKeputusan kodKeputusan;
    private String kodKepu;
    private String stageId;
    private String trhksidang ;
    private String pihakName;
    private String edit;
    private String lokasi;
    private String tempohHM;
    private String kodItemPermit;
    private Date datePTG;
    private Date dateJKTD;
    private boolean editPTD;
    private boolean editPTG;
    private boolean openPTG;
    private boolean openPTD;
    private String convTempat;
    private String convNama;
    private LaporanTanah laporanTanah;
    private Pihak pihak;
    private PemohonHubungan pemohonHubungan;
    private String alamatPenuhPihak;
    private String alamatPenuhPhbgn;
    private String syaratNyata;
    private String syaratNyata1;
    private String tahunselama;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public String getTrhksidang() {
        return trhksidang;
    }

    public void setTrhksidang(String trhksidang) {
        this.trhksidang = trhksidang;
    }

    public FasaPermohonan getFasaPermohonanJKTD() {
        return fasaPermohonanJKTD;
    }

    public void setFasaPermohonanJKTD(FasaPermohonan fasaPermohonanJKTD) {
        this.fasaPermohonanJKTD = fasaPermohonanJKTD;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPTG() {
        return senaraiLaporanKandunganPTG;
    }

    public void setSenaraiLaporanKandunganPTG(List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG) {
        this.senaraiLaporanKandunganPTG = senaraiLaporanKandunganPTG;
    }

    public String getKodItemPermit() {
        return kodItemPermit;
    }

    public void setKodItemPermit(String kodItemPermit) {
        this.kodItemPermit = kodItemPermit;
    }
            
    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPTG2() {
        return senaraiLaporanKandunganPTG2;
    }

    public void setSenaraiLaporanKandunganPTG2(List<PermohonanKertasKandungan> senaraiLaporanKandunganPTG2) {
        this.senaraiLaporanKandunganPTG2 = senaraiLaporanKandunganPTG2;
    }

    public String getTahunselama() {
        return tahunselama;
    }

    public void setTahunselama(String tahunselama) {
        this.tahunselama = tahunselama;
    }

    public String getTempohHM() {
        return tempohHM;
    }

    public void setTempohHM(String tempohHM) {
        this.tempohHM = tempohHM;
    }
    
    
    
    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public FasaPermohonanDAO getFasaPermohonanDAO() {
        return fasaPermohonanDAO;
    }

    public void setFasaPermohonanDAO(FasaPermohonanDAO fasaPermohonanDAO) {
        this.fasaPermohonanDAO = fasaPermohonanDAO;
    }

    public String getKodKepu() {
        return kodKepu;
    }

    
    
    public void setKodKepu(String kodKepu) {
        this.kodKepu = kodKepu;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }
    
    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }
    
    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }


    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public PermohonanPermitItemDAO getPermohonanPermitItemDAO() {
        return permohonanPermitItemDAO;
    }

    public void setPermohonanPermitItemDAO(PermohonanPermitItemDAO permohonanPermitItemDAO) {
        this.permohonanPermitItemDAO = permohonanPermitItemDAO;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanTuntutanKosDAO getPermohonanTuntutanKosDAO() {
        return permohonanTuntutanKosDAO;
    }

    public void setPermohonanTuntutanKosDAO(PermohonanTuntutanKosDAO permohonanTuntutanKosDAO) {
        this.permohonanTuntutanKosDAO = permohonanTuntutanKosDAO;
    }

    public String getSyortolak() {
        return syortolak;
    }

    public void setSyortolak(String syortolak) {
        this.syortolak = syortolak;
    }

    public String getHurianpentadbir1() {
        return hurianpentadbir1;
    }

    public void setHurianpentadbir1(String hurianpentadbir1) {
        this.hurianpentadbir1 = hurianpentadbir1;
    }

    public String getHurianpentadbir2() {
        return hurianpentadbir2;
    }

    public void setHurianpentadbir2(String hurianpentadbir2) {
        this.hurianpentadbir2 = hurianpentadbir2;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public KodSekatanKepentinganDAO getKodSekatanKepentinganDAO() {
        return kodSekatanKepentinganDAO;
    }

    public void setKodSekatanKepentinganDAO(KodSekatanKepentinganDAO kodSekatanKepentinganDAO) {
        this.kodSekatanKepentinganDAO = kodSekatanKepentinganDAO;
    }

    public KodSyaratNyataDAO getKodSyaratNyataDAO() {
        return kodSyaratNyataDAO;
    }

    public void setKodSyaratNyataDAO(KodSyaratNyataDAO kodSyaratNyataDAO) {
        this.kodSyaratNyataDAO = kodSyaratNyataDAO;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public String getConvTempat() {
        return convTempat;
    }

    public void setConvTempat(String convTempat) {
        this.convTempat = convTempat;
    }
    
    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public String getUlasanjb1() {
        return ulasanjb1;
    }

    public void setUlasanjb1(String ulasanjb1) {
        this.ulasanjb1 = ulasanjb1;
    }

    public String getUlasanjb2() {
        return ulasanjb2;
    }

    public void setUlasanjb2(String ulasanjb2) {
        this.ulasanjb2 = ulasanjb2;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public PelupusanService getPelupusanService() {
        return pservice;
    }

    public void setPelupusanService(PelupusanService pservice) {
        this.pservice = pservice;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodKategori() {
        return kodKategori;
    }

    public void setKodKategori(String kodKategori) {
        this.kodKategori = kodKategori;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public PelupusanService getPservice() {
        return pservice;
    }

    public void setPservice(PelupusanService pservice) {
        this.pservice = pservice;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
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

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan3() {
        return senaraiLaporanKandungan3;
    }

    public void setSenaraiLaporanKandungan3(List<PermohonanKertasKandungan> senaraiLaporanKandungan3) {
        this.senaraiLaporanKandungan3 = senaraiLaporanKandungan3;
    }

    public Vector getSenaraiLaporanKandunganUtil() {
        return senaraiLaporanKandunganUtil;
    }

    public void setSenaraiLaporanKandunganUtil(Vector senaraiLaporanKandunganUtil) {
        this.senaraiLaporanKandunganUtil = senaraiLaporanKandunganUtil;
    }

    public List<PermohonanRujukanLuar> getSenaraiLaporanKandunganRujukLuar() {
        return senaraiLaporanKandunganRujukLuar;
    }

    public void setSenaraiLaporanKandunganRujukLuar(List<PermohonanRujukanLuar> senaraiLaporanKandunganRujukLuar) {
        this.senaraiLaporanKandunganRujukLuar = senaraiLaporanKandunganRujukLuar;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public boolean isEditPTG() {
        return editPTG;
    }

    public void setEditPTG(boolean editPTG) {
        this.editPTG = editPTG;
    }

    public boolean isOpenPTD() {
        return openPTD;
    }

    public void setOpenPTD(boolean openPTD) {
        this.openPTD = openPTD;
    }

    public boolean isOpenPTG() {
        return openPTG;
    }

    public void setOpenPTG(boolean openPTG) {
        this.openPTG = openPTG;
    }

    public Date getDateJKTD() {
        return dateJKTD;
    }

    public void setDateJKTD(Date dateJKTD) {
        this.dateJKTD = dateJKTD;
    }

    public Date getDatePTG() {
        return datePTG;
    }

    public void setDatePTG(Date datePTG) {
        this.datePTG = datePTG;
    }


    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public String getAlamatPenuhPhbgn() {
        return alamatPenuhPhbgn;
    }

    public void setAlamatPenuhPhbgn(String alamatPenuhPhbgn) {
        this.alamatPenuhPhbgn = alamatPenuhPhbgn;
    }

    public String getAlamatPenuhPihak() {
        return alamatPenuhPihak;
    }

    public void setAlamatPenuhPihak(String alamatPenuhPihak) {
        this.alamatPenuhPihak = alamatPenuhPihak;
    }
    
    



    @DefaultHandler
    public Resolution showForm() {
        /*
         * FOR EDITABLE PTD
         */
        openPTD = Boolean.TRUE;
        editPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTG = Boolean.FALSE;
       return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public Resolution viewFormPTD() {
        /*
         * FOR NON EDITABLE PTD
         */
        openPTD = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        openPTG = Boolean.FALSE;
        editPTG = Boolean.FALSE;
       return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public Resolution showPTG() {
        /*
         * FOR EDITABLE PTG
         */
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.TRUE;
       return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public Resolution viewFormPTG() {
        /*
         * FOR NON EDITABLE PTG
         */
        openPTD = Boolean.TRUE;
        openPTG = Boolean.TRUE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
       return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public Resolution showForm1() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

       return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getConvNama() {
        return convNama;
    }

    public void setConvNama(String convNama) {
        this.convNama = convNama;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohonanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
    }

    

    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSyaratNyata_lptn.jsp").addParameter("popup", "true");
    }

    public String getPihakName() {
        return pihakName;
    }

    public void setPihakName(String pihakName) {
        this.pihakName = pihakName;
    }
    
    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn_lptn.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        pemohon = pservice.findPemohonPihak(idPermohonan);
        LOG.info("---------pemohon---------::" + pemohon);
        mohonHakmilik = pservice.findMohonHakmilik(idPermohonan);
        LOG.info("---------mohonHakmilik---------::" + mohonHakmilik);
        if(mohonHakmilik!=null){
            if(mohonHakmilik.getTempohHakmilik()!=null){
                if(mohonHakmilik.getTempohHakmilik().equals("999")){
                    tahunselama = "0";
                    tempohHM = "0";
                }
                else{
                    tahunselama = "99";
                    tempohHM = mohonHakmilik.getTempohHakmilik();
                }
            }
            if(mohonHakmilik.getSyaratNyataBaru()!=null){
                kod = mohonHakmilik.getSyaratNyataBaru().getKod();
                syaratNyata = mohonHakmilik.getSyaratNyataBaru().getSyarat();
            }
            if(mohonHakmilik.getSekatanKepentinganBaru()!=null){
                kodSktn = mohonHakmilik.getSekatanKepentinganBaru().getKod();
                syaratNyata1 = mohonHakmilik.getSekatanKepentinganBaru().getSekatan();
            }
        }
        
        String pihakName = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakName = pemohon.getPihak().getNama();
            pihak = pemohon.getPihak();
            pemohonHubungan = pservice.findHubunganByIDSuamiIsteri(pemohon.getIdPemohon());
            if(pemohonHubungan!=null){
            if(pemohonHubungan.getAlamat1()!=null&&!("").equals(pemohonHubungan.getAlamat1()))
                alamatPenuhPhbgn = pemohonHubungan.getAlamat1();
            if(pemohonHubungan.getAlamat2()!=null&&!("").equals(pemohonHubungan.getAlamat2()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat2();
            if(pemohonHubungan.getAlamat3()!=null&&!("").equals(pemohonHubungan.getAlamat3()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat3();
            if(pemohonHubungan.getAlamat4()!=null&&!("").equals(pemohonHubungan.getAlamat4()))
                alamatPenuhPhbgn = alamatPenuhPhbgn +", "+pemohonHubungan.getAlamat4();
            }
            if(pihak!=null){
                if(pihak.getAlamat1()!=null&&!("").equals(pihak.getAlamat1())){
                alamatPenuhPihak = pihak.getAlamat1();
                }if(pihak.getAlamat2()!=null&&!("").equals(pihak.getAlamat2())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat2();
                }if(pihak.getAlamat3()!=null&&!("").equals(pihak.getAlamat3())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat3();
                }if(pihak.getAlamat4()!=null&&!("").equals(pihak.getAlamat4())){
                    alamatPenuhPihak = alamatPenuhPihak +", "+ pihak.getAlamat4();
                }
            }
        }


        String mhtetap = " ";
        if ((mohonHakmilik != null) && mohonHakmilik.getKodHakmilikTetap() != null) {
            mhtetap = mohonHakmilik.getKodHakmilikTetap().getNama();
        }

        BigDecimal luas = BigDecimal.ZERO;
        if(mohonHakmilik.getLuasTerlibat() != null){
            luas = mohonHakmilik.getLuasTerlibat();

        }

        String koduom = " ";
        if(mohonHakmilik.getKodUnitLuas() != null){
            koduom = mohonHakmilik.getKodUnitLuas().getNama();
        }

        String lokasi = " ";
        if(mohonHakmilik.getLokasi() != null){
            lokasi = mohonHakmilik.getLokasi();
        }

        String kodbpm = " ";
        if(mohonHakmilik.getBandarPekanMukimBaru() != null){
            kodbpm = mohonHakmilik.getBandarPekanMukimBaru().getNama();
        }

        String koddaerah = " ";
        if(mohonHakmilik.getHakmilik() != null){
            koddaerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
        }
        
        String kodtanah = " ";
        if(mohonHakmilik.getJenisPenggunaanTanah() != null){
            kodtanah = mohonHakmilik.getJenisPenggunaanTanah().getNama();
        }

        String msebab = " ";
        if(permohonan != null && permohonan.getSebab() != null){
            msebab = permohonan.getSebab();
        }


//        if ((mohonHakmilik != null) && (permohonan != null)) {
//            tajuk = " permohonan daripada  " + pihakName + " untuk memiliki tanah kerajaan secara" + " "
//                    + mhtetap + " " + " di bawah seksyen 76 kanun tanah negara 1965 seluas lebih kurang" + " " + luas + " "
//                    + " " + koduom + " " + " di" + " " + lokasi + " , " + " mukim "
//                    + kodbpm + " , " + " daerah " + " " +koddaerah + " "
//                    + kodtanah + " " + " ( " + msebab + " )";
//            //   pemohon.getPihak().getNama() +
//        }
        pihakName = pihakName!=null?pelUtiliti.convertStringtoInitCap(pihakName):pihakName;
        lokasi = lokasi!=null?pelUtiliti.convertStringtoInitCap(lokasi):lokasi;
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "huraian_syor_mmk"); //NOTE: FOR PBMT ONLY, GET PTG for date PTG received
        if(mohonFasa!=null)
            datePTG = mohonFasa.getInfoAudit().getTarikhMasuk();
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pservice.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKTD"); //NOTE : FOR PBMT ONLY, TO GET JKTD date 
        if(mohonKertas!=null)
                dateJKTD = mohonKertas.getTarikhSidang();
        senaraiLaporanKawasan = pservice.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
        convNama = pelUtiliti.convertStringtoInitCap(pihakName);
        convTempat = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getLokasi());
        permohonanLaporPelan = pservice.findByIdPermohonanPelan(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        List<LaporanTanah> listLaporanTanah;
        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

        if (!(listLaporanTanah.isEmpty())) {
            laporanTanah = listLaporanTanah.get(0);
        }
//        tujuan = "1.1 " + " Kertas in bertujuan untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus mengenai Permohonan daripada"
//                + " " + pihakName!=null?pelUtiliti.convertStringtoInitCap(pihakName):pihakName + " " + " seperti berikut" + '\n' + '\n'
//                + "  i )" + " " + " Untuk memilik tanah Kerajaan secara Hakmilik Tetap / Lesen Pendudukan Sementara" + '\n' + '\n'
//                + " ii )" + " Seluas lebihkurang" + luas + " " + koduom + '\n' + '\n'
//                + "iii )" + " di  " + lokasi!=null?pelUtiliti.convertStringtoInitCap(lokasi):lokasi + " mukim" + kodbpm!=null?pelUtiliti.convertStringtoInitCap(kodbpm):kodbpm + " daerah Seremban" + '\n' + '\n'
//                + " iv )" + " Untuk kegunaan" + " " + kodtanah!=null?pelUtiliti.convertStringtoInitCap(kodtanah):kodtanah + " " + " ( " + msebab!=null?pelUtiliti.convertStringtoInitCap(msebab):msebab + " )";
//          
//        
        
//        permohonanRujukanLuar = pservice.findIDPermohonanRujByIdPermohonan(idPermohonan);
//
//
//        String agencynama = " ";
//        if(permohonanRujukanLuar != null && permohonanRujukanLuar.getAgensi() != null){
//            agencynama = permohonanRujukanLuar.getAgensi().getNama();
//        }
//
//
//        String sokong = " ";
//        if(permohonanRujukanLuar != null && permohonanRujukanLuar.getDiSokong() != null){
//            sokong = permohonanRujukanLuar.getDiSokong()+"";
//        }
//
//        String ulasan = " ";
//        if(permohonanRujukanLuar != null && permohonanRujukanLuar.getUlasan() != null){
//            ulasan = permohonanRujukanLuar.getUlasan()+"";
//        }
//
//
//        ulasanjb1 = " " + agencynama + '\n' + '\n'
//                + " " + sokong + '\n' + '\n'
//                + " " + ulasan;



        if (mohonHakmilik.getSyaratNyataBaru() != null) {
            kod = mohonHakmilik.getSyaratNyataBaru().getKod();
        }
        if (mohonHakmilik.getSekatanKepentinganBaru() != null) {
            kodSktn = mohonHakmilik.getSekatanKepentinganBaru().getKod();
        }
        if (mohonHakmilik.getKategoriTanahBaru() != null) {
            kodKategori = mohonHakmilik.getKategoriTanahBaru().getKod();
        }

        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");
        LOG.info("---------rehydrate-----permohonankertas--------------::" + permohonanKertas);
        PermohonanKertasKandungan mkk = new PermohonanKertasKandungan();
        if(permohonanKertas!=null)
            mkk = pservice.findByBilNIdKertas(5, permohonanKertas.getIdKertas()); 
//        if (permohonanKertas != null) {
//            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
//                kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
//
////                if (kertasKandungan.getBil() == 0) {
////                    tajuk = kertasKandungan.getKandungan();
////                }else  if (kertasKandungan.getBil() == 1) {
////                    tujuan = kertasKandungan.getKandungan();
////                } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("2.1")) {
////                    ulasanjb1 = kertasKandungan.getKandungan();
////                } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("2.2")) {
////                    ulasanjb2 = kertasKandungan.getKandungan();
// //               }
////                else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("3.1")) {
////                    hurianpentadbir1 = kertasKandungan.getKandungan();
////                } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("3.2")) {
////                    hurianpentadbir2 = kertasKandungan.getKandungan();
//                }
//                if(kertasKandungan.getBil() == 5){
//                    if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("1")) {
//                        syortolak = kertasKandungan.getKandungan();
//                    }
//                }
//        }

        if (permohonanKertas != null) {
        if(mkk!=null){
            syortolak = mkk.getKandungan();
        }   
        senaraiLaporanKandungan1 = pservice.findByIdLapor(permohonanKertas.getIdKertas(),4);
        senaraiLaporanKandunganPTG = pservice.findByIdLapor(permohonanKertas.getIdKertas(),7);
        senaraiLaporanKandunganPTG2 = pservice.findByIdLapor(permohonanKertas.getIdKertas(),8);
        if(senaraiLaporanKandungan1.isEmpty())
           senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
       

        if(permohonanKertas.getTarikhSidang() != null){
        trhksidang = sdf.format(permohonanKertas.getTarikhSidang());
        }
        }else{
          senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
          senaraiLaporanKandunganPTG = new ArrayList<PermohonanKertasKandungan>();
          senaraiLaporanKandunganPTG2 = new ArrayList<PermohonanKertasKandungan>();
        }

        permohonanTuntutanKos = pservice.findMohontuntutkos(idPermohonan);
        permohonanPermitItem = pservice.findByIdMohonPermitItem(idPermohonan);
        if(permohonanPermitItem!=null){
            kodItemPermit = permohonanPermitItem.getKodItemPermit().getKod();
        }
        //fasaPermohonan = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, tujuan);


        // getting stage id

//        String taskId = (String)getContext().getRequest().getSession().getAttribute("taskId");
//        if (StringUtils.isBlank(taskId)) {
//          taskId = getContext().getRequest().getParameter("taskId");
//      }
//        Task task = service.getTaskFromBPel(taskId, peng);



        //REMOVE
//       stageId ="murali";
//       fasaPermohonan = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        //NOTE stageId FOR MOHON FASA NEED TO BE SEDIA_DRAF_MMK SO BECAUSE THE KPSN ARE IN THERE
        if(permohonan.getKodUrusan().getKod().equals("PBMT")){
           fasaPermohonan = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_mmk");
           fasaPermohonanJKTD = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "terima_keputusan_jktd"); //NOTE : THIS IS FOR PERAKUAN JKTD
        }
        else
           fasaPermohonan = pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
         if(fasaPermohonan!= null && fasaPermohonan.getKeputusan()!= null){
             kodKepu = fasaPermohonan.getKeputusan().getKod();
         }
         
       /*
        * MOHON_RUJUK_LUAR
        */
         senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        if (permohonanKertas != null) {
            senaraiLaporanKandunganUtil = new Vector();
            //senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
            senaraiLaporanKandunganRujukLuar = pservice.findByIdMohonRujukLuar(idPermohonan);
            for(int i=0;i<senaraiLaporanKandunganRujukLuar.size();i++){
                //PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                //kand1 = senaraiLaporanKandungan2.get(i);
                PermohonanRujukanLuar kand1 = new PermohonanRujukanLuar();
                kand1 = senaraiLaporanKandunganRujukLuar.get(i);
                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                pelUtil.setPermohonanRujukanLuar(kand1);
                if(kand1.getAgensi()!=null){
                    if(kand1.getAgensi().getKategoriAgensi().getKod().equals("JTK"))
                        senaraiLaporanKandunganUtil.add(pelUtil);
                }
            }
            logger.info("------if-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        } else {
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiLaporanKandunganUtil = new Vector();
            senaraiPermohonanRujLuar = pservice.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
            logger.info("-----------senaraiPermohonanRujLuar-------:" + senaraiPermohonanRujLuar.size());
            if (!senaraiPermohonanRujLuar.isEmpty()) {
                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                    
                    PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                    if(rujLuar.getUlasan() == null){
                        rujLuar.setUlasan("Tiada ulasan diterima");
                    }
                    pelUtil.setPermohonanRujukanLuar(rujLuar);
                    //kand1.setKandungan(kandVal);
                    //kand1.setSubtajuk(rujLuar.getAgensi().getNama());
                    if(rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK"))
                       //senaraiLaporanKandungan2.add(kand1);
                        senaraiLaporanKandunganUtil.add(pelUtil);
                }
            }


            logger.info("------else-----senaraiLaporanKandungan2-------:" + senaraiLaporanKandungan2.size());
        }
       /*
        * END
        */

    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pservice.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pservice.searchKodSyaratNyata("%", kc, syaratNyata2);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = pservice.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pservice.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
    }
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
    public Resolution simpan() {

        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKS");
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");
        
        /*
         * DISABLE TO FOLLOW NEW MMK SYNTAX 12/09/2011
         */
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();
//
//        
//         if (tajuk == null || tajuk.equals("")) {
//            tajuk = "TIADA DATA";
//           }
//
//        if (tujuan == null || tujuan.equals("")) {
//            tujuan = "TIADA DATA";
//        }
//        if (ulasanjb1 == null || ulasanjb1.equals("")) {
//            ulasanjb1 = "TIADA DATA";
//        }
//        if (ulasanjb2 == null || ulasanjb2.equals("")) {
//            ulasanjb2 = "TIADA DATA";
//        }
//        if (hurianpentadbir1 == null || hurianpentadbir1.equals("")) {
//            hurianpentadbir1 = "TIADA DATA";
//        }
//
//        if (hurianpentadbir2 == null || hurianpentadbir2.equals("")) {
//            hurianpentadbir2 = "TIADA DATA";
//        }
//
//        if (syortolak == null || syortolak.equals("")) {
//            syortolak = "TIADA DATA";
//        }
//
//
//        listUlasan.add(tajuk);
//        listUlasan.add(tujuan);
//        listUlasan.add(ulasanjb1);
//        listUlasan.add(ulasanjb2);
////        listUlasan.add(hurianpentadbir1);
////        listUlasan.add(hurianpentadbir2);
//        listUlasan.add(syortolak);
//
//        listSubtajuk.add(" ");
//        listSubtajuk.add(" ");
//        listSubtajuk.add("2.1");
//        listSubtajuk.add("2.2");
////        listSubtajuk.add("3.1");
////        listSubtajuk.add("3.2");
//
//        listSubtajuk.add("4.1");
//
//        billNo.add("0");
//        billNo.add("1");
//        billNo.add("2");
//        billNo.add("2");
//        billNo.add("4");


//        if (permohonanKertas != null) {
//            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            pservice.simpanPermohonanKertas(permohonanKertas);
//            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {
//
//                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
//                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
//                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
//
//                   if (kertasKandungan.getBil() == 0) {
//                        kertasKdgn.setKandungan(tajuk);
//                    }
//
//                    if (kertasKandungan.getBil() == 1) {
//                        kertasKdgn.setKandungan(tujuan);
//                    }
////                    if (kertasKandungan.getSubtajuk().equals("2.1")) {
////                        kertasKdgn.setKandungan(ulasanjb1);
////                    }
////                    if (kertasKandungan.getSubtajuk().equals("2.2")) {
////                        kertasKdgn.setKandungan(ulasanjb2);
////                    }
////                    if (kertasKandungan.getSubtajuk().equals("3.1")) {
////                        kertasKdgn.setKandungan(hurianpentadbir1);
////                    }
////                    if (kertasKandungan.getSubtajuk().equals("3.2")) {
////                        kertasKdgn.setKandungan(hurianpentadbir2);
////                    }
//                    if (kertasKandungan.getSubtajuk().equals("4.1")) {
//                        kertasKdgn.setKandungan(syortolak);
//                    }
//
//                    kertasKdgn.setInfoAudit(infoAudit);
//                    pservice.simpanPermohonanKertasKandungan(kertasKdgn);
//                }
//            }
//
//
//            senaraiLaporanKandungan3 = pservice.findByIdLapor(permohonanKertas.getIdKertas(), 3);
//              LOG.info("---------senaraiLaporanKandungan3--------:"+senaraiLaporanKandungan3);
//              LOG.info("---------senaraiLaporanKandungan3--size------:"+senaraiLaporanKandungan3.size());
//
//              int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//              LOG.info("---------kira--------:"+kira);
//              for (int i = 1; i <= kira; i++) {
//              InfoAudit iaP = new InfoAudit();
//                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                if (senaraiLaporanKandungan3.size() != 0 && i <= senaraiLaporanKandungan3.size()) {
//                LOG.info("---------if--------:");
//                   Long id = senaraiLaporanKandungan3.get(i - 1).getIdKandungan();
//                   LOG.info("---------id--------:"+id);
//                    if (id != null) {
//                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
//                        LOG.info("---------Bil--------:"+permohonanKertasKandungan1.getBil());
//                        iaP = permohonanKertasKandungan1.getInfoAudit();
//                        iaP.setTarikhKemaskini(new Date());
//                        iaP.setDimasukOleh(peng);
//                        LOG.info("------SubTajuk-----------:"+permohonanKertasKandungan1.getSubtajuk());
//                    }
//                } else {
//                    LOG.info("---------else--------:");
//                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                    permohonanKertasKandungan1.setBil(3);
//                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan1.setSubtajuk("3." + i);
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    LOG.info("---------else-------end-:");
//                }
//                permohonanKertasKandungan1.setKertas(permohonanKertas);
//                LOG.info("---------i--------:"+i);
//                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
//                LOG.info("---------kandungan--------:"+kandungan);
//                permohonanKertasKandungan1.setKandungan(kandungan);
//                permohonanKertasKandungan1.setInfoAudit(iaP);
//                LOG.info("---------Bil-2-------:"+permohonanKertasKandungan1.getBil());
//                LOG.info("------SubTajuk2-----------:"+permohonanKertasKandungan1.getSubtajuk());
//                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//        }
//
//        } 
//        else {
//            LOG.info("------else------permohonankertas:: NULL--------------::" + permohonanKertas);
//
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            permohonanKertas.setInfoAudit(infoAudit);
//            permohonanKertas.setPermohonan(permohonan);
//            permohonanKertas.setCawangan(permohonan.getCawangan());
//            permohonanKertas.setTajuk("KERTAS MMK");
//            kd = kodDokumenDAO.findById("MMKS");
//            permohonanKertas.setKodDokumen(kd);
//            pservice.simpanPermohonanKertas(permohonanKertas);
//            for (int i = 0; i < listUlasan.size(); i++) {
//                String ulasan = (String) listUlasan.get(i);
//                String billNo1 = billNo.get(i);
//                String subTajuk = listSubtajuk.get(i);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
//                kertasKdgn.setKertas(permohonanKertas);
//                // kertasKdgn.setBil(i + 1);
//                kertasKdgn.setBil(Integer.parseInt(billNo1));
//                kertasKdgn.setSubtajuk(subTajuk);
//                kertasKdgn.setInfoAudit(infoAudit);
//                kertasKdgn.setKandungan(ulasan);
//                kertasKdgn.setCawangan(permohonan.getCawangan());
//                pservice.simpanPermohonanKertasKandungan(kertasKdgn);
//            }
//
//             senaraiLaporanKandungan3 = pservice.findByIdLapor(permohonanKertas.getIdKertas(), 3);
//              LOG.info("---------senaraiLaporanKandungan3--------:"+senaraiLaporanKandungan3);
//              LOG.info("---------senaraiLaporanKandungan3--size------:"+senaraiLaporanKandungan3.size());
//
//              int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//              LOG.info("---------kira--------:"+kira);
//              for (int i = 1; i <= kira; i++) {
//              InfoAudit iaP = new InfoAudit();
//                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                if (senaraiLaporanKandungan3.size() != 0 && i <= senaraiLaporanKandungan3.size()) {
//                LOG.info("---------if--------:");
//                   Long id = senaraiLaporanKandungan3.get(i - 1).getIdKandungan();
//                   LOG.info("---------id--------:"+id);
//                    if (id != null) {
//                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
//                        LOG.info("---------Bil--------:"+permohonanKertasKandungan1.getBil());
//                        iaP = permohonanKertasKandungan1.getInfoAudit();
//                        iaP.setTarikhKemaskini(new Date());
//                        iaP.setDimasukOleh(peng);
//                        LOG.info("------SubTajuk-----------:"+permohonanKertasKandungan1.getSubtajuk());
//                    }
//                } else {
//                    LOG.info("---------else--------:");
//                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//                    permohonanKertasKandungan1.setBil(3);
//                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan1.setSubtajuk("3." + i);
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    LOG.info("---------else-------end-:");
//                }
//                permohonanKertasKandungan1.setKertas(permohonanKertas);
//                LOG.info("---------i--------:"+i);
//                String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
//                LOG.info("---------kandungan--------:"+kandungan);
//                permohonanKertasKandungan1.setKandungan(kandungan);
//                permohonanKertasKandungan1.setInfoAudit(iaP);
//                LOG.info("---------Bil-2-------:"+permohonanKertasKandungan1.getBil());
//                LOG.info("------SubTajuk2-----------:"+permohonanKertasKandungan1.getSubtajuk());
//                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//        }
//
//
//
//
//        }
        //END OF DISABLE
        
        infoAudit = new InfoAudit();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        //Stage Id need to be fix to get kpsn since kpsn only involved at sedia_mmk only
        if(permohonan.getKodUrusan().getKod().equals("PBMT"))
           fasaPermohonan= pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_draf_mmk");
        else
            fasaPermohonan= pservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        
            if(fasaPermohonan == null){
                fasaPermohonan = new FasaPermohonan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setCawangan(pengguna.getKodCawangan());
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setIdAliran(stageId);
                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kodKepu));
                pservice.simpanFasaPermohonan(fasaPermohonan);
            }
            else{
                infoAudit = fasaPermohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kodKepu));
                LOG.info(fasaPermohonan.getKeputusan().getKod());
                if(fasaPermohonan.getKeputusan()!=null){
                    if(!StringUtils.isBlank(fasaPermohonan.getKeputusan().getKod()))
                        pservice.simpanFasaPermohonan(fasaPermohonan);
                }
            }
//mohon fasa table saving
        
        PermohonanKertas mohonKertas = new PermohonanKertas();
        mohonKertas = pservice.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "MMKS");
        if(mohonKertas!=null){
            PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
            mohonKertasKand = pservice.findByBilNIdKertas(5, mohonKertas.getIdKertas());
            infoAudit = new InfoAudit();
            
            if(mohonKertasKand!=null){
                infoAudit = mohonKertasKand.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonKertasKand.setKandungan(syortolak);
                mohonKertasKand.setInfoAudit(infoAudit);
            }else{
                mohonKertasKand = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                mohonKertasKand.setCawangan(mohonKertas.getCawangan());
                mohonKertasKand.setKertas(mohonKertas);
                mohonKertasKand.setBil(5);
                mohonKertasKand.setSubtajuk("1");
                mohonKertasKand.setKandungan(syortolak);
                mohonKertasKand.setInfoAudit(infoAudit);
            }
            pservice.simpanPermohonanKertasKandungan(mohonKertasKand);
        }
            // in MohonHakmilik

//            HakmilikPermohonan hmptemp = pservice.findByIdPermohonan(idPermohonan);
//            if (syaratNyata != null) {
//                    LOG.info("THIS IS SYARATNYATA ->"+syaratNyata);
//                        KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(syaratNyata);
//                        mohonHakmilik.setSyaratNyataBaru(kodSyarat);
//                    }
//
//                    if (syaratNyata1 != null) {
//                        KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(syaratNyata1);
//                        mohonHakmilik.setSekatanKepentinganBaru(sekatan);
//                    }
            if (kod != null) {
                        KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
                        mohonHakmilik.setSyaratNyataBaru(kodSyarat);
                    }

                    if (kodSktn != null) {
                        KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
                        mohonHakmilik.setSekatanKepentinganBaru(sekatan);
                    }
            if(tahunselama!=null){
                if(tahunselama.equals("0")){
                    mohonHakmilik.setTempohHakmilik("999"); //NOTE 999 refer to KEKAL
                }else{
                    if(tempohHM!=null)
                        mohonHakmilik.setTempohHakmilik(tempohHM);
                }
            }
//            hmptemp = pservice.findByIdPermohonan(idPermohonan);
            if(mohonHakmilik!= null){
            infoAudit = mohonHakmilik.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pservice.simpanHakmilikPermohonan(mohonHakmilik);
        } else {
            mohonHakmilik = new HakmilikPermohonan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            pservice.simpanHakmilikPermohonan(mohonHakmilik);
        }

            // in mohontuntukos

            permohonanTuntutanKos = new PermohonanTuntutanKos();

            permohonanTuntutanKos = pservice.findMohontuntutkos(idPermohonan);
            if(permohonanTuntutanKos!= null){
            infoAudit = permohonanTuntutanKos.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonanTuntutanKos.setInfoAudit(infoAudit);
            pservice.simpanPermohonanTuntutanKos(permohonanTuntutanKos);

            }else {
            permohonanTuntutanKos = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanTuntutanKos.setInfoAudit(infoAudit);
            permohonanTuntutanKos.setCawangan(permohonan.getCawangan());      
            permohonanTuntutanKos.setPermohonan(permohonan);
            permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISPRM").getNama());
            permohonanTuntutanKos.setAmaunTuntutan(new BigDecimal(0));
            pservice.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
            }

//         senaraiLaporanKandungan2 = new ArrayList<PermohonanKertasKandungan>();
//        senaraiLaporanKandungan2 = pservice.findByIdLapor(permohonanKertas.getIdKertas(), 3);
//        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
//        InfoAudit iaP = new InfoAudit();
//        for (int i = 1; i <= kira; i++) {
//            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            if (senaraiLaporanKandungan2.size() != 0 && i <= senaraiLaporanKandungan2.size()) {
//                Long id = senaraiLaporanKandungan2.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(3);
//            String kandungan = getContext().getRequest().getParameter("ulasan" + i);
//            
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("3." + i);
//            //InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(pengguna);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
////            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//            
//            String moh_rujLuar = getContext().getRequest().getParameter("rowAgensi" + i);
//            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
//            permohonanRujukanLuar = pservice.findByIdRujukan(Long.parseLong(moh_rujLuar));
//            permohonanRujukanLuar.setUlasan(kandungan);
//            pservice.simpanPermohonanRujLuar(permohonanRujukanLuar);
//        }   
        /*
         * permohonanPermitItem
         */
            permohonanPermitItem = pservice.findByIdMohonPermitItem(idPermohonan);
            
            if(permohonanPermitItem!= null){
                infoAudit = permohonanTuntutanKos.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodItemPermit));
                pservice.simpanPermohonanPermitItem(permohonanPermitItem);

            }else {
                permohonanPermitItem = new PermohonanPermitItem();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanPermitItem.setInfoAudit(infoAudit);
                permohonanPermitItem.setKodCawangan(permohonan.getCawangan());      
                permohonanPermitItem.setPermohonan(permohonan);
                permohonanPermitItem.setKodItemPermit(kodItemPermitDAO.findById(kodItemPermit));
                pservice.simpanPermohonanPermitItem(permohonanPermitItem);
            }
         /*
          * END OF MOHON PERMIT ITEM
          */
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("popup", "true");
    }
    
    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
//            case 2: //FOR Perihal Permohonan
//                updateKandungan(2, kand);
//
//                break;
//            case 3:
//
//                updateKandungan(3, kand);
//
//                break;
            case 4:  // FOR HURAIAN PENTADBIR TANAH DAERAH 

                updateKandungan(4, kand);

                break;
//            case 5:
//
//                updateKandungan(5, kand);
//
//                break;
//            case 6:
//
//                updateKandungan(6, kand);
//                break;
              case 7: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                  updateKandungan(7, kand);
                  break;
              case 8: //  FOR SYOR PENGARAH TANAH DAN GALIAN
                  updateKandungan(8, kand);
                  break;
//              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                  updateKandungan(9, kand);
//                  break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public void updateKandungan(int i, String kand) {


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("BMBT")) {
            permohonanKertas.setTajuk("Draf MMK");
            KodDokumen kod = kodDokumenDAO.findById("MMKS");
            permohonanKertas.setKodDokumen(kod);
        } 
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonanKertas);

        long a = permohonanKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);
        
        if(i==5){
            pLK = pservice.findByBilNIdKertas(5, permohonanKertas.getIdKertas());
            if(pLK==null){
                pLK = new PermohonanKertasKandungan();               
                pLK.setKertas(permohonanKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setSubtajuk("1");                
        }else{
            if (plk.isEmpty()) {
            pLK.setSubtajuk("1");
//            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setKertas(permohonanKertas);
            pLK.setInfoAudit(infoAudit);
            pLK.setCawangan(cawangan);
        }
        
        
        if(i==5){
            List senaraiLaporanKandunganTolak = new ArrayList <PermohonanKertasKandungan>();
            senaraiLaporanKandunganTolak = pservice.findByIdLapor(permohonanKertas.getIdKertas(),5);
            if(senaraiLaporanKandunganTolak.isEmpty()){
                pelPtService.simpanPermohonanKertasKandungan(pLK);
            }else{
                pelPtService.simpanUpdateOnlyPermohonanKertasKandungan(pLK);
            }            
        }             
        else
            pelPtService.simpanPermohonanKertasKandungan(pLK);

    }
    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
//            case 1:
//                break;
//            case 2:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 2);
//                senaraiLaporanKandungan1.add(pkk);
//                break;
//            case 3:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 3);
//                listKertasHuraianPTD.add(pkk);
//                break;
            case 4: //FOR HURAIAN PENTADBIR TANAH 
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiLaporanKandungan1.add(pkk);
                break;
//            case 5:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 5);
//                listKertasPerakuanPTG.add(pkk);
//                break;
//            case 6:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 6);
//                listKertasKeputusanPTG.add(pkk);
//                break;
              case 7: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
                  pkk = new PermohonanKertasKandungan();
                  pkk.setBil((short) 7);
                  senaraiLaporanKandunganPTG.add(pkk);
                  break;
              case 8: // FOR SYOR PENGARAH TANAH DAN GALIAN
                  pkk = new PermohonanKertasKandungan();
                  pkk.setBil((short) 8);
                  senaraiLaporanKandunganPTG2.add(pkk);
                  break;
//              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 9);
//                  senaraiLaporanKandunganptg2.add(pkk);
//                  break;   
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("tab", "true");
    }
     public Resolution simpanhurian() {


        LOG.info("------------Simpan Hurian started-----------::");


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKS");
        LOG.info("----------Kod Dokumen----------::" + kd);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");

        LOG.info("-------Simpan---Hurian--in--permohonankertas--------------::");


        if (permohonanKertas != null) {
            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pservice.simpanPermohonanKertas(permohonanKertas);


            senaraiLaporanKandungan1 = pservice.findByIdLapor(permohonanKertas.getIdKertas(), 6);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
                    Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(peng);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(6);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("6." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                String kandungan = getContext().getRequest().getParameter("kandungan6" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }


              senaraiLaporanKandungan2 = pservice.findByIdLapor(permohonanKertas.getIdKertas(), 7);

             kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
              for (int i = 1; i <= kira; i++) {
              InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiLaporanKandungan2.size() != 0 && i <= senaraiLaporanKandungan2.size()) {
                   Long id = senaraiLaporanKandungan2.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = pservice.findkandunganByIdKandungan(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDimasukOleh(peng);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    permohonanKertasKandungan1.setBil(7);
                    permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan1.setSubtajuk("7." + i);
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                String kandungan = getContext().getRequest().getParameter("kandungan7" + i);
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pservice.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        }
         addSimpleMessage("Maklumat telah berjaya disimpan.");
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         return new JSP("pelupusan/kertas_mmkNS.jsp").addParameter("popup", "true");


}

}
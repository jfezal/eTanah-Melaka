/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.KodSyaratNyata;
import etanah.model.KodSekatanKepentingan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Notis;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanRujukanLuar;

@UrlBinding("/pembangunan/keputusan_terdahulu")
public class KeputusanTerdahuluActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanTerdahuluActionBean.class);
    @Inject
    BPelService service;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private HakmilikPermohonan hp;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //add by wani
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn;
    private String lokasi;
    private String kmno;
    private String pejTanah;
    private String nama;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String taskId;
    private Task task = null;
    private Pengguna pengguna;
    private KodDokumen kd;
    private String kodHakmilikSementara;
    private String kodHakmilikTetap;
    private String kategoriTanahBaru;
    private String jenisPenggunaanTanah;
    private String kodSyaratNyataBaru;
    private String kodSekatanKepentinganBaru;
    private String idPermohonan;
    private List<Integer> tempohTamat;
    private boolean check = true;
    private String tajuk;
    private String tujuan;
    private String latarBelakang;
    private String pengarahTanah;
    private String keputusan;
    private List<PermohonanKertas> kertasList;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String ulasan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("-----------showForm---------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/keputusan_Terdahulu.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        HakmilikPermohonan hakp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        hp = permohonan.getSenaraiHakmilik().get(0);

        listPemohon = permohonan.getSenaraiPemohon();

        //wani
        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        kertasList = new ArrayList<PermohonanKertas>();
        kertasList = devService.findMohonanKertasByKodUrusan(idPermohonan);

        permohonanRujukanLuar = devService.findUlasanPerRujLuar(idPermohonan, "MMKT");
        
        if (permohonanRujukanLuar != null) {
            ulasan = permohonanRujukanLuar.getUlasan();
            LOG.info("------ulasan: " + ulasan);
        }
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    //add by wani
    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(String kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public String getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(String kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public String getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setKategoriTanahBaru(String kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public String getJenisPenggunaanTanah() {
        return jenisPenggunaanTanah;
    }

    public void setJenisPenggunaanTanah(String jenisPenggunaanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunaanTanah;
    }

    public String getKodSekatanKepentinganBaru() {
        return kodSekatanKepentinganBaru;
    }

    public void setKodSekatanKepentinganBaru(String kodSekatanKepentinganBaru) {
        this.kodSekatanKepentinganBaru = kodSekatanKepentinganBaru;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Integer> getTempohTamat() {
        return tempohTamat;
    }

    public void setTempohTamat(List<Integer> tempohTamat) {
        this.tempohTamat = tempohTamat;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getPengarahTanah() {
        return pengarahTanah;
    }

    public void setPengarahTanah(String pengarahTanah) {
        this.pengarahTanah = pengarahTanah;
    }

    public List<PermohonanKertas> getKertasList() {
        return kertasList;
    }

    public void setKertasList(List<PermohonanKertas> kertasList) {
        this.kertasList = kertasList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}

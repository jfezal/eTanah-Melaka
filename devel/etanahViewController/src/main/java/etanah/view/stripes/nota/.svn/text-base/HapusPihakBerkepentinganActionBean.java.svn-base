/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanAtasPihakBerkepentinganDAO;
import etanah.model.HakmilikPermohonan;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.HttpCache;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.KodWarganegara;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.RegService;
import etanah.service.SyerValidationService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.math.fraction.Fraction;

@HttpCache(allow = false)
@UrlBinding("/daftar/pembetulan/HapusPihakBerkepentinganActionBean")
public class HapusPihakBerkepentinganActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    SyerValidationService syerService;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikPermohonanService HakmilikPermohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakKepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PemohonDAO permohonDAO;
    @Inject
    PembetulanService pService;
    @Inject
    PermohonanAtasPihakBerkepentinganDAO permohonanAtasPihakBerkepentinganDAO;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKemaskiniService;
    private List<Pemohon> listPemohon;
    private List<PermohonanPihak> listMohonPihak;
    private Permohonan permohonan;
    private HakmilikUrusan hakmilikUrusan;
    private String idPermohonan;
    private Permohonan p;
    private Pengguna pguna;
    private Pemohon pemhn;
    private PermohonanPihak mhnphk;
    private Pihak pihak;
    private InfoAudit info;
    private HakmilikPihakBerkepentingan hpBerkepentingan;
    private HakmilikPihakBerkepentingan hpMAP;
    private PermohonanPihak mpMap;
    private Pemohon pemohonMap;
    private PermohonanPihakKemaskini permohonanPihakKemaskini;
    private HakmilikWaris waris;
    private HakmilikWaris warisbetul;
    boolean tiadaDataFlag = false;
    private List<PermohonanPihak> senaraiPemohonBerangkai;
    private List<PermohonanPihakKemaskini> mohonPihakKemaskiniList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList2;
    private List<HakmilikPihakBerkepentingan> pihakWarisList;
    private HakmilikPihakBerkepentingan HakmilikPihakMAP;
    private List<HakmilikWaris> hwList;
    private List<HakmilikWaris> hwBetulList;
    private List<PihakCawangan> cawanganList;
    private List<Pemohon> pemohonList;
    private List<Pemohon> pemohonList2;
    private List<PermohonanPihak> mohonPihakList;
    private List<HakmilikUrusan> hakmilikUrusanListY;
    private List<PermohonanAtasPihakBerkepentingan> permohonanAtasPihakBerkepentinganList;
    private PermohonanPihak mohonPihak2;
    private Pemohon pemohon;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private List<PermohonanAtasPerserahan> permohonanAtasPeserahanList;
    private List<PermohonanPihakKemaskini> pihakDelete;
    private List<Pemohon> senaraiPemohon;
    private List<PermohonanPihak> senaraiMohonPihak;
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak;
    private PermohonanAtasPihakBerkepentingan phk;
    private String[] syer1;
    private String[] syer2;
    private String idWaris;
    boolean cariFlag;
    private List<KodBangsa> senaraiKodBangsa;
    private static final Logger LOG = Logger.getLogger(PihakBerkepentinganActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    butiran PB
    private String nama;
    private String jenisPengenalan;
    private String noPengenalan;
    private String noPengenalanLama;
    private String bangsa;
    private String jantina;
    private String warganegara;
    private String jenisPB;
    private String penyebut;
    private String pembilang;
// alamat daftar
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
// alamat surat menyurat
    private String alamatSurat1;
    private String alamatSurat2;
    private String alamatSurat3;
    private String alamatSurat4;
    private String poskodSurat;
    private String negeriSurat;
    private String kodUrusan;

    public String getKodUrusan() {
        return kodUrusan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Pemohon getPemhn() {
        return pemhn;
    }

    public void setPemhn(Pemohon pemhn) {
        this.pemhn = pemhn;
    }

    public PermohonanPihak getMhnphk() {
        return mhnphk;
    }

    public void setMhnphk(PermohonanPihak mhnphk) {
        this.mhnphk = mhnphk;
    }

    public PermohonanAtasPihakBerkepentingan getPhk() {
        return phk;
    }

    public void setPhk(PermohonanAtasPihakBerkepentingan phk) {
        this.phk = phk;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikWaris> getHwBetulList() {
        return hwBetulList;
    }

    public void setHwBetulList(List<HakmilikWaris> hwBetulList) {
        this.hwBetulList = hwBetulList;
    }

    public HakmilikWaris getWarisbetul() {
        return warisbetul;
    }

    public void setWarisbetul(HakmilikWaris warisbetul) {
        this.warisbetul = warisbetul;
    }

    public HakmilikWaris getWaris() {
        return waris;
    }

    public void setWaris(HakmilikWaris waris) {
        this.waris = waris;
    }

    public String getIdWaris() {
        return idWaris;
    }

    public void setIdWaris(String idWaris) {
        this.idWaris = idWaris;
    }

    public List<HakmilikWaris> getHwList() {
        return hwList;
    }

    public void setHwList(List<HakmilikWaris> hwList) {
        this.hwList = hwList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakWarisList() {
        return pihakWarisList;
    }

    public void setPihakWarisList(List<HakmilikPihakBerkepentingan> pihakWarisList) {
        this.pihakWarisList = pihakWarisList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList2() {
        return pihakKepentinganList2;
    }

    public void setPihakKepentinganList2(List<HakmilikPihakBerkepentingan> pihakKepentinganList2) {
        this.pihakKepentinganList2 = pihakKepentinganList2;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public PermohonanPihakKemaskini getPermohonanPihakKemaskini() {
        return permohonanPihakKemaskini;
    }

    public void setPermohonanPihakKemaskini(PermohonanPihakKemaskini permohonanPihakKemaskini) {
        this.permohonanPihakKemaskini = permohonanPihakKemaskini;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNegeriSurat() {
        return negeriSurat;
    }

    public void setNegeriSurat(String negeriSurat) {
        this.negeriSurat = negeriSurat;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoPengenalanLama() {
        return noPengenalanLama;
    }

    public void setNoPengenalanLama(String noPengenalanLama) {
        this.noPengenalanLama = noPengenalanLama;
    }

    public String getPembilang() {
        return pembilang;
    }

    public void setPembilang(String pembilang) {
        this.pembilang = pembilang;
    }

    public String getPenyebut() {
        return penyebut;
    }

    public void setPenyebut(String penyebut) {
        this.penyebut = penyebut;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getPoskodSurat() {
        return poskodSurat;
    }

    public void setPoskodSurat(String poskodSurat) {
        this.poskodSurat = poskodSurat;
    }

    public String getWarganegara() {
        return warganegara;
    }

    public void setWarganegara(String warganegara) {
        this.warganegara = warganegara;
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

    public String getAlamatSurat1() {
        return alamatSurat1;
    }

    public void setAlamatSurat1(String alamatSurat1) {
        this.alamatSurat1 = alamatSurat1;
    }

    public String getAlamatSurat2() {
        return alamatSurat2;
    }

    public void setAlamatSurat2(String alamatSurat2) {
        this.alamatSurat2 = alamatSurat2;
    }

    public String getAlamatSurat3() {
        return alamatSurat3;
    }

    public void setAlamatSurat3(String alamatSurat3) {
        this.alamatSurat3 = alamatSurat3;
    }

    public String getAlamatSurat4() {
        return alamatSurat4;
    }

    public void setAlamatSurat4(String alamatSurat4) {
        this.alamatSurat4 = alamatSurat4;
    }

    public String getBangsa() {
        return bangsa;
    }

    public void setBangsa(String bangsa) {
        this.bangsa = bangsa;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
    }

    public String getJenisPB() {
        return jenisPB;
    }

    public void setJenisPB(String jenisPB) {
        this.jenisPB = jenisPB;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public HakmilikPihakBerkepentingan getHpBerkepentingan() {
        return hpBerkepentingan;
    }

    public void setHpBerkepentingan(HakmilikPihakBerkepentingan hpBerkepentingan) {
        this.hpBerkepentingan = hpBerkepentingan;
    }

    public List<PermohonanAtasPihakBerkepentingan> getPermohonanAtasPihakBerkepentinganList() {
        return permohonanAtasPihakBerkepentinganList;
    }

    public void setPermohonanAtasPihakBerkepentinganList(List<PermohonanAtasPihakBerkepentingan> permohonanAtasPihakBerkepentinganList) {
        this.permohonanAtasPihakBerkepentinganList = permohonanAtasPihakBerkepentinganList;
    }

    public List<PermohonanAtasPerserahan> getPermohonanAtasPeserahanList() {
        return permohonanAtasPeserahanList;
    }

    public void setPermohonanAtasPeserahanList(List<PermohonanAtasPerserahan> permohonanAtasPeserahanList) {
        this.permohonanAtasPeserahanList = permohonanAtasPeserahanList;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListY() {
        return hakmilikUrusanListY;
    }

    public void setHakmilikUrusanListY(List<HakmilikUrusan> hakmilikUrusanListY) {
        this.hakmilikUrusanListY = hakmilikUrusanListY;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public String[] getSyer1() {
        return syer1;
    }

    public void setSyer1(String[] syer1) {
        this.syer1 = syer1;
    }

    public String[] getSyer2() {
        return syer2;
    }

    public void setSyer2(String[] syer2) {
        this.syer2 = syer2;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<PermohonanPihakKemaskini> getMohonPihakKemaskiniList() {
        return mohonPihakKemaskiniList;
    }

    public void setMohonPihakKemaskiniList(List<PermohonanPihakKemaskini> mohonPihakKemaskiniList) {
        this.mohonPihakKemaskiniList = mohonPihakKemaskiniList;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<PermohonanPihak> getSenaraiPemohonBerangkai() {
        return senaraiPemohonBerangkai;
    }

    public void setSenaraiPemohonBerangkai(List<PermohonanPihak> senaraiPemohonBerangkai) {
        this.senaraiPemohonBerangkai = senaraiPemohonBerangkai;
    }

    public List<PermohonanPihakKemaskini> getPihakDelete() {
        return pihakDelete;
    }

    public void setPihakDelete(List<PermohonanPihakKemaskini> pihakDelete) {
        this.pihakDelete = pihakDelete;
    }

    public List<Pemohon> getPemohonList2() {
        return pemohonList2;
    }

    public void setPemohonList2(List<Pemohon> pemohonList2) {
        this.pemohonList2 = pemohonList2;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PermohonanPihak> getListMohonPihak() {
        return listMohonPihak;
    }

    public void setListMohonPihak(List<PermohonanPihak> listMohonPihak) {
        this.listMohonPihak = listMohonPihak;
    }

    public PermohonanPihak getMohonPihak() {
        return mohonPihak2;
    }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak2 = mohonPihak;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public List<PermohonanPihak> getSenaraiMohonPihak() {
        return senaraiMohonPihak;
    }

    public void setSenaraiMohonPihak(List<PermohonanPihak> senaraiMohonPihak) {
        this.senaraiMohonPihak = senaraiMohonPihak;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihak() {
        return senaraiHakmilikPihak;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakMAP() {
        return HakmilikPihakMAP;
    }

    public void setHakmilikPihakMAP(HakmilikPihakBerkepentingan HakmilikPihakMAP) {
        this.HakmilikPihakMAP = HakmilikPihakMAP;
    }

    public void setSenaraiHakmilikPihak(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak) {
        this.senaraiHakmilikPihak = senaraiHakmilikPihak;
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
        return new JSP("daftar/pembetulan/HapusBetulPihakBerkepentingan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPemohon = new ArrayList<Pemohon>();
        senaraiHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
        senaraiMohonPihak = new ArrayList<PermohonanPihak>();

        p = permohonanService.findById(idPermohonan);
        if (p != null) {
            idPermohonan = p.getIdPermohonan();
            mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "WAR");
            pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
            List<HakmilikPermohonan> listHmk = p.getSenaraiHakmilik();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            hakmilikUrusanListY = new ArrayList<HakmilikUrusan>();
            pihakWarisList = new ArrayList<HakmilikPihakBerkepentingan>();
            pihakDelete = new ArrayList<PermohonanPihakKemaskini>();
            for (HakmilikPermohonan hmk : listHmk) {
                idPermohonan = p.getIdPermohonan();
                p = permohonanService.findById(idPermohonan);
                String idHakmilik = hmk.getHakmilik().getIdHakmilik();
                permohonanAtasPihakBerkepentinganList = pService.findByAtasPihakByIdMohon(idPermohonan);
                permohonanAtasPeserahanList = pService.findAtasUrusan(idPermohonan);
                pihakKepentinganList.addAll(pService.findHakmilikPihakActiveByIdH(idHakmilik));
                hakmilikUrusanListY.addAll(pService.findUrusanByidHNY(idHakmilik));
                pihakWarisList.addAll(pService.findHakmilikPihakWarisByIdHakmilik(idHakmilik));
                pihakDelete.addAll(pService.findPihakRemove(idPermohonan));
            }
            List<PermohonanAtasPihakBerkepentingan> senaraiMAP = pService.findByAtasPihakByIdMohon2(idPermohonan);
            for (PermohonanAtasPihakBerkepentingan MohonAtasPihak : senaraiMAP) {
                if (MohonAtasPihak.getJenisTugasan().equals("HAPUS")) {
                    if (MohonAtasPihak.getPihakBerkepentingan() != null) {
                        Long idHP = MohonAtasPihak.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan();
                        hakmilikPihak = hakmilikPihakBerkepentinganDAO.findById(idHP);
                        senaraiHakmilikPihak.add(hakmilikPihak);
                    }
                    if (MohonAtasPihak.getPermohonanPihak() != null) {
                        Long idMP = MohonAtasPihak.getPermohonanPihak().getIdPermohonanPihak();;
                        mohonPihak2 = permohonanPihakDAO.findById(idMP);
                        senaraiMohonPihak.add(mohonPihak2);
                    }
                    if (MohonAtasPihak.getPemohon() != null) {
                        pemohon = permohonDAO.findById(MohonAtasPihak.getPemohon().getIdPemohon());
                        senaraiPemohon.add(pemohon);
                    }
                }
            }
            hwBetulList = pService.findWarisIDMohon(p.getIdPermohonan());

        }

    }

    public Resolution simpanPemohonPihak() {

        String[] param = getContext().getRequest().getParameterValues("idHakmilikPihakBerkepentingan");
        String error = "";
        String msg = "";
        for (String idHakmilikPihakBerkepentingan : param) {

            LOG.info(idHakmilikPihakBerkepentingan);
            LOG.info(p.getIdPermohonan());
            InfoAudit ia = new InfoAudit();

            HakmilikPihakBerkepentingan hpk = pService.findByIdHp(idHakmilikPihakBerkepentingan);
            PermohonanAtasPihakBerkepentingan ppihak = pService.findByAtasPihak(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
            LOG.info(hpk.getIdHakmilikPihakBerkepentingan());
            if (ppihak == null) {
                PermohonanAtasPihakBerkepentingan pe = new PermohonanAtasPihakBerkepentingan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setPihakBerkepentingan(hpk);
                pe.setJenisTugasan("HAPUS");
                if (hpk.getPerserahan() != null) {
                    pe.setCatatan(hpk.getPerserahan().getIdPerserahan());
                }
                if (hpk.getHakmilik() != null) {
                    Hakmilik hm = hpk.getHakmilik();
                    LOG.info("ID HAKMILIK = " + hm.getIdHakmilik());
                    pe.setHakmilik(hm);
                }
                pService.simpanMohonAtasPihak(pe);
//                saveDelPihak(pe.getIdAtasPihak());
                //modified for urusan BETPB for save mohon_atas_urusan for urusan terlibat
                LOG.info(p.getKodUrusan().getKod());
                String kodUrusan1 = p.getKodUrusan().getKod();
                String kodPB1 = hpk.getJenis().getKod();
                if (kodUrusan1.equals("BETPB")) {

                    PermohonanAtasPihakBerkepentingan pp = pService.findByAtasPihak(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    pp.setInfoAudit(ia);
                    if (hpk.getPerserahan() != null) {
                        pp.setCatatan(hpk.getPerserahan().getIdPerserahan());
                        LOG.info("Hakmilik Pihak = :" + hpk.getPerserahan().getIdPerserahan());
                    }
                    pp.setHakmilik(hpk.getHakmilik());
                    pService.simpanMohonAtasPihak(pp);

//                    LOG.info(hpk.getPerserahan().getIdUrusan());
                    LOG.info(p.getIdPermohonan());
                    if (hpk.getPerserahan() != null) {
                        hakmilikUrusan = hakmilikUrusanDAO.findById(hpk.getPerserahan().getIdUrusan());
                        Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                    }
//                pemohonList = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
//                        LOG.info(pBru.getIdPermohonan());
                    saveDelPihak(pe.getIdAtasPihak());
//
//                    
//                    if (kodPB1.equals("PA")) {
//                        PermohonanAtasPihakBerkepentingan pp = pService.findByAtasPihak(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
//                        ia.setDiKemaskiniOleh(pguna);
//                        ia.setTarikhKemaskini(new java.util.Date());
//                        pp.setInfoAudit(ia);
//                        pp.setCatatan(hpk.getPerserahan().getIdPerserahan());
//                        LOG.info("Hakmilik Pihak = :" + hpk.getPerserahan().getIdPerserahan());
//                        pp.setHakmilik(hpk.getHakmilik());
//                        pService.simpanMohonAtasPihak(pp);
//
//                        LOG.info(hpk.getPerserahan().getIdUrusan());
//                        LOG.info(p.getIdPermohonan());
//                        hakmilikUrusan = hakmilikUrusanDAO.findById(hpk.getPerserahan().getIdUrusan());
//                        Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
////                pemohonList = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
//                        LOG.info(pBru.getIdPermohonan());
//                        saveDelPihak(pe.getIdAtasPihak());
//
//                    }
                }
            } else {
                error = "Pihak Berkepentingan ini telah diPohon pada tab " + ppihak.getJenisTugasan();
            }
        }

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    private void saveDelPihak(long idAtasPihak) {
        String namaMedan = "status";
        String nilaiLama = "Y";
        String nilaiBaru = "T";
        InfoAudit ia = new InfoAudit();
//        permohonanAtasPihakBerkepentinganList = pService.findByAtasPihakByIdMohon(p.getIdPermohonan());
//       PermohonanAtasPihakBerkepentingan mohonAtasPihak = permohonanAtasPihakBerkepentinganDAO.findById(idAtasPihak);
//        for (PermohonanAtasPihakBerkepentingan pap : permohonanAtasPihakBerkepentinganList) {
        LOG.info("cer tgk ape = " + idAtasPihak);
        PermohonanAtasPihakBerkepentingan paPihakBerkepentingan = permohonanAtasPihakBerkepentinganDAO.findById(idAtasPihak);
        if (paPihakBerkepentingan.getPihakBerkepentingan() != null) {
            hpMAP = hakmilikPihakBerkepentinganDAO.findById(paPihakBerkepentingan.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan());
        } else if (paPihakBerkepentingan.getPermohonanPihak() != null) {
            mpMap = permohonanPihakDAO.findById(paPihakBerkepentingan.getPermohonanPihak().getIdPermohonanPihak());
        } else if (paPihakBerkepentingan.getPemohon() != null) {
            pemohonMap = pemohonService.findById(String.valueOf(paPihakBerkepentingan.getPemohon().getIdPemohon()));
        }
        if (paPihakBerkepentingan.getPihakBerkepentingan() != null) {
            Long idHakmilikPihakBerkepentingan = paPihakBerkepentingan.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan();
            List<PermohonanAtasPerserahan> mohonAtasPerserahanList = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(idPermohonan);
            if (mohonAtasPerserahanList.size() > 0) {
                for (PermohonanAtasPerserahan mohonAtasPerserahan : mohonAtasPerserahanList) {
                    if (permohonanPihakKemaskini == null) {
                        LOG.info("Entering Save");
                        permohonanPihakKemaskini = new PermohonanPihakKemaskini();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);

                        pService.saveKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {

                            LOG.info("NAMA MEDAN = " + namaMedan);
//                        if (namaMedan.equals("jenisPB")) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                LOG.info("mpMap.getJenis() = " + mpMap.getJenis());
                                LOG.info("mpMap.getPermohonan().getIdPermohonan() = " + mpMap.getPermohonan().getIdPermohonan());
                                LOG.info("mpMap.getPihak() = " + mpMap.getPihak());
                                Pihak idPihak = mpMap.getPihak();
                                String idPermohonan = mpMap.getPermohonan().getIdPermohonan();
                                KodJenisPihakBerkepentingan jenisKP = mpMap.getJenis();
                                permohonanPihakKemaskini.setJenis(jenisKP);
                                permohonanPihakKemaskini.setIdPermohonanLama(idPermohonan);
                                permohonanPihakKemaskini.setPihak(idPihak);
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                            permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                        }
                    } else {
                        LOG.info("Entering UPDATE");
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDimasukOleh(paPihakBerkepentingan.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(paPihakBerkepentingan.getInfoAudit().getTarikhMasuk());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                    permohonanPihakKemaskini.setPihak(idPihak);
                        pService.updateKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(mpMap.getPihak());
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                        }
                        permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                    }
                }
            } else {
                LOG.info("Entering Save");
//                    InfoAudit ia = new InfoAudit();
                permohonanPihakKemaskini = new PermohonanPihakKemaskini();
                permohonanPihakKemaskini.setInfoAudit(pguna.getInfoAudit());
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                permohonanPihakKemaskini.setPermohonan(p);//not null
                permohonanPihakKemaskini.setInfoAudit(ia);//not null
                permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                permohonanPihakKemaskini.setNilaiLama(nilaiLama);
                pService.saveKKini(permohonanPihakKemaskini);
            }

        }
        if (paPihakBerkepentingan.getPermohonan() != null) {
//                Long idPemohon = pap.getPemohon().getIdPemohon();

            List<PermohonanAtasPerserahan> mohonAtasPerserahanList = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(idPermohonan);
            if (mohonAtasPerserahanList.size() > 0) {
                for (PermohonanAtasPerserahan mohonAtasPerserahan : mohonAtasPerserahanList) {
                    if (permohonanPihakKemaskini == null) {
                        LOG.info("Entering Save");
                        permohonanPihakKemaskini = new PermohonanPihakKemaskini();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);

                        pService.saveKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {
                            if (mohonAtasPerserahan.getPermohonanBaru() != null) {
                                permohonanPihakKemaskini.setIdPermohonanLama(mohonAtasPerserahan.getPermohonanBaru().getIdPermohonan());
                            }
                            LOG.info("NAMA MEDAN = " + namaMedan);
//                        if (namaMedan.equals("jenisPB")) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                LOG.info("mpMap.getJenis() = " + mpMap.getJenis());
                                LOG.info("mpMap.getPermohonan().getIdPermohonan() = " + mpMap.getPermohonan().getIdPermohonan());
                                LOG.info("mpMap.getPihak() = " + mpMap.getPihak());
                                Pihak idPihak = mpMap.getPihak();
                                String idPermohonan = mpMap.getPermohonan().getIdPermohonan();
                                KodJenisPihakBerkepentingan jenisKP = mpMap.getJenis();
                                permohonanPihakKemaskini.setJenis(jenisKP);
                                permohonanPihakKemaskini.setIdPermohonanLama(idPermohonan);
                                permohonanPihakKemaskini.setPihak(idPihak);
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                            permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                        }
                    } else {
                        LOG.info("Entering UPDATE");
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDimasukOleh(paPihakBerkepentingan.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(paPihakBerkepentingan.getInfoAudit().getTarikhMasuk());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                    permohonanPihakKemaskini.setPihak(idPihak);
                        pService.updateKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(mpMap.getPihak());
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                        }
                        permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                    }
                }
            }
        }
        if (paPihakBerkepentingan.getPermohonanPihak() != null) {
            Long idMohonPihak = paPihakBerkepentingan.getPermohonanPihak().getIdPermohonanPihak();

            List<PermohonanAtasPerserahan> mohonAtasPerserahanList = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(idPermohonan);
            if (mohonAtasPerserahanList.size() > 0) {
                for (PermohonanAtasPerserahan mohonAtasPerserahan : mohonAtasPerserahanList) {
                    if (permohonanPihakKemaskini == null) {
                        LOG.info("Entering Save");
                        permohonanPihakKemaskini = new PermohonanPihakKemaskini();
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);

                        pService.saveKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {

                            LOG.info("NAMA MEDAN = " + namaMedan);
//                        if (namaMedan.equals("jenisPB")) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                LOG.info("mpMap.getJenis() = " + mpMap.getJenis());
                                LOG.info("mpMap.getPermohonan().getIdPermohonan() = " + mpMap.getPermohonan().getIdPermohonan());
                                LOG.info("mpMap.getPihak() = " + mpMap.getPihak());
                                Pihak idPihak = mpMap.getPihak();
                                String idPermohonan = mpMap.getPermohonan().getIdPermohonan();
                                KodJenisPihakBerkepentingan jenisKP = mpMap.getJenis();
                                permohonanPihakKemaskini.setJenis(jenisKP);
                                permohonanPihakKemaskini.setIdPermohonanLama(idPermohonan);
                                permohonanPihakKemaskini.setPihak(idPihak);
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                            permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                        }
                    } else {
                        LOG.info("Entering UPDATE");
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDimasukOleh(paPihakBerkepentingan.getInfoAudit().getDimasukOleh());
                        ia.setTarikhMasuk(paPihakBerkepentingan.getInfoAudit().getTarikhMasuk());
                        permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                        permohonanPihakKemaskini.setPermohonan(p);//not null
                        permohonanPihakKemaskini.setInfoAudit(ia);//not null
                        permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                        permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                        permohonanPihakKemaskini.setNilaiBaru(nilaiBaru);
                        permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                    permohonanPihakKemaskini.setPihak(idPihak);
                        pService.updateKKini(permohonanPihakKemaskini);
                        if (mohonAtasPerserahan != null) {
                            if (hpMAP != null) {
                                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                            }
                            if (mpMap != null) {
                                permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(mpMap.getPihak());
                            }
                            if (pemohonMap != null) {
                                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                            }
                        }
                        permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                    }
                }
            }
        }

    }

    public Resolution simpanPemohon() {

        String[] param = getContext().getRequest().getParameterValues("idPemohon");
        String error = "";
        String msg = "";
        for (String idPemohon : param) {

            LOG.info(idPemohon);
            LOG.info(p.getIdPermohonan());
            InfoAudit ia = new InfoAudit();

//            PermohonanPihak mohonPihak = permohonanPihakService.findById(idPemohon);
            Pemohon pmhn1 = permohonDAO.findById(Long.parseLong(idPemohon));

//            HakmilikPihakBerkepentingan hpk = pService.findByIdHp(idHakmilikPihakBerkepentingan);
            PermohonanAtasPihakBerkepentingan ppihak = pService.findByAtasPihakByIdMP(idPemohon, p.getIdPermohonan());
            LOG.info(pmhn1.getPermohonan().getIdPermohonan());
            if (ppihak == null) {
                PermohonanAtasPihakBerkepentingan pe = new PermohonanAtasPihakBerkepentingan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setPemohon(pmhn1);
                pe.setJenisTugasan("HAPUS");
                if (pmhn1.getPermohonan().getIdPermohonan() != null) {
                    pe.setCatatan(pmhn1.getPermohonan().getIdPermohonan());
                }
                if (pmhn1.getHakmilik() != null) {
                    Hakmilik hm = pmhn1.getHakmilik();
                    LOG.info("ID HAKMILIK = " + hm.getIdHakmilik());
                    pe.setHakmilik(hm);
                }
                pService.simpanMohonAtasPihak(pe);

                //modified for urusan BETPB for save mohon_atas_urusan for urusan terlibat
                LOG.info(p.getKodUrusan().getKod());
                String kodUrusan1 = p.getKodUrusan().getKod();
                String kodPB1 = pmhn1.getJenis().getKod();
                if (kodUrusan1.equals("BETPB")) {
                    if (kodPB1.equals("PM")) {
                        PermohonanAtasPihakBerkepentingan pp = pService.findByAtasPihak(idPemohon, p.getIdPermohonan());
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
//                    pp.setInfoAudit(ia);
//                    if (mohonPihak.getPermohonan().getIdPermohonan() != null) {
//                        pp.setCatatan(mohonPihak.getPermohonan().getIdPermohonan());
//                    }
//                    LOG.info("Hakmilik Pihak = :" + hpk.getPerserahan().getIdPerserahan());

//                        pService.simpanMohonAtasPihak(pp);
                        LOG.info(pmhn1.getPermohonan().getIdPermohonan());
                        LOG.info(p.getIdPermohonan());
                        hakmilikUrusan = hakmilikUrusanService.findByIdPerserahanIdHakmilik(pmhn1.getPermohonan().getIdPermohonan(), pmhn1.getHakmilik().getIdHakmilik());
                        Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
//                pemohonList = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
                        LOG.info(pBru.getIdPermohonan());
//                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusan(p.getIdPermohonan(), pBru.getIdPermohonan());
                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahan(p.getIdPermohonan(), hakmilikUrusan.getIdUrusan(), pBru.getIdPermohonan());

                        if (mhnAtsUrusan == null) {
                            PermohonanAtasPerserahan mau = new PermohonanAtasPerserahan();
                            ia.setDimasukOleh(pguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            mau.setInfoAudit(ia);
                            mau.setPermohonan(p);
                            mau.setIdAtasUrusan(hakmilikUrusan.getIdUrusan());
                            mau.setUrusan(hakmilikUrusan);
                            mau.setPermohonanBaru(pBru);
                            pService.simpanAtasUrusan2(mau);
                        } else {
                        }
                    }
                }
                saveDelPihak(pe.getIdAtasPihak());
            } else {
                error = "Pihak Berkepentingan ini telah diPohon pada tab " + ppihak.getJenisTugasan();
            }

        }

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanPemohonPihak2() {

        String[] param = getContext().getRequest().getParameterValues("idPermohonanPihak");
        String error = "";
        String msg = "";
        for (String idMohonPihak : param) {

            LOG.info(idMohonPihak);
            LOG.info(p.getIdPermohonan());
            InfoAudit ia = new InfoAudit();

            PermohonanPihak mohonPihak = permohonanPihakService.findById(idMohonPihak);

//            HakmilikPihakBerkepentingan hpk = pService.findByIdHp(idHakmilikPihakBerkepentingan);
            PermohonanAtasPihakBerkepentingan ppihak = pService.findByAtasPihakByIdMP(idMohonPihak, p.getIdPermohonan());
            LOG.info(mohonPihak.getIdPermohonanPihak());
            if (ppihak == null) {
                PermohonanAtasPihakBerkepentingan pe = new PermohonanAtasPihakBerkepentingan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setPermohonanPihak(mohonPihak);
                pe.setJenisTugasan("HAPUS");
                if (mohonPihak.getPermohonan().getIdPermohonan() != null) {
                    pe.setCatatan(mohonPihak.getPermohonan().getIdPermohonan());
                }
                if (mohonPihak.getHakmilik() != null) {
                    Hakmilik hm = mohonPihak.getHakmilik();
                    LOG.info("ID HAKMILIK = " + hm.getIdHakmilik());
                    pe.setHakmilik(hm);
                }
                pService.simpanMohonAtasPihak(pe);
                saveDelPihak(pe.getIdAtasPihak());
                //modified for urusan BETPB for save mohon_atas_urusan for urusan terlibat
                LOG.info(p.getKodUrusan().getKod());
                String kodUrusan1 = p.getKodUrusan().getKod();
                String kodPB1 = mohonPihak.getJenis().getKod();
                if (kodUrusan1.equals("BETPB")) {
                    if (kodPB1.equals("PM")) {
                        PermohonanAtasPihakBerkepentingan pp = pService.findByAtasPihak(idMohonPihak, p.getIdPermohonan());
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
//                        if (mohonPihak.getHakmilik() != null) {
//                            pp.setHakmilik(mohonPihak.getHakmilik());
//                        }
//                        pService.simpanMohonAtasPihak(pp);
//
//                        LOG.info(mohonPihak.getPermohonan().getIdPermohonan());
//                        LOG.info(p.getIdPermohonan());
                        hakmilikUrusan = hakmilikUrusanService.findByIdPerserahanIdHakmilik(mohonPihak.getPermohonan().getIdPermohonan(), mohonPihak.getHakmilik().getIdHakmilik());
                        Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                        LOG.info(pBru.getIdPermohonan());
//                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusan(p.getIdPermohonan(), pBru.getIdPermohonan());
                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahan(p.getIdPermohonan(), hakmilikUrusan.getIdUrusan(), pBru.getIdPermohonan());

                        if (mhnAtsUrusan == null) {
                            PermohonanAtasPerserahan mau = new PermohonanAtasPerserahan();
                            ia.setDimasukOleh(pguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            mau.setInfoAudit(ia);
                            mau.setPermohonan(p);
                            mau.setIdAtasUrusan(hakmilikUrusan.getIdUrusan());
                            mau.setUrusan(hakmilikUrusan);
                            mau.setPermohonanBaru(pBru);
                            pService.simpanAtasUrusan2(mau);
                        } else {
                        }
                    }
                    if (kodPB1.equals("PG")) {
                        PermohonanAtasPihakBerkepentingan pp = pService.findByAtasPihak(idMohonPihak, p.getIdPermohonan());
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(new java.util.Date());
//                        if (mohonPihak.getHakmilik() != null) {
//                            pp.setHakmilik(mohonPihak.getHakmilik());
//                        }
//                        pService.simpanMohonAtasPihak(pp);
//
//                        LOG.info(mohonPihak.getPermohonan().getIdPermohonan());
//                        LOG.info(p.getIdPermohonan());

                        LOG.info("ID HAKMILIK = " + mohonPihak.getHakmilik().getIdHakmilik());
//                        LOG.info("ID HAKMILIK PP = " + pp.getPermohonanPihak().getHakmilik().getIdHakmilik());
                        hakmilikUrusan = hakmilikUrusanService.findByIdPerserahanIdHakmilik(mohonPihak.getPermohonan().getIdPermohonan(), mohonPihak.getHakmilik().getIdHakmilik());
                        Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
                        LOG.info(pBru.getIdPermohonan());
//                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusan(p.getIdPermohonan(), pBru.getIdPermohonan());
                        PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahan(p.getIdPermohonan(), hakmilikUrusan.getIdUrusan(), pBru.getIdPermohonan());
                        if (mhnAtsUrusan == null) {
                            PermohonanAtasPerserahan mau = new PermohonanAtasPerserahan();
                            ia.setDimasukOleh(pguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            mau.setInfoAudit(ia);
                            mau.setPermohonan(p);
                            mau.setIdAtasUrusan(hakmilikUrusan.getIdUrusan());
                            mau.setUrusan(hakmilikUrusan);
                            mau.setPermohonanBaru(pBru);
                            pService.simpanAtasUrusan2(mau);
                            saveDelPihak(pe.getIdAtasPihak());
                        }
                    }
                }
            } else {
                error = "Pihak Berkepentingan ini telah diPohon pada tab " + ppihak.getJenisTugasan();
            }
        }

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanPemohon2Pihak() {

        String[] param = getContext().getRequest().getParameterValues("idPemohon");
        String error = "";
        String msg = "";
        for (String idPemohon : param) {

            LOG.info(idPemohon);

            InfoAudit ia = new InfoAudit();

            Pemohon pmhn = pemohonService.findById(idPemohon);
            HakmilikPihakBerkepentingan hpk = pService.findByidPihak(String.valueOf(pmhn.getPihak().getIdPihak()), pmhn.getJenis().getKod(), pmhn.getHakmilik().getIdHakmilik());
            LOG.info(hpk.getIdHakmilikPihakBerkepentingan());
            PermohonanAtasPihakBerkepentingan ppihak = pService.findByAtasPihak(String.valueOf(hpk.getIdHakmilikPihakBerkepentingan()), p.getIdPermohonan());
            LOG.info(ppihak);
            if (ppihak == null) {
                PermohonanAtasPihakBerkepentingan pe = new PermohonanAtasPihakBerkepentingan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(p);
                pe.setHakmilik(hpk.getHakmilik());
                pe.setPihakBerkepentingan(hpk);
                pe.setJenisTugasan("HAPUS");
                pService.simpanMohonAtasPihak(pe);

                //modified for urusan BETPB for save mohon_atas_urusan for urusan terlibat
                LOG.info("idurusan1:: " + hpk.getPerserahan().getIdUrusan());
                LOG.info(p.getIdPermohonan());
                hakmilikUrusan = pService.findurusanByIdHMIdMhn(hpk.getHakmilik().getIdHakmilik(), pmhn.getPermohonan().getIdPermohonan());
                Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
//                pemohonList = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());

//                PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusan(p.getIdPermohonan(), pBru.getIdPermohonan());
                PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahan(p.getIdPermohonan(), hakmilikUrusan.getIdUrusan(), pBru.getIdPermohonan());

                LOG.info("idurusan2:: " + hakmilikUrusan.getIdUrusan());
                if (mhnAtsUrusan == null) {
                    PermohonanAtasPerserahan mau2 = new PermohonanAtasPerserahan();
                    ia.setDimasukOleh(pguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    mau2.setInfoAudit(ia);
                    mau2.setPermohonan(p);
                    mau2.setIdAtasUrusan(hakmilikUrusan.getIdUrusan());
                    mau2.setUrusan(hakmilikUrusan);
                    mau2.setPermohonanBaru(pBru);
                    pService.simpanAtasUrusan2(mau2);
                } else {
                }

            } else {
                PermohonanAtasPihakBerkepentingan pp = permohonanAtasPihakBerkepentinganDAO.findById(ppihak.getIdAtasPihak());
//                ia.setDiKemaskiniOleh(pguna);
//                ia.setTarikhKemaskini(new java.util.Date());
//                pp.setInfoAudit(ia);
                pp.setHakmilik(hpk.getHakmilik());
                pService.simpanMohonAtasPihak(pp);
                //modified for urusan BETPB for save mohon_atas_urusan for urusan terlibat

//                LOG.info("idurusan3:: " + hpk.getPerserahan().getIdUrusan());
                LOG.info(p.getIdPermohonan());
                hakmilikUrusan = pService.findurusanByIdHMIdMhn(hpk.getHakmilik().getIdHakmilik(), pmhn.getPermohonan().getIdPermohonan());
                Permohonan pBru = permohonanDAO.findById(hakmilikUrusan.getIdPerserahan());
//                pemohonList = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());

//                PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusan(p.getIdPermohonan(), pBru.getIdPermohonan());
                PermohonanAtasPerserahan mhnAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahan(p.getIdPermohonan(), hakmilikUrusan.getIdUrusan(), pBru.getIdPermohonan());

                if (mhnAtsUrusan == null) {
                    PermohonanAtasPerserahan mau3 = new PermohonanAtasPerserahan();
                    ia.setDimasukOleh(pguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    mau3.setInfoAudit(ia);
                    mau3.setPermohonan(p);
                    mau3.setIdAtasUrusan(hakmilikUrusan.getIdUrusan());
                    mau3.setUrusan(hakmilikUrusan);
                    mau3.setPermohonanBaru(pBru);
                    pService.simpanAtasUrusan2(mau3);
                } else {
                }

                error = "Pihak Berkepentingan ini telah diPohon pada tab " + ppihak.getJenisTugasan();
            }
        }

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

//        public Resolution removePemohonPihak() {
//
//        String[] param = getContext().getRequest().getParameterValues("idHakmilikPihakBerkepentingan");
//        String error = "";
//        String msg = "";
//        for (String idHakmilikPihakBerkepentingan : param) {
//
//            LOG.info(idHakmilikPihakBerkepentingan);
//
//            InfoAudit ia = new InfoAudit();
//
//            HakmilikPihakBerkepentingan hpk = pService.findByIdHp(idHakmilikPihakBerkepentingan);
//            PermohonanPihakKemaskini ppk = new PermohonanPihakKemaskini();
//            ppk.setPermohonan(p);
//            ppk.setCawangan(pguna.getKodCawangan());
//            ppk.setIdKemaskini(hpk.getIdHakmilikPihakBerkepentingan());
//                ia.setDimasukOleh(pguna);
//                ia.setTarikhMasuk(new java.util.Date());
//                ppk.setInfoAudit(ia);
//        }     
//
//        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
//    }
    public Resolution caripihak() {

        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        for (String idUrusan : param) {
            pihakKepentinganList2 = pService.findHakmilikPihakActiveByIdUrusan(idUrusan);

            LOG.info(getContext().getRequest().getParameterValues("idPermohonan"));
            LOG.info("sini");

            String param2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

            Permohonan permohonan = permohonanDAO.findById(param2);
            LOG.info(permohonan.getIdPermohonan());
            LOG.info(permohonan.getKodUrusan().getKod());
            kodUrusan = permohonan.getKodUrusan().getKod();
            LOG.info(kodUrusan);
            hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            LOG.info(hakmilikUrusan.getIdPerserahan());
            pemohonList2 = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
            LOG.info(pemohonList.size());

        }

        rehydrate();
        return new JSP("daftar/pembetulan/edit_PihakBerkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution caripihak2() {

        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        for (String idUrusan : param) {
            pihakKepentinganList2 = pService.findHakmilikPihakActiveByIdUrusan(idUrusan);

            LOG.info(getContext().getRequest().getParameterValues("idPermohonan"));
            LOG.info("sini");

            String param2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

            Permohonan permohonan = permohonanDAO.findById(param2);
            LOG.info(permohonan.getIdPermohonan());
            LOG.info(permohonan.getKodUrusan().getKod());
            kodUrusan = permohonan.getKodUrusan().getKod();
            LOG.info(kodUrusan);
            hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
            LOG.info(hakmilikUrusan.getIdPerserahan());
            listPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            listMohonPihak = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        }

        rehydrate();
        return new JSP("daftar/pembetulan/delete_pihak_lama.jsp").addParameter("popup", "true");
    }

    public Resolution cariWaris() {

        String[] param = getContext().getRequest().getParameterValues("idHakmilikPihakBerkepentingan");
        for (String idHakmilikPihakBerkepentingan : param) {
            hwList = pService.findHakmilikWaris(idHakmilikPihakBerkepentingan);
        }
        rehydrate();
        return new JSP("daftar/pembetulan/betulPihakWaris.jsp").addParameter("tab", "true");
    }

    public Resolution showEditPemohon() {
        String idHP = getContext().getRequest().getParameter("idAtasPihak");
        String idAtasPihak = getContext().getRequest().getParameter("idAtasPihak");
        String idAtasUrusan = getContext().getRequest().getParameter("idAtasUrusan");
        LOG.info("idAtasPIHAK la :" + idHP);
        LOG.info("idAtasUrusan la :" + idAtasUrusan);

        kodUrusan = p.getKodUrusan().getKod();

        PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihak(idAtasPihak, p.getIdPermohonan());
        if (map.getPihakBerkepentingan() != null) {
            hpBerkepentingan = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHP));
        }
        String idHakmilik = hpBerkepentingan.getHakmilik().getIdHakmilik();
        LOG.info("idHakmilik :=" + idHakmilik);
        LOG.info("id pihak :" + hpBerkepentingan.getPihak().getIdPihak());
        LOG.info("map.getIdAtasPihak() = " + map.getIdAtasPihak());
        List<PermohonanPihakKemaskini> phkList = pService.findAtasIdHp(p.getIdPermohonan(), String.valueOf(map.getIdAtasPihak()));
        LOG.info("MOHON ATAS PIHAK LIST = " + phkList.size());
        if (phkList.size() > 0) {

            for (PermohonanPihakKemaskini pbMap : phkList) {
                if (pbMap != null) {
                    if (pbMap.getNamaMedan().equals("nama")) {
                        nama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                        jenisPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalan")) {
                        noPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                        noPengenalanLama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("bangsa")) {
                        bangsa = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jantina")) {
                        jantina = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("warganegara")) {
                        warganegara = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPB")) {
                        jenisPB = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("penyebut")) {
                        penyebut = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("pembilang")) {
                        pembilang = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat1")) {
                        alamat1 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat2")) {
                        alamat2 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat3")) {
                        alamat3 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat4")) {
                        alamat4 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("poskod")) {
                        poskod = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("negeri")) {
                        negeri = pbMap.getNilaiBaru();
                    }

                }
            }
        }
//    }
//        }

        return new JSP(
                "daftar/pembetulan/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution showEditMohonPihak() {
        String idMP = getContext().getRequest().getParameter("idPermohonanPihak");
        String idMP2 = getContext().getRequest().getParameter("idAtasPihak");

        LOG.info("idPermohonanPihak la :" + idMP);
        LOG.info("idAtasPIHAK la :" + idMP2);

        kodUrusan = p.getKodUrusan().getKod();

        PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihakByIdMP(idMP, p.getIdPermohonan());
        if (map.getPermohonanPihak() != null) {
            mhnphk = permohonanPihakDAO.findById(map.getPermohonanPihak().getIdPermohonanPihak());
        }
        String idHakmilik = mhnphk.getHakmilik().getIdHakmilik();
        LOG.info("idHakmilik :=" + idHakmilik);
        LOG.info("id pihak :" + mhnphk.getPihak().getIdPihak());
        LOG.info("map.getIdAtasPihak() = " + map.getIdAtasPihak());
        List<PermohonanPihakKemaskini> phkList = pService.findAtasIdHp(p.getIdPermohonan(), String.valueOf(map.getIdAtasPihak()));
        LOG.info("MOHON ATAS PIHAK LIST = " + phkList.size());
        if (phkList.size() > 0) {

            for (PermohonanPihakKemaskini pbMap : phkList) {
                if (pbMap != null) {
                    if (pbMap.getNamaMedan().equals("nama")) {
                        nama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                        jenisPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalan")) {
                        noPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                        noPengenalanLama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("bangsa")) {
                        bangsa = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jantina")) {
                        jantina = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("warganegara")) {
                        warganegara = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPB")) {
                        jenisPB = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("penyebut")) {
                        penyebut = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("pembilang")) {
                        pembilang = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat1")) {
                        alamat1 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat2")) {
                        alamat2 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat3")) {
                        alamat3 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat4")) {
                        alamat4 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("poskod")) {
                        poskod = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("negeri")) {
                        negeri = pbMap.getNilaiBaru();
                    }

                }
            }
        }
//    }
//        }

        return new JSP(
                "daftar/pembetulan/edit_mohon_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution showEditPemohonLama() {
        String idPemohon1 = getContext().getRequest().getParameter("idPemohon");
        String idMP2 = getContext().getRequest().getParameter("idAtasPihak");

        LOG.info("idPermohonanPihak la :" + idPemohon1);
        LOG.info("idAtasPIHAK la :" + idMP2);

        kodUrusan = p.getKodUrusan().getKod();

        PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihakByIdPemohon(idPemohon1, p.getIdPermohonan());
        if (map.getPemohon() != null) {
            pemhn = pemohonService.findById(String.valueOf(map.getPemohon().getIdPemohon()));
        }
        String idHakmilik = pemhn.getHakmilik().getIdHakmilik();
        LOG.info("idHakmilik :=" + idHakmilik);
        LOG.info("id pihak :" + pemhn.getPihak().getIdPihak());
        LOG.info("map.getIdAtasPihak() = " + map.getIdAtasPihak());
        List<PermohonanPihakKemaskini> phkList = pService.findAtasIdHp(p.getIdPermohonan(), String.valueOf(map.getIdAtasPihak()));
        LOG.info("MOHON ATAS PIHAK LIST = " + phkList.size());
        if (phkList.size() > 0) {

            for (PermohonanPihakKemaskini pbMap : phkList) {
                if (pbMap != null) {
                    if (pbMap.getNamaMedan().equals("nama")) {
                        nama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPengenalan")) {
                        jenisPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalan")) {
                        noPengenalan = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("noPengenalanLama")) {
                        noPengenalanLama = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("bangsa")) {
                        bangsa = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jantina")) {
                        jantina = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("warganegara")) {
                        warganegara = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("jenisPB")) {
                        jenisPB = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("penyebut")) {
                        penyebut = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("pembilang")) {
                        pembilang = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat1")) {
                        alamat1 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat2")) {
                        alamat2 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat3")) {
                        alamat3 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("alamat4")) {
                        alamat4 = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("poskod")) {
                        poskod = pbMap.getNilaiBaru();
                    }
                    if (pbMap.getNamaMedan().equals("negeri")) {
                        negeri = pbMap.getNilaiBaru();
                    }

                }
            }
        }
//    }
//        }

        return new JSP(
                "daftar/pembetulan/edit_pemohon_lama.jsp").addParameter("popup", "true");
    }

    public Resolution editWaris() {

        idWaris = (String) getContext().getRequest().getParameter("idWaris");
        waris = pService.findWarisByID(idWaris);
        warisbetul = pService.findWarisBetulByID(idWaris);
        if (warisbetul != null) {
            if (warisbetul.getNama() != null) {
                nama = warisbetul.getNama();
            }
            if (warisbetul.getJenisPengenalan() != null) {
                jenisPengenalan = warisbetul.getJenisPengenalan().getKod();
            }
            if (warisbetul.getNoPengenalan() != null) {
                noPengenalan = warisbetul.getNoPengenalan();
            }
            if (warisbetul.getNegeri() != null) {
                negeri = warisbetul.getNegeri().getKod();
            }
            if (String.valueOf(warisbetul.getSyerPembilang()) != null) {
                pembilang = String.valueOf(warisbetul.getSyerPembilang());
            }
            if (String.valueOf(warisbetul.getSyerPenyebut()) != null) {
                penyebut = String.valueOf(warisbetul.getSyerPenyebut());
            }
            if (warisbetul.getWargaNegara() != null) {
                warganegara = warisbetul.getWargaNegara().getKod();
            }
        }
        return new JSP("daftar/pembetulan/edit_waris.jsp").addParameter("popup", "true");
    }

    public Resolution deletePihak() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idAtasPihak = getContext().getRequest().getParameter("idAtasPihak");
        LOG.info(idAtasPihak);
        LOG.debug("id atas pihak ~~~" + idAtasPihak);
        if (idAtasPihak != null) {
            phk = pService.findByAtasPihak(idAtasPihak, idMhn);
            if (phk == null) {
                phk = pService.findByAtasPihakByIdMP(idAtasPihak, idMhn);
            }
            if (phk == null) {
                phk = pService.findByAtasPihakByIdPemohon(idAtasPihak, idMhn);
            }
            Long idAtasPihak2 = phk.getIdAtasPihak();
            List<PermohonanPihakKemaskini> senaraiMpk = permohonanPihakKemaskiniService.findByIdPermohonanAndIdAtasPihak(idPermohonan, String.valueOf(idAtasPihak2));
            if (senaraiMpk.size() > 0) {
                permohonanPihakKemaskiniService.delete(senaraiMpk);
            }
            if (phk.getPihakBerkepentingan() != null) {
                if (phk.getPihakBerkepentingan().getPerserahan() != null) {
                    PermohonanAtasPerserahan pau = pService.findAtasMohonByIdMhnAndIdMhnBaru(phk.getPihakBerkepentingan().getPerserahan().getIdPerserahan(), idPermohonan);
                    if (pau != null) {
                        pService.deleteUrusan(pau);
                    }
                }
            }

            permohonanAtasPihakBerkepentinganList = pService.findByAtasPihakByIdMohon(idPermohonan);
            permohonanAtasPeserahanList = pService.findAtasUrusan(idPermohonan);
            List<PermohonanPihakKemaskini> ppk = pService.findAtasIdHp(p.getIdPermohonan(), idAtasPihak);

            for (PermohonanPihakKemaskini pk : ppk) {
                if (pk != null) {
                    pService.delete(pk);
                }
            }

//            for (PermohonanAtasPerserahan pas : permohonanAtasPeserahanList) {
//            }
            for (PermohonanAtasPihakBerkepentingan pak : permohonanAtasPihakBerkepentinganList) {
                if (phk != null) {
                    pService.delete(phk);
                }
            }
        }
        rehydrate();

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm");
    }

    //    Save Pembetulan Pemilik
    public Resolution saveBetulPB() {
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        String idMP = getContext().getRequest().getParameter("idPermohonanPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit audit = peng.getInfoAudit();
        String error = "";
        String msg = "";
        if (idHakmilikPihakBerkepentingan != null) {
            PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihak(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
            if (map.getPihakBerkepentingan() != null) {
                hpBerkepentingan = hakmilikPihakBerkepentinganDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
                Permohonan mhn = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));
                if (mhn.getKodUrusan().getKod().equals("BETPB")) {
                    PermohonanAtasPerserahan mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhn(p.getIdPermohonan());
                    LOG.info("Start Save betul BETPB");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");

                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(hpBerkepentingan.getPihak().getNama()) ? "" : hpBerkepentingan.getPihak().getNama()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(hpBerkepentingan.getJenisPengenalan().getKod()) ? "" : hpBerkepentingan.getJenis().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(hpBerkepentingan.getNoPengenalan()) ? "" : hpBerkepentingan.getNoPengenalan()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalanLama)) {
                        LOG.info("No Pengenalan Lama::" + noPengenalanLama);
                        if (noPengenalanLama.equalsIgnoreCase("-")) {
                            noPengenalanLama = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalanLama", noPengenalanLama, (StringUtils.isBlank(hpBerkepentingan.getNoPengenalanLama()) ? "" : hpBerkepentingan.getNoPengenalanLama()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = hpBerkepentingan.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(hpBerkepentingan.getPihak().getNama()) ? "" : hpBerkepentingan.getPihak().getNama()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + hpBerkepentingan.getPihak().getKodJantina());
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(hpBerkepentingan.getPihak().getKodJantina()) ? "" : hpBerkepentingan.getPihak().getKodJantina()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((hpBerkepentingan.getPihak().getWargaNegara() == null) ? "" : hpBerkepentingan.getPihak().getWargaNegara().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(hpBerkepentingan.getJenis().getKod()) ? "" : hpBerkepentingan.getJenis().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(hpBerkepentingan.getSyerPenyebut()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(hpBerkepentingan.getSyerPembilang()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat1()) ? "" : hpBerkepentingan.getPihak().getAlamat1()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat2()) ? "" : hpBerkepentingan.getPihak().getAlamat2()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }

                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat3()) ? "" : hpBerkepentingan.getPihak().getAlamat3()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat4()) ? "" : hpBerkepentingan.getPihak().getAlamat4()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(hpBerkepentingan.getPihak().getPoskod()) ? "" : hpBerkepentingan.getPihak().getPoskod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        if (hpBerkepentingan != null) {
                            msg = saveAndUpdateMedan("negeri", negeri, (hpBerkepentingan.getPihak().getNegeri() == null ? "" : hpBerkepentingan.getPihak().getNegeri().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat1()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat1()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat2()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat2()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat3()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat3()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat4()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat4()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }
                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratPoskod()) ? "" : hpBerkepentingan.getPihak().getSuratPoskod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (hpBerkepentingan.getPihak().getSuratNegeri() == null ? "" : hpBerkepentingan.getPihak().getSuratNegeri().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }

                } else {
                    LOG.info("Start Save betul Pemilik");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");
                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(hpBerkepentingan.getPihak().getNama()) ? "" : hpBerkepentingan.getPihak().getNama()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(hpBerkepentingan.getPihak().getJenisPengenalan().getKod()) ? "" : hpBerkepentingan.getPihak().getJenisPengenalan().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(hpBerkepentingan.getPihak().getNoPengenalan()) ? "" : hpBerkepentingan.getPihak().getNoPengenalan()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(noPengenalanLama)) {
                        LOG.info("No Pengenalan Lama::" + noPengenalanLama);
                        if (noPengenalanLama.equalsIgnoreCase("-")) {
                            noPengenalanLama = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalanLama", noPengenalanLama, (StringUtils.isBlank(hpBerkepentingan.getNoPengenalanLama()) ? "" : hpBerkepentingan.getNoPengenalanLama()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = hpBerkepentingan.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(hpBerkepentingan.getPihak().getBangsa().getKod()) ? "" : hpBerkepentingan.getPihak().getBangsa().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + hpBerkepentingan.getPihak().getKodJantina());

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(hpBerkepentingan.getPihak().getKodJantina()) ? "" : hpBerkepentingan.getPihak().getKodJantina()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((hpBerkepentingan.getPihak().getWargaNegara() == null) ? "" : hpBerkepentingan.getPihak().getWargaNegara().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(hpBerkepentingan.getJenis().getKod()) ? "" : hpBerkepentingan.getJenis().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(hpBerkepentingan.getSyerPenyebut()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(hpBerkepentingan.getSyerPembilang()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat1()) ? "" : hpBerkepentingan.getPihak().getAlamat1()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat2()) ? "" : hpBerkepentingan.getPihak().getAlamat2()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat3()) ? "" : hpBerkepentingan.getPihak().getAlamat3()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(hpBerkepentingan.getPihak().getAlamat4()) ? "" : hpBerkepentingan.getPihak().getAlamat4()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(hpBerkepentingan.getPihak().getPoskod()) ? "" : hpBerkepentingan.getPihak().getPoskod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        msg = saveAndUpdateMedan("negeri", negeri, (hpBerkepentingan.getPihak().getNegeri() == null ? "" : hpBerkepentingan.getPihak().getNegeri().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat1()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat1()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat2()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat2()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat3()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat3()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratAlamat4()) ? "" : hpBerkepentingan.getPihak().getSuratAlamat4()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }

                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(hpBerkepentingan.getPihak().getSuratPoskod()) ? "" : hpBerkepentingan.getPihak().getSuratPoskod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (hpBerkepentingan.getPihak().getSuratNegeri() == null ? "" : hpBerkepentingan.getPihak().getSuratNegeri().getKod()), String.valueOf(hpBerkepentingan.getIdHakmilikPihakBerkepentingan()), String.valueOf(map.getIdAtasPihak()));

                    }
                }
            }
        }
        if (hpBerkepentingan.getPerserahan() != null) {
            List<PermohonanAtasPerserahan> mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahanList(p.getIdPermohonan(), hpBerkepentingan.getPerserahan().getIdUrusan());
            for (PermohonanAtasPerserahan pap : mohonAtsUrusan) {
                if (mohonAtsUrusan != null) {
                    LOG.info("pap.getPermohonanBaru().getIdPermohonan():" + pap.getPermohonanBaru().getIdPermohonan());
                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(pap.getPermohonanBaru().getIdPermohonan(), hpBerkepentingan.getHakmilik().getIdHakmilik(), hpBerkepentingan.getPihak().getIdPihak());
                    List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
                    for (PermohonanPihak pmphk : mohonPihakList) {
                        if (StringUtils.isNotBlank(nama)) {
                            pmphk.setNama(nama);
                        }
                        if (StringUtils.isNotBlank(noPengenalan)) {
                            pmphk.setNoPengenalan(noPengenalan);
                        }
                        if (StringUtils.isNotBlank(penyebut)) {
                            pmphk.setSyerPenyebut(Integer.valueOf(penyebut));
                            pmphk.setJumlahPenyebut(Integer.valueOf(penyebut));
                        }
                        if (StringUtils.isNotBlank(pembilang)) {
                            pmphk.setSyerPembilang(Integer.valueOf(pembilang));
                            pmphk.setJumlahPembilang(Integer.valueOf(pembilang));
                        }

                        Alamat alamat = new Alamat();
                        if (StringUtils.isNotBlank(alamat1)) {
                            LOG.info(alamat1);
                            if (alamat1.equalsIgnoreCase("-")) {
                                alamat1 = "";
                            }
                            alamat.setAlamat1(alamat1);

                        }
                        if (StringUtils.isNotBlank(alamat2)) {
                            LOG.info(alamat2);
                            if (alamat2.equalsIgnoreCase("-")) {
                                alamat2 = "";
                            }
                            alamat.setAlamat2(alamat2);

                        }
                        if (StringUtils.isNotBlank(alamat3)) {
                            LOG.info(alamat3);
                            if (alamat3.equalsIgnoreCase("-")) {
                                alamat3 = "";
                            }
                            alamat.setAlamat3(alamat3);

                        }
                        if (StringUtils.isNotBlank(alamat4)) {
                            LOG.info(alamat4);
                            if (alamat4.equalsIgnoreCase("-")) {
                                alamat4 = "";
                            }
                            alamat.setAlamat4(alamat4);

                        }
                        if (StringUtils.isNotBlank(poskod)) {
                            LOG.info(poskod);
                            if (poskod.equalsIgnoreCase("-")) {
                                poskod = "";
                            }
                            alamat.setPoskod(poskod);

                        }
                        if (StringUtils.isNotBlank(negeri)) {
                            LOG.info(negeri);
                            if (negeri.equalsIgnoreCase("-")) {
                                negeri = "";
                            }
                            KodNegeri kn = kodNegeriDAO.findById(negeri);
                            alamat.setNegeri(kn);

                        }
                        pmphk.setAlamat(alamat);
                        audit.setTarikhKemaskini(new java.util.Date());
                        audit.setDiKemaskiniOleh(peng);
                        pmphk.setInfoAudit(audit);
                        permPihak.add(pmphk);

                    }
                    permohonanPihakService.saveOrUpdate(permPihak);

                    pemohonList = pemohonService.senaraiPemohonByIdPihakIdHakmilikIdMohon(hpBerkepentingan.getPihak().getIdPihak(), hpBerkepentingan.getHakmilik().getIdHakmilik(), pap.getPermohonanBaru().getIdPermohonan());
                    List<Pemohon> pmh = new ArrayList<Pemohon>();

                    for (Pemohon pmohon : pemohonList) {
                        if (StringUtils.isNotBlank(nama)) {
                            pmohon.setNama(nama);
                        }
                        if (StringUtils.isNotBlank(noPengenalan)) {
                            pmohon.setNoPengenalan(noPengenalan);
                        }
                        if (StringUtils.isNotBlank(penyebut)) {
                            pmohon.setSyerPenyebut(Integer.valueOf(penyebut));
                            pmohon.setJumlahPenyebut(Integer.valueOf(penyebut));
                        }
                        if (StringUtils.isNotBlank(pembilang)) {
                            pmohon.setSyerPembilang(Integer.valueOf(pembilang));
                            pmohon.setJumlahPembilang(Integer.valueOf(pembilang));
                        }
                        Alamat alamat = new Alamat();
                        if (StringUtils.isNotBlank(alamat1)) {
                            LOG.info(alamat1);
                            if (alamat1.equalsIgnoreCase("-")) {
                                alamat1 = "";
                            }
                            alamat.setAlamat1(alamat1);

                        }
                        if (StringUtils.isNotBlank(alamat2)) {
                            LOG.info(alamat2);
                            if (alamat2.equalsIgnoreCase("-")) {
                                alamat2 = "";
                            }
                            alamat.setAlamat2(alamat2);

                        }
                        if (StringUtils.isNotBlank(alamat3)) {
                            LOG.info(alamat3);
                            if (alamat3.equalsIgnoreCase("-")) {
                                alamat3 = "";
                            }
                            alamat.setAlamat3(alamat3);

                        }
                        if (StringUtils.isNotBlank(alamat4)) {
                            LOG.info(alamat4);
                            if (alamat4.equalsIgnoreCase("-")) {
                                alamat4 = "";
                            }
                            alamat.setAlamat4(alamat4);

                        }
                        if (StringUtils.isNotBlank(poskod)) {
                            LOG.info(poskod);
                            if (poskod.equalsIgnoreCase("-")) {
                                poskod = "";
                            }
                            alamat.setPoskod(poskod);

                        }
                        if (StringUtils.isNotBlank(negeri)) {
                            LOG.info(negeri);
                            if (negeri.equalsIgnoreCase("-")) {
                                negeri = "";
                            }
                            KodNegeri kn = kodNegeriDAO.findById(negeri);
                            alamat.setNegeri(kn);

                        }
                        pmohon.setAlamat(alamat);
                        audit.setTarikhKemaskini(new java.util.Date());
                        audit.setDiKemaskiniOleh(peng);
                        pmohon.setInfoAudit(audit);
                        pmh.add(pmohon);
                    }
                    pemohonService.saveOrUpdateMultiple(pmh);

                }

            }
        }
        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution saveBetulPemohon() {
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        String idMP = getContext().getRequest().getParameter("idPermohonanPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit audit = peng.getInfoAudit();
        String error = "";
        String msg = "";
        if (idHakmilikPihakBerkepentingan != null) {
            PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihakByIdPemohon(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
            if (map.getPemohon() != null) {
                pemhn = pemohonService.findById(idHakmilikPihakBerkepentingan);
                Permohonan mhn = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));
                if (mhn.getKodUrusan().equals("BETPB")) {
                    PermohonanAtasPerserahan mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhn(p.getIdPermohonan());
                    LOG.info("Start Save betul BETPB");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");

                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(pemhn.getPihak().getNama()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(pemhn.getJenisPengenalan().getKod()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(pemhn.getNoPengenalan()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalanLama)) {
                        LOG.info("No Pengenalan Lama::" + noPengenalanLama);
                        if (noPengenalanLama.equalsIgnoreCase("-")) {
                            noPengenalanLama = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalanLama", noPengenalanLama, (StringUtils.isBlank(pemhn.getPihak().getNama()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = pemhn.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(pemhn.getPihak().getNama()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + pemhn.getPihak().getKodJantina());
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(pemhn.getPihak().getKodJantina()) ? "" : pemhn.getPihak().getKodJantina()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((pemhn.getPihak().getWargaNegara() == null) ? "" : pemhn.getPihak().getWargaNegara().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(pemhn.getJenis().getKod()) ? "" : pemhn.getJenis().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(pemhn.getSyerPenyebut()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(pemhn.getSyerPembilang()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(pemhn.getPihak().getAlamat1()) ? "" : pemhn.getPihak().getAlamat1()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(pemhn.getPihak().getAlamat2()) ? "" : pemhn.getPihak().getAlamat2()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }

                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(pemhn.getPihak().getAlamat3()) ? "" : pemhn.getPihak().getAlamat3()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(pemhn.getPihak().getAlamat4()) ? "" : pemhn.getPihak().getAlamat4()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(pemhn.getPihak().getPoskod()) ? "" : pemhn.getPihak().getPoskod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        if (pemhn != null) {
                            msg = saveAndUpdateMedan("negeri", negeri, (pemhn.getPihak().getNegeri() == null ? "" : pemhn.getPihak().getNegeri().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat1()) ? "" : pemhn.getPihak().getSuratAlamat1()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat2()) ? "" : pemhn.getPihak().getSuratAlamat2()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat3()) ? "" : pemhn.getPihak().getSuratAlamat3()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat4()) ? "" : pemhn.getPihak().getSuratAlamat4()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }
                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(pemhn.getPihak().getSuratPoskod()) ? "" : pemhn.getPihak().getSuratPoskod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (pemhn.getPihak().getSuratNegeri() == null ? "" : pemhn.getPihak().getSuratNegeri().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }

                } else {
                    LOG.info("Start Save betul Pemilik");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");
                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(pemhn.getPihak().getNama()) ? "" : pemhn.getPihak().getNama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(pemhn.getPihak().getJenisPengenalan().getKod()) ? "" : pemhn.getPihak().getJenisPengenalan().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(pemhn.getPihak().getNoPengenalan()) ? "" : pemhn.getPihak().getNoPengenalan()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
//                    if (StringUtils.isNotBlank(noPengenalanLama)) {
//                        LOG.info("No Pengenalan Lama::" + noPengenalanLama);
//                        if (noPengenalanLama.equalsIgnoreCase("-")) {
//                            noPengenalanLama = "";
//                        }
//                        msg = saveAndUpdateMedan("noPengenalanLama", noPengenalanLama, (StringUtils.isBlank(pemhn.getNoPengenalanLama()) ? "" : pemhn.getNoPengenalanLama()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
//                    }
                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = pemhn.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(pemhn.getPihak().getBangsa().getKod()) ? "" : pemhn.getPihak().getBangsa().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + pemhn.getPihak().getKodJantina());

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(pemhn.getPihak().getKodJantina()) ? "" : pemhn.getPihak().getKodJantina()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((pemhn.getPihak().getWargaNegara() == null) ? "" : pemhn.getPihak().getWargaNegara().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(pemhn.getJenis().getKod()) ? "" : pemhn.getJenis().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(pemhn.getSyerPenyebut()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(pemhn.getSyerPembilang()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(pemhn.getPihak().getAlamat1()) ? "" : pemhn.getPihak().getAlamat1()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(pemhn.getPihak().getAlamat2()) ? "" : pemhn.getPihak().getAlamat2()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(pemhn.getPihak().getAlamat3()) ? "" : pemhn.getPihak().getAlamat3()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(pemhn.getPihak().getAlamat4()) ? "" : pemhn.getPihak().getAlamat4()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(pemhn.getPihak().getPoskod()) ? "" : pemhn.getPihak().getPoskod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        msg = saveAndUpdateMedan("negeri", negeri, (pemhn.getPihak().getNegeri() == null ? "" : pemhn.getPihak().getNegeri().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat1()) ? "" : pemhn.getPihak().getSuratAlamat1()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat2()) ? "" : pemhn.getPihak().getSuratAlamat2()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat3()) ? "" : pemhn.getPihak().getSuratAlamat3()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(pemhn.getPihak().getSuratAlamat4()) ? "" : pemhn.getPihak().getSuratAlamat4()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }

                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(pemhn.getPihak().getSuratPoskod()) ? "" : pemhn.getPihak().getSuratPoskod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (pemhn.getPihak().getSuratNegeri() == null ? "" : pemhn.getPihak().getSuratNegeri().getKod()), String.valueOf(pemhn.getIdPemohon()), String.valueOf(map.getIdAtasPihak()));

                    }
                }
            }
        }
//        if (pemhn.getPermohonan() != null) {
//            HakmilikUrusan hu = pService.findHUrusan(pemhn.getHakmilik().getIdHakmilik(), pemhn.getPermohonan().getIdPermohonan());
//            List<PermohonanAtasPerserahan> mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahanList(p.getIdPermohonan(), hu.getIdUrusan());
//            for (PermohonanAtasPerserahan pap : mohonAtsUrusan) {
//                if (mohonAtsUrusan != null) {
//                    LOG.info("pap.getPermohonanBaru().getIdPermohonan():" + pap.getPermohonanBaru().getIdPermohonan());
//                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(pap.getPermohonanBaru().getIdPermohonan(), pemhn.getHakmilik().getIdHakmilik(), pemhn.getPihak().getIdPihak());
//                    List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
//                    for (PermohonanPihak pmphk : mohonPihakList) {
//                        if (StringUtils.isNotBlank(nama)) {
//                            pmphk.setNama(nama);
//                        }
//                        if (StringUtils.isNotBlank(noPengenalan)) {
//                            pmphk.setNoPengenalan(noPengenalan);
//                        }
//                        if (StringUtils.isNotBlank(penyebut)) {
//                            pmphk.setSyerPenyebut(Integer.valueOf(penyebut));
//                            pmphk.setJumlahPenyebut(Integer.valueOf(penyebut));
//                        }
//                        if (StringUtils.isNotBlank(pembilang)) {
//                            pmphk.setSyerPembilang(Integer.valueOf(pembilang));
//                            pmphk.setJumlahPembilang(Integer.valueOf(pembilang));
//                        }
//
//                        Alamat alamat = new Alamat();
//                        if (StringUtils.isNotBlank(alamat1)) {
//                            LOG.info(alamat1);
//                            if (alamat1.equalsIgnoreCase("-")) {
//                                alamat1 = "";
//                            }
//                            alamat.setAlamat1(alamat1);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat2)) {
//                            LOG.info(alamat2);
//                            if (alamat2.equalsIgnoreCase("-")) {
//                                alamat2 = "";
//                            }
//                            alamat.setAlamat2(alamat2);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat3)) {
//                            LOG.info(alamat3);
//                            if (alamat3.equalsIgnoreCase("-")) {
//                                alamat3 = "";
//                            }
//                            alamat.setAlamat3(alamat3);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat4)) {
//                            LOG.info(alamat4);
//                            if (alamat4.equalsIgnoreCase("-")) {
//                                alamat4 = "";
//                            }
//                            alamat.setAlamat4(alamat4);
//
//                        }
//                        if (StringUtils.isNotBlank(poskod)) {
//                            LOG.info(poskod);
//                            if (poskod.equalsIgnoreCase("-")) {
//                                poskod = "";
//                            }
//                            alamat.setPoskod(poskod);
//
//                        }
//                        if (StringUtils.isNotBlank(negeri)) {
//                            LOG.info(negeri);
//                            if (negeri.equalsIgnoreCase("-")) {
//                                negeri = "";
//                            }
//                            KodNegeri kn = kodNegeriDAO.findById(negeri);
//                            alamat.setNegeri(kn);
//
//                        }
//                        pmphk.setAlamat(alamat);
//                        audit.setTarikhKemaskini(new java.util.Date());
//                        audit.setDiKemaskiniOleh(peng);
//                        pmphk.setInfoAudit(audit);
//                        permPihak.add(pmphk);
//
//                    }
//                    permohonanPihakService.saveOrUpdate(permPihak);
//
//                    pemohonList = pemohonService.senaraiPemohonByIdPihakIdHakmilikIdMohon(pemhn.getPihak().getIdPihak(), pemhn.getHakmilik().getIdHakmilik(), pap.getPermohonanBaru().getIdPermohonan());
//                    List<Pemohon> pmh = new ArrayList<Pemohon>();
//
//                    for (Pemohon pmohon : pemohonList) {
//                        if (StringUtils.isNotBlank(nama)) {
//                            pmohon.setNama(nama);
//                        }
//                        if (StringUtils.isNotBlank(noPengenalan)) {
//                            pmohon.setNoPengenalan(noPengenalan);
//                        }
//                        if (StringUtils.isNotBlank(penyebut)) {
//                            pmohon.setSyerPenyebut(Integer.valueOf(penyebut));
//                            pmohon.setJumlahPenyebut(Integer.valueOf(penyebut));
//                        }
//                        if (StringUtils.isNotBlank(pembilang)) {
//                            pmohon.setSyerPembilang(Integer.valueOf(pembilang));
//                            pmohon.setJumlahPembilang(Integer.valueOf(pembilang));
//                        }
//                        Alamat alamat = new Alamat();
//                        if (StringUtils.isNotBlank(alamat1)) {
//                            LOG.info(alamat1);
//                            if (alamat1.equalsIgnoreCase("-")) {
//                                alamat1 = "";
//                            }
//                            alamat.setAlamat1(alamat1);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat2)) {
//                            LOG.info(alamat2);
//                            if (alamat2.equalsIgnoreCase("-")) {
//                                alamat2 = "";
//                            }
//                            alamat.setAlamat2(alamat2);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat3)) {
//                            LOG.info(alamat3);
//                            if (alamat3.equalsIgnoreCase("-")) {
//                                alamat3 = "";
//                            }
//                            alamat.setAlamat3(alamat3);
//
//                        }
//                        if (StringUtils.isNotBlank(alamat4)) {
//                            LOG.info(alamat4);
//                            if (alamat4.equalsIgnoreCase("-")) {
//                                alamat4 = "";
//                            }
//                            alamat.setAlamat4(alamat4);
//
//                        }
//                        if (StringUtils.isNotBlank(poskod)) {
//                            LOG.info(poskod);
//                            if (poskod.equalsIgnoreCase("-")) {
//                                poskod = "";
//                            }
//                            alamat.setPoskod(poskod);
//
//                        }
//                        if (StringUtils.isNotBlank(negeri)) {
//                            LOG.info(negeri);
//                            if (negeri.equalsIgnoreCase("-")) {
//                                negeri = "";
//                            }
//                            KodNegeri kn = kodNegeriDAO.findById(negeri);
//                            alamat.setNegeri(kn);
//
//                        }
//                        pmohon.setAlamat(alamat);
//                        audit.setTarikhKemaskini(new java.util.Date());
//                        audit.setDiKemaskiniOleh(peng);
//                        pmohon.setInfoAudit(audit);
//                        pmh.add(pmohon);
//                    }
//                    pemohonService.saveOrUpdateMultiple(pmh);
//
//                }
//
//            }
//        }
        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution saveBetulMohonPihak() {
        String idHakmilikPihakBerkepentingan = getContext().getRequest().getParameter("idHakmilikPihakBerkepentingan");
        String idMP = getContext().getRequest().getParameter("idPermohonanPihak");
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        LOG.info("idHakmilikPihakBerkepentingan = " + idHakmilikPihakBerkepentingan);
        LOG.info("idPermohonanPihak = " + idPemohon);
        LOG.info("idPermohonanPihak = " + idMP);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit audit = peng.getInfoAudit();
        String error = "";
        String msg = "";
        if (idHakmilikPihakBerkepentingan != null) {
            PermohonanAtasPihakBerkepentingan map = pService.findByAtasPihakByIdMP(idHakmilikPihakBerkepentingan, p.getIdPermohonan());
            if (map.getPermohonanPihak() != null) {
                mohonPihak2 = permohonanPihakDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
                Permohonan mhn = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));
                if (mhn.getKodUrusan().equals("BETPB")) {
                    PermohonanAtasPerserahan mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhn(p.getIdPermohonan());
                    LOG.info("Start Save betul BETPB");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");

                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(noPengenalanLama)) {
                        LOG.info("No Pengenalan Lama::" + noPengenalanLama);
                        if (noPengenalanLama.equalsIgnoreCase("-")) {
                            noPengenalanLama = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalanLama", noPengenalanLama, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = mohonPihak2.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + mohonPihak2.getPihak().getKodJantina());
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(mohonPihak2.getPihak().getKodJantina()) ? "" : mohonPihak2.getPihak().getKodJantina()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((mohonPihak2.getPihak().getWargaNegara() == null) ? "" : mohonPihak2.getPihak().getWargaNegara().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        LOG.info("MSG = " + msg);
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(mohonPihak2.getJenis().getKod()) ? "" : mohonPihak2.getJenis().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(mohonPihak2.getSyerPenyebut()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(mohonPihak2.getSyerPembilang()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat1()) ? "" : mohonPihak2.getPihak().getAlamat1()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat2()) ? "" : mohonPihak2.getPihak().getAlamat2()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }

                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat3()) ? "" : mohonPihak2.getPihak().getAlamat3()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat4()) ? "" : mohonPihak2.getPihak().getAlamat4()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(mohonPihak2.getPihak().getPoskod()) ? "" : mohonPihak2.getPihak().getPoskod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }
                    }

                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        if (mohonPihak2 != null) {
                            msg = saveAndUpdateMedan("negeri", negeri, (mohonPihak2.getPihak().getNegeri() == null ? "" : mohonPihak2.getPihak().getNegeri().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat1()) ? "" : mohonPihak2.getPihak().getSuratAlamat1()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat2()) ? "" : mohonPihak2.getPihak().getSuratAlamat2()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat3()) ? "" : mohonPihak2.getPihak().getSuratAlamat3()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat4()) ? "" : mohonPihak2.getPihak().getSuratAlamat4()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }
                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratPoskod()) ? "" : mohonPihak2.getPihak().getSuratPoskod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (mohonPihak2.getPihak().getSuratNegeri() == null ? "" : mohonPihak2.getPihak().getSuratNegeri().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }

                } else {
                    LOG.info("Start Save betul Pemilik");
                    //    butiran PB check null or not
                    LOG.info("::Butiran PB::");
                    if (StringUtils.isNotBlank(nama)) {
                        LOG.info("Nama::" + nama);
                        if (nama.equalsIgnoreCase("-")) {
                            nama = "";
                        }
                        msg = saveAndUpdateMedan("nama", nama, (StringUtils.isBlank(mohonPihak2.getPihak().getNama()) ? "" : mohonPihak2.getPihak().getNama()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(jenisPengenalan)) {
                        LOG.info("Jenis Pengenalan::" + jenisPengenalan);
                        if (jenisPengenalan.equalsIgnoreCase("-")) {
                            jenisPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("jenisPengenalan", jenisPengenalan, (StringUtils.isBlank(mohonPihak2.getJenisPengenalan().getKod()) ? "" : mohonPihak2.getPihak().getJenisPengenalan().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(noPengenalan)) {
                        LOG.info("No Pengenalan::" + noPengenalan);
                        if (noPengenalan.equalsIgnoreCase("-")) {
                            noPengenalan = "";
                        }
                        msg = saveAndUpdateMedan("noPengenalan", noPengenalan, (StringUtils.isBlank(mohonPihak2.getPihak().getNoPengenalan()) ? "" : mohonPihak2.getPihak().getNoPengenalan()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(bangsa)) {
                        LOG.info("Bangsa::" + bangsa);
                        if (bangsa.equalsIgnoreCase("-")) {
                            bangsa = "";
                        }
                        KodBangsa lama = mohonPihak2.getPihak().getBangsa();
                        if (lama != null) {
                            msg = saveAndUpdateMedan("bangsa", bangsa, (StringUtils.isBlank(mohonPihak2.getPihak().getBangsa().getKod()) ? "" : mohonPihak2.getPihak().getBangsa().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        } else {
                            msg = saveAndUpdateMedan("bangsa", bangsa, "", String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                        }

                    }
                    if (StringUtils.isNotBlank(jantina)) {
                        if (jantina.equalsIgnoreCase("-")) {
                            jantina = "";
                        }
                        LOG.info("jantina::" + jantina);
                        LOG.info("Kod Jantina::" + mohonPihak2.getPihak().getKodJantina());

                        msg = saveAndUpdateMedan("jantina", jantina, (StringUtils.isBlank(mohonPihak2.getPihak().getKodJantina()) ? "" : mohonPihak2.getPihak().getKodJantina()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(warganegara)) {
                        LOG.info("Warganegara::" + warganegara);
                        if (warganegara.equalsIgnoreCase("-")) {
                            warganegara = "";
                        }
                        msg = saveAndUpdateMedan("warganegara", warganegara, ((mohonPihak2.getPihak().getWargaNegara() == null) ? "" : mohonPihak2.getPihak().getWargaNegara().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(jenisPB)) {
                        LOG.info("Jenis PB::" + jenisPB);
                        if (jenisPB.equalsIgnoreCase("-")) {
                            jenisPB = "";
                        }
                        msg = saveAndUpdateMedan("jenisPB", jenisPB, (StringUtils.isBlank(mohonPihak2.getJenis().getKod()) ? "" : mohonPihak2.getJenis().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }

                    if (StringUtils.isNotBlank(penyebut)) {
                        LOG.info("Penyebut::" + penyebut);
                        if (penyebut.equalsIgnoreCase("-")) {
                            penyebut = "";
                        }
                        msg = saveAndUpdateMedan("penyebut", penyebut, String.valueOf(mohonPihak2.getSyerPenyebut()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(pembilang)) {
                        LOG.info("Pembilang::" + pembilang);
                        if (pembilang.equalsIgnoreCase("-")) {
                            pembilang = "";
                        }
                        msg = saveAndUpdateMedan("pembilang", pembilang, String.valueOf(mohonPihak2.getSyerPembilang()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat daftar
                    LOG.info("::Alamat Berdaftar::");
                    if (StringUtils.isNotBlank(alamat1)) {
                        LOG.info(alamat1);
                        if (alamat1.equalsIgnoreCase("-")) {
                            alamat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamat1", alamat1, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat1()) ? "" : mohonPihak2.getPihak().getAlamat1()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat2)) {
                        LOG.info(alamat2);
                        if (alamat2.equalsIgnoreCase("-")) {
                            alamat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamat2", alamat2, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat2()) ? "" : mohonPihak2.getPihak().getAlamat2()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat3)) {
                        LOG.info(alamat3);
                        if (alamat3.equalsIgnoreCase("-")) {
                            alamat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamat3", alamat3, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat3()) ? "" : mohonPihak2.getPihak().getAlamat3()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamat4)) {
                        LOG.info(alamat4);
                        if (alamat4.equalsIgnoreCase("-")) {
                            alamat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamat4", alamat4, (StringUtils.isBlank(mohonPihak2.getPihak().getAlamat4()) ? "" : mohonPihak2.getPihak().getAlamat4()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskod)) {
                        LOG.info(poskod);
                        if (poskod.equalsIgnoreCase("-")) {
                            poskod = "";
                        }
                        msg = saveAndUpdateMedan("poskod", poskod, (StringUtils.isBlank(mohonPihak2.getPihak().getPoskod()) ? "" : mohonPihak2.getPihak().getPoskod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeri)) {
                        LOG.info(negeri);
                        if (negeri.equalsIgnoreCase("-")) {
                            negeri = "";
                        }
                        msg = saveAndUpdateMedan("negeri", negeri, (mohonPihak2.getPihak().getNegeri() == null ? "" : mohonPihak2.getPihak().getNegeri().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
// alamat surat menyurat
                    LOG.info("::Alamat Surat Menyurat::");
                    if (StringUtils.isNotBlank(alamatSurat1)) {
                        LOG.info(alamatSurat1);
                        if (alamatSurat1.equalsIgnoreCase("-")) {
                            alamatSurat1 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat1", alamatSurat1, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat1()) ? "" : mohonPihak2.getPihak().getSuratAlamat1()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat2)) {
                        LOG.info(alamatSurat2);
                        if (alamatSurat2.equalsIgnoreCase("-")) {
                            alamatSurat2 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat2", alamatSurat2, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat2()) ? "" : mohonPihak2.getPihak().getSuratAlamat2()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat3)) {
                        LOG.info(alamatSurat3);
                        if (alamatSurat3.equalsIgnoreCase("-")) {
                            alamatSurat3 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat3", alamatSurat3, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat3()) ? "" : mohonPihak2.getPihak().getSuratAlamat3()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(alamatSurat4)) {
                        LOG.info(alamatSurat4);
                        if (alamatSurat4.equalsIgnoreCase("-")) {
                            alamatSurat4 = "";
                        }
                        msg = saveAndUpdateMedan("alamatSurat4", alamatSurat4, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratAlamat4()) ? "" : mohonPihak2.getPihak().getSuratAlamat4()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(poskodSurat)) {
                        LOG.info(poskodSurat);
                        if (poskodSurat.equalsIgnoreCase("-")) {
                            poskodSurat = "";
                        }

                        msg = saveAndUpdateMedan("poskodSurat", poskodSurat, (StringUtils.isBlank(mohonPihak2.getPihak().getSuratPoskod()) ? "" : mohonPihak2.getPihak().getSuratPoskod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));
                    }
                    if (StringUtils.isNotBlank(negeriSurat)) {
                        LOG.info(negeriSurat);
                        if (negeriSurat.equalsIgnoreCase("-")) {
                            negeriSurat = "";
                        }
                        msg = saveAndUpdateMedan("negeriSurat", negeriSurat, (mohonPihak2.getPihak().getSuratNegeri() == null ? "" : mohonPihak2.getPihak().getSuratNegeri().getKod()), String.valueOf(mohonPihak2.getIdPermohonanPihak()), String.valueOf(map.getIdAtasPihak()));

                    }
                }
            }
        }

        if (mohonPihak2.getPermohonan() != null) {
            HakmilikUrusan hu = pService.findHUrusan(mohonPihak2.getHakmilik().getIdHakmilik(), mohonPihak2.getPermohonan().getIdPermohonan());
            List<PermohonanAtasPerserahan> mohonAtsUrusan = pService.findMohonAtsUrusanbyIdMhnIdPerserahanList(p.getIdPermohonan(), hu.getIdUrusan());
            for (PermohonanAtasPerserahan pap : mohonAtsUrusan) {
                if (mohonAtsUrusan != null) {
                    LOG.info("pap.getPermohonanBaru().getIdPermohonan():" + pap.getPermohonanBaru().getIdPermohonan());
                    mohonPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(pap.getPermohonanBaru().getIdPermohonan(), mohonPihak2.getHakmilik().getIdHakmilik(), mohonPihak2.getPihak().getIdPihak());
                    List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
                    for (PermohonanPihak pmphk : mohonPihakList) {
                        if (StringUtils.isNotBlank(nama)) {
                            pmphk.setNama(nama);
                        }
                        if (StringUtils.isNotBlank(noPengenalan)) {
                            pmphk.setNoPengenalan(noPengenalan);
                        }
                        if (StringUtils.isNotBlank(penyebut)) {
                            pmphk.setSyerPenyebut(Integer.valueOf(penyebut));
                            pmphk.setJumlahPenyebut(Integer.valueOf(penyebut));
                        }
                        if (StringUtils.isNotBlank(pembilang)) {
                            pmphk.setSyerPembilang(Integer.valueOf(pembilang));
                            pmphk.setJumlahPembilang(Integer.valueOf(pembilang));
                        }

                        Alamat alamat = new Alamat();
                        if (StringUtils.isNotBlank(alamat1)) {
                            LOG.info(alamat1);
                            if (alamat1.equalsIgnoreCase("-")) {
                                alamat1 = "";
                            }
                            alamat.setAlamat1(alamat1);

                        }
                        if (StringUtils.isNotBlank(alamat2)) {
                            LOG.info(alamat2);
                            if (alamat2.equalsIgnoreCase("-")) {
                                alamat2 = "";
                            }
                            alamat.setAlamat2(alamat2);

                        }
                        if (StringUtils.isNotBlank(alamat3)) {
                            LOG.info(alamat3);
                            if (alamat3.equalsIgnoreCase("-")) {
                                alamat3 = "";
                            }
                            alamat.setAlamat3(alamat3);

                        }
                        if (StringUtils.isNotBlank(alamat4)) {
                            LOG.info(alamat4);
                            if (alamat4.equalsIgnoreCase("-")) {
                                alamat4 = "";
                            }
                            alamat.setAlamat4(alamat4);

                        }
                        if (StringUtils.isNotBlank(poskod)) {
                            LOG.info(poskod);
                            if (poskod.equalsIgnoreCase("-")) {
                                poskod = "";
                            }
                            alamat.setPoskod(poskod);

                        }
                        if (StringUtils.isNotBlank(negeri)) {
                            LOG.info(negeri);
                            if (negeri.equalsIgnoreCase("-")) {
                                negeri = "";
                            }
                            KodNegeri kn = kodNegeriDAO.findById(negeri);
                            alamat.setNegeri(kn);

                        }
                        pmphk.setAlamat(alamat);
                        audit.setTarikhKemaskini(new java.util.Date());
                        audit.setDiKemaskiniOleh(peng);
                        pmphk.setInfoAudit(audit);
                        permPihak.add(pmphk);

                    }
//                    permohonanPihakService.saveOrUpdate(permPihak);

                    pemohonList = pemohonService.senaraiPemohonByIdPihakIdHakmilikIdMohon(mohonPihak2.getPihak().getIdPihak(), mohonPihak2.getHakmilik().getIdHakmilik(), pap.getPermohonanBaru().getIdPermohonan());
                    List<Pemohon> pmh = new ArrayList<Pemohon>();

                    for (Pemohon pmohon : pemohonList) {
                        if (StringUtils.isNotBlank(nama)) {
                            pmohon.setNama(nama);
                        }
                        if (StringUtils.isNotBlank(noPengenalan)) {
                            pmohon.setNoPengenalan(noPengenalan);
                        }
                        if (StringUtils.isNotBlank(penyebut)) {
                            pmohon.setSyerPenyebut(Integer.valueOf(penyebut));
                            pmohon.setJumlahPenyebut(Integer.valueOf(penyebut));
                        }
                        if (StringUtils.isNotBlank(pembilang)) {
                            pmohon.setSyerPembilang(Integer.valueOf(pembilang));
                            pmohon.setJumlahPembilang(Integer.valueOf(pembilang));
                        }
                        Alamat alamat = new Alamat();
                        if (StringUtils.isNotBlank(alamat1)) {
                            LOG.info(alamat1);
                            if (alamat1.equalsIgnoreCase("-")) {
                                alamat1 = "";
                            }
                            alamat.setAlamat1(alamat1);

                        }
                        if (StringUtils.isNotBlank(alamat2)) {
                            LOG.info(alamat2);
                            if (alamat2.equalsIgnoreCase("-")) {
                                alamat2 = "";
                            }
                            alamat.setAlamat2(alamat2);

                        }
                        if (StringUtils.isNotBlank(alamat3)) {
                            LOG.info(alamat3);
                            if (alamat3.equalsIgnoreCase("-")) {
                                alamat3 = "";
                            }
                            alamat.setAlamat3(alamat3);

                        }
                        if (StringUtils.isNotBlank(alamat4)) {
                            LOG.info(alamat4);
                            if (alamat4.equalsIgnoreCase("-")) {
                                alamat4 = "";
                            }
                            alamat.setAlamat4(alamat4);

                        }
                        if (StringUtils.isNotBlank(poskod)) {
                            LOG.info(poskod);
                            if (poskod.equalsIgnoreCase("-")) {
                                poskod = "";
                            }
                            alamat.setPoskod(poskod);

                        }
                        if (StringUtils.isNotBlank(negeri)) {
                            LOG.info(negeri);
                            if (negeri.equalsIgnoreCase("-")) {
                                negeri = "";
                            }
                            KodNegeri kn = kodNegeriDAO.findById(negeri);
                            alamat.setNegeri(kn);

                        }
                        pmohon.setAlamat(alamat);
                        audit.setTarikhKemaskini(new java.util.Date());
                        audit.setDiKemaskiniOleh(peng);
                        pmohon.setInfoAudit(audit);
                        pmh.add(pmohon);
                    }
//                    pemohonService.saveOrUpdateMultiple(pmh);

                }

            }
        }
        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public String saveAndUpdateMedan(String namaMedan, String valueMedan, String nilaiLama, String idHakmilikPihakBerkepentingan, String idAtasPihak) {
        String error = "";
        String msg = "";
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("Entering Save in KKini");
        LOG.info("::Butiran PB::");
        info = pguna.getInfoAudit();
        PermohonanAtasPihakBerkepentingan paPihakBerkepentingan = permohonanAtasPihakBerkepentinganDAO.findById(Long.parseLong(idAtasPihak));
        if (paPihakBerkepentingan.getPihakBerkepentingan() != null) {
            hpMAP = hakmilikPihakBerkepentinganDAO.findById(paPihakBerkepentingan.getPihakBerkepentingan().getIdHakmilikPihakBerkepentingan());
        } else if (paPihakBerkepentingan.getPermohonanPihak() != null) {
            mpMap = permohonanPihakDAO.findById(paPihakBerkepentingan.getPermohonanPihak().getIdPermohonanPihak());
        } else if (paPihakBerkepentingan.getPemohon() != null) {
            pemohonMap = pemohonService.findById(String.valueOf(paPihakBerkepentingan.getPemohon().getIdPemohon()));
        }
        LOG.info(" getIdAtasPihak = " + paPihakBerkepentingan.getIdAtasPihak());
        permohonanPihakKemaskini = pService.findByidMohonNamaMedanidAtasPihak(p.getIdPermohonan(), namaMedan, String.valueOf(paPihakBerkepentingan.getIdAtasPihak()));
        List<PermohonanAtasPerserahan> mohonAtasPerserahanList = permohonanAtasPerserahanService.findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(idPermohonan);

//        LOG.info("idPihak = " + idPihak);
        if (mohonAtasPerserahanList.size() > 0) {
            for (PermohonanAtasPerserahan mohonAtasPerserahan : mohonAtasPerserahanList) {
                if (permohonanPihakKemaskini == null) {
                    LOG.info("Entering Save");
                    permohonanPihakKemaskini = new PermohonanPihakKemaskini();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                    permohonanPihakKemaskini.setPermohonan(p);//not null
                    permohonanPihakKemaskini.setInfoAudit(info);//not null
                    permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                    permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                    permohonanPihakKemaskini.setNilaiBaru(valueMedan);
                    permohonanPihakKemaskini.setNilaiLama(nilaiLama);

                    pService.saveKKini(permohonanPihakKemaskini);
                    if (mohonAtasPerserahan != null) {

                        LOG.info("NAMA MEDAN = " + namaMedan);
//                        if (namaMedan.equals("jenisPB")) {
                        if (hpMAP != null) {
                            permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                            permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                        }
                        if (mpMap != null) {
                            LOG.info("mpMap.getJenis() = " + mpMap.getJenis());
                            LOG.info("mpMap.getPermohonan().getIdPermohonan() = " + mpMap.getPermohonan().getIdPermohonan());
                            LOG.info("mpMap.getPihak() = " + mpMap.getPihak());
                            Pihak idPihak = mpMap.getPihak();
                            String idPermohonan = mpMap.getPermohonan().getIdPermohonan();
                            KodJenisPihakBerkepentingan jenisKP = mpMap.getJenis();
                            permohonanPihakKemaskini.setJenis(jenisKP);
                            permohonanPihakKemaskini.setIdPermohonanLama(idPermohonan);
                            permohonanPihakKemaskini.setPihak(idPihak);
                        }
                        if (pemohonMap != null) {
                            permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                            permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                            permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                        }
                        permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                    }
                    msg = "Maklumat Berjaya Disimpan.";
                } else {
                    LOG.info("Entering UPDATE");
                    info.setDiKemaskiniOleh(pguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    info.setDimasukOleh(paPihakBerkepentingan.getInfoAudit().getDimasukOleh());
                    info.setTarikhMasuk(paPihakBerkepentingan.getInfoAudit().getTarikhMasuk());
                    permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
                    permohonanPihakKemaskini.setPermohonan(p);//not null
                    permohonanPihakKemaskini.setInfoAudit(info);//not null
                    permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
                    permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
                    permohonanPihakKemaskini.setNilaiBaru(valueMedan);
                    permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                    permohonanPihakKemaskini.setPihak(idPihak);
                    pService.updateKKini(permohonanPihakKemaskini);
                    if (mohonAtasPerserahan != null) {
                        if (hpMAP != null) {
                            permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                            permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
                        }
                        if (mpMap != null) {
                            permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                            permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                            permohonanPihakKemaskini.setPihak(mpMap.getPihak());
                        }
                        if (pemohonMap != null) {
                            permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                            permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                            permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
                        }
                    }
                    permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
                    msg = "Maklumat Berjaya Dikemaskini";
                }
            }
        }

        if (permohonanPihakKemaskini == null) {
            LOG.info("Entering Save");
            permohonanPihakKemaskini = new PermohonanPihakKemaskini();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
            permohonanPihakKemaskini.setPermohonan(p);//not null
            permohonanPihakKemaskini.setInfoAudit(info);//not null
            permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
            permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
            permohonanPihakKemaskini.setNilaiBaru(valueMedan);
            permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                permohonanPihakKemaskini.setPihak(idPihak);
            pService.saveKKini(permohonanPihakKemaskini);
            if (hpMAP != null) {
                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
            }
            if (mpMap != null) {
                permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                permohonanPihakKemaskini.setPihak(mpMap.getPihak());
            }
            if (pemohonMap != null) {
                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
            }
            permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
            msg = "Maklumat Berjaya Disimpan.";
        } else {
            LOG.info("Entering UPDATE");
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            info.setDimasukOleh(paPihakBerkepentingan.getInfoAudit().getDimasukOleh());
            info.setTarikhMasuk(paPihakBerkepentingan.getInfoAudit().getTarikhMasuk());
            permohonanPihakKemaskini.setAtasPihakBerkepentingan(paPihakBerkepentingan);
            permohonanPihakKemaskini.setPermohonan(p);//not null
            permohonanPihakKemaskini.setInfoAudit(info);//not null
            permohonanPihakKemaskini.setCawangan(pguna.getKodCawangan());
            permohonanPihakKemaskini.setNamaMedan(namaMedan);//not null
            permohonanPihakKemaskini.setNilaiBaru(valueMedan);
            permohonanPihakKemaskini.setNilaiLama(nilaiLama);
//                permohonanPihakKemaskini.setPihak(idPihak);
            pService.updateKKini(permohonanPihakKemaskini);
            if (hpMAP != null) {
                permohonanPihakKemaskini.setJenis(hpMAP.getJenis());
                permohonanPihakKemaskini.setPihak(hpMAP.getPihak());
            }
            if (mpMap != null) {
                permohonanPihakKemaskini.setJenis(mpMap.getJenis());
                permohonanPihakKemaskini.setIdPermohonanLama(mpMap.getPermohonan().getIdPermohonan());
                permohonanPihakKemaskini.setPihak(mpMap.getPihak());
            }
            if (pemohonMap != null) {
                permohonanPihakKemaskini.setJenis(pemohonMap.getJenis());
                permohonanPihakKemaskini.setIdPermohonanLama(pemohonMap.getPermohonan().getIdPermohonan());
                permohonanPihakKemaskini.setPihak(pemohonMap.getPihak());
            }
            permohonanPihakKemaskiniService.save(permohonanPihakKemaskini);
            msg = "Maklumat Berjaya Dikemaskini";
        }

        return msg;
    }

    public Resolution saveBetulWaris() throws ParseException {
        String error = "";
        String msg = "";
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = pguna.getInfoAudit();
        idWaris = (String) getContext().getRequest().getParameter("idWaris");
        LOG.info(idWaris);
        waris = pService.findWarisByID(idWaris);
        warisbetul = pService.findWarisBetulByID(idWaris);
        hpBerkepentingan = hakmilikPihakBerkepentinganDAO.findById(waris.getPemegangAmanah().getIdHakmilikPihakBerkepentingan());

        if (warisbetul == null) {
            warisbetul = new HakmilikWaris();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            String status2 = "MA";
            warisbetul.setCawangan(pguna.getKodCawangan());
            warisbetul.setInfoAudit(info);
            warisbetul.setNama(nama);
            LOG.debug("jenis kp ===" + jenisPengenalan);
            if (jenisPengenalan != null) {
                KodJenisPengenalan kjp = new KodJenisPengenalan();
                kjp.setKod(jenisPengenalan);
                warisbetul.setJenisPengenalan(kjp);
            }
            warisbetul.setNoPengenalan(noPengenalan);
            warisbetul.setStatus(status2);
            warisbetul.setBetulWaris(waris);
            if (negeri != null) {
                KodNegeri kn = new KodNegeri();
                kn.setKod(negeri);
                warisbetul.setNegeri(kn);
            }
            if ((pembilang != null) && (penyebut != null)) {
                warisbetul.setSyerPembilang(Long.parseLong(pembilang));
                warisbetul.setSyerPenyebut(Long.parseLong(penyebut));
            }
            warisbetul.setPemegangAmanah(hpBerkepentingan);
            if (warganegara != null) {
                KodWarganegara kw = new KodWarganegara();
                kw.setKod(warganegara);
                warisbetul.setWargaNegara(kw);
            }
            warisbetul.setPermohonanPembetulan(p);
            pService.simpanWaris(warisbetul);
            msg = "Kemaskini Data Pembetulan Telah Berjaya";
        } else {
            LOG.info("Update Existing Record");
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
            String status2 = "MA";
            warisbetul.setCawangan(pguna.getKodCawangan());
            warisbetul.setInfoAudit(info);
            warisbetul.setNama(nama);
            if (jenisPengenalan != null) {
                KodJenisPengenalan kjp = new KodJenisPengenalan();
                kjp.setKod(jenisPengenalan);
                warisbetul.setJenisPengenalan(kjp);
            }
            warisbetul.setNoPengenalan(noPengenalan);
            warisbetul.setStatus(status2);
            warisbetul.setBetulWaris(waris);
            if (negeri != null) {
                KodNegeri kn = new KodNegeri();
                kn.setKod(negeri);
                warisbetul.setNegeri(kn);
            }
            if ((pembilang != null) && (penyebut != null)) {
                warisbetul.setSyerPembilang(Long.parseLong(pembilang));
                warisbetul.setSyerPenyebut(Long.parseLong(penyebut));
            }
            warisbetul.setPemegangAmanah(hpBerkepentingan);
            if (warganegara != null) {
                KodWarganegara kw = new KodWarganegara();
                kw.setKod(warganegara);
                warisbetul.setWargaNegara(kw);
            }
            warisbetul.setPermohonanPembetulan(p);
            pService.updateWaris(waris);
            msg = "Kemaskini Data Pembetulan Telah Berjaya";

        }
        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm1").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteWaris() {

        String idWaris = getContext().getRequest().getParameter("idWaris");
        LOG.info(idWaris);
        if (idWaris != null) {

            warisbetul = pService.findWarisBetulByIDwaris(idWaris);
            if (warisbetul != null) {
                pService.deleteWaris(warisbetul);

            }

        }
        rehydrate();

        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "showForm1");
    }

    public Resolution semakSyer() {
        String result = "";
//        String result = "Pembahagian tanah berjaya.";
        int i = 0;
        String idPihak = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("pembilang");//pembilang
        String s2 = getContext().getRequest().getParameter("penyebut");//penyebut
        LOG.debug("s1:" + s1);
        LOG.debug("s2:" + s2);
        LOG.debug("idpihak:" + idPihak);
        List<PermohonanPihak> permPihak = new ArrayList<PermohonanPihak>();
        for (PermohonanPihak pp : mohonPihakList) {
//            Fraction f = new Fraction(Integer.parseInt(syer1[i]), Integer.parseInt(syer2[i]));
//            pp.setSyer(f.doubleValue());
            pp.setSyerPembilang(Integer.parseInt(syer1[i]));
            pp.setSyerPenyebut(Integer.parseInt(syer2[i]));
            permPihak.add(pp);
            i = i + 1;
        }
        permohonanPihakService.saveOrUpdate(permPihak);

        List<HakmilikPermohonan> senaraiPermohonan = p.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : senaraiPermohonan) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            try {
                int r = syerService.doValidateSyerPortion(p.getIdPermohonan(), hm.getIdHakmilik());
                if (r < 0) {
                    result = "Jumlah pembahagian tanah tidak mencukupi.";
                    break;
                } else if (r > 0) {
                    result = "Jumlah pembahagian tanah melebihi daripada bahagian pemohon.";
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return new StreamingResolution("text/plain", "Pembahagian Tanah Gagal.");
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution agihSamaRata() {
        String results = "0";
        String DELIM = "__^$__";
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        } else {
            hm = p.getSenaraiHakmilik().get(0).getHakmilik();
        }

        //TODO : multiple hakmilik
//        Hakmilik hm = p.getSenaraiHakmilik().get(0).getHakmilik();
//        List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
        Fraction sumAllPemohon = Fraction.ZERO;
        Fraction samaRata = Fraction.ZERO;

        if (hm != null) {
            LOG.debug("************");

            for (Pemohon pemohon : pemohonList) {
                HakmilikPihakBerkepentingan hmk = hakmilikPihakKepentinganService.findHakmilikPihakByPihak(pemohon.getPihak().getIdPihak(), hm);
                if (hmk != null) {
                    LOG.debug("pihak = " + pemohon.getPihak().getNama());
                    LOG.debug("pembilang =" + hmk.getSyerPembilang());
                    LOG.debug("penyebut =" + hmk.getSyerPenyebut());

                    sumAllPemohon = sumAllPemohon.add(new Fraction(hmk.getSyerPembilang(), hmk.getSyerPenyebut()));
                    continue;
                }
            }
            LOG.debug("************");
        }
        LOG.debug("sum all = " + sumAllPemohon);
        if (sumAllPemohon.getDenominator() == 1) {
            sumAllPemohon = Fraction.ONE;
        }
        LOG.debug("sum all = " + sumAllPemohon);

        if (mohonPihakList != null && mohonPihakList.size() > 0) {
            int pemohonList = 0;

            for (PermohonanPihak pp : mohonPihakList) {
                if (pp == null || pp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik h = pp.getHakmilik();
                if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                    pemohonList++;
                }
            }
            if (pemohonList > 0) {
                List<PermohonanPihak> senarai = new ArrayList<PermohonanPihak>();
                samaRata = sumAllPemohon.divide(pemohonList);
                for (PermohonanPihak pp : mohonPihakList) {
                    if (pp == null || pp.getHakmilik() == null) {
                        continue;
                    }
                    Hakmilik h = pp.getHakmilik();
                    if (h.getIdHakmilik().equals(hm.getIdHakmilik())) {
                        pp.setSyerPembilang(samaRata.getNumerator());
                        pp.setSyerPenyebut(samaRata.getDenominator());
                        senarai.add(pp);
                    }
                }
                permohonanPihakService.saveOrUpdate(senarai);
            }

            StringBuilder s = new StringBuilder();
            s.append(samaRata.getNumerator()).append(DELIM).append(samaRata.getDenominator());
            results = s.toString();
            LOG.debug(results);
        }

//        permohonanPihakService.saveOrUpdate(mohonPihakList);
//        return new RedirectResolution(HapusPihakBerkepentinganActionBean.class, "getSenaraiHakmilikKepentingan");
        return new StreamingResolution("text/plain", results);
    }

    public Resolution updateSyerMohonPihak() {
        String idPihak = getContext().getRequest().getParameter("idpihak");
        String s1 = getContext().getRequest().getParameter("pembilang");//pembilang
        String s2 = getContext().getRequest().getParameter("penyebut");//penyebut
        LOG.debug("s1:" + s1);
        LOG.debug("s2:" + s2);
        LOG.debug("idpihak:" + idPihak);
        if (StringUtils.isNotBlank(idPihak) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            LOG.debug(idPihak);
            LOG.debug(s1);
            LOG.debug(s2);
            PermohonanPihak pp = permohonanPihakService.findById(idPihak);
            pp.setSyerPembilang(Integer.parseInt(s1));
            pp.setSyerPenyebut(Integer.parseInt(s2));
            permohonanPihakService.saveOrUpdate(pp);
        }
        return new StreamingResolution("text/plain", "1");
    }
}

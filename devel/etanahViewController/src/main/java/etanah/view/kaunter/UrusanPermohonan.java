package etanah.view.kaunter;

/**
 * 25/4	Renamed from UrusanCache
 */
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.ActionBean;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAnsuran;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanUrusan;
import etanah.model.UrusanDokumen;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.AkaunService;
import etanah.service.KaunterService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahContextListener;
import java.math.BigInteger;
import org.apache.commons.lang.StringUtils;

public class UrusanPermohonan implements UrusanKaunter {

    public static final String KOD_JABATAN_PENDAFTARAN = "16";
    public static final String KOD_JABATAN_STRATA = "20";
    public static final String KOD_JABATAN_CONSENT = "22";
    public static final String KOD_JABATAN_PELUPUSAN = "2";
    public static final String KOD_DOKUMEN_AKUAN_PENERIMAAN = "UNKN1";
    private static final long serialVersionUID = 1L;
    String kodUrusan;
    String kumpulanUrusan;
    Character kategoriPemohon = 'X';
    ArrayList<DokumenValue> senaraiDokumenSerahan = new ArrayList<DokumenValue>();
    ArrayList<DokumenValue> senaraiDokumenTamb = new ArrayList<DokumenValue>();
    ArrayList<String> hakmilikPermohonan = new ArrayList<String>();
    ArrayList<String> idHakmilikSiriDari = new ArrayList<String>();
    ArrayList<String> idHakmilikSiriKe = new ArrayList<String>();
    BigDecimal jumlahCaj = BigDecimal.ZERO;
    // values for below, see kaunterUrusanInfo.properties
    String kodJabatan;
    String kodUrusanPilih;
    boolean berkaitanSebelum = false;
    String labelAmaun1;
    BigDecimal amaun1;
    BigDecimal amaun2; //added by murali 05/09/2012
    BigDecimal amaun3; //added by murali 05/09/2012
    String labelTarikh1;
    Date tarikh1;
    String labelNilai1;
    String nilai1;
    String sebabLain;
    String sebabKecuali;
    String pegawaiLain;
    String pegawaiKecuali;
    int katgTanah = 1;

    BigDecimal nilaiJPPH = new BigDecimal(BigInteger.ZERO);
    Long jumlahTDK = Long.parseLong("0");
    String kategori;
    /**
     * Label for displaying all list for selection.
     */
    ArrayList<String> labelNilai1Senarai;
    ArrayList<Integer> nilai1Senarai;
    String labelTeks1;
    String teks1;
    Dokumen akuanPenerimaan;
    // urusan pendaftaran sahaja
    int suratSABKuantiti = 0;
    ArrayList<String> suratSAD = new ArrayList<String>();
    int suratSWBKuantiti = 0;
    ArrayList<String> suratSWD = new ArrayList<String>();
    int suratSBBKuantiti = 0;
    ArrayList<String> suratSBD = new ArrayList<String>();
    //SM
    int SMKuantiti = 0;
    ArrayList<String> smPerserahan = new ArrayList<String>();
    String noRujukan;
    Permohonan permohonan;
    ArrayList<UrusanPermohonan> senaraiPermohonan;
    BigDecimal cajPengecualian;
    BigDecimal cajPelepasan;
    BigDecimal cajNotis;
    BigDecimal cajFail;
    BigDecimal cajLain;
    String idPermohonanSebelum;
    // position in Session's WorkData's List<UrusanCache>
    int position;
    long idDokumen1;
    //for multiple sijil carian
    List<Long> senaraiDokumen;
    
    String ptdflow = "http://xmlns.oracle.com/Hasil/Project1/Humantask1";
    String ptgflow = "http://xmlns.oracle.com/AnsuranHasilPTG/AnsuranPTG/Humantask_AnsuranPTG";
    boolean ptgtrue;

    /*
     long idTransaksiCaj;
     long idTransaksiCajPelepasan;
     long idTransaksiCajPengecualian;
     long idTransaksiCajNotis;
     long idTransaksiCajFail;
     long idTransaksiCajLain;
     */
    @Inject
    private PermohonanUtil permohonanUtil;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    @Inject
    private UrusanPostProcess urusanPostProcessor;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    private Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodUOMDAO kodUOMDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    AkaunService akaunService;
//    @Inject
//    private ReportUtil reportUtil;
    // No. generator for Surat Kuasa Wakil
    @Inject
    private etanah.sequence.GeneratorIdWakil idWakilGenerator;
    final Map<String, String[]> URUSAN_INFO = new HashMap<String, String[]>();
    private static Logger LOG = Logger.getLogger(UrusanPermohonan.class);
    private static boolean debug = LOG.isDebugEnabled();
    private String pbbd;

    private StringBuilder catatan = new StringBuilder();

    @Override
    public List<DokumenValue> getSenaraiDokumenSerahan() {
        return senaraiDokumenSerahan;
    }

    public List<DokumenValue> getSenaraiDokumenTamb() {
        return senaraiDokumenTamb;
    }

    @Override
    public List<String> getSenaraiIdHakmilik() {
        // TODO
        return null;
    }

    public String getKodJabatan() {
        LOG.debug(kodJabatan);
        return kodJabatan;
    }

    public void setKodJabatan(String kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getKumpulanUrusan() {
        return kumpulanUrusan;
    }

    public boolean isPtgtrue() {
        return ptgtrue;
    }

    public void setPtgtrue(boolean ptgtrue) {
        this.ptgtrue = ptgtrue;
    }

    public void setKumpulanUrusan(String kumpulan) {
        kumpulanUrusan = kumpulan;
    }

    public void setJenisPemohon(Character jenisPemohon) {
        this.kategoriPemohon = jenisPemohon;
    }

    public Character getJenisPemohon() {
        return kategoriPemohon;
    }

    public String getNamaUrusan() {
        if (kodUrusan != null) {
            LOG.debug(kodUrusan);
            return kodUrusanDAO.findById(kodUrusan).getNama();
        } else {
            return null;
        }
    }

    public String getKodUrusanPilih() {
        return kodUrusanPilih;
    }

    public void setKodUrusanPilih(String kodUrusanPilih) {
        this.kodUrusanPilih = kodUrusanPilih;
    }

    public boolean isBerkaitanSebelum() {
        return berkaitanSebelum;
    }

    public void setBerkaitanSebelum(boolean berkaitanSebelum) {
        this.berkaitanSebelum = berkaitanSebelum;
    }

    public List<String> getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(ArrayList<String> hakmilikPermohonan) {
        LOG.debug("setHakmilikPermohonan invoked");
        LOG.debug("setHakmilikPermohonan.size=" + hakmilikPermohonan.size());
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public void setIdHakmilikSiriDari(ArrayList<String> idHakmilikSiriDari) {
        this.idHakmilikSiriDari = idHakmilikSiriDari;
    }

    public ArrayList<String> getIdHakmilikSiriDari() {
        return idHakmilikSiriDari;
    }

    public int getKatgTanah() {
        return katgTanah;
    }

    public void setKatgTanah(int katgTanah) {
        this.katgTanah = katgTanah;
    }

    public void setIdHakmilikSiriKe(ArrayList<String> idHakmilikSiriKe) {
        this.idHakmilikSiriKe = idHakmilikSiriKe;
    }

    public ArrayList<String> getIdHakmilikSiriKe() {
        return idHakmilikSiriKe;
    }

    public String getLabelAmaun1() {
        return labelAmaun1;
    }

    public BigDecimal getAmaun1() {
        return amaun1;
    }

    public void setAmaun1(BigDecimal amaun1) {
        this.amaun1 = amaun1;
    }

    public String getLabelTarikh1() {
        return labelTarikh1;
    }

    public Date getTarikh1() {
        return tarikh1;
    }

    public void setTarikh1(Date tarikh1) {
        this.tarikh1 = tarikh1;
    }

    public String getLabelNilai1() {
        return labelNilai1;
    }

    public String getNilai1() {
        return nilai1;
    }

    public void setNilai1(String nilai1) {
        this.nilai1 = nilai1;
    }

    public void setLabelNilai1senarai(ArrayList<String> labelNilai1Senarai) {
        this.labelNilai1Senarai = labelNilai1Senarai;
    }

    public ArrayList<String> getLabelNilai1Senarai() {
        return labelNilai1Senarai;
    }

    public void setNilai1senarai(ArrayList<Integer> nilai1Senarai) {
        this.nilai1Senarai = nilai1Senarai;
    }

    public ArrayList<Integer> getNilai1Senarai() {
        return nilai1Senarai;
    }

    public String getLabelTeks1() {
        return labelTeks1;
    }

    public void setLabelTeks1(String labelTeks1) {
        this.labelTeks1 = labelTeks1;
    }

    public String getTeks1() {
        return teks1;
    }

    public void setTeks1(String teks1) {
        this.teks1 = teks1;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String id) {
        this.noRujukan = id;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public int getSuratSABKuantiti() {
        return suratSABKuantiti;
    }

    public void setSuratSABKuantiti(int suratSABKuantiti) {
        this.suratSABKuantiti = suratSABKuantiti;
    }

    public ArrayList<String> getSuratSAD() {
        return suratSAD;
    }

    public void setSuratSAD(ArrayList<String> suratSAD) {
        this.suratSAD = suratSAD;
    }

    public int getSuratSWBKuantiti() {
        return suratSWBKuantiti;
    }

    public void setSuratSWBKuantiti(int suratSWBKuantiti) {
        this.suratSWBKuantiti = suratSWBKuantiti;
    }

    public ArrayList<String> getSuratSWD() {
        return suratSWD;
    }

    public void setSuratSWD(ArrayList<String> suratSWD) {
        this.suratSWD = suratSWD;
    }

    public int getSuratSBBKuantiti() {
        return suratSBBKuantiti;
    }

    public void setSuratSBBKuantiti(int suratSBBKuantiti) {
        this.suratSBBKuantiti = suratSBBKuantiti;
    }

    public String getPtdflow() {
        return ptdflow;
    }

    public void setPtdflow(String ptdflow) {
        this.ptdflow = ptdflow;
    }

    public String getPtgflow() {
        return ptgflow;
    }

    public void setPtgflow(String ptgflow) {
        this.ptgflow = ptgflow;
    }


    public ArrayList<String> getSuratSBD() {
        return suratSBD;
    }

    public void setSuratSBD(ArrayList<String> suratSBD) {
        this.suratSBD = suratSBD;
    }

    public BigDecimal getCajPengecualian() {
        return cajPengecualian;
    }

    public void setCajPengecualian(BigDecimal cajPengecualian) {
        this.cajPengecualian = cajPengecualian;
    }

    public BigDecimal getCajPelepasan() {
        return cajPelepasan;
    }

    public void setCajPelepasan(BigDecimal cajPelepasan) {
        this.cajPelepasan = cajPelepasan;
    }

    public BigDecimal getCajNotis() {
        return cajNotis;
    }

    public void setCajNotis(BigDecimal cajNotis) {
        this.cajNotis = cajNotis;
    }

    public BigDecimal getCajFail() {
        return cajFail;
    }

    public void setCajFail(BigDecimal cajFail) {
        this.cajFail = cajFail;
    }

    public BigDecimal getCajLain() {
        return cajLain;
    }

    public void setCajLain(BigDecimal cajLain) {
        this.cajLain = cajLain;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public long getIdDokumen1() {
        return idDokumen1;
    }

    public void setIdDokumen1(long idDokumen1) {
        this.idDokumen1 = idDokumen1;
    }

    public List<Long> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Long> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public BigDecimal getAmaun2() {
        return amaun2;
    }

    public void setAmaun2(BigDecimal amaun2) {
        this.amaun2 = amaun2;
    }

    public BigDecimal getAmaun3() {
        return amaun3;
    }

    public void setAmaun3(BigDecimal amaun3) {
        this.amaun3 = amaun3;
    }

    public int getSMKuantiti() {
        return SMKuantiti;
    }

    public void setSMKuantiti(int SMKuantiti) {
        this.SMKuantiti = SMKuantiti;
    }

    public ArrayList<String> getSmPerserahan() {
        return smPerserahan;
    }

    public void setSmPerserahan(ArrayList<String> smPerserahan) {
        this.smPerserahan = smPerserahan;
    }

    public String getSebabLain() {
        return sebabLain;
    }

    public void setSebabLain(String sebabLain) {
        this.sebabLain = sebabLain;
    }

    public String getPegawaiLain() {
        return pegawaiLain;
    }

    public void setPegawaiLain(String pegawaiLain) {
        this.pegawaiLain = pegawaiLain;
    }

    public String getPegawaiKecuali() {
        return pegawaiKecuali;
    }

    public void setPegawaiKecuali(String pegawaiKecuali) {
        this.pegawaiKecuali = pegawaiKecuali;
    }

    public String getPbbd() {
        return pbbd;
    }

    public void setPbbd(String pbbd) {
        this.pbbd = pbbd;
    }

    public BigDecimal getNilaiJPPH() {
        return nilaiJPPH;
    }

    public void setNilaiJPPH(BigDecimal nilaiJPPH) {
        this.nilaiJPPH = nilaiJPPH;
    }

    public Long getJumlahTDK() {
        return jumlahTDK;
    }

    public void setJumlahTDK(Long jumlahTDK) {
        this.jumlahTDK = jumlahTDK;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    /*
     public long getIdTransaksiCaj() {
     return idTransaksiCaj;
     }

     public void setIdTransaksiCaj(long idTransaksiCaj) {
     this.idTransaksiCaj = idTransaksiCaj;
     }

     public long getIdTransaksiCajFail() {
     return idTransaksiCajFail;
     }

     public void setIdTransaksiCajFail(long idTransaksiCajFail) {
     this.idTransaksiCajFail = idTransaksiCajFail;
     }

     public long getIdTransaksiCajLain() {
     return idTransaksiCajLain;
     }

     public void setIdTransaksiCajLain(long idTransaksiCajLain) {
     this.idTransaksiCajLain = idTransaksiCajLain;
     }

     public long getIdTransaksiCajNotis() {
     return idTransaksiCajNotis;
     }

     public void setIdTransaksiCajNotis(long idTransaksiCajNotis) {
     this.idTransaksiCajNotis = idTransaksiCajNotis;
     }

     public long getIdTransaksiCajPelepasan() {
     return idTransaksiCajPelepasan;
     }

     public void setIdTransaksiCajPelepasan(long idTransaksiCajPelepasan) {
     this.idTransaksiCajPelepasan = idTransaksiCajPelepasan;
     }

     public long getIdTransaksiCajPengecualian() {
     return idTransaksiCajPengecualian;
     }

     public void setIdTransaksiCajPengecualian(long idTransaksiCajPengecualian) {
     this.idTransaksiCajPengecualian = idTransaksiCajPengecualian;
     }
     */
    /**
     * Get the possible values for nilai1 selection which are retrived from
     * KodKadarBayaran for this KodUrusan
     *
     * @return
     */
    public List<String> getNilai1Selection() {
        if (kodUrusan == null || kodUrusan.length() == 0) {
            return null;
        }
        LOG.info("TEST KATEGORI>>>>>>>");
        Session s = sessionProvider.get();
        return s.createQuery("select distinct kkb.kategori from KodKadarBayaran kkb where kkb.kodUrusan.id = :kodUrusan order by kkb.kategori desc").setString("kodUrusan", kodUrusan).list();
    }

    @Override
    public boolean hasToInitiateWorkflow() {
        return true;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public Dokumen getCetakan() {
        return akuanPenerimaan;
    }

    public String getSebabKecuali() {
        return sebabKecuali;
    }

    public void setSebabKecuali(String sebabKecuali) {
        this.sebabKecuali = sebabKecuali;
    }

    /**
     * List of transaction before being added/subtracted with others.
     *
     * @return
     */
    public List<TransaksiValue> getSenaraiTransaksiPra() {
        // prepare list of hakmilik
        ArrayList<String> listBersiri = new ArrayList<String>();
        permohonanUtil.updateIdHakmilikFromBersiri(listBersiri, getIdHakmilikSiriDari(), idHakmilikSiriKe);
        String senaraiHakmilik = permohonanUtil.getSenaraiHakmilikUrusan(hakmilikPermohonan, listBersiri);

        return getSenaraiTransaksiPra0(senaraiHakmilik, listBersiri);
    }

    private List<TransaksiValue> getSenaraiTransaksiPra0(String senaraiHakmilik, ArrayList<String> listBersiri) {
        ArrayList<TransaksiValue> senaraiTransaksi = new ArrayList<TransaksiValue>();

        KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
        if (ku.getKodTransaksi() != null) {
            ArrayList<HakmilikPermohonan> listHp = new ArrayList<HakmilikPermohonan>();
            if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
                for (String h : hakmilikPermohonan) {
                    if (h == null || h.trim().length() == 0) {
                        continue;  // filter out null entries
                    }
                    HakmilikPermohonan hp = new HakmilikPermohonan();
                    Hakmilik hm = new Hakmilik();
                    hm.setIdHakmilik(h);
                    hp.setHakmilik(hm);
                    listHp.add(hp);
                }
            }
//            if (ku.getKod().equals("PBS")) {
//                LOG.debug("--Kod Urusan--PBS--");
//                List<TransaksiValue> listTransaksi = kaunterService.calculateFeePBS(ku, amaun1, amaun2, tarikh1, nilai1, nilai1Senarai,
//                        teks1, listHp, listBersiri);
//                if ("05".equals(conf.getProperty("kodNegeri"))) {
//                    amaun1 = new BigDecimal(1);
//                }
//                for (int x = 0; x < listTransaksi.size(); x++) {
//                    TransaksiValue t = listTransaksi.get(x);
//                    if (t.senaraiHakmilik == null) {
//                        t.senaraiHakmilik = senaraiHakmilik;
//                    }
//                    t.utkUrusan = this;
//                    jumlahCaj = jumlahCaj.add(t.amaun);
//                }
//                senaraiTransaksi.addAll(listTransaksi);
//            } else 
            if (ku.getKod().equals("PBTS")) {
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    amaun1 = new BigDecimal(1);
                    amaun3 = new BigDecimal(1);
                }
                LOG.debug("--Kod Urusan--PBTS--");
                List<TransaksiValue> listTransaksi = kaunterService.calculateFeePBTS(ku, amaun1, amaun2, amaun3, tarikh1, nilai1, nilai1Senarai,
                        teks1, listHp, listBersiri);
                for (int x = 0; x < listTransaksi.size(); x++) {
                    TransaksiValue t = listTransaksi.get(x);
                    if (t.senaraiHakmilik == null) {
                        t.senaraiHakmilik = senaraiHakmilik;
                    }
                    t.utkUrusan = this;
                    jumlahCaj = jumlahCaj.add(t.amaun);
                }
                senaraiTransaksi.addAll(listTransaksi);
            } else if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (ku.getKod().equals("PBBS") || ku.getKod().equals("PBBD") || ku.getKod().equals("PBS")) {
                    if (amaun1 != null && amaun1 != BigDecimal.ZERO) {
                        LOG.debug("--amaun1--:" + amaun1);
                        List<TransaksiValue> listTransaksi = kaunterService.calculateFeeMlkKos(ku, amaun1, tarikh1, nilai1, nilai1Senarai,
                                teks1, listHp, listBersiri);
                        for (int x = 0; x < listTransaksi.size(); x++) {
                            TransaksiValue t = listTransaksi.get(x);
                            if (t.senaraiHakmilik == null) {
                                t.senaraiHakmilik = senaraiHakmilik;
                            }
                            t.utkUrusan = this;
                            jumlahCaj = jumlahCaj.add(t.amaun);
                        }
                        senaraiTransaksi.addAll(listTransaksi);
                    } else if (amaun2 != null && amaun2 != BigDecimal.ZERO) {
                        LOG.debug("--amaun2--:" + amaun2);
                        List<TransaksiValue> listTransaksi = kaunterService.calculateFeeMlk(ku, amaun2, tarikh1, nilai1, nilai1Senarai,
                                teks1, listHp, listBersiri, katgTanah);
                        for (int x = 0; x < listTransaksi.size(); x++) {
                            TransaksiValue t = listTransaksi.get(x);
                            if (t.senaraiHakmilik == null) {
                                t.senaraiHakmilik = senaraiHakmilik;
                            }
                            t.utkUrusan = this;
                            jumlahCaj = jumlahCaj.add(t.amaun);
                        }
                        senaraiTransaksi.addAll(listTransaksi);
                    }
                } else if (ku.getKod().equals("SEK4")) {
                    if (StringUtils.isNotEmpty(kategori)) {
                        LOG.debug("--amaun1--:" + amaun1);
                        List<TransaksiValue> listTransaksi = kaunterService.calculateFeeACQ(ku, nilaiJPPH, kategori, jumlahTDK, listHp, listBersiri);
                        for (int x = 0; x < listTransaksi.size(); x++) {
                            TransaksiValue t = listTransaksi.get(x);
                            if (t.senaraiHakmilik == null) {
                                t.senaraiHakmilik = senaraiHakmilik;
                            }
                            t.utkUrusan = this;
                            jumlahCaj = jumlahCaj.add(t.amaun);
                        }
                        senaraiTransaksi.addAll(listTransaksi);
                    }
                } else {
                    if (ku.getKod().equals("PPPP")) {
                        BigDecimal cajBaru = BigDecimal.ZERO;
                        if (amaun1 != null && amaun1.compareTo(BigDecimal.ZERO) != 0) {
                            LOG.debug("--amaun1--:" + amaun1);
                            jumlahCaj = amaun1;
                            cajBaru = amaun1;
                        } else if (amaun2 != null && amaun2 != BigDecimal.ZERO) {
                            LOG.debug("--amaun2--:" + amaun2);
                            jumlahCaj = amaun2;
                            cajBaru = amaun2;
                        }
                        TransaksiValue t = new TransaksiValue();
                        t.setKodUrusan(ku.getKod());
                        t.setNamaUrusan(ku.getNama());
                        t.utkUrusan = this;
                        t.senaraiHakmilik = senaraiHakmilik;
                        t.amaun = cajBaru;
                        t.kodTransaksi = ku.getKodTransaksi().getKod();
                        senaraiTransaksi.add(t);
                    } else {
                        List<TransaksiValue> listTransaksi = kaunterService.calculateFee(ku, amaun1, tarikh1, nilai1, nilai1Senarai,
                                teks1, listHp, listBersiri);
                        for (int x = 0; x < listTransaksi.size(); x++) {
                            TransaksiValue t = listTransaksi.get(x);
                            if (t.senaraiHakmilik == null) {
                                t.senaraiHakmilik = senaraiHakmilik;
                            }
                            t.utkUrusan = this;
                            jumlahCaj = jumlahCaj.add(t.amaun);
                        }
                        senaraiTransaksi.addAll(listTransaksi);
                    }
                }
            } else {
                List<TransaksiValue> listTransaksi = kaunterService.calculateFee(ku, amaun1, tarikh1, nilai1, nilai1Senarai,
                        teks1, listHp, listBersiri);
                for (int x = 0; x < listTransaksi.size(); x++) {
                    TransaksiValue t = listTransaksi.get(x);
                    if (t.senaraiHakmilik == null) {
                        t.senaraiHakmilik = senaraiHakmilik;
                        t.sebabLain = sebabLain;
                        t.pegawaiLain = pegawaiLain;
                    }
                    t.utkUrusan = this;
                    jumlahCaj = jumlahCaj.add(t.amaun);
                }
                senaraiTransaksi.addAll(listTransaksi);
            }

        } else {
            // create zero transaction
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(ku.getKod());
            t.setNamaUrusan(ku.getNama());
            t.utkUrusan = this;
            t.senaraiHakmilik = senaraiHakmilik;
            t.amaun = BigDecimal.ZERO;
            senaraiTransaksi.add(t);
        }

        // TODO t.kodTransaksi
        ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
        senaraiKodUrusan.add(kodUrusan);
        // caj based on documents submittted
        List<UrusanDokumen> lud = kaunterService.getUrusanDokumen(senaraiKodUrusan);
        for (DokumenValue dv : senaraiDokumenSerahan) {
            if (dv != null && dv.kodDokumen != null) {
                for (int k = 0; k < lud.size(); k++) {
                    if (lud.get(k).getKodDokumen().getKod().equals(dv.kodDokumen)) {
                        BigDecimal caj = lud.get(k).getCaj();
                        if (caj != null && (caj.compareTo(BigDecimal.ZERO)) > 0) {
                            // create a new transaction
                            TransaksiValue dokumenT = new TransaksiValue();
                            dokumenT.kodUrusan = kodUrusan;
                            KodDokumen kodDok = kodDokumenDAO.findById(dv.kodDokumen);
                            dokumenT.namaUrusan = kodDok != null ? kodDok.getNama() : dv.kodDokumen;
                            // t3.senaraiHakmilik = t.senaraiHakmilik;
                            dokumenT.setKodDokumen(dv.kodDokumen);
//                            dokumenT.kuantiti = dv.bil;   modified by hakim (Pelupusan)
                            if (ku.getJabatan().getKod().equals(KOD_JABATAN_PENDAFTARAN)) {
                                dokumenT.kuantiti = dv.bil;
                            } else {
                                dokumenT.kuantiti = 1;
                            }
                            dokumenT.amaun = caj.multiply(new BigDecimal(dv.bil));
                            dokumenT.utkUrusan = this;
                            dokumenT.kodTransaksi = lud.get(k).getKodTransaksi().getKod();
                            senaraiTransaksi.add(dokumenT);
                        }
                        break;
                    }
                }
            }
        }

        // caj for Surat Amanah Baru
        if (suratSABKuantiti > 0) {
            TransaksiValue t = permohonanUtil.calculateFeeForDocuments(this, "SAB", suratSABKuantiti);
            String s[] = senaraiHakmilik.split(",");
            t.setAmaun(t.getAmaun().multiply(new BigDecimal(s.length)));
            senaraiTransaksi.add(t);
            jumlahCaj = jumlahCaj.add(t.amaun);
        }

        // caj for Surat Amanah Daftar
        int sizeSurat = permohonanUtil.getCountValidSuratSWD(suratSAD); // process valid only
        if (sizeSurat > 0) {
            TransaksiValue t = permohonanUtil.calculateFeeForDocuments(this, "SAD", sizeSurat);

            senaraiTransaksi.add(t);
            jumlahCaj = jumlahCaj.add(t.amaun);
        }

        // caj for Surat Kebenaran Baru
        if (suratSBBKuantiti > 0) {
            TransaksiValue t = permohonanUtil.calculateFeeForDocuments(this, "SBB", suratSBBKuantiti);
            senaraiTransaksi.add(t);
            jumlahCaj = jumlahCaj.add(t.amaun);
        }

        // caj for Surat Kebenaran Daftar
        sizeSurat = permohonanUtil.getCountValidSuratSWD(suratSBD); // process valid only
        if (sizeSurat > 0) {
            TransaksiValue t = permohonanUtil.calculateFeeForDocuments(this, "SBD", sizeSurat);
            senaraiTransaksi.add(t);
            jumlahCaj = jumlahCaj.add(t.amaun);
        }

        // caj for Surat Kuasa Wakil Baru
        if (suratSWBKuantiti > 0) {
            TransaksiValue t4 = permohonanUtil.calculateFeeForDocuments(this, "SWB", suratSWBKuantiti);
            String s[] = senaraiHakmilik.split(",");
            t4.setAmaun(t4.getAmaun().multiply(new BigDecimal(s.length)));
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }

        // caj for Surat Kuasa Wakil Daftar
        sizeSurat = permohonanUtil.getCountValidSuratSWD(suratSWD); // process valid only
        if (sizeSurat > 0) {
            TransaksiValue t5 = permohonanUtil.calculateFeeForDocuments(this, "SWD", sizeSurat);
            senaraiTransaksi.add(t5);
            jumlahCaj = jumlahCaj.add(t5.amaun);
        }

        //Added by Aizuddin for SM
        // caj for SM
        if (!"SMK".equals(kodUrusan) && !"SMBT".equals(kodUrusan)) {
            if (SMKuantiti > 0) {
                TransaksiValue t6 = permohonanUtil.calculateFeeForDocuments(this, "SMD", SMKuantiti);
                senaraiTransaksi.add(t6);
                jumlahCaj = jumlahCaj.add(t6.amaun);
            }

            // caj for SM Batal
            sizeSurat = permohonanUtil.getCountValidSuratSWD(smPerserahan); // process valid only
            if (sizeSurat > 0) {
                TransaksiValue t7 = permohonanUtil.calculateFeeForDocuments(this, "SMD", sizeSurat);
                senaraiTransaksi.add(t7);
                jumlahCaj = jumlahCaj.add(t7.amaun);
            }
        }
        LOG.debug("jumlahCaj pra=" + jumlahCaj);

        return senaraiTransaksi;
    }

    public List<TransaksiValue> getSenaraiTransaksi() {
        // prepare list of hakmilik
        ArrayList<String> listBersiri = new ArrayList<String>();
        permohonanUtil.updateIdHakmilikFromBersiri(listBersiri, getIdHakmilikSiriDari(), idHakmilikSiriKe);
        String senaraiHakmilik = permohonanUtil.getSenaraiHakmilikUrusan(hakmilikPermohonan, listBersiri);

        List<TransaksiValue> senaraiTransaksi = getSenaraiTransaksiPra0(senaraiHakmilik, listBersiri);

        // calculation for pelepasan/pengecualian/notis/bayaran fail/lain2
        final BigDecimal NEGATION = new BigDecimal(-1);
        if (cajPelepasan != null && BigDecimal.ZERO.compareTo(cajPelepasan) < 0) {
            // create a new transaction
            TransaksiValue t4 = new TransaksiValue();
            t4.kodUrusan = kodUrusan;
            if (this.pegawaiKecuali != null) {
                t4.namaUrusan = "Pelepasan" + " ( " + this.sebabKecuali + " - " + this.pegawaiKecuali + " )";
            } else {
                t4.namaUrusan = "Pelepasan" + " ( " + this.sebabKecuali + ")";
            }
            t4.senaraiHakmilik = senaraiHakmilik;
            t4.amaun = cajPelepasan.multiply(NEGATION);
            t4.utkUrusan = this;
            // TODO get proper kods for this!
            t4.kodTransaksi = senaraiTransaksi.get(0).kodTransaksi;
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }
        if (cajPengecualian != null && BigDecimal.ZERO.compareTo(cajPengecualian) < 0) {
            // create a new transaction
            TransaksiValue t4 = new TransaksiValue();
            t4.kodUrusan = kodUrusan;
            if (this.pegawaiKecuali != null) {
                t4.namaUrusan = "Pengecualian" + " ( " + this.sebabKecuali + " - " + this.pegawaiKecuali + " )";
            } else {
                t4.namaUrusan = "Pengecualian" + " ( " + this.sebabKecuali + ")";
            }
            t4.senaraiHakmilik = senaraiHakmilik;
            t4.amaun = cajPengecualian.multiply(NEGATION);
            t4.utkUrusan = this;
            // TODO get proper kods for this!
            t4.kodTransaksi = senaraiTransaksi.get(0).kodTransaksi;
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }
        if (cajNotis != null && BigDecimal.ZERO.compareTo(cajNotis) < 0) {
            // create a new transaction
            TransaksiValue t4 = new TransaksiValue();
            t4.kodUrusan = kodUrusan;
            t4.namaUrusan = "Notis";
            t4.senaraiHakmilik = senaraiHakmilik;
            t4.amaun = cajNotis;
            t4.utkUrusan = this;
            // TODO get proper kods for this!
            t4.kodTransaksi = senaraiTransaksi.get(0).kodTransaksi;
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }
        if (cajFail != null && BigDecimal.ZERO.compareTo(cajFail) < 0) {
            // create a new transaction
            TransaksiValue t4 = new TransaksiValue();
            t4.kodUrusan = kodUrusan;
            t4.namaUrusan = "Bayaran Fail";
            t4.senaraiHakmilik = senaraiHakmilik;
            t4.amaun = cajFail;
            t4.utkUrusan = this;
            // TODO get proper kods for this!
            t4.kodTransaksi = senaraiTransaksi.get(0).kodTransaksi;
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }
        if (cajLain != null && BigDecimal.ZERO.compareTo(cajLain) < 0) {
            // create a new transaction
            TransaksiValue t4 = new TransaksiValue();
            t4.kodUrusan = this.kodUrusan;
            if (this.pegawaiLain != null) {
                t4.namaUrusan = "Bayaran-bayaran Lain" + " ( " + this.sebabLain + " - " + this.pegawaiLain + " )";
            } else {
                t4.namaUrusan = "Bayaran-bayaran Lain" + " ( " + this.sebabLain + " )";
            }
            t4.senaraiHakmilik = senaraiHakmilik;
            t4.amaun = this.cajLain;
            t4.utkUrusan = this;
            // TODO get proper kods for this!
            t4.kodTransaksi = senaraiTransaksi.get(0).kodTransaksi;
            senaraiTransaksi.add(t4);
            jumlahCaj = jumlahCaj.add(t4.amaun);
        }

        jumlahCaj.setScale(2);
        if (debug) {
            LOG.debug("jumlahCaj=" + jumlahCaj.toPlainString());
        }

        return senaraiTransaksi;
    }

    /*
     * Save all urusan in this kumpulan
     */
    @Override
    public List<UrusanPermohonan> doSave(String kodNegeri, Pengguna pengguna, Dokumen resit,
            String idKumpulan, int seq,
            // PENYERAH
            String idPenyerah, KodPenyerah penyerahKod,
            String penyerahNoPengenalan, KodJenisPengenalan penyerahJenisPengenalan,
            String penyerahNama, String penyerahAlamat1,
            String penyerahAlamat2, String penyerahAlamat3, String penyerahAlamat4,
            String penyerahPoskod, KodNegeri penyerahNegeri,
            String penyerahEmail, String penyerahNoTelefon,
            String namaPenyampai, String noPengenalanPenyampai,
            String noTelPenyampai, InfoAudit ia) {
        boolean cSBB = false;
        boolean cSBL = false;
        boolean dSBB = false;
        boolean dSBL = false;
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);

        //tukar ganti
        List<Permohonan> senaraiPermohonanTukarGanti = new ArrayList<Permohonan>();

        // open folder for storing submitted documents
        // only one folder for grouped submissions
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk("-");
        fd.setInfoAudit(ia);

        // save the content
        ArrayList<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        fd.setSenaraiKandungan(akf);
        List<DokumenValue> senaraiDokumenSerahan = getSenaraiDokumenSerahan();
        if (senaraiDokumenSerahan != null) {
            for (DokumenValue dv : senaraiDokumenSerahan) {
                if (dv == null) {
                    continue;
                }
                KodDokumen kd = kodDokumenDAO.findById(dv.kodDokumen);
                // saving noRujukan by comma delimited
                String[] listTajuk = null;
                if (dv.noRujukan != null && dv.noRujukan.length() > 0) {
                    listTajuk = dv.noRujukan.split(",");
                }

                // save for every copy
//                for (int j = 0; j < dv.getBil(); j++) {  modified by hakim (Pelupusan)
                for (int j = 0; j < 1; j++) {
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kd);
                    d.setInfoAudit(ia);
                    if (listTajuk != null && j < listTajuk.length && listTajuk[j] != null) {
                        String noRujukan = listTajuk[j].trim();
                        // d.setTajuk(kd.getKod() + " " + noRujukan);
                        d.setTajuk(kd.getNama() + " " + noRujukan);
                        d.setNoRujukan(noRujukan);
                    } else {
                        d.setTajuk(kd.getNama());
                    }
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);

                    /*
                     // generate No.Rujukan for SWB, SAB (todo), SBB (todo)
                     if (kd != null && "SWB".equals(kd.getKod())){
                     String noSWB = idWakilGenerator.generate(
                     "04".equals(conf.getProperty("kodNegeri")) ? "melaka" : null,
                     pengguna.getKodCawangan(), null);
                     d.setNoRujukan(noSWB);
                     }
                     */
                    if (d.getKodDokumen().getKod().equals("SBB")) {
                        cSBB = true;
                        if (cSBL) {
                            dSBL = true;
                            continue;
                        }
                    }
                    if (d.getKodDokumen().getKod().equals("SBL")) {
                        cSBL = true;
                        if (cSBB) {
                            dSBB = true;
                            continue;
                        }
                    }

                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    akf.add(k);
                }
            }
        }
        // additional documents
        List<DokumenValue> senaraiDokumenTamb = getSenaraiDokumenTamb();
        if (debug) {
            LOG.debug("senaraiDokumenTamb != null?" + (senaraiDokumenTamb != null));
        }
        if (senaraiDokumenTamb != null && senaraiDokumenTamb.size() > 0) {
            for (DokumenValue dv : senaraiDokumenTamb) {
                if (dv == null || dv.kodDokumen == null) {
                    continue;
                }

                Dokumen d = new Dokumen();
                KodDokumen kd = kodDokumenDAO.findById(dv.getKodDokumen());

                String c = dv.catatan;
                if ((kd != null && !kd.getKod().equals("0"))
                        || (c != null && c.trim().length() > 0)) {
                    if (kd != null && kd.getKod().equals("0")) { // the type not set
                        d.setKodDokumen(null);
                        d.setTajuk("KIV");
                    } else {
                        if (kd == null) {
                            d.setTajuk("KIV");
                        } else {
                            d.setKodDokumen(kd);
                            d.setTajuk(kd.getNama());
                        }
                    }
                    d.setInfoAudit(ia);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder f = new KandunganFolder();
                    f.setCatatan(dv.getCatatan());
                    f.setDokumen(d);
                    f.setFolder(fd);
                    f.setInfoAudit(ia);
                    akf.add(f);
                }
            }
        }

        // attach resit to folder
        if (resit != null) {
            KandunganFolder kf2 = new KandunganFolder();
            kf2.setFolder(fd);
            kf2.setDokumen(resit);
            kf2.setInfoAudit(ia);
            akf.add(kf2);
        }
        folderDokumenDAO.save(fd);

        ArrayList<Hakmilik> listHakmilik = getSenaraiHakmilik();
        // adding Permohonan
        StringBuilder tajukFolder = new StringBuilder();
        // find the jabatan for urusan
        KodUrusan kod = kodUrusanDAO.findById(kodUrusan);
        if (kod == null) {
            LOG.error("Urusan \"" + kodUrusan + "\" tidak dijumpai!");
            throw new RuntimeException("Urusan \"" + kodUrusan + "\" tidak dijumpai!");
        }
        if (kod.getAktif() != 'Y') {
            LOG.error("Urusan \"" + kodUrusan + "\" tidak aktif!");
            throw new RuntimeException("Urusan \"" + kodUrusan + "\" tidak aktif!");
        }

        boolean perluJelasCukai = (kod.getPerluJelasCukai() == 'Y');
        if (perluJelasCukai) {
            for (Hakmilik hm : listHakmilik) {
                Akaun ac = hm.getAkaunCukai();
                if (ac == null || ac.getBaki().compareTo(BigDecimal.ZERO) > 0) {
                    throw new RuntimeException("ID Hakmilik " + hm.getIdHakmilik()
                            + " masih belum menjelaskan Cukai. Urusan \""
                            + kod.getNama()
                            + "\" memerlukan cukai dijelaskan.");
                }
            }
        }

        senaraiPermohonan = new ArrayList<UrusanPermohonan>();
        senaraiPermohonan.add(this);

        // PROCESSING DOKUMEN SW/SB/SA
        // surat kuasa wakil
        if (kod.getJabatan().getKod().equals(KOD_JABATAN_PENDAFTARAN)) {

            //proses tukar ganti
            if (seq == 1) {
                ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);

                senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, listHakmilik);

                if (!senaraiPermohonanTukarGanti.isEmpty()) {
                    for (Permohonan p : senaraiPermohonanTukarGanti) {
                        UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                        uvs.kodJabatan = "16";
                        uvs.kodUrusan = p.getKodUrusan().getKod();
                        uvs.noRujukan = p.getIdPermohonan();
                        uvs.permohonan = p;
                        senaraiPermohonan.add(uvs);
                        if (catatan.length() > 0) {
                            catatan.append(",");
                        }
                        catatan.append(p.getIdPermohonan());
                    }

                    List<Hakmilik> hms = tukarGanti.getSenaraiHm();
                    if (hms != null && !hms.isEmpty()) {
                        //utk urusan HMSC, hakmilik baru akan dijana
                        //dan digunakan utk urusan yg lain
                        listHakmilik = new ArrayList(hms);
                    }
                }
            }

            LOG.debug("suratSWBKuantiti=" + suratSWBKuantiti);
            if (suratSWBKuantiti > 0) {
                KodUrusan ku = kodUrusanDAO.findById("SW");
                KodDokumen kdSW = kodDokumenDAO.findById("SWB");

                for (int i = 0; i < suratSWBKuantiti; i++) {
                    // generate permohonan Surat Kuasa Wakil Baru
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SW";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);

                    // add to folder
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSW);
                    d.setInfoAudit(ia);
                    d.setTajuk("SW " + uvs.noRujukan);
                    d.setNoRujukan(uvs.noRujukan);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            if (suratSWD != null) {
                KodDokumen kdSW = kodDokumenDAO.findById("SWD");

                for (String surat : suratSWD) {
                    if (surat == null || surat.trim().length() == 0) {
                        continue;
                    }

                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSW);
                    d.setInfoAudit(ia);
                    d.setTajuk("SW " + surat);
                    d.setNoRujukan(surat);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            // surat amanah
            if (suratSABKuantiti > 0) {
                KodUrusan ku = kodUrusanDAO.findById("SA");
                KodDokumen kdSA = kodDokumenDAO.findById("SAB");

                for (int i = 0; i < suratSABKuantiti; i++) {
                    // generate permohonan Surat Kuasa Wakil Baru
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SA";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);

                    // add to folder
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSA);
                    d.setInfoAudit(ia);
                    d.setTajuk("SA " + uvs.noRujukan);
                    d.setNoRujukan(uvs.noRujukan);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            if (suratSAD != null) {
                KodDokumen kdSA = kodDokumenDAO.findById("SAD");

                for (String surat : suratSAD) {
                    if (surat == null || surat.trim().length() == 0) {
                        continue;
                    }

                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSA);
                    d.setInfoAudit(ia);
                    d.setTajuk("SA " + surat);
                    d.setNoRujukan(surat);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            // surat kebenaran
            if (suratSBBKuantiti > 0) {
                KodUrusan ku = kodUrusanDAO.findById("SB");
                KodDokumen kdSB = kodDokumenDAO.findById("SBB");

                for (int i = 0; i < suratSBBKuantiti; i++) {
                    // generate permohonan Surat Kuasa Wakil Baru
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SB";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);

                    // add to folder
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSB);
                    d.setInfoAudit(ia);
                    d.setTajuk("SB " + uvs.noRujukan);
                    d.setNoRujukan(uvs.noRujukan);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            if (suratSBD != null) {
                KodDokumen kdSB = kodDokumenDAO.findById("SBD");

                for (String surat : suratSBD) {
                    if (surat == null || surat.trim().length() == 0) {
                        continue;
                    }

                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSB);
                    d.setInfoAudit(ia);
                    d.setTajuk("SB " + surat);
                    d.setNoRujukan(surat);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }

            //SM Added by Aizuddin
            // surat kebenaran
            if (SMKuantiti > 0) {
                KodUrusan ku = kodUrusanDAO.findById("SMD");
                KodDokumen kdSB = kodDokumenDAO.findById("SMB");

                for (int i = 0; i < SMKuantiti; i++) {
                    // generate permohonan Surat Kuasa Wakil Baru
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SMD";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);

                    // add to folder
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSB);
                    d.setInfoAudit(ia);
                    d.setTajuk("SM " + uvs.noRujukan);
                    d.setNoRujukan(uvs.noRujukan);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            if (smPerserahan != null) {
                KodDokumen kdSB = kodDokumenDAO.findById("SMB");

                for (String surat : smPerserahan) {
                    if (surat == null || surat.trim().length() == 0) {
                        continue;
                    }

                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kdSB);
                    d.setInfoAudit(ia);
                    d.setTajuk("SM " + surat);
                    d.setNoRujukan(surat);
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    KandunganFolder k = new KandunganFolder();
                    k.setFolder(fd);
                    k.setDokumen(d);
                    k.setInfoAudit(ia);
                    fd.getSenaraiKandungan().add(k);
                }
            }
            //End added by Aizuddin
            if (kod.getKod().equals("SB")) {
                senaraiPermohonan.clear();

                ArrayList<Dokumen> listDok = new ArrayList<Dokumen>();

                KandunganFolder fd5 = new KandunganFolder();
                KodUrusan ku = kodUrusanDAO.findById("SB");
                int bilSBB = 0;
                int bilSBL = 0;
                for (DokumenValue dv : senaraiDokumenSerahan) {

                    if (dv == null) {
                        continue;
                    }
                    for (int v = 0; v < dv.getBil(); v++) {
                        KodDokumen kd = kodDokumenDAO.findById(dv.kodDokumen);
                        Dokumen d = new Dokumen();
                        d.setKodDokumen(kd);
                        d.setInfoAudit(ia);
                        d.setTajuk(kd.getNama());
                        d.setNoVersi("1.0");
                        d.setKlasifikasi(klasifikasiAm);
                        if (d.getKodDokumen().getKod().equals("SBL")) {
                            bilSBL = bilSBL + 1;
                            continue;
                        }
                        if (d.getKodDokumen().getKod().equals("SBB")) {
                            bilSBB = bilSBB + 1;
                            continue;
                        }

                        dokumenDAO.save(d);
                        listDok.add(d);
                    }

                }
                for (int j = 0; j < bilSBL; j++) {
                    KodDokumen kd = kodDokumenDAO.findById("SBL");
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kd);
                    d.setInfoAudit(ia);
                    d.setTajuk(kd.getNama());
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    FolderDokumen fd2 = new FolderDokumen();
                    fd2.setTajuk("-");
                    fd2.setInfoAudit(ia);

                    // save the content
                    ArrayList<KandunganFolder> akf2 = new ArrayList<KandunganFolder>();
                    KandunganFolder kf22 = new KandunganFolder();
                    kf22.setFolder(fd2);
                    kf22.setDokumen(d);
                    kf22.setInfoAudit(ia);
                    akf2.add(kf22);

                    for (Dokumen dok : listDok) {
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(fd2);
                        kf.setDokumen(dok);
                        kf.setInfoAudit(ia);
                        akf2.add(kf);
                    }
                    if (resit != null) {
                        KandunganFolder kf2 = new KandunganFolder();
                        kf2.setFolder(fd2);
                        kf2.setDokumen(resit);
                        kf2.setInfoAudit(ia);
                        akf2.add(kf2);
                    }
                    fd2.setSenaraiKandungan(akf2);
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SB";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd2, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd2, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);
                }
                for (int j = 0; j < bilSBB; j++) {
                    KodDokumen kd = kodDokumenDAO.findById("SBB");
                    Dokumen d = new Dokumen();
                    d.setKodDokumen(kd);
                    d.setInfoAudit(ia);
                    d.setTajuk(kd.getNama());
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    dokumenDAO.save(d);
                    FolderDokumen fd2 = new FolderDokumen();
                    fd2.setTajuk("-");
                    fd2.setInfoAudit(ia);

                    // save the content
                    ArrayList<KandunganFolder> akf2 = new ArrayList<KandunganFolder>();
                    KandunganFolder kf22 = new KandunganFolder();
                    kf22.setFolder(fd2);
                    kf22.setDokumen(d);
                    kf22.setInfoAudit(ia);
                    akf2.add(kf22);
                    fd2.setSenaraiKandungan(akf2);
                    for (Dokumen dok : listDok) {
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(fd2);
                        kf.setDokumen(dok);
                        kf.setInfoAudit(ia);
                        akf2.add(kf);
                    }
                    if (resit != null) {
                        KandunganFolder kf2 = new KandunganFolder();
                        kf2.setFolder(fd2);
                        kf2.setDokumen(resit);
                        kf2.setInfoAudit(ia);
                        akf2.add(kf2);
                    }
                    UrusanPermohonan uvs = etanahContextListener.newInstance(UrusanPermohonan.class);
                    uvs.kodJabatan = "16";
                    uvs.kodUrusan = "SB";
                    Permohonan p = savePermohonan(kodNegeri, pengguna,
                            ku, uvs, idKumpulan, 0, fd2, null, listHakmilik,
                            idPenyerah, penyerahKod,
                            penyerahNoPengenalan, penyerahJenisPengenalan,
                            penyerahNama, penyerahAlamat1,
                            penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                            penyerahPoskod, penyerahNegeri,
                            penyerahEmail, penyerahNoTelefon,
                            namaPenyampai, noPengenalanPenyampai,
                            noTelPenyampai, ia);
                    uvs.noRujukan = p.getIdPermohonan();
                    uvs.permohonan = p;
                    uvs.akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd2, p.getIdPermohonan(), ia);
                    senaraiPermohonan.add(uvs);
                }

                return senaraiPermohonan;

            }

        }
        // PROCESSING DOKUMEN SW/SB/SA END

        /**
         * Permohonan lama ialah permohonan sebelum yang ada kaitan dgn
         * permohonan sekarang.
         */
        String idPermohonanLama = idPermohonanSebelum;
        Permohonan permohonanLama = null;
        if (idPermohonanLama != null) {
            permohonanLama = permohonanDAO.findById(idPermohonanLama);
            if (permohonanLama == null) {
                LOG.error("ID Permohonan " + idPermohonanLama + " yang diberikan tidak wujud!");
                throw new RuntimeException("ID Permohonan/Perserahan " + idPermohonanLama
                        + " yang diberikan tidak wujud!");
            }
        }

        if (debug) {
            LOG.debug("adding urusan:" + kodUrusan + " for jabatan " + kod.getJabatanNama());
        }

        Permohonan p = savePermohonan(kodNegeri, pengguna, kod,
                this, idKumpulan, seq, fd, permohonanLama,
                listHakmilik,
                idPenyerah, penyerahKod,
                penyerahNoPengenalan, penyerahJenisPengenalan,
                penyerahNama, penyerahAlamat1,
                penyerahAlamat2, penyerahAlamat3, penyerahAlamat4,
                penyerahPoskod, penyerahNegeri,
                penyerahEmail, penyerahNoTelefon, namaPenyampai, noPengenalanPenyampai,
                noTelPenyampai, ia);

        noRujukan = p.getIdPermohonan();
        permohonan = p;

        if (!senaraiPermohonanTukarGanti.isEmpty()) {
            for (Permohonan p2 : senaraiPermohonanTukarGanti) {
                if (StringUtils.isEmpty(p2.getUlasan())) {
                    p2.setUlasan(p.getIdPermohonan());
                    permohonanDAO.save(p2);
                }
            }
        }

        /**
         * Permohon Pengambilan
         */
        if (kod.getJabatan().getKod().equals("9")) {
            if (kod.getKod().equals("SEK4") || kod.getKod().equals("831")) {
                BigDecimal deposit = new BigDecimal(BigInteger.ZERO);
                BigDecimal feePermohonan = new BigDecimal(BigInteger.ZERO);

                PermohonanPengambilan mohonAmbil = pengambilanAPTService.findPermohonanPengambilanByIdMH(p.getIdPermohonan());
                if (mohonAmbil == null) {
                    mohonAmbil = new PermohonanPengambilan();
                }

                mohonAmbil.setPermohonan(p);
                mohonAmbil.setInfoAudit(p.getInfoAudit());
                mohonAmbil.setKatPengambilan(kategori);
                mohonAmbil.setNilaiJPPH(nilaiJPPH);
                mohonAmbil.setDeposit(deposit);
                mohonAmbil.setFeePermohonan(feePermohonan);
                pengambilanAPTService.saveOrUpdatePermohonanPengambilan(mohonAmbil);
            }
        }
        
        
            if (kod.getKod().equals("BACT")) {

            PermohonanAnsuran ansuran = new PermohonanAnsuran();
            ansuran.setInfoAudit(p.getInfoAudit());
            ansuran.setIdPermohonan(p.getIdPermohonan());
            Hakmilik hak = hakmilikDAO.findById(hakmilikPermohonan.get(0));
            ansuran = akaunService.setAnsuran(teks1, nilai1, hak, ansuran);
            akaunService.savePermohonanAnsuran(ansuran);

            if (ansuran.getKatgLulus().equals("PTG")) {
                p.setIdWorkflow(ptgflow);
            } else {
                p.setIdWorkflow(ptdflow);
            }

            permohonanDAO.save(p);
        }
        

        // handling special urusan
        // TODO move to UrusanPostProcess
        PermohonanUrusan pu = null;
        String[] infos = permohonanUtil.URUSAN_INFO.get(kodUrusan);
        if (infos != null && infos[0] != null && "Amaun Transaksi (RM)".equalsIgnoreCase(infos[0])) {
            pu = new PermohonanUrusan();
            pu.setCawangan(pengguna.getKodCawangan());
            pu.setPermohonan(p);
            pu.setInfoAudit(ia);
            pu.setPerjanjianAmaun(amaun1);
        }
        if (infos != null && infos[1] != null && "Tarikh Penyaksian".equalsIgnoreCase(infos[1])) {
            if (pu == null) {
                pu = new PermohonanUrusan();
                pu.setCawangan(pengguna.getKodCawangan());
                pu.setPermohonan(p);
                pu.setInfoAudit(ia);
            }
            pu.setTarikhSaksi(tarikh1);
        }
        if (pu != null) {
            permohonanUrusanDAO.save(pu);
        }

        if (tajukFolder.length() > 0) {
            tajukFolder.append(",");
        }
        tajukFolder.append(p.getIdPermohonan());

        // surat akuan penerimaan
        akuanPenerimaan = generateAkuanPenerimaan(klasifikasiAm, fd, p.getIdPermohonan(), ia);

        return senaraiPermohonan;
    }

    private ArrayList<Hakmilik> getSenaraiHakmilik() {
        ArrayList<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        // processing Hakmilik
        if (hakmilikPermohonan != null && hakmilikPermohonan.size() > 0) {
            for (String hp : hakmilikPermohonan) {
                if (hp == null) {
                    continue;
                }
                if (hp.trim().length() > 0) {
                    Hakmilik hm = hakmilikDAO.findById(hp);
                    if (hm == null) {
                        throw new RuntimeException("ID Hakmilik " + hp + " tidak dijumpai.");
                    }
                    listHakmilik.add(hm);
                }
            }
        }

        // processing hakmilik bersiri
        if (getIdHakmilikSiriDari() != null && idHakmilikSiriKe != null) {
            for (int i = 0; i < getIdHakmilikSiriDari().size(); i++) {
                String idH1 = getIdHakmilikSiriDari().get(i);
                String idH2 = idHakmilikSiriKe.get(i);

                if (idH1 == null || idH1.trim().length() == 0
                        || idH2 == null || idH2.trim().length() == 0) {
                    continue;
                }

                ArrayList<String> list = permohonanUtil.getIdHakmilikFromSiri(idH1, idH2);

                List<Hakmilik> listId = sessionProvider.get().createQuery(
                        "select h from Hakmilik h inner join fetch h.senaraiAkaun a "
                        + "where h.idHakmilik in (:listHakmilik)").setParameterList("listHakmilik", list).list();
                listHakmilik.addAll(listId);
            }
        }
        return listHakmilik;
    }

    private Dokumen generateAkuanPenerimaan(KodKlasifikasi klas, FolderDokumen fd,
            String noRujukan, InfoAudit ia) {
        Dokumen d = new Dokumen();
        d.setKodDokumen(kodDokumenDAO.findById("UNKN1"));
        d.setFormat("application/pdf");
        d.setInfoAudit(ia);
        d.setKlasifikasi(klas);
        d.setNoVersi("1.0");
        d.setTajuk("Akuan Penerimaan " + noRujukan);
        d.setNoRujukan(noRujukan);
        dokumenDAO.save(d);
        KandunganFolder kf1 = new KandunganFolder();
        kf1.setFolder(fd);
        kf1.setDokumen(d);
        kf1.setInfoAudit(ia);
        fd.getSenaraiKandungan().add(kf1);

        return d;
    }

    @Override
    public void doAfterSave(ReportUtil reportUtil, DokumenKewangan dk, InfoAudit ia) {
        String documentPath = conf.getProperty("document.path");

        LOG.debug("senaraiPermohonan.size=" + senaraiPermohonan.size());

        // generate Surat Akuan Penerimaan for each Permohonan
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
        for (UrusanPermohonan u : senaraiPermohonan) {
            if (u.akuanPenerimaan == null) {
                continue;
            }
            String path2 = "AkuanPenerimaan" + File.separator + u.akuanPenerimaan.getIdDokumen();
            if (debug) {
                LOG.debug("HSLResitAkuanPenerimaan=" + path + path2);
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                reportUtil.generateReport("HSLResitAkuanPenerimaan002_MLK.rdf",
                        new String[]{"p_id_mohon"},
                        new String[]{u.getNoRujukan()},
                        path + path2, ia.getDimasukOleh());
            } else {
                reportUtil.generateReport("HSLResitAkuanPenerimaan002.rdf",
                        new String[]{"p_id_mohon"},
                        new String[]{u.getNoRujukan()},
                        path + path2, ia.getDimasukOleh());
            }
            u.akuanPenerimaan.setNamaFizikal(reportUtil.getDMSPath());
            dokumenDAO.update(u.akuanPenerimaan);

            // post processing
            urusanPostProcessor.performPostProcess(u, u.getSenaraiHakmilik(), dk, u.getSenaraiTransaksi(), ia);

        }
    }

    private Permohonan savePermohonan(String kodNegeri, Pengguna pengguna, KodUrusan kodUrusan,
            UrusanPermohonan uv, String idKumpulan, int seq, FolderDokumen fd,
            Permohonan permohonanLama,
            List<Hakmilik> listHakmilik,
            // PENYERAH
            String idPenyerah, KodPenyerah penyerahKod,
            String penyerahNoPengenalan, KodJenisPengenalan penyerahJenisPengenalan,
            String penyerahNama, String penyerahAlamat1,
            String penyerahAlamat2, String penyerahAlamat3, String penyerahAlamat4,
            String penyerahPoskod, KodNegeri penyerahNegeri,
            String penyerahEmail, String penyerahNoTelefon, String namaPenyampai, String noPengenalanPenyampai,
            String noTelPenyampai, InfoAudit ia) {

        KodCawangan caw = pengguna.getKodCawangan();
        if (KOD_JABATAN_STRATA.equals(kodUrusan.getJabatan().getKod())) {
            if (listHakmilik != null && !listHakmilik.isEmpty()) {
                Hakmilik hm = listHakmilik.get(0);
                if (hm != null) {
                    caw = hm.getCawangan();
                }
            }
        }

        String idPermohonan = null;
        if (KOD_JABATAN_PENDAFTARAN.equals(kodUrusan.getJabatan().getKod())) { // for pendaftaran, use ID Perserahan
            idPermohonan = idPerserahanGenerator.generate(
                    kodNegeri, pengguna.getKodCawangan(), kodUrusan);
        } else {
            idPermohonan = idPermohonanGenerator.generate(
                    kodNegeri, caw, kodUrusan);
        }
        uv.noRujukan = idPermohonan;

        
        Permohonan p = new Permohonan();
        // add by azri: for N9, all "kod_serah = SB" finish proses at spoc
        if ("SB".equals(kodUrusan.getKodPerserahan().getKod())) {
            p.setStatus("SL");
            p.setKeputusanOleh(pengguna);
            p.setTarikhKeputusan(new java.util.Date());
            p.setKeputusanOleh(pengguna);
            KodKeputusan kpsn = new KodKeputusan();
            kpsn.setKod("D");
            p.setKeputusan(kpsn);
        } else {
            p.setStatus("TA");
        }
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(caw);
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);
        // for Urusan Berangkai set only for Pendaftaran (except for SW, SA, SB)
        if (kodUrusan.getJabatan().getKod().equals(KOD_JABATAN_PENDAFTARAN)
                && !"SA".equals(kodUrusan.getKod())
                && !"SB".equals(kodUrusan.getKod())
                && !"SW".equals(kodUrusan.getKod())) {
            p.setIdKumpulan(idKumpulan);
            p.setKumpulanNo(seq);
        }

        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        // add by azri: for N9, all "kod_serah = SB" finish proses at spoc
        if ("SB".equals(kodUrusan.getKodPerserahan().getKod())) {
            iaPermohonan.setTarikhKemaskini(d);
            iaPermohonan.setDiKemaskiniOleh(pengguna);
        }
        p.setInfoAudit(iaPermohonan);
        // penyerah
        if (idPenyerah != null && idPenyerah.length() > 0
                && !"0".equals(idPenyerah)) {
            p.setIdPenyerah(Long.parseLong(idPenyerah));
        }
        if (penyerahKod != null && penyerahKod.getKod() != null
                && !"0".equals(penyerahKod.getKod())) {
            p.setKodPenyerah(penyerahKod);
        }
        p.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
        p.setPenyerahNoPengenalan(penyerahNoPengenalan);
        p.setPenyerahNama(penyerahNama);
        p.setPenyerahAlamat1(penyerahAlamat1);
        p.setPenyerahAlamat2(penyerahAlamat2);
        p.setPenyerahAlamat3(penyerahAlamat3);
        p.setPenyerahAlamat4(penyerahAlamat4);
        p.setPenyerahPoskod(penyerahPoskod);
        if (penyerahNegeri != null) {
            p.setPenyerahNegeri(penyerahNegeri);
        }
        p.setPermohonanSebelum(permohonanLama);
        p.setPenyerahEmail(penyerahEmail);
        p.setPenyerahNoTelefon1(penyerahNoTelefon);
        p.setNamaPenyampai(namaPenyampai);
        p.setNoPengenalanPenyampai(noPengenalanPenyampai);
        p.setNoTelPenyampai(noTelPenyampai);
        p.setUntukTahun(d.getYear() + 1900);
     
            p.setIdWorkflow(kodUrusan.getIdWorkflow());
        
        if (catatan.length() > 0) {
            p.setCatatan(catatan.toString());
        }
        permohonanDAO.save(p);

        // attach Hakmilik
        if (listHakmilik != null) {
            for (Hakmilik hm : listHakmilik) {
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setKodHakmilik(hm.getKodHakmilik());
                hpa.setBandarPekanMukimBaru(hm.getBandarPekanMukim());
                hpa.setLuasTerlibat(hm.getLuas());
                hpa.setKodUnitLuas(hm.getKodUnitLuas());
                hpa.setLot(hm.getLot());
                hpa.setNoLot(hm.getNoLot());
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
            }
        }

        /*
         * Cater for Pelupusan - PBMT
         */
        if (kodUrusan.getJabatan().getKod().equals(KOD_JABATAN_PELUPUSAN)) {
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (kodUrusan.getKod().equals("PBMT") || kodUrusan.getKod().equals("PTBTS") || kodUrusan.getKod().equals("PTBTC")) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    if (uv.amaun1 != null) {
                        hpa.setLuasTerlibat(uv.amaun1);
                        hpa.setKodUnitLuas(kodUOMDAO.findById("H"));
                    }
                    hpa.setPermohonan(p);
                    hpa.setInfoAudit(ia);
                    hakmilikPermohonanDAO.save(hpa);
                }
            }
        }

        // processing permohonan lama
        if (permohonanLama != null) {
            // copy data from permohonanLama
            p.setCatatan("Permohonan ini telah dibuat berdasarkan Permohonan/Perserahan "
                    + permohonanLama.getIdPermohonan() + ".");

            // copy pemohon
            //COPY PIHAK TERLIBAT CONSENT TO TABLE PEMOHON IF KESINAMBUNGAN ADALAH URUSAN PENDAFTARAN (Added by Amin @ ptg melaka)
            if (kodUrusan.getJabatan().getKod().equals(KOD_JABATAN_PENDAFTARAN) && permohonanLama.getKodUrusan().getJabatan().getKod().equals(KOD_JABATAN_CONSENT)) {

                List<PermohonanPihak> senaraiPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKod(permohonanLama.getIdPermohonan(), "TER");
                if (senaraiPihakTerlibat != null) {
                    List<Pemohon> newList = new ArrayList<Pemohon>();
                    for (PermohonanPihak p1 : senaraiPihakTerlibat) {
                        Pemohon p2 = new Pemohon();
                        p2.setCawangan(pengguna.getKodCawangan());
                        p2.setPermohonan(p);
                        p2.setPihak(p1.getPihak());
                        p2.setPihakCawangan(p1.getPihakCawangan());
                        p2.setHakmilik(p1.getHakmilik());
                        p2.setJenis(p1.getJenis());
                        p2.setKaitan(p1.getKaitan());
                        p2.setPekerjaan(p1.getPekerjaan());
                        p2.setPendapatan(p1.getPendapatan());
                        p2.setStatusKahwin(p1.getStatusKahwin());
                        p2.setSyerPembilang(p1.getSyerPembilang());
                        p2.setSyerPenyebut(p1.getSyerPenyebut());
                        p2.setTanggungan(p1.getTangungan());
                        p2.setUmur(p1.getUmur());
                        p2.setMatawang(p1.getKodMataWang());
                        p2.setNama(p1.getNama());
                        p2.setJenisPengenalan(p1.getJenisPengenalan());
                        p2.setNoPengenalan(p1.getNoPengenalan());
                        p2.setAlamat(p1.getAlamat());
                        p2.setAlamatSurat(p1.getAlamatSurat());
                        p2.setInfoAudit(ia);
                        newList.add(p2);
                    }
                    p.setSenaraiPemohon(newList);
                }

                // copy PermohonanPihak
                //NO NEED TO COPY TER DAN WAR (CONSENT) IF KESINAMBUNGAN ADALAH URUSAN PENDAFTARAN 
                List<PermohonanPihak> senaraiPihak = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(permohonanLama.getIdPermohonan(), "TER", "WAR");

                if (senaraiPihak != null) {
                    List<PermohonanPihak> newList = new ArrayList<PermohonanPihak>();
                    for (PermohonanPihak p1 : senaraiPihak) {

                        PermohonanPihak p2 = new PermohonanPihak();
                        p2.setCawangan(pengguna.getKodCawangan());
                        p2.setPermohonan(p);
                        p2.setPihak(p1.getPihak());
                        p2.setPihakCawangan(p1.getPihakCawangan());
                        p2.setHakmilik(p1.getHakmilik());

                        //KES CONSENT : SET KOD JENIS PB SUPAYA IKUT KOD JENIS PB PENDAFTARAN SEPERTI DIBERITAHU OLEH EN FIKRI
                        //PMT - PM
                        //GD  - PG
                        //PJT - PJ
                        //TEN - PY
                        KodJenisPihakBerkepentingan kodJenisPB = new KodJenisPihakBerkepentingan();

                        if (kodUrusan.getKod().equals("PMT")) {
                            kodJenisPB.setKod("PM");
                        } else if (kodUrusan.getKod().equals("GD")) {
                            kodJenisPB.setKod("PG");
                        } else if (kodUrusan.getKod().equals("PJT")) {
                            kodJenisPB.setKod("PJ");
                        } else if (kodUrusan.getKod().equals("TEN")) {
                            kodJenisPB.setKod("PY");
                        } else {
                            kodJenisPB.setKod(p1.getJenis().getKod());
                        }

                        p2.setJenis(kodJenisPB);
                        p2.setKaitan(p1.getKaitan());
                        p2.setPekerjaan(p1.getPekerjaan());
                        p2.setPendapatan(p1.getPendapatan());
                        p2.setStatusKahwin(p1.getStatusKahwin());
                        p2.setSyerPembilang(p1.getSyerPembilang() != null ? p1.getSyerPembilang() : 0);
                        p2.setSyerPenyebut(p1.getSyerPenyebut() != null ? p1.getSyerPenyebut() : 0);
                        p2.setTangungan(p1.getTangungan());
                        p2.setUmur(p1.getUmur());
                        p2.setDalamanNilai1(p1.getDalamanNilai1());
                        p2.setKodMataWang(p1.getKodMataWang());
                        p2.setWargaNegara(p1.getWargaNegara());
                        p2.setNama(p1.getNama());
                        p2.setJenisPengenalan(p1.getJenisPengenalan());
                        p2.setNoPengenalan(p1.getNoPengenalan());
                        p2.setAlamat(p1.getAlamat());
                        p2.setAlamatSurat(p1.getAlamatSurat());
                        p2.setInfoAudit(ia);
                        newList.add(p2);

                        List<PermohonanPihakHubungan> listHbgn = p1.getSenaraiHubungan();
                        ArrayList<PermohonanPihakHubungan> newList2 = new ArrayList<PermohonanPihakHubungan>();
                        for (PermohonanPihakHubungan pph1 : listHbgn) {
                            PermohonanPihakHubungan pph2 = new PermohonanPihakHubungan();
                            pph2.setAlamat1(pph1.getAlamat1());
                            pph2.setAlamat2(pph1.getAlamat2());
                            pph2.setAlamat3(pph1.getAlamat3());
                            pph2.setAlamat4(pph1.getAlamat4());
                            pph2.setPoskod(pph1.getPoskod());
                            pph2.setCawangan(pengguna.getKodCawangan());
                            pph2.setInfoAudit(ia);
                            pph2.setJenisPengenalan(pph1.getJenisPengenalan());
                            pph2.setKaitan(pph1.getKaitan());
                            pph2.setNama(pph1.getNama());
                            pph2.setNegeri(pph1.getNegeri());
                            pph2.setNoPengenalan(pph1.getNoPengenalan());
                            pph2.setNoTelefon1(pph1.getNoTelefon1());
                            pph2.setPihak(p2);
                            pph2.setSyerPembilang(pph1.getSyerPembilang());
                            pph2.setSyerPenyebut(pph1.getSyerPenyebut());
                            pph2.setUmur(pph1.getUmur());
                            pph2.setWargaNegara(pph1.getWargaNegara());
                            newList2.add(pph2);
                        }
                        if (newList2.size() > 0) {
                            p2.setSenaraiHubungan(newList2);
                        }
                    }
                    p.setSenaraiPihak(newList);
                }

            } else {
                // copy pemohon
                List<Pemohon> senaraiPemohon = permohonanLama.getSenaraiPemohon();
                if (senaraiPemohon != null) {
                    List<Pemohon> newList = new ArrayList<Pemohon>();
                    for (Pemohon p1 : senaraiPemohon) {
                        Pemohon p2 = new Pemohon();
                        p2.setCawangan(pengguna.getKodCawangan());
                        p2.setPermohonan(p);
                        p2.setPihak(p1.getPihak());
                        p2.setPihakCawangan(p1.getPihakCawangan());
                        p2.setKaitan(p1.getKaitan());
                        p2.setPekerjaan(p1.getPekerjaan());
                        p2.setPendapatan(p1.getPendapatan());
                        p2.setPendapatanLain(p1.getPendapatanLain());
                        p2.setStatusKahwin(p1.getStatusKahwin());
                        p2.setTanggungan(p1.getTanggungan());
                        p2.setUmur(p1.getUmur());
                        p2.setMatawang(p1.getMatawang());
                        p2.setWargaNegara(p1.getWargaNegara());
                        p2.setNama(p1.getNama());
                        p2.setJenisPengenalan(p1.getJenisPengenalan());
                        p2.setNoPengenalan(p1.getNoPengenalan());
                        p2.setAlamat(p1.getAlamat());
                        p2.setAlamatSurat(p1.getAlamatSurat());
                        p2.setInfoAudit(ia);
                        newList.add(p2);
                    }
                    p.setSenaraiPemohon(newList);
                }
                // copy PermohonanPihak
                // edit by izzat
                // for JPGD / JPGPJ no need to copy PermohonanPihak
                if ((!"PPTL".equals(permohonanLama.getKodUrusan().getKod())
                        && !"PPJP".equals(permohonanLama.getKodUrusan().getKod()) && !"PJTA".equals(permohonanLama.getKodUrusan().getKod()))) {
                    List<PermohonanPihak> senaraiPihak = permohonanLama.getSenaraiPihak();
                    if (senaraiPihak != null) {
                        List<PermohonanPihak> newList = new ArrayList<PermohonanPihak>();
                        for (PermohonanPihak p1 : senaraiPihak) {

                            PermohonanPihak p2 = new PermohonanPihak();
                            p2.setCawangan(pengguna.getKodCawangan());
                            p2.setPermohonan(p);
                            p2.setPihak(p1.getPihak());
                            p2.setPihakCawangan(p1.getPihakCawangan());
                            p2.setHakmilik(p1.getHakmilik());
                            p2.setJenis(p1.getJenis());
                            p2.setKaitan(p1.getKaitan());
                            p2.setPekerjaan(p1.getPekerjaan());
                            p2.setPendapatan(p1.getPendapatan());
                            p2.setStatusKahwin(p1.getStatusKahwin());
                            p2.setSyerPembilang(p1.getSyerPembilang() != null ? p1.getSyerPembilang() : 0);
                            p2.setSyerPenyebut(p1.getSyerPenyebut() != null ? p1.getSyerPenyebut() : 0);
                            p2.setTangungan(p1.getTangungan());
                            p2.setUmur(p1.getUmur());
                            p2.setDalamanNilai1(p1.getDalamanNilai1());
                            p2.setKodMataWang(p1.getKodMataWang());
                            p2.setWargaNegara(p1.getWargaNegara());
                            p2.setNama(p1.getNama());
                            p2.setJenisPengenalan(p1.getJenisPengenalan());
                            p2.setNoPengenalan(p1.getNoPengenalan());
                            p2.setAlamat(p1.getAlamat());
                            p2.setAlamatSurat(p1.getAlamatSurat());
                            p2.setInfoAudit(ia);
                            newList.add(p2);

                            List<PermohonanPihakHubungan> listHbgn = p1.getSenaraiHubungan();
                            ArrayList<PermohonanPihakHubungan> newList2 = new ArrayList<PermohonanPihakHubungan>();
                            for (PermohonanPihakHubungan pph1 : listHbgn) {
                                PermohonanPihakHubungan pph2 = new PermohonanPihakHubungan();
                                pph2.setAlamat1(pph1.getAlamat1());
                                pph2.setAlamat2(pph1.getAlamat2());
                                pph2.setAlamat3(pph1.getAlamat3());
                                pph2.setAlamat4(pph1.getAlamat4());
                                pph2.setPoskod(pph1.getPoskod());
                                pph2.setCawangan(pengguna.getKodCawangan());
                                pph2.setInfoAudit(ia);
                                pph2.setJenisPengenalan(pph1.getJenisPengenalan());
                                pph2.setKaitan(pph1.getKaitan());
                                pph2.setNama(pph1.getNama());
                                pph2.setNegeri(pph1.getNegeri());
                                pph2.setNoPengenalan(pph1.getNoPengenalan());
                                pph2.setNoTelefon1(pph1.getNoTelefon1());
                                pph2.setPihak(p2);
                                pph2.setSyerPembilang(pph1.getSyerPembilang());
                                pph2.setSyerPenyebut(pph1.getSyerPenyebut());
                                pph2.setUmur(pph1.getUmur());
                                pph2.setWargaNegara(pph1.getWargaNegara());
                                newList2.add(pph2);
                            }
                            if (newList2.size() > 0) {
                                p2.setSenaraiHubungan(newList2);
                            }
                        }
                        p.setSenaraiPihak(newList);
                    }
                }

            }

            if (kodUrusan.getJabatan().getKod().equals(KOD_JABATAN_PENDAFTARAN) && permohonanLama.getKodUrusan().getJabatan().getKod().equals(KOD_JABATAN_CONSENT)) {
            } else {
            }

            permohonanDAO.save(p);
        }

        return p;
    }

    @Override
    public Class<? extends ActionBean> getEditActionBean() {
        return PermohonanKaunter2.class;
    }
}

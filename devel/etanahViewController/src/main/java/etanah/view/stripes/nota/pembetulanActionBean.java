/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPembetulanUrusanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Alamat;
import etanah.model.Hakmilik;
import etanah.model.AlamatSurat;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodBangsa;
import etanah.model.KodJabatan;
import etanah.model.KodNegeri;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodPerintah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodRizab;
import etanah.model.KodKategoriTanah;
import etanah.model.KodSeksyen;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.KodLot;
import etanah.model.KodUrusan;
import etanah.model.KodWarganegara;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.model.PermohonanPembetulanUrusan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.model.PihakPengarah;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Dokumen;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.KodJenisPengenalan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihakKemaskini;
import etanah.sequence.GeneratorIdPerserahanPaksa;
import etanah.service.CalcTax;
import etanah.service.RegService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.service.daftar.PembetulanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanPihakHubunganService;
import etanah.service.daftar.PermohonanPihakKemaskiniService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.fairul/wan.fairul
 */
@UrlBinding("/daftar/pembetulan/betul")
public class pembetulanActionBean extends AbleActionBean {

    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    GeneratorIdPerserahanPaksa generatorIdPerserahanPaksa;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
    @Inject
    PermohonanRujukanLuarService service; // mohon_ruj_luar service
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanService;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodBandarPekanMukimDAO kodBpmDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikPihakBerkepentinganDAO hpbDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanPembetulanHakmilikDAO permohonanPembetulanHakmilikDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikSebelumPermohonanDAO mohonHakmilikSblmDAO;
    @Inject
    HakmilikAsalPermohonanDAO mohonHakmilikAsalDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanPihakHubunganService permohonanPihakHubunganService;
    @Inject
    PermohonanPihakKemaskiniService permohonanPihakKkiniService;
    private Hakmilik hakmilik;
    private PermohonanPihakKemaskini permohonanPihakKemaskini;
    private KodStatusHakmilik kodStaHak;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan mohonHakmilik;
    private PermohonanPihakHubungan permohonanPihakHubungan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihakHubungan> permohonanPihakHubunganList;
    private PermohonanPembetulanHakmilik permohonanPembetulanHakmilik;
    private PermohonanPembetulanUrusan permohonanPembetulanUrusan;
    private PermohonanPembetulanUrusanDAO permohonanPembetulanUrusanDAO;
    private HakmilikPermohonan hakmilikpermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanAtasPerserahan permohonanAtasPerserahan;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<KodSeksyen> listKodSeksyenByBpm;
    private List<KodStatusHakmilik> listKodStatusHakmilikD;
    private List<PermohonanRujukanLuar> listPermohonanRujukanLuar;
    private List<KodStatusHakmilik> listKodStatusHakmilik;
    private List<KodStatusHakmilik> listKodStatusHakmilikB;
    private List<HakmilikUrusan> hakmilikUrusanListY;
    private List<HakmilikUrusan> hakmilikUrusanListNotaBorang;
    private List<HakmilikUrusan> hakmilikUrusanListByKodserah;
    private List<KodUrusan> senaraiUrusanPendaftaran;
    private List<PermohonanPembetulanUrusan> listUrusanBetul;
    private List<PermohonanPembetulanHakmilik> listHakmilikAsal;
    private List<PermohonanPembetulanHakmilik> listHakimilSblm;
    private List<HakmilikAsalPermohonan> listHakmilikAsalMohon;
    private HakmilikAsalPermohonan hakmilikAsalPermohonan;
    private List<HakmilikSebelumPermohonan> listHakimilSblmMohon;
    private HakmilikSebelumPermohonan hakmilikSebelumPermohonan;
    private List<PermohonanAtasPerserahan> mohonAtasUrusan;
    private List<PermohonanPembetulanHakmilik> permohonanbetulHakmilik;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<PermohonanPihak> permohonanPihakList;
    private List<Pemohon> pemohonList;
    private List<PihakCawangan> cawanganList;
    private List<Dokumen> senaraiDokumen;
    private List<PihakPengarah> pengarahList;
    private List<KandunganFolder> listSWD;
    private List<KandunganFolder> listSBD;
    private List<KandunganFolder> listSAB;
    private Permohonan permohonan;
    private Permohonan permohonanLama;
    private Pihak pihakTemp;
    private PermohonanRujukanLuar pkl;
    private String idH;
    private String idHA;
    private String idHakmilik;
    private String idPerserahan;
    private String idPembetulan;
    private String idPermohonanPihak;
    private String tambahWaris;
    private String idPerserahanLama;
    private boolean saveFlag = false;
    private Pengguna peng;
    private InfoAudit info;
    private String tarikhDaftar;
    private String tarikhDaftarAsal;
    private String tarikhDaftarUrusan;
    private String tarikhLuput;
    private String kodseksyen;
    private String kodUrusan;
    private String idUrusan;
    private String copyToAllHakmilik;
    private String noJilid;
    private String noFolio;
    private Date trhLuput;
    private String[] tarikhluputbaru;
    private String[] tempohPeganganBaru;
    private String idHakmilikBaru;
    private String idHakmilikAsal;
    private String idHakmilikSebelum;
    private List<HakmilikAsal> hakmilikAsalList;
    private HakmilikAsal hakmilikAsal;
    private List<HakmilikSebelum> hakmilikSebelumList;
    private List<HakmilikSebelum> hakmilikSebelumListLama;
    private HakmilikSebelum hakmilikSebelum;
    private HakmilikUrusan hakmilikUrusan;
    private PermohonanUrusan mohonUrusan;
    private FolderDokumen fd;
    private PermohonanPembetulanUrusan betulUrusan;
    private static final Logger LOG = Logger.getLogger(pembetulanActionBean.class);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    private String aktif;
    private Date trhDaftar;
    private Date trhDaftarBaru;
    private String trhconvert;
    private String trhKuasa;
    private String trhTamat;
    private String tarikhDaftarHakmilikAsal;
    private Date trhDaftarHakmilikAsal;
    private String idHS;
    private String idPermohonan;
    private String statusHakmilikKod;
    private String idPemohon;
    private String idMohon;
    private String idHakmilikTemp;
    private String[] cukai;
    private String[] cukaiThn;
    private String flag;
    private String idPihak;
    private String kodPB;
    private String[] noPelan;
    private Pihak pihak;
    private Pihak pemilik;
    private String idPermohonanLama;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganPemilikList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganWarisList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganPGList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganKAVEATList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganPJList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganTENList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganPIList;
    private List<HakmilikPihakBerkepentingan> hpBerkepentinganPBList;
    private List<HakmilikUrusan> hakmilikUrusanSC;
    private List<HakmilikUrusan> hakmilikUrusanN;
    private List<HakmilikUrusan> hakmilikUrusanB;
    private List<HakmilikUrusan> hakmilikUrusanSCbatal;
    private List<HakmilikUrusan> hakmilikUrusanNbatal;
    private List<HakmilikUrusan> hakmilikUrusanBbatal;
    private HakmilikPihakBerkepentingan hpBerkepentingan;
    private String[] statusHakmilikbaru;
    private boolean flagBetul = false;
    private boolean kemaskini;
    private boolean tambah;
    private boolean flagHakmilikStrata = false;
    private String kodNegeri;
//    butiran PB
    private String nama;
    private String jenisPengenalan;
    private String noPengenalan;
    private String bangsa;
    private String jantina;
    private String warganegara;
    private String jenisPB;
    private String kumpulanPB;
    private String noSuratAmanah;
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
    //for betul maklumat urusan - SC
    private String perjanjianAmaun;
    private String perjanjianDutiStem;
    private String perjanjianNoRujukan;
    private String noFail;
    private String tarikhSaksi;
    //for betul maklumat urusan - N
    private String tarikhSidang;
    private String tarikhRujukan;
    private String noRujukan;
    private String tarikhKuatkuasa;
    private String tarikhLulus;
    private String noSidang;
    private String catatan;
    private String ulasan;
    private String tempohTahun;
    private String tempohBulan;
    private String tempohHari;
    private String majlis;
    //for betul maklumat urusan - B
    private String perintahTarikhKuatkuasa;
    private String tarikhMula;
    private String kodPerintah;
    private String perintahSebab;
    private String namaSidang;
    private String tarikhTamat;
    private String tarikhDaftarBaru;
    //for tambah urusan - pihak berkepentingan
    boolean tambahCawFlag;
    boolean tambahPengarahFlag;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> mohonPihakList;
    private String tempatLahirLain;
    private List<KodBangsa> senaraiKodBangsa;
    private List<KodJenisPihakBerkepentingan> senaraiKodPenerima;
    private List<KodBangsa> senaraiKodBangsaOrang;
    private List<Pemohon> senaraiPemohon;
    private List<PermohonanPihak> senaraiMohonPihak;
    private List<KodBangsa> senaraiKodBangsaSyarikat;
    private List<Pihak> pihakByNameList;
    //for betul maklumat berkaitan hakmilik
    private String luasTerlibat;
    private String strKodUOM;
    private String cukaiBaru;
    private String cukaiLama;
    private String luasBaru;
    private String luas;
    private String jenisPihak;
    private String kawasan;
    private String[] syerPembilang;
    private String[] syerPenyebut;
    private String[] umurWaris;
    private String[] syerPembilang1;
    private String[] syerPenyebut1;
    private String kod_syarat;
    private String kodUOMLama;
    private String kodUOM;
    private String kod_sekatan;
    private String kodLot;
    private String rizab_kod;
    private String kategoriTanah;
    private String gunatanah;
    private String SAB;
    private String SBD;
    private String SWD;
    private String caw;
    private boolean SWdahAda = false;
    private boolean SBdahAda = false;
    private boolean SAdahAda = false;
    private Dokumen dokumenSave;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idH = getContext().getRequest().getParameter("idH");
        idHA = getContext().getRequest().getParameter("idHA");
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            listKodStatusHakmilik = pService.findStatusHakmilik();
            listKodStatusHakmilikD = pService.findStatusHakmilikD();
            listKodStatusHakmilikB = pService.findStatusHakmilikB();
            hakmilikUrusanListY = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanListNotaBorang = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanListByKodserah = new ArrayList<HakmilikUrusan>();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();

            mohonAtasUrusan = pService.findAtasUrusan(idPermohonan);

            if (StringUtils.isBlank(idH)) {
                permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), idH);
            } else {
                permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), idH);
            }
            permohonanbetulHakmilik = pService.findidPermohonan(permohonan.getIdPermohonan());

            for (HakmilikPermohonan hmk : hakmilikPermohonanList) {
                String idHakmilik = hmk.getHakmilik().getIdHakmilik();
                hakmilikUrusanListY.addAll(pService.findUrusanByidHY(idHakmilik));
                hakmilikUrusanListNotaBorang.addAll(pService.findUrusanNotaBorang(idHakmilik));
                hakmilikUrusanListByKodserah.addAll(pService.findUrusanByidKodSerah(idHakmilik));
                pihakKepentinganList.addAll(pService.findHakmilikPihakActiveByIdH(idHakmilik));
                permohonanRujukanLuar = pService.findRujukanIDMohonHakmilik(idPermohonan, idHakmilik);

                HakmilikPermohonan hplama = regService.searchMohonHakmilikObject(idHakmilik, permohonan.getIdPermohonan());
                if (hplama != null) {
                    listHakmilikAsalMohon = regService.searchMohonHakmilikAsalByID(hplama.getId());
                    listHakimilSblmMohon = regService.searchMohonHakmilikSblmByID(hplama.getId());
                }

                if (permohonan.getKodUrusan().equals("BETHM")) {
                    LOG.info("masuk");
                    List<PermohonanPembetulanHakmilik> pph = pService.findidPermohonanOridHakmilik(permohonan.getIdPermohonan(), idHakmilik);
                    if (pph != null) {
                        for (PermohonanPembetulanHakmilik ph : pph) {
                            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), ph.getIdHakmilik());
                        }
                        if (StringUtils.isBlank(idUrusan)) {
                            permohonanPembetulanHakmilik = pService.findByidPidHandidAu(permohonan.getIdPermohonan(), idHakmilik, idUrusan);
                        } else {
                            permohonanPembetulanHakmilik = pService.findByidPidHandidAu(permohonan.getIdPermohonan(), idHakmilik, idUrusan);
                        }
                    }
                }
                noPelan = new String[hakmilikPermohonanList.size()];
                cukai = new String[hakmilikPermohonanList.size()];
                cukaiThn = new String[hakmilikPermohonanList.size()];
                statusHakmilikbaru = new String[hakmilikPermohonanList.size()];
                tarikhluputbaru = new String[hakmilikPermohonanList.size()];
                tempohPeganganBaru = new String[hakmilikPermohonanList.size()];

            }
            if (idH != null) {
                setHakmilik(hakmilikDAO.findById(idH));
                setHakmilikAsal(pService.findByidHidHA(idH, idHA));
                if (permohonanPembetulanHakmilik != null) {
                } else {
                    LOG.info("permohonanPembetulanHakmilik :: NULL");
                }
                LOG.info(permohonanPembetulanHakmilik);

            }

        }

    }

    @DefaultHandler
    public Resolution asasHakmilik() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        idH = getContext().getRequest().getParameter("idH");
        listHakmilikAsal = pService.findHakmilikAsal(permohonan.getIdPermohonan());
//        listHakimilSblm = pService.findHakmilikSblm(permohonan.getIdPermohonan());

        HakmilikPermohonan hplama = regService.searchMohonHakmilikObject(idH, permohonan.getIdPermohonan());
        if (hplama != null) {
            listHakimilSblmMohon = regService.searchMohonHakmilikSblmByID(hplama.getId());
        }

        if (idH != null) {
            hakmilik = hakmilikDAO.findById(idH);
            //for hakmilik Strata
            if (hakmilik.getIdHakmilikInduk() != null) {
                setFlagHakmilikStrata(true);
            } else {
                setFlagHakmilikStrata(false);
            }
            if (permohonanPembetulanHakmilik != null) {
                permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), idH);
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }

            LOG.info(permohonanPembetulanHakmilik);
        }
        return new JSP("daftar/pembetulan/pembetul_maklumatAsasHakmilik.jsp").addParameter("tab", "true");

    }

    public Resolution messagerME() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/pembetulan/messager.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
//        return new JSP("daftar/pembetulan/select_senarai_hakmilik.jsp").addParameter("tab", "true");
        return new JSP("daftar/pembetulan/senaraiHakmilikTerlibat.jsp").addParameter("tab", "true");

    }

    public Resolution betulCukai() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        int i = 0;
        cukai = new String[hakmilikPermohonanList.size()];
        cukaiThn = new String[hakmilikPermohonanList.size()];
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                if (permohonanPembetulanHakmilik.getCukai() != null) {
                    cukai[i] = String.valueOf(permohonanPembetulanHakmilik.getCukai());
                } else {
                    cukai[i] = String.valueOf("");
                }

                if (permohonanPembetulanHakmilik.getCukaiSemasa() != null) {
                    cukaiThn[i] = String.valueOf(permohonanPembetulanHakmilik.getCukaiSemasa());
                } else {
                    cukaiThn[i] = String.valueOf("");
                }
            }
            i++;
        }
        return new JSP("daftar/pembetulan/betulCukai.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiUrusan() {
        String error = getContext().getRequest().getParameter("error");
        hakmilikUrusanB = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanN = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanSC = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanBbatal = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanNbatal = new ArrayList<HakmilikUrusan>();
        hakmilikUrusanSCbatal = new ArrayList<HakmilikUrusan>();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        listUrusanBetul = pService.findUrusanBetulList(permohonan.getIdPermohonan());
//         alert("Count =", hakmilikPermohonanList.size());
        for (HakmilikPermohonan hpMOHON : hakmilikPermohonanList) {

            if (hpMOHON.getHakmilik().getIdHakmilikInduk() != null) {
                error = "Kemaskini Data Pembetulan Telah Berjaya";
                setFlagHakmilikStrata(false);
            } else {
                setFlagHakmilikStrata(true);
            }
            hakmilikUrusanB.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "B"));
            hakmilikUrusanN.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "N"));
            hakmilikUrusanSC.addAll(pService.findHakmilikByHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "SC"));

            hakmilikUrusanBbatal.addAll(pService.findHakmilikByHakmilikBatal(hpMOHON.getHakmilik().getIdHakmilik(), "B"));
            hakmilikUrusanNbatal.addAll(pService.findHakmilikByHakmilikBatal(hpMOHON.getHakmilik().getIdHakmilik(), "N"));
            hakmilikUrusanSCbatal.addAll(pService.findHakmilikByHakmilikBatal(hpMOHON.getHakmilik().getIdHakmilik(), "SC"));

        }

        return new JSP("daftar/pembetulan/senaraiUrusan.jsp").addParameter("tab", "true");

    }

    public Resolution popupAsal() {
        idH = getContext().getRequest().getParameter("idH");
        idHA = getContext().getRequest().getParameter("idHA");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            setHakmilikAsal(pService.findByidHidHA(idH, idHA));

            List<PermohonanPembetulanHakmilik> PPH = pService.findidPermohonan(idPermohonan);

            for (PermohonanPembetulanHakmilik permohonanPembetulanHakmilik : PPH) {
                LOG.info("permohonanPembetulanHakmilik :" + permohonanPembetulanHakmilik.getTarikhDaftarHakmilikAsal());
                if (permohonanPembetulanHakmilik != null) {
                    if (permohonanPembetulanHakmilik.getTarikhDaftarHakmilikAsal() != null) {
                        tarikhDaftarHakmilikAsal = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftarHakmilikAsal()).substring(0, 10);
                    }
                } else {
                    LOG.info("permohonanPembetulanHakmilik :: NULL");
                }
                LOG.info(permohonanPembetulanHakmilik);

            }
        }
        // return new JSP("daftar/pembetulan/editHakmilikAsal.jsp").addParameter("tab", "true");
        return new JSP("daftar/pembetulan/editHakmilikAsal.jsp").addParameter("tab", "true");

    }

    //added by eda 0n 15/12/2017 deleting for hakmilik Asal
    public Resolution popupDeleteHakmilikAsal() {
        idH = getContext().getRequest().getParameter("idH");
        idHA = getContext().getRequest().getParameter("idHA");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            setHakmilikAsal(pService.findByidHidHA(idH, idHA));
        }
        return new JSP("daftar/pembetulan/deleteHakmilikAsal.jsp").addParameter("tab", "true");

    }

    public Resolution popupSebelumkini() {
        idH = getContext().getRequest().getParameter("idH");
        idHS = getContext().getRequest().getParameter("idHS");
        if (idH != null) {
            hakmilik = hakmilikDAO.findById(idH);
            hakmilikSebelumListLama = pService.findByidHidHSList(idH, idHS);
            if (permohonanPembetulanHakmilik != null) {
//                alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
//                alert("Null", "PembetulanHakmilikBetuL");
            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/editHakmilikSebelumkini.jsp").addParameter("popup", "true");

    }

    public Resolution popupAsas() {

        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (getHakmilik().getIdHakmilikInduk() != null) {
                setFlagHakmilikStrata(true);
            } else {
                setFlagHakmilikStrata(false);
            }
            String kodBpm = getHakmilik().getBandarPekanMukim().getbandarPekanMukim();
            String kodDaerah = getHakmilik().getDaerah().getKod();
            System.out.print("Kod Daerah" + kodDaerah);
            System.out.print("Kod Daerah" + kodBpm);
            KodBandarPekanMukim kbpm = kodBpmDAO.findById(Integer.parseInt(kodBpm));
            if (kbpm != null) {
                String bpm = kbpm.getbandarPekanMukim();
                listKodSeksyenByBpm = regService.getSenaraiKodSeksyenByBPM(bpm, kodDaerah);
                if (listKodSeksyenByBpm.size() > 0) {
                }
            }
            if (permohonanPembetulanHakmilik != null) {
                if (permohonanPembetulanHakmilik.getTarikhDaftar() != null) {
                    tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0, 10);
                }
                if (permohonanPembetulanHakmilik.getTarikhLuput() != null) {
                    tarikhLuput = dateFormat.format(permohonanPembetulanHakmilik.getTarikhLuput()).substring(0, 10);
                }
                if (permohonanPembetulanHakmilik.getTarikhDaftarHakmilikAsal() != null) {
                    tarikhDaftarAsal = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftarHakmilikAsal()).substring(0, 10);
                }
                if (permohonanPembetulanHakmilik.getSyaratNyata() != null) {
                    kod_syarat = permohonanPembetulanHakmilik.getSyaratNyata().getKod();
                }
                if (permohonanPembetulanHakmilik.getUomLama() != null) {
                    kodUOMLama = permohonanPembetulanHakmilik.getUomLama().getKod();
                }
                if (permohonanPembetulanHakmilik.getUom() != null) {
                    kodUOM = permohonanPembetulanHakmilik.getUom().getKod();
                }
                if (permohonanPembetulanHakmilik.getSekatanKepentingan() != null) {
                    kod_sekatan = permohonanPembetulanHakmilik.getSekatanKepentingan().getKod();
                }
                if (permohonanPembetulanHakmilik.getKodLot() != null) {
                    kodLot = permohonanPembetulanHakmilik.getKodLot().getKod();
                }
                if (permohonanPembetulanHakmilik.getRizab() != null) {
                    rizab_kod = String.valueOf(permohonanPembetulanHakmilik.getRizab().getKod());
                }
                if (permohonanPembetulanHakmilik.getKategoriTanah() != null) {
                    kategoriTanah = permohonanPembetulanHakmilik.getKategoriTanah().getKod();
                }
                if (permohonanPembetulanHakmilik.getKegunaanTanah() != null) {
                    gunatanah = permohonanPembetulanHakmilik.getKegunaanTanah().getKod();
                }

//           alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

//         alert("Null", "PembetulanHakmilikBetuL");
            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/popup_editAsas.jsp").addParameter("popup", "true");
    }

    public Resolution asalHakmilik() {
        return new JSP("daftar/pembetulan/pembetul_maklumatHakmilikAsal.jsp").addParameter("tab", "true");
    }

    public Resolution sebelumkiniHakmilik() {
        return new JSP("daftar/pembetulan/pembetul_maklumatHakmilikSebelumkini.jsp").addParameter("tab", "true");
    }

    public Resolution tambahHakmilikAsal() {
        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (permohonanPembetulanHakmilik != null) {
                if (permohonanPembetulanHakmilik.getTarikhDaftar() != null) {
                    tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0, 10);
                }
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/tambahHakmilikAsal.jsp").addParameter("popup", "true");
    }

    public Resolution tambahHakmilikSebelum() {
        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (permohonanPembetulanHakmilik != null) {
//                 if(permohonanPembetulanHakmilik.getTarikhDaftar() != null){
//                tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0,10);}
//           alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/tambahHakmilikSebelum.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPihakBerkepentingan() {
        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (permohonanPembetulanHakmilik != null) {
//                 if(permohonanPembetulanHakmilik.getTarikhDaftar() != null){
//                tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0,10);}
//           alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/tambahPihakBerkepentingan.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPihakBerkepentinganWaris() {
        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (permohonanPembetulanHakmilik != null) {
//                 if(permohonanPembetulanHakmilik.getTarikhDaftar() != null){
//                tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0,10);}
//           alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/tambahPihakBerkepentinganWaris.jsp").addParameter("popup", "true");
    }

    public Resolution tambahPihakBerkepentinganTerlibat() {
        idH = getContext().getRequest().getParameter("idH");
        if (idH != null) {
            setHakmilik(hakmilikDAO.findById(idH));
            if (permohonanPembetulanHakmilik != null) {
//                 if(permohonanPembetulanHakmilik.getTarikhDaftar() != null){
//                tarikhDaftar = dateFormat.format(permohonanPembetulanHakmilik.getTarikhDaftar()).substring(0,10);}
//           alert("test POPASS", permohonanPembetulanHakmilik.getIdBetul());
            } else {
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();

            }
            LOG.info(permohonanPembetulanHakmilik);

        }
        return new JSP("daftar/pembetulan/tambahPihakBerkepentinganTerlibat.jsp").addParameter("popup", "true");
    }

    public Resolution saveBetul() throws ParseException {
        String error = "";
        String msg = "";
        idH = getContext().getRequest().getParameter("idH");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        tarikhDaftar = getContext().getRequest().getParameter("tarikhDaftar");
        statusHakmilikKod = getContext().getRequest().getParameter("statusHakmilikKod");
        kodseksyen = getContext().getRequest().getParameter("kodseksyen");
//        String kod_syarat = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.syaratNyata.kod");
//        String kodUOMLama = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.uomLama.kod");
//        String kodUOM = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.uom.kod");
        String noLot = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.noLot");
        String tarikhDaftarAsal = getContext().getRequest().getParameter("tarikhDaftarAsal");
//        String kod_sekatan = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.sekatanKepentingan.kod");
        String noFail = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.noFail");
        String lokasi = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.lokasi");
        String luasLama = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.luasLama");
        String luas = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.luas");
//        String kodLot =  getContext().getRequest().getParameter("permohonanPembetulanHakmilik.kodLot.kod");
        String noLitho = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.noLitho");
        String noPelan = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.noPelan");
        String noPU = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.noPU");
        String tempohPengangan = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.tempohPengangan");
//        String rizab_kod = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.rizab.kod");     
//        String kategoriTanah = getContext().getRequest().getParameter("permohonanPembetulanHakmilik.kategoriTanah.kod");           

        Hakmilik hm = pService.findHakmilik(idH);
        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idH);
        if (statusHakmilikKod != null) {
            kodStaHak = kodStatusHakmilikDAO.findById(statusHakmilikKod);
        }
        permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), idH);

        if (permohonanPembetulanHakmilik != null) {

            LOG.info("Update Existing Record - saveBetul");
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            //1
            permohonanPembetulanHakmilik.setIdHakmilik(idH);
            //2
            permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
            //3
            permohonanPembetulanHakmilik.setInfoAudit(info);
            //4
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            //5
            if (StringUtils.isNotBlank(tarikhDaftar)) {
                trhDaftar = dateFormat.parse(tarikhDaftar);
                permohonanPembetulanHakmilik.setTarikhDaftar(trhDaftar);
                permohonanPembetulanHakmilik.setTarikhDaftarSemasa(hm.getTarikhDaftar());
            }
            //6
            if (StringUtils.isNotBlank(tarikhLuput)) {
                trhLuput = dateFormat.parse(tarikhLuput);
                permohonanPembetulanHakmilik.setTarikhLuput(trhLuput);
                permohonanPembetulanHakmilik.setTarikhLuputSemasa(hm.getTarikhLuput());
            }
            //7
            if (StringUtils.isNotBlank(tarikhDaftarAsal)) {
                trhDaftarHakmilikAsal = dateFormat.parse(tarikhDaftarAsal);
                permohonanPembetulanHakmilik.setTarikhDaftarHakmilikAsal(dateFormat.parse(tarikhDaftarAsal));
                permohonanPembetulanHakmilik.setTarikhDaftarHakmilikAsalSemasa(hm.getTarikhDaftarAsal());
            }
            //8
            if (StringUtils.isNotBlank(kodseksyen)) {
                KodSeksyen ks = new KodSeksyen();
                ks.setKod(Integer.valueOf(kodseksyen));
                permohonanPembetulanHakmilik.setSeksyen(ks);
                permohonanPembetulanHakmilik.setSeksyenSemasa(hm.getSeksyen());
            }
            //9
            //permohonanPembetulanHakmilik.setTarikhLuput(trhLuput);
            //10
            //permohonanPembetulanHakmilik.setTarikhLuputSemasa(hm.getTarikhLuput());
            //11
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            //12
            if (kodStaHak != null) {
                permohonanPembetulanHakmilik.setStatusHakmilik(kodStaHak);
                permohonanPembetulanHakmilik.setStatusHakmilikSemasa(hm.getKodStatusHakmilik());
            }
            //13
            if (StringUtils.isNotBlank(kod_syarat)) {
                KodSyaratNyata ksn = new KodSyaratNyata();
                ksn.setKod(kod_syarat);
                permohonanPembetulanHakmilik.setSyaratNyataSemasa(hm.getSyaratNyata());
                permohonanPembetulanHakmilik.setSyaratNyata(ksn);
            }
            //14
            if ((StringUtils.isNotBlank(kod_sekatan))) {
                KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                ksk.setKod(kod_sekatan);
                permohonanPembetulanHakmilik.setSekatanKepentinganSemasa(hm.getSekatanKepentingan());
                permohonanPembetulanHakmilik.setSekatanKepentingan(ksk);
            }
            //15
            if (StringUtils.isNotBlank(noLot)) {
                permohonanPembetulanHakmilik.setNoLot(noLot);
                permohonanPembetulanHakmilik.setNoLotSemasa(hm.getNoLot());
            }
            //16
            if (StringUtils.isNotBlank(noFail)) {
                permohonanPembetulanHakmilik.setNoFail(noFail);
                permohonanPembetulanHakmilik.setNoFailSemasa(hm.getNoFail());
            }
            //17
            if (StringUtils.isNotBlank(lokasi)) {
                permohonanPembetulanHakmilik.setLokasi(lokasi);
                permohonanPembetulanHakmilik.setLokasiSemasa(hm.getLokasi());
            }
            //18
            if (StringUtils.isNotBlank(luasLama)) {
                permohonanPembetulanHakmilik.setLuasLama(luasLama);
                permohonanPembetulanHakmilik.setLuasLamaSemasa(hm.getLuasLama());
            }
            //19
            if (StringUtils.isNotBlank(noLitho)) {
                permohonanPembetulanHakmilik.setNoLitho(noLitho);
                permohonanPembetulanHakmilik.setNoLithoSemasa(hm.getNoLitho());
            }
            //20
            if (StringUtils.isNotBlank(noPelan)) {
                permohonanPembetulanHakmilik.setNoPelan(noPelan);
                permohonanPembetulanHakmilik.setNoPelanSemasa(hm.getNoPelan());
            }
            //21
            if (StringUtils.isNotBlank(noPU)) {
                permohonanPembetulanHakmilik.setNoPU(noPU);
                permohonanPembetulanHakmilik.setNoPUSemasa(hm.getNoPu());
            }
            //22
            if (StringUtils.isNotBlank(luas)) {
                BigDecimal b2 = new BigDecimal(luas);
                permohonanPembetulanHakmilik.setLuas(b2);
                permohonanPembetulanHakmilik.setLuasSemasa(hm.getLuas());
            }
            //====================================================
            //23
            if (StringUtils.isNotBlank(kodUOMLama)) {
                KodUOM ku = new KodUOM();
                ku.setKod(kodUOMLama);
                permohonanPembetulanHakmilik.setUomLama(ku);
                permohonanPembetulanHakmilik.setUomLamaSemasa(hm.getKodUnitLuasLama());
            }
//                //24
            if (StringUtils.isNotBlank(kodUOM)) {
                KodUOM ku = new KodUOM();
                ku.setKod(kodUOM);
                permohonanPembetulanHakmilik.setUom(ku);
                permohonanPembetulanHakmilik.setUomSemasa(hm.getKodUnitLuas());
            }
            //25
            if (StringUtils.isNotBlank(kodLot)) {
                KodLot kl = new KodLot();
                kl.setKod(kodLot);
                permohonanPembetulanHakmilik.setKodLot(kl);
                permohonanPembetulanHakmilik.setKodLotSemasa(hm.getLot());
            }
            //26
            if (StringUtils.isNotBlank(tempohPengangan)) {
                permohonanPembetulanHakmilik.setTempohPengangan(Integer.valueOf(tempohPengangan));;
                permohonanPembetulanHakmilik.setTempohPenganganSemasa(hm.getTempohPegangan());
            }
            //27
            if (StringUtils.isNotBlank(rizab_kod)) {
                KodRizab kr = new KodRizab();
                kr.setKod(Integer.valueOf(rizab_kod));
                permohonanPembetulanHakmilik.setRizabSemasa(hm.getRizab());
                permohonanPembetulanHakmilik.setRizab(kr);
            }
            //28
            if (StringUtils.isNotBlank(kategoriTanah)) {
                KodKategoriTanah kt = new KodKategoriTanah();
                kt.setKod(kategoriTanah);
                permohonanPembetulanHakmilik.setKategoriTanahSemasa(hm.getKategoriTanah());
                permohonanPembetulanHakmilik.setKategoriTanah(kt);
            }
            //29
            if (StringUtils.isNotBlank(gunatanah)) {
                KodKegunaanTanah kg = new KodKegunaanTanah();
                kg.setKod(gunatanah);
                permohonanPembetulanHakmilik.setKegunaanTanah(kg);
                permohonanPembetulanHakmilik.setKegunaanTanahSemasa(hm.getKegunaanTanah());
            }
            pService.updateBetul(permohonanPembetulanHakmilik);
            msg = "Kemaskini Data Pembetulan Telah Berjaya";

        } else {
            PermohonanPembetulanHakmilik pph = new PermohonanPembetulanHakmilik();

            LOG.info("New Entry");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            //1
            pph.setIdHakmilik(idH);
            //2
            pph.setCawangan(peng.getKodCawangan());
            //3
            pph.setInfoAudit(info);
            //4
            pph.setPermohonan(permohonan);
            //5
            if (StringUtils.isNotBlank(tarikhDaftar)) {
                trhDaftar = dateFormat.parse(tarikhDaftar);
                pph.setTarikhDaftar(trhDaftar);
                pph.setTarikhDaftarSemasa(hm.getTarikhDaftar());
            }
            //6
            if (StringUtils.isNotBlank(tarikhLuput)) {
                trhLuput = dateFormat.parse(tarikhLuput);
                pph.setTarikhLuput(trhLuput);
                pph.setTarikhLuputSemasa(hm.getTarikhLuput());
            }
            //7
            if (StringUtils.isNotBlank(tarikhDaftarAsal)) {
                trhDaftarHakmilikAsal = dateFormat.parse(tarikhDaftarAsal);
                pph.setTarikhDaftarHakmilikAsal(trhDaftarHakmilikAsal);
                pph.setTarikhDaftarHakmilikAsalSemasa(hm.getTarikhDaftarAsal());
            }
            //8
            if (StringUtils.isNotBlank(kodseksyen)) {
                KodSeksyen ks = new KodSeksyen();
                ks.setKod(Integer.valueOf(kodseksyen));
                pph.setSeksyen(ks);
                pph.setSeksyenSemasa(hm.getSeksyen());
            }
            //9
            //10
            //11
            pph.setHakmilik(hakmilikPermohonan);
            //12
            if (kodStaHak != null) {
                pph.setStatusHakmilik(kodStaHak);
            }
            //13
            if (StringUtils.isNotBlank(kod_syarat)) {
                KodSyaratNyata ksn = new KodSyaratNyata();
                ksn.setKod(kod_syarat);
                pph.setSyaratNyataSemasa(hm.getSyaratNyata());
                pph.setSyaratNyata(ksn);
            }
            //14
            if (StringUtils.isNotBlank(kod_sekatan)) {
                KodSekatanKepentingan ksk = new KodSekatanKepentingan();
                ksk.setKod(kod_sekatan);
                pph.setSekatanKepentinganSemasa(hm.getSekatanKepentingan());
                pph.setSekatanKepentingan(ksk);
            }
            //15               
            if (StringUtils.isNotBlank(noLot)) {
                pph.setNoLot(noLot);
                pph.setNoLotSemasa(hm.getNoLot());
            }
            //16
            if (StringUtils.isNotBlank(noFail)) {
                pph.setNoFail(noFail);
                pph.setNoFailSemasa(hm.getNoFail());
            }
            //17
            if (StringUtils.isNotBlank(lokasi)) {
                pph.setLokasi(lokasi);
                pph.setLokasiSemasa(hm.getLokasi());
            }
            //18
            if (StringUtils.isNotBlank(luasLama)) {
                pph.setLuasLama(luasLama);
                pph.setLuasLamaSemasa(hm.getLuasLama());
            }
            //19
            if (StringUtils.isNotBlank(noLitho)) {
                pph.setNoLitho(noLitho);
                pph.setNoLithoSemasa(hm.getNoLitho());
            }
            //20
            if (StringUtils.isNotBlank(noPelan)) {
                pph.setNoPelan(noPelan);
                pph.setNoPelanSemasa(hm.getNoPelan());
            }
            //21
            if (StringUtils.isNotBlank(noPU)) {
                pph.setNoPU(noPU);
                pph.setNoPUSemasa(hm.getNoPu());
            }
            //22
            if (StringUtils.isNotBlank(luas)) {
                BigDecimal b2 = new BigDecimal(luas);
                pph.setLuas(b2);
                pph.setLuasSemasa(hm.getLuas());
            }
            //23                
            if (StringUtils.isNotBlank(kodUOMLama)) {
                pph.setUomLama(kodUOMDAO.findById(kodUOMLama));
                pph.setUomLamaSemasa(hm.getKodUnitLuasLama());
            }
            //24
            if (StringUtils.isNotBlank(kodUOM)) {
                pph.setUom(kodUOMDAO.findById(kodUOM));
                pph.setUomSemasa(hm.getKodUnitLuas());
            }
            //25                
            if (StringUtils.isNotBlank(kodLot)) {
                pph.setKodLot(kodLotDAO.findById(kodLot));
                pph.setKodLotSemasa(hm.getLot());
            }
            //26
            if (StringUtils.isNotBlank(tempohPengangan)) {
                pph.setTempohPengangan(Integer.valueOf(tempohPengangan));;
                pph.setTempohPenganganSemasa(hm.getTempohPegangan());
            }
            //27
            if (StringUtils.isNotBlank(rizab_kod)) {
                KodRizab kr = new KodRizab();
                kr.setKod(Integer.valueOf(rizab_kod));
                pph.setRizabSemasa(hm.getRizab());
                pph.setRizab(kr);
            }
            //28
            if (StringUtils.isNotBlank(kategoriTanah)) {
                KodKategoriTanah kt = new KodKategoriTanah();
                kt.setKod(kategoriTanah);
                pph.setKategoriTanahSemasa(hm.getKategoriTanah());
                pph.setKategoriTanah(kt);
            }
            //29
            if (StringUtils.isNotBlank(gunatanah)) {
                KodKegunaanTanah kg = new KodKegunaanTanah();
                kg.setKod(gunatanah);
                pph.setKegunaanTanah(kg);
                pph.setKegunaanTanahSemasa(hm.getKegunaanTanah());
            }
            pService.simpanBetul(pph);
            msg = "Kemasukan Data Pembetulan Telah Berjaya";
        }

        return new RedirectResolution(pembetulanActionBean.class, "messagerME").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveBetulAsal() throws ParseException {
        String error = "";
        String msg = "";
        idH = getContext().getRequest().getParameter("idH");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        tarikhDaftarHakmilikAsal = getContext().getRequest().getParameter("tarikhDaftarHakmilikAsal");
        idHakmilikAsal = getContext().getRequest().getParameter("idHakmilikAsal");

        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idH);
        permohonanPembetulanHakmilik = pService.findByidHakmilikAsal(permohonan.getIdPermohonan(), idHakmilikAsal);

        if (permohonanPembetulanHakmilik != null) {

            LOG.info("Update Existing Record - saveBetulAsal");
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
            permohonanPembetulanHakmilik.setInfoAudit(info);
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            permohonanPembetulanHakmilik.setIdHakmilikAsal(idHakmilikAsal);
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanHakmilik.setIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            if (!tarikhDaftarHakmilikAsal.isEmpty()) {
                trhDaftarHakmilikAsal = dateFormat.parse(tarikhDaftarHakmilikAsal);
                permohonanPembetulanHakmilik.setTarikhDaftarHakmilikAsal(trhDaftarHakmilikAsal);
            }
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            pService.updateBetul(permohonanPembetulanHakmilik);
            addSimpleMessage("Kemaskini Data Pembetulan Telah Berjaya");
        } else {

            LOG.info("New Entry");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            PermohonanPembetulanHakmilik permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
            permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
            permohonanPembetulanHakmilik.setInfoAudit(info);
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            permohonanPembetulanHakmilik.setIdHakmilikAsal(idHakmilikAsal);
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanHakmilik.setIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            if (!tarikhDaftarHakmilikAsal.isEmpty()) {
                trhDaftarHakmilikAsal = dateFormat.parse(tarikhDaftarHakmilikAsal);
                permohonanPembetulanHakmilik.setTarikhDaftarHakmilikAsal(trhDaftarHakmilikAsal);
            }
            pService.simpanBetul(permohonanPembetulanHakmilik);
            addSimpleMessage("Kemasukan Data Pembetulan Telah Berjaya");
        }

        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution saveBetulSebelum() throws ParseException {
        String error = "";
        String msg = "";
        idH = getContext().getRequest().getParameter("idH");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idHakmilikSebelum = getContext().getRequest().getParameter("idHakmilikSebelum");
        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idH);
        hakmilikSebelumPermohonan = regService.searchMohonHakmilikSblmByIdMohonIdHakmilikSej(idHakmilikSebelum, permohonan.getIdPermohonan());
        permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
        if (hakmilikSebelumPermohonan != null) {
            LOG.info("Update Existing Record - saveBetulSebelum");

            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());

            hakmilikSebelumPermohonan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            hakmilikSebelumPermohonan.setHakmilik(hakmilikPermohonan.getHakmilik());
            hakmilikSebelumPermohonan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikSebelumPermohonan.setHakmilikSejarah(idHakmilikSebelum);
            hakmilikSebelumPermohonan.setPermohonan(permohonan);
            hakmilikSebelumPermohonan.setInfoAudit(info);
            regService.simpanMohonHakmilikSebelum(hakmilikSebelumPermohonan);
            addSimpleMessage("Kemaskini Data Pembetulan Telah Berjaya");
        } else {
            LOG.info("New Entry...");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            HakmilikSebelumPermohonan hakmilikSebelumPermohonan = new HakmilikSebelumPermohonan();

            hakmilikSebelumPermohonan.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            hakmilikSebelumPermohonan.setHakmilik(hakmilikPermohonan.getHakmilik());
            hakmilikSebelumPermohonan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikSebelumPermohonan.setHakmilikSejarah(idHakmilikSebelum);
            hakmilikSebelumPermohonan.setPermohonan(permohonan);
            hakmilikSebelumPermohonan.setInfoAudit(info);
            regService.simpanMohonHakmilikSebelum(hakmilikSebelumPermohonan);
            addSimpleMessage("Kemasukan Data Pembetulan Telah Berjaya");

        }

        if (permohonanPembetulanHakmilik != null) {
            permohonanPembetulanHakmilik.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            permohonanPembetulanHakmilik.setIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanHakmilik.setIdHakmilikAsalSemasa(idHakmilikSebelum);
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            permohonanPembetulanHakmilik.setInfoAudit(info);
            pService.simpanBetul(permohonanPembetulanHakmilik);
        } else {
            permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
            permohonanPembetulanHakmilik.setCawangan(hakmilikPermohonan.getHakmilik().getCawangan());
            permohonanPembetulanHakmilik.setIdHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanHakmilik.setIdHakmilikAsalSemasa(idHakmilikSebelum);
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            permohonanPembetulanHakmilik.setInfoAudit(info);
            pService.simpanBetul(permohonanPembetulanHakmilik);
        }
        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);
    }

//    Urusan BETC
    public Resolution saveCukai() throws ParseException {
        int counter = 0;
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        LOG.debug("hakmilikPermohonanList Size:" + hakmilikPermohonanList.size());
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            Hakmilik hm = pService.findHakmilik(hp.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList:" + cukai[counter] + " : idhakmilik : " + hp.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList:" + cukaiThn[counter] + " : idhakmilik : " + hp.getHakmilik().getIdHakmilik());
            String check = getContext().getRequest().getParameter("flag");

            if (check.equalsIgnoreCase("UC")) {
                if (StringUtils.isNotBlank(cukai[counter])) {
                    if (Double.valueOf(cukai[counter]) < 6) {
                        error = "Cukai Hakmilik: " + hp.getHakmilik().getIdHakmilik() + " yang dimasukkan kurang dari RM 6";
                        return new RedirectResolution(pembetulanActionBean.class, "betulCukai").addParameter("error", error).addParameter("message", msg);
                    }
                }

                if (StringUtils.isNotBlank(cukaiThn[counter])) {
                    if (Double.valueOf(cukaiThn[counter]) < 6) {
                        error = "Cukai Tahunan Hakmilik: " + hp.getHakmilik().getIdHakmilik() + " yang dimasukkan kurang dari RM 6";
                        return new RedirectResolution(pembetulanActionBean.class, "betulCukai").addParameter("error", error).addParameter("message", msg);
                    }
                }
                permohonan.setSebab("");
                permohonanService.saveOrUpdate(permohonan);
            } else if (check.equalsIgnoreCase("C")) {
                permohonan.setSebab("Membenarkan Cukai Baru/Cukai Tahunan Baru bawah RM6 untuk kes remisyen.");
                permohonanService.saveOrUpdate(permohonan);
            }

            if (permohonanPembetulanHakmilik != null) {
                LOG.info("Update Existing Record - saveCukai");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                if (hm.getCukai() != null) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(hm.getCukai());
                }
                if (StringUtils.isNotBlank(cukai[counter])) {
                    permohonanPembetulanHakmilik.setCukai(new BigDecimal(cukai[counter]));
                }
                if (!StringUtils.isNotBlank(cukai[counter])) {
                    permohonanPembetulanHakmilik.setCukai(null);
                }
                if (StringUtils.isNotBlank(cukaiThn[counter])) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(new BigDecimal(cukaiThn[counter]));
                }
                if (!StringUtils.isNotBlank(cukaiThn[counter])) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(null);
                }
                pService.updateBetul(permohonanPembetulanHakmilik);
                msg = "Kemaskini Data Pembetulan Telah Berjaya";
            } else {
                LOG.info("New Entry");
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                if (hm.getCukai() != null) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(hm.getCukai());
                }
                if (StringUtils.isNotBlank(cukai[counter])) {
                    permohonanPembetulanHakmilik.setCukai(new BigDecimal(cukai[counter]));
                }
                if (!StringUtils.isNotBlank(cukai[counter])) {
                    permohonanPembetulanHakmilik.setCukai(null);
                }
                if (StringUtils.isNotBlank(cukaiThn[counter])) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(new BigDecimal(cukaiThn[counter]));
                }
                if (!StringUtils.isNotBlank(cukaiThn[counter])) {
                    permohonanPembetulanHakmilik.setCukaiSemasa(null);
                }
                pService.simpanBetul(permohonanPembetulanHakmilik);
                msg = "Kemasukan Data Pembetulan Telah Berjaya";

            }
            counter = counter + 1;
        }
        return new RedirectResolution(pembetulanActionBean.class, "betulCukai").addParameter("error", error).addParameter("message", msg);
    }

//    PB SECTION
    //TODO:FILTER PB
    public Resolution senaraiPemilik() {
        hpBerkepentinganPemilikList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganWarisList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganKAVEATList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganPGList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganPIList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganPJList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganTENList = new ArrayList<HakmilikPihakBerkepentingan>();
        hpBerkepentinganPBList = new ArrayList<HakmilikPihakBerkepentingan>();

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//         alert("Count =", hakmilikPermohonanList.size());
        for (HakmilikPermohonan hpMOHON : hakmilikPermohonanList) {

            hpBerkepentinganPemilikList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "PM"));
            hpBerkepentinganWarisList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "WAR"));
            hpBerkepentinganKAVEATList.addAll(pService.findKAVEAT(hpMOHON.getHakmilik().getIdHakmilik()));
            hpBerkepentinganPGList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "PG"));
            hpBerkepentinganPIList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "PI"));
            hpBerkepentinganPJList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "PJ"));
            hpBerkepentinganTENList.addAll(pService.findPemilikByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik(), "PT"));

            hpBerkepentinganPBList.addAll(pService.findPBByidHakmilik(hpMOHON.getHakmilik().getIdHakmilik()));
        }

        return new JSP("daftar/pembetulan/senaraiPB.jsp").addParameter("tab", "true");
    }

    //    Save Pembetulan Pemilik
    public Resolution saveBetulPB() {
        String error = "Muhahahaha";
        String msg = "Muhahahaha";
        LOG.info("Start Save betul Pemilik");
//      idH = getContext().getRequest().getParameter("idH");
        //    butiran PB
        LOG.info("::Butiran PB::");
        if (StringUtils.isNotBlank(nama)) {
            LOG.info("Nama::" + nama);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(jenisPengenalan)) {
            LOG.info("Jenis Pengenalan::" + jenisPengenalan);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(noPengenalan)) {
            LOG.info("No Pengenalan::" + noPengenalan);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(bangsa)) {
            LOG.info("Bangsa::" + bangsa);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(jantina)) {
            LOG.info("Bangsa::" + jantina);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(warganegara)) {
            LOG.info("Warganegara::" + warganegara);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(jenisPB)) {
            LOG.info("Jenis PB::" + jenisPB);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(kumpulanPB)) {
            LOG.info("Kumpulan PB::" + kumpulanPB);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(noSuratAmanah)) {
            LOG.info("Ruj. Surat Amanah::" + noSuratAmanah);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(penyebut)) {
            LOG.info("Penyebut::" + penyebut);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(pembilang)) {
            LOG.info("Pembilang::" + pembilang);
            flagBetul = true;
        }
// alamat daftar
        LOG.info("::Alamat Berdaftar::");
        if (StringUtils.isNotBlank(alamat1)) {
            LOG.info(alamat1);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(alamat2)) {
            LOG.info(alamat2);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(alamat3)) {
            LOG.info(alamat3);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(alamat4)) {
            LOG.info(alamat4);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(poskod)) {
            LOG.info(poskod);
            flagBetul = true;
        }
        if (StringUtils.isNotBlank(negeri)) {
            LOG.info(negeri);
            flagBetul = true;
        }
// alamat surat menyurat
        LOG.info("::Alamat Surat Menyurat::");
        if (StringUtils.isNotBlank(alamatSurat1)) {
            LOG.info(alamatSurat1);
        }
        if (StringUtils.isNotBlank(alamatSurat2)) {
            LOG.info(alamatSurat2);
        }
        if (StringUtils.isNotBlank(alamatSurat3)) {
            LOG.info(alamatSurat3);
        }
        if (StringUtils.isNotBlank(alamatSurat4)) {
            LOG.info(alamatSurat4);
        }
        if (StringUtils.isNotBlank(poskodSurat)) {
            LOG.info(poskodSurat);
        }
        if (StringUtils.isNotBlank(negeriSurat)) {
            LOG.info(negeriSurat);
        }
//      return new RedirectResolution(pembetulanActionBean.class, "senaraiPemilik");
        return new RedirectResolution(pembetulanActionBean.class, "messagerME").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution editPemilik() {
        pihak = pihakDAO.findById(Long.parseLong(idPihak));
        hpBerkepentingan = pService.findByidPihak(idPihak, kodPB, idH);
        return new JSP("daftar/pembetulan/pembetulanPemilik.jsp").addParameter("popup", "true");
    }

    public Resolution maklumatTarikhLuput() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        int i = 0;
        tarikhluputbaru = new String[hakmilikPermohonanList.size()];
        tempohPeganganBaru = new String[hakmilikPermohonanList.size()];
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                tarikhluputbaru[i] = dateFormat.format(permohonanPembetulanHakmilik.getTarikhLuput()).substring(0, 10);
                tempohPeganganBaru[i] = String.valueOf(permohonanPembetulanHakmilik.getTempohPengangan());
            }
            i++;
        }

        return new JSP("daftar/pembetulan/betulTarikhLuput.jsp").addParameter("tab", "true");
    }

    //    Urusan BETLP
    public Resolution saveTarikhLuput() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        int counter = 0;
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        LOG.debug("hakmilikPermohonanList Size:" + hakmilikPermohonanList.size());
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            Hakmilik hm = pService.findHakmilik(hp.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList:" + tarikhluputbaru[counter] + " : idhakmilik : " + hp.getHakmilik().getIdHakmilik());
            int lid = Integer.valueOf(tempohPeganganBaru[counter]);
            if (lid <= 99) {
                if (permohonanPembetulanHakmilik != null) {

                    LOG.info("Update Existing Record - saveTarikhLuput");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                    permohonanPembetulanHakmilik.setInfoAudit(info);
                    permohonanPembetulanHakmilik.setPermohonan(permohonan);
                    permohonanPembetulanHakmilik.setHakmilik(hp);
                    permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                    permohonanPembetulanHakmilik.setTarikhLuput(dateFormat.parse(tarikhluputbaru[counter]));
                    permohonanPembetulanHakmilik.setTempohPengangan(lid);
                    permohonanPembetulanHakmilik.setTarikhLuputSemasa(hm.getTarikhLuput());
                    permohonanPembetulanHakmilik.setTempohPenganganSemasa(hm.getTempohPegangan());
                    pService.updateBetul(permohonanPembetulanHakmilik);
                    msg = "Kemaskini Data Pembetulan Telah Berjaya";
                } else {
                    LOG.info("New Entry");
                    permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                    permohonanPembetulanHakmilik.setInfoAudit(info);
                    permohonanPembetulanHakmilik.setPermohonan(permohonan);
                    permohonanPembetulanHakmilik.setHakmilik(hp);
                    permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                    permohonanPembetulanHakmilik.setTarikhLuput(dateFormat.parse(tarikhluputbaru[counter]));
                    permohonanPembetulanHakmilik.setTempohPengangan(lid);
                    permohonanPembetulanHakmilik.setTarikhLuputSemasa(hm.getTarikhLuput());
                    permohonanPembetulanHakmilik.setTempohPenganganSemasa(hm.getTempohPegangan());
                    pService.simpanBetul(permohonanPembetulanHakmilik);
                    msg = "Kemasukan Data Pembetulan Telah Berjaya";
                }

            } else {
                error = "Tempoh Pegangan bagi hakmilik : " + hp.getHakmilik().getIdHakmilik() + " melebihi 99 tahun";
                return new RedirectResolution(pembetulanActionBean.class, "maklumatTarikhLuput").addParameter("error", error).addParameter("message", msg);
            }
            counter = counter + 1;
        }
        return new RedirectResolution(pembetulanActionBean.class, "maklumatTarikhLuput").addParameter("error", error).addParameter("message", msg);
    }

    //    Urusan BETST
    public Resolution maklumatStatus() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        int i = 0;
        statusHakmilikbaru = new String[hakmilikPermohonanList.size()];
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                statusHakmilikbaru[i] = String.valueOf(permohonanPembetulanHakmilik.getHakmilik().getHakmilik().getKodStatusHakmilik().getKod());
            }
            i++;
        }
        return new JSP("daftar/pembetulan/betulStatusHakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution saveStatus() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        int counter = 0;
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        LOG.debug("hakmilikPermohonanList Size:" + hakmilikPermohonanList.size());
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            Hakmilik hm = pService.findHakmilik(hp.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList:" + statusHakmilikbaru[counter] + " : idhakmilik : " + hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                LOG.info("Update Existing Record - saveStatus");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                permohonanPembetulanHakmilik.setStatusHakmilikSemasa(hm.getKodStatusHakmilik());
                if (StringUtils.isNotBlank(statusHakmilikbaru[counter])) {
                    KodStatusHakmilik ks = new KodStatusHakmilik();
                    ks.setKod(statusHakmilikbaru[counter]);
                    permohonanPembetulanHakmilik.setStatusHakmilik(ks);
                }
                pService.updateBetul(permohonanPembetulanHakmilik);
                msg = "Data Pembetulan Telah Berjaya";
            } else {
                LOG.info("New Entry");
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                permohonanPembetulanHakmilik.setStatusHakmilikSemasa(hm.getKodStatusHakmilik());
                if (StringUtils.isNotBlank(statusHakmilikbaru[counter])) {
                    KodStatusHakmilik ks = new KodStatusHakmilik();
                    ks.setKod(statusHakmilikbaru[counter]);
                    permohonanPembetulanHakmilik.setStatusHakmilik(ks);
                }
                pService.simpanBetul(permohonanPembetulanHakmilik);
                msg = "Kemasukan Data Pembetulan Telah Berjaya";

            }
            counter = counter + 1;
        }
        return new RedirectResolution(pembetulanActionBean.class, "maklumatStatus").addParameter("error", error).addParameter("message", msg);
    }

    //    Urusan BETP
    public Resolution maklumatPelan() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        int i = 0;
        noPelan = new String[hakmilikPermohonanList.size()];
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                noPelan[i] = permohonanPembetulanHakmilik.getNoPelan();
            }
            i++;
        }
        return new JSP("daftar/pembetulan/betulNoPelan.jsp").addParameter("tab", "true");
    }

    public Resolution savePelan() throws ParseException {
        String error = "";
        String msg = "";
        int counter = 0;
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        LOG.debug("hakmilikPermohonanList Size:" + hakmilikPermohonanList.size());
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            Hakmilik hm = pService.findHakmilik(hp.getHakmilik().getIdHakmilik());
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
            LOG.debug("hakmilikPermohonanList:" + noPelan[counter] + " : idhakmilik : " + hp.getHakmilik().getIdHakmilik());
            if (permohonanPembetulanHakmilik != null) {
                LOG.info("Update Existing Record - savePelan");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                permohonanPembetulanHakmilik.setNoPelanSemasa(hm.getNoPelan());
                if (StringUtils.isNotBlank(noPelan[counter])) {
                    permohonanPembetulanHakmilik.setNoPelan(noPelan[counter]);
                }
                pService.updateBetul(permohonanPembetulanHakmilik);
                msg = "Kemaskini Data Pembetulan Telah Berjaya";
            } else {
                LOG.info("New Entry");
                permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
                permohonanPembetulanHakmilik.setInfoAudit(info);
                permohonanPembetulanHakmilik.setPermohonan(permohonan);
                permohonanPembetulanHakmilik.setHakmilik(hp);
                permohonanPembetulanHakmilik.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                permohonanPembetulanHakmilik.setNoPelanSemasa(hm.getNoPelan());
                if (StringUtils.isNotBlank(noPelan[counter])) {
                    permohonanPembetulanHakmilik.setNoPelan(noPelan[counter]);
                }
                pService.simpanBetul(permohonanPembetulanHakmilik);
                msg = "Kemasukan Data Pembetulan Telah Berjaya";

            }
            counter = counter + 1;
        }
        return new RedirectResolution(pembetulanActionBean.class, "maklumatPelan").addParameter("error", error).addParameter("message", msg);
    }

    //    Urusan BETEN
    public Resolution maklumatBatal() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/pembetulan/betulBatalEndosan.jsp").addParameter("tab", "true");
    }

    public Resolution saveBatalEndosan() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        for (String idUrusan : param) {
            hakmilikUrusan = pService.findUrusan(idUrusan);
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
            if (permohonanAtasPerserahan != null) {
                error = "Urusan Telah diPohon";
                return new RedirectResolution(pembetulanActionBean.class, "maklumatBatal").addParameter("error", error).addParameter("message", msg);
            } else {
                permohonanAtasPerserahan = new PermohonanAtasPerserahan();
                LOG.info("New Record - saveBatalEndosan");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanAtasPerserahan.setInfoAudit(info);
                permohonanAtasPerserahan.setPermohonan(permohonan);
                permohonanAtasPerserahan.setUrusan(hakmilikUrusan);
                pService.simpanAtasUrusan(permohonanAtasPerserahan);

            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";
        return new RedirectResolution(pembetulanActionBean.class, "maklumatBatal").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChanges() {
        String error = "";
        String msg = "";
        String idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        LOG.debug("idUrusan : " + idUrusan);
        if (idUrusan != null) {
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));

            if (permohonanAtasPerserahan != null) {
                pService.deleteUrusan(permohonanAtasPerserahan);
            }
            msg = "Data Telah Berjaya diHapuskan";
        }
        return new RedirectResolution(pembetulanActionBean.class, "maklumatBatal").addParameter("error", error).addParameter("message", msg);
    }

    //    Urusan BETHM
    public Resolution maklumatSilap() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/pembetulan/betulSilapEndosan.jsp").addParameter("tab", "true");
    }

    public Resolution saveSilapEndosan() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String[] param = getContext().getRequest().getParameterValues("idUrusan");
        for (String idUrusan : param) {
            hakmilikUrusan = pService.findUrusan(idUrusan);
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
            if (permohonanAtasPerserahan != null) {
                error = "Urusan Telah diPohon";
                return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
            } else {
                permohonanAtasPerserahan = new PermohonanAtasPerserahan();
                LOG.info("New Record - saveSilapEndosan");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanAtasPerserahan.setInfoAudit(info);
                permohonanAtasPerserahan.setPermohonan(permohonan);
                permohonanAtasPerserahan.setUrusan(hakmilikUrusan);
                pService.simpanAtasUrusan(permohonanAtasPerserahan);
            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";
        return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution betulSilapEndosan() throws ParseException, IllegalAccessException, InvocationTargetException {
        LOG.info("--- betulSilapEndosan() :: Start ---");
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();

        hakmilikUrusan = pService.findUrusan(idUrusan);
        permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
        hakmilikPermohonan = pService.findByIdHakmilik(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        PermohonanPembetulanHakmilik pph = pService.findByidPidHandidAu(String.valueOf(permohonan.getIdPermohonan()), String.valueOf(hakmilikPermohonan.getId()), String.valueOf(permohonanAtasPerserahan.getIdAtasUrusan()));

        LOG.info(hakmilikUrusan.getIdPerserahan());
        if (idHakmilikBaru != null) {
            List<HakmilikUrusan> listhu = pService.findUrusanByidHY(idHakmilikBaru);
            for (HakmilikUrusan hu : listhu) {
                LOG.info(hu.getIdPerserahan());
                if (hakmilikUrusan.getIdPerserahan().equals(hu.getIdPerserahan())) {
                    error = "Perserahan ini telah didaftarkan pada hakmilik " + idHakmilikBaru;
                    return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
                }
            }
        }

        if (hakmilikUrusan.getHakmilik().getIdHakmilik().equals(idHakmilikBaru)) {
            error = "ID Hakmilik yang dimasukkan adalah ID Hakmilik yang sama !!";
        } else if (pph != null) {
            LOG.info("Update Existing Record - betulSilapEndosan");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            pph.setCawangan(peng.getKodCawangan());
            pph.setInfoAudit(info);
            pph.setIdHakmilik(idHakmilikBaru);
            pph.setPermohonan(permohonan);
            pph.setHakmilik(hakmilikPermohonan);
            if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                pph.setIdHakmilikAsal(hakmilikUrusan.getHakmilik().getIdHakmilik()); // insert hakmilik asal in column id_hakmilik_asal
            }
            pph.setIdAtasUrusan(permohonanAtasPerserahan.getIdAtasUrusan());
            pService.updateBetul(pph);
            betulHakmilikPihakUpdate(); //update hakmilik pihak
            msg = "Kemaskini Data Pembetulan Telah Berjaya";
            return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
        } else {
            pph = new PermohonanPembetulanHakmilik();
            LOG.info("New Record - betulSilapEndosan");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            pph.setInfoAudit(info);
            pph.setCawangan(peng.getKodCawangan());
            pph.setIdHakmilik(idHakmilikBaru);
            pph.setHakmilik(hakmilikPermohonan);
            pph.setIdAtasUrusan(permohonanAtasPerserahan.getIdAtasUrusan());
            pph.setPermohonan(permohonan);
            if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                pph.setIdHakmilikAsal(hakmilikUrusan.getHakmilik().getIdHakmilik()); // insert hakmilik asal in column id_hakmilik_asal
            }
            pService.simpanBetul(pph);
            betulHakmilikPihakNew(); //copy hakmilik pihak from 'id_hakmilik lama'
            insertMohonHakmilik(idUrusan, idHakmilikBaru);
            insertMohonPihak(idUrusan, idHakmilikBaru);
            inserPemohon(idUrusan, idHakmilikBaru);
            insertMohonRujLuar(idUrusan, idHakmilikBaru);
            msg = "Kemasukan Data Pembetulan Telah Berjaya";
        }
        LOG.info("--- betulSilapEndosan() :: End ---");
        return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
    }

    public void insertMohonRujLuar(String idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        PermohonanRujukanLuar mrlLama = pService.findRujukanIDMohonHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        PermohonanRujukanLuar mrlBaru = pService.findRujukanIDMohonHakmilik(hu.getIdPerserahan(), idHakmilikBaru);

        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        if (mrlBaru == null) {

            PermohonanRujukanLuar mrl = new PermohonanRujukanLuar();

            Permohonan mhn = permohonanDAO.findById(hu.getIdPerserahan());
            Hakmilik hm = pService.findHakmilik(idHakmilikBaru);
            mrl.setPermohonan(mhn);
            mrl.setHakmilik(hm);
            mrl.setCawangan(mhn.getCawangan());
            mrl.setNoFail(mrlLama.getNoFail());
            mrl.setNoRujukan(mrlLama.getNoRujukan());
            mrl.setTarikhRujukan(mrlLama.getTarikhRujukan());
            mrl.setNamaMasuk(mrlLama.getNamaMasuk());
            mrl.setInfoAudit(info);
            regService.saveOrUpdate(mrl);
        }

    }

    public void inserPemohon(String idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        List<Pemohon> pemohonList = regService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        List<Pemohon> pemohonListLama = regService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), idHakmilikBaru);
//        HakmilikPihakBerkepentingan hpk = hpbDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
        setHakmilik(hakmilikDAO.findById(idHakmilikBaru));

        for (Pemohon pemohon : pemohonList) {

            Pemohon pmohon = pService.findByidPemohonOnly(String.valueOf(pemohon.getPihak().getIdPihak()), String.valueOf(pemohon.getPermohonan().getIdPermohonan()), idHakmilikBaru);
            if (pmohon == null) {
                Pemohon pe = new Pemohon();

                pe.setInfoAudit(info);
                pe.setPermohonan(pemohon.getPermohonan());
                pe.setPihak(pemohon.getPihak());
                pe.setKaitan(permohonan.getIdPermohonan());
                pe.setCawangan(peng.getKodCawangan());
                pe.setHakmilik(getHakmilik());
                pe.setJenis(pemohon.getJenis());

                pe.setNama(pemohon.getNama());
                if (pemohon.getNoPengenalan() != null) {
                    pe.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getSyerPembilang() != null) {
                    pe.setSyerPembilang(pemohon.getSyerPembilang());
                }
                if (pemohon.getSyerPenyebut() != null) {
                    pe.setSyerPenyebut(pemohon.getSyerPenyebut());
                }
//                pe.setNama(pemohon.getNama());
                if (pemohon.getJenisPengenalan() != null) {
                    pe.setJenisPengenalan(pemohon.getJenisPengenalan());
                }
                if (pe.getNoPengenalan() != null) {
                    pe.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getWargaNegara() != null) {
                    pe.setWargaNegara(pemohon.getWargaNegara());
                }
                Alamat alamat = new Alamat();
                if (pemohon.getAlamat() != null) {
                    alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                    alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                    alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                    alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                    alamat.setPoskod(pemohon.getPihak().getPoskod());
                    alamat.setNegeri(pemohon.getPihak().getNegeri());
                    pe.setAlamat(alamat);
                }

                if (pe.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(pmohon.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(pmohon.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(pmohon.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(pmohon.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(pmohon.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(pmohon.getAlamatSurat().getNegeriSurat());
//                    senaraiPemohon.add(pe);
                    pe.setAlamatSurat(alamatSurat);
                }

                pemohonService.saveOrUpdate(pe);
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    pe.setKaitan(permohonan.getIdPermohonan());
                }
            } else {
                pmohon.setInfoAudit(info);
                pmohon.setPermohonan(pemohon.getPermohonan());
                pmohon.setPihak(pemohon.getPihak());
                pmohon.setKaitan(permohonan.getIdPermohonan());
                pmohon.setCawangan(peng.getKodCawangan());
                pmohon.setHakmilik(getHakmilik());
                pmohon.setJenis(pemohon.getJenis());

                pmohon.setNama(pemohon.getNama());
                if (pemohon.getNoPengenalan() != null) {
                    pmohon.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getSyerPembilang() != null) {
                    pmohon.setSyerPembilang(pemohon.getSyerPembilang());
                }
                if (pemohon.getSyerPenyebut() != null) {
                    pmohon.setSyerPenyebut(pemohon.getSyerPenyebut());
                }
//                pe.setNama(pemohon.getNama());
                if (pemohon.getJenisPengenalan() != null) {
                    pmohon.setJenisPengenalan(pemohon.getJenisPengenalan());
                }
                if (pmohon.getNoPengenalan() != null) {
                    pmohon.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getWargaNegara() != null) {
                    pmohon.setWargaNegara(pemohon.getWargaNegara());
                }
                Alamat alamat = new Alamat();
                if (pemohon.getAlamat() != null) {
                    alamat.setAlamat1(pmohon.getPihak().getAlamat1());
                    alamat.setAlamat2(pmohon.getPihak().getAlamat2());
                    alamat.setAlamat3(pmohon.getPihak().getAlamat3());
                    alamat.setAlamat4(pmohon.getPihak().getAlamat4());
                    alamat.setPoskod(pmohon.getPihak().getPoskod());
                    alamat.setNegeri(pmohon.getPihak().getNegeri());
                }

                if (pemohon.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(pmohon.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(pmohon.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(pmohon.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(pmohon.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(pmohon.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(pmohon.getAlamatSurat().getNegeriSurat());
//                    senaraiPemohon.add(pe);

                }
            }
        }
    }

    public void insertMohonPihak(String idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));

        List<PermohonanPihak> mohonPihakLIst = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilikBaru, hu.getIdPerserahan());
        List<PermohonanPihak> mohonPihakListLama = regService.findMohonPihakByHakmilikAndIdMohon(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());

        setHakmilik(hakmilikDAO.findById(idHakmilikBaru));
        for (PermohonanPihak mohonP : mohonPihakListLama) {
            permohonanPihak = pService.findMohonPihakbyIdpihakAndIdHakmilik(mohonP.getPihak().getIdPihak(), mohonP.getPermohonan().getIdPermohonan(), idHakmilikBaru);
            if (permohonanPihak == null) {
                PermohonanPihak ppihak = new PermohonanPihak();
                ppihak.setPermohonan(mohonP.getPermohonan());
                ppihak.setPihak(mohonP.getPihak());
                ppihak.setHakmilik(getHakmilik());
                ppihak.setJenis(mohonP.getJenis());
                ppihak.setCawangan(mohonP.getCawangan());
                ppihak.setInfoAudit(info);
                if (mohonP.getSyerPembilang() != null) {
                    ppihak.setSyerPembilang(mohonP.getSyerPembilang());
                }
                if (mohonP.getSyerPenyebut() != null) {
                    ppihak.setSyerPenyebut(mohonP.getSyerPenyebut());
                }
                ppihak.setNama(mohonP.getNama());
                if (mohonP.getJenisPengenalan() != null) {
                    ppihak.setJenisPengenalan(mohonP.getJenisPengenalan());
                }
                if (mohonP.getNoPengenalan() != null) {
                    ppihak.setNoPengenalan(mohonP.getNoPengenalan());
                }
                if (mohonP.getWargaNegara() != null) {
                    ppihak.setWargaNegara(mohonP.getWargaNegara());
                }

                Alamat alamat = new Alamat();
                alamat.setAlamat1(mohonP.getAlamat().getAlamat1());
                alamat.setAlamat2(mohonP.getAlamat().getAlamat2());
                alamat.setAlamat3(mohonP.getAlamat().getAlamat3());
                alamat.setAlamat4(mohonP.getAlamat().getAlamat4());
                alamat.setPoskod(mohonP.getAlamat().getPoskod());
                alamat.setNegeri(mohonP.getAlamat().getNegeri());

                if (mohonP.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(mohonP.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(mohonP.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(mohonP.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(mohonP.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(mohonP.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(mohonP.getAlamatSurat().getNegeriSurat());

                    ppihak.setAlamat(alamat);
                    ppihak.setAlamatSurat(alamatSurat);
                }
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    ppihak.setKaitan(permohonan.getIdPermohonan());
                }
                permohonanPihakService.saveOrUpdate(ppihak);
            } else {
//                PermohonanPihak ppihak = new PermohonanPihak();
                permohonanPihak.setPermohonan(mohonP.getPermohonan());
                permohonanPihak.setPihak(mohonP.getPihak());
                permohonanPihak.setHakmilik(getHakmilik());
                permohonanPihak.setJenis(mohonP.getJenis());
                permohonanPihak.setCawangan(mohonP.getCawangan());
                permohonanPihak.setInfoAudit(info);
                if (mohonP.getSyerPembilang() != null) {
                    permohonanPihak.setSyerPembilang(mohonP.getSyerPembilang());
                }
                if (mohonP.getSyerPenyebut() != null) {
                    permohonanPihak.setSyerPenyebut(mohonP.getSyerPenyebut());
                }
                permohonanPihak.setNama(mohonP.getNama());
                if (mohonP.getJenisPengenalan() != null) {
                    permohonanPihak.setJenisPengenalan(mohonP.getJenisPengenalan());
                }
                if (mohonP.getNoPengenalan() != null) {
                    permohonanPihak.setNoPengenalan(mohonP.getNoPengenalan());
                }
                if (mohonP.getWargaNegara() != null) {
                    permohonanPihak.setWargaNegara(mohonP.getWargaNegara());
                }

                Alamat alamat = new Alamat();
                alamat.setAlamat1(mohonP.getAlamat().getAlamat1());
                alamat.setAlamat2(mohonP.getAlamat().getAlamat2());
                alamat.setAlamat3(mohonP.getAlamat().getAlamat3());
                alamat.setAlamat4(mohonP.getAlamat().getAlamat4());
                alamat.setPoskod(mohonP.getAlamat().getPoskod());
                alamat.setNegeri(mohonP.getAlamat().getNegeri());

                if (mohonP.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(mohonP.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(mohonP.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(mohonP.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(mohonP.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(mohonP.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(mohonP.getAlamatSurat().getNegeriSurat());

//                    permohonanPihak.setAlamat(alamat);
                    permohonanPihak.setAlamatSurat(alamatSurat);
                }
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    permohonanPihak.setKaitan(permohonan.getIdPermohonan());
                }
                permohonanPihak.setAlamat(alamat);
//                permohonanPihak.setAlamatSurat(alamatSurat);
                permohonanPihakService.saveOrUpdate(permohonanPihak);
            }

        }
    }

    public void insertMohonHakmilik(String idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        HakmilikPermohonan hpLama = hakmilikpermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        HakmilikPermohonan hpBaru = hakmilikpermohonanService.findHakmilikPermohonan(idHakmilikBaru, hu.getIdPerserahan());
        Hakmilik hakmilikBaru = hakmilikDAO.findById(idHakmilikBaru);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        if (hpBaru == null) {
            HakmilikPermohonanData temp = new HakmilikPermohonanData();
//            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            org.springframework.beans.BeanUtils.copyProperties(hpLama, temp);
//            
//            BeanUtilsBean.getInstance().getConvertUtils().register(null, null);
//    
//            BeanUtils.copyProperties(temp, hpLama);
//            temp.setId(0);

            HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
            org.springframework.beans.BeanUtils.copyProperties(temp, hakmilikPermohonanBaru);
            hakmilikPermohonanBaru.setHakmilik(hakmilikBaru);
            hakmilikPermohonanBaru.setPermohonan(permohonanDAO.findById(hu.getIdPerserahan()));
//            hakmilikpermohonan.set
            hakmilikPermohonanBaru.setInfoAudit(info);

            pService.saveMohonHakmilik(hakmilikPermohonanBaru);

        }
        if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
            HakmilikPermohonan HpNb = hakmilikpermohonanService.findHakmilikPermohonan(idHakmilikBaru, permohonan.getIdPermohonan());;
            HakmilikPermohonan hpLamaNB = hakmilikpermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
            if (HpNb == null) {
                HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
                HakmilikPermohonanData temp = new HakmilikPermohonanData();
                org.springframework.beans.BeanUtils.copyProperties(hpLamaNB, temp);

                org.springframework.beans.BeanUtils.copyProperties(temp, hakmilikPermohonanBaru);
                hakmilikPermohonanBaru.setHakmilik(hakmilikBaru);
                hakmilikPermohonanBaru.setPermohonan(permohonan);
                hakmilikPermohonanBaru.setInfoAudit(info);

                pService.saveMohonHakmilik(hakmilikPermohonanBaru);
            }
        }

    }

    // add by azri 4/7/2013:
    public void betulHakmilikPihakNew() {
        LOG.info("--- betulHakmilikPihakNew() :: Start ---");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("--- idHakmilikBaru ---" + idHakmilikBaru);
        LOG.info("--- idUrusan ---" + idUrusan);
        long idUrusanLong = Long.parseLong(idUrusan); // convert idUrusan to Long
//        int idUrusanInt = Integer.parseInt(idUrusan);  // convert idUrusan to integer
        if (idUrusan != null) {
            HakmilikUrusan hmu = hakmilikUrusanDAO.findById(idUrusanLong);
//            HakmilikUrusan hmu = pService.findByIdUrusan(idUrusanInt);  // use this to get id_hakmilik from HakmilikUrusan
            List<HakmilikPihakBerkepentingan> listPihak = pService.findByIdUrusanAndIdHakmilik(idUrusanLong, hmu.getHakmilik().getIdHakmilik()); // use this get data from HakmilikPihakBerkepentingan
            LOG.info("--- listPihak.size() ---" + listPihak.size());
            if (listPihak.size() > 0) { // check if there are 'hakmilik pihak berkepentingan' related to 'endorsan'
                setHakmilik(hakmilikDAO.findById(idHakmilikBaru)); // use to get input id_hakmilik
                info = peng.getInfoAudit();
                for (HakmilikPihakBerkepentingan hpb : listPihak) {
                    HakmilikPihakBerkepentingan hpbBaru = new HakmilikPihakBerkepentingan();
                    info.setDiKemaskiniOleh(peng);
                    info.setDimasukOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    info.setTarikhMasuk(new java.util.Date());
                    hpbBaru.setInfoAudit(info);
                    hpbBaru.setCawangan(getHakmilik().getCawangan());
                    hpbBaru.setHakmilik(getHakmilik());
                    hpbBaru.setPihak(hpb.getPihak());
                    hpbBaru.setPihakCawangan(hpb.getPihakCawangan());
                    hpbBaru.setJenis(hpb.getJenis());
                    hpbBaru.setSyerPembilang(hpb.getSyerPembilang());
                    hpbBaru.setSyerPenyebut(hpb.getSyerPenyebut());
                    hpbBaru.setPerserahan(hpb.getPerserahan());
                    hpbBaru.setPerserahanBatal(hpb.getPerserahanBatal());
                    hpbBaru.setPerserahanKaveat(hpb.getPerserahanKaveat());
                    hpbBaru.setAktif('T'); //set not active until urusan have been 'daftar'
                    hpbBaru.setIdPermohonan(idPermohonan);
                    hpbBaru.setKaveatAktif(hpb.getKaveatAktif());
                    hpbBaru.setJenisPengenalan(hpb.getJenisPengenalan());
                    hpbBaru.setNoPengenalan(hpb.getNoPengenalan());
                    hpbBaru.setNama(hpb.getNama());
                    hpbBaru.setAlamat1(hpb.getAlamat1());
                    hpbBaru.setAlamat2(hpb.getAlamat2());
                    hpbBaru.setAlamat3(hpb.getAlamat3());
                    hpbBaru.setAlamat4(hpb.getAlamat4());
                    hpbBaru.setPoskod(hpb.getPoskod());
                    hpbBaru.setNegeri(hpb.getNegeri());
                    hpbBaru.setWargaNegara(hpb.getWargaNegara());
                    hpbBaru.setPihakKongsiBersama(hpb.getPihakKongsiBersama());
                    hpbBaru.setPenubuhanSyarikat(hpb.getPenubuhanSyarikat());
                    hpbBaru.setJumlahPenyebut(hpb.getJumlahPenyebut());
                    hpbBaru.setJumlahPembilang(hpb.getJumlahPembilang());
                    if (hpb.getAlamatSurat() != null) {
                        AlamatSurat alamatSurat = new AlamatSurat();  // use this to copy alamat surat from 'hakmilik lama'       
                        alamatSurat.setAlamatSurat1(hpb.getAlamatSurat().getAlamatSurat1());
                        alamatSurat.setAlamatSurat2(hpb.getAlamatSurat().getAlamatSurat2());
                        alamatSurat.setAlamatSurat3(hpb.getAlamatSurat().getAlamatSurat3());
                        alamatSurat.setAlamatSurat4(hpb.getAlamatSurat().getAlamatSurat4());
                        alamatSurat.setPoskodSurat(hpb.getAlamatSurat().getPoskodSurat());
                        alamatSurat.setNegeriSurat(hpb.getAlamatSurat().getNegeriSurat());
                        hpbBaru.setAlamatSurat(alamatSurat);
                    }
                    pService.save(hpbBaru);
                }
            }
        }
        LOG.info("--- betulHakmilikPihakNew() :: End ---");
    }

    public void betulHakmilikPihakUpdate() {
        LOG.info("--- betulHakmilikPihakUpdate() :: Start ---");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idHakmilikBaruTemp = (String) getContext().getRequest().getParameter("idHakmilikBaruTemp"); // temp id_hakmilik if user try to change id_hakmilik    
        long idUrusanLong = Long.parseLong(idUrusan); // convert idUrusan to Long
        if (idUrusan != null) {
            if (idHakmilikBaruTemp != null) { // check if hidden temp id_hakmilik is not null to prevent system error
                List<HakmilikPihakBerkepentingan> listPihak = pService.findByIdUrusanAndIdHakmilik(idUrusanLong, idHakmilikBaruTemp); // use this get data from HakmilikPihakBerkepentingan
                LOG.info("--- listPihak.size() ---" + listPihak.size());
                if (listPihak.size() > 0) { // check if there are 'hakmilik pihak berkepentingan' related to 'endorsan'
                    setHakmilik(hakmilikDAO.findById(idHakmilikBaru)); // use to get input id_hakmilik
                    info = peng.getInfoAudit();
                    for (HakmilikPihakBerkepentingan hpb : listPihak) {
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        hpb.setInfoAudit(info);
                        hpb.setHakmilik(getHakmilik());
                        pService.save(hpb);
                    }
                }
            }
        }
        LOG.info("--- betulHakmilikPihakUpdate() :: End ---");
    }
    // add by azri 4/7/2013: End

    public Resolution deleteChanges2() {
        String error = "";
        String msg = "";
        String idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        LOG.debug("idUrusan : " + idUrusan);
        LOG.debug("---- idHakmilik : " + idHakmilik);

        if (idUrusan != null) {
            permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
            permohonanPembetulanHakmilik = pService.findByUrusan(String.valueOf(permohonan.getIdPermohonan()), String.valueOf(permohonanAtasPerserahan.getIdAtasUrusan()));

//            Integer idUrusanLama = Integer.valueOf(idUrusan);
            if (permohonanPembetulanHakmilik != null) {
                deleteMohonPihak(permohonanPembetulanHakmilik.getIdHakmilik(), idUrusan);
                pService.deleteUrusanHakmilikBetul(permohonanPembetulanHakmilik);

                // add by azri 4/7/2013: to revert hakmilik_pihak info
                long idUrusanLong = Long.parseLong(idUrusan); // convert idUrusan to Long
                List<HakmilikPihakBerkepentingan> listPihak = pService.findByIdUrusanAndIdHakmilik(idUrusanLong, permohonanPembetulanHakmilik.getIdHakmilik());
                if (listPihak.size() > 0) {
                    for (HakmilikPihakBerkepentingan hpb : listPihak) {
                        pService.deleteHakmilikPihakBerkepentingan(listPihak);
                    }
                }
                // add by azri 4/7/2013: End
            }
            if (permohonanAtasPerserahan != null) {
                pService.deleteUrusan(permohonanAtasPerserahan);
            }

            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
    }

    public void deleteMohonPihak(String idHakmilik, String idUrusan) {

        Long idUrusanLama = Long.parseLong(idUrusan);
        hakmilikUrusan = hakmilikUrusanDAO.findById(idUrusanLama);
        HakmilikPermohonan hpBaru = hakmilikpermohonanService.findHakmilikPermohonan(permohonanPembetulanHakmilik.getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
        if (hpBaru != null) {
            hakmilikPermohonanDAO.delete(hpBaru);
        }

    }

    //    Urusan BETUL -Maklumat Urusan
    public Resolution maklumatUrusan() {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("daftar/pembetulan/betulUrusan.jsp").addParameter("tab", "true");
    }

    public Resolution saveUrusan() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();

        hakmilikUrusan = pService.findUrusan(idUrusan);
        permohonanPembetulanHakmilik = pService.findByidPidH(String.valueOf(permohonan.getIdPermohonan()), hakmilikUrusan.getHakmilik().getIdHakmilik());
        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        if (permohonanPembetulanHakmilik != null) {
            error = "Data Telah Disimpan";

            return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
        } else {
            permohonanPembetulanHakmilik = new PermohonanPembetulanHakmilik();
            LOG.info("New Record - saveUrusan");
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanPembetulanHakmilik.setInfoAudit(info);
            permohonanPembetulanHakmilik.setCawangan(peng.getKodCawangan());
            permohonanPembetulanHakmilik.setIdHakmilik(idHakmilikBaru);
            permohonanPembetulanHakmilik.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanHakmilik.setPermohonan(permohonan);
            pService.simpanBetul(permohonanPembetulanHakmilik);
        }

        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "maklumatSilap").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution viewUrusanDetail() {
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        idMohon = (String) getContext().getRequest().getParameter("idMohon");
        permohonanAtasPerserahan = pService.findAtasMohon(idUrusan, String.valueOf(permohonan.getIdPermohonan()));
        if (permohonanAtasPerserahan != null) {
            permohonanPembetulanHakmilik = pService.findByUrusan(String.valueOf(permohonan.getIdPermohonan()), String.valueOf(permohonanAtasPerserahan.getIdAtasUrusan()));
            if (permohonanPembetulanHakmilik != null) {
                idHakmilikBaru = permohonanPembetulanHakmilik.getIdHakmilik();
            }
        }
        return new JSP("daftar/pembetulan/editHakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution viewUrusan() {
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikUrusan = pService.findUrusan(idUrusan);
        mohonUrusan = pService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
        betulUrusan = pService.findUrusanBetul(idUrusan);
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        String tajuk = hakmilikUrusan.getIdPerserahan();

        fd = pService.findFolderByTajuk(tajuk);
        LOG.info("ID Mohon 2 " + hakmilikUrusan.getIdPerserahan());
        LOG.info("ID Hakmilik 2 " + hakmilikUrusan.getHakmilik().getIdHakmilik());

//        pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
        pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

        if (fd != null) {
            if (betulUrusan != null) {
                if (betulUrusan.getNoFolio() != null) {
                    noFolio = betulUrusan.getNoFolio();
                    LOG.info("noFolio " + noFolio);
                }
                if (betulUrusan.getNoJilid() != null) {
                    noJilid = betulUrusan.getNoJilid();
                    LOG.info("noJilid " + noJilid);
                }
            }
        }

        if (betulUrusan != null) {
            if (betulUrusan.getTarikhDaftar() != null) {
                tarikhDaftar = dateTimeFormat.format(betulUrusan.getTarikhDaftar());
                jam = tarikhDaftar.substring(11, 13);
                minit = tarikhDaftar.substring(14, 16);
                saat = tarikhDaftar.substring(17, 19);
                ampm = tarikhDaftar.substring(20, 22);
            }
            if (betulUrusan.getNoFail() != null) {
                noFail = betulUrusan.getNoFail();
            }
            if (betulUrusan.getNoRujukan() != null) {
                noRujukan = betulUrusan.getNoRujukan();
            }
            if (betulUrusan.getPerjanjianAmaun() != null) {
                perjanjianAmaun = String.valueOf(betulUrusan.getPerjanjianAmaun());
            }
            if (betulUrusan.getPerjanjianDutiStem() != null) {
                perjanjianDutiStem = String.valueOf(betulUrusan.getPerjanjianDutiStem());
            }
            if (betulUrusan.getPerjanjianNoRujukan() != null) {
                perjanjianNoRujukan = betulUrusan.getPerjanjianNoRujukan();
            }
            if (betulUrusan.getTarikhSaksi() != null) {
                tarikhSaksi = dateFormat.format(betulUrusan.getTarikhSaksi());
            }
            if (betulUrusan.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(betulUrusan.getTarikhRujukan());
            }
            if (betulUrusan.getTarikhSidang() != null) {
                tarikhSidang = dateFormat.format(betulUrusan.getTarikhSidang());
            }
            if (betulUrusan.getTarikhTamat() != null) {
                tarikhTamat = dateFormat.format(betulUrusan.getTarikhTamat());
            }
            if (betulUrusan.getTempohHari() != null) {
                tempohHari = String.valueOf(betulUrusan.getTempohHari());
            }
            if (betulUrusan.getTempohBulan() != null) {
                tempohBulan = String.valueOf(betulUrusan.getTempohBulan());
            }
            if (betulUrusan.getTempohTahun() != null) {
                tempohTahun = String.valueOf(betulUrusan.getTempohTahun());
            }
            if (betulUrusan.getKodPerintah() != null) {
                kodPerintah = betulUrusan.getKodPerintah().getKod();
            }
            if (betulUrusan.getNoSidang() != null) {
                noSidang = betulUrusan.getNoSidang();
            }
            if (betulUrusan.getItem() != null) {
                namaSidang = betulUrusan.getItem();
            }
            if (betulUrusan.getPerintahSebab() != null) {
                perintahSebab = betulUrusan.getPerintahSebab();
            }
            if (betulUrusan.getPerintahTarikhKuatkuasa() != null) {
                perintahTarikhKuatkuasa = dateFormat.format(betulUrusan.getPerintahTarikhKuatkuasa());
            }
            if (betulUrusan.getLuasTerlibat() != null) {
                luasTerlibat = String.valueOf(betulUrusan.getLuasTerlibat());
            }
            if (betulUrusan.getKodUnitLuas() != null) {
                strKodUOM = betulUrusan.getKodUnitLuas().getKod();
            }

//            if (betulUrusan.getLuasTerlibat() != null) {
//                luasTerlibat = String.valueOf(permohonanPembetulanUrusan.getLuasTerlibat());
//            }
//            if (betulUrusan.getCukaiBaru() != null) {
//                cukaiBaru = String.valueOf(betulUrusan.getCukaiBaru());
//            }
            //Added by aizuddin for majlis MAJD
            if (betulUrusan.getItem() != null) {
                majlis = betulUrusan.getItem();
            }

        }
        hakmilikPermohonan = pService.findByIdHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        mohonHakmilik = pService.findByIdHakmilikIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
        LOG.info("Luas = " + mohonHakmilik.getLuasTerlibat());
        if (mohonHakmilik.getLuasTerlibat() != null) {
            luas = String.valueOf(mohonHakmilik.getLuasTerlibat());
        }
        if (mohonHakmilik.getKodUnitLuas() != null) {
            strKodUOM = mohonHakmilik.getKodUnitLuas().getNama();
        }
        if (hakmilikPermohonan.getCukaiBaru() != null) {
            cukaiBaru = String.valueOf(hakmilikPermohonan.getCukaiBaru());
        }

        if (hakmilikUrusan.getCukaiLama() != null) {
            cukaiLama = String.valueOf(hakmilikUrusan.getCukaiLama());
        }
        senaraiPemohon = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
        senaraiMohonPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(hakmilikUrusan.getIdPerserahan());

        return new JSP("daftar/pembetulan/edit_urusan.jsp").addParameter("popup", "true");
    }

    public Resolution viewUrusanBatal() {
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikUrusan = pService.findUrusan(idUrusan);
        mohonUrusan = pService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
        betulUrusan = pService.findUrusanBetul(idUrusan);
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        String tajuk = hakmilikUrusan.getIdPerserahan();

        fd = pService.findFolderByTajuk(tajuk);
        LOG.info("ID Mohon Batal " + hakmilikUrusan.getIdPerserahan());
        LOG.info("ID Hakmilik 2 " + hakmilikUrusan.getHakmilik().getIdHakmilik());

//        pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
        pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

        if (fd != null) {
            if (betulUrusan != null) {
                if (betulUrusan.getNoFolio() != null) {
                    noFolio = betulUrusan.getNoFolio();
                    LOG.info("noFolio " + noFolio);
                }
                if (betulUrusan.getNoJilid() != null) {
                    noJilid = betulUrusan.getNoJilid();
                    LOG.info("noJilid " + noJilid);
                }
            }
        }

        if (betulUrusan != null) {
            if (betulUrusan.getTarikhDaftar() != null) {
                tarikhDaftar = dateTimeFormat.format(betulUrusan.getTarikhDaftar());
                jam = tarikhDaftar.substring(11, 13);
                minit = tarikhDaftar.substring(14, 16);
                saat = tarikhDaftar.substring(17, 19);
                ampm = tarikhDaftar.substring(20, 22);
            }
            if (betulUrusan.getAktif() != null) {
                aktif = betulUrusan.getAktif();
            }
            if (betulUrusan.getNoFail() != null) {
                noFail = betulUrusan.getNoFail();
            }
            if (betulUrusan.getNoRujukan() != null) {
                noRujukan = betulUrusan.getNoRujukan();
            }
            if (betulUrusan.getPerjanjianAmaun() != null) {
                perjanjianAmaun = String.valueOf(betulUrusan.getPerjanjianAmaun());
            }
            if (betulUrusan.getPerjanjianDutiStem() != null) {
                perjanjianDutiStem = String.valueOf(betulUrusan.getPerjanjianDutiStem());
            }
            if (betulUrusan.getPerjanjianNoRujukan() != null) {
                perjanjianNoRujukan = betulUrusan.getPerjanjianNoRujukan();
            }
            if (betulUrusan.getTarikhSaksi() != null) {
                tarikhSaksi = dateFormat.format(betulUrusan.getTarikhSaksi());
            }
            if (betulUrusan.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(betulUrusan.getTarikhRujukan());
            }
            if (betulUrusan.getTarikhSidang() != null) {
                tarikhSidang = dateFormat.format(betulUrusan.getTarikhSidang());
            }
            if (betulUrusan.getTarikhTamat() != null) {
                tarikhTamat = dateFormat.format(betulUrusan.getTarikhTamat());
            }
            if (betulUrusan.getTempohHari() != null) {
                tempohHari = String.valueOf(betulUrusan.getTempohHari());
            }
            if (betulUrusan.getTempohBulan() != null) {
                tempohBulan = String.valueOf(betulUrusan.getTempohBulan());
            }
            if (betulUrusan.getTempohTahun() != null) {
                tempohTahun = String.valueOf(betulUrusan.getTempohTahun());
            }
            if (betulUrusan.getKodPerintah() != null) {
                kodPerintah = betulUrusan.getKodPerintah().getKod();
            }
            if (betulUrusan.getNoSidang() != null) {
                noSidang = betulUrusan.getNoSidang();
            }
            if (betulUrusan.getItem() != null) {
                namaSidang = betulUrusan.getItem();
            }
            if (betulUrusan.getPerintahSebab() != null) {
                perintahSebab = betulUrusan.getPerintahSebab();
            }
            if (betulUrusan.getPerintahTarikhKuatkuasa() != null) {
                perintahTarikhKuatkuasa = dateFormat.format(betulUrusan.getPerintahTarikhKuatkuasa());
            }
            if (betulUrusan.getLuasTerlibat() != null) {
                luasTerlibat = String.valueOf(betulUrusan.getLuasTerlibat());
            }
            if (betulUrusan.getKodUnitLuas() != null) {
                strKodUOM = betulUrusan.getKodUnitLuas().getKod();
            }

//            if (betulUrusan.getLuasTerlibat() != null) {
//                luasTerlibat = String.valueOf(permohonanPembetulanUrusan.getLuasTerlibat());
//            }
//            if (betulUrusan.getCukaiBaru() != null) {
//                cukaiBaru = String.valueOf(betulUrusan.getCukaiBaru());
//            }
            //Added by aizuddin for majlis MAJD
            if (betulUrusan.getItem() != null) {
                majlis = betulUrusan.getItem();
            }

        }
        hakmilikPermohonan = pService.findByIdHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
        mohonHakmilik = pService.findByIdHakmilikIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan(), hakmilikPermohonan.getHakmilik().getIdHakmilik());
        LOG.info("Luas = " + mohonHakmilik.getLuasTerlibat());
        if (mohonHakmilik.getLuasTerlibat() != null) {
            luas = String.valueOf(mohonHakmilik.getLuasTerlibat());
        }
        if (mohonHakmilik.getKodUnitLuas() != null) {
            strKodUOM = mohonHakmilik.getKodUnitLuas().getNama();
        }
        if (hakmilikPermohonan.getCukaiBaru() != null) {
            cukaiBaru = String.valueOf(hakmilikPermohonan.getCukaiBaru());
        }
        senaraiPemohon = pemohonService.findPemohonByIdPermohonan(hakmilikUrusan.getIdPerserahan());
        senaraiMohonPihak = permohonanPihakService.findPermohonanPihakByIdPermohonan(hakmilikUrusan.getIdPerserahan());

        return new JSP("daftar/pembetulan/edit_urusan_batal.jsp").addParameter("popup", "true");
    }

    public Resolution updateUrusan() {
        idPembetulan = (String) getContext().getRequest().getParameter("idPembetulan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPerserahanLama = (String) getContext().getRequest().getParameter("idPerserahanLama");
        hakmilikPermohonan = pService.findByIdHakmilik(permohonan.getIdPermohonan(), idHakmilik);
        setHakmilik(hakmilikDAO.findById(String.valueOf(hakmilikPermohonan)));
        caw = permohonan.getCawangan().getKod();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit iaPermohonan = new InfoAudit();
        SWD = "";
        SBD = "";
        SAB = "";

        permohonanLama = permohonanService.findById(idPerserahanLama);

        if (permohonanLama.getFolderDokumen() == null) {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("-");
            iaPermohonan.setDimasukOleh(peng);
            iaPermohonan.setTarikhMasuk(new java.util.Date());
            fd.setInfoAudit(iaPermohonan);
            folderDokumenDAO.save(fd);

            permohonanLama.setFolderDokumen(fd);
            permohonanService.saveOrUpdate(permohonanLama);
        }

        if (permohonanLama.getFolderDokumen() != null) {
            fd = permohonanLama.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }

                    if (kf.getDokumen() != null) {
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SWD")) {
                            SWD = kf.getDokumen().getNoRujukan();
                        } else if (kod.equals("SBD")) {
                            SBD = kf.getDokumen().getNoRujukan();
                        } else if (kod.equals("SAB")) {
                            SAB = kf.getDokumen().getNoRujukan();
                        }
                    }
                }
            }
        }

        if (idPembetulan != null) {
            permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            if (permohonanPembetulanUrusan.getIdPerserahanLama() != null) {
                idPerserahan = permohonanPembetulanUrusan.getIdPerserahanLama();
                permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, permohonanPembetulanUrusan.getIdPerserahanLama());

                if (permohonanPembetulanUrusan.getTempohTahun() != null) {
                    tempohTahun = String.valueOf(permohonanPembetulanUrusan.getTempohTahun());
                }
                if (permohonanPembetulanUrusan.getTempohBulan() != null) {
                    tempohBulan = String.valueOf(permohonanPembetulanUrusan.getTempohBulan());
                }
                if (permohonanPembetulanUrusan.getTempohHari() != null) {
                    tempohHari = String.valueOf(permohonanPembetulanUrusan.getTempohHari());
                }
                if (permohonanPembetulanUrusan.getTarikhMula() != null) {
                    trhKuasa = sdf.format(permohonanPembetulanUrusan.getTarikhMula());
//                            String.valueOf(permohonanPembetulanUrusan.getTarikhMula());
                }
                if (permohonanPembetulanUrusan.getTarikhTamat() != null) {
                    trhTamat = sdf.format(permohonanPembetulanUrusan.getTarikhTamat());
                }

//                if (permohonanRujukanLuar.getTempohTahun() == null) {
//                    tempohTahun = "0";
//                } else {
//                    tempohTahun = String.valueOf(permohonanRujukanLuar.getTempohTahun());
//                }
//                if (permohonanRujukanLuar.getTempohBulan() == null) {
//                    tempohBulan = "0";
//                } else {
//                    tempohBulan = String.valueOf(permohonanRujukanLuar.getTempohBulan());
//                }
//                if (permohonanRujukanLuar.getTempohHari() == null) {
//                    tempohHari = "0";
//                } else {
//                    tempohHari = String.valueOf(permohonanRujukanLuar.getTempohHari());
//                }
//                if (permohonanRujukanLuar.getTarikhKuatkuasa() != null) {
//                    trhKuasa = dateFormat.format(permohonanRujukanLuar.getTarikhKuatkuasa());
//                }
//                if (permohonanRujukanLuar.getTarikhTamat() != null) {
//                    trhTamat = dateFormat.format(permohonanRujukanLuar.getTarikhTamat());
//                }
            }
            if (permohonanPembetulanUrusan.getKodUrusan() != null) {
                kodUrusan = permohonanPembetulanUrusan.getKodUrusan().getKod();
                senaraiUrusanPendaftaran = pService.findSCBN();
            }
            if (permohonanPembetulanUrusan.getTarikhDaftar() != null) {
                tarikhDaftar = dateTimeFormat.format(permohonanPembetulanUrusan.getTarikhDaftar());
                jam = tarikhDaftar.substring(11, 13);
                minit = tarikhDaftar.substring(14, 16);
                saat = tarikhDaftar.substring(17, 19);
                ampm = tarikhDaftar.substring(20, 22);
            }
            if (permohonanPembetulanUrusan.getNoJilid() != null) {
                noJilid = permohonanPembetulanUrusan.getNoJilid();
            }
            if (permohonanPembetulanUrusan.getNoFolio() != null) {
                noFolio = permohonanPembetulanUrusan.getNoFolio();
            }
            if (permohonanPembetulanUrusan.getLuasTerlibat() != null) {
                luasTerlibat = String.valueOf(permohonanPembetulanUrusan.getLuasTerlibat());
            }
            if (permohonanPembetulanUrusan.getKodUnitLuas() != null) {
                strKodUOM = String.valueOf(permohonanPembetulanUrusan.getKodUnitLuas().getKod());
            }
            if (permohonanPembetulanUrusan.getNoFail() != null) {
                noFail = permohonanPembetulanUrusan.getNoFail();
            }
            if (permohonanPembetulanUrusan.getNoRujukan() != null) {
                noRujukan = permohonanPembetulanUrusan.getNoRujukan();
            }
            if (permohonanPembetulanUrusan.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(permohonanPembetulanUrusan.getTarikhRujukan());
            }
            if (permohonanPembetulanUrusan.getCukaiBaru() != null) {
                cukaiBaru = String.valueOf(permohonanPembetulanUrusan.getCukaiBaru());
            }

        }
        return new JSP("daftar/pembetulan/update_urusan.jsp").addParameter("popup", "true");
    }

    public Resolution updateUrusanTerperinci() {
        if (idUrusan == null) {
            idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        }

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPerserahanLama = (String) getContext().getRequest().getParameter("idPerserahanLama");
        hakmilikPermohonan = pService.findByIdHakmilik(permohonan.getIdPermohonan(), idHakmilik);
        setHakmilik(hakmilikDAO.findById(String.valueOf(hakmilikPermohonan)));
        caw = permohonan.getCawangan().getKod();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit iaPermohonan = new InfoAudit();
        SWD = "";
        SBD = "";
        SAB = "";

        hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        idPerserahanLama = hakmilikUrusan.getIdPerserahan();
        permohonanLama = permohonanService.findById(idPerserahanLama);
        pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

        if (permohonanLama.getFolderDokumen() == null) {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("-");
            iaPermohonan.setDimasukOleh(peng);
            iaPermohonan.setTarikhMasuk(new java.util.Date());
            fd.setInfoAudit(iaPermohonan);
            folderDokumenDAO.save(fd);

            permohonanLama.setFolderDokumen(fd);
            permohonanService.saveOrUpdate(permohonanLama);
        }
        listSWD = new ArrayList<KandunganFolder>();
        listSBD = new ArrayList<KandunganFolder>();
        listSAB = new ArrayList<KandunganFolder>();
        if (permohonanLama.getFolderDokumen() != null) {
            fd = permohonanLama.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }

                    if (kf.getDokumen() != null) {
                        String kod = kf.getDokumen().getKodDokumen().getKod();

                        if (kod.equals("SWD")) {
//                            SWD = kf.getDokumen().getNoRujukan();
                            listSWD.add(kf);
                        } else if (kod.equals("SBD")) {
//                            SBD = kf.getDokumen().getNoRujukan();
                            listSBD.add(kf);
                        } else if (kod.equals("SAB")) {
//                            SAB = kf.getDokumen().getNoRujukan();
                            listSAB.add(kf);
                        }
                    }
                }
            }
        }

//        listPermohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonanList(idHakmilik, idPerserahanLama);
        senaraiDokumen = new ArrayList<Dokumen>();
        if (SWD != null) {
            List<Dokumen> listDokumenSWD = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPerserahanLama, idHakmilik, SWD);
            senaraiDokumen.addAll(listDokumenSWD);
        }
        if (SBD != null) {
            List<Dokumen> listDokumenSBD = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPerserahanLama, idHakmilik, SBD);
            senaraiDokumen.addAll(listDokumenSBD);
        }
        if (SAB != null) {
            List<Dokumen> listDokumenSAB = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPerserahanLama, idHakmilik, SAB);
            senaraiDokumen.addAll(listDokumenSAB);
        }

        if (idPembetulan != null) {
            permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            if (permohonanPembetulanUrusan.getIdPerserahanLama() != null) {
                idPerserahan = permohonanPembetulanUrusan.getIdPerserahanLama();
                permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, permohonanPembetulanUrusan.getIdPerserahanLama());

                if (permohonanPembetulanUrusan.getTempohTahun() != null) {
                    tempohTahun = String.valueOf(permohonanPembetulanUrusan.getTempohTahun());
                }
                if (permohonanPembetulanUrusan.getTempohBulan() != null) {
                    tempohBulan = String.valueOf(permohonanPembetulanUrusan.getTempohBulan());
                }
                if (permohonanPembetulanUrusan.getTempohHari() != null) {
                    tempohHari = String.valueOf(permohonanPembetulanUrusan.getTempohHari());
                }
                if (permohonanPembetulanUrusan.getTarikhMula() != null) {
                    trhKuasa = sdf.format(permohonanPembetulanUrusan.getTarikhMula());
//                            String.valueOf(permohonanPembetulanUrusan.getTarikhMula());
                }
                if (permohonanPembetulanUrusan.getTarikhTamat() != null) {
                    trhTamat = sdf.format(permohonanPembetulanUrusan.getTarikhTamat());
                }
            }
            if (permohonanPembetulanUrusan.getKodUrusan() != null) {
                kodUrusan = permohonanPembetulanUrusan.getKodUrusan().getKod();
                senaraiUrusanPendaftaran = pService.findSCBN();
            }
            if (permohonanPembetulanUrusan.getTarikhDaftar() != null) {
                tarikhDaftar = dateTimeFormat.format(permohonanPembetulanUrusan.getTarikhDaftar());
                jam = tarikhDaftar.substring(11, 13);
                minit = tarikhDaftar.substring(14, 16);
                saat = tarikhDaftar.substring(17, 19);
                ampm = tarikhDaftar.substring(20, 22);
            }
            if (permohonanPembetulanUrusan.getNoJilid() != null) {
                noJilid = permohonanPembetulanUrusan.getNoJilid();
            }
            if (permohonanPembetulanUrusan.getNoFolio() != null) {
                noFolio = permohonanPembetulanUrusan.getNoFolio();
            }
            if (permohonanPembetulanUrusan.getLuasTerlibat() != null) {
                luasTerlibat = String.valueOf(permohonanPembetulanUrusan.getLuasTerlibat());
            }
            if (permohonanPembetulanUrusan.getKodUnitLuas() != null) {
                strKodUOM = String.valueOf(permohonanPembetulanUrusan.getKodUnitLuas().getKod());
            }
            if (permohonanPembetulanUrusan.getNoFail() != null) {
                noFail = permohonanPembetulanUrusan.getNoFail();
            }
            if (permohonanPembetulanUrusan.getNoRujukan() != null) {
                noRujukan = permohonanPembetulanUrusan.getNoRujukan();
            }
            if (permohonanPembetulanUrusan.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(permohonanPembetulanUrusan.getTarikhRujukan());
            }
            if (permohonanPembetulanUrusan.getCukaiBaru() != null) {
                cukaiBaru = String.valueOf(permohonanPembetulanUrusan.getCukaiBaru());
            }

        } else {
            permohonanPembetulanUrusan = pService.findByidPermohonanAndKemaskini(permohonan.getIdPermohonan());
            if (permohonanPembetulanUrusan == null) {
                permohonanPembetulanUrusan = pService.findByidPermohonan(permohonan.getIdPermohonan());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            if (permohonanPembetulanUrusan.getIdPerserahanLama() != null) {
                idPerserahan = permohonanPembetulanUrusan.getIdPerserahanLama();
                permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, permohonanPembetulanUrusan.getIdPerserahanLama());

                if (permohonanPembetulanUrusan.getTempohTahun() != null) {
                    tempohTahun = String.valueOf(permohonanPembetulanUrusan.getTempohTahun());
                }
                if (permohonanPembetulanUrusan.getTempohBulan() != null) {
                    tempohBulan = String.valueOf(permohonanPembetulanUrusan.getTempohBulan());
                }
                if (permohonanPembetulanUrusan.getTempohHari() != null) {
                    tempohHari = String.valueOf(permohonanPembetulanUrusan.getTempohHari());
                }
                if (permohonanPembetulanUrusan.getTarikhMula() != null) {
                    trhKuasa = sdf.format(permohonanPembetulanUrusan.getTarikhMula());
//                            String.valueOf(permohonanPembetulanUrusan.getTarikhMula());
                }
                if (permohonanPembetulanUrusan.getTarikhTamat() != null) {
                    trhTamat = sdf.format(permohonanPembetulanUrusan.getTarikhTamat());
                }
            }
            if (permohonanPembetulanUrusan.getKodUrusan() != null) {
                kodUrusan = permohonanPembetulanUrusan.getKodUrusan().getKod();
                senaraiUrusanPendaftaran = pService.findSCBN();
            }
            if (permohonanPembetulanUrusan.getTarikhDaftar() != null) {
                tarikhDaftar = dateTimeFormat.format(permohonanPembetulanUrusan.getTarikhDaftar());
                jam = tarikhDaftar.substring(11, 13);
                minit = tarikhDaftar.substring(14, 16);
                saat = tarikhDaftar.substring(17, 19);
                ampm = tarikhDaftar.substring(20, 22);
            }
            if (permohonanPembetulanUrusan.getNoJilid() != null) {
                noJilid = permohonanPembetulanUrusan.getNoJilid();
            }
            if (permohonanPembetulanUrusan.getNoFolio() != null) {
                noFolio = permohonanPembetulanUrusan.getNoFolio();
            }
            if (permohonanPembetulanUrusan.getLuasTerlibat() != null) {
                luasTerlibat = String.valueOf(permohonanPembetulanUrusan.getLuasTerlibat());
            }
            if (permohonanPembetulanUrusan.getKodUnitLuas() != null) {
                strKodUOM = String.valueOf(permohonanPembetulanUrusan.getKodUnitLuas().getKod());
            }
            if (permohonanPembetulanUrusan.getNoFail() != null) {
                noFail = permohonanPembetulanUrusan.getNoFail();
            }
            if (permohonanPembetulanUrusan.getNoRujukan() != null) {
                noRujukan = permohonanPembetulanUrusan.getNoRujukan();
            }
            if (permohonanPembetulanUrusan.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(permohonanPembetulanUrusan.getTarikhRujukan());
            }
            if (permohonanPembetulanUrusan.getCukaiBaru() != null) {
                cukaiBaru = String.valueOf(permohonanPembetulanUrusan.getCukaiBaru());
            }
        }
        return new JSP("daftar/pembetulan/update_urusan_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution tambahSuratWakil() {
//        String idDokumen = getContext().getRequest().getParameter("idDokumen");
////        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
//
//        Permohonan mohon = permohonanDAO.findById(idPerserahan);
//        permohonanLama = permohonanDAO.findById(idPermohonanLama);
//        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
////        List<FolderDokumen> senaraiFolderDokumens = folderDokumenDAO.findByExample(mohon.getFolderDokumen());
//        KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, mohon.getFolderDokumen());
//
//        if (kf.getDokumen() != null) {
//            String kod = kf.getDokumen().getKodDokumen().getKod();
//
//            if (kod.equals("SWD")) {
//                SWD = kf.getDokumen().getNoRujukan();
//                dokumenSave = kf.getDokumen();
//            } else if (kod.equals("SBD")) {
//                SBD = kf.getDokumen().getNoRujukan();
//                dokumenSave = kf.getDokumen();
//            } else if (kod.equals("SAB")) {
//                SAB = kf.getDokumen().getNoRujukan();
//                dokumenSave = kf.getDokumen();
//            }
//        }
        updateUrusanTerperinci();
        tambah = true;
        return new JSP("daftar/pembetulan/update_urusan_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskini() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
//        idPerserahan = getContext().getRequest().getParameter("idPerserahan");

        Permohonan mohon = permohonanDAO.findById(idPerserahan);
        permohonanLama = permohonanDAO.findById(idPermohonanLama);
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
//        List<FolderDokumen> senaraiFolderDokumens = folderDokumenDAO.findByExample(mohon.getFolderDokumen());
        KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, mohon.getFolderDokumen());

        if (kf.getDokumen() != null) {
            String kod = kf.getDokumen().getKodDokumen().getKod();

            if (kod.equals("SWD")) {
                SWD = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            } else if (kod.equals("SBD")) {
                SBD = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            } else if (kod.equals("SAB")) {
                SAB = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            }
        }
        kemaskini = true;
        return new JSP("daftar/pembetulan/update_urusan_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution Delete() {
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
//        idPerserahan = getContext().getRequest().getParameter("idPerserahan");

        Permohonan mohon = permohonanDAO.findById(idPerserahan);
        permohonanLama = permohonanDAO.findById(idPermohonanLama);
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
//        List<FolderDokumen> senaraiFolderDokumens = folderDokumenDAO.findByExample(mohon.getFolderDokumen());
        KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, mohon.getFolderDokumen());

        if (kf.getDokumen() != null) {
            String kod = kf.getDokumen().getKodDokumen().getKod();

            if (kod.equals("SWD")) {
                SWD = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            } else if (kod.equals("SBD")) {
                SBD = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            } else if (kod.equals("SAB")) {
                SAB = kf.getDokumen().getNoRujukan();
                dokumenSave = kf.getDokumen();
            }
        }
        kemaskini = true;
        return new JSP("daftar/pembetulan/update_urusan_terperinci.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSASBSW() {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
//        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        tarikhDaftarUrusan = getContext().getRequest().getParameter("tarikhDaftar");
        kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        noFolio = getContext().getRequest().getParameter("noFolio");
        noJilid = getContext().getRequest().getParameter("noJilid");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        luasTerlibat = getContext().getRequest().getParameter("luasTerlibat");
        strKodUOM = getContext().getRequest().getParameter("strKodUOM");
        noFail = getContext().getRequest().getParameter("noFail");
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        cukaiBaru = getContext().getRequest().getParameter("cukaiBaru");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kawasan = getContext().getRequest().getParameter("kawasan");
        tempohTahun = getContext().getRequest().getParameter("tempohTahun");
        tempohBulan = getContext().getRequest().getParameter("tempohBulan");
        tempohHari = getContext().getRequest().getParameter("tempohHari");
        trhKuasa = getContext().getRequest().getParameter("trhKuasa");
        trhTamat = getContext().getRequest().getParameter("trhTamat");

//        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
//        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
        Permohonan mohon = permohonanDAO.findById(idPerserahan);
//
//              senaraiDokumen = new ArrayList<Dokumen>();
//        if (SWD != null){
//             List<Dokumen> listDokumenSWD = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPerserahanLama, idHakmilik, SWD);
//             senaraiDokumen.addAll(listDokumenSWD);
//        }
//        if (SBD != null){
//            List<Dokumen> listDokumenSBD = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPerserahanLama, idHakmilik, SBD);
//            senaraiDokumen.addAll(listDokumenSBD);
//        }
//        if (SAB != null){
//            List<Dokumen> listDokumenSAB = service.findDokumenByidMohonANDidHakmilikANDnoRUJ(idPermohonan, idHakmilik, SAB);
//            senaraiDokumen.addAll(listDokumenSAB);
//        }

        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
//        List<FolderDokumen> senaraiFolderDokumens = folderDokumenDAO.findByExample(mohon.getFolderDokumen());
        KandunganFolder kf = kandunganFolderService.findByDokumen(dokumen, mohon.getFolderDokumen());
        if (kf != null) {
            kandunganFolderService.delete(kf);
        }
        dokumenService.delete(dokumen);

//        //tambah SW ni ha
//        if (StringUtils.isNotBlank(SWD)) {
//            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
//            fd = mohonSurat.getFolderDokumen();
//            if (fd.getSenaraiKandungan() != null) {
//                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
//                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
//                        continue;
//                    }
//                    if (kf.getDokumen() != null) {
//                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
//                        dokumenDAO.delete(d);
////                        String kod = kf.getDokumen().getKodDokumen().getKod();
////                        if (kod.equals("SWD")) {
////                            SWdahAda = true;
////                            info.setDiKemaskiniOleh(peng);
////                            info.setTarikhKemaskini(new java.util.Date());
////                            Permohonan mhn = permohonanService.findById(SWD);
////                            d.setPermohonan(mhn);
////                            d.setNoRujukan(null);
////                            d.setInfoAudit(info);
////                            dokumenDAO.saveOrUpdate(d);
////                        }
//                    }
//                }
//            }
//        }
//
//        if (StringUtils.isNotBlank(SAB)) {
//            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
//            fd = mohonSurat.getFolderDokumen();
//            if (fd.getSenaraiKandungan() != null) {
//                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
//                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
//                        continue;
//                    }
//                    if (kf.getDokumen() != null) {
//                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
//                        dokumenDAO.delete(d);
////                        String kod = kf.getDokumen().getKodDokumen().getKod();
////                        if (kod.equals("SAB")) {
////                            SAdahAda = true;
////                            info.setDiKemaskiniOleh(peng);
////                            info.setTarikhKemaskini(new java.util.Date());
////                            Permohonan mhn = permohonanService.findById(SAB);
////                            d.setPermohonan(mhn);
////                            d.setNoRujukan(null);
////                            d.setInfoAudit(info);
////                            dokumenDAO.saveOrUpdate(d);
////                        }
//                    }
//                }
//            }
//        }
//
//        if (StringUtils.isNotBlank(SBD)) {
//            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
//            fd = mohonSurat.getFolderDokumen();
//            if (fd.getSenaraiKandungan() != null) {
//                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
//                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
//                        continue;
//                    }
//                    if (kf.getDokumen() != null) {
//                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
//                        dokumenDAO.delete(d);
////                        String kod = kf.getDokumen().getKodDokumen().getKod();
////                        if (kod.equals("SBD")) {
////                            SBdahAda = true;
////                            info.setDiKemaskiniOleh(peng);
////                            info.setTarikhKemaskini(new java.util.Date());
////                            Permohonan mhn = permohonanService.findById(SBD);
////                            d.setPermohonan(mhn);
////                            d.setNoRujukan(null);
////                            d.setInfoAudit(info);
////                            dokumenDAO.saveOrUpdate(d);
////                        }
//                    }
//                }
//            }
//        }
//
////        PermohonanRujukanLuar updateMohonRujLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPerserahan);
////      idRujukan
//        /*UPDATE MOHON RUJ LUAR*/
//        LOG.info("----- idperserahan :" + idPerserahan);
//
//
//        PermohonanRujukanLuar updateMohonRujLuar = service.findById(Long.parseLong(idRujukan));
//        if (updateMohonRujLuar != null) {
//            service.delete(updateMohonRujLuar);
//        }
        msg = "Kemaskini Data Pembetulan Telah Berjaya";

        return updateUrusanTerperinci();

//        return new RedirectResolution(pembetulanActionBean.class, "updateUrusanTerperinci").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution tambahPihak() {
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        pemohonList = new ArrayList<Pemohon>();
        permohonanPihakList = new ArrayList<PermohonanPihak>();
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
//        if (permohonanPembetulanUrusan != null){
//            permohonanPihakList = pService.findMohonPihakByIdMohonAndIdHakmilik(permohonanPembetulanUrusan.getIdPerserahanLama(), permohonanPembetulanUrusan.getHakmilik().getHakmilik().getIdHakmilik());
//        }
        if (permohonanPembetulanUrusan.getKodUrusan() != null) {
            kodUrusan = permohonanPembetulanUrusan.getKodUrusan().getKod();
            pemohonList = pService.findPemohonbyIdMohonAndIdHakmilikAndKaitan(permohonanPembetulanUrusan.getIdPerserahanLama(), permohonan.getIdPermohonan());
            permohonanPihakList = pService.findMohonPihakbyIdMohonAndIdHakmilikAndKaitan(permohonanPembetulanUrusan.getIdPerserahanLama(), permohonan.getIdPermohonan(), permohonanPembetulanUrusan.getHakmilik().getHakmilik().getIdHakmilik());
//            permohonanPihakList = permohonanPihakService.findMohonPihakByIdHmAndIdMohon(permohonanPembetulanUrusan.getIdPerserahanLama(), idHakmilik);
            if (permohonanPihakList.size() <= 0) {
                permohonanPihakList = pService.findMohonPihakByIdMohonAndIdHakmilik(permohonan.getIdPermohonan(), permohonanPembetulanUrusan.getHakmilik().getHakmilik().getIdHakmilik());
            }
            syerPembilang1 = new String[pemohonList.size()];
            syerPenyebut1 = new String[pemohonList.size()];
            syerPembilang = new String[permohonanPihakList.size()];
            syerPenyebut = new String[permohonanPihakList.size()];

            if (pemohonList != null) {
                int counter = 0;
                for (int i = 0; i < pemohonList.size(); i++) {
                    Pemohon p = pemohonList.get(i);
                    syerPembilang1[counter] = String.valueOf(p.getSyerPembilang());
                    syerPenyebut1[counter] = String.valueOf(p.getSyerPenyebut());
                    counter = counter + 1;
                }
            }

            if (permohonanPihakList != null) {
                int counter = 0;
                for (int i = 0; i < permohonanPihakList.size(); i++) {
                    PermohonanPihak hpb = permohonanPihakList.get(i);
                    syerPembilang[counter] = String.valueOf(hpb.getSyerPembilang());
                    syerPenyebut[counter] = String.valueOf(hpb.getSyerPenyebut());
                    counter = counter + 1;
                }
            }
            permohonanPihakHubunganList = permohonanPihakHubunganService.findMohonPihakByIdMohonAndIdHakmilik(idPermohonanPihak);
        }

        return new JSP("daftar/pembetulan/tambah_pihak.jsp").addParameter("popup", "true");
    }

    public Resolution updateSyerMohonPihakHbgn() {
        String idPermohonanPihakHbgn = getContext().getRequest().getParameter("idPermohonanPihakHbgn");
//        if (idPermohonanPihakHbgn != null){
//            permohonanPihakHubungan = permohonanPihakHubunganDAO.findById(Long.parseLong(idPermohonanPihakHbgn));
//        }
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut
        String umurWarisNew = getContext().getRequest().getParameter("umurWaris");

        if (StringUtils.isNotBlank(idPermohonanPihakHbgn) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            permohonanPihakHubungan = permohonanPihakHubunganDAO.findById(Long.parseLong(idPermohonanPihakHbgn));
            permohonanPihakHubungan.setSyerPembilang(Integer.parseInt(s1));
            permohonanPihakHubungan.setSyerPenyebut(Integer.parseInt(s2));
        }
        if (StringUtils.isNotBlank(idPermohonanPihakHbgn) && StringUtils.isNotBlank(umurWarisNew)) {
            permohonanPihakHubungan = permohonanPihakHubunganDAO.findById(Long.parseLong(idPermohonanPihakHbgn));
            permohonanPihakHubungan.setUmur(Integer.parseInt(umurWarisNew));

        }
        permohonanPihakHubunganService.save(permohonanPihakHubungan);
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution updateSyerMohonPihak() {
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        String s1 = getContext().getRequest().getParameter("syer1");//pembilang
        String s2 = getContext().getRequest().getParameter("syer2");//penyebut

        if (StringUtils.isNotBlank(idPermohonanPihak) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            PermohonanPihak hpb = pService.findMohonPihakbyidPermohonanPihak(Long.parseLong(idPermohonanPihak));
            hpb.setJumlahPembilang(Integer.parseInt(s1));
            hpb.setJumlahPenyebut(Integer.parseInt(s2));
            hpb.setSyerPembilang(Integer.parseInt(s1));
            hpb.setSyerPenyebut(Integer.parseInt(s2));
            pService.simpanMohonPihak(hpb);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution updateSyerPemohon() {
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        String s1 = getContext().getRequest().getParameter("syer3");//pembilang
        String s2 = getContext().getRequest().getParameter("syer4");//penyebut

        if (StringUtils.isNotBlank(idPemohon) && StringUtils.isNotBlank(s1) && StringUtils.isNotBlank(s2)) {
            Pemohon hpb = pService.findPemohonbyIdPemohon(Long.parseLong(idPemohon));
            hpb.setJumlahPembilang(Integer.parseInt(s1));
            hpb.setJumlahPenyebut(Integer.parseInt(s2));
            hpb.setSyerPembilang(Integer.parseInt(s1));
            hpb.setSyerPenyebut(Integer.parseInt(s2));
            pService.updatePemohon(hpb);
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution addUrusan() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        senaraiUrusanPendaftaran = pService.findSCBN();
        idPermohonan = permohonan.getIdPermohonan();
        return new JSP("daftar/pembetulan/add_urusan.jsp").addParameter("popup", "true");
    }

    public Resolution saveUrusanSC() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        aktif = getContext().getRequest().getParameter("aktif");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Copy to all hakmilik Surat Cara##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);
            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update Record - saveUrusanSC");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setAktif(aktif);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINI");

                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    } else {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    }
                    betulUrusan.setTarikhDaftar(trhDaftar);

                    if (StringUtils.isNotBlank(noFolio)) {
                        betulUrusan.setNoFolio(noFolio);
                    }
                    if (StringUtils.isNotBlank(noJilid)) {
                        betulUrusan.setNoJilid(noJilid);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(perjanjianAmaun)) {
                        BigDecimal b = new BigDecimal(perjanjianAmaun);
                        betulUrusan.setPerjanjianAmaun(b);
                    }
                    if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                        BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                        betulUrusan.setPerjanjianDutiStem(b2);
                    }
                    if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                        betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                    }
                    if (StringUtils.isNotBlank(tarikhSaksi)) {
                        trhDaftar = dateFormat.parse(tarikhSaksi);
                        betulUrusan.setTarikhSaksi(trhDaftar);
                    }
                    //Added by Aizuddin for urusan pajakan
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanSC");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINI");
                    betulUrusan.setAktif(aktif);
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noFolio)) {
                        betulUrusan.setNoFolio(noFolio);
                    }
                    if (StringUtils.isNotBlank(noJilid)) {
                        betulUrusan.setNoJilid(noJilid);
                    }
                    if (StringUtils.isNotBlank(perjanjianAmaun)) {
                        BigDecimal b = new BigDecimal(perjanjianAmaun);
                        betulUrusan.setPerjanjianAmaun(b);
                    }
                    if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                        BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                        betulUrusan.setPerjanjianDutiStem(b2);
                    }
                    if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                        betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                    }
                    if (StringUtils.isNotBlank(tarikhSaksi)) {
                        trhDaftar = dateFormat.parse(tarikhSaksi);
                        betulUrusan.setTarikhSaksi(trhDaftar);
                    }
                    //Added by Aizuddin for urusan pajakan
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        trhDaftarBaru = dateFormat.parse(tarikhDaftarBaru);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            mohonUrusan = pService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
            betulUrusan = pService.findUrusanBetul(idUrusan);
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanSC");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINI");
                if (StringUtils.isNotBlank(tarikhDaftar)) {
                    trhDaftar = dateFormat.parse(tarikhDaftar);
                    betulUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }

                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noFolio)) {
                    betulUrusan.setNoFolio(noFolio);
                }
                if (StringUtils.isNotBlank(noJilid)) {
                    betulUrusan.setNoJilid(noJilid);
                }
                if (StringUtils.isNotBlank(perjanjianAmaun)) {
                    BigDecimal b = new BigDecimal(perjanjianAmaun);
                    betulUrusan.setPerjanjianAmaun(b);
                }
                if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                    BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                    betulUrusan.setPerjanjianDutiStem(b2);
                }
                if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                    betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                }
                if (StringUtils.isNotBlank(tarikhSaksi)) {
                    trhDaftar = dateFormat.parse(tarikhSaksi);
                    betulUrusan.setTarikhSaksi(trhDaftar);
                }
//                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
//                    trhDaftarBaru = dateFormat.parse(tarikhDaftarBaru);
//                    betulUrusan.setTarikhDaftar(trhDaftarBaru);
//                }
                //Added by Aizuddin for urusan pajakan
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }
                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanSC_2");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINI");
//                if (StringUtils.isNotBlank(tarikhDaftar)) {
//                    trhDaftar = dateFormat.parse(tarikhDaftar);
//                    betulUrusan.setTarikhDaftar(trhDaftar);
//                }

                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }

                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noFolio)) {
                    betulUrusan.setNoFolio(noFolio);
                }
                if (StringUtils.isNotBlank(noJilid)) {
                    betulUrusan.setNoJilid(noJilid);
                }
                if (StringUtils.isNotBlank(perjanjianAmaun)) {
                    BigDecimal b = new BigDecimal(perjanjianAmaun);
                    betulUrusan.setPerjanjianAmaun(b);
                }
                if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                    BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                    betulUrusan.setPerjanjianDutiStem(b2);
                }
                if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                    betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                }
                if (StringUtils.isNotBlank(tarikhSaksi)) {
                    trhDaftar = dateFormat.parse(tarikhSaksi);
                    betulUrusan.setTarikhSaksi(trhDaftar);
                }
                //Added by Aizuddin for urusan pajakan
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }
                pService.simpanUrusanBetul(betulUrusan);
            }
        }

        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveUrusanSCBatal() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        aktif = getContext().getRequest().getParameter("aktif");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Copy to all hakmilik Surat Cara##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);
            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update Record - saveUrusanSCBatal");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    betulUrusan.setAktif(aktif);

                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    } else {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    }
                    betulUrusan.setTarikhDaftar(trhDaftar);

                    if (StringUtils.isNotBlank(noFolio)) {
                        betulUrusan.setNoFolio(noFolio);
                    }
                    if (StringUtils.isNotBlank(noJilid)) {
                        betulUrusan.setNoJilid(noJilid);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(perjanjianAmaun)) {
                        BigDecimal b = new BigDecimal(perjanjianAmaun);
                        betulUrusan.setPerjanjianAmaun(b);
                    }
                    if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                        BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                        betulUrusan.setPerjanjianDutiStem(b2);
                    }
                    if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                        betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                    }
                    if (StringUtils.isNotBlank(tarikhSaksi)) {
                        trhDaftar = dateFormat.parse(tarikhSaksi);
                        betulUrusan.setTarikhSaksi(trhDaftar);
                    }
                    //Added by Aizuddin for urusan pajakan
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanSCBatal");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setAktif(aktif);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noFolio)) {
                        betulUrusan.setNoFolio(noFolio);
                    }
                    if (StringUtils.isNotBlank(noJilid)) {
                        betulUrusan.setNoJilid(noJilid);
                    }
                    if (StringUtils.isNotBlank(perjanjianAmaun)) {
                        BigDecimal b = new BigDecimal(perjanjianAmaun);
                        betulUrusan.setPerjanjianAmaun(b);
                    }
                    if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                        BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                        betulUrusan.setPerjanjianDutiStem(b2);
                    }
                    if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                        betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                    }
                    if (StringUtils.isNotBlank(tarikhSaksi)) {
                        trhDaftar = dateFormat.parse(tarikhSaksi);
                        betulUrusan.setTarikhSaksi(trhDaftar);
                    }
                    //Added by Aizuddin for urusan pajakan
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        trhDaftarBaru = dateFormat.parse(tarikhDaftarBaru);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            mohonUrusan = pService.findMohonUrusan(hakmilikUrusan.getIdPerserahan());
            betulUrusan = pService.findUrusanBetul(idUrusan);
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanSCBatal");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                if (StringUtils.isNotBlank(tarikhDaftar)) {
                    trhDaftar = dateFormat.parse(tarikhDaftar);
                    betulUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }

                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noFolio)) {
                    betulUrusan.setNoFolio(noFolio);
                }
                if (StringUtils.isNotBlank(noJilid)) {
                    betulUrusan.setNoJilid(noJilid);
                }
                if (StringUtils.isNotBlank(perjanjianAmaun)) {
                    BigDecimal b = new BigDecimal(perjanjianAmaun);
                    betulUrusan.setPerjanjianAmaun(b);
                }
                if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                    BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                    betulUrusan.setPerjanjianDutiStem(b2);
                }
                if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                    betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                }
                if (StringUtils.isNotBlank(tarikhSaksi)) {
                    trhDaftar = dateFormat.parse(tarikhSaksi);
                    betulUrusan.setTarikhSaksi(trhDaftar);
                }
//                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
//                    trhDaftarBaru = dateFormat.parse(tarikhDaftarBaru);
//                    betulUrusan.setTarikhDaftar(trhDaftarBaru);
//                }
                //Added by Aizuddin for urusan pajakan
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }
                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanSCBatal");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
//                if (StringUtils.isNotBlank(tarikhDaftar)) {
//                    trhDaftar = dateFormat.parse(tarikhDaftar);
//                    betulUrusan.setTarikhDaftar(trhDaftar);
//                }

                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }

                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noFolio)) {
                    betulUrusan.setNoFolio(noFolio);
                }
                if (StringUtils.isNotBlank(noJilid)) {
                    betulUrusan.setNoJilid(noJilid);
                }
                if (StringUtils.isNotBlank(perjanjianAmaun)) {
                    BigDecimal b = new BigDecimal(perjanjianAmaun);
                    betulUrusan.setPerjanjianAmaun(b);
                }
                if (StringUtils.isNotBlank(perjanjianDutiStem)) {
                    BigDecimal b2 = new BigDecimal(perjanjianDutiStem);
                    betulUrusan.setPerjanjianDutiStem(b2);
                }
                if (StringUtils.isNotBlank(perjanjianNoRujukan)) {
                    betulUrusan.setPerjanjianNoRujukan(perjanjianNoRujukan);
                }
                if (StringUtils.isNotBlank(tarikhSaksi)) {
                    trhDaftar = dateFormat.parse(tarikhSaksi);
                    betulUrusan.setTarikhSaksi(trhDaftar);
                }
                //Added by Aizuddin for urusan pajakan
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }
                pService.simpanUrusanBetul(betulUrusan);
            }
        }

        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveUrusanB() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        aktif = getContext().getRequest().getParameter("aktif");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Copy to all hakmilik Borang##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);
            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update Record - saveUrusanB");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setAktif(aktif);
                    betulUrusan.setUlasan("KEMASKINI");
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(kodPerintah)) {
                        KodPerintah kp = new KodPerintah();
                        kp.setKod(kodPerintah);
                        betulUrusan.setKodPerintah(kp);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(namaSidang)) {
                        betulUrusan.setItem(namaSidang);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahSebab)) {
                        betulUrusan.setPerintahSebab(perintahSebab);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanB");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setAktif(aktif);
                    betulUrusan.setUlasan("KEMASKINI");
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(kodPerintah)) {
                        KodPerintah kp = new KodPerintah();
                        kp.setKod(kodPerintah);
                        betulUrusan.setKodPerintah(kp);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(namaSidang)) {
                        betulUrusan.setItem(namaSidang);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahSebab)) {
                        betulUrusan.setPerintahSebab(perintahSebab);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            betulUrusan = pService.findUrusanBetul(idUrusan);
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanB");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINI");
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(kodPerintah)) {
                    KodPerintah kp = new KodPerintah();
                    kp.setKod(kodPerintah);
                    betulUrusan.setKodPerintah(kp);
                }
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(namaSidang)) {
                    betulUrusan.setItem(namaSidang);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahSebab)) {
                    betulUrusan.setPerintahSebab(perintahSebab);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }

                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanB_2");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINI");
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(kodPerintah)) {
                    KodPerintah kp = new KodPerintah();
                    kp.setKod(kodPerintah);
                    betulUrusan.setKodPerintah(kp);
                }
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(namaSidang)) {
                    betulUrusan.setItem(namaSidang);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahSebab)) {
                    betulUrusan.setPerintahSebab(perintahSebab);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }

                pService.simpanUrusanBetul(betulUrusan);
            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveUrusanBbatal() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Save Urusan Borang - Batal##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);
            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update Record - saveUrusanBbatal");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    betulUrusan.setAktif(aktif);
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(kodPerintah)) {
                        KodPerintah kp = new KodPerintah();
                        kp.setKod(kodPerintah);
                        betulUrusan.setKodPerintah(kp);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(namaSidang)) {
                        betulUrusan.setItem(namaSidang);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahSebab)) {
                        betulUrusan.setPerintahSebab(perintahSebab);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanBbatal");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    betulUrusan.setAktif(aktif);
                    if (StringUtils.isNotBlank(tarikhDaftar)) {
                        trhDaftar = dateFormat.parse(tarikhDaftar);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(kodPerintah)) {
                        KodPerintah kp = new KodPerintah();
                        kp.setKod(kodPerintah);
                        betulUrusan.setKodPerintah(kp);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(namaSidang)) {
                        betulUrusan.setItem(namaSidang);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahSebab)) {
                        betulUrusan.setPerintahSebab(perintahSebab);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                        trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                        betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhDaftar = dateFormat.parse(tarikhTamat);
                        betulUrusan.setTarikhTamat(trhDaftar);
                    }

                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            betulUrusan = pService.findUrusanBetul(idUrusan);
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanBbatal");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                betulUrusan.setAktif(aktif);
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(kodPerintah)) {
                    KodPerintah kp = new KodPerintah();
                    kp.setKod(kodPerintah);
                    betulUrusan.setKodPerintah(kp);
                }
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(namaSidang)) {
                    betulUrusan.setItem(namaSidang);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahSebab)) {
                    betulUrusan.setPerintahSebab(perintahSebab);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }

                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanBbatal_2");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                betulUrusan.setAktif(aktif);
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(kodPerintah)) {
                    KodPerintah kp = new KodPerintah();
                    kp.setKod(kodPerintah);
                    betulUrusan.setKodPerintah(kp);
                }
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(namaSidang)) {
                    betulUrusan.setItem(namaSidang);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahSebab)) {
                    betulUrusan.setPerintahSebab(perintahSebab);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(perintahTarikhKuatkuasa)) {
                    trhDaftar = dateFormat.parse(perintahTarikhKuatkuasa);
                    betulUrusan.setPerintahTarikhKuatkuasa(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhTamat)) {
                    trhDaftar = dateFormat.parse(tarikhTamat);
                    betulUrusan.setTarikhTamat(trhDaftar);
                }

                pService.simpanUrusanBetul(betulUrusan);
            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveUrusanNBatal() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        aktif = getContext().getRequest().getParameter("aktif");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Copy to all hakmilik Nota##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);

            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update Record - saveUrusanNBatal");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    betulUrusan.setAktif(aktif);
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        } else {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        }
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }

                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(catatan)) {
                        betulUrusan.setCatatan(catatan);
                    }
                    if (StringUtils.isNotBlank(ulasan)) {
                        betulUrusan.setItem(ulasan);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    //Add by Aizuddin to save item
                    if (StringUtils.isNotBlank(majlis)) {
                        betulUrusan.setItem(majlis);
                    }
                    luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                    if (StringUtils.isNotBlank(luasBaru)) {
                        BigDecimal b2 = new BigDecimal(luasBaru);
                        betulUrusan.setLuasTerlibat(b2);
                    }
                    cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                    if (StringUtils.isNotBlank(cukaiBaru)) {
                        BigDecimal b1 = new BigDecimal(cukaiBaru);
                        betulUrusan.setCukaiBaru(b1);
                    }

                    if (StringUtils.isNotBlank(strKodUOM)) {
                        KodUOM k = kodUOMDAO.findById(strKodUOM);
                        betulUrusan.setKodUnitLuas(k);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanNBatal");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                    betulUrusan.setAktif(aktif);
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(catatan)) {
                        betulUrusan.setCatatan(catatan);
                    }
                    if (StringUtils.isNotBlank(ulasan)) {
                        betulUrusan.setItem(ulasan);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(majlis)) {
                        betulUrusan.setItem(majlis);
                    }
                    luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                    if (StringUtils.isNotBlank(luasBaru)) {
                        BigDecimal b2 = new BigDecimal(luasBaru);
                        betulUrusan.setLuasTerlibat(b2);
                    }
                    cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                    if (StringUtils.isNotBlank(cukaiBaru)) {
                        BigDecimal b1 = new BigDecimal(cukaiBaru);
                        betulUrusan.setCukaiBaru(b1);
                    }

                    if (StringUtils.isNotBlank(strKodUOM)) {
                        KodUOM k = kodUOMDAO.findById(strKodUOM);
                        betulUrusan.setKodUnitLuas(k);
                    }

                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            betulUrusan = pService.findUrusanBetul(idUrusan);
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanNBatal");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setAktif(aktif);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                betulUrusan.setAktif(aktif);
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(catatan)) {
                    betulUrusan.setCatatan(catatan);
                }
                if (StringUtils.isNotBlank(ulasan)) {
                    betulUrusan.setItem(ulasan);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                //Add by Aizuddin to save item
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                if (cukaiBaru == null) {
                    cukaiBaru = getContext().getRequest().getParameter("cukai");
                }

                if (StringUtils.isNotBlank(luasBaru)) {
                    BigDecimal b2 = new BigDecimal(luasBaru);
                    betulUrusan.setLuasTerlibat(b2);
                }
                if (StringUtils.isNotBlank(cukaiBaru)) {
                    BigDecimal b1 = new BigDecimal(cukaiBaru);
                    betulUrusan.setCukaiBaru(b1);
                }

                if (StringUtils.isNotBlank(strKodUOM)) {
                    KodUOM k = kodUOMDAO.findById(strKodUOM);
                    betulUrusan.setKodUnitLuas(k);
                }

                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanNBatal_2");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setUlasan("KEMASKINIURUSANBATAL");
                betulUrusan.setAktif(aktif);
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(tarikhDaftar)) {
                    trhDaftar = dateFormat.parse(tarikhDaftar);
                    betulUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(catatan)) {
                    betulUrusan.setCatatan(catatan);
                }
                if (StringUtils.isNotBlank(ulasan)) {
                    betulUrusan.setItem(ulasan);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                if (StringUtils.isNotBlank(luasBaru)) {
                    BigDecimal b2 = new BigDecimal(luasBaru);
                    betulUrusan.setLuasTerlibat(b2);
                }

                cukaiBaru = getContext().getRequest().getParameter("cukai");
                if (StringUtils.isNotBlank(cukaiBaru)) {
                    BigDecimal b1 = new BigDecimal(cukaiBaru);
                    betulUrusan.setCukaiBaru(b1);
                }

                if (StringUtils.isNotBlank(strKodUOM)) {
                    KodUOM k = kodUOMDAO.findById(strKodUOM);
                    betulUrusan.setKodUnitLuas(k);
                }
                pService.simpanUrusanBetul(betulUrusan);
            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveUrusanN() throws ParseException {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        String idPerserahan = pService.findUrusan(idUrusan).getIdPerserahan();
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        aktif = getContext().getRequest().getParameter("aktif");
        tarikhDaftarBaru = getContext().getRequest().getParameter("betulUrusan.tarikhDaftar");

        //Added by Aizuddin for save all hakmilik
        copyToAllHakmilik = getContext().getRequest().getParameter("copyToAll");
        if (StringUtils.isNotBlank(copyToAllHakmilik)) {
            LOG.info("############Copy to all hakmilik Nota##############");
            List<HakmilikUrusan> hakmilikUrusanList = pService.findUrusanbyIDPerserahan(idPerserahan);

            for (HakmilikUrusan hu : hakmilikUrusanList) {
                idUrusan = String.valueOf(hu.getIdUrusan());
                hakmilikUrusan = pService.findUrusan(idUrusan);
                betulUrusan = pService.findUrusanBetul(idUrusan);
                LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
//                pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
                pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

                if (betulUrusan != null) {
                    LOG.info("update  - saveUrusanN");
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        } else {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        }
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }

                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(catatan)) {
                        betulUrusan.setCatatan(catatan);
                    }
                    if (StringUtils.isNotBlank(ulasan)) {
                        betulUrusan.setItem(ulasan);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    //Add by Aizuddin to save item
                    if (StringUtils.isNotBlank(majlis)) {
                        betulUrusan.setItem(majlis);
                    }
                    luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                    if (StringUtils.isNotBlank(luasBaru)) {
                        BigDecimal b2 = new BigDecimal(luasBaru);
                        betulUrusan.setLuasTerlibat(b2);
                    }
                    cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                    if (StringUtils.isNotBlank(cukaiBaru)) {
                        BigDecimal b1 = new BigDecimal(cukaiBaru);
                        betulUrusan.setCukaiBaru(b1);
                    }
                    //cukai lama yus edit 14022019
                    cukaiLama = getContext().getRequest().getParameter("betulUrusan.cukaiLama");
                    LOG.info("cukaiLama --> " + cukaiLama);
                    if (StringUtils.isNotBlank(cukaiLama)) {
                        BigDecimal b3 = new BigDecimal(cukaiLama);
                        betulUrusan.setCukaiLama(b3);
                    }
                    //cukai lama yus edit 14022019 end
                    if (StringUtils.isNotBlank(strKodUOM)) {
                        KodUOM k = kodUOMDAO.findById(strKodUOM);
                        betulUrusan.setKodUnitLuas(k);
                    }

                    pService.updateUrusanBetul(betulUrusan);
                    msg = "Kemasikini Data Pembetulan Telah Berjaya";
                } else {
                    betulUrusan = new PermohonanPembetulanUrusan();
                    LOG.info("New Record - saveUrusanN");
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    betulUrusan.setInfoAudit(info);
                    betulUrusan.setCawangan(peng.getKodCawangan());
                    betulUrusan.setPermohonan(permohonan);
                    betulUrusan.setUrusan(hakmilikUrusan);
                    betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                    betulUrusan.setHakmilik(hakmilikPermohonan);
                    betulUrusan.setUlasan("KEMASKINI");
                    if (StringUtils.isNotBlank(noFail)) {
                        betulUrusan.setNoFail(noFail);
                    }
                    if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        } else {
                            trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                            betulUrusan.setTarikhDaftar(trhDaftar);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhDaftar = dateFormat.parse(tarikhSidang);
                        betulUrusan.setTarikhSidang(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhDaftar = dateFormat.parse(tarikhRujukan);
                        betulUrusan.setTarikhRujukan(trhDaftar);
                    }
                    if (StringUtils.isNotBlank(noRujukan)) {
                        betulUrusan.setNoRujukan(noRujukan);
                    }
                    if (StringUtils.isNotBlank(noSidang)) {
                        betulUrusan.setNoSidang(noSidang);
                    }
                    if (StringUtils.isNotBlank(catatan)) {
                        betulUrusan.setCatatan(catatan);
                    }
                    if (StringUtils.isNotBlank(ulasan)) {
                        betulUrusan.setItem(ulasan);
                    }
                    if (StringUtils.isNotBlank(tempohTahun)) {
                        betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                    }
                    if (StringUtils.isNotBlank(tempohBulan)) {
                        betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                    }
                    if (StringUtils.isNotBlank(tempohHari)) {
                        betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                    }
                    if (StringUtils.isNotBlank(majlis)) {
                        betulUrusan.setItem(majlis);
                    }
                    luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                    if (StringUtils.isNotBlank(luasBaru)) {
                        BigDecimal b2 = new BigDecimal(luasBaru);
                        betulUrusan.setLuasTerlibat(b2);
                    }
                    cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                    if (StringUtils.isNotBlank(cukaiBaru)) {
                        BigDecimal b1 = new BigDecimal(cukaiBaru);
                        betulUrusan.setCukaiBaru(b1);
                    }

                    cukaiLama = getContext().getRequest().getParameter("betulUrusan.cukaiLama");
                    LOG.info("cukaiLama --> " + cukaiLama);
                    if (StringUtils.isNotBlank(cukaiLama)) {
                        BigDecimal b3 = new BigDecimal(cukaiLama);
                        betulUrusan.setCukaiBaru(b3);
                    }
                    if (StringUtils.isNotBlank(strKodUOM)) {
                        KodUOM k = kodUOMDAO.findById(strKodUOM);
                        betulUrusan.setKodUnitLuas(k);
                    }

                    pService.simpanUrusanBetul(betulUrusan);
                }
            }
        } else {

            hakmilikUrusan = pService.findUrusan(idUrusan);
            betulUrusan = pService.findUrusanBetul(idUrusan);
//            pkl = pService.findRujukanIDMohon(hakmilikUrusan.getIdPerserahan());
            LOG.info("ID Mohon, " + hakmilikUrusan.getIdPerserahan() + " ID Hakmilik, " + hakmilikUrusan.getHakmilik().getIdHakmilik());
            pkl = pService.findRujukanIDMohonHakmilik(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
            hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());

            if (betulUrusan != null) {
                LOG.info("update Record 2 - saveUrusanN");
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setUlasan("KEMASKINI");
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhDaftarBaru)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    } else {
                        trhconvert = tarikhDaftarBaru + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                        betulUrusan.setTarikhDaftar(trhDaftar);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(catatan)) {
                    betulUrusan.setCatatan(catatan);
                }
                if (StringUtils.isNotBlank(ulasan)) {
                    betulUrusan.setItem(ulasan);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                //Add by Aizuddin to save item
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                cukaiBaru = getContext().getRequest().getParameter("betulUrusan.cukaiBaru");
                cukaiLama = getContext().getRequest().getParameter("betulUrusan.cukaiLama");
                luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                if (cukaiBaru == null) {
                    cukaiBaru = getContext().getRequest().getParameter("cukai");
                }

                if (StringUtils.isNotBlank(luasBaru)) {
                    BigDecimal b2 = new BigDecimal(luasBaru);
                    betulUrusan.setLuasTerlibat(b2);
                }
                if (StringUtils.isNotBlank(cukaiBaru)) {
                    BigDecimal b1 = new BigDecimal(cukaiBaru);
                    betulUrusan.setCukaiBaru(b1);
                }
                if (StringUtils.isNotBlank(cukaiLama)) {
                    BigDecimal b3 = new BigDecimal(cukaiLama);
                    betulUrusan.setCukaiLama(b3);
                }

                if (StringUtils.isNotBlank(strKodUOM)) {
                    KodUOM k = kodUOMDAO.findById(strKodUOM);
                    betulUrusan.setKodUnitLuas(k);
                }

                pService.updateUrusanBetul(betulUrusan);
                msg = "Kemasikini Data Pembetulan Telah Berjaya";

                return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
            } else {
                betulUrusan = new PermohonanPembetulanUrusan();
                LOG.info("New Record - saveUrusanN_2");
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                betulUrusan.setInfoAudit(info);
                betulUrusan.setCawangan(peng.getKodCawangan());
                betulUrusan.setPermohonan(permohonan);
                betulUrusan.setUrusan(hakmilikUrusan);
                betulUrusan.setKodUrusan(permohonan.getKodUrusan());
                betulUrusan.setHakmilik(hakmilikPermohonan);
                betulUrusan.setUlasan("KEMASKINI");
                if (StringUtils.isNotBlank(noFail)) {
                    betulUrusan.setNoFail(noFail);
                }
                if (StringUtils.isNotBlank(tarikhDaftar)) {
                    trhDaftar = dateFormat.parse(tarikhDaftar);
                    betulUrusan.setTarikhDaftar(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhSidang)) {
                    trhDaftar = dateFormat.parse(tarikhSidang);
                    betulUrusan.setTarikhSidang(trhDaftar);
                }
                if (StringUtils.isNotBlank(tarikhRujukan)) {
                    trhDaftar = dateFormat.parse(tarikhRujukan);
                    betulUrusan.setTarikhRujukan(trhDaftar);
                }
                if (StringUtils.isNotBlank(noRujukan)) {
                    betulUrusan.setNoRujukan(noRujukan);
                }
                if (StringUtils.isNotBlank(noSidang)) {
                    betulUrusan.setNoSidang(noSidang);
                }
                if (StringUtils.isNotBlank(catatan)) {
                    betulUrusan.setCatatan(catatan);
                }
                if (StringUtils.isNotBlank(ulasan)) {
                    betulUrusan.setItem(ulasan);
                }
                if (StringUtils.isNotBlank(tempohTahun)) {
                    betulUrusan.setTempohTahun(Integer.valueOf(tempohTahun));
                }
                if (StringUtils.isNotBlank(tempohBulan)) {
                    betulUrusan.setTempohBulan(Integer.valueOf(tempohBulan));
                }
                if (StringUtils.isNotBlank(tempohHari)) {
                    betulUrusan.setTempohHari(Integer.valueOf(tempohHari));
                }
                if (StringUtils.isNotBlank(majlis)) {
                    betulUrusan.setItem(majlis);
                }
                luasBaru = getContext().getRequest().getParameter("betulUrusan.luasTerlibat");
                if (StringUtils.isNotBlank(luasBaru)) {
                    BigDecimal b2 = new BigDecimal(luasBaru);
                    betulUrusan.setLuasTerlibat(b2);
                }

                cukaiBaru = getContext().getRequest().getParameter("cukai");
                if (StringUtils.isNotBlank(cukaiBaru)) {
                    BigDecimal b1 = new BigDecimal(cukaiBaru);
                    betulUrusan.setCukaiBaru(b1);
                }

                if (StringUtils.isNotBlank(strKodUOM)) {
                    KodUOM k = kodUOMDAO.findById(strKodUOM);
                    betulUrusan.setKodUnitLuas(k);
                }
                pService.simpanUrusanBetul(betulUrusan);
            }
        }
        msg = "Kemasukan Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution saveTambahUrusan() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        tarikhDaftarUrusan = getContext().getRequest().getParameter("tarikhDaftarUrusan");
        kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        noFolio = getContext().getRequest().getParameter("noFolio");
        noJilid = getContext().getRequest().getParameter("noJilid");
        noFail = getContext().getRequest().getParameter("noFail");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        kodNegeri = conf.getProperty("kodNegeri");
        String copyToAll = getContext().getRequest().getParameter("copyToAll");

//        List<PermohonanPihak>senaraiMp = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilik, idPerserahan);
        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
        permohonanPembetulanUrusan = pService.findByidPerserahan(String.valueOf(permohonan.getIdPermohonan()), idPerserahan, idHakmilik);

        if (StringUtils.isNotBlank(idPerserahan)) {
            Permohonan mohonLama = permohonanService.findById(idPerserahan);
            if (mohonLama == null) {
                mohonLama = new Permohonan();
                HakmilikPermohonan mhm = new HakmilikPermohonan();
                KodJabatan k = new KodJabatan();

                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                mohonLama.setIdPermohonan(idPerserahan);
                mohonLama.setCawangan(peng.getKodCawangan());
                mohonLama.setKodUrusan(ku);
                mohonLama.setStatus("SL");
                InfoAudit iaPermohonan = new InfoAudit();
                Date d = new Date();
                if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    } else {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    iaPermohonan.setTarikhMasuk(trhDaftar);
                    mohonLama.setTarikhKeputusan(trhDaftar);
                } else {
                    iaPermohonan.setTarikhMasuk(d);
                }
                iaPermohonan.setDimasukOleh(peng);
                mohonLama.setInfoAudit(iaPermohonan);
//        permohonanDAO.save(p);
                permohonanService.saveOrUpdate(mohonLama);

                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                Permohonan checkMohon2 = permohonanDAO.findById(idPerserahan);
                mhm.setPermohonan(mohonLama);
                mhm.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
                mhm.setInfoAudit(info);
                pService.saveMohonHakmilik(mhm);
            }

            if (mohonLama != null) {
                permohonanPembetulanUrusan = new PermohonanPembetulanUrusan();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
                permohonanPembetulanUrusan.setInfoAudit(info);
                permohonanPembetulanUrusan.setPermohonan(permohonan);
                permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
                permohonanPembetulanUrusan.setIdPerserahanLama(mohonLama.getIdPermohonan());
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                permohonanPembetulanUrusan.setKodUrusan(ku);
                permohonanPembetulanUrusan.setUlasan("TAMBAHBARU");
                LOG.info(tarikhDaftarUrusan);
                if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                    if (StringUtils.isNotBlank(jam)) {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    } else {
                        trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                        LOG.info(trhconvert);
                        trhDaftar = dateTimeFormat.parse(trhconvert);
                    }
                    permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                }

                if (StringUtils.isNotBlank(noJilid)) {
                    permohonanPembetulanUrusan.setNoJilid(noJilid);
                }
                if (StringUtils.isNotBlank(noFolio)) {
                    permohonanPembetulanUrusan.setNoFolio(noFolio);
                }
                if (StringUtils.isNotBlank(noFail)) {
                    permohonanPembetulanUrusan.setNoFail(noFail);
                }
                
                if (permohonanPembetulanUrusan.getIdPerserahanLama() != null) {
                    idPerserahan = permohonanPembetulanUrusan.getIdPerserahanLama();
                    permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, permohonanPembetulanUrusan.getIdPerserahanLama());
                    LOG.info(" /* Insert Id_mohon if table Mohon_ruj_luar null*/ ");
                    if (permohonanRujukanLuar == null) {
                        PermohonanRujukanLuar mohonrujluar = new PermohonanRujukanLuar();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        Permohonan checkMohon = permohonanDAO.findById(idPerserahan);
                        mohonrujluar.setPermohonan(checkMohon);
                        mohonrujluar.setInfoAudit(info);
                        mohonrujluar.setCawangan(peng.getKodCawangan());
                        mohonrujluar.setKodRujukan(kodRujukanDAO.findById("FL"));
                        mohonrujluar.setCatatan("Kemasukan BETUR");
                        if (StringUtils.isNotBlank(noFail)) {
                            mohonrujluar.setNoFail(noFail);
                        }
                        mohonrujluar.setHakmilik(hakmilikPermohonan.getHakmilik());
                        mohonrujluar.setNilai2(BigDecimal.valueOf(permohonanPembetulanUrusan.getIdPembetulan()));
                        service.saveOrUpdate(mohonrujluar);
                    }                   
                }
                
            } else {
                if (StringUtils.isBlank(idPerserahan)) {
                    //generate ID Permohonan Paksa
                    KodJabatan k = new KodJabatan();
                    k.setAkronim("F");
                    KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                    // add by azri 24/7/2013: insert id_mohon Paksa into table mohon   
                    LOG.info(" /* Insert Id_mohon Paksa into table Mohon */ ");
                    Permohonan p = new Permohonan();
                    p.setIdPermohonan(generatorIdPerserahanPaksa.generate(kodNegeri, peng.getKodCawangan(), ku));
                    p.setCawangan(peng.getKodCawangan());
                    p.setKodUrusan(ku);
                    p.setStatus("SL");
                    InfoAudit iaPermohonan = new InfoAudit();
                    Date d = new Date();
                    if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        } else {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        }
//                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                        iaPermohonan.setTarikhMasuk(trhDaftar);
                        p.setTarikhKeputusan(trhDaftar);
                    } else {
                        iaPermohonan.setTarikhMasuk(d);
                    }
                    iaPermohonan.setDimasukOleh(peng);
                    p.setInfoAudit(iaPermohonan);
//        permohonanDAO.save(p);
                    permohonanService.saveOrUpdate(p);

//        idPerserahan = generatorIdPerserahanPaksa.generate(kodNegeri, peng.getKodCawangan(), k);
                    idPerserahan = p.getIdPermohonan();
                    permohonanPembetulanUrusan = new PermohonanPembetulanUrusan();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
                    permohonanPembetulanUrusan.setInfoAudit(info);
                    permohonanPembetulanUrusan.setPermohonan(permohonan);
                    permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
                    permohonanPembetulanUrusan.setIdPerserahanLama(permohonan.getIdPermohonan());
                    permohonanPembetulanUrusan.setKodUrusan(ku);
                    LOG.info(tarikhDaftarUrusan);
                    if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                        if (StringUtils.isNotBlank(jam)) {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        } else {
                            trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                            LOG.info(trhconvert);
                            trhDaftar = dateTimeFormat.parse(trhconvert);
                        }
                        permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                    }

                    if (StringUtils.isNotBlank(noJilid)) {
                        permohonanPembetulanUrusan.setNoJilid(noJilid);
                    }
                    if (StringUtils.isNotBlank(noFolio)) {
                        permohonanPembetulanUrusan.setNoFolio(noFolio);
                    }
                    if (StringUtils.isNotBlank(noFail)) {
                        permohonanPembetulanUrusan.setNoFail(noFail);
                    }
                    permohonanPembetulanUrusan.setIdPerserahanLama(p.getIdPermohonan());

                }
                Permohonan mohon = permohonanService.findById(idPerserahan);
                HakmilikPermohonan mhm = new HakmilikPermohonan();
                KodJabatan k = new KodJabatan();

                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                Permohonan checkMohon2 = permohonanDAO.findById(idPerserahan);
                mhm.setPermohonan(mohon);
                mhm.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
                mhm.setInfoAudit(info);
                pService.saveMohonHakmilik(mhm);
            }
            pService.simpanTambahUrusan(permohonanPembetulanUrusan);
            
            if (mohonLama == null) {
                LOG.info(" /* Insert Id_mohon Paksa into table Mohon_ruj_luar */ ");
                PermohonanRujukanLuar mohonrujluar = new PermohonanRujukanLuar();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                Permohonan checkMohon = permohonanDAO.findById(idPerserahan);
                mohonrujluar.setPermohonan(checkMohon);
                mohonrujluar.setInfoAudit(info);
                mohonrujluar.setCawangan(peng.getKodCawangan());
                mohonrujluar.setKodRujukan(kodRujukanDAO.findById("FL"));
                mohonrujluar.setCatatan("Kemasukan BETUR");
                if (StringUtils.isNotBlank(noFail)) {
                    mohonrujluar.setNoFail(noFail);
                }
                mohonrujluar.setHakmilik(hakmilikPermohonan.getHakmilik());
                mohonrujluar.setNilai2(BigDecimal.valueOf(permohonanPembetulanUrusan.getIdPembetulan()));
                service.saveOrUpdate(mohonrujluar);
            }

            // add by azri 23/7/2013 : insert 'id_mohon Paksa' into mohon_hakmilik         
            HakmilikPermohonan mhm = pService.findByIdHakmilikIdPermohonan(idPerserahan, hakmilikPermohonan.getHakmilik().getIdHakmilik());
            if (mhm == null) {
                LOG.info(" /* Insert Id_mohon Paksa into table Mohon_Hakmilik */ ");
                mhm = new HakmilikPermohonan();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                Permohonan checkMohon2 = permohonanDAO.findById(idPerserahan);
                mhm.setPermohonan(checkMohon2);
                mhm.setHakmilik(hakmilikPermohonan.getHakmilik());
                mhm.setInfoAudit(info);
                pService.saveMohonHakmilik(mhm);
            }

            msg = "Kemasukan Data Pembetulan Telah Berjaya";

            if (StringUtils.isNotBlank(copyToAll)) {
                List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                    Hakmilik hm = hp.getHakmilik();
                    if (idHakmilik.equals(hm.getIdHakmilik())) {
                        continue;
                    }
                    PermohonanPembetulanUrusan ppu = new PermohonanPembetulanUrusan();
                    ppu.setCawangan(permohonanPembetulanUrusan.getCawangan());
                    ppu.setInfoAudit(permohonanPembetulanUrusan.getInfoAudit());
                    ppu.setPermohonan(permohonanPembetulanUrusan.getPermohonan());
                    ppu.setHakmilik(hp);
                    ppu.setKodUrusan(permohonanPembetulanUrusan.getKodUrusan());
                    ppu.setTarikhDaftar(permohonanPembetulanUrusan.getTarikhDaftar());
                    ppu.setIdPerserahanLama(permohonanPembetulanUrusan.getIdPerserahanLama());
                    ppu.setNoJilid(permohonanPembetulanUrusan.getNoJilid());
                    ppu.setNoFolio(permohonanPembetulanUrusan.getNoFolio());
                    ppu.setNoFail(permohonanPembetulanUrusan.getNoFail());

                    pService.simpanTambahUrusan(ppu);
                }
            }
            msg = "Kemasukan Data Pembetulan Telah Berjaya";
        } else {

            //generate ID Permohonan Paksa
            KodJabatan k = new KodJabatan();
            k.setAkronim("F");
            KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
            // add by azri 24/7/2013: insert id_mohon Paksa into table mohon   
            LOG.info(" /* Insert Id_mohon Paksa into table Mohon */ ");
            Permohonan p = new Permohonan();
            p.setIdPermohonan(generatorIdPerserahanPaksa.generate(kodNegeri, peng.getKodCawangan(), ku));
            p.setCawangan(peng.getKodCawangan());
            p.setKodUrusan(ku);
            p.setStatus("SL");
            InfoAudit iaPermohonan = new InfoAudit();
            Date d = new Date();
            if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                if (StringUtils.isNotBlank(jam)) {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                } else {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                }
//                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
                iaPermohonan.setTarikhMasuk(trhDaftar);
                p.setTarikhKeputusan(trhDaftar);
            } else {
                iaPermohonan.setTarikhMasuk(d);
            }
            iaPermohonan.setDimasukOleh(peng);
            p.setInfoAudit(iaPermohonan);
//        permohonanDAO.save(p);
            permohonanService.saveOrUpdate(p);

//        idPerserahan = generatorIdPerserahanPaksa.generate(kodNegeri, peng.getKodCawangan(), k);
            idPerserahan = p.getIdPermohonan();
            permohonanPembetulanUrusan = new PermohonanPembetulanUrusan();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
            permohonanPembetulanUrusan.setInfoAudit(info);
            permohonanPembetulanUrusan.setPermohonan(permohonan);
            permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
            permohonanPembetulanUrusan.setIdPerserahanLama(idPerserahan);
            permohonanPembetulanUrusan.setKodUrusan(ku);
            LOG.info(tarikhDaftarUrusan);
            if (StringUtils.isNotBlank(tarikhDaftarUrusan)) {
                if (StringUtils.isNotBlank(jam)) {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                } else {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                }
                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
            }

            if (StringUtils.isNotBlank(noJilid)) {
                permohonanPembetulanUrusan.setNoJilid(noJilid);
            }
            if (StringUtils.isNotBlank(noFolio)) {
                permohonanPembetulanUrusan.setNoFolio(noFolio);
            }
            if (StringUtils.isNotBlank(noFail)) {
                permohonanPembetulanUrusan.setNoFail(noFail);
            }
            Permohonan mohon = permohonanService.findById(idPerserahan);
            HakmilikPermohonan mhm = new HakmilikPermohonan();
//                KodJabatan k = new KodJabatan();

//                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            Permohonan checkMohon2 = permohonanDAO.findById(idPerserahan);
            mhm.setPermohonan(mohon);
            mhm.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
            mhm.setInfoAudit(info);
            pService.saveMohonHakmilik(mhm);
//            permohonanPembetulanUrusan.setIdPerserahanLama(permohonan.getIdPermohonan());
            pService.simpanTambahUrusan(permohonanPembetulanUrusan);

            // add by nad 03/7/2019 : insert 'id_mohon Paksa' into Mohon_ruj_luar
            LOG.info(" /* Insert Id_mohon Paksa into table Mohon_ruj_luar */ ");
            PermohonanRujukanLuar mohonrujluar = new PermohonanRujukanLuar();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            Permohonan checkMohon = permohonanDAO.findById(idPerserahan);
            mohonrujluar.setPermohonan(checkMohon);
            mohonrujluar.setInfoAudit(info);
            mohonrujluar.setCawangan(peng.getKodCawangan());
            mohonrujluar.setKodRujukan(kodRujukanDAO.findById("FL"));
            mohonrujluar.setCatatan("Kemasukan BETUR");
            if (StringUtils.isNotBlank(noFail)) {
                mohonrujluar.setNoFail(noFail);
            }
            mohonrujluar.setHakmilik(hakmilikPermohonan.getHakmilik());
            mohonrujluar.setNilai2(BigDecimal.valueOf(permohonanPembetulanUrusan.getIdPembetulan()));
            service.saveOrUpdate(mohonrujluar);

        }

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChanges3() {
        String error = "";
        String msg = "";
        idPembetulan = (String) getContext().getRequest().getParameter("idPembetulan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idPembetulan : " + idPembetulan);
        if (idPembetulan != null) {
            permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
            String idmohon = permohonanPembetulanUrusan.getIdPerserahanLama(); // get id_mohon Paksa

            //PermohonanRujukanLuar mohonRujLuar = pService.findRujukanIDMohonHakmilik(permohonanPembetulanUrusan.getIdPerserahanLama(), idHakmilik);
            PermohonanRujukanLuar mohonRujLuar = pService.findRujukanIDMohonHakmilik2(permohonanPembetulanUrusan.getIdPerserahanLama(), idHakmilik, Long.valueOf(idPembetulan));
            if (mohonRujLuar != null) {
                service.delete(mohonRujLuar);
            }
            //add by azri 23/7/2013 
            /*Delete ID paksa from table mohon_ruj_luar*/
            //PermohonanRujukanLuar mrl = service.findMohonRujukLuarIdHakmilikIdPermohonanCatatanBETUR(idHakmilik, idmohon);
            PermohonanRujukanLuar mrl = service.findMohonRujukLuarIdHakmilikIdPermohonanCatatanBETUR2(idHakmilik, idmohon, Long.valueOf(idPembetulan));
            if (mrl != null) {
                LOG.info(" /* DELETE table mohon_ruj_luar */ ");
                service.delete(mrl);
            }

            if (permohonanPembetulanUrusan != null) {
                LOG.info(" /* DELETE table mohon_urusan_betul */ ");
                pService.deleteTambahUrusan(permohonanPembetulanUrusan);
            }

            /*Delete ID paksa from table mohon_hakmilik*/
            HakmilikPermohonan mhm = pService.findByIdHakmilikIdPermohonan(idmohon, idHakmilik);
            if (mhm != null) {
                LOG.info(" /* DELETE table Mohon_Hakmilik */ ");
                pService.deletMohonHakmilik(mhm);
            }
            /*Delete Mohon_Urusan_Betul*/


            /*Delete ID paksa from table mohon*/
            Permohonan mohon = permohonanDAO.findById(idmohon);
//            if (mohon != null) {
//                LOG.info(" /* DELETE table mohon */ ");
//                permohonanService.deletePermohonan(mohon);
//            }//add by azri END

            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChanges4() {
        String error = "";
        String msg = "";
        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        LOG.debug("idUrusan : " + idUrusan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        if (idUrusan != null) {
            permohonanPembetulanUrusan = pService.findByidUrusan(permohonan.getIdPermohonan(), idUrusan);
            HakmilikUrusan hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));

            if (hakmilikUrusan != null) {
                LOG.info("hakmilik urusan = " + hakmilikUrusan.getHakmilik().getIdHakmilik());
                LOG.info("hakmilik urusan = " + hakmilikUrusan.getIdPerserahan());
                List<PermohonanRujukanLuar> mohonRujLuarList = service.findMohonRujukLuarIdHakmilikIdPermohonanList(hakmilikUrusan.getHakmilik().getIdHakmilik(), hakmilikUrusan.getIdPerserahan());
                for (PermohonanRujukanLuar rl : mohonRujLuarList) {
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    rl.setInfoAudit(info);
                    //rl.setUlasanMandatori("T"); //nad comment sebab nak hapus kemasukan pembetulan dalam baiki & baiki terperinci je
                    service.update(rl);
                    break;
                }
            }

            LOG.info("status hakmilik urusan = " + hakmilikUrusan.getAktif());
            if (hakmilikUrusan != null) {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hakmilikUrusan.setInfoAudit(info);
                //hakmilikUrusan.setAktif('T'); //nad comment
                hakmilikUrusanList.add(hakmilikUrusan);

//                 hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
            }
            LOG.info("status hakmilik urusan = " + hakmilikUrusan.getAktif());

            if (permohonanPembetulanUrusan != null) {
                pService.deleteTambahUrusan(permohonanPembetulanUrusan);
            }
            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteTotal() {
        String error = "";
        String msg = "";

        idUrusan = (String) getContext().getRequest().getParameter("idUrusan");
        LOG.debug("idUrusan : " + idUrusan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        HakmilikUrusan hakmilikUrusan = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));

        if (hakmilikUrusan != null) {
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = pService.findPihakbyIdHakmilikAndIdUrusanOnly(String.valueOf(hakmilikUrusan.getIdUrusan()), hakmilikUrusan.getHakmilik().getIdHakmilik());
            LOG.info("hakmilikPihakList =: " + hakmilikPihakList.size());
            for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {
                hakmilikPihakBerkepentinganDAO.delete(hakmilikPihak);
            }

//            hakmilikUrusanDAO.delete(hakmilikUrusan);
            hakmilikUrusanService.deleteHakmilikUrusan(hakmilikUrusan);
        }

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    //added by eda on 15/12/2017
    public Resolution deleteHakmililAsal() {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idHakmilikAsal = (String) getContext().getRequest().getParameter("idHakmilikAsal");
        hakmilikAsal = pService.findByidHA(Long.valueOf(idHakmilikAsal));

        if (idHakmilikAsal != null) {
            pService.deleteHakmilikAsal(hakmilikAsal);
            msg = "Data Telah Berjaya diHapuskan";
        }
        //return new RedirectResolution(etanah.view.stripes.nota.pembetulanActionBean.class).addParameter("message", msg);
        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChangesAsal() {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idHakmilikAsal = (String) getContext().getRequest().getParameter("idHakmilikAsal");
        LOG.debug("idHakmilikAsal: " + idHakmilikAsal);
        if (idHakmilikAsal != null) {
            permohonanPembetulanHakmilik = pService.findByidBetul(Long.parseLong(idHakmilikAsal));
            if (permohonanPembetulanHakmilik != null) {
                pService.deleteUrusanHakmilikBetul(permohonanPembetulanHakmilik);
            }
            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteChangesSblm() {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idHakmilikSebelum = (String) getContext().getRequest().getParameter("idHakmilikSebelum");
        LOG.debug("idHakmilikSebelum : " + idHakmilikSebelum);
        if (idHakmilikSebelum != null) {
            hakmilikSebelumPermohonan = regService.searchMohonHakmilikSblmByMhs(Long.parseLong(idHakmilikSebelum));
            if (hakmilikSebelumPermohonan != null) {
                regService.deleteMohonHakmilikSblm(hakmilikSebelumPermohonan);
            }
            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteBetulHakmilik() {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idHakmilik : " + idHakmilik);
        if (idHakmilik != null) {
            permohonanPembetulanHakmilik = pService.findByidPidH(permohonan.getIdPermohonan(), idHakmilik);
            if (permohonanPembetulanHakmilik != null) {
                pService.deleteUrusanHakmilikBetul(permohonanPembetulanHakmilik);
            }
            msg = "Data Telah Berjaya diHapuskan";

        }
        return new RedirectResolution(pembetulanActionBean.class, "asasHakmilik").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteHakmilikPihak() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idSblm = getContext().getRequest().getParameter("idSblm");

        hakmilikSebelum = hakmilikSebelumDAO.findById(Long.parseLong(idSblm));
        regService.deleteHakmilikSblm(hakmilikSebelum);

//        hakmilikSebelumDAO.delete(hakmilikSebelum);
//        PermohonanPihakKemaskini mpkk = new PermohonanPihakKemaskini();
//
//        mpkk.setNamaMedan("HapusHakmilikSblm");
//        mpkk.setNilaiBaru(String.valueOf(hakmilikSebelum.getIdSebelum()));
//        mpkk.setPermohonan(permohonan);
//        mpkk.setInfoAudit(info);
//        permohonanPihakKkiniService.delete(permohonanPihakKemaskini);
//        return new RedirectResolution(etanah.view.stripes.nota.pembetulanActionBean.class);
        return new RedirectResolution(etanah.view.stripes.nota.pembetulanActionBean.class, "asasHakmilik");

//        return new RedirectResolution(etanah.view.stripes.nota.pembetulanActionBean.class);
    }

    public Resolution deleteHakmilikPihak2() {

        String idMhn = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idSblm = getContext().getRequest().getParameter("idSblm");

        hakmilikSebelum = hakmilikSebelumDAO.findById(Long.parseLong(idSblm));

        PermohonanPihakKemaskini mpkk = new PermohonanPihakKemaskini();

        mpkk.setNamaMedan("HapusHakmilikSblm");
        mpkk.setNilaiBaru(String.valueOf(hakmilikSebelum.getIdSebelum()));
        mpkk.setPermohonan(permohonan);
        mpkk.setInfoAudit(info);
        permohonanPihakKkiniService.save(mpkk);

        return new RedirectResolution(etanah.view.stripes.nota.pembetulanActionBean.class);
    }

    public Resolution updateTambahUrusan() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        tarikhDaftarUrusan = getContext().getRequest().getParameter("tarikhDaftar");
        kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        noFolio = getContext().getRequest().getParameter("noFolio");
        noJilid = getContext().getRequest().getParameter("noJilid");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        luasTerlibat = getContext().getRequest().getParameter("luasTerlibat");
        strKodUOM = getContext().getRequest().getParameter("strKodUOM");
        noFail = getContext().getRequest().getParameter("noFail");
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        cukaiBaru = getContext().getRequest().getParameter("cukaiBaru");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kawasan = getContext().getRequest().getParameter("kawasan");
        tempohTahun = getContext().getRequest().getParameter("tempohTahun");
        tempohBulan = getContext().getRequest().getParameter("tempohBulan");
        tempohHari = getContext().getRequest().getParameter("tempohHari");
        trhKuasa = getContext().getRequest().getParameter("trhKuasa");
        trhTamat = getContext().getRequest().getParameter("trhTamat");

        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);

        //tambah SW ni ha
        if (StringUtils.isNotBlank(SWD)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen() != null) {
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SWD")) {
                            SWdahAda = true;
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            Permohonan mhn = permohonanService.findById(SWD);
                            d.setPermohonan(mhn);
                            d.setNoRujukan(SWD);
                            d.setInfoAudit(info);
                            dokumenDAO.saveOrUpdate(d);
                        }
                    }
                }
            }

            if (!SWdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SWD");

                Permohonan mohonSurat2 = permohonanService.findById(SWD);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
                d.setPermohonan(mohonSurat2);
                d.setTajuk("Surat Kuasa Wakil Daftar");
                d.setNoRujukan(SWD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }

        if (StringUtils.isNotBlank(SAB)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen() != null) {
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SAB")) {
                            SAdahAda = true;
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            Permohonan mhn = permohonanService.findById(SAB);
                            d.setPermohonan(mhn);
                            d.setNoRujukan(SAB);
                            d.setInfoAudit(info);
                            dokumenDAO.saveOrUpdate(d);
                        }
                    }
                }
            }

            if (!SAdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SAB");

                Permohonan mohonSurat2 = permohonanService.findById(SAB);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
                d.setPermohonan(mohonSurat2);
                d.setTajuk("Surat Amanah Baru");
                d.setNoRujukan(SAB);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }

        if (StringUtils.isNotBlank(SBD)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen() != null) {
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SBD")) {
                            SBdahAda = true;
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            Permohonan mhn = permohonanService.findById(SBD);
                            d.setPermohonan(mhn);
                            d.setNoRujukan(SBD);
                            d.setInfoAudit(info);
                            dokumenDAO.saveOrUpdate(d);
                        }
                    }
                }
            }

            if (!SBdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SBD");

                Permohonan mohonSurat2 = permohonanService.findById(SBD);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
                d.setPermohonan(mohonSurat2);
                d.setTajuk("Surat Kebenaran Daftar");
                d.setNoRujukan(SBD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }
           
        LOG.info("Update Existing Record - updateTambahUrusan");
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
        permohonanPembetulanUrusan.setInfoAudit(info);
        permohonanPembetulanUrusan.setPermohonan(permohonan);
        permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
        if (StringUtils.isNotBlank(tempohTahun)) {
            permohonanPembetulanUrusan.setTempohTahun(Integer.parseInt(tempohTahun));
        }
        if (StringUtils.isNotBlank(tempohBulan)) {
            permohonanPembetulanUrusan.setTempohBulan(Integer.parseInt(tempohBulan));
        }
        if (StringUtils.isNotBlank(tempohHari)) {
            permohonanPembetulanUrusan.setTempohHari(Integer.parseInt(tempohHari));
        }

        if (StringUtils.isNotBlank(trhKuasa)) {
            permohonanPembetulanUrusan.setTarikhMula(dateFormat.parse(trhKuasa));
        }
        if (StringUtils.isNotBlank(trhTamat)) {
            permohonanPembetulanUrusan.setTarikhTamat(dateFormat.parse(trhTamat));
        }
        if (StringUtils.isNotBlank(kodUrusan)) {
            KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
            permohonanPembetulanUrusan.setKodUrusan(ku);
        }
        if (StringUtils.isNotBlank(tarikhDaftar)) {
            if (StringUtils.isNotBlank(jam)) {
//                trhconvert = tarikhDaftarUrusan;
                trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                LOG.info(trhconvert);
                trhDaftar = dateTimeFormat.parse(trhconvert);
            } else {
                trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                LOG.info(trhconvert);
                trhDaftar = dateTimeFormat.parse(trhconvert);
            }
            permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
        }
        if (StringUtils.isNotBlank(noJilid)) {
            permohonanPembetulanUrusan.setNoJilid(noJilid);
        }
        if (StringUtils.isNotBlank(noFolio)) {
            permohonanPembetulanUrusan.setNoFolio(noFolio);
        }
        if (StringUtils.isNotBlank(luasTerlibat)) {
            BigDecimal b = new BigDecimal(luasTerlibat);
            permohonanPembetulanUrusan.setLuasTerlibat(b);
        }
        if (StringUtils.isNotBlank(strKodUOM)) {
            KodUOM k = kodUOMDAO.findById(strKodUOM);
            permohonanPembetulanUrusan.setKodUnitLuas(k);
        }
        if (StringUtils.isNotBlank(cukaiBaru)) {
            BigDecimal b1 = new BigDecimal(cukaiBaru);
            permohonanPembetulanUrusan.setCukaiBaru(b1);
        }
        if (StringUtils.isNotBlank(noFail)) {
            permohonanPembetulanUrusan.setNoFail(noFail);
        }
        if (StringUtils.isNotBlank(noRujukan)) {
            permohonanPembetulanUrusan.setNoRujukan(noRujukan);
        }
        if (StringUtils.isNotBlank(tarikhRujukan)) {
            trhDaftar = dateFormat.parse(tarikhRujukan);
            permohonanPembetulanUrusan.setTarikhRujukan(trhDaftar);
        }
        if (StringUtils.isNotBlank(cukaiBaru)) {
            BigDecimal b = new BigDecimal(cukaiBaru);
            permohonanPembetulanUrusan.setCukaiBaru(b);
        }
        if (StringUtils.isNotBlank(kawasan)) {
            permohonanPembetulanUrusan.setItem(kawasan);
        }

        permohonanPembetulanUrusan.setIdPerserahanLama(idPerserahan);
        pService.updateTambahUrusan(permohonanPembetulanUrusan);

        /*UPDATE MOHON RUJ LUAR*/
        LOG.info("----- idperserahan :" + idPerserahan);
        PermohonanRujukanLuar updateMohonRujLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPerserahan);
        if (updateMohonRujLuar != null) {
            LOG.info(" /* UPDATE MOHON RUJ LUAR */ ");
            updateMohonRujLuar.setInfoAudit(info);
            updateMohonRujLuar.setHakmilik(hakmilikPermohonan.getHakmilik());
            if (StringUtils.isNotBlank(noFail)) {
                updateMohonRujLuar.setNoFail(noFail);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                trhDaftar = dateFormat.parse(tarikhRujukan);
                updateMohonRujLuar.setTarikhRujukan(trhDaftar);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(kawasan)) {
                updateMohonRujLuar.setItem(kawasan);
            }
            if (StringUtils.isNotBlank(trhKuasa)) {
                updateMohonRujLuar.setTarikhKuatkuasa(dateFormat.parse(trhKuasa));
            }
            if (StringUtils.isNotBlank(trhTamat)) {
                updateMohonRujLuar.setTarikhTamat(dateFormat.parse(trhTamat));
            }
            if (StringUtils.isNotBlank(tempohTahun)) {
                Integer it = new Integer(tempohTahun);
                updateMohonRujLuar.setTempohTahun(it);
            }
            if (StringUtils.isNotBlank(tempohBulan)) {
                Integer it = new Integer(tempohBulan);
                updateMohonRujLuar.setTempohBulan(it);
            }
            if (StringUtils.isNotBlank(tempohHari)) {
                Integer it = new Integer(tempohHari);
                updateMohonRujLuar.setTempohHari(it);
            }

            service.update(updateMohonRujLuar);
        }

        /*UPDATE MOHON_HAKMILIK*/
        HakmilikPermohonan updateMohonHakmilik = pService.findByIdHakmilikIdPermohonan(idPerserahan, idHakmilik);
        if (updateMohonHakmilik != null) {
            LOG.info(" /* UPDATE MOHON_HAKMILIK */ ");
            updateMohonHakmilik.setInfoAudit(info);
            if (StringUtils.isNotBlank(luasTerlibat)) { // update luas terlibat in table mohon_hakmilik
                BigDecimal lt = new BigDecimal(luasTerlibat);
                updateMohonHakmilik.setLuasTerlibat(lt);
            }
            if (StringUtils.isNotBlank(luasTerlibat)) { // update luas terlibat in table mohon_hakmilik
                BigDecimal lt = new BigDecimal(luasTerlibat);
                updateMohonHakmilik.setLuasTerlibat(lt);
            }
            if (StringUtils.isNotBlank(strKodUOM)) {
                KodUOM uom = kodUOMDAO.findById(strKodUOM);
                updateMohonHakmilik.setKodUnitLuas(uom);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b1 = new BigDecimal(cukaiBaru);
                updateMohonHakmilik.setCukaiBaru(b1);
            }
            pService.updateMohonHakmilik(updateMohonHakmilik);
        }
        
        /*UPDATE MOHON HAKMILIK BETUL FOR PSPL*/
        LOG.info("----- idperserahan :" + idPerserahan);
        permohonanLama = permohonanService.findById(idPerserahan);
        Hakmilik hm = pService.findHakmilik(idHakmilik);
        LOG.info(" /* UPDATE MOHON_HAKMILIK_BETUL PSPL */ ");
            if (permohonanLama.getKodUrusan().getKod().equals("PSPL")){
                PermohonanPembetulanHakmilik mhb = pService.findUrusanPSPL(idPerserahan, idHakmilik);
                if (mhb != null) {
                    LOG.info("betur update tempoh PSPL");
                    mhb.setTempohPenganganSemasa(permohonanPembetulanUrusan.getTempohTahun());
                    mhb.setTarikhLuputSemasa(permohonanPembetulanUrusan.getTarikhTamat());
                    mhb.setTarikhLuput(hm.getTarikhLuput());
                    mhb.setTarikhDaftar(permohonanPembetulanUrusan.getTarikhMula());
                    mhb.setInfoAudit(info);
                    pService.simpanBetul(mhb);
                } else {
                    PermohonanPembetulanHakmilik mhbNew = new PermohonanPembetulanHakmilik();
                    LOG.info("NEW mohon hakmilik betul PSPL");
                    mhbNew.setPermohonan(permohonanLama);
                    mhbNew.setHakmilik(updateMohonHakmilik);
                    mhbNew.setIdHakmilik(idHakmilik);
                    mhbNew.setCawangan(peng.getKodCawangan());
                    mhbNew.setTarikhDaftar(permohonanPembetulanUrusan.getTarikhMula());
                    mhbNew.setTempohPengangan(hm.getTempohPegangan());
                    mhbNew.setTarikhLuput(hm.getTarikhLuput());
                    mhbNew.setTempohPenganganSemasa(permohonanPembetulanUrusan.getTempohTahun());
                    mhbNew.setTarikhLuputSemasa(permohonanPembetulanUrusan.getTarikhTamat());
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    mhbNew.setInfoAudit(ia);
                    pService.simpanBetul(mhbNew);
                    
                }
        }
                        
        /* UPDATE MOHON */
        Permohonan updateMohon = permohonanService.findById(idPerserahan);
        if (updateMohon != null) {
            LOG.info(" /* UPDATE MOHON */ ");
            updateMohon.setInfoAudit(info);
            if (StringUtils.isNotBlank(kodUrusan)) {
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                updateMohon.setKodUrusan(ku);
            }
            if (StringUtils.isNotBlank(tarikhDaftar)) {
                if (StringUtils.isNotBlank(jam)) {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                } else {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                }
                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
            }
            permohonanService.update(updateMohon);
        }

        msg = "Kemaskini Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanSuratKemaskini() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        tarikhDaftarUrusan = getContext().getRequest().getParameter("tarikhDaftar");
        kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        noFolio = getContext().getRequest().getParameter("noFolio");
        noJilid = getContext().getRequest().getParameter("noJilid");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        luasTerlibat = getContext().getRequest().getParameter("luasTerlibat");
        strKodUOM = getContext().getRequest().getParameter("strKodUOM");
        noFail = getContext().getRequest().getParameter("noFail");
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        cukaiBaru = getContext().getRequest().getParameter("cukaiBaru");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kawasan = getContext().getRequest().getParameter("kawasan");
        tempohTahun = getContext().getRequest().getParameter("tempohTahun");
        tempohBulan = getContext().getRequest().getParameter("tempohBulan");
        tempohHari = getContext().getRequest().getParameter("tempohHari");
        trhKuasa = getContext().getRequest().getParameter("trhKuasa");
        trhTamat = getContext().getRequest().getParameter("trhTamat");

        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);

        Permohonan mohonSurat = permohonanService.findById(idPerserahan);
        fd = mohonSurat.getFolderDokumen();
        if (fd.getSenaraiKandungan() != null) {
            for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                    continue;
                }

            }
        }

        if (dokumenSave.getKodDokumen().getKod().equals("SWD")) {
            SWdahAda = true;
            if (SWdahAda) {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                Permohonan mhn = permohonanService.findById(SWD);
                dokumenSave.setHakmilik(idHakmilik);
                if (mhn != null) {
                    dokumenSave.setPermohonan(mhn);
                } else {
                    dokumenSave.setPermohonan(mohonSurat);
                }
                dokumenSave.setNoRujukan(SWD);
                dokumenSave.setInfoAudit(info);
                dokumenDAO.saveOrUpdate(dokumenSave);
            }
            if (!SWdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SWD");

                Permohonan mohonSurat2 = permohonanService.findById(SWD);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
//                d.setPermohonan(mohonSurat);
                d.setHakmilik(idHakmilik);
                if (mohonSurat2 != null) {
                    d.setPermohonan(mohonSurat2);
                } else {
                    d.setPermohonan(mohonSurat);
                }
                d.setHakmilik(idHakmilik);
                d.setTajuk("Surat Kuasa Wakil Daftar");
                d.setNoRujukan(SWD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        } else if (dokumenSave.getKodDokumen().getKod().equals("SAB")) {
            if (SAdahAda) {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                Permohonan mhn = permohonanService.findById(SAB);
                dokumenSave.setHakmilik(idHakmilik);
                if (mhn != null) {
                    dokumenSave.setPermohonan(mhn);
                } else {
                    dokumenSave.setPermohonan(mohonSurat);
                }
                dokumenSave.setNoRujukan(SAB);
                dokumenSave.setInfoAudit(info);
                dokumenDAO.saveOrUpdate(dokumenSave);
            } else if (!SAdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SAB");

                Permohonan mohonSurat2 = permohonanService.findById(SAB);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
//                d.setPermohonan(mohonSurat);
                d.setHakmilik(idHakmilik);
                if (mohonSurat2 != null) {
                    d.setPermohonan(mohonSurat2);
                } else {
                    d.setPermohonan(mohonSurat);
                }
                d.setHakmilik(idHakmilik);
                d.setTajuk("Surat Kuasa Wakil Daftar");
                d.setNoRujukan(SWD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        } else if (dokumenSave.getKodDokumen().getKod().equals("SBD")) {
            if (SBdahAda) {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                Permohonan mhn = permohonanService.findById(SBD);
                dokumenSave.setHakmilik(idHakmilik);
                if (mhn != null) {
                    dokumenSave.setPermohonan(mhn);
                } else {
                    dokumenSave.setPermohonan(mohonSurat);
                }
                dokumenSave.setNoRujukan(SBD);
                dokumenSave.setInfoAudit(info);
                dokumenDAO.saveOrUpdate(dokumenSave);
            } else if (!SBdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SBD");

                Permohonan mohonSurat2 = permohonanService.findById(SBD);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
//                d.setPermohonan(mohonSurat);
                d.setHakmilik(idHakmilik);
                if (mohonSurat2 != null) {
                    d.setPermohonan(mohonSurat2);
                } else {
                    d.setPermohonan(mohonSurat);
                }
                d.setHakmilik(idHakmilik);
                d.setTajuk("Surat Kuasa Wakil Daftar");
                d.setNoRujukan(SWD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }

        /*UPDATE MOHON RUJ LUAR*/
        LOG.info("----- idperserahan :" + idPerserahan);
        PermohonanRujukanLuar updateMohonRujLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPerserahan);
        if (updateMohonRujLuar != null) {
            LOG.info(" /* UPDATE MOHON RUJ LUAR */ ");
            updateMohonRujLuar.setInfoAudit(info);
            updateMohonRujLuar.setHakmilik(hakmilikPermohonan.getHakmilik());
            if (StringUtils.isNotBlank(noFail)) {
                updateMohonRujLuar.setNoFail(noFail);
            }
            if (StringUtils.isNotBlank(SAB)) {
                KodDokumen kd = kodDokumenDAO.findById("SAB");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SAB);
            }
            if (StringUtils.isNotBlank(SBD)) {
                KodDokumen kd = kodDokumenDAO.findById("SBD");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SBD);
            }
            if (StringUtils.isNotBlank(SWD)) {
                KodDokumen kd = kodDokumenDAO.findById("SWD");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SWD);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                trhDaftar = dateFormat.parse(tarikhRujukan);
                updateMohonRujLuar.setTarikhRujukan(trhDaftar);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            service.update(updateMohonRujLuar);
        }

        /*UPDATE MOHON_HAKMILIK*/
        /* UPDATE MOHON */
        Permohonan updateMohon = permohonanService.findById(idPerserahan);
        if (updateMohon != null) {
            LOG.info(" /* UPDATE MOHON */ ");
            updateMohon.setInfoAudit(info);
            if (StringUtils.isNotBlank(kodUrusan)) {
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                updateMohon.setKodUrusan(ku);
            }

            permohonanService.update(updateMohon);
        }

        msg = "Kemaskini Data Pembetulan Telah Berjaya";

        return updateUrusanTerperinci();
//        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution updateTambahUrusanTerperinci() throws ParseException {
        String error = "";
        String msg = "";
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        tarikhDaftarUrusan = getContext().getRequest().getParameter("tarikhDaftar");
        kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        noFolio = getContext().getRequest().getParameter("noFolio");
        noJilid = getContext().getRequest().getParameter("noJilid");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        saat = getContext().getRequest().getParameter("saat");
        ampm = getContext().getRequest().getParameter("ampm");
        luasTerlibat = getContext().getRequest().getParameter("luasTerlibat");
        strKodUOM = getContext().getRequest().getParameter("strKodUOM");
        noFail = getContext().getRequest().getParameter("noFail");
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        cukaiBaru = getContext().getRequest().getParameter("cukaiBaru");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        kawasan = getContext().getRequest().getParameter("kawasan");
        tempohTahun = getContext().getRequest().getParameter("tempohTahun");
        tempohBulan = getContext().getRequest().getParameter("tempohBulan");
        tempohHari = getContext().getRequest().getParameter("tempohHari");
        trhKuasa = getContext().getRequest().getParameter("trhKuasa");
        trhTamat = getContext().getRequest().getParameter("trhTamat");

        tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
        tarikhKuatkuasa = getContext().getRequest().getParameter("tarikhKuatkuasa");
        tarikhLulus = getContext().getRequest().getParameter("tarikhLulus");

        hakmilikPermohonan = pService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
        if (permohonanPembetulanUrusan == null) {
            permohonanPembetulanUrusan = pService.findByidPermohonan(permohonan.getIdPermohonan());
        }

        //tambah SW ni ha
        if (StringUtils.isNotBlank(SWD)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen() != null) {
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SWD")) {
                            SWdahAda = true;
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            Permohonan mhn = permohonanService.findById(SWD);
                            d.setHakmilik(idHakmilik);
                            if (mhn != null) {
                                d.setPermohonan(mhn);
                            } else {
                                d.setPermohonan(mohonSurat);
                            }
                            d.setNoRujukan(SWD);
                            d.setInfoAudit(info);
                            dokumenDAO.saveOrUpdate(d);
                        }
                    }
                }
            }

            if (!SWdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SWD");

                Permohonan mohonSurat2 = permohonanService.findById(SWD);

                Dokumen d = new Dokumen();

                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
//                d.setPermohonan(mohonSurat);
                d.setHakmilik(idHakmilik);
                if (mohonSurat2 != null) {
                    d.setPermohonan(mohonSurat2);
                } else {
                    d.setPermohonan(mohonSurat);
                }
                d.setHakmilik(idHakmilik);
                d.setTajuk("Surat Kuasa Wakil Daftar");
                d.setNoRujukan(SWD);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }

        if (StringUtils.isNotBlank(SAB)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();
            if (fd.getSenaraiKandungan() != null) {
                for (KandunganFolder kf : fd.getSenaraiKandungan()) {
                    if (kf == null || kf.getDokumen() == null || kf.getDokumen().getKodDokumen() == null) {
                        continue;
                    }
                    if (kf.getDokumen() != null) {
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                        String kod = kf.getDokumen().getKodDokumen().getKod();
                        if (kod.equals("SAB")) {
                            SAdahAda = true;
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            Permohonan mhn = permohonanService.findById(SAB);
//                            d.setPermohonan(mohonSurat);
                            d.setHakmilik(idHakmilik);
                            if (mhn != null) {
                                d.setPermohonan(mhn);
                            } else {
                                d.setPermohonan(mohonSurat);
                            }
                            d.setHakmilik(idHakmilik);
//                            d.setPermohonan(mhn);
                            d.setNoRujukan(SAB);
                            d.setInfoAudit(info);
                            dokumenDAO.saveOrUpdate(d);
                        }
                    }
                }
            }

            if (!SAdahAda) {
                InfoAudit ia = new InfoAudit();
                KodDokumen kd = kodDokumenDAO.findById("SAB");

                Permohonan mohonSurat2 = permohonanService.findById(SAB);

                Dokumen d = new Dokumen();
                d.setHakmilik(idHakmilik);
                if (mohonSurat2 != null) {
                    d.setPermohonan(mohonSurat2);
                } else {
                    d.setPermohonan(mohonSurat);
                }
//                d.setPermohonan(mohonSurat);
                d.setHakmilik(idHakmilik);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                d.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
                d.setTajuk("Surat Amanah Baru");
                d.setNoRujukan(SAB);
                d.setKlasifikasi(kodKlasifikasiDAO.findById(1));
                d.setNoVersi("1.0");
                d.setInfoAudit(ia);
                dokumenDAO.saveOrUpdate(d);

                KandunganFolder kf = new KandunganFolder();

                kf.setInfoAudit(ia);
                kf.setDokumen(d);
                permohonanLama = permohonanService.findById(idPerserahan);
                kf.setFolder(permohonanLama.getFolderDokumen());
                kandunganFolderService.saveOrUpdate(kf);
            }
        }

        if (StringUtils.isNotBlank(SBD)) {
            Permohonan mohonSurat = permohonanService.findById(idPerserahan);
            fd = mohonSurat.getFolderDokumen();

            InfoAudit ia = new InfoAudit();
            KodDokumen kd = kodDokumenDAO.findById("SBD");

            Permohonan mohonSurat2 = permohonanService.findById(SBD);

            Dokumen dNew = new Dokumen();

            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            dNew.setKodDokumen(kd);
//                d.setPermohonan(mohonSurat2);
            dNew.setHakmilik(idHakmilik);
            if (mohonSurat2 != null) {
                dNew.setPermohonan(mohonSurat2);
            } else {
                dNew.setPermohonan(mohonSurat);
            }
            dNew.setTajuk("Surat Kebenaran Daftar");
            dNew.setNoRujukan(SBD);
            dNew.setKlasifikasi(kodKlasifikasiDAO.findById(1));
            dNew.setNoVersi("1.0");
            dNew.setInfoAudit(ia);
            dokumenDAO.saveOrUpdate(dNew);

            KandunganFolder kfNew = new KandunganFolder();

            kfNew.setInfoAudit(ia);
            kfNew.setDokumen(dNew);
            permohonanLama = permohonanService.findById(idPerserahan);
            kfNew.setFolder(permohonanLama.getFolderDokumen());
            kandunganFolderService.saveOrUpdate(kfNew);

        }

//           
        //
        LOG.info("Update Existing Record - updateTambahUrusanTerperinci");
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        if (permohonanPembetulanUrusan != null) {
            permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
            permohonanPembetulanUrusan.setInfoAudit(info);
            permohonanPembetulanUrusan.setPermohonan(permohonan);
            permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
            if (tempohTahun != null) {
                permohonanPembetulanUrusan.setTempohTahun(Integer.parseInt(tempohTahun));
            }
            if (tempohBulan != null) {
                permohonanPembetulanUrusan.setTempohBulan(Integer.parseInt(tempohBulan));
            }
            if (tempohHari != null) {
                permohonanPembetulanUrusan.setTempohHari(Integer.parseInt(tempohHari));
            }

            if (StringUtils.isNotBlank(trhKuasa)) {
                permohonanPembetulanUrusan.setTarikhMula(dateFormat.parse(trhKuasa));
            }
            if (StringUtils.isNotBlank(trhTamat)) {
                permohonanPembetulanUrusan.setTarikhTamat(dateFormat.parse(trhTamat));
            }
            if (StringUtils.isNotBlank(kodUrusan)) {
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                permohonanPembetulanUrusan.setKodUrusan(ku);
            }
            if (StringUtils.isNotBlank(tarikhDaftar)) {
                if (StringUtils.isNotBlank(jam)) {
//                trhconvert = tarikhDaftarUrusan;
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                } else {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                }
                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
            }
            if (StringUtils.isNotBlank(noJilid)) {
                permohonanPembetulanUrusan.setNoJilid(noJilid);
            }
            if (StringUtils.isNotBlank(noFolio)) {
                permohonanPembetulanUrusan.setNoFolio(noFolio);
            }
            if (StringUtils.isNotBlank(luasTerlibat)) {
                BigDecimal b = new BigDecimal(luasTerlibat);
                permohonanPembetulanUrusan.setLuasTerlibat(b);
            }
            if (StringUtils.isNotBlank(strKodUOM)) {
                KodUOM k = kodUOMDAO.findById(strKodUOM);
                permohonanPembetulanUrusan.setKodUnitLuas(k);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b1 = new BigDecimal(cukaiBaru);
                permohonanPembetulanUrusan.setCukaiBaru(b1);
            }
            if (StringUtils.isNotBlank(noFail)) {
                permohonanPembetulanUrusan.setNoFail(noFail);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                permohonanPembetulanUrusan.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                trhDaftar = dateFormat.parse(tarikhRujukan);
                permohonanPembetulanUrusan.setTarikhRujukan(trhDaftar);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b = new BigDecimal(cukaiBaru);
                permohonanPembetulanUrusan.setCukaiBaru(b);
            }
            if (StringUtils.isNotBlank(kawasan)) {
                permohonanPembetulanUrusan.setItem(kawasan);
            }
            
            permohonanLama = permohonanService.findById(idPerserahan);
            if (!permohonanLama.getKodUrusan().getKod().equals("PSPL")){
                permohonanPembetulanUrusan.setIdPerserahanLama(idPerserahan);
            }
            pService.updateTambahUrusan(permohonanPembetulanUrusan);
        } else {
            permohonanPembetulanUrusan = new PermohonanPembetulanUrusan();
            permohonanPembetulanUrusan.setCawangan(peng.getKodCawangan());
            permohonanPembetulanUrusan.setInfoAudit(info);
            permohonanPembetulanUrusan.setPermohonan(permohonan);
            permohonanPembetulanUrusan.setHakmilik(hakmilikPermohonan);
            if (tempohTahun != null) {
            }
            permohonanPembetulanUrusan.setTempohTahun(Integer.parseInt(tempohTahun));
            if (tempohBulan != null) {
                permohonanPembetulanUrusan.setTempohBulan(Integer.parseInt(tempohBulan));
            }
            if (tempohHari != null) {
                permohonanPembetulanUrusan.setTempohHari(Integer.parseInt(tempohHari));
            }

            if (StringUtils.isNotBlank(trhKuasa)) {
                permohonanPembetulanUrusan.setTarikhMula(dateFormat.parse(trhKuasa));
            }
            if (StringUtils.isNotBlank(trhTamat)) {
                permohonanPembetulanUrusan.setTarikhTamat(dateFormat.parse(trhTamat));
            }
            if (StringUtils.isNotBlank(kodUrusan)) {
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                permohonanPembetulanUrusan.setKodUrusan(ku);
            }
            if (StringUtils.isNotBlank(tarikhDaftar)) {
                if (StringUtils.isNotBlank(jam)) {
//                trhconvert = tarikhDaftarUrusan;
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                } else {
                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
                    LOG.info(trhconvert);
                    trhDaftar = dateTimeFormat.parse(trhconvert);
                }
                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
            }
            if (StringUtils.isNotBlank(noJilid)) {
                permohonanPembetulanUrusan.setNoJilid(noJilid);
            }
            if (StringUtils.isNotBlank(noFolio)) {
                permohonanPembetulanUrusan.setNoFolio(noFolio);
            }
            if (StringUtils.isNotBlank(luasTerlibat)) {
                BigDecimal b = new BigDecimal(luasTerlibat);
                permohonanPembetulanUrusan.setLuasTerlibat(b);
            }
            if (StringUtils.isNotBlank(strKodUOM)) {
                KodUOM k = kodUOMDAO.findById(strKodUOM);
                permohonanPembetulanUrusan.setKodUnitLuas(k);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b1 = new BigDecimal(cukaiBaru);
                permohonanPembetulanUrusan.setCukaiBaru(b1);
            }
            if (StringUtils.isNotBlank(noFail)) {
                permohonanPembetulanUrusan.setNoFail(noFail);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                permohonanPembetulanUrusan.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                trhDaftar = dateFormat.parse(tarikhRujukan);
                permohonanPembetulanUrusan.setTarikhRujukan(trhDaftar);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b = new BigDecimal(cukaiBaru);
                permohonanPembetulanUrusan.setCukaiBaru(b);
            }
            if (StringUtils.isNotBlank(kawasan)) {
                permohonanPembetulanUrusan.setItem(kawasan);
            }
            
            permohonanLama = permohonanService.findById(idPerserahan);
            if (!permohonanLama.getKodUrusan().getKod().equals("PSPL")){
                permohonanPembetulanUrusan.setIdPerserahanLama(idPerserahan);
            }
            pService.updateTambahUrusan(permohonanPembetulanUrusan);
        }
        /*UPDATE MOHON RUJ LUAR*/
        LOG.info("----- idperserahan :" + idPerserahan);
        PermohonanRujukanLuar updateMohonRujLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPerserahan);
        if (updateMohonRujLuar != null) {
            LOG.info(" /* UPDATE MOHON RUJ LUAR */ ");
            updateMohonRujLuar.setInfoAudit(info);
            updateMohonRujLuar.setHakmilik(hakmilikPermohonan.getHakmilik());
            if (StringUtils.isNotBlank(noFail)) {
                updateMohonRujLuar.setNoFail(noFail);
            }
            if (StringUtils.isNotBlank(SAB)) {
                KodDokumen kd = kodDokumenDAO.findById("SAB");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SAB);
            }
            if (StringUtils.isNotBlank(SBD)) {
                KodDokumen kd = kodDokumenDAO.findById("SBD");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SBD);
            }
            if (StringUtils.isNotBlank(SWD)) {
                KodDokumen kd = kodDokumenDAO.findById("SWD");
                updateMohonRujLuar.setKodDokumenRujukan(kd);
                updateMohonRujLuar.setCatatan(SWD);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                trhDaftar = dateFormat.parse(tarikhRujukan);
                updateMohonRujLuar.setTarikhRujukan(trhDaftar);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                updateMohonRujLuar.setNoRujukan(noRujukan);
            }
            if (StringUtils.isNotBlank(kawasan)) {
                updateMohonRujLuar.setItem(kawasan);
            }
            if (StringUtils.isNotBlank(trhKuasa)) {
                updateMohonRujLuar.setTarikhKuatkuasa(dateFormat.parse(trhKuasa));
            }
            if (StringUtils.isNotBlank(trhTamat)) {
                updateMohonRujLuar.setTarikhTamat(dateFormat.parse(trhTamat));
            }
            if (StringUtils.isNotBlank(tempohTahun)) {
                Integer it = new Integer(tempohTahun);
                updateMohonRujLuar.setTempohTahun(it);
            }
            if (StringUtils.isNotBlank(tempohBulan)) {
                Integer it = new Integer(tempohBulan);
                updateMohonRujLuar.setTempohBulan(it);
            }
            if (StringUtils.isNotBlank(tempohHari)) {
                Integer it = new Integer(tempohHari);
                updateMohonRujLuar.setTempohHari(it);
            }
            if (StringUtils.isNotBlank(tarikhSidang)) {
                updateMohonRujLuar.setTarikhSidang(dateFormat.parse(tarikhSidang));
            }
            if (StringUtils.isNotBlank(tarikhKuatkuasa)) {
                updateMohonRujLuar.setTarikhKuatkuasa(dateFormat.parse(tarikhKuatkuasa));
            }
            if (StringUtils.isNotBlank(tarikhLulus)) {
                updateMohonRujLuar.setTarikhLulus(dateFormat.parse(tarikhLulus));
            }

            service.update(updateMohonRujLuar);
        }

        /*UPDATE MOHON_HAKMILIK*/
        HakmilikPermohonan updateMohonHakmilik = pService.findByIdHakmilikIdPermohonan(idPerserahan, idHakmilik);
        if (updateMohonHakmilik != null) {
            LOG.info(" /* UPDATE MOHON_HAKMILIK */ ");
            updateMohonHakmilik.setInfoAudit(info);
            if (StringUtils.isNotBlank(luasTerlibat)) { // update luas terlibat in table mohon_hakmilik
                BigDecimal lt = new BigDecimal(luasTerlibat);
                updateMohonHakmilik.setLuasTerlibat(lt);
            }
            if (StringUtils.isNotBlank(luasTerlibat)) { // update luas terlibat in table mohon_hakmilik
                BigDecimal lt = new BigDecimal(luasTerlibat);
                updateMohonHakmilik.setLuasTerlibat(lt);
            }
            if (StringUtils.isNotBlank(strKodUOM)) {
                KodUOM uom = kodUOMDAO.findById(strKodUOM);
                updateMohonHakmilik.setKodUnitLuas(uom);
            }
            if (StringUtils.isNotBlank(cukaiBaru)) {
                BigDecimal b1 = new BigDecimal(cukaiBaru);
                updateMohonHakmilik.setCukaiBaru(b1);
            }
            pService.updateMohonHakmilik(updateMohonHakmilik);
        }

        /* UPDATE MOHON */
        Permohonan updateMohon = permohonanService.findById(idPerserahan);
        if (updateMohon != null) {
            LOG.info(" /* UPDATE MOHON updateTambahUrusanTerperinci */ ");
            updateMohon.setInfoAudit(info);
            if (StringUtils.isNotBlank(kodUrusan)) {
                KodUrusan ku = kodUrusanDAO.findById(kodUrusan);
                updateMohon.setKodUrusan(ku);
            }
//            if (StringUtils.isNotBlank(tarikhDaftar)) {
//                if (StringUtils.isNotBlank(jam)) {
//                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
//                    LOG.info(trhconvert);
//                    trhDaftar = dateTimeFormat.parse(trhconvert);
//                } else {
//                    trhconvert = tarikhDaftarUrusan + " " + jam + ":" + minit + ":" + saat + " " + ampm;
//                    LOG.info(trhconvert);
//                    trhDaftar = dateTimeFormat.parse(trhconvert);
//                }
//                permohonanPembetulanUrusan.setTarikhDaftar(trhDaftar);
//            }
            permohonanService.update(updateMohon);
        }

        msg = "Kemaskini Data Pembetulan Telah Berjaya";

        return new RedirectResolution(pembetulanActionBean.class, "senaraiUrusan").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution rekod_pembetulan() {

        List<PermohonanRujukanLuar> listruj = pService.searchMohonRujByIDPermohonan(permohonan.getIdPermohonan());
        if (listruj != null) {
            for (PermohonanRujukanLuar p : listruj) {
                permohonanRujukanLuar = p;
            }
        }

        return new JSP("daftar/pembetulan/nota_daftar_betul.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonPihak() {

        String[] param = getContext().getRequest().getParameterValues("idHakmilikPihakBerkepentingan");
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
        String error = "";
        String msg = "";

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        for (String idHakmilikPihakBerkepentingan : param) {

            InfoAudit ia = new InfoAudit();

            permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
            HakmilikPihakBerkepentingan hpk = hpbDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
            Pemohon pmohon = pService.findByidPemohon(String.valueOf(hpk.getPihak().getIdPihak()), String.valueOf(permohonan.getIdPermohonan()), permohonanPembetulanUrusan.getIdPerserahanLama());
            Permohonan mohonLama = permohonanService.findById(permohonanPembetulanUrusan.getIdPerserahanLama());

            if (pmohon == null) {
                Pemohon pe = new Pemohon();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setPermohonan(mohonLama);
                pe.setPihak(hpk.getPihak());
                pe.setKaitan(permohonan.getIdPermohonan());
                pe.setCawangan(peng.getKodCawangan());

                /*pe.setNama(hpk.getPihak().getNama());
                 pe.setNoPengenalan(hpk.getPihak().getNoPengenalan());*/
                pe.setNama(hpk.getNama());
                pe.setJenisPengenalan(hpk.getJenisPengenalan());
                pe.setNoPengenalan(hpk.getNoPengenalan());

                Alamat alamat = new Alamat();
                /*alamat.setAlamat1(hpk.getPihak().getAlamat1());
                 alamat.setAlamat2(hpk.getPihak().getAlamat2());
                 alamat.setAlamat3(hpk.getPihak().getAlamat3());
                 alamat.setAlamat4(hpk.getPihak().getAlamat4());
                 alamat.setPoskod(hpk.getPihak().getPoskod());
                 alamat.setNegeri(hpk.getPihak().getNegeri());*/
                alamat.setAlamat1(hpk.getAlamat1());
                alamat.setAlamat2(hpk.getAlamat2());
                alamat.setAlamat3(hpk.getAlamat3());
                alamat.setAlamat4(hpk.getAlamat4());
                alamat.setPoskod(hpk.getPoskod());
                alamat.setNegeri(hpk.getNegeri());

                AlamatSurat alamatSurat = new AlamatSurat();
                alamatSurat.setAlamatSurat1(hpk.getPihak().getSuratAlamat1());
                alamatSurat.setAlamatSurat2(hpk.getPihak().getSuratAlamat2());
                alamatSurat.setAlamatSurat3(hpk.getPihak().getSuratAlamat3());
                alamatSurat.setAlamatSurat4(hpk.getPihak().getSuratAlamat4());
                alamatSurat.setPoskodSurat(hpk.getPihak().getSuratPoskod());
                alamatSurat.setNegeriSurat(hpk.getPihak().getSuratNegeri());

                pe.setAlamat(alamat);
                pe.setAlamatSurat(alamatSurat);
                pe.setHakmilik(hpk.getHakmilik());
                pe.setJenis(hpk.getJenis());
                pe.setJumlahPembilang(hpk.getJumlahPembilang());
                pe.setJumlahPenyebut(hpk.getJumlahPenyebut());
                pe.setSyerPembilang(hpk.getSyerPembilang());
                pe.setSyerPenyebut(hpk.getSyerPenyebut());
                pe.setWargaNegara(hpk.getPihak().getWargaNegara());

                senaraiPemohon.add(pe);
            } else {
                error = "Pihak Berkepentingan ini telah diPohon";
            }
        }
        if (senaraiPemohon.size() > 0) {
            pemohonService.saveOrUpdateMultiple(senaraiPemohon);

        }

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution deletePihak() {

        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        LOG.info(idPermohonanPihak);
        if (idPermohonanPihak != null) {
            PermohonanPihak phk = permohonanPihakDAO.findById(Long.parseLong(idPermohonanPihak));

            if (phk != null) {
                pService.deleteMohonPihak(phk);

            }
        }

        rehydrate();

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution deletePemohon() {

        idPemohon = getContext().getRequest().getParameter("idPemohon");
        LOG.info(idPemohon);
        if (idPemohon != null) {
            Pemohon pmohon = pemohonService.findById(idPemohon);
            if (pmohon != null) {
                pemohonService.delete(pmohon);
            }
        }
        rehydrate();

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution deleteWaris() {

        String idPermohonanPihakHbgn = getContext().getRequest().getParameter("idPermohonanHbgn");
        LOG.info(idPermohonanPihakHbgn);
        if (idPermohonanPihakHbgn != null) {
            permohonanPihakHubungan = permohonanPihakHubunganDAO.findById(Long.parseLong(idPermohonanPihakHbgn));
            if (permohonanPihakHubungan != null) {
                permohonanPihakHubunganService.delete(permohonanPihakHubungan);
            }
        }
        rehydrate();

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution kemaskiniWarisPopap() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
//        umurWaris = getContext().getRequest().getParameter("umurWaris");

        if (idPermohonanPihak != null) {
            permohonanPihakHubunganList = permohonanPihakHubunganService.findMohonPihakByIdMohonAndIdHakmilik(idPermohonanPihak);
            syerPembilang = new String[permohonanPihakHubunganList.size()];
            syerPenyebut = new String[permohonanPihakHubunganList.size()];
            umurWaris = new String[permohonanPihakHubunganList.size()];
        }
        tambahWaris = getContext().getRequest().getParameter("tambahWaris");
        if (StringUtils.isNotBlank(jenisPB)) {
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }
        int counter = 0;
        int counter2 = 0;
        for (int i = 0; i < permohonanPihakHubunganList.size(); i++) {
            PermohonanPihakHubungan pph = permohonanPihakHubunganList.get(i);

            if (pph.getSyerPembilang() != 0 || pph.getSyerPenyebut() != 0) {
                int syerPem = pph.getSyerPembilang();
                int syerPen = pph.getSyerPenyebut();

                syerPembilang[counter] = String.valueOf(syerPem);
                syerPenyebut[counter] = String.valueOf(syerPen);
                counter = counter + 1;
            } else {

                syerPembilang[counter] = String.valueOf(0);
                syerPenyebut[counter] = String.valueOf(0);
                counter = counter + 1;
            }
            if (pph.getUmur() != null) {
                umurWaris[counter2] = String.valueOf(pph.getUmur());
                counter2 = counter2 + 1;
            } else {
                umurWaris[counter2] = String.valueOf(0);
                counter2 = counter2 + 1;
            }
        }

        kodNegeri = conf.getProperty("kodNegeri");
        cariFlag = true;
        tiadaDataFlag = true;
        senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
        return new JSP("daftar/pembetulan/pihak_berkepentingan_waris_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution pihakKepentinganPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        tambahWaris = getContext().getRequest().getParameter("tambahWaris");
        if (StringUtils.isNotBlank(jenisPB)) {
            getContext().getRequest().setAttribute("jenis", jenisPB);
        }
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
        }

        kodNegeri = conf.getProperty("kodNegeri");
        cariFlag = true;
        tiadaDataFlag = true;
        senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }
        kodNegeri = conf.getProperty("kodNegeri");
        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution cariPihak() {

        String tmp = getContext().getRequest().getParameter("tuanTanah");
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
//        if (StringUtils.isNotBlank(idHakmilik)) {
//            getContext().getRequest().setAttribute("hakmilik", idHakmilik);
//            getContext().getRequest().setAttribute("multiple", Boolean.TRUE);
//        }
//        if (StringUtils.isNotBlank(tmp)) {
//            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
//        }
//
//        String jenisPB = getContext().getRequest().getParameter("jenisPB");
//        if (StringUtils.isNotBlank(jenisPB)) {
//            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
//            if (permohonanPihak == null) {
//                permohonanPihak = new PermohonanPihak();
//            }
//            permohonanPihak.setJenis(kod);
//            getContext().getRequest().setAttribute("jenis", jenisPB);
//        } else {
//            String kodUrusan = permohonan.getKodUrusan().getKod();
//            if (kodUrusan.equals("TMAML") || kodUrusan.equals("TMAMD")) {
//                //if TMAML / TMAMD , jenis pihak must be pendaftar(PP)
//                KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById("PP");
//                if (permohonanPihak == null) {
//                    permohonanPihak = new PermohonanPihak();
//                }
//                permohonanPihak.setJenis(kod);
//            }
//        }
//
//        if (pihak != null) {
//
//            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
//                Pihak pihakSearch = new Pihak();
//                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
//                mohonPihakList = permohonanPihakService.getSenaraiMohonPihakByKodAndJenisPengenalan(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
//                boolean duplicateFlag = false;
//
////                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
////                    if (!(mohonPihakList.isEmpty())) {
////                        for (PermohonanPihak pp : mohonPihakList) {
////                            if (pp.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
////
////                                duplicateFlag = true;
////                                break;
////                            }
////                        }
////                    }
////                }
//                if (!duplicateFlag) {
//                    if (pihakSearch != null) {
//                        pihak = pihakSearch;
//                        if (pihak.getTempatLahir() != null) {
//                            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
//                                tempatLahirLain = pihak.getTempatLahir();
//                                pihak.setTempatLahir("LAIN-LAIN");
//                            }
//                        }
//                        cariFlag = true;
//                        tiadaDataFlag = false;
//                    } else {
//                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
//                        cariFlag = true;
//                        tiadaDataFlag = true;
//                    }
//                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
//                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
//                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
//                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
//
//                } else {
//                    pihak = new Pihak();
//                    addSimpleError("Maklumat Ini Telah Dipilih.");
//                }
//            } else if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
//                pihakByNameList = new ArrayList<Pihak>();
//                pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());
//
//                if (pihakByNameList.isEmpty()) {
//                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
//                    cariFlag = true;
//                    tiadaDataFlag = true;
//                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
//                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
//                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
//                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
//                }
//            } else if (pihak.getJenisPengenalan() == null) {
//                addSimpleError("Sila Masukkan Jenis Pengenalan.");
//            } else if (pihak.getNoPengenalan() == null && pihak.getNama() == null) {
//                addSimpleError("Sila Masukkan  Nombor Pengenalan atau Nama.");
//            }
//
//        } else {
//
//            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
//            return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
//        }
        cariFlag = true;
        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution cariPihakPemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");

        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        if (StringUtils.isNotBlank(jenisPB)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPB);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPB);
        } else {
            String kodUrusan = permohonan.getKodUrusan().getKod();
            if (kodUrusan.equals("TMAML") || kodUrusan.equals("TMAMD")) {
                //if TMAML / TMAMD , jenis pihak must be pendaftar(PP)
                KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById("PP");
                if (permohonanPihak == null) {
                    permohonanPihak = new PermohonanPihak();
                }
                permohonanPihak.setJenis(kod);
            }
        }

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                mohonPihakList = permohonanPihakService.getSenaraiMohonPihakByKodAndJenisPengenalan(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null && StringUtils.isBlank(idHakmilik)) {
                    if (!(mohonPihakList.isEmpty())) {
                        for (PermohonanPihak pp : mohonPihakList) {
                            if (pp.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {

                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        if (pihak.getTempatLahir() != null) {
                            if (!pihak.getTempatLahir().equalsIgnoreCase("JOHOR") && !pihak.getTempatLahir().equalsIgnoreCase("KEDAH") && !pihak.getTempatLahir().equalsIgnoreCase("KELANTAN") && !pihak.getTempatLahir().equalsIgnoreCase("MELAKA") && !pihak.getTempatLahir().equalsIgnoreCase("NEGERI SEMBILAN") && !pihak.getTempatLahir().equalsIgnoreCase("PAHANG") && !pihak.getTempatLahir().equalsIgnoreCase("PERAK") && !pihak.getTempatLahir().equalsIgnoreCase("PERLIS") && !pihak.getTempatLahir().equalsIgnoreCase("PULAU PINANG") && !pihak.getTempatLahir().equalsIgnoreCase("SABAH") && !pihak.getTempatLahir().equalsIgnoreCase("SARAWAK") && !pihak.getTempatLahir().equalsIgnoreCase("SELANGOR") && !pihak.getTempatLahir().equalsIgnoreCase("TERENGGANU") && !pihak.getTempatLahir().equalsIgnoreCase("WP KUALA LUMPUR") && !pihak.getTempatLahir().equalsIgnoreCase("WP LABUAN") && !pihak.getTempatLahir().equalsIgnoreCase("WP PUTRAJAYA")) {
                                tempatLahirLain = pihak.getTempatLahir();
                                pihak.setTempatLahir("LAIN-LAIN");
                            }
                        }
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
                pihakByNameList = new ArrayList<Pihak>();
                pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());

                if (pihakByNameList.isEmpty()) {
                    addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsa();
                    senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
                    senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
                    senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());
                }
            } else if (pihak.getJenisPengenalan() == null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                addSimpleError("Sila Masukkan  Nombor Pengenalan atau Nama.");
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
            return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
        }

        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution simpanPihakPopup() {

        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        List<PermohonanPihak> senaraiPemohonanPihak = new ArrayList<PermohonanPihak>();

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }
        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

        }

        pihakTemp.setInfoAudit(info);
        jenisPihak = getContext().getRequest().getParameter("jenisPB");
        KodJenisPihakBerkepentingan kodJenisPihak = new KodJenisPihakBerkepentingan();
        kodJenisPihak.setKod(jenisPihak);
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
        hakmilikPermohonan = hakmilikpermohonanService.findHakmilikPermohonanbyidMH(permohonanPembetulanUrusan.getHakmilik().getId());
        LOG.info(permohonanPembetulanUrusan.getIdPerserahanLama());
        Permohonan mohonLama = permohonanService.findById(permohonanPembetulanUrusan.getIdPerserahanLama());
        PermohonanPihak ppihak = new PermohonanPihak();
        ppihak.setPermohonan(mohonLama);
        ppihak.setKaitan(permohonan.getIdPermohonan());
        ppihak.setPihak(pihakTemp);
        ppihak.setHakmilik(hakmilikPermohonan.getHakmilik());
        ppihak.setJenis(kodJenisPihak);
        ppihak.setInfoAudit(info);
        ppihak.setSyerPembilang(0);
        ppihak.setSyerPenyebut(0);

        ppihak.setNama(pihak.getNama());
        ppihak.setJenisPengenalan(pihak.getJenisPengenalan());
        ppihak.setNoPengenalan(pihak.getNoPengenalan());
        ppihak.setWargaNegara(pihak.getWargaNegara());

        Alamat alamat = new Alamat();
        alamat.setAlamat1(pihak.getAlamat1());
        alamat.setAlamat2(pihak.getAlamat2());
        alamat.setAlamat3(pihak.getAlamat3());
        alamat.setAlamat4(pihak.getAlamat4());
        alamat.setPoskod(pihak.getPoskod());
        alamat.setNegeri(pihak.getNegeri());

        AlamatSurat alamatSurat = new AlamatSurat();
        alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
        alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
        alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
        alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
        alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
        alamatSurat.setNegeriSurat(pihak.getSuratNegeri());

        ppihak.setAlamat(alamat);
        ppihak.setAlamatSurat(alamatSurat);

        if (ppihak != null) {
            ppihak.setCawangan(permohonan.getCawangan());
            senaraiPemohonanPihak.add(ppihak);
            regService.simpanPihak(pihakTemp);
            permohonanPihakService.saveOrUpdate(ppihak);
            rehydrate();

            return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
        }

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution simpanPihakWaris() throws ParseException {

        Permohonan permohonan = null;
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        tambahWaris = getContext().getRequest().getParameter("tambahWaris");
        String namaWaris = getContext().getRequest().getParameter("nama");
        String jenisPengenalan = getContext().getRequest().getParameter("jenisPengenalan");
        String noPengenalan = getContext().getRequest().getParameter("noPengenalan");
        String wargaNegara = getContext().getRequest().getParameter("wargaNegara");
        String jenisPihak = getContext().getRequest().getParameter("jenisPihakWaris");
        String alamat1 = getContext().getRequest().getParameter("alamat1");
        String alamat2 = getContext().getRequest().getParameter("alamat2");
        String alamat3 = getContext().getRequest().getParameter("alamat3");
        String alamat4 = getContext().getRequest().getParameter("alamat4");
        String poskod = getContext().getRequest().getParameter("poskod");
        String negeri = getContext().getRequest().getParameter("negeri");

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        if (!tambahWaris.equals("true")) {
            pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            if (pihakTemp == null) {
                pihakTemp = new Pihak();
                pihakTemp.setNama(namaWaris);
                pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
                pihakTemp.setNoPengenalan(noPengenalan);
                pihakTemp.setKodJantina(pihak.getKodJantina());
                pihakTemp.setBangsa(pihak.getBangsa());
                pihakTemp.setSuku(pihak.getSuku());
                pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
                pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
                pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
                pihakTemp.setWargaNegara(kodWarganegaraDAO.findById(wargaNegara));
                pihakTemp.setAlamat1(alamat1);
                pihakTemp.setAlamat2(alamat2);
                pihakTemp.setAlamat3(alamat3);
                pihakTemp.setAlamat4(alamat4);
                pihakTemp.setPoskod(poskod);
                pihakTemp.setNegeri(kodNegeriDAO.findById(negeri));
                pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
                pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
                pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
                pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
                pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
                pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
                pihakTemp.setTempatLahir(pihak.getTempatLahir());
                pihakTemp.setInfoAudit(info);
            }
        } else {
            pihakTemp = new Pihak();
//            if (pihakTemp == null) {
            pihakTemp.setNama(namaWaris);
            pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalan));
            pihakTemp.setNoPengenalan(noPengenalan);
//            pihakTemp.setKodJantina(pihak.getKodJantina());
//            pihakTemp.setBangsa(pihak.getBangsa());
//            pihakTemp.setSuku(pihak.getSuku());
//            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
//            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
//            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(kodWarganegaraDAO.findById(wargaNegara));
            pihakTemp.setAlamat1(alamat1);
            pihakTemp.setAlamat2(alamat2);
            pihakTemp.setAlamat3(alamat3);
            pihakTemp.setAlamat4(alamat4);
            pihakTemp.setPoskod(poskod);
            pihakTemp.setNegeri(kodNegeriDAO.findById(negeri));
//            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
//            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
//            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
//            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
//            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
//            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
//            pihakTemp.setTempatLahir(pihak.getTempatLahir());
            pihakTemp.setInfoAudit(info);
//            }
        }
        regService.simpanPihak(pihakTemp);

//        permohonanPihakHubunganList = permohonanPihakHubunganService.findHakmilikPermohonanByidMH(idPermohonanPihak);
        PermohonanPihakHubungan PermohonanPihakHubunganNew = new PermohonanPihakHubungan();
        hakmilikPermohonan = hakmilikpermohonanService.findHakmilikPermohonanbyidMH(permohonanPembetulanUrusan.getHakmilik().getId());
        permohonanPihak = permohonanPihakDAO.findById(Long.parseLong(idPermohonanPihak));
        Permohonan mohonLama = permohonanService.findById(permohonanPembetulanUrusan.getIdPerserahanLama());

        PermohonanPihakHubunganNew.setPihak(permohonanPihak);
        PermohonanPihakHubunganNew.setInfoAudit(info);
        PermohonanPihakHubunganNew.setKaitan(jenisPihak);
        PermohonanPihakHubunganNew.setCawangan(permohonan.getCawangan());

        PermohonanPihakHubunganNew.setNama(pihakTemp.getNama());
        PermohonanPihakHubunganNew.setNoPengenalan(pihakTemp.getNoPengenalan());

        Alamat alamat = new Alamat();
        PermohonanPihakHubunganNew.setAlamat1(pihakTemp.getAlamat1());
        PermohonanPihakHubunganNew.setAlamat2(pihakTemp.getAlamat2());
        PermohonanPihakHubunganNew.setAlamat3(pihakTemp.getAlamat3());
        PermohonanPihakHubunganNew.setAlamat4(pihakTemp.getAlamat4());
        PermohonanPihakHubunganNew.setPoskod(pihakTemp.getPoskod());
        PermohonanPihakHubunganNew.setNegeri(pihakTemp.getNegeri());

        PermohonanPihakHubunganNew.setJenisPengenalan(pihakTemp.getJenisPengenalan());
        PermohonanPihakHubunganNew.setWargaNegara(pihakTemp.getWargaNegara());

        permohonanPihakHubunganService.save(PermohonanPihakHubunganNew);
        addSimpleMessage("kemasukan data telah berjaya");
        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution simpanPemohonPopup() throws ParseException {

        Permohonan permohonan = null;
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        permohonanPembetulanUrusan = pService.findByidPembetulan(String.valueOf(permohonan.getIdPermohonan()), idPembetulan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setWargaNegara(pihak.getWargaNegara());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());

        }

        pihakTemp.setInfoAudit(info);
        hakmilikPermohonan = hakmilikpermohonanService.findHakmilikPermohonanbyidMH(permohonanPembetulanUrusan.getHakmilik().getId());
        Permohonan mohonLama = permohonanService.findById(permohonanPembetulanUrusan.getIdPerserahanLama());
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(mohonLama);
        pmohon.setHakmilik(hakmilikPermohonan.getHakmilik());
        pmohon.setPihak(pihakTemp);
        pmohon.setInfoAudit(info);
        pmohon.setKaitan(permohonan.getIdPermohonan());
        pmohon.setCawangan(permohonan.getCawangan());

        pmohon.setNama(pihak.getNama());
        pmohon.setNoPengenalan(pihak.getNoPengenalan());

        Alamat alamat = new Alamat();
        alamat.setAlamat1(pihak.getAlamat1());
        alamat.setAlamat2(pihak.getAlamat2());
        alamat.setAlamat3(pihak.getAlamat3());
        alamat.setAlamat4(pihak.getAlamat4());
        alamat.setPoskod(pihak.getPoskod());
        alamat.setNegeri(pihak.getNegeri());

        AlamatSurat alamatSurat = new AlamatSurat();
        alamatSurat.setAlamatSurat1(pihak.getSuratAlamat1());
        alamatSurat.setAlamatSurat2(pihak.getSuratAlamat2());
        alamatSurat.setAlamatSurat3(pihak.getSuratAlamat3());
        alamatSurat.setAlamatSurat4(pihak.getSuratAlamat4());
        alamatSurat.setPoskodSurat(pihak.getSuratPoskod());
        alamatSurat.setNegeriSurat(pihak.getSuratNegeri());

        pmohon.setAlamat(alamat);
        pmohon.setAlamatSurat(alamatSurat);
        pmohon.setJenisPengenalan(pihak.getJenisPengenalan());
        pmohon.setWargaNegara(pihak.getWargaNegara());

        if (pmohon != null) {

            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            pihakTemp.setTempatLahir(pihak.getTempatLahir());
            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);

            return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
        }

        return new RedirectResolution(pembetulanActionBean.class, "tambahPihak").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution selectName() {

        String idPihak = getContext().getRequest().getParameter("idPihak");
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());

        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution selectNamePemohon() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        idPembetulan = getContext().getRequest().getParameter("idPembetulan");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
        cawanganList = pihak.getSenaraiCawangan();
        pengarahList = pihak.getSenaraiPengarah();
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        senaraiKodBangsaOrang = listUtil.getSenaraiKodBangsaByJenisPengenalan("B");
        senaraiKodBangsaSyarikat = listUtil.getSenaraiKodBangsaByJenisPengenalan("S");
        senaraiKodPenerima = listUtil.getSenaraiKodPihakPenerima(permohonan.getKodUrusan().getJabatanNama(), permohonan.getKodUrusan().getNama());

        return new JSP("daftar/pembetulan/pihak_berkepentingan_popup.jsp").addParameter("popup", "true").addParameter("idPembetulan", idPembetulan);
    }

    public Resolution kiraCukaiKelompok() {
        String result = "";
        String idH = (String) getContext().getRequest().getParameter("idHakmilik");
        Hakmilik h = hakmilikDAO.findById(idH);
        String kodUOM = h.getKodUnitLuas().getKod();
        String kodRizab;

//        MathContext mc = new MathContext(0);
//        mc = mc.DECIMAL32;
        LOG.debug("kiraCukaiKelompok");
        LOG.debug(":idhakmilik:" + idH);
        LOG.debug(":kodUOM:" + kodUOM);
        LOG.debug(":luas:" + h.getLuas());
        LOG.debug("kodGunaTanah : " + h.getKegunaanTanah());
        LOG.debug("kodbpm : " + h.getBandarPekanMukim().getKod());

        if (h.getRizab() != null) {
            LOG.debug("kodRizab : " + h.getRizab().getKod());
            kodRizab = String.valueOf(h.getRizab().getKod());
        } else {
            kodRizab = null;
        }

        if (kodUOM != null && h.getLuas() != null && h.getKegunaanTanah().getKod() != null && h.getBandarPekanMukim() != null) {
            result = String.valueOf(calcTax.calculate(h.getKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, h.getLuas(), h, kodRizab));
        } else if (kodUOM == null) {
            result = "Kod UOM tiada";
        } else if (h.getLuas() == null) {
            result = "Luas tiada";
        } else if (h.getKegunaanTanah() == null) {
            result = "Kod Kegunaan Tanah tiada";
        } else if (h.getBandarPekanMukim() == null) {
            result = "Kod Bandar/Pekan/Mukim  tiada";
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution senaraiKodGunaTanahByKatTanah() {
        String kodKatTanah = (String) getContext().getRequest().getParameter("kodKatTanah");
        if (StringUtils.isNotBlank(kodKatTanah)) {
            listKodGunaTanahByKatTanah = regService.getSenaraiKodGunaTanahByKatTanah(kodKatTanah);
        }
        return new ForwardResolution("/WEB-INF/jsp/daftar/pembetulan/partial_kodgunatanah.jsp").addParameter("popup", "true");
    }

    public Resolution kiraTempoh() {
        String result = "";
        String tempoh = (String) getContext().getRequest().getParameter("tempoh");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Hakmilik hm = pService.findHakmilik(idHakmilik);
        Date OldDate = null;
        if (hm.getTarikhDaftarAsal() == null) {
            OldDate = hm.getTarikhDaftar();
            LOG.info("Tarikh Daftar : " + OldDate);
        } else {
            OldDate = hm.getTarikhDaftarAsal();
            LOG.info("Tarikh Daftar Asal: " + OldDate);
        }
        if (StringUtils.isNotBlank(tempoh)) {
            Calendar ca1 = Calendar.getInstance();
            ca1.set(OldDate.getYear(), OldDate.getMonth(), OldDate.getDay());
            ca1.add(Calendar.YEAR, Integer.valueOf(tempoh) + 1900);
            LOG.info("Tahun: " + ca1.getTime());
            result = String.valueOf(dateFormat.format(ca1.getTime()));
            LOG.info(result);
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution kiraDate() throws ParseException {
        String result = "";
        String tarikh = (String) getContext().getRequest().getParameter("tarikh");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Hakmilik hm = pService.findHakmilik(idHakmilik);
        Date OldDate = null;
        if (hm.getTarikhDaftarAsal() == null) {
            OldDate = hm.getTarikhDaftar();
            LOG.info("Tarikh Daftar : " + OldDate);
        } else {
            OldDate = hm.getTarikhDaftarAsal();
            LOG.info("Tarikh Daftar Asal: " + OldDate);
        }
        if (StringUtils.isNotBlank(tarikh)) {
            Date TodaysDate = dateFormat.parse(tarikh);
            long mills_per_day = 1000 * 60 * 60 * 24;
            long day_diff = (TodaysDate.getTime() - OldDate.getTime()) / mills_per_day;
            long year_diff = day_diff / 365;
            Integer lid = Integer.valueOf((int) year_diff);
            result = String.valueOf(lid);
        }
        return new StreamingResolution("text/plain", result);
    }

    public String getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(String tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public PermohonanRujukanLuar getPkl() {
        return pkl;
    }

    public void setPkl(PermohonanRujukanLuar pkl) {
        this.pkl = pkl;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganPBList() {
        return hpBerkepentinganPBList;
    }

    public void setHpBerkepentinganPBList(List<HakmilikPihakBerkepentingan> hpBerkepentinganPBList) {
        this.hpBerkepentinganPBList = hpBerkepentinganPBList;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganPemilikList() {
        return hpBerkepentinganPemilikList;
    }

    public void setHpBerkepentinganPemilikList(List<HakmilikPihakBerkepentingan> hpBerkepentinganPemilikList) {
        this.hpBerkepentinganPemilikList = hpBerkepentinganPemilikList;
    }

    public String getStatusHakmilikKod() {
        return statusHakmilikKod;
    }

    public void setStatusHakmilikKod(String statusHakmilikKod) {
        this.statusHakmilikKod = statusHakmilikKod;
    }

    public String getTarikhDaftarHakmilikAsal() {
        return tarikhDaftarHakmilikAsal;
    }

    public void setTarikhDaftarHakmilikAsal(String tarikhDaftarHakmilikAsal) {
        this.tarikhDaftarHakmilikAsal = tarikhDaftarHakmilikAsal;
    }

    public HakmilikAsal getHakmilikAsal() {
        return hakmilikAsal;
    }

    public void setHakmilikAsal(HakmilikAsal hakmilikAsal) {
        this.hakmilikAsal = hakmilikAsal;
    }

    public String getIdHA() {
        return idHA;
    }

    public void setIdHA(String idHA) {
        this.idHA = idHA;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public PermohonanPembetulanHakmilik getPermohonanPembetulanHakmilik() {
        return permohonanPembetulanHakmilik;
    }

    public void setPermohonanPembetulanHakmilik(PermohonanPembetulanHakmilik permohonanPembetulanHakmilik) {
        this.permohonanPembetulanHakmilik = permohonanPembetulanHakmilik;
    }

    public List<KodSeksyen> getListKodSeksyenByBpm() {
        return listKodSeksyenByBpm;
    }

    public void setListKodSeksyenByBpm(List<KodSeksyen> listKodSeksyenByBpm) {
        this.listKodSeksyenByBpm = listKodSeksyenByBpm;
    }

    public List<KodStatusHakmilik> getListKodStatusHakmilikB() {
        return listKodStatusHakmilikB;
    }

    public void setListKodStatusHakmilikB(List<KodStatusHakmilik> listKodStatusHakmilikB) {
        this.listKodStatusHakmilikB = listKodStatusHakmilikB;
    }

    public List<KodStatusHakmilik> getListKodStatusHakmilikD() {
        return listKodStatusHakmilikD;
    }

    public void setListKodStatusHakmilikD(List<KodStatusHakmilik> listKodStatusHakmilikD) {
        this.listKodStatusHakmilikD = listKodStatusHakmilikD;
    }

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public List<HakmilikAsal> getHakmilikAsalList() {
        return hakmilikAsalList;
    }

    public void setHakmilikAsalList(List<HakmilikAsal> hakmilikAsalList) {
        this.hakmilikAsalList = hakmilikAsalList;
    }

    public HakmilikSebelum getHakmilikSebelum() {
        return hakmilikSebelum;
    }

    public void setHakmilikSebelum(HakmilikSebelum hakmilikSebelum) {
        this.hakmilikSebelum = hakmilikSebelum;
    }

    public List<HakmilikSebelum> getHakmilikSebelumList() {
        return hakmilikSebelumList;
    }

    public void setHakmilikSebelumList(List<HakmilikSebelum> hakmilikSebelumList) {
        this.hakmilikSebelumList = hakmilikSebelumList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganWarisList() {
        return hpBerkepentinganWarisList;
    }

    public void setHpBerkepentinganWarisList(List<HakmilikPihakBerkepentingan> hpBerkepentinganWarisList) {
        this.hpBerkepentinganWarisList = hpBerkepentinganWarisList;
    }

    public Pihak getPemilik() {
        return pemilik;
    }

    public void setPemilik(Pihak pemilik) {
        this.pemilik = pemilik;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganKAVEATList() {
        return hpBerkepentinganKAVEATList;
    }

    public void setHpBerkepentinganKAVEATList(List<HakmilikPihakBerkepentingan> hpBerkepentinganKAVEATList) {
        this.hpBerkepentinganKAVEATList = hpBerkepentinganKAVEATList;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganPGList() {
        return hpBerkepentinganPGList;
    }

    public void setHpBerkepentinganPGList(List<HakmilikPihakBerkepentingan> hpBerkepentinganPGList) {
        this.hpBerkepentinganPGList = hpBerkepentinganPGList;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganPIList() {
        return hpBerkepentinganPIList;
    }

    public void setHpBerkepentinganPIList(List<HakmilikPihakBerkepentingan> hpBerkepentinganPIList) {
        this.hpBerkepentinganPIList = hpBerkepentinganPIList;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganPJList() {
        return hpBerkepentinganPJList;
    }

    public void setHpBerkepentinganPJList(List<HakmilikPihakBerkepentingan> hpBerkepentinganPJList) {
        this.hpBerkepentinganPJList = hpBerkepentinganPJList;
    }

    public List<HakmilikPihakBerkepentingan> getHpBerkepentinganTENList() {
        return hpBerkepentinganTENList;
    }

    public void setHpBerkepentinganTENList(List<HakmilikPihakBerkepentingan> hpBerkepentinganTENList) {
        this.hpBerkepentinganTENList = hpBerkepentinganTENList;
    }

    public String getKodPB() {
        return kodPB;
    }

    public void setKodPB(String kodPB) {
        this.kodPB = kodPB;
    }

    public HakmilikPihakBerkepentingan getHpBerkepentingan() {
        return hpBerkepentingan;
    }

    public void setHpBerkepentingan(HakmilikPihakBerkepentingan hpBerkepentingan) {
        this.hpBerkepentingan = hpBerkepentingan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanB() {
        return hakmilikUrusanB;
    }

    public void setHakmilikUrusanB(List<HakmilikUrusan> hakmilikUrusanB) {
        this.hakmilikUrusanB = hakmilikUrusanB;
    }

    public List<HakmilikUrusan> getHakmilikUrusanN() {
        return hakmilikUrusanN;
    }

    public void setHakmilikUrusanN(List<HakmilikUrusan> hakmilikUrusanN) {
        this.hakmilikUrusanN = hakmilikUrusanN;
    }

    public List<HakmilikUrusan> getHakmilikUrusanSC() {
        return hakmilikUrusanSC;
    }

    public void setHakmilikUrusanSC(List<HakmilikUrusan> hakmilikUrusanSC) {
        this.hakmilikUrusanSC = hakmilikUrusanSC;
    }

    //    butiran & alamat
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

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
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

    public String getNoSuratAmanah() {
        return noSuratAmanah;
    }

    public void setNoSuratAmanah(String noSuratAmanah) {
        this.noSuratAmanah = noSuratAmanah;
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

    public String getJenisPB() {
        return jenisPB;
    }

    public void setJenisPB(String jenisPB) {
        this.jenisPB = jenisPB;
    }

    public String getKumpulanPB() {
        return kumpulanPB;
    }

    public void setKumpulanPB(String kumpulanPB) {
        this.kumpulanPB = kumpulanPB;
    }

    public boolean isFlagBetul() {
        return flagBetul;
    }

    public void setFlagBetul(boolean flagBetul) {
        this.flagBetul = flagBetul;
    }

    public String getTarikhLuput() {
        return tarikhLuput;
    }

    public void setTarikhLuput(String tarikhLuput) {
        this.tarikhLuput = tarikhLuput;
    }

    public String[] getTarikhluputbaru() {
        return tarikhluputbaru;
    }

    public void setTarikhluputbaru(String[] tarikhluputbaru) {
        this.tarikhluputbaru = tarikhluputbaru;
    }

    public String[] getStatusHakmilikbaru() {
        return statusHakmilikbaru;
    }

    public void setStatusHakmilikbaru(String[] statusHakmilikbaru) {
        this.statusHakmilikbaru = statusHakmilikbaru;
    }

    public String[] getNoPelan() {
        return noPelan;
    }

    public String[] getCukaiThn() {
        return cukaiThn;
    }

    public void setCukaiThn(String[] cukaiThn) {
        this.cukaiThn = cukaiThn;
    }

    public void setNoPelan(String[] noPelan) {
        this.noPelan = noPelan;
    }

    public List<PermohonanAtasPerserahan> getMohonAtasUrusan() {
        return mohonAtasUrusan;
    }

    public void setMohonAtasUrusan(List<PermohonanAtasPerserahan> mohonAtasUrusan) {
        this.mohonAtasUrusan = mohonAtasUrusan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListY() {
        return hakmilikUrusanListY;
    }

    public void setHakmilikUrusanListY(List<HakmilikUrusan> hakmilikUrusanListY) {
        this.hakmilikUrusanListY = hakmilikUrusanListY;
    }

    public String getIdHakmilikBaru() {
        return idHakmilikBaru;
    }

    public void setIdHakmilikBaru(String idHakmilikBaru) {
        this.idHakmilikBaru = idHakmilikBaru;
    }

    public String getIdUrusan() {
        return idUrusan;
    }

    public void setIdUrusan(String idUrusan) {
        this.idUrusan = idUrusan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListByKodserah() {
        return hakmilikUrusanListByKodserah;
    }

    public void setHakmilikUrusanListByKodserah(List<HakmilikUrusan> hakmilikUrusanListByKodserah) {
        this.hakmilikUrusanListByKodserah = hakmilikUrusanListByKodserah;
    }

    public PermohonanPembetulanUrusan getBetulUrusan() {
        return betulUrusan;
    }

    public void setBetulUrusan(PermohonanPembetulanUrusan betulUrusan) {
        this.betulUrusan = betulUrusan;
    }

    public PermohonanUrusan getMohonUrusan() {
        return mohonUrusan;
    }

    public void setMohonUrusan(PermohonanUrusan mohonUrusan) {
        this.mohonUrusan = mohonUrusan;
    }

    public FolderDokumen getFd() {
        return fd;
    }

    public void setFd(FolderDokumen fd) {
        this.fd = fd;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<PermohonanPembetulanHakmilik> getPermohonanbetulHakmilik() {
        return permohonanbetulHakmilik;
    }

    public void setPermohonanbetulHakmilik(List<PermohonanPembetulanHakmilik> permohonanbetulHakmilik) {
        this.permohonanbetulHakmilik = permohonanbetulHakmilik;
    }

    public List<KodStatusHakmilik> getListKodStatusHakmilik() {
        return listKodStatusHakmilik;
    }

    public void setListKodStatusHakmilik(List<KodStatusHakmilik> listKodStatusHakmilik) {
        this.listKodStatusHakmilik = listKodStatusHakmilik;
    }

    public List<HakmilikUrusan> getHakmilikUrusanListNotaBorang() {
        return hakmilikUrusanListNotaBorang;
    }

    public void setHakmilikUrusanListNotaBorang(List<HakmilikUrusan> hakmilikUrusanListNotaBorang) {
        this.hakmilikUrusanListNotaBorang = hakmilikUrusanListNotaBorang;
    }

    public String getKodseksyen() {
        return kodseksyen;
    }

    public void setKodseksyen(String kodseksyen) {
        this.kodseksyen = kodseksyen;
    }

    public List<PermohonanPembetulanUrusan> getListUrusanBetul() {
        return listUrusanBetul;
    }

    public void setListUrusanBetul(List<PermohonanPembetulanUrusan> listUrusanBetul) {
        this.listUrusanBetul = listUrusanBetul;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KodUrusan> getSenaraiUrusanPendaftaran() {
        return senaraiUrusanPendaftaran;
    }

    public void setSenaraiUrusanPendaftaran(List<KodUrusan> senaraiUrusanPendaftaran) {
        this.senaraiUrusanPendaftaran = senaraiUrusanPendaftaran;
    }

    public PermohonanPembetulanUrusan getPermohonanPembetulanUrusan() {
        return permohonanPembetulanUrusan;
    }

    public void setPermohonanPembetulanUrusan(PermohonanPembetulanUrusan permohonanPembetulanUrusan) {
        this.permohonanPembetulanUrusan = permohonanPembetulanUrusan;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getNoFolio() {
        return noFolio;
    }

    public void setNoFolio(String noFolio) {
        this.noFolio = noFolio;
    }

    public String getNoJilid() {
        return noJilid;
    }

    public void setNoJilid(String noJilid) {
        this.noJilid = noJilid;
    }

    public String getIdPembetulan() {
        return idPembetulan;
    }

    public void setIdPembetulan(String idPembetulan) {
        this.idPembetulan = idPembetulan;
    }

    public String getIdHakmilikAsal() {
        return idHakmilikAsal;
    }

    public void setIdHakmilikAsal(String idHakmilikAsal) {
        this.idHakmilikAsal = idHakmilikAsal;
    }

    public String getIdHakmilikSebelum() {
        return idHakmilikSebelum;
    }

    public void setIdHakmilikSebelum(String idHakmilikSebelum) {
        this.idHakmilikSebelum = idHakmilikSebelum;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getPerjanjianAmaun() {
        return perjanjianAmaun;
    }

    public void setPerjanjianAmaun(String perjanjianAmaun) {
        this.perjanjianAmaun = perjanjianAmaun;
    }

    public String getPerjanjianDutiStem() {
        return perjanjianDutiStem;
    }

    public void setPerjanjianDutiStem(String perjanjianDutiStem) {
        this.perjanjianDutiStem = perjanjianDutiStem;
    }

    public String getPerjanjianNoRujukan() {
        return perjanjianNoRujukan;
    }

    public void setPerjanjianNoRujukan(String perjanjianNoRujukan) {
        this.perjanjianNoRujukan = perjanjianNoRujukan;
    }

    public String getTarikhSaksi() {
        return tarikhSaksi;
    }

    public void setTarikhSaksi(String tarikhSaksi) {
        this.tarikhSaksi = tarikhSaksi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoSidang() {
        return noSidang;
    }

    public void setNoSidang(String noSidang) {
        this.noSidang = noSidang;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(String tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getPerintahTarikhKuatkuasa() {
        return perintahTarikhKuatkuasa;
    }

    public void setPerintahTarikhKuatkuasa(String perintahTarikhKuatkuasa) {
        this.perintahTarikhKuatkuasa = perintahTarikhKuatkuasa;
    }

    public String getKodPerintah() {
        return kodPerintah;
    }

    public void setKodPerintah(String kodPerintah) {
        this.kodPerintah = kodPerintah;
    }

    public String getNamaSidang() {
        return namaSidang;
    }

    public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }

    public String getPerintahSebab() {
        return perintahSebab;
    }

    public void setPerintahSebab(String perintahSebab) {
        this.perintahSebab = perintahSebab;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public List<PermohonanPembetulanHakmilik> getListHakimilSblm() {
        return listHakimilSblm;
    }

    public void setListHakimilSblm(List<PermohonanPembetulanHakmilik> listHakimilSblm) {
        this.listHakimilSblm = listHakimilSblm;
    }

    public List<PermohonanPembetulanHakmilik> getListHakmilikAsal() {
        return listHakmilikAsal;
    }

    public void setListHakmilikAsal(List<PermohonanPembetulanHakmilik> listHakmilikAsal) {
        this.listHakmilikAsal = listHakmilikAsal;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public boolean isTambahPengarahFlag() {
        return tambahPengarahFlag;
    }

    public void setTambahPengarahFlag(boolean tambahPengarahFlag) {
        this.tambahPengarahFlag = tambahPengarahFlag;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getTempatLahirLain() {
        return tempatLahirLain;
    }

    public void setTempatLahirLain(String tempatLahirLain) {
        this.tempatLahirLain = tempatLahirLain;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public List<KodBangsa> getSenaraiKodBangsaOrang() {
        return senaraiKodBangsaOrang;
    }

    public void setSenaraiKodBangsaOrang(List<KodBangsa> senaraiKodBangsaOrang) {
        this.senaraiKodBangsaOrang = senaraiKodBangsaOrang;
    }

    public List<KodBangsa> getSenaraiKodBangsaSyarikat() {
        return senaraiKodBangsaSyarikat;
    }

    public void setSenaraiKodBangsaSyarikat(List<KodBangsa> senaraiKodBangsaSyarikat) {
        this.senaraiKodBangsaSyarikat = senaraiKodBangsaSyarikat;
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPenerima() {
        return senaraiKodPenerima;
    }

    public void setSenaraiKodPenerima(List<KodJenisPihakBerkepentingan> senaraiKodPenerima) {
        this.senaraiKodPenerima = senaraiKodPenerima;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public List<Pihak> getPihakByNameList() {
        return pihakByNameList;
    }

    public void setPihakByNameList(List<Pihak> pihakByNameList) {
        this.pihakByNameList = pihakByNameList;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(String cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public String getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(String luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public String getStrKodUOM() {
        return strKodUOM;
    }

    public void setStrKodUOM(String strKodUOM) {
        this.strKodUOM = strKodUOM;
    }

    public String getJenisPihak() {
        return jenisPihak;
    }

    public void setJenisPihak(String jenisPihak) {
        this.jenisPihak = jenisPihak;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public String[] getCukai() {
        return cukai;
    }

    public void setCukai(String[] cukai) {
        this.cukai = cukai;
    }

    public String[] getTempohPeganganBaru() {
        return tempohPeganganBaru;
    }

    public void setTempohPeganganBaru(String[] tempohPeganganBaru) {
        this.tempohPeganganBaru = tempohPeganganBaru;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public boolean isFlagHakmilikStrata() {
        return flagHakmilikStrata;
    }

    public void setFlagHakmilikStrata(boolean flagHakmilikStrata) {
        this.flagHakmilikStrata = flagHakmilikStrata;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getTarikhDaftarUrusan() {
        return tarikhDaftarUrusan;
    }

    public void setTarikhDaftarUrusan(String tarikhDaftarUrusan) {
        this.tarikhDaftarUrusan = tarikhDaftarUrusan;
    }

    public String getKawasan() {
        return kawasan;
    }

    public void setKawasan(String kawasan) {
        this.kawasan = kawasan;
    }

    public String getPerintahTarikhMula() {
        return tarikhMula;
    }

    public void setPerintahTarikhMula(String perintahTarikhMula) {
        this.tarikhMula = perintahTarikhMula;
    }

    public String getMajlis() {
        return majlis;
    }

    public void setMajlis(String majlis) {
        this.majlis = majlis;
    }

    public String getCopyToAllHakmilik() {
        return copyToAllHakmilik;
    }

    public void setCopyToAllHakmilik(String copyToAllHakmilik) {
        this.copyToAllHakmilik = copyToAllHakmilik;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getIdHakmilikTemp() {
        return idHakmilikTemp;
    }

    public void setIdHakmilikTemp(String idHakmilikTemp) {
        this.idHakmilikTemp = idHakmilikTemp;
    }

    public String getTrhKuasa() {
        return trhKuasa;
    }

    public void setTrhKuasa(String trhKuasa) {
        this.trhKuasa = trhKuasa;
    }

    public String getTrhTamat() {
        return trhTamat;
    }

    public void setTrhTamat(String trhTamat) {
        this.trhTamat = trhTamat;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getLuasBaru() {
        return luasBaru;
    }

    public void setLuasBaru(String luasBaru) {
        this.luasBaru = luasBaru;
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

    public String getTarikhDaftarAsal() {
        return tarikhDaftarAsal;
    }

    public void setTarikhDaftarAsal(String tarikhDaftarAsal) {
        this.tarikhDaftarAsal = tarikhDaftarAsal;
    }

    public String[] getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(String[] syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public String[] getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(String[] syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public String[] getSyerPembilang1() {
        return syerPembilang1;
    }

    public void setSyerPembilang1(String[] syerPembilang1) {
        this.syerPembilang1 = syerPembilang1;
    }

    public String[] getSyerPenyebut1() {
        return syerPenyebut1;
    }

    public void setSyerPenyebut1(String[] syerPenyebut1) {
        this.syerPenyebut1 = syerPenyebut1;
    }

    public String getKod_syarat() {
        return kod_syarat;
    }

    public void setKod_syarat(String kod_syarat) {
        this.kod_syarat = kod_syarat;
    }

    public String getKodUOMLama() {
        return kodUOMLama;
    }

    public void setKodUOMLama(String kodUOMLama) {
        this.kodUOMLama = kodUOMLama;
    }

    public String getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getKod_sekatan() {
        return kod_sekatan;
    }

    public void setKod_sekatan(String kod_sekatan) {
        this.kod_sekatan = kod_sekatan;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getRizab_kod() {
        return rizab_kod;
    }

    public void setRizab_kod(String rizab_kod) {
        this.rizab_kod = rizab_kod;
    }

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getGunatanah() {
        return gunatanah;
    }

    public void setGunatanah(String gunatanah) {
        this.gunatanah = gunatanah;
    }

    public String getSAB() {
        return SAB;
    }

    public void setSAB(String SAB) {
        this.SAB = SAB;
    }

    public String getSBD() {
        return SBD;
    }

    public void setSBD(String SBD) {
        this.SBD = SBD;
    }

    public String getSWD() {
        return SWD;
    }

    public void setSWD(String SWD) {
        this.SWD = SWD;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getIdPerserahanLama() {
        return idPerserahanLama;
    }

    public void setIdPerserahanLama(String idPerserahanLama) {
        this.idPerserahanLama = idPerserahanLama;
    }

    public Permohonan getPermohonanLama() {
        return permohonanLama;
    }

    public void setPermohonanLama(Permohonan permohonanLama) {
        this.permohonanLama = permohonanLama;
    }

    public boolean isSWdahAda() {
        return SWdahAda;
    }

    public void setSWdahAda(boolean SWdahAda) {
        this.SWdahAda = SWdahAda;
    }

    public boolean isSBdahAda() {
        return SBdahAda;
    }

    public void setSBdahAda(boolean SBdahAda) {
        this.SBdahAda = SBdahAda;
    }

    public boolean isSAdahAda() {
        return SAdahAda;
    }

    public void setSAdahAda(boolean SAdahAda) {
        this.SAdahAda = SAdahAda;
    }

    public List<HakmilikAsalPermohonan> getListHakmilikAsalMohon() {
        return listHakmilikAsalMohon;
    }

    public void setListHakmilikAsalMohon(List<HakmilikAsalPermohonan> listHakmilikAsalMohon) {
        this.listHakmilikAsalMohon = listHakmilikAsalMohon;
    }

    public List<HakmilikSebelumPermohonan> getListHakimilSblmMohon() {
        return listHakimilSblmMohon;
    }

    public void setListHakimilSblmMohon(List<HakmilikSebelumPermohonan> listHakimilSblmMohon) {
        this.listHakimilSblmMohon = listHakimilSblmMohon;
    }

    public HakmilikAsalPermohonan getHakmilikAsalPermohonan() {
        return hakmilikAsalPermohonan;
    }

    public void setHakmilikAsalPermohonan(HakmilikAsalPermohonan hakmilikAsalPermohonan) {
        this.hakmilikAsalPermohonan = hakmilikAsalPermohonan;
    }

    public HakmilikSebelumPermohonan getHakmilikSebelumPermohonan() {
        return hakmilikSebelumPermohonan;
    }

    public void setHakmilikSebelumPermohonan(HakmilikSebelumPermohonan hakmilikSebelumPermohonan) {
        this.hakmilikSebelumPermohonan = hakmilikSebelumPermohonan;
    }

    public String getTarikhDaftarBaru() {
        return tarikhDaftarBaru;
    }

    public void setTarikhDaftarBaru(String tarikhDaftarBaru) {
        this.tarikhDaftarBaru = tarikhDaftarBaru;
    }

    public Date getTrhDaftarBaru() {
        return trhDaftarBaru;
    }

    public void setTrhDaftarBaru(Date trhDaftarBaru) {
        this.trhDaftarBaru = trhDaftarBaru;
    }

    public List<HakmilikSebelum> getHakmilikSebelumListLama() {
        return hakmilikSebelumListLama;
    }

    public void setHakmilikSebelumListLama(List<HakmilikSebelum> hakmilikSebelumListLama) {
        this.hakmilikSebelumListLama = hakmilikSebelumListLama;
    }

    public List<PermohonanRujukanLuar> getListPermohonanRujukanLuar() {
        return listPermohonanRujukanLuar;
    }

    public void setListPermohonanRujukanLuar(List<PermohonanRujukanLuar> listPermohonanRujukanLuar) {
        this.listPermohonanRujukanLuar = listPermohonanRujukanLuar;
    }

    public List<Dokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Dokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public List<HakmilikUrusan> getHakmilikUrusanSCbatal() {
        return hakmilikUrusanSCbatal;
    }

    public void setHakmilikUrusanSCbatal(List<HakmilikUrusan> hakmilikUrusanSCbatal) {
        this.hakmilikUrusanSCbatal = hakmilikUrusanSCbatal;
    }

    public List<HakmilikUrusan> getHakmilikUrusanNbatal() {
        return hakmilikUrusanNbatal;
    }

    public void setHakmilikUrusanNbatal(List<HakmilikUrusan> hakmilikUrusanNbatal) {
        this.hakmilikUrusanNbatal = hakmilikUrusanNbatal;
    }

    public List<HakmilikUrusan> getHakmilikUrusanBbatal() {
        return hakmilikUrusanBbatal;
    }

    public void setHakmilikUrusanBbatal(List<HakmilikUrusan> hakmilikUrusanBbatal) {
        this.hakmilikUrusanBbatal = hakmilikUrusanBbatal;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public List<KandunganFolder> getListSWD() {
        return listSWD;
    }

    public void setListSWD(List<KandunganFolder> listSWD) {
        this.listSWD = listSWD;
    }

    public List<KandunganFolder> getListSBD() {
        return listSBD;
    }

    public void setListSBD(List<KandunganFolder> listSBD) {
        this.listSBD = listSBD;
    }

    public List<KandunganFolder> getListSAB() {
        return listSAB;
    }

    public void setListSAB(List<KandunganFolder> listSAB) {
        this.listSAB = listSAB;
    }

    public boolean isKemaskini() {
        return kemaskini;
    }

    public void setKemaskini(boolean kemaskini) {
        this.kemaskini = kemaskini;
    }

    public String getIdPermohonanLama() {
        return idPermohonanLama;
    }

    public void setIdPermohonanLama(String idPermohonanLama) {
        this.idPermohonanLama = idPermohonanLama;
    }

    public Dokumen getDokumenSave() {
        return dokumenSave;
    }

    public void setDokumenSave(Dokumen dokumenSave) {
        this.dokumenSave = dokumenSave;
    }

    public boolean isTambah() {
        return tambah;
    }

    public void setTambah(boolean tambah) {
        this.tambah = tambah;
    }

    public String getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(String idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public String getTambahWaris() {
        return tambahWaris;
    }

    public void setTambahWaris(String tambahWaris) {
        this.tambahWaris = tambahWaris;
    }

    public List<PermohonanPihakHubungan> getPermohonanPihakHubunganList() {
        return permohonanPihakHubunganList;
    }

    public void setPermohonanPihakHubunganList(List<PermohonanPihakHubungan> permohonanPihakHubunganList) {
        this.permohonanPihakHubunganList = permohonanPihakHubunganList;
    }

    public List<PihakPengarah> getPengarahList() {
        return pengarahList;
    }

    public void setPengarahList(List<PihakPengarah> pengarahList) {
        this.pengarahList = pengarahList;
    }

    public String[] getUmurWaris() {
        return umurWaris;
    }

    public void setUmurWaris(String[] umurWaris) {
        this.umurWaris = umurWaris;
    }

}

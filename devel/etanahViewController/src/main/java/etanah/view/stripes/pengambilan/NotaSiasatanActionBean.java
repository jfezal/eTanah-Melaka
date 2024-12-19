/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pihak;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanFasaGis;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.Pihak;
import etanah.service.common.LelongService;
import etanah.service.common.HakmilikService;
import etanah.service.PengambilanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.validator.MohonFasaGISValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.util.bean.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.StreamingResolution;
//import org.jgroups.protocols.SIZE;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/nota_siasatan")
public class NotaSiasatanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(NotaSiasatanActionBean.class);
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    LelongService lelongService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    etanah.Configuration conf;
    private Pengguna pengguna;
//    private KodPeranan kodPeranan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPerbicaraan hakmilikPerbicaraan2;
    private HakmilikPermohonan hakmilikPermohonan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Pengguna> senaraiPengguna;
    private List<Pihak> senaraiTuanTanah = new ArrayList<Pihak>();
    private List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar = new ArrayList<PerbicaraanKehadiran>();
    private List<PerbicaraanKehadiran> senaraiPermohonanPihakTdkBKepentingan = new ArrayList<PerbicaraanKehadiran>();
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private PerbicaraanKehadiran perbicaraanKehadiranPPTB;
    public String noRujukan;
    private Date tarikhPemilikan;
    private String caraPemilikan;
    private BigDecimal hargaPembelian;
    private BigDecimal nilaianPenilai;
    private BigDecimal keputusanNilai;
//    private BigDecimal nilaiHutang= BigDecimal.ZERO;
//    private BigDecimal luasTerlibat= BigDecimal.ZERO;
    private BigDecimal nilaiHutang = new BigDecimal(1);
    private BigDecimal luasTerlibat = new BigDecimal(0);

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public BigDecimal getNilaiHutang() {
        return nilaiHutang;
    }

    public void setNilaiHutang(BigDecimal nilaiHutang) {
        this.nilaiHutang = nilaiHutang;
    }
//    private BigDecimal nilaiHutang;
//    private BigDecimal luasTerlibat;
    private String lokasi;
    private BigDecimal jarakKeBandar;
    private KodUOM kodUOM;
    private String keadaanTanah;
    private String bangunan;
    private String tanaman;
    private BigDecimal amaunDituntut;
    private String alasanTuntutan;
    private String pemohonUlasan;
    private String penilaiNoRujukan;
    private String penilaiTarikh;
    private String penilaiAmaun;
    private String penilaiUlasan;
    private String catatan;
    private String catatanKeputusan;
    private String idPengguna;
    private String idPerbicaraan;
    private Date tarikhMasuk;
    private String diMasuk;
    private String keteranganGKL;
    private String keteranganPPP;
    private String keteranganTuanpunya;
    private String keteranganLain;
    private String penilaiNama;
    private String akujanjiPenilai;
    private String ulasanPenilai;
    private String lokasiTerkini;
    private String pecahSyarat;
    private String ujudGPPJ;
    private String komenGPPJ;
    private Long idPermohonanPihak;
//    private String kodPeranan;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private List<BigDecimal> itemNilaianTanahList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianBngnList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianLainList = new ArrayList<BigDecimal>();
    private List<Character> hadir = new ArrayList<Character>();
    private List<Character> hadir2 = new ArrayList<Character>();
    private List<String> keputusan = new ArrayList<String>();
    private List<String> keputusan2 = new ArrayList<String>();
    private List<Date> tarikhMilik = new ArrayList<Date>();
    private List<String> caraMilik = new ArrayList<String>();
    private List<String> hargaMilik = new ArrayList<String>();
    private Penilaian penilaian;
    private char r = 'T';
    private char p = 'T';
    private char pbt = 'T';
    private String tarikhBicara;
    private String jam;
    private String minit;
    private String ampm;
    private int size = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanPihakTidakBerkepentingan pptb;
    private List<PermohonanPihakTidakBerkepentingan> senaraiPPTB;
    private List<PerbicaraanKehadiran> bantahElektrikKList;
    private List<PerbicaraanKehadiran> bantahElektrikSList;
    private List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar1;
    private String kodUrusan;
    private PermohonanPihak permohonanPihak;
//    private PermohonanPihak ph;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution showPerintah() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
                    senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
                    System.out.println("saiz senarai mohon pihak >> " + senaraiPermohonanPihak.size());
                }
            }
        }
        return new JSP("pengambilan/perintah.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonan.getKodUrusan().getKod();
        if (idPermohonan != null) {
            // fasaPermohonanService = null;
            Permohonan p = permohonanDAO.findById(idPermohonan);
            senaraiPengguna = notisPenerimaanService.getPengguna();
            logger.info("::senaraiPengguna:: " + senaraiPengguna.size());
            logger.info("::senaraiPenggunaSahaja:: " + senaraiPengguna);
            // Permohonan HAkmilik Kedesakan
            FasaPermohonan fasaPermohonan = null;
            try {
                // bila split kedesakan
                fasaPermohonan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
                System.out.println("fasa " + fasaPermohonan.getIdAliran());
                if (fasaPermohonan != null) {
                    // listkan ade kedesakan
                    hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                }
            } catch (Exception h) {

                hakmilikPermohonanList = pengambilanService.findbyIdHakmilikTiadaKedesakan(idPermohonan);
                System.out.println("Error fasa Permohonan " + h);
            }
        }
//        senaraiPengguna = notisPenerimaanService.getPengguna();
    }

    public Resolution showEditTuanTanah() {
        String idpihak = getContext().getRequest().getParameter("idPihak");
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");

    }

    public Resolution showTuanTanahPopup() {
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showTuanTanahXPopup() {
        return new JSP("pengambilan/notasiasatan2_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showPBTDaftarPopup() {
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution showPBTXDaftarPopup() {
        return new JSP("pengambilan/notasiasatan2_popup.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikDetails() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();

        hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        System.out.println("hakmilik pihak berkepentingan" + hpList.size());
        logger.info("id Hakmilik " + idHakmilik);
        logger.info("idPermohonan " + idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        kodUrusan = permohonan.getKodUrusan().getKod();
        logger.info("hakmilikPermohonan " + hakmilikPermohonan.getId());
        logger.info("kodUrusan " + kodUrusan);
        if (hakmilikPermohonan != null) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PILDA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                kodUrusan = permohonan.getKodUrusan().getKod();
                hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
                senaraiPengguna = notisPenerimaanService.getPengguna();
                logger.info("==::senaraiPengguna::== " + senaraiPengguna.size());
                logger.info("==::senaraiPenggunaSahaja::== " + senaraiPengguna);
                // logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
                if (hakbicaraList.size() > 1) {
                    hakmilikPerbicaraan = hakbicaraList.get(0);
                } else {
                    //   hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraNotaSiasatan(hakmilikPermohonan.getId());
                    //  logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
                }

                if (hakmilikPerbicaraan == null) {
                    logger.info("create id bicara baru ");
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }
                if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                    getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                }

                if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                    String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    tarikhBicara = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    ampm = tarikh.substring(20, 22);
                }





            } else {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicara(hakmilikPermohonan.getId());
                //   logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());

                hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
                //  logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
                if (hakmilikPerbicaraan == null) {
                    logger.info("create id bicara baru ");
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }
                if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                    getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                }

                if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                    String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    tarikhBicara = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    ampm = tarikh.substring(20, 22);
                }





            }


            senaraiPPTB = notisPenerimaanService.getSenaraiPPTBByHakmilik(hakmilikPermohonan.getId());
            if (!senaraiPPTB.isEmpty()) {
                for (PermohonanPihakTidakBerkepentingan ptb : senaraiPPTB) {
                    pptb = notisPenerimaanService.getPBT(hakmilikPermohonan.getId(), ptb.getIdPermohonanPhkTdkBerptg());
                    perbicaraanKehadiranPPTB = notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(ptb.getIdPermohonanPhkTdkBerptg(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiranPPTB == null) {
                        perbicaraanKehadiranPPTB = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiranPPTB.setInfoAudit(infoAudit);
                        perbicaraanKehadiranPPTB.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiranPPTB.setPerbicaraan(hakmilikPerbicaraan);
                        perbicaraanKehadiranPPTB.setPermohonanPihakTidakBerkepentingan(pptb);
                        notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiranPPTB);
                    }



                }
//                for (int i=0;i<=senaraiPPTB.size();i++){
//                PermohonanPihakTidakBerkepentingan ptb= senaraiPPTB.get(i);
//                pptb=notisPenerimaanService.getPBT(hakmilikPermohonan.getId(),ptb.getIdPermohonanPhkTdkBerptg());
//                perbicaraanKehadiranPPTB=notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(ptb.getIdPermohonanPhkTdkBerptg(), hakmilikPerbicaraan.getIdPerbicaraan());
//                if(perbicaraanKehadiranPPTB == null) {
//                    perbicaraanKehadiranPPTB = new PerbicaraanKehadiran();
//                    InfoAudit infoAudit = new InfoAudit();
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                    infoAudit.setDimasukOleh(peng);
//                    perbicaraanKehadiranPPTB.setInfoAudit(infoAudit);
//                    perbicaraanKehadiranPPTB.setCawangan(permohonan.getCawangan());
//                    perbicaraanKehadiranPPTB.setPerbicaraan(hakmilikPerbicaraan);
//                    perbicaraanKehadiranPPTB.setPermohonanPihakTidakBerkepentingan(pptb);
//                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiranPPTB);
//                }
//
//                }


            }


            for (int i = 0; i < hpList.size(); i++) {
                System.out.println("masuk loop senarai kehadiran");

                HakmilikPihakBerkepentingan hp = hpList.get(i);
                List<PermohonanPihak> permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                for (PermohonanPihak permohonanPihak : permohonanPihakList) {
                    perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiran == null) {
                        perbicaraanKehadiran = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                        perbicaraanKehadiran.setPihak(permohonanPihak);
                        notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                    }
                }
            }
            int c = 1;
            int counter = hakbicaraList.size() - c;
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            for (int i = 0; i < senaraiPihakPentingBerdaftar.size(); i++) {
                perbicaraanKehadiran = senaraiPihakPentingBerdaftar.get(i);
                tarikhMilik.add(perbicaraanKehadiran.getPenilaiTarikh());
                caraMilik.add(perbicaraanKehadiran.getPenilaiCatatan());
                hargaMilik.add(perbicaraanKehadiran.getPenilaiUlasan());
                if (perbicaraanKehadiran.getHadir() == null) {
                    hadir.add('0');
                } else if (perbicaraanKehadiran.getHadir().equals('1')) {
                    hadir.add('1');
                } else if (perbicaraanKehadiran.getHadir().equals('0')) {
                    hadir.add('0');
                }
                if (perbicaraanKehadiran.getBantahElektrik() == null) {
                    keputusan.add("0");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("1")) {
                    keputusan.add("1");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("0")) {
                    keputusan.add("0");
                }
            }
            senaraiPermohonanPihakTdkBKepentingan = notisPenerimaanService.getSenaraiPBTTidakDaftar2(hakmilikPerbicaraan.getIdPerbicaraan());
            for (int i = 0; i < senaraiPermohonanPihakTdkBKepentingan.size(); i++) {
                perbicaraanKehadiran = senaraiPermohonanPihakTdkBKepentingan.get(i);
                if (perbicaraanKehadiran.getHadir() == null) {
                    hadir2.add('0');
                } else if (perbicaraanKehadiran.getHadir().equals('1')) {
                    hadir2.add('1');
                } else if (perbicaraanKehadiran.getHadir().equals('0')) {
                    hadir2.add('0');
                }
                if (perbicaraanKehadiran.getBantahElektrik() == null) {
                    keputusan2.add("0");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("1")) {
                    keputusan2.add("1");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("0")) {
                    keputusan2.add("0");
                }
            }
//            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
//                if (perbicaraanKehadiran.getHadir() != null && perbicaraanKehadiran.getHadir() != ' ') {
//                    hadir.set(i, perbicaraanKehadiran.getHadir());
////                   hadir2.set(i, perbicaraanKehadiran.getHadir());
//                }
//            }
            /**
             * *************************************************SENARAI TUAN
             * TANAH****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiTuanTanah = notisPenerimaanService.getSenaraiTuanTanah(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("-----senaraiTuanTanah-----" + getSenaraiTuanTanah().size());
            /**
             * *************************************************SENARAI PBT
             * Berdaftar****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("-----senaraiPihakPentingBerdaftar-----" + senaraiPihakPentingBerdaftar.size());
            /**
             * *************************************************SENARAI PBT
             * Tidak
             * Berdaftar****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiPermohonanPihakTdkBKepentingan = notisPenerimaanService.getSenaraiPBTTidakDaftar2(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("-----senaraiPihakPentingBerdaftarTidakBerdaftar-----" + senaraiPermohonanPihakTdkBKepentingan.size());
        }
        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        logger.debug("id Hakmilik " + idHakmilik);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        // hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraNotaSiasatan(hakmilikPermohonan.getId());

        logger.info("genReport :: start");
        logger.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String gen3 = "";
        String gen4 = "";
        String code = "";
        String code3 = "";
        String code4 = "";
        bantahElektrikKList = hakmilikService.findBantahElektrik0(hakmilikPerbicaraan.getIdPerbicaraan());
        logger.info("***bantahElektrikKList*** " + bantahElektrikKList.size());
        bantahElektrikSList = hakmilikService.findBantahElektrik1(hakmilikPerbicaraan.getIdPerbicaraan());
        logger.info("***bantahElektrikSList*** " + bantahElektrikSList.size());

        try {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "ACQDocK_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9

                //// jana semula nota bicara
                code = "NSIA";
                gen = "ACQNotaBicara_MLK.rdf";
                logger.info("genReportFromXML ** G");
                logger.info("p == " + p);
                logger.info("gen == " + gen);
                logger.info("code == " + code);
                logger.info("pengguna == " + pengguna);
                lelongService.reportGen(p, gen, code, pengguna);

                code = "HC";
                gen = "ACQDOCH_lain.rdf";
                logger.info("genReportFromXML ** G");
                logger.info("p == " + p);
                logger.info("gen == " + gen);
                logger.info("code == " + code);
                logger.info("pengguna == " + pengguna);
                lelongService.reportGen(p, gen, code, pengguna);

                if (StringUtils.isNotBlank(idHakmilik)) {
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                    List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
                    logger.info("hp == " + hpList.size());

                    for (HakmilikPihakBerkepentingan hp : hpList) {
                       

                        try {
                             PermohonanPihak pp = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                        logger.info("pp == " + pp);
                            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                            logger.info("Hakmilik Permohonan " + hakmilikPermohonan);
                            //hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraNotaSiasatan(hakmilikPermohonan.getId());
                            logger.info("Hakmilik Perbicaraan " + hakmilikPerbicaraan);

                            logger.info("pp.getIdPermohonanPihak() " + pp.getIdPermohonanPihak());
                            logger.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());
                            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                            logger.info("Perbicaraan Kehadiran " + perbicaraanKehadiran.getIdKehadiran());
                        } catch (Exception j) {
                        }
                        if (perbicaraanKehadiran.getBantahElektrik() != null) {
                            logger.info("Bantah Elektrik x null ");

                            switch (gatCase(bantahElektrikKList, bantahElektrikSList)) {
                                case 1:
                                    //Bantah Elektrik 0
                                    logger.info("Bantah Elektrik == 0 ");
                                    code = "G";
                                    gen = "ACQDocG_MLK.rdf";
                                    logger.info("genReportFromXML ** G");
                                    logger.info("p == " + p);
                                    logger.info("gen == " + gen);
                                    logger.info("code == " + code);
                                    logger.info("pengguna == " + pengguna);
                                    lelongService.reportGen(p, gen, code, pengguna);

                                    code3 = "H";
                                    gen3 = "ACQDocH_MLK.rdf";
                                    logger.info("genReportFromXML ** H");
                                    logger.info("p == " + p);
                                    logger.info("gen3 == " + gen3);
                                    logger.info("code3 == " + code3);
                                    logger.info("pengguna == " + pengguna);
                                    lelongService.reportGen3(p, gen3, code3, pengguna);
                                    break;
                                case 2:
                                    //Bantah Elektrik 1
                                    logger.info("Bantah Elektrik == 1 ");
                                    code = "M";
                                    gen = "ACQDocM_NS.rdf";
                                    logger.info("genReportFromXML ** M");
                                    logger.info("p == " + p);
                                    logger.info("gen == " + gen);
                                    logger.info("code == " + code);
                                    logger.info("pengguna == " + pengguna);
//                                    lelongService.reportGen(p, gen, code, pengguna);
                                    break;
                                case 3:
                                    //Bantah Elektrik 0&1
                                    logger.info("Bantah Elektrik == 0 & 1 ");
                                    code = "G";
                                    gen = "ACQDocG_MLK.rdf";
                                    logger.info("genReportFromXML ** G");
                                    logger.info("p == " + p);
                                    logger.info("gen == " + gen);
                                    logger.info("code == " + code);
                                    logger.info("pengguna == " + pengguna);
                                    lelongService.reportGen(p, gen, code, pengguna);

                                    code3 = "H";
                                    gen3 = "ACQDocH_MLK.rdf";
                                    logger.info("genReportFromXML ** H");
                                    logger.info("p == " + p);
                                    logger.info("gen3 == " + gen3);
                                    logger.info("code3 == " + code3);
                                    logger.info("pengguna == " + pengguna);
                                    lelongService.reportGen3(p, gen3, code3, pengguna);

//                                    code4 = "M";
//                                    gen4 = "ACQDocM_NS.rdf";
//                                    logger.info("genReportFromXML ** M");
//                                    logger.info("p == " + p);
//                                    logger.info("gen4 == " + gen4);
//                                    logger.info("code4 == " + code4);
//                                    logger.info("pengguna == " + pengguna);
//                                    lelongService.reportGen4(p, gen4, code4, pengguna);
//                                    break;
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        logger.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);

    }

    public int gatCase(List<PerbicaraanKehadiran> bantahElektrikKList, List<PerbicaraanKehadiran> bantahElektrikSList) {
        if (!bantahElektrikKList.isEmpty() && bantahElektrikSList.isEmpty()) {
            //case 1
            return 1;
        }
        if (bantahElektrikKList.isEmpty() && !bantahElektrikSList.isEmpty()) {
            //case 2
            return 2;
        }
        if (!bantahElektrikKList.isEmpty() && !bantahElektrikSList.isEmpty()) {
            //case 3
            return 3;
        }
        return 0;
    }

    public Resolution simpan() throws ParseException, java.text.ParseException {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("------------peng-----------" + peng);
        System.out.println("------------idPermohonan-----------" + idPermohonan);
        System.out.println("------------idHakmilik-----------" + idHakmilik);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (hakmilikPermohonan != null) {
            luasTerlibat = hakmilikPermohonan.getLuasTerlibat();
        }

        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        System.out.println(hakmilikPerbicaraan.getIdPerbicaraan());

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PILDA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHL") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {

//            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());

            hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
            //   logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
            if (hakbicaraList.size() > 1) {
                hakmilikPerbicaraan = hakbicaraList.get(0);
            } else {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraNotaSiasatan(hakmilikPermohonan.getId());
                // logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
            }

            logger.info("masuk sini");
            if (hakmilikPerbicaraan == null) {
                hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(info);
                hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraan = hakmilikPerbicaraanDAO.saveOrUpdate(hakmilikPerbicaraanTemp);
            } else {
//                hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                hakmilikPerbicaraanTemp = hakmilikPerbicaraan;
                logger.info("");
                InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }

            hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
            // catatan
            logger.info("masuk catatan");
            int c = 1;
            int counter = hakbicaraList.size() - c;
            for (HakmilikPerbicaraan hp : hakbicaraList) {
                System.out.println("update HakmilikPerbicaraan");
                hp = hakbicaraList.get(counter);

//                hakmilikPerbicaraanTemp = hakmilikPerbicaraan;
                InfoAudit info = hp.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                hp.setInfoAudit(info);

            }

        } else {
            if (notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(info);
                hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
            } else {
                hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }
        }
        catatanKeputusan = getContext().getRequest().getParameter("hakmilikPerbicaraan.catatan");
        System.out.println("catatanKeputusan");
        hakmilikPerbicaraanTemp = hakmilikPerbicaraanDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
        System.out.println("-----------hakmilikPerbicaraanTemp-------------" + hakmilikPerbicaraanTemp);
//        hakmilikPerbicaraanTemp.setIdPerbicaraan(hakmilikPerbicaraan.getIdPerbicaraan());
        hakmilikPerbicaraanTemp.setCatatan(catatanKeputusan);
        System.out.println("catatan = " + catatanKeputusan);
        hakmilikPerbicaraanTemp.setNoRujukan(hakmilikPerbicaraan.getNoRujukan());
//        idPengguna = getContext().getRequest().getParameter("hakmilikPerbicaraan.dibicaraOleh.idPengguna");
        String idP = getContext().getRequest().getParameter("hakmilikPerbicaraan.dibicaraOleh.idPengguna");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.dibicaraOleh.idPengguna"))) {
            hakmilikPerbicaraanTemp.setDibicaraOleh(penggunaDAO.findById(idP));
        }

//        hakmilikPerbicaraanTemp.setDibicaraOleh(penggunaDAO.findById(idPengguna));
        String tarikhP = getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhPemilikan");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhPemilikan"))) {
            hakmilikPerbicaraanTemp.setTarikhPemilikan(sdf1.parse(tarikhP));
        }
        caraPemilikan = getContext().getRequest().getParameter("hakmilikPerbicaraan.caraPemilikan");
        hakmilikPerbicaraanTemp.setCaraPemilikan(caraPemilikan);

        String hargaP = getContext().getRequest().getParameter("hakmilikPerbicaraan.hargaPembelian");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.hargaPembelian"))) {
            hakmilikPerbicaraanTemp.setHargaPembelian(BigDecimal.valueOf(Double.parseDouble(hargaP)));
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.nilaianPenilai"))) {
            hakmilikPerbicaraanTemp.setNilaianPenilai(nilaianPenilai);
        }
        penilaiNoRujukan = getContext().getRequest().getParameter("hakmilikPerbicaraan.penilaiNoRujukan");
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(penilaiNoRujukan);
        tanaman = getContext().getRequest().getParameter("hakmilikPerbicaraan.tanaman");
        hakmilikPerbicaraanTemp.setTanaman(tanaman);
        bangunan = getContext().getRequest().getParameter("hakmilikPerbicaraan.bangunan");
        hakmilikPerbicaraanTemp.setBangunan(bangunan);
        pemohonUlasan = getContext().getRequest().getParameter("hakmilikPerbicaraan.pemohonUlasan");
        hakmilikPerbicaraanTemp.setPemohonUlasan(pemohonUlasan);
        alasanTuntutan = getContext().getRequest().getParameter("hakmilikPerbicaraan.alasanTuntutan");
        hakmilikPerbicaraanTemp.setAlasanTuntutan(alasanTuntutan);

        String keputusanN = getContext().getRequest().getParameter("hakmilikPerbicaraan.keputusanNilai");
//        Double keputusanN = Double.valueOf(getContext().getRequest().getParameter("hakmilikPerbicaraan.keputusanNilai"));
        System.out.println("keputusanN.replace(\",\", \"\") " + keputusanN.replace(",", ""));
//        System.out.println("keputusanN.replace(\",\", \"\") " + keputusanN);
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.keputusanNilai"))) {
//            hakmilikPerbicaraanTemp.setKeputusanNilai(BigDecimal.valueOf(Double.parseDouble(keputusanN.replace(",", ""))));
            hakmilikPerbicaraanTemp.setKeputusanNilai(BigDecimal.valueOf(Double.parseDouble(keputusanN.replace(",", ""))));
//              hakmilikPerbicaraanTemp.setKeputusanNilai(BigDecimal.valueOf(keputusanN));
            nilaiHutang = BigDecimal.valueOf(Double.parseDouble(keputusanN.replace(",", ""))).multiply(luasTerlibat);

            logger.debug("keputusan Nilai " + BigDecimal.valueOf(Double.parseDouble(keputusanN.replace(",", ""))));
//            logger.debug("keputusan Nilai "+BigDecimal.valueOf(keputusanN));
            logger.debug("luas terlibat " + luasTerlibat);
            logger.debug("luas terlibat " + luasTerlibat);
            logger.debug("nilai hutang " + nilaiHutang);

            hakmilikPerbicaraanTemp.setNilaiHutang(nilaiHutang);
        }
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.kodUOM.kod"))) {
            hakmilikPerbicaraanTemp.setKodUOM(kodUOMDAO.findById(getContext().getRequest().getParameter("hakmilikPerbicaraan.kodUOM.kod")));
        }
        penilaiNoRujukan = getContext().getRequest().getParameter("hakmilikPerbicaraan.penilaiNoRujukan");
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(penilaiNoRujukan);
        keteranganGKL = getContext().getRequest().getParameter("hakmilikPerbicaraan.keteranganGKL");
        hakmilikPerbicaraanTemp.setKeteranganGKL(keteranganGKL);
        keteranganPPP = getContext().getRequest().getParameter("hakmilikPerbicaraan.keteranganPPP");
        hakmilikPerbicaraanTemp.setKeteranganPPP(keteranganPPP);
        keteranganTuanpunya = getContext().getRequest().getParameter("hakmilikPerbicaraan.keteranganTuanpunya");
        hakmilikPerbicaraanTemp.setKeteranganTuanpunya(keteranganTuanpunya);
        keteranganLain = getContext().getRequest().getParameter("hakmilikPerbicaraan.keteranganLain");
        hakmilikPerbicaraanTemp.setKeteranganLain(keteranganLain);
        String tarikhSuratPenilai = getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhSuratPenilai");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhSuratPenilai"))) {
            hakmilikPerbicaraanTemp.setTarikhSuratPenilai(sdf1.parse(tarikhSuratPenilai));
        }
        String tarikhPecahSyarat = getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhPecahSyarat");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("hakmilikPerbicaraan.tarikhPecahSyarat"))) {
            hakmilikPerbicaraanTemp.setTarikhPecahSyarat(sdf1.parse(tarikhPecahSyarat));
        }
        akujanjiPenilai = getContext().getRequest().getParameter("hakmilikPerbicaraan.akujanjiPenilai");
        hakmilikPerbicaraanTemp.setAkujanjiPenilai(akujanjiPenilai);
        penilaiNama = getContext().getRequest().getParameter("hakmilikPerbicaraan.penilaiNama");
        hakmilikPerbicaraanTemp.setPenilaiNama(penilaiNama);
        ulasanPenilai = getContext().getRequest().getParameter("hakmilikPerbicaraan.ulasanPenilai");
        hakmilikPerbicaraanTemp.setUlasanPenilai(ulasanPenilai);
        lokasiTerkini = getContext().getRequest().getParameter("hakmilikPerbicaraan.lokasiTerkini");
        hakmilikPerbicaraanTemp.setLokasiTerkini(lokasiTerkini);
        pecahSyarat = getContext().getRequest().getParameter("hakmilikPerbicaraan.pecahSyarat");
        hakmilikPerbicaraanTemp.setPecahSyarat(pecahSyarat);
        ujudGPPJ = getContext().getRequest().getParameter("hakmilikPerbicaraan.ujudGPPJ");
        hakmilikPerbicaraanTemp.setUjudGPPJ(ujudGPPJ);
        komenGPPJ = getContext().getRequest().getParameter("hakmilikPerbicaraan.komenGPPJ");
        hakmilikPerbicaraanTemp.setKomenGPPJ(komenGPPJ);
        noRujukan = getContext().getRequest().getParameter("hakmilikPerbicaraan.noRujukan");
        hakmilikPerbicaraanTemp.setNoRujukan(noRujukan);
        //   if (hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
        if (hakmilikPerbicaraan != null && hakmilikPerbicaraan.getIdPerbicaraan() > 0) {

            System.out.println("--------hakmilikPerbicaraan.getIdPerbicaraan()------------" + hakmilikPerbicaraan.getIdPerbicaraan());
            hakmilikPerbicaraan = hakmilikPerbicaraanDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("--------senaraiPihakPentingBerdaftar------------" + notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan()));
            if (senaraiPihakPentingBerdaftar.size() > 0) {
                perbicaraanKehadiran = perbicaraanKehadiranDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
                for (int i = 0; i < senaraiPihakPentingBerdaftar.size(); i++) {
                    perbicaraanKehadiran = senaraiPihakPentingBerdaftar.get(i);
                    System.out.println("--------perbicaraanKehadiran------------" + perbicaraanKehadiran);
                    System.out.println("--------hadir------------" + hadir.get(i));
                    System.out.println("--------Keputusan------------" + keputusan.get(i));
                    // System.out.println("--------tarikhMilik------------" + tarikhMilik.get(i));
                    perbicaraanKehadiran.setHadir(hadir.get(i));
                    perbicaraanKehadiran.setBantahElektrik(keputusan.get(i));
                    try {
                        perbicaraanKehadiran.setPenilaiTarikh(tarikhMilik.get(i));
                        perbicaraanKehadiran.setPenilaiCatatan(caraMilik.get(i));
                        perbicaraanKehadiran.setPenilaiUlasan(hargaMilik.get(i));
                    } catch (Exception jk) {
                    }
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            } else {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                perbicaraanKehadiran.setInfoAudit(info);
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pptb);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
            senaraiPermohonanPihakTdkBKepentingan = notisPenerimaanService.getSenaraiPBTTidakDaftar2(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("--------senaraiPermohonanPihakTdkBKepentingan------------" + senaraiPermohonanPihakTdkBKepentingan);
            if (senaraiPermohonanPihakTdkBKepentingan.size() > 0) {
                perbicaraanKehadiran = perbicaraanKehadiranDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
                for (int i = 0; i < senaraiPermohonanPihakTdkBKepentingan.size(); i++) {
                    perbicaraanKehadiran = senaraiPermohonanPihakTdkBKepentingan.get(i);
                    System.out.println("--------perbicaraanKehadiranPPTB------------" + perbicaraanKehadiran);
                    System.out.println("--------hadir2------------" + hadir2.get(i));
                    System.out.println("--------Keputusan------------" + keputusan2.get(i));
                    perbicaraanKehadiran.setHadir(hadir2.get(i));
                    perbicaraanKehadiran.setBantahElektrik(keputusan2.get(i));
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            } else {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                perbicaraanKehadiran.setInfoAudit(info);
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pptb);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }

            if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                getContext().getRequest().setAttribute("showReport", Boolean.TRUE);
            }
            String kodPeranan = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                kodPeranan = "226";  // Melaka
            } else {
                kodPeranan = "71";   // NS
            }
            logger.info("------Kod Negeri----- : " + (conf.getProperty("kodNegeri")));
            logger.info("------Kod Peranan---- : " + kodPeranan);

            if (peng.getPerananUtama().getKod().equals(kodPeranan)) {
                logger.info("masuk kod peranan ");
                getContext().getRequest().setAttribute("showReport", Boolean.TRUE);
//                catatan = hakmilikPerbicaraan.getCatatan();
//                logger.debug("=== pilih catatan === " + catatan);
//                if (StringUtils.isNotBlank(catatan)) {
//                    if (catatan.equalsIgnoreCase("Bantahan")) {
//                        logger.info("masuk catatan == bantahan ");
//                        getContext().getRequest().setAttribute("showReport", Boolean.TRUE);
//                    }
//                }

            }
        }
        senaraiTuanTanah = notisPenerimaanService.getSenaraiTuanTanah(hakmilikPerbicaraan.getIdPerbicaraan());
        hakmilikPerbicaraanTemp.setBatalRizab(r);
        hakmilikPerbicaraanTemp.setKawasanPBT(pbt);
        hakmilikPerbicaraanTemp.setPelanPembangunan(p);

        hakmilikPerbicaraan = hakmilikPerbicaraanDAO.saveOrUpdate(hakmilikPerbicaraanTemp);

//         if (hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
//            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
//            if (senaraiPerbicaraanKehadiran != null) {
//                for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                    perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
//                    perbicaraanKehadiran.setHadir(hadir.get(i));
//                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
//                }
//            } else {
//                perbicaraanKehadiran = new PerbicaraanKehadiran();
//                InfoAudit info = new InfoAudit();
//                info.setDimasukOleh(peng);
//                info.setTarikhMasuk(new java.util.Date());
//                perbicaraanKehadiran.setInfoAudit(info);
//                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
//                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
//                perbicaraanKehadiran.setPihak(permohonanPihak);
//                perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pptb);
//                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
//            }



        addSimpleMessage("Maklumat telah berjaya disimpan");
        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKehadiran() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        logger.info("simpan kehadiran");
        logger.info("idHakmilik" + idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hpp : hakmilikPermohonanList) {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicara2(hpp.getId());
            if (hakmilikPerbicaraan != null) {
                logger.info("loop 2");
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara(hakmilikPerbicaraan.getIdPerbicaraan());
                if (senaraiPerbicaraanKehadiran != null) {
                    logger.info("masuk sini create org baru");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setNama(getContext().getRequest().getParameter("perbicaraanKehadiran.nama"));
                    perbicaraanKehadiran.setNoPengenalan(getContext().getRequest().getParameter("perbicaraanKehadiran.noPengenalan"));
                    perbicaraanKehadiran.setJawatan(getContext().getRequest().getParameter("perbicaraanKehadiran.jawatan"));
                    perbicaraanKehadiran.setPenilaiUlasan(getContext().getRequest().getParameter("perbicaraanKehadiran.penilaiUlasan"));
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }


        }
        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPBTTidakPenting() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        logger.info("simpan kehadiran");
        logger.info("idHakmilik" + idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        for (HakmilikPermohonan hpp : hakmilikPermohonanList) {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicara2(hpp.getId());
            if (hakmilikPerbicaraan != null) {
                logger.info("loop 2");
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara(hakmilikPerbicaraan.getIdPerbicaraan());
                if (senaraiPerbicaraanKehadiran != null) {
                    logger.info("masuk sini create org baru");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setNama(getContext().getRequest().getParameter("pbttidakpenting.nama"));
                    perbicaraanKehadiran.setNoPengenalan(getContext().getRequest().getParameter("pbttidakpenting.noPengenalan"));
                    perbicaraanKehadiran.setJawatan(getContext().getRequest().getParameter("pbttidakpenting.jawatan"));
                    perbicaraanKehadiran.setPenilaiUlasan(getContext().getRequest().getParameter("pbttidakpenting.penilaiUlasan"));
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }


        }
        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerintah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if (notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
            hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(info);
            hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
            hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
        } else {
            hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(ia);
        }
        try {
            hakmilikPerbicaraanTemp.setJenisPembangunan(hakmilikPerbicaraan.getJenisPembangunan());
        } catch (Exception hj) {
        }
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);

        if (hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }
        }



        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Resolution savePerbicaraanKehadiran() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        perbicaraanKehadiran = new PerbicaraanKehadiran();
        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/nota_siasatan_new.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getAlasanTuntutan() {
        return alasanTuntutan;
    }

    public void setAlasanTuntutan(String alasanTuntutan) {
        this.alasanTuntutan = alasanTuntutan;
    }

    public String getCaraPemilikan() {
        return caraPemilikan;
    }

    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPemohonUlasan() {
        return pemohonUlasan;
    }

    public void setPemohonUlasan(String pemohonUlasan) {
        this.pemohonUlasan = pemohonUlasan;
    }

    public String getPenilaiAmaun() {
        return penilaiAmaun;
    }

    public void setPenilaiAmaun(String penilaiAmaun) {
        this.penilaiAmaun = penilaiAmaun;
    }

    public String getPenilaiNoRujukan() {
        return penilaiNoRujukan;
    }

    public void setPenilaiNoRujukan(String penilaiNoRujukan) {
        this.penilaiNoRujukan = penilaiNoRujukan;
    }

    public String getPenilaiTarikh() {
        return penilaiTarikh;
    }

    public void setPenilaiTarikh(String penilaiTarikh) {
        this.penilaiTarikh = penilaiTarikh;
    }

    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }

    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }

    public String getTanaman() {
        return tanaman;
    }

    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }

    public BigDecimal getAmaunDituntut() {
        return amaunDituntut;
    }

    public void setAmaunDituntut(BigDecimal amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public BigDecimal getHargaPembelian() {
        return hargaPembelian;
    }

    public void setHargaPembelian(BigDecimal hargaPembelian) {
        this.hargaPembelian = hargaPembelian;
    }

    public BigDecimal getJarakKeBandar() {
        return jarakKeBandar;
    }

    public void setJarakKeBandar(BigDecimal jarakKeBandar) {
        this.jarakKeBandar = jarakKeBandar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhPemilikan() {
        return tarikhPemilikan;
    }

    public void setTarikhPemilikan(Date tarikhPemilikan) {
        this.tarikhPemilikan = tarikhPemilikan;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public List<PerbicaraanKehadiran> getSenaraiPermohonanPihakTdkBKepentingan() {
        return senaraiPermohonanPihakTdkBKepentingan;
    }

    public void setSenaraiPermohonanPihakTdkBKepentingan(List<PerbicaraanKehadiran> senaraiPermohonanPihakTdkBKepentingan) {
        this.senaraiPermohonanPihakTdkBKepentingan = senaraiPermohonanPihakTdkBKepentingan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getDiMasuk() {
        return diMasuk;
    }

    public void setDiMasuk(String diMasuk) {
        this.diMasuk = diMasuk;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public List<BigDecimal> getItemNilaianBngnList() {
        return itemNilaianBngnList;
    }

    public void setItemNilaianBngnList(List<BigDecimal> itemNilaianBngnList) {
        this.itemNilaianBngnList = itemNilaianBngnList;
    }

    public List<BigDecimal> getItemNilaianLainList() {
        return itemNilaianLainList;
    }

    public void setItemNilaianLainList(List<BigDecimal> itemNilaianLainList) {
        this.itemNilaianLainList = itemNilaianLainList;
    }

    public List<BigDecimal> getItemNilaianTanahList() {
        return itemNilaianTanahList;
    }

    public void setItemNilaianTanahList(List<BigDecimal> itemNilaianTanahList) {
        this.itemNilaianTanahList = itemNilaianTanahList;
    }

    public List<Character> getHadir() {
        return hadir;
    }

    public void setHadir(List<Character> hadir) {
        this.hadir = hadir;
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

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan2() {
        return hakmilikPerbicaraan2;
    }

    public void setHakmilikPerbicaraan2(HakmilikPerbicaraan hakmilikPerbicaraan2) {
        this.hakmilikPerbicaraan2 = hakmilikPerbicaraan2;
    }

    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }

    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }

    public PermohonanPihakTidakBerkepentingan getPptb() {
        return pptb;
    }

    public void setPptb(PermohonanPihakTidakBerkepentingan pptb) {
        this.pptb = pptb;
    }

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranPPTB() {
        return perbicaraanKehadiranPPTB;
    }

    public void setPerbicaraanKehadiranPPTB(PerbicaraanKehadiran perbicaraanKehadiranPPTB) {
        this.perbicaraanKehadiranPPTB = perbicaraanKehadiranPPTB;
    }

    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTB() {
        return senaraiPPTB;
    }

    public void setSenaraiPPTB(List<PermohonanPihakTidakBerkepentingan> senaraiPPTB) {
        this.senaraiPPTB = senaraiPPTB;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getIdPerbicaraan() {
        return idPerbicaraan;
    }

    public void setIdPerbicaraan(String idPerbicaraan) {
        this.idPerbicaraan = idPerbicaraan;
    }

    public String getCatatanKeputusan() {
        return catatanKeputusan;
    }

    public void setCatatanKeputusan(String catatanKeputusan) {
        this.catatanKeputusan = catatanKeputusan;
    }

    public String getKeteranganGKL() {
        return keteranganGKL;
    }

    public void setKeteranganGKL(String keteranganGKL) {
        this.keteranganGKL = keteranganGKL;
    }

    public List<PerbicaraanKehadiran> getSenaraiPihakPentingBerdaftar() {
        return senaraiPihakPentingBerdaftar;
    }

    public void setSenaraiPihakPentingBerdaftar(List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar) {
        this.senaraiPihakPentingBerdaftar = senaraiPihakPentingBerdaftar;
    }

    public String getKeteranganTuanpunya() {
        return keteranganTuanpunya;
    }

    public void setKeteranganTuanpunya(String keteranganTuanpunya) {
        this.keteranganTuanpunya = keteranganTuanpunya;
    }

    public String getKeteranganLain() {
        return keteranganLain;
    }

    public void setKeteranganLain(String keteranganLain) {
        this.keteranganLain = keteranganLain;
    }

    public String getAkujanjiPenilai() {
        return akujanjiPenilai;
    }

    public void setAkujanjiPenilai(String akujanjiPenilai) {
        this.akujanjiPenilai = akujanjiPenilai;
    }

    public String getPenilaiNama() {
        return penilaiNama;
    }

    public void setPenilaiNama(String penilaiNama) {
        this.penilaiNama = penilaiNama;
    }

    public String getUlasanPenilai() {
        return ulasanPenilai;
    }

    public void setUlasanPenilai(String ulasanPenilai) {
        this.ulasanPenilai = ulasanPenilai;
    }

    public String getLokasiTerkini() {
        return lokasiTerkini;
    }

    public void setLokasiTerkini(String lokasiTerkini) {
        this.lokasiTerkini = lokasiTerkini;
    }

    public String getPecahSyarat() {
        return pecahSyarat;
    }

    public void setPecahSyarat(String pecahSyarat) {
        this.pecahSyarat = pecahSyarat;
    }

    public String getUjudGPPJ() {
        return ujudGPPJ;
    }

    public void setUjudGPPJ(String ujudGPPJ) {
        this.ujudGPPJ = ujudGPPJ;
    }

    public String getKomenGPPJ() {
        return komenGPPJ;
    }

    public void setKomenGPPJ(String komenGPPJ) {
        this.komenGPPJ = komenGPPJ;
    }

    public BigDecimal getNilaianPenilai() {
        return nilaianPenilai;
    }

    public void setNilaianPenilai(BigDecimal nilaianPenilai) {
        this.nilaianPenilai = nilaianPenilai;
    }

    public BigDecimal getKeputusanNilai() {
        return keputusanNilai;
    }

    public void setKeputusanNilai(BigDecimal keputusanNilai) {
        this.keputusanNilai = keputusanNilai;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<Pihak> getSenaraiTuanTanah() {
        return senaraiTuanTanah;
    }

    public void setSenaraiTuanTanah(List<Pihak> senaraiTuanTanah) {
        this.senaraiTuanTanah = senaraiTuanTanah;
    }

    public List<Character> getHadir2() {
        return hadir2;
    }

    public void setHadir2(List<Character> hadir2) {
        this.hadir2 = hadir2;
    }

    public List<String> getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(List<String> keputusan) {
        this.keputusan = keputusan;
    }

    public List<String> getKeputusan2() {
        return keputusan2;
    }

    public void setKeputusan2(List<String> keputusan2) {
        this.keputusan2 = keputusan2;
    }

    public List<PerbicaraanKehadiran> getBantahElektrikKList() {
        return bantahElektrikKList;
    }

    public void setBantahElektrikKList(List<PerbicaraanKehadiran> bantahElektrikKList) {
        this.bantahElektrikKList = bantahElektrikKList;
    }

    public List<PerbicaraanKehadiran> getBantahElektrikSList() {
        return bantahElektrikSList;
    }

    public void setBantahElektrikSList(List<PerbicaraanKehadiran> bantahElektrikSList) {
        this.bantahElektrikSList = bantahElektrikSList;
    }

    public List<PerbicaraanKehadiran> getSenaraiPihakPentingBerdaftar1() {
        return senaraiPihakPentingBerdaftar1;
    }

    public void setSenaraiPihakPentingBerdaftar1(List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar1) {
        this.senaraiPihakPentingBerdaftar1 = senaraiPihakPentingBerdaftar1;
    }

    public Long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(Long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public List<Date> getTarikhMilik() {
        return tarikhMilik;
    }

    public void setTarikhMilik(List<Date> tarikhMilik) {
        this.tarikhMilik = tarikhMilik;
    }

    public List<String> getCaraMilik() {
        return caraMilik;
    }

    public void setCaraMilik(List<String> caraMilik) {
        this.caraMilik = caraMilik;
    }

    public List<String> getHargaMilik() {
        return hargaMilik;
    }

    public void setHargaMilik(List<String> hargaMilik) {
        this.hargaMilik = hargaMilik;
    }
}

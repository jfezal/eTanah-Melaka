/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodItemPermit;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanPermitItem;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import etanah.util.FileUtil;
import etanah.view.stripes.pelupusan.disClass.DisPBGSAPage;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.util.Collections;
import etanah.service.BPelService;
import etanah.service.CalcTaxPelupusan;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisKertasMMKController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import etanah.view.stripes.pelupusan.disClass.DisPemilikanTanah;
import java.util.Date;
import java.util.Vector;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.dao.PenempatanPesertaDAO;
import etanah.model.PenempatanPeserta;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/kertas_MMKPBGSA")
public class KertasMMKPBGSAActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    CalcTaxPelupusan calcTax;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PenempatanPesertaDAO penempatanPesertaDAO ;
    DisKertasMMKController disKertasMMKController;
    private DisSyaratSekatan disSyaratSekatan;
    private DisPermohonanBahanBatu disPermohonanBahanBatu;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private String kawasanAdun;
    private String defaultPerihalSempadan;
    private String defaultPerihalPemohon;
    private String defaultKandunganTajuk;
    private String kandunganTujuan;
    private String kandunganPerihalPermohonan;
    private String kandunganPerihalPemohon;
    private String kandunganPerihalTanah1;
    private String kandunganPerihalTanah2;
    private String kandunganPerihalTanah3;
    private String defaultKandunganJbtn;
    private String defaultKandunganAdun;
    private String kandunganTajuk; //Bil - 0
    private String idMh;
    private String index;
    private String indexSyarat;
    private String katTanahPilihan;
    private String kodGunaTanah;
    private String keteranganKadarPremium;
    private String kodHakmilik;
    private String kodU;
    private String ksn;
    private String fixStageId;
    private String keg;
    private String catatan;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean keputusanJktd = Boolean.FALSE;
    private boolean kelompok;
    private BigDecimal amnt;
    private Pengguna peng;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertas permohonanKertasJktd;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodCawangan cawangan;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonanKertasJktd;
    private Pemohon pemohon;
    private PemohonHubungan pemohonHubungan;
    private PermohonanPermitItem permohonanPermitItem;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    etanahActionBeanContext ctx;
    private PermohonanRujukanLuar mohonRujLuarJKM;
    private PermohonanRujukanLuar senaraiUlasanPengawal;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal;
    private List<PermohonanRujukanLuar> senaraiUlasanAdun;
    private List<PermohonanRujukanLuar> senaraiUlasanLain;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
//    private List<PermohonanKertasKandungan> senaraiKandunganTajuk;  //Bil - 0
    private List<PermohonanKertasKandungan> senaraiKandunganTujuan; //Bil - 1
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalPermohonan; //Bil - 2
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalTanah; //Bil - 3
    private List<PermohonanKertasKandungan> senaraiKandunganHuraianPtd; // Bil - 4
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtd; // Bil - 5
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtg; // Bil - 6
    private List<PermohonanKertasKandungan> senaraiKandunganKeputusan; // Bil - 7
    private List<PermohonanLaporanUlasan> senaraiLaporanUlas = new Vector();
    //Only for cater Kertas MMK NS
    private List<PermohonanKertasKandungan> senaraiKandunganKeputusanJktd; // Bil - 8
    private List<PermohonanKertasKandungan> senaraiKandunganHuraianPtg; // Bil - 9
    private List<PermohonanKertasKandungan> senaraiKandunganPemohon; //Bil - 10
    private List<PermohonanKertasKandungan> senaraiKandunganTemp;
    private List<HakmilikPermohonan> senaraiMohonHakmilik;
    private List<LaporanTanah> senaraiLaporanTanah;
    private List<LaporanTanahSempadan> laporTanahSempadanList;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<String> senaraikodKadarPremium;
    private List<Pemohon> listPemohon;
    private List<PemohonHubungan> listPemohonHubungan;
    private List<DisPemilikanTanah> listDisPemilikanTanah;
    private List<PermohonanKelompok> senaraiKelompok;
    private List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan;
    private List<HakmilikPermohonan> senaraiHakmilikTolak;
    private List<HakmilikPermohonan> senaraiHakmilikLulus;
    private static final Logger LOG = Logger.getLogger(KertasMMKPBGSAActionBean.class);
    private List<PenempatanPeserta> penempatanPesertaList ;

    @DefaultHandler
    public Resolution editOnlyKertasMMKPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPT();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution editOnlyKertasMMKPTJKM() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTGMLK();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution viewOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution editOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution editOnlyKertasMMKPTG() {
        keputusanJktd = Boolean.TRUE;
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution editOnlyKertasMMKPTGMLK() {
//        keputusanJktd = Boolean.TRUE;
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTGMLK();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public Resolution viewOnlyKertasMMKPTG() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        if (kodNegeri.equals("04")) {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_MLK()).addParameter("tab", "true");
        } else {
            return new JSP(DisPBGSAPage.getKMMK_MAIN_PAGE_NS()).addParameter("tab", "true");
        }
    }

    public void setDefaultKandungan(String caw) {
        String daerah = new String();
        if (caw != null) {
            daerah = disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(caw);
        }
        defaultPerihalPemohon = "Senarai nama dan latar belakang pemohon adalah seperti di Lampiran A.";
        defaultPerihalSempadan = "Tanah - tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut :";
        defaultKandunganJbtn = "Pentadbir Tanah " + daerah + " telah pun merujuk permohonan ini kepada jabatan - jabatan teknikal"
                + "<br/>dan ulasan jabatan - jabatan tersebut adalah seperti berikut :";

        LOG.info("Set Default Kandungan");
    }

    public void setObjectPermohonanKertas(Permohonan permohonan, String kodDok) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        permohonanKertas = new PermohonanKertas();
        permohonanKertas.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertas, "new", pengguna));
        permohonanKertas.setTajuk("Draf MMK");
        KodDokumen kod = kodDokumenDAO.findById(kodDok);
        permohonanKertas.setKodDokumen(kod);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setPermohonan(permohonan);
        disLaporanTanahService.getPelPtService().simpanPermohonanKertas(permohonanKertas);
        LOG.info("Saving Default Object Permohonan Kertas");
    }

    public void setObjectKertasKandunganTajuk(String kand, PermohonanKertas permohonanKertas) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(0);
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Tajuk");
    }

    public void setObjectKertasKandunganTujuan(String kand, PermohonanKertas permohonanKertas) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(1);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Tujuan");
    }

    public void setObjectKertasKandunganPerihalPermohonan(String kand, PermohonanKertas permohonanKertas) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(2);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Perihal Permohonan");
    }

    public void setObjectKertasKandunganPerihalTanah(String kand, PermohonanKertas permohonanKertas, int num) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(3);
        if (num == 1) {
            pkk.setSubtajuk("1");
        } else if (num == 2) {
            pkk.setSubtajuk("2");
        } else if (num == 3) {
            pkk.setSubtajuk("3");
        }
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Tanah");
    }

    public void setObjectKertasKandunganPerihalPemohon(String kand, PermohonanKertas permohonanKertas) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(10);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Perihal Pemohon");
    }

    public String setDefaultKandunganTajukKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganTajuk = new String();
        String namaPemohon = pmhn.getPihak() != null ? pmhn.getPihak().getNama() : new String();
        String noKp = pmhn.getNoPengenalan() != null ? pmhn.getNoPengenalan() : new String();
        BigDecimal luasTerlibat = new BigDecimal(0);
        String unitLuas = new String();
        String lot = new String();
        String bpm = new String();
        Integer tempohPajakan = new Integer(0);
        if (hp.getHakmilik() != null) {
            luasTerlibat = hp.getHakmilik().getLuas() != null ? hp.getHakmilik().getLuas() : new BigDecimal(0);
            unitLuas = hp.getHakmilik().getKodUnitLuas() != null ? hp.getHakmilik().getKodUnitLuas().getNama() : null;
            lot = hp.getHakmilik().getLot() != null ? hp.getHakmilik().getLot().getNama() + " " + hp.getHakmilik().getNoLot() : null;
            bpm = hp.getHakmilik().getBandarPekanMukim() != null ? hp.getHakmilik().getBandarPekanMukim().getNama() : null;
        } else {
            if ((p.getKodUrusan().getKod().equals("PBGSA") || p.getKodUrusan().getKod().equals("PLPS")) && p.getCawangan().getKod().equals("05")) {
                luasTerlibat = hakmilikPermohonan.getLuasDiluluskan() != null ? hakmilikPermohonan.getLuasDiluluskan() : hakmilikPermohonan.getLuasTerlibat();
                unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
                lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
                bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
                tempohPajakan = new Integer(0);
            } else {
                luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
                unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
                lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
                bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
                tempohPajakan = hp.getTempohPajakan() != null ? hp.getTempohPajakan() : new Integer(0);
            }
        }
        String permitItem = new String();
        String kb = new String(); //Keluar batu
        String pb = new String(); // Proses batu
        String mb = new String(); //Pindah batu
        String totalBatuan = new String();
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(p.getIdPermohonan());
        if (p.getKodUrusan().getKod().equals("LPSP")) {
            if (!senaraiMohonPermitItem.isEmpty()) {
                for (PermohonanPermitItem mohonPermitItem : senaraiMohonPermitItem) {
                    if (mohonPermitItem.getKodItemPermit() != null) {
                        if (mohonPermitItem.getKodItemPermit().getKod().equals("KB")) {
                            kb = "Mengeluarkan";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("PB")) {
                            pb = "Memproses";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("MB")) {
                            mb = "Memindahkan";
                        } else {
                            permitItem = mohonPermitItem.getKodItemPermit().getNama();
                        }
                    }
                }
                if (!kb.isEmpty() && !pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + pb + "/" + mb;
                } else if (!kb.isEmpty() && !pb.isEmpty()) {
                    totalBatuan = kb + "/" + pb;
                } else if (!kb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + mb;
                } else if (!pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = pb + "/" + mb;
                } else if (!kb.isEmpty()) {
                    totalBatuan = kb;
                } else if (!pb.isEmpty()) {
                    totalBatuan = pb;
                } else if (!mb.isEmpty()) {
                    totalBatuan = mb;
                }
            }
        } else {
            if (!senaraiMohonPermitItem.isEmpty()) {
                permitItem = senaraiMohonPermitItem.get(0).getKodItemPermit() != null ? senaraiMohonPermitItem.get(0).getKodItemPermit().getNama() : new String();
            }
        }
        String jenisBahanBatuan = new String();
        String jumlahIsipadu = new String();
        String jumlahIsipaduUOM = new String();
        List<PermohonanBahanBatuan> senaraiBahanBatuan = new ArrayList<PermohonanBahanBatuan>();
        senaraiBahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohonList(p.getIdPermohonan());
        if (!senaraiBahanBatuan.isEmpty()) {
            jenisBahanBatuan = senaraiBahanBatuan.get(0).getJenisBahanBatu() != null ? senaraiBahanBatuan.get(0).getJenisBahanBatu().getNama() : new String();
            jumlahIsipadu = senaraiBahanBatuan.get(0).getJumlahIsipadu() != null ? "" + senaraiBahanBatuan.get(0).getJumlahIsipadu() : new String();
            jumlahIsipaduUOM = senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom() != null ? senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom().getNama() : new String();
        }
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;
        kandunganTajuk = "PERMOHONAN DARIPADA ";

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : p.getKodUrusan().getKod().equals("PTPBP") ? 23
                : p.getKodUrusan().getKod().equals("PRMMK") ? 24
                : 0;
        switch (typeNum) {
            case 6: //Urusan LPSP

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon.toUpperCase();
                    kandunganTajuk += " UNTUK MENDAPATKAN LESEN PENDUDUKAN SEMENTARA ";
                }
                if (permitItem != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += permitItem.toUpperCase();
                }
                if (totalBatuan != null) {
                    kandunganTajuk += " DAN PERMIT ";
                    kandunganTajuk += totalBatuan.toUpperCase();
                }
                if (jenisBahanBatuan != null) {
                    kandunganTajuk += " IAITU ";
                    kandunganTajuk += jenisBahanBatuan.toUpperCase();
                }
                if (jumlahIsipadu != null && jumlahIsipaduUOM != null) {
                    kandunganTajuk += " SEBANYAK ";
                    kandunganTajuk += jumlahIsipadu;
                    kandunganTajuk += " ";
                    kandunganTajuk += jumlahIsipaduUOM.toUpperCase();
                }
                /*if (tujuan != null) {
                 kandunganTajuk += tujuan.toUpperCase();
                 }*/
                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", DAERAH ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                if (tujuan != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += tujuan.toUpperCase();
                }

                kandunganTajuk += " DI BAWAH SEKSYEN 69(1) KANUN TANAH NEGARA ";

                break;
            case 7: //Urusan PLPS

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon.toUpperCase();
                    kandunganTajuk += " UNTUK MEMILIKI TANAH KERAJAAN SECARA LESEN PENDUDUKAN SEMENTARA ";
                }
                /*if (tujuan != null) {
                 kandunganTajuk += tujuan.toUpperCase();
                 }*/
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", DAERAH ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                if (tujuan != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += tujuan.toUpperCase();
                }
                /*if (bpm != null) {
                 kandunganTajuk += ", ";
                 kandunganTajuk += bpm.toUpperCase();
                 }*/

                kandunganTajuk += " DI BAWAH SEKSYEN 65(1) KANUN TANAH NEGARA ";

                break;
            case 8: //Urusan PPRU

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon.toUpperCase();
                    kandunganTajuk += " UNTUK MENGGUNAKAN RUANG UDARA UNTUK TUJUAN ";
                }
                if (tujuan != null) {
                    kandunganTajuk += tujuan.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot.toUpperCase();
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 75A KANUN TANAH NEGARA ";

                break;
            case 9: //Urusan PPTR

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon;
                    kandunganTajuk += " UNTUK MEMAJAK SELAMA ";
                    kandunganTajuk += tempohPajakan;
                    kandunganTajuk += " TAHUN TANAH KERAJAAN UNTUK TUJUAN ";
                }
                if (tujuan != null) {
                    kandunganTajuk += tujuan.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm;
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah;
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 63 KANUN TANAH NEGARA ";

                break;
            case 10: //Urusan PTGSA

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon.toUpperCase();
                    kandunganTajuk += " UNTUK PENAMATAN TANAH BERKELOMPOK (GSA) UNTUK TUJUAN ";
                }
                if (tujuan != null) {
                    kandunganTajuk += tujuan.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ";
                    kandunganTajuk += lot.toUpperCase();
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 44 AKTA 1960 KANUN TANAH NEGARA ";

                break;
            case 11: //Urusan PRMP

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon;
                    kandunganTajuk += " UNTUK MEMILIKI PERMIT TANAH PERTANIAN ";
                }
                if (permitItem != null) {
                    kandunganTajuk += "UNTUK TUJUAN ";
                    kandunganTajuk += permitItem.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }

                break;
            case 12: //Urusan PBMT

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon;
                    kandunganTajuk += " UNTUK MENDAPATKAN PEMBERIMILIKAN TANAH KERAJAAN BAGI TUJUAN ";
                }
                if (tujuan != null) {
                    kandunganTajuk += tujuan.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ATAS LOT ";
                    kandunganTajuk += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA ";

                break;
            case 23: //Urusan PTPBP

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon;
                    kandunganTajuk += " UNTUK MEMILIKI PERMIT KHAS TANAH PERTANIAN UNTUK TUJUAN BUKAN PERTANIAN";
                }
                if (permitItem != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += permitItem.toUpperCase();
                }
                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS ";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }

                break;
            case 24: //Urusan PRMMK

                if (namaPemohon != null) {
                    kandunganTajuk += namaPemohon;
                    kandunganTajuk += " UNTUK MERIZABKAN TANAH KERAJAAN DIBAWAH SEKSYEN 62 KANUN TANAH NEGARA 1965";
                }

//                if (lot != null) {
//                    kandunganTajuk += " DI ATAS ";
//                    kandunganTajuk += lot;
//                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTajuk += " SELUAS LEBIH KURANG";
                    kandunganTajuk += luasTerlibat;
                    kandunganTajuk += " ";
                    kandunganTajuk += unitLuas.toUpperCase();
                }

                if (bpm != null) {
                    kandunganTajuk += "DI ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }

                if (p.getSebab() != null) {
                    kandunganTajuk += "UNTUK TUJUAN ";
                    kandunganTajuk += p.getSebab().toUpperCase();
                }

                break;
            default:
                break;
        }

        return kandunganTajuk;
    }

    public String setDefaultKandunganTujuanKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganTujuan = new String();
        String namaPemohon = pmhn.getPihak() != null ? pmhn.getPihak().getNama() : new String();
        BigDecimal luasTerlibat = new BigDecimal(0);
        String unitLuas = new String();
        String lot = new String();
        String bpm = new String();
        if (hp.getHakmilik() != null) {
            luasTerlibat = hp.getHakmilik().getLuas() != null ? hp.getHakmilik().getLuas() : new BigDecimal(0);
            unitLuas = hp.getHakmilik().getKodUnitLuas() != null ? hp.getHakmilik().getKodUnitLuas().getNama() : null;
            lot = hp.getHakmilik().getLot() != null ? hp.getHakmilik().getLot().getNama() + " " + hp.getHakmilik().getNoLot() : null;
            bpm = hp.getHakmilik().getBandarPekanMukim() != null ? hp.getHakmilik().getBandarPekanMukim().getNama() : null;
        } else {
            luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
            unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
            lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
            bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
        }
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;
        String permitItem = new String();
        String kb = new String(); //Keluar batu
        String pb = new String(); // Proses batu
        String mb = new String(); //Pindah batu
        String totalBatuan = new String();
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(p.getIdPermohonan());
        if (p.getKodUrusan().getKod().equals("LPSP")) {
            if (!senaraiMohonPermitItem.isEmpty()) {
                for (PermohonanPermitItem mohonPermitItem : senaraiMohonPermitItem) {
                    if (mohonPermitItem.getKodItemPermit() != null) {
                        if (mohonPermitItem.getKodItemPermit().getKod().equals("KB")) {
                            kb = "Mengeluarkan";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("PB")) {
                            pb = "Memproses";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("MB")) {
                            mb = "Memindahkan";
                        } else {
                            permitItem = mohonPermitItem.getKodItemPermit().getNama();
                        }
                    }
                }
                if (!kb.isEmpty() && !pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + pb + "/" + mb;
                } else if (!kb.isEmpty() && !pb.isEmpty()) {
                    totalBatuan = kb + "/" + pb;
                } else if (!kb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + mb;
                } else if (!pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = pb + "/" + mb;
                } else if (!kb.isEmpty()) {
                    totalBatuan = kb;
                } else if (!pb.isEmpty()) {
                    totalBatuan = pb;
                } else if (!mb.isEmpty()) {
                    totalBatuan = mb;
                }
            }
        } else {
            if (!senaraiMohonPermitItem.isEmpty()) {
                permitItem = senaraiMohonPermitItem.get(0).getKodItemPermit() != null ? senaraiMohonPermitItem.get(0).getKodItemPermit().getNama() : new String();
            }
        }
        String jenisBahanBatuan = new String();
        String jumlahIsipadu = new String();
        String jumlahIsipaduUOM = new String();
        List<PermohonanBahanBatuan> senaraiBahanBatuan = new ArrayList<PermohonanBahanBatuan>();
        senaraiBahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohonList(p.getIdPermohonan());
        if (!senaraiBahanBatuan.isEmpty()) {
            jenisBahanBatuan = senaraiBahanBatuan.get(0).getJenisBahanBatu() != null ? senaraiBahanBatuan.get(0).getJenisBahanBatu().getNama() : new String();
            jumlahIsipadu = senaraiBahanBatuan.get(0).getJumlahIsipadu() != null ? "" + senaraiBahanBatuan.get(0).getJumlahIsipadu() : new String();
            jumlahIsipaduUOM = senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom() != null ? senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom().getNama() : new String();
        }
        kandunganTujuan = "Tujuan rencana ini adalah untuk mendapatkan Pertimbangan Majlis Mesyuarat Kerajaan Negeri ";
        if (kodNegeri.equals("04")) {
            kandunganTujuan += "Melaka";
        } else {
            kandunganTujuan += "Sembilan";
        }
        kandunganTujuan += " mengenai permohonan ";

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : p.getKodUrusan().getKod().equals("PTPBP") ? 23
                : p.getKodUrusan().getKod().equals("PRMMK") ? 24
                : 0;
        switch (typeNum) {
            case 6: //Urusan LPSP
                kandunganTujuan += "untuk mendapatkan Lesen Pendudukan Sementara ";
                if (permitItem != null) {
                    kandunganTujuan += "untuk tujuan ";
                    kandunganTujuan += permitItem;
                }
                if (totalBatuan != null) {
                    kandunganTujuan += " dan permit ";
                    kandunganTujuan += totalBatuan.toLowerCase();
                }
                if (jenisBahanBatuan != null) {
                    kandunganTujuan += " iaitu ";
                    kandunganTujuan += jenisBahanBatuan.toLowerCase();
                }
                if (jumlahIsipadu != null && jumlahIsipaduUOM != null) {
                    kandunganTujuan += " sebanyak ";
                    kandunganTujuan += jumlahIsipadu;
                    kandunganTujuan += " ";
                    kandunganTujuan += jumlahIsipaduUOM.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                if (tujuan != null) {
                    kandunganTujuan += " untuk tujuan ";
                    kandunganTujuan += tujuan.toLowerCase();
                }
                kandunganTujuan += " di bawah Seksyen 69 Kanun Tanah Negara.";

                break;
            case 7: //Urusan PLPS
                kandunganTujuan += "untuk memiliki tanah kerajaan secara Lesen Pendudukan Sementara untuk tujuan ";
                if (tujuan != null) {
                    kandunganTujuan += tujuan.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                kandunganTujuan += " di bawah Seksyen 65(1) Kanun Tanah Negara.";

                break;
            case 8: //Urusan PPRU
                kandunganTujuan += "untuk menggunakan ruang udara untuk tujuan ";
                if (tujuan != null) {
                    kandunganTujuan += tujuan.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                kandunganTujuan += " di bawah Seksyen 75A Kanun Tanah Negara.";

                break;
            case 9: //Urusan PPTR
                kandunganTujuan += "memajak tanah rizab bagi tujuan ";
                if (tujuan != null) {
                    kandunganTujuan += tujuan.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                kandunganTujuan += " di bawah Seksyen 63 Kanun Tanah Negara.";

                break;
            case 10: //Urusan PTGSA
                kandunganTujuan += "untuk penamatan tanah berkelompok (GSA) untuk tujuan ";
                if (tujuan != null) {
                    kandunganTujuan += tujuan.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                kandunganTujuan += " di bawah Seksyen 44 Akta 1960 Kanun Tanah Negara.";

                break;
            case 11: //Urusan PRMP
                kandunganTujuan += "memiliki permit tanah pertanian untuk tujuan ";
                if (permitItem != null) {
                    kandunganTujuan += permitItem.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }

                break;
            case 12: //Urusan PBMT
                kandunganTujuan += "pemberimilikan tanah kerajaan bagi tujuan ";
                if (tujuan != null) {
                    kandunganTujuan += tujuan.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas lot ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                kandunganTujuan += " di bawah Seksyen 76 Kanun Tanah Negara.";

                break;
            case 23: //Urusan PTPBP
                kandunganTujuan += "memiliki permit khas tanah pertanian untuk tujuan bukan pertanian untuk tujuan ";
                if (permitItem != null) {
                    kandunganTujuan += permitItem.toLowerCase();
                }

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }
                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }

                break;
            case 24: //Urusan PRMMK

                kandunganTujuan += "untuk merizabkan tanah kerajaan di bawah seksyen 62 kanun tanah negara 1965 ";

                if (lot != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lot;
                }

                if (luasTerlibat != null && unitLuas != null) {
                    kandunganTujuan += " seluas ";
                    kandunganTujuan += luasTerlibat;
                    kandunganTujuan += " ";
                    kandunganTujuan += unitLuas.toLowerCase();
                }

                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }

                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }

                break;
            default:
                break;
        }

        return kandunganTujuan;
    }

    public String setDefaultKandunganPerihalPermohonanKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganPermohonan = new String();
        String namaPemohon = pmhn.getPihak() != null ? pmhn.getPihak().getNama() : new String();
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String permitItem = new String();
        String kb = new String(); //Keluar batu
        String pb = new String(); // Proses batu
        String mb = new String(); //Pindah batu
        String totalBatuan = new String();
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(p.getIdPermohonan());
        if (p.getKodUrusan().getKod().equals("LPSP")) {
            if (!senaraiMohonPermitItem.isEmpty()) {
                for (PermohonanPermitItem mohonPermitItem : senaraiMohonPermitItem) {
                    if (mohonPermitItem.getKodItemPermit() != null) {
                        if (mohonPermitItem.getKodItemPermit().getKod().equals("KB")) {
                            kb = "Mengeluarkan";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("PB")) {
                            pb = "Memproses";
                        } else if (mohonPermitItem.getKodItemPermit().getKod().equals("MB")) {
                            mb = "Memindahkan";
                        } else {
                            permitItem = mohonPermitItem.getKodItemPermit().getNama();
                        }
                    }
                }
                if (!kb.isEmpty() && !pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + pb + "/" + mb;
                } else if (!kb.isEmpty() && !pb.isEmpty()) {
                    totalBatuan = kb + "/" + pb;
                } else if (!kb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = kb + "/" + mb;
                } else if (!pb.isEmpty() && !mb.isEmpty()) {
                    totalBatuan = pb + "/" + mb;
                } else if (!kb.isEmpty()) {
                    totalBatuan = kb;
                } else if (!pb.isEmpty()) {
                    totalBatuan = pb;
                } else if (!mb.isEmpty()) {
                    totalBatuan = mb;
                }
            }
        } else {
            if (!senaraiMohonPermitItem.isEmpty()) {
                permitItem = senaraiMohonPermitItem.get(0).getKodItemPermit() != null ? senaraiMohonPermitItem.get(0).getKodItemPermit().getNama() : new String();
            }
        }
        String jenisBahanBatuan = new String();
        String jumlahIsipadu = new String();
        String jumlahIsipaduUOM = new String();
        List<PermohonanBahanBatuan> senaraiBahanBatuan = new ArrayList<PermohonanBahanBatuan>();
        senaraiBahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohonList(p.getIdPermohonan());
        if (!senaraiBahanBatuan.isEmpty()) {
            jenisBahanBatuan = senaraiBahanBatuan.get(0).getJenisBahanBatu() != null ? senaraiBahanBatuan.get(0).getJenisBahanBatu().getNama() : new String();
            jumlahIsipadu = senaraiBahanBatuan.get(0).getJumlahIsipadu() != null ? "" + senaraiBahanBatuan.get(0).getJumlahIsipadu() : new String();
            jumlahIsipaduUOM = senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom() != null ? senaraiBahanBatuan.get(0).getJumlahIsipaduDipohonUom().getNama() : new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = p.getInfoAudit() != null ? p.getInfoAudit().getTarikhMasuk() : null;

        kandunganPermohonan = "Pentadbir Tanah Daerah ";
        if (daerah != null) {
            kandunganPermohonan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
        }

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : p.getKodUrusan().getKod().equals("PTPBP") ? 23
                : p.getKodUrusan().getKod().equals("PRMMK") ? 24
                : 0;
        switch (typeNum) {
            case 6: //Urusan LPSP
                kandunganPermohonan += " telah menerima permohonan untuk mendapatkan Lesen Pendudukan Sementara dan permit ";
                if (totalBatuan != null) {
                    kandunganPermohonan += totalBatuan.toLowerCase();
                }
                if (jenisBahanBatuan != null) {
                    kandunganPermohonan += " iaitu ";
                    kandunganPermohonan += jenisBahanBatuan.toLowerCase();
                }
                kandunganPermohonan += " daripada ";
                break;
            case 7: //Urusan PLPS
                kandunganPermohonan += " telah menerima permohonan untuk memiliki tanah kerajaan secara Lesen Pendudukan Sementara daripada ";

                break;
            case 8: //Urusan PPRU
                kandunganPermohonan += " telah menerima permohonan untuk menggunakan ruang udara daripada ";

                break;
            case 9: //Urusan PPTR
                kandunganPermohonan += " telah menerima permohonan untuk memajak tanah rizab daripada ";

                break;
            case 10: //Urusan PTGSA
                kandunganPermohonan += " telah menerima permohonan untuk penamatan tanah di bawah GSA daripada ";

                break;
            case 11: //Urusan PRMP
                kandunganPermohonan += " telah menerima permohonan untuk memiliki permit tanah pertanian daripada ";

                break;
            case 12: //Urusan PBMT
                kandunganPermohonan += " telah menerima permohonan untuk mendapatkan ";
                kandunganPermohonan += "pemberimilikan tanah kerajaan daripada ";

                break;
            case 23: //Urusan PTPBP
                kandunganPermohonan += " telah menerima permohonan untuk memiliki permit khas tanah pertanian untuk tujuan bukan pertanian daripada ";

                break;
            case 24: //Urusan PRMMK
                kandunganPermohonan += " telah menerima permohonan untuk merizabkan tanah kerajaan daripada ";

                break;
            default:
                break;
        }
        if (namaPemohon != null) {
            kandunganPermohonan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(namaPemohon);
        }
        if (date != null) {
            String tarikhMasuk = sdf.format(date);
            kandunganPermohonan += " pada ";
            kandunganPermohonan += tarikhMasuk;
        }
        return kandunganPermohonan;
    }

    public String setDefaultKandunganPerihalPemohonKertas(Permohonan p, String caw, Pemohon pmhn, String kodNegeri) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganPemohon = new String();
        String namaPemohon = pmhn.getPihak() != null ? pmhn.getPihak().getNama() : new String();
        String noPengenalan = pmhn.getPihak() != null ? pmhn.getPihak().getNoPengenalan() : new String();
        String alamat = new String();
        if (pmhn.getPihak() != null) {
            if (StringUtils.isNotBlank(pmhn.getPihak().getAlamat1())) {
                alamat += pmhn.getPihak().getAlamat1();
            }
            if (StringUtils.isNotBlank(pmhn.getPihak().getAlamat2())) {
                alamat += ", ";
                alamat += pmhn.getPihak().getAlamat2();
            }
            if (StringUtils.isNotBlank(pmhn.getPihak().getAlamat3())) {
                alamat += ", ";
                alamat += pmhn.getPihak().getAlamat3();
            }
            if (StringUtils.isNotBlank(pmhn.getPihak().getAlamat4())) {
                alamat += ", ";
                alamat += pmhn.getPihak().getAlamat4();
            }
        }

        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = p.getInfoAudit() != null ? p.getInfoAudit().getTarikhMasuk() : null;

        kandunganPemohon = "Pemohon ialah ";
        if (namaPemohon != null) {
            kandunganPemohon += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(namaPemohon);
        }
        if (noPengenalan != null) {
            kandunganPemohon += ", No. Pengenalan : ";
            kandunganPemohon += noPengenalan;
        }
        if (alamat != null) {
            kandunganPemohon += " beralamat di ";
            kandunganPemohon += alamat;
        }
        return kandunganPemohon;
    }

    public String setDefaultKandunganTajukKertasKelompok(Permohonan p, String caw, HakmilikPermohonan hp, String kodNegeri, PermohonanKelompok pk, int size) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganTajuk = new String();
        BigDecimal luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
        String unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
        String lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
        String bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;
        String lokasi = hp.getLokasi() != null ? hp.getLokasi() : null;
        String katTanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getNama() : null;
        String gunaTanah = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getNama() : null;
        String namaPemohon = new String();
        String noIc = new String();
        if (pk.getJenisKelopok().equals("K")) {
            kandunganTajuk = "PERMOHONAN BERKELOMPOK ";
        } else {
            kandunganTajuk = "PERMOHONAN BERTINDIH ";
            if (pk.getPermohonan() != null) {
                if (pk.getPermohonan().getSenaraiPemohon() != null) {
                    if (pk.getPermohonan().getSenaraiPemohon().get(0).getPihak() != null) {
                        namaPemohon = pk.getPermohonan().getSenaraiPemohon().get(0).getPihak().getNama().toUpperCase();
                        noIc = pk.getPermohonan().getSenaraiPemohon().get(0).getPihak().getNoPengenalan().toUpperCase();
                    }
                }
            }
            if (StringUtils.isNotBlank(namaPemohon) && StringUtils.isNotBlank(noIc)) {
                kandunganTajuk += "DARIPADA ";
                kandunganTajuk += namaPemohon;
                kandunganTajuk += " NO PENGENALAN ";
                kandunganTajuk += noIc;
            }
        }

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : 0;
        switch (typeNum) {
            case 12: //Urusan PBMT

                if (pk.getJenisKelopok().equals("K")) {
                    kandunganTajuk += "UNTUK MEMILIKI TANAH KERAJAAN SEBANYAK ";
                    kandunganTajuk += lot;
//                    kandunganTajuk += " PLOT ";
                } else {
                    kandunganTajuk += " DAN ";
                    kandunganTajuk += size - 1;
                    kandunganTajuk += " YANG LAIN UNTUK MEMILIKI TANAH KERAJAAN ";
                }

                if (lokasi != null) {
                    kandunganTajuk += "DI ATAS ";
                    kandunganTajuk += lokasi.toUpperCase();

                }
                if (pk.getJenisKelopok().equals("T")) {
                    if (lot != null) {
                        kandunganTajuk += " ";
                        kandunganTajuk += lot;
                    }
                    if (luasTerlibat != null && unitLuas != null) {
                        kandunganTajuk += " SELUAS ";
                        kandunganTajuk += luasTerlibat;
                        kandunganTajuk += " ";
                        kandunganTajuk += unitLuas.toUpperCase();
                    }
                }
                if (bpm != null) {
                    kandunganTajuk += "DI ";
                    kandunganTajuk += bpm;
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah;
                }
                if (katTanah != null && gunaTanah != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += katTanah.toUpperCase();
                    kandunganTajuk += "(";
                    kandunganTajuk += gunaTanah.toUpperCase();
                    kandunganTajuk += ")";
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA ";

                break;
            default:
                break;
        }

        return kandunganTajuk;
    }

    public String setDefaultKandunganTujuanKertasKelompok(Permohonan p, String caw, HakmilikPermohonan hp, String kodNegeri, PermohonanKelompok pk, int size) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganTujuan = new String();
        BigDecimal luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
        String unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
        String bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;
        String lokasi = hp.getLokasi() != null ? hp.getLokasi() : null;
        String lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
        String katTanah = hp.getKategoriTanahBaru() != null ? hp.getKategoriTanahBaru().getNama() : null;
        String gunaTanah = hp.getKodKegunaanTanah() != null ? hp.getKodKegunaanTanah().getNama() : null;
        kandunganTujuan = "Tujuan rencana ini adalah untuk mendapatkan Pertimbangan Majlis Mesyuarat Kerajaan Negeri ";
        if (kodNegeri.equals("04")) {
            kandunganTujuan += "Melaka";
        } else {
            kandunganTujuan += "Sembilan";
        }
        kandunganTujuan += " mengenai permohonan ";

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : 0;
        switch (typeNum) {
            case 12: //Urusan PBMT
                kandunganTujuan += "pemberimilikan tanah kerajaan sebanyak ";
                if (pk.getJenisKelopok().equals("K")) {
                    kandunganTujuan += lot;
                } else {
                    kandunganTujuan += size;
                    kandunganTujuan += " permohonan";
                }

                if (lokasi != null) {
                    kandunganTujuan += " di atas ";
                    kandunganTujuan += lokasi;
                }
                if (pk.getJenisKelopok().equals("T")) {
                    if (lot != null) {
                        kandunganTujuan += " ";
                        kandunganTujuan += lot;
                    }
                    if (luasTerlibat != null && unitLuas != null) {
                        kandunganTujuan += " seluas ";
                        kandunganTujuan += luasTerlibat;
                        kandunganTujuan += " ";
                        kandunganTujuan += unitLuas.toLowerCase();
                    }
                }
                if (bpm != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(bpm);
                }
                if (daerah != null) {
                    kandunganTujuan += ", ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
                }
                if (katTanah != null && gunaTanah != null) {
                    kandunganTujuan += " untuk tujuan ";
                    kandunganTujuan += katTanah;
                    kandunganTujuan += "(";
                    kandunganTujuan += gunaTanah;
                    kandunganTujuan += ")";
                }

                break;
            default:
                break;
        }

        return kandunganTujuan;
    }

    public String setDefaultKandunganPerihalPermohonanKertasKelompok(Permohonan p, String caw, HakmilikPermohonan hp, String kodNegeri, PermohonanKelompok pk, int size) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganPermohonan = new String();
        BigDecimal luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
        String unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
        String bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;
        String lokasi = hp.getLokasi() != null ? hp.getLokasi() : null;
        String lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = p.getInfoAudit() != null ? p.getInfoAudit().getTarikhMasuk() : null;

        kandunganPermohonan = "Pentadbir Tanah Daerah ";
        if (daerah != null) {
            kandunganPermohonan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
        }
        kandunganPermohonan += " telah menerima permohonan ";
        if (pk.getJenisKelopok().equals("K")) {
            kandunganPermohonan += "berkelompok ";
        } else {
            kandunganPermohonan += "bertindih ";
        }

        kandunganPermohonan += "sebanyak ";
        if (pk.getJenisKelopok().equals("K")) {
            kandunganPermohonan += lot;
        } else {
            kandunganPermohonan += size;
            kandunganPermohonan += " permohonan";
        }

        if (date != null) {
            String tarikhMasuk = sdf.format(date);
            kandunganPermohonan += " pada ";
            kandunganPermohonan += tarikhMasuk;
        }
        return kandunganPermohonan;
    }

    public String setDefaultKandunganPerihalTanah(HakmilikPermohonan hp, LaporanTanah laporanTanah, int num) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganPerihalTanah = new String();
        if (num == 1) {
            if (laporanTanah.getKecerunanTanah() != null) {
                kandunganPerihalTanah += "Kecerunan tanah adalah ";
                kandunganPerihalTanah += laporanTanah.getKecerunanTanah().getNama().toLowerCase();
            }
        } else if (num == 2) {
            if (laporanTanah.getKodKeadaanTanah() != null) {
                kandunganPerihalTanah += "Keadaan tanah adalah ";
                kandunganPerihalTanah += laporanTanah.getKodKeadaanTanah().getNama().toLowerCase();
            }
        } else if (num == 3) {
            if (hp.getHakmilik() != null) {
                if (hp.getHakmilik().getLokasi() != null) {
                    kandunganPerihalTanah += "Tanah ini terletak di ";
                    kandunganPerihalTanah += hp.getHakmilik().getLokasi();
                }
            } else {
                if (hp.getLokasi() != null) {
                    kandunganPerihalTanah += "Tanah ini terletak di ";
                    kandunganPerihalTanah += hp.getLokasi();
                }
            }
        }
        return kandunganPerihalTanah;
    }

    public void setObjectKertasKandunganKeputusanJktd(FasaPermohonan fasaPermohonan, PermohonanKertas permohonanKertas, PermohonanKertas permohonanKertasJktd) {
        String kand = "Mesyuarat Jawantankuasa Tanah Daerah pada ";
        if (permohonanKertasJktd.getTarikhSidang() != null && fasaPermohonan.getKeputusan() != null) {
            kand += sdf.format(permohonanKertasJktd.getTarikhSidang());
            kand += " telah mengesyorkan supaya permohonan ini ";
            if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                kand += "diluluskan";
            } else {
                kand += "ditolak";
            }
            kand += " oleh Majlis Mesyuarat Kerajaan.";
        }

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(8);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Tajuk");
    }

    public void setObjectKertasKandunganKeputusanPTG(PermohonanKertas permohonanKertas) {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kand = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri ";
        if (kodNegeri.equals("04")) {
            kand += "Melaka.";
        } else {
            kand += "Sembilan Darul Khusus.";
        }
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
        pkk.setBil(7);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Tajuk");
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "15TrmLapSdknDrafMMK";
        }
        return stageId;
    }

    public Resolution searchSyarat() {
        indexSyarat = getContext().getRequest().getParameter("index");
        idMh = getContext().getRequest().getParameter("idMh");
        String jenisSyarat = getContext().getRequest().getParameter("jenisSyarat");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String forwardJSP = new String();
        disSyaratSekatan = new DisSyaratSekatan();
        if (!StringUtils.isEmpty(jenisSyarat)) {
            if (jenisSyarat.equalsIgnoreCase("nyata")) {
                disSyaratSekatan.setKodSyaratNyata(new String());
                disSyaratSekatan.setSyaratNyata2(new String());
                String kodSekatan = getContext().getRequest().getParameter("kodSktn");
                if (!StringUtils.isEmpty(kodSekatan)) {
                    KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                    kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{kodSekatan}, 0);
                    if (kodSK != null) {
                        disSyaratSekatan.setKodSktn(kodSK.getKod());
                        disSyaratSekatan.setSyaratSekatan(kodSK.getSekatan());
                    }
                }
                disSyaratSekatan.setListKodSyaratNyata(new ArrayList<KodSyaratNyata>());
                forwardJSP = DisPBGSAPage.getKMMK_SYARATNYATA_PAGE();
            } else if (jenisSyarat.equalsIgnoreCase("sekatan")) {
                disSyaratSekatan.setKodSekatan(new String());
                disSyaratSekatan.setSyaratSekatan(new String());
                String kodNyata = getContext().getRequest().getParameter("kodNyata");
                if (!StringUtils.isEmpty(kodNyata)) {
                    KodSyaratNyata kodSN = new KodSyaratNyata();
                    kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{kodNyata}, 0);
                    if (kodSN != null) {
                        disSyaratSekatan.setKod(kodSN.getKod());
                        disSyaratSekatan.setSyaratNyata(kodSN.getSyarat());
                    }
                }
                disSyaratSekatan.setListKodSekatan(new ArrayList<KodSekatanKepentingan>());
                forwardJSP = DisPBGSAPage.getKMMK_SEKATAN_PAGE();
            }
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        idMh = getContext().getRequest().getParameter("idMh");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idMh}, 2);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("LPSP")) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSyaratNyata())) {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata(disSyaratSekatan.getKodSyaratNyata(), kc, disSyaratSekatan.getSyaratNyata2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSyaratNyata().size());
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata("%", kc, disSyaratSekatan.getSyaratNyata2()));
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyata("%", kc, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(DisPBGSAPage.getKMMK_SYARATNYATA_PAGE()).addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        idMh = getContext().getRequest().getParameter("idMh");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idMh}, 2);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), kc, disSyaratSekatan.getSekatKpntgn2()));
                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }

        return new JSP(DisPBGSAPage.getKMMK_SEKATAN_PAGE()).addParameter("popup", "true");
    }

    public Resolution kiraCukai() {
        String result = "";
        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMh = getContext().getRequest().getParameter("idMh");
        HakmilikPermohonan hp = null;
        if (!StringUtils.isEmpty(idMh)) {
            hp = new HakmilikPermohonan();
            hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMh));
        } else {
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                if (senaraiHakmilikPermohonan.size() == 1) {
                    hp = senaraiHakmilikPermohonan.get(0);
                } else {
                    //Not yet implemented
                }
            }
        }

        if (hp != null) {

            if (hp.getLuasDiluluskan().longValue() < 0) {
                result = "-1";
            } else {
                result = String.valueOf(calcTax.calculate(kodTanah, String.valueOf(hp.getBandarPekanMukimBaru().getKod()), "H", new BigDecimal(1), hp, null));
            }

        }
        return new StreamingResolution("text/plain", result);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanKandungan", "!deleteRow"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        stageId = stageId(taskId, peng);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        if (idPermohonan != null) {
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            if (senaraiKelompok.size() > 0) {
                kelompok = true;
            } else {
                kelompok = false;
            }
        }

        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("MLCRG")) {
            mohonRujLuarJKM = pelupusanService.findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan);
        }

        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan != null) {
                setDefaultKandungan(permohonan.getCawangan().getDaerah().getNama());
            }
            if (!kelompok) {
                listPemohon = permohonan.getSenaraiPemohon();
                for (Pemohon p : listPemohon) {
                    pemohon = p;

                    //lebih dari 1
                    listPemohonHubungan = pelupusanService.findHubunganByIDPemohon(p.getIdPemohon());

                    //utk 1 data
                    for (PemohonHubungan ph : listPemohonHubungan) {
                        pemohonHubungan = ph;

                    }
                }

                listDisPemilikanTanah = new ArrayList<DisPemilikanTanah>();
//            listPemohonHubungan = new ArrayList();
                PemohonHubungan pemohonHubungan = new PemohonHubungan();

                listPemohon = permohonan.getSenaraiPemohon();
                LOG.info("listPemohon.size():" + listPemohon.size());

                for (Pemohon p : listPemohon) {
                    pemohon = p;
                    listPemohonHubungan = pelupusanService.findHubunganByIDPemohon(p.getIdPemohon());

                    for (PemohonHubungan ph : listPemohonHubungan) {
                        pemohonHubungan = ph;

                    }
                }

                //LOG.info("listPemohon.size():" + listPemohon.size());
                //LOG.info("listPemohonHubungan.size():" + listPemohonHubungan.size());

                for (Pemohon p : listPemohon) {
                    List<HakmilikPihakBerkepentingan> listHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
                    listHakmilikPihak = disLaporanTanahService.getPelupusanService().findHakmilikPihak(p.getPihak().getIdPihak());

                    for (HakmilikPihakBerkepentingan hpb : listHakmilikPihak) {
                        if (hpb.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
                            DisPemilikanTanah dpt = new DisPemilikanTanah();
                            dpt.setHakmilik(hpb.getHakmilik());
                            dpt.setPemohon(p);
                            listDisPemilikanTanah.add(dpt);

                        }
                    }
                }
                
                if(listPemohon.size()>0)
                {
                    for (PemohonHubungan ph : listPemohonHubungan) {
                        DisPemilikanTanah dpt = new DisPemilikanTanah();
                        dpt.setPemohonHubungan(ph);
                        dpt.setPemohon(ph.getPemohon());
                        dpt.setHakmilik(ph.getPemohon().getHakmilik());
                        listDisPemilikanTanah.add(dpt);

                    }

                    LOG.info("listDisPemilikanTanah.size():" + listPemohon.size());
                    LOG.info("listDisPemilikanTanah.size():" + listPemohonHubungan.size());
                }
            }
            /**
             * Ulasan JTek
             */
            senaraiUlasanJabatanTeknikal = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK", kodNegeri);
            if (senaraiUlasanJabatanTeknikal.size() > 0) {
                for (int i = 0; i < senaraiUlasanJabatanTeknikal.size(); i++) {
                    PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
                    mohonRujuk = senaraiUlasanJabatanTeknikal.get(i);
                    if (mohonRujuk.getUlasan() == null) {
                        mohonRujuk.setUlasan("Tiada Ulasan Diterima");
                    }
                    disLaporanTanahService.getPelupusanService().simpanPermohonanRujLuar(mohonRujuk);
                }
            }
            /*
             * Ulasan Adun
             */
            if (kodNegeri.equals("04")) {
                senaraiUlasanAdun = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN", kodNegeri);

                if (senaraiUlasanAdun.size() > 0) {
                    for (int i = 0; i < senaraiUlasanAdun.size(); i++) {
                        PermohonanRujukanLuar mohonRujuk = new PermohonanRujukanLuar();
                        mohonRujuk = senaraiUlasanAdun.get(i);
//                            mohonRujuk.setUlasan(ulasanAgensi);
                        if (mohonRujuk.getUlasan() == null) {
                            mohonRujuk.setUlasan("Tiada Ulasan Diterima");
                        }

                        if (mohonRujuk.getAgensi() != null) {
                            kawasanAdun = disLaporanTanahService.getPelupusanService().findKodDUNByAgensi(mohonRujuk.getAgensi().getKod()).getNama();
                        }
                        disLaporanTanahService.getPelupusanService().simpanPermohonanRujLuar(mohonRujuk);
                    }
                    String caw = permohonan.getCawangan().getDaerah() != null ? permohonan.getCawangan().getDaerah().getNama() : new String();

                    defaultKandunganAdun = "Pentadbir Tanah " + disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(caw) + " telah pun merujuk permohonan ini kepada ADUN "
                            + "<br/>kawasan " + disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(kawasanAdun) + " dan ulasan adalah seperti berikut :";
                }
                if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    senaraiUlasanPengawal = disLaporanTanahService.getPelupusanService().findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan);
                }
            }

            /**
             * Hakmilik or Not Have Hakmilik
             */
            if (idPermohonan != null) {

                permohonan = new Permohonan();
                permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
                penempatanPesertaList = pelupusanService.findPenempatanPesertaByIdMohon(idPermohonan);
                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//              senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                } else {
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                }

                if (senaraiHakmilikPermohonan.size() > 0) {
                    if (senaraiHakmilikPermohonan.size() == 1) {
                        hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
                    } else {
                        hakmilikPermohonan = null;
                        //Not yet implemented
                    }
                } else {
                    hakmilikPermohonan = null;
                }
                if (permohonan.getKodUrusan().getKod().equals("PBGSA") && permohonan.getCawangan().getKod().equals("05")) {
                    hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                }
                if (permohonan.getKodUrusan().getKod().equals("PBMMK") && permohonan.getCawangan().getKod().equals("05")) {
                    hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                }
                if (senaraiHakmilikPermohonan.size() > 0) {
                    senaraiHakmilikLulus = new ArrayList<HakmilikPermohonan>();
                    senaraiHakmilikTolak = new ArrayList<HakmilikPermohonan>();
                    if (kelompok) {
                        if (senaraiKelompok.size() > 0) {
                            for (PermohonanKelompok pk : senaraiKelompok) {
                                List<HakmilikPermohonan> senaraiHakmilikPermohonanTempLulus = new ArrayList<HakmilikPermohonan>();
                                List<HakmilikPermohonan> senaraiHakmilikPermohonanTempTolak = new ArrayList<HakmilikPermohonan>();
                                senaraiHakmilikPermohonanTempLulus = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByLulus(pk.getPermohonan().getIdPermohonan());
                                senaraiHakmilikPermohonanTempTolak = disLaporanTanahService.getPelupusanService().getHakmilikPermohonanListByTolak(pk.getPermohonan().getIdPermohonan());
                                if (senaraiHakmilikPermohonanTempLulus.size() > 0) {
                                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempLulus) {
                                        senaraiHakmilikLulus.add(hp);
                                    }
                                }
                                if (senaraiHakmilikPermohonanTempTolak.size() > 0) {
                                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonanTempTolak) {
                                        senaraiHakmilikTolak.add(hp);
                                    }
                                }
                            }
                        }
                    }
                }

            }

            /**
             * Lot - lot Sempadan
             */
            senaraiLaporanTanah = disLaporanTanahService.getLaporanTanahService().getListLaporanTanah(idPermohonan);
            if (!senaraiLaporanTanah.isEmpty()) {
                //One Laporan Tanah
                if (senaraiLaporanTanah.size() == 1) {
                    laporanTanah = senaraiLaporanTanah.get(0);
                    if (hakmilikPermohonan != null) {
                        kandunganPerihalTanah1 = setDefaultKandunganPerihalTanah(hakmilikPermohonan, laporanTanah, 1);
                        kandunganPerihalTanah2 = setDefaultKandunganPerihalTanah(hakmilikPermohonan, laporanTanah, 2);
                        kandunganPerihalTanah3 = setDefaultKandunganPerihalTanah(hakmilikPermohonan, laporanTanah, 3);
                    }
                    laporTanahSempadanList = disLaporanTanahService.getPelupusanService().findLaporTanahSmpdnByIdLapor(laporanTanah.getIdLaporan());
                }
                //If more not yet implemented
            }

            /**
             * Permohonan Kertas
             */
            String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
            if (StringUtils.isNotBlank(kodDok)) {
                permohonanKertas = new PermohonanKertas();
                permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                if (permohonanKertas != null) {
                    senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                    if (!senaraiKandunganTemp.isEmpty()) {
                        kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                    }
                    senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                    senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                    senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                    senaraiKandunganHuraianPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                    senaraiKandunganPerakuanPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                    senaraiKandunganPerakuanPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 6);
                    senaraiKandunganKeputusan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 7);
                    if (kodNegeri.equals("05")) {
                        senaraiKandunganKeputusanJktd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 8);
                    }
                    if (kodNegeri.equals("04")) {
                        senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);
                    }
                    senaraiKandunganHuraianPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 9);
                } else {
                    if (hakmilikPermohonan != null) {
                        if (kelompok) {
                            kandunganTajuk = setDefaultKandunganTajukKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                            kandunganTujuan = setDefaultKandunganTujuanKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                            kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                        } else {
                            if(permohonan.getSenaraiPemohon().isEmpty())
                            {
                                kandunganTajuk = setDefaultKandunganTajukKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, new Pemohon(), kodNegeri);
                                kandunganTujuan = setDefaultKandunganTujuanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, new Pemohon(), kodNegeri);
                                kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, new Pemohon(), kodNegeri);
                            }
                            else
                            {
                                kandunganTajuk = setDefaultKandunganTajukKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
                                kandunganTujuan = setDefaultKandunganTujuanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
                                kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
                            }
                        }
                        if (kodNegeri.equals("04")) {
                            kandunganPerihalPemohon = setDefaultKandunganPerihalPemohonKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), permohonan.getSenaraiPemohon().get(0), kodNegeri);
                        }
                    } else {
                        //Not Implemented Yet
                    }
                    setObjectPermohonanKertas(permohonan, kodDok);
                    permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                    if (permohonanKertas != null) {
                        setObjectKertasKandunganTajuk(kandunganTajuk, permohonanKertas);
                        setObjectKertasKandunganTujuan(kandunganTujuan, permohonanKertas);
                        setObjectKertasKandunganPerihalPermohonan(kandunganPerihalPermohonan, permohonanKertas);
                        if (StringUtils.isNotBlank(kandunganPerihalTanah1)) {
                            setObjectKertasKandunganPerihalTanah(kandunganPerihalTanah1, permohonanKertas, 1);
                        }
                        if (StringUtils.isNotBlank(kandunganPerihalTanah2)) {
                            setObjectKertasKandunganPerihalTanah(kandunganPerihalTanah2, permohonanKertas, 2);
                        }
                        if (StringUtils.isNotBlank(kandunganPerihalTanah3)) {
                            setObjectKertasKandunganPerihalTanah(kandunganPerihalTanah3, permohonanKertas, 3);
                        }
                        if (kodNegeri.equals("05")) {
                            fasaPermohonanKertasJktd = new FasaPermohonan();
                            fasaPermohonanKertasJktd = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, "cetak_kertas_jktd");
                            permohonanKertasJktd = new PermohonanKertas();
                            permohonanKertasJktd = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "JKTD");
                            if (fasaPermohonanKertasJktd != null && permohonanKertasJktd != null) {
                                setObjectKertasKandunganKeputusanJktd(fasaPermohonanKertasJktd, permohonanKertas, permohonanKertasJktd);
                            }
                        }
                        if (kodNegeri.equals("04")) {
                            setObjectKertasKandunganPerihalPemohon(kandunganPerihalPemohon, permohonanKertas);
                        }
                        setObjectKertasKandunganKeputusanPTG(permohonanKertas);

                        senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                        senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                        senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                        senaraiKandunganKeputusanJktd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 8);
                    }

                    if (kodNegeri.equals("04")) {
                        senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);
                    }
                }

            }
            /**
             * Permohonan Fasa
             */
            fixStageId = getStageByUrusan(permohonan.getKodUrusan().getKod(), kodNegeri);
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, fixStageId);
            if (fasaPermohonan != null) {
                ksn = fasaPermohonan.getKeputusan() != null ? fasaPermohonan.getKeputusan().getKod() : null;
            }

            amnt = checkPermohonanTuntutanKos(permohonan);

            if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                permohonanPermitItem = new PermohonanPermitItem();
                permohonanPermitItem = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItem, new String[]{idPermohonan}, 0);
            }

            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                disPermohonanBahanBatu = new DisPermohonanBahanBatu();
                disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
                //FOR JUMLAH KENE BAYAR IN SYARAT-SYARAT
                if (hakmilikPermohonan.getKodMilik() != null) {
                    if (hakmilikPermohonan.getKodMilik().getKod().equals('H')) {
                        if (disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BG") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("PS")
                                || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TL") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("RP")
                                || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TM") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BT")) {
                            if (disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu() != null) {
                                disPermohonanBahanBatu.setJumlahKeneBayar(disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getRoyaltiTanahMilik().multiply(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu()).toString());
                                disPermohonanBahanBatu.setCagarKeneBayar((0.2) * Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()));
                            }
                        }
                    }
                }
                if (hakmilikPermohonan.getKodMilik() != null) {
                    if (hakmilikPermohonan.getKodMilik().getKod().equals('K') || hakmilikPermohonan.getKodMilik().getKod().equals('R') || hakmilikPermohonan.getKodMilik().getKod().equals('L')) {
                        if (disPermohonanBahanBatu.getSyaratBahanBatu() != null || disPermohonanBahanBatu.getSyaratBahanBatu() != null) {
                            if (disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BG") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("PS")
                                    || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TL") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("RP")
                                    || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TM") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BT")) {
                                if (disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu() != null) {
                                    disPermohonanBahanBatu.setJumlahKeneBayar(disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getRoyaltiTanahKerajaan().multiply(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu()).toString());
                                    disPermohonanBahanBatu.setCagarKeneBayar(0.2 * Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()));
                                }
                            }
                        }
                    }
                }
                PermohonanTuntutanKos mohonTuntutKosA = new PermohonanTuntutanKos();
                mohonTuntutKosA = (PermohonanTuntutanKos) disLaporanTanahService.findObject(mohonTuntutKosA, new String[]{idPermohonan, "DISKD"}, 0);
                if (mohonTuntutKosA != null) {
                    disPermohonanBahanBatu.setKuponQty(mohonTuntutKosA.getKuantiti());
                    if (kodNegeri.equals("04")) {
                        disPermohonanBahanBatu.setKuponAmaun(50.00);
                    } else {
                        disPermohonanBahanBatu.setKuponAmaun(10.00);
                    }

                    disPermohonanBahanBatu.setKupon(BigDecimal.valueOf(Double.parseDouble(String.valueOf(disPermohonanBahanBatu.getKuponQty())) * disPermohonanBahanBatu.getKuponAmaun()));

                } else {
                    if (kodNegeri.equals("04")) {
                        disPermohonanBahanBatu.setKuponAmaun(50.00);
                    } else {
                        disPermohonanBahanBatu.setKuponAmaun(10.00);
                    }

                }

                PermohonanTuntutanKos mohonTuntutKosB = new PermohonanTuntutanKos();
                mohonTuntutKosB = (PermohonanTuntutanKos) disLaporanTanahService.findObject(mohonTuntutKosB, new String[]{idPermohonan, "DISCJ"}, 0);
                if (mohonTuntutKosB != null) {
                    disPermohonanBahanBatu.setCagarJalan(mohonTuntutKosB.getAmaunTuntutan());
                }
                //JUMLAH KESELURUHAN
                if (StringUtils.isEmpty(String.valueOf(disPermohonanBahanBatu.getCagarKeneBayar()))) {
                    disPermohonanBahanBatu.setCagarKeneBayar(0.00);
                }
                if (disPermohonanBahanBatu.getCagarJalan() != null) {
                    if (StringUtils.isEmpty(disPermohonanBahanBatu.getCagarJalan().toString())) {
                        disPermohonanBahanBatu.setCagarJalan(new BigDecimal(0));
                    }
                } else {
                    disPermohonanBahanBatu.setCagarJalan(new BigDecimal(0));
                }
                if (disPermohonanBahanBatu.getKupon() != null) {
                    if (StringUtils.isEmpty(disPermohonanBahanBatu.getKupon().toString())) {
                        disPermohonanBahanBatu.setKupon(new BigDecimal(0));
                    }
                } else {
                    disPermohonanBahanBatu.setKupon(new BigDecimal(0));
                }

                BigDecimal cagaran = new BigDecimal(0);
//            double percentDouble = 20 / 100;
//            BigDecimal percent = BigDecimal.valueOf(percentDouble);
//            cagaran = BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar()).multiply(percent);
                cagaran = BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar());
//            disPermohonanBahanBatu.setTotalAll(disPermohonanBahanBatu.getCagarJalan().add(BigDecimal.valueOf(disPermohonanBahanBatu.getCagarKeneBayar())).add(disPermohonanBahanBatu.getKupon()).add(cagaran));
                if (disPermohonanBahanBatu.getJumlahKeneBayar() != null) {
                    disPermohonanBahanBatu.setTotalAll(disPermohonanBahanBatu.getCagarJalan().add(BigDecimal.valueOf(Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()))).add(disPermohonanBahanBatu.getKupon()).add(cagaran));
                }
            } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
//                disPermohonanBahanBatu = new DisPermohonanBahanBatu();
//                disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
            }

            if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                permohonanTuntutanKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
                    amnt = permohonanTuntutanKos.getAmaunTuntutan();
                    if ((amnt != null) && (disPermohonanBahanBatu.getTotalAll() != null)) {
                        disPermohonanBahanBatu.setTotalLPSdanPermit(disPermohonanBahanBatu.getTotalAll().add(amnt));
                    }
                }
            }
            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium() != null)) {
                keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();
            }
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
            }
            catatan = new String();
            catatan = permohonan.getSebab();

            if (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PJTK") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("PTPBP") || permohonan.getKodUrusan().getKod().equals("RLPS")) {
                permohonanPermitItem = new PermohonanPermitItem();
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    List<PermohonanPermitItem> senaraiPermohonanPermitItem = new ArrayList<PermohonanPermitItem>();
                    senaraiPermohonanPermitItem = disLaporanTanahService.getPelupusanService().findPermohonanPermitItemByIdMohonList(idPermohonan);
                    keg = new String();
                    for (PermohonanPermitItem ppi : senaraiPermohonanPermitItem) {
                        if (ppi.getKodItemPermit().getKod().equals("LN")) {
                            keg = ppi.getKodItemPermit().getKod();

                        }
                    }
                } else {
                    permohonanPermitItem = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItem, new String[]{idPermohonan}, 0);
                    if (permohonanPermitItem != null) {
                        if ((permohonanPermitItem != null) && (permohonanPermitItem.getKodItemPermit() != null)) {
                            keg = permohonanPermitItem.getKodItemPermit().getKod();
                        }
                    } else {
                        permohonanPermitItem = new PermohonanPermitItem();
                    }
                }

            }
        }
    }

    public Resolution refreshpage() {
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = new PermohonanKertas();
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);

        if (idPermohonan != null) {
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            if (senaraiKelompok.size() > 0) {
                if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                    kelompok = true;
                } else {
                    kelompok = false;
                }

            } else {
                kelompok = false;
            }
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

            senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

            if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//              senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            } else {
                senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            }

            if (senaraiHakmilikPermohonan.size() > 0) {
                if (senaraiHakmilikPermohonan.size() == 1) {
                    hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
                    if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKeteranganKadarPremium() != null)) {
                        keteranganKadarPremium = hakmilikPermohonan.getKeteranganKadarPremium();
                    }
                } else {
                    //Not yet implemented
                }
            } else {
                hakmilikPermohonan = null;
            }

        }
        /**
         * Lot - lot Sempadan
         */
        senaraiLaporanTanah = disLaporanTanahService.getLaporanTanahService().getListLaporanTanah(idPermohonan);
        if (!senaraiLaporanTanah.isEmpty()) {
            //One Laporan Tanah
            if (senaraiLaporanTanah.size() == 1) {
                laporanTanah = senaraiLaporanTanah.get(0);
                laporTanahSempadanList = disLaporanTanahService.getPelupusanService().findLaporTanahSmpdnByIdLapor(laporanTanah.getIdLaporan());
            }
        }
        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
//        rehydrate();
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
//        permohonanKertas = new PermohonanKertas();
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        String forwardJSP = new String();
        int typeNum = type.equals("kTajuk") ? 0
                : type.equals("kTujuan") ? 1
                : type.equals("kPermohonan") ? 2
                : type.equals("kTanah") ? 3
                : type.equals("kHuraianPtd") ? 4
                : type.equals("kPerakuanPtd") ? 5
                : type.equals("kPerakuanPtg") ? 6
                : type.equals("kKeputusan") ? 7
                : type.equals("kKeputusanJktd") ? 8
                : type.equals("kHuraianPtg") ? 9
                : type.equals("main") ? 10
                : type.equals("kPemohon") ? 11
                : 11;

        switch (typeNum) {
            case 0:
                senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                if (!senaraiKandunganTemp.isEmpty()) {
                    kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                }
                forwardJSP = DisPBGSAPage.getKMMK_TAJUK();
                break;
            case 1:
                senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                forwardJSP = DisPBGSAPage.getKMMK_TUJUAN();
                break;
            case 2:
                senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_PERMOHONAN();
                break;
            case 3:
                senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_TANAH();
                break;
            case 4:
                senaraiKandunganHuraianPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                forwardJSP = DisPBGSAPage.getKMMK_HURAIAN_PTD();
                break;
            case 5:
                senaraiKandunganPerakuanPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                fixStageId = getStageByUrusan(permohonan.getKodUrusan().getKod(), kodNegeri);
                fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, fixStageId);
                amnt = checkPermohonanTuntutanKos(permohonan);
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    permohonanTuntutanKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                    if ((permohonanTuntutanKos != null) && (permohonanTuntutanKos.getAmaunTuntutan() != null)) {
                        amnt = permohonanTuntutanKos.getAmaunTuntutan();
                        if ((amnt != null) && (disPermohonanBahanBatu.getTotalAll() != null)) {
                            disPermohonanBahanBatu.setTotalLPSdanPermit(disPermohonanBahanBatu.getTotalAll().add(amnt));
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    senaraikodKadarPremium = disLaporanTanahService.getPlpservice().getSenaraiKodKadarPremiumDistinctNama();
                }
                if (fasaPermohonan != null) {
                    ksn = fasaPermohonan.getKeputusan() != null ? fasaPermohonan.getKeputusan().getKod() : null;
                }
                if (hakmilikPermohonan != null) {
                    idMh = String.valueOf(hakmilikPermohonan.getId());
                }
                forwardJSP = DisPBGSAPage.getKMMK_PERAKUAN_PTD();
                break;
            case 6:
                senaraiKandunganPerakuanPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 6);
                forwardJSP = DisPBGSAPage.getKMMK_PERAKUAN_PTG();
                break;
            case 7:
                senaraiKandunganKeputusan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 7);
                forwardJSP = DisPBGSAPage.getKMMK_KEPUTUSAN();
                break;
            case 8:
                senaraiKandunganKeputusanJktd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 8);
                forwardJSP = DisPBGSAPage.getKMMK_KEPUTUSAN_JKTD();
                break;
            case 9:
                senaraiKandunganHuraianPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 9);
                forwardJSP = DisPBGSAPage.getKMMK_HURAIAN_PTG();
                break;
            case 10:
                rehydrate();
                disKertasMMKController = (DisKertasMMKController) getContext().getRequest().getSession().getAttribute("disKertasMMKController");
                if (kodNegeri.equals("04")) {
                    forwardJSP = DisPBGSAPage.getKMMK_MAIN_PAGE_MLK();
                } else {
                    forwardJSP = DisPBGSAPage.getKMMK_MAIN_PAGE_NS();
                }
                break;
            case 11:
                senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_PEMOHON();
                break;
        }
        return forwardJSP;
    }

    /**
     *
     * Purpose to cater kod dokumen based on urusan and negeri
     */
    public String kertasDok(String urusan, String kodNegeri) {
        String kodDok = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : urusan.equals("PBGSA") ? 4
                : 0;

        switch (typeUrusan) {
            case 1: //BMBT
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            case 2: //PJBTR
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            case 3: //PBMT
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            case 4: //PBGSA
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    kodDok = "RMN";
                } else {
                    kodDok = "RMN";
                }
                break;
        }
        return kodDok;
    }

    public Resolution simpanKodSyaratNyata() {
        idMh = getContext().getRequest().getParameter("idMh");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idMh}, 2);
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKod())) {
                KodSyaratNyata kodSN = new KodSyaratNyata();
                kodSN = (KodSyaratNyata) disLaporanTanahService.findObject(kodSN, new String[]{disSyaratSekatan.getKod()}, 0);
                if (kodSN != null) {
                    hakmilikPermohonanSave.setSyaratNyataBaru(kodSN);
                }
            }
        }

        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        rehydrate();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMh));
        }
        return new JSP(DisPBGSAPage.getKMMK_PERAKUAN_PTD()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKodSekatan() {
        idMh = getContext().getRequest().getParameter("idMh");
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idMh}, 2);
            ;
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSktn())) {
                KodSekatanKepentingan kodSK = new KodSekatanKepentingan();
                kodSK = (KodSekatanKepentingan) disLaporanTanahService.findObject(kodSK, new String[]{disSyaratSekatan.getKodSktn()}, 0);
                if (kodSK != null) {
                    hakmilikPermohonanSave.setSekatanKepentinganBaru(kodSK);
                }
            }
        }
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonan = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMh));
        }
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);
        rehydrate();
        return new JSP(DisPBGSAPage.getKMMK_PERAKUAN_PTD()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKandungan() throws ParseException {

        System.out.println("disPermohonanBahanBatu simpanKandungan: " + disPermohonanBahanBatu);

        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
            if (senaraiKelompok.size() > 0) {
                kelompok = true;
            } else {
                kelompok = false;
            }
        }
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        int index = 0;
        boolean updateDB = false;
        boolean updateDB2 = false;
        index = Integer.parseInt(getContext().getRequest().getParameter("indexKertas"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        String forwardJSP = new String();

        switch (index) {
            case 0:
                forwardJSP = DisPBGSAPage.getKMMK_TAJUK();
                updateKandungan(kandunganTajuk, "kTajuk", permohonanKertas, 0);
                break;
            case 1:
                forwardJSP = DisPBGSAPage.getKMMK_TUJUAN();
                updateKandungan(kand, "kTujuan", permohonanKertas, 1);
                break;
            case 2:
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_PERMOHONAN();
                updateKandungan(kand, "kPermohonan", permohonanKertas, 2);
                break;
            case 3:
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_TANAH();
                updateKandungan(kand, "kTanah", permohonanKertas, 3);
                break;
            case 4:
                forwardJSP = DisPBGSAPage.getKMMK_HURAIAN_PTD();
                updateKandungan(kand, "kHuraianPtd", permohonanKertas, 4);
                break;
            case 5:
                forwardJSP = DisPBGSAPage.getKMMK_PERAKUAN_PTD();
                updateKandungan(kand, "kPerakuanPtd", permohonanKertas, 5);
                updateMohonFasa();
                if (kodNegeri.equals("05")) {
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn == null) {
                        ksn = getContext().getRequest().getParameter("kodksn");
                        if (ksn != null) {
                            if (ksn.equals("SL")) {
                                updateMohonHakmilik();
                                if (!kelompok) {
                                    if (!permohonan.getKodUrusan().getKod().equals("PLPS") && !permohonan.getKodUrusan().getKod().equals("PBPTG") && !permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                                        updateMohonTuntutKos();
                                    }
                                    if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                                        updateBahanBatu();
                                    }
                                }
                            }
                        }
                    } else {
                        if (ksn.equals("SL")) {
                            updateMohonHakmilik();
                            if (!kelompok) {
                                if (!permohonan.getKodUrusan().getKod().equals("PLPS") && !permohonan.getKodUrusan().getKod().equals("PBPTG") && !permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                                    updateMohonTuntutKos();
                                }
                                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                                    updateBahanBatu();
                                }
                            }

                        }
                    }
                } else {
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn == null) {
                        ksn = getContext().getRequest().getParameter("kodksn");
                        if (ksn != null) {
                            if (ksn.equals("SL")) {
                                updateMohonHakmilik();
                                if (!kelompok) {
                                    if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
                                        updateMohonTuntutKos();
                                    }
                                    if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                                        updateBahanBatu();
                                    }
                                    if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                                        updatePermohonan();
                                        updateMohonPermitItem();
                                        updateMohonTuntutKos();
                                    }

                                }
                            }
                        }
                    } else {
                        if (ksn.equals("SL")) {
                            updateMohonHakmilik();
                            if (!kelompok) {
                                if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
                                    updateMohonTuntutKos();
                                }
                                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                                    updateBahanBatu();
                                }
                                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                                    updatePermohonan();
                                    updateMohonPermitItem();
                                    updateMohonTuntutKos();
                                }
                            }

                        }
                    }
                }
                break;
            case 6:
                forwardJSP = DisPBGSAPage.getKMMK_PERAKUAN_PTG();
                updateKandungan(kand, "kPerakuanPtg", permohonanKertas, 6);
                break;
            case 7:
                forwardJSP = DisPBGSAPage.getKMMK_KEPUTUSAN();
                updateKandungan(kand, "kKeputusan", permohonanKertas, 7);
                break;
            case 8:
                forwardJSP = DisPBGSAPage.getKMMK_KEPUTUSAN_JKTD();
                updateKandungan(kand, "kKeputusanJktd", permohonanKertas, 8);
                break;
            case 9:
                forwardJSP = DisPBGSAPage.getKMMK_HURAIAN_PTG();
                updateKandungan(kand, "kHuraianPtg", permohonanKertas, 9);
                break;
            case 10:
                forwardJSP = DisPBGSAPage.getKMMK_PERIHAL_PEMOHON();
                updateKandungan(kand, "kPemohon", permohonanKertas, 10);
                break;
            default:
                break;
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = true;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }
        return new JSP(forwardJSP).addParameter("tab", Boolean.TRUE);
    }

    public Boolean updateMohonFasa() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        fixStageId = new String();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        fixStageId = getStageByUrusan(permohonan.getKodUrusan().getKod(), kodNegeri);
        cawangan = new KodCawangan();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ksn = ctx.getRequest().getParameter("syorKpsn");
        if (ksn == null) {
            ksn = getContext().getRequest().getParameter("kodksn");
        }
        cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
        fasaPermohonan = new FasaPermohonan();
        fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, fixStageId);
        if (fasaPermohonan != null) {
            fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "update", pengguna));
        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setInfoAudit(disLaporanTanahService.findAudit(fasaPermohonan, "new", pengguna));
        }
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        if (ksn != null) {
            KodKeputusan kkp = disLaporanTanahService.getKodKeputusanDAO().findById(ksn);
            fasaPermohonan.setKeputusan(kkp);
        }
        fasaPermohonan.setIdAliran(fixStageId);
        disLaporanTanahService.getLaporanTanahService().simpanFasaPermohonan(fasaPermohonan);

        return updateDB;
    }

    public Boolean updateKandungan(String kand, String typeSyor, PermohonanKertas mohonKertas, int bil) {

        boolean updateDB = false;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        sizeSenaraiLaporUlas = ctx.getRequest().getParameter("sizeSenaraiLaporUlas");
//        List<PermohonanLaporanUlasan> senaraiLaporanKandungan2 = new ArrayList<PermohonanLaporanUlasan>();
        List<PermohonanKertasKandungan> senaraiPermohonanKertasKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiPermohonanKertasKandungan = disLaporanTanahService.getPelupusanService().findByIdLapor(mohonKertas.getIdKertas(), bil);
        if (!StringUtils.isEmpty(typeSyor)) {
            if (typeSyor.equalsIgnoreCase("kTajuk")) {
                if (!senaraiPermohonanKertasKandungan.isEmpty()) {
                    for (PermohonanKertasKandungan pkk : senaraiPermohonanKertasKandungan) {
                        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "update", pengguna));
                        pkk.setKandungan(kand);
                        disLaporanTanahService.getPelupusanService().saveOrUpdatePermohonanKertasKandungan(pkk);
                    }
                } else {
                    cawangan = new KodCawangan();
                    cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                    PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
                    pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "new", pengguna));
                    pkk.setBil(0);
                    pkk.setCawangan(cawangan);
                    pkk.setKertas(mohonKertas);
                    pkk.setKandungan(kand);
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
                }
            } else {
                for (PermohonanKertasKandungan pkk : senaraiPermohonanKertasKandungan) {
                    String checkKand = String.valueOf(pkk.getIdKandungan()) + "kandunganKertas";
                    String kandungan = ctx.getRequest().getParameter(checkKand);
//                    String idK = ctx.getRequest().getParameter("idKandungan1");
//                    if (pkk.getIdKandungan() == Long.valueOf(idK)) {
                    if (!StringUtils.isEmpty(kandungan)) {
                        pkk.setKandungan(kandungan);
                        pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "update", pengguna));
                        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
                    }
//                    }
                }

            }

        }

        return updateDB;
    }

    public Boolean updateMohonHakmilik() {

        boolean updateDB = false;
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idMh = getContext().getRequest().getParameter("idMh");
        kodU = getContext().getRequest().getParameter("kodU");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if (!StringUtils.isEmpty(idMh)) {
            hakmilikPermohonanSave = new HakmilikPermohonan();
            hakmilikPermohonanSave = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMh));
        } else {
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                if (senaraiHakmilikPermohonan.size() == 1) {
                    hakmilikPermohonanSave = senaraiHakmilikPermohonan.get(0);
                    if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
                        hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
                        LOG.info("hakmilikPermohonan ::::::::::::" + hakmilikPermohonan);
                    }
                } else {
                    //Not yet implemented
                }
            }
        }

        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = true;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }

        if (!permohonan.getKodUrusan().getKod().equals("PTGSA") && !permohonan.getKodUrusan().getKod().equals("PBMMK") && !permohonan.getKodUrusan().getKod().equals("PBPTG") && !permohonan.getKodUrusan().getKod().equals("PBPTD") && !permohonan.getKodUrusan().getKod().equals("PBGSA") && !permohonan.getKodUrusan().getKod().equals("PLPS") && !permohonan.getKodUrusan().getKod().equals("PPRU") && !permohonan.getKodUrusan().getKod().equals("PTPBP") && !permohonan.getKodUrusan().getKod().equals("LPSP") && !permohonan.getKodUrusan().getKod().equals("LMCRG") && !permohonan.getKodUrusan().getKod().equals("MLCRG")) {
            if (!kelompok) {
                if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
                    hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
                    if (!StringUtils.isEmpty(kodU)) {
                        KodUOM kodUOM = new KodUOM();
                        kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                        hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
                    } else {
                        addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
                    }
                } else {
                    if (hakmilikPermohonanSave.getKodUnitLuas() != null) {
                        hakmilikPermohonanSave.setLuasLulusUom(hakmilikPermohonan.getKodUnitLuas());
                    }
                }
            }

            if (!StringUtils.isEmpty(hakmilikPermohonan.getStatusLuasDiluluskan())) {
                hakmilikPermohonanSave.setStatusLuasDiluluskan(hakmilikPermohonan.getStatusLuasDiluluskan());
            }
        }
        kodHakmilik = getContext().getRequest().getParameter("kodHakmilik");
        if (!StringUtils.isEmpty(kodHakmilik)) {
            KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHakmilik);
            hakmilikPermohonanSave.setKodHakmilik(khm);
            if (kodHakmilik.equals("PM") || kodHakmilik.equals("PN")) {
                hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
            hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
        }
        katTanahPilihan = getContext().getRequest().getParameter("kategoriTanahBaru.kod");
        if (!StringUtils.isEmpty(katTanahPilihan)) {
            KodKategoriTanah kktb = disLaporanTanahService.getKodKategoriTanahDAO().findById(katTanahPilihan);
            hakmilikPermohonanSave.setKategoriTanahBaru(kktb);
        }
        kodGunaTanah = getContext().getRequest().getParameter("kodGunaTanah");
        if (!StringUtils.isEmpty(kodGunaTanah)) {
            KodKegunaanTanah kgt = disLaporanTanahService.getKodKegunaanTanahDAO().findById(kodGunaTanah);
            hakmilikPermohonanSave.setKodKegunaanTanah(kgt);
        }
        keteranganKadarPremium = getContext().getRequest().getParameter("keteranganKadarPremium");
        if (!StringUtils.isEmpty(keteranganKadarPremium)) {
            hakmilikPermohonanSave.setKeteranganKadarPremium(keteranganKadarPremium);
        }
        if (!permohonan.getKodUrusan().getKod().equals("PTGSA") && !permohonan.getKodUrusan().getKod().equals("PBGSA") && !permohonan.getKodUrusan().getKod().equals("PBPTG") && !permohonan.getKodUrusan().getKod().equals("PBPTD") && !permohonan.getKodUrusan().getKod().equals("PBMMK") && !permohonan.getKodUrusan().getKod().equals("PLPS") && !permohonan.getKodUrusan().getKod().equals("PPRU") && !permohonan.getKodUrusan().getKod().equals("LPSP") && !permohonan.getKodUrusan().getKod().equals("LMCRG") && !permohonan.getKodUrusan().getKod().equals("MLCRG")) {
            if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
                hakmilikPermohonanSave.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
            }
            if (!StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
                hakmilikPermohonanSave.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
            }

            if (hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
                hakmilikPermohonanSave.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (amnt != BigDecimal.ZERO) {
                hakmilikPermohonanSave.setKadarPremium(amnt);
            }
            if (hakmilikPermohonanSave.getLuasDiluluskan() != null && hakmilikPermohonanSave.getKodUnitLuas() != null) {
                BigDecimal cukai = calcTax.calculate(kodGunaTanah, String.valueOf(hakmilikPermohonanSave.getBandarPekanMukimBaru().getKod()), hakmilikPermohonanSave.getKodUnitLuas().getKod(), hakmilikPermohonanSave.getLuasDiluluskan(), hakmilikPermohonanSave, null);
                if (cukai != null && !cukai.equals(BigDecimal.ZERO)) {
                    hakmilikPermohonanSave.setCukaiBaru(cukai);
                }
            }
            if (hakmilikPermohonan != null && hakmilikPermohonan.getCukaiPerMeterPersegi() != null) {
                hakmilikPermohonanSave.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
            }
            if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganKadarPremium())) {
                hakmilikPermohonanSave.setKeteranganKadarPremium(hakmilikPermohonan.getKeteranganKadarPremium());
            }
        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            if (amnt != BigDecimal.ZERO) {
                hakmilikPermohonanSave.setKadarPremium(amnt);
            }
            if (hakmilikPermohonanSave.getLuasDiluluskan() != null && hakmilikPermohonanSave.getKodUnitLuas() != null) {
                BigDecimal cukai = calcTax.calculate(kodGunaTanah, String.valueOf(hakmilikPermohonanSave.getBandarPekanMukimBaru().getKod()), hakmilikPermohonanSave.getKodUnitLuas().getKod(), hakmilikPermohonanSave.getLuasDiluluskan(), hakmilikPermohonanSave, null);
                if (cukai != null && !cukai.equals(BigDecimal.ZERO)) {
                    hakmilikPermohonanSave.setCukaiBaru(cukai);
                }
            }
        }
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);

        return updateDB;
    }

    public void updateMohonPermitItem() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanPermitItem> senaraiMohonPermitItem = new ArrayList<PermohonanPermitItem>();
        senaraiMohonPermitItem = disLaporanTanahService.getPlpservice().findPermohonanPermitItemByIdMohonList(idPermohonan);

        for (PermohonanPermitItem ppi : senaraiMohonPermitItem) {
            if (!ppi.getKodItemPermit().getKod().equalsIgnoreCase("KB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("PB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("MB") && !ppi.getKodItemPermit().getKod().equalsIgnoreCase("LN")) {
                PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                if (ppi.getKodItemPermit() != null) {
                    permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan(), ppi.getKodItemPermit().getKod()}, 1);
                } else {
                    permohonanPermitItemSave = (PermohonanPermitItem) disLaporanTanahService.findObject(permohonanPermitItemSave, new String[]{ppi.getPermohonan().getIdPermohonan()}, 0);
                }
                if (permohonanPermitItemSave != null) {
                    permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "update", pengguna));
                } else {
                    permohonanPermitItemSave = new PermohonanPermitItem();
                    permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                    permohonanPermitItemSave.setPermohonan(permohonan);
                    permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                }

                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                        disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
                    }
                }
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            System.err.println(senaraiMohonPermitItem.size());
            if (senaraiMohonPermitItem.size() == 0) {
                System.out.println("RLPS keg :" + keg);
                PermohonanPermitItem permohonanPermitItemSave = new PermohonanPermitItem();
                permohonanPermitItemSave.setInfoAudit(disLaporanTanahService.findAudit(permohonanPermitItemSave, "new", pengguna));
                permohonanPermitItemSave.setPermohonan(permohonan);
                permohonanPermitItemSave.setKodCawangan(permohonan.getCawangan());
                if (!StringUtils.isEmpty(keg)) {
                    KodItemPermit kip = new KodItemPermit();
                    kip = disLaporanTanahService.getKodItemPermitDAO().findById(keg);
                    if (kip != null) {
                        permohonanPermitItemSave.setKodItemPermit(kip);
                        disLaporanTanahService.getPlpservice().saveOrUpdate(permohonanPermitItemSave);
                    }
                }
            }
        }
    }

    public void updateMohonTuntutKos() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        HakmilikPermohonan mohonHM = new HakmilikPermohonan();

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
                //                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 2
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 5
                //                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 10
                : 0;

        switch (numUrusan) {
            case 1:
                /*
                 * Add for Bayaran Kupon 
                 */

                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    rehydrate();
                }
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISKD");

                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    // plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
                    disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKos);
                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Kupon");
                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon());
//                    mohonTuntutKos.setAmaunTuntutan(disPermohonanBahanBatu.getKupon() != null ? disPermohonanBahanBatu.getKupon() : new BigDecimal(0));
                    BigDecimal seUnit = new BigDecimal(50);
                    mohonTuntutKos.setAmaunSeunit(seUnit);
                    mohonTuntutKos.setKuantiti(disPermohonanBahanBatu.getKuponQty());
                    mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISKD"));
                    mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                }
                /* End for bayaran kupon
                 * 
                 */
                /*Add for CAGARAN JALAN
                 * 
                 */
                PermohonanTuntutanKos mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                mohonTuntutKosCagarJln = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCJ");

                if (mohonTuntutKosCagarJln == null) {
                    mohonTuntutKosCagarJln = new PermohonanTuntutanKos();
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "new", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
////                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISKD").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanSavePermohonanTuntutanKos(mohonTuntutKosCagarJln);
                } else {
                    mohonTuntutKosCagarJln.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosCagarJln, "update", pengguna));
                    mohonTuntutKosCagarJln.setPermohonan(permohonan);
                    mohonTuntutKosCagarJln.setCawangan(permohonan.getCawangan());
                    mohonTuntutKosCagarJln.setItem("Cagaran Jalan");
                    mohonTuntutKosCagarJln.setAmaunTuntutan(disPermohonanBahanBatu.getCagarJalan());
//                        BigDecimal seUnit = new BigDecimal(50);
//                        mohonTuntutKosCagarJln.setAmaunSeunit(seUnit);
//                        mohonTuntutKosCagarJln.setKuantiti(kuponQty);
                    mohonTuntutKosCagarJln.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ"));
                    mohonTuntutKosCagarJln.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCJ").getKodKewTrans());
                    disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCagarJln);
                }
                /* 
                 *  End for bayaran kupon
                 */
                /*
                 * Add for bayaran LPS - For urusan LPSP
                 */
                if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                    PermohonanTuntutanKos mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                    mohonTuntutKosBayaranLPS = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                    if (mohonTuntutKosBayaranLPS == null) {
                        mohonTuntutKosBayaranLPS = new PermohonanTuntutanKos();
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "new", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPermohonanTuntutanKosDAO().save(mohonTuntutKosBayaranLPS);
                    } else {
                        mohonTuntutKosBayaranLPS.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKosBayaranLPS, "update", pengguna));
                        mohonTuntutKosBayaranLPS.setPermohonan(permohonan);
                        mohonTuntutKosBayaranLPS.setCawangan(permohonan.getCawangan());
                        mohonTuntutKosBayaranLPS.setItem("Bayaran LPS");
                        mohonTuntutKosBayaranLPS.setAmaunTuntutan(amnt);
                        mohonTuntutKosBayaranLPS.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                        mohonTuntutKosBayaranLPS.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                        disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosBayaranLPS);
                    }
                }
                /*
                 * End for bayaran LPS
                 */
                break;
            case 2:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISCR");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISCR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISCR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;

            case 3:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISLB");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISLB"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISLB").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 4:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DIS4B");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DIS4B").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 5:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4A").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 6:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4D");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }

                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4D").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 7:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4E");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB4E").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 8:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISB7"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISB7").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
            case 9:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISPRM").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);

                PermohonanTuntutanKos mohonTuntutKosCukai = new PermohonanTuntutanKos();
                mohonTuntutKosCukai = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISTAX");
                HakmilikPermohonan mohonHM1 = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM1 = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM1, new String[]{idMh}, 2);
                }
                if (mohonTuntutKosCukai == null) {
                    mohonTuntutKosCukai = new PermohonanTuntutanKos();
                    mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKosCukai.setPermohonan(permohonan);
                    mohonTuntutKosCukai.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKosCukai.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM1 != null) {
                    mohonTuntutKosCukai.setHakmilikPermohonan(mohonHM1);
                    mohonTuntutKosCukai.setAmaunTuntutan(calcTax.calculate(mohonHM1.getKodKegunaanTanah().getKod(), String.valueOf(mohonHM1.getBandarPekanMukimBaru().getKod()), mohonHM1.getKodUnitLuas().getKod(), mohonHM1.getLuasDiluluskan(), mohonHM1, null));
                }
                mohonTuntutKosCukai.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getNama());
                mohonTuntutKosCukai.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX"));
                mohonTuntutKosCukai.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISTAX").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKosCukai);
                break;
            case 10:
                mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISSTR");
                idMh = getContext().getRequest().getParameter("idMh");
                mohonHM = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    mohonHM = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHM, new String[]{idMh}, 2);
                }
                if (mohonTuntutKos == null) {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "new", pengguna));
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());

                } else {
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos, "update", pengguna));
                }
                if (mohonHM != null) {
                    mohonTuntutKos.setHakmilikPermohonan(mohonHM);
                }
                mohonTuntutKos.setItem(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getNama());
                mohonTuntutKos.setAmaunTuntutan(amnt);
                mohonTuntutKos.setKodTuntut(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR"));
                mohonTuntutKos.setKodTransaksi(disLaporanTanahService.getKodTuntutDAO().findById("DISSTR").getKodKewTrans());
                disLaporanTanahService.getPlpservice().simpanPermohonanTuntutanKos(mohonTuntutKos);
                break;
        }

    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        String forwardJSP = new String();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKandunganTemp = disLaporanTanahService.getPelPtService().findByIdLapor(permohonanKertas.getIdKertas(), index);
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        if (senaraiKandunganTemp.isEmpty()) {
            permohonanKertasKandungan.setSubtajuk("1");
        } else {
            int n = Integer.parseInt(senaraiKandunganTemp.get(senaraiKandunganTemp.size() - 1).getSubtajuk()) + 1;
            permohonanKertasKandungan.setSubtajuk(String.valueOf(n));
        }
        permohonanKertasKandungan.setKandungan("Sila Tambah Ayat Di Sini"); //Temporary 

        switch (index) {
            case 1:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kTujuan");
                break;
            case 2:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kPermohonan");
                break;
            case 3:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kTanah");
                break;
            case 4:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kHuraianPtd");
                break;
            case 5:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                ksn = getContext().getRequest().getParameter("kodksn");
                if (ksn != null) {
                    updateMohonFasa();
                }
                rehydrate();

                forwardJSP = refreshData("kPerakuanPtd");
                break;
            case 6:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kPerakuanPtg");
                break;
            case 7:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kKeputusan");
                break;
            case 8:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kKeputusanJktd");
                break;
            case 9:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kHuraianPtg");
                break;
            case 10:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kPemohon");
                break;
            default:
                break;
        }
        senaraiKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idPermohonan);
        if (senaraiKelompok.size() > 0) {
            if (senaraiKelompok.get(0).getJenisKelopok().equals("K")) {
                kelompok = true;
            } else {
                kelompok = false;
            }

        } else {
            kelompok = false;
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String getStageByUrusan(String urusan, String kodNegeri) {
        String stage = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : urusan.equals("PPTR") ? 4
                : urusan.equals("PBGSA") ? 5
                : urusan.equals("PTGSA") ? 6
                : 0;

        switch (typeUrusan) {
            case 1: //BMBT
                if (kodNegeri.equals("04")) {
                    stage = "";
                } else {
                    stage = "";
                }
                break;
            case 2: //PJBTR
                if (kodNegeri.equals("04")) {
                    stage = "";
                } else {
                    stage = "";
                }
                break;
            case 3: //PBMT
                if (kodNegeri.equals("04")) {
                    stage = "sedia_draf_mmk";
                } else {
                    stage = "terima_kpsn_jktd_sedia_mmk";
                }
                break;
            case 4://PPTR
                if (kodNegeri.equals("04")) {
                    stage = "sedia_draf_mmkn";
                } else {
                    stage = "09PenyediaanDrafMMK";
                }
                break;
            case 5: //PBGSA
                if (kodNegeri.equals("04")) {
                    stage = "";
                } else {
                    stage = "09TrmdanImbs";
                }
                break;
            case 6: //PTGSA
                if (kodNegeri.equals("04")) {
                    stage = "";
                } else {
                    stage = "07SedDrfMMK";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    stage = "peraku_ptd";
                } else {
                    stage = "peraku_ptd";
                }
                break;
        }

        return stage;
    }

    public BigDecimal checkPermohonanTuntutanKos(Permohonan p) {
        BigDecimal amaun = new BigDecimal(0);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 2
                : p.getKodUrusan().getKod().equals("PBPTG") ? 3
                : p.getKodUrusan().getKod().equals("LMCRG") ? 4
                : p.getKodUrusan().getKod().equals("PJLB") ? 5
                : p.getKodUrusan().getKod().equals("LPSP") ? 6
                : p.getKodUrusan().getKod().equals("PLPS") ? 7
                : p.getKodUrusan().getKod().equals("PPRU") ? 8
                : p.getKodUrusan().getKod().equals("PPTR") ? 9
                : p.getKodUrusan().getKod().equals("PTGSA") ? 10
                : p.getKodUrusan().getKod().equals("PRMP") ? 11
                : p.getKodUrusan().getKod().equals("PBMT") ? 12
                : p.getKodUrusan().getKod().equals("MCMCL") ? 13
                : p.getKodUrusan().getKod().equals("MMMCL") ? 14
                : p.getKodUrusan().getKod().equals("PRIZ") ? 15
                : p.getKodUrusan().getKod().equals("PHLA") ? 16
                : p.getKodUrusan().getKod().equals("PBRZ") ? 17
                : p.getKodUrusan().getKod().equals("PBHL") ? 18
                : p.getKodUrusan().getKod().equals("BMBT") ? 19
                : p.getKodUrusan().getKod().equals("PJBTR") ? 20
                : p.getKodUrusan().getKod().equals("PLPTD") ? 21
                : p.getKodUrusan().getKod().equals("PBMMK") ? 22
                : 0;
        switch (typeNum) {
            case 7: //Urusan PLPS
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4A");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 8: //Urusan PPRU
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4D");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 9: //Urusan PPTR
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4E");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 11: //Urusan PRMP
                mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB7");
                if (mohonTuntutKos != null) {
                    if (mohonTuntutKos.getAmaunTuntutan() != BigDecimal.ZERO) {
                        amaun = mohonTuntutKos.getAmaunTuntutan();
                    }
                }

                break;
            case 12: //Urusan PBMT

                break;

            default:
                break;
        }
        return amaun;
    }

    public void updateBahanBatu() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        idMh = getContext().getRequest().getParameter("idMh");
        kodU = getContext().getRequest().getParameter("kodU");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanBahanBatuan mohonBahanBatuSave = new PermohonanBahanBatuan();
        mohonBahanBatuSave = (PermohonanBahanBatuan) disLaporanTanahService.findObject(mohonBahanBatuSave, new String[]{idPermohonan}, 0);
        if (!StringUtils.isEmpty(ksn) && ksn.equalsIgnoreCase("SL")) {
            if (mohonBahanBatuSave != null) {
                mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "update", pengguna));
            } else {

                HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
                if (!StringUtils.isEmpty(idMh)) {
                    hakmilikPermohonanSave = new HakmilikPermohonan();
                    hakmilikPermohonanSave = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMh));
                } else {
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                    if (senaraiHakmilikPermohonan.size() > 0) {
                        if (senaraiHakmilikPermohonan.size() == 1) {
                            hakmilikPermohonanSave = senaraiHakmilikPermohonan.get(0);
                        } else {
                            //Not yet implemented
                        }
                    }
                }
                mohonBahanBatuSave = new PermohonanBahanBatuan();
                mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "new", pengguna));
                mohonBahanBatuSave.setPermohonan(permohonan);
                if (hakmilikPermohonanSave != null) {
                    mohonBahanBatuSave.setBandarPekanMukim(hakmilikPermohonanSave.getHakmilik() != null ? hakmilikPermohonanSave.getHakmilik().getBandarPekanMukim() : hakmilikPermohonanSave.getBandarPekanMukimBaru());
                }
            }
            String stringUOM = new String();
            KodUOM kodUOM = new KodUOM();
            stringUOM = getContext().getRequest().getParameter("jumlahIsipaduUom");
            if (!StringUtils.isEmpty(stringUOM)) {
                kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{stringUOM}, 0);
                mohonBahanBatuSave.setJumlahIsipaduUom(kodUOM);
            } else {
                mohonBahanBatuSave.setJumlahIsipaduUom(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipaduDipohonUom());
            }

            if (disPermohonanBahanBatu != null) {
                if (disPermohonanBahanBatu.getSyaratBahanBatu() != null) {
                    if (!StringUtils.isEmpty(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu().toString())) {
                        mohonBahanBatuSave.setJumlahIsipadu(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu());
                    }
                    if (disPermohonanBahanBatu.getSyaratBahanBatu().getTempohDisyor() != null) {
                        mohonBahanBatuSave.setTempohDisyor(disPermohonanBahanBatu.getSyaratBahanBatu().getTempohDisyor());
                    }
                }

            }
            stringUOM = new String();
            kodUOM = new KodUOM();
            stringUOM = getContext().getRequest().getParameter("tempohSyorUom");
            if (!StringUtils.isEmpty(stringUOM)) {
                kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{stringUOM}, 0);
                mohonBahanBatuSave.setTempohSyorUom(kodUOM);
            } else {
                if (disPermohonanBahanBatu.getSyaratBahanBatu() == null) {
                    PermohonanBahanBatuan mohonBahanBatu = new PermohonanBahanBatuan();
                    mohonBahanBatu = (PermohonanBahanBatuan) disLaporanTanahService.findObject(mohonBahanBatu, new String[]{idPermohonan}, 0);
//                    mohonBahanBatuSave.setTempohSyorUom(disLaporanTanahService.getKodUOMDAO().findById(mohonBahanBatu.getUnitTempohKeluar().getKod()));
                } else {
                    String stringUnitTempohUOM = new String();
                    stringUnitTempohUOM = getContext().getRequest().getParameter("unitTempohUOM");
                    if (StringUtils.isNotBlank(stringUnitTempohUOM)) {
                        mohonBahanBatuSave.setTempohSyorUom(disLaporanTanahService.getKodUOMDAO().findById(stringUnitTempohUOM));
                    }
                }

            }

            disLaporanTanahService.getPlpservice().simpanPermohonanBahanBatuan(mohonBahanBatuSave);
        }
    }

    public void updatePermohonan() {
        Permohonan permohonanSave = new Permohonan();
        permohonanSave = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        if (permohonanSave != null) {
            if (!StringUtils.isEmpty(catatan)) {
                permohonanSave.setCatatan(catatan);
                disLaporanTanahService.getPlpservice().simpanPermohonan(permohonanSave);
            }
        }
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getDefaultPerihalSempadan() {
        return defaultPerihalSempadan;
    }

    public void setDefaultPerihalSempadan(String defaultPerihalSempadan) {
        this.defaultPerihalSempadan = defaultPerihalSempadan;
    }

    public String getDefaultPerihalPemohon() {
        return defaultPerihalPemohon;
    }

    public void setDefaultPerihalPemohon(String defaultPerihalPemohon) {
        this.defaultPerihalPemohon = defaultPerihalPemohon;
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

    public String getKandunganTajuk() {
        return kandunganTajuk;
    }

    public void setKandunganTajuk(String kandunganTajuk) {
        this.kandunganTajuk = kandunganTajuk;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganTujuan() {
        return senaraiKandunganTujuan;
    }

    public void setSenaraiKandunganTujuan(List<PermohonanKertasKandungan> senaraiKandunganTujuan) {
        this.senaraiKandunganTujuan = senaraiKandunganTujuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerihalPermohonan() {
        return senaraiKandunganPerihalPermohonan;
    }

    public void setSenaraiKandunganPerihalPermohonan(List<PermohonanKertasKandungan> senaraiKandunganPerihalPermohonan) {
        this.senaraiKandunganPerihalPermohonan = senaraiKandunganPerihalPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerihalTanah() {
        return senaraiKandunganPerihalTanah;
    }

    public void setSenaraiKandunganPerihalTanah(List<PermohonanKertasKandungan> senaraiKandunganPerihalTanah) {
        this.senaraiKandunganPerihalTanah = senaraiKandunganPerihalTanah;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerakuanPtd() {
        return senaraiKandunganPerakuanPtd;
    }

    public void setSenaraiKandunganPerakuanPtd(List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtd) {
        this.senaraiKandunganPerakuanPtd = senaraiKandunganPerakuanPtd;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerakuanPtg() {
        return senaraiKandunganPerakuanPtg;
    }

    public void setSenaraiKandunganPerakuanPtg(List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtg) {
        this.senaraiKandunganPerakuanPtg = senaraiKandunganPerakuanPtg;
    }

    public String getDefaultKandunganTajuk() {
        return defaultKandunganTajuk;
    }

    public void setDefaultKandunganTajuk(String defaultKandunganTajuk) {
        this.defaultKandunganTajuk = defaultKandunganTajuk;
    }

    public DisKertasMMKController getDisKertasMMKController() {
        return disKertasMMKController;
    }

    public void setDisKertasMMKController(DisKertasMMKController disKertasMMKController) {
        this.disKertasMMKController = disKertasMMKController;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanAdun() {
        return senaraiUlasanAdun;
    }

    public void setSenaraiUlasanAdun(List<PermohonanRujukanLuar> senaraiUlasanAdun) {
        this.senaraiUlasanAdun = senaraiUlasanAdun;
    }

    public List<LaporanTanahSempadan> getLaporTanahSempadanList() {
        return laporTanahSempadanList;
    }

    public void setLaporTanahSempadanList(List<LaporanTanahSempadan> laporTanahSempadanList) {
        this.laporTanahSempadanList = laporTanahSempadanList;
    }

    public String getDefaultKandunganJbtn() {
        return defaultKandunganJbtn;
    }

    public void setDefaultKandunganJbtn(String defaultKandunganJbtn) {
        this.defaultKandunganJbtn = defaultKandunganJbtn;
    }

    public String getDefaultKandunganAdun() {
        return defaultKandunganAdun;
    }

    public void setDefaultKandunganAdun(String defaultKandunganAdun) {
        this.defaultKandunganAdun = defaultKandunganAdun;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganHuraianPtd() {
        return senaraiKandunganHuraianPtd;
    }

    public void setSenaraiKandunganHuraianPtd(List<PermohonanKertasKandungan> senaraiKandunganHuraianPtd) {
        this.senaraiKandunganHuraianPtd = senaraiKandunganHuraianPtd;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganKeputusan() {
        return senaraiKandunganKeputusan;
    }

    public void setSenaraiKandunganKeputusan(List<PermohonanKertasKandungan> senaraiKandunganKeputusan) {
        this.senaraiKandunganKeputusan = senaraiKandunganKeputusan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganTemp() {
        return senaraiKandunganTemp;
    }

    public void setSenaraiKandunganTemp(List<PermohonanKertasKandungan> senaraiKandunganTemp) {
        this.senaraiKandunganTemp = senaraiKandunganTemp;
    }

    public String getKawasanAdun() {
        return kawasanAdun;
    }

    public void setKawasanAdun(String kawasanAdun) {
        this.kawasanAdun = kawasanAdun;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiMohonHakmilik() {
        return senaraiMohonHakmilik;
    }

    public void setSenaraiMohonHakmilik(List<HakmilikPermohonan> senaraiMohonHakmilik) {
        this.senaraiMohonHakmilik = senaraiMohonHakmilik;
    }

    public List<LaporanTanah> getSenaraiLaporanTanah() {
        return senaraiLaporanTanah;
    }

    public void setSenaraiLaporanTanah(List<LaporanTanah> senaraiLaporanTanah) {
        this.senaraiLaporanTanah = senaraiLaporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public DisSyaratSekatan getDisSyaratSekatan() {
        return disSyaratSekatan;
    }

    public void setDisSyaratSekatan(DisSyaratSekatan disSyaratSekatan) {
        this.disSyaratSekatan = disSyaratSekatan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexSyarat() {
        return indexSyarat;
    }

    public void setIndexSyarat(String indexSyarat) {
        this.indexSyarat = indexSyarat;
    }

    public List<KodKegunaanTanah> getListKodGunaTanahByKatTanah() {
        return listKodGunaTanahByKatTanah;
    }

    public void setListKodGunaTanahByKatTanah(List<KodKegunaanTanah> listKodGunaTanahByKatTanah) {
        this.listKodGunaTanahByKatTanah = listKodGunaTanahByKatTanah;
    }

    public List<String> getSenaraikodKadarPremium() {
        return senaraikodKadarPremium;
    }

    public void setSenaraikodKadarPremium(List<String> senaraikodKadarPremium) {
        this.senaraikodKadarPremium = senaraikodKadarPremium;
    }

    public String getKandunganTujuan() {
        return kandunganTujuan;
    }

    public void setKandunganTujuan(String kandunganTujuan) {
        this.kandunganTujuan = kandunganTujuan;
    }

    public String getKandunganPerihalPermohonan() {
        return kandunganPerihalPermohonan;
    }

    public void setKandunganPerihalPermohonan(String kandunganPerihalPermohonan) {
        this.kandunganPerihalPermohonan = kandunganPerihalPermohonan;
    }

    public String getKandunganPerihalTanah1() {
        return kandunganPerihalTanah1;
    }

    public void setKandunganPerihalTanah1(String kandunganPerihalTanah1) {
        this.kandunganPerihalTanah1 = kandunganPerihalTanah1;
    }

    public String getKandunganPerihalTanah2() {
        return kandunganPerihalTanah2;
    }

    public void setKandunganPerihalTanah2(String kandunganPerihalTanah2) {
        this.kandunganPerihalTanah2 = kandunganPerihalTanah2;
    }

    public String getKandunganPerihalTanah3() {
        return kandunganPerihalTanah3;
    }

    public void setKandunganPerihalTanah3(String kandunganPerihalTanah3) {
        this.kandunganPerihalTanah3 = kandunganPerihalTanah3;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    public String getFixStageId() {
        return fixStageId;
    }

    public void setFixStageId(String fixStageId) {
        this.fixStageId = fixStageId;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganKeputusanJktd() {
        return senaraiKandunganKeputusanJktd;
    }

    public void setSenaraiKandunganKeputusanJktd(List<PermohonanKertasKandungan> senaraiKandunganKeputusanJktd) {
        this.senaraiKandunganKeputusanJktd = senaraiKandunganKeputusanJktd;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganHuraianPtg() {
        return senaraiKandunganHuraianPtg;
    }

    public void setSenaraiKandunganHuraianPtg(List<PermohonanKertasKandungan> senaraiKandunganHuraianPtg) {
        this.senaraiKandunganHuraianPtg = senaraiKandunganHuraianPtg;
    }

    public boolean isKeputusanJktd() {
        return keputusanJktd;
    }

    public void setKeputusanJktd(boolean keputusanJktd) {
        this.keputusanJktd = keputusanJktd;
    }

    public PermohonanKertas getPermohonanKertasJktd() {
        return permohonanKertasJktd;
    }

    public void setPermohonanKertasJktd(PermohonanKertas permohonanKertasJktd) {
        this.permohonanKertasJktd = permohonanKertasJktd;
    }

    public FasaPermohonan getFasaPermohonanKertasJktd() {
        return fasaPermohonanKertasJktd;
    }

    public void setFasaPermohonanKertasJktd(FasaPermohonan fasaPermohonanKertasJktd) {
        this.fasaPermohonanKertasJktd = fasaPermohonanKertasJktd;
    }

    public List<DisPemilikanTanah> getListDisPemilikanTanah() {
        return listDisPemilikanTanah;
    }

    public void setListDisPemilikanTanah(List<DisPemilikanTanah> listDisPemilikanTanah) {
        this.listDisPemilikanTanah = listDisPemilikanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public List<PemohonHubungan> getListPemohonHubungan() {
        return listPemohonHubungan;
    }

    public void setListPemohonHubungan(List<PemohonHubungan> listPemohonHubungan) {
        this.listPemohonHubungan = listPemohonHubungan;
    }

    public boolean isKelompok() {
        return kelompok;
    }

    public void setKelompok(boolean kelompok) {
        this.kelompok = kelompok;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTiadaKeputusan() {
        return senaraiHakmilikTiadaKeputusan;
    }

    public void setSenaraiHakmilikTiadaKeputusan(List<HakmilikPermohonan> senaraiHakmilikTiadaKeputusan) {
        this.senaraiHakmilikTiadaKeputusan = senaraiHakmilikTiadaKeputusan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTolak() {
        return senaraiHakmilikTolak;
    }

    public void setSenaraiHakmilikTolak(List<HakmilikPermohonan> senaraiHakmilikTolak) {
        this.senaraiHakmilikTolak = senaraiHakmilikTolak;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikLulus() {
        return senaraiHakmilikLulus;
    }

    public void setSenaraiHakmilikLulus(List<HakmilikPermohonan> senaraiHakmilikLulus) {
        this.senaraiHakmilikLulus = senaraiHakmilikLulus;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanLain() {
        return senaraiUlasanLain;
    }

    public void setSenaraiUlasanLain(List<PermohonanRujukanLuar> senaraiUlasanLain) {
        this.senaraiUlasanLain = senaraiUlasanLain;
    }

    public PermohonanRujukanLuar getSenaraiUlasanPengawal() {
        return senaraiUlasanPengawal;
    }

    public void setSenaraiUlasanPengawal(PermohonanRujukanLuar senaraiUlasanPengawal) {
        this.senaraiUlasanPengawal = senaraiUlasanPengawal;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganPemohon() {
        return senaraiKandunganPemohon;
    }

    public void setSenaraiKandunganPemohon(List<PermohonanKertasKandungan> senaraiKandunganPemohon) {
        this.senaraiKandunganPemohon = senaraiKandunganPemohon;
    }

    public String getKandunganPerihalPemohon() {
        return kandunganPerihalPemohon;
    }

    public void setKandunganPerihalPemohon(String kandunganPerihalPemohon) {
        this.kandunganPerihalPemohon = kandunganPerihalPemohon;
    }

    public PermohonanPermitItem getPermohonanPermitItem() {
        return permohonanPermitItem;
    }

    public void setPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        this.permohonanPermitItem = permohonanPermitItem;
    }

    public DisPermohonanBahanBatu getDisPermohonanBahanBatu() {
        return disPermohonanBahanBatu;
    }

    public void setDisPermohonanBahanBatu(DisPermohonanBahanBatu disPermohonanBahanBatu) {
        this.disPermohonanBahanBatu = disPermohonanBahanBatu;
    }

    public PermohonanRujukanLuar getMohonRujLuarJKM() {
        return mohonRujLuarJKM;
    }

    public void setMohonRujLuarJKM(PermohonanRujukanLuar mohonRujLuarJKM) {
        this.mohonRujLuarJKM = mohonRujLuarJKM;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanUlas() {
        return senaraiLaporanUlas;
    }

    public void setSenaraiLaporanUlas(List<PermohonanLaporanUlasan> senaraiLaporanUlas) {
        this.senaraiLaporanUlas = senaraiLaporanUlas;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<PenempatanPeserta> getPenempatanPesertaList() {
        return penempatanPesertaList;
    }

    public void setPenempatanPesertaList(List<PenempatanPeserta> penempatanPesertaList) {
        this.penempatanPesertaList = penempatanPesertaList;
    }
}

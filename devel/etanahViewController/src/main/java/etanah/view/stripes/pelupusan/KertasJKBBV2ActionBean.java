/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
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
import etanah.model.NoPt;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanPermitItem;
import org.apache.log4j.Logger;
import java.text.ParseException;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
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


/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/kertas_JKBBV2")
public class KertasJKBBV2ActionBean extends AbleActionBean {

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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
    etanahActionBeanContext ctx;
    private PermohonanRujukanLuar senaraiUlasanPengawal;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal;
    private List<PermohonanRujukanLuar> senaraiUlasanAdun;
    private List<PermohonanRujukanLuar> senaraiUlasanLain;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
//    private List<PermohonanKertasKandungan> senaraiKandunganTajuk;  //Bil - 0
    private List<PermohonanKertasKandungan> senaraiKandunganTujuan; //Bil - 1
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalPermohonan; //Bil - 2
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalTanah; //Bil - 3
    private List<PermohonanKertasKandungan> senaraiKandunganPemohon; // Bil - 4
    private List<PermohonanKertasKandungan> senaraiKandunganAsas; // Bil - 5
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtd; // Bil - 6
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtg; // Bil - 7
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPegawaiTertinggi; // Bil - 8
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
    private List<PermohonanBahanBatuan> senaraiBahanBatu;
    private PermohonanBahanBatuan syaratBahanBatu;
    private static final Logger LOG = Logger.getLogger(KertasJKBBV2ActionBean.class);

    @DefaultHandler
    public Resolution editOnlyKertasMMKPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPT();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");

    }

    public Resolution viewOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution editOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution editOnlyKertasMMKPTG() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution editOnlyKertasMMKPTGMLK() {
//        keputusanJktd = Boolean.TRUE;
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTGMLK();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyKertasMMKPTG() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKJKBB_MAIN_PAGE()).addParameter("tab", "true");
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
        pkk.setBil(4);
        pkk.setSubtajuk("1");
        pkk.setCawangan(cawangan);
        pkk.setKertas(permohonanKertas);
        pkk.setKandungan(kand);
        disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
        LOG.info("Saving Default Object Kertas Kandungan Perihal Pemohon");
    }

    public String setDefaultKandunganTajukKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri, List<PermohonanBahanBatuan> senaraiBatuan, List senaraiMohonPermitItem) {
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
            luasTerlibat = hp.getLuasDiluluskan() != null ? hp.getLuasDiluluskan() : hp.getLuasTerlibat();
            unitLuas = hp.getLuasLulusUom() != null ? hp.getLuasLulusUom().getNama() : hp.getKodUnitLuas().getNama();
            lot = hp.getLot() != null ? hp.getLot().getNama() + " " + hp.getNoLot() : null;
            bpm = hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : null;
            tempohPajakan = hp.getTempohPajakan() != null ? hp.getTempohPajakan() : new Integer(0);
        }
        String daerah = p.getCawangan() != null ? p.getCawangan().getDaerah().getNama() : null;
        String tujuan = p.getSebab() != null ? p.getSebab() : null;

        String permitItem = new String();
        String jenisBahanBatu = new String();
        for (int i = 0; i < senaraiMohonPermitItem.size(); i++) {
            PermohonanPermitItem mohonPermitItem = new PermohonanPermitItem();
            mohonPermitItem = (PermohonanPermitItem) senaraiMohonPermitItem.get(i);
            if (senaraiMohonPermitItem.size() == 1) {
                permitItem = mohonPermitItem.getKodItemPermit().getNama();
            } else {
                if (i != senaraiMohonPermitItem.size() - 1) {
                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " / ";
                } else {
                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " iaitu ";
                }
            }
        }

        PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
        if (senaraiMohonPermitItem.size() == 1) {
            jenisBahanBatu = mohonBahanBatuan.getJenisBahanBatu().getNama();
        } else {
            jenisBahanBatu = jenisBahanBatu + mohonBahanBatuan.getJenisBahanBatu().getNama() + " ";
        }
        kandunganTajuk = "PERMOHONAN UNTUK MENDAPATKAN PERMIT ";

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 1
                : p.getKodUrusan().getKod().equals("PBPTG") ? 1
                : p.getKodUrusan().getKod().equals("PBMMK") ? 1
                : 0;
        switch (typeNum) {
            case 1: //Urusan PPTR

                if (permitItem != null) {
                    kandunganTajuk += permitItem.toUpperCase();
                    kandunganTajuk += " ";
                }
                if (jenisBahanBatu != null) {
                    kandunganTajuk += jenisBahanBatu.toUpperCase();
                }
                if (mohonBahanBatuan != null) {
                    kandunganTajuk += " SEBANYAK ";
                    kandunganTajuk += mohonBahanBatuan.getJumlahIsipaduDipohon();
                    kandunganTajuk += " ";
                    kandunganTajuk += mohonBahanBatuan.getJumlahIsipaduDipohonUom().getNama().toUpperCase();
                }
                if (namaPemohon != null) {
                    kandunganTajuk += " DARIPADA ";
                    kandunganTajuk += namaPemohon.toUpperCase();
                }

                if (lot != null) {
                    kandunganTajuk += " DI ATAS ";
                    kandunganTajuk += lot.toUpperCase();
                }
                if (bpm != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += bpm.toUpperCase();
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah.toUpperCase();
                }
                if (tujuan != null) {
                    kandunganTajuk += " UNTUK TUJUAN ";
                    kandunganTajuk += tujuan.toUpperCase();
                }

                break;
            default:
                break;
        }

        return kandunganTajuk;
    }

    public String setDefaultKandunganTujuanKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri, List<PermohonanBahanBatuan> senaraiBatuan, List senaraiMohonPermitItem) {
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
        String jenisBahanBatu = new String();
        for (int i = 0; i < senaraiMohonPermitItem.size(); i++) {
            PermohonanPermitItem mohonPermitItem = new PermohonanPermitItem();
            mohonPermitItem = (PermohonanPermitItem) senaraiMohonPermitItem.get(i);
            if (senaraiMohonPermitItem.size() == 1) {
                permitItem = mohonPermitItem.getKodItemPermit().getNama();
            } else {
                if (i != senaraiMohonPermitItem.size() - 1) {
                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " / ";
                } else {
                    permitItem = permitItem + mohonPermitItem.getKodItemPermit().getNama() + " iaitu ";
                }
            }
        }

        PermohonanBahanBatuan mohonBahanBatuan = (PermohonanBahanBatuan) senaraiBahanBatu.get(0);
        String tempohUnit = new String();
        tempohUnit = mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("B") ? "Bulan" : mohonBahanBatuan.getUnitTempohKeluar().getKod().equalsIgnoreCase("T") ? "Tahun" : "Hari";
        if (senaraiMohonPermitItem.size() == 1) {
            jenisBahanBatu = mohonBahanBatuan.getJenisBahanBatu().getNama();
        } else {
            jenisBahanBatu = jenisBahanBatu + mohonBahanBatuan.getJenisBahanBatu().getNama() + " ";
        }

        kandunganTujuan = "Tujuan rencana ini adalah untuk mendapatkan pertimbangan ";
        if (p.getKodUrusan().getKod().equals("PPBB")) {
            kandunganTujuan += "Mesyuarat Jawatankuasa Belah Bahagi Negeri Melaka";
        } else if (p.getKodUrusan().getKod().equals("PBPTD")) {
            kandunganTujuan += "Pentadbir Tanah Daerah ";
            kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
        } else if (p.getKodUrusan().getKod().equals("PBPTG")) {
            if (kodNegeri.equals("04")) {
                kandunganTujuan += "Yang Berbahagia Pengarah Tanah dan Galian Negeri Melaka";
            } else {
                kandunganTujuan += "Pengarah Tanah dan Galian, Negeri Sembilan";
            }
        } else if (p.getKodUrusan().getKod().equals("PBMMK")) {
            kandunganTujuan += "Majlis Mesyuarat Kerajaan Negeri Sembilan";
        }

        kandunganTujuan += " mengenai permohonan ";

        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 1
                : p.getKodUrusan().getKod().equals("PBPTG") ? 1
                : p.getKodUrusan().getKod().equals("PBMMK") ? 1
                : 0;
        switch (typeNum) {
            case 1: //Urusan PBMT
                kandunganTujuan += "untuk mendapatkan permit mengeluarkan ";

                if (jenisBahanBatu != null) {
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(jenisBahanBatu);
                }
                if (mohonBahanBatuan != null) {
                    kandunganTujuan += " sebanyak ";
                    kandunganTujuan += mohonBahanBatuan.getJumlahIsipaduDipohon();
                    kandunganTujuan += " ";
                    kandunganTujuan += mohonBahanBatuan.getJumlahIsipaduDipohonUom().getNama();
                }
                if (tempohUnit != null) {
                    kandunganTujuan += " pada tempoh ";
                    kandunganTujuan += mohonBahanBatuan.getTempohKeluar();
                    kandunganTujuan += " ";
                    kandunganTujuan += tempohUnit;
                }
                if (namaPemohon != null) {
                    kandunganTujuan += " daripada ";
                    kandunganTujuan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(namaPemohon);
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
                    kandunganTujuan += ".";
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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = p.getInfoAudit() != null ? p.getInfoAudit().getTarikhMasuk() : null;


        kandunganPermohonan = "Pentadbir Tanah Daerah ";
        if (daerah != null) {
            kandunganPermohonan += disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(daerah);
        }



        int typeNum = p.getKodUrusan().getKod().equals("PPBB") ? 1
                : p.getKodUrusan().getKod().equals("PBPTD") ? 1
                : p.getKodUrusan().getKod().equals("PBPTG") ? 1
                : p.getKodUrusan().getKod().equals("PBMMK") ? 1
                : 0;
        switch (typeNum) {
            case 1: //Urusan Batuan
                kandunganPermohonan += " telah menerima permohonan untuk mendapatkan permit bahan batuan daripada ";

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
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanKandungan", "!deleteRow"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

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

                LOG.info("listPemohon.size():" + listPemohon.size());
                LOG.info("listPemohonHubungan.size():" + listPemohonHubungan.size());

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
             * Permohonan Batuan
             */
            senaraiBahanBatu = disLaporanTanahService.getPelupusanService().findPermohonanBahanBatuanByIdMohonList(idPermohonan);
            syaratBahanBatu = disLaporanTanahService.getPelupusanService().findPermohonanBahanBatuanByIdMohon(idPermohonan);
            List senaraiMohonPermitItem = new Vector();
            senaraiMohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohonList(idPermohonan);

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
                    senaraiKandunganAsas = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                    senaraiKandunganPerakuanPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 6);
                    senaraiKandunganPerakuanPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 7);
                    senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                    senaraiLaporanKandunganPerakuanPegawaiTertinggi = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 8);
                } else {
                    if (hakmilikPermohonan != null) {
                        if (kelompok) {
                            kandunganTajuk = setDefaultKandunganTajukKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                            kandunganTujuan = setDefaultKandunganTujuanKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                            kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertasKelompok(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, kodNegeri, senaraiKelompok.get(0), senaraiKelompok.size());
                        } else {
                            kandunganTajuk = setDefaultKandunganTajukKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri, senaraiBahanBatu, senaraiMohonPermitItem);
                            kandunganTujuan = setDefaultKandunganTujuanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri, senaraiBahanBatu, senaraiMohonPermitItem);
                            kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
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

                        setObjectKertasKandunganPerihalPemohon(kandunganPerihalPemohon, permohonanKertas);
//                        setObjectKertasKandunganKeputusanPTG(permohonanKertas);


                    }
                    senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                    senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                    senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                    senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
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

            /*
             * MOHON BAHAN BATU
             * PPBB, PBPTD, PBPTG ,LPSP
             */
            if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("LPSP") || permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                disPermohonanBahanBatu = new DisPermohonanBahanBatu();
                disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
                //FOR JUMLAH KENE BAYAR IN SYARAT-SYARAT
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
                if (hakmilikPermohonan.getKodMilik().getKod().equals('K') || hakmilikPermohonan.getKodMilik().getKod().equals('R') || hakmilikPermohonan.getKodMilik().getKod().equals('L')) {
                    if (disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BG") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("PS")
                            || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TL") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("RP")
                            || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("TM") || disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getKod().equals("BT")) {
                        if (disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu() != null) {
                            disPermohonanBahanBatu.setJumlahKeneBayar(disPermohonanBahanBatu.getSyaratBahanBatu().getJenisBahanBatu().getRoyaltiTanahKerajaan().multiply(disPermohonanBahanBatu.getSyaratBahanBatu().getJumlahIsipadu()).toString());
                            disPermohonanBahanBatu.setCagarKeneBayar(0.2 * Double.parseDouble(disPermohonanBahanBatu.getJumlahKeneBayar()));
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
                disPermohonanBahanBatu = new DisPermohonanBahanBatu();
                disPermohonanBahanBatu.setSyaratBahanBatu(disLaporanTanahService.getPlpservice().findPermohonanBahanBatuanByIdMohon(idPermohonan));
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
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
//        permohonanKertas = new PermohonanKertas();
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        String forwardJSP = new String();
        int typeNum = type.equals("kTajuk") ? 0
                : type.equals("kTujuan") ? 1
                : type.equals("kPermohonan") ? 2
                : type.equals("kTanah") ? 3
                : type.equals("kPemohon") ? 4
                : type.equals("kAsas") ? 5
                : type.equals("kPerakuanPtd") ? 6
                : type.equals("kPerakuanPtg") ? 7
                : type.equals("kPTertinggi") ? 8
                : type.equals("main") ? 10
                : 11;

        switch (typeNum) {
            case 0:
                senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                if (!senaraiKandunganTemp.isEmpty()) {
                    kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                }
                forwardJSP = DisPermohonanPage.getKJKBB_TAJUK();
                break;
            case 1:
                senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                forwardJSP = DisPermohonanPage.getKJKBB_TUJUAN();
                break;
            case 2:
                senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_PERMOHONAN();
                break;
            case 3:
                senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_TANAH();
                break;
            case 4:
                senaraiKandunganPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_PEMOHON();
                break;
            case 5:
                senaraiKandunganAsas = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                forwardJSP = DisPermohonanPage.getKJKBB_ASAS_PERTIMBANGAN();
                break;
            case 6:
                senaraiKandunganPerakuanPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 6);
                fixStageId = getStageByUrusan(permohonan.getKodUrusan().getKod(), kodNegeri);
                fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, fixStageId);
                amnt = checkPermohonanTuntutanKos(permohonan);
                if (fasaPermohonan != null) {
                    ksn = fasaPermohonan.getKeputusan() != null ? fasaPermohonan.getKeputusan().getKod() : null;
                }
                if (hakmilikPermohonan != null) {
                    idMh = String.valueOf(hakmilikPermohonan.getId());
                }
                forwardJSP = DisPermohonanPage.getKJKBB_PERAKUAN_PTD();
                break;
            case 7:
                senaraiKandunganPerakuanPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 7);
                forwardJSP = DisPermohonanPage.getKJKBB_PERAKUAN_PTG();
                break;
            case 8:
                senaraiLaporanKandunganPerakuanPegawaiTertinggi = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 8);
                forwardJSP = DisPermohonanPage.getKJKBB_PENOLONG_PEGAWAI();
                break;
            case 10:
                rehydrate();
                disKertasMMKController = (DisKertasMMKController) getContext().getRequest().getSession().getAttribute("disKertasMMKController");
                forwardJSP = DisPermohonanPage.getKJKBB_MAIN_PAGE();

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
        int typeUrusan = urusan.equals("PBPTD") ? 1
                : urusan.equals("PBPTG") ? 2
                : urusan.equals("PPBB") ? 3
                : 0;

        switch (typeUrusan) {
            case 1: //PBPTD
                if (kodNegeri.equals("04")) {
                    kodDok = "JKBB";
                } else {
                    kodDok = "JKBB";
                }
                break;
            case 2: //PBPTG
                if (kodNegeri.equals("04")) {
                    kodDok = "JKBB";
                } else {
                    kodDok = "JKBB";
                }
                break;
            case 3: //PPBB
                if (kodNegeri.equals("04")) {
                    kodDok = "JKBB";
                } else {
                    kodDok = "JKBB";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    kodDok = "JKBB";
                } else {
                    kodDok = "JKBB";
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
        return new JSP(DisPermohonanPage.getKMMK_PERAKUAN_PTD()).addParameter("tab", Boolean.TRUE);
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
        return new JSP(DisPermohonanPage.getKMMK_PERAKUAN_PTD()).addParameter("tab", Boolean.TRUE);
    }

    public Resolution simpanKandungan() throws ParseException {

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
                forwardJSP = DisPermohonanPage.getKJKBB_TAJUK();
                updateKandungan(kandunganTajuk, "kTajuk", permohonanKertas, 0);
                break;
            case 1:
                forwardJSP = DisPermohonanPage.getKJKBB_TUJUAN();
                updateKandungan(kand, "kTujuan", permohonanKertas, 1);
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_PERMOHONAN();
                updateKandungan(kand, "kPermohonan", permohonanKertas, 2);
                break;
            case 3:
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_TANAH();
                updateKandungan(kand, "kTanah", permohonanKertas, 3);
                break;
            case 4:
                forwardJSP = DisPermohonanPage.getKJKBB_PERIHAL_PEMOHON();
                updateKandungan(kand, "kPemohon", permohonanKertas, 4);
                break;
            case 5:
                forwardJSP = DisPermohonanPage.getKJKBB_ASAS_PERTIMBANGAN();
                updateKandungan(kand, "kAsas", permohonanKertas, 5);
                break;
            case 6:
                forwardJSP = DisPermohonanPage.getKJKBB_PERAKUAN_PTD();
                updateKandungan(kand, "kPerakuanPtd", permohonanKertas, 6);
                updateMohonFasa();
                if (kodNegeri.equals("05")) {
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn == null) {
                        ksn = getContext().getRequest().getParameter("kodksn");
                        if (ksn != null) {
                            if (ksn.equals("SL")) {
//                                updateMohonHakmilik();
                                updateBahanBatu();
                                if (!kelompok) {
                                    updateMohonTuntutKos();
                                }
                            }
                        }
                    } else {
                        if (ksn.equals("SL")) {
//                            updateMohonHakmilik();
                            updateBahanBatu();
                            if (!kelompok) {
                                updateMohonTuntutKos();
                            }

                        }
                    }
                } else {
                    ksn = ctx.getRequest().getParameter("syorKpsn");
                    if (ksn == null) {
                        ksn = getContext().getRequest().getParameter("kodksn");
                        if (ksn != null) {
                            if (ksn.equals("SL")) {
//                                updateMohonHakmilik();
                                updateBahanBatu();
                                if (!kelompok) {
                                    updateMohonTuntutKos();
                                }
                            }
                        }
                    } else {
                        if (ksn.equals("SL")) {
//                            updateMohonHakmilik();
                            updateBahanBatu();
                            if (!kelompok) {
                                updateMohonTuntutKos();
                            }

                        }
                    }
                }
                break;
            case 7:
                forwardJSP = DisPermohonanPage.getKJKBB_PERAKUAN_PTG();
                updateKandungan(kand, "kPerakuanPtg", permohonanKertas, 7);
                break;
            case 8:
                forwardJSP = DisPermohonanPage.getKJKBB_PENOLONG_PEGAWAI();
                updateKandungan(kand, "kPTertinggi", permohonanKertas, 8);
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

        kodHakmilik = getContext().getRequest().getParameter("kodHakmilik");
        if (!StringUtils.isEmpty(kodHakmilik)) {
            KodHakmilik khm = disLaporanTanahService.getKodHakmilikDAO().findById(kodHakmilik);
            hakmilikPermohonanSave.setKodHakmilik(khm);
            if (kodHakmilik.equals("PM")) {
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

        if (!StringUtils.isEmpty(hakmilikPermohonan.getKeteranganCukaiBaru())) {
            hakmilikPermohonanSave.setKeteranganCukaiBaru(hakmilikPermohonan.getKeteranganCukaiBaru());
        }
        if (!StringUtils.isEmpty(hakmilikPermohonan.getAgensiUpahUkur())) {
            hakmilikPermohonanSave.setAgensiUpahUkur(hakmilikPermohonan.getAgensiUpahUkur());
        }
        if (hakmilikPermohonan.getLuasTerlibat() != null && !StringUtils.isEmpty(hakmilikPermohonan.getLuasTerlibat().toString())) {
            hakmilikPermohonanSave.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
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
        }
        disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(hakmilikPermohonanSave);


        return updateDB;
    }

    public void updateMohonTuntutKos() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
        HakmilikPermohonan mohonHM = new HakmilikPermohonan();

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PPBB") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 1
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 1
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 2
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 3
                //                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 5
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 6
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
                forwardJSP = refreshData("kPemohon");
                break;
            case 5:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kAsas");
                break;
            case 6:
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
            case 7:
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
            case 8:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kPTertinggi");
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
        int typeUrusan = urusan.equals("PBPTD") ? 1
                : urusan.equals("PBPTG") ? 2
                : urusan.equals("PPBB") ? 3
                : 0;

        switch (typeUrusan) {
            case 1: //PBPTD
                if (kodNegeri.equals("04")) {
                    stage = "sedia_draf_ptd";
                } else {
                    stage = "sedia_draf_ptd";
                }
                break;
            case 2: //PBPTG
                if (kodNegeri.equals("04")) {
                    stage = "sedia_draf_ptg";
                } else {
                    stage = "sedia_draf_ptg";
                }
                break;
            case 3: //PPBB
                if (kodNegeri.equals("04")) {
                    stage = "sedia_draf_jkbb";
                } else {
                    stage = "sedia_draf_jkbb";
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

    public void updateBahanBatu() {
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanBahanBatuan mohonBahanBatuSave = new PermohonanBahanBatuan();
        mohonBahanBatuSave = (PermohonanBahanBatuan) disLaporanTanahService.findObject(mohonBahanBatuSave, new String[]{idPermohonan}, 0);
        if (mohonBahanBatuSave != null) {
            mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "update", pengguna));
        } else {
            cawangan = new KodCawangan();
            cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
            HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
            NoPt noPt = new NoPt();

            mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);

            mohonBahanBatuSave = new PermohonanBahanBatuan();
            mohonBahanBatuSave.setInfoAudit(disLaporanTanahService.findAudit(mohonBahanBatuSave, "new", pengguna));
            mohonBahanBatuSave.setPermohonan(permohonan);
            if (mohonHakmilik != null) {
                mohonBahanBatuSave.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
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
                mohonBahanBatuSave.setTempohSyorUom(disLaporanTanahService.getKodUOMDAO().findById(stringUnitTempohUOM));
            }

        }

        disLaporanTanahService.getPlpservice().simpanPermohonanBahanBatuan(mohonBahanBatuSave);

    }

    public BigDecimal checkPermohonanTuntutanKos(Permohonan p) {
        BigDecimal amaun = new BigDecimal(0);
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
            case 9: //Urusan PPTR
                PermohonanTuntutanKos mohonTuntutKos = disLaporanTanahService.getPlpservice().findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISB4E");
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

    public List<PermohonanKertasKandungan> getSenaraiKandunganAsas() {
        return senaraiKandunganAsas;
    }

    public void setSenaraiKandunganAsas(List<PermohonanKertasKandungan> senaraiKandunganAsas) {
        this.senaraiKandunganAsas = senaraiKandunganAsas;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPegawaiTertinggi() {
        return senaraiLaporanKandunganPerakuanPegawaiTertinggi;
    }

    public void setSenaraiLaporanKandunganPerakuanPegawaiTertinggi(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPegawaiTertinggi) {
        this.senaraiLaporanKandunganPerakuanPegawaiTertinggi = senaraiLaporanKandunganPerakuanPegawaiTertinggi;
    }

    public PermohonanBahanBatuan getSyaratBahanBatu() {
        return syaratBahanBatu;
    }

    public void setSyaratBahanBatu(PermohonanBahanBatuan syaratBahanBatu) {
        this.syaratBahanBatu = syaratBahanBatu;
    }

    public DisPermohonanBahanBatu getDisPermohonanBahanBatu() {
        return disPermohonanBahanBatu;
    }

    public void setDisPermohonanBahanBatu(DisPermohonanBahanBatu disPermohonanBahanBatu) {
        this.disPermohonanBahanBatu = disPermohonanBahanBatu;
    }

    public List<PermohonanBahanBatuan> getSenaraiBahanBatu() {
        return senaraiBahanBatu;
    }

    public void setSenaraiBahanBatu(List<PermohonanBahanBatuan> senaraiBahanBatu) {
        this.senaraiBahanBatu = senaraiBahanBatu;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.model.Akaun;
import etanah.model.Transaksi;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
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
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import etanah.util.FileUtil;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
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
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;

@UrlBinding("/hasil/kertas_risalat")
/**
 *
 * @author khadijah
 */
public class KertasRisalatMMKNActionBean extends AbleActionBean {

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
    DisKertasMMKController disKertasMMKController;
    private DisSyaratSekatan disSyaratSekatan;
    private DisPermohonanBahanBatu disPermohonanBahanBatu;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private String kawasanAdun;
    private String defaultPerihalSempadan;
    private String defaultHakmilikBerdaftar;
    private String defaultPerihalPemohon;
    private String defaultKandunganTajuk;
    private String kandunganTujuan;
    private String kandunganPerihalPermohonan;
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
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    private int tahun = 0;
    private int min = 0;
    private int max = 0;
    private BigDecimal amnt;
    private BigDecimal tunggakan = BigDecimal.ZERO;
    private BigDecimal dendaLewat = BigDecimal.ZERO;
    private BigDecimal baki = BigDecimal.ZERO;
    private BigDecimal dendaSemasa = BigDecimal.ZERO;
    private BigDecimal jumlahLewatBayar = BigDecimal.ZERO;
    private Pengguna peng;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodCawangan cawangan;
    private FasaPermohonan fasaPermohonan;
    etanahActionBeanContext ctx;
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal;
    private List<PermohonanRujukanLuar> senaraiUlasanAdun;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
//    private List<PermohonanKertasKandungan> senaraiKandunganTajuk;  //Bil - 0
    private List<PermohonanKertasKandungan> senaraiKandunganTujuan; //Bil - 1
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalPermohonan; //Bil - 2
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalTanah; //Bil - 3
    private List<PermohonanKertasKandungan> senaraiKandunganHuraianPtd; // Bil - 4
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtd; // Bil - 5
    private List<PermohonanKertasKandungan> senaraiKandunganPerakuanPtg; // Bil - 6
    private List<PermohonanKertasKandungan> senaraiKandunganKeputusan; // Bil - 7
    private List<PermohonanKertasKandungan> senaraiKandunganPerihalPemohon; // Bil - 9
    private List<PermohonanKertasKandungan> senaraiUlasanJab; // Bil- 10
    private List<PermohonanKertasKandungan> senaraiKandunganTemp;
    private List<HakmilikPermohonan> senaraiMohonHakmilik;
    private List<LaporanTanah> senaraiLaporanTanah;
    private List<LaporanTanahSempadan> laporTanahSempadanList;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakList;
    private List<Akaun> akaunList;
    private List<Transaksi> transList;
    private List<KodKegunaanTanah> listKodGunaTanahByKatTanah;
    private List<String> senaraikodKadarPremium;
    private static final Logger LOG = Logger.getLogger(KertasRisalatMMKNActionBean.class);
    private BigDecimal cukaiSetahun = new BigDecimal(0.00);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution editOnlyKertasMMKPT() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPT();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP("hasil/kertas_risalat.jsp").addParameter("tab", "true");


    }

    public Resolution viewOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP("hasil/kertas_risalat.jsp").addParameter("tab", "true");
    }

    public Resolution editOnlyKertasMMKPTD() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP("hasil/kertas_risalat.jsp").addParameter("tab", "true");
    }

    public Resolution editOnlyKertasMMKPTG() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP("hasil/kertas_risalat.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnlyKertasMMKPTG() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTG();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP("hasil/kertas_risalat.jsp").addParameter("tab", "true");
    }

    public void setDefaultKandungan(String caw) {
        String daerah = new String();
        if (caw != null) {
            daerah = disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(caw);
        }
        defaultPerihalPemohon = "Senarai nama dan latar belakang pemohon adalah seperti di Lampiran A.";
        defaultPerihalSempadan = "Tanah - tanah yang bersempadan dengan tanah yang dipohon adalah seperti berikut :";
        defaultHakmilikBerdaftar = "Hakmilik berdaftar bagi hakmilik tersebut adalah seperti di bawah :";
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

    public String setDefaultKandunganTajukKertas(Permohonan p, String caw, HakmilikPermohonan hp, Pemohon pmhn, String kodNegeri) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kandunganTajuk = new String();
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
        kandunganTajuk = "PERMOHONAN RAYUAN ";

        int typeNum = p.getKodUrusan().getKod().equals("PPDL") ? 1
                : p.getKodUrusan().getKod().equals("PPRCT") ? 2
                : p.getKodUrusan().getKod().equals("RPPP") ? 3
                : p.getKodUrusan().getKod().equals("PPPT") ? 4
                : p.getKodUrusan().getKod().equals("REMRI") ? 5
                : p.getKodUrusan().getKod().equals("RCTN") ? 6
                : 0;
        switch (typeNum) {
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
                    kandunganTajuk += bpm;
                }
                if (daerah != null) {
                    kandunganTajuk += ", ";
                    kandunganTajuk += daerah;
                }
                kandunganTajuk += " DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA ";

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
        kandunganTujuan = "Tujuan kertas ini adalah untuk mendapatkan Pertimbangan Majlis Mesyuarat Kerajaan Negeri ";
        if (kodNegeri.equals("04")) {
            kandunganTujuan += "Melaka";
        } else {
            kandunganTujuan += "Sembilan";
        }
        kandunganTujuan += " mengenai permohonan ";

        int typeNum = p.getKodUrusan().getKod().equals("PPDL") ? 1
                : p.getKodUrusan().getKod().equals("PPRCT") ? 2
                : p.getKodUrusan().getKod().equals("RPPP") ? 3
                : p.getKodUrusan().getKod().equals("PPPT") ? 4
                : p.getKodUrusan().getKod().equals("REMRI") ? 5
                : p.getKodUrusan().getKod().equals("RCTN") ? 6
                : 0;
        switch (typeNum) {
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
        kandunganPermohonan += " telah menerima permohonan rayuan ";


        int typeNum = p.getKodUrusan().getKod().equals("PPDL") ? 1
                : p.getKodUrusan().getKod().equals("PPRCT") ? 2
                : p.getKodUrusan().getKod().equals("RPPP") ? 3
                : p.getKodUrusan().getKod().equals("PPPT") ? 4
                : p.getKodUrusan().getKod().equals("REMRI") ? 5
                : p.getKodUrusan().getKod().equals("RCTN") ? 6
                : 0;
        switch (typeNum) {
            case 12: //Urusan PBMT
                kandunganPermohonan += "pemberimilikan tanah kerajaan daripada ";

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
                forwardJSP = DisPermohonanPage.getKMMK_SYARATNYATA_PAGE();
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
                forwardJSP = DisPermohonanPage.getKMMK_SEKATAN_PAGE();
            }
        }
        return new JSP(forwardJSP).addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
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

        return new JSP(DisPermohonanPage.getKMMK_SYARATNYATA_PAGE()).addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
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

        return new JSP(DisPermohonanPage.getKMMK_SEKATAN_PAGE()).addParameter("popup", "true");
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

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakList(String idhakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idhakmilik);
        return q.list();
    }

    public List<Akaun> findIdHakmilikbyAkaun(String idhakmilik) {
        String queryAkaun = "SELECT ac FROM etanah.model.Akaun ac WHERE ac.hakmilik.idHakmilik = :idhakmilik";
        Session session = sessionProvider.get();
        Query qA = session.createQuery(queryAkaun);
        qA.setString("idhakmilik", idhakmilik);
        return qA.list();
    }

    public List<Transaksi> findIdHakmilikbytransaksi(String akaunDebit) {
        String queryTrans = "SELECT kt FROM etanah.model.Transaksi kt WHERE kt.akaunDebit = :akaunDebit";
        Session session = sessionProvider.get();
        Query qT = session.createQuery(queryTrans);
        qT.setString("akaunDebit", akaunDebit);
        return qT.list();
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
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan != null) {
                setDefaultKandungan(permohonan.getCawangan().getDaerah().getNama());
            }

            /**
             * Ulasan JTek
             */
            senaraiUlasanJabatanTeknikal = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK");
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
                senaraiUlasanAdun = disLaporanTanahService.getPelupusanService().findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "ADN");
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
                    senaraiKandunganPerihalPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 9);
                    senaraiUlasanJab = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);

                } else {
                    if (hakmilikPermohonan != null) {
                        kandunganTajuk = setDefaultKandunganTajukKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
                        kandunganTujuan = setDefaultKandunganTujuanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
                        kandunganPerihalPermohonan = setDefaultKandunganPerihalPermohonanKertas(permohonan, permohonan.getCawangan().getDaerah().getKod(), hakmilikPermohonan, permohonan.getSenaraiPemohon().get(0), kodNegeri);
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

                    }
                    senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                    senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                    senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                    senaraiKandunganPerihalPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 9);
                    senaraiUlasanJab = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);
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

            if (hakmilikPermohonan != null) {
                hakmilikPihakList = findHakmilikPihakList(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            }

            if (hakmilikPermohonan != null) {
                akaunList = findIdHakmilikbyAkaun(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (!akaunList.isEmpty()) {
                    Akaun a = akaunList.get(0);
                    Hakmilik hakmilik = a.getHakmilik() ;
                    cukaiSetahun = hakmilik.getCukaiSebenar();

                    transList = findIdHakmilikbytransaksi(a.getNoAkaun());

                    Date d = new Date();
                    tahun = Integer.parseInt(yy.format(d));
                    min = tahun;

                    //cukai tahunan

                   
                    for (Transaksi t : transList) {
                        if (t.getKodTransaksi().getKod().equalsIgnoreCase("61401") && t.getUntukTahun() == Calendar.getInstance().get(Calendar.YEAR)) {
                            //cukaiSetahun = t.getAmaun().toString();
//                            if (t.getUntukTahun() < min) {
//                                min = t.getUntukTahun();
//                            }
//                            max = min;
//                            if ((t.getUntukTahun() > max) && (max != tahun)) {
//                                max = t.getUntukTahun();
//                            }
                        } else if (t.getKodTransaksi().getKod().equalsIgnoreCase("61402")) {
                            tunggakan = tunggakan.add(t.getAmaun());                                                      
                        } else if (t.getKodTransaksi().getKod().equalsIgnoreCase("76152")) {
                            dendaLewat = dendaLewat.add(t.getAmaun());
                        }

                        if (t.getKodTransaksi().getKod().equalsIgnoreCase("76152") && t.getUntukTahun() == Calendar.getInstance().get(Calendar.YEAR)) {
                            
                            dendaSemasa = t.getAmaun();
                        }
                        
                        if (t.getUntukTahun() < min) {
                            min = t.getUntukTahun();
                        }
                        max = min;
                        if ((t.getUntukTahun() > max) && (max != tahun)) {
                            max = t.getUntukTahun();
                        }
                        if(tahun > max){
                            max = tahun;
                        }
                        
                    }
                    if (a.getBaki() == null) {
                        BigDecimal b = cukaiSetahun;

                        baki = b.add(tunggakan).add(dendaLewat);

                    } else {
                        baki = a.getBaki();
                    }
                    
                    jumlahLewatBayar = baki;


                    if (dendaSemasa == BigDecimal.ZERO) {

                        try {

                            Date todayDate = new Date();
                            //System.out.println("todayDate ::: " + sdf.format(todayDate));

                            String dueDate = "01/1/" + Calendar.getInstance().get(Calendar.YEAR);
                            

                            Date date1 = sdf.parse(dueDate);

                            if (todayDate.after(date1)) {

                                int i = baki.intValue();
                                if (i >= 1 && i <5) {
                                    //baki < RM5
                                    dendaSemasa = new BigDecimal(1.00);
                                } else if (i >= 5 && i <= 20) {
                                    //baki RM5.00 - RM20.00
                                    dendaSemasa = new BigDecimal(1.50);
                                } else if (i > 20 && i <= 50) {
                                    //baki RM20.01 - RM50.00
                                    dendaSemasa = new BigDecimal(5.00);
                                } else if (i > 50 && i <= 100) {
                                    //baki RM50.01 - RM100.00
                                    dendaSemasa = new BigDecimal(10.00);
                                } else if (i > 100) {
                                    //baki > RM100.01

                                    dendaSemasa = baki.multiply(new BigDecimal(0.1));
                                    BigDecimal round_up = dendaSemasa.setScale(1, BigDecimal.ROUND_UP);
                                    dendaSemasa = round_up;

                                }
                                //System.out.println(todayDate + " is after " + date1);

                            }
                        } catch (ParseException ex) {
                            java.util.logging.Logger.getLogger(KertasRisalatMMKNActionBean.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        jumlahLewatBayar = baki.add(dendaSemasa);

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
//        senaraiLaporanTanah = disLaporanTanahService.getLaporanTanahService().getListLaporanTanah(idPermohonan);
//        if (!senaraiLaporanTanah.isEmpty()) {
//            //One Laporan Tanah
//            if (senaraiLaporanTanah.size() == 1) {
//                laporanTanah = senaraiLaporanTanah.get(0);
//                laporTanahSempadanList = disLaporanTanahService.getPelupusanService().findLaporTanahSmpdnByIdLapor(laporanTanah.getIdLaporan());
//            }
//        }
        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
        rehydrate();
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = new PermohonanKertas();
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
                : type.equals("main") ? 8
                : type.equals("kPerihal") ? 9
                : type.equals("kUlasan") ? 10
                : 11;

        switch (typeNum) {
            case 0:
                senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                if (!senaraiKandunganTemp.isEmpty()) {
                    kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                }
                forwardJSP = HasilPermohonanPage.getKMMK_TAJUK();
                break;
            case 1:
                senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                forwardJSP = HasilPermohonanPage.getKMMK_TUJUAN();
                break;
            case 2:
                senaraiKandunganPerihalPermohonan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_PERMOHONAN();
                break;
            case 3:
                senaraiKandunganPerihalTanah = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_TANAH();
                break;
            case 4:
                senaraiKandunganHuraianPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                forwardJSP = HasilPermohonanPage.getKMMK_HURAIAN_PTD();
                break;
            case 5:
                senaraiKandunganPerakuanPtd = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
//                fixStageId = getStageByUrusan(permohonan.getKodUrusan().getKod(), kodNegeri);
//                fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(idPermohonan, fixStageId);
//                if (fasaPermohonan != null) {
//                    ksn = fasaPermohonan.getKeputusan() != null ? fasaPermohonan.getKeputusan().getKod() : null;
//                }
//                if (hakmilikPermohonan != null) {
//                    idMh = String.valueOf(hakmilikPermohonan.getId());
//                }
                forwardJSP = HasilPermohonanPage.getKMMK_PERAKUAN_PTD();
                break;
            case 6:
                senaraiKandunganPerakuanPtg = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 6);
                forwardJSP = HasilPermohonanPage.getKMMK_PERAKUAN_PTG();
                break;
            case 7:
                senaraiKandunganKeputusan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 7);
                forwardJSP = HasilPermohonanPage.getKMMK_KEPUTUSAN();
                break;
            case 8:

                disKertasMMKController = (DisKertasMMKController) getContext().getRequest().getSession().getAttribute("disKertasMMKController");
                rehydrate();
                LOG.info("Masuk sini - Untuk refresh");
                if (kodNegeri.equals("04")) {
                    forwardJSP = HasilPermohonanPage.getKMMK_MAIN_PAGE_MLK();
                } else {
                    forwardJSP = DisPermohonanPage.getKMMK_MAIN_PAGE_NS();
                }
                break;
            case 9:
                senaraiKandunganPerihalPemohon = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 9);
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_PEMOHON();
                break;
            case 10:
                senaraiUlasanJab = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 10);
                forwardJSP = HasilPermohonanPage.getKMMK_ULASAN_JABATAN();
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
        int typeUrusan = urusan.equals("PPDL") ? 1
                : urusan.equals("PRCT") ? 2
                : urusan.equals("RPPP") ? 3
                : urusan.equals("PPPT") ? 4
                : urusan.equals("REMRI") ? 5
                : urusan.equals("RCTN") ? 6
                : 0;

        switch (typeUrusan) {
            case 1: //PPDL
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            case 2: //PRCT
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            case 3: //RPPP
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            case 4: //PPPT
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            case 5: //REMRI
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            case 6: //RCTN
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    kodDok = "RIS";
                } else {
                    kodDok = "RIS";
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
                forwardJSP = HasilPermohonanPage.getKMMK_TAJUK();
                updateKandungan(kandunganTajuk, "kTajuk", permohonanKertas, 0);
                break;
            case 1:
                forwardJSP = HasilPermohonanPage.getKMMK_TUJUAN();
                updateKandungan(kand, "kTujuan", permohonanKertas, 1);
                break;
            case 2:
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_PERMOHONAN();
                updateKandungan(kand, "kPermohonan", permohonanKertas, 2);
                break;
            case 3:
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_TANAH();
                updateKandungan(kand, "kTanah", permohonanKertas, 3);
                break;
            case 4:
                forwardJSP = HasilPermohonanPage.getKMMK_HURAIAN_PTD();
                updateKandungan(kand, "kHuraianPtd", permohonanKertas, 4);
                break;
            case 5:
                forwardJSP = HasilPermohonanPage.getKMMK_PERAKUAN_PTD();
                updateKandungan(kand, "kPerakuanPtd", permohonanKertas, 5);
//                updateMohonHakmilik();
//                updateMohonFasa();
//                if (kodNegeri.equals("05")) {
//                    updateMohonTuntutKos();
//                }
                break;
            case 6:
                forwardJSP = HasilPermohonanPage.getKMMK_PERAKUAN_PTG();
                updateKandungan(kand, "kPerakuanPtg", permohonanKertas, 6);
                break;
            case 7:
                forwardJSP = HasilPermohonanPage.getKMMK_KEPUTUSAN();
                updateKandungan(kand, "kKeputusan", permohonanKertas, 7);
                break;
            case 9:
                forwardJSP = HasilPermohonanPage.getKMMK_PERIHAL_PEMOHON();
                updateKandungan(kand, "kPerihal", permohonanKertas, 9);
                break;
            case 10:
                forwardJSP = HasilPermohonanPage.getKMMK_ULASAN_JABATAN();
                updateKandungan(kand, "kUlasan", permohonanKertas, 10);
                break;
            default:
                break;

        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
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
        hakmilikPermohonanSave.setTempohPajakan(hakmilikPermohonan.getTempohPajakan());
        hakmilikPermohonanSave.setLuasDiluluskan(hakmilikPermohonan.getLuasDiluluskan());
        if (!permohonan.getKodUrusan().getKod().equals("PBMT")) {
            if (!StringUtils.isEmpty(kodU)) {
                KodUOM kodUOM = new KodUOM();
                kodUOM = (KodUOM) disLaporanTanahService.findObject(kodUOM, new String[]{kodU}, 0);
                hakmilikPermohonanSave.setLuasLulusUom(kodUOM);
            } else {
                addSimpleError("Sila Pilih Jenis Ukuran bagi Luas Disyorkan");
            }
        } else {
            if (hakmilikPermohonanSave.getKodUnitLuas() != null) {
                hakmilikPermohonanSave.setLuasLulusUom(hakmilikPermohonanSave.getKodUnitLuas());
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
//                ksn = getContext().getRequest().getParameter("kodksn");
//                if (ksn != null) {
//                    updateMohonFasa();
//                }
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
            case 9:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kPerihal");
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
                forwardJSP = refreshData("kUlasan");
                break;
            default:
                break;
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String getStageByUrusan(String urusan, String kodNegeri) {
        String stage = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
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
                    stage = "";
                } else {
                    stage = "terima_kpsn_jktd_sedia_mmk";
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

    public String getDefaultHakmilikBerdaftar() {
        return defaultHakmilikBerdaftar;
    }

    public void setDefaultHakmilikBerdaftar(String defaultHakmilikBerdaftar) {
        this.defaultHakmilikBerdaftar = defaultHakmilikBerdaftar;
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

    public List<PermohonanKertasKandungan> getSenaraiKandunganPerihalPemohon() {
        return senaraiKandunganPerihalPemohon;
    }

    public void setSenaraiKandunganPerihalPemohon(List<PermohonanKertasKandungan> senaraiKandunganPerihalPemohon) {
        this.senaraiKandunganPerihalPemohon = senaraiKandunganPerihalPemohon;
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

    public List<PermohonanKertasKandungan> getSenaraiUlasanJab() {
        return senaraiUlasanJab;
    }

    public void setSenaraiUlasanJab(List<PermohonanKertasKandungan> senaraiUlasanJab) {
        this.senaraiUlasanJab = senaraiUlasanJab;
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

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakList() {
        return hakmilikPihakList;
    }

    public void setHakmilikPihakList(List<HakmilikPihakBerkepentingan> hakmilikPihakList) {
        this.hakmilikPihakList = hakmilikPihakList;
    }

    public List<Akaun> getAkaunList() {
        return akaunList;
    }

    public void setAkaunList(List<Akaun> akaunList) {
        this.akaunList = akaunList;
    }

    public BigDecimal getCukaiSetahun() {
        return cukaiSetahun;
    }

    public void setCukaiSetahun(BigDecimal cukaiSetahun) {
        this.cukaiSetahun = cukaiSetahun;
    }

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public BigDecimal getDendaLewat() {
        return dendaLewat;
    }

    public void setDendaLewat(BigDecimal dendaLewat) {
        this.dendaLewat = dendaLewat;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public BigDecimal getDendaSemasa() {
        return dendaSemasa;
    }

    public void setDendaSemasa(BigDecimal dendaSemasa) {
        this.dendaSemasa = dendaSemasa;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public BigDecimal getJumlahLewatBayar() {
        return jumlahLewatBayar;
    }

    public void setJumlahLewatBayar(BigDecimal jumlahLewatBayar) {
        this.jumlahLewatBayar = jumlahLewatBayar;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodTuntut;
import etanah.model.Pemohon;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import static java.lang.Double.parseDouble;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.mapping.Map;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borang5A_MCL")
public class Borang5aMCL_ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PelupusanService plpservice;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    private Pemohon pemohon;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private String idPermohonan;
    private String premium;
    private String cukaiTahunPertama;
    private String bayaranPelan;
    private String penyediaanHMS;
    private String penyediaanHMK;
    private String pendaftaranHMS;
    private String pendaftaranHMK;
    private String notis20;
    private BigDecimal premiumRM;
    private BigDecimal cukaiTahunPertamaRM;
    private BigDecimal bayaranPelanRM;
//    private BigDecimal penyediaanHMSrm ;
//    private BigDecimal penyediaanHMKrm ;
    private BigDecimal pendaftaranHMSrm;
    private BigDecimal pendaftaranHMKrm;
    private BigDecimal notis;
    private BigDecimal jumlah;
    private double jumlah1;
    private List senaraiItem;
    private String test;
    private BigDecimal harga;
    private boolean item = false;
    private List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    private PermohonanTuntutanKos mohonTuntutKos;
    private PermohonanTuntutanKos mohonTuntutKosDEV01;
    private PermohonanTuntutanKos mohonTuntutKosDEV02;
    private PermohonanTuntutanKos mohonTuntutKosDEV04;
    private PermohonanTuntutanKos mohonTuntutKosDEV11;
    private PermohonanTuntutanKos mohonTuntutKosDEV14;
    private PermohonanTuntutanKos mohonTuntutKosDISPRM;
    private PermohonanTuntutanKos mohonTuntutKosDISTAX;
    private PermohonanTuntutanKos mohonTuntutKosDISFT;
    private PermohonanTuntutanKos mohonTuntutKosDISQT;
    private PermohonanTuntutanKos mohonTuntutKosDISPEL;
    private PermohonanTuntutanKos mohonTuntutKosDISN;
    private PermohonanTuntutanKos mohonTuntutKosDEV13;
    private Pengguna pengguna;
    private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private FasaPermohonan fasaPermohonan;
    private String KeputusanPT;
    private KodTuntut kodTuntut;
    private static final Logger LOG = Logger.getLogger(Borang5aMCL_ActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/borang5A_MCL.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String pendaftaranHMSrm1 = getContext().getRequest().getParameter("pendaftaranHMSrm");
        if (pendaftaranHMSrm1 != null) {
            pendaftaranHMSrm = new BigDecimal(pendaftaranHMSrm1);
        }
        String pendaftaranHMKrm1 = getContext().getRequest().getParameter("pendaftaranHMKrm");
        if (pendaftaranHMKrm1 != null) {
            pendaftaranHMKrm = new BigDecimal(pendaftaranHMKrm1);
        }

        String[] tvalue = {"permohonan"};
        Object[] tname = {permohonan};

        senaraiMohonTuntutKos = permohonanTuntutanKosDAO.findByEqualCriterias(tvalue, tname, null);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        KeputusanPT = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "tahun_hm_daftar").getKeputusan().getKod();
        HakmilikPermohonan hmTemp = new HakmilikPermohonan();
        if (!hakmilikPermohonanList.isEmpty()) {
            hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
        }

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        if (idhakmilikpermohonan == null) {
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
        } else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
            hakmilikPermohonan = new HakmilikPermohonan();
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        }

        if (senaraiMohonTuntutKos.isEmpty()) {

            
            getContext().getRequest().setAttribute("item", Boolean.FALSE);
            if (KeputusanPT.equals("96") || KeputusanPT.equals("95")) {
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                // Premium Tanah
                premium = "Bayaran Premium";
                premiumRM = new BigDecimal(100);
                jumlah1 = jumlah1 + premiumRM.doubleValue();
                // Hasil Tahun Pertama
                Double convert = 0.00;
                convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()); // / 2 - divide by 2 comment temporary
                cukaiTahunPertama = "Hasil Tahun Pertama";
                cukaiTahunPertamaRM = BigDecimal.valueOf(convert);
                jumlah1 = jumlah1 + cukaiTahunPertamaRM.doubleValue();
                //Pendaftaran Hakmilik Kekal atau Sementara
                hakmilik = hakmilikPermohonan.getHakmilik();
                if (hakmilik != null) {
                    if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM")) {
                        pendaftaranHMS = "Pendaftaran Hakmilik Kekal";
                        pendaftaranHMSrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                    } else if (hakmilik.getKodHakmilik().getKod().equals("PN")) {
                        pendaftaranHMS = "Pendaftaran Hakmilik Sementara";
                        pendaftaranHMSrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                    } else if (hakmilik.getKodHakmilik().getKod().equals("HSM") || hakmilik.getKodHakmilik().getKod().equals("HSD")) {
                        pendaftaranHMS = "Pendaftaran Hakmilik Sementara";
                        pendaftaranHMSrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        pendaftaranHMK = "Pendaftaran Hakmilik Kekal";
                        pendaftaranHMKrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                    } else if (hakmilik.getKodHakmilik().getKod().equals("HMM")) {
                        pendaftaranHMS = "Pendaftaran Hakmilik Sementara";
                        pendaftaranHMSrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        pendaftaranHMK = "Pendaftaran Hakmilik Kekal";
                        pendaftaranHMKrm = new BigDecimal(50);
                        jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                    }

                }
                //Pelan Geran
                bayaranPelan = "Pelan Geran";
                bayaranPelanRM = new BigDecimal(5);
                jumlah1 = jumlah1 + bayaranPelanRM.doubleValue();

                notis20 = "Notis";
                notis = new BigDecimal(20);
                jumlah1 = jumlah1 + notis.doubleValue();
            }
        } else {
            getContext().getRequest().setAttribute("item", Boolean.TRUE);
            //senaraiItem = senaraiMohonTuntutKos ;
            for (int i = 0; i < senaraiMohonTuntutKos.size(); i++) {
                this.permohonanTuntutanKos = (PermohonanTuntutanKos) senaraiMohonTuntutKos.get(i);
                //Premium Tanah
                if ((permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV04") || permohonanTuntutanKos.getKodTuntut().getKod().equals("DISPRM")) && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) { //Premium
                    premium = permohonanTuntutanKos.getItem();
                    premiumRM = permohonanTuntutanKos.getAmaunTuntutan();
                    jumlah1 = jumlah1 + premiumRM.doubleValue();
                }

                //Hasil Tahun Pertama DISTAX
                if ((permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV11") || permohonanTuntutanKos.getKodTuntut().getKod().equals("DISTAX")) && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                    cukaiTahunPertama = permohonanTuntutanKos.getItem();
                    cukaiTahunPertamaRM = permohonanTuntutanKos.getAmaunTuntutan();
                    jumlah1 = jumlah1 + cukaiTahunPertamaRM.doubleValue();
                }

                //Pendaftaran Hakmilik Kekal atau Sementara
                hakmilik = hakmilikPermohonan.getHakmilik();
                if (hakmilik != null) {
//                    if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("PN")) {
                        if ((permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV02") || permohonanTuntutanKos.getKodTuntut().getKod().equals("DISFT")) && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            pendaftaranHMK = permohonanTuntutanKos.getItem();
                            pendaftaranHMKrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                        }
//                    } else if (hakmilik.getKodHakmilik().getKod().equals("HSM") || hakmilik.getKodHakmilik().getKod().equals("PN") || hakmilik.getKodHakmilik().getKod().equals("HSD")) {
                        if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV01") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            pendaftaranHMS = permohonanTuntutanKos.getItem();
                            pendaftaranHMSrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        } else if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV02") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            pendaftaranHMK = permohonanTuntutanKos.getItem();
                            pendaftaranHMKrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                        }
//                    } else if (hakmilik.getKodHakmilik().getKod().equals("HMM")) {
                        if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV01") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            pendaftaranHMS = permohonanTuntutanKos.getItem();
                            pendaftaranHMSrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        } else if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV02") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            pendaftaranHMK = permohonanTuntutanKos.getItem();
                            pendaftaranHMKrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                        } else if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DISQT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            if (pendaftaranHMSrm == null) {
                                pendaftaranHMS = permohonanTuntutanKos.getItem();
                                pendaftaranHMSrm = permohonanTuntutanKos.getAmaunTuntutan();
                                jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                            }

                            jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        } else if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DISFT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                            if (pendaftaranHMKrm == null) {
                                pendaftaranHMK = permohonanTuntutanKos.getItem();
                                pendaftaranHMKrm = permohonanTuntutanKos.getAmaunTuntutan();
                                jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                            }
                        }
//                    }
                }
//                if (hakmilik.getKodHakmilik().getKod().equals("HSM")) {
                    LOG.info("permohonanTuntutanKos.getKodTuntut().getKod()  = " + permohonanTuntutanKos.getKodTuntut().getKod());
                    if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DISQT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                        if (pendaftaranHMSrm == null) {
                            pendaftaranHMS = permohonanTuntutanKos.getItem();
                            pendaftaranHMSrm = permohonanTuntutanKos.getAmaunTuntutan();
                            jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                        }

                        jumlah1 = jumlah1 + pendaftaranHMSrm.doubleValue();
                    } else if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DISFT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                        if (pendaftaranHMKrm == null) {
                            pendaftaranHMK = permohonanTuntutanKos.getItem();
                            pendaftaranHMKrm = permohonanTuntutanKos.getAmaunTuntutan();
                        }
                        jumlah1 = jumlah1 + pendaftaranHMKrm.doubleValue();
                    }

//                }
                //Pelan Geran
                if ((permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV14") || permohonanTuntutanKos.getKodTuntut().getKod().equals("DISPEL")) && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()) {
                    bayaranPelan = permohonanTuntutanKos.getItem();
                    bayaranPelanRM = permohonanTuntutanKos.getAmaunTuntutan();
                    jumlah1 = jumlah1 + bayaranPelanRM.doubleValue();
                }

                //Notis if alamat pemohon di melaka
                mohonTuntutKosDEV13 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV13");
                if (mohonTuntutKosDEV13 != null) {
                    if (permohonanTuntutanKos.getKodTuntut().getKod().equals("DEV13")) {
                        notis20 = permohonanTuntutanKos.getItem();
                        notis = permohonanTuntutanKos.getAmaunTuntutan();
                        jumlah1 = jumlah1 + notis.doubleValue();
                    }
                } else {
                    notis20 = "Notis";
                    notis = new BigDecimal(20);
                }
            }
//            cukaiTahunPertama = "Hasil Tahun Pertama";
//            bayaranPelan = "Pelan Geran";

        }
    }

    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/borang5A_MCL.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMohonTuntutKos() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PN") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PM")) {
            pendaftaranHMSrm = new BigDecimal(Double.parseDouble(getContext().getRequest().getParameter("pendaftaranHMSrm")));
        } else {
            pendaftaranHMKrm = new BigDecimal(Double.parseDouble(getContext().getRequest().getParameter("pendaftaranHMKrm")));
        }

//        pendaftaranHMKrm = new BigDecimal(Double.parseDouble(getContext().getRequest().getParameter("pendaftaranHMKrm")));
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tvalue = {"permohonan"};
        Object[] tname = {permohonan};

//        pemohon = plpservice.findPemohon(idPermohonan);
//        mohonTuntutKos = plpservice.findMohontuntutkos(idPermohonan);
        if (permohonan.getKodUrusan().getJabatan().getAkronim().equals("DEV")) {
            //for Pendaftaran Hakmilik Sementara
            mohonTuntutKosDEV01 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV01");
            //for Pendaftaran Hakmilik Kekal
            mohonTuntutKosDEV02 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV02");
            //for Bayaran Premium
            mohonTuntutKosDEV04 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV04");
            //for Hasil Tahun Pertama
            mohonTuntutKosDEV11 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV11");
            //for Pelan Geran
            mohonTuntutKosDEV14 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV14");

            mohonTuntutKosDEV13 = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DEV13");

            if (mohonTuntutKosDEV01 != null) {
                if (pendaftaranHMSrm != null) {
                    if (!hakmilik.getKodHakmilik().getKod().equals("GM") && !hakmilik.getKodHakmilik().getKod().equals("PM")) {
                        InfoAudit info = new InfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        mohonTuntutKosDEV01.setInfoAudit(info);
                        mohonTuntutKosDEV01.setAmaunTuntutan(pendaftaranHMSrm);
                        plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV01);
                    }
                }
            } else if (pendaftaranHMSrm != null) {
                if (!hakmilik.getKodHakmilik().getKod().equals("GM") && !hakmilik.getKodHakmilik().getKod().equals("PM")) {
                    PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    mt.setInfoAudit(info);
                    mt.setPermohonan(permohonan);
                    mt.setCawangan(permohonan.getCawangan());
                    mt.setHakmilikPermohonan(hakmilikPermohonan);
                    mt.setAmaunTuntutan(pendaftaranHMSrm);
                    if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM")) {
                        mt.setItem("Pendaftaran Hakmilik Kekal");
                        mt.setKodTuntut(kodTuntutDAO.findById("DEV02")); //DEV02
                        mt.setKodTransaksi(kodTuntutDAO.findById("DEV02").getKodKewTrans()); //72490
                    } else {
                        mt.setItem("Pendaftaran Hakmilik Sementara");
                        mt.setKodTuntut(kodTuntutDAO.findById("DEV01")); //DEV01
                        mt.setKodTransaksi(kodTuntutDAO.findById("DEV01").getKodKewTrans()); //72489
                    }

                    plpservice.simpanSavePermohonanTuntutanKos(mt);
                }
            }
            if (mohonTuntutKosDEV02 != null) {
                if (pendaftaranHMSrm != null) {
                    if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM")) {
                        InfoAudit info = new InfoAudit();
                        info.setDiKemaskiniOleh(pengguna);
                        info.setTarikhKemaskini(new java.util.Date());
                        mohonTuntutKosDEV02.setInfoAudit(info);
                        mohonTuntutKosDEV02.setAmaunTuntutan(pendaftaranHMSrm);
                        plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV02);
                    }
                }
            } else if (pendaftaranHMSrm != null) {
                if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("HSM") || hakmilik.getKodHakmilik().getKod().equals("HSD") || hakmilik.getKodHakmilik().getKod().equals("HMM")) {
                    PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    mt.setInfoAudit(info);
                    mt.setPermohonan(permohonan);
                    mt.setCawangan(permohonan.getCawangan());
                    mt.setHakmilikPermohonan(hakmilikPermohonan);
                    mt.setAmaunTuntutan(pendaftaranHMSrm);
                    if (hakmilik.getKodHakmilik().getKod().equals("GM") || hakmilik.getKodHakmilik().getKod().equals("PM") || hakmilik.getKodHakmilik().getKod().equals("HSM") || hakmilik.getKodHakmilik().getKod().equals("HSD") || hakmilik.getKodHakmilik().getKod().equals("HMM")) {

                        mt.setItem("Pendaftaran Hakmilik Kekal");
                        mt.setKodTuntut(kodTuntutDAO.findById("DEV02")); //DEV02
                        mt.setKodTransaksi(kodTuntutDAO.findById("DEV02").getKodKewTrans()); //72490
                    } else {
                        mt.setItem("Pendaftaran Hakmilik Sementara");
                        mt.setKodTuntut(kodTuntutDAO.findById("DEV01")); //DEV01
                        mt.setKodTransaksi(kodTuntutDAO.findById("DEV01").getKodKewTrans()); //72489
                    }

                    plpservice.simpanSavePermohonanTuntutanKos(mt);
                }
            }
            if (mohonTuntutKosDEV04 != null) {
                if (premiumRM != null) {
                    InfoAudit info = new InfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKosDEV04.setInfoAudit(info);
                    mohonTuntutKosDEV04.setAmaunTuntutan(premiumRM);
                    plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV04);
                }
            } else if (premiumRM != null) {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem("Bayaran Premium");
                mt.setAmaunTuntutan(premiumRM);
                mt.setKodTuntut(kodTuntutDAO.findById("DEV04")); //DEV04
                mt.setKodTransaksi(kodTuntutDAO.findById("DEV04").getKodKewTrans()); //73604
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
            if (mohonTuntutKosDEV11 != null) {
                if (cukaiTahunPertamaRM != null) {
                    InfoAudit info = new InfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKosDEV11.setInfoAudit(info);
                    mohonTuntutKosDEV11.setAmaunTuntutan(cukaiTahunPertamaRM);
                    plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV11);
                }
            } else if (cukaiTahunPertamaRM != null) {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem("Hasil Tahun Pertama");
                mt.setAmaunTuntutan(cukaiTahunPertamaRM);
                mt.setKodTuntut(kodTuntutDAO.findById("DEV11")); //DEV11
                mt.setKodTransaksi(kodTuntutDAO.findById("DEV11").getKodKewTrans()); //61403
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
            if (mohonTuntutKosDEV14 != null) {
                if (bayaranPelanRM != null) {
                    InfoAudit info = new InfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKosDEV14.setInfoAudit(info);
                    mohonTuntutKosDEV14.setAmaunTuntutan(bayaranPelanRM);
                    plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV14);
                }
            } else if (bayaranPelanRM != null) {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem("Pelan Geran");
                mt.setAmaunTuntutan(bayaranPelanRM);
                mt.setKodTuntut(kodTuntutDAO.findById("DEV14")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DEV14").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
            if (mohonTuntutKosDEV13 != null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKosDEV13.setInfoAudit(info);
                mohonTuntutKosDEV13.setAmaunTuntutan(notis);
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDEV13);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem("Notis");
                mt.setAmaunTuntutan(notis);
                mt.setKodTuntut(kodTuntutDAO.findById("DEV13")); //DEV04
                mt.setKodTransaksi(kodTuntutDAO.findById("DEV13").getKodKewTrans()); //73604
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }

        } else if (permohonan.getKodUrusan().getJabatan().getAkronim().equals("DIS")) {
            mohonTuntutKosDISPRM = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISPRM");
            mohonTuntutKosDISTAX = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISTAX");
            mohonTuntutKosDISFT = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISFT");
            mohonTuntutKosDISQT = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISQT");
            mohonTuntutKosDISPEL = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISPEL");
            mohonTuntutKosDISN = plpservice.findMohontuntutkosByIdMohonAndKodTuntut(idPermohonan, "DISN");

//            if (pendaftaranHMKrm != null) {
            if (mohonTuntutKosDISPRM != null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKosDISPRM.setInfoAudit(info);
                mohonTuntutKosDISPRM.setAmaunTuntutan(premiumRM);
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISPRM);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem(kodTuntutDAO.findById("DISPRM").getNama());
                mt.setAmaunTuntutan(premiumRM);
                mt.setKodTuntut(kodTuntutDAO.findById("DISPRM")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
//            }
            if (mohonTuntutKosDISTAX != null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKosDISTAX.setInfoAudit(info);
                mohonTuntutKosDISTAX.setAmaunTuntutan(cukaiTahunPertamaRM);
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISTAX);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem(kodTuntutDAO.findById("DISTAX").getNama());
                mt.setAmaunTuntutan(cukaiTahunPertamaRM);
                mt.setKodTuntut(kodTuntutDAO.findById("DISTAX")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
            if (mohonTuntutKosDISFT != null) {
//                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mohonTuntutKosDISFT.setInfoAudit(info);
//                mohonTuntutKosDISFT.setPermohonan(permohonan);
//                mohonTuntutKosDISFT.setCawangan(permohonan.getCawangan());
//                mohonTuntutKosDISFT.setHakmilikPermohonan(hakmilikPermohonan);
//                mohonTuntutKosDISFT.setItem("Penyediaan dan pendaftaran hakmilik tetap");
                if (pendaftaranHMKrm != null) {
                    mohonTuntutKosDISFT.setAmaunTuntutan(pendaftaranHMKrm);
                } else if (pendaftaranHMSrm != null) {
                    mohonTuntutKosDISFT.setAmaunTuntutan(pendaftaranHMSrm);
                }
                mohonTuntutKosDISFT.setKodTuntut(kodTuntutDAO.findById("DISFT")); //DEV14
                mohonTuntutKosDISFT.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISFT);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem(kodTuntutDAO.findById("DISFT").getNama());
                if (pendaftaranHMKrm != null) {
                    mt.setAmaunTuntutan(pendaftaranHMKrm);
                } 
                mt.setKodTuntut(kodTuntutDAO.findById("DISFT")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }

            //HAKMILIK SEMENTARA             
                    if (mohonTuntutKosDISQT != null) {
//                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        mohonTuntutKosDISQT.setInfoAudit(info);
//                mohonTuntutKosDISFT.setPermohonan(permohonan);
//                mohonTuntutKosDISFT.setCawangan(permohonan.getCawangan());
//                mohonTuntutKosDISFT.setHakmilikPermohonan(hakmilikPermohonan);
//                mohonTuntutKosDISFT.setItem("Penyediaan dan pendaftaran hakmilik tetap");
                        mohonTuntutKosDISQT.setAmaunTuntutan(pendaftaranHMSrm);
                        mohonTuntutKosDISQT.setKodTuntut(kodTuntutDAO.findById("DISQT")); //DEV14
                        mohonTuntutKosDISQT.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans()); //73151
                        plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISQT);
                    } else {
                        PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        mt.setInfoAudit(info);
                        mt.setPermohonan(permohonan);
                        mt.setCawangan(permohonan.getCawangan());
                        mt.setHakmilikPermohonan(hakmilikPermohonan);
                        mt.setItem(kodTuntutDAO.findById("DISQT").getNama());
                        mt.setAmaunTuntutan(pendaftaranHMSrm);
                        mt.setKodTuntut(kodTuntutDAO.findById("DISQT")); //DEV14
                        mt.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans()); //73151
                        plpservice.simpanSavePermohonanTuntutanKos(mt);
                    }


            if (mohonTuntutKosDISPEL != null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKosDISPEL.setInfoAudit(info);
                mohonTuntutKosDISPEL.setAmaunTuntutan(bayaranPelanRM);
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISPEL);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem(kodTuntutDAO.findById("DISPEL").getNama());
                mt.setAmaunTuntutan(pendaftaranHMSrm);
                mt.setKodTuntut(kodTuntutDAO.findById("DISPEL")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DISPEL").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
            if (mohonTuntutKosDISN != null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKosDISN.setInfoAudit(info);
                mohonTuntutKosDISN.setAmaunTuntutan(notis);
                plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKosDISN);
            } else {
                PermohonanTuntutanKos mt = new PermohonanTuntutanKos();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mt.setInfoAudit(info);
                mt.setPermohonan(permohonan);
                mt.setCawangan(permohonan.getCawangan());
                mt.setHakmilikPermohonan(hakmilikPermohonan);
                mt.setItem(kodTuntutDAO.findById("DISN").getNama());
                mt.setAmaunTuntutan(notis);
                mt.setKodTuntut(kodTuntutDAO.findById("DISN")); //DEV14
                mt.setKodTransaksi(kodTuntutDAO.findById("DISN").getKodKewTrans()); //73151
                plpservice.simpanSavePermohonanTuntutanKos(mt);
            }
        }

//        LOG.info("pemohon negeri - " + pemohon.getPihak().getNegeri().getKod());
        LOG.info("dah simpan");
        addSimpleMessage("Maklumat Telah Disimpan");
        return new JSP("pelupusan/borang5A_MCL.jsp").addParameter("tab", "true");

    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getBayaranPelan() {
        return bayaranPelan;
    }

    public void setBayaranPelan(String bayaranPelan) {
        this.bayaranPelan = bayaranPelan;
    }

    public String getCukaiTahunPertama() {
        return cukaiTahunPertama;
    }

    public void setCukaiTahunPertama(String cukaiTahunPertama) {
        this.cukaiTahunPertama = cukaiTahunPertama;
    }

    public String getPendaftaranHMK() {
        return pendaftaranHMK;
    }

    public void setPendaftaranHMK(String pendaftaranHMK) {
        this.pendaftaranHMK = pendaftaranHMK;
    }

    public String getPendaftaranHMS() {
        return pendaftaranHMS;
    }

    public void setPendaftaranHMS(String pendaftaranHMS) {
        this.pendaftaranHMS = pendaftaranHMS;
    }

    public String getPenyediaanHMK() {
        return penyediaanHMK;
    }

    public void setPenyediaanHMK(String penyediaanHMK) {
        this.penyediaanHMK = penyediaanHMK;
    }

    public String getPenyediaanHMS() {
        return penyediaanHMS;
    }

    public void setPenyediaanHMS(String penyediaanHMS) {
        this.penyediaanHMS = penyediaanHMS;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public BigDecimal getBayaranPelanRM() {
        return bayaranPelanRM;
    }

    public void setBayaranPelanRM(BigDecimal bayaranPelanRM) {
        this.bayaranPelanRM = bayaranPelanRM;
    }

    public BigDecimal getPendaftaranHMKrm() {
        return pendaftaranHMKrm;
    }

    public void setPendaftaranHMKrm(BigDecimal pendaftaranHMKrm) {
        this.pendaftaranHMKrm = pendaftaranHMKrm;
    }

    public BigDecimal getPendaftaranHMSrm() {
        return pendaftaranHMSrm;
    }

    public void setPendaftaranHMSrm(BigDecimal pendaftaranHMSrm) {
        this.pendaftaranHMSrm = pendaftaranHMSrm;
    }

    public BigDecimal getPremiumRM() {
        return premiumRM;
    }

    public void setPremiumRM(BigDecimal premiumRM) {
        this.premiumRM = premiumRM;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public boolean isItem() {
        return item;
    }

    public void setItem(boolean item) {
        this.item = item;
    }

    public List getSenaraiItem() {
        return senaraiItem;
    }

    public void setSenaraiItem(List senaraiItem) {
        this.senaraiItem = senaraiItem;
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getCukaiTahunPertamaRM() {
        return cukaiTahunPertamaRM;
    }

    public void setCukaiTahunPertamaRM(BigDecimal cukaiTahunPertamaRM) {
        this.cukaiTahunPertamaRM = cukaiTahunPertamaRM;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public double getJumlah1() {
        return jumlah1;
    }

    public void setJumlah1(double jumlah1) {
        this.jumlah1 = jumlah1;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV01() {
        return mohonTuntutKosDEV01;
    }

    public void setMohonTuntutKosDEV01(PermohonanTuntutanKos mohonTuntutKosDEV01) {
        this.mohonTuntutKosDEV01 = mohonTuntutKosDEV01;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV02() {
        return mohonTuntutKosDEV02;
    }

    public void setMohonTuntutKosDEV02(PermohonanTuntutanKos mohonTuntutKosDEV02) {
        this.mohonTuntutKosDEV02 = mohonTuntutKosDEV02;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV04() {
        return mohonTuntutKosDEV04;
    }

    public void setMohonTuntutKosDEV04(PermohonanTuntutanKos mohonTuntutKosDEV04) {
        this.mohonTuntutKosDEV04 = mohonTuntutKosDEV04;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV11() {
        return mohonTuntutKosDEV11;
    }

    public void setMohonTuntutKosDEV11(PermohonanTuntutanKos mohonTuntutKosDEV11) {
        this.mohonTuntutKosDEV11 = mohonTuntutKosDEV11;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV14() {
        return mohonTuntutKosDEV14;
    }

    public void setMohonTuntutKosDEV14(PermohonanTuntutanKos mohonTuntutKosDEV14) {
        this.mohonTuntutKosDEV14 = mohonTuntutKosDEV14;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDISFT() {
        return mohonTuntutKosDISFT;
    }

    public void setMohonTuntutKosDISFT(PermohonanTuntutanKos mohonTuntutKosDISFT) {
        this.mohonTuntutKosDISFT = mohonTuntutKosDISFT;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDISPEL() {
        return mohonTuntutKosDISPEL;
    }

    public void setMohonTuntutKosDISPEL(PermohonanTuntutanKos mohonTuntutKosDISPEL) {
        this.mohonTuntutKosDISPEL = mohonTuntutKosDISPEL;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDISPRM() {
        return mohonTuntutKosDISPRM;
    }

    public void setMohonTuntutKosDISPRM(PermohonanTuntutanKos mohonTuntutKosDISPRM) {
        this.mohonTuntutKosDISPRM = mohonTuntutKosDISPRM;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDISTAX() {
        return mohonTuntutKosDISTAX;
    }

    public void setMohonTuntutKosDISTAX(PermohonanTuntutanKos mohonTuntutKosDISTAX) {
        this.mohonTuntutKosDISTAX = mohonTuntutKosDISTAX;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getNotis20() {
        return notis20;
    }

    public void setNotis20(String notis20) {
        this.notis20 = notis20;
    }

    public BigDecimal getNotis() {
        return notis;
    }

    public void setNotis(BigDecimal notis) {
        this.notis = notis;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDEV13() {
        return mohonTuntutKosDEV13;
    }

    public void setMohonTuntutKosDEV13(PermohonanTuntutanKos mohonTuntutKosDEV13) {
        this.mohonTuntutKosDEV13 = mohonTuntutKosDEV13;
    }

    public PermohonanTuntutanKos getMohonTuntutKosDISQT() {
        return mohonTuntutKosDISQT;
    }

    public void setMohonTuntutKosDISQT(PermohonanTuntutanKos mohonTuntutKosDISQT) {
        this.mohonTuntutKosDISQT = mohonTuntutKosDISQT;
    }

}

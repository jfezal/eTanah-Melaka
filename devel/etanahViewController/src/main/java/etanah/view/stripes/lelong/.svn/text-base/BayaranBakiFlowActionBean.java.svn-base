/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanPihak;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/bayaranbakiflow")
public class BayaranBakiFlowActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(BayaranBakiFlowActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PenggunaDAO penggunaDAO;
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    Permohonan permohonan;
    private String idPermohonan;
    private String idLelong;
    private String idEnkuiri;
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String baki;
    private String hari;
    private String tarikhLelong;
    private String tarikhAkhirBayaran;
    private boolean flag = false;
    private boolean showBtn = false;
    private boolean showPTD = true;
    private boolean bersameke;
    private boolean melaka = true;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<Lelongan> senaraiLelongan;
    private Pengguna pengguna;
    private String disabled;
    private String idHakmilik;
    private boolean hideBtn = false;
    private FasaPermohonan fs;
    private FasaPermohonan fs1;
    private BigDecimal komisyen1;
    private BigDecimal komisyen2;
    private BigDecimal komisyen3;
    private BigDecimal komisyen4;
    private BigDecimal komisyen5;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal c;
    private BigDecimal d;
    private BigDecimal e;
    private BigDecimal f;
    private BigDecimal z;
    private BigDecimal y;
    private BigDecimal baki1 = BigDecimal.ZERO;
    private BigDecimal baki2 = BigDecimal.ZERO;
    private BigDecimal baki3 = BigDecimal.ZERO;
    private BigDecimal baki4 = BigDecimal.ZERO;
    private BigDecimal totalKomisyen;
    private BigDecimal cukaitanah;
    private BigDecimal jumlah;
    private BigDecimal hargaBidaan;
    private List<FasaPermohonan> fasaList;
    private FasaPermohonan fasaPermohonan;
    private List<Lelongan> listLelong;
    private long idLelong1;
    private List<Pembida> senaraiPembida;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");

        List<Lelongan> senaraiLelongan2 = new ArrayList<Lelongan>();
        if (permohonan.getPermohonanSebelum() == null) {
            senaraiLelongan2 = lelongService.listLelonganPLAK(idPermohonan);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            senaraiLelongan2 = lelongService.listLelonganPLAK(idPermohonan);
            if (senaraiLelongan2.isEmpty()) {
                addLelong();
                senaraiLelongan2 = lelongService.listLelonganPLAK(idPermohonan);
            }
        }
        rehydrate();
        return new JSP("lelong/BayaranBaki_flow.jsp").addParameter("tab", "true");
    }

    public void addLelong() {
        InfoAudit ial = new InfoAudit();
        ial.setDiKemaskiniOleh(pengguna);
        ial.setTarikhKemaskini(new java.util.Date());
        ial.setDimasukOleh(pengguna);
        ial.setTarikhMasuk(new java.util.Date());
        List<Lelongan> listlol = lelongService.listLelonganPLAK(idPermohonan);
        LOG.info("listlol : " + listlol.size());
        if (listlol.isEmpty()) {
            List<Lelongan> listLel = lelongService.listLelonganPL(idPermohonan);
            LOG.info("listLel : " + listLel.size());
            KodStatusLelongan kod = kodStatusLelonganDAO.findById("AP");

            for (Lelongan ll : listLel) {
                ll.setKodStatusLelongan(kod);
                lelongService.saveOrUpdate(ll);

                List<AkuanTerimaDeposit> listATD = lelongService.findATD(permohonan.getPermohonanSebelum().getIdPermohonan(), String.valueOf(ll.getIdLelong()));
                for (AkuanTerimaDeposit aTD : listATD) {
                    AkuanTerimaDeposit ak = new AkuanTerimaDeposit();
                    ak.setAmaun(aTD.getAmaun());
                    ak.setIdRuj(String.valueOf(ll.getIdLelong()));
                    ak.setBank(aTD.getBank());
                    ak.setCaraBayaran(aTD.getCaraBayaran());
                    ak.setInfoAudit(ial);
                    ak.setPermohonan(permohonan);
                    ak.setTarikhTerima(aTD.getTarikhTerima());
                    ak.setNoDokumenBayar(aTD.getNoDokumenBayar());
                    ak.setPermohonanPihak(aTD.getPermohonanPihak());
                    lelongService.saveOrUpdate(ak);
                }
            }

            if (permohonan.getPermohonanSebelum() != null) {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                }
            }
        }
    }

    public Resolution showForm3() {
        //FasaPermohonan ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
        String msg = "Laporan Telah Dijana. Sila Buat Semakan Sebelum Cetakan.";
        FasaPermohonan ff = null;
        FasaPermohonan f1 = null;
        if (permohonan.getPermohonanSebelum() == null) {
            ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
            f1 = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
            fasaList = lelongService.getPermonanFasaRekodBidaan1(idPermohonan);
        } else {
            ff = lelongService.findFasaPermohonanSemak16H(permohonan.getPermohonanSebelum().getIdPermohonan());
            f1 = lelongService.findFasaPermohonanSemakPembida(permohonan.getPermohonanSebelum().getIdPermohonan());
            fasaList = lelongService.getPermonanFasaRekodBidaan1(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        if (ff.getKeputusan().getKod().equals("PH") || (ff.getKeputusan().getKod().equals("JL"))) {
            LOG.info("--PH-- && JL");

            if (!fasaList.isEmpty()) {
                fasaPermohonan = fasaList.get(0);
                if (fasaPermohonan.getKeputusan() != null) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                        LOG.info("------AP------");
                        List<PermohonanPihak> pp = null;
                        if (permohonan.getPermohonanSebelum() == null) {
                            enkuiri = lelongService.findEnkuiri(idPermohonan);
                            pp = lelongService.cekPermohonan(idPermohonan);
                        } else {
                            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (list2.isEmpty()) {
                                enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                                if (enkuiri == null) {
                                    List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                                    if (!list3.isEmpty()) {
                                        enkuiri = list3.get(0);
                                    }
                                }
                                pp = lelongService.cekPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                            } else {
                                enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                if (enkuiri == null) {
                                    List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                    if (!list3.isEmpty()) {
                                        enkuiri = list3.get(0);
                                    }
                                }
                                pp = lelongService.cekPermohonan(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            }
                        }
                        PermohonanPihak permohonanPihak = null;
                        for (PermohonanPihak oo : pp) {
                            if (oo.getJenis().getKod().equals("PG")) {
                                permohonanPihak = oo;
                                break;
                            }
                        }

                        listLelong = lelongService.listLelonganAP(idPermohonan);
                        String ap = "";
                        for (Lelongan ll : listLelong) {
                            if (ll.getKomisyenJualan() == null) {
                                ap = "ok";
                            }
                        }

                        if (ap.equals("ok")) {
                            kireKomisyen(permohonanPihak);
                        }

                        LOG.info("genReport :: start");
                        LOG.info("generate report start.");

                        try {
                            String gen = "";
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                //melaka
                                gen = "LLGBorang16QPTD_MLK.rdf";
                            }
                            String code = "16QPT";
                            LOG.info("genReportFromXML");
                            lelongService.reportGen(permohonan, gen, code, pengguna);
                        } catch (Exception ex) {
                            LOG.error("Error : " + ex);
                        }
                        LOG.info("genReport :: finish");

                    }
                    String ade = null;
                    if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                        LOG.info("-----TB-----");
                        listLelong = lelongService.listLelonganAKAP(idPermohonan);
                        if ("05".equals(conf.getProperty("kodNegeri"))) {
                            LOG.info("----n9-----");
                            for (Lelongan ll : listLelong) {
                                if (ll.getPembida() != null) {
                                    LOG.info("------ade------");
                                    ade = "ade";
                                }
                            }
                        }

                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            LOG.info("----MLK-----");
                            for (Lelongan ll : listLelong) {
                                idLelong1 = ll.getIdLelong();
                                senaraiPembida = enService.getListPembida(idLelong1);
                                for (Pembida bids : senaraiPembida) {
                                    if (bids.getPihak() != null && bids.getKodStsPembida().getKod().equals("BJ")) {
                                        LOG.info("------ade------");
                                        ade = "ade";
                                    }

                                }
                            }
                        }

                        if (!StringUtils.isEmpty(ade)) {
                            if (ade.equals("ade")) {

                                LOG.info("------ade------");
                                List<PermohonanPihak> pp = null;
                                if (permohonan.getPermohonanSebelum() == null) {
                                    enkuiri = lelongService.findEnkuiri(idPermohonan);
                                    pp = lelongService.cekPermohonan(idPermohonan);
                                } else {
                                    List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                                    if (list2.isEmpty()) {
                                        enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                                        if (enkuiri == null) {
                                            List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                                            if (!list3.isEmpty()) {
                                                enkuiri = list3.get(0);
                                            }
                                        }
                                        pp = lelongService.cekPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                                    } else {
                                        enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                        if (enkuiri == null) {
                                            List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                            if (!list3.isEmpty()) {
                                                enkuiri = list3.get(0);
                                            }
                                        }
                                        pp = lelongService.cekPermohonan(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                                    }
                                }
                                PermohonanPihak permohonanPihak = null;
                                for (PermohonanPihak oo : pp) {
                                    if (oo.getJenis().getKod().equals("PG")) {
                                        permohonanPihak = oo;
                                        break;
                                    }
                                }
                                listLelong = lelongService.listLelonganAP(idPermohonan);
                                String ap = "";
                                for (Lelongan ll : listLelong) {
                                    if (ll.getKomisyenJualan() == null) {
                                        ap = "ok";
                                    }
                                }
                                if (ap.equals("ok")) {
                                    kireKomisyen(permohonanPihak);
                                }

                                LOG.info("genReport :: start");
                                LOG.info("generate report start.");

                                try {
                                    String gen = "";
                                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                                        //melaka
                                        gen = "LLGBorang16QPTD_MLK.rdf";
                                    }
                                    String code = "16QPT";
                                    LOG.info("genReportFromXML");
                                    lelongService.reportGen(permohonan, gen, code, pengguna);
                                } catch (Exception ex) {
                                    LOG.error("Error : " + ex);
                                }
                                LOG.info("genReport :: finish");
                            }
                        }
                    }
                }
            }
        }
        return new StreamingResolution("text/plain", msg);
//        return new JSP("lelong/BayaranBaki_flow.jsp").addParameter("tab", "true");
    }

    public void kireKomisyen(PermohonanPihak permohonanPihak) {

        listLelong = lelongService.listLelonganPLAK(idPermohonan);
        for (Lelongan lelongan : listLelong) {

            if (lelongan.getHargaBidaan() != null) {
                if (enkuiri.getCaraLelong().equals("A")) {
                    LOG.info("---asing---");
                    hargaBidaan = lelongan.getHargaBidaan();
                }
                if (enkuiri.getCaraLelong().equals("B")) {
                    LOG.info("---bersama---");
                    hargaBidaan = enkuiri.getHargaBida();
                }
                LOG.info("hargaBidaan : " + hargaBidaan);

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    a = BigDecimal.valueOf(10000);
                    baki1 = hargaBidaan.subtract(a);
                    LOG.info("baki1:" + baki1);
                    if (baki1.intValue() > 0) {
                        baki2 = baki1.subtract(a);
                        LOG.info("baki2:" + baki2);
                    } else if (baki1.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki2 = new BigDecimal(0.00);
                    }
                    if (baki2.intValue() > 0) {
                        baki3 = baki2.subtract(a);
                        LOG.info("baki3:" + baki3);
                    } else if (baki2.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki3 = new BigDecimal(0.00);
                    }
                    LOG.info("haragbidaaa:" + hargaBidaan);

                    if (hargaBidaan.intValue() < 10000) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen1 = hargaBidaan.multiply(e);
                        LOG.info("komisyen1.0:" + komisyen1);
                    } else if (hargaBidaan.intValue() >= 10000) {
                        b = BigDecimal.valueOf(0.05);
                        komisyen1 = a.multiply(b);
                        LOG.info("komisyen1:" + komisyen1);
                        totalKomisyen = komisyen1;
                    }
                    if (baki1.intValue() >= 10000) {
                        c = BigDecimal.valueOf(0.03);
                        komisyen2 = a.multiply(c);
                        LOG.info("komisyen2.0:" + komisyen2);
                    } else if ((baki1.intValue() < 10000) && (baki1.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen2 = baki1.multiply(e);
                        LOG.info("komisyen2:" + komisyen2);
                        totalKomisyen = komisyen1.add(komisyen2);
                    }
                    if (baki2.intValue() >= 10000) {
                        d = BigDecimal.valueOf(0.02);
                        komisyen3 = a.multiply(d);
//            baki3 = baki2.subtract(a);
                        LOG.info("komisyen3.0:" + komisyen3);
                    } else if ((baki2.intValue() < 10000) && (baki2.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen3 = baki1.multiply(e);
                        LOG.info("komisyen3:" + komisyen3);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3);
                    }
                    if ((baki3.intValue() > 0)) {
                        d = BigDecimal.valueOf(0.01);
                        komisyen4 = baki3.multiply(d);
                        LOG.info("komisyen4.0:" + komisyen4);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
                        if (totalKomisyen.intValue() > 2000) {
                            f = BigDecimal.valueOf(2000);
                            totalKomisyen = f;
                        }
                    }
                } else {
                    a = BigDecimal.valueOf(1000);
                    z = BigDecimal.valueOf(10000);
                    y = BigDecimal.valueOf(40000);

//                    LOGGER.info("hargabidaan:" + hargaBidaan);

                    if (hargaBidaan.intValue() >= 1000) {
                        e = BigDecimal.valueOf(0.04);
                        komisyen1 = a.multiply(e);
                        LOG.info("komisyen1:" + komisyen1);
                        baki1 = hargaBidaan.subtract(a);
//                        totalKomisyen = komisyen1;
                    }
                    LOG.info("baki 1: " + baki1);
                    if ((baki1.intValue() >= 10000)) {
                        b = BigDecimal.valueOf(0.02);
                        komisyen2 = baki1.multiply(b);
                        LOG.info("komisyen2:" + komisyen2);
                        totalKomisyen = komisyen2.add(komisyen1);
                        baki2 = baki1.subtract(z);
                        LOG.info("baki 2: " + baki2);
                    } else if (baki1.intValue() < 10000) {
                        totalKomisyen = komisyen1;
                        LOG.info("total komisyen :" + totalKomisyen);
                    }


                    if ((baki2.intValue() >= 10001) && (baki2.intValue() <= 20000)) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen3 = baki2.multiply(c);
                        LOG.info("komisyen3:" + komisyen3);
                        totalKomisyen = komisyen3.add(komisyen2).add(komisyen1);
                        baki3 = baki2.subtract(z);
                        LOG.info("baki 3: " + baki3);
                    }

                    if ((baki3.intValue() >= 20001) && (baki3.intValue() <= 60000)) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen4 = baki3.multiply(c);
                        LOG.info("komisyen4:" + komisyen4);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
                        baki4 = baki3.subtract(y);
                        LOG.info("baki 4: " + baki4);
                    }

                    if (baki4.intValue() >= 60001) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen5 = baki4.multiply(c);
                        LOG.info("komisyen5:" + komisyen5);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4).add(komisyen5);
//                        LOGGER.info("total komisyen sume :" +totalKomisyen);
                    }

                    if (totalKomisyen.intValue() > 2000) {
                        f = BigDecimal.valueOf(2000);
                        totalKomisyen = f;
                    }
                }

                lelongan.setKomisyenJualan(totalKomisyen);
                lelongan.setBakiCukaiSemasa(cukaitanah);
                lelongService.saveOrUpdate(lelongan);
            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            Date now = new Date();

            senaraiLelongan = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());

            LOG.info("senaraiLelongan.size :" + senaraiLelongan.size());
            List<Enkuiri> listEn = null;
            if (permohonan.getPermohonanSebelum() == null) {
                listEn = lelongService.getEnkuiri(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    listEn = lelongService.getEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!listEn.isEmpty()) {
                        listEn = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                    }
                } else {
                    listEn = lelongService.getEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (!listEn.isEmpty()) {
                        listEn = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    }
                }
            }



            if ("04".equals(conf.getProperty("kodNegeri"))) {
                melaka = true;
                LOG.info("--masuk sini klu fs---");
                if (permohonan.getPermohonanSebelum() == null) {
                    fs = lelongService.findFasaPermohonanSemak16H(idPermohonan);
                    fs1 = lelongService.findFasaPermohonanSemakPembida(idPermohonan);
//                    LOG.info("mohon fasa 1: " + fs.getKeputusan().getKod());
//                    LOG.info("mohon fasa 2: " + fs1.getKeputusan().getKod());
//                    if (fs.getKeputusan().getKod().equals("JL") || fs1.getKeputusan().getKod().equals("AA")) {
//                        showPTD = true;
//                    } else {
//                        showPTD = false;
//                    }
                    if (!fs.getKeputusan().getKod().equals("JL")) {
                        showPTD = false;
                    } else {
                        showPTD = true;
                    }

                    if (fs1 != null) {
                        if (!fs1.getKeputusan().getKod().equals("AA")) {
                            showPTD = false;
                        } else {
                            showPTD = true;
                        }
                    }

                } else {
                    fs = lelongService.findFasaPermohonanSemak16H(permohonan.getPermohonanSebelum().getIdPermohonan());
                    fs1 = lelongService.findFasaPermohonanSemakPembida(permohonan.getPermohonanSebelum().getIdPermohonan());

                    if (fs.getKeputusan().getKod().equals("JL")) {
                        showPTD = true;
                    } //    else if (  fs1.getKeputusan().getKod().equals("AA")){
                    //                               showPTD = true;
                    //                       }
                    else {
                        showPTD = false;
                    }
//                   if (fs.getKeputusan().getKod().equals("JL") || fs1.getKeputusan().getKod().equals("AA")) {
//                        showPTD = true;
//                    } else {
//                        showPTD = false;
//                    }
//
//                       if (!fs.getKeputusan().getKod().equals("JL") || !fs1.getKeputusan().getKod().equals("AA")) {
//                        showPTD = false;
//                    } else {
//                        showPTD = true;
//                    }
                }
            }



            Enkuiri en = listEn.get(0);
            LOG.info("Enkuiri Status : " + en.getCaraLelong());
            String bk = "";
            if (en.getCaraLelong().equals("A")) {
                bersameke = false;
                LOG.info("-------A-------");
                for (Lelongan L : senaraiLelongan) {

                    Calendar c1 = new GregorianCalendar();
                    Calendar c2 = new GregorianCalendar();
                    c1.setTime(now);
                    c2.setTime(L.getTarikhLelong());

                    long time = c1.getTimeInMillis() - c2.getTimeInMillis();

                    LOG.info("Day : " + (time / (1000 * 60 * 60 * 24)));
                    baki = "";
                    baki = L.getBaki().toString();
                    LOG.info("L.getBaki().toString(); : " + baki);
                    tarikhLelong = sdf.format(L.getTarikhLelong());
                    tarikhAkhirBayaran = sdf.format(L.getTarikhAkhirBayaran());
                    if ((now).after(L.getTarikhLelong())) {
                        long count = (now.getTime() - L.getTarikhLelong().getTime()) / 1000 / 60 / 60 / 24;
                        count += 1L;
                        LOG.info("count :" + count);
                        hari = count + "";
                        if (count >= 120) {
                            showBtn = true;
                        }
                    } else {
                        LOG.info("count : before");
                    }
                    LOG.info("baki: " + baki);
                    if (L.getBaki().intValue() != 0) {
                        disabled = "disabled";
                        bk = "ada";
                        LOG.debug("--->0---");
                    } else {
                        disabled = null;
                        LOG.debug("---=0---");
                    }
                }
                if (!bk.equals("ada")) {
                    hideBtn = true;
                } else {
                    hideBtn = false;
                }
            }
            if (en.getCaraLelong().equals("B")) {
                LOG.info("-------B-------");
                bersameke = true;
                for (Lelongan L : senaraiLelongan) {

                    Calendar c1 = new GregorianCalendar();
                    Calendar c2 = new GregorianCalendar();
                    c1.setTime(now);
                    c2.setTime(L.getTarikhLelong());

                    long time = c1.getTimeInMillis() - c2.getTimeInMillis();

                    LOG.info("Day : " + (time / (1000 * 60 * 60 * 24)));

                    baki = en.getBaki().toString();
                    tarikhLelong = sdf.format(L.getTarikhLelong());
                    tarikhAkhirBayaran = sdf.format(L.getTarikhAkhirBayaran());
                    if ((now).after(L.getTarikhLelong())) {
                        long count = (now.getTime() - L.getTarikhLelong().getTime()) / 1000 / 60 / 60 / 24;
                        hari = count + "";
                        count++;
                        LOG.info("count :" + count);
                        if (count >= 120) {
                            showBtn = true;
                        }
                    } else {
                        LOG.info("count : before");
                    }
                    LOG.info("baki: " + baki);
                    if (en.getBaki().intValue() != 0) {
                        disabled = "disabled";
                    } else {
                        disabled = null;
                    }
                }
                senaraiLelongan = new ArrayList<Lelongan>();
                List<Lelongan> senaraiLelongan2 = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
                senaraiLelongan.add(senaraiLelongan2.get(0));
                StringBuilder sb = new StringBuilder();
                for (Lelongan ll : senaraiLelongan2) {
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(ll.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                }
                idHakmilik = sb.toString();
            }

        }
    }

    //generate Surat Bayar Baki
    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        try {
            String gen = "";
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                gen = "LLGSuratBayarBaki_NS.rdf";
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "LLGSuratBayarBaki_MLK.rdf";
            }

            String code = "SBI";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    //generate Lucut Hak
    public Resolution genReportA() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            LOG.info("genReportFromXML");
            String gen = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "LLGSrtLucutHak_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                gen = "LLGSrtLucutHak_NS.rdf";
            }
            String code = "SLH";
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    //generate Komisyen Jualan Pentadbir Tanah
    public void genReportB() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        try {
            String gen = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "LLGBorang16QPTD_MLK.rdf";
            }
            String code = "16QPT";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOG.error("Error : " + ex);
        }
        LOG.info("genReport :: finish");
        rehydrate();

    }

//    public Resolution genReportB() {
//        LOG.info("genReport :: start");
//        LOG.info("generate report start.");
//        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//
//        try {
//            LOG.info("genReportFromXML");
//            String gen = "";
//            if ("04".equals(conf.getProperty("kodNegeri"))) {
//                //melaka
//                gen = "LLGBorang16QPTD_MLK.rdf";
//            }
//            String code = "16QPT";
//            lelongService.reportGen(permohonan, gen, code, pengguna);
//        } catch (Exception ex) {
//            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//        }
//        LOG.info("genReport :: finish");
//        return new StreamingResolution("text/plain", msg);
//    }
    public Resolution reset() {
        InfoAudit ia = pengguna.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        String idLelong2 = getContext().getRequest().getParameter("idLelong");
        LOG.info("idLelong : " + idLelong2);
        lelong = lelonganDAO.findById(Long.parseLong(idLelong2));

        List<Enkuiri> listEn = null;
        if (permohonan.getPermohonanSebelum() == null) {
            listEn = lelongService.getEnkuiri(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                listEn = lelongService.getEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            } else {
                listEn = lelongService.getEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
        }

        enkuiri = listEn.get(0);

        if (bersameke == false) {
            //asing
            LOG.info("idPermohonan: " + idPermohonan);
            LOG.info("idLelong :" + idLelong);
            lelong.setBaki(BigDecimal.valueOf(0));
            enService.saveResetBaki(lelong, pengguna);
        } else {
            //bersama
            LOG.info("idPermohonan: " + idPermohonan);
            LOG.info("idEnkuiri :" + idEnkuiri);
            enkuiri.setInfoAudit(ia);
            enkuiri.setBaki(BigDecimal.valueOf(0));
            lelongService.saveOrUpdate(enkuiri);
            List<Lelongan> list = lelongService.listLelonganPLAK(idPermohonan);
            for (Lelongan ll : list) {
                ll.setBaki(BigDecimal.valueOf(0));
                enService.saveResetBaki(ll, pengguna);
            }
        }
        getContext().getRequest().setAttribute("AT", Boolean.FALSE);
        getContext().getRequest().setAttribute("dahBayar", Boolean.TRUE);
        getContext().getRequest().setAttribute("show", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("lelong/BayaranBaki_flow.jsp").addParameter("tab", "true");
    }

    public Resolution resetBayaran() {
        InfoAudit ia = pengguna.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        String idLelong2 = getContext().getRequest().getParameter("idLelong");
        LOG.info("idLelong : " + idLelong2);
        lelong = lelonganDAO.findById(Long.parseLong(idLelong2));


        List<Enkuiri> listEn = null;
        if (permohonan.getPermohonanSebelum() == null) {
            listEn = lelongService.getEnkuiri(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                listEn = lelongService.getEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
            } else {
                listEn = lelongService.getEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (!listEn.isEmpty()) {
                    listEn = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
        }

        enkuiri = listEn.get(0);
        if (bersameke == false) {
            //asing
            LOG.info("idPermohonan: " + idPermohonan);
            LOG.info("idLelong :" + idLelong);
            BigDecimal hargaBida = lelong.getHargaBidaan();
            BigDecimal deposit = BigDecimal.ZERO;
            List<AkuanTerimaDeposit> listATD = lelongService.findATDAll(idPermohonan, String.valueOf(lelong.getIdLelong()));
            for (AkuanTerimaDeposit at : listATD) {
                deposit = deposit.add(at.getAmaun());
            }
            BigDecimal bakiOld = hargaBida.subtract(deposit);
            lelong.setBaki(bakiOld);
            enService.saveResetBaki(lelong, pengguna);
        } else {
            //bersama
            LOG.info("idPermohonan: " + idPermohonan);
            LOG.info("idEnkuiri :" + idEnkuiri);
            BigDecimal hargaBida = lelong.getHargaBidaan();
            BigDecimal deposit = BigDecimal.ZERO;
            List<AkuanTerimaDeposit> listATD = lelongService.findATDAll(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            for (AkuanTerimaDeposit at : listATD) {
                deposit = deposit.add(at.getAmaun());
            }
            BigDecimal bakiOld = hargaBida.subtract(deposit);
            enkuiri.setInfoAudit(ia);
            enkuiri.setBaki(bakiOld);
            lelongService.saveOrUpdate(enkuiri);
            List<Lelongan> list = lelongService.listLelonganPLAK(idPermohonan);
            for (Lelongan ll : list) {
                ll.setBaki(bakiOld);
                enService.saveResetBaki(ll, pengguna);
            }
        }
        getContext().getRequest().setAttribute("AT", Boolean.FALSE);
        getContext().getRequest().setAttribute("dahBayar", Boolean.TRUE);
        getContext().getRequest().setAttribute("show", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("lelong/BayaranBaki_flow.jsp").addParameter("tab", "true");
    }

    @SuppressWarnings("static-access")
    public Resolution terusPermohonan() {
        FasaPermohonan fasa = new FasaPermohonan();
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            fasa = lelongService.findFasaPermohonanCetakSurat(permohonan.getIdPermohonan());
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(permohonan.getIdPermohonan());
        }
        KodStatusLelongan kod = null;
        String idMohonBaru = null;
        List<Hakmilik> hh = new ArrayList<Hakmilik>();
        if (senaraiLelongan.size() >= 2) {
            List<Lelongan> lel = new ArrayList<Lelongan>();
            String idLelong2 = getContext().getRequest().getParameter("idLelong");
            LOG.info("idLelong : " + idLelong2);
            lelong = lelonganDAO.findById(Long.parseLong(idLelong2));
            KodUrusan kk = permohonan.getKodUrusan();
            Pengguna pp = penggunaDAO.findById(fasa.getInfoAudit().getDimasukOleh().getIdPengguna());
            FolderDokumen fff = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            hh.add(lelong.getHakmilikPermohonan().getHakmilik());
            LOG.info("hh.size() : " + hh.size());
            if (hh.size() > 0) {
                LOG.info("----tak bayar lagi----");
                try {
                    String taskId = "";
                    Permohonan p = gipw.generateIdPermohonanWorkflowGetIdMohon(kk, pp, hh, permohonan, fff, lel);
                    lelong.setPermohonan(p);
                    lelongService.saveOrUpdate(lelong);
                    idMohonBaru = p.getIdPermohonan();
                    IWorkflowContext ctxx = workFlowService.authenticate(pp.getIdPengguna());
                    LOG.info("IDMohon : " + p.getIdPermohonan());
                    List<Task> taskList = null;
                    do {
                        taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                        LOG.info("taskList : " + taskList.size());
                    } while (taskList.isEmpty());
                    if (taskList.size() > 0) {
                        Task t = taskList.get(0);
                        String stageID = null;
                        if (t.getSystemAttributes().getTaskId() != null) {
                            taskId = t.getSystemAttributes().getTaskId();
                            LOG.info("taskId : " + taskId);
                            Task tt = null;
                            if (stageID != null) {
                                ctxx = workFlowService.authenticate(pp.getIdPengguna());
                            }
                            tt = workFlowService.acquireTask(taskId, ctxx);
                            LOG.info("Task acquireTask : " + tt.getSystemAttributes().getAcquiredBy());
                            do {
                                taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                                LOG.info("taskList : " + taskList.size());
                            } while (taskList.isEmpty());
                            t = taskList.get(0);
                            taskId = t.getSystemAttributes().getTaskId();
                            stageID = workFlowService.updateTaskOutcome(taskId, ctxx, "AA");
                            LOG.info("stageID : " + stageID);
                        }
                    }
                } catch (Exception ex) {
                    LOG.info("Error : " + ex);
                }
            }
        }
        if (permohonan.getSenaraiHakmilik().size() == 1) {
            LOG.info("------1------");
            List<Lelongan> ll = lelongService.listLelonganPLAK(permohonan.getIdPermohonan());
            for (Lelongan oo : ll) {
                kod = kodStatusLelonganDAO.findById("AT");
                oo.setKodStatusLelongan(kod);
                lelongService.saveOrUpdate(oo);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!hh.isEmpty()) {
            for (Hakmilik h : hh) {
                LOG.info("IdHakmilik : " + h.getIdHakmilik());
                if (h == null) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(h.getIdHakmilik());
            }
        }
        String idHakm = sb.toString();
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.Sila Rujuk IdPermohonan : " + idMohonBaru + " Untuk Meneruskan Proses Seterusnya Bagi Hakmilik : " + idHakm);
        return new JSP("lelong/BayaranBaki_flow.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getBaki() {
        return baki;
    }

    public void setBaki(String baki) {
        this.baki = baki;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public boolean isBersameke() {
        return bersameke;
    }

    public void setBersameke(boolean bersameke) {
        this.bersameke = bersameke;
    }

    public EnkuiriService getEnService() {
        return enService;
    }

    public void setEnService(EnkuiriService enService) {
        this.enService = enService;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(String idLelong) {
        this.idLelong = idLelong;
    }

    public String getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(String idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public boolean isHideBtn() {
        return hideBtn;
    }

    public void setHideBtn(boolean hideBtn) {
        this.hideBtn = hideBtn;
    }

    public boolean isShowPTD() {
        return showPTD;
    }

    public void setShowPTD(boolean showPTD) {
        this.showPTD = showPTD;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getKomisyen1() {
        return komisyen1;
    }

    public void setKomisyen1(BigDecimal komisyen1) {
        this.komisyen1 = komisyen1;
    }

    public BigDecimal getKomisyen2() {
        return komisyen2;
    }

    public void setKomisyen2(BigDecimal komisyen2) {
        this.komisyen2 = komisyen2;
    }

    public BigDecimal getKomisyen3() {
        return komisyen3;
    }

    public void setKomisyen3(BigDecimal komisyen3) {
        this.komisyen3 = komisyen3;
    }

    public BigDecimal getKomisyen4() {
        return komisyen4;
    }

    public void setKomisyen4(BigDecimal komisyen4) {
        this.komisyen4 = komisyen4;
    }

    public BigDecimal getTotalKomisyen() {
        return totalKomisyen;
    }

    public void setTotalKomisyen(BigDecimal totalKomisyen) {
        this.totalKomisyen = totalKomisyen;
    }

    /**
     * @return the z
     */
    public BigDecimal getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(BigDecimal z) {
        this.z = z;
    }

    /**
     * @return the baki4
     */
    public BigDecimal getBaki4() {
        return baki4;
    }

    /**
     * @param baki4 the baki4 to set
     */
    public void setBaki4(BigDecimal baki4) {
        this.baki4 = baki4;
    }

    /**
     * @return the y
     */
    public BigDecimal getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(BigDecimal y) {
        this.y = y;
    }

    /**
     * @return the komisyen5
     */
    public BigDecimal getKomisyen5() {
        return komisyen5;
    }

    /**
     * @param komisyen5 the komisyen5 to set
     */
    public void setKomisyen5(BigDecimal komisyen5) {
        this.komisyen5 = komisyen5;
    }

    public FasaPermohonan getFs() {
        return fs;
    }

    public void setFs(FasaPermohonan fs) {
        this.fs = fs;
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getD() {
        return d;
    }

    public void setD(BigDecimal d) {
        this.d = d;
    }

    public BigDecimal getE() {
        return e;
    }

    public void setE(BigDecimal e) {
        this.e = e;
    }

    public BigDecimal getF() {
        return f;
    }

    public void setF(BigDecimal f) {
        this.f = f;
    }

    public BigDecimal getBaki1() {
        return baki1;
    }

    public void setBaki1(BigDecimal baki1) {
        this.baki1 = baki1;
    }

    public BigDecimal getBaki2() {
        return baki2;
    }

    public void setBaki2(BigDecimal baki2) {
        this.baki2 = baki2;
    }

    public BigDecimal getBaki3() {
        return baki3;
    }

    public void setBaki3(BigDecimal baki3) {
        this.baki3 = baki3;
    }

    public BigDecimal getCukaitanah() {
        return cukaitanah;
    }

    public void setCukaitanah(BigDecimal cukaitanah) {
        this.cukaitanah = cukaitanah;
    }

    public List<FasaPermohonan> getFasaList() {
        return fasaList;
    }

    public void setFasaList(List<FasaPermohonan> fasaList) {
        this.fasaList = fasaList;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Lelongan> getListLelong() {
        return listLelong;
    }

    public void setListLelong(List<Lelongan> listLelong) {
        this.listLelong = listLelong;
    }

    public long getIdLelong1() {
        return idLelong1;
    }

    public void setIdLelong1(long idLelong1) {
        this.idLelong1 = idLelong1;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }

    public FasaPermohonan getFs1() {
        return fs1;
    }

    public void setFs1(FasaPermohonan fs1) {
        this.fs1 = fs1;
    }
}

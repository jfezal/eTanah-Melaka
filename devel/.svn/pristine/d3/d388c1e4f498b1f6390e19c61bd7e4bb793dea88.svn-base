/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanPihak;
import etanah.service.StrataPtService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/komisyen_Jualan")
public class KomisyenJualanActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(KomisyenJualanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    DokumenDAO dokumenDAO;
    private String idPermohonan;
    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private String kodNegeri;
    private long idLelong;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Pembida> senaraiPembida;
    private BigDecimal amaunTunggakan;
    private BigDecimal hargaBidaan;
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
    private List<Lelongan> listLelong;
    private Pengguna pengguna;
    private boolean view;
    private long idDokumen;
    private List<FasaPermohonan> fasaList;
    private List<FasaPermohonan> fasaListJLB;
    private String reportName;
    private Dokumen dokumen;
    private String SERVER_LOCATION;
    private String reportKey;
    private String iframeURL;
    private FasaPermohonan fasaPermohonan;
    private boolean show;

    @DefaultHandler
    public Resolution showForm() {
        show = true;
        return new JSP("lelong/komisyen_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        show = true;
//        FasaPermohonan ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
        FasaPermohonan ff = null;
        if (permohonan.getPermohonanSebelum() == null) {
            ff = lelongService.findFasaPermohonanSemak16H(idPermohonan);
        } else {
            ff = lelongService.findFasaPermohonanSemak16H(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        if (fasaList.size() <= 0) {
            fasaList = lelongService.getPermonanFasaRekodBidaanJLB(idPermohonan);
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            LOGGER.info("----MLK-----");
            if (ff.getKeputusan().getKod().equals("PH") || (ff.getKeputusan().getKod().equals("JL"))) {
                LOGGER.info("--PH-- && JL");
                if (!fasaList.isEmpty()) {
                    fasaPermohonan = fasaList.get(0);
                    if (fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                            LOGGER.info("------AP------");
                            show = true;
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
                            if (view == false) {
                                genReport2();
                            }
                        }
                        String ade = null;
                        if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                            LOGGER.info("-----TB-----");
                            listLelong = lelongService.listLelonganAKAP(idPermohonan);
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOGGER.info("----n9-----");
                                for (Lelongan ll : listLelong) {
                                    if (ll.getPembida() != null) {
                                        LOGGER.info("------ade------");
                                        ade = "ade";
                                    }
                                }
                            }

                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOGGER.info("----MLK-----");
                                for (Lelongan ll : listLelong) {
                                    idLelong = ll.getIdLelong();
                                    senaraiPembida = enService.getListPembida(idLelong);
                                    for (Pembida bids : senaraiPembida) {
                                        if (bids.getPihak() != null && bids.getKodStsPembida().getKod().equals("BJ")) {
                                            LOGGER.info("------ade------");
                                            ade = "ade";
                                        }

                                    }
                                }
                            }

                            if (!StringUtils.isEmpty(ade)) {
                                if (ade.equals("ade")) {

                                    LOGGER.info("------ade------");
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
                                        show = true;
                                    }
                                    if (view == false) {
                                        genReport2();
                                    }
                                }
                            } else {
                                show = false;
                                addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selasai.");
                            }
                        }
                    } else {
                        show = false;
                        addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                    }
                } else {
                    show = false;
                    addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                }
            }
        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOGGER.info("----n9-----");
            if (ff.getKeputusan().getKod().equals("PH")) {
                LOGGER.info("--PH--");
                if (!fasaList.isEmpty()) {
                    fasaPermohonan = fasaList.get(0);
                    if (fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("AP")) {
                            LOGGER.info("------AP------");
                            show = true;
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
                            if (view == false) {
                                genReport2();
                            }
                        }
                        String ade = null;
                        if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                            LOGGER.info("-----TB-----");
                            listLelong = lelongService.listLelonganAKAP(idPermohonan);
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOGGER.info("----n9-----");
                                for (Lelongan ll : listLelong) {
                                    if (ll.getPembida() != null) {
                                        LOGGER.info("------ade------");
                                        ade = "ade";
                                    }
                                }
                            }

                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOGGER.info("----MLK-----");
                                for (Lelongan ll : listLelong) {
                                    idLelong = ll.getIdLelong();
                                    senaraiPembida = enService.getListPembida(idLelong);
                                    for (Pembida bids : senaraiPembida) {
                                        if (bids.getPihak() != null && bids.getKodStsPembida().getKod().equals("BJ")) {
                                            LOGGER.info("------ade------");
                                            ade = "ade";
                                        }

                                    }
                                }
                            }

                            if (!StringUtils.isEmpty(ade)) {
                                if (ade.equals("ade")) {

                                    LOGGER.info("------ade------");
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
                                        show = true;
                                    }
                                    if (view == false) {
                                        genReport2();
                                    }
                                }
                            } else {
                                show = false;
                                addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selasai.");
                            }
                        }
                    } else {
                        show = false;
                        addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                    }
                } else {
                    show = false;
                    addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                }
            } else {
                LOGGER.info("--JL--");
                LOGGER.info("fasaList.size() : " + fasaList.size());
                show = false;
                if (fasaList.size() != 0) {
                    fasaPermohonan = fasaList.get(0);
                    if (fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("TB")) {
                            listLelong = lelongService.listLelonganAP(idPermohonan);
                            String ap = "";
                            for (Lelongan ll : listLelong) {
                                if (ll.getKomisyenJualan() == null) {
                                    ap = "ok";
                                    break;
                                }
                            }
                            if (ap.equals("ok")) {
                                addSimpleMessage("Jurulelong Adalah Jurulelong Berlesen,Sila Klik Butang Selesai");
                            } else {
                                addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selasai.");
                            }
                        } else {
                            addSimpleMessage("Jurulelong Adalah Jurulelong Berlesen,Sila Klik Butang Selesai");
                        }
                    } else {
                        show = false;
                        addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                    }
                } else {
                    show = false;
                    addSimpleError("Sila Masukkan Keputusan Terlebih Dahulu Di Tab Kemasukan Rekod Bidaan");
                }
            }
        }
        return new JSP("lelong/komisyen_jualan.jsp").addParameter("tab", "true");
    }

    public void kireKomisyen(PermohonanPihak permohonanPihak) {

        listLelong = lelongService.listLelonganPLAK(idPermohonan);
        for (Lelongan lelongan : listLelong) {

            if (lelongan.getHargaBidaan() != null) {
                if (enkuiri.getCaraLelong().equals("A")) {
                    LOGGER.info("---asing---");
                    hargaBidaan = lelongan.getHargaBidaan();
                }
                if (enkuiri.getCaraLelong().equals("B")) {
                    LOGGER.info("---bersama---");
                    hargaBidaan = enkuiri.getHargaBida();
                }
                LOGGER.info("hargaBidaan : " + hargaBidaan);

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    a = BigDecimal.valueOf(10000);
                    baki1 = hargaBidaan.subtract(a);
                    LOGGER.info("baki1:" + baki1);
                    if (baki1.intValue() > 0) {
                        baki2 = baki1.subtract(a);
                        LOGGER.info("baki2:" + baki2);
                    } else if (baki1.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki2 = new BigDecimal(0.00);
                    }
                    if (baki2.intValue() > 0) {
                        baki3 = baki2.subtract(a);
                        LOGGER.info("baki3:" + baki3);
                    } else if (baki2.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki3 = new BigDecimal(0.00);
                    }
                    LOGGER.info("haragbidaaa:" + hargaBidaan);

                    if (hargaBidaan.intValue() < 10000) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen1 = hargaBidaan.multiply(e);
                        LOGGER.info("komisyen1.0:" + komisyen1);
                    } else if (hargaBidaan.intValue() >= 10000) {
                        b = BigDecimal.valueOf(0.05);
                        komisyen1 = a.multiply(b);
                        LOGGER.info("komisyen1:" + komisyen1);
                        totalKomisyen = komisyen1;
                    }
                    if (baki1.intValue() >= 10000) {
                        c = BigDecimal.valueOf(0.03);
                        komisyen2 = a.multiply(c);
                        LOGGER.info("komisyen2.0:" + komisyen2);
                    } else if ((baki1.intValue() < 10000) && (baki1.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen2 = baki1.multiply(e);
                        LOGGER.info("komisyen2:" + komisyen2);
                        totalKomisyen = komisyen1.add(komisyen2);
                    }
                    if (baki2.intValue() >= 10000) {
                        d = BigDecimal.valueOf(0.02);
                        komisyen3 = a.multiply(d);
//            baki3 = baki2.subtract(a);
                        LOGGER.info("komisyen3.0:" + komisyen3);
                    } else if ((baki2.intValue() < 10000) && (baki2.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen3 = baki1.multiply(e);
                        LOGGER.info("komisyen3:" + komisyen3);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3);
                    }
                    fasaListJLB = lelongService.getPermonanFasaRekodBidaanJLB(idPermohonan);
                    BigDecimal totalKom = komisyen1.add(komisyen2).add(komisyen3);
                    if (baki3 != null) {
                        BigDecimal bakiBaru = baki3.multiply(BigDecimal.valueOf(0.01));
                        bakiBaru = bakiBaru.add(totalKom);
                        List<FasaPermohonan> mohonFasaList = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
                        if (mohonFasaList.size() > 0) {
                            totalKomisyen = bakiBaru;
                        } else {
                            totalKomisyen = bakiBaru.multiply(BigDecimal.valueOf(0.25));
                            if (totalKomisyen.intValue() > 500) {
                                totalKomisyen = BigDecimal.valueOf(500);
                            }
                        }

                    }
//                    if (fasaListJLB.size() <= 0) {
//                        if ((baki3.intValue() > 0)) {
//                            d = BigDecimal.valueOf(0.01);
//                            komisyen4 = baki3.multiply(d);
//                            LOGGER.info("komisyen4.0:" + komisyen4);
//                            totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
//                            if (totalKomisyen.intValue() > 2000) {
//                                f = BigDecimal.valueOf(2000);
//                                totalKomisyen = f;
//                            }
//                        }
//                    }else {
//                        if ((baki3.intValue() > 0)) {
//                            d = BigDecimal.valueOf(0.01);
//                            komisyen4 = baki3.multiply(d);
//                            LOGGER.info("komisyen4.0:" + komisyen4);
//                            totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
//                            if (totalKomisyen.intValue() > 2000) {
//                                f = BigDecimal.valueOf(500);
//                                totalKomisyen = f;
//                            }
//                        }
//                    }
                } else {
                    a = BigDecimal.valueOf(1000);
                    z = BigDecimal.valueOf(10000);
                    y = BigDecimal.valueOf(40000);

//                    LOGGER.info("hargabidaan:" + hargaBidaan);
                    if (hargaBidaan.intValue() >= 1000) {
                        e = BigDecimal.valueOf(0.04);
                        komisyen1 = a.multiply(e);
                        LOGGER.info("komisyen1:" + komisyen1);
                        baki1 = hargaBidaan.subtract(a);
//                        totalKomisyen = komisyen1;
                    }
                    LOGGER.info("baki 1: " + baki1);
                    if ((baki1.intValue() >= 10000)) {
                        b = BigDecimal.valueOf(0.02);
                        komisyen2 = baki1.multiply(b);
                        LOGGER.info("komisyen2:" + komisyen2);
                        totalKomisyen = komisyen2.add(komisyen1);
                        baki2 = baki1.subtract(z);
                        LOGGER.info("baki 2: " + baki2);
                    } else if (baki1.intValue() < 10000) {
                        totalKomisyen = komisyen1;
                        LOGGER.info("total komisyen :" + totalKomisyen);
                    }

                    if ((baki2.intValue() >= 10001) && (baki2.intValue() <= 20000)) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen3 = baki2.multiply(c);
                        LOGGER.info("komisyen3:" + komisyen3);
                        totalKomisyen = komisyen3.add(komisyen2).add(komisyen1);
                        baki3 = baki2.subtract(z);
                        LOGGER.info("baki 3: " + baki3);
                    }

                    if ((baki3.intValue() >= 20001) && (baki3.intValue() <= 60000)) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen4 = baki3.multiply(c);
                        LOGGER.info("komisyen4:" + komisyen4);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
                        baki4 = baki3.subtract(y);
                        LOGGER.info("baki 4: " + baki4);
                    }

                    if (baki4.intValue() >= 60001) {
                        c = BigDecimal.valueOf(0.01);
                        komisyen5 = baki4.multiply(c);
                        LOGGER.info("komisyen5:" + komisyen5);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4).add(komisyen5);
//                        LOGGER.info("total komisyen sume :" +totalKomisyen);
                    }
                    fasaListJLB = lelongService.getPermonanFasaRekodBidaanJLB(idPermohonan);
                    if (fasaListJLB.size() <= 0) {
                        if (totalKomisyen.intValue() > 2000) {
                            f = BigDecimal.valueOf(2000);
                            totalKomisyen = f;
                        }
                    } else if (totalKomisyen.intValue() > 500) {
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

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        fasaList = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
        if (fasaList.size() <= 0) {
            fasaList = lelongService.getPermonanFasaRekodBidaanJLB(idPermohonan);
        }
        if (!fasaList.isEmpty()) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                reportName = "LLGBorang16Q_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                reportName = "LLGBorang16Q_NS.rdf";
            }

            List<KandunganFolder> listKandunganFolder = lelongService.getListDokumenKM(permohonan.getFolderDokumen().getFolderId());
            if (!listKandunganFolder.isEmpty()) {
                for (KandunganFolder ff : listKandunganFolder) {
                    if (ff.getDokumen().getKodDokumen().getKod().equals("KM")) {
                        dokumen = ff.getDokumen();
                        break;
                    }
                }
            }

            if (dokumen != null) {
                idDokumen = dokumen.getIdDokumen();
                view = true;
                viewPdf();
            } else {
                view = false;
//                genReport2();
            }
        }
    }

    //sebelum jana
    public Resolution janaReport() {
        LOGGER.info("------sebelum jana------");
        String add = conf.getProperty("report.server.location");
        InetAddress address = null;
        try {
            address = InetAddress.getByName(lelongService.getIP(add));
            SERVER_LOCATION = lelongService.replaceDNS(address.getHostAddress(), add);
        } catch (UnknownHostException ex) {
            LOGGER.info("Error-" + ex);
        }
        LOGGER.info("SERVER_LOCATION : " + SERVER_LOCATION);
        reportKey = conf.getProperty("report.key");
        StringBuilder cmd = new StringBuilder(SERVER_LOCATION).append("?").append(reportKey).append("&").append(reportName).append("&").append("p_id_mohon").append("=").append(idPermohonan);
        LOGGER.info("SERVER REPORT : " + cmd);
        iframeURL = cmd.toString();
        getContext().getRequest().setAttribute("jana", Boolean.TRUE);
        getContext().getRequest().setAttribute("lulus", Boolean.FALSE);
        return new JSP("lelong/komisyen_jualan.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution printReport() {
        LOGGER.info("------selepas jana------");
        String docPath = conf.getProperty("document.path");
        StringBuilder cmd = new StringBuilder(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + dokumen.getNamaFizikal() + strService.getFormat(dokumen.getFormat()));
        iframeURL = cmd.toString();
        LOGGER.info("iframeURL : " + iframeURL);
        return new JSP("lelong/komisyen_jualan.jsp").addParameter("tab", "true");
    }

    //selepas jana
    public Resolution viewPdf() {
        LOGGER.info("------selepas jana------");
        LOGGER.info("idDokumen :" + idDokumen);
//        Dokumen dd = dokumenDAO.findById(idDokumen);
//        if (dd == null) {
//            return new ErrorResolution(500, "Dokumen tidak dijumpai");
//        }
//        String docPath = conf.getProperty("document.path");
//        String fn = dd.getNamaFizikal();
//        LOGGER.info("fn ::" + fn);
//        File ff = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//        LOGGER.info("new FileInputStream(f) : " + ff.getPath());
//        if (ff != null) {
//            try {
//                return new StreamingResolution(dd.getFormat(), new FileInputStream(ff));
//            } catch (FileNotFoundException ex) {
//            }
//        }
        return new JSP("lelong/komisyen_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "LLGBorang16Q_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                gen = "LLGBorang16Q_NS.rdf";
            }
            String code = "KM";
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOGGER.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public void genReport2() {
        LOGGER.info("genReport :: start");
        LOGGER.info("generate report start.");
        try {
            String gen = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                gen = "LLGBorang16Q_MLK.rdf";
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                gen = "LLGBorang16Q_NS.rdf";
            }
            String code = "KM";
            LOGGER.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            LOGGER.error("Error : " + ex);
        }
        LOGGER.info("genReport :: finish");
        rehydrate();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public String getSERVER_LOCATION() {
        return SERVER_LOCATION;
    }

    public void setSERVER_LOCATION(String SERVER_LOCATION) {
        this.SERVER_LOCATION = SERVER_LOCATION;
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

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getCukaitanah() {
        return cukaitanah;
    }

    public void setCukaitanah(BigDecimal cukaitanah) {
        this.cukaitanah = cukaitanah;
    }

    public BigDecimal getD() {
        return d;
    }

    public void setD(BigDecimal d) {
        this.d = d;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
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

    public List<FasaPermohonan> getFasaList() {
        return fasaList;
    }

    public void setFasaList(List<FasaPermohonan> fasaList) {
        this.fasaList = fasaList;
    }

    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIframeURL() {
        return iframeURL;
    }

    public void setIframeURL(String iframeURL) {
        this.iframeURL = iframeURL;
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

    public List<Lelongan> getListLelong() {
        return listLelong;
    }

    public void setListLelong(List<Lelongan> listLelong) {
        this.listLelong = listLelong;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public BigDecimal getTotalKomisyen() {
        return totalKomisyen;
    }

    public void setTotalKomisyen(BigDecimal totalKomisyen) {
        this.totalKomisyen = totalKomisyen;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(long idLelong) {
        this.idLelong = idLelong;
    }

    /**
     * @return the kodNegeri
     */
    public String getKodNegeri() {
        return kodNegeri;
    }

    /**
     * @param kodNegeri the kodNegeri to set
     */
    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
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

    public List<FasaPermohonan> getFasaListJLB() {
        return fasaListJLB;
    }

    public void setFasaListJLB(List<FasaPermohonan> fasaListJLB) {
        this.fasaListJLB = fasaListJLB;
    }
}

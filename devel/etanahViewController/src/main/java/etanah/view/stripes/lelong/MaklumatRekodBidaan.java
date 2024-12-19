/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

/**
 *
 * @author nur.aqilah
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodStsPembidaDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.DokumenKewangan;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusLelongan;
import etanah.model.KodStsPembida;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/lelong/rekod_bidaan")
public class MaklumatRekodBidaan extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatRekodBidaan.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    private KodStsPembidaDAO kodStsPembidaDAO;
    @Inject
    private PembidaDAO pembidaDAO;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private AkuanTerimaDeposit akuanTerimaDeposit;
    private Pihak pihak;
    private Enkuiri enkuiri;
    private Lelongan lelong;
    private Pembida pembida;
    private String idPermohonan;
    private String idPembida;
    private String idLelong;
    private String tarikhAkhirBayaran;
    private String tarikhLelong;
    private String aktif;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan2;
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> senaraiLelongan4;
    private List<Lelongan> senaraiLelongan5;
    private List<Lelongan> listBil;
    private List<Enkuiri> senaraiEnkuiri;
    private List<FasaPermohonan> senaraifasamohon;
    private List<DokumenKewangan> checkBayaranPerintah;
    private List<Pembida> senaraiPembida;
    private List<String> listIDHakmilik = new ArrayList<String>();
    private List<Pembida> list2 = new ArrayList<Pembida>();
    private String disabled;
    private String disabled1;
    private String kodJenis;
    private String kodNegeri;
    private String reportName;
    private String status;
    private boolean bida;
    private boolean disable = false;
    private boolean owh = false;
    private boolean hideSimpan = false;
    private boolean bersameke;
    private boolean pembidaSama = true;
    private boolean BJ = false;
    private boolean buttonSimpan = true;
    private boolean adapembida = true;
    private String kodBank;
    private AkuanTerimaDeposit atd;
    private BigDecimal hargaBidaan;
    private BigDecimal hargaRizab;
    private BigDecimal depositAwal;
    private BigDecimal deposit;
    private String kodBank2;
    private AkuanTerimaDeposit atd2;
    private BigDecimal hargaBidaan2;
    private BigDecimal deposit2;
    private BigDecimal baki;
    private Pengguna pengguna;
    private FasaPermohonan fasa;
    private FasaPermohonan fasa1;
    private boolean show;
    private boolean error;
    private boolean rujuk = false;
    private boolean mlk;
    private int bilLelong;
    private boolean negeri;
    private String kodCareBayar;
    private String kodCareBayar2;
    private String jam;
    private String minit;
    private String ampm;
    private long idLelong1;
    private BigDecimal a;

    @DefaultHandler
    public Resolution showForm() {

        if (idPermohonan != null) {

            LOG.info("---showForm---");
            if (idPermohonan != null) {
                getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
                senaraifasamohon = lelongService.getPermonanFasaRekodBidaan1(idPermohonan);

                LOG.debug("senaraifasamohon.size :" + senaraifasamohon.size() + " ::id aliran " + senaraifasamohon.get(0).getIdAliran());

//                for (FasaPermohonan fp : senaraifasamohon) {
//                    if (fp.getKeputusan() == null) {
//                        status = null;
//                        continue;
//                    }
//                    if ("AP".equals(fp.getKeputusan().getKod())) {
//                        status = "AP";
//                        LOG.debug("berjaya");
//                        break;
//                    } else if ("TB".equals(fp.getKeputusan().getKod())) {
//                        status = "TB";
//                        LOG.debug("lagi berjaya");
//                        break;
//                    }
//                }

                if (!senaraifasamohon.isEmpty()) {
                    if (senaraifasamohon.get(0).getKeputusan() == null) {
                        status = null;
                    } else {
                        if ("AP".equals(senaraifasamohon.get(0).getKeputusan().getKod())) {
                            status = "AP";
                            LOG.debug("berjaya");

                        } else if ("TB".equals(senaraifasamohon.get(0).getKeputusan().getKod())) {
                            status = "TB";
                            LOG.debug("lagi berjaya");

                        }
                    }
                }

                if (status == null) {

                    setCheckBayaranPerintah(lelongService.checkBayarPerintah(idPermohonan));

                    LOG.info("checkBayaranPerintah.size() " + getCheckBayaranPerintah().size());

                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("Melaka Sahaja check status bayaran perintah");
                        if (getCheckBayaranPerintah().size() < 1) {
                            setDisable(true);
                            LOG.info("disable = true");

                            if (permohonan.getSenaraiHakmilik().size() > 1) {
                                if (enkuiri.getCaraLelong().equals("B")) {
                                    show = false;
                                } else {
                                    List<Lelongan> list = lelongService.listLelonganA(idPermohonan);
                                    if (list.size() > 1) {
                                        show = true;
                                    } else {
                                        show = false;
                                    }
                                }

                            } else {
                                show = false;
                            }
                            addSimpleError("Sila Jelaskan Bayaran Perintah RM80 Dikaunter SPOC Terlebih Dahulu");

                        } else {
                            setDisable(false);
                            LOG.info("disable = false");
                            if (permohonan.getSenaraiHakmilik().size() > 1) {
                                if (enkuiri.getCaraLelong().equals("B")) {
                                    show = false;
                                } else {
                                    List<Lelongan> list = lelongService.listLelonganA(idPermohonan);
                                    if (list.size() > 1) {
                                        show = true;
                                    } else {
                                        show = false;
                                    }
                                }

                            } else {
                                show = false;
                            }
                        }

                        return new JSP("lelong/keputusan_lelongan.jsp").addParameter("tab", "true");
                    } else {
                        setDisable(false); //button tak payah disabled
                        LOG.info("Negeri 9 xyah check status bayaran pelbagai");
                        if (permohonan.getSenaraiHakmilik().size() > 1) {
                            if (enkuiri.getCaraLelong().equals("B")) {
                                show = false;
                            } else {
                                List<Lelongan> list = lelongService.listLelonganA(idPermohonan);
                                if (list.size() > 1) {
                                    show = true;
                                } else {
                                    show = false;
                                }
                            }

                        } else {
                            show = false;
                        }
                        return new JSP("lelong/keputusan_lelongan.jsp").addParameter("tab", "true");
                    }

                }

                if (status.equals("AP")) {
                    show = true;
                    error = true;
                    owh = true;
                    getContext().getRequest().setAttribute("kembali", Boolean.TRUE);
                    return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
                }
                if (status.equals("TB")) {
                    owh = false;
                    error = true;
                    getContext().getRequest().setAttribute("kembali", Boolean.TRUE);
                    if (bida == true) {
                        addSimpleMessage("Keputusan Adalah Tiada Pembida.Sila Klik Butang Selesai...");
                    }
                    return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        LOG.info("---showForm6---");
        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        getContext().getRequest().setAttribute("kembali", Boolean.FALSE);
        status = "AP";
        show = true;
        error = true;
        owh = true;
//        hideSimpan = true;
        return new JSP("lelong/maklumat_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        LOG.info("---showForm1---");

        List<Lelongan> list = lelongService.listLelonganAT(idPermohonan);
        if (!list.isEmpty()) {
            for (Lelongan ll : list) {
                if (ll.getKodStatusLelongan().getKod().equals("AT")) {
                    getContext().getRequest().setAttribute("AT", Boolean.TRUE);
                    break;
                }
            }
        }
        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        getContext().getRequest().setAttribute("dahBayar", Boolean.FALSE);
        show = true;
        fasa = lelongService.findFasaPermohonanCetakSurat(idPermohonan);

        if (fasa != null) {
            if (fasa.getKeputusan() != null) {
                if (fasa.getKeputusan().getKod().equals("AC") || fasa.getKeputusan().getKod().equals("LB")) {
                    LOG.info("---AC--LB---");
                    if (permohonan.getSenaraiHakmilik().size() > 1) {
                        if (enkuiri.getCaraLelong().equals("B")) {
                            LOG.info("---B---");
                            bersameke = true;
                            bida = true;

                            senaraiLelongan2 = lelongService.listLelonganATDESC(idPermohonan);
                            lelong = senaraiLelongan2.get(0);

                            //N9
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----N9-----");
                                if (lelong.getPembida() != null) {
                                    pihak = lelong.getPembida();
                                    kodNegeri = pihak.getNegeri().getKod();
                                    kodJenis = pihak.getJenisPengenalan().getKod();
                                }
                            }

                            //MLK
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----MLK-----");
                                Long idLelong1 = lelong.getIdLelong();
                                Pembida pem = pembidaDAO.findById(idLelong1);
//                                if (pem.getPihak() != null) {
                                if (pem.getPihak() != null && pem.getKodStsPembida().getKod().equals("BJ")) {
                                    pihak = pem.getPihak();
                                    kodNegeri = pihak.getNegeri().getKod();
                                    kodJenis = pihak.getJenisPengenalan().getKod();

                                }
                            }

                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan);
                            LOG.info("---jalan2 sini x---");
                            if (!listATD.isEmpty()) {
                                LOG.info("---jalan2 sini x1---");
                                atd = listATD.get(0);

                                LOG.info("---jalan2 sini x2---");
                                if (atd.getBank() != null) {
                                    kodBank = atd.getBank().getKod();
                                }
                            }
                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan);

                            if (!listATD2.isEmpty()) {
                                atd2 = listATD2.get(0);
                                LOG.info("atd : " + atd2.getIdAkuan());
                                if (atd2.getBank() != null) {
                                    kodBank2 = atd2.getBank().getKod();
                                    deposit2 = atd2.getAmaun();
                                }
                            }
                            hargaBidaan = enkuiri.getHargaBida();
                            deposit = atd.getAmaun();
                            baki = enkuiri.getBaki();
                        }
                        if (enkuiri.getCaraLelong().equals("A")) {
                            LOG.info("---A---");
                            senaraiLelongan2 = lelongService.listLelonganAT(idPermohonan);
                            LOG.info("senaraiLelongan2 : " + senaraiLelongan2.size());
                            bida = false;
                            if (senaraiLelongan2.size() == 1) {
                                bida = true;
                                lelong = senaraiLelongan2.get(0);
                                //N9
                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                    LOG.info("----N9-----");
                                    if (lelong.getPembida() != null) {
                                        pihak = lelong.getPembida();
                                        kodNegeri = pihak.getNegeri().getKod();
                                        kodJenis = pihak.getJenisPengenalan().getKod();
                                    }
                                }
                                //MLK
                                if ("04".equals(conf.getProperty("kodNegeri"))) {
                                    LOG.info("----MLK-----");
                                    Long idLelong1 = lelong.getIdLelong();
                                    Pembida pem = pembidaDAO.findById(idLelong1);
                                    if (pem.getPihak() != null) {
                                        pihak = pem.getPihak();
                                        kodNegeri = pihak.getNegeri().getKod();
                                        kodJenis = pihak.getJenisPengenalan().getKod();
                                    }
                                }

                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan);
                                LOG.info("---jalan2 sini a---");
                                if (!listATD.isEmpty()) {
                                    LOG.info("---jalan2 sini a1---");
                                    atd = listATD.get(0);
                                    LOG.info("---jalan2 sini a2---");
                                    if (atd.getBank() != null) {
                                        kodBank = atd.getBank().getKod();
                                    }
                                }
                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan);

                                if (!listATD2.isEmpty()) {
                                    atd2 = listATD2.get(0);
                                    LOG.info("atd : " + atd2.getIdAkuan());
                                    if (atd2.getBank() != null) {
                                        kodBank2 = atd2.getBank().getKod();
                                        deposit2 = atd2.getAmaun();
                                    }
                                }
                                hargaBidaan = lelong.getHargaBidaan();
                                deposit = lelong.getDeposit();
                                baki = lelong.getBaki();
                            }
                        }
                    }
                    if (permohonan.getSenaraiHakmilik().size() == 1) {
                        if (enkuiri.getCaraLelong().equals("B")) {
                            LOG.info("---B---");
                            bersameke = true;
                            bida = true;

                            senaraiLelongan2 = lelongService.listLelonganATDESC(idPermohonan);
                            lelong = senaraiLelongan2.get(0);
                            //N9
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----N9-----");
                                if (lelong.getPembida() != null) {
                                    pihak = lelong.getPembida();
                                    kodNegeri = pihak.getNegeri().getKod();
                                    kodJenis = pihak.getJenisPengenalan().getKod();
                                }
                            }

                            //MLK
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----MLK-----");
                                Long idLelong1 = lelong.getIdLelong();
                                Pembida pem = pembidaDAO.findById(idLelong1);
                                if (pem != null) {
//                                    if (pem.getPihak() != null) {
                                    pihak = pem.getPihak();
                                    kodNegeri = pihak.getNegeri().getKod();
                                    kodJenis = pihak.getJenisPengenalan().getKod();
//                                    }
                                }
                            }
                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan);
                            LOG.info("---jalan2 sini q---");
                            if (!listATD.isEmpty()) {
                                LOG.info("---jalan2 sini q1---");
                                atd = listATD.get(0);
                                LOG.info("---jalan2 sini q2---");
                                if (atd.getBank() != null) {
                                    kodBank = atd.getBank().getKod();
                                }
                            }
                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan);

                            if (!listATD2.isEmpty()) {
                                atd2 = listATD2.get(0);
                                LOG.info("atd : " + atd2.getIdAkuan());
                                if (atd2.getBank() != null) {
                                    kodBank2 = atd2.getBank().getKod();
                                    deposit2 = atd2.getAmaun();
                                }
                            }

                            hargaBidaan = enkuiri.getHargaBida();
                            deposit = atd.getAmaun();
                            baki = enkuiri.getBaki();
                        }
                        if (enkuiri.getCaraLelong().equals("A")) {
                            LOG.info("---A---");
                            senaraiLelongan2 = lelongService.listLelonganAT(idPermohonan);
                            LOG.info("senaraiLelongan2 : " + senaraiLelongan2.size());
                            bida = true;
                            lelong = senaraiLelongan2.get(0);
                            //N9
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----N9-----");
                                if (lelong.getPembida() != null) {
                                    pihak = lelong.getPembida();
                                    kodNegeri = pihak.getNegeri().getKod();
                                    kodJenis = pihak.getJenisPengenalan().getKod();
                                }
                            }

                            //MLK
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----MLK-----");
                                Long idLelong1 = lelong.getIdLelong();
                                Pembida pem = pembidaDAO.findById(idLelong1);
                                if (pem != null) {
                                    if (pem.getPihak() != null) {
                                        pihak = pem.getPihak();
                                        kodNegeri = pihak.getNegeri().getKod();
                                        kodJenis = pihak.getJenisPengenalan().getKod();
                                    }
                                }
                            }

                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(lelong.getIdLelong()));
                            if (!listATD.isEmpty()) {
                                atd = listATD.get(0);
                                if (atd.getBank() != null) {
                                    kodBank = atd.getBank().getKod();
                                }
                            }
                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));

                            if (!listATD2.isEmpty()) {
                                atd2 = listATD2.get(0);
                                LOG.info("atd : " + atd2.getIdAkuan());
                                if (atd2.getBank() != null) {
                                    kodBank2 = atd2.getBank().getKod();
                                    deposit2 = atd2.getAmaun();
                                }
                            }
                            hargaBidaan = lelong.getHargaBidaan();
                            deposit = lelong.getDeposit();
                            baki = lelong.getBaki();
                        }
                    }
                }
            }
        }
        if (permohonan.getSenaraiHakmilik().size() == 1 || enkuiri.getCaraLelong().equals("B")) {
            LOG.info("---B---");
            fasa = lelongService.findFasaPermohonanCetakSurat(idPermohonan);
            if (fasa == null) {
                if (permohonan.getPermohonanSebelum() == null) {
//                     senaraifasamohon = lelongService.getPermonanFasaRekodBidaan1(idPermohonan);
                    fasa1 = lelongService.findPermonanFasaRekodBidaan1(idPermohonan);
                } else {
//                    senaraifasamohon = lelongService.getPermonanFasaRekodBidaan1(permohonan.getPermohonanSebelum().getIdPermohonan());
                    fasa1 = lelongService.findPermonanFasaRekodBidaan1(permohonan.getPermohonanSebelum().getIdPermohonan());
                }
//                FasaPermohonan ff = senaraifasamohon.get(0);
                if (fasa1.getKeputusan().getKod().equals("AP")) {
                    show = true;
                }
                if (fasa1.getKeputusan().getKod().equals("TB")) {
                    show = false;
                }
            }
        }
        return new JSP("lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showFormRM() {
        rujuk = true;
        show = true;
        return new JSP("lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("AT", Boolean.FALSE);
        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        getContext().getRequest().setAttribute("dahBayar", Boolean.FALSE);
        show = true;
        return new JSP("lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        LOG.info("----showForm3----");
        LOG.info("conf.getProperty() " + conf.getProperty("kodNegeri"));
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            fasa = lelongService.findFasaPermohonanCetakSurat(idPermohonan);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(idPermohonan);
        }
        if (fasa != null) {
            LOG.info("----if----");
            if (fasa.getKeputusan() == null) {
                if (permohonan.getSenaraiHakmilik().size() > 1) {
                    if (enkuiri.getCaraLelong().equals("B")) {
                        show = false;
                    } else {
                        List<Lelongan> list = lelongService.listLelonganAP(idPermohonan);
                        if (list.size() > 1) {
                            show = true;
                        } else {
                            show = false;
                        }
                    }
                } else {
                    show = false;
                }
                return new JSP("lelong/keputusan_bayarBaki.jsp").addParameter("tab", "true");
            }
            getContext().getRequest().setAttribute("AT", Boolean.FALSE);
            getContext().getRequest().setAttribute("dahBayar", Boolean.TRUE);
            show = true;
            getContext().getRequest().setAttribute("bayarBaki", Boolean.TRUE);

        } else {
            LOG.info("----else----");
            if (permohonan.getSenaraiHakmilik().size() > 1) {
                if (enkuiri.getCaraLelong().equals("B")) {
                    show = false;
                } else {
                    List<Lelongan> list = lelongService.listLelongan(idPermohonan);
                    if (list.size() > 1) {
                        show = true;
                    } else {
                        show = false;
                    }
                }
            } else {
                show = false;
            }
            return new JSP("lelong/keputusan_bayarBaki.jsp").addParameter("tab", "true");
        }
        return new JSP("lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        LOG.info("----showForm5----");
        LOG.info("conf.getProperty() " + conf.getProperty("kodNegeri"));
        show = true;
        return new JSP("lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("show", Boolean.TRUE);
        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        LOG.info("showForm");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiBayaranBaki.jsp");
    }

    public Resolution pembidaPopup() {

//        // Added by nur.aqilah
//        // sediaSuratBakiBidaan stage -  maklumat pembida can't be updated
//        List<FasaPermohonan> checkSediaSuratBakiBidaan = lelongService.checkSediaSuratBakiBidaan(permohonan);
//
//        LOG.info("Stage Sedia Surat Baki Bidaan " + checkSediaSuratBakiBidaan.size());
//
//        if (checkSediaSuratBakiBidaan.size() >= 1) {
//            hideSimpan = true;
//        } else {
//            hideSimpan = false;
//        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            mlk = false;
            LOG.info("-----n9------");
            String id = getContext().getRequest().getParameter("id");
            getContext().getRequest().setAttribute("id", id);
            idLelong = getContext().getRequest().getParameter("idLelong");
            LOG.info("idLelong : " + idLelong);
            LOG.info("idPermohonan : " + idPermohonan);
            List<Lelongan> listlel = lelongService.lelongList(Long.parseLong(idLelong));
            for (Lelongan ll : listlel) {
                if (ll != null) {
                    lelong = ll;
                    hargaBidaan = lelong.getHargaBidaan();
                    deposit = lelong.getDeposit();
                    baki = lelong.getBaki();
                    pihak = ll.getPembida();
                    if (pihak != null) {
                        kodNegeri = pihak.getNegeri().getKod();
                        kodJenis = pihak.getJenisPengenalan().getKod();
                    }
                    break;
                }
            }
            //asing
            if (enkuiri.getCaraLelong().equals("A")) {

                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, idLelong);
                if (!listATD.isEmpty()) {
                    atd = listATD.get(0);
                    if (atd.getBank() != null) {
                        kodBank = atd.getBank().getKod();
                    }
                    if (atd.getCaraBayaran() != null) {
                        kodCareBayar = atd.getCaraBayaran().getKod();
                    }
                    deposit = atd.getAmaun();
                }

                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, idLelong);

                if (!listATD2.isEmpty()) {
                    atd2 = listATD2.get(0);
                    LOG.info("atd : " + atd2.getIdAkuan());
                    if (atd2.getBank() != null) {
                        kodBank2 = atd2.getBank().getKod();
                    }
                    if (atd2.getCaraBayaran() != null) {
                        kodCareBayar2 = atd2.getCaraBayaran().getKod();
                    }
                    deposit2 = atd2.getAmaun();
                }
            }
        } else {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                mlk = true;
                LOG.info("-----MLK------");
                String id = getContext().getRequest().getParameter("id");
                getContext().getRequest().setAttribute("id", id);
                idLelong = getContext().getRequest().getParameter("idLelong");
                lelong = lelongService.findLelong3(idLelong);

                LOG.info("idLelong : " + idLelong);
                LOG.info("idPermohonan : " + idPermohonan);
                if (idLelong == null || idLelong.isEmpty()) {
                    buttonSimpan = false;
                    return new JSP("lelong/pembida_popup.jsp").addParameter("popup", "true");
                } else {
                    senaraiPembida = enService.getListPembida(Long.parseLong(idLelong));

                    for (Pembida ll : senaraiPembida) {
                        if (ll != null) {
                            Long idLelong1 = ll.getLelong().getIdLelong();
                            LOG.info("id Lelong dlm table pembida:" + idLelong1);
                            Long idPembida = ll.getIdPembida();

                            if (ll.getKodStsPembida() != null) {
                                if (ll.getKodStsPembida().getKod().equals("BJ")) {
                                    pembida = ll;
                                    LOG.info("id Pembida yg BJ:" + ll.getIdPembida());
                                    BJ = true;
                                    LOG.info("hargaRizab:" + ll.getLelong().getHargaRizab());
                                } else {
                                    BJ = false;
                                    LOG.info("id Pembida yg lain dr BJ:" + ll.getIdPembida());
                                }
//                        break;
                            }
                        }
//                        if (ll == null) {
//                            addSimpleError("Sila Masukkan Nama Pembida di Utiliti Senarai Pembida");
//                            return new JSP("lelong/pembida_popup.jsp").addParameter("popup", "true");
//                        }
                    }
                }

                //asing
                if (enkuiri.getCaraLelong().equals("A")) {

                    if (pembida != null) {
                        if (pembida.getPihak() != null) {
                            pihak = pembida.getPihak();
                            kodJenis = pihak.getJenisPengenalan().getKod();
                            if (pihak.getNegeri() != null) {
                                kodNegeri = pihak.getNegeri().getKod();
                            }
                        }
                    }


                    long idLelong2 = lelong.getIdLelong();
                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, idLelong2);
                    if (!listATD.isEmpty()) {
                        atd = listATD.get(0);
                        if (atd.getBank() != null) {
                            kodBank = atd.getBank().getKod();
                        }
                        if (atd.getCaraBayaran() != null) {
                            kodCareBayar = atd.getCaraBayaran().getKod();
                        }
                        deposit = atd.getAmaun();
                    }

                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, idLelong2);

                    if (!listATD2.isEmpty()) {
                        atd2 = listATD2.get(0);
                        LOG.info("atd : " + atd2.getIdAkuan());
                        if (atd2.getBank() != null) {
                            kodBank2 = atd2.getBank().getKod();
                        }
                        if (atd2.getCaraBayaran() != null) {
                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
                        }
                        deposit2 = atd2.getAmaun();
                    }
                    hargaBidaan = lelong.getHargaBidaan();
                    if (atd != null) {
                        deposit = atd.getAmaun();
                    } else {
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            pembida = lelongService.getPembida(Long.parseLong(idLelong));
                            deposit = pembida.getLelong().getDeposit();
                        } else {
                            deposit = lelong.getDeposit();
                        }
                    }
                    baki = lelong.getBaki();


//                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, idLelong);
//                    if (!listATD.isEmpty()) {
//                        atd = listATD.get(0);
//                        if (atd.getBank() != null) {
//                            kodBank = atd.getBank().getKod();
//                        }
//                        if (atd.getCaraBayaran() != null) {
//                            kodCareBayar = atd.getCaraBayaran().getKod();
//                        }
//                        deposit = atd.getAmaun();
//                    }
//
//                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, idLelong);
//
//                    if (!listATD2.isEmpty()) {
//                        atd2 = listATD2.get(0);
//                        LOG.info("atd : " + atd2.getIdAkuan());
//                        if (atd2.getBank() != null) {
//                            kodBank2 = atd2.getBank().getKod();
//                        }
//                        if (atd2.getCaraBayaran() != null) {
//                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
//                        }
//                        deposit2 = atd2.getAmaun();
//                    }
                }
            }
        }

        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        return new JSP("lelong/pembida_popup_rekod.jsp").addParameter("popup", "true");
    }

    public Resolution pembidaPopupSemak() {
        idLelong = getContext().getRequest().getParameter("idLelong");
        List<Lelongan> listlel = lelongService.lelongList(Long.parseLong(idLelong));
        List<Pembida> listPem = enService.getListPembida(Long.parseLong(idLelong));

        //N9
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("--------N9--------");
            for (Lelongan ll : listlel) {
                if (ll != null) {
                    lelong = ll;
                    hargaBidaan = lelong.getHargaBidaan();
                    deposit = lelong.getDeposit();
                    baki = lelong.getBaki();
                    pihak = ll.getPembida();
                    if (pihak != null) {
                        kodNegeri = pihak.getNegeri().getKod();
                        kodJenis = pihak.getJenisPengenalan().getKod();
                    }
                    break;
                }
            }
        }
        //MLK
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("------MLK------");
            for (Lelongan ll : listlel) {
                if (ll != null) {
                    for (Pembida pem : listPem) {
                        pihak = pem.getPihak();
//                        deposit = pem.getLelong().getDeposit();//komen by zatie cz dia amil total deposit
                    }
                    lelong = ll;
                    hargaBidaan = lelong.getHargaBidaan();
//                    deposit = lelong.getDeposit();
                    baki = lelong.getBaki();
                    if (pihak != null) {
                        kodNegeri = pihak.getNegeri().getKod();
                        kodJenis = pihak.getJenisPengenalan().getKod();
                    }
                    break;
                }
            }
        }


        //asing
        if (enkuiri.getCaraLelong().equals("A")) {

            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, idLelong);
            if (!listATD.isEmpty()) {
                atd = listATD.get(0);
                if (atd.getBank() != null) {
                    kodBank = atd.getBank().getKod();
                }
                if (atd.getCaraBayaran() != null) {
                    kodCareBayar = atd.getCaraBayaran().getKod();
                }
                deposit = atd.getAmaun();
            }

            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, idLelong);

            if (!listATD2.isEmpty()) {
                atd2 = listATD2.get(0);
                LOG.info("atd : " + atd2.getIdAkuan());
                if (atd2.getBank() != null) {
                    kodBank2 = atd2.getBank().getKod();
                }
                if (atd2.getCaraBayaran() != null) {
                    kodCareBayar2 = atd2.getCaraBayaran().getKod();
                }
                deposit2 = atd2.getAmaun();
            }
        }
        getContext().getRequest().setAttribute("bayarBaki", Boolean.TRUE);
        return new JSP("lelong/popup_pembida_semak.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idLelong = (String) getContext().getRequest().getSession().getAttribute("idLelong");
        idPembida = (String) getContext().getRequest().getSession().getAttribute("pembida.idPembida");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            negeri = true;
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            negeri = false;
        }
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
            } else {
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
            listBil = lelongService.getLeloganDESC(idPermohonan);
            bilLelong = listBil.get(0).getBil();
            senaraiLelongan = lelongService.getLeloganDesc(enkuiri.getIdEnkuiri());
            lelong = senaraiLelongan.get(0);
            LOG.info("bilLelong : " + bilLelong);
            senaraiLelongan2 = new ArrayList<Lelongan>();
            if (permohonan.getSenaraiHakmilik().size() >= 2) {
                LOG.info("------banyak!!!------");
                //utk lebih dr satu hakmilik
                bida = false;
                senaraiLelongan2 = lelongService.listLelonganTP(idPermohonan, bilLelong);
                senaraiLelongan4 = lelongService.listLelonganAK(idPermohonan);
                LOG.info("senaraiLelongan : " + senaraiLelongan2.size());

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (enkuiri.getCaraLelong().equals("A")) {
                        senaraiPembida = new ArrayList<Pembida>();
                        Boolean statusBJ = Boolean.FALSE;
                        for (Lelongan lelongan : senaraiLelongan4) {
                            List<Pembida> listPembida = enService.getListPembida(lelongan.getIdLelong());
                            if (listPembida.size() == 1) {
                                senaraiPembida.add(listPembida.get(0));
                            } else if (listPembida.size() > 1) {
                                for (Pembida p : listPembida) {
                                    if (p.getKodStsPembida().getKod().equalsIgnoreCase("BJ")) {
                                        senaraiPembida.add(p);
                                        statusBJ = true;
                                        BJ = true;
                                        break;
                                    }
                                }

                                if (statusBJ == false) {
                                    senaraiPembida.add(listPembida.get(0));
                                    BJ = false;
                                }

                            }
                        }

                        LOG.info("::::: senaraiPembida >1 asing : " + senaraiPembida.size());
                    } else {
                        senaraiPembida = new ArrayList<Pembida>();
                        Boolean statusBJ = Boolean.FALSE;
                        String bidaic = "";
                        for (Lelongan lelongan : senaraiLelongan4) {
//                            if (pembida != null) {
//                                List<Pembida> listPembida = enService.getListPembida(lelongan.getIdLelong());

                            List<Pembida> listPembida = enService.getListPembida(lelongan.getIdLelong());
                            for (Pembida pembidalaa : listPembida) {
                                LOG.info("getNoPengenalan " + pembidalaa.getPihak().getNoPengenalan() + " , bidaic =" + bidaic);
                                Boolean loopbida = false;
                                for (Pembida lt : senaraiPembida) {
                                    if (pembidalaa.getPihak().getNoPengenalan().equals(lt.getPihak().getNoPengenalan())) {
                                        loopbida = true;
                                        break;
                                    }
                                }
                                if (!loopbida) {
                                    senaraiPembida.add(pembidalaa);
                                }
//                   
                            }

                            if (senaraiPembida != null) {
                                for (Pembida bid : senaraiPembida) {
                                    if (bid.getKodStsPembida().getKod().equals("BJ")) {
                                        pembida = bid;
                                        LOG.info("id Pembida yg BJ:" + bid.getIdPembida());
                                        BJ = true;
                                    } else {
                                        BJ = false;
                                        LOG.info("id Pembida yg lain dr BJ:" + bid.getIdPembida());
                                    }
//                        break;
                                }
                            }


//                                if (listPembida.size() == 1) {
//                                    senaraiPembida.add(listPembida.get(0));
//                                } else if (listPembida.size() > 1) {
//                                    for (Pembida p : listPembida) {
//                                        if (p.getKodStsPembida().getKod().equalsIgnoreCase("BJ")) {
//                                            senaraiPembida.add(p);
//                                            statusBJ = true;
//                                            break;
//                                        }
//                                    }

//                                    if (statusBJ == false) {
//                                        senaraiPembida.add(listPembida.get(0));
//                                    }
//                                }
//                            }
                        }
                    }

                    LOG.info("::::: senaraiPembida : " + senaraiPembida.size());
                }

                //berasingan

                if (enkuiri.getCaraLelong().equals("A")) {
                    bida = false;
                    bersameke = false;
                    pembidaSama = false;
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        if (lelong.getPembida() != null) {
                            pihak = lelong.getPembida();
////                        kodNegeri = pihak.getNegeri().getKod();
////                        kodJenis = pihak.getJenisPengenalan().getKod();
                        }
                    }
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        if (pembida != null) {
                            if (pembida.getPihak() != null) {
                                pihak = pembida.getPihak();
                                hargaBidaan = pembida.getLelong().getHargaBidaan();
                                if (pihak.getJenisPengenalan() != null) {
                                    kodJenis = pihak.getJenisPengenalan().getKod();
                                }
                                if (pihak.getNegeri() != null) {
                                    kodNegeri = pihak.getNegeri().getKod();
                                }
                            }
                        }
                    }

                    long idLelong2 = lelong.getIdLelong();
                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, idLelong2);
                    if (!listATD.isEmpty()) {
                        atd = listATD.get(0);
                        if (atd.getBank() != null) {
                            kodBank = atd.getBank().getKod();
                        }
                        if (atd.getCaraBayaran() != null) {
                            kodCareBayar = atd.getCaraBayaran().getKod();
                        }
                        deposit = atd.getAmaun();
                    }

                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, idLelong2);

                    if (!listATD2.isEmpty()) {
                        atd2 = listATD2.get(0);
                        LOG.info("atd : " + atd2.getIdAkuan());
                        if (atd2.getBank() != null) {
                            kodBank2 = atd2.getBank().getKod();
                        }
                        if (atd2.getCaraBayaran() != null) {
                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
                        }
                        deposit2 = atd2.getAmaun();
                    }
                    hargaBidaan = lelong.getHargaBidaan();
                    if (atd != null) {
                        deposit = atd.getAmaun();
                    } else {
                        deposit = lelong.getDeposit();
                    }
                    baki = lelong.getBaki();
                }

                //bersama
                if (enkuiri.getCaraLelong().equals("B")) {
                    pembidaSama = true;
                    bersameke = true;
                    bida = true;
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        if (lelong.getPembida() != null) {
                            pihak = lelong.getPembida();
                            if (pihak.getJenisPengenalan() != null) {
                                kodJenis = pihak.getJenisPengenalan().getKod();
                            }
                            if (pihak.getNegeri() != null) {
                                kodNegeri = pihak.getNegeri().getKod();
                            }
//                            kodNegeri = pihak.getNegeri().getKod();
//                            kodJenis = pihak.getJenisPengenalan().getKod();
                        }
                    }
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        if (pembida != null) {
                            if (pembida.getPihak() != null) {
                                pihak = pembida.getPihak();
                                kodJenis = pihak.getJenisPengenalan().getKod();
                                if (pihak.getNegeri() != null) {
                                    kodNegeri = pihak.getNegeri().getKod();
                                }
                            }
                        }
                    }
                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                    if (!listATD.isEmpty()) {
                        atd = listATD.get(0);
                        if (atd.getBank() != null) {
                            kodBank = atd.getBank().getKod();
                        }
                        if (atd.getCaraBayaran() != null) {
                            kodCareBayar = atd.getCaraBayaran().getKod();
                        }
                        deposit = atd.getAmaun();
                        LOG.info("deposit 10% : " + deposit);
                    }
                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                    if (!listATD2.isEmpty()) {
                        atd2 = listATD2.get(0);
                        LOG.info("atd : " + atd2.getIdAkuan());
                        if (atd2.getBank() != null) {
                            kodBank2 = atd2.getBank().getKod();
                        }
                        if (atd2.getCaraBayaran() != null) {
                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
                        }
                        deposit2 = atd2.getAmaun();
                    }
                    hargaBidaan = enkuiri.getHargaBida();
                    if (atd != null) {
                        BigDecimal b = BigDecimal.ZERO;
                        if (deposit2 != null) {
                            LOG.info("deposit tambahan : " + deposit2);
                            deposit = atd.getAmaun();
                            LOG.info("deposit 10% : " + deposit);
                            b = deposit.add(deposit2);
                            LOG.info("jumlah deposit: " + b);
//                            baki = baki.subtract(deposit2);//komen 20/8 cz tolak 2 kali
                            LOG.info("baki slps tolak deposit: " + baki);
                        } else {
                            b = deposit;
                        }
                    } else {
                        deposit = enkuiri.getDeposit();
                    }
//                    edited by farizal 15/5/15
//                    amaun Baki xkluar kt page (lelongan bersama 2hakmilik)
                    baki = lelong.getBaki();
                }
            } else {
                //utk satu hakmilik
                LOG.info("------satu!!!------");
                LOG.info("idPermohonan : " + idPermohonan);
                senaraiLelongan2 = lelongService.listLelonganTP(idPermohonan, bilLelong);
                lelong = senaraiLelongan2.get(0);
                LOG.info("Enkuiri : " + enkuiri.getIdEnkuiri());
                LOG.info("lelong : " + lelong.getIdLelong());


                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    pembidaSama = false;
//                    senaraiLelongan4 = lelongService.listLelonganAKAP1(idPermohonan);
                    senaraiLelongan4 = lelongService.listLelonganAK(idPermohonan);
                    senaraiPembida = new ArrayList<Pembida>();
                    Boolean statusBJ = Boolean.FALSE;
                    for (Lelongan lelongan : senaraiLelongan4) {
                        List<Pembida> listPembida = enService.getListPembida(lelongan.getIdLelong());
                        LOG.info("size list senarai lelongan4 :" + senaraiLelongan4.size());
                        if (listPembida.size() == 1) {
                            senaraiPembida.add(listPembida.get(0));
                        } else if (listPembida.size() > 1) {
                            for (Pembida p : listPembida) {
                                LOG.info("size list Pembida :" + listPembida.size());
                                if (p.getKodStsPembida().getKod().equalsIgnoreCase("BJ")) {
                                    LOG.info("size list Pembida BJ true :" + listPembida.size());
                                    senaraiPembida.add(p);
                                    statusBJ = true;
                                    break;
                                }

                                if (statusBJ == false) {
                                    LOG.info("size list Pembida BJ False :" + listPembida.size());
                                    senaraiPembida.add(p);
                                }
                            }

                        }
                    }
                    LOG.info("::::: senaraiPembida : " + senaraiPembida.size());

                    if (senaraiPembida != null) {
                        for (Pembida bid : senaraiPembida) {
                            pembida = bid;
                            if (bid.getKodStsPembida().getKod().equals("BJ")) {
//                                pembida = bid;
                                LOG.info("id Pembida yg BJ:" + bid.getIdPembida());
                                BJ = true;
                            } else {
                                LOG.info("id Pembida yg lain dr BJ:" + bid.getIdPembida());
                            }
//                        break;
                        }
                    }

                    LOG.info("::::: senaraiPembida >1 sama : " + senaraiPembida.size());
                }



                if (enkuiri.getCaraLelong().equals("A")) {
                    bida = true;
                    bersameke = false;
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        if (lelong.getPembida() != null) {
                            pihak = lelong.getPembida();
                            if (pihak.getJenisPengenalan() != null) {
                                kodJenis = pihak.getJenisPengenalan().getKod();
                            }
                            if (pihak.getNegeri() != null) {
                                kodNegeri = pihak.getNegeri().getKod();
                            }
////                        kodNegeri = pihak.getNegeri().getKod();
////                        kodJenis = pihak.getJenisPengenalan().getKod();
                        }
                    }
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        if (pembida != null) {
                            if (pembida.getPihak() != null) {
                                pihak = pembida.getPihak();
                                kodJenis = pihak.getJenisPengenalan().getKod();
                                if (pihak.getNegeri() != null) {
                                    kodNegeri = pihak.getNegeri().getKod();
                                }
                            }
                        }
                    }
                    long idLelong2 = lelong.getIdLelong();
                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, idLelong2);
                    if (!listATD.isEmpty()) {
                        atd = listATD.get(0);
                        if (atd.getBank() != null) {
                            kodBank = atd.getBank().getKod();
                        }
                        if (atd.getCaraBayaran() != null) {
                            kodCareBayar = atd.getCaraBayaran().getKod();
                        }
                        deposit = atd.getAmaun();
                    }

                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, idLelong2);

                    if (!listATD2.isEmpty()) {
                        atd2 = listATD2.get(0);
                        LOG.info("atd : " + atd2.getIdAkuan());
                        if (atd2.getBank() != null) {
                            kodBank2 = atd2.getBank().getKod();
                        }
                        if (atd2.getCaraBayaran() != null) {
                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
                        }
                        deposit2 = atd2.getAmaun();
                    }
                    hargaBidaan = lelong.getHargaBidaan();
                    if (atd != null) {
                        deposit = atd.getAmaun();
                    } else {
                        deposit = lelong.getDeposit();
                    }
                    baki = lelong.getBaki();
                }
                //bersama
                if (enkuiri.getCaraLelong().equals("B")) {
                    bersameke = true;
                    bida = true;
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        if (lelong.getPembida() != null) {
                            pihak = lelong.getPembida();
                            if (pihak.getJenisPengenalan() != null) {
                                kodJenis = pihak.getJenisPengenalan().getKod();
                            }
                            if (pihak.getNegeri() != null) {
                                kodNegeri = pihak.getNegeri().getKod();
                            }
////                        kodNegeri = pihak.getNegeri().getKod();
////                        kodJenis = pihak.getJenisPengenalan().getKod();
                        }
                    }
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("g sini x1");
                        if (pembida != null) {
                            LOG.info("g sini x2");
                            if (pembida.getPihak() != null) {
                                LOG.info("g sini x3");
                                pihak = pembida.getPihak();
                                LOG.info("g sini x4");
                                kodJenis = pihak.getJenisPengenalan().getKod();
                                if (pihak.getNegeri() != null) {
                                    LOG.info("g sini x5");
                                    kodNegeri = pihak.getNegeri().getKod();
                                }
                            }
                        }
                    }

//                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(lelong.getIdLelong()));
                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                    if (!listATD.isEmpty()) {
                        atd = listATD.get(0);
                        if (atd.getBank() != null) {
                            kodBank = atd.getBank().getKod();
                        }
                        if (atd.getCaraBayaran() != null) {
                            kodCareBayar = atd.getCaraBayaran().getKod();
                        }
                    }
//                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD(idPermohonan, String.valueOf(lelong.getIdLelong()));
                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                    if (!listATD2.isEmpty()) {
                        atd2 = listATD2.get(0);
                        LOG.info("atd : " + atd2.getIdAkuan());
                        if (atd2.getBank() != null) {
                            kodBank2 = atd2.getBank().getKod();
                        }
                        if (atd2.getCaraBayaran() != null) {
                            kodCareBayar2 = atd2.getCaraBayaran().getKod();
                        }
                        deposit2 = atd2.getAmaun();

                    }
                    hargaBidaan = enkuiri.getHargaBida();
                    if (atd != null) {

                        deposit = atd.getAmaun();
                        baki = enkuiri.getBaki();//komen dlu
                        BigDecimal b = BigDecimal.ZERO;
                        if (deposit2 != null) {
                            LOG.info("deposit2 test : " + deposit2);
                            deposit = atd.getAmaun();
                            LOG.info("deposit test : " + deposit);
                            b = deposit.add(deposit2);
                            LOG.info("b test: " + b);
                            LOG.info("baki sblm : " + baki);
//                            baki = baki.subtract(deposit2);
                            LOG.info("baki slps : " + baki);
                        } else {
                            b = deposit;
                        }
                        LOG.info(" kesB : ");
                        LOG.info(" deposit : " + atd.getAmaun());
                    } else {//komen dlu
                        deposit = enkuiri.getDeposit();//komen dlu
                    }//komen dlu
//                    baki = enkuiri.getBaki();//komen dlu

                }
            }
        }

        System.out.println("senarai :::: " + senaraiPembida);
    }

    public Resolution simpanKpsn() {

        senaraifasamohon = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
        LOG.info("senaraifasamohon : " + senaraifasamohon.size());
        FasaPermohonan fas = null;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        if (!senaraifasamohon.isEmpty()) {
            fas = senaraifasamohon.get(0);
            fas.setInfoAudit(ia);
        } else {
            fas = new FasaPermohonan();
            fas.setInfoAudit(ia);
            fas.setPermohonan(permohonan);
            fas.setIdAliran("rekodBidaan");
            fas.setCawangan(pengguna.getKodCawangan());
            fas.setIdPengguna(pengguna.getIdPengguna());
        }
        String kpsn = getContext().getRequest().getParameter("kpsn");
        String ulasan = getContext().getRequest().getParameter("ulasan");
        LOG.info("kpsn : " + kpsn);
        LOG.info("ulasan : " + ulasan);
        KodKeputusan kos = kodKeputusanDAO.findById(kpsn);
//        KodStatusLelongan ks = kodStatusLelonganDAO.findById("TB");
        fas.setKeputusan(kos);
        if (ulasan != null) {
            fas.setUlasan(ulasan);
        }
        fas.setTarikhHantar(new Date());
        fas.setTarikhKeputusan(new Date());
        lelongService.saveUpdate2(fas);
//
//        senaraiLelongan = lelongService.listLelonganAK(idPermohonan);
//        if (permohonan.getSenaraiHakmilik().size() > 1) {
//            if (enkuiri.getCaraLelong().equals("A")) {
//                for (Lelongan ll : senaraiLelongan) {
//                    ll.setKodStatusLelongan(ks);
//                    lelongService.saveOrUpdate(ll);
//                }
//            }
//        }

        rehydrate();
        showForm();
        return new JSP("/lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKpsn2() {
        LOG.info("conf.getProperty() " + conf.getProperty("kodNegeri"));
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            fasa = lelongService.findFasaPermohonanCetakSurat(idPermohonan);
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            fasa = lelongService.findFasaPermohonanCetakSuratLucutHak(idPermohonan);
        }
        FasaPermohonan fas = null;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        if (fasa != null) {
            fas = fasa;
            fas.setInfoAudit(ia);
        } else {
            fas = new FasaPermohonan();
            fas.setInfoAudit(ia);
            fas.setPermohonan(permohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                fas.setIdAliran("cetakSuratBakiBidaan");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                fas.setIdAliran("cetakSuratLucutHak");
            }
            fas.setCawangan(pengguna.getKodCawangan());
            fas.setIdPengguna(pengguna.getIdPengguna());
        }
        String kpsn = getContext().getRequest().getParameter("kpsn");
        String ulasan = getContext().getRequest().getParameter("ulasan");
        LOG.info("kpsn : " + kpsn);
        LOG.info("ulasan : " + ulasan);
        KodKeputusan kos = kodKeputusanDAO.findById(kpsn);
        fas.setKeputusan(kos);
        if (ulasan != null) {
            fas.setUlasan(ulasan);
        }
        fas.setTarikhHantar(new Date());
        fas.setTarikhKeputusan(new Date());
        lelongService.saveUpdate2(fas);

//        Lelongan lelonngan2 = enService.getLelongIdMohon(idPermohonan);
//        Long idPihak2 = lelonngan2.getPembida().getIdPihak();
//        LOG.info("---id Lelong----: " + idPihak2);

        senaraiLelongan4 = lelongService.listLelonganAP(idPermohonan);
        for (Lelongan lelog : senaraiLelongan4) {
            Long idLelong2 = lelog.getIdLelong();
            LOG.info("id Lelong dlm table lelong:" + idLelong2);
            senaraiPembida = enService.getListPembida(idLelong2);

            for (Pembida ll : senaraiPembida) {
                if (ll != null) {
                    Long idLelong1 = ll.getLelong().getIdLelong();
                    LOG.info("id Lelong dlm table pembida:" + idLelong1);

                    if (fas.getKeputusan().getKod().equals("LB")) {
                        if (ll.getKodStsPembida() != null) {
                            if (ll.getKodStsPembida().getKod().equals("BJ") && (ll.getLelong().getBaki().intValue() != 0)) {
                                pembida = ll;
                                LOG.info("id Pembida yg BJ:" + ll.getIdPembida());
                                ll.setKodStsPembida(kodStsPembidaDAO.findById("AT"));
                                enService.saveOrUpdate(ll);

                            } //                        break;
                        }
                    }
                }
            }
//                        if (ll == null) {
//                            addSimpleError("Sila Masukkan Nama Pembida di Utiliti Senarai Pembida");
//                            return new JSP("lelong/pembida_popup.jsp").addParameter("popup", "true");
//                        }
        }

        rehydrate();
        showForm3();
        return new JSP("/lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    public Resolution kembali() {

        senaraifasamohon = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
        FasaPermohonan fas = senaraifasamohon.get(0);
        fas.setKeputusan(null);
        fas.setUlasan(null);
        lelongService.saveUpdate2(fas);
//                idLelong = (String) getContext().getRequest().getSession().getAttribute("idLelong");
//        senaraiPembida = enService.getListPembida(Long.parseLong(idLelong));
//        Pembida bid = senaraiPembida.get(0);
//        bid.setKodStsPembida(kodStsPembidaDAO.findById("TB"));
//        enService.saveOrUpdate(bid);
        rehydrate();
        showForm();
        return new JSP("/lelong/keputusan_lelongan.jsp").addParameter("tab", "true");
    }

    public Resolution kembali3() {

        senaraifasamohon = lelongService.getPermonanFasaRekodBidaan(idPermohonan);
        FasaPermohonan fas = senaraifasamohon.get(0);
        fas.setKeputusan(null);
        fas.setUlasan(null);
        lelongService.saveUpdate2(fas);

        if (permohonan.getSenaraiHakmilik().size() == 1) {
            LOG.info("1 hm");
            senaraiLelongan2 = lelongService.listLelonganTP(idPermohonan, bilLelong);
            Lelongan lelonngan2 = senaraiLelongan2.get(0);
            Long idLelong2 = lelonngan2.getIdLelong();
            LOG.info("---id Lelong----: " + idLelong2);

            lelonngan2.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
            enService.simpan(lelonngan2);

            Pembida bid = enService.getLelongPembida(idLelong2);
            Long idPihak2 = bid.getPihak().getIdPihak();

            Pembida pemm = enService.findPihakBJ(idLelong2, idPihak2);
            pihak = pemm.getPihak();
            LOG.info("idPihak :" + pemm.getPihak().getIdPihak());
            pemm.setKodStsPembida(kodStsPembidaDAO.findById("TB"));
            enService.saveOrUpdate(pemm);
        }


        if (permohonan.getSenaraiHakmilik().size() > 1) {
//            if (enkuiri.getCaraLelong().equals("A")) {
            LOG.info("2 hm");
            for (Lelongan ll : senaraiLelongan2) {
                Long idLelong3 = ll.getIdLelong();
                ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
                enService.simpan(ll);
            }

            senaraiPembida = enService.getListPembida(lelong.getIdLelong());
            for (Pembida bids : senaraiPembida) {
                Long idPihak3 = bids.getPihak().getIdPihak();
                if (!bids.getKodStsPembida().getKod().equals("TB")) {
                    Pembida pemmm = enService.findPihakBJ(lelong.getIdLelong(), idPihak3);
                    pemmm.setKodStsPembida(kodStsPembidaDAO.findById("TB"));
                    enService.saveOrUpdate(pemmm);
                    break;
                }
            }
        }

        error = true;
        refreshpage();
        return new JSP("/lelong/keputusan_lelongan.jsp").addParameter("tab", "true");
    }

    public Resolution kembali2() {

        LOG.info("kembali 2");
        fasa = lelongService.findFasaPermohonanCetakSurat(idPermohonan);
        FasaPermohonan fas = fasa;
        fas.setKeputusan(null);
        fas.setUlasan(null);
        lelongService.saveUpdate2(fas);


        LOG.info("kembali 2A");
        senaraiLelongan4 = lelongService.listLelonganAP(idPermohonan);
        LOG.info("kembali 2B");
        for (Lelongan lelog : senaraiLelongan4) {
            LOG.info("kembali 2C");
            Long idLelong2 = lelog.getIdLelong();
            LOG.info("kembali 2D");
            LOG.info("id Lelong dlm table lelong:" + idLelong2);
            senaraiPembida = enService.getListPembida(idLelong2);
            LOG.info("kembali 2E");

            for (Pembida ll : senaraiPembida) {
                if (ll != null) {
                    Long idLelong1 = ll.getLelong().getIdLelong();
                    LOG.info("id Lelong dlm table pembida:" + idLelong1);

                    if (ll.getKodStsPembida() != null) {
                        if (ll.getKodStsPembida().getKod().equals("AT")) {
                            pembida = ll;
                            LOG.info("id Pembida yg BJ:" + ll.getIdPembida());
                            ll.setKodStsPembida(kodStsPembidaDAO.findById("BJ"));
                            enService.saveOrUpdate(ll);

                        } //                        break;
                    }
                }
            }
        }

        rehydrate();
        showForm3();
        return new JSP("/lelong/keputusan_bayarBaki.jsp").addParameter("tab", "true");
    }

    public Resolution requestParam() {
        reportName = getContext().getRequest().getParameter("namaReport");
        return new JSP("/lelong/UtilitiBayaranBaki.jsp").addParameter("popup", "true");
    }

    //satu hakmilik & Lelong Bersama
    //n9
    public Resolution save() {

        InfoAudit info = pengguna.getInfoAudit();
        AkuanTerimaDeposit ak = null;
        PermohonanPihak perPihak = null;
        List<PermohonanPihak> pp = null;
        if (permohonan.getPermohonanSebelum() == null) {
            pp = lelongService.getSenaraiPmohonPihak(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                pp = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                pp = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
            }
        }
        for (PermohonanPihak oo : pp) {
            if (oo.getJenis().getKod().equals("PG")) {
                perPihak = oo;
                break;
            }
        }
        List<AkuanTerimaDeposit> listATD = null;
        if (enkuiri.getCaraLelong().equals("A")) {
            listATD = lelongService.findATD(idPermohonan, String.valueOf(lelong.getIdLelong()));
        }
        if (enkuiri.getCaraLelong().equals("B")) {
            listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
        }


        if (listATD.isEmpty()) {
            ak = new AkuanTerimaDeposit();
        } else {
            ak = listATD.get(0);
        }

        senaraiLelongan2 = lelongService.listLelonganA(idPermohonan);
        Pihak p = null;
        if (!senaraiLelongan2.isEmpty()) {
            for (Lelongan oo : senaraiLelongan2) {
                p = oo.getPembida();
            }
        }
        if (p == null) {
            p = new Pihak();
        }

        if (enkuiri.getCaraLelong().equals("A")) {
            ak.setIdRuj(String.valueOf(senaraiLelongan2.get(0).getIdLelong()));
        }
        if (enkuiri.getCaraLelong().equals("B")) {
            ak.setIdRuj(String.valueOf(enkuiri.getIdEnkuiri()));
        }

        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        ak.setAmaun(deposit);
        if (kodBank != null) {
            KodBank kodbank = kodBankDAO.findById(kodBank);
            ak.setBank(kodbank);
        }
        String noDok = getContext().getRequest().getParameter("atd.noDokumenBayar");
        LOG.info("noDok : " + noDok);
        if (StringUtils.isNotEmpty(noDok)) {
            ak.setNoDokumenBayar(atd.getNoDokumenBayar());
        }
        KodCaraBayaran kodBayar = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            kodBayar = kodCaraBayaranDAO.findById("DB");
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if (kodCareBayar != null) {
                kodBayar = kodCaraBayaranDAO.findById(kodCareBayar);
                if (kodCareBayar.equals("T")) {
                    ak.setNoDokumenBayar(null);
                    ak.setBank(null);
                }
            }
        }
        ak.setCaraBayaran(kodBayar);
        ak.setInfoAudit(info);
        ak.setPermohonan(permohonan);
        ak.setPermohonanPihak(perPihak);
        ak.setTarikhTerima(senaraiLelongan2.get(0).getTarikhLelong());
        ak.setJenisDeposit('H');
        lelongService.saveOrUpdate(ak);

        if (deposit2 != null && deposit2.signum() > 0) {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (enkuiri.getCaraLelong().equals("A")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
            }
            if (enkuiri.getCaraLelong().equals("B")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            }

            if (listATD2.isEmpty()) {
                ak2 = new AkuanTerimaDeposit();
            } else {
                ak2 = listATD2.get(0);
            }
            ak2.setIdRuj(ak.getIdRuj());
            ak2.setAmaun(deposit2);
            if (kodBank2 != null) {
                KodBank kodbank = kodBankDAO.findById(kodBank2);
                ak2.setBank(kodbank);
            }
            noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
            LOG.info("noDok : " + noDok);
            if (StringUtils.isNotEmpty(noDok)) {
                ak2.setNoDokumenBayar(atd2.getNoDokumenBayar());
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                kodBayar = kodCaraBayaranDAO.findById("DB");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                if (kodCareBayar2 != null) {
                    kodBayar = kodCaraBayaranDAO.findById(kodCareBayar2);
                    if (kodCareBayar2.equals("T")) {
                        ak2.setNoDokumenBayar(null);
                        ak2.setBank(null);
                    }
                }
            }
            ak2.setCaraBayaran(kodBayar);
            ak2.setInfoAudit(info);
            ak2.setPermohonan(permohonan);
            ak2.setTarikhTerima(senaraiLelongan2.get(0).getTarikhLelong());
            ak2.setPermohonanPihak(perPihak);
            ak2.setJenisDeposit('T');
            lelongService.saveOrUpdate(ak2);
        } else {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (enkuiri.getCaraLelong().equals("A")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
            }
            if (enkuiri.getCaraLelong().equals("B")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            }

            if (!listATD2.isEmpty()) {
                ak2 = listATD2.get(0);
                lelongService.delete(ak2);

                atd2 = null;
                kodBank2 = null;
                deposit2 = null;
            }

        }

        LOG.info("deposit : " + deposit);
        LOG.info("deposit2 : " + deposit2);

        BigDecimal b = BigDecimal.ZERO;
        if (deposit2 != null) {
            LOG.info("deposit2 test : " + deposit2);
            b = deposit.add(deposit2);
            LOG.info("b test: " + b);
            LOG.info("baki sblm : " + baki);
//            baki = baki.subtract(deposit2);
            LOG.info("baki slps : " + baki);
        } else {
            b = deposit;
        }
        LOG.info("b : " + b);
        LOG.info("setBaki : " + baki);
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        enkuiri.setDeposit(b);
        enkuiri.setHargaBida(hargaBidaan);
        enkuiri.setBaki(baki);
        enkuiri.setInfoAudit(info);
        lelongService.saveOrUpdate(enkuiri);
        KodNegeri kod2 = kodNegeriDAO.findById(getContext().getRequest().getParameter("pihak.suratNegeri.kod"));
        p.setNegeri(kod2);
//        KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
//        p.setJenisPengenalan(jenis);
        p.setInfoAudit(info);
        p.setNama(pihak.getNama());
        KodJenisPengenalan kodP = kodJenisPengenalanDAO.findById(getContext().getRequest().getParameter("pihak.jenisPengenalan.kod"));
        p.setJenisPengenalan(kodP);
        p.setNoPengenalan(pihak.getNoPengenalan());
        p.setAlamat1(pihak.getAlamat1());
        p.setAlamat2(pihak.getAlamat2());
        p.setAlamat3(pihak.getAlamat3());
        p.setAlamat4(pihak.getAlamat4());
        p.setPoskod(pihak.getPoskod());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setNoTelefon2(pihak.getNoTelefon2());
        p.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        lelongService.saveOrUpdate(p);
        for (Lelongan oo : senaraiLelongan2) {
            oo.setPembida(p);
            oo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));
            oo.setDeposit(b);
            oo.setBaki(baki);
            oo.setHargaBidaan(hargaBidaan);
            lelongService.saveOrUpdate(oo);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("/lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    //satu hakmilik & Lelong Bersama
    //MLK
    public Resolution saveMLK() {
        LOG.info("---mlk---");
        mlk = true;
        bersameke = true;
        InfoAudit info = pengguna.getInfoAudit();
        AkuanTerimaDeposit ak = null;
        PermohonanPihak perPihak = null;
        List<PermohonanPihak> pp = null;
        if (permohonan.getPermohonanSebelum() == null) {
            pp = lelongService.getSenaraiPmohonPihak(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                pp = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                pp = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
            }
        }
        for (PermohonanPihak oo : pp) {
            if (oo.getJenis().getKod().equals("PG")) {
                perPihak = oo;
                break;
            }
        }
        List<AkuanTerimaDeposit> listATD = null;
//        if (enkuiri.getCaraLelong().equals("A")) {
        listATD = lelongService.findATD(idPermohonan, String.valueOf(lelong.getIdLelong()));
//        }
//        if (enkuiri.getCaraLelong().equals("B")) {
//            listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//        }


        if (listATD.isEmpty()) {
            ak = new AkuanTerimaDeposit();
        } else {
            ak = listATD.get(0);
        }

        senaraiLelongan2 = lelongService.listLelonganA(idPermohonan);
        Pihak p = null;

        if (!senaraiLelongan2.isEmpty()) {
            for (Lelongan lelongan : senaraiLelongan2) {
                List<Pembida> listPembida = enService.getListPembida(lelongan.getIdLelong());
                for (Pembida bids : listPembida) {
                    p = bids.getPihak();
                }
            }
        }

        if (p == null) {
            p = new Pihak();
        }

        if (enkuiri.getCaraLelong().equals("A")) {
            ak.setIdRuj(String.valueOf(senaraiLelongan2.get(0).getIdLelong()));
        }
        if (enkuiri.getCaraLelong().equals("B")) {
            ak.setIdRuj(String.valueOf(enkuiri.getIdEnkuiri()));
        }

        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        ak.setAmaun(deposit);
        if (kodBank != null) {
            KodBank kodbank = kodBankDAO.findById(kodBank);
            ak.setBank(kodbank);
        }
//        String noDok = getContext().getRequest().getParameter("atd.noDokumenBayar");
        String noDok = getContext().getRequest().getParameter("pembida.noRujukan");
        LOG.info("noDok : " + noDok);
        if (StringUtils.isNotEmpty(noDok)) {
            ak.setNoDokumenBayar(getContext().getRequest().getParameter("pembida.noRujukan"));
        }
        KodCaraBayaran kodBayar = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            kodBayar = kodCaraBayaranDAO.findById("DB");
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if (kodCareBayar != null) {
                kodBayar = kodCaraBayaranDAO.findById(kodCareBayar);
                if (kodCareBayar.equals("T")) {
                    ak.setNoDokumenBayar(null);
                    ak.setBank(null);
                }
            }
        }
        ak.setCaraBayaran(kodBayar);
        ak.setInfoAudit(info);
        ak.setPermohonan(permohonan);
        ak.setPermohonanPihak(perPihak);
        ak.setTarikhTerima(senaraiLelongan2.get(0).getTarikhLelong());
        ak.setJenisDeposit('H');

        List<AkuanTerimaDeposit> senaraiATD = lelongService.findATD(idPermohonan);
        for (AkuanTerimaDeposit at : senaraiATD) {
            if (at.getAktif().equalsIgnoreCase("Y")) {
                at.setAktif("T");
                lelongService.saveOrUpdate(at);
            }
        }

        ak.setAktif("Y");
        lelongService.saveOrUpdate(ak);

        if (deposit2 != null && deposit2.signum() > 0) {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
//            if (enkuiri.getCaraLelong().equals("A")) {
            listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
//            }
//            if (enkuiri.getCaraLelong().equals("B")) {
//                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//            }

            if (listATD2.isEmpty()) {
                ak2 = new AkuanTerimaDeposit();
            } else {
                ak2 = listATD2.get(0);
            }
            ak2.setIdRuj(ak.getIdRuj());
            ak2.setAmaun(deposit2);
            if (kodBank2 != null) {
                KodBank kodbank = kodBankDAO.findById(kodBank2);
                ak2.setBank(kodbank);
            }
            noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
            LOG.info("noDok : " + noDok);
            if (StringUtils.isNotEmpty(noDok)) {
                ak2.setNoDokumenBayar(getContext().getRequest().getParameter("atd2.noDokumenBayar"));
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                kodBayar = kodCaraBayaranDAO.findById("DB");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                if (kodCareBayar2 != null) {
                    kodBayar = kodCaraBayaranDAO.findById(kodCareBayar2);
                    if (kodCareBayar2.equals("T")) {
                        ak2.setNoDokumenBayar(null);
                        ak2.setBank(null);
                    }
                }
            }
            ak2.setCaraBayaran(kodBayar);
            ak2.setInfoAudit(info);
            ak2.setPermohonan(permohonan);
            ak2.setTarikhTerima(senaraiLelongan2.get(0).getTarikhLelong());
            ak2.setPermohonanPihak(perPihak);
            ak2.setJenisDeposit('T');

            List<AkuanTerimaDeposit> senaraiATD1 = lelongService.findATD2(idPermohonan);
            for (AkuanTerimaDeposit at : senaraiATD1) {
                if (at.getAktif().equalsIgnoreCase("Y")) {
                    at.setAktif("T");
                    lelongService.saveOrUpdate(at);
                }
            }

            ak2.setAktif("Y");
            lelongService.saveOrUpdate(ak2);
        } else {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
//            if (enkuiri.getCaraLelong().equals("A")) {
            listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
//            }
//            if (enkuiri.getCaraLelong().equals("B")) {
//                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//            }

            if (!listATD2.isEmpty()) {
                ak2 = listATD2.get(0);
                lelongService.delete(ak2);

                atd2 = null;
                kodBank2 = null;
                deposit2 = null;
            }

        }
        LOG.info("lelong satu hakmilik : ");
        LOG.info("deposit : " + deposit);
        LOG.info("deposit2 : " + deposit2);
        LOG.info("hargaRizab : " + hargaRizab);
        BigDecimal b = BigDecimal.ZERO;
//         a = BigDecimal.valueOf(0.1);
//         depositAwal = hargaRizab.multiply(a);
//            LOG.info("depositAwal : " + depositAwal);
        if (deposit2 != null) {
            b = deposit.add(deposit2);
//            baki = baki.subtract(deposit2);
        } else {
            b = deposit;
        }
        LOG.info("b : " + b);
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        enkuiri.setDeposit(b);
        enkuiri.setHargaBida(hargaBidaan);
        enkuiri.setBaki(baki);
        enkuiri.setInfoAudit(info);
        lelongService.saveOrUpdate(enkuiri);
        kodNegeri = getContext().getRequest().getParameter("pihak.suratNegeri.kod");
        KodNegeri kod2 = kodNegeriDAO.findById(kodNegeri);
        p.setNegeri(kod2);
//        KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
//        p.setJenisPengenalan(jenis);
        p.setNama(getContext().getRequest().getParameter("pembida.pihak.nama"));
        p.setNoPengenalan(getContext().getRequest().getParameter("pembida.pihak.noPengenalan"));
        p.setAlamat1(getContext().getRequest().getParameter("pembida.pihak.alamat1"));
        p.setAlamat2(getContext().getRequest().getParameter("pembida.pihak.alamat2"));
        p.setAlamat3(getContext().getRequest().getParameter("pembida.pihak.alamat3"));
        p.setAlamat4(getContext().getRequest().getParameter("pembida.pihak.alamat4"));
        p.setPoskod(getContext().getRequest().getParameter("pembida.pihak.poskod"));
        p.setNoTelefon1(getContext().getRequest().getParameter("pembida.pihak.noTelefon1"));
        p.setNoTelefon2(getContext().getRequest().getParameter("pembida.pihak.noTelefon2"));
        p.setNoTelefonBimbit(getContext().getRequest().getParameter("pembida.pihak.noTelefonBimbit"));
        p.setInfoAudit(info);
        lelongService.saveOrUpdate(p);
        for (Lelongan oo : senaraiLelongan2) {
//            oo.setPembida(p);
            oo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));
            oo.setDeposit(b);
            oo.setBaki(baki);
            oo.setHargaBidaan(hargaBidaan);
            lelongService.saveOrUpdate(oo);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("/lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    //lebih dr satu hakmilik
    //n9
    public Resolution savePembida() {
        idLelong = getContext().getRequest().getParameter("idLelong");
        Lelongan ll = lelonganDAO.findById(Long.parseLong(idLelong));
        Pihak p = ll.getPembida();
        if (p == null) {
            p = new Pihak();
        }
        PermohonanPihak perPihak = null;
        List<PermohonanPihak> pp = null;
        if (permohonan.getPermohonanSebelum() == null) {
            pp = lelongService.getSenaraiPmohonPihak(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                pp = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                pp = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
            }
        }
        for (PermohonanPihak oo : pp) {
            if (oo.getJenis().getKod().equals("PG")) {
                perPihak = oo;
                break;
            }
        }

        InfoAudit info = pengguna.getInfoAudit();
        AkuanTerimaDeposit ak = null;

        if (bersameke == true) {
            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

            if (listATD.isEmpty()) {
                ak = new AkuanTerimaDeposit();
            } else {
                ak = listATD.get(0);
            }
            ak.setIdRuj(String.valueOf(enkuiri.getIdEnkuiri()));
            if (ak.getJenisDeposit() == 'H') {
                hargaRizab = ak.getAmaun();
            }
            LOG.info("hargaRizab  : " + hargaRizab);
            ll.setHargaBidaan(hargaBidaan);
            ll.setBaki(baki);
            senaraiLelongan2 = lelongService.listLelongan(idPermohonan);
            if (!senaraiLelongan2.isEmpty()) {
                for (Lelongan oo : senaraiLelongan2) {
                    oo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));
                    lelongService.saveOrUpdate(oo);
                }
            }
        } else {
            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(ll.getIdLelong()));

            if (listATD.isEmpty()) {
                ak = new AkuanTerimaDeposit();
            } else {
                ak = listATD.get(0);
            }
            ak.setIdRuj(String.valueOf(ll.getIdLelong()));
            ll.setHargaBidaan(hargaBidaan);
            ll.setBaki(baki);
            ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));
        }

        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        ak.setAmaun(deposit);
        if (kodBank != null) {
            KodBank kodbank = kodBankDAO.findById(kodBank);
            ak.setBank(kodbank);
        }
        String noDok = getContext().getRequest().getParameter("atd.noDokumenBayar");
        LOG.info("noDok : " + noDok);
        if (StringUtils.isNotEmpty(noDok)) {
            ak.setNoDokumenBayar(atd.getNoDokumenBayar());
        }
        KodCaraBayaran kodBayar = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            kodBayar = kodCaraBayaranDAO.findById("DB");
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if (kodCareBayar != null) {
                kodBayar = kodCaraBayaranDAO.findById(kodCareBayar);
                if (kodCareBayar.equals("T")) {
                    ak.setNoDokumenBayar(null);
                    ak.setBank(null);
                }
            }
        }
        ak.setCaraBayaran(kodBayar);
        ak.setInfoAudit(info);
        ak.setPermohonan(permohonan);
        ak.setTarikhTerima(ll.getTarikhLelong());
        ak.setPermohonanPihak(perPihak);
        ak.setJenisDeposit('H');
        lelongService.saveOrUpdate(ak);

        if (deposit2 != null && deposit2.signum() > 0) {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (bersameke == true) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            } else {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(ll.getIdLelong()));
            }

            if (listATD2.isEmpty()) {
                ak2 = new AkuanTerimaDeposit();
            } else {
                ak2 = listATD2.get(0);
            }
            ak2.setIdRuj(ak.getIdRuj());
            ak2.setAmaun(deposit2);
            if (kodBank2 != null) {
                KodBank kodbank = kodBankDAO.findById(kodBank2);
                ak2.setBank(kodbank);
            }
            noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
            LOG.info("noDok : " + noDok);
            if (StringUtils.isNotEmpty(noDok)) {
                ak2.setNoDokumenBayar(atd2.getNoDokumenBayar());
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                kodBayar = kodCaraBayaranDAO.findById("DB");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                if (kodCareBayar2 != null) {
                    kodBayar = kodCaraBayaranDAO.findById(kodCareBayar2);
                    if (kodCareBayar2.equals("T")) {
                        ak2.setNoDokumenBayar(null);
                        ak2.setBank(null);
                    }
                }
            }
            ak2.setCaraBayaran(kodBayar);
            ak2.setInfoAudit(info);
            ak2.setPermohonan(permohonan);
            LOG.info("ll.getTarikhLelong() : " + ll.getTarikhLelong());
            ak2.setTarikhTerima(ll.getTarikhLelong());
            ak2.setPermohonanPihak(perPihak);
            ak2.setJenisDeposit('T');
            lelongService.saveOrUpdate(ak2);
        } else {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (enkuiri.getCaraLelong().equals("A")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
            }
            if (enkuiri.getCaraLelong().equals("B")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            }

            if (!listATD2.isEmpty()) {
                ak2 = listATD2.get(0);
                lelongService.delete(ak2);

                atd2 = null;
                kodBank2 = null;
                deposit2 = null;
            }

        }



        LOG.info("deposit : " + deposit);
        LOG.info("deposit2 : " + deposit2);
        BigDecimal b = BigDecimal.ZERO;
        if (deposit2 != null) {
            b = deposit.add(deposit2);
            LOG.info("baki awal 1 : " + baki);
            baki = baki.subtract(deposit2);
            LOG.info("baki slps 1 : " + baki);
        } else {
            b = deposit;
        }
        LOG.info("b : " + b);

        if (bersameke == true) {
            enkuiri.setHargaBida(hargaBidaan);
            enkuiri.setDeposit(b);
            enkuiri.setBaki(baki);
            lelongService.saveOrUpdate(enkuiri);
        }
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        KodNegeri kod = kodNegeriDAO.findById(kodNegeri);
        p.setNegeri(kod);
        KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
        p.setJenisPengenalan(jenis);
        p.setInfoAudit(ia);
        p.setNama(pihak.getNama());
        p.setNoPengenalan(pihak.getNoPengenalan());
        p.setAlamat1(pihak.getAlamat1());
        p.setAlamat2(pihak.getAlamat2());
        p.setAlamat3(pihak.getAlamat3());
        p.setAlamat4(pihak.getAlamat4());
        p.setPoskod(pihak.getPoskod());
        p.setNoTelefon1(pihak.getNoTelefon1());
        p.setNoTelefon2(pihak.getNoTelefon2());
        p.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        lelongService.saveOrUpdate(p);
        ll.setPembida(p);
        ll.setDeposit(b);
        ll.setInfoAudit(ia);
        lelongService.saveOrUpdate(ll);
        showForm();
        rehydrate();
//        idLelong = String.valueOf(ll.getIdLelong());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    //lebih dr satu hakmilik
    //MLK
    public Resolution savePembidaMLK() {
        LOG.info("-----save melaka-------");
        idLelong = getContext().getRequest().getParameter("idLelong");
        LOG.info("id Lelong---" + lelong.getIdLelong());
        pembida = lelongService.getPembida(Long.parseLong(idLelong));
        Lelongan ll = lelonganDAO.findById(lelong.getIdLelong());
        idPembida = getContext().getRequest().getParameter("pembida.idPembida");

        Pembida pem = enService.getPembidaID(idPembida);
        Lelongan lll = lelonganDAO.findById(pem.getLelong().getIdLelong());
        LOG.info("id lelong:" + pem.getLelong().getIdLelong());
        LOG.info("id pembida :" + pem.getIdPembida());
        LOG.info("id Pihak: " + pem.getPihak().getIdPihak());

        Pihak p = pem.getPihak();

        if (p == null) {
            p = new Pihak();
        }
        PermohonanPihak perPihak = null;
        List<PermohonanPihak> pp = null;
        if (permohonan.getPermohonanSebelum() == null) {
            pp = lelongService.getSenaraiPmohonPihak(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                pp = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                pp = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
            }
        }
        for (PermohonanPihak oo : pp) {
            if (oo.getJenis().getKod().equals("PG")) {
                perPihak = oo;
                break;
            }
        }

        InfoAudit info = pengguna.getInfoAudit();
        AkuanTerimaDeposit ak = null;

        if (bersameke == true) {
            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

            if (listATD.isEmpty()) {
                ak = new AkuanTerimaDeposit();
            } else {
                ak = listATD.get(0);
            }
            ak.setIdRuj(String.valueOf(enkuiri.getIdEnkuiri()));
            ll.setHargaBidaan(hargaBidaan);
            ll.setBaki(baki);
            senaraiLelongan2 = lelongService.listLelongan(idPermohonan);
            if (!senaraiLelongan2.isEmpty()) {
                for (Lelongan oo : senaraiLelongan2) {
                    oo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));
                    lelongService.saveOrUpdate(oo);
                }
            }
        } else {
            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(lll.getIdLelong()));
            List<AkuanTerimaDeposit> listATD5 = lelongService.findATD(idPermohonan);

            if (listATD.isEmpty()) {
                ak = new AkuanTerimaDeposit();
            } else {
                ak = listATD.get(0);
            }
            ak.setIdRuj(String.valueOf(lll.getIdLelong()));
            lll.setHargaBidaan(hargaBidaan);
            lll.setBaki(baki);
            LOG.info("baki brp : " + baki);
            lll.setKodStatusLelongan(kodStatusLelonganDAO.findById("AP"));

            for (AkuanTerimaDeposit at : listATD) {
                if (at.getAktif().equalsIgnoreCase("Y")) {
                    at.setAktif("T");
                    lelongService.saveOrUpdate(at);

                }
            }
        }

        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        ak.setAmaun(deposit);
        if (kodBank != null) {
            KodBank kodbank = kodBankDAO.findById(kodBank);
            ak.setBank(kodbank);
        }
        String noDok = getContext().getRequest().getParameter("pembida.noRujukan");
        LOG.info("noDok : " + noDok);
        if (StringUtils.isNotEmpty(noDok)) {
            ak.setNoDokumenBayar(getContext().getRequest().getParameter("pembida.noRujukan"));
            pem.setNoRujukan(getContext().getRequest().getParameter("pembida.noRujukan"));
        }
        KodCaraBayaran kodBayar = null;
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            kodBayar = kodCaraBayaranDAO.findById("DB");
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            if (kodCareBayar != null) {
                kodBayar = kodCaraBayaranDAO.findById(kodCareBayar);
                if (kodCareBayar.equals("T")) {
                    ak.setNoDokumenBayar(null);
                    ak.setBank(null);
                }
            }
        }
        ak.setCaraBayaran(kodBayar);
        ak.setInfoAudit(info);
        ak.setPermohonan(permohonan);
        ak.setTarikhTerima(ll.getTarikhLelong());
        ak.setPermohonanPihak(perPihak);
        ak.setJenisDeposit('H');

        List<AkuanTerimaDeposit> senaraiATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//        List<AkuanTerimaDeposit> senaraiATD = lelongService.findATD(idPermohonan);
        for (AkuanTerimaDeposit at : senaraiATD) {
            if (at.getAktif().equalsIgnoreCase("Y")) {
                at.setAktif("T");
                lelongService.saveOrUpdate(at);

            }
        }

        ak.setAktif("Y");
        lelongService.saveOrUpdate(ak);
        enService.saveOrUpdate(pem);

        if (deposit2 != null && deposit2.signum() > 0) {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (bersameke == true) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            } else {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lll.getIdLelong()));
            }

            List<AkuanTerimaDeposit> listATD5 = lelongService.findATD2(idPermohonan);

            if (listATD2.isEmpty()) {
                ak2 = new AkuanTerimaDeposit();
            } else {
                ak2 = listATD2.get(0);
            }

            for (AkuanTerimaDeposit at : listATD2) {
                if (at.getAktif().equalsIgnoreCase("Y")) {
                    at.setAktif("T");
                    lelongService.saveOrUpdate(at);

                }
            }

            ak2.setIdRuj(ak.getIdRuj());
            ak2.setAmaun(deposit2);
            if (kodBank2 != null) {
                KodBank kodbank = kodBankDAO.findById(kodBank2);
                ak2.setBank(kodbank);
            }
            noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
            LOG.info("noDok : " + noDok);
            if (StringUtils.isNotEmpty(noDok)) {
                ak2.setNoDokumenBayar(atd2.getNoDokumenBayar());
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                kodBayar = kodCaraBayaranDAO.findById("DB");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                if (kodCareBayar2 != null) {
                    kodBayar = kodCaraBayaranDAO.findById(kodCareBayar2);
                    if (kodCareBayar2.equals("T")) {
                        ak2.setNoDokumenBayar(null);
                        ak2.setBank(null);
                    }
                }
            }
            ak2.setCaraBayaran(kodBayar);
            ak2.setInfoAudit(info);
            ak2.setPermohonan(permohonan);
            LOG.info("ll.getTarikhLelong() : " + ll.getTarikhLelong());
            ak2.setTarikhTerima(ll.getTarikhLelong());
            ak2.setPermohonanPihak(perPihak);
            ak2.setJenisDeposit('T');

            List<AkuanTerimaDeposit> senaraiATD1 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            for (AkuanTerimaDeposit at : senaraiATD1) {
                if (at.getAktif().equalsIgnoreCase("Y")) {
                    at.setAktif("T");
                    lelongService.saveOrUpdate(at);
                }
            }

            ak2.setAktif("Y");
            lelongService.saveOrUpdate(ak2);
        } else {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (enkuiri.getCaraLelong().equals("A")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
            }
            if (enkuiri.getCaraLelong().equals("B")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            }

            if (!listATD2.isEmpty()) {
                ak2 = listATD2.get(0);
                lelongService.delete(ak2);

                atd2 = null;
                kodBank2 = null;
                deposit2 = null;
            }

        }

        LOG.info("deposit : " + deposit);
        LOG.info("deposit2 : " + deposit2);
        BigDecimal b = BigDecimal.ZERO;
        a = BigDecimal.valueOf(0.1);
        if (deposit2 != null) {
            b = deposit.add(deposit2);
//            baki = baki.subtract(deposit2);
        } else {
            b = deposit;

        }
        LOG.info("b : " + b);

        if (bersameke == true) {
            LOG.info("baki cni : " + baki);
            enkuiri.setHargaBida(hargaBidaan);
            enkuiri.setDeposit(b);
            enkuiri.setBaki(baki);
            lelongService.saveOrUpdate(enkuiri);
        }
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        kodNegeri = getContext().getRequest().getParameter("pihak.suratNegeri.kod");
        KodNegeri kod = kodNegeriDAO.findById(kodNegeri);
        p.setNegeri(kod);
        KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
        p.setJenisPengenalan(jenis);
        p.setInfoAudit(ia);
        p.setNama(getContext().getRequest().getParameter("pembida.pihak.nama"));
        p.setNoPengenalan(getContext().getRequest().getParameter("pembida.pihak.noPengenalan"));
        p.setAlamat1(getContext().getRequest().getParameter("pembida.pihak.alamat1"));
        p.setAlamat2(getContext().getRequest().getParameter("pembida.pihak.alamat2"));
        p.setAlamat3(getContext().getRequest().getParameter("pembida.pihak.alamat3"));
        p.setAlamat4(getContext().getRequest().getParameter("pembida.pihak.alamat4"));
        p.setPoskod(getContext().getRequest().getParameter("pembida.pihak.poskod"));
        p.setNoTelefon1(getContext().getRequest().getParameter("pembida.pihak.noTelefon1"));
        p.setNoTelefon2(getContext().getRequest().getParameter("pembida.pihak.noTelefon2"));
        p.setNoTelefonBimbit(getContext().getRequest().getParameter("pembida.pihak.noTelefonBimbit"));
        lelongService.saveOrUpdate(p);
//        ll.setPembida(p);
        lll.setDeposit(b);
        lll.setInfoAudit(ia);
        lelongService.saveOrUpdate(lll);
        showForm();
        rehydrate();
//        idLelong = String.valueOf(ll.getIdLelong());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    //lebih dr satu hakmilik
    //MLK
    // ni utk deposit tambahan klu wat selepas save yg pertama
    public Resolution saveDepositTambahan() {

        LOG.info("-----save deposit tambahan-------");
        idLelong = getContext().getRequest().getParameter("idLelong");
        LOG.info("id Lelong---" + lelong.getIdLelong());
        Lelongan ll = lelonganDAO.findById(lelong.getIdLelong());
        idPembida = getContext().getRequest().getParameter("pembida.idPembida");

        Pembida pem = enService.getPembidaID(idPembida);
        Lelongan lll = lelonganDAO.findById(pem.getLelong().getIdLelong());
        LOG.info("id lelong:" + pem.getLelong().getIdLelong());
        LOG.info("id pembida :" + pem.getIdPembida());
        LOG.info("id Pihak: " + pem.getPihak().getIdPihak());

        Pihak p = pem.getPihak();

        String noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
        LOG.info("noDok : " + noDok);

        KodCaraBayaran kodBayar = null;
        AkuanTerimaDeposit ak = null;
        PermohonanPihak perPihak = null;
        List<PermohonanPihak> pp = null;
        ak = new AkuanTerimaDeposit();
        InfoAudit info = pengguna.getInfoAudit();

        ak.setIdRuj(String.valueOf(lll.getIdLelong()));


        if (permohonan.getPermohonanSebelum() == null) {
            pp = lelongService.getSenaraiPmohonPihak(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                pp = lelongService.getSenaraiPmohonPihak(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                pp = lelongService.getSenaraiPmohonPihak(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
            }
        }
        for (PermohonanPihak oo : pp) {
            if (oo.getJenis().getKod().equals("PG")) {
                perPihak = oo;
                break;
            }
        }


        if (deposit2 != null && deposit2.signum() > 0) {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (bersameke == true) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            } else {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(ll.getIdLelong()));
            }

//            if (listATD2.isEmpty()) {
            ak2 = new AkuanTerimaDeposit();
//            } else {
//                ak2 = listATD2.get(0);
//            }
            ak2.setIdRuj(ak.getIdRuj());
            ak2.setAmaun(deposit2);
            if (kodBank2 != null) {
                KodBank kodbank = kodBankDAO.findById(kodBank2);
                ak2.setBank(kodbank);
            }
            noDok = getContext().getRequest().getParameter("atd2.noDokumenBayar");
            LOG.info("noDok : " + noDok);
            if (StringUtils.isNotEmpty(noDok)) {
                ak2.setNoDokumenBayar(atd2.getNoDokumenBayar());
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                kodBayar = kodCaraBayaranDAO.findById("DB");
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //negeri9
                if (kodCareBayar2 != null) {
                    kodBayar = kodCaraBayaranDAO.findById(kodCareBayar2);
                    if (kodCareBayar2.equals("T")) {
                        ak2.setNoDokumenBayar(null);
                        ak2.setBank(null);
                    }
                }
            }
            ak2.setCaraBayaran(kodBayar);
            ak2.setInfoAudit(info);
            ak2.setPermohonan(permohonan);
            LOG.info("ll.getTarikhLelong() : " + ll.getTarikhLelong());
            ak2.setTarikhTerima(ll.getTarikhLelong());
            ak2.setPermohonanPihak(perPihak);
            ak2.setJenisDeposit('T');

            List<AkuanTerimaDeposit> senaraiATD1 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            for (AkuanTerimaDeposit at : senaraiATD1) {
                if (at.getAktif().equalsIgnoreCase("Y")) {
                    at.setAktif("T");
                    lelongService.saveOrUpdate(at);
                }
            }

            ak2.setAktif("Y");
            lelongService.saveOrUpdate(ak2);
        } else {
            AkuanTerimaDeposit ak2 = null;
            List<AkuanTerimaDeposit> listATD2 = null;
            if (enkuiri.getCaraLelong().equals("A")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(lelong.getIdLelong()));
            }
            if (enkuiri.getCaraLelong().equals("B")) {
                listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
            }

            if (!listATD2.isEmpty()) {
                ak2 = listATD2.get(0);
                lelongService.delete(ak2);

                atd2 = null;
                kodBank2 = null;
                deposit2 = null;
            }

        }

        showForm();
        rehydrate();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution tarikPermohonan() {
        LOG.info("-----tarikPermohonan-----");
        LOG.info("senaraiLelongan2 : " + senaraiLelongan2.size());
        for (int i = 0; i < senaraiLelongan2.size(); i++) {
            String tp = "";
            tp = getContext().getRequest().getParameter("ada" + i);
            LOG.info("tp : " + tp);
            if (StringUtils.isNotEmpty(tp)) {
                if (tp.equals("P")) {
                    Lelongan ll = senaraiLelongan2.get(i);
                    ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TP"));
                    lelongService.saveOrUpdate(ll);
                }
            }
        }
        rehydrate();
        showForm();
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution deletePembida2() {
        LOG.info("-----delete-----");
        String idLelongle = getContext().getRequest().getParameter("idLelong");
        LOG.info("idLelong : " + idLelongle);
        String idPihakle = getContext().getRequest().getParameter("idPihak");
        LOG.info("idPihakle : " + idPihakle);
        Lelongan dd = lelonganDAO.findById(Long.parseLong(idLelongle));
        Pembida ppp = enService.findPihak(Long.parseLong(idPihakle));
        Long idPihak3 = ppp.getPihak().getIdPihak();
        LOG.info("id Pihak :" + idPihak3);
        dd.setHargaBidaan(null);
        dd.setBaki(null);
        dd.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
        lelongService.saveOrUpdate(dd);

        if (idPihak3 != 0) {
            Pihak pp = pihakDAO.findById(idPihak3);
            lelongService.deletePihak(pp);
            lelongService.deletePihak(ppp);

        }
        List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(dd.getIdLelong()));
        if (!listATD.isEmpty()) {
            for (AkuanTerimaDeposit aTD : listATD) {
                lelongService.delete(aTD);
            }
        }
        List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(dd.getIdLelong()));
        if (!listATD2.isEmpty()) {
            for (AkuanTerimaDeposit aTD : listATD2) {
                lelongService.delete(aTD);
            }
        }
        showForm();
        rehydrate();
        return new JSP("lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        showForm();
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("/lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    public Resolution reset() {
        InfoAudit ia = pengguna.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        String idLelong2 = getContext().getRequest().getParameter("idLelong");
        LOG.info("idLelong : " + idLelong2);
        lelong = lelonganDAO.findById(Long.parseLong(idLelong2));

        if (bersameke == false) {
            //asing
            LOG.info("idPermohonan: " + idPermohonan);
            LOG.info("idLelong :" + idLelong);
            lelong.setBaki(BigDecimal.valueOf(0));
            enService.saveResetBaki(lelong, pengguna);
        } else {
            //bersama
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
        show = true;
        return new JSP("/lelong/rekod_bidaan_semak.jsp").addParameter("tab", "true");
    }

    //for melaka only
    public Resolution simpanPembida() {
        //Pembida Berjaya
        LOG.info("------simpan-------");
        String idpem = getContext().getRequest().getParameter("pembida");
        LOG.info("----------pembida---- :" + idpem);
        if (idpem == null) {
            addSimpleError("Sila Masukkan Nama Pembida di Utiliti Senarai Pembida");
            return new JSP("lelong/pembida_popup.jsp").addParameter("popup", "true");
        }
        LOG.info("idpem : " + idpem);

        Pembida pem = pembidaDAO.findById(Long.valueOf(getContext().getRequest().getParameter("pembida")));
        lelong = lelongService.findLelong3(idLelong);
        idLelong = getContext().getRequest().getParameter("idLelong");
        LOG.info("id lelong dlm ini :" + idLelong);



        InfoAudit ia = pengguna.getInfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (pem != null) {
            pem.setIdPembida(Long.valueOf(idpem));
            KodStsPembida ksl = new KodStsPembida();
            ksl.setKod("BJ");
            pem.setKodStsPembida(ksl);
            pem.setInfoAudit(ia);
            enService.saveOrUpdate(pem);
        }


//        rehydrate();
        if (pem.getKodStsPembida().getKod().equals("BJ")) {
            BJ = true;
            mlk = true;
        }


        pembidaPopup();
//        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan..");

        getContext().getRequest().setAttribute("bayarBaki", Boolean.FALSE);
        getContext().getRequest().setAttribute("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/pembida_popup.jsp").addParameter("popup", "true");
        return new JSP("lelong/pembida_popup.jsp").addParameter("popup", "true");
    }

    //for melaka only
    public Resolution simpanPembidaFlow() {
        //Pembida Berjaya
        LOG.info("------simpan pembida flow-------");
        String idpem = getContext().getRequest().getParameter("pembida");
        getContext().getRequest().setAttribute("pembida", pembida);
        LOG.info("----------pembida---- :" + idpem);
        LOG.info("idpem : " + idpem);

        InfoAudit ia = pengguna.getInfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        Pembida pem = pembidaDAO.findById(Long.valueOf(getContext().getRequest().getParameter("pembida")));

        if (permohonan.getSenaraiHakmilik().size() > 1) {
            if (enkuiri.getCaraLelong().equals("B")) {
                LOG.info("--2 hm sama--");
                pembidaSama = true;
                list2 = enService.getListPembidaPihak(Long.valueOf(getContext().getRequest().getParameter("pembida")));
                for (Pembida bida1 : list2) {
                    KodStsPembida ksl = new KodStsPembida();
                    ksl.setKod("BJ");
                    bida1.setKodStsPembida(ksl);
                    enService.saveOrUpdate(bida1);
                    if (bida1.getKodStsPembida().getKod().equals("BJ")) {
                        BJ = true;
                        negeri = false;
                    }
                }

                BJ = true;
                negeri = false;


            } else if (enkuiri.getCaraLelong().equals("A")) {
                pembidaSama = false;
                LOG.info("--2 hm asing--");
//                Pembida pem = pembidaDAO.findById(Long.valueOf(getContext().getRequest().getParameter("pembida")));
                Pembida pem1 = enService.getPembidaID(idpem);
                LOG.info("id Pem laaa :" + pem1.getIdPembida());
//            if (pem1 != null) {
                KodStsPembida ksl = new KodStsPembida();
                ksl.setKod("BJ");
                pem1.setKodStsPembida(ksl);
                pem1.setInfoAudit(ia);
                enService.saveOrUpdate(pem1);
                if (pem1.getKodStsPembida().getKod().equals("BJ")) {
                    BJ = true;
                    negeri = false;
                }


            }
        }

        if (permohonan.getSenaraiHakmilik().size() == 1) {
            if (enkuiri.getCaraLelong().equals("B")) {
                LOG.info("--1 hm--");
                pembidaSama = false;
                Pembida pem1 = enService.getPembidaID(idpem);
                LOG.info("id Pem laaa :" + pem1.getIdPembida());
//            if (pem1 != null) {
                KodStsPembida ksl = new KodStsPembida();
                ksl.setKod("BJ");
                pem1.setKodStsPembida(ksl);
                pem1.setInfoAudit(ia);
                enService.saveOrUpdate(pem1);
                if (pem1.getKodStsPembida().getKod().equals("BJ")) {
                    BJ = true;
                    negeri = false;
//                }
                }
            }

        }

//
//            if (pem.getKodStsPembida().getKod().equals("BJ")) {
//                BJ = true;
//                negeri = false;
//            }
        showForm();
        rehydrate();

        return new JSP("/lelong/rekod_bidaan.jsp").addParameter("tab", "true");
    }

    //added by nur.aqilah
    public Resolution viewDetail() {
        LOG.info("---viewDetail---");
        idLelong = getContext().getRequest().getParameter("idLelong");

        LOG.info("id lelong : " + idLelong);

        pembida = lelongService.getPembida(Long.parseLong(idLelong));
        lelong = lelongService.findLelong3(idLelong);
        idPermohonan = lelong.getPermohonan().getIdPermohonan();
        setHargaRizab(lelong.getHargaRizab());

        //editted by nur.aqilah
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            mlk= true;
            pembida = lelongService.getPembida(Long.parseLong(idLelong));
            
        } else {
            mlk= false;
            pihak=lelong.getPembida();

        }
        LOG.info("id permohonan : " + idPermohonan);
        LOG.info("Harga Bidaan: " + lelong.getHargaBidaan());
        LOG.info("Baki: " + lelong.getBaki());
        List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, Long.parseLong(idLelong));
        if (!listATD.isEmpty()) {
            atd = listATD.get(0);
            if (atd.getBank() != null) {
                kodBank = atd.getBank().getKod();
            }
            if (atd.getCaraBayaran() != null) {
                kodCareBayar = atd.getCaraBayaran().getKod();
            }

            deposit = atd.getAmaun();
        }

        List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, Long.parseLong(idLelong));

        if (!listATD2.isEmpty()) {
            atd2 = listATD2.get(0);
            LOG.info("atd : " + atd2.getIdAkuan());
            if (atd2.getBank() != null) {
                kodBank2 = atd2.getBank().getKod();
            }
            if (atd2.getCaraBayaran() != null) {
                kodCareBayar2 = atd2.getCaraBayaran().getKod();
            }
            deposit2 = atd2.getAmaun();
        }

//        rehydrate();
//        showForm1();

        return new JSP("lelong/semak_pembida_info.jsp").addParameter("popup", "true");
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
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

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisabled1() {
        return disabled1;
    }

    public void setDisabled1(String disabled1) {
        this.disabled1 = disabled1;
    }

    public boolean isBida() {
        return bida;
    }

    public void setBida(boolean bida) {
        this.bida = bida;
    }

    public List<Lelongan> getSenaraiLelongan2() {
        return senaraiLelongan2;
    }

    public void setSenaraiLelongan2(List<Lelongan> senaraiLelongan2) {
        this.senaraiLelongan2 = senaraiLelongan2;
    }

    public boolean isOwh() {
        return owh;
    }

    public void setOwh(boolean owh) {
        this.owh = owh;
    }

    public String getKodJenis() {
        return kodJenis;
    }

    public void setKodJenis(String kodJenis) {
        this.kodJenis = kodJenis;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isBersameke() {
        return bersameke;
    }

    public void setBersameke(boolean bersameke) {
        this.bersameke = bersameke;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public AkuanTerimaDeposit getAtd() {
        return atd;
    }

    public void setAtd(AkuanTerimaDeposit atd) {
        this.atd = atd;
    }

    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }

    public FasaPermohonan getFasa() {
        return fasa;
    }

    public void setFasa(FasaPermohonan fasa) {
        this.fasa = fasa;
    }

    public AkuanTerimaDeposit getAtd2() {
        return atd2;
    }

    public void setAtd2(AkuanTerimaDeposit atd2) {
        this.atd2 = atd2;
    }

    public BigDecimal getDeposit2() {
        return deposit2;
    }

    public void setDeposit2(BigDecimal deposit2) {
        this.deposit2 = deposit2;
    }

    public BigDecimal getHargaBidaan2() {
        return hargaBidaan2;
    }

    public void setHargaBidaan2(BigDecimal hargaBidaan2) {
        this.hargaBidaan2 = hargaBidaan2;
    }

    public String getKodBank2() {
        return kodBank2;
    }

    public void setKodBank2(String kodBank2) {
        this.kodBank2 = kodBank2;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<Lelongan> getListBil() {
        return listBil;
    }

    public void setListBil(List<Lelongan> listBil) {
        this.listBil = listBil;
    }

    public int getBilLelong() {
        return bilLelong;
    }

    public void setBilLelong(int bilLelong) {
        this.bilLelong = bilLelong;
    }

    public boolean isRujuk() {
        return rujuk;
    }

    public void setRujuk(boolean rujuk) {
        this.rujuk = rujuk;
    }

    public boolean isNegeri() {
        return negeri;
    }

    public void setNegeri(boolean negeri) {
        this.negeri = negeri;
    }

    public String getKodCareBayar() {
        return kodCareBayar;
    }

    public void setKodCareBayar(String kodCareBayar) {
        this.kodCareBayar = kodCareBayar;
    }

    public String getKodCareBayar2() {
        return kodCareBayar2;
    }

    public void setKodCareBayar2(String kodCareBayar2) {
        this.kodCareBayar2 = kodCareBayar2;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public String getIdPembida() {
        return idPembida;
    }

    public void setIdPembida(String idPembida) {
        this.idPembida = idPembida;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public boolean isMlk() {
        return mlk;
    }

    public void setMlk(boolean mlk) {
        this.mlk = mlk;
    }

    public boolean isBJ() {
        return BJ;
    }

    public void setBJ(boolean BJ) {
        this.BJ = BJ;
    }

    public List<String> getListIDHakmilik() {
        return listIDHakmilik;
    }

    public void setListIDHakmilik(List<String> listIDHakmilik) {
        this.listIDHakmilik = listIDHakmilik;
    }

    public boolean isButtonSimpan() {
        return buttonSimpan;
    }

    public void setButtonSimpan(boolean buttonSimpan) {
        this.buttonSimpan = buttonSimpan;
    }

    public boolean isAdapembida() {
        return adapembida;
    }

    public void setAdapembida(boolean adapembida) {
        this.adapembida = adapembida;
    }

    public List<Lelongan> getSenaraiLelongan4() {
        return senaraiLelongan4;
    }

    public void setSenaraiLelongan4(List<Lelongan> senaraiLelongan4) {
        this.senaraiLelongan4 = senaraiLelongan4;
    }

    public List<Pembida> getList2() {
        return list2;
    }

    public void setList2(List<Pembida> list2) {
        this.list2 = list2;
    }

    public boolean isPembidaSama() {
        return pembidaSama;
    }

    public void setPembidaSama(boolean pembidaSama) {
        this.pembidaSama = pembidaSama;
    }

    public FasaPermohonan getFasa1() {
        return fasa1;
    }

    public void setFasa1(FasaPermohonan fasa1) {
        this.fasa1 = fasa1;
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

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public List<Lelongan> getSenaraiLelongan5() {
        return senaraiLelongan5;
    }

    public void setSenaraiLelongan5(List<Lelongan> senaraiLelongan5) {
        this.senaraiLelongan5 = senaraiLelongan5;
    }

    public List<DokumenKewangan> getCheckBayaranPerintah() {
        return checkBayaranPerintah;
    }

    public void setCheckBayaranPerintah(List<DokumenKewangan> checkBayaranPerintah) {
        this.checkBayaranPerintah = checkBayaranPerintah;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    /**
     * @return the a
     */
    public BigDecimal getA() {
        return a;
    }

    /**
     * @return the hargaRizab
     */
    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    /**
     * @param hargaRizab the hargaRizab to set
     */
    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    /**
     * @return the depositAwal
     */
    public BigDecimal getDepositAwal() {
        return depositAwal;
    }

    /**
     * @param depositAwal the depositAwal to set
     */
    public void setDepositAwal(BigDecimal depositAwal) {
        this.depositAwal = depositAwal;
    }

    public boolean isHideSimpan() {
        return hideSimpan;
    }

    public void setHideSimpan(boolean hideSimpan) {
        this.hideSimpan = hideSimpan;
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import net.sourceforge.stripes.action.ForwardResolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import java.math.BigDecimal;
import org.hibernate.Transaction;
import java.util.Iterator;
import net.sourceforge.stripes.action.DontValidate;
import etanah.service.common.PermohonanPihakService;

@UrlBinding("/pengambilan/gantiRugiNS")
public class perbicaraanKosGantiRugiNSActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(perbicaraanKosGantiRugiNSActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PenggunaPerananDAO penggunaPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    public perbicaraanKosGantiRugiNSActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
    }
    private boolean flag = false;
//    private List<Akaun> akaunList;
    private Permohonan permohonan;
    private String idHakmilik;
    private Long idPihak;
    private Long idPermohonanPihak;
    private String kodDokumen;
    private Pemohon pemohon;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> permohonanPihak1List;
    private List<Pihak> getSenaraiPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanTuntutanKos permohonanTuntutanKos1;
    //private List<PermohonanTuntutanKos> senaraiKosgantiRugi;
    private List<PermohonanTuntutanKos> senaraiTuntutanKos;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal jumCaraBayar = new BigDecimal(0.00);
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private BigDecimal amaunSebenarTuanTotal = new BigDecimal(0.00);
    private BigDecimal amaunSebenarTotal = new BigDecimal(0.00);
    private BigDecimal tuanTanahAmaunTotal = new BigDecimal(0.00);
    private List<BigDecimal> hakmilikAmaunList = new ArrayList<BigDecimal>();
    private List<BigDecimal> hakmilikSebenarList = new ArrayList<BigDecimal>();
    private List<BigDecimal> tuanTanahAmaun = new ArrayList<BigDecimal>();
    private List<BigDecimal> amaunSebenarTotalList = new ArrayList<BigDecimal>();
    private List<BigDecimal> hakmilikAmaun = new ArrayList<BigDecimal>();
    private List<BigDecimal> amaunSebenarList = new ArrayList<BigDecimal>();
//    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp";
//    private static final String POPUP_VIEW = "pengambilan/negerisembilan/penarikanbalik/penambahan_kosgantirugiNS.jsp";
    private String nama;
    private BigDecimal luasTerlibat;
//    private Converter converter;
    private String item;
    private PermohonanTuntutanKos item1;
    private PermohonanTuntutanKos amaunTuntutan1;
    private PermohonanTuntutanKos amaunSebenar1;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idKos;
    //private String[] AmaunDituntut;
    private BigDecimal amaunTuntutan;
    //private String[] AmaunDiLaksanakan;
    private BigDecimal amaunSebenar;
//    private BigDecimal jum;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private int bil;
    private boolean showMaklumatPemilik;
    private List<PermohonanTuntutanKos> permohonanTuntutanKosList;

    @DefaultHandler
    public Resolution defaultPage() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            pengambilanService.simpanPermohonanPihak(permohonan, peng);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            pengambilanService.simpanPermohonanPihak(permohonan.getPermohonanSebelum(), peng);
        }
        pengambilanService.simpanPermohonanSebelum(permohonan, peng);
        calculateMaklumatHakmilikTotal();
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution showFormTptd() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            pengambilanService.simpanPermohonanPihak(permohonan, peng);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            pengambilanService.simpanPermohonanPihak(permohonan.getPermohonanSebelum(), peng);
        }
        pengambilanService.simpanPermohonanSebelum(permohonan, peng);
        calculateMaklumatHakmilikTotal();
        getContext().getRequest().getSession().setAttribute("Tptd", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPtPengambilan() {
        //  getContext().getRequest().setAttribute("detail", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            senaraiTuntutanKos = pengambilanService.findByIDMohonKos(idPermohonan);
            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilik = pengambilanService.findByIdHakmilik(idHakmilik);
                hakmilik = hakmilikDAO.findById(idHakmilik);
                
              //  permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakListAktif(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
            }
        }

//            item = getContext().getRequest().getParameter("item");
//            if (idPermohonan != null) {
////                permohonan = permohonanDAO.findById(idPermohonan);
//                permohonanTuntutanKos = new PermohonanTuntutanKos();
//                permohonanTuntutanKos.setPermohonan(permohonan);
////                String[] key = {"permohonan"};
////                Object[] value = {permohonan};
//                List<PermohonanTuntutanKos> list = pengambilanService.findByIDMohonKos(permohonanSblm.getIdPermohonan());
//                if (list != null && list.size() == 1) {
//                    permohonanTuntutanKos = list.get(0);
//                    item = permohonanTuntutanKos.getItem();
//                }
//                else if (idKos != null && list.size() != 0) {
//                    for (PermohonanTuntutanKos ptk : list) {
//                        if (String.valueOf(ptk.getIdKos()).equals(idKos)) {
//                            permohonanTuntutanKos = ptk;
//                            break;
//                        }
//                    }
//                } else {
//                    addSimpleError("Tiada Data Data MOHON_TUNTUT_KOS");
//                    permohonanTuntutanKos = null;
//                }
//            }

        if (permohonan == null) {
            addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
            return;
        }
    }

    public Resolution hakmilikDetails() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        
      //  permohonanPihak1List = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, hakmilik.getIdHakmilik());
        permohonanPihak1List = permohonanPihakService.getAllSenaraiPmohonPihakByHakmilikAndMohonAktif(idPermohonan, hakmilik.getIdHakmilik());

        for (PermohonanPihak pp : permohonanPihak1List) {
            permohonanTuntutanKosList = pengambilanService.getIdPermohonan(idPermohonan);
        }
        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
//        List<PermohonanPihak> permohonanPihak1List = new ArrayList<PermohonanPihak>();
//        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
        permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
        logger.info("id mohon " + idPermohonan);
        logger.info("id hakmilik " + hakmilik.getIdHakmilik());
        logger.info("id pihak " + idPihak);
        logger.info("permohonanPihak1List " + permohonanPihak1List.size());

        for (PermohonanPihak pp : permohonanPihak1List) {
            logger.info("PermohonanPihak pp ");
            if (pp != null) {
                logger.info("pp != null");
                 logger.info("pp :"+pp.getIdPermohonanPihak());
//                permohonanPihak = pp;
//                getContext().getRequest().getSession().setAttribute("permohonanPihak", permohonanPihak);
                permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(pp.getIdPermohonanPihak());
//                getContext().getRequest().setAttribute("showTuntutan", Boolean.TRUE);
            } else {
                addSimpleError("Tiada Data Dalam Pangkalan Data Pada Item Yang Di klik");
            }

            if (permohonanTuntutanKosList != null && permohonanTuntutanKosList.size() > 0) {
                for (PermohonanTuntutanKos permohonanTuntutanKos : permohonanTuntutanKosList) {
                    if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                        amaunSebenar = permohonanTuntutanKos.getAmaunSebenar();
                        amaunSebenarList.add(amaunSebenar);
                    }
                }
            }
        }


        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);

        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("showGantirugi", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public void calculateMaklumatHakmilikTotal() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Hakmilik hakmilik1 = new Hakmilik();

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik1 = hakmilikDAO.findById(idHakmilik);
        }

        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        PermohonanPihak permohonanPihak1 = new PermohonanPihak();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList1 = new ArrayList<PermohonanTuntutanKos>();
        List<PermohonanPihak> permohonanPihak1List = new ArrayList<PermohonanPihak>();

        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();

                BigDecimal tuanTanahAmaunTotal = BigDecimal.ZERO;
                tuanTanahAmaunTotal = BigDecimal.ZERO;
                amaunSebenarTuanTotal = BigDecimal.ZERO;

                if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
                    for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : senaraiPihakBerkepentingan) {
//                        permohonanPihak1 = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                        permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                        for (PermohonanPihak pp : permohonanPihak1List) {
                            if (pp != null) {
                                permohonanPihak = pp;
                                permohonanTuntutanKosList1 = pengambilanService.findByIdPermohonanPihak2(pp.getIdPermohonanPihak());

                                jumCaraBayar1 = BigDecimal.ZERO;
                                amaunSebenarTotal = BigDecimal.ZERO;

                                for (PermohonanTuntutanKos permohonanTuntutanKos : permohonanTuntutanKosList1) {
                                    if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                                        jumCaraBayar1 = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar1);
                                    }
                                    if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                                        amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                                    }
                                }
                                //                    tuanTanahAmaun.add(jumCaraBayar1);
                                //                    amaunSebenarTotalList.add(amaunSebenarTotal);
                                tuanTanahAmaunTotal = tuanTanahAmaunTotal.add(jumCaraBayar1);
                                amaunSebenarTuanTotal = amaunSebenarTuanTotal.add(amaunSebenarTotal);
                            }//if
                        }
                    }
                }
                jumCaraBayar1 = tuanTanahAmaunTotal;

                hakmilikAmaunList.add(tuanTanahAmaunTotal);
                hakmilikSebenarList.add(amaunSebenarTuanTotal);


            }//for
        }
    }

    public void calculateMaklumatTuanTanahTotal() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Hakmilik hakmilik1 = new Hakmilik();
        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik1 = hakmilikDAO.findById(idHakmilik);
        }

        PermohonanPihak permohonanPihak1 = new PermohonanPihak();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList1 = new ArrayList<PermohonanTuntutanKos>();
        List<PermohonanPihak> permohonanPihak1List = new ArrayList<PermohonanPihak>();

        senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();
//        BigDecimal tuanTanahAmaunTotal = BigDecimal.ZERO;

        tuanTanahAmaunTotal = BigDecimal.ZERO;
        amaunSebenarTuanTotal = BigDecimal.ZERO;

        if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
            for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : senaraiPihakBerkepentingan) {
//                permohonanPihak1 = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik1.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik1.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                for (PermohonanPihak pp : permohonanPihak1List) {
                    if (pp != null) {
                        permohonanPihak = pp;
                        permohonanTuntutanKosList1 = pengambilanService.findByIdPermohonanPihak2(pp.getIdPermohonanPihak());

                        jumCaraBayar1 = BigDecimal.ZERO;
                        amaunSebenarTotal = BigDecimal.ZERO;

                        for (PermohonanTuntutanKos permohonanTuntutanKos : permohonanTuntutanKosList1) {
                            if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                                jumCaraBayar1 = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar1);
                            }
                            if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                                amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                            }
                        }
                        tuanTanahAmaun.add(jumCaraBayar1);
                        amaunSebenarTotalList.add(amaunSebenarTotal);
                        tuanTanahAmaunTotal = tuanTanahAmaunTotal.add(jumCaraBayar1);
                        amaunSebenarTuanTotal = amaunSebenarTuanTotal.add(amaunSebenarTotal);
                    }//if
                }
            }
        }

        jumCaraBayar1 = tuanTanahAmaunTotal;

    }

    public void calculateMaklumatATuntutanTotal() {
        if (permohonanTuntutanKosList != null && permohonanTuntutanKosList.size() > 0) {

            jumCaraBayar = BigDecimal.ZERO;
            amaunSebenarTotal = BigDecimal.ZERO;

            for (PermohonanTuntutanKos permohonanTuntutanKos : permohonanTuntutanKosList) {
                if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                    jumCaraBayar = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar);
                }
                if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                    amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                }
            }
        }
    }

    public Resolution popup() {
        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        permohonanTuntutanKos1 = new PermohonanTuntutanKos();
        return new JSP("pengambilan/negerisembilan/penarikanbalik/penambahan_kosgantirugiNS.jsp").addParameter("popup", "true");
    }

    public Resolution editTuntutanKos() {
        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos1 = permohonanTuntutanKosDAO.findById(Long.parseLong(idKos));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/penambahan_kosgantirugiNS.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup1() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        permohonanTuntutanKos1.setPermohonan(permohonan);
        permohonanTuntutanKos1.setPihak(permohonanPihak);
        permohonanTuntutanKos1.setCawangan(permohonan.getCawangan());

        InfoAudit ia = permohonanTuntutanKos1.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanTuntutanKos1.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        pengambilanService.simpanTuntutanKos(permohonanTuntutanKos1);

//        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        permohonanPihak1List = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik.getIdHakmilik(), permohonanPihak.getPihak().getIdPihak());
        for (PermohonanPihak pp : permohonanPihak1List) {
            if (pp != null) {
                permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(pp.getIdPermohonanPihak());
            }
        }

        if (permohonanTuntutanKos1 != null) {
            if (permohonanTuntutanKosList.size() > 0) {
                for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
                    try {
                        amaunSebenar = permohonanTuntutanKosList.get(i).getAmaunSebenar();
                        amaunSebenarList.add(amaunSebenar);
                    } catch (Exception e) {
                    }
                }
            }
        }

        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution editPopup() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        idKos = getContext().getRequest().getParameter("idKos");
        PermohonanTuntutanKos permohonanTuntutan = permohonanTuntutanKosDAO.findById(Long.parseLong(idKos));

        permohonanTuntutan.setItem(permohonanTuntutanKos1.getItem());
        permohonanTuntutan.setAmaunTuntutan(permohonanTuntutanKos1.getAmaunTuntutan());
        permohonanTuntutan.setAmaunSebenar(permohonanTuntutanKos1.getAmaunSebenar());
        InfoAudit ia = permohonanTuntutan.getInfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        permohonanTuntutan.setInfoAudit(ia);

        pengambilanService.simpanTuntutanKos(permohonanTuntutan);

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }

        if (permohonanTuntutanKosList.size() > 0) {
            for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
                try {
                    amaunSebenar = permohonanTuntutanKosList.get(i).getAmaunSebenar();
                    amaunSebenarList.add(amaunSebenar);
                } catch (Exception e) {
                }
            }
        }
        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution popupReturn() {
        getContext().getRequest().setAttribute("showMaklumatTuanTanah", Boolean.TRUE);
        return new ForwardResolution("pengambilan/maklumatTuanTanah.jsp").addParameter("popup", "true");
    }

    public Resolution popupTptd() {
        return new JSP("pengambilan/penambahan_kosgantirugiTptd.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit() {
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos = permohonanTuntutanKosDAO.findById(Long.MIN_VALUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/penambahan_kosgantirugiNS.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit1() {
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos = permohonanTuntutanKosDAO.findById(Long.MIN_VALUE);
        return new JSP("pengambilan/penambahan_kosgantirugi_edit.jsp").addParameter("tab", "true");
    }

    @DontValidate
    public Resolution kembali() {
        return new RedirectResolution(perbicaraanKosGantiRugiNSActionBean.class);
    }

//    @DefaultHandler
//    public Resolution hakmilikDetail() {
////        showMaklumatPemilik = true;
//        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumatTuanTanah.jsp").addParameter("popup", "true");
//    }
    public Resolution maklumatTuntutanTuanTanah() {
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        getSenaraiPihak = pengambilanService.findByIdPihakList(idPihak);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumatTuntutanTuanTanah.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPopup() {
        idKos = (String) getContext().getRequest().getSession().getAttribute("idKos");
        permohonanTuntutanKos = permohonanTuntutanKosDAO.findById(Long.MIN_VALUE);
        PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
        ptk.setPermohonan(permohonan);
        ptk.setCawangan(permohonan.getCawangan());
        ptk.setItem(item);
        ptk.setAmaunTuntutan(amaunTuntutan);
        ptk.setAmaunSebenar(amaunSebenar);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = ptk.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ptk.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        pengambilanService.simpanTuntutanKos(ptk);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pengambilan/maklumatTuntutanTuanTanah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPerPihak() {
        idKos = (String) getContext().getRequest().getSession().getAttribute("idKos");
        idPermohonanPihak = (Long) getContext().getRequest().getSession().getAttribute("idPermohonanPihak");

        permohonanTuntutanKos = permohonanTuntutanKosDAO.findById(Long.MIN_VALUE);
        permohonanTuntutanKos = pengambilanService.findTuntutanByIdPermohonanPihak(idPermohonanPihak);

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanPihak == null) {
            permohonanPihak = new PermohonanPihak();
        }

        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanPihak.setInfoAudit(iaPermohonan);
        permohonanPihak.getHakmilik().getIdHakmilik();
        permohonanPihak.getPihak().getIdPihak();
        permohonanPihak.setCawangan(permohonan.getCawangan());
        hakmilikDAO.saveOrUpdate(hakmilik);

        if (getContext().getRequest().getParameter("item") != null) {
            if (item1 == null) {
                item1 = new PermohonanTuntutanKos();
            }
            item1.setPihak(permohonanPihak);
            item = getContext().getRequest().getParameter("item");
            item1.setItem(item);
            item1.setCawangan(permohonan.getCawangan());
            //permohonanKertas.setTajuk("Pentadbir Tanah");
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            item1.setInfoAudit(iaP);
            permohonanTuntutanKosDAO.saveOrUpdate(item1);
        }

        if (getContext().getRequest().getParameter("amaunTuntutan") != null) {
            if (amaunTuntutan1 == null) {
                amaunTuntutan1 = new PermohonanTuntutanKos();
            }
            amaunTuntutan1.setPihak(permohonanPihak);
            String amaunTuntutan = getContext().getRequest().getParameter("amaunTuntutan");
            BigDecimal b = new BigDecimal(amaunTuntutan);
            amaunTuntutan1.setAmaunTuntutan(b);
            amaunTuntutan1.setCawangan(permohonan.getCawangan());
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            item1.setInfoAudit(iaP);
            permohonanTuntutanKosDAO.saveOrUpdate(item1);
        }

        if (getContext().getRequest().getParameter("amaunSebenar") != null) {
            if (amaunTuntutan1 == null) {
                amaunTuntutan1 = new PermohonanTuntutanKos();
            }
            amaunTuntutan1.setPihak(permohonanPihak);
            String amaunSebenar = getContext().getRequest().getParameter("amaunSebenar");
            BigDecimal c = new BigDecimal(amaunSebenar);
            amaunSebenar1.setAmaunSebenar(c);
            amaunSebenar1.setCawangan(permohonan.getCawangan());
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            item1.setInfoAudit(iaP);
            permohonanTuntutanKosDAO.saveOrUpdate(item1);
        }
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(PermohonanPenarikanBalikMMKNActionBean.class, "locate");
    }

    public Resolution simpanTuntutan() {

        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }
        if (permohonanTuntutanKosList.size() > 0) {
            for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
                PermohonanTuntutanKos ptk = permohonanTuntutanKosList.get(i);
                try {
                    if (i < amaunSebenarList.size()) {
                        if (amaunSebenarList.get(i) != null) {
                            ptk.setAmaunSebenar(amaunSebenarList.get(i));
                        }
                    }
                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
                pengambilanService.simpanTuntutanKos(ptk);
            }
        }

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");
        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }

        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }

        calculateMaklumatATuntutanTotal();
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution semakIdHakmilik() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        idHakmilik = getContext().getRequest().getParameter("idHakmilik");//bkk time gune ADMIN

        if (StringUtils.isNotBlank(idHakmilik)) {
            senaraiPihakBerkepentingan = pengambilanService.findbyIdHakmilik(idHakmilik);
        }
        hakmilik = pengambilanService.findByIdHakmilik(idHakmilik);
        if (hakmilik != null) {
            calculateMaklumatTuanTanahTotal();
        }
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (pemohon == null) {
            pemohon = new Pemohon();
        }
        pihak = new Pihak();
        pemohon.setPermohonan(permohonan);
        pemohon.setCawangan(permohonan.getCawangan());

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = pemohon.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            pemohon.setInfoAudit(ia);
            pihak.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        nama = getContext().getRequest().getParameter("nama");
        pihak.setNama(nama);
        pemohon.setPihak(pihak);

        pengambilanService.simpanPihak(pihak);
        pengambilanService.simpanNamaPemohon(pemohon);

//        logger.debug("tess1 :" + aduanSiasatan.getIdSiasatan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanSiasatan.getIdSiasatan());
        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos = pengambilanService.findTuntutanByIdKos(Long.parseLong(idKos));

        if (permohonanTuntutanKos != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanTuntutanKos.setInfoAudit(ia);
            pengambilanService.deleteAll(permohonanTuntutanKos);
        }

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }

        if (permohonanTuntutanKosList.size() > 0) {
            for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
                try {
                    amaunSebenar = permohonanTuntutanKosList.get(i).getAmaunSebenar();
                    amaunSebenarList.add(amaunSebenar);
                } catch (Exception e) {
                }
            }
        }
        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSingle() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idKos = getContext().getRequest().getParameter("idKos");
        item = getContext().getRequest().getParameter("item");
//        String amaunTuntutan = getContext().getRequest().getParameter("amaunTuntutan");
//        String amaunSebenar= getContext().getRequest().getParameter("amaunSebenar");
        logger.info(idKos);
        permohonanTuntutanKos = pengambilanService.findTuntutanByIdKos(Long.parseLong(idKos));
//        aduanLokasi = enforceService.findAduanLokasiByIdAduan(Long.parseLong(idAduanLokasi));

        if (permohonanTuntutanKos == null) {
            permohonanTuntutanKos = new PermohonanTuntutanKos();
        }
        permohonanTuntutanKos.setPermohonan(permohonan);
        permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
        permohonanTuntutanKos.setItem(item);
        permohonanTuntutanKos.setAmaunTuntutan(new BigDecimal(999.99));
        permohonanTuntutanKos.setAmaunSebenar(new BigDecimal(999.99));

        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanTuntutanKos.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        pengambilanService.simpanTuntutanKos(permohonanTuntutanKos);
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        logger.debug("perbicaraanKosGantiRugiNSActionBean::simpan::" + permohonanTuntutanKos.getIdKos());
        return new JSP("pengambilan/negerisembilan/penarikanbalik/keputusan_perbicaraan_kosgantirugi1NS.jsp").addParameter("tab", "true");
//        return new RedirectResolution(perbicaraanKosGantiRugiNSActionBean.class, "locate");
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public void setSenaraiPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan) {
        this.senaraiPihakBerkepentingan = senaraiPihakBerkepentingan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(Long idPihak) {
        this.idPihak = idPihak;
    }

    public Long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(Long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public List<PermohonanTuntutanKos> getSenaraiTuntutanKos() {
        return senaraiTuntutanKos;
    }

    public void setSenaraiTuntutanKos(List<PermohonanTuntutanKos> senaraiTuntutanKos) {
        this.senaraiTuntutanKos = senaraiTuntutanKos;
    }

    public String getIdKos() {
        return idKos;
    }

    public void setIdKos(String idKos) {
        this.idKos = idKos;
    }

    public BigDecimal getAmaunSebenar() {
        return amaunSebenar;
    }

    public void setAmaunSebenar(BigDecimal amaunSebenar) {
        this.amaunSebenar = amaunSebenar;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public BigDecimal getJumCaraBayar1() {
        return jumCaraBayar1;
    }

    public void setJumCaraBayar1(BigDecimal jumCaraBayar1) {
        this.jumCaraBayar1 = jumCaraBayar1;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

//    public BigDecimal getJum() {
//        return jum;
//    }
//
//    public void setJum(BigDecimal jum) {
//        this.jum = jum;
//    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public List<Pihak> getGetSenaraiPihak() {
        return getSenaraiPihak;
    }

    public void setGetSenaraiPihak(List<Pihak> getSenaraiPihak) {
        this.getSenaraiPihak = getSenaraiPihak;
    }

    public boolean isShowMaklumatPemilik() {
        return showMaklumatPemilik;
    }

    public void setShowMaklumatPemilik(boolean showMaklumatPemilik) {
        this.showMaklumatPemilik = showMaklumatPemilik;
    }

    public PermohonanTuntutanKos getAmaunSebenar1() {
        return amaunSebenar1;
    }

    public void setAmaunSebenar1(PermohonanTuntutanKos amaunSebenar1) {
        this.amaunSebenar1 = amaunSebenar1;
    }

    public PermohonanTuntutanKos getAmaunTuntutan1() {
        return amaunTuntutan1;
    }

    public void setAmaunTuntutan1(PermohonanTuntutanKos amaunTuntutan1) {
        this.amaunTuntutan1 = amaunTuntutan1;
    }

    public PermohonanTuntutanKos getItem1() {
        return item1;
    }

    public void setItem1(PermohonanTuntutanKos item1) {
        this.item1 = item1;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanTuntutanKos> getPermohonanTuntutanKosList() {
        return permohonanTuntutanKosList;
    }

    public void setPermohonanTuntutanKosList(List<PermohonanTuntutanKos> permohonanTuntutanKosList) {
        this.permohonanTuntutanKosList = permohonanTuntutanKosList;
    }

    public List<BigDecimal> getTuanTanahAmaun() {
        return tuanTanahAmaun;
    }

    public void setTuanTanahAmaun(List<BigDecimal> tuanTanahAmaun) {
        this.tuanTanahAmaun = tuanTanahAmaun;
    }

    public BigDecimal getTuanTanahAmaunTotal() {
        return tuanTanahAmaunTotal;
    }

    public void setTuanTanahAmaunTotal(BigDecimal tuanTanahAmaunTotal) {
        this.tuanTanahAmaunTotal = tuanTanahAmaunTotal;
    }

    public List<BigDecimal> getHakmilikAmaun() {
        return hakmilikAmaun;
    }

    public void setHakmilikAmaun(List<BigDecimal> hakmilikAmaun) {
        this.hakmilikAmaun = hakmilikAmaun;
    }

    public List<BigDecimal> getAmaunSebenarList() {
        return amaunSebenarList;
    }

    public void setAmaunSebenarList(List<BigDecimal> amaunSebenarList) {
        this.amaunSebenarList = amaunSebenarList;
    }

    public BigDecimal getAmaunSebenarTotal() {
        return amaunSebenarTotal;
    }

    public void setAmaunSebenarTotal(BigDecimal amaunSebenarTotal) {
        this.amaunSebenarTotal = amaunSebenarTotal;
    }

    public List<BigDecimal> getAmaunSebenarTotalList() {
        return amaunSebenarTotalList;
    }

    public void setAmaunSebenarTotalList(List<BigDecimal> amaunSebenarTotalList) {
        this.amaunSebenarTotalList = amaunSebenarTotalList;
    }

    public BigDecimal getAmaunSebenarTuanTotal() {
        return amaunSebenarTuanTotal;
    }

    public void setAmaunSebenarTuanTotal(BigDecimal amaunSebenarTuanTotal) {
        this.amaunSebenarTuanTotal = amaunSebenarTuanTotal;
    }

    public List<BigDecimal> getHakmilikAmaunList() {
        return hakmilikAmaunList;
    }

    public void setHakmilikAmaunList(List<BigDecimal> hakmilikAmaunList) {
        this.hakmilikAmaunList = hakmilikAmaunList;
    }

    public List<BigDecimal> getHakmilikSebenarList() {
        return hakmilikSebenarList;
    }

    public void setHakmilikSebenarList(List<BigDecimal> hakmilikSebenarList) {
        this.hakmilikSebenarList = hakmilikSebenarList;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos1() {
        return permohonanTuntutanKos1;
    }

    public void setPermohonanTuntutanKos1(PermohonanTuntutanKos permohonanTuntutanKos1) {
        this.permohonanTuntutanKos1 = permohonanTuntutanKos1;
    }

    public List<PermohonanPihak> getPermohonanPihak1List() {
        return permohonanPihak1List;
    }

    public void setPermohonanPihak1List(List<PermohonanPihak> permohonanPihak1List) {
        this.permohonanPihak1List = permohonanPihak1List;
    }
}

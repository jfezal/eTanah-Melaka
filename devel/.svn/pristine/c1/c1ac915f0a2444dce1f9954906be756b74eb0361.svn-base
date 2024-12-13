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
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import com.thoughtworks.xstream.converters.Converter;
import etanah.dao.AkaunDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikDAO;
//import etanah.dao.HakmilikPihakBerkepentingan;
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
import etanah.service.PengambilanService1;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.math.MathContext;
import org.hibernate.Transaction;
import java.text.ParseException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.validator.Length;
import etanah.service.common.PermohonanPihakService;
import java.util.LinkedList;

@UrlBinding("/pengambilan/keputusanPampasan")
public class JanaReportPerTuanTanahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(perbicaraanKosGantiRugiActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PengambilanService1 pengambilanService1;
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
    public JanaReportPerTuanTanahActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
    }
    private boolean flag = false;
//    private List<Akaun> akaunList;
    private List<Pemohon> pemohonList;
    private Permohonan permohonan;
    private Dokumen dokumen;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private String idsblm;
    private String idHakmilik;
    private Long idPihak;
    private Long idPermohonanPihak;
    private String borangK;
    private String kodDokumen;
    private Pemohon pemohon;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> permohonanPihakList;
    private List<Pihak> getSenaraiPihak;
    private KodCawangan cawangan;
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
    private ArrayList[] tuanTanahAmaunList = new ArrayList[20];
    private ArrayList[] sebenarAmaunList = new ArrayList[20];
    //ArrayList arrayListDoubles[] = new ArrayList [ 5 ];
    private boolean showTable;
    private boolean showTambahPermintaan;
//    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp";
//    private static final String POPUP_VIEW = "pengambilan/penambahan_kosgantirugi.jsp";
    private String nama;
    private String noLot;
    private BigDecimal luasTerlibat;
//    private Converter converter;
    private String item;
    private PermohonanTuntutanKos item1;
    private PermohonanTuntutanKos amaunTuntutan1;
    private PermohonanTuntutanKos amaunSebenar1;
    private PermohonanTuntutanKos bilKos;
    private HakmilikPermohonan hakmilikPermohonan;
    boolean kerosakan;
    private String idKos;
    //private String[] AmaunDituntut;
    private BigDecimal amaunTuntutan;
    //private String[] AmaunDiLaksanakan;
    private BigDecimal amaunSebenar;
//    private BigDecimal jum;
    private String idPermohonan;
    private Integer index;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private KodJenisPihakBerkepentingan jenis;
    private int bil;
    private boolean showMaklumatPemilik;
    private List<PermohonanTuntutanKos> permohonanTuntutanKosList;

    @DefaultHandler
    public Resolution defaultPage() {
        calculateMaklumatHakmilikTotal();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        pengambilanService.simpanPermohonanSebelum(permohonan,peng);
        return new JSP("pengambilan/perTuanTanahGantiRugiReport.jsp").addParameter("tab", "true");
    }

//    @DefaultHandler
    public Resolution showFormTptd() {
//        showMaklumatPemilik = false;
        //    getContext().getRequest().setAttribute("pt", Boolean.TRUE);
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPtPengambilan() {
        //  getContext().getRequest().setAttribute("detail", Boolean.TRUE);
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        System.out.println("--------popup----------");
        permohonanTuntutanKos1 = new PermohonanTuntutanKos();
        return new JSP("pengambilan/penambahan_kosgantirugi.jsp").addParameter("popup", "true");
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
        return new JSP("pengambilan/penambahan_kosgantirugi.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit1() {
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos = permohonanTuntutanKosDAO.findById(Long.MIN_VALUE);
        return new JSP("pengambilan/penambahan_kosgantirugi_edit.jsp").addParameter("tab", "true");
    }

    @DontValidate
    public Resolution kembali() {
        return new RedirectResolution(perbicaraanKosGantiRugiActionBean.class);
    }

    public Resolution hakmilikDetails() {
        //verifyPengguna();
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);

        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);


        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        //verifyPengguna();
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //String idSblm=(String)getContext().getRequest().getSession().getAttribute("idSblm");


        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();


        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));


        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        if (idSblm != null) //        permohonanPihak = pengambilanService1.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
        {
            permohonanPihakList = pengambilanService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idSblm, hakmilik.getIdHakmilik(), idPihak);
        }
        for (PermohonanPihak pp : permohonanPihakList) {
            if (pp != null) {
                getContext().getRequest().getSession().setAttribute("permohonanPihak", permohonanPihak);
                permohonanTuntutanKosList = pengambilanService1.findByIdPermohonanPihak2(pp.getIdPermohonanPihak());
                getContext().getRequest().setAttribute("showTuntutan", Boolean.TRUE);
            } else {
                addSimpleError("Tiada Data Dalam Pangkalan Data Pada Item Yang Di klik");
            }
        }

        if (permohonanTuntutanKosList != null && permohonanTuntutanKosList.size() > 0) {
            for (int i = 0; i < permohonanTuntutanKosList.size(); i++) {
                try {
                    amaunSebenar = permohonanTuntutanKosList.get(i).getAmaunSebenar();
                    amaunSebenarList.add(amaunSebenar);
                } catch (Exception e) {
                }
            }
        }

        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);

        calculateMaklumatHakmilikTotal();
        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
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
        cawangan = permohonan.getCawangan();
        PermohonanTuntutanKos ptk = new PermohonanTuntutanKos();
        ptk.setPermohonan(permohonan);
        ptk.setCawangan(cawangan);
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
        permohonanTuntutanKos = pengambilanService1.findTuntutanByIdPermohonanPihak(idPermohonanPihak);
        cawangan = permohonan.getCawangan();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanPihak == null) {
            permohonanPihak = new PermohonanPihak();
        }

        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanPihak.setInfoAudit(iaPermohonan);
        permohonanPihak.getHakmilik().getIdHakmilik();
        permohonanPihak.getPihak().getIdPihak();
        permohonanPihak.setCawangan(cawangan);
        hakmilikDAO.saveOrUpdate(hakmilik);

        if (getContext().getRequest().getParameter("item") != null) {
            if (item1 == null) {
                item1 = new PermohonanTuntutanKos();
            }
            item1.setPihak(permohonanPihak);
            item = getContext().getRequest().getParameter("item");
            item1.setItem(item);
            cawangan = permohonan.getCawangan();
            item1.setCawangan(cawangan);
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
            cawangan = permohonan.getCawangan();
            amaunTuntutan1.setCawangan(cawangan);
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
            cawangan = permohonan.getCawangan();
            amaunSebenar1.setCawangan(cawangan);
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

    public Resolution simpanPopup1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);
        System.out.println("-------permohonanPihak--------" + permohonanPihak);
        System.out.println("-------permohonanTuntutanKos1---id-----" + permohonanTuntutanKos1.getIdKos());
        System.out.println("-------permohonanTuntutanKos1---id-----" + permohonanTuntutanKos1.getItem());

        cawangan = permohonanSblm.getCawangan();
        permohonanTuntutanKos1.setPermohonan(permohonanSblm);
        permohonanTuntutanKos1.setPihak(permohonanPihak);
        permohonanTuntutanKos1.setCawangan(cawangan);
//        permohonanTuntutanKos1.setItem(item);
//        permohonanTuntutanKos1.setAmaunTuntutan(amaunTuntutan);
//        permohonanTuntutanKos1.setAmaunSebenar(amaunSebenar);
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


//        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
//            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
//            hakmilik = hakmilikDAO.findById(idHakmilik);
//        }
//        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");
//
//        if (permohonanPihak != null) {
//            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
//        }
//
//        calculateMaklumatTuanTanahTotal();
//        calculateMaklumatATuntutanTotal();
//
//        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//       return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService1.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
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
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
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
            permohonanTuntutanKosList = pengambilanService1.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
        }

        calculateMaklumatTuanTanahTotal();
        calculateMaklumatATuntutanTotal();


        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
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

        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
    }

//     @ValidationMethod(on = "semakIdHakmilik")
//    public void validateHakmilik(ValidationErrors errors) {
//        if (hakmilik == null) {
//            errors.add("a", new SimpleError("Sila Masukkan ID Hakmilik"));
//        }
//    }
//    public List getSenaraiKos(){
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("from PermohonanTuntutanKos h where h.permohonan.idPermohonan= :idPermohonan");
//            q.setString("idPermohonan", idPermohonan);
//            return q.list();
//    }
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

        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
            for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilikPermohonan) {
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();

                BigDecimal tuanTanahAmaunTotal = BigDecimal.ZERO;
                tuanTanahAmaunTotal = BigDecimal.ZERO;
                amaunSebenarTuanTotal = BigDecimal.ZERO;

                if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
                    for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : senaraiPihakBerkepentingan) {
//                        permohonanPihak1 = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                        permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                        for (PermohonanPihak pp : permohonanPihakList) {
                            if (pp != null) {
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
        // String idSblm=(String)getContext().getRequest().getSession().getAttribute("idSblm");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        Hakmilik hakmilik1 = new Hakmilik();
        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik1 = hakmilikDAO.findById(idHakmilik);
        }

        PermohonanPihak permohonanPihak1 = new PermohonanPihak();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList1 = new ArrayList<PermohonanTuntutanKos>();

        senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();
//        BigDecimal tuanTanahAmaunTotal = BigDecimal.ZERO;
        tuanTanahAmaunTotal = BigDecimal.ZERO;
        amaunSebenarTuanTotal = BigDecimal.ZERO;
        if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
            for (int i = 0; i < senaraiPihakBerkepentingan.size(); i++) {
                hakmilikPihakBerkepentingan = (HakmilikPihakBerkepentingan) senaraiPihakBerkepentingan.get(i);

                permohonanPihak1 = pengambilanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm, hakmilik1.getIdHakmilik(), hakmilikPihakBerkepentingan.getPihak().getIdPihak());

                if (permohonanPihak1 != null) {
                    permohonanTuntutanKosList1 = pengambilanService1.findByIdPermohonanPihak2(permohonanPihak1.getIdPermohonanPihak());

                    jumCaraBayar1 = BigDecimal.ZERO;
                    amaunSebenarTotal = BigDecimal.ZERO;

                    for (Iterator<PermohonanTuntutanKos> ptk = permohonanTuntutanKosList1.iterator(); ptk.hasNext();) {
                        permohonanTuntutanKos = ptk.next();
                        if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                            jumCaraBayar1 = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar1);
                        }
                        if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                            amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                        }
                    }
                    tuanTanahAmaun.add(jumCaraBayar1);
                    System.out.println("---------tuanTanahAmaun------" + tuanTanahAmaun);
                    amaunSebenarTotalList.add(amaunSebenarTotal);
                    tuanTanahAmaunTotal = tuanTanahAmaunTotal.add(jumCaraBayar1);
                    amaunSebenarTuanTotal = amaunSebenarTuanTotal.add(amaunSebenarTotal);
                }//if
            }
        }

        jumCaraBayar1 = tuanTanahAmaunTotal;


    }

    public void calculateMaklumatATuntutanTotal() {
        if (permohonanTuntutanKosList != null && permohonanTuntutanKosList.size() > 0) {

            jumCaraBayar = BigDecimal.ZERO;
            amaunSebenarTotal = BigDecimal.ZERO;

            for (Iterator<PermohonanTuntutanKos> ptk = permohonanTuntutanKosList.iterator(); ptk.hasNext();) {
                permohonanTuntutanKos = ptk.next();
                if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                    jumCaraBayar = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar);
                }
                if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                    amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                }

            }
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        // idsblm = getContext().getRequest().getParameter("idsblm");
        // String idSblm=(String)getContext().getRequest().getSession().getAttribute("idSblm");

        System.out.println("----idsblm---------------" + getContext().getRequest().getSession().getAttribute("idSblm"));


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        if (peng.getPerananUtama().getKod().equals("6")) {
//
//            getContext().getRequest().setAttribute("admin", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tptg1", Boolean.FALSE);
//            System.out.println("Admin");
//        }
        //tptg1
//        if (peng.getPerananUtama().getKod().equals("54")) {
//
//            getContext().getRequest().setAttribute("tptg1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("admin", Boolean.FALSE);
//            System.out.println("tptg1");
//        }
//        if (idPermohonan != null) {
//            Permohonan p = permohonanDAO.findById(idPermohonan);
//            senaraiTuntutanKos = pengambilanService.findByIDMohonKos(p.getIdPermohonan());
//            senaraiHakmilikPermohonan = p.getSenaraiHakmilik();
//
//        }

        if (idSblm != null) {
            Permohonan p = permohonanDAO.findById(idSblm);

            System.out.println("---------Permohonan------------" + p);


            if (p != null) {
                //modified code

                senaraiHakmilikPermohonan = p.getSenaraiHakmilik();


                System.out.println("---------senaraiHakmilikPermohonan------------" + senaraiHakmilikPermohonan);
                System.out.println("---------senaraiHakmilikPermohonan------------" + senaraiHakmilikPermohonan.size());

                senaraiTuntutanKos = pengambilanService.findByIDMohonKos(p.getIdPermohonan());



                //            Permohonan p = permohonanDAO.findById(idPermohonan);
//            senaraiTuntutanKos = pengambilanService.findByIDMohonKos(p.getIdPermohonan());
//            senaraiHakmilikPermohonan = p.getSenaraiHakmilik();

                // Permohonan p = permohonanDAO.findById(idSblm.getIdPermohonan());




//            pihak = p.getSenaraiHakmilik().iterator().next().getHakmilik().getSenaraiPihakBerkepentingan().iterator().next().getPihak();
//            getSenaraiPihak = pengambilanService.findByIdPihakList(pihak.getIdPihak());

//             if (permohonanPihak != null) {
//                item1 = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
//                amaunTuntutan1 = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
//            }

                if (StringUtils.isNotBlank(idsblm)) {
                    permohonanRujukanLuar = pengambilanService1.findByIdPermohonan(idsblm);
                    permohonan = permohonanDAO.findById(idsblm);
                    // JOptionPane.showMessageDialog(null, permohonanRujukanLuar.getNoFail());

                }

                if (StringUtils.isNotBlank(idHakmilik)) {
                    hakmilik = pengambilanService1.findByIdHakmilik(idHakmilik);
                    hakmilik = hakmilikDAO.findById(idHakmilik);
                    // JOptionPane.showMessageDialog(null, permohonanRujukanLuar.getNoFail());
                }

//            item = getContext().getRequest().getParameter("item");
//            if (idPermohonan != null) {
//                permohonan = permohonanDAO.findById(idPermohonan);
//                permohonanTuntutanKos = new PermohonanTuntutanKos();
//                permohonanTuntutanKos.setPermohonan(permohonan);
//                String[] key = {"permohonan"};
//                Object[] value = {permohonan};
//                List<PermohonanTuntutanKos> list = permohonanTuntutanKosDAO.findByEqualCriterias(key, value, null);
//                if(list == null && list.size() == 0)
//                {
//                    permohonanTuntutanKos = new PermohonanTuntutanKos();
//                }else
//                if (list != null && list.size() == 1) {
//                    permohonanTuntutanKos = list.get(0);
////                lokasi = aduanLokasi.getLokasi();
//                    item = permohonanTuntutanKos.getItem();
//                } else if (idKos != null && list != null) {
//                    for (PermohonanTuntutanKos ptk : list) {
//                        if (String.valueOf(ptk.getIdKos()).equals(idKos)) {
//                            permohonanTuntutanKos = ptk;
//                            break;
//                        }
//                    }
//                } else {
//                    permohonanTuntutanKos = null;
//                }
//            }

                if (permohonan == null) {
                    addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                    return;
                }
                senaraiTuntutanKos = pengambilanService1.findByIDMohonKos(idPermohonan);

                // Need add code here

                calculateMaklumatHakmilikTotal();


            }

        }

    }

    public Resolution semakIdHakmilik() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        idHakmilik = getContext().getRequest().getParameter("idHakmilik");//bkk time gune ADMIN

        if (StringUtils.isNotBlank(idHakmilik)) {
            senaraiPihakBerkepentingan = pengambilanService.findbyIdHakmilik(idHakmilik);
        }
        hakmilik = pengambilanService1.findByIdHakmilik(idHakmilik);
        if (hakmilik != null) {
            calculateMaklumatTuanTanahTotal();
        }
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        if (pemohon == null) {
            pemohon = new Pemohon();
        }
        pihak = new Pihak();
        pemohon.setPermohonan(permohonan);
        pemohon.setCawangan(kodCawanganDAO.findById(cawangan.getKod()));

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

        pengambilanService1.simpanPihak(pihak);
        pengambilanService1.simpanNamaPemohon(pemohon);

//        logger.debug("tess1 :" + aduanSiasatan.getIdSiasatan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanSiasatan.getIdSiasatan());
        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idKos = getContext().getRequest().getParameter("idKos");
        permohonanTuntutanKos = pengambilanService1.findTuntutanByIdKos(Long.parseLong(idKos));

        if (permohonanTuntutanKos != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanTuntutanKos.setInfoAudit(ia);
            pengambilanService1.deleteAll(permohonanTuntutanKos);
        }

        if (getContext().getRequest().getSession().getAttribute("idHakmilik") != null) {
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = (PermohonanPihak) getContext().getRequest().getSession().getAttribute("permohonanPihak");

        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService1.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());
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
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
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
        permohonanTuntutanKos = pengambilanService1.findTuntutanByIdKos(Long.parseLong(idKos));
//        aduanLokasi = enforceService.findAduanLokasiByIdAduan(Long.parseLong(idAduanLokasi));
        cawangan = permohonan.getCawangan();

        if (permohonanTuntutanKos == null) {
            permohonanTuntutanKos = new PermohonanTuntutanKos();
        }
        permohonanTuntutanKos.setPermohonan(permohonan);
        permohonanTuntutanKos.setCawangan(cawangan);
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
        logger.debug("perbicaraanKosGantiRugiActionBean::simpan::" + permohonanTuntutanKos.getIdKos());
        return new JSP("pengambilan/keputusan_perbicaraan_kosgantirugi1.jsp").addParameter("tab", "true");
//        return new RedirectResolution(perbicaraanKosGantiRugiActionBean.class, "locate");
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
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

    public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
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

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getIdsblm() {
        return idsblm;
    }

    public void setIdsblm(String idsblm) {
        this.idsblm = idsblm;
    }

    public String getBorangK() {
        return borangK;
    }

    public void setBorangK(String borangK) {
        this.borangK = borangK;
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

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
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

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
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

    public PermohonanTuntutanKos getBilKos() {
        return bilKos;
    }

    public void setBilKos(PermohonanTuntutanKos bilKos) {
        this.bilKos = bilKos;
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

    public ArrayList[] getSebenarAmaunList() {
        return sebenarAmaunList;
    }

    public void setSebenarAmaunList(ArrayList[] sebenarAmaunList) {
        this.sebenarAmaunList = sebenarAmaunList;
    }

    public ArrayList[] getTuanTanahAmaunList() {
        return tuanTanahAmaunList;
    }

    public void setTuanTanahAmaunList(ArrayList[] tuanTanahAmaunList) {
        this.tuanTanahAmaunList = tuanTanahAmaunList;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }
}


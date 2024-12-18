/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.report.ReportUtil;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/utiliti_lelong")
public class UtilitiTabsTarikhdanJurulelong extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiTabsTarikhdanJurulelong.class);
    @Inject
    LelongService lelongService;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    FolderDokumenDAO folderDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    CalenderManager manager;
    @Inject
    EnkuiriService enService;
    @Inject
    private etanah.Configuration conf;
    private String idPermohonan;
    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    public String selectedTab = "0";
    private FasaPermohonan fasaPermohonan;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
    private String tarikhDokumen;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> senaraiLelongan4;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private Enkuiri enkuiri;
    private Dokumen doc;
    private String tarikhEnkuiri;
    private String tarikhLelong;
    private String tarikhAkhirBayaran;
    private Lelongan lelong;
    private Lelongan lelong1;
    private String idHak;
    private String jam;
    private String minit;
    private String ampm;
    private String tempat;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private BigDecimal hargaRizab;
    private BigDecimal deposit;
    private boolean button = false;
    private boolean error = false;
    private boolean flag = false;
    private String reportName;
    private long idDokumen;
//    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

//    public Resolution showForm() {
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiCarianPermohonan.jsp");
//    }
    public Resolution showForm3() {
        LOG.info("---showForm---");

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx.getKodCawangan().getKod());

        return new ForwardResolution("/WEB-INF/jsp/lelong/calender_lelong7.jsp").addParameter("popup", "true");
    }

    public Resolution find() {

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());

                List<FasaPermohonan> listFasa = lelongService.getFasaSemak16H(idPermohonan);
                if (!listFasa.isEmpty()) {
                    LOG.debug("error");
                    addSimpleError("Utiliti Ini Tidak Dapat Diteruskan. ID Permohonan :" + idPermohonan + " Telah Melepasi Fasa Yang Sepatutnya");
                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
                } else {
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        negori = true;
                        enkuiri = lelongService.findEnkuiri(idPermohonan);
                        amaunTunggakan = enkuiri.getAmaunTunggakan();
                    }

                    listLel = lelongService.getLeloganASC(idPermohonan);
                    listLelongan.add(lelong);
                    enkuiri = lelongService.findEnkuiri(idPermohonan);

                    hargaRizab = enkuiri.getHargaRizab();
                    deposit = enkuiri.getDeposit();

                    if (enkuiri == null) {
                        addSimpleError("Tarikh Enkuiri Dan Tarikh Lelongan Belum Ditetapkan.Urusan Tidak Boleh Diteruskan");
                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
                    }

                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
                    if (senaraiLelongan.isEmpty()) {
                        addSimpleError("Tarikh Lelongan Belum Ditetapkan.Urusan Tidak Boleh Diteruskan");
                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//                        /return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
                    }
                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                            lelong = ll;
                            break;
                        }
                    }
                    if (lelong == null) {
                        addSimpleError("Tarikh Lelongan Belum Ditetapkan.Urusan Tidak Boleh Diteruskan");
                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
                    }
                    if (permohonan.getSenaraiHakmilik().size() == 1) {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                        getContext().getRequest().setAttribute("same", Boolean.TRUE);
                        if (enkuiri.getCaraLelong() == null) {
                            enkuiri.setCaraLelong("B");
                            lelongService.saveOrUpdate(enkuiri);
                        }
                        //bersama
                        listLelongan = new ArrayList<Lelongan>();
                        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
                        for (Lelongan ll : senaraiLelongan) {
                            if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                                lelong = ll;
                                break;
                            }
                        }
                        listLelongan.add(lelong);
                        StringBuilder sb = new StringBuilder();
                        if (permohonan != null) {
                            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                                Hakmilik h = hp.getHakmilik();
                                if (sb.length() > 0) {
                                    sb.append("<br>");
                                }
                                sb.append(h.getIdHakmilik());
                            }
                        }
                        idHak = sb.toString();
                    }

                    if (permohonan.getSenaraiHakmilik().size() > 1) {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                        if (enkuiri.getCaraLelong() == null) {
                            getContext().getRequest().setAttribute("sblmPilih", Boolean.TRUE);
                        } else {
                            getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                            //berasingan
                            if (enkuiri.getCaraLelong().equals("A")) {
                                getContext().getRequest().setAttribute("same", Boolean.FALSE);
                            }
                            //bersama
                            listLelongan = new ArrayList<Lelongan>();
                            if (enkuiri.getCaraLelong().equals("B")) {
                                getContext().getRequest().setAttribute("same", Boolean.TRUE);
                                listLelongan.add(lelong);
                                StringBuilder sb = new StringBuilder();
                                if (permohonan != null) {
                                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                                        Hakmilik h = hp.getHakmilik();
                                        if (sb.length() > 0) {
                                            sb.append("<br>");
                                        }
                                        sb.append(h.getIdHakmilik());
                                    }
                                }
                                idHak = sb.toString();
                            }
                        }
                    }
                }

            } else {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
            }
            senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
            listLel = lelongService.getLeloganASC(idPermohonan);
            reportName = getContext().getRequest().getParameter("namaReport");
        }
        try {

            if (lelong != null && lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
                tempat = lelong.getTempat().toString();
                hargaRizab = lelong.getHargaRizab();
                deposit = lelong.getDeposit();
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }


        flag = true;
        LOG.info("Rehydrate - finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
    }

    //berasingan utk n.melaka~
    public Resolution simpanLelong() throws ParseException {
        LOG.info("----berasingan------");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
        }

        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        enkuiri = lelongService.findEnkuiri(idPermohonan);
        FasaPermohonan fasa = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());


        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (fasa != null) {
            fasa.setInfoAudit(ia);
            fasa.setKeputusan(null);
        } else {
            fasa = new FasaPermohonan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            fasa.setPermohonan(permohonan);
            fasa.setInfoAudit(ia);
            fasa.setCawangan(pengguna.getKodCawangan());
            fasa.setIdPengguna(pengguna.getIdPengguna());
            fasa.setIdAliran("semak16HLantikJurulelong");
        }

        lelongService.saveUpdate2(fasa);

        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
        listLel = lelongService.getLeloganASC(idPermohonan);
        LOG.info("listLel : " + listLel.size());

        LOG.info("senaraiLelongan1 sama.size : " + senaraiLelongan.size());
        listLel = lelongService.getLeloganASC(idPermohonan);
        senaraiLelongan4 = lelongService.getLeloganALLDESC2(idPermohonan);

        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());


        for (Lelongan lelong2 : senaraiLelongan4) {
            LOG.info("" + lelong2.getKodStatusLelongan().getKod());
            if (lelong2.getKodStatusLelongan().getKod().equals("AK")) {
                lelong = lelong2;
                lelong2.setKodStatusLelongan(kodStatusLelonganDAO.findById("TT"));
                enService.simpan(lelong2);
            }
        }

        for (int i = 0; i < listLel.size(); i++) {
            Lelongan lelo = new Lelongan();
            LOG.info("loop :" + i);
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());

            lelo.setHakmilikPermohonan(lel.getHakmilikPermohonan());
            lelo.setEnkuiri(lel.getEnkuiri());
            lelo.setBil(lel.getBil());

            tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.info("tarikhLelong :" + tarikhLelong);
            try {
                lelo.setTarikhLelong(sdf.parse(tarikhLelong));
            } catch (Exception ex) {
                LOG.error("tarikhLelong-780 : " + ex);
            }
            try {
                lelo.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error("tarikhAkhirBayaran-785 : " + ex);
            }

            lelo.setTempat(getContext().getRequest().getParameter("tempat"));

            String harga = getContext().getRequest().getParameter("hargaRizab" + i);
            LOG.info("harga :" + harga);
            BigDecimal hargaRizab = new BigDecimal((harga.trim()).replaceAll(",", ""));
            LOG.info("hargaRizab :" + hargaRizab);
            lelo.setHargaRizab(hargaRizab);

            String depo = getContext().getRequest().getParameter("deposit" + i);
            LOG.info("depo :" + depo);
            BigDecimal deposit = new BigDecimal((depo.trim()).replaceAll(",", ""));
            LOG.info("deposit :" + deposit);
            lelo.setDeposit(deposit);

            lelo.setPermohonan(permohonan);
            lelo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            lelo.setInfoAudit(ia);
            enService.simpan(lelo);
            lelong = lelo;
        }

        enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));

        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            lelongService.saveOrUpdate(enkuiri);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            listLel = lelongService.getLeloganASC(idPermohonan);
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
            flag = true;
            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
        }
//        }
        lelongService.saveOrUpdate(enkuiri);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        listLel = lelongService.getLeloganASC(idPermohonan);
        getContext().getRequest().setAttribute("same", Boolean.FALSE);
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
    }
//__________________________________________________________________________________________________________________________________________
//bersama utk n.melaka

    public Resolution saveBersama() throws ParseException {
        LOG.info("----bersama------");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
        }
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        enkuiri = lelongService.findEnkuiri(idPermohonan);
        FasaPermohonan fasa = lelongService.findFasaPermohonanSemak16H(permohonan.getIdPermohonan());


        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if (fasa != null) {
            fasa.setInfoAudit(ia);
            fasa.setKeputusan(null);
        } else {
            fasa = new FasaPermohonan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            fasa.setPermohonan(permohonan);
            fasa.setCawangan(pengguna.getKodCawangan());
            fasa.setInfoAudit(ia);
            fasa.setIdPengguna(pengguna.getIdPengguna());
            fasa.setIdAliran("semak16HLantikJurulelong");
        }

        lelongService.saveUpdate2(fasa);

        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
        LOG.info("senaraiLelongan1 sama.size : " + senaraiLelongan.size());
        listLel = lelongService.getLeloganASC(idPermohonan);
        senaraiLelongan4 = lelongService.getLeloganALLDESC2(idPermohonan);
        lelong = lelongService.findLelong(idPermohonan);

        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        for (Lelongan lelong2 : senaraiLelongan4) {
            LOG.info("" + lelong2.getKodStatusLelongan().getKod());
            if (lelong2.getKodStatusLelongan().getKod().equals("AK")) {
                lelong = lelong2;
                lelong2.setKodStatusLelongan(kodStatusLelonganDAO.findById("TT"));
                enService.simpan(lelong2);
            }
        }

        enkuiri.setHargaRizab(hargaRizab);
        enkuiri.setDeposit(deposit);
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        lelongService.saveOrUpdate(enkuiri);

        Lelongan lelo = new Lelongan();

        if (lelong.getHakmilikPermohonan() != null) {
            lelo.setHakmilikPermohonan(lelong.getHakmilikPermohonan());
        }
        if (lelong.getEnkuiri() != null) {
            lelo.setEnkuiri(lelong.getEnkuiri());
        }

        if (String.valueOf(lelong.getBil()).length() != 0) {
            lelo.setBil(lelong.getBil());
        }

        tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
        LOG.info("tarikhLelong :" + tarikhLelong);
        try {
            lelo.setTarikhLelong(sdf.parse(tarikhLelong));
        } catch (Exception ex) {
            LOG.error("tarikhLelong-780 : " + ex);
        }
        try {
            lelo.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
        } catch (Exception ex) {
            LOG.error("tarikhAkhirBayaran-785 : " + ex);
        }

        try {
            lelo.setTempat(getContext().getRequest().getParameter("tempat"));
            lelo.setHargaRizab(enkuiri.getHargaRizab());
            lelo.setDeposit(enkuiri.getDeposit());
            lelo.setPermohonan(permohonan);
            lelo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
            lelo.setInfoAudit(ia);
            enService.simpan(lelo);
        } catch (Exception ex) {
            LOG.error("lelo : " + ex);
        }

        enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));

        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);
            lelongService.saveOrUpdate(enkuiri);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            listLelongan.add(lelong);
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
            flag = true;
            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
        }
        lelongService.saveOrUpdate(enkuiri);
        enService.simpan(lelo);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        listLelongan.add(lelong);
        getContext().getRequest().setAttribute("same", Boolean.TRUE);
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTabs.jsp");
    }
    
    

    public Resolution cetak() throws FileNotFoundException, ParseException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        FolderDokumen fd = permohonan.getFolderDokumen();
        LOG.info("genReport :: start");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        String gen = "";
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            //melaka
            gen = "LLGBorang16HPenyerah_MLK.rdf";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //negeri9
            gen = "LLGBorang16HPenyerah_NS.rdf";
        }
        String code = "16H";

        try {
            LOG.info("genReportFromXML");
            Dokumen doc = lelongService.reportGen2(permohonan, gen, code, pengguna);
            idDokumen = doc.getIdDokumen();

            getContext().getRequest().setAttribute("idDokumen", idDokumen);

            LOG.info("id Dokumenssss :" + idDokumen);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("application/pdf", msg);
    }

    public Resolution tarikhLelongEdit() {
        return new JSP("lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

    public Resolution jurulelongView() {
        return new JSP("lelong/perlantikanJurulelong.jsp");
    }

    public Resolution dokumenTab() {
        return new JSP("lelong/dokumen_utiliti_tabs.jsp");
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumenSebelum() {
        return folderDokumenSebelum;
    }

    public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
        this.folderDokumenSebelum = folderDokumenSebelum;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public Map<String, String> getKodMapSebelum() {
        return kodMapSebelum;
    }

    public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
        this.kodMapSebelum = kodMapSebelum;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public List<KodDokumen> getListKodDokumen() {
        return listKodDokumen;
    }

    public void setListKodDokumen(List<KodDokumen> listKodDokumen) {
        this.listKodDokumen = listKodDokumen;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<Dokumen> getSenaraiKandunganSebelum() {
        return senaraiKandunganSebelum;
    }

    public void setSenaraiKandunganSebelum(List<Dokumen> senaraiKandunganSebelum) {
        this.senaraiKandunganSebelum = senaraiKandunganSebelum;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getTarikhDokumen() {
        return tarikhDokumen;
    }

    public void setTarikhDokumen(String tarikhDokumen) {
        this.tarikhDokumen = tarikhDokumen;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public List<Lelongan> getSenaraiLelongan4() {
        return senaraiLelongan4;
    }

    public void setSenaraiLelongan4(List<Lelongan> senaraiLelongan4) {
        this.senaraiLelongan4 = senaraiLelongan4;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
//    public Pengguna getPengguna() {
//        return pengguna;
//    }
//
//    public void setPengguna(Pengguna pengguna) {
//        this.pengguna = pengguna;
//    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }
}

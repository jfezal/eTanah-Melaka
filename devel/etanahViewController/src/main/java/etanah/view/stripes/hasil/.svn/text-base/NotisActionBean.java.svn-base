/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.DasarTuntutanCukai;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdNotis;
import etanah.sequence.GeneratorIdPermohonan;
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
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/notis")
public class NotisActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NotisActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Hakmilik hakmilik;
    private KodUrusan ku;
    private DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanNotis> senaraiNotis;
    private List<DasarTuntutanNotis> list2;
    private KodStatusTerima statusTerima;
    private static List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanCukaiHakmilik> senaraiDTCHdmp;
    private List<KodNotis> senaraiKodNotis = new ArrayList<KodNotis>();
    private boolean visible = false;
    private boolean flag = false;
    private boolean btn = false;
    private String noDasar;
    private Integer tempohTahun;
    private String kodStatus;
    private String kodNegeri;
    private String idHakmilik;
    private String penghantaran;
    private String hantar;
    private String bilBulan;
    private String bilHari;
    private String suratPeringatan = "none";
    private String suratPeringatanReadOnly;
    private String notis6A = "none";
    private String notis8A = "none";
    private String notisGantian6A = "none";
    private String notisGantian8A = "none";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String pNoDasar;
    private String pKodCaw;
    private String pIdHakmilik;
    private String darimana;
    private List<DasarTuntutanCukai> senaraiDTCpopup;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    NotisPeringatan6AManager manager;
    @Inject
    NotisPeringatan6AManager notisPeringatan6AManager;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodCaraPenghantaranDAO caraKodPenghantaranDAO;
    @Inject
    GeneratorIdNotis generatorIdNotis;
    @Inject
    GeneratorIdPermohonan generatorIdPermohonan;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    GenerateIdPermohonanWorkflow service;
    @Inject
    DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    RekodPenghantaran2ActionBean rekod;
    @Inject
    etanah.kodHasilConfig khconf;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            setKodNegeri("melaka");

        }
//        senaraiKodNotis = new ArrayList<KodNotis>();
        senaraiKodNotis = manager.doSenaraiKodNotis();
        
        LOG.debug("senaraiKodNotis.size :"+senaraiKodNotis.size());
        senaraiDasarTuntutanCukaiHakmilik = new ArrayList<DasarTuntutanCukaiHakmilik>();
        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");

    }

    public Resolution search() throws ParseException {
        setVisible(true);
        LOG.debug("noDasar :" + noDasar);
        LOG.debug("kodStatus :" + kodStatus);
        Date now = new java.util.Date();
        senaraiDasarTuntutanCukaiHakmilik = new ArrayList<DasarTuntutanCukaiHakmilik>();
        if (!("".equals(noDasar))) {
            senaraiDTCHdmp = searchDasar();
            if (senaraiDTCHdmp.isEmpty()) {
                setVisible(false);
                suratPeringatanReadOnly = "true";
                addSimpleError("No. Rujukan Dasar tidak tepat.");
            }
            try {
                LOG.debug("senaraiDTCHdmp.size() : " + senaraiDTCHdmp.size());
                if ("N6A".equals(kodStatus)) { // notis 6A
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp 6A : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis 6A : " + senaraiNotis.size());
                            senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                        }
                        //to check whether this dasar already got notis6A or not
                        for (DasarTuntutanNotis dasarNotis : senaraiNotis) {
                            if(dasarNotis.getNotis().getKod().equals(kodStatus)){
                                setFlag(true);
                                addSimpleError("Dasar "+noDasar+" sudah dikenakan Notis 6A.");
                            }
                        }
                    }                    
                    LOG.debug("noDasar22 :" + noDasar);
                    //                 setBtn(false);
                    notis6A = null;
                } else if ("N8A".equals(kodStatus)) { // notis 8A
                    senaraiNotis = new ArrayList<DasarTuntutanNotis>();
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp 8A : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis 8A : " + senaraiNotis.size());
                        }
                        //checking if hakmilik has 8A before
                        boolean not8A = true;
                        for (DasarTuntutanNotis dcn8A : dtch.getSenaraiNotis()) {
                            if(dcn8A.getNotis().getKod().equals(kodStatus)){
                                not8A = false;
                                break;
                            }
                        }
                        // finish checking if hakmilik has 8A before
                        if(not8A){
                            for (DasarTuntutanNotis dtn : senaraiNotis) {
    //                            Date now = new java.util.Date();//sdf.parse("31/05/2010");//new java.util.Date();
                                if (dtn.getTarikhTerima() != null) {
                                    long todate = now.getTime();
                                    long fromdate = dtn.getTarikhTerima().getTime();
                                    long date = (todate - fromdate) / (1000 * 60 * 60 * 24); //convert to date
                                    LOG.debug("long date :" + date);
                                    if (dtn.getNotis().getKod().equals("N6A") && date > 90) {
                                        senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                                    }
                                } else {
                                    LOG.debug("dtn.getTarikhTerima() null");
                                }
                                //to check whether this dasar already got notis6A or not
    //                            if(dtn.getNotis().getKod().equals(kodStatus)){
    //                                setFlag(true);
    //                                addSimpleError("Dasar "+noDasar+" sudah dikenakan Notis 8A.");
    //                            }
                            }
                        }
                    }
                    LOG.debug("noDasar22 :" + noDasar);
                    notis8A = null;
                    //                setBtn(false);
                } else if ("NG".equals(kodStatus)) { // notis Gantian
                    senaraiNotis = new ArrayList<DasarTuntutanNotis>();
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp NG : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis NG : " + senaraiNotis.size());
                        }
                        //checking if hakmilik has NG before
                        boolean notNG = true;
                        for (DasarTuntutanNotis dcnNG : dtch.getSenaraiNotis()) {
                            if(dcnNG.getNotis().getKod().equals(kodStatus)){
                                notNG = false;
                                break;
                            }
                        }
                        // finish checking if hakmilik has NG before
                        if(notNG){
                            for (DasarTuntutanNotis dtn : senaraiNotis) {
                                if(dtn.getStatusTerima() == null)
                                    continue;
                                if (dtn.getNotis().getKod().equals("N6A") && dtn.getStatusTerima().getKod().equals("XT")) {
                                    LOG.debug("kod " + dtn.getNotis().getKod());
                                    LOG.debug("status" + dtn.getStatusTerima().getKod());
                                    //                          if(  dtn.getStatusTerima().getKod().equals("XT")){

                                    senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                                } //                    }
                                else {
                                    LOG.debug("dtn.getTarikhTerima() null");
                                }
                                //to check whether this dasar already got notis6A or not
    //                            if(dtn.getNotis().getKod().equals(kodStatus)){
    //                                setFlag(true);
    //                                addSimpleError("Notis gantian untuk dasar "+noDasar+" sudah dikeluarkan.");
    //                            }
                            }
                        }
                    }
                    LOG.debug("noDasar22 :" + noDasar);
                    notisGantian6A = null;                    
                }
                else { //surat peringatan
                    senaraiDasarTuntutanCukaiHakmilik = senaraiDTCHdmp;
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                        if (!dtch.getSenaraiNotis().isEmpty()) {
                            for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                                bilBulan = "" + dtn.getTempohBulan();
                                bilHari = "" + dtn.getTempohHari();
                                suratPeringatanReadOnly = "true";
                            }
                        }
                    }
                    suratPeringatan = null;

                }
            } catch (NullPointerException nex) {
                LOG.error("Null Pointer Exception :" + nex);
                nex.printStackTrace();
            }
        }
        // just for checking only
        LOG.info("(search)senaraiDasarTuntutanCukaiHakmilik.size :"+senaraiDasarTuntutanCukaiHakmilik.size());
        for (DasarTuntutanCukaiHakmilik dtchOnly : senaraiDasarTuntutanCukaiHakmilik) {
            LOG.info("(search) dtchOnly.hakmilik :"+ dtchOnly.getHakmilik().getIdHakmilik());
        }
        // finish just for checking only
        if (senaraiDasarTuntutanCukaiHakmilik.size() == 0) {
            flag = true;
        }else{
            senaraiDasarTuntutanCukaiHakmilik = removeDuplicateDasarHakmilik(senaraiDasarTuntutanCukaiHakmilik);
        }
        senaraiKodNotis = manager.doSenaraiKodNotis();
        setBtn(true);
        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");
    }

    public List getSenaraiKodAgensi() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodAgensi u where u.kod in (:kod1,:kod2)");
        q.setString("kod1", "0153");
        q.setString("kod2", "2402");
        return q.list();
    }

    public List searchDasar() {
        String hql = "from DasarTuntutanCukaiHakmilik dt ";
        hql += "where dt.dasarTuntutanCukai.idDasar = :noDasar ";
        Session s = sessionProvider.get();
        Query q = s.createQuery(hql);
        q.setParameter("noDasar", noDasar);
        return q.list();
    }

    public Resolution popupDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/hasil/hakmilik_details.jsp").addParameter("popup", "true");
    }

    public Resolution cari() {
        Date now = new java.util.Date();
        senaraiDasarTuntutanCukaiHakmilik = new ArrayList<DasarTuntutanCukaiHakmilik>();
        if (!("".equals(noDasar))) {
            senaraiDTCHdmp = searchDasar();
            if (senaraiDTCHdmp.isEmpty()) {
                setVisible(false);
                suratPeringatanReadOnly = "true";
                addSimpleError("No. Rujukan Dasar tidak tepat.");
            }
            try {
                LOG.debug("senaraiDTCHdmp.size() : " + senaraiDTCHdmp.size());
                if ("N6A".equals(kodStatus)) { // notis 6A
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp 6A : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis 6A : " + senaraiNotis.size());

                            senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                        }
                    }
                    LOG.debug("noDasar22 :" + noDasar);
                    //                 setBtn(false);
                    notis6A = null;
                } else if ("N8A".equals(kodStatus)) { // notis 8A
                    senaraiNotis = new ArrayList<DasarTuntutanNotis>();
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp 8A : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis 8A : " + senaraiNotis.size());
                        }
                        for (DasarTuntutanNotis dtn : senaraiNotis) {
//                            Date now = new java.util.Date();//sdf.parse("31/05/2010");//new java.util.Date();
                            if (dtn.getTarikhTerima() != null) {
                                long todate = now.getTime();
                                long fromdate = dtn.getTarikhTerima().getTime();
                                long date = (todate - fromdate) / (1000 * 60 * 60 * 24); //convert to date
                                LOG.debug("long date :" + date);
                                if (dtn.getNotis().getKod().equals("N6A") && date > 90) {
                                    senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                                }
                            } else {
                                LOG.debug("dtn.getTarikhTerima() null");
                            }
                        }
                    }
                    LOG.debug("noDasar22 :" + noDasar);
                    notis8A = null;
                    //                setBtn(false);
                } else if ("NG".equals(kodStatus)) { // notis Gantian
                    senaraiNotis = new ArrayList<DasarTuntutanNotis>();
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCHdmp) {
                        LOG.debug("senaraiDTCHdmp NG : " + senaraiDTCHdmp.size());
                        if (dtch.getHakmilik().getAkaunCukai().getBaki().intValue() > 0) {
                            senaraiNotis = dtch.getSenaraiNotis();
                            LOG.debug("senaraiNotis NG : " + senaraiNotis.size());
                        }
                        for (DasarTuntutanNotis dtn : senaraiNotis) {
                            //
                            if (dtn.getNotis().getKod().equals("N6A") && dtn.getStatusTerima().getKod().equals("XT")) {
                                LOG.debug("kod " + dtn.getNotis().getKod());
                                LOG.debug("status" + dtn.getStatusTerima().getKod());
                                //                          if(  dtn.getStatusTerima().getKod().equals("XT")){

                                senaraiDasarTuntutanCukaiHakmilik.add(dtch);
                            } //                    }
                            else {
                                LOG.debug("dtn.getTarikhTerima() null");
                            }
                        }
                    }
                    LOG.debug("noDasar22 :" + noDasar);
                    notisGantian6A = null;
                    //                setBtn(false);
                } //                else if("N6A".equals(kodStatus)){
                //                       senaraiDasarTuntutanCukaiHakmilik = senaraiDTCHdmp;
                //
                //                    notis6A =null;
                //                }
                else { //surat peringatan
                    senaraiDasarTuntutanCukaiHakmilik = senaraiDTCHdmp;
                    for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                        if (!dtch.getSenaraiNotis().isEmpty()) {
                            for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                                bilBulan = "" + dtn.getTempohBulan();
                                bilHari = "" + dtn.getTempohHari();
                                suratPeringatanReadOnly = "true";
                            }
                        }
                    }
                    suratPeringatan = null;
                    notis6A = null;
                    //                setBtn(false);
                }
            } catch (NullPointerException nex) {
                LOG.error("Null Pointer Exception :" + nex);
                nex.printStackTrace();
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");

    }

    public Resolution savePeringatan() throws FileNotFoundException, ParseException {

        search();
        String msg = "";
//        cari();
        LOG.debug("--");

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list2 = new ArrayList<DasarTuntutanNotis>();
//        Long idNotis = 01l;
        KodNotis kodNotis = new KodNotis();
        kodNotis.setKod(kodStatus);
        KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();
        kodCaraPenghantaran.setKod(penghantaran);
        List<DasarTuntutanNotis> dtnList = new ArrayList<DasarTuntutanNotis>();
        try{
            for (DasarTuntutanCukaiHakmilik dtc : senaraiDasarTuntutanCukaiHakmilik) {
                for (HakmilikPihakBerkepentingan hpb : dtc.getHakmilik().getSenaraiPihakBerkepentingan()) {
                    if(hpb.getAktif() == 'Y' && getSelectedPB(hpb)){
                        if (doCheckHakmilikNotis(dtc.getIdDasarHakmilik())) {
                            DasarTuntutanNotis notis = new DasarTuntutanNotis();
                            Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(ctx.getKodNegeri(), caw, notis));
                            notis.setIdNotis(seqIdNotis);
                            notis.setCawangan(caw);
                            if (bilBulan != null) {
                                notis.setTempohBulan(Integer.parseInt(bilBulan));
                            }
                            if (bilHari != null) {
                                notis.setTempohHari(Integer.parseInt(bilHari));
                            }
                            notis.setInfoAudit(info);
                            notis.setHakmilik(dasarTuntutanCukaiHakmilikDAO.findById(dtc.getIdDasarHakmilik()));
                            notis.setNotis(kodNotis);
                            notis.setPihak(hpb.getPihak()); //set notis based on how many pihak under hakmilik
                            //            notis.setCaraPenghantaran(kodCaraPenghantaran);
                            notis.setTarikhNotis(now);
                            dtnList.add(notis);

                            manager.saveNotis(notis);
                        }
                    }
                }
            }
            setBtn(true);
            setFlag(true);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
            msg = "berjaya";
        }catch(Exception ex){
            LOG.error("Exception Peringatan :"+ex);
            ex.printStackTrace(); // for development only
            addSimpleError("Maklumat Gagal Disimpan");
            msg = "gagal";
        }
        
//        String namaReport = "";
//        if("04".equals(conf.getProperty("kodNegeri"))){ // for negeri Melaka
//            namaReport = "hasilSuratPeringatanML.rdf";
//        }else if("05".equals(conf.getProperty("kodNegeri"))){ // for negeri Sembilan
//            namaReport = "hasilSuratPeringatan.rdf";
//        }
//        String dasar = noDasar.replaceAll(" ", "%20");
//        Date now1 = new Date();
//        File f = null;
//        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
//        String documentPath = conf.getProperty("document.path");
////        String path = tarikh + File.separator + String.valueOf(noDasar);
//        String path = tarikh + File.separator + "SP-"+noDasar.substring(0, 2)+tarikh.substring(0, 8);
//        LOG.debug("dasar :"+dasar);
//        LOG.info("path :"+path);
////        reportUtil.generateReport("hasilSuratPeringatanML.rdf",
//        reportUtil.generateReport(namaReport,
//                //                new String[]{"p_id_kew_dok"},
//                new String[]{"p_id_dasar"},
//                new String[]{dasar},
//                documentPath + path, null);
//        f = new File(documentPath + path);
//        FileInputStream fis = new FileInputStream(f);
////        return new StreamingResolution("application/pdf", fis);
        senaraiKodNotis = manager.doSenaraiKodNotis();
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution save6A() throws ParseException {
//        cari();
        search();
        LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() : " + senaraiDasarTuntutanCukaiHakmilik.size());

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list2 = new ArrayList<DasarTuntutanNotis>();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        KodNotis kodNotis = new KodNotis();//
        kodNotis.setKod(kodStatus);//
        KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();//
        kodCaraPenghantaran.setKod(penghantaran);//
        LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() : "+senaraiDasarTuntutanCukaiHakmilik.size());
        for (DasarTuntutanCukaiHakmilik dtc : senaraiDasarTuntutanCukaiHakmilik) {
            for (HakmilikPihakBerkepentingan hpb : dtc.getHakmilik().getSenaraiPihakBerkepentingan()) {
                if(hpb.getAktif() == 'Y' && getSelectedPB(hpb)){
                    DasarTuntutanNotis notis = new DasarTuntutanNotis();
                    Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(ctx.getKodNegeri(), caw, notis));
                    notis.setIdNotis(seqIdNotis);
                    notis.setCawangan(caw);
                    notis.setInfoAudit(info);
                    LOG.debug("dtc.getIdDasarHakmilik() : "+dtc.getIdDasarHakmilik());
                    notis.setHakmilik(dasarTuntutanCukaiHakmilikDAO.findById(dtc.getIdDasarHakmilik()));
                    notis.setPihak(hpb.getPihak()); //set notis based on how many pihak under hakmilik
                    notis.setNotis(kodNotis);
                    notis.setTarikhNotis(now);
                    manager.saveNotis(notis);
                }                
            }
            senaraiHakmilik.add(dtc.getHakmilik());

        }
        ku = kodUrusanDAO.findById("NT6A");

        LOG.info("(sblm)senaraiHakmilik :"+senaraiHakmilik.size());
        // to remove duplicate for hakmilik
        senaraiHakmilik = removeDuplicateHakmilik(senaraiHakmilik);
        LOG.info("(slps)senaraiHakmilik :"+senaraiHakmilik.size());

        if (service.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "6A")) {
            /*** re comment again since discuss with haji's SME and kak fida 28/10/2011 ***/
            /*** based on CC Musalmah 25/11/2014 ***/
            if ("05".equals(conf.getProperty("kodNegeri"))) { // for negeri sembilan
                /** comment temp since have problem**/
                Transaksi t = new Transaksi();
                KodTransaksi kt = new KodTransaksi();
                kt.setKod(khconf.getProperty("notis6A")); // kod Notis 6A Tanah
                KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');
                t.setCawangan(caw);
                t.setKodTransaksi(kt);
                t.setAmaun(new BigDecimal(10));//caj dikenakan utk keluarkan Notis 6A utk N9
                t.setStatus(status);
                t.setKuantiti(1);
                t.setInfoAudit(info);
                t.setUntukTahun(new java.util.Date().getYear() + 1900);//getYear() starting 1900
                t.setTahunKewangan(new java.util.Date().getYear() + 1900);//getYear() starting 1900
                String resultTransaksi = manager.simpan6A(senaraiHakmilik, p);
                if ("berjaya".equals(resultTransaksi)) {
                    LOG.info("Transaksi Notis 6A telah berjaya ditambah disetiap Hakmilik.");
                    setFlag(true);
                }
            }else{
                setFlag(true);
            }
                addSimpleMessage("Permohonan Telah Berjaya..");
            } else {
                addSimpleError("Permohonan Tidak Berjaya..");
            }
//        }
        senaraiKodNotis = manager.doSenaraiKodNotis();
        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");
    }

    public Resolution save8A() throws ParseException {
//        cari();
        search();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list2 = new ArrayList<DasarTuntutanNotis>();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        KodNotis kodNotis = new KodNotis();//
        kodNotis.setKod(kodStatus);//
        KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();//
        kodCaraPenghantaran.setKod(penghantaran);//
        for (DasarTuntutanCukaiHakmilik dtc : senaraiDasarTuntutanCukaiHakmilik) {
            for (HakmilikPihakBerkepentingan hpb : dtc.getHakmilik().getSenaraiPihakBerkepentingan()) {
                if(hpb.getAktif() == 'Y' && getSelectedPB(hpb)){
                    DasarTuntutanNotis notis = new DasarTuntutanNotis();
                    Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(ctx.getKodNegeri(), caw, notis));
                    notis.setIdNotis(seqIdNotis);
                    notis.setCawangan(caw);
                    notis.setInfoAudit(info);
                    notis.setHakmilik(dasarTuntutanCukaiHakmilikDAO.findById(dtc.getIdDasarHakmilik()));
                    notis.setPihak(hpb.getPihak()); //set notis based on how many pihak under hakmilik
                    notis.setNotis(kodNotis);
                    notis.setTarikhNotis(now);
                    manager.saveNotis(notis);
                    senaraiHakmilik.add(dtc.getHakmilik());
                }
            }
        }
        ku = kodUrusanDAO.findById("NT8A");

        // to remove duplicate for hakmilik
        senaraiHakmilik = removeDuplicateHakmilik(senaraiHakmilik);

        if (service.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "8A")) {
            setFlag(true);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan.");
        }
        senaraiKodNotis = manager.doSenaraiKodNotis();

        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");

    }

    public Resolution notisGantian8A() throws ParseException {
//        cari();
        search();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list2 = new ArrayList<DasarTuntutanNotis>();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        KodNotis kodNotis = new KodNotis();//
        kodNotis.setKod(kodStatus);//
        KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();//
        kodCaraPenghantaran.setKod(penghantaran);//
        LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() : " + senaraiDasarTuntutanCukaiHakmilik.size());
        for (DasarTuntutanCukaiHakmilik dtc : senaraiDasarTuntutanCukaiHakmilik) {
            for (HakmilikPihakBerkepentingan hpb : dtc.getHakmilik().getSenaraiPihakBerkepentingan()) {
                DasarTuntutanNotis notis = new DasarTuntutanNotis();
                Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(ctx.getKodNegeri(), caw, notis));
                notis.setIdNotis(seqIdNotis);
                notis.setCawangan(caw);
                notis.setInfoAudit(info);
                notis.setHakmilik(dasarTuntutanCukaiHakmilikDAO.findById(dtc.getIdDasarHakmilik()));
                notis.setPihak(hpb.getPihak()); //set notis based on how many pihak under hakmilik
                notis.setNotis(kodNotis);
                notis.setTarikhNotis(now);
                manager.saveNotis(notis);
            }
            senaraiHakmilik.add(dtc.getHakmilik());
        }

        // to remove duplicate for hakmilik
        senaraiHakmilik = removeDuplicateHakmilik(senaraiHakmilik);

        List<Hakmilik> ptg = new ArrayList<Hakmilik>();
        List<Hakmilik> ptd = new ArrayList<Hakmilik>();
        LOG.debug("senaraiHakmilik.size() : " + senaraiHakmilik.size());
        for (Hakmilik hm : senaraiHakmilik) {
            if ((hm.getKodHakmilik().getKod().equals("GRN")) || (hm.getKodHakmilik().getKod().equals("PN"))
                    || (hm.getKodHakmilik().getKod().equals("HSD"))) {
                LOG.debug("**Hakmilik PTG**");
                LOG.debug("idHakmilik : " + hm.getIdHakmilik());
                LOG.debug("KodHakmilik : " + hm.getKodHakmilik().getKod());
                ptg.add(hm);
            } else {
                LOG.debug("**Hakmilik PTD**");
                LOG.debug("idHakmilik : " + hm.getIdHakmilik());
                LOG.debug("KodHakmilik : " + hm.getKodHakmilik().getKod());
                ptd.add(hm);
            }
        }
        LOG.debug("PTD.size() : " + ptd.size());
        LOG.debug("PTG.size() : " + ptg.size());
        boolean success = true;
        boolean success1 = true;
        if (ptd.size() > 0) {
            ku = kodUrusanDAO.findById("NG8AD");

            if (service.generateIdPermohonanWorkflowInternal(ku, p, ptd, "")) {
//                addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
                success = true;
            } else //                addSimpleError("Maklumat tidak berjaya disimpan.");
            {
                success = false;
            }
        }
        if (ptg.size() > 0) {
            ku = kodUrusanDAO.findById("NG8AP");

            if (service.generateIdPermohonanWorkflowInternal(ku, p, ptg, "")) {
//                addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
                success1 = true;
            } else //                addSimpleError("Maklumat tidak berjaya disimpan.");
            {
                success1 = false;
            }
        }
        if (success1 == success) {
            setFlag(true);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan.");
        }
        senaraiKodNotis = manager.doSenaraiKodNotis();

        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");

    }

    public Resolution notisGantian() throws ParseException {
//        cari();
        search();

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser(); //penggunaDAO.findById("admin");

        Date now = new Date();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodCawangan caw = p.getKodCawangan();
        list2 = new ArrayList<DasarTuntutanNotis>();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        KodNotis kodNotis = new KodNotis();//
        kodNotis.setKod(kodStatus);//
        KodCaraPenghantaran kodCaraPenghantaran = new KodCaraPenghantaran();//
        kodCaraPenghantaran.setKod(penghantaran);//
        LOG.debug("senaraiDasarTuntutanCukaiHakmilik.size() : " + senaraiDasarTuntutanCukaiHakmilik.size());
        for (DasarTuntutanCukaiHakmilik dtc : senaraiDasarTuntutanCukaiHakmilik) {
//            LOG.debug("dtc.getSenaraiNotis.size() : "+dtc.getSenaraiNotis().size());
            for (DasarTuntutanNotis dcn : dtc.getSenaraiNotis()) {
                LOG.debug("dcn.getNotis().getKod() : " + dcn.getNotis().getKod());
                if (dcn.getStatusTerima() != null) {
                    LOG.debug("dcn.getStatusTerima().getKod() : " + dcn.getStatusTerima().getKod());
                }
                if (dcn.getNotis().getKod().equals("N6A") && dcn.getStatusTerima().getKod().equals("XT")) {
                    DasarTuntutanNotis notis = new DasarTuntutanNotis();
                    Long seqIdNotis = Long.parseLong(generatorIdNotis.generate(ctx.getKodNegeri(), caw, notis));
                    notis.setIdNotis(seqIdNotis);
                    notis.setCawangan(caw);
                    notis.setInfoAudit(info);
                    notis.setHakmilik(dasarTuntutanCukaiHakmilikDAO.findById(dtc.getIdDasarHakmilik()));
                    notis.setPihak(dcn.getPihak());
                    notis.setNotis(kodNotis);
                    notis.setCatatanTerima(dcn.getCatatanTerima());
                    notis.setTarikhNotis(now);
                    manager.saveNotis(notis);

                    senaraiHakmilik.add(dtc.getHakmilik());
                }
            }
        }

        // to remove duplicate for hakmilik
        senaraiHakmilik = removeDuplicateHakmilik(senaraiHakmilik);

        List<Hakmilik> ptg = new ArrayList<Hakmilik>();
        List<Hakmilik> ptd = new ArrayList<Hakmilik>();
        LOG.debug("senaraiHakmilik.size() : " + senaraiHakmilik.size());
        for (Hakmilik hm : senaraiHakmilik) {
            if ((hm.getKodHakmilik().getKod().equals("GRN")) || (hm.getKodHakmilik().getKod().equals("PN"))
                    || (hm.getKodHakmilik().getKod().equals("HSD"))) {
                LOG.debug("**Hakmilik PTG**");
                LOG.debug("idHakmilik : " + hm.getIdHakmilik());
                LOG.debug("KodHakmilik : " + hm.getKodHakmilik().getKod());
                ptg.add(hm);
            } else {
                LOG.debug("**Hakmilik PTD**");
                LOG.debug("idHakmilik : " + hm.getIdHakmilik());
                LOG.debug("KodHakmilik : " + hm.getKodHakmilik().getKod());
                ptd.add(hm);
            }
        }
        LOG.debug("PTD.size() : " + ptd.size());
        LOG.debug("PTG.size() : " + ptg.size());
        boolean success = true;
        boolean success1 = true;
        if (ptd.size() > 0) {
            ku = kodUrusanDAO.findById("NGPTD");

            if (service.generateIdPermohonanWorkflowInternal(ku, p, ptd, "")) {
//                addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
                success = true;
            } else //                addSimpleError("Maklumat tidak berjaya disimpan.");
            {
                success = false;
            }
        }
        if (ptg.size() > 0) {
            ku = kodUrusanDAO.findById("NGPTG");

            if (service.generateIdPermohonanWorkflowInternal(ku, p, ptg, "")) {
//                addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
                success1 = true;
            } else //                addSimpleError("Maklumat tidak berjaya disimpan.");
            {
                success1 = false;
            }
        }
        if (success1 == success) {
            setFlag(true);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan dan Permohonan berjaya..");
        } else {
            addSimpleError("Maklumat tidak berjaya disimpan.");
        }
        senaraiKodNotis = manager.doSenaraiKodNotis();

        return new ForwardResolution("/WEB-INF/jsp/hasil/notis_1.jsp");

    }

    public Resolution cetak() throws FileNotFoundException {
//        LOG.debug("hakmilik :" + hakmilik.getIdHakmilik());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
//        String idKew = getContext().getRequest().getParameter("idKew");
//        String idHakmilik = hakmilik.getIdHakmilik();
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(noDasar);
        reportUtil.generateReport("hasilSuratPeringatan.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_dasar"},
                new String[]{noDasar},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        setFlag(true);
        return new StreamingResolution("application/pdf", fis);


    }
    
    public Resolution cetak2() throws FileNotFoundException {
//        LOG.debug("hakmilik :" + hakmilik.getIdHakmilik());
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
//        String idKew = getContext().getRequest().getParameter("idKew");
//        String idHakmilik = hakmilik.getIdHakmilik();
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(noDasar);
        reportUtil.generateReport("hasilSuratPeringatanBil2.rdf",
                //                new String[]{"p_id_kew_dok"},
                new String[]{"p_id_dasar"},
                new String[]{noDasar},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        setFlag(true);
        return new StreamingResolution("application/pdf", fis);


    }

    public Resolution sedia6A() {
        senaraiDasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilikDAO.findAll();

        return new ForwardResolution("/WEB-INF/jsp/hasil/penyediaan_notis6A.jsp");
    }

    private Boolean doCheckHakmilikNotis(Long idCukaiHakmilik) {
        boolean result = true;
        for (DasarTuntutanNotis dtn : dasarTuntutanNotisDAO.findAll()) {
            if (idCukaiHakmilik.equals(dtn.getHakmilik().getIdDasarHakmilik()) && "SP".equals(dtn.getNotis().getKod())) {
                result = false;
            }
        }
        return result;
    }

    public Resolution popupCarianDasar(){
//        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kodCawP = peng.getKodCawangan().getKod();
//        darimana = (String) getContext().getRequest().getSession().getAttribute("asalan");
        LOG.debug("(popupCarianDasar) darimana :"+darimana);
        return new ForwardResolution("/WEB-INF/jsp/hasil/popup_carian_dasar.jsp").addParameter("popup", "true");
    }
    
    public Resolution carianDasar(){
        LOG.debug("(CarianDasar) darimana :"+darimana);
        senaraiDTCpopup = new ArrayList<DasarTuntutanCukai>();
        Session s = sessionProvider.get();
        String hql = "FROM DasarTuntutanCukai dtc WHERE 1=1 ";
        if(pNoDasar != null)
            hql += "AND dtc.idDasar LIKE :noDasar ";
        if(pKodCaw != null)
            hql += "AND dtc.cawangan.kod = :caw ";

        hql+= "ORDER BY dtc.tarikhDasar desc";

        LOG.debug("hql :"+hql);
        Query q = s.createQuery(hql);
        if(pNoDasar != null)
            q.setParameter("noDasar", "%"+pNoDasar+"%");
        if(pKodCaw != null)
            q.setParameter("caw", pKodCaw);
        
        if(pIdHakmilik == null){
            LOG.info("pIdHakmilik is null.");
            senaraiDTCpopup = q.list();           
            LOG.debug("senaraiDTCpopup.size :"+senaraiDTCpopup.size());
        }else{
            LOG.info("pIdHakmilik :"+pIdHakmilik);
            List<DasarTuntutanCukai> senaraiDasarAll = new ArrayList<DasarTuntutanCukai>();
            senaraiDasarAll = q.list();
            for (DasarTuntutanCukai dtc : senaraiDasarAll) {
                for (DasarTuntutanCukaiHakmilik dtcHakmilik : dtc.getSenaraiHakmilik()) {
                    if(dtcHakmilik.getHakmilik().getIdHakmilik().equals(pIdHakmilik)){
                        senaraiDTCpopup.add(dtc);
                        LOG.debug("match :"+dtcHakmilik.getHakmilik().getIdHakmilik());
                        break;
                    }
                }
            }
        }        
        return new ForwardResolution("/WEB-INF/jsp/hasil/popup_carian_dasar.jsp").addParameter("popup", "true");
    }

    public Resolution refreshBase(){
        LOG.debug("noDasar :"+noDasar);
        return showForm();
    }

    private List<DasarTuntutanCukaiHakmilik> removeDuplicateDasarHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDTCH){
        Map<Hakmilik,DasarTuntutanCukaiHakmilik> map = new HashMap<Hakmilik,DasarTuntutanCukaiHakmilik>();
            for(int i=0;i<senaraiDTCH.size();i++){
                Hakmilik hakmilik = senaraiDTCH.get(i).getHakmilik();
                if(map.containsKey(hakmilik))
                    continue;
                else
                    map.put(hakmilik, senaraiDTCH.get(i));
            }
            senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>(map.values());
        return senaraiDTCH;
    }

    private boolean getSelectedPB(HakmilikPihakBerkepentingan hpb){
        boolean flagPB = false; // if luar daripada permintaan
        /**
         * PM = Pemilik
         * PA = Pemegang Amanah
         * PG = Pemegang Gadaian
         * PJ = Pemegang Pajakan
         * PJK = Pemegang Pajakan Kecil
         * PY = Penyewa
         * PKA = Pemasuk Kaveat Amanah
         * PKD = Pemasuk Kaveat Pendaftar
         * PKL = Pemasuk Kaveat Pemegang lien
         * PKS = Pemasuk Kaveat Persendirian
         * PI = Pemasuk Ismen
         * PP = Pentadbir
         * PK = Pemegang Kuasa(probate)/Executor
         * RP = Representative
         * WPA = Wasi dan Pemegang Amanah
         * KL = K.A (L.A)
         * WKL = Wakil
         * WS = Wasi
         * JA = Penjaga Amanah
         * PAS = Pemegang Amanah Seumur Hidup
         * ROS = With A Right Of Survivorship
         * JK = Jawatankuasa
         * PB = Pembubar
        **/
        if(hpb.getJenis().getKod().equals("PM") || hpb.getJenis().getKod().equals("PA") || hpb.getJenis().getKod().equals("PG")
            || hpb.getJenis().getKod().equals("PJ") || hpb.getJenis().getKod().equals("PJK") || hpb.getJenis().getKod().equals("PY")
            || hpb.getJenis().getKod().equals("PKA") || hpb.getJenis().getKod().equals("PKD") || hpb.getJenis().getKod().equals("PKL")
            || hpb.getJenis().getKod().equals("PKS") || hpb.getJenis().getKod().equals("PI") || hpb.getJenis().getKod().equals("PP")
            || hpb.getJenis().getKod().equals("PK") || hpb.getJenis().getKod().equals("RP") || hpb.getJenis().getKod().equals("WPA")
            || hpb.getJenis().getKod().equals("KL") || hpb.getJenis().getKod().equals("WKL") || hpb.getJenis().getKod().equals("WS")
            || hpb.getJenis().getKod().equals("JA") || hpb.getJenis().getKod().equals("PAS") || hpb.getJenis().getKod().equals("ROS")
            || hpb.getJenis().getKod().equals("JK") || hpb.getJenis().getKod().equals("PB")){
            flagPB = true;
        }
        LOG.debug("getSelectedPB : flagPB ="+flagPB);
        return flagPB;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getNoDasar() {
        return noDasar;
    }

    public void setNoDasar(String noDasar) {
        this.noDasar = noDasar;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    /**
     * @return the senaraiNotis
     */
    public List<DasarTuntutanNotis> getSenaraiNotis() {
        return senaraiNotis;
    }

    /**
     * @param senaraiNotis the senaraiNotis to set
     */
    public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    /**
     * @return the dasarTuntutanCukaiHakmilik
     */
    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilik() {
        return dasarTuntutanCukaiHakmilik;
    }

    /**
     * @param dasarTuntutanCukaiHakmilik the dasarTuntutanCukaiHakmilik to set
     */
    public void setDasarTuntutanCukaiHakmilik(DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik) {
        this.dasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilik;
    }

    /**
     * @return the list2
     */
    public List<DasarTuntutanNotis> getList2() {
        return list2;
    }

    /**
     * @param list2 the list2 to set
     */
    public void setList2(List<DasarTuntutanNotis> list2) {
        this.list2 = list2;
    }

    public String getHantar() {
        return hantar;
    }

    public void setHantar(String hantar) {
        this.hantar = hantar;
    }

    public String getPenghantaran() {
        return penghantaran;
    }

    public void setPenghantaran(String penghantaran) {
        this.penghantaran = penghantaran;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDTCHdmp() {
        return senaraiDTCHdmp;
    }

    public void setSenaraiDTCHdmp(List<DasarTuntutanCukaiHakmilik> senaraiDTCHdmp) {
        this.senaraiDTCHdmp = senaraiDTCHdmp;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    /**
     * @return the suratPeringatan
     */
    public String getSuratPeringatan() {
        return suratPeringatan;
    }

    /**
     * @param suratPeringatan the suratPeringatan to set
     */
    public void setSuratPeringatan(String suratPeringatan) {
        this.suratPeringatan = suratPeringatan;
    }

    /**
     * @return the notis6A
     */
    public String getNotis6A() {
        return notis6A;
    }

    /**
     * @param notis6A the notis6A to set
     */
    public void setNotis6A(String notis6A) {
        this.notis6A = notis6A;
    }

    /**
     * @return the notis8A
     */
    public String getNotis8A() {
        return notis8A;
    }

    /**
     * @param notis8A the notis8A to set
     */
    public void setNotis8A(String notis8A) {
        this.notis8A = notis8A;
    }

    /**
     * @return the notisGantian6A
     */
    public String getNotisGantian6A() {
        return notisGantian6A;
    }

    /**
     * @param notisGantian6A the notisGantian6A to set
     */
    public void setNotisGantian6A(String notisGantian6A) {
        this.notisGantian6A = notisGantian6A;
    }

    /**
     * @return the notisGantian8A
     */
    public String getNotisGantian8A() {
        return notisGantian8A;
    }

    /**
     * @param notisGantian8A the notisGantian8A to set
     */
    public void setNotisGantian8A(String notisGantian8A) {
        this.notisGantian8A = notisGantian8A;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @return the btn
     */
    public boolean isBtn() {
        return btn;
    }

    /**
     * @param btn the btn to set
     */
    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public String getBilBulan() {
        return bilBulan;
    }

    public void setBilBulan(String bilBulan) {
        this.bilBulan = bilBulan;
    }

    public String getBilHari() {
        return bilHari;
    }

    public void setBilHari(String bilHari) {
        this.bilHari = bilHari;
    }

    public String getSuratPeringatanReadOnly() {
        return suratPeringatanReadOnly;
    }

    public void setSuratPeringatanReadOnly(String suratPeringatanReadOnly) {
        this.suratPeringatanReadOnly = suratPeringatanReadOnly;
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

    public List<KodNotis> getSenaraiKodNotis() {
        return senaraiKodNotis;
    }

    public void setSenaraiKodNotis(List<KodNotis> senaraiKodNotis) {
        this.senaraiKodNotis = senaraiKodNotis;
    }

    public String getpIdHakmilik() {
        return pIdHakmilik;
    }

    public void setpIdHakmilik(String pIdHakmilik) {
        this.pIdHakmilik = pIdHakmilik;
    }

    public String getpKodCaw() {
        return pKodCaw;
    }

    public void setpKodCaw(String pKodCaw) {
        this.pKodCaw = pKodCaw;
    }

    public String getpNoDasar() {
        return pNoDasar;
    }

    public void setpNoDasar(String pNoDasar) {
        this.pNoDasar = pNoDasar;
    }

    public List<DasarTuntutanCukai> getSenaraiDTCpopup() {
        return senaraiDTCpopup;
    }

    public void setSenaraiDTCpopup(List<DasarTuntutanCukai> senaraiDTCpopup) {
        this.senaraiDTCpopup = senaraiDTCpopup;
    }

    public String getDarimana() {
        return darimana;
    }

    public void setDarimana(String darimana) {
        this.darimana = darimana;
    }

//    private List<Hakmilik> removeDuplicateHakmilik(List<Hakmilik> senaraiHakmilik){
//        //Create a HashSet which allows no duplicates
//          HashSet hashSet = new HashSet(senaraiHakmilik);
//
//          //Assign the HashSet to a new ArrayList
//          ArrayList arrayList2 = new ArrayList(hashSet) ;
//
//          //Ensure correct order, since HashSet doesn't
//          Collections.sort(arrayList2);
//          return arrayList2;
//    }

    private List<Hakmilik> removeDuplicateHakmilik(List<Hakmilik> senaraiHakmilik){
        Map<String,Hakmilik> map = new HashMap<String,Hakmilik>();
            for(int i=0;i<senaraiHakmilik.size();i++){
                String hm = senaraiHakmilik.get(i).getIdHakmilik();
                if(map.containsKey(hm))
                    continue;
                else
                    map.put(hm, senaraiHakmilik.get(i));
            }
            senaraiHakmilik = new ArrayList<Hakmilik>(map.values());
        return senaraiHakmilik;
    }
}


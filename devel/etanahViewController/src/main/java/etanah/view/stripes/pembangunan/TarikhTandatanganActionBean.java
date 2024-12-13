/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.InfoAudit;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.PembangunanService;
import java.text.DateFormat;
import java.text.ParseException;
import net.sourceforge.stripes.action.ForwardResolution;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/tarikhTandantangan")
public class TarikhTandatanganActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(TarikhTandatanganActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    EnforceService enforceService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    private Pengguna pguna;
    private List<Pengguna> listPp;
    private List<Pengguna> listPp2;
    private List<Pengguna> listPp3;
    private List<Pengguna> listPp4;
    private List<Pengguna> listPtdN9;
    private List<Pengguna> listPtgN9;
    private String peringkat;
    private Permohonan mohon;
    private boolean bp = false;
    private BPelService service;
    private String taskId;
    Task task = null;
    private String stageId;
    private PermohonanTandatanganDokumen tt;
    private String kodDoc;
    private String allKodDoc;
    private String kodDoc2;
    private String tandatangan;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikh;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //peringkat ptd
    @DefaultHandler
    public Resolution showForm() {
        LOG.info("show PTD form");
        peringkat = "ptd";
        return new JSP("pembangunan/common/tarikh_tandatangan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("show PTG form");
        peringkat = "ptg";
        return new JSP("pembangunan/common/tarikh_tandatangan.jsp").addParameter("tab", "true");
    }

    // Hide Tairkhtt
    //peringkat ptd
    public Resolution showForm3() {
        LOG.info("show PTD form");
        peringkat = "ptd";
        return new JSP("pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    // Hide Tairkhtt
    public Resolution showForm4() {
        LOG.info("show PTG form");
        peringkat = "ptg";
        return new JSP("pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        LOG.info("show imbas pelan prahitung tandatangan");
        peringkat = "ptd2";
        return new JSP("pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        LOG.info("show Urusan PPK form");
        peringkat = "ptg2";
        return new JSP("pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    //hide Tandatangan
    public Resolution showForm7() {
        LOG.info("Tarikh form Only");
        return new JSP("pembangunan/common/tarikh_only.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormN9forPTD() {
        LOG.info("--showFormN9forPTD--");
        peringkat = "ptd";
        return new JSP("pembangunan/ns/tandatangan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormN9forPTG() {
        LOG.info("--showFormN9forPTG--");
        peringkat = "ptg";
        return new JSP("pembangunan/ns/tandatangan.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        service = new BPelService();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            mohon = permohonanDAO.findById(idPermohonan);
        }

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        //testing purpose
//        stageId ="cetaksrtdokimbaspelansediayab";
        LOG.info("=====StageId======:" + stageId);
//        if (stageId.equals("cetaksrtjkbbrekodtkhtt")) {
//            kodDoc = "SJKBB";
//        }
//        if (stageId.equals("cetaksuratkpsnjkbbrekodtkhtt")) {
//            kodDoc = "SJKBB";
//        }
//        if (stageId.equals("cetaknilaianpremiumcetaklulus")) {
//            kodDoc = "SLSB";
//        }
//        if (stageId.equals("rekodtkhperakuannotis7g")) {
//            kodDoc = "NTS7G";
//        }
//        if (stageId.equals("sediacetaksrtkpsn")) {
//            kodDoc = "SKPN";
//        }
//        if (stageId.equals("rekodtkhtt")) {
//            kodDoc = "SJKBB";
//        }
//        if (stageId.equals("rekodtkhperakuan")) {
//            kodDoc = "NTS7G";
//        }
//        if (stageId.equals("rekodkpsnyabkadarjpph")) {
//            kodDoc = "SKLP";
//        }
//        if (stageId.equals("cetaksrtkpsnrekodtkhtt")) {
//            kodDoc = "SPSBT";
//        }
//        if (stageId.equals("rekodkpsnyabkdrjpphtkhkpsn")) {
//            kodDoc = "SLA";
//        }
//        if (stageId.equals("rekodtkhtthantarptg")) {
//            kodDoc = "SPBU";
//        }
//        if (stageId.equals("rekodtkhtthantarptd")) {
//            kodDoc = "SPBU";
//        }
//        if (stageId.equals("syorminitbebas")) {
//            kodDoc = "MINB";
//        }
//        if (stageId.equals("ctksrtkecualiupahukur")) {
//            kodDoc = "SIPBU";
//        }
//        if (stageId.equals("cetakrekodtrkhsuratmakluman")) {
//            kodDoc = "SMBTL";
//        }
//        if (stageId.equals("rekodkpsnmmkncetaksurat")) {
//            kodDoc = "SRPPG";
//        }
//        if (stageId.equals("cetaksuratkelulusanb5a")) {
//            if (mohon.getKodUrusan().getKod().equals("RPP")) {
//                kodDoc = "SRPPD";
//            } else if (mohon.getKodUrusan().getKod().equals("RNS")) {
//                kodDoc = "SRNSD";
//            }
//        }
//        if (stageId.equals("rekodkpsncetaksurat")) {
//            kodDoc = "SRNSG";
//        }
//        if (stageId.equals("cetaksuratmohonrayuan")) {
//            kodDoc = "SPRNS";
//        }
//        if (stageId.equals("cetaksuratjpphpremium")) {
//            kodDoc = "JPPHN";
//        }
//        if (stageId.equals("cetaksuratkelulusan")) {
//            kodDoc = "SLLS";
//        }
//        if (stageId.equals("cetaksurattolak")) {
//            kodDoc = "STLK";
//        }
//        if (stageId.equals("cetaksrttolak")) {
//            kodDoc = "SPSBT";
//        }
//        if (stageId.equals("laporantanah")) {
//            kodDoc = "LT";
//        }
//        if (stageId.equals("cetaksuratimbasprahitung")) {
//            kodDoc = "SLPH";
//        }
//        if (stageId.equals("cetakrencanajkbb")) {
//            kodDoc = "JKBBS";
//        }
//        if (stageId.equals("semakrencanaringkasptg")) {
//            kodDoc = "RPPTG";
//        }
//        if (stageId.equals("cetaknotis7g")) {
//            kodDoc = "NTS7G";
//        }
//        if (stageId.equals("sediahantarborang7c")) {
//            kodDoc = "7C";
//        }
//        if (stageId.equals("cetaksuratperingatan")) {
//            kodDoc = "SPER";
//        }
//        if (stageId.equals("cetaksuratbatal")) {
//            kodDoc = "SBTL";
//        }
//        if (stageId.equals("cetaksrtmintaterimadokumen")) {
//            kodDoc = "SMD";
//        }
//        if (stageId.equals("derafperakuanjkbbptd")) {
//            kodDoc = "JKBBD";
//        }
//        if (stageId.equals("cetakjkbbrekodkpsn")) {
//            if (mohon.getKodUrusan().getKod().equals("SBMS")) {
//                kodDoc = "JKBBG";
//            }
//        }
//        if (stageId.equals("cetaksrtdokimbaspelansediayab")) {
//            kodDoc = "SBMSD";
//        }
//        if (stageId.equals("semakderafperakuanyabptd") || stageId.equals("perakuanyabptd")) {
//            kodDoc = "RYABD";
//        }
//        if (stageId.equals("perakuanyabptg")) {
//            kodDoc = "RYABG";
//        }
//        if (stageId.equals("cetaksuratlulusberimilik5a")) {
//            kodDoc = "N5A";
//            kodDoc2 = "SKN5A";
//        }


        if (mohon.getKodUrusan().getKod().equalsIgnoreCase("425") || mohon.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
            LOG.info("::::: rehydrate for sek 425 & 425A");
            tt = enforceService.findPermohonanTandatanganDok(idPermohonan, "SKM");

            if (tt != null) {
                LOG.info("::::: rehydrate for sek 425 & 425A :: tt not null");
                tandatangan = tt.getDiTandatangan();
            }
        } else {
            String negeri = conf.getProperty("kodNegeri");
            int numNegeri = negeri.equalsIgnoreCase("04") ? 1 : negeri.equalsIgnoreCase("05") ? 2 : 0;

            int numUrusan = this.getNumUrusan(mohon);
            LOG.info("=====numUrusan======:" + numUrusan);
            LOG.info("=====numNegeri======:" + numNegeri);
            allKodDoc = getKodDokumen(numUrusan, stageId, numNegeri, mohon);
            LOG.info("=====Kod Dokumen===allKodDoc===:" + allKodDoc);
            if (allKodDoc.contains(",")) {
                StringTokenizer st = new StringTokenizer(allKodDoc, ",");
                if (st.hasMoreElements()) {
                    kodDoc = (String) st.nextElement();
                    LOG.info("=====If===KodDoc===:" + kodDoc);
                }
            } else {
                kodDoc = allKodDoc;
                LOG.info("=====else===KodDoc===:" + kodDoc);
            }

            List<PermohonanTandatanganDokumen> listTt;
            listTt = devServ.findTtDokumen(idPermohonan);
            for (PermohonanTandatanganDokumen td : listTt) {
                if (td.getKodDokumen().getKod().equals(kodDoc)) {
                    tt = td;
                }
            }

            if (tt != null) {
                tandatangan = tt.getDiTandatangan();
                if (tt.getTrhTt() != null) {
                    tarikh = sdf.format(tt.getTrhTt());
                }
            }
        }

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();

        //ptd
        listPp = new LinkedList<Pengguna>();
        listPp = devServ.findPenggunaByKumpBpel("pttptdbangun", "pptd", "pentadbirtanah", kod.getKod());
        LOG.info("---ListPp" + listPp);

//        //ptd by kod peranan
//        for (Pengguna p : lp) {
////            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {                    
//                    if (p.getPerananUtama().getKod().equals("71")) { 
//                        listPp.add(p);
//                    } 
//                    else if (p.getPerananUtama().getKod().equals("75")) {
//                        listPp.add(p);
//                    } 
//                    else if (p.getPerananUtama().getKod().equals("77")) {
//                        listPp.add(p);
//                    }
//                }
//            }
//        }

        //ptg
        listPp2 = new LinkedList<Pengguna>();
        listPp2 = devServ.findPenggunaByKumpBpel("pptg", "tptg", "ptg", kod.getKod());
        LOG.info("---ListPp2" + listPp2);

        //ptd2
        listPp3 = new LinkedList<Pengguna>();
        listPp3 = devServ.findPenggunaByKumpBpel("pttptdbangun", "pptd", "pentadbirtanah", kod.getKod());
        LOG.info("---ListPp3" + listPp3);

        //ptg2        
        listPp4 = new LinkedList<Pengguna>();
        listPp4 = devServ.findPenggunaByKumpBpel("pptg", "pptptg", "ptg", kod.getKod());
        LOG.info("---ListPp4" + listPp4);

        //PTD N9
        listPtdN9 = new LinkedList<Pengguna>();
        listPtdN9 = devServ.findPenggunaByKumpBpel("tptd", "kptptdbangun", "ptd", kod.getKod());
        LOG.info("---listPtdN9" + listPtdN9);
        
        //PTG N9
        listPtgN9 = new LinkedList<Pengguna>();
        listPtgN9 = devServ.findPenggunaByKumpBpel("pptg", "kptptgbangun", "ptg", kod.getKod());
        LOG.info("---listPtgN9" + listPtgN9);

        LOG.info("rehydrate finish");
    }

    public Resolution simpanTandatangan() {
        LOG.info("simpanTandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        if (mohon.getKodUrusan().getKod().equalsIgnoreCase("425") || mohon.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
            LOG.info("::: for sek425 & sek 425A");
            if (tt == null) {
                tt = new PermohonanTandatanganDokumen();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tt.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            tt.setPermohonan(mohon);
            tt.setInfoAudit(infoAudit);
            tt.setCawangan(pguna.getKodCawangan());
            tt.setDiTandatangan(tandatangan);
            tt.setKodDokumen(kodDokumenDAO.findById("SKM"));
            tt.setTrhTt(new java.util.Date());
            enforceService.simpanPermohonanTandatanganDok(tt);
        } else {

            StringTokenizer st = new StringTokenizer(allKodDoc, ",");
            while (st.hasMoreElements()) {
                PermohonanTandatanganDokumen tt = null;
                kodDoc = (String) st.nextElement();
                LOG.info("=====simpanTandatangan===KodDoc===:" + kodDoc);

                List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
                listTt = devServ.findTtDokumen(idPermohonan);
                System.out.println("listTt:" + listTt.size());
                for (PermohonanTandatanganDokumen td : listTt) {
                    if (td.getKodDokumen().getKod().equals(kodDoc)) {
                        LOG.info("=====simpanTandatangan===If===:" + kodDoc);
                        tt = td;
                    }
                }

                if (tt != null) {
                    LOG.info("=====simpanTandatangan===If========tt======:" + tt);
                    infoAudit = tt.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDimasukOleh(pguna);
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setDiTandatangan(tandatangan);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                } else {
                    LOG.info("=====simpanTandatangan===else========tt======:" + tt);
                    tt = new PermohonanTandatanganDokumen();
                    infoAudit.setDimasukOleh(pguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setDiTandatangan(tandatangan);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                }
            }// while
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (caw.getKod().equals("00")) {
            peringkat = "ptg";
        } else {
            peringkat = "ptd";
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/tarikh_tandatangan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTarikh() {
        LOG.info("simpan tarikh tandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        StringTokenizer st = new StringTokenizer(allKodDoc, ",");
        while (st.hasMoreElements()) {
            PermohonanTandatanganDokumen tt = null;
            kodDoc = (String) st.nextElement();
            LOG.info("=====simpanTarikh===KodDoc===:" + kodDoc);

            List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
            listTt = devServ.findTtDokumen(idPermohonan);
            System.out.println("listTt:" + listTt.size());
            for (PermohonanTandatanganDokumen td : listTt) {
                if (td.getKodDokumen().getKod().equals(kodDoc)) {
                    LOG.info("=====simpanTarikh===If===:" + kodDoc);
                    tt = td;
                }
            }

            if (tt != null) {
                LOG.info("=====simpanTarikh===If========tt======:" + tt);
                infoAudit = tt.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                try {
                    Date trktt = (Date) formatter.parse(tarikh);
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setTrhTt(trktt);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                } catch (ParseException e) {
                    LOG.info("Exception:" + e);
                }
            } else {
                LOG.info("=====simpanTarikh===else========tt======:" + tt);
                LOG.info("else simpanTarikh");
                tt = new PermohonanTandatanganDokumen();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                try {
                    Date trktt = (Date) formatter.parse(tarikh);
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setTrhTt(trktt);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                } catch (ParseException e) {
                    LOG.info("Exception:" + e);
                }
            }
        }// while
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (caw.getKod().equals("00")) {
            peringkat = "ptg";
        } else {
            peringkat = "ptd";
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/tarikh_tandatangan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTarikhOnly() {
        LOG.info("simpan tarikh tandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        StringTokenizer st = new StringTokenizer(allKodDoc, ",");
        while (st.hasMoreElements()) {
            PermohonanTandatanganDokumen tt = null;
            kodDoc = (String) st.nextElement();
            LOG.info("=====simpanTarikh===KodDoc===:" + kodDoc);

            List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
            listTt = devServ.findTtDokumen(idPermohonan);
            System.out.println("listTt:" + listTt.size());
            for (PermohonanTandatanganDokumen td : listTt) {
                if (td.getKodDokumen().getKod().equals(kodDoc)) {
                    LOG.info("=====simpanTarikh===If===:" + kodDoc);
                    tt = td;
                }
            }

            if (tt != null) {
                LOG.info("=====simpanTarikh===If========tt======:" + tt);
                infoAudit = tt.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                try {
                    Date trktt = (Date) formatter.parse(tarikh);
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setTrhTt(trktt);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                } catch (ParseException e) {
                    LOG.info("Exception:" + e);
                }
            } else {
                LOG.info("=====simpanTarikh===else========tt======:" + tt);
                LOG.info("else simpanTarikh");
                tt = new PermohonanTandatanganDokumen();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                try {
                    Date trktt = (Date) formatter.parse(tarikh);
                    tt.setCawangan(caw);
                    tt.setPermohonan(mohon);
                    tt.setInfoAudit(infoAudit);
                    tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                    tt.setTrhTt(trktt);
                    devServ.simpanPermohonanTandatanganDokumen(tt);
                } catch (ParseException e) {
                    LOG.info("Exception:" + e);
                }
            }
        }// while
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (caw.getKod().equals("00")) {
            peringkat = "ptg";
        } else {
            peringkat = "ptd";
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/tarikh_only.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTandatangan2() {
        LOG.info("simpanTandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //     Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
        listTt = devServ.findTtDokumen(idPermohonan);
        System.out.println("listTt:" + listTt.size());
        for (PermohonanTandatanganDokumen td : listTt) {
            if (td.getKodDokumen().getKod().equals(kodDoc)) {
                tt = td;
            }
        }

        if (tt != null) {
            infoAudit = tt.getInfoAudit();
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(pguna);
            tt.setCawangan(caw);
            tt.setPermohonan(mohon);
            tt.setInfoAudit(infoAudit);
            tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
            tt.setDiTandatangan(tandatangan);
            devServ.simpanPermohonanTandatanganDokumen(tt);
        } else {
            tt = new PermohonanTandatanganDokumen();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tt.setCawangan(caw);
            tt.setPermohonan(mohon);
            tt.setInfoAudit(infoAudit);
            tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
            tt.setDiTandatangan(tandatangan);
            devServ.simpanPermohonanTandatanganDokumen(tt);
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (caw.getKod().equals("00")) {
            peringkat = "ptg";
        } else {
            peringkat = "ptd";
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTandatangan3() {
        LOG.info("simpanTandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        StringTokenizer st = new StringTokenizer(allKodDoc, ",");
        while (st.hasMoreElements()) {
            PermohonanTandatanganDokumen tt = null;
            kodDoc = (String) st.nextElement();
            LOG.info("=====simpanTandatangan===KodDoc===:" + kodDoc);

            List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
            listTt = devServ.findTtDokumen(idPermohonan);
            System.out.println("listTt:" + listTt.size());
            for (PermohonanTandatanganDokumen td : listTt) {
                if (td.getKodDokumen().getKod().equals(kodDoc)) {
                    LOG.info("=====simpanTandatangan===If===:" + kodDoc);
                    tt = td;
                }
            }

            if (tt != null) {
                LOG.info("=====simpanTandatangan===If========tt======:" + tt);
                infoAudit = tt.getInfoAudit();
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
                tt.setCawangan(caw);
                tt.setPermohonan(mohon);
                tt.setInfoAudit(infoAudit);
                tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                tt.setDiTandatangan(tandatangan);
                devServ.simpanPermohonanTandatanganDokumen(tt);
            } else {
                LOG.info("=====simpanTandatangan===else========tt======:" + tt);
                tt = new PermohonanTandatanganDokumen();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tt.setCawangan(caw);
                tt.setPermohonan(mohon);
                tt.setInfoAudit(infoAudit);
                tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                tt.setDiTandatangan(tandatangan);
                devServ.simpanPermohonanTandatanganDokumen(tt);
            }
        }// while

        peringkat = "ptd2";
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTandatangan4() {
        LOG.info("simpanTandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
        listTt = devServ.findTtDokumen(idPermohonan);
        System.out.println("listTt:" + listTt.size());
        for (PermohonanTandatanganDokumen td : listTt) {
            if (td.getKodDokumen().getKod().equals(kodDoc)) {
                tt = td;
            }
        }

        if (tt != null) {
            infoAudit = tt.getInfoAudit();
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(pguna);
            tt.setCawangan(caw);
            tt.setPermohonan(mohon);
            tt.setInfoAudit(infoAudit);
            tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
            tt.setDiTandatangan(tandatangan);
            devServ.simpanPermohonanTandatanganDokumen(tt);
        } else {
            tt = new PermohonanTandatanganDokumen();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            tt.setCawangan(caw);
            tt.setPermohonan(mohon);
            tt.setInfoAudit(infoAudit);
            tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
            tt.setDiTandatangan(tandatangan);
            devServ.simpanPermohonanTandatanganDokumen(tt);
        }

        peringkat = "ptg2";
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/tarikh_tandatangan2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTandatanganN9() {
        LOG.info("simpanTandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        StringTokenizer st = new StringTokenizer(allKodDoc, ",");
        while (st.hasMoreElements()) {
            PermohonanTandatanganDokumen tt = null;
            kodDoc = (String) st.nextElement();
            LOG.info("=====simpanTandatangan===KodDoc===:" + kodDoc);

            List<PermohonanTandatanganDokumen> listTt = new ArrayList<PermohonanTandatanganDokumen>();
            listTt = devServ.findTtDokumen(idPermohonan);
            System.out.println("listTt:" + listTt.size());
            for (PermohonanTandatanganDokumen td : listTt) {
                if (td.getKodDokumen().getKod().equals(kodDoc)) {
                    LOG.info("=====simpanTandatangan===If===:" + kodDoc);
                    tt = td;
                }
            }

            if (tt != null) {
                LOG.info("=====simpanTandatangan===If========tt======:" + tt);
                infoAudit = tt.getInfoAudit();
                infoAudit.setTarikhKemaskini(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
                tt.setCawangan(caw);
                tt.setPermohonan(mohon);
                tt.setInfoAudit(infoAudit);
                tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                tt.setDiTandatangan(tandatangan);
                devServ.simpanPermohonanTandatanganDokumen(tt);
            } else {
                LOG.info("=====simpanTandatangan===else========tt======:" + tt);
                tt = new PermohonanTandatanganDokumen();
                infoAudit.setDimasukOleh(pguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                tt.setCawangan(caw);
                tt.setPermohonan(mohon);
                tt.setInfoAudit(infoAudit);
                tt.setKodDokumen(kodDokumenDAO.findById(kodDoc));
                tt.setDiTandatangan(tandatangan);
                devServ.simpanPermohonanTandatanganDokumen(tt);
            }
        }
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (caw.getKod().equals("00")) {
            peringkat = "ptg";
        } else {
            peringkat = "ptd";
        }

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/ns/tandatangan.jsp").addParameter("tab", "true");
    }
    
    
    public String currentStageId() {
        service = new BPelService();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        return stageId;
    }

    public int getNumUrusan(Permohonan permohonan) {
        int numUrusan = permohonan.getKodUrusan().getKod().equals("TSPSS") ? 1
                : permohonan.getKodUrusan().getKod().equals("SBMS") ? 2
                : permohonan.getKodUrusan().getKod().equals("PPT") ? 3
                : permohonan.getKodUrusan().getKod().equals("PPCS") ? 4
                : permohonan.getKodUrusan().getKod().equals("PPCB") ? 5
                : permohonan.getKodUrusan().getKod().equals("PPCBA") ? 6
                : permohonan.getKodUrusan().getKod().equals("PYTN") ? 7
                : permohonan.getKodUrusan().getKod().equals("TSKKT") ? 8
                : permohonan.getKodUrusan().getKod().equals("TSPSN") ? 9
                : permohonan.getKodUrusan().getKod().equals("TSBSN") ? 10
                : permohonan.getKodUrusan().getKod().equals("TSKSN") ? 11
                : permohonan.getKodUrusan().getKod().equals("PPK") ? 12
                : permohonan.getKodUrusan().getKod().equals("PBTL") ? 13
                : permohonan.getKodUrusan().getKod().equals("PBSK") ? 14
                : permohonan.getKodUrusan().getKod().equals("RBPA") ? 15
                : permohonan.getKodUrusan().getKod().equals("RLTB") ? 16
                : permohonan.getKodUrusan().getKod().equals("RNS") ? 17
                : permohonan.getKodUrusan().getKod().equals("RPP") ? 18
                : permohonan.getKodUrusan().getKod().equals("RPMMK") ? 19
                : permohonan.getKodUrusan().getKod().equals("PSBT") ? 20
                : permohonan.getKodUrusan().getKod().equals("PSMT") ? 21
                : permohonan.getKodUrusan().getKod().equals("RTLK") ? 22
                : permohonan.getKodUrusan().getKod().equals("TSPTD") ? 23
                : permohonan.getKodUrusan().getKod().equals("TSPTG") ? 24
                : permohonan.getKodUrusan().getKod().equals("TSMMK") ? 25
                : permohonan.getKodUrusan().getKod().equals("SBPS") ? 26
                : 0;
        return numUrusan;
    }

    public String getKodDokumen(int numUrusan, String stageId, int numNegeri, Permohonan permohonan) {
        LOG.info("=====getKodDokumen======");
        String kodDok = new String();
        DevTandatanganManual dtm = new DevTandatanganManual();
        switch (numUrusan) {
            case 1:
                //TSPSS
                LOG.info("=====getKodDokumen======TSPSS=======");
                kodDok = dtm.getKodDokByCaseTSPSS(stageId, numNegeri, permohonan);
                break;
            case 2:
                //SBMS
                LOG.info("=====getKodDokumen======SBMS=======");
                kodDok = dtm.getKodDokByCaseSBMS(stageId, numNegeri, permohonan);
                break;
            case 3:
                //PPT
                LOG.info("=====getKodDokumen======PPT=======");
                kodDok = dtm.getKodDokByCasePPT(stageId, numNegeri, permohonan);
                break;
            case 4:
                //PPCS
                LOG.info("=====getKodDokumen======PPCS=======");
                kodDok = dtm.getKodDokByCasePPCS(stageId, numNegeri, permohonan);
                break;
            case 5:
                //PPCB
                LOG.info("=====getKodDokumen======PPCB=======");
                kodDok = dtm.getKodDokByCasePPCB(stageId, numNegeri, permohonan);
                break;
            case 6:
                //PPCBA
                LOG.info("=====getKodDokumen======PPCBA=======");
                kodDok = dtm.getKodDokByCasePPCBA(stageId, numNegeri, permohonan);
                break;
            case 7:
                //PYTN
                LOG.info("=====getKodDokumen======PYTN=======");
                kodDok = dtm.getKodDokByCasePYTN(stageId, numNegeri, permohonan);
                break;
            case 8:
                //TSKKT
                LOG.info("=====getKodDokumen======TSKKT=======");
                kodDok = dtm.getKodDokByCaseTSKKT(stageId, numNegeri, permohonan);
                break;
            case 9:
                //TSPSN
                LOG.info("=====getKodDokumen======TSPSN=======");
                kodDok = dtm.getKodDokByCaseTSPSN(stageId, numNegeri, permohonan);
                break;
            case 10:
                //TSBSN
                LOG.info("=====getKodDokumen======TSBSN=======");
                kodDok = dtm.getKodDokByCaseTSBSN(stageId, numNegeri, permohonan);
                break;
            case 11:
                //TSKSN
                LOG.info("=====getKodDokumen======TSKSN=======");
                kodDok = dtm.getKodDokByCaseTSKSN(stageId, numNegeri, permohonan);
                break;
            case 12:
                //PPK
                LOG.info("=====getKodDokumen======PPK=======");
                kodDok = dtm.getKodDokByCasePPK(stageId, numNegeri, permohonan);
                break;
            case 13:
                //PBTL
                LOG.info("=====getKodDokumen======PBTL=======");
                kodDok = dtm.getKodDokByCasePBTL(stageId, numNegeri, permohonan);
                break;
            case 14:
                //PBSK
                LOG.info("=====getKodDokumen======PBSK=======");
                kodDok = dtm.getKodDokByCasePBSK(stageId, numNegeri, permohonan);
                break;
            case 15:
                //RBPA
                LOG.info("=====getKodDokumen======RBPA=======");
                kodDok = dtm.getKodDokByCaseRBPA(stageId, numNegeri, permohonan);
                break;
            case 16:
                //RLTB
                LOG.info("=====getKodDokumen======RLTB=======");
                kodDok = dtm.getKodDokByCaseRLTB(stageId, numNegeri, permohonan);
                break;
            case 17:
                //RNS
                LOG.info("=====getKodDokumen======RNS=======");
                kodDok = dtm.getKodDokByCaseRNS(stageId, numNegeri, permohonan);
                break;
            case 18:
                //RPP
                LOG.info("=====getKodDokumen======RPP=======");
                kodDok = dtm.getKodDokByCaseRPP(stageId, numNegeri, permohonan);
                break;
            case 19:
                //RPMMK
                LOG.info("=====getKodDokumen======RPMMK=======");
                kodDok = dtm.getKodDokByCaseRPMMK(stageId, numNegeri, permohonan);
                break;
            case 20:
                //PSBT
                LOG.info("=====getKodDokumen======PSBT=======");
                kodDok = dtm.getKodDokByCasePSBT(stageId, numNegeri, permohonan);
                break;
            case 21:
                //PSMT
                LOG.info("=====getKodDokumen======PSMT=======");
                kodDok = dtm.getKodDokByCasePSMT(stageId, numNegeri, permohonan);
                break;

            case 22:
                //RTLK
                LOG.info("=====getKodDokumen======RTLK=======");
                kodDok = dtm.getKodDokByCaseRTLK(stageId, numNegeri, permohonan);
                break;
                
            case 23:
                //TSPTD
                LOG.info("=====getKodDokumen======TSPTD=======");
                kodDok = dtm.getKodDokByCaseRTLK(stageId, numNegeri, permohonan);
                break;
                
                
           case 24:
                //TSPTG
                LOG.info("=====getKodDokumen======TSPTG=======");
                kodDok = dtm.getKodDokByCaseRTLK(stageId, numNegeri, permohonan);
                break;
               
               
           case 25:
                //TSMMK
                LOG.info("=====getKodDokumen======TSMMK=======");
                kodDok = dtm.getKodDokByCaseRTLK(stageId, numNegeri, permohonan);
                break;
               
                             
           case 26:
                //SBPS
                LOG.info("=====getKodDokumen======SBPS=======");
                kodDok = dtm.getKodDokByCaseRTLK(stageId, numNegeri, permohonan);
                break;

        }

        return kodDok;
    }

    public List<Pengguna> getListPp2() {
        return listPp2;
    }

    public void setListPp2(List<Pengguna> listPp2) {
        this.listPp2 = listPp2;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

    public boolean isBp() {
        return bp;
    }

    public void setBp(boolean bp) {
        this.bp = bp;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public PermohonanTandatanganDokumen getTt() {
        return tt;
    }

    public void setTt(PermohonanTandatanganDokumen tt) {
        this.tt = tt;
    }

    public String getKodDoc() {
        return kodDoc;
    }

    public void setKodDoc(String kodDoc) {
        this.kodDoc = kodDoc;
    }

    public String getTandatangan() {
        return tandatangan;
    }

    public void setTandatangan(String tandatangan) {
        this.tandatangan = tandatangan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public List<Pengguna> getListPp3() {
        return listPp3;
    }

    public void setListPp3(List<Pengguna> listPp3) {
        this.listPp3 = listPp3;
    }

    public List<Pengguna> getListPp4() {
        return listPp4;
    }

    public void setListPp4(List<Pengguna> listPp4) {
        this.listPp4 = listPp4;
    }

    public List<Pengguna> getListPtdN9() {
        return listPtdN9;
    }

    public void setListPtdN9(List<Pengguna> listPtdN9) {
        this.listPtdN9 = listPtdN9;
    }

    public List<Pengguna> getListPtgN9() {
        return listPtgN9;
    }

    public void setListPtgN9(List<Pengguna> listPtgN9) {
        this.listPtgN9 = listPtgN9;
    }
    
    
}

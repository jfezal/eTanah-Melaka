/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
//import able.stripes.JSP;
import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodJabatanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.PenggunaPeranan;
import etanah.model.KodJabatan;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanRujukanLuarService;
import java.util.Date;

@UrlBinding("/pengambilan/agihTugasan")
public class AgihTugasanActionBean extends AbleActionBean {

    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    PelupusanService pservice;
    @Inject
    private etanah.Configuration conf;
    @Inject
    AcqAgihanTugas dat;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    String IdPermohonan;
    private String stageID;
    private String participant;
    private List<Pengguna> listPp;
    private List<Pengguna> listPp2;
    private List<Pengguna> listPp3;
    private List<Pengguna> listPp4;
    private List<Pengguna> listPp5;
    private List<Pengguna> listPp6;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String stage;
//    private etanah.Configuration conf;
    private String kodNegeri;
    @Inject
    PermohonanService permohonanService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihTugasanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");

    @Inject
    public AgihTugasanActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

//    pelukispelan
    @DefaultHandler
    public Resolution showForm() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        listPp2 = new LinkedList<Pengguna>();
        listPp3 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("67")) { // for NS
                    if (p.getPerananUtama().getKod().equals("228")) { // modified hazirah for melaka
//                    if (p.getPerananUtama().getKod().equals("31")) { //for Melaka
                        listPp.add(p);
                        stage = "03AgihanTugasChart";
                    }
//                    else if (p.getPerananUtama().getKod().equals("71")) {  //forNS
////                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
//                        listPp.add(p);
//                        stage = "14AgihanTugas";
//                    }else if (p.getPerananUtama().getKod().equals("33")) {  //forNS
////                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
//                        listPp.add(p);
//                        stage = "14AgihanTugas";
//                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

//    @ns
    public Resolution AgihKePPelan1() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("30006")) {
                        listPp.add(p);
                    }
                    if (p.getPerananUtama().getKod().equals("67")) {
                        listPp.add(p);
                    }
                    if (p.getPerananUtama().getKod().equals("226")) {
                        listPp.add(p);
                    }
                    if (p.getPerananUtama().getKod().equals("31")) {
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution AgihKePPelan1Melaka() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("226")) {
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

//    penolongpegawaitanah
    public Resolution showForm2() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("69")) { //for NS
                    if (p.getPerananUtama().getKod().equals("24")) { //for Melaka
                        listPp.add(p);
//                        stage = "04SemakAgihTugas";
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //    Ketua penolongpegawaitanah PTG NS
    public Resolution showFormKPPTPTG() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("27")) { //for NS
                        listPp.add(p);
//                        stage = "04SemakAgihTugas";
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //    ptgpptambil1 @NS
    public Resolution pptanahptg1() {
//        KodCawangan kod = pguna.getKodCawangan();
//        List<Pengguna> lp = penggunaDao.findAll();
//        listPp = new LinkedList<Pengguna>();
//        for (Pengguna p : lp) {
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("70")) {
//                        listPp.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("237")) {
//                        listPp.add(p);
//                    }
//                }
//            }
//        }
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        listPp = dat.getPenggunaByBPELCaseSEK4("agih_so", permohonan.getCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution agihPTPengambilan() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        listPp = dat.getPenggunaByBPELCase831A("MaklumSediaSrt", pengguna.getKodCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution pptanahptg_831bc() {
//        KodCawangan kod = pguna.getKodCawangan();
//        List<Pengguna> lp = penggunaDao.findAll();
//        listPp = new LinkedList<Pengguna>();
//        for (Pengguna p : lp) {
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("70")) {
//                        listPp.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("237")) {
//                        listPp.add(p);
//                    }
//                }
//            }
//        }
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        listPp = dat.getPenggunaByBPELCase831A("agihan_tugas_ptg", pengguna.getKodCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //    ptgpptambil1 @NS
    public Resolution pptanahptd() {

        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        //Pengguna pengguna1 = penggunaDao.findById(pengguna.getIdPengguna());
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);

        if (permohonan.getKodUrusan().getKod().equals("SEK4") || permohonan.getKodUrusan().getKod().equals("831")) {
            listPp = dat.getPenggunaByBPELCaseSEK4("agih_so", pengguna.getKodCawangan().getKod());
        } else {
            //SELAIN DARI APT BARU
            String msg = getContext().getRequest().getParameter("message");
            String currentStage = getContext().getRequest().getParameter("currentStage");
            logger.info("currentStage: " + currentStage);

            if (StringUtils.isNotBlank(msg)) {
                addSimpleMessage(msg);
            }
            String kodNegeri = conf.getProperty("kodNegeri");
            logger.info("kod negeri: " + kodNegeri);
            urusan = permohonan.getKodUrusan().getKod();
        // kene cater ikut peranan..
            // List<PenggunaPeranan> list = penggunaperananDao.findAll();
            listPp = new LinkedList<Pengguna>();

            try {
                List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                for (Task t : l) {
                    stageID = t.getSystemAttributes().getStage();
//                stageID = "05SemakanAgihTugas";
                    participant = t.getSystemAttributes().getParticipantName();
                    if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                        break;
                    }
                    logger.info("-----Stage ID:" + stageID + "-----");
                }
            } catch (Exception e) {
                logger.error("error ::" + e.getMessage());
            }

            /*
             * CALLING PENGGUNA METHOD
             */
            this.getListPguna(permohonan, kodNegeri);

//        if (bpelName.size() > 0) {
//            listPp = pservice.findPenggunaByBPEL831A(bpelName);
//        }
           // return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");

        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //    Ketua Penolong Pegawai Tanah @NS
    public Resolution kpptanahptd() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//                    System.out.println("p.getPerananUtama().getKod() : "+p.getPerananUtama().getKod());

                    if (p.getPerananUtama().getKod().equals("32")) {
                        listPp.add(p);
                    }

                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution pptanahptdmelaka() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("228")) {
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution semakCharting() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        listPp = dat.getPenggunaByBPELCaseSEK4("semak_charting", permohonan.getCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");

    }

//    penolongpentadbir/pembantutadbirtertinggi - Melaka
//    penolong/timbalan ptg & ptg - NS
    public Resolution showForm3() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("80")) {  //forNS
                    if (p.getPerananUtama().getKod().equals("35")) { //for Melaka
                        listPp.add(p);
                    }
//                    if (p.getPerananUtama().getKod().equals("54")){ //for NS
////                    if (p.getPerananUtama().getKod().equals("75")){ //for Melaka
//                        listPp3.add(p);
//                    }
//                    if (p.getPerananUtama().getKod().equals("55")){ //for NS
//                        listPp3.add(p);
//                    }
//                    stage = "13_1_MaklumAgih";
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

//     penolong pegawai tanah ptg
    public Resolution showForm4() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("71")) {  //forNS
//                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
                        listPp4.add(p);
                        stage = "14AgihanTugas";
                    }

                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //ptptg - Melaka
    public Resolution showForm5() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("45")) { //for Melaka
                        listPp5.add(p);
                        stage = "agihantugas5";
                    }

                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("48")) {  //forNS
//                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
                        listPp6.add(p);
                        stage = "16agihantugas";
                    }

                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp4 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("48")) {  //forNS
//                    if (p.getPerananUtama().getKod().equals("71")) { //for Melaka
                        listPp6.add(p);
                        stage = "40terima_pa_agihtugas";
                    }

                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }
    //-----------------------------------MELAKA-------------------------------//

    public Resolution showForm10() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        listPp2 = new LinkedList<Pengguna>();
        listPp3 = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("31")) { //for Melaka
                        listPp.add(p);
                        stage = "03AgihanTugasChart";
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //    pembantu tadbir pengambilan
    public Resolution showForm13() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("29")) {
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }
    //    penolongpegawaitanah

    public Resolution showForm11() {

        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("24")) { //for Melaka
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }
    //    ptgpptambil1

    public Resolution showForm12() {
        KodCawangan kod = pguna.getKodCawangan();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (p.getPerananUtama().getKod().equals("35")) {
                        listPp.add(p);
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Agihan_Tugasan.jsp").addParameter("tab", "true");
    }

    //-----------------------------------MELAKA-------------------------------//
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        System.out.println("IdPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);
        }
    }

    private void getListPguna(Permohonan permohonan, String negeri) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("831B") ? 1
                : 0;

//        //FOR TESTING ONLY (REMOVED)
//        stageID = "arahan_charting";
        FasaPermohonan fasaPermohonan = fasaPermohonanManager.findLastStage(permohonan.getIdPermohonan());
        String mohonFasa = fasaPermohonan.getIdAliran();
//        String mohonFasa = ;
//        if (stageID == null){
//            stageID = "agih_so";
//        }
        String kodUrusan = permohonan.getKodUrusan().getKod();
        listPp = dat.getPengguna(numUrusan, stageID, negeri, permohonan.getCawangan().getKod());
//        stageID = "SEK4";
        if (stageID.equals("05SemakanAgihTugas") && permohonan.getKodUrusan().getKod().equals("831A")) {
            listPp = new ArrayList<Pengguna>();
            List<Pengguna> listPp1 = new ArrayList<Pengguna>();
            listPp = dat.getPenggunaByBPELCase831A(stageID, permohonan.getCawangan().getKod());
        }

        if (stageID.equals("semak_charting") && permohonan.getKodUrusan().getKod().equals("SEK4")) {
            listPp = new ArrayList<Pengguna>();
            List<Pengguna> listPp1 = new ArrayList<Pengguna>();
            listPp = dat.getPenggunaByBPELCase831A(stageID, permohonan.getCawangan().getKod());
        }
        if (stageID.equals("agih_so") && permohonan.getKodUrusan().getKod().equals("SEK4")) {
            listPp = new ArrayList<Pengguna>();
            List<Pengguna> listPp1 = new ArrayList<Pengguna>();
            listPp = dat.getPenggunaByBPELCaseSEK4(stageID, permohonan.getCawangan().getKod());
        }

        if (stageID.equals("05SemakanAgihTugas") && permohonan.getKodUrusan().getKod().equals("831C")) {
            listPp = new ArrayList<Pengguna>();
            List<Pengguna> listPp1 = new ArrayList<Pengguna>();
            listPp = dat.getPenggunaByBPELCase831C(stageID, permohonan.getCawangan().getKod());
        }

    }

    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        StringBuilder sb = new StringBuilder();
        Task task = null;
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID();
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
                //  WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");

                FasaPermohonan fasaPermohonan = new FasaPermohonan();

//                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                String stageIdAgihan = task.getSystemAttributes().getStage(); //stage id selepas agihan

                Permohonan permohonan = mohonDao.findById(idPermohonan);

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
//                            FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();

                FasaPermohonan fasaPermohonanBaru = fasaPermohonanManager.getCurrentStage(idPermohonan, stageIdAgihan);

//                for (FasaPermohonan mf : senaraiFasa) {
//                    if (mf.getIdAliran().equals(stageIdAgihan)
//                            && mf.getIdPengguna().equals(pengguna.getIdPengguna())) {
//                        fasaPermohonan = mf;
//                        break;
//                    }
//                }
                logger.debug("cawangan " + pengguna.getKodCawangan());

                if (fasaPermohonanBaru != null) {
                    au = fasaPermohonan.getInfoAudit();
                    au.setTarikhKemaskini(new Date());
                    au.setDiKemaskiniOleh(pguna);
                    fasaPermohonan.setInfoAudit(au);
                } else {
//                    fasaPermohonan = new FasaPermohonan();
                    au.setDimasukOleh(pguna);
                    au.setTarikhMasuk(new Date());
                    fasaPermohonan.setInfoAudit(au);
                    fasaPermohonan.setPermohonan(permohonan);
                    fasaPermohonan.setCawangan(pengguna.getKodCawangan());
                    fasaPermohonan.setIdAliran(stageIdAgihan);
                    fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                }
                fasaPermohonan.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(fasaPermohonan);

                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            return new RedirectResolution(SenaraiTugasanActionBean.class
            ).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");

        }

        return new RedirectResolution(SenaraiTugasanActionBean.class
        ).addParameter("message", sb.toString() + " :Agihan Tugasan Kepada " + pengguna.getIdPengguna() + " Telah Berjaya.");
    }

    private List<Map<String, String>> getPermohonanWithTaskID() throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        logger.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", mohon.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);

        return list;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<Pengguna> getListPp2() {
        return listPp2;
    }

    public void setListPp2(List<Pengguna> listPp2) {
        this.listPp2 = listPp2;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Pengguna> getListPp3() {
        return listPp3;
    }

    public void setListPp3(List<Pengguna> listPp3) {
        this.listPp3 = listPp3;
    }

//    public Configuration getConf() {
//        return conf;
//    }
//
//    public void setConf(Configuration conf) {
//        this.conf = conf;
//    }
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<Pengguna> getListPp4() {
        return listPp4;
    }

    public void setListPp4(List<Pengguna> listPp4) {
        this.listPp4 = listPp4;
    }

    public List<Pengguna> getListPp6() {
        return listPp6;
    }

    public void setListPp6(List<Pengguna> listPp6) {
        this.listPp6 = listPp6;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStageID() {
        return stageID;
    }

    public void setStageID(String stageID) {
        this.stageID = stageID;
    }
}

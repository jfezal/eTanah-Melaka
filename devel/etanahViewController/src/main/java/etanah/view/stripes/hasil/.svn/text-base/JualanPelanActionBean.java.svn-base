package etanah.view.stripes.hasil;

import etanah.dao.*;
import etanah.model.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.gis.UrusanCMSDAO;
import etanah.ldap.LDAPManager;
import etanah.model.gis.UrusanCMS;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit2;
import etanah.service.KaunterService;
import etanah.service.session.LoginSession;
import etanah.service.session.SessionManager;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author abdul.hakim
 * Pembelian Pelan for Melaka
 * 
 */
//@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/jualan_pelan")
public class JualanPelanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(JualanPelanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/jualan_pelan.jsp";
    private static final String PAYMENT_VIEW = "/WEB-INF/jsp/hasil/jualan_pelan_1.jsp";
    private static final String pelan = "73151";

    private DokumenKewangan dokumenKewangan;
    private KodUrusan kodUrusan;
    private Akaun akaun;
    private UrusanCMS urusanCMS;
    private Transaksi transaksi;
    private static Pengguna pguna = new Pengguna();
    private static String IDPengguna;
    private String kataLaluan;
    private static String idtrans;
    private String bpm;
    private String daerah;
    private String noCarian;
    private String jenisCarian;
    private int bilLot = 0;
    private String mod = "Biasa";
    public static boolean sessionLogOut = false;
    private boolean flag = false;
    private String kadPengenalan= null;

    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodBankDAO kodBankDAO;
    private AkaunDAO akaunDAO;
    private TransaksiDAO transaksiDAO;
    private KodAkaunDAO kodAkaunDAO;
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private PenggunaDAO penggunaDAO;
    private UrusanCMSDAO urusanCMSDAO;

    private BigDecimal total = (BigDecimal.ZERO);
    private BigDecimal jumCaraBayar = (BigDecimal.ZERO);
    private String akaunKutHarian;
    private String idKewDok;
    private String jawatan;
    private String receipt = "";
    private int bilTransaksi;
    private static String kodNegeri;
    private List<Integer> bil = new ArrayList<Integer>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<UrusanCMS> senaraiUrusanCMS = new ArrayList<UrusanCMS>();
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private List<String> senaraiLotSebelah = new ArrayList<String>();
    private List<String> chkbox = new ArrayList<String>();
    private List<String> a4Saiz = new ArrayList<String>();
    private List<String> a3Saiz = new ArrayList<String>();
    private boolean ptg = true;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    @Inject UamService uam;

    @Inject
    public JualanPelanActionBean(DokumenKewanganDAO dokumenKewanganDAO, KodTransaksiDAO kodTransaksiDAO, AkaunDAO akaunDAO,
                                 KodDokumenDAO kodDokumenDAO, KodBankDAO kodBankDAO, TransaksiDAO transaksiDAO, PenggunaDAO penggunaDAO,
                                 KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO, KodCaraBayaranDAO kodCaraBayaranDAO,
                                 KodAkaunDAO kodAkaunDAO, KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO, UrusanCMSDAO urusanCMSDAO){
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.kodTransaksiDAO = kodTransaksiDAO;
        this.kodDokumenDAO= kodDokumenDAO;
        this.kodStatusDokumenKewanganDAO = kodStatusDokumenKewanganDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
        this.akaunDAO = akaunDAO;
        this.transaksiDAO = transaksiDAO;
        this.kodAkaunDAO = kodAkaunDAO;
        this.kodStatusTransaksiKewanganDAO = kodStatusTransaksiKewanganDAO;
        this.penggunaDAO = penggunaDAO;
        this.urusanCMSDAO = urusanCMSDAO;
    }

    @Inject
    GeneratorNoResit2 noResitGenerator2;

    @Inject
    private KaunterService kaunterService;

    @Inject
    KodKutipanDAO kodKutipanDAO;

    @Inject
    KutipanHasilManager manager;

    @Inject
    private ReportUtil reportUtil;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    private etanah.Configuration conf;

    @Inject
    PenyataPemungutService pp;

    @Inject
    private ModKutipService modKutip;

    @DefaultHandler
    public Resolution showForm() throws WorkflowException {

        IDPengguna = getContext().getRequest().getParameter("uid");
        kataLaluan = getContext().getRequest().getParameter("pass");
        idtrans = getContext().getRequest().getParameter("idtrans");
        
        Pengguna peng = penggunaDAO.findById(IDPengguna);
        pguna = peng;

        validateUser(null, IDPengguna, kataLaluan);

//        if (peng == null) {
//	    auditLogin("USER_FAIL");
//        } else if(peng.getPassword() == null){
//	    auditLogin("PASS_NULL");
//        } else if(!peng.getPassword().equals(kataLaluan)) {
//	    auditLogin("PASS_FAIL");
//        } else {
//            auditLogin("USER_OK");
//            if (peng.getTarikhAkhirLogin() == null) {
//                peng.setTarikhAkhirLogin(new java.util.Date());
//            }
//
//            HttpSession ses = context.getRequest().getSession();
//            ses.setAttribute(etanah.view.etanahActionBeanContext.KEY_USER, peng);
//        }
        senaraiUrusanCMS = searchUrusanCMS(idtrans);
        bilTransaksi = senaraiUrusanCMS.size();
        collectPayment();
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public List<UrusanCMS> searchUrusanCMS(String id) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.gis.UrusanCMS u where u.idTransaksi = :idTransaksi");
//            Query q = s.createQuery("select u from etanah.model.gis.UrusanCMS u");
            q.setString("idTransaksi", id);
//            List l = q.list();
            return q.list();
        } finally {
            //session.close();
        }
    }

    private void auditLogin(String status) {
	// in case getting request information failed
	String str = String.format("LOGIN [USER:%s] [STATUS:%s]", IDPengguna, status);
        try {
		HttpServletRequest req = getContext().getRequest();
		str += String.format(" [IP:%s,%d] [ClientIP:%s] [SSL:%b] [UA:%s]",
					req.getRemoteAddr(),
					req.getRemotePort(),
					req.getHeader("ClientIP"),
					req.isSecure(),
					req.getHeader("User-Agent"));
        } catch (Exception e) {
		LOG.error(e);
        } finally {
		syslog.info(str);
	}
    }

    public Resolution collectPayment() {
        if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }
        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO);
                senaraiCaraBayaran.add(cr);
            }
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution save(){

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        pguna = pengguna;
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        String a = pengguna.getJawatan();
        jawatan = a.replace(" ", "_");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try{
            String resit = noResitGenerator2.getAndLockSerialNo(pguna);
            receipt = resit;
            // resit
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resit);
            // set idResit to pageContext
            ctx.getRequest().setAttribute("noResit", dk.getIdDokumenKewangan());
            mod = modKutip.loadPenyerahFromSession(ctx);
            dk.setAmaunBayaran(jumCaraBayar);
            dk.setKodDokumen(kodDokumenDAO.findById("PLK"));
            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk.setInfoAudit(ia);
            dk.setCawangan(caw);
            dk.setTarikhTransaksi(new java.util.Date());
            if (mod != null && mod.length() > 0) dk.setMod(kodKutipanDAO.findById(mod.charAt(0)));
            dk.setIsuKepada(dokumenKewangan.getIsuKepada());
            dk.setIdKaunter(pengguna.getIdKaunter());
            // save cara bayaran

            ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
            for (CaraBayaran cb : senaraiCaraBayaran) {
                if(cb.getAmaun() != null){
                    if (cb.getAmaun().intValue() > 0) {
                        KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                        if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {  // clear if null
                            cb.setBank(null);
                            cb.setBankCawangan(null);
                        }
                        if (cb.getBank() != null) {
                            KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                            cb.setBank(bank);
                        }if((crByr.getKod().equals("KW"))||(crByr.getKod().equals("WP"))){
                            cb.setBank(kodBankDAO.findById("PMB"));
                        }
                        cb.setKodCaraBayaran(crByr);
                        cb.setCawangan(caw);
                        cb.setInfoAudit(ia);
                        manager.saveOrUpdate(cb);
                        DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                        for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                            CaraBayaran c = senaraiCaraBayaran.get(i);
                            if(c.getKodCaraBayaran() != null){
                                if (c.getKodCaraBayaran().getKod().equals("T")) {
                                    dk.setAmaunTunai(c.getAmaun());
                                }
                            }
                        }
                        dkb.setCaraBayaran(cb);
                        dkb.setDokumenKewangan(dk);
                        dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                        dkb.setInfoAudit(ia);
                        dkb.setAktif('Y');
                        adkb.add(dkb);
                    }
                }
            }

            dk.setSenaraiBayaran(adkb);
            manager.saveOrUpdate(dk);
//            System.out.println("dk : "+dk.getIdDokumenKewangan());
            idKewDok = dk.getIdDokumenKewangan();

            updateKutipanHarian(resit, jumCaraBayar, pengguna);
            displayPayment(resit);

            tx.commit();

            addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        }catch(Exception ex) {
            noResitGenerator2.rollbackAndUnlockSerialNo(pguna);
            LOG.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Pembayaran tidak berjaya.");
        }finally{noResitGenerator2.commitAndUnlockSerialNo(pguna);}
        return new ForwardResolution(PAYMENT_VIEW);
    }

    private Long updateGISTable(String resit, int qty, BigDecimal total, String saiz){
        List<UrusanCMS> cmsList = searchUrusanCMS(idtrans);
        long x = 0L;
        
        for (int i=0; i<cmsList.size(); i++) {
            String knttA3 = "";
            String knttA4 = "";
            UrusanCMS cms = cmsList.get(i);
            if(i< a3Saiz.size()){
                if(a3Saiz.get(i) != null){
                    knttA3 = a3Saiz.get(i);
                }
            }
            if(i< a4Saiz.size()){
                if(a4Saiz.get(i) != null){
                    knttA4 = a4Saiz.get(i);
                }
            }
//            if(a4Saiz.get(i) != null)
//            if(a3Saiz.get(i) != null)
            if((!knttA3.equals(""))||(!knttA4.equals(""))){
                cms.setBaki(qty);
                cms.setBilangan(qty);
                cms.setNoResit(resit);
                cms.setKodUrusan(pelan);
//                if(saiz.equalsIgnoreCase("0"))
//                    cms.setSaizKertas("A3");
//                if(saiz.equalsIgnoreCase("1"))
//                    cms.setSaizKertas("A4");
//                if(saiz.equalsIgnoreCase("01"))
//                    cms.setSaizKertas("A4");
                if(!knttA3.equals("")){
                    cms.setKuantitiA3(Long.parseLong(knttA3));
                    cms.setBaki(Long.parseLong(knttA3));
                    cms.setBilangan(Long.parseLong(knttA3));
                }
                if(!knttA4.equals("")){
                    cms.setKuantitiA4(Long.parseLong(knttA4));
                    cms.setBaki(Long.parseLong(knttA4));
                    cms.setBilangan(Long.parseLong(knttA4));
                }
                if((!knttA3.equals(""))&&(!knttA4.equals(""))){
                    long a = cms.getKuantitiA3() + cms.getKuantitiA4();
                    cms.setBaki(a);
                    cms.setBilangan(a);
                }

                manager.saveOrUpdate(cms);
            }
            x += cms.getBilangan();
        }
        return x;
    }

    public Resolution delete(){
        String id = getContext().getRequest().getParameter("idTr");

        UrusanCMS cms = urusanCMSDAO.findById(id);

        manager.deleteCMS(cms);

        senaraiUrusanCMS = searchUrusanCMS(idtrans);
        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");
    }

    public Resolution showPlanInfo(){
        String idTrans = getContext().getRequest().getParameter("idTrans");
        String noTrans = getContext().getRequest().getParameter("noTrans");
        List<KodBandarPekanMukim> bpmList = new ArrayList<KodBandarPekanMukim>();
        String lotSebelah = "";

        List<UrusanCMS> infoList = searchPelanInfo(idTrans, noTrans);
        for (UrusanCMS cms : infoList) {
            daerah = cms.getDaerah().getNama();
            noCarian = cms.getNoCarian();
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :daerah AND u.bandarPekanMukim = :bpm");
            q.setString("daerah", cms.getDaerah().getKod());
            q.setString("bpm", cms.getKodMukim());
            bpmList = q.list();
            jenisCarian = cms.getJenisCarian();
            if(cms.getLotSebelah() != null)
                lotSebelah = cms.getLotSebelah();
        }
        for (KodBandarPekanMukim kodBPM : bpmList) {
            bpm = kodBPM.getNama();
        }
        String [] a = StringUtils.splitPreserveAllTokens(lotSebelah, ",");
        bilLot = a.length;
        for(int i=0; i<a.length;i++){
            String b = a[i];
            senaraiLotSebelah.add(b.substring(2, b.length()));
        }
        for (String xx : senaraiLotSebelah) {
        }
        return new JSP("hasil/jualan_pelan_info.jsp").addParameter("popup", "true");
    }

    public List<UrusanCMS> searchPelanInfo(String idTransaksi, String noTransaksi) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.gis.UrusanCMS u where u.idTransaksi = :idTransaksi AND u.noTransaksi = :noTransaksi");
        q.setString("idTransaksi", idTransaksi);
        q.setString("noTransaksi", noTransaksi);
        return q.list();
    }

    private void updateKutipanHarian(String resit, BigDecimal total, Pengguna pengguna){
        List<Akaun>akaunList = new ArrayList<Akaun>();
        KodAkaun kodAkaun = kodAkaunDAO.findById("AKH");
        String [] n1 = {"kodAkaun"};
        Object [] v1 = {kodAkaun};
        akaunList = akaunDAO.findByEqualCriterias(n1, v1, null);
       
        for (Akaun ak : akaunList) {
            if(ak.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())){
                akaunKutHarian = ak.getNoAkaun();
            }
        }
        akaun = new Akaun();
        akaun = akaunDAO.findById(akaunKutHarian);
        akaun.setBaki((akaun.getBaki().add(total)));

        manager.saveOrUpdate(akaun);

        Integer qty = 0;
        String flg = "";
        int kntt = 0;
        for (int i = 0; i < bil.size(); i++) {
            qty = bil.get(i);
            String flg1 = chkbox.get(i);
            if((qty != null)){
                kntt = kntt+qty;
                flg = flg+flg1;
            }
        }
        createTransaction(resit, akaunKutHarian, pengguna, pelan, kntt, total, flg);
    }

    public void createTransaction(String noResit, String noAcc, Pengguna pengguna, String kod, int qty, BigDecimal tot, String flg){

            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            int year = Integer.parseInt(sdf.format(now));

            transaksi = new Transaksi();
            transaksi.setCawangan(pengguna.getKodCawangan());
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(kod));
            transaksi.setAmaun(tot);
            transaksi.setDokumenKewangan(dokumenKewanganDAO.findById(noResit));
            transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            transaksi.setUntukTahun(year);
            transaksi.setTahunKewangan(year);
            transaksi.setBayaranAgensi("N");
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
            transaksi.setInfoAudit(ia);
            transaksi.setAkaunDebit(akaunDAO.findById(noAcc));
            long x = updateGISTable(noResit, qty, tot, flg);
            transaksi.setKuantiti((int) x);
            manager.save(transaksi);

            DokumenKewangan dk = dokumenKewanganDAO.findById(noResit);
            pp.savePenyataPemungut(dk);

    }

    public void displayPayment(String resit){

        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.Transaksi u where u.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan");
        q.setString("idDokumenKewangan", resit);

        senaraiTransaksi = q.list();

    }

    @DontValidate
    public Resolution main() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = new Pengguna();
        pengguna = ctx.getUser();
        if(pengguna.getKodCawangan().getKod().equals("00")){
            ptg = false;
        }
        pguna = penggunaDAO.findById(IDPengguna);
        return new RedirectResolution(etanah.view.kaunter.PermohonanKaunter.class);
//        return new ForwardResolution("/WEB-INF/jsp/kaunter/main.jsp");
    }

    public Resolution cetak() throws FileNotFoundException {
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKew = getContext().getRequest().getParameter("idKew");
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idKew);
        reportUtil.generateReport("HSLResitJualanPelan_MLK.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idKew},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);

    }

    public void validateUser(ValidationErrors errors, String idPguna, String pass) throws WorkflowException {
        LOG.debug("sessionLogout : " + sessionLogOut);
        Pengguna peng = penggunaDAO.findById(idPguna);
        LOG.debug("--else--");

        if (peng != null) {
            try {
                // if user does not exists on LDAP
                // then we must not allow it
                if (!LDAPManager.isValidUser(IDPengguna, pass)) {
                    if (peng != null) {
                        if (peng.getBilCubaan() != null) {
                            peng.setBilCubaan(peng.getBilCubaan() + 1);
                        } else {
                            peng.setBilCubaan(Long.parseLong("1"));
                        }
                    }
                    uam.savePengguna(peng);
                    peng = null;
                }
            } catch (Exception e) {
                LOG.info("User LDAP validation fail");
                peng = null;
            }
        }
        if (peng == null) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_FAIL");
            peng = penggunaDAO.findById(IDPengguna);
        } else if (peng.getStatus() == null || !"A".equals(peng.getStatus().getKod())) {
            errors.add("IDPengguna", new SimpleError("Maklumat anda untuk log masuk tidak sah"));
            auditLogin("USER_INACTIVE");

        } else if (peng.getBilCubaan() != null && peng.getBilCubaan() >= 3) {
            errors.add("IDPengguna", new SimpleError("Akaun anda telah disekat. Sila hubungi pentadbir sistem untuk mengaktifkan semula akaun"));
            KodStatusPengguna ksp = new KodStatusPengguna();
            ksp.setKod("X");
            peng.setStatus(ksp);
                 uam.savePengguna(peng);
            auditLogin("USER_INACTIVE");

        } else {
            LOG.info("----------------------------------else----------------------------------");
            SessionManager sm = SessionManager.getInstance();
            LoginSession ls = sm.getActiveSessionByUserId(IDPengguna);
            if (ls != null) {
                if (sessionLogOut) {
                    if (org.apache.commons.lang.StringUtils.isNotBlank(kadPengenalan) && peng.getNoPengenalan().equals(kadPengenalan)) {
                        sm.killSession(ls.getSessionId());
                    } else {
                        errors.add("IDPengguna", new SimpleError("No Kad Pengenalan untuk \"" + IDPengguna + "\" "
                                + "tidak sah. Sila hubungi pentadbir sistem"));
                        setFlag(false);
                        sessionLogOut = false;
                    }
                } else {
                    errors.add("IDPengguna", new SimpleError("Pengguna \"" + IDPengguna + "\" "
                            + "telah pun log masuk pada " + formatSDF(ls.getDateCreated()) + ". Sila klik Log Keluar untuk keluar "
                            + "dari sistem terlebih dahulu atau sila masukkan No. Kad Pengenalan untuk memulakan sesi baru."));
                    setFlag(true);
                    auditLogin("MULTIPLE_LOGIN_ATTEMPT");

                    sessionLogOut = true;
                    return;
                }
            }
            auditLogin("USER_OK");
            peng.setBilCubaan(null);
            uam.savePengguna(peng);
            if (peng.getTarikhAkhirLogin() == null) {
                peng.setTarikhAkhirLogin(new java.util.Date());

            }

            HttpSession ses = context.getRequest().getSession();
            ses.setAttribute(etanah.view.etanahActionBeanContext.KEY_USER, peng);

            sm.addSession(ses.getId(), peng.getIdPengguna(), new Date(),
                    context.getRequest().getRemoteAddr(), context.getRequest().getRemoteHost());
        }


    }

    public String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public List<Integer> getBil() {
        return bil;
    }

    public void setBil(List<Integer> bil) {
        this.bil = bil;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public String getAkaunKutHarian() {
        return akaunKutHarian;
    }

    public void setAkaunKutHarian(String akaunKutHarian) {
        this.akaunKutHarian = akaunKutHarian;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public String getIDPengguna() {
        return IDPengguna;
    }

    public void setIDPengguna(String IDPengguna) {
        this.IDPengguna = IDPengguna;
    }

    public String getKataLaluan() {
        return kataLaluan;
    }

    public void setKataLaluan(String kataLaluan) {
        this.kataLaluan = kataLaluan;
    }

    public String getIdtrans() {
        return idtrans;
    }

    public void setIdtrans(String idtrans) {
        this.idtrans = idtrans;
    }

    public UrusanCMS getUrusanCMS() {
        return urusanCMS;
    }

    public void setUrusanCMS(UrusanCMS urusanCMS) {
        this.urusanCMS = urusanCMS;
    }

    public List<UrusanCMS> getSenaraiUrusanCMS() {
        return senaraiUrusanCMS;
    }

    public void setSenaraiUrusanCMS(List<UrusanCMS> senaraiUrusanCMS) {
        this.senaraiUrusanCMS = senaraiUrusanCMS;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getNoCarian() {
        return noCarian;
    }

    public void setNoCarian(String noCarian) {
        this.noCarian = noCarian;
    }

    public List<String> getSenaraiLotSebelah() {
        return senaraiLotSebelah;
    }

    public void setSenaraiLotSebelah(List<String> senaraiLotSebelah) {
        this.senaraiLotSebelah = senaraiLotSebelah;
    }

    public int getBilLot() {
        return bilLot;
    }

    public void setBilLot(int bilLot) {
        this.bilLot = bilLot;
    }

    public String getJenisCarian() {
        return jenisCarian;
    }

    public void setJenisCarian(String jenisCarian) {
        this.jenisCarian = jenisCarian;
    }

    public List<String> getChkbox() {
        return chkbox;
    }

    public void setChkbox(List<String> chkbox) {
        this.chkbox = chkbox;
    }

    public int getBilTransaksi() {
        return bilTransaksi;
    }

    public void setBilTransaksi(int bilTransaksi) {
        this.bilTransaksi = bilTransaksi;
    }

    public List<String> getA3Saiz() {
        return a3Saiz;
    }

    public void setA3Saiz(List<String> a3Saiz) {
        this.a3Saiz = a3Saiz;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public List<String> getA4Saiz() {
        return a4Saiz;
    }

    public void setA4Saiz(List<String> a4Saiz) {
        this.a4Saiz = a4Saiz;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<KodUrusan> getPilihanKodUrusan() {
        return kaunterService.findAllUrusanByJabatan();
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static boolean isSessionLogOut() {
        return sessionLogOut;
    }

    public static void setSessionLogOut(boolean sessionLogOut) {
        JualanPelanActionBean.sessionLogOut = sessionLogOut;
    }

    public String getKadPengenalan() {
        return kadPengenalan;
    }

    public void setKadPengenalan(String kadPengenalan) {
        this.kadPengenalan = kadPengenalan;
    }
}

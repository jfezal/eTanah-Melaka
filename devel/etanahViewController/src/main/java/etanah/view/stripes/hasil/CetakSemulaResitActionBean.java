package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/cetak_semula_resit")
public class CetakSemulaResitActionBean extends AbleActionBean {

    private DokumenKewangan dokumenKewangan;
    private Permohonan permohonan;
    private CaraBayaran caraBayaran;
    private DokumenKewanganBayaran dokumenKewanganBayaran;
    private Pengguna pengguna;
    private Transaksi transaksi;
    private Akaun akaun;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private boolean flag = false;
    private boolean flag1 = false;
    private String pembeli = "";
    private static String kodNegeri;
    private String radio = "";
    private DokumenKewanganDAO dokumenKewanganDAO;
    private PermohonanDAO permohonanDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private PenggunaDAO penggunaDAO;
    private TransaksiDAO transaksiDAO;
    private AkaunDAO akaunDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private List<DokumenKewanganBayaran> senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Akaun> senaraiAkauan = new ArrayList<Akaun>();
    private List<DokumenKewangan> senaraiDokumenKewangan = new ArrayList<DokumenKewangan>();
    private static final Logger LOG = Logger.getLogger(CetakSemulaResitActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_resit.jsp";
    private static final String DETAIL_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_resit_1.jsp";

    @Inject
    public CetakSemulaResitActionBean(DokumenKewanganDAO dokumenKewanganDAO, PermohonanDAO permohonanDAO,
            DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO,
            PenggunaDAO penggunaDAO, TransaksiDAO transaksiDAO, AkaunDAO akaunDAO,
            HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.permohonanDAO = permohonanDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
        this.penggunaDAO = penggunaDAO;
        this.transaksiDAO = transaksiDAO;
        this.akaunDAO = akaunDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeri";
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution search() {
        if (dokumenKewangan != null) {
            senaraiTransaksi = searchByNoResit();
            return new ForwardResolution(DETAIL_VIEW);
        }
        if (permohonan != null) {
            searchByIdPermohonan();
        }
        if (akaun != null) {
            searchByNoAkaun();
            flag = true;
            return new ForwardResolution(DEFAULT_VIEW);
        }
        if (hakmilik != null) {
            searchByIDHakmilik();
            flag = true;
            return new ForwardResolution(DEFAULT_VIEW);
        }
        return new ForwardResolution(DETAIL_VIEW);
    }

    public void searchByIDHakmilik() {
        Session s = sessionProvider.get();
        Query qAkaun = s.createQuery("SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik = :hm AND a.kodAkaun ='AC'");
        qAkaun.setString("hm", hakmilik.getIdHakmilik());
        Akaun ak = (Akaun) qAkaun.uniqueResult();

        Query q = s.createQuery("SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.akaun.noAkaun = :noAkaun AND dk.status.kod ='A' ");
        q.setString("noAkaun", ak.getNoAkaun());
        senaraiDokumenKewangan = q.list();
    }

    public void searchByNoAkaun() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.akaun.noAkaun = :noAkaun AND dk.status.kod ='A' ");
        q.setString("noAkaun", akaun.getNoAkaun());
        senaraiDokumenKewangan = q.list();
    }

    public Resolution next() {
        dokumenKewangan = dokumenKewanganDAO.findById(radio);
            senaraiTransaksi = searchByNoResit();
        return new ForwardResolution(DETAIL_VIEW);
    }

//    public void searhByNoResit(){
//        LOG.info("dokumenKewangan.getIdDokumenKewangan() : "+dokumenKewangan.getIdDokumenKewangan());
//        String idResit = dokumenKewangan.getIdDokumenKewangan();
//        dokumenKewangan = new DokumenKewangan();
//        dokumenKewangan = dokumenKewanganDAO.findById(idResit);
//
//        Session s = sessionProvider.get();
//
//        String sqlResit = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.idDokumenKewangan = :kod";
//        Query qResit = s.createQuery(sqlResit);
//        qResit.setString("kod", dokumenKewangan.getIdDokumenKewangan());
//
//        LOG.info("idresit: "+dokumenKewangan.getIdDokumenKewangan());
//
//           DokumenKewangan dk = (DokumenKewangan) qResit.uniqueResult();
//
//        if(dk !=null){idResit = dk.getIdDokumenKewangan();
//
//            if(dokumenKewangan !=null){
//            String idKaunter = null;
//            if(kodNegeri.equals("melaka")){
//                idKaunter = idResit.substring(4, 6);}
//            if(kodNegeri.equals("negeri")){
//                idKaunter = idResit.substring(10, 12);}
//
//
//            Query q = s.createQuery("SELECT p FROM etanah.model.Pengguna p WHERE p.idKaunter =:kaunter AND p.kodCawangan.kod =:caw");
//                q.setString("kaunter", idKaunter);
//                q.setString("caw", dokumenKewangan.getCawangan().getKod());
//            pengguna = (Pengguna) q.uniqueResult();
//            }
//            }
//        else{
//            String sql1 = "SELECT d FROM etanah.model.DokumenKewangan d WHERE d.noRujukan = :kod";
//            Query q1 = s.createQuery(sql1);
//            q1.setString("kod", dokumenKewangan.getIdDokumenKewangan());
//            DokumenKewangan d = (DokumenKewangan) q1.uniqueResult();
//            if(d!=null){idResit = d.getIdDokumenKewangan();}
//        }
////-------------------------------------------------------
//
//        String a = "";
//        if(dokumenKewangan !=null){
////            String idKaunter = null;
////            if(kodNegeri.equals("melaka")){
////                idKaunter = idResit.substring(4, 6);}
////            if(kodNegeri.equals("negeri")){
////                idKaunter = idResit.substring(10, 12);}
//
//
//            Query q = s.createQuery("SELECT p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod =:caw");
//                q.setString("caw", dokumenKewangan.getCawangan().getKod());
//            pengguna = (Pengguna) q.uniqueResult();
//
////            String [] v1 = {"idKaunter"};
////            String [] v2 = {a};
////            List<Pengguna> pList = penggunaDAO.findByEqualCriterias(v1, v2, null);
//////            String idPguna = "";
//////            for (Pengguna pguna : pList) {
//////                idPguna = pguna.getIdPengguna();
//////            }
////            pengguna = new Pengguna();
//////            pengguna = penggunaDAO.findById(idPguna);
////            pengguna = dokumenKewangan.getInfoAudit().getDimasukOleh();
//
//            dokumenKewanganBayaran = new DokumenKewanganBayaran();
//            String [] n1 = {"dokumenKewangan"};
//            Object [] n2 = {dokumenKewangan};
//            senaraiCaraBayaran = dokumenKewanganBayaranDAO.findByEqualCriterias(n1, n2, null);
//
//            String noAkaun = "";
//            transaksi = new Transaksi();
//            String [] a1 = {"dokumenKewangan"};
//            Object [] a2 = {dokumenKewangan};
//            senaraiTransaksi = transaksiDAO.findByEqualCriterias(a1, a2, null);
//            String pmohonan = "";
////            for (Transaksi tr : senaraiTransaksi) {
////                if((tr.getAkaunDebit()!=null)&&(tr.getAkaunKredit() != null)){
////                    noAkaun = tr.getAkaunKredit().getNoAkaun();
////                }else if(tr.getPermohonan()!=null){
////                    pmohonan = tr.getPermohonan().getIdPermohonan();
////                }
////            }
//            noAkaun = dokumenKewangan.getAkaun().getNoAkaun();
//            if(!noAkaun.equals("")){
//                akaun = akaunDAO.findById(noAkaun);
//            }
//            if(!pmohonan.equals("")){
//
//                permohonan = permohonanDAO.findById(pmohonan);
//                LOG.info("idPermohonan : "+permohonan.getIdPermohonan());
//
//                String [] c1 = {"permohonan"};
//                Object [] c2 = {permohonan};
//                List<HakmilikPermohonan> hpList = hakmilikPermohonanDAO.findByEqualCriterias(c1, c2, null);
//
//                for (HakmilikPermohonan hp : hpList) {
//                    hakmilik = new Hakmilik();
//                    hakmilik = hp.getHakmilik();
//                }
//
//                String [] d1 = {"hakmilik"};
//                Object [] d2 = {hakmilik};
//                List<Akaun> accList = akaunDAO.findByEqualCriterias(d1, d2, null);
//                for (Akaun ac : accList) {
//                    if(ac.getKodAkaun().getKod().equals("AC")){
//                        akaun = ac;
//                    }
//                }
//            }
//        }else{
//            addSimpleError("Tiada Rekod dijumpai.");
//        }
//
//    }
    public List<Transaksi> searchByNoResit() {

        List<Transaksi> list = new ArrayList<Transaksi>();
        String resit = "";

        Session s = sessionProvider.get();

        String sqlResit = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.idDokumenKewangan = :kod";
        Query qResit = s.createQuery(sqlResit);
        qResit.setString("kod", dokumenKewangan.getIdDokumenKewangan());
        DokumenKewangan dk = (DokumenKewangan) qResit.uniqueResult();

        
        String idPguna = null;


        if (dk != null) {
            resit = dk.getIdDokumenKewangan();
            idPguna = dk.getInfoAudit().getDimasukOleh().getIdPengguna();
            senaraiCaraBayaran = dk.getSenaraiBayaran();
            dokumenKewangan = dk;
            

            
        } else {
            String sql1 = "SELECT dk FROM etanah.model.DokumenKewangan dk WHERE dk.noRujukan = :kod";
            Query q1 = s.createQuery(sql1);
            q1.setString("kod", dokumenKewangan.getIdDokumenKewangan());
            DokumenKewangan d = (DokumenKewangan) q1.uniqueResult();
            if (d != null) {
                resit = d.getIdDokumenKewangan();
                idPguna = d.getInfoAudit().getDimasukOleh().getIdPengguna();
                senaraiCaraBayaran = d.getSenaraiBayaran();
                dokumenKewangan = d;
                
            }
        }
        



        String pengguna2 = "SELECT peng FROM etanah.model.Pengguna peng WHERE peng.idPengguna = :id";
        Query q2 = s.createQuery(pengguna2);
        q2.setString("id", idPguna);
        pengguna = (Pengguna) q2.uniqueResult();

        LOG.info("id Pengguna:" +pengguna.getIdPengguna());
        LOG.info("cawangan:" + pengguna.getKodCawangan().getKod());



        String sql = "SELECT t FROM etanah.model.Transaksi t WHERE t.dokumenKewangan.idDokumenKewangan = :kod";
        Query q = s.createQuery(sql);
        q.setString("kod", resit);
        String rst = "";
        list = q.list();
        LOG.info("list.size() : " + list.size());
//        for (Transaksi tr : tList) {
//            if (tr.getDokumenKewangan() != null) {
//                if (!tr.getDokumenKewangan().getIdDokumenKewangan().equals(rst)) {
//                    LOG.info("--------------------------SINI -------------------------- : "+tr.getDokumenKewangan().getIdDokumenKewangan());
//                    list.add(tr);
//                    rst = tr.getDokumenKewangan().getIdDokumenKewangan();
//                }
//            }
//        }
        
        if(dokumenKewangan.getAkaun() !=null){
            akaun = dokumenKewangan.getAkaun();
        }
        return list;
    }

    public void searchByIdPermohonan() {
        String idPermohonan = permohonan.getIdPermohonan();
        permohonan = new Permohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            String[] b1 = {"permohonan"};
            Object[] b2 = {permohonan};
            List<Transaksi> transList = transaksiDAO.findByEqualCriterias(b1, b2, null);
            String resit = "";
            for (Transaksi tr : transList) {
                resit = tr.getDokumenKewangan().getIdDokumenKewangan();
            }
            dokumenKewangan = new DokumenKewangan();
            dokumenKewangan = dokumenKewanganDAO.findById(resit);
            searchByNoResit();
        }
    }

    public Resolution cetak() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKewDok = getContext().getRequest().getParameter("idKewDok");
        LOG.info("idKewDok : " + idKewDok);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idKewDok);
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("SPOCCetakanSemulaResit_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKewDok},
                    documentPath + path, null);
        } else {
            reportUtil.generateReport("SPOCCetakanSemulaResit.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{idKewDok},
                    documentPath + path, null);
        }

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution main() {
        dokumenKewangan = new DokumenKewangan();
        permohonan = new Permohonan();
        senaraiTransaksi = new ArrayList<Transaksi>();
        senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
        return new RedirectResolution(CetakSemulaResitActionBean.class);
    }

    public void searchPelanByName() {
    }

    public CaraBayaran getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(CaraBayaran caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public DokumenKewanganBayaran getDokumenKewanganBayaran() {
        return dokumenKewanganBayaran;
    }

    public void setDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {
        this.dokumenKewanganBayaran = dokumenKewanganBayaran;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<DokumenKewanganBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<DokumenKewanganBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public List<Akaun> getSenaraiAkauan() {
        return senaraiAkauan;
    }

    public void setSenaraiAkauan(List<Akaun> senaraiAkauan) {
        this.senaraiAkauan = senaraiAkauan;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public List<DokumenKewangan> getSenaraiDokumenKewangan() {
        return senaraiDokumenKewangan;
    }

    public void setSenaraiDokumenKewangan(List<DokumenKewangan> senaraiDokumenKewangan) {
        this.senaraiDokumenKewangan = senaraiDokumenKewangan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isFlag1() {
        return flag1;
    }

    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }
}

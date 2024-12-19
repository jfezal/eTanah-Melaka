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
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/hasil/cetak_semula")
public class CetakSemulaActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(CetakSemulaActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_resit.jsp";
    private static final String DETAIL_VIEW = "/WEB-INF/jsp/hasil/cetak_semula.jsp";

    private DokumenKewangan dokumenKewangan;
    private Pengguna pengguna;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private PenggunaDAO penggunaDAO;
    private DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    private List<DokumenKewanganBayaran> senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
    private boolean flag = false;

    private static String kodNegeri = null;


    @Inject
    public CetakSemulaActionBean(DokumenKewanganDAO dokumenKewanganDAO, PenggunaDAO penggunaDAO,
                                 DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO){
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.penggunaDAO = penggunaDAO;
        this.dokumenKewanganBayaranDAO = dokumenKewanganBayaranDAO;
    }

    @Inject
    private ReportUtil reportUtil;

    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution search(){
        LOG.info("search 1 : start");
        String idResit = dokumenKewangan.getIdDokumenKewangan();

        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeri";
        }

        dokumenKewangan = new DokumenKewangan();
        dokumenKewangan = dokumenKewanganDAO.findById(idResit);
        if(dokumenKewangan !=null){
            pengguna = new Pengguna();
            String idKaunter = null;
            if(kodNegeri.equals("melaka")){
                idKaunter = idResit.substring(4, 6);}
            if(kodNegeri.equals("negeri")){
                idKaunter = idResit.substring(9, 11);}
            String idPguna = "";
            Session s = sessionProvider.get();
            Query q = s.createQuery("SELECT p FROM etanah.model.Pengguna p WHERE p.idKaunter =:kaunter AND p.kodCawangan.kod =:caw and p.status.kod='A'");
            q.setString("kaunter", idKaunter);
            q.setString("caw", dokumenKewangan.getCawangan().getKod());

            pengguna = (Pengguna) q.uniqueResult();
//            for (Pengguna pguna : pList) {
//                idPguna = pguna.getIdPengguna();
//            }
//            pengguna = penggunaDAO.findById(idPguna);

            String [] a1 = {"dokumenKewangan"};
            Object [] a2 = {dokumenKewangan};
            senaraiCaraBayaran = dokumenKewanganBayaranDAO.findByEqualCriterias(a1, a2, null);
        }else{
            flag = true;
            addSimpleError("Tiada Rekod dijumpai.");
        }

        return new ForwardResolution (DETAIL_VIEW);
    }

    public Resolution cetak() throws FileNotFoundException{
         etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idKewDok = getContext().getRequest().getParameter("idKewDok");
        LOG.info("idKewDok : "+idKewDok);
        String documentPath = conf.getProperty("document.path");
        String path = tarikh + File.separator + String.valueOf(idKewDok);
        reportUtil.generateReport("SPOCCetakanSemulaResitPOMO_MLK.rdf",
                new String[]{"p_id_cara_bayar"},
                new String[]{idKewDok},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution main(){
        LOG.info("main : START");
        return new RedirectResolution(CetakSemulaResitActionBean.class);
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<DokumenKewanganBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(List<DokumenKewanganBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
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
@UrlBinding("/hasil/carian")
public class CarianActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(CarianActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/carian.jsp";

    private PermohonanCarian permohonanCarian;
    private CarianHakmilik carianHakmilik;
    private Permohonan permohonan;
    private DokumenKewangan dokumenKewangan;

    private PermohonanCarianDAO permohonanCarianDAO;
    private CarianHakmilikDAO carianHakmilikDAO;
    private boolean flag = false;
    private List<CarianHakmilik> senaraiHakmilik = new ArrayList<CarianHakmilik>();
    
    @Inject
    public CarianActionBean(PermohonanCarianDAO permohonanCarianDAO, CarianHakmilikDAO carianHakmilikDAO){
        this.permohonanCarianDAO = permohonanCarianDAO;
        this.carianHakmilikDAO = carianHakmilikDAO;
    }

    @Inject
    private ReportUtil reportUtil;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution (DEFAULT_VIEW);
    }

    public Resolution search(){
        if(dokumenKewangan != null){
            senaraiHakmilik = searchByResit();
        }else if (permohonan != null){
            senaraiHakmilik = searchByIDPermohonan();
        }
        flag = true;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public List<CarianHakmilik> searchByResit(){
        List<CarianHakmilik> list = new ArrayList<CarianHakmilik>();
        LOG.info("dokumenKewangan.getIdDokumenKewangan() : "+dokumenKewangan.getIdDokumenKewangan());

        String [] n1 = {"resit"};
        Object [] v1 = {dokumenKewangan};
        List<PermohonanCarian> pList = permohonanCarianDAO.findByEqualCriterias(n1, v1, null);
        LOG.info("pList.size() : "+pList.size());
        for (PermohonanCarian pc : pList) {
            String [] n2 = {"permohonanCarian"};
            Object [] v2 = {pc};
            List<CarianHakmilik> chList = carianHakmilikDAO.findByEqualCriterias(n2, v2, null);
            list.addAll(chList);
        }
        LOG.info("list.size() : "+list.size());
        return list;
    }

    public List<CarianHakmilik> searchByIDPermohonan(){
        List<CarianHakmilik> list = new ArrayList<CarianHakmilik>();

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.CarianHakmilik u where u.idPerserahan = :nama");
        q.setString("nama", permohonan.getIdPermohonan());

//        System.out.println("sql :"+q.getQueryString());
        list = q.list();
        LOG.info("list.size() : "+list.size());

        return list;
    }

    public Resolution cetakSijil() throws FileNotFoundException{
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String documentPath = File.separator + "tmp" + File.separator;
        LOG.info("idHakmilik : "+idHakmilik);
        String path = tarikh + File.separator + String.valueOf(idHakmilik);
        reportUtil.generateReport("HSLResitBayaranBelakangCekNS.rdf",
                new String[]{"p_id_cara_bayar"},
                new String[]{idHakmilik},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Resolution cetakReceipt() throws FileNotFoundException{
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik : "+idHakmilik);
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idHakmilik);
        reportUtil.generateReport("SPOCCetakanSemulaResit.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idHakmilik},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public CarianHakmilik getCarianHakmilik() {
        return carianHakmilik;
    }

    public void setCarianHakmilik(CarianHakmilik carianHakmilik) {
        this.carianHakmilik = carianHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanCarian getPermohonanCarian() {
        return permohonanCarian;
    }

    public void setPermohonanCarian(PermohonanCarian permohonanCarian) {
        this.permohonanCarian = permohonanCarian;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<CarianHakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<CarianHakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }
}

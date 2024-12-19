/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.service.KodService;
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
@Wizard(startEvents = {"showForm","filterByJabatan"})
@UrlBinding("/hasil/semak_dokumen")
public class SemakDokumenActionBean extends AbleActionBean{
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/semak_dokumen.jsp";
    private static final Logger LOG = Logger.getLogger(SemakDokumenActionBean.class);

    private KodUrusan ku;
    private UrusanDokumen urusanDokumen;
    private KodDokumen kodDokumen;
    private KodJabatan kodJabatan;
    private boolean flag = false;
    private String jabatan = "";
    private static String kodNegeri = null;

    private KodUrusanDAO kodUrusanDAO;
    private UrusanDokumenDAO urusanDokumenDAO;
    private KodDokumenDAO kodDokumenDAO;
    private KodJabatanDAO kodJabatanDAO;
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private List<UrusanDokumen> senaraiUrusanDokumen = new ArrayList<UrusanDokumen>();
    private List<KodJabatan> senaraiKodJabatan;
    @Inject
    KodService kodService;

    @Inject
    public SemakDokumenActionBean(KodUrusanDAO kodUrusanDAO, UrusanDokumenDAO urusanDokumenDAO, KodDokumenDAO kodDokumenDAO,
                                  KodJabatanDAO kodJabatanDAO){
        this.kodUrusanDAO = kodUrusanDAO;
        this.urusanDokumenDAO = urusanDokumenDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.kodJabatanDAO = kodJabatanDAO;
    }

    @Inject
    private ReportUtil reportUtil;

    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }
        filterByJabatan();
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution filterByJabatan(){
        LOG.info("kodJabatan :"+getContext().getRequest().getParameter("kodJabatan"));
        LOG.info("jabatan : "+jabatan);
        String kodJab = jabatan;
        LOG.info("kodJab :"+kodJab);
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if(kodJab == null || kodJab.equals("") || kodJab.equals("0")){
            sql = "select ku from KodUrusan ku where ku.aktif = 'Y' and ku.urusanKaunter = 'Y' order by ku.kod";
            q = s.createQuery(sql);
        }else{
            sql = "select ku from KodUrusan ku where ku.jabatan.kod = :kod and ku.aktif = 'Y' and ku.urusanKaunter = 'Y' order by ku.kod";
            q = s.createQuery(sql);
            q.setString("kod", kodJab);
        }
        senaraiKodUrusan = q.list();
        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");
    }

    public Resolution search(){
        filterByJabatan();
        LOG.info("jabatan : "+jabatan);
        LOG.info("kodUrusan : "+ku.getKod());
        String kodUrusan = ku.getKod();

        ku = new KodUrusan();
        ku = kodUrusanDAO.findById(kodUrusan);

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.UrusanDokumen u where u.kodUrusan = :kodUrusan order by u.wajib DESC, kod_dokumen ASC");
//        Query q = s.createQuery("from etanah.model.UrusanDokumen u where u.kodUrusan = :kodUrusan order by decode (u.wajib,'Y','A','B')");
        q.setString("kodUrusan", kodUrusan);

        senaraiUrusanDokumen = q.list();
        LOG.info("senaraiUrusanDokumen.size() : "+senaraiUrusanDokumen.size());


        flag = true;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution cetak() throws FileNotFoundException{
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        Date now = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        LOG.info("kodUrusan : "+kodUrusan);
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(kodUrusan);
        reportUtil.generateReport("SPOCSemakanDokumen_MLK.rdf",
                new String[]{"p_kod_urusan"},
                new String[]{kodUrusan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    public UrusanDokumen getUrusanDokumen() {
        return urusanDokumen;
    }

    public void setUrusanDokumen(UrusanDokumen urusanDokumen) {
        this.urusanDokumen = urusanDokumen;
    }

    public KodJabatan getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(KodJabatan kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<KodJabatan> getSenaraiKodJabatan() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        if (p.getPerananUtama().getKod() != null) {
            if (p.getPerananUtama().getKod().equals("2")) {
                return kodJabatanDAO.findAll();
            } else {
                return kodService.findKodJabatan(p.getPerananUtama().getKodJabatan().getKod());
            }
        }
        else return kodJabatanDAO.findAll();
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public List<UrusanDokumen> getSenaraiUrusanDokumen() {
        return senaraiUrusanDokumen;
    }

    public void setSenaraiUrusanDokumen(List<UrusanDokumen> senaraiUrusanDokumen) {
        this.senaraiUrusanDokumen = senaraiUrusanDokumen;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public void setSenaraiKodJabatan(List<KodJabatan> senaraiKodJabatan) {
        this.senaraiKodJabatan = senaraiKodJabatan;
    }
    
    
}

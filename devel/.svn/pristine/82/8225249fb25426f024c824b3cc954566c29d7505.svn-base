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
@UrlBinding("/hasil/cetak_pelan")
public class CetakPelanActionBean extends AbleActionBean{

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
    private String pembeli = "";
    private String radio;
    private String jawatan;
    private static String kodNegeri = null;
    private boolean flag1 = false;

    private DokumenKewanganDAO dokumenKewanganDAO;
    private TransaksiDAO transaksiDAO;
    private List<DokumenKewanganBayaran> senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
    private List<Akaun> senaraiAkauan = new ArrayList<Akaun>();
    private List<Transaksi> transList = new ArrayList<Transaksi>();
    private List<DokumenKewangan> senaraiDokumenKewangan = new ArrayList<DokumenKewangan>();

    private static final Logger LOG = Logger.getLogger(CetakPelanActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_resit.jsp";
    private static final String PELAN_VIEW = "/WEB-INF/jsp/hasil/cetak_semula_pelan.jsp";

    @Inject
    private etanah.Configuration conf;
    
    @Inject
    public CetakPelanActionBean(DokumenKewanganDAO dokumenKewanganDAO, TransaksiDAO transaksiDAO){
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.transaksiDAO = transaksiDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public Resolution main(){
        dokumenKewangan = new DokumenKewangan();
        permohonan = new Permohonan();
        senaraiTransaksi = new ArrayList<Transaksi>();
        senaraiCaraBayaran = new ArrayList<DokumenKewanganBayaran>();
        return new RedirectResolution(CetakSemulaResitActionBean.class);
    }

    public Resolution searchPelan(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeri";
        }

        if(dokumenKewangan != null){
            searchPelanByResit();
        }else if(!pembeli.equals("")){
            searchPelanByName();
        }
//        flag = true;
        flag1 = true;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @Inject
    private ReportUtil reportUtil;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    public void searchPelanByResit(){
        String resit = dokumenKewangan.getIdDokumenKewangan();
        LOG.info("resit : "+resit);
        LOG.info("idDokumneKewangan : "+dokumenKewangan.getIdDokumenKewangan());
        dokumenKewangan = new DokumenKewangan();
        dokumenKewangan = dokumenKewanganDAO.findById(resit);
        if (dokumenKewangan != null) {
            LOG.info("KodDokumen : " + dokumenKewangan.getKodDokumen().getKod());
            if (dokumenKewangan.getKodDokumen().getKod().equals("PLK")) {
                senaraiDokumenKewangan.add(dokumenKewangan);
            }
        }
    }

    public void searchPelanByName(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.DokumenKewangan u where u.isuKepada LIKE :isuKepada");
        q.setString("isuKepada", "%"+pembeli+"%");
        List<DokumenKewangan> dkList = q.list();
        for (DokumenKewangan dk : dkList) {
            if(dk.getKodDokumen().getKod().equals("PLK")){
                senaraiDokumenKewangan.add(dk);
            }
        }
    }

    public Resolution next(){
        LOG.info("radio : "+radio);
        dokumenKewangan = dokumenKewanganDAO.findById(radio);

        String [] n1 = {"dokumenKewangan"};
        Object [] n2 = {dokumenKewangan};
        transList = transaksiDAO.findByEqualCriterias(n1, n2, null);

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pguna = ctx.getUser();

        pengguna = new Pengguna();
        pengguna = pguna;
        String a = pengguna.getJawatan();
        jawatan = a.replace(" ", "_");

        return new ForwardResolution(PELAN_VIEW);
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
        reportUtil.generateReport("HSLResitJualanPelan_MLK.rdf",
                new String[]{"p_id_kew_dok"},
                new String[]{idKewDok},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
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

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public List<Transaksi> getTransList() {
        return transList;
    }

    public void setTransList(List<Transaksi> transList) {
        this.transList = transList;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
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

}

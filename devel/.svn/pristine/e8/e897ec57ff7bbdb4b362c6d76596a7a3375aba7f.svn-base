package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.sequence.GeneratorKumpulanAkaun;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
@UrlBinding("/hasil/create_idkumpulan")
public class CreateIdKumpulanActionBean extends AbleActionBean {
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/create_idKumpulan.jsp";
    private static final String HAKMILIK_VIEW = "/WEB-INF/jsp/hasil/create_idKumpulan_1.jsp";
    private static final String HAKMILIK_SAVE = "/WEB-INF/jsp/hasil/create_idKumpulan_2.jsp";
    private static final Logger log = Logger.getLogger(CreateIdKumpulanActionBean.class);
    private static String kodNegeri = null;
    private static String idKump = null;
    private String daerah;
    private String idHakmilikSiriDari = null;
    private String idHakmilikSiriKe = null;

    private Akaun akaun;
    private Hakmilik hakmilik;
    private KumpulanAkaun kumpulanAkaun;

    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private List<KodBandarPekanMukim> senaraiBPM = new ArrayList<KodBandarPekanMukim>();
    private boolean flag = false;
    private int bilHakmilik = 6;

    private AkaunDAO akaunDAO;
    private HakmilikDAO hakmilikDAO;
    private KumpulanAkaunDAO kumpulanAkaunDAO;

    @Inject
    public CreateIdKumpulanActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO, KumpulanAkaunDAO kumpulanAkaunDAO){
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kumpulanAkaunDAO = kumpulanAkaunDAO;
    }

    @Inject
    private KutipanHasilManager manager;

    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    GeneratorKumpulanAkaun kumpAkaunGenerator;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        daerah = caw.getKod();
        log.info("daerah : "+daerah);
        
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution save(){
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        KodCawangan caw = pengguna.getKodCawangan();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());

        idKump = kumpAkaunGenerator.generate(ctx.getKodNegeri(), pengguna.getKodCawangan(), pengguna);
        kumpulanAkaun.setIdKumpulan(idKump);
        kumpulanAkaun.setInfoAudit(ia);
        kumpulanAkaun.setCawangan(caw);
        manager.save(kumpulanAkaun);
        
        addSimpleMessage("ID Kumpulan "+idKump+" Telah Berjaya Diwujudkan");
        return new ForwardResolution(HAKMILIK_VIEW);
    }

    public Resolution saveHakmilik(){
        if((idHakmilikSiriDari != null)&&(idHakmilikSiriKe != null)){
            log.info("siri");
            details();
            saveHm();
        }else{
            log.info("xsiri");
            saveHm();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan. ID Kumpulan : "+idKump);
        return new ForwardResolution(HAKMILIK_SAVE);
    }

    public Resolution details() {
            int length = idHakmilikSiriDari.length();
            String a = "";
            String c = "";
            String b = "";
            if(length == 17){
                a = idHakmilikSiriDari.substring(9, length);
                c = idHakmilikSiriDari.substring(0, 9);
                b = idHakmilikSiriKe.substring(9, length);
            }else{
                a = idHakmilikSiriDari.substring(8, length);
                c = idHakmilikSiriDari.substring(0, 8);
                b = idHakmilikSiriKe.substring(8, length);
            }

            int a1 = Integer.parseInt(a);
            int b1 = Integer.parseInt(b);
            int series = b1 - a1 +1;
            String id = null;
            int l1 = c.length();
            int l = length - l1;
            for(int x=0;x<series;x++){
                int a2 = a1 + x;
                id = String.format("%s%0"+l+"d", c, a2);

                Hakmilik h = hakmilikDAO.findById(id);
                if(h != null){
                    senaraiHakmilik.add(h);
                }
            }

        return new ForwardResolution(HAKMILIK_SAVE);
    }

    public Resolution saveHm(){
        log.info("senaraiHakmilik.size() : "+senaraiHakmilik.size());
        KumpulanAkaun kump = kumpulanAkaunDAO.findById(idKump);
        log.info("kumpulanAkaun.getIdKumpulan() : "+kump.getIdKumpulan());
        for (Hakmilik hm : senaraiHakmilik) {
            List<Akaun> listA = new ArrayList<Akaun>();
            Session s = sessionProvider.get();
            Query q = s.createQuery("select a from etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod ='AC'");
            q.setString("idHakmilik", hm.getIdHakmilik());
            listA = q.list();

            Akaun ak = listA.get(0);
            ak.setKumpulan(kump);

            manager.saveOrUpdate(ak);
        }

        return new ForwardResolution(HAKMILIK_SAVE);
    }

    public Resolution main(){
        return new RedirectResolution(CreateIdKumpulanActionBean.class);
    }

    public Resolution search(){
//        senaraiHakmilik = manager.findAll(getContext().getRequest().getParameterMap());
        flag = true;
        return new ForwardResolution (HAKMILIK_VIEW);
    }

    public Resolution updates() {
        for (int i = 0; i < bilHakmilik; i++) {
            Hakmilik hm = new Hakmilik();
            Akaun acc = new Akaun();
            senaraiHakmilik.add(hm);
            senaraiAkaun.add(acc);
        }
        return new ForwardResolution(HAKMILIK_VIEW);
    }

    public Resolution kembali(){
        return new RedirectResolution(SearchingIDKumpulanActionBean.class);
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

    public KumpulanAkaun getKumpulanAkaun() {
        return kumpulanAkaun;
    }

    public void setKumpulanAkaun(KumpulanAkaun kumpulanAkaun) {
        this.kumpulanAkaun = kumpulanAkaun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public String getIdKump() {
        return idKump;
    }

    public void setIdKump(String idKump) {
        this.idKump = idKump;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdHakmilikSiriDari() {
        return idHakmilikSiriDari;
    }

    public void setIdHakmilikSiriDari(String idHakmilikSiriDari) {
        this.idHakmilikSiriDari = idHakmilikSiriDari;
    }

    public String getIdHakmilikSiriKe() {
        return idHakmilikSiriKe;
    }

    public void setIdHakmilikSiriKe(String idHakmilikSiriKe) {
        this.idHakmilikSiriKe = idHakmilikSiriKe;
    }
}

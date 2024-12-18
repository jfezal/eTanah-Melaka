package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KumpulanAkaunDAO;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
//@Wizard(startEvents = {"showForm", "popup", "delete", "deleteKumpulan"})
@UrlBinding("/hasil/carian_idKumpulan")
public class SearchingIDKumpulanActionBean extends AbleActionBean{
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/carian_idKumpulan.jsp";
    private static final String POPUP_VIEW = "/WEB-INF/jsp/hasil/popup_idKumpulan.jsp";
    private static final Logger log = Logger.getLogger(SearchingIDKumpulanActionBean.class);

    private Akaun akaun;
    private Hakmilik hakmilik;
    private KumpulanAkaun kumpulanAkaun;
    private KodCawangan kodCawangan;
    private static KodCawangan pgunaCaw;
    private static String selectedDaerah;
    private String daerah;
    private String idKumpulan;
    private String namaKumpulan;
    private String carian;

    private static String kodNegeri = null;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private boolean flag = false;
    private int bilHakmilik = 20;
    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Akaun> senaraiAccount = new ArrayList<Akaun>();
    private List<KumpulanAkaun> senaraiKumpulanAkaun = new ArrayList<KumpulanAkaun>();
    private static List<KumpulanAkaun> listKumpulanAkaun = new ArrayList<KumpulanAkaun>();
    private List<Akaun> senaraiKumpulan = new ArrayList<Akaun>();
    private List<Akaun> senaraiAkaun = new ArrayList<Akaun>();
    private static List<Akaun> listAkaun = new ArrayList<Akaun>();
    
    private AkaunDAO akaunDAO;
    private HakmilikDAO hakmilikDAO;
    private KumpulanAkaunDAO kumpulanAkaunDAO;
    private KodCawanganDAO kodCawanganDAO;

    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();

    private static String dummyId = null;
    private static String id = null;
    private static String caw = null;

    @Inject
    public SearchingIDKumpulanActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO, KumpulanAkaunDAO kumpulanAkaunDAO,
                                                                      KodCawanganDAO kodCawanganDAO){
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.kumpulanAkaunDAO = kumpulanAkaunDAO;
        this.kodCawanganDAO = kodCawanganDAO;
    }

    @Inject
    private KutipanHasilManager manager;
    
    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT d FROM etanah.model.KodDaerah d WHERE d.kod NOT IN ('00') order by d.kod asc");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        daerah = pengguna.getKodCawangan().getDaerah().getKod();

        senaraiDaerah = q.list();
        
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public Resolution search(){        
        if(hakmilik != null){
            searchingIdHakmilik();
            flag1 = false;
            flag2 = true;
        }else if(akaun != null){
            searchingNoAkaun();
            flag1 = false;
            flag2 = true;
        }else{
            log.info("masuk else");
//            dummyId = kumpulanAkaun.getIdKumpulan();
            searchingIdKumpulan(getContext().getRequest().getParameterMap());
            flag1 = true;
            flag2 = false;
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();

        Pengguna pengguna = ctx.getUser();
        pgunaCaw = pengguna.getKodCawangan();
        showForm();
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    
    public void searchingIdKumpulan(Map<String, String[]> param){
        Session s = sessionProvider.get();
        String query = "SELECT ka FROM etanah.model.KumpulanAkaun ka WHERE ka.catatan IS NOT NULL ";

        if (isNotBlank(param.get("idKumpulan"))) {
            query += "AND ka.idKumpulan = :id ";
        }

        if (isNotBlank(param.get("namaKumpulan"))) {
            query += "AND ka.catatan LIKE :nama ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND ka.cawangan.kod = :caw ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idKumpulan"))) {
            q.setString("id", param.get("idKumpulan")[0]);
        }

        if (isNotBlank(param.get("namaKumpulan"))) {
            q.setString("nama", "%"+param.get("namaKumpulan")[0]+"%");
        }

        if (isNotBlank(param.get("daerah"))) {
            q.setString("caw", param.get("daerah")[0]);
        }

        senaraiKumpulanAkaun = q.list();
        listKumpulanAkaun = senaraiKumpulanAkaun;
    }

    public void searchingIdHakmilik(){
        log.info("hakmilik.getIdHakmilik() : "+hakmilik.getIdHakmilik());
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.Akaun a where a.hakmilik.idHakmilik = :idHakmilik AND a.kumpulan.idKumpulan IS NOT NULL");
        q.setString("idHakmilik", hakmilik.getIdHakmilik());
        senaraiKumpulan = q.list();
    }

    public void searchingNoAkaun(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.Akaun a where a.noAkaun = :noAkaun AND a.kumpulan.idKumpulan IS NOT NULL");
        q.setString("noAkaun", akaun.getNoAkaun());
        senaraiKumpulan = q.list();
    }

    public Resolution papar() {
        log.info("pgunaCaw : "+pgunaCaw.getKod());
        String popup = getContext().getRequest().getParameter("popup");
        Date now = new Date();
        String idx =  null;

        InfoAudit ia = new InfoAudit();

        if (StringUtils.isNotEmpty(popup)) {
//            idHakmilik = getContext().getRequest().getParameter("idHakmilik");
            log.info("isNotEmpty");
            idx = getContext().getRequest().getParameter("idKumpulan");
            log.info("idx : " + idx);
        }
        log.info("idx : " + idx);
        return new JSP(POPUP_VIEW);
    }

    public Resolution popup() {
        String idx = getContext().getRequest().getParameter("idKumpulan");
//        String popup = getContext().getRequest().getParameter("popup");
        log.info("pgunaCaw : "+pgunaCaw.getKod());

        kumpulanAkaun = kumpulanAkaunDAO.findById(idx);
        dummyId = kumpulanAkaun.getIdKumpulan();
        id = kumpulanAkaun.getIdKumpulan();
        caw = kumpulanAkaun.getCawangan().getDaerah().getNama();
        selectedDaerah = kumpulanAkaun.getCawangan().getDaerah().getKod();
        String[] n1 = {"kumpulan"};
        Object[] v1 = {kumpulanAkaun};
        senaraiAkaun = akaunDAO.findByEqualCriterias(n1, v1, null);
        listAkaun = senaraiAkaun;

        return new ForwardResolution(POPUP_VIEW);
//        return new JSP(POPUP_VIEW).addParameter("popup", "true");
    }

    public Resolution delete(){
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        log.info("idHakmilik : "+idHakmilik);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT a from etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC'");
        q.setString("idHakmilik", idHakmilik);
        List<Akaun> list = q.list();
        log.info("list.size() : "+list.size());
        Akaun a = new Akaun();
        if(list.size() > 0){
            a = list.get(0);
        }
        log.info("a.getNoAkaun() : "+a.getNoAkaun());
        a.setKumpulan(null);
        manager.saveOrUpdate(a);

        return new JSP("hasil/carian_idKumpulan.jsp").addParameter("popup", "true");
    }

    public Resolution deletePopup(){
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        log.info("idHakmilik : "+idHakmilik);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT a from etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik AND a.kodAkaun.kod = 'AC'");
        q.setString("idHakmilik", idHakmilik);
        List<Akaun> list = q.list();
        log.info("list.size() : "+list.size());
        Akaun a = new Akaun();
        if(list.size() > 0){
            a = list.get(0);
        }
        log.info("a.getNoAkaun() : "+a.getNoAkaun());
        a.setKumpulan(null);
        manager.saveOrUpdate(a);

        List<Akaun> list3 = new ArrayList<Akaun>();
        if(StringUtils.isNotBlank(idHakmilik)){
            for (Akaun h : listAkaun) {
                if(!StringUtils.equals(idHakmilik, h.getHakmilik().getIdHakmilik())){
                    list3.add(h);
                }
            }
        }else{
            list3 = listAkaun;
        }
        listAkaun = list3;
        senaraiAkaun = listAkaun;
        reloadPage();

        return new ForwardResolution(POPUP_VIEW).addParameter("popup", "true");
    }

    public Resolution reloadPage(){
        log.info("reload");
//        String idx = getContext().getRequest().getParameter("cari");
//        String popup = getContext().getRequest().getParameter("popup");
        log.info("pgunaCaw : "+pgunaCaw.getKod());

        kumpulanAkaun = kumpulanAkaunDAO.findById(dummyId);
        dummyId = kumpulanAkaun.getIdKumpulan();
        id = kumpulanAkaun.getIdKumpulan();
        caw = kumpulanAkaun.getCawangan().getDaerah().getNama();
        selectedDaerah = kumpulanAkaun.getCawangan().getDaerah().getKod();
        String[] n1 = {"kumpulan"};
        Object[] v1 = {kumpulanAkaun};
        senaraiAkaun = akaunDAO.findByEqualCriterias(n1, v1, null);
        listAkaun = senaraiAkaun;

        return new ForwardResolution(POPUP_VIEW);
//        return new JSP("hasil/popup_idKumpulan").addParameter("popup", "true");
//        return new ForwardResolution(POPUP_VIEW).addParameter("popup", "true");
    }

    public Resolution deleteKumpulan(){
        String idKumpulan = getContext().getRequest().getParameter("idKumpulan");
        log.info("idKumpulan : "+idKumpulan);

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT a from etanah.model.Akaun a WHERE a.kumpulan.idKumpulan = :idKumpulan");
        q.setString("idKumpulan", idKumpulan);

        List<Akaun> list = q.list();
        log.info("list.size() : "+list.size());
        for (Akaun ak : list) {
            log.info("ak.getNoAkaun() : "+ak.getNoAkaun());
            ak.setKumpulan(null);
            manager.saveOrUpdate(ak);
        }

        KumpulanAkaun kumpAkaun = kumpulanAkaunDAO.findById(idKumpulan);
        manager.delete(kumpAkaun);

        List<KumpulanAkaun> list3 = new ArrayList<KumpulanAkaun>();
        if(StringUtils.isNotBlank(idKumpulan)){
            for (KumpulanAkaun h : listKumpulanAkaun) {
                if(!StringUtils.equals(idKumpulan, h.getIdKumpulan())){
                    list3.add(h);
                }
            }
        }else{
            list3 = listKumpulanAkaun;
        }
        listKumpulanAkaun = list3;
        senaraiKumpulanAkaun = listKumpulanAkaun;
        flag1 = true;
        return new ForwardResolution(DEFAULT_VIEW).addParameter("popup", "true");
    }

    public Resolution simpan() {
        log.info("id : "+id);
        log.info("senaraiHakmilik.size() : "+senaraiHakmilik.size());

        KumpulanAkaun kump = kumpulanAkaunDAO.findById(id);
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
        flag = true;
        addSimpleMessage("Maklumat telah berjaya disimpan. ID Kumpulan : "+kump.getIdKumpulan());

        kumpulanAkaun = kumpulanAkaunDAO.findById(dummyId);
        id = kumpulanAkaun.getIdKumpulan();
        caw = kumpulanAkaun.getCawangan().getDaerah().getNama();
        String[] n1 = {"kumpulan"};
        Object[] v1 = {kumpulanAkaun};
        senaraiAkaun = akaunDAO.findByEqualCriterias(n1, v1, null);
        listAkaun = senaraiAkaun;

        senaraiHakmilik = new ArrayList<Hakmilik>();
        senaraiAccount = new ArrayList<Akaun>();

        return new ForwardResolution(POPUP_VIEW);
//        return new JSP(POPUP_VIEW).addParameter("popup", "true");
    }

    public Resolution tambahKumpulan(){
        return new RedirectResolution(CreateIdKumpulanActionBean.class);
    }
    
    public Resolution doCheckKumpulan() {
        log.info("*****AjaxValidateIdHakmilik.doCheckHakmilik:hakmilik :" + getContext().getRequest().getParameter("idHakmilik"));
        String results = "0";
        Hakmilik h = hakmilikDAO.findById((getContext().getRequest().getParameter("idHakmilik")).trim());
        if (h != null) {
            Akaun ac = h.getAkaunCukai();
            results = ac.getNoAkaun();
            
            log.info("ac.getNoAkaun() : "+ac.getNoAkaun());
            if(ac.getKumpulan() != null){
                results = ac.getKumpulan().getCatatan();
            }
        }
        log.info("results : "+results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution back(){
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

    public List<KodDaerah> getSenaraiDaerah() {
        return senaraiDaerah;
    }

    public void setSenaraiDaerah(List<KodDaerah> senaraiDaerah) {
        this.senaraiDaerah = senaraiDaerah;
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

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public List<KumpulanAkaun> getSenaraiKumpulanAkaun() {
        return senaraiKumpulanAkaun;
    }

    public void setSenaraiKumpulanAkaun(List<KumpulanAkaun> senaraiKumpulanAkaun) {
        this.senaraiKumpulanAkaun = senaraiKumpulanAkaun;
    }

    public List<Akaun> getSenaraiKumpulan() {
        return senaraiKumpulan;
    }

    public void setSenaraiKumpulan(List<Akaun> senaraiKumpulan) {
        this.senaraiKumpulan = senaraiKumpulan;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<KumpulanAkaun> getListKumpulanAkaun() {
        return listKumpulanAkaun;
    }

    public void setListKumpulanAkaun(List<KumpulanAkaun> listKumpulanAkaun) {
        this.listKumpulanAkaun = listKumpulanAkaun;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<Akaun> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public String getDummyId() {
        return dummyId;
    }

    public void setDummyId(String dummyId) {
        this.dummyId = dummyId;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(int bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public List<Akaun> getSenaraiAccount() {
        return senaraiAccount;
    }

    public void setSenaraiAccount(List<Akaun> senaraiAccount) {
        this.senaraiAccount = senaraiAccount;
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

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setIdKumpulan(String idKumpulan) {
        this.idKumpulan = idKumpulan;
    }

    public String getNamaKumpulan() {
        return namaKumpulan;
    }

    public void setNamaKumpulan(String namaKumpulan) {
        this.namaKumpulan = namaKumpulan;
    }

    public KodCawangan getPgunaCaw() {
        return pgunaCaw;
    }

    public void setPgunaCaw(KodCawangan pgunaCaw) {
        this.pgunaCaw = pgunaCaw;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getCarian() {
        return carian;
    }

    public void setCarian(String carian) {
        this.carian = carian;
    }

    public String getSelectedDaerah() {
        return selectedDaerah;
    }

    public void setSelectedDaerah(String selectedDaerah) {
        this.selectedDaerah = selectedDaerah;
    }
}

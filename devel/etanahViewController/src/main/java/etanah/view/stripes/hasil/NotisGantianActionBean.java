/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.HakmilikAlamatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.Query;


/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/notis_gantian")
public class NotisGantianActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NotisGantianActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private String noRujDasar;
    private String tarikhRujDasar;
    private String tarikhWarta;
    private String tarikhPerintah;
    private String radioButton;
    private static String idNotis;
    private String sebab;
    private String idPermohonan;
    private static boolean flag = false;
    private static String idHakmilik;
    private String nama;
    private String jawatan;

    private List<DasarTuntutanCukaiHakmilik> hakmilikList = new ArrayList<DasarTuntutanCukaiHakmilik>();
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<DasarTuntutanNotis> senaraiNotis;
    private List<KodPeranan> senaraiJawatan = new ArrayList<KodPeranan>();
    private List<Pengguna> senaraiPengguna = new ArrayList<Pengguna>();

    private DasarTuntutanCukai dasarTuntutanCukai;
    private DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik;
    private DasarTuntutanNotis dasarTuntutanNotis ;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private Permohonan permohonan;
    private HakmilikAlamat hakmilikAlamat;
    private KodPeranan kodPeranan;

    private DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    private DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    private DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    private HakmilikDAO hakmilikDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private PermohonanDAO permohonanDAO;
    private HakmilikAlamatDAO hakmilikAlamatDAO;
    private PenggunaDAO penggunaDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Inject
    public NotisGantianActionBean(DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO, DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO,
                                  DasarTuntutanNotisDAO dasarTuntutanNotisDAO, HakmilikDAO hakmilikDAO, HakmilikPermohonanDAO hakmilikPermohonanDAO,
                                  PermohonanDAO permohonanDAO, HakmilikAlamatDAO hakmilikAlamatDAO, PenggunaDAO penggunaDAO) {
        this.dasarTuntutanCukaiDAO = dasarTuntutanCukaiDAO;
        this.dasarTuntutanCukaiHakmilikDAO = dasarTuntutanCukaiHakmilikDAO;
        this.dasarTuntutanNotisDAO = dasarTuntutanNotisDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.permohonanDAO = permohonanDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.hakmilikAlamatDAO = hakmilikAlamatDAO;
        this.penggunaDAO = penggunaDAO;
    }

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    NotisPeringatan6AManager manager;

    public Resolution showForm() {
        return new JSP("hasil/notis_gantian.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        LOG.info("showForm1");
        flag = true;
        return new JSP("hasil/notis_gantian1.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");

        idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        List<HakmilikPermohonan> senaraiMohonHakmilik = new ArrayList<HakmilikPermohonan>();

        String [] n1 = {"permohonan"};
        Object [] v1 = {getPermohonan()};
        senaraiMohonHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(n1, v1, null);
        
        senaraiNotis = new ArrayList<DasarTuntutanNotis>();
        for (HakmilikPermohonan hp : senaraiMohonHakmilik) {
            String [] n2 = {"hakmilik"};
            Object [] v2 = {hp.getHakmilik()};
            List<DasarTuntutanCukaiHakmilik> dcthList = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(n2, v2, null);
            hakmilikList.addAll(dcthList);
        }
        List<DasarTuntutanNotis> notisList1 = new ArrayList<DasarTuntutanNotis>();
        for (DasarTuntutanCukaiHakmilik dcth : hakmilikList) {
            String [] n3 = {"hakmilik"};
            Object [] v3 = {dcth};
            List<DasarTuntutanNotis> notisList = dasarTuntutanNotisDAO.findByEqualCriterias(n3, v3, null);
            notisList1.addAll(notisList);
        }

        for (DasarTuntutanNotis dtn : notisList1) {
            if(dtn.getNotis().getKod().equals("NG")){
                senaraiNotis.add(dtn);
            }
        }
        hakmilik = senaraiMohonHakmilik.get(0).getHakmilik();
    }
    
    public Resolution popup() {
        LOG.info("idHakmilik : "+idHakmilik+" idNotis : "+idNotis);
//        idHakmilik =getContext().getRequest().getParameter("idHakmilik");
//        idNotis =getContext().getRequest().getParameter("idNotis");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        LOG.info("hakmilik.getCawangan() : "+hakmilik.getCawangan().getKod());
        dasarTuntutanNotis = dasarTuntutanNotisDAO.findById(Long.parseLong(idNotis));
        if(dasarTuntutanNotis.getTarikhDihantar() != null){
            tarikhWarta = sdf.format(dasarTuntutanNotis.getTarikhDihantar());
        }
        if(dasarTuntutanNotis.getTarikhPerintah() != null){
            tarikhPerintah = sdf.format(dasarTuntutanNotis.getTarikhPerintah());
            LOG.info("tarikhPerintah : "+tarikhPerintah);
        }
        if(dasarTuntutanNotis.getDihantarOleh() != null){
            nama = dasarTuntutanNotis.getDihantarOleh().getIdPengguna();
            jawatan = dasarTuntutanNotis.getDihantarOleh().getPerananUtama().getKod();
            Session s = sessionProvider.get();
                String sql = "SELECT p from etanah.model.Pengguna p where p.perananUtama.kod = :kod AND p.kodCawangan.kod =:kodCaw AND p.status.kod='A'";
                Query q = s.createQuery(sql);
                q.setString("kod", jawatan);
                q.setString("kodCaw", hakmilik.getCawangan().getKod());
            senaraiPengguna = q.list();
        }
        getSenaraiKodJawatan();
        
        return new JSP("hasil/notis_gantian_edit.jsp").addParameter("popup", "true");
    }

     public Resolution popup1() {
         flag = true;
         popup();
        return new JSP("hasil/notis_gantian_edit.jsp").addParameter("popup", "true");
     }

    public void getSenaraiKodJawatan(){
        KodCawangan kodCaw = hakmilik.getCawangan();
        String query = "SELECT kp FROM etanah.model.KodPeranan kp WHERE kp.kod = :kod1 OR kp.kod = :kod2 OR kp.kod = :kod3";
        Query q = sessionProvider.get().createQuery(query);
            q.setString("kod1", "9");
            q.setString("kod2", "10");
            q.setString("kod3", "PTD");
        senaraiJawatan = q.list();
        LOG.info("senaraiJawatan.size() : "+senaraiJawatan.size());
    }

    public Resolution filterByJawatan(){
        LOG.info("kodJawatan :"+getContext().getRequest().getParameter("kodJawatan"));
        LOG.info("hakmilik.getIdHakmilik() :"+hakmilik.getIdHakmilik());
//        idNotis  = String.valueOf(senaraiNotis.get(0).getIdNotis());
        LOG.info("idNotis : "+idNotis);
        String kodJawatan = getContext().getRequest().getParameter("kodJawatan");
        if(jawatan !=null){
            kodJawatan = jawatan;
        }
        LOG.info("kodJab :"+kodJawatan);
        Session s = sessionProvider.get();
            String sql = "SELECT p from etanah.model.Pengguna p where p.perananUtama.kod = :kod AND p.kodCawangan.kod =:kodCaw AND p.status.kod='A'";
            Query q = s.createQuery(sql);
            q.setString("kod", kodJawatan);
            q.setString("kodCaw", hakmilik.getCawangan().getKod());
//            q.setString("kodCaw", hakmilik.getCawangan().getKod());
        senaraiPengguna = q.list();

        popup();
        LOG.info("senaraiPengguna.siza() : "+senaraiPengguna.size());
        return new JSP("hasil/notis_gantian_edit.jsp").addParameter("popup", "true");
    }

    public Resolution save() throws ParseException{
        LOG.info("idNotis : "+idNotis);
        DasarTuntutanNotis dtn = dasarTuntutanNotisDAO.findById(Long.parseLong(idNotis));

        dtn.setTempatHantar1(dasarTuntutanNotis.getTempatHantar1());
        dtn.setTempatHantar2(dasarTuntutanNotis.getTempatHantar2());
        dtn.setTempatHantar3(dasarTuntutanNotis.getTempatHantar3());
        dtn.setTempatHantar4(dasarTuntutanNotis.getTempatHantar4());
//        dtn.setTempatHantar5(dasarTuntutanNotis.getTempatHantar5());
//        dtn.setTempatHantar6(dasarTuntutanNotis.getTempatHantar6());
//        if(flag){
            if(dasarTuntutanNotis.getNoRujukan() != null){
                dtn.setNoRujukan(dasarTuntutanNotis.getNoRujukan());}
            if(tarikhWarta != null){
                dtn.setTarikhDihantar(sdf.parse(tarikhWarta));}
//        }
        if(tarikhPerintah != null){
            dtn.setTarikhPerintah(sdf.parse(tarikhPerintah));
        }
        if(nama != null){
            dtn.setDihantarOleh(penggunaDAO.findById(nama));
        }

        manager.updateNotis(dtn);
        popup();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("hasil/notis_gantian_edit.jsp").addParameter("popup", "true");
//        return showForm();
    }

    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilik() {
        return dasarTuntutanCukaiHakmilik;
    }

    public void setDasarTuntutanCukaiHakmilik(DasarTuntutanCukaiHakmilik dasarTuntutanCukaiHakmilik) {
        this.dasarTuntutanCukaiHakmilik = dasarTuntutanCukaiHakmilik;
    }

    public DasarTuntutanNotis getDasarTuntutanNotis() {
        return dasarTuntutanNotis;
    }

    public void setDasarTuntutanNotis(DasarTuntutanNotis dasarTuntutanNotis) {
        this.dasarTuntutanNotis = dasarTuntutanNotis;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<DasarTuntutanCukaiHakmilik> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<DasarTuntutanCukaiHakmilik> hakmilikList) {
        this.hakmilikList = hakmilikList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikAlamat getHakmilikAlamat() {
        return hakmilikAlamat;
    }

    public void setHakmilikAlamat(HakmilikAlamat hakmilikAlamat) {
        this.hakmilikAlamat = hakmilikAlamat;
    }

    public List<DasarTuntutanNotis> getSenaraiNotis() {
        return senaraiNotis;
    }

    public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
        this.senaraiNotis = senaraiNotis;
    }

    public String getNoRujDasar() {
        return noRujDasar;
    }

    public void setNoRujDasar(String noRujDasar) {
        this.noRujDasar = noRujDasar;
    }

    public String getTarikhRujDasar() {
        return tarikhRujDasar;
    }

    public void setTarikhRujDasar(String tarikhRujDasar) {
        this.tarikhRujDasar = tarikhRujDasar;
    }

    public String getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(String radioButton) {
        this.radioButton = radioButton;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public List<KodPeranan> getSenaraiJawatan() {
        return senaraiJawatan;
    }

    public void setSenaraiJawatan(List<KodPeranan> senaraiJawatan) {
        this.senaraiJawatan = senaraiJawatan;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getTarikhPerintah() {
        return tarikhPerintah;
    }

    public void setTarikhPerintah(String tarikhPerintah) {
        this.tarikhPerintah = tarikhPerintah;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
}

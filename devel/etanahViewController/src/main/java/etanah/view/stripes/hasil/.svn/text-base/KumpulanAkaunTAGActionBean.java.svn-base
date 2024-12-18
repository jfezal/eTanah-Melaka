/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.hasil.TagAkaunAhliDAO;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.Pengguna;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/kumpulan_akaunTAG")
public class KumpulanAkaunTAGActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(KumpulanAkaunTAGActionBean.class);
    private static final String CARIAN_VIEW = "/WEB-INF/jsp/hasil/tag_akaun_carian.jsp";

    private String negeri;
    private String carian;
    private String idHakmilik;
    private String noAkaun;
    private String idKumpulan;
    private String namaKumpulan;
    private String cawangan;
    
    private boolean showPanelGroup = false;
    private boolean showPanelSingle = false;

    private Pengguna peng;
    
    private List<TagAkaunAhli> senaraiAhli;
    private List<TagAkaun> senaraiKumpulan;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    TagAkaunDAO tagAkaunDAO;
    @Inject
    TagAkaunAhliDAO tagAkaunAhliDAO;
    @Inject
    KutipanHasilManager manager;

    @DefaultHandler
    public Resolution showForm(){
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if("04".equals(conf.getProperty("kodNegeri"))){
            negeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            negeri = "sembilan";
        }
        cawangan = peng.getKodCawangan().getKod();
        LOG.info("negeri :"+negeri);
        return new ForwardResolution(CARIAN_VIEW);
    }

    public Resolution carianKumpulan(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            negeri = "melaka";
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            negeri = "sembilan";
        }
        LOG.info("carian :"+carian);
        LOG.info("idHakmilik :"+idHakmilik);
        LOG.info("noAkaun :"+noAkaun);
        LOG.info("idKumpulan :"+idKumpulan);
        LOG.info("namaKumpulan :"+namaKumpulan);
        LOG.info("cawangan :"+cawangan);
        if(carian.equals("KMP")){
            searchingIdKumpulan();
            showPanelGroup = true;
            showPanelSingle = false;
        }else if(carian.equals("HMK")){
            searchingIdHakmilik();
            showPanelGroup = false;
            showPanelSingle = true;
        }else if(carian.equals("AKN")){
            searchingNoAkaun();
            showPanelGroup = false;
            showPanelSingle = true;
        }else{
            addSimpleError("Sila Pilih Jenis Carian Terlebih Dahulu.");
        }

        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution(CARIAN_VIEW);
    }

    public void searchingIdKumpulan(){
        Session s = sessionProvider.get();
        String sql = "SELECT ta FROM etanah.model.hasil.TagAkaun ta WHERE 1=1 ";
        if (StringUtils.isNotBlank(idKumpulan)) {
            sql += "AND ta.idTag like :id ";
        }
        if (StringUtils.isNotBlank(namaKumpulan)) {
            sql += "AND ta.nama like :nama ";
        }
        if (StringUtils.isNotBlank(cawangan)) {
            sql += "AND ta.cawangan.kod = :kodCaw ";
        }
        LOG.info("sql :"+sql);
        Query query = s.createQuery(sql);

        if (StringUtils.isNotBlank(idKumpulan)) {
            query.setString("id", "%"+idKumpulan+"%");
        }
        if (StringUtils.isNotBlank(namaKumpulan)) {
            query.setString("nama", "%"+namaKumpulan+"%");
        }
        if (StringUtils.isNotBlank(cawangan)) {
            query.setString("kodCaw", cawangan);
        }
        senaraiKumpulan = query.list();
        LOG.debug("senaraiKumpulan.size() :"+senaraiKumpulan.size());
    }

    public void searchingIdHakmilik(){
        LOG.info("idHakmilik : "+idHakmilik);
        Session s = sessionProvider.get();
        Query q = s.createQuery("select taa from etanah.model.hasil.TagAkaunAhli taa where taa.akaun.hakmilik.idHakmilik like :idHakmilik");
        q.setString("idHakmilik", "%"+idHakmilik+"%");
        senaraiAhli = q.list();
        LOG.debug("senaraiAhli.size() :"+senaraiAhli.size());
    }

    public void searchingNoAkaun(){
        LOG.info("noAkaun : "+noAkaun);
        Session s = sessionProvider.get();
        Query q = s.createQuery("select taa from etanah.model.hasil.TagAkaunAhli taa where taa.akaun.noAkaun like :noAkaun");
        q.setString("noAkaun", "%"+noAkaun+"%");
        senaraiAhli = q.list();
        LOG.debug("senaraiAhli.size() :"+senaraiAhli.size());
    }

    public Resolution hapusKumpulan(){
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        String idTag = getContext().getRequest().getParameter("idKumpulanAkaun");
        TagAkaun tagKumpAkaun = new TagAkaun();
        if(idTag != null){
            tagKumpAkaun = tagAkaunDAO.findById(idTag);
            LOG.info("idTag :"+tagKumpAkaun.getIdTag());
            try{
                manager.deleteKumpulanAkaunTag(tagKumpAkaun, tagKumpAkaun.getSenaraiAhli());
                addSimpleMessage("Kumpulan BERJAYA dihapuskan.");
            }catch(Exception ex){
                LOG.error("Data Tidak berjaya dihapuskan. :"+ex);
                ex.printStackTrace(); // for development only
                addSimpleError("Kumpulan TIDAK berjaya dihapuskan.");
            }
        }else{
            addSimpleError("Kumpulan TIDAK dijumpai.");
        }
        carianKumpulan();
        return new ForwardResolution(CARIAN_VIEW);
    }

    public Resolution hapusAhli(){
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        long idAhli = Long.parseLong(getContext().getRequest().getParameter("idAhli"));
        String namaKumpulan = null;
        TagAkaunAhli tagAhli = new TagAkaunAhli();
        try{
            tagAhli = tagAkaunAhliDAO.findById(idAhli);
            namaKumpulan = tagAhli.getTagAkaun().getNama();
            LOG.info("idAhli :"+tagAhli.getIdAhli());
            manager.deleteAkaunAhli(tagAhli);
            LOG.debug("Hakmilik BERJAYA dihapuskan dari kumpulan :"+namaKumpulan+" .");
            addSimpleMessage("Hakmilik BERJAYA dihapuskan dari kumpulan :"+namaKumpulan+" .");
        }catch(Exception ex){
            LOG.error("Hakmilik TIDAK berjaya dihapuskan dari kumpulan :"+namaKumpulan+" . "+ex);
            addSimpleError("Hakmilik TIDAK berjaya dihapuskan.");
            ex.printStackTrace(); // for development only
        }
        carianKumpulan();
        return new ForwardResolution(CARIAN_VIEW);
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getCarian() {
        return carian;
    }

    public void setCarian(String carian) {
        this.carian = carian;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public boolean isShowPanelGroup() {
        return showPanelGroup;
    }

    public void setShowPanelGroup(boolean showPanelGroup) {
        this.showPanelGroup = showPanelGroup;
    }

    public boolean isShowPanelSingle() {
        return showPanelSingle;
    }

    public void setShowPanelSingle(boolean showPanelSingle) {
        this.showPanelSingle = showPanelSingle;
    }

    public List<TagAkaunAhli> getSenaraiAhli() {
        return senaraiAhli;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public void setSenaraiAhli(List<TagAkaunAhli> senaraiAhli) {
        this.senaraiAhli = senaraiAhli;
    }

    public List<TagAkaun> getSenaraiKumpulan() {
        return senaraiKumpulan;
    }

    public void setSenaraiKumpulan(List<TagAkaun> senaraiKumpulan) {
        this.senaraiKumpulan = senaraiKumpulan;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.service.HakmilikService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/pertanyaan_bil")
public class PertanyaanBilCukaiActionBean extends AbleActionBean {
    
    private static final Logger LOG = Logger.getLogger(PertanyaanBilCukaiActionBean.class);
    
    private String kodNegeri;
    private String idHakmilikSearch;
    private String noAkaunSearch;
    private List<Akaun> senaraiAkaun;
    private List<Hakmilik> senaraiHakmilik;
    private boolean aktifPanel = false;
    
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dtchDAO;
    @Inject
    HakmilikService hakmilikService;
    
    @DefaultHandler
    public Resolution showForm(){
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "sembilan";
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_bil_cukai.jsp");
    }
    
    public Resolution Carian(){
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "sembilan";
        }
        senaraiHakmilik = new ArrayList<Hakmilik>();
        senaraiAkaun = new ArrayList<Akaun>();
        LOG.info("(Carian) idHakmilikSearch :"+idHakmilikSearch);
        LOG.info("(Carian) noAkaunSearch :"+noAkaunSearch);
        Session s = sessionProvider.get();
        Query q = null;
        String sql = "select ak from etanah.model.Akaun ak where 1=1 ";
        if(idHakmilikSearch != null && noAkaunSearch == null){
            sql += "and ak.hakmilik.idHakmilik like :idHakmilik ";
            LOG.info(sql);
            q = s.createQuery(sql);
            q.setString("idHakmilik", "%"+idHakmilikSearch+"%");
        }
        if(idHakmilikSearch == null && noAkaunSearch != null){
            sql += "and ak.noAkaun like :noAkaun ";
            LOG.info(sql);
            q = s.createQuery(sql);
            q.setString("noAkaun", "%"+noAkaunSearch+"%");
        }
        if(idHakmilikSearch != null && noAkaunSearch != null){
            sql += "and ak.noAkaun like :noAkaun and ak.hakmilik.idHakmilik like :idHakmilik ";
            LOG.info(sql);
            q = s.createQuery(sql);
            q.setString("idHakmilik", "%"+idHakmilikSearch+"%");
            q.setString("noAkaun", "%"+noAkaunSearch+"%");
        }
        senaraiAkaun = q.list();
        LOG.info("(Carian) senaraiAkaun :"+senaraiAkaun.size());
        if(senaraiAkaun.size() < 1){
            addSimpleError("Tiada data dijumpai. Sila cuba sekali lagi.");
        }else
            aktifPanel = true;

        List<DasarTuntutanCukaiHakmilik> senaraiDtch = new ArrayList<DasarTuntutanCukaiHakmilik>();
        for (Akaun akaun : senaraiAkaun) {
            String[] name = {"hakmilik"};
            Object[] value = {akaun.getHakmilik()};
            senaraiDtch = dtchDAO.findByEqualCriterias(name, value, null);
            for (DasarTuntutanCukaiHakmilik dtch : senaraiDtch) {
                if(dtch.getStatus().getKod().equals("ST")){
                    for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                        if(dtn.getNotis().getKod().equals("N8A")){
                            senaraiHakmilik.add(akaun.getHakmilik());
                        }
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_bil_cukai.jsp");
    }
    
    public Resolution bilCetakPenyataRun() {
        Hakmilik hakmilik = new Hakmilik();
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        LOG.info("bilCetakPenyata Start");
        String idKew = getContext().getRequest().getParameter("idHakmilik");
        Pengguna pguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        hakmilik = hakmilikDAO.findById(idKew);
        if (hakmilik != null) {
            Akaun ak = hakmilik.getAkaunCukai();
            ak.setBilCetakPenyata(ak.getBilCetakPenyata() + 1);
            ak.setInfoAudit(ia);
            try{
                hakmilikService.updateBilanganCetak(ak);
                LOG.debug("Berjaya.");
            }catch(Exception ex){
                LOG.error(ex);
                ex.printStackTrace(); // for development only
            }            
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/pertanyaan_bil_cukai.jsp");
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getIdHakmilikSearch() {
        return idHakmilikSearch;
    }

    public void setIdHakmilikSearch(String idHakmilikSearch) {
        this.idHakmilikSearch = idHakmilikSearch;
    }

    public String getNoAkaunSearch() {
        return noAkaunSearch;
    }

    public void setNoAkaunSearch(String noAkaunSearch) {
        this.noAkaunSearch = noAkaunSearch;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public boolean isAktifPanel() {
        return aktifPanel;
    }

    public void setAktifPanel(boolean aktifPanel) {
        this.aktifPanel = aktifPanel;
    }

    }

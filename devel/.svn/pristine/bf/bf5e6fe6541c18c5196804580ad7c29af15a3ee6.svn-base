/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodTanah;
import etanah.model.Pengguna;
import etanah.model.SenaraiKodRujukan;
import etanah.model.SiriNoPt;
import etanah.service.KodService;
import etanah.service.PembangunanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author NageswaraRao
 */
@HttpCache(allow = false)
@UrlBinding("/utility_kod/siriNoPt")
public class SiriNoPtActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(SiriNoPtActionBean.class);
    
    private String daerah;
    private Pengguna pguna;
    private String mukim;
    //private List<SiriNoPt> listSiriNoPt;    
    private List<Hakmilik> listhakmilik;
    private List<KodBandarPekanMukim> senaraiMukim;
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PembangunanService devService;
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/pembangunan/utiliti/listSiriNoPt.jsp");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       daerah = (pengguna.getKodCawangan().getDaerah().getKod())+" - "+(pengguna.getKodCawangan().getDaerah().getNama());
       senaraiMukim = new ArrayList<KodBandarPekanMukim>();
       senaraiMukim = devService.searchKodBPM(pengguna.getKodCawangan().getDaerah().getKod());        
       LOG.info("--------daerah--------------:"+daerah);
       LOG.info("--------senaraiMukim--------:"+senaraiMukim);
    }
    
    
    public Resolution search(){
       Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       daerah = (pengguna.getKodCawangan().getDaerah().getKod())+" - "+(pengguna.getKodCawangan().getDaerah().getNama());
       senaraiMukim = new ArrayList<KodBandarPekanMukim>();
       senaraiMukim = devService.searchKodBPM(pengguna.getKodCawangan().getDaerah().getKod());
       
       LOG.info("--------KodDaerah--------:"+pengguna.getKodCawangan().getDaerah().getKod());
       LOG.info("--------Mukim------------:"+mukim);
       //listSiriNoPt = new ArrayList<SiriNoPt>();       
       //listSiriNoPt = devService.searchSiriNoPtByKodBPM(pengguna.getKodCawangan().getDaerah().getKod(),mukim);
       listhakmilik = new ArrayList<Hakmilik>();
       listhakmilik = devService.searchNoPtByKodBPM(pengguna.getKodCawangan().getDaerah().getKod(),mukim);
       LOG.info("--------listhakmilik------------:"+listhakmilik.size());
        return new JSP("/pembangunan/utiliti/listSiriNoPt.jsp");        
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<KodBandarPekanMukim> getSenaraiMukim() {
        return senaraiMukim;
    }

    public void setSenaraiMukim(List<KodBandarPekanMukim> senaraiMukim) {
        this.senaraiMukim = senaraiMukim;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    //    public List<SiriNoPt> getListSiriNoPt() {
    //        return listSiriNoPt;
    //    }
    //
    //    public void setListSiriNoPt(List<SiriNoPt> listSiriNoPt) {
    //        this.listSiriNoPt = listSiriNoPt;
    //    }
    public List<Hakmilik> getListhakmilik() {
        return listhakmilik;
    }

    public void setListhakmilik(List<Hakmilik> listhakmilik) {
        this.listhakmilik = listhakmilik;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;

//import etanah.dao.AduanPortalDAO;

//import etanah.model.AduanPortal;
import etanah.service.uam.AduanPortalService;
import java.util.ArrayList;
import java.util.List;
import etanah.dao.AduanPortalDAO;
import etanah.dao.KodStatusPortalDAO;
import etanah.model.AduanPortal;
import etanah.model.KodStatusPortal;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/uam/senarai_aduan_portal")
public class SenaraiAduanPortalActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SenaraiAduanPortalActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    AduanPortalService aduanPortalService;
    @Inject
    AduanPortalDAO aduanPortalDAO;
    @Inject
    KodStatusPortalDAO kodStatusPortalDAO;
    AduanPortal aduanPortal;
    private String status;
    private List<AduanPortal> senaraiAduanPortal;
    private List<KodStatusPortal> senaraiStatus;

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/uam/senaraiAduanPortal.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        //senaraiAduanPortal = new ArrayList<AduanPortal>() ;
        //senaraiStatus = new ArrayList<KodStatusPortal>() ;
        senaraiAduanPortal = aduanPortalService.getSenaraiAduan();
        senaraiStatus = aduanPortalService.getSenaraiStatus();
        System.out.println("size Aduan : " + senaraiAduanPortal.size());
    }

    public Resolution searchAduan() throws NamingException {
        if (status != null) {
            System.out.println("search status : " + status);
            senaraiAduanPortal = new ArrayList<AduanPortal>();
            senaraiAduanPortal.addAll(aduanPortalService.searchStatus(status));
            System.out.println("size search result(status) : " + senaraiAduanPortal.size());
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/senaraiAduanPortal.jsp");
    }

    public Resolution getNewAduanList() throws NamingException {
          logger.info("------------rehydrate--------------");
         
        senaraiAduanPortal = aduanPortalService.getSenaraiNewAduan();
        
        System.out.println("size Aduan : " + senaraiAduanPortal.size());
            
        return new ForwardResolution("/WEB-INF/jsp/uam/getNewAduan.jsp");
    }

    public List<AduanPortal> getSenaraiAduanPortal() {
        return senaraiAduanPortal;
    }

    public void setSenaraiAduanPortal(List<AduanPortal> senaraiAduanPortal) {
        this.senaraiAduanPortal = senaraiAduanPortal;
    }

    public List<KodStatusPortal> getSenaraiStatus() {
        return senaraiStatus;
    }

    public void setSenaraiStatus(List<KodStatusPortal> senaraiStatus) {
        this.senaraiStatus = senaraiStatus;
    }

    public AduanPortal getAduanPortal() {
        return aduanPortal;
    }

    public void setAduanPortal(AduanPortal aduanPortal) {
        this.aduanPortal = aduanPortal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

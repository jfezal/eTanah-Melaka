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
@UrlBinding("/uam/get_new_aduan")
public class GetNewAduanActionBean extends AbleActionBean {

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

    @DefaultHandler
    public Resolution showForm() {

        return new ForwardResolution("/WEB-INF/jsp/uam/getNewAduan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");

        senaraiAduanPortal = aduanPortalService.getSenaraiNewAduan();

        System.out.println("size Aduan : " + senaraiAduanPortal.size());

    }

    public List<AduanPortal> getSenaraiAduanPortal() {
        return senaraiAduanPortal;
    }

    public void setSenaraiAduanPortal(List<AduanPortal> senaraiAduanPortal) {
        this.senaraiAduanPortal = senaraiAduanPortal;
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

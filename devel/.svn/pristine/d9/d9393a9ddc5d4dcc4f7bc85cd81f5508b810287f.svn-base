/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author siti.mudmainnah
 */
@UrlBinding("/strata/CetakanSijilMC")
public class CetakanSijilMC extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(CetakanSijilMC.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private BadanPengurusan bdnUrus;
    private String idHakmilik;
    @Inject
    StrataPtService strataPtService;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("addNew", Boolean.FALSE);
        bdnUrus = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/cetakanSijilMC.jsp");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BadanPengurusan getBdnUrus() {
        return bdnUrus;
    }

    public void setBdnUrus(BadanPengurusan bdnUrus) {
        this.bdnUrus = bdnUrus;
    }

}

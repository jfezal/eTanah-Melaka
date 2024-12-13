package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import able.stripes.JSP;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author haqqiem 12 Mar 2013
 *
 */
@UrlBinding("/hasil/maklumat_hakmilik")
public class MaklumatHakmilikActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatHakmilikActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    
    private static final String MAIN_VIEW = "hasil/maklumat_hakmilik.jsp";
    
    private String idPermohonan = "";
    private String negeri = "";
    
    private Permohonan permohonan = new Permohonan();
    private HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
    private Hakmilik hakmilik = new Hakmilik();
    
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
    
    @Inject private etanah.Configuration conf;
    @Inject private HakmilikDAO hakmilikDAO;
    @Inject private PermohonanDAO permohonanDAO;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("..:: INSIDE showForm : START ::..");
        if("04".equals(conf.getProperty("kodNegeri"))){negeri = "melaka";}
        if("05".equals(conf.getProperty("kodNegeri"))){negeri = "negeri";}

        LOG.info("..:: INSIDE showForm : FINISH ::..");
        return new JSP(MAIN_VIEW).addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:START");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : "+idPermohonan);
        
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        LOG.info("senaraiHakmilikTerlibat.size() : "+senaraiHakmilikTerlibat.size());
        
        hakmilik = senaraiHakmilikTerlibat.get(0).getHakmilik();
        LOG.info("hakmilik.getIdHakmilik() : "+hakmilik.getIdHakmilik());
        
        LOG.info("rehydrate:FINISH");
    }
    
    public Resolution reloadEdit() {
        LOG.info("..:: INSIDE reloadEdit ::..");
        String id = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik : "+id);
        hakmilik = hakmilikDAO.findById(id);
        return showForm();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}

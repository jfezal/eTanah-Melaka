/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.strata.CommonService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/utiliti_dok")
public class CarianMohonRujLuarAction extends AbleActionBean {
    
    private List<PermohonanRujukanLuar> mohonRujLuar;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CarianDokumenActionBean.class);
    private boolean flag = false;
    private String idPermohonan;
    @Inject
    private CommonService service;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/utilitiDokumen.jsp");
    }
    
    public Resolution findPermohonan()throws Exception{
        if(getContext().getRequest().getParameter("IdPermohonan")==null){
        mohonRujLuar = service.findPermohonan(idPermohonan);
        }
        else{
            idPermohonan = getContext().getRequest().getParameter("IdPermohonan");
             mohonRujLuar = service.findPermohonan(idPermohonan);
        }
        setFlag(true);
        logger.debug("no of rujLuarDok list"+mohonRujLuar.size());
        logger.debug("PASS");
        
        return new ForwardResolution("/WEB-INF/jsp/utiliti/utilitiDokumen.jsp");

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanRujukanLuar> getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(List<PermohonanRujukanLuar> mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public CommonService getService() {
        return service;
    }

    public void setService(CommonService service) {
        this.service = service;
    }
    
    
}

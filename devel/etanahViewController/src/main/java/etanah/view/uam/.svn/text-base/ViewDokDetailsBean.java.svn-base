/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Dokumen;
import etanah.service.uam.UamService;
import java.util.logging.Logger;
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
@UrlBinding("/uam/dok_Details")
public class ViewDokDetailsBean extends AbleActionBean{

    @Inject
    private UamService service;
    private Dokumen dok;
    
    
    @DefaultHandler
    public Resolution showForm(){
        
        return new JSP("uam/viewDokDetails.jsp");
    }
    
    public Resolution getDokumenDetails(){
        String id = getContext().getRequest().getParameter("idDok");
        dok = service.getDokDetails(Long.parseLong(id));
        
        return new ForwardResolution("/WEB-INF/jsp/uam/viewDokDetails.jsp");
    }

    public Dokumen getDok() {
        return dok;
    }

    public void setDok(Dokumen dok) {
        this.dok = dok;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }
    
    
}

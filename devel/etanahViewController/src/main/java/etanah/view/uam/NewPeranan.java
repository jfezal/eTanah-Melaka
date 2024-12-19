/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/uam/new_peranan")
public class NewPeranan extends AbleActionBean {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewPeranan.class);
    private KodPeranan peranan;
    @Inject
    private UamService service;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new JSP("uam/new_peranan.jsp");
    }

    public Resolution newPeranan() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info("Creating Peranan....");
        
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        if(peranan != null){
            logger.debug("screen :"+peranan.getDefaultScreen());
            service.newGroup(peranan, pengguna);
        }
        logger.info("Creating Peranan Success....");
        return new ForwardResolution("/WEB-INF/jsp/uam/new_peranan.jsp");
    }

    public Resolution updatePeranan() throws Exception{
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if(peranan != null){
            logger.debug("screen :"+peranan.getDefaultScreen());
            service.updateGroup(peranan,pengguna);
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/new_peranan.jsp");
    }

    public Resolution searchPeranan() throws Exception {
        logger.info("Searching Peranan....");
        peranan = service.searchingPeranan(peranan.getNama());

        if (peranan != null) {
            if (peranan.getNama() != null) {
                getContext().getRequest().setAttribute("kemaskini", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                addSimpleMessage("Carian Peranan berjaya.");
                logger.info("Searching Peranan Success....");
                
                return new ForwardResolution("/WEB-INF/jsp/uam/new_peranan.jsp");
            }else{
                addSimpleError("Peranan tidak wujud.");
            }
        }else{
            addSimpleError("Peranan tidak wujud.");
        }
        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kemaskini", Boolean.FALSE);
        getContext().getRequest().setAttribute("kataLaluan", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/uam/new_peranan.jsp");
    }

    public KodPeranan getPeranan() {
        return peranan;
    }

    public void setPeranan(KodPeranan peranan) {
        this.peranan = peranan;
    }

}

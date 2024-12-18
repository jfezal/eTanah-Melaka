/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.manager.TabManager;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Solahuddin
 */
@UrlBinding("/pmohonanConsent")
public class PermohonanConsentActionBean extends AbleActionBean {

    TabManager tabManager;

    @Inject
    public PermohonanConsentActionBean(TabManager tabManager){
        this.tabManager = tabManager;
    }

   
    public String getPrefixUri() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public void rehydrate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @DefaultHandler
    public Resolution showForm() throws Exception{
       
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");

    }
   
}

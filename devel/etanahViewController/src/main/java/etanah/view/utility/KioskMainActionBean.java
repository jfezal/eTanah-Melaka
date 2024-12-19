/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
@UrlBinding("/kiosk/main")
public class KioskMainActionBean extends AbleActionBean{
     @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @DefaultHandler
    public Resolution showForm() {

        return new JSP("utiliti/kiosk_main.jsp");
    }
}

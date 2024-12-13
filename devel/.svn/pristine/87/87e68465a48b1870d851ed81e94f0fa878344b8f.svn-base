package com.theta.portal.stripes;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.TableService;
import com.theta.portal.stripes.form.SenaraiTugasan;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;

@UrlBinding("/welcome")
public class WelcomeActionBean extends BaseActionBean {

    

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    List<SenaraiTugasan> list = new ArrayList<SenaraiTugasan>();
    List<SenaraiTugasan> listPulangSemula = new ArrayList<SenaraiTugasan>();

    @DefaultHandler
    public Resolution welcome() {

        return new JSP("index.jsp");
    }

   
    

    

}

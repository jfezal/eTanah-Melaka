package etanah.view;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


/**
 *
 * @author ${user}
 */
@UrlBinding("/home")
public class HomeAction extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("home.jsp");
    }
    
     public Resolution sek4(){
        return new JSP("home.jsp");
    }
    public Resolution sek8(){
        return new JSP("home_1.jsp");
    }
    
    public Resolution aduan(){
        return new JSP("home_2.jsp");
    }
}

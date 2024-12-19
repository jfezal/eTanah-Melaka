/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import etanah.view.BasicTabActionBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author solahuddin
 */

@UrlBinding("/senaraiTugasan2")
public class SenaraiTugasan2 extends BasicTabActionBean { 

    List listValue = new ArrayList();

    @DefaultHandler
    public Resolution showForm(){
        HttpSession ses = context.getRequest().getSession();
        listValue = (List) ses.getAttribute("listValue");

        return new ForwardResolution("/WEB-INF/jsp/common/senarai_tugasan.jsp").addParameter("txnCode", txnCode).addParameter("stageId",stageId);
    }

    public List getListValue() {
        return listValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }
}

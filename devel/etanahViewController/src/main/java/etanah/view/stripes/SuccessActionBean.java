/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author solahuddin
 */

@UrlBinding("/successPage")
public class SuccessActionBean extends BasicTabActionBean {

    @DefaultHandler
    public Resolution showForm() {
        
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("idPermohonan", idPermohonan);

    }

    public Resolution goPage(){
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("txnCode", txnCode).addParameter("stageId", stageId).addParameter("idPermohonan", idPermohonan);
    }
}

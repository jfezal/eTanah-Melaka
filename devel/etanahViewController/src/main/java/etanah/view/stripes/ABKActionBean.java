/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
/**
 *
 * @author khairil
 */
@UrlBinding("/awalanBelakangKaunter")
public class ABKActionBean extends AbleActionBean {
     @DefaultHandler
    public Resolution showForm(){
        return new ForwardResolution("/WEB-INF/jsp/pendaftaran/kemasukan_belakang_kaunter.jsp");
    }

}

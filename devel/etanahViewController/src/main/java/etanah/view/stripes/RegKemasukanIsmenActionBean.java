/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author khairil
 */
@UrlBinding("/Ismen")
public class RegKemasukanIsmenActionBean extends BasicTabActionBean{

@DefaultHandler
public Resolution showForm(){
     
     return new ForwardResolution("/WEB-INF/jsp/pendaftaran/ismen.jsp");
}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/perakuan")
public class PerakuanActionBean extends BasicTabActionBean {
    String peraku = "";
    String ulasan = "";


    public void save(){
        
    }

    public String getPeraku() {
        return peraku;
    }

    public void setPeraku(String peraku) {
        this.peraku = peraku;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    
}

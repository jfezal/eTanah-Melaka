/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author john
 */
@UrlBinding("/pembangunan/melaka/pengesahan_pelan")
public class PengesahanPelanActionBean extends AbleActionBean{
    @DefaultHandler
    public Resolution showForm() {
       
        return new JSP("pembangunan/SBMS/pengesahan_pelan.jsp");
    }
}

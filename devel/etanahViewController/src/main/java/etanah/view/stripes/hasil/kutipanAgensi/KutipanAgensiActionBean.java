/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/hasil/kutipan_agensi_new")
public class KutipanAgensiActionBean extends AbleActionBean{
    
    private static String MAIN_PAGE = "hasil/kutipan/kutipan_agensi_main.jsp";
    private static String UPDATE_PAGE = "hasil/kutipan/kutipan_agensi_update.jsp";
    
        @DefaultHandler
    public Resolution showForm() {
        
        return new JSP(MAIN_PAGE);
    }
}

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/endosan")
public class PenyediaanEndosanActionBean extends AbleActionBean {

     @DefaultHandler
    public Resolution showEndorsan() {
        return new JSP("hasil/penyediaan_borang_endosan.jsp").addParameter("tab", "true");
    }
    

}
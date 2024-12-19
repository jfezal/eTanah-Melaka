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
@UrlBinding("/hasil/pembayaran")
public class StatusPembayaranActionBean extends AbleActionBean {

     @DefaultHandler
    public Resolution showBayaran() {
        return new JSP("hasil/status_pembayaran_6A.jsp").addParameter("tab", "true");
    }
}

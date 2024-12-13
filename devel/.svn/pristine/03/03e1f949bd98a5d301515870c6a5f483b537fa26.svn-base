/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/senarai_hakmilik_bersyarat")
public class SenaraiHakmilikActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(SenaraiHakmilikActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("display form");
        return new JSP("pembangunan/pecahSempadan/dev_senarai_hakmilik.jsp").addParameter("tab", "true");
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@Wizard(startEvents = {"showForm"})
@UrlBinding("/lelong/kalender_jurulelong")
public class KalenderActionBean extends AbleActionBean {

    @Inject
    CalenderManager manager;
    @Inject
    LelongService lelongService;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private static final Logger LOG = Logger.getLogger(KalenderActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("---showForm---");
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx.getKodCawangan().getKod());
        return new ForwardResolution("/WEB-INF/jsp/lelong/calender_lelong_utiliti.jsp");
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

}

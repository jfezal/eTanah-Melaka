package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

@UrlBinding("/DendaLewat")
public class DendaLewat extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(DendaLewat.class);
    String reportName;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");

//        reportName = "ACQBayaranDendaLewat_MLK.rdf";

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/dendaLewat.jsp");
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }


}

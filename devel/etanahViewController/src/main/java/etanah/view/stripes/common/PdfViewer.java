/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.sql.Connection;
import java.sql.DriverManager;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author mansur
 */
@UrlBinding("/common/pdf_viewer")
public class PdfViewer extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PdfViewer.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private StringBuilder pdf_url;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("pdf_url=" + pdf_url);

        //Connection();
        return new JSP("common/pdf_viewer.jsp").addParameter("popup", "true");
    }

   private void Connection () {
        Connection conn = null;
        
        try {
            String driver = "oracle.jdbc.OracleDriver";
            Class.forName(driver);
            conn = DriverManager.getConnection("http://etml-rep-vip:9003/reports/rwservlet", "orarep", "ASDqwe123#");
            Integer timeout = conn.getNetworkTimeout();
            LOG.info("log out time " + timeout);
        }catch (Exception ex) {
            LOG.error("not valid", ex);
        }
    }
    


    public StringBuilder getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(StringBuilder pdf_url) {
        this.pdf_url = pdf_url;
    }

}

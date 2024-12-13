/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author nur.aqilah
 */
@UrlBinding("/lelong/pdf_viewer")
public class PdfViewer extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(etanah.view.stripes.lelong.PdfViewer.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    private StringBuilder pdf_url;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("pdf_url="+pdf_url);
        return new JSP("lelong/pdf_viewer.jsp").addParameter("popup", "true");
    }

    public StringBuilder getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(StringBuilder pdf_url) {
        this.pdf_url = pdf_url;
    }

}
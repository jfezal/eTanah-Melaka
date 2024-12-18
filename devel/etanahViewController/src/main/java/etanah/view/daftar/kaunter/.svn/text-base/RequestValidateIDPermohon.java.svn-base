/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar.kaunter;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@HttpCache (allow = false)
@UrlBinding ("/daftar/check_idpermohonan")
public class RequestValidateIDPermohon extends AbleActionBean{

    public String idPermohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    private static Logger LOG = Logger.getLogger(RequestValidateIDPermohon.class);
    private static boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution doCheckPermohonan() {
        if (debug)
        	LOG.debug("*****RequestValidateIDPermohon.doCheckPermohonan:idPermohonan :" + idPermohonan);
        String results = "0";
        String kodUrusanCurrent = getContext().getRequest().getParameter("kodUrusan");

        Permohonan p = permohonanDAO.findById(idPermohonan);
        if ("SSLSC".equals(kodUrusanCurrent)) {
            KodUrusan ku = p.getKodUrusan();
            if (!"SC".equals(ku.getKodPerserahan().getKod()) ){
                results = "Perserahan bukan urusniaga";
            } else {
                if(p != null) results = "1";
            }
        } else {
            if(p != null) results = "1";
        }
        
        return new StreamingResolution("text/plain", results);
    }

}

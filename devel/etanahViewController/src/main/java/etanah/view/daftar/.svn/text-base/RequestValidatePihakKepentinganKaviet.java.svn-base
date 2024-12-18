/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.common.HakmilikPihakKepentinganService;
import java.util.List;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@HttpCache(allow=false)
@UrlBinding("/daftar/check_pihak")
public class RequestValidatePihakKepentinganKaviet extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(RequestValidatePihakKepentinganKaviet.class);
    
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanDAO permohonanDAO;
    public String idHakmilik;
    private Permohonan permohonan;


    public Resolution doCheckPihak(){
        logger.debug("idHakmilik :" + idHakmilik);

        String results = "0";        
        List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.doCheckPihakKaviet(idHakmilik);
        if(list.size() > 0)
            results = "1";        
        return new StreamingResolution("text/plain", results);
    }

    public Resolution doCheckPihak2(){
        logger.debug("idHakmilik :" + idHakmilik);
        String results = "0";
        List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.doCheckPihakTenansi(idHakmilik);
        if(list.size() > 0)
            results = "1";
        return new StreamingResolution("text/plain", results);
    }
}

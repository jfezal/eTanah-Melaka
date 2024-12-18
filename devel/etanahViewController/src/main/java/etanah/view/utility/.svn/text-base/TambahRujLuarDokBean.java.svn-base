/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.KodDokumen;
import etanah.view.strata.CommonService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Aziz
 */
@HttpCache(allow=false)
@UrlBinding("utility/tambah_Dokumen")
public class TambahRujLuarDokBean extends AbleActionBean {

    private String keyword;
   // private List<Dokumen> listSearchDok;
    //private List<Dokumen> listFiterSearchDok;
    @Inject
    private CommonService service;
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("utiliti/senaraiTambahDok.jsp");
        
    }
    
    public Resolution cariTambahDok()throws Exception{
        
//     listSearchDok = service.allSearchDokumen(keyword);
     
        
        
        
        return new ForwardResolution("utiliti/senaraiTambahDok.jsp");
    }
}

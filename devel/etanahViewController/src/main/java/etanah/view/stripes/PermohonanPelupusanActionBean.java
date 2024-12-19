/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.manager.TabManager;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/pmohonanPelupusan")
public class PermohonanPelupusanActionBean extends AbleActionBean{

    TabManager tabManager;
//    private DummyTableForDisplayTag table;
    


    @Inject
    public PermohonanPelupusanActionBean(TabManager tabManager){
        this.tabManager = tabManager;        
    }   

    @DefaultHandler
    public Resolution showForm(){
//        table = new DummyTableForDisplayTag();
       
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }
    

    
    public Resolution save(){        
        return new ForwardResolution("/WEB-INF/jsp/tab/main_page.jsp");
    }

    public Resolution popup(){
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/kemasukkan_maklumat_tanah_dipohon.jsp");        
    }

}

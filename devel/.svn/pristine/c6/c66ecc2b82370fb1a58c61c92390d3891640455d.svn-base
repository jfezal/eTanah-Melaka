/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.SejarahDokumenDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.SejarahDokumen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
@UrlBinding("/sej_dok")
public class SejarahDokumenAction extends AbleActionBean{
    
    
    private String idHakmilik;
    
    private List<SejarahDokumen> listSejarahDokumen;
    
    @Inject HakmilikDAO hakmilikDAO;
    
    @Inject SejarahDokumenDAO sejarahDAO;
    
    
    static final Comparator<SejarahDokumen> ORDER_BY =
                                 new Comparator<SejarahDokumen>() {
        @Override
        public int compare(SejarahDokumen t1, SejarahDokumen t2) {           

            int noVersi1 = Integer.parseInt(t1.getNoVersi());
            int noVersi2 = Integer.parseInt(t2.getNoVersi());

            if (noVersi1 > noVersi2) return 1;
            else if (noVersi1 < noVersi2) return -1;
            else return 0;
            
        }
    };
    
    
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/daftar/utiliti/sej_dok_main.jsp");
    }
    
    public Resolution search() {
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm  = hakmilikDAO.findById(idHakmilik);
            if (hm != null) {
                listSejarahDokumen = new ArrayList<SejarahDokumen>();
                Dokumen dhde = hm.getDhde();
                SejarahDokumen sej = sejarahDAO.findById(dhde.getIdDokumen());
                listSejarahDokumen.add(sej);
                
                do {
                    
                    sej = sejarahDAO.findById(sej.getInduk().getIdDokumen());
                    listSejarahDokumen.add(sej);
                    
                } while(sej != null                         
                        && sej.getIdDokumen() != sej.getInduk().getIdDokumen());
            }
        }
        
        Collections.sort(listSejarahDokumen, ORDER_BY);
        
        return new JSP("/daftar/utiliti/sej_dok_main.jsp");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<SejarahDokumen> getListSejarahDokumen() {
        return listSejarahDokumen;
    }

    public void setListSejarahDokumen(List<SejarahDokumen> listSejarahDokumen) {
        this.listSejarahDokumen = listSejarahDokumen;
    }
    
}

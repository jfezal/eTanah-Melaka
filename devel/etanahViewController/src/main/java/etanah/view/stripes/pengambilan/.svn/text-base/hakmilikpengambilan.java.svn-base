/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/hakmilikpengambilan")
public class hakmilikpengambilan extends AbleActionBean {
    private List<Hakmilik> list;
    private Hakmilik hakmilik;
    private String idHakmilik;
    @Inject
    HakmilikDAO hakmilikDAO;


    @DefaultHandler
    public Resolution showForm(){
        list = Collections.EMPTY_LIST;
        return new ForwardResolution("/WEB-INF/jsp/common/carian_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution searchHakmilik(){
        if(hakmilik != null){
            Hakmilik hkmilik = hakmilikDAO.findById(hakmilik.getIdHakmilik());
            if(hkmilik != null){
                list = new LinkedList<Hakmilik>();
                list.add(hkmilik);
            }else
                list = Collections.EMPTY_LIST;
        }else
            list = Collections.EMPTY_LIST;
        return new ForwardResolution("/WEB-INF/jsp/common/carian_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }


    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.dao.PihakDAO;
import java.util.Collections;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import etanah.model.Pihak;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.service.RegService;
import etanah.service.common.PihakService;

/**
 *
 * @author khairil
 */
@UrlBinding("/pihak")
public class MaklumatPihakActionBean extends AbleActionBean {

    private List<Pihak> list;
    Pihak pihak;
    private String idPihak;
    String idHakmilik;
    
    @Inject
    PihakDAO pihakDAO;
    @Inject
    RegService regService;
    @Inject
    PihakService pihakService;

    @DefaultHandler
    public Resolution showForm() {
        list = Collections.EMPTY_LIST;
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        System.out.println("idHakmilik:" + idHakmilik);
        return new ForwardResolution("/WEB-INF/jsp/daftar/common/carian_pihak.jsp").addParameter("popup", "true").addParameter("idHakmilik",idHakmilik);
    }

    public Resolution searchPihak() {
        if (pihak != null) {
//            list = regService.searchPihak(pihak.getNama());
            list = pihakService.findAll(getContext().getRequest().getParameterMap());

        } else {
            addSimpleError("Sila Masukkan Nama Pihak / No Kad Pengenalan");
        }

        return new ForwardResolution("/WEB-INF/jsp/daftar/common/carian_pihak.jsp").addParameter("popup", "true");

    }

     public Resolution pihakDetail() {
        
        pihak = pihakDAO.findById(new Long(idPihak));
        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

//    public List<Pihak> searchPihak(String nama) {
//        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("from etanah.model.Pihak u where u. = :nama");
//            q.setString("nama", nama);
//            return q.list();
//        } finally {
//            //session.close();
//        }
//
//    }

    
    public List<Pihak> getList() {
        return list;
    }


    public void setList(List<Pihak> list) {
        this.list = list;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }
}

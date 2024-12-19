/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.nota;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.RegService;
import etanah.model.Hakmilik;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
/**
 *
 * @author mohd.fairul
 */@UrlBinding("/nota/pembetulan/senaraiHakmilik")
public class PembetulSenaraiHakmilik extends AbleActionBean{
    @Inject
    PermohonanDAO permohonanDAO;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int size = 0;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/paparan_maklumat_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }
    @HttpCache(allow=false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/pembetulan/select_senarai_hakmilik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
//       Permohonan p = ((etanahActionBeanContext)getContext()).getPermohonan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (p != null) {
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            size = hakmilikPermohonanList.size();
        }
//        if (idHakmilik != null) {
//
//            listHP = regService.searchMohonHakmilik(idHakmilik);
//            Long idMohonHakmilik = listHP.get(0).getId();
//            hakmilik = hakmilikDAO.findById(idHakmilik);
//            HakmilikPermohonan hp = new HakmilikPermohonan();
//            hp = hakmilikPermohonanDAO.findById(idMohonHakmilik);
//            listHakmilikAsalPermohonan = hp.getSenaraiHakmilikAsal();
//            listHakmilikSblmPermohonan = hp.getSenaraiHakmilikSebelum();
//            pihakKepentinganList = regService.searchPihakBerKepentingan(idHakmilik);
//            syerPembilang = new String[pihakKepentinganList.size()];
//            syerPenyebut = new String[pihakKepentinganList.size()];
//
//        }
//
//        if (pihakKepentinganList != null) {
//            int counter = 0;
//            for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : pihakKepentinganList) {
//                syerPembilang[counter] = String.valueOf(hakmilikPihakBerkepentingan.getSyerPembilang());
//                syerPenyebut[counter] = String.valueOf(hakmilikPihakBerkepentingan.getSyerPenyebut());
//                counter = counter + 1;
//            }
//        }
    }

     public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

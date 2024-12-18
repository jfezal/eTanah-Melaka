/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author mazurahayati.yusop
 */
@HttpCache(allow = false)
@UrlBinding("/lelong/maklumat_tanah")
public class MaklumatTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int size = 0;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
//    private String[] atasTanah;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_tanah.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparanHakmilik() {
        return new JSP("consent/paparan_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            List<HakmilikPihakBerkepentingan> senaraiHpk = new ArrayList<HakmilikPihakBerkepentingan>();
            pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                Hakmilik hk = hp.getHakmilik();
                if (hk == null) {
                    continue;
                }
                senaraiHpk = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hk);
                for (HakmilikPihakBerkepentingan hpk : senaraiHpk) {
                    if (hpk == null) {
                        continue;
                    }
                    pihakKepentinganList.add(hpk);
                }
            }
        }
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

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }
}


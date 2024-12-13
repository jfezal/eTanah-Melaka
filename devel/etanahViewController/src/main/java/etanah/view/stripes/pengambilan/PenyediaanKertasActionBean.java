/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/penyediaan_kertas")
public class PenyediaanKertasActionBean extends AbleActionBean {
@Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    String idHakmilik;

    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int size = 0;
    private String[] atasTanah;

     @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/penyediaan_kertas_pbn.jsp").addParameter("tab", "true");
    }

     public Resolution showForm2() {
        return new JSP("pengambilan/huraian_PTG.jsp").addParameter("tab", "true");
    }
      public Resolution showForm3() {
        return new JSP("pengambilan/paparan_kertas_pertimbangan.jsp").addParameter("tab", "true");
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

    public String[] getAtasTanah() {
        return atasTanah;
    }

    public void setAtasTanah(String[] atasTanah) {
        this.atasTanah = atasTanah;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Hakmilik;
import etanah.service.common.HakmilikService;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanPihakTidakBerkepentingan;

/**
 *
 * @author nordiyana
 */
@HttpCache(allow=false)
@UrlBinding("/pengambilan/maklumat_hakmilik")
public class maklumathakmilikpengambilanactionbean extends AbleActionBean {
      @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PengambilanService service;
    String idHakmilik;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihakTidakBerkepentingan> pbtlist;

    public PermohonanPihakTidakBerkepentingan getPbt() {
        return pbt;
    }

    public void setPbt(PermohonanPihakTidakBerkepentingan pbt) {
        this.pbt = pbt;
    }

    public List<PermohonanPihakTidakBerkepentingan> getPbtlist() {
        return pbtlist;
    }

    public void setPbtlist(List<PermohonanPihakTidakBerkepentingan> pbtlist) {
        this.pbtlist = pbtlist;
    }
    private PermohonanPihakTidakBerkepentingan pbt;
    private int size = 0;
    private String[] atasTanah;


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }
    @HttpCache(allow=false)
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
    @HttpCache(allow=false)
    public Resolution hakMilikPopup() {
//         String urusan = getContext().getRequest().getParameter("urusan");
//        if (StringUtils.isNotBlank(urusan)) {
//            getContext().getRequest().setAttribute("urusan", urusan);
//        }
        return new JSP("pengambilan/kemasukan_hakmilik.jsp").addParameter("popup", "true");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            atasTanah = new String[hakmilikPermohonanList.size()];
            size = hakmilikPermohonanList.size();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan h = (HakmilikPermohonan)hakmilikPermohonanList.get(i);
                atasTanah[i] = h.getHakmilik().getMaklumatAtasTanah();
            }
        }
    }

    public Resolution simpanHakmilikPopup() {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            hp=service.findPermohonanByIdMH(hakmilikPermohonanList.get(0).getId());
           for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList)
           {  
               pbt=new PermohonanPihakTidakBerkepentingan();
               pbt.setHakmilikPermohonan(hakmilikPermohonan);
           
           
           }
            Hakmilik h = hakmilikDAO.findById(hakmilik.getIdHakmilik());
            if (h == null) {
            h = new Hakmilik();
            h.setIdHakmilik(hakmilik.getIdHakmilik());
            hakmilikService.saveHakmilik(h);
            pbtlist=service.findByIdMh(hakmilikPermohonanList.get(0).getId());
            
            if(pbtlist !=null){
                
               
                
            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");

      return new StreamingResolution("text/plain", "1");
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

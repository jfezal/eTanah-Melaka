/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Permohonan;
import etanah.service.PengambilanService;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.PermohonanRujukanLuar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/drafWartaPB")
public class drafWartaPenarikanBalikActionBean extends AbleActionBean {


    private static Logger logger = Logger.getLogger(drafWartaPenarikanBalikActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanPengambilan permohonanPengambilan;

    @Inject
    PengambilanService service;
    private Date tarikhWartaTerdahulu;
    private String noWartaTerdahulu;
    private String idPermohonan;
    private String idPengambilan;
    private String idSebelum;

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");

     @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/penarikanbalik/drafWartaPenarikanBalik.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Permohonan pm = permohonanDAO.findById(idPermohonan);
        permohonanSebelum = pm.getPermohonanSebelum();
        
        logger.debug("id terdahulu" + permohonanSebelum);

        if (permohonanSebelum != null)
         {
//            permohonanSebelum = permohonanDAO.findById(idPermohonan);

            permohonanRujukanLuar = service.getLatestRujukanLuar(permohonanSebelum.getIdPermohonan());
            if(permohonanRujukanLuar == null){
//                permohonanRujukanLuar = service.getRujLuar(permohonanSebelum.getIdPermohonan());
                permohonanRujukanLuarList = service.getRujLuarList(permohonanSebelum.getIdPermohonan());
            }

            if(permohonanRujukanLuar != null){
                tarikhWartaTerdahulu = permohonanRujukanLuar.getTarikhLulus();
                noWartaTerdahulu = permohonanRujukanLuar.getItem();
            }
        }
    }

     public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public Date getTarikhWartaTerdahulu() {
        return tarikhWartaTerdahulu;
    }

    public void setTarikhWartaTerdahulu(Date tarikhWartaTerdahulu) {
        this.tarikhWartaTerdahulu = tarikhWartaTerdahulu;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }
     public String getNoWartaTerdahulu() {
        return noWartaTerdahulu;
    }

    public void setnoWartaTerdahulu(String noWartaTerdahulu) {
        this.noWartaTerdahulu = noWartaTerdahulu;
    }

    public String getIdPengambilan() {
        return idPengambilan;
    }

    public void setIdPengambilan(String idPengambilan) {
        this.idPengambilan = idPengambilan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }
}

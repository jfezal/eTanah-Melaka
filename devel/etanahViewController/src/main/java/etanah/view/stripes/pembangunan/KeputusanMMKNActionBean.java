/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import net.sourceforge.stripes.action.ErrorResolution;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/keputusanMMKN")
public class KeputusanMMKNActionBean extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(MaklumatPermohonanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private String stageId;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/keputusan_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start.");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
        // Melaka
//        stageId = "rekodkpsnmmkncetaksurat";
        //NS
        stageId = "rekodkpsnmmkncetaksurat";
        
        fasaPermohonan = devService.findFasaPermohonanByIdAliran(idPermohonan, stageId);

        permohonanRujukanLuar = devService.findUlasanPerRujLuar(idPermohonan,"MMKT");

        permohonan = permohonanDAO.findById(idPermohonan);
        logger.info("rehydrate end.");
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    
}

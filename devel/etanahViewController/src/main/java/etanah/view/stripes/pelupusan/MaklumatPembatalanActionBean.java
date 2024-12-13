package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.Pengguna;

@UrlBinding("/pelupusan/maklumat_pembatalan")
public class MaklumatPembatalanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatPembatalanActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    Permohonan permohonan;

    public String getSebabPembatalan() {
        return sebabPembatalan;
    }

    public void setSebabPembatalan(String sebabPembatalan) {
        this.sebabPembatalan = sebabPembatalan;
    }
    private String idPermohonan;
    private String sebabPembatalan;
    private ActionBeanContext context;

    public MaklumatPembatalanActionBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ActionBeanContext getContext() {
        // TODO Auto-generated method stub
        return this.context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate");
        // Get Permohonan
        retrieveData();
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_pembatalan.jsp").addParameter("tab", "true");
    }

    public Resolution retrieveData() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        TanahRizabPermohonan mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);

        if (mohonTrizab != null) {

            this.setSebabPembatalan(mohonTrizab.getCatatan());
        } else {
            this.setSebabPembatalan("");
        }

        return new JSP("pelupusan/maklumat_pembatalan.jsp").addParameter(
                "tab", "true");
    }

    public Resolution saveData() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
		//Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
		LOG.info("idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        TanahRizabPermohonan mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);

        LOG.info("sebabPembatalan: " + sebabPembatalan);
//                LOG.info("-->" + permohonan.getSebab());

        // Set Permohonan
        mohonTrizab.setCatatan(sebabPembatalan);

        // Transaction start
        // autocommit = false
        try {
            pelupusanService.simpanTanahRizabPermohonan(mohonTrizab);

            addSimpleMessage("Maklumat Berjaya Disimpan");
            // commit
        } catch (Exception ex) {
            //ex.printStackTrace();
            // rollback
        }


        // Transaction end
        return new JSP("pelupusan/maklumat_pembatalan.jsp").addParameter(
                "tab", "true");
    }
}

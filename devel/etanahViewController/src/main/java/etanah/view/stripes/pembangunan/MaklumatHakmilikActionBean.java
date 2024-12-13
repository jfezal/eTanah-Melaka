/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.service.common.HakmilikService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author nursyazwani
 */
@HttpCache(allow = false)
@UrlBinding("/pembangunan/maklumat_hakmilik")
public class MaklumatHakmilikActionBean extends AbleActionBean {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MaklumatHakmilikActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private Hakmilik hakmilik;
    String idHakmilik;
    String idPermohonan;
    private Permohonan mohon;
    private String taskId;
    private String stageId;
    private Pengguna pguna;
    private Permohonan permohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<String> noLotList = new ArrayList<String>();
    private String noLot;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/dev_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/common/dev_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        noLot = hakmilik.getNoLot().replace("0", "");
        getContext().getRequest().setAttribute("noLot", noLot);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_maklumat_hakmilik_detail.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonan = permohonanDAO.findById(idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = currentStageId(taskId);
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }


        noLotList = new ArrayList<String>();
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            noLotList.add(hp.getHakmilik().getNoLot().replaceAll("^0*", ""));
            //noLotLnoLotListist.add(hp.getHakmilik().getNoLot().replace("0", ""));
        }
        System.out.println("--noLotList--:" + noLotList);

        
       logger.info("---------rehydrate finish.-----------");

    }

    public Resolution simpanHakmilik() {
        
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp = hakmilikPermohonanList.get(i);
            String maklumatAtasTanah = (String) getContext().getRequest().getParameter("maklumatAtasTanah" + i);

            if (maklumatAtasTanah != null) {
                hmp.getHakmilik().setMaklumatAtasTanah(maklumatAtasTanah);
            } else {
                hmp.getHakmilik().setMaklumatAtasTanah("");
            }
             
hakmilikPermohonanService.save(hmp);
            hakmilikService.saveHakmilik(hmp.getHakmilik());
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/common/dev_maklumat_hakmilik.jsp").addParameter("tab", "true");
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public List<String> getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(List<String> noLotList) {
        this.noLotList = noLotList;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
}

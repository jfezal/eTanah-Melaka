/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import com.google.inject.Inject;
import etanah.model.AduanPortal;
import etanah.model.Pengguna;
import etanah.model.PermohonanPengguna;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/main")
public class MainActionBean extends BasicTabActionBean {

    @Inject
    CommonService comm;
    List taskList = new ArrayList();
    String size = "0";
    String size2 = "0";
    String size3 = "0";
    private Pengguna pengguna;
    private static final Logger logger = Logger.getLogger(MainActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/main/main.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = ((etanahActionBeanContext) getContext()).getUser();

        try {
            /*Integrating with workflow for worklist */
//            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//            taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
//            size = taskList.size() + " Tugasan"; 
            /*-----------------------------------------*/
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public Resolution getTugasan() {
//        try {
//            IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//            taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
//            size = taskList.size() + " Tugasan";
//
//            getContext().getRequest().getSession().setAttribute("size", size);
//            getContext().getRequest().getSession().setAttribute("taskList", taskList);
//
//        } catch (Exception ex) {
//        }

        return new StreamingResolution("size", size);
    }

    public Resolution getTugasan2() {
        try {
            List<PermohonanPengguna> mohonAdmin;
            List<PermohonanPengguna> mohonPenyelia;
            int totalAdmin = 0;
            int totalPenyelia = 0;
            if (pengguna.getPerananUtama() != null) {
                if (pengguna.getPerananUtama().getKod().equals("6")) {
                    mohonAdmin = comm.findListBykod("SH", pengguna.getKodCawangan().getKod());
                    if (!mohonAdmin.isEmpty()) {
                        totalAdmin = mohonAdmin.size();
                    }
                    if (!comm.findListBykod("BR", "KM", pengguna.getIdPengguna()).isEmpty()) {
                        totalAdmin = totalAdmin + comm.findListBykod("BR", "KM", pengguna.getIdPengguna()).size();
                    }
                } else {
                    mohonPenyelia = comm.findListBykod("BR", "KM", pengguna.getIdPengguna());
                    if (!mohonPenyelia.isEmpty()) {
                        totalPenyelia = mohonPenyelia.size();
                    }
                }

            }

            size2 = String.valueOf(totalAdmin + totalPenyelia);

            getContext().getRequest().getSession().setAttribute("size", size);
            getContext().getRequest().getSession().setAttribute("taskList", taskList);

        } catch (Exception ex) {
        }

        return new StreamingResolution("size2", size2);
    }

    public Resolution getTugasan3() {
        try {

            List<AduanPortal> senaraiAduanPortal;
            int totalAduan = 0 ;

            if (pengguna.getPerananUtama() != null) {
                if (pengguna.getPerananUtama().getKod().equals("6")) {
                    senaraiAduanPortal = comm.searchNewAduanListByStatus("1");
                    if (!senaraiAduanPortal.isEmpty()) {
                       
                    totalAduan = senaraiAduanPortal.size();
                    }
                }
            }
            size3 = String.valueOf(totalAduan);

            System.out.println("size Aduan : " + totalAduan);
            
            getContext().getRequest().getSession().setAttribute("size", size);
            getContext().getRequest().getSession().setAttribute("taskList", taskList);

        } catch (Exception ex) {
        }
        return new StreamingResolution("size3", size3);

    }

    
}

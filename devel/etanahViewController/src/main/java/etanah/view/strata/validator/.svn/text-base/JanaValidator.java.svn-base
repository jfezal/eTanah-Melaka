/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermitDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Permit;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanPermitButir;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.StaleObjectException;
import java.util.logging.Level;

/**
 *
 * @author faidzal
 */
public class JanaValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    HakmilikPermohonanDAO HakmilikPermohonanDAO;
    @Inject
    PermitDAO PermitDAO;
    private static final Logger LOG = Logger.getLogger(JanaValidator.class);
    IWorkflowContext ctxOnBehalf = null;
    private Permit permit = new Permit();
    private List<Permit> permitList = new ArrayList();
    private List<Permit> permitList2 = new ArrayList();
    private List<PermohonanPermitButir> mohonPermitList = new ArrayList();
    private HakmilikPermohonan IdMH;
    private Permit idMH3;
    private Permit maxKos;
    private int maxPermit;
    private String stageId;
    private String taskId;
    private String nextStage;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        String stage = "";
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();


        if (kodNegeri.equals("04") && mohon.getKodUrusan().getKod().equals("PPRUS")) {
            
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semakpermit");

            if (mohonFasa != null) {
                LOG.info("-------LALU DI PPRUS-----------");
                LOG.info("-------id mohon-----------" + mohon.getIdPermohonan());

                IdMH = strService.findIdMH(mohon.getIdPermohonan());
                LOG.info("-------IdMH--------" + IdMH);
                if (IdMH != null) {
                    LOG.info("-------IdMH2--------" + IdMH.getId());
                    mohonPermitList = strService.findPermitbutirByIDMH(IdMH.getId());
                    LOG.info("-------mohonPermitList--------" + mohonPermitList);
                    long idMH3 = IdMH.getId();
                    LOG.info("-------idMH--------" + idMH3);

                    if (!mohonPermitList.isEmpty()) {
                        permitList2 = strService.findmohonpermitbtrByIDMH(idMH3);
                        LOG.info("-------permitList2--------" + permitList2.size());
                    }
                    LOG.info("------mohon permit butir list------" + mohonPermitList);
                    maxKos = strService.getHighestNopermit();
                    LOG.info("-------maxKos--------" + maxKos);
                    if (maxKos == null) {
                        int i = 1;
                        System.out.println("------Lalu Max  Null-------");
                        for (Permit p : permitList) {
                            p.setNoPermitStrata(i);
                            strService.savePermit(p);
                            i++;
                        }

                    } else if (maxKos != null) {
                        LOG.info("------Lalu MAx Kos Tidak Null-------");
                        maxPermit = maxKos.getNoPermitStrata();
                        LOG.info("-------maxnoPermit------" + maxPermit);
                        maxPermit++; // yus add 30052018
                        for (Permit p : permitList2) {
                            if (p.getNoPermitStrata() == null) {
                                p.setNoPermitStrata(maxPermit);
                                strService.savePermit(p);
                                maxPermit++;
                                LOG.info("-------noPermit baru------" + maxPermit);
                            } else {
                                LOG.info("-------noPermit dh ada------");
                            }
                        }
                    }
                }
                t.add("STRB4DPermitRuangUdara_MLK.rdf");
                t.add("STRSPermit_MLK.rdf");
                t2.add("PRU");
                t2.add("SMPRU");
                comm.reportGen(mohon, t, t2);

            }
        }
    }

    @Override
        public String beforeComplete(StageContext context, String proposedOutcome) {

        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan permohonan = context.getPermohonan();


        if (permohonan.getKodUrusan().getKod().equals("PPRUS")) {
            context.addMessage(" - Urusan telah selesai. ");

        }

        return proposedOutcome;

    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public HakmilikPermohonan getIdMH() {
        return IdMH;
    }

    public void setIdMH(HakmilikPermohonan IdMH) {
        this.IdMH = IdMH;
    }

    public Permit getMaxKos() {
        return maxKos;
    }

    public void setMaxKos(Permit maxKos) {
        this.maxKos = maxKos;
    }

    public int getMaxPermit() {
        return maxPermit;
    }

    public void setMaxPermit(int maxPermit) {
        this.maxPermit = maxPermit;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public List<Permit> getPermitList() {
        return permitList;
    }

    public void setPermitList(List<Permit> permitList) {
        this.permitList = permitList;
    }

    public List<PermohonanPermitButir> getMohonPermitList() {
        return mohonPermitList;
    }

    public void setMohonPermitList(List<PermohonanPermitButir> mohonPermitList) {
        this.mohonPermitList = mohonPermitList;
    }

    public List<Permit> getPermitList2() {
        return permitList2;
    }

    public void setPermitList2(List<Permit> permitList2) {
        this.permitList2 = permitList2;
    }

    public Permit getIdMH3() {
        return idMH3;
    }

    public void setIdMH3(Permit idMH3) {
        this.idMH3 = idMH3;
    }
}

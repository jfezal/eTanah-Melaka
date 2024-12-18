
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.report.ReportUtil;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class SifusValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    private String stage;
    private static final Logger LOG = Logger.getLogger(SifusValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");

        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(peng);
        ia2.setTarikhKemaskini(new Date());

        if (kodNegeri.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("SFUS") || permohonan.getKodUrusan().getKod().equals("PFUS")) {
                mohonFasa = strService.findMohonFasaKpsn(permohonan.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSijilSIFUS1_MLK.rdf");
                        t2.add("SFUS");
                        comm.reportGen(permohonan, t, t2);
                    }
                    t.add("STRKertasPTG1_MLK.rdf");
                    t2.add("KPTG");
                    comm.reportGen(permohonan, t, t2);

                }
            }

        } //Negeri9
        else {
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        Pengguna peng = context.getPengguna();
        
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(peng);
        ia2.setTarikhKemaskini(new Date());

        try {
            outcome = checkstageID(context);
            if (outcome != null && !outcome.equals("")) {
                proposedOutcome = outcome;
                LOG.info(" ******** outcome condition***********:" + outcome);

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")) {
                    mohonFasa = strService.findMohonFasaKpsn(permohonan.getIdPermohonan(), "keputusan");
                    if (mohonFasa != null) {
                        String kptsn = mohonFasa.getKeputusan().getKod();
                        if (kptsn.equals("L")) {
                            List<Projek> listpr = strService.findSifus(hp.getHakmilik().getIdHakmilik(), null, "Y");
                            for (Projek pr : listpr) {
                                pr.setAktif("T");
                                pr.setInfoAudit(ia2);
                                strService.simpanProjek(pr);
                            }
                            List<Projek> listprM = strService.findSifus(hp.getHakmilik().getIdHakmilik(), null, "M");
                            for (Projek pr : listprM) {
                                pr.setAktif("Y");
                                pr.setTarikhLulus(new Date());
                                pr.setInfoAudit(ia2);
                                strService.simpanProjek(pr);
                            }
                        } else {
                            List<Projek> listprM = strService.findSifus(hp.getHakmilik().getIdHakmilik(), null, "M");
                            for (Projek pr : listprM) {
                                pr.setAktif("B");
                                pr.setInfoAudit(ia2);
                                strService.simpanProjek(pr);
                            }
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS")) {
                    mohonFasa = strService.findMohonFasaKpsn(permohonan.getIdPermohonan(), "keputusan");
                    if (mohonFasa != null) {
                        String kptsn = mohonFasa.getKeputusan().getKod();
                        if (kptsn.equals("L")) {
                            List<Projek> listprM = strService.findSifus(hp.getHakmilik().getIdHakmilik(), null, "M");
                            for (Projek pr : listprM) {
                                pr.setAktif("Y");
                                pr.setTarikhLulus(new Date());
                                pr.setInfoAudit(ia2);
                                strService.simpanProjek(pr);
                            }
                        } else {
                            List<Projek> listprM = strService.findSifus(hp.getHakmilik().getIdHakmilik(), null, "M");
                            for (Projek pr : listprM) {
                                pr.setAktif("B");
                                pr.setInfoAudit(ia2);
                                strService.simpanProjek(pr);
                            }
                        }
                    }
                }
            }
            LOG.info(" ******** outcome ***********:" + outcome);
        } catch (WorkflowException ex) {
            LOG.info(" ******Exception** outcome ***********:" + outcome);
            LOG.error(ex.getMessage());
            return proposedOutcome;
        }
        LOG.info(" ******** proposedOutcome ***********:" + proposedOutcome);
        return proposedOutcome;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        FasaPermohonan mohonFasa = new FasaPermohonan();

        Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(" ******** stage ***********:" + stage);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")) {
                mohonFasa = strService.findMohonFasaKpsn(permohonan.getIdPermohonan(), "keputusan");
                if (stage.contentEquals("keputusan")) {
                    if (mohonFasa != null) {
                        String kptsn = mohonFasa.getKeputusan().getKod();
                        value = kptsn;
                    }
                }
            }
        }
        return value;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}

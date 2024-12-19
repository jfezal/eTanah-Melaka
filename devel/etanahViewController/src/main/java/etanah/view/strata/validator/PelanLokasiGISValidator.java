/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.gis.PelanGIS;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class PelanLokasiGISValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(KeputusanMMKValidator.class);

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = context.getPengguna();

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(peng);

        PelanGIS pelanGIS = strService.findPelanByIdPermohonan(permohonan.getIdPermohonan());
        if (pelanGIS != null) {
            String dokumenPath = conf.getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + pelanGIS.getPelanTif();
            LOG.info("path : " + dokumenPath);
            try {
                comm.savePelanLokasi(dokumenPath, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);
                LOG.info("simpan pelan lokasi berjaya.");
            } catch (Exception ex) {
                LOG.error(ex);
            }
        } else {
        }

        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            if (permohonan.getKodUrusan().getKod().equals("P14A") || permohonan.getKodUrusan().getKod().equals("P22B")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    withDrawIdPermohonanSblm(permohonan);
                }
            }
        }
        return proposedOutcome;
    }

    public void withDrawIdPermohonanSblm(Permohonan permohonan) {
        Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
        try {
            List senaraiTask = WorkFlowService.queryTasksByAdmin(permohonanSblm.getIdPermohonan());
            LOG.debug("----senaraiTask----:" + senaraiTask.size());
            if (senaraiTask.isEmpty()) {
                LOG.info("-----idSblm tidak di jumpai-----");
            } else {
                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    LOG.info("-----idSblm di jumpai-----");
                    LOG.info(task);
                    taskId = task.getSystemAttributes().getTaskId();
                }
            }
            WorkFlowService.withdrawTask(taskId);
            permohonanSblm.setStatus("BP");
            lelongService.saveOrUpdate(permohonanSblm);
            LOG.debug("----Withdraw IdSblm success----:");
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

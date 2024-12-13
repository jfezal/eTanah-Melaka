/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusUlasanJabatanTeknikal;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.UlasanJabatanTeknikal;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.common.TaskDebugService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanah;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
public class JKSMNValidator implements StageListener {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private TaskDebugService ts;
    private PermohonanKertas mohonKertas;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG;
    private List<PermohonanKelompok> senaraiPermohonanKelompok;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();
        IWorkflowContext ctxW = null;
        senaraiLaporanKandunganPerakuanPTG = new ArrayList<PermohonanKertasKandungan>();
        mohonKertas = pelupusanService.findPermohonanKertasByIdPermohonanNKodDokumen(permohonan.getIdPermohonan(), "JKM");
        if (permohonan.getKodUrusan().getKod().equals("PCRG") || permohonan.getKodUrusan().getKod().equals("LPJH")) {
            senaraiLaporanKandunganPerakuanPTG = pelupusanService.findByIdLapor(mohonKertas.getIdKertas(), 8);
        }

//        Boolean flagSubmit = Boolean.TRUE;
        LOG.info("--------------senaraiLaporanKandunganPerakuanPTG------------------------" + senaraiLaporanKandunganPerakuanPTG.size());
        if (senaraiLaporanKandunganPerakuanPTG.isEmpty()) {
            LOG.info("bil 8 empty");
            context.addMessage("Sila Masukkan Perakuan Pengarah Tanah Dan Galian.");
            LOG.info("lalu message");
//                        flagSubmit = Boolean.FALSE;
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}

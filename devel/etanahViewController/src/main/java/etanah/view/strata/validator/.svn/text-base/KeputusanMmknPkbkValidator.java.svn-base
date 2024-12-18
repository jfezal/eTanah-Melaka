/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import etanah.view.stripes.pengambilan.sek4.validator.*;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.ref.strata.pkbk.RefPeringkat;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.strata.integrationflow.PkbkIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class KeputusanMmknPkbkValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(LaporanTanahPTGValidator.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PkbkIntegrationFlowService pkbkIntegrationFlowService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        RefPeringkat ref = new RefPeringkat();
        pkbkIntegrationFlowService.completeTask(ref.REKOD_KPSN_MMK_PKBK, context.getPermohonan(), context.getPengguna());

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

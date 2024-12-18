/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.view.stripes.pelupusan.common.PLPTReportService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author john
 */
public class SemakSemulaValidator implements StageListener {

    @Inject
    PLPTReportService pLPTReportService;

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
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();
        String stageId = context.getStageName();
        HashMap reportMap = new HashMap();
        if ("semak_surat_5a".equals(stageId)) {
            reportMap.put("reportName1", "DISB5A_MLK.rdf");
            reportMap.put("reportKod1", "N5A");
            reportMap.put("reportName1", "DISSrtKelulusan_MLK.rdf");
            reportMap.put("reportKod1", "N5A");
            pLPTReportService.generateReport(permohonan, reportMap, pengguna);
        } else if ("terima_keputusan_ptmmk".equals(stageId)) {
            if (context.getCurrentOutcome().equals("L")) {
                reportMap.put("reportName1", "DISSrtKpsnMMKNPBPTGL_MLK.rdf");
                reportMap.put("reportKod1", "SKM");

            } else {
                reportMap.put("reportName1", "DISSrtKpsnTolakMMKNPBPTGL_MLK.rdf");
                reportMap.put("reportKod1", "SKM");

            }
            pLPTReportService.generateReport(permohonan, reportMap, pengguna);
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
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
    }

}

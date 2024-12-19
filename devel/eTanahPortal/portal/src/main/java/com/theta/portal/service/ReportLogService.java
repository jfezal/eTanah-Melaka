/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskReportLogDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportLog;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.wideplay.warp.persist.Transactional;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class ReportLogService {

    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    HelpdeskReportLogDAO helpdeskReportLogDAO;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    @Inject
    StageService stageService;

    @Transactional
    public void saveRecord(Long reportId, String note, UserTable userId, String stage) {

        HelpdeskReport report = helpdeskReportDAO.findById(reportId);
        HelpdeskReportLog reportLog = new HelpdeskReportLog();
        reportLog.setDateCreated(new Date());
        reportLog.setHelpdeskReport(report);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById(stage);
        reportLog.setKodStage(kodStage);
        reportLog.setNote(note);
        reportLog.setUserId(userId);
        helpdeskReportLogDAO.save(reportLog);
        HelpdeskStage helpdDeskstage = helpdeskStageDAO.findById(reportId);
        UserTable assignee = helpdDeskstage.getCreatedBy();
        helpdeskStageDAO.delete(helpdDeskstage);
        if (stage.equals("PSC")) {
            HelpdeskTechnical tech = stageService.findbyReportId(reportId);
            stageService.nextStage(kodStage, reportId, tech.getTechnicalId() + "",userId,tech.getTechnicalOfficerId());
        } else if(stage.equals("PSU")) {
            HelpdeskTechnical tech = stageService.findbyReportId(reportId);
            stageService.nextStage(kodStage, reportId, tech.getTechnicalId() + "",userId,assignee);
        }else if(stage.equals("PST")) {
//            HelpdeskTechnical tech = stageService.findbyReportId(reportId);
assignee = report.getReportBy();
            stageService.nextStage(kodStage, reportId, reportId + "",userId,assignee);
        }else if(stage.equals("PSA")) {
//            HelpdeskTechnical tech = stageService.findbyReportId(reportId);
assignee = report.getReportBy();
            stageService.nextStage(kodStage, reportId, reportId + "",userId,assignee);
        }else if(stage.equals("PSK")) {
            HelpdeskContractor con = stageService.findHelpdeskContractorByReportId(reportId);
            stageService.nextStage(kodStage, reportId, con.getHelpdeskContractorId() + "",userId,assignee);
        }else if(stage.equals("PSAL")) {
//            HelpdeskTechnical tech = stageService.findbyReportId(reportId);
            stageService.nextStage(kodStage, reportId, reportId + "",userId,assignee);
        }

    }
}

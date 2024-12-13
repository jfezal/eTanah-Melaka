/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskContractorDocumentsDAO;
import com.theta.portal.dao.HelpdeskHistoryDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.HelpdeskTechnicalDAO;
import com.theta.portal.dao.RefHelpdeskHistoryTypeDAO;
import com.theta.portal.dao.RefHelpdeskReportStatusDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskHistory;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskHistoryType;
import com.theta.portal.model.RefHelpdeskReportStatus;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.session.LoginSession;
import com.theta.portal.stripes.util.SendEmelService;
import com.wideplay.warp.persist.Transactional;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
public class StageService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    HelpdeskTechnicalDAO helpdeskTechnicalDAO;
    @Inject
    HelpdeskHistoryDAO helpdeskHistoryDAO;
    @Inject
    RefHelpdeskHistoryTypeDAO refHelpdeskHistoryTypeDAO;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO;
    @Inject
    AduanService aduanService;
    @Inject
    SendEmelService sendEmelService;
    @Inject
    HelpdeskContractorDocumentsDAO helpdeskContractorDocumentsDAO;

    @Transactional
    public boolean nextStage(RefHelpdeskStage kodStage, Long reportId, String param, UserTable createBy, UserTable assignee) {
        boolean a = true;
        boolean ristricted = false;
//        UserTable assignee;
        HelpdeskStage stage = helpdeskStageDAO.findById(reportId);
        if (stage != null) {
            if (stage.getUserId() != null && !stage.getUserId().getUserId().equals(createBy.getUserId())) {
                ristricted = true;
                a = false;
            }
        } else {
            stage = new HelpdeskStage();
        }
        if (!ristricted) {
            HelpdeskReport helpdeskReport = helpdeskReportDAO.findById(reportId);
            stage.setKodStage(kodStage);
            stage.setReportId(reportId);
            stage.setHelpdeskReport(helpdeskReport);
            stage.setParamValue(param);
            stage.setDateCreated(new Date());
            stage.setUserId(assignee);
            stage.setCreatedBy(createBy);

            helpdeskStageDAO.save(stage);
            RefHelpdeskHistoryType historyType = getHistoryType(kodStage);
            HelpdeskHistory his = history(createBy, helpdeskReport, reportId, historyType);
            helpdeskHistoryDAO.save(his);
            if ("US".equals(kodStage.getKod())) {
                try{
                sendEmelConfirmation(helpdeskReport, assignee);
                }catch (Exception es){
                    System.err.println("dddd"+es.toString());
                }}
            else if ("PTS".equals(kodStage.getKod())) {
                try{
                sendEmelConfirmation(helpdeskReport, assignee);
                }catch (Exception es){
                    System.err.println("dddd"+es.toString());
                }}

        }
        return a;
    }

    @Transactional
    public void close(RefHelpdeskStage kodStage, Long reportId, UserTable closedBy) {
//        boolean a = true;
        HelpdeskReport report = helpdeskReportDAO.findById(reportId);
        RefHelpdeskReportStatus status = refHelpdeskReportStatusDAO.findById(2L);
        report.setDateClosed(new Date());
        report.setStatus(status);
        report.setClosedBy(closedBy);
        helpdeskReportDAO.save(report);
        HelpdeskStage stage = helpdeskStageDAO.findById(reportId);
        RefHelpdeskHistoryType historyType = getHistoryType(kodStage);
        HelpdeskHistory his = history(closedBy, report, reportId, historyType);
        helpdeskHistoryDAO.save(his);
        if (stage != null) {
        } else {
            stage = new HelpdeskStage();
        }
        helpdeskStageDAO.delete(stage);
//        return a;
    }

    @Transactional
    public void selesai1(Long reportId) {
//        boolean a = true;
        HelpdeskStage stage = helpdeskStageDAO.findById(reportId);

        if (stage != null) {
        } else {
            stage = new HelpdeskStage();
        }
        helpdeskStageDAO.delete(stage);
//        return a;
    }

    public HelpdeskHistory history(UserTable createBy, HelpdeskReport reportId, Long refId, RefHelpdeskHistoryType historyType) {
        HelpdeskHistory his = new HelpdeskHistory();
        his.setCreateBy(createBy);
        his.setCreateDate(new Date());
        his.setHistoryType(historyType);
        his.setReportId(reportId);
        his.setRefId(refId);
        return his;
    }

    public HelpdeskTechnical findbyReportId(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskTechnical p "
                + "where p.reportId.reportId = :reportId");
        query.setParameter("reportId", reportId);
        return (HelpdeskTechnical) query.uniqueResult();
    }

    public HelpdeskContractor findHelpdeskContractorByReportId(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskContractor p "
                + "where p.reportId.reportId = :reportId");
        query.setParameter("reportId", reportId);
            return (HelpdeskContractor) query.uniqueResult();
    }

    private RefHelpdeskHistoryType getHistoryType(RefHelpdeskStage kodStage) {
        //1=baru, 2=Pegawai, 3=kontraktor, 
        //4=   Pegawai Penyelesai Tugasan, 5 = Selesai, 6=Dalam Proses , 7=Kontraktor Luar, 
        //8=Pulang Semula, 9=User Semak, 10=Pulang Aduan
        RefHelpdeskHistoryType type = null;

        if (kodStage != null) {
            int a = kodStage.getRef();
            switch (a) {
//                case "PSU":
//                case "PSA":
//                case "PSK":
//                case "PST":
                case 9:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 10:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 11:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 12:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 13:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 8:
                    type = refHelpdeskHistoryTypeDAO.findById(8L);
                    break;
                case 5:
                    type = refHelpdeskHistoryTypeDAO.findById(5L);
                    break;
                case 4:
                    type = refHelpdeskHistoryTypeDAO.findById(4L);
                    break;
                case 6:
                    type = refHelpdeskHistoryTypeDAO.findById(6L);
                    break;
                case 2:
                    type = refHelpdeskHistoryTypeDAO.findById(2L);
                    break;
                case 3:
                    type = refHelpdeskHistoryTypeDAO.findById(3L);
                    break;
                default:
                    break;
            }
        }
        return type;
    }

    public void agihSemula(long reportId, UserTable pengguna) {

        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = aduanService.findHelpdeskAssignByReportID(String.valueOf(reportId));

        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("AG");
        nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getAssignId(), pengguna, null);
        tx.commit();
    }

    @Transactional
    public void claimTugasan(UserTable user, HelpdeskReport report) {
        HelpdeskStage stage = helpdeskStageDAO.findById(report.getReportId());
        stage.setUserId(user);
        helpdeskStageDAO.save(stage);
    }

    @Transactional
    public void updateNoteSelesai(String nota, Long reportId) {
        HelpdeskReport report = helpdeskReportDAO.findById(reportId);
        report.setNote(nota);
        helpdeskReportDAO.saveOrUpdate(report);
    }

    private void sendEmelConfirmation(HelpdeskReport helpdeskReport, UserTable assignee) {
        HelpdeskTechnical helpdeskTechnical = findbyReportId(helpdeskReport.getReportId());;
        sendEmelService.sendMailConfirmation(helpdeskReport, helpdeskTechnical, assignee);
    }
    
    @Transactional
    public void deleteAttchmnt(HelpdeskContractorDocuments contractorDocuments) {
       helpdeskContractorDocumentsDAO.delete(contractorDocuments);
    }
}

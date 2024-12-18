/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskAssignDAO;
import com.theta.portal.dao.HelpdeskContractorDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskReportDocumentsDAO;
import com.theta.portal.dao.HelpdeskTechnicalDAO;
import com.theta.portal.dao.HelpdeskTechnicalDocumentsDAO;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportDocuments;
import com.theta.portal.model.HelpdeskReportLog;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.HelpdeskTechnicalDocuments;
import com.wideplay.warp.persist.Transactional;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class AduanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    HelpdeskReportDocumentsDAO helpdeskReportDocumentsDAO;
    @Inject
    HelpdeskTechnicalDAO helpdeskTechnicalDAO;
    @Inject
    HelpdeskAssignDAO helpdeskAssignDAO;
    @Inject
    HelpdeskTechnicalDocumentsDAO helpdeskTechnicalDocumentsDAO;
    @Inject
    HelpdeskContractorDAO helpdeskContractorDAO;

    @Transactional
    public HelpdeskReport saveHelpdeskReport(HelpdeskReport report) {
        return helpdeskReportDAO.saveOrUpdate(report);
    }

    @Transactional
    public HelpdeskReportDocuments saveHelpdeskReportDocuments(HelpdeskReportDocuments documents) {
        return helpdeskReportDocumentsDAO.saveOrUpdate(documents);
    }

    @Transactional
    public HelpdeskTechnical saveHelpdeskTechnical(HelpdeskTechnical technicalReport) {
        return helpdeskTechnicalDAO.saveOrUpdate(technicalReport);
    }

    @Transactional
    public HelpdeskTechnicalDocuments saveHelpdeskTechnicalDocuments(HelpdeskTechnicalDocuments technicalReportDocuments) {
        return helpdeskTechnicalDocumentsDAO.saveOrUpdate(technicalReportDocuments);
    }

    @Transactional
    public HelpdeskAssign saveHelpdeskAssign(HelpdeskAssign assign) {
        return helpdeskAssignDAO.saveOrUpdate(assign);
    }

    public List<HelpdeskTechnical> listHelpdeskTechnicalByReportId(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskTechnical p "
                + "where p.reportId.reportId = :reportId");
        query.setParameter("reportId", reportId);
        return query.list();
    }

    public List<HelpdeskAssign> listHelpdeskAssignByReportId(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskAssign p "
                + "where p.reportId.reportId = :reportId");
        query.setLong("reportId", reportId);
        return query.list();
    }

    public HelpdeskTechnical findByReportID(Long reportId) {
Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskTechnical p "
                + "where p.reportId.reportId = :reportId");
        query.setLong("reportId", reportId);
        return (HelpdeskTechnical) query.uniqueResult();    }

    @Transactional
    public HelpdeskContractor saveHelpdeskContractor(HelpdeskContractor contractor) {
        return helpdeskContractorDAO.saveOrUpdate(contractor);
    }

    List<HelpdeskContractor> listHelpdeskKontraktorByReportId(Long reportId) {
        Session session = sessionProvider.get();

        Query query = session.createQuery(
                "select p from HelpdeskContractor p "
                + "where p.reportId.reportId = :reportId");
        query.setLong("reportId", reportId);
        return query.list();
    }

    List<HelpdeskReportLog> listPulangSemulaByReportId(Long reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskReportLog p "
                + "where p.helpdeskReport.reportId = :reportId");
        query.setLong("reportId", reportId);
        return query.list();
    }

    public Boolean setPulangSemula(HelpdeskReport report) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskStage p "
                + "where p.kodStage.kod in ('PSC','PST','PSA','PSU') and p.reportId = :reportId");
        query.setLong("reportId", report.getReportId());
        if (query.list().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public HelpdeskAssign findHelpdeskAssignByReportID(String reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskAssign p "
                + "where p.reportId.reportId = :reportId");
        query.setString("reportId", reportId);
        return (HelpdeskAssign) query.uniqueResult();
    }
    public HelpdeskTechnical findHelpdeskTechnicalByReportID(String reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskTechnical p "
                + "where p.reportId.reportId = :reportId");
        query.setString("reportId", reportId);
        return (HelpdeskTechnical) query.uniqueResult();
    }
    public HelpdeskContractor findHelpdeskContractorByReportID(String reportId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from HelpdeskContractor p "
                + "where p.reportId.reportId = :reportId");
        query.setString("reportId", reportId);
        return (HelpdeskContractor) query.uniqueResult();
    }
    
    

}

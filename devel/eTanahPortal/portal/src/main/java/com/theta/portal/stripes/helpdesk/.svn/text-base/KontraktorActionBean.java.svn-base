/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskContractorDAO;
import com.theta.portal.dao.HelpdeskContractorDocumentsDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskContractorStatusDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskContractorStatus;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.AduanService;
import com.theta.portal.service.StageService;
import com.theta.portal.service.TableService;
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.HelpdeskMainActionBean;
import com.theta.portal.stripes.form.DMSUploadForm;
import com.theta.portal.stripes.form.aduan.AduanForm;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/kontraktor")
public class KontraktorActionBean extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HelpdeskContractorDocumentsDAO helpdeskContractorDocumentsDAO;
    @Inject
    RefHelpdeskContractorStatusDAO refHelpdeskContractorStatusDAO;
    @Inject
    HelpdeskContractorDAO helpdeskContractorDAO;
    @Inject
    TableService tableService;
    @Inject
    AduanService aduanService;
    @Inject
    StageService stageService;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    DMSService dMSService;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    FileBean file2;
    List<DMSUploadForm> listOfDocument;
    List<AduanForm> listAduan;
    List<AduanForm> listAgihan;
    List<AduanForm> listTeknikal;
    HelpdeskContractorDocuments contractorDocuments;

    String keterangan;
    String helpdeskContractorId;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        UserTable user = getContext().getCurrentUser();
        helpdeskContractorId = getContext().getRequest().getParameter("helpdeskContractorId");
        HelpdeskReport report = null;
        HelpdeskContractor contractor = null;
        if (helpdeskContractorId != null && StringUtils.isNotBlank(helpdeskContractorId)) {
            contractor = helpdeskContractorDAO.findById(Long.valueOf(helpdeskContractorId));
            keterangan = contractor.getDescription();
            report = contractor.getReportId();
            setReportId(report.getReportId());
            listAduan = tableService.listAduan(report);
            listAgihan = tableService.listAgihan(report);
            listTeknikal = tableService.listTeknikal(report);
            setListPulangSemula(tableService.listPulangSemula(report));
            listOfDocument = tableService.listOfDocument(contractor.getHelpdeskContractorId());
            setPulangsemula(aduanService.setPulangSemula(report));
            stageService.claimTugasan(user, report);
        }

        setVendor(Boolean.TRUE);

    }

    @DefaultHandler
    public Resolution welcome() {

        return new JSP("helpdesk/kontraktor.jsp");
    }

    public Resolution simpan() {
        String helpdeskContractorId = getContext().getRequest().getParameter("helpdeskContractorId");

        save("simpan");
        return new RedirectResolution(KontraktorActionBean.class).addParameter("helpdeskContractorId", helpdeskContractorId);
    }

    public Resolution saveDocuments() throws IOException {
        save("simpan");

        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        HelpdeskContractor contractor = null;
        HelpdeskContractorDocuments contractorDocuments = null;
        String helpdeskContractorId = getContext().getRequest().getParameter("helpdeskContractorId");
        if (helpdeskContractorId != null && StringUtils.isNotBlank(helpdeskContractorId)) {
            contractor = helpdeskContractorDAO.findById(Long.valueOf(helpdeskContractorId));
            contractorDocuments = new HelpdeskContractorDocuments();
            contractorDocuments.setHelpdeskContractorId(contractor);
            contractorDocuments.setContentType(file2.getContentType());
            contractorDocuments.setFileName(file2.getFileName());
            contractorDocuments.setBinaryData(dMSService.convertFileToByte(file2));
            helpdeskContractorDocumentsDAO.save(contractorDocuments);
        }
        tx.commit();
        return new RedirectResolution(KontraktorActionBean.class).addParameter("helpdeskContractorId", helpdeskContractorId);
    }

    public void save(String status) {
        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        RefHelpdeskContractorStatus kodStatus = null;
        UserTable user = getContext().getCurrentUser();
        String helpdeskContractorId = getContext().getRequest().getParameter("helpdeskContractorId");
        HelpdeskReport report = null;
        HelpdeskContractor contractor = null;
        if (helpdeskContractorId != null && StringUtils.isNotBlank(helpdeskContractorId)) {
            contractor = helpdeskContractorDAO.findById(Long.valueOf(helpdeskContractorId));
            contractor.setDescription(keterangan);
            contractor.setContractorUserId(user);
            kodStatus = refHelpdeskContractorStatusDAO.findById(1L);
            if ("hantar".equals(status)) {
                kodStatus = refHelpdeskContractorStatusDAO.findById(2L);
                contractor.setClosedDate(new Date());

            }
            contractor.setStatus(kodStatus);
            report = contractor.getReportId();
            helpdeskContractorDAO.save(contractor);
        }
        tx.commit();
    }

    public Resolution hantar() {
        UserTable user = getContext().getCurrentUser();
        String reportId = getContext().getRequest().getParameter("reportId");

        save("hantar");
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("PTS");
        HelpdeskTechnical technicalReport = stageService.findbyReportId(Long.valueOf(reportId));
        HelpdeskStage stage = helpdeskStageDAO.findById(Long.valueOf(reportId));
        stageService.nextStage(kodStage, technicalReport.getReportId().getReportId(), "" + technicalReport.getTechnicalId(), user, stage.getCreatedBy());
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution deleteFile() {
        //UserTable user = getContext().getCurrentUser();
        String attId = getContext().getRequest().getParameter("id");
        contractorDocuments = helpdeskContractorDocumentsDAO.findById(Long.valueOf(attId));
        stageService.deleteAttchmnt(contractorDocuments);

        return new RedirectResolution(KontraktorActionBean.class).addParameter("helpdeskContractorId", helpdeskContractorId);
    }

    public List<DMSUploadForm> getListOfDocument() {
        return listOfDocument;
    }

    public void setListOfDocument(List<DMSUploadForm> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    public List<AduanForm> getListAduan() {
        return listAduan;
    }

    public void setListAduan(List<AduanForm> listAduan) {
        this.listAduan = listAduan;
    }

    public List<AduanForm> getListAgihan() {
        return listAgihan;
    }

    public void setListAgihan(List<AduanForm> listAgihan) {
        this.listAgihan = listAgihan;
    }

    public List<AduanForm> getListTeknikal() {
        return listTeknikal;
    }

    public void setListTeknikal(List<AduanForm> listTeknikal) {
        this.listTeknikal = listTeknikal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHelpdeskContractorId() {
        return helpdeskContractorId;
    }

    public void setHelpdeskContractorId(String helpdeskContractorId) {
        this.helpdeskContractorId = helpdeskContractorId;
    }

    public FileBean getFile2() {
        return file2;
    }

    public void setFile2(FileBean file2) {
        this.file2 = file2;
    }

    public HelpdeskContractorDocuments getContractorDocuments() {
        return contractorDocuments;
    }

    public void setContractorDocuments(HelpdeskContractorDocuments contractorDocuments) {
        this.contractorDocuments = contractorDocuments;
    }

}

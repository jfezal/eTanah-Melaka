/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.HelpdeskTechnicalDAO;
import com.theta.portal.dao.HelpdeskTechnicalDocumentsDAO;
import com.theta.portal.dao.RefHelpdeskContractorStatusDAO;
import com.theta.portal.dao.RefHelpdeskReportStatusDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskTechnicalStatusDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.dao.VendorDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskContractorDocuments;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.HelpdeskTechnicalDocuments;
import com.theta.portal.model.RefHelpdeskContractorStatus;
import com.theta.portal.model.RefHelpdeskReportStatus;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.RefHelpdeskTechnicalStatus;
import com.theta.portal.model.UserTable;
import com.theta.portal.model.Vendor;
import com.theta.portal.service.AduanService;
import com.theta.portal.service.StageService;
import com.theta.portal.service.TableService;
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.HelpdeskMainActionBean;
import com.theta.portal.stripes.form.DMSUploadForm;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.form.aduan.AgihanForm;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import com.theta.portal.stripes.util.SendEmelService;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@UrlBinding("/helpdesk/p_teknikal")
public class PTeknikalActionBean extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    DMSService dMSService;
    @Inject
    HelpdeskTechnicalDocumentsDAO helpdeskTechnicalDocumentsDAO;
    @Inject
    UserManager userManager;
    @Inject
    RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO;
    @Inject
    RefHelpdeskTechnicalStatusDAO refHelpdeskTechnicalStatusDAO;
    @Inject
    HelpdeskTechnicalDAO helpdeskTechnicalDAO;
    @Inject
    AduanService aduanService;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    VendorDAO vendorDAO;
    @Inject
    RefHelpdeskContractorStatusDAO refHelpdeskContractorStatusDAO;
    @Inject
    TableService tableService;
    @Inject
    StageService stageService;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    @Inject
    SendEmelService sendEmelService;
    Long helpdeskTypeId;
    Long hardwareTypeId;
    Long submodulTypeId;
    Long itemTypeId;

    String jenisMasalah;
    String subKategori;
    String item;
    String perkara;
    String keterangan;
    String tarikhAduan;
    String vendorId;

    String catatanKeKontraktor;

    FileBean file;
    List<DMSUploadForm> listOfDocument;
    List<AduanForm> listAduan;
    List<AduanForm> listAgihan;
    List<AgihanUserForm> listAgihanKontraktor;

    List<AduanForm> listTeknikal;
    List<AduanForm> listKontraktor;

    String technicalId;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        UserTable user = getContext().getCurrentUser();
        listAduan = new ArrayList<AduanForm>();
        String technicalId = getContext().getRequest().getParameter("technicalId");
        HelpdeskReport report = null;
        HelpdeskTechnical technical = null;
        if (technicalId != null && StringUtils.isNotBlank(technicalId)) {
            technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));
            keterangan = technical.getDescription();
            report = technical.getReportId();
            setReportId(report.getReportId());
            listAduan = tableService.listAduan(report);
            listAgihan = tableService.listAgihan(report);
            listAgihanKontraktor = userManager.listAgihanTugasanKontraktor();
            listTeknikal = tableService.listTeknikal(report);
            listKontraktor = tableService.listKontraktor(report);
            setListPulangSemula(tableService.listPulangSemula(report));
            setPulangsemula(aduanService.setPulangSemula(report));

            listOfDocument = tableService.listOfDocumentTechnical(technical.getTechnicalId());
        }
    }

    @DefaultHandler
    public Resolution welcome() {
        setpTeknikal(Boolean.TRUE);
        return new JSP("helpdesk/teknikal.jsp");
    }

    public Resolution semak() {
        setpTeknikalSemak(Boolean.TRUE);
        return new JSP("helpdesk/teknikal_semak.jsp");
    }

    public Resolution simpan() {
        String technicalId = getContext().getRequest().getParameter("technicalId");

        save();
        return new RedirectResolution(PTeknikalActionBean.class).addParameter("technicalId", technicalId);
    }

    public void save() {
        UserTable user = getContext().getCurrentUser();
        String technicalId = getContext().getRequest().getParameter("technicalId");
        HelpdeskReport report = null;
        HelpdeskTechnical technical = null;
        if (technicalId != null && StringUtils.isNotBlank(technicalId)) {
            technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));
            technical.setClosedDate(new Date());
            technical.setDescription(keterangan);
            RefHelpdeskTechnicalStatus statusTech = refHelpdeskTechnicalStatusDAO.findById(1L);
            technical.setStatus(statusTech);
            technical = aduanService.saveHelpdeskTechnical(technical);

            report = technical.getReportId();
            RefHelpdeskReportStatus status = null;
            status = refHelpdeskReportStatusDAO.findById(3L);
            report.setStatus(status);
            report.setModifiedBy(user);
            report.setModfiedDate(new Date());
            report = aduanService.saveHelpdeskReport(report);

        }

    }

    public Resolution selesai() {
        UserTable user = getContext().getCurrentUser();
        String technicalId = getContext().getRequest().getParameter("technicalId");
        HelpdeskReport report = null;
        HelpdeskTechnical technical = null;
        if (technicalId != null && StringUtils.isNotBlank(technicalId)) {
            technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));
            technical.setClosedDate(new Date());
            technical.setDescription(keterangan);
            RefHelpdeskTechnicalStatus statusTech = refHelpdeskTechnicalStatusDAO.findById(1L);
            technical.setStatus(statusTech);
            technical = aduanService.saveHelpdeskTechnical(technical);

            report = technical.getReportId();
            RefHelpdeskReportStatus status = null;
            status = refHelpdeskReportStatusDAO.findById(3L);
            report.setStatus(status);
            report.setModifiedBy(user);
            report.setModfiedDate(new Date());
            report = aduanService.saveHelpdeskReport(report);
            RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("US");
//            HelpdeskStage stage = helpdeskStageDAO.findById(report.getReportId());
            stageService.nextStage(kodStage, report.getReportId(), "" + report.getReportId(), user, report.getReportBy());
        }

        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution saveDocuments() throws IOException {
        Session session = sessionProvider.get();

        String technicalId = getContext().getRequest().getParameter("technicalId");
        Transaction tx = session.beginTransaction();
        HelpdeskTechnical technical = null;
        HelpdeskTechnicalDocuments helpdeskTechnicalDocuments = null;
        if (technicalId != null && StringUtils.isNotBlank(technicalId)) {
            technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));
            helpdeskTechnicalDocuments = new HelpdeskTechnicalDocuments();
            helpdeskTechnicalDocuments.setTechnicalId(technical);
            helpdeskTechnicalDocuments.setContentType(file.getContentType());
            helpdeskTechnicalDocuments.setFileName(file.getFileName());
            helpdeskTechnicalDocuments.setBinaryData(dMSService.convertFileToByte(file));
            helpdeskTechnicalDocumentsDAO.save(helpdeskTechnicalDocuments);
        }
        tx.commit();
        return new RedirectResolution(PTeknikalActionBean.class).addParameter("technicalId", technicalId);
    }

    public Resolution tolakAduan() {
        UserTable user = getContext().getCurrentUser();
        String technicalId = getContext().getRequest().getParameter("technicalId");
        HelpdeskTechnical technical = null;
        technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));

        HelpdeskAssign assign = aduanService.findHelpdeskAssignByReportID("" + technical.getReportId().getReportId());

        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("AG");
        stageService.nextStage(kodStage, technical.getReportId().getReportId(), "" + assign.getAssignId(), user, null);
        return new RedirectResolution(HelpdeskMainActionBean.class);

    }

    public Resolution agihKontraktor() {

        UserTable user = getContext().getCurrentUser();
        Vendor vendor = vendorDAO.findById(Long.valueOf(vendorId));
        String technicalId = getContext().getRequest().getParameter("technicalId");
        HelpdeskReport report = null;
        HelpdeskTechnical technical = null;
        if (technicalId != null && StringUtils.isNotBlank(technicalId)) {
            try {
                technical = helpdeskTechnicalDAO.findById(Long.valueOf(technicalId));
                technical.setClosedDate(new Date());
                technical.setDescription(keterangan);
                technical.setDescHdassign(catatanKeKontraktor);
                RefHelpdeskTechnicalStatus statusTech = refHelpdeskTechnicalStatusDAO.findById(1L);
                technical.setStatus(statusTech);
                technical = aduanService.saveHelpdeskTechnical(technical);

                report = technical.getReportId();
                RefHelpdeskReportStatus status = null;
                status = refHelpdeskReportStatusDAO.findById(3L);
                report.setStatus(status);
                report.setModifiedBy(user);
                report.setModfiedDate(new Date());
                report = aduanService.saveHelpdeskReport(report);

                RefHelpdeskContractorStatus statusC = refHelpdeskContractorStatusDAO.findById(1L);
                HelpdeskContractor contractor = stageService.findHelpdeskContractorByReportId(report.getReportId());
                if (contractor != null) {
                } else {
                    contractor = new HelpdeskContractor();
                }
                contractor.setAssignDate(new Date());
                contractor.setReportId(report);
                contractor.setTechnicalId(technical);
                contractor.setStatus(statusC);
                contractor.setContractorId(vendor.getVendorId());
                contractor = aduanService.saveHelpdeskContractor(contractor);
                RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("VEN");
                stageService.nextStage(kodStage, contractor.getReportId().getReportId(), "" + contractor.getHelpdeskContractorId(), user, null);
                sendEmelService.mail(report, vendor.getEmail());
            } catch (IOException ex) {
                Logger.getLogger(PTeknikalActionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(PTeknikalActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution pulangSemulaKontractor() {
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("PSC");
//        stageService.nextStage(kodStage, contractor.getReportId().getReportId(), "" + contractor.getHelpdeskContractorId());

        return new RedirectResolution(PTeknikalActionBean.class);

    }

    public Resolution pulangSemulaHelpdesk() {
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("PSH");
//        stageService.nextStage(kodStage, contractor.getReportId().getReportId(), "" + contractor.getHelpdeskContractorId());

        return new RedirectResolution(PTeknikalActionBean.class);

    }

    public HelpdeskReportDAO getHelpdeskReportDAO() {
        return helpdeskReportDAO;
    }

    public void setHelpdeskReportDAO(HelpdeskReportDAO helpdeskReportDAO) {
        this.helpdeskReportDAO = helpdeskReportDAO;
    }

    public DMSService getdMSService() {
        return dMSService;
    }

    public void setdMSService(DMSService dMSService) {
        this.dMSService = dMSService;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public RefHelpdeskReportStatusDAO getRefHelpdeskReportStatusDAO() {
        return refHelpdeskReportStatusDAO;
    }

    public void setRefHelpdeskReportStatusDAO(RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO) {
        this.refHelpdeskReportStatusDAO = refHelpdeskReportStatusDAO;
    }

    public RefHelpdeskTechnicalStatusDAO getRefHelpdeskTechnicalStatusDAO() {
        return refHelpdeskTechnicalStatusDAO;
    }

    public void setRefHelpdeskTechnicalStatusDAO(RefHelpdeskTechnicalStatusDAO refHelpdeskTechnicalStatusDAO) {
        this.refHelpdeskTechnicalStatusDAO = refHelpdeskTechnicalStatusDAO;
    }

    public AduanService getAduanService() {
        return aduanService;
    }

    public void setAduanService(AduanService aduanService) {
        this.aduanService = aduanService;
    }

    public UserTableDAO getUserTableDAO() {
        return userTableDAO;
    }

    public void setUserTableDAO(UserTableDAO userTableDAO) {
        this.userTableDAO = userTableDAO;
    }

    public Long getHelpdeskTypeId() {
        return helpdeskTypeId;
    }

    public void setHelpdeskTypeId(Long helpdeskTypeId) {
        this.helpdeskTypeId = helpdeskTypeId;
    }

    public Long getHardwareTypeId() {
        return hardwareTypeId;
    }

    public void setHardwareTypeId(Long hardwareTypeId) {
        this.hardwareTypeId = hardwareTypeId;
    }

    public Long getSubmodulTypeId() {
        return submodulTypeId;
    }

    public void setSubmodulTypeId(Long submodulTypeId) {
        this.submodulTypeId = submodulTypeId;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getJenisMasalah() {
        return jenisMasalah;
    }

    public void setJenisMasalah(String jenisMasalah) {
        this.jenisMasalah = jenisMasalah;
    }

    public String getSubKategori() {
        return subKategori;
    }

    public void setSubKategori(String subKategori) {
        this.subKategori = subKategori;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTarikhAduan() {
        return tarikhAduan;
    }

    public void setTarikhAduan(String tarikhAduan) {
        this.tarikhAduan = tarikhAduan;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

    public List<AgihanUserForm> getListAgihanKontraktor() {
        return listAgihanKontraktor;
    }

    public void setListAgihanKontraktor(List<AgihanUserForm> listAgihanKontraktor) {
        this.listAgihanKontraktor = listAgihanKontraktor;
    }

    public String getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(String technicalId) {
        this.technicalId = technicalId;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public List<AduanForm> getListTeknikal() {
        return listTeknikal;
    }

    public void setListTeknikal(List<AduanForm> listTeknikal) {
        this.listTeknikal = listTeknikal;
    }

    public List<AduanForm> getListKontraktor() {
        return listKontraktor;
    }

    public void setListKontraktor(List<AduanForm> listKontraktor) {
        this.listKontraktor = listKontraktor;
    }

    public String getCatatanKeKontraktor() {
        return catatanKeKontraktor;
    }

    public void setCatatanKeKontraktor(String catatanKeKontraktor) {
        this.catatanKeKontraktor = catatanKeKontraktor;
    }

}

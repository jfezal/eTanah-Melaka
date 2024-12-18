/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskAssignDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.RefHelpdeskReportStatusDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskTechnicalStatusDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskReportStatus;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.RefHelpdeskTechnicalStatus;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.AduanService;
import com.theta.portal.service.StageService;
import com.theta.portal.service.TableService;
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.HelpdeskMainActionBean;
import com.theta.portal.stripes.form.DMSUploadForm;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/agih_semak")
public class HelpDeskActionBean extends BaseActionBean {

    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    DMSService dMSService;
    @Inject
    UserManager userManager;
    @Inject
    RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO;
    @Inject
    RefHelpdeskTechnicalStatusDAO refHelpdeskTechnicalStatusDAO;
    @Inject
    HelpdeskAssignDAO helpdeskAssignDAO;
    @Inject
    AduanService aduanService;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    StageService stageService;
    @Inject
    TableService tableService;

    Long helpdeskTypeId;
    Long hardwareTypeId;
    Long submodulTypeId;
    Long itemTypeId;
    String vendorId;
    String jenisMasalah;
    String subKategori;
    String item;
    String perkara;
    String keterangan;
    String tarikhAduan;
    String userId;

    List<DMSUploadForm> listOfDocument;
    List<AduanForm> listAduan;
    List<AgihanUserForm> listAgihanUser;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        UserTable user = getContext().getCurrentUser();

        String assignId = getContext().getRequest().getParameter("assignId");
        HelpdeskAssign assign = null;
        listAduan = new ArrayList<AduanForm>();
        HelpdeskReport report = null;
        if (assignId != null && StringUtils.isNotBlank(assignId)) {
            assign = helpdeskAssignDAO.findById(Long.valueOf(assignId));
            report = assign.getReportId();
            AduanForm form = new AduanForm();
            form.setNamaPelapor(report.getEmpId().getEmployeeName());
            form.setUnit(report.getEmpId().getEmployeeDepartmentId().getDepartmentName());
            form.setPerkara(report.getTitle());
            form.setKeterangan(report.getDescription());
            form.setJenisMasalah(report.getTypeReport().getHelpdeskType());
//            helpdeskTypeId = report.getTypeReport().getHelpdeskTypeId();
            if (report.getHardwareType() != null) {
                hardwareTypeId = report.getHardwareType().getHardwareTypeId();
            }
            if (report.getModulType() != null) {
                submodulTypeId = report.getModulType().getSubmodulTypeId();
            }
            itemTypeId = report.getItemType().getItemTypeId();
            List<DMSUploadForm> list = dMSService.findDocumentByReportId(report.getReportId());
            form.setListOfDocument(list);
            listAduan.add(form);
            setReportId(report.getReportId());
            setAgih(Boolean.TRUE);
            setListPulangSemula(tableService.listPulangSemula(report));
            setPulangsemula(aduanService.setPulangSemula(report));
        }

        listAgihanUser = userManager.listAgihanTugasanUser("" + user.getUserPtdOfficeId());
    }

    @DefaultHandler
    public Resolution welcome() {

        return new JSP("helpdesk/agih_semak.jsp");
    }

    public Resolution tugasanBelumSelesai() {
        String userId = getContext().getRequest().getParameter("userId");
        String tugasan = userManager.findTugasanBelumSelesai(userId) + "";
        return new StreamingResolution("text/plain", tugasan);
    }

    public Resolution simpan() {
        UserTable user = getContext().getCurrentUser();
        UserTable userAssign = userTableDAO.findById(Long.valueOf(userId));
        String assignId = getContext().getRequest().getParameter("assignId");
        HelpdeskAssign assign = null;
        listAduan = new ArrayList<AduanForm>();
        HelpdeskReport report = null;
        if (assignId != null && StringUtils.isNotBlank(assignId)) {
            assign = helpdeskAssignDAO.findById(Long.valueOf(assignId));
            report = assign.getReportId();
            RefHelpdeskReportStatus status = null;
            status = refHelpdeskReportStatusDAO.findById(3L);
            report.setStatus(status);
            report.setModifiedBy(user);
            report.setModfiedDate(new Date());
            report = aduanService.saveHelpdeskReport(report);

            RefHelpdeskTechnicalStatus statusTech = refHelpdeskTechnicalStatusDAO.findById(1L);
            HelpdeskTechnical technicalReport = stageService.findbyReportId(report.getReportId());
            if (technicalReport == null) {
                technicalReport = new HelpdeskTechnical();
            } else {

            }
            technicalReport.setAssignDate(new Date());
            technicalReport.setReportId(report);
            technicalReport.setTechnicalOfficerId(userAssign);
            technicalReport.setStatus(statusTech);
//            technicalReport.setDescHdassign(keterangan);
            technicalReport = aduanService.saveHelpdeskTechnical(technicalReport);

            assign.setDescription(keterangan);
            assign.setTechnicalOfficerId(userAssign);
            assign.setTechnicalReportId(technicalReport);
            assign.setCreateBy(user);
            assign.setCreateDate(new Date());
            assign.setAssignDate(new Date());
            assign = aduanService.saveHelpdeskAssign(assign);
            RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("PT");
            stageService.nextStage(kodStage, technicalReport.getReportId().getReportId(), "" + technicalReport.getTechnicalId(), user, userAssign);

        }
        return new RedirectResolution(HelpdeskMainActionBean.class);
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

    public List<AgihanUserForm> getListAgihanUser() {
        return listAgihanUser;
    }

    public void setListAgihanUser(List<AgihanUserForm> listAgihanUser) {
        this.listAgihanUser = listAgihanUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

}

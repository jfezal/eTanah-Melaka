/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskReportDocumentsDAO;
import com.theta.portal.dao.RefHardwareTypeDAO;
import com.theta.portal.dao.RefHelpdeskReportStatusDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskTypeDAO;
import com.theta.portal.dao.RefItemTypeDAO;
import com.theta.portal.dao.RefSubmodulTypeDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.Employee;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportDocuments;
import com.theta.portal.model.RefHardwareType;
import com.theta.portal.model.RefHelpdeskReportStatus;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.AduanService;
import com.theta.portal.service.StageService;
import com.theta.portal.service.TableService;
import com.theta.portal.service.UserService;
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.HelpdeskMainActionBean;
import com.theta.portal.stripes.form.DMSUploadForm;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/daftar_aduan_manual")
public class DaftarAduanManualActionBean extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
     @Inject
    RefHardwareTypeDAO refHardwareTypeDAO;
    @Inject
    RefHelpdeskTypeDAO refHelpdeskTypeDAO;
    @Inject
    RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO;
    @Inject
    RefSubmodulTypeDAO refSubmodulTypeDAO;
    @Inject
    RefItemTypeDAO refItemTypeDAO;
    @Inject
    DMSService dMSService;
    @Inject
    AduanService aduanService;
    @Inject
    UserManager userManager;
    @Inject
    StageService stageService;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
    @Inject
    HelpdeskReportDocumentsDAO helpdeskReportDocumentsDAO;
    @Inject
    UserService userService;
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    TableService tableService;
    Long reportId;
    Long helpdeskTypeId;
    Long hardwareTypeId;
    Long submodulTypeId;
    Long itemTypeId;
    Long pelaporId;

    String jenisMasalah;
    String subKategori;
    String item;
    String perkara;
    String keterangan;
    String tarikhAduan;

    List<DMSUploadForm> listOfDocument;
    FileBean file;
    FileBean file2;
    List<Employee> listPelapor;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        String reportId = getContext().getRequest().getParameter("reportId");
        HelpdeskReport report = null;
        UserTable user = getContext().getCurrentUser();

        if (reportId != null && StringUtils.isNotBlank(reportId)) {
            report = helpdeskReportDAO.findById(Long.valueOf(reportId));
            perkara = report.getTitle();
            keterangan = report.getDescription();

            helpdeskTypeId = report.getTypeReport().getHelpdeskTypeId();
            hardwareTypeId = report.getHardwareType().getHardwareTypeId();
            submodulTypeId = report.getModulType().getSubmodulTypeId();
            itemTypeId = report.getItemType().getItemTypeId();
            listOfDocument = dMSService.findDocumentByReportId(Long.valueOf(reportId));

            setListPulangSemula(tableService.listPulangSemula(report));
            setPulangsemula(aduanService.setPulangSemula(report));
        }

        listPelapor = userService.findListEmployee(user.getUserPtdOfficeId());
    }

    @DefaultHandler
    public Resolution welcome() {

        return new JSP("helpdesk/daftar_aduan_manual.jsp");
    }

    public Resolution saveDocuments() throws IOException {
        Session session = sessionProvider.get();

        String reportId = getContext().getRequest().getParameter("reportId");
        Transaction tx = session.beginTransaction();
        HelpdeskReport report = null;
        HelpdeskReportDocuments documents = new HelpdeskReportDocuments();
        if (reportId != null && StringUtils.isNotBlank(reportId)) {
            report = helpdeskReportDAO.findById(Long.valueOf(reportId));
            documents = new HelpdeskReportDocuments();
            documents.setReportId(report);
            documents.setContentType(file2.getContentType());
            documents.setFileName(file2.getFileName());
            documents.setBinaryData(dMSService.convertFileToByte(file2));
            helpdeskReportDocumentsDAO.save(documents);
        }
        tx.commit();
        return new RedirectResolution(DaftarAduanActionBean.class).addParameter("reportId", reportId);
    }

    public HelpdeskAssign saveData(Long s) throws IOException, ParseException {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String reportId = getContext().getRequest().getParameter("reportId");
        UserTable user = getContext().getCurrentUser();
        UserTable pelapor = new UserTable();
        RefHelpdeskReportStatus status = null;
        if (s != null) {
            status = refHelpdeskReportStatusDAO.findById(s);
        }

        HelpdeskReport report = null;
        if (StringUtils.isEmpty(reportId)) {
            report = new HelpdeskReport();
                    report.setCreateDate(new Date());

        } else {
            report = helpdeskReportDAO.findById(Long.valueOf(reportId));
            if (report == null) {
                report = new HelpdeskReport();
                        report.setCreateDate(new Date());

            }
        }
        Employee employee = employeeDAO.findById(pelaporId);
        pelapor = userService.findByEmployeeId(employee.getEmployeeId());
        report.setCreateBy(user);
        report.setDescription(keterangan);
//        report.setDateReport(tarikhAduan);
        report.setReportBy(pelapor);
        report.setEmpId(employee);
             RefHardwareType hardwareType = refHardwareTypeDAO.findById(hardwareTypeId);
        report.setHardwareType(hardwareType);
//        report.setModulReport(submodulTypeId);
        report.setModulType(refSubmodulTypeDAO.findById(submodulTypeId));
        report.setTypeReport(refHelpdeskTypeDAO.findById(helpdeskTypeId));
//        report.setItemReport(itemTypeId);
        report.setItemType(refItemTypeDAO.findById(itemTypeId));
        Date tarikhAduan1 = sdf.parse(tarikhAduan);
        report.setDateReport(tarikhAduan1);
        report.setTitle(perkara);
        report.setStatus(status);
        report = aduanService.saveHelpdeskReport(report);
        if (file != null) {
            HelpdeskReportDocuments documents = new HelpdeskReportDocuments();
            documents.setContentType(file.getContentType());
            documents.setReportId(report);
            documents.setFileName(file.getFileName());
            documents.setBinaryData(dMSService.convertFileToByte(file));
            documents = aduanService.saveHelpdeskReportDocuments(documents);
        }
        reportId = String.valueOf(report.getReportId());
        HelpdeskAssign assign = new HelpdeskAssign();
        assign.setAssignDate(new Date());
        assign.setCreateBy(user);
        assign.setCreateDate(new Date());

        assign.setReportId(report);
        assign = aduanService.saveHelpdeskAssign(assign);
        return assign;
    }

    public Resolution simpan() throws IOException, ParseException {

        UserTable user = getContext().getCurrentUser();
        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(null);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("UD");
        stageService.nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getReportId().getReportId(), user, user);
        tx.commit();
        return new RedirectResolution(DaftarAduanActionBean.class).addParameter("reportId", assign.getReportId().getReportId());
    }

    public Resolution hantar() throws IOException, ParseException {
        UserTable user = getContext().getCurrentUser();

        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(1L);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("AG");
        stageService.nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getAssignId(), user, null);
        tx.commit();

        return new RedirectResolution(HelpdeskMainActionBean.class);
    }
    
       public Resolution view() throws ParseException {
        UserTable pengguna = getContext().getCurrentUser();
        String employeeId = getContext().getRequest().getParameter("employeeId");
        Employee employee = employeeDAO.findById(Long.parseLong(employeeId));
        JabatanForm form = userService.value(employee);
        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", obj.toString());
    }

    public List<DMSUploadForm> getListOfDocument() {
        return listOfDocument;
    }

    public void setListOfDocument(List<DMSUploadForm> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
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

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public FileBean getFile2() {
        return file2;
    }

    public void setFile2(FileBean file2) {
        this.file2 = file2;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public List<Employee> getListPelapor() {
        return listPelapor;
    }

    public void setListPelapor(List<Employee> listPelapor) {
        this.listPelapor = listPelapor;
    }

    public Long getPelaporId() {
        return pelaporId;
    }

    public void setPelaporId(Long pelaporId) {
        this.pelaporId = pelaporId;
    }

}
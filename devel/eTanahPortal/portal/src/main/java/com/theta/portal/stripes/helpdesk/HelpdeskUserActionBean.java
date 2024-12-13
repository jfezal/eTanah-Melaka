/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.helpdesk;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
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
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.HelpdeskMainActionBean;
import com.theta.portal.stripes.form.DMSUploadForm;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
@UrlBinding("/helpdesk/user")
public class HelpdeskUserActionBean extends BaseActionBean {

    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    HelpdeskReportDocumentsDAO helpdeskReportDocumentsDAO;
    @Inject
    RefHardwareTypeDAO refHardwareTypeDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RefSubmodulTypeDAO refSubmodulTypeDAO;
    @Inject
    RefItemTypeDAO refItemTypeDAO;
    @Inject
    RefHelpdeskTypeDAO refHelpdeskTypeDAO;
    @Inject
    RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO;
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
    TableService tableService;
    Long reportId;
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

    String nota;

    List<DMSUploadForm> listOfDocument;
    FileBean file;
    FileBean file2;

    List<AduanForm> listAduan;
    List<AduanForm> listAgihan;
    List<AgihanUserForm> listAgihanKontraktor;

    List<AduanForm> listTeknikal;
    List<AduanForm> listKontraktor;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        UserTable user = getContext().getCurrentUser();
        String reportId = getContext().getRequest().getParameter("reportId");
        HelpdeskReport report = null;
        if (reportId != null && StringUtils.isNotBlank(reportId)) {
            report = helpdeskReportDAO.findById(Long.valueOf(reportId));
            perkara = report.getTitle();
            keterangan = report.getDescription();

            if (report.getTypeReport() != null) {
                helpdeskTypeId = report.getTypeReport().getHelpdeskTypeId();
            }
            if (report.getHardwareType() != null) {
                hardwareTypeId = report.getHardwareType().getHardwareTypeId();
            }
            if (report.getModulType() != null) {
                submodulTypeId = report.getModulType().getSubmodulTypeId();
            }
            if (report.getItemType() != null) {
                itemTypeId = report.getItemType().getItemTypeId();
            }

            listOfDocument = dMSService.findDocumentByReportId(Long.valueOf(reportId));
            setReportId(report.getReportId());
            listAduan = tableService.listAduan(report);
            listAgihan = tableService.listAgihan(report);
            listTeknikal = tableService.listTeknikal(report);
            listKontraktor = tableService.listKontraktor(report);
            setListPulangSemula(tableService.listPulangSemula(report));
            setPulangsemula(aduanService.setPulangSemula(report));

        }

    }

    public Resolution saveDocuments() throws IOException {
        Session session = sessionProvider.get();

        String reportId = getContext().getRequest().getParameter("reportId");
        Transaction tx = session.beginTransaction();
        HelpdeskReport report = null;
        HelpdeskReportDocuments helpdeskReportDocuments = null;
        if (reportId != null && StringUtils.isNotBlank(reportId)) {
            report = helpdeskReportDAO.findById(Long.valueOf(reportId));
            helpdeskReportDocuments = new HelpdeskReportDocuments();
            helpdeskReportDocuments.setReportId(report);
            helpdeskReportDocuments.setContentType(file2.getContentType());
            helpdeskReportDocuments.setFileName(file2.getFileName());
            helpdeskReportDocuments.setBinaryData(dMSService.convertFileToByte(file2));
            helpdeskReportDocumentsDAO.save(helpdeskReportDocuments);
        }
        tx.commit();
        return new RedirectResolution(HelpdeskUserActionBean.class).addParameter("reportId", reportId);
    }

    @DefaultHandler
    public Resolution welcome() {
        setUser(Boolean.TRUE);

        return new JSP("helpdesk/user.jsp");
    }

    public Resolution selesaiForm() {
        setUserSelesai(Boolean.TRUE);
        return new JSP("helpdesk/user_selesai.jsp");
    }

    public HelpdeskAssign saveData(Long s) throws IOException {
        String reportId = getContext().getRequest().getParameter("reportId");
        UserTable user = getContext().getCurrentUser();
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
            }
        }
        Employee employee = userManager.findByPengguna(user);
        report.setCreateBy(user);
        report.setDescription(keterangan);
//        report.setDateReport(tarikhAduan);
        report.setReportBy(user);
        report.setEmpId(employee);
        RefHardwareType hardwareType = refHardwareTypeDAO.findById(hardwareTypeId);
        report.setHardwareType(hardwareType);
//        report.setModulReport(submodulTypeId);
        report.setModulType(refSubmodulTypeDAO.findById(submodulTypeId));
        report.setTypeReport(refHelpdeskTypeDAO.findById(helpdeskTypeId));
//        report.setItemReport(itemTypeId);
        report.setItemType(refItemTypeDAO.findById(itemTypeId));
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

    public Resolution simpan() throws IOException {
        Session session = sessionProvider.get();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(null);
        tx.commit();
        return new RedirectResolution(HelpdeskUserActionBean.class).addParameter("reportId", assign.getReportId().getReportId());
    }

    public Resolution hantar() throws IOException {
        Session session = sessionProvider.get();
        UserTable user = getContext().getCurrentUser();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(1L);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("AG");
        stageService.nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getAssignId(), user, null);
        tx.commit();

        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution selesai() throws IOException {
        Session session = sessionProvider.get();
        UserTable user = getContext().getCurrentUser();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(1L);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("US");
        stageService.close(kodStage, reportId, user);
//        stageService.nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getAssignId(),user,null);
        tx.commit();

        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution simpanSelesai() throws IOException {
        Session session = sessionProvider.get();
        UserTable user = getContext().getCurrentUser();

        Transaction tx = session.beginTransaction();
        tx = session.beginTransaction();
        HelpdeskAssign assign = saveData(1L);
        RefHelpdeskStage kodStage = refHelpdeskStageDAO.findById("US");
        stageService.updateNoteSelesai(nota, reportId);
//        stageService.nextStage(kodStage, assign.getReportId().getReportId(), "" + assign.getAssignId(),user,null);
        tx.commit();

        return new RedirectResolution(HelpdeskMainActionBean.class, "selesaiForm").addParameter("reportId", reportId);
    }

    public HelpdeskReportDAO getHelpdeskReportDAO() {
        return helpdeskReportDAO;
    }

    public void setHelpdeskReportDAO(HelpdeskReportDAO helpdeskReportDAO) {
        this.helpdeskReportDAO = helpdeskReportDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public RefHelpdeskTypeDAO getRefHelpdeskTypeDAO() {
        return refHelpdeskTypeDAO;
    }

    public void setRefHelpdeskTypeDAO(RefHelpdeskTypeDAO refHelpdeskTypeDAO) {
        this.refHelpdeskTypeDAO = refHelpdeskTypeDAO;
    }

    public RefHelpdeskReportStatusDAO getRefHelpdeskReportStatusDAO() {
        return refHelpdeskReportStatusDAO;
    }

    public void setRefHelpdeskReportStatusDAO(RefHelpdeskReportStatusDAO refHelpdeskReportStatusDAO) {
        this.refHelpdeskReportStatusDAO = refHelpdeskReportStatusDAO;
    }

    public DMSService getdMSService() {
        return dMSService;
    }

    public void setdMSService(DMSService dMSService) {
        this.dMSService = dMSService;
    }

    public AduanService getAduanService() {
        return aduanService;
    }

    public void setAduanService(AduanService aduanService) {
        this.aduanService = aduanService;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public StageService getStageService() {
        return stageService;
    }

    public void setStageService(StageService stageService) {
        this.stageService = stageService;
    }

    public RefHelpdeskStageDAO getRefHelpdeskStageDAO() {
        return refHelpdeskStageDAO;
    }

    public void setRefHelpdeskStageDAO(RefHelpdeskStageDAO refHelpdeskStageDAO) {
        this.refHelpdeskStageDAO = refHelpdeskStageDAO;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}

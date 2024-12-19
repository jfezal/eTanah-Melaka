/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.PtdOfficeDAO;
import com.theta.portal.dao.UserRoleDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.model.Employee;
import com.theta.portal.model.HelpdeskAssign;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportLog;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.PtdOffice;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.dms.DMSService;
import com.theta.portal.stripes.form.DMSUploadForm;
import com.theta.portal.stripes.form.SenaraiTugasan;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.utiliti.PengawasanForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class TableService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DMSService dMSService;
    @Inject
    AduanService aduanService;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    @Inject
    HelpdeskReportDAO helpdeskReportDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    PtdOfficeDAO ptdOfficeDAO;
    @Inject
    UserRoleDAO userRoleDAO;
    @Inject
    UserService userService;
    List<AduanForm> listContractor = new ArrayList<AduanForm>();

    public List<AduanForm> listAduan(HelpdeskReport report) {
        List<AduanForm> listAduan = new ArrayList<AduanForm>();

        AduanForm form = new AduanForm();
        form.setNamaPelapor(report.getEmpId().getEmployeeName());
        form.setUnit(report.getEmpId().getEmployeeDepartmentId().getDepartmentName());
        form.setPerkara(report.getTitle());
        form.setKeterangan(report.getDescription());
        form.setJenisMasalah(report.getTypeReport().getHelpdeskType());
//         
        List<DMSUploadForm> list = dMSService.findDocumentByReportId(report.getReportId());
        form.setListOfDocument(list);
        listAduan.add(form);
        return listAduan;
    }

    public List<AduanForm> listAgihan(HelpdeskReport report) throws ParseException {
        List<AduanForm> listAgihan = new ArrayList<AduanForm>();
        List<HelpdeskAssign> list = aduanService.listHelpdeskAssignByReportId(report.getReportId());
        for (HelpdeskAssign h : list) {
            AduanForm form = new AduanForm();
            form.setPegawaiAgihan(h.getCreateBy().getEmployeeId().getEmployeeName());
            form.setTarikhAgih(formatStringSDF(h.getCreateDate()));
            form.setCatatanPegawaiAgihan(h.getDescription());

            listAgihan.add(form);
        }
        return listAgihan;
    }

    public List<AduanForm> listTeknikal(HelpdeskReport report) {
        List<AduanForm> listTechnical = new ArrayList<AduanForm>();
        List<HelpdeskTechnical> list = aduanService.listHelpdeskTechnicalByReportId(report.getReportId());
        for (HelpdeskTechnical h : list) {
            AduanForm form = new AduanForm();
            UserTable user = userTableDAO.findById(h.getTechnicalOfficerId().getUserId());
            form.setNamaPegTeknikal(user.getEmployeeId().getEmployeeName());
//            form.setTarikhHantar(formatStringSDF(h.getClosedDate()));
            form.setUnit(user.getEmployeeId().getEmployeeDepartmentId().getDepartmentName());
            form.setCatatanPegawaiTeknikal(h.getDescription());
            form.setArahanKontraktor(h.getDescHdassign());
            List<DMSUploadForm> l = dMSService.findDocumentByTechnicalId(h.getTechnicalId());
            form.setListOfDocument(l);
            listTechnical.add(form);
        }
        return listTechnical;
    }

    public List<AduanForm> listKontraktor(HelpdeskReport report) {
        List<AduanForm> listKontraktor = new ArrayList<AduanForm>();
        List<HelpdeskContractor> list = aduanService.listHelpdeskKontraktorByReportId(report.getReportId());

        for (HelpdeskContractor h : list) {
            AduanForm form = new AduanForm();
            if (h.getContractorUserId() != null) {
                UserTable user = userTableDAO.findById(h.getContractorUserId().getUserId());
                form.setUnit(user.getEmployeeId().getEmployeeDepartmentId().getDepartmentName());
                form.setNamaPegKontraktor(user.getEmployeeId().getEmployeeName());
            }
            form.setNamaPegTeknikal(h.getHelpdeskContractorId() + "");
//            form.setTarikhHantar(formatStringSDF(h.getClosedDate()));

            form.setCatatanKontraktor(h.getDescription());
            List<DMSUploadForm> l = dMSService.findDocumentByContractorId(h.getHelpdeskContractorId());
            form.setListOfDocument(l);
            listKontraktor.add(form);
        }
        return listKontraktor;
    }

    public List<SenaraiTugasan> senaraiTugasan(UserTable user, String idReport, String tarikhMula, String tarikhAkhir, int start, int max) throws ParseException {
        List<SenaraiTugasan> list = new ArrayList<SenaraiTugasan>();
        List<HelpdeskStage> listTugasan = getListTugasan(user, idReport, tarikhMula, tarikhAkhir);
        for (HelpdeskStage sa : listTugasan) {
            SenaraiTugasan s = new SenaraiTugasan();
            HelpdeskReport hd = helpdeskReportDAO.findById(sa.getReportId());
            s.setPerkara(hd.getTitle());
            HelpdeskReport report = helpdeskReportDAO.findById(sa.getReportId());
            System.out.print("Report ID ="+report.getReportId());
            if(report.getReportBy()!=null){
            PtdOffice ptd = ptdOfficeDAO.findById(report.getReportBy().getUserPtdOfficeId());
            s.setDaerah(ptd.getPtdName());
            }
            s.setReportId("" + sa.getReportId());
            s.setTarikhTerima(formatStringSDF(sa.getDateCreated()));
            s.setTarikhAduan(formatStringSDF(hd.getCreateDate()));
            s.setReportId("" + sa.getReportId());
            s.setPeringkat(sa.getKodStage() != null ? sa.getKodStage().getName() : null);
            if (StringUtils.contains(sa.getKodStage().getUrl(), "?")) {
                s.setUrl(sa.getKodStage().getUrl() + "&" + sa.getKodStage().getParamValue() + "=" + sa.getParamValue());
            } else {
                s.setUrl(sa.getKodStage().getUrl() + "?" + sa.getKodStage().getParamValue() + "=" + sa.getParamValue());
            }
            HelpdeskReport r = hd;

            if (r.getItemType() != null) {
                s.setItem(r.getItemType().getItemTypeName());
            }
            if (r.getEmpId() != null) {
                s.setJabatan(r.getEmpId().getEmployeePtdOfficeId().getPtdName());
                s.setUnit(r.getEmpId().getEmployeeDepartmentId().getDepartmentName());
                s.setNamaPelapor(r.getEmpId().getEmployeeName());

            }
            if (r.getModulType() != null) {
                s.setModul(r.getModulType().getSubmodulTypeName());
            }
            s.setNoAduan(r.getReportId() + "");
            s.setJenisLaporan(r.getTypeReport().getHelpdeskType());
            s.setTarikhAduan(formatStringSDF(r.getCreateDate()));
            s.setUrlReport("helpdesk/report_viewer?reportId=" + r.getReportId());
            if (sa.getUserId() != null && sa.getUserId().getId().equals(user.getId())) {
                s.setView("enabled");
            } else {
                s.setView(userService.checkAutorityStage(user, sa.getKodStage()));

            }
            list.add(s);
        }
        return list;
    }

    public String formatStringSDF(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(date);
    }

    public List<DMSUploadForm> listOfDocument(Long helpdeskContractorId) {
        List<DMSUploadForm> list = dMSService.findDocumentByContractorId(helpdeskContractorId);
        return list;
    }

    public List<DMSUploadForm> listOfDocumentTechnical(Long technicalId) {
        List<DMSUploadForm> list = dMSService.findDocumentByTechnicalId(technicalId);
        return list;
    }

    private boolean isAdmin(UserTable user) {
        boolean admin = false;
        UserRole role = userService.findByUserAndRole(user, 15L);
        if (role != null) {
            if (user.getUserRoleList().contains(role)) {
                admin = true;
            }
        }
        return admin;
    }

    private List<HelpdeskStage> getListTugasan(UserTable user, String idReport, String tarikhMula, String tarikhAkhir) throws ParseException {
        Long ptd = user.getUserPtdOfficeId();
        boolean admin = isAdmin(user);

        Long userId = user.getUserId();
        List<HelpdeskStage> list = new ArrayList<HelpdeskStage>();
        Session session = sessionProvider.get();
        String q = "select distinct s from HelpdeskStage s, HelpdeskReport p, UserTable u, RefHelpdeskStageRole sr, UserRole ur, HelpdeskTechnical ht "
                + "where s.helpdeskReport.reportId = p.reportId and s.helpdeskReport.status != 2"
                + " and  s.reportId = p.reportId "
                + "and ((s.userId.userId = u.userId) or "
                + "(s.userId.userId is null and s.kodStage.kod = sr.refHelpdeskStage.kod "
                + "and sr.refUserType.typeId = ur.typeId.typeId "
                + "and ur.userId.userId = u.userId )or (ht.reportId = p.reportId and ht.technicalOfficerId.userId = :userId ))"
                + "and u.userId = :userId";
        if (!admin) {
            q += " and p.empId.employeePtdOfficeId.ptdOfficeId = :ptd ";
        }
        if (StringUtils.isNotBlank(idReport)) {
            q += " and p.reportId =:idReport ";

        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            q += " and p.dateReport >=:tarikhMula ";

        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            q += " and p.dateReport <=:tarikhAkhir ";

        }
        q += " ORDER BY s.dateCreated desc";
        Query query = session.createQuery(q);
        if (!admin) {
            query.setParameter("ptd", ptd);

        }
        query.setParameter("userId", userId);
        if (StringUtils.isNotBlank(idReport)) {
            query.setString("idReport", idReport);
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            query.setDate("tarikhMula", formatSDF(tarikhMula));

        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            query.setDate("tarikhAkhir", formatSDFPlus1(tarikhAkhir));

        }
        list = query.list();
        return list;
    }

    public Long countSenaraiTugasan(UserTable pengguna, String idReport, String tarikhMula, String tarikhAkhir) throws ParseException {
        Long ptd = pengguna.getUserPtdOfficeId();
        Long userId = pengguna.getUserId();
        boolean admin = isAdmin(pengguna);

        List<HelpdeskStage> list = new ArrayList<HelpdeskStage>();
        Session session = sessionProvider.get();
        String q = "select count(distinct s) from HelpdeskStage s, HelpdeskReport p, UserTable u, RefHelpdeskStageRole sr, UserRole ur, HelpdeskTechnical ht "
                + "where s.helpdeskReport.reportId = p.reportId and s.helpdeskReport.status != 2"
                + " and  s.reportId = p.reportId "
                + "and ((s.userId.userId = u.userId) or "
                + "(s.userId.userId is null and s.kodStage.kod = sr.refHelpdeskStage.kod "
                + "and sr.refUserType.typeId = ur.typeId.typeId "
                + "and ur.userId.userId = u.userId ) or (ht.reportId = p.reportId and ht.technicalOfficerId.userId = :userId))"
                + "and u.userId = :userId";
        if (!admin) {
            q += " and p.empId.employeePtdOfficeId.ptdOfficeId = :ptd ";
        }
        if (StringUtils.isNotBlank(idReport)) {
            q += " and p.reportId =:idReport ";
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            q += " and p.createDate >=:tarikhMula ";
        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            q += " and p.createDate <=:tarikhAkhir ";
        }
        Query query = session.createQuery(q);
        if (!admin) {
            query.setParameter("ptd", ptd);

        }
        query.setParameter("userId", userId);
        if (StringUtils.isNotBlank(idReport)) {
            query.setString("idReport", idReport);
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            query.setDate("tarikhMula", formatSDF(tarikhMula));

        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            query.setDate("tarikhAkhir", formatSDFPlus1(tarikhAkhir));

        }
        return (Long) query.uniqueResult();
    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.parse(date);
    }

    public Date formatSDFPlus1(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);  // number of days to add

        return sdf.parse(sdf.format(c.getTime()));
    }

    public List<SenaraiTugasan> senaraiTugasanPulangSemula(UserTable pengguna) throws ParseException {
        List<SenaraiTugasan> list = new ArrayList<SenaraiTugasan>();
        List<HelpdeskStage> listTugasan = getListTugasanPulangSemula(pengguna);
        for (HelpdeskStage sa : listTugasan) {
            SenaraiTugasan s = new SenaraiTugasan();
            HelpdeskReport hd = helpdeskReportDAO.findById(sa.getReportId());
            s.setPerkara(hd.getTitle());
            HelpdeskReport report = helpdeskReportDAO.findById(sa.getReportId());
            PtdOffice ptd = ptdOfficeDAO.findById(report.getReportBy().getUserPtdOfficeId());
            s.setDaerah(ptd.getPtdName());
            s.setReportId("" + sa.getReportId());
            s.setTarikhTerima(formatStringSDF(sa.getDateCreated()));
            s.setTarikhAduan(formatStringSDF(hd.getCreateDate()));
            s.setReportId("" + sa.getReportId());
            s.setPeringkat(sa.getKodStage().getName());
            if (StringUtils.contains(sa.getKodStage().getUrl(), "?")) {
                s.setUrl(sa.getKodStage().getUrl() + "&" + sa.getKodStage().getParamValue() + "=" + sa.getParamValue());
            } else {
                s.setUrl(sa.getKodStage().getUrl() + "?" + sa.getKodStage().getParamValue() + "=" + sa.getParamValue());
            }
            HelpdeskReport r = hd;

            if (r.getItemType() != null) {
                s.setItem(r.getItemType().getItemTypeName());
            }
            if (r.getEmpId() != null) {
                s.setJabatan(r.getEmpId().getEmployeePtdOfficeId().getPtdName());
                s.setUnit(r.getEmpId().getEmployeeDepartmentId().getDepartmentName());
                s.setNamaPelapor(r.getEmpId().getEmployeeName());

            }
            if (r.getModulType() != null) {
                s.setModul(r.getModulType().getSubmodulTypeName());
            }
            s.setNoAduan(r.getReportId() + "");
            s.setJenisLaporan(r.getTypeReport().getHelpdeskType());
            s.setTarikhAduan(formatStringSDF(r.getCreateDate()));
            s.setUrlReport("helpdesk/report_viewer?reportId=" + r.getReportId());
            list.add(s);
        }
        return list;
    }

    private List<HelpdeskStage> getListTugasanPulangSemula(UserTable pengguna) {
        Long ptd = pengguna.getUserPtdOfficeId();
        Long userId = pengguna.getUserId();
        List<HelpdeskStage> list = new ArrayList<HelpdeskStage>();
        boolean admin = isAdmin(pengguna);
        Session session = sessionProvider.get();
        String query
                = "select distinct s from HelpdeskStage s, HelpdeskReport p, UserTable u, RefHelpdeskStageRole sr "
                + "where s.helpdeskReport.reportId = p.reportId "
                + "and s.reportId = p.reportId "
                + "and s.userId.userId = u.userId and s.kodStage.kod = sr.refHelpdeskStage.kod "
                + " and sr.refHelpdeskStage.kod in ('PSC','PST','PSA','PSU','PSK') "
                + "and u.userId = :userId";
        if (!admin) {
            query += " and p.empId.employeePtdOfficeId.ptdOfficeId = :ptd ";
        }
        Query q = session.createQuery(query);
        if (!admin) {
            q.setParameter("ptd", ptd);

        }
        q.setParameter("userId", userId);
        list = q.list();
        return list;
    }

    public Long countSenaraiSemakSemula(UserTable pengguna) {
        Long ptd = pengguna.getUserPtdOfficeId();
        Long userId = pengguna.getUserId();
        Session session = sessionProvider.get();
        boolean admin = isAdmin(pengguna);
        String q
                = "select count(distinct s) from HelpdeskStage s, HelpdeskReport p, UserTable u, RefHelpdeskStageRole sr "
                + "where s.helpdeskReport.reportId = p.reportId "
                + "and s.reportId = p.reportId "
                + "and s.userId.userId = u.userId and s.kodStage.kod = sr.refHelpdeskStage.kod "
                + " and sr.refHelpdeskStage.kod in ('PSC','PST','PSA','PSU','PSK') "
                + "and u.userId = :userId";
        if (!admin) {
            q += " and p.empId.employeePtdOfficeId.ptdOfficeId = :ptd ";
        }
        Query query = session.createQuery(q);
        if (!admin) {
            query.setParameter("ptd", ptd);

        }
        query.setParameter("userId", userId);
        return (Long) query.uniqueResult();
    }

    public List<AduanForm> listPulangSemula(HelpdeskReport report) throws ParseException {
        List<AduanForm> listPulangSemula = new ArrayList<AduanForm>();
        List<HelpdeskReportLog> list = aduanService.listPulangSemulaByReportId(report.getReportId());

        for (HelpdeskReportLog h : list) {
            AduanForm form = new AduanForm();
            form.setNama(h.getUserId().getEmployeeId().getEmployeeName());
            form.setCatatan(h.getNote());
            form.setTarikhStage(formatStringSDF(h.getDateCreated()));
            listPulangSemula.add(form);
        }
        return listPulangSemula;
    }
}

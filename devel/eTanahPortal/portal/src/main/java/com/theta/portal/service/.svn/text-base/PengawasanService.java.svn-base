/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskContractorDAO;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskStageDAO;
import com.theta.portal.dao.HelpdeskTechnicalDAO;
import com.theta.portal.dao.VendorDAO;
import com.theta.portal.model.HelpdeskContractor;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskStage;
import com.theta.portal.model.HelpdeskTechnical;
import com.theta.portal.model.RefHelpdeskType;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.model.Vendor;
import com.theta.portal.stripes.utiliti.ManageAduanForm;
import com.theta.portal.stripes.utiliti.PengawasanForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PengawasanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HelpdeskStageDAO helpdeskStageDAO;
    @Inject
    StageService stageService;
    @Inject
    PengawasanService pengawasanService;
    @Inject
    HelpdeskReportDAO helpdeskReportDao;
    @Inject
    com.theta.portal.dao.RefHardwareTypeDAO refHardwareTypeDAO;
    @Inject
    com.theta.portal.dao.RefHelpdeskTypeDAO refHelpdeskTypeDAO;
    @Inject
    UserService userService;

    @Inject
    VendorDAO vendorDAO;
    @Inject
    HelpdeskTechnicalDAO helpdeskTechnicalDAO;
    @Inject
    HelpdeskContractorDAO helpdeskContractorDAO;

    public List<PengawasanForm> findRecord(String noAduan, String status, String peringkat,String techId, String pejabat, String jenismasalah, String tarikhMula, String tarikhAkhir, Long reportby, int start, int max) throws ParseException {
        List<PengawasanForm> list = new ArrayList<PengawasanForm>();
        Session session = sessionProvider.get();
        String param[] = new String[6];
        int i = 0;
        String queryStr = "";
        String table = "";
        queryStr = "select p from HelpdeskReport p ";
        if (StringUtils.isNotBlank(peringkat)) {
            table = ", HelpdeskStage r ";

        }
        if(StringUtils.isNotBlank(techId)){
        table += ", HelpdeskTechnical t ";
        }
        queryStr +=table;
        if (StringUtils.isNotBlank(noAduan)) {
            param[i] = " p.reportId = :noAduan ";
            i++;
        }
         if (StringUtils.isNotBlank(status)) {
            if (status.equals("O")) {
                param[i] = " p.status.helpdeskReportStatusTypeId != 2 ";
                i++;
            } else if (status.equals("T")) {
                 param[i] = " p.status.helpdeskReportStatusTypeId = 2 ";
                i++;
            } else {

            }
        }
          if(StringUtils.isNotBlank(techId)){
       param[i] = " p.reportId = t.reportId and t.technicalOfficerId.userId = :techId ";
            i++;
        }
        if (StringUtils.isNotBlank(pejabat)) {
            param[i] = " p.createBy.userPtdOfficeId = :pejabat ";
            i++;
        }
        if (StringUtils.isNotBlank(peringkat)) {
            param[i] = " p.reportId = r.reportId and r.kodStage.kod = :peringkat ";
            i++;
        }
        if (StringUtils.isNotBlank(jenismasalah)) {
            param[i] = "p.typeReport.helpdeskTypeId = :jenismasalah ";
            i++;
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            param[i] = " p.dateReport >= :tarikhMula ";
            i++;
        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            param[i] = " p.dateReport <= :tarikhAkhir ";
            i++;
        }
        if (reportby != null) {
            param[i] = " p.reportBy.userId = :reportby ";
            i++;
        }
        for (int a = 0; a < param.length; a++) {
            if (a == 0) {
                if (!StringUtils.isBlank(param[a])) {
                    queryStr += " where " + param[a];
                }
            } else if (StringUtils.isBlank(param[a])) {
                break;
            } else {
                queryStr += " and " + param[a];
            }
        }
        queryStr += " order by p.dateReport desc";
        Query query = session.createQuery(queryStr);
        if (StringUtils.isNotBlank(noAduan)) {
            query.setString("noAduan", noAduan);

        }
        if (StringUtils.isNotBlank(pejabat)) {
            query.setString("pejabat", pejabat);

        }
        if (StringUtils.isNotBlank(peringkat)) {
            query.setString("peringkat", peringkat);

        }
        if (StringUtils.isNotBlank(jenismasalah)) {
            query.setString("jenismasalah", jenismasalah);
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            query.setDate("tarikhMula", formatSDF(tarikhMula));

        }if (StringUtils.isNotBlank(techId)) {
            query.setString("techId", techId);

        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            query.setDate("tarikhAkhir", formatSDFPlus1(tarikhAkhir));

        }
        if (reportby != null) {
            query.setLong("reportby", reportby);

        }
        query.setFirstResult(start);
        query.setMaxResults(max);

        for (int b = 0; b < query.list().size(); b++) {
            HelpdeskReport r = (HelpdeskReport) query.list().get(b);
            PengawasanForm f = new PengawasanForm();
            if (r.getItemType() != null) {
                f.setItem(r.getItemType().getItemTypeName());
            }
            if (r.getEmpId() != null) {
                f.setJabatan(r.getEmpId().getEmployeePtdOfficeId().getPtdName());
                f.setUnit(r.getEmpId().getEmployeeDepartmentId().getDepartmentName());
                f.setNamaPelapor(r.getEmpId().getEmployeeName());

            }
            if (r.getModulType() != null) {
                f.setModul(r.getModulType().getSubmodulTypeName());
            }
            f.setNoAduan(r.getReportId() + "");
            f.setJenisLaporan(r.getTypeReport().getHelpdeskType());
            f.setTarikhAduan(formatStringSDF(r.getCreateDate()));
            f.setUrlReport("helpdesk/report_viewer?reportId=" + r.getReportId());
            HelpdeskStage stage = helpdeskStageDAO.findById(r.getReportId());
            if (stage != null) {
                if (stage.getUserId() != null) {
                    if (stage.getUserId().getEmployeeId() != null) {
                        f.setNamaPegawai(stage.getUserId().getEmployeeId().getEmployeeName());
                    }
                }
                f.setStatus(stage.getKodStage().getName());
                f.setBilHari(countDay(stage.getDateCreated()) + "Hari");
                f.setTarikhTerima(formatStringSDF(stage.getDateCreated()));
            } else {
                f.setNamaPegawai("");
                f.setStatus("Tutup");
            }

            list.add(f);
        }
        return list;
    }

    public ManageAduanForm value(String idReport, UserTable currentUser) throws ParseException {
        ManageAduanForm form = new ManageAduanForm();
        HelpdeskReport helpdeskReport = helpdeskReportDao.findById(Long.parseLong(idReport));

        form.setNoAduan(helpdeskReport.getReportId() + "");
        form.setNamaPengadu(helpdeskReport.getEmpId().getEmployeeName());
        if (helpdeskReport.getTypeReport() != null) {
            form.setJenisAduan(helpdeskReport.getTypeReport().getHelpdeskType());
        }
        form.setKeterangan(helpdeskReport.getDescription());
        if (helpdeskReport.getHardwareType() != null) {

            com.theta.portal.model.RefHardwareType hardware = refHardwareTypeDAO.findById(helpdeskReport.getHardwareType().getHardwareTypeId());
            form.setHardwareType(hardware.getHardwareTypeName());

            RefHelpdeskType type = refHelpdeskTypeDAO.findById(helpdeskReport.getHardwareType().getHardwareTypeId());
            form.setHelpdeskType(type.getHelpdeskType());
        }
        if (helpdeskReport.getItemType() != null) {
            form.setItem(helpdeskReport.getItemType().getItemTypeName());
        }
        if (helpdeskReport.getTypeReport() != null) {
            form.setPerkara(helpdeskReport.getTypeReport().getHelpdeskType());
        }
        if (helpdeskReport.getModulType() != null) {
            form.setSubmodulType(helpdeskReport.getModulType().getSubmodulTypeName());
        }
        form.setPejabatPengadu(helpdeskReport.getEmpId().getEmployeePtdOfficeId().getPtdName());
        if (helpdeskReport.getCreateDate() != null) {
            form.setTarikhAduan(formatStringSDF(helpdeskReport.getCreateDate()));
        }

        HelpdeskTechnical helpdeskTechnical
                = stageService.findbyReportId(helpdeskReport.getReportId());
        if (helpdeskTechnical != null) {
            form.setNamaPegawaiTeknikal(helpdeskTechnical.getTechnicalOfficerId().getEmployeeId().getEmployeeName());
            form.setPejabatTeknikal(helpdeskTechnical.getTechnicalOfficerId().getEmployeeId().getEmployeePtdOfficeId().getPtdName());
            form.setKeteranganTeknikal(helpdeskTechnical.getDescription());
            if (helpdeskTechnical.getClosedDate() != null) {
                form.setTarikhTeknikalTutup(formatStringSDF(helpdeskTechnical.getClosedDate()));
            }
            if (helpdeskTechnical.getClosedDate() != null) {
                form.setTarikhTeknikalAgih(formatStringSDF(helpdeskTechnical.getClosedDate()));
            }
            if (helpdeskTechnical.getAssignDate() != null) {
                form.setTarikhTeknikalTerima(formatStringSDF(helpdeskTechnical.getAssignDate()));
            }
        }
        HelpdeskContractor helpdeskContractor = stageService.findHelpdeskContractorByReportId(helpdeskReport.getReportId());
        if (helpdeskContractor != null) {
            if (helpdeskContractor.getContractorUserId() != null) {
                form.setNamaKontraktor(helpdeskContractor.getContractorUserId().getEmployeeId().getEmployeeName());
                Vendor vendor = vendorDAO.findById(helpdeskContractor.getContractorId());
                form.setNamaSyarikat(vendor.getVendorName());
                form.setKeteranganKontraktor(helpdeskContractor.getDescription());
                if (helpdeskContractor.getClosedDate() != null) {
                    form.setTarikhKontraktorTutup(formatStringSDF(helpdeskContractor.getClosedDate()));
                }
                 if (helpdeskContractor.getAssignDate()!= null) {
                    form.setTarikhKontraktorTerima(formatStringSDF(helpdeskContractor.getAssignDate()));
                }
            }
        }
        if (userService.checkAutority(currentUser)) {
            form.setTolakTugasanButton(Boolean.FALSE);
            form.setUserSemakButton(Boolean.FALSE);
        } else {
            form.setTolakTugasanButton(Boolean.TRUE);
            form.setUserSemakButton(Boolean.TRUE);
        }
        HelpdeskStage stage = helpdeskStageDAO.findById(Long.parseLong(idReport));
        if (stage != null) {
            if (stage.getKodStage().getKod().equals("US")) {
                form.setUserSemakButton(Boolean.FALSE);
            }
            if (stage.getKodStage().getKod().equals("PT")) {
                if (stage.getUserId().getUserId().equals(currentUser.getUserId())) {
                    form.setUserSemakButton(Boolean.FALSE);
                }
            }
        } else {
            form.setTolakTugasanButton(Boolean.TRUE);
            form.setUserSemakButton(Boolean.TRUE);
        }

        return form;
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

    public String formatStringSDF(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(date);
    }

    private String countDay(Date dateCreated) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(sdf.format(new Date()));
        Date secondDate = sdf.parse(sdf.format(dateCreated));

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff + "";
    }

    public Long countFindRecord(String noAduan, String status, String peringkat,String techId, String pejabat, String jenismasalah, String tarikhMula, String tarikhAkhir, Long reportby) throws ParseException {
        Session session = sessionProvider.get();
        String param[] = new String[8];
        int i = 0;
        String queryStr = "";
        String table = "";
        queryStr = "select count(p) from HelpdeskReport p ";
        if (StringUtils.isNotBlank(peringkat)) {
            table = ", HelpdeskStage r ";

        }
        if(StringUtils.isNotBlank(techId)){
        table += ", HelpdeskTechnical t ";
        }
        queryStr +=table;
        if (StringUtils.isNotBlank(noAduan)) {
            param[i] = " p.reportId = :noAduan ";
            i++;
        }
        if (StringUtils.isNotBlank(pejabat)) {
            param[i] = " p.createBy.userPtdOfficeId = :pejabat ";
            i++;
        }
         if(StringUtils.isNotBlank(techId)){
        table += ", HelpdeskTechnical t ";
        }
        if (StringUtils.isNotBlank(status)) {
            if (status.equals("O")) {
                param[i] = " p.status.helpdeskReportStatusTypeId != 2 ";
                i++;
            } else if (status.equals("T")) {
                 param[i] = " p.status.helpdeskReportStatusTypeId = 2 ";
                i++;
            } else {

            }
        }
        if (StringUtils.isNotBlank(peringkat)) {
            param[i] = " p.reportId = r.reportId and r.kodStage.kod = :peringkat ";
            i++;
        }
           if(StringUtils.isNotBlank(techId)){
       param[i] = " p.reportId = t.reportId and t.technicalOfficerId.userId = :techId ";
            i++;
        }
        if (StringUtils.isNotBlank(jenismasalah)) {
            param[i] = "p.typeReport.helpdeskTypeId = :jenismasalah ";
            i++;
        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            param[i] = " p.createDate >= :tarikhMula ";
            i++;
        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            param[i] = " p.createDate <= :tarikhAkhir ";
            i++;
        }

        if (reportby != null) {
            param[i] = " p.reportBy.userId = :reportby ";
            i++;
        }
        for (int a = 0; a < param.length; a++) {
            if (a == 0) {
                if (!StringUtils.isBlank(param[a])) {
                    queryStr += " where " + param[a];
                }
            } else if (StringUtils.isBlank(param[a])) {
                break;
            } else {
                queryStr += " and " + param[a];
            }
        }
        Query query = session.createQuery(queryStr);
        if (StringUtils.isNotBlank(noAduan)) {
            query.setString("noAduan", noAduan);

        }
        if (StringUtils.isNotBlank(pejabat)) {
            query.setString("pejabat", pejabat);

        }
        if (StringUtils.isNotBlank(peringkat)) {
            query.setString("peringkat", peringkat);

        }
        if (StringUtils.isNotBlank(jenismasalah)) {
            query.setString("jenismasalah", jenismasalah);
        }
        if (StringUtils.isNotBlank(techId)) {
            query.setString("techId", techId);

        }
        if (StringUtils.isNotBlank(tarikhMula)) {
            query.setDate("tarikhMula", formatSDF(tarikhMula));

        }
        if (StringUtils.isNotBlank(tarikhAkhir)) {
            query.setDate("tarikhAkhir", formatSDFPlus1(tarikhAkhir));

        }
        if (reportby != null) {
            query.setLong("reportby", reportby);

        }

        return (Long) query.iterate().next();
    }

    public boolean checkAutority(UserTable pengguna) {
        boolean a = false;
        if (pengguna.getUserRoleList().isEmpty()) {
            a = true;
        }

        return a;
    }

}

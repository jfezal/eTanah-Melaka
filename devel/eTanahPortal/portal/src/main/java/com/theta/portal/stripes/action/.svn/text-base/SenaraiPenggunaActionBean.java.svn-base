/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.action;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.theta.portal.dao.DepartmentDAO;
import com.theta.portal.dao.PtdOfficeDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.Department;
import com.theta.portal.model.Employee;
import com.theta.portal.model.PtdOffice;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.SenaraiPenggunaService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.form.PenggunaForm;
import com.theta.portal.stripes.form.SenaraiPenggunaForm;
import com.theta.portal.stripes.form.UserListParam;
import com.theta.portal.stripes.utiliti.ManageAduanForm;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/helpdesk/admin/senarai_pengguna")
public class SenaraiPenggunaActionBean extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    UserManager userManager;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    DepartmentDAO departmentDAO;
    @Inject
    PtdOfficeDAO ptdOfficeDAO;
    @Inject
    SenaraiPenggunaService senaraiPenggunaService;
    List<SenaraiPenggunaForm> listSenaraiPengguna = new ArrayList();
    List<Department> senaraiDepartment = new ArrayList<Department>();
    String userIdPengguna;
    String idPengguna;
    String namaPengguna;
    String noTel;
    String emel;
    String alamat;
    String userPTDOffice;
    String departId;
    String status;
    String katalaluan;

    String ptdOfficeId;
    String deptC;
    String statusC;
    String userType;

    @DefaultHandler
    public Resolution showForm() {
//        add = "false";
//        userTable = getContext().getCurrentUser();
//        employee = userManager.findByPengguna(userTable);
        UserListParam param = null;
                UserTable pengguna = getContext().getCurrentUser();

        senaraiDepartment = senaraiPenggunaService.getDepartment(pengguna.getUserPtdOfficeId()+"");
        listSenaraiPengguna = senaraiPenggunaService.senaraiPengguna(param);
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
        set__pg_total_records(listSenaraiPengguna.size());
        return new JSP("admin/senarai_pengguna.jsp");
    }

    public Resolution cari() {
        UserListParam param = new UserListParam();
        param.setStatus(statusC);
        if (StringUtils.isNotBlank(ptdOfficeId)) {
            param.setUserPtdOfficeId(Long.valueOf(ptdOfficeId));
        }
        param.setUnit(deptC);
        if (StringUtils.isNotBlank(userType)) {
            param.setUserType(Long.valueOf(userType));
        }

        listSenaraiPengguna = senaraiPenggunaService.senaraiPengguna(param);
        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }
        set__pg_total_records(listSenaraiPengguna.size());
        return new JSP("admin/senarai_pengguna.jsp");
    }

    public Resolution view() throws ParseException {
        UserTable pengguna = getContext().getCurrentUser();
        String userId = getContext().getRequest().getParameter("userId");
        PenggunaForm form = senaraiPenggunaService.value(userId);
        JSONObject obj = new JSONObject(form);
        return new StreamingResolution("application/json", obj.toString());
    }

    public Resolution kemaskini() throws ParseException {
        UserTable pengguna = userTableDAO.findById(Long.valueOf(userIdPengguna));
        Employee emp = pengguna.getEmployeeId();
        Department department = departmentDAO.findById(Long.valueOf(departId));
        PtdOffice ptd = ptdOfficeDAO.findById(Long.valueOf(userPTDOffice));
        emp.setEmail(emel);
        emp.setEmployeeActiveStatus(status);
        emp.setEmployeeAddress(alamat);
        emp.setEmployeeDepartmentId(department);
        emp.setEmployeeName(namaPengguna);
        emp.setEmployeePhoneNo(noTel);
        emp.setEmployeePtdOfficeId(ptd);
        pengguna.setEmployeeId(emp);
        pengguna.setDepartId(Long.valueOf(departId));
        pengguna.setUserPtdOfficeId(Long.valueOf(userPTDOffice));
        pengguna.setActiveStatus(status.charAt(0));
        if (StringUtils.isNotBlank(katalaluan)) {
            pengguna.setPassword(katalaluan);
        }
        senaraiPenggunaService.update(emp);
        senaraiPenggunaService.update(pengguna);
        return new JSP("admin/senarai_pengguna.jsp");
    }
 public Resolution viewUnit() {
        String kod = getContext().getRequest().getParameter("kod");
        
        List<Department> senarai = senaraiPenggunaService.getDepartment(kod);
        JSONArray array = new JSONArray(senarai);
        
        return new StreamingResolution("application/json", array.toString());
    }
    public List<SenaraiPenggunaForm> getListSenaraiPengguna() {
        return listSenaraiPengguna;
    }

    public void setListSenaraiPengguna(List<SenaraiPenggunaForm> listSenaraiPengguna) {
        this.listSenaraiPengguna = listSenaraiPengguna;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public SenaraiPenggunaService getSenaraiPenggunaService() {
        return senaraiPenggunaService;
    }

    public void setSenaraiPenggunaService(SenaraiPenggunaService senaraiPenggunaService) {
        this.senaraiPenggunaService = senaraiPenggunaService;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUserPTDOffice() {
        return userPTDOffice;
    }

    public void setUserPTDOffice(String userPTDOffice) {
        this.userPTDOffice = userPTDOffice;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKatalaluan() {
        return katalaluan;
    }

    public void setKatalaluan(String katalaluan) {
        this.katalaluan = katalaluan;
    }

    public String getUserIdPengguna() {
        return userIdPengguna;
    }

    public void setUserIdPengguna(String userIdPengguna) {
        this.userIdPengguna = userIdPengguna;
    }

    public UserTableDAO getUserTableDAO() {
        return userTableDAO;
    }

    public void setUserTableDAO(UserTableDAO userTableDAO) {
        this.userTableDAO = userTableDAO;
    }

    public DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public PtdOfficeDAO getPtdOfficeDAO() {
        return ptdOfficeDAO;
    }

    public void setPtdOfficeDAO(PtdOfficeDAO ptdOfficeDAO) {
        this.ptdOfficeDAO = ptdOfficeDAO;
    }

    public String getPtdOfficeId() {
        return ptdOfficeId;
    }

    public void setPtdOfficeId(String ptdOfficeId) {
        this.ptdOfficeId = ptdOfficeId;
    }

    public String getDeptC() {
        return deptC;
    }

    public void setDeptC(String deptC) {
        this.deptC = deptC;
    }

    public String getStatusC() {
        return statusC;
    }

    public void setStatusC(String statusC) {
        this.statusC = statusC;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Department> getSenaraiDepartment() {
        return senaraiDepartment;
    }

    public void setSenaraiDepartment(List<Department> senaraiDepartment) {
        this.senaraiDepartment = senaraiDepartment;
    }
    
    

}

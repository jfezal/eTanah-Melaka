/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.dao.DepartmentDAO;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.dao.PtdOfficeDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.model.Department;
import com.theta.portal.model.Employee;
import com.theta.portal.model.PtdOffice;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.session.LoginSession;
import com.theta.portal.service.session.SessionManager;
import com.theta.portal.stripes.form.PenggunaForm;
import com.theta.portal.stripes.form.SenaraiPenggunaForm;
import com.theta.portal.stripes.form.UserListParam;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class SenaraiPenggunaService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    PtdOfficeDAO ptdOfficeDAO;
    @Inject
    DepartmentDAO departmentDAO;

    public List<UserTable> findListEmployee(UserListParam param) {
        Session session = sessionProvider.get();
        String s = "select u from UserTable u ";
        if (param != null) {
            s = s + "where ";
            if (param.getUserType() != null) {
                s = "select u from UserTable u, UserRole ur where";
                s = s + " u.userId= ur.userId and ur.typeId.typeId = :typeId";
                if (param.getUserPtdOfficeId() != null) {
                    s = s + " and u.userPtdOfficeId = :userPtdOfficeId";
                }
                if (StringUtils.isNotBlank(param.getStatus())) {;
                    s = s + " and u.activeStatus= :activeStatus";
                }
                if (StringUtils.isNotBlank(param.getUnit())) {
                    s = s + " and u.departId= :departId";
                }
            } else {
                s = s + "u.userName is not null ";
                if (param.getUserPtdOfficeId() != null) {
                    s = s + " and u.userPtdOfficeId = :userPtdOfficeId";
                }
                if (StringUtils.isNotBlank(param.getStatus())) {;
                    s = s + " and u.activeStatus= :activeStatus";
                }
                if (StringUtils.isNotBlank(param.getUnit())) {
                    s = s + " and u.departId= :departId";
                }
            }

        }
        Query query = session.createQuery(s);
        if (param != null) {
            if (param.getUserPtdOfficeId() != null) {
                query.setLong("userPtdOfficeId", param.getUserPtdOfficeId());
            }
            if (StringUtils.isNotBlank(param.getStatus())) {;
                query.setString("activeStatus", param.getStatus());
            }
            if (param.getUserType() != null) {
                query.setLong("typeId", param.getUserType());
            }
            if (StringUtils.isNotBlank(param.getUnit())) {

                query.setString("departId", param.getUnit());
            }
        }

//        query.setParameter("peranan", peranan);
        return query.list();
    }

    public List<SenaraiPenggunaForm> senaraiPengguna(UserListParam param) {
        SessionManager sm = SessionManager.getInstance();
        List<UserTable> list = findListEmployee(param);
        List<SenaraiPenggunaForm> l = new ArrayList<SenaraiPenggunaForm>();
        PtdOffice ofis = new PtdOffice();
        Department dept = new Department();
        int i = 0;
        for (UserTable ut : list) {
            SenaraiPenggunaForm form = new SenaraiPenggunaForm();
            LoginSession ss = sm.getActiveSessionByUserId(ut.getUserName());
            ofis = ptdOfficeDAO.findById(ut.getUserPtdOfficeId());
            dept = departmentDAO.findById(ut.getDepartId());
            form.setIdPengguna(ut.getUserName());
            form.setUserId(ut.getUserId() + "");
            form.setJabatan(ofis.getPtdAcronym() + "");
            if (ut.getEmployeeId() != null) {
                form.setNama(ut.getEmployeeId().getEmployeeName());
            }

            System.out.println(i + 1 + "***" + ut.getUserId());
            form.setUnit(dept.getDepartmentName() + "");
            form.setStatus(ut.getActiveStatus() == '1' ? "Aktif" : "Tidak Aktif");
            form.setOnline(ss != null ? "Online" : "Offline");
            form.setPeranan(!ut.getUserRoleList().isEmpty() ? perananUser(ut.getUserRoleList()) : "-");
            l.add(form);
        }
        return l;
    }

    private String perananUser(List<UserRole> userRoleList) {
        String a = "";
        for (UserRole r : userRoleList) {

            a = a + "<p>" + r.getTypeId().getUserType();
        }
        return a;
    }

    public PenggunaForm value(String userId) {
        PenggunaForm form = new PenggunaForm();
        UserTable user = userTableDAO.findById(Long.valueOf(userId));
        if (user != null) {
            form.setAlamat(user.getEmployeeId().getEmployeeAddress());
            form.setDepartId(user.getDepartId() + "");
            form.setEmel(user.getEmployeeId().getEmail());
            form.setNamaPengguna(user.getEmployeeId().getEmployeeName());
            form.setNoTel(user.getEmployeeId().getEmployeePhoneNo());
            form.setStatus(user.getActiveStatus() + "");
            form.setUserPTDOffice(user.getUserPtdOfficeId() + "");
            form.setIdPengguna(user.getUserName());
            form.setUserId(userId);
        }
        return form;
    }
    
     public List<Department> getDepartment(String ptdOfficeId) {

        String sb = "SELECT m FROM Department m  where m.ptdOfficeId.ptdOfficeId = :ptdOfficeId ORDER BY m.departmentName asc";
        Query q = sessionProvider.get().createQuery(sb);
        q.setString("ptdOfficeId", ptdOfficeId);
        return q.list();
    }

    public void update(UserTable pengguna) {
        userTableDAO.update(pengguna);
    }

    public void update(Employee emp) {
        employeeDAO.update(emp);
    }

}

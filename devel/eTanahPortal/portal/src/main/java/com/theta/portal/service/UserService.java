/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service;

import com.google.inject.Inject;
import com.theta.portal.model.Employee;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.RefUserType;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.stripes.helpdesk.JabatanForm;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class UserService {
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    public List<Employee> findListEmployee(Long userPtdOfficeId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from Employee p "
                + "where p.employeeActiveStatus= 1 and p.employeePtdOfficeId.ptdOfficeId = :userPtdOfficeId");
        query.setParameter("userPtdOfficeId", userPtdOfficeId);
        return query.list();
    }
    
    public UserTable findByEmployeeId(Long employeeId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserTable p "
                + "where p.employeeId.employeeId=  :employeeId and p.activeStatus = 1");
        query.setParameter("employeeId", employeeId);
        return (UserTable) query.uniqueResult();
    }
    
    UserRole findByUserAndRole(UserTable user, long l) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserRole p "
                + "where p.userId.userId = :userId and p.typeId.typeId = :typeId");
        query.setParameter("userId", user.getUserId());
        query.setParameter("typeId", l);
        return (UserRole) query.uniqueResult();
    }
    
    public List<RefUserType> findTypeActive() {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from RefUserType p "
                + "where p.status = 'Y'");
        return query.list();
    }
    
    boolean checkAutority(UserTable currentUser) {
        return false;
    }
    
     String checkAutorityStage(UserTable currentUser, RefHelpdeskStage stage) {
             Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserRole p, RefHelpdeskStageRole rhsr "
                + "where p.userId.userId = :userId and p.typeId.typeId=rhsr.refUserType.typeId  and rhsr.refHelpdeskStage.kod = :kod");
        query.setParameter("userId", currentUser.getUserId());
        query.setParameter("kod", stage.getKod());
        if(query.list().isEmpty()){
        return "disabled";
        }else{
        return "enabled";
        }
        
    }
    
    
    public JabatanForm value(Employee employee) {
        JabatanForm form = new JabatanForm();
        form.setJabatan(employee.getEmployeePtdOfficeId().getPtdOfficeName());
        form.setUnit(employee.getEmployeeDepartmentId().getDepartmentName());
        form.setEmel(employee.getEmail());
        form.setNotTel(employee.getEmployeePhoneNo());
        return form;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.action;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.dao.DepartmentDAO;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.dao.PtdOfficeDAO;
import com.theta.portal.dao.RefUserTypeDAO;
import com.theta.portal.dao.UserRoleDAO;
import com.theta.portal.manager.UserManager;
import com.theta.portal.model.Department;
import com.theta.portal.model.Employee;
import com.theta.portal.model.PtdOffice;
import com.theta.portal.model.RefUserType;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.UserService;
import com.theta.portal.stripes.BaseActionBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.FetchProfile.Item;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;

@UrlBinding("/helpdesk/admin/userProfile")
public class UserProfileActionBean extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    UserManager userManager;
    @Inject
    DepartmentDAO deparmentDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    PtdOfficeDAO ptdOfficeDAO;
    @Inject
    UserService userService;
    @Inject
    RefUserTypeDAO refUserTypeDAO;
    @Inject
    UserRoleDAO userRoleDAO;

    UserTable userTable;
    Employee employee;
    private String oldPassword;
    private String password;
    Long departmentId;
    Long ptdOfficeId;
    String add;
    List<RefUserType> items;
    List<UserRole> ur;
    UserRole userRole;
    String userName;

    @DefaultHandler
    public Resolution welcome() {
        add = "false";
        userTable = getContext().getCurrentUser();
        employee = userManager.findByPengguna(userTable);

        return new JSP("admin/user_profile.jsp");
    }

    public Resolution reset() {
        userTable = getContext().getCurrentUser();
        employee = userManager.findByPengguna(userTable);

        return new JSP("admin/reset_password.jsp");
    }

    public Resolution checkUser() {
        userTable = userManager.findUserId(userName);
        if (userTable != null) {
            employee = userManager.findByPengguna(userTable);
            departmentId = employee.getEmployeeDepartmentId().getDepartmentId();
            ptdOfficeId = employee.getEmployeePtdOfficeId().getPtdOfficeId();
            items = userService.findTypeActive();
            ur = userManager.findbyUserId(userTable);

            return new JSP("admin/edit_userProfile.jsp");
        } else {
            addSimpleError("Maklumat tidak wujud");
            return new JSP("admin/check_user.jsp");
        }

        //return new JSP("admin/edit_userProfile.jsp");
    }

    public Resolution editUserProfile() {
        String peranan[] = getContext().getRequest().getParameterValues("peranan");
        String perananId[] = getContext().getRequest().getParameterValues("perananId");

        if (userTable != null) {
            UserTable ut = userManager.findUserId(userTable.getUserName());
            Employee e = userManager.findByPengguna(ut);

            //update userTable
            //ut.setUserName(userTable.getUserName());
            ut.setActiveStatus(employee.getEmployeeActiveStatus().charAt(0));

            //update emplyee table
            e.setEmployeeName(employee.getEmployeeName());
            if (employee.getEmployeePhoneNo() != null) {
                e.setEmployeePhoneNo(employee.getEmployeePhoneNo());
            }
            if (employee.getEmployeeAddress() != null) {
                e.setEmployeeAddress(employee.getEmployeeAddress());
            }
            e.setEmail(employee.getEmail());

            if (departmentId != null) {
                e.setEmployeeDepartmentId(deparmentDAO.findById(departmentId));
            }

            if (ptdOfficeId != null) {
                e.setEmployeePtdOfficeId(ptdOfficeDAO.findById(ptdOfficeId));
            }

            e.setEmployeeActiveStatus(employee.getEmployeeActiveStatus());

            //userrole
            if (peranan != null) {

                //deleterole
                ur = userManager.findbyUserId(ut);
//                if (ur != null) {
//                    for (UserRole role : ur) {
//                        userRoleDAO.delete(role);
//                    }
//                }

                for (int i = 0; i < peranan.length; i++) {
                    String a = peranan[i];
                    RefUserType typeId = refUserTypeDAO.findById(Long.parseLong(a));
                    UserRole userRole = new UserRole();
                    userRole.setUserId(ut);
                    userRole.setTypeId(typeId);

                    userManager.saveOrUpdateRole(userRole);
                }
            }

            //deleteRoles
            if (perananId != null) {

                //deleterole
                for (int i = 0; i < perananId.length; i++) {
                    String typeIdRoles = perananId[i];
                    RefUserType typeId1 = refUserTypeDAO.findById(Long.parseLong(typeIdRoles));                   
                    userRole = userManager.findbyUserIdTypeId(ut, typeId1);
                    userRoleDAO.delete(userRole);

                }

            }

            userManager.saveOrUpdate(ut);
            userManager.saveOrUpdateEmp(e);

            addSimpleMessage("Kemaskini telah berjaya");
        }
        return new RedirectResolution(UserProfileActionBean.class, "edit");
    }

    public Resolution edit() {

        return new JSP("admin/check_user.jsp");
    }

    public Resolution resetPassword() {
        String userName1 = getContext().getRequest().getParameter("userName");
        userTable = userManager.findUserId(userName1);

        return new JSP("admin/password_reset.jsp");
    }

    public Resolution addUser() {
        add = "true";

        items = userService.findTypeActive();

        return new JSP("admin/add_userProfile.jsp");
    }

    public Resolution updateUserProfile() {

        if (userTable != null) {
            UserTable ut = userManager.findUserId(userTable.getUserName());
            Employee e = userManager.findByPengguna(ut);

            //update userTable
            //ut.setUserName(userTable.getUserName());
            ut.setActiveStatus(employee.getEmployeeActiveStatus().charAt(0));

            //update emplyee table
            e.setEmployeeName(employee.getEmployeeName());
            if (employee.getEmployeePhoneNo() != null) {
                e.setEmployeePhoneNo(employee.getEmployeePhoneNo());
            }
            if (employee.getEmployeeAddress() != null) {
                e.setEmployeeAddress(employee.getEmployeeAddress());
            }
            e.setEmail(employee.getEmail());

            if (employee.getEmployeeDepartmentId() != null) {
                e.setEmployeeDepartmentId(deparmentDAO.findById(departmentId));
            }

            if (employee.getEmployeePtdOfficeId() != null) {
                e.setEmployeePtdOfficeId(ptdOfficeDAO.findById(ptdOfficeId));
            }
            e.setEmployeeActiveStatus(employee.getEmployeeActiveStatus());

            userManager.saveOrUpdate(ut);
            userManager.saveOrUpdateEmp(e);
            addSimpleMessage("Kemaskini telah berjaya");
        }
        return new RedirectResolution(UserProfileActionBean.class, "welcome");
    }

    public Resolution addUserProfile() {
        String peranan[] = getContext().getRequest().getParameterValues("peranan");
        UserTable ut = new UserTable();
        Employee e = new Employee();

        UserTable userLogin = getContext().getCurrentUser();
        //update emplyee table
        e.setEmployeeName(employee.getEmployeeName());
        e.setEmployeePhoneNo(employee.getEmployeePhoneNo());
        e.setEmployeeAddress(employee.getEmployeeAddress());
        e.setEmail(employee.getEmail());
        e.setEmployeePtdOfficeId(ptdOfficeDAO.findById(ptdOfficeId));
        e.setEmployeeDepartmentId(deparmentDAO.findById(departmentId));
        e.setEmployeeActiveStatus(employee.getEmployeeActiveStatus());
        // e.setCreateBy(BigInteger.valueOf(userLogin.getEmployeeId().getEmployeeId()));
        e.setCreateDate(new Date());
        userManager.saveOrUpdateEmp(e);

        //update userTable
        ut.setUserName(userTable.getUserName());
        ut.setPassword(userTable.getPassword());
        ut.setActiveStatus(employee.getEmployeeActiveStatus().charAt(0));

        PtdOffice ptd = ptdOfficeDAO.findById(ptdOfficeId);
        ut.setUserPtdOfficeId(ptd.getPtdOfficeId().longValue());
        ut.setEmployeeId(e);
        employee = employeeDAO.findById(e.getEmployeeId());
        ut.setId(String.valueOf(employee.getEmployeeId()));
        Department d = deparmentDAO.findById(departmentId);
        ut.setDepartId(d.getDepartmentId().longValue());
        //ut.setId("save");
        ut.setCreatedBy(getContext().getCurrentUser().getUserId());
        ut.setCreationDate(new Date());

        userManager.saveOrUpdate(ut);

        //userrole
        if (peranan != null) {
            for (int i = 0; i < peranan.length; i++) {
                String a = peranan[i];
                RefUserType typeId = refUserTypeDAO.findById(Long.parseLong(a));
                UserRole userRole = new UserRole();
                userRole.setUserId(ut);
                userRole.setTypeId(typeId);
                userManager.saveOrUpdateRole(userRole);
            }
        }
        addSimpleMessage("Maklumat Pengguna telah berjaya di simpan.");

        return new RedirectResolution(UserProfileActionBean.class, "addUser");
    }

    public Resolution updatePasswdProfile() {
        if (userTable != null) {

            UserTable ut = userManager.findUserId(userTable.getUserName());
            if (ut == null) {
                addSimpleError("ID Pengguna Salah!");
            } else if (userManager.authenticate(ut.getUserName(), oldPassword)) {
                //String encrypted = userManager.getEncryptChangePwd(password);

                ut.setPassword(password);
                ut.setActiveStatus('1');
                userManager.saveOrUpdate(ut);
                addSimpleMessage("Kata laluan telah berjaya dikemaskini");
            } else {
                addSimpleError("Kata Laluan Lama Salah");
            }
        }
        return new JSP("admin/reset_password.jsp");
    }

    public Resolution updatePasswdUser() {

        String userName1 = getContext().getRequest().getParameter("userName");
        if (userName1 != null) {

            UserTable ut = userManager.findUserId(userName1);
            if (ut == null) {
                addSimpleError("ID Pengguna Salah!");
            } else {
                //String encrypted = userManager.getEncryptChangePwd(password);

                ut.setPassword(password);
                ut.setActiveStatus('1');
                userManager.saveOrUpdate(ut);
                addSimpleMessage("Kata laluan telah berjaya dikemaskini");
            }
        }
        return new JSP("admin/check_user.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
        add = getContext().getRequest().getParameter("add");
        if ("false".equals(add)) {
            userTable = getContext().getCurrentUser();
            employee = userManager.findByPengguna(userTable);
            departmentId = employee.getEmployeeDepartmentId().getDepartmentId();
            ptdOfficeId = employee.getEmployeePtdOfficeId().getPtdOfficeId();
        }

    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPtdOfficeId() {
        return ptdOfficeId;
    }

    public void setPtdOfficeId(Long ptdOfficeId) {
        this.ptdOfficeId = ptdOfficeId;
    }

    public List<RefUserType> getItems() {
        return items;
    }

    public void setItems(List<RefUserType> items) {
        this.items = items;
    }

    public List<UserRole> getUr() {
        return ur;
    }

    public void setUr(List<UserRole> ur) {
        this.ur = ur;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

}

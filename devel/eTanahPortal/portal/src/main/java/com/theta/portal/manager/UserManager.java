/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.manager;

import com.google.inject.Inject;
import com.theta.portal.dao.EmployeeDAO;
import com.theta.portal.dao.UserRoleDAO;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.dao.VendorDAO;
import com.theta.portal.model.Employee;
import com.theta.portal.model.RefUserType;
import com.theta.portal.model.UserRole;
import com.theta.portal.model.UserTable;
import com.theta.portal.model.Vendor;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import com.wideplay.warp.persist.Transactional;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wan.fairul
 */
public class UserManager {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(UserManager.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    VendorDAO vendorDAO;
    @Inject
    UserTableDAO userTableDAO;
    @Inject
    UserRoleDAO userRoleDAO;

    public boolean authenticate(String userId, String password) {

        Connection con = null;
        con = sessionProvider.get().connection();
        String encryptPwd = null;
//        try {
//            encryptPwd = getEncryptPwd(con, password);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
//        }        
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserTable p "
                + "where p.userName = :userId and p.password = :password");
        query.setString("userId", userId);
        query.setString("password", password);

        UserTable user = (UserTable) query.uniqueResult();
        return user != null;
    }

//    public static String getEncryptPwd(Connection con1, String katalaluan) throws SQLException {
//        Connection con = null;
//        CallableStatement proc = null;
//        String pwd = null;
//
//        try {
//            con = con1;
//            proc = con.prepareCall("{ ? = call PASS_ENCRYPT(?) }");
//            proc.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
//            proc.setString(2, katalaluan);
//            proc.executeUpdate();
//
//        } finally {
//            try {
//                pwd = proc.getString(1);
//            } catch (SQLException e) {
//            }
//            con.close();
//        }
//
//        return pwd;
//    }
    public String getEncryptChangePwd(String katalaluan) {
        Connection con = null;
        con = sessionProvider.get().connection();
        String encryptPwd = null;
//        try {
//            encryptPwd = getEncryptPwd(con, katalaluan);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return encryptPwd;
    }

    public List<UserTable> findAll() {
        String query = "SELECT a FROM UserTable a ";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public UserTable findUserIdPwd(String idPengguna, String kataLaluan) {
        Connection con = null;
        con = sessionProvider.get().connection();
        String encryptPwd = null;
//        try {
//            encryptPwd = getEncryptPwd(con, kataLaluan);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String query = "SELECT a FROM PtPengguna a WHERE a.idPengguna = :idPengguna and a.kataLaluan = :kataLaluan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        q.setString("kataLaluan", encryptPwd);
        return (UserTable) q.uniqueResult();
    }

    public Long getTotalRecord(Map<String, String[]> param) {

        String query = "SELECT count(p) FROM PtPengguna p WHERE p.idPengguna IS NOT NULL ";

        if (isNotBlank(param.get("kategori"))) {
            query += "AND p.kodKategoriPengguna = :kategori ";
        }

        if (isNotBlank(param.get("idPengguna"))) {
            query += "AND p.idPengguna = :idPengguna ";
        }

        if (isNotBlank(param.get("nama"))) {
            query += "AND LOWER(p.nama) LIKE :nama ";
        }

        if (isNotBlank(param.get("status"))) {
            query += "AND p.aktifFl = :status ";
        }

        if (isDebug) {
            LOG.debug("query : " + query);
        }
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("kategori"))) {
            q.setString("kategori", param.get("kategori")[0].trim());
        }

        if (isNotBlank(param.get("idPengguna"))) {
            q.setString("idPengguna", param.get("idPengguna")[0].trim());
        }

        if (isNotBlank(param.get("nama"))) {
            q.setString("nama", "%" + (param.get("nama")[0].trim()).toLowerCase() + "%");
        }

        if (isNotBlank(param.get("status"))) {
            q.setCharacter("status", param.get("status")[0].trim().charAt(0));
        }

        return (Long) q.iterate().next();
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public UserTable findUserId(String userLogin) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserTable p "
                + "where p.userName = :userLogin and p.activeStatus = '1'");
        query.setString("userLogin", userLogin);
        return (UserTable) query.uniqueResult();
    }

    public UserTable findUserIdActive(String userName) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserTable p "
                + "where p.userName = :userName");
        query.setString("userName", userName);
        return (UserTable) query.uniqueResult();
    }

    public Employee findByPengguna(UserTable pengguna) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from Employee p "
                + "where p.employeeId = :employeeId");
        query.setString("employeeId", pengguna.getId());
        return (Employee) query.uniqueResult();
    }

    public List<AgihanUserForm> listAgihanTugasanUser(String idPtd) {
        List<AgihanUserForm> list = new ArrayList<AgihanUserForm>();
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from UserTable p, UserRole r "
                + "where p.userPtdOfficeId = :idPtd and p.activeStatus = 1 and p.userId = r.userId.userId"
                        + " and r.typeId.typeId = 13");
        query.setString("idPtd", idPtd);
        for (int a = 0; a < query.list().size(); a++) {
            UserTable u = (UserTable) query.list().get(a);
            Employee e = employeeDAO.findById(Long.valueOf(u.getId()));
            AgihanUserForm f = new AgihanUserForm();
            f.setName(e.getEmployeeName());
            f.setUserId(u.getUserId());
            list.add(f);
        }

        return list;
    }

    public List<AgihanUserForm> listAgihanTugasanKontraktor() {
        List<AgihanUserForm> list = new ArrayList<AgihanUserForm>();
        for (Vendor v : listVendorHelpdesk()) {
            AgihanUserForm form = new AgihanUserForm();
            form.setName(v.getVendorName());
            form.setUserId(v.getVendorId());
            list.add(form);
        }
        return list;
    }
    
      public List<Vendor> listVendorHelpdesk() {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from Vendor p "
                + "where p.helpdeskStatus = 'Y'");
        return  query.list();
    }

    public Long findTugasanBelumSelesai(String userId) {
        Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select count(distinct s) from HelpdeskStage s, HelpdeskReport p, UserTable u, RefHelpdeskStageRole sr, UserRole ur "
                + "where s.helpdeskReport.reportId = p.reportId "
                + "and s.reportId = p.reportId "
                + "and s.userId.userId = u.userId "
                + "and u.userId = :userId");
        query.setString("userId", userId);
        return (Long) query.uniqueResult();
//        return 1L;
    }

    @Transactional
    public UserTable saveOrUpdate(UserTable userTable) {
        return userTableDAO.saveOrUpdate(userTable);
    }

    @Transactional
    public Employee saveOrUpdateEmp(Employee employee) {
        return employeeDAO.saveOrUpdate(employee);
    }

    public Long getTotalUser() {

        String query = "SELECT count(p) FROM UserTable p";

        Query q = sessionProvider.get().createQuery(query);

        return (Long) q.iterate().next();
    }

    @Transactional
    public UserRole saveOrUpdateRole(UserRole userRole) {
        return userRoleDAO.saveOrUpdate(userRole);
    }
    
    public List<UserRole> findbyUserId(UserTable userId) {
        String query = "SELECT a FROM UserRole a "
                + "where a.userId.userId = :userId";
        
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("userId", userId.getUserId());
        return q.list();
    }
    
     public UserRole findbyUserIdTypeId(UserTable userId, RefUserType typeId) {
        String query = "SELECT a FROM UserRole a "
                + "where a.userId.userId = :userId and a.typeId.typeId = :typeId";
        
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("userId", userId.getUserId());
        q.setLong("typeId", typeId.getTypeId());
         return (UserRole) q.uniqueResult();
    }

}

package com.theta.portal.stripes.config;

import com.google.inject.Inject;
import com.theta.portal.dao.UserTableDAO;
import com.theta.portal.model.Employee;
import com.wideplay.warp.persist.Transactional;
import com.theta.portal.model.UserTable;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.hibernate.LockMode;

public class ableActionBeanContext extends ActionBeanContext {

    public static final String USER_ID_KEY = "_KEY_USER";
    public static final String USER_ID_DETAIL = "_KEY_DETAIL";
    public static final String USER_GRP = "_KEY_USER_GRP";
    public static final String VENDOR_GRP = "_KEY_VENDOR_GRP";
    public static final String PTEKNIKAL_GRP = "_KEY_PTEKNIKAL_GRP";
    public static final String AGIH_GRP = "_KEY_AGIH_GRP";
    public static final String COUNTER_ID_KEY = "_COUNTER_ID";
    public static final String TRH_LOGIN = "_TRH_LOGIN";
    public static final String ACCESS_LEVEL = "_ACCESS_LEVEL";

    private UserTable pengguna;
    private Employee employee;
    private String counterID;
    private String accessLevel;

    @Inject
    UserTableDAO manager;

    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    public ableActionBeanContext(UserTableDAO manager, com.google.inject.Provider<Session> sessionProvider) {
        this.manager = manager;
        this.sessionProvider = sessionProvider;
    }

    @Transactional
    public UserTable getCurrentUser() {
        if (pengguna == null) {
            HttpSession session = getRequest().getSession();
            if (session != null) {
                return (UserTable) session.getAttribute(USER_ID_KEY);
            }
        }
        return pengguna;
    }

    public String getAgihGrp() {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            return (String) session.getAttribute(AGIH_GRP);
        }
        return "";
    }

    public String getPteknikalGrp() {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            return (String) session.getAttribute(PTEKNIKAL_GRP);
        }
        return "";
    }

    public String getUserGrp() {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            return (String) session.getAttribute(USER_GRP);
        }
        return "";
    }

    public String getVendorGrp() {
        HttpSession session = getRequest().getSession();
        if (session != null) {
            return (String) session.getAttribute(VENDOR_GRP);
        }
        return "";
    }

    public void setCurrentUser(UserTable user) {
        HttpSession session = getRequest().getSession(true);
        session.setAttribute(USER_ID_KEY, user.getUserName());

        session.setAttribute(TRH_LOGIN, new Date());
        this.pengguna = user;
    }

    public void setCounter(String counter) {
        HttpSession session = getRequest().getSession(true);
        session.setAttribute(COUNTER_ID_KEY, counter);
        counterID = counter;
    }

    public String getCounter() {
        if ("".equals(counterID) || counterID == null) {
            HttpSession session = getRequest().getSession();
            if (session != null) {
                counterID = (String) session.getAttribute(COUNTER_ID_KEY);
            }
        }
        return counterID;
    }

    public String getAccessLevel() {
        if ("".equals(accessLevel) || accessLevel == null) {
            HttpSession session = getRequest().getSession();
            if (session != null) {
                accessLevel = (String) session.getAttribute(ACCESS_LEVEL);
            }
        }
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        HttpSession session = getRequest().getSession(true);
        session.setAttribute(ACCESS_LEVEL, accessLevel);
        this.accessLevel = accessLevel;
    }

    public String getUserId() {
        return ((UserTable) getRequest().getSession().getAttribute(USER_ID_KEY)).getUserName();
    }

    public boolean isLoggedIn() {
        return getUserId() != null;
    }

    public void logout() {
        this.pengguna = null;
        HttpSession session = getRequest().getSession();
        if (session != null) {
            session.setAttribute(USER_ID_KEY, null);
//            session.invalidate();
        }
    }

    public UserTable getPengguna() {
        return pengguna;
    }

    public void setPengguna(UserTable pengguna) {
        this.pengguna = pengguna;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getCounterID() {
        return counterID;
    }

    public void setCounterID(String counterID) {
        this.counterID = counterID;
    }

    public UserTableDAO getManager() {
        return manager;
    }

    public void setManager(UserTableDAO manager) {
        this.manager = manager;
    }

}

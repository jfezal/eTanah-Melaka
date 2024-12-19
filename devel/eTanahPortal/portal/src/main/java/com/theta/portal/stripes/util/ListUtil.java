/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.theta.portal.dao.DepartmentDAO;
import com.theta.portal.dao.PtdOfficeDAO;
import com.theta.portal.dao.RefHardwareTypeDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.dao.RefHelpdeskTypeDAO;
import com.theta.portal.dao.RefItemTypeDAO;
import com.theta.portal.dao.RefSubmodulTypeDAO;
import com.theta.portal.dao.RefUserTypeDAO;
import com.theta.portal.model.Department;
import com.theta.portal.model.PtdOffice;
import com.theta.portal.model.RefHardwareType;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.RefHelpdeskType;
import com.theta.portal.model.RefItemType;
import com.theta.portal.model.RefSubmodulType;
import com.theta.portal.model.RefUserType;
import com.theta.portal.stripes.BaseActionBean;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author wan.fairul
 */
@Singleton
public class ListUtil extends BaseActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RefHelpdeskTypeDAO refHelpdeskTypeDAO;
    @Inject
    RefSubmodulTypeDAO refSubmodulTypeDAO;
    @Inject
    RefItemTypeDAO refItemTypeDAO;
    @Inject
    RefHardwareTypeDAO refHardwareTypeDAO;
    @Inject
    PtdOfficeDAO ptdOfficeDAO;
    @Inject
    DepartmentDAO departmentDAO;
    @Inject
    RefHelpdeskStageDAO refHelpdeskStageDAO;
@Inject
RefUserTypeDAO refUserTypeDAO;
    public List<RefHelpdeskStage> getKodStatus() {
        return refHelpdeskStageDAO.findAll();
    }

    public List<RefHelpdeskType> getKodJenisMasalah() {
        return refHelpdeskTypeDAO.findAll();
    }

    public List<RefSubmodulType> getSubModul() {
        return refSubmodulTypeDAO.findAll();
    }

    public List<RefItemType> getItem() {
        return refItemTypeDAO.findAll();
    }

    public List<RefHardwareType> getHardwareType() {
        return refHardwareTypeDAO.findAll();
    }

    public List<PtdOffice> getPtdOffice() {
        return ptdOfficeDAO.findAll();
    }

    public List<Department> getDepartment() {

        String sb = "SELECT m FROM Department m ORDER BY m.departmentName asc";
        Query q = sessionProvider.get().createQuery(sb);
        return q.list();
    }

    public List<PtdOffice> getPTDOffice() {

        String s = "SELECT p FROM PtdOffice p ORDER BY p.ptdOfficeName asc";
        Query q = sessionProvider.get().createQuery(s);
        return q.list();
    }
    
    public List<RefUserType> getUserType(){
      Session session = sessionProvider.get();
        Query query = session.createQuery(
                "select p from RefUserType p "
                + "where p.status = 'Y'");
        return query.list();
    }

}

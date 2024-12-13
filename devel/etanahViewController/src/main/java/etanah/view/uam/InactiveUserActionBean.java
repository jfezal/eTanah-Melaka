/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Izam
 */
@HttpCache(allow = false)
@UrlBinding("/uam/inactive_user")
public class InactiveUserActionBean extends AbleActionBean {

    private Pengguna pguna;
    private List<Pengguna> userList;
    @Inject
    private UamService service;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("uam/inactiveUser.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        userList = service.allInactiveUser();
    }

    public Resolution searchUser() throws NamingException {
        if (pguna != null) {
            if (StringUtils.isNotBlank(pguna.getIdPengguna())) {
                userList = service.searchInactiveUser(pguna.getIdPengguna());
                
                if (userList.isEmpty()) {
                    addSimpleError("Pengguna Tidak Wujud");
                }
            } else {
                userList = service.allInactiveUser();
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/uam/inactiveUser.jsp");
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.Pengguna;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 *
 * @author khairil
 */

@UrlBinding("/WSlogin")
public class LoginWlistActionBean extends BasicTabActionBean {

    PenggunaDAO pengDAO;
    public Pengguna pengguna = new Pengguna();


    @Inject
    public LoginWlistActionBean(PenggunaDAO pengDAO){
        this.pengDAO = pengDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/common/login.jsp");
    }

    public Resolution auth(){

            Pengguna penggunaDB = new Pengguna();
            penggunaDB = pengDAO.findById(pengguna.getIdPengguna());

            if(penggunaDB != null || !penggunaDB.equals(null)){
                
                HttpSession ses = context.getRequest().getSession();
                ses.setAttribute(etanahActionBeanContext.KEY_USER, penggunaDB);

                return new RedirectResolution(SenaraiTugasanActionBean.class);
            }else{
                pengguna = new Pengguna();
                addSimpleError("Id Pengguna atau Katalaluan anda salah.. Sila masukkan sekali lagi..");
            }
        return new ForwardResolution("/WEB-INF/jsp/common/login.jsp");
    }

    public Resolution reset(){

        pengguna = new Pengguna();
        return new ForwardResolution(LoginWlistActionBean.class);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
}

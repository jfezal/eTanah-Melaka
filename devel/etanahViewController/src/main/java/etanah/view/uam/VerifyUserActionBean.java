
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanPenggunaDAO;
import etanah.ldap.LDAPManager;
import etanah.model.InfoAudit;
import etanah.model.KodStatusPengguna;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.PermohonanPengguna;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import java.util.logging.Level;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.mail.internet.*;
import javax.mail.MessagingException;
import java.util.Properties;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.PenggunaPerananDAO;

/**
 *
 * @author khairil
 */
@UrlBinding("/uam/verify_user")
public class VerifyUserActionBean extends AbleActionBean {

   
    private static final Logger logger = Logger.getLogger(VerifyUserActionBean.class);
    @Inject
    private UamService uamService;
    @Inject
    private PermohonanPenggunaDAO mohonpgunaDAO;
    @Inject
    private PenggunaDAO penggunaDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PenggunaPerananDAO penggunaPerananDAO;
    private Pengguna peng;
    private List<PermohonanPengguna> listMohonPguna;
    private String password;
    private String passwordBaru;
    private String idPguna;

    @DefaultHandler
    public Resolution showForm() {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = (String) getContext().getRequest().getParameter("messages");
        String errors = (String) getContext().getRequest().getParameter("errors");

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(errors)) {
            addSimpleError(errors);
        }

        if (peng.getPerananUtama().getKod().equals("6")) {
            //get list pengguna utk pengesahan admin
            listMohonPguna = uamService.getSenaraiPengesahanAdmin(peng.getKodCawangan().getKod());
        }
        if (listMohonPguna == null) {
            listMohonPguna = uamService.getSenaraiPengesahanPegawai("BR", "KM", peng.getIdPengguna());
        } else {
            logger.debug("listing senarai pengesahan user :" + peng.getIdPengguna());
            listMohonPguna.addAll(uamService.getSenaraiPengesahanPegawai("BR", "KM", peng.getIdPengguna()));
        }


        return new JSP("uam/verify.jsp");
    }


    public PermohonanPengguna updateStatus(PermohonanPengguna pp, String status, String catatan) {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        KodStatusPengguna ksp = new KodStatusPengguna();
        ksp.setKod(status);
        pp.setStatus(ksp);
        pp.setCatatan(catatan);
        pp.setInfoAufit(info);
        mohonpgunaDAO.update(pp);
        return pp;
    }

  
    public Resolution sahPenyelia() throws Exception {
        String idPguna = (String) getContext().getRequest().getParameter("idPguna");
        String status = (String) getContext().getRequest().getParameter("status");
        String catatan = (String) getContext().getRequest().getParameter("catatan");
        logger.debug("start sahPenyelia untuk idPguna :" + idPguna);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            PermohonanPengguna pp = mohonpgunaDAO.findById(idPguna);
            if (pp != null) {
                updateStatus(pp, status, catatan);
            }
            tx.commit();
            addSimpleMessage("Pengguna : " + idPguna + " telah disahkan");

        } catch (Exception e) {
            addSimpleError("Error:" + e.getMessage());
            tx.rollback();
        }
        return new RedirectResolution(VerifyUserActionBean.class, "showForm");
//        return new JSP("uam/verify.jsp");
//        return showForm();
    }



    public boolean verifyPassword(String idPguna, String password) {

        if (uamService.checkPasswordAdmin(idPguna, password).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //    for pengesahan admin
  
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws Exception {
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public List<PermohonanPengguna> getListMohonPguna() {
        return listMohonPguna;
    }

    public void setListMohonPguna(List<PermohonanPengguna> listMohonPguna) {
        this.listMohonPguna = listMohonPguna;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordBaru() {
        return passwordBaru;
    }

    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    public String getIdPguna() {
        return idPguna;
    }

    public void setIdPguna(String idPguna) {
        this.idPguna = idPguna;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khairil
 */
@HttpCache(allow = false)
@UrlBinding("/util/reset_access")
public class ResetAccess extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(ResetAccess.class);
    private static final String VIEWRESET = "/WEB-INF/jsp/common/reset_access.jsp";
    String IDPengguna;

    public String getIDPengguna() {
        return IDPengguna;
    }

    public void setIDPengguna(String IDPengguna) {
        this.IDPengguna = IDPengguna;
    }
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().getSession().setAttribute("version", conf.getProperty("version"));
        //return new JSP("daftar/kemasukan_perincian_hakmilik.jsp").addParameter("popup", "true");
        return new ForwardResolution(VIEWRESET);
    }

    public Resolution resetAccess() {
        logger.debug("::reset access::");
        logger.debug("IDPengguna : " + IDPengguna);
        Pengguna peng = penggunaDao.findById(IDPengguna);

        if (peng != null) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();

            try {
                logger.info("Trying to update user status...");
                peng.setNoPengenalan(""); //temporary used for Status Online
                penggunaDao.update(peng);

            } catch (Exception ex) {
                tx.rollback();
                addSimpleError("Pengguna "+ IDPengguna +" tidak berjaya di reset");
                logger.info("Updating user status failed...");

            } finally {
                tx.commit();
                addSimpleMessage("Pengguna " + IDPengguna + " berjaya di reset");
                logger.info("User status updated...");
            }
        }else{
            addSimpleError("Pengguna " + IDPengguna + " tiada.Sila semak semula ID Pengguna untuk di reset");
        }
        return new ForwardResolution(VIEWRESET);
    }
}

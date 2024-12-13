package etanah.view;

import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import etanah.dao.PermohonanDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;

import etanah.model.Permohonan;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.hibernate.LockMode;
import org.hibernate.Session;

public class etanahActionBeanContext extends ActionBeanContext {

    public static final String KEY_USER = "_KEY_USER";

    public static final String KEY_WORK_DATA = "_KEY_WORK_DATA";

    @Inject
    private etanah.Configuration conf;

    private Permohonan permohonan;

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Pengguna getUser() {
        return (Pengguna) getRequest().getSession().getAttribute(KEY_USER);
    }

    public String getKodNegeri() {
        return conf.getProperty("kodNegeri");
    }

    public KodCawangan getKodCawangan() {
        return getUser().getKodCawangan();
    }

    public void setPermohonan(String idPermohonan) {
        permohonan = permohonanDAO.findById(idPermohonan);
        getRequest().getSession().setAttribute("permohonan", permohonan);
    }

    public Permohonan getPermohonan() {
        permohonan = (Permohonan) getRequest().getSession().getAttribute(
                "permohonan");
        sessionProvider.get().lock(permohonan, LockMode.NONE);
        return permohonan;
    }

    /**
     * Work data concept to avoid programmers use too many session attributes.
     * Each user should have only one work data. E.g. if he is working on a
     * wizard like pages, the pages can this data here. And when he move to
     * other wizard pages, the old data will be nullified and dispose, whereas
     * new data will overwrite it.
     * 
     * @return
     */
    public Object getWorkData() {
        return getRequest().getSession().getAttribute(KEY_WORK_DATA);
    }

    public void setWorkData(Object o) {
        HttpSession session = getRequest().getSession();
        session.removeAttribute(KEY_WORK_DATA); // remove old data if any to set
                                                // with new one
        session.setAttribute(KEY_WORK_DATA, o);
    }

    public void removeWorkdata() {
        HttpSession session = getRequest().getSession();
        session.removeAttribute(KEY_WORK_DATA); // remove old data if any to set
                                                // with new one
    }

}

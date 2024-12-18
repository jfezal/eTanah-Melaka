/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.DevIntegrationService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author faidzal
 */
@UrlBinding("/testUnit/regIntegration")
public class InitiateRegTestActionBean extends AbleActionBean {

    @Inject
    DevIntegrationService dis;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    

    public Resolution initiateNotaForm() {
        String urusan = (String) getContext().getRequest().getParameter("kodUrusan");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodUrusan kodUrusan = kodUrusanDAO.findById(urusan);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (kodUrusan != null) {
            //dis.intRegKelulusan(kodUrusan, peng, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "16", "T");
        }
        return new JSP("");
    }

    public Resolution initiateMHForm() {
        return new JSP("");
    }

    public void initiateNota(Permohonan permohonan, KodUrusan kodUrusan, Pengguna peng) {
        if (permohonan.getKodUrusan().getKod().equals("SBMS")) {
            //dis.intRegKelulusan(kodUrusan, peng, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "16", "T");
        } else if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {
        }
    }

    public void initiateMH() {
    }
}

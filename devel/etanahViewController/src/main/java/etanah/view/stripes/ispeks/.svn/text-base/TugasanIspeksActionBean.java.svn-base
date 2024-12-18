/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.PenyataPemungutTunai;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.service.ispeks.TugasanIspekService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/tugasan")
public class TugasanIspeksActionBean extends AbleActionBean {

    @Inject
    PenyataPemungutService penyataPemungutService;
@Inject
        TugasanIspekService tugasanIspekService;
    List<TugasanIspeksForm> senaraiTugasanPP;
    List<TugasanIspeksForm> senaraiTugasanJR;
    List<TugasanIspeksForm> senaraiTugasanBL;
    String idPengguna;

    @DefaultHandler
    public Resolution showForm() {
        String noPenyata = getContext().getRequest().getParameter("noPenyata");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPengguna = pengguna.getIdPengguna();
        String caw = pengguna.getKodCawangan().getKod();
        senaraiTugasanPP = penyataPemungutService.paparTugasan(pengguna, caw, "PP");
        senaraiTugasanBL = penyataPemungutService.paparTugasan(pengguna, caw, "BIL");
        senaraiTugasanJR = penyataPemungutService.paparTugasan(pengguna, caw, "JOR");
        return new JSP("ispeks/tugasan.jsp");
    }

    public Resolution validate() {
        String kodPeringkat = getContext().getRequest().getParameter("peringkat");
        String id = getContext().getRequest().getParameter("id");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String results = tugasanIspekService.validatePeringkatUser(kodPeringkat, pengguna, id);
        return new StreamingResolution("text/plain", results);
    }

    public List<TugasanIspeksForm> getSenaraiTugasanPP() {
        return senaraiTugasanPP;
    }

    public void setSenaraiTugasanPP(List<TugasanIspeksForm> senaraiTugasanPP) {
        this.senaraiTugasanPP = senaraiTugasanPP;
    }

    public List<TugasanIspeksForm> getSenaraiTugasanJR() {
        return senaraiTugasanJR;
    }

    public void setSenaraiTugasanJR(List<TugasanIspeksForm> senaraiTugasanJR) {
        this.senaraiTugasanJR = senaraiTugasanJR;
    }

    public List<TugasanIspeksForm> getSenaraiTugasanBL() {
        return senaraiTugasanBL;
    }

    public void setSenaraiTugasanBL(List<TugasanIspeksForm> senaraiTugasanBL) {
        this.senaraiTugasanBL = senaraiTugasanBL;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

}

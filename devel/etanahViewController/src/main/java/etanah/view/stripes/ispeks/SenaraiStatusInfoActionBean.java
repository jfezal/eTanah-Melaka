/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.StatusInfoIspeksDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.Bil;
import etanah.model.ispek.Journal;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.StatusInfoIspeks;
import etanah.service.ispeks.BilService;
import etanah.service.ispeks.IDDiSpeksService;
import etanah.service.ispeks.IspeksService;
import etanah.service.ispeks.IspeksStatus;
import etanah.service.ispeks.JournalService;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/status_info")
public class SenaraiStatusInfoActionBean extends AbleActionBean {

    @Inject
    IspeksService ispeksService;
    @Inject
    StatusInfoIspeksDAO statusInfoIspeksDAO;
    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    JournalService journalService;
    @Inject
    BilService bilService;
    List<StatusInfoIspeksForm> listStatusInfoIspeks;
    @Inject
    IDDiSpeksService iDDiSpeksService;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    String noPenyataR;
    String statusInfoId;
    String jenisFail;

    @DefaultHandler
    public Resolution showForm() {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        listStatusInfoIspeks = ispeksService.findStatusInfoCawangan(pengguna.getKodCawangan().getKod());
        return new JSP("ispeks/status_info.jsp");
    }

    public Resolution hantarReJana() throws IOException, MessagingException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        IspeksStatus status = new IspeksStatus();

        if (jenisFail.equals("PP")) {
            PenyataPemungut pp = penyataPemungutService.findByNoPenyata(noPenyataR);
            status = iDDiSpeksService.janaIddPP(pp.getId(), pp.getKodCaw(), pengguna);
        } else if (jenisFail.equals("JR")) {
            Journal journal = journalService.findByNoJurnal(noPenyataR);
            status = iDDiSpeksService.janaIddJournal(journal.getId(), journal.getKodCawangan(), pengguna);
        } else if (jenisFail.equals("BL")) {
            Bil bil = bilService.findByNoPenyata(noPenyataR);
            status = iDDiSpeksService.janaIddBil(bil.getId(), bil.getKodCawangan(), pengguna);
        }
        if ("Y".equals(status.getStatusGPG()) && "Y".equals(status.getStatusIdd()) && "Y".equals(status.getStatusSftp())) {
            addSimpleMessage("Proses telah berjaya dan dihantar");

        } else {
        }
        listStatusInfoIspeks = ispeksService.findStatusInfoCawangan(pengguna.getKodCawangan().getKod());
        return new JSP("ispeks/status_info.jsp");
    }

    public List<StatusInfoIspeksForm> getListStatusInfoIspeks() {
        return listStatusInfoIspeks;
    }

    public void setListStatusInfoIspeks(List<StatusInfoIspeksForm> listStatusInfoIspeks) {
        this.listStatusInfoIspeks = listStatusInfoIspeks;
    }



    public String getNoPenyataR() {
        return noPenyataR;
    }

    public void setNoPenyataR(String noPenyataR) {
        this.noPenyataR = noPenyataR;
    }

    public String getJenisFail() {
        return jenisFail;
    }

    public void setJenisFail(String jenisFail) {
        this.jenisFail = jenisFail;
    }

    public String getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(String statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

}

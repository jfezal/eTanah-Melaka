/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPerbicaraanKehadiranDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPerbicaraan;
import etanah.model.PermohonanPerbicaraanKehadiran;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_kehadiran")
public class MaklumatKehadiranActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatKehadiranActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanPerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    ListUtil listUtil;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<PermohonanPerbicaraanKehadiran> listPerbicaraanKehadiran = new ArrayList<PermohonanPerbicaraanKehadiran>();
    private String idPermohonan;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private PermohonanPerbicaraanKehadiran perbicaraanKehadiran;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_Kehadiran.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/maklumat_Kehadiran.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();

            List<PermohonanPerbicaraan> perBicaraList = new ArrayList<PermohonanPerbicaraan>();
            perBicaraList = consentService.findSenaraiMohonBicaraTangguh(idPermohonan, "TANGGUH1");

            if (perBicaraList.isEmpty()) {
                permohonanPerbicaraan = consentService.findMohonBicaraByIdMohon(idPermohonan);
                listPerbicaraanKehadiran = consentService.getSenaraiPerbicaraanKehadiranByIdBicara(permohonanPerbicaraan.getIdPerbicaraan());

            } else {
                listPerbicaraanKehadiran = perBicaraList.get(0).getSenaraiKehadiran();
            }
        }
    }

    public Resolution addWakil() {
        String idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        perbicaraanKehadiran = perbicaraanKehadiranDAO.findById(Long.parseLong(idKehadiran));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_wakil.jsp").addParameter("popup", "true");
    }

    public Resolution viewWakil() {
        String idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        perbicaraanKehadiran = perbicaraanKehadiranDAO.findById(Long.parseLong(idKehadiran));
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_wakil.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        consentService.simpanMultipleBicaraKehadiran(listPerbicaraanKehadiran);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("consent/maklumat_Kehadiran.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBicaraHadir() {

        String hadir = getContext().getRequest().getParameter("hadir");
        String catatan = getContext().getRequest().getParameter("catatan");
        String row = getContext().getRequest().getParameter("row");

        if (!listPerbicaraanKehadiran.isEmpty()) {
            PermohonanPerbicaraanKehadiran kehadiran = new PermohonanPerbicaraanKehadiran();
            kehadiran = listPerbicaraanKehadiran.get(Integer.parseInt(row));
            kehadiran.setHadir(hadir.charAt(0));
            kehadiran.setKeterangan(catatan);
            consentService.simpanPerbicaraanKehadiran(kehadiran);
        }
        return new RedirectResolution("/consent/maklumat_kehadiran?getSenaraiKehadiran").addParameter("tab", "true");
    }

    public Resolution simpanWakil() {
        PermohonanPerbicaraanKehadiran kehadiranTemp = new PermohonanPerbicaraanKehadiran();
        kehadiranTemp = perbicaraanKehadiranDAO.findById(perbicaraanKehadiran.getIdKehadiran());

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit = kehadiranTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        kehadiranTemp.setInfoAudit(infoAudit);
        kehadiranTemp.setWakilNama(perbicaraanKehadiran.getWakilNama());
        kehadiranTemp.setWakilKodPengenalan(perbicaraanKehadiran.getWakilKodPengenalan());
        kehadiranTemp.setWakilNoPengenalan(perbicaraanKehadiran.getWakilNoPengenalan());
        consentService.simpanPerbicaraanKehadiran(kehadiranTemp);

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        if (kehadiranTemp != null) {
            return new RedirectResolution("/consent/maklumat_kehadiran?getSenaraiKehadiran").addParameter("tab", "true");
        }
        return new StreamingResolution("text/plain", "1");
    }

    public Resolution deleteWakil() {
        PermohonanPerbicaraanKehadiran kehadiranTemp = new PermohonanPerbicaraanKehadiran();
        kehadiranTemp = perbicaraanKehadiranDAO.findById(perbicaraanKehadiran.getIdKehadiran());

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit = kehadiranTemp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        kehadiranTemp.setInfoAudit(infoAudit);
        kehadiranTemp.setWakilNama(null);
        kehadiranTemp.setWakilKodPengenalan(null);
        kehadiranTemp.setWakilNoPengenalan(null);
        consentService.simpanPerbicaraanKehadiran(kehadiranTemp);

        addSimpleMessage("Maklumat Wakil Berjaya Dihapus");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        if (kehadiranTemp != null) {
            return new RedirectResolution("/consent/maklumat_kehadiran?getSenaraiKehadiran").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution getSenaraiKehadiran() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_Kehadiran.jsp").addParameter("tab", "true");
    }

    public Resolution addKehadiranPopup() {
        return new JSP("consent/maklumat_kehadiran_popup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKehadiranPopup() {

        permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();
        permohonanPerbicaraan = consentService.findMohonBicaraNotTangguh(idPermohonan);

        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
        perbicaraanKehadiran.setPerbicaraan(permohonanPerbicaraan);
        perbicaraanKehadiran.setInfoAudit(infoAudit);
        consentService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);

        if (perbicaraanKehadiran != null) {
            return new RedirectResolution("/consent/maklumat_kehadiran?getSenaraiKehadiran").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");
    }

    public Resolution deleteMultipleKehadiran() {

        String[] uids = getContext().getRequest().getParameterValues("idKehadiran");
        if (uids.length > 0) {
            consentService.deleteMohonBicaraHadir(uids);
        }
        return new RedirectResolution("/consent/maklumat_kehadiran?getSenaraiKehadiran").addParameter("tab", "true");
    }

    public List<PermohonanPerbicaraanKehadiran> getListPerbicaraanKehadiran() {
        return listPerbicaraanKehadiran;
    }

    public void setListPerbicaraanKehadiran(List<PermohonanPerbicaraanKehadiran> listPerbicaraanKehadiran) {
        this.listPerbicaraanKehadiran = listPerbicaraanKehadiran;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PermohonanPerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }
}

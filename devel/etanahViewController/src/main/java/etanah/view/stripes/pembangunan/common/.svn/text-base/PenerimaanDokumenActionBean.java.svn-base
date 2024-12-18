/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenyerahSokonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PenyerahSokongan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author john
 */
@UrlBinding("/pembangunan/melaka/terima_dokumen")
public class PenerimaanDokumenActionBean extends AbleActionBean {

    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanService mohonFasaService;
    @Inject
    PenyerahSokonganDAO penyerahSokonganDAO;
    private static final Logger LOG = Logger.getLogger(PenerimaanDokumenPrecompActionBean.class);
    private String namaPenyerah;
    private Date tarikhTerima;
    private String noTel;
    private String tugasanUtil = Boolean.FALSE.toString();
    String stageId;
    @Inject
    PembangunanService pembangunanService;
Pengguna peng;
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/common/pengesahan_terima_dokumen.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        saveDate(idPermohonan);

        return new RedirectResolution(PenerimaanDokumenPrecompActionBean.class);

    }
 public void saveDate(String idPermohonan) {
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        PenyerahSokongan ps = pembangunanService.findPenyerahSokonganByIdMohon(idPermohonan, stageId);
        if (ps != null) {
        } else {
            ps = new PenyerahSokongan();
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        ps.setMohon(mohon);
        ps.setNamaPenyerah(namaPenyerah);
        ps.setIdAliran(stageId);
        ps.setTarikhTerima(tarikhTerima);
        ps.setNoTel(noTel);
        ps.setInfoAudit(ia);
        pembangunanService.savePenyerahSokongan(ps);
    }

    public Resolution selesaiTugasan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");
        saveDate(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        boolean statusSelesai = false;
        if (stageId.equals("")) {
            if (sBMSIntegrationFlowService.initiateRencanaMenteri(permohonan)) {
                sBMSIntegrationFlowService.deleteTugasanTable(permohonan);
//            sBMSIntegrationFlowService.insertTugasanTable(permohonanDAO.findById("0401DEV2017000171"), "JKBB", peng.getIdPengguna());
            }
        } else if (stageId.equals("terima_pelan_b2")) {
            if (sBMSIntegrationFlowService.initiateTerimaPelanB2(permohonan)) {
                sBMSIntegrationFlowService.deleteTugasanTable(permohonan);
//            sBMSIntegrationFlowService.insertTugasanTable(permohonanDAO.findById("0401DEV2017000171"), "JKBB", peng.getIdPengguna());
            }
        } else if (stageId.equals("terima_pelan_b1")) {
            if (sBMSIntegrationFlowService.initiateTerimaPelanB1(permohonan)) {
                sBMSIntegrationFlowService.deleteTugasanTable(permohonan);
//            sBMSIntegrationFlowService.insertTugasanTable(permohonanDAO.findById("0401DEV2017000171"), "JKBB", peng.getIdPengguna());
            }
        }

        return new RedirectResolution("/utiliti/tugasan");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");
        PenyerahSokongan ps = pembangunanService.findPenyerahSokonganByIdMohon(idPermohonan, stageId);
        if (ps != null) {
            namaPenyerah = ps.getNamaPenyerah();
            noTel = ps.getNoTel();
            tarikhTerima = ps.getTarikhTerima();
        }
                peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    public String getTugasanUtil() {
        return tugasanUtil;
    }

    public void setTugasanUtil(String tugasanUtil) {
        this.tugasanUtil = tugasanUtil;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.LupusPermitDAO;
import etanah.dao.PermitDAO;
import etanah.model.InfoAudit;
import etanah.model.LupusPermit;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/borang7")
public class Borang7ActionBean extends AbleActionBean {

    @Inject
    PermohonanService permohonanService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PelupusanUtiliti pUtiliti;
    @Inject
    PermitDAO permitDAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    private static final Logger LOG = Logger.getLogger(Borang7ActionBean.class);
    private LupusPermit lupusPermit;
    private int permitNo;
    private Date tarikh;
    private Date tarikhMula;
    private Date tarikhTamat;
    private Date tujuanPermit;
    private String peruntukanTambahan;
    private String idPermohonan;
    private String strukturBolehBina;
    private Permohonan permohonan;
    private Pengguna pguna;
    private Permit permit;
    private PermohonanTuntutanKos mohonTuntutKos;
    private PermohonanPermitItem mohonPermitItem;
    private boolean viewOnly = false;

    @DefaultHandler
    public Resolution showForm() {
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/Borang_7.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/Borang_7.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanService.findById(idPermohonan);
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
        mohonPermitItem = pelupusanService.findPermohonanPermitItemByIdMohon(idPermohonan);
        permit = pelupusanService.getPermitbyIdMohon(idPermohonan);


    }

    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        String idPermit = (String) getContext().getRequest().getParameter("lupusPermit.id");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        Permit permitTemp = new Permit();
        permitTemp = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        LOG.info("pguna->" + pguna);
        if ((permitTemp != null)) {
//            permitTemp= pelupusanService.findPermitByIdPermohonan(idPermohonan);
            infoAudit = permitTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permitTemp = new Permit();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            Permit perm = pelupusanService.getMaxOfNoPermitByJenisPermit("F");
            permitTemp.setNoPermit(perm != null ? String.valueOf(Integer.parseInt(perm.getNoPermit()) + 1) : "1");
        }


        if (StringUtils.isEmpty(permitTemp.getNoPermit())) {
            Permit perm = pelupusanService.getMaxOfNoPermitByJenisPermit("F");
            permitTemp.setNoPermit(perm != null ? String.valueOf(Integer.parseInt(perm.getNoPermit()) + 1) : "1");
        }

        permitTemp.setInfoAudit(infoAudit);
        permitTemp.setTarikhPermit(permit.getTarikhPermit());
        permitTemp.setTarikhPermitMula(permit.getTarikhPermitMula());
        permitTemp.setTarikhpermitAkhir(permit.getTarikhpermitAkhir());
        permitTemp.setPeruntukanTambahan(permit.getPeruntukanTambahan());
        permitTemp.setPermohonan(permohonan);
        permitTemp.setKodJenisPermit(kodJenisPermitDAO.findById("F"));
        pelupusanService.saveOrUpdate(permitTemp);

        addSimpleMessage("Maklumat telah Disimpan");
        LOG.info(":: no permit : " + permitTemp.getNoPermit());
        rehydrate();

        return new JSP("pelupusan/Borang_7.jsp").addParameter("tab", "true");
    }

    public int getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(int permitNo) {
        this.permitNo = permitNo;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(Date tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public Date getTujuanPermit() {
        return tujuanPermit;
    }

    public void setTujuanPermit(Date tujuanPermit) {
        this.tujuanPermit = tujuanPermit;
    }

    public String getStrukturBolehBina() {
        return strukturBolehBina;
    }

    public void setStrukturBolehBina(String strukturBolehBina) {
        this.strukturBolehBina = strukturBolehBina;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermohonanPermitItem getMohonPermitItem() {
        return mohonPermitItem;
    }

    public void setMohonPermitItem(PermohonanPermitItem mohonPermitItem) {
        this.mohonPermitItem = mohonPermitItem;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }
}

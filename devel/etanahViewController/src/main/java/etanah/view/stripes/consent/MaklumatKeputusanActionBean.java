/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author muhammad.amin
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/consent/maklumat_keputusan")
public class MaklumatKeputusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    ConsentPtdService consentService;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonanPTD;
    private FasaPermohonan fasaPermohonanJKTL;
    private FasaPermohonan fasaPermohonanPTG;
    private String syorPtg;
    private String syorTanahLadang;
    private String syorPtd;

    @DefaultHandler
    public Resolution show() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/maklumat_keputusan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            fasaPermohonanPTD = consentService.findMohonFasaByStage(idPermohonan, "Stage2");
            fasaPermohonanJKTL = consentService.findMohonFasaByStage(idPermohonan, "Stage7");
            fasaPermohonanPTG = consentService.findMohonFasaByStage(idPermohonan, "Stage6");

            if (fasaPermohonanPTD.getKeputusan() != null) {
                syorPtd = fasaPermohonanPTD.getKeputusan().getKod();
            }
            if (fasaPermohonanJKTL.getKeputusan() != null) {
                syorTanahLadang = fasaPermohonanJKTL.getKeputusan().getKod();
            }
            if (fasaPermohonanPTG.getKeputusan() != null) {
                syorPtg = fasaPermohonanPTG.getKeputusan().getKod();
            }
        }
    }

    public Resolution simpan() {

        if (syorPtd == null || syorTanahLadang == null || syorPtg == null) {
            if (syorPtd == null) {
                addSimpleError("Sila masukkan syor Pentadbir Tanah");
            } else if (syorTanahLadang == null) {
                addSimpleError("Sila masukkan syor Jawatankuasa Tanah Ladang");
            } else if (syorPtg == null) {
                addSimpleError("Sila masukkan syor Pengarah Tanah dan Galian");
            }
        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            FasaPermohonan fasaPermohonanPTDTemp = consentService.findMohonFasaByStage(idPermohonan, "Stage2");
            FasaPermohonan fasaPermohonanJKTLTemp = consentService.findMohonFasaByStage(idPermohonan, "Stage7");
            FasaPermohonan fasaPermohonanPTGTemp = consentService.findMohonFasaByStage(idPermohonan, "Stage6");
            Permohonan permohonanTemp = permohonanDAO.findById(idPermohonan);

            InfoAudit infoAudit = new InfoAudit();

            //SAVE SYOR PENTADBIR TANAH
            if (fasaPermohonanPTDTemp != null) {
                infoAudit = fasaPermohonanPTDTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            fasaPermohonanPTDTemp.setKeputusan(kodKeputusanDAO.findById(syorPtd));
            fasaPermohonanPTDTemp.setKeputusanUntuk("PENTADBIR TANAH");
            fasaPermohonanPTDTemp.setTarikhKeputusan(new java.util.Date());
            fasaPermohonanPTDTemp.setInfoAudit(infoAudit);

            //SAVE SYOR JAWATANKUASA TANAH LADANG
            if (fasaPermohonanJKTLTemp != null) {
                infoAudit = fasaPermohonanJKTLTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            fasaPermohonanJKTLTemp.setKeputusan(kodKeputusanDAO.findById(syorTanahLadang));
            fasaPermohonanJKTLTemp.setKeputusanUntuk("JAWATANKUASA TANAH LADANG");
            fasaPermohonanJKTLTemp.setTarikhKeputusan(new java.util.Date());
            fasaPermohonanJKTLTemp.setInfoAudit(infoAudit);

            //SAVE SYOR JAWATANKUASA TANAH LADANG INTO TABLE PERMOHONAN
            permohonanTemp.setKeputusan(kodKeputusanDAO.findById(syorTanahLadang));
            permohonanTemp.setTarikhKeputusan(new java.util.Date());
            permohonanTemp.setKeputusanOleh(pengguna);
            
            //SAVE SYOR PTG
            if (fasaPermohonanPTGTemp != null) {
                infoAudit = fasaPermohonanPTGTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }

            fasaPermohonanPTGTemp.setKeputusan(kodKeputusanDAO.findById(syorPtg));
            fasaPermohonanPTGTemp.setKeputusanUntuk("PTG");
            fasaPermohonanPTGTemp.setTarikhKeputusan(new java.util.Date());
            fasaPermohonanPTGTemp.setInfoAudit(infoAudit);

            consentService.simpanPermohonan(permohonanTemp);
            consentService.simpanFasaPermohonan(fasaPermohonanPTDTemp);
            consentService.simpanFasaPermohonan(fasaPermohonanJKTLTemp);
            consentService.simpanFasaPermohonan(fasaPermohonanPTGTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("consent/maklumat_keputusan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getSyorPtd() {
        return syorPtd;
    }

    public void setSyorPtd(String syorPtd) {
        this.syorPtd = syorPtd;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getSyorTanahLadang() {
        return syorTanahLadang;
    }

    public void setSyorTanahLadang(String syorTanahLadang) {
        this.syorTanahLadang = syorTanahLadang;
    }
}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.PermohonanAtasPihakBerkepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.consent.PihakConsentActionBean;
import etanah.view.stripes.consent.PihakPenerimaActionBean;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/common/mohon_pihak")
public class PermohonanPihakActionBean extends AbleActionBean {

    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakKepentinganDAO;
    @Inject
    PermohonanAtasPihakBerkepentinganService permohonanAtasPihakBerkepentinganService;
    private List<PermohonanPihak> mohonPihakList;
    private Permohonan permohonan;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/kemaskini_senarai_mohon_pihak.jsp").addParameter("tab", "true");
    }

    public Resolution save() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
//        String syerA = getContext().getRequest().getParameter("syerA");
//        String syerB = getContext().getRequest().getParameter("syerB");
//        Fraction f = Fraction.getFraction(Integer.parseInt(syerA), Integer.parseInt(syerB));
//        Fraction f = new Fraction(Integer.parseInt(syerA), Integer.parseInt(syerB));
        Pihak p = null;
        if (StringUtils.isNotEmpty(idPihak)) {
            p = pihakService.findById(idPihak);
        }
        KodJenisPihakBerkepentingan kod = kodJenisPihakKepentinganDAO.findById("PPG");
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        PermohonanPihak pp = new PermohonanPihak();
        pp.setPihak(p);
        pp.setPermohonan(permohonan);
        pp.setJenis(kod);
        pp.setSyerPembilang(0);
        pp.setSyerPenyebut(0);
        pp.setInfoAudit(info);
        permohonanPihakService.saveOrUpdate(pp);
//        return new JSP("daftar/kemaskini_senarai_mohon_pihak.jsp").addParameter("tab", "true");
        return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
    }

    public Resolution copy() {
        Permohonan p = permohonan.getPermohonanSebelum();
        mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(p.getIdPermohonan());
        for (PermohonanPihak permohonanPihak : mohonPihakList) {
            PermohonanPihak pp = new PermohonanPihak();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            pp.setInfoAudit(info);
            pp.setPermohonan(permohonan);
            pp.setPihak(permohonanPihak.getPihak());
            pp.setSyerPembilang(permohonanPihak.getSyerPembilang());
            pp.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
            pp.setJenis(permohonanPihak.getJenis());
            permohonanPihakService.saveOrUpdate(pp);
        }
        return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
    }

    public Resolution delete() {
        String urusan = getContext().getRequest().getParameter("urusan");
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if (StringUtils.isNotBlank(idPermohonanPihak)) {
            PermohonanPihak pp = permohonanPihakService.findById(idPermohonanPihak);
            permohonanPihakService.delete(pp);
        }
        if (StringUtils.isNotBlank(urusan)) {
            if (urusan.equals("pajakan")) {
                return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganPajakanForm").addParameter("tab", "true");
            } else if (urusan.equals("pnpa")) {
                return new RedirectResolution(PihakBerkepentinganActionBean.class, "showPihakBerkepentinganPemegangAmanahForm").addParameter("tab", "true");
            }
        }

        return new RedirectResolution("/pihak_berkepentingan?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
    }

    public Resolution deleteMultiplePihak() {
        String returnUrl = "/pihak_berkepentingan?getSenaraiHakmilikKepentingan";
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String pajakan = getContext().getRequest().getParameter("pajakan");
        String[] uids = getContext().getRequest().getParameterValues("idPermohonanPihak");
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (uids.length > 0) {
            permohonan = permohonanService.findById(idMohon);
            if (permohonan != null) {
                KodUrusan ku = permohonan.getKodUrusan();
                List<PermohonanAtasPihakBerkepentingan> tmp = new ArrayList<PermohonanAtasPihakBerkepentingan>();

                for (String idPermohonanPihak : uids) {
                    if (ku.getKod().equals("TRPA")) {
                        List<PermohonanAtasPihakBerkepentingan> senarai = permohonanAtasPihakBerkepentinganService.findByPermohonanPihak(idPermohonanPihak);
                        for (PermohonanAtasPihakBerkepentingan pab : senarai) {
                            if (pab == null) {
                                continue;
                            }
                            pab.setPermohonanPihak(null);
                            tmp.add(pab);
                        }
                    }
                }
                permohonanAtasPihakBerkepentinganService.save(tmp);
            }
            permohonanPihakService.delete(uids);
        }

        if (StringUtils.isNotBlank(pajakan) && pajakan.equals("true")) {
            return new RedirectResolution("/pihak_berkepentingan?showPihakKepentinganPajakanForm&idHakmilik=" + idHakmilik).addParameter("tab", "true");
        } else if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakBerkepentinganActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution(returnUrl).addParameter("tab", "true");
        }
    }

    public Resolution deleteMultiplePihakConsent() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String[] uids = getContext().getRequest().getParameterValues("idPermohonanPihak");
        if (uids.length > 0) {
            permohonanPihakService.delete(uids);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution("/consent/pihak_consent?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }
    }

    public Resolution deleteMultiplePihakPenerima() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String[] uids = getContext().getRequest().getParameterValues("idPermohonanPihak");
        if (uids.length > 0) {
            permohonanPihakService.delete(uids);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakPenerimaActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution("/consent/pihak_penerima?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
        }
    }

    public Resolution deleteMultipleWaris() {
        String idHakmilik = getContext().getRequest().getParameter("hakmilik");
        String[] uids = getContext().getRequest().getParameterValues("idPermohonanPihak");
        if (uids.length > 0) {
            permohonanPihakService.delete(uids);
        }

        if (StringUtils.isNotBlank(idHakmilik)) {
            return new RedirectResolution(PihakConsentActionBean.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
        } else {
            return new RedirectResolution("/consent/kemasukan_waris").addParameter("tab", "true");
        }
    }

    public Resolution deletePenerimaTurunMilik() {
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if (StringUtils.isNotBlank(idPermohonanPihak)) {
            PermohonanPihak pp = permohonanPihakService.findById(idPermohonanPihak);
            permohonanPihakService.delete(pp);
        }
        return new RedirectResolution("consent/pihak_turun_milik?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
    }

    public Resolution deleteSiMati() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (StringUtils.isNotBlank(idPihak)) {
            PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan, Long.valueOf(idPihak));
            permohonanPihakService.delete(pp);
        }
        return new RedirectResolution("consent/pihak_turun_milik?getSenaraiHakmilikKepentingan").addParameter("tab", "true");
    }

    public Resolution delete2() {
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if (StringUtils.isNotBlank(idPermohonanPihak)) {
            PermohonanPihak pp = permohonanPihakService.findById(idPermohonanPihak);
            permohonanPihakService.delete(pp);
        }
        return new RedirectResolution("/pihak_berkepentingan?senaraiHakmilikPindahMilikBerkepentingan").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!delete", "!deleteMultiplePihak", "!deleteMultiplePihakConsent"})
    public void rehydrate() {
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idMohon != null) {
            permohonan = permohonanService.findById(idMohon);
//            mohonPihakList = permohonanPihakService.getSenaraiPmohonPihak(idMohon);
            mohonPihakList = permohonan.getSenaraiPihak();
        }
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }
}

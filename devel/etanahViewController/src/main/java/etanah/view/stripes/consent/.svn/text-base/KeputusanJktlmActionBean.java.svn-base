/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/keputusan_jktlm")
public class KeputusanJktlmActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
//    private String tarikhKeputusan;
    private String keputusan;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(KeputusanJktlmActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/keputusan_jktlm.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage7");
            permohonanRujukanLuar = conService.findMohonRujukanNotTangguh2(idPermohonan);

            if (permohonan.getKeputusan() != null) {
                keputusan = permohonan.getKeputusan().getKod();
            }

//            if (permohonan.getKeputusan() != null) {
//                keputusan = permohonan.getKeputusan().getKod();
//
//                if (fasaPermohonan.getTarikhKeputusan() != null) {
//                    tarikhKeputusan = sdf.format(fasaPermohonan.getTarikhKeputusan());
//                }
//            } else {
//                if (permohonanRujukanLuar != null && permohonanRujukanLuar.getTarikhSidang() != null) {
//                    tarikhKeputusan = sdf.format(permohonanRujukanLuar.getTarikhSidang());
//                } else {
//                    tarikhKeputusan = sdf.format(new java.util.Date());
//                }
//            }
        }
    }

    public Resolution simpan() throws ParseException {

        LOG.info("::: SAVE KEPUTUSAN JKTLM");
        LOG.info("::: KEPUTUSAN : " + keputusan);
        LOG.info("::: SEBAB TOLAK : " + permohonan.getUlasan());
        LOG.info("::: ULASAN : " + fasaPermohonan.getUlasan());
//        LOG.info("::: TARIKH : " + tarikhKeputusan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Permohonan permohonanTemp = permohonanDAO.findById(idPermohonan);
        FasaPermohonan fasaPermohonanTemp = conService.findMohonFasaByStage(idPermohonan, "Stage7");

        InfoAudit infoAudit = new InfoAudit();

        if (permohonanTemp == null) {
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

//        if (StringUtils.isNotBlank(tarikhKeputusan)) {
//            Date trh = sdf.parse(tarikhKeputusan);
//            permohonanTemp.setTarikhKeputusan(trh);
//        }
        permohonanTemp.setTarikhKeputusan(permohonanRujukanLuar.getTarikhSidang());

        if (keputusan != null) {
            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonanTemp.setKeputusan(kodKeputusan);
        }

        permohonanTemp.setInfoAudit(infoAudit);
        permohonanTemp.setUlasan(permohonan.getUlasan());

        permohonanTemp.setKeputusanOleh(pengguna);
        conService.simpanPermohonan(permohonanTemp);

        if (fasaPermohonanTemp == null) {
            infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            infoAudit = fasaPermohonanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

//        if (StringUtils.isNotBlank(tarikhKeputusan)) {
//            Date trh = sdf.parse(tarikhKeputusan);
//            fasaPermohonanTemp.setTarikhKeputusan(trh);
//        }

        fasaPermohonanTemp.setTarikhKeputusan(permohonanRujukanLuar.getTarikhSidang());

        if (keputusan != null) {
            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            fasaPermohonanTemp.setKeputusan(kodKeputusan);
        }
        fasaPermohonanTemp.setInfoAudit(infoAudit);
        fasaPermohonanTemp.setUlasan(fasaPermohonan.getUlasan());

        conService.simpanFasaPermohonan(fasaPermohonanTemp);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/consent/keputusan_jktlm.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
}

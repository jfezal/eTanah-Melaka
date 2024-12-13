/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodRujukan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/consent/keputusan_permohonan")
public class KeputusanPermohonanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService consentService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String idHakmilik;
    private static final Logger log = Logger.getLogger(KeputusanPermohonanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/keputusan_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        return new JSP("consent/keputusan_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution showNoSijil() {
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        return new JSP("consent/keputusan_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        log.info("Id Permohonan: " + idPermohonan);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("PJKTL")
                    || permohonan.getKodUrusan().getKod().equals("RJKTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {

                PermohonanRujukanLuar permohonanRujLuarTemp = null;//new PermohonanRujukanLuar();

                if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("PJKTL")) {
                    permohonanRujLuarTemp = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");
                } else {
                    permohonanRujLuarTemp = consentService.findMohonRujukanNotTangguh(idPermohonan);
                }

                if (permohonanRujLuarTemp != null) {
                    infoAudit = new InfoAudit();
                    infoAudit = permohonanRujLuarTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }

                if (permohonanRujLuarTemp != null) {
                    permohonanRujLuarTemp.setNoRujukan(permohonanRujLuarTemp.getNoSidang());
                    permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
                    permohonanRujLuarTemp.setPermohonan(permohonan);
                    permohonanRujLuarTemp.setInfoAudit(infoAudit);

                    KodRujukan kodRujukan = new KodRujukan();
                    kodRujukan.setKod("FL");
                    permohonanRujLuarTemp.setKodRujukan(kodRujukan);

                    consentService.simpanRujukanLuar(permohonanRujLuarTemp);
                }
            }

            if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("PJKTL")) {
                permohonanRujukanLuar = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");
            } else if (permohonan.getKodUrusan().getKod().equals("RJKTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(idPermohonan);
            } else {
                if (!permohonan.getSenaraiRujukanLuar().isEmpty()) {
                    permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                }
            }

//            StringBuilder sb = new StringBuilder();
//            if (!permohonan.getSenaraiHakmilik().isEmpty()) {
//                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
//                    if (hp == null) {
//                        continue;
//                    }
//                    Hakmilik h = hp.getHakmilik();
//                    if (sb.length() > 0) {
//                        sb.append(",");
//                    }
//                    sb.append(h.getIdHakmilik());
//                }
//            }
//            idHakmilik = sb.toString();
        }
    }

    public Resolution simpan() {

        if (permohonanRujukanLuar.getNoRujukan() == null) {
            addSimpleError("Sila Masukan Nombor Sijil");
        } else {
            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("PJKTL")) {
                permohonanRujLuarTemp = consentService.findMohonRujukanByNamaNotTangguh(idPermohonan, "MESYUARAT JKTL");
            } else if (permohonan.getKodUrusan().getKod().equals("RJKTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                permohonanRujLuarTemp = consentService.findMohonRujukanNotTangguh(idPermohonan);
            } else {
                if (!(permohonan.getSenaraiRujukanLuar().isEmpty())) {
                    permohonanRujLuarTemp = permohonan.getSenaraiRujukanLuar().get(0);
                }
            }

            if (permohonanRujLuarTemp != null) {
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            permohonanRujLuarTemp.setNoRujukan(permohonanRujukanLuar.getNoRujukan());
            permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);

            consentService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

        }
        getContext().getRequest().setAttribute("noSijil", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/keputusan_permohonan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
}

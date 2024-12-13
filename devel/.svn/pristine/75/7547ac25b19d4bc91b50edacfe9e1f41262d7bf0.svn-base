/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kemasukan_ulasan")
public class KemasukanUlasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private PermohonanKertasKandungan kertasKandungan;
    private Pengguna pengguna;
    private String ulasan;

    @DefaultHandler
    public Resolution showForm() {
        showForm34();
        return new JSP("consent/kemasukan_ulasan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm34() {
        return new JSP("consent/kemasukan_ulasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas perKertas = new PermohonanKertas();
                perKertas = permohonan.getSenaraiKertas().get(i);

                for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {

                    kertasKandungan = perKertas.getSenaraiKandungan().get(j);

                    if (pengguna.getPerananUtama() != null) {
                        if (kertasKandungan.getBil() == 2) {

                            if (pengguna.getPerananUtama().getKod().equals("56")) {
                                ulasan = kertasKandungan.getKandungan();
                            }

                        } else if (kertasKandungan.getBil() == 3) {

                            if (pengguna.getPerananUtama().getKod().equals("57")) {
                                ulasan = kertasKandungan.getKandungan();
                            }
                        }
                    }
                }
            }

        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit =
                    permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS JKTL");

        conService.simpanPermohonanKertas(permohonanKertas);

        if (StringUtils.isBlank(ulasan)) {
            ulasan = " ";
        }

        if (kertasKandungan != null) {

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j <
                        permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (pengguna.getPerananUtama() != null) {
                        if (kertasKdgn.getBil() == 2) {

                            if (pengguna.getPerananUtama().getKod().equals("56")) {
                                kertasKdgn.setKandungan(ulasan);
                            }

                        } else if (kertasKdgn.getBil() == 3) {

                            if (pengguna.getPerananUtama().getKod().equals("57")) {
                                kertasKdgn.setKandungan(ulasan);
                            }

                        }
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }

            }

        } else {

            infoAudit.setTarikhMasuk(new java.util.Date());
            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
            kertasKdgn.setKertas(permohonanKertas);
            if (pengguna.getPerananUtama() != null) {
                if (pengguna.getPerananUtama().getKod().equals("56")) {
                    kertasKdgn.setBil(2);
                } else if (pengguna.getPerananUtama().getKod().equals("57")) {
                    kertasKdgn.setBil(3);
                }
            }

            kertasKdgn.setInfoAudit(infoAudit);
            kertasKdgn.setKandungan(ulasan);
            kertasKdgn.setCawangan(permohonan.getCawangan());
            conService.simpanPermohonanKertasKandungan(kertasKdgn);

        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/consent/kemasukan_ulasan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}

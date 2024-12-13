/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.service.LaporanTanahService;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@HttpCache(allow = false)
@UrlBinding("/consent/maklumat_hakmilik")
public class MaklumatHakmilikActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    HakmilikUrusanService hUService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    String idHakmilik;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<LaporanTanah> laporanTanahList;
    private List<HakmilikUrusan> senaraiHakmilikUrusan;
    private List<HakmilikUrusan> senaraiHakmilikUrusanGD;
    private List<HakmilikUrusan> senaraiUrusanRizab;
    private List<PermohonanPihak> senaraiPemegangGadai;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("consent/maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparanHakmilik() {
        return new JSP("consent/paparan_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikPopup() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiHakmilikUrusan = new ArrayList<HakmilikUrusan>();
        senaraiHakmilikUrusan = hUService.findHakmilikUrusanActiveByIdHakmilikAndUrusan(idHakmilik, "ADAT");
        senaraiHakmilikUrusanGD = new ArrayList<HakmilikUrusan>();
        senaraiHakmilikUrusanGD = hUService.findHakmilikUrusanActiveByIdHakmilikAndUrusan(idHakmilik, "GD");
        senaraiUrusanRizab = new ArrayList<HakmilikUrusan>();
        senaraiUrusanRizab = hUService.findHakmilikUrusanTanahRizab(idHakmilik);

        if (senaraiHakmilikUrusanGD != null) {
            if (senaraiHakmilikUrusanGD.size() > 0) {

                HakmilikUrusan hakmilikUrusan = senaraiHakmilikUrusanGD.get(0);
                
                if (hakmilikUrusan.getIdPerserahan() != null && hakmilikUrusan.getHakmilik().getIdHakmilik() != null) {
                    senaraiPemegangGadai = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(hakmilikUrusan.getIdPerserahan(), "PG", hakmilikUrusan.getHakmilik().getIdHakmilik());
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_hakmilik_popup.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = consentService.findSenaraiHakmilikPermohonan(idPermohonan);

            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/MMK")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/JKTL2")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Negeri/Consent/pmwna")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent2/Project1/RJT")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent2/Project1/RJD")) {

                laporanTanahList = laporanTanahService.getSenaraiLaporanTanah(idPermohonan);

                if (laporanTanahList.isEmpty()) {

                    Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);

                    for (HakmilikPermohonan hakPermohonan : hakmilikPermohonanList) {

                        infoAudit.setTarikhMasuk(new java.util.Date());
                        LaporanTanah laporanTanah = new LaporanTanah();

                        //KES RAYUAN URUSAN MMK
                        if (permohonan.getPermohonanSebelum() != null) {

                            List<LaporanTanah> laporanTanahLamaList;
                            laporanTanahLamaList = laporanTanahService.getSenaraiLaporanTanah(permohonan.getPermohonanSebelum().getIdPermohonan());

                            for (LaporanTanah laporanTanahLama : laporanTanahLamaList) {
                                if (laporanTanahLama.getHakmilikPermohonan().getHakmilik().getIdHakmilik().equals(hakPermohonan.getHakmilik().getIdHakmilik())) {
                                    laporanTanah.setRancanganKerajaan(laporanTanahLama.getRancanganKerajaan());
                                    laporanTanah.setUsahaTanam(laporanTanahLama.getUsahaTanam());
                                    laporanTanah.setJenisBangunan(laporanTanahLama.getJenisBangunan());
                                    laporanTanah.setKeadaanTanah(laporanTanahLama.getKeadaanTanah());
                                }
                            }
                        }

                        laporanTanah.setPermohonan(permohonan);
                        laporanTanah.setHakmilikPermohonan(hakPermohonan);
                        laporanTanah.setInfoAudit(infoAudit);
                        laporanTanahService.simpanLaporanTanah(laporanTanah);
                    }
                    laporanTanahList = laporanTanahService.getSenaraiLaporanTanah(idPermohonan);
                }
            }
        }
    }

    public Resolution simpanHakmilik() {
        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp = hakmilikPermohonanList.get(i);
            hakmilikService.saveHakmilik(hmp.getHakmilik());
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("consent/maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution simpanLaporanTanah() {
        for (int i = 0; i < laporanTanahList.size(); i++) {
            LaporanTanah laporanTanah = new LaporanTanah();
            laporanTanah = laporanTanahList.get(i);
            laporanTanahService.simpanLaporanTanah(laporanTanah);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("consent/maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<LaporanTanah> getLaporanTanahList() {
        return laporanTanahList;
    }

    public void setLaporanTanahList(List<LaporanTanah> laporanTanahList) {
        this.laporanTanahList = laporanTanahList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
        return senaraiHakmilikUrusan;
    }

    public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
        this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
    }

    public List<HakmilikUrusan> getSenaraiUrusanRizab() {
        return senaraiUrusanRizab;
    }

    public void setSenaraiUrusanRizab(List<HakmilikUrusan> senaraiUrusanRizab) {
        this.senaraiUrusanRizab = senaraiUrusanRizab;
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusanGD() {
        return senaraiHakmilikUrusanGD;
    }

    public void setSenaraiHakmilikUrusanGD(List<HakmilikUrusan> senaraiHakmilikUrusanGD) {
        this.senaraiHakmilikUrusanGD = senaraiHakmilikUrusanGD;
    }

    public List<PermohonanPihak> getSenaraiPemegangGadai() {
        return senaraiPemegangGadai;
    }

    public void setSenaraiPemegangGadai(List<PermohonanPihak> senaraiPemegangGadai) {
        this.senaraiPemegangGadai = senaraiPemegangGadai;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}

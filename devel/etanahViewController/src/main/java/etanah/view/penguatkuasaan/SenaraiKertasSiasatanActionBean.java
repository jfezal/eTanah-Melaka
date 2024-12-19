/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.PermohonanSemakDokumen;
//added
import etanah.model.KodUrusan;
import etanah.model.UrusanDokumen;
import etanah.service.EnforceService;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author Murali
 * * modify by sitifariza.hanim 28022011
 */
@UrlBinding("/penguatkuasaan/SemakKertasSiasatan")
public class SenaraiKertasSiasatanActionBean extends BasicTabActionBean {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;
    private List<PermohonanSemakDokumen> senaraiSemakDokumen;
    private List<KodUrusan> kodUrusan;
    private List<UrusanDokumen> senaraiUrusanDokumen;
    private String idSaksi;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (!senaraiUrusanDokumen.isEmpty() && !senaraiSemakDokumen.isEmpty()) {

            return new JSP("penguatkuasaan/senarai_semak_kertas_siasatan.jsp").addParameter("tab", "true");
        } else {
            return new JSP("penguatkuasaan/senarai_semak_kertas_siasataan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        if (!senaraiUrusanDokumen.isEmpty() && !senaraiSemakDokumen.isEmpty()) {
            return new JSP("penguatkuasaan/senarai_semak_kertas_siasatan.jsp").addParameter("tab", "true");
        } else {
            return new JSP("penguatkuasaan/senarai_semak_kertas_siasataan.jsp").addParameter("tab", "true");
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("-----rehydrate----:" + permohonan);

        if (permohonan.getKodUrusan() != null) {
            //get data from table urusan_dokumen based on kod urusan
            senaraiUrusanDokumen = enforceService.findUrusanDokumenBykodUrusan(permohonan.getKodUrusan().getKod());
            //get data from mohon_semak_dok based on id mohon
            senaraiSemakDokumen = enforceService.findSenaraiPermohonanSemakDokumen(idPermohonan);
        }
    }

    // senarai_semak_kertas_siasatan`s Simpan
    public Resolution simpan() throws ParseException {
        String catatan;
        String status;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        senaraiSemakDokumen = enforceService.findSenaraiPermohonanSemakDokumen(idPermohonan);
        if (senaraiSemakDokumen != null) {
            for (int i = 0; i < senaraiSemakDokumen.size(); i++) {
                PermohonanSemakDokumen semakDokumen = new PermohonanSemakDokumen();
                semakDokumen = senaraiSemakDokumen.get(i);
                info = semakDokumen.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                catatan = getContext().getRequest().getParameter("catatan" + i);
                status = getContext().getRequest().getParameter("status" + i);
                System.out.println("-----status-------:" + status);
                semakDokumen.setAdaDokumen(status);
                semakDokumen.setInfoAudit(info);
                semakDokumen.setCatatan(catatan);
                enforceService.simpanPermohonanSemakDokumen(semakDokumen);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/senarai_semak_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    // senarai_semak_kertas_siasataan`s Simpan
    public Resolution simpan1() throws ParseException {
        String catatan;
        String status;

//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        //senaraiSemakDokumen = pelupusanService.findSenaraiPermohonanSemakDokumen(idPermohonan);
        senaraiUrusanDokumen = enforceService.findUrusanDokumenBykodUrusan(permohonan.getKodUrusan().getKod());

        if (senaraiUrusanDokumen != null) {
            for (int i = 0; i < senaraiUrusanDokumen.size(); i++) {

                PermohonanSemakDokumen semakDokumen = new PermohonanSemakDokumen();
                UrusanDokumen urusanDokumen = senaraiUrusanDokumen.get(i);
                // info = urusanDokumen.getInfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                catatan = getContext().getRequest().getParameter("catatan" + i);
                status = getContext().getRequest().getParameter("status" + i);
                System.out.println("-----status-------:" + status);
                semakDokumen.setAdaDokumen(status);
                semakDokumen.setInfoAudit(info);
                semakDokumen.setCatatan(catatan);
                semakDokumen.setPermohonan(permohonan);
                semakDokumen.setCawangan(permohonan.getCawangan());
                semakDokumen.setKodDokumen(urusanDokumen.getKodDokumen());
                enforceService.simpanPermohonanSemakDokumen(semakDokumen);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new RedirectResolution(SenaraiKertasSiasatanActionBean.class, "locate");
//        return new JSP("penguatkuasaan/senarai_semak_kertas_siasatan.jsp").addParameter("tab", "true");
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanSemakDokumen> getSenaraiSemakDokumen() {
        return senaraiSemakDokumen;
    }

    public void setSenaraiSemakDokumen(List<PermohonanSemakDokumen> senaraiSemakDokumen) {
        this.senaraiSemakDokumen = senaraiSemakDokumen;
    }

    public List<KodUrusan> getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(List<KodUrusan> kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<UrusanDokumen> getSenaraiUrusanDokumen() {
        return senaraiUrusanDokumen;
    }

    public void setSenaraiUrusanDokumen(List<UrusanDokumen> senaraiUrusanDokumen) {
        this.senaraiUrusanDokumen = senaraiUrusanDokumen;
    }

    public String getIdSaksi() {
        return idSaksi;
    }

    public void setIdSaksi(String idSaksi) {
        this.idSaksi = idSaksi;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodSenarai;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanUrusan;
import etanah.model.SenaraiRujukan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_undang")
public class MaklumatUndangActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanUrusan permohonanUrusan;
    private KodSenarai kodSenarai;
    private SenaraiRujukan senaraiRujukan;
    private List<KodSenarai> kodSenaraiList;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_undang.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("consent/maklumat_undang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodSenaraiList = conService.getKodSenaraiByJabatanAndKod("22", "U");

            permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "UNDANG LUAK");

            if (permohonanUrusan == null) {
                permohonanUrusan = new PermohonanUrusan();
                permohonanUrusan.setPermohonan(permohonan);
                permohonanUrusan.setCawangan(permohonan.getCawangan());
                permohonanUrusan.setPerihal("UNDANG LUAK");
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanUrusan.setInfoAudit(infoAudit);
                conService.simpanPermohonanUrusan(permohonanUrusan);
            } else {
                if (permohonanUrusan.getCatatan() != null) {
                    senaraiRujukan = conService.findSenaraiRujukan(permohonanUrusan.getCatatan());
                }
            }
        }
    }

    public Resolution simpan() {

        if (senaraiRujukan == null) {
            addSimpleError("Sila masukkan maklumat dengan lengkap.");
        } else {

            if (senaraiRujukan.getKod() == null) {
                addSimpleError("Sila masukkan jenis suku.");
            } else if (senaraiRujukan.getPerihal() == null) {
                addSimpleError("Sila masukkan nama gelaran.");
            } else if (senaraiRujukan.getNama() == null) {
                addSimpleError("Sila masukkan nama.");
            } else {

                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                SenaraiRujukan senaraiRujukanTemp = new SenaraiRujukan();

                senaraiRujukanTemp = senaraiRujukanDAO.findById(senaraiRujukan.getKod());

                InfoAudit infoAudit = new InfoAudit();
                infoAudit = senaraiRujukanTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

                senaraiRujukanTemp.setKod(senaraiRujukan.getKod());
                senaraiRujukanTemp.setNama(senaraiRujukan.getNama());
                senaraiRujukanTemp.setPerihal(senaraiRujukan.getPerihal());

                KodNegeri kodNegeri = new KodNegeri();
                kodNegeri.setKod("05");

                Alamat alamat = new Alamat();
                alamat.setAlamat1("00000");
                alamat.setPoskod("00000");
                alamat.setNegeri(kodNegeri);
                senaraiRujukanTemp.setAlamat(alamat);
                senaraiRujukanTemp.setInfoAudit(infoAudit);

                conService.simpanSenaraiRujukan(senaraiRujukanTemp);
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_undang.jsp").addParameter("tab", "true");
    }

    public Resolution getUndang() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodSuku = getContext().getRequest().getParameter("kodSuku");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "UNDANG LUAK");

            if (permohonanUrusan != null) {
                InfoAudit infoAudit = new InfoAudit();
                infoAudit = permohonan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanUrusan.setInfoAudit(infoAudit);
                permohonanUrusan.setCatatan(kodSuku);
                conService.simpanPermohonanUrusan(permohonanUrusan);
            }
        }

        senaraiRujukan = conService.findSenaraiRujukan(kodSuku);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_undang.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KodSenarai> getKodSenaraiList() {
        return kodSenaraiList;
    }

    public void setKodSenaraiList(List<KodSenarai> kodSenaraiList) {
        this.kodSenaraiList = kodSenaraiList;
    }

    public KodSenarai getKodSenarai() {
        return kodSenarai;
    }

    public void setKodSenarai(KodSenarai kodSenarai) {
        this.kodSenarai = kodSenarai;
    }

    public SenaraiRujukan getSenaraiRujukan() {
        return senaraiRujukan;
    }

    public void setSenaraiRujukan(SenaraiRujukan senaraiRujukan) {
        this.senaraiRujukan = senaraiRujukan;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }
}

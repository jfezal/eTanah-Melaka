/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Alamat;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.KodSenarai;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
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
@UrlBinding("/consent/maklumat_datok_lembaga")
public class MaklumatDatokLembagaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanUrusan permohonanUrusanLuak;
    private PermohonanUrusan permohonanUrusanLembaga;
    private PermohonanPihak permohonanPihak;
    private KodSenarai kodSenarai;
    private SenaraiRujukan senaraiRujukan;
    private List<KodSenarai> kodSenaraiList;
    private String negeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/maklumat_datok_lembaga.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("consent/maklumat_datok_lembaga.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodSenaraiList = conService.getKodSenaraiByJabatanAndKod("22", "S");
            permohonanUrusanLuak = conService.findMohonUrusanByPerihal(idPermohonan, "LUAK");
            permohonanUrusanLembaga = conService.findMohonUrusanByPerihal(idPermohonan, "LEMBAGA");

            if (permohonanUrusanLuak == null) {
                permohonanUrusanLuak = new PermohonanUrusan();
                permohonanUrusanLuak.setPermohonan(permohonan);
                permohonanUrusanLuak.setCawangan(permohonan.getCawangan());
                permohonanUrusanLuak.setPerihal("LUAK");
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanUrusanLuak.setInfoAudit(infoAudit);
                conService.simpanPermohonanUrusan(permohonanUrusanLuak);
            } else {
                if (permohonanUrusanLuak.getCatatan() != null) {
                    senaraiRujukan = conService.findSenaraiRujukan(permohonanUrusanLuak.getCatatan());
                }
            }

            if (permohonanUrusanLembaga == null) {
                permohonanUrusanLembaga = new PermohonanUrusan();
                permohonanUrusanLembaga.setPermohonan(permohonan);
                permohonanUrusanLembaga.setCawangan(permohonan.getCawangan());
                permohonanUrusanLembaga.setPerihal("LEMBAGA");
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanUrusanLembaga.setInfoAudit(infoAudit);
                conService.simpanPermohonanUrusan(permohonanUrusanLembaga);
            } else {
                if (permohonanUrusanLembaga.getCatatan() != null) {
                    senaraiRujukan = conService.findSenaraiRujukan(permohonanUrusanLembaga.getCatatan());
                    if (senaraiRujukan.getAlamat().getNegeri() != null) {
                        negeri = senaraiRujukan.getAlamat().getNegeri().getKod();
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        if (senaraiRujukan == null || permohonanUrusanLuak.getCatatan() == null) {
            addSimpleError("Sila masukkan maklumat dengan lengkap.");
        } else {

            if (senaraiRujukan.getKod() == null) {
                addSimpleError("Sila masukkan jenis suku.");
            } else if (senaraiRujukan.getPerihal() == null) {
                addSimpleError("Sila masukkan nama gelaran.");
            } else if (senaraiRujukan.getNama() == null) {
                addSimpleError("Sila masukkan nama.");
            } else if (senaraiRujukan.getAlamat().getAlamat1() == null) {
                addSimpleError("Sila masukkan alamat.");
            } else if (senaraiRujukan.getAlamat().getPoskod() == null) {
                addSimpleError("Sila masukkan poskod.");
            } else if (negeri == null) {
                addSimpleError("Sila masukkan negeri.");
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
                senaraiRujukanTemp.setNoTel1(senaraiRujukan.getNoTel1());

                KodNegeri kodNegeri = new KodNegeri();
                kodNegeri.setKod(negeri);

                Alamat alamat = new Alamat();
                alamat.setAlamat1(senaraiRujukan.getAlamat().getAlamat1());
                alamat.setAlamat2(senaraiRujukan.getAlamat().getAlamat2());
                alamat.setAlamat3(senaraiRujukan.getAlamat().getAlamat3());
                alamat.setAlamat4(senaraiRujukan.getAlamat().getAlamat4());
                alamat.setPoskod(senaraiRujukan.getAlamat().getPoskod());
                alamat.setNegeri(kodNegeri);
                senaraiRujukanTemp.setAlamat(alamat);
                senaraiRujukanTemp.setInfoAudit(infoAudit);

                conService.simpanSenaraiRujukan(senaraiRujukanTemp);

                if (permohonanUrusanLuak.getCatatan() != null) {
                    conService.simpanPermohonanUrusan(permohonanUrusanLuak);
                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_datok_lembaga.jsp").addParameter("tab", "true");
    }

    public Resolution getDatokLembaga() {

        String kodSuku = getContext().getRequest().getParameter("kodSuku");
        String luak = getContext().getRequest().getParameter("luak");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (kodSuku != null && permohonanUrusanLembaga != null) {
            permohonanUrusanLembaga.setCatatan(kodSuku);
            conService.simpanPermohonanUrusan(permohonanUrusanLembaga);
        }

        if (luak != null && permohonanUrusanLuak != null) {
            permohonanUrusanLuak.setCatatan(luak);
            conService.simpanPermohonanUrusan(permohonanUrusanLuak);

        }
        permohonanUrusanLuak = conService.findMohonUrusanByPerihal(idPermohonan, "LUAK");
        senaraiRujukan = conService.findSenaraiRujukan(kodSuku);
        if (senaraiRujukan.getAlamat().getNegeri() != null) {
            negeri = senaraiRujukan.getAlamat().getNegeri().getKod();
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_datok_lembaga.jsp").addParameter("tab", "true");
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

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public PermohonanUrusan getPermohonanUrusanLuak() {
        return permohonanUrusanLuak;
    }

    public void setPermohonanUrusanLuak(PermohonanUrusan permohonanUrusanLuak) {
        this.permohonanUrusanLuak = permohonanUrusanLuak;
    }

    public PermohonanUrusan getPermohonanUrusanLembaga() {
        return permohonanUrusanLembaga;
    }

    public void setPermohonanUrusanLembaga(PermohonanUrusan permohonanUrusanLembaga) {
        this.permohonanUrusanLembaga = permohonanUrusanLembaga;
    }
}

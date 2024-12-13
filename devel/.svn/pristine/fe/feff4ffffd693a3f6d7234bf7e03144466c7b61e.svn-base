/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Alamat;
import etanah.model.Enkuiri;
import etanah.model.InfoAudit;
import etanah.model.Kehadiran;
import etanah.model.KodNegeri;
import etanah.model.KodSenarai;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.SenaraiRujukan;
import etanah.service.ConsentPtdService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@UrlBinding("/lelong/datuk_lembaga")
public class DatukLembagaActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(DatukLembagaActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    LelongService lelongService;
    private Permohonan permohonan;
    private Kehadiran kehadiran;
    private Enkuiri enkuiri;
    private PermohonanPihak permohonanPihak;
    private KodSenarai kodSenarai;
    private SenaraiRujukan senaraiRujukan;
    private List<KodSenarai> kodSenaraiList;
    private String negeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("lelong/Datuk_lembaga.jsp").addParameter("tab", "true");
    }

    public Resolution showFormView() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("lelong/Datuk_lembaga_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);

            kodSenaraiList = conService.getKodSenaraiByJabatanAndKod("22", "S");
            if (permohonan.getRujukanUndang2() != null) {
                senaraiRujukan = conService.findSenaraiRujukan(permohonan.getRujukanUndang2());
                if (senaraiRujukan.getAlamat().getNegeri() != null) {
                    negeri = senaraiRujukan.getAlamat().getNegeri().getKod();
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
                LOG.info("---------senarai rujukan------");


                InfoAudit ia = new InfoAudit();
                Kehadiran hadir = new Kehadiran();
                hadir.setInfoAudit(ia);
                hadir.setEnkuiri(enkuiri);
                hadir.setKodSenarai(senaraiRujukanTemp.getSenarai());
                hadir.setSenaraiRujukan(senaraiRujukanTemp);

                conService.simpanSenaraiRujukan(senaraiRujukanTemp);
                lelongService.saveKehadiran(hadir, pengguna);

                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/lelong/Datuk_lembaga.jsp").addParameter("tab", "true");
    }

    public Resolution getDatokLembaga() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodSuku = getContext().getRequest().getParameter("kodSuku");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(infoAudit);
            permohonan.setRujukanUndang2(kodSuku);
            conService.simpanPermohonan(permohonan);
        }

        senaraiRujukan = conService.findSenaraiRujukan(kodSuku);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/lelong/Datuk_lembaga.jsp").addParameter("tab", "true");
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

    public Kehadiran getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(Kehadiran kehadiran) {
        this.kehadiran = kehadiran;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }
}

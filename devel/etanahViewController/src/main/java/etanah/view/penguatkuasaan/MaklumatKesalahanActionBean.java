package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
//import etanah.model.KodPeranan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.common.EnforcementService;
import org.hibernate.Session;

//import net.sourceforge.stripes.action.StreamingResolution;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_kesalahan")
public class MaklumatKesalahanActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(MaklumatKesalahanActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private Hakmilik hakmilik;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kandungan;
    private String tempatSidang;
    private String nomborRujukan;
    private String tajuk;
    private String idHakmilik;
    private String tarikhButiran;
    private String jam;
    private String minit;
    private String ampm;
    private String kodDokumen1;
    private String kod;
    private Pengguna pengguna;
    private List<Pengguna> senaraiPengguna;
    private String recordCount;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private int rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasan;
    private String kodNegeri;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kesalahan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_kesalahan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {

                kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "NKSH");

                if (kertas != null) {
                    tempatSidang = kertas.getTempatSidang();
                    tajuk = kertas.getTajuk();




                }


            } catch (Exception ex) {

                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        if (kertas == null) {
            kertas = new PermohonanKertas();
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTempatSidang(tempatSidang);
        kertas.setTajuk(tajuk);
        KodCawangan caw = peng.getKodCawangan();
        kertas.setCawangan(caw);
        kertas.setKodDokumen(kodDokumenDAO.findById("NKSH"));
        enforceService.simpanPermohonanKertas(kertas);






        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_kesalahan.jsp").addParameter("tab", "true");
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getKodDokumen1() {
        return kodDokumen1;
    }

    public void setKodDokumen1(String kodDokumen1) {
        this.kodDokumen1 = kodDokumen1;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public String getTempatSidang() {
        return tempatSidang;
    }

    public void setTempatSidang(String tempatSidang) {
        this.tempatSidang = tempatSidang;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    public String getTarikhButiran() {
        return tarikhButiran;
    }

    public void setTarikhButiran(String tarikhButiran) {
        this.tarikhButiran = tarikhButiran;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }
}
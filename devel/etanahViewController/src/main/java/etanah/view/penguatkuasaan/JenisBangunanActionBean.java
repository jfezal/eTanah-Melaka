/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodUOMDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 * @modified by ctzainal
 */
@UrlBinding("/penguatkuasaan/jenis_bangunan")
public class JenisBangunanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(JenisBangunanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    private List<PermohonanLaporanBangunan> senaraiPermohonanLaporanBangunan;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private String idLaporBangunan;
    private String idLapor;
    private String jenisBangunan;
    private BigDecimal ukuran;
    private String uomUkuran;
    private String kod;
    private BigDecimal nilai;
    private String namaPemunya;
    private String namaPenyewa;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodUOM kodUOM;
    private LaporanTanah laporanTanah;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private String ukuranTemp; //save at nama penyewa for temp

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/penguatkuasaan/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    public Resolution bangunanPopup() {
        idLapor = getContext().getRequest().getParameter("idLapor");
        return new JSP("penguatkuasaan/popup_maklumat_bangunan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!bangunanPopup", "!simpan"})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            logger.debug("idPermohonan :" + idPermohonan);
            if (StringUtils.isNotBlank(idLaporBangunan)) {
                senaraiPermohonanLaporanBangunan = enforceService.findByIDLaporBangunan(idPermohonan);
            }

        }
    }

    public Resolution refreshpage() {

        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (idLapor != null) {
            idLapor = getContext().getRequest().getParameter("idLapor");
            laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLapor));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (permohonanLaporanBangunan == null) {
            permohonanLaporanBangunan = new PermohonanLaporanBangunan();

        }
        permohonanLaporanBangunan = new PermohonanLaporanBangunan();
        permohonanLaporanBangunan.setPermohonan(permohonan);
        permohonanLaporanBangunan.setCawangan(pguna.getKodCawangan());
        permohonanLaporanBangunan.setInfoAudit(ia);
        permohonanLaporanBangunan.setJenisBangunan(jenisBangunan);
        permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
        permohonanLaporanBangunan.setUkuran(ukuran);
        permohonanLaporanBangunan.setKeteranganTahunBinaan(ukuranTemp);
        if (uomUkuran != null) {
            KodUOM kuom = kodUOMDAO.findById(uomUkuran);
            permohonanLaporanBangunan.setUomUkuran(kuom);
        } else {
            permohonanLaporanBangunan.setUomUkuran(null);
        }

        permohonanLaporanBangunan.setNilai(nilai);
        permohonanLaporanBangunan.setNamaPemunya(namaPemunya);
        permohonanLaporanBangunan.setNamaPenyewa(namaPenyewa);

        enforceService.simpanBangunan(permohonanLaporanBangunan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution edit() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idLaporBangunan));
        InfoAudit ia = new InfoAudit();

        permohonanLaporanBangunan.setJenisBangunan(jenisBangunan);
        permohonanLaporanBangunan.setUkuran(ukuran);
        permohonanLaporanBangunan.setKeteranganTahunBinaan(ukuranTemp);
        if (uomUkuran != null) {
            KodUOM kuom = kodUOMDAO.findById(uomUkuran);
            permohonanLaporanBangunan.setUomUkuran(kuom);
        } else {
            permohonanLaporanBangunan.setUomUkuran(null);
        }

        permohonanLaporanBangunan.setNilai(nilai);
        permohonanLaporanBangunan.setNamaPemunya(namaPemunya);
        permohonanLaporanBangunan.setNamaPenyewa(namaPenyewa);

        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());
        permohonanLaporanBangunan.setInfoAudit(ia);
        permohonanLaporanBangunan.setCawangan(pguna.getKodCawangan());
        enforceService.updateBangunan(permohonanLaporanBangunan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution deleteBangunan() {

        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idLaporBangunan));


        if (permohonanLaporanBangunan != null) {

            enforceService.deleteAllBangunan(permohonanLaporanBangunan);
        }
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");

    }

    public Resolution editBangunan() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idLaporBangunan));


        jenisBangunan = permohonanLaporanBangunan.getJenisBangunan();
        ukuran = permohonanLaporanBangunan.getUkuran();
        ukuranTemp = permohonanLaporanBangunan.getKeteranganTahunBinaan();
        if (permohonanLaporanBangunan.getUomUkuran() != null) {
            uomUkuran = permohonanLaporanBangunan.getUomUkuran().getKod();
        }
        nilai = permohonanLaporanBangunan.getNilai();
        namaPemunya = permohonanLaporanBangunan.getNamaPemunya();
        namaPenyewa = permohonanLaporanBangunan.getNamaPenyewa();

        return new JSP("penguatkuasaan/popup_edit_bangunan.jsp").addParameter("popup", "true");
    }

    public Resolution jenisBangunanDetails() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idLaporBangunan));

        jenisBangunan = permohonanLaporanBangunan.getJenisBangunan();
        ukuran = permohonanLaporanBangunan.getUkuran();
        ukuranTemp = permohonanLaporanBangunan.getKeteranganTahunBinaan();
        if (permohonanLaporanBangunan.getUomUkuran() != null) {
            uomUkuran = permohonanLaporanBangunan.getUomUkuran().getKod();
        }
        nilai = permohonanLaporanBangunan.getNilai();
        namaPemunya = permohonanLaporanBangunan.getNamaPemunya();
        namaPenyewa = permohonanLaporanBangunan.getNamaPenyewa();


        return new JSP("penguatkuasaan/popup_jenis_bangunan_details.jsp").addParameter("popup", "true");
    }

    public Resolution viewSaksi() {
        idLaporBangunan = getContext().getRequest().getParameter("idLaporBangunan");
        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idLaporBangunan));
        return new JSP("penguatkuasaan/popup_view_bangunan.jsp").addParameter("popup", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    //    bangunan
    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public List<PermohonanLaporanBangunan> getSenaraiPermohonanLaporanBangunan() {
        return senaraiPermohonanLaporanBangunan;
    }

    public void setSenaraiPermohonanLaporanBangunan(List<PermohonanLaporanBangunan> senaraiPermohonanLaporanBangunan) {
        this.senaraiPermohonanLaporanBangunan = senaraiPermohonanLaporanBangunan;
    }

    public String getIdLaporBangunan() {
        return idLaporBangunan;
    }

    public void setIdLaporBangunan(String idLaporBangunan) {
        this.idLaporBangunan = idLaporBangunan;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public BigDecimal getUkuran() {
        return ukuran;
    }

    public void setUkuran(BigDecimal ukuran) {
        this.ukuran = ukuran;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getNamaPemunya() {
        return namaPemunya;
    }

    public void setNamaPemunya(String namaPemunya) {
        this.namaPemunya = namaPemunya;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getIdLapor() {
        return idLapor;
    }

    public void setIdLapor(String idLapor) {
        this.idLapor = idLapor;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getUomUkuran() {
        return uomUkuran;
    }

    public void setUomUkuran(String uomUkuran) {
        this.uomUkuran = uomUkuran;
    }

    public String getUkuranTemp() {
        return ukuranTemp;
    }

    public void setUkuranTemp(String ukuranTemp) {
        this.ukuranTemp = ukuranTemp;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.service.pengambilan.aduan.AduanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/ringkasan_aduan")
public class RingkasanAduanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RingkasanAduanActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    AduanService aduanService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;

    String report_p_id_mohon;
    private Permohonan permohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    String idPermohonan;
    private String penyerahNoPengenalan;
    private String pNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNegeri;
    private String penyerahNegeri1;
    private String penyerahNoTelefon;
    private String penyerahEmail;

    String kod;

    // mohon_aduan
    private PermohonanAduan pemohonAduan;
    private String aduan;
    String bandarPekanMukim;
    String cawangan;

    @DefaultHandler
    public Resolution showForm() {

//        System.out.println("inside show form RingkasanAduanActionBean ");
        //"0401ACQ2020000411"; // 
        String idPermohonan = ""; //0401ACQ2020000411";
        //String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
            if (permohonan != null) {
                LOG.info(":: permohonan x null ::");

                KodJenisPengenalan kjp = permohonan.getPenyerahJenisPengenalan();

                if (permohonan.getPenyerahJenisPengenalan() != null) {
                    setKod(kjp.getKod());
                } else {
                    setKod("B");
                }

                penyerahNoPengenalan = permohonan.getPenyerahNoPengenalan();
                pNoPengenalan = permohonan.getPenyerahNoPengenalan();
                LOG.info("pNoPengenalan  : " + pNoPengenalan);
                penyerahNama = permohonan.getPenyerahNama();
                penyerahAlamat1 = permohonan.getPenyerahAlamat1();
                penyerahAlamat2 = permohonan.getPenyerahAlamat2();
                penyerahAlamat3 = permohonan.getPenyerahAlamat3();
                penyerahAlamat4 = permohonan.getPenyerahAlamat4();
                penyerahPoskod = permohonan.getPenyerahPoskod();
                penyerahNegeri = permohonan.getPenyerahNegeri().getKod();
                penyerahNegeri1 = permohonan.getPenyerahNegeri().getKod();
                penyerahNoTelefon = permohonan.getPenyerahNoTelefon1();
                penyerahEmail = permohonan.getPenyerahEmail();
            }

        }

        if (isDebug) {
            LOG.debug("show form finish");
        }
        System.out.println("idPermohonan x null");

        hakmilikPermohonanList = hakmilikpermohonanservice.findByIdPermohonan(idPermohonan);
        System.out.println("b4 sett idPermohonan not empty ++> " + idPermohonan);
        setIdPermohonan(idPermohonan);

        // mohon_aduan
        pemohonAduan = aduanService.findAduanByIdMohon(idPermohonan);

//        setBandarPekanMukim(pemohonAduan.getBandarPekanMukim().getNama());
        setCawangan(pemohonAduan.getCawangan().getName());

        setAduan(pemohonAduan.getPerihal());
        return new JSP("pengambilan/aduan_kerosakan/ringkasan_aduan.jsp").addParameter("tab", "true");

    }

    public Resolution maklumatPengadu() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/.jsp").addParameter("tab", "true");

    }

    public Resolution agihTugas() {

        //return new JSP("pengambilan/aduan_kerosakan/kemasukan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/aduan_kerosakan/.jsp").addParameter("tab", "true");

    }

    public Resolution view() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Permohonan mohon = aduanService.findfromdb(idPermohonan);
        report_p_id_mohon = mohon.getIdPermohonan();
        return new JSP("pengambilan/aduan_kerosakan/.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public String getReport_p_id_mohon() {
        return report_p_id_mohon;
    }

    public void setReport_p_id_mohon(String report_p_id_mohon) {
        this.report_p_id_mohon = report_p_id_mohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getpNoPengenalan() {
        return pNoPengenalan;
    }

    public void setpNoPengenalan(String pNoPengenalan) {
        this.pNoPengenalan = pNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNegeri1() {
        return penyerahNegeri1;
    }

    public void setPenyerahNegeri1(String penyerahNegeri1) {
        this.penyerahNegeri1 = penyerahNegeri1;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanAduan getPemohonAduan() {
        return pemohonAduan;
    }

    public void setPemohonAduan(PermohonanAduan pemohonAduan) {
        this.pemohonAduan = pemohonAduan;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

}// end class

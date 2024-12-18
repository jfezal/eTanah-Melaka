/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.service.common.CawanganService;
import etanah.service.common.EnforcementService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Mohammad Hafifi
 */
@UrlBinding("/penguatkuasaan/maklumat_pihak")
public class MaklumatPihakActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatOrangDisyakiActionBean.class);
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforcementService enforcementService;
    @Inject
    etanah.Configuration conf;
    @Inject
    ListUtil listUtil;
    private Permohonan permohonan;
    private String kodCawangan;
    private String idPermohonan;
    private Pemohon pemohon;
    private String namaPemohon;
    private String alamatTetap1;
    private String alamatTetap2;
    private String alamatTetap3;
    private String alamatTetapPoskod;
    private String alamatTetapNegeri;
    private String alamatSurat1;
    private String alamatSurat2;
    private String alamatSurat3;
    private String alamatSuratPoskod;
    private String alamatSuratNegeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pihak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pihak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            pemohon = enforcementService.findPemohonByIDMohon(idPermohonan);

            System.out.println("pemohon initial ----------- : " + pemohon);

            if (pemohon != null) {
                kodCawangan = pemohon.getCawangan().getKod();
                namaPemohon = pemohon.getNama();
                alamatTetap1 = pemohon.getAlamat().getAlamat1();
                alamatTetap2 = pemohon.getAlamat().getAlamat2();
                alamatTetap3 = pemohon.getAlamat().getAlamat3();
                alamatTetapPoskod = pemohon.getAlamat().getPoskod();
                alamatTetapNegeri = pemohon.getAlamat().getNegeri().getKod();

                if (pemohon.getAlamatSurat() != null) {
                    alamatSurat1 = pemohon.getAlamatSurat().getAlamatSurat1();
                    alamatSurat2 = pemohon.getAlamatSurat().getAlamatSurat2();
                    alamatSurat3 = pemohon.getAlamatSurat().getAlamatSurat3();
                    alamatSuratPoskod = pemohon.getAlamatSurat().getPoskodSurat();
                    alamatSuratNegeri = pemohon.getAlamatSurat().getNegeriSurat().getKod();
                }
            } else {
                permohonan = permohonanDAO.findById(idPermohonan);
                kodCawangan = permohonan.getCawangan().getKod();
                System.out.println("idPermohonan ----------- : " + idPermohonan);
                System.out.println("kodCawangan ----------- : " + kodCawangan);
            }
        }
    }

    public Resolution simpan() throws ParseException {
        logger.info("-------------simpan-----------------");

        try {
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();

            if (pemohon == null) {
                pemohon = new Pemohon();
                pemohon.setCawangan(new KodCawangan());
                pemohon.setPermohonan(new Permohonan());
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = pemohon.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }

            pemohon.getCawangan().setKod(kodCawangan);
            pemohon.getPermohonan().setIdPermohonan(idPermohonan);
            pemohon.setNama(namaPemohon);
            pemohon.setInfoAudit(ia);

            Alamat alamatTetap = new Alamat();
            alamatTetap.setNegeri(new KodNegeri());
            alamatTetap.setAlamat1(alamatTetap1);
            alamatTetap.setAlamat2(alamatTetap2);
            alamatTetap.setAlamat3(alamatTetap3);
            alamatTetap.setPoskod(alamatTetapPoskod);
            alamatTetap.getNegeri().setKod(alamatTetapNegeri);
            pemohon.setAlamat(alamatTetap);

            AlamatSurat alamatSurat = new AlamatSurat();
            alamatSurat.setNegeriSurat(new KodNegeri());
            alamatSurat.setAlamatSurat1(alamatSurat1);
            alamatSurat.setAlamatSurat2(alamatSurat2);
            alamatSurat.setAlamatSurat3(alamatSurat3);
            alamatSurat.setPoskodSurat(alamatSuratPoskod);
            if (alamatSuratNegeri.equals("Pilih ...")) {
                alamatTetapNegeri = "";
            }
            alamatSurat.getNegeriSurat().setKod(alamatTetapNegeri);

            pemohon.setAlamatSurat(alamatSurat);

            System.out.println("pemohon save ----------- : " + pemohon);

            enforcementService.simpanMaklumatPihak(pemohon);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_pihak.jsp").addParameter("tab", "true");
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getAlamatTetap1() {
        return alamatTetap1;
    }

    public void setAlamatTetap1(String alamatTetap1) {
        this.alamatTetap1 = alamatTetap1;
    }

    public String getAlamatTetap2() {
        return alamatTetap2;
    }

    public void setAlamatTetap2(String alamatTetap2) {
        this.alamatTetap2 = alamatTetap2;
    }

    public String getAlamatTetap3() {
        return alamatTetap3;
    }

    public void setAlamatTetap3(String alamatTetap3) {
        this.alamatTetap3 = alamatTetap3;
    }

    public String getAlamatTetapPoskod() {
        return alamatTetapPoskod;
    }

    public void setAlamatTetapPoskod(String alamatTetapPoskod) {
        this.alamatTetapPoskod = alamatTetapPoskod;
    }

    public String getAlamatTetapNegeri() {
        return alamatTetapNegeri;
    }

    public void setAlamatTetapNegeri(String alamatTetapNegeri) {
        this.alamatTetapNegeri = alamatTetapNegeri;
    }

    public String getAlamatSurat1() {
        return alamatSurat1;
    }

    public void setAlamatSurat1(String alamatSurat1) {
        this.alamatSurat1 = alamatSurat1;
    }

    public String getAlamatSurat2() {
        return alamatSurat2;
    }

    public void setAlamatSurat2(String alamatSurat2) {
        this.alamatSurat2 = alamatSurat2;
    }

    public String getAlamatSurat3() {
        return alamatSurat3;
    }

    public void setAlamatSurat3(String alamatSurat3) {
        this.alamatSurat3 = alamatSurat3;
    }

    public String getAlamatSuratPoskod() {
        return alamatSuratPoskod;
    }

    public void setAlamatSuratPoskod(String alamatSuratPoskod) {
        this.alamatSuratPoskod = alamatSuratPoskod;
    }

    public String getAlamatSuratNegeri() {
        return alamatSuratNegeri;
    }

    public void setAlamatSuratNegeri(String alamatSuratNegeri) {
        this.alamatSuratNegeri = alamatSuratNegeri;
    }
}

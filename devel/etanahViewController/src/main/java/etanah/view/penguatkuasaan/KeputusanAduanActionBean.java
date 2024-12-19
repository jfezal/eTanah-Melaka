/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.KodPeranan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanTandatanganDokumen;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.KodCawangan;
import etanah.model.Hakmilik;
import etanah.model.Permohonan;
import etanah.model.PermohonanUkur;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

/**
 *
 * @author sitizainal
 *
 */
@UrlBinding("/penguatkuasaan/keputusanAduan")
public class KeputusanAduanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KeputusanAduanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    EnforceService enfService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    private PermohonanUkur permohonanUkur;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String tarikhPerakuan;
    private String ulasan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String makluman;
    private List<Pengguna> senaraiPengguna;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private String rowCount;
    private Pengguna pengguna;
    private String idPermohonan;
    private PermohonanKertasKandungan maklumanUlasan;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private Date currentDate;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_aduan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-------------rehydrate------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            senaraiPengguna = enfService.getSenaraiPguna();
        } else {
            ArrayList kumpulanBpel = new ArrayList<String>();
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpel.add("pptd"); //PPTD
                kumpulanBpel.add("ptd"); //PTD
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                kumpulanBpel.add("pptd"); //PPTD
                kumpulanBpel.add("ptd"); //PTD
            }

            senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
        }

        currentDate = new java.util.Date();

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();

        kertas = enfService.findByIdKrts(idPermohonan, "KPA"); //KPA = keputusan aduan

        if (kertas != null) {
            maklumanUlasan = enfService.findByIdBil(kertas.getIdKertas(), 2);
            if (maklumanUlasan != null) {
                makluman = maklumanUlasan.getKandungan();
            }
            senaraiKertas = enfService.findAllKeputusanAduan(kertas.getIdKertas());
            logger.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());
        } else {
            makluman = "Adalah dimaklumkan bahawa siasatan berkaitan aduan tuan iaitu";
        }

        mohonTandatanganDokumen = enfService.findPermohonanTandatanganDok(idPermohonan, "KPA");

        if (mohonTandatanganDokumen != null) {
            diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
        }

        rowCount = String.valueOf(senaraiKertas.size());
        System.out.println("rowCount rehydrate : " + rowCount);

    }

    public Resolution simpanUlasan() {
        logger.info("--------------simpanUlasan--------------");

        InfoAudit ia = new InfoAudit();

        if (kertas == null) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk("MAKLUMBALAS ADUAN AWAM");
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("KPA"));
        enfService.simpanPermohonanKertas(kertas);

        //to save makluman (bil 2)
        if (maklumanUlasan != null) {
            ia = maklumanUlasan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            maklumanUlasan = new PermohonanKertasKandungan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        maklumanUlasan.setKertas(kertas);
        maklumanUlasan.setBil(2);
        maklumanUlasan.setKandungan(makluman);
        maklumanUlasan.setCawangan(permohonan.getCawangan());
        maklumanUlasan.setInfoAudit(ia);
        enfService.simpanPermohonanKertasKandungan(maklumanUlasan);

        //to save list makluman

        System.out.println("rowCount save: " + rowCount);
        int k = 3;
        if (StringUtils.isNotBlank(rowCount)) {
            for (int i = 1; i <= Integer.parseInt(rowCount); i++) {
                if (senaraiKertas.size() != 0 && i <= senaraiKertas.size()) {
                    Long id = senaraiKertas.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan.setKertas(kertas);
                permohonanKertasKandungan.setBil(k);
                String kandungan = getContext().getRequest().getParameter("kandungan" + i);
                permohonanKertasKandungan.setKandungan(kandungan);
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setInfoAudit(ia);
                enfService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                k++;
            }
        }

        if (mohonTandatanganDokumen == null) {
            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = mohonTandatanganDokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        mohonTandatanganDokumen.setPermohonan(permohonan);
        mohonTandatanganDokumen.setInfoAudit(ia);
        mohonTandatanganDokumen.setCawangan(pengguna.getKodCawangan());
        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("KPA"));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enfService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteUlasan() {
        logger.info("--------------deleteUlasan--------------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        logger.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            }
            enfService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod telah berjaya dihapuskan");
        return new RedirectResolution(KeputusanAduanActionBean.class, "locate");
    }

    public Resolution simpan() {
        logger.info("-------------simpan------------------");
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit infoAudit = new InfoAudit();

            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodPeranan = pguna.getPerananUtama().getKod();


            permohonanLaporanUlasan = enfService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "kpsn_aduan");

            if (permohonanLaporanUlasan != null) {
                logger.info("update tarikh perakuan in mohon ukur start");
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            KodDokumen kodDokumen = kodDokumenDAO.findById("KPA");
            permohonanLaporanUlasan.setKodDokumen(kodDokumen);
            permohonanLaporanUlasan.setJenisUlasan("kpsn_aduan");
            permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);


            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("penguatkuasaan/keputusan_aduan.jsp").addParameter("tab", "true");
        }
        logger.info("-------------simpan finish------------------");
        tx.commit();
        return new JSP("penguatkuasaan/keputusan_aduan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTarikhPerakuan() {
        return tarikhPerakuan;
    }

    public void setTarikhPerakuan(String tarikhPerakuan) {
        this.tarikhPerakuan = tarikhPerakuan;
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

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getMakluman() {
        return makluman;
    }

    public void setMakluman(String makluman) {
        this.makluman = makluman;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public PermohonanKertasKandungan getMaklumanUlasan() {
        return maklumanUlasan;
    }

    public void setMaklumanUlasan(PermohonanKertasKandungan maklumanUlasan) {
        this.maklumanUlasan = maklumanUlasan;
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}

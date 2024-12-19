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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_eksibit")
public class MaklumatEksibitActionBean extends AbleActionBean {

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
    private static final Logger LOG = Logger.getLogger(MaklumatEksibitActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private Hakmilik hakmilik;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kandungan;
    private String tajuk;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String recordCount;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private String rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Pengguna pengguna;
    private List<Pengguna> senaraiPengguna;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private PermohonanKertasKandungan maklumanUlasan;
    private String makluman;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_eksibit.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_eksibit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("-------------rehydrate------------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);


        ArrayList kumpulanBpel = new ArrayList<String>();
        //'PPUU','PUU','PTD'
        if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            kumpulanBpel.add("puuptg"); // PUU
            kumpulanBpel.add("ppuuptg"); // PPUU
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
            kumpulanBpel.add("ppuuptd"); // PPUU
            kumpulanBpel.add("ptd"); // PTD
        }

        senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
//        senaraiPengguna = enforceService.getSenaraiPguna();

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "EBT");

        if (kertas != null) {
            maklumanUlasan = enforceService.findByIdBil(kertas.getIdKertas(), 3);
            System.out.println("idKertas" + kertas.getIdKertas());
            if (maklumanUlasan != null) {
                System.out.println("laalala" + maklumanUlasan.getKandungan());
                makluman = maklumanUlasan.getKandungan();
                System.out.println("makluman" + makluman);
            }
            if (senaraiKertas != null) {
                senaraiKertas = enforceService.findAllKeputusanAduan3(kertas.getIdKertas());
                LOG.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());
            }
        }
//        else {
//            makluman = "Laporan Polis";
//        }

        mohonTandatanganDokumen = enforceService.findPermohonanTandatanganDok(idPermohonan, "EBT");

        if (mohonTandatanganDokumen != null) {
            diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
        }

        rowCount = String.valueOf(senaraiKertas.size());
        System.out.println("rowCount rehydrate : " + rowCount);

    }

    public Resolution simpanUlasan() {
        LOG.info("--------------simpanUlasan--------------");

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
        kertas.setTajuk("Eksibit");
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("EBT"));

        enforceService.simpanPermohonanKertas(kertas);

        //to save makluman (bil 3)
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
        maklumanUlasan.setBil(3);
        maklumanUlasan.setKandungan(makluman);
        maklumanUlasan.setCawangan(permohonan.getCawangan());
        maklumanUlasan.setInfoAudit(ia);
        enforceService.simpanPermohonanKertasKandungan(maklumanUlasan);

        //to save list makluman

        System.out.println("rowCount save: " + rowCount);
        int k = 4;
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
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
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
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("EBT"));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_eksibit.jsp").addParameter("tab", "true");
    }

    public Resolution deleteUlasan() {
        LOG.info("--------------deleteUlasan--------------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod telah berjaya dihapuskan");
        return new RedirectResolution(MaklumatEksibitActionBean.class, "locate");
    }
//
//    @Before(stages = {LifecycleStage.BindingAndValidation})
//    public void rehydrate() {
//        System.out.println("--------------rehydrate--------------");
//        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
//        if (idPermohonan != null) {
//            try {
//                permohonan = permohonanDAO.findById(idPermohonan);
//                kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "EBT");
//                senaraiPengguna = enforceService.getSenaraiPenggunaEksibit();
//                System.out.println("kertas : " + kertas);
//                if (kertas != null) {
//                    tajuk = kertas.getTajuk();
//                    senaraiKertas = enforceService.findByIdKertas3(kertas.getIdKertas());
//
//                    if (senaraiKertas != null) {
//                        recordCount = String.valueOf(senaraiKertas.size());
//                    }
//
//                    mohonTandatanganDokumen = enforceService.findPermohonanTandatanganDok(idPermohonan, "EBT");
//
//                    if (mohonTandatanganDokumen != null) {
//                        diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
//                    }
//
//                }
//
//
//            } catch (Exception ex) {
//
//                LOG.error(ex);
//                ex.printStackTrace();
//            }
//
//        }
//
//    }

//    public Resolution simpan() throws ParseException {
//
//        InfoAudit ia = new InfoAudit();
//
//
//        if (kertas == null) {
//            kertas = new PermohonanKertas();
//            ia.setDimasukOleh(pengguna);
//            ia.setTarikhMasuk(new Date());
//        } else {
//            ia = kertas.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengguna);
//            ia.setTarikhKemaskini(new java.util.Date());
//        }
//        kertas.setPermohonan(permohonan);
//        kertas.setInfoAudit(ia);
//        kertas.setTajuk("Eksibit");
//        kertas.setCawangan(pengguna.getKodCawangan());
//        kertas.setKodDokumen(kodDokumenDAO.findById("EBT"));
//
//        enforceService.simpanPermohonanKertas(kertas);
//
//        recordCount = getContext().getRequest().getParameter("recordCount");
//        if (StringUtils.isNotBlank(recordCount)) {
//            int row = Integer.parseInt(recordCount);
//            int j = 1;
//            for (int i = 0; i < row; i++) {
//                if (senaraiKertas.size() != 0 && i < senaraiKertas.size()) {
//                    Long id = senaraiKertas.get(i).getIdKandungan();
//                    if (id != null) {
//                        permohonanKertasKandungan = enforceService.findByIdKandungan(id);
//                    }
//                } else {
//                    permohonanKertasKandungan = new PermohonanKertasKandungan();
//                }
//                kandungan = getContext().getRequest().getParameter("kandungan" + i);
//                LOG.info("kandungan : " + kandungan);
//                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
//                permohonanKertasKandungan.setKertas(kertas);
//                permohonanKertasKandungan.setKandungan(kandungan);
//                permohonanKertasKandungan.setInfoAudit(ia);
//                permohonanKertasKandungan.setBil(j);
//                j++;
//
//                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
//
//            }
//        }
//
//        if (mohonTandatanganDokumen == null) {
//            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
//            ia.setDimasukOleh(pengguna);
//            ia.setTarikhMasuk(new java.util.Date());
//        } else {
//            ia = mohonTandatanganDokumen.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengguna);
//            ia.setTarikhKemaskini(new java.util.Date());
//        }
//
//        mohonTandatanganDokumen.setPermohonan(permohonan);
//        mohonTandatanganDokumen.setInfoAudit(ia);
//        mohonTandatanganDokumen.setCawangan(pengguna.getKodCawangan());
//        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
//        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("EBT"));
//        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
//        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);
//
//
//        boolean flag = true;
//
//        if (flag) {
//            addSimpleMessage("Maklumat telah berjaya disimpan.");
//        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_eksibit.jsp").addParameter("tab", "true");
//    }
//
//    // Click on Hapus button in HomePage
//    public Resolution deleteLaporan() {
//        idKandungan = getContext().getRequest().getParameter("idKandungan");
//        System.out.println("idKertas:" + idKandungan);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        try {
//            if (idKandungan != null) {
//                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
//            }
//            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
//        } catch (Exception e) {
//            e.printStackTrace();
//            addSimpleError("kesalahan dalam menghapuskan rekod");
//        }
//        addSimpleMessage("Rekod berjaya dihapuskan");
//        rehydrate();
////        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_pemantauan.jsp").addParameter("tab", "true");
//        return showForm();
//    }
    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
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

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
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

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public PermohonanKertasKandungan getMaklumanUlasan() {
        return maklumanUlasan;
    }

    public void setMaklumanUlasan(PermohonanKertasKandungan maklumanUlasan) {
        this.maklumanUlasan = maklumanUlasan;
    }

    public String getMakluman() {
        return makluman;
    }

    public void setMakluman(String makluman) {
        this.makluman = makluman;
    }
}

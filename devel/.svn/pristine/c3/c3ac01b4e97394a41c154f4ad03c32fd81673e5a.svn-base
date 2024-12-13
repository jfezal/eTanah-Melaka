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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import org.hibernate.Session;
//import net.sourceforge.stripes.action.StreamingResolution;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/diari_siasatan_IO")
public class DiariSiasatanIOActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
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
    private static final Logger LOG = Logger.getLogger(DiariSiasatanIOActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private Hakmilik hakmilik;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kandungan;
    private String tajuk;
    private String idHakmilik;
    private String tarikhButiran;
    private String jam;
    private String minit;
    private String ampm;
    private String kodDokumen1;
    private String kod;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String recordCount;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private List<PermohonanKertas> senaraiKertasIO;
    private int rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasan;
    private String kodNegeri;
    private Long idKertas;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("view2", Boolean.TRUE);
        return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        senaraiKertasIO = new ArrayList<PermohonanKertas>();
        if (idPermohonan != null) {
            try {
                permohonan = permohonanDAO.findById(idPermohonan);
                kertas = enforceService.findByIdKrts(idPermohonan, "DIAIO");

                if (kertas == null) {
                    if (permohonan.getPermohonanSebelum() != null) {
                        kertas = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "DIAIO");
                    }
                }
                permohonan = permohonanDAO.findById(idPermohonan);
                senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());
                senaraiKertasIO = enforceService.findByIdKertasIO(kertas.getPermohonan().getIdPermohonan(), kertas.getIdKertas());

                if (kertas != null) {
                    tajuk = kertas.getTajuk();
                }

                if (senaraiKertasIO != null) {
                    System.out.println("test rekod IO..........." + senaraiKertasIO.size());
                    recordCount = String.valueOf(senaraiKertasIO.size());

                    for (int i = 0; i < senaraiKertasIO.size(); i++) {
                        idKertas = senaraiKertasIO.get(i).getIdKertas();
                        kod = senaraiKertasIO.get(i).getKodDokumen().getNama();
                        tajuk = senaraiKertasIO.get(i).getTajuk();
                    }
                    System.out.println("Rekod IO.............");

                }

                if (senaraiKertas != null) {
                    System.out.println("mana kah jam nie....." + senaraiKertas.size());
                    recordCount = String.valueOf(senaraiKertas.size());

                    for (int i = 0; i < senaraiKertas.size(); i++) {
                        jam = jamFormat.format(senaraiKertas.get(i).getTarikhButiran());
                        System.out.println("jam format : " + jam);
                        minit = minitFormat.format(senaraiKertas.get(i).getTarikhButiran());
                        System.out.println("minit format : " + minit);
                        ampm = ampmFormat.format(senaraiKertas.get(i).getTarikhButiran());
                        System.out.println("ampm format : " + ampm);
                    }
                    System.out.println("mana record");
////                    if (permohonanKertasKandungan != null) {
////                    tarikhButiran = sdf.format(permohonanKertasKandungan.getTarikhButiran());
//                        tarikhButiran = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(0, 10);
//                        jam = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(11, 13);
//                        minit = minitFormat.format(permohonanKertasKandungan.getTarikhButiran());
////                                sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(14, 16);
//                        ampm = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(17, 19);
//                        System.out.println("tarikh : " + tarikhButiran + "jam : "+ jam +"minit : "+minit+ "ampm : "+ampm);
                }

            } catch (Exception ex) {
                LOG.error(ex);
            }


            permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "DSIO");//DS = Diari Siasatan
            if (permohonanLaporanUlasan != null) {
                ulasan = permohonanLaporanUlasan.getUlasan();
            }

        }

    }

//    public Resolution semakIdHakmilik(){
//        String result = "";
//
//        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        System.out.println("IdHakmilik ialah :"+idHakmilik);
//        Hakmilik idHakmilik2 = enforceService.semakIdHakmilik(idHakmilik);
//        if(idHakmilik2 != null){
//            result = "exist";
//        }else
//            result = "not exist";
//        return new StreamingResolution("test",result);
//    }
    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());
        if (kertas == null) {
            kertas = new PermohonanKertas();
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        KodCawangan caw = peng.getKodCawangan();
        kertas.setCawangan(caw);
        kod = "DIAIO";
        kertas.setKodDokumen(kodDokumenDAO.findById(kod));
        enforceService.simpanPermohonanKertas(kertas);

        int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        for (int i = 0; i < row; i++) {
            if (senaraiKertas.size() != 0 && i < senaraiKertas.size()) {
                Long id = senaraiKertas.get(i).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan = enforceService.findByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
            }

            String tarikh = getContext().getRequest().getParameter("tarikh" + i);
            jam = getContext().getRequest().getParameter("jam" + i);
            minit = getContext().getRequest().getParameter("minit" + i);
            ampm = getContext().getRequest().getParameter("ampm" + i);
            tarikh = tarikh + " " + jam + ":" + minit + " " + ampm;
            LOG.info("tarikh : " + tarikh);
            kandungan = getContext().getRequest().getParameter("kandungan" + i);
            LOG.info("kandungan : " + kandungan);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setKertas(kertas);
            permohonanKertasKandungan.setKandungan(kandungan);
            permohonanKertasKandungan.setTarikhButiran(sdf.parse(tarikh));

            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setBil(i);

            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

        }

        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
    }

    // Click on Hapus button in HomePage
    public Resolution deleteDiari() {
        idKandungan = getContext().getRequest().getParameter("idKandungan");
        System.out.println("idKertas:" + idKandungan);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = enforceService.findByIdKKand(idKandungan);
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/diari_siasatan_IO.jsp").addParameter("tab", "true");
        return showForm();
    }

    public Resolution simpanUlasan() {
        LOG.info("-------------simpanUlasan------------------");

        try {
            InfoAudit infoAudit = new InfoAudit();

            Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodPeranan = pguna.getPerananUtama().getKod();


            permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "DS");//DS = Diari Siasatan

            if (permohonanLaporanUlasan != null) {
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            permohonanLaporanUlasan.setJenisUlasan("DS");
            permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            enforceService.simpanRingkasanKes(permohonanLaporanUlasan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        } catch (Exception ex) {
            LOG.error(ex);
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
            return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
        }
        LOG.info("-------------simpan finish------------------");
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/diari_siasatan_IO.jsp").addParameter("tab", "true");
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

    public List<PermohonanKertas> getSenaraiKertasIO() {
        return senaraiKertasIO;
    }

    public void setSenaraiKertasIO(List<PermohonanKertas> senaraiKertasIO) {
        this.senaraiKertasIO = senaraiKertasIO;
    }

    public Long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(Long idKertas) {
        this.idKertas = idKertas;
    }
}

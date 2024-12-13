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
import etanah.model.KodDokumen;
import etanah.model.KodPeranan;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak modify by Siti Fariza Hanim
 */
@UrlBinding("/penguatkuasaan/maklumat_laporan_ringkas")
public class MaklumatLaporanRingkasActionBean extends AbleActionBean {

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
    private static final Logger LOG = Logger.getLogger(MaklumatLaporanRingkasActionBean.class);
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
    private int rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Pengguna pengguna;
    private String tarikhLaporan;
    private String ulasanPPT;
    private String ulasanPPTK;
    private String stageId;
    private PermohonanLaporanUlasan mohonLaporUlasPPT;
    private PermohonanLaporanUlasan mohonLaporUlasPPTK;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_ringkas.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);

        if (stageId.equalsIgnoreCase("semak1_laporan_ringkas")) {
            getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/laporan_ringkas.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "sah_laporan_awal2";
        }
        LOG.info("-------------stageId--" + stageId);

        mohonLaporUlasPPT = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "PPTLR");//PPTLR = ulasan ringkas PPT
        if (mohonLaporUlasPPT != null) {
            ulasanPPT = mohonLaporUlasPPT.getUlasan();
        }

        mohonLaporUlasPPTK = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "PPTKLR");//PPTKLR = ulasan laporan ringkas PPTK
        if (mohonLaporUlasPPTK != null) {
            ulasanPPTK = mohonLaporUlasPPTK.getUlasan();
        }

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        if (idPermohonan != null) {
            try {
                permohonan = permohonanDAO.findById(idPermohonan);
                kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "LRP");
                System.out.println("kertas : " + kertas);
                if (kertas != null) {
                    tajuk = kertas.getTajuk();
//                    senaraiKertas = enforceService.findByIdKertas3(kertas.getIdKertas());
                    senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());


                    try {
                        if (kertas.getTarikhSidang() != null) {
                            tarikhLaporan = sdf1.format(kertas.getTarikhSidang()).substring(0, 10);
                            jam = sdf1.format(kertas.getTarikhSidang()).substring(11, 13);
                            if (jam.startsWith("0")) {
                                jam = sdf1.format(kertas.getTarikhSidang()).substring(12, 13);
                            }
                            minit = sdf1.format(kertas.getTarikhSidang()).substring(14, 16);
                            ampm = sdf1.format(kertas.getTarikhSidang()).substring(17, 19);
                            System.out.println("jam : " + jam + "minit : " + minit + "ampm : " + ampm);
                        }

                    } catch (Exception e) {
                        LOG.error(e);
                        e.printStackTrace();
                    }

                    if (senaraiKertas != null) {
                        recordCount = String.valueOf(senaraiKertas.size());
                    }
                } else {
                    LOG.info("Laporan Ringkas null");
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            kertas = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "LRP");
                            if (kertas != null) {
                                tajuk = kertas.getTajuk();
                                senaraiKertas = enforceService.findByIdKertas3(kertas.getIdKertas());

                                try {
                                    if (kertas.getTarikhSidang() != null) {
                                        tarikhLaporan = sdf1.format(kertas.getTarikhSidang()).substring(0, 10);
                                        jam = sdf1.format(kertas.getTarikhSidang()).substring(11, 13);
                                        if (jam.startsWith("0")) {
                                            jam = sdf1.format(kertas.getTarikhSidang()).substring(12, 13);
                                        }
                                        minit = sdf1.format(kertas.getTarikhSidang()).substring(14, 16);
                                        ampm = sdf1.format(kertas.getTarikhSidang()).substring(17, 19);
                                        System.out.println("jam : " + jam + "minit : " + minit + "ampm : " + ampm);
                                    }


                                } catch (Exception e) {
                                    LOG.error(e);
                                    e.printStackTrace();
                                }
                            }

                            if (senaraiKertas != null) {
                                recordCount = String.valueOf(senaraiKertas.size());
                            }
                        }
                    }
                }



            } catch (Exception ex) {

                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();


        if (kertas == null) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("LRP"));
        try {
            if (tarikhLaporan != null) {
                tarikhLaporan = tarikhLaporan + " " + jam + ":" + minit + " " + ampm;
                kertas.setTarikhSidang(sdf1.parse(tarikhLaporan));
            }

        } catch (Exception e) {
            LOG.error(e);
        }

        enforceService.simpanPermohonanKertas(kertas);

        int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        int j = 1;
        for (int i = 0; i < row; i++) {
            if (senaraiKertas.size() != 0 && i < senaraiKertas.size()) {
                Long id = senaraiKertas.get(i).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan = enforceService.findByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
            }
            kandungan = getContext().getRequest().getParameter("kandungan" + i);
            LOG.info("kandungan : " + kandungan);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan.setKertas(kertas);
            permohonanKertasKandungan.setKandungan(kandungan);
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setBil(j);
            j++;

            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

        }


        if (mohonLaporUlasPPT != null) {
            ia = mohonLaporUlasPPT.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonLaporUlasPPT = new PermohonanLaporanUlasan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pengguna);
        }

        mohonLaporUlasPPT.setUlasan(ulasanPPT);
        mohonLaporUlasPPT.setJenisUlasan("PPTLR");
        mohonLaporUlasPPT.setCawangan(pengguna.getKodCawangan());
        mohonLaporUlasPPT.setPermohonan(permohonan);
        mohonLaporUlasPPT.setInfoAudit(ia);
        KodPeranan kp = kodPerananDAO.findById(pengguna.getPerananUtama().getKod());
        mohonLaporUlasPPT.setKodPeranan(kp);

        enforceService.simpanRingkasanKes(mohonLaporUlasPPT);

        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_ringkas.jsp").addParameter("tab", "true");
    }

    // Click on Hapus button in HomePage
    public Resolution deleteLaporan() {
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
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_ringkas.jsp").addParameter("tab", "true");
        return showForm();
    }

    public Resolution simpanUlasan() {
        LOG.info("-------------simpanUlasan------------------");

        try {
            InfoAudit infoAudit = new InfoAudit();

            String kodPeranan = pengguna.getPerananUtama().getKod();

            if (mohonLaporUlasPPTK != null) {
                infoAudit = mohonLaporUlasPPTK.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                mohonLaporUlasPPTK = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pengguna);
            }

            mohonLaporUlasPPTK.setUlasan(ulasanPPTK);
            mohonLaporUlasPPTK.setJenisUlasan("PPTKLR");
            mohonLaporUlasPPTK.setCawangan(pengguna.getKodCawangan());
            mohonLaporUlasPPTK.setPermohonan(permohonan);
            mohonLaporUlasPPTK.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            mohonLaporUlasPPTK.setKodPeranan(kp);

            enforceService.simpanRingkasanKes(mohonLaporUlasPPTK);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        } catch (Exception ex) {
            LOG.error(ex);
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
            return new JSP("penguatkuasaan/diari_siasatan.jsp").addParameter("tab", "true");
        }
        LOG.info("-------------simpan finish------------------");
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_ringkas.jsp").addParameter("tab", "true");
    }

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

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
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

    public String getTarikhLaporan() {
        return tarikhLaporan;
    }

    public void setTarikhLaporan(String tarikhLaporan) {
        this.tarikhLaporan = tarikhLaporan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getUlasanPPT() {
        return ulasanPPT;
    }

    public void setUlasanPPT(String ulasanPPT) {
        this.ulasanPPT = ulasanPPT;
    }

    public String getUlasanPPTK() {
        return ulasanPPTK;
    }

    public void setUlasanPPTK(String ulasanPPTK) {
        this.ulasanPPTK = ulasanPPTK;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanLaporanUlasan getMohonLaporUlasPPT() {
        return mohonLaporUlasPPT;
    }

    public void setMohonLaporUlasPPT(PermohonanLaporanUlasan mohonLaporUlasPPT) {
        this.mohonLaporUlasPPT = mohonLaporUlasPPT;
    }

    public PermohonanLaporanUlasan getMohonLaporUlasPPTK() {
        return mohonLaporUlasPPTK;
    }

    public void setMohonLaporUlasPPTK(PermohonanLaporanUlasan mohonLaporUlasPPTK) {
        this.mohonLaporUlasPPTK = mohonLaporUlasPPTK;
    }
}

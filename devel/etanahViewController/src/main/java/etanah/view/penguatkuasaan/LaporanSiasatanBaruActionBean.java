/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/penguatkuasaan/laporan_siasatan_baru")
public class LaporanSiasatanBaruActionBean extends AbleActionBean {

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
    private static final Logger LOG = Logger.getLogger(LaporanSiasatanBaruActionBean.class);
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
//    private Date tarikhSidang;
    private String tarikhSidang;
    private String jam;
    private String minit;
    private String ampm;
    private String kodDokumen1;
    private String kod;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String recordCount;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private int rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasan;
    private String kodNegeri;
    private String stageId;
    private String idKertas;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_siasatan_baru.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_siasatan_baru.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, peng);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
            }
        }
        kodNegeri = conf.getProperty("kodNegeri");

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        if (idPermohonan != null) {
            try {
                kertas = enforceService.findByIdKrts(idPermohonan, "LPS");
                permohonan = permohonanDAO.findById(idPermohonan);
                senaraiKertas = enforceService.findByIdKertas3(kertas.getIdKertas());

                if (kertas != null) {
                    tajuk = kertas.getTajuk();

                    if (kertas.getTarikhSidang() != null) {
                        tarikhSidang = sdf.format(kertas.getTarikhSidang());
                        tarikhSidang = sdf1.format(kertas.getTarikhSidang()).substring(0, 10);
                        jam = sdf1.format(kertas.getTarikhSidang()).substring(11, 13);
                        if (jam.startsWith("0")) {
                            jam = sdf1.format(kertas.getTarikhSidang()).substring(12, 13);
                        }
                        minit = sdf1.format(kertas.getTarikhSidang()).substring(14, 16);
                        System.out.println("minit trh_operasi : " + minit);
//                        if (minit.startsWith("0")) {
//                            minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
//                        }
                        ampm = sdf1.format(kertas.getTarikhSidang()).substring(17, 19);
                    }
                } else {
                    LOG.info("Laporan Awal null");
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            System.out.println("id mohon : " + permohonan.getPermohonanSebelum().getIdPermohonan());
                            kertas = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "LPS");
                            if (kertas != null) {
                                tajuk = kertas.getTajuk();
                                
                                if (kertas.getTarikhSidang() != null) {
                                    tarikhSidang = sdf.format(kertas.getTarikhSidang());
                                    tarikhSidang = sdf1.format(kertas.getTarikhSidang()).substring(0, 10);
                                    jam = sdf1.format(kertas.getTarikhSidang()).substring(11, 13);
                                    if (jam.startsWith("0")) {
                                        jam = sdf1.format(kertas.getTarikhSidang()).substring(12, 13);
                                    }
                                    minit = sdf1.format(kertas.getTarikhSidang()).substring(14, 16);
                                    System.out.println("minit trh_operasi : " + minit);
//                        if (minit.startsWith("0")) {
//                            minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
//                        }
                                    ampm = sdf1.format(kertas.getTarikhSidang()).substring(17, 19);
                                }
                            }
                        }
                    }
                }

                if (senaraiKertas != null) {
                    recordCount = String.valueOf(senaraiKertas.size());
                }

            } catch (Exception ex) {

                LOG.error(ex);
            }

        }

    }

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
        tarikhSidang = tarikhSidang + " " + jam + ":" + minit + " " + ampm;
        System.out.println("tarikh laporan :" + tarikhSidang);
        System.out.println("jam :" + jam);
        System.out.println("minit :" + minit);
        System.out.println("ampm :" + ampm);
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
//        kertas.setTarikhSidang(tarikhSidang);
        kertas.setTarikhSidang(sdf1.parse(tarikhSidang));
        KodCawangan caw = peng.getKodCawangan();
        kertas.setCawangan(caw);
        kod = "LPS";
        kertas.setKodDokumen(kodDokumenDAO.findById(kod));
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
            permohonanKertasKandungan.setSubtajuk(stageId);
            permohonanKertasKandungan.setBil(j);
            j++;

            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

        }

        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_siasatan_baru.jsp").addParameter("tab", "true");
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
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_siasatan_baru.jsp").addParameter("tab", "true");
        return showForm();
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }
}


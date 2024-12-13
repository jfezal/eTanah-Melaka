package etanah.view.stripes.pelupusan;

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
import org.hibernate.Session;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak modify by Siti Fariza Hanim, hazirah
 */
@UrlBinding("/pelupusan/surat_keputusan")
public class SuratKeputusanActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    BPelService service;
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
    private static final Logger LOG = Logger.getLogger(SuratKeputusanActionBean.class);
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
    private String stageId;
    private String taskId;
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
    private Task task = null;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        return new JSP("pelupusan/surat_keputusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/surat_keputusan.jsp").addParameter("tab", "true");
    }

    public String currentStageId(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
            stageId = "semak_surat_tolak2";
        }
        LOG.info(" ******* StageId *******:" + stageId);
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        if (idPermohonan != null) {
            try {
                taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                stageId = currentStageId(taskId);
//                if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    if (stageId.equals("sedia_surat_tolak") || stageId.equals("semak_surat_tolak") || stageId.equals("tandatangan_surat_tolak2") || stageId.equals("semak_surat_tolak2") || stageId.equals("tandatangan_surat_tolak")) {
                        permohonan = permohonanDAO.findById(idPermohonan);
                        kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "SMT");
                        System.out.println("kertas : " + kertas);
                        if (kertas != null) {
                            tajuk = kertas.getTajuk();
                            System.out.println("tajuk : "+ tajuk);
                            senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());

                            if (senaraiKertas != null) {
                                recordCount = String.valueOf(senaraiKertas.size());
                            }
                        } else {
                            LOG.info("Keputusan MMKN");
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                                if (permohonan.getPermohonanSebelum() != null) {
                                    kertas = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "SKM");
                                    if (kertas != null) {
                                        tajuk = kertas.getTajuk();
                                        senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());

                                    }

                                    if (senaraiKertas != null) {
                                        recordCount = String.valueOf(senaraiKertas.size());
                                    }
                                }
                            }
                        }
                    } else if (stageId.equals("sedia_surat_kelulusan") || stageId.equals("semak_surat_kelulusan") || stageId.equals("tandatangan_surat_kelulusan")) {
                        permohonan = permohonanDAO.findById(idPermohonan);
                        kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "SL");
                        System.out.println("kertas : " + kertas);
                        if (kertas != null) {
                            tajuk = kertas.getTajuk();
                            senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());

                            if (senaraiKertas != null) {
                                recordCount = String.valueOf(senaraiKertas.size());
                            }
                        } else {
                            LOG.info("Keputusan MMKN");
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                                if (permohonan.getPermohonanSebelum() != null) {
                                    kertas = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "SKM");
                                    if (kertas != null) {
                                        tajuk = kertas.getTajuk();
                                        senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());

                                    }

                                    if (senaraiKertas != null) {
                                        recordCount = String.valueOf(senaraiKertas.size());
                                    }
                                }
                            }
                        }
                    }
//                }


            } catch (Exception ex) {

                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();
        if (stageId.equals("sedia_surat_tolak") || stageId.equals("semak_surat_tolak") || stageId.equals("tandatangan_surat_tolak2") || stageId.equals("semak_surat_tolak2") || stageId.equals("tandatangan_surat_tolak")) {
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
            kertas.setKodDokumen(kodDokumenDAO.findById("SMT"));
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
        } else if (stageId.equals("sedia_surat_kelulusan") || stageId.equals("semak_surat_kelulusan") || stageId.equals("tandatangan_surat_kelulusan")) {
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
            kertas.setKodDokumen(kodDokumenDAO.findById("SL"));
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
        }

        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/surat_keputusan.jsp").addParameter("tab", "true");
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
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_remedi.jsp").addParameter("tab", "true");
        return showForm();
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}

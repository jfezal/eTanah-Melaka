/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.EnkuiriPenguatkuasaanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodJenisEnkuiriDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PihakDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
//import org.apache.derby.impl.sql.compile.ParseException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mazurahayati.yusop
 * @modified by ctzainal
 */
@UrlBinding("/penguatkuasaan/maklumat_laporan_enkuiri")
public class MaklumatLaporanEnkuiriActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatLaporanEnkuiriActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    EnkuiriPenguatkuasaanDAO enkuiriDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanpihakDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikpermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    CalendarEnkuiriManager manager;
    @Inject
    EnkuiriPenguatkuasaanDAO enkuiriPenguatkuasaanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodJenisEnkuiriDAO kodJenisEnkuiriDAO;
    private Permohonan permohonan;
    private EnkuiriPenguatkuasaan enkuiri;
    private PermohonanPihak permohonanpihak;
    private Pihak pihak;
    private Notis notis;
    private List<EnkuiriPenguatkuasaan> senaraiEnkuiri;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<Pihak> senaraiPihak;
    private List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan;
//    private String tarikhEnkuiri;
    private String tarikhMula;
    private String tujuan;
    private String jam;
    private String minit;
//    private String saat;
    private String ampm;
    private String hari;
    private String idPermohonan;
    private String catatan;
    private long idNotis;
    private long idNotis1;
    private long idNotis2;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
    SimpleDateFormat sdfToday = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat sdfdisplay = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String tempat;
//    private String perkara;
    private KodNotis kn;
    private Notis notis1;
    private String disabled = null;
    private String idPermohonanPihak;
    private HakmilikPermohonan hakmilikpermohonan;
    private Hakmilik hakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private List<CalendarEnkuiri> listCalendar;
    private String ulasan;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private List<Pengguna> senaraiPengguna;
    private Boolean borang2A = Boolean.FALSE;
    private Boolean borang2B = Boolean.FALSE;
    private String stageId;
    private Pengguna peng;
    private String kodJenisEnkuiri;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
//        if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
//            if (permohonan.getKeputusan() == null) {
//                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//                addSimpleError("Sila buat keputusan terlebih dahulu");
//            } else if (!permohonan.getKeputusan().getKod().matches("DD")) {
//                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//                addSimpleError("Notis ini tidak perlu disediakan");
//            } else {
//                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//            }
//        } else {
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().matches("127")) {
            if (permohonan.getKeputusan() == null) {
                getContext().getRequest().setAttribute("edit7b", Boolean.FALSE);
                addSimpleError("Sila buat keputusan terlebih dahulu");
            } else if (!permohonan.getKeputusan().getKod().matches("PE")) {
                getContext().getRequest().setAttribute("edit7b", Boolean.FALSE);
                addSimpleError("Notis ini tidak perlu disediakan");
            } else {
                getContext().getRequest().setAttribute("edit7b", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit7b", Boolean.TRUE);
        }
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("penguatkuasaan/calendar_enkuiri.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, peng);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
            }
        } else {
            stageId = "sedia_surat_enkuiri";
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //props for NS
                if (stageId.equalsIgnoreCase("semak2_laporan2")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                        kodJenisEnkuiri = "WA";
                    } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                        kodJenisEnkuiri = "WB";
                    }

                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("sedia_borang_gantian7e") || stageId.equalsIgnoreCase("semak_gantian_borang7e") || stageId.equalsIgnoreCase("sedia_borang7e") || stageId.equalsIgnoreCase("syor_kpsn_enkuiri_b7e") || stageId.equalsIgnoreCase("kpsn_enkuiri_b7e")) {
                        kodJenisEnkuiri = "7E";
                    } else if (stageId.equalsIgnoreCase("sedia_borang7b") || stageId.equalsIgnoreCase("peraku_borang7b") || stageId.equalsIgnoreCase("hantar_borang7b") || stageId.equalsIgnoreCase("sedia_borang_gantian7b") || stageId.equalsIgnoreCase("semak_gantian_borang7b") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7b") || stageId.equalsIgnoreCase("peraku_kertas_mmk")) {
                        kodJenisEnkuiri = "7B";
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (stageId.equalsIgnoreCase("sedia_surat_enkuiri")) {
                        borang2A = true;
                        kodJenisEnkuiri = "2A";
                    }
                }
            } else {
                //props for MLK
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("sedia_borang7e")) {
                        kodJenisEnkuiri = "7E";
                    } else if (stageId.equalsIgnoreCase("sedia_borang7b")) {
                        kodJenisEnkuiri = "7B";
                    } else if (stageId.equalsIgnoreCase("sedia_borang7f")) {
                        kodJenisEnkuiri = "7F";
                    } else if (stageId.equalsIgnoreCase("sedia_borang7a")) {
                        kodJenisEnkuiri = "7A";
                    } else if (stageId.equalsIgnoreCase("sedia_borang8a")) {
                        kodJenisEnkuiri = "8A";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (stageId.equalsIgnoreCase("sedia_notis_2ab")) { //else if (stageId.equalsIgnoreCase("sedia_notis_2ab"))
                        borang2A = true;
                        kodJenisEnkuiri = "2A";
                        borang2B = true;
                        //kodJenisEnkuiri = "2B";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    if (stageId.equalsIgnoreCase("sedia_borang") || stageId.equalsIgnoreCase("maklum_batal_endorsan")) {
                        kodJenisEnkuiri = "2A";
                    } else if (stageId.equalsIgnoreCase("sedia_borang2ab2")) {
                        kodJenisEnkuiri = "2A2";
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    if (stageId.equalsIgnoreCase("sedia_borang") || stageId.equalsIgnoreCase("maklum_batal_endorsan")) {
                        kodJenisEnkuiri = "2B";
                    } else if (stageId.equalsIgnoreCase("sedia_borang2ab2")) {
                        kodJenisEnkuiri = "2B2";
                    }
                }

            }


        }

        LOG.info("stageId : " + stageId);
        LOG.info("borang2A : " + borang2A);
        LOG.info("borang2B : " + borang2B);
        LOG.info("kodJenisEnkuiri : " + kodJenisEnkuiri);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            //calendar
            listCalendar = manager.getALLEnkuiri();

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                senaraiPengguna = enforceService.getSenaraiPguna();
            } else {
                ArrayList kumpulanBpel = new ArrayList<String>();
                if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                    kumpulanBpel.add("pptd"); //PPTD
                    kumpulanBpel.add("ptd"); //PTD
                    kumpulanBpel.add("ppttptgkuasa"); //PPTT
                    kumpulanBpel.add("pptkptgkuasa"); //PPTK

                } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                    kumpulanBpel.add("pptd"); //PPTD
                    kumpulanBpel.add("ptd"); //PTD
                    kumpulanBpel.add("ppttptdkuasa"); //PPTT
                    kumpulanBpel.add("pptkptdkuasa"); //PPTK
                }

                senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
            }

            LOG.info("size senarai pengguna : " + senaraiPengguna.size());

            try {
                String i = "";
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    i = ">";
                } else {
                    i = "<";
                }
                senaraiEnkuiri = enforceService.getSenaraiEnkuiri(idPermohonan);
                enkuiri = enforceService.getSenaraiPermohonanByTarikhEnkuiri(idPermohonan, sdf.parse(sdfToday.format(new Date()) + " 11:59 PM"), kodJenisEnkuiri, i);


                mohonTandatanganDokumen = enforceService.findPermohonanTandatanganDok(idPermohonan, kodJenisEnkuiri);

                if (mohonTandatanganDokumen != null) {
                    diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                }

                if (enkuiri != null) {
                    tempat = new String(enkuiri.getTempat());
                    catatan = new String(enkuiri.getCatatan());
//                        ulasan = new String(enkuiri.getUlasan());
                    if (enkuiri.getTarikhMula() != null) {
                        tarikhMula = sdfdisplay.format(enkuiri.getTarikhMula()).substring(0, 10);
                        jam = sdf.format(enkuiri.getTarikhMula()).substring(11, 13);
                        minit = sdf.format(enkuiri.getTarikhMula()).substring(14, 16);
                        ampm = sdf.format(enkuiri.getTarikhMula()).substring(17, 19);
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public Resolution simpanEnkuiri() {
        LOG.info("-----simpanEnkuiri-----");

        InfoAudit ia = new InfoAudit();

        try {
            if (enkuiri == null) {
                enkuiri = new EnkuiriPenguatkuasaan();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = enkuiri.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            tarikhMula = tarikhMula + " " + jam + ":" + minit + " " + ampm;
            enkuiri.setTarikhMula(sdfdisplay.parse(tarikhMula));
            enkuiri.setPermohonan(permohonan);
            enkuiri.setInfoAudit(ia);
            enkuiri.setCawangan(peng.getKodCawangan());
            enkuiri.setCatatan(catatan);
//            enkuiri.setUlasan(ulasan);
            enkuiri.setTujuan("ENKUIRI");
            enkuiri.setTempat(tempat);
            enkuiri.setStatus("A");
            enkuiri.setKodJenisEnkuiri(kodJenisEnkuiriDAO.findById(kodJenisEnkuiri));
            tarikhMula = sdfdisplay.format(enkuiri.getTarikhMula()).substring(0, 10);

        } catch (Exception ex) {
            LOG.error(ex);
        }
        enforceService.simpanEnkuiriPenguatkuasaan(enkuiri);

        if (mohonTandatanganDokumen == null) {
            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = mohonTandatanganDokumen.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        mohonTandatanganDokumen.setPermohonan(permohonan);
        mohonTandatanganDokumen.setInfoAudit(ia);
        mohonTandatanganDokumen.setCawangan(peng.getKodCawangan());
        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kodJenisEnkuiri));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_laporan_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution savePeringatan() throws FileNotFoundException, ParseException {

        String result = null;
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());

        notis1 = new Notis();
        Dokumen doc1 = new Dokumen();

        doc1.setInfoAudit(ia);
        KodDokumen kd = new KodDokumen();
        kd.setKod("NS");
        doc1.setKodDokumen(kd);
        doc1.setTajuk("Notis Kehadiran Siasatan");
        doc1.setNoVersi("1.0");
        enforceService.saveDokumenNotis(doc1);

        if (doc1 != null) {
            LOG.debug("idDocument :" + doc1.getIdDokumen());
            notis1.setDokumenNotis(doc1);
        }

        System.out.println("permohonan :" + permohonan.getIdPermohonan());
        notis1.setPermohonan(permohonan);
        notis1.setInfoAudit(ia);
        kn = new KodNotis();
        kn.setKod("NS");
        notis1.setKodNotis(kn);
        notis1.setTarikhNotis(now);
        notis1.setDokumenNotis(doc1);

        enforceService.saveNotis(notis1);
        disabled = "disabled";

        System.out.println("idNotis : " + notis1.getIdNotis());

        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);

        idNotis1 = notis1.getIdNotis();
        rehydrate();
        System.out.println("disabled :" + disabled);
        return new StreamingResolution("application/pdf");

    }

    public Resolution janaNotis() throws FileNotFoundException {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        String[] tnameEnk = {"permohonan"};
        Object[] modelEnk = {permohonan};

        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{permohonan.getIdPermohonan()};
        String path = "";
        String dokumenPath = conf.getProperty("document.path");
        Dokumen d = null;
        KodDokumen kd = null;

        List<EnkuiriPenguatkuasaan> senaraiEnkuiri = enkuiriDAO.findByEqualCriterias(tnameEnk, modelEnk, null);
        if (senaraiEnkuiri.size() != 0) {

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";
            if (permohonan.getKodUrusan().getKod().matches("351")) {
                kd = kodDokumenDAO.findById("2A");
                reportName = "ENFB2A_MLK.rdf";
            } else {
                kd = kodDokumenDAO.findById("2B");
                reportName = "ENFB2B_MLK.rdf";
            }
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            path = String.valueOf(permohonan.getFolderDokumen().getFolderId()) + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
        }
        File f = null;
        f = new File(dokumenPath + reportUtil.getDMSPath());
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Resolution deleteEnkuiri() {
        LOG.debug("delete enkuiri");
        Long idEnkuiri = Long.parseLong(getContext().getRequest().getParameter("idEnkuiri"));
        enkuiri = enkuiriPenguatkuasaanDAO.findById(idEnkuiri);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            enkuiriPenguatkuasaanDAO.delete(enkuiri);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");


        return new RedirectResolution(MaklumatLaporanEnkuiriActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public EnkuiriPenguatkuasaan getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(EnkuiriPenguatkuasaan enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<EnkuiriPenguatkuasaan> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public PermohonanPihak getPermohonanpihak() {
        return permohonanpihak;
    }

    public void setPermohonanpihak(PermohonanPihak permohonanpihak) {
        this.permohonanpihak = permohonanpihak;
    }

    public void setSenaraiEnkuiri(List<EnkuiriPenguatkuasaan> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
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

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(long idNotis) {
        this.idNotis = idNotis;
    }

    public KodNotis getKn() {
        return kn;
    }

    public void setKn(KodNotis kn) {
        this.kn = kn;
    }

    public Notis getNotis1() {
        return notis1;
    }

    public void setNotis1(Notis notis1) {
        this.notis1 = notis1;
    }

    public long getIdNotis1() {
        return idNotis1;
    }

    public void setIdNotis1(long idNotis1) {
        this.idNotis1 = idNotis1;
    }

    public long getIdNotis2() {
        return idNotis2;
    }

    public void setIdNotis2(long idNotis2) {
        this.idNotis2 = idNotis2;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(String idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public HakmilikPermohonan getHakmilikpermohonan() {
        return hakmilikpermohonan;
    }

    public void setHakmilikpermohonan(HakmilikPermohonan hakmilikpermohonan) {
        this.hakmilikpermohonan = hakmilikpermohonan;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihakBerkepentingan() {
        return listHakmilikPihakBerkepentingan;
    }

    public void setListHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan) {
        this.listHakmilikPihakBerkepentingan = listHakmilikPihakBerkepentingan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public List<CalendarEnkuiri> getListCalendar() {
        return listCalendar;
    }

    public void setListCalendar(List<CalendarEnkuiri> listCalendar) {
        this.listCalendar = listCalendar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getKodJenisEnkuiri() {
        return kodJenisEnkuiri;
    }

    public void setKodJenisEnkuiri(String kodJenisEnkuiri) {
        this.kodJenisEnkuiri = kodJenisEnkuiri;
    }

    public Boolean getBorang2A() {
        return borang2A;
    }

    public void setBorang2A(Boolean borang2A) {
        this.borang2A = borang2A;
    }

    public Boolean getBorang2B() {
        return borang2B;
    }

    public void setBorang2B(Boolean borang2B) {
        this.borang2B = borang2B;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

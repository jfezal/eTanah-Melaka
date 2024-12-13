/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/maklumat_lelongan_awam")
public class LelonganAwamActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(LelonganAwamActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private JuruLelong jurulelong;
    private Pihak pihak;
    private String idLelong;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan1;
    private List<Lelongan> senaraiLelongan2;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri1;
    private List<Enkuiri> senaraiEnkuiri2;
    private List<FasaPermohonan> senaraifasamohon;
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String tarikhLelong;
    private String idPermohonan;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tempat;
    private String tarikhAkhirBayaran;
    private String ejaanHarga;
    private String disabled;
    private String disabled1;
    private Integer bil;
    private BigDecimal hargaRizab;
    private BigDecimal baki;
    private BigDecimal deposit;
    private String status;
    private List<String> idHakmilik = new ArrayList<String>();
    private List<String> idHakmilik2 = new ArrayList<String>();
    private String stageId;
    private Pengguna pengguna;
    Task task = null;
    private String workflowId;
    private String idFolder = "";
    private BPelService service;
    private String taskId;
    private String kodUrusan;
    private String negeri;
    private boolean flag = false;
    private String edit = "false";
    private List<List<PermohonanPihak>> csvList = new ArrayList<List<PermohonanPihak>>();
    private List<List<PermohonanPihak>> csvList2 = new ArrayList<List<PermohonanPihak>>();
    private boolean negori;
    private BigDecimal amaunTunggakan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormA() {
        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("lelong/cetak16H_2B.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("lelong/PilihanPembida.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("lelong/calender_lelong2.jsp").addParameter("popup", "true");
    }

    //untuk Melaka
    public Resolution showForm4() {
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            senaraifasamohon = fasaPermohonanDAO.findByEqualCriterias(tname, tvalue, null);

            LOG.debug("senaraifasamohon.size :" + senaraifasamohon.size());

            for (FasaPermohonan fp : senaraifasamohon) {
                if (fp.getKeputusan() == null) {
                    status = null;
                    continue;
                }
                if ("LS".equals(fp.getKeputusan().getKod())) {
                    status = "LS";
                    LOG.debug("berjaya");
                    break;
                } else if ("RM".equals(fp.getKeputusan().getKod())) {
                    status = "RM";
                    LOG.debug("lagi berjaya");
                    break;
                }
            }

            if (status == null) {
                getContext().getRequest().setAttribute("error", Boolean.FALSE);
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
            }
            if (status.equals("LS")) {
                getContext().getRequest().setAttribute("error", Boolean.TRUE);
                addSimpleMessage("Tarikh lelongan awam hendaklah tidak kurang dari Satu Bulan Dari Tarikh Akhir Siasatan.");
                return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
            }
            if (status.equals("RM")) {
                getContext().getRequest().setAttribute("error", Boolean.TRUE);
                addSimpleMessage("Sila Klik Selesai untuk cetak sijil rujukan mahkamah");
                return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
            }
        }
        return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
    }

    //untuk n9
    public Resolution showForm4A() {
        setFlag(true);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            negeri = "NS";
        }
        addSimpleMessage("Tarikh lelongan awam hendaklah tidak kurang dari Satu Bulan Dari Tarikh Akhir Siasatan.");
        return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        return new JSP("lelong/maklumat_perintah_jualan_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            if (StringUtils.isBlank(taskId)) {
                taskId = getContext().getRequest().getParameter("taskId");
            }
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                System.out.println("stageId:" + stageId);
            } else {
                stageId = getContext().getRequest().getParameter("stageId");
                System.out.println("stageId:" + stageId);
            }

            if (permohonan != null) {
                KodUrusan ku = permohonan.getKodUrusan();
                kodUrusan = ku.getKod();
                workflowId = ku.getIdWorkflow();
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            }

            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                try {
                    String[] tname = {"permohonan"};
                    Object[] tvalue = {permohonan};
                    senaraifasamohon = fasaPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
//                senaraiEnkuiri = enkuiriDAO.findByEqualCriterias(tname, tvalue, null);

                    Session ss = sessionProvider.get();
                    Query qq = ss.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :idPermohonan order by e.idEnkuiri asc");
                    qq.setString("idPermohonan", idPermohonan);
                    senaraiEnkuiri = qq.list();


                    LOG.debug("senaraiEnkuiri size :" + senaraiEnkuiri.size());
                    enkuiri = senaraiEnkuiri.get(senaraiEnkuiri.size() - 1);


                    Session sss = sessionProvider.get();
                    Query qqq = sss.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
                    qqq.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
                    senaraiLelongan = qqq.list();

                    Session sss1 = sessionProvider.get();
                    Query qqq1 = sss1.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
                    qqq1.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
                    senaraiLelongan2 = qqq1.list();


                    //calender

                    List<PermohonanPihak> pihakle = new ArrayList<PermohonanPihak>();
                    senaraiEnkuiri2 = lelongService.getALLEnkuri(permohonan.getCawangan().getKod());
                    senaraiLelongan1 = new ArrayList<Lelongan>();

                    int i = 0;
                    for (Enkuiri ee : senaraiEnkuiri2) {
                        String hak = "";
                        List<Lelongan> listLL = lelongService.getLeloganALLDESC2(ee.getPermohonan().getIdPermohonan());
                        if (listLL.size() != 0) {
                            senaraiLelongan1.add(listLL.get(0));
                        }
                        List<PermohonanPihak> listHAkP = lelongService.getSenaraiPmohonPihak(ee.getPermohonan().getIdPermohonan());
                        LOG.info("listHAkP : " + listHAkP.size());
                        Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                        for (PermohonanPihak hp : listHAkP) {
                            Long id = hp.getPihak().getIdPihak();
                            if (pihakMap2.containsKey(id)) {
                                continue;
                            } else {
                                pihakMap2.put(id, hp);
                            }
                        }
                        pihakle = new ArrayList(pihakMap2.values());
                        csvList.add(i, pihakle);
                        StringBuilder sb = new StringBuilder();
                        for (HakmilikPermohonan hp : ee.getPermohonan().getSenaraiHakmilik()) {
                            Hakmilik h = hp.getHakmilik();
                            if (sb.length() > 0) {
                                sb.append("<br>");
                            }
                            sb.append(h.getIdHakmilik());
                        }
                        hak = sb.toString();
                        idHakmilik.add(i, hak);
                        i++;
                        pihakle = new ArrayList<PermohonanPihak>();
                    }
                    pihakle = new ArrayList<PermohonanPihak>();

                    LOG.info("senaraiLelongan1 : " + senaraiLelongan1.size());
                    int j = 0;
                    if (senaraiLelongan1.size() != 0) {
                        for (Lelongan ee : senaraiLelongan1) {
                            String hak = "";
                            List<PermohonanPihak> listHAkP = lelongService.getSenaraiPmohonPihak(ee.getPermohonan().getIdPermohonan());
                            LOG.info("listHAkP : " + listHAkP.size());
                            Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                            for (PermohonanPihak hp : listHAkP) {
                                Long id = hp.getPihak().getIdPihak();
                                if (pihakMap2.containsKey(id)) {
                                    continue;
                                } else {
                                    pihakMap2.put(id, hp);
                                }
                            }
                            pihakle = new ArrayList(pihakMap2.values());
                            csvList2.add(j, pihakle);
                            StringBuilder sb = new StringBuilder();
                            for (HakmilikPermohonan hp : ee.getPermohonan().getSenaraiHakmilik()) {
                                Hakmilik h = hp.getHakmilik();
                                if (sb.length() > 0) {
                                    sb.append("<br>");
                                }
                                sb.append(h.getIdHakmilik());
                            }
                            hak = sb.toString();
                            idHakmilik2.add(hak);
                            j++;
                            pihakle = new ArrayList<PermohonanPihak>();
                        }
                    }
                    //end calender


                    //for negeri melake
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        negori = false;
                    } else {
                        negori = true;
                    }

                    if (senaraiLelongan.size() != 0) {

                        LOG.debug("senaraiLelong size :" + senaraiLelongan.size());
                        lelong = senaraiLelongan.get(senaraiLelongan.size() - 1);

                        for (Lelongan lelongan : senaraiLelongan) {
                            LOG.debug("idLelong :" + lelongan.getIdLelong());
                        }

//                        tarikhLelong = sdf.format(lelong.getTarikhLelong());
                        tempat = new String(lelong.getTempat());
                        ejaanHarga = new String(lelong.getEjaanHarga());
                        hargaRizab = lelong.getHargaRizab();
                        deposit = lelong.getDeposit();
                        status = lelong.getKodStatusLelongan().getKod();

                        if (senaraiLelongan.size() == 3) {
                            addSimpleMessage("Lelongan sebanyak 3 kali telah dilakukan.");
                            disabled = "disabled";
                        }

                        senaraiLelongan.remove(senaraiLelongan.size() - 1);

                        if (senaraiLelongan.size() == 0) {
                            lelong.setBil(1);
                        } else {
                            for (Lelongan lelongan : senaraiLelongan) {
                                lelong.setBil(lelongan.getBil() + 1);
                            }
                        }

                        tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            try {
                if (lelong.getTarikhLelong() != null) {

                    tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                    jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                    minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                    ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
                }
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }

    public Resolution simpanLelong() {

        String result = null;

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        for (Lelongan lelong : senaraiLelongan2) {
            if (lelong.getKodStatusLelongan().getKod().equals("AK")) {
                KodStatusLelongan ksl1 = new KodStatusLelongan();
                ksl1.setKod("TG");
                lelong.setKodStatusLelongan(ksl1);
                enService.simpan(lelong);
            }
        }

        Lelongan lel = new Lelongan();

        if (enkuiri != null) {
            LOG.debug("idEnkuiri :" + enkuiri.getIdEnkuiri());
            lel.setEnkuiri(enkuiri);
        }
        lel.setJurulelong(jurulelong);

        tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
        System.out.println("tarikhLelong :" + tarikhLelong);

        try {
            lel.setTarikhLelong(sdf.parse(tarikhLelong));
        } catch (Exception ex) {
            LOG.error(ex);
        }

        try {
            lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
        } catch (Exception ex) {
            LOG.error(ex);
        }

        lel.setTempat(tempat);
        lel.setHargaRizab(hargaRizab);
        lel.setBaki(baki);
        lel.setDeposit(deposit);
        lel.setEjaanHarga(ejaanHarga);
        lel.setTarikhAkhirBayaran(lel.getTarikhAkhirBayaran());


        KodStatusLelongan ksl = new KodStatusLelongan();
        ksl.setKod("AK");
        lel.setKodStatusLelongan(ksl);

        Session sss = sessionProvider.get();
        Query qqq = sss.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
        qqq.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
        senaraiLelongan = qqq.list();



        if (senaraiLelongan.size() == 0) {
            lel.setBil(1);
        } else {
            for (Lelongan lelongan : senaraiLelongan) {
                lel.setBil(lelongan.getBil() + 1);
            }
        }

        System.out.println("permohonan :" + permohonan.getIdPermohonan());
        lel.setEnkuiri(enkuiri);
        lel.setInfoAudit(ia);
        enService.simpan(lel);

//        if (lel.getBil() == 3) {
//            addSimpleMessage("Lelongan sebanyak 3 kali telah dilakukan.");
//            disabled = "disabled";
//        }

        System.out.println("idLelong : " + lel.getIdLelong());
        System.out.println("bil : " + lel.getBil());
        LOG.debug("lelong.getTarikhAkhirBayaran: " + lel.getTarikhAkhirBayaran());

        tarikhLelong = sdf.format(lel.getTarikhLelong()).substring(0, 10);
        tarikhAkhirBayaran = sdf1.format(lel.getTarikhAkhirBayaran());
        rehydrate();

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negeri = "Melaka";
            showForm4();
        } else {
            showForm4A();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/maklumat_perintah_jualan.jsp").addParameter("tab", "true");
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        System.out.println("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
            LOG.info("genReportFromXML");
            genReportFromXml();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
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
        doc.setTajuk(kd.getKod());
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

    private void genReportFromXml() throws Exception {
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        stageId = task.getSystemAttributes().getStage();
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            String gen = "LLGBorang16H_MLK.rdf";
            String prefix = "VDOC";
            String code = "16H";
//                String multipleReport = (String) m.get("multiple");
//                LOG.info("multipleReport :: " + multipleReport);
            String[] params = null;
            String[] values = null;
            KodDokumen kd = kodDokumenDAO.findById(code);
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));

            params = new String[]{"p_id_mohon"};
            values = new String[]{idPermohonan};
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("::Path To save :: " + (dokumenPath + path));
            LOG.info("::Report Name ::" + gen);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                hakmilikPermohonan.setDokumen1(d);
            }
            if (kd.getKod().equals("DHKE")) {
                hakmilikPermohonan.setDokumen3(d);
            }
            if (kd.getKod().equals("DHDE")) {
                hakmilikPermohonan.setDokumen2(d);
            }
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }
    }

    public Resolution genReport1() {
        LOG.info("genReport :: start");
        System.out.println("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
            LOG.info("genReportFromXML1");
            genReportFromXml1();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    private Dokumen saveOrUpdateDokumen1(FolderDokumen fd, KodDokumen kd, String id) {
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
        doc.setTajuk(kd.getKod());
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

    private void updatePathDokumen1(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private void genReportFromXml1() throws Exception {
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        stageId = task.getSystemAttributes().getStage();
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            String gen = "LLGIsytiharJual.rdf";
            String prefix = "VDOC";
            String code = "PJ";
//                String multipleReport = (String) m.get("multiple");
//                LOG.info("multipleReport :: " + multipleReport);
            String[] params = null;
            String[] values = null;
            KodDokumen kd = kodDokumenDAO.findById(code);
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));

            params = new String[]{"p_id_mohon"};
            values = new String[]{idPermohonan};
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
            d = saveOrUpdateDokumen1(fd, kd, idPermohonan);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            LOG.info("::Path To save :: " + (dokumenPath + path));
            LOG.info("::Report Name ::" + gen);
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen1(reportUtil.getDMSPath(), d.getIdDokumen());
            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                hakmilikPermohonan.setDokumen1(d);
            }
            if (kd.getKod().equals("DHKE")) {
                hakmilikPermohonan.setDokumen3(d);
            }
            if (kd.getKod().equals("DHDE")) {
                hakmilikPermohonan.setDokumen2(d);
            }
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        }
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(String idLelong) {
        this.idLelong = idLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public Integer getBil() {
        return bil;
    }

    public void setBil(Integer bil) {
        this.bil = bil;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getEjaanHarga() {
        return ejaanHarga;
    }

    public void setEjaanHarga(String ejaanHarga) {
        this.ejaanHarga = ejaanHarga;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled1() {
        return disabled1;
    }

    public void setDisabled1(String disabled1) {
        this.disabled1 = disabled1;
    }

    public List<Lelongan> getSenaraiLelongan1() {
        return senaraiLelongan1;
    }

    public void setSenaraiLelongan1(List<Lelongan> senaraiLelongan1) {
        this.senaraiLelongan1 = senaraiLelongan1;
    }

    public List<Enkuiri> getSenaraiEnkuiri1() {
        return senaraiEnkuiri1;
    }

    public void setSenaraiEnkuiri1(List<Enkuiri> senaraiEnkuiri1) {
        this.senaraiEnkuiri1 = senaraiEnkuiri1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(List<String> idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(List<String> idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public List<Lelongan> getSenaraiLelongan2() {
        return senaraiLelongan2;
    }

    public void setSenaraiLelongan2(List<Lelongan> senaraiLelongan2) {
        this.senaraiLelongan2 = senaraiLelongan2;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
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

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public List<List<PermohonanPihak>> getCsvList() {
        return csvList;
    }

    public void setCsvList(List<List<PermohonanPihak>> csvList) {
        this.csvList = csvList;
    }

    public List<List<PermohonanPihak>> getCsvList2() {
        return csvList2;
    }

    public void setCsvList2(List<List<PermohonanPihak>> csvList2) {
        this.csvList2 = csvList2;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public JuruLelong getJurulelong() {
        return jurulelong;
    }

    public void setJurulelong(JuruLelong jurulelong) {
        this.jurulelong = jurulelong;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
    }

    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }
    
}

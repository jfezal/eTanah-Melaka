/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.AkaunDAO;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;

import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;


import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;
import etanah.service.BPelService;

import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import java.io.File;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/printah_jual_negeri9")
public class PerintahJualanNegeri9ActionBean extends AbleActionBean {

    private static Logger LOG = Logger.getLogger(PerintahJualanNegeri9ActionBean.class);
    private static Logger logger = Logger.getLogger(PerintahJualanNegeri9ActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodTransaksiDAO KodTransaksiDAO;
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
    etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    private String idPermohonan;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> listPermohonanPihak;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Hakmilik hakmilik;
    private String idhakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPihakBerkepentingan> listHakmilikPP;
    private List<HakmilikPermohonan> listHakmilikPermohonan;
    private List<Enkuiri> enkuiriList;
    private List<Lelongan> lelonganList;
    private List<Akaun> listAkaun;
    private String namaDaerah;
    private Enkuiri enkuiri;
    private Lelongan lelongan;
    private BigDecimal hargaBidaan;
    private BigDecimal komisyen1;
    private BigDecimal komisyen2;
    private BigDecimal komisyen3;
    private BigDecimal komisyen4;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal c;
    private BigDecimal d;
    private BigDecimal e;
    private BigDecimal f;
    private BigDecimal g;
    private BigDecimal h;
    private BigDecimal baki1;
    private BigDecimal baki2;
    private BigDecimal baki3;
    private BigDecimal baki4;
    private BigDecimal totalKomisyen;
    private BigDecimal cukaitanah;
    private BigDecimal baki;
    private BigDecimal jumlah;
    private Akaun akaun;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private BPelService service;
    private Pengguna pengguna;
    private String taskId;
    private String stageId;
    Task task = null;
    private String kodUrusan;
    private String workflowId;
    private String idFolder = "";
    private ReportUtil reportUtil;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/perintah_jualan_Negeri9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("lelong/perintah_jualan_Negeri9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("lelong/syarat_jualan_n9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("lelong/kontrak_n9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        return new JSP("lelong/komisyenjualan_n9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {

        if (totalKomisyen != null) {

            permohonanTuntutanKos = lelongService.getPermohonanTuntutanKos(idPermohonan);
            if (permohonanTuntutanKos == null) {

                permohonanTuntutanKos = new PermohonanTuntutanKos();
                Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                KodCawangan caw = p.getKodCawangan();
                InfoAudit info = p.getInfoAudit();
                info.setDimasukOleh(p);
                info.setTarikhMasuk(new java.util.Date());
                permohonanTuntutanKos.setInfoAudit(info);
                permohonanTuntutanKos.setItem("Komisyen Jualan Lelongan");
                permohonanTuntutanKos.setCawangan(caw);
                permohonanTuntutanKos.setPermohonan(permohonan);
                permohonanTuntutanKos.setAmaunTuntutan(totalKomisyen);
                permohonanTuntutanKos.setPihak(permohonanPihak);
                KodTransaksi kodTransaksi = KodTransaksiDAO.findById("20017");
                permohonanTuntutanKos.setKodTransaksi(kodTransaksi);
                lelongService.saveOrUpdate(permohonanTuntutanKos);
            } else {
                permohonanTuntutanKos.setAmaunTuntutan(totalKomisyen);
                lelongService.saveOrUpdate(permohonanTuntutanKos);
            }
        }
        return new JSP("lelong/komisyenjualan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5A() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("lelong/komisyenjualan_n9.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        LOG.info("rehydrate start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        logger.info("inside rehydrate");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            logger.info("idPermohonan" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            namaDaerah = permohonan.getCawangan().getName();

            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            if (StringUtils.isBlank(taskId)) {
                taskId = getContext().getRequest().getParameter("taskId");
            }
            task = service.getTaskFromBPel(taskId, pengguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                LOG.info("stageId:" + stageId);
            } else {
                stageId = getContext().getRequest().getParameter("stageId");
                LOG.info("stageId:" + stageId);
            }

            if (permohonan != null) {
                KodUrusan ku = permohonan.getKodUrusan();
                kodUrusan = ku.getKod();
                workflowId = ku.getIdWorkflow();
                idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            }

        }

        LOG.info("inside rehydrate");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            LOG.info("idPermohonan" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            namaDaerah = permohonan.getCawangan().getName();

        }

        String[] v1 = {"permohonan"};
        Object[] n1 = {permohonan};
        listPermohonanPihak = permohonanPihakDAO.findByEqualCriterias(v1, n1, null);

        for (PermohonanPihak pp : listPermohonanPihak) {
            permohonanPihak = pp;
        }

        String[] v3 = {"permohonan"};
        Object[] n3 = {permohonan};
        listHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(v3, n3, null);
        for (HakmilikPermohonan oo : listHakmilikPermohonan) {
            hakmilikPermohonan = oo;
            hakmilik = oo.getHakmilik();
            idhakmilik = oo.getHakmilik().getIdHakmilik();
        }

        String[] v2 = {"hakmilik"};
        Object[] n2 = {hakmilik};
        listHakmilikPP = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(v2, n2, null);

        for (HakmilikPihakBerkepentingan hh : listHakmilikPP) {
            hakmilikPihakBerkepentingan = hh;
        }


        String[] v4 = {"akaun"};
        Object[] n4 = {akaun};
        listAkaun = akaunDAO.findByEqualCriterias(v4, n4, null);
        listAkaun = lelongService.getHakmilikA(idhakmilik);

        for (Akaun ak : listAkaun) {
            akaun = ak;
            hakmilik = ak.getHakmilik();
            idhakmilik = ak.getHakmilik().getIdHakmilik();
            logger.debug("idhakmilik akaun :" + idhakmilik);
            cukaitanah = ak.getBaki();
            logger.debug("baki akaun :" + cukaitanah);
        }


        if (permohonan.getPermohonanSebelum() != null) {
            enkuiriList = lelongService.getEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
            for (Enkuiri ee : enkuiriList) {
                enkuiri = ee;
            }
        } else {
            enkuiriList = lelongService.getEnkuiri(idPermohonan);
            for (Enkuiri ee : enkuiriList) {
                enkuiri = ee;
            }

        }

        if (enkuiri != null) {
            logger.info("enkuiri : " + enkuiri.getIdEnkuiri());

            String[] v5 = {"enkuiri"};
            Object[] n5 = {enkuiri};

            lelonganList = lelonganDAO.findByEqualCriterias(v5, n5, null);
            for (Lelongan lel : lelonganList) {
                lelongan = lel;
                cukaitanah = akaun.getBaki();
                logger.debug("baki : " + getCukaitanah());
            }
            try {
                if (lelongan.getHargaBidaan() != null) {

                    hargaBidaan = lelongan.getHargaBidaan();
                    a = BigDecimal.valueOf(10000);
                    baki1 = hargaBidaan.subtract(a);
                    logger.info("baki1:" + baki1);
                    if (baki1.intValue() > 0) {
                        baki2 = baki1.subtract(a);
                        logger.info("baki2:" + baki2);

                    } else if (baki1.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki2 = new BigDecimal(0.00);
                    }
                    if (baki2.intValue() > 0) {
                        baki3 = baki2.subtract(a);
                        logger.info("baki3:" + baki3);
                    } else if (baki2.intValue() <= 0) {
                        totalKomisyen = komisyen1;
                        baki3 = new BigDecimal(0.00);
                    }
                    logger.info("haragbidaaa:" + hargaBidaan);

                    if (hargaBidaan.intValue() < 10000) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen1 = hargaBidaan.multiply(e);
                        logger.info("komisyen1.0:" + komisyen1);

                    } else if (hargaBidaan.intValue() >= 10000) {
                        b = BigDecimal.valueOf(0.05);
                        komisyen1 = a.multiply(b);
                        logger.info("komisyen1:" + komisyen1);

                        totalKomisyen = komisyen1;

                    }
                    if (baki1.intValue() >= 10000) {

                        c = BigDecimal.valueOf(0.03);
                        komisyen2 = a.multiply(c);
//            baki2 = baki1.subtract(a);
                        logger.info("komisyen2.0:" + komisyen2);
                    } else if ((baki1.intValue() < 10000) && (baki1.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen2 = baki1.multiply(e);
                        logger.info("komisyen2:" + komisyen2);
                        totalKomisyen = komisyen1.add(komisyen2);
                    }


                    if (baki2.intValue() >= 10000) {

                        d = BigDecimal.valueOf(0.02);
                        komisyen3 = a.multiply(d);
//            baki3 = baki2.subtract(a);
                        logger.info("komisyen3.0:" + komisyen3);
                    } else if ((baki2.intValue() < 10000) && (baki2.intValue() > 0)) {
                        e = BigDecimal.valueOf(0.01);
                        komisyen3 = baki1.multiply(e);
                        logger.info("komisyen3:" + komisyen3);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3);
                    }
                    if ((baki3.intValue() > 0)) {

                        d = BigDecimal.valueOf(0.01);
                        komisyen4 = baki3.multiply(d);
                        logger.info("komisyen4.0:" + komisyen4);
                        totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
                        if (totalKomisyen.intValue() > 2000) {
                            f = BigDecimal.valueOf(2000);
                            totalKomisyen = f;
                        }
                    }
                    jumlah = totalKomisyen.add(cukaitanah);
                    logger.debug("jumlah dibayar :" + jumlah);

                    lelongan.setKomisyenJualan(totalKomisyen);
                    lelongan.setBakiCukaiSemasa(cukaitanah);
                    lelongService.saveOrUpdate(lelongan);







//                hargaBidaan = lelongan.getHargaBidaan();
//                a = BigDecimal.valueOf(1000);
//                b = BigDecimal.valueOf(0.04);
//                f = BigDecimal.valueOf(10000);
//                g = BigDecimal.valueOf(20000);
//                baki1 = hargaBidaan.subtract(a);
//                komisyen1 = a.multiply(b);
//                logger.info("baki1:" + getBaki1());
//                if ((baki1.intValue() > 1000) && (baki1.intValue() <= 10000)) {
//                    c = BigDecimal.valueOf(0.02);
//                    komisyen2 = baki1.multiply(c);
//                    baki2 = baki1.subtract(f);
//                    logger.info("baki2:" + getBaki2());
//
//                } else if ((baki2.intValue() > 10000) && (baki2.intValue() <= 20000)) {
//                    d = BigDecimal.valueOf(0.01);
//                    komisyen3 = baki2.multiply(d);
//                    baki3 = hargaBidaan.subtract(g);
//
//                } else if ((baki3.intValue() > 20001) && (baki3.intValue() <= 60000)) {
//                    d = BigDecimal.valueOf(0.01);
//                    komisyen4 = baki3.multiply(d);
//                    baki4 = hargaBidaan.subtract(baki3);
//                } else if (baki4.intValue() > 60000) {
//                    d = BigDecimal.valueOf(0.01);
//                    baki4.multiply(d);
//                }
//
//                totalKomisyen = komisyen1.add(komisyen2).add(komisyen3).add(komisyen4);
//                if (totalKomisyen.intValue() > 2000) {
//                    g = BigDecimal.valueOf(2000);
//                    totalKomisyen = g;
//                }
//
//                logger.info("haragbidaaa:" + hargaBidaan);
//
//
//                jumlah = totalKomisyen.add(cukaitanah);
//                logger.debug("jumlah dibayar :" + jumlah);
//
//                lelongan.setKomisyenJualan(totalKomisyen);
//                lelongan.setBakiCukaiSemasa(cukaitanah);
//                lelongService.saveOrUpdate(lelongan);
                }
            } catch (Exception ex) {
                logger.debug(ex);
            }
        }
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (StringUtils.isBlank(stageId)) {
            LOG.error("::Stage Id NULL::");
            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
        }

        try {
//            if ((!permohonan.getKodUrusan().getJabatan().getKod().equals("16"))
//                    && (!stageId.equals("kemasukan"))) {
            LOG.info("genReportFromXML");
            genReportFromXml();
//            }
//            LOGGER.info("genReportFromListener");
//            genReportFromListener();
        } catch (Exception ex) {
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

    private void genReportFromXml() throws Exception {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        String path = "";
        stageId = task.getSystemAttributes().getStage();
        Dokumen d = null;

        if (workflowId != null && stageId != null) {
            String gen = "LLG_KTN_BORANG_16Q.rdf";
            String prefix = "VDOC";
            String code = "16Q";
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
//                }
//            }
        }

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanPihak> getListPermohonanPihak() {
        return listPermohonanPihak;
    }

    public void setListPermohonanPihak(List<PermohonanPihak> listPermohonanPihak) {
        this.listPermohonanPihak = listPermohonanPihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getNamaDaerah() {
        return namaDaerah;
    }

    public void setNamaDaerah(String namaDaerah) {
        this.namaDaerah = namaDaerah;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<Enkuiri> getEnkuiriList() {
        return enkuiriList;
    }

    public void setEnkuiriList(List<Enkuiri> enkuiriList) {
        this.enkuiriList = enkuiriList;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public List<Lelongan> getLelonganList() {
        return lelonganList;
    }

    public void setLelonganList(List<Lelongan> lelonganList) {
        this.lelonganList = lelonganList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPP() {
        return listHakmilikPP;
    }

    public void setListHakmilikPP(List<HakmilikPihakBerkepentingan> listHakmilikPP) {
        this.listHakmilikPP = listHakmilikPP;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    /**
     * @return the hargaBidaan
     */
    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    /**
     * @param hargaBidaan the hargaBidaan to set
     */
    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }

    /**
     * @return the komisyen1
     */
    public BigDecimal getKomisyen1() {
        return komisyen1;
    }

    /**
     * @param komisyen1 the komisyen1 to set
     */
    public void setKomisyen1(BigDecimal komisyen1) {
        this.komisyen1 = komisyen1;
    }

    /**
     * @return the komisyen2
     */
    public BigDecimal getKomisyen2() {
        return komisyen2;
    }

    /**
     * @param komisyen2 the komisyen2 to set
     */
    public void setKomisyen2(BigDecimal komisyen2) {
        this.komisyen2 = komisyen2;
    }

    /**
     * @return the komisyen3
     */
    public BigDecimal getKomisyen3() {
        return komisyen3;
    }

    /**
     * @param komisyen3 the komisyen3 to set
     */
    public void setKomisyen3(BigDecimal komisyen3) {
        this.komisyen3 = komisyen3;
    }

    /**
     * @return the komisyen4
     */
    public BigDecimal getKomisyen4() {
        return komisyen4;
    }

    /**
     * @param komisyen4 the komisyen4 to set
     */
    public void setKomisyen4(BigDecimal komisyen4) {
        this.komisyen4 = komisyen4;
    }

    /**
     * @return the a
     */
    public BigDecimal getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(BigDecimal a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public BigDecimal getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(BigDecimal b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public BigDecimal getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(BigDecimal c) {
        this.c = c;
    }

    /**
     * @return the d
     */
    public BigDecimal getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(BigDecimal d) {
        this.d = d;
    }

    /**
     * @return the e
     */
    public BigDecimal getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(BigDecimal e) {
        this.e = e;
    }

    /**
     * @return the f
     */
    public BigDecimal getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(BigDecimal f) {
        this.f = f;
    }

    /**
     * @return the baki1
     */
    public BigDecimal getBaki1() {
        return baki1;
    }

    /**
     * @param baki1 the baki1 to set
     */
    public void setBaki1(BigDecimal baki1) {
        this.baki1 = baki1;
    }

    /**
     * @return the baki2
     */
    public BigDecimal getBaki2() {
        return baki2;
    }

    /**
     * @param baki2 the baki2 to set
     */
    public void setBaki2(BigDecimal baki2) {
        this.baki2 = baki2;
    }

    /**
     * @return the baki3
     */
    public BigDecimal getBaki3() {
        return baki3;
    }

    /**
     * @param baki3 the baki3 to set
     */
    public void setBaki3(BigDecimal baki3) {
        this.baki3 = baki3;
    }

    /**
     * @return the totalKomisyen
     */
    public BigDecimal getTotalKomisyen() {
        return totalKomisyen;
    }

    /**
     * @param totalKomisyen the totalKomisyen to set
     */
    public void setTotalKomisyen(BigDecimal totalKomisyen) {
        this.totalKomisyen = totalKomisyen;
    }

    /**
     * @return the cukaitanah
     */
    public BigDecimal getCukaitanah() {
        return cukaitanah;
    }

    /**
     * @param cukaitanah the cukaitanah to set
     */
    public void setCukaitanah(BigDecimal cukaitanah) {
        this.cukaitanah = cukaitanah;
    }

    /**
     * @return the baki
     */
    public BigDecimal getBaki() {
        return baki;
    }

    /**
     * @param baki the baki to set
     */
    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    /**
     * @return the jumlah
     */
    public BigDecimal getJumlah() {
        return jumlah;
    }

    /**
     * @param jumlah the jumlah to set
     */
    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    /**
     * @return the baki4
     */
    public BigDecimal getBaki4() {
        return baki4;
    }

    /**
     * @param baki4 the baki4 to set
     */
    public void setBaki4(BigDecimal baki4) {
        this.baki4 = baki4;
    }

    /**
     * @return the g
     */
    public BigDecimal getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(BigDecimal g) {
        this.g = g;
    }

    /**
     * @return the h
     */
    public BigDecimal getH() {
        return h;
    }

    /**
     * @param h the h to set
     */
    public void setH(BigDecimal h) {
        this.h = h;
    }

    public List<Akaun> getListAkaun() {
        return listAkaun;
    }

    public void setListAkaun(List<Akaun> listAkaun) {
        this.listAkaun = listAkaun;
    }

    public String getIdhakmilik() {
        return idhakmilik;
    }

    public void setIdhakmilik(String idhakmilik) {
        this.idhakmilik = idhakmilik;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        PerintahJualanNegeri9ActionBean.LOG = LOG;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PerintahJualanNegeri9ActionBean.logger = logger;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public ReportUtil getReportUtil() {
        return reportUtil;
    }

    public void setReportUtil(ReportUtil reportUtil) {
        this.reportUtil = reportUtil;
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
}

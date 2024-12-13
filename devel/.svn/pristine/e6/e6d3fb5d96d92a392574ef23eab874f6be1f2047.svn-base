/*
 *  Document   : keputusanKuatkuasa
 *  Created on : Jul 6, 2011, 4:29:29 PM
 *  Author     : Shah
 *  Edited     : Zadirul 14/7/2011
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.KodNegeri;
import etanah.model.KodPeranan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@UrlBinding("/strata/keputusanKuatkuasa")
public class KeputusanKuatkuasaActionBean extends AbleActionBean {

    @Inject
    StrataPtService sservice;
    @Inject
    CommonService comService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiSiasatDAO;
    @Inject
    FasaPermohonanDAO mohonFasaDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private KodJenisPengenalanDAO kodPengenalanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    etanah.Configuration conf;
    PegawaiPenyiasat pegawaiSiasat;
    FasaPermohonan mohonFasa;
    Permohonan permohonan;
    String kodurusan;
    String idPermohonan;
    Long idPermohonan2;
    String namaPegawai;
    String ulasan;
    String taskId;
    String noKp;
    KodNegeri kod;
    String namaJabatan;
    String namaBahagian;
    String kodCawangan;
    KodCawangan kodCawangan1;
    String alamat1;
    String alamat2;
    String alamat3;
    String alamat4;
    String poskod;
    String kodNegeri;
    String stageId;
    String keputusan;
    private static final Logger LOG = Logger.getLogger(KeputusanKuatkuasaActionBean.class);
    boolean adekes = false;
    boolean tiadakes = false;
    private String kodPengenalan;
    private String jawatan;
    private String negeri;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        stageId = "semaklaporan";
        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);
        String kodnegeri = conf.getProperty("kodNegeri");
        kodurusan = permohonan.getKodUrusan().getKod();

        if (mohonFasa != null) {
//            getContext().getRequest().setAttribute("adakes", Boolean.TRUE);
            ulasan = mohonFasa.getUlasan();
            if (kodnegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("P14A")) {
                adekes = true;

            } else if (mohonFasa.getKeputusan() != null) {
                keputusan = mohonFasa.getKeputusan().getKod();
                LOG.debug("KEPUTUSAN = " + keputusan);
                if (keputusan.equals("AK")) {
                    adekes = true;
                    LOG.debug("KEPUTUSAN = " + keputusan + " adekes: " + adekes);
                } else if (keputusan.equals("TK")) {
                    tiadakes = true;
                    LOG.debug("KEPUTUSAN = " + keputusan + " tiadakes: " + tiadakes);
                }
            }

        }

        return new JSP("strata/kuatkuasa/keputusanKuatkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution showFormN9() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        stageId = "semaklaporan";
        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
//            getContext().getRequest().setAttribute("adakes", Boolean.TRUE);
            ulasan = mohonFasa.getUlasan();
            if (mohonFasa.getKeputusan() != null) {
                keputusan = mohonFasa.getKeputusan().getKod();
                LOG.debug("KEPUTUSAN = " + keputusan);
            }

        }

        return new JSP("strata/kuatkuasa/keputusanKuatkuasaN9.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("---rehydrate---");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "semaklaporan";

        negeri = conf.getProperty("kodNegeri");
        LOG.info("CONF NEGERI : " + negeri);



//        idPermohonan2 = Long.parseLong(idPermohonan);
//        pegawaiSiasat = pegawaiSiasatDAO.findById(idPermohonan);
        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);
        if (pegawaiSiasat != null) {


            LOG.debug("pegawaiSiasat not null : " + pegawaiSiasat);
            namaPegawai = pegawaiSiasat.getNama();
            noKp = pegawaiSiasat.getNoPengenalan();
            namaJabatan = pegawaiSiasat.getNamaJabatan();
            namaBahagian = pegawaiSiasat.getNamaBahagian();
            alamat1 = pegawaiSiasat.getAlamat1();
            alamat2 = pegawaiSiasat.getAlamat2();
            alamat3 = pegawaiSiasat.getAlamat3();
            alamat4 = pegawaiSiasat.getAlamat4();
            poskod = pegawaiSiasat.getPoskod();
            jawatan = pegawaiSiasat.getJawatan();
            if (pegawaiSiasat.getJenisPengenalan() != null) {
                kodPengenalan = pegawaiSiasat.getJenisPengenalan().getKod();
            }
            if (pegawaiSiasat.getNegeri() != null) {
                kodNegeri = pegawaiSiasat.getNegeri().getKod();
            }
        } else {
            LOG.info("null pegawai penyiasat");
        }

        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
//            getContext().getRequest().setAttribute("adakes", Boolean.TRUE);
            ulasan = mohonFasa.getUlasan();
            if (mohonFasa.getKeputusan() != null) {
                keputusan = mohonFasa.getKeputusan().getKod();
                LOG.debug("KEPUTUSAN = " + keputusan);
                if (keputusan.equals("AK")) {
                    adekes = true;
                } else if (keputusan.equals("TK")) {
                    tiadakes = false;
                }
            }

        }
        BPelService service = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            if (stageId.equals("janasurattolak")) {
                getContext().getRequest().setAttribute("janasurattolak", Boolean.TRUE);
            }
        }

    }

    public Resolution adaKes() {
        adekes = true;
        tiadakes = false;
        keputusan = "AK";
//        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
        return new JSP("strata/kuatkuasa/keputusanKuatkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution tiadaKes() {
        tiadakes = true;
        adekes = false;
        keputusan = "TK";
//        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
        return new JSP("strata/kuatkuasa/keputusanKuatkuasa.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        LOG.info("---Simpan Pegawai Penyiasat---");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        comService.setPengguna(peng);
        InfoAudit infoAudit = new InfoAudit();

        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        kodCawangan1 = permohonan.getCawangan();

        if (!kodNegeri.isEmpty()) {
            kod = kodNegeriDAO.findById(kodNegeri);
        }

        if (pegawaiSiasat != null) {
            infoAudit = pegawaiSiasat.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        } else {
            pegawaiSiasat = new PegawaiPenyiasat();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        LOG.info(kodPengenalan);
        KodJenisPengenalan kp = kodPengenalanDAO.findById("B");

        pegawaiSiasat.setAlamat1(alamat1);
        pegawaiSiasat.setAlamat2(alamat2);
        pegawaiSiasat.setAlamat3(alamat3);
        pegawaiSiasat.setAlamat4(alamat4);
        pegawaiSiasat.setPermohonan(permohonan);
        pegawaiSiasat.setInfoAudit(infoAudit);
        pegawaiSiasat.setCawangan(kodCawangan1);
        pegawaiSiasat.setNamaJabatan(namaJabatan);
        pegawaiSiasat.setNamaBahagian(namaBahagian);
        pegawaiSiasat.setPoskod(poskod);
        pegawaiSiasat.setNama(namaPegawai);
        pegawaiSiasat.setJenisPengenalan(kp);
        pegawaiSiasat.setNoPengenalan(noKp);
        pegawaiSiasat.setNegeri(kod);

        String kodnegeri = conf.getProperty("kodNegeri");
        KodKeputusan kk = kodKeputusanDAO.findById("AK");

        if (kodnegeri.equals("04")) {
            pegawaiSiasat.setJawatan(jawatan);


        } else {
            if (!jawatan.isEmpty() || jawatan != null) {
                KodPeranan peranan = kodPerananDAO.findById(jawatan);
                pegawaiSiasat.setJawatan(peranan.getKod());
            } else {
                addSimpleError("Sila Pilih Jawatan Pegawai Siasatan");

            }
//            //if peranan = penptsrata1 (penolong pegawai tadbir), kod keputusan = A1
//            if (peranan.getKod().equals("22")) {
//                kk = kodKeputusanDAO.findById("A1");
//                LOG.info("KodKeputusan = " + kk.getNama());
//            } //if peranan = penptsrata1 (penolong pegawai tanah), kod keputusan = A2
//            else if (peranan.getKod().equals("20")) {
//                kk = kodKeputusanDAO.findById("A2");
//                LOG.info("KodKeputusan = " + kk.getNama());
//            }

        }


        sservice.simpanPegawaiPenyiasat(pegawaiSiasat);

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "semaklaporan";


//        BPelService service = new BPelService();
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, peng);
//            stageId = t.getSystemAttributes().getStage();
//        }
//        idPermohonan2 = Long.parseLong(idPermohonan);
        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
            LOG.info("--------fasaPermohonan not null---------::");
            infoAudit = mohonFasa.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("--------fasaPermohonan is null---------::");
            mohonFasa = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        mohonFasa.setPermohonan(permohonan);
        mohonFasa.setUlasan("");
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setIdPengguna(peng.getIdPengguna());
        mohonFasa.setIdAliran(stageId);
        mohonFasa.setCawangan(permohonan.getCawangan());
        mohonFasa.setKeputusan(kk);
        mohonFasa = sservice.saveFasaMohon(mohonFasa);
        permohonan.setKeputusan(kk);
        permohonan.setKeputusanOleh(peng);
        permohonan.setTarikhKeputusan(new java.util.Date());
        permohonan.setUlasan(ulasan);
        sservice.updateMohon(permohonan);

//        /* call service to generate report base on KOD_DOKUMEN = SLPPS */
//        List<String> listReportName = new ArrayList();
//        String kodnegeri = conf.getProperty("kodNegeri");
//        if (kodnegeri.equals("04")) {
//            listReportName.add("STRLantiKIO_MLK.rdf");
//        } else {
//            listReportName.add("STRLantiKIO_NS.rdf");
//        }
//
//        List<String> listKodDok = new ArrayList();
//        listKodDok.add("SLPPS");
//        comService.reportGen(permohonan, listReportName, listKodDok);
//
//        /* if AdaKes : DELETE folder_dok and dok kod_dokumen = STPS which related to TiadaKes */
//        Dokumen d = null;
//        KodDokumen kd = kodDokumenDAO.findById("STPS");
//        if (kd != null) {
//            LOG.info("KOD DOKUMEN : " + kd.getKod());
//            d = sservice.findDok(idPermohonan, kd.getKod());
//            if (d != null) {
//                /* delete KandunganFolder = table folder_dok */
//                LOG.info("IN DELETING KandunganFolder");
//                sservice.deleteKandunganFolderByIdDokumen(permohonan.getFolderDokumen().getFolderId(), d.getIdDokumen());
//                /* delete Dokumen */
//                LOG.info("IN DELETING Dokumen");
//                sservice.deleteAll2(d);
//            }
//
//        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
    }

    public Resolution simpan3() {
        LOG.info("---Simpan Pegawai Penyiasat---");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        comService.setPengguna(peng);
        InfoAudit infoAudit = new InfoAudit();

        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        kodCawangan1 = permohonan.getCawangan();

        if (!kodNegeri.isEmpty()) {
            kod = kodNegeriDAO.findById(kodNegeri);
        }

        if (pegawaiSiasat != null) {
            infoAudit = pegawaiSiasat.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        } else {
            pegawaiSiasat = new PegawaiPenyiasat();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        LOG.info(kodPengenalan);
        KodJenisPengenalan kp = kodPengenalanDAO.findById("B");

        pegawaiSiasat.setAlamat1(alamat1);
        pegawaiSiasat.setAlamat2(alamat2);
        pegawaiSiasat.setAlamat3(alamat3);
        pegawaiSiasat.setAlamat4(alamat4);
        pegawaiSiasat.setPermohonan(permohonan);
        pegawaiSiasat.setInfoAudit(infoAudit);
        pegawaiSiasat.setCawangan(kodCawangan1);
        pegawaiSiasat.setNamaJabatan(namaJabatan);
        pegawaiSiasat.setNamaBahagian(namaBahagian);
        pegawaiSiasat.setPoskod(poskod);
        pegawaiSiasat.setNama(namaPegawai);
        pegawaiSiasat.setJenisPengenalan(kp);
        pegawaiSiasat.setNoPengenalan(noKp);
        pegawaiSiasat.setNegeri(kod);

        String kodnegeri = conf.getProperty("kodNegeri");
        KodKeputusan kk = kodKeputusanDAO.findById("AK");

        if (kodnegeri.equals("04")) {
            pegawaiSiasat.setJawatan(jawatan);


        } else {
            if (!jawatan.isEmpty() || jawatan != null) {
                KodPeranan peranan = kodPerananDAO.findById(jawatan);
                pegawaiSiasat.setJawatan(jawatan);
            } else {
                addSimpleError("Sila Pilih Jawatan Pegawai Siasatan");

            }
        }


        sservice.simpanPegawaiPenyiasat(pegawaiSiasat);

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "semaklaporan";

        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
            LOG.info("--------fasaPermohonan not null---------::");
            infoAudit = mohonFasa.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("--------fasaPermohonan is null---------::");
            mohonFasa = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        mohonFasa.setPermohonan(permohonan);
        mohonFasa.setUlasan("");
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setIdPengguna(peng.getIdPengguna());
        mohonFasa.setIdAliran(stageId);
        mohonFasa.setCawangan(permohonan.getCawangan());
        mohonFasa.setKeputusan(kk);
        mohonFasa = sservice.saveFasaMohon(mohonFasa);
        permohonan.setKeputusan(kk);
        permohonan.setKeputusanOleh(peng);
        permohonan.setUlasan(ulasan);
        permohonan.setTarikhKeputusan(new java.util.Date());
        sservice.updateMohon(permohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showFormN9");
    }

    public Resolution simpan2() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = "semaklaporan";
        KodKeputusan kk = kodKeputusanDAO.findById("TK");
        LOG.info("KodKeputusan = " + kk.getNama());
        comService.setPengguna(peng);
//        BPelService service = new BPelService();
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, peng);
//            stageId = t.getSystemAttributes().getStage();
//        }
//        idPermohonan2 = Long.parseLong(idPermohonan);
        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);
        if (pegawaiSiasat != null) {
            LOG.info("----IN DELETING Pegawai Siasat----");
            sservice.deletePegawaSiasatByIdMohon(idPermohonan);
        }

        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
            LOG.info("::--------fasaPermohonan not null---------::");
            infoAudit = mohonFasa.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("::--------fasaPermohonan is null---------::");
            mohonFasa = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        mohonFasa.setPermohonan(permohonan);
        mohonFasa.setUlasan(ulasan);
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setIdAliran(stageId);
        mohonFasa.setIdPengguna(peng.getIdPengguna());
        mohonFasa.setCawangan(permohonan.getCawangan());
        mohonFasa.setKeputusan(kk);
        mohonFasa = sservice.saveFasaMohon(mohonFasa);
        permohonan.setKeputusan(kk);
        permohonan.setKeputusanOleh(peng);
        permohonan.setTarikhKeputusan(new java.util.Date());
        permohonan.setUlasan(ulasan);
        sservice.updateMohon(permohonan);

//        /* call service to generate report base on KOD_DOKUMEN = STPS */
//        List<String> listReportName = new ArrayList();
//        String kodnegeri = conf.getProperty("kodNegeri");
//        if (kodnegeri.equals("04")) {
//            listReportName.add("STRSTolakKes_MLK.rdf");
//        } else {
//            listReportName.add("STRSTolakKes_NS.rdf");
//        }
//        List<String> listKodDok = new ArrayList();
//        listKodDok.add("STPS");
//        comService.reportGen(permohonan, listReportName, listKodDok);
//
//        /* if AdaKes : DELETE folder_dok and dok kod_dokumen = SLPPS which related to TiadaKes */
//        Dokumen d = null;
//        KodDokumen kd = kodDokumenDAO.findById("SLPPS");
//        if (kd != null) {
//            LOG.info("KOD DOKUMEN : " + kd.getKod());
//            d = sservice.findDok(idPermohonan, kd.getKod());
//            if (d != null) {
//                /* delete KandunganFolder = table folder_dok */
//                LOG.info("IN DELETING KandunganFolder");
//                sservice.deleteKandunganFolderByIdDokumen(permohonan.getFolderDokumen().getFolderId(), d.getIdDokumen());
//                /* delete Dokumen */
//                LOG.info("IN DELETING Dokumen");
//                sservice.deleteAll2(d);
//            }
//        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
    }

    public Resolution isiSemula1() {
        LOG.info("--------isiSemula1 start here---------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);

        if (pegawaiSiasat != null) {
            sservice.deletePegawaSiasatByIdMohon(idPermohonan);
        }

        LOG.info("--------isiSemula1 end here---------::");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
    }

    public Resolution isiSemula3() {
        LOG.info("--------isiSemula1 start here---------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);

        if (pegawaiSiasat != null) {
            sservice.deletePegawaSiasatByIdMohon(idPermohonan);
        }

        LOG.info("--------isiSemula1 end here---------::");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showFormN9");
    }

    public Resolution isiSemula2() {
        LOG.info("--------isiSemula2 start here---------::");
        InfoAudit infoAudit = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        stageId = "semaklaporan";
        mohonFasa = sservice.findMohonFasa(idPermohonan, stageId);

        if (mohonFasa != null) {
            LOG.info("::--------fasaPermohonan not null---------::");
            infoAudit = mohonFasa.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("::--------fasaPermohonan is null---------::");
            mohonFasa = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        mohonFasa.setUlasan("");
        mohonFasa = sservice.saveFasaMohon(mohonFasa);

        LOG.info("--------isiSemula2 end here---------::");
        return new RedirectResolution(KeputusanKuatkuasaActionBean.class, "showForm");
    }

    /**
     * Setter and Getter *
     */
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPermohonan2() {
        return idPermohonan2;
    }

    public void setIdPermohonan2(Long idPermohonan2) {
        this.idPermohonan2 = idPermohonan2;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNamaBahagian() {
        return namaBahagian;
    }

    public void setNamaBahagian(String namaBahagian) {
        this.namaBahagian = namaBahagian;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public boolean isAdekes() {
        return adekes;
    }

    public void setAdekes(boolean adekes) {
        this.adekes = adekes;
    }

    public boolean isTiadakes() {
        return tiadakes;
    }

    public void setTiadakes(boolean tiadakes) {
        this.tiadakes = tiadakes;
    }

    public KodNegeri getKod() {
        return kod;
    }

    public void setKod(KodNegeri kod) {
        this.kod = kod;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(String kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getKodurusan() {
        return kodurusan;
    }

    public void setKodurusan(String kodurusan) {
        this.kodurusan = kodurusan;
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.KodHakmilik;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.daftar.BatalNotaService;
import etanah.view.strata.CommonService;
import etanah.service.common.HakmilikService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.io.File;

/**
 *
 * @author Murali
 */
public class KeputusanValidation2 implements StageListener {

    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikPermohonan hp;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    HakmilikService hakmilikServ;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    Pengguna pengguna;
    FolderDokumen fd;
    private static final Logger LOG = Logger.getLogger(KeputusanValidation2.class);
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Hakmilik hakmilikSTRATA;
    private Hakmilik hakmilikSTRATA2;
    private HakmilikPermohonan hakmilikSTRATA3;
    private Hakmilik NoBukuSTRATA;
    private Hakmilik hakmilikDaerahMukim;
    private Hakmilik hakmilikNoBukuSTRATA;
    private Hakmilik highestNoBukuStrata;
    private KodHakmilik kodHakmilik;
    private List<Hakmilik> listHighestNoBukuSTRATA = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikDaerahMukim = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikSTRATA = new ArrayList<Hakmilik>();
    private List<Hakmilik> nobukup = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiBukuSTRATA;
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    private PermohonanRujukanLuar permohonanRujLuar;
    @Inject
    BatalNotaService batalNotaService;
    private List<PermohonanBangunan> pBangunanProvisionalBlock;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t1 = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

        mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            String dokumenPath = conf.getProperty("document.path");
            Dokumen e = null;
            Dokumen f = null;
            Dokumen g = null;
            Dokumen h = null;
            Dokumen i = null;
            Dokumen j = null;
            Dokumen k = null;
            Dokumen b = null;
            Dokumen t = null;
            String idFolder = "";
            Permohonan permohonan = context.getPermohonan();
            String idPermohonan = permohonan.getIdPermohonan();

            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            String[] params = null;
            String[] values = null;
            String[] params2 = null;
            String[] values2 = null;
            String[] values3 = null;
            String[] value = null;
            String path2 = "";
            String path3 = "";
            String path4 = "";
            String path5 = "";
            String path6 = "";
            String path8 = "";
            String path10 = "";
            String path11 = "";
            String path12 = "";
            String path13 = "";
            String path15 = "";
            String gen2 = "";
            String gen3 = "";
            String gen4 = "";
            String gen5 = "";
            String gen6 = "";
            String gen8 = "";
            String gen9 = "";
            String gen10 = "";
            String gen11 = "";
            String gen12 = "";
            String gen13 = "";
            List<HakmilikPermohonan> hkm = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen();
            KodDokumen kd3 = new KodDokumen();
            KodDokumen kd4 = new KodDokumen();
            KodDokumen kd5 = new KodDokumen();
            KodDokumen kd6 = new KodDokumen();
            KodDokumen kd7 = new KodDokumen();
            KodDokumen kd8 = new KodDokumen();
            KodDokumen kd10 = new KodDokumen();
            KodDokumen kd11 = new KodDokumen();
            KodDokumen kd12 = new KodDokumen();
            KodDokumen kd13 = new KodDokumen();
            KodDokumen kd14 = new KodDokumen();
            KodDokumen kd15 = new KodDokumen();

            Hakmilik hkinduk = hakmilikDAO.findById(hkm.get(1).getHakmilik().getIdHakmilikInduk());
            List<HakmilikPermohonan> hk = strService.findIdHakmilikSortAsc(idPermohonan);
            LOG.info("--Hakmilik--::" + hk.get(1).getHakmilik().getIdHakmilik());
            LOG.info("--Hakmilik hkinduk--::" + hkinduk);
            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};

            gen2 = "STRB2K_MLK.rdf";
            gen3 = "STRB3K_MLK.rdf";

            if (mohon.getKodUrusan().getKod().equals("PBBS")
                    || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PBS")) {
                Hakmilik hakmilikBukuSTRATA;
                kodHakmilik = hakmilikServ.getKodHakmilik(hkinduk.getIdHakmilik());
                if (String.valueOf(kodHakmilik.getMilikDaerah()).equalsIgnoreCase("Y")) {//ptd
                    senaraiHakmilikSTRATA = hakmilikServ.getDaerahMukimbyIDHakmilik(hkinduk.getIdHakmilik());
                    for (int hm = 0; hm < 1; hm++) {
                        hakmilikSTRATA = senaraiHakmilikSTRATA.get(hm);
                        if (hakmilikSTRATA.getNoBukuDaftarStrata() == null) {
                            senaraiHakmilikDaerahMukim = strService.getFilterDaerahMukim(hakmilikSTRATA.getDaerah().getKod(), hakmilikSTRATA.getBandarPekanMukim().getKod());
                            if (senaraiHakmilikDaerahMukim != null) {
                                for (int gm = 0; gm < 1; gm++) {
                                    hakmilikDaerahMukim = senaraiHakmilikDaerahMukim.get(gm);
                                    listHighestNoBukuSTRATA = hakmilikServ.getHighestNoBukuSTRATAPTD(hakmilikSTRATA.getDaerah().getKod(), hakmilikSTRATA.getBandarPekanMukim().getKod(), "Y");
                                    if (listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA == null) {
                                        for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                                            hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                                            hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                                            hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                                            hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                                        }
                                    } else if (!listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA != null) {
                                        for (int zm = 0; zm < listHighestNoBukuSTRATA.size(); zm++) {
                                            highestNoBukuStrata = listHighestNoBukuSTRATA.get(zm);
                                            String highestNo = highestNoBukuStrata.getNoBukuDaftarStrata();
                                            int highestNoBuku = Integer.parseInt(highestNo);
                                            highestNoBuku = highestNoBuku + 1;
                                            for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                                                hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                                                hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                                                hakmilikBukuSTRATA.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
                                                hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                                            }
                                        }
                                    }
                                }
                            } else if (senaraiHakmilikDaerahMukim == null) {
                                for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                                    hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                                }
                            }
                        }
                    }
                } else if (String.valueOf(kodHakmilik.getMilikDaerah()).equalsIgnoreCase("T")) {//ptg
                    senaraiHakmilikSTRATA = hakmilikServ.getIDHakmilikByInduk(hkinduk.getIdHakmilik());
                    listHighestNoBukuSTRATA = hakmilikServ.getHighestNoBukuSTRATAPTG("T");
                    for (int pq = 0; pq < 1; pq++) {
                        hakmilikSTRATA = senaraiHakmilikSTRATA.get(pq);
                        if (hakmilikSTRATA.getNoBukuDaftarStrata() == null) {
                            if (listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA == null) {
                                for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                                    hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                                }
                            } else if (!listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA != null) {
                                highestNoBukuStrata = listHighestNoBukuSTRATA.get(0);
                                String highestNo = highestNoBukuStrata.getNoBukuDaftarStrata();
                                int highestNoBuku = Integer.parseInt(highestNo);
                                highestNoBuku = highestNoBuku + 1;
                                for (int qm = 0; qm < senaraiHakmilikSTRATA.size(); qm++) {
                                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(qm);
                                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                                    hakmilikBukuSTRATA.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
                                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                                }
                            }
                        }
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("PHPP")
                    || mohon.getKodUrusan().getKod().equals("PHPC")) {
                LOG.info("--PHPP/PHPC--::");
                for (int vm = 0; vm < hk.size(); vm++) {
                    if (hk.get(vm).getHakmilik().getNoBukuDaftarStrata() == null) {
                        LOG.info("xde no buku strata");
                    } else {
                        String noBuku = hk.get(vm).getHakmilik().getNoBukuDaftarStrata();

                        for (int V = 0; V < hk.size(); V++) {
                            Hakmilik nobuku = hk.get(V).getHakmilik();
                            LOG.info(nobuku);
                            nobuku.setNoBukuDaftarStrata(noBuku);
                            hakmilikDAO.saveOrUpdate(nobuku);
                        }
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("PSBS")) {
                LOG.info("--PSBS--::");
                String idHakmilikInduk = hkinduk.getIdHakmilik();
                nobukup = hakmilikServ.getIDHakmilikByInduk(idHakmilikInduk);
                LOG.info("nobukup " + nobukup.size());

                LOG.info("HK" + hk.size());
                for (int v = 0; v < nobukup.size(); v++) {
                    if (nobukup.get(v).getNoBukuDaftarStrata().isEmpty()) {
                        LOG.info("xde no buku strata");
                    } else {
                        String noBuku = nobukup.get(v).getNoBukuDaftarStrata();
                        for (int vm = 0; vm < hk.size(); vm++) {
                            LOG.info("--Hakmilik--::" + hk.get(vm).getHakmilik().getIdHakmilik());
                            LOG.info("jumlh bit" + hk.get(vm).getHakmilik().getIdHakmilik().length());
                            Integer pnjg = hk.get(vm).getHakmilik().getIdHakmilik().length();

                            if (pnjg >= 18) {

                                Hakmilik nobuku = hk.get(vm).getHakmilik();
                                LOG.info(nobuku);
                                nobuku.setNoBukuDaftarStrata(noBuku);
                                hakmilikDAO.saveOrUpdate(nobuku);
                            }
                        }
                    }
                }
            }

            LOG.info("--Generating 3K--::");
            kd3.setKod("3K");
            f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            LOG.info("::Path To save :: " + path3);
            LOG.info("::Report Name ::" + gen3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            LOG.info("--Generated 3K--::");


            if (!(mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP"))) {
                if (mohon.getKodUrusan().getKod().equals("PSBS")) {
                    gen13 = "STRB4AKB_MLK.rdf";
                    kd15.setKod("4AKB");
                    b = saveOrUpdateDokumen(fd, kd15, hkinduk.getIdHakmilik(), context);
                    path15 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                    LOG.info("::Path To save :: " + path15);
                    LOG.info("::Report Name ::" + gen13);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd15.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path15);
                    reportUtil.generateReport(gen13, params, values, dokumenPath + path15, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                }

                LOG.info("--Generating 2K--::");
                kd2.setKod("2K");
                e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                LOG.info("::Path To save :: " + path2);
                LOG.info("::Report Name ::" + gen2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                LOG.info("--Generated 2K--::");

                for (HakmilikPermohonan hakmilikPermohonan : hk) {
                    LOG.info("--Generating 4K DHKK--::");
                    if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() != null) {
                        String idHakmilik2 = hakmilikPermohonan.getHakmilik().getIdHakmilik();
                        if (hakmilikPermohonan.getHakmilik().getKodKategoriBangunan().getKod().equals("B")) {
                            String idPermohonan1 = permohonan.getIdPermohonan();
                            values2 = new String[]{idPermohonan1.trim(), idHakmilik2};
                            gen9 = "STRB4KDHKK_MLK.rdf";
                            kd5.setKod("4KDHK");
                            b = saveOrUpdateDokumen(fd, kd5, idHakmilik2, context);
                            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                            LOG.info("::Path To save :: " + path5);
                            LOG.info("::Report Name ::" + gen9);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilik2 + " and saving it to:" + path5);
                            reportUtil.generateReport(gen9, params, values2, dokumenPath + path5, peng);
                            updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                            LOG.info("--Generated 4K DHKK--::");

                            LOG.info("--Generating 4K DHDK--::");
                            gen5 = "STRB4KDHDK_MLK.rdf";
                            kd6.setKod("4KDHD");
                            b = saveOrUpdateDokumen(fd, kd6, idHakmilik2, context);
                            path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                            LOG.info("::Path To save :: " + path6);
                            LOG.info("::Report Name ::" + gen5);
                            syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + idHakmilik2 + " and saving it to:" + path6);
                            reportUtil.generateReport(gen5, params, values2, dokumenPath + path6, peng);
                            updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                            LOG.info("--Generated 4K DHDK--::");
                        }

                        LOG.info("--Generating BSKDHDK--::");
                        values3 = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
                        gen10 = "STRBSKDHDK_MLK.rdf";
                        kd10.setKod("BSKDK");
                        t = saveOrUpdateDokumen(fd, kd10, idHakmilik2, context);
                        path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
                        LOG.info("::Path To save :: " + path10);
                        LOG.info("::Report Name ::" + gen10);
                        reportUtil.generateReport(gen10, params, values3, dokumenPath + path10, peng);
                        updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
                        LOG.info("--Generated BSKDHDK--::");

                        LOG.info("--Generating BSKDHKK--::");
                        gen6 = "STRBSKDHKK_MLK.rdf";
                        kd13.setKod("BSKKK");
                        t = saveOrUpdateDokumen(fd, kd13, idHakmilik2, context);
                        path11 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
                        LOG.info("::Path To save :: " + path11);
                        LOG.info("::Report Name ::" + gen6);
                        reportUtil.generateReport(gen6, params, values3, dokumenPath + path11, peng);
                        updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
                        LOG.info("--Generated BSKDHKK--::");
                    }
                }

//                LOG.info("--Generating BSKDHDK--::");
//                gen10 = "STRBSKDHDK_MLK.rdf";
//                kd10.setKod("BSKDK");
//                t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
//                path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
//                LOG.info("::Path To save :: " + path10);
//                LOG.info("::Report Name ::" + gen10);
//                reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
//                LOG.info("--Generated BSKDHDK--::");
//
//                LOG.info("--Generating BSKDHKK--::");
//                gen6 = "STRBSKDHKK_MLK.rdf";
//                kd13.setKod("BSKKK");
//                t = saveOrUpdateDokumen(fd, kd13, hkinduk.getIdHakmilik(), context);
//                path11 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
//                LOG.info("::Path To save :: " + path11);
//                LOG.info("::Report Name ::" + gen6);
//                reportUtil.generateReport(gen6, params, values, dokumenPath + path11, peng);
//                updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
//                LOG.info("--Generated BSKDHKK--::");

                pBangunanProvisionalBlock = strService.findByIDPermohonanByProvisional(permohonan.getIdPermohonan());
                LOG.info("---pBangunanProvisionalBlock---" + pBangunanProvisionalBlock.size());

                if (pBangunanProvisionalBlock.size() > 0) {
                    LOG.info("--Generating STRB4AK--::");
                    kd11.setKod("4ADHD");
                    gen11 = "STRB4AKDHDK_MLK.rdf";
                    j = saveOrUpdateDokumen(fd, kd11, hkinduk.getIdHakmilik(), context);
                    path12 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(j.getIdDokumen());
                    LOG.info("::Path To save :: " + path12);
                    LOG.info("::Report Name ::" + gen11);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd11.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path12);
                    reportUtil.generateReport(gen11, params, values, dokumenPath + path12, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), j.getIdDokumen());
                    LOG.info("--Generated STRB4AKDHDK_MLK--::");

                    kd12.setKod("4ADHK");
                    gen12 = "STRB4AKDHKK_MLK.rdf";
                    j = saveOrUpdateDokumen(fd, kd12, hkinduk.getIdHakmilik(), context);
                    path13 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(j.getIdDokumen());
                    LOG.info("::Path To save :: " + path13);
                    LOG.info("::Report Name ::" + gen12);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd12.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path13);
                    reportUtil.generateReport(gen12, params, values, dokumenPath + path13, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), j.getIdDokumen());
                    LOG.info("--Generated STRB4AKDHKK_MLK--::");
                }
            }


            LOG.info("--Bayaran5F--::");
            kd8.setKod("STR5F");
            gen8 = "STRBayaran5F_MLK.rdf";
            k = saveOrUpdateDokumen(fd, kd8, permohonan.getIdPermohonan(), context);
            path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
            LOG.info("::Path To save :: " + path8);
            LOG.info("::Report Name ::" + gen8);
            syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path8);
            reportUtil.generateReport(gen8, params, values, dokumenPath + path8, peng);
            updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
            LOG.info("--Generated Bayaran5F--::");

            //gen notis 5f
            LOG.info("--Generating 5F--::");
            kd4.setKod("5F");
            gen4 = "STRB5F_MLK.rdf";
            g = saveOrUpdateDokumen(fd, kd4, permohonan.getIdPermohonan(), context);
            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
            LOG.info("::Path To save :: " + path4);
            LOG.info("::Report Name ::" + gen4);
            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
            reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
            updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
            LOG.info("--Generated 5F--::");

            if (mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP")) {

                /*LOG.info("--Generating B4KBATAL--::");
                gen10 = "REGB4KBATAL_MLK.rdf";
                kd10.setKod("4KB");
                t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
                path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
                LOG.info("::Path To save :: " + path10);
                LOG.info("::Report Name ::" + gen10);
                reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
                updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
                LOG.info("--Generated B4KBATAL--::");*/

                gen2 = "STRB2KPH_MLK.rdf";

                LOG.info("--Generating 2K--::");
                kd2.setKod("2K");
                e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                LOG.info("::Path To save :: " + path2);
                LOG.info("::Report Name ::" + gen2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                LOG.info("--Generated 2K--::");


//                gen9 = "STRB4KDHKKPH_MLK.rdf";
//                    kd5.setKod("4KDHK");
//                    b = saveOrUpdateDokumen(fd, kd5, hkinduk.getIdHakmilik(), context);
//                    path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
//                    LOG.info("::Path To save :: " + path5);
//                    LOG.info("::Report Name ::" + gen9);
//                    syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
//                    reportUtil.generateReport(gen9, params, value, dokumenPath + path5, peng);
//                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
//                    LOG.info("--Generated 4K DHKK--::");
//
//                    LOG.info("--Generating 4K DHDK--::");
//                    gen5 = "STRB4KDHDKPH_MLK.rdf";
//                    kd6.setKod("4KDHD");
//                    b = saveOrUpdateDokumen(fd, kd6, hkinduk.getIdHakmilik(), context);
//                    path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
//                    LOG.info("::Path To save :: " + path6);
//                    LOG.info("::Report Name ::" + gen5);
//                    syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path6);
//                    reportUtil.generateReport(gen5, params, value, dokumenPath + path6, peng);
//                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
//                    LOG.info("--Generated 4K DHDK--::");

                for (HakmilikPermohonan hakmilikPermohonan : hk) {
                    Hakmilik hakmilik1 = new Hakmilik();

                    String idHakmilik2 = hakmilikPermohonan.getHakmilik().getIdHakmilik();
                    values = new String[]{idPermohonan.trim(), idHakmilik2};
                    value = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};

                    gen9 = "STRB4KDHKK_MLK.rdf";
                    kd5.setKod("4KDHK");
                    b = saveOrUpdateDokumen(fd, kd5, idHakmilik2, context);
                    path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                    LOG.info("::Path To save :: " + path5);
                    LOG.info("::Report Name ::" + gen9);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilik2 + " and saving it to:" + path5);
                    reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                    LOG.info("--Generated 4K DHKK--::");

                    LOG.info("--Generating 4K DHDK--::");
                    gen5 = "STRB4KDHDK_MLK.rdf";
                    kd6.setKod("4KDHD");
                    b = saveOrUpdateDokumen(fd, kd6, idHakmilik2, context);
                    path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                    LOG.info("::Path To save :: " + path6);
                    LOG.info("::Report Name ::" + gen5);
                    syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + idHakmilik2 + " and saving it to:" + path6);
                    reportUtil.generateReport(gen5, params, values, dokumenPath + path6, peng);
                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                    LOG.info("--Generated 4K DHDK--::");
                }

                LOG.info("--Generating BSKDHDK--::");
                //gen10 = "STRBSK_MLK.rdf";
                gen10 = "STRBSKDHDK_MLK.rdf";
                kd10.setKod("BSKDK");
                t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
                path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
                LOG.info("::Path To save :: " + path10);
                LOG.info("::Report Name ::" + gen10);
                reportUtil.generateReport(gen10, params, value, dokumenPath + path10, peng);
                updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
                LOG.info("--Generated BSKDHDK--::");

                LOG.info("--Generating BSKDHKK--::");
                gen6 = "STRBSKDHKK_MLK.rdf";
                kd14.setKod("BSKKK");
                t = saveOrUpdateDokumen(fd, kd14, hkinduk.getIdHakmilik(), context);
                path11 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
                LOG.info("::Path To save :: " + path11);
                LOG.info("::Report Name ::" + gen6);
                reportUtil.generateReport(gen6, params, value, dokumenPath + path11, peng);
                updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
                LOG.info("--Generated BSKDHKK--::");

            }
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    @Transactional
    private void saveHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);

    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan PTG");
        n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Timbalan Pengarah Hakmilik Geran Tanah untuk makluman");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("23"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

        //added at PAT Melaka on 11/07/2012 to initiate Task to Registration Module

        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = mohon.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
        String kodNegeri = conf.getProperty("kodNegeri");

//        /*initiating to HTB */flow slh
//        if (!(mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP"))) {
//            KodUrusan kod = kodUrusanDAO.findById("HTB");
//            LOG.info(kod.getNama());
//            LOG.info(mohon.getFolderDokumen());
//            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);
//
//            Permohonan mohonSebelum = strService.findIDSblmByKodUrusan(mohon.getIdPermohonan(), kod.getKod());
//            if(mohonSebelum != null){
//              context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonSebelum.getIdPermohonan()
//                      + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Hakmilik Strata(Pendaftaran Perbadanan Pengurusan (Pendaftaran Perbadanan Pengurusan).");
//            }
//        }
        if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                || mohon.getKodUrusan().getKod().equals("PSBS") || mohon.getKodUrusan().getKod().equals("PHPC")
                || mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PBS")) {
            updateStatusHakmilik(mohon, infoAudit, proposedOutcome);
        }

        if (kodNegeri.equals("05")) {
            context.addMessage(" - Makluman kepada Timbalan Pengarah Tanah dan Galian.");
        } else {
            context.addMessage(" - Makluman kepada Timbalan Pendaftar Hakmilik Geran Tanah.");
        }
        return proposedOutcome;
    }

    public void updateStatusHakmilik(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        String kod = permohonan.getKodUrusan().getKod();
        KodStatusHakmilik kodStatusHakmilik;
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan1");
        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                if (hakmilik != null) {
                    hakmilik.setInfoAudit(info);
                    if (kod.equals("PBBS") || kod.equals("PBBD")
                            || kod.equals("PSBS") || kod.equals("PBS")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
                        kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
                        hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                    }
                    if (kod.equals("PHPP") || kod.equals("PHPC")) {
                        kodStatusHakmilik = new KodStatusHakmilik();
                        if (hakmilik.getKodStatusHakmilik().getKod().equals("D")) {
                            kodStatusHakmilik = kodStatusHakmilikDAO.findById("B");
                            hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                        }
                        if (hakmilik.getKodStatusHakmilik().getKod().equals("T")) {
                            kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
                            hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                        }
                    }
                }
                strService.simpanHakmilik(hakmilik);
            }
        }
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    private void updateNewPetak(StageContext sc) {
        for (PermohonanBangunan pb : sc.getPermohonan().getSenaraiBangunan()) {
            pb.setPermohonan(sc.getPermohonan().getPermohonanSebelum());
            strService.saveMohonBangunan(pb);
        }

    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getNoBukuSTRATA() {
        return NoBukuSTRATA;
    }

    public void setNoBukuSTRATA(Hakmilik NoBukuSTRATA) {
        this.NoBukuSTRATA = NoBukuSTRATA;
    }

    public Hakmilik getHakmilikDaerahMukim() {
        return hakmilikDaerahMukim;
    }

    public void setHakmilikDaerahMukim(Hakmilik hakmilikDaerahMukim) {
        this.hakmilikDaerahMukim = hakmilikDaerahMukim;
    }

    public Hakmilik getHakmilikNoBukuSTRATA() {
        return hakmilikNoBukuSTRATA;
    }

    public void setHakmilikNoBukuSTRATA(Hakmilik hakmilikNoBukuSTRATA) {
        this.hakmilikNoBukuSTRATA = hakmilikNoBukuSTRATA;
    }

    public Hakmilik getHakmilikSTRATA() {
        return hakmilikSTRATA;
    }

    public void setHakmilikSTRATA(Hakmilik hakmilikSTRATA) {
        this.hakmilikSTRATA = hakmilikSTRATA;
    }

    public Hakmilik getHakmilikSTRATA2() {
        return hakmilikSTRATA2;
    }

    public void setHakmilikSTRATA2(Hakmilik hakmilikSTRATA2) {
        this.hakmilikSTRATA2 = hakmilikSTRATA2;
    }

    public Hakmilik getHighestNoBukuStrata() {
        return highestNoBukuStrata;
    }

    public void setHighestNoBukuStrata(Hakmilik highestNoBukuStrata) {
        this.highestNoBukuStrata = highestNoBukuStrata;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public List<Hakmilik> getListHighestNoBukuSTRATA() {
        return listHighestNoBukuSTRATA;
    }

    public void setListHighestNoBukuSTRATA(List<Hakmilik> listHighestNoBukuSTRATA) {
        this.listHighestNoBukuSTRATA = listHighestNoBukuSTRATA;
    }

    public List<Hakmilik> getSenaraiBukuSTRATA() {
        return senaraiBukuSTRATA;
    }

    public void setSenaraiBukuSTRATA(List<Hakmilik> senaraiBukuSTRATA) {
        this.senaraiBukuSTRATA = senaraiBukuSTRATA;
    }

    public List<Hakmilik> getSenaraiHakmilikDaerahMukim() {
        return senaraiHakmilikDaerahMukim;
    }

    public void setSenaraiHakmilikDaerahMukim(List<Hakmilik> senaraiHakmilikDaerahMukim) {
        this.senaraiHakmilikDaerahMukim = senaraiHakmilikDaerahMukim;
    }

    public List<Hakmilik> getSenaraiHakmilikSTRATA() {
        return senaraiHakmilikSTRATA;
    }

    public void setSenaraiHakmilikSTRATA(List<Hakmilik> senaraiHakmilikSTRATA) {
        this.senaraiHakmilikSTRATA = senaraiHakmilikSTRATA;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }
}

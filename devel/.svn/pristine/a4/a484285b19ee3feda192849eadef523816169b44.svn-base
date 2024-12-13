/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerintahDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodPerintah;
import etanah.model.KodRujukan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.Notis;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUkur;
import etanah.model.PihakAlamatTamb;
import etanah.model.gis.PelanGIS;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.CalcTax;
import etanah.service.EnforceService;
import etanah.service.JanaHakmilikService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.service.daftar.MohonHakmilikService;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author w.fairul
 */
public class GenerateIdPerserahanWorkflow1 {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow1.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodPerintahDAO kodPerintahDAO;
    @Inject
    private KodRujukanDAO kodRujukanDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    MohonHakmilikService mhservice;
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JanaHakmilikService janaHakmilikService;
    @Inject
    CalcTax calcTax;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    PermohonanUkur mohonUkur;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow1(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageId) {

        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            for (Hakmilik hm : senaraiHakmilik) {

                Permohonan p = new Permohonan();
                p.setStatus("TA");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), hm.getCawangan(), ku));
                    p.setCawangan(hm.getCawangan());
                } else {
                    p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), ku));
                    p.setCawangan(pengguna.getKodCawangan());
                }

                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);

                if (conf.getProperty("kodNegeri").equals("05")) { //update stage_id_sblm di table mohon
                    p.setIdStagePermohonanSebelum(stageId);
                }

                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        p.setPenyerahNama("Unit Penguatkuasaan " + pengguna.getKodCawangan().getName());
                        KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                        caraPermohonan.setKod("UD"); //Unit dalaman
                        p.setCaraPermohonan(caraPermohonan);
                    } else {
                        p.setPenyerahNama(pengguna.getKodCawangan().getName());
                    }

//                    p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                    p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                    if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                        p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                    }

                    if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                        p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                    }
                    p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                    p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());

                }
//            
                p.setInfoAudit(ia);
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                permohonanDAO.save(p);

                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                        if (ku.getKod().equalsIgnoreCase("TTWKP") || ku.getKod().equalsIgnoreCase("TTWLM") || ku.getKod().equalsIgnoreCase("TTWLB")) {
                            //Hafifi : masukkan maklumat mohon_pihak dan pihak
                            List<Pemohon> pemohonList = enforcementService.findPemohonByIdPermohonan(permohonan.getIdPermohonan());
                            List<PermohonanPihak> mohonPihak = enforcementService.findMohonPihakByIdPermohonan(permohonan.getIdPermohonan());

                            if (pemohonList != null) {
                                if (pemohonList.size() > 0) {
                                    for (Pemohon pemohon : pemohonList) {
                                        Pemohon newPemohon = new Pemohon();
                                        newPemohon.setCawangan(pemohon.getCawangan());
                                        newPemohon.setPermohonan(p);
                                        newPemohon.setPihak(pemohon.getPihak());
                                        newPemohon.setHakmilik(pemohon.getHakmilik());
                                        newPemohon.setJenis(pemohon.getJenis());
                                        newPemohon.setSyerPembilang(pemohon.getSyerPembilang());
                                        newPemohon.setSyerPenyebut(pemohon.getSyerPenyebut());
                                        newPemohon.setNama(pemohon.getNama());
                                        newPemohon.setJenisPengenalan(pemohon.getJenisPengenalan());
                                        newPemohon.setNoPengenalan(pemohon.getNoPengenalan());
                                        newPemohon.setWargaNegara(pemohon.getWargaNegara());
                                        newPemohon.setAlamat(pemohon.getAlamat());
                                        newPemohon.setAlamatSurat(pemohon.getAlamatSurat());
                                        newPemohon.setInfoAudit(ia);
                                        enforcementService.simpanPemohon(newPemohon);
                                    }
                                }
                            }
                            if (mohonPihak != null) {
                                if (mohonPihak.size() > 0) {
                                    for (PermohonanPihak permohonanPihak : mohonPihak) {
                                        PermohonanPihak newPermohonanPihak = new PermohonanPihak();
                                        newPermohonanPihak.setPermohonan(p);
                                        newPermohonanPihak.setCawangan(permohonanPihak.getCawangan());
                                        newPermohonanPihak.setPihak(permohonanPihak.getPihak());
                                        newPermohonanPihak.setJenis(permohonanPihak.getJenis());
                                        newPermohonanPihak.setHakmilik(permohonanPihak.getHakmilik());
                                        newPermohonanPihak.setSyerPembilang(permohonanPihak.getSyerPembilang());
                                        newPermohonanPihak.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                                        newPermohonanPihak.setUmur(permohonanPihak.getUmur());
                                        newPermohonanPihak.setPendapatan(permohonanPihak.getPendapatan());
                                        newPermohonanPihak.setKaitan(permohonanPihak.getKaitan());
                                        newPermohonanPihak.setTangungan(permohonanPihak.getTangungan());
                                        newPermohonanPihak.setNama(permohonanPihak.getNama());
                                        newPermohonanPihak.setJenisPengenalan(permohonanPihak.getJenisPengenalan());
                                        newPermohonanPihak.setNoPengenalan(permohonanPihak.getNoPengenalan());
                                        newPermohonanPihak.setWargaNegara(permohonanPihak.getWargaNegara());
                                        newPermohonanPihak.setAlamat(permohonanPihak.getAlamat());
                                        newPermohonanPihak.setAlamatSurat(permohonanPihak.getAlamatSurat());
                                        newPermohonanPihak.setInfoAudit(ia);
                                        enforcementService.simpanPermohonanPihak(newPermohonanPihak);
                                    }
                                }
                            }
                        }
                    }
                }

                String id = hm.getIdHakmilik();
                Hakmilik hmb = new Hakmilik();
                hmb = hakmilikDAO.findById(id);
                if (hmb == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }

                for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
                    BigDecimal luas = mh.getLuasTerlibat();
                    KodUOM kodUOM = new KodUOM();
                    kodUOM.setKod(mh.getKodUnitLuas().getKod());
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hpa.setInfoAudit(ia);
                    hpa.setPermohonan(p);

                    if (conf.getProperty("kodNegeri").equals("05")) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                            if (stageId.equalsIgnoreCase("keputusan2")) {
                                //Hafifi : 15/4/2014 : dapatkan luas terhakis dari syor laporan tanah
                                LaporanTanah laporanTanah = enforceService.findByIDMohon(permohonan.getIdPermohonan());
                                if (laporanTanah != null) {
                                    hpa.setLuasTerlibat(laporanTanah.getPerengganLuasSebelum());
                                } else {
                                    hpa.setLuasTerlibat(luas);
                                }
                            } else {
                                hpa.setLuasTerlibat(luas);
                            }
                        } else {
                            hpa.setLuasTerlibat(luas);
                        }
                    } else {
                        hpa.setLuasTerlibat(luas);
                    }

                    hpa.setKodUnitLuas(kodUOM);
                    hakmilikPermohonanDAO.save(hpa);
                }

                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());

                Permohonan mohon = p;
//                mhservice.janaHakmilikBaru(hm, ia, p, pengguna, 1);

                List<HakmilikPermohonan> hmp = permohonan.getSenaraiHakmilik();

//                mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hmb, ia, mohon, pengguna, 1, null, null);
                LOG.info(permohonan);


                try {

                    //to get no warta from laporan tanah
                    String[] tname = {"permohonan"};
                    Object[] model = {permohonan};

                    List<PermohonanRujukanLuar> listRujukanLuar;
                    listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);
                    permohonanRujLuar = new PermohonanRujukanLuar();


                    if (!(listRujukanLuar.isEmpty())) {
                        for (int i = 0; i < listRujukanLuar.size(); i++) {
                            PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                            rujL = listRujukanLuar.get(i);
                            if (rujL.getKodDokumenRujukan() != null) {
                                if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                                    permohonanRujLuar = listRujukanLuar.get(i);
                                    LOG.info("No warta : " + permohonanRujLuar.getNoRujukan());
                                }
                            }

                            if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
                                String kodRuj = "";

                                if (ku.getKod().equalsIgnoreCase("N7A")) {
                                    kodRuj = "W7A";
                                } else if (ku.getKod().equalsIgnoreCase("N7B")) {
                                    kodRuj = "W7B";
                                } else if (ku.getKod().equalsIgnoreCase("N8A")) {
                                    kodRuj = "W8A";
                                }

                                if (rujL.getKodRujukan().getKod().equals(kodRuj)) {
                                    permohonanRujLuar = listRujukanLuar.get(i);
                                }
                            }
                        }
                    } else {
                        permohonanRujLuar = null;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                KodRujukan kodRujuk = kodRujukanDAO.findById("KW");
                KodPerintah kodPerintah = kodPerintahDAO.findById("PD");
                PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setCawangan(mohon.getCawangan());
                permohonanRujukanLuar.setPermohonan(mohon);
                permohonanRujukanLuar.setInfoAudit(ia);

                if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
                    //Hafifi 05/09/2013 : set tarikh kuatkuasa, sampai dan tarikh rujukan kalau ada warta. kalau xda warta, set tarikh disampai ikut tarikh OKS terima notis
                    if (permohonanRujLuar != null) {
                        permohonanRujukanLuar.setNoRujukan(permohonanRujLuar.getNoRujukan());
                        permohonanRujukanLuar.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                        permohonanRujukanLuar.setTarikhKuatkuasa(permohonanRujLuar.getTarikhKuatkuasa());
                    } else {
                        Notis notis = null;

                        if (ku.getKod().equalsIgnoreCase("N7A")) {
                            notis = enforcementService.findByIdNotis2(permohonan.getIdPermohonan(), "7A");
                        } else if (ku.getKod().equalsIgnoreCase("N7B")) {
                            notis = enforcementService.findByIdNotis2(permohonan.getIdPermohonan(), "7B");
                        }

                        if (notis != null) {
                            permohonanRujukanLuar.setTarikhDisampai(notis.getTarikhTerima());
                        }
                    }
                } else {
                    permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                    if (permohonanRujLuar.getNoRujukan() != null) {
                        permohonanRujukanLuar.setNoRujukan(permohonanRujLuar.getNoRujukan());
                    }// to do : kod N7B & PSPBN set tarikh kkuasa & tarikh sampai as current date - temp purpose
                    permohonanRujukanLuar.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
                }

                permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
                permohonanRujukanLuar.setKodRujukan(kodRujuk);
                permohonanRujukanLuar.setKodPerintah(kodPerintah);
                if (hmb != null) {
                    permohonanRujukanLuar.setHakmilik(hmb);
                }
                strService.SimpanMohonRujukLuar(permohonanRujukanLuar);

            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            return false;
        }
        return true;
    }

    //Hafifi 23/4/2014 : Generate ID Perserahan MH unk NS
    public boolean generateIdPerserahanWorkflowMH(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageId) {

        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("TEST_" + tarikh);
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            Hakmilik hm = senaraiHakmilik.get(0);

            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), hm.getCawangan(), ku));
            p.setCawangan(hm.getCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdStagePermohonanSebelum(stageId);

            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                p.setPenyerahNama("Unit Penguatkuasaan " + pengguna.getKodCawangan().getName());
                KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                caraPermohonan.setKod("UD"); //Unit dalaman
                p.setCaraPermohonan(caraPermohonan);
                p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                }

                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                }
                p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());

            }
//            
            p.setInfoAudit(ia);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            permohonanDAO.save(p);

            Hakmilik hmBaru = this.janaHakmilikBaru(p, pengguna, permohonan, "");

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());


            //Hafifi 23/4/2014 : mohon_hakmilik
            HakmilikPermohonan hpa = new HakmilikPermohonan();
            hpa.setHakmilik(hmBaru);
            hpa.setPermohonan(p);
            hpa.setLuasTerlibat(hmBaru.getLuas());
            hpa.setKodUnitLuas(hmBaru.getKodUnitLuas());
            hpa.setLot(hmBaru.getLot());
            hpa.setNoLot(hmBaru.getNoLot());
            hpa.setKodHakmilik(hmBaru.getKodHakmilik());
            hpa.setInfoAudit(ia);
            hakmilikPermohonanDAO.save(hpa);

            //Hafifi 23/4/2014 : mohon_ruj_luar
            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setCawangan(p.getCawangan());
            permohonanRujukanLuar.setPermohonan(p);
            permohonanRujukanLuar.setHakmilik(hmBaru);
            KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
            KodJabatan kodJabatan = kodJabatanDAO.findById("24");
            permohonanRujukanLuar.setJabatan(kodJabatan);
            permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setTarikhRujukan(permohonan.getTarikhKeputusan());
//            permohonanRujukanLuar.setNoRujukan(NoRuj); // no msyuarat
//            permohonanRujukanLuar.setTarikhRujukan(date);// tarikh mesyuarat
//            permohonanRujukanLuar.setNoSidang(NoSidang); // yang meluluskan
//            permohonanRujukanLuar.setTarikhSidang(lulusan); //tarikh kelulusan
//            permohonanRujukanLuar.setNamaSidang(NamaSidang); //nama sidang
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            return false;
        }
        return true;
    }

    public Hakmilik janaHakmilikBaru(Permohonan mohonREG, Pengguna pengguna, Permohonan mohonENF, String sts) throws Exception {
        String error = "";

        try {
            PelanGIS pelanGIS = enforcementService.findPelanB1ByMohon(mohonENF.getIdPermohonan());
            Hakmilik hakmilikLama = mohonENF.getSenaraiHakmilik().get(0).getHakmilik(); //Hafifi 23/4/2014 : di NS, cuma melibatkan satu hakmilik sahaja

            if (pelanGIS != null) {
                InfoAudit ia = new InfoAudit();
                Date now = new Date();
                String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                Hakmilik hakmilikBaru = new Hakmilik();

                mohonUkur = new PermohonanUkur();
                mohonUkur = enforcementService.findPermohonanUkurByIdPermohonan(mohonENF.getIdPermohonan());
                hakmilikBaru.setNoPu(mohonUkur.getNoPermohonanUkur());
                hakmilikBaru.setNoFail(mohonENF.getIdPermohonan());
                hakmilikBaru.setKodHakmilik(hakmilikLama.getKodHakmilik());
                hakmilikBaru.setNoPelan(pelanGIS.getNoPelanAkui());
                hakmilikBaru.setLot(hakmilikLama.getLot());
                hakmilikBaru.setNoLot(pelanGIS.getPelanGISPK().getNoLot());
                hakmilikBaru.setLuas(pelanGIS.getLuas());

                KodUOM kodUnitLuas = kodUOMDAO.findById(pelanGIS.getUnitUkur());
                hakmilikBaru.setKodUnitLuas(kodUnitLuas);

                hakmilikBaru.setCawangan(hakmilikLama.getCawangan());
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hakmilikLama.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(hakmilikLama.getSeksyen());
                hakmilikBaru.setLokasi(hakmilikLama.getLokasi());
//                hakmilikBaru.setKumpulan(hakmilikLama.getKumpulan());
//                hakmilikBaru.setIdHakmilikInduk(hakmilikLama.getIdHakmilikInduk());
//                hakmilikBaru.setNoBangunan(hakmilikLama.getNoBangunan());
//                hakmilikBaru.setNoTingkat(hakmilikLama.getNoTingkat());
//                hakmilikBaru.setNoPetak(hakmilikLama.getNoPetak());
                hakmilikBaru.setKategoriTanah(hakmilikLama.getKategoriTanah());
                hakmilikBaru.setKodTanah(hakmilikLama.getKodTanah());
                hakmilikBaru.setMaklumatAtasTanah(hakmilikLama.getMaklumatAtasTanah());
                hakmilikBaru.setSyaratNyata(hakmilikLama.getSyaratNyata());
                hakmilikBaru.setKegunaanTanah(hakmilikLama.getKegunaanTanah());
                hakmilikBaru.setSekatanKepentingan(hakmilikLama.getSekatanKepentingan());
                //TODO:Hafifi 23/4/2014 : setkan trh_daftar dan kod_sts_hakmilik
                hakmilikBaru.setPegangan(hakmilikLama.getPegangan());
                hakmilikBaru.setTempohPegangan(hakmilikLama.getTempohPegangan());
//                hakmilikBaru.setTarikhLuput(hakmilikLama.getTarikhLuput());
                hakmilikBaru.setRizab(hakmilikLama.getRizab());
                hakmilikBaru.setLotBumiputera(hakmilikLama.getLotBumiputera());
                hakmilikBaru.setPbt(hakmilikLama.getPbt());
                hakmilikBaru.setMilikPersekutuan(hakmilikLama.getMilikPersekutuan());
//                hakmilikBaru.setLuasLama(hakmilikLama.getLuasLama());
//                hakmilikBaru.setKodUnitLuasLama(hakmilikLama.getKodUnitLuasLama());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho());
                //TODO:Hafifi 23/4/2014 : setkan kod_cukai dan cukai
//                Integer intBpm = 0;
//                if (hakmilikBaru.getBandarPekanMukim() != null) {
//                    intBpm = hakmilikBaru.getBandarPekanMukim().getKod();
//                }
//                Integer intRizab = 0;
//                if (hakmilikBaru.getRizab() != null) {
//                    intRizab = hakmilikBaru.getRizab().getKod();
//                }
//                hakmilikBaru.setCukai(calcTax.calculate(hakmilikBaru.getKegunaanTanah().getKod(), intBpm.toString(), hakmilikBaru.getKodUnitLuas().getKod(), hakmilikBaru.getLuas(), hakmilikBaru, intRizab.toString()));
//                hakmilikBaru.setCukaiSebenar(hakmilikBaru.getCukai());
                //TODO:Hafifi 23/4/2014 : setkan kod_sumber
                hakmilikBaru.setNoVersiDhde(0); //default new hakmilik
                hakmilikBaru.setNoVersiDhke(0);
//                hakmilikBaru.setUPI(hakmilikLama.getUPI());
                hakmilikBaru.setRizabNoWarta(hakmilikLama.getRizabNoWarta());
                hakmilikBaru.setRizabKawasan(hakmilikLama.getRizabKawasan());
                hakmilikBaru.setRizabTarikhWarta(hakmilikLama.getRizabTarikhWarta());
                hakmilikBaru.setPbtNoWarta(hakmilikLama.getPbtNoWarta());
                hakmilikBaru.setPbtKawasan(hakmilikLama.getPbtKawasan());
                hakmilikBaru.setPbtTarikhWarta(hakmilikLama.getPbtTarikhWarta());
                hakmilikBaru.setGsaNoWarta(hakmilikLama.getGsaNoWarta());
                hakmilikBaru.setGsaKawasan(hakmilikLama.getGsaKawasan());
                hakmilikBaru.setGsaTarikhWarta(hakmilikLama.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(hakmilikLama.getTarikhDaftarAsal());
//                hakmilikBaru.setUnitSyer(hakmilikLama.getUnitSyer());
//                hakmilikBaru.setBadanPengurusan(hakmilikLama.getBadanPengurusan());

//                KodStatusHakmilik ksh = new KodStatusHakmilik();
//                ksh.setKod("T");
//                hakmilikBaru.setKodStatusHakmilik(ksh);              
//                hakmilikBaru.setNoBukuDaftarStrata(hakmilikLama.getNoBukuDaftarStrata());
//                hakmilikBaru.setNoSkim(hakmilikLama.getNoSkim());
//                hakmilikBaru.setJumlahUnitSyer(hakmilikLama.getJumlahUnitSyer());
//                hakmilikBaru.setKodKategoriBangunan(hakmilikLama.getKodKategoriBangunan());
//                hakmilikBaru.setKodKegunaanBangunan(hakmilikLama.getKodKegunaanBangunan());

                return janaHakmilikService.returnJanaHakmilikBaruFromHakmilikTerlibat(hakmilikLama, hakmilikBaru, ia, mohonREG, pengguna, null);
            } else {
                throw new Exception("Pelan GIS tidak wujud");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Permohonan genWorkflowIdPerserahan1(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        Permohonan permohonanBaru = new Permohonan();
        try {
            generateIdPerserahanWorkflow1(ku, pengguna, senaraiHakmilik, permohonan, null);
            permohonanBaru =
                    permohonan;
            LOG.debug("idPermohonan :" + permohonanBaru.getIdPermohonan());
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return permohonanBaru;
    }

    public boolean generateIdPerserahan(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, List<Pemohon> listPemohon, List<PermohonanPihak> permohonanPihakList) {

        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                p.setPenyerahNama(pengguna.getKodCawangan().getName());
//                    p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                }

                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                }
                p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());

            }
//            
            p.setInfoAudit(ia);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            if (permohonan.getKeputusan() != null) {
                p.setKeputusan(kodKeputusanDAO.findById(permohonan.getKeputusan().getKod()));
            }
            permohonanDAO.save(p);

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
            Permohonan mohon = p;

            try {

                //to get no warta from laporan tanah
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};

                List<PermohonanRujukanLuar> listRujukanLuar;
                listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);


                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                        rujL = listRujukanLuar.get(i);
                        if (rujL.getKodDokumenRujukan() != null) {
                            if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                                permohonanRujLuar = listRujukanLuar.get(i);
                                LOG.info("No warta : " + permohonanRujLuar.getNoRujukan());
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            KodRujukan kodRujuk = kodRujukanDAO.findById("KW");
            KodPerintah kodPerintah = kodPerintahDAO.findById("PD");
            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setCawangan(mohon.getCawangan());
            permohonanRujukanLuar.setPermohonan(mohon);
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            if (permohonanRujLuar != null) {
                if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
                    permohonanRujukanLuar.setNoRujukan(permohonanRujLuar.getNoRujukan());
                }
                // to do : kod N7B & PSPBN set tarikh kkuasa & tarikh sampai as current date - temp purpose
                permohonanRujukanLuar.setTarikhSidang(permohonanRujLuar.getTarikhSidang());
            }
            permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
            permohonanRujukanLuar.setKodRujukan(kodRujuk);
            permohonanRujukanLuar.setKodPerintah(kodPerintah);
            permohonanRujukanLuar.setHakmilik(null);
            strService.SimpanMohonRujukLuar(permohonanRujukanLuar);

            LOG.info("::::::::::::::::: size senarai hakmilik while generating id pendaftaran :" + senaraiHakmilik.size());
            int i = 1;
            for (Hakmilik hm : senaraiHakmilik) {
                LOG.info("Bil to create mohon hakmilik for pendaftaran ::: " + i);
                String id = hm.getIdHakmilik();
                Hakmilik hmb = new Hakmilik();
                hmb = hakmilikDAO.findById(id);
                if (hmb == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }

//                for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
                BigDecimal luas = hm.getLuas();
                KodUOM kodUOM = new KodUOM();
                kodUOM.setKod(hm.getKodUnitLuas().getKod());
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hpa.setLuasTerlibat(luas);
                hpa.setKodUnitLuas(kodUOM);
                hakmilikPermohonanDAO.save(hpa);
                i++;
//                }
            }

            //for seksyen 351 & 352
            if (conf.getProperty("kodNegeri").equalsIgnoreCase("04")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                    if (!listPemohon.isEmpty()) {
                        for (Pemohon pMohon : listPemohon) {
                            Pemohon pemohon = new Pemohon();
                            pemohon.setCawangan(new KodCawangan());
                            pemohon.setPermohonan(p);
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            pemohon.setSyerPembilang(pMohon.getSyerPembilang());
                            pemohon.setSyerPenyebut(pMohon.getSyerPenyebut());
                            pemohon.setCawangan(pengguna.getKodCawangan());
                            pemohon.setPihak(pMohon.getPihak());
                            pemohon.setNama(pMohon.getNama());
                            pemohon.setNoPengenalan(pMohon.getNoPengenalan());
                            pemohon.setInfoAudit(ia);
                            pemohon.setDalamanNilai1(pMohon.getDalamanNilai1());
                            pemohon.setDalamanNilai2(pMohon.getDalamanNilai2());
                            pemohon.setJenis(pMohon.getJenis());
                            pemohon.setWargaNegara(pMohon.getWargaNegara());

                            Alamat alamat = new Alamat();
                            if (pMohon.getAlamat() != null) {
                                alamat.setAlamat1(pMohon.getAlamat().getAlamat1());
                                alamat.setAlamat2(pMohon.getAlamat().getAlamat2());
                                alamat.setAlamat3(pMohon.getAlamat().getAlamat3());
                                alamat.setAlamat4(pMohon.getAlamat().getAlamat4());
                                alamat.setPoskod(pMohon.getAlamat().getPoskod());
                                alamat.setNegeri(pMohon.getAlamat().getNegeri());
                            }


                            AlamatSurat alamatSurat = new AlamatSurat();
                            if (!pMohon.getPihak().getSenaraiAlamatTamb().isEmpty()) {
                                alamatSurat.setAlamatSurat1(pMohon.getPihak().getSuratAlamat1());
                                alamatSurat.setAlamatSurat2(pMohon.getPihak().getSuratAlamat2());
                                alamatSurat.setAlamatSurat3(pMohon.getPihak().getSuratAlamat3());
                                alamatSurat.setAlamatSurat4(pMohon.getPihak().getSuratAlamat4());
                                alamatSurat.setPoskodSurat(pMohon.getPihak().getSuratPoskod());
                                alamatSurat.setNegeriSurat(pMohon.getPihak().getSuratNegeri());
                            }

                            pemohon.setAlamat(alamat);
                            pemohon.setAlamatSurat(alamatSurat);
                            enforcementService.simpanMaklumatPihak(pemohon);
                        }
                    }

                    if (!permohonanPihakList.isEmpty()) {
                        for (PermohonanPihak pp : permohonanPihakList) {
                            PermohonanPihak pPihak = new PermohonanPihak();
                            List<PihakAlamatTamb> senaraiAlamatTamb = pp.getPihak().getSenaraiAlamatTamb();
                            PihakAlamatTamb pihakAlamatTamb = senaraiAlamatTamb.get(0);
                            pPihak.setPermohonan(p);
                            pPihak.setHakmilik(pp.getHakmilik());
                            pPihak.setCawangan(p.getCawangan());
                            pPihak.setPihak(pp.getPihak());
                            pPihak.setJenis(pp.getJenis());
                            pPihak.setDalamanNilai1(pp.getDalamanNilai1());
                            pPihak.setDalamanNilai2(pp.getDalamanNilai2());
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new java.util.Date());
                            pPihak.setInfoAudit(ia);
                            if (pp != null) {
                                pPihak.setKaitan(pp.getKaitan() != null ? pp.getKaitan() : "");
                                pPihak.setUmur(pp.getUmur() != null ? pp.getUmur() : 0);
                                pPihak.setStatusKahwin(pp.getStatusKahwin() != null ? pp.getStatusKahwin() : "");
                                pPihak.setPekerjaan(pp.getPekerjaan() != null ? pp.getPekerjaan() : "");
                                if (pp.getSyerPembilang() != null) {
                                    pPihak.setSyerPembilang(pp.getSyerPembilang());
                                }
                                if (pp.getSyerPenyebut() != null) {
                                    pPihak.setSyerPenyebut(pp.getSyerPenyebut());
                                }
                            }

                            pPihak.setJenisPengenalan(pp.getPihak().getJenisPengenalan());
                            pPihak.setWargaNegara(pp.getPihak().getWargaNegara());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pp.getPihak().getAlamat1());
                            alamat.setAlamat2(pp.getPihak().getAlamat2());
                            alamat.setAlamat3(pp.getPihak().getAlamat3());
                            alamat.setAlamat4(pp.getPihak().getAlamat4());
                            alamat.setPoskod(pp.getPihak().getPoskod());
                            alamat.setNegeri(pp.getPihak().getNegeri());
                            pPihak.setAlamat(alamat);

                            if (pihakAlamatTamb != null) {
                                AlamatSurat alamatSurat = new AlamatSurat();
                                alamatSurat.setAlamatSurat1(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga1()) ? pihakAlamatTamb.getAlamatKetiga1() : "");
                                alamatSurat.setAlamatSurat2(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga2()) ? pihakAlamatTamb.getAlamatKetiga2() : "");
                                alamatSurat.setAlamatSurat3(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga3()) ? pihakAlamatTamb.getAlamatKetiga3() : "");
                                alamatSurat.setAlamatSurat4(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetiga4()) ? pihakAlamatTamb.getAlamatKetiga4() : "");
                                alamatSurat.setPoskodSurat(StringUtils.isNotBlank(pihakAlamatTamb.getAlamatKetigaPoskod()) ? pihakAlamatTamb.getAlamatKetigaPoskod() : "");
                                alamatSurat.setNegeriSurat(pihakAlamatTamb.getAlamatKetigaNegeri() != null ? pihakAlamatTamb.getAlamatKetigaNegeri() : null);
                                pPihak.setAlamatSurat(alamatSurat);
                            }
                            enforcementService.simpanMaklumatPermohonanPihak(pPihak);
                        }
                    }

                }
            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            return false;
        }
        return true;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }
}

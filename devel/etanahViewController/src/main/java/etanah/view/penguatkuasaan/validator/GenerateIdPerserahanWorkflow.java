/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodRujukan;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.EnforceService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.daftar.MohonHakmilikService;
import etanah.service.daftar.PembetulanService;
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
public class GenerateIdPerserahanWorkflow {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodRujukanDAO kodRujukanDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PembetulanService pService;
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
    StrataPtService strService;
    @Inject
    MohonHakmilikService mhservice;
    @Inject
    EnforceService enforceService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RegService regService;
    @Inject
    etanah.sequence.GeneratorIdHakmilik idHakmilikGenerator;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    public String idFail;
    public String namaBPM;
    public String kodNegeri;
    public String idPermohonanPendaftaranBaru;
    public List<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    public Long idMHBaru;
    private Permohonan permohonanPendaftaran = new Permohonan();
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageId, List<HakmilikPermohonan> listHakmilikPermohonan) {

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
        String id = null;

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

                if (hm.getKodHakmilik() != null) {
                    if (hm.getKodHakmilik().getKod().equalsIgnoreCase("HSM") || hm.getKodHakmilik().getKod().equalsIgnoreCase("HST")) {
                         LOG.info(":::: kod hakmilik HSM OR HST");
                        ku = kodUrusanDAO.findById("HSSTA");
                    }
                }

                LOG.info(":::: kod urusan :" + ku.getKod());
                Permohonan p = new Permohonan();
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                    p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                    if (permohonan.getPenyerahAlamat3() != null) {
                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                    }

                    if (permohonan.getPenyerahAlamat4() != null) {
                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                    }
                    p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                    p.setPenyerahNegeri(permohonan.getPenyerahNegeri());

                }
                p.setInfoAudit(ia);
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                permohonanDAO.save(p);

                id = hm.getIdHakmilik();
                Hakmilik hmb = new Hakmilik();
                hmb = hakmilikDAO.findById(id);
                if (hmb == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                if (!ku.getKod().equalsIgnoreCase("HKSTA") && !ku.getKod().equalsIgnoreCase("HSSTA")) {
                    for (HakmilikPermohonan mh : listHakmilikPermohonan) {
                        BigDecimal luas = mh.getLuasTerlibat();
                        KodUOM kodUOM = new KodUOM();
                        kodUOM.setKod(mh.getKodUnitLuas().getKod());
                        HakmilikPermohonan hpa = new HakmilikPermohonan();
                        hpa.setHakmilik(hm);
                        hpa.setInfoAudit(ia);
                        hpa.setPermohonan(p);
                        hpa.setLuasTerlibat(luas);
                        hpa.setKodUnitLuas(kodUOM);
                        hakmilikPermohonanDAO.save(hpa);
                        idMHBaru = hpa.getId();
                    }
                }

                String idWorkflow = "";

                if (ku.getKod().equalsIgnoreCase("HKSTA") || ku.getKod().equalsIgnoreCase("HSSTA")) {
                    idWorkflow = p.getKodUrusan().getIdWorkflowIntegration();
                    System.out.println(":::start generate new id hakmilik for " + ku.getKod());
                    janaHakmilikBaru(hm, ia, p, pengguna, 1, permohonan, listHakmilikPermohonan);
                } else {
                    idWorkflow = p.getKodUrusan().getIdWorkflowIntegration();
                }

                System.out.println("::::id workflow before initiate id pendaftaran : " + idWorkflow);

                WorkFlowService.initiateTask(idWorkflow,
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                Permohonan mohon = p;
                idPermohonanPendaftaranBaru = p.getIdPermohonan();

//                mhservice.janaHakmilikBaru(hm, ia, p, pengguna, 1);

                List<HakmilikPermohonan> hmp = permohonan.getSenaraiHakmilik();

//                mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hmb, ia, mohon, pengguna, 1, null, null);
                LOG.info(permohonan);


                try {

                    //to get no warta from laporan tanah
                    String[] tname = {"permohonan"};
                    Object[] model = {permohonan};

                    List<PermohonanRujukanLuar> listRujukanLuar;
//                    listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);

                    String idMohon = permohonan.getIdPermohonan();
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                        if (stageId.equalsIgnoreCase("arah_daftar_hmsambungan") || stageId.equalsIgnoreCase("kpsn_siasatan")) {
                            if (permohonan.getPermohonanSebelum() != null) {
                                //1) After create new IP
                                idMohon = permohonan.getPermohonanSebelum().getIdPermohonan();
                                LOG.info(":::: id permohonan sebelum exist ::::" + idMohon);
                            }
                        }

                    }
                    listRujukanLuar = enforceService.findPermohonanRujukanLuar(idMohon);
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

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                KodRujukan kodRujuk = kodRujukanDAO.findById("KW");
                PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setCawangan(mohon.getCawangan());
                permohonanRujukanLuar.setPermohonan(mohon);
                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                if (permohonanRujLuar.getNoRujukan() != null) {
                    permohonanRujukanLuar.setNoRujukan(permohonanRujLuar.getNoRujukan());
                }
                permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
                permohonanRujukanLuar.setKodRujukan(kodRujuk);
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


        kodNegeri = conf.getProperty("kodNegeri");
//        comment for a while
//        if (kodNegeri.equalsIgnoreCase("04")) {
//            if (ku.getKod().equalsIgnoreCase("HKSTA")) {
//                if (StringUtils.isNotBlank(idPermohonanPendaftaranBaru)) {
//                    permohonanPendaftaran = permohonanDAO.findById(idPermohonanPendaftaranBaru);
//                    if (permohonanPendaftaran != null) {
//                        listMohonHakmilikBaru = enforceService.findHakmilikPermohonan(permohonanPendaftaran.getIdPermohonan());
//                    }
//
//                    if (!listMohonHakmilikBaru.isEmpty()) {
//                        idMHBaru = listMohonHakmilikBaru.get(0).getId();
//                    }
//                }
//
//                try {
//                    List<HakmilikSebelumPermohonan> listHsp = new ArrayList<HakmilikSebelumPermohonan>();
//
//                    for (Hakmilik h : senaraiHakmilik) {
//                        LOG.info("::: id hakmilik lama :" + h.getIdHakmilik());
//                        LOG.info("::: id MH baru (PEND) :" + idMHBaru);
//                        if (idMHBaru != null) {
//                            listHsp = enforcementService.findHakmilikSebelum(h.getIdHakmilik(), idMHBaru);
//                        }
//                    }
//
//                    LOG.debug("::::::::::::::listHsp :" + listHsp.size());
//                    if (!listHsp.isEmpty()) {
//                        /*INSERT INTO HAKMILIK ASAL*/
//                        LOG.debug("::::::::::::::INSERT INTO HakmilikAsal");
//
//                        Hakmilik h = new Hakmilik();
//                        h = enforcementService.findHakmilikAsal(listHsp.get(0).getHakmilik().getIdHakmilik());
//
//                        HakmilikAsal ha = new HakmilikAsal();
//                        ha.setInfoAudit(ia);
//                        ha.setHakmilik(listHsp.get(0).getHakmilik());
//                        if (h != null) {
//                            LOG.info("::::::::: id hakmilik asal : " + h.getIdHakmilik());
//                            ha.setHakmilikAsal(sejarahHakmilikDAO.findById(h.getIdHakmilik()));
//                        }
//                        enforcementService.simpanHakmilikAsal(ha);
//                    }
//
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }

        return false;
    }

    public Permohonan genWorkflowIdPerserahan(
            KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, String stageId, List<HakmilikPermohonan> listHakmilikPermohonan) {
        Permohonan permohonanBaru = new Permohonan();
        try {
            generateIdPerserahanWorkflow(ku, pengguna, senaraiHakmilik, permohonan, stageId, listHakmilikPermohonan);
            permohonanBaru =
                    permohonan;
            LOG.debug("idPermohonan :" + permohonanBaru.getIdPermohonan());
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return permohonanBaru;
    }

    public void janaHakmilikBaru(Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, Permohonan permohonanEnf, List<HakmilikPermohonan> listHakmilikPermohonan) {
        kodNegeri = conf.getProperty("kodNegeri");

        List<HakmilikPihakBerkepentingan> hakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();
        LOG.info("size hakmilik permohonan ENF: " + listHakmilikPermohonan.size());

        BigDecimal luasBaru = BigDecimal.ZERO;
        String kodLotBaru = null;
        String noLotBaru = null;
        KodUOM kodUom = new KodUOM();
        if (listHakmilikPermohonan.get(0).getLuasTerlibat() != null) {
            luasBaru = listHakmilikPermohonan.get(0).getLuasTerlibat();
            kodUom = listHakmilikPermohonan.get(0).getKodUnitLuas();
            kodLotBaru = listHakmilikPermohonan.get(0).getLot().getKod();
            noLotBaru = listHakmilikPermohonan.get(0).getNoLot();
        }

        LOG.info("luasBaru : " + luasBaru);

        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilik.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilik.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilik);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                hakmilikBaru.setNoFail(idFail);
                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                hakmilikBaru.setDaerah(hakmilik.getDaerah());
                hakmilikBaru.setBandarPekanMukim(kbpm);
                hakmilikBaru.setSeksyen(null);
                KodHakmilik kodHakmilik = new KodHakmilik();
                kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
                hakmilikBaru.setKodHakmilik(kodHakmilik);
                KodLot kl = new KodLot();
                kl.setKod(kodLotBaru);
                hakmilikBaru.setLot(kl);
                hakmilikBaru.setNoLot(noLotBaru);
                KodKategoriTanah kkt = new KodKategoriTanah();
                kkt.setKod("1");
                hakmilikBaru.setKategoriTanah(kkt);
//                KodKegunaanTanah kgt = new KodKegunaanTanah();
//                kgt.setKod("BO1");
//                kgt.setKategoriTanah(kkt);
                String kodgunaguna = "B01";
                hakmilikBaru.setKegunaanTanah(regService.cariKegunaanTanah(kodgunaguna, kkt.getKod()));
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
                KodUOM kuo = new KodUOM();
                kuo.setKod("M");
                hakmilikBaru.setKodUnitLuas(kodUom);
                hakmilikBaru.setLuas(luasBaru);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik baru:" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setSyaratNyata(hakmilik.getSyaratNyata());
                hakmilikBaru.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
                hakmilikBaru.setNoLitho(hakmilik.getNoLitho());
                hakmilikBaru.setNoPelan(hakmilik.getNoPelan());
                hakmilikBaru.setInfoAudit(ia);
                enforcementService.simpanHakmilik(hakmilikBaru);

                hakmilikPihak = hakmilik.getSenaraiPihakBerkepentingan();

                for (HakmilikPihakBerkepentingan hpb : hakmilikPihak) {
                    HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan = new HakmilikPihakBerkepentingan();
                    hakmilikPihakBerkepentingan.setHakmilik(hakmilikBaru);
                    hakmilikPihakBerkepentingan.setCawangan(p.getCawangan());
                    hakmilikPihakBerkepentingan.setJenis(hpb.getJenis());
                    hakmilikPihakBerkepentingan.setInfoAudit(ia);
                    hakmilikPihakBerkepentingan.setNama(hpb.getPihak().getNama());
                    hakmilikPihakBerkepentingan.setJenisPengenalan(hpb.getPihak().getJenisPengenalan());
                    hakmilikPihakBerkepentingan.setNoPengenalan(hpb.getPihak().getNoPengenalan());
                    hakmilikPihakBerkepentingan.setPihak(hpb.getPihak());
                    hakmilikPihakBerkepentingan.setAktif(hpb.getAktif());
                    hakmilikPihakBerkepentingan.setKaveatAktif(hpb.getKaveatAktif());
                    hakmilikPihakBerkepentingan.setSyerPembilang(hpb.getSyerPembilang());
                    hakmilikPihakBerkepentingan.setSyerPenyebut(hpb.getSyerPenyebut());
                    enforcementService.simpanMohonPihak(hakmilikPihakBerkepentingan);
                    System.out.println("id hpb : " + hakmilikPihakBerkepentingan.getIdHakmilikPihakBerkepentingan());

                }

                /*INSERT INTO MOHON HAKMILIK*/
                LOG.debug("::::::::::::::INSERT INTO HakmilikPermohonan");
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                mohonHakmilikBaru.setLuasTerlibat(luasBaru);
                mohonHakmilikBaru.setKodUnitLuas(kodUom);
                mohonHakmilikBaru.setNoLot(noLotBaru);
                mohonHakmilikBaru.setLot(kl);
                enforcementService.simpanMohonHakmilik(mohonHakmilikBaru);

                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                LOG.debug("::::::::::::::INSERT INTO HakmilikSebelumPermohonan");
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setCawangan(pengguna.getKodCawangan());
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hakmilik.getIdHakmilik());
                hsp.setInfoAudit(ia);
                enforcementService.simpanMohonHakmilikSebelum(hsp);

                /*INSERT INTO HAKMILIK URUSAN*/
                List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
                hakmilikUrusanList = enforcementService.findHakmilikUrusanActiveByIdHakmilik(hakmilik.getIdHakmilik());
                LOG.info("::::::::::::::size hakmilikUrusanList : " + hakmilikUrusanList.size());
                for (HakmilikUrusan hu : hakmilikUrusanList) {

                    HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                    hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                    hakmilikUrusanBaru.setIdPerserahan(p.getIdPermohonan());
                    hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                    hakmilikUrusanBaru.setAktif(hu.getAktif());
                    hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                    hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                    hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                    hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                    hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                    KodDaerah kd = hu.getDaerah();
                    if (kd == null) {
                        kd = hu.getHakmilik().getDaerah();
                    }
                    hakmilikUrusanBaru.setDaerah(kd);
                    hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                    hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                    hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                    hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                    hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                    hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                    hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                    hakmilikUrusanBaru.setInfoAudit(ia);
                    hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                }

            }
        }


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

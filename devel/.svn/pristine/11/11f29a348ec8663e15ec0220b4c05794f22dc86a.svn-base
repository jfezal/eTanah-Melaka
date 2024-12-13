
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.PihakDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanBangunan;
import etanah.model.Pihak;
import etanah.model.Alamat;
import etanah.model.PermohonanPihak;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.strata.BadanPengurusan;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.PermohonanStrata;
import etanah.model.gis.PelanGIS;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.service.common.DokumenService;
import etanah.view.strata.CommonService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;

/**
 *
 * @author Murali
 */
public class terimaPABValidator implements StageListener {

    @Inject
    DokumenDAO dokDAO;
    @Inject
    DokumenService dokServ;
    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    KodCawanganDAO kodCawDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO mohonPihakDAO;
    @Inject
    private TaskDebugService tds;
    private static final Logger LOG = Logger.getLogger(terimaPABValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<Hakmilik> idBdnUrus;
    private BadanPengurusan idBdn;
    private Pihak idPhk;
    private PermohonanPihak mhnPihak = new PermohonanPihak();
    private Permohonan mhnDaftar = new Permohonan();
    private KodCawangan kodCaw = new KodCawangan();
    private KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
    private Hakmilik idhm = new Hakmilik();
    private Permohonan mohonDaft = new Permohonan();
    @Inject
    private etanah.Configuration conf;
    @Inject
    CommonService comm;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;

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

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();

        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        LOG.info("--idHakmilik--" + idHakmilik);
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
        KodUrusan kod = new KodUrusan();

        // mohonFasa = strService.findFasaPermohonanByIdAliran(mohonCEK.getIdPermohonan(), "kemasukan");
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
        if (permohonan != null) {
            KodKeputusan kodkpsn = kodKeputusanDAO.findById("L");
            if (kodkpsn != null) {
                permohonan.setKeputusan(kodkpsn);
            }
            peng = (Pengguna) context.getPengguna();
            permohonan.setKeputusanOleh(peng);
            permohonan.setTarikhKeputusan(new Date());
            strService.updateMohon(permohonan);
        }

        //CHECK FAIL EXIST ---- JUPEM2U//

        String stage = "";
        FasaPermohonan mohonFasa = new FasaPermohonan();
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semaklaporan");
            if (mohonFasa != null) {
                stage = "H1";
            }

            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_sediakertasptg");
            if (mohonFasa != null) {
                stage = "T1";
            }

            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_semakpelan");
            if (mohonFasa != null) {
                stage = "H2";
            }

            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "g_terimapab");
            if (mohonFasa != null) {
                stage = "T2";
            }
            PelanGIS p;
            PermohonanStrata mohonStrata = new PermohonanStrata();
            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                    mohonStrata = strService.findIDnoIDMH(permohonan.getIdPermohonan());
                } else {
                    mohonStrata = strService.findID(permohonan.getIdPermohonan());
                }
            } else {
                mohonStrata = strService.findID(permohonan.getIdPermohonan());
            }

            LOG.info("-----mohonStrata.getNamaSkim()-----" + mohonStrata.getNamaSkim());
            LOG.info("-----stage-----" + stage);
            List<PelanGIS> lp = strService.findListPelanGISPKByNoSkim(mohonStrata.getNamaSkim(), stage);

            if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PHPC")
                    || permohonan.getKodUrusan().getKod().equals("PHPP")) {
                LOG.info("----ID Permohonan----::" + permohonan.getIdPermohonan());
                Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "PAB");
                Dokumen dokPAB = dokDAO.findById(d.getIdDokumen());
                if (dokPAB.getPerihal() != null && dokPAB.getPerihal().equals("2")) {
                    if (!lp.isEmpty()) {
                        if (dokPAB.getNamaFizikal() == null && dokPAB.getCatatanMinit() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Sila muat turun Dokumen dari JUPEM.");
                            return null;
                        } else if (dokPAB.getNamaFizikal() == null || dokPAB.getCatatanMinit() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Sila muat turun Dokumen dari JUPEM.");
                            return null;
                        } else if (dokPAB.getCatatanMinit().equals("2")) {
                            dokPAB.setCatatanMinit(null);
                            dokPAB.setPerihal(null);
                            dokServ.saveOrUpdate(dokPAB);
                        }
                    } else {
                        context.addMessage(permohonan.getIdPermohonan() + " - Arahan: Dokumen dari JUPEM masih belum diterima.");
                        return null;
                    }
                } else {
                    dokPAB.setCatatanMinit(null);
                    dokPAB.setPerihal(null);
                    dokServ.saveOrUpdate(dokPAB);
                }
                Dokumen dk = strService.findDok(permohonan.getIdPermohonan(), "JPP");
                Dokumen dokJPP = dokDAO.findById(dk.getIdDokumen());
                dokJPP.setCatatanMinit(null);
                dokServ.saveOrUpdate(dokJPP);
            }
        }
        //END----//


        Permohonan mohonHTB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "HTB");
        Permohonan mohonHT = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "HT");
        LOG.info("-----mohonHTB-----:" + mohonHTB);
        LOG.info("-----mohonHT-----:" + mohonHT);

        if (mohonHTB == null || mohonHT == null) {

            // Initiate task into HTB for urusan PBBS, PBBD, PBTS, PBS
            if (permohonan.getKodUrusan().getKod().equals("PBBS")
                    || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                LOG.info("-----initiating to HTB-----");
                kod = kodUrusanDAO.findById("HTB");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);

                Permohonan mohonReg = new Permohonan();
                if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                    mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                } else {
                    mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                }

                LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setCawangan(senaraiHakmilik.get(0).getCawangan());
                permohonanRujLuar.setPermohonan(mohonReg);
                //ida tmbah 29/04/2013
                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String formattedDate = dateFormat.format(date);
                    LOG.info("--formattedDate--" + formattedDate);
                    permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                } catch (Exception e) {
                }
                KodRujukan kodRujukan;
                kodRujukan = kodRujukanDAO.findById("FL");
                permohonanRujLuar.setKodRujukan(kodRujukan);
                strService.SimpanMohonRujukLuar(permohonanRujLuar);
                LOG.info("--Saved in Mohon Rujuluar--:");
                LOG.info("-----initiated to HTB-----");

            }
            // Done Initiate task into HTB for urusan PBBS, PBBD, PBTS, PBS

            //Generate ID Hakmilik PBBS, PBBD, PBTS, PBS
            if (permohonan.getKodUrusan().getKod().equals("PBBS")
                    || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                kod = kodUrusanDAO.findById("HT");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());

                /* added for Strata to generate Hakmilik strata @DEMO, SEREMBAN 26-07-2012*/
                LOG.info("-----generating Id Hakmiliks-----");
                Permohonan permohonanBaru = new Permohonan();
                try {
                    //permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);
                    if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                        //generate HT
                        permohonanBaru = comm.createPermohonanBaru2(permohonan, kod, peng, senaraiHakmilik);
                    } else {
                        permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);

                    }

                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(terimaPABValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
                LOG.info("PERMOHONAN BARU : " + permohonanBaru.getIdPermohonan());
                LOG.info("-----Redirecting to CommonService to generate Id Hakmiliks-----");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(permohonan.getIdPermohonan());
                    if (mhnBngn.get(0).getNamaLain() != null) {
                    comm.generateHakmilikStrataN9manual(infoAudit, permohonanBaru, peng);
                    }
                    else{
                       comm.generateHakmilikStrataN9(infoAudit, permohonanBaru, peng);
                    }
                } else {
                    comm.generateHakmilikStrata(infoAudit, permohonanBaru, peng);
                }

            }
            //Done Generate ID Hakmilik PBBS, PBBD, PBTS, PBS


            //Generate ID Hakmilik PBBSS
            if (permohonan.getKodUrusan().getKod().equals("PBBSS")) {
                kod = kodUrusanDAO.findById("HTSPV");
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());

                /* added for Strata to generate Hakmilik strata @DEMO, SEREMBAN 26-07-2012*/
                LOG.info("-----generating Id Hakmiliks-----");
                Permohonan permohonanBaru = new Permohonan();
                try {
                    //permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);
                    if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                        //generate HTSPV
                        permohonanBaru = comm.createPermohonanBaru2(permohonan, kod, peng, senaraiHakmilik);
                    } else {
                        permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);

                    }

                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(terimaPABValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
                LOG.info("PERMOHONAN BARU : " + permohonanBaru.getIdPermohonan());
                LOG.info("-----Redirecting to CommonService to generate Id Hakmiliks-----");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    comm.generateHakmilikStrataPBBSSN9(infoAudit, permohonanBaru, peng);
                } else {
                    comm.generateHakmilikStrata(infoAudit, permohonanBaru, peng);
                }

            }
            //Done Generate ID Hakmilik PBBSS

            //Saving data into table mohon pihak
            if (permohonan.getKodUrusan().getKod().equals("PBBS")
                    || permohonan.getKodUrusan().getKod().equals("PBBD")
                    || permohonan.getKodUrusan().getKod().equals("PBS")
                    || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                System.out.println("----idHakmilik----" + idHakmilik);
                idBdnUrus = strService.getIdBdnUrus(idHakmilik);
                System.out.println("----idBdnUrus----" + idBdnUrus);
                idBdn = strService.getidPihakByIdBdn(idBdnUrus.get(0).getBadanPengurusan().getIdBadan());
                System.out.println("----idBdn----" + idBdn);
                idPhk = strService.getMaklumatByIDPihak(idBdn.getPihak().getIdPihak());
                System.out.println("----idPhk----" + idPhk);
                mohonDaft = strService.getidMohonHTBByIDSblm(permohonan.getIdPermohonan(), "HTB");
                System.out.println("----mohonDaft----" + mohonDaft);
                System.out.println("----mohonDaft.getIdPermohonan()----" + mohonDaft.getIdPermohonan());
                mhnDaftar.setIdPermohonan(mohonDaft.getIdPermohonan());
                mhnDaftar = permohonanDAO.findById(mohonDaft.getIdPermohonan());
                System.out.println("----mhnDaftar----" + mhnDaftar);
                mhnPihak.setPermohonan(mhnDaftar);
                //kodCaw.setKod("00");
                kodCaw = kodCawDAO.findById("00");
                System.out.println("----kodCaw----" + kodCaw);
                mhnPihak.setCawangan(kodCaw);
                kodPihak = kodPihakDAO.findById("PM");
                //kodPihak.setKod("PM");
                System.out.println("----kodPihak----" + kodPihak);
                mhnPihak.setJenis(kodPihak);
                System.out.println("----idBdn.getPihak().getIdPihak()----" + idBdn.getPihak().getIdPihak());
                idhm.setIdHakmilik(idHakmilik);
                mhnPihak.setHakmilik(idhm);
                System.out.println("----idhm----" + idhm);
                //idPhk.setIdPihak(idBdn.getPihak().getIdPihak());
                idPhk = pihakDAO.findById(idBdn.getPihak().getIdPihak());
                System.out.println("----idPhk----" + idPhk);
                System.out.println("----infoAudit----" + infoAudit);
                mhnPihak.setPihak(idPhk);
                mhnPihak.setInfoAudit(infoAudit);
                System.out.println("----idPhk.getNama()----" + idPhk.getNama());
                mhnPihak.setNama(idPhk.getNama());
                System.out.println("----idPhk.getJenisPengenalan()----" + idPhk.getJenisPengenalan());
                mhnPihak.setJenisPengenalan(idPhk.getJenisPengenalan());
                System.out.println("----idPhk.getNoPengenalan()----" + idPhk.getNoPengenalan());
                mhnPihak.setNoPengenalan(idPhk.getNoPengenalan());
                Alamat alamat = new Alamat();
                alamat.setAlamat1(idPhk.getAlamat1());
                alamat.setAlamat2(idPhk.getAlamat2());
                alamat.setAlamat3(idPhk.getAlamat3());
                alamat.setAlamat4(idPhk.getAlamat4());
                alamat.setPoskod(idPhk.getPoskod());
                alamat.setNegeri(idPhk.getNegeri());
                AlamatSurat alamatSurat = new AlamatSurat();
                alamatSurat.setAlamatSurat1(idPhk.getSuratAlamat1());
                alamatSurat.setAlamatSurat2(idPhk.getSuratAlamat2());
                alamatSurat.setAlamatSurat3(idPhk.getSuratAlamat3());
                alamatSurat.setAlamatSurat4(idPhk.getSuratAlamat4());
                alamatSurat.setPoskodSurat(idPhk.getSuratPoskod());
                alamatSurat.setNegeriSurat(idPhk.getSuratNegeri());
                mhnPihak.setAlamat(alamat);
                mhnPihak.setAlamatSurat(alamatSurat);
                mohonPihakDAO.saveOrUpdate(mhnPihak);
            }
        }
        //Done saving data into table mohon pihak

        //Generate ID Hakmilik for urusan PHPP n PHPC
        if (permohonan.getKodUrusan().getKod().equals("PHPC") || permohonan.getKodUrusan().getKod().equals("PHPP")) {
            LOG.info("--PHPC/PHPP--");
            if (permohonan.getKodUrusan().getKod().equals("PHPC") && permohonan.getBilanganPermohonan() == 1) {
                kod = kodUrusanDAO.findById("HTSPS");
            } else if (permohonan.getKodUrusan().getKod().equals("PHPC") && permohonan.getBilanganPermohonan() == 2) {
                kod = kodUrusanDAO.findById("HTSPB");
            } else {
                kod = kodUrusanDAO.findById("HTSC");
            }
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());

            /* added for Strata to generate Hakmilik strata @DEMO, SEREMBAN 26-07-2012*/
            LOG.info("-----generating Id Hakmiliks-----");
            Permohonan permohonanBaru = new Permohonan();
            try {
                if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                    //generate HT
                    permohonanBaru = comm.createPermohonanBaru2(permohonan, kod, peng, senaraiHakmilik);
                } else {
                    permohonanBaru = comm.createPermohonanBaru(permohonan, kod, peng);

                }
            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(terimaPABValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StaleObjectException ex) {
                java.util.logging.Logger.getLogger(terimaPABValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(terimaPABValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            LOG.info("PERMOhONANA BARU : " + permohonanBaru.getIdPermohonan());
            LOG.info("-----Redirecting to CommonService to generate Id Hakmiliks-----");

            // strService.generatePHPCHakmilikStrataN9(infoAudit, permohonanBaru, peng);

            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("PHPC")) {
                    comm.generateHakmilikPHPCStrataN9(infoAudit, permohonanBaru, peng);
                }
                if (permohonan.getKodUrusan().getKod().equals("PHPP")) {
                    comm.generateHakmilikPHPPStrataN9(infoAudit, permohonanBaru, peng);
                }
            } else {
                strService.generatePHPCHakmilikStrata(infoAudit, permohonanBaru, peng);
            }
        }

        //Done Generate ID Hakmilik for urusan PHPP n PHPC


        //Filter urusan PNB
        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")
                || permohonan.getKodUrusan().getKod().equals("PBBSS")) {
            //check urusan: PNB registered or not if registered, have to complete it first     
            try {
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                LOG.debug("----PNB----Registered----:" + mohonPNB);
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    LOG.debug("----mohonFasa----:" + mohonFasa);
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String user = (String) m.get("participant");
                    LOG.debug("----mohonPNB----user----:" + user);
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }
                }

            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }
        }
//        return null;
        return proposedOutcome;
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

    public BadanPengurusan getIdBdn() {
        return idBdn;
    }

    public void setIdBdn(BadanPengurusan idBdn) {
        this.idBdn = idBdn;
    }

    public Pihak getIdPhk() {
        return idPhk;
    }

    public void setIdPhk(Pihak idPhk) {
        this.idPhk = idPhk;
    }

    public PermohonanPihak getMhnPihak() {
        return mhnPihak;
    }

    public void setMhnPihak(PermohonanPihak mhnPihak) {
        this.mhnPihak = mhnPihak;
    }

    public List<Hakmilik> getIdBdnUrus() {
        return idBdnUrus;
    }

    public void setIdBdnUrus(List<Hakmilik> idBdnUrus) {
        this.idBdnUrus = idBdnUrus;
    }

    public Permohonan getMhnDaftar() {
        return mhnDaftar;
    }

    public void setMhnDaftar(Permohonan mhnDaftar) {
        this.mhnDaftar = mhnDaftar;
    }

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

    public KodJenisPihakBerkepentingan getKodPihak() {
        return kodPihak;
    }

    public void setKodPihak(KodJenisPihakBerkepentingan kodPihak) {
        this.kodPihak = kodPihak;
    }

    public Hakmilik getIdhm() {
        return idhm;
    }

    public void setIdhm(Hakmilik idhm) {
        this.idhm = idhm;
    }

    public Permohonan getMohonDaft() {
        return mohonDaft;
    }

    public void setMohonDaft(Permohonan mohonDaft) {
        this.mohonDaft = mohonDaft;
    }
}

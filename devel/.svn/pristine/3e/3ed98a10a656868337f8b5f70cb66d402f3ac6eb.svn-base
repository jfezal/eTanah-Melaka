/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.TanahRizabPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.initiateService.InitiateTaskService;
import etanah.view.stripes.pelupusan.initiateService.MohonHakmilikPelupusanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham Modified by Shazwan purpose : Integration to Pendaftaran
 */
public class CatitTanahValidator implements StageListener {

    @Inject
    MohonHakmilikPelupusanService mhservice;
    @Inject
    PelupusanService plpservice;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    InitiateTaskService its;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    GeneratorIdPermohonan generatorIdPermohonan;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private static final Logger LOG = Logger.getLogger(CatitTanahValidator.class);
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    StrataPtService strService;
    @Inject
    private TaskDebugService ts;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    etanah.Configuration conf;
    private String noWarta;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        String kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        int numUrusan = permohonan.getKodUrusan().getKod().equals("MCMCL") ? 1
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 2
                //                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 3
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 4
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 5
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 6
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 7
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 10
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 11
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 11
                : permohonan.getKodUrusan().getKod().equals("BMRE") ? 11
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 11
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 10
                : 0;
        KodUrusan kod = new KodUrusan();
        InfoAudit infoAudit = new InfoAudit();
        String stageName = context.getStageName();

        //Notify each pengguna pendaftar
        Notifikasi n = new Notifikasi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        n.setInfoAudit(ia);
        n.setCawangan(permohonan.getCawangan());

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the current user..
         */
        List<String> bpelName = new ArrayList<String>();
        String kodCaw = new String();
        if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
            bpelName.add("pptd"); //NOTIFICATION TO PHLA USER
            kodCaw = permohonan.getCawangan().getKod();
        } else {
            bpelName.add("ptptgregistration");
            kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN PTG, CANT GET FROM MOHON.KODCAW
        }

        if (bpelName.size() > 0) {
            listPp = plpservice.findPenggunaByBPEL(bpelName, kodCaw);
        }

        switch (numUrusan) {
            case 1: //MCMCL
                n.setTajuk("Makluman Permohonan Mencatitkan Tanah Sebagai Tanah Adat Melaka (MCL)");
                if (context.getStageName().equals("kemasukan")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("MCLM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hp.getHakmilik());
                    }

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilik) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilik);

                            }
                        }
                    }

                } else if (context.getStageName().equals("sedia_surat_tolak")) {
                    n.setMesej("Makluman Penyediaan Surat Tolak Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("MCLM"); //KOD MCLM IS NOT FOR THIS STAGE, NEED TO VERIFY
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (context.getStageName().equals("sedia_cukai_baru")) {
                    n.setMesej("Makluman Penyediaan Cukai Baru Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("MCLL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                        mohonRujLuar = pelupusanService.findPermohonanRujByNoFail(permohonan.getIdPermohonan());
                        if (mohonRujLuar != null) {
                            /*
                             * CHECKING HAKMILIK_URUSAN
                             */
                            HakmilikUrusan hu = new HakmilikUrusan();
                            hu = pelupusanService.findHakmilikUrusanByIdMohon(mohonRujLuar.getPermohonan().getIdPermohonan());
                            if (hu != null) {
                                if (hu.getAktif() == 'Y') {
                                    permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                    its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                } else {
                                    context.addMessage("HakmilikUrusan tidak aktif.");
                                    return null;
                                }
                            }
                        } else {
                            context.addMessage("Permohonan Mencatitkan Tanah Sebagai Tanah Adat Melaka (MCL) tidak dijumpai");
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (context.getStageName().equals("bayaran_proses")) {
                    n.setMesej("Makluman Proses Bayaran Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("MCLL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                        mohonRujLuar = pelupusanService.findPermohonanRujByNoFail(permohonan.getIdPermohonan());
                        if (mohonRujLuar != null) {
                            /*
                             * CHECKING HAKMILIK_URUSAN
                             */
                            HakmilikUrusan hu = new HakmilikUrusan();
                            hu = pelupusanService.findHakmilikUrusanByIdMohon(mohonRujLuar.getPermohonan().getIdPermohonan());
                            if (hu != null) {
                                if (hu.getAktif() == 'Y') {
                                    permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                    its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                } else {
                                    context.addMessage("HakmilikUrusan tidak aktif.");
                                    return null;
                                }
                            }
                        } else {
                            context.addMessage("Permohonan Mencatitkan Tanah Sebagai Tanah Adat Melaka (MCL) tidak dijumpai");
                        }
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2: //MMMCL
                n.setTajuk("Makluman Permohonan Memasukkan Tanah Sebagai Tanah Adat Melaka (MCL)");
                if (context.getStageName().equals("kemasukan")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("MCLM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();

                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilikh.add(hp.getHakmilik());
                    }

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    kod = kodUrusanDAO.findById("SBKBG"); // Hold dulu - Changed to SBKSM
//                    kod = kodUrusanDAO.findById("SBKSM");
                    kod = kodUrusanDAO.findById("SBTM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
//                    fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");

                    List<FasaPermohonan> senaraiFasa = plpservice.findMohonFasaByIdMohonIdPenggunaList(permohonan.getIdPermohonan(), "kemasukan");
                    fasaPermohonan = senaraiFasa.get(0);
                    if (fasaPermohonan != null) {
                        fasaPermohonan.setPermohonan(permohonan);
                        fasaPermohonan.setIdPengguna(peng.getIdPengguna());
                        InfoAudit info = new InfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        fasaPermohonan.setInfoAudit(info);
                        List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();

                        for (HakmilikPermohonan hm : senaraiHakmilik) {
                            if (hm.getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
                                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById("YQ"));
                            } else if (hm.getHakmilik().getKodHakmilik().getKod().equals("PM") || hm.getHakmilik().getKodHakmilik().getKod().equals("GM")) {
                                fasaPermohonan.setKeputusan(kodKeputusanDAO.findById("YT"));
                            }
                        }
                        LOG.debug("--update keputusan based on jenis hakmilik--");
                        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
                    }

                    LOG.debug("senaraiHakmilikh : " + senaraiHakmilikh.size());
                    if (!senaraiHakmilikh.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilikh) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilikh);

                            }
                        }
                    }

                } else if (context.getStageName().equals("perakuan_ptd")) {
                    n.setMesej("Makluman Perakuan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    permohonan = context.getPermohonan();
                      List<FasaPermohonan> senaraiFasa = plpservice.findMohonFasaByIdMohonIdPenggunaList(permohonan.getIdPermohonan(), "perakuan_ptd");
                    FasaPermohonan fasaPermohonan = senaraiFasa.get(0);
                    if (fasaPermohonan != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                            kod = kodUrusanDAO.findById("MCLL");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            Permohonan permohonanBaru = new Permohonan();
                            its.setPengguna(peng);
                            try {
                                permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (StaleObjectException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
//                            kod = kodUrusanDAO.findById("SBKSB");
                            kod = kodUrusanDAO.findById("SBTB");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            Permohonan permohonanBaru = new Permohonan();
                            its.setPengguna(peng);
                            try {
                                permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                its.setHakmilikPermohonan(permohonan, permohonanBaru);
                                its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (StaleObjectException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }//Sementara untuk pembatalan noting 
                else if (context.getStageName().equals("sedia_surat_tolak")) {
                    n.setMesej("Makluman Penyediaan Surat Tolak Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("SBTB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);

                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (context.getStageName().equals("maklum_bayaran")) {
                    n.setTajuk("Makluman Prose Bayaran Permohonan Permohonan Memasukkan Tanah sebagai Tanah Adat Melaka (MCL)");
                    n.setMesej("Makluman Proses Bayaran Permohonan - " + permohonan.getIdPermohonan());

                    ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                    list.add(kodPerananDAO.findById("5"));
                    notifikasiService.addRolesToNotifikasi(n, peng.getKodCawangan(), list);

                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG dan Unit Hasil Telah Dihantar.");
                    //kod = kodUrusanDAO.findById("MCLL"); 
//                    kod = kodUrusanDAO.findById("SBKSL");
                    kod = kodUrusanDAO.findById("SBTL");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuarMultipleHakmilik(permohonanBaru, permohonan.getIdPermohonan(), "FL", permohonan);
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } //                }else if (context.getStageName().equals("sah_bayaran")) {
                //                    n.setMesej("Makluman Pengesahan Bayaran Permohonan - " + permohonan.getIdPermohonan());
                //                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                //                    kod = kodUrusanDAO.findById("SBTL");
                //                    LOG.info(kod.getNama());
                //                    LOG.info(permohonan.getFolderDokumen());
                //                    Permohonan permohonanBaru = new Permohonan();
                //                    its.setPengguna(peng);
                //                    try {
                //                        permohonanBaru = its.createPermohonanBaru(permohonan, kod);
                //                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                //                    } catch (WorkflowException ex) {
                //                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                //                    } catch (StaleObjectException ex) {
                //                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                //                    } catch (Exception ex) {
                //                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                //                    }
                //                } else if (context.getStageName().equals("arahan_tugas6")) {
                //                    n.setMesej("Makluman Arahan Tugasan Permohonan - " + permohonan.getIdPermohonan());
                //                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                //                    permohonan = context.getPermohonan();
                //
                //                    if (proposedOutcome.equals("LO")) {
                //                        //            permohonan.getPermohonanSebelum().
                //                        LOG.info("Initiate HKBM");
                //                        peng = (Pengguna) context.getPengguna();
                //                        //        InfoAudit infoAudit = new InfoAudit();
                //                        //        infoAudit.setDimasukOleh(peng);
                //                        //        infoAudit.setTarikhMasuk(new java.util.Date());
                //                        //        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                //                        //        String[] name = {"idHakmilik"};
                //                        //        Object[] value = {idHakmilik};
                //                        //        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                //                        String idMohon = context.getPermohonan().getIdPermohonan();
                //                        //        idHakmilik = "" + a ;
                //                        String[] name = {"idMohon"};
                //                        Object[] value = {idMohon};
                //                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                //                        // kod = kodUrusanDAO.findById("HKBM"); Changes based on email from Faizal Ali 12/04/2012
                //                        // kod = kodUrusanDAO.findById("HKSTB");
                //                        kod = kodUrusanDAO.findById("HKBM");
                //                        LOG.info(kod.getNama());
                //                        LOG.info(permohonan.getFolderDokumen());
                //                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
                //                    }
                //                }
                else if (context.getStageName().equals("sedia_jadual")) {
                    n.setTajuk("Makluman Prose Bayaran Permohonan Permohonan Memasukkan Tanah sebagai Tanah Adat Melaka (MCL)");
                    n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());

                    ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                    list.add(kodPerananDAO.findById("5"));
                    notifikasiService.addRolesToNotifikasi(n, peng.getKodCawangan(), list);

                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG dan Unit Hasil Telah Dihantar.");
                    permohonan = context.getPermohonan();

                    LOG.info("Initiate HSBM or HKBM");
                    peng = (Pengguna) context.getPengguna();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                    String idMohon = context.getPermohonan().getIdPermohonan();
                    //        idHakmilik = "" + a ;
                      List<FasaPermohonan> senaraiFasa = plpservice.findMohonFasaByIdMohonIdPenggunaList(permohonan.getIdPermohonan(), "kemasukan");
                    FasaPermohonan fasaPermohonan = senaraiFasa.get(0);
//                    FasaPermohonan fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanService.findByIdPermohonan(senaraiFasa.get(0).getPermohonan().getIdPermohonan());
//                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();
                    String jenisHM = "";
                    // kod = kodUrusanDAO.findById("HSBM"); Changes based on email from Faizal Ali 12/04/2012
                    //kod = kodUrusanDAO.findById("HSSTB");
//                    for (HakmilikPermohonan hm : senaraiHakmilik) {
//                        if (hm.getHakmilik().getKodHakmilik().getKod().equals("HSM")) {
//                            senaraiHakmilikHSBM.add(hm);
//                        } else if (hm.getHakmilik().getKodHakmilik().getKod().equals("PM") || hm.getHakmilik().getKodHakmilik().getKod().equals("GM")) {
//                            senaraiHakmilikHKBM.add(hm);
//                        }
//                    }

                    if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
                    } else if (fasaPermohonan.getKeputusan().getKod().equals("YT")) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan, stageName);
                    }
                }

                break;
            case 3:
                if (context.getStageName().equals("11keputusan_laporan_permohonan")) {
                    permohonan = context.getPermohonan();
                    List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilikh.add(hp.getHakmilik());
                    }

                    if (!senaraiHakmilikh.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilikh) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilikh);

                            }
                        }
                    }

                    if (proposedOutcome.equals("AW")) {
                        LOG.info("Initiate PHLA");
                        peng = (Pengguna) context.getPengguna();
                        String idMohon = context.getPermohonan().getIdPermohonan();
                        String[] name = {"idMohon"};
                        Object[] value = {idMohon};
                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                        kod = kodUrusanDAO.findById("PHLA");

                        if (disLaporanTanahService.generatePermohonanWorkflow(kod, peng, senaraiHakmilik, permohonan)) {
                            return proposedOutcome;
                        } else {
                            return null;
                        }

                    }
                } else if (context.getStageName().equals("07keputusan_laporan_permohonan")) {
                    permohonan = context.getPermohonan();
                    List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilikh.add(hp.getHakmilik());
                    }

                    if (!senaraiHakmilikh.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilikh) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilikh);

                            }
                        }
                    }

                    if (proposedOutcome.equals("AW")) {
                        LOG.info("Initiate PHLA");
                        peng = (Pengguna) context.getPengguna();
                        String idMohon = context.getPermohonan().getIdPermohonan();
                        String[] name = {"idMohon"};
                        Object[] value = {idMohon};
                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                        kod = kodUrusanDAO.findById("PHLA");

                        if (disLaporanTanahService.generatePermohonanWorkflow(kod, peng, senaraiHakmilik, permohonan)) {
                            return proposedOutcome;
                        } else {
                            return null;
                        }

                    }
                }

                break;
            case 4:
                //PTGSA
                n.setTajuk("Makluman Permohonan Penamatan Tanah Berkelompok GSA");
                if (context.getStageName().equals("18TerimaWartaPNB")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    kod = kodUrusanDAO.findById("IGSAB");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();

                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilik) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilik);

                            }
                        }
                    }

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case 5:
                //PHLA *edit to PHLP
                n.setTajuk("Makluman Permohonan Hak Lalulalang");
                if (context.getStageName().equals("38SedPelan")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Tanah Telah Dihantar.");
                    permohonan = context.getPermohonan();
                    FasaPermohonan mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23Smk");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            }
//                            else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
//                                kod = kodUrusanDAO.findById("HLLS");
//                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();

                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilik) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilik);

                            }
                        }
                    }

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (context.getStageName().equals("45TrmSalinanPA")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Penghantaran berjaya.");
                    permohonan = context.getPermohonan();
                    FasaPermohonan mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            } else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                                kod = kodUrusanDAO.findById("HLLS");
                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (context.getStageName().equals("35SmkdanCtkTndtngn")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Penghantaran berjaya.");
                    permohonan = context.getPermohonan();
                    FasaPermohonan mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                                kod = kodUrusanDAO.findById("HLLA");
                            } else if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                                kod = kodUrusanDAO.findById("HLLS");
                            }
                        }
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();

                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilik) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilik);

                            }
                        }
                    }

                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case 6:
                //RAYT (Kes tiada hakmilik)
                n.setTajuk("Makluman Rayuan Penolakan Permohonan");
                if (context.getStageName().equals("027_SediaPU") || context.getStageName().equals("g_terima_pa_b1")) {
                    n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    permohonan = context.getPermohonan();

                    LOG.info("Initiate HSBM or HKBM");
                    peng = (Pengguna) context.getPengguna();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();

                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
                            senaraiHakmilikHSBM.add(hm);
                        } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
                                || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
                            senaraiHakmilikHKBM.add(hm);
                        }
                    }

                    List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilikh.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilikh) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilikh);

                            }
                        }
                    }

                    if (senaraiHakmilikHSBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
                    } else if (senaraiHakmilikHKBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);

                    }
                }

                break;
            case 7:
                //PBHL
                n.setTajuk("Makluman Pembatalan Hak Lalulalang");
                if (context.getStageName().equals("22SedButir")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());

                    permohonan = context.getPermohonan();
                    List<HakmilikPermohonan> senaraiHakmilikTerlibat = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", permohonan.getIdPermohonan());
                    Hakmilik hm = new Hakmilik();
                    hm = senaraiHakmilikTerlibat.get(0).getHakmilik();
                    List<HakmilikUrusan> hmUrusan = new ArrayList<HakmilikUrusan>();
                    hmUrusan = hakmilikUrusanService.findHakmilikUrusanByIdHakmilik(hm.getIdHakmilik());
                    boolean isTrue = false;
                    List<Permohonan> senaraisemakmohon = plpservice.getsenaraiIdMohon(permohonan.getIdPermohonan());

                    List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilik.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilik) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilik);

                            }
                        }
                    }

                    if (senaraisemakmohon.size() == 0) {
                        context.addMessage(" : Makluman Permohonan kepada Unit Pendaftaran Telah Dihantar.");
                        LOG.info("senaraisemakmohon.size()");
                        kod = kodUrusanDAO.findById("HLLB");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        Permohonan permohonanBaru = new Permohonan();
                        its.setPengguna(peng);
                        try {
                            permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                            its.setHakmilikPermohonan(permohonan, permohonanBaru);
                            its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                        } catch (WorkflowException ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (StaleObjectException ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        return null;
                    } else {
                        List<Permohonan> senaraimohondahdaftar = plpservice.getsenaraiIdMohonwithStatus(permohonan.getIdPermohonan());

                        if (senaraimohondahdaftar.size() == 0) {
                            context.addMessage(" : Permohonan ini belum didaftarkan.");
                            return null;
                        } else {
                            context.addMessage(" : Permohonan berjaya dihantar.");
                            isTrue = true;
                        }
                        /*for(int i = 0 ; i < hmUrusan.size() ; i++){
                        HakmilikUrusan hku = hmUrusan.get(i);
                        if(hku.getKodUrusan().getKod().equals("HLLB"))
                        isTrue = true;
                        }
                        if (isTrue) {
                        
                        }else{
                        context.addMessage("Permohonan ini belum didaftarkan") ;
                        return null;
                        }*/
                    }
                } else if (context.getStageName().equals("29Semakan")) {
                    n.setMesej("Makluman Kemasukan Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Penghantaran berjaya.");
                    permohonan = context.getPermohonan();
                    kod = kodUrusanDAO.findById("HKBM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan permohonanBaru = new Permohonan();
                    its.setPengguna(peng);
                    try {
                        permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                        its.setHakmilikPermohonan(permohonan, permohonanBaru);
                        its.setMohonRujukanLuar(permohonanBaru, permohonan.getIdPermohonan(), "FL");
                    } catch (WorkflowException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (StaleObjectException ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
            case 8:
                n.setTajuk("Makluman Permohonan Pemberimilikan Tanah Bawah Tanah");
                if (context.getStageName().equals("terima_pa_B1")) {
                    n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    permohonan = context.getPermohonan();

                    LOG.info("Initiate HSBM or HKBM");
                    peng = (Pengguna) context.getPengguna();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                    String idMohon = context.getPermohonan().getIdPermohonan();
                    String[] name = {"permohonan"};
                    Object[] value = {permohonan};
                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();
                    String jenisHM = "";

                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
                            senaraiHakmilikHSBM.add(hm);
                        } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
                                || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
                            senaraiHakmilikHKBM.add(hm);
                        }
                    }

                    List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        senaraiHakmilikh.add(hp.getHakmilik());
                    }
                    if (!senaraiHakmilik.isEmpty()) {
                        //Need To generate before Noting
                        LOG.info("buat urusan tukar ganti");
                        for (Hakmilik h : senaraiHakmilikh) {
                            if (h.getNoVersiDhde() == 0) {
                                prosesTukarGanti(peng, senaraiHakmilikh);

                            }
                        }
                    }

                    if (senaraiHakmilikHSBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HSBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
                    } else if (senaraiHakmilikHKBM.size() > 0) {
                        kod = kodUrusanDAO.findById("HKBM");
                        LOG.info(kod.getNama());
                        LOG.info(permohonan.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);

                    }

                }
                break;
            case 9:
                //PBMT
                n.setTajuk("Permohonan Pemberimilikan Tanah Kerajaan");
                if (kodNegeri.equals("04")) {
//                    if (context.getStageName().equals("g_penyediaan_pu_pt") || context.getStageName().equals("semak_charting_hakmilik") || context.getStageName().equals("g_penyediaan_pu2)")) {
//                        n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
//                        context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
//                        permohonan = context.getPermohonan();
//
//                        LOG.info("Initiate HSBM or HKBM");
//                        peng = (Pengguna) context.getPengguna();
//                        infoAudit = new InfoAudit();
//                        infoAudit.setDimasukOleh(peng);
//                        infoAudit.setTarikhMasuk(new java.util.Date());
//
//
////                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
//                        String[] name = {"permohonan"};
//                        Object[] value = {permohonan};
//                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
//                        List<HakmilikPermohonan> senaraiHakmilikHSBM = new ArrayList<HakmilikPermohonan>();
//                        List<HakmilikPermohonan> senaraiHakmilikHKBM = new ArrayList<HakmilikPermohonan>();
//
//                        for (HakmilikPermohonan hm : senaraiHakmilik) {
//                            if (hm.getKodHakmilik().getKod().equals("HSM") || hm.getKodHakmilik().getKod().equals("HMM") || hm.getKodHakmilik().getKod().equals("HSD")) {
//                                senaraiHakmilikHSBM.add(hm);
//                            } else if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("GMM")
//                                    || hm.getKodHakmilik().getKod().equals("PN") || hm.getKodHakmilik().getKod().equals("PM")) {
//                                senaraiHakmilikHKBM.add(hm);
//                            }
//                        }
//
//                        if (senaraiHakmilikHSBM.size() > 0) {
//                            kod = kodUrusanDAO.findById("HSBM");
//                            LOG.info(kod.getNama());
//                            LOG.info(permohonan.getFolderDokumen());
//                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBM, permohonan, stageName);
//                        } else if (senaraiHakmilikHKBM.size() > 0) {
//                            kod = kodUrusanDAO.findById("HKBM");
//                            LOG.info(kod.getNama());
//                            LOG.info(permohonan.getFolderDokumen());
//                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBM, permohonan, stageName);
//
//                        }
//                    }
                    if (context.getStageName().equals("hantar_pu_jupem")) {
                        n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
                        context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                        permohonan = new Permohonan();
                        permohonan = context.getPermohonan();
                        FasaPermohonan mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "qt_ft");
                        if (mohonFasa != null) {
                            if (mohonFasa.getKeputusan() != null) {
                                if (mohonFasa.getKeputusan().getKod().equals("QT")) {
                                    List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                                    listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
                                    LOG.info("Initiate HSBM");
                                    peng = (Pengguna) context.getPengguna();
                                    infoAudit = new InfoAudit();
                                    infoAudit.setDimasukOleh(peng);
                                    infoAudit.setTarikhMasuk(new java.util.Date());

//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
//                        String[] name = {"permohonan"};
//                        Object[] value = {permohonan};
//                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                                    List<HakmilikPermohonan> senaraiHakmilikHSBMPendaftar = new ArrayList<HakmilikPermohonan>();
                                    List<HakmilikPermohonan> senaraiHakmilikHSBMDaerah = new ArrayList<HakmilikPermohonan>();
                                    InfoAudit info = new InfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    if (listMohonKelompok.size() > 0) {
                                        LOG.info("Kelompok mode");
                                        Permohonan p = new Permohonan();
                                        for (PermohonanKelompok pk : listMohonKelompok) {
                                            p = pk.getPermohonan();
                                            LOG.info("Kod Urusan : " + p.getKodUrusan().getKod());
                                            LOG.info("Size HMP : " + p.getSenaraiHakmilik().size());
                                            for (HakmilikPermohonan hm : p.getSenaraiHakmilik()) {
                                                hm.setInfoAudit(info);
                                                if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                                    if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                        hm.setPegangan("P");
                                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                    } else {
                                                        hm.setPegangan("S");
                                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                    }
                                                    hm.setPermohonan(p);
                                                    strService.saveHakmilikPermohonan(hm);
                                                    senaraiHakmilikHSBMPendaftar.add(hm);
                                                } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                                    if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                        hm.setPegangan("P");
                                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                    } else {
                                                        hm.setPegangan("S");
                                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                    }
                                                    hm.setPermohonan(p);
                                                    strService.saveHakmilikPermohonan(hm);
                                                    senaraiHakmilikHSBMDaerah.add(hm);
                                                }
                                            }

                                            LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                                            LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                                            if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                                                LOG.info("HSBM Pendaftar");
                                                kod = kodUrusanDAO.findById("HSBM");
                                                LOG.info(kod.getNama());
                                                LOG.info(permohonan.getFolderDokumen());
                                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, permohonan, stageName);
                                            }

                                            if (senaraiHakmilikHSBMDaerah.size() > 0) {
                                                kod = kodUrusanDAO.findById("HSBM");
                                                LOG.info("HSBM Daerah");
                                                LOG.info(kod.getNama());
                                                LOG.info(permohonan.getFolderDokumen());
                                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, permohonan, stageName);
                                            }
                                        }
                                    } else {
                                        LOG.info("Kod Urusan : " + permohonan.getKodUrusan().getKod());
                                        LOG.info("Size HMP : " + permohonan.getSenaraiHakmilik().size());
                                        for (HakmilikPermohonan hm : permohonan.getSenaraiHakmilik()) {
                                            hm.setInfoAudit(info);
                                            if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                                hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHSBMPendaftar.add(hm);
                                            } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                                hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHSBMDaerah.add(hm);
                                            }
                                        }
                                        LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                                        LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                                        if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                                            LOG.info("HSBM Pendaftar");
                                            kod = kodUrusanDAO.findById("HSBM");
                                            LOG.info(kod.getNama());
                                            LOG.info(permohonan.getFolderDokumen());
                                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, permohonan, stageName);
                                        }

                                        if (senaraiHakmilikHSBMDaerah.size() > 0) {
                                            kod = kodUrusanDAO.findById("HSBM");
                                            LOG.info("HSBM Daerah");
                                            LOG.info(kod.getNama());
                                            LOG.info(permohonan.getFolderDokumen());
                                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, permohonan, stageName);
                                        }
                                    }
                                    proposedOutcome = "QT";

                                } else {
                                    proposedOutcome = "FT";
                                }
                            }

                        }

                    } else if (context.getStageName().equals("g_terima_pa_b1")) {
                        n.setMesej("Makluman Terima PA B1 - " + permohonan.getIdPermohonan());
                        context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                        permohonan = context.getPermohonan();
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "g_terima_pa_b1");

                        if (mohonFasa != null) {
                            if (mohonFasa.getKeputusan() != null) {
                                if (mohonFasa.getKeputusan().getKod().equals("T")) {
                                    LOG.info("Initiate HKBM");
                                    peng = (Pengguna) context.getPengguna();
                                    infoAudit = new InfoAudit();
                                    infoAudit.setDimasukOleh(peng);
                                    infoAudit.setTarikhMasuk(new java.util.Date());

//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
                                    String[] name = {"permohonan"};
                                    Object[] value = {permohonan};
                                    List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                                    List<HakmilikPermohonan> senaraiHakmilikHKBMPendaftar = new ArrayList<HakmilikPermohonan>();
                                    List<HakmilikPermohonan> senaraiHakmilikHKBMDaerah = new ArrayList<HakmilikPermohonan>();
                                    InfoAudit info = new InfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    boolean statusQT = false;
                                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                                        hm.setInfoAudit(info);
                                        if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                                            if (hm.getKodHakmilikTetap().getKod().equals("GRN") || hm.getKodHakmilikTetap().getKod().equals("PN")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHKBMPendaftar.add(hm);
                                            } else if (hm.getKodHakmilikTetap().getKod().equals("GM") || hm.getKodHakmilikTetap().getKod().equals("PM")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setPegangan("P");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                                } else {
                                                    hm.setPegangan("S");
                                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHKBMDaerah.add(hm);
                                            }
                                            statusQT = Boolean.TRUE;
                                        } else { //Straight FT
                                            if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                    hm.setPegangan("P");
                                                } else {
                                                    hm.setPegangan("S");
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHKBMPendaftar.add(hm);
                                            } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                                hm.setKodHakmilikSementara(null);
                                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                    hm.setPegangan("P");
                                                } else {
                                                    hm.setPegangan("S");
                                                }
                                                hm.setPermohonan(permohonan);
                                                hm = strService.saveHakmilikPermohonan(hm);
                                                senaraiHakmilikHKBMDaerah.add(hm);
                                            }
                                            statusQT = Boolean.FALSE;
                                        }
                                    }

                                    if (senaraiHakmilikHKBMPendaftar.size() > 0) {
                                        if (statusQT) {
                                            kod = kodUrusanDAO.findById("HKGHS");
                                            LOG.info("HKGHS Pendaftar");
                                        } else {
                                            kod = kodUrusanDAO.findById("HKBM");
                                            LOG.info("HKBM Pendaftar");
                                        }

                                        LOG.info(kod.getNama());
                                        LOG.info(permohonan.getFolderDokumen());
                                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHKBMPendaftar, permohonan, stageName);
                                    }

                                    if (senaraiHakmilikHKBMDaerah.size() > 0) {
                                        if (statusQT) {
                                            kod = kodUrusanDAO.findById("HKGHS");
                                            LOG.info("HKGHS Daerah");
                                        } else {
                                            kod = kodUrusanDAO.findById("HKBM");
                                            LOG.info("HKBM Daerah");
                                        }
                                        LOG.info(kod.getNama());
                                        LOG.info(permohonan.getFolderDokumen());
                                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBMDaerah, permohonan, stageName);
                                    }
                                }
                            }
                        }
                    }
                } else if (context.getStageName().equals("semak_pu")) {
                    n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                    permohonan = new Permohonan();
                    permohonan = context.getPermohonan();
                    List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                    listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");

                    LOG.info("Initiate HSBM");
                    peng = (Pengguna) context.getPengguna();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
//                        String[] name = {"permohonan"};
//                        Object[] value = {permohonan};
//                        List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                    List<HakmilikPermohonan> senaraiHakmilikHSBMPendaftar = new ArrayList<HakmilikPermohonan>();
                    List<HakmilikPermohonan> senaraiHakmilikHSBMDaerah = new ArrayList<HakmilikPermohonan>();
                    InfoAudit info = new InfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    if (listMohonKelompok.size() > 0) {
                        LOG.info("Kelompok mode");
                        Permohonan p = new Permohonan();
                        for (PermohonanKelompok pk : listMohonKelompok) {
                            p = pk.getPermohonan();
                            senaraiHakmilikHSBMPendaftar = new ArrayList<HakmilikPermohonan>();
                            senaraiHakmilikHSBMDaerah = new ArrayList<HakmilikPermohonan>();
                            senaraiHakmilikHSBMPendaftar.clear();
                            senaraiHakmilikHSBMDaerah.clear();
                            LOG.info("IDMOHON : " + p.getIdPermohonan());
                            LOG.info("Kod Urusan : " + p.getKodUrusan().getKod());
                            LOG.info("Size HMP : " + p.getSenaraiHakmilik().size());
                            LOG.info("Size senaraiHakmilikHSBMDaerah :" + senaraiHakmilikHSBMDaerah.size());
                            for (HakmilikPermohonan hm : p.getSenaraiHakmilik()) {
                                hm.setInfoAudit(info);
                                if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                    if (hm.getKodHakmilik().getKod().equals("PN")) {
                                        hm.setPegangan("P");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                    } else {
                                        hm.setPegangan("S");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                    }
                                    hm.setPermohonan(p);
                                    strService.saveHakmilikPermohonan(hm);
                                    senaraiHakmilikHSBMPendaftar.add(hm);
                                } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                    hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                    if (hm.getKodHakmilik().getKod().equals("PM")) {
                                        hm.setPegangan("P");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                    } else {
                                        hm.setPegangan("S");
                                        hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                        hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                    }
                                    hm.setPermohonan(p);
                                    strService.saveHakmilikPermohonan(hm);
                                    senaraiHakmilikHSBMDaerah.add(hm);
                                }
                            }

                            LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                            LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                            if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                                LOG.info("HSBM Pendaftar");
                                kod = kodUrusanDAO.findById("HSBM");
                                LOG.info(kod.getNama());
                                LOG.info(p.getFolderDokumen());
                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, p, stageName);
                            }

                            if (senaraiHakmilikHSBMDaerah.size() > 0) {
                                kod = kodUrusanDAO.findById("HSBM");
                                LOG.info("HSBM Daerah");
                                LOG.info(kod.getNama());
                                LOG.info(p.getFolderDokumen());
                                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, p, stageName);
                            }
                        }
                    } else {
                        LOG.info("Kod Urusan : " + permohonan.getKodUrusan().getKod());
                        LOG.info("Size HMP : " + permohonan.getSenaraiHakmilik().size());
                        for (HakmilikPermohonan hm : permohonan.getSenaraiHakmilik()) {
                            hm.setInfoAudit(info);
                            if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSD"));
                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                    hm.setPegangan("P");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                } else {
                                    hm.setPegangan("S");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                }
                                hm.setPermohonan(permohonan);
                                strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHSBMPendaftar.add(hm);
                            } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                hm.setKodHakmilikSementara(disLaporanTanahService.getKodHakmilikDAO().findById("HSM"));
                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                    hm.setPegangan("P");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                } else {
                                    hm.setPegangan("S");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                    hm.setKodHakmilikTetap(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                }
                                hm.setPermohonan(permohonan);
                                strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHSBMDaerah.add(hm);
                            }
                        }
                        LOG.info("Size Hakmilik Daerah : " + senaraiHakmilikHSBMDaerah.size());
                        LOG.info("Size Hakmilik Pendaftar : " + senaraiHakmilikHSBMPendaftar.size());
                        if (senaraiHakmilikHSBMPendaftar.size() > 0) {
                            LOG.info("HSBM Pendaftar");
                            kod = kodUrusanDAO.findById("HSBM");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHSBMPendaftar, permohonan, stageName);
                        }

                        if (senaraiHakmilikHSBMDaerah.size() > 0) {
                            kod = kodUrusanDAO.findById("HSBM");
                            LOG.info("HSBM Daerah");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHSBMDaerah, permohonan, stageName);
                        }
                    }

                } else if (context.getStageName().equals("g_terima_pa_b1")) {
                    n.setMesej("Makluman Terima PA B1 - " + permohonan.getIdPermohonan());

                    permohonan = context.getPermohonan();
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "g_terima_pa_b1");

                    if (mohonFasa != null) {
                        if (mohonFasa.getKeputusan() != null) {
                            if (mohonFasa.getKeputusan().getKod().equals("LO")) {
                                context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                                LOG.info("Initiate HKBM");
                                peng = (Pengguna) context.getPengguna();
                                infoAudit = new InfoAudit();
                                infoAudit.setDimasukOleh(peng);
                                infoAudit.setTarikhMasuk(new java.util.Date());

//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
                                String[] name = {"permohonan"};
                                Object[] value = {permohonan};
                                List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                                List<HakmilikPermohonan> senaraiHakmilikHKBMPendaftar = new ArrayList<HakmilikPermohonan>();
                                List<HakmilikPermohonan> senaraiHakmilikHKBMDaerah = new ArrayList<HakmilikPermohonan>();
                                InfoAudit info = new InfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                boolean statusQT = false;
                                for (HakmilikPermohonan hm : senaraiHakmilik) {
                                    hm.setInfoAudit(info);
                                    if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                                        if (hm.getKodHakmilikTetap().getKod().equals("GRN") || hm.getKodHakmilikTetap().getKod().equals("PN")) {
                                            hm.setKodHakmilikSementara(null);
                                            if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                hm.setPegangan("P");
                                                hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                            } else {
                                                hm.setPegangan("S");
                                                hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                            }
                                            hm.setPermohonan(permohonan);
                                            hm = strService.saveHakmilikPermohonan(hm);
                                            senaraiHakmilikHKBMPendaftar.add(hm);
                                        } else if (hm.getKodHakmilikTetap().getKod().equals("GM") || hm.getKodHakmilikTetap().getKod().equals("PM")) {
                                            hm.setKodHakmilikSementara(null);
                                            if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                hm.setPegangan("P");
                                                hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                            } else {
                                                hm.setPegangan("S");
                                                hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                            }
                                            hm.setPermohonan(permohonan);
                                            hm = strService.saveHakmilikPermohonan(hm);
                                            senaraiHakmilikHKBMDaerah.add(hm);
                                        }
                                        statusQT = Boolean.TRUE;
                                    } else { //Straight FT
                                        if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                            hm.setKodHakmilikSementara(null);
                                            if (hm.getKodHakmilik().getKod().equals("PN")) {
                                                hm.setPegangan("P");
                                            } else {
                                                hm.setPegangan("S");
                                            }
                                            hm.setPermohonan(permohonan);
                                            hm = strService.saveHakmilikPermohonan(hm);
                                            senaraiHakmilikHKBMPendaftar.add(hm);
                                        } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                            hm.setKodHakmilikSementara(null);
                                            if (hm.getKodHakmilik().getKod().equals("PM")) {
                                                hm.setPegangan("P");
                                            } else {
                                                hm.setPegangan("S");
                                            }
                                            hm.setPermohonan(permohonan);
                                            hm = strService.saveHakmilikPermohonan(hm);
                                            senaraiHakmilikHKBMDaerah.add(hm);
                                        }
                                        statusQT = Boolean.FALSE;
                                    }
                                }

                                if (senaraiHakmilikHKBMPendaftar.size() > 0) {
                                    if (statusQT) {
                                        kod = kodUrusanDAO.findById("HKGHS");
                                        LOG.info("HKGHS Pendaftar");
                                    } else {
                                        kod = kodUrusanDAO.findById("HKBM");
                                        LOG.info("HKBM Pendaftar");
                                    }

                                    LOG.info(kod.getNama());
                                    LOG.info(permohonan.getFolderDokumen());
                                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHKBMPendaftar, permohonan, stageName);
                                }

                                if (senaraiHakmilikHKBMDaerah.size() > 0) {
                                    if (statusQT) {
                                        kod = kodUrusanDAO.findById("HKGHS");
                                        LOG.info("HKGHS Daerah");
                                    } else {
                                        kod = kodUrusanDAO.findById("HKBM");
                                        LOG.info("HKBM Daerah");
                                    }
                                    LOG.info(kod.getNama());
                                    LOG.info(permohonan.getFolderDokumen());
                                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBMDaerah, permohonan, stageName);
                                }
                            }
                        }
                    }
                } else if (context.getStageName().equals("lulus_bersyarat")) {
                    LOG.info("Lulus Bersyarat");
                    permohonan = context.getPermohonan();
                    FasaPermohonan fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "lulus_bersyarat");
                    if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("Y")) {
                            n.setMesej("Makluman Penyediaan Permohonan Pembatalan Perizaban");
                            context.addMessage(" : Permohonan Pembatalan Perizaban telah berjaya.");
                            kod = kodUrusanDAO.findById("PBRZ");
                            LOG.info(kod.getNama());
                            LOG.info(permohonan.getFolderDokumen());
                            Permohonan permohonanBaru = new Permohonan();
                            its.setPengguna(peng);
                            try {
                                permohonanBaru = its.createPermohonanBaru(permohonan, kod, stageName);
                                its.setHakmilikPermohonan(permohonan, permohonanBaru);
//                                    ia = new InfoAudit();
//                                    ia = permohonan.getInfoAudit();
//                                    permohonan.setStatus("SD");
//                                    permohonan.setInfoAudit(ia);
//                                    pelupusanService.simpanPermohonan(permohonan);
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (StaleObjectException ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            n.setMesej("Makluman Keputusan Permohonan Pemberimilikan Tanah Kerajaan - Lulus");
                            List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                            IWorkflowContext ctxW = null;
//                                listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "T");
                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(permohonan.getIdPermohonan());
                            if (listMohonKelompok.size() > 0) {
                                Permohonan p = new Permohonan();
                                for (PermohonanKelompok pk : listMohonKelompok) {
                                    p = pk.getPermohonan();
                                    if (p != null) {
                                        if (p.getSenaraiHakmilik().size() > 0) {
                                            HakmilikPermohonan hp = p.getSenaraiHakmilik().get(0);

                                            if (hp != null) {
                                                ia = new InfoAudit();
                                                ia = p.getInfoAudit();
                                                if (hp.getKeputusan() != null) {
                                                    if (hp.getKeputusan().getKod().equals("L")) {
                                                        p.setStatus("AK");
                                                    }
                                                    p.setKeputusan(hp.getKeputusan());
                                                    p.setPermohonanSebelum(permohonan);

                                                }
                                                p.setInfoAudit(ia);
                                                pelupusanService.simpanPermohonan(p);
                                            }
                                        }
                                        if (p.getKeputusan().getKod().equals("L")) {
                                            //Push BPEL to stage terima_keputusan_mmk
                                            LOG.info("Push id mohon yang lulus ke stage terima_keputusan");
                                            try {
                                                String taskId = new String();
                                                String nextStage = new String();

                                                fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(p.getIdPermohonan(), "kemasukan");
                                                if (pk.getJenisKelopok().equals("K")) {
                                                    ctxW = WorkFlowService.authenticate(fasaPermohonan.getIdPengguna());
                                                } else {
                                                    ctxW = WorkFlowService.authenticate(p.getInfoAudit().getDikemaskiniOleh().getIdPengguna());
                                                }
                                                //   ctxW = WorkFlowService.authenticate(p.getInfoAudit().getDikemaskiniOleh().getIdPengguna());
                                                Map m = ts.traceTask(p.getIdPermohonan());
                                                taskId = (String) m.get("taskID");
                                                LOG.info("Task Id :" + taskId);
                                                if (StringUtils.isNotBlank(taskId)) {
//                                                    WorkFlowService.acquireTask(taskId, ctxW);
                                                    if (permohonan.getCatatan().equals("T")) { //Utk bertindih
                                                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "L");
                                                    } else if (permohonan.getCatatan().equals("K")) {
                                                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "K");
                                                    } else if (permohonan.getCatatan().equals("R")) {
                                                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "K");
                                                    }
                                                }
                                            } catch (WorkflowException ex) {
                                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (StaleObjectException ex) {
                                                java.util.logging.Logger.getLogger(CatitTanahValidator.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }
                                }
                                //SDP kan Kelompok
                                if (permohonan.getCatatan().equals("K")) {
                                    ia = new InfoAudit();
                                    ia = permohonan.getInfoAudit();
                                    permohonan.setStatus("SD");
                                    permohonan.setInfoAudit(ia);
                                    pelupusanService.simpanPermohonan(permohonan);
                                }

                                if (permohonan.getCatatan().equals("T")) {
                                    context.addMessage(" : Permohonan Bertindih telah selesai. Sila teruskan dengan permohonan individu");
                                    proposedOutcome = "L"; //Make grouping id Completed for bertindih
                                } else if (permohonan.getCatatan().equals("K")) {
                                    context.addMessage(" : Sila teruskan dengan permohonan individu. Permohonan Berkelompok akan bersambung selepas bayaran untuk permohonan individu telah dibuat.");
                                    proposedOutcome = "K";//Push kelompok ke stage QTFT 
                                } else if (permohonan.getCatatan().equals("R")) {
                                    context.addMessage(" : Sila teruskan dengan permohonan individu. Permohonan Beramai-ramai akan bersambung selepas bayaran untuk permohonan individu telah dibuat.");
                                    proposedOutcome = "K";//Push kelompok ke stage QTFT 
                                }
                            } else {
                                context.addMessage("Penghantaran telah berjaya.");
                            }
                        }
                    }
                }

                break;
            case 10:
                n.setMesej("Terima Senarai Penempatan Peserta dan Hantar ke Modul Pendaftaran " + permohonan.getIdPermohonan());
                //context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                //context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                permohonan = context.getPermohonan();
                FasaPermohonan mohonFasa = new FasaPermohonan();
                //mohonFasa = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "19TrmKptsnMMK");

                LOG.info("Initiate HKBM");
                peng = (Pengguna) context.getPengguna();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());

//                    String idMohon = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
                String[] name = {"permohonan"};
                Object[] value = {permohonan};
                List<HakmilikPermohonan> senaraiHakmilik = hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
                List<HakmilikPermohonan> senaraiHakmilikHKBMPendaftar = new ArrayList<HakmilikPermohonan>();
                List<HakmilikPermohonan> senaraiHakmilikHKBMDaerah = new ArrayList<HakmilikPermohonan>();
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                boolean statusQT = false;

                if (kodNegeri.equals("04")) {
                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        hm.setInfoAudit(info);
                        if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                            if (hm.getKodHakmilikTetap().getKod().equals("GRN") || hm.getKodHakmilikTetap().getKod().equals("PN")) {
                                hm.setKodHakmilikSementara(null);
                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                    hm.setPegangan("P");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PN"));
                                } else {
                                    hm.setPegangan("S");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GRN"));
                                }
                                hm.setPermohonan(permohonan);
                                hm = strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHKBMPendaftar.add(hm);
                            } else if (hm.getKodHakmilikTetap().getKod().equals("GM") || hm.getKodHakmilikTetap().getKod().equals("PM")) {
                                hm.setKodHakmilikSementara(null);
                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                    hm.setPegangan("P");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("PM"));
                                } else {
                                    hm.setPegangan("S");
                                    hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                                }
                                hm.setPermohonan(permohonan);
                                hm = strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHKBMDaerah.add(hm);
                                statusQT = Boolean.TRUE;
                            }
                        } else { //Straight FT
                            if (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("PN")) {
                                hm.setKodHakmilikSementara(null);
                                if (hm.getKodHakmilik().getKod().equals("PN")) {
                                    hm.setPegangan("P");
                                } else {
                                    hm.setPegangan("S");
                                }
                                hm.setPermohonan(permohonan);
                                hm = strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHKBMPendaftar.add(hm);
                            } else if (hm.getKodHakmilik().getKod().equals("GM") || hm.getKodHakmilik().getKod().equals("PM")) {
                                hm.setKodHakmilikSementara(null);
                                if (hm.getKodHakmilik().getKod().equals("PM")) {
                                    hm.setPegangan("P");
                                } else {
                                    hm.setPegangan("S");
                                }
                                hm.setPermohonan(permohonan);
                                hm = strService.saveHakmilikPermohonan(hm);
                                senaraiHakmilikHKBMDaerah.add(hm);
                            }
                            statusQT = Boolean.FALSE;
                        }
                    }
                } else {
                    for (HakmilikPermohonan hm : senaraiHakmilik) {
                        hm.setInfoAudit(info);
                        if (hm.getKodHakmilikSementara() != null) { //From QT to FT
                            hm.setPegangan("S");
                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                            hm.setPermohonan(permohonan);
                            hm = strService.saveHakmilikPermohonan(hm);
                            senaraiHakmilikHKBMDaerah.add(hm);
                            statusQT = Boolean.TRUE;

                        } else { //Straight FT
                            hm.setPegangan("S");
                            hm.setPermohonan(permohonan);
                            hm.setKodHakmilik(disLaporanTanahService.getKodHakmilikDAO().findById("GM"));
                            hm = strService.saveHakmilikPermohonan(hm);
                            senaraiHakmilikHKBMDaerah.add(hm);
                            statusQT = Boolean.FALSE;
                        }
                    }
                }

                List<Hakmilik> senaraiHakmilikh = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    senaraiHakmilikh.add(hp.getHakmilik());
                }
                if (!senaraiHakmilikh.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    for (Hakmilik h : senaraiHakmilikh) {
                        if (h.getNoVersiDhde() == 0) {
                            prosesTukarGanti(peng, senaraiHakmilikh);

                        }
                    }
                }

                if (senaraiHakmilikHKBMPendaftar.size() > 0) {
                    kod = kodUrusanDAO.findById("HKBM");
                    LOG.info("HKBM Pendaftar");

                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikHKBMPendaftar, permohonan, stageName);
                }

                if (senaraiHakmilikHKBMDaerah.size() > 0) {
                    kod = kodUrusanDAO.findById("HKBM");
                    LOG.info("HKBM Daerah");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikHKBMDaerah, permohonan, stageName);
                }

                break;

            case 11: //MMRE,BMRE AND WMRE
                n.setTajuk("Makluman Masukkan Tanah Ke Dalam Rizab Melayu");
                n.setMesej("Makluman Penyediaan PU dan PT Permohonan - " + permohonan.getIdPermohonan());
                if (permohonan.getCawangan().getKod().equals("00")) {
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTG Telah Dihantar.");
                } else {
                    context.addMessage(" : Makluman Permohonan kepada Pentadbir Pendaftaran PTD Telah Dihantar.");
                }
                permohonan = context.getPermohonan();

                if (permohonan.getKodUrusan().getKod().equals("MMRE")) {
                    LOG.info("Initiate IRM");
                }
                if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                    LOG.info("Initiate IRM");
                }
                if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
                    LOG.info("Initiate IRMB");
                }
                if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                    LOG.info("Initiate ADAT");
                }

//                 TanahRizabPermohonan tanahRizabPermohonan = new TanahRizabPermohonan();
//               tanahRizabPermohonan =pelupusanService.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());
//               noWarta = tanahRizabPermohonan.getNoWarta();
//               LOG.info("noWarta: "+ tanahRizabPermohonan.getNoWarta());
//        LOG.info("noWarta1: "+ noWarta);
                peng = (Pengguna) context.getPengguna();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());

                String idMohon = context.getPermohonan().getIdPermohonan();
                String[] mohon_name = {"permohonan"};
                Object[] mohon_value = {permohonan};
                List<HakmilikPermohonan> senarai_Hakmilik = hakmilikPermohonanDAO.findByEqualCriterias(mohon_name, mohon_value, null);

                List<HakmilikPermohonan> senaraiHakmilikIRM = new ArrayList<HakmilikPermohonan>();
                List<HakmilikPermohonan> senaraiHakmilikIRM1 = new ArrayList<HakmilikPermohonan>();//add by zt
                String jenisHM = "";

//                for (HakmilikPermohonan hm : senarai_Hakmilik) {
//
//                    senaraiHakmilikIRM.add(hm);
//
//                }
                for (HakmilikPermohonan hm : senarai_Hakmilik) {
                    if (hm.getHakmilik().getCawangan().getKod().equals("00"))//add by zati
                    {
                        senaraiHakmilikIRM.add(hm);
                    } else {
                        senaraiHakmilikIRM1.add(hm);
                    }
                }

                List<Hakmilik> senaraiHakmilikm = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    senaraiHakmilikm.add(hp.getHakmilik());
                }
                if (!senaraiHakmilikm.isEmpty()) {
                    //Need To generate before Noting
                    LOG.info("buat urusan tukar ganti");
                    for (Hakmilik h : senaraiHakmilikm) {
                        if (h.getNoVersiDhde() == 0) {
                            prosesTukarGanti(peng, senaraiHakmilikm);

                        }
                    }
                }

                if (senaraiHakmilikIRM.size() > 0) {
                    if (permohonan.getKodUrusan().getKod().equals("MMRE")) {
                        kod = kodUrusanDAO.findById("IRM");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                        kod = kodUrusanDAO.findById("IRM");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
                        kod = kodUrusanDAO.findById("IRMB");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                        kod = kodUrusanDAO.findById("ADAT");
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());

                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPendaftar(kod, peng, senaraiHakmilikIRM, permohonan, stageName);

                } else if (senaraiHakmilikIRM1.size() > 0) {
                    if (permohonan.getKodUrusan().getKod().equals("MMRE")) {
                        kod = kodUrusanDAO.findById("IRM");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("WMRE")) {
                        kod = kodUrusanDAO.findById("IRM");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("BMRE")) {
                        kod = kodUrusanDAO.findById("IRMB");
                    }
                    if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                        kod = kodUrusanDAO.findById("ADAT");
                    }
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());

                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilikIRM1, permohonan, stageName);

                }

                break;
        }
        if (!permohonan.getKodUrusan().getKod().equals("PBGSA") || !permohonan.getKodUrusan().getKod().equals("PTMTA") || !permohonan.getKodUrusan().getKod().equals("BMRE") || !permohonan.getKodUrusan().getKod().equals("WMRE") || !permohonan.getKodUrusan().getKod().equals("PJTK")) {
            if (!listPp.isEmpty()) {
                Pengguna pguna = new Pengguna();
                ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                pguna = (Pengguna) listPp.get(0);
                list.add(pguna.getPerananUtama());
                n.setCawangan(pguna.getKodCawangan());
//            for(int i = 0 ; i < listPp.size() ; i++){
//             pguna = (Pengguna) listPp.get(i);
//             list.add(pguna.getPerananUtama());              
//            }

//            for(int z = 0 ; z < listPp.size() ; z++){
//             pguna = (Pengguna) listPp.get(z);
//            n.setCawangan(pguna.getKodCawangan());
//             notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan() , list);
//            }
                notifikasiService.addRolesToNotifikasi(n, pguna.getKodCawangan(), list);
            }
        }
//        if (permohonan.getSenaraiPemohon().isEmpty()) {
//            context.addMessage("Sila masukkan pemohon");
//            return null;
//        }
//        return null;
        return proposedOutcome;
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {

        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        if (!senaraiHakmilik.isEmpty()) {

            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        }

    }

    private void updateMohonHakmilik(Permohonan permohonan, Pengguna p) {

        List<HakmilikPermohonan> senaraiMohonHakmilik = new ArrayList<HakmilikPermohonan>();
        senaraiMohonHakmilik = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        if (!senaraiMohonHakmilik.isEmpty()) {
            for (HakmilikPermohonan mohonHakmilik : senaraiMohonHakmilik) {
                HakmilikPermohonan mohonHakmilikSave = new HakmilikPermohonan();
                mohonHakmilikSave.setPermohonan(permohonan);
                mohonHakmilikSave.setHakmilik(mohonHakmilik.getHakmilik());
                mohonHakmilikSave.setLuasTerlibat(mohonHakmilik.getLuasTerlibat());
                mohonHakmilikSave.setLuasDiluluskan(mohonHakmilik.getLuasDiluluskan());
                mohonHakmilikSave.setLuasUkurHalus(mohonHakmilik.getLuasUkurHalus());
                mohonHakmilikSave.setLuasPelanB1(mohonHakmilik.getLuasPelanB1());
                mohonHakmilikSave.setKodUnitLuas(mohonHakmilik.getKodUnitLuas());
                mohonHakmilikSave.setLuasUkurHalusUom(mohonHakmilik.getLuasUkurHalusUom());
                mohonHakmilikSave.setLuasLulusUom(mohonHakmilik.getLuasLulusUom());
                mohonHakmilikSave.setLuasPelanB1Uom(mohonHakmilik.getLuasPelanB1Uom());
                mohonHakmilikSave.setKodSeksyen(mohonHakmilik.getKodSeksyen());
                mohonHakmilikSave.setBandarPekanMukimBaru(mohonHakmilik.getBandarPekanMukimBaru());
                mohonHakmilikSave.setKodHakmilik(mohonHakmilik.getKodHakmilik());
                mohonHakmilikSave.setLot(mohonHakmilik.getLot());
                mohonHakmilikSave.setNoLot(mohonHakmilik.getNoLot());
                mohonHakmilikSave.setNoPajakan(mohonHakmilik.getNoPajakan());
                mohonHakmilikSave.setKategoriTanahBaru(mohonHakmilik.getKategoriTanahBaru());
                mohonHakmilikSave.setSyaratNyataBaru(mohonHakmilik.getSyaratNyataBaru());
                mohonHakmilikSave.setSekatanKepentinganBaru(mohonHakmilik.getSekatanKepentinganBaru());
                mohonHakmilikSave.setKadarCukaiBaru(mohonHakmilik.getKadarCukaiBaru());
                mohonHakmilikSave.setCukaiBaru(mohonHakmilik.getCukaiBaru());
                mohonHakmilikSave.setLokasi(mohonHakmilik.getLokasi());
                mohonHakmilikSave.setJarak(mohonHakmilik.getJarak());
                mohonHakmilikSave.setUnitJarak(mohonHakmilik.getUnitJarak());
                mohonHakmilikSave.setJarakDari(mohonHakmilik.getJarakDari());
                mohonHakmilikSave.setJarakDariNama(mohonHakmilik.getJarakDariNama());
                mohonHakmilikSave.setNomborRujukan(mohonHakmilik.getNomborRujukan());
                mohonHakmilikSave.setKodMilik(mohonHakmilik.getKodMilik());
                mohonHakmilikSave.setTarikhAwalDaftarGeran(mohonHakmilik.getTarikhAwalDaftarGeran());
                mohonHakmilikSave.setTempohPajakan(mohonHakmilik.getTempohPajakan());
                mohonHakmilikSave.setKodHakmilikTetap(mohonHakmilik.getKodHakmilikTetap());
                mohonHakmilikSave.setKodHakmilikSementara(mohonHakmilik.getKodHakmilikSementara());
                mohonHakmilikSave.setTempohHakmilik(mohonHakmilik.getTempohHakmilik());
                mohonHakmilikSave.setCukaiPerMeterPersegi(mohonHakmilik.getCukaiPerMeterPersegi());
                mohonHakmilikSave.setCukaiPerLot(mohonHakmilik.getCukaiPerLot());
                mohonHakmilikSave.setKadarPremium(mohonHakmilik.getKadarPremium());
                mohonHakmilikSave.setDendaPremium(mohonHakmilik.getDendaPremium());
                mohonHakmilikSave.setJenisPenggunaanTanah(mohonHakmilik.getJenisPenggunaanTanah());
                mohonHakmilikSave.setSyaratNyata(mohonHakmilik.getSyaratNyata());
                mohonHakmilikSave.setSekatanKepentingan(mohonHakmilik.getSekatanKepentingan());
                mohonHakmilikSave.setNilaianJpph(mohonHakmilik.getNilaianJpph());
                mohonHakmilikSave.setCatatan(mohonHakmilik.getCatatan());
                mohonHakmilikSave.setHubunganHakmilik(mohonHakmilik.getHubunganHakmilik());
                mohonHakmilikSave.setDokumen1(mohonHakmilik.getDokumen1());
                mohonHakmilikSave.setDokumen2(mohonHakmilik.getDokumen2());
                mohonHakmilikSave.setDokumen3(mohonHakmilik.getDokumen3());
                mohonHakmilikSave.setDokumen4(mohonHakmilik.getDokumen4());
                mohonHakmilikSave.setDokumen5(mohonHakmilik.getDokumen5());
                mohonHakmilikSave.setDokumen6(mohonHakmilik.getDokumen6());
                mohonHakmilikSave.setKosInfra(mohonHakmilik.getKosInfra());
                mohonHakmilikSave.setTanahDiambil(mohonHakmilik.getTanahDiambil());
                mohonHakmilikSave.setKeteranganInfra(mohonHakmilik.getKeteranganInfra());
                mohonHakmilikSave.setKeteranganCukaiBaru(mohonHakmilik.getKeteranganCukaiBaru());
                mohonHakmilikSave.setKeteranganKadarPremium(mohonHakmilik.getKeteranganKadarPremium());
                mohonHakmilikSave.setKodKegunaanTanah(mohonHakmilik.getKodKegunaanTanah());
                mohonHakmilikSave.setKeteranganKadarUkur(mohonHakmilik.getKeteranganKadarUkur());
                mohonHakmilikSave.setKeteranganKadarDaftar(mohonHakmilik.getKeteranganKadarDaftar());
                mohonHakmilikSave.setJarakDariKediaman(mohonHakmilik.getJarakDariKediaman());
                mohonHakmilikSave.setJarakDariKediamanUom(mohonHakmilik.getJarakDariKediamanUom());
                mohonHakmilikSave.setAgensiUpahUkur(mohonHakmilik.getAgensiUpahUkur());
                mohonHakmilikSave.setStatusLuasDiluluskan(mohonHakmilik.getStatusLuasDiluluskan());
                mohonHakmilikSave.setPenjenisan(mohonHakmilik.getPenjenisan());
                mohonHakmilikSave.setNoUnitPetak(mohonHakmilik.getNoUnitPetak());
                mohonHakmilikSave.setPegangan(mohonHakmilik.getPegangan());
                mohonHakmilikSave.setKodDUN(mohonHakmilik.getKodDUN());
                mohonHakmilikSave.setKodKawasanParlimen(mohonHakmilik.getKodKawasanParlimen());
                mohonHakmilikSave.setTempohPengosongan(mohonHakmilik.getTempohPengosongan());
                mohonHakmilikSave.setInfoAudit(disLaporanTanahService.findAudit(mohonHakmilikSave, "new", p));
                disLaporanTanahService.getPlpservice().simpanHakmilikPermohonan(mohonHakmilikSave);
            }
        }

    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

        int numUrusan = permohonan.getKodUrusan().getKod().equals("MCMCL") ? 1
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 2
                //                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 3
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 4
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 5
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 6
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 7
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 8
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 9
                : 0;
        switch (numUrusan) {
            case 9: //PBMT
                if (context.getStageName().equals("lulus_bersyarat")) {
                    FasaPermohonan fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "lulus_bersyarat");
                    if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("Y")) {
                            permohonan.setInfoAudit(disLaporanTanahService.findAudit(permohonan, "update", pengguna));
                            permohonan.setStatus("SD");
                            disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
                            LOG.debug("status permohonan:" + permohonan.getStatus());
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    /**
     * @return the noWarta
     */
    public String getNoWarta() {
        return noWarta;
    }

    /**
     * @param noWarta the noWarta to set
     */
    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }
    
}

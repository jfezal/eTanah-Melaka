
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
public class InitiatePendaftaranValidator implements StageListener {

    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow1 generateIdPerserahanWorkflow1;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    private static final Logger LOG = Logger.getLogger(InitiatePendaftaranValidator.class);
    private String idHakmilik;
    private String stageId;
    private String keputusan;
    private String kodUrusan;
    private PermohonanNota permohonanNota;
    private List<Pemohon> listPemohon = new ArrayList<Pemohon>();
    private List<Pemohon> listPemohonPihak = new ArrayList<Pemohon>();
    private List<PermohonanPihak> listPermohonanPihak = new ArrayList<PermohonanPihak>();
    private Boolean urusanTTWB = Boolean.FALSE;
    private Boolean urusanTTWKP = Boolean.FALSE;
    private Boolean urusanTTWLM = Boolean.FALSE;
    private Boolean urusanTTWLB = Boolean.FALSE;
    private Boolean initialUrusan = Boolean.FALSE;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
    private List<HakmilikPihakBerkepentingan> listPihakPemohon = new ArrayList<HakmilikPihakBerkepentingan>();

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String message = "";
        Permohonan permohonan = context.getPermohonan();

        Pengguna peng = (Pengguna) context.getPengguna();

        stageId = context.getStageName();
        kodUrusan = permohonan.getKodUrusan().getKod();

        LOG.info("--------------stage id------------- : " + stageId);


        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);

        try {
            if (permohonanNota != null) {
                LOG.info("::: kandungan nota :" + permohonanNota.getNota());
                context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                return null;
            }


            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
            FasaPermohonan fp2 = new FasaPermohonan();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        fp2 = fp;
                        keputusan = fp.getKeputusan().getKod();
                        LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }
                }
            }

            if ("05".equals(conf.getProperty("kodNegeri"))) {
                boolean isKenaMuatNaik = false;

                //mandatorikan carian sekiranya melibatkan endorsan
                if (stageId.equalsIgnoreCase("arah_endorsan")) {
                    List dokumenCarianRasmi = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SCRH");
                    List dokumenCarianSendiri = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SC");
                    if (dokumenCarianRasmi.isEmpty() && dokumenCarianSendiri.isEmpty()) {
                        context.addMessage("Sila muat naik Sijil Carian Rasmi Hakmilik atau Catatan Carian Persendirian terlebih dahulu");
                        return null;
                    } else {
                        for (Object obj : dokumenCarianRasmi) {
                            Dokumen doc = (Dokumen) obj;
                            if (doc.getNamaFizikal() == null) {
                                isKenaMuatNaik = true;
                                break;
                            }
                        }

                        if (isKenaMuatNaik) {
                            context.addMessage("Sila muat naik Sijil Carian Rasmi Hakmilik atau Catatan Carian Persendirian terlebih dahulu");
                            return null;
                        } else {
                            for (Object obj : dokumenCarianSendiri) {
                                Dokumen doc = (Dokumen) obj;
                                if (doc.getNamaFizikal() == null) {
                                    isKenaMuatNaik = true;
                                    break;
                                }
                            }
                        }

                        if (isKenaMuatNaik) {
                            context.addMessage("Sila muat naik Sijil Carian Rasmi Hakmilik atau Catatan Carian Persendirian terlebih dahulu");
                            return null;
                        }
                    }
                }

                String kodUrusanPend = "";

                if (kodUrusan.equals("351") || kodUrusan.equals("352")) {
                    LOG.info("--------------integrasi urusan 351 @ 352 ke pendaftaran--------------");
                    if (stageId.equalsIgnoreCase("arah_endorsan")) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                            kodUrusanPend = "TTW";
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                            kodUrusanPend = "TT";
                        }
                    } else if (stageId.equalsIgnoreCase("syor_arah_endorsan2")) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                            if (keputusan.equalsIgnoreCase("WB")) {
                                kodUrusanPend = "TTWB";
                            } else if (keputusan.equalsIgnoreCase("WK")) {
                                kodUrusanPend = "TTWKP";
                            } else if (keputusan.equalsIgnoreCase("WL")) {
                                kodUrusanPend = "TTWLM";
                            } else if (keputusan.equalsIgnoreCase("WM")) {
                                kodUrusanPend = "TTWLB";
                            }
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                            if (keputusan.equalsIgnoreCase("WC")) {
                                kodUrusanPend = "TTB";
                            } else if (keputusan.equalsIgnoreCase("WD")) {
                                kodUrusanPend = "TTTK";
                            }
                        }
                    } else if (stageId.equalsIgnoreCase("keputusan_bantahan")) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                            if (keputusan.equalsIgnoreCase("WK")) {
                                kodUrusanPend = "TTWKP";
                            } else if (keputusan.equalsIgnoreCase("WL")) {
                                kodUrusanPend = "TTWLM";
                            } else if (keputusan.equalsIgnoreCase("WM")) {
                                kodUrusanPend = "TTWLB";
                            }
                        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                            if (keputusan.equalsIgnoreCase("WD")) {
                                kodUrusanPend = "TTTK";
                            }
                        }
                    }
                } else if (kodUrusan.equals("127")) {
                    if (stageId.equalsIgnoreCase("arah_endorsan7a")) { //DONE
                        kodUrusanPend = "N7A";
                    } else if (stageId.equalsIgnoreCase("kpsn_pemantauan4")) { //DONE
                        if (keputusan.equalsIgnoreCase("RE")) {
                            kodUrusanPend = "N7AB";
                        }
                    } else if (stageId.equalsIgnoreCase("arah_endorsan7b")) { //DONE
                        kodUrusanPend = "N7B";
                    } else if (stageId.equalsIgnoreCase("kpsn_pemantauan5")) { //DONE
                        if (keputusan.equalsIgnoreCase("RE")) {
                            kodUrusanPend = "N7BB";
                        }
                    } else if (stageId.equalsIgnoreCase("arah_endorsan8a")) { //DONE
                        kodUrusanPend = "N8A";
                    } else if (stageId.equalsIgnoreCase("arah_btl_endorsan")) {
                        kodUrusanPend = "N8AB";
                    } else if (stageId.equalsIgnoreCase("keputusan_mmk")) { //DONE
                        if (keputusan.equalsIgnoreCase("DA")) {
                            kodUrusanPend = "PSPBN";
                        }
                    } else if (stageId.equalsIgnoreCase("maklum_bayaran_kos_remedi")) { //DONE
                        kodUrusanPend = "PSPBB";
                    }
                } else if (kodUrusan.equals("49")) {
                    if (stageId.equalsIgnoreCase("keputusan2")) {
                        if (keputusan.equalsIgnoreCase("HS")) {
                            kodUrusanPend = "STMA";
                        } else if (keputusan.equalsIgnoreCase("HK")) {
                            kodUrusanPend = "TMA";
                        }
                    } else if (stageId.equalsIgnoreCase("arah_endorsan_hksta")) {
                        kodUrusanPend = "HKSTA";
                    } else if (stageId.equalsIgnoreCase("arah_endorsan_tmak")) {
                        kodUrusanPend = "TMAK";
                    }
                }

                if (!kodUrusanPend.isEmpty()) {
                    initiateTugasanPendaftaran(permohonan, peng, kodUrusanPend, context);

                    List<Permohonan> p = enforceService.findIdPerserahanByKodUrusan(permohonan.getIdPermohonan(), kodUrusanPend);
                    if (!p.isEmpty()) {
                        message = " - Penghantaran Berjaya. Id Perserahan untuk pendaftaran: " + p.get(0).getIdPermohonan();
                    }
                }

                updateKeputusan(permohonan, peng, stageId);
                KodUrusan kod = kodUrusanDAO.findById(kodUrusanPend);

                Notifikasi n = new Notifikasi();
                List<HakmilikPermohonan> senaraiHakmilik = context.getPermohonan().getSenaraiHakmilik();
                String stageId = context.getStageName();

                if (!senaraiHakmilik.isEmpty()) {
                    if (kodUrusan.equals("127")) {
                        System.out.println("masuk 127");

                        HakmilikPermohonan hm = senaraiHakmilik.get(0);

                        if (hm != null) {
                            n.setTajuk("Maklum endorsan bagi urusan dibawah Seksyen 127 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                            n.setMesej("Dimaklumkan bahawa satu endorsan " + ((kod != null) ? kod.getNama() : "") + " akan dibuat "
                                    + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                    + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                    + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                    + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
                        }
                    } else if (kodUrusan.equals("351")) {
                        System.out.println("masuk 351");
                        HakmilikPermohonan hm = senaraiHakmilik.get(0);
                        //FasaPermohonan fp = enforcementService.findByStageId(context.getPermohonan().getIdPermohonan(), stageId);

                        if (hm != null) {
                            if (keputusan != null) {

                                if (keputusan.equals("XT")) {
                                    n.setTajuk("Satu Makluman bantahan bagi urusan dibawah Seksyen 351 KTN (ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ")");
                                    n.setMesej("Satu bantahan telah diterima  "
                                            + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                            + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                            + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                            + " Daerah " + hm.getHakmilik().getDaerah().getNama() + " dan masih dalam proses siasatan lanjutan.");
                                } else {
                                    n.setTajuk("Maklum endorsan bagi urusan dibawah Seksyen 351 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                                    n.setMesej("Dimaklumkan bahawa satu endorsan " + ((kod != null) ? kod.getNama() : "") + " akan dibuat "
                                            + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                            + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                            + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                            + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
                                }
                            } else {
                                n.setTajuk("Maklum endorsan bagi urusan dibawah Seksyen 351 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                                n.setMesej("Dimaklumkan bahawa satu endorsan " + ((kod != null) ? kod.getNama() : "") + " akan dibuat "
                                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
                            }
                        }
                    } else if (kodUrusan.equals("352")) {
                        System.out.println("masuk 352");
                        HakmilikPermohonan hm = senaraiHakmilik.get(0);
                        //FasaPermohonan fp = enforcementService.findByStageId(context.getPermohonan().getIdPermohonan(), stageId);

                        if (hm != null) {
                            if (keputusan.equals("XT")) {
                                n.setTajuk("Satu Makluman bantahan bagi urusan dibawah Seksyen 352 KTN (ID Permohonan : " + context.getPermohonan().getIdPermohonan() + ")");
                                n.setMesej("Satu bantahan telah diterima  "
                                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + " dan masih dalam proses siasatan lanjutan.");
                            } else {
                                n.setTajuk("Maklum endorsan bagi urusan dibawah Seksyen 352 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                                n.setMesej("Dimaklumkan bahawa satu endorsan " + ((kod != null) ? kod.getNama() : "") + " akan dibuat "
                                        + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                        + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                        + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                        + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
                            }
                        }
                    } else if (kodUrusan.equals("49")) {
                        System.out.println("masuk 49");

                        HakmilikPermohonan hm = senaraiHakmilik.get(0);

                        if (hm != null) {
                            n.setTajuk("Maklum endorsan bagi urusan dibawah Seksyen 49 KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                            n.setMesej("Dimaklumkan bahawa satu endorsan " + ((kod != null) ? kod.getNama() : "") + " akan dibuat "
                                    + " bagi ID Permohonan : " + context.getPermohonan().getIdPermohonan()
                                    + " yang melibatkan pemilik " + hm.getHakmilik().getLot().getNama() + " " + hm.getHakmilik().getNoLot()
                                    + " Mukim " + hm.getHakmilik().getBandarPekanMukim().getNama()
                                    + " Daerah " + hm.getHakmilik().getDaerah().getNama() + ".");
                        }

                    }
                }

                //Notify to PTG
                System.out.println("notify");
                Pengguna p = context.getPengguna();
                KodCawangan cawangan = kodCawanganDAO.findById("00");
                n.setCawangan(cawangan);
                ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                //list.add(kodPerananDAO.findById("234")); //N9 - PTG (PAT)
                list.add(kodPerananDAO.findById("12")); //N9 - PTG (FAT)
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(new Date());
                n.setInfoAudit(ia);
                notifikasiService.addRolesToNotifikasi(n, cawangan, list);
                message += " dan makluman kepada Pengarah Tanah dan Galian telah dihantar.";
                System.out.println("notify 2");

                context.addMessage(message);
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    if (stageId.equalsIgnoreCase("arah_endorsan")) {
                        initialUrusan = true;
                    } else if (stageId.equalsIgnoreCase("bantahan_1tahun")) {
                        if (keputusan.equalsIgnoreCase("XT")) { //XT = Tiada Bantahan
                            createPermohonanPendaftaran(permohonan, peng, initialUrusan);
                        }

                    }
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
        //return null;
    }

    public void generatePemohonUsingIDMohonPendaftaran(Permohonan permohonan, Pengguna pengguna, String kodUrusan) {
        LOG.info("-------generatePemohonUsingIDMohonPendaftaran for id mohon ::: " + permohonan.getIdPermohonan());

        try {
            Pemohon pemohonEnf = enforcementService.findPemohonByIDMohon(permohonan.getIdPermohonan());
            InfoAudit ia = new InfoAudit();

            if (pemohonEnf != null) {
                Pemohon pemohonPend = new Pemohon();
                pemohonPend.setCawangan(new KodCawangan());
                pemohonPend.setPermohonan(new Permohonan());

                Permohonan permohonanPend = enforcementService.findByIDSebelum(permohonan.getIdPermohonan(), kodUrusan);
                LOG.info("-------generatePemohonUsingIDMohonPendaftaran for id mohon ::: " + permohonanPend.getIdPermohonan());
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

                pemohonPend.getCawangan().setKod(pemohonEnf.getCawangan().getKod());
                pemohonPend.getPermohonan().setIdPermohonan(permohonanPend.getIdPermohonan());
                pemohonPend.setNama(pemohonEnf.getNama());
                pemohonPend.setInfoAudit(ia);
                pemohonPend.setAlamat(pemohonEnf.getAlamat());
                pemohonPend.setAlamatSurat(pemohonEnf.getAlamatSurat());
                LOG.info("-------pemohonPend ::: " + pemohonPend);

                enforcementService.simpanMaklumatPihak(pemohonPend);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public void initiateTugasanPendaftaran(Permohonan permohonan, Pengguna pengguna, String kodUrusan, StageContext context) {
        LOG.info("-------initiateTugasanPendaftaran for kod urusan ::: " + kodUrusan);
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById(kodUrusan);
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());
        if (conf.getProperty("kodNegeri").equalsIgnoreCase("05")) {
            if(kod.getKodPerserahan().getKod().equalsIgnoreCase("MH")){
                generateIdPerserahanWorkflow1.generateIdPerserahanWorkflowMH(kod, pengguna, senaraiHakmilik, permohonan, context.getStageName());
            }else{
                generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, pengguna, senaraiHakmilik, permohonan, context.getStageName());
            }
        } else {
            generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, pengguna, senaraiHakmilik, permohonan, context.getStageName());
        }
    }

    public void createPermohonanPendaftaran(Permohonan permohonan, Pengguna pengguna, Boolean initialUrusan) {
        System.out.println("initial urusan ::::::::::::" + initialUrusan);
        permohonanPihakList = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan());
        listPemohon = enforcementService.findListPemohon(permohonan.getIdPermohonan());
        hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
        ArrayList senaraiHakmilikPermohonan = new ArrayList<String>();
        HashSet hsTTWB = new HashSet();
        HashSet hsTTWKP = new HashSet();
        HashSet hsTTWLM = new HashSet();
        HashSet hsTTWLB = new HashSet();

        if (!hakmilikPermohonanList.isEmpty()) {
            for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                if (hp.getHakmilik() != null) {
                    senaraiHakmilikPermohonan.add(hp.getHakmilik().getIdHakmilik());
                }
            }

            if (!senaraiHakmilikPermohonan.isEmpty()) {
                for (Pemohon pm : listPemohon) {
                    if (StringUtils.isNotBlank(pm.getDalamanNilai1())) {
                        if (pm.getDalamanNilai1().equalsIgnoreCase("TTWB")) {
                            listPihakPemohon = enforcementService.findPihakTerlibat(senaraiHakmilikPermohonan, permohonan, "IN", pm.getDalamanNilai1());
                            for (HakmilikPihakBerkepentingan hpb : listPihakPemohon) {
                                if (hpb.getHakmilik() != null) {
                                    urusanTTWB = true;
                                    hsTTWB.add(hpb.getHakmilik().getIdHakmilik());
                                }
                            }
                        } else if (pm.getDalamanNilai1().equalsIgnoreCase("TTWKP")) {
                            listPihakPemohon = enforcementService.findPihakTerlibat(senaraiHakmilikPermohonan, permohonan, "IN", pm.getDalamanNilai1());
                            for (HakmilikPihakBerkepentingan hpb : listPihakPemohon) {
                                if (hpb.getHakmilik() != null) {
                                    urusanTTWKP = true;
                                    hsTTWKP.add(hpb.getHakmilik().getIdHakmilik());
                                }
                            }
                        } else if (pm.getDalamanNilai1().equalsIgnoreCase("TTWLM")) {
                            listPihakPemohon = enforcementService.findPihakTerlibat(senaraiHakmilikPermohonan, permohonan, "IN", pm.getDalamanNilai1());
                            for (HakmilikPihakBerkepentingan hpb : listPihakPemohon) {
                                if (hpb.getHakmilik() != null) {
                                    urusanTTWLM = true;
                                    hsTTWLM.add(hpb.getHakmilik().getIdHakmilik());
                                }
                            }
                        } else if (pm.getDalamanNilai1().equalsIgnoreCase("TTWLB")) {
                            listPihakPemohon = enforcementService.findPihakTerlibat(senaraiHakmilikPermohonan, permohonan, "IN", pm.getDalamanNilai1());
                            for (HakmilikPihakBerkepentingan hpb : listPihakPemohon) {
                                if (hpb.getHakmilik() != null) {
                                    urusanTTWLB = true;
                                    hsTTWLB.add(hpb.getHakmilik().getIdHakmilik());
                                }
                            }
                        }
                    }
                }
            }

        }

        for (PermohonanPihak pp : permohonanPihakList) {
            if (StringUtils.isNotBlank(pp.getDalamanNilai1())) {
                if (pp.getDalamanNilai1().equalsIgnoreCase("TTWB")) {
                    hsTTWB.add(pp.getHakmilik().getIdHakmilik());
                    urusanTTWB = true;
                } else if (pp.getDalamanNilai1().equalsIgnoreCase("TTWKP")) {
                    hsTTWKP.add(pp.getHakmilik().getIdHakmilik());
                    urusanTTWKP = true;
                } else if (pp.getDalamanNilai1().equalsIgnoreCase("TTWLM")) {
                    hsTTWLM.add(pp.getHakmilik().getIdHakmilik());
                    urusanTTWLM = true;
                } else if (pp.getDalamanNilai1().equalsIgnoreCase("TTWLB")) {
                    hsTTWLB.add(pp.getHakmilik().getIdHakmilik());
                    urusanTTWLB = true;
                }
            }
        }
        KodUrusan kod = null;
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        if (urusanTTWB == true) {
            listPemohonPihak = enforcementService.findListPemohon(permohonan.getIdPermohonan(), "TTWB");
            listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), "TTWB");

            if (initialUrusan == true) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    LOG.info("::value TTW for TTWB ::");
                    kod = kodUrusanDAO.findById("TTW");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    kod = kodUrusanDAO.findById("TT");
                }
            } else {
                kod = kodUrusanDAO.findById("TTWB");
            }

            Iterator it = hsTTWLM.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                Hakmilik h = hakmilikDAO.findById(value);
                senaraiHakmilik.add(h);
                LOG.info("::value TTWB : " + value);
            }

            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahan(kod, pengguna, senaraiHakmilik, permohonan, listPemohonPihak, listPermohonanPihak);
        }

        if (urusanTTWKP == true) {
            listPemohonPihak = enforcementService.findListPemohon(permohonan.getIdPermohonan(), "TTWKP");
            listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), "TTWKP");

            if (initialUrusan == true) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    LOG.info("::value TTWKP for TTW ::");
                    kod = kodUrusanDAO.findById("TTW");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    kod = kodUrusanDAO.findById("TT");
                }
            } else {
                kod = kodUrusanDAO.findById("TTWKP");
            }
            Iterator it = hsTTWKP.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                Hakmilik h = hakmilikDAO.findById(value);
                senaraiHakmilik.add(h);
                LOG.info("::value TTWKP : " + value);
            }
            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahan(kod, pengguna, senaraiHakmilik, permohonan, listPemohonPihak, listPermohonanPihak);
        }

        if (urusanTTWLM == true) {
            listPemohonPihak = enforcementService.findListPemohon(permohonan.getIdPermohonan(), "TTWLM");
            listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), "TTWLM");

            if (initialUrusan == true) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    LOG.info("::value TTWLM for TTW ::");
                    kod = kodUrusanDAO.findById("TTW");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    kod = kodUrusanDAO.findById("TT");
                }
            } else {
                kod = kodUrusanDAO.findById("TTWLM");
            }
            Iterator it = hsTTWLM.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                Hakmilik h = hakmilikDAO.findById(value);
                senaraiHakmilik.add(h);
                LOG.info("::value TTWLM : " + value);
            }

            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahan(kod, pengguna, senaraiHakmilik, permohonan, listPemohonPihak, listPermohonanPihak);
        }

        if (urusanTTWLB == true) {
            listPemohonPihak = enforcementService.findListPemohon(permohonan.getIdPermohonan(), "TTWLB");
            listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), "TTWLB");

            if (initialUrusan == true) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    LOG.info("::value TTW for TTWLB ::");
                    kod = kodUrusanDAO.findById("TTW");
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    kod = kodUrusanDAO.findById("TT");
                }
            } else {
                kod = kodUrusanDAO.findById("TTWLB");
            }
            Iterator it = hsTTWLB.iterator();
            while (it.hasNext()) {
                String value = (String) it.next();
                Hakmilik h = hakmilikDAO.findById(value);
                senaraiHakmilik.add(h);
                LOG.info("::value TTWLB : " + value);
            }

            LOG.info(kod.getNama());
            LOG.info(permohonan.getFolderDokumen());
            generateIdPerserahanWorkflow1.generateIdPerserahan(kod, pengguna, senaraiHakmilik, permohonan, listPemohonPihak, listPermohonanPihak);
        }

    }

    public void updateKeputusan(Permohonan permohonan, Pengguna pengguna, String stageId) {
        LOG.info("-------updateKeputusan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            FasaPermohonan fp = enforcementService.findByStageId(permohonan.getIdPermohonan(), stageId);

            if (fp != null) {
                if (fp.getKeputusan() != null) {
                    if (!fp.getKeputusan().getKod().isEmpty()) {
                        permohonan.setKeputusan(fp.getKeputusan());
                        permohonan.setTarikhKeputusan(fp.getTarikhKeputusan());
                    }
                }
            }

            permohonan.setKeputusanOleh(ia.getDikemaskiniOleh());
            permohonan.setInfoAudit(ia);
            enforceService.simpanPermohonan(permohonan);

        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        LOG.info("::: validate nota tindakan beforePushBack :::");
        Permohonan permohonan = context.getPermohonan();
        stageId = context.getStageName();

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota != null) {
            LOG.info("::: kandungan nota :" + permohonanNota.getNota());
            context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
            return null;
        } else {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }

        return "back";
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota == null) {
            LOG.info("::: kandungan nota tidak null ::: ");
            PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
            if (nota != null) {
                LOG.info("::: update status nota to T = tidak aktif ::: ");
                nota.setStatusNota('T');
                enforceService.simpanNota(nota);
            }
        }

        /*
         FasaPermohonan fasaPermohonan = enforcementService.findByStageId(permohonan.getIdPermohonan(), stageId);
         permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
         permohonan.setTarikhKeputusan(fasaPermohonan.getTarikhKeputusan());
         enforceService.savePermohonan(permohonan);
         */
    }
}

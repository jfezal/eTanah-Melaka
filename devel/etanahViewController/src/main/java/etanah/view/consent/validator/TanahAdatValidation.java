
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KandunganFolder;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanPerbicaraan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.ConsentPtdService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class TanahAdatValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakPihakService;
    private static String[] KodPengenalanIndividu = {"B", "L", "P", "P", "I", "F", "K"};
    private static final Logger LOG = Logger.getLogger(TanahAdatValidation.class);

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
        String stageId = context.getStageName();

        try {
            //FOR URUSAN TANAH ADAT PMADT, TMADT, CGADT, PAADT
            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/ADAT")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/tmwna")) {

                if (stageId.equals("Stage1")) {

                    // if(v == "B" || v == "L" || v == "P" || v == "T" || v == "I" || v == "F" || v == "K"){

                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {

                        if (pemohon.getPihak().getJenisPengenalan() != null) {
                            String jenisKP = pemohon.getPihak().getJenisPengenalan().getKod();
                            if (jenisKP.equals("B") || jenisKP.equals("L") || jenisKP.equals("P") || jenisKP.equals("T") || jenisKP.equals("I") || jenisKP.equals("F") || jenisKP.equals("K")) {
                                if (pemohon.getUmur() == null) {
                                    context.addMessage("Sila masukkan umur pada pemohon " + WordUtils.capitalizeFully(pemohon.getPihak().getNama()) + " untuk permohonan : " + permohonan.getIdPermohonan());
                                    return null;
                                }
                            }
                        }
                    }

                    List<PermohonanPihak> listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKod(permohonan.getIdPermohonan(), "TER");

                    for (PermohonanPihak mohonPihak : listMohonPihak) {

                        if (mohonPihak.getPihak().getJenisPengenalan() != null) {
                            String jenisKP = mohonPihak.getPihak().getJenisPengenalan().getKod();
                            if (jenisKP.equals("B") || jenisKP.equals("L") || jenisKP.equals("P") || jenisKP.equals("T") || jenisKP.equals("I") || jenisKP.equals("F") || jenisKP.equals("K")) {
                                if (mohonPihak.getUmur() == null) {

                                    String jenisPihak;
                                    if (mohonPihak.getJenis().getKod().equals("WAR")) {
                                        jenisPihak = "waris ";
                                    } else {
                                        jenisPihak = "penerima ";
                                    }

                                    context.addMessage("Sila masukkan umur pada " + jenisPihak + WordUtils.capitalizeFully(mohonPihak.getPihak().getNama()) + " untuk permohonan : " + permohonan.getIdPermohonan());
                                    return null;
                                }
                            }
                        }
                    }

                    PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LEMBAGA");
                    if (permohonanUrusan == null || permohonanUrusan.getCatatan() == null) {
                        context.addMessage("Sila Masukkan Maklumat Datok Lembaga : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage3")) {
                    PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();
                    permohonanPerbicaraan = consentService.findMohonBicaraNotTangguh(permohonan.getIdPermohonan());

                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("TQ")) {
                            context.addMessage("Sila Masukkan Tarikh Bicara : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    } else if (permohonanPerbicaraan == null) {
                        context.addMessage("Sila Masukkan Tarikh Bicara : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage4")) {
                    boolean reportFound = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("NB")) {
                            reportFound = true;
                        }
                    }
                    if (!reportFound) {
                        context.addMessage("Sila Jana Laporan Terlebih Dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }

                    List<PermohonanPerbicaraan> listPerbicaraanTangguh = consentService.findSenaraiMohonBicaraTangguh(permohonan.getIdPermohonan(), "TANGGUH");
                    if (listPerbicaraanTangguh.size() > 0) {
                        reportFound = false;
                        for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                            if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("STBI")) {
                                reportFound = true;
                            }
                        }
                        if (!reportFound) {
                            context.addMessage("Sila Jana Laporan Terlebih Dahulu : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }

                } else if (stageId.equals("Stage6")) {
                    FasaPermohonan fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage6");
                    if (fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("TQ")) {
                            List<PermohonanPerbicaraan> mohonBicaraList = consentService.findSenaraiMohonBicaraTangguh(permohonan.getIdPermohonan(), "TANGGUH");
                            if (mohonBicaraList.size() > 5) {
                                context.addMessage("Permohonan ini telah ditangguh melebihi 5 kali : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        } else {

                            for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                                List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());

                                if (listPenerima.isEmpty()) {
                                    context.addMessage("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                                    return null;
                                }

                                if (!permohonan.getSenaraiPihak().isEmpty()) {
                                    for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                                        if (!perPihak.getJenis().getKod().equals("TER") && !perPihak.getJenis().getKod().equals("WAR") && !perPihak.getJenis().getKod().equals("PA")) {
                                            if (perPihak.getSyerPembilang() == null || perPihak.getSyerPenyebut() == null) {
                                                context.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                                return null;
                                            } else if (perPihak.getSyerPembilang() == 0 || perPihak.getSyerPenyebut() == 0) {
                                                context.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                                return null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } //FOR URUSAN TANAH PMWNA AND PMWWA
            else if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/pmwna")) {

                if (stageId.equals("Stage14")) {

                    //CLEAR KEPUTUSAN TERDAHULU DALAM TABLE PERMOHONAN
                    if (permohonan.getKeputusan() != null) {
                        permohonan.setKeputusan(null);
                        consentService.simpanPermohonan(permohonan);
                    }

                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {

                        if (pemohon.getPihak().getJenisPengenalan() != null) {
                            String jenisKP = pemohon.getPihak().getJenisPengenalan().getKod();
                            if (jenisKP.equals("B") || jenisKP.equals("L") || jenisKP.equals("P") || jenisKP.equals("T") || jenisKP.equals("I") || jenisKP.equals("F") || jenisKP.equals("K")) {
                                if (pemohon.getUmur() == null) {
                                    context.addMessage("Sila masukkan umur pada pemohon " + WordUtils.capitalizeFully(pemohon.getPihak().getNama()) + " untuk permohonan : " + permohonan.getIdPermohonan());
                                    return null;
                                }
                            }
                        }
                    }

                    List<PermohonanPihak> listMohonPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKod(permohonan.getIdPermohonan(), "TER");

                    for (PermohonanPihak mohonPihak : listMohonPihak) {

                        if (mohonPihak.getPihak().getJenisPengenalan() != null) {
                            String jenisKP = mohonPihak.getPihak().getJenisPengenalan().getKod();
                            if (jenisKP.equals("B") || jenisKP.equals("L") || jenisKP.equals("P") || jenisKP.equals("T") || jenisKP.equals("I") || jenisKP.equals("F") || jenisKP.equals("K")) {
                                if (mohonPihak.getUmur() == null) {

                                    String jenisPihak;
                                    if (mohonPihak.getJenis().getKod().equals("WAR")) {
                                        jenisPihak = "waris ";
                                    } else {
                                        jenisPihak = "penerima ";
                                    }

                                    context.addMessage("Sila masukkan umur pada " + jenisPihak + WordUtils.capitalizeFully(mohonPihak.getPihak().getNama()) + " untuk permohonan : " + permohonan.getIdPermohonan());
                                    return null;
                                }
                            }
                        }
                    }


                    PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LEMBAGA");
                    if (permohonanUrusan == null || permohonanUrusan.getCatatan() == null) {
                        context.addMessage("Sila Masukkan Maklumat Datok Lembaga : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage16")) {
                    PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();
                    permohonanPerbicaraan = consentService.findMohonBicaraNotTangguh(permohonan.getIdPermohonan());

                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("TQ")) {
                            context.addMessage("Sila Masukkan Tarikh Bicara : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    } else if (permohonanPerbicaraan == null) {
                        context.addMessage("Sila Masukkan Tarikh Bicara : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage17")) {
                    boolean reportFound = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("NB")) {
                            reportFound = true;
                        }
                    }
                    if (!reportFound) {
                        context.addMessage("Sila Jana Laporan Terlebih Dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage19")) {
                    FasaPermohonan fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage19");
                    if (fasaPermohonan.getKeputusan() != null) {
                        if (fasaPermohonan.getKeputusan().getKod().equals("TQ")) {
                            List<PermohonanPerbicaraan> mohonBicaraList = consentService.findSenaraiMohonBicaraTangguh(permohonan.getIdPermohonan(), "TANGGUH");
                            if (mohonBicaraList.size() > 5) {
                                context.addMessage("Permohonan ini telah ditangguh melebihi 5 kali : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        } else {

                            for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                                List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());

                                if (listPenerima.isEmpty()) {
                                    context.addMessage("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                                    return null;
                                }

                                if (!permohonan.getSenaraiPihak().isEmpty()) {
                                    for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                                        if (!perPihak.getJenis().getKod().equals("TER") && !perPihak.getJenis().getKod().equals("WAR") && !perPihak.getJenis().getKod().equals("PA")) {
                                            if (perPihak.getSyerPembilang() == null || perPihak.getSyerPenyebut() == null) {
                                                context.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                                return null;
                                            } else if (perPihak.getSyerPembilang() == 0 || perPihak.getSyerPenyebut() == 0) {
                                                context.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                                return null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (stageId.equals("Stage21")) {

                    boolean checkReport = false;
                    String kodDokumen = "PBI";

                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                            checkReport = true;
                        }
                    }
                    if (!checkReport) {
                        context.addMessage("Sila Jana Perintah Terlebih Dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
            } //FOR URUSAN BANTAHAN TANAH ADAT
            else if (permohonan.getKodUrusan().getKod().equals("BTADT")) {
                if (stageId.equals("Stage1")) {

                    PermohonanUrusan mohonUrusanSebelum = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "URUSAN BANTAHAN");
                    if (mohonUrusanSebelum == null) {
                        context.addMessage("Sila Masukkan Maklumat Permohonan Terdahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                    for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                        List<PermohonanPihak> listPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hakmilik.getIdHakmilik());

                        if (listPihakTerlibat.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat pihak terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (permohonan.getSenaraiPemohon().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }

                    for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                        if (perPihak.getJenis().getKod().equals("TER")) {
                            if (perPihak.getPihak().getJenisPengenalan() != null) {
                                if (perPihak.getPihak().getJenisPengenalan().getKod().equals("B") || perPihak.getPihak().getJenisPengenalan().getKod().equals("L") || perPihak.getPihak().getJenisPengenalan().getKod().equals("P") || perPihak.getPihak().getJenisPengenalan().getKod().equals("T") || perPihak.getPihak().getJenisPengenalan().getKod().equals("D") || perPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
                                    if (perPihak.getPihak().getSuku() == null) {
                                        context.addMessage("Sila masukkan maklumat suku pada pihak terlibat atau tuan tanah : " + permohonan.getIdPermohonan());
                                        return null;
                                    }
                                }
                            }
                        }
                    }

                    if (permohonan.getSenaraiHakmilik().size() == 1) {
                        HakmilikPermohonan hakPermohonan = permohonan.getSenaraiHakmilik().get(0);
                        List<HakmilikPihakBerkepentingan> hakPihakList = hakPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakPermohonan.getHakmilik());

                        for (HakmilikPihakBerkepentingan hakPihak : hakPihakList) {
                            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                                if (hakPihak.getPihak().getIdPihak() == pemohon.getPihak().getIdPihak()) {
                                    if (hakPihak.getPihak().getJenisPengenalan() != null) {
                                        if (hakPihak.getPihak().getJenisPengenalan().getKod().equals("B") || hakPihak.getPihak().getJenisPengenalan().getKod().equals("L") || hakPihak.getPihak().getJenisPengenalan().getKod().equals("P") || hakPihak.getPihak().getJenisPengenalan().getKod().equals("T") || hakPihak.getPihak().getJenisPengenalan().getKod().equals("D") || hakPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
                                            if (hakPihak.getPihak().getSuku() == null) {
                                                context.addMessage("Sila masukkan maklumat suku pada tuan tanah : " + permohonan.getIdPermohonan());
                                                return null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LEMBAGA");
                    if (permohonanUrusan == null || permohonanUrusan.getCatatan() == null) {
                        context.addMessage("Sila Masukkan Maklumat Datok Lembaga : " + permohonan.getIdPermohonan());
                        return null;
                    }

                    PermohonanUrusan mohonUrusanUndang = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "UNDANG LUAK");
                    if (mohonUrusanUndang != null) {
                        if (mohonUrusanUndang.getCatatan() == null) {
                            context.addMessage("Sila Masukkan Maklumat Undang Luak : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    } else {
                        context.addMessage("Sila Masukkan Maklumat Undang Luak : " + permohonan.getIdPermohonan());
                        return null;
                    }

                } else if (stageId.equals("Stage4") || stageId.equals("Stage5") || stageId.equals("Stage8")) {

                    PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());

                    if (permohonanKertas == null) {
                        context.addMessage("Sila masukkan maklumat kertas kerja MMK untuk : " + permohonan.getIdPermohonan());
                        return null;
                    } else {
                        boolean ulasanComplete = true;
                        if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                                PermohonanKertasKandungan kertasKdgn = null;
                                kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                                //kertas kerja MMK-PT
                                if (stageId.equals("Stage4")) {

                                    if (kertasKdgn.getBil() == 2 || kertasKdgn.getBil() == 3 || kertasKdgn.getBil() == 4) {

                                        if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                            ulasanComplete = false;
                                            break;
                                        }
                                    }
                                    //kertas kerja MMK-PTD    
                                } else if (stageId.equals("Stage5")) {
                                    if (kertasKdgn.getBil() == 5 || kertasKdgn.getBil() == 3 || kertasKdgn.getBil() == 6) {

                                        if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                            ulasanComplete = false;
                                            break;
                                        }
                                    }
                                }//kertas kerja MMK-PTG   
                                else if (stageId.equals("Stage8")) {
                                    if (kertasKdgn.getBil() == 7 || kertasKdgn.getBil() == 8) {

                                        if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                            ulasanComplete = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        try {

                            if (!ulasanComplete) {
                                context.addMessage("Sila masukkan maklumat kertas kerja MMK untuk : " + permohonan.getIdPermohonan());
                                return null;
                            }

                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                            return null;
                        }
                    }
                } else if (stageId.equals("Stage11")) {
                    PermohonanPerbicaraan permohonanPerbicaraan = new PermohonanPerbicaraan();
                    permohonanPerbicaraan = consentService.findMohonBicaraNotTangguh(permohonan.getIdPermohonan());

                    if (permohonanPerbicaraan == null) {
                        context.addMessage("Sila Masukkan Tarikh Bicara : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals("Stage14") || stageId.equals("Stage17")) {

                    PermohonanKertas permohonanKertas = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "MMK KEDUA");

                    if (permohonanKertas == null) {
                        context.addMessage("Sila masukkan maklumat kertas kerja MMK untuk : " + permohonan.getIdPermohonan());
                        return null;
                    } else {
                        String error = null;
                        boolean ulasanComplete = true;
                        if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                                PermohonanKertasKandungan kertasKdgn = null;

                                kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                                //kertas kerja mmk-pt
                                if (stageId.equals("Stage14")) {

                                    if (kertasKdgn.getBil() == 5 || kertasKdgn.getBil() == 6) {

                                        if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                            ulasanComplete = false;
                                            error = "Sila masukkan huraian dan syor Pentadbir Tanah";
                                            break;
                                        }
                                    }
                                    //kertas kerja mmk-ptd    
                                } else if (stageId.equals("Stage17")) {
                                    if (kertasKdgn.getBil() == 7 || kertasKdgn.getBil() == 8) {

                                        if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                            ulasanComplete = false;
                                            error = "Sila masukkan huraian dan syor Pengarah Tanah Dan Galian";
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        try {

                            if (!ulasanComplete) {
                                context.addMessage(error + " pada kertas kerja MMK untuk : " + permohonan.getIdPermohonan());
                                return null;
                            }

                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                            return null;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
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
        return "back";
    }
}

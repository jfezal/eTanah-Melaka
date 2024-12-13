
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
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanUrusan;
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
public class PihakBerkepentinganValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPihakKepentinganService hakPihakService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    private static final Logger LOG = Logger.getLogger(PihakBerkepentinganValidation.class);

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

        try {
            
            //FOR URUSAN PMMAT CONSENT AND DPWNA
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PMMAT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("DPWNA")) {

                PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanByIdMohon(permohonan.getIdPermohonan());

                if (permohonanRujukanLuar == null) {
                    context.addMessage("Sila masukkan maklumat perintah pesaka yang hendak didaftarkan untuk ID Permohonan : " + permohonan.getIdPermohonan());
                    return null;
                } else {
                    if (permohonanRujukanLuar.getNoRujukan() == null) {
                        context.addMessage("Sila masukkan maklumat perintah pesaka yang hendak didaftarkan untuk ID Permohonan : " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
            }
            //FOR URUSAN SERENTAK
            if (permohonan.getKodUrusan().getKod().equals("PCPTD") || permohonan.getKodUrusan().getKod().equals("PCMMK") || permohonan.getKodUrusan().getKod().equals("PGDMB")) {

                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                    List<PermohonanPihak> listPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hakmilik.getIdHakmilik());
                    List<PermohonanPihak> listPenerimaPindahmilik = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "PPM", hakmilik.getIdHakmilik());
                    List<PermohonanPihak> listPenerimaGadaian = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "PGA", hakmilik.getIdHakmilik());
                    List<HakmilikPihakBerkepentingan> senaraiKeempunyaan = hakPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilik);
                    if (senaraiKeempunyaan.size() == 1) {
                        if (permohonan.getSenaraiPemohon().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (listPenerimaPindahmilik.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat Penerima Pindahmilik untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (listPenerimaGadaian.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat Penerima Gadaian untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    } else {
                        if (listPihakTerlibat.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat Pihak Terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (permohonan.getSenaraiPemohon().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (listPenerimaPindahmilik.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat Penerima Pindahmilik untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (listPenerimaGadaian.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat Penerima Gadaian untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }

            } else {
                //FOR URUSAN BIASA
                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                    List<PermohonanPihak> listPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hakmilik.getIdHakmilik());
                    List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());
//                    List<HakmilikPihakBerkepentingan> senaraiKeempunyaan = hakPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hakmilik);
//                    if (senaraiKeempunyaan.size() == 1) {
                    if (permohonan.getSenaraiPemohon().isEmpty()) {
                        context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                        return null;
                    } else if (listPihakTerlibat.isEmpty()) {
                        context.addMessage("Sila masukkan maklumat pihak terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                        return null;
                    } else if (listPenerima.isEmpty()) {
                        if (!permohonan.getKodUrusan().getKod().equals("TMADT") && !permohonan.getKodUrusan().getKod().equals("TMWNA")) {
                            context.addMessage("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
//                    }
//                    else {
//                        if (listPihakTerlibat.isEmpty()) {
//                            context.addMessage("Sila masukkan maklumat pihak terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
//                            return null;
//                        } else if (permohonan.getSenaraiPemohon().isEmpty()) {
//                            context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
//                            return null;
//                        } else if (listPenerima.isEmpty()) {
//                            if (!permohonan.getKodUrusan().getKod().equals("TMADT") && !permohonan.getKodUrusan().getKod().equals("TMWNA")) {
//                                context.addMessage("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
//                                return null;
//                            }
//                        }
//                    }
                }
            }

            //VALIDATE PEMEGANG AMANAH
            for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());

                for (PermohonanPihak permohonanPihak : listPenerima) {
                    if (permohonanPihak.getJenis().getKod().equals("PA")) {
                        if (permohonanPihak.getSenaraiHubungan().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat penerima amanah kepada " + permohonanPihak.getPihak().getNama() + " untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }
            }

            //VALIDATE ALAMAT PEMEHON
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                if (pemohon.getAlamatSurat() == null) {
                    context.addMessage("Sila masukkan alamat surat bagi pemohon " + WordUtils.capitalizeFully(pemohon.getPihak().getNama()) + " untuk permohonan : " + permohonan.getIdPermohonan());
                    return null;
                }
            }

            //VALIDATOR FOR URUSAN EMMKN NEGERI MELAKA

            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("ConsentMelaka/MMK_melaka") || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent3/Project1/mmk2")) {

                if (context.getStageName().equals("Stage1")) {
                    if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent3/Project1/mmk2")) {

                        for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                            if (perPihak.getJenis().getKod().equals("TER")) {
                                if (perPihak.getPihak().getJenisPengenalan() != null) {
                                    if (perPihak.getPihak().getJenisPengenalan().getKod().equals("B")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("L")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("P")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("T")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("D")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("I")
                                            || perPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
                                        if (perPihak.getPihak().getTarikhMati() == null || perPihak.getPihak().getNoSijilMati() == null) {
                                            context.addMessage("Sila masukkan maklumat kematian pada pihak terlibat dengan lengkap : " + permohonan.getIdPermohonan());
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                    if (pemohon.getPihak().getJenisPengenalan().getKod().equals("B") || pemohon.getPihak().getJenisPengenalan().getKod().equals("L")
                            || pemohon.getPihak().getJenisPengenalan().getKod().equals("P") || pemohon.getPihak().getJenisPengenalan().getKod().equals("T")
                            || pemohon.getPihak().getJenisPengenalan().getKod().equals("I") || pemohon.getPihak().getJenisPengenalan().getKod().equals("K")) {

//                        if (pemohon.getTempohMastautin() == null) {
//                            context.addMessage("Sila masukkan tempoh mastautin untuk pemohon  : " + permohonan.getIdPermohonan());
//                            return null;
//                        }

                        if (pemohon.getPihak().getKodJantina() == null) {
                            context.addMessage("Sila masukkan maklumat jantina untuk pemohon  : " + permohonan.getIdPermohonan());
                            return null;
                        }

                        if (pemohon.getPihak().getBangsa() == null) {
                            context.addMessage("Sila masukkan maklumat bangsa untuk pemohon  : " + permohonan.getIdPermohonan());
                            return null;
                        }

//                        if (pemohon.getPihak().getTempatLahir() == null) {
//                            context.addMessage("Sila masukkan maklumat tempat lahir untuk pemohon  : " + permohonan.getIdPermohonan());
//                            return null;
//                        }
                    }
                }

//                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
//                    LaporanTanah laporanTanah = consentService.findLaporanTanahByIdMH(permohonan.getIdPermohonan(), String.valueOf(hakmilikPermohonan.getId()));
//                    if (laporanTanah == null) {
//                        context.addMessage("Sila masukkan maklumat perihal tanah untuk ID Hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " ID Pemohonan : " + permohonan.getIdPermohonan());
//                        return null;
//                    }
//                }

                if (context.getStageName().equals("Stage2")) {

                    PermohonanKertas permohonanKertasRisalat = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT MMKN");

                    if (permohonanKertasRisalat == null) {
                        context.addMessage("Sila masukkan maklumat kertas risalat : " + permohonan.getIdPermohonan());
                        return null;
                    }

                    PermohonanKertas permohonanKertasRingkas = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");

                    if (permohonanKertasRingkas == null) {
                        context.addMessage("Sila masukkan maklumat kertas ringkas : " + permohonan.getIdPermohonan());
                        return null;
                    }

                    if (permohonan.getKodUrusan().getIdWorkflow().endsWith("ConsentMelaka/MMK_melaka")) {

                        FasaPermohonan fasaPermohonan = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage9");

                        if (fasaPermohonan != null) {
                            PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "RAYUAN");
                            if (permohonanUrusan == null) {
                                context.addMessage("Sila masukkan maklumat Jenis Rayuan Pengurangan Bayaran : " + permohonan.getIdPermohonan());
                                return null;
                            }

                            PermohonanKertas permohonanKertasRisalatRayuan = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT RAYUAN");

                            if (permohonanKertasRisalatRayuan == null) {
                                context.addMessage("Sila masukkan maklumat kertas risalat : " + permohonan.getIdPermohonan());
                                return null;
                            }

                            PermohonanKertas permohonanKertasRingkasRayuan = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS RAYUAN");

                            if (permohonanKertasRingkasRayuan == null) {
                                context.addMessage("Sila masukkan maklumat kertas ringkas : " + permohonan.getIdPermohonan());
                                return null;
                            }

                            for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                                List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId(), "R");
                                if (mohonTuntutKosList.isEmpty()) {
                                    context.addMessage("Sila semak maklumat bayaran terlebih dahulu : " + permohonan.getIdPermohonan());
                                    return null;
                                }
                            }
                        }

                        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                            List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId());
                            if (mohonTuntutKosList.isEmpty()) {
                                context.addMessage("Sila masukkan maklumat yuran notis kelulusan untuk hakmilik " + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        }
                    }
                }
                if (context.getStageName().equals("Stage7")) {
                    for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                        List<PermohonanTuntutanKos> mohonTuntutKosList = consentService.findSenaraiMohonTuntutKosByKodTuntutAndIdMH(permohonan.getIdPermohonan(), "CON01", hakmilikPermohonan.getId(), null);
                        if (mohonTuntutKosList.isEmpty()) {
                            context.addMessage("Sila masukkan maklumat yuran notis kelulusan untuk hakmilik " + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }
            }

            //VALIDATOR FOR URUSAN TANAH ADAT
            if (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/ADAT")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/pmwna")
                    || permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/tmwna")) {
                if (permohonan.getKodUrusan().getKod().equals("TMADT") || permohonan.getKodUrusan().getKod().equals("TMWNA")) {
                    for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                        if (perPihak.getJenis().getKod().equals("TER")) {
//                            if (perPihak.getPihak().getJenisPengenalan() != null) {
//                                if (perPihak.getPihak().getJenisPengenalan().getKod().equals("B")
//                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("L")
//                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("P")
//                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("T")
//                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("D")
//                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
                                    if (perPihak.getPihak().getTarikhMati() == null || perPihak.getPihak().getNoSijilMati() == null) {
                                        context.addMessage("Sila masukkan maklumat kematian pada pihak terlibat dengan lengkap : " + permohonan.getIdPermohonan());
                                        return null;
                                    }
                                    if (perPihak.getPihak().getSuku() == null) {
                                        context.addMessage("Sila masukkan maklumat suku pada pihak terlibat : " + permohonan.getIdPermohonan());
                                        return null;
                                    }
//                                }
//                            }
                        }
                    }
                } else {
                    for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                        if (perPihak.getJenis().getKod().equals("TER")) {
                            if (perPihak.getPihak().getJenisPengenalan() != null) {
                                if (perPihak.getPihak().getJenisPengenalan().getKod().equals("B")
                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("L")
                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("P")
                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("T")
                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("D")
                                        || perPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
                                    if (perPihak.getPihak().getSuku() == null) {
                                        context.addMessage("Sila masukkan maklumat suku pada pihak terlibat : " + permohonan.getIdPermohonan());
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
                                        if (hakPihak.getPihak().getJenisPengenalan().getKod().equals("B")
                                                || hakPihak.getPihak().getJenisPengenalan().getKod().equals("L")
                                                || hakPihak.getPihak().getJenisPengenalan().getKod().equals("P")
                                                || hakPihak.getPihak().getJenisPengenalan().getKod().equals("T")
                                                || hakPihak.getPihak().getJenisPengenalan().getKod().equals("D")
                                                || hakPihak.getPihak().getJenisPengenalan().getKod().equals("F")) {
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
                }

//                if (permohonan.getRujukanUndang2() == null) {
//                    context.addMessage("Sila masukkan maklumat Datok Lembaga : " + permohonan.getIdPermohonan());
//                    return null;
//                }
            }

            //VALIDATOR SYER
            //NOT APPLICABLE FOR URUSAN TANAH ADAT AS THE SHARE WILL BE ONLY FINALIZED AT STAGE 5 OF THE FLOW
            if ((!permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/ADAT"))
                    || (permohonan.getKodUrusan().getIdWorkflow().endsWith("Consent/ADAT") && context.getStageName().equals("Stage5"))) {
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

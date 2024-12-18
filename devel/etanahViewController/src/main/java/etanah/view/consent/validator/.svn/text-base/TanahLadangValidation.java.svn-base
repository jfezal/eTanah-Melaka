
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanUrusan;
import etanah.report.ReportUtil;
import etanah.service.ConsentPtdService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanPihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class TanahLadangValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    ReportUtil reportUtil;
    private static Pengguna pengguna;
    private String totalLuas;
    private DecimalFormat df = new DecimalFormat("#0.0000");
    private BigDecimal sum = new BigDecimal(0.0000);
    private static final Logger LOG = Logger.getLogger(TanahLadangValidation.class);

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
        String kodNegeri = conf.getProperty("kodNegeri");

        LOG.info("kodNegeri=" + kodNegeri + ",kodUrusan=" + permohonan.getKodUrusan().getKod() + ",stageId=" + stageId);

        //NEGERI MELAKA
        if (kodNegeri.equals("04")) {

            if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {

                String stageA;      // VALIDATE KERTAS JKTL
                String stageB;      // VALIDATE KERTAS JKTL
                String stageC;      // VALIDATE TARIKH TT
                String stageD = ""; // VALIDATE LAPORAN TANAH

                if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                    stageA = "Stage3";
                    stageB = "Stage4";
                    stageC = "Stage8";
                    stageD = "Stage2";
                } else {
                    stageA = "Stage2";
                    stageB = "Stage4";
                    stageC = "Stage7";
                }

                if (stageId.equals(stageD)) {

                    LaporanTanah laporanTanah = consentService.findLaporanTanahByIdMH(permohonan.getIdPermohonan(), String.valueOf(permohonan.getSenaraiHakmilik().get(0).getId()));

                    if (laporanTanah == null) {
                        context.addMessage("Sila masukkan maklumat laporan tanah untuk : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (stageId.equals(stageA)) {

                    PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());

                    if (permohonanKertas != null) {

                        boolean ulasanComplete = true;
                        if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                                PermohonanKertasKandungan kertasKdgn = null;
                                kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                                if (kertasKdgn.getBil() == 2 || kertasKdgn.getBil() == 3 || kertasKdgn.getBil() == 4 || kertasKdgn.getBil() == 5 || kertasKdgn.getBil() == 6) {

                                    if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                        ulasanComplete = false;
                                        break;
                                    }
                                }
                            }
                        }

                        try {

                            if (!ulasanComplete) {
                                context.addMessage("Sila masukkan maklumat kertas JKTL untuk : " + permohonan.getIdPermohonan());
                                return null;
                            }

                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                            return null;
                        }
                    } else {
                        context.addMessage("Sila masukkan maklumat kertas JKTL untuk : " + permohonan.getIdPermohonan());
                        return null;
                    }

//                    LaporanTanah laporanTanah = consentService.findLaporanTanahByIdMohon(permohonan.getIdPermohonan());
//                    PermohonanLaporanUlasan mohonUlasanKerja = consentService.findMohonUlasByJenisUlasan(permohonan.getIdPermohonan(), "KERJA");
//                    PermohonanLaporanUlasan mohonUlasanTani = consentService.findMohonUlasByJenisUlasan(permohonan.getIdPermohonan(), "PERTANIAN");
//
//                    //to validate kertas jktl & laporan tanah as well
//                    if (laporanTanah == null || laporanTanah.getKeadaanTanah() == null || laporanTanah.getUsahaTanam() == null
//                            || mohonUlasanKerja == null || mohonUlasanKerja.getUlasan() == null || mohonUlasanTani == null || mohonUlasanTani.getUlasan() == null) {
//                        context.addMessage("Sila masukkan maklumat laporan tanah untuk : " + permohonan.getIdPermohonan());
//                        return null;
//                    }
                }//stage perakuan PTG
                else if (stageId.equals(stageB)) {

                    PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());

                    if (permohonanKertas != null) {

                        boolean ulasanComplete = true;
                        if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                            for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                                kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                                if (kertasKdgn.getBil() == 7 || kertasKdgn.getBil() == 8) {

                                    if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                        ulasanComplete = false;
                                    }
                                }
                            }
                        }

                        try {

                            if (!ulasanComplete) {
                                context.addMessage("Sila masukkan huraian kertas JKTL untuk : " + permohonan.getIdPermohonan());
                                return null;
                            }

                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                            return null;
                        }
                    }
                } else if (stageId.equals(stageC)) {

                    if (permohonan.getKeputusan() != null && permohonan.getKeputusan().getKod().equals("L")) {

                        PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(permohonan.getIdPermohonan(), "SK");

                        if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getTrhTt() == null) {
                            context.addMessage("Sila masukkan tarikh tandatangan sijil untuk : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }
            }
        } //NEGERI SEMBILAN
        else if (kodNegeri.equals("05")) {

            if (permohonan.getKodUrusan().getKod().equals("PJKTL")) {

                if (stageId.equals("Stage7")) {
                    //if (!permohonan.getSenaraiKertas().isEmpty()) {
                    //PermohonanKertas permohonanKertas = permohonan.getSenaraiKertas().get(0);
                    PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());
                    boolean ulasanComplete = true;

                    if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                        for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                            kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                            if (kertasKdgn.getBil() == 8 || kertasKdgn.getBil() == 9) {
                                if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                    ulasanComplete = false;
                                }
                            }
                        }
                    }

                    try {

                        if (!ulasanComplete) {
                            context.addMessage("Sila masukkan huraian kertas JKTL untuk : " + permohonan.getIdPermohonan());
                            return null;
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                    //}
                } else if (stageId.equals("Stage8")) {
                    try {

                        if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                            if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null || permohonanRujukanLuar.getLokasiAgensi() == null) {
                                context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {

                if (stageId.equals("Stage5")) {
                    //  if (!permohonan.getSenaraiKertas().isEmpty()) {
                    //PermohonanKertas permohonanKertas = permohonan.getSenaraiKertas().get(0);
                    PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());

                    boolean ulasanComplete = true;
                    if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                        for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                            PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                            kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                            if (kertasKdgn.getBil() == 8 || kertasKdgn.getBil() == 9) {

                                if (kertasKdgn.getKandungan().equals(" ") || kertasKdgn.getKandungan().isEmpty()) {
                                    ulasanComplete = false;
                                }
                            }
                        }
                    }

                    try {

                        if (!ulasanComplete) {
                            context.addMessage("Sila masukkan huraian kertas JKTL untuk : " + permohonan.getIdPermohonan());
                            return null;
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                    //  }
                } else if (stageId.equals("Stage6")) {
                    try {

                        if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                            if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null || permohonanRujukanLuar.getLokasiAgensi() == null) {
                                context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                } else if (stageId.equals("Stage7")) {
                    try {
                        FasaPermohonan fasaPermohonanPTD = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage2");
                        FasaPermohonan fasaPermohonanJKTL = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage7");
                        FasaPermohonan fasaPermohonanPTG = consentService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage6");

                        if (fasaPermohonanPTD.getKeputusan() == null && fasaPermohonanJKTL.getKeputusan() == null && fasaPermohonanPTG.getKeputusan() == null) {
                            context.addMessage("Sila masukkan maklumat keputusan : " + permohonan.getIdPermohonan());
                            return null;
                        }
                        if (fasaPermohonanPTD.getKeputusan() == null) {
                            context.addMessage("Sila masukkan syor Pentadbir Tanah : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (fasaPermohonanJKTL.getKeputusan() == null) {
                            context.addMessage("Sila masukkan syor Jawatankuasa Tanah Ladang : " + permohonan.getIdPermohonan());
                            return null;
                        } else if (fasaPermohonanPTG.getKeputusan() == null) {
                            context.addMessage("Sila masukkan syor Pengarah Tanah Dan Galian : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                }
                //validate PMJTL - to enter details mesyuarat MMK @stage9 - kpsn
                //permohonanRujukanLuar = consentService.findMohonRujukanByNamaSidangNotTangguh(permohonan.getIdPermohonan(), "MESYUARAT MMK");
                
                // rayuan JKTL dengan sekatan
                //function to generate paper rayuan moved to stage 3, validation for MMK details no longer required
            } /*else if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                if (stageId.equals("Stage3")) {
                    try {

                        if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                            if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                                context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                } 
                //stage to key-in maklumat mesyuarat MMK by SUMMK no longer required
                else if (stageId.equals("Stage14")) {
                    //validate mesyuarat for kes tangguh
                    PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(permohonan.getIdPermohonan());
                    if (permohonanRujukanLuar == null) {
                        context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                        return null;
                    } else {
                        if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                            context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }

                }
                //rayuan jktl tanpa sekatan
                //function to generate paper rayuan moved to stage 5, validation for MMK details no longer required
            } else if (permohonan.getKodUrusan().getKod().equals("RJKTL")) {
                if (stageId.equals("Stage5")) {
                    try {

                        if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                            context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                            if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                                context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                                return null;
                            }
                        }

                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                        return null;
                    }
                }
                //stage to key-in maklumat mesyuarat MMK by SUMMK no longer required
                else if (stageId.equals("Stage16")) {
                    //validate mesyuarat for kes tangguh
                    PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(permohonan.getIdPermohonan());
                    if (permohonanRujukanLuar == null) {
                        context.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                        return null;
                    } else {
                        if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                            context.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }

                }
            }*/
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        Permohonan permohonan = ctx.getPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");

        //NEGERI MELAKA
        if (kodNegeri.equals("04")) {

            if (ctx.getStageName().equals("Stage1")) {
                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                    List<PermohonanPihak> listPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hakmilik.getIdHakmilik());
                    List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());

                    if (permohonan.getSenaraiPemohon().isEmpty()) {
                        ctx.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    } else if (listPihakTerlibat.isEmpty()) {
                        ctx.addMessage("Sila masukkan maklumat pihak terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    } else if (listPenerima.isEmpty()) {
                        ctx.addMessage("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;

                    }

                    if (!permohonan.getSenaraiPihak().isEmpty()) {
                        for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                            if (!perPihak.getJenis().getKod().equals("TER") && !perPihak.getJenis().getKod().equals("WAR") && !perPihak.getJenis().getKod().equals("PA")) {
                                if (perPihak.getSyerPembilang() == null || perPihak.getSyerPenyebut() == null) {
                                    ctx.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                    return Boolean.FALSE;
                                } else if (perPihak.getSyerPembilang() == 0 || perPihak.getSyerPenyebut() == 0) {
                                    ctx.addMessage("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());
                                    return Boolean.FALSE;
                                }
                            }
                        }
                    }
                }

                //VALIDATE ALAMAT PEM0HON
                for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                    if (pemohon.getAlamatSurat() == null) {
                        ctx.addMessage("Sila masukkan alamat surat bagi pemohon " + WordUtils.capitalizeFully(pemohon.getPihak().getNama()));
                        return Boolean.FALSE;
                    }
                }

                LOG.info(":::: JANA TAJUK ::::");

                String tajuk;
                String namaPemohon = "";
                String namaPenerima = "";
                String namaHakmilik = "";

                for (int i = 0; i < permohonan.getSenaraiPemohon().size(); i++) {
                    Pemohon pmhn = permohonan.getSenaraiPemohon().get(i);
                    if (i == 0) {
                        namaPemohon = WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
                    } else if (i == permohonan.getSenaraiPemohon().size() - 1) {
                        namaPemohon = namaPemohon + " dan " + WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
                    } else if (i != 0 && i != permohonan.getSenaraiPemohon().size() - 1) {
                        namaPemohon = namaPemohon + ", " + WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
                    }
                }

                List<PermohonanPihak> senaraiMohonPihakAll = permohonanPihakService.getSenaraiPmohonPihakByNotKod(permohonan.getIdPermohonan(), "TER");
                List<PermohonanPihak> senaraiMohonPihakChecked = new ArrayList<PermohonanPihak>();

                for (PermohonanPihak perPihak : senaraiMohonPihakAll) {

                    if (senaraiMohonPihakChecked.isEmpty()) {
                        senaraiMohonPihakChecked.add(perPihak);
                    } else {
                        boolean found = false;
                        for (PermohonanPihak perPihakChecked : senaraiMohonPihakChecked) {
                            if (perPihak.getPihak().equals(perPihakChecked.getPihak())) {
                                found = true;
                            }
                        }
                        if (!found) {
                            senaraiMohonPihakChecked.add(perPihak);
                        }
                    }
                }

                for (int i = 0; i < senaraiMohonPihakChecked.size(); i++) {
                    PermohonanPihak pmhnPihak = senaraiMohonPihakChecked.get(i);
                    if (i == 0) {
                        namaPenerima = WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
                    } else if (i == senaraiMohonPihakChecked.size() - 1) {
                        namaPenerima = namaPenerima + " dan " + WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
                    } else if (i != 0 && i != senaraiMohonPihakChecked.size() - 1) {
                        namaPenerima = namaPenerima + ", " + WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
                    }
                }

                //ADD DAERAH TO LIST
                List<Hakmilik> senaraiDaerah = new ArrayList<Hakmilik>();

                for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
                    if (senaraiDaerah.isEmpty()) {
                        senaraiDaerah.add(hakmilikMohon.getHakmilik());
                    } else {
                        boolean dataDaerah = false;
                        for (Hakmilik daerahCheck : senaraiDaerah) {
                            if (daerahCheck.getDaerah().getKod().equals(hakmilikMohon.getHakmilik().getDaerah().getKod())) {
                                dataDaerah = true;
                                break;
                            }
                        }
                        if (!dataDaerah) {
                            senaraiDaerah.add(hakmilikMohon.getHakmilik());
                        }
                    }
                }

                //ADD MUKIM TO LIST
                List<Hakmilik> senaraiMukim = new ArrayList<Hakmilik>();

                for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
                    if (senaraiMukim.isEmpty()) {
                        senaraiMukim.add(hakmilikMohon.getHakmilik());
                    } else {
                        boolean dataMukim = false;
                        for (Hakmilik mukimCheck : senaraiMukim) {
                            if (mukimCheck.getBandarPekanMukim().getKod() == hakmilikMohon.getHakmilik().getBandarPekanMukim().getKod()) {
                                dataMukim = true;
                                break;
                            }
                        }
                        if (!dataMukim) {
                            senaraiMukim.add(hakmilikMohon.getHakmilik());
                        }
                    }
                }

                //GENERATE NAMA HAKMILIK MAIN
                if (permohonan.getSenaraiHakmilik().size() < 6) {
                    int countMukim = 0;
                    List<Hakmilik> senaraiMukimGenerated = new ArrayList<Hakmilik>();

                    for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                        HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);

                        //CHECK GENERATED OR NOT
                        boolean doGenerate = true;
                        if (senaraiMukimGenerated.size() > 0) {
                            for (Hakmilik hakmilikGenerated : senaraiMukimGenerated) {

                                if (hakmilikMohon.getHakmilik().getBandarPekanMukim().getKod() == hakmilikGenerated.getBandarPekanMukim().getKod()) {
                                    doGenerate = false;
                                    break;
                                }
                            }
                        }
                        //GENERATE NAMA HAKMILIK IF TRUE
                        if (doGenerate) {

                            //ADD DATA MUKIM SAMA TO LIST
                            List<Hakmilik> senaraiMukimSama = new ArrayList<Hakmilik>();
                            for (int j = 0; j < permohonan.getSenaraiHakmilik().size(); j++) {
                                HakmilikPermohonan hakmilikMohon2 = permohonan.getSenaraiHakmilik().get(j);

                                if (senaraiMukim.get(countMukim).getBandarPekanMukim().getKod() == hakmilikMohon2.getHakmilik().getBandarPekanMukim().getKod()) {
                                    senaraiMukimSama.add(hakmilikMohon2.getHakmilik());
                                }

                            }
                            int count = 0;
                            for (Hakmilik hakmilikMukim : senaraiMukimSama) {
                                if (count == 0) {
                                    namaHakmilik = namaHakmilik + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                } else if (count != 0 && count != senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + ", " + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                } else if (count == senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + " dan " + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                }

                                for (PermohonanPihak pihakSyer : permohonan.getSenaraiPihak()) {
                                    if (!pihakSyer.getJenis().getKod().equals("TER") && pihakSyer.getHakmilik().getIdHakmilik().equals(hakmilikMukim.getIdHakmilik())) {
                                        namaHakmilik = namaHakmilik + " dengan syer terlibat " + pihakSyer.getSyerPenyebut() + "/" + pihakSyer.getSyerPenyebut() + " bahagian";
                                    }
                                }

                                if (count == senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + " di " + hakmilikMukim.getBandarPekanMukim().getNama() + " ";
                                }
                                count++;
                            }

                            if (!senaraiMukimSama.isEmpty()) {
                                senaraiMukimGenerated.add(senaraiMukimSama.get(0));
                                namaHakmilik = namaHakmilik + "Daerah " + hakmilikMohon.getHakmilik().getDaerah().getNama() + ", ";
                                countMukim++;
                            }
                        }
                    }
                } else {
                    int count = 0;
                    for (Hakmilik hakmilikDaerah : senaraiDaerah) {

                        if (count == 0) {
                            namaHakmilik = namaHakmilik + "Daerah " + WordUtils.capitalizeFully(hakmilikDaerah.getDaerah().getNama());
                        } else if (count != 0 && count != senaraiDaerah.size() - 1) {
                            namaHakmilik = namaHakmilik + ", Daerah " + WordUtils.capitalizeFully(hakmilikDaerah.getDaerah().getNama());
                        } else if (count == senaraiDaerah.size() - 1) {
                            namaHakmilik = namaHakmilik + " dan Daerah " + WordUtils.capitalizeFully(hakmilikDaerah.getDaerah().getNama());
                        }
                        count++;
                    }
                }

                LOG.info("::::: HAKMILIK : " + namaHakmilik);

                //CALCULATE LUAS
                for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
                    sum = sum.add(hakmilikMohon.getHakmilik().getLuas());
                }

                totalLuas = df.format(sum) + " " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama());

                //CALCULATE SYER
                String syer = "";
                for (PermohonanPihak mohonPihak : permohonan.getSenaraiPihak()) {
                    syer = mohonPihak.getSyerPembilang() + "/" + mohonPihak.getSyerPenyebut();
                }

                if (permohonan.getPermohonanSebelum() == null) {
                    if (permohonan.getSenaraiHakmilik().size() < 6) {
                        tajuk = "permohonan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik " + namaHakmilik + "Melaka seluas " + totalLuas + " di bawah Seksyen 214A Kanun Tanah Negara";
                    } else {
                        tajuk = "permohonan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik seperti di lampiran A seluas " + totalLuas + " " + namaHakmilik + ", Melaka di bawah Seksyen 214A Kanun Tanah Negara";
                    }
                } else {
                    if (permohonan.getSenaraiHakmilik().size() < 6) {
                        tajuk = "permohonan rayuan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik " + namaHakmilik + "Melaka seluas " + totalLuas + " di bawah Seksyen 214A Kanun Tanah Negara";
                    } else {
                        tajuk = "permohonan rayuan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik seperti di lampiran A seluas " + totalLuas + " " + namaHakmilik + ", Melaka di bawah Seksyen 214A Kanun Tanah Negara";
                    }
                }

                LOG.info("::::: TAJUK : " + tajuk);
                LOG.info("::::: TOTAL LUAS : " + totalLuas);

                //SIMPAN TAJUK
                InfoAudit infoAudit = new InfoAudit();
                PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "TAJUK");

                if (permohonanUrusan == null) {
                    infoAudit.setDimasukOleh(ctx.getPengguna());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohonanUrusan = new PermohonanUrusan();

                } else {
                    infoAudit = permohonanUrusan.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(ctx.getPengguna());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }

                permohonanUrusan.setCawangan(permohonan.getCawangan());
                permohonanUrusan.setPermohonan(permohonan);
                permohonanUrusan.setCatatan(tajuk);
                permohonanUrusan.setInfoAudit(infoAudit);
                permohonanUrusan.setPerihal("TAJUK");
                consentService.simpanPermohonanUrusan(permohonanUrusan);

                //SIMPAN TOTAL LUAS
                infoAudit = new InfoAudit();
                PermohonanUrusan permohonanUrusan2 = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LUAS");

                if (permohonanUrusan2 == null) {
                    infoAudit.setDimasukOleh(ctx.getPengguna());
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohonanUrusan2 = new PermohonanUrusan();

                } else {
                    infoAudit = permohonanUrusan2.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(ctx.getPengguna());
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }

                permohonanUrusan2.setCawangan(permohonan.getCawangan());
                permohonanUrusan2.setPermohonan(permohonan);
                permohonanUrusan2.setCatatan(totalLuas);
                permohonanUrusan2.setInfoAudit(infoAudit);
                permohonanUrusan2.setPerihal("LUAS");
                consentService.simpanPermohonanUrusan(permohonanUrusan2);

            } else if (ctx.getStageName().equals("Stage5")) {
                //VALIDATE KERTAS RINGKAS RMJTL
                List<PermohonanRujukanLuar> listUlasanTeknikal = consentService.findSenaraiMohonRujukanByAgensiNotNull(permohonan.getIdPermohonan());

                if (listUlasanTeknikal.size() > 0) {
                    for (PermohonanRujukanLuar mohonRujLuar : listUlasanTeknikal) {
                        if (mohonRujLuar.getDiSokong() == null) {
                            ctx.addMessage("Sila masukkan maklumat ulasan " + WordUtils.capitalizeFully(mohonRujLuar.getAgensi().getNama()) + " pada kertas ringkas.");
                            return Boolean.FALSE;
                        }
                    }
                }
            } else if (ctx.getStageName().equals("Stage6")) {

                List<PermohonanRujukanLuar> listUlasanTeknikal = consentService.findSenaraiMohonRujukanByAgensiNotNull(permohonan.getIdPermohonan());

                if (listUlasanTeknikal.size() > 0) {
                    for (PermohonanRujukanLuar mohonRujLuar : listUlasanTeknikal) {
                        if (mohonRujLuar.getDiSokong() == null) {
                            ctx.addMessage("Sila masukkan maklumat ulasan " + WordUtils.capitalizeFully(mohonRujLuar.getAgensi().getNama()) + " pada kertas ringkas.");
                            return Boolean.FALSE;
                        }
                    }
                }

                PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(permohonan.getIdPermohonan());

                if (permohonanRujukanLuar == null) {
                    ctx.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                    return Boolean.FALSE;
                } else {
                    if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                        ctx.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    }
                }
            }


        } //NEGERI SEMBILAN
        else if (kodNegeri.equals("05")) {

            String stageCheckA = ""; //MESYURAT JKTL
            String stageCheckB = ""; //MESYURAT MMK TANAH LADANG
            String stageCheckC = ""; //NOMBOR SIJIL

            //validate details MMK - to enter details mesyuarat MMK @stage9 - kpsn
            if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                stageCheckA = "Stage6";
                stageCheckB = "Stage9";
                stageCheckC = "Stage11";
            } else if (permohonan.getKodUrusan().getKod().equals("PJKTL")) {
                stageCheckA = "Stage8";
                stageCheckC = "Stage10";
            } 
            //validate details MMK - to enter details mesyuarat MMK @stage9 - kpsn
            else if (permohonan.getKodUrusan().getKod().equals("RJKTL")) {
                stageCheckB = "Stage9";
            } 
            //validate details MMK - to enter details mesyuarat MMK @stage7 - kpsn
            else if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                stageCheckB = "Stage7";
            }

            //CHECKING MESYURAT JKTL
            if (ctx.getStageName().equals(stageCheckA)) {
                if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                    ctx.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                    return Boolean.FALSE;
                } else {
                    PermohonanRujukanLuar permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                    if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                        ctx.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    }
                }
            }

            //CHECKING MESYURAT MMK TANAH LADANG
            if (ctx.getStageName().equals(stageCheckB)) {

                PermohonanRujukanLuar permohonanRujukanLuar;

                if (permohonan.getKodUrusan().getKod().equals("RMJTL") || permohonan.getKodUrusan().getKod().equals("RJKTL")) {
                    permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh(permohonan.getIdPermohonan());

                } else {
                    permohonanRujukanLuar = consentService.findMohonRujukanByNamaSidangNotTangguh(permohonan.getIdPermohonan(), "MESYUARAT MMK");

                }

                if (permohonanRujukanLuar == null) {
                    ctx.addMessage("Sila masukkan maklumat mesyuarat : " + permohonan.getIdPermohonan());
                    return Boolean.FALSE;
                } else {
                    if (permohonanRujukanLuar.getTarikhSidang() == null || permohonanRujukanLuar.getNoSidang() == null) {
                        ctx.addMessage("Sila masukkan maklumat mesyuarat dengan lengkap : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    }
                }
            }

            //CHECKING NOMBOR SIJIL
            if (ctx.getStageName().equals(stageCheckC)) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanByNamaNotTangguh(permohonan.getIdPermohonan(), "MESYUARAT JKTL");
                    if (permohonanRujukanLuar != null) {
                        if (permohonanRujukanLuar.getNoRujukan() == null) {
                            ctx.addMessage("Sila masukkan nombor sijil : " + permohonan.getIdPermohonan());
                            return Boolean.FALSE;
                        }
                    }
                }
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;

        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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
}

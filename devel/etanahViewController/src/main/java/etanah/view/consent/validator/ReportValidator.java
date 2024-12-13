/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanUrusan;
import etanah.report.ReportUtil;
import etanah.service.ConsentPtdService;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.ValidationService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.List;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class ReportValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
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
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    ConsentPtdService consentService;
    private static final Logger LOGGER = Logger.getLogger(ReportValidator.class);
    private static Pengguna pengguna;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        pengguna = context.getPengguna();
        if (permohonan != null) {
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            String kodNegeri = conf.getProperty("kodNegeri");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            if (permohonan.getKeputusan() != null) {

                if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL") || permohonan.getKodUrusan().getKod().equals("PJKTL")) {
                    //--------for urusan tanah ladang-------
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        for (int i = 0; i < 2; i++) {

                            if (kodNegeri.equals("04")) {  //FOR MELAKA

                                if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                                    if (i == 0) {
                                        kd = kodDokumenDAO.findById("SJTL"); // FOR SIJIL TANAH LADANG
                                        reportName = "CONSSijilMhnPmlkTnhLadang001_MLK.rdf";
                                    } else {
                                        kd = kodDokumenDAO.findById("SKTL"); // FOR SURAT
                                        reportName = "CONSSrtKebPMT_Ladang_MLK.rdf";
                                    }
                                } else { //RAYUAN - RMJTL
                                    if (i == 0) {
                                        kd = kodDokumenDAO.findById("SJRTL"); // FOR SIJIL TANAH LADANG
                                        reportName = "CONSSijilMhnPmlkTnhLadang001_MLK.rdf";
                                    } else {
                                        kd = kodDokumenDAO.findById("SKRTL"); // FOR SURAT
                                        reportName = "CONSSrtKebPMT_Ladang_MLK.rdf";
                                    }
                                }
                            }

                            if (kodNegeri.equals("05")) {  //FOR NEGERI SEMBILAN

                                if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                                    if (i == 0) {
                                        kd = kodDokumenDAO.findById("SJK"); // FOR SIJIL TANAH LADANG
                                        reportName = "CONSSijilMhnPmlkTnhLadang001.rdf";
                                    }
                                } else {
                                    if (i == 0) {
                                        kd = kodDokumenDAO.findById("SJK"); // FOR SIJIL TANAH LADANG
                                        reportName = "CONSSijilMhnPmlkTnhLadang001.rdf";
                                    } else {
                                        kd = kodDokumenDAO.findById("SK"); // FOR SURAT
                                        reportName = "CONS_SRT_TL_NS.rdf";
                                    }
                                }
                            }

                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                        }
                    } else {
                        //-------if fail jana surat sahaja-------

                        if (kodNegeri.equals("04")) {  //FOR MELAKA
                            if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
                                kd = kodDokumenDAO.findById("SKTL"); // FOR SURAT
                            } else {
                                kd = kodDokumenDAO.findById("SKRTL"); // FOR SURAT
                            }
                            reportName = "CONSSrtKebPMT_Ladang_MLK.rdf";
                        } else if (kodNegeri.equals("05")) {  //FOR NEGERI SEMBILAN
                            kd = kodDokumenDAO.findById("SK"); // FOR SURAT
                            reportName = "CONS_SRT_TL_NS.rdf";
                        }

                        d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    }
                }
            }
            //FOR URUSAN PMMK1 MMKN MELAKA            
            if (kodNegeri.equals("04") && permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "RAYUAN");
                if (context.getStageName().equals("Stage2") || context.getStageName().equals("Stage3") || context.getStageName().equals("Stage4")
                        || context.getStageName().equals("Stage5") || context.getStageName().equals("Stage6")) {
                    //RIS / RISR
                    //RMN / RMNR
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            if (permohonanUrusan == null) {
                                kd = kodDokumenDAO.findById("RIS");
                            } else {
                                kd = kodDokumenDAO.findById("RISR");
                            }
                            reportName = "CONS_Risalat_MMK1.rdf";
                        } else {
                            if (permohonanUrusan == null) {
                                kd = kodDokumenDAO.findById("RMN");
                            } else {
                                kd = kodDokumenDAO.findById("RMNR");
                            }
                            reportName = "CONS_Ringkas_MMK1.rdf";
                        }
                        d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    }
                } else if (context.getStageName().equals("Stage8") || context.getStageName().equals("Stage11")) {
                    //SMM / SMMRY 
                    if (permohonanUrusan == null) {
                        kd = kodDokumenDAO.findById("SMM");
                    } else {
                        kd = kodDokumenDAO.findById("SMMRY");
                    }
                    reportName = "CONS_Surat_Per_Keb_PMMMK.rdf";
                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        String kodNegeri = conf.getProperty("kodNegeri");
        String kodDokumen = "";
        boolean reportFound = false;
        //MELAKA
        if (kodNegeri.equals("04")) {

            if ((context.getPermohonan().getKodUrusan().getKod().equals("RMJTL") && context.getStageName().equals("Stage7"))
                    || (context.getPermohonan().getKodUrusan().getKod().equals("PMJTL") && context.getStageName().equals("Stage8"))) {

                if (context.getPermohonan().getKodUrusan().getKod().equals("PMJTL")) {
                    kodDokumen = "SKTL";
                } else {//RAYUAN
                    kodDokumen = "SKRTL";
                }

                for (KandunganFolder kandunganFolder : context.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                        reportFound = true;
                    }
                }
                if (!reportFound) {
                    context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + context.getPermohonan().getIdPermohonan());
                    return null;
                }
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PMMK1")) {

                if (context.getStageName().equals("Stage6") || context.getStageName().equals("Stage8") || context.getStageName().equals("Stage11")) {

                    PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(context.getPermohonan().getIdPermohonan(), "RAYUAN");

                    if (permohonanUrusan == null) {

                        if (context.getStageName().equals("Stage6")) {
                            kodDokumen = "RIS";
                        } else if (context.getStageName().equals("Stage8") || context.getStageName().equals("Stage11")) {
                            kodDokumen = "SMM";
                        }
                    } else {
                        if (context.getStageName().equals("Stage6")) {
                            kodDokumen = "RISR";
                        } else if (context.getStageName().equals("Stage8") || context.getStageName().equals("Stage11")) {
                            kodDokumen = "SMMRY";
                        }
                    }

                    for (KandunganFolder kandunganFolder : context.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                            reportFound = true;
                        }
                    }

                    if (!reportFound) {
                        context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + context.getPermohonan().getIdPermohonan());
                        return null;
                    }
                }
            }
        } // NS
        else if (kodNegeri.equals("05")) {
            if (context.getPermohonan().getKodUrusan().getKod().equals("PJKTL") && (context.getStageName().equals("Stage10") || context.getStageName().equals("Stage11"))) {
                for (KandunganFolder kandunganFolder : context.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK")) {
                        reportFound = true;
                    }
                }
                if (!reportFound) {
                    context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + context.getPermohonan().getIdPermohonan());
                    return null;
                }
            } else if (context.getPermohonan().getKodUrusan().getKod().equals("PMJTL") && (context.getStageName().equals("Stage15") || context.getStageName().equals("Stage16"))) {
                for (KandunganFolder kandunganFolder : context.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK")) {
                        reportFound = true;
                    }
                }
                if (!reportFound) {
                    context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + context.getPermohonan().getIdPermohonan());
                    return null;
                }
            } else if ((context.getPermohonan().getKodUrusan().getKod().equals("PMJTL") && context.getStageName().equals("Stage11"))) {
                for (KandunganFolder kandunganFolder : context.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {
                    if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SJK")) {
                        reportFound = true;
                    }
                }
                if (!reportFound) {
                    context.addMessage("Sila Jana Dokumen Terlebih Dahulu : " + context.getPermohonan().getIdPermohonan());
                    return null;
                }
            }
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

            if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                if (ctx.getStageName().equals("Stage7")) {

                    PermohonanKertas permohonanKertas = consentService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "KERTAS JKTL");

                    if (permohonanKertas == null || permohonanKertas.getNomborRujukan() == null) {
                        ctx.addMessage("Sila masukkan no sijil untuk permohonan : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    }
                }
            } else if (permohonan.getKodUrusan().getKod().equals("PMMK1")
                    || permohonan.getKodUrusan().getKod().equals("PMMK2")) {

                if (ctx.getStageName().equals("Stage1")) {

                    if (permohonan.getSenaraiPemohon().isEmpty()) {
                        ctx.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                        return Boolean.FALSE;
                    }

                    List<PermohonanRujukanLuar> listUlasanTeknikal = consentService.findSenaraiMohonRujukanByAgensiNotNull(permohonan.getIdPermohonan());

                    if (listUlasanTeknikal.isEmpty()) {
                        ctx.addMessage("Sila semak maklumat agensi terlebih dahulu.");
                        return Boolean.FALSE;
                    } else {
                        for (PermohonanRujukanLuar mohonRujLuar : listUlasanTeknikal) {
                            if (mohonRujLuar.getCatatan() == null) {
                                ctx.addMessage("Sila masukkan nama pegawai untuk perhatian terlebih dahulu.");
                                return Boolean.FALSE;
                            }
                        }
                    }

                    PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan());

                    if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                        ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                        return Boolean.FALSE;
                    }

                    LOGGER.info(":::: JANA TAJUK ::::");

                    String tajuk;
                    String namaHakmilik = "";
                    for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                        HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);
                        if (i == 0) {
                            namaHakmilik = hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + hakmilikMohon.getHakmilik().getKodUnitLuas().getNama() + " di " + hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama() + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                        } else if (i == permohonan.getSenaraiHakmilik().size() - 1) {
                            namaHakmilik = namaHakmilik + " dan " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getKodUnitLuas().getNama()) + " di " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama()) + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                        } else if (i != 0 && i != permohonan.getSenaraiHakmilik().size() - 1) {
                            namaHakmilik = namaHakmilik + ", " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getKodUnitLuas().getNama()) + " di " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama()) + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                        }
                    }

                    String namaPemohon;
                    String jenisHartanah;
                    String noKpLain = "";

                    //Set nama pemohon
                    Pemohon pemohon = permohonan.getSenaraiPemohon().get(0);

                    if (pemohon.getPihak().getNoPengenalanLain() != null) {
                        noKpLain = "(No K/P " + pemohon.getPihak().getNoPengenalanLain() + ") ";
                    }

                    if (pemohon.getPihak().getJenisPengenalan().getKod().equals("S")) {
                        namaPemohon = "Syarikat " + WordUtils.capitalizeFully(pemohon.getPihak().getWargaNegara().getNama());
                    } else if (pemohon.getPihak().getJenisPengenalan().getKod().equals("N") || pemohon.getPihak().getJenisPengenalan().getKod().equals("U") || pemohon.getPihak().getJenisPengenalan().getKod().equals("D")) {
                        namaPemohon = WordUtils.capitalizeFully(pemohon.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pemohon.getPihak().getJenisPengenalan().getNama()) + " " + pemohon.getPihak().getNoPengenalan().toUpperCase() + ") " + noKpLain + "didaftarkan di " + WordUtils.capitalizeFully(pemohon.getPihak().getWargaNegara().getNama());
                    } else {
                        namaPemohon = WordUtils.capitalizeFully(pemohon.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pemohon.getPihak().getJenisPengenalan().getNama()) + " " + pemohon.getPihak().getNoPengenalan().toUpperCase() + ") " + noKpLain + "warganegara " + WordUtils.capitalizeFully(pemohon.getPihak().getWargaNegara().getNama());
                    }

                    if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                        jenisHartanah = "hartanah";
                    } else {
                        jenisHartanah = "hartanah di Negeri Melaka melalui harta pesaka";
                    }

                    if (permohonan.getPermohonanSebelum() != null) {
                        tajuk = "permohonan rayuan daripada " + namaPemohon + " untuk memiliki " + jenisHartanah + " di atas hakmilik " + namaHakmilik + ", Melaka di bawah Seksyen 433B (1) Kanun Tanah Negara 1965";

                    } else {
                        tajuk = "permohonan daripada " + namaPemohon + " untuk memiliki " + jenisHartanah + " di atas hakmilik " + namaHakmilik + ", Melaka di bawah Seksyen 433B (1) Kanun Tanah Negara 1965";
                    }

                    LOGGER.info("::::: TAJUK : " + tajuk);

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
                }

                if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                    if (ctx.getStageName().equals("Stage8")) {
                        PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "SMM");
                        if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                            ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                            return Boolean.FALSE;
                        }
                    } else if (ctx.getStageName().equals("Stage10")) {
                        PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "SK");
                        if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                            ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                            return Boolean.FALSE;
                        }
                    } else if (ctx.getStageName().equals("Stage11")) {
                        PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "SMM");
                        if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                            ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                            return Boolean.FALSE;
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                    if (ctx.getStageName().equals("Stage8")) {
                        PermohonanTandatanganDokumen mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "SK");
                        if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                            ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                            return Boolean.FALSE;
                        }
                    }
                }
            }
        }

        return Boolean.TRUE;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}

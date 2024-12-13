/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.JabatanConstants;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Kompaun;
import etanah.model.LaporanTanah;
import etanah.model.Notifikasi;
import etanah.model.Notis;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.gis.PelanGIS;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.EnforceService;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.penguatkuasaan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim @modified hazirah for plps
 */
public class NotaTindakanValidator implements StageListener {

    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    private KodRujukanDAO kodRujDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    private GeneratorIdPermohonan idGenMohon;
    @Inject
    private TaskDebugService tds;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private PermohonanNota permohonanNota;
    private String stageId;
    private static final Logger LOG = Logger.getLogger(NotaTindakanValidator.class);
    private String keputusan;
    private List<HakmilikPermohonan> hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiTanahMilik;
    private List<AduanOrangKenaSyak> senaraiDakwaOks;
    private List<BarangRampasan> senaraiDakwaBarangRampasan;
    private List<OperasiPenguatkuasaan> listOp;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private List<PermohonanRujukanLuar> mohonRujLuar;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private boolean statusDakwa = Boolean.FALSE;
    private List<HakmilikPermohonan> senaraiTanahMilikKerajaan;
    private List<HakmilikPermohonan> senaraiTanahMilikPersendirian;
    private Boolean tanahMilik = Boolean.FALSE;
    private Boolean tanahKerajaan = Boolean.FALSE;
    String senaraiReport[], nama, kod;
    ArrayList reportName = new ArrayList<String>();
    private String kodUrusan;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {

        Permohonan permohonan = context.getPermohonan();
        stageId = context.getStageName();
        Pengguna pengguna = context.getPengguna();

        LOG.info("--------------stage id onGenerateReports------------- : " + stageId);

        senaraiTanahMilikKerajaan = enforceService.findSenaraiTanahMilik(permohonan.getIdPermohonan());
        senaraiTanahMilikPersendirian = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());

        if (!senaraiTanahMilikKerajaan.isEmpty()) {
            tanahKerajaan = true;
        } else if (!senaraiTanahMilikPersendirian.isEmpty()) {
            tanahMilik = true;
        }

        LOG.info("tanahKerajaan : " + tanahKerajaan);
        LOG.info("tanahMilik : " + tanahMilik);

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") && stageId.equalsIgnoreCase("sedia_laporan2")) {
                try {
                    FolderDokumen f = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), "LPOP");
                    LOG.info("::update table dokumen : stage id ::: " + stageId);
                    for (int i = 0; i < listDokumen.size(); i++) {
                        Dokumen d = listDokumen.get(i).getDokumen();
                        if (d != null) {
                            //d.setDalamanNilai1("T");
                            d.setPerihal("T");
                            enforcementService.simpanDokumen(d);

                        }

                    }
                } catch (Exception ex) {
                    LOG.error(ex);
                }

            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS")) {
                if (stageId.equalsIgnoreCase("semak1_laporan_awal1")) {
                    List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                    for (FasaPermohonan f : senaraiFasa) {
                        if (StringUtils.isNotBlank(f.getIdAliran())) {
                            if (f.getIdAliran().equalsIgnoreCase("semak1_laporan_awal1")) {
                                if (f.getKeputusan() != null) {
                                    if (f.getKeputusan().getKod().equalsIgnoreCase("KH")) { //KH = kes berat
                                        LOG.info("--------------stage id------------- : " + stageId + "(" + f.getKeputusan().getKod() + ")");
                                        janaDokumen(permohonan, pengguna, "ENFLTNH351_MLK.rdf", "LTNH");
                                    }

                                    if (f.getKeputusan().getKod().equalsIgnoreCase("KR")) { //KR = kes ringan
                                        LOG.info("--------------stage id------------- : " + stageId + "(" + f.getKeputusan().getKod() + ")");
                                        janaDokumen(permohonan, pengguna, "ENFKPA_MLK.rdf", "KPA");
                                    }
                                }

                            }
                        }
                    }
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) { //maklum_kpsn_mmkn  rekod_keputusan_mmkn
                    //jana dokumen for Surat Keputusan MMKN
                    janaDokumen(permohonan, pengguna, "DISSKpsnMMKNPLPS_MLK.rdf", "SKM");

                    ArrayList kodDokumenTerlibat = new ArrayList<String>();
                    kodDokumenTerlibat.add("RMN");
                    kodDokumenTerlibat.add("KMN");
                    kodDokumenTerlibat.add("SKM");
                    try {
                        FolderDokumen f = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                        int c = 1;
                        for (Object s : kodDokumenTerlibat) {
                            List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), s.toString());
                            LOG.info(c + "::update table dokumen(kod klasifikasi) : stage id ::: " + stageId + " & kod dokumen :: " + s.toString());
                            for (int i = 0; i < listDokumen.size(); i++) {
                                Dokumen d = listDokumen.get(i).getDokumen();
                                if (d != null) {
                                    if (pengguna.getTahapSekuriti() != null) {
                                        d.setKlasifikasi(kodKlasifikasiDAO.findById(pengguna.getTahapSekuriti().getKod()));
                                        enforcementService.simpanDokumen(d);
                                    }

                                }

                            }
                            c++;
                        }

                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
            }
//            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
//                if (stageId.equalsIgnoreCase("semak1_laporan_awal1") || stageId.equalsIgnoreCase("semak_laporan1") || stageId.equalsIgnoreCase("g_sedia_pelan") || stageId.equalsIgnoreCase("sedia_laporan1")) {
//                    if (tanahKerajaan == true) {
//                        reportName.add("ENFLTNHKJAAN_MLK.rdf,LTNH");
//                    } else if (tanahMilik == true) {
//                        reportName.add("ENFLTNH_MLK.rdf,LTNH");
//                    }
//                }
//
//                LOG.info("::: size report (" + permohonan.getRujukanUndang2() + "): " + reportName.size());
//                ArrayList<String> data = reportName;
//                for (String a : data) {
//                    senaraiReport = a.split(",");
//                    LOG.info("length senaraiReport : " + senaraiReport.length);
//                    if (senaraiReport.length > 1) {
//                        nama = senaraiReport[0];
//                        kod = senaraiReport[1];
//                        LOG.info("nama report : " + nama + " kod : " + kod);
//                        janaDokumen(permohonan, pengguna, nama, kod);
//                    }
//                }
//            }
        }
//        } else if (conf.getProperty("kodNegeri").equals("05")) {
//            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
//                List senaraiOK = enforcementService.getListAduanOrangkenaSyak2(permohonan.getIdPermohonan());
//                if (senaraiOK.size() > 0) {
//                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
//                        reportName.add("ENFNP_425_NS.rdf,PKT");
//                    } else {
//                        reportName.add("ENFNP_425A_NS.rdf,PKT");
//                    }
//                } else {
//                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
//                        reportName.add("ENFNP_425_Kosong_NS.rdf,PKT");
//                    } else {
//                        reportName.add("ENFNP_425A_Kosong_NS.rdf,PKT");
//                    }
//                }
//            }
//
//            LOG.info("::: size report (" + permohonan.getIdPermohonan() + "): " + reportName.size());
//            ArrayList<String> data = reportName;
//            for (String a : data) {
//                senaraiReport = a.split(",");
//                LOG.info("length senaraiReport : " + senaraiReport.length);
//                if (senaraiReport.length > 1) {
//                    nama = senaraiReport[0];
//                    kod = senaraiReport[1];
//                    LOG.info("nama report : " + nama + " kod : " + kod);
//                    janaDokumen(permohonan, pengguna, nama, kod);
//                }
//            }
//        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49") && stageId.equalsIgnoreCase("g_hantar_pu")) {//g_hantar_pu
                try {

                    List<PelanGIS> pelanGis = new ArrayList<PelanGIS>();
                    pelanGis = enforcementService.findPelanByIdMohon(permohonan.getIdPermohonan(), "B1");
                    LOG.info("::size listDokumen (B1) for sek49 ::: " + pelanGis.size());
                    if (pelanGis.isEmpty()) {
                        context.addMessage("Sila masukkan Dokumen B1 : " + permohonan.getIdPermohonan());
                        return null;
                    } else {
                    }

                } catch (Exception ex) {
                    LOG.error(ex);
                }

            }
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) { //update kod_klas table dokumen
                    //jana dokumen for Surat Keputusan MMKN
                    janaDokumen(permohonan, pengguna, "DISSKpsnMMKNPLPS_MLK.rdf", "SKM");

                    ArrayList kodDokumenTerlibat = new ArrayList<String>();
                    kodDokumenTerlibat.add("RMN");
                    kodDokumenTerlibat.add("KMN");
                    kodDokumenTerlibat.add("SKM");
                    try {
                        FolderDokumen f = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                        int c = 1;
                        for (Object s : kodDokumenTerlibat) {
                            List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), s.toString());
                            LOG.info(c + "::update table dokumen(kod klasifikasi) : stage id ::: " + stageId + " & kod dokumen :: " + s.toString());
                            for (int i = 0; i < listDokumen.size(); i++) {
                                Dokumen d = listDokumen.get(i).getDokumen();
                                if (d != null) {
                                    if (pengguna.getTahapSekuriti() != null) {
                                        d.setKlasifikasi(kodKlasifikasiDAO.findById(pengguna.getTahapSekuriti().getKod()));
                                        enforcementService.simpanDokumen(d);
                                    }

                                }

                            }
                            c++;
                        }

                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
            }
        }


        permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        if (permohonanNota != null) {
            LOG.info("::: kandungan nota :" + permohonanNota.getNota());
            context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
            return null;
        }

        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                String mesej = "Sila Lampirkan Dokumen Berikut Sebelum Ke Peringkat Seterusnya Untuk Permohonan : %s <br/> %s";
                StringBuilder sb = new StringBuilder();

                if (stageId.equalsIgnoreCase("sedia_kertas_pertuduhan")
                        || stageId.equalsIgnoreCase("arah_simpan_barang")
                        || stageId.equalsIgnoreCase("sedia_kertas_siasatan")) {
                    //Hafifi 04/02/2014 : Check for supporting document
                    List dokumenSSPP = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SSPP");
                    List dokumenSLPPS = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SLPPS");
                    if (dokumenSSPP.isEmpty() || dokumenSLPPS.isEmpty()) {
                        sb.append("Salinan Surat Lantikan Sebagai Pengawai Penyiasat oleh PTD/PTG<br/>");
                    }
                }
                if (stageId.equalsIgnoreCase("jalan_operasi1")
                        || stageId.equalsIgnoreCase("sedia_laporan1")) {
                    //Hafifi 28/09/2013 : Check for supporting document
                    List dokumenKPS = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "KPS");
                    List dokumenSYarikat = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SSPS");
                    if (dokumenKPS.isEmpty() && dokumenSYarikat.isEmpty()) {
                        sb.append("Kad Pengenalan Saspek / Salinan Sijil Pendaftaran Syarikat<br/>");
//                        context.addMessage(String.format(mesej, "Kad Pengenalan Saspek (disahkan benar)", permohonan.getIdPermohonan()));
//                        return null;
                    }
                    boolean perluLaporanPolis = false;
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                        LaporanTanah laporanTanah = enforceService.findByIDMohon(permohonan.getIdPermohonan());

                        if (laporanTanah.getAdaKes().equals('Y')) {
                            perluLaporanPolis = true;
                        }
                    } else {
                        perluLaporanPolis = true;
                    }
                    if (perluLaporanPolis) {
                        List dokumenSLP = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "LP");
                        if (dokumenSLP.isEmpty()) {
                            sb.append("Salinan Laporan Polis<br/>");
//                        context.addMessage(String.format(mesej, "Salinan Report Polis (disahkan benar)", permohonan.getIdPermohonan()));
//                        return null;
                        }
                    }

//                    List dokumenBKRP = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "BKRP");
//                    if (dokumenBKRP.isEmpty()) {
//                        context.addMessage(String.format(mesej, "Borang Kenyataan Rakaman Percakapan Daripada Saspek & Saksi-Saksi Yang Perlu", permohonan.getIdPermohonan()));
//                        return null;
//                    }
                    List dokumenPLK = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "PLK");
                    if (dokumenPLK.isEmpty()) {
                        sb.append("Pelan Lokasi<br/>");
//                        context.addMessage(String.format(mesej, "Pelan Lokasi Kejadian", permohonan.getIdPermohonan()));
//                        return null;
                    }
                    List dokumenLTK = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "LTK");
                    if (dokumenLTK.isEmpty()) {
                        sb.append("Lakaran Tempat Kejadian<br/>");
//                        context.addMessage(String.format(mesej, "Lakaran Tempat Kejadian", permohonan.getIdPermohonan()));
//                        return null;
                    }
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                        List dokumenIB = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "IB");
                        if (dokumenIB.isEmpty()) {
                            sb.append("Gambar Barang Rampasan<br/>");
//                        context.addMessage(String.format(mesej, "Senarai Barang Yang Dirampas dan Gambar", permohonan.getIdPermohonan()));
//                        return null;
                        }
                    }

                    List dokumenDIARI = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "DIARI");
                    if (dokumenDIARI.isEmpty()) {
                        sb.append("Diari Siasatan<br/>");
//                        context.addMessage(String.format(mesej, "Diari Siasatan (pada hari serbuan)", permohonan.getIdPermohonan()));
//                        return null;
                    }
//                    List dokumenSSPP = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SSPP");
//                    if (dokumenSSPP.isEmpty()) {
//                        context.addMessage(String.format(mesej, "Salinan Surat Lantikan Sebagai Pegawai Penyiasat oleh PTD/PTG", permohonan.getIdPermohonan()));
//                        return null;
//                    }
                    List dokumenGSL = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "GSL");
                    if (dokumenGSL.isEmpty()) {
                        sb.append("Gambar Serbuan dan Lokasi<br/>");
//                        context.addMessage(String.format(mesej, "Gambar Sewaktu Serbuan dan Lokasi", permohonan.getIdPermohonan()));
//                        return null;
                    }
                    List dokumenSKKP = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SKKP");
                    if (dokumenSKKP.isEmpty()) {
                        sb.append("Salinan Kad Kuasa Pengawai Serbuan/Operasi<br/>");
//                        context.addMessage(String.format(mesej, "Salinan Kad Kuasa Pegawai Serbuan / Operasi", permohonan.getIdPermohonan()));
//                        return null;
                    }
//                    List dokumenSPJPM = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "SPJPM");
//                    if (dokumenSPJPM.isEmpty()) {
//                        sb.append("Surat Pengesahan Sempadan / Tanah Kerajaan / Tanah Rizab / Tanah Hutan oleh JUPEM dll<br/>");
////                        context.addMessage(String.format(mesej, "Surat Pengesahan Sempadan / Tanah Kerajaan / Tanah Rizab / Tanah Hutan oleh JUPEM dll", permohonan.getIdPermohonan()));
////                        return null;
//                    }
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {
                        List dokumenIBD = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "IBD");
                        if (dokumenIBD.isEmpty()) {
                            sb.append("Inventori Barang - Barang yang Dirampas<br/>");
//                        context.addMessage(String.format(mesej, "Lain-Lain Dokumen Sokongan", permohonan.getIdPermohonan()));
//                        return null;
                        }
                    }

//                    List dokumenLLDS = enforceService.findDokumenListAndKodDokumen(permohonan.getIdPermohonan(), "LLDS");
//                    if (dokumenLLDS.isEmpty()) {
//                        sb.append("Lain-Lain Dokumen Sokongan<br/>");
////                        context.addMessage(String.format(mesej, "Lain-Lain Dokumen Sokongan", permohonan.getIdPermohonan()));
////                        return null;
//                    }

                    if (sb.length() > 0) {
                        context.addMessage(String.format(mesej, permohonan.getIdPermohonan(), sb.toString()));
                        return null;
                    }
                }
            }

            //added by ida 041213
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                if (stageId.equalsIgnoreCase("hantar_notis_kosongtanah")) {

                    KodNotis kodnotis = new KodNotis();
                    kodnotis = kodNotisDAO.findById("NK");
                    List<Notis> notis = enforcementService.findAllNotisByIdMohon(permohonan.getIdPermohonan(), kodnotis.getKod());

                    if (notis.isEmpty()) {
                        context.addMessage("Sila masukkan Maklumat Bukti Penyampaian Notis Kosongkan Tanah terlebih dahulu - " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
                if (stageId.equalsIgnoreCase("sedia_surat_kompaun")) {

                    KodNotis kodnotis = new KodNotis();
                    kodnotis = kodNotisDAO.findById("STK");
                    List<Notis> notis = enforcementService.findAllNotisByIdMohon(permohonan.getIdPermohonan(), kodnotis.getKod());

                    if (notis.isEmpty()) {
                        context.addMessage("Sila masukkan Maklumat Bukti Penyampaian Surat Tawaran Kompaun terlebih dahulu - " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
                if (stageId.equalsIgnoreCase("hantar_surat_jaminan")) {

                    KodNotis kodnotis = new KodNotis();
                    kodnotis = kodNotisDAO.findById("SJA");
                    List<Notis> notis = enforcementService.findAllNotisByIdMohon(permohonan.getIdPermohonan(), kodnotis.getKod());

                    if (notis.isEmpty()) {
                        context.addMessage("Sila masukkan Maklumat Bukti Penyampaian Surat Jaminan terlebih dahulu - " + permohonan.getIdPermohonan());
                        return null;
                    }
                }


            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {

                if (stageId.equalsIgnoreCase("imbas_warta")) {

                    KodRujukan kodruj = new KodRujukan();

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                        kodruj = kodRujDAO.findById("WA");
                    } else {
                        kodruj = kodRujDAO.findById("WB");
                    }

                    List<PermohonanRujukanLuar> rujluar = enforcementService.findKodRujByIDPermohonan(permohonan.getIdPermohonan(), kodruj.getKod());

                    if (rujluar.isEmpty()) {
                        context.addMessage("Sila masukkan Maklumat Terimaan Warta terlebih dahulu - " + permohonan.getIdPermohonan());
                        return null;
                    }
                }

                if (stageId.equalsIgnoreCase("terima_arahan1")) {

                    KodNotis kodnotis = new KodNotis();

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                        kodnotis = kodNotisDAO.findById("23A");
                    } else {
                        kodnotis = kodNotisDAO.findById("23B");
                    }

                    List<Notis> notis = enforcementService.findAllNotisByIdMohon(permohonan.getIdPermohonan(), kodnotis.getKod());

                    if (notis.isEmpty()) {
                        context.addMessage("Sila masukkan Maklumat Penghantar Notis/Borang terlebih dahulu - " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
            }
        }

        hakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonan = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
        LOG.info("--------------size hakmilikPermohonan------------- : " + hakmilikPermohonan.size());

        senaraiTanahMilik = new ArrayList<HakmilikPermohonan>();
        senaraiTanahMilik = enforceService.findSenaraiTanahMilik(permohonan.getIdPermohonan());
        LOG.info("--------------size senaraiTanahMilik------------- : " + senaraiTanahMilik.size());

        mohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
        mohonRujLuar = enforcementService.findMahkamahByIDPermohonan(permohonan.getIdPermohonan());
        LOG.info("------------------size kputusan mahmakah----------:" + mohonRujLuar.size());

        try {

            //validator to check keputusan mahkamah
            if (mohonRujLuar.size() == 0 && stageId.equalsIgnoreCase("rekod_trh_sebutan")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                    context.addMessage("Sila masukkan Maklumat Keputusan Mahkamah terlebih dahulu." + permohonan.getIdPermohonan());
                    return null;
                }
            }

            if (mohonRujLuar.size() != 0 && stageId.equalsIgnoreCase("rekod_trh_sebutan")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    for (int i = 0; i < mohonRujLuar.size(); i++) {
                        PermohonanRujukanLuar d = mohonRujLuar.get(i);
                        if (d != null) {
                            if (d.getKeputusanPendakwaan() != null) {

                                if (d.getKeputusanPendakwaan().getKod().equals("KP")) {
                                    statusDakwa = true;
                                }
                            }


                        }

                    }
                    if (statusDakwa == false) {
                        context.addMessage("Status Maklumat Keputusan Mahkamah masih belum sampai peringkat Keputusan." + permohonan.getIdPermohonan());
                        return null;
                    }

                }
            }

            if (mohonRujLuar.size() != 0 && stageId.equalsIgnoreCase("maklum_trh_sebutan")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    for (int i = 0; i < mohonRujLuar.size(); i++) {
                        PermohonanRujukanLuar d = mohonRujLuar.get(i);
                        if (d != null) {
                            if (d.getKeputusanPendakwaan() != null) {

                                if (d.getKeputusanPendakwaan().getKod().equals("KP")) {
                                    statusDakwa = true;
                                }
                            }


                        }

                    }
                    if (statusDakwa == false) {
                        context.addMessage("Status Maklumat Keputusan Mahkamah masih belum sampai peringkat Keputusan." + permohonan.getIdPermohonan());
                        return null;
                    }

                }
            }
            //maklum_trh_sebutan - slh satu kena KP


            if (permohonan.getAduan().getSenaraiOrangKenaSyak().isEmpty() && stageId.equalsIgnoreCase("buka_ks")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    if (permohonan.getPermohonanSebelum().getAduan().getSenaraiOrangKenaSyak().isEmpty()) {
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                            context.addMessage("Sila masukkan Maklumat Orang Disyaki di tab Orang Disyaki : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                    context.addMessage("Sila masukkan Maklumat Orang Disyaki di tab Orang Disyaki : " + permohonan.getIdPermohonan());
                    return null;
                }

            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127") && stageId.equalsIgnoreCase("sedia_laporan1")) {
                if (hakmilikPermohonan.size() == 0 && senaraiTanahMilik.size() == 0) {
                    //to force user to insert data to table mohon_hakmilik (using id hakmilik or tanah milik)
                    LOG.info("--------------HakmilikPermohonan Null-------------");
                    context.addMessage("Sila Masukkan Maklumat Hakmilik atau Tanah Milik di tab Laporan Tanah : " + permohonan.getIdPermohonan());
                    return null;
                }
            }

            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }
                }
            }

            //Check mohon fasa table based on id mohon
            FasaPermohonan fasa = enforcementService.findByStageId(permohonan.getIdPermohonan(), stageId);
            InfoAudit ia = new InfoAudit();

            if (fasa != null) {
                ia = fasa.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonan.setKeputusanOleh(fasa.getInfoAudit().getDimasukOleh());
            } else {
                fasa = new FasaPermohonan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }

            fasa.setIdAliran(stageId);
            fasa.setPermohonan(permohonan);
            fasa.setCawangan(pengguna.getKodCawangan());
            fasa.setIdPengguna(pengguna.getIdPengguna());
            fasa.setTarikhKeputusan(new Date());
            fasa.setInfoAudit(ia);
            fasaPermohonanService.saveOrUpdate(fasa);

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                // update table mohon
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {

                    if (stageId.equalsIgnoreCase("sediakan_laporan_awal")) {
                        if (keputusan.equalsIgnoreCase("TW")) { //TW = Tidak Wujud
                            LOG.info("-------1-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("terima_keputusan")) {
                        if (keputusan.equalsIgnoreCase("TT")) { //TT = Tiada Tindakan
                            LOG.info("-------2-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("buka_halangan")) {
                        if (keputusan.equalsIgnoreCase("BG")) {
                            LOG.info("-------3-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("kemaskini_rekod_kpsn")) {
                        LOG.info("-------4-------");
                        updateKeputusan(permohonan, pengguna);
                    }

                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("424") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
                    if (stageId.equalsIgnoreCase("keputusan_kes")) {
                        if (keputusan.equalsIgnoreCase("TK") || keputusan.equalsIgnoreCase("OP")) {  // TK = Tiada Kes , OP = Siasatan Polis
                            LOG.info("-------5-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("terima_keputusan")) {
                        if (keputusan.equalsIgnoreCase("TT")) { //TT = Tiada Tindakan
                            LOG.info("-------6-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("kemaskini_keputusan")) {
                        LOG.info("-------7-------");
                        updateKeputusan(permohonan, pengguna);
                    }

                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("keputusan_op")) {
                        if (keputusan.equalsIgnoreCase("OF")) { //TT = Tiada Berjaya
                            LOG.info("-------8-------");
                            updateKeputusan(permohonan, pengguna);
                        }
                    }
                    if (stageId.equalsIgnoreCase("maklum_kpsn_nfa")) {
                        LOG.info("-------9-------");
                        updateKeputusan(permohonan, pengguna);
                    }
                    if (stageId.equalsIgnoreCase("maklum_jawapan_kes")) {
                        LOG.info("-------10-------");
                        updateKeputusan(permohonan, pengguna);
                    }
                }

                //************ checking senarai dakwa ****************
                senaraiDakwaOks = new ArrayList<AduanOrangKenaSyak>();
                senaraiDakwaBarangRampasan = new ArrayList<BarangRampasan>();

                senaraiDakwaOks = enforceService.getSenaraiDakwaOks(permohonan.getIdPermohonan());
                LOG.info("-------size senarai dakwa oks------- :" + senaraiDakwaOks.size());

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")
                        || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(permohonan.getIdPermohonan());
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(permohonan.getIdPermohonan());
                }

                if (operasiPenguatkuasaan != null) {
                    senaraiDakwaBarangRampasan = enforceService.getSenaraiDakwaBarangRampasan(operasiPenguatkuasaan.getIdOperasi());
                }

                //check senarai dakwa for multiple op
                senaraiDakwaBarangRampasan = enforceService.findBarangRampasanForDakwa(permohonan.getIdPermohonan());
                LOG.info("-------size senarai dakwa barang rampasan : -------" + senaraiDakwaBarangRampasan.size());

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                    if (stageId.equalsIgnoreCase("semak_dok_dakwa")) {
                        if (senaraiDakwaOks.size() != 0) {
                            LOG.info("--------------senarai dakwa oks null-------------");
                            context.addMessage("Sila pilih orang yang hendak didakwa di tab Senarai Dakwa untuk Permohonan : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("semak_dokumen_dakwa1") || stageId.equalsIgnoreCase("semak_dok_dakwa")) { //sedia_srt_iringan semak_dok_dakwa
                        if (senaraiDakwaOks.size() != 0) {
                            LOG.info("--------------senarai dakwa barang rampasan null-------------");
                            context.addMessage("Sila pilih orang yang hendak didakwa di tab Senarai Dakwa untuk Permohonan : " + permohonan.getIdPermohonan());
                            return null;
                        }

                        senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());

                        LOG.info("size senaraiOksIp : " + senaraiOksIp.size());
                        if (!senaraiOksIp.isEmpty()) {
                            Long idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                            LOG.info("id operasi : " + idOpBasedOnIdIP);

                            if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                                senaraiDakwaBarangRampasan = enforceService.findListDakwaForIP(permohonan.getIdPermohonan(), idOpBasedOnIdIP);
                                LOG.info("-------yang ni laa size senarai dakwa barang rampasan : -------" + senaraiDakwaBarangRampasan.size());

                                senaraiDakwaOks = enforceService.findSenaraiDakwaOks(permohonan.getIdPermohonan(), idOpBasedOnIdIP);
                                LOG.info("-------size senarai dakwa oks based on id IP : -------" + senaraiDakwaOks.size());

                                if (senaraiDakwaOks.size() != 0) {
                                    LOG.info("--------------senarai dakwa barang rampasan null-------------");
                                    context.addMessage("Sila pilih orang yang hendak didakwa di tab Senarai Dakwa untuk Permohonan : " + permohonan.getIdPermohonan());
                                    return null;
                                }

                            }
                        }
                    }
                }

                List<Kompaun> senaraiKompaunIP = enforcementService.findKompaunIP(permohonan.getIdPermohonan());
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    if (stageId.equalsIgnoreCase("maklum_keputusan_kompaun")) {
                        if (senaraiKompaunIP.size() == 0) {
                            LOG.info("--------------senarai kompaun IP null-------------");
                            context.addMessage("Sila masukkan maklumat kompaun di tab Muktamad Kompaun untuk Permohonan : " + permohonan.getIdPermohonan());
                            return null;
                        }
                    }
                }

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    if (stageId.equalsIgnoreCase("sedia_notis")) {
                        Notis bilanganNotis = enforcementService.findMaxBil(permohonan.getIdPermohonan(), "NK");
                        if (bilanganNotis != null) {
                            if (StringUtils.isNotEmpty(bilanganNotis.getNoRujukan())) {
                                if (bilanganNotis.getNoRujukan().equalsIgnoreCase("1")) {
                                    bilanganNotis.setNoRujukan("0");
                                    enforcementService.simpanNotisPenguatkuasaan(bilanganNotis);
                                    LOG.info(":::: update table notis (set no rujukan to 0)");
                                }
                            }
                        }
                    }

                    if (stageId.equalsIgnoreCase("semak_notis")) {
                        if (!listOp.isEmpty()) {
                            for (OperasiPenguatkuasaan op : listOp) {
                                for (AduanOrangKenaSyak oks : op.getSenaraiAduanOrangKenaSyak()) {
                                    PermohonanKertas bilanganNotis = enforceService.findMaxBil(permohonan.getIdPermohonan(), "PKT", oks.getIdOrangKenaSyak());
                                    if (bilanganNotis != null) {
                                        if (StringUtils.isNotEmpty(bilanganNotis.getStatusKertas())) {
                                            if (bilanganNotis.getStatusKertas().equalsIgnoreCase("1")) {
                                                bilanganNotis.setStatusKertas("0");
                                                enforceService.simpanPermohonanKertas(bilanganNotis);
                                                LOG.info(":::: update table PermohonanKertas (set statusKertas to 0)");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                // *********************** for NS ***********************
                String outcome = "";
                List<Notis> listNotis = new ArrayList<Notis>();

                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("hantar_borang7e") || stageId.equalsIgnoreCase("sedia_borang7e") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7e")) { //TI = Tidak diserah, S = Diserah
                        listNotis = enforcementService.findListNotisByIdAndTempat8Null(permohonan.getIdPermohonan(), "N7E");
                    } else if (stageId.equalsIgnoreCase("hantar2_borang7f") || stageId.equalsIgnoreCase("sedia_borang7f") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7f") || stageId.equalsIgnoreCase("bukti2_penyampaian_gantian_b7f")) { //TI = Tidak diserah, S = Diserah
                        listNotis = enforcementService.findListNotisByIdAndTempat8Null(permohonan.getIdPermohonan(), "N7F");
                    } else if (stageId.equalsIgnoreCase("hantar_borang7a") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7a")) { //TI = Tidak diserah, S = Diserah
                        listNotis = enforcementService.findListNotisByIdAndTempat8Null(permohonan.getIdPermohonan(), "7A");
                    } else if (stageId.equalsIgnoreCase("hantar_borang7b") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7b")) { //TI = Tidak diserah, S = Diserah
                        listNotis = enforcementService.findListNotisByIdAndTempat8Null(permohonan.getIdPermohonan(), "7B");
                    } else if (stageId.equalsIgnoreCase("bukti_penyampaian_b8a")) { //TI = Tidak diserah, S = Diserah
                        listNotis = enforcementService.findListNotisByIdAndTempat8Null(permohonan.getIdPermohonan(), "8A");
                    }

                    if (stageId.equalsIgnoreCase("hantar_borang7e")
                            || stageId.equalsIgnoreCase("sedia_borang7e")
                            || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7e")
                            || stageId.equalsIgnoreCase("hantar2_borang7f")
                            || stageId.equalsIgnoreCase("sedia_borang7f")
                            || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7f")
                            || stageId.equalsIgnoreCase("bukti2_penyampaian_gantian_b7f")
                            || stageId.equalsIgnoreCase("hantar_borang7a")
                            || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7a")
                            || stageId.equalsIgnoreCase("hantar_borang7b")
                            || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7b")
                            || stageId.equalsIgnoreCase("bukti_penyampaian_b8a")) {
                        if (listNotis.isEmpty()) {
                            context.addMessage("Sila masukkan Bukti Penyampaian Notis : " + permohonan.getIdPermohonan());
                            return null;
                        } else {
                            for (Notis notis : listNotis) {
                                if (notis.getKodStatusTerima().getKod().equalsIgnoreCase("XT")) {
                                    outcome = "TI";
                                    break;
                                } else {
                                    outcome = "S";
                                }
                            }

                            if (!outcome.isEmpty()) {
                                proposedOutcome = outcome;
                            }
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    if (stageId.equalsIgnoreCase("maklum_bayaran_kompaun")) {
                        proposedOutcome = "COMPLETE";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("100")) {
                    if (stageId.equalsIgnoreCase("Tindakan_425")) {
                        //Hafifi 2/4/2014 : Integrate to urusan Seksyen 425 KTN
                        Permohonan p = this.initiateToPenguatkuasaan(permohonan, pengguna, "425");
                        context.addMessage(" - Penghantaran Berjaya. Id Permohonan untuk penguatkuasaan Seksyen 425 KTN : " + p.getIdPermohonan());
                    } else if (stageId.equalsIgnoreCase("beri_arahan")) {
                        //Hafifi 2/4/2014 : Jangan benarkan user proceed to next stage jika endorsan 8A belum matang 3 bulan
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (stageId.equalsIgnoreCase("g_charting_keputusan")) {
                        outcome = permohonan.getKeputusan().getKod();
                    }

                    if (!outcome.isEmpty()) {
                        proposedOutcome = outcome;
                    }
                }

                if (StringUtils.isNotBlank(keputusan)) {
                    proposedOutcome = keputusan;
                }

                updateKeputusan(permohonan, pengguna, stageId);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
            return null;
        }

        return proposedOutcome;
//          return null;
    }

    public Permohonan initiateToPenguatkuasaan(Permohonan permohonan, Pengguna pengguna, String kodUrusan) throws ParseException, Exception {
        LOG.debug("----initiate To Penguatkuasaan Seksyen " + kodUrusan + " ----:");
        try {
            KodUrusan kod_urusan = kodUrusanDAO.findById(kodUrusan);
            return this.generatePermohonanBaru(permohonan, kod_urusan, pengguna);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Permohonan generatePermohonanBaru(Permohonan permohonan, KodUrusan ku, Pengguna pengguna) throws WorkflowException, StaleObjectException, Exception {
        try {
            LOG.info("----initiating ID Mohon----:");
            KodCawangan caw = permohonan.getCawangan();
            Permohonan p = new Permohonan();
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            FolderDokumen fd = new FolderDokumen();
            long idFolder = Long.parseLong(tarikh);
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);
            p.setStatus("TA");
            p.setIdPermohonan(idGenMohon.generate(conf.getProperty("kodNegeri"), caw, ku));
            LOG.info("----ID Mohon Generated----:");
            p.setCawangan(permohonan.getCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                p.setPenyerahNama("Unit Penguatkuasaan " + pengguna.getKodCawangan().getName());
                KodCaraPermohonan caraPermohonan = new KodCaraPermohonan();
                caraPermohonan.setKod("UD"); //Unit dalaman
                p.setCaraPermohonan(caraPermohonan);
                p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(pengguna.getKodCawangan().getAlamat().getAlamat1());
                p.setPenyerahAlamat2(pengguna.getKodCawangan().getAlamat().getAlamat2());
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                if (pengguna.getKodCawangan().getAlamat().getAlamat3() != null) {
                    p.setPenyerahAlamat3(pengguna.getKodCawangan().getAlamat().getAlamat3());
                }

                if (pengguna.getKodCawangan().getAlamat().getAlamat4() != null) {
                    p.setPenyerahAlamat4(pengguna.getKodCawangan().getAlamat().getAlamat4());
                }
                p.setPenyerahPoskod(pengguna.getKodCawangan().getAlamat().getPoskod());
                p.setPenyerahNegeri(pengguna.getKodCawangan().getAlamat().getNegeri());
                //added by murali: saving stageIdSblm
                Map m = tds.traceTask(permohonan.getIdPermohonan());
                String stageId = (String) m.get("stage");
                LOG.info("--stageId--:" + stageId);
                if (stageId != null) {
                    p.setIdStagePermohonanSebelum(stageId);
                }

                //Hafifi 2/4/2014 : Masukkan data laporan tanah
                LaporanTanah laporanTanah = enforceService.findByIDMohon(permohonan.getIdPermohonan());
                if (laporanTanah != null) {
                    //Hafifi 2/4/2014 : 
                    LaporanTanah laporanTanahBaru = new LaporanTanah();
                    laporanTanahBaru.setInfoAudit(ia);
                    laporanTanahBaru.setJenisLaporan("LTNH");
                    laporanTanahBaru.setHakmilikPermohonan(laporanTanah.getHakmilikPermohonan());
                    laporanTanahBaru.setPermohonan(laporanTanah.getPermohonan());
                    laporanTanahBaru.setNamaSempadanJalanraya(laporanTanah.getNamaSempadanJalanraya());
                    laporanTanahBaru.setJarakSempadanJalanraya(laporanTanah.getJarakSempadanJalanraya());
                    laporanTanahBaru.setNamaSempadanKeretapi(laporanTanah.getNamaSempadanKeretapi());
                    laporanTanahBaru.setJarakSempadanKeretapi(laporanTanah.getJarakSempadanKeretapi());
                    laporanTanahBaru.setNamaSempadanLaut(laporanTanah.getNamaSempadanLaut());
                    laporanTanahBaru.setJarakSempadanLaut(laporanTanah.getJarakSempadanLaut());
                    laporanTanahBaru.setNamaSempadanSungai(laporanTanah.getNamaSempadanSungai());
                    laporanTanahBaru.setJarakSempadanSungai(laporanTanah.getJarakSempadanSungai());
                    laporanTanahBaru.setNamaSempadanlain(laporanTanah.getNamaSempadanlain());
                    laporanTanahBaru.setJarakSempadanLain(laporanTanah.getJarakSempadanLain());
                    laporanTanahBaru.setAdaJalanMasuk(laporanTanah.getAdaJalanMasuk());
                    laporanTanahBaru.setCatatanJalanMasuk(laporanTanah.getCatatanJalanMasuk());
                    laporanTanahBaru.setJenisJalan(laporanTanah.getJenisJalan());
                    laporanTanahBaru.setKecerunanTanah(laporanTanah.getKecerunanTanah());
                    laporanTanahBaru.setKetinggianDariJalan(laporanTanah.getKetinggianDariJalan());
                    laporanTanahBaru.setKecerunanBukit(laporanTanah.getKecerunanBukit());
                    laporanTanahBaru.setParasAir(laporanTanah.getParasAir());
                    laporanTanahBaru.setStrukturTanah(laporanTanah.getStrukturTanah());
                    laporanTanahBaru.setStrukturTanahLain(laporanTanah.getStrukturTanahLain());
                    laporanTanahBaru.setKodKeadaanTanah(laporanTanah.getKodKeadaanTanah());
                    laporanTanahBaru.setTanahBertumpu(laporanTanah.getTanahBertumpu());
                    laporanTanahBaru.setKeteranganTanahBertumpu(laporanTanah.getKeteranganTanahBertumpu());
                    laporanTanahBaru.setDilintasLaluanGas(laporanTanah.getDilintasLaluanGas());
                    laporanTanahBaru.setDilintasPaip(laporanTanah.getDilintasPaip());
                    laporanTanahBaru.setDilintasParit(laporanTanah.getDilintasParit());
                    laporanTanahBaru.setDilintasSungai(laporanTanah.getDilintasSungai());
                    laporanTanahBaru.setDilintasTaliar(laporanTanah.getDilintasTaliar());
                    laporanTanahBaru.setDilintasTiangElektrik(laporanTanah.getDilintasTiangElektrik());
                    laporanTanahBaru.setDilintasTiangTelefon(laporanTanah.getDilintasTiangTelefon());
                    laporanTanahBaru.setDilintasiLain(laporanTanah.getDilintasiLain());
//                    laporanTanah.setUsaha(laporanTanahAwal.getUsaha());
//                    laporanTanah.setDiusaha(laporanTanahAwal.getDiusaha());
                    laporanTanahBaru.setPerenggan(laporanTanah.getPerenggan());
                    laporanTanahBaru.setPerengganLuas(laporanTanah.getPerengganLuas());
                    laporanTanahBaru.setPerengganLuasUom(laporanTanah.getPerengganLuasUom());
                    laporanTanahBaru.setRancanganKerajaan(laporanTanah.getRancanganKerajaan());
                    laporanTanahBaru.setJenisTanah(laporanTanah.getJenisTanah());
                    disLaporanTanahService.getPlpservice().simpanLaporanTanah(laporanTanahBaru);
                }

                HakmilikPermohonan hakmilikPermohonan = enforceService.findhakmilikPermohonanByIdpermohonan(permohonan.getIdPermohonan());
                if (hakmilikPermohonan != null) {
                    HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
                    hakmilikPermohonanBaru.setInfoAudit(ia);
                    hakmilikPermohonanBaru.setPermohonan(permohonan);
                    hakmilikPermohonanBaru.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                    hakmilikPermohonanBaru.setKodUnitLuas(hakmilikPermohonan.getKodUnitLuas());
                    hakmilikPermohonanBaru.setBandarPekanMukimBaru(hakmilikPermohonan.getBandarPekanMukimBaru());
                    hakmilikPermohonanBaru.setLot(hakmilikPermohonan.getLot());
                    hakmilikPermohonanBaru.setNoLot(hakmilikPermohonan.getNoLot());
                    hakmilikPermohonanBaru.setLokasi(hakmilikPermohonan.getLokasi());
                    hakmilikPermohonanBaru.setJarak(hakmilikPermohonan.getJarak());
                    hakmilikPermohonanBaru.setJarakDari(hakmilikPermohonan.getJarakDari());
                    hakmilikPermohonanBaru.setJarakDariKediaman(hakmilikPermohonan.getJarakDariKediaman());
                    hakmilikPermohonanBaru.setJarakDariKediamanUom(hakmilikPermohonan.getJarakDariKediamanUom());
                    disLaporanTanahService.getPlpservice().saveOrUpdateHakmilikPermohonan(hakmilikPermohonanBaru);
                }
            }
            p.setInfoAudit(ia);
            enforceService.savePermohonan(p);

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

            return p;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void updateKeputusan(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("-------updateKeputusan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKeputusan(kodKeputusanDAO.findById("SV")); // SV = kes selesai
            enforceService.simpanPermohonan(permohonan);

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

            permohonan.setInfoAudit(ia);
            enforceService.simpanPermohonan(permohonan);

        }
    }

    public void updateKodKeputusanPermohonan(Permohonan permohonan, Pengguna pengguna, String kodkeputusan) {
        LOG.info("-------updateKodKeputusanPermohonan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKeputusan(kodKeputusanDAO.findById(kodkeputusan));
            enforceService.simpanPermohonan(permohonan);

        }
    }

    public void janaDokumen(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport) {
        try {
            //if ("04".equals(conf.getProperty("kodNegeri"))) {
            //for Melaka, need to generate report for Perlantikan Pegawai Penyiasat
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");

            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";

            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
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
        if (!etanah.util.StringUtils.isBlank(kd.getNama())) {
            doc.setTajuk(kd.getNama());
        }
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
    public void afterComplete(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

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

        //update table mohon(column : kod_kpsn)

        List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

        for (FasaPermohonan fp : senaraiFasa) {
            if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                if (fp.getKeputusan() != null) {
                    keputusan = fp.getKeputusan().getKod();
                    LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                }
            }
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                if (stageId.equalsIgnoreCase("terima_bayaran_kompaun")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SC");//SC = Kes Selesai Kompaun
                }
                if (stageId.equalsIgnoreCase("kemaskini_kpsn_mahkamah1")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SF");//SF = Kes Selesai Pendakwaan
                }
                if (stageId.equalsIgnoreCase("maklum_kpsn_nfa")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SO");//SO = Kes Selesai Tiada Tindakan Lanjut
                }
                if (stageId.equalsIgnoreCase("maklum_keputusan_pelupusan")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SQ");//SQ = Kes Selesai Barang Dilupus
                }
                if (stageId.equalsIgnoreCase("maklum_kpsn_jualan")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SR");//SR = Kes Selesai Barang Dijual
                }
                if (stageId.equalsIgnoreCase("maklum_jawapan_kes")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SU");//SU = Kes Selesai Tiada Kes
                }
                if (stageId.equalsIgnoreCase("imbas_kemaskini_brg")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SW");//SU = Kes Selesai Barang Dilepas
                }

            }
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                if (stageId.equalsIgnoreCase("bayaran_kompaun")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SC");//SC = Kes Selesai Kompaun
                }
                if (stageId.equalsIgnoreCase("kemaskini_rekod_keputusan")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SF");//SF = Kes Selesai Pendakwaan
                }
                if (stageId.equalsIgnoreCase("maklum_kpsn_nfa")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SO");//SO = Kes Selesai Tiada Tindakan Lanjut
                }
                if (stageId.equalsIgnoreCase("maklum_kpsn_pelupusan")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SQ");//SQ = Kes Selesai Barang Dilupus
                }
                if (stageId.equalsIgnoreCase("maklum_kpsn_jualan1")) {
                    updateKodKeputusanPermohonan(permohonan, pengguna, "SR");//SR = Kes Selesai Barang Dijual
                }
                if (stageId.equalsIgnoreCase("keputusan_op")) {
                    if (keputusan.equalsIgnoreCase("TK")) {
                        updateKodKeputusanPermohonan(permohonan, pengguna, "SU");//SU = Kes Selesai Tiada Kes
                    }
                }
                if (stageId.equalsIgnoreCase("keputusan_laporan2")) {
                    if (keputusan.equalsIgnoreCase("A")) {
                        updateKodKeputusanPermohonan(permohonan, pengguna, "CU");//CU = Kes Selesai Patuh Kosongkan Tanah
                    }
                }
                if (stageId.equalsIgnoreCase("rekod_kpsn_mmkn")) {
                    if (keputusan.equalsIgnoreCase("T")) {
                        updateKodKeputusanPermohonan(permohonan, pengguna, "CV");//CV = Kes Selesai MMKN Tolak
                    }
                }
                if (stageId.equalsIgnoreCase("enkuiri_kpsn_denda")) {
                    if (keputusan.equalsIgnoreCase("TD")) {
                        updateKodKeputusanPermohonan(permohonan, pengguna, "CW");//SU = Kes Selesai Tiada Denda
                    }
                }

            }
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    //jana dokumen for Surat Keputusan MMKN
                    janaDokumen(permohonan, pengguna, "DISSKpsnMMKNPLPS_MLK.rdf", "SKM");

                    ArrayList kodDokumenTerlibat = new ArrayList<String>();
                    kodDokumenTerlibat.add("RMN");
                    kodDokumenTerlibat.add("KMN");
                    kodDokumenTerlibat.add("SKM");
                    try {
                        FolderDokumen f = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                        int c = 1;
                        for (Object s : kodDokumenTerlibat) {
                            List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), s.toString());
                            LOG.info(c + "::update table dokumen(kod klasifikasi) : stage id ::: " + stageId + " & kod dokumen :: " + s.toString());
                            for (int i = 0; i < listDokumen.size(); i++) {
                                Dokumen d = listDokumen.get(i).getDokumen();
                                if (d != null) {
                                    if (pengguna.getTahapSekuriti() != null) {
                                        d.setKlasifikasi(kodKlasifikasiDAO.findById(pengguna.getTahapSekuriti().getKod()));
                                        enforcementService.simpanDokumen(d);
                                    }

                                }

                            }
                            c++;
                        }

                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
            }

        } else {
            //Hafifi 23/1/2014 : hantar notifikasi status bayaran kompaun
            Notifikasi n = new Notifikasi();
            List<HakmilikPermohonan> senaraiHakmilik = context.getPermohonan().getSenaraiHakmilik();
            String message = "";

            kodUrusan = permohonan.getKodUrusan().getKod();

            if (kodUrusan.equals("422") || kodUrusan.equals("423") || kodUrusan.equals("424") || kodUrusan.equals("427") || kodUrusan.equals("428")) {
                if (stageId.equalsIgnoreCase("terima_byrn_denda")) {
                    n.setTajuk("Maklum Bayaran Kompaun bagi urusan dibawah Seksyen " + kodUrusan + " KTN (ID Permohonnan : " + context.getPermohonan().getIdPermohonan() + ")");
                    n.setMesej("Dimaklumkan bahawa satu bayaran kompaun telah dibuat bagi satu kesalahan dibawah Seksyen " + kodUrusan + " KTN bagi ID Permohonan " + context.getPermohonan().getIdPermohonan());

                    Pengguna p = context.getPengguna();
                    KodCawangan cawangan = kodCawanganDAO.findById("00");
                    n.setCawangan(cawangan);
                    ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new Date());

                    //list.add(kodPerananDAO.findById("234")); //N9 - PTG (PAT)
                    list.add(kodPerananDAO.findById("12")); //N9 - PTG (FAT)                
                    n.setInfoAudit(ia);
                    notifikasiService.addRolesToNotifikasi(n, cawangan, list);

                    list = new ArrayList<KodPeranan>();
                    list.add(kodPerananDAO.findById("10")); //N9 - PTD (FAT)
                    n.setInfoAudit(ia);
                    notifikasiService.addRolesToNotifikasi(n, cawangan, list);

                    message += " dan makluman kepada Pengarah Tanah dan Galian dan Pentadbir Tanah Daerah telah dihantar.";

                    context.addMessage(message);
                }
            }
        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
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

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
            listOp = enforceService.findListLaporanOperasi(permohonan.getIdPermohonan());
            System.out.println("listOp size : " + listOp.size());
            if (listOp.size() != 0) {
                System.out.println("opFlag true");
            }
        } else {
            //for Melaka, only pass idPermohonan
            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(permohonan.getIdPermohonan());
        }

        if (stageId.equalsIgnoreCase("semak_notis")) {
            if (!listOp.isEmpty()) {
                for (OperasiPenguatkuasaan op : listOp) {
                    for (AduanOrangKenaSyak oks : op.getSenaraiAduanOrangKenaSyak()) {
                        PermohonanKertas bilanganNotis = enforceService.findMaxBil(permohonan.getIdPermohonan(), "PKT", oks.getIdOrangKenaSyak());
                        if (bilanganNotis != null) {
                            if (StringUtils.isNotEmpty(bilanganNotis.getStatusKertas())) {
                                if (bilanganNotis.getStatusKertas().equalsIgnoreCase("1")) {
                                    bilanganNotis.setStatusKertas("0");
                                    enforceService.simpanPermohonanKertas(bilanganNotis);
                                    LOG.info(":::: update table PermohonanKertas (set statusKertas to 0)");
                                }
                            }
                        }
                    }
                }
            }
        }

        return "back";
    }
}

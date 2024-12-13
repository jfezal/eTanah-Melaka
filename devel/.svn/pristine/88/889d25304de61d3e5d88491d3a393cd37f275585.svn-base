package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.report.ReportUtil;
import etanah.service.ConsentPtdService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/cetak_semula")
public class CetakSemulaActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ConsentPtdService consentService;
    private Permohonan permohonan;
    private Pihak pihak;
//    private Pihak pihakSearch;
    private Pengguna pengguna;
    private String kodNegeri;
    private List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandunganGenarated = new ArrayList<KandunganFolder>();
    List<DokumenKewangan> senaraiDokumenKewangan = new ArrayList<DokumenKewangan>();
    private static final Logger LOGGER = Logger.getLogger(CetakSemulaActionBean.class);
    etanahActionBeanContext ctx;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/cetak_semula.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() {
        kodNegeri = conf.getProperty("kodNegeri");
        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");
        if (permohonan == null && pihak == null) {
            addSimpleError("Sila Masukkan Nombor Pengenalan Atau ID Permohonan.");
        } else {

            if (pihak != null) {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Masukkan Jenis Pengenalan.");
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {


//                    pihakSearch = new Pihak();
//                    pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                    List<Pihak> senaraiPihak = pihakService.findPihakBynoKPkodKP(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                    if (!senaraiPihak.isEmpty()) {

                        LOGGER.info(":: SIZE PIHAK : " + senaraiPihak.size());

                        for (Pihak pihakSearch : senaraiPihak) {

                            LOGGER.info(":: NAMA : " + pihakSearch.getNama());

//                    if (pihakSearch != null) {
                            List<Pemohon> pemohonList;
                            pemohonList = pemohonService.findPemohonByIdPihak(pihakSearch);
                            if (!pemohonList.isEmpty()) {

                                senaraiPermohonan = new ArrayList<Permohonan>();

                                for (Pemohon pemohon : pemohonList) {

                                    if (pemohon.getPermohonan().getKodUrusan().getJabatan().getKod().equals("22")) {

                                        LOGGER.info(":::::STATUS : " + pemohon.getPermohonan().getIdPermohonan() + " - " + pemohon.getPermohonan().getStatus());
                                        if (pemohon.getPermohonan().getStatus().equals("SL")) {
                                            if (pemohon.getPermohonan().getInfoAudit().getDimasukOleh().getIdPengguna().equals("ECON")) {

                                                senaraiPermohonan.add(pemohon.getPermohonan());

                                            } else {
                                                boolean report = false;

                                                if (!pemohon.getPermohonan().getFolderDokumen().getSenaraiKandungan().isEmpty()) {

                                                    for (KandunganFolder kFolder : pemohon.getPermohonan().getFolderDokumen().getSenaraiKandungan()) {

                                                        if (kFolder.getDokumen().getKodDokumen().getKod().equals("SK")
                                                                || kFolder.getDokumen().getKodDokumen().getKod().equals("SJK")
                                                                || kFolder.getDokumen().getKodDokumen().getKod().equals("PBI")
                                                                || kFolder.getDokumen().getKodDokumen().getKod().equals("SK2")) {
                                                            report = true;
                                                        }
                                                    }
                                                }
                                                if (report) {
                                                    senaraiPermohonan.add(pemohon.getPermohonan());
                                                }
                                            }
                                        }
                                    }
                                }

                                if (senaraiPermohonan.size() > 0) {
                                    addSimpleMessage("Carian berjaya. Sila jana dokumen tersebut.");
                                }

//                                else {
//                                    addSimpleError("Carian tidak berjaya.");
//                                }
                            }

//                            else {
//                                addSimpleError("Carian tidak berjaya.");
//                            }
                        }
                    } else {
                        addSimpleError("Carian tidak berjaya.");
                    }
                }
            }
            if (permohonan != null) {
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

                if (permohonan != null) {
                    LOGGER.info(":::::STATUS : " + permohonan.getIdPermohonan() + " - " + permohonan.getStatus());

                    if (permohonan.getStatus().equals("SL")) {

                        if (permohonan.getInfoAudit().getDimasukOleh().getIdPengguna().equals("ECON")) {

                            senaraiPermohonan.add(permohonan);
                            addSimpleMessage("Carian berjaya. Sila papar dokumen tersebut.");

                        } else {

                            senaraiDokumenKewangan = consentService.findSenaraiDokumenKewanganByNoRujukan(permohonan.getIdPermohonan());

                            if (!senaraiDokumenKewangan.isEmpty()) {
                                senaraiKandungan = new ArrayList<KandunganFolder>();

                                if (!permohonan.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                                    for (KandunganFolder kFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                                        if (kFolder.getDokumen().getKodDokumen() != null) {
                                            if (kFolder.getDokumen().getKodDokumen().getKod().equals("SK")
                                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("SJK")
                                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("PBI")
                                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("SK2")) {
                                                senaraiKandungan.add(kFolder);
                                            }
                                        }
                                    }
                                    if (senaraiKandungan.size() > 0) {
                                        addSimpleMessage("Carian berjaya. Sila jana dokumen tersebut.");
                                    } else {
                                        addSimpleError("Tiada dokumen untuk dicetak bagi permohonan ini.");
                                    }
                                }
                            } else {
                                if (kodNegeri.equals("05")) {
                                    addSimpleError("Sila pastikan bayaran cetakan semula telah dibuat. (Salinan Sah Dokumen-73199)");
                                } else {
                                    addSimpleError("Sila pastikan bayaran cetakan semula telah dibuat. (Salinan Sah Dokumen-72499)");
                                }
                            }
                        }

                    } else {
                        if (permohonan.getStatus().equals("TK")) {
                               addSimpleMessage("Permohonan ini telah dibatalkan.");
                        } 
                        else {
                               addSimpleError("Permohonan ini belum diselesaikan.");
                        }
                    }
                } else {
                    addSimpleError("Carian tidak berjaya.");
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/cetak_semula.jsp");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        pihak = new Pihak();
        senaraiKandungan = null;
        senaraiPermohonan = null;

        return new ForwardResolution("/WEB-INF/jsp/consent/cetak_semula.jsp");
    }

    public Resolution generateReports() {

        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = conf.getProperty("kodNegeri");
        LOGGER.info("::: KOD NEGERI " + kodNegeri + " :::");

        pengguna = (Pengguna) ctx.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = ctx.getRequest().getParameter("idPermohonan");
        LOGGER.info("--ID PERMOHONAN PARAMETER " + idPermohonan);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        } else {
            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        }

        if (permohonan != null) {
            senaraiDokumenKewangan = new ArrayList<DokumenKewangan>();
            senaraiDokumenKewangan = consentService.findSenaraiDokumenKewanganByNoRujukan(permohonan.getIdPermohonan());
            if (!senaraiDokumenKewangan.isEmpty()) {

                LOGGER.info("--JANA REPORT UNTUK CETAKAN SEMULA " + permohonan.getIdPermohonan());
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{permohonan.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                String reportName = "";
                String kodUrusan = permohonan.getKodUrusan().getKod();

                for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                    String kodDokumenCheck = kandunganFolder.getDokumen().getKodDokumen().getKod();

                    if (kodDokumenCheck.equals("SK") || kodDokumenCheck.equals("SJK") || kodDokumenCheck.equals("PBI") || kodDokumenCheck.equals("SK2")) {

                        //FOR NEGERI SEMBILAN
                        if (kodNegeri.equals("05")) {
                            //*************SURAT KEBENARAN (SK)*************
                            if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK")) {

                                kd = kodDokumenDAO.findById("SKC");
                                //-----PTD-----
                                if (kodUrusan.startsWith("PPTD")) {
                                    reportName = "CONS_Surat_Keb_PMT_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("GPTD")) {
                                    reportName = "CONS_Surat_Keb_GD_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("PJPTD")) {
                                    reportName = "CONS_Surat_Keb_PJK_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("PCPTD")) {
                                    reportName = "CONS_Surat_Keb_PMTGD_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("DPWNA")) {
                                    reportName = "CONS_SuratKelulusan_PTD_WNA.rdf";
                                } //-----PTG-----
                                else if (kodUrusan.startsWith("PMMAT")) {
                                    reportName = "CONS_SuratKelulusan_PTG_WNA.rdf";
                                } //-----MB-----
                                else if (kodUrusan.startsWith("PMTMB")) {
                                    reportName = "CONS_Surat_Keb_PMT_MB_NS.rdf";
                                } else if (kodUrusan.startsWith("PGDMB")) {
                                    reportName = "CONS_Surat_Keb_PMTGD_MB_NS.rdf";
                                } else if (kodUrusan.startsWith("MCGMB")) {
                                    reportName = "CONS_Surat_Keb_GD_MB_NS.rdf";
                                } else if (kodUrusan.startsWith("MCGMB")) {
                                    reportName = "CONS_Surat_Keb_GD_MB_NS.rdf";
                                } //-----MMK-----
                                else if (kodUrusan.startsWith("PMMK")) {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                } else if (kodUrusan.startsWith("PCMMK")) {
                                    reportName = "CONS_Surat_Keb_PMTGD_MMK_NS.rdf";
                                } else if (kodUrusan.startsWith("PJMMK")) {
                                    reportName = "CONS_Surat_Keb_PJK_MMK_NS.rdf";
                                } else if (kodUrusan.startsWith("MCMMK")) {
                                    reportName = "CONS_Surat_Keb_GD_MMK_NS.rdf";
                                } else if (kodUrusan.startsWith("GSMMK")) {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                }//-----TANAH LADANG-----
                                else if (kodUrusan.startsWith("PJKTL")) {
                                    reportName = "CONS_SRT_TL_NS.rdf";
                                } else if (kodUrusan.startsWith("PMJTL")) {
                                    if (permohonan.getKeputusan().getKod().equals("T")) {
                                        reportName = "CONS_SRT_TL_NS.rdf";
                                    } else {
                                        reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                    }
                                }//-----RAYUAN TANAH LADANG-----
                                else if (kodUrusan.startsWith("RMJTL") || kodUrusan.startsWith("RJKTL")) {
                                    reportName = "CONS_Surat_Keb_PMT_MMK_NS.rdf";
                                } //-----TANAH ADAT-----
                                if (kodUrusan.startsWith("PMADT")) {
                                    reportName = "CONS_Surat_Keb_PMADT_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("TMADT")) {
                                    reportName = "CONS_Surat_Keb_TMADT_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("CGADT")) {
                                    reportName = "CONS_Surat_Keb_CGADT_PTD.rdf";
                                } else if (kodUrusan.startsWith("TTADT")) {
                                    reportName = "CONS_Surat_Keb_TTADT_PTD_NS.rdf";
                                } else if (kodUrusan.startsWith("PJADT")) {
                                    reportName = "CONS_Surat_Keb_PJADT_PTD.rdf";
                                } else if (kodUrusan.startsWith("PMWNA")) {
                                    reportName = "CONS_Surat_Keb_PMADT_PTD.rdf";
                                } else if (kodUrusan.startsWith("TMWNA")) {
                                    if (permohonan.getKeputusan().getKod().equals("L")) {
                                        reportName = "CONS_Surat_Keb_PMT_PTD_NS.rdf";
                                    } else {
                                        reportName = "CONS_Surat_Keb_TMADT_PTD_NS.rdf";

                                    }
                                } //-----BANTAHAN TANAH ADAT-----
                                else if (kodUrusan.startsWith("BTADT")) {
                                    reportName = "CONS_Surat_Keb_BTADT_PTD_NS.rdf";
                                }
                            } //*************SIJIL KELULUSAN*************
                            else if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SJK")) {

                                kd = kodDokumenDAO.findById("SJKC");
                                //-----MB-----
                                if (kodUrusan.startsWith("PMTMB")) {
                                    reportName = "CONS_SIJIL_MB_PMT_NS.rdf";
                                } else if (kodUrusan.startsWith("PGDMB")) {
                                    reportName = "CONS_SIJIL_MB_GD_NS.rdf";
                                } else if (kodUrusan.startsWith("MCGMB")) {
                                    reportName = "CONS_SIJIL_MB_PMT_GD_NS.rdf";
                                } //-----TANAH LADANG-----
                                else if (kodUrusan.startsWith("PJKTL") || kodUrusan.startsWith("PMJTL")) {
                                    reportName = "CONSSijilMhnPmlkTnhLadang001.rdf";
                                } //-----RAYUAN TANAH LADANG-----
                                else if (kodUrusan.startsWith("RMJTL") || kodUrusan.startsWith("RJKTL")) {
                                    reportName = "CONSSijilMhnPmlkTnhLadang001.rdf";
                                }
                            }//*************PERINTAH BICARA*************
                            else if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("PBI")) {
                                kd = kodDokumenDAO.findById("PBIC");
                                if (kodUrusan.startsWith("PMADT")) {
                                    reportName = "CONSPerintahSeksyen7Adat.rdf";
                                } else if (kodUrusan.startsWith("TMADT")) {
                                    reportName = "CONSPerintahSeksyen10Adat_JadualF.rdf";
//                                CONSPerintahSeksyen10Adat_PAADT_JadualF -- ADA PA
                                } else if (kodUrusan.startsWith("CGADT")) {
                                    reportName = "CONSPerintahSeksyen7Adat.rdf";
                                } else if (kodUrusan.startsWith("TTADT")) {
                                    reportName = "CONSPerintahSeksyen9Adat.rdf";
                                } else if (kodUrusan.startsWith("PJADT")) {
                                    reportName = "CONSPerintahSeksyen7Adat.rdf";
                                } else if (kodUrusan.startsWith("PMWNA")) {
                                    reportName = "CONSPerintahSeksyen7Adat.rdf";
                                } else if (kodUrusan.startsWith("TMWNA")) {
                                    reportName = "CONSPerintahSeksyen10Adat_JadualF.rdf";
//                                CONSPerintahSeksyen10Adat_PAADT_JadualF --  ADA PA
                                }
                            }
                        } //FOR NEGERI MELAKA
                        else {
                            //*************SURAT KELULUSAN*************
                            if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SK")) {

                                kd = kodDokumenDAO.findById("SKC");
                                //-----KM-----
                                if (kodUrusan.startsWith("PMKMM")) {
                                    reportName = "CONS_SRT_KEB_PMT_MLK.rdf";
                                } else if (kodUrusan.startsWith("MCKMM")) {
                                    reportName = "CONS_SRT_KEB_CGR_MLK.rdf";
                                } else if (kodUrusan.startsWith("PJKMM")) {
                                    reportName = "CONS_SRT_KEB_PJK_MLK.rdf";
                                } else if (kodUrusan.startsWith("SWKMM")) {
                                    reportName = "CONS_SRT_KEB_SW_MLK.rdf";
                                } //-----PTG-----
                                else if (kodUrusan.startsWith("PPTGM")) {
                                    reportName = "CONS_SRT_KEB_PMT_MLK_PTG.rdf";
                                } //-----MMK HARTANAH-----
                                else if (kodUrusan.startsWith("PMMK1")) {
                                    reportName = "CONS_SRT_KEB_PMMMK_H_MLK.rdf";
                                } //-----MMK PESAKA-----
                                else if (kodUrusan.startsWith("PMMK2")) {
                                    reportName = "CONS_SRT_KEB_PMMMK_P_MLK.rdf";
                                }//-----TANAH LADANG-----
                                else if (kodUrusan.startsWith("PMJTL")) {
                                    reportName = "CONSSrtKebPMT_Ladang_MLK.rdf";
                                }
                            } //*************SIJIL KELULUSAN*************
                            else if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SJK")) {

                                kd = kodDokumenDAO.findById("SJKC");
                                //-----TANAH LADANG-----
                                if (kodUrusan.startsWith("PMJTL")) {
                                    reportName = "CONSSijilMhnPmlkTnhLadang001_MLK.rdf";
                                }
                            }
                        }

                        LOGGER.info("--------KOD URUSAN : " + kodUrusan);
                        LOGGER.info("--------KOD DOKUMEN : " + kd.getKod());
                        LOGGER.info("--------RDF : " + reportName);

                        d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    }
                }

                senaraiKandunganGenarated = new ArrayList<KandunganFolder>();
                LOGGER.info("--------ID folder : " + permohonan.getFolderDokumen().getFolderId());
                List<KandunganFolder> senaraiFolderDokumen = consentService.findSenaraiFolderDokumenByIdFolder(String.valueOf(permohonan.getFolderDokumen().getFolderId()));
                senaraiKandunganGenarated = new ArrayList<KandunganFolder>();
                if (!senaraiFolderDokumen.isEmpty()) {
                    for (KandunganFolder kFolder : senaraiFolderDokumen) {
                        if (kFolder.getDokumen().getKodDokumen() != null) {
                            if (kFolder.getDokumen().getKodDokumen().getKod().equals("SKC")
                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("SJKC")
                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("PBIC")
                                    || kFolder.getDokumen().getKodDokumen().getKod().equals("SK2C")) {
                                senaraiKandunganGenarated.add(kFolder);
                            }
                        }
                    }
                    if (senaraiKandunganGenarated.size() > 0) {
                        addSimpleMessage("Dokumen telah berjaya dijana. Sila buat cetakan.");
                    }
                }
            } else {
                if (kodNegeri.equals("05")) {
                    addSimpleError("Sila pastikan bayaran cetakan semula telah dibuat. (Salinan Sah Dokumen-73199)");
                } else {
                    addSimpleError("Sila pastikan bayaran cetakan semula telah dibuat. (Salinan Sah Dokumen-72499)");
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/cetak_semula.jsp");

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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

//    public Pihak getPihakSearch() {
//        return pihakSearch;
//    }
//
//    public void setPihakSearch(Pihak pihakSearch) {
//        this.pihakSearch = pihakSearch;
//    }
    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<KandunganFolder> getSenaraiKandunganGenarated() {
        return senaraiKandunganGenarated;
    }

    public void setSenaraiKandunganGenarated(List<KandunganFolder> senaraiKandunganGenarated) {
        this.senaraiKandunganGenarated = senaraiKandunganGenarated;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

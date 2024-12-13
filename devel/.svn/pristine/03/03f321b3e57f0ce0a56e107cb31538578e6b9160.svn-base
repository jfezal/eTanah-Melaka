/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.view.kaunter.*;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LelonganDAO;

import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.AduanLokasi;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Permohonan;

import etanah.model.Lelongan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanNota;
import etanah.service.EnforceService;
import etanah.service.KaunterService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_senarai_dokumen")
public class UtilitiSenaraiDokumenActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private Enkuiri enkuiri;
    private String stage;
    private String status;
    private String participant;
    private String idHakmilik;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<Lelongan> senaraiLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<AduanLokasi> bpmList;
    private List<AduanLokasi> lokasiList;
    private List<Permohonan> jenisKesalahanList;
    private List<AduanLokasi> daerahList;
    private String daerah;
    private String bpm;
    private String jenisKesalahan;
    private String lokasi;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    LelonganDAO lelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private PembetulanService pService;
    @Inject
    EnforceService eService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private TabManager tm;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    private String stageLabel;
    private List<PermohonanNota> listNota;
    private static final Logger log = Logger.getLogger(UtilitiSenaraiDokumenActionBean.class);
    private Boolean semakanDokumen = Boolean.FALSE;
    private String fromPage;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private FolderDokumen folderDokumen;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KodDokumen> KodDokumenNotRequired = new ArrayList<KodDokumen>();
    private Dokumen d;
    private KandunganFolder kf;
    FileBean fileToBeUpload;
    private Pengguna pengguna;
    private Permohonan secondLayerPermohonan;
    private Permohonan thirdLayerPermohonan;
    private List<PermohonanNota> listNotaPermohonanSebelum;
    private List<PermohonanNota> listNotaPermohonanHD; //HD = sebahagian kompaun dakwa

    @DefaultHandler
    public Resolution insertIdPermohonan() {
        semakanDokumen = true;
        fromPage = "senaraiDokumen";
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakanPermohonan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Resolution checkPermohonan() throws WorkflowException {
        semakanDokumen = true;
        fromPage = "senaraiDokumen";
        log.info("kod negeri : " + conf.getProperty("kodNegeri"));
        if (bpm != null) {
            System.out.println("search bpm : " + bpm);
            bpmList = new ArrayList<AduanLokasi>();
            bpmList.addAll(eService.searchBpm(bpm.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(bpm) : " + bpmList.size());
            getContext().getRequest().setAttribute("bpm", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian.jsp");
        }
        if (daerah != null) {
            log.info("search daerah : " + daerah);
            daerahList = new ArrayList<AduanLokasi>();
            daerahList.addAll(eService.searchDaerah(daerah.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(daerah) : " + daerahList.size());
            getContext().getRequest().setAttribute("daerah", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian.jsp");
        }
        if (jenisKesalahan != null) {
            log.info("search jenisKesalahan : " + jenisKesalahan);
            jenisKesalahanList = new ArrayList<Permohonan>();
            jenisKesalahanList.addAll(eService.searchJenisKesalahan(jenisKesalahan.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(jenis kesalahan) : " + jenisKesalahanList.size());
            getContext().getRequest().setAttribute("jenisKesalahan", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian.jsp");
        }
        if (lokasi != null) {
            log.info("search lokasi : " + lokasi);
            lokasiList = new ArrayList<AduanLokasi>();
            lokasiList.addAll(eService.searchLokasi(lokasi.toLowerCase(), conf.getProperty("kodNegeri")));
            System.out.println("size search result(lokasi) : " + lokasiList.size());
            getContext().getRequest().setAttribute("lokasi", Boolean.TRUE);
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian.jsp");
        }
        if (idHakmilik != null) {
            hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
            hakmilikUrusanList.addAll(pService.findUrusanByidHY(idHakmilik));
            return new ForwardResolution("/WEB-INF/jsp/common/maklumat_urusan.jsp");
        }
        if ((permohonan == null) && (idHakmilik == null) && (daerah == null)) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan atau Bandar/Pekan/Mukim atau Daerah atau Lokasi atau Jenis Kesalahan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakanPermohonan.jsp");
        }
        idPermohonan = permohonan.getIdPermohonan();
        if (idPermohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakanPermohonan.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/semakanPermohonan.jsp");
        } else {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getAcquiredBy();
                stageLabel = tm.getCurrentAction(permohonan.getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage());
            }

            if (permohonan.getPermohonanSebelum() != null) {
                //1) find mohon nota based on id permohonan sebelum (IP create new id permohonan)
                secondLayerPermohonan = permohonan.getPermohonanSebelum();
                log.info(":: secondLayerPermohonan : " + secondLayerPermohonan.getIdPermohonan());

                listNotaPermohonanSebelum = eService.findListNotaByIdMohonSebelum(permohonan.getPermohonanSebelum().getIdPermohonan());
                log.info("size listNotaPermohonanSebelum : " + listNotaPermohonanSebelum.size());

                //2) find mohon kertas for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                Permohonan permohonanIP = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (permohonanIP != null) {
                    if (permohonanIP.getPermohonanSebelum() != null) {
                        thirdLayerPermohonan = permohonanIP.getPermohonanSebelum();
                        log.info(":: thirdLayerPermohonan : " + thirdLayerPermohonan.getIdPermohonan());

                        listNotaPermohonanHD = eService.findListNotaByIdMohonSebelum(permohonanIP.getPermohonanSebelum().getIdPermohonan());
                    }

                }
            }
        }
        listNota = eService.findListNotaByIdMohonSebelum(permohonan.getIdPermohonan());
        log.info(":::: size list nota : " + listNota.size());
        log.info(":::: fromPage : " + fromPage);
        status = permohonan.getStatus();
        if (status == null) {
            addSimpleMessage("Urusan ini sedang diproses");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("TS".equals(status)) {
            addSimpleMessage("Menunggu Permohonan/Perserahan sebelum untuk bermula");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("TA".equals(status)) {
            addSimpleMessage("Tugasan ini belum diambil oleh sesiapa");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("AK".equals(status)) {
            String msg = "Urusan ini belum selesai (Peringkat " + stageLabel;
            if (StringUtils.isNotBlank(participant)) {
                msg += " - " + participant;
            }
            msg += ")";
            addSimpleMessage(msg);
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("GB".equals(status)) { // Gantung
            // TODO: check if task own by SPOC
            addSimpleMessage("Urusan ini telah digantung. Sila hubungi Unit "
                    + permohonan.getKodUrusan().getJabatanNama());
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("TK".equals(status)) {//TK - Tidak Aktif - Urusan telah dibatalkan
            addSimpleMessage("Urusan telah dibatalkan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("TP".equals(status)) {
            addSimpleMessage("Menunggu tindakan selanjut daripada pemohon untuk meneruskan aliran kerja. Tugasan Berada Di kaunter");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("SL".equals(status)) {
            addSimpleMessage("Urusan ini telah selesai diproses. Keputusan telah dikeluarkan");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else if ("SS".equals(status)) {
            addSimpleMessage("Urusan ini telah disemak semula");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
        } else {

            return null;

        }



    }

    public Resolution viewhakmilikDetail() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = pService.findHakmilik(idHakmilik);
        return new JSP("daftar/utiliti/paparan_hakmilik_single.jsp").addParameter("popup", "true");
    }

    public Resolution viewDetailPermohonan() {
        String idMohon = (String) getContext().getRequest().getParameter("idMohon");
        permohonan = permohonanDAO.findById(idMohon);
        listNota = eService.findListNotaByIdMohonSebelum(permohonan.getIdPermohonan());
        log.info(":::: size list nota : " + listNota.size());
        return new JSP("penguatkuasaan/view_permohonan_detail.jsp").addParameter("popup", "true");
    }

    public Resolution addDocForm() {
        log.debug("addDocForm");

        String ids = getContext().getRequest().getParameter("folder.idFolder");
        if (ids != null && ids.length() > 0) {
            folderDokumen = folderDAO.findById(Long.valueOf(ids));
        } else {
            ids = getContext().getRequest().getParameter("permohonan.idPermohonan");
            if (ids != null && ids.length() > 0) {
                permohonan = permohonanDAO.findById(ids);
                if (permohonan != null) {
                    folderDokumen = permohonan.getFolderDokumen();
                    if (folderDokumen != null) {
                        if (folderDokumen.getSenaraiKandungan() != null) {
                            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                                if (kf == null || kf.getDokumen() == null) {
                                    continue;
                                }
                                Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                                kf.setDokumen(d);
                                senaraiKandungan.add(kf);
                            }
                        }
                    }
                }
            }
        }

        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
//            for (int i = 0; i < 3; i++) {
            KandunganFolder kf = new KandunganFolder();
            kandunganFolderTamb.add(kf);
//            }
        }
        return new JSP("penguatkuasaan/utiliti_kemasukan_tambahan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        log.debug("::::: simpanDokumenTambahan");
        semakanDokumen = true;
        fromPage = "senaraiDokumen";
        String ids = getContext().getRequest().getParameter("permohonan.idPermohonan");
        log.debug(":::::ids id permohonan : " + ids);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();

        if (ids != null && ids.length() > 0) {
            permohonan = permohonanDAO.findById(ids);

            if (permohonan != null) {
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen != null) {
                    akf = folderDokumen.getSenaraiKandungan();
                }
            }
        }

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            KandunganFolder f = kandunganFolderTamb.get(0);
            Dokumen d = f.getDokumen();
            String c = f.getCatatan();

            KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());

            if ((kd != null && !kd.getKod().equals("0"))
                    || (c != null && c.trim().length() > 0)) {
                d.setInfoAudit(ia);
                KodDokumen kDok = kodDokumenDAO.findById(kd.getKod());
                d.setTajuk(kd == null ? "KIV" : kDok.getNama());
                d.setNoVersi("1.0");
                d.setKodDokumen(kd);
                d.setKlasifikasi(klasifikasiAm);
                dokumenDAO.save(d);
                f.setFolder(folderDokumen);
                f.setInfoAudit(ia);
                f.setDokumen(d);
                akf.add(f);
            }
            if (akf.size() > 0) {
                folderDokumen.setSenaraiKandungan(akf);
            }

            folderDokumen.setInfoAudit(ia);
            folderDAO.save(folderDokumen);
//            kandunganFolderDAO.save(f);

            tx.commit();

//            saveToSession(ctx);

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());


        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
    }

    public Resolution reload() {
        semakanDokumen = true;
        fromPage = "senaraiDokumen";
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        log.debug(":::::reload id mohon : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    //1) find mohon nota based on id permohonan sebelum (IP create new id permohonan)
                    secondLayerPermohonan = permohonan.getPermohonanSebelum();
                    log.info(":: secondLayerPermohonan : " + secondLayerPermohonan.getIdPermohonan());

                    listNotaPermohonanSebelum = eService.findListNotaByIdMohonSebelum(permohonan.getPermohonanSebelum().getIdPermohonan());
                    log.info("size listNotaPermohonanSebelum : " + listNotaPermohonanSebelum.size());

                    //2) find mohon kertas for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                    Permohonan permohonanIP = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (permohonanIP != null) {
                        if (permohonanIP.getPermohonanSebelum() != null) {
                            thirdLayerPermohonan = permohonanIP.getPermohonanSebelum();
                            log.info(":: thirdLayerPermohonan : " + thirdLayerPermohonan.getIdPermohonan());

                            listNotaPermohonanHD = eService.findListNotaByIdMohonSebelum(permohonanIP.getPermohonanSebelum().getIdPermohonan());
                        }

                    }
                }
            } else {
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
    }

    public Resolution deleteSelected() {
        log.debug("::::: masuk deleteSelected");
        String id = getContext().getRequest().getParameter("id");
        log.debug("::::: id dokumen :" + id);
        semakanDokumen = true;
        fromPage = "senaraiDokumen";

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(id)) {
                Dokumen d = dokumenDAO.findById(Long.parseLong(id));
                if (d != null) {
                    dokumenDAO.delete(d);
                    addSimpleMessage("Dokumen berjaya di hapus.");
                }
            }

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }
        tx.commit();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/permohonan_status_penguatkuasaan.jsp");
    }

    public Resolution popupUpload() {
        log.info("::::: popupUpload");

        String folderId = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        String idDokumen = getContext().getRequest().getParameter("idDokumen");



        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
        }
        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }

        if (StringUtils.isNotBlank(dokumenKod)) {
            getContext().getRequest().setAttribute("dokumenKod", dokumenKod);
        } else {
            return new ErrorResolution(404, "Kod Dokumen tidak ditentukan.");
        }


        if (StringUtils.isNotBlank(idDokumen)) {
            getContext().getRequest().setAttribute("idDokumen", idDokumen);
        }

        log.info("value when open popup ::: folderId : " + folderId + " idPermohonan : " + idPermohonan + " dokumenKod : " + dokumenKod);
        return new JSP("penguatkuasaan/utiliti_popup_upload.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        log.info("processUploadDoc");
        String folderId = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        String idDokumen = getContext().getRequest().getParameter("idDokumen");


        log.info("folderId : " + folderId + " idPermohonan : " + idPermohonan + " dokumenKod : " + dokumenKod + " idDokumen : " + idDokumen);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        KodDokumen kodDokumen = kodDokumenDAO.findById(dokumenKod);

        if (idDokumen != null && StringUtils.isNotBlank(idDokumen)) {
            log.info("existing dokumen");
            d = dokumenDAO.findById(Long.parseLong(idDokumen));
            ia = d.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("new dokumen");
            d = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }

        d.setInfoAudit(ia);
        d.setTajuk(kodDokumen.getNama());
        d.setNoVersi("1.0");
        d.setKodDokumen(kodDokumen);
        d.setKlasifikasi(klasifikasiAm);

        kf = kandunganFolderService.findByDokumen(d, permohonan.getFolderDokumen());
        if (kf == null) {
            kf = new KandunganFolder();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        kf.setFolder(permohonan.getFolderDokumen());
        kf.setDokumen(d);
        kf.setInfoAudit(ia);

        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        kandunganFolderDAO.save(kf);



        if (d != null) {
            getContext().getRequest().setAttribute("dokumenId", d.getIdDokumen());
            tx.commit();
        } else {
            tx.rollback();
        }
        //return new ErrorResolution(404, "Dokumen tidak ditentukan.");

        d = dokumenDAO.saveOrUpdate(d);
        kandunganFolderDAO.save(kf);

        String dokumenId = Long.toString(d.getIdDokumen());

        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                log.debug("content type = " + contentType);

                permohonan = permohonanDAO.findById(idPermohonan);

                DMSUtil dmsUtil = new DMSUtil();

                if (permohonan != null) {

                    FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                    fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                    String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                    updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), contentType, "", "");

                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return new JSP("penguatkuasaan/popup_upload.jsp").addParameter("popup", "true");
            }
        }
        addSimpleMessage("Muat naik fail berjaya.");
        if (getContext().getRequest().getParameter("prm") != null) {
            log.info("get dokumen kod");
            getContext().getRequest().setAttribute("dokumenKod", getContext().getRequest().getParameter("dokumenKod"));
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }

        return new JSP("penguatkuasaan/utiliti_popup_upload.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan, String idRujukan) {
        log.info("updatePathDokumen");
        System.out.println("format : " + format);
        System.out.println("catatan : " + catatan);
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        }
        System.out.println("id rujukan : " + idRujukan);
        if (StringUtils.isNotBlank(idRujukan)) {
            if (d.getKodDokumen().getKod().equalsIgnoreCase("LP")) {
                d.setDalamanNilai1(idRujukan);
            } else {
                d.setPerihal(idRujukan);
            }
        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public List<AduanLokasi> getBpmList() {
        return bpmList;
    }

    public void setBpmList(List<AduanLokasi> bpmList) {
        this.bpmList = bpmList;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getJenisKesalahan() {
        return jenisKesalahan;
    }

    public void setJenisKesalahan(String jenisKesalahan) {
        this.jenisKesalahan = jenisKesalahan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<AduanLokasi> getDaerahList() {
        return daerahList;
    }

    public void setDaerahList(List<AduanLokasi> daerahList) {
        this.daerahList = daerahList;
    }

    public List<Permohonan> getJenisKesalahanList() {
        return jenisKesalahanList;
    }

    public void setJenisKesalahanList(List<Permohonan> jenisKesalahanList) {
        this.jenisKesalahanList = jenisKesalahanList;
    }

    public List<AduanLokasi> getLokasiList() {
        return lokasiList;
    }

    public void setLokasiList(List<AduanLokasi> lokasiList) {
        this.lokasiList = lokasiList;
    }

    public String getStageLabel() {
        return stageLabel;
    }

    public void setStageLabel(String stageLabel) {
        this.stageLabel = stageLabel;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public Boolean getSemakanDokumen() {
        return semakanDokumen;
    }

    public void setSemakanDokumen(Boolean semakanDokumen) {
        this.semakanDokumen = semakanDokumen;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public Dokumen getD() {
        return d;
    }

    public void setD(Dokumen d) {
        this.d = d;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getSecondLayerPermohonan() {
        return secondLayerPermohonan;
    }

    public void setSecondLayerPermohonan(Permohonan secondLayerPermohonan) {
        this.secondLayerPermohonan = secondLayerPermohonan;
    }

    public Permohonan getThirdLayerPermohonan() {
        return thirdLayerPermohonan;
    }

    public void setThirdLayerPermohonan(Permohonan thirdLayerPermohonan) {
        this.thirdLayerPermohonan = thirdLayerPermohonan;
    }

    public List<PermohonanNota> getListNotaPermohonanSebelum() {
        return listNotaPermohonanSebelum;
    }

    public void setListNotaPermohonanSebelum(List<PermohonanNota> listNotaPermohonanSebelum) {
        this.listNotaPermohonanSebelum = listNotaPermohonanSebelum;
    }

    public List<PermohonanNota> getListNotaPermohonanHD() {
        return listNotaPermohonanHD;
    }

    public void setListNotaPermohonanHD(List<PermohonanNota> listNotaPermohonanHD) {
        this.listNotaPermohonanHD = listNotaPermohonanHD;
    }
}

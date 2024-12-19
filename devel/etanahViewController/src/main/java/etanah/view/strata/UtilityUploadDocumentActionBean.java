/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import com.google.inject.Inject;

import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Permohonan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.DokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.service.KaunterService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author murali
 */
@HttpCache(allow = false)
@UrlBinding("/utility/strata/dokumen")
public class UtilityUploadDocumentActionBean extends AbleActionBean {

    @Inject
    private FolderDokumenDAO folderDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenService dokumenService;
    @Inject
    EnkuiriService ES;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    etanah.Configuration conf;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private Pengguna pengguna;
    private Permohonan p;
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private List<FolderDokumen> listFolderDokumen = new ArrayList<FolderDokumen>();
    private String tajuk;
    private String idPermohonan;
    private String idFolder;
    private Permohonan permohonan;
    private static final Logger LOG = Logger.getLogger(UtilityUploadDocumentActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhDokumen;
    private String dokumenID;
    private String folderID;
    FileBean fileToBeUpload;

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public FolderDokumen getFolderDokumenSebelum() {
        return folderDokumenSebelum;
    }

    public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
        this.folderDokumenSebelum = folderDokumenSebelum;
    }

    public void setPermohonan(Permohonan p) {
        this.p = p;
    }

    public Permohonan getPermohonan() {
        return this.p;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public Map<String, String> getKodMapSebelum() {
        return kodMapSebelum;
    }

    public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
        this.kodMapSebelum = kodMapSebelum;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<Dokumen> getSenaraiKandunganSebelum() {
        return senaraiKandunganSebelum;
    }

    public void setSenaraiKandunganSebelum(List<Dokumen> senaraiKandunganSebelum) {
        this.senaraiKandunganSebelum = senaraiKandunganSebelum;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public List<FolderDokumen> getListFolderDokumen() {
        return listFolderDokumen;
    }

    public void setListFolderDokumen(List<FolderDokumen> listFolderDokumen) {
        this.listFolderDokumen = listFolderDokumen;
    }

    public String getTarikhDokumen() {
        return tarikhDokumen;
    }

    public void setTarikhDokumen(String tarikhDokumen) {
        this.tarikhDokumen = tarikhDokumen;
    }

    public String getDokumenID() {
        return dokumenID;
    }

    public void setDokumenID(String dokumenID) {
        this.dokumenID = dokumenID;
    }

    public String getFolderID() {
        return folderID;
    }

    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp");
    }

    public Resolution saveFolderInfo() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        try {
            folderDAO.saveOrUpdate(folderDokumen);
            tx.commit();
            addSimpleMessage("Kemasukan Data Berjaya.");
        } catch (Exception ex) {
            addSimpleError("Kemasukan Data Gagal." + ex.getMessage());
            tx.rollback();
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp").addParameter("tab", "true");
    }

    public Resolution find() {
        LOG.info("--idPermohonan-- : " + idPermohonan);

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String id = new String();
            if (permohonan == null) {
                idPermohonan = null;
                addSimpleError("Sila Masukkan ID Permohonan.");
                return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp");
            } else {
                LOG.info("--IdPermohonan-- :" + permohonan.getIdPermohonan());
                LOG.info("--tajuk-- :" + permohonan.getFolderDokumen().getTajuk());
                id = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            }
            String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
            String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
            if (id == null) {
                LOG.info("-----if------");
                folderDokumen = folderDAO.findById(Long.valueOf(id));
                LOG.info("--id Folder --:" + folderDokumen.getFolderId());
            } else {
                LOG.info("-----else------");

                if (permohonan != null && permohonan.getIdPermohonan() != null) {
                    id = permohonan.getIdPermohonan();
                    LOG.info("-----id------" + id);
                }
                if (id != null && id.length() > 0) {
                    p = permohonanDAO.findById(id);
                    if (p == null) {
                        addSimpleError("ID Permohonan " + id + " tidak wujud");
                    }
                    folderDokumen = p.getFolderDokumen();
                    if (p.getPermohonanSebelum() != null) {
                        folderDokumenSebelum = p.getPermohonanSebelum().getFolderDokumen();
                        for (KandunganFolder kf : folderDokumenSebelum.getSenaraiKandungan()) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
                            if (kd == null) {
                                continue;
                            }
                            kodMapSebelum.put(kd.getKod(), kd.getNama());
                            if (StringUtils.isNotBlank(filter2)) {
                                if (kd.getKod().equals(filter2)) {
                                    senaraiKandunganSebelum.add(kf.getDokumen());
                                }
                            } else {
                                senaraiKandunganSebelum.add(kf.getDokumen());
                            }
                        }
                    }


                    if (p != null) {
                        senaraiKodUrusan.add(p.getKodUrusan().getKod());
                        LOG.info("--kodUrusan-- " + p.getKodUrusan().getKod());

                    } //for filtering purposes
                    for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                        if (kf == null || kf.getDokumen() == null) {
                            continue;
                        }
                        KodDokumen kd = kf.getDokumen().getKodDokumen();
                        if (kd != null) {
                            kodMap.put(kd.getKod(), kd.getNama());
                        }


                        if (StringUtils.isNotBlank(filter)) {
                            if (kd.getKod().equals(filter)) {
                                senaraiKandungan.add(kf);
                            }
                        } else {
                            senaraiKandungan.add(kf);
                        }
                    }
                } else {
                    addSimpleError("Folder tidak ditentukan.");
                }
            }

        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp");        
    }

    public Resolution reload() {
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("--idPermohonan--: " + idMohon);
        p = permohonanDAO.findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));

        for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
            if (kf == null || kf.getDokumen() == null) {
                continue;
            }
            KodDokumen kd = kf.getDokumen().getKodDokumen();
            if (kd != null) {
                kodMap.put(kd.getKod(), kd.getNama());
            }
            senaraiKandungan.add(kf);
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp");
    }

    public Resolution refreshpage() {
        LOG.info("--refreshPage--");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        find();
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp").addParameter("popup", "true");
    }

    public Resolution viewPDF() {
        return new ForwardResolution("/WEB-INF/jsp/dokumen/uploadFileApplet.jsp");
    }

    public Resolution dokumenTambahanForm() {

        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("--idPermohonan-- : " + idMohon);
        p = permohonanDAO.findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        LOG.info("idDokumen : " + idDokumen);
        senaraiKodUrusan.add(p.getKodUrusan().getKod());

        LOG.info("----------dokumenTambahanForm--------");
        LOG.info("--senaraiKodUrusan-- : " + senaraiKodUrusan.size());

        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/dokumen_tambahan.jsp");
    }

    public Resolution deleteSelected() {
        String[] ids = getContext().getRequest().getParameterValues("chkbox");
        String idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            p = permohonanDAO.findById(idPermohonan);
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Dokumen d = dokumenDAO.findById(Long.parseLong(id));
                if (d != null) {

                    List<HakmilikPermohonan> senarai = p.getSenaraiHakmilik();
                    for (HakmilikPermohonan hp : senarai) {
                        if (hp.getDokumen1() != null && hp.getDokumen1().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen1(null);
                        }
                        if (hp.getDokumen2() != null && hp.getDokumen2().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen2(null);
                        }
                        if (hp.getDokumen3() != null && hp.getDokumen3().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen3(null);
                        }
                        if (hp.getDokumen4() != null && hp.getDokumen4().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen4(null);
                        }
                        if (hp.getDokumen5() != null && hp.getDokumen5().getIdDokumen() == d.getIdDokumen()) {
                            hp.setDokumen5(null);
                        }
                        hakmilikPermohonanService.saveOrUpdateWithoutConnection(hp);
                    }

                    dokumenDAO.delete(d);
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
        return new RedirectResolution(UtilityUploadDocumentActionBean.class, "reload").addParameter("permohonan.idPermohonan", p.getIdPermohonan());
    }

    public Resolution saveEditInfo() {
        String result = "0";
        String tajuk = getContext().getRequest().getParameter("tajuk");
        String idDok = getContext().getRequest().getParameter("idDok");

        if (StringUtils.isNotBlank(idDok)) {
            Dokumen d = dokumenDAO.findById(Long.valueOf(idDok));
            if (d != null) {
                d.setTajuk(tajuk);
                dokumenService.saveOrUpdate(d);
                result = "1";
            }
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution simpanDokumenTambahan() {
        LOG.info("--simpanDokumenTambahan::start--");
        String idMohon = getContext().getRequest().getParameter("permohonan.idPermohonan");
        LOG.info("--idPermohonan:--" + idMohon);
        p = permohonanDAO.findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(p.getFolderDokumen().getFolderId()));
        LOG.info("--kandunganFolderTamb:--" + kandunganFolderTamb.size());

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);

        String result = null;

        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        List<KandunganFolder> akf = new ArrayList<KandunganFolder>();
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            for (KandunganFolder f : kandunganFolderTamb) {
                if (f == null) {
                    continue;
                }

                Dokumen d = f.getDokumen();
                if (d == null || d.getKodDokumen() == null) {
                    continue;

                }
                KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());
                if (StringUtils.isBlank(kd.getKod())) {
                    continue;
                }
                String c = f.getCatatan();
                if ((kd != null && !kd.getKod().equals("0"))
                        || (c != null && c.trim().length() > 0)) {
                    d.setInfoAudit(ia);
                    d.setTajuk(kd == null ? "KIV" : kd.getNama());
                    d.setNoVersi("1.0");
                    d.setKlasifikasi(klasifikasiAm);
                    try {
                        d.setTarikhDokumen(sdf.parse(tarikhDokumen));

                    } catch (Exception ex) {
                        LOG.error(ex);
                    }

                    dokumenDAO.save(d);
                    f.setCatatan(c);
                    f.setFolder(folderDokumen);
                    f.setInfoAudit(ia);
                    akf.add(f);
                }
            }
            if (akf.size() > 0) {
                folderDokumen.setSenaraiKandungan(akf);
            }
            folderDAO.save(folderDokumen);

            tx.commit();
            result = "success";
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(
                    t.getMessage());
            result = "fail";
        }
        LOG.info("--simpanDokumenTambahan::finish--");
        return new StreamingResolution("text/plain", result);
    }

    public Resolution reset() {
        idPermohonan = null;
        senaraiKandungan = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utility/utilityUploadDocument.jsp");
    }

    public Resolution uploadForm() {
        return new JSP("strata/utility/upload.jsp").addParameter("popup", "true");
    }

    public Resolution processUpload() {
        System.out.println("------------processUpload------------::");
        dokumenID = getContext().getRequest().getParameter("dokumenId");
        folderID = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String dokumenPath = conf.getProperty("document.path");
        DMSUtil dmsUtil = new DMSUtil();
        String catatan = getContext().getRequest().getParameter("catatan");
        System.out.println("fileToBeUpload " + fileToBeUpload);
        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();
                String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileToBeUpload.getFileName();
                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderID;
                FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                updatePathDokumen(fizikalPath, Long.parseLong(dokumenID), fileToBeUpload.getContentType(), catatan);
                addSimpleMessage("Muat naik fail berjaya.");
                reload();               
            } catch (Exception ex) {
                Logger.getLogger(SenaraiKertasSiasatanFoliAActionBean.class).error(ex);
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } else {
            addSimpleError("Muat naik fail tidak berjaya.");
        }
        return new JSP("strata/utility/upload.jsp").addParameter("popup", "true");        
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan) {
        System.out.println("------------updatePathDokumen------------::");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null && StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
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
}

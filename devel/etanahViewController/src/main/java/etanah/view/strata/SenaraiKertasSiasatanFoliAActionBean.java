/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.PermohonanSemakDokumen;
import etanah.service.StrataPtService;
import etanah.model.KodUrusan;
import etanah.model.UrusanDokumen;
import etanah.util.FileUtil;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import org.apache.log4j.Logger;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanSemakDokumenDAO;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import java.util.ArrayList;
import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.Map;
import java.util.HashMap;
import javax.mail.Folder;
import java.util.Date;
import java.util.Iterator;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.util.DMSUtil;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/SemakKertasSiasatanFoliA")
public class SenaraiKertasSiasatanFoliAActionBean extends BasicTabActionBean {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService commonService;
    @Inject
    PermohonanSemakDokumenDAO permohonanSemakDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;
    private List<PermohonanSemakDokumen> senaraiSemakDokumen;
    private List<KodUrusan> kodUrusan;
    private List<UrusanDokumen> senaraiUrusanDokumen;
    private List<String> checkStatus;
    @Inject
    etanah.Configuration conf;
    FileBean fileToBeUpload;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private Pengguna pengguna;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    @Inject
    private FolderDokumenDAO folderDAO;
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private Folder folder;
    private KandunganFolder kandunganFolder;
    private static final Logger LOG = Logger.getLogger(SenaraiKertasSiasatanFoliAActionBean.class);
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> KodDokumenNotRequired = new ArrayList<KodDokumen>();
    private List<KodDokumen> KodDokumenRequiredFiloA = new ArrayList<KodDokumen>();
    private String cat;
    private List<KandunganFolder> senaraiKandungan1 = new ArrayList<KandunganFolder>();
    private String dokumenID;
    private String folderID;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private List<KandunganFolder> kfd = new ArrayList<KandunganFolder>();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/kuatkuasa/senarai_semak_kertas_siasataan_A.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("-----rehydrate----:");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan() != null) {
            //KodUrusan ku = kodUrusanDAO.findById("P8");
            KodUrusan ku = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
            senaraiUrusanDokumen = strService.findUrusanDokumenBykodUrusan(ku.getKod());
            // to save kod dokumen from urusan dokumen to dokumen table
            for (int i = 0; i < senaraiUrusanDokumen.size(); i++) {
                Dokumen d = null;
                KodDokumen kd = kodDokumenDAO.findById(senaraiUrusanDokumen.get(i).getKodDokumen().getKod());
                FolderDokumen fd = folderDAO.findById(permohonan.getFolderDokumen().getFolderId());
                //need to check whether its already exist or not
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            }
        }

        //For Dokumen_Tambah_Page:Start
        senaraiKandungan1 = new ArrayList<KandunganFolder>();

        String ids = getContext().getRequest().getParameter("folder.idFolder");
        System.out.println("----------ids-------------::" + ids);
        if (ids != null && ids.length() > 0) {
            folderDokumen = folderDAO.findById(Long.valueOf(ids));
        } else {
            ids = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            System.out.println("----------ids:Permohonan-------------::" + ids);
            if (ids != null && ids.length() > 0) {
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen != null) {
                    kfd = strService.findkandunganFolderByfolderIdDesc(folderDokumen.getFolderId());
                    if (folderDokumen.getSenaraiKandungan() != null) {
                        //for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                        for (KandunganFolder kf : kfd) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                            //System.out.println("----------Dokumens d-------------::" + d.getKodDokumen().getKod());
                            String[] koddoc = {"PSDPS", "LP"};
                            if (ArrayUtils.contains(koddoc, d.getKodDokumen().getKod())) {
                                kf.setDokumen(d);
                                senaraiKandungan1.add(kf);
                            }
                        }
                    }
                }
            }
        }

    }

    public Resolution addDocForm() {
        System.out.println("------------adding Documen-------------");
        senaraiKodUrusan.add("P8");
        senaraiKodUrusan.add("P22A");
        senaraiKodUrusan.add("P14A");
        senaraiKodUrusan.add("P22B");
        senaraiKodUrusan.add("P40A");
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
        return new JSP("strata/kuatkuasa/senarai_semak_kertas_tambahan_doc_FA.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        System.out.println("------------simpan Dokumen Tambahan----------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        Date now = new Date();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
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
        rehydrate();
        //return new JSP("strata/kuatkuasa/senarai_semak_kertas_siasataan_A.jsp").addParameter("tab", "true");
        return new RedirectResolution(SenaraiKertasSiasatanFoliAActionBean.class, "showForm");
    }

    public Resolution deleteSelected() {
        String[] ids = getContext().getRequest().getParameterValues("chkbox");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Long id1 = Long.parseLong(id);
                Dokumen dok1 = dokumenDAO.findById(id1);
                dokumenDAO.delete(dok1);

                List<KandunganFolder> akf = folderDokumen.getSenaraiKandungan();
                for (Iterator<KandunganFolder> it = akf.iterator(); it.hasNext();) {
                    KandunganFolder fd1 = it.next();
                    if (id1 == fd1.getDokumen().getIdDokumen()) {
                        it.remove();
                        break;
                    }
                }
                folderDokumen.setSenaraiKandungan(akf);
            }
            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        System.out.println("-----------Selected Dokumen deleted---------::");
        rehydrate();
        return new RedirectResolution(SenaraiKertasSiasatanFoliAActionBean.class, "showForm");
    }

    public Resolution reload() {
        return new ForwardResolution("/WEB-INF/jsp/strata/kuatkuasa/senarai_semak_kertas_siasataan_A.jsp");
    }

    public Resolution muatNaikForm1() {

        return new JSP("strata/kuatkuasa/muat_naik_siasatan_A.jsp").addParameter("popup", "true");
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
            } catch (Exception ex) {
                Logger.getLogger(SenaraiKertasSiasatanFoliAActionBean.class).error(ex);
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } else {
            addSimpleError("Muat naik fail tidak berjaya.");
        }
        return new JSP("strata/kuatkuasa/muat_naik_siasatan_A.jsp").addParameter("popup", "true");
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

    //added
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
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
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

    public Resolution showRefresh() {
        LOG.info("------Refreshed----");
        return new JSP("strata/kuatkuasa/senarai_semak_kertas_siasataan_A.jsp").addParameter("popup", "true");
    }
    public Resolution showCatatanEdit() {
        return new JSP("strata/kuatkuasa/CatatanEdit_A.jsp").addParameter("popup", "true");
    }
    public Resolution upDateCatatan() {
        dokumenID = getContext().getRequest().getParameter("dokumenId");
        folderID = getContext().getRequest().getParameter("folderId");
        idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
        String catatan = getContext().getRequest().getParameter("catatan");
        Dokumen doc = new Dokumen();
        doc = dokumenDAO.findById(Long.valueOf(dokumenID));
        permohonan = permohonanDAO.findById(idPermohonan);        
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, permohonan.getFolderDokumen());
        if (kf != null) {
            InfoAudit ia = new InfoAudit();
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            kf.setInfoAudit(ia);
            if (catatan != null && StringUtils.isNotBlank(catatan)) {
                kf.setCatatan(catatan);
            }
            dokumenService.saveKandunganWithDokumen(kf);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/kuatkuasa/CatatanEdit_A.jsp").addParameter("popup", "true");
    }
    

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public List<KandunganFolder> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<KandunganFolder> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanSemakDokumen> getSenaraiSemakDokumen() {
        return senaraiSemakDokumen;
    }

    public void setSenaraiSemakDokumen(List<PermohonanSemakDokumen> senaraiSemakDokumen) {
        this.senaraiSemakDokumen = senaraiSemakDokumen;
    }

    public List<KodUrusan> getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(List<KodUrusan> kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<UrusanDokumen> getSenaraiUrusanDokumen() {
        return senaraiUrusanDokumen;
    }

    public void setSenaraiUrusanDokumen(List<UrusanDokumen> senaraiUrusanDokumen) {
        this.senaraiUrusanDokumen = senaraiUrusanDokumen;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
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

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumenSebelum() {
        return folderDokumenSebelum;
    }

    public void setFolderDokumenSebelum(FolderDokumen folderDokumenSebelum) {
        this.folderDokumenSebelum = folderDokumenSebelum;
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

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public KandunganFolder getKandunganFolder() {
        return kandunganFolder;
    }

    public void setKandunganFolder(KandunganFolder kandunganFolder) {
        this.kandunganFolder = kandunganFolder;
    }
    //adding dokumen

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public List<KodDokumen> getKodDokumenNotRequired() {
        return strService.getKodDokumenNotRequired(senaraiKodUrusan);
    }

    public List<KodDokumen> getKodDokumenRequiredFiloA() {
        return strService.getKodDokumenRequiredFiloA(senaraiKodUrusan);
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getFolderID() {
        return folderID;
    }

    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    public String getDokumenID() {
        return dokumenID;
    }

    public void setDokumenID(String dokumenID) {
        this.dokumenID = dokumenID;
    }
}

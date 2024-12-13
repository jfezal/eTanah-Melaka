/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import etanah.service.EnforceService;
import etanah.view.strata.*;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
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
import etanah.view.etanahActionBeanContext;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.PermohonanSemakDokumen;
import etanah.service.StrataPtService;
import etanah.model.KodUrusan;
import etanah.model.UrusanDokumen;
import net.sourceforge.stripes.action.FileBean;
import org.apache.log4j.Logger;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
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
import java.util.Iterator;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/semak_kertas_siasatan")
public class MaklumatSenaraiSemakKSActionBean extends AbleActionBean {

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
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;
    private List<PermohonanSemakDokumen> senaraiSemakDokumen;
    private List<KodUrusan> kodUrusan;
    private List<UrusanDokumen> senaraiUrusanDokumen;
    @Inject
    etanah.Configuration conf;
    FileBean fileToBeUpload;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
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
    private static final Logger LOG = Logger.getLogger(MaklumatSenaraiSemakKSActionBean.class);
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private String cat;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private PermohonanSemakDokumen semakDokumen;
    private String idPermohonan;
    private List<PermohonanSemakDokumen> notExistDokumen;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/senarai_semak_KS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/senarai_semak_KS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getKodUrusan() != null) {

            try {
                senaraiSemakDokumen = enforceService.findSenaraiPermohonanSemakDokumen(idPermohonan);
                System.out.println("senaraiSemakDokumen : " + senaraiSemakDokumen.size());
            } catch (Exception ex) {
                LOG.error(ex);
            }

            KodUrusan ku = kodUrusanDAO.findById(permohonan.getKodUrusan().getKod());
            senaraiUrusanDokumen = strService.findUrusanDokumenBykodUrusan(ku.getKod());



            // to save kod dokumen from urusan dokumen to dokumen table
            for (int i = 0; i < senaraiUrusanDokumen.size(); i++) {
                Dokumen d = null;
                KodDokumen kd = kodDokumenDAO.findById(senaraiUrusanDokumen.get(i).getKodDokumen().getKod());
                System.out.println("-----KodDokumen----:" + kd);
                FolderDokumen fd = folderDAO.findById(permohonan.getFolderDokumen().getFolderId());
                System.out.println("-----FolderDokumen----:" + fd);
                //need to check whether its already exist or not
                System.out.println("-----Saving kod_dok list in FolderDokumen from urusan dok----:" + fd);
                d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
            }
        }

        //For Dokumen_Tambah_Page:Start
        senaraiKandungan = new ArrayList<KandunganFolder>();
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

        LOG.info("----size senarai kandungan : ---- " + senaraiKandungan.size());
        LOG.info("----size senaraiSemakDokumen : ---- " + senaraiSemakDokumen.size());

    }

    public Resolution deleteSelected() {
        System.out.println("-----------deleting Selected Dokumen---------");
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
        System.out.println("-----------Rehydrate called---------::");
        return new RedirectResolution(MaklumatSenaraiSemakKSActionBean.class, "showForm");
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
        System.out.println("-----saveOrUpdateDokumen::Saving kod_dok list in FolderDokumen from urusan dok----:");
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');

            doc.setInfoAudit(ia);
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
        }

        return doc;
    }

    public Dokumen semakDokumen(KodDokumen kd, FolderDokumen fd, String id) {
        Dokumen d = null;
        List<KandunganFolder> l = kandunganFolderService.findByIdFolder(fd);
        for (KandunganFolder kf : l) {
            d = dokumenService.findByIDKodDokumen(kf.getDokumen().getIdDokumen(), kd, id);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    public Resolution simpanSemakDokumen() throws ParseException {
        LOG.info("simpanSemakDokumen");
        String catatan;
        InfoAudit info = new InfoAudit();

        for (int i = 0; i < senaraiKandungan.size(); i++) {
            String idDokumen = getContext().getRequest().getParameter("checkbox" + i);
            System.out.println("id Dokumen yang dipilih : " + idDokumen);
            if (StringUtils.isNotBlank(idDokumen)) {
                semakDokumen = enforceService.findPermohonanSemakDokumen(idPermohonan, Long.parseLong(idDokumen));
                if (semakDokumen == null) {
                    System.out.println("new semak dokumen");
                    semakDokumen = new PermohonanSemakDokumen();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                } else {
                    System.out.println("existing semak dokumen");
                    info = semakDokumen.getInfoAudit();
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());
                }
                Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));
                catatan = getContext().getRequest().getParameter("catatan" + i);
                if (dokumen.getNamaFizikal() != null) {
                    semakDokumen.setAdaDokumen("Y");
                } else {
                    semakDokumen.setAdaDokumen("T");
                }
                semakDokumen.setInfoAudit(info);
                semakDokumen.setCatatan(catatan);
                semakDokumen.setKodDokumen(dokumen.getKodDokumen());
                semakDokumen.setCawangan(pengguna.getKodCawangan());
                semakDokumen.setPermohonan(permohonan);
                semakDokumen.setDokumen(dokumen);
                semakDokumen.setStatus("Y");
                enforceService.simpanPermohonanSemakDokumen(semakDokumen);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("penguatkuasaan/senarai_semak_KS.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MaklumatSenaraiSemakKSActionBean.class, "locate");
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
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

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public PermohonanSemakDokumen getSemakDokumen() {
        return semakDokumen;
    }

    public void setSemakDokumen(PermohonanSemakDokumen semakDokumen) {
        this.semakDokumen = semakDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}

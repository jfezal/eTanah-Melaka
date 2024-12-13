/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

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
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
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
 * @author mazurahayati.yusop
 */
@HttpCache(allow = false)
@UrlBinding("/lelong/dokumen")
public class UtilitiUploadDokumenActionBean extends AbleActionBean {

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
    private static final Logger LOG = Logger.getLogger(FolderAction.class);
    private static boolean isDebug = LOG.isDebugEnabled();

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

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiUploadDokumen.jsp");
    }

    public Resolution saveFolderInfo() {
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
//        Pengguna pguna = (Pengguna)getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiUploadDokumen.jsp").addParameter("tab", "true");

//    	return getContext().getSourcePageResolution();
    }

    public Resolution find() {
//        if (StringUtils.isNotBlank(tajuk)) {
//            folderDokumen = folderDAO.findById(Long.valueOf(idFolder));
//            if (tajuk != null) {
//                LOG.debug("IdPermohonan() :" + folderDokumen.getTajuk());
//                listFolderDokumen = ES.getSenaraiFolder(tajuk);
//
//                String[] tname = {"tajuk"};
//                Object[] tvalue = {folderDokumen};
//                listFolderDokumen = folderDAO.findByEqualCriterias(tname, tvalue, tajuk);


                pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                String id = getContext().getRequest().getParameter("folder.idFolder");
                String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
                String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
                if (id != null && id.length() > 0) {
                    folderDokumen = folderDAO.findById(Long.valueOf(id));
                } else {
                    id = getContext().getRequest().getParameter("idPermohonan");
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
                        }
                        //for filtering purposes
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
                        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiUploadDokumen.jsp");
//                    }}
                }
            }
            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiUploadDokumen.jsp");
        }

    public Resolution reload() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiUploadDokumen.jsp").addParameter("tab", "true");
    }

    public Resolution viewPDF() {
        return new ForwardResolution("/WEB-INF/jsp/dokumen/uploadFileApplet.jsp");
    }
    /*
     * fikri: for upload document tambahan
     *
     */

    @HandlesEvent("addDocForm")
    public Resolution dokumenTambahanForm() {
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
        return new JSP("dokumen/dokumen_tambahan.jsp").addParameter("popup", "true");
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
        return new RedirectResolution(FolderAction.class, "reload").addParameter("permohonan.idPermohonan", p.getIdPermohonan());
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

//         @HandlesEvent("saveDocForm")
    public Resolution simpanDokumenTambahan() {
        LOG.info("simpanDokumenTambahan::start");
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

//                KodDokumen kd = d.getKodDokumen();
                KodDokumen kd = kodDokumenDAO.findById(d.getKodDokumen().getKod());
                if (StringUtils.isBlank(kd.getKod())) {
                    continue;
                }
                String c = f.getCatatan();
                if ((kd != null && !kd.getKod().equals("0"))
                        || (c != null && c.trim().length() > 0)) {
//                    if (kd != null && !kd.getKod().equals("0")) { // the type not set
//                        d.setKodDokumen(null);
//                    }
//                    d.setCatatanMinit(c);
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
        LOG.info("simpanDokumenTambahan::finish");
//             return new JSP("dokumen/dokumen_tambahan.jsp");
        return new RedirectResolution(FolderAction.class, "reload").addParameter("permohonan.idPermohonan", p.getIdPermohonan());
    }
}

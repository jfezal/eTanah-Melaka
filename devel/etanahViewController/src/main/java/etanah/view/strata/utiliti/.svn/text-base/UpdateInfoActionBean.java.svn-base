/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.KaunterService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.dokumen.FolderAction;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@HttpCache(allow = false)
@UrlBinding("/strata/utiliti/kemaskini_info")
public class UpdateInfoActionBean extends AbleActionBean {

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
    private PembetulanService pService;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    @Inject
    private DokumenService dokumenService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    StrataPtService strataService;
    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private UrusanDokumen urusanDokumen;
    private Pengguna pengguna;
    private String idPermohonan;
    private String idHakmilik;
    private boolean flag = false;
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private List<String> senaraiKandungan = new ArrayList<String>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private static final Logger LOG = Logger.getLogger(UpdateInfoActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    //@Before(stages = {LifecycleStage.BindingAndValidation}, 
    //on={"view","reload","viewPDF", "addDocForm" ,"simpanDokumenTambahan", "saveFolderInfo"})
    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
    }

    public Resolution search() throws WorkflowException {
        LOG.debug("--- Search START ---");
        if (permohonan == null) {
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
        }

        idPermohonan = permohonan.getIdPermohonan();
        LOG.debug("======== idPermohonan :" + idPermohonan);

        if (idPermohonan == null) {
            LOG.debug("======== idpermohonan null");
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.debug("======== Permohonan :" + permohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
        } else {

            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            LOG.debug("======== pengguna :" + pengguna);
            senaraiKandungan = new ArrayList<String>();
            LOG.debug("======== senaraiKandungan :" + senaraiKandungan);
            senaraiKandunganSebelum = new ArrayList<Dokumen>();
            LOG.debug("======== senaraiKandunganSebelum :" + senaraiKandunganSebelum);

            if (pengguna != null) {
                getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
            }

            /*           String id = getContext().getRequest().getParameter("folder.idFolder");
             LOG.debug("======== idFolder :" + id);
             String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
             LOG.debug("======== filter :" + filter);
             String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
             LOG.debug("======== filter2 :" + filter2);
             if (id != null && id.length() > 0) {
             folderDokumen = folderDAO.findById(Long.valueOf(id));
             } else { */
            //idPermohonan = permohonan.getIdPermohonan();
            LOG.debug("======== else ======");
            String id = permohonan.getIdPermohonan();
            //id = getContext().getRequest().getParameter("permohonan.idPermohonan");
            LOG.debug("======== idFolder :" + id);
            if (id != null && id.length() > 0) {
                permohonan = permohonanDAO.findById(id);

                List<FasaPermohonan> fasamohon = strataService.checkIDAliran(permohonan.getIdPermohonan());

                if (fasamohon != null) {
                    for (FasaPermohonan fp : fasamohon) {

                        if (!fp.getIdAliran().equalsIgnoreCase("kemasukan")) {
                            permohonan = null;
                        }
                    }
                    if (permohonan == null) {
                        addSimpleError("Maaf. ID Permohonan " + id + " telah didaftarkan. Data Permohonan tidak boleh dikemaskini.");
                    } else {
                        setFlag(true);
                    }
                } else {
                    permohonan = null;
                    addSimpleError(" ID Permohonan " + id + " tidak dijumpai.");
                    return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
                }

                /*              if (permohonan == null) {
                 addSimpleError("ID Permohonan " + id + " tidak wujud");
                 return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
                 } */

                if (permohonan != null) {
                    senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
                }
                //for filtering purposes

                senaraiKandungan.add("Maklumat Penyerah");
                senaraiKandungan.add("Maklumat Pemohon");
                senaraiKandungan.add("Nama Projek");
                senaraiKandungan.add("Nama Perbadanan Pengurusan");

            } else {
                addSimpleError("Folder tidak ditentukan.");
            }
            //   }
        }
        LOG.debug("--- Search END ---");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        //senaraiKandungan = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
    }

    public Resolution saveFolderInfo() {
        LOG.debug("--- save Folder Info---");
        LOG.debug("======== idPermohonan :" + idPermohonan);
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
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
//    	return getContext().getSourcePageResolution();
    }

    public Resolution reload() {
        LOG.debug("--- reload ---");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_info.jsp");
    }

    public Resolution viewPDF() {
        LOG.debug("--- view PDF---");
        return new ForwardResolution("/WEB-INF/jsp/dokumen/uploadFileApplet.jsp");
    }

    @HandlesEvent("addDocForm")
    public Resolution addDocForm() {
        LOG.debug("--- dokumen Tambahan Form---");
        LOG.debug("======== idPermohonan :" + idPermohonan);
        // reset the additional documents submitted to 10
        if (kandunganFolderTamb.size() == 0) {
            for (int i = 0; i < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
//        return new JSP("daftar/utiliti/dokumen_tambahan.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/dokumen/dokumen_tambahan.jsp").addParameter("popup", "true");
        return new JSP("/strata/utiliti/dokumen_tambahan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSelected() {
        LOG.debug("--- Delete Selected---");
        LOG.debug("======== idPermohonan :" + idPermohonan);
        String[] ids = getContext().getRequest().getParameterValues("chkbox");
        String idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            for (String id : ids) {
                Dokumen d = dokumenDAO.findById(Long.parseLong(id));
                if (d != null) {
                    List<HakmilikPermohonan> senarai = permohonan.getSenaraiHakmilik();
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
//        return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/tambah_dokumen_tertinggal.jsp");
        return new RedirectResolution(UpdateInfoActionBean.class, "search").addParameter("permohonan.idPermohonan", permohonan.getIdPermohonan());
    }

    public Resolution saveEditInfo() {
        LOG.debug("--- Save Edit Info ---");
        LOG.debug("======== idPermohonan :" + idPermohonan);
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
        LOG.debug("--- Simpan Dokumen Tambahan---");
        LOG.debug("======== idPermohonan :" + idPermohonan);
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
        return new RedirectResolution(UpdateInfoActionBean.class, "reload").addParameter("permohonan.idPermohonan", permohonan.getIdPermohonan());
    }
//Setter and Getter

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return this.permohonan;
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

    public List<String> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<String> senaraiKandungan) {
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

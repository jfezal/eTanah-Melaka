/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.KaunterService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mdizzat
 */
@UrlBinding("/lelong/pembatalan_permohonan")
public class PembatalanPermohonanUtiliti extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(PembatalanPermohonanUtiliti.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    private KaunterService kaunterService;
    @Inject
    FolderDokumenDAO folderDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String idPermohonan;
    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private FolderDokumen folderDokumenSebelum;
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    public String selectedTab = "0";
    private FasaPermohonan fasaPermohonan;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KandunganFolder> kandunganFolderTamb = new ArrayList<KandunganFolder>();
    private List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhDokumen;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan_main.jsp");
    }

    public Resolution kembali() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan_main.jsp");
    }

    public Resolution find() {

        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
//                permohonan.getFolderDokumen().getTajuk();LOG.info("tajuk :" + permohonan.getFolderDokumen().getTajuk());
                LOG.info("tajuk :" + permohonan.getFolderDokumen().getTajuk());
            } else {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan_main.jsp");
            }

            String id = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            String filter = getContext().getRequest().getParameter("filterBy"); // to filter dokumen current permohonan
            String filter2 = getContext().getRequest().getParameter("filterBy2"); // to filter dokumen permohonan sebelum
            if (id == null) {
                LOG.info("-----if------");
                folderDokumen = folderDAO.findById(Long.valueOf(id));
                LOG.info("id Folder :" + folderDokumen.getFolderId());
            } else {
                LOG.info("-----else------");
                folderDokumen = permohonan.getFolderDokumen();
                if (permohonan.getPermohonanSebelum() != null) {
                    folderDokumenSebelum = permohonan.getPermohonanSebelum().getFolderDokumen();
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

            }
            fasaPermohonan = lelongService.findFasaBatalPermohonan(permohonan.getIdPermohonan());
        }
        LOG.info("Rehydrate - finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan.jsp");
    }

    public Resolution refresh() {
        LOG.info("-----refresh-----");
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        idPermohonan = idMohon;
        LOG.info("idMohon : " + idMohon);
        LOG.info("idPermohonan : " + idPermohonan);
        permohonan = permohonanDAO.findById(idMohon);
        find();
        LOG.info("---End---");
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan.jsp");
    }

    public Resolution simpan() throws WorkflowException, StaleObjectException {
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        String ulasan = getContext().getRequest().getParameter("fasaPermohonan.ulasan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        FasaPermohonan ff = lelongService.findFasaBatalPermohonan(permohonan.getIdPermohonan());
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = p.getInfoAudit();
        //jana surat batal
        String gen = "LLGSuratBatal_NS.rdf";
        String code = "SB";
        lelongService.reportGen(permohonan, gen, code, p);
        //save ulasan
        if (ff != null) {
            ff.setUlasan(ulasan);
            lelongService.saveUpdate2(ff);
        } else {
            ff = new FasaPermohonan();

            info.setDimasukOleh(p);
            info.setTarikhMasuk(new java.util.Date());
            ff.setCawangan(p.getKodCawangan());
            ff.setIdPengguna(p.getIdPengguna());
            ff.setInfoAudit(info);
            ff.setIdAliran("batalPermohonanPerintahJual");
            ff.setPermohonan(permohonan);
            ff.setTarikhHantar(new java.util.Date());
            ff.setUlasan(ulasan);
            ff.setKeputusan(kodKeputusanDAO.findById("BP"));
            lelongService.saveUpdate2(ff);
        }
        //update status
        permohonan.setStatus("BP");
        permohonan.setTarikhKeputusan(new java.util.Date());
        KodKeputusan kodKpsn = kodKeputusanDAO.findById("BP");
        permohonan.setKeputusan(kodKpsn);
        permohonan.setKeputusanOleh(p);
        lelongService.saveOrUpdate(permohonan);
        List<Enkuiri> listEnkuiri = lelongService.getAllDesc(permohonan.getIdPermohonan());
        if (!listEnkuiri.isEmpty()) {
            Enkuiri en = listEnkuiri.get(0);
            en.setStatus(kodStatusEnkuiriDAO.findById("BP"));
            lelongService.saveOrUpdate(en);
        }
        List<Lelongan> listLelong = lelongService.listLelonganAKAP(idPermohonan);
        if (!listLelong.isEmpty()) {
            for (Lelongan ll : listLelong) {
                ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("BP"));
                lelongService.saveOrUpdate(ll);
            }
        }
        //withdrawIdPermohonan
        withdrawIdPermohonan(permohonan);
        find();
        selectedTab = getContext().getRequest().getParameter("selectedTab");
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/pembatalan_permohonan.jsp");
    }

    @SuppressWarnings("static-access")
    public void withdrawIdPermohonan(Permohonan permohonan) throws WorkflowException, StaleObjectException {

        LOG.info("IDMohon : " + permohonan.getIdPermohonan());
        idPermohonan = permohonan.getIdPermohonan();
        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
        LOG.info("senaraiTask : " + senaraiTask.size());
        if (senaraiTask.isEmpty()) {
            LOG.info("-----idPermohonan tidak di jumpai-----");
        } else {
            Task task = (Task) senaraiTask.get(0);
            if (task != null) {
                LOG.info("-----idPermohonan di jumpai-----");
                LOG.info(task);
                String taskId = task.getSystemAttributes().getTaskId();
                WorkFlowService.withdrawTask(taskId);
                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                LOG.info("------Finish------");
            }
        }
    }

    public Resolution dokumenTambahanForm() {
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(permohonan.getFolderDokumen().getFolderId()));
        senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());

        LOG.info("----------dokumenTambahanForm--------");
        LOG.info("senaraiKodUrusan : " + senaraiKodUrusan.size());
        listKodDokumen = kaunterService.getKodDokumenNotRequired(senaraiKodUrusan);
        if (kandunganFolderTamb.isEmpty()) {
            for (int i = 0; i
                    < 10; i++) {
                KandunganFolder kf = new KandunganFolder();
                kandunganFolderTamb.add(kf);
            }
        }
//        }
        return new JSP("lelong/dokumen_tambah_utiliti.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDokumenTambahan() {
        LOG.info("simpanDokumenTambahan::start");
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);
        folderDokumen = folderDAO.findById(Long.valueOf(permohonan.getFolderDokumen().getFolderId()));
        LOG.info("kandunganFolderTamb : " + kandunganFolderTamb.size());

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

                } //                KodDokumen kd = d.getKodDokumen();
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
            addSimpleError(
                    t.getMessage());
            result = "fail";
        }
        LOG.info("simpanDokumenTambahan::finish");
        find();
        LOG.info("result : "+result);
        LOG.info("---End-----------");
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new JSP("lelong/pembatalan_permohonan.jsp");
    }

    public Resolution dokumenTab() {
        return new JSP("lelong/dokumen_utiliti.jsp");
    }

    public Resolution ulasan() {
        return new JSP("lelong/ulasan_utiliti.jsp");
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FolderDokumenDAO getFolderDAO() {
        return folderDAO;
    }

    public void setFolderDAO(FolderDokumenDAO folderDAO) {
        this.folderDAO = folderDAO;
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

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<KandunganFolder> getKandunganFolderTamb() {
        return kandunganFolderTamb;
    }

    public void setKandunganFolderTamb(List<KandunganFolder> kandunganFolderTamb) {
        this.kandunganFolderTamb = kandunganFolderTamb;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public KodKlasifikasiDAO getKodKlasifikasiDAO() {
        return kodKlasifikasiDAO;
    }

    public void setKodKlasifikasiDAO(KodKlasifikasiDAO kodKlasifikasiDAO) {
        this.kodKlasifikasiDAO = kodKlasifikasiDAO;
    }

    public String getTarikhDokumen() {
        return tarikhDokumen;
    }

    public void setTarikhDokumen(String tarikhDokumen) {
        this.tarikhDokumen = tarikhDokumen;
    }

    public List<KodDokumen> getListKodDokumen() {
        return listKodDokumen;
    }

    public void setListKodDokumen(List<KodDokumen> listKodDokumen) {
        this.listKodDokumen = listKodDokumen;
    }
}

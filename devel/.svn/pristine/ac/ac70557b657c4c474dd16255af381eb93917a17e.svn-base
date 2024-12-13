/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.DokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TugasanUtilitiDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.InfoMmkn;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.service.BPelService;
import etanah.service.common.DokumenService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.ListUtil;
import etanah.view.dokumen.FolderAction;
import etanah.view.etanahActionBeanContext;
import etanah.view.utility.DocumentUtilityAction;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nns
 */

@UrlBinding("/strata/uploadKertasMMKN")
public class UploadMMKActionBean extends AbleActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    TugasanUtilitiDAO tugasanUtilitiDao;
    @Inject
    ListUtil listUtil;
    @Inject
    private DokumenService dokumenService;
    
    Permohonan permohonan = new Permohonan();
    
    
    private String idPermohonan;
    private Pengguna pengguna; 
    private String kodDokumen;
    private Dokumen dokumen;
    private TugasanUtiliti tugasanUtiliti;
    private InfoMmkn infoMmkn;
    FileBean fileToBeUpload;
    private static Logger LOG = Logger.getLogger(UploadMMKActionBean.class);
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan2 = new ArrayList<KandunganFolder>();
    private List<KodDokumen> senaraiKodMMK;
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private String stageId = "";
    Task task = null;
    
    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }
    
    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("strata/uploadMMKN.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        createNewDocument(pengguna, permohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
        } else {
            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            senaraiKandungan = new ArrayList<KandunganFolder>();

            if (pengguna != null) {
                getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
            }
            String id = getContext().getRequest().getParameter("folder.idFolder");
            id = permohonan.getIdPermohonan();
            if (id != null && id.length() > 0) {
                permohonan = permohonanDAO.findById(id);
                if (permohonan == null) {
                    addSimpleError("ID Permohonan " + id + " tidak wujud");
                }

                if (permohonan != null) {
                    senaraiKodUrusan.add(permohonan.getKodUrusan().getKod());
                }

                //for filtering purposes
                List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
                for (KandunganFolder kf : senaraiKF) {
                    if (kf == null || kf.getDokumen() == null) {
                        continue;
                    }
                    KodDokumen kd = kf.getDokumen().getKodDokumen();

                    if (kd != null) {
                        kodMap.put(kd.getKod(), kd.getNama());
                    }
                    if (stageId != null) {
                        if (permohonan.getKodUrusan().getKod().equals("PKBK")) {
                            if (stageId.equals("drafkertasmmk") || stageId.equals("semakdrafkertasmmk") || stageId.equals("semakkertasmmk") || stageId.equals("perakuan")) {
                                if (kf.getDokumen().getPermohonan() != null) {
                                    if (kf.getDokumen().getKodDokumen().getKod().equals("MMKT") || kf.getDokumen().getKodDokumen().getKod().equals("MMKN")) {
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
    
    public Resolution muatNaikForm() {

        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
        }

        String folderId = getContext().getRequest().getParameter("folderId");
        String dokumenId = getContext().getRequest().getParameter("dokumenId");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (StringUtils.isNotBlank(folderId)) {
            getContext().getRequest().setAttribute("folderId", folderId);
        } else {
            return new ErrorResolution(404, "Folder tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(dokumenId)) {
            getContext().getRequest().setAttribute("dokumenId", dokumenId);
        } else {
            return new ErrorResolution(404, "Dokumen tidak ditentukan.");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        } else {
            return new ErrorResolution(404, "Tiada Id Permohonan.");
        }
        return new JSP("strata/muatNaikMMKN.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("prm"))) {
            return new RedirectResolution(DocumentUtilityAction.class, "searchPermohonan").addParameter("idPermohonan", idPermohonan).addParameter("popup", "true");
        } else {
            return new RedirectResolution(FolderAction.class, "reload").addParameter("permohonan.idPermohonan", idPermohonan);
        }
    }

    public Resolution processUpload() {
        LOG.info("simpanMuatNaik::start");

        String dokumenId = getContext().getRequest().getParameter("dokumenId");

        String folderId = getContext().getRequest().getParameter("folderId");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                LOG.debug("content type = " + contentType);

                permohonan = permohonanDAO.findById(idPermohonan);
                if (permohonan != null) {
                    List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
                    DMSUtil dmsUtil = new DMSUtil();
                    if (senaraiKF.size() > 0) {
                        for (KandunganFolder kf : senaraiKF) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();

                            if (kd != null) {
                                kodMap.put(kd.getKod(), kd.getNama());
                            }
                            if (kf.getDokumen().getKodDokumen().getKod().equals(kodDokumen)) {
                                senaraiKandungan2.add(kf);
                            }
                        }
                        if (senaraiKandungan2.size() < 0) {
//                            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
//                            fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
//                            String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
//                            updatePathDokumen2(fizikalPath, Long.parseLong(dokumenId), contentType);
//                            addSimpleMessage("Muat naik fail berjaya.");
                        } else {
                            FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
                            fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
                            String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
                            updatePathDokumen(fizikalPath, senaraiKandungan2.get(0).getDokumen().getIdDokumen(), contentType);
                            addSimpleMessage("Muat naik fail berjaya.");
                        }
                    }
                } else {
                    addSimpleMessage("Muat naik tidak berjaya.");
                    if (getContext().getRequest().getParameter("prm") != null) {
                        getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                        getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                    }
                    return showForm();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
                addSimpleMessage("Muat naik tidak berjaya.");
                if (getContext().getRequest().getParameter("prm") != null) {
                    getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
                    getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
                }
                return muatNaikForm();
            }
        }

        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }
        return muatNaikForm();
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }
    
    private void createNewDocument(Pengguna peng, Permohonan permohonan) {

        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        //tugasanUtiliti = tugasanUtilitiDao.findById(permohonan.getIdPermohonan());
        FolderDokumen fd = permohonan.getFolderDokumen();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        LOG.debug("task Id = " + taskId);
            BPelService service = new BPelService();
            task = service.getTaskFromBPel(taskId, pengguna);
            stageId = task.getSystemAttributes().getStage();
            //stageId = "drafkertasmmk";
        LOG.debug("stage Id = " + stageId);
        
        if (stageId != null) {
            if (permohonan.getKodUrusan().getKod().equals("PKBK")) {
                if (stageId.equals("drafkertasmmk") || stageId.equals("semakdrafkertasmmk") || stageId.equals("semakkertasmmk") || stageId.equals("perakuan")) {
                    senaraiKodMMK = listUtil.getSenaraiKodDokumenMMKN();
                }
            }

            for (KodDokumen kd : senaraiKodMMK) {
                Dokumen doc = dokumenService.findDokumenByIdMohon(idPermohonan, kd.getKod());
                if (doc == null) {
                    LOG.debug("doc null");
                    dokumen = new Dokumen();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    dokumen.setBaru('Y');

                    dokumen.setKlasifikasi(kodKlas);
                    dokumen.setFormat("application/pdf");
                    dokumen.setInfoAudit(ia);
                    dokumen.setPermohonan(permohonanDAO.findById(idPermohonan));
                    dokumen.setNoVersi("1.0");
                    dokumen.setTajuk(kd.getNama());
                    dokumen.setKodDokumen(kd);
                    dokumen = dokumenService.saveOrUpdate(dokumen);

                    KandunganFolder folderDokumen = new KandunganFolder();
                    folderDokumen.setInfoAudit(ia);
                    folderDokumen.setFolder(permohonan.getFolderDokumen());
                    folderDokumen.setDokumen(dokumen);
                    dokumenService.saveKandunganWithDokumen(folderDokumen);

                }
            }
        }

    }

 

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }
    
    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public TugasanUtilitiDAO getTugasanUtilitiDao() {
        return tugasanUtilitiDao;
    }

    public void setTugasanUtilitiDao(TugasanUtilitiDAO tugasanUtilitiDao) {
        this.tugasanUtilitiDao = tugasanUtilitiDao;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
    
    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }
}

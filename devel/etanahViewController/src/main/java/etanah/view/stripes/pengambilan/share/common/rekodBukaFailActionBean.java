/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodRujukanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.InfoMmkn;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.common.DokumenService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/common/rekodBukaFail")
//public class rekodBukaFailActionBean {
public class rekodBukaFailActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanService permohonanServ;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    DokumenDAO dokumenDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger LOG = Logger.getLogger(RekodKpsnMMKActionBean.class);

    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<KandunganFolder> senaraiKandungan2 = new ArrayList<KandunganFolder>();
    private List<Dokumen> senaraiKandunganSebelum = new ArrayList<Dokumen>();
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
    private Map<String, String> kodMapSebelum = new HashMap<String, String>();
    private Map<String, String> kodMap = new HashMap<String, String>();
    private ArrayList<String> senaraiKodUrusan = new ArrayList<String>();
    private List<KodDokumen> senaraiKodMMK;

    Permohonan permohonan;
    Dokumen dokumen;
    Pengguna pengguna;
    String idPermohonan;
    FileBean fileToBeUpload;
    PermohonanRujukanLuar permohonanRujukanLuar;
    String kodDokumen;
    String noRujukan;
    private String tarikhFail;
    String[] kodDokumenBaru;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanServ.findById(idPermohonan);

//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new JSP("/pengambilan/APT/rekod_buka_fail.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanServ.findById(idPermohonan);
        kodDokumenBaru = new String[]{"UPEN4", "UPEN3"};
//        kodDokumenBaru = "UPEN3";
        for (String kodDk : kodDokumenBaru) {
            permohonanRujukanLuar = permohonanRujukanLuarService.findByidPermohonanNoRujsuratNew(idPermohonan, kodDk);
            if (permohonanRujukanLuar != null) {
                senaraiPermohonanRujukanLuar.add(permohonanRujukanLuar);
            }

        }

        createNewDocument(pengguna, permohonan);
        if (permohonanRujukanLuar != null) {
            if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                tarikhFail = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
            }
            if (permohonanRujukanLuar.getNoRujukan() != null) {
                noRujukan = permohonanRujukanLuar.getNoRujukan();
            }
        }

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
                permohonan = permohonanServ.findById(id);
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
                    if (kd.getKod().equals("UPEN4") || kd.getKod().equals("UPEN3")) {
                        if (kf.getDokumen().getPermohonan() != null) {
                            senaraiKandungan.add(kf);
                        }
                    }
                }
            }

        }
    }

    public Resolution processUpload() {
        LOG.info("simpanMuatNaik::start");

        String dokumenId = getContext().getRequest().getParameter("dokumenId");

        String folderId = getContext().getRequest().getParameter("folderId");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        if (!kodDokumen.equals("")) {
        if (fileToBeUpload != null) {
            try {
                String fileName = fileToBeUpload.getFileName();

                String contentType = fileToBeUpload.getContentType();

                LOG.debug("content type = " + contentType);

                permohonan = permohonanServ.findById(idPermohonan);
                if (permohonan != null) {
                    List<KandunganFolder> senaraiKF = permohonan.getFolderDokumen().getSenaraiKandungan();
                    DMSUtil dmsUtil = new DMSUtil();
                    if (senaraiKF.size() > 0) {
                        for (KandunganFolder kf : senaraiKF) {
                            if (kf == null || kf.getDokumen() == null) {
                                continue;
                            }
                            KodDokumen kd = kf.getDokumen().getKodDokumen();
//                        urusanDokumen = strataService.findUrusanDokbyKOD(kd.getKod(), permohonan.getKodUrusan().getKod());

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

//        Logger.getLogger(MuatnaikActionBean.class).info("simpanMuatNaik::finish");
        if (getContext().getRequest().getParameter("prm") != null) {
            getContext().getRequest().setAttribute("prm", getContext().getRequest().getParameter("prm"));
            getContext().getRequest().setAttribute("idPermohonan", getContext().getRequest().getParameter("idPermohonan"));
        }
//        return new RedirectResolution(FolderAction.class).addParameter("permohonan.idPermohonan", idPermohonan);
//        return new JSP("strata/utiliti/muat_naik_dokumen.jsp").addParameter("popup", "true");
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

    public Resolution simpanFile() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String[] kodDokumenBaru;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        kodDokumenBaru = new String[]{"UPEN4", "UPEN3"};
//        kodDokumenBaru = "UPEN3";
        for (String kodDk : kodDokumenBaru) {
            permohonan = permohonanServ.findById(idPermohonan);
            permohonanRujukanLuar = permohonanRujukanLuarService.findByidPermohonanNoRujsuratNew(idPermohonan, kodDk);
            if (permohonanRujukanLuar == null) {
                PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
                prl.setTarikhRujukan(sdf.parse(tarikhFail));
                prl.setNoRujukan(noRujukan);
                prl.setInfoAudit(ia);
                prl.setKodRujukan(kodRujukanDAO.findById(kodDk));
                prl.setPermohonan(permohonan);
                prl.setKodDokumenRujukan(kodDokumenDAO.findById(kodDk));
                permohonanRujukanLuarService.saveOrUpdate(prl);
            }
        }
        rehydrate();
        return showForm();
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
        return new JSP("/pengambilan/APT/muat_naik_APT_UPEN.jsp").addParameter("popup", "true");
    }

    private void createNewDocument(Pengguna peng, Permohonan permohonan) {

        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar

//        permohonan
        FolderDokumen fd = permohonan.getFolderDokumen();
        senaraiKodMMK = listUtil.getSenaraiKodDokumenMMKN();
        kodDokumenBaru = new String[]{"UPEN4", "UPEN3"};

        for (String kodDk : kodDokumenBaru) {
            KodDokumen kd = kodDokumenDAO.findById(kodDk);
            Dokumen doc = dokumenService.findDokumenByIdMohon(idPermohonan, kd.getKod());
            if (doc == null) {
//                LOG.debug("doc null");
                dokumen = new Dokumen();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                dokumen.setBaru('Y');

                dokumen.setKlasifikasi(kodKlas);
                dokumen.setFormat("application/pdf");
                dokumen.setInfoAudit(ia);
                dokumen.setPermohonan(permohonanServ.findById(idPermohonan));
                //TODO : increase versi if regenarate report
                dokumen.setNoVersi("1.0");
                dokumen.setTajuk(kd.getKod());
                dokumen.setKodDokumen(kd);
//            dokumen.setDalamanNilai1(id);
                dokumen = dokumenService.saveOrUpdate(dokumen);
                KandunganFolder folderDokumen = new KandunganFolder();
                folderDokumen.setInfoAudit(ia);
                folderDokumen.setFolder(permohonan.getFolderDokumen());
                folderDokumen.setDokumen(dokumen);
                dokumenService.saveKandunganWithDokumen(folderDokumen);
            }
        }

    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PermohonanService getPermohonanServ() {
        return permohonanServ;
    }

    public void setPermohonanServ(PermohonanService permohonanServ) {
        this.permohonanServ = permohonanServ;
    }

    public KodKlasifikasiDAO getKodKlasifikasiDAO() {
        return kodKlasifikasiDAO;
    }

    public void setKodKlasifikasiDAO(KodKlasifikasiDAO kodKlasifikasiDAO) {
        this.kodKlasifikasiDAO = kodKlasifikasiDAO;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public PermohonanRujukanLuarService getPermohonanRujukanLuarService() {
        return permohonanRujukanLuarService;
    }

    public void setPermohonanRujukanLuarService(PermohonanRujukanLuarService permohonanRujukanLuarService) {
        this.permohonanRujukanLuarService = permohonanRujukanLuarService;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        rekodBukaFailActionBean.LOG = LOG;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<KandunganFolder> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<KandunganFolder> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<Dokumen> getSenaraiKandunganSebelum() {
        return senaraiKandunganSebelum;
    }

    public void setSenaraiKandunganSebelum(List<Dokumen> senaraiKandunganSebelum) {
        this.senaraiKandunganSebelum = senaraiKandunganSebelum;
    }

    public Map<String, String> getKodMapSebelum() {
        return kodMapSebelum;
    }

    public void setKodMapSebelum(Map<String, String> kodMapSebelum) {
        this.kodMapSebelum = kodMapSebelum;
    }

    public Map<String, String> getKodMap() {
        return kodMap;
    }

    public void setKodMap(Map<String, String> kodMap) {
        this.kodMap = kodMap;
    }

    public ArrayList<String> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(ArrayList<String> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public List<KodDokumen> getSenaraiKodMMK() {
        return senaraiKodMMK;
    }

    public void setSenaraiKodMMK(List<KodDokumen> senaraiKodMMK) {
        this.senaraiKodMMK = senaraiKodMMK;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
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

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTarikhFail() {
        return tarikhFail;
    }

    public void setTarikhFail(String tarikhFail) {
        this.tarikhFail = tarikhFail;
    }

    public KodRujukanDAO getKodRujukanDAO() {
        return kodRujukanDAO;
    }

    public void setKodRujukanDAO(KodRujukanDAO kodRujukanDAO) {
        this.kodRujukanDAO = kodRujukanDAO;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }

    public String[] getKodDokumenBaru() {
        return kodDokumenBaru;
    }

    public void setKodDokumenBaru(String[] kodDokumenBaru) {
        this.kodDokumenBaru = kodDokumenBaru;
    }

}

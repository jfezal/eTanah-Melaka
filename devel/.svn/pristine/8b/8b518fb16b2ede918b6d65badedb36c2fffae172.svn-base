/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.IntegrasiDokumenDAO;
import etanah.dao.IntegrasiPermohonanButirDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.IntegrasiDokumen;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.JFileChooser;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class JupemInIntegration1 {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    IntegrasiDokumenDAO integrasiDokumenDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrationMohonbutirDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    
    KodDokumen kodDokumen = new KodDokumen();
    IWorkflowContext ctx = null;
    IWorkflowContext ctxOnBehalf = null;
    private static final Logger LOG = Logger.getLogger(JupemInIntegration1.class);
    private String taskId;
    private String stage;
    JFileChooser chooser = new JFileChooser();
    private IntegrasiDokumen integrasiDok;
    String typeDokumen = "";
    InfoAudit ia;
    String idPermohonan = "";
    String idAliran = "";
    Permohonan permohonan;

    public String inboundGIS(boolean initiate) throws IOException, Exception {
        String kpsn = "";
        String inbound = conf.getProperty("gis.inbound.path");
        String documentPath = conf.getProperty("document.path");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("IdPermohonan: " + idPermohonan + " idAliran : " + idAliran);
        IntegrasiPermohonan integrationMohon = getIntegMohon(idPermohonan, idAliran);
        if (integrationMohon != null) {
            LOG.info("IdInteg : " + integrationMohon.getIdInteg());
            IntegrasiPermohonanButir integrationMohonbutir = getintegMohonButirByIdInteg(integrationMohon.getIdInteg());
            if (integrationMohonbutir != null) {
                LOG.info("integrationMohonbutir.getIdButir(): " + integrationMohonbutir.getIdButir());
                List<IntegrasiPermohonanDokumen> listIntgMDok = getIntegrasiPermohonanDokumen(integrationMohonbutir.getIdButir());
                List<IntegrasiDokumen> listInDoc = getIntegrasiDokumen(integrationMohon.getIdAliranTerima(), integrationMohon.getKodUrusan().getKod());
                if (listInDoc.size() == listIntgMDok.size()) {
                    LOG.info("listInDoc :" + listInDoc.size() + " listIntgMDok :" + listIntgMDok.size());
                    String[] folderL = dirlist(inbound + File.separator + integrationMohonbutir.getNamaFolderTerima());
                    LOG.info("folderL : " + folderL.length);
                    if (folderL.length == listInDoc.size()) {
                        for (int i = 0; i < folderL.length; i++) {

                            String kod = new String();
                            kod = listIntgMDok.get(i).getKodDokumen().getKod();
                            kodDokumen = kodDokumenDAO.findById(kod);
                            File src = new File(inbound + File.separator + integrationMohonbutir.getNamaFolderTerima() + File.separator + listIntgMDok.get(i).getNamaFizikal());
                            typeDokumen = new String();
                            typeDokumen = new MimetypesFileTypeMap().getContentType(src);
                            Long filename = (saveDokumen(getFolder(idPermohonan).getFolderId(), ia)).getIdDokumen();
                            File dst = new File(documentPath + File.separator + getFolder(idPermohonan).getFolderId() + File.separator);
                            if (dst.isDirectory()) {
                                if (!dst.exists()) {
                                    dst.mkdir();
                                }
                            }
                            dst = new File(documentPath + File.separator + getFolder(idPermohonan).getFolderId() + File.separator + filename);
                            System.out.println("File Type= " + typeDokumen);
                            copyDirectory(src, dst);
                        }
                        if (integrationMohon.getKodKeputusan() == null) {
                            kpsn = "APPROVE";
                        } else {
                            kpsn = integrationMohon.getKodKeputusan().getKod();
                        }
                        if(initiate){
                        initiateTaskbyId(permohonan, kpsn, "sysjupem1");
                        }
                    } else {
                        LOG.info("Senarai folder dan senarai dokumen integration tidak sama");
                    }
                } else {
                    LOG.info("Senarai integration mohon dokumen dan senarai dokumen integration tidak sama");
                }
            }
        }
        return "";
    }

    public void initiateTaskbyId(Permohonan permohonan, String outcome, String username) throws WorkflowException, StaleObjectException {
        ctxOnBehalf = WorkFlowService.authenticate(username);
        List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
        LOG.info("Task FOund::" + l);
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            taskId = t.getSystemAttributes().getTaskId();
            if (t.getSystemAttributes().getAcquiredBy() == null) {
                WorkFlowService.acquireTask(taskId, ctxOnBehalf);
            }
            LOG.info("AcquiredBy::" + t.getSystemAttributes().getAcquiredBy());
            try {
                WorkFlowService.getTask(taskId, ctxOnBehalf);
                LOG.debug("Claim Found Task::" + taskId);
                WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, outcome); // "L" comfirm balik
                LOG.debug("Update Task Outcome" + stage);
            } catch (StaleObjectException ex) {
                java.util.logging.Logger.getLogger(JupemInIntegration1.class.getName()).info(ex.toString());
            }
        }
    }

    public FolderDokumen getFolder(String tajuk) {
        String query = "SELECT b FROM etanah.model.FolderDokumen b where b.tajuk = :tajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("tajuk", tajuk);
        return (FolderDokumen) q.uniqueResult();
    }

    public Dokumen saveDokumen(Long idFolder, InfoAudit ia) throws Exception {
        try {
            Dokumen doc = new Dokumen();
            KodKlasifikasi kodk = new KodKlasifikasi();
//            KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
            doc.setTajuk(kodDokumen.getKod());
            doc.setInfoAudit(ia);
            doc.setKodDokumen(kodDokumen);
//            doc.setKlasifikasi(klasifikasiAm);
            doc.setNoVersi("1.0");
            doc = dokumenDAO.saveOrUpdate(doc);

            String fizikalPath = idFolder + File.separator + doc.getIdDokumen();
            updatePathDokumen(fizikalPath, doc, this.typeDokumen);
            updateFolderDoc(ia, doc, idFolder);
            return doc;
        } catch (Exception ex) {
            Logger.getLogger(JupemInIntegration1.class.getName()).error(ex);
            return null;
        }
    }

    @Transactional
    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
        d.setTajuk(d.getKodDokumen().getKod());
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        System.out.println("Dokumen: " + d);
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    @Transactional
    public void updateFolderDoc(InfoAudit ia, Dokumen doc, Long id) {
        KandunganFolder kanFolder = new KandunganFolder();
        FolderDokumen fd;
        fd = folderDokumenDAO.findById(id);
        System.out.println("DOKUMEN: " + doc.getIdDokumen());
        kanFolder.setDokumen(doc);
        kanFolder.setFolder(fd);
        kanFolder.setCatatan("S");
        kanFolder.setInfoAudit(ia);
        kandunganFolderDAO.save(kanFolder);
    }

    public List<IntegrasiDokumen> getIntegrasiDokumen(String idAliran, String kod) {
        String query = "SELECT b FROM etanah.model.IntegrasiDokumen b where b.idAliran = :idAliran and b.kodUrusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idAliran", idAliran);
        q.setString("kod", kod);
        return q.list();
    }

    private IntegrasiPermohonanButir getintegMohonButirByIdInteg(Long idInteg) {
        String query = "SELECT b FROM etanah.model.IntegrasiPermohonanButir b where b.integrasiPermohonan.idInteg = :idInteg";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idInteg", idInteg);
        return (IntegrasiPermohonanButir) q.list().get(0);
    }

    private IntegrasiPermohonan getIntegMohon(String idPermohonan, String idAliranTerima) {
        String query = "SELECT b FROM etanah.model.IntegrasiPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.idAliranTerima = :idAliranTerima";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliranTerima", idAliranTerima);
        return (IntegrasiPermohonan) q.uniqueResult();
    }

    private List<IntegrasiPermohonanDokumen> getIntegrasiPermohonanDokumen(Long idButir) {
        String query = "SELECT b FROM etanah.model.IntegrasiPermohonanDokumen b where b.integrasiPermohonanButir.idButir = :idButir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idButir", idButir);
        return q.list();
    }

    private String[] dirlist(String fname) {
        File dir = new File(fname);
        String[] chld = dir.list();
        if (chld == null) {
            System.out.println("Specified directory does not exist or is not a directory.:" + fname);
        } else {
            for (int i = 0; i < chld.length; i++) {
                String fileName = chld[i];
                System.out.println(fileName);
            }
        }
        return dir.list();
    }

    public void copyDirectory(File srcPath, File dstPath) throws IOException {

        if (srcPath.isDirectory()) {
            if (!dstPath.exists()) {
                dstPath.mkdir();
            }

            String files[] = srcPath.list();
            for (int i = 0; i < files.length; i++) {
                copyDirectory(new File(srcPath, files[i]), new File(dstPath, files[i]));
            }

        } else {
            if (!srcPath.exists()) {
                System.out.println("File or directory does not exist.");


            } else {
                InputStream in = new FileInputStream(srcPath);
                OutputStream out = new FileOutputStream(dstPath);
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
        System.out.println("file copied.");
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
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

    public IntegrasiDokumen getIntegrasiDok() {
        return integrasiDok;
    }

    public void setIntegrasiDok(IntegrasiDokumen integrasiDok) {
        this.integrasiDok = integrasiDok;
    }

    InfoAudit getInfoPenguna(String peng) {
        Pengguna pengguna = new Pengguna();
        pengguna = penggunaDAO.findById(peng);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }
}

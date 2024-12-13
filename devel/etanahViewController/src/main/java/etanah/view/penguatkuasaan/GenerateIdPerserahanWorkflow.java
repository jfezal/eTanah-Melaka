/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan;

import etanah.view.stripes.hasil.*;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurshahida.radzi
 */
public class GenerateIdPerserahanWorkflow {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public Permohonan generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
//            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq
        Permohonan p = new Permohonan();
        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            for (Hakmilik hm : senaraiHakmilik) {

                
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                LOG.info(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);                
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    p.setPenyerahNama(permohonan.getPenyerahNama());
                    p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                    p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                    if (permohonan.getPenyerahAlamat3() != null) {
                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                    }

                    if (permohonan.getPenyerahAlamat4() != null) {
                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                    }
                    p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                    p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                   
                    //ida update 28/06/2013
                        if (conf.getProperty("kodNegeri").equals("05")) {
                         p.setIdWorkflow(p.getKodUrusan().getIdWorkflowIntegration());
                        }
                        

                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);


                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                permohonan = p;
            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
//            return false;
        }
        return p;
    }

    public Permohonan genWorkflowIdPerserahan(
            KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        Permohonan permohonanBaru = new Permohonan();
        try {
            generateIdPerserahanWorkflow(ku, pengguna, senaraiHakmilik, permohonan);
            permohonanBaru =
                    permohonan;
            LOG.debug("idPermohonan :" + permohonanBaru.getIdPermohonan());
        } catch (Exception ex) {
            LOG.error(ex);
        }

        return permohonanBaru;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}

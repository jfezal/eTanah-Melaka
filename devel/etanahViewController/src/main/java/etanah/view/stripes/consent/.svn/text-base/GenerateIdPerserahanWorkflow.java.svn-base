/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCaraPermohonanDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.KodService;
import etanah.service.daftar.MohonHakmilikService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author muhammad.amin
 */
public class GenerateIdPerserahanWorkflow {

    private static final Logger LOG = Logger.getLogger(GenerateIdPerserahanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idGenerator2;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    MohonHakmilikService mhservice;
    @Inject
    private KodService kodService;
    @Inject
    private KodPenyerahDAO kodPenyerahDAO;
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

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = pengguna.getKodCawangan();
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

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

                Permohonan p = new Permohonan();
                p.setStatus("TA");
                String idPermohonan = "";
                if (ku.getKod().equals("100")) {
                    idPermohonan = idGenerator2.generate(conf.getProperty("kodNegeri"), caw, ku);
                    KodCaraPermohonan kod = kodCaraPermohonanDAO.findById("UD");
                    p.setCaraPermohonan(kod);
                } else {
                    idPermohonan = idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku);
                }
                p.setIdPermohonan(idPermohonan);
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    if (ku.getKod().equals("100")) {
                        List<KodCawanganJabatan> senarai 
                                = kodService.findKodCawanganJabatan2(pengguna.getKodCawangan().getKod(), null, null, "16");
                        if (!senarai.isEmpty()) {
                            KodCawanganJabatan jabatan = senarai.get(0);
                            p.setPenyerahNama(jabatan.getNama());
                            p.setKodPenyerah(kodPenyerahDAO.findById(jabatan.getKodPenyerah()));
                            p.setPenyerahAlamat1(jabatan.getAlamat1());
                            p.setPenyerahAlamat2(jabatan.getAlamat2());
                            p.setPenyerahAlamat3(jabatan.getAlamat3());
                            p.setPenyerahAlamat4(jabatan.getAlamat4());
                            p.setPenyerahPoskod(jabatan.getPoskod());
                            p.setPenyerahNegeri(jabatan.getNegeri());
                        }
                    } else {
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
                if (!ku.getKod().equals("100")) {
                    permohonan = p;
                }
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
            return false;
        }
        return true;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.StrataPtService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodJabatan;
import etanah.model.KodRujukan;
import etanah.service.PembangunanService;

/**
 *
 * @author wan.fairul
 */
public class GenerateIdPermohonan {

    private static final Logger LOG = Logger.getLogger(GenerateIdPermohonan.class);
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
    StrataPtService strService;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PembangunanService pembangunanServ;
    private Permohonan permohonan;
    private Permohonan mohon;
    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = permohonan.getCawangan();
        }
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



            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(caw);
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

            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            for (Hakmilik hm : senaraiHakmilik) {
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
            }

//            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
//                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                    p.getKodUrusan().getNama());

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
            mohon = p;

            //for PSM
            LOG.info(mohon);
            PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setCawangan(mohon.getCawangan());
            permohonanRujukanLuar.setPermohonan(mohon);
            permohonanRujukanLuar.setInfoAudit(ia);
            KodJabatan k = kodJabatanDAO.findById("6");
            KodRujukan r = kodRujukanDAO.findById("FL");
            permohonanRujukanLuar.setJabatan(k);
            permohonanRujukanLuar.setNoRujukan(permohonan.getIdPermohonan());
            permohonanRujukanLuar.setKodRujukan(r);
            strService.SimpanMohonRujukLuar(permohonanRujukanLuar);

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

    public boolean generateIdPerserahanWorkflow_Integration(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = permohonan.getCawangan();
        }
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
            
            KodCaraPermohonan kcp = new KodCaraPermohonan();
            kcp = pembangunanServ.findKodCaraPermohonan("UD");

            KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(caw.getKod());

            Permohonan p = new Permohonan();
            p.setCaraPermohonan(kcp);
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(caw);
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setIdWorkflow(ku.getIdWorkflowIntegration());
            if (permohonan != null) {
                p.setPermohonanSebelum(permohonan);
                //p.setPenyerahNama(permohonan.getPenyerahNama());
                p.setPenyerahNama("Unit Pembangunan");
                //p.setKodPenyerah(permohonan.getKodPenyerah());
                p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                if (kodCawanganJabatan.getAlamat3() != null) {
                    p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                }

                if (kodCawanganJabatan.getAlamat4() != null) {
                    p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                }
                p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            for (Hakmilik hm : senaraiHakmilik) {
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
            }

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                    p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());
            mohon = p;


            for (Hakmilik hm : senaraiHakmilik) {
                String idHakmilik = hm.getIdHakmilik();

                //for PSM
                LOG.info(mohon);
                PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setCawangan(mohon.getCawangan());
                permohonanRujukanLuar.setPermohonan(mohon);
                permohonanRujukanLuar.setInfoAudit(ia);
                KodJabatan k = kodJabatanDAO.findById("6");
                KodRujukan r = kodRujukanDAO.findById("FL");
                permohonanRujukanLuar.setJabatan(k);
                permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
                permohonanRujukanLuar.setNoRujukan(permohonan.getIdPermohonan());
                permohonanRujukanLuar.setKodRujukan(r);
                // sethakmilik
                LOG.info(idHakmilik);
                permohonanRujukanLuar.setHakmilik(hm);

                strService.SimpanMohonRujukLuar(permohonanRujukanLuar);
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

    public boolean generateIdPerserahanWorkflowDiffHakmilik(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        for (int a = 0; a < senaraiHakmilik.size(); a++) {

            KodCawangan caw = null;
            if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(a).getKodHakmilik().getKod().equals("HSD")))) {
                caw = kodCawanganDAO.findById("00");
            } else {
                caw = permohonan.getCawangan();
            }
            LOG.info(ku.getNama());
            LOG.info(permohonan.getFolderDokumen());

            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            InfoAudit ia = new InfoAudit();
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
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
                KodCaraPermohonan kcp = new KodCaraPermohonan();
                kcp = pembangunanServ.findKodCaraPermohonan("UD");
                
                KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(caw.getKod());
                
                Permohonan p = new Permohonan();
                p.setCaraPermohonan(kcp);
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(caw);
                p.setKodUrusan(ku);
                p.setFolderDokumen(fd);
                p.setIdWorkflow(ku.getIdWorkflowIntegration());
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    p.setPenyerahNama("Unit Pembangunan");
                    //p.setKodPenyerah(permohonan.getKodPenyerah());
                    p.setPenyerahAlamat1(kodCawanganJabatan.getAlamat1());
                    p.setPenyerahAlamat2(kodCawanganJabatan.getAlamat2());
                    if (kodCawanganJabatan.getAlamat3() != null) {
                        p.setPenyerahAlamat3(kodCawanganJabatan.getAlamat3());
                    }

                    if (kodCawanganJabatan.getAlamat4() != null) {
                        p.setPenyerahAlamat4(kodCawanganJabatan.getAlamat4());
                    }
                    p.setPenyerahPoskod(kodCawanganJabatan.getPoskod());
                    p.setPenyerahNegeri(kodCawanganJabatan.getNegeri());

                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);
                
                for (int c=0; c<1; c++) {
                //for (Hakmilik hm : senaraiHakmilik) {
                    Hakmilik hm = (Hakmilik)senaraiHakmilik.get(a);
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
                }

                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflowIntegration(),
                        p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                mohon = p;

                for (int b = 0; b < 1; b++) {
                    //for (Hakmilik hm : senaraiHakmilik) {
                    Hakmilik hm = (Hakmilik) senaraiHakmilik.get(a);
                    String idHakmilik = hm.getIdHakmilik();

                    //for PSM
                    LOG.info(mohon);
                    PermohonanRujukanLuar permohonanRujukanLuar = new PermohonanRujukanLuar();
                    permohonanRujukanLuar.setCawangan(mohon.getCawangan());
                    permohonanRujukanLuar.setPermohonan(mohon);
                    permohonanRujukanLuar.setInfoAudit(ia);
                    KodJabatan k = kodJabatanDAO.findById("6");
                    KodRujukan r = kodRujukanDAO.findById("FL");
                    permohonanRujukanLuar.setJabatan(k);
                    permohonanRujukanLuar.setNoFail(permohonan.getIdPermohonan());
                    permohonanRujukanLuar.setNoRujukan(permohonan.getIdPermohonan());
                    permohonanRujukanLuar.setKodRujukan(r);
                    // sethakmilik
                    LOG.info(idHakmilik);
                    permohonanRujukanLuar.setHakmilik(hm);

                    strService.SimpanMohonRujukLuar(permohonanRujukanLuar);
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

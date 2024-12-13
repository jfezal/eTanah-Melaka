/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPenyerah;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.sequence.GeneratorIdPermohonan;
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
 * @author nordiyana
 */
public class PermohonanBaruPengambilanService {
 private static final Logger LOG = Logger.getLogger(PermohonanBaruPengambilanService.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    LelongService lelongService;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idMohonGenerator;

    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */
    public boolean generateIdPermohonanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, FolderDokumen folder) {
        KodCawangan caw = pengguna.getKodCawangan();
//        KodCawangan caw = kodCawanganDAO.findById("00");
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
            if (folder == null) {
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
            } else {
                fd = folder;
            }

            KodPenyerah kp = kodPenyerahDAO.findById("01"); // Kod Penyerah Dalaman. cth, Unit Hasil
            Permohonan p = new Permohonan();
            for (Hakmilik hm : senaraiHakmilik) {


                p.setStatus("TA");
//                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setPenyerahNama("UNIT LELONG");
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    p.setPermohonanSebelum(permohonan);
                    p.setKodPenyerah(kp);
                    p.setPenyerahNama(kp.getNama());
                    if (permohonan.getIdPenyerah() != null) {
                        p.setIdPenyerah(permohonan.getIdPenyerah());
                    }
                    if (permohonan.getKodPenyerah() != null) {
                        p.setKodPenyerah(permohonan.getKodPenyerah());
                    }
                    if (permohonan.getPenyerahJenisPengenalan() != null) {
                        p.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
                    }
                    if (permohonan.getPenyerahNoPengenalan() != null) {
                        p.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
                    }
//                    if(permohonan.getPenyerahNoRujukan() != null)
//                        p.setPenyerahNoRujukan(permohonan.getPenyerahNoRujukan());
                    if (permohonan.getPenyerahNama() != null) {
                        p.setPenyerahNama(permohonan.getPenyerahNama());
                    }
                    if (permohonan.getPenyerahAlamat1() != null) {
                        p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                    }
                    if (permohonan.getPenyerahAlamat2() != null) {
                        p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                    }
                    if (permohonan.getPenyerahAlamat3() != null) {
                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                    }
                    if (permohonan.getPenyerahAlamat4() != null) {
                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                    }
                    if (permohonan.getPenyerahPoskod() != null) {
                        p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                    }
                    if (permohonan.getPenyerahNegeri() != null) {
                        p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                    }
                    if (permohonan.getPenyerahNoTelefon1() != null) {
                        p.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
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
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());

                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());

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

    public Permohonan generateIdPermohonanWorkflowGetIdMohon(KodUrusan ku, Pengguna pengguna,
            List<Hakmilik> senaraiHakmilik, Permohonan permohonan, FolderDokumen folder) {
        KodCawangan caw = pengguna.getKodCawangan();
//        KodCawangan caw = kodCawanganDAO.findById("00");
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return null;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
//            fd = permohonan.getFolderDokumen();
            if (folder == null) {
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
            } else {
                fd = folder;
            }

            KodPenyerah kp = kodPenyerahDAO.findById("01"); // Kod Penyerah Dalaman. cth, Unit Hasil

            for (Hakmilik hm : senaraiHakmilik) {


                p.setStatus("TA");
//                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                p.setKodUrusan(ku);
                p.setPenyerahNama("UNIT PENGAMBILAN");
                p.setFolderDokumen(fd);
                if (permohonan != null) {
                    if (permohonan.getPermohonanSebelum() == null) {
                        p.setPermohonanSebelum(permohonan);
                    }
                    if (permohonan.getPermohonanSebelum() != null) {
                        p.setPermohonanSebelum(permohonan.getPermohonanSebelum());
                    }
                    p.setKodPenyerah(kp);
                    p.setPenyerahNama(kp.getNama());
                    if (permohonan.getIdPenyerah() != null) {
                        p.setIdPenyerah(permohonan.getIdPenyerah());
                    }
                    if (permohonan.getKodPenyerah() != null) {
                        p.setKodPenyerah(permohonan.getKodPenyerah());
                    }
                    if (permohonan.getPenyerahJenisPengenalan() != null) {
                        p.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
                    }
                    if (permohonan.getPenyerahNoPengenalan() != null) {
                        p.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
                    }
                    if (permohonan.getPenyerahNoRujukan() != null) {
                        p.setPenyerahNoRujukan(permohonan.getPenyerahNoRujukan());
                    }
                    if (permohonan.getPenyerahNama() != null) {
                        p.setPenyerahNama(permohonan.getPenyerahNama());
                    }
                    if (permohonan.getPenyerahAlamat1() != null) {
                        p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
                    }
                    if (permohonan.getPenyerahAlamat2() != null) {
                        p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
                    }
                    if (permohonan.getPenyerahAlamat3() != null) {
                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
                    }
                    if (permohonan.getPenyerahAlamat4() != null) {
                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
                    }
                    if (permohonan.getPenyerahPoskod() != null) {
                        p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
                    }
                    if (permohonan.getPenyerahNegeri() != null) {
                        p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
                    }
                    if (permohonan.getPenyerahNoTelefon1() != null) {
                        p.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
                    }
                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                List<PermohonanAsal> list = lelongService.listMohonAsal2(permohonan.getIdPermohonan());

                PermohonanAsal pasal = new PermohonanAsal();
                pasal.setInfoAudit(ia);
                pasal.setIdPermohonan(p);
                if (list.size() == 0) {
                    pasal.setIdPermohonanAsal(permohonan);
                }else{
                    pasal.setIdPermohonanAsal(list.get(0).getIdPermohonanAsal());
                }
                pasal.setCawangan(caw);
                lelongService.saveOrUpdate(pasal);
                KodStatusLelongan kod = kodStatusLelonganDAO.findById("AP");
//                for (Lelongan lelongan : listLEL) {
//                    lelongan.setKodStatusLelongan(kod);
//                    lelongan.setPermohonan(p);
//                    lelongService.saveOrUpdate(lelongan);
//                }

                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hpa.setNomborRujukan(permohonan.getSenaraiHakmilik().get(0).getNomborRujukan());
                hakmilikPermohonanDAO.save(hpa);
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());

                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());

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
            return null;
        }
        return p;
    }
}

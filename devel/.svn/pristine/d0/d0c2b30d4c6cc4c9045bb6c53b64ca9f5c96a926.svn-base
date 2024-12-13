package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusan;
import etanah.model.KodPenyerah;
import etanah.model.KodStatusPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author abu.mansur
 */
public class GenerateIdPermohonanWorkflow {
    private static final Logger LOG = Logger.getLogger(GenerateIdPermohonanWorkflow.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    private GeneratorIdPermohonan idMohonGenerator;
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

    private Permohonan permohonanDaftar;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dtchDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    // for pembatalan permohonan
    @Inject
    KodKeputusanDAO kodKpsnDAO;
    @Inject
    KodStatusPermohonanDAO kodStsMohonDAO;
    @Inject
    NotisPeringatan6AManager manager;
/*
 *  for permohonan that not start with spoc
 *  generate id permohonan and send to bpel process
 *  @param KodUrusan
 *  @param Pengguna
 *  @param hakmilikList
 */
    public boolean generateIdPermohonanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String idworkflow = null;
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

            KodPenyerah kp = kodPenyerahDAO.findById("00"); // Kod Penyerah Dalaman. cth, Unit Hasil
            Permohonan p = new Permohonan();
            for (Hakmilik hm : senaraiHakmilik) {

                // utk urusan pendaftaran (kodJabatan=16) n hakmilik pendaftar sahaja
                if(ku.getJabatan().getKod().equals("16") && (hm.getKodHakmilik().getKod().equals("GRN") || hm.getKodHakmilik().getKod().equals("HSD") || hm.getKodHakmilik().getKod().equals("PN"))){
                    caw = hm.getCawangan();
                }
                
                p.setStatus("TA");
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(caw);
                p.setKodUrusan(ku);
                p.setPenyerahNama("UNIT HASIL");
                p.setFolderDokumen(fd);
                if(ku.getIdWorkflowIntegration() != null){
                    idworkflow = ku.getIdWorkflowIntegration();
                    p.setIdWorkflow(ku.getIdWorkflowIntegration());}
                else{
                    idworkflow = ku.getIdWorkflow();
                    p.setIdWorkflow(ku.getIdWorkflow());}
                if(permohonan != null){
                    p.setPermohonanSebelum(permohonan);
                    p.setKodPenyerah(kp);
//                    p.setPenyerahNama(kp.getNama());
//                    if(permohonan.getIdPenyerah() != null)
//                        p.setIdPenyerah(permohonan.getIdPenyerah());
//                    if(permohonan.getKodPenyerah() != null)
//                        p.setKodPenyerah(permohonan.getKodPenyerah());
//                    if(permohonan.getPenyerahJenisPengenalan() != null)
//                        p.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
//                    if(permohonan.getPenyerahNoPengenalan() != null)
//                        p.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
//                    if(permohonan.getPenyerahNoRujukan() != null)
//                        p.setPenyerahNoRujukan(permohonan.getPenyerahNoRujukan());
//                    if(permohonan.getPenyerahNama() != null)
//                        p.setPenyerahNama(permohonan.getPenyerahNama());
//                    if(permohonan.getPenyerahAlamat1() != null)
//                        p.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
//                    if(permohonan.getPenyerahAlamat2() != null)
//                        p.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
//                    if(permohonan.getPenyerahAlamat3() != null)
//                        p.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
//                    if(permohonan.getPenyerahAlamat4() != null)
//                        p.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
//                    if(permohonan.getPenyerahPoskod() != null)
//                        p.setPenyerahPoskod(permohonan.getPenyerahPoskod());
//                    if(permohonan.getPenyerahNegeri() != null)
//                        p.setPenyerahNegeri(permohonan.getPenyerahNegeri());
//                    if(permohonan.getPenyerahNoTelefon1() != null)
//                        p.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
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
                LOG.debug("hpa.id :"+hpa.getId()+", idHakmilik :"+hpa.getHakmilik().getIdHakmilik());
                LOG.debug("---------------------------id workflow :"+idworkflow);

                WorkFlowService.initiateTask(idworkflow,
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                
                // process tukar ganti
                ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
                String kn = conf.getProperty("kodNegeri");
                KodCawangan c = pengguna.getKodCawangan();
        
                if (!senaraiHakmilik.isEmpty()) {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                    List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kn, pengguna, senaraiHakmilik);
                    if (!senaraiPermohonanTukarGanti.isEmpty()) {
                        LOG.info("TUKAR GANTI NOT NULL "+senaraiPermohonanTukarGanti.size());
                        for (Permohonan ptg : senaraiPermohonanTukarGanti) {
                            KodUrusan urusan = ptg.getKodUrusan();
                            LOG.info("kodUrusan : "+ptg.getKodUrusan().getKod());
                            LOG.info("id workflow : "+ptg.getIdWorkflow());
                            LOG.info("Id Permohonan : "+ptg.getIdPermohonan());
                            try {
                                if (urusan.getKePTG() == 'Y') {
                                    WorkFlowService.initiateTask(urusan.getIdWorkflow(),
                                            ptg.getIdPermohonan(), c.getKod() + ",00", pengguna.getIdPengguna(),
                                            urusan.getNama());
                                } else if (urusan.getKePTG() == 'T') {
                                    WorkFlowService.initiateTask(urusan.getIdWorkflow(),
                                            ptg.getIdPermohonan(), c.getKod(), pengguna.getIdPengguna(),
                                            urusan.getNama());
                                }

//                                permohonanDAO.save(ptg);
                            } catch (Exception e) {
                                LOG.error(e);
                            }
                        }
                    }
                    System.out.println("------------------------------------------------------------------------FINISH------------------------------------------------------------------------------");
                }
                
            }
            tx.commit();
            permohonanDaftar = p;

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

    public Permohonan genWorkflowIdPermohonan(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan){
        Permohonan permohonanBaru = new Permohonan();
        try{
            generateIdPermohonanWorkflow(ku, pengguna, senaraiHakmilik, permohonan);
            permohonanBaru = permohonanDaftar;
            LOG.debug("idPermohonan :"+permohonanBaru.getIdPermohonan());
//            LOG.debug("permohonanBaru.senaraiHakmilik.size :"+permohonanBaru.getSenaraiHakmilik().size());
//            LOG.debug("permohonanBaru.senaraiHakmilik.idHakmilik :"+permohonanBaru.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        }catch(Exception ex){
            LOG.error(ex);
            ex.printStackTrace();
        }
        return permohonanBaru;
    }

    public boolean generateIdPermohonanWorkflowInternal(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, String jenisNotis) {
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();        
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
//            KodPenyerah kp = kodPenyerahDAO.findById("00"); // Kod Penyerah Dalaman. cth, Unit Hasil
            
            for (Hakmilik hm : senaraiHakmilik) {
                Date now = new Date();
                String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
                long idFolder = Long.parseLong(tarikh); // TODO create seq
                // open folder for storing submitted documents
                // only one folder for all submission
                FolderDokumen fd = new FolderDokumen();
    //            fd = permohonan.getFolderDokumen();
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
                //start utk bwk sume bukti penghantaran ltak dlm tab folder
                List<DasarTuntutanCukaiHakmilik> senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>();
                List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();
                String[] name = {"hakmilik"};
                Object[] value= {hm};
                senaraiDTCH = dtchDAO.findByEqualCriterias(name, value, null);
                String dasar = "";
                
                Permohonan p = new Permohonan();

                p.setStatus("TA");
                p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                KodPenyerah kp = kodPenyerahDAO.findById("00"); // Kod Penyerah Dalaman. cth, Unit Hasil
                p.setKodPenyerah(kp);
                p.setKodUrusan(ku);
                p.setPenyerahNama("UNIT HASIL");
                p.setFolderDokumen(fd);
                p.setInfoAudit(ia);
                permohonanDAO.save(p);
                
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                
                for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCH) {
                    if(dtch.getStatus().getKod().equals("ST")){
                        for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                            if(dtn.getDokumenBukti() != null){
                                senaraiDokumen.add(dtn.getDokumenBukti());
                                LOG.debug("DTN.dokumenBukti not null for :"+dtn.getIdNotis());
                            }else{
                                LOG.error("DTN.dokumenBukti NULL for :"+dtn.getIdNotis());
                            }
                        }
                        
                        // 10/10/2017 : save id_permohonan --> dasar_cukai_hakmilik
                        if (dtch.getHakmilik() == hm) {
                            if (jenisNotis.equals("6A")) {
                                dtch.setPerserahan6A(p);
                            }
                            if (jenisNotis.equals("8A")) {
                                dtch.setPerserahan8A(p);
                            }
                            if (jenisNotis.equals("B6A")) {
                                dtch.setPerserahanBatal6A(p);
                            }
                            if (jenisNotis.equals("B8A")) {
                                dtch.setPerserahanBatal8A(p);
                            }
                            manager.updateDTCH(dtch, pengguna);
                        }
                    }else{
                        LOG.error("DTCH.status :"+dtch.getStatus().getNama());
                    }
                }
                LOG.info("senaraiDokumen.size :"+senaraiDokumen.size());
                if(senaraiDokumen.size() > 0){
                    for (Dokumen dok : senaraiDokumen) {
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(fd);
                        kf.setDokumen(dok);
                        kf.setInfoAudit(ia);
                        kandunganFolderDAO.save(kf);
                        LOG.debug("KF.id :"+kf.getIdKandunganFolder());
                    }
                }
                //finish utk bwk sume bukti penghantaran ltak dlm tab folder
                
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
                LOG.debug("hpa.id :"+hpa.getId()+", idHakmilik :"+hpa.getHakmilik().getIdHakmilik());

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

    public String generateIdPermohonanBayaranDeposit(KodUrusan ku, Pengguna pengguna) {
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        String id= null;
        if (ku == null) {
            return "false";
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
                Date now = new Date();
                String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
                long idFolder = Long.parseLong(tarikh); // TODO create seq
                // open folder for storing submitted documents
                // only one folder for all submission
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
                List<Dokumen> senaraiDokumen = new ArrayList<Dokumen>();

                LOG.info("senaraiDokumen.size :"+senaraiDokumen.size());
                if(senaraiDokumen.size() > 0){
                    for (Dokumen dok : senaraiDokumen) {
                        KandunganFolder kf = new KandunganFolder();
                        kf.setFolder(fd);
                        kf.setDokumen(dok);
                        kf.setInfoAudit(ia);
                        kandunganFolderDAO.save(kf);
                        LOG.debug("KF.id :"+kf.getIdKandunganFolder());
                    }
                }
                //finish utk bwk sume bukti penghantaran ltak dlm tab folder

                Permohonan p = new Permohonan();

                p.setStatus("TA");
                p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
                p.setCawangan(pengguna.getKodCawangan());
                KodPenyerah kp = kodPenyerahDAO.findById("00"); // Kod Penyerah Dalaman. cth, Unit Hasil
                p.setKodPenyerah(kp);
                p.setKodUrusan(ku);
                p.setPenyerahNama("UNIT HASIL");
                p.setFolderDokumen(fd);
                p.setInfoAudit(ia);
                permohonanDAO.save(p);
                id = p.getIdPermohonan();

                WorkFlowService.initiateTask(ku.getIdWorkflow(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        ku.getNama());

//            }
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
            return "false";
        }
        return id;
    }

    public Permohonan getPermohonanDaftar() {
        return permohonanDaftar;
    }

    public void setPermohonanDaftar(Permohonan permohonanDaftar) {
        this.permohonanDaftar = permohonanDaftar;
    }

    public String getBatalPermohonan(Permohonan permohonan, String sebab, Pengguna pguna){
        String result = "";
        LOG.info("idPermohonan :"+permohonan.getIdPermohonan());
        LOG.info("sebab :"+sebab);
        Date now = new Date();
        KodKeputusan kk = kodKpsnDAO.findById("TK"); // TK = Tiada Kes / Batal
        KodStatusPermohonan ksp = kodStsMohonDAO.findById("TK"); // TK = Tidak Aktif

        try{
            //set BPEL
             List senaraiTask = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            LOG.info("senaraiTask : " + senaraiTask.size());
            if(senaraiTask.isEmpty()){
                result ="gagal";
                LOG.error("Permohonan tidak dijumpai. Id Permohonan :"+permohonan.getIdPermohonan());
            }else{
                Task task = (Task) senaraiTask.get(0);
                if (task != null) {
                    //set table permohonan
                    InfoAudit ia = permohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(now);

                    permohonan.setKeputusan(kk);
                    permohonan.setSebab(sebab);
                    permohonan.setStatus("TK");// TK = Tidak Aktif
                    permohonan.setInfoAudit(ia);
                    permohonanDAO.saveOrUpdate(permohonan);
                    //finish set table permohonan

                    LOG.info("task :"+task);
                    String taskId = task.getSystemAttributes().getTaskId();
                    WorkFlowService.withdrawTask(taskId);
                    LOG.info("Withdraw Task berjaya. Task id :"+taskId);
                    result = "berjaya";
                }else{
                    LOG.error("BPEL, task tidak dijumpai.");
                    result = "gagal";
                }
            }

        }catch(Exception ex){
            ex.printStackTrace(); // for development only
            LOG.error("(getBatalPermohonan) ex :"+ex);
            result = "gagal";
        }
        return result;
    }
}

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
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.daftar.MohonHakmilikService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.PermohonanPlotPelan;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.service.PembangunanService;
import java.util.ArrayList;
import etanah.dao.KodHakmilikDAO;
import etanah.model.PermohonanUkur;

/**
 *
 * @author w.fairul
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
    MohonHakmilikService mhservice;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;

    /*
     *  for permohonan that not start with spoc
     *  generate id permohonan and send to bpel process
     *  @param KodUrusan
     *  @param Pengguna
     *  @param hakmilikList
     */

    public boolean generateIdPerserahanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan) {

                KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && (senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD"))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }
        String[] listluas = new String[0];
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
                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
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

                }
                p.setInfoAudit(ia);
                permohonanDAO.save(p);

                String id = hm.getIdHakmilik();
                Hakmilik hmb = new Hakmilik();
                hmb = hakmilikDAO.findById(id);
                if (hmb == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
//                HakmilikPermohonan hpa = new HakmilikPermohonan();
//                hpa.setHakmilik(hm);
//                hpa.setInfoAudit(ia);
//                hpa.setPermohonan(p);
//                hakmilikPermohonanDAO.save(hpa);
                PermohonanUkur pu = devServ.findPermohonanUkurByIdPermohonan(permohonan.getIdPermohonan());
                if(ku.getKod().equals("HKPS") || ku.getKod().equals("HTSPS")){
                    hmb.setKodHakmilik(pu.getKodHakmilik());
//                        System.out.println("hkps test:" + hmb.getKodHakmilik().getKod());
//                        if(pu.getPerincianBorang5b().equals("Y")){
//                            hmb.setKodHakmilik(kodHakmilikDAO.findById("GRN"));
//                        }
//                        else if(pu.getPerincianBorang5c().equals("Y")){
//                            hmb.setKodHakmilik(kodHakmilikDAO.findById("LN"));
//                        }
//                        else if(pu.getPerincianBorang5d().equals("Y")){
//                            hmb.setKodHakmilik(kodHakmilikDAO.findById("GM"));
//                        }
//                        else if(pu.getPerincianBorang5e().equals("Y")){
//                            hmb.setKodHakmilik(kodHakmilikDAO.findById("PM"));
//                        }
                }
                else{
                    System.out.println("hsps test:" + hmb.getKodHakmilik().getKod());
                    if(hm.getBandarPekanMukim().getNama().contains("MUKIM")){
                        hmb.setKodHakmilik(kodHakmilikDAO.findById("HSM"));
                    }
                    else{
                        hmb.setKodHakmilik(kodHakmilikDAO.findById("HSD"));
                    }
                    
                }

                WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama());
                Permohonan mohon = p;
                String idMohon = permohonan.getIdPermohonan();
//                String[] name = {"idMohon"};
//                Object[] value = {idMohon};
//                PermohonanPlotPelan mpp = permohonanPlotPelanDAO.findByEqualCriterias(name, value, null);
                int sum =0;
               
                List<PermohonanPlotPelan> mpp = devServ.findPermohonanPlotPelanByIdPermohonan(idMohon);
                for (PermohonanPlotPelan permohonanPlotPelan : mpp) {
                    if(permohonanPlotPelan.getPemilikan().getKod()== 'H'){
                        sum = sum + permohonanPlotPelan.getBilanganPlot();
                        listluas = new String[sum];
                        for(int i=0; i < sum; i++){                      
                            listluas[i] = permohonanPlotPelan.getLuas().toString();
                        }
                    }
                }

                List<HakmilikPermohonan> hmp = permohonan.getSenaraiHakmilik();
                LOG.info(sum);
                mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hmb, ia, mohon, pengguna, sum, null, listluas);
//                mhservice.janaHakmilikBaruFromHakmilikTerlibat(hmp, hm, ia, mohon, pengguna, sum, String.valueOf(hm.getBandarPekanMukim()));

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

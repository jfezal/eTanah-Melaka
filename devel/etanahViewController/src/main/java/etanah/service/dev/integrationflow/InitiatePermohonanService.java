/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dev.integrationflow;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.PembangunanService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
public class InitiatePermohonanService {

    private static final Logger LOG = Logger.getLogger(InitiatePermohonanService.class);
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private GeneratorIdPerserahan idGenerator;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
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

    public String generateIdPerserahan(KodUrusan ku, KodCawangan caw, Pengguna pengguna, Permohonan permohonan, String stageId) {
        String error = "";
        LOG.info(ku.getNama());
        LOG.info(permohonan.getFolderDokumen());
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return "Kod Urusan null";
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
//        long idFolder = Long.parseLong(tarikh); // TODO create seq
            Permohonan p = new Permohonan();

        try {

            FolderDokumen fd = new FolderDokumen();

            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
//            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            KodCaraPermohonan kcp = new KodCaraPermohonan();
            kcp = pembangunanServ.findKodCaraPermohonan("UD");

            KodCawanganJabatan kodCawanganJabatan = (KodCawanganJabatan) pembangunanServ.findAlamat(caw.getKod());

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
            System.out.println("-->Stage Name : " + stageId);
            p.setIdStagePermohonanSebelum(stageId);
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            LOG.info("EROROROROROR" + error);

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
            throw new RuntimeException(t);
        }

        return p.getIdPermohonan();
    }
}

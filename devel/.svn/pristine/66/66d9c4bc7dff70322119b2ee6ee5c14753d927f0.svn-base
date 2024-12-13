/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.PenilaianDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.AmbilPampasan;
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
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.PengambilanService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mazurahayati.yusop
 */
public class PermohonanBaruLelongService {

    private static final Logger LOG = Logger.getLogger(PermohonanBaruLelongService.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PenilaianDAO penilaianDAO;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanRujukanLuarService luarService;
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
     * for permohonan that not start with spoc generate id permohonan and send
     * to bpel process @param KodUrusan @param Pengguna @param hakmilikList
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
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());
            }
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            LOG.error(t);
            return false;
        }
        return true;
    }

    public Permohonan generateIdPermohonanWorkflowGetIdMohon(KodUrusan ku, Pengguna pengguna,
            List<Hakmilik> senaraiHakmilik, Permohonan permohonan, FolderDokumen folder, List<Lelongan> listLEL) {
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
               // fd = folder;
                
                //edit folder id by syazwan
                fd.setTajuk("TEST_" + tarikh); // TODO
                fd.setInfoAudit(ia);
                fd.setFolderId(idFolder);
                folderDokumenDAO.save(fd);
            }

            KodPenyerah kp = kodPenyerahDAO.findById("01"); // Kod Penyerah Dalaman. cth, Unit Hasil

//            for (Hakmilik hm : senaraiHakmilik) {


            p.setStatus("TA");
//                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setPenyerahNama("UNIT LELONG");
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
            if (list.isEmpty()) {
                pasal.setIdPermohonanAsal(permohonan);
            } else {
                pasal.setIdPermohonanAsal(list.get(0).getIdPermohonanAsal());
            }
            pasal.setCawangan(caw);
            lelongService.saveOrUpdate(pasal);

            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(permohonan.getIdPermohonan());
            if (!listPP.isEmpty()) {
                for (PermohonanPihak permohonanPihak : listPP) {
                    InfoAudit info = pengguna.getInfoAudit();
                    PermohonanPihak permohonanP = new PermohonanPihak();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    if (permohonanPihak.getSyerPembilang() != null) {
                        permohonanP.setSyerPembilang(permohonanPihak.getSyerPembilang());
                    }
                    if (permohonanPihak.getSyerPembilang() != null) {
                        permohonanP.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                    }
                    permohonanP.setHakmilik(permohonanPihak.getHakmilik());
                    permohonanP.setInfoAudit(info);
                    permohonanP.setPihak(permohonanPihak.getPihak());
                    permohonanP.setJenis(permohonanPihak.getJenis());
                    permohonanP.setCawangan(caw);
                    permohonanP.setPermohonan(p);
                    lelongService.saveOrUpdate(permohonanP);
                }
            }

            KodStatusLelongan kod = kodStatusLelonganDAO.findById("AP");
            for (Lelongan lelongan : listLEL) {
                List<AkuanTerimaDeposit> listATD = lelongService.findATDAll(permohonan.getIdPermohonan(), String.valueOf(lelongan.getIdLelong()));
                if (!listATD.isEmpty()) {
                    for (AkuanTerimaDeposit atd : listATD) {
                        atd.setPermohonan(p);
                        lelongService.saveOrUpdate(atd);
                    }
                }
                lelongan.setKodStatusLelongan(kod);
                lelongan.setPermohonan(p);
                lelongService.saveOrUpdate(lelongan);
            }
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
                hpa.setNomborRujukan(permohonan.getSenaraiHakmilik().get(0).getNomborRujukan());
                hakmilikPermohonanDAO.save(hpa);
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());
            }
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

//            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            LOG.error(t);
            return null;
        }
        return p;
    }

    public Permohonan generateIdPermohonanWorkflowGetIdMohonACQ(KodUrusan ku, Pengguna pengguna,
            List<Hakmilik> senaraiHakmilik, Permohonan permohonan, FolderDokumen folder) {
        KodCawangan caw = pengguna.getKodCawangan();
//        KodCawangan caw = kodCawanganDAO.findById("00");
        Permohonan p = new Permohonan();
        InfoAudit ia = new InfoAudit();
        System.out.println("ku " + ku.getKod());
        System.out.println("Permohonan 1 :" + permohonan.getIdPermohonan());


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
            System.out.println("kp " + kp.getKod());
            System.out.println("Permohonan 2 :" + permohonan.getIdPermohonan());
//            for (Hakmilik hm : senaraiHakmilik) {


            p.setStatus("TA");
//                p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setIdPermohonan(idMohonGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setPenyerahNama("UNIT PENGAMBILAN");
            p.setSebab(permohonan.getSebab());
            p.setCatatan(permohonan.getCatatan());
            p.setKeputusan(permohonan.getKeputusan());
            p.setKeputusanOleh(permohonan.getKeputusanOleh());

            p.setFolderDokumen(fd);
            if (permohonan != null) {
                if (permohonan.getPermohonanSebelum() == null) {
                    p.setPermohonanSebelum(permohonan);
                }
                if (permohonan.getPermohonanSebelum() != null) {
                    p.setPermohonanSebelum(permohonan);
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
                if (permohonan.getIdWorkflow() != null) {
                    p.setIdWorkflow(permohonan.getIdWorkflow());
                }
                if (permohonan.getUntukTahun() != null) {
                    p.setUntukTahun(permohonan.getUntukTahun());
                }
            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);
            System.out.println("kp " + kp.getKod());
            System.out.println("Permohonan 3 :" + permohonan.getIdPermohonan());
            //  added by asri


            //  System.out.println("permohonan.getIdPermohonan() : " + permohonan.getIdPermohonan());
            //System.out.println("saiz luar " + luar.size());

            System.out.println("Permohonan 3.1 :" + permohonan.getIdPermohonan());
            try {

                List<PermohonanRujukanLuar> luar = luarService.findByidPermohonanListDuplicate(permohonan.getIdPermohonan());
                if (!luar.isEmpty()) {
                    for (PermohonanRujukanLuar perRujLuar : luar) {
                        PermohonanRujukanLuar perPR = new PermohonanRujukanLuar();
                        perPR.setKodCawanganDirujuk(perRujLuar.getKodCawanganDirujuk());
                        perPR.setAgensi(perRujLuar.getAgensi());
                        perPR.setHakmilik(perRujLuar.getHakmilik());
                        perPR.setPermohonan(p);
                        InfoAudit info = pengguna.getInfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        perPR.setCawangan(perRujLuar.getCawangan());
                        perPR.setInfoAudit(info);
                        perPR.setKodRujukan(perRujLuar.getKodRujukan());
                        perPR.setNoRujukan(perRujLuar.getNoRujukan());
                        perPR.setTarikhRujukan(perRujLuar.getTarikhRujukan());
                        perPR.setTarikhDisampai(perRujLuar.getTarikhDisampai());
                        perPR.setCatatan(perRujLuar.getCatatan());
                        perPR.setNamaPenyedia(perRujLuar.getNamaPenyedia());
                        perPR.setTarikhJangkaTerima(perRujLuar.getTarikhJangkaTerima());
                        perPR.setItem(perRujLuar.getItem());
                        perPR.setTarikhLulus(perRujLuar.getTarikhLulus());
                        luarService.save(perPR);
                    }
                }
            } catch (Exception h) {
                System.out.println("error masuk rujukan luar " + h);
            }
            try {

                List<PermohonanPengambilan> mp = luarService.findByidPermohonanListDuplicatePengambilan(permohonan.getIdPermohonan());
                if (!mp.isEmpty()) {
                    for (PermohonanPengambilan perAmbil : mp) {
                        PermohonanPengambilan perPR = new PermohonanPengambilan();
                        perPR.setProjekKerajaan(perAmbil.getProjekKerajaan());
                        perPR.setPermohonan(p);
                        InfoAudit info = pengguna.getInfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        perPR.setCawangan(perAmbil.getCawangan());
                        perPR.setInfoAudit(info);
//                        perPR.setKodRujukan(perAmbil.getKodRujukan());
//                        perPR.setNoRujukan(perAmbil.getNoRujukan());
//                        perPR.setTarikhRujukan(perAmbil.getTarikhRujukan());
//                        perPR.setTarikhDisampai(perAmbil.getTarikhDisampai());
//                        perPR.setCatatan(perAmbil.getCatatan());
//                        perPR.setNamaPenyedia(perAmbil.getNamaPenyedia());
//                        perPR.setTarikhJangkaTerima(perAmbil.getTarikhJangkaTerima());
//                        perPR.setItem(perAmbil.getItem());
//                        perPR.setTarikhLulus(perAmbil.getTarikhLulus());
//                        luarService.save(perPR);
                    }
                }
            } catch (Exception h) {
                System.out.println("error masuk rujukan luar " + h);
            }

            System.out.println("kp " + kp.getKod());
            System.out.println("Permohonan 4 :" + permohonan.getIdPermohonan());
            List<PermohonanPihak> listPP = lelongService.getSenaraiPmohonPihak(permohonan.getIdPermohonan());
            if (!listPP.isEmpty()) {
                for (PermohonanPihak permohonanPihak : listPP) {
                    InfoAudit info = pengguna.getInfoAudit();
                    PermohonanPihak permohonanP = new PermohonanPihak();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    if (permohonanPihak.getSyerPembilang() != null) {
                        permohonanP.setSyerPembilang(permohonanPihak.getSyerPembilang());
                    }
                    if (permohonanPihak.getSyerPembilang() != null) {
                        permohonanP.setSyerPenyebut(permohonanPihak.getSyerPenyebut());
                    }
                    permohonanP.setHakmilik(permohonanPihak.getHakmilik());
                    permohonanP.setInfoAudit(info);
                    permohonanP.setPihak(permohonanPihak.getPihak());
                    permohonanP.setJenis(permohonanPihak.getJenis());
                    permohonanP.setCawangan(caw);
                    permohonanP.setPermohonan(p);
                    lelongService.saveOrUpdate(permohonanP);
                }
            }
            System.out.println("Permohonan 5 :" + permohonan.getIdPermohonan());
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
                hpa.setPegangan(permohonan.getSenaraiHakmilik().get(0).getPegangan());// setkan 1 sebagai desakan
                hpa.setNomborRujukan(permohonan.getSenaraiHakmilik().get(0).getNomborRujukan());
                List<HakmilikPermohonan> hakmilikPermohonanList;
                hakmilikPermohonanList = p.getPermohonanSebelum().getSenaraiHakmilik(); // kene search permohonan list berdasarkan id hakmilik(id)
                System.out.println("Permohonan 6 :" + permohonan.getIdPermohonan());

                hakmilikPermohonanDAO.save(hpa);

                for (HakmilikPermohonan h : hakmilikPermohonanList) {
                    if (hm.getIdHakmilik().equals(h.getHakmilik().getIdHakmilik())) {
                        hpa.setLuasTerlibat(h.getLuasTerlibat());
                        hpa.setKodUnitLuas(h.getKodUnitLuas());
                        // }

                        /// simpan dalam table ambil_hakmilik_bicara
                        List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
                        hakmilikPerbicaraanList = pengambilanService.getHakmilikPerbicaraanByidMHList(h.getId());
                        if (!hakmilikPerbicaraanList.isEmpty()) {
                            for (HakmilikPerbicaraan hpb : hakmilikPerbicaraanList) {
                                HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
                                hakmilikPerbicaraan.setCawangan(hpb.getCawangan());
                                hakmilikPerbicaraan.setAmaunDituntut(hpb.getAmaunDituntut());
                                hakmilikPerbicaraan.setKeputusanNilai(hpb.getKeputusanNilai());
                                InfoAudit info = pengguna.getInfoAudit();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                hakmilikPerbicaraan.setInfoAudit(info);
                                hakmilikPerbicaraan.setHakmilikPermohonan(hpa);
                                hakmilikPerbicaraan.setTarikhBicara(hpb.getTarikhBicara());
                                hakmilikPerbicaraan.setLokasiBicara(hpb.getLokasiBicara());
                                hakmilikPerbicaraan.setTempohKeteranganBertulis(hpb.getTempohKeteranganBertulis());
                                hakmilikPerbicaraan.setBatalRizab(hpb.getBatalRizab());
                                hakmilikPerbicaraan.setKawasanPBT(hpb.getKawasanPBT());
                                hakmilikPerbicaraan.setBangunan(hpb.getBangunan());
                                hakmilikPerbicaraan.setPelanPembangunan(hpb.getPelanPembangunan());
                                hakmilikPerbicaraan.setCatatan(hpb.getCatatan());
                                hakmilikPerbicaraan.setCaraPemilikan(hpb.getCaraPemilikan());
                                hakmilikPerbicaraan.setNoRujukan(hpb.getNoRujukan());
                                hakmilikPerbicaraan.setPecahSyarat(hpb.getPecahSyarat());
                                hakmilikPerbicaraan.setTarikhPecahSyarat(hpb.getTarikhPecahSyarat());
                                hakmilikPerbicaraan.setHargaPembelian(hpb.getHargaPembelian());
                                hakmilikPerbicaraan.setKodUOM(hpb.getKodUOM());
                                hakmilikPerbicaraan.setTarikhSuratPenilai(hpb.getTarikhSuratPenilai());
                                hakmilikPerbicaraan.setTarikhPemilikan(hpb.getTarikhPemilikan());
                                hakmilikPerbicaraan.setTanaman(hpb.getTanaman());
                                hakmilikPerbicaraan.setAlasanTuntutan(hpb.getAlasanTuntutan());
                                hakmilikPerbicaraan.setPemohonUlasan(hpb.getPemohonUlasan());
                                hakmilikPerbicaraan.setPenilaiNama(hpb.getPenilaiNama());
                                hakmilikPerbicaraan.setPenilaiNoRujukan(hpb.getPenilaiNoRujukan());
                                hakmilikPerbicaraan.setLokasi(hpb.getLokasi());
                                hakmilikPerbicaraan.setUjudGPPJ(hpb.getUjudGPPJ());
                                hakmilikPerbicaraan.setKomenGPPJ(hpb.getKomenGPPJ());
                                hakmilikPerbicaraan.setAkujanjiPenilai(hpb.getAkujanjiPenilai());
                                hakmilikPerbicaraan.setKeteranganGKL(hpb.getKeteranganGKL());
                                hakmilikPerbicaraan.setKeteranganLain(hpb.getKeteranganLain());
                                hakmilikPerbicaraan.setKeteranganPPP(hpb.getKeteranganPPP());
                                hakmilikPerbicaraan.setKeteranganPenuntut(hpb.getKeteranganPenuntut());
                                hakmilikPerbicaraan.setUlasanPenilai(hpb.getUlasanPenilai());
                                pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);

                                /// simpan dalam table ambil_bicara_hadir
                                List<PerbicaraanKehadiran> kehadiranList;
                                kehadiranList = pengambilanService.getAllPerbicaraanKehadiran(hpb.getIdPerbicaraan());
                                if (!kehadiranList.isEmpty()) {
                                    for (PerbicaraanKehadiran pk : kehadiranList) {
                                        PerbicaraanKehadiran perbicaraanKehadiran = new PerbicaraanKehadiran();
                                        perbicaraanKehadiran.setCawangan(pk.getCawangan());
                                        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                                        perbicaraanKehadiran.setPihak(pk.getPihak());
                                        InfoAudit infoc = pengguna.getInfoAudit();
                                        infoc.setDimasukOleh(pengguna);
                                        infoc.setTarikhMasuk(new java.util.Date());
                                        perbicaraanKehadiran.setInfoAudit(infoc);
                                        perbicaraanKehadiran.setHadir(pk.getHadir());
                                        perbicaraanKehadiran.setNama(pk.getNama());
                                        perbicaraanKehadiran.setNoPengenalan(pk.getNoPengenalan());
                                        perbicaraanKehadiran.setBantahElektrik(pk.getBantahElektrik());
                                        perbicaraanKehadiran.setCatatan(pk.getCatatan());
                                        perbicaraanKehadiran.setKeterangan(pk.getKeterangan());
                                        perbicaraanKehadiran.setWakilPihak(pk.getWakilPihak());
                                        perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pk.getPermohonanPihakTidakBerkepentingan());
                                        pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                                        /// simpan dalam table ambil_hadir_nilai
                                        List<Penilaian> nilaiList;
                                        nilaiList = pengambilanService.getPenilaianByidHadirPampasan(pk.getIdKehadiran());
                                        if (!nilaiList.isEmpty()) {
                                            for (Penilaian nilai : nilaiList) {
                                                Penilaian nilaian = new Penilaian();
                                                nilaian.setCawangan(nilai.getCawangan());
                                                nilaian.setKehadiran(perbicaraanKehadiran);
                                                nilaian.setAmaun(nilai.getAmaun());
                                                InfoAudit infocd = pengguna.getInfoAudit();
                                                infoc.setDimasukOleh(pengguna);
                                                infoc.setTarikhMasuk(new java.util.Date());
                                                nilaian.setInfoAudit(infoc);
                                                nilaian.setItem(nilai.getItem());
                                                nilaian.setJenis(nilai.getJenis());
                                                nilaian.setPerUnit(nilai.getPerUnit());
                                                nilaian.setUlasan(nilai.getUlasan());
                                                penilaianDAO.save(nilaian);

                                            }
                                        }

                                        /// simpan dalam table ambil_pampasan
                                        List<AmbilPampasan> ambilPampasanList;
                                        ambilPampasanList = pengambilanService.getAmbilPampasanListByidHadir(pk.getIdKehadiran());
                                        if (!ambilPampasanList.isEmpty()) {
                                            for (AmbilPampasan pampasan : ambilPampasanList) {
                                                AmbilPampasan pampasanBaru = new AmbilPampasan();
                                                pampasanBaru.setPerbicaraanKehadiran(perbicaraanKehadiran);
                                                pampasanBaru.setDokDiambil(pampasan.getDokDiambil());
                                                pampasanBaru.setJumTerimaPampasan(pampasan.getJumTerimaPampasan());
                                                InfoAudit infocd = pengguna.getInfoAudit();
                                                infoc.setDimasukOleh(pengguna);
                                                infoc.setTarikhMasuk(new java.util.Date());
                                                pampasanBaru.setInfoAudit(infoc);
                                                pampasanBaru.setKodBank(pampasan.getKodBank());
                                                pampasanBaru.setKodCaraBayaran(pampasan.getKodCaraBayaran());
                                                pampasanBaru.setTarikhDok(pampasan.getTarikhDok());
                                                pampasanBaru.setNoDok(pampasan.getNoDok());
                                                ambilPampasanDAO.save(pampasanBaru);

                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }

                }
                hakmilikPermohonanDAO.saveOrUpdate(hpa);
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());
            }


            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());


//            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            LOG.error("Error buat id permohonan baru" + t);
            return null;
        }
        return p;
    }

    public Permohonan generateIdPermohonanWorkflow1(KodUrusan ku, Pengguna pengguna, List<Hakmilik> senaraiHakmilik, Permohonan permohonan, FolderDokumen folder) {
        Permohonan p = new Permohonan();
        KodCawangan caw = pengguna.getKodCawangan();
//        KodCawangan caw = kodCawanganDAO.findById("00");
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
                LOG.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());
            }
            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            LOG.error(t);
            return null;
        }
        return p;
    }
}

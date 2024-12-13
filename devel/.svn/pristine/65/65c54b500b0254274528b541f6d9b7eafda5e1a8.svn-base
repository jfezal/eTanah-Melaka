/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import com.Ostermiller.util.Base64;
import com.google.inject.Injector;
import etanah.Configuration;
import etanah.dao.AgensiKutipanDokumenDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodAgensiKutipanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.model.AgensiKutipanDokumen;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodAgensiKutipan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.PortalPengguna;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etanahContextListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fikri
 */
public class IntegrasiKutipanImp implements IntegrasiKutipan {

    private static Injector injector = etanahContextListener.getInjector();
    KutipanAgensiAction action = injector.getInstance(KutipanAgensiAction.class);
    KutipanAgensiService service = injector.getInstance(KutipanAgensiService.class);
    Configuration conf = injector.getInstance(Configuration.class);
    KodDokumenDAO kodDokumenDAO = injector.getInstance(KodDokumenDAO.class);
    KodKlasifikasiDAO kodKlasifikasiDAO = injector.getInstance(KodKlasifikasiDAO.class);
    DokumenDAO dokumenDAO = injector.getInstance(DokumenDAO.class);
    PenggunaDAO pgunaDAO = injector.getInstance(PenggunaDAO.class);
    AgensiKutipanDokumenDAO agensiKutipanDokumenDAO = injector.getInstance(AgensiKutipanDokumenDAO.class);
    KodAgensiKutipanDAO kodAgensiKutipanDAO = injector.getInstance(KodAgensiKutipanDAO.class);
    PortalPenggunaDAO portalPenggunaDAO = injector.getInstance(PortalPenggunaDAO.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String uploadKutipanAgensi(String convertBacktoNormalString, String filename, String kodAgensi) {
        //byte[] bytes = new byte[1024];
        byte[] bytes = Base64.decodeToBytes(convertBacktoNormalString);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        if (bytes == null) {
            return "Fail Tiada!!";
        }

        action.setKodAgensi(kodAgensi);
        action.setFileName(filename);
        if (action.checkExistingFile()) {
            return "Fail Telah Wujud!!";
        } else {
            try {
//                InputStream is = new FileInputStream(file);
                Scanner scanner = new Scanner(in).useDelimiter("\n");

                if (!action.processFile(scanner)) {
                    if (StringUtils.isNotBlank(action.sb_error.toString())) {
                        return action.sb_error.toString();
                    } else {
                        return "Terdapat ralat pada sistem. Sila hubungi IT admin anda.";
                    }
                }

            } catch (Exception ex) {
                return ex.getMessage();
            }

        }

        return "Upload Berjaya.";
    }

    @Override
    public List<AgensiView> sejarahTransaksi(String kodAgensi) {
        return action.sejarahTransaksi(kodAgensi);
    }

    @Override
    public LoginInfo login(String idPengguna, String password) {
        PortalPengguna pp = service.getPenggunaPortal(idPengguna);
        LoginInfo info = null;
        if (pp != null) {
            info = new LoginInfo();
            if (pp.getPasswd().equals(password)) {
                info.setAuth(true);
            }

            info.setEmel(pp.getEmail());
            info.setKodAgensiKutipan(pp.getKodAgensiKutipan().getKod());
            info.setNama(pp.getNama());
            info.setNamaAgensi(pp.getKodAgensiKutipan().getNama());
            info.setNoKp(pp.getNoKp());
            info.setNoTel(pp.getNoTel());
            info.setUsername(pp.getIdPguna());
            info.setPassword(pp.getPasswd());
        }
        return info;
    }

    private void checkAndMkdir(File dir) {
        if (dir != null && !dir.exists()) {
            Deque<File> makeDir = new ArrayDeque<File>();
            makeDir.push(dir);
            File last = dir.getParentFile();
            while (true) {
                if (!last.exists()) {
                    makeDir.push(last);
                    last = last.getParentFile();
                } else {
                    break;
                }
            }

            // create dirs
            File d = null;
            while (!makeDir.isEmpty()) {
                d = makeDir.pop();
                //logger.debug("Creating dir - " + d.getAbsolutePath());
                d.mkdir();
                d.setExecutable(true, false);
                d.setReadable(true, false);
            }
        }
    }

    @Override
    public String uploadFileKutipan(String convertBacktoNormalString, String filename, String kodAgensi, String koddokumen, String format, String catatan) {
        String s = "";
        File file = new File(conf.getKutipanAgensiPath() + File.separator + kodAgensi + File.separator + koddokumen);
        checkAndMkdir(file);
        FileUtil fileUtil = new FileUtil(conf.getKutipanAgensiPath() + File.separator + kodAgensi + File.separator + koddokumen);
        Dokumen doc = new Dokumen();
        KodDokumen kd = new KodDokumen();
        KodAgensiKutipan kodAgensiKutipan = kodAgensiKutipanDAO.findById(kodAgensi);
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        kd = kodDokumenDAO.findById(koddokumen);
        Pengguna pguna = pgunaDAO.findById("portal");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        doc.setTajuk(kd.getNama() + "-" + kodAgensi);
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);
        updatePathDokumen(kodAgensi + File.separator + koddokumen + File.separator + filename, doc, format);
        AgensiKutipanDokumen agd = new AgensiKutipanDokumen();
        agd.setIdDokumen(doc);
        agd.setKodAgensiKutipan(kodAgensiKutipan);
        agd.setKodDokumen(kd);
        agd.setInfoAudit(ia);
        agd.setCatatan(catatan);
        updateAgensiDokumen(agd);
        byte[] bytes = Base64.decodeToBytes(convertBacktoNormalString);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        if (bytes == null) {
            return "Fail Tiada!!";
        }

        try {

            fileUtil.saveFile(filename, in);
        } catch (Exception ex) {
            Logger.getLogger(IntegrasiKutipanImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();

        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    private void updateAgensiDokumen(AgensiKutipanDokumen agd) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");

        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();

        agd = agensiKutipanDokumenDAO.saveOrUpdate(agd);
        if (agd != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    @Override
    public List<DokumenInfo> senaraiDokumen(String kodAgensi) {
        List<AgensiKutipanDokumen> lkd = service.getSenaraiDokumen(kodAgensi);
        List<DokumenInfo> ldok = new ArrayList<DokumenInfo>();
        for (AgensiKutipanDokumen akd : lkd) {
            DokumenInfo dok = new DokumenInfo();
            dok.setIdAgensiKutipandok(akd.getIdAgensiKutipan());
            dok.setIdDokumen(String.valueOf(akd.getIdDokumen().getIdDokumen()));
            dok.setKodAgensi(akd.getKodAgensiKutipan().getKod());
            dok.setKodDokumen(akd.getKodDokumen().getKod());
            dok.setNamaDokumen(akd.getKodDokumen().getNama());
            dok.setCatatan(akd.getCatatan());
            dok.setTarikh(sdf.format(akd.getInfoAudit().getTarikhMasuk()));
            try {
                dok.setNamaFail(service.getNamaFail(akd.getIdDokumen().getNamaFizikal()).getName());
                dok.setSaiz(service.getNamaFail(akd.getIdDokumen().getNamaFizikal()).length());
            } catch (Exception e) {
            }
            dok.setTajuk(akd.getIdDokumen().getTajuk());
            ldok.add(dok);
        }
        return ldok;
    }

    @Override
    public LoginInfo kemaskiniProfil(LoginInfo loginInfo) {
        PortalPengguna pp = portalPenggunaDAO.findById(loginInfo.getUsername());
        pp.setEmail(loginInfo.getEmel() != null ? loginInfo.getEmel() : pp.getEmail());
        pp.setNoTel(loginInfo.getNoTel() != null ? loginInfo.getNoTel() : pp.getNoTel());
        pp.setNama(loginInfo.getNama());
        pp.setNoKp(loginInfo.getNoKp());
        pp.setPasswd(loginInfo.getPassword() != null ? loginInfo.getPassword() : pp.getPasswd());
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();

        pp = portalPenggunaDAO.saveOrUpdate(pp);
        if (pp != null) {
            tx.commit();
        } else {
            tx.rollback();
        }

        return loginInfo;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.uam;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodJawatanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.KonfigurasiSistemDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanPenggunaDAO;
import etanah.dao.PermohonanPenggunaDitolakDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.ldap.LDAPManager;
import etanah.model.AuditData;
//import etanah.model.AuditDataId;
import etanah.model.AuditDataId;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodAgensiKutipan;
import etanah.model.KodJabatan;
import etanah.model.KodJawatan;
import etanah.model.KodLaporanEMMKN;
import etanah.model.KodPeranan;
import etanah.model.KodStatusPengguna;
import etanah.model.KodUnitJabatan;
import etanah.model.KonfigurasiSistem;
import etanah.model.LogPenggunaApplikasi;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.PenyeliaPengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPengguna;
import etanah.model.PermohonanPenggunaDitolak;
import etanah.report.ReportUtilMMKN;
import etanah.view.uam.ListAllPengguna;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import oracle.adf.share.security.model.dc.idm.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
/**
 *
 * @author solahuddin
 */
public class UamService {

    private LDAPManager ldapManager;
    @Inject
    private PermohonanPenggunaDAO mohonPgunaDAO;
    @Inject
    private KodJawatanDAO kodJawatanDAO;
    @Inject
    private KodJabatanDAO kodJabatanDAO;
    @Inject
    private PermohonanPenggunaDAO pPengDAO;
    @Inject
    private PenggunaDAO pengDAO;
    @Inject
    private PenggunaPerananDAO pPerananDAO;
    @Inject
    private KodPerananDAO kPerananDAO;
    @Inject
    KonfigurasiSistemDAO konfigurasiSistemDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KodStatusPenggunaDAO kodStatusPenggunaDAO;
    @Inject
    PermohonanPenggunaDitolakDAO permohonanPenggunaDitolakDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ReportUtilMMKN reportUtilMMKN;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    private static final Logger SYSLOG = etanah.SYSLOG.getLogger();
    private String kodJabatan;
    KonfigurasiSistem confSys;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Transactional
    public void updatePP(PermohonanPengguna pp) {
        mohonPgunaDAO.saveOrUpdate(pp);
    }

    public List<LogPenggunaApplikasi> findLogApp(String idPguna) {
        String query = "select p from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna and rownum=1 order by p.idPgunaAppLog desc";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idPguna", idPguna);
        SYSLOG.info("CHECK PASSWORD admin...");
        return q.list();
    }

    public List<PermohonanPengguna> checkPasswordAdmin(String idPguna, String password) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna p where p.password =:password and p.idPengguna =:idPguna");
        q.setString("password", password);
        q.setString("idPguna", idPguna);
        SYSLOG.info("CHECK PASSWORD admin...");
        return q.list();
    }

    public List<PermohonanPengguna> getSenaraiPengesahanAdmin(String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna p where p.status.kod ='SH' and p.kodCawangan.kod = :kodCaw");
        q.setString("kodCaw", kodCaw);
        SYSLOG.info("Listing mohonpgguna untuk pengesahan admin...");
        return q.list();
    }

    public Object getnamaJawatan(String namaJawatan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodJawatan p where p.kod =:nJawatan");
        q.setString("nJawatan", namaJawatan);
        return q.uniqueResult();
    }

    public Object getnamaCawangan(String namaCawangan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodCawangan p where p.kod =:nCawangan");
        q.setString("nCawangan", namaCawangan);
        return q.uniqueResult();
    }

    public Object getnamaJabatan(String namaJabatan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodJabatan p where p.kod =:nJabatan");
        q.setString("nJabatan", namaJabatan);
        return q.uniqueResult();
    }

    public List<PermohonanPengguna> getSenaraiPengesahanPegawai(String kod, String kodM, String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna p where p.penyelia.idPengguna =:idPengguna "
                + "and (p.status.kod = :kod or p.status.kod = :kodM)");
        q.setString("idPengguna", idPengguna);
        q.setString("kodM", kodM);
        q.setString("kod", kod);
        SYSLOG.info("Listing mohonpgguna untuk pengesahan penyelia...");
        return q.list();
    }

    public List<PermohonanPengguna> searchByIDPengguna(String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna u where u.idPengguna = :nama");
        q.setString("nama", idPengguna);
        System.out.println("sql :" + q.getQueryString());
        return q.list();
    }

    public List<PermohonanPengguna> searchByIcPengguna(String ic) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna u where u.noPengenalan = :noPengenalan");
        q.setString("noPengenalan", ic);
        System.out.println("sql :" + q.getQueryString());
        return q.list();
    }

    public boolean pengesahanKatalaluan(Pengguna pguna, String pass) throws Exception {
        SYSLOG.info("Create new user " + pguna.getIdPengguna());
        boolean check = false;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        ldapManager = new LDAPManager();
        try {

            Pengguna p = pengDAO.findById(pguna.getIdPengguna());
            InfoAudit a = new InfoAudit();
            if (p != null && p.getInfoAudit().getDimasukOleh() != null) {
                pguna.setStatus(kodStatusPenggunaDAO.findById("A"));
                changePassword(pguna, pguna, pass);

            }

            check = true;

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Create new user " + pguna.getIdPengguna() + " complete");
        return check;
    }

    public boolean newUser2(PermohonanPengguna pguna, String pass) throws Exception {
        SYSLOG.info("Create new user " + pguna.getIdPengguna());
        boolean check = false;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

//        ldapManager.getGroupsByUsername(pguna.getIdPengguna());
        ldapManager = new LDAPManager();
        try {
            if (ldapManager.findUserByUsername(pguna.getIdPengguna())) {
                PermohonanPengguna p = pPengDAO.findById(pguna.getIdPengguna());
                if (p != null) {
                    return check;
                }

                return check;

            } else {
                PermohonanPengguna p = pPengDAO.findById(pguna.getIdPengguna());
                InfoAudit a = new InfoAudit();
                KodJabatan kodJab = kodJabatanDAO.findById(pguna.getKodJabatan().getKod());
                if (p != null && p.getInfoAufit().getDimasukOleh() != null) {

                    p.setNama(pguna.getNama());
                    p.setNoPengenalan(pguna.getNoPengenalan());
                    p.setKodCawangan(pguna.getKodCawangan());
                    KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getJawatan());
                    p.setJawatan(Jawatan.getNama());
                    p.setKodJawatan(Jawatan);
                    p.setKodJabatan(kodJab);
                    p.setIdKaunter(pguna.getIdKaunter());
                    p.setAlamat1(pguna.getAlamat1());
                    p.setAlamat2(pguna.getAlamat2());
                    p.setAlamat3(pguna.getAlamat3());
                    p.setAlamat4(pguna.getAlamat4());
                    p.setPoskod(pguna.getPoskod());
                    p.setNegeri(pguna.getNegeri());
                    p.setNoTelefon(pguna.getNoTelefon());
                    p.setNoTelefonBimbit(pguna.getNoTelefonBimbit());
                    p.setPenyelia(pguna.getPenyelia());
                    p.setEmail(pguna.getEmail());
                    p.setTarikhAkhirLogin(pguna.getTarikhAkhirLogin());
                    p.setPassword(pguna.getPassword());
                    p.setPerananUtama(pguna.getPerananUtama());
                    p.setTahapSekuriti(pguna.getTahapSekuriti());

                    KodStatusPengguna ksp = new KodStatusPengguna();
                    ksp.setKod("KM");
                    p.setStatus(ksp);

                    a.setDimasukOleh(p.getInfoAufit().getDimasukOleh());
                    a.setTarikhMasuk(p.getInfoAufit().getTarikhMasuk());
//                    a.setDiKemaskiniOleh(pgunaM);
                    a.setTarikhKemaskini(new Date());

                } else {
                    p = new PermohonanPengguna();
                    p = pguna;
                    SYSLOG.info("kodjabatan : " + pguna.getKodJabatan().getKod());
                    Pengguna admin = pengDAO.findById("admin");

                    a.setDimasukOleh(admin);
                    a.setTarikhMasuk(new Date());
                    p.setKodJabatan(kodJab);
                    KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getJawatan());
                    p.setJawatan(Jawatan.getNama());
                    p.setKodJawatan(Jawatan);
                    KodStatusPengguna ksp = new KodStatusPengguna();
                    ksp.setKod("BR");
                    p.setStatus(ksp);
                }

                //pguna.setInfoAudit(a);
                p.setInfoAufit(a);

                //pengDAO.saveOrUpdate(pguna);
                pPengDAO.saveOrUpdate(p);
//                ldapManager.addUser(pguna.getIdPengguna(), pguna.getNama(), pguna.getNama(), pass, pguna.getJawatan());

                tx.commit();
                check = true;
            }
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Create new user " + pguna.getIdPengguna() + " complete");
        return check;
    }

    //    for kemasKini jawatan
    public String jawatanName(String kod) {
        KodJawatan kodJ = new KodJawatan();
        kodJ = kodJawatanDAO.findById(kod);
        return kodJ.getNama();
    }

    public boolean newUser3(PermohonanPengguna pguna, String pass, Pengguna pengguna, String penyeliaString) throws Exception {
        SYSLOG.info("Create new user " + pguna.getIdPengguna());
        boolean check = false;
        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();

//        ldapManager = new LDAPManager();
        try {
            PermohonanPengguna p = pPengDAO.findById(pguna.getIdPengguna());
            InfoAudit a = new InfoAudit();
            KodJabatan kodJab = kodJabatanDAO.findById(pguna.getKodJabatan().getKod());
            KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getJawatan());

            if (p != null && p.getInfoAufit().getDimasukOleh() != null) {
                p.setNama(pguna.getNama());
                p.setNoPengenalan(pguna.getNoPengenalan());
                p.setKodCawangan(pguna.getKodCawangan());
                p.setKodJawatan(Jawatan);
                p.setKodJabatan(pguna.getKodJabatan());
                p.setKodJabatan(kodJab);
                p.setIdKaunter(pguna.getIdKaunter());
                p.setJawatan(pguna.getKodJawatan().getNama());
                p.setAlamat1(pguna.getAlamat1());
                p.setAlamat2(pguna.getAlamat2());
                p.setAlamat3(pguna.getAlamat3());
                p.setAlamat4(pguna.getAlamat4());
                p.setPoskod(pguna.getPoskod());
                p.setNegeri(pguna.getNegeri());
                p.setNoTelefon(pguna.getNoTelefon());
                p.setNoTelefonBimbit(pguna.getNoTelefonBimbit());
                if (!StringUtils.isEmpty(penyeliaString)) {
                    SYSLOG.info("newUser3 penyeliaString " + penyeliaString);
                    Pengguna penyeliaBaru = new Pengguna();
                    penyeliaBaru = pengDAO.findById(penyeliaString);
                    if (penyeliaBaru != null) {
                        p.setPenyelia(penyeliaBaru);
                    }
                }
                p.setEmail(pguna.getEmail());
                p.setTarikhAkhirLogin(pguna.getTarikhAkhirLogin());
                p.setPassword(pguna.getPassword());
                p.setPerananUtama(pguna.getPerananUtama());
                p.setIdKaunter(pguna.getIdKaunter());
                p.setTahapSekuriti(pguna.getTahapSekuriti());
                KodStatusPengguna ksp = new KodStatusPengguna();
                ksp.setKod("KM");
                p.setStatus(ksp);

                a.setDimasukOleh(pengguna);
                a.setTarikhMasuk(p.getInfoAufit().getTarikhMasuk());
                a.setTarikhKemaskini(new Date());

            } else {
                p = new PermohonanPengguna();
                p = pguna;
                Pengguna admin = pengDAO.findById("admin");
                a.setDimasukOleh(admin);
                a.setTarikhMasuk(new Date());

                KodStatusPengguna ksp = new KodStatusPengguna();
                ksp.setKod("BR");
                p.setStatus(ksp);
            }

            //pguna.setInfoAudit(a);
            p.setInfoAufit(a);

            //pengDAO.saveOrUpdate(pguna);
            simpanMohonPengguna(p);
//            pPengDAO.saveOrUpdate(p);
//                ldapManager.addUser(pguna.getIdPengguna(), pguna.getNama(), pguna.getNama(), pass, pguna.getJawatan());

//            tx.commit();
            check = true;
//            }
        } catch (Exception e) {
//            tx.rollback();
            throw e;
        }

        SYSLOG.info("Create new user " + pguna.getIdPengguna() + " complete");
        return check;
    }

    @Transactional
    public PermohonanPengguna simpanMohonPengguna(PermohonanPengguna p) {
        return mohonPgunaDAO.saveOrUpdate(p);
    }

    public boolean pengesahanUser(PermohonanPengguna pguna, String pass, Pengguna pengguna) throws Exception {
        SYSLOG.info("Create new user " + pguna.getIdPengguna());
        boolean check = false;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            PermohonanPengguna p = pPengDAO.findById(pguna.getIdPengguna());
            Pengguna peng = pengDAO.findById(pguna.getIdPengguna());
            InfoAudit a = new InfoAudit();

            if (peng != null && peng.getInfoAudit().getDimasukOleh() != null) {
                peng.setPassword(pguna.getPassword());
                a.setDimasukOleh(peng.getInfoAudit().getDimasukOleh());
                a.setTarikhMasuk(peng.getInfoAudit().getTarikhMasuk());

//                    a.setDiKemaskiniOleh(pgunaM);
                a.setTarikhKemaskini(new Date());

            } else {
                peng = new Pengguna();
                peng = pengguna;
//                a.setDimasukOleh(pgunaM);
                a.setTarikhMasuk(new Date());
            }
            peng.setInfoAudit(a);
            pengDAO.saveOrUpdate(peng);

            if (p != null && p.getInfoAufit().getDimasukOleh() != null) {
                p.setPassword(pguna.getPassword());
                a.setDimasukOleh(p.getInfoAufit().getDimasukOleh());
                a.setTarikhMasuk(p.getInfoAufit().getTarikhMasuk());

//                    a.setDiKemaskiniOleh(pgunaM);
                a.setTarikhKemaskini(new Date());

            } else {
                p = new PermohonanPengguna();
                p = pguna;
                Pengguna admin = pengDAO.findById("admin");
                a.setDimasukOleh(admin);
                a.setTarikhMasuk(new Date());

//                KodStatusPengguna ksp = new KodStatusPengguna();
//                ksp.setKod("BR");
//                p.setStatus(ksp);
            }

            //pguna.setInfoAudit(a);
            p.setInfoAufit(a);

            //pengDAO.saveOrUpdate(pguna);
            pPengDAO.saveOrUpdate(p);

            tx.commit();
            check = true;
//            }
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        SYSLOG.info("Create new user " + pguna.getIdPengguna() + " complete");
        return check;
    }
//use at @UrlBinding("/uam/new_update")

    public boolean newUser(Pengguna pguna, Pengguna pgunaM, String pass) throws Exception {
        SYSLOG.info("Create new user " + pguna.getIdPengguna());
        boolean check = false;
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        ldapManager = new LDAPManager();
        try {
            if (ldapManager.isValidUser(pguna.getIdPengguna(), pass)) {
                return check;

            } else {
                Pengguna p = pengDAO.findById(pguna.getIdPengguna());
                InfoAudit a = new InfoAudit();

                if (p != null && p.getInfoAudit().getDimasukOleh() != null) {
                    p.setPassword(pguna.getPassword());
                    p.setNama(pguna.getNama());
                    p.setNoPengenalan(pguna.getNoPengenalan());
//                    p.setJawatan(pguna.getKodJawatan().getNama());
                    p.setKodCawangan(pguna.getKodCawangan());
                    p.setTahapSekuriti(pguna.getTahapSekuriti());
                    p.setPerananUtama(pguna.getPerananUtama());
                    p.setIdKaunter(pguna.getIdKaunter());
                    p.setPenyeliaFlag(pguna.getPenyeliaFlag());
                    p.setEmail(pguna.getEmail());
//                    p.setKodJawatan(pguna.getKodJawatan());
                    if (pguna.getKodJawatan() != null) {
                        KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
                        p.setKodJawatan(Jawatan);
                    }
                    p.setJawatan(pguna.getKodJawatan().getNama());
                    p.setPelulusBayaranKurang(pguna.getPelulusBayaranKurang());
                    p.setBilCubaan(null);
                    a.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
                    a.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
                    a.setDiKemaskiniOleh(pgunaM);
                    a.setTarikhKemaskini(new Date());
                    PenggunaPeranan pp = checkPerananbyIdPguna(pguna.getIdPengguna(), pguna.getPerananUtama().getKod());
                    if (pp != null && !pp.getPeranan().getKod().equals(pguna.getPerananUtama().getKod())) {
                        pPerananDAO.delete(pp);
                    }

                } else {
                    p = new Pengguna();
                    p = pguna;
                    if (pguna.getKodJawatan() != null) {
                        KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
                        p.setKodJawatan(Jawatan);
                    }
                    p.setJawatan(pguna.getKodJawatan().getNama());
                    a.setDimasukOleh(pgunaM);
                    a.setTarikhMasuk(new Date());
                }

                //pguna.setInfoAudit(a);
                p.setInfoAudit(a);

                if (pguna.getStatus() != null) {
                    KodStatusPengguna ksp = new KodStatusPengguna();
                    ksp.setKod(pguna.getStatus().getKod()); // set user status base on choices
                    //pguna.setStatus(ksp);
                    p.setStatus(ksp);
                }

                //pengDAO.saveOrUpdate(pguna);
                pengDAO.saveOrUpdate(p);
                PenggunaPeranan pperanan = new PenggunaPeranan();
                pperanan.setPengguna(p);
                pperanan.setPeranan(p.getPerananUtama());
                pperanan.setInfoAudit(p.getInfoAudit());
                pPerananDAO.saveOrUpdate(pperanan);

                KodJawatan kodJ = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
                pguna.setKodJawatan(kodJ);
                ldapManager.addUser(pguna.getIdPengguna(), pguna.getNama(), pguna.getNama(),
                        pass, pguna.getKodJawatan().getNama());
                // ldapManager.assignGroupToUser(pperanan.getPengguna().getIdPengguna(), pperanan.getPeranan().getKumpBPEL());
                copyFromPengguna(pguna, pgunaM);

                tx.commit();
                check = true;
            }
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        SYSLOG.info("Create new user " + pguna.getIdPengguna() + " complete");
        return check;
    }

    public AuditData searchByAuditId(AuditDataId auditDataId) {
//        SYSLOG.info("Searching audit data by" + id+ " and "+nmMedan);
        String query = "select a from etanah.model.AuditData a where a.auditDataId.idAudit=:id and a.auditDataId.namaMedan =:nmMedan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", auditDataId.getIdAudit());
        q.setString("nmMedan", auditDataId.getNamaMedan());

        return (AuditData) q.uniqueResult();

    }

    public List<AuditData> searchByAuditId(Long id) {
        SYSLOG.info("Searching audit data " + id);
        String query = "select a from etanah.model.AuditData a where a.idAudit=:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);

        return q.list();

    }

    public PermohonanPengguna searchingUser2(String username) throws NamingException {
        SYSLOG.info("Searching user " + username);
        ldapManager = new LDAPManager();
        PermohonanPengguna p = (PermohonanPengguna) pPengDAO.findById(username);
        SYSLOG.info("Searching user " + username + " complete");
        return p;
    }

    public Pengguna searchingUser(String username) throws NamingException {
        SYSLOG.info("Searching user " + username);
        ldapManager = new LDAPManager();
        Pengguna p = pengDAO.findById(username);
        SYSLOG.info("Searching user " + username + " complete");
        return p;
    }

    public Pengguna searchingUser(String idPengguna, String noKp) throws Exception {
        SYSLOG.info("Searching user " + idPengguna + " with KP: " + noKp);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.idPengguna =:idPengguna and p.noPengenalan =:noKp");
        q.setString("idPengguna", idPengguna);
        q.setString("noKp", noKp);

        SYSLOG.info("Searching user " + idPengguna + "with KP: " + noKp + " complete");
        return (Pengguna) q.uniqueResult();
    }

    public Pengguna pengesahanUserEmail(String idPengguna, String email) throws Exception {
        SYSLOG.info("Searching user " + idPengguna + " with Email:: " + email);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.idPengguna =:idPengguna and p.email =:email");
        q.setString("idPengguna", idPengguna);
        q.setString("email", email);
        SYSLOG.info("Searching user " + idPengguna + "with Email:: " + email + " completed");
        return (Pengguna) q.uniqueResult();
    }

//    for search id & ic pengguna
    public Pengguna searchingUserReg(String idPengguna, String noKp) throws NamingException {
        SYSLOG.info("Searching user " + idPengguna + " with KP: " + noKp);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.idPengguna =:idPengguna and p.noPengenalan =:noKp");
        q.setString("idPengguna", idPengguna);
        q.setString("noKp", noKp);
        SYSLOG.info("Searching user " + idPengguna + "with KP: " + noKp + " complete");
        return (Pengguna) q.uniqueResult();
    }

    public Pengguna searchingUserReg(String idPengguna) throws NamingException {
        SYSLOG.info("Searching user " + idPengguna);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.idPengguna =:idPengguna");
        q.setString("idPengguna", idPengguna);
        SYSLOG.info("Searching user " + idPengguna);
        return (Pengguna) q.uniqueResult();
    }

//    for search id & ic mohonPengguna
    public PermohonanPengguna searchingUserReg2(String idPengguna, String noKp) throws NamingException {
        SYSLOG.info("Searching user " + idPengguna + " with KP: " + noKp);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna p where p.idPengguna =:idPengguna and p.noPengenalan =:noKp");
        q.setString("idPengguna", idPengguna);
        q.setString("noKp", noKp);
        SYSLOG.info("Searching user " + idPengguna + "with KP: " + noKp + " complete");
        return (PermohonanPengguna) q.uniqueResult();
    }

    public PermohonanPengguna searchingUserReg2(String idPengguna) throws NamingException {
        SYSLOG.info("Searching user " + idPengguna);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanPengguna p where p.idPengguna =:idPengguna");
        q.setString("idPengguna", idPengguna);
        SYSLOG.info("Searching user " + idPengguna);
        return (PermohonanPengguna) q.uniqueResult();
    }

    public KodPeranan searchingPeranan(String peranan) throws NamingException {
        SYSLOG.info("Searching user " + peranan);
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodPeranan u where u.nama =:nama");
        q.setString("nama", peranan);
        SYSLOG.info("Searching user " + peranan + " complete");
        return (KodPeranan) q.uniqueResult();
    }

    public List<Pengguna> searchingAllUser() throws NamingException {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.status.kod =:kod "
                + "order by p.idPengguna asc");
        q.setString("kod", "A");

        SYSLOG.info("Listing all user...");

        return q.list();
    }

    public List<Pengguna> findAll(Map<String, String[]> param) {
        String query = "SELECT a FROM etanah.model.Pengguna a, etanah.model.PenggunaPeranan ab WHERE a.idPengguna is not null ";
        // query += " AND ab.peranan.kod =:perananUtama ";
        query += " AND ab.pengguna.idPengguna = a.idPengguna ";
        if (isNotBlank(param.get("perananUtama"))) {
            //query = "SELECT a FROM etanah.model.Pengguna a, etanah.model.PenggunaPeranan ab WHERE a.idPengguna is not null";
            query += " AND ab.peranan.kod =:perananUtama ";
            // query += " AND ab.pengguna.idPengguna = :a.idPengguna ";
        }
        if (isNotBlank(param.get("kodCawangan"))) {
            query += " AND a.kodCawangan.kod =:kodCawangan ";
        }
        if (isNotBlank(param.get("kod_jab"))) {
            query += " AND a.kodJabatan.kod =:kod_jab ";
        }

        if (isNotBlank(param.get("status"))) {
            query += " AND a.status.kod =:status ";
        }
        query += "order by a.idPengguna asc";
        Query q = sessionProvider.get().createQuery(query);
        if (isNotBlank(param.get("kodCawangan"))) {
            q.setString("kodCawangan", param.get("kodCawangan")[0].trim());
        }
        if (isNotBlank(param.get("kod_jab"))) {
            q.setString("kod_jab", param.get("kod_jab")[0].trim());
        }
        if (isNotBlank(param.get("perananUtama"))) {
            q.setString("perananUtama", param.get("perananUtama")[0].trim());
        }
        if (isNotBlank(param.get("status"))) {
            q.setString("status", param.get("status")[0].trim());
        }

        PermohonanPengguna pp;
        return q.list();
    }

    public List<KodPeranan> findPeranan(Map<String, String[]> param) {
        String query = "SELECT a FROM etanah.model.KodPeranan a WHERE a.kod is not null";
        if (isNotBlank(param.get("perananUtama"))) {
            query += " AND a.kod =:perananUtama order by a.kod asc";
        }
        Query q = sessionProvider.get().createQuery(query);
        if (isNotBlank(param.get("perananUtama"))) {
            q.setString("perananUtama", param.get("perananUtama")[0].trim());
        }
        return q.list();
    }

    public List<Pengguna> allInactiveUser() throws NamingException {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.status.kod =:kod");
        q.setString("kod", "X");

        SYSLOG.info("Listing all inactive user...");

        return q.list();
    }

    public List<Pengguna> searchInactiveUser(String searchStr) throws NamingException {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Pengguna p where p.idPengguna like :searchStr "
                + "and p.status.kod = :kod");
        q.setString("searchStr", "%" + searchStr + "%");
        q.setString("kod", "X");

        SYSLOG.info("Listing all inactive user...");

        return q.list();
    }

    public boolean checkPassword(String username, String pass) throws NamingException, UserNotFoundException {
        ldapManager = new LDAPManager();
        return ldapManager.isValidUser(username, pass);
    }

    public boolean checkUser(String username) throws NamingException {
        ldapManager = new LDAPManager();
        boolean check = false;
        List<String> allUser = ldapManager.getAllUser();
        for (String p : allUser) {
            if (p.equals(username)) {
                check = true;
            }
        }
        return check;
    }

    private boolean checkPeranan(String peranan) throws NamingException {
        ldapManager = new LDAPManager();
        boolean check = false;
        List<String> allGroup = ldapManager.getAllGroup();
        for (String p : allGroup) {
            if (p.equals(peranan)) {
                check = true;
            }
        }
        SYSLOG.info("Boolean :" + check);
        return check;
    }

    public List<AuditData> findAuditMedan(Map<String, String[]> param) {
        String qBase = "SELECT a FROM etanah.model.AuditData a";
        String query = "SELECT a FROM etanah.model.AuditData a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("idAuditData.idAudit"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.auditDataId.idAudit =:idAudit";
            } else {
                query += "" + mulCond + " a.auditDataId.idAudit =:idAudit";
            }

        }
        if (isNotBlank(param.get("jadual"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.namaTable=:jadual";
            } else {
                query += "" + mulCond + " a.namaTable=:jadual";
            }
        }
        if (isNotBlank(param.get("idAuditData.namaMedan"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.auditDataId.namaMedan =:namaMedan";
            } else {
                query += "" + mulCond + " a.auditDataId.namaMedan =:namaMedan";
            }
        }
        if (isNotBlank(param.get("cawangan"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.cawangan =:cawangan";
            } else {
                query += "" + mulCond + " a.cawangan =:cawangan";
            }
        }

        if (isNotBlank(param.get("aktiviti"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.aktiviti=:aktiviti";
            } else {
                query += mulCond + " a.aktiviti=:aktiviti";
            }
        }

        String tDari = "";
        String tHingga = "";
        if (isNotBlank(param.get("tarikhDari")) && isNotBlank(param.get("tarikhHingga"))) {
            //TODO :search from date and until date
            tDari = param.get("tarikhDari")[0].trim();
            tHingga = param.get("tarikhHingga")[0].trim() + " 23:59:59";
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.masa between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            }

        } else if (isNotBlank(param.get("tarikhDari"))) {
            tDari = param.get("tarikhDari")[0].trim();
            tHingga = tDari + " 23:59:59";
            SYSLOG.debug("###If tarikhMsHingga isNotBlank###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.masa between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            }
        }

        if (isNotBlank(param.get("idPengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.pengguna=:idPengguna";
            } else {
                query += "" + mulCond + " a.pengguna=:idPengguna";
            }
        }

        query += " order by a.masa asc";

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idAuditData.idAudit"))) {
            q.setString("idAudit", param.get("idAuditData.idAudit")[0].trim());
        }
        if (isNotBlank(param.get("primary"))) {
            q.setString("primary", param.get("primary")[0].trim());
        }
        if (isNotBlank(param.get("jadual"))) {
            q.setString("jadual", param.get("jadual")[0].trim());
        }
        if (isNotBlank(param.get("idAuditData.namaMedan"))) {
            q.setString("namaMedan", param.get("idAuditData.namaMedan")[0].trim());
        }
        if (isNotBlank(param.get("cawangan"))) {
            q.setString("cawangan", param.get("cawangan")[0].trim());
        }
        if (isNotBlank(param.get("aktiviti"))) {
            q.setString("aktiviti", param.get("aktiviti")[0].trim());
        }
        if (StringUtils.isNotBlank(tDari)) {
            q.setString("tarikhDari", tDari);
        }
        if (StringUtils.isNotBlank(tHingga)) {
            q.setString("tarikhHingga", tHingga);
        }
        if (isNotBlank(param.get("idPengguna"))) {
            q.setString("idPengguna", param.get("idPengguna")[0].trim());
        }

        SYSLOG.info("SQL Query: " + query);
        SYSLOG.info("qlist.size :" + q.list().size());
        return q.list();
    }

    public List<DokumenCapaian> findAuditDok(Map<String, String[]> param) {
        String qBase = "SELECT a FROM etanah.model.DokumenCapaian a";
        String query = "SELECT a FROM etanah.model.DokumenCapaian a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("idCapai"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.idCapaian=:idCapai ";
            } else {
                query += "" + mulCond + " a.idCapaian=:idCapai ";
            }
        }

        AuditData ad = new AuditData();
        //ad.get
        if (isNotBlank(param.get("idAudit"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.idAudit =:idAudit";
            } else {
                query += "" + mulCond + " a.idAudit =:idAudit";

            }

        }

        if (isNotBlank(param.get("dok.idDokumen"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.dokumen.idDokumen=:id ";
            } else {
                query += "" + mulCond + " a.dokumen.idDokumen=: id";

            }
        }

        if (isNotBlank(param.get("jadual"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.namaTable=:jadual";
            } else {
                query += "" + mulCond + " a.namaTable=:jadual";

            }
        }
        if (isNotBlank(param.get("pengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.dimasukOleh.idPengguna=:pguna ";
            } else {
                query += "" + mulCond + " a.infoAudit.dimasukOleh.idPengguna=:pguna";
            }
        }

        if (isNotBlank(param.get("aktiviti"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.aktiviti=:aktiviti";
            } else {
                query += mulCond + " a.aktiviti=:aktiviti";

            }

        }

        if (isNotBlank(param.get("aktiviti.kod"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.aktiviti.kod=:kodAkt ";

            } else {
                query += "" + mulCond + " a.aktiviti.kod=:kodAkt ";
            }
        }
        if (isNotBlank(param.get("tarikhDari"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa>=tarikhDari";
            } else {
                query += "" + mulCond + " a.masa>=tarikhDari";

            }

        }

        if (isNotBlank(param.get("tarikhMsDari"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk>=to_date(:tarikhDari,'dd/MM/yyyy') ";

//        if (isNotBlank(param.get("tarikhHingga"))) {
//            if (query.equalsIgnoreCase(qBase)) {
//                query += "" + cond + " a.masa<=tarikhHingga";
//            } else {
//                query += "" + mulCond + " a.masa<= jadual";
//
//            }
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk>=to_date(:tarikhDari,'dd/MM/yyyy') ";
            }

        }

        if (isNotBlank(param.get("tarikhMsHingga"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy') ";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy') ";
            }

        }
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idAudit"))) {
            q.setString("idAudit", param.get("idAudit")[0].trim());

        }

        if (isNotBlank(param.get("idCapai"))) {
            q.setString("idCapai", param.get("idCapai")[0].trim());
        }
        if (isNotBlank(param.get("dok.idDokumen"))) {
            q.setString("id", param.get("dok.idDokumen")[0].trim());
        }

        if (isNotBlank(param.get("pengguna"))) {
            q.setString("pguna", param.get("pengguna")[0].trim());
        }

        if (isNotBlank(param.get("aktiviti"))) {
            q.setString("aktiviti", param.get("aktiviti")[0].trim());

        }

        if (isNotBlank(param.get("aktiviti.kod"))) {
            q.setString("kodAkt", param.get("aktiviti.kod")[0].trim());
        }

        if (isNotBlank(param.get("tarikhMsDari"))) {
            q.setString("tarikhDari", param.get("tarikhMsDari")[0].trim());
        }

        if (isNotBlank(param.get("tarikhMsHingga"))) {
            q.setString("tarikhHingga", param.get("tarikhMsHingga")[0].trim());
        }

        return q.list();

    }

    public DokumenCapaian getAuditDokDetails(Long id) {
        String query = "Select p from etanah.model.DokumenCapaian p where p.idCapaian=:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);

        SYSLOG.info(q);

        return (DokumenCapaian) q.uniqueResult();
    }

    public PermohonanPengguna viewMohonPgunaData(String id) {
        String query = "Select p from etanah.model.PermohonanPengguna p where p.idPengguna=:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", id);

        SYSLOG.info(q);

        return (PermohonanPengguna) q.uniqueResult();
    }

    public Dokumen getDokDetails(Long id) {
        String query = "Select p from etanah.model.Dokumen p where p.idDokumen=:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);

        SYSLOG.info(q);

        return (Dokumen) q.uniqueResult();
    }

    public List<PenggunaPeranan> getPenggunaPeranan(String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PenggunaPeranan u where u.pengguna.idPengguna =:idPengguna");
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public List<PenggunaPeranan> getPenggunaPerananForPerananTambahan(String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PenggunaPeranan u where u.pengguna.idPengguna =:idPengguna and u.peranan.kod "
                + "not in (select p.perananUtama.kod from Pengguna p where p.idPengguna = :idPengguna)");
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public PenggunaPeranan getPpByIdPeranan(String idPeranan, String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PenggunaPeranan u where u.peranan.kod =:idPeranan and u.pengguna.idPengguna=:idPengguna");
        q.setString("idPeranan", idPeranan);
        q.setString("idPengguna", idPengguna);
        return (PenggunaPeranan) q.uniqueResult();
    }

    public void saveGroup(String[] chk, Pengguna p, Pengguna pgunaM, List<PenggunaPeranan> listPerananCurr) throws NamingException, Exception {
        SYSLOG.info("Adding Group into user " + p.getIdPengguna());

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        ldapManager = new LDAPManager();

        try {
            // remove the user from PenggunaPeranan
            for (PenggunaPeranan ppr : listPerananCurr) {
                pPerananDAO.delete(ppr);
            }

            // remove the user groups from LDAP
            List<String> ldapGroups = ldapManager.getGroupsByUsername(p.getIdPengguna());
            if (ldapGroups.size() > 0) {
                for (String group : ldapGroups) {
                    ldapManager.removeGroupFromUser(p.getIdPengguna(), group);
                }
            }

            for (String str : chk) {
                if (str == null) {
                    continue;
                }

                PenggunaPeranan pp = (PenggunaPeranan) getPpByIdPeranan(str, p.getIdPengguna());
                KodPeranan kp = kPerananDAO.findById(str);
                InfoAudit au = new InfoAudit();

                if (pp != null) {
                    if (pp.getInfoAudit().getDimasukOleh() != null) {
                        au.setDimasukOleh(pp.getInfoAudit().getDimasukOleh());
                        au.setTarikhMasuk(pp.getInfoAudit().getTarikhMasuk());
                        au.setDiKemaskiniOleh(pgunaM);
                        au.setTarikhKemaskini(new Date());
                        pp.setInfoAudit(au);
                        pPerananDAO.saveOrUpdate(pp);
                    }
                } else {
                    pp = new PenggunaPeranan();
                    pp.setPeranan(kp);
                    pp.setPengguna(p);
                    au.setDimasukOleh(pgunaM);
                    au.setTarikhMasuk(new Date());
                    pp.setInfoAudit(au);
                    pPerananDAO.save(pp);

                    // assign to new BPEL group
                    ldapManager.assignGroupToUser(pp.getPengguna().getIdPengguna(), pp.getPeranan().getKumpBPEL());
                }
            }

            SYSLOG.info("==== Restore Peranan Utama ====");

            PenggunaPeranan pperanan = findPPeqPguna(p.getIdPengguna());
            if (pperanan != null) {
                SYSLOG.info("==== PP Wujud ====");
                pperanan.setPengguna(p);
                pperanan.setPeranan(p.getPerananUtama());
                pperanan.setInfoAudit(p.getInfoAudit());
                pPerananDAO.saveOrUpdate(pperanan);
            } else {
                SYSLOG.info("==== PP x Wujud ====");
                PenggunaPeranan pgunaPeranan = new PenggunaPeranan();
                InfoAudit k = new InfoAudit();
                k.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
                k.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
                pgunaPeranan.setPengguna(p);
                pgunaPeranan.setPeranan(p.getPerananUtama());
                pgunaPeranan.setInfoAudit(k);
                pPerananDAO.saveOrUpdate(pgunaPeranan);
            }

            // assign to new BPEL group
            ldapManager.assignGroupToUser(p.getIdPengguna(), p.getPerananUtama().getKumpBPEL());

            tx.commit();
            ldapManager.close();

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        SYSLOG.info("Adding Group into user " + p.getIdPengguna() + " complete");
    }

    public void newGroup(KodPeranan kp, Pengguna pgunaM) throws Exception {
        SYSLOG.info("Add new peranan" + kp.getNama());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            ldapManager = new LDAPManager();
            InfoAudit au = new InfoAudit();
            au.setTarikhMasuk(new Date());
            au.setDimasukOleh(pgunaM);
            kp.setInfoAudit(au);
            kp.setKod(String.valueOf(1 + getMaxVal()));
            kPerananDAO.save(kp);

            if (!checkPeranan(kp.getNama())) {
                ldapManager.addGroup(kp.getNama(), kp.getNama());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Add new peranan " + kp.getNama() + " complete");
    }

    public void updateGroup(KodPeranan kp, Pengguna pgunaM) throws Exception {
        SYSLOG.info("update new peranan" + kp.getNama());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        KodPeranan kpTemp = kPerananDAO.findById(kp.getKod());
        try {
            kpTemp.setDefaultScreen(kp.getDefaultScreen());
            kpTemp.setAktif(kp.getAktif());
            InfoAudit au = new InfoAudit();
            au.setTarikhMasuk(kpTemp.getInfoAudit().getTarikhMasuk());
            au.setDimasukOleh(kpTemp.getInfoAudit().getDimasukOleh());
            au.setTarikhKemaskini(new Date());
            au.setDiKemaskiniOleh(pgunaM);
            kp.setInfoAudit(au);
            kPerananDAO.saveOrUpdate(kpTemp);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        SYSLOG.info("update new peranan " + kp.getNama() + " complete");
    }

    private Long getMaxVal() {
        int a = 0;
        Session s = sessionProvider.get();
        // FIXME: Use Turutan to get peranan value
        Query q = s.createQuery("select max(kod) from etanah.model.KodPeranan");

        return Long.parseLong((String) q.iterate().next()) + 90;
    }

    public AuditData searchUserById(Long id) {
        //SYSLOG.info("Finding user audit by id:" + id);
        String query = "Select p FROM etanah.model.AuditData p WHERE p.auditDataId.idAudit =:id and p.auditDataId.namaMedan in ('DIKKINI')";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (AuditData) q.uniqueResult();
    }

    public Pengguna getPenggunaDetails(String id) {

        String query = "Select p from etanah.model.Pengguna p where p.idPengguna=:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", id);
        return (Pengguna) q.uniqueResult();
    }

    public void changePassword(Pengguna p, Pengguna pgunaM, String password) throws Exception {
        SYSLOG.info("Changing password user " + p.getIdPengguna());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        ldapManager = new LDAPManager();
        try {
            Pengguna newP = pengDAO.findById(p.getIdPengguna());
            InfoAudit a = new InfoAudit();
            if (newP.getInfoAudit().getDimasukOleh() != null && newP.getInfoAudit().getTarikhMasuk() != null) {
                a = newP.getInfoAudit();
                a.setDiKemaskiniOleh(pgunaM);
                a.setTarikhKemaskini(new Date());
            } else {
                a.setDimasukOleh(pgunaM);
                a.setTarikhMasuk(new Date());
            }
            //newP.setPassword(p.getPassword());
            newP.setPassword(password);
            newP.setDimasukKlaluan(pgunaM.getIdPengguna());
            SYSLOG.info("New user password is " + p.getPassword() + "so, it completed. (=.=') ");
            newP.setInfoAudit(a);
            newP.setTarikhKemaskiniKatalaluan(new Date());
            pengDAO.saveOrUpdate(newP);
            ldapManager.changePassword(p.getIdPengguna(), password);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Changing password user " + p.getIdPengguna() + " complete");
    }

//use at @UrlBinding("/uam/new_update")  
    public void update(Pengguna pguna, Pengguna pgunaM, String kata) throws Exception {
        SYSLOG.info("Updating data user " + pguna.getIdPengguna());
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        ldapManager = new LDAPManager();
        try {

            Pengguna p = pengDAO.findById(pguna.getIdPengguna());

            SYSLOG.info("==== Down Here ====");
            InfoAudit a = new InfoAudit();
            if (p.getInfoAudit().getDimasukOleh() != null) {
                a.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
                a.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
                a.setDiKemaskiniOleh(pgunaM);
                a.setTarikhKemaskini(new Date());
            } else {
                a.setDimasukOleh(pgunaM);
                a.setTarikhMasuk(new Date());
            }

            p.setInfoAudit(a);
            p.setIdKaunter(pguna.getIdKaunter());
            p.setKadKuasa(pguna.getKadKuasa());
            p.setIdPengguna(pguna.getIdPengguna());
            p.setNama(pguna.getNama());
            p.setEmail(pguna.getEmail());
            p.setNoPengenalan(pguna.getNoPengenalan());

            if (pguna.getKodJawatan() != null) {
                KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
                p.setKodJawatan(Jawatan);
            }
            p.setJawatan(p.getKodJawatan().getNama());
            p.setKodJabatan(pguna.getKodJabatan());
            p.setKodCawangan(pguna.getKodCawangan());
            p.setTahapSekuriti(pguna.getTahapSekuriti());
            p.setPerananUtama(pguna.getPerananUtama());
            p.setPenyeliaFlag(pguna.getPenyeliaFlag());
            p.setPelulusBayaranKurang(pguna.getPelulusBayaranKurang());
            p.setBilCubaan(null);
            KodStatusPengguna ksp = new KodStatusPengguna();
            ksp.setKod(pguna.getStatus().getKod());
            p.setStatus(ksp);
            p = pengDAO.saveOrUpdate(p);

            PenggunaPeranan pperanan = findPPeqPguna(pguna.getIdPengguna());
            if (pperanan != null) {
                SYSLOG.info("==== PP Wujud ====");
                pperanan.setPengguna(p);
                pperanan.setPeranan(p.getPerananUtama());
                pperanan.setInfoAudit(p.getInfoAudit());
                pPerananDAO.saveOrUpdate(pperanan);
            } else {
                SYSLOG.info("==== PP Not Wujud ====");
                PenggunaPeranan pp = new PenggunaPeranan();
                InfoAudit k = new InfoAudit();
                k.setDimasukOleh(p.getInfoAudit().getDimasukOleh());
                k.setTarikhMasuk(p.getInfoAudit().getTarikhMasuk());
                pp.setPengguna(p);
                pp.setPeranan(p.getPerananUtama());
                pp.setInfoAudit(k);
                pPerananDAO.saveOrUpdate(pp);
            }

            ldapManager.modifyUser(pguna.getIdPengguna(), pguna.getNama(), pguna.getNama(), pguna.getJawatan());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Updating data user " + pguna.getIdPengguna() + " complete");
    }

    public void deleteUser(String username) throws Exception {
        SYSLOG.info("Delete user " + username);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        ldapManager = new LDAPManager();
        try {
            Pengguna p = pengDAO.findById(username);
            KodStatusPengguna ksp = new KodStatusPengguna();
            ksp.setKod("H");
            p.setStatus(ksp);
            InfoAudit ia = p.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            pengDAO.saveOrUpdate(p);

            ldapManager.deleteUser(username);

            List<PenggunaPeranan> pp = getPenggunaPeranan(username);
            for (PenggunaPeranan ppT : pp) {
                pPerananDAO.delete(ppT);
                //ldapManager.removeGroupFromUser(ppT.getPengguna().getIdPengguna(), ppT.getPeranan().getNama());
            }
            tx.commit();

        } catch (Exception e) {
            SYSLOG.info("Can't delete user " + username);
            tx.rollback();
            throw e;
        }
        SYSLOG.info("Delete user " + username + " complete");
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public Pengguna savePengguna(Pengguna pguna) {

        return pengDAO.saveOrUpdate(pguna);
    }

    public List<AuditData> findAuditMedan2(Map<String, String[]> param, String d) {
        String qBase = "SELECT a FROM etanah.model.AuditData a";
        String query = "SELECT a FROM etanah.model.AuditData a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("jadual"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.namaTable=:jadual";
            } else {
                query += "" + mulCond + " a.namaTable=:jadual";
            }
        }
        if (isNotBlank(param.get("idAuditData.namaMedan"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.auditDataId.namaMedan =:namaMedan";
            } else {
                query += "" + mulCond + " a.auditDataId.namaMedan =:namaMedan";
            }
        }
        if (isNotBlank(param.get("cawangan"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.cawangan =:cawangan";
            } else {
                query += "" + mulCond + " a.cawangan =:cawangan";
            }
        }
        if (isNotBlank(param.get("aktiviti"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.aktiviti=:aktiviti";
            } else {
                query += mulCond + " a.aktiviti=:aktiviti";
            }
        }

        if (isNotBlank(param.get("tarikhDari"))) {
            SYSLOG.debug("###If tarikhDari isNotBlank###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.masa>=to_date(:d,'dd/MM/yyyy')";
            }
        }
        if (d != null) {
            SYSLOG.debug("###If currDate !=null###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.masa>=to_date(:d,'dd/MM/yyyy')";
            }
        }

        if (isNotBlank(param.get("tarikhHingga"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.masa<=to_date(:tarikhHingga,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.masa<=to_date(:tarikhHingga,'dd/MM/yyyy')";
            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("primary"))) {
            q.setString("primary", param.get("primary")[0].trim());
        }
        if (isNotBlank(param.get("jadual"))) {
            q.setString("jadual", param.get("jadual")[0].trim());
        }
        if (isNotBlank(param.get("idAuditData.namaMedan"))) {
            q.setString("namaMedan", param.get("idAuditData.namaMedan")[0].trim());
        }
        if (isNotBlank(param.get("cawangan"))) {
            q.setString("cawangan", param.get("cawangan")[0].trim());
        }
        if (isNotBlank(param.get("aktiviti"))) {
            q.setString("aktiviti", param.get("aktiviti")[0].trim());
        }

        if (isNotBlank(param.get("tarikhDari"))) {
            q.setString("d", d);
        }

        if (StringUtils.isNotBlank(d)) {
            q.setString("d", d);
        }

        if (isNotBlank(param.get("tarikhHingga"))) {
            q.setString("tarikhHingga", param.get("tarikhHingga")[0].trim());
        }

        SYSLOG.info("SQL Query: " + query);
        SYSLOG.info("qlist.size :" + q.list().size());
        return q.list();

    }

    public List<KandunganFolder> findFolderDokByIdFolder(Long idfolder) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KandunganFolder kf where kf.folder.folderId =:idFolder");
        q.setLong("idFolder", idfolder);
        return q.list();
    }

    public List<DokumenCapaian> findAuditDok2(Map<String, String[]> param, String d) {
        String qBase = "SELECT a FROM etanah.model.DokumenCapaian a";
        String query = "SELECT a FROM etanah.model.DokumenCapaian a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("dok.idDokumen"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.dokumen.idDokumen=:id ";
            } else {
                query += "" + mulCond + " a.dokumen.idDokumen=: id";

            }
        }

        if (isNotBlank(param.get("pengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.dimasukOleh.idPengguna=:pguna ";
            } else {
                query += "" + mulCond + " a.infoAudit.dimasukOleh.idPengguna=:pguna";
            }
        }

        if (isNotBlank(param.get("aktiviti.kod"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.aktiviti.kod=:kodAkt ";

            } else {
                query += "" + mulCond + " a.aktiviti.kod=:kodAkt ";
            }
        }

        if (isNotBlank(param.get("tarikhMsDari"))) {
            SYSLOG.debug("###If tarikhDari isNotBlank###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            }
        }
        if (d != null) {
            SYSLOG.debug("###If currDate !=null###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            }
        }

        if (isNotBlank(param.get("tarikhMsHingga"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy') ";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy') ";
            }

        }
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("dok.idDokumen"))) {
            q.setString("id", param.get("dok.idDokumen")[0].trim());
        }

        if (isNotBlank(param.get("pengguna"))) {
            q.setString("pguna", param.get("pengguna")[0].trim());
        }

        if (isNotBlank(param.get("aktiviti"))) {
            q.setString("aktiviti", param.get("aktiviti")[0].trim());

        }

        if (isNotBlank(param.get("aktiviti.kod"))) {
            q.setString("kodAkt", param.get("aktiviti.kod")[0].trim());
        }

        if (isNotBlank(param.get("tarikhMsDari"))) {
            q.setString("d", d);
        }

        if (StringUtils.isNotBlank(d)) {
            q.setString("d", d);
        }

        if (isNotBlank(param.get("tarikhMsHingga"))) {
            q.setString("tarikhHingga", param.get("tarikhMsHingga")[0].trim());
        }

        return q.list();

    }

    public List<DokumenCapaian> findAuditDok3(Map<String, String[]> param, List<Long> listDok) throws ParseException {
        String qBase = "SELECT a FROM etanah.model.DokumenCapaian a";
        String query = "SELECT a FROM etanah.model.DokumenCapaian a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("pengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.dimasukOleh.idPengguna=:pguna ";
            } else {
                query += "" + mulCond + " a.infoAudit.dimasukOleh.idPengguna=:pguna";
            }
        }

        if (query.equalsIgnoreCase(qBase)) {
            query += "" + cond + " a.dokumen.idDokumen in(:listDok)";
        } else {
            query += "" + mulCond + " a.dokumen.idDokumen in(:listDok)";
        }

        if (isNotBlank(param.get("kodDokumen"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.dokumen.kodDokumen.kod=:kodD ";
            } else {
                query += "" + mulCond + " a.dokumen.kodDokumen.kod=:kodD";
            }
        }

        String tDari = "";
        String tHingga = "";
        if (isNotBlank(param.get("tarikhMsDari")) && isNotBlank(param.get("tarikhMsHingga"))) {
            //TODO :search from date and until date
            tDari = param.get("tarikhMsDari")[0].trim();
            tHingga = param.get("tarikhMsHingga")[0].trim() + " 23:59:59";
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk between to_date(:tarikhMsDari,'dd/MM/yyyy') and to_date(:tarikhMsHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk between to_date(:tarikhMsDari,'dd/MM/yyyy') and to_date(:tarikhMsHingga,'dd/MM/yyyy hh24:mi:ss')";
            }

        } else if (isNotBlank(param.get("tarikhMsDari"))) {
            tDari = param.get("tarikhMsDari")[0].trim();
            tHingga = tDari + " 23:59:59";
            SYSLOG.debug("###If tarikhMsHingga isNotBlank###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.infoAudit.tarikhMasuk between to_date(:tarikhMsDari,'dd/MM/yyyy') and to_date(:tarikhMsHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.infoAudit.tarikhMasuk between to_date(:tarikhMsDari,'dd/MM/yyyy') and to_date(:tarikhMsHingga,'dd/MM/yyyy hh24:mi:ss')";
            }
        }

        query += " order by a.infoAudit.tarikhMasuk asc";

        Query q = sessionProvider.get().createQuery(query);

        q.setParameterList("listDok", listDok);

        if (isNotBlank(param.get("pengguna"))) {
            q.setString("pguna", param.get("pengguna")[0].trim());
        }

        if (isNotBlank(param.get("kodDokumen"))) {
            q.setString("kodD", param.get("kodDokumen")[0].trim());
        }

        if (StringUtils.isNotBlank(tDari)) {
            q.setString("tarikhMsDari", tDari);
        }

        if (StringUtils.isNotBlank(tHingga)) {
            q.setString("tarikhMsHingga", tHingga);
        }
        return q.list();
    }

    public List<LogPenggunaApplikasi> findAuditApp(Map<String, String[]> param) {
        String qBase = "SELECT a FROM etanah.model.LogPenggunaApplikasi a";
        String query = "SELECT a FROM etanah.model.LogPenggunaApplikasi a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("idPengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.idPguna =:idPengguna";
            } else {
                query += "" + mulCond + " a.idPguna =:idPengguna";
            }

        }

        String tDari = "";
        String tHingga = "";
        if (isNotBlank(param.get("tarikhDari")) && isNotBlank(param.get("tarikhHingga"))) {
            //TODO :search from date and until date
            tDari = param.get("tarikhDari")[0].trim();
            tHingga = param.get("tarikhHingga")[0].trim() + " 23:59:59";
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.tarikhMasuk between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.tarikhMasuk between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            }

        } else if (isNotBlank(param.get("tarikhDari"))) {
            tDari = param.get("tarikhDari")[0].trim();
            tHingga = tDari + " 23:59:59";
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.tarikhMasuk between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            } else {
                query += "" + mulCond + " a.tarikhMasuk between to_date(:tarikhDari,'dd/MM/yyyy') and to_date(:tarikhHingga,'dd/MM/yyyy hh24:mi:ss')";
            }
        }

        query += " order by a.tarikhMasuk asc";

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idPengguna"))) {
            q.setString("idPengguna", param.get("idPengguna")[0].trim());
        }

        if (StringUtils.isNotBlank(tDari)) {
            q.setString("tarikhDari", tDari);
        }

        if (StringUtils.isNotBlank(tHingga)) {
            q.setString("tarikhHingga", tHingga);
        }

        return q.list();
    }

    public List<LogPenggunaApplikasi> findAuditApp2(Map<String, String[]> param, String d) {
        String qBase = "SELECT a FROM etanah.model.LogPenggunaApplikasi a";
        String query = "SELECT a FROM etanah.model.LogPenggunaApplikasi a";
        String cond = " WHERE ";
        String mulCond = " AND ";

        if (isNotBlank(param.get("idPengguna"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.idPguna =:idPengguna";
            } else {
                query += "" + mulCond + " a.idPguna =:idPengguna";
            }

        }

        if (isNotBlank(param.get("tarikhDari"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            }
        }

        if (d != null) {
            SYSLOG.debug("###If currDate !=null###");
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.tarikhMasuk>=to_date(:d,'dd/MM/yyyy')";
            }
        }

        if (isNotBlank(param.get("tarikhHingga"))) {
            if (query.equalsIgnoreCase(qBase)) {
                query += "" + cond + " a.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy')";
            } else {
                query += "" + mulCond + " a.tarikhMasuk<=to_date(:tarikhHingga,'dd/MM/yyyy')";
            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idPengguna"))) {
            q.setString("idPengguna", param.get("idPengguna")[0].trim());
        }

        if (isNotBlank(param.get("tarikhDari"))) {
            q.setString("d", d);
        }

        if (StringUtils.isNotBlank(d)) {
            q.setString("d", d);
        }

        if (isNotBlank(param.get("tarikhHingga"))) {
            q.setString("tarikhHingga", param.get("tarikhHingga")[0].trim());
        }

        SYSLOG.info("SQL Query: " + query);
        SYSLOG.info("qlist.size :" + q.list().size());
        return q.list();
    }

    public void deletePenggunaPeranan(String idPengguna, String kod) {
        PenggunaPeranan penggunaPeranan = new PenggunaPeranan();
        penggunaPeranan = findPeranan(idPengguna, kod);
        deletePenggunaPeranan(penggunaPeranan);

    }

    @Transactional
    public void deletePenggunaPeranan(PenggunaPeranan p) {
        pPerananDAO.delete(p);
    }

    private PenggunaPeranan findPeranan(String idPengguna, String kod) {
        String query = "SELECT b FROM etanah.model.PenggunaPeranan b where b.pengguna.idPengguna = :idPengguna"
                + " and b.peranan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPengguna", idPengguna);
        q.setString("kod", kod);
        return (PenggunaPeranan) q.uniqueResult();

    }

    @Transactional
    public PenggunaPeranan savePenggunaPeranan(PenggunaPeranan penggunaPeranan) {
        return pPerananDAO.saveOrUpdate(penggunaPeranan);
    }

    public List<Pengguna> findByCawangan(String cawangan, String jabatan) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod =:cawangan"
                + " and p.penyeliaFlag = 'Y'"
                + " and p.kodJabatan.kod = :jabatan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("cawangan", cawangan);
        q.setString("jabatan", jabatan);
        return q.list();
    }

    public List<Pengguna> findByPenyeliaPguna(String kodJawatan, String cawangan) {
        String query = "Select pp FROM etanah.model.PenyeliaPengguna pp WHERE pp.pgguna =:kodJawatan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodJawatan", kodJawatan);
        List<PenyeliaPengguna> listPP = q.list();
        List<Pengguna> listPguna = new ArrayList<Pengguna>();
        for (PenyeliaPengguna pp : listPP) {
            String query2 = "Select p FROM etanah.model.Pengguna p WHERE p.kodJawatan.kod = :kodJawatan";
            if (pp.getPtg() == 'Y') {
                query2 += " and p.kodCawangan.kod in ('00','" + cawangan + "')";
            } else {
                query2 += " and p.kodCawangan.kod =:cawangan";
            }
            Query q2 = sessionProvider.get().createQuery(query2);
            q2.setString("kodJawatan", String.valueOf(pp.getPenyelia()));
            if (pp.getPtg() == 'Y') {
//                q2.setString("ptg", "00");
            } else {
                q2.setString("cawangan", cawangan);
            }
            listPguna = q2.list();

        }
        return listPguna;
    }

    public List<KodPeranan> findPerananByJab(String kod) {
        String query = "Select k FROM etanah.model.KodPeranan k WHERE k.kodJabatan.kod =:kod"
                + " and k.kumpBPEL is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    @Transactional
    public PermohonanPengguna mohonPengguna(PermohonanPengguna pp) {
        return mohonPgunaDAO.saveOrUpdate(pp);
    }

    public PenggunaPeranan checkPerananbyIdPguna(String idPengguna, String kod) {
        String query = "Select p FROM etanah.model.PenggunaPeranan p WHERE p.pengguna.idPengguna =:idPengguna"
                + " and p.peranan.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        q.setString("kod", kod);

        return (PenggunaPeranan) q.uniqueResult();
    }

    public PasswordExp checkExpiry(String IDPengguna) {
        PasswordExp pE = new PasswordExp();
        Pengguna peng = pengDAO.findById(IDPengguna);
        KonfigurasiSistem ks = konfigurasiSistemDAO.findById("tempoh_notis_klaluan");
        KonfigurasiSistem ksi = konfigurasiSistemDAO.findById("tempoh_klaluan");

        if (ks.isAktif() == 'Y') {
            if (peng.getTarikhKemaskiniKatalaluan() != null) {
                Calendar today = Calendar.getInstance();
                Calendar cal = Calendar.getInstance();
                cal.setTime(peng.getTarikhKemaskiniKatalaluan());
                long exp = (today.getTimeInMillis() - cal.getTimeInMillis()) / (24 * 60 * 60 * 1000);
                SYSLOG.info("current + 111" + cal.getTime() + "expexpexp" + exp);

                if (Integer.parseInt(ksi.getNilai()) <= Integer.parseInt(String.valueOf(exp))) {
                    pE.setExp(true);
                    pE.setReminder(false);

                }

                if (Integer.parseInt(ksi.getNilai()) >= Integer.parseInt(String.valueOf(exp)) && Integer.parseInt(ksi.getNilai()) - 7 <= Integer.parseInt(String.valueOf(exp))) {
                    pE.setReminder(true);
                    pE.setExp(false);
                    pE.setBil(Math.abs(Integer.parseInt(String.valueOf(exp)) - (Integer.parseInt(ksi.getNilai()))));
                }
            }
        }

        return pE;
    }

    @Transactional
    public void savePenggunaTolak(PermohonanPenggunaDitolak ppd) {
        permohonanPenggunaDitolakDAO.saveOrUpdate(ppd);
    }

    @Transactional
    public void deleteMohonPguna(PermohonanPengguna mohonPguna) {
        mohonPgunaDAO.delete(mohonPguna);
    }

    public PermohonanPengguna copyFromPengguna(Pengguna pguna, Pengguna currPengguna) throws NamingException {

        PermohonanPengguna pp = pPengDAO.findById(pguna.getIdPengguna());
        if (pp != null) {
        } else {
            pp = new PermohonanPengguna();
            pp.setIdPengguna(pguna.getIdPengguna());
        }
        InfoAudit a = new InfoAudit();
        if (pguna.getKodJabatan() != null) {
            KodJabatan kodJab = kodJabatanDAO.findById(pguna.getKodJabatan().getKod());
            pp.setKodJabatan(kodJab);
        }
        pp.setNama(pguna.getNama());
        pp.setNoPengenalan(pguna.getNoPengenalan());
        pp.setKodCawangan(pguna.getKodCawangan());
        if (pguna.getKodJawatan() != null) {
            KodJawatan Jawatan = kodJawatanDAO.findById(pguna.getKodJawatan().getKod());
            pp.setKodJawatan(Jawatan);
        }
        pp.setJawatan(pguna.getKodJawatan().getNama());
        pp.setIdKaunter(pguna.getIdKaunter());
        pp.setAlamat1(pguna.getAlamat1());
        pp.setAlamat2(pguna.getAlamat2());
        pp.setAlamat3(pguna.getAlamat3());
        pp.setAlamat4(pguna.getAlamat4());
        pp.setPoskod(pguna.getPoskod());
        pp.setNegeri(pguna.getNegeri());
        pp.setNoTelefon(pguna.getNoTelefon());
        pp.setNoTelefonBimbit(pguna.getNoTelefonBimbit());
        pp.setPenyelia(pguna.getPenyelia());
        pp.setEmail(pguna.getEmail());
        pp.setTahapSekuriti(pguna.getTahapSekuriti());
        pp.setPelulusBayaranKurang(pguna.getPelulusBayaranKurang());

//        pp.setTarikhAkhirLogin(pguna.getTarikhAkhirLogin());
        pp.setPassword(pguna.getPassword());
        KodPeranan kp = kPerananDAO.findById(pguna.getPerananUtama().getKod());
        pguna.setPerananUtama(kp);

        if (pp.getPerananUtama() != null) {

            //if (pp.getPerananUtama().getKumpBPEL().equals(pguna.getPerananUtama().getKumpBPEL())) {
            if (pp.getPerananUtama() == pguna.getPerananUtama()) {
                SYSLOG.info("Do Nothing !!");
            } else {
                pp.setPerananUtama(pguna.getPerananUtama());
                ldapManager.assignGroupToUser(pguna.getIdPengguna(), pguna.getPerananUtama().getKumpBPEL());
            }
        } else {
            pp.setPerananUtama(pguna.getPerananUtama());
            ldapManager.assignGroupToUser(pguna.getIdPengguna(), pguna.getPerananUtama().getKumpBPEL());
        }
        pp.setTahapSekuriti(pguna.getTahapSekuriti());
        pp.setBilCubaan(null);

        KodStatusPengguna ksp = new KodStatusPengguna();
        ksp.setKod("A");
        pp.setStatus(ksp);

        if (pguna.getInfoAudit() == null || pguna.getInfoAudit().getDimasukOleh() == null) {
            a.setDimasukOleh(currPengguna);
            a.setTarikhMasuk(new Date());
        } else {
            a.setDimasukOleh(pguna.getInfoAudit().getDimasukOleh());
            a.setTarikhMasuk(pguna.getInfoAudit().getTarikhMasuk());
        }

//                    a.setDiKemaskiniOleh(pgunaM);
        a.setTarikhKemaskini(new Date());

        pp.setInfoAufit(a);
        pp = mohonPengguna(pp);

        return pp;
    }

    public List<Pengguna> getPenyeliaUnit(String kod, String unit, String caw, String ptg) {
        String query = "Select pp FROM etanah.model.Pengguna pp WHERE pp.kodJabatan.kod in ("
                + " select kju.jabatan.kod from etanah.model.KodUnitJabatan kju where kju.kodUnit.kod = :unit and kju.ptg = :ptg)"
                + " and pp.kodCawangan.kod = :caw and pp.penyeliaFlag = 'Y'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kod);
        q.setString("unit", unit);
        q.setString("caw", caw);
        q.setString("ptg", ptg);

        return q.list();
    }

    public List<KodPeranan> getKodPerananByUnit(List listJab) {
        String query = "Select kp FROM etanah.model.KodPeranan kp WHERE kp.kodJabatan.kod in (:listJab) and kp.aktif = 'Y' order by kp.nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("listJab", listJab);
        return q.list();
    }

    public List<KodPeranan> getKodPerananByUnit1(List listJab, String idPengguna) {
        String query = "Select kp FROM etanah.model.KodPeranan kp WHERE kp.kodJabatan.kod in (:listJab) and kp.aktif = 'Y' "
                + "and kp.kod not in (select p.perananUtama.kod from etanah.model.Pengguna p where p.idPengguna = :idPengguna) order by kp.nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("listJab", listJab);
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public List<KodUnitJabatan> getKodJabUnit(String unit, String ptg) {
        String query = "Select kju FROM etanah.model.KodUnitJabatan kju where kju.kodUnit.id = :unit and kju.ptg = :ptg";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("ptg", ptg);
        q.setString("unit", unit);
        return q.list();
    }

    public List<Pengguna> getPenggunaUnit(List listJab, String caw) {
        String query = "Select pp FROM etanah.model.Pengguna pp WHERE pp.kodJabatan.kod in (:listJab)"
                + "and pp.kodCawangan.kod = :caw and pp.penyeliaFlag = 'Y' and pp.status.kod = 'A' order by pp.nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("listJab", listJab);
        q.setString("caw", caw);
        return q.list();
    }

    public List<KodUnitJabatan> getKodJab(String kodJabatan, String ptg) {
        String query = "Select kju FROM etanah.model.KodUnitJabatan kju where kju.jabatan.kod = :kodJabatan and kju.ptg = :ptg";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("ptg", ptg);
        q.setString("kodJabatan", kodJabatan);
        return q.list();
    }

    public KodLaporanEMMKN getKodLaporanEMMKNByUrusanNJenisLaporan(String urusan, char jenisLaporan) {
        String query = "Select kle FROM etanah.model.KodLaporanEMMKN kle where kle.kodUrusan.kod = :urusan and kle.jenisLaporan = :jenisLaporan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("urusan", urusan);
        q.setCharacter("jenisLaporan", jenisLaporan);
        return (KodLaporanEMMKN) q.uniqueResult();
    }

    public void createRisalatService(Permohonan permohonan, EMMKNService service, Pengguna pengguna, PermohonanKertas permohonanKertas) {
        byte[] reportMMKN = null;
        byte[] reportRingkasanMMKN = null;
        KodLaporanEMMKN kertasRisalat = new KodLaporanEMMKN();
        KodLaporanEMMKN kertasRingkasan = new KodLaporanEMMKN();
        SYSLOG.info("Kod Urusan :" + permohonan.getKodUrusan().getKod());
        kertasRisalat = getKodLaporanEMMKNByUrusanNJenisLaporan(permohonan.getKodUrusan().getKod(), 'K');
        kertasRingkasan = getKodLaporanEMMKNByUrusanNJenisLaporan(permohonan.getKodUrusan().getKod(), 'R');

        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{permohonan.getIdPermohonan()};

        if (kertasRisalat != null) {
            SYSLOG.info("Jana Dokumen Risalat MMKN");
            reportMMKN = reportUtilMMKN.generateReport(kertasRisalat.getNamaLaporan(), params, values, null, pengguna);
        }
        if (kertasRingkasan != null) {
            SYSLOG.info("Jana Dokumen Ringkasan MMKN");
            reportRingkasanMMKN = reportUtilMMKN.generateReport(kertasRingkasan.getNamaLaporan(), params, values, null, pengguna);
        }

        if (reportMMKN != null) {
            SYSLOG.info("Byte Array of Risalat MMKN=" + reportMMKN.toString());
            if (reportRingkasanMMKN != null) {
                SYSLOG.info("Byte Array of Ringkasan MMKN=" + reportRingkasanMMKN.toString());
//                service.addRisalat(permohonanKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), service.createFile(reportRingkasanMMKN, "Ringkasan MMKN"));
            } else {
//                service.addRisalat(permohonanKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), null);
            }
        }

    }

    public List<KodAgensi> findJTEKAgensi() {
        String query = "Select kju FROM etanah.model.KodAgensi kju where kju.kategoriAgensi.kod = 'JTK' order by kju.nama ASC";
        Query q = sessionProvider.get().createQuery(query);

        return q.list();
    }

    public List<KodAgensiKutipan> findKutipanAgensi() {
        String query = "Select kju FROM etanah.model.KodAgensiKutipan kju order by kju.nama ASC";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    @Transactional
    public void deleteAllPeranan(PenggunaPeranan pk) {
        pPerananDAO.delete(pk);
    }

    public KodAgensi findKodAgensiTerpilih(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kle FROM etanah.model.KodAgensi kle where kle.kod = :kod");
        q.setString("kod", kod);
        return (KodAgensi) q.uniqueResult();
    }

    public PenggunaPeranan findPPeqPguna(String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PenggunaPeranan u where u.pengguna.idPengguna =:idPengguna and u.peranan.kod "
                + " in (select p.perananUtama.kod from Pengguna p where p.idPengguna = :idPengguna)");
        q.setString("idPengguna", idPengguna);
        return (PenggunaPeranan) q.uniqueResult();
    }

    public List<Pengguna> searchingUserList(String idPengguna) {
     String query = "Select pp FROM etanah.model.Pengguna pp WHERE pp.idPengguna like :idPengguna";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna",  "%" + idPengguna + "%");
        return q.list(); 
    }
}

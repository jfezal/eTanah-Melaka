
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PenilaianDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermohonanPihakTidakBerkepentinganDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanMahkamahDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanPihakWakilDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodStatusMohonPihak;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.KandunganFolder;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanMahkamah;
import etanah.model.Pihak;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanPihakWakil;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import jsx3.gui.Sound;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh
 */
public class NotisPenerimaanService {

    private static final Logger LOG = Logger.getLogger(NotisPenerimaanService.class);
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    HakmilikDAO hakmilikDAO;
    PermohonanDAO permohonanDAO;
    NotisDAO notisDAO;
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    PenilaianDAO penilaianDAO;
    PermohonanKertasDAO permohonanKertasDAO;
    PermohonanPihakWakilDAO permohonanPihakWakilDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanPihakTidakBerkepentinganDAO permohonanPPTBDAO;
    @Inject
    PermohonanMahkamahDAO permohonanMahkamahDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PengambilanService pengambilanService;

    @Inject
    public NotisPenerimaanService(PermohonanDAO permohonanDAO, HakmilikDAO hakmilikDAO, HakmilikPermohonanDAO hakmilikPermohonanDAO, NotisDAO notisDAO,
            HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO, PerbicaraanKehadiranDAO perbicaraanKehadiranDAO, PenilaianDAO penilaianDAO, PermohonanKertasDAO permohonanKertasDAO,
            PermohonanPihakWakilDAO permohonanPihakWakilDAO) {
        this.permohonanDAO = permohonanDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.notisDAO = notisDAO;
        this.hakmilikPerbicaraanDAO = hakmilikPerbicaraanDAO;
        this.perbicaraanKehadiranDAO = perbicaraanKehadiranDAO;
        this.penilaianDAO = penilaianDAO;
        this.permohonanKertasDAO = permohonanKertasDAO;
        this.permohonanPihakWakilDAO = permohonanPihakWakilDAO;
    }

    @Transactional
    public void simpanPermohonanPihakWakil(PermohonanPihakWakil permohonanPihakWakil) {
        permohonanPihakWakilDAO.saveOrUpdate(permohonanPihakWakil);
    }

    @Transactional
    public void simpanBicaraHadir(PerbicaraanKehadiran pk) {
        perbicaraanKehadiranDAO.saveOrUpdate(pk);
    }

    @Transactional
    public void saveOrUpdateNotis(Notis n) {
        notisDAO.saveOrUpdate(n);
    }

    @Transactional
    public HakmilikPerbicaraan saveOrUpdateHakmilikPerbicaraan(HakmilikPerbicaraan n) {
        return hakmilikPerbicaraanDAO.saveOrUpdate(n);
    }

    @Transactional
    public PermohonanKertas saveOrUpdatePermohonanKertas(PermohonanKertas n) {
        return permohonanKertasDAO.saveOrUpdate(n);
    }

    @Transactional
    public Permohonan saveOrUpdatePermohonan(Permohonan p) {
        return permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public PermohonanPihakTidakBerkepentingan saveOrUpdatePPTB(PermohonanPihakTidakBerkepentingan n) {
        return permohonanPPTBDAO.saveOrUpdate(n);
    }

    @Transactional
    public PerbicaraanKehadiran saveOrUpdatePerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        return perbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
    }

    @Transactional
    public void saveOrUpdatePenilaian(Penilaian penilaian) {
        penilaianDAO.saveOrUpdate(penilaian);
    }

    @Transactional
    public void saveOrUpdateHakmilikPermohonan(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.saveOrUpdate(hp);
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p where p.permohonan.idPermohonan = :idPermohonan and p.item like 'Deposit 50%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public Notis getNotisByIdMP(Long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.pihak.idPermohonanPihak = :id_mohon_pihak ");
        q.setParameter("id_mohon_pihak", idMP);
        return (Notis) q.uniqueResult();
    }

//     and m.tarikhBicara is null
    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMH2(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikbicara(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.tarikhBicara is null");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikbicara2(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.tarikhBicara is not null");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikbicaraPHHLA(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikbicaraNotaSiasatan(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH ");//and m.noRujukan is not null
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public PermohonanPihakTidakBerkepentingan getHakmilikPBT(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakTidakBerkepentingan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (PermohonanPihakTidakBerkepentingan) q.uniqueResult();
    }

    public PermohonanPihakTidakBerkepentingan getPBT(long idMH, long idPermohonanPhkTdkBerptg) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakTidakBerkepentingan m WHERE m.hakmilikPermohonan.id = :idMH and m.idPermohonanPhkTdkBerptg=:idPermohonanPhkTdkBerptg");
        q.setParameter("idMH", idMH);
        q.setParameter("idPermohonanPhkTdkBerptg", idPermohonanPhkTdkBerptg);
        return (PermohonanPihakTidakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanPihakTidakBerkepentingan> getHakmilikPPTBList(long idMH) {
        String query = "SELECT m FROM etanah.model.PermohonanPihakTidakBerkepentingan m WHERE m.hakmilikPermohonan.id = :idMH ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public PermohonanPihakTidakBerkepentingan getPihakTidakPentingList(long idPermohonanPhkTdkBerptg) {
        String query = "SELECT m FROM etanah.model.PermohonanPihakTidakBerkepentingan m WHERE m.idPermohonanPhkTdkBerptg = :idPermohonanPhkTdkBerptg";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPermohonanPhkTdkBerptg", idPermohonanPhkTdkBerptg);
        return (PermohonanPihakTidakBerkepentingan) q.uniqueResult();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList(long idMH) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.catatan='Lulus'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getSenaraiKandunganList(long idPerbicaraan, String ujudGPPJ) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.idPerbicaraan = :idPerbicaraan AND m.ujudGPPJ=:ujudGPPJ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPerbicaraan", idPerbicaraan);
        q.setString("ujudGPPJ", ujudGPPJ);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanListResult(long idMH) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH and m.catatan is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPerbicaraanLulus(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.ambil.HakmilikPerbicaraan m,etanah.model.HakmilikPermohonan h WHERE m.hakmilikPermohonan.id = h.id and m.catatan='Lulus' and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikKedesakan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h WHERE h.pegangan = '1' and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikXKedesakan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h WHERE (h.pegangan = '0' or h.pegangan is null) and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanLulus(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m,etanah.model.HakmilikPermohonan h WHERE m.hakmilikPermohonan.id = h.id and m.catatan='Lulus' and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanTangguh(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m,etanah.model.HakmilikPermohonan h WHERE m.hakmilikPermohonan.id = h.id and m.catatan='Tangguh' and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanPertikai(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m,etanah.model.HakmilikPermohonan h WHERE m.hakmilikPermohonan.id = h.id and m.catatan='Pertikai' and h.permohonan.idPermohonan=:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pengguna> getPengguna() {
        String query = "SELECT b FROM etanah.model.Pengguna b where b.perananUtama = '223' or b.perananUtama='53'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> getPenggunaMahkamah() {
        String query = "SELECT b FROM etanah.model.Pengguna b where b.perananUtama = '63' or b.perananUtama='71' or b.perananUtama='225'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public FasaPermohonan getPenggunaKemasukan01(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.idAliran = '01Kemasukan' and b.permohonan.idPermohonan=:idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }
//     public List<Pengguna> getPengguna() {
//        String query = "SELECT b FROM etanah.model.Pengguna b where b.PerananUtama = '9'";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        return q.list();
//    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMPidBicara(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyKeterangan(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.keterangan is not null");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyKeteranganIdMPW(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.keterangan is not null and m.wakilPermohonanPihak.idWakilPermohonanPihak is not null");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMohonPidBicaraList(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMohonPidBicaraList1(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
//        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMohonPidBicara1(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
//        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranPM(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null and m.pihak.jenis.kod='PM'");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranPMAmanah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null and m.pihak.jenis.kod='PM' and m.keterangan='Amanah'");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranNOTPM(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null and m.pihak.jenis.kod !='PM'");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMPidBicaraList(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranBantah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null and m.bantahElektrik ='2'");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranTBantah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null and m.bantahElektrik ='1'");
        // q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getidMPTList(long idPermohonanPhkTdkBerptg) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg = :idPermohonanPhkTdkBerptg and m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg is NOT NULL");
        q.setParameter("idPermohonanPhkTdkBerptg", idPermohonanPhkTdkBerptg);
        return q.list();

    }

    public PerbicaraanKehadiran getidMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak");
        q.setParameter("idPermohonanPihak", idPermohonanPihak);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMPTidBicara(long idPermohonanPhkTdkBerptg, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg = :idPermohonanPhkTdkBerptg and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPhkTdkBerptg", idPermohonanPhkTdkBerptg);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranByidBicara(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranByidBicaraList(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMPT(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg is not null and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMP(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg is null and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidMPidBicaraidHadir(long idKehadiran, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.idKehadiran = :idKehadiran and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idKehadiran", idKehadiran);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidHadir(long idKehadiran) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.idKehadiran = :idKehadiran");
        q.setLong("idKehadiran", idKehadiran);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidBicara(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidBicaraPPTB(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg is not NULL");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranbyidBicara2(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.PermohonanPihak a,etanah.model.ambil.PerbicaraanKehadiran b, etanah.model.Pihak c,  etanah.model.HakmilikPihakBerkepentingan d  WHERE d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak AND a.idPermohonanPihak = b.pihak.idPermohonanPihak AND b.perbicaraan.idPerbicaraan =:idBicara and b.pihak.idPermohonanPihak is not null ");
        q.setLong("idBicara", idPerbicaraan);
//        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is not null");
//        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranMahkamah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.keterangan='Mahkamah'");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<HakmilikPermohonan> getMHMahkamah(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT mh FROM etanah.model.ambil.PerbicaraanKehadiran m,etanah.model.ambil.HakmilikPerbicaraan hp,etanah.model.HakmilikPermohonan mh where "
                + "m.keterangan='Mahkamah' "
                + "and mh.id=hp.hakmilikPermohonan.id "
                + "and hp.idPerbicaraan=m.perbicaraan.idPerbicaraan "
                + "and mh.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public HakmilikPermohonan getMHMahkamah1(String idPermohonan, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT mh FROM etanah.model.ambil.PerbicaraanKehadiran m,etanah.model.ambil.HakmilikPerbicaraan hp,etanah.model.HakmilikPermohonan mh where "
                + "m.keterangan='Mahkamah' "
                + "and mh.id=hp.hakmilikPermohonan.id "
                + "and hp.idPerbicaraan=m.perbicaraan.idPerbicaraan "
                + "and mh.hakmilik.idHakmilik = :idHakmilik "
                + "and mh.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();

    }

    public List<HakmilikPermohonan> getMHAmanah(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct mh FROM etanah.model.ambil.PerbicaraanKehadiran m,etanah.model.ambil.HakmilikPerbicaraan hp,etanah.model.HakmilikPermohonan mh where "
                + "m.keterangan='Amanah' "
                + "and mh.id=hp.hakmilikPermohonan.id "
                + "and hp.idPerbicaraan=m.perbicaraan.idPerbicaraan "
                + "and mh.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPermohonan> getMHTerima(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT mh FROM etanah.model.ambil.PerbicaraanKehadiran m,etanah.model.ambil.HakmilikPerbicaraan hp,etanah.model.HakmilikPermohonan mh where "
                + "m.keterangan='Terima' "
                + "and mh.id=hp.hakmilikPermohonan.id "
                + "and hp.idPerbicaraan=m.perbicaraan.idPerbicaraan "
                + "and mh.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranAmanah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.keterangan='Amanah'");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getbicarahadir(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.PermohonanPihak a,etanah.model.ambil.PerbicaraanKehadiran b, etanah.model.Pihak c,  etanah.model.HakmilikPihakBerkepentingan d  WHERE d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak AND a.idPermohonanPihak = b.pihak.idPermohonanPihak AND b.perbicaraan.idPerbicaraan =:idBicara and b.pihak.idPermohonanPihak is null ");
        q.setLong("idBicara", idPerbicaraan);
//        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m,etanah.model.ambil.PermohonanPihak mp where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is null");
//        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<PerbicaraanKehadiran> getbicarahadirIdMPW(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.pihak.idPermohonanPihak is null and m.wakilPermohonanPihak.idWakilPermohonanPihak is not null");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();

    }

    public List<Penilaian> getPenilaianTanahByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = 'T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<Penilaian> getPenilaianPinjamanByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = 'P'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<Penilaian> getPenilaianByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<Penilaian> getPenilaianBngnByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = 'B'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public Penilaian getPenilaianBngnByidNilaianP(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = 'P'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return (Penilaian) q.uniqueResult();
    }

    public Penilaian getPenilaianBngnByidNilaianB(long idPenilaian) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.idPenilaian = :idPenilaian and b.jenis = 'B'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPenilaian", idPenilaian);
        return (Penilaian) q.uniqueResult();
    }

    public Penilaian getPenilaianBngnByidNilaian(long idPenilaian) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.idPenilaian = :idPenilaian";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPenilaian", idPenilaian);
        return (Penilaian) q.uniqueResult();
    }

    public Penilaian getPenilaianLainByidNilaianL(long idPenilaian) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.idPenilaian = :idPenilaian and b.jenis = 'L'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPenilaian", idPenilaian);
        return (Penilaian) q.uniqueResult();
    }

    public List<Penilaian> getPenilaianLainByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = 'L'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public Penilaian getPenilaianByidHadirJenis(long idKehadiran, char jenis) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran and b.jenis = :jenis";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        q.setParameter("jenis", jenis);
        return (Penilaian) q.uniqueResult();
    }

    public List<Penilaian> getPenilaianByidHadir1(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

//    public Penilaian getPenilaianByidHadir1(long idKehadiran) {
//        String query = "SELECT b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setLong("idKehadiran", idKehadiran);
//        return (Penilaian) q.uniqueResult();
//    }
//    public String sumPenilaian(long idKehadiran) {
//        String query = "SELECT SUM(amaun) FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setLong("idKehadiran", idKehadiran);
////        q.executeUpdate();
//        LOG.info("::q.toString():: " + q.toString());
//        return q.toString();
//    }
    public List<Penilaian> sumPenilaian(long idKehadiran) {
        String query = "Select b FROM etanah.model.ambil.Penilaian b where b.kehadiran.idKehadiran = :idKehadiran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    @Transactional
    public void simpanPermohonanPihak(Permohonan permohonan, Pengguna peng) {

        String idPermohonan = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : listHp) {
            //   List<PermohonanPihak> pp = getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
            try {
                List<PermohonanPihak> pp = pengambilanService.findMohonPihak(idPermohonan, hp.getHakmilik().getIdHakmilik());
                if (pp.size() == 0) {
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {

                        PermohonanPihak newPP = new PermohonanPihak();
                        newPP.setPermohonan(permohonan);
                        newPP.setHakmilik(hakmilikPihak.getHakmilik());
                        newPP.setPihak(hakmilikPihak.getPihak());
                        newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                        newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                        newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                        newPP.setCawangan(permohonan.getCawangan());
                        newPP.setInfoAudit(ia);
//                        KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                        newPP.setJenis(findByIdKodPihak("PJK"));
                        permohonanPihakDAO.save(newPP);
                    }
                }
            } catch (Exception h) {
            }
        }

    }

    @Transactional
    public PermohonanPihak simpanMohonPihak(Permohonan permohonan, Pengguna peng, Pihak pihak) {

        String idPermohonan = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        PermohonanPihak newPP = new PermohonanPihak();
        newPP.setPermohonan(permohonan);
        newPP.setCawangan(permohonan.getCawangan());
        newPP.setPihak(pihak);
        newPP.setJenis(findByIdKodPihak("PJK"));
        newPP.setInfoAudit(ia);
        return permohonanPihakDAO.saveOrUpdate(newPP);

    }

    @Transactional
    public PerbicaraanKehadiran updateKehadiranByIdHadir(String kehadiran, String idHadir, String keputusan) {
        PerbicaraanKehadiran ppkh = new PerbicaraanKehadiran();
        System.out.println("idHadir : " + idHadir);
        System.out.println("kehadiran " + kehadiran);
        Character kehadiranPBT = kehadiran.charAt(0);
        long id_hadir = Long.parseLong(idHadir);
        System.out.println("id_hadir : " + idHadir);
        System.out.println("kehadiranPBT : " + kehadiranPBT);
        System.out.println("keputusan : " + keputusan);
        PerbicaraanKehadiran pkhd = new PerbicaraanKehadiran();
        ppkh = perbicaraanKehadiranDAO.findById(id_hadir);
        ppkh.setIdKehadiran(id_hadir);
        ppkh.setHadir(kehadiranPBT);
        ppkh.setBantahElektrik(keputusan);
//        System.out.println(permohonanPihak.getNama());
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("UPDATE etanah.model.ambil.PerbicaraanKehadiran m SET m.hadir = :kehadiran AND m.bantahElektrik = :keputusan WHERE m.pihak.idPermohonanPihak = :idMP");
//        q.setCharacter("kehadiran", kehadiranPBT);
//        q.setLong("idMP", id_mp);
//        q.setString("keputusan", keputusan);
//        int result = q.executeUpdate();
        return perbicaraanKehadiranDAO.saveOrUpdate(ppkh);
    }

    @Transactional
    public PermohonanPihak simpanMohonPemohon(PermohonanPihak pp) {

        return permohonanPihakDAO.saveOrUpdate(pp);

    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihakKodPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct m.pihak.idPihak FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihak2(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT m FROM etanah.model.PermohonanPihak m,etanah.model.HakmilikPihakBerkepentingan d WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak and d.hakmilik.idHakmilik = m.hakmilik.idHakmilik and d.aktif='Y'");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihakIdHakmilikNull(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik is null AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
//        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
//        return (PermohonanPihak) q.uniqueResult();
        return q.list();
    }

    public HakmilikPihakBerkepentingan getSenaraiHakmilikPihakBerkepentinganByIdHakmilikIdPihak(Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m WHERE m.pihak.idPihak = :id_pihak");
        q.setParameter("id_pihak", idPihak);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik ");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilik2(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        return q.list();
    }

    public KodJenisPihakBerkepentingan findByIdKodPihak(String kodPihak) {
        String query = "SELECT b FROM etanah.model.KodJenisPihakBerkepentingan b WHERE b.kod = :kodPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPihak", kodPihak);
        return (KodJenisPihakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon and m.hakmilik.idHakmilik =:id_hakmilik ");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTBByHakmilik(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakTidakBerkepentingan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return q.list();
    }

    public List<Pihak> getSenaraiTuanTanah(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT c FROM etanah.model.PermohonanPihak a,etanah.model.ambil.PerbicaraanKehadiran b, etanah.model.Pihak c,  etanah.model.HakmilikPihakBerkepentingan d  WHERE d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak AND a.idPermohonanPihak = b.pihak.idPermohonanPihak AND b.perbicaraan.idPerbicaraan =:idBicara ");
        q.setLong("idBicara", idBicara);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiTuanTanahMohonPihak(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT a FROM etanah.model.PermohonanPihak a,etanah.model.ambil.PerbicaraanKehadiran b, etanah.model.Pihak c,  etanah.model.HakmilikPihakBerkepentingan d  WHERE d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak AND a.idPermohonanPihak = b.pihak.idPermohonanPihak AND b.perbicaraan.idPerbicaraan =:idBicara ");
        q.setLong("idBicara", idBicara);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiTuanTanahMohonPihakBicara(String idHakmilik, String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT a FROM etanah.model.PermohonanPihak a, etanah.model.Pihak c,  etanah.model.HakmilikPihakBerkepentingan d  WHERE a.permohonan.idPermohonan =:idMohon and d.hakmilik.idHakmilik =:idHakmilik and d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak ");
        q.setString("idHakmilik", idHakmilik);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getSenaraiPBTDaftar(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.PermohonanPihak a,etanah.model.ambil.PerbicaraanKehadiran b, etanah.model.Pihak c, etanah.model.HakmilikPihakBerkepentingan d WHERE d.hakmilik.idHakmilik = a.hakmilik.idHakmilik and d.aktif='Y' and  a.pihak.idPihak=d.pihak.idPihak and a.pihak.idPihak = c.idPihak AND a.idPermohonanPihak = b.pihak.idPermohonanPihak AND b.perbicaraan.idPerbicaraan =:idBicara ");
        q.setLong("idBicara", idBicara);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getSenaraiPBTTidakDaftar(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.PermohonanPihakTidakBerkepentingan a,etanah.model.ambil.PerbicaraanKehadiran b WHERE a.idPermohonanPhkTdkBerptg = b.permohonanPihakTidakBerkepentingan.idPermohonanPhkTdkBerptg AND b.perbicaraan.idPerbicaraan =:idBicara ");
        q.setLong("idBicara", idBicara);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getSenaraiPBTTidakDaftar2(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.ambil.PerbicaraanKehadiran b WHERE b.perbicaraan.idPerbicaraan =:idBicara and b.nama is not null ");
        q.setLong("idBicara", idBicara);
        return q.list();
    }

    public List<KodStatusMohonPihak> getSenaraiKodStatusMohonPihak() {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KodStatusMohonPihak m ");
        return q.list();
    }

    public List<Notis> getListNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenNotis(long idFolder, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = kod");
        q.setParameter("id_folder", idFolder);
        q.setParameter("kod", kod);
        return q.list();
    }

//     public List<Dokumen> getListDokumen(long idFolder, String kod) {
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT m FROM etanah.model.Dokumen m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = kod");
//        q.setParameter("id_folder", idFolder);
//        q.setParameter("kod", kod);
//        return q.list();
//    }
    public List<Dokumen> findDokumenList(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        String query = "select x from Dokumen d,"
                + "Permohonan a,"
                + "FolderDokumen b,"
                + "KandunganFolder kf,"
                + "KodDokumen f "
                + "where a.folderDokumen.folderId = b.folderId "
                + "and b.folderId = kf.folder.folderId "
                + "and kf.dokumen.idDokumen = d.idDokumen "
                + "and d.kodDokumen.kod = kf.kodDokumen.kod "
                + "and d.kodDokumen.kod = :kod "
                + "and a.idPermohonan = :idPermohonan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//    public List<Dokumen> findDokumenlist(String idPermohonan, String idAliran) {
//        Session s = sessionProvider.get();
//        String query = "select d from IntegrasiDokumen k,"
//                + "Permohonan a,"
//                + "FolderDokumen b,"
//                + "Dokumen d,"
//                + "KandunganFolder c,"
//                + "KodDokumen f "
//                + "where a.folderDokumen.folderId = b.folderId "
//                + "and b.folderId = c.folder.folderId "
//                + "and c.dokumen.idDokumen = d.idDokumen "
//                + "and d.kodDokumen.kod = k.kodDokumen.kod "
//                + "and d.kodDokumen.kod = f.kod "
//                + "and k.diperluOleh in ('GIS','JUPEM') "
//                + "and k.dokHantar = 'Y' "
//                + "and a.idPermohonan = :idPermohonan "
//                + "and k.idAliran = :idAliran";
//
//        Query q = s.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        q.setString("idAliran", idAliran);
//        return q.list();
//    }
    public List<KandunganFolder> getListDokumenB(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'B'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'PB'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public KandunganFolder getDokumenH(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'H'");
        q.setParameter("id_folder", idFolder);
        return (KandunganFolder) q.uniqueResult();
    }

    public KandunganFolder getDokumenK(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'K'");
        q.setParameter("id_folder", idFolder);
        return (KandunganFolder) q.uniqueResult();
    }

    public List<KandunganFolder> getListDokumenEF(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'E' and m.dokumen.kodDokumen.kod='F'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenK(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'K'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenGeneral(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SPBG'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public Notis getNotisByidMPkodNotis(String idMohon, long idPermohonanPihak, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPermohonanPihak = :idPermohonanPihak AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("idPermohonanPihak", idPermohonanPihak);
        q.setParameter("kod_notis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public List<Notis> getNotisByIdMohonkodNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPermohonanPihak is null AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getNotisByIdMohonMPkodNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPermohonanPihak is not null AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public Notis getNotisByidNotisAduan(long idNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.idNotis= :idNotis");
        q.setLong("idNotis", idNotis);
        return (Notis) q.uniqueResult();
    }

    public Notis getNotisByidMPJPPH(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak = NULL AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public List<Notis> getNotisByidMPNotPemohon(String idMohon, long idPihak, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.pihak.idPihak != :idPihak AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("idPihak", idPihak);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public Notis getNotiskodNotisJPPH(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return (Notis) q.uniqueResult();

    }

    public FasaPermohonan getFasaPermohonan(String idPermohonan, String stageId) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.FasaPermohonan m WHERE m.permohonan.idPermohonan = :id_mohon AND m.idAliran = :idAliran ");
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("idAliran", stageId);
        return (FasaPermohonan) q.uniqueResult();
    }
        public List<FasaPermohonan> getFasaPermohonanList(String idPermohonan, String stageId) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.FasaPermohonan m WHERE m.permohonan.idPermohonan = :id_mohon AND m.idAliran = :idAliran ");
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("idAliran", stageId);
         return q.list();
    }

    public PermohonanPihak getMohonPihakByIdMohonIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik is null AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getMPByIdMohonIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik is not null AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getIdPihakByIdMP(long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.idPermohonanPihak = :idMP");
        q.setParameter("idMP", idMP);
        return (PermohonanPihak) q.uniqueResult();
    }
//     fd.tajuk = :id_mohon AND 

    public Dokumen getDokumenBykod(String idPermohonan, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT d FROM etanah.model.Dokumen d, etanah.model.Permohonan p, etanah.model.KandunganFolder kf WHERE p.idPermohonan = :id_mohon AND kf.folder.folderId = p.folderDokumen.folderId AND d.idDokumen = kf.dokumen.idDokumen AND d.kodDokumen.kod = :kod" +
                " and d.dalamanNilai1 is not null ");
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("kod", kod);
        return (Dokumen) q.uniqueResult();
    }

    public Dokumen getDokumenBykod2(String idPermohonan, String kod, String kod2) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT d FROM etanah.model.Dokumen d, etanah.model.FolderDokumen fd, etanah.model.KandunganFolder kf WHERE fd.tajuk = :id_mohon AND kf.folder.folderId = fd.folderId AND d.idDokumen = kf.dokumen.idDokumen AND d.kodDokumen.kod = :kod AND d.kodDokumen.kod = :kod2 ");
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("kod", kod);
        q.setParameter("kod2", kod2);
        return (Dokumen) q.uniqueResult();
    }

    public Dokumen getDokumenBykod3(String idPermohonan, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT d FROM etanah.model.Dokumen d, etanah.model.FolderDokumen fd, etanah.model.KandunganFolder kf WHERE d.dalamanNilai1 = :id_mohon AND kf.folder.folderId = fd.folderId AND d.idDokumen = kf.dokumen.idDokumen AND d.kodDokumen.kod = :kod  ");
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("kod", kod);
//        q.setParameter("kod2", kod2);
        return (Dokumen) q.uniqueResult();
    }

    public PermohonanMahkamah findPermohonanMahkamahByidMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanMahkamah m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanMahkamah) q.uniqueResult();

    }

    @Transactional
    public PermohonanMahkamah saveOrUpdateMahkamah(PermohonanMahkamah n) {
        return permohonanMahkamahDAO.saveOrUpdate(n);
    }

    public List<PermohonanMahkamah> getPermohonanMahkamahListByidMohon(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanMahkamah m, PermohonanPihak pp WHERE pp.permohonan.idPermohonan = :id_mohon AND m.permohonanPihak.idPermohonanPihak = pp.idPermohonanPihak AND m.namaPenolongKananPendaftar is not null");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public Notis getNotisBykodNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak = NULL AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return (Notis) q.uniqueResult();
    }

    public Notis getNotisBykodNotis2(String idMohon, String kodNotis, String kodNotis2) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak = NULL AND m.kodNotis.kod = :kod_notis AND m.kodNotis.kod = :kod_notis2");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        q.setParameter("kod_notis2", kodNotis2);
        return (Notis) q.uniqueResult();
    }

    @Transactional
    public void deleteAllNilai(Penilaian p) {
        penilaianDAO.delete(p);

    }

    @Transactional
    public void deleteAllKehadiran(PerbicaraanKehadiran p) {
        perbicaraanKehadiranDAO.delete(p);

    }

    @Transactional
    public void deleteAllPPTB(PermohonanPihakTidakBerkepentingan p) {
        permohonanPPTBDAO.delete(p);

    }

    @Transactional
    public void deleteAllPermohonanPihakWakil(PermohonanPihakWakil permohonanPihakWakil) {
        permohonanPihakWakilDAO.delete(permohonanPihakWakil);

    }

    public Permohonan getMaklumatPendaftar(String idMohon, String kodurusan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan = :id_mohon AND m.kodUrusan.kod = 'ABT-D'");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kodurusan", kodurusan);
        return (Permohonan) q.uniqueResult();
    }

    public List<PermohonanPihakWakil> getPermohonanPihakWakilListByidBicaraIdMH(long idMH, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakWakil m WHERE m.hakmilikPermohonan.id = :idMH AND m.hakmilikPerbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setParameter("idMH", idMH);
        q.setParameter("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PermohonanPihakWakil> getPermohonanPihakWakilListByidMPW(long idWakilPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakWakil m WHERE m.idWakilPermohonanPihak = :idWakilPermohonanPihak");
        q.setParameter("idWakilPermohonanPihak", idWakilPermohonanPihak);
        return q.list();
    }

    public PermohonanPihakWakil getPermohonanPihakWakilByidMPW(long idWakilPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakWakil m WHERE m.idWakilPermohonanPihak = :idWakilPermohonanPihak");
        q.setParameter("idWakilPermohonanPihak", idWakilPermohonanPihak);
        return (PermohonanPihakWakil) q.uniqueResult();
    }

    public PermohonanPihakWakil getPermohonanPihakWakilByidBicaraIdMH(long idMH, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihakWakil m WHERE m.hakmilikPermohonan.id = :idMH AND m.hakmilikPerbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setParameter("idMH", idMH);
        q.setParameter("idPerbicaraan", idPerbicaraan);
        return (PermohonanPihakWakil) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> getMohonRujukLuarByIdMohon(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanRujukanLuar m WHERE m.permohonan.idPermohonan = :id_mohon AND m.namaAgensi is not null");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    @Transactional
    public void deleteIdMPW(long idWakilPermohonanPihak) {
        String query = "DELETE FROM etanah.model.ambil.PerbicaraanKehadiran b where b.wakilPermohonanPihak.idWakilPermohonanPihak = :idWakilPermohonanPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idWakilPermohonanPihak", idWakilPermohonanPihak);
        q.executeUpdate();
    }

    @Transactional
    public void deleteIdMPW2(long idWakilPermohonanPihak) {
        String query = "DELETE FROM etanah.model.PermohonanPihakWakil b where b.idWakilPermohonanPihak = :idWakilPermohonanPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idWakilPermohonanPihak", idWakilPermohonanPihak);
        q.executeUpdate();
    }

    public List<KodAgensi> getSenaraikodAgensi() {
        try {
            String query = "Select u from etanah.model.KodAgensi u where u.kod in('6006') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

/**
 *
 * @author massita
 */
import etanah.model.Permohonan;
import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.SYSLOG;
import etanah.dao.AkaunDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.WakilPihakDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.LaporanTandaSempadanDAO;
import etanah.dao.LaporanPemulihanTanahDAO;
import etanah.dao.PermitDAO;
import etanah.model.PermohonanTuntutanKos;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.IntegrasiPermohonan;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.dao.PihakDAO;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.SyaratPendudukanDAO;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKlasifikasi;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTandaSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.SyaratPendudukan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.ambil.Penilaian;
import etanah.dao.PenilaianDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.AduanLokasi;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.TanahRizabPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.Akaun;
import etanah.model.JUBL;
import etanah.model.KodAkaun;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.PermohonanNota;
import etanah.model.Notis;
import etanah.model.KodPenyerah;
import etanah.model.KodStatusAkaun;
import etanah.model.KodStatusHakmilik;
import etanah.model.WakilPihak;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import etanah.model.KodUrusan;
import etanah.model.Permit;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.model.PermohonanJUBL;
import etanah.model.PermohonanPihakWakil;
import etanah.model.etapp.TBLINTPPTWARTA;
import etanah.service.common.LelongService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.util.ArrayList;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import etanah.workflow.WorkFlowService;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;

public class PengambilanService {

    private static final Logger logger = Logger.getLogger(PengambilanService.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanJUBL permohonanJUBL;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanJUBLDAO permohonanJUBLDAO;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    JUBL jubl;
    @Inject
    JUBLDAO jubldao;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    WakilPihakDAO wakilPihakDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    LaporanPemulihanTanahDAO laporanPemulihanTanahDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PenilaianDAO penilaianDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    SyaratPendudukanDAO syaratPendudukanDAO;
    @Inject
    LaporanTandaSempadanDAO laporanTandaSempadanDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PermohonanRujukanLuarSalinanDAO permohonanRujukanLuarSalinanDAO;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    private GeneratorIdPermohonan idMohonGenerator;
    @Inject
    KodPenyerahDAO kodPenyerahDAO;
    @Inject
    private PermitDAO permitDAO;
    @Inject
    LelongService lelongService;
    Permohonan permohonan;
    PermohonanRujukanLuar permohonanRujukanLuar;
    PermohonanPengambilan permohonanPengambilan;
    Penilaian penilaian;
    SyaratPendudukan syaratPendudukan;
    Hakmilik hakmilik;
    HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    PerbicaraanKehadiran perbicaraanKehadiran;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();

    @Transactional
    public void saveOrUpdatePermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);
    }

    public FasaPermohonan findFasaPermohonanPerbicaraan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '45Perbicaraan' and p.tarikhHantar is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanDepositKedua(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '135TerimaDepositkedua' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> listPermohonanTuntutanKosPertama(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod = 'ACQD50' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> listPermohonanTuntutanKosKedua(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod = 'ACQD75' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanPerbicaraan2(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '45Perbicaraan' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKemasukan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '49KemasukanMaklumat' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonan126Keputusan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '126Keputusan' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonan114KeputusanRunding(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '114KeputusanRunding' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonan15SiasatanPerintah(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '15SiasatanPerintah' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKedesakan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '28AdaKedesakan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
        // 29SediaBrgI
    }

    public FasaPermohonan findFasaPermohonanKedesakanEtapp(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '19MaklumEndBrgD'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanPertikaian(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '16RekodSampaiTampal'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKedesakanBaru(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '29SediaBrgI'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKedesakanBaruEtapp(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '19MaklumEndBrgD'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanWujudDE(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.keputusan.kod='DE'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanPelanB1(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'g_terima_pa_b1'";//60SemakLaporan
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findByIDMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan"
                + "AND b.dokumen.kodDokumen where kodDokumen = 'K'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPengambilanHakmilik> findListByidMH(String idmh) {
        String query = "SELECT b FROM etanah.model.ambil.PermohonanPengambilanHakmilik b where b.hakmilikPermohonan.id = :idmh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idmh", idmh);
        return q.list();
    }

    public PermohonanPengambilanHakmilik findByidMH(String idmh) {
        String query = "SELECT b FROM etanah.model.ambil.PermohonanPengambilanHakmilik b where b.hakmilikPermohonan.id = :idmh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idmh", idmh);
        return (PermohonanPengambilanHakmilik) q.uniqueResult();
    }

    public List<PermohonanPihak> findMohonPihak(String idPermohonan, String hakmilik) {
        String query = "SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("id_mohon", idPermohonan);
        q.setParameter("id_Hakmilik", hakmilik);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findByIDMohonWartaAll(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.noRujukan is null and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findByIDMohonWarta(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.noRujukan is null and b.permohonan.idPermohonan = :idPermohonan and b.namaPenyedia is null and b.item is not null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findByIDMohonWartaD(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.noRujukan is null and b.permohonan.idPermohonan = :idPermohonan and b.namaPenyedia is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPengambilan> findByIDMohonPengambilan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.ambil.PermohonanPengambilan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanPengambilan findByIdMohon(String idPermohonan) {
        String queryStr = "SELECT b FROM etanah.model.ambil.PermohonanPengambilan b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanPengambilan) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanPihakPendeposit findByIdMohonPendeposit(String idPermohonan, long idPihak) {
        String query = "Select p FROM etanah.model.PermohonanPihakPendeposit p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public PermohonanPihakPendeposit findByPendeposit(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakPendeposit p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public List<PermohonanPihakPendeposit> findByPendepositList(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakPendeposit p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> findListPemohon(String idPermohonan) {
        System.out.println("id mohon :" + idPermohonan);
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Pemohon findPemohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public WakilPihak findByPihak(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilPihak p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (WakilPihak) q.uniqueResult();
    }

    public int findMaxIdWakil() {
        String query = "Select max(p.idWakil+0) FROM etanah.model.WakilPihak p";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.uniqueResult();
    }

    public Akaun findAkaunByIdMohon(String idPermohonan) {
        String queryStr = "SELECT b FROM etanah.model.Akaun b where b.permohonan.idPermohonan = :idPermohonan";
        return (Akaun) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Akaun findAkaunBynoAkaun(String noAkaun) {
        String queryStr = "SELECT b FROM etanah.model.Akaun b where b.noAkaun = :noAkaun";
        return (Akaun) sessionProvider.get().createQuery(queryStr).setString("noAkaun", noAkaun).uniqueResult();
    }

    public PermohonanRujukanLuar findByIdMohonRujLuar(String idPermohonan) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanPihakWakil findByIdPihakWakil(long idBicara, long idMH) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanPihakWakil b where b.hakmilikPerbicaraan.idPerbicaraan = :idBicara and b.hakmilikPermohonan.id= :idMH ";
        Query q = sessionProvider.get().createQuery(queryStr);
        q.setLong("idBicara", idBicara);
        q.setLong("idMH", idMH);
//        return (PermohonanPihakWakil) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
        return (PermohonanPihakWakil) q.uniqueResult();
    }

    public Permohonan findByIdPermohonan2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<Permohonan> findByIdPermohonanList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//    public PermohonanRujukanLuar findByIDMohonRujukanLuar(String idPermohonan) {
//        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan And b.kodDokumenRujukan.kod='K'";
//        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
//    }
//
    public PermohonanRujukanLuar findByIDMohonRujukanLuar(String idSebelum) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idSebelum";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idSebelum", idSebelum).uniqueResult();
    }

    public LaporanPemulihanTanah findByIDMohonPulihTanah(String idMohon, long idMohonPihak) {
        String queryStr = "SELECT b FROM etanah.model.LaporanPemulihanTanah b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b FROM etanah.model.LaporanPemulihanTanah b where b.permohonan.idPermohonan = :idMohon and b.permohonanPihak.idPermohonanPihak = :idMohonPihak");
        q.setParameter("idMohon", idMohon);
        q.setParameter("idMohonPihak", idMohonPihak);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    public PermohonanRujukanLuar findByIDMohonRujukanLuar1(String idSebelum) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.permohonanSebelum = :idSebelum";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idSebelum", idSebelum).uniqueResult();
    }

    public List<PermohonanRujukanLuar> findMohonRujukanLuarListbyIdMohon(String idSebelum) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idSebelum";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idSebelum", idSebelum);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findbyIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findbyIdHakmilik1(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findbyIdHakmilikAdaKedesakan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idHakmilik and b.pegangan='1' and b.penarikBalikan='0' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findbyIdHakmilikTiadaKedesakan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idHakmilik and (b.pegangan='0' or b.pegangan is null) and b.penarikBalikan='0' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosByIdPermohonan(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.permohonan.idPermohonan = :idPermohonan and b.kodTransaksi.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findByIDMohonKos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permohonan findByIdSblm(String idSebelum) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idSebelum";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).uniqueResult();
    }

    public JUBL findJUBL(long id) {
        String query = "SELECT b FROM etanah.model.JUBL b where b.idJubl = :id";
        return (JUBL) sessionProvider.get().createQuery(query).setLong("id", id).uniqueResult();
    }

    public Permohonan findByIdPermohonanSebelum(String idSebelum) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idSebelum";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).uniqueResult();
    }

    public PermohonanPengambilan findByIdPermohonanSebelum2(String idSebelum) {
        String query = "SELECT b FROM etanah.model.ambil.PermohonanPengambilan b where b.permohonan.permohonanSebelum.idPermohonan = :idSebelum";
        return (PermohonanPengambilan) sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).uniqueResult();
    }

    public List<Pemohon> findByIdSblm2(String idSebelum) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idSebelum";
        return sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanByidMHnIdSblm(long idMH, String idSebelum) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH"
                + " AND b.permohonan.idPermohonan := idSebelum");
        q.setParameter("idMH", idMH);
        q.setString("idPermohonan", idSebelum);
        return q.list();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanByidMHnIdPermohonanPampasan(long idMH, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH"
                + " AND b.permohonan.idPermohonan := idPermohonan");
        q.setParameter("idMH", idMH);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanPampasan(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranByidMHnIdPermohonanPampasan(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak != null and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranMahkamah(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak != null and m.keterangan='Mahkamah' and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getAllPerbicaraanKehadiran(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranAmanahRaya(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak != null and m.keterangan='Amanah' and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<Penilaian> getPenilaianByidHadirPampasan(long idKehadiran) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.Penilaian m where m.kehadiran.idKehadiran = :idKehadiran");
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<Penilaian> getPenilaianByidHadirPampasan2(long idKehadiran) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.Penilaian m where m.kehadiran.idKehadiran = :idKehadiran");
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public Pihak findByIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        return (Pihak) sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).uniqueResult();
    }

    public List<Pihak> findByIdPihakList(Long idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    public Hakmilik findByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilik = :idHakmilik";
        return (Hakmilik) sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).uniqueResult();
    }

    public PermohonanRujukanLuar findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findByIdPermohonan1(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdPermohonanhakmilikbicara(String idPermohonan, String idHakmilik, long id) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idSPermohonan AND p.hakmilik.idHakmilik = :id_Hakmilik AND p.permohonan.id = :id_MH AND  ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setLong("idMH", id);
        return q.list();
    }

    public List<HakmilikPermohonan> findByKodPenyerah(String kodPenyerah) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.kodPenyerah.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kodPenyerah);
        return q.list();
    }

    public List<PermohonanPihakTidakBerkepentingan> findByIdMh(long id) {
        String query = "Select p FROM etanah.model.PermohonanPihakTidakBerkepentingan p WHERE p.HakmilikPermohonan.id = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdSebelum(String idSebelum) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idSebelum";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idSebelum", idSebelum);
        return q.list();
    }

    public HakmilikPermohonan findPermohonanByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.permohonan.idPermohonan = :idPermohonan";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<HakmilikPermohonan> findPermohonanByIdPermohonanPegangan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.permohonan.idPermohonan = :idPermohonan and b.pegangan is null ";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Penilaian findPenilaian(long idhadir) {
        String query = "SELECT b FROM etanah.model.ambil.Penilaian b WHERE b.kehadiran.idKehadiran = :idhadir and b.jenis='P' ";
        return (Penilaian) sessionProvider.get().createQuery(query).setLong("idhadir", idhadir).uniqueResult();
    }

    public HakmilikPermohonan findPermohonanByIdMH(long idMH) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.id = :idMH";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setLong("idMH", idMH).uniqueResult();
    }

    public PermohonanTuntutanKos findTuntutanByIdKos(Long idKos) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p WHERE p.idKos = :idKos";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findTuntutanByIdPermohonanPihak(Long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.pihak.idPermohonanPihak = :idPermohonanPihak";
        return (PermohonanTuntutanKos) sessionProvider.get().createQuery(query).setLong("idPermohonanPihak", idPermohonanPihak).uniqueResult();
//        + "AND b.pihak.pihak.idPihak where idPihak = idPihak"
    }

    public PermohonanTuntutanKos findTuntutanByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod='ACQD50'";
        return (PermohonanTuntutanKos) sessionProvider.get().createQuery(query).setParameter("idPermohonan", idPermohonan).uniqueResult();
//        + "AND b.pihak.pihak.idPihak where idPihak = idPihak"
    }

    public SyaratPendudukan findByIdPermohonan4SyaratPendudukan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SyaratPendudukan b where b.permohonan.idPermohonan = :idPermohonan";
        return (SyaratPendudukan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByIdHakmilikIdPihakList(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return q.list();
    }

    public List<AmbilPampasan> getAmbilPampasanListByidMP(long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return q.list();
    }

    // added  by Rajesh
    public List<PermohonanTuntutanKos> findByIdPermohonanPihak2(Long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.pihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return q.list();
    }

    public List<PermohonanTuntutanKos> getIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanPengambilan findNoWartaByIdPengambilan(Long idPengambilan) {
        String query = "SELECT p FROM etanah.model.ambil.PermohonanPengambilan p WHERE p.idPengambilan = :idPengambilan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPengambilan", idPengambilan);
        return (PermohonanPengambilan) q.uniqueResult();
    }

    public List<HakmilikPerbicaraan> getListHakmilikbicara(Long idMH) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonanBantah(String idPermohonan) {
        String query = "SELECT mh FROM etanah.model.ambil.PerbicaraanKehadiran abh,etanah.model.PermohonanPihak mp,etanah.model.Pihak phk,etanah.model.ambil.HakmilikPerbicaraan ahb,etanah.model.HakmilikPermohonan mh where "
                + " mp.permohonan.idPermohonan = mh.permohonan.idPermohonan "
                + "and ahb.hakmilikPermohonan.id=mh.id "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and abh.perbicaraan.idPerbicaraan=ahb.idPerbicaraan "
                + "and abh.pihak.idPermohonanPihak=mp.idPermohonanPihak "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and mh.permohonan.idPermohonan=:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

//                +"and abh.bantahElektrik = 'Y' "
    public List<HakmilikPerbicaraan> getListHakmilikPermohonanBicara(String idPermohonan) {
        String query = "SELECT mh FROM etanah.model.ambil.PerbicaraanKehadiran abh,etanah.model.PermohonanPihak mp,etanah.model.Pihak phk,etanah.model.ambil.HakmilikPerbicaraan ahb,etanah.model.HakmilikPermohonan mh where "
                + " mp.permohonan.idPermohonan = mh.permohonan.idPermohonan "
                + "and ahb.hakmilikPermohonan.id=mh.id "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and abh.perbicaraan.idPerbicaraan=ahb.idPerbicaraan "
                + "and abh.pihak.idPermohonanPihak=mp.idPermohonanPihak "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and mh.permohonan.idPermohonan=:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMHPampasan(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanByidMHList(Long idMH) {
        String query = "SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public List<PermohonanPihak> getPermohonanPihakByidMohonList(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanPihak m WHERE m.Permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> getPermohonanPihakByidMohonList1(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan and m.hakmilik.idHakmilik is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> getPermohonanPihakPemohon(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idPermohonan and m.hakmilik.idHakmilik is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getBantahanList(String idPermohonan) {
        String query = "SELECT abh FROM etanah.model.ambil.PerbicaraanKehadiran abh,etanah.model.PermohonanPihak mp,etanah.model.Pihak phk,etanah.model.ambil.HakmilikPerbicaraan ahb,etanah.model.HakmilikPermohonan mh where "
                + " mp.permohonan.idPermohonan = mh.permohonan.idPermohonan "
                + "and ahb.hakmilikPermohonan.id=mh.id "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and abh.perbicaraan.idPerbicaraan=ahb.idPerbicaraan "
                + "and abh.pihak.idPermohonanPihak=mp.idPermohonanPihak "
                + "and mp.pihak.idPihak=phk.idPihak "
                + "and mh.permohonan.idPermohonan=:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//                +"and abh.bantahElektrik = 'Y' "
    public PermohonanPihak getPmohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak AND m.jenis='PM'");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> getPmohonPihakByIdHakmilikIdPihakList(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT distinct m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak ");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return q.list();
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak(String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m WHERE m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak AND m.aktif = 'Y'");
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMPidBicara(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidBicara(long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }
    
     public List<KodAgensi> findKodAgensi2(int kodKementerian, String nama, String katgAgensi, String negeri, String gelaran, String daerah) {

        String qBase = "Select a from KodAgensi a ";
        String query = "Select a from KodAgensi a ";
        String cond = " Where ";
        String mulCond = " and ";

        if (kodKementerian != 0) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodKementerian =:kodKementerian ";
            } else {
                query += " " + mulCond + "  a.kodKementerian =:kodKementerian ";

            }
        }
        if (StringUtils.isNotBlank(nama) || nama != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " UPPER(a.nama) LIKE:nama ";
            } else {
                query += " " + mulCond + " UPPER(a.nama) LIKE:nama ";

            }
        }
        if (StringUtils.isNotBlank(katgAgensi) || katgAgensi != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kategoriAgensi.kod =:katgAgensi ";
            } else {
                query += " " + mulCond + "  a.kategoriAgensi =:katgAgensi ";

            }
        }
        if (StringUtils.isNotBlank(negeri) || negeri != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodNegeri.kod =:negeri ";
            } else {
                query += " " + mulCond + "  a.kodNegeri.kod =:negeri ";

            }
        }


        if (StringUtils.isNotBlank(gelaran) || gelaran != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodGelaran.kod =:gelaran ";
            } else {
                query += " " + mulCond + "  a.kodGelaran.kod =:gelaran ";

            }
        }
        if (StringUtils.isNotBlank(daerah) || daerah != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.kodDaerah.kod =:daerah ";
            } else {
                query += " " + mulCond + "  a.kodDaerah.kod =:daerah ";

            }
        }
        Query q = sessionProvider.get().createQuery(query);

        if (kodKementerian != 0) {
            q.setInteger("kodKementerian", kodKementerian);
        }

        if (nama != null) {
            q.setString("nama", "%" + nama.toUpperCase() + "%");
        }
        if (katgAgensi != null) {
            q.setString("katgAgensi", katgAgensi);
        }
        if (negeri != null) {
            q.setString("negeri", negeri);
        }
        if (gelaran != null) {
            q.setString("gelaran", gelaran);
        }
        if (daerah != null) {
            q.setString("daerah", daerah);
        }
        return q.list();

    }

    public AmbilPampasan getPerbicaraanKehadiranbyidMPidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return (AmbilPampasan) q.uniqueResult();

    }

    public List<AmbilPampasan> getAmbilPampasanListByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
//        String query = "SELECT COUNT(DISTINCT b) FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<AmbilPampasan> getAmbilPampasanList(long idKehadiran) {
        String query = "SELECT DISTINCT b.perbicaraanKehadiran.idKehadiran FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.ambil.PerbicaraanKehadiran b WHERE b.idKehadiran IN (select distinct k.perbicaraanKehadiran.idKehadiran from etanah.model.AmbilPampasan k where k.perbicaraanKehadiran.idKehadiran = :idKehadiran)";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public AmbilPampasan getAmbilPampasanByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return (AmbilPampasan) q.uniqueResult();
    }

    public AmbilPampasan getAmbilPampasanByidAmbil(long idAmbilPampasan) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.idAmbilPampasan = :idAmbilPampasan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idAmbilPampasan", idAmbilPampasan);
        return (AmbilPampasan) q.uniqueResult();
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahByidMHidMP(long idMH, long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP ");
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahByidMHidMP2(long idMH, long idMP, String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.idPermohonan= :idMohon and m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP ");
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        q.setParameter("idMohon", idMohon);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahByJenisLaporan(String idPermohonan, long idMH, long idMP, String jenisLapor) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.idPermohonan= :idPermohonan and m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP and m.jenisLaporan = :jenisLaporan");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        q.setParameter("jenisLaporan", jenisLapor);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanah(String idPermohonan, long idMH, long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.idPermohonan= :idPermohonan and m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahIdMP(String idPermohonan, long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.idPermohonan= :idPermohonan and m.permohonanPihak.idPermohonanPihak = :idMP");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idMP", idMP);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    @Transactional
    public LaporanPemulihanTanah simpanLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah) {
        laporanPemulihanTanah = laporanPemulihanTanahDAO.saveOrUpdate(laporanPemulihanTanah);
        return laporanPemulihanTanah;
    }

    @Transactional
    public Penilaian simpanPampasan(Penilaian penilaian) {
        penilaian = penilaianDAO.saveOrUpdate(penilaian);
        return penilaian;
    }

    @Transactional
    public void simpanInIntegrasiPermohonan(IntegrasiPermohonan integrasiPermohonan) {
        integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);
    }

    @Transactional
    public void simpanNamaPemohon(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void simpanFolder(FolderDokumen folderdokumen) {
        folderDokumenDAO.saveOrUpdate(folderdokumen);
    }

    @Transactional
    public void simpanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.saveOrUpdate(permohonanTuntutanKos);
    }

    @Transactional
    public void deleteAll(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }

    @Transactional
    public void delete() {
        permohonanTuntutanKosDAO.delete(null);
    }

    @Transactional
    public void delete(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }

    @Transactional
    public void deleteAmbilPampasan(List<AmbilPampasan> list) {
        for (AmbilPampasan ambilPampasan : list) {
            ambilPampasanDAO.delete(ambilPampasan);
        }
    }

    @Transactional
    public void delete(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.delete(ambilPampasan);
    }

    @Transactional
    public void save(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.save(permohonanPengambilan);
    }

    @Transactional
    public void saveJUBL(PermohonanJUBL permohonanJUBL) {
        permohonanJUBLDAO.save(permohonanJUBL);
    }

    @Transactional
    public void saveJUBL2(JUBL jubl) {
        jubldao.save(jubl);
    }

    @Transactional
    public void update(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.update(permohonanPengambilan);
    }

    @Transactional
    public void simpanWarta(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.saveOrUpdate(permohonanPengambilan);
    }

    @Transactional
    public boolean saveSingleHakmilikPerbicaraan(HakmilikPerbicaraan hp) {
        hp = (HakmilikPerbicaraan) hakmilikPerbicaraanDAO.saveOrUpdate(hp);
        return (hp != null);
    }

    @Transactional
    public void saveSyaratPendudukan(SyaratPendudukan syaratPendudukan) {
        syaratPendudukanDAO.save(syaratPendudukan);
    }

    public PermohonanPengambilan findByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.ambil.PermohonanPengambilan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanPengambilan) q.uniqueResult();

    }

    @Transactional
    public void simpanPermohonanSebelum(Permohonan permohonan, Pengguna peng) {

        permohonanDAO.saveOrUpdate(permohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        Permohonan pSblm = permohonan.getPermohonanSebelum();

        String idSblm = pSblm.getIdPermohonan();

        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = pSblm.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : listHp) {
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
            for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {

                senaraiPermohonanPihak = pengambilanService.getSenaraiPmohonPihakByIdHakmilikIdPihakList(idSblm, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                for (PermohonanPihak pp : senaraiPermohonanPihak) {
                    if (pp == null) {
                        PermohonanPihak newPP = new PermohonanPihak();
                        newPP.setPermohonan(pSblm);
                        newPP.setHakmilik(hakmilikPihak.getHakmilik());
                        newPP.setPihak(hakmilikPihak.getPihak());
                        newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                        newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                        newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                        newPP.setCawangan(pSblm.getCawangan());
                        newPP.setInfoAudit(ia);
                        hakmilikPihak.getKaveatAktif();
                        KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                        newPP.setJenis(findByIdKodPihak("PJK"));
                        permohonanPihakDAO.save(newPP);
                    }
                }
            }
        }
        //permohonanDAO.saveOrUpdate(permohonan);

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
            try {
                System.out.println("hp.getHakmilik().getIdHakmilik() >> " + hp.getHakmilik().getIdHakmilik());
                List<PermohonanPihak> pp = pengambilanService.findMohonPihak(idPermohonan, hp.getHakmilik().getIdHakmilik());
                if (pp.size() == 0) {
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {

//                PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                if (pp == null) {
                        if (hakmilikPihak.getAktif() == 'Y') {
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
                }
            } catch (Exception h) {
            }
        }

    }

    @Transactional
    public void saveOrupdateAmbilPampasan(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.saveOrUpdate(ambilPampasan);
    }

    @Transactional
    public void saveOrupdatePermohonanPihak(PermohonanPihak permohonanPihak) {
        permohonanPihakDAO.saveOrUpdate(permohonanPihak);
    }

    @Transactional
    public void update(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.saveOrUpdate(ambilPampasan);
    }

    @Transactional
    public void simpanIdMohonOSebab(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public HakmilikPerbicaraan saveOrUpdateHakmilikPerbicaraan(HakmilikPerbicaraan n) {
        return hakmilikPerbicaraanDAO.saveOrUpdate(n);
    }

    @Transactional
    public JUBL saveOrUpdateJUBL(JUBL n) {
        return jubldao.saveOrUpdate(n);
    }

    @Transactional
    public PermohonanRujukanLuar saveOrUpdateMRL(PermohonanRujukanLuar permohonanRujukanLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void saveMRL(PermohonanRujukanLuar mrl) {
        permohonanRujukanLuarDAO.save(mrl);
    }

    @Transactional
    public PerbicaraanKehadiran saveOrUpdatePerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        return perbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
    }

    public KodJenisPihakBerkepentingan findByIdKodPihak(String kodPihak) {
        String query = "SELECT b FROM etanah.model.KodJenisPihakBerkepentingan b WHERE b.kod = :kodPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPihak", kodPihak);
        return (KodJenisPihakBerkepentingan) q.uniqueResult();
    }

    public KodKeputusan findByKodKeputusan(String kodPutus) {
        String query = "SELECT b FROM etanah.model.KodKeputusan b WHERE b.kod = :kodPutus";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPutus", kodPutus);
        return (KodKeputusan) q.uniqueResult();
    }

    public KodStatusHakmilik findByKodStatusHakmilik(String kodPutus) {
        String query = "SELECT b FROM etanah.model.KodStatusHakmilik b WHERE b.kod = :kodPutus";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPutus", kodPutus);
        return (KodStatusHakmilik) q.uniqueResult();
    }

    public PermohonanPihak findByIdSblmIdPihak(String idSblm, Long idPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan "
                + " AND b.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idSblm);
        q.setLong("idPihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public AmbilPampasan getAmbilPampasanByidMP(long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b where b.perbicaraanKehadiran.pihak.idPermohonanPihak = :idPermohonanPihak ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (AmbilPampasan) q.uniqueResult();

    }

    public AmbilPampasan getAmbilPampasanByIdHadirNidMP(long idHadir) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b where b.perbicaraanKehadiran.idKehadiran = :idHadir ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idHadir", idHadir);
        return (AmbilPampasan) q.uniqueResult();

    }

    public LaporanTandaSempadan getLaporanTandaSempadanByidMHidMP(long idMH, long idMP) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanTandaSempadan m WHERE m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP ");
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        return (LaporanTandaSempadan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonan_KeputusanNIDAliran(String idPermohonan, String idAliran) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = :idAliran";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<Permohonan> searchIdMohon(String id, String sebabProjek, String idPermohonan, String kod) {

        try {
            String query = "Select u from etanah.model.Permohonan u WHERE u.idPermohonan IN (Select distinct hp.permohonan.idPermohonan from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik IN (Select hp.hakmilik.idHakmilik from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan))";

            if (id != null) {
                query += " AND u.idPermohonan LIKE :id";
            }

            if (sebabProjek != null) {
                query += " AND u.sebab LIKE :sebabProjek";
            }
            if (kod != null) {
                query += " AND u.kodUrusan.kod LIKE :kod ";
            }

            Query q = sessionProvider.get().createQuery(query);

            if (id != null) {
                q.setString("id", id + "%");
            }
            q.setParameter("idPermohonan", idPermohonan);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }
            if (sebabProjek != null) {
                q.setString("sebabProjek", "%" + sebabProjek + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Permohonan> searchPermohonan(String id, String sebabProjek, String kod, String urusan) {

        try {
            String query = "Select u from etanah.model.Permohonan u WHERE u.kodUrusan.kod = :kod";

            if (id != null) {
                query += " AND u.idPermohonan LIKE :id";
            }

            if (sebabProjek != null) {
                query += " AND u.sebab LIKE :sebabProjek ";
            }

            Query q = sessionProvider.get().createQuery(query);

            if (id != null) {
                q.setString("id", id + "%");
            }
            q.setParameter("kod", kod);

            if (sebabProjek != null) {
                q.setString("sebabProjek", "%" + sebabProjek + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Permohonan> searchBpm(String bpm, String kodNegeri) {
        String query = "Select p from  etanah.model.KodBandarPekanMukim k, etanah.model.Permohonan p,etanah.model.HakmilikPermohonan mh  "
                + "WHERE k.nama =:bpm AND substring(p.idPermohonan, 0,2) =:kodNegeri AND substring(p.idPermohonan, 3,2) =k.daerah.kod AND substring(p.idPermohonan, 5,3) ='ACQ' and  mh.permohonan.idPermohonan=p.idPermohonan and substring(mh.hakmilik.idHakmilik,5,2)=k.bandarPekanMukim ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("bpm", bpm);
        q.setString("kodNegeri", kodNegeri);
        return q.list();

//        select a.id_mohon,a.kod_bpm,k.KOD_DAERAH,k.nama
//        from aduan_lokasi a, kod_bpm k
//        where k.nama LIKE '%Durian%'
//        and k.kod=a.KOD_BPM
    }

    public PermohonanPengambilan getPermohonanPengambilanByidSblm(String idSblm) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PermohonanPengambilan m WHERE m.permohonan.idPermohonan = :idSblm ");
        q.setString("idSblm", idSblm);
        return (PermohonanPengambilan) q.uniqueResult();

    }

    @Transactional
    public HakmilikPerbicaraan saveHakmilikPerbicaraan(HakmilikPerbicaraan hp) {
        hp = (HakmilikPerbicaraan) hakmilikPerbicaraanDAO.saveOrUpdate(hp);
        return hp;
    }

    public LaporanTandaSempadan getLaporanTandaSempadanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanTandaSempadan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return (LaporanTandaSempadan) q.uniqueResult();
    }

    public PermohonanLaporanPelan getLaporanPelukisPelanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanLaporanPelan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    @Transactional
    public LaporanTandaSempadan simpanLaporanTandaSempadan(LaporanTandaSempadan laporanTandaSempadan) {
        laporanTandaSempadan = laporanTandaSempadanDAO.saveOrUpdate(laporanTandaSempadan);
        return laporanTandaSempadan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakByidPihak(String idHakmilik, long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m WHERE m.hakmilik.idHakmilik = :idHakmilik and m.pihak.idPihak = :idPihak ");
        q.setString("idHakmilik", idHakmilik);
        q.setLong("idPihak", idPihak);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PermohonanRujukanLuar getLatestRujukanLuar(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanRujukanLuar mk where mk.permohonan.idPermohonan = :idPermohonan and mk.catatan = 'Warta Pembetulan' and mk.infoAudit.tarikhMasuk = (Select MAX(pk.infoAudit.tarikhMasuk) from etanah.model.PermohonanRujukanLuar pk where pk.permohonan.idPermohonan = :idPermohonan and pk.catatan = 'Warta Pembetulan')");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar getRujLuar(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.catatan = 'Warta Asal'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> getRujLuarList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.catatan = 'Warta Asal'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    public List<PermohonanPihak> getSenaraiPermohonPihakByIdHakmilik(String idMohon, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPermohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :idPihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("idPihak", idPihak);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPermohonPihak(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    @Transactional
    public void deleteHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    @Transactional
    public void deletePermohonanPihak(PermohonanPihak permohonanPihak) {
        permohonanPihakDAO.delete(permohonanPihak);
    }

    public List<Permohonan> getSeneraiPermohonanByidHakmilik(String idHakmilik) {

        try {
            String query = "Select u from etanah.model.Permohonan u WHERE u.idPermohonan IN (Select distinct hp.permohonan.idPermohonan from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik) AND (u.kodUrusan.kod = '831A' OR u.kodUrusan.kod = '831B' OR u.kodUrusan.kod = '831C' OR u.kodUrusan.kod = 'ESK8')";

//           String query = "Select HP FROM etanah.model.HakmilikPermohonan HP,etanah.model.Permohonan P WHERE ";
//
//               query += " HP.hakmilik.idHakmilik = :idHakmilik ";
//               query += " AND ";
//               query += "HP.permohonan.idPermohonan = P.idPermohonan ANd (P.status = 'AK' OR P.status = 'SL')";
//               query += " AND ";
//               query += "P.kodUrusan.kod = :kod ";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("idHakmilik", idHakmilik);
//               q.setString("kod", kod);

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihakBerkepentinganByidHakmilik(String idHakmilik) {

        try {
            String query = "Select distinct u.hakmilik from etanah.model.HakmilikPihakBerkepentingan u WHERE u.hakmilik = :idHakmilik";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }
    }

    @Transactional
    public Permohonan simpanPermohonan(Permohonan permohonan) {
        return permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public FolderDokumen saveOrUpdateFolderDokumen(FolderDokumen fd) {
        return folderDokumenDAO.saveOrUpdate(fd);
    }

    @Transactional
    public Dokumen saveOrUpdateDokumen(Dokumen d) {
        return dokumenDAO.saveOrUpdate(d);
    }

    @Transactional
    public void deleteDokumen(Dokumen dokumen) {
        dokumenDAO.delete(dokumen);
    }

    @Transactional
    public KandunganFolder saveOrUpdateKandunganFolder(KandunganFolder kf) {
        return kandunganFolderDAO.saveOrUpdate(kf);
    }

    @Transactional
    public void deleteKandunganFolder(KandunganFolder kf) {
        kandunganFolderDAO.delete(kf);
    }

    @Transactional
    public PermohonanTuntutanKos simpanMohonTuntutKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        return permohonanTuntutanKosDAO.saveOrUpdate(permohonanTuntutanKos);
    }

    public PermohonanTuntutanKos getMohonTuntutKosByIdMohonItem(String idPermohonan, String item) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.permohonan.idPermohonan = :idPermohonan and b.item = :item";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("item", item);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakListbyIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik order by b.pihak.nama ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranByKetarangan(long idPerbicaraan, String keterangan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak != null and m.perbicaraan.idPerbicaraan = :idPerbicaraan and m.keterangan = :keterangan");
        q.setLong("idPerbicaraan", idPerbicaraan);
        q.setParameter("keterangan", keterangan);
        return q.list();
    }

//added by Afham
    public List<LaporanTanah> findLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanidMH(String idPermohonan, long idMH) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id=:idMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    //added by murali 12-10-2011
    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('223','225') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPTG(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('12','28') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }
    // added by murali 12-10-2011

    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonan(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public PermohonanTandatanganDokumen findPermohonanDokTTByIdMohonIdMh(String idPermohonan, String idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.hakmilikPermohonan.id = :idMH");
        q.setString("idPermohonan", idPermohonan);
//        q.setString("kodDokumen", kodDokumen);
        q.setString("idMH", idMH);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanDokTT(PermohonanTandatanganDokumen tandatanganDokumen) {
        permohonanTandatanganDokumenDAO.saveOrUpdate(tandatanganDokumen);
    }

    public List<PermohonanTandatanganDokumen> findPermohonanDokTTByIdPermohonanrehy(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.permohonan.idPermohonan = :idPermohonan)");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodAgensi> searchKodAgensiLupus(String kod, String kodAgensiNama, String kodNegeri) {

        try {
            String query = "";

            if (kodNegeri != null && kodNegeri.equals("04")) {
                System.out.println("-------Melaka---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN' , 'JTK') AND kodNegeri.kod =:kodNegeri";
            } else if (kodNegeri != null && kodNegeri.equals("05")) {
                System.out.println("-------NS---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') AND kodNegeri.kod =:kodNegeri";
            }
            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchKodAgensiADN(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public KodDUN findKodDUNByAgensi(String kodAgensi) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodDUN lt where lt.kodAgensi.kod = :kodAgensi");
        q.setString("kodAgensi", kodAgensi);
        return (KodDUN) q.uniqueResult();
    }

    public KodAkaun findKodAkaun(String kodAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodAkaun lt where lt.kod = :kodAkaun");
        q.setString("kodAkaun", kodAkaun);
        return (KodAkaun) q.uniqueResult();
    }

    public KodCaraPermohonan findKodCaraMohon(String kodAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodCaraPermohonan lt where lt.kod = :kodAkaun");
        q.setString("kodAkaun", kodAkaun);
        return (KodCaraPermohonan) q.uniqueResult();
    }

    public KodStatusAkaun findKodStatusAkaun(String kodStatusAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodStatusAkaun lt where lt.kod = :kodStatusAkaun");
        q.setString("kodStatusAkaun", kodStatusAkaun);
        return (KodStatusAkaun) q.uniqueResult();
    }

    public List<PermohonanRujukanLuarDokumen> findByIdMohonForMRLDok(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarDokumen b where b.permohonanRujukanLuar.idRujukan = :idRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUN(String idPermohonan, String kategoriAgensi) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        return q.list();
    }

    public List<KodAgensi> searchKodAgensiJTK(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public PermohonanRujukanLuarSalinan findSalinanByIdRujukanAndKodAgensi(String idRujukan, String kodAgensi) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan AND b.kodAgensi.kod = :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        q.setString("kodAgensi", kodAgensi);
        return (PermohonanRujukanLuarSalinan) q.uniqueResult();
    }

    @Transactional
    public void saveOrUpdate(PermohonanRujukanLuarSalinan p) {
        permohonanRujukanLuarSalinanDAO.saveOrUpdate(p);

    }

    @Transactional
    public void saveOrUpdate(HakmilikPermohonan p) {
        hakmilikPermohonanDAO.saveOrUpdate(p);

    }

    public List<PermohonanRujukanLuarSalinan> findByIdMohon3(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return q.list();
    }

    public PermohonanRujukanLuar getSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.agensi.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan  ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanHakmilik(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan" + " AND b.hakmilik.idHakmilik = :idHakmilik ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanHakmilikJab(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan" + " AND b.kodRujukan.kod !='NF' AND b.hakmilik.idHakmilik = :idHakmilik ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanRujukanLuarDokumen> findListDokumenRujukan(String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idRujukan", idRujukan);
        return q.list();
    }

    public List<PermohonanRujukanLuarSalinan> findListSalinanRujukan(String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarSalinan h where h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idRujukan", idRujukan);
        return q.list();
    }

    @Transactional
    public void deleteDokumen1(PermohonanRujukanLuarDokumen p) {
        permohonanRujukanLuarDokumenDAO.delete(p);
    }

    @Transactional
    public void deleteSalinan(PermohonanRujukanLuarSalinan p) {
        permohonanRujukanLuarSalinanDAO.delete(p);
    }

    public PermohonanRujukanLuar findByIdRujukan(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public TanahRizabPermohonan findByidTanahRizabPermohonan(Long idTanahRizabPermohonan) {
        String query = "SELECT b FROM etanah.model.TanahRizabPermohonan b where b.idTanahRizabPermohonan = :idTanahRizabPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTanahRizabPermohonan", idTanahRizabPermohonan);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public PermohonanRujukanLuarSalinan findSalinan(String idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarSalinan) q.uniqueResult();
    }

    @Transactional
    public void simpanAkaun(Akaun akaun) {
        akaunDAO.saveOrUpdate(akaun);
    }

    @Transactional
    public void simpanPermohonanRujLuarDokumen(PermohonanRujukanLuarDokumen permohonanRujukanLuarDokumen) {
        permohonanRujukanLuarDokumenDAO.saveOrUpdate(permohonanRujukanLuarDokumen);
    }

    public PermohonanRujukanLuarDokumen findDokumen(String idDokumen, String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.dokumen.idDokumen = :idDokumen" + " and h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idDokumen", idDokumen).setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensi1(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.namaAgensi is not null  ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanLaporanPelan> findByIdPermohonanPelan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdPermohonanPP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        LOG.info("query ::" + q);

        return q.list();
    }

    @Transactional
    public void simpanDokumen(PermohonanRujukanLuarDokumen p) {
        permohonanRujukanLuarDokumenDAO.saveOrUpdate(p);
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosACQ(String idPermohonan, String kod) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p where p.permohonan.idPermohonan = :idPermohonan AND p.kodTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    //added By Murali on 10-01-2012
    public List<Permohonan> getSeneraiPermohonanByIdHakmilik(String id, String sebabProjek, String kod, String idPermohonan) {

        try {
            String query = "SELECT u from etanah.model.Permohonan u WHERE u.kodUrusan.kod = :kod AND u.idPermohonan IN "
                    + " (SELECT distinct hp.permohonan.idPermohonan from etanah.model.HakmilikPermohonan hp "
                    + " WHERE hp.hakmilik.idHakmilik IN(SELECT hp1.hakmilik.idHakmilik from etanah.model.HakmilikPermohonan hp1 WHERE hp1.permohonan.idPermohonan = :idPermohonan))";

            if (id != null) {
                query += " AND u.idPermohonan LIKE :id";
            }

            if (sebabProjek != null) {
                query += " AND u.sebab LIKE :sebabProjek ";
            }

            Query q = sessionProvider.get().createQuery(query);

            q.setParameter("kod", kod);

            q.setParameter("idPermohonan", idPermohonan);

            if (id != null) {
                q.setString("id", id + "%");
            }

            if (sebabProjek != null) {
                q.setString("sebabProjek", "%" + sebabProjek + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }
    //end

    //added by murali 16-02-2012
    public List<HakmilikPermohonan> getHakmilikPermohonanList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan order by b.hakmilik.bandarPekanMukim ASC";
        return sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).list();
    }

    public List<PermohonanNota> findListNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan AND p.idAliran != :idAliran "
                + "ORDER BY p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return q.list();

    }

    public List<PermohonanKertas> findMohonanKertasByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Notis> searchNotis1(String idPermohonan, String idHakmilik, String kod) {
        String query = "Select n from etanah.model.Notis n,etanah.model.HakmilikPermohonan mh WHERE mh.permohonan.idPermohonan=n.permohonan.idPermohonan";

        if (idPermohonan != null) {
            query += " AND n.idPermohonan LIKE :idPermohonan";
        }
        if (idHakmilik != null) {
            query += " AND mh.hakmilik.idhakmilik LIKE :idHakmilik ";
        }
        if (kod != null) {
            query += " AND n.kodNotis.kod LIKE :kod ";
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodNotis", kod);
        return q.list();

    }

    public List<PerbicaraanKehadiran> searchAgensi1(String idPermohonan, String idHakmilik, String noKP, String nama, String jenisAgensi) {
        String query = "Select n from etanah.model.Permohonan p,etanah.model.PermohonanPihak pr,etanah.model.Pihak ph,etanah.model.Ambil.PerbicaraanKehadiran pk WHERE p.idPermohonan=pr.permohonan.idPermohonan";

        if (idPermohonan != null) {
            query += " AND p.idPermohonan LIKE :idPermohonan";
        }
        if (idHakmilik != null) {
            query += " AND pr.hakmilik.idhakmilik LIKE :idHakmilik ";
        }
        if (noKP != null) {
            query += " AND ph.noPengenalan LIKE :noKP ";
        }
        if (nama != null) {
            query += " AND ph.nama LIKE :nama ";
        }
        if (jenisAgensi != null) {
            query += " AND pk.keterangan LIKE :jenisAgensi ";
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noPengenalan", noKP);
        q.setString("nama", nama);
        q.setString("keterangan", jenisAgensi);
        return q.list();

    }

    @Transactional
    public Pihak saveOrUpdate(Pihak p) {
        return pihakDAO.saveOrUpdate(p);
    }

    @Transactional
    public void kemaskiniwakilPihak(WakilPihak wakilPihak) {
        wakilPihakDAO.saveOrUpdate(wakilPihak);
    }

    @Transactional
    public void simpanwakilPihak(WakilPihak wakilPihak) {
        wakilPihakDAO.save(wakilPihak);
    }

    @Transactional
    public void hapus(WakilPihak wakilPihak) {
        wakilPihakDAO.delete(wakilPihak);
    }

    public List<AmbilPampasan> searchPihak(String idPermohonan, String idHakmilik, String noKP, String nama, String jenisAgensi) {
        String query = "Select distinct ap from etanah.model.Permohonan p,etanah.model.PermohonanPihak pr,etanah.model.Pihak ph,etanah.model.ambil.PerbicaraanKehadiran pk, etanah.model.AmbilPampasan ap, etanah.model.ambil.HakmilikPerbicaraan hb"
                + " where pk.idKehadiran = ap.perbicaraanKehadiran.idKehadiran and pk.pihak.pihak.idPihak = pr.pihak.idPihak and hb.idPerbicaraan = pk.perbicaraan.idPerbicaraan";

        if (idPermohonan != null) {
            query += " AND p.idPermohonan LIKE :idPermohonan";
        }
        if (idHakmilik != null) {
            query += " AND pr.hakmilik.idHakmilik LIKE :idHakmilik ";
        }
        if (noKP != null) {
            query += " AND ph.noPengenalan LIKE :noKP ";
        }
        if (nama != null) {
            query += " AND ph.nama LIKE :nama ";
        }
        if (jenisAgensi != null) {
            query += " AND pk.keterangan LIKE :keterangan ";
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noKP", noKP);
        q.setString("nama", nama);
        q.setString("keterangan", jenisAgensi);
        return q.list();

    }
//    public List<Permohonan> searchNotis(String idPermohonan,String idHakmilik, String kod) {
//
//        try {
//            String query = "Select u from etanah.model.Permohonan u WHERE u.idPermohonan IN (Select distinct hp.permohonan.idPermohonan from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik IN (Select hp.hakmilik.idHakmilik from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan))";
//
//            if (idHakmilik != null) {
//                query += " AND u.idPermohonan LIKE :id";
//            }
//
//            if (idPermohonan != null) {
//                query += " AND u.sebab LIKE :sebabProjek";
//            }
//            if (kod != null) {
//                query += " AND u.kodUrusan.kod LIKE :kod ";
//            }
//
//
//            Query q = sessionProvider.get().createQuery(query);
//
//            if (id != null) {
//                q.setString("id", id + "%");
//            }
//            q.setParameter("idPermohonan", idPermohonan);
//            if (kod != null) {
//                q.setString("kod", kod + "%");
//            }
//            if (sebabProjek != null) {
//                q.setString("sebabProjek", "%" + sebabProjek + "%");
//            }
//
//            return q.list();
//        } finally {
//            //session.close();
//        }
//    }

    @Transactional
    public Long savePihakPemohon(Pihak pihak, Pemohon pemohon) {
        Pihak pihakTemp = new Pihak();
        Pemohon pemohonTemp = new Pemohon();
        pihakTemp = pihakDAO.saveOrUpdate(pihak);
        pemohon.setPihak(pihakTemp);
        pemohonTemp = pemohonDAO.saveOrUpdate(pemohon);
        return pihakTemp.getIdPihak();

    }

    public Permohonan generateIdPermohonanWorkflowGetIdMohonACQ(KodUrusan ku, Pengguna pengguna,
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

//            for (Hakmilik hm : senaraiHakmilik) {
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
            }
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            //buat sendiriiiiiiiii xnk
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
                List<HakmilikPermohonan> hakmilikPermohonanList;
                hakmilikPermohonanList = p.getPermohonanSebelum().getSenaraiHakmilik();

                for (HakmilikPermohonan h : hakmilikPermohonanList) {
                    if (hm.getIdHakmilik().equals(h.getHakmilik().getIdHakmilik())) {
                        hpa.setLuasTerlibat(h.getLuasTerlibat());
                        hpa.setKodUnitLuas(h.getKodUnitLuas());
                    }
                }
                hakmilikPermohonanDAO.save(hpa);
                logger.debug("hpa.id :" + hpa.getId() + ", idHakmilik :" + hpa.getHakmilik().getIdHakmilik());
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
            logger.error(t);
            return null;
        }
        return p;
    }

    public Pihak findPihak(String nama) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pihak p where nama = :nama");
        q.setString("nama", nama);
        return (Pihak) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findMRLEtapp(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<PermohonanRujukanLuar> findWartaEtappByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodJenisPengenalan> getKodPengenalan() throws NamingException {
        String query = "Select kp from etanah.model.KodJenisPengenalan kp WHERE kp.aktif = 'Y' Order By nama ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

//        SYSLOG.info("Listing all Kod Peranan...");
        return q.list();
    }

    public List<KodAgensi> getSenaraikodAgensi() {
        try {
            String query = "Select u from etanah.model.KodAgensi u where u.kod in('1306') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUrusan> getKodUrusan() throws NamingException {
        String query = "Select ku from etanah.model.KodUrusan ku WHERE ku.aktif = 'Y' AND ku.jabatan.kod = '9' order by ku.nama ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

//        LOG.info("Listing all Kod Urusan...");
        return q.list();
    }
}

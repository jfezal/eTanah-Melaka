/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkuanTerimaDepositDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.EnkuiriPeminjamDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.LelonganSukuDAO;
import etanah.dao.MenuCapaianDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PermohonanAsalDAO;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilPihakDAO;
import etanah.model.Akaun;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.Enkuiri;
import etanah.model.EnkuiriPeminjam;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KandunganFolder;
import etanah.model.Kehadiran;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Lelongan;
import etanah.model.LelonganSuku;
import etanah.model.Notis;
import etanah.model.Pembida;
import etanah.model.Pemohon;
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.FasaPermohonanLog;
import etanah.model.Hakmilik;
import etanah.model.MenuCapaian;
import etanah.model.Peguam;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanCarian;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.SytJuruLelong;
import etanah.model.WakilPihak;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mdizzat.mashrom
 */
public class LelongService {

    private static final Logger LOG = Logger.getLogger(LelongService.class);
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    MenuCapaian menuCapaian;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    JuruLelong jurulelong;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PembidaDAO pembidaDAO;
    @Inject
    private WakilPihakDAO wakilPihakDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatTambDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    AkuanTerimaDepositDAO akuanTerimaDepositDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    PermohonanAsalDAO permohonanAsalDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    PermohonanHubunganDAO permohonanHubunganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ReportUtil reportUtil;
    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenService dokumenService;
    @Inject
    EnkuiriService enService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    LelonganSukuDAO lelonganSukuDAO;
    @Inject
    EnkuiriPeminjamDAO enkuiriPeminjamDAO;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanLogDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    MenuCapaianDAO menuCapaianDAO;
    private static final Logger LOGGER = Logger.getLogger(PermohonanCarianService.class);
    private boolean isDebug = LOGGER.isDebugEnabled();

    public List<FasaPermohonan> CheckWRKodStatus(Permohonan permohon) {

        String idmohon = permohon.getIdPermohonan();
        String kodKeputusan = "WR";
        String idAliran = "mohonTangguh";

        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.FasaPermohonan p where 1=1 ");

        if (StringUtils.isNotBlank(idmohon)) {
            query.append(" and p.permohonan.idPermohonan like :idmohon");
        }
        if (StringUtils.isNotBlank(kodKeputusan)) {
            query.append(" and p.keputusan.kod like :kodKeputusan");
        }

        if (StringUtils.isNotBlank(idAliran)) {
            query.append(" and p.idAliran like :idAliran");
        }
        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(idmohon)) {
            q.setString("idmohon", idmohon.toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(kodKeputusan)) {
            q.setString("kodKeputusan", kodKeputusan + "%");
        }
        if (StringUtils.isNotBlank(idAliran)) {
            q.setString("idAliran", idAliran + "%");
        }
        return q.list();
    }

    public List<FasaPermohonan> CheckXNKodStatus(Permohonan permohon) {

        String idmohon = permohon.getIdPermohonan();
        String kodKeputusan = "WR";
        String idAliran = "mohonTangguh";

        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.FasaPermohonan p where 1=1 ");

        if (StringUtils.isNotBlank(idmohon)) {
            query.append(" and p.permohonan.idPermohonan like :idmohon");
        }
        if (StringUtils.isNotBlank(kodKeputusan)) {
            query.append(" and p.keputusan.kod like :kodKeputusan");
        }

        if (StringUtils.isNotBlank(idAliran)) {
            query.append(" and p.idAliran like :idAliran");
        }
        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(idmohon)) {
            q.setString("idmohon", idmohon.toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(kodKeputusan)) {
            q.setString("kodKeputusan", kodKeputusan + "%");
        }
        if (StringUtils.isNotBlank(idAliran)) {
            q.setString("idAliran", idAliran + "%");
        }
        return q.list();
    }

    public List<FasaPermohonan> CheckIdAliran(Permohonan permohon) {

        String idmohon = permohon.getIdPermohonan();
        String idAliran = "cetak16H";
        String idAliran2 = "kmskJurulelong";

        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.FasaPermohonan p where 1=1 ");

        if (StringUtils.isNotBlank(idmohon)) {
            query.append(" and p.permohonan.idPermohonan like :idmohon");
        }

        if (StringUtils.isNotBlank(idAliran)) {
            query.append(" and p.idAliran like :idAliran");
        }

        if (StringUtils.isNotBlank(idAliran2)) {
            query.append(" or p.idAliran like :idAliran2");
        }
        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(idmohon)) {
            q.setString("idmohon", idmohon.toUpperCase() + "%");
        }

        if (StringUtils.isNotBlank(idAliran)) {
            q.setString("idAliran", idAliran + "%");
        }
        if (StringUtils.isNotBlank(idAliran)) {
            q.setString("idAliran2", idAliran2 + "%");
        }
        return q.list();
    }

    public List<FasaPermohonan> Check16H(Permohonan permohon) {

        String idPermohonan = permohon.getIdPermohonan();

        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.FasaPermohonan p where p.permohonan.idPermohonan=:idPermohonan and p.idAliran IN ('rekodBidaanJLB', 'rekodBidaan')");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<FasaPermohonan> CheckSemakRekodPenghantaran(String idPermohonan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.FasaPermohonan p where p.permohonan.idPermohonan=:idPermohonan and p.idAliran= 'semakRekodPenghantaran'");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<FasaPermohonan> checkSediaSuratBakiBidaan(Permohonan permohon) {

        String idPermohonan = permohon.getIdPermohonan();

        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.FasaPermohonan p where p.permohonan.idPermohonan=:idPermohonan and p.idAliran='sediaSuratBakiBidaan'");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<FasaPermohonan> checkSemakLantikJurulelong(Permohonan permohon) {

        String idPermohonan = permohon.getIdPermohonan();

        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.FasaPermohonan p where p.permohonan.idPermohonan=:idPermohonan and p.idAliran= 'semak16HLantikJurulelong'");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public Lelongan findbyIdLelong(Permohonan permohon) {
        String idmohon = permohon.getIdPermohonan();
        String kodStatusLelongan = "AK";

        StringBuilder query = new StringBuilder();

        query.append("Select p FROM etanah.model.Lelongan p WHERE 1=1");

        if (StringUtils.isNotBlank(idmohon)) {
            query.append(" and p.permohonan.idPermohonan like :idmohon");
        }
        if (StringUtils.isNotBlank(kodStatusLelongan)) {
            query.append(" and p.keputusan.kod like :kodStatusLelongan");
        }

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(idmohon)) {
            q.setString("idmohon", idmohon.toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(kodStatusLelongan)) {
            q.setString("kodStatusLelongan", kodStatusLelongan + "%");
        }

        return (Lelongan) q.uniqueResult();
    }

    public List<Lelongan> checkTangguhBatalPermohonan(String idPermohonan) {
        String query = "Select l FROM etanah.model.Lelongan l, etanah.model.FasaPermohonan f where l.permohonan.idPermohonan= :idPermohonan AND "
                + "f.permohonan.idPermohonan= :idPermohonan and f.idAliran IN ('kmskJurulelong','rekodBidaanJLB','rekodBidaan','cetak16H', 'semakPengisytiharan', 'sediaPengisytiharan', 'semak16HLantikJurulelong') and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> checkTangguhBatalPermohonan2(String idPermohonan) {
        String query = "Select l FROM etanah.model.Lelongan l, etanah.model.FasaPermohonan f where l.permohonan.idPermohonan= :idPermohonan AND "
                + "(l.tarikhLelong - sysdate )>=0 and f.permohonan.idPermohonan= :idPermohonan and f.idAliran IN ('kmskJurulelong','rekodBidaanJLB','rekodBidaan','cetak16H', 'semakPengisytiharan', 'sediaPengisytiharan', 'rekodPenghantaran16H', 'semak16HLantikJurulelong', 'sedia16H', 'bayaranPerintah', 'kpsnSiasatan') and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PenghantarNotis> findSenaraiPenghantarNotisByNoKpAndCaw(String jenisKp, String noKp, String kodCawangan, String aktif) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.kodJenisPengenalan.kod = :jenis_kp AND p.noPengenalan = :no_kp AND p.cawangan.kod = :kod_cawangan AND p.aktif = :aktif order by p.idPenghantarNotis asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("jenis_kp", jenisKp);
        q.setString("no_kp", noKp);
        q.setString("kod_cawangan", kodCawangan);
        q.setString("aktif", aktif);
        return q.list();
    }

    public Peguam checkPeguam(String noPengenalan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Peguam p where p.noPengenalan= :noPengenalan");
        q.setParameter("noPengenalan", noPengenalan);
        return (Peguam) q.uniqueResult();
    }

    public Lelongan findbyIdLelongAK(Permohonan permohonan) {
        String idPermohonan = permohonan.getIdPermohonan();
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan= :idPermohonan AND l.kodStatusLelongan.kod='AK'");
        q.setParameter("idPermohonan", idPermohonan);

        return (Lelongan) q.uniqueResult();
    }

    public List<Lelongan> findbyIdLelongAKList(Permohonan permohonan) {
        String idPermohonan = permohonan.getIdPermohonan();
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan= :idPermohonan AND l.kodStatusLelongan.kod='AK'");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<Permohonan> findPermohonanSblmByIdSblmAK(String idPermohonan) {
//        String idPermohonan = permohonan.getIdPermohonan();
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select l FROM etanah.model.Lelongan l WHERE l.permohonanSebelum.idPermohonan = :idPermohonan AND l.status='AK'");
        q.setParameter("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<Pembida> checkpihak(Long idLelong, String noPengenalan) {
        String query = "Select p FROM etanah.model.Pembida p WHERE p.lelong.idLelong= :idLelong AND p.pihak.noPengenalan= :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idLelong", idLelong);
        q.setParameter("noPengenalan", noPengenalan);
        return q.list();
    }

    public SytJuruLelong findByNoSykt(String noPengenalan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("Select s FROM etanah.model.SytJuruLelong s WHERE s.noPengenalan = :noPengenalan");
        q.setParameter("noPengenalan", noPengenalan);
        return (SytJuruLelong) q.uniqueResult();
    }

    public Lelongan findByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Lelongan p WHERE p.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);

        return (Lelongan) q.uniqueResult();
    }

    public Permohonan findPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);

        return (Permohonan) q.uniqueResult();
    }

    public List<JuruLelong> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JuruLelong p where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }

    public List<SytJuruLelong> findAllSyt() {
        StringBuilder query = new StringBuilder();
        query.append("select s from etanah.model.SytJuruLelong s where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }

    public List<FasaPermohonan> checkStatussemak16HLantikJurulelong(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select fp from etanah.model.FasaPermohonan fp WHERE fp.permohonan.idPermohonan= :idPermohonan AND fp.keputusan.kod='JL'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<JuruLelong> findJuruLelong() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JuruLelong p where p.aktif ='Y' order by p.idPengguna.nama");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }

    public List<JuruLelong> findJuruLelongByNoPengenalan(String noPengenalan) {
//        StringBuilder query = new StringBuilder();
//        query.append("select p from etanah.model.JuruLelong p where p.noPengenalan= :noPengenalan order by p.idPengguna.nama");
//        Query q = sessionProvider.get().createQuery(query.toString());
        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.JuruLelong p where p.noPengenalan= :noPengenalan order by p.idPengguna.nama");
        q.setParameter("noPengenalan", noPengenalan);
        return q.list();
    }

    public List<Permohonan> validatorRekodPenghantaran(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getCatatan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Permohonan p, etanah.model.FasaPermohonan fp WHERE p.idPermohonan = :idPermohonan AND fp.permohonan.idPermohonan"
                + "=p.idPermohonan AND fp.keputusan.kod IN ('EM','LM')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permohonan searchIdMohonByKodCawangan(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.Permohonan p where p.idPermohonan= :idPermohonan and p.cawangan.kod = :kod");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("kod", kod);

        return (Permohonan) q.uniqueResult();
    }

    public List<JuruLelong> findPenyerahByParam(String nama, String id) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JuruLelong p where 1=1");

        if (StringUtils.isNotBlank(nama)) {
            query.append(" and p.nama like :nama");
        }
        if (StringUtils.isNotBlank(id)) {
            query.append(" and p.idJlb like :id");
        }
//        query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(nama)) {
            q.setString("nama", nama.toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(id)) {
            q.setString("id", id + "%");
        }
        return q.list();
    }

    public List<SytJuruLelong> findSytPenyerahByParam(String nama, String id) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.SytJuruLelong p where 1=1");

        if (StringUtils.isNotBlank(nama)) {
            query.append(" and p.nama like :nama");
        }
        if (StringUtils.isNotBlank(id)) {
            query.append(" and p.idSytJlb like :id");
        }
//        query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(nama)) {
            //camne nk buat ignoreCase???
            q.setString("nama", "%" + nama + "%");
        }
        if (StringUtils.isNotBlank(id)) {
            q.setString("id", id + "%");
        }
        return q.list();
    }

    public List<Permohonan> getSenaraiPermohonan(String idPermohonan, String kodCaw, int start, int max) {

        String q = "";

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select m from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            query.setString("idPermohonan", "%" + "AUC" + "%" + "%" + idPermohonan);
        }
        query.setFirstResult(start);
        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

    public Long getTotalRecordFolderAction(String idPermohonan, String kodCaw) {
        String q = "";
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select count(m) from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            query.setString("idPermohonan", "%" + "AUC" + "%" + "%" + idPermohonan);
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return (Long) query.iterate().next();
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian(String idCarian, String kodCaw, int start, int max) {
        if (isDebug) {
            LOGGER.debug("from record [" + start + "]");
            LOGGER.debug("to record [" + max + "]");
        }

        String q = "Select m from etanah.model.PermohonanCarian m where m.idCarian like :idCarian";
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q).setString("idCarian", "%" + "AUC" + "%" + "%" + idCarian + "%");
        query.setFirstResult(start);
        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

    public List<Permohonan> getSenaraiPermohonan2(String idPermohonan, String kodCaw) {

        String q = "";

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select m from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {

            query.setString("idPermohonan", "%" + idPermohonan + "%");
        }
//        query.setFirstResult(start);
//        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

    public Long getTotalRecordFolderAction2(String idPermohonan, String kodCaw) {
        String q = "";
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select count(m) from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            query.setString("idPermohonan", "%" + idPermohonan + "%");
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return (Long) query.iterate().next();
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian2(String idCarian, String kodCaw) {
//        if (isDebug) {
//            LOGGER.debug("from record [" + start + "]");
//            LOGGER.debug("to record [" + max + "]");
//        }

        String q = "Select m from etanah.model.PermohonanCarian m where m.idCarian like :idCarian";
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q).setString("idCarian", "%" + idCarian + "%");
//        query.setFirstResult(start);
//        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

    public List<Pembida> getListPembida(Long idLelong) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT P FROM etanah.model.Pembida P WHERE P.lelong.idLelong = :idLelong order by P.idPembida asc");
        query.setParameter("idLelong", idLelong);
        return query.list();
    }

    public List<DokumenKewangan> checkBayarPerintah(String noRujukan) {
        Session ses = sessionProvider.get();
        Query query = ses.createQuery("SELECT D FROM etanah.model.DokumenKewangan D WHERE D.noRujukan = :noRujukan");
        query.setParameter("noRujukan", noRujukan);
        return query.list();
    }

    @Transactional
    public Kehadiran saveOrUpdate(Kehadiran j) {
        return kehadiranDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveKehadiran(Kehadiran hadir, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        hadir.setInfoAudit(ia);
        kehadiranDAO.saveOrUpdate(hadir).getIdKehadiran();
    }

    @Transactional
    public void saveLelonganSuku(LelonganSuku ls) {
        lelonganSukuDAO.saveOrUpdate(ls);
    }

    @Transactional
    public void saveOrUpdate(EnkuiriPeminjam ep) {
        enkuiriPeminjamDAO.saveOrUpdate(ep);
    }

    @Transactional
    public void saveOrUpdate(Enkuiri en) {
        enkuiriDAO.saveOrUpdate(en);
    }

    @Transactional
    public void saveOrUpdate(KandunganFolder en) {
        kandunganFolderDAO.saveOrUpdate(en);
    }

    @Transactional
    public void saveOrUpdate(PenghantarNotis en) {
        penghantarNotisDAO.saveOrUpdate(en);
    }

    @Transactional
    public void deletePenghantarNotis(PenghantarNotis en) {
        penghantarNotisDAO.delete(en);
    }

    public void saveOrUpdate(PermohonanAsal en) {
        permohonanAsalDAO.saveOrUpdate(en);
    }

    @Transactional
    public void saveOrUpdate(PermohonanHubungan ph) {
        permohonanHubunganDAO.saveOrUpdate(ph);
    }

    @Transactional
    public void saveOrUpdate(PermohonanTuntutanKos ptk) {
        permohonanTuntutanKosDAO.saveOrUpdate(ptk);
    }

    @Transactional
    public void saveOrUpdate(PermohonanPihak pp) {
        permohonanPihakDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdate(AkuanTerimaDeposit pp) {
        akuanTerimaDepositDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdate(Pihak pp) {
        pihakDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdate(PihakAlamatTamb pp) {
        pihakAlamatTambDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void deleteAll(Kehadiran j) {
        kehadiranDAO.delete(j);
    }

    @Transactional
    public void deletePihak(Pihak j) {
        pihakDAO.delete(j);
    }

    @Transactional
    public void deletePihak(Pembida pem) {
        pembidaDAO.delete(pem);
    }

    @Transactional
    public void deleteWakilPihak(WakilPihak wakilPihak) {
        wakilPihakDAO.delete(wakilPihak);
    }

    @Transactional
    public void delete(LelonganSuku ls) {
        lelonganSukuDAO.delete(ls);
    }

    @Transactional
    public void delete(EnkuiriPeminjam ep) {
        enkuiriPeminjamDAO.delete(ep);
    }

    @Transactional
    public void delete(KandunganFolder kf) {
        kandunganFolderDAO.delete(kf);
    }

    @Transactional
    public void delete(PermohonanAtasPerserahan pap) {
        permohonanAtasPerserahanDAO.delete(pap);
    }

    @Transactional
    public void saveOrUpdate(JuruLelong j) {
        jurulelongDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveOrUpdate(Lelongan lelong) {
        lelonganDAO.saveOrUpdate(lelong);
    }

    @Transactional
    public void delete(JuruLelong j) {
        jurulelongDAO.delete(j);
    }

    @Transactional
    public void delete(AkuanTerimaDeposit aa) {
        akuanTerimaDepositDAO.delete(aa);
    }

    @Transactional
    public void delete(Lelongan lelong) {
        lelonganDAO.delete(lelong);
    }

    @Transactional
    public void delete(Dokumen d) {
        dokumenDAO.delete(d);
    }

    @Transactional
    public void saveOrUpdatee(Lelongan lelong) {
        lelonganDAO.saveOrUpdate(lelong);
    }

    @Transactional
    public void saveOrUpdatDokumen(Dokumen dokumen) {
        dokumenDAO.saveOrUpdate(dokumen);
    }

    @Transactional
    public void saveOrUpdatDokumen(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.saveOrUpdate(hp);
    }

    @Transactional
    public void saveOrUpdate(Permohonan pp) {
        permohonanDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdate(PermohonanAtasPerserahan pp) {
        permohonanAtasPerserahanDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveUpdate2(FasaPermohonan j) {
        fasaPermohonanDAO.saveOrUpdate(j);
    }

    @Transactional
    public void delete(FasaPermohonan j) {
        fasaPermohonanDAO.delete(j);
    }

    public void deleteEnkuiri(Enkuiri ee) {
        enkuiriDAO.delete(ee);
    }

    public void deleteLelong(Lelongan ll) {
        lelonganDAO.delete(ll);
        ;
    }

    @Transactional
    public void deletetest(FasaPermohonanLog l, FasaPermohonan j) {
        fasaPermohonanLogDAO.delete(l);
        fasaPermohonanDAO.delete(j);

    }

    @Transactional
    public void deleteMohonFasa(FasaPermohonanLog l) {
        fasaPermohonanLogDAO.delete(l);
    }

    @Transactional
    public void delete(PermohonanTuntutanKos j) {
        permohonanTuntutanKosDAO.delete(j);
    }

    @Transactional
    public void saveOrUpdate(Notis j) {
        notisDAO.saveOrUpdate(j);
    }

    @Transactional
    public void deleteAll(Notis j) {
        notisDAO.delete(j);
    }

    @Transactional
    public void deleteAll(PermohonanPihak j) {
        permohonanPihakDAO.delete(j);
    }

    public List<PermohonanHubungan> getByIdSumber(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ph FROM etanah.model.PermohonanHubungan ph WHERE ph.permohonanSumber.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<EnkuiriPeminjam> getPeminjam(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ep FROM etanah.model.EnkuiriPeminjam ep WHERE ep.enkuiri.idEnkuiri = :idEnkuiri order by ep.idEnkuiriPeminjam asc");
        q.setParameter("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Kehadiran> getSenaraiwakil(Long idKehadiran) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Kehadiran m WHERE m.idKehadiran = :id_hadir");
        q.setParameter("id_hadir", idKehadiran);
        return q.list();
    }

    public List<Kehadiran> getwakilA(String idWakil) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Kehadiran m WHERE m.wakilPihak.idWakil= :id_wakil");
        q.setParameter("id_wakil", idWakil);
        return q.list();
    }

    public List<Permohonan> getListPPBLPPTL(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan =:idPermohonan and m.kodUrusan.kod in ('PPBL','PPTL') and m.status in ('AK','TA')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getListPPTL(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan =:idPermohonan and m.kodUrusan.kod in ('PPTL') and m.status not in ('T','L')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getListPPBL(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan =:idPermohonan and m.kodUrusan.kod in ('PPBL') and m.status not in ('T','L')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getHistory2(String nomborRujukan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.idPermohonan =:nomborRujukan");
        q.setParameter("nomborRujukan", nomborRujukan);
        return q.list();
    }

    public List<Enkuiri> getEnkuiri(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Enkuiri m WHERE m.permohonan.idPermohonan= :idmohon and m.status.kod ='AK'");
        q.setParameter("idmohon", idmohon);
        return q.list();
    }

    public List<Enkuiri> getEnkuiriNotAK(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Enkuiri m WHERE m.permohonan.idPermohonan= :idmohon and m.status.kod not in ('AK') order by m.idEnkuiri desc");
        q.setParameter("idmohon", idmohon);
        return q.list();
    }

    public List<Enkuiri> getSejarahSiasatan(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Enkuiri m WHERE m.permohonan.idPermohonan= :idmohon");
        q.setParameter("idmohon", idmohon);
        return q.list();
    }

    public List<Enkuiri> getAllDesc(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e WHERE e.permohonan.idPermohonan= :idmohon order by e.bilanganKes desc");
        q.setParameter("idmohon", idmohon);
        return q.list();
    }

    public Enkuiri findEnkuiriId(String idPermohonan) {

        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan=:idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public List<Enkuiri> getEnkuiriList(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Enkuiri m WHERE m.permohonan.idPermohonan= :idmohon and m.status.kod ='AK' order by m.idEnkuiri asc");
        q.setParameter("idmohon", idmohon);
        return q.list();
    }

    public List<Kehadiran> getListWakil(Long idPihak, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Kehadiran m WHERE m.hakmilikPihakBerkepentingan.pihak.idPihak = :idPihak and m.enkuiri.permohonan.idPermohonan = :idPermohonan and m.enkuiri.status.kod ='AK'");
        q.setLong("idPihak", idPihak);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pengguna> getListPptlelong() {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m.nama FROM etanah.model.Pengguna m WHERE m.perananUtama.kod= 'pptlelong' AND m.status.kod='A'");
        return q.list();

    }

    public List<Kehadiran> getListKehadiran(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Kehadiran m WHERE m.enkuiri.permohonan.idPermohonan = :idPermohonan order by idKehadiran asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<JuruLelong> getSenaraiJuruLelong(Long idJlb) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT j FROM etanah.model.JuruLelong j WHERE j.idJlb = :id_jlb");
        q.setParameter("id_jlb", idJlb);
        return q.list();
    }

    public List<JuruLelong> findPemohonByIdPermohonan(Long idJlb) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select j from etanah.model.JuruLelong j where j.idJlb = :id_jlb");
        q.setParameter("id_jlb", idJlb);
        return q.list();
    }

    public List<Lelongan> getIDjuruLelong(Long idjlb) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.jurulelong.idJlb= :id_jlb");
        q.setParameter("id_jlb", idjlb);
        return q.list();
    }

    public List<Lelongan> lelongList(Long idlelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.idLelong= :id_lelong");
        q.setParameter("id_lelong", idlelong);
        return q.list();
    }

    public List<Lelongan> getTG(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.permohonan.idPermohonan= :idPermohonan AND m.kodStatusLelongan.kod= 'TG'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Enkuiri> getTGEnkuiri(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e WHERE e.permohonan.idPermohonan= :idPermohonan AND e.status.kod= 'TG'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganASC(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.kodStatusLelongan.kod in ('AK','AP','AT','TB','TG','RM','BM') and l.permohonan.idPermohonan =:idPermohonan order by l.idLelong asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganDesc(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.enkuiri.idEnkuiri = :idEnkuiri order by l.bil desc");
        q.setParameter("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Lelongan> getAllLelongan(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.enkuiri.idEnkuiri = :idEnkuiri");
//        Query q = session.createQuery("SELECT P.pihak.nama FROM etanah.model.Lelongan l, etanah.model.Pembida P  WHERE l.enkuiri.idEnkuiri = :idEnkuiri AND l.idLelong= P.lelong.idLelong");
        q.setParameter("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Lelongan> getAllLelongan2(String idPermohonan) {

        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l, etanah.model.Enkuiri e WHERE l.enkuiri.idEnkuiri = e.idEnkuiri AND l.permohonan.idPermohonan= :idPermohonan");

//        Query q = session.createQuery("SELECT P.pihak.nama FROM etanah.model.Lelongan l, etanah.model.Pembida P  WHERE l.enkuiri.idEnkuiri = :idEnkuiri");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Kehadiran> getAllKehadiran(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("Select p FROM etanah.model.Kehadiran p WHERE p.enkuiri.idEnkuiri = :idEnkuiri");
        q.setParameter("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Notis> getNotisNot16H(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("Select p FROM etanah.model.Notis p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.kodNotis!='16H'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Notis> getNotis16H(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("Select p FROM etanah.model.Notis p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.kodNotis='16H'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganALLDESC(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan order by l.idLelong desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganDESC(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan order by l.bil desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganALLDESC2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan and l.kodStatusLelongan.kod = 'AK' order by l.idLelong desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganTA(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan and l.kodStatusLelongan.kod = 'TA' order by l.idLelong desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Enkuiri> getEnkuiriTA(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e WHERE e.permohonan.idPermohonan =:idPermohonan and e.status.kod = 'TA'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Enkuiri getEnkuiriTA1(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e WHERE e.permohonan.idPermohonan =:idPermohonan and e.status.kod = 'TA'");
        q.setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public List<Lelongan> getLelonganForFindAllPembida(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan");
//          Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLelonganForFindPembida(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan AND l.kodStatusLelongan.kod= 'AK'");
//          Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan =:idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> checkIdMohonSebelum(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Permohonan p WHERE p.idPermohonan =:idPermohonan and p.permohonanSebelum.idPermohonan!=null");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> checkIdMohonSebelum2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Permohonan p WHERE p.permohonanSebelum.idPermohonan =:idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getMohonSDtoAK(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Permohonan p WHERE p.idPermohonan =:idPermohonan and p.status = 'SD'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganNotInAK(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.kodStatusLelongan.kod not in ('AK') and l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Lelongan> getLeloganNotInAK2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.kodStatusLelongan.kod not in ('AK') and l.permohonan.idPermohonan = :idPermohonan order by l.idLelong desc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getAllSejarah(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan order by l.idLelong desc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLeloganNotInRM(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.kodStatusLelongan.kod not in ('RM') and l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Pemohon> getPemohon(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Pemohon m WHERE m.permohonan.idPermohonan = :permohonan");
        q.setParameter("permohonan", idMohon);
        return q.list();
    }

    public List<Notis> getSenaraiNotis(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :permohonan");
        q.setParameter("permohonan", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> cekPermohonan(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<Notis> getNotis(long idNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.idNotis = :id_notis");
        q.setParameter("id_notis", idNotis);
        return q.list();
    }

    public List<Notis> getNotisSSLList(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Notis getNotisSSL(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return (Notis) q.uniqueResult();
    }

    public List<Pembida> getListPembida(long idPembida) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Pembida m WHERE m.idPembida = :idPembida");
        q.setParameter("idPembida", idPembida);
        return q.list();
    }

    public List<Pembida> getListPembidaByIdPihak(long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Pembida m WHERE m.pihak.idPihak = :idPihak");
        q.setParameter("idPihak", idPihak);
        return q.list();
    }

    public List<Pembida> getListPihakIC(String noPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT pem FROM etanah.model.Pembida pem WHERE pem.pihak.noPengenalan = :noPengenalan AND pem.kodStsPembida.kod='SH'");
        q.setParameter("noPengenalan", noPengenalan);
        return q.list();
    }

    public List<Pembida> checkPembida(String noPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT pem FROM etanah.model.Pembida pem WHERE pem.pihak.noPengenalan = :noPengenalan AND pem.kodStsPembida.kod='SH' AND (sysdate - pem.tarikhAkhir)>=0");
        q.setParameter("noPengenalan", noPengenalan);
        return q.list();
    }

    public List<Pembida> getListPembidaByIdLelong(long idLelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Pembida m WHERE m.lelong.idLelong = :idLelong");
        q.setParameter("idLelong", idLelong);
        return q.list();
    }

    public List<Pembida> checkPembidaStatus(long idLelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Pembida p where p.lelong.idLelong= :idLelong AND p.kodStsPembida.kod IN ('BJ','AT')");
        q.setParameter("idLelong", idLelong);
        return q.list();
    }

    public List<Notis> getListNotis(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis order by m.hakmilikPihakBerkepentingan.jenis.kod asc");
        //Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getListNotisBaru(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis AND m.kodCaraPenghantaran is null order by m.hakmilikPihakBerkepentingan.jenis.kod asc");
        //Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getListNotisSSLNG(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.Notis p where p.permohonan.idPermohonan = :id_mohon AND p.kodNotis.kod IN ('SSL','NG') order by p.hakmilikPihakBerkepentingan.jenis.kod asc");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<Notis> getListNotis2(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getListNotisAll(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<Notis> getListNotisTS(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = 'SSL' and m.kodStatusTerima.kod = 'XT'");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihak(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon order by m.jenis.kod asc");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByPihak(Long idPihak, String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.pihak.idPihak = :idPihak and m.permohonan.idPermohonan = :idMohon order by m.hakmilik.idHakmilik asc");
        q.setParameter("idPihak", idPihak);
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakByHakmilikAktif(Long idPihak, String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m, etanah.model.Hakmilik hm  WHERE m.hakmilik.idHakmilik = hm.idHakmilik "
                + "and m.pihak.idPihak = :idPihak and m.permohonan.idPermohonan = :idMohon and hm.kodStatusHakmilik.kod not in ('B')");
        q.setParameter("idPihak", idPihak);
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<Pihak> getSenaraiPmohonPihakDistinctPG(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT m.pihak FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon and m.jenis.kod in('PG','PGA')");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakPG(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon and m.jenis.kod ='PG'");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakPM(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon and m.jenis.kod ='PM'");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanPihak> getSenaraiPmohonPihakPenting(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon and m.jenis.kod not in ('PG') ");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<HakmilikPermohonan> getPermohonan(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :id_hakmilik and m.permohonan.kodUrusan.kod = 'PJPT' and m.permohonan.status = 'TA' order by m.infoAudit.tarikhMasuk desc");
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> getPermohonanAtasPerserahan(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ph FROM etanah.model.PermohonanAtasPerserahan ph WHERE ph.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> getIdPerserahan(long idUrusan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ph FROM etanah.model.PermohonanAtasPerserahan ph WHERE ph.urusan.idUrusan = :idUrusan");
        q.setLong("idUrusan", idUrusan);
        return q.list();
    }

    public List<HakmilikPermohonan> getPermohonanByIdmohon(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<HakmilikPermohonan> getPermohonanByIdmohonSBLM(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik and m.permohonan.kodUrusan.kod = 'PPTL' and m.permohonan.idPermohonan not in( :idPermohonan )");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getPermohonanByIdmohonPPJP(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik and m.permohonan.kodUrusan.kod = 'PPJP' and m.permohonan.idPermohonan not in( :idPermohonan )");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonan(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik and m.permohonan.idPermohonan =:idPermohonan");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> listUrusanValidator(List<String> list) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik in (:list) and m.permohonan.kodUrusan.kod in ('PPJP','PPTL') "
                + "and m.permohonan.status in ('AK','TA')");
        q.setParameterList("list", list);
        return q.list();
    }

    public List<PermohonanPihak> getPemohonByIdmohon(List idMohon, List idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan in (:idMohon) and m.hakmilik.idHakmilik in (:idhm)");
        q.setParameterList("idMohon", idMohon);
         q.setParameterList("idhm", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> getHakmilikUrusanByIdmohon(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikUrusan m WHERE m.idPerserahan = :idMohon and m.kodUrusan.kod in ('GD','GDPJ','GDPJK','GDWM','PMG') order by m.hakmilik.idHakmilik asc ");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<HakmilikUrusan> getHakmilikUrusanPJT(String idPerserahan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikUrusan m WHERE m.idPerserahan = :idPerserahan");
        q.setString("idPerserahan", idPerserahan);
        return q.list();
    }

    public List<String> listMohonPihakHakmilikPihak(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("select mp from etanah.model.PermohonanPihak mp, etanah.model.HakmilikPihakBerkepentingan hp where mp.hakmilik.idHakmilik and hp.hakmilik.idHakmilik =:idHakmilik");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<String> listHakmilikUrusan2(List<String> list) {
        int size = list.size();
        LOG.info("size : " + size);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select hu.idPerserahan from "
                + "etanah.model.HakmilikUrusan hu where hu.hakmilik.idHakmilik in (:list) and hu.kodUrusan.kod in ('GD','GDPJ','GDPJK','GDWM') "
                + "group by hu.idPerserahan HAVING count(hu.idPerserahan) =:size");
        q.setParameterList("list", list);
        q.setInteger("size", size);
        return q.list();
    }

    public List<String> listHakmilikUrusanPMG(List<String> list) {
        int size = list.size();
        LOG.info("size : " + size);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select hu.idPerserahan from "
                + "etanah.model.HakmilikUrusan hu where hu.hakmilik.idHakmilik in (:list) and hu.kodUrusan.kod in ('PMG') and hu.aktif ='Y' "
                + "group by hu.idPerserahan HAVING count(hu.idPerserahan) =:size");
        q.setParameterList("list", list);
        q.setInteger("size", size);
        return q.list();
    }

    public List<String> listHakmilikUrusanPMG2(List<String> list, int size) {

        LOG.info("size : " + size);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select h.idPerserahan from etanah.model.HakmilikUrusan h"
                + " where h.idPerserahan in (:list) and h.kodUrusan.kod in ('PMG') "
                + " group by h.idPerserahan HAVING count(h.idPerserahan) =:size");
//                + "and h.aktif ='Y' group by h.idPerserahan HAVING count(h.idPerserahan) =:size");
        q.setParameterList("list", list);
        q.setInteger("size", size);
        return q.list();
    }

    public List<String> listHakmilikUrusan3(List<String> list, int size) {

        LOG.info("size : " + size);
        LOG.info("list : " + list);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select h.idPerserahan from etanah.model.HakmilikUrusan h"
                //                + " where h.hakmilik.idHakmilik in (:list) and h.kodUrusan.kod in ('GD','GDPJ','GDPJK','GDWM') "
                + " where h.idPerserahan in (:list) and h.kodUrusan.kod in ('GD','GDPJ','GDPJK','GDWM') "
                + "group by h.idPerserahan HAVING count(h.idPerserahan) >=:size");//edit
//                + "and h.aktif ='Y' group by h.idPerserahan HAVING count(h.idPerserahan) =:size");
        q.setParameterList("list", list);
        q.setInteger("size", size);
        return q.list();
    }

    public List<String> listHakmilikPermohonan2(List<String> list, String idPermohonan) {
        int size = list.size();
        LOG.info("size : " + size);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select hu.permohonan.idPermohonan from "
                + "etanah.model.HakmilikPermohonan hu where hu.hakmilik.idHakmilik in (:list) and hu.permohonan.idPermohonan not in (:idPermohonan) and "
                + "hu.permohonan.kodUrusan.kod in ('PPJP','PPTL','PJTA') and hu.permohonan.status in ('AK','TA') "
                + "group by hu.permohonan.idPermohonan HAVING count(hu.permohonan.idPermohonan) =:size");
        q.setParameterList("list", list);
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("size", size);
        return q.list();
    }

    public List<String> listHakmilikPermohonan3(List<String> list, int size, String idPermohonan) {

        LOG.info("size : " + size);
        Session session = sessionProvider.get();
        Query q = session.createQuery("select h.permohonan.idPermohonan from etanah.model.HakmilikPermohonan h"
                + " where h.permohonan.idPermohonan in (:list) and h.permohonan.kodUrusan.kod in ('PPJP','PPTL','PJTA')and h.permohonan.idPermohonan not in (:idPermohonan) "
                + "and h.permohonan.status in ('AK','TA') group by h.permohonan.idPermohonan HAVING count(h.permohonan.idPermohonan) =:size");
        q.setParameterList("list", list);
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("size", size);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SSL' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenSSLM(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SSLM' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenTG(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'TG' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenT(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'STP' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenSB(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SB' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListALLDokumen(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenNG(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod in ('NG')");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenLEL(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'LEL' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen2A(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = '2A' ");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen16H(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = '16H'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenSNG(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SNG'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen16H1(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = '16H1'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenBPL(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'BPL'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<HakmilikUrusan> checkPMKMH(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT h FROM etanah.model.HakmilikUrusan h WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS') AND h.aktif='Y'");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanRujukanLuar> checkPermohonanRujukanLuar(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT prl FROM etanah.model.PermohonanRujukanLuar prl WHERE prl.hakmilik.idHakmilik= :idHakmilik");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> checkDaftarHakMilikUrusan(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT h FROM etanah.model.HakmilikUrusan h WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Permohonan> checkUrusanMohon(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT h FROM etanah.model.Permohonan h WHERE h.idPermohonan= :idPermohonan AND h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> checkHakMilikUrusan(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT h FROM etanah.model.HakmilikUrusan h WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenTGL(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'TGL'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenTTL(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'TTL'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenI(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'I'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenM(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'M'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenMEMO(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'MEMO'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenKM(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'KM'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenIystiharJual(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'PJ'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenWarta(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'SRW'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentingan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' order by m.hakmilik.idHakmilik asc,m.jenis.kod asc ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakALL(String idPermohonan, List<String> list) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.idHakmilik in (:list) and m.hakmilik = hp.hakmilik and m.aktif = 'Y' order by m.jenis.kod asc");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameterList("list", list);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getlistPihak(String idPermohonan, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.pihak.idPihak =:idPihak");
        q.setParameter("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

//        public List<HakmilikPihakBerkepentingan> getlistAlamatPihak(String idPermohonan, Long idPihak) {
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.PermohonanPihak pp WHERE pp.permohonan.idPermohonan = :idPermohonan and pp.hakmilik = m.hakmilik and m.pihak.idPihak =:idPihak");
//        q.setParameter("idPermohonan", idPermohonan);
//        q.setLong("idPihak", idPihak);
//        return q.list();
//    }
    public List<HakmilikPihakBerkepentingan> getPenggadaiJe(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT m.pihak.idPihak FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.jenis.kod = 'PG' ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pihak> getListPihak(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT DISTINCT m.pihak FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.jenis.kod = 'PG' ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPenggadaiJeLe(String idPermohonan, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.jenis.kod = 'PG' and m.pihak.idPihak = :idPihak ");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idPihak", idPihak);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPenggadaiLe(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.jenis.kod = 'PG' ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listMohonAsal3(long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m where m.enkuiri.idEnkuiri = :idEnkuiri and  m.kodStatusLelongan.kod in ('SL','RM','AP')");
        q.setParameter("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<PermohonanAsal> listMohonAsal(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanAsal m where m.idPermohonanAsal.idPermohonan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAsal> listMohonAsal2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanAsal m where m.idPermohonan.idPermohonan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPihakje(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' and  m.jenis.kod = 'PG' ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m WHERE m.Hakmilik.idHakmilik = :idHakmilik ");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByHakmilik(Hakmilik hakmilik) {
        //fikri :: return only tuan tanah and pemegang amanah        
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PG')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentingan2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik = m.hakmilik and m.aktif = 'Y' order by m.hakmilik.idHakmilik asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilik(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :id_mohon ");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikY(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikbyID(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik and m.permohonan.kodUrusan.kod in ('PPJP','PPTL') ");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Permohonan> checkMohonUrusanMahkamah(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.idPermohonan = :idPermohonan and m.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> checkTarikhPerserahan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.idPermohonan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> searchHakmilikP(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m,etanah.model.Lelongan l,"
                + "etanah.model.Enkuiri en WHERE m.hakmilik.idHakmilik = :idHakmilik"
                + " and m.permohonan.idPermohonan = en.permohonan.idPermohonan and en.idEnkuiri = l.enkuiri.idEnkuiri and l.kodStatusLelongan.kod = 'AK' and "
                + "m.permohonan.kodUrusan.kod = 'PPJP' and m.permohonan.idPermohonan NOT IN(:idPermohonan) and m.permohonan.status = 'TA'");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

//    public List<HakmilikUrusan> searchHakmilikandMohon(String idHakmilik, String idPermohonan) {
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m,etanah.model.HakmilikUrusan hu,"
//                + "etanah.model.PermohonanRujukanLuar rl WHERE m.hakmilik.idHakmilik = hu.hakmilik.idHakmilik and hu.kodUrusan.kod in ('PMHUN','PMHUK','PLT','PLK','PLS','PMKMH')"
//                + "and hu.hakmilik.idHakmilik = rl.hakmilik.idHakmilik =:idHakmilik and hu.aktif = 'Y' and  m.permohonan.idPermohonan =:idPermohonan and m.permohonan.kodUrusan.kod = 'PPJP' ");
//        q.setParameter("idHakmilik", idHakmilik);
//        q.setParameter("idPermohonan", idPermohonan);
//        return q.list();
//    }
    public List<Enkuiri> searchHakmilikPemohon(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT en FROM etanah.model.HakmilikPermohonan m,"
                + "etanah.model.Enkuiri en WHERE m.hakmilik.idHakmilik = :idHakmilik"
                + " and m.permohonan.idPermohonan = en.permohonan.idPermohonan and "
                + "m.permohonan.kodUrusan.kod = 'PPJP' and m.permohonan.idPermohonan NOT IN(:idPermohonan)");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> searchHakmilikPemohonLelong(String idHakmilik, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.HakmilikPermohonan m,etanah.model.Lelongan l,"
                + "etanah.model.Enkuiri en WHERE m.hakmilik.idHakmilik = :idHakmilik"
                + " and m.permohonan.idPermohonan = en.permohonan.idPermohonan and l.enkuiri.idEnkuiri = en.idEnkuiri and "
                + "m.permohonan.kodUrusan.kod = 'PPJP' and m.permohonan.idPermohonan NOT IN(:idPermohonan)");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelongan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m,etanah.model.Enkuiri e WHERE e.permohonan.idPermohonan = :idPermohonan "
                + "and e.status.kod = 'AK' and m.enkuiri.idEnkuiri = e.idEnkuiri and m.kodStatusLelongan.kod = 'AK'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAP(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AP') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAK1(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK', 'AP','AT') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganA(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP') and m.permohonan.idPermohonan = :idPermohonan order by m.bil desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganTP(String idPermohonan, int bil) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','TP','PL','AT','RM','TB','TA') and m.permohonan.idPermohonan = :idPermohonan and m.bil =:bil");
        q.setParameter("idPermohonan", idPermohonan);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<Lelongan> listLelonganAKAP(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','PL','AT','RM','TB','TP') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAKAP2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AP','PL','AT','RM','TB') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAK(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','TA') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAKTG(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','TA','TG') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganAKNew(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','TA') and m.permohonan.idPermohonan = :idPermohonan order by m.infoAudit.tarikhMasuk desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by syazwan(21/10/2013)
    //select from lelong with kod_sts = TB
    public List<Lelongan> listLelonganTB(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('TB') and m.permohonan.idPermohonan = :idPermohonan order by m.idLelong");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    //add TA bby zaty
    public List<Lelongan> listLelonganAKAP1(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP', 'AT','TA') and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganRM(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod = 'RM' and m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganENKUIRI(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE "
                + "m.kodStatusLelongan.kod in ('AK','AP','PL','AT') and m.enkuiri.idEnkuiri = :idEnkuiri");
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

    public List<Lelongan> listLelonganPL(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.permohonan.idPermohonan = :idPermohonan "
                + "and m.kodStatusLelongan.kod = 'PL'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> listLelonganPLAK(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.kodStatusLelongan.kod in ('AK','AP') and m.permohonan.idPermohonan =:idMohon");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<Lelongan> listLelonganAT(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.kodStatusLelongan.kod in ('AT','AP') and m.permohonan.idPermohonan =:idMohon");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<Lelongan> listLelonganATDESC(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.kodStatusLelongan.kod in ('AT','AP','TB') and m.permohonan.idPermohonan =:idMohon order by m.bil desc");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<LelonganSuku> listLelongSuku(long idLelong) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.LelonganSuku l WHERE l.lelongan.idLelong =:idLelong");
        q.setLong("idLelong", idLelong);
        return q.list();
    }

    public Lelongan findIdLelong(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT l FROM etanah.model.Lelongan l WHERE l.lelongan.idMohon =:idMohon");
        q.setString("idMohon", idMohon);
        return (Lelongan) q.uniqueResult();
    }

    public List<Enkuiri> getEnkuiriSblm2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :id_mohon order by e.idEnkuiri desc");
        q.setParameter("id_mohon", idPermohonan);
        return q.list();
    }

    public List<Enkuiri> getEnkuiriSblm(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT e FROM etanah.model.Enkuiri e where e.permohonan.idPermohonan = :id_mohon and e.status.kod = 'AK' order by e.idEnkuiri desc");
        q.setParameter("id_mohon", idPermohonan);
        return q.list();
    }

    public List<Lelongan> getLelongSblm(Enkuiri enkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.enkuiri= :id_enkuiri and m.enkuiri.status.kod = 'AK' order by m.idLelong desc");
        q.setParameter("id_enkuiri", enkuiri);
        return q.list();
    }

    @Transactional
    public long simpanOrUpdateDokumen(Dokumen dokumen) {
        long idDokumen = new Long(1L); // initialize long
        try {
            dokumen = dokumenDAO.saveOrUpdate(dokumen);
            idDokumen = dokumen.getIdDokumen();
        } catch (Exception ex) {
            LOG.error("simpanOrUpdateDokumen tidak berjaya :" + ex);
        }
        return idDokumen;
    }

    @Transactional
    public void updateNotis(Notis l) {
        LOG.debug("Notis:start");
        notisDAO.update(l);
        LOG.debug("Notis:finish");
    }

    @Transactional
    public void updatePembida(Pembida l) {
        LOG.debug("Pembida:start");
        pembidaDAO.update(l);
        LOG.debug("Pembida:finish");
    }

    public HakmilikPermohonan findHakmilikPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findPBByidHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public Lelongan findHakmilikPermohonanByidHakmilik(String idHakmilik) {
        String query = "Select l FROM etanah.model.HakmilikPermohonan p,etanah.model.Lelongan l  WHERE p.hakmilik.idHakmilik = :idHakmilik "
                + "and p.id = l.hakmilikPermohonan.id "
                + "and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Lelongan) q.uniqueResult();
    }

    public Lelongan getLelong(Long idEnkuiri) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.enkuiri.idEnkuiri = :idEnkuiri and m.kodStatusLelongan.kod = 'AK' ");
        q.setLong("idEnkuiri", idEnkuiri);
        return (Lelongan) q.uniqueResult();
    }

//    public List<Enkuiri> getALLEnkuri(String kod) {
//        String query = "SELECT m FROM etanah.model.Enkuiri m WHERE m.tarikhEnkuiri is not null and m.status.kod ='AK' 
//        and m.bilanganKes is not null and m.permohonan.cawangan.kod =:kod";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("kod", kod);
//        return q.list();
//    }
    //user nak view semua tarikh lelong dan enkuiri yang lepas
    public List<Enkuiri> getALLEnkuri(String kod) {
        String query = "SELECT m FROM etanah.model.Enkuiri m WHERE to_char(m.tarikhEnkuiri, 'YYYY') = to_char(sysdate,'YYYY') "
                + "and m.bilanganKes is not null and m.permohonan.cawangan.kod =:kod "
                + "and m.status not in ('TG') "
                + "and m.permohonan.status not in ('BP','RM')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    public List<Lelongan> LelongannotAK(Long idEnkuiri) {
        Session session = sessionProvider.get();
//      Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.enkuiri.idEnkuiri = :idEnkuiri and m.kodStatusLelongan.kod not in ('AK')");
        Query q = session.createQuery("SELECT m FROM etanah.model.Lelongan m WHERE m.enkuiri.idEnkuiri = :idEnkuiri");
        q.setLong("idEnkuiri", idEnkuiri);
        return q.list();
    }

//    public List<Lelongan> getALLLelongan(String kod) {
//        String query = "SELECT m FROM etanah.model.Lelongan m WHERE m.tarikhLelong is not null and m.kodStatusLelongan.kod ='AK' and m.enkuiri.permohonan.cawangan.kod =:kod";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("kod", kod);
//        return q.list();
//    }
    //user nak view semua tarikh lelong dan enkuiri yang lepas
    public List<Lelongan> getALLLelongan(String kod) {
//        String query = "SELECT m FROM etanah.model.Lelongan m WHERE m.tarikhLelong is not null and m.enkuiri.permohonan.cawangan.kod =:kod";
        String query = "SELECT m FROM etanah.model.Lelongan m WHERE to_char(m.tarikhLelong, 'YYYY') = to_char(sysdate,'YYYY') and m.enkuiri.permohonan.cawangan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();

    }

    public List<Lelongan> getALLLelonganNotInSL(String kod) {
        String query = "SELECT m FROM etanah.model.Lelongan m WHERE m.tarikhLelong is not null and m.enkuiri.permohonan.cawangan.kod =:kod "
                + "and m.kodStatusLelongan.kod not in ('SL','RM') "
                + "and m.permohonan.status not in ('BP','RM')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }
    //added by nur.aqilah
    //validator 4 calendar
    //stage rekodBidaan

    public List<Lelongan> findLelonganTerdahulu(String idPermohonan, int bil) {
        String query = "SELECT l FROM etanah.model.Lelongan l where l.permohonan.idPermohonan= :idPermohonan AND l.kodStatusLelongan.kod IN ('TG', 'TB', 'AT') AND (bil-l.bil<=1)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnPermohonanBatal'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<Pengguna> findPenggunaId(String nama) {
        String query = "Select idPengguna FROM etanah.model.Pengguna p WHERE p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanChartingKeputusan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '16RekodMaklum'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanChartingKeputusan831b(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '126Keputusan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanChartingKeputusanOutcomes(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'g_charting_keputusan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanChartingKeputusanPTSP(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '10RekodKeputusanMMK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonanLog findFasaPermohonanLog(FasaPermohonan mohonFasa) {
        String query = "Select p FROM etanah.model.FasaPermohonanLog p WHERE p.fasa.idFasa = :id_fasa";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id_fasa", mohonFasa.getIdFasa());
        return (FasaPermohonanLog) q.uniqueResult();
    }

    public List<FasaPermohonanLog> findFasaPermohonanLog2(FasaPermohonan mohonFasa) {
        String query = "Select p FROM etanah.model.FasaPermohonanLog p WHERE p.fasa.idFasa = :id_fasa order by p.infoAudit.tarikhKemaskini";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id_fasa", mohonFasa.getIdFasa());
        return q.list();
    }

    //add by massita
    public FasaPermohonan findFasaPermohonanPengambilan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '09KeputusanMMK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanCetak16I(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'Cetak16I'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanBayaran(String idPermohonan, String idAliran) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = :idAliran"
                + " order by idFasa ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);

        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    public FasaPermohonan findFasaPermohonanSurat(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanNotisGanti(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'notisGantian'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSemakRekodPenghantaran(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakRekodPenghantaran'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanCetakWarta(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetakWarta'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanDrafWarta(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'drafWarta'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> getFasaPermohonanSemakRekodPenghantaran(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakRekodPenghantaran'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getFasaPermohonanMohonTangguh(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'mohonTangguh'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> listFasaPermohonanSemak16H(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semak16HLantikJurulelong'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanSemak16H(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semak16HLantikJurulelong'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKmskJurulelong(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kmskJurulelong'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSemakPembida(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakPembida' order by p.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findListFasaPermohonanSemakPembida(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakPembida' order by p.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaBatalPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'batalPermohonanPerintahJual'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanMuatNaik16Q(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'muatNaik16Q'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanMohonTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'mohonTangguh'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanMohonTangguhList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'mohonTangguh' order by p.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        return (FasaPermohonan) q.uniqueResult();
        return q.list();
    }

    public List<FasaPermohonan> findFasaPermohonanSemakPermohonanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakanPermohonan' order by p.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        return (FasaPermohonan) q.uniqueResult();
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanSemakPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakanPermohonan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaKpsnTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnTangguh'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findlantikanPelelong(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'lantikanPelelong'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findlantikanPelelong1(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnTangguh'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> getFasaKpsnTangguh(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnTangguh'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasasuratLulusTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'suratLulusTangguh'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSemak16Q(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semak16Q'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSemakRekod16H(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakRekod16H'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public KandunganFolder getListDokumenSSL(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m, etanah.model.Dokumen d WHERE m.folder.folderId = :idFolder and m.dokumen.idDokumen = d.idDokumen and d.kodDokumen.kod in ('RBY')");
        q.setParameter("idFolder", idFolder);
        return (KandunganFolder) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanCetakSurat(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetakSuratBakiBidaan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanCetakSuratList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetakSuratBakiBidaan' order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanCetakSuratLucutHak(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetakSuratLucutHak'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanPerbicaraan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '45Perbicaraan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKedesakan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '28AdaKedesakan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanRekodBidaan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodBidaan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findPermonanFasaRekodBidaan1(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ('rekodBidaan', 'rekodBidaanJLB') order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findPermonanFasaRekodBidaanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ('rekodBidaan', 'rekodBidaanJLB') order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanRekodBidaanJLB(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodBidaanJLB'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanRekodBidaanJLBlist(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ( 'rekodBidaanJLB' ) order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanRekodBidaan2(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'lelonganKedua'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanRekodPenghantaran16H(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodPenghantaran16H'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanRekodBidaan3(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'lelonganKetiga'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSiasatan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnSiasatan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanSiasatanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnSiasatan' order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasaPermohonanPB(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '01DrafPenarikanBalik'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSediaPengistiharan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'sediaPengistiharan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSiasatanKodTG(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnSiasatan' and p.keputusan.kod='TG'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonan16H(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetak16H'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanCetakSuratLantikan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'cetakSuratLantikan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanSediaPengisytiharan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'sediaPengisytiharan'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> getFasaKemasukanPerintah(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ('mohonTangguh','kpsnSiasatan','sedia16H','semak16HLantikJurulelong','cetak16H','rekodPenghantaran16H','sediaPengisytiharan','rekodBidaan','rekodBidaanJLB',semakPembida','cetakSuratLantikan','cetakSuratBakiBidaan','sediaCetak16I','sediaSijilRujuk','cetakSijilRujuk','cetakSuratTolak')";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getFasaSemak16H(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ('cetak16H','rekodPenghantaran16H','sediaPengisytiharan','rekodBidaan','rekodBidaanJLB','semakPembida','cetakSuratLantikan','cetakSuratBakiBidaan','sediaCetak16I','sediaSijilRujuk','cetakSijilRujuk','cetakSuratTolak')";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> findFasa(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findFasa1(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnTangguh'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonanTangguhList(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnTangguh' order by p.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (q.list());
    }

    public FasaPermohonan findFasaRekodBidaan_JLB(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ('rekodBidaan','rekodBidaanJLB')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    //added kod_sts by syazwan(18/10/2013)
    public Enkuiri findEnkuiri(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan and p.status.kod in ('AK')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    //edit y zatie add kod_sts TA (20/12/2013)
    public Enkuiri findEnkuiri2(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan and p.status.kod in ('AK','SL','TA','BM')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public List<Lelongan> findEnkuiriAP(String idPermohonan) {
        String query = "Select p FROM etanah.model.Lelongan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.status.kod = 'AP'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Enkuiri findEnkuiri1(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public Enkuiri findEnkuiriAll(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public List<Enkuiri> findEnkuiriAll2(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanRujukanLuar findMohonRujuk(String idPermohonan) {
        String query = "Select m FROM etanah.model.PermohonanRujukanLuar m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findMohonRujukList(String idPermohonan) {
        String query = "Select m FROM etanah.model.PermohonanRujukanLuar m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findMohonRujukHakMilikUrusanList(String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p, etanah.model.HakmilikUrusan h WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.hakmilik.idHakmilik=p.hakmilik.idHakmilik and p.permohonan.idPermohonan= h.idPerserahan and h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findPermohonanByIdHakmilik(String idHakmilik) {
        String query = "Select hp FROM etanah.model.HakmilikPermohonan hp, etanah.model.Permohonan p WHERE hp.hakmilik.idHakmilik= :idHakmilik AND p.idPermohonan= hp.permohonan.idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findPermohonanIdHakmilik(String idHakmilik) {
        String query = "Select hp FROM etanah.model.HakmilikPermohonan hp WHERE hp.hakmilik.idHakmilik= :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findPermohonanIdHakmilikMahkamah(String idHakmilik) {
        String query = "Select hp FROM etanah.model.HakmilikPermohonan hp, etanah.model.Permohonan p WHERE hp.hakmilik.idHakmilik= :idHakmilik AND hp.permohonan.idPermohonan=p.idPermohonan AND p.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findMohonRujukAUC(String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p, etanah.model.HakmilikUrusan h, etanah.model.Permohonan m WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.hakmilik.idHakmilik=p.hakmilik.idHakmilik and p.permohonan.idPermohonan= m.idPermohonan and h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS') and p.permohonan.kodUrusan.kod in ('PPJP')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public PermohonanRujukanLuar findMohonRujukAUC1(String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p, etanah.model.HakmilikUrusan h, etanah.model.Permohonan m WHERE h.hakmilik.idHakmilik= :idHakmilik AND h.hakmilik.idHakmilik=p.hakmilik.idHakmilik and p.permohonan.idPermohonan= m.idPermohonan and h.kodUrusan.kod IN ('PMKMH','PMHUN','PMHUK','PLT','PLK','PLS') and p.permohonan.kodUrusan.kod in ('PPJP')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public Lelongan findLelong(String idPermohonan) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Lelongan) q.uniqueResult();
    }

    public List<Lelongan> findListLelong(String idPermohonan) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan and l.kodStatusLelongan.kod = 'AK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Lelongan> findListLelongan(String idPermohonan) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan order by l.bil desc, l.infoAudit.tarikhMasuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Lelongan findLelong2(String idPermohonan, String kodStatus) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.permohonan.idPermohonan = :idPermohonan and l.kodStatusLelongan.kod = :kodStatus";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodStatus", kodStatus);
        return (Lelongan) q.uniqueResult();
    }

    public Lelongan findLelong3(String idLelong) {
        String query = "Select l FROM etanah.model.Lelongan l WHERE l.idLelong = :idLelong";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idLelong", idLelong);
        return (Lelongan) q.uniqueResult();
    }

    public List<Enkuiri> findEnkuiriTG(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan and p.status.kod = 'TG' order by p.idEnkuiri desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Enkuiri findEnkuiriTG2(String idPermohonan) {
        String query = "Select p FROM etanah.model.Enkuiri p WHERE p.permohonan.idPermohonan = :idPermohonan and p.status.kod = 'TG' order by p.idEnkuiri desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (Enkuiri) q.uniqueResult();
    }

    public List<Akaun> getHakmilikA(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id_hakmilik");
        q.setParameter("id_hakmilik", idHakmilik);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasa(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'kpsnSiasatan' order by p.idFasa DESC");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasaSemakPembida(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakPembida'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasaRekodBidaan1(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran in ( 'rekodBidaan', 'rekodBidaanJLB') order by p.infoAudit.tarikhMasuk desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasaRekodBidaan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodBidaan'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasaRekodBidaanJLB(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodBidaanJLB' order by p.infoAudit.tarikhMasuk desc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasaBayaran(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'bayaran'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermonanFasa2(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semak16HLantikJurulelong'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermohonanMohonTangguh(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'mohonTangguh'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getPermohonanNotisGantian(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semakRekodPenghantaran'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getRekodKpsn(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'arahNG'");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATD(String idPermohonan, String idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idRuj =:idRuj and p.jenisDeposit = 'H'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATDAll(String idPermohonan, String idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idRuj =:idRuj";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATD(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.jenisDeposit = 'H'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATD2(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.jenisDeposit = 'T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATD2(String idPermohonan, String idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idRuj =:idRuj and p.jenisDeposit = 'T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATDS(String idPermohonan, Long idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idRuj =:idRuj and p.jenisDeposit = 'H'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setLong("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findDepositH(Long idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.idRuj =:idRuj and p.jenisDeposit = 'H'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findDepositT(Long idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.idRuj =:idRuj and p.jenisDeposit = 'T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRuj", idRuj);
        return q.list();
    }

    public List<AkuanTerimaDeposit> findATD2S(String idPermohonan, Long idRuj) {
        String query = "SELECT p FROM etanah.model.AkuanTerimaDeposit p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idRuj =:idRuj and p.jenisDeposit = 'T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setLong("idRuj", idRuj);
        return q.list();
    }

    public List<PermohonanTuntutanKos> listPT(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('L01','L02','L03')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> listTB(Long idKos) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanBayar m WHERE m.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
    }

    public PenasihatUndang getALLPenUndg() {
        String query = "SELECT m FROM etanah.model.PenasihatUndang m WHERE m.kodNegeri.kod in ('04','05')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (PenasihatUndang) q.uniqueResult();
    }

    public Pihak getPihak(String noPengenalan) {
        String query = "SELECT m FROM etanah.model.Pihak m WHERE m.noPengenalan = :noPengenalan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return (Pihak) q.uniqueResult();
    }

    public Pihak getPihakID(String idPihak) {
        String query = "SELECT m FROM etanah.model.Pihak m WHERE m.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPihak", idPihak);
        return (Pihak) q.uniqueResult();
    }

    public Pembida getIdPihak(String idPembida) {
        String query = "SELECT m FROM etanah.model.Pembida m WHERE m.idPembida = :idPembida";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPembida", idPembida);
        return (Pembida) q.uniqueResult();
    }

    public PermohonanPihak getPihakIDinMohonPihak(String idPermohonan, String idPihak) {
        String query = "SELECT pp FROM etanah.model.PermohonanPihak pp WHERE pp.pihak.idPihak = :idPihak and pp.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPihak", idPihak);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getPihakIDinMohonPihak2(String idPermohonan, String idPihak, String idHakmilik) {
        String query = "SELECT pp FROM etanah.model.PermohonanPihak pp WHERE pp.pihak.idPihak = :idPihak and pp.permohonan.idPermohonan = :idPermohonan and pp.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPihak", idPihak);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanPihak) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan1(String idPihak, String idPermohonan) {
        String query = "SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan "
                + "and hp.hakmilik = m.hakmilik and m.aktif = 'Y' "
                + "and m.jenis.kod in ('PM') "
                + "and m.pihak.idPihak = :idPihak ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPihak", idPihak);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PihakAlamatTamb getPihakAlamatTamb(long idPihak) {
        String query = "SELECT m FROM etanah.model.PihakAlamatTamb m WHERE m.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return (PihakAlamatTamb) q.uniqueResult();
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('L03')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> listPermohonanTuntutanKos(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('L03')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> listPermohonanTuntutanKos2(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('L01','L02')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> listPermohonanTuntutanSTR(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('STR08')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos2(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodTuntut.kod in ('L01','L02')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PenghantarNotis findPenghantarNotis(int idPenghantarNotis) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.idPenghantarNotis = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("id", idPenghantarNotis);
        return (PenghantarNotis) q.uniqueResult();
    }

    public Pengguna findPengguna(String idPengguna) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.idPengguna = :idPengguna";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        return (Pengguna) q.uniqueResult();
    }

    public List<PenghantarNotis> findPenghantarNotisAK() {
        String query = "Select p FROM etanah.model.PenghantarNotis p order by p.idPenghantarNotis asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PenghantarNotis> findPenghantarNotisAktif() {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.aktif = 'Y' order by p.idPenghantarNotis asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<MenuCapaian> capaiList() {

        String query = "Select p FROM etanah.model.MenuCapaian p WHERE p.peranan.kod = 'JLB' ";
        //String query = "Select p FROM etanah.model.MenuCapaian p WHERE p.peranan.kod = 'JLB' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();

    }

    public List<Pengguna> capaiPTLelong(String kod) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.perananUtama.kod = 'pptlelong' and p.status = 'A' and p.kodCawangan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PenggunaPeranan> capaiPgunPeranan(String kod) {
        String query = "Select p FROM etanah.model.PenggunaPeranan p WHERE p.peranan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    public Pengguna findPT(String idPengguna) {
        String query = "Select p FROM etanah.model.Pengguna p WHERE p.idPengguna = :idPengguna";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        return (Pengguna) q.uniqueResult();
    }

    public List<PenghantarNotis> findPenghantarNotisList(int idPenghantarNotis) {
        String query = "SELECT m FROM etanah.model.PenghantarNotis m WHERE m.idPenghantarNotis = :idPenghantarNotis";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPenghantarNotis", idPenghantarNotis);
        return q.list();
    }

    public Permohonan findKodCawangan(String cawKod) {
        String query = "SELECT p FROM etanah.model.Permohonan p WHERE p.cawangan.kod = :kod and p.permohonan.kodUrusan.kod = 'PPJP','PPTL','PPBL'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", cawKod);
        return (Permohonan) q.uniqueResult();
    }

    public JuruLelong getIC(String noPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT j FROM etanah.model.JuruLelong j WHERE j.noPengenalan = :noPengenalan ");
        q.setParameter("noPengenalan", noPengenalan);
        return (JuruLelong) q.uniqueResult();

    }

    //genreport
    public void reportGen(Permohonan p, String repName, String repCode, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        String gen = repName;
        String code = repCode;
        kd = kodDokumenDAO.findById(code);
        d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan(), pengguna);
        String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        LOG.info("path dokumen" + path);
        reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
    }

    //genreport
    public Dokumen reportGen2(Permohonan p, String repName, String repCode, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        String gen = repName;
        String code = repCode;
        kd = kodDokumenDAO.findById(code);
        d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan(), pengguna);
        String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
        return d;
    }

    //genreport
    public void reportGen3(Permohonan p, String repName, String repCode, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        String gen3 = repName;
        String code3 = repCode;
        kd = kodDokumenDAO.findById(code3);
        d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan(), pengguna);
        String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
    }

    //genreport
    public void reportGen4(Permohonan p, String repName, String repCode, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        String gen4 = repName;
        String code4 = repCode;
        kd = kodDokumenDAO.findById(code4);
        d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan(), pengguna);
        String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        reportUtil.generateReport(gen4, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
    }

    public Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    public void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public String stageId(String taskId, Pengguna pengguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public String removeHttp(String s) {
//        String remove = "://";
//        int i;
//        String result = "";
//        i = s.indexOf(remove);
//        if (i != -1) {
//            result = s.substring(0, i);
//            result = result + s.substring(i + remove.length());
//            s = result;
//        }
//        return s;
        String[] http = s.split("://");
        LOG.info("http 1 : " + http[0]);
        LOG.info("http 2 : " + http[1]);
        LOG.info("removeHttp : " + http.length);
        return http[http.length - 1];
    }

    public String sub(String aa) {
        String result = "";
        int i;
        i = aa.indexOf(":");
        if (i != -1) {
            result = aa.substring(0, i);
//            result = result + aa.substring(i + aa.length());
            aa = result;
        }
        return aa;
    }

    public String replaceDNS(String rep, String ori) throws UnknownHostException {
        String a = "";
        CharSequence s = getIP(ori).subSequence(0, getIP(ori).length());
        CharSequence as = rep.subSequence(0, rep.length());
        a = ori.replace(s, as);
        return a;
    }

    public String getIP(String dns) throws UnknownHostException {
        String ip = null;
        ip = (sub(removeHttp(dns)));
        return ip;
    }

    public FasaPermohonan findMohonFasaByIdMohonIdPengguna(String idPermohonan, String idAliran) {
        String query = "Select h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        FasaPermohonan fsp = (FasaPermohonan) q.uniqueResult();
        return fsp;
    }

    public FasaPermohonan FasaPermohonanRekodBidaan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p, FasaPermohonanLog f"
                + "   WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'rekodBidaan'"
                + " and p.senaraiLog = f.fasa";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();

    }

    public List<Pengguna> findPenggunaByBPEL(List<String> bpelName) {
//        String query = "Select u from etanah.model.PenggunaPeranan u WHERE ";
        String query = "SELECT u FROM etanah.model.PenggunaPeranan u, etanah.model.JuruLelong j WHERE u.pengguna = j.idPengguna and j.aktif ='Y' and ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
            query = query + " order by j.idPengguna.nama";
        }
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    public Pembida getPembidaByIdLelong(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE pe.kodStsPembida.kod in ('BJ','AT')"
                + " and pe.lelong.idLelong = :idLelong";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLelong", idLelong);
        return (Pembida) q.uniqueResult();

    }

//    6 mei 2015 farizal
    public Pembida getPembidaBerjaya(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE pe.kodStsPembida.kod = 'BJ'"
                + " and pe.lelong.idLelong = :idLelong";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLelong", idLelong);
        return (Pembida) q.uniqueResult();

    }

    public List<Pembida> getSenaraiPembidaBerjaya(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE pe.kodStsPembida.kod = 'BJ'"
                + " and pe.lelong.idLelong = :idLelong";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLelong", idLelong);
        return q.list();

    }

    public Pembida getPembida(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE "
                + "pe.lelong.idLelong = :idLelong";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLelong", idLelong);
        return (Pembida) q.uniqueResult();

    }

    public Pembida getPembidaID(long idPihak) {
        String query = "Select pe from etanah.model.Pembida pe WHERE "
                + "pe.pihak.idPihak = :idPihak";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return (Pembida) q.uniqueResult();

    }

    public Pembida getPembidaByIdPembida(long idPembida) {
        String query = "Select pe from etanah.model.Pembida pe WHERE "
                + "pe.idPembida = :idPembida";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPembida", idPembida);
        return (Pembida) q.uniqueResult();

    }

    public List<Pembida> getPembidaList(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE "
                + "pe.lelong.idLelong = :idLelong";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idLelong", idLelong);
        return q.list();

    }

    public List<Pembida> getPembidaListBerjayaTakByrBaki(long idLelong) {
        String query = "Select pe from etanah.model.Pembida pe WHERE "
                + "pe.lelong.idLelong = :idLelong AND pe.kodStsPembida.kod IN ('BJ','AT')";
//        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idLelong", idLelong);
        return q.list();

    }
}
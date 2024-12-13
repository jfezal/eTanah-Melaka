/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodSenarai;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodSenaraiDAO;
import etanah.dao.LampiranPerintahDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanLampiranPerintahDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanPerbicaraanDAO;
import etanah.dao.PermohonanPerbicaraanKehadiranDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.ProjekDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KandunganFolder;
import etanah.model.KodNegara;
import etanah.model.KodNegeri;
import etanah.model.LampiranPerintah;
import etanah.model.LaporanTanah;
import etanah.model.Notis;
import etanah.model.PenghantarNotis;
import etanah.model.PermohonanLampiranPerintah;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanPerbicaraan;
import etanah.model.PermohonanPerbicaraanKehadiran;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PihakAlamatTamb;
import etanah.model.Projek;
import etanah.model.SenaraiRujukan;
import java.util.Date;

/**
 *
 * @author solahuddin
 */
public class ConsentPtdService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanPerbicaraanDAO permohonanPerbicaraanDAO;
    @Inject
    PermohonanPerbicaraanKehadiranDAO permohonanPerbicaraanKehadiranDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodSenaraiDAO kodSenaraiDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLampiranPerintahDAO mohonLampiranPerintahDAO;
    @Inject
    LampiranPerintahDAO lampiranPerintahDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatTambDAO;
    @Inject
    ProjekDAO projekDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public Pemohon simpanPemohon(Pemohon pemohon) {
        return (Pemohon) pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.update(permohonan);
    }

    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.update(pihak);
    }

    @Transactional
    public void simpanPihakAlamatTamb(PihakAlamatTamb pihakAlamatTamb) {
        pihakAlamatTambDAO.saveOrUpdate(pihakAlamatTamb);
    }

    @Transactional
    public void simpanMohonLampiranPerintah(PermohonanLampiranPerintah mohonLampiranPerintah) {
        mohonLampiranPerintahDAO.saveOrUpdate(mohonLampiranPerintah);
    }

    @Transactional
    public void simpanLampiranPerintah(LampiranPerintah lampiranPerintah) {
        lampiranPerintahDAO.saveOrUpdate(lampiranPerintah);
    }

    @Transactional
    public void simpanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fasaPermohonan) {
        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
    }

    @Transactional
    public void simpanSenaraiRujukan(SenaraiRujukan senaraiRujukan) {
        senaraiRujukanDAO.saveOrUpdate(senaraiRujukan);
    }

    @Transactional
    public void simpanKodSenarai(KodSenarai kodSenarai) {
        kodSenaraiDAO.saveOrUpdate(kodSenarai);
    }

    @Transactional
    public void simpanPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        permohonanUrusanDAO.saveOrUpdate(permohonanUrusan);
    }

    @Transactional
    public void simpanPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    @Transactional
    public void simpanNotis(Notis notis) {
        notisDAO.saveOrUpdate(notis);
    }

    @Transactional
    public void hapusPemohon(List<Pemohon> list) {
        pemohonDAO.delete(list.get(0));
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPermohonanPerbicaraan(PermohonanPerbicaraan permohonanPerbicaraan) {
        permohonanPerbicaraanDAO.saveOrUpdate(permohonanPerbicaraan);
    }

    @Transactional
    public void simpanLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void simpanHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    @Transactional
    public void simpanPenghantarNotis(PenghantarNotis penghantarNotis) {
        penghantarNotisDAO.saveOrUpdate(penghantarNotis);
    }

    @Transactional
    public void simpanPerbicaraanKehadiran(PermohonanPerbicaraanKehadiran permohonanPerbicaraanKehadiran) {
        permohonanPerbicaraanKehadiranDAO.saveOrUpdate(permohonanPerbicaraanKehadiran);
    }

    @Transactional
    public void simpanMultipleBicaraKehadiran(List<PermohonanPerbicaraanKehadiran> bicaraHadir) {
        for (PermohonanPerbicaraanKehadiran perbicaraanKehadiran : bicaraHadir) {
            permohonanPerbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
        }
    }

    @Transactional
    public void simpanMohonTuntutKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.saveOrUpdate(permohonanTuntutanKos);
    }

    @Transactional
    public void simpanMohonTandatanganDokumen(PermohonanTandatanganDokumen permohonanTandatanganDokumen) {
        permohonanTandatanganDokumenDAO.saveOrUpdate(permohonanTandatanganDokumen);
    }

    @Transactional
    public void deleteMohonBicaraHadir(String[] uids) {
        for (int i = 0; i < uids.length; i++) {
            PermohonanPerbicaraanKehadiran perbicaraanKehadiran = permohonanPerbicaraanKehadiranDAO.findById(Long.parseLong(uids[i]));
            if (perbicaraanKehadiran == null) {
                continue;
            }
            permohonanPerbicaraanKehadiranDAO.delete(perbicaraanKehadiran);
        }
    }

    @Transactional
    public void simpanProjek(Projek projek) {
        projekDAO.saveOrUpdate(projek);
    }

    @Transactional
    public void deleteProjek(Projek projek) {
        projekDAO.delete(projek);
    }

    @Transactional
    public void deleteMohonTuntutKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }

    @Transactional
    public long simpanDokumen(Dokumen dokumen) {
        long idDokumen = new Long(1L); // initialize long
        try {
            dokumen = dokumenDAO.saveOrUpdate(dokumen);
            idDokumen = dokumen.getIdDokumen();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return idDokumen;
    }

    public List<KodSenarai> getKodSenaraiByJabatanAndKod(String jabatan, String kod) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KodSenarai m WHERE m.kodJabatan.kod = :kod_jabatan AND m.kod LIKE :kod");
        q.setParameter("kod_jabatan", jabatan);
        q.setParameter("kod", "%" + kod + "%");
        return q.list();
    }

    public SenaraiRujukan findSenaraiRujukan(String kodSenarai) {
        String query = "Select p FROM etanah.model.SenaraiRujukan p WHERE p.senarai.kod = :kod";
        List<SenaraiRujukan> senaraiRujukan = sessionProvider.get().createQuery(query).setString("kod", kodSenarai).list();

        if (!senaraiRujukan.isEmpty()) {
            return senaraiRujukan.get(0);
        } else {
            return null;
        }
    }

    public PermohonanPerbicaraan findMohonBicaraByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPerbicaraan p WHERE p.permohonan.idPermohonan = :id_permohonan";
        List<PermohonanPerbicaraan> senaraiMohonBicara = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiMohonBicara.isEmpty()) {
            return senaraiMohonBicara.get(0);
        } else {
            return null;
        }
    }

    public PermohonanPerbicaraan findMohonBicaraNotTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPerbicaraan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.catatan is null";
        List<PermohonanPerbicaraan> senaraiMohonBicara = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiMohonBicara.isEmpty()) {
            return senaraiMohonBicara.get(0);
        } else {
            return null;
        }
    }

    public PermohonanRujukanLuar findMohonRujukanNotTangguh(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.catatan is null";
        List<PermohonanRujukanLuar> senaraiRujukan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiRujukan.isEmpty()) {
            return senaraiRujukan.get(0);
        } else {
            return null;
        }
    }

    public PermohonanRujukanLuar findMohonRujukanNotTangguh2(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.catatan is null AND p.agensi is null";
        List<PermohonanRujukanLuar> senaraiRujukan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiRujukan.isEmpty()) {
            return senaraiRujukan.get(0);
        } else {
            return null;
        }
    }

    public PermohonanRujukanLuar findMohonRujukanByNamaNotTangguh(String idPermohonan, String namaSidang) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.namaSidang = :nama_sidang AND p.catatan is null";
        List<PermohonanRujukanLuar> senaraiRujukan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("nama_sidang", namaSidang).list();

        if (!senaraiRujukan.isEmpty()) {
            return senaraiRujukan.get(0);
        } else {
            return null;
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiRujLuarByIdMohonAgensi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan  ORDER BY b.nilai ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanUrusan findMohonUrusanByPerihal(String idPermohonan, String perihal) {
        String query = "Select p FROM etanah.model.PermohonanUrusan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.perihal = :perihal";
        List<PermohonanUrusan> senaraiPermohonanUrusan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("perihal", perihal).list();

        if (!senaraiPermohonanUrusan.isEmpty()) {
            return senaraiPermohonanUrusan.get(0);
        } else {
            return null;
        }
    }
    
        public PermohonanUrusan findMohonUrusanByPerihalIdHakmilik(String idPermohonan, String perihal, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanUrusan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.hakmilik.idHakmilik = :id_hakmilik AND p.perihal = :perihal";
        List<PermohonanUrusan> senaraiPermohonanUrusan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("id_hakmilik", idHakmilik).setParameter("perihal", perihal).list();

        if (!senaraiPermohonanUrusan.isEmpty()) {
            return senaraiPermohonanUrusan.get(0);
        } else {
            return null;
        }
    }

    public PermohonanUrusan findMohonUrusanByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanUrusan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.perihal is null";
        List<PermohonanUrusan> senaraiPermohonanUrusan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiPermohonanUrusan.isEmpty()) {
            return senaraiPermohonanUrusan.get(0);
        } else {
            return null;
        }
    }

    public PermohonanUrusan findMohonUrusanByIdMohonIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanUrusan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.hakmilik.idHakmilik = :id_hakmilik AND p.perihal is null";
        List<PermohonanUrusan> senaraiPermohonanUrusan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("id_hakmilik", idHakmilik).list();

        if (!senaraiPermohonanUrusan.isEmpty()) {
            return senaraiPermohonanUrusan.get(0);
        } else {
            return null;
        }
    }

    public FasaPermohonan findMohonFasaByStage(String idPermohonan, String idAliran) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.idAliran = :id_aliran";
        List<FasaPermohonan> senaraiMohonFasa = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("id_aliran", idAliran).list();

        if (!senaraiMohonFasa.isEmpty()) {
            return senaraiMohonFasa.get(0);
        } else {
            return null;
        }
    }

    public PermohonanKertas findMohonKertas(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanKertas p WHERE p.permohonan.idPermohonan = :id_permohonan";
        List<PermohonanKertas> senaraiMohonKertas = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiMohonKertas.isEmpty()) {
            return senaraiMohonKertas.get(0);
        } else {
            return null;
        }
    }

    public PermohonanKertas findMohonKertasByTajuk(String idPermohonan, String tajuk) {
        String query = "Select p FROM etanah.model.PermohonanKertas p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.tajuk = :tajuk";
        List<PermohonanKertas> senaraiMohonKertas = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("tajuk", tajuk).list();

        if (!senaraiMohonKertas.isEmpty()) {
            return senaraiMohonKertas.get(0);
        } else {
            return null;
        }
    }

    public PermohonanKertas findMohonKertasByKodDokumen(String idPermohonan, String kodDokumen) {
        String query = "Select p FROM etanah.model.PermohonanKertas p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.kodDokumen.kod = :kod_dokumen";
        List<PermohonanKertas> senaraiMohonKertas = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("kod_dokumen", kodDokumen).list();

        if (!senaraiMohonKertas.isEmpty()) {
            return senaraiMohonKertas.get(0);
        } else {
            return null;
        }
    }

    public PermohonanLaporanUlasan findMohonUlasByJenisUlasan(String idPermohonan, String jenisUlasan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.jenisUlasan = :jenis_ulasan";
        List<PermohonanLaporanUlasan> senaraiMohonUlas = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("jenis_ulasan", jenisUlasan).list();

        if (!senaraiMohonUlas.isEmpty()) {
            return senaraiMohonUlas.get(0);
        } else {
            return null;
        }
    }

    public PermohonanRujukanLuar findMohonRujukanByNamaSidangNotTangguh(String idPermohonan, String namaSidang) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.namaSidang = :nama_sidang AND p.catatan is null";
        List<PermohonanRujukanLuar> senaraiRujukanLuar = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("nama_sidang", namaSidang).list();

        if (!senaraiRujukanLuar.isEmpty()) {
            return senaraiRujukanLuar.get(0);
        } else {
            return null;
        }
    }

    public PermohonanRujukanLuar findMohonRujukanByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan";
        List<PermohonanRujukanLuar> senaraiRujukanLuar = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).list();

        if (!senaraiRujukanLuar.isEmpty()) {
            return senaraiRujukanLuar.get(0);
        } else {
            return null;
        }
    }

    public List<Permohonan> findSenaraiMohonSebelumByKodUrusanAndStatus(String idPermohonan, String kodUrusan, String status, String status2) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Permohonan p WHERE p.permohonanSebelum.idPermohonan = :id_permohonan AND p.kodUrusan.kod = :kod_urusan AND p.status IN (:status, :status2)");
            q.setString("id_permohonan", idPermohonan);
            q.setString("kod_urusan", kodUrusan);
            q.setString("status", status);
            q.setString("status2", status2);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanPerbicaraanKehadiran> getSenaraiPerbicaraanKehadiranByIdBicara(Long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPerbicaraanKehadiran m WHERE m.perbicaraan.idPerbicaraan = :id_bicara");
        q.setParameter("id_bicara", idBicara);
        return q.list();
    }

    public List<PermohonanPerbicaraan> findSenaraiMohonBicara(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanPerbicaraan p WHERE p.permohonan.idPermohonan = :id_permohonan");
            q.setString("id_permohonan", idPermohonan);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanPerbicaraan> findSenaraiMohonBicaraTangguh(String idPermohonan, String catatan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanPerbicaraan p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.catatan LIKE :catatan ORDER BY p.catatan");
            q.setString("id_permohonan", idPermohonan);
            q.setString("catatan", "%" + catatan + "%");
            return q.list();
        } finally {
        }
    }

    public List<Permohonan> findSenaraiMohonByTarikh(Date tarikhMula, Date tarikhTamat, String kodJabatan, String kodCaw) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Permohonan p WHERE p.infoAudit.tarikhMasuk Between :tarikh_mula AND :tarikh_tamat AND p.kodUrusan.jabatan.kod =:kod_jabatan AND p.cawangan.kod =:kod_caw ORDER BY p.infoAudit.tarikhMasuk");
            q.setDate("tarikh_mula", tarikhMula);
            q.setDate("tarikh_tamat", tarikhTamat);
            q.setString("kod_jabatan", kodJabatan);
            q.setString("kod_caw", kodCaw);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiMohonRujukanByNamaSidang(String idPermohonan, String namaSidang) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.namaSidang = :nama_sidang");
            q.setString("id_permohonan", idPermohonan);
            q.setString("nama_sidang", namaSidang);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiMohonRujukanByAgensiIsNull(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.agensi is null");
            q.setString("id_permohonan", idPermohonan);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiMohonRujukanByAgensiNotNull(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.agensi is not null");
            q.setString("id_permohonan", idPermohonan);
            return q.list();
        } finally {
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiMohonRujukanTangguh(String idPermohonan, String catatan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.catatan LIKE :catatan ORDER BY p.catatan");
            q.setString("id_permohonan", idPermohonan);
            q.setString("catatan", "%" + catatan + "%");
            return q.list();
        } finally {
        }
    }

    public List<PermohonanRujukanLuar> findSenaraiMohonRujukanByNamaTangguh(String idPermohonan, String namaSidang, String catatan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.namaSidang = :nama_sidang AND p.catatan LIKE :catatan ORDER BY p.catatan");
            q.setString("id_permohonan", idPermohonan);
            q.setString("nama_sidang", namaSidang);
            q.setString("catatan", "%" + catatan + "%");
            return q.list();
        } finally {
        }
    }

    public List<PermohonanTuntutanKos> findSenaraiMohonTuntutKos(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.catatan is null");
        q.setParameter("id_permohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findSenaraiMohonTuntutKosByKodTuntut(String idPermohonan, String kodTuntut) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.kodTuntut.kod = :kod_tuntut");
        q.setParameter("id_permohonan", idPermohonan);
        q.setParameter("kod_tuntut", kodTuntut);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findSenaraiMohonTuntutKosByKodTuntutAndIdMH(String idPermohonan, String kodTuntut, Long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.kodTuntut.kod = :kod_tuntut AND m.hakmilikPermohonan.id = :id_mh");
        q.setParameter("id_permohonan", idPermohonan);
        q.setParameter("kod_tuntut", kodTuntut);
        q.setParameter("id_mh", idMH);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findSenaraiMohonTuntutKosByCatatan(String idPermohonan, String catatan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.catatan = :catatan");
        q.setParameter("id_permohonan", idPermohonan);
        q.setParameter("catatan", catatan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findSenaraiMohonTuntutKosByKodTuntutAndIdMH(String idPermohonan, String kodTuntut, Long idMH, String catatan) {
        String query;
        if (catatan != null) {
            query = "is not null";
        } else {
            query = "is null";
        }
        Session session = sessionProvider.get();

        Query q = session.createQuery("FROM etanah.model.PermohonanTuntutanKos m WHERE m.permohonan.idPermohonan = :id_permohonan AND m.kodTuntut.kod = :kod_tuntut AND m.hakmilikPermohonan.id = :id_mh AND m.catatan " + query);
        q.setParameter("id_permohonan", idPermohonan);
        q.setParameter("kod_tuntut", kodTuntut);
        q.setParameter("id_mh", idMH);

        return q.list();
    }

    public List<Notis> findSenaraiNotis(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_permohonan");
        q.setParameter("id_permohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findSenaraiPB() {
        Session session = sessionProvider.get();
        Query q = session.createQuery("Select p FROM etanah.model.HakmilikPihakBerkepentingan p WHERE p.aktif ='Y'");
        return q.list();
    }

    public List<Pihak> findSenaraiPihakByNama(String nama) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak p WHERE UPPER(p.nama) = UPPER(:nama)");
            q.setString("nama", nama);
            return q.list();
        } finally {
        }
    }

    public List<HakmilikPihakBerkepentingan> findSenaraiHakPihakByNamaAndJenis(String idPihak, String jenis) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan p WHERE p.pihak.idPihak = :id_Pihak AND p.jenis.kod =:jenis AND p.aktif='Y'");
            q.setString("id_Pihak", idPihak);
            q.setString("jenis", jenis);
            return q.list();
        } finally {
        }
    }

    public List<HakmilikPihakBerkepentingan> findSenaraiHakPihakByHakmilikAndJenis(String idHakmilik, String jenis) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan p WHERE p.hakmilik.idHakmilik = :id_hakmilik AND p.jenis.kod =:jenis AND p.aktif='Y'");
            q.setString("id_hakmilik", idHakmilik);
            q.setString("jenis", jenis);
            return q.list();
        } finally {
        }
    }

    public List<HakmilikPermohonan> findSenaraiHakmilikPermohonan(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :id_permohonan ORDER BY p.hakmilik.idHakmilik");
            q.setString("id_permohonan", idPermohonan);
            return q.list();
        } finally {
        }
    }

    public HakmilikPermohonan findMohonHakmilikByIdH(String idPermohonan, String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik and hp.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMH(String idPermohonan, String idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan AND u.hakmilikPermohonan.id =:idMH");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public KodNegeri findKodNegeriByNama(String nama) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.KodNegeri u where u.nama =:nama");
        q.setString("nama", nama);
        return (KodNegeri) q.uniqueResult();
    }

    public KodNegara findKodNegaraByNama(String nama) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.KodNegara u where u.nama =:nama");
        q.setString("nama", nama);
        return (KodNegara) q.uniqueResult();
    }

    public List<PermohonanTuntutanBayar> findMohonTuntutBayar(long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :id_Kos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id_Kos", idKos);
        return q.list();
    }

    public List<PermohonanLampiranPerintah> findSenaraiMohonLampiranPerintahByJenis(String idPermohonan, String jenisLaporan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanLampiranPerintah p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.jenisLaporan = :jenis_laporan ORDER BY p.infoAudit.tarikhMasuk");
            q.setString("id_permohonan", idPermohonan);
            q.setString("jenis_laporan", jenisLaporan);

            return q.list();
        } finally {
        }
    }

    public PermohonanLampiranPerintah findMohonLampiranPerintahByJenis(String idPermohonan, String jenisLaporan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.PermohonanLampiranPerintah p where p.permohonan.idPermohonan =:id_permohonan AND p.jenisLaporan = :jenis_laporan");
        q.setString("id_permohonan", idPermohonan);
        q.setString("jenis_laporan", jenisLaporan);
        return (PermohonanLampiranPerintah) q.uniqueResult();
    }

    @Transactional
    public void deleteMohonLampiranPerintah(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            PermohonanLampiranPerintah mohonLampiranPerintah = mohonLampiranPerintahDAO.findById(Long.parseLong(ids[i]));
            if (mohonLampiranPerintah == null) {
                continue;
            }

            if (mohonLampiranPerintah.getSenaraiLampiranPerintah().size() > 0) {
                for (LampiranPerintah lampiranPerintah : mohonLampiranPerintah.getSenaraiLampiranPerintah()) {
                    if (lampiranPerintah == null) {
                        continue;
                    }
                    lampiranPerintahDAO.delete(lampiranPerintah);
                }
            }
            mohonLampiranPerintahDAO.delete(mohonLampiranPerintah);
        }
    }

    public List<Pihak> findPihak(String nama, String jenisPengenalan, String noPengenalan) {
        String query = "SELECT p FROM etanah.model.Pihak p WHERE p.idPihak = p.idPihak ";

        if (nama != null) {
            query += "AND p.nama LIKE :nama ";
        }

        if (jenisPengenalan != null) {
            query += "AND p.jenisPengenalan.kod = :jenis_Pengenalan ";
        }

        if (noPengenalan != null) {
            query += "AND p.noPengenalan LIKE :no_Pengenalan ";
        }

        Query q = sessionProvider.get().createQuery(query);
        if (nama != null) {
            q.setString("nama", "%" + nama + "%");
        }

        if (jenisPengenalan != null) {
            q.setString("jenis_Pengenalan", jenisPengenalan);
        }

        if (noPengenalan != null) {
            q.setString("no_Pengenalan", noPengenalan);
        }

        return q.list();
    }

    public List<PenghantarNotis> findSenaraiPenghantarNotis() {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.aktif = 'Y' order by p.idPenghantarNotis asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
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

    public List<PenghantarNotis> findSenaraiPenghantarNotisByCaw(String kodCawangan, String aktif) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.cawangan.kod = :kod_cawangan AND p.aktif = :aktif order by p.nama asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod_cawangan", kodCawangan);
        q.setString("aktif", aktif);
        return q.list();
    }

    public List<DokumenKewangan> findSenaraiDokumenKewanganByNoRujukan(String noRujukan) {
        String query = "Select p FROM etanah.model.DokumenKewangan p WHERE p.noRujukan = :no_rujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("no_rujukan", noRujukan);
        return q.list();
    }

    public List<KandunganFolder> findSenaraiFolderDokumenByIdFolder(String idFolder) {
        String query = "Select p FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :id_folder";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id_folder", idFolder);
        return q.list();
    }

    public PermohonanTandatanganDokumen findMohonTandatanganDokomen(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan =:id_permohonan");
        q.setString("id_permohonan", idPermohonan);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public PermohonanTandatanganDokumen findMohonTandatanganDokomen(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan =:id_permohonan and p.kodDokumen.kod =:kod_dokumen");
        q.setString("id_permohonan", idPermohonan);
        q.setString("kod_dokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public PermohonanRujukanLuar findMohonRujukanByKodAgensi(String idPermohonan, String kodAgensi) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :id_permohonan AND p.agensi.kod  =:kod_agensi";
        List<PermohonanRujukanLuar> senaraiRujukan = sessionProvider.get().createQuery(query).setParameter("id_permohonan", idPermohonan).setParameter("kod_agensi", kodAgensi).list();

        if (!senaraiRujukan.isEmpty()) {
            return senaraiRujukan.get(0);
        } else {
            return null;
        }
    }
}

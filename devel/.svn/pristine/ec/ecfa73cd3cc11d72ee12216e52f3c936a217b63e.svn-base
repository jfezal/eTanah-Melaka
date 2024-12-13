/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

/**
 *
 * @author nordiyana
 */
import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.LaporanTandaSempadanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.IntegrasiPermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.Pemohon;
import etanah.model.HakmilikUrusan;
import etanah.model.Pihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.SyaratPendudukanDAO;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.PermohonanAduanDAO;
import etanah.dao.LaporanPemulihanTanahDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.model.Alamat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.SyaratPendudukan;
import etanah.model.PermohonanPihak;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.PermohonanPihakHubungan;
import etanah.service.common.PermohonanPihakService;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.ambil.HakmilikPerbicaraan;


import org.apache.log4j.Logger;
import org.hibernate.Session;

public class PengambilanAduanService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
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
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
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
    PermohonanAduanDAO permohonanAduanDAO;
    @Inject
    HakmilikPermohonanDAO hpDAO;
    @Inject
    LaporanPemulihanTanahDAO laporanPemulihanTanahDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
//    @Inject
//    AlamatDAO alamatDAO;
    Permohonan permohonan;
    PermohonanRujukanLuar permohonanRujukanLuar;
    PermohonanPengambilan permohonanPengambilan;
    SyaratPendudukan syaratPendudukan;
    Hakmilik hakmilik;
    HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    HakmilikUrusan hakmilikUrusan;
    HakmilikPermohonan hakmilikPermohonan;
    PermohonanPihak pp;

    public Pihak findByIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        return (Pihak) sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).uniqueResult();
    }

    public List<HakmilikUrusan> findByidUrusan(String idHakmilik, String kod) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.hakmilik.idHakmilik = :idHakmilik" + " and B.kodUrusan.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
//        return (HakmilikUrusan) q.uniqueResult();
        return q.list();

    }

    public List<HakmilikPermohonan> findUrusanMH(String idHakmilik) {
        Session s = sessionProvider.get();
        String query = "select mh.hakmilik.idHakmilik,mh.permohonan.idPermohonan from HakmilikPermohonan mh,"
                + "Permohonan a "
                + "where mh.permohonan.idPermohonan = a.idPermohonan "
                + "and a.kodUrusan = 'SEK4' "
                + "and mh.hakmilik.idHakmilik = :idHakmilik ";

        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

//     select j.id_hakmilik,j.id_mohon
//             i.id_mohon,i.kod_urusan
//             from mohon_hakmilik j , mohon i
//             where j.id_mohon = i.id_mohon and kod_urusan = 'SEK4' and j.ID_HAKMILIK = '050597HSD01701292';
    public HakmilikUrusan findByidUrusanHakmilik(String idHakmilik, String kod) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.hakmilik.idHakmilik = :idHakmilik" + " and B.kodUrusan.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return (HakmilikUrusan) q.uniqueResult();
//        return q.list();

    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahByidMohon(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.id = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    @Transactional
    public void simpanIdHakmilik(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void simpanPA(PermohonanAduan permohonanAduan) {
        permohonanAduanDAO.saveOrUpdate(permohonanAduan);
    }

//       @Transactional
//   public void simpanAlamatPengadu(Alamat alamat) {
//       alamatDAO.saveOrUpdate(alamat);
//   }
    @Transactional
    public void simpanPP(PermohonanPihak pp) {
        permohonanPihakDAO.saveOrUpdate(pp);
    }
     @Transactional
    public void simpanWakil(PermohonanPihakHubungan pph) {
        permohonanPihakHubunganDAO.saveOrUpdate(pph);
    }

    @Transactional
    public void simpanHP(HakmilikPermohonan hp) {
        hpDAO.saveOrUpdate(hp);
    }

    @Transactional
    public void simpanPemohon(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }

    public PermohonanAduan findByidPAduan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanAduan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanAduan) q.uniqueResult();
    }

//   public List<HakmilikPermohonan> getSeneraiPermohonanByidHakmilik(String idHakmilik, String kod) {
//        String query = "Select HP FROM etanah.model.HakmilikPermohonan HP,etanah.model.Permohonan P WHERE HP.hakmilik.idHakmilik = :idHakmilik and HP.permohonan.idPermohonan = P.idPermohonan and P.kodUrusan.kod = :kod";
//        Query q = sessionProvider.get().createQuery(query);
//        q.setParameter("idHakmilik", idHakmilik);
//        q.setParameter("kod", kod);
//        return q.list();
//
//    }
    public List<HakmilikPermohonan> getSeneraiPermohonanByidHakmilik(String idHakmilik, String kod) {

        try {
            String query = "Select HP FROM etanah.model.HakmilikPermohonan HP,etanah.model.Permohonan P WHERE ";

            query += " HP.hakmilik.idHakmilik = :idHakmilik ";
            query += " AND ";
            query += "HP.permohonan.idPermohonan = P.idPermohonan ANd (P.status = 'AK' OR P.status = 'SL' OR P.status = 'TA')";
            query += " AND ";
            query += "P.kodUrusan.kod = :kod ";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("idHakmilik", idHakmilik);
            q.setString("kod", kod);

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikPermohonan> getSeneraiPermohonanByidHakmilik831a(String idHakmilik, String kod) {

        try {
            String query = "Select HP FROM etanah.model.HakmilikPermohonan HP,etanah.model.Permohonan P WHERE ";

            query += " HP.hakmilik.idHakmilik = :idHakmilik ";
            query += " AND ";
            query += "HP.permohonan.idPermohonan = P.idPermohonan ANd (P.status = 'AK' OR P.status = 'SL')";
            query += " AND ";
            query += "P.kodUrusan.kod = :kod ";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("idHakmilik", idHakmilik);
            q.setString("kod", kod);

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Pihak> findAmanahlist(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select f.nama from PermohonanPihak k,"
                + "Permohonan a,"
                + "HakmilikPermohonan b,"
                + "HakmilikPerbicaraan d,"
                + "PerbicaraanKehadiran c,"
                + "Pihak f "
                + "where a.idPermohonan = b.permohonan.idPermohonan "
                + "and b.id = d.hakmilikPermohonan.id "
                + "and d.idPerbicaraan = c.perbicaraan.idPerbicaraan "
                + "and c.pihak.idPermohonanPihak = k.idPermohonanPihak "
                + "and k.pihak.idPihak = f.idPihak "
                + "and c.keterangan = 'Amanah' "
                + "and a.idPermohonan = :idPermohonan ";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permohonan findpermohonanExist(String idPermohonan, long pihak, String kod) {

        try {
            String query = "Select P FROM etanah.model.Pemohon HP,etanah.model.Permohonan P WHERE ";

            query += " P.permohonanSebelum.idPermohonan = :idPermohonan ";
            query += " AND ";
            query += "HP.pihak.idPihak = :pihak ANd (P.status = 'AK' OR P.status = 'TA')";
            query += " AND ";
            query += "P.kodUrusan.kod = :kod ";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("idPermohonan", idPermohonan);
            q.setLong("pihak", pihak);
            q.setString("kod", kod);

            return (Permohonan) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public Pemohon findPemohonByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Pemohon findPemohonByPihak(String idPermohonan, long idPihak) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan and b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameter("idPihak", idPihak);

        return (Pemohon) q.uniqueResult();
    }

    public List<Pemohon> findPemohonByPihak2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    public PermohonanPihak findPihakByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilik.idHakmilik !=null";
        return (PermohonanPihak) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Permohonan findpermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<PermohonanPihak> findPihakByIdMohonList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilik.idHakmilik !=null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public PermohonanPihak findPemohon(String idPermohonan, long idPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan and b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameter("idPihak", idPihak);

        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> findPihak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public PermohonanAduan findPermohonanAduanByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanAduan b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanAduan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findHPByIdMohonSek4(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik and b.permohonan.kodUrusan.kod = 'SEK4'";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).uniqueResult();
    }

    public HakmilikPermohonan findHPByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findHPByIdMH(long idMH) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.id = :idMH";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setLong("idMH", idMH).uniqueResult();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdHidP(String idHakmilik, long idPihak) {
        String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik and h.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setParameter("idPihak", idPihak);

        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    //added by murali
    public List<PermohonanPihak> findPihakByIdMohonListBasedOnIdhakmilikandIdpihak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilik.idHakmilik is not null"
                + " and b.pihak.idPihak is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
}

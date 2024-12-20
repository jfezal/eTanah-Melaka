/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This Service is mainly used for utility 'KUTIPAN DATA'
 * 
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.model.Akaun;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikSebelum;
//import etanah.model.HakmilikLama;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.model.Pihak;
import java.util.ArrayList;
//import etanah.model.KodCawangan;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ei
 */
public class KutipanDataService {

  @Inject
  PermohonanRujukanLuarDAO permohonanrujukanluarDAO;
  @Inject
  HakmilikAsalDAO hakmilikAsalDao;
  @Inject
  FolderDokumenDAO folderDokumenDAO;
  @Inject
  PermohonanUrusanDAO permohonanUrusanDAO;
  @Inject
  PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
  @Inject
  PemohonDAO pemohonDAO;
  @Inject
  PermohonanHubunganDAO permohonanHubunganDAO;
  @Inject
  HakmilikUrusanDAO hakmilikUrusanDAO;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  private static final Logger LOG = Logger.getLogger(KutipanDataService.class);

  public List<Hakmilik> findListHakmilikByKump(int kump) {
    String query = "SELECT hm FROM etanah.model.Hakmilik hm "
            + "WHERE hm.kumpulan = :kumpulan  "
            + "ORDER BY hm.idHakmilik asc";
    Query q = sessionProvider.get().createQuery(query);
    q.setInteger("kumpulan", kump);
    return q.list();
  }

  public Hakmilik findHakmilikVersiSatu(String idHakmilik, String caw) {
    // FIND Hakmilik version 1 : use in utility 'undo tukarganti'
    String query = "SELECT h FROM etanah.model.Hakmilik h "
            + "WHERE h.idHakmilik = :idHakmilik "
            + "and h.kodStatusHakmilik.kod is not 'B' "
            + "and h.noVersiDhde = 1 "
            + "and h.cawangan.kod = :kod";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kod", caw);
    return (Hakmilik) q.uniqueResult();
  }

  public Hakmilik findHakmilikbyIdhakmilikandCaw(String idHakmilik, String caw) {
    // FIND Hakmilik 
    String query = "SELECT h FROM etanah.model.Hakmilik h "
            + "WHERE h.idHakmilik = :idHakmilik "
            + "and h.kodStatusHakmilik.kod is not 'B' "
            + "and h.cawangan.kod = :kod";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kod", caw);
    return (Hakmilik) q.uniqueResult();
  }
  
  public Hakmilik findIDHakmilikbyIdhakmilikandCaw(String idHakmilik, String caw) {
    // FIND Hakmilik utiliti dalaman
    String query = "SELECT h FROM etanah.model.Hakmilik h "
            + "WHERE h.idHakmilik = :idHakmilik "
            + "and h.cawangan.kod = :kod";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kod", caw);
    return (Hakmilik) q.uniqueResult();
  }
  
  public Hakmilik findHakmilikbyIdhakmilik(String idHakmilik) {
    // FIND Hakmilik 
    String query = "SELECT h FROM etanah.model.Hakmilik h "
            + "WHERE h.idHakmilik = :idHakmilik ";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
    return (Hakmilik) q.uniqueResult();
  }

  public List<HakmilikAsal> findListHakmilikAsalByIdHakmilik(String idHakmilik) {
    // find list hakmilik asal
    String query = "SELECT ha FROM etanah.model.HakmilikAsal ha "
            + "WHERE ha.hakmilik.idHakmilik = :idHakmilik  "
            + "ORDER BY ha.hakmilikAsal asc";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public HakmilikAsal findHakmilikAsalByIdAsal(String idHakmilik, String idAsal) {
    // FIND Hakmilik asal
    String query = "SELECT ha FROM etanah.model.HakmilikAsal ha "
            + "WHERE ha.hakmilikAsal = :idAsal "
            + "and ha.hakmilik.idHakmilik = :idHakmilik ";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idAsal", idAsal);
    return (HakmilikAsal) q.uniqueResult();
  }

  public List<HakmilikSebelum> findListHakmilikSebelumByIdHakmilik(String idHakmilik) {
    // find list hakmilik sebelum
    String query = "SELECT hs FROM etanah.model.HakmilikSebelum hs "
            + "WHERE hs.hakmilik.idHakmilik = :idHakmilik  "
            + "ORDER BY hs.hakmilikSebelum asc";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public HakmilikSebelum findHakmilikSebelumByIdSblm(String idHakmilik, String idSblm) {
    // FIND Hakmilik Sebelum
    String query = "SELECT hs FROM etanah.model.HakmilikSebelum hs "
            + "WHERE hs.hakmilikSebelum = :idSblm "
            + "and hs.hakmilik.idHakmilik = :idHakmilik";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idSblm", idSblm);
    return (HakmilikSebelum) q.uniqueResult();
  }

  public List<KodJenisPihakBerkepentingan> findListSenaraiKodPihak() {
    // list of all 'PIHAK' types = 'PEMILIK'
    String query = "SELECT pb FROM etanah.model.KodJenisPihakBerkepentingan pb "
            //+ "WHERE pb.kod in ('PM','PA','PP','PK','RP','WPA','KL','WKL','WS','JG','CP','JA','PUH','PAS','ROS','JK','PLK') "
            + "WHERE pb.aktif='Y' "
            + "ORDER BY pb.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<KodJenisPihakBerkepentingan> findListPenerima(String namaUrusan) {
    List<String> senaraiKodPihak = new ArrayList<String>();
    String query = "Select u from etanah.model.KodJenisPihakBerkepentingan u";
    if (namaUrusan.equalsIgnoreCase("Pindahmilik Tanah")
            || namaUrusan.startsWith("Perintah Mahkamah")
            || namaUrusan.startsWith("Turun Milik")
            || namaUrusan.equalsIgnoreCase("Pindahmilik Tanah melalui Mahkamah")
            || namaUrusan.startsWith("Hakmilik Sementara")
            || namaUrusan.startsWith("Hakmilik Kekal")
            || namaUrusan.startsWith("Perintah Jual")) {
      senaraiKodPihak.add("CP");
      senaraiKodPihak.add("WAR");
      senaraiKodPihak.add("ASL");
      senaraiKodPihak.add("JA");
      senaraiKodPihak.add("JK");
      senaraiKodPihak.add("KL");
      senaraiKodPihak.add("PA");
      senaraiKodPihak.add("PDP");
      senaraiKodPihak.add("PK");
      senaraiKodPihak.add("PLK");
      senaraiKodPihak.add("PM");
      senaraiKodPihak.add("PP");
      senaraiKodPihak.add("RP");
      senaraiKodPihak.add("WKL");
      senaraiKodPihak.add("WPA");
      senaraiKodPihak.add("WS");
      senaraiKodPihak.add("CP");
      senaraiKodPihak.add("WP");
      senaraiKodPihak.add("PH");
    } else if (namaUrusan.startsWith("Gadaian")
            || namaUrusan.equalsIgnoreCase("Pindahmilik Gadaian")) {
      senaraiKodPihak.add("PAG");
      senaraiKodPihak.add("PG");
    } else if (namaUrusan.startsWith("Ismen")) {
      senaraiKodPihak.add("PI");
    } else if (namaUrusan.startsWith("Kaveat Amanah")) {
      senaraiKodPihak.add("PKA");
    } else if (namaUrusan.startsWith("Kaveat Pemegang Lien")) {
      senaraiKodPihak.add("PKL");
    } else if (namaUrusan.startsWith("Kaveat Pendaftar")) {
      senaraiKodPihak.add("PKD");
    } else if (namaUrusan.startsWith("Kaveat Persendirian")) {
      senaraiKodPihak.add("PKS");
    } else if (namaUrusan.startsWith("Pemotongan Kaveat")
            || namaUrusan.startsWith("Tarikbalik Kaveat")
            || namaUrusan.startsWith("Cadangan Pemotongan")
            || namaUrusan.startsWith("Pembatalan Kaveat")) {
      senaraiKodPihak.add("PPK");
    } else if (namaUrusan.startsWith("Mortgage")) {
      senaraiKodPihak.add("PMG");
    } else if (namaUrusan.equalsIgnoreCase("Pajakan Kecil Sebahagian Tanah")) {
      senaraiKodPihak.add("PJK");
    } else if (namaUrusan.startsWith("Pajakan")
            || namaUrusan.equalsIgnoreCase("Pindahmilik Pajakan")
            || namaUrusan.equalsIgnoreCase("Pindahmilik Pajakan Kecil")
            || namaUrusan.equalsIgnoreCase("Gadaian Pajakan")
            || namaUrusan.equalsIgnoreCase("Gadaian Pajakan Kecil")) {
      senaraiKodPihak.add("PJ");
      senaraiKodPihak.add("PJK");
    } else if (namaUrusan.startsWith("Pendaftaran Penghuni")) {
      senaraiKodPihak.add("PUH");
      senaraiKodPihak.add("PAS");
    } else if (namaUrusan.startsWith("With A Right")) {
      senaraiKodPihak.add("ROS");
    } else if (namaUrusan.startsWith("Tenansi")
            || namaUrusan.startsWith("Sewaan")) {
      senaraiKodPihak.add("PY");
    } else if (namaUrusan.startsWith("Pendaftaran Pemegang Amanah")) {
      senaraiKodPihak.add("PA");
      senaraiKodPihak.add("WAR");
    } else if (namaUrusan.equalsIgnoreCase("Pertukaran Pemegang Amanah Atas Perintah Mahkamah")) {
      senaraiKodPihak.add("PA");
    } else if (namaUrusan.equalsIgnoreCase("Pembatalan Pendaftaran Pemegang Amanah")) {
      senaraiKodPihak.add("PM");
    } else if (namaUrusan.startsWith("Tukar Nama")
            || namaUrusan.equalsIgnoreCase("Perletakhakan Berkanun - Borang 30A")
            || namaUrusan.startsWith("Perletakhakan Oleh Mahkamah")
            || namaUrusan.equalsIgnoreCase("Pindahan Kepada Pemegang Harta Akibat Bankrap")) {
        senaraiKodPihak.add("CP");
        senaraiKodPihak.add("WAR");
        senaraiKodPihak.add("ASL");
        senaraiKodPihak.add("JA");
        senaraiKodPihak.add("JK");
        senaraiKodPihak.add("KL");
        senaraiKodPihak.add("PA");
        senaraiKodPihak.add("PDP");
        senaraiKodPihak.add("PK");
        senaraiKodPihak.add("PLK");
        senaraiKodPihak.add("PM");
        senaraiKodPihak.add("PP");
        senaraiKodPihak.add("RP");
        senaraiKodPihak.add("WKL");
        senaraiKodPihak.add("WPA");
        senaraiKodPihak.add("WS");
        senaraiKodPihak.add("CP");
        senaraiKodPihak.add("PAG");
        senaraiKodPihak.add("PG");
        senaraiKodPihak.add("WP");
        senaraiKodPihak.add("PH");
    } else if (namaUrusan.startsWith("Hakmilik Strata (Pendaftaran Perbadanan Pengurusan)")) {
        senaraiKodPihak.add("BP");
    }
    query += " where u.kod in (:list)";
    query += " order by u.nama";
    Query q = sessionProvider.get().createQuery(query);
    q.setParameterList("list", senaraiKodPihak);
    return q.list();
  }

  public List<KodJenisPihakBerkepentingan> findListSenaraiKodPihakN9() {
    // list of all 'PIHAK' types = 'PEMILIK' for N9 only
    String query = "SELECT pb FROM etanah.model.KodJenisPihakBerkepentingan pb "
            + "WHERE pb.kod in ('PM','PA','PP','PK','RP','WPA','KL','WKL','WS','JG','JA','PUH','PAS','JK','PLK') "
            + "and pb.aktif='Y' "
            + "ORDER BY pb.nama asc";
    Query q = sessionProvider.get().createQuery(query);
    return q.list();
  }

  public List<HakmilikPihakBerkepentingan> findListPemilik(String idHakmilik) {
    // list for pemilik
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik and hpb.aktif='Y' "
            + "and hpb.jenis.kod in ('PM','PA','PP','PK','RP','WPA','KL','WKL','WS','WAR','JG','CP','JA','PUH','PAS','ROS','JK','PLK','GD') "
            + "ORDER BY hpb.nama asc";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }
  
  public List<HakmilikPihakBerkepentingan> findListPemilik2(String idHakmilik) {
    // list for pemilik
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik "
            //+ "and hpb.jenis.kod in ('PM','PA','PP','PK','RP','WPA','KL','WKL','WS','WAR','JG','CP','JA','PUH','PAS','ROS','JK','PLK','GD') "
            + "ORDER BY hpb.nama asc";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }  
  
  public List<HakmilikPihakBerkepentingan> findListPemilik1(String idHakmilik) {
    // list for pemilik
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik and hpb.aktif='Y' "
           // + "and hpb.jenis.kod in ('PM','PA','PP','PK','RP','WPA','KL','WKL','WS','WAR','JG','CP','JA','PUH','PAS','ROS','JK','PLK') "
            + "ORDER BY hpb.nama asc";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }
  public List<HakmilikPihakBerkepentingan> findListPemilikAktif(String idHakmilik) {
    // list for pemilik
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik and hpb.aktif='Y' "
            + "ORDER BY hpb.nama asc";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public List<HakmilikPihakBerkepentingan> findListHakmilikPihakByIdUrusan(String idHakmilik, Long idUrusan) {
    // list for Hakmilik_pihak by id urusan
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik =:idHakmilik "
            + "and hpb.perserahan.idUrusan =:idUrusan";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setLong("idUrusan", idUrusan);
    return q.list();
  }

  public List<HakmilikPihakBerkepentingan> findListHakmilikPihakByIdUrusanBatal(String idHakmilik, Long idUrusan) {
    // list for Hakmilik_pihak by id urusan Batal
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik =:idHakmilik "
            + "and hpb.perserahanBatal.idUrusan=:idUrusan";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setLong("idUrusan", idUrusan);
    return q.list();
  }

  public HakmilikPihakBerkepentingan findPemilikbyHakmilikAndPihak(String idHakmilik, String idPihak, String jenis) {
    // FIND HAKMILIK_PIHAK
    String query = "SELECT hpb FROM etanah.model.HakmilikPihakBerkepentingan hpb "
            + "WHERE hpb.hakmilik.idHakmilik = :idHakmilik and hpb.pihak.idPihak = :idPihak"
            + " AND hpb.jenis.kod = :jenis";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik)
            .setString("idPihak", idPihak).setString("jenis", jenis);
    return (HakmilikPihakBerkepentingan) q.uniqueResult();
  }

  public HakmilikPermohonan findMohonHakmilik(String idHakmilik, String idMohon) {
    // FIND MOHON_HAKMILIK
    String query = "SELECT mhm FROM etanah.model.HakmilikPermohonan mhm "
            + "WHERE mhm.hakmilik.idHakmilik = :idHakmilik and mhm.permohonan.idPermohonan = :idPermohonan";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idMohon);
    return (HakmilikPermohonan) q.uniqueResult();
  }

  public List<HakmilikUrusan> findListUrusanByHakmilik(String idHakmilik) {
    // FIND ALL URUSAN-BY-HAKMILIK
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.hakmilik.idHakmilik = :idHakmilik ";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanTukarganti(String idHakmilik) {
    // FIND ALL URUSAN-BY-HAKMILIK
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.hakmilik.idHakmilik = :idHakmilik "
            + "and hu.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') and hu.aktif = 'Y' "
            + "ORDER BY hu.hakmilik.idHakmilik";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanTukargantibyDate(String trhMula, String trhTamat) {
    // FIND LIST TUKARGANTI By DATE - ONLY USE IN 'UTILITI SENARAI TUKARGANTI'
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.kodUrusan.kod in ('HKTHK','HSTHK','HMSC') and hu.aktif = 'Y' "
            + "AND (hu.tarikhDaftar BETWEEN to_date(:trhMula,'dd/MM/yyyy') AND to_date(:trhTamat,'dd/MM/yyyy'))"
            + "ORDER BY hu.hakmilik.idHakmilik";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("trhMula", trhMula).setString("trhTamat", trhTamat);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanByIdMohon(String idMohon) {
    // FIND ALL URUSAN-BY-ID_MOHON
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.idPerserahan = :idPerserahan and hu.aktif = 'Y'";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idPerserahan", idMohon);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanByIdMohonAndKumpHm(String idMohon, Integer kumpHm) {
    // FIND ALL URUSAN-BY-ID_MOHON and KUMPHM
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.idPerserahan = :idPerserahan and hu.hakmilik.kumpulan = :kumpulan";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("idPerserahan", idMohon).setInteger("kumpulan", kumpHm);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanSebelum(String kodUrusan, String idHakmilik) {
    // FIND ID URUSAN SEBELUM
    StringBuilder query = new StringBuilder();
    query.append("SELECT hu FROM etanah.model.HakmilikUrusan hu WHERE hu.hakmilik.idHakmilik = :idHakmilik ");
    if (kodUrusan.equals("GDPJ")) {
      query.append("and hu.kodUrusan.kod in ('PJBT','PJT') ");
    }
    if (kodUrusan.equals("GDPJK")) {
      query.append("and hu.kodUrusan.kod in ('PJKT','PJKBT') ");
    }
    if (kodUrusan.equals("PJKT")) {
      query.append("and hu.kodUrusan.kod in ('PJT') ");
    }
    if (kodUrusan.equals("PJKBT")) {
      query.append("and hu.kodUrusan.kod in ('PJBT') ");
    }
    if (kodUrusan.equals("KVAK") || kodUrusan.equals("KVSK") || kodUrusan.equals("KVPK") || kodUrusan.equals("KVLK")) {
      query.append("and hu.kodUrusan.kod in ('PJT','PJKT','PJKBT','PJTM','PJBT','GD','TEN','KVLP','KVLS','KVLT','IS','ISBLS','GDCE','TENBT')");
    }
    if (kodUrusan.equals("PLK")) {
      query.append("and hu.kodUrusan.kodPerserahan.kod in ('SC') ");
    }
    if (kodUrusan.equals("KVSPC")) {
      query.append("and hu.kodUrusan.kod in ('KVSB','KVSK','KVSMP','KVSP','KVSPT','KVSTB','KVST','KVSTB')");
    }
    query.append("ORDER BY hu.idPerserahan asc ");

    Session session = sessionProvider.get();
    Query q = session.createQuery(query.toString()).setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public List<HakmilikUrusan> findListUrusanByKodSerahAndKump(Integer kump, String kodSerah) {
    // GROUP URUSAN BY KODSERAH
    String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
            + "WHERE hu.hakmilik.kumpulan = :kumpulan "
            + "and hu.kodUrusan.kodPerserahan.kod = :kodSerah "
            + "ORDER BY hu.idPerserahan asc ";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setInteger("kumpulan", kump).setString("kodSerah", kodSerah);
    return q.list();
  }

  public List<KodUrusan> findListkodUrusanByKodSerah(String kodSerah) {
    // GROUP KOD URUSAN BY KODSERAH
    String query = "SELECT ku FROM etanah.model.KodUrusan ku "
            + "WHERE ku.kodPerserahan.kod = :kodSerah "
            + "and ku.jabatan.kod = '16' and ku.aktif='Y' ORDER BY ku.nama asc";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query).setString("kodSerah", kodSerah);
    return q.list();
  }

  public List<KodUrusan> findListkodUrusanNota() {
    // GROUP KOD URUSAN NOTA
    String query = "SELECT ku FROM etanah.model.KodUrusan ku "
            //+ "WHERE ku.kod in ('ABT-D','ABTKB','ADAT','IGSA','IGSA5','IGSA6','IPM','IRM','IROA','IRTB','ITB','ITP','KB','KRM','PTB','PTP','IKOA','N6A','PHKK','PHSK','KOSR','TSSKM','HLLA','PBMM','PBSCM','PREM','PSKM','HLLS','TT','TSM','PBBM','PBCTL','PBCTM','MAJB','MAJD','RKSR','LMTP','HLTE','N7A','PINDE')"
            + "WHERE ku.kodPerserahan.kod = 'N'"
            + "ORDER BY ku.nama asc";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query);
    return q.list();
  }

  public Integer getIdKumpMaxNum() {
    // GET KUMPULAN MAX NUM
    Session s = sessionProvider.get();
    Query q = s.createQuery("SELECT max(kumpulan) FROM etanah.model.Hakmilik");
    return (Integer) q.uniqueResult();
  }

  public Permohonan findIdMohon(String idPermohonan, String kodUrusan) {
    // FIND PERMOHONAN 
    String query = "SELECT m FROM etanah.model.Permohonan m "
            + "WHERE m.idPermohonan = :idPermohonan "
            + "and m.kodUrusan.kod = :kodUrusan";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("kodUrusan", kodUrusan);
    return (Permohonan) q.uniqueResult();
  }

  public Permohonan findIdMohonTukarganti(String idPermohonan, String caw) {
    // FIND PERMOHONAN TUKARGANTI
    String query = "SELECT m FROM etanah.model.Permohonan m "
            + "WHERE m.idPermohonan = :idPermohonan and m.cawangan.kod =:kod and m.keputusan.kod = 'D' "
            + "and m.kodUrusan.kod in ('HKTHK','HSTHK','HMSC')";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("kod", caw);
    return (Permohonan) q.uniqueResult();
  }

  public Permohonan findMohon(String idPermohonan) {
    // USE IN UTILITI UNDO TUKARGANTI
    String query = "SELECT m FROM etanah.model.Permohonan m "
            + "WHERE m.idPermohonan = :idPermohonan and m.keputusan.kod = 'D'";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
    return (Permohonan) q.uniqueResult();
  }

  public KodBandarPekanMukim findKodBPM(String bpm, String kodDaerah) {
    // FIND BPM
    String query = "SELECT bpm FROM etanah.model.KodBandarPekanMukim bpm "
            + "WHERE bpm.bandarPekanMukim = :bpm and bpm.daerah.kod = :kod";
    Query q = sessionProvider.get().createQuery(query).setString("bpm", bpm).setString("kod", kodDaerah);
    return (KodBandarPekanMukim) q.uniqueResult();
  }

  public List<Pihak> findPihakByNama(String namaPihak) {
    try {
      LOG.info("-> nama Pihak : " + namaPihak);
      Session s = sessionProvider.get();
      Query q = s.createQuery("SELECT p FROM etanah.model.Pihak p WHERE p.nama LIKE :nama");
      q.setString("nama", "%" + namaPihak + "%");
      return q.list();
    } finally {
    }
  }

  public List<Pemohon> findListPemohonByIdHakmilikandIdMohon(String idHakmilik, String idMohon) {
    // FIND LIST PEMOHON
    String query = "SELECT p FROM etanah.model.Pemohon p "
            + "WHERE p.hakmilik.idHakmilik = :idHakmilik and p.permohonan.idPermohonan=:idPermohonan "
            + "ORDER BY p.nama asc";
    Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idMohon);
    return q.list();
  }

  public Pemohon findPemohonByIdMohonIdHakmilikandIdPihak(String idMohon, String idHakmilik, Long IdPihak) {
    // FIND PEMOHON
    String query = "SELECT p FROM etanah.model.Pemohon p "
            + "WHERE p.hakmilik.idHakmilik = :idHakmilik and p.permohonan.idPermohonan=:idPermohonan and p.pihak.idPihak=:idPihak";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idPermohonan", idMohon).setString("idHakmilik", idHakmilik).setLong("idPihak", IdPihak);
    return (Pemohon) q.uniqueResult();
  }

  public Pemohon findPemohonByIdPemohon(String idPemohon) {
    return pemohonDAO.findById(Long.valueOf(idPemohon));
  }

  public PermohonanUrusan findMohonUrusan(String idPermohonan) {
    // FIND TABLE MOHON_URUSAN-BY-ID_MOHON
    String query = "SELECT u FROM etanah.model.PermohonanUrusan u "
            + "WHERE u.permohonan.idPermohonan = :idPermohonan";
    Query q = sessionProvider.get().createQuery(query);
    q.setString("idPermohonan", idPermohonan);
    return (PermohonanUrusan) q.uniqueResult();
  }

  public PermohonanRujukanLuar findMohonRujLuarByIdMohonAndHakmilik(String idPermohonan, String idHakmilik) {
    // FIND TABLE MOHON_RUJ_LUAR
    String query = "SELECT mrl FROM etanah.model.PermohonanRujukanLuar mrl "
            + "WHERE mrl.permohonan.idPermohonan = :idPermohonan "
            + "and mrl.hakmilik.idHakmilik = :idHakmilik";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
    return (PermohonanRujukanLuar) q.uniqueResult();
  }

  public List<Dokumen> findListDokumen(KodDokumen kd, String id) {
    // FIND LIST DHKE AND DHDE
    try {
      Session s = sessionProvider.get();
      Query q = s.createQuery("SELECT d FROM etanah.model.Dokumen d "
              + "WHERE d.kodDokumen.kod = :kodDokumen and d.dalamanNilai1 = :id and d.baru = 'Y'");
      q.setParameter("kodDokumen", kd.getKod());
      q.setString("id", id);
      return q.list();
    } finally {
    }
  }

  public Akaun findAkaunForHakmilik(String kodCaw, String idHakmilik, String kodAkaun) {
    String queryStr = "SELECT a FROM Akaun a "
            + "WHERE a.cawangan.id = :kodCawangan "
            + "AND a.kodAkaun.id = :kodAkaun "
            + "AND a.hakmilik.id = :idHakmilik "
            + "AND a.status not in ('B')";
    Query query = sessionProvider.get().createQuery(queryStr);
    query.setString("kodCawangan", kodCaw);
    query.setString("kodAkaun", kodAkaun);
    query.setString("idHakmilik", idHakmilik);
    return (Akaun) query.uniqueResult();
  }

  public List<PermohonanPihak> findListMohonPihak(String idPermohonan, String idHakmilik) {
    // FIND LIST MOHON_PIHAK ("KEPADA")
    Session session = sessionProvider.get();
    Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m "
            + "WHERE m.permohonan.idPermohonan = :idPermohonan "
            + "AND  m.hakmilik.idHakmilik = :idHakmilik");
    q.setParameter("idPermohonan", idPermohonan);
    q.setParameter("idHakmilik", idHakmilik);
    return q.list();
  }

  public PermohonanPihak findMohonPihak(String idPermohonan, String idHakmilik) {
    // FIND MOHON_PIHAK ("KEPADA")
    String query = "SELECT m FROM etanah.model.PermohonanPihak m "
            + "WHERE m.permohonan.idPermohonan = :idPermohonan"
            + " AND  m.hakmilik.idHakmilik = :idHakmilik";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
    return (PermohonanPihak) q.uniqueResult();
  }

  public FolderDokumen findPerserahan(String idPermohonan) {
    String query = "SELECT m FROM etanah.model.FolderDokumen m "
            + "WHERE m.tajuk = :idPermohonan";
    Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
    return (FolderDokumen) q.uniqueResult();
  }

  @Transactional
  public void saveMohonRujLuar(PermohonanRujukanLuar mrl) {
    permohonanrujukanluarDAO.saveOrUpdate(mrl);
  }
  @Transactional
  public void saveFolderDokumen(FolderDokumen fd) {
    folderDokumenDAO.saveOrUpdate(fd);
  }

  @Transactional
  public void simpanHakmilikAsal(HakmilikAsal asal) {
    hakmilikAsalDao.save(asal);
  }

  @Transactional
  public PermohonanUrusan savePermohonanUrusan(PermohonanUrusan pu) {
    return permohonanUrusanDAO.saveOrUpdate(pu);
  }

  @Transactional
  public HakmilikUrusan saveOrUpdateHakmilikUrusan(HakmilikUrusan hu) {
    return hakmilikUrusanDAO.saveOrUpdate(hu);
  }

  @Transactional
  public void delete(PermohonanRujukanLuar rujukanLuar) {
    permohonanRujukanLuarDAO.delete(rujukanLuar);
  }

  @Transactional
  public void deleteMohonHbgn(PermohonanHubungan mohonHbgn) {
    permohonanHubunganDAO.delete(mohonHbgn);
  }
}

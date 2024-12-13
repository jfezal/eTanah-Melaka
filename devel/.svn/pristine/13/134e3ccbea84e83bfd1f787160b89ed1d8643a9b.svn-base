/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.pengambilan.aduan;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AduanBayaranDAO;
import etanah.dao.AduanPerbincanganDAO;
import etanah.dao.AduanSuratDAO;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikPermohonanBaruDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotaSiasatanDAO;
import etanah.dao.NotaSiasatanLengkapDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanAduanDAO;
import etanah.dao.PermohonanMahkamahDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.dao.TuntutanPerPBDAO;
import etanah.model.AduanBayaran;
import etanah.model.AduanPerbincangan;
import etanah.model.AduanSurat;
import etanah.model.Alamat;
import etanah.model.AmbilPampasan;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPermohonanBaru;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNegeri;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAduan;
import etanah.model.PermohonanMahkamah;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.NotaSiasatan;
import etanah.model.ambil.NotaSiasatanLengkap;
//import etanah.model.PermohonanPengambilan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.model.ambil.TuntutanPerPB;
import etanah.report.ReportUtil;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author user
 */
public class AduanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    NotaSiasatanDAO notaSiasatanDAO;
    @Inject
    PermohonanMahkamahDAO permohonanMahkamahDAO;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanAduanDAO permohonanAduanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;

    @Inject
    AduanPerbincanganDAO aduanPerbincanganDAO;

    @Inject
    AduanSuratDAO aduanSuratDAO;

    @Inject
    AduanBayaranDAO aduanBayaranDAO;

    @Inject
    NotaSiasatanLengkapDAO notaSiasatanLengkapDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    HakmilikPermohonanBaruDAO hakmilikPermohonanBaruDAO;
    
    @Inject
    TuntutanPerPBDAO tuntutanPerPBDAO;

    public Permohonan findfromdb(String idPermohonan) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void savePemohon(Permohonan mohon, String nama, String noKp, Alamat alamat,
            String noTel, String email, String hubungan, KodCawangan caw) {
        System.out.println("start savePemohon ");

        System.out.println("value nama ++> " + nama);
        System.out.println("value IdPermohonan ++> " + mohon.getIdPermohonan());

        mohon.setNamaPenerima(nama);
        mohon.setAlamatPenerima1(alamat.getAlamat1());
        mohon.setAlamatPenerima2(alamat.getAlamat2());
        mohon.setAlamatPenerima3(alamat.getAlamat3());
        mohon.setAlamatPenerima4(alamat.getAlamat4());

        Pemohon pemohon = findPemohonByIdmohon(mohon.getIdPermohonan());
        System.out.println(" 1-1 ");

        if (pemohon == null) {

            pemohon = new Pemohon();
            pemohon.setNama(nama);
            System.out.println(" 2 ");
            pemohon.setNoPengenalan(noKp);
            System.out.println(" 3 ");

            pemohon.setAlamat(alamat);

            pemohon.setPermohonan(mohon);

            pemohon.setCawangan(caw);
        } else {

            pemohon.setNama(nama);
            System.out.println(" 2 ");
            pemohon.setNoPengenalan(noKp);
            System.out.println(" 3 ");

            pemohon.setAlamat(alamat);

            pemohon.setPermohonan(mohon);

            pemohon.setCawangan(caw);
        }

        pemohonDAO.save(pemohon);
    }

    @Transactional
    public void savePemohon2(Permohonan mohon, String nama, String noKp, Alamat alamat,
            String noTel, String email, String hubungan, KodCawangan caw, KodUrusan ku, Pengguna peng, KodNegeri kodNegeri, KodJenisPengenalan kjp) {
        System.out.println("start savePemohon2 ");

        System.out.println("value nama ++> " + nama);
        System.out.println("value IdPermohonan ++> " + mohon.getIdPermohonan());

        mohon.setPenyerahNama(nama);
        mohon.setPenyerahAlamat1(alamat.getAlamat1());
        mohon.setPenyerahAlamat2(alamat.getAlamat2());
        mohon.setPenyerahAlamat3(alamat.getAlamat3());
        mohon.setPenyerahAlamat4(alamat.getAlamat4());

        mohon.setPenyerahNoTelefon1(noTel);

        mohon.setPenyerahNoPengenalan(noKp);
        mohon.setPenyerahEmail(email);
        mohon.setPenyerahPoskod(alamat.getPoskod());
        //mohon.setPenyerahJenisPengenalan();
        mohon.setPenyerahJenisPengenalan(kjp);
        mohon.setKodUrusan(ku);
        mohon.setCawangan(caw);
        mohon.setIdWorkflow(ku.getIdWorkflow());
        mohon.setPenyerahNegeri(kodNegeri);

        simpanMohon(mohon);

        System.out.println(" 1-1 ");

    }

    @Transactional
    public void simpanMohon(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void saveMaklumatAduan(PermohonanAduan mohonAduan) {
        System.out.println("Start saveMaklumatAduan");
        permohonanAduanDAO.saveOrUpdate(mohonAduan);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void saveAduanPerbincangan(AduanPerbincangan aduanPerbincgn) {
        System.out.println("Start saveAduanPerbincangan");
        aduanPerbincanganDAO.save(aduanPerbincgn);
    }

    @Transactional
    public void saveAduanSurat(AduanSurat aduanSurat) {
        System.out.println("Start saveAduanSurat");
        aduanSuratDAO.save(aduanSurat);
    }

    @Transactional
    public void saveAduanBayaran(AduanBayaran aduanBayaran) {
        System.out.println("Start saveAduanBayaran");
        aduanBayaranDAO.save(aduanBayaran);
    }

    @Transactional
    public void saveNotaSiasatanLengkap(NotaSiasatanLengkap nSiaLkp) {
        System.out.println("Start saveNotaSiasatanLengkap");
        notaSiasatanLengkapDAO.save(nSiaLkp);
    }

    public List<AduanSurat> findAduanSuratByIDMohon(String id_mohon) {
        System.out.println("start findAduanSuratByIDMohon ");
        String query = "Select m from etanah.model.AduanSurat m WHERE m.permohonan.idPermohonan = :idPermohonan  ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", id_mohon);
        return q.list();
    }

    public AduanSurat findAduanSuratByTimeStamp(String timeStamp) {
        System.out.println("start findAduanSuratByTimeStamp ");
        String query = "Select max(p) FROM etanah.model.AduanSurat p  ";
        Query q = sessionProvider.get().createQuery(query);
        //q.setString("timeStamp", timeStamp);
        return (AduanSurat) q.uniqueResult();
    }

    public Pemohon findPemohonByIdmohon(String idPermohonan) {
        System.out.println("start findPemohonByIdmohon ");
        String query = "Select p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan =:idPermohonan and "
                + "p.kodJnsPemohon.kod = 'PE'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public PermohonanPengambilanHakmilik findMohonPengambilanHakmilikByIdMH(String id_MH) {
        String query = "Select p FROM etanah.model.PermohonanPengambilanHakmilik p where p.id_mh =:idHakmilik ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", id_MH);
        return (PermohonanPengambilanHakmilik) q.uniqueResult();
    }

    public Dokumen generateReport(Permohonan p, Pengguna pengguna) {

        System.out.println(" start generateReport ");
        /// check dgn ida @ wahida nama yg betul
        String reportName = "HSLResitAkuanPenerimaan002_MLK.rdf";
        String params[] = {"p_id_mohon"};
        String values[] = {p.getIdPermohonan()};
        Dokumen dokumen = new Dokumen();
        KodDokumen kd = kodDokumenDAO.findById("UNKN1");
        /// sampai sini

        StringBuilder sb = new StringBuilder();
        String idFolder = String.valueOf(p.getFolderDokumen().getFolderId());
        String dokumenPath = conf.getProperty("document.path");
        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
        dokumen = saveOrUpdateDokumen(p.getFolderDokumen(), kd, sb.toString(), pengguna);
        String path = idFolder + File.separator + String.valueOf(dokumen.getIdDokumen());
        reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen());

        System.out.println("dokumen id ++> " + dokumen.getIdDokumen());
        return dokumen;

    }

    public PermohonanAduan findAduanByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanAduan p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanAduan) q.uniqueResult();
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd,
            KodDokumen kd,
            String id,
            Pengguna pengguna) {

        pengguna = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod());
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Transactional
    public Permohonan saveMohon(Permohonan mohon, Pengguna peng) {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        mohon.setInfoAudit(ia);
        FolderDokumen fd = saveFolderDokumen(ia, mohon.getIdPermohonan());

        mohon.setFolderDokumen(fd);
        permohonanDAO.save(mohon);

        return mohon;
    }

    @Transactional
    public FolderDokumen saveFolderDokumen(InfoAudit ia, String tajuk) {
        FolderDokumen fd = new FolderDokumen();
        fd.setInfoAudit(ia);
        fd.setTajuk(tajuk);
        folderDokumenDAO.save(fd);
        return fd;
    }

    @Transactional
    public void savePermohonan(Permohonan mohon) {
        permohonanDAO.saveOrUpdate(mohon);
    }

    public List<Pemohon> findPermohonByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> findPermohonByIdMohonHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan "
                + "and p.hakmilik.idHakmilik :idHakmilik";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public NotaSiasatan findByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.ambil.NotaSiasatan p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (NotaSiasatan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdMohonIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan "
                + " and p.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        return (HakmilikPermohonan) q.uniqueResult();
    }

    public BorangPerHakmilik findByIdMHNBE(long id, String nbe) {
        String query = "Select p FROM etanah.model.ambil.BorangPerHakmilik p WHERE"
                + " p.hakmilikPermohonan.id = :id "
                + " and p.kodNotis.kod =:nbe ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        q.setString("nbe", nbe);
        return (BorangPerHakmilik) q.uniqueResult();
    }

    public List<BorangPerHakmilik> findByIdMHKodnotis(long id, String Kodnotis) {
        String query = "Select p FROM etanah.model.ambil.BorangPerHakmilik p WHERE"
                + " p.hakmilikPermohonan.id = :id "
                + " and p.kodNotis.kod =:Kodnotis ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        q.setString("Kodnotis", Kodnotis);
        return q.list();
    }

    public NotaSiasatanLengkap findNotaLengkapByNotaIdMohon(long idNota, long id) {
        String query = "Select p FROM etanah.model.ambil.NotaSiasatanLengkap p WHERE "
                + " p.notaSiasatan.idNota = :idNota "
                + " and p.borangPerPB.id = :id";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idNota", idNota);
        q.setLong("id", id);
        return (NotaSiasatanLengkap) q.uniqueResult();
    }

    public List<NotaSiasatanLengkap> findNotaLengkapByNotaId(long idNota) {
        String query = "Select p FROM etanah.model.ambil.NotaSiasatanLengkap p WHERE "
                + " p.notaSiasatan.idNota = :idNota ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idNota", idNota);
        return q.list();
    }

    public PermohonanMahkamah findPermohonanMahkamah(String idPermohonan, String idBorangPerPb) {
        String query = "Select p FROM etanah.model.PermohonanMahkamah p WHERE "
                + " p.permohonan.idPermohonan = :idPermohonan "
                + " and p.borangPerPB.id = :idBorangPerPb";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idBorangPerPb", idBorangPerPb);
        return (PermohonanMahkamah) q.uniqueResult();
    }

    public PermohonanMahkamah findByidPermohonanMahkamah(Long idMohonMahkamah) {
        String query = "Select p FROM etanah.model.PermohonanMahkamah p WHERE "
                + " p.idMm = :idMohonMahkamah";
//                + " and p.borangPerPB.id = :idBorangPerPb";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMohonMahkamah", idMohonMahkamah);
//        q.setString("idBorangPerPb", idBorangPerPb);
        return (PermohonanMahkamah) q.uniqueResult();
    }

    @Transactional
    public void savePermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        permohonanMahkamahDAO.save(permohonanMahkamah);
    }

    @Transactional
    public void delPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        permohonanMahkamahDAO.delete(permohonanMahkamah);
    }

    @Transactional
    public void saveNotaSiasatan(NotaSiasatan nota) {
        notaSiasatanDAO.save(nota);
    }

    @Transactional
    public void saveBorangPerHakmilik(BorangPerHakmilik bph) {
        borangPerHakmilikDAO.saveOrUpdate(bph);
    }

    @Transactional
    public void saveBorangPerPB(BorangPerPB pb) {
        borangPerPBDAO.save(pb);
    }

    @Transactional
    public void deleteNotaSiasatanLengkap(NotaSiasatanLengkap n) {
        notaSiasatanLengkapDAO.delete(n);
    }

    @Transactional
    public void deleteBorangPerPB(BorangPerPB pb) {
        borangPerPBDAO.delete(pb);
    }
    
    @Transactional
    public void deleteTuntutanPerPB(TuntutanPerPB tuntutanPerPB) {
        tuntutanPerPBDAO.delete(tuntutanPerPB);
    }

    public List<BorangPerHakmilik> findListByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.ambil.BorangPerHakmilik p, etanah.model.HakmilikPermohonan hp WHERE "
                + " p.hakmilikPermohonan.id = hp.id "
                + " and hp.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Long findJumlahPihakTuntut(String idPermohonan) {
        String query = "Select count(p) FROM etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph WHERE "
                + " ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Long) q.uniqueResult();
    }

    public BigDecimal findJumlahTuntutan(String idPermohonan) {
        String query = "Select sum(a.amaun) FROM etanah.model.ambil.TuntutanPerPB a,etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph WHERE "
                + "a.borangPerPB.id = p.id and"
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (BigDecimal) q.uniqueResult();
    }

    public List<AmbilPampasan> findAmbilPampasanByIdPermohonan(String idPermohonan) {
        String query = "Select ap FROM etanah.model.AmbilPampasan ap,etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph WHERE  ap.borangPerPb.id = p.id and "
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public AmbilPampasan findAmbilPampasanById(Long id) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.AmbilPampasan m WHERE m.idAmbilPampasan = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", id);
        return (AmbilPampasan) q.uniqueResult();

    }

    public PermohonanPengambilanHakmilik findPermohonanPengambilanHakmilikByIdMH(Long idMohonHakmilik) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.ambil.PermohonanPengambilanHakmilik m WHERE m.hakmilikPermohonan.id = :idMohonHakmilik";
        Query q = s.createQuery(query);
        q.setParameter("idMohonHakmilik", idMohonHakmilik);
        return (PermohonanPengambilanHakmilik) q.uniqueResult();

    }

    public List<HakmilikPermohonanBaru> findHakmilikPermohonanBaruByIdMH(Long idMohonHakmilik) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.HakmilikPermohonanBaru m WHERE m.hakmilikPermohonan.id = :idMohonHakmilik";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMohonHakmilik", idMohonHakmilik);
        return q.list();

//           String query = "Select p FROM etanah.model.ambil.BorangPerPB p where p.borangPerHakmilik.id =:idBorangHm ";
//        Session session = sessionProvider.get();
//        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idBorangHm", idBorangHm);
//        return q.list();
    }

    public List<BorangPerPB> findByidMohonNotAmbilPampasan(String idPermohonan) {
        List<String> d = findByidMohonAmbilPampasan(idPermohonan);
        String query = "Select p FROM etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph WHERE "
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan ";
        if (!d.isEmpty()) {
            query = query + " and p.id not in (:list)";
        }

        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        if (!d.isEmpty()) {
            q.setParameterList("list", d);
        }

        return q.list();
    }

    public List<BorangPerPB> findByidMohonNotAmbilPampasanSek8(String idPermohonan) {
        List<String> d = findByidMohonAmbilPampasanSek8(idPermohonan);
        String query = "Select p FROM etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + " etanah.model.ambil.BorangPerHakmilik ph WHERE "
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan and p.kodNotis.kod = 'NBH'";
        if (!d.isEmpty()) {
            query = query + " and p.id not in (:list)";
        }

        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        if (!d.isEmpty()) {
            q.setParameterList("list", d);
        }

        return q.list();
    }

    public List<String> findByidMohonAmbilPampasanSek8(String idPermohonan) {
        String query = "Select ap.borangPerPb.id FROM "
                + "etanah.model.AmbilPampasan ap, "
                + "etanah.model.ambil.BorangPerPB p, "
                + "etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph "
                + "WHERE ap.borangPerPb.id = p.id and "
                + " p.borangPerHakmilik.id = ph.id and "
                + "ph.kodNotis.kod = 'NBG' and  "
                + "ph.hakmilikPermohonan.id = hp.id and "
                + " hp.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<String> findByidMohonAmbilPampasan(String idPermohonan) {
        String query = "Select ap.borangPerPb.id FROM etanah.model.ambil.TuntutanPerPB a,etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph,etanah.model.AmbilPampasan ap WHERE ap.borangPerPb.id = p.id and "
                + "a.borangPerPB.id = p.id and"
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BorangPerPB> findByIdBorangPerHMByKodNotis(Long idBorangHm, String kodNotis) {
        String query = "Select p FROM etanah.model.ambil.BorangPerPB p where p.borangPerHakmilik.id =:idBorangHm and p.kodNotis.kod =:kodNotis ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBorangHm", idBorangHm);
        q.setString("kodNotis", kodNotis);
        return q.list();
//        return q.list();
    }

    public List<BorangPerPB> findByIdBorangPerHM(Long idBorangHm) {
        String query = "Select p FROM etanah.model.ambil.BorangPerPB p where p.borangPerHakmilik.id =:idBorangHm ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBorangHm", idBorangHm);
        return q.list();
//        return q.list();
    }

    @Transactional
    public void saveAmbilPampasan(AmbilPampasan ambil) {
        ambilPampasanDAO.save(ambil);
    }

    @Transactional
    public void saveAmbilHakmilik(PermohonanPengambilanHakmilik ambilHakmilik) {
        permohonanPengambilanHakmilikDAO.save(ambilHakmilik);
    }

    @Transactional
    public void savePemohonPemaju(Pemohon pemohon) {
        pemohonDAO.save(pemohon);
    }

    @Transactional
    public void saveHakmilikPermohonanBaru(HakmilikPermohonanBaru hakmilikPermohonanBaru) {
        hakmilikPermohonanBaruDAO.save(hakmilikPermohonanBaru);
    }

    public List<Pemohon> findPemohonNotAgensi(String idPermohonan) {
        System.out.println("start findPemohonByIdmohon ");
        String query = "Select p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan =:idPermohonan and "
                + "p.kodJnsPemohon.kod is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMohonHakmilikByIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p where p.permohonan.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan findMohonHakmilikByIdHakmilikIdmohon(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p where p.permohonan.idPermohonan =:idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<BorangPerPB> findBorangPBbyKodNotis(BorangPerHakmilik ph, String nbf) {
        String query = "Select p FROM etanah.model.ambil.BorangPerPB p where p.borangPerHakmilik.id =:id "
                + "and p.kodNotis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", ph.getId());
        q.setString("kod", nbf);
        return q.list();
    }

    @Transactional
    public void saveHakmilikPermohonan(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.save(hp);
    }
    
    
     @Transactional
    public void delete(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.delete(ambilPampasan);
    }
}

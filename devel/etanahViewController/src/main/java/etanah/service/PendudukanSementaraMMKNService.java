/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanMahkamahDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KertasRisalatDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermohonanPihakWakilDAO;
import etanah.model.Dokumen;
import etanah.model.BorangQ;
import etanah.model.FasaPermohonan;
import etanah.model.KertasRisalat;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihakWakil;
import etanah.model.ambil.PerbicaraanKehadiran;

import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Rajesh
 */
public class PendudukanSementaraMMKNService {

    private static final Logger logger = Logger.getLogger(PendudukanSementaraMMKNService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanMahkamahDAO permohonanMahkamahDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    KertasRisalatDAO kertasRisalatDAO;
    @Inject
    PermohonanPihakWakilDAO permohonanPihakWakilDAO;
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    PermohonanKertas permohonanKertas;
    KertasRisalat kertasRisalat;

//    @Transactional
//    public void simpanKertas(PermohonanKertas kertas, PermohonanKertasKandungan kand) {
//        permohonanKertasDAO.saveOrUpdate(kertas);
//        if (kand != null) {
//            permohonanKertasKandunganDAO.saveOrUpdate(kand);
//        }
//    }
    @Transactional
    public PermohonanKertas simpanPermohonanKertas(PermohonanKertas kertas) {
        return permohonanKertasDAO.saveOrUpdate(kertas);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(kertasKandungan);
    }

    @Transactional
    public void simpanKertas(PermohonanKertas kertas, PermohonanKertasKandungan kand) {
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(kertas);
        if (kand != null) {
            permohonanKertasKandunganDAO.saveOrUpdate(kand);
        }
    }

    @Transactional
    public void simpanPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        permohonanMahkamahDAO.saveOrUpdate(permohonanMahkamah);
    }

    @Transactional
    public void simpanPermohonanPihakWakil(PermohonanPihakWakil permohonanPihakWakil) {
        permohonanPihakWakilDAO.saveOrUpdate(permohonanPihakWakil);
    }

    @Transactional
    public void simpanKertasRisalat(KertasRisalat kertasRisalat) {
        kertasRisalatDAO.saveOrUpdate(kertasRisalat);
    }

    @Transactional
    public void simpanPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        perbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
    }

    @Transactional
    public void deleteDokumen(Dokumen dokumen) {
        dokumenDAO.delete(dokumen);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.delete(permohonanKertasKandungan);
    }

    public PermohonanRujukanLuar findByKodDokumen(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='SMMR'";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanRujukanLuar findByACQ(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanRujukanLuar findByREG(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanRujukanLuar mohonRujLuarByADUN(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.agensi.kategoriAgensi.kod = 'ADN'";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanKertasKandungan findByKodIdKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        return (PermohonanKertasKandungan) sessionProvider.get().createQuery(query).setLong("idKertas", idKertas).uniqueResult();
    }

    public PermohonanKertas findByKodIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.tajuk='Draf Kertas Siasatan'";
        return (PermohonanKertas) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanKertas findMMKNByKodIdPermohonan(String idPermohonan, String tajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.tajuk = :tajuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByKodIdPermohonan2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByMahkamah(String idPermohonan, long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.permohonanPihak.idPermohonanPihak=:idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod='MMKN' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMMKNSByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod='MMKNS' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public KertasRisalat findKertasRisalat(String idPermohonan, String idKertas) {
//        String query = "SELECT kr FROM etanah.model.KertasRisalat kr where kr.permohonan.idPermohonan = :idPermohonan and kr.kertas.idKertas = :idKertas";
        String query = "SELECT b FROM etanah.model.KertasRisalat b where b.permohonan.idPermohonan = :idPermohonan and b.idKertas = :idKertas";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idKertas", idKertas);
        return (KertasRisalat) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByIdPermohonanRENC(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod='RENC' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByIdPermohonanMMKND(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod='MMKND' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas getPermohonanKertasByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.permohonanPihak.idPermohonanPihak = null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas getPermohonanKertasByIdMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanKertas m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak");
        q.setParameter("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertas> findMMKNByIdPermohonanList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
//      and b.noRujukan is null

    public List<PermohonanRujukanLuar> findMRLList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findMRLListWartaAsal(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and (b.catatan='Warta Pembetulan' or b.catatan='Warta Asal') ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findMRLListMLK(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.namaPenyedia is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanRujukanLuar findMRL(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByKertas(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil= :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public PermohonanKertasKandungan findbil1ByIdKertas(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findbil1ByIdKertasAndIdRujukan(Long idKertas, int bil, Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.rujukan.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setLong("idRujukan", idRujukan);
//        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findbil1ByIdKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        logger.info("q.list() : " + q.list());
        return q.list();
    }

    public PermohonanKertasKandungan findbil1ByIdKertasnIdMP(Long idKertas, long idPermohonanPihak, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak and b.bil = :bil";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public Dokumen findDokumenByIdDokumen(Long idDokumen) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idDokumen", idDokumen);
        return (Dokumen) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByKertasDesc(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil= :bil order by b.subtajuk DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);

        return q.list();
    }

    public PermohonanKertasKandungan findByKertasDesc1(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil= :bil order by b.idKandungan DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);

        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByKertasSubTajuk(Long idKertas, int bil, String subtajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil= :bil and b.subtajuk LIKE :subtajuk  order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subtajuk", "%" + subtajuk + "%");
        return q.list();
    }

    public PermohonanKertas findAffidavit(String idPermohonan, long idPermohonanPihak, String tajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.tajuk = :tajuk and b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<FasaPermohonan> findFasaPermohonankpsnRisalat(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran ='keputusan_deraf_risalat' order by p.tarikhKeputusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    public List<FasaPermohonan> findFasaPermohonanterima_keputusan_ptd(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran ='terima_keputusan_ptd' order by p.tarikhKeputusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findAffidavitByIdMohon(String idPermohonan, long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanMahkamah findPermohonanMahkamahByidMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanMahkamah m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanMahkamah) q.uniqueResult();

    }

    public BorangQ findPampasanByIdMohon(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.BorangQ m WHERE m.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (BorangQ) q.uniqueResult();
    }

    public PermohonanKertas findMMKNByIdPermohonanAndKodDokumen(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod= :kod ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanKertas) q.uniqueResult();
    }

    public KertasRisalat getKertasRisalat() {
        return kertasRisalat;
    }

    public void setKertasRisalat(KertasRisalat kertasRisalat) {
        this.kertasRisalat = kertasRisalat;
    }
}

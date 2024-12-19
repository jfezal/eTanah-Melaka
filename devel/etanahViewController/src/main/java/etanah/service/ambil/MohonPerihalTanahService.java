/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ambil;

import etanah.dao.*;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.ambil.MohonAgensiTanahDAO;
import etanah.dao.ambil.MohonPerihalTanahDAO;
import etanah.dao.ambil.SyorLaporanTanahDAO;
import etanah.model.Permohonan;
import etanah.model.KodUrusan;
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodAgensi;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodKeadaanTanah;
import etanah.model.ambil.MohonAgensiTanah;
import etanah.model.ambil.MohonKeadaanTanah;
import etanah.model.ambil.MohonLotSepadan;
import etanah.model.ambil.MohonPerihalTanah;
import etanah.model.ambil.SyorLaporanTanah;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import java.util.Date;
import etanah.model.ambil.TampalBorangHakmilik;

/**
 *
 * @author zipzap
 */
public class MohonPerihalTanahService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(InfoWartaService.class);
    @Inject
    MohonPerihalTanahDAO mohonPerihalTanahDAO;
    @Inject
    MohonAgensiTanahDAO mohonAgensiTanahDAO;
    @Inject
    MohonKeadaanTanahDAO MohonKeadaanTanahDAO;
    @Inject
    MohonLotSepadanDAO mohonLotSepadanDAO;
    @Inject
    SyorLaporanTanahDAO syorLaporanTanahDAO;

    public List<MohonPerihalTanah> findMohonPerihalTanahByIdMh(Long idMh) {
        String query = "SELECT h FROM etanah.model.ambil.MohonPerihalTanah h where h.hakmilikPermohonan.id = :idMh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMh", idMh);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }

    public List<KodKeadaanTanah> findKodKeadaanTanahByJenisKeadaan(String jenisKeadaan) {
        String query = "SELECT h FROM etanah.model.KodKeadaanTanah h where h.jenisKeadaan = :jenisKeadaan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setLong("idMh", idMh);
        q.setString("jenisKeadaan", jenisKeadaan);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }

    public List<MohonKeadaanTanah> findmohonKeadaanTanah(String jenisKeadaan) {
        String query = "SELECT h FROM etanah.model.KodKeadaanTanah h where h.jenisKeadaan = :jenisKeadaan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setLong("idMh", idMh);
        q.setString("jenisKeadaan", jenisKeadaan);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }

    public MohonKeadaanTanah findByIdMHAndKod(String idMh, String kodKeadaan) {
        String query = "SELECT h FROM etanah.model.ambil.MohonKeadaanTanah h where h.hakmilikPermohonan.id = :idMh "
                + "and h.kodKeadaanTanah.kod = :kodKeadaan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMh", idMh);
        q.setString("kodKeadaan", kodKeadaan);
        LOG.info("query ::" + query);
        return (MohonKeadaanTanah) q.uniqueResult();
    }

    public List<MohonKeadaanTanah> findByIdMHAndKodAndJenisKeadaan(String idPermohonan, String idMohonHakmilik) {
        String query = "SELECT h FROM etanah.model.ambil.MohonKeadaanTanah h where h.idPermohonan = :idPermohonan "
                + "and h.hakmilikPermohonan.id = :idMohonHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idMohonHakmilik", idMohonHakmilik);
        LOG.info("query ::" + query);
        return q.list();

    }

    public List<MohonKeadaanTanah> findByIdMohon(String idMohonHakmilik, String jenisKeadaan) {
        String query = "SELECT h FROM etanah.model.ambil.MohonKeadaanTanah h where h.hakmilikPermohonan.id = :idMohonHakmilik "
                + "and h.kodKeadaanTanah.jenisKeadaan = :jenisKeadaan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonHakmilik", idMohonHakmilik);
        q.setString("jenisKeadaan", jenisKeadaan);
        LOG.info("query ::" + query);
        return q.list();

    }

    public MohonLotSepadan mohonLotSpdnByJenisSpdn(String idMohonHakmilik, String jenisSempadan) {
        Session s = sessionProvider.get();
        String query = "SELECT h FROM etanah.model.ambil.MohonLotSepadan h where h.hakmilikPermohonan.id = :idMohonHakmilik "
                + "and h.jenisSempadan = :jenisSempadan";
        Query q = s.createQuery(query);
        q.setParameter("idMohonHakmilik", idMohonHakmilik);
        q.setParameter("jenisSempadan", jenisSempadan);
        return (MohonLotSepadan) q.uniqueResult();
    }

    public List<MohonLotSepadan> findByIdMohonMohonLotBySepadan(String idMohonHakmilik, String jenisSempadan) {
        String query = "SELECT h FROM etanah.model.ambil.MohonLotSepadan h where h.hakmilikPermohonan.id = :idMohonHakmilik "
                + "and h.jenisSempadan = :jenisSempadan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonHakmilik", idMohonHakmilik);
        q.setString("jenisSempadan", jenisSempadan);
        LOG.info("query ::" + query);
        return q.list();

    }

    public List<MohonLotSepadan> findByIdMohonMohonLotSepadan(String idMohonHakmilik) {
        String query = "SELECT h FROM etanah.model.ambil.MohonLotSepadan h where h.hakmilikPermohonan.id = :idMohonHakmilik "
                + "and h.jenisSempadan not in ('LampiranD')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonHakmilik", idMohonHakmilik);
//        q.setString("jenisKeadaan", jenisKeadaan);
        LOG.info("query ::" + query);
        return q.list();

    }

    public SyorLaporanTanah findByIdMohonSyorLaporanTanah(String idPermohonan, String item) {
        Session s = sessionProvider.get();
        String query = "SELECT h FROM etanah.model.ambil.SyorLaporanTanah h where h.idPermohonan = :idPermohonan "
                + "and h.item = :item";
        Query q = s.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("item", item);
        return (SyorLaporanTanah) q.uniqueResult();
    }

    public List<SyorLaporanTanah> findByIdMHSyorLaporanTanah(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.ambil.SyorLaporanTanah h where h.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        //q.setString("idMohonHakmilik", idMohonHakmilik);
        LOG.info("query ::" + query);
        return q.list();
    }

    public List<KodAgensi> findJabatanTeknikal() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ka from etanah.model.KodAgensi ka where ka.kategoriAgensi.kod = 'JTK'");
        return q.list();
    }

    public List<MohonAgensiTanah> findJabatanTeknikalList(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ka from etanah.model.ambil.MohonAgensiTanah ka where ka.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void simpanAgensiTanah(MohonAgensiTanah mohonAgensiTanah) {
        mohonAgensiTanahDAO.saveOrUpdate(mohonAgensiTanah);
    }
    
     @Transactional
    public void deleteAgensiTanah(MohonAgensiTanah mohonAgensiTanah) {
        mohonAgensiTanahDAO.delete(mohonAgensiTanah);
    }

    @Transactional
    public void simpanMohonPerihalTanah(MohonPerihalTanah mohonPerihalTanah) {
        mohonPerihalTanahDAO.saveOrUpdate(mohonPerihalTanah);
    }

    @Transactional
    public void simpanMohonKeadaanTanah(MohonKeadaanTanah mohonKeadaanTanah) {
        MohonKeadaanTanahDAO.saveOrUpdate(mohonKeadaanTanah);
    }

    @Transactional
    public void simpanMohonLotSepadan(MohonLotSepadan mohonLotSepadan) {
        mohonLotSepadanDAO.saveOrUpdate(mohonLotSepadan);
    }

    @Transactional
    public void simpanSyorLaporanTanah(SyorLaporanTanah syorLaporanTanah) {
        syorLaporanTanahDAO.saveOrUpdate(syorLaporanTanah);
    }

    @Transactional
    public void deleteMohonLotSepadan(MohonLotSepadan mohonLotSepadan) {
        mohonLotSepadanDAO.delete(mohonLotSepadan);
    }

    @Transactional
    public void deleteMohonKeadaanTanah(MohonKeadaanTanah mohonKeadaanTanah) {
        MohonKeadaanTanahDAO.delete(mohonKeadaanTanah);
    }

}

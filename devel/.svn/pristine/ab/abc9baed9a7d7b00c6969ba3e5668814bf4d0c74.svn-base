/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.KodPeranan;
import org.hibernate.Session;//add by tulasi
import org.hibernate.Query;//add by tulasi
import java.util.List;

/**
 *
 * @author muhammad.amin
 */
public class LaporanTanahService {

    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;//add by tulasi
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;

    @Transactional
    public LaporanTanah simpanLaporanTanah(LaporanTanah laporanTanah) {
        return laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void simpanUpdateOnlyLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.update(laporanTanah);
    }

    @Transactional
    public PermohonanLaporanBangunan simpanLaporanTanahBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        return permohonanLaporanBangunanDAO.saveOrUpdate(permohonanLaporanBangunan);
    }

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fasaPermohonan) {
        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
    }

    @Transactional
    public void updateHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);
    }

    @Transactional
    public void simpanSaveOnlyFasaPermohonan(FasaPermohonan fasaPermohonan) {
        fasaPermohonanDAO.save(fasaPermohonan);
    }

    @Transactional
    public void simpanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    //added by tulasi
    public LaporanTanah findLaporanTanahByIdMohonIdMP(String idPermohonan, String idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan AND u.hakmilikPermohonan.id =:idMH");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();

    }
    //ended by tulasi

    @Transactional
    public void simpanHakmilikImej(ImejLaporan imejLaporan) {
        imejLaporanDAO.saveOrUpdate(imejLaporan);

    }

    @Transactional
    public void updateHakmilikPermohonan(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.saveOrUpdate(hp);
    }

    @Transactional
    public List<ImejLaporan> getLaporImejByHakmilik(String idHakmilik) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public List<LaporanTanah> getSenaraiLaporanTanah(String idPermohonan) {
        String query = "select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan ORDER BY u.hakmilikPermohonan.hakmilik.idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getUtaraLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'U'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getHakmilikImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'H'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

     @Transactional
    public List<ImejLaporan> getHakmilikImejByLaporanIdPanorama(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'P'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
     @Transactional
    public List<ImejLaporan> getHakmilikImejByLaporanIdLampiranD(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'D'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
     @Transactional
    public List<ImejLaporan> getHakmilikImejByLaporanIdLampiranG(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'G'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
    @Transactional
    public List<ImejLaporan> getSelatanLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'S'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getTimurLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'T'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getBaratLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'B'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
    
    @Transactional
    public List<ImejLaporan> getTimurLautLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = '1'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
    @Transactional
    public List<ImejLaporan> getTenggaraLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = '2'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
    @Transactional
    public List<ImejLaporan> getBaratDayaLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = '3'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }
    @Transactional
    public List<ImejLaporan> getBaratLautLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = '4'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void deleteImejLaporanByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporan p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    public ImejLaporan getImejLaporan(Long dokumenId) {
        String query = "Select p FROM etanah.model.ImejLaporan p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        return (ImejLaporan) (q.list().size() > 1 ? q.list().get(0) : q.uniqueResult());
    }

    public ImejLaporan getLaporImejByLaporDokumen(long idLaporan, long idDokumen) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.dokumen.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        q.setLong("idDokumen", idDokumen);
        return (ImejLaporan) q.uniqueResult();
    }

    @Transactional
    public ImejLaporan getLaporImejByHakmilikDokumen(String idHakmilik, long idDokumen) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.hakmilik.idHakmilik = :idHakmilik and i.dokumen.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setLong("idDokumen", idDokumen);
        return (ImejLaporan) q.uniqueResult();
    }

    @Transactional
    public ImejLaporan getLaporImejByDokumen(LaporanTanah laporanTanah, Dokumen dokumen, char pandanganImej) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.dokumen.idDokumen = :idDokumen and i.pandanganImej = :pandanganImej";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", laporanTanah.getIdLaporan());
        q.setLong("idDokumen", dokumen.getIdDokumen());
        q.setCharacter("pandanganImej", pandanganImej);
        return (ImejLaporan) q.uniqueResult();
    }
    /*
     * THIS METHOD SHARED FOR LAPORANTANAHV2..DO NOT MODIFIED WITHOUT CONSULT
     */

    @Transactional
    public List<ImejLaporan> getLaporImejByPndgnImejNlaporTnhSmpdn(LaporanTanah laporanTanah, char pandanganImej, LaporanTanahSempadan lts) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.laporanTanahSempadan.idLaporTanahSpdn = :lts and i.pandanganImej = :pandanganImej";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", laporanTanah.getIdLaporan());
        q.setLong("lts", lts.getIdLaporTanahSpdn());
        q.setCharacter("pandanganImej", pandanganImej);

        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getLaporImejByPndgnImejNlaporTnhSmpdn(LaporanTanah laporanTanah, char pandanganImej, LaporanTanahSempadan lts, String catatan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.catatan = :catatan and i.pandanganImej = :pandanganImej";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", laporanTanah.getIdLaporan());
        // q.setLong("lts", lts.getIdLaporTanahSpdn());
        q.setCharacter("pandanganImej", pandanganImej);
        q.setString("catatan", catatan);

        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getLaporImejByPndgnImejNlaporTnhSmpdn(LaporanTanah laporanTanah, char pandanganImej) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan  and i.pandanganImej = :pandanganImej";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", laporanTanah.getIdLaporan());
        q.setCharacter("pandanganImej", pandanganImej);

        return q.list();
    }

    public List<Dokumen> getDokumenByIdPenguna(String format1, String format2, String userName) {
        String strQuery = "Select d from etanah.model.Dokumen d where (d.format = :format1 OR d.format = :format2) AND d.infoAudit.dimasukOleh.nama = :userName";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("format1", format1);
        q.setString("format2", format2);
        q.setString("userName", userName);
        return q.list();
    }

    public LaporanTanah findLaporanTanahByIdMohonIdMH(String idPermohonan, long idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan = :idPermohonan AND u.hakmilikPermohonan.id = :idMH");
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }
    
    public LaporanTanah findLaporanTanahByIdLapor(String idPermohonan, long idLapor) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan = :idPermohonan AND u.idLaporan = :idLapor");
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idLapor", idLapor);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMohonMH(String idPermohonan, long idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan = :idPermohonan AND u.hakmilikPermohonan.id = :idMH");
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<LaporanTanah> findSenaraiLaporanTanahByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
//        return (LaporanTanah) q.uniqueResult();
    }

    public List<LaporanTanah> findSenaraiLaporanTanahByIdLapor(String idLaporan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.LaporanTanah u where u.idLaporan = :idLaporan");
        q.setString("idLaporan", idLaporan);
        return q.list();
    }

    public List<PermohonanLaporanBangunan> getLaporBngnByIdLaporan(long idLaporan, String kategori) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.laporanTanah.idLaporan = :idLaporan and d.kategori = :kategori";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idLaporan", idLaporan);
        q.setParameter("kategori", kategori);
        return q.list();
    }

    public List<PermohonanLaporanBangunan> getLaporBngnByIdPermohonan(String idPermohonan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void deleteLaporBngn(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        permohonanLaporanBangunanDAO.delete(permohonanLaporanBangunan);

    }

    public PermohonanLaporanUlasan getLaporUlasanByIdMohonJenis(String idPermohonan, String jenisUlasan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.jenisUlasan = :jenisUlasan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan getLaporUlasanByIdMohonJenis(String idPermohonan, String jenisUlasan, String kod) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.jenisUlasan = :jenisUlasan and d.kodPeranan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisUlasan", jenisUlasan);
        q.setParameter("kod", kod);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }
    
    public PermohonanLaporanUlasan getLaporUlasanByIdMohonJenisIdLapor(String idPermohonan, String jenisUlasan, String kod, long idLaporan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.jenisUlasan = :jenisUlasan and d.kodPeranan.kod =:kod and d.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisUlasan", jenisUlasan);
        q.setParameter("kod", kod);
        q.setParameter("idLaporan", idLaporan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan getLaporUlasan(String idPermohonan, String jenisUlasan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.jenisUlasan = :jenisUlasan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan getLaporUlasanByIdMohon(String idPermohonan, String jenisUlasan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.jenisUlasan = :jenisUlasan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan getLaporUlasanByIdMohonidLaporan(String idPermohonan, long idLaporan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idLaporan", idLaporan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    @Transactional
    public PermohonanLaporanUlasan simpanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        return permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    public PermohonanLaporanUlasan findMohonLaporUlas(String idPermohonan, String jenisUlasan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.PermohonanLaporanUlasan u where u.permohonan.idPermohonan = :idPermohonan AND u.jenisUlasan = :jenisUlasan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public PermohonanLaporanUlasan findMohonLaporUlas(String idPermohonan, String jenisUlasan, String jenisLaporan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.PermohonanLaporanUlasan u where u.permohonan.idPermohonan = :idPermohonan AND u.jenisUlasan = :jenisUlasan AND u.jenisLaporan = :jenisLaporan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        q.setString("jenisLaporan", jenisLaporan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public HakmilikUrusan findHakmilikUrusan(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.HakmilikUrusan u where u.hakmilik.idHakmilik = :idHakmilik AND u.kodUrusan.kod = 'STMA'");
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q.uniqueResult();
    }
    //add bangunan for laporan tanah : Syaiful

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<PermohonanLaporanBangunan> getLaporBngnByIdLaporan(String idLaporan) {
//        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.L.idLaporan = :idLaporan";
        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.permohonan.idPermohonan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public List<ImejLaporan> getBngnLaporImejByLaporanId(long idLaporan) {
        String query = "Select i FROM etanah.model.ImejLaporan i WHERE i.laporanTanah.idLaporan = :idLaporan and i.pandanganImej = 'L'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    public List<LaporanTanah> getListLaporanTanah(String idPermohonan) {
        String query = "select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<LaporanTanah> getListLaporanTanah(String idPermohonan, String jenisLaporan) {
        String query = "select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan AND u.jenisLaporan =:jenisLaporan order by u.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        return q.list();

    }

    public LaporanTanah getLaporanTanah(String idPermohonan) {
        String query = "select u from etanah.model.LaporanTanah u where u.permohonan.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public KodPeranan getKodPeranan(String kod) {
        String query = "SELECT d FROM etanah.model.KodPeranan d WHERE d.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kod);
        return (KodPeranan) q.uniqueResult();
    }
}

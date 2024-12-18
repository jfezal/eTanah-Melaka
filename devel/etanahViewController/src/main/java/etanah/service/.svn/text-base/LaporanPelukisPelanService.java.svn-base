/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Permohonan;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanLaporanBangunan;

/**
 *
 * @author Rajesh
 */
public class LaporanPelukisPelanService {

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    HakmilikDAO hakmilikDAO;
    TanahRizabPermohonanDAO tanahrizabpermohonanDAO;
    PermohonanDAO permohonanDAO;
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    PermohonanLaporanPelan permohonanLaporanPelan;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    //added by mr5rule
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    PermohonanLaporanKawasan permohonanLaporanKawasan;
    //end
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    PermohonanManual permohonanManual;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(LaporanPelukisPelanService.class);

    @Inject
    public LaporanPelukisPelanService(TanahRizabPermohonanDAO tanahrizabpermohonanDAO, PermohonanDAO permohonanDAO, HakmilikDAO hakmilikDAO, HakmilikPermohonanDAO hakmilikPermohonanDAO, PermohonanLaporanPelanDAO permohonanLaporanPelanDAO, PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO) {
        this.tanahrizabpermohonanDAO = tanahrizabpermohonanDAO;
        this.permohonanDAO = permohonanDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
        this.permohonanLaporanPelanDAO = permohonanLaporanPelanDAO;
        this.permohonanLaporanKawasanDAO = permohonanLaporanKawasanDAO;
    }

    @Transactional
    public void saveOrUpdateTanahMilik(HakmilikPermohonan h) {
        hakmilikPermohonanDAO.saveOrUpdate(h);
    }

    @Transactional
    public void saveOrUpdatePermohonan(Permohonan p) {
        permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveTanahMilik(List<HakmilikPermohonan> hakmilikPermohonanList) {
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
        }
    }

    @Transactional
    public void deleteTanahMilik(HakmilikPermohonan h) {
        hakmilikPermohonanDAO.delete(h);
    }

    public HakmilikPermohonan findTanahMilikByIdHakmilikPermohonan(String id) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.id = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", id);
        return (HakmilikPermohonan) q.uniqueResult();
    }

//    public PermohonanLaporanPelan findTByIdPermohonan(String id) {
//    String query = "Select p FROM etanah.model.PermohonanLaporanPelan p WHERE p.id = :id";
//    Query q = sessionProvider.get().createQuery(query);
//    q.setString("id", id);
//    return (PermohonanLaporanPelan) q.uniqueResult();
//    }
    @Transactional
    public void saveOrUpdatetanahRizab(TanahRizabPermohonan h) {
        tanahrizabpermohonanDAO.saveOrUpdate(h);
    }

    @Transactional
    public void saveOrUpdate(TanahRizabPermohonan h, Permohonan p) {
        tanahrizabpermohonanDAO.saveOrUpdate(h);
        permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void savetanahrizabpermohonan(List<TanahRizabPermohonan> tanahRizabList) {
        for (TanahRizabPermohonan tanahrizab : tanahRizabList) {
            tanahrizabpermohonanDAO.saveOrUpdate(tanahrizab);
        }
    }

    @Transactional
    public void save(TanahRizabPermohonan rizab) {
        tanahrizabpermohonanDAO.save(rizab);
    }

    @Transactional
    public void update(TanahRizabPermohonan rizab) {
        tanahrizabpermohonanDAO.update(rizab);
    }

    @Transactional
    public void saveOrUpdate(TanahRizabPermohonan rizab) {
        tanahrizabpermohonanDAO.saveOrUpdate(rizab);
    }

    @Transactional
    public void deleteAll(TanahRizabPermohonan h) {
        tanahrizabpermohonanDAO.delete(h);
    }

    @Transactional
    public void deleteAllRL(PermohonanRujukanLuar h) {
        permohonanRujukanLuarDAO.delete(h);

    }

    @Transactional
    public void deleteAllLitho(PermohonanLaporanPelan h) {
        permohonanLaporanPelanDAO.delete(h);

    }

    @Transactional
    public void deleteAllBangunan(PermohonanLaporanBangunan h) {
        permohonanLaporanBangunanDAO.delete(h);

    }

    public TanahRizabPermohonan findTanahRizabByIdTanahRizab(Long idTanahRizabPermohonan) {
        String query = "Select p FROM etanah.model.TanahRizabPermohonan p WHERE p.idTanahRizabPermohonan = :idTanahRizabPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idTanahRizabPermohonan", idTanahRizabPermohonan);
        //q.setString("idH", idH);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public PermohonanRujukanLuar findRujLuarByIdRL(Long idRujukan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanLaporanPelan findLaporPelanByIdlapor(Long idLaporan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanPelan p WHERE p.idLaporan = :idLaporan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanBangunan findLaporPelanByIdlaporBangunan(Long idLaporBangunan) {
        String query = "Select p FROM etanah.model.PermohonanLaporanBangunan p WHERE p.idLaporBangunan = :idLaporBangunan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporBangunan", idLaporBangunan);
        return (PermohonanLaporanBangunan) q.uniqueResult();
    }

    public TanahRizabPermohonan findByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.TanahRizabPermohonan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findByidP(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanPelan h where h.noLitho is null and h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findMohonLaporPelanByidMohon(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanPelan h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        if (!q.list().isEmpty()) {
            return (PermohonanLaporanPelan) q.list().get(0);
        } else {
            return (PermohonanLaporanPelan) q.uniqueResult();
//             return (PermohonanLaporanPelan) q.list().get(0);//data xunique-simulasi
        }

////         if(!q.list().isEmpty()){
////          return (PermohonanLaporanPelan) q.list().get(0);
////         }else{
////             return (PermohonanLaporanPelan) q.uniqueResult();
//        return (PermohonanLaporanPelan) q.list().get(0);//data xunique-simulasi
////         }
////        return (PermohonanLaporanPelan) q.list().get(0);
    }

    public PermohonanLaporanKawasan findByidMohonKodRizab(String idPermohonan, String kodRizab) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan = :idPermohonan AND h.kodRizab.kod = :kodRizab";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodRizab", kodRizab);

        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

    public List<PermohonanLaporanKawasan> findByidMohon(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan = :idPermohonan AND h.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void saveOrUpdateHakmilik(Hakmilik h) {
        hakmilikDAO.saveOrUpdate(h);
    }

    @Transactional
    public void saveOrUpdatePermohonanLaporanPelan(PermohonanLaporanPelan p) {
        permohonanLaporanPelanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatePermohonanLaporanKawasan(PermohonanLaporanKawasan lpk) {
        permohonanLaporanKawasanDAO.saveOrUpdate(lpk);
    }

    @Transactional
    public void saveOrUpdatePermohonanManual(PermohonanManual p) {
        permohonanManualDAO.saveOrUpdate(p);
    }

    public List<PermohonanManual> findPermohonanManualByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanManual h where h.idPermohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    @Transactional
    public void deletePermohonanManual(PermohonanManual permohonanManual) {
        permohonanManualDAO.delete(permohonanManual);
    }

    public List<PermohonanLaporanPelan> findNolitho(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.noLitho is not null and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanLaporanPelan findMohonLaporPelanByidMohonHackmilikMohan(String idPermohonan, Long id) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanPelan h where h.permohonan.idPermohonan = :idPermohonan and h.hakmilikPermohonan.id = :id_mk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id_mk", id);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public List<KodBandarPekanMukim> cariBPM(String kodDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim u where u.daerah.kod =:kodDaerah");
        q.setString("kodDaerah", kodDaerah);
        return q.list();
    }
}

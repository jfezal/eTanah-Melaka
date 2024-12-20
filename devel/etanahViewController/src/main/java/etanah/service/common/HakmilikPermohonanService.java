/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Versi4k;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
public class HakmilikPermohonanService {

    private static final Logger logger = Logger.getLogger(HakmilikPermohonanService.class);
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public boolean saveHakmilikPermohonan(List<HakmilikPermohonan> list) {

        for (HakmilikPermohonan hakmilikPermohonan : list) {
            hakmilikPermohonan = (HakmilikPermohonan) hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
            if (hakmilikPermohonan == null) {
                return false;
            }
        }
        return true;
    }

    public void saveOrUpdateWithoutConnection(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.save(hp);
    }

    @Transactional
    public boolean saveSingleHakmilikPermohonan(HakmilikPermohonan hp) {
        hp = (HakmilikPermohonan) hakmilikPermohonanDAO.saveOrUpdate(hp);
        return (hp != null);
    }

    @Transactional
    public HakmilikPermohonan save(HakmilikPermohonan hp) {
        return hakmilikPermohonanDAO.saveOrUpdate(hp);
    }
    
    public HakmilikPermohonan checkByIdHakmilikIdMohon(String idPermohonan, String idHakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan = :idPermohonan and h.hakmilik.idHakmilik=:idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik",idHakmilik);
        
        HakmilikPermohonan hp;
        
        if (q.list().size() > 1) {
            hp = (HakmilikPermohonan) q.list().get(0);
        } else {
            hp = (HakmilikPermohonan) q.uniqueResult();
        }        

        return hp;        
    }   
    
    public HakmilikPermohonan checkByIdMohonLatest(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan = :idPermohonan ORDER BY h.permohonan.id DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        
        HakmilikPermohonan hp;
        
        if (q.list().size() > 1) {
            hp = (HakmilikPermohonan) q.list().get(0);
        } else {
            hp = (HakmilikPermohonan) q.uniqueResult();
        }        

        return hp;        
    }    

    public List<String> findIdHakmilikByPermohonan(String idPermohonan) {
        String q = "Select hp.hakmilik.idHakmilik FROM etanah.model.HakmilikPermohonan hp " + "WHERE hp.permohonan.idPermohonan = :idPermohonan order by hp.mohon.idPermohonan"; // added by amin khan
        Query query = sessionProvider.get().createQuery(q).setString("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<HakmilikPermohonan> findIdHakmilikSort(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan order by hp.hakmilik.idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<TanahRizabPermohonan> findIdHakmilikTanahAA(String idPermohonan) {
        String q = "Select tr from etanah.model.TanahRizabPermohonan tr where tr.permohonan.idPermohonan = :idPermohonan and tr.rizab.kod ='88'";
        Query query = sessionProvider.get().createQuery(q).setString("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<TanahRizabPermohonan> findIdHakmilikTanahXAA(String idPermohonan) {
        String q = "Select tr from etanah.model.TanahRizabPermohonan tr where tr.permohonan.idPermohonan = :idPermohonan and tr.rizab.kod !='88'";
        Query query = sessionProvider.get().createQuery(q).setString("idPermohonan", idPermohonan);
        return query.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilik(String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdPermohonanOnlyHakmilikAmbil(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilikAmbil = 'A'";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
     public List<HakmilikPermohonan> findByIdPermohonanOnly(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    public List<HakmilikPermohonan> findByIdPermohonanTDK(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik is null";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikAsalPermohonan> findByIdSejHakmilik(String idHakmilik) {
        String query = "Select hap from etanah.model.HakmilikAsalPermohonan hap where hap.hakmilikSejarah = :idHakmilik "
                + "and hap.hakmilikPermohonan.permohonan.status IN ('TA', 'AK')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikSebelumPermohonan> findByIdSejHakmilikSblm(String idHakmilik) {
        String query = "Select hsp from etanah.model.HakmilikSebelumPermohonan hsp where hsp.hakmilikSejarah = :idHakmilik "
                + "and hsp.hakmilikPermohonan.permohonan.status IN ('TA', 'AK')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilikStrataByIdHakmilikInduk(String idHakmilikInduk) {
        String query = "Select h from etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilikInduk AND h.kodStatusHakmilik = 'D'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikAndJabatan(String idHakmilik, String jabatan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik AND hp.permohonan.kodUrusan.jabatan.kod = :jabatan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        q.setParameter("jabatan", jabatan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByNoLotAndJabatan(String noLot, String jabatan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.noLot like :noLot AND hp.permohonan.kodUrusan.jabatan.kod = :jabatan AND hp.permohonan.idPermohonan like '%DIS%'";
        Query q = sessionProvider.get().createQuery(query).setString("noLot", "%" + noLot);
        q.setParameter("jabatan", jabatan);
        return q.list();
    }

    public HakmilikPermohonan findHakmilikPermohonan(String idHakmilik, String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.hakmilik.idHakmilik = :idHakmilik and hp.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    // Added to get HakmilikPerbicaraan based on IdHakmilikPermohonan --- Imran Khan
    public List<HakmilikPerbicaraan> findByIdHakmilikPermohonan(long idHakmilikPermohonan) {
        String q = "Select ambil FROM etanah.model.ambil.HakmilikPerbicaraan ambil "
                + "WHERE ambil.hakmilikPermohonan.id = :idHakmilikPermohonan order by tarikhBicara desc";
        Query query = sessionProvider.get().createQuery(q).setLong("idHakmilikPermohonan", idHakmilikPermohonan);
        return query.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikL4(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp,Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan "
                + "and hp.nomborRujukan = 'L4' and hp.luasTerlibat != hp.hakmilik.luas and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikK4(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp,Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan "
                + "and hp.nomborRujukan = 'K4' and hp.luasTerlibat != hp.hakmilik.luas and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikX4(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp,Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan "
                + "and hp.nomborRujukan = 'X4' and hp.luasTerlibat != hp.hakmilik.luas and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdPermohonan(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikLebihKecil(String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.hakmilik.idHakmilik = :idHakmilik "
                + "and hp.nomborRujukan = 'L4' and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikLebihBesar(String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.hakmilik.idHakmilik = :idHakmilik "
                + "and hp.nomborRujukan = 'K4' and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikSamaDengan(String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik h where hp.hakmilik.idHakmilik = h.idHakmilik and hp.hakmilik.idHakmilik = :idHakmilik "
                + "and hp.nomborRujukan = 'X4' and (hp.penarikBalikan='0' or hp.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findIDMHByIDMohon(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan "
                + "and m.hakmilikAmbil = 'A' "
                + "and (m.penarikBalikan = '0' or m.penarikBalikan is null)";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIDMHByIDMohonIDHakmilikXnull(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and (m.penarikBalikan = '0' or m.penarikBalikan is null) and m.hakmilik.idHakmilik is not null  ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIDMHByIDMohonSebahagian(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and (m.penarikBalikan = '0' or m.penarikBalikan is null) and m.luasTerlibat != m.hakmilik.luas  ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIDMHByIDMohonPBA(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan "
                + "and m.hakmilikAmbil = 'A' ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIDMHByIDMohonPenarikan(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and m.penarikBalikan = '1'   ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> carianHakmilikBetween(String idPermohonan, String idHakmilikMula, String idHakmilikAkhir) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan "
                + "and m.hakmilik.idHakmilik between :idHakmilikMula and :idHakmilikAkhir ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikMula", idHakmilikMula);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilikAkhir", idHakmilikAkhir);
        return q.list();

    }
        public List<Hakmilik> carianHakmilikBetweenHakmilik(String idHakmilikMula, String idHakmilikAkhir) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.hakmilik m WHERE m.idHakmilik between :idHakmilikMula and :idHakmilikAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikMula", idHakmilikMula);
        q.setString("idHakmilikAkhir", idHakmilikAkhir);
        return q.list();

    }

    public List<Hakmilik> carianHakmilikStrataBetween(String idHakmilikMula, String idHakmilikAkhir) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.Hakmilik m WHERE m.idHakmilik between :idHakmilikMula and :idHakmilikAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikMula", idHakmilikMula);
        q.setString("idHakmilikAkhir", idHakmilikAkhir);
        return q.list();
    }

    public List<Versi4k> carianVersiHakmilikStrataBetween(String idHakmilikMula, String idHakmilikAkhir) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.Versi4k m WHERE m.idHakmilikStrata between :idHakmilikMula and :idHakmilikAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikMula", idHakmilikMula);
        q.setString("idHakmilikAkhir", idHakmilikAkhir);
        return q.list();

    }
    
    public Versi4k findVersiStrataByhakmilikStrata(String idHakmilikStrata) {
        Session s = sessionProvider.get();
        String query = "Select m from etanah.model.Versi4k m WHERE m.idHakmilikStrata = :idHakmilikStrata";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikStrata", idHakmilikStrata);
        return (Versi4k) q.uniqueResult();

    }

    public List<HakmilikPermohonan> findIdPermohonanbyIdHakmilikENF(String idHakmilik) {
        String query = "SELECT mh FROM etanah.model.HakmilikPermohonan mh WHERE mh.hakmilik.idHakmilik = :idHakmilik AND mh.permohonan.kodUrusan.kod IN ('49','100','127','351','352','422','423','424','427','428','429','425','425A','426')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonanByKodUrusanAktif(String idHakmilik, List kodUrusan) {

        StringBuilder query = new StringBuilder("SELECT mh FROM etanah.model.HakmilikPermohonan mh ")
                .append("WHERE mh.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND mh.permohonan.status IN ('TA', 'AK') ")
                .append("AND mh.permohonan.kodUrusan.kod in (:list)");

        Query q = sessionProvider.get().createQuery(query.toString())
                .setString("idHakmilik", idHakmilik)
                .setParameterList("list", kodUrusan);
        return q.list();
    }

    public HakmilikPermohonan findIdhakmilikbyIdPermohonanENF(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = s.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
        

    }

    public HakmilikPermohonan findIdhakmilikbyIdPermohonan2nLast(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan order by m.id asc ";
        Query q = s.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        //return (HakmilikPermohonan) q.uniqueResult();
       
        HakmilikPermohonan hmp;
        if (q.list().size() > 1) {
            hmp = (HakmilikPermohonan) q.list().get(0);
        } else {
            hmp = (HakmilikPermohonan) q.uniqueResult();
        }
        return hmp;        

    }    
    
    public HakmilikPermohonan findHakmilikPermohonanbyidMH(Long id) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.id = :id";
        Query q = s.createQuery(query);
        q.setParameter("id", id);
        return (HakmilikPermohonan) q.uniqueResult();

    }
    
     public List<HakmilikPermohonan> findHakmilikByIdMohon(String idPermohonan) {
        String query = "Select m from etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
}

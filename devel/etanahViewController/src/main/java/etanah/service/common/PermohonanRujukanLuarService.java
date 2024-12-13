/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.ambil.PermohonanPengambilan;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.dao.StatusTanahLepasPengambilanDAO;
import etanah.model.Dokumen;

/**
 *
 * @author fikri
 */
public class PermohonanRujukanLuarService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PermohonanDAO PermohonanDAO;
    @Inject
    StatusTanahLepasPengambilanDAO statusTanahLepasPengambilanDAO;

    @Transactional
    public void simpanAmbil(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.saveOrUpdate(permohonanPengambilan);
    }

    @Transactional
    public void simpanrujluar(Permohonan p) {
        PermohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void savePermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.save(permohonanPengambilan);
    }
    @Transactional
    public void save(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.save(permohonanRujukanLuar);
    }

    @Transactional
    public void update(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.update(permohonanRujukanLuar);
    }

    @Transactional
    public void delete(PermohonanRujukanLuar rujukanLuar) {
        permohonanRujukanLuarDAO.delete(rujukanLuar);
    }

    @Transactional
    public PermohonanRujukanLuar saveOrUpdate(PermohonanRujukanLuar permohonanRujukanLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    public PermohonanRujukanLuar saveOrUpdateByConn(PermohonanRujukanLuar permohonanRujukanLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);

    }

    @Transactional
    public void saveOrUpdate(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        for (PermohonanRujukanLuar rujukanLuar : senaraiRujukanLuar) {
            saveOrUpdateByConn(rujukanLuar);
        }
    }

    public PermohonanRujukanLuar findById(Long Id) {

        return permohonanRujukanLuarDAO.findById(Id);
    }

    public PermohonanRujukanLuar findByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.kodRujukan.kod='NF' and h.tarikhLulus is null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanRujukanLuar) q.uniqueResult();
    }
    
    public PermohonanRujukanLuar findByidPermohonan2(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        
        PermohonanRujukanLuar prl;
        
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        //return prl;     
        
        return prl;
    }
    
    public PermohonanRujukanLuar checkByIdHakmilikIdMohon(String idPermohonan, String idHakmilik) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.hakmilik.idHakmilik=:idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik",idHakmilik);
        
        PermohonanRujukanLuar prl;
        
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }        

        return prl;        
    }
     

    public List<PermohonanRujukanLuar> findByidPermohonanList(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.tarikhLulus is null ";//and h.tarikhLulus is not null
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<PermohonanRujukanLuar> findMohonRujukLuarIdHakmilikIdPermohonanList(String idHakmilik, String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.hakmilik.idHakmilik=:idHakmilik order by h.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    public PermohonanRujukanLuar findByidPermohonanNoRujsurat(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.kodRujukan.kod='NF' and h.tarikhLulus is null ";//and h.noRujukan is not null 
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanRujukanLuar) q.uniqueResult();
    }
     public PermohonanRujukanLuar findByidPermohonanNoRujsuratNew(String idPermohonan, String kodRuj) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan "
                + "and h.kodRujukan.kod = :kodRuj ";//and h.noRujukan is not null 
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodRuj", kodRuj);

        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findByidPermohonanListDuplicate(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan  ";//and h.tarikhLulus is not null
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPengambilan> findByidPermohonanListDuplicatePengambilan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.ambil.PermohonanPengambilan h where h.permohonan.idPermohonan = :idPermohonan ";//and h.tarikhLulus is not null
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> findDokumenByidMohonANDidHakmilik(String idPermohonan, String idHakmilik) {
        String query = "SELECT d FROM etanah.model.Dokumen d where d.permohonan.idPermohonan = :idPermohonan "
                + "and d.hakmilik = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        return q.list();
    }

    public List<Dokumen> findDokumenByidMohonANDidHakmilikANDnoRUJ(String idPermohonan, String idHakmilik, String noRujukan) {
        String query = "SELECT d FROM etanah.model.Dokumen d where d.permohonan.idPermohonan = :idPermohonan "
                + "and d.hakmilik = :idHakmilik "
                + "and d.noRujukan = :noRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noRujukan", noRujukan);

        return q.list();
    }

    public List<PermohonanRujukanLuar> findByidPermohonanListBorangK(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan = :idPermohonan and h.tarikhLulus is not null ";//and h.tarikhLulus is not null
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }


    public PermohonanRujukanLuar findMohonRujukLuarIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.hakmilik.idHakmilik=:idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        PermohonanRujukanLuar prl = (PermohonanRujukanLuar) q.uniqueResult();
        return prl;
    }

    public PermohonanPengambilan findByidP(String idPermohonan) {

        String query = "SELECT h FROM etanah.model.ambil.PermohonanPengambilan h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanPengambilan) q.uniqueResult();
    }

    public PermohonanRujukanLuar findMohonRujukLuarIdHakmilikIdPermohonan(String idHakmilik, String idPermohonan) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.hakmilik.idHakmilik=:idHakmilik "
                + "and h.permohonan.idPermohonan =:idPermohonan "
                + "and h.ulasanMandatori is null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);

        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }
    
    public PermohonanRujukanLuar findMohonRujukLuarIdMohonFirst(String idPermohonan) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where "
                + "h.permohonan.idPermohonan =:idPermohonan "
                + "order by h.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }    
    
    public PermohonanRujukanLuar findByidPermohonanHSC(String idPermohonan, String idHakmilik) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan =:idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);

        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }    

    public PermohonanRujukanLuar findMohonRujukLuarIdHakmilikIdPermohonanCatatanBETUR(String idHakmilik, String idPermohonan) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.hakmilik.idHakmilik=:idHakmilik and h.permohonan.idPermohonan =:idPermohonan and h.catatan like 'Kemasukan BETUR'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        PermohonanRujukanLuar prl = (PermohonanRujukanLuar) q.uniqueResult();
        return prl;
    }
    
    public PermohonanRujukanLuar findMohonRujukLuarIdHakmilikIdPermohonanCatatanBETUR2(String idHakmilik, String idPermohonan, long idPembetulan) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.hakmilik.idHakmilik=:idHakmilik and h.permohonan.idPermohonan =:idPermohonan and h.nilai2 =:idPembetulan and h.catatan like 'Kemasukan BETUR'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPembetulan", idPembetulan);
        PermohonanRujukanLuar prl = (PermohonanRujukanLuar) q.uniqueResult();
        return prl;
    }

    public StatusTanahLepasPengambilan findMohonLPSAmbilByIdMohonKodLPS(String idPermohonan, String kod) {
        String query = "SELECT h FROM etanah.model.StatusTanahLepasPengambilan h where h.idPermohonan.idPermohonan = :idPermohonan and h.kodStatusTanahLepasPengambilan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (StatusTanahLepasPengambilan) q.uniqueResult();
    }

    @Transactional
    public StatusTanahLepasPengambilan saveOrUpdateStatusTanahLepasPengambilan(StatusTanahLepasPengambilan statusTanahLepasPengambilan) {
        return statusTanahLepasPengambilanDAO.saveOrUpdate(statusTanahLepasPengambilan);
    }

    @Transactional
    public void deleteStatusTanahLepasPengambilan(StatusTanahLepasPengambilan statusTanahLepasPengambilan) {
        statusTanahLepasPengambilanDAO.delete(statusTanahLepasPengambilan);
    }
}

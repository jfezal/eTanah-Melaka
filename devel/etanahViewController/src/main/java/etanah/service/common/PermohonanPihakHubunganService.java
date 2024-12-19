package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.model.PermohonanPihakHubungan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ${user}
 */
public class PermohonanPihakHubunganService {

    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
     @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public PermohonanPihakHubungan findById(String id) {
        return permohonanPihakHubunganDAO.findById(Long.parseLong(id));
    }
    
    public List<PermohonanPihakHubungan> findMohonPihakByIdMohonAndIdHakmilik(String idPermohonanPihak) {
        String query = "Select pph from etanah.model.PermohonanPihakHubungan pph where pph.pihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonanPihak", idPermohonanPihak);
        return q.list();
    }
    

    @Transactional
    public void save(List<PermohonanPihakHubungan> senaraiPihak) {
        for (PermohonanPihakHubungan permohonanPihakHubungan : senaraiPihak) {
            if (permohonanPihakHubungan == null) {
                continue;
            }
            permohonanPihakHubunganDAO.saveOrUpdate(permohonanPihakHubungan);
        }
    }

    @Transactional
    public void delete(PermohonanPihakHubungan pihak) {
        permohonanPihakHubunganDAO.delete(pihak);
    }

    @Transactional
    public void save(PermohonanPihakHubungan pihak) {
        permohonanPihakHubunganDAO.saveOrUpdate(pihak);
    }
}

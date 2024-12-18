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

/**
 *
 * @author nordiyana
 */
public class rujukluarpengambilan {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;

    @Inject
    PermohonanDAO PermohonanDAO;

    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;

      @Transactional
    public void save(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.saveOrUpdate(permohonanPengambilan);
    }

    @Transactional
    public void update(PermohonanRujukanLuar permohonanRujukanLuar){
        permohonanRujukanLuarDAO.update(permohonanRujukanLuar);
    }

    public PermohonanRujukanLuar findById(Long Id){
        return permohonanRujukanLuarDAO.findById(Id);
    }

   public PermohonanPengambilan findByidP (String idPermohonan)
{
String query = "SELECT h FROM etanah.model.ambil.PermohonanPengambilan h where h.permohonan = :idPermohonan";
Session session = sessionProvider.get();
Query q = session.createQuery(query);
q.setString("idPermohonan", idPermohonan);

return (PermohonanPengambilan) q.uniqueResult();

}
}

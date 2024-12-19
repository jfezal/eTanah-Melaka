/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.TanahRizabPermohonan;
import etanah.model.Permohonan;
import etanah.util.StringUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.model.Dokumen;
import etanah.model.KodRizab;
/**
 *
 * @author nordiyana
 */
public class TanahRizabService {
    TanahRizabPermohonanDAO tanahrizabpermohonanDAO;
    PermohonanDAO permohonanDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(TanahRizabService.class);

    @Inject
    public TanahRizabService(TanahRizabPermohonanDAO tanahrizabpermohonanDAO, PermohonanDAO permohonanDAO) {
        this.tanahrizabpermohonanDAO = tanahrizabpermohonanDAO;
        this.permohonanDAO = permohonanDAO;
    }
    @Transactional
     public void deleteAll(TanahRizabPermohonan h) {
        tanahrizabpermohonanDAO.delete(h);
     }

    @Transactional
    public void saveOrUpdate(TanahRizabPermohonan h, Permohonan p) {
        tanahrizabpermohonanDAO.saveOrUpdate(h);
        permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatetanahRizab(TanahRizabPermohonan h) {
        tanahrizabpermohonanDAO.saveOrUpdate(h);
    }
    @Transactional
    public void save(TanahRizabPermohonan h){
       tanahrizabpermohonanDAO.save(h);
    }

    @Transactional
    public void update(TanahRizabPermohonan h){
       tanahrizabpermohonanDAO.update(h);
    }

    @Transactional
    public void savetanahrizabpermohonan(List<TanahRizabPermohonan> tanahRizabList) {
         for (TanahRizabPermohonan tanahrizab : tanahRizabList) {
            tanahrizabpermohonanDAO.saveOrUpdate(tanahrizab);
         }
    }

     public TanahRizabPermohonan findByidPermohonan (String idPermohonan)
{
        String query = "SELECT h FROM etanah.model.TanahRizabPermohonan h where h.permohonan = :idPermohonan";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("idPermohonan", idPermohonan);

            return (TanahRizabPermohonan) q.uniqueResult();

}

     //added by mr5rule
       public KodRizab findByKod (String kod)
{
        String query = "SELECT k FROM etanah.model.KodRizab k where k.kod = :kod";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod);

            return (KodRizab) q.uniqueResult();

}
       //end
    

   public TanahRizabPermohonan findTanahRizabByIdTanahRizab(Long idTanahRizabPermohonan) {
    String query = "Select p FROM etanah.model.TanahRizabPermohonan p WHERE p.idTanahRizabPermohonan = :idTanahRizabPermohonan";
    Query q = sessionProvider.get().createQuery(query);
    q.setLong("idTanahRizabPermohonan", idTanahRizabPermohonan);
    //q.setString("idH", idH);
    return (TanahRizabPermohonan) q.uniqueResult();
    }


//    @Inject
//    public TanahRizabPermohonan findByLitho(String idPermohonan) {
//        return tanahrizabpermohonanDAO.                      (idPermohonan);
//    }

   public List<Dokumen> getDokumenByIdPenguna(String format,String userName) {
        String strQuery = "Select d from etanah.model.Dokumen d where d.format = :format AND d.infoAudit.dimasukOleh.nama = :userName";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("format", format);
        q.setString("userName", userName);
        return q.list();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;

import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.PermohonanRujukanLuar;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.view.stripes.hasil.PermohonanHasilActionBean;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author mohd.fairul
 */
public class NotaService {
    @Inject
    PermohonanRujukanLuarDAO permohonanrujukanluarDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDOA;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(NotaService.class);


//    @Transactional
//    public void simpanPermohonanRujLuarUpdate(List <PermohonanRujukanLuar> permohonanrujluarList){
//        for (PermohonanRujukanLuar permohonanRujLuar : permohonanrujluarList) {
//        permohonanrujukanluaDAO.saveOrUpdate(permohonanRujLuar);
//        }
//    }

    
    @Transactional
    public String saveOrUpdate(PermohonanRujukanLuar permohonanRujukanLuar, Pengguna pengguna, Permohonan permohonan){
        LOG.info("permohonanRujukanLuar:start");
        LOG.debug(permohonanRujukanLuar.getIdRujukan());
        String result = null;
        
        InfoAudit info = permohonan.getInfoAudit();
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        try{
            
            if(permohonanRujukanLuar.getPermohonan().getIdPermohonan() == null){
                LOG.info("test");
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanRujukanLuar.setInfoAudit(info);
                permohonanrujukanluarDAO.save(permohonanRujukanLuar);
                result = "save";
            }else{
                 LOG.info("test1");
                permohonanRujukanLuar.setInfoAudit(info);
                permohonanrujukanluarDAO.update(permohonanRujukanLuar);
                result = "update";
            }
        }catch(Exception ex){
            result = ex.toString();
            LOG.error("permohonanRujukanLuar ex: "+ex);
        }
        LOG.info("permohonanRujukanLuar:finish");
       return result;
    }

     @Transactional
    public void simpanPermohonanRujLuar(PermohonanRujukanLuar permohonanrujluar){

        permohonanrujukanluarDAO.saveOrUpdate(permohonanrujluar);

    }
    
    public void simpanPermohonanRujLuarWithTransaction(PermohonanRujukanLuar permohonanrujluar){

        permohonanrujukanluarDAO.saveOrUpdate(permohonanrujluar);

    }

     @Transactional
    public void simpanSingle(HakmilikPermohonan hakmilikPermohonan){
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

      @Transactional
    public void simpanPihak(Pihak p){
       pihakDAO.saveOrUpdate(p);
    }
       @Transactional
    public void simpanMohon(PermohonanPihak MP){
       permohonanPihakDOA.saveOrUpdate(MP);
    }


  @Transactional
    public void deleteMohon(PermohonanPihak MP){
       permohonanPihakDOA.delete(MP);
    }
     

     public PermohonanRujukanLuar findByidPermohonan (String idPermohonan)
     {
        String query = "SELECT h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan = :idPermohonan";
       
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
       
        LOG.info("query ::" + query);
        LOG.info("findByIdPerserahan :: finish");
        return (PermohonanRujukanLuar) q.uniqueResult();
      
    }

      public PermohonanPihak findByidPermohonanAndHakmilik (String idPermohonan,String idHakmilik )
     {
        String query = "SELECT h FROM etanah.model.PermohonanPihak h where h.permohonan = :idPermohonan and h.hakmilik = :idHakmilik";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        LOG.info("query ::" + query);
        LOG.info("findByIdPerserahan :: finish");
        return (PermohonanPihak) q.uniqueResult();
    }

       public HakmilikPermohonan findByidPermohonanAndHakmilikMohonHakmilik (String idPermohonan,String idHakmilik )
     {
        String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan = :idPermohonan and h.hakmilik = :idHakmilik";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        LOG.info("query ::" + query);
        LOG.info("findByIdPerserahan :: finish");
        return (HakmilikPermohonan) q.uniqueResult();
    }

       public List<PermohonanPihak> findByidPermohonanAndHakmilikList (String idPermohonan,String idHakmilik )
     {
        String query = "SELECT h FROM etanah.model.PermohonanPihak h where h.permohonan = :idPermohonan and h.hakmilik = :idHakmilik";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        LOG.info("query ::" + query);
        LOG.info("findByIdPerserahan :: finish");
        return q.list();
    }

       public PermohonanPihak findByidPermohonanAndHakmilikidPihak (String idPermohonan,String idHakmilik, String idPihak )
     {
        String query = "SELECT h FROM etanah.model.PermohonanPihak h where h.permohonan = :idPermohonan and h.hakmilik = :idHakmilik and h.pihak = :idPihak";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPihak", idPihak);

        LOG.info("query ::" + query);
        LOG.info("findByIdPerserahan :: finish");
        return (PermohonanPihak) q.uniqueResult();
    }

     public List<KodSekatanKepentingan> cariSekatanKepentingan(String sekatan, String kod, String kodCaw)
     {
     String query = "Select u from etanah.model.KodSekatanKepentingan u where u.cawangan.kod = :kodCaw ";
//         String query = "Select u from etanah.model.KodSekatanKepentingan u ";
      if ((kod != null)&&(sekatan != null)) {
          query += "AND u.kodSekatan LIKE :kod ";
          query += "AND u.sekatan LIKE :sekatan ";
            }
         else if ((kod != null)&&(sekatan == null)) {
          query += "AND u.kodSekatan LIKE :kod ";
//          query += "AND u.sekatan LIKE :sekatan ";
            }
         else if ((kod == null)&&(sekatan != null)) {
//          query += "WHERE u.kodSekatan LIKE :kod ";
          query += "AND u.sekatan LIKE :sekatan ";
            }
      Query q = sessionProvider.get().createQuery(query);
       
        if ((kod != null)&&(sekatan != null)) {
                q.setString("kod", kod + "%");
                q.setString("sekatan", "%" + sekatan + "%");
            }
      else if ((kod != null)&&(sekatan == null)) {
                q.setString("kod", "%" + kod + "%");
//                q.setString("sekatan", sekatan + "%");
            }
      else if ((kod == null)&&(sekatan != null)) {
//                q.setString("kod", kod + "%");
                q.setString("sekatan", "%" + sekatan + "%");
            }

      q.setString("kodCaw", kodCaw);
      LOG.info("Kod cAw"+ kodCaw);
      LOG.info("Query"+ query);
      return q.list();
     }

     public List<KodSyaratNyata> cariSyarat(String urusan,  String kod, String kodCaw){
         LOG.info("This is code syarat::"+ kod);
          String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kodCaw ";
//          String query = "Select u from etanah.model.KodSyaratNyata u ";
           if ((kod != null) && (urusan != null)) {
                query += "AND u.kodSyarat LIKE :kod ";
                query += "AND u.syarat LIKE :urusan ";
            }
           else if ((kod != null)&& (urusan == null)) {
                    query += "AND u.kodSyarat LIKE :kod ";
                }
          else if ((kod == null)&& (urusan != null)) {
                    query += "AND u.syarat LIKE :urusan ";
          }
          Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
             if ((kod != null)&& (urusan != null)) {
                q.setString("kod", "%" + kod + "%");
                q.setString("urusan", "%" + urusan + "%");
            }
             else  if ((kod != null)&& (urusan == null)) {
                q.setString("kod","%" + kod + "%");
             }
           if ((kod == null)&& (urusan != null)) {
                q.setString("urusan", "%" + urusan + "%");
            }

           q.setString("kodCaw", kodCaw);
      LOG.info("Kod cAw"+ kodCaw);
      LOG.info("Query"+ query);
             return q.list();

     }

       public List<PermohonanPihak> listPermohonanPihak(String idPermohonan){
      
//          String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw ";
         String query = "SELECT h FROM etanah.model.PermohonanPihak h where h.permohonan = :idPermohonan";
               
              Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        
        
        
             return q.list();

     }

     
  
}

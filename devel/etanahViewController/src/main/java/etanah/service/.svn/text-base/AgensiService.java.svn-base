/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import etanah.dao.*;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodUrusan;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import java.util.Map;
import java.util.Date;
import etanah.model.KodAgensi;
import etanah.model.KodUrusanAgensi;
import etanah.model.PermohonanRujukanLuar;

public class AgensiService {
    
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(AduanService.class);

    public List<Permohonan> getSenaraiPermohonanByKodUrusan() {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.kodUrusan = 'ADUAN' order by h.idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        //q.setString("tarikhMula", param.get("tarikhMula")[0]);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }
    
    public List<KodAgensi> getSenaraiAgensiWarta()
    {
        //KodUrusanAgensi  
        String query = "SELECT h FROM etanah.model.KodAgensi h where h.kod in('1270','2403') order by h.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }
    
    public KodUrusanAgensi getSenaraikodUrusanAgensi()
    {
        //KodUrusanAgensi
        String query = "SELECT g FROM etanah.model.KodUrusanAgensi where h.kodUrusan = 'PWGSA'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        LOG.info("query ::" + query);
        return (KodUrusanAgensi) q.uniqueResult();
    }
    
    public KodAgensi findNameAgensi(String kodagensi) {
        String query = "SELECT h FROM etanah.model.KodAgensi h where h.kod = :kodagensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodagensi", kodagensi);
        return (KodAgensi) q.uniqueResult();
    }
    
     @Transactional
    public PermohonanRujukanLuar simpanpermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }
     
    public PermohonanRujukanLuar findPermohonanRujByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }
    
    public PermohonanRujukanLuar findPermohonanRujByIdPermohonanKodRuj(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and lt.kodRujukan.kod='FL' ");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }
}

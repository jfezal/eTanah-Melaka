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
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodBandarPekanMukim;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author aminah.abdmutalib
 */
public class AduanService {

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

    public List<Permohonan> getSenaraiPermohonanByIdPermohonan(String idPermohonan, Date fromDate) {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.kodUrusan = 'ADUAN' ";
        if (idPermohonan != null) {
            query += " and h.idPermohonan = :idPermohonan";
        }
        if (fromDate != null) {
            query += " and trim(h.infoAudit.tarikhMasuk) = :fromDate";
        }
        query += " order by h.idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        LOG.info("query ::" + query);
        if (idPermohonan != null) {
            q.setString("idPermohonan", idPermohonan);
        }
        if (fromDate != null) {
            q.setDate("fromDate", fromDate);
        }

        return q.list();
    }

    public List<KodUrusan> getSenaraiUrusan() {
        String query = "SELECT h FROM etanah.model.KodUrusan h where h.kod <> 'ADUAN' and (h.jabatan = '24') and h.kodPerserahan.kod <> 'Y' order by h.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        //q.setString("tarikhMula", param.get("tarikhMula")[0]);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }

    public List<AduanLokasi> findSenaraiLokasiByID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanLokasi b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<AduanOrangKenaSyak> findSenaraiOKSByID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanOrangKenaSyak b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return kodCaraPermohonanDAO.findAll();
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return kodBandarPekanMukimDAO.findAll();
    }

    public List<KodUrusan> getSenaraiUrusanEnfMLK() {
        String query = "SELECT h FROM etanah.model.KodUrusan h where h.kod <> 'ADUAN' and (h.jabatan = '24') "
                + "and h.kodPerserahan.kod <> 'Y' and h.kod IN ('49','127','128','129','130','351','352','422','423','424','425','425A','426','427','428','429') "
                + "order by h.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        //q.setString("tarikhMula", param.get("tarikhMula")[0]);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }
    
      public List<Permohonan> getMaklumatAduanbyIdPermohonanENF(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        LOG.info("query ::" + query);
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FasaPermohonanLogDAO;
import etanah.dao.UrusanAliranDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FasaPermohonanLog;
import etanah.model.KodUrusan;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author md.nurfikri
 */
public class FasaPermohonanService {

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    FasaPermohonanLogDAO fasaPermohonanLogDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    private UrusanAliranDAO urusanAliranDAO;
    
    private static final Logger LOGGER = Logger.getLogger(FasaPermohonanService.class);

    @Transactional
    public FasaPermohonan saveOrUpdate(FasaPermohonan fp) {
        fp = fasaPermohonanDAO.saveOrUpdate(fp);
        return fp;
    }

    @Transactional
    public void saveFasaLog(List<FasaPermohonanLog> fasaLogs) {
        for (FasaPermohonanLog fasaLog : fasaLogs) {
            fasaPermohonanLogDAO.save(fasaLog);
        }
    }
    
    @Transactional
    public void deleteFasaLog(List<FasaPermohonan> listFasa) {
         for (FasaPermohonan fasa : listFasa) {
            fasaPermohonanDAO.delete(fasa);
        }
    }
    
    public void deleteFasaAll (List<FasaPermohonan> listFasa) {
        for (FasaPermohonan fasa : listFasa) {
            if (!fasa.getSenaraiLog().isEmpty()) {
                for(FasaPermohonanLog log : fasa.getSenaraiLog()) {
                    fasaPermohonanLogDAO.delete(log);
                }
            }
            fasaPermohonanDAO.delete(fasa);
        }
    }
    

    public boolean checkKeputusan(String idPermohonan, String idPengguna, String keputusan, String stageId) {
        String query = "SELECT f FROM etanah.model.FasaPermohonan f WHERE f.permohonan.idPermohonan = :idPermohonan "
                + "and f.idPengguna = :idPengguna and f.keputusan.kod is not null and f.idAliran = :stageId";
        if (StringUtils.isNotBlank(keputusan)) {
            query += " and f.keputusan.kod = :kodKeputusan";
        }
        LOGGER.info(query);
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan)
                .setString("idPengguna", idPengguna)
                .setString("stageId", stageId);
        if (StringUtils.isNotBlank(keputusan)) {
            q.setString("kodKeputusan", keputusan);
        }
//        FasaPermohonan f = (FasaPermohonan)q.uniqueResult();
//        return (f != null);
        List<FasaPermohonan> l = (List<FasaPermohonan>) q.list();
        if (l != null && l.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public FasaPermohonan getKeputusan(String idPermohonan, String idPengguna, String idWorkflow) {
        String query = "SELECT f FROM etanah.model.FasaPermohonan f WHERE f.permohonan.idPermohonan = :idPermohonan "
                + "and f.idPengguna = :idPengguna and f.idAliran = :idWorkflow";
        LOGGER.info(query);
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idPengguna", idPengguna).setString("idWorkflow", idWorkflow);

//        return (FasaPermohonan)q.uniqueResult();
        List<FasaPermohonan> fasaMohonList = q.list();
        FasaPermohonan fp = new FasaPermohonan();
        if (fasaMohonList != null && fasaMohonList.size() > 0) {
            fp = fasaMohonList.get(0);
        }
        return fp;
    }

    public FasaPermohonan getLastStage(String idPermohonan, String stageId) {
        LOGGER.debug("stageId = " + stageId);
        FasaPermohonan fasaPermohonan = null;

        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan order by fp.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);

        int nextFasa = 0;
        List<FasaPermohonan> senaraiFasa = q.list();
        for (FasaPermohonan fp : senaraiFasa) {
            if (fp.getIdAliran().equals(stageId)) {
                if (senaraiFasa.size() > (nextFasa + 1)) {
                    fasaPermohonan = senaraiFasa.get(nextFasa + 1);
                    break;
                }
            }
            nextFasa++;
        }
        LOGGER.debug("is fasaPermohonan null ?" + ((fasaPermohonan != null) ? "not null" : "is null"));

        if (fasaPermohonan != null) {
            return fasaPermohonan;
        } else {
            return (FasaPermohonan) q.list().get(1);
        }
    }
    
    public FasaPermohonan getCurrentStage(String idPermohonan, String stageId) {        
         
         StringBuilder sb = new StringBuilder("Select fp from etanah.model.FasaPermohonan fp ")
                 .append("where fp.permohonan.idPermohonan = :idPermohonan ")
                 .append("and fp.idAliran = :stageId");
         
        Query q = sessionProvider.get().createQuery(sb.toString())
                .setString("idPermohonan", idPermohonan).setString("stageId", stageId);
        
        if (q.list().size() > 1)
            return (FasaPermohonan)q.list().get(0);
        
        return (FasaPermohonan)q.uniqueResult();
    }
    
    public String getLastStage(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan order by fp.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        FasaPermohonan fp = (FasaPermohonan) q.list().get(0);
        if (fp != null) {
            return fp.getIdAliran();
        } else {
            return null;
        }
    }

    public List<FasaPermohonan> findStatus(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan order by fp.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    public List<FasaPermohonan> findStatusDESC(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan order by fp.infoAudit.tarikhMasuk DESC";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
    //hazwan
    public List<FasaPermohonan> findStatusSelesai(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan "
                + "and fp.tarikhHantar is not null order by fp.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //hamizah
    public List<FasaPermohonan> findStatusSelesai1(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan "
                + "order by fp.infoAudit.tarikhMasuk asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public FasaPermohonan findLastStage(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan order by fp.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (FasaPermohonan)q.list().get(0);
    }
    
    public FasaPermohonan findStage(String idPermohonan, String idPengguna) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan and fp.idPengguna = :idPengguna";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idPengguna", idPengguna);
        return (FasaPermohonan) q.list().get(1);
    }
    
    public List<KodUrusan> findNB() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('NB') and fp.urusanBelakangKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<KodUrusan> findN() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('N') and fp.urusanBelakangKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<KodUrusan> findMH() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('MH') and fp.urusanBelakangKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<KodUrusan> findSC() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('SC') and fp.urusanKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();

    }

    public List<KodUrusan> findB() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('B') and fp.urusanKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();

    }
    
    public List<KodUrusan> findCP() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('CP') and fp.urusanKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();

    }
    
    public List<KodUrusan> findSW() {
        String query = "Select fp from etanah.model.KodUrusan fp where fp.kodPerserahan.kod in ('SW') and fp.urusanKaunter ='Y' and fp.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();

    }

    public List<FasaPermohonanLog> senaraiFasaLog(FasaPermohonan fp) {
        StringBuilder sb = new StringBuilder("Select f from etanah.model.FasaPermohonanLog f where ")
                .append("f.fasa.idFasa = :idFasa and ")
                .append("f.fasa.keputusan is not null");
        Query q = sessionProvider.get().createQuery(sb.toString()).setParameter("idFasa", fp.getIdFasa());

        return q.list();
    }
    
    public FasaPermohonan getKodKedesakan(String idMohon, String kodKeputusan){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.FasaPermohonan m WHERE m.permohonan.idPermohonan = :idMohon AND m.keputusan.kod = :kodKeputusan");
        q.setParameter("idMohon",idMohon);
        q.setParameter("kodKeputusan", kodKeputusan);
        return (FasaPermohonan) q.uniqueResult();
    }
    
    public String findKeraniKemasukan(String idMohon){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT fp.idPengguna FROM etanah.model.FasaPermohonan fp WHERE fp.idAliran = 'kemasukan' AND fp.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon",idMohon);
        return (String) q.uniqueResult();
    }
    
    public String getSasaranHari(String kodUrusan, String stageId) {
        Session session = sessionProvider.get();
        StringBuilder sb = new StringBuilder("SELECT s.sasaranHari FROM etanah.model.UrusanAliran s ")
                .append("WHERE s.kodUrusan.kod = :kodUrusan ")
                .append("AND s.idAliran = :stageId");
        LOGGER.debug(sb.toString());
        Query q = session.createQuery(sb.toString())
                .setParameter("kodUrusan", kodUrusan)
                .setParameter("stageId", stageId);
        return q.uniqueResult() != null ? ((BigDecimal)q.uniqueResult()).toString():"0";
        
    }

    public List<FasaPermohonan> findByIdMohonAndStageId(String idPermohonan, String idAliran) {

        String query = "Select fp from etanah.model.FasaPermohonan fp where "
        + "fp.permohonan.idPermohonan = :idPermohonan "
        + "and fp.idAliran = :idAliran "
        + "order by fp.infoAudit.tarikhMasuk desc";
        Query q = sessionProvider.get().createQuery(query).setParameter("idPermohonan", idPermohonan).setParameter("idAliran", idAliran);
        return q.list();    }
    
    public List<FasaPermohonan> findByIdMohonAndStageIdDESC(String idPermohonan, String idAliran) {

        String query = "Select fp from etanah.model.FasaPermohonan fp where "
        + "fp.permohonan.idPermohonan = :idPermohonan "
        + "and fp.idAliran = :idAliran "
        + "order by fp.idFasa";
        Query q = sessionProvider.get().createQuery(query).setParameter("idPermohonan", idPermohonan).setParameter("idAliran", idAliran);
        return q.list();    }
    
}

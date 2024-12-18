/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.IntegrasiMohonStatusDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.emmkn.IntegrasiMohonStatus;
import etanah.model.IntegrasiPermohonan;
import etanah.model.IntegrasiPermohonanButir;
import etanah.model.IntegrasiPermohonanDokumen;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Izam
 */
public class IntegrasiService {

    @Inject
    IntegrasiMohonStatusDAO mohonStatusDAO;
    @Inject
    protected Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(IntegrasiService.class);

    @Transactional
    public boolean saveLog(Pengguna pengguna, Permohonan mohon, EMMKNService emmkn) {
        IntegrasiMohonStatus ims = new IntegrasiMohonStatus();

        InfoAudit a = new InfoAudit();
        a.setDimasukOleh(pengguna);
        a.setTarikhMasuk(new Date());

        ims.setPermohonan(mohon);
        ims.setLogEMMKN(emmkn.getLoggingId());
        ims.setStatusEMMKN(emmkn.getStatusCode());
        ims.setMesejEMMKN(emmkn.getStatusMessage());
//        ims.setLogEMMKN("emmkn-log");
//        ims.setStatusEMMKN("SUCCESS");
//        ims.setMesejEMMKN("emmkn-message");
        ims.setInfoAudit(a);

        ims = (IntegrasiMohonStatus) mohonStatusDAO.saveOrUpdate(ims);
        return (ims != null);
    }

    public List<IntegrasiMohonStatus> allLogs() {
        String query = "from etanah.model.emmkn.IntegrasiMohonStatus ims order by ims.infoAudit.tarikhMasuk desc, "
                + "ims.infoAudit.tarikhKemaskini desc, ims.permohonan.idPermohonan desc";
        Query q = sessionProvider.get().createQuery(query);
        return (List<IntegrasiMohonStatus>) q.list();
        //return mohonStatusDAO.findAll();
    }

    public List<IntegrasiMohonStatus> findByIdMohon(String idMohon) {
        String query = "from etanah.model.emmkn.IntegrasiMohonStatus ims where ims.permohonan.idPermohonan = :idPermohonan "
                + "order by ims.infoAudit.tarikhMasuk desc, ims.infoAudit.tarikhKemaskini desc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idMohon);
        return (List<IntegrasiMohonStatus>) q.list();
    }

    public IntegrasiPermohonan findIDIntegByIdMohon(String idMohon, String idAliran) {
        String query = "SELECT p FROM etanah.model.IntegrasiPermohonan p WHERE p.permohonan.idPermohonan = :IdMH AND p.idAliran = :idAliran AND p.idInteg = (SELECT MAX(p1.idInteg) from etanah.model.IntegrasiPermohonan p1 WHERE p1.permohonan.idPermohonan = :IdMH AND p1.idAliran = :idAliran)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("IdMH", idMohon);
        q.setParameter("idAliran", idAliran);
        return (IntegrasiPermohonan) q.uniqueResult();
    }

    public IntegrasiPermohonanButir findIDButirByIDInteg(Long idInteg) {
        String query = "SELECT p FROM etanah.model.IntegrasiPermohonanButir p WHERE p.integrasiPermohonan.idInteg = :idInteg";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idInteg", idInteg);
        return (IntegrasiPermohonanButir) q.uniqueResult();
    }

    public IntegrasiPermohonanDokumen findFileByIDButir(Long idButir) {
        String query = "SELECT p FROM etanah.model.IntegrasiPermohonanDokumen p WHERE p.integrasiPermohonanButir.idButir = :idButir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idButir", idButir);
        return (IntegrasiPermohonanDokumen) q.uniqueResult();
    }
}

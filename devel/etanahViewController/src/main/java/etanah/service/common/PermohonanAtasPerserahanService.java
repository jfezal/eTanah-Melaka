/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanAtasPerserahanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class PermohonanAtasPerserahanService {

    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanHubunganDAO permohonanHubunganDAO;

    @Transactional
    public boolean save(List<PermohonanAtasPerserahan> list) {
        for (PermohonanAtasPerserahan permohonanAtasPerserahan : list) {
            permohonanAtasPerserahan = permohonanAtasPerserahanDAO.saveOrUpdate(permohonanAtasPerserahan);
            if (permohonanAtasPerserahan == null) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public boolean save(PermohonanAtasPerserahan permohonanAtasPerserahan) {
        permohonanAtasPerserahan = permohonanAtasPerserahanDAO.saveOrUpdate(permohonanAtasPerserahan);
        if (permohonanAtasPerserahan == null) {
            return false;
        }
        return true;
    }

    @Transactional
    public void delete(Long id) {
        PermohonanAtasPerserahan permohonanAtasPerserahan = permohonanAtasPerserahanDAO.findById(id);
        if (permohonanAtasPerserahan != null) {
            permohonanAtasPerserahanDAO.delete(permohonanAtasPerserahan);
        }
    }

    public void deleteMultiple(String[] uids) {
        for (String uid : uids) {
            if (StringUtils.isBlank(uid)) {
                continue;
            }
            delete(Long.parseLong(uid));
        }
    }

    @Transactional
    public void deleteHubungan(String[] uids) {
        for (String id : uids) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            PermohonanHubungan ph = permohonanHubunganDAO.findById(Long.parseLong(id));
            if (ph == null) {
                continue;
            }
            permohonanHubunganDAO.delete(ph);
        }
    }

    public PermohonanAtasPerserahan findMohonAtasUrusanByIDPermohonan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findMohonAtasUrusanByIDPermohonanAndIdUrusan(String idPermohonan, Long idUrusan) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan "
                        + "AND p.urusan.idUrusan = :idUrusan");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idUrusan", idUrusan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findMohonAtasUrusanByIDPermohonanAndIDHakmilik(String idPermohonan, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.urusan.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idHakmilik", idHakmilik);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public List<PermohonanAtasPerserahan> findMohonAtasUrusanByIDPermohonanAndIDHakmilikList(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
//        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public PermohonanAtasPerserahan findMohonAtasUrusanByIDPermohonanAndIDMohonHakmilik(String idPermohonan, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idHakmilik", idHakmilik);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public List<PermohonanAtasPerserahan> findSenaraiMohonAtasUrusanByIDPermohonan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session
                .createQuery("SELECT p FROM etanah.model.PermohonanAtasPerserahan p WHERE p.permohonan.idPermohonan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanAnsuranDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanAnsuran;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class AnsuranService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PermohonanAnsuranDAO permohonanAnsuranDAO;

    public PermohonanAnsuran findByIdPermohonan(Permohonan mohon) {
        String queryStr = "SELECT a FROM PermohonanAnsuran a WHERE a.idPermohonan = :idPermohonan";
        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("idPermohonan", mohon.getIdPermohonan());
        return (PermohonanAnsuran) query.uniqueResult();
    }

    @Transactional
    public void saveAnsuran(PermohonanAnsuran ansuran) {
        permohonanAnsuranDAO.saveOrUpdate(ansuran);
    }

}

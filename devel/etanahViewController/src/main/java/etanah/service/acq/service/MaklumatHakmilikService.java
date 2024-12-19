/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.service;

import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class MaklumatHakmilikService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Long findPihakKepentinganBerdaftar(String idHakmilik) {
        String query = "select count(p) from etanah.model.HakmilikPihakBerkepentingan p"
                + " where p.aktif = 'Y' and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Long) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findListPihakKepentinganBerdaftar(String idHakmilik) {
        String query = "select p from etanah.model.HakmilikPihakBerkepentingan p"
                + " where p.aktif = 'Y' and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonanNotTDK(String idPermohonan) {
        String query = "select p from etanah.model.HakmilikPermohonan p"
                + " where p.hakmilik is not null and p.permohonan.idPermohonan = :idPermohonan and p.hakmilikAmbil = 'A'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

}

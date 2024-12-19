/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import etanah.model.ispek.IspeksResitPerbendaharaan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class ResitPerbendaharaanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<IspeksResitPerbendaharaan> findResitParam(String noPenyata, String tarikhMula, String tarikhAkhir, String kodCaw) throws ParseException {
        String query = "Select r FROM etanah.model.ispek.IspeksResitPerbendaharaan r,etanah.model.ispek.PenyataPemungut p where p.kodCaw.kod = :kodCaw"
                + " and r.collectorStatementNum = p.noPenyata";
        if (!StringUtils.isEmpty(noPenyata)) {
            query = query + " and p.noPenyata =:noPenyata ";
        }
        if (!StringUtils.isEmpty(tarikhMula)) {
            query = query + " and p.tarikhJana Between :tarikhMula AND :tarikhAkhir ";
        }

        Query q = sessionProvider.get().createQuery(query);
        if (!StringUtils.isEmpty(noPenyata)) {
            q.setString("noPenyata", noPenyata);
        }
        if (!StringUtils.isEmpty(tarikhMula)) {
            q.setDate("tarikhMula", sdf.parse(tarikhMula));
            q.setDate("tarikhAkhir", sdf.parse(tarikhAkhir));
        }

        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    IspeksResitPerbendaharaan findResitByPPPenyata(String noRef) {
        String query = "Select r FROM etanah.model.ispek.IspeksResitPerbendaharaan r where r.collectorStatementNum = :noPenyata";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyata", noRef);
        return (IspeksResitPerbendaharaan) q.uniqueResult();
    }
}

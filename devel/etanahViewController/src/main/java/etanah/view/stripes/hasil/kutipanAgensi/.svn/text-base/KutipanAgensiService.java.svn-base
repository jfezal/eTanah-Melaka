/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import com.google.inject.Inject;
import etanah.model.AgensiKutipanDokumen;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.PortalPengguna;
import java.io.File;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class KutipanAgensiService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<KodAgensiKutipanCawangan> getSenaraiKodAgensiCawangan(String kodAgensiKutipan) {
        StringBuilder query = new StringBuilder("Select m from etanah.model.KodAgensiKutipanCawangan m")
                .append(" Where m.agensiKutipan.kod = :kodAgensi");
        Session session = sessionProvider.get();
        Query q = session.createQuery(query.toString());
        q.setParameter("kodAgensi", kodAgensiKutipan);

        return q.list();
    }

    public PortalPengguna getPenggunaPortal(String idPengguna) {
        StringBuilder query = new StringBuilder("Select p from etanah.model.PortalPengguna p")
                .append(" Where p.idPguna = :idPengguna  and p.kodAgensiKutipan.kod is not null");
        Session session = sessionProvider.get();
        Query q = session.createQuery(query.toString());
        q.setParameter("idPengguna", idPengguna);
        return (PortalPengguna) q.uniqueResult();

    }

    public List<AgensiKutipanDokumen> getSenaraiDokumen(String kodAgensi) {
        StringBuilder query = new StringBuilder("Select p from etanah.model.AgensiKutipanDokumen p")
                .append(" Where p.kodAgensiKutipan.kod = :kodAgensi order by p.infoAudit.tarikhMasuk asc");
        Session session = sessionProvider.get();
        Query q = session.createQuery(query.toString());
        q.setParameter("kodAgensi", kodAgensi);

        return q.list();

    }

    File getNamaFail(String namaFizikal) {
        File file = new File(namaFizikal);
        return file;
    }
}

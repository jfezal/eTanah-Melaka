/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import etanah.model.view.CekTakLakuView;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class IspeksCekTakLakuService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<CekTakLakuView> findByKodCawangan(String kod) {
        List<CekTakLakuView> l = new ArrayList<CekTakLakuView>();
        String query = "SELECT v FROM etanah.model.view.CekTakLakuView v, etanah.model.ispek.PenyataPemungut p where "
                + "v.noPenyata = p.noPenyata and p.kodCaw.kod = :kod and v.kodStatus !='B'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        List<CekTakLakuView> r = q.list();
        if (!r.isEmpty()) {
            for (CekTakLakuView s : r) {
                l.add(s);
            }
        }
        return l;
    }

}

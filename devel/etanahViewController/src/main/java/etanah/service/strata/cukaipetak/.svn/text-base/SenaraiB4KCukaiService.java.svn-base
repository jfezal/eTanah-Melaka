/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.cukaipetak;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import etanah.view.strata.cukaipetak.MaklumatSkim;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author john
 */
public class SenaraiB4KCukaiService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<MaklumatSkim> findSkimJana(KodCawangan kodCaw) {
        List <MaklumatSkim> listSkim = new ArrayList<MaklumatSkim>();
        String query = "select p from etanah.model.Hakmilik p "
                + "right join fetch p.endorsanCukai b4k "
//                + "on p.idHakmilik = b4k.hakmilik.idHakmilik "
//                + "and b4k.noVersi < p.noVersiDhde "
                + "where p.idHakmilikInduk is not null ";
        query += " order by p.bandarPekanMukim ";
        Query q = sessionProvider.get().createQuery(query);
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            Hakmilik hakmilik = (Hakmilik) it.next();
            MaklumatSkim ms = new MaklumatSkim();
            ms.setIdHakmilikInduk(hakmilik.getIdHakmilik());
listSkim.add(ms);
        }
        return listSkim;
    }

}

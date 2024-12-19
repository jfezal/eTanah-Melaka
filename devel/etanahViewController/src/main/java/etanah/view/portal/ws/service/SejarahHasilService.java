/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws.service;

import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.model.Akaun;
import etanah.model.view.TransaksiSejarah;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class SejarahHasilService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    AkaunDAO akaunDAO;

    public Akaun findByList(String noAkaun, String kodCaw) {
        Akaun akaun = null;
        List<TransaksiSejarah> list = new ArrayList<TransaksiSejarah>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.view.TransaksiSejarah u where u.noAkaun = :noAkaun and u.kodCaw = :kodCaw");
        q.setString("noAkaun", noAkaun);
        q.setString("kodCaw", kodCaw);
        
        list = q.list();
        if (!list.isEmpty()) {
            akaun = new Akaun();
            TransaksiSejarah ts = list.get(0);
            akaun = akaunDAO.findById(ts.getNoAkaun());

        }
        return akaun;
    }

    public TransaksiSejarah findTransaksiSejarah(String kodCaw) {
     List<TransaksiSejarah> list = new ArrayList<TransaksiSejarah>();
      TransaksiSejarah ts = new TransaksiSejarah();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.view.TransaksiSejarah u where u.kodCaw = :kodCaw");
        q.setString("kodCaw", kodCaw);
        q.setFirstResult(0);
            q.setMaxResults(1);
        list = q.list();
        if (!list.isEmpty()) {
             ts = list.get(0);

        }
        return ts;    }

}

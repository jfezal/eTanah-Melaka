/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ws;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.Permohonan;
import etanah.service.RegService;
import etanah.view.portal.service.ws.StatusTukarGanti;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author wazer
 */
public class StatusTukarGantiByPartialService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public StatusTukarGanti findStatusTukarGantiByPartial(String kodDaerah, String noLot, String kodBpm, String jenisHakmilik) {

        
        StatusTukarGanti form = new StatusTukarGanti();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.daerah.kod = :kodDaerah "
                + "and u.noLot = : noLot "
                + "and u.bandarPekanMukim.kod = : kodBpm "
                + "and u.kodHakmilik.kod = : jenisHakmilik");
        q.setString("kodDaerah", kodDaerah);
        q.setString("noLot", noLot);
        q.setString("kodBpm", kodBpm);
        q.setString("jenisHakmilik", jenisHakmilik);
        q.uniqueResult();
        Hakmilik hakmilik = (Hakmilik) q.uniqueResult();

        form.setIdHakmilik(hakmilik.getIdHakmilik());
        form.setDaerah(hakmilik.getDaerah().getNama());
        form.setVersi(String.valueOf(hakmilik.getVersion()));
        form.setTarikh(String.valueOf(hakmilik.getInfoAudit().getTarikhKemaskini()));
        return form;
    }
}

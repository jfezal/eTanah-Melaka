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
public class StatusTukarGantiService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public StatusTukarGanti findStatusTukarGanti(String idHakmilik) {

        StatusTukarGanti form = new StatusTukarGanti();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        q.uniqueResult();
        Hakmilik hakmilik = (Hakmilik) q.uniqueResult();

        form.setIdHakmilik(hakmilik.getIdHakmilik());
        form.setDaerah(hakmilik.getDaerah().getNama());
        form.setVersi(String.valueOf(hakmilik.getNoVersiDhde()));
        form.setTarikh(String.valueOf(hakmilik.getInfoAudit().getTarikhKemaskini()));
        form.setNoLot(hakmilik.getNoLot());
//        form.setKeputusanOleh(mohon.getKeputusanOleh().getNama());
        return form;
    }
}

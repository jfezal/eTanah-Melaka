/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ws;

import com.google.inject.Inject;
import etanah.dao.PortalPengumumanDAO;
import etanah.model.PortalPengumuman;
import etanah.view.portal.service.ws.PengumumanForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class PengumumanService {

    @Inject
    PortalPengumumanDAO portalPengumumanDAO;

    public List<PengumumanForm> senaraiPengumumanAktif() {
        List<PengumumanForm> l = new ArrayList<PengumumanForm>();
        List<PortalPengumuman> listPengumuman = portalPengumumanDAO.findAll();
        for(PortalPengumuman p :listPengumuman){
            PengumumanForm f = new PengumumanForm();
            f.setPengumuman(p.getKandungan());
            f.setTajuk(p.getTajuk());
            l.add(f);
        }
        return l;
    }

}

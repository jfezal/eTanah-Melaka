/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.theta.client.service;

import etanah.view.portal.service.ws.StatusTukarGanti;
import java.net.URL;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;
import net.theta.client.form.StatusTukarGantiForm;

/**
 *
 * @author mohd.faidzal
 */
public class ClientSemakanTukarGantiService {

    public StatusTukarGantiForm findByidHakmilik(String idHakmilik,URL url) {
        StatusTukarGantiForm form = new StatusTukarGantiForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        StatusTukarGanti s = port.findStatusTukarGanti(idHakmilik);
        form.setDaerah(s.getDaerah().getValue());
        form.setDiDaftarOleh(s.getDiDaftarOleh().getValue());
        form.setIdHakmilik(s.getIdHakmilik().getValue());
        form.setNoLot(s.getNoLot().getValue());
        form.setTarikh(s.getTarikh().getValue());
        form.setVersi(s.getVersi().getValue());
        return form;
    }

    public StatusTukarGantiForm findByParameter(String daerah, String bpm, String jenisHakmilik, String noHakmilik,URL url) {
        StatusTukarGantiForm form = new StatusTukarGantiForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
//        String kodDaerah, String noLot, String kodBpm, String jenisHakmilik
        StatusTukarGanti s = port.statusTukarGantiByPartial(daerah, noHakmilik, bpm, jenisHakmilik);
        form.setDaerah(s.getDaerah().getValue());
        form.setDiDaftarOleh(s.getDiDaftarOleh().getValue());
        form.setIdHakmilik(s.getIdHakmilik().getValue());
        form.setNoLot(s.getNoLot().getValue());
        form.setTarikh(s.getTarikh().getValue());
        form.setVersi(s.getVersi().getValue());
        return form;    }

   
}

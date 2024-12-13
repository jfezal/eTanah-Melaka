/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.theta.client.service;

import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.portal.service.ws.ArrayOfStatusSemakanAkaun;
import etanah.view.portal.service.ws.StatusSemakanAkaun;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;
import net.theta.client.form.DokumenInfoClientForm;
import net.theta.client.form.StatusSemakanAkaunForm;

/**
 *
 * @author mohd.faidzal
 */
public class ClientSemakanAkaunService {
    

    public StatusSemakanAkaunForm findStatusSemakanAkaun(String noAkaun,URL url) {
        StatusSemakanAkaunForm form = new StatusSemakanAkaunForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        StatusSemakanAkaun s = port.findStatusSemakanAkaun(noAkaun);
            form.setNoAkaun(s.getNoAkaun().getValue());
            form.setStatus(s.getStatus().getValue());
            form.setBaki(s.getBaki().getValue());
            form.setIdHakmilik(s.getIdHakmilik().getValue());

        return form;
    }

    public List<StatusSemakanAkaunForm> findStatusByNoKp(String noKp,URL url) {
        List<StatusSemakanAkaunForm> list = new ArrayList<StatusSemakanAkaunForm>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfStatusSemakanAkaun a = port.findStatusSemakanAkaunByNoPengenalan(noKp);
        if (a != null) {
            List<StatusSemakanAkaun> b = a.getStatusSemakanAkaun();
            for (int i = 0; i < b.size(); i++) {
                StatusSemakanAkaunForm form = new StatusSemakanAkaunForm();
                StatusSemakanAkaun s = b.get(i);
                form.setNoAkaun(s.getNoAkaun().getValue());
                form.setBaki(s.getBaki().getValue());
                form.setStatus(s.getStatus().getValue());
                form.setIdHakmilik(s.getIdHakmilik().getValue());

                list.add(form);
            }
        }

        return list;
    }

    public DokumenInfoClientForm muatTurunBilCukai(String i,URL url) {
        DokumenInfoClientForm form = new DokumenInfoClientForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        DokumenInfo d = port.muatTurunBilCukai(i);
        if (d != null) {
            form.setBytes(d.getBytes().getValue());
            form.setDocType(d.getDocType().getValue());
            form.setFileName(d.getFileName().getValue());
        }
        return form;
    }
    
    public StatusSemakanAkaunForm findAkaunByIDHakmilik(String noAkaun,URL url) {
        StatusSemakanAkaunForm form = new StatusSemakanAkaunForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        StatusSemakanAkaun s = port.findStatusSemakanAkaun(noAkaun);
            form.setNoAkaun(s.getNoAkaun().getValue());
            form.setStatus(s.getStatus().getValue());
            form.setBaki(s.getBaki().getValue());
            form.setIdHakmilik(s.getIdHakmilik().getValue());

        return form;
    }

}

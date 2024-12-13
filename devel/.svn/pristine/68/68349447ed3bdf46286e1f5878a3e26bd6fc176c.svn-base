/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.theta.client.service;

import etanah.view.portal.service.ws.ArrayOfStatusPermohonan;
import etanah.view.portal.service.ws.StatusPermohonan;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;
import net.theta.client.form.StatusPermohonanForm;

/**
 *
 * @author mohd.faidzal
 */
public class ClientStatusPermohonanService {

    public StatusPermohonanForm findStatusByIdPermohonan(String idPermohonan,URL url) throws MalformedURLException {
        StatusPermohonanForm form = new StatusPermohonanForm();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        StatusPermohonan s = port.findStatusPermohonan(idPermohonan);
        form.setIdPermohonan(s.getIdPermohonan().getValue());
        form.setKeputusanOleh(s.getKeputusanOleh().getValue());
        form.setKodUrusan(s.getKodUrusan().getValue());
        form.setNamaUrusan(s.getNamaUrusan().getValue());
        form.setStatus(s.getStatus().getValue());
        form.setTarikhKeputusan(s.getTarikhKeputusan().getValue());
        return form;
    }

    public List<StatusPermohonanForm> findStatusByNoKp(String noKp,URL url) {
        List< StatusPermohonanForm> list = new ArrayList<StatusPermohonanForm>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfStatusPermohonan a = port.findStatusPermohonanByNoPengenalan(noKp);
        List<StatusPermohonan> b = a.getStatusPermohonan();
        for (int i = 0; i < b.size(); i++) {
            StatusPermohonanForm form = new StatusPermohonanForm();
            StatusPermohonan s = b.get(i);
            form.setIdPermohonan(s.getIdPermohonan().getValue());
            form.setKeputusanOleh(s.getKeputusanOleh().getValue());
            form.setKodUrusan(s.getKodUrusan().getValue());
            form.setNamaUrusan(s.getNamaUrusan().getValue());
            form.setStatus(s.getStatus().getValue());
            form.setTarikhKeputusan(s.getTarikhKeputusan().getValue());
            list.add(form);
        }
        return list;
    }
}

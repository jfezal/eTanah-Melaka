/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.portal;

import com.google.inject.Inject;
import com.theta.portal.stripes.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;
import net.theta.client.form.StatusPermohonanForm;
import net.theta.client.service.ClientStatusPermohonanService;

/**
 *
 * @author zipzap
 */
public class StatusPermohonanService {

    @Inject
    ClientStatusPermohonanService clientStatusPermohonanService;
    @Inject
    Configuration conf;
    String url1;

    public StatusPermohonanForm findPermohonanStatus(String idPermohonan) throws MalformedURLException {
        url1  = conf.getProperty("url.webservice");
        URL url = new URL(url1);
        StatusPermohonanForm form = clientStatusPermohonanService.findStatusByIdPermohonan(idPermohonan, url);
        return form;
    }

    public List<StatusPermohonanForm> findPermohonanStatusByNoKP(String noKP) throws MalformedURLException {
       url1  = conf.getProperty("url.webservice");
        URL url = new URL(url1);
        List<StatusPermohonanForm> form = clientStatusPermohonanService.findStatusByNoKp(noKP, url);
        return form;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
}

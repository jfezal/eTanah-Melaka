/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.portal;

import com.google.inject.Inject;
import com.theta.portal.stripes.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import net.theta.client.form.StatusTukarGantiForm;
import net.theta.client.service.ClientSemakanTukarGantiService;

/**
 *
 * @author user
 */
public class StatusTukarGantiService {

    @Inject
    Configuration conf;
    String url1;
    @Inject
    ClientSemakanTukarGantiService clientSemakanTukarGantiService;

    public StatusTukarGantiForm findTukarGantiStatus(String idHakmilik) throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        StatusTukarGantiForm form = clientSemakanTukarGantiService.findByidHakmilik(idHakmilik, url);
        return form;
    }

    public StatusTukarGantiForm findTukarGantiStatusParam(String daerah, String bpm, String jenisHakmilik, String noHakmilik) throws MalformedURLException {
      url1  = conf.getProperty("url.webservice");  URL url = new URL(url1);
        StatusTukarGantiForm form = clientSemakanTukarGantiService.findByParameter(daerah, bpm, jenisHakmilik, noHakmilik, url);
        return form;
    }
    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
}

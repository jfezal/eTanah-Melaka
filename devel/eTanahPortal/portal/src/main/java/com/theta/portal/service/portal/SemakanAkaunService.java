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
import net.theta.client.form.StatusSemakanAkaunForm;
import net.theta.client.service.ClientSemakanAkaunService;

/**
 *
 * @author zipzap
 */
public class SemakanAkaunService {

    @Inject
    ClientSemakanAkaunService clientSemakanAkaunService;
    @Inject
    Configuration conf;
    String url1;

    public StatusSemakanAkaunForm findStatusSemakanAkaun(String noAkaun) throws MalformedURLException {
        url1  = conf.getProperty("url.webservice");
        URL url = new URL(url1);
        StatusSemakanAkaunForm form = clientSemakanAkaunService.findStatusSemakanAkaun(noAkaun, url);
        return form;
    }

    public List<StatusSemakanAkaunForm> findStatusByNoKp(String noKP) throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<StatusSemakanAkaunForm> form = clientSemakanAkaunService.findStatusByNoKp(noKP, url);
        return form;
    }

    public String getUrl1() {
        url1 = conf.getProperty("url.webservice");
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
    
    
}

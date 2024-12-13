/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.portal;

import com.google.inject.Inject;
import com.theta.portal.stripes.Configuration;
import com.theta.portal.stripes.portal.DokumenForm;
import com.theta.portal.stripes.portal.KodUrusan;
import etanah.view.portal.service.ws.ArrayOfSenaraiDokumen;
import etanah.view.portal.service.ws.ArrayOfSenaraiUrusan;
import etanah.view.portal.service.ws.SenaraiDokumen;
import etanah.view.portal.service.ws.SenaraiUrusan;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalian;
import localhost._8080.perkhidmatanonlinews.PerkhidmatanAtasTalianPortType;

/**
 *
 * @author mohd.faidzal
 */
public class SemakanDokumenService {
    @Inject
    Configuration conf;
    String url1;
    public List<KodUrusan> findKodUrusanByKeyWord(String key) throws MalformedURLException {
        url1  = conf.getProperty("url.webservice");URL url = new URL(url1);
        List<KodUrusan> list = new ArrayList<KodUrusan>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfSenaraiUrusan s = port.findByKataKunci(key);
        for (SenaraiUrusan ss : s.getSenaraiUrusan()) {
            KodUrusan e = new KodUrusan();
            e.setKod(ss.getKodUrusan().getValue());
            e.setNamaUrusan(ss.getNamUrusan().getValue());
            list.add(e);
        }
        return list;
    }
    
    public List<DokumenForm> findListDokumen(String urusan) throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<DokumenForm> list = new ArrayList<DokumenForm>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfSenaraiDokumen s = port.findbyKodUrusan(urusan);
        for (SenaraiDokumen ss : s.getSenaraiDokumen()) {
            DokumenForm e = new DokumenForm();
            e.setNamaDokumen(ss.getNamaDokumen().getValue());
            e.setMandatori(ss.getMandatori().getValue());
            e.setMuatTurun(ss.getMuatTurun().getValue());
            e.setSah(ss.getSah().getValue());
            list.add(e);
        }
        return list;
    }
    
    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

}

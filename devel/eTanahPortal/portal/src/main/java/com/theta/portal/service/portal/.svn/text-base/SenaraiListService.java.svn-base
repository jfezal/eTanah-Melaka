/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.service.portal;

import com.google.inject.Inject;
import com.theta.portal.stripes.Configuration;
import com.theta.portal.stripes.util.ListValue;
import etanah.view.portal.service.ws.ArrayOfSenaraiBandarPekanMukim;
import etanah.view.portal.service.ws.ArrayOfSenaraiKodDaerah;
import etanah.view.portal.service.ws.ArrayOfSenaraiKodHakmilik;
import etanah.view.portal.service.ws.SenaraiBandarPekanMukim;
import etanah.view.portal.service.ws.SenaraiKodDaerah;
import etanah.view.portal.service.ws.SenaraiKodHakmilik;
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
public class SenaraiListService {
    @Inject
    Configuration conf;
    String url1;
    public List<ListValue> senaraiDaerah() throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<ListValue> list = new ArrayList<ListValue>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfSenaraiKodDaerah listKodDaerah = port.listKodDaerah();
        for (int i = 0; i < listKodDaerah.getSenaraiKodDaerah().size(); i++) {
            SenaraiKodDaerah s = listKodDaerah.getSenaraiKodDaerah().get(i);
            ListValue e = new ListValue();
            e.setKod(s.getKodDaerah().getValue());
            e.setValue(s.getNamaDaerah().getValue());
            list.add(e);
        }
        return list;
    }

    public List<ListValue> senaraiBandarPekanMukim(String daerah) throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<ListValue> list = new ArrayList<ListValue>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfSenaraiBandarPekanMukim listBandarPekanMukim = port.listBandarPekanMukim(daerah);
        for (int i = 0; i < listBandarPekanMukim.getSenaraiBandarPekanMukim().size(); i++) {
            SenaraiBandarPekanMukim s = listBandarPekanMukim.getSenaraiBandarPekanMukim().get(i);
            ListValue e = new ListValue();
            e.setKod(s.getBandarPekanMukim().getValue());
            e.setValue(s.getNama().getValue());
            list.add(e);
        }
        return list;
    }

    public List<ListValue> senaraiJenisHakmilik() throws MalformedURLException {
       url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<ListValue> list = new ArrayList<ListValue>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
         ArrayOfSenaraiKodHakmilik listKod = port.listKod();
        for (int i = 0; i < listKod.getSenaraiKodHakmilik().size(); i++) {
            SenaraiKodHakmilik s = listKod.getSenaraiKodHakmilik().get(i);
            ListValue e = new ListValue();
            e.setKod(s.getKodHakmilik().getValue());
            e.setValue(s.getNama().getValue());
            list.add(e);
        }
        return list;
    }

    public List<ListValue> senaraiSeksyen(String bpm) throws MalformedURLException {
   url1  = conf.getProperty("url.webservice"); URL url = new URL(url1);
        List<ListValue> list = new ArrayList<ListValue>();
        PerkhidmatanAtasTalian service = new PerkhidmatanAtasTalian(url);
        PerkhidmatanAtasTalianPortType port = service.getPerkhidmatanAtasTalianHttpPort();
        ArrayOfSenaraiKodDaerah listKodDaerah = port.listKodDaerah();
        for (int i = 0; i < listKodDaerah.getSenaraiKodDaerah().size(); i++) {
            SenaraiKodDaerah s = listKodDaerah.getSenaraiKodDaerah().get(i);
            ListValue e = new ListValue();
            e.setKod(s.getKodDaerah().getValue());
            e.setValue(s.getNamaDaerah().getValue());
            list.add(e);
        }
        return list;
    }

}

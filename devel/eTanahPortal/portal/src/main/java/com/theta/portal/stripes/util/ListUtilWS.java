/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.theta.portal.service.portal.SenaraiListService;
import com.theta.portal.stripes.BaseActionBean;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
@Singleton
public class ListUtilWS extends BaseActionBean {

    @Inject
    SenaraiListService senaraiListService;

    public List<ListValue> getListDaerah() throws MalformedURLException {
        return senaraiListService.senaraiDaerah();

    }

    public List<ListValue> getListBandarPekanMukim() throws MalformedURLException {
        return senaraiListService.senaraiBandarPekanMukim("01");

    }
    public List<ListValue> getListJenisHakmilik() throws MalformedURLException {
        return senaraiListService.senaraiJenisHakmilik();

    }
    public List<ListValue> getListSeksyen(String bpm) throws MalformedURLException {
        return senaraiListService.senaraiSeksyen(bpm);

    }
}

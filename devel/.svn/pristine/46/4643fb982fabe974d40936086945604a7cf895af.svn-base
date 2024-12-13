/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.portal;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.service.portal.StatusTukarGantiService;
import com.theta.portal.stripes.BaseActionBean;
import com.theta.portal.stripes.Configuration;
import etanah.view.portal.service.ws.SenaraiKodDaerah;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.theta.client.form.StatusTukarGantiForm;

@UrlBinding("/tukar_ganti")
public class StatusTukarGantiActionBean extends BaseActionBean {
    @Inject
    Configuration conf;
    String url1;
    @Inject
    StatusTukarGantiService statusTukarGantiService;
    String id;
    
    String daerah;
    String bpm;
    String jenisHakmilik;
    String noHakmilik;
    
    
    List <StatusTukarGantiForm> listStatusTukarGanti;
    
    List<SenaraiKodDaerah> listDaerah;
      
    @DefaultHandler
    public Resolution mainPage() {
        
        //listDaerah = 
        return new JSP("/portal/semakan_status_tukar_ganti.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }

    public Resolution statusPermohonan() {
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
    
    public Resolution cari() throws MalformedURLException {
        
        StatusTukarGantiForm f = statusTukarGantiService.findTukarGantiStatus(id);
        
        listStatusTukarGanti = new ArrayList<StatusTukarGantiForm>();
        listStatusTukarGanti.add(f);
        
//        StatusTukarGantiForm f = statusTukarGantiActionBean.findTukarGantiStatus(id);
//        
//        daerah = f.getDaerah();
//        diDaftarOleh = f.getDiDaftarOleh();
//        idHakmilik = f.getIdHakmilik();
//        noLot = f.getNoLot();
//        tarikh = f.getTarikh();
//        versi = f.getVersi();
        
       return new JSP("/portal/semakan_status_tukar_ganti.jsp");
    }
    
        public Resolution cari2() throws MalformedURLException {
        
        StatusTukarGantiForm f = statusTukarGantiService.findTukarGantiStatusParam(daerah,bpm,jenisHakmilik,noHakmilik);
        
        listStatusTukarGanti = new ArrayList<StatusTukarGantiForm>();
        listStatusTukarGanti.add(f);

       return new JSP("/portal/semakan_status_tukar_ganti.jsp");
    }

    public StatusTukarGantiService getStatusTukarGantiService() {
        return statusTukarGantiService;
    }

    public void setStatusTukarGantiService(StatusTukarGantiService statusTukarGantiService) {
        this.statusTukarGantiService = statusTukarGantiService;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }
    
    
    public List<SenaraiKodDaerah> getListDaerah() {
        return listDaerah;
    }
    
    public List<StatusTukarGantiForm> getListStatusTukarGanti() {
        return listStatusTukarGanti;
    }

    public void setListStatusTukarGanti(List<StatusTukarGantiForm> listStatusTukarGanti) {
        this.listStatusTukarGanti = listStatusTukarGanti;
    }

    public void setListDaerah(List<SenaraiKodDaerah> listDaerah) {
        this.listDaerah = listDaerah;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getUrl1() {
        url1 = conf.getProperty("url.webservice");
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }
}
    

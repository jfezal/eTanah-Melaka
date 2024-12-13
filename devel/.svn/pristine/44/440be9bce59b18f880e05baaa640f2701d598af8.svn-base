/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.portal;

import able.stripes.JSP;
import com.google.inject.Inject;
import com.theta.portal.service.portal.StatusPermohonanService;
import com.theta.portal.stripes.BaseActionBean;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.theta.client.form.StatusPermohonanForm;

@UrlBinding("/status_permohonan")
public class StatusPermohonanActionBean extends BaseActionBean {

    @Inject
    StatusPermohonanService statusPermohonanService;
    String id;
    String katgCarian;
    
    String idPermohonan;
    String status;
    String kodUrusan;
    String namaUrusan;
    
    List <StatusPermohonanForm> listStatusPermohonan;
    
    @DefaultHandler
    public Resolution mainPage() {

//        StatusPermohonan f = statusPermohonanService.findPermohonanStatus(idPermohonan);
//        status = f.getStatus().getValue();
//        return new JSP("/portal/SemakanStatusPermohonan.jsp");
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }

    public Resolution cari() throws MalformedURLException {
        
        if(katgCarian.equals("a")){
            StatusPermohonanForm f = statusPermohonanService.findPermohonanStatus(id);
        
            listStatusPermohonan = new ArrayList<StatusPermohonanForm>();
            listStatusPermohonan.add(f);
        }
        else {
            List<StatusPermohonanForm> f = statusPermohonanService.findPermohonanStatusByNoKP(id);
        
            listStatusPermohonan = new ArrayList<StatusPermohonanForm>();
        
            for(int i=0; i < f.size(); i++)
            {
                StatusPermohonanForm a = f.get(i);
                StatusPermohonanForm b = new StatusPermohonanForm();

                b.setIdPermohonan(a.getIdPermohonan());
                b.setKeputusanOleh(a.getKeputusanOleh());
                b.setKodUrusan(a.getKodUrusan());
                b.setNamaUrusan(a.getNamaUrusan());
                b.setStatus(a.getStatus());
                b.setTarikhKeputusan(a.getTarikhKeputusan());

                listStatusPermohonan.add(b);
            }
        }

//        StatusPermohonanForm f = statusPermohonanService.findPermohonanStatus(id);
//        
//        listStatusPermohonan = new ArrayList<StatusPermohonanForm>();
//        listStatusPermohonan.add(f);
        
//        status = f.getStatus();
//        idPermohonan = f.getIdPermohonan();
//        kodUrusan = f.getKodUrusan();
//        return new JSP("/portal/SemakanStatusPermohonan.jsp");
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
    
    public Resolution cari2() throws MalformedURLException {

        List<StatusPermohonanForm> f = statusPermohonanService.findPermohonanStatusByNoKP(id);
        
        listStatusPermohonan = new ArrayList<StatusPermohonanForm>();
        
        for(int i=0; i < f.size(); i++)
        {
            StatusPermohonanForm a = f.get(i);
            StatusPermohonanForm b = new StatusPermohonanForm();
            
            b.setIdPermohonan(a.getIdPermohonan());
            b.setKeputusanOleh(a.getKeputusanOleh());
            b.setKodUrusan(a.getKodUrusan());
            b.setNamaUrusan(a.getNamaUrusan());
            b.setStatus(a.getStatus());
            b.setTarikhKeputusan(a.getTarikhKeputusan());
            
            listStatusPermohonan.add(b);
        }
        
//        status = f.getStatus();
//        idPermohonan = f.getIdPermohonan();
//        kodUrusan = f.getKodUrusan();
//        return new JSP("/portal/SemakanStatusPermohonan.jsp");
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKatgCarian() {
        return katgCarian;
    }

    public void setKatgCarian(String katgCarian) {
        this.katgCarian = katgCarian;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<StatusPermohonanForm> getListStatusPermohonan() {
        return listStatusPermohonan;
    }

    public void setListStatusPermohonan(List<StatusPermohonanForm> listStatusPermohonan) {
        this.listStatusPermohonan = listStatusPermohonan;
    }
    
    
}

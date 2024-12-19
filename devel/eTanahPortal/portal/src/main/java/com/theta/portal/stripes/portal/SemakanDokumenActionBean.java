/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.portal;

import able.stripes.JSP;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.theta.portal.service.portal.SemakanDokumenService;
import com.theta.portal.stripes.BaseActionBean;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.json.JSONObject;

@UrlBinding("/semak_dokumen")
public class SemakanDokumenActionBean extends BaseActionBean {
@Inject
        SemakanDokumenService semakanDokumenService;
    List<KodUrusan> listKodUrusan = new ArrayList<KodUrusan>();
    List<DokumenForm> listDokumen = new ArrayList<DokumenForm>();
    String key;
    String kodUrusan;

    @DefaultHandler
    public Resolution mainPage() {
        return new JSP("/portal/senarai_semak_dokumen.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
  public Resolution cari() throws MalformedURLException {
      listKodUrusan = semakanDokumenService.findKodUrusanByKeyWord(key);
//        listDokumen = semakanDokumenService.findListDokumen("SBMS");

        return new JSP("/portal/senarai_semak_dokumen.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
  
  public Resolution viewDokumen() throws MalformedURLException{
  String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
  listDokumen = semakanDokumenService.findListDokumen(kodUrusan);
  String json = new Gson().toJson(listDokumen );
   JSONObject obj = new JSONObject(listDokumen);
        return new StreamingResolution("application/json", json);
  }
    public Resolution statusPermohonan() {
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }

    public List<KodUrusan> getListKodUrusan() {
        return listKodUrusan;
    }

    public void setListKodUrusan(List<KodUrusan> listKodUrusan) {
        this.listKodUrusan = listKodUrusan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<DokumenForm> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<DokumenForm> listDokumen) {
        this.listDokumen = listDokumen;
    }
    
    
}

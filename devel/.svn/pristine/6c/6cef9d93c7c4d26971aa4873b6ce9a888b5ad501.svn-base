/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.*;
import net.sourceforge.stripes.action.*;

@UrlBinding("/enf/utiliti_cetak_borang")
public class CetakBorangKosongActionBean extends AbleActionBean {

    private Map<String, String> map = new HashMap<String, String>();
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution view() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/cetak_borang.jsp");
    }
    
    private void showReports() {
        map.put("Borang Aduan", "ENFBorangAduan_NS.rdf");
        map.put("Laporan Siasatan / Tanah Kosong", "ENFLTNH_KOSONG.rdf");
        map.put("Borang Kenyataan", "ENFPERCAKAPANDALAMPEMERIKSAAN_NS.rdf");
        map.put("Inventori Barang Rampasan", "ENFSENARAIBARANGRAMPASAN_NS.rdf");
        map.put("Notis dan Inventori", "ENFNOTISDANINVENTORI_NS.rdf");
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}

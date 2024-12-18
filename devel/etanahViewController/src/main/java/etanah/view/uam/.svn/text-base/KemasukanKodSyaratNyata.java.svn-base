/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.RegService;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import etanah.view.*;
import etanah.model.*;
import etanah.dao.*;
import etanah.util.*;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author Zairul
 */
@HttpCache(allow = false)
@UrlBinding("/uam/kemasukan_kod_syarat_nyata")
public class KemasukanKodSyaratNyata extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KemasukanKodSyaratNyata.class);
    @Inject
    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    private List<KodSyaratNyata> listKodSyaratNyata;
    String kodSyaratNyata;
    String syaratNyata;
    String syaratNyataBaru;
    String kodSyaratNyataBaru;
    String kodKatTanahBaru;
    String kodKatTanah;
    KodSyaratNyata checkKodSyarat;

    @DefaultHandler
    public Resolution showForm() {
        cariKodSyaratNyata();
        return new JSP("uam/masuk_syarat_nyata.jsp");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();

        if (kodSyaratNyata != null) {
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata, kodKatTanah);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata, kodKatTanah);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("uam/masuk_syarat_nyata.jsp");
    }
    
      public Resolution tambahKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();

        checkKodSyarat = regService.searchKodSyaratByCaw(kodSyaratNyataBaru, kc);

        if (checkKodSyarat != null) {
            addSimpleError("Kod Syarat Nyata sudah wujud, sila masukkan Kod Syarat Nyata yang lain");
            return new JSP("uam/masuk_syarat_nyata.jsp");

        } else {

            KodSyaratNyata kodSyaratNyata = new KodSyaratNyata();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            kodSyaratNyata.setSyarat(syaratNyataBaru);
            kodSyaratNyata.setKod(kc.concat(kodSyaratNyataBaru));
            kodSyaratNyata.setKodSyarat(kodSyaratNyataBaru);
            kodSyaratNyata.setCawangan(peng.getKodCawangan());
            kodSyaratNyata.setInfoAudit(ia);
            kodSyaratNyata.setAktif('Y');
            if (StringUtils.isNotBlank(kodKatTanahBaru)) {
                kodSyaratNyata.setKategoriTanah(kodKategoriTanahDAO.findById(kodKatTanahBaru));
            }
            regService.simpanKodSyaratNyata(kodSyaratNyata);
            
            addSimpleMessage("Kod Syarat Nyata "+kodSyaratNyataBaru+" berjaya diwujudkan");     
            return new JSP("uam/masuk_syarat_nyata.jsp");
        }
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyataBaru() {
        return syaratNyataBaru;
    }

    public void setSyaratNyataBaru(String syaratNyataBaru) {
        this.syaratNyataBaru = syaratNyataBaru;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }  

    public KodSyaratNyata getCheckKodSyarat() {
        return checkKodSyarat;
    }

    public void setCheckKodSyarat(KodSyaratNyata checkKodSyarat) {
        this.checkKodSyarat = checkKodSyarat;
    }    

    public String getKodKatTanahBaru() {
        return kodKatTanahBaru;
    }

    public void setKodKatTanahBaru(String kodKatTanahBaru) {
        this.kodKatTanahBaru = kodKatTanahBaru;
    }
 
}

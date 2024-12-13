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

/**
 *
 * @author Zairul
 */
@HttpCache(allow = false)
@UrlBinding("/uam/kemasukan_kod_sekatan")
public class KemasukanKodSekatan extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KemasukanKodSekatan.class);
    @Inject
    RegService regService;
    @Inject
    KodSekatanKepentinganDAO  kodSekatanKepentinganDAO;
    private List<KodSekatanKepentingan> listKodSekatan;
    String kodSekatan;
    String sekatan;
    String availabeKod;
    String sekatanBaru;
    KodSekatanKepentingan checkKodSekatan;
    String kodSekatanBaru;

    @DefaultHandler
    public Resolution showForm() {
        cariKodSekatan();
        return new JSP("uam/masuk_sekatan_main.jsp");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();

        if (kodSekatan != null) {
            listKodSekatan = regService.searchKodSekatan(kodSekatan, kc, sekatan);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = regService.searchKodSekatan("%", kc, sekatan);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("uam/masuk_sekatan_main.jsp");
    }

    public Resolution tambahKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();

        checkKodSekatan = regService.searchKodSekatanByCaw(kodSekatanBaru, kc);

        if (checkKodSekatan != null) {
            addSimpleError("Kod Sekatan sudah wujud, sila masukkan Kod Sekatan yang lain");
            return new JSP("uam/masuk_sekatan_main.jsp");

        } else {

            KodSekatanKepentingan kodSekatanKepentingan = new KodSekatanKepentingan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            kodSekatanKepentingan.setSekatan(sekatanBaru);
            kodSekatanKepentingan.setKod(kc.concat(kodSekatanBaru));
            kodSekatanKepentingan.setKodSekatan(kodSekatanBaru);
            kodSekatanKepentingan.setCawangan(peng.getKodCawangan());
            kodSekatanKepentingan.setInfoAudit(ia);
            kodSekatanKepentingan.setAktif('Y');                                
            regService.simpanKodSekatan(kodSekatanKepentingan);           

            addSimpleMessage("Kod Sekatan "+kodSekatanBaru+" berjaya diwujudkan");     
            return new JSP("uam/masuk_sekatan_main.jsp");
        }
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getAvailabeKod() {
        return availabeKod;
    }

    public void setAvailabeKod(String availabeKod) {
        this.availabeKod = availabeKod;
    }

    public KodSekatanKepentingan getCheckKodSekatan() {
        return checkKodSekatan;
    }

    public void setCheckKodSekatan(KodSekatanKepentingan checkKodSekatan) {
        this.checkKodSekatan = checkKodSekatan;
    }

    public String getKodSekatanBaru() {
        return kodSekatanBaru;
    }

    public void setKodSekatanBaru(String kodSekatanBaru) {
        this.kodSekatanBaru = kodSekatanBaru;
    }

    public String getSekatanBaru() {
        return sekatanBaru;
    }

    public void setSekatanBaru(String sekatanBaru) {
        this.sekatanBaru = sekatanBaru;
    }
        
}

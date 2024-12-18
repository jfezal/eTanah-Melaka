/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import etanah.model.Pengguna;
import java.util.ArrayList;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import java.util.List;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author khairil
 */
@UrlBinding("/utiliti/pertanyaan_kod")
public class PertanyaanKod extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PertanyaanKod.class);
    private List<KodSekatanKepentingan> listKodSekatan = new ArrayList();
    private List<KodSyaratNyata> listKodSyaratNyata;
    String kodSekatan;
    String kodSyaratNyata;
    String kodKatTanah;
    String syaratNyata;
    String sekatan;
    @Inject
    RegService regService;

//    @DefaultHandler
//    public Resolution showForm() {
//        return new JSP("daftar/kemasukan_maklumat_asas.jsp").addParameter("tab", "true");
//    }
    @DefaultHandler
    public Resolution showFormCariKodSekatan() {
        cariKodSekatan();
        return new JSP("utiliti/carian_kodsekatan.jsp");
    }

    public Resolution showFormCariKodSyaratNyata() {
        cariKodSyaratNyata();
        return new JSP("utiliti/carian_kodsyaratnyata.jsp");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
        if (kodSekatan != null) {
            listKodSekatan = regService.searchKodSekatan(kodSekatan, kc, sekatan);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());

        } else {
            listKodSekatan = regService.searchKodSekatan("%", kc, sekatan);
//            addSimpleError("Sila Cari / Pilih Kod Sekatan");
        }
        if (listKodSekatan.size() < 1) {
//            addSimpleError("Kod Sekatan Tidak Sah");
        }
        return new JSP("utiliti/carian_kodsekatan.jsp");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata , kodKatTanah);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata , kodKatTanah);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
        }
        if (listKodSyaratNyata.size() < 1) {
//            if (kodKatTanah.compareTo("4") == 0)
//            addSimpleError("Kod Syarat Nyata Tiada");
//            else
//            if (kodKatTanah.compareTo("0") == 0)
//            addSimpleError("Kod Syarat Nyata Tidak Sah");
        }
        return new JSP("utiliti/carian_kodsyaratnyata.jsp");
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    
    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }
}

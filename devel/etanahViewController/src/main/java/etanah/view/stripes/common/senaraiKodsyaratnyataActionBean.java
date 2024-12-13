/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;



/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/senarai/kodsyaratnyata")
public class senaraiKodsyaratnyataActionBean extends AbleActionBean{
 @Inject
 KodSekatanKepentinganDAO kodsekatanKepentinganDAO;
 @Inject
 KodSyaratNyataDAO kodsyaratNyataDAO;

 private static final Logger LOG = Logger.getLogger(senaraiKodsyaratnyataActionBean.class);
private List<KodSekatanKepentingan> kodsekatanKepentinganList;
private List<KodSyaratNyata> kodsyaratNyataList;
private KodSekatanKepentingan kodsekatanKepentingan;
private KodSyaratNyata kodsyaratNyata;
@DefaultHandler
    public Resolution urusanTerlibat() {
        return new JSP("common/senaraiKodsekatanKepentingan.jsp").addParameter("tab", "true");
    }

@Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

            kodsekatanKepentinganList = kodsekatanKepentinganDAO.findAll();
            kodsyaratNyataList = kodsyaratNyataDAO.findAll();
           
            for (KodSekatanKepentingan KSP : kodsekatanKepentinganList) {
//                LOG.debug("KOD Sekatan " + KSP.getKod());

            }
//            size = hakmilikPermohonanList.size();
//            if(!(hakmilikPermohonanList.isEmpty())){
//                hakmilikPermohonan = hakmilikPermohonanList.get(0);
//
//        }
//         }
    }

    public KodSekatanKepentingan getKodsekatanKepentingan() {
        return kodsekatanKepentingan;
    }

    public void setKodsekatanKepentingan(KodSekatanKepentingan kodsekatanKepentingan) {
        this.kodsekatanKepentingan = kodsekatanKepentingan;
    }

    public List<KodSekatanKepentingan> getKodsekatanKepentinganList() {
        return kodsekatanKepentinganList;
    }

    public void setKodsekatanKepentinganList(List<KodSekatanKepentingan> kodsekatanKepentinganList) {
        this.kodsekatanKepentinganList = kodsekatanKepentinganList;
    }

    public KodSyaratNyata getKodsyaratNyata() {
        return kodsyaratNyata;
    }

    public void setKodsyaratNyata(KodSyaratNyata kodsyaratNyata) {
        this.kodsyaratNyata = kodsyaratNyata;
    }

    public List<KodSyaratNyata> getKodsyaratNyataList() {
        return kodsyaratNyataList;
    }

    public void setKodsyaratNyataList(List<KodSyaratNyata> kodsyaratNyataList) {
        this.kodsyaratNyataList = kodsyaratNyataList;
    }

}

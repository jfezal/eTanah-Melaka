package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;

/**
 *
 * @author abdul.hakim
 * 15 June 2011
 *
 **/
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/cetak_ringkasan")
public class CetakanRingkasanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(CetakanRingkasanActionBean.class);
    private static boolean isDebug = logger.isDebugEnabled();
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/cetakan_ringkasan.jsp";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String tarikh;
    private String mod;
    private Pengguna pguna = new Pengguna();
    private String kodNegeri;

    @Inject
    private etanah.Configuration conf;
    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("..:: Step 1 ::..");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeriSembilan";
        }
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Date now = new Date();

        pguna = ctx.getUser();
        tarikh = sdf.format(now);
        logger.info("kodNegeri : " + kodNegeri);
        return new ForwardResolution(DEFAULT_VIEW);
    }
}

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.SejarahTransaksi;
import etanah.model.Transaksi;
import etanah.service.AkaunService;
import etanah.service.RegService;
import etanah.service.common.HakmilikService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
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
 * @author muhammad.amin
 */
@UrlBinding("/hasil/carianAkaun")

public class CarianAkaunActionBean extends AbleActionBean {

    @Inject
    HakmilikService hakmilikService;
    @Inject
    AkaunService akaunService;
    @Inject
    RegService regService;

    private TaskDebugService taskDebugService;

    private Akaun akaun;
    private Hakmilik hakmilik;
    private Transaksi transaksi;
    private AkaunDAO accDAO;
    private HakmilikDAO hakmilikDAO;
    private TransaksiDAO transaksiDAO;
    private List<SejarahTransaksi> sejarahList;
    private List<Akaun> senaraiAkaun;
    private String idHakmilik;
    private String NoAkaun;
    
    
    etanahActionBeanContext ctx;
    private static final Logger LOG = Logger.getLogger(CarianAkaunActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/carian_akaun.jsp");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() throws ParseException {
        
        LOG.debug("-------------***IDHAKMILIK***--------------" + idHakmilik);
        
        if (idHakmilik == null) {
            addSimpleError("Sila masukkan ID Hakmilik");
            return showForm();
        }
        
        hakmilik = hakmilikService.findById(idHakmilik);
        if (hakmilik != null) {
           if (hakmilik.getKodStatusHakmilik().getKod() == "D"){
             LOG.debug("-------------***HAKMILIK_DAFTAR***--------------" + hakmilik );
           }
           else if (hakmilik.getKodStatusHakmilik().getKod() == "B"){
             LOG.debug("-------------***BATAL***--------------" + hakmilik );
           }
        }
        
        akaun = akaunService.findById(idHakmilik);
        if (akaun != null){
            if (akaun.getKodAkaun().getKod() == "A"){
                LOG.debug("-------------***AKAUN_SAH***--------------" + akaun );
            }
            else if (akaun.getKodAkaun().getKod()== "B"){
            LOG.debug("-------------***AKAUN_BATAL***--------------" + akaun );
            }
                        }        
        return new ForwardResolution("/WEB-INF/jsp/hasil/carian_akaun.jsp");
    
    }

    public Resolution reset() {

        hakmilik = new Hakmilik();

        return new ForwardResolution("/WEB-INF/jsp/hasil/carian_akaun.jsp");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoAkaun() {
        return NoAkaun;
    }

    public void setNoAkaun(String NoAkaun) {
        this.NoAkaun = NoAkaun;
    }
    
    

}

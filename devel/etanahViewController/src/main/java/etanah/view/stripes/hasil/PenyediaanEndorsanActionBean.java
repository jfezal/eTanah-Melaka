package etanah.view.stripes.hasil;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author nurfaizati
 */
@UrlBinding("/hasil/endorsan")
public class PenyediaanEndorsanActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PenyediaanEndorsanActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    private HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;

    @Inject
    private AkaunDAO akaunDAO;
    private Akaun akaun;
    private List<HakmilikPihakBerkepentingan> pihakList;

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;

    @Inject
    private  etanah.Configuration conf;

    private String idHakmilik;
    private String negeri;
    private String noFail;

    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            // untuk negeri melaka
            negeri = "melaka";
        }
        return new JSP("hasil/penyediaan_borang_endorsan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = permohonanDAO.findById(idPermohonan).getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        LOG.debug("idHakmilik :"+idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        String[] name = {"hakmilik"};
        Object[] value = {getHakmilik()};
        List<Akaun> list = akaunDAO.findByEqualCriterias(name, value, null);
        for (int i = 0; i < list.size(); i++) {
            Akaun ak = list.get(i);
             pihakList = ak.getHakmilik().getSenaraiPihakBerkepentingan();
        }
        List<DasarTuntutanCukaiHakmilik> senaraiDasarHakmilik = dasarTuntutanCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
        for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarHakmilik) {
            if(dtch.getStatus().getKod().equals("ST")){ // ST = Sedang Dituntut in table kod_sts_tuntutan_cukai
                noFail = dtch.getNoRujukan();
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public List<HakmilikPihakBerkepentingan> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<HakmilikPihakBerkepentingan> pihakList) {
        this.pihakList = pihakList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

/**
 *
 * @author abu.mansur
 */
import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.Akaun;
import etanah.model.HakmilikPihakBerkepentingan;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/hasil/papar_hakmilik_pihak")
public class PaparHakmilikPihakActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PaparHakmilikPihakActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    private HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;

    @Inject
    private AkaunDAO akaunDAO;
    private Akaun akaun;
    private List<HakmilikPihakBerkepentingan> pihakList;

    @Inject
    private etanah.Configuration conf;
    private String idHakmilik;
    private String negeri;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri")))
            negeri = "melaka";
        return new JSP("hasil/papar_hakmilik_pihak.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.debug("idHakmilik :"+idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);

        String[] name = {"hakmilik"};
        Object[] value = {getHakmilik()};
        List<Akaun> list = akaunDAO.findByEqualCriterias(name, value, null);
        for (int i = 0; i < list.size(); i++) {
            Akaun ak = list.get(i);
             pihakList = ak.getHakmilik().getSenaraiPihakBerkepentingan();
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

}

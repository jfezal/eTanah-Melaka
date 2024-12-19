/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.dao.*;
import etanah.model.DasarTuntutanCukai;
import etanah.model.Pengguna;
import etanah.service.HakmilikService;
import java.util.ArrayList;
import java.util.List;
import org.displaytag.util.ParamEncoder;
import etanah.view.etanahActionBeanContext;
import org.displaytag.tags.TableTagParameters;

/**
 *
 * @author nurfaizati
 */
//@Wizard(startEvents = {"selectTransaction", "search"})
@UrlBinding("/hasil/dasar")
public class DasarActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(DasarActionBean.class);
    private static boolean isDebug = logger.isDebugEnabled();
    private String kodNegeri;
    private DasarTuntutanCukai dasarTuntutanCukai;
    private List<DasarTuntutanCukaiHakmilik> dtchList;
    private List<DasarTuntutanCukai> senaraiDasar;
    private boolean flag = false;
     private Pengguna pengguna;
     private String idDasarHapus;

    @Inject
    HakmilikService hakmilikService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    NotisPeringatan6AManager manager;

    @DefaultHandler
    public Resolution selectTransaction() {

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            setKodNegeri("melaka");

        }

        return new ForwardResolution("/WEB-INF/jsp/hasil/dasar.jsp");

    }

    public Resolution search() {
            pengguna = ((Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER));
        senaraiDasar = hakmilikService.findDasar(getContext().getRequest().getParameterMap());//, get__pg_start(), get__pg_max_records(), getPengguna().getKodCawangan().getKod());
        setFlag(true);

        return new ForwardResolution("/WEB-INF/jsp/hasil/dasar.jsp");
    }
    
    public Resolution deleteDasar(){
        String result ="";
        if(idDasarHapus != null){
            DasarTuntutanCukai dasar = dasarTuntutanCukaiDAO.findById(idDasarHapus);
                logger.info("idDasar :"+dasar.getIdDasar());
                result = manager.deleteDasar(dasar);
        }else{
            result = "tiada";
        }
//        search();
        if(result.equals("success")){
            logger.debug("Dasar BERJAYA dihapuskan.");
            addSimpleMessage("Dasar BERJAYA dihapuskan.");
        }else if (result.equals("fail")){
            logger.error("Dasar TIDAK berjaya dihapuskan.");
            addSimpleError("Dasar TIDAK berjaya dihapuskan.");
        }else{
            logger.error("ID Dasar Tidak Dijumpai.");
            addSimpleError("ID Dasar Tidak Dijumpai.");
        }
        return new ForwardResolution("/WEB-INF/jsp/hasil/dasar.jsp");
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public DasarTuntutanCukai getDasarTuntutanCukai() {
        return dasarTuntutanCukai;
    }

    public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
        this.dasarTuntutanCukai = dasarTuntutanCukai;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<DasarTuntutanCukaiHakmilik> getDtchList() {
        return dtchList;
    }

    public void setDtchList(List<DasarTuntutanCukaiHakmilik> dtchList) {
        this.dtchList = dtchList;
    }

    /**
     * @return the pengguna
     */
    public Pengguna getPengguna() {
        return pengguna;
    }

    /**
     * @param pengguna the pengguna to set
     */
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<DasarTuntutanCukai> getSenaraiDasar() {
        return senaraiDasar;
    }

    public void setSenaraiDasar(List<DasarTuntutanCukai> senaraiDasar) {
        this.senaraiDasar = senaraiDasar;
    }

    public String getIdDasarHapus() {
        return idDasarHapus;
    }

    public void setIdDasarHapus(String idDasarHapus) {
        this.idDasarHapus = idDasarHapus;
    }
}

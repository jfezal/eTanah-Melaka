/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.cukaipetak;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.Pengguna;
import etanah.service.strata.cukaipetak.SenaraiB4KCukaiService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author john
 */
@UrlBinding("/strata/jana_b4k_cukai_petak")
public class SenaraiEndorsanB4kCukaiPetakActionBean extends AbleActionBean {

    @Inject
    SenaraiB4KCukaiService senaraiB4KCukaiService;
    List<MaklumatSkim> senaraiSkim = new ArrayList<MaklumatSkim>();
    private static final Logger LOG = Logger.getLogger(SenaraiEndorsanB4kCukaiPetakActionBean.class);
    Pengguna pengguna;
String daerah;
    String idHakmilik;
    String bpm;
    private List<KodDaerah> listKodDaerah;
    private List<KodBandarPekanMukim> listBPM;
    @DefaultHandler
    public Resolution showForm() {

        LOG.info("showForm");
        KodCawangan kodCaw = pengguna.getKodCawangan();
        senaraiSkim = senaraiB4KCukaiService.findSkimJana(kodCaw);
        setPage(senaraiSkim.size());

        int a = get__pg_start();
        int b = get__pg_max_records();
        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/senarai_janaan_geran_strata.jsp");
    }

    private void setPage(int senarai) {

        ParamEncoder encoder = new ParamEncoder("line");

        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        set__pg_total_records(senarai);
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        if (get__pg_max_records() > get__pg_total_records()) {
            set__pg_max_records(get__pg_total_records());
        }

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public List<MaklumatSkim> getSenaraiSkim() {
        return senaraiSkim;
    }

    public void setSenaraiSkim(List<MaklumatSkim> senaraiSkim) {
        this.senaraiSkim = senaraiSkim;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public List<KodDaerah> getListKodDaerah() {
        return listKodDaerah;
    }

    public void setListKodDaerah(List<KodDaerah> listKodDaerah) {
        this.listKodDaerah = listKodDaerah;
    }

    public List<KodBandarPekanMukim> getListBPM() {
        return listBPM;
    }

    public void setListBPM(List<KodBandarPekanMukim> listBPM) {
        this.listBPM = listBPM;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.cukaipetak;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.SkimStrataDAO;
import etanah.model.Pengguna;
import etanah.model.SkimStrata;
import etanah.service.strata.cukaipetak.CukaiPetakService;
import etanah.service.strata.cukaipetak.TunggakanCukaiPetak;
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
import org.hibernate.Session;

/**
 *
 * @author john
 */
@UrlBinding("/strata/tunggakan_cukai_petak")
public class KemaskiniTunggakanActionBean extends AbleActionBean {
String idHakmilik;
    @Inject
    CukaiPetakService cukaiPetakService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    TunggakanCukaiPetak tunggakanCukaiPetak;
    private static final Logger LOG = Logger.getLogger(KemaskiniTunggakanActionBean.class);
    Pengguna pengguna;
    Integer totalSkim;
    Integer totalKemaskini;
    private List<MaklumatSkim> senaraiSkim = new ArrayList<MaklumatSkim>();

    @DefaultHandler
    public Resolution showForm() {

        LOG.info("showForm");
        int a = get__pg_start();
        int b = get__pg_max_records();
        senaraiSkim = cukaiPetakService.findSkimTunggakan(a,b);
        //setPage(cukaiPetakService.findCountSkimTunggakan().intValue());
        setPage(senaraiSkim.size());
        LOG.info("senaraiSkim>>>"+senaraiSkim.size());
        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/janatunggakan.jsp");
    }
public Resolution cari() {
        LOG.info("showForm");
        senaraiSkim = cukaiPetakService.findSkimTunggakanIdHakmilik(idHakmilik);

        setPage(senaraiSkim.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/janatunggakan.jsp");
    }
    private void setPage(int senarai) {
        LOG.info("senarai>>>"+senarai);
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

    public Resolution janaCukai() {
        String idSkimStrata = getContext().getRequest().getParameter("idSkimStrata");
        SkimStrata skim = skimStrataDAO.findById(Long.parseLong(idSkimStrata));

        tunggakanCukaiPetak.setTunggakanCukaiPetak(skim.getHakmilikInduk().getIdHakmilik(), pengguna.getIdPengguna(), skim.getId());
        tunggakanCukaiPetak.janaTunggakan();

        addSimpleMessage("Tunggakan Cukai petak untuk " + skim.getHakmilikInduk().getIdHakmilik() + " sedang di jana.");

        return showForm();
    }

    public Resolution janaBerkumpulan() {
        String[] senaraiIdSkim = getContext().getRequest().getParameterValues("senaraiSkim");
        for (String idSkimStrata : senaraiIdSkim) {
//            idHakmilik = getContext().getRequest().getParameter("idHakmilik");
            SkimStrata skim = skimStrataDAO.findById(Long.parseLong(idSkimStrata));
            LOG.info(skim.getHakmilikInduk().getIdHakmilik());

            tunggakanCukaiPetak.setTunggakanCukaiPetak(skim.getHakmilikInduk().getIdHakmilik(), pengguna.getIdPengguna(), skim.getId());
            tunggakanCukaiPetak.janaTunggakan();

            addSimpleMessage("Tunggakan Cukai petak untuk " + skim.getHakmilikInduk().getIdHakmilik() + " sedang di jana.");

        }
        return showForm();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Integer getTotalSkim() {
        return totalSkim;
    }

    public void setTotalSkim(Integer totalSkim) {
        this.totalSkim = totalSkim;
    }

    public Integer getTotalKemaskini() {
        return totalKemaskini;
    }

    public void setTotalKemaskini(Integer totalKemaskini) {
        this.totalKemaskini = totalKemaskini;
    }

    public List<MaklumatSkim> getSenaraiSkim() {
        return senaraiSkim;
    }

    public void setSenaraiSkim(List<MaklumatSkim> senaraiSkim) {
        this.senaraiSkim = senaraiSkim;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

}

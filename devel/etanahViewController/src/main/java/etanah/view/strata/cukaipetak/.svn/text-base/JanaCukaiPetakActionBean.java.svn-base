/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.cukaipetak;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.SkimStrataDAO;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.SkimStrata;
import etanah.service.strata.cukaipetak.CukaiPetakService;
import etanah.service.strata.cukaipetak.JanaCukaiPetak;
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
@UrlBinding("/strata/jana_cukai_petak")
public class JanaCukaiPetakActionBean extends AbleActionBean {

    @Inject
    CukaiPetakService cukaiPetakService;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    JanaCukaiPetak janaCukaiPetak;
    private List<MaklumatSkim> senaraiSkim = new ArrayList<MaklumatSkim>();
    private String idSkim[];
    private static final Logger LOG = Logger.getLogger(KemaskiniSkimStrataActionBean.class);
    Pengguna pengguna;
    Hakmilik hakmilik ;
    String idHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        senaraiSkim = cukaiPetakService.findSkimStrataJanaCukai();

        setPage(senaraiSkim.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/janacukaipetak.jsp");
    }
public Resolution cari() {
        LOG.info("showForm");
        senaraiSkim = cukaiPetakService.findSkimStrataJanaCukai(idHakmilik);

        setPage(senaraiSkim.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/janacukaipetak.jsp");
    }
    public Resolution janaCukai() {
        String idSkimStrata = getContext().getRequest().getParameter("idSkimStrata");
        SkimStrata skim = skimStrataDAO.findById(Long.parseLong(idSkimStrata));
        if (!skim.getFlagCukai().equals("Y")) {
            skim.setFlagCukai("Y");
            cukaiPetakService.saveSkimStrata(skim);

            janaCukaiPetak.setJanaCukaiPetakS(skim.getHakmilikInduk().getIdHakmilik(), pengguna.getIdPengguna(), skim.getId());
            janaCukaiPetak.jana();
            addSimpleMessage("Cukai petak untuk " + skim.getHakmilikInduk().getIdHakmilik() + " sedang di jana.");

        }
        return showForm();
    }

    public Resolution perincianHakmilik() {
        
          String idHakmilikInduk = getContext().getRequest().getParameter("idHakmilikInduk");
          hakmilik = hakmilikDAO.findById(idHakmilikInduk);

        return new JSP("/strata/cukaipetak/perincianHakmilikInduk.jsp").addParameter("popup", true);
    }

    public Resolution janaBerkumpulan() {
        String[] senaraiIdSkim = getContext().getRequest().getParameterValues("senaraiSkim");
        for (String idSkimStrata : senaraiIdSkim) {
//            idHakmilik = getContext().getRequest().getParameter("idHakmilik");
            SkimStrata skim = skimStrataDAO.findById(Long.parseLong(idSkimStrata));
            LOG.info(skim.getHakmilikInduk().getIdHakmilik());
            if (!skim.getFlagCukai().equals("Y")) {
                skim.setFlagCukai("Y");
                cukaiPetakService.saveSkimStrata(skim);

                janaCukaiPetak.setJanaCukaiPetakS(skim.getHakmilikInduk().getIdHakmilik(), pengguna.getIdPengguna(), skim.getId());
                janaCukaiPetak.jana();
                addSimpleMessage("Cukai petak untuk " + skim.getHakmilikInduk().getIdHakmilik() + " sedang di jana.");

            }
        }
        return showForm();
    }

    public Resolution janaCukaiMultiple() {
        idSkim = getContext().getRequest().getParameterValues("idSkim");
        for (int a = 0; a < idSkim.length; a++) {
            SkimStrata skim = skimStrataDAO.findById(Long.parseLong(idSkim[a]));

            if (!skim.getFlagCukai().equals("Y")) {

                skim.setFlagCukai("Y");
                cukaiPetakService.saveSkimStrata(skim);
                janaCukaiPetak.setJanaCukaiPetakS(skim.getHakmilikInduk().getIdHakmilik(), pengguna.getIdPengguna(), skim.getId());
                janaCukaiPetak.jana();
                addSimpleMessage("Cukai petak untuk " + skim.getHakmilikInduk().getIdHakmilik() + " sedang di jana.");

            }
        }
        return showForm();
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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

    public List<MaklumatSkim> getSenaraiSkim() {
        return senaraiSkim;
    }

    public void setSenaraiSkim(List<MaklumatSkim> senaraiSkim) {
        this.senaraiSkim = senaraiSkim;
    }

    public String[] getIdSkim() {
        return idSkim;
    }

    public void setIdSkim(String[] idSkim) {
        this.idSkim = idSkim;
    }

    public CukaiPetakService getCukaiPetakService() {
        return cukaiPetakService;
    }

    public void setCukaiPetakService(CukaiPetakService cukaiPetakService) {
        this.cukaiPetakService = cukaiPetakService;
    }

    public SkimStrataDAO getSkimStrataDAO() {
        return skimStrataDAO;
    }

    public void setSkimStrataDAO(SkimStrataDAO skimStrataDAO) {
        this.skimStrataDAO = skimStrataDAO;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public JanaCukaiPetak getJanaCukaiPetak() {
        return janaCukaiPetak;
    }

    public void setJanaCukaiPetak(JanaCukaiPetak janaCukaiPetak) {
        this.janaCukaiPetak = janaCukaiPetak;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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
    
}

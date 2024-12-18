/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/PKKR")
public class PermohonanStrataPKKRActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strataService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    CommonService comm;
    List<HakmilikPihakBerkepentingan> listPihak = new ArrayList();
    List<HakmilikPermohonan> listHakmilikP = new ArrayList();
    String idPermohonan = new String();
    private static final Logger LOG = Logger.getLogger(PermohonanStrataPKKRActionBean.class);

    public Resolution showForm() {

        return new JSP("strata/kos_rendah/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution maklumatTanah() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listHakmilikP = permohonanDAO.findById(idPermohonan).getSenaraiHakmilik();
        return new JSP("strata/kos_rendah/maklumatTanahList.jsp").addParameter("tab", "true");
    }

    public Resolution paparPemilik() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        listPihak = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hakmilik);
        return new JSP("strata/common/senaraiPihak.jsp").addParameter("tab", true);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution paparsuratPKKR() {
        return new JSP("strata/kos_rendah/paparsuratPKKR.jsp").addParameter("tab", true);
    }

    public Resolution jana() {
        String kodNegeri = conf.getProperty("kodNegeri");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        comm.setPengguna(pengguna);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        FasaPermohonan mohonFasa = new FasaPermohonan();
        if (kodNegeri.equals("04")) {
            mohonFasa = strataService.findMohonFasa(idPermohonan, "keputusanmmk");
        } else {

            mohonFasa = strataService.findMohonFasa(idPermohonan, "keputusanmmk");
        }
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (mohonFasa != null) {
            LOG.info("START REPORT");
            if (!mohonFasa.getKeputusan().getKod().equals(null)) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusBgnKosRendah_MLK.rdf");
                        t2.add("SLKR");

                        t.add("STRBHKosRendah_MLK.rdf");
                        t2.add("KKR");
                        comm.reportGen(p, t, t2);
                    } else {
                        t.add("STRSLulusBgnKosRendah_NS.rdf");
                        t2.add("SLKR");

                        t.add("STRBHKosRendah_NS.rdf");
                        t2.add("KKR");
                        comm.reportGen(p, t, t2);


                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakBgnKosRendah_MLK.rdf");
                        t2.add("STKR");
                        comm.reportGen(p, t, t2);
                    } else {
                        t.add("STRSTolakBgnKosRendah_NS.rdf");
                        t2.add("STKR");
                        comm.reportGen(p, t, t2);
                    }
                }
            } else {
                addSimpleError("Sila masukkan keputusan terlebih dahulu");
            }
        } else {
            addSimpleError("Sila masukkan keputusan terlebih dahulu");
        }
        return new RedirectResolution(PermohonanStrataPKKRActionBean.class, "paparsuratPKKR");
    }

    public List<HakmilikPihakBerkepentingan> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<HakmilikPihakBerkepentingan> listPihak) {
        this.listPihak = listPihak;
    }

    public List<HakmilikPermohonan> getListHakmilikP() {
        return listHakmilikP;
    }

    public void setListHakmilikP(List<HakmilikPermohonan> listHakmilikP) {
        this.listHakmilikP = listHakmilikP;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}

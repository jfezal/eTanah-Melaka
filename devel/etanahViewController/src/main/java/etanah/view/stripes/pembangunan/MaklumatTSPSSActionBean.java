/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.model.InfoAudit;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.List;
import etanah.service.PembangunanService;
import etanah.model.PermohonanPlotPelan;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/melaka/maklumat_TSPSS")
public class MaklumatTSPSSActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(MaklumatTSPSSActionBean.class);
    @Inject
    PembangunanService devService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    private List<PermohonanPlotPelan> senaraiPlotPelan;


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/maklumat_TSPSS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/maklumat_TSPSS.jsp").addParameter("tab", "true");
    }

    public Resolution showBorang7c() {
        return new JSP("pembangunan/melaka/borang_7C_TSPSS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPlotPelan = devService.findPermohonanPlotPelanByIdPermohonanOnlyForTSPSS(idPermohonan);
    }

    public Resolution simpan(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodSyaratNyata;
        String kodSekatan;

        for (int i = 0; i < senaraiPlotPelan.size(); i++) {           
           kodSyaratNyata = getContext().getRequest().getParameter("kodSyaratNyata" + i);
           kodSekatan = getContext().getRequest().getParameter("kodSekatan" + i);
           PermohonanPlotPelan mpp=new PermohonanPlotPelan();
           mpp = senaraiPlotPelan.get(i);
           InfoAudit infoAudit=new InfoAudit();
           infoAudit = mpp.getInfoAudit();
           infoAudit.setTarikhKemaskini(new java.util.Date());
           infoAudit.setDiKemaskiniOleh(pengguna);
           if (mpp != null) {
//               LOG.debug("------kodSyaratNyata--------:" + kodSyaratNyata);
//               LOG.debug("------kodSekatan------------:" + kodSekatan);
               if (kodSyaratNyata != null && !kodSyaratNyata.equals("")) {
                   KodSyaratNyata syaratNyata1 = kodSyaratNyataDAO.findById(kodSyaratNyata);                 
                   mpp.setKodSyaratNyata(syaratNyata1);
               } else {
                   mpp.setKodSyaratNyata(null);
               }
               if (kodSekatan != null && !kodSekatan.equals("")) {
                   KodSekatanKepentingan sekatanKepentingan1 = kodSekatanKepentinganDAO.findById(kodSekatan);                   
                   mpp.setKodSekatanKepentingan(sekatanKepentingan1);
               } else {
                   mpp.setKodSekatanKepentingan(null);
               }
               mpp.setInfoAudit(infoAudit);
               devService.simpanPermohonanPlotPelan(mpp);
           }
       }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new JSP("pembangunan/melaka/maklumat_TSPSS.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPlotPelan> getSenaraiPlotPelan() {
        return senaraiPlotPelan;
    }

    public void setSenaraiPlotPelan(List<PermohonanPlotPelan> senaraiPlotPelan) {
        this.senaraiPlotPelan = senaraiPlotPelan;
    }

  
}

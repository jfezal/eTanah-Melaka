/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PembangunanService;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanAsal;
import etanah.view.etanahActionBeanContext;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.dao.PermohonanAsalDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/melaka/ulasanAdun")
public class UlasanAdunActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(UlasanAdunActionBean.class);
    @Inject
    PembangunanService devServ;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanAsalDAO permohonanAsalDAO;

    private Permohonan mohon;
    private PermohonanRujukanLuar ulasanAdun;
    private String idMohonAsal;
    private PermohonanAsal mohonAsal;
    private String idPermohonan;
    Permohonan permohonanCurr;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/ulasan_adun.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanCurr = permohonanDAO.findById(idPermohonan);
    //    mohon = permohonanDAO.findById(idPermohonan);
    //    ulasanAdun = devServ.findUlasanAdun(idPermohonan);
        if(idPermohonan != null){
            PermohonanAsal ma = new PermohonanAsal();
            ma = devServ.findPermohonanAsal(idPermohonan);            
            if(ma != null && ma.getIdPermohonanAsal() != null){                
                ulasanAdun = devServ.findUlasanAdun(ma.getIdPermohonanAsal().getIdPermohonan());
            }
        }
        LOG.info("ualasan Aduan:"+ulasanAdun);
    }

    public Resolution cariUlasan(){
        idMohonAsal = getContext().getRequest().getParameter("idMohonAsal");
        mohon = permohonanDAO.findById(idMohonAsal);
        ulasanAdun = devServ.findUlasanAdun(idMohonAsal);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonAsal = devServ.findPermohonanAsal(idPermohonan);
        LOG.info("/// mohonAsal ////:"+mohonAsal);
        InfoAudit ia = new InfoAudit();
        if(mohonAsal != null){            
            ia = mohonAsal.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }else{
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            mohonAsal = new PermohonanAsal();
        }

        mohonAsal.setIdPermohonan(permohonanCurr);
        mohonAsal.setCawangan(pengguna.getKodCawangan());
        mohonAsal.setInfoAudit(ia);
        mohonAsal.setIdPermohonanAsal(mohon);
        devServ.simpanMohonAsal(mohonAsal);        
        return new JSP("pembangunan/melaka/ulasan_adun.jsp").addParameter("tab", "true");
    }

  
    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public String getIdMohonAsal() {
        return idMohonAsal;
    }

    public void setIdMohonAsal(String idMohonAsal) {
        this.idMohonAsal = idMohonAsal;
    }

    public PermohonanAsal getMohonAsal() {
        return mohonAsal;
    }

    public void setMohonAsal(PermohonanAsal mohonAsal) {
        this.mohonAsal = mohonAsal;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    
}

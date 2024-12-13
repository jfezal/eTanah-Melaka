/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.service.PelupusanService;
import etanah.service.PermohonanLaporanPelanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


/**
 *
 * @author Navin
 */

@UrlBinding("/pelupusan/pelan_spGSA")
public class PelanSPActionBean extends AbleActionBean {

    private Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    PermohonanLaporanPelanService permohonanLaporanPelanService;
    private InfoAudit ia;
    private Pengguna peng;
    private String idPermohonan;
    private PermohonanLaporanKawasan permohonanLaporanKawasan ;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("pelupusan/gsa/pelan_sp.jsp").addParameter("tab", "true");
    }
    
    public Resolution viewForm(){
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("pelupusan/gsa/pelan_sp.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if(permohonan != null){
            permohonanLaporanKawasan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
            if(permohonanLaporanKawasan == null){
                permohonanLaporanKawasan = new PermohonanLaporanKawasan() ;
                 InfoAudit infoAudit = new InfoAudit();
                 infoAudit.setDimasukOleh(peng);
                 infoAudit.setTarikhMasuk(new java.util.Date());
                 permohonanLaporanKawasan.setInfoAudit(infoAudit);
                 permohonanLaporanKawasan.setPermohonan(permohonan);
                 permohonanLaporanKawasan.setKodCawangan(permohonan.getCawangan());
                 permohonanLaporanKawasan.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));
                 pelupusanService.simpanPermohonanLaporKawasan(permohonanLaporanKawasan);
            }
        }
     }

    public Resolution simpanPelanSP() {
        
             ia = new InfoAudit();
             peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//             ia.setDimasukOleh(peng);
//             ia.setTarikhMasuk(new java.util.Date());
             idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
             permohonan = permohonanDAO.findById(idPermohonan);
             PermohonanLaporanKawasan permohonanLaporanKawasanTemp = null ;
              if (Validation()) {
                  getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("pelupusan/gsa/pelan_sp.jsp").addParameter("tab", "true");
            }   
             if(permohonanLaporanKawasan != null){
                 ia = permohonanLaporanKawasan.getInfoAudit() ;
                 ia.setDiKemaskiniOleh(peng);
                 ia.setTarikhKemaskini(new java.util.Date());
                 permohonanLaporanKawasanTemp = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
                 if(permohonanLaporanKawasanTemp != null){
                     permohonanLaporanKawasanTemp.setTarikhTerimaWarta(permohonanLaporanKawasan.getTarikhTerimaWarta());
                     permohonanLaporanKawasanTemp.setNoPelanWarta(permohonanLaporanKawasan.getNoPelanWarta());
                     permohonanLaporanKawasanTemp.setInfoAudit(ia);
                     permohonanLaporanKawasanTemp.setPermohonan(permohonan);
                     permohonanLaporanKawasanTemp = permohonanLaporanPelanService.saveOrUpdate(permohonanLaporanKawasanTemp);
                 }
             }
             
             addSimpleMessage("Maklumat telah berjaya disimpan");
             getContext().getRequest().setAttribute("edit", Boolean.TRUE);
             return new JSP("pelupusan/gsa/pelan_sp.jsp").addParameter("tab", "true");
    }
    
    public boolean Validation() {
        boolean flag = false;

       if ((permohonanLaporanKawasan != null) && (permohonanLaporanKawasan.getTarikhTerimaWarta() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Terima Pelan SP");
        }
       else if ((permohonanLaporanKawasan != null) && (permohonanLaporanKawasan.getNoPelanWarta() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan No. Pelan SP");
        }
        return flag;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }




}


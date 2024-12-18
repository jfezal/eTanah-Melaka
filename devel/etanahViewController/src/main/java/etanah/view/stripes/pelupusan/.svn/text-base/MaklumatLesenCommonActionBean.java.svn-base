/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikDAO ;
import etanah.dao.PermohonanJenteraDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.ImejLaporan;
import etanah.model.LaporanTanah;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.service.LaporanTanahService;

/**
 *
 * @author Akmal
 */

@UrlBinding("/pelupusan/maklumat_lesen_common") 
public class MaklumatLesenCommonActionBean extends AbleActionBean {
    private static Logger logger = Logger.getLogger(MaklumatRayuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO ;

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList; 
    private List<Permit> permitList;
    private List<PermitItem> permitItemList;
    private String idPermohonan;
    private Permit permit;
    private PermitItem permitItem;
    private PermitSahLaku permitSahLaku;
    private Pengguna pengguna ;  
    private Pemohon pemohon ;
    private Pihak pihak ;
    private long idPermit;
    private long idPermitItem;

    
    @DefaultHandler
    public Resolution showForm() {
    return new JSP("pelupusan/common/maklumat_lesen.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;

    if(permohonan != null){
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        
        pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        //permitItemList = pelupusanService.findPermitItemByIdPermit(idPermitItem);melaka
       
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        permitItem = pelupusanService.findPermitItemByIdPermit(permit.getIdPermit());
        permitSahLaku = pelupusanService.findPermitSahLakuByIdMohon(idPermohonan);
        idPermit = permit.getIdPermit();
        idPermitItem = permitItem.getIdPermitItem();
        if(permit != null || permitItem != null || permitSahLaku != null)   
        { logger.info("--------------idPermit " + permit.getIdPermit());
           logger.info("--------------tarikhMula " + permitSahLaku.getTarikhPermitMula());
           logger.info("--------------tarikhAkhir " + permitSahLaku.getTarikhPermitTamat());
        }
        
    }
    }

    
    
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public List<Permit> getPermitList() {
        return permitList;
    }

    public void setPermitList(List<Permit> permitList) {
        this.permitList = permitList;
    }

    public List<PermitItem> getPermitItemList() {
        return permitItemList;
    }

    public void setPermitItemList(List<PermitItem> permitItemList) {
        this.permitItemList = permitItemList;
    }

    public long getIdPermitItem() {
        return idPermitItem;
    }

    public void setIdPermitItem(long idPermitItem) {
        this.idPermitItem = idPermitItem;
    }

    public long getIdPermit() {
        return idPermit;
    }

    public void setIdPermit(long idPermit) {
        this.idPermit = idPermit;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    
    
}

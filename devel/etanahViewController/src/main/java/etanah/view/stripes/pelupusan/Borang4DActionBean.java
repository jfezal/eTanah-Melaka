/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.LupusPermitDAO;
import etanah.model.InfoAudit;
import etanah.model.LupusPermit;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author sitifariza.hanim
 * modified by nurul izza on 05102010
 */
@UrlBinding("/pelupusan/borang4d")
public class Borang4DActionBean extends AbleActionBean  {

    @Inject
    PermohonanService permohonanService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    
    private LupusPermit lupusPermit;
    private int permitNo;
    private Date tarikh;
    private Date tarikhMula;
    private Date tarikhTamat;
    private Date tujuanPermit;
    private String peruntukanTambahan;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pguna;
    private LupusPermit permit;
    private String strukturBolehBina;
    private PermohonanTuntutanKos mohonTuntutKos ;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/Borang_4D.jsp").addParameter("tab", "true");
    }
        @Before(stages = {LifecycleStage.BindingAndValidation})
        public void rehydrate() {            
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            
            permohonan = permohonanService.findById(idPermohonan);
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB7");
            lupusPermit =pelupusanService.getLupusPermitByIdPermohonan(idPermohonan);
            
        }

  public Resolution simpan() {
      
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        String idPermit  = (String) getContext().getRequest().getParameter("lupusPermit.id");
        InfoAudit infoAudit = new InfoAudit();
        LupusPermit permitTemp = new LupusPermit();
  
        if((idPermit!=null)&&(!idPermit.equals(""))){
   
            permitTemp= lupusPermitDAO.findById(Long.parseLong(idPermit));
            infoAudit = permitTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }else{
            
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        
          permitTemp.setInfoAudit(infoAudit);

          permitTemp.setNoPermit(lupusPermit.getNoPermit());
          permitTemp.setTarikhKeluar(lupusPermit.getTarikhKeluar());
          permitTemp.setTarikhMula(lupusPermit.getTarikhMula());
          permitTemp.setTarikhTamat(lupusPermit.getTarikhTamat());
//          permitTemp.setTempohPermit(lupusPermit.getTempohPermit());
          permitTemp.setStrukturBolehBina(lupusPermit.getStrukturBolehBina());
          permitTemp.setPeruntukanTambahan(lupusPermit.getPeruntukanTambahan());
          permitTemp.setPermohonan(permohonan);
          permitTemp.setNoSiri("RohanSiri");
          permitTemp.setJenisPermit('R');
          permitTemp.setAktif('Y');
          pelupusanService.saveOrUpdate(permitTemp);

          addSimpleMessage("Maklumat telah Disimpan");
       

      return new JSP("pelupusan/Borang_4D.jsp").addParameter("tab", "true");
  }

    public int getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(int permitNo) {
        this.permitNo = permitNo;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

   

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(Date tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public Date getTujuanPermit() {
        return tujuanPermit;
    }

    public void setTujuanPermit(Date tujuanPermit) {
        this.tujuanPermit = tujuanPermit;
    }

    public LupusPermit getLupusPermit() {
        return lupusPermit;
    }

    public void setLupusPermit(LupusPermit lupusPermit) {
        this.lupusPermit = lupusPermit;
    }

    public String getStrukturBolehBina() {
        return strukturBolehBina;
    }

    public void setStrukturBolehBina(String strukturBolehBina) {
        this.strukturBolehBina = strukturBolehBina;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }
    
   



   
    
}

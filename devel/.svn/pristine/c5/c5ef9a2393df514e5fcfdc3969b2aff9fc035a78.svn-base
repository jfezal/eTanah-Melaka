/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodStatusPermohonanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
//import etanah.dao.HakmilikDAO ;
//import etanah.dao.KodKeputusanDAO;
//import etanah.dao.KodTuntutDAO;
//import etanah.dao.KodUOMDAO;
//import etanah.dao.PermitItemDAO;
//import etanah.dao.PermitItemKuantitiDAO;
//import etanah.dao.PermohonanJenteraDAO;
//import etanah.dao.PermohonanKertasDAO;
//import etanah.dao.PermohonanTuntutanKosDAO;
//import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
//import etanah.model.KodBahanBatu;
//import etanah.model.KodBandarPekanMukim;
//import etanah.model.KodKeputusan;
import etanah.model.KodUrusan;
import etanah.dao.PihakDAO;
import etanah.model.KodCawangan;
import etanah.model.KodStatusPermohonan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
//import etanah.model.PermohonanBahanBatuan;
//import etanah.model.PermohonanJentera;
//import etanah.model.PermohonanKertas;
//import etanah.model.PermohonanPermitItem;
//import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
/**
 *
 * @author Akmal
 */
@UrlBinding("/pelupusan/kemasukanSDP")
public class kemasukanSDPActionBean extends AbleActionBean{
    private static Logger logger = Logger.getLogger(kemasukanSDPActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodStatusPermohonanDAO kodStatusPermohonanDAO; 
    
    private Pihak pihak;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private KodUrusan kodUrusan;
    private KodCawangan kodCaw;
    private Pemohon pemohon;
    private String idPermohonan;
    private Long idPihak;
    private int sizeKod ;
    private List <Permohonan>senaraiSDP;
   
    
    @DefaultHandler
    public Resolution showForm() {
//        return new JSP("pelupusan/batuan/permohonan_sdp.jsp").addParameter("tab", "true");
        senaraiSDP = pelupusanService.findAllSDPByDIS();
        sizeKod = senaraiSDP.size();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/permohonan_sdp.jsp");
    }

    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
          senaraiSDP = pelupusanService.findAllSDPByDIS();
          sizeKod = senaraiSDP.size();
    }
    
    public Resolution tukarStatus() {
          Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       String item = getContext().getRequest().getParameter("item");
       String[] listSDP = item.split(",") ;
       InfoAudit ia = new InfoAudit() ;
       
       for(int i = 0 ; i < listSDP.length ; i++){
  
           if(!listSDP[i].equals("T")){
               Permohonan mohon = new Permohonan() ;
               mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(listSDP[i]);
               if(mohon!=null){
                    mohon.setStatus("AK");
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    pelupusanService.simpanPermohonan(mohon);
               }
           }
       }
      return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/permohonan_sdp.jsp");
    }
    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(Long idPihak) {
        this.idPihak = idPihak;
    }

    public List<Permohonan> getSenaraiSDP() {
        return senaraiSDP;
    }

    public void setSenaraiSDP(List<Permohonan> senaraiSDP) {
        this.senaraiSDP = senaraiSDP;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }
    
    
}

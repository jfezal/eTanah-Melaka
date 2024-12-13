/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import etanah.service.common.TanahRizabService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.view.ListUtil;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
@UrlBinding("/pelupusan/surat_badan_pengawal")
public class SuratBadanPengawalActionBean extends AbleActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    KodNegeriDAO kodNegeriDAO ;
    @Inject
    KodRujukanDAO kodRujukanDAO ;
    private Pengguna pengguna ;
    private String idPermohonan ;
    private Permohonan permohonan ;
    private TanahRizabPermohonan tanahRizabPermohonan ;
    private String kodNegeri ;
    private PermohonanRujukanLuar mohonRujLuar ;
    etanahActionBeanContext ctx;
    
     @DefaultHandler
    public Resolution sediaSuratForm() {
    return new JSP("pelupusan/sedia_surat_badan_pengawal.jsp").addParameter("tab", "true");
    }
     
      public Resolution terimaUlasanForm() {
          InfoAudit info = new InfoAudit();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        mohonRujLuar = pelupusanService.findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan) ;
        // For first time only
        if(mohonRujLuar == null){
              mohonRujLuar = new PermohonanRujukanLuar() ;           
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonRujLuar.setCawangan(permohonan.getCawangan());
            mohonRujLuar.setPermohonan(permohonan);
            mohonRujLuar.setInfoAudit(info);
            mohonRujLuar.setKodRujukan(kodRujukanDAO.findById("FL"));    
        pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
        }
    return new JSP("pelupusan/terima_ulasan_badan_pengawal.jsp").addParameter("tab", "true");
    }
   
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        if(permohonan != null){
            tanahRizabPermohonan = tanahRizabService.findByidPermohonan(idPermohonan) ;
            mohonRujLuar = pelupusanService.findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan) ;
        }
    }
    
    public Resolution simpanBadanPengawal(){
        ctx = (etanahActionBeanContext) getContext();
        tanahRizabPermohonan = new TanahRizabPermohonan();
        pengguna = new Pengguna();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        tanahRizabPermohonan = tanahRizabService.findByidPermohonan(idPermohonan) ;
        InfoAudit info = new InfoAudit();
        if(tanahRizabPermohonan != null){
            info = tanahRizabPermohonan.getInfoAudit() ;
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            tanahRizabPermohonan.setInfoAudit(info);
        }else{
            tanahRizabPermohonan = new TanahRizabPermohonan();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            tanahRizabPermohonan.setInfoAudit(info);
            tanahRizabPermohonan.setCawangan(permohonan.getCawangan());
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setDaerah(permohonan.getCawangan().getDaerah());
            tanahRizabPermohonan.setAktif('Y');
            List<HakmilikPermohonan> hakmilikPermohonanTemp = pelupusanService.getHakmilikPermohonan(idPermohonan);
            if(!hakmilikPermohonanTemp.isEmpty()){
                for(HakmilikPermohonan hp : hakmilikPermohonanTemp){
                    if(hp.getBandarPekanMukimBaru()!=null)
                        tanahRizabPermohonan.setBandarPekanMukim(hp.getBandarPekanMukimBaru());
                    else{
                        tanahRizabPermohonan.setBandarPekanMukim(hp.getHakmilik().getBandarPekanMukim());
                    }
                    if(!StringUtils.isEmpty(hp.getNoLot()))
                        tanahRizabPermohonan.setNoLot(hp.getNoLot());
                    else{
                        tanahRizabPermohonan.setNoLot(hp.getHakmilik().getNoLot());
                    }
                        
                }
            }
            
        }
        if(!kodNegeri.equals("0")){
            KodNegeri kn = kodNegeriDAO.findById(kodNegeri) ;
            tanahRizabPermohonan.setJagaNegeri(kn);
        }
        else {
            tanahRizabPermohonan.setJagaNegeri(null);
        }
        String penjaga = ctx.getRequest().getParameter("tanahRizabPermohonan.penjaga");
        String namaPenjaga = ctx.getRequest().getParameter("tanahRizabPermohonan.namaPenjaga");
        String jagaAlamat1 = ctx.getRequest().getParameter("tanahRizabPermohonan.jagaAlamat1");
        String jagaAlamat2 = ctx.getRequest().getParameter("tanahRizabPermohonan.jagaAlamat2");
        String jagaAlamat3 = ctx.getRequest().getParameter("tanahRizabPermohonan.jagaAlamat3");
        String jagaAlamat4 = ctx.getRequest().getParameter("tanahRizabPermohonan.jagaAlamat4");
        String jagaPoskod = ctx.getRequest().getParameter("tanahRizabPermohonan.jagaPoskod");
        if(!StringUtils.isEmpty(penjaga))
            tanahRizabPermohonan.setPenjaga(penjaga);
        if(!StringUtils.isEmpty(namaPenjaga))
            tanahRizabPermohonan.setNamaPenjaga(namaPenjaga);
        if(!StringUtils.isEmpty(jagaAlamat1))
            tanahRizabPermohonan.setJagaAlamat1(jagaAlamat1);
        if(!StringUtils.isEmpty(jagaAlamat2))
            tanahRizabPermohonan.setJagaAlamat2(jagaAlamat2);
        if(!StringUtils.isEmpty(jagaAlamat3))
            tanahRizabPermohonan.setJagaAlamat3(jagaAlamat3);
        if(!StringUtils.isEmpty(jagaAlamat4))
            tanahRizabPermohonan.setJagaAlamat4(jagaAlamat4);
        if(!StringUtils.isEmpty(jagaPoskod))
            tanahRizabPermohonan.setJagaPoskod(jagaPoskod);
        tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/sedia_surat_badan_pengawal.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanUlasanPengawal() {
        InfoAudit info = new InfoAudit();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        mohonRujLuar = pelupusanService.findPermohonanRujByIdPermohonanAndTiadaAgensi(idPermohonan) ;
        if(mohonRujLuar != null){
            info = mohonRujLuar.getInfoAudit() ;
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonRujLuar.setInfoAudit(info);
        }
        else {
            mohonRujLuar = new PermohonanRujukanLuar() ;
            
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonRujLuar.setCawangan(permohonan.getCawangan());
            mohonRujLuar.setPermohonan(permohonan);
            mohonRujLuar.setInfoAudit(info);
            mohonRujLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
        }
        pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/terima_ulasan_badan_pengawal.jsp").addParameter("tab", "true");
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

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }
    
    
}

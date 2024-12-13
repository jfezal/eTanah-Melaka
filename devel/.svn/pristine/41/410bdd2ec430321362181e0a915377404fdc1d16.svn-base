/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.KodDokumen;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/draf_pertimbangan_ptg")
public class DrafPertimbanganPTGActionBean extends AbleActionBean{
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private PermohonanKertas kertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private KodDokumen kodDokumen;
    private String idPermohonan;
    private String kandungan;
    private String kandungan1;
    private String kandungan2;
    private String kandungan3;
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/draf_pertimbangan_ptg.jsp").addParameter("tab","true");
    }

    @Before(stages ={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println(idPermohonan);
        kertas = pelupusanService.findByIdKrts(idPermohonan, "Draf PTG");
        if(kertas != null){
            int b = 1 ;
            for(int i = 0 ; i < kertas.getSenaraiKandungan().size() ; i++){
            permohonanKertasKandungan = pelupusanService.findByKertasBil(kertas.getIdKertas(), b);
            if(b == 1){
            kandungan = permohonanKertasKandungan.getKandungan();
            }
            else if(b == 2){
            kandungan1 = permohonanKertasKandungan.getKandungan();
            }
            else if(b == 3){
            kandungan2 = permohonanKertasKandungan.getKandungan();
            }
            else if(b == 4){
                kandungan3 = permohonanKertasKandungan.getKandungan();
            }
            b++ ;
            }
        }
    }

    public Resolution simpanDrafPTG(){

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List <PermohonanKertas> DrafPTG = permohonanKertasDAO.findByEqualCriterias(tname, tvalue, null);
        if(DrafPTG.isEmpty()){
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            
            KodDokumen kod = kodDokumenDAO.findById("KPTG");
            
            if(kod != null){
                kertas.setKodDokumen(kod);
            }else{
                
            }
            kertas = new PermohonanKertas();
            kertas.setInfoAudit(ia);
            kertas.setCawangan(permohonan.getCawangan());
            kertas.setTajuk("Draf PTG");
            kertas.setPermohonan(permohonan);

            pelupusanService.simpanPermohonanKertas(kertas);
            if(kandungan == null){
                kandungan = "Tiada" ;
            }
             if(kandungan1 == null){
                kandungan1 = "Tiada" ;
            }
             if(kandungan2 == null){
                kandungan2 = "Tiada" ;
            }
             if(kandungan3 == null){
                kandungan3 = "Tiada" ;
            }
            String[] a = {kandungan, kandungan1, kandungan2, kandungan3} ;
            int b = 1 ;
            for(int i = 0 ; i < 4 ; i++){
            PermohonanKertasKandungan pkkptg = new PermohonanKertasKandungan();
            pkkptg.setInfoAudit(ia);
            pkkptg.setKertas(kertas);
            pkkptg.setBil(b);
            pkkptg.setInfoAudit(ia);
            pkkptg.setKandungan(a[i]);
            pkkptg.setCawangan(permohonan.getCawangan());
            b++;

            pelupusanService.simpanPermohonanKertasKandungan(pkkptg);
            }
        } else{

            kertas = pelupusanService.findByIdKrts(idPermohonan, "Draf PTG");
            InfoAudit ia = new InfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDimasukOleh(kertas.getInfoAudit().getDimasukOleh()) ;
            ia.setTarikhMasuk(kertas.getInfoAudit().getTarikhMasuk()) ;
            kertas.setInfoAudit(ia);


            pelupusanService.simpanPermohonanKertas(kertas);
            List<PermohonanKertasKandungan> listDrafPTG = kertas.getSenaraiKandungan();
            if(kandungan == null){
                kandungan = "Tiada" ;
            }
             if(kandungan1 == null){
                kandungan1 = "Tiada" ;
            }
             if(kandungan2 == null){
                kandungan2 = "Tiada" ;
            }
             if(kandungan3 == null){
                kandungan3 = "Tiada" ;
            }
            if(!listDrafPTG.isEmpty()){
                    int b = 1 ;
                    String[] a = {kandungan, kandungan1, kandungan2, kandungan3} ;
                    for(int i = 0 ; i < kertas.getSenaraiKandungan().size() ; i++){
                        PermohonanKertasKandungan pkptg = pelupusanService.findByKertasBil(kertas.getIdKertas(), b) ;
                    pkptg.setKandungan(a[i]);
                 
                    pkptg.setInfoAudit(ia);
                    pelupusanService.simpanPermohonanKertasKandungan(pkptg);
                    b++;
                }
            }
        }
            addSimpleMessage("Maklumat telah berjaya disimpan");
            rehydrate();
      return new JSP("pelupusan/draf_pertimbangan_ptg.jsp").addParameter("tab","true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getKandungan1() {
        return kandungan1;
    }

    public void setKandungan1(String kandungan1) {
        this.kandungan1 = kandungan1;
    }

    public String getKandungan2() {
        return kandungan2;
    }

    public void setKandungan2(String kandungan2) {
        this.kandungan2 = kandungan2;
    }

    public String getKandungan3() {
        return kandungan3;
    }

    public void setKandungan3(String kandungan3) {
        this.kandungan3 = kandungan3;
    }
    


}




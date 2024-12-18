/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.model.Permohonan ;
import etanah.model.Hakmilik ;
import etanah.model.HakmilikPermohonan ;
import etanah.model.PermohonanTuntutanKos ;
import etanah.model.Pengguna ;
import etanah.dao.PermohonanDAO ;
import etanah.dao.HakmilikDAO ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Resolution ;
import net.sourceforge.stripes.action.Before ;
import net.sourceforge.stripes.action.UrlBinding ;
import net.sourceforge.stripes.action.DefaultHandler ;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage ;


/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/borang5A_BMBT")
public class Borang5ABMBTActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    PelupusanService plpservice ;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO ;
    @Inject
    KodTuntutDAO kodTuntutDAO ;
    private Permohonan permohonan ;
    private Hakmilik hakmilik ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private PermohonanTuntutanKos permohonanTuntutanKos ;
    private String idPermohonan ;
    private String premium ;
    private String premiumT ;
    private String cukaiS ;
    private String ukur ;
    private String pndftrn ;
    private String tndaSmpdn ;
    private BigDecimal premiumRM ;
    private BigDecimal premiumTRM ;
    private BigDecimal cukaiSRM ;
    private BigDecimal pndftrnRM ;
    private BigDecimal tndaSmpdnRM ;
    private BigDecimal ukurRM ;
    private BigDecimal jumlah ;
    private double jumlah1 ;
    private List senaraiItem ;
    private String test ;
    private BigDecimal harga ;
    private boolean item = false ;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos ;
    private PermohonanTuntutanKos mohonTuntutKos ;
    private Pengguna pengguna ;
    private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/bmbt/borang5A_BMBT.jsp").addParameter("tab", "true") ;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;

        
        String[] tvalue = {"permohonan"} ;
        Object[] tname = {permohonan} ;

         senaraiMohonTuntutKos = permohonanTuntutanKosDAO.findByEqualCriterias(tvalue, tname, null) ;
         hakmilikPermohonanList = permohonan.getSenaraiHakmilik() ;
         HakmilikPermohonan hmTemp = new HakmilikPermohonan();
            if(!hakmilikPermohonanList.isEmpty()){
                hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
            }
            
            String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
                if(idhakmilikpermohonan == null){
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                }
                else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
                }
         

        if(senaraiMohonTuntutKos.isEmpty()){
            getContext().getRequest().setAttribute("item", Boolean.FALSE);
        }
        else {
            getContext().getRequest().setAttribute("item", Boolean.TRUE);
            //senaraiItem = senaraiMohonTuntutKos ;
            for(int i=0 ; i < senaraiMohonTuntutKos.size() ; i++){
               this.permohonanTuntutanKos = (PermohonanTuntutanKos) senaraiMohonTuntutKos.get(i) ;
               //Premium Tanah
               if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISPRM") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){ //Premium
               premium = permohonanTuntutanKos.getItem() ;
               premiumRM = permohonanTuntutanKos.getAmaunTuntutan() ;
               jumlah1 = jumlah1 + premiumRM.doubleValue() ;
               }
               
               //Premium Tambahan
               if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISPRMT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){
               premiumT = permohonanTuntutanKos.getItem() ;
               premiumTRM = permohonanTuntutanKos.getAmaunTuntutan() ;
               jumlah1 = jumlah1 + premiumTRM.doubleValue() ;
               }

               
               //Cukai Stratum
               if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISTAX") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){
               cukaiS = permohonanTuntutanKos.getItem() ;
               cukaiSRM = permohonanTuntutanKos.getAmaunTuntutan() ;
               jumlah1 = jumlah1 + cukaiSRM.doubleValue() ;
               }
               
               //Bayaran Ukur
                if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISUKUR") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){
               ukur = permohonanTuntutanKos.getItem() ;
               ukurRM = permohonanTuntutanKos.getAmaunTuntutan() ;
               jumlah1 = jumlah1 + ukurRM.doubleValue() ;
               }
                
               //Bayaran Pendaftaran Hakmilik Tetap 
                 if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISFT") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){
               pndftrn = permohonanTuntutanKos.getItem() ;
               pndftrnRM = permohonanTuntutanKos.getAmaunTuntutan() ;
               jumlah1 = jumlah1 + pndftrnRM.doubleValue() ;
               }
                 
//                 //Bayaran Tanda Sempadan 
//                 if(permohonanTuntutanKos.getKodTuntut().getKod().equals("DISSEM") && permohonanTuntutanKos.getHakmilikPermohonan().getId() == hakmilikPermohonan.getId()){
//               tndaSmpdn = permohonanTuntutanKos.getItem() ;
//               tndaSmpdnRM = permohonanTuntutanKos.getAmaunTuntutan() ;
//               jumlah1 = jumlah1 + tndaSmpdnRM.doubleValue() ;
//               }
                        
            }
        }

       
        
    }
    
    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/bmbt/borang5A_BMBT.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }   

    public BigDecimal getPremiumRM() {
        return premiumRM;
    }

    public void setPremiumRM(BigDecimal premiumRM) {
        this.premiumRM = premiumRM;
    } 

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public boolean isItem() {
        return item;
    }

    public void setItem(boolean item) {
        this.item = item;
    }

    public List getSenaraiItem() {
        return senaraiItem;
    }

    public void setSenaraiItem(List senaraiItem) {
        this.senaraiItem = senaraiItem;
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }



    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public double getJumlah1() {
        return jumlah1;
    }

    public void setJumlah1(double jumlah1) {
        this.jumlah1 = jumlah1;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getCukaiS() {
        return cukaiS;
    }

    public void setCukaiS(String cukaiS) {
        this.cukaiS = cukaiS;
    }

    public BigDecimal getCukaiSRM() {
        return cukaiSRM;
    }

    public void setCukaiSRM(BigDecimal cukaiSRM) {
        this.cukaiSRM = cukaiSRM;
    }

    public String getPremiumT() {
        return premiumT;
    }

    public void setPremiumT(String premiumT) {
        this.premiumT = premiumT;
    }

    public BigDecimal getPremiumTRM() {
        return premiumTRM;
    }

    public void setPremiumTRM(BigDecimal premiumTRM) {
        this.premiumTRM = premiumTRM;
    }

    public String getUkur() {
        return ukur;
    }

    public void setUkur(String ukur) {
        this.ukur = ukur;
    }

    public BigDecimal getUkurRM() {
        return ukurRM;
    }

    public void setUkurRM(BigDecimal ukurRM) {
        this.ukurRM = ukurRM;
    }

    public String getPndftrn() {
        return pndftrn;
    }

    public void setPndftrn(String pndftrn) {
        this.pndftrn = pndftrn;
    }

    public BigDecimal getPndftrnRM() {
        return pndftrnRM;
    }

    public void setPndftrnRM(BigDecimal pndftrnRM) {
        this.pndftrnRM = pndftrnRM;
    }

    public String getTndaSmpdn() {
        return tndaSmpdn;
    }

    public void setTndaSmpdn(String tndaSmpdn) {
        this.tndaSmpdn = tndaSmpdn;
    }

    public BigDecimal getTndaSmpdnRM() {
        return tndaSmpdnRM;
    }

    public void setTndaSmpdnRM(BigDecimal tndaSmpdnRM) {
        this.tndaSmpdnRM = tndaSmpdnRM;
    }
    
}

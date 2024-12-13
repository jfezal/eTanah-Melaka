
/**
 *
 * @author afham
 */

package etanah.view.stripes.pelupusan ;

import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.model.Permohonan ;
import etanah.model.Hakmilik ;
import etanah.model.HakmilikPermohonan ;
import etanah.model.PermohonanTuntutanKos ;
import etanah.model.PermohonanRujukanLuar ;
import etanah.dao.HakmilikDAO ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanTuntutanKosDAO ;
import etanah.dao.PermohonanRujukanLuarDAO ;
import etanah.dao.PermohonanDAO ;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.service.PelupusanService ;
import etanah.service.StrataPtService ;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@UrlBinding("/pelupusan/pengiraan_notis_5a_mcl")
public class PengiraanNotis5AMCLActionBean extends AbleActionBean {

    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutKosDAO ;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO ;
     @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    KodTuntutDAO kodTuntutDAO ;
    private Hakmilik hakmilik ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private PermohonanTuntutanKos permohonanTuntutanKos ;
    private Pengguna pengguna ;
    private Permohonan permohonan ;
    private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
    //private StrataPtService str ;
    private PermohonanRujukanLuar permohonanRujukanLuar ;
    private int tahunP ;
    private int tahunS ;
    private int test ;
    private BigDecimal nilaiP ;
    private BigDecimal test4 ;
    private int bakiTempohPajakan ;
    private BigDecimal btp ;
    private BigDecimal btp1 ;
    private BigDecimal btp2 ;
    private BigDecimal np1 ;
    private BigDecimal np2 ;
    private BigDecimal np3 ;
    private BigDecimal np4 ;
    private BigDecimal np5 ;
    private BigDecimal np6 ;
    private BigDecimal nilaiPasaran1 ;
    private BigDecimal nilaiPasaran2 ;
    private BigDecimal nilaiPasaran3 ;
    private BigDecimal premium ;
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos ;
    private PermohonanTuntutanKos mohonTuntutKos ;
    private String thnhakmilik;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/pengiraan_notis5A_MCL.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan) ;
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;

        thnhakmilik = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "tahun_hm_daftar").getKeputusan().getKod();
        
            List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
            HakmilikPermohonan hmTemp = new HakmilikPermohonan();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik() ;
            if(!hakmilikPermohonanList.isEmpty()){
                hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
            }
            String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
                if(idhakmilikpermohonan == null){
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                    if(hakmilikPermohonan.getNilaianJpph() != null)
                    nilaiP = hakmilikPermohonan.getNilaianJpph() ;
                }
                else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
                    if(hakmilikPermohonan.getNilaianJpph() != null)
                    nilaiP = hakmilikPermohonan.getNilaianJpph() ;
                }
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;

            tahunP = Integer.parseInt(hakmilik.getTarikhLuput().toString().substring(0, 4));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            tahunS = Integer.parseInt(sdf.format(date));
            bakiTempohPajakan =  tahunP - tahunS;
            if(nilaiP != null && bakiTempohPajakan != 0){
                String baki = "" + bakiTempohPajakan ;
                double a = Double.parseDouble(baki);
                nilaiPasaran1 = nilaiP.multiply(BigDecimal.valueOf(a)).divide(new BigDecimal(100));
                nilaiPasaran2 = nilaiP.subtract(nilaiPasaran1);
                nilaiPasaran3 = nilaiPasaran2.divide(new BigDecimal(4));
                np6 = nilaiPasaran3.divide(new BigDecimal(2));
                premium = np6 ;
            }
//            nilaiP = permohonanRujukanLuar.getNilai() ;
            senaraiMohonTuntutKos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, tvalue, null) ;
//            if(senaraiMohonTuntutKos.isEmpty()){
//                getContext().getRequest().setAttribute("item", Boolean.FALSE);
//            }
//            else{
//                getContext().getRequest().setAttribute("item", Boolean.TRUE);
//                np1 = nilaiP ;
//                np2 = nilaiP ;
//                for(int i=0;i<senaraiMohonTuntutKos.size();i++){
//                    this.permohonanTuntutanKos = senaraiMohonTuntutKos.get(i) ;
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("tahun semasa")){
//                        tahunS = permohonanTuntutanKos.getAmaunTuntutan() ;
//                    }
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("bakitempohpajakan")){
//                        bakiTempohPajakan = permohonanTuntutanKos.getAmaunTuntutan() ;
//                        btp1 = bakiTempohPajakan ;
//                        btp2 = bakiTempohPajakan ;
//                        btp = bakiTempohPajakan ;
//                    }
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("nilaipasaran1")){
//                        nilaiPasaran1 = permohonanTuntutanKos.getAmaunTuntutan() ;
//                        np3 = nilaiPasaran1 ;
//                    }
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("nilaipasaran2")){
//                        nilaiPasaran2 = permohonanTuntutanKos.getAmaunTuntutan() ;
//                        np4 = nilaiPasaran2 ;
//                    }
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("nilaipasaran3")){
//                        nilaiPasaran3 = permohonanTuntutanKos.getAmaunTuntutan() ;
//                        np5 = nilaiPasaran3 ;
//                    }
//                    if(permohonanTuntutanKos.getItem().equalsIgnoreCase("premium")){
//                        premium = permohonanTuntutanKos.getAmaunTuntutan() ;
//                        np6 = premium ;
//                    }
//                }
//            }
            

            
              
    }

    public Resolution simpan(){
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tvalue = {"permohonan"} ;
        Object[] tname = {permohonan} ;

        senaraiMohonTuntutKos = permohonanTuntutanKosDAO.findByEqualCriterias(tvalue, tname, null);
        
         mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRM",hakmilikPermohonan.getId());
        InfoAudit info = new InfoAudit();

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Premium");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Premium");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
    

        addSimpleMessage("Maklumat Telah Disimpan") ;
        rehydrate() ;
        return new JSP("pelupusan/pengiraan_notis5A_MCL.jsp").addParameter("tab", "true") ;
    }
    
     public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/pengiraan_notis5A_MCL.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
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

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public int getTahunP() {
        return tahunP;
    }

    public void setTahunP(int tahunP) {
        this.tahunP = tahunP;
    }

    public int getTahunS() {
        return tahunS;
    }

    public void setTahunS(int tahunS) {
        this.tahunS = tahunS;
    }

    public BigDecimal getNilaiP() {
        return nilaiP;
    }

    public void setNilaiP(BigDecimal nilaiP) {
        this.nilaiP = nilaiP;
    }

    public int getBakiTempohPajakan() {
        return bakiTempohPajakan;
    }

    public void setBakiTempohPajakan(int bakiTempohPajakan) {
        this.bakiTempohPajakan = bakiTempohPajakan;
    }

    public BigDecimal getNilaiPasaran1() {
        return nilaiPasaran1;
    }

    public void setNilaiPasaran1(BigDecimal nilaiPasaran1) {
        this.nilaiPasaran1 = nilaiPasaran1;
    }

    public BigDecimal getNilaiPasaran2() {
        return nilaiPasaran2;
    }

    public void setNilaiPasaran2(BigDecimal nilaiPasaran2) {
        this.nilaiPasaran2 = nilaiPasaran2;
    }

    public BigDecimal getNilaiPasaran3() {
        return nilaiPasaran3;
    }

    public void setNilaiPasaran3(BigDecimal nilaiPasaran3) {
        this.nilaiPasaran3 = nilaiPasaran3;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getBtp() {
        return btp;
    }

    public void setBtp(BigDecimal btp) {
        this.btp = btp;
    }

    public BigDecimal getBtp1() {
        return btp1;
    }

    public void setBtp1(BigDecimal btp1) {
        this.btp1 = btp1;
    }

    public BigDecimal getBtp2() {
        return btp2;
    }

    public void setBtp2(BigDecimal btp2) {
        this.btp2 = btp2;
    }

    public BigDecimal getNp1() {
        return np1;
    }

    public void setNp1(BigDecimal np1) {
        this.np1 = np1;
    }

    public BigDecimal getNp2() {
        return np2;
    }

    public void setNp2(BigDecimal np2) {
        this.np2 = np2;
    }

    public BigDecimal getNp3() {
        return np3;
    }

    public void setNp3(BigDecimal np3) {
        this.np3 = np3;
    }

    public BigDecimal getNp4() {
        return np4;
    }

    public void setNp4(BigDecimal np4) {
        this.np4 = np4;
    }

    public BigDecimal getNp5() {
        return np5;
    }

    public void setNp5(BigDecimal np5) {
        this.np5 = np5;
    }

    public BigDecimal getNp6() {
        return np6;
    }

    public void setNp6(BigDecimal np6) {
        this.np6 = np6;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public String getThnhakmilik() {
        return thnhakmilik;
    }

    public void setThnhakmilik(String thnhakmilik) {
        this.thnhakmilik = thnhakmilik;
    }
}
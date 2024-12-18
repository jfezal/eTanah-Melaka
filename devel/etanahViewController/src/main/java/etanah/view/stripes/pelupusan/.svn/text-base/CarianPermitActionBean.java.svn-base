/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermitDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author afham
 */

@UrlBinding("/pelupusan/carian_permit")
public class CarianPermitActionBean extends AbleActionBean {

     @Inject
    PelupusanService pelupusanService;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO ;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    PermitDAO permitDAO ;
    @Inject
    PelupusanService plpService;

    private boolean status = false ;
    private List<PermitSahLaku> renewList ;
    private PermitSahLaku permitSahLaku ;
    private Pihak pihakList ;
    private Permit permitTemp ;
    private Permit permit ;
    private String idPermohonan ;
    private Permohonan permohonan ;
    private HakmilikPermohonan hakMilikPermohonan ;
    private String jenisPermit ;
    private String tempohSahLaku ;
    private PermitSahLaku permitSahLakuAsal ;
    private PermitSahLaku permitSahLaku1 ;
    private PermitSahLaku permitSahLaku2 ;
    private PermitSahLaku permitSahLaku3 ;
    private PermitItem permitItem ;
    private int temp,temp2 ;
    private String peruntukanTambahan ;
    private String tahunAkhir ;
    private String noPermit ;
    private String noFail ;
    private String noLesen ;
    private String noSiri ;
    private String idMohon1 ;
    private Date tarikhPermit ;
    private Date tarikhPermitMula ;
    private Date tarikhPermitTamat ;
    private String name ;
    private String jenisPengenalan ;
    private String noPengenalan ;
    private List<PermitSahLaku> sahLakuList ;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
     private static final Logger LOG = Logger.getLogger(CarianPermitActionBean.class);
    private int a,i;
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/pelupusan/carian_permit.jsp";
    @DefaultHandler
    public Resolution showForm()
    {
       return new ForwardResolution(DEFAULT_VIEW) ;
    }

    public Resolution carianSemula() {
        return showForm() ;
    }


    public Resolution cariLesen() {
      
        LOG.info("noPermit :" + noPermit) ;
        if (noPermit != null) {
            permit = plpService.getPermitbyNoPermit(noPermit);

            if (permit != null) {

                getContext().getRequest().setAttribute("status", Boolean.TRUE);
//            permitTemp = permit;
                idPermohonan = permit.getPermohonan().getIdPermohonan();
                permitItem = plpService.findPermitItemByIdPermit(permit.getIdPermit()) ;
                hakMilikPermohonan = plpService.findMohonHakmilik(permit.getPermohonan().getIdPermohonan());
                

//                temp = permit.getTempohSah();
//                tempohSahLaku = "" + temp;
//                peruntukanTambahan = permit.getPeruntukanTambahan();
//                temp2 = permit.getTahunAkhir();
//                tahunAkhir = "" + temp2;
             
                pihakList =  permit.getPihak() ;
                sahLakuList = plpService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                i = sahLakuList.size();
                if(sahLakuList.size() < 5){
                     for (int i = 0; i < sahLakuList.size(); i++) {
                      if(sahLakuList.size()==1){
                        permitSahLakuAsal = sahLakuList.get(0);
//                        idMohon1 = permitSahLaku1.getPermohonan().getIdPermohonan();
                      }
                      else if (sahLakuList.size() == 2) {
                        permitSahLakuAsal = sahLakuList.get(0);
                        permitSahLaku1 = sahLakuList.get(1);
                        idMohon1 = permitSahLaku1.getPermohonan().getIdPermohonan();
                    } else if (sahLakuList.size() == 3) {
                        permitSahLakuAsal = sahLakuList.get(0);
                        permitSahLaku1 = sahLakuList.get(1);
                        permitSahLaku2 = sahLakuList.get(2);
                        idMohon1 = permitSahLaku2.getPermohonan().getIdPermohonan();

                    } else if (sahLakuList.size() == 4) {
                        permitSahLakuAsal = sahLakuList.get(0);
                        permitSahLaku1 = sahLakuList.get(1);
                        permitSahLaku2 = sahLakuList.get(2);
                        permitSahLaku3 = sahLakuList.get(3);
                        idMohon1 = permitSahLaku3.getPermohonan().getIdPermohonan();
                    }
                }
                addSimpleMessage("Maklumat dijumpai");
                }
                else {
                    addSimpleError("Maklumat Pembaharuan Telah Lebih Daripada Tiga Kali") ;
                }
               
            } else {
                addSimpleError("Maklumat tidak dijumpai");
                getContext().getRequest().setAttribute("status", Boolean.FALSE);

            }
        }
        else {
            addSimpleError("Sila Masukkan No Permit") ;
        }

        return new ForwardResolution(DEFAULT_VIEW) ;
    }
   

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermitSahLaku getPermitSahLakuAsal() {
        return permitSahLakuAsal;
    }

    public void setPermitSahLakuAsal(PermitSahLaku permitSahLakuAsal) {
        this.permitSahLakuAsal = permitSahLakuAsal;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }

    public HakmilikPermohonan getHakMilikPermohonan() {
        return hakMilikPermohonan;
    }

    public void setHakMilikPermohonan(HakmilikPermohonan hakMilikPermohonan) {
        this.hakMilikPermohonan = hakMilikPermohonan;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNoSiri() {
        return noSiri;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public Permit getPermitTemp() {
        return permitTemp;
    }

    public String getJenisPermit() {
        return jenisPermit;
    }

    public void setJenisPermit(String jenisPermit) {
        this.jenisPermit = jenisPermit;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public String getTahunAkhir() {
        return tahunAkhir;
    }

    public void setTahunAkhir(String tahunAkhir) {
        this.tahunAkhir = tahunAkhir;
    }

    public String getTempohSahLaku() {
        return tempohSahLaku;
    }

    public void setTempohSahLaku(String tempohSahLaku) {
        this.tempohSahLaku = tempohSahLaku;
    }

    public void setPermitTemp(Permit permitTemp) {
        this.permitTemp = permitTemp;
    }

    public Pihak getPihakList() {
        return pihakList;
    }

    public void setPihakList(Pihak pihakList) {
        this.pihakList = pihakList;
    }

    public Date getTarikhPermit() {
        return tarikhPermit;
    }

    public void setTarikhPermit(Date tarikhPermit) {
        this.tarikhPermit = tarikhPermit;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public Date getTarikhPermitTamat() {
        return tarikhPermitTamat;
    }

    public void setTarikhPermitTamat(Date tarikhPermitTamat) {
        this.tarikhPermitTamat = tarikhPermitTamat;
    }





    public void setNoSiri(String noSiri) {
        this.noSiri = noSiri;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp2() {
        return temp2;
    }

    public void setTemp2(int temp2) {
        this.temp2 = temp2;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public List<PermitSahLaku> getRenewList() {
        return renewList;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public List<PermitSahLaku> getSahLakuList() {
        return sahLakuList;
    }

    public void setSahLakuList(List<PermitSahLaku> sahLakuList) {
        this.sahLakuList = sahLakuList;
    }

    public void setRenewList(List<PermitSahLaku> renewList) {
        this.renewList = renewList;
    }

    public PermitSahLaku getPermitSahLaku1() {
        return permitSahLaku1;
    }

    public void setPermitSahLaku1(PermitSahLaku permitSahLaku1) {
        this.permitSahLaku1 = permitSahLaku1;
    }

    public PermitSahLaku getPermitSahLaku2() {
        return permitSahLaku2;
    }

    public void setPermitSahLaku2(PermitSahLaku permitSahLaku2) {
        this.permitSahLaku2 = permitSahLaku2;
    }

    public PermitSahLaku getPermitSahLaku3() {
        return permitSahLaku3;
    }

    public void setPermitSahLaku3(PermitSahLaku permitSahLaku3) {
        this.permitSahLaku3 = permitSahLaku3;
    }

    public String getIdMohon1() {
        return idMohon1;
    }

    public void setIdMohon1(String idMohon1) {
        this.idMohon1 = idMohon1;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    

}

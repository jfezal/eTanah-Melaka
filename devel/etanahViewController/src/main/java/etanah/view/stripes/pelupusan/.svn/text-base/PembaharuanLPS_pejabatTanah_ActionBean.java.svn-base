package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermitDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;


@UrlBinding("/pelupusan/maklumat_lesen")
public class PembaharuanLPS_pejabatTanah_ActionBean extends AbleActionBean {
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
    private PermitItem permitItem ;
    private HakmilikPermohonan hakMilikPermohonan;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanTuntutanKos mohonTuntutKos ;
    private PermohonanTuntutanBayar mohonTuntutBayar1 ;
    private PermohonanTuntutanBayar mohonTuntutBayar2 ;
    private PermohonanTuntutanBayar mohonTuntutBayar3 ;
    private PermohonanTuntutanBayar mohonTuntutBayarAsal ;
    private Permit permitTemp ;
    private Permit permit ;
    private String idPermohonan ;
    private Permohonan permohonan ;
    private String jenisPermit ;
    private String tempohSahLaku ;
    private PermitSahLaku permitSahLaku1 ;
    private PermitSahLaku permitSahLaku2 ;
    private PermitSahLaku permitSahLaku3 ;
    private PermitSahLaku permitSahLakuAsal ;
    private int temp,temp2 ;
    private String peruntukanTambahan ;
//    private String tahunAkhir ;
    private String noPermit ;
    private String noFail ;
    private String noLesen ;
    private String noSiri ;
    private String idMohon1 ;
    private String idMohon2 ;
    private String idMohon3 ;
    private String noResitBayaran ;
    private String noResitBayaran1 ;
    private String noResitBayaran2 ;
    private String noResitBayaran3 ;
    private Long idKos ;
    private Date tarikhBayaran ;
    private Date tarikhBayaran1 ;
    private Date tarikhBayaran2 ;
    private Date tarikhBayaran3 ;
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
     private static final Logger LOG = Logger.getLogger(PembaharuanLPS_pejabatTanah_ActionBean.class);
    private int a,i;
    private PermitSahLaku noPermit1 ;
    @DefaultHandler
    public Resolution showForm()
    {
       return new JSP("pelupusan/pembaharuanLPS_pjbtTnh.jsp").addParameter("tab", "true") ;
    }


//    public Resolution cariLesen(){
//      getContext().getRequest().setAttribute("status", Boolean.TRUE);
//      permit = plpService.getPermitbyNoPermit(noPermit);
//
//      if(permit != null){
//          permitTemp = permit;
//          idPermohonan = permitTemp.getPermohonan().getIdPermohonan();
//          temp = permitTemp.getTempohSah();
//          tempohSahLaku = "" + temp ;
//          peruntukanTambahan = permitTemp.getPeruntukanTambahan();
//          temp2 = permitTemp.getTahunAkhir();
//          tahunAkhir = ""+temp2;
//          pihakList = permitTemp.getPermohonan().getSenaraiPihak();
//          sahLakuList = plpService.findPermitSahLakuByIdPermit(permit.getIdPermit()) ;
//          i = sahLakuList.size() ;
//          for(int i = 0 ; i < sahLakuList.size() ; i++){
//
//              if(sahLakuList.size() == 1){
//                  permitSahLaku1 = sahLakuList.get(0) ;
//                  idMohon1 = permitSahLaku1.getPermohonan().getIdPermohonan() ;
//              }
//              else if(sahLakuList.size() == 2){
//                  permitSahLaku1 = sahLakuList.get(0) ;
//                  permitSahLaku2 = sahLakuList.get(1) ;
//                  idMohon1 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
////                  idMohon2 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
//              }
//              else if(sahLakuList.size() == 3){
//                  permitSahLaku3= sahLakuList.get(2);
//                  permitSahLaku1 = sahLakuList.get(0) ;
//                  permitSahLaku2 = sahLakuList.get(1) ;
//                  idMohon1 = permitSahLaku3.getPermohonan().getIdPermohonan() ;
////                  idMohon2 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
////                  idMohon3 = permitSahLaku3.getPermohonan().getIdPermohonan() ;
//              }
//
//
//
//          }
//          addSimpleMessage("Maklumat dijumpai");}
////          permitSahLaku = plpService;
////      for(i=0;i<3;i++)
////          if(get(i).getNoSiri()==null)
////              permitSahLaku1 = permitSahLaku;
////              idPermohonan = permitSahLaku1.
//
//
//      else{
//
//          addSimpleMessage("Maklumat tidak di jumpai");}
////       if(renewList.size() > 3){
////        getContext().getRequest().setAttribute("data", Boolean.TRUE);
////        addSimpleError("Pembaharuan Lesen sudah ke tahap maksima.Sila Buat Permohonan Baru") ;
////    }
//      return new JSP("pelupusan/pembaharuanLPS_pjbtTnh.jsp").addParameter("tab", "true") ;
//    }

    public Resolution simpanUpdateLesen(){
         //simpan no.lesen dan pembaharuan LPS
         //getContext().getRequest().setAttribute("status", Boolean.TRUE);
         
         idMohon1 = getContext().getRequest().getParameter("idMohon1");
         
         PermitSahLaku temp = plpService.findPermitSahLakuByIdMohon(idMohon1) ;
         if(temp != null){
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
            InfoAudit ia = new InfoAudit() ;
            ia.setDimasukOleh(temp.getInfoAudit().getDimasukOleh()) ;
            ia.setTarikhMasuk(temp.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pg);
            ia.setTarikhKemaskini(new java.util.Date());
            temp.setInfoAudit(ia);

            if(noSiri == null){
            int maxVal = Integer.parseInt(noPermit1.getNoSiri()) + 1;               
            temp.setNoSiri(maxVal+"");}
//            String a = sdf1.format(tarikhPermit) ;
            temp.setTarikhPermit(new java.util.Date());
            temp.setTarikhPermitMula(tarikhPermitMula);
            temp.setTarikhPermitTamat(tarikhPermitTamat);
            //permitSahLakuDAO.saveOrUpdate(temp) ;
            plpService.simpanPermitSahLaku(temp);

         }
         addSimpleMessage("Maklumat Telah Disimpan") ;

         return new JSP("pelupusan/pembaharuanLPS_pjbtTnh.jsp").addParameter("tab", "true") ;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
    permohonan = permohonanDAO.findById(idPermohonan) ;


   permitSahLaku = plpService.findPermitSahLakuByIdMohon(idPermohonan) ;


      if(permitSahLaku != null){

          permitTemp = permitSahLaku.getPermit();
          permitItem = plpService.findPermitItemByIdPermit(permitTemp.getIdPermit());
          hakMilikPermohonan = plpService.findMohonHakmilik(permitTemp.getPermohonan().getIdPermohonan());

//          kodBandarPekanMukim = hakMilikPermohonan.getBandarPekanMukimBaru();
          noPermit1 = plpService.getMaxOfNoSiri();
          idPermohonan = permitTemp.getPermohonan().getIdPermohonan();
          mohonTuntutKos =plpService.findMohonTuntutKosByIdPermohonan(permitTemp.getPermohonan().getIdPermohonan());
                 // idKos = mohonTuntutKos.getIdKos();
                  mohonTuntutBayarAsal =plpService.findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                  if(mohonTuntutBayarAsal != null){
                   tarikhBayaran = mohonTuntutBayarAsal.getTarikhBayar();
                      noResitBayaran = mohonTuntutBayarAsal.getDokumenKewangan().getIdDokumenKewangan();
                  }
          pihakList =  permitTemp.getPihak();
          sahLakuList = plpService.findPermitSahLakuByIdPermit(permitTemp.getIdPermit()) ;
          i = sahLakuList.size() ;

          for(int i = 0 ; i < sahLakuList.size() ; i++){
              if(sahLakuList.size()==1){
                   permitSahLakuAsal = sahLakuList.get(0) ;
//                  idMohon1 = permitSahLakuAsal.getPermohonan().getIdPermohonan() ;
                  idMohon1 = permitTemp.getPermohonan().getIdPermohonan() ;
                  LOG.info(idMohon1) ;
                  
                  if(permitSahLaku.getNoSiri() != null && mohonTuntutBayar1 != null){
                      noSiri = permitSahLakuAsal.getNoSiri() ;
                      tarikhPermit = permitSahLakuAsal.getTarikhPermit() ;
                      tarikhPermitMula = permitSahLakuAsal.getTarikhPermitMula() ;
                      tarikhPermitTamat = permitSahLakuAsal.getTarikhPermitTamat() ;
                     
                      
              }
              }

              if(sahLakuList.size() == 2){
                   permitSahLakuAsal = sahLakuList.get(0) ;
                  permitSahLaku1 = sahLakuList.get(1) ;
                  idMohon1 = permitSahLaku1.getPermohonan().getIdPermohonan() ;
                   mohonTuntutKos =plpService.findMohonTuntutKosByIdPermohonan(idMohon1);
                  idKos = mohonTuntutKos.getIdKos();
                  mohonTuntutBayar1 =plpService.findMohonTuntutanBayarByIdTuntutKos(Long.valueOf(idKos));
                  
                  if(permitSahLaku1.getNoSiri() != null){
                      noSiri = permitSahLaku1.getNoSiri() ;
                      tarikhPermit = permitSahLaku1.getTarikhPermit() ;
                      tarikhPermitMula = permitSahLaku1.getTarikhPermitMula() ;
                      tarikhPermitTamat = permitSahLaku1.getTarikhPermitTamat() ;
                       tarikhBayaran1 = mohonTuntutBayar1.getTarikhBayar();
                      noResitBayaran1 = mohonTuntutBayar1.getDokumenKewangan().getIdDokumenKewangan();
                  }
              }
              else if(sahLakuList.size() == 3){
                   permitSahLakuAsal = sahLakuList.get(0) ;
                  permitSahLaku1 = sahLakuList.get(1) ;
                  permitSahLaku2 = sahLakuList.get(2) ;
                  idMohon1 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
                  mohonTuntutKos =plpService.findMohonTuntutKosByIdPermohonan(idMohon1);
//                  idKos = mohonTuntutKos.getIdKos();
                  mohonTuntutBayar2 =plpService.findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                  if(mohonTuntutBayar2 != null){
                        tarikhBayaran2 = mohonTuntutBayar2.getTarikhBayar();
                      noResitBayaran2 = mohonTuntutBayar2.getDokumenKewangan().getIdDokumenKewangan();
                  }
                  
                  if(permitSahLaku2.getNoSiri() != null){
                      noSiri = permitSahLaku2.getNoSiri() ;
                      tarikhPermit = permitSahLaku2.getTarikhPermit() ;
                      tarikhPermitMula = permitSahLaku2.getTarikhPermitMula() ;
                      tarikhPermitTamat = permitSahLaku2.getTarikhPermitTamat() ;
                    
                  }
                  //Data untuk renew yang pertama
                  if(permitSahLaku1.getNoSiri() != null){
                      mohonTuntutKos = plpService.findMohonTuntutKosByIdPermohonan(permitSahLaku1.getPermohonan().getIdPermohonan());
                      mohonTuntutBayar1 = plpService.findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                         tarikhBayaran1 = mohonTuntutBayar1.getTarikhBayar();
                      noResitBayaran1 = mohonTuntutBayar1.getDokumenKewangan().getIdDokumenKewangan();
                  }
//                  idMohon2 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
              }
              else if(sahLakuList.size() == 4){
                   permitSahLakuAsal = sahLakuList.get(0) ;
                  permitSahLaku1 = sahLakuList.get(1) ;
                  permitSahLaku2 = sahLakuList.get(2) ;
                  permitSahLaku3= sahLakuList.get(3);
                  idMohon1 = permitSahLaku3.getPermohonan().getIdPermohonan() ;
                  mohonTuntutKos =plpService.findMohonTuntutKosByIdPermohonan(idMohon1);
                  idKos = mohonTuntutKos.getIdKos();
                  mohonTuntutBayar3 =plpService.findMohonTuntutanBayarByIdTuntutKos(Long.valueOf(idKos));
                  if(mohonTuntutBayar3 != null){
                      tarikhBayaran3 = mohonTuntutBayar3.getTarikhBayar();
                      noResitBayaran3 = mohonTuntutBayar3.getDokumenKewangan().getIdDokumenKewangan();
                  }
                  if(permitSahLaku3.getNoSiri() != null){
                      noSiri = permitSahLaku3.getNoSiri() ;
                      tarikhPermit = permitSahLaku3.getTarikhPermit() ;
                      tarikhPermitMula = permitSahLaku3.getTarikhPermitMula() ;
                      tarikhPermitTamat = permitSahLaku3.getTarikhPermitTamat() ;
                  
                  }
                  
                   if(permitSahLaku1.getNoSiri() != null){
                      mohonTuntutKos = plpService.findMohonTuntutKosByIdPermohonan(permitSahLaku1.getPermohonan().getIdPermohonan());
                      mohonTuntutBayar1 = plpService.findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                         tarikhBayaran1 = mohonTuntutBayar1.getTarikhBayar();
                      noResitBayaran1 = mohonTuntutBayar1.getDokumenKewangan().getIdDokumenKewangan();
                  }
                   
                      if(permitSahLaku2.getNoSiri() != null){
                      mohonTuntutKos = plpService.findMohonTuntutKosByIdPermohonan(permitSahLaku2.getPermohonan().getIdPermohonan());
                      mohonTuntutBayar2 = plpService.findMohonTuntutanBayarByIdTuntutKos(mohonTuntutKos.getIdKos());
                         tarikhBayaran2 = mohonTuntutBayar2.getTarikhBayar();
                      noResitBayaran2 = mohonTuntutBayar2.getDokumenKewangan().getIdDokumenKewangan();
                  }
//                  idMohon2 = permitSahLaku2.getPermohonan().getIdPermohonan() ;
//                  idMohon3 = permitSahLaku3.getPermohonan().getIdPermohonan() ;
              }



          
   // permit = permitDAO.findById(noLesen) ;
    

//    a = renewList.size() ;

                 }
      }
    }

    public HakmilikPermohonan getHakMilikPermohonan() {
        return hakMilikPermohonan;
    }

    public void setHakMilikPermohonan(HakmilikPermohonan hakMilikPermohonan) {
        this.hakMilikPermohonan = hakMilikPermohonan;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public PermitSahLaku getPermitSahLakuAsal() {
        return permitSahLakuAsal;
    }

    public void setPermitSahLakuAsal(PermitSahLaku permitSahLakuAsal) {
        this.permitSahLakuAsal = permitSahLakuAsal;
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

    public String getNoResitBayaran() {
        return noResitBayaran;
    }

    public void setNoResitBayaran(String noResitBayaran) {
        this.noResitBayaran = noResitBayaran;
    }

    public Date getTarikhBayaran() {
        return tarikhBayaran;
    }

    public void setTarikhBayaran(Date tarikhBayaran) {
        this.tarikhBayaran = tarikhBayaran;
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

    public PermitSahLaku getNoPermit1() {
        return noPermit1;
    }

    public void setNoPermit1(PermitSahLaku noPermit1) {
        this.noPermit1 = noPermit1;
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

    public String getIdMohon2() {
        return idMohon2;
    }

    public void setIdMohon2(String idMohon2) {
        this.idMohon2 = idMohon2;
    }

    public String getIdMohon3() {
        return idMohon3;
    }

    public void setIdMohon3(String idMohon3) {
        this.idMohon3 = idMohon3;
    }

    public Long getIdKos() {
        return idKos;
    }

    public void setIdKos(Long idKos) {
        this.idKos = idKos;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayar1() {
        return mohonTuntutBayar1;
    }

    public void setMohonTuntutBayar1(PermohonanTuntutanBayar mohonTuntutBayar1) {
        this.mohonTuntutBayar1 = mohonTuntutBayar1;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayar2() {
        return mohonTuntutBayar2;
    }

    public void setMohonTuntutBayar2(PermohonanTuntutanBayar mohonTuntutBayar2) {
        this.mohonTuntutBayar2 = mohonTuntutBayar2;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayar3() {
        return mohonTuntutBayar3;
    }

    public void setMohonTuntutBayar3(PermohonanTuntutanBayar mohonTuntutBayar3) {
        this.mohonTuntutBayar3 = mohonTuntutBayar3;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayarAsal() {
        return mohonTuntutBayarAsal;
    }

    public void setMohonTuntutBayarAsal(PermohonanTuntutanBayar mohonTuntutBayarAsal) {
        this.mohonTuntutBayarAsal = mohonTuntutBayarAsal;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public String getNoResitBayaran1() {
        return noResitBayaran1;
    }

    public void setNoResitBayaran1(String noResitBayaran1) {
        this.noResitBayaran1 = noResitBayaran1;
    }

    public String getNoResitBayaran2() {
        return noResitBayaran2;
    }

    public void setNoResitBayaran2(String noResitBayaran2) {
        this.noResitBayaran2 = noResitBayaran2;
    }

    public String getNoResitBayaran3() {
        return noResitBayaran3;
    }

    public void setNoResitBayaran3(String noResitBayaran3) {
        this.noResitBayaran3 = noResitBayaran3;
    }

    public Date getTarikhBayaran1() {
        return tarikhBayaran1;
    }

    public void setTarikhBayaran1(Date tarikhBayaran1) {
        this.tarikhBayaran1 = tarikhBayaran1;
    }

    public Date getTarikhBayaran2() {
        return tarikhBayaran2;
    }

    public void setTarikhBayaran2(Date tarikhBayaran2) {
        this.tarikhBayaran2 = tarikhBayaran2;
    }

    public Date getTarikhBayaran3() {
        return tarikhBayaran3;
    }

    public void setTarikhBayaran3(Date tarikhBayaran3) {
        this.tarikhBayaran3 = tarikhBayaran3;
    }




}
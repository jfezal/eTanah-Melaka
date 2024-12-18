/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

/**
 *
 * @author Shazwan
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.LupusPermitDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.model.DokumenKewangan;
import etanah.model.HakmilikPermohonan;
import etanah.service.PelupusanService;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPermit;
import etanah.model.KodUOM;
import etanah.model.LupusPermit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitInfoPerbaharui;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.PermitStrukturDiluluskan;
import etanah.model.Permohonan;
import etanah.model.PermohonanHakmilikUrusanSebelum;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/borang4E")
public class Borang4EActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO ;
    @Inject
    PermitItemDAO permitItemDAO ;
    @Inject
    KodUOMDAO kodUOMDAO ;
    private PermohonanTuntutanBayar permohonanTuntutanBayar;
    private PermitSahLaku permitSahLaku;
    private String noPermit;
    private BigDecimal bayaran;
    private String bayaranPerkataan;
    private String noResit;
    private String tempohBerakhir;
    private String idPermohonan;
    private String luasKodUom;
    private String permitMula;
    private String permitAkhir;
    private Pengguna pguna;
    private LupusPermit lupusPermit;
    private Permohonan permohonan;
    private Permit permit;
    private PermitInfoPerbaharui permitInfoPerbaharui;
    private String peruntukanTambahan;
    private Pemohon pemohon ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private PermitStrukturDiluluskan permitStrukLulus;
    private HakmilikPermohonan mohonHakmilik;
    private PermohonanPermitItem ppi ;
    private PermitItem permitItem ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(KeputusanPermohonanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/borang4E.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/borang4E.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        mohonHakmilik = pelupusanService.findByIdPermohonan(idPermohonan);
        if (permit != null) {
            peruntukanTambahan = permit.getPeruntukanTambahan();
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
            permitStrukLulus = pelupusanService.findPermitStrukLuasByIdPermohonan(permit.getIdPermit());
            if(permitStrukLulus!=null){
                if(permitStrukLulus.getKodUomLuas()!=null)
                    luasKodUom = permitStrukLulus.getKodUomLuas().getKod();
            }
            permitInfoPerbaharui = pelupusanService.findPermitInfoPerbaharuiByIdPermit(String.valueOf(permit.getIdPermit()));
            if(permit.getTempohSah()==null||permit.getTempohSah()<=0)
                permit.setTempohSah(mohonHakmilik.getTempohPajakan());
            
            if(permit.getTempohSahUom()==null){
                KodUOM tahunUOM = new KodUOM();
                tahunUOM = kodUOMDAO.findById("T");
                permit.setTempohSahUom(tahunUOM);
            }
        }


        if (idPermohonan != null) {
            List<PermohonanTuntutanKos> senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            if (senaraiMohonTuntutKos != null) {
                for (PermohonanTuntutanKos ptk : senaraiMohonTuntutKos) {
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DISB4E")) {
                        permohonanTuntutanBayar = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                        if(permohonanTuntutanBayar!=null){
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                           
                            if(permit!=null&&permit.getTarikhpermitAkhir()==null){
                                Calendar c = Calendar.getInstance();
                                c.setTime(permohonanTuntutanBayar.getTarikhBayar());
                                c.add(Calendar.YEAR, mohonHakmilik.getTempohPajakan());
                                permitMula =  sdf.format(permohonanTuntutanBayar.getTarikhBayar());
                                permitAkhir = sdf.format(c.getTime());
                            }else if(permit==null){
                                Calendar c = Calendar.getInstance();
                                c.setTime(permohonanTuntutanBayar.getTarikhBayar());
                                c.add(Calendar.YEAR, mohonHakmilik.getTempohPajakan());
                                permitMula =  sdf.format(permohonanTuntutanBayar.getTarikhBayar());
                                permitAkhir = sdf.format(c.getTime());
                            }else if(permit!=null&&permit.getTarikhpermitAkhir()!=null){
                                permitAkhir = sdf.format(permit.getTarikhpermitAkhir());
                            }
                            if(permit!=null&&permit.getTarikhPermitMula()!=null){
                                permitMula =  sdf.format(permohonanTuntutanBayar.getTarikhBayar());
                            }
                           
                        }
                    }

                }
            }
        }

    }

    public Resolution simpanPermit() throws ParseException {


        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);

        if (Validation()) {
            return new JSP("pelupusan/borang4E.jsp").addParameter("tab", "true");
        }
        log.info("-------Permit SahLaku Saving--------------------");
        InfoAudit info = new InfoAudit() ;
        String keterangan = new String();
        String tarikhMula = new String();
        int tempohSah = 0;
        if(permit!=null){
            if(permit.getTempohSah()!=null)
                tempohSah = permit.getTempohSah();
            if(!StringUtils.isBlank(permit.getKeterangan()))
                keterangan = permit.getKeterangan();
            
        }
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        log.info("-------permit--------------------" + permit);
//        PermitSahLaku permitSahLakuTemp = null;
        if (permit != null) {          
            log.info("-------permitSahLaku--------------------" + permitSahLaku);
            permit.setKodCawangan(permohonan.getCawangan());
            InfoAudit ia = permit.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            log.info("-------permit is Null--------------------");

            log.info("-------Generating No permit---------");
           permit = new Permit();
//            Permit maxpermit = pelupusanService.getMaxOfNoPermit() ;
////            if (maxpermit == null) {
////                permit.setNoPermit("1");
////            } else {
//                int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
//                permit.setNoPermit(maxVal + "");
////            }

            permit.setPermohonan(permohonan);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permit.setInfoAudit(ia);
            permit.setKodCawangan(permohonan.getCawangan());
        }
         log.info("-------permit Not Null--------------------");
         
         if(permit.getNoPermit() == null){
               Permit maxpermit = pelupusanService.getMaxOfNoPermit() ;
//            if (maxpermit == null) {
//                permit.setNoPermit("1");
//            } else {
                int maxVal = Integer.parseInt(maxpermit.getNoPermit()) + 1;
                 permit.setNoPermit(maxVal + "");
         }
         if(permohonan.getKodUrusan().getKod().equals("PPTR")){
          KodJenisPermit kodJenis = kodJenisPermitDAO.findById("E") ;
                permit.setKodJenisPermit(kodJenis) ;
         }
                pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan) ;
                permit.setPihak(pemohon.getPihak()) ;
                hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
//                if(hakmilikPermohonan.getCatatan() != null){
//                permit.setKeterangan(hakmilikPermohonan.getCatatan()) ;
//                }
        permit.setPeruntukanTambahan(peruntukanTambahan);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        permit.setTarikhPermitMula(sdf.parse(permitMula));
        
        permit.setTempohSah(tempohSah);
        permit.setKeterangan(keterangan);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
                           
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(permitMula));
        c.add(Calendar.YEAR, tempohSah);
        permitAkhir = new String();
        permit.setTarikhpermitAkhir(c.getTime());
        permit = pelupusanService.saveOrUpdate(permit);

        

//        permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
//
//        InfoAudit info;
//        if (permitSahLakuTemp != null) {
//            log.info("-------permitSahLaku Not Null---------------::");
//            info = permitSahLakuTemp.getInfoAudit();
//            info.setDiKemaskiniOleh(peng);
//            info.setTarikhKemaskini(new java.util.Date());
//            permitSahLakuTemp.setInfoAudit(info);
//
//        } else {
//            log.info("-------permitSahLaku is Null--------::");
//            permitSahLakuTemp = new PermitSahLaku();
//            permitSahLakuTemp.setPermit(permit);
//            permitSahLakuTemp.setPermohonan(permohonan);
//            info = new InfoAudit();
//            info.setDimasukOleh(peng);
//            info.setTarikhMasuk(new java.util.Date());
//            permitSahLakuTemp.setInfoAudit(info);
//            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
//        }
//        if(permitSahLakuTemp.getNoSiri() == null){
//              PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri() ;
//         int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;
//         
//           permitSahLakuTemp.setNoSiri(maxSiri + "") ;
//        }     
//         permitSahLakuTemp.setTarikhPermit(new java.util.Date()) ;     
//        permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
//        permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
//        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);
//        
        /*
         * DISABLED 
         */
//         ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
////         log.info(ppi) ;
//        permitItem = pelupusanService.findPermitItemByIdPermit2(permit.getIdPermit()) ;
//        
//            if(permitItem == null){
//                 log.info("-------permitItem is Null--------------------");
//                permitItem = new PermitItem() ;
//            InfoAudit ia = new InfoAudit() ;
//            ia.setDimasukOleh(peng) ;
//            ia.setTarikhMasuk(new java.util.Date()) ;
//            permitItem.setInfoAudit(ia);
//            permitItem.setKodItemPermit(ppi.getKodItemPermit());
//            permitItem.setPermit(permit);
//            permitItem.setKodCawangan(permohonan.getCawangan());
//            pelupusanService.simpanPermitItem(permitItem);
//          
//            }
            /*
             * END
             */
//            else{
//              info = new InfoAudit() ;
//            info.setDimasukOleh(permitItem.getInfoAudit().getDimasukOleh()) ;
//            info.setTarikhMasuk(permitItem.getInfoAudit().getTarikhMasuk()) ;
//            info.setDiKemaskiniOleh(peng) ;
//            info.setTarikhKemaskini(new java.util.Date()) ;
//            permitItem.setInfoAudit(info) ;
//            permitItemDAO.saveOrUpdate(permitItem) ;
//
//            }
       
        
        Permit permitTemp = new Permit();
        permitTemp = pelupusanService.findPermitByIdPermohonan(idPermohonan);
                
        HakmilikPermohonan hm = new HakmilikPermohonan();
        hm = pelupusanService.findByIdPermohonan(idPermohonan);
        if(hm!=null){
            info = new InfoAudit();
            info = hm.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            hm.setLokasi(mohonHakmilik.getLokasi());
            hm.setInfoAudit(info);
        }else{
            hm = new HakmilikPermohonan();
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hm.setInfoAudit(info);
            hm.setLokasi(mohonHakmilik.getLokasi());
        }
        pelupusanService.simpanHakmilikPermohonan(hm);
        
        permitTemp = new Permit();
        permitTemp = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        if(permitTemp!=null){
           PermitInfoPerbaharui permitInfoPerbaharuiTemp = new PermitInfoPerbaharui();
           permitInfoPerbaharuiTemp = pelupusanService.findPermitInfoPerbaharuiByIdPermit(String.valueOf(permitTemp.getIdPermit()));
           InfoAudit ia = new InfoAudit();
           if(permitInfoPerbaharuiTemp!=null){
               ia = permitInfoPerbaharuiTemp.getInfoAudit();
               ia.setTarikhKemaskini(new java.util.Date());
               ia.setDiKemaskiniOleh(peng);
               permitInfoPerbaharuiTemp.setInfoAudit(ia);
           }else{
              permitInfoPerbaharuiTemp = new PermitInfoPerbaharui();
              ia = new InfoAudit();
              ia.setDimasukOleh(peng);
              ia.setTarikhMasuk(new java.util.Date());
              permitInfoPerbaharuiTemp.setInfoAudit(ia);
              permitInfoPerbaharuiTemp.setKodCawangan(permit.getKodCawangan());
              permitInfoPerbaharuiTemp.setPermit(permit);
           }
           if(permohonan.getKodUrusan().getKod().equals("PPTR") && permohonan.getCawangan().getKod().equals("04")){
               permitInfoPerbaharuiTemp.setBulanAkhirBayaran(permitInfoPerbaharui.getBulanAkhirBayaran());
               permitInfoPerbaharuiTemp.setHariAkhirBayaran(permitInfoPerbaharui.getHariAkhirBayaran());
           }
           pelupusanService.simpanPermitInfoPerbaharui(permitInfoPerbaharuiTemp);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah disimpan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/borang4E.jsp").addParameter("tab", "true");
    }

    public boolean Validation() {
        boolean flag = false;

        if ((permitMula == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Mula");
        } 
        if ((permitAkhir == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Tamat");
        } 
        return flag;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public String getBayaranPerkataan() {
        return bayaranPerkataan;
    }

    public void setBayaranPerkataan(String bayaranPerkataan) {
        this.bayaranPerkataan = bayaranPerkataan;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getTempohBerakhir() {
        return tempohBerakhir;
    }

    public void setTempohBerakhir(String tempohBerakhir) {
        this.tempohBerakhir = tempohBerakhir;
    }

    public LupusPermit getLupusPermit() {
        return lupusPermit;
    }

    public void setLupusPermit(LupusPermit lupusPermit) {
        this.lupusPermit = lupusPermit;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public PermitSahLaku getPermitSahLaku() {
        return permitSahLaku;
    }

    public void setPermitSahLaku(PermitSahLaku permitSahLaku) {
        this.permitSahLaku = permitSahLaku;
    }

    public PermohonanTuntutanBayar getPermohonanTuntutanBayar() {
        return permohonanTuntutanBayar;
    }

    public void setPermohonanTuntutanBayar(PermohonanTuntutanBayar permohonanTuntutanBayar) {
        this.permohonanTuntutanBayar = permohonanTuntutanBayar;
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

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public PermitStrukturDiluluskan getPermitStrukLulus() {
        return permitStrukLulus;
    }

    public void setPermitStrukLulus(PermitStrukturDiluluskan permitStrukLulus) {
        this.permitStrukLulus = permitStrukLulus;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public String getLuasKodUom() {
        return luasKodUom;
    }

    public void setLuasKodUom(String luasKodUom) {
        this.luasKodUom = luasKodUom;
    }

    public PermitInfoPerbaharui getPermitInfoPerbaharui() {
        return permitInfoPerbaharui;
    }

    public void setPermitInfoPerbaharui(PermitInfoPerbaharui permitInfoPerbaharui) {
        this.permitInfoPerbaharui = permitInfoPerbaharui;
    }

    public String getPermitAkhir() {
        return permitAkhir;
    }

    public void setPermitAkhir(String permitAkhir) {
        this.permitAkhir = permitAkhir;
    }

    public String getPermitMula() {
        return permitMula;
    }

    public void setPermitMula(String permitMula) {
        this.permitMula = permitMula;
    }
    
    
}


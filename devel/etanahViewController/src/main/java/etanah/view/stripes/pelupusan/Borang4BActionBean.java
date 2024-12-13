package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
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
import etanah.model.LupusPermit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Afham
 */

@UrlBinding("/pelupusan/penyediaan_borang4B")
public class Borang4BActionBean extends AbleActionBean {

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
    KodTuntutDAO kodTuntutDAO ;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO ;
    private PermohonanTuntutanBayar permohonanTuntutanBayarLPS ;
    private PermohonanTuntutanBayar permohonanTuntutanBayar;
    private PermohonanTuntutanBayar permohonanTuntutanBayar1 ;
    private PermohonanTuntutanBayar permohonanTuntutanBayar2 ;
    private PermitSahLaku permitSahLaku;
    private String noPermit;
    private BigDecimal bayaran;
    private String bayaranPerkataan;
    private String noResit;
    private String tempohBerakhir;
    private String idPermohonan;
    private Pengguna pguna;
    private Permohonan permohonan;
    private Permit permit;
    private String peruntukanTambahan;
    private Pemohon pemohon ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private PermohonanBahanBatuan permohonanBahanBatuan ;
    private PermitItem permitItem ;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(Borang4BActionBean.class);
    private List<PermohonanTuntutanKos> senaraiMohonTuntutKos ;
    private boolean edit ;
    private boolean view ;
    
    
    @DefaultHandler
    public Resolution showForm() {
       view = Boolean.TRUE ;
        return new JSP("pelupusan/Borang_4B.jsp").addParameter("tab", "true");
    }
    
     public Resolution viewForm() {
        view = Boolean.FALSE ;
        return new JSP("pelupusan/Borang_4B.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);

        if (permit != null) {
            peruntukanTambahan = permit.getPeruntukanTambahan();
            permitSahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
        }


        if (idPermohonan != null) {
             senaraiMohonTuntutKos = strService.findMohonTuntutKos(idPermohonan);
            if (senaraiMohonTuntutKos != null) {
                for (PermohonanTuntutanKos ptk : senaraiMohonTuntutKos) {
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DIS4C")) {
                        permohonanTuntutanBayar = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                    }
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DISWC")) {
                        permohonanTuntutanBayar1 = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                    }
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DISKD")) {
                        permohonanTuntutanBayar2 = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                    }
                    if (ptk.getKodTuntut() != null && ptk.getKodTuntut().getKod().equalsIgnoreCase("DIS4B")) {
                        permohonanTuntutanBayarLPS = pelupusanService.findMohonTuntutBayar(ptk.getIdKos());
                    }

                }
            }
        }

    }

    public Resolution simpanPermit() {


        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);

        if (Validation()) {
             return new JSP("pelupusan/Borang_4B.jsp").addParameter("tab", "true");
        }
        log.info("-------Permit SahLaku Saving--------------------");

        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
        log.info("-------permit--------------------" + permit);
        PermitSahLaku permitSahLakuTemp = null;
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
          KodJenisPermit kodJenis = kodJenisPermitDAO.findById("B") ;
               
                permit.setKodJenisPermit(kodJenis) ;
                pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan) ;
                permit.setPihak(pemohon.getPihak()) ;

        permit.setPeruntukanTambahan(peruntukanTambahan);
        permit = pelupusanService.saveOrUpdate(permit);



        permitSahLakuTemp = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

        InfoAudit info;
        if (permitSahLakuTemp != null) {
            log.info("-------permitSahLaku Not Null---------------::");
            info = permitSahLakuTemp.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);

        } else {
            log.info("-------permitSahLaku is Null--------::");
            permitSahLakuTemp = new PermitSahLaku();
            permitSahLakuTemp.setPermit(permit);
            permitSahLakuTemp.setPermohonan(permohonan);
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permitSahLakuTemp.setInfoAudit(info);
            permitSahLakuTemp.setKodCawangan(permohonan.getCawangan());
        }
        if(permitSahLakuTemp.getNoSiri() == null){
              PermitSahLaku pTemp = pelupusanService.getMaxOfNoSiri() ;
         int maxSiri = Integer.parseInt(pTemp.getNoSiri()) + 1;
         
           permitSahLakuTemp.setNoSiri(maxSiri + "") ;
        }     
         permitSahLakuTemp.setTarikhPermit(new java.util.Date()) ;     
        permitSahLakuTemp.setTarikhPermitMula(permitSahLaku.getTarikhPermitMula());
        permitSahLakuTemp.setTarikhPermitTamat(permitSahLaku.getTarikhPermitTamat());
        pelupusanService.simpanPermitSahLaku(permitSahLakuTemp);
       
         
//         log.info(ppi) ;
        permitItem = pelupusanService.findPermitItemByIdPermit2(permit.getIdPermit()) ;
        permohonanBahanBatuan = pelupusanService.findPermohonanBahanBatuanByIdMohon(idPermohonan);
            if(permitItem == null){
                 log.info("-------permitItem is Null--------------------");
                permitItem = new PermitItem() ;
            InfoAudit ia = new InfoAudit() ;
            ia.setDimasukOleh(peng) ;
            ia.setTarikhMasuk(new java.util.Date()) ;
            permitItem.setInfoAudit(ia);
            permitItem.setKodItemPermit(permohonanBahanBatuan.getJenisBahanBatu());
            permitItem.setPermit(permit);
            permitItem.setKodCawangan(permohonan.getCawangan());
            pelupusanService.simpanPermitItem(permitItem);
          
            }
            
            PermohonanTuntutan mohonTuntut = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(idPermohonan, "DIS4B");
            if(mohonTuntut == null){
                mohonTuntut = new PermohonanTuntutan() ;
                mohonTuntut.setCawangan(permohonan.getCawangan());
                InfoAudit ia = new InfoAudit() ;
                ia.setDimasukOleh(peng) ;
                ia.setTarikhMasuk(new java.util.Date()) ;
                mohonTuntut.setInfoAudit(ia);
                mohonTuntut.setPermohonan(permohonan);
                mohonTuntut.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DIS4B"));
                mohonTuntut.setTarikhTuntutan(new java.util.Date());
                
                pelupusanService.simpanPermohonanTuntutan(mohonTuntut);
            }
        

        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("pelupusan/Borang_4B.jsp").addParameter("tab", "true");
    }

    public boolean Validation() {
        boolean flag = false;

        if ((permitSahLaku == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Mula");
        } else if ((permitSahLaku != null) && (permitSahLaku.getTarikhPermitMula() == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Mula");
        }
        if ((permitSahLaku == null)) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Permit Tamat");
        } else if ((permitSahLaku != null) && (permitSahLaku.getTarikhPermitTamat() == null)) {
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

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public PermohonanTuntutanBayar getPermohonanTuntutanBayar1() {
        return permohonanTuntutanBayar1;
    }

    public void setPermohonanTuntutanBayar1(PermohonanTuntutanBayar permohonanTuntutanBayar1) {
        this.permohonanTuntutanBayar1 = permohonanTuntutanBayar1;
    }

    public PermohonanTuntutanBayar getPermohonanTuntutanBayar2() {
        return permohonanTuntutanBayar2;
    }

    public void setPermohonanTuntutanBayar2(PermohonanTuntutanBayar permohonanTuntutanBayar2) {
        this.permohonanTuntutanBayar2 = permohonanTuntutanBayar2;
    }

    public PermohonanTuntutanBayar getPermohonanTuntutanBayarLPS() {
        return permohonanTuntutanBayarLPS;
    }

    public void setPermohonanTuntutanBayarLPS(PermohonanTuntutanBayar permohonanTuntutanBayarLPS) {
        this.permohonanTuntutanBayarLPS = permohonanTuntutanBayarLPS;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    

}



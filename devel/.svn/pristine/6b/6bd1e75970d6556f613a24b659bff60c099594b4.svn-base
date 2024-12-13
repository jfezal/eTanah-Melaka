/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.AkaunDAO;
import etanah.dao.AnsuranDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikDAO ;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Ansuran;
import etanah.model.CaraBayaran;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.ImejLaporan;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.LaporanTanah;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorNoResit;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.common.LelongService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import etanah.workflow.WorkFlowService;
import java.io.File;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.PembangunanService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Akmal
 */

@UrlBinding("/pembangunan/rayuan_ansuran")
public class RayuanAnsuranActionBean extends AbleActionBean{
    private static Logger logger = Logger.getLogger(RayuanAnsuranActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    AnsuranDAO ansuranDAO ;
    @Inject
    private GeneratorNoResit noResitGenerator;
    @Inject
    AkaunDAO akaunDAO ;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO ;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DokumenDAO dokumenDAO ;
    @Inject
    FolderDokumenDAO folderDokumenDAO ;
    @Inject
    KandunganFolderDAO kandunganFolderDAO ;
    @Inject
    KodTransaksiDAO kodTransaksiDAO ;
     @Inject
    private KodStatusTransaksiKewanganDAO kodStatusDAO; 
    @Inject
    TransaksiDAO transaksiDAO ;
    @Inject
    CaraBayaranDAO caraBayaranDAO ;
    @Inject 
    private static final Logger LOG = Logger.getLogger(RayuanAnsuranActionBean.class);
      @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    LelongService lelongService;
    @Inject
    PembangunanService devService;
    
    private Permohonan permohonan; 
    private PermohonanTuntutanBayar mohonTuntutBayar;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList; 
    private Ansuran ansuran ;
    private List<Ansuran> ansuranList ;
    private String idPermohonan;
    private String resitNo;
    private Pengguna pengguna ;  
    private Pemohon pemohon ;
    private Pihak pihak ;
    private int bilBulan;
    private Date tarikhAkhirBayaran1;
    private Date tarikhAkhirBayaran2;
    private Date tarikhAkhirBayaran3;
    private Date tarikhAkhirBayaran4;
    private Date tarikhAkhirBayaran5;
    private Date tarikhAkhirBayaran6;
    private BigDecimal bulan1;
    private BigDecimal bulan2;
    private BigDecimal bulan3;
    private BigDecimal bulan4;
    private BigDecimal bulan5;
    private BigDecimal bulan6;
    private BigDecimal amaun1;
    private BigDecimal amaun2;
    private BigDecimal amaun3;
    private BigDecimal amaun4;
    private BigDecimal amaun5;
    private BigDecimal amaun6;

    private BigDecimal premium;
    private BigDecimal hasilTahunPertama;
    private BigDecimal bayaranPelan;
    private BigDecimal sumbanganSaliran;
    private BigDecimal bayaranSemantara;
    private BigDecimal bayaranKekal;
    private BigDecimal jumlah;
    private BigDecimal jumlahKeseluruhan;
    private BigDecimal jumlah2;
    private BigDecimal jumlah3;
    private BigDecimal jumlah4;
    private BigDecimal premiumBaru;
    private BigDecimal pendaftaran;
    
    private PermohonanTuntutanKos permohonanTuntutanKos ;
    private PermohonanTuntutan permohonanTuntutan ;
    private Date tarikhDibayar = new Date();
    private List<PermohonanTuntutanBayar> listBayaran ;
    private Akaun akaun ;
    IWorkflowContext ctxW = null;
    private boolean ptd;
    private boolean isAnsuranPremium;
    private boolean view;
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private BigDecimal jumlahCaj ;
    private BigDecimal jumCaraBayar ;
    private String stageId ;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikh;
    
    @DefaultHandler
    public Resolution showForm() {
        ptd = true ;
        isAnsuranPremium = true;
    return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormView() {
        view = true;     
        ptd = false;
        isAnsuranPremium = false;
    return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormPTD() {
        ptd = true ;
        isAnsuranPremium = false;
    return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }

    public Resolution viewAnsuran() {
        ptd = false ;
        return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }
    
    public Resolution tarikhJana() {
        return new JSP("pembangunan/rayuan/tarikh_jana.jsp").addParameter("tab", "true");
    }


    public Resolution viewNotis5a() {
         premiumBaru = BigDecimal.ZERO;
//         premiumBaru = permohonan.getNilaiDipohon();
         List<PermohonanTuntutanKos> listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
            listtuntutankos = devService.findTuntutByIdMohon(permohonan.getIdPermohonan());
        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                PermohonanTuntutanKos permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        premiumBaru = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
        }
        if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")){
                jumlah2 = hasilTahunPertama.add(premiumBaru).add(sumbanganSaliran).add(bayaranPelan).add(bayaranKekal).add(bayaranSemantara);
         }else if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSN") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKKT") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKSN")){
             jumlah2 = pendaftaran.add(premiumBaru).add(sumbanganSaliran);
         }

        return new JSP("pembangunan/rayuan/rayuan_notis5A.jsp").addParameter("tab", "true");
    }

    public Resolution viewRNSNotis5a() {
         premiumBaru = BigDecimal.ZERO;

         List<PermohonanTuntutanKos> listtuntutankos1 = new ArrayList<PermohonanTuntutanKos>();
            listtuntutankos1 = devService.findTuntutByIdMohon(permohonan.getIdPermohonan());
        if(!(listtuntutankos1.isEmpty())){
            for(int i=0; i < listtuntutankos1.size(); i++){
                PermohonanTuntutanKos permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos1.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        premiumBaru = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
        }         
         jumlah2 = hasilTahunPertama.add(premiumBaru).add(sumbanganSaliran).add(bayaranPelan).add(bayaranKekal).add(bayaranSemantara);
        return new JSP("pembangunan/rayuan/rayuan_notis5A.jsp").addParameter("tab", "true");
    }


    public Resolution viewNotis5aLama() {          
      if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")){
                jumlah3 = hasilTahunPertama.add(premium).add(sumbanganSaliran).add(bayaranPelan).add(bayaranKekal).add(bayaranSemantara);
         }else if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSN") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKKT") ||
                  permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKSN")){
             jumlah3 = pendaftaran.add(premium).add(sumbanganSaliran);
         }
        return new JSP("pembangunan/rayuan/rayuan_notis5ALama.jsp").addParameter("tab", "true");
    }
    
    public Resolution bayaran() {
         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        if(permohonan != null){
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04") ;
            akaun = pelupusanService.findByIdMohon(idPermohonan);
            if(permohonanTuntutanKos != null){
                jumlahCaj = akaun.getBaki() ;
            }
        }
         if (senaraiCaraBayaran == null) {
            senaraiCaraBayaran = new ArrayList<CaraBayaran>();
        }

        if (senaraiCaraBayaran.size() == 0) {
            for (int i = 0; i < 5; i++) {
                CaraBayaran cr = new CaraBayaran();
                cr.setAmaun(BigDecimal.ZERO); // reset amount
                senaraiCaraBayaran.add(cr);
            }
        }
    return new JSP("pembangunan/rayuan/bayaran_ansuran.jsp").addParameter("tab", "true");
    }
    
    public Resolution ulasan() {
    return new JSP("pelupusan/rayuan/ulasan_rayuan.jsp").addParameter("tab", "true");
    }
        
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        hasilTahunPertama = BigDecimal.ZERO;
        premium = BigDecimal.ZERO;
        sumbanganSaliran = BigDecimal.ZERO;
        bayaranPelan = BigDecimal.ZERO;
        bayaranKekal = BigDecimal.ZERO;
        bayaranSemantara = BigDecimal.ZERO;
        pendaftaran = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        jumlah2 = BigDecimal.ZERO;
        jumlah3 = BigDecimal.ZERO;        
        jumlahKeseluruhan = BigDecimal.ZERO;
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        if(permohonan.getPermohonanSebelum() == null){
            addSimpleError("Sila masukkan permohonan sebelum terlebih dahulu di bahagian rayuan");
        }
        if(permohonan != null){            
            if(permohonan.getPermohonanSebelum() != null){                
                permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04") ;
                if(permohonanTuntutanKos != null)                 
                listBayaran = pelupusanService.findPermohonanTuntutanBayarByIdKos(permohonanTuntutanKos.getIdKos());         
            }

            ansuranList = devService.findAnsuranListByIdPermohonan(idPermohonan);

            List<PermohonanTuntutanKos> listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
            listtuntutankos = devService.findTuntutByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                PermohonanTuntutanKos permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        hasilTahunPertama = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        sumbanganSaliran = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV03")){
                        bayaranPelan = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV02")){
                        bayaranKekal = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV01")){
                        bayaranSemantara = permohonantuntutkos.getAmaunTuntutan();
                    } else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV08")){
                        pendaftaran = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
         
        }

             if(!ansuranList.isEmpty()){
                 bilBulan = ansuranList.size();
                 BigDecimal count = new BigDecimal(bilBulan);
                 for(int i = 0 ; i < ansuranList.size()&& bilBulan!=0 ; i++){
                     ansuran = ansuranList.get(i);                     
                     if(i == 0){
                         tarikhAkhirBayaran1 = ansuran.getTarikhAkhirBayaran(); 
                         bulan1 = ansuran.getAmaun();
                         if(bulan1==null)
                            bulan1 = premium.divide(count,BigDecimal.ROUND_FLOOR);
//                         jumlahKeseluruhan = jumlahKeseluruhan.add(bulan1);
                     }
                     else if(i == 1){
                         tarikhAkhirBayaran2 = ansuran.getTarikhAkhirBayaran(); 
                         bulan2 = ansuran.getAmaun();
                         if(bulan2==null)
                            bulan2 = premium.divide(count,BigDecimal.ROUND_FLOOR);
                        jumlahKeseluruhan = jumlahKeseluruhan.add(bulan2);
                     }
                     else if(i == 2){
                         tarikhAkhirBayaran3 = ansuran.getTarikhAkhirBayaran(); 
                         bulan3 = ansuran.getAmaun();
                         if(bulan3==null)
                             bulan3 = premium.divide(count,BigDecimal.ROUND_FLOOR);                         
                         jumlahKeseluruhan = jumlahKeseluruhan.add(bulan3);
                     }
                      else if(i == 3){
                         tarikhAkhirBayaran4 = ansuran.getTarikhAkhirBayaran(); 
                         bulan4 = ansuran.getAmaun();
                         if(bulan4==null)
                             bulan4 = premium.divide(count,BigDecimal.ROUND_FLOOR);                         
                         jumlahKeseluruhan = jumlahKeseluruhan.add(bulan4);
                     }
                      else if(i == 4){
                         tarikhAkhirBayaran5 = ansuran.getTarikhAkhirBayaran(); 
                         bulan5 = ansuran.getAmaun() ;
                         if(bulan5==null)
                             bulan5 = premium.divide(count,BigDecimal.ROUND_FLOOR);                         
                         jumlahKeseluruhan = jumlahKeseluruhan.add(bulan5);
                     }
                      else if(i == 5){
                         tarikhAkhirBayaran6 = ansuran.getTarikhAkhirBayaran(); 
                         bulan6 = ansuran.getAmaun() ;
                         if(bulan6==null)
                             bulan6 = premium.divide(count,BigDecimal.ROUND_FLOOR);
                         jumlahKeseluruhan = jumlahKeseluruhan.add(bulan6);
                     }
                 }
             }
                if(bulan1!=null){
                     if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")){
                        jumlah = hasilTahunPertama.add(bulan1).add(sumbanganSaliran).add(bayaranPelan).add(bayaranKekal).add(bayaranSemantara);
                     }else if(permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS") ||
                              permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSN") ||
                              permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKKT") ||
                              permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKSN")){
                         jumlah = pendaftaran.add(bulan1).add(sumbanganSaliran);
                     }
                 }

            jumlahKeseluruhan = jumlahKeseluruhan.add(jumlah);
         LOG.debug("---------Jumlah-----------:"+jumlah);
         LOG.debug("---------jumlahKeseluruhan-----------:"+jumlahKeseluruhan);
         
         if(permohonan.getTarikhMula() != null){
             tarikh = sdf.format(permohonan.getTarikhMula());
         }
        }
    }
    
    public Resolution simpanAnsuran() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String bulan = getContext().getRequest().getParameter("bilBulan") ;
//        logger.info("Bape bulan? :" + bulan);
//        int size = Integer.parseInt(bulan);
//            String[] tname = {"permohonan"} ;
//            Object[] tvalue = {permohonan} ;
//            ansuranList = ansuranDAO.findByEqualCriterias(tname, tvalue, null) ;
//            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM") ;
//            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DIS5A");
//            permohonanTuntutan = devService.findMohonTuntutanByIdAsal(permohonan.getPermohonanSebelum().getIdPermohonan());
//            if(!ansuranList.isEmpty()){
//                for(int i = 0 ; i < ansuranList.size() ; i++){
//                    ansuran = ansuranList.get(i);
//                    pelupusanService.deleteAnsuran(ansuran);                   
//                }
//                ansuranList = ansuranDAO.findByEqualCriterias(tname, tvalue, null) ;
//            }

            ansuranList = devService.findAnsuranListByIdPermohonan(idPermohonan);
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04") ;
            BigDecimal total = new BigDecimal(0) ;
            if(bulan1 != null){
                total = total.add(bulan1);               

            }
             if(bulan2 != null){
                    total = total.add(bulan2);
                }
                if(bulan3 != null){
                    total = total.add(bulan3);
                }
                if(bulan4 != null){
                    total = total.add(bulan4);
                }
                if(bulan5 != null){
                    total = total.add(bulan5);
                }
                if(bulan6 != null){
                    total = total.add(bulan6);
                }

//             if(total.equals(permohonanTuntutanKos.getAmaunTuntutan())){
                  InfoAudit ia = new InfoAudit();
                  permohonanTuntutanKos.setAmaunSebenar(permohonanTuntutanKos.getAmaunTuntutan());
                  pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
            if(!ansuranList.isEmpty()){
                for(int i = 0 ; i < ansuranList.size() ; i++){
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                   if(i == 0){
                       ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran1);
                       ansuran.setAmaun(bulan1);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                   else if(i == 1){
                       ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran2);
                       ansuran.setAmaun(bulan2);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 2){
                      ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran3);
                       ansuran.setAmaun(bulan3);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 3){
                       ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran4);
                       ansuran.setAmaun(bulan4);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 4){
                      ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran5);
                       ansuran.setAmaun(bulan5);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 5){
                      ansuran = ansuranList.get(i) ;   
                       ia = ansuran.getInfoAudit() ;
                       ansuran.setInfoAudit(ia);
                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran6);
                       ansuran.setAmaun(bulan6);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                }
                }
          rehydrate() ;
          addSimpleMessage("Maklumat telah disimpan");  
          return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }

     public Resolution simpanBulan() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String bulan = getContext().getRequest().getParameter("bilBulan") ;
        logger.info("Bape bulan? :" + bulan);
        int size = Integer.parseInt(bulan);
            String[] tname = {"permohonan"} ;
            Object[] tvalue = {permohonan} ;
            ansuranList = ansuranDAO.findByEqualCriterias(tname, tvalue, null) ;
//            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM") ;
//            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DIS5A");
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04") ;
            permohonanTuntutan = devService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV5A");
            if(!ansuranList.isEmpty()){
                for(int i = 0 ; i < ansuranList.size() ; i++){
                    ansuran = ansuranList.get(i);
                    pelupusanService.deleteAnsuran(ansuran);                   
                }
                ansuranList = ansuranDAO.findByEqualCriterias(tname, tvalue, null) ;
            }
            InfoAudit ia = new InfoAudit() ;
            if(ansuranList.isEmpty()){
                for(int i = 0 ; i < size ; i++){
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                   if(i == 0){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran1);
//                       ansuran.setAmaun(bulan1);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                   else if(i == 1){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran2);
//                       ansuran.setAmaun(bulan2);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 2){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran3);
//                       ansuran.setAmaun(bulan3);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 3){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran4);
//                       ansuran.setAmaun(bulan4);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 4){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran5);
//                       ansuran.setAmaun(bulan5);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                     else if(i == 5){
                       ansuran = new Ansuran() ;                  
                       ansuran.setInfoAudit(ia);
                       ansuran.setPermohonan(permohonan);
                       ansuran.setCawangan(permohonan.getCawangan());
//                       ansuran.setTarikhAkhirBayaran(tarikhAkhirBayaran6);
//                       ansuran.setAmaun(bulan6);
                       ansuran.setPermohonanTuntutan(permohonanTuntutan);
                       pelupusanService.simpanAnsuran(ansuran);
                   }
                }
                }
          
           rehydrate() ;
          addSimpleMessage("Maklumat telah disimpan");  
          return new JSP("pembangunan/rayuan/rayuan_ansuran_bayaran.jsp").addParameter("tab", "true");
    }
    
    
    public Resolution simpanBayaran() {
        //permohonanTuntutanKos.amaunTuntutan
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
      
        BigDecimal payTotal = new BigDecimal(0);
        BigDecimal balanceTotal = new BigDecimal(0);
//        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM") ;
        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04") ;
        akaun = pelupusanService.findByIdMohon(idPermohonan) ;
         KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String documentPath = conf.getProperty("document.path");
         Date now = new Date();
        int year = now.getYear() + 1900;
        LOG.info("DOC PATH :" + documentPath);
        LOG.info("Amaun Bayar" + jumCaraBayar);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        if(jumCaraBayar!=null)
            payTotal = payTotal.add(jumCaraBayar);
       
        if(payTotal.doubleValue() <= akaun.getAmaunMatang().doubleValue()){            
            balanceTotal = permohonanTuntutanKos.getAmaunTuntutan().subtract(payTotal);
            akaun.setBaki(balanceTotal);
            permohonanTuntutanKos.setAmaunTuntutan(balanceTotal);
            mohonTuntutBayar = new PermohonanTuntutanBayar();
            InfoAudit info = permohonanTuntutanKos.getInfoAudit();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            resitNo = noResitGenerator.generate(ctx.getKodNegeri(), permohonanTuntutanKos.getCawangan(), pengguna);
            
            LOG.info("START KEW DOK");
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(resitNo);
            dk.setCawangan(permohonanTuntutanKos.getCawangan());
            dk.setAmaunBayaran(jumCaraBayar);
            dk.setKodDokumen(kodDokumenDAO.findById("ANS"));
            dk.setInfoAudit(info);
            saveCaraBayaran(permohonan.getCawangan(), dk, info);
            dokumenKewanganDAO.save(dk);
            LOG.info("END KEW DOK");
             LOG.info("START KEW_TRANS");
            Transaksi trans = new Transaksi();
            trans.setKodTransaksi(kodTransaksiDAO.findById(permohonanTuntutanKos.getKodTransaksi().getKod()));
//            trans.setAkaunDebit(akTerima);
            trans.setAmaun(jumCaraBayar);
            trans.setUntukTahun(year);
            trans.setPermohonan(permohonanTuntutanKos.getPermohonan());
            trans.setDokumenKewangan(dk);
            trans.setPerihal(permohonan.getKodUrusan().getNama());
            trans.setStatus(kodStatusDAO.findById('T'));
            trans.setKuantiti(1);
            trans.setInfoAudit(info);
            trans.setCawangan(permohonan.getCawangan());
            transaksiDAO.save(trans);
            LOG.info("END KEW_TRANS: " + trans.getIdTransaksi());
            LOG.info("START TUNTUT BAYAR");
            mohonTuntutBayar.setPermohonanTuntutanKos(permohonanTuntutanKos);
            mohonTuntutBayar.setAmaun(payTotal);
            mohonTuntutBayar.setDokumenKewangan(dk);  
            mohonTuntutBayar.setInfoAudit(info);
            mohonTuntutBayar.setTarikhBayar(new java.util.Date());
             LOG.info("END TUNTUT BAYAR");
            akaun.setInfoAudit(info);
            akaunDAO.saveOrUpdate(akaun);
            pelupusanService.save(mohonTuntutBayar);
            pelupusanService.simpanPermohonanTuntutanKos(permohonanTuntutanKos);
            
            
         long idFolder = permohonan.getFolderDokumen().getFolderId();
        Dokumen resit = new Dokumen();
        resit.setFormat("application/pdf");
        resit.setInfoAudit(info);
        resit.setKlasifikasi(klasifikasiAm);
        KodDokumen kodResit = kodDokumenDAO.findById("ANS");
        resit.setKodDokumen(kodResit);
        resit.setNoVersi("1.0");
        LOG.info(kodResit.getNama());
        resit.setTajuk("Resit Bayaran Ansuran");
        resit = dokumenDAO.saveOrUpdate(resit);
        tx.commit();
        LOG.info("RESIT IDDOCUMENT: " + resit.getIdDokumen());
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
        String path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(resit.getIdDokumen());
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            reportUtil.generateReport("HSLResitUrusanTanah_MLK.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, pengguna);
        } else {
            reportUtil.generateReport("HSLResitUrusanTanah.rdf",
                    new String[]{"p_id_kew_dok"},
                    new String[]{resitNo},
                    path + path2, pengguna);
        }
        resit.setNamaFizikal(reportUtil.getDMSPath());
        getContext().getRequest().setAttribute("resitId", String.valueOf(resit.getIdDokumen()));
        dokumenDAO.update(resit);

        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(resit);
        kf.setFolder(folderDokumenDAO.findById(idFolder));
        kf.setInfoAudit(info);
        kf.setCatatan("");
        kandunganFolderDAO.save(kf);
        
         BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, stageId);
        info = fasaPermohonan.getInfoAudit();
        info.setTarikhKemaskini(new Date());
        info.setDiKemaskiniOleh(pengguna);
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(info);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setTarikhHantar(new java.util.Date());
        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        

            addSimpleMessage("Maklumat telah disimpan");  
        }
        else{
            addSimpleError("Jumlah bayaran melebihi amaun tuntutan");
        }
           rehydrate() ;
           jumCaraBayar = new BigDecimal(0);
//           BigDecimal baki0 = new BigDecimal(0);
        if (akaun.getBaki().doubleValue() == 0.00) {
               try {
            ctxW = WorkFlowService.authenticate(pengguna.getIdPengguna());
            } catch (Exception e) {
            LOG.error("error ::" + e.getMessage());
            }
            StringBuilder sb = new StringBuilder();
            try {
                List<Map<String, String>> list = getPermohonanWithTaskID(pengguna);
                for (Map<String, String> map : list) {
                    String taskID = map.get("taskId");
                    String idPermohonan = map.get("idPermohonan");
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(idPermohonan);
                    LOG.info("TaskID ::" + taskID);
                    LOG.info("idPermohonan ::" + idPermohonan);
                    //WorkFlowService.updateTaskOutcome(taskID, ctxW, "COMPLETE");
                     FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanBayaran(idPermohonan, "terimabayaran");
                      if (fasaPermohonan != null) {
                        //InfoAudit dt = fasaPermohonan.getInfoAudit();
                        Date userDate = fasaPermohonan.getInfoAudit().getTarikhMasuk();
                        LOG.debug("----User date from mohon_fasa----:" + userDate);
                        LOG.info("idPermohonan ::" + fasaPermohonan.getPermohonan().getIdPermohonan());
                        LOG.info("idAliran ::" + fasaPermohonan.getIdAliran());
                        LOG.info("Trh masuk ::" + fasaPermohonan.getInfoAudit().getTarikhMasuk());
                        LOG.info("Trh Hantar ::" + fasaPermohonan.getTarikhHantar());
                        LOG.info("Pguna ::" + fasaPermohonan.getInfoAudit().getDimasukOleh().getNama());
                        long days;
                        days = getDaysBetweenDates(userDate);
                        LOG.info("No. of Days ::" + days);
                        if(days > 90){
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "APPROVE");
                        }else{
                            WorkFlowService.updateTaskOutcome(taskID, ctxW, "COMPLETE");
                        }
                  }
                    addSimpleMessage("Urusan Telah Selesai") ;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
           
//           return new JSP("pelupusan/rayuan/bayaran_ansuran.jsp").addParameter("tab", "true");
      return new ForwardResolution("/WEB-INF/jsp/pembangunan/rayuan/cetak_resit_bayaran_ansuran.jsp").addParameter("tab", "true");
    }

     public Resolution simpanTarikhOnly() {
        LOG.info("simpan tarikh tandatangan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pengguna.getKodCawangan();
        
        String tt = getContext().getRequest().getParameter("tarikh");
        LOG.info("Tarikh Jana : " + tt);

            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null){
                 try {
                    Date trktt = (Date) formatter.parse(tt);
                    permohonan.setTarikhMula(trktt);
                    devService.simpanPermohonan(permohonan);
                } catch (java.text.ParseException e) {
                    LOG.info("Exception:" + e);
                }
            }           
        addSimpleMessage("Maklumat telah berjaya disimpan.");              
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/rayuan/tarikh_jana.jsp").addParameter("tab", "true");
    }
     
    public long getDaysBetweenDates(Date userDate) {

        Date currDate = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.clear();
        Calendar c2 = Calendar.getInstance();
        c2.clear();
        c1.set(userDate.getYear() + 1900, userDate.getMonth(), userDate.getDate());
        c2.set(currDate.getYear() + 1900, currDate.getMonth(), currDate.getDate());

        // Print out the dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LOG.info("----User date----:" + sdf.format(c1.getTime()));
        LOG.info("----current date----:" + sdf.format(c2.getTime()));

        // Get the represented date in milliseconds
        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = time2 - time1;

        // Difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }
    
    public Resolution simpanUlasan() {
         pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        if(permohonan != null){
            InfoAudit ia = new InfoAudit();
            ia = permohonan.getInfoAudit() ;
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            String sebabPermohonan = getContext().getRequest().getParameter("permohonan.ulasan");
            permohonan.setUlasan(sebabPermohonan);
        pelupusanService.simpanPermohonan(permohonan);
            
        }
        addSimpleMessage("Maklumat telah disimpan");       
        return new JSP("pelupusan/rayuan/ulasan_rayuan.jsp").addParameter("tab", "true");
    }
    
     private void saveCaraBayaran(KodCawangan caw, DokumenKewangan dk, InfoAudit ia) {
        ArrayList<DokumenKewanganBayaran> adkb = new ArrayList<DokumenKewanganBayaran>();
        LOG.info("START CARABAYAR:-----------------------------");
        for (CaraBayaran cb : senaraiCaraBayaran) {
            if (cb.getKodCaraBayaran() != null && !cb.getKodCaraBayaran().getKod().equals("0")
                    && cb.getAmaun() != null && cb.getAmaun().compareTo(BigDecimal.ZERO) > 0) {
                // clear if null
                if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {
                    cb.setBank(null);
                    cb.setBankCawangan(null);
                }
                cb.setCawangan(caw);
                cb.setInfoAudit(ia);
                cb.setAktif('Y');
                caraBayaranDAO.save(cb);
                DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                dkb.setCaraBayaran(cb);
                dkb.setDokumenKewangan(dk);
                dkb.setAmaun(cb.getAmaun()); // TODO: cheque handling
                dkb.setInfoAudit(ia);
                dkb.setAktif('Y');
                adkb.add(dkb);
            }
        }
        dk.setSenaraiBayaran(adkb);
    }
     private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        LOG.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);
        return list;
    }
    
    
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
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

    public Date getTarikhAkhirBayaran1() {
        return tarikhAkhirBayaran1;
    }

    public void setTarikhAkhirBayaran1(Date tarikhAkhirBayaran1) {
        this.tarikhAkhirBayaran1 = tarikhAkhirBayaran1;
    }

    public Date getTarikhAkhirBayaran2() {
        return tarikhAkhirBayaran2;
    }

    public void setTarikhAkhirBayaran2(Date tarikhAkhirBayaran2) {
        this.tarikhAkhirBayaran2 = tarikhAkhirBayaran2;
    }

    public Date getTarikhAkhirBayaran3() {
        return tarikhAkhirBayaran3;
    }

    public void setTarikhAkhirBayaran3(Date tarikhAkhirBayaran3) {
        this.tarikhAkhirBayaran3 = tarikhAkhirBayaran3;
    }

    public Date getTarikhAkhirBayaran4() {
        return tarikhAkhirBayaran4;
    }

    public void setTarikhAkhirBayaran4(Date tarikhAkhirBayaran4) {
        this.tarikhAkhirBayaran4 = tarikhAkhirBayaran4;
    }

    public Date getTarikhAkhirBayaran5() {
        return tarikhAkhirBayaran5;
    }

    public void setTarikhAkhirBayaran5(Date tarikhAkhirBayaran5) {
        this.tarikhAkhirBayaran5 = tarikhAkhirBayaran5;
    }

    public Date getTarikhAkhirBayaran6() {
        return tarikhAkhirBayaran6;
    }

    public void setTarikhAkhirBayaran6(Date tarikhAkhirBayaran6) {
        this.tarikhAkhirBayaran6 = tarikhAkhirBayaran6;
    }

    public BigDecimal getBulan1() {
        return bulan1;
    }

    public void setBulan1(BigDecimal bulan1) {
        this.bulan1 = bulan1;
    }

    public BigDecimal getBulan2() {
        return bulan2;
    }

    public void setBulan2(BigDecimal bulan2) {
        this.bulan2 = bulan2;
    }

    public BigDecimal getBulan3() {
        return bulan3;
    }

    public void setBulan3(BigDecimal bulan3) {
        this.bulan3 = bulan3;
    }

    public BigDecimal getBulan4() {
        return bulan4;
    }

    public void setBulan4(BigDecimal bulan4) {
        this.bulan4 = bulan4;
    }

    public BigDecimal getBulan5() {
        return bulan5;
    }

    public void setBulan5(BigDecimal bulan5) {
        this.bulan5 = bulan5;
    }

    public BigDecimal getBulan6() {
        return bulan6;
    }

    public void setBulan6(BigDecimal bulan6) {
        this.bulan6 = bulan6;
    }

    public int getBilBulan() {
        return bilBulan;
    }

    public void setBilBulan(int bilBulan) {
        this.bilBulan = bilBulan;
    }

    public Ansuran getAnsuran() {
        return ansuran;
    }

    public void setAnsuran(Ansuran ansuran) {
        this.ansuran = ansuran;
    }

    public List<Ansuran> getAnsuranList() {
        return ansuranList;
    }

    public void setAnsuranList(List<Ansuran> ansuranList) {
        this.ansuranList = ansuranList;
    }

    public PermohonanTuntutan getPermohonanTuntutan() {
        return permohonanTuntutan;
    }

    public void setPermohonanTuntutan(PermohonanTuntutan permohonanTuntutan) {
        this.permohonanTuntutan = permohonanTuntutan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanTuntutanBayar getMohonTuntutBayar() {
        return mohonTuntutBayar;
    }

    public void setMohonTuntutBayar(PermohonanTuntutanBayar mohonTuntutBayar) {
        this.mohonTuntutBayar = mohonTuntutBayar;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public BigDecimal getAmaun1() {
        return amaun1;
    }

    public void setAmaun1(BigDecimal amaun1) {
        this.amaun1 = amaun1;
    }

    public BigDecimal getAmaun2() {
        return amaun2;
    }

    public void setAmaun2(BigDecimal amaun2) {
        this.amaun2 = amaun2;
    }

    public BigDecimal getAmaun3() {
        return amaun3;
    }

    public void setAmaun3(BigDecimal amaun3) {
        this.amaun3 = amaun3;
    }

    public BigDecimal getAmaun4() {
        return amaun4;
    }

    public void setAmaun4(BigDecimal amaun4) {
        this.amaun4 = amaun4;
    }

    public BigDecimal getAmaun5() {
        return amaun5;
    }

    public void setAmaun5(BigDecimal amaun5) {
        this.amaun5 = amaun5;
    }

    public BigDecimal getAmaun6() {
        return amaun6;
    }

    public void setAmaun6(BigDecimal amaun6) {
        this.amaun6 = amaun6;
    }

    public List<PermohonanTuntutanBayar> getListBayaran() {
        return listBayaran;
    }

    public void setListBayaran(List<PermohonanTuntutanBayar> listBayaran) {
        this.listBayaran = listBayaran;
    }

    public boolean isPtd() {
        return ptd;
    }

    public void setPtd(boolean ptd) {
        this.ptd = ptd;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }

    public Date getTarikhDibayar() {
        return tarikhDibayar;
    }

    public void setTarikhDibayar(Date tarikhDibayar) {
        this.tarikhDibayar = tarikhDibayar;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    public BigDecimal getJumCaraBayar() {
        return jumCaraBayar;
    }

    public void setJumCaraBayar(BigDecimal jumCaraBayar) {
        this.jumCaraBayar = jumCaraBayar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public BigDecimal getBayaranKekal() {
        return bayaranKekal;
    }

    public void setBayaranKekal(BigDecimal bayaranKekal) {
        this.bayaranKekal = bayaranKekal;
    }

    public BigDecimal getBayaranPelan() {
        return bayaranPelan;
    }

    public void setBayaranPelan(BigDecimal bayaranPelan) {
        this.bayaranPelan = bayaranPelan;
    }

    public BigDecimal getBayaranSemantara() {
        return bayaranSemantara;
    }

    public void setBayaranSemantara(BigDecimal bayaranSemantara) {
        this.bayaranSemantara = bayaranSemantara;
    }

    public BigDecimal getHasilTahunPertama() {
        return hasilTahunPertama;
    }

    public void setHasilTahunPertama(BigDecimal hasilTahunPertama) {
        this.hasilTahunPertama = hasilTahunPertama;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getSumbanganSaliran() {
        return sumbanganSaliran;
    }

    public void setSumbanganSaliran(BigDecimal sumbanganSaliran) {
        this.sumbanganSaliran = sumbanganSaliran;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public BigDecimal getJumlahKeseluruhan() {
        return jumlahKeseluruhan;
    }

    public void setJumlahKeseluruhan(BigDecimal jumlahKeseluruhan) {
        this.jumlahKeseluruhan = jumlahKeseluruhan;
    }

    public boolean isIsAnsuranPremium() {
        return isAnsuranPremium;
    }

    public void setIsAnsuranPremium(boolean isAnsuranPremium) {
        this.isAnsuranPremium = isAnsuranPremium;
    }

    public BigDecimal getJumlah2() {
        return jumlah2;
    }

    public void setJumlah2(BigDecimal jumlah2) {
        this.jumlah2 = jumlah2;
    }

    public BigDecimal getPremiumBaru() {
        return premiumBaru;
    }

    public void setPremiumBaru(BigDecimal premiumBaru) {
        this.premiumBaru = premiumBaru;
    }

    public BigDecimal getJumlah3() {
        return jumlah3;
    }

    public void setJumlah3(BigDecimal jumlah3) {
        this.jumlah3 = jumlah3;
    }

    public BigDecimal getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(BigDecimal pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public BigDecimal getJumlah4() {
        return jumlah4;
    }

    public void setJumlah4(BigDecimal jumlah4) {
        this.jumlah4 = jumlah4;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }
}

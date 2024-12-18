/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikDAO ;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanJenteraDAO;
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
import etanah.model.KodKeputusan;
import etanah.model.KodPemilikan;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanManual;
import etanah.service.BPelService;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;


/**
 *
 * @author Akmal
 */
@UrlBinding("/pelupusan/laporan_tanah_batuan")
public class LaporanTanahBatuanActionBean extends AbleActionBean{
    private static Logger logger = Logger.getLogger(LaporanTanahBatuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO ;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    KodPemilikanDAO kodPemilikanDAO ;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService ;
    @Inject
    KodKeputusanDAO kodKeputusanDAO ;
    
    private LaporanTanah laporanTanah;
    private Boolean display = Boolean.FALSE;
    private KodBandarPekanMukim kodBPM;
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanJentera permohonanJentera ;
    private KodBahanBatu jenisBahanBatu;
    private List<PermohonanManual> permohonanManualList ;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList;
    private List<PermohonanJentera> listJentera ;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private String idPermohonan;
    private Pengguna pengguna ;  
    private Pemohon pemohon ;
    private Pihak pihak ;
    private Character kodMilik ;
    private FasaPermohonan fasaPermohonan;
    private String stageId;
    private String ulasan ;
    private String kpsn ;
    private BigDecimal jumlahIsipadu ;
    private String tempoh ;
    private KodUOM jumlahIsipaduUOM ;
    private String tempohSyorUOM;
    private Integer tempohKeluar;
    private Character unitTempohKeluar;
    private KodKeputusan kod ;
    private String kpsn2 ;
    private Date tarikhKeputusan ;

    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        BPelService service = new BPelService();
//        stageId = "laporan_tanah";
        //stageId = null;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        
    if(permohonan != null){
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        listJentera = permohonanJenteraDAO.findByEqualCriterias(tname, tvalue, null) ;
        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan) ;
        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
        if(permohonanBahanBatuan!=null){
            if(permohonanBahanBatuan.getTempohSyorUom() != null)
            tempohSyorUOM = permohonanBahanBatuan.getTempohSyorUom().getKod();
        }
        permohonanManualList = pelupusanService.findByIdMohonlist(idPermohonan) ;
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "laporan_tanah") ;
        if(hakmilikPermohonan.getKodMilik() != null){
            kodMilik = hakmilikPermohonan.getKodMilik().getKod() ;
        }
        if(fasaPermohonan !=null){
            tarikhKeputusan = fasaPermohonan.getTarikhKeputusan();
            if(fasaPermohonan.getTarikhKeputusan()==null)
                fasaPermohonan.setTarikhKeputusan(new Date()); 
        }
        if(laporanTanah != null){
        imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
        utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
        selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
        timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
        baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
        }
        if(pemohon != null)
        {pihak = pemohon.getPihak() ;}
        
        }
    if(fasaPermohonan != null){
       if(fasaPermohonan.getKeputusan() != null){
           kpsn = fasaPermohonan.getKeputusan().getKod() ;
          if(kpsn2 == null)
           kpsn2 = fasaPermohonan.getKeputusan().getKod() ;
           ulasan = fasaPermohonan.getUlasan() ;
           //baru
       }
    }

    }
    
     @DefaultHandler
    public Resolution showForm() {
    return new JSP("pelupusan/batuan/laporan_tanah_mlk.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {

        display = Boolean.TRUE;
    return new JSP("pelupusan/batuan/laporan_tanah_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showFormNs1(){
         return new JSP("pelupusan/batuan/laporan_tanah_ns1.jsp").addParameter("tab", "true");
    }
     
     
     public Resolution simpanLaporan() throws Exception {
         String test = getContext().getRequest().getParameter("kodMilik") ;
         logger.info("TEST.....> " + test) ;
         hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan); 
         laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan) ;
         InfoAudit infoAudit = new InfoAudit() ;
         if(hakmilikPermohonan != null){
            if(StringUtils.isNotBlank(test)){
             Character test1 = test.charAt(0) ;
             KodPemilikan testing = kodPemilikanDAO.findById(test1);
             hakmilikPermohonan.setKodMilik(testing);
             pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
            }
         }
         
             if (laporanTanah != null) {
                 infoAudit.setDiKemaskiniOleh(pengguna);
                 infoAudit.setTarikhKemaskini(new java.util.Date());
                 infoAudit.setDimasukOleh(laporanTanah.getInfoAudit().getDimasukOleh());
                 infoAudit.setTarikhMasuk(laporanTanah.getInfoAudit().getTarikhMasuk());
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);       
            laporanTanahService.simpanLaporanTanah(laporanTanah);
        } else {
             infoAudit.setDimasukOleh(pengguna);
             infoAudit.setTarikhMasuk(new java.util.Date());
            laporanTanah = new LaporanTanah();
            //Utara
            String utara = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraMilikKerajaan") ;
            String utaraLot = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraNoLot") ;
            String utaraK = getContext().getRequest().getParameter("laporanTanah.sempadanUtaraKegunaan") ;

            if(StringUtils.isNotBlank(utara)){
            Character utaraC = utara.charAt(0) ;
            laporanTanah.setSempadanUtaraMilikKerajaan(utaraC);
            }
            laporanTanah.setSempadanUtaraNoLot(utaraLot);
            laporanTanah.setSempadanUtaraKegunaan(utaraK);
            //Selatan
             String selatan = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanMilikKerajaan") ;
            String selatanLot = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanNoLot") ;
            String selatanK = getContext().getRequest().getParameter("laporanTanah.sempadanSelatanKegunaan") ;
            if(StringUtils.isNotBlank(selatan)){
            Character selatanC = selatan.charAt(0) ;
            laporanTanah.setSempadanSelatanMilikKerajaan(selatanC);
            }
            laporanTanah.setSempadanSelatanNoLot(selatanLot);
            laporanTanah.setSempadanSelatanKegunaan(selatanK);
            
            //Timur
               String timur = getContext().getRequest().getParameter("laporanTanah.sempadanTimurMilikKerajaan") ;
            String timurLot = getContext().getRequest().getParameter("laporanTanah.sempadanTimurNoLot") ;
            String timurK = getContext().getRequest().getParameter("laporanTanah.sempadanTimurKegunaan") ;
            if(StringUtils.isNotBlank(timur)){
            Character timurC = timur.charAt(0) ;
            laporanTanah.setSempadanTimurMilikKerajaan(timurC);
            }
            laporanTanah.setSempadanTimurNoLot(timurLot);
            laporanTanah.setSempadanTimurKegunaan(timurK);
            
            //Barat
               String barat = getContext().getRequest().getParameter("laporanTanah.sempadanBaratMilikKerajaan") ;
            String baratLot = getContext().getRequest().getParameter("laporanTanah.sempadanBaratNoLot") ;
            String baratK = getContext().getRequest().getParameter("laporanTanah.sempadanBaratKegunaan") ;
            if(StringUtils.isNotBlank(barat)){
            Character baratC = barat.charAt(0) ;
            laporanTanah.setSempadanBaratMilikKerajaan(baratC);
            }
            laporanTanah.setSempadanBaratNoLot(baratLot);
            laporanTanah.setSempadanBaratKegunaan(baratK);
            
          laporanTanah.setPermohonan(permohonan);      
            laporanTanah.setInfoAudit(infoAudit);
            pelupusanService.simpanLaporanTanah(laporanTanah) ;

        }
             
          BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------"+taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--"+stageId);
        }

        stageId = "laporan_tanah";
        kpsn = getContext().getRequest().getParameter("kpsn");
        logger.info("Keputusan----- " + kpsn);
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId) ;
        if (fasaPermohonan != null) {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(fasaPermohonan.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(fasaPermohonan.getInfoAudit().getTarikhMasuk());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setTarikhKeputusan(tarikhKeputusan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            kod = kodKeputusanDAO.findById(kpsn) ;
//            kod = kpsn;
            
//            kod = pelupusanService.finByIdKodKpsn(kpsn);
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
             ulasan = getContext().getRequest().getParameter("fasaPermohonan.ulasan");
            if(ulasan != null && ("").equals(ulasan)) {
            fasaPermohonan.setUlasan(ulasan);
            }
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            if(kpsn.equals("DI")){
                setKpsn2("DI");
            }
            else if(kpsn.equals("DX")){
                setKpsn2("DX");
            }
            else if(kpsn.equals("TI")){
                setKpsn2("TI");
            }
            else;
//          
        }else
        {
            fasaPermohonan = new FasaPermohonan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            kod = kodKeputusanDAO.findById(kpsn) ;
            
            if(kpsn.equals("DI")){
                setKpsn2("DI");
            }
            else if(kpsn.equals("XD")){
                setKpsn2("XD");
            }
            else if(kpsn.equals("TI")){
                setKpsn2("TI");
            }
            else;
            
            

            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
            ulasan = getContext().getRequest().getParameter("fasaPermohonan.ulasan");
            if(ulasan != null && ("").equals(ulasan)) {
            fasaPermohonan.setUlasan(ulasan);
            }
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        }
        if(kpsn.equals("DI")){
        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan);
        if(permohonanBahanBatuan != null){
            InfoAudit ia = new InfoAudit() ;
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDimasukOleh(permohonanBahanBatuan.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(permohonanBahanBatuan.getInfoAudit().getTarikhMasuk());
            permohonanBahanBatuan.setInfoAudit(ia);

            fasaPermohonan.setTarikhKeputusan(tarikhKeputusan);
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
            
            jumlahIsipaduUOM = new KodUOM() ;
             String kod3 = getContext().getRequest().getParameter("jumlahIsipaduUom.kod");
             jumlahIsipaduUOM.setKod(kod3);
             permohonanBahanBatuan.setJumlahIsipaduUom(jumlahIsipaduUOM);
             String isipadu = getContext().getRequest().getParameter("permohonanBahanBatuan.jumlahIsipadu");
                if(isipadu != null && !("").equals(isipadu)) {
                jumlahIsipadu = new BigDecimal(isipadu);
                permohonanBahanBatuan.setJumlahIsipadu(jumlahIsipadu);
                permohonanBahanBatuan.setTempohSyorUom(kodUOMDAO.findById(tempohSyorUOM));
                pelupusanService.simpanPermohonanBahanBatuan(permohonanBahanBatuan);
                }        
            }
        }
        else if(kpsn.equals("DX")){
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId) ;
            if (fasaPermohonan != null) {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(fasaPermohonan.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(fasaPermohonan.getInfoAudit().getTarikhMasuk());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setTarikhKeputusan(tarikhKeputusan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            kod = kodKeputusanDAO.findById(kpsn) ;
//            kod = kpsn;

//            kod = pelupusanService.finByIdKodKpsn(kpsn);
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
             ulasan = getContext().getRequest().getParameter("fasaPermohonan.ulasan");
        }
        }
        else if(kpsn.equals("TI")){
            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId) ;
            if (fasaPermohonan != null) {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setDimasukOleh(fasaPermohonan.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(fasaPermohonan.getInfoAudit().getTarikhMasuk());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setTarikhKeputusan(tarikhKeputusan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            kod = kodKeputusanDAO.findById(kpsn) ;
//            kod = kpsn;

//            kod = pelupusanService.finByIdKodKpsn(kpsn);
            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
             ulasan = getContext().getRequest().getParameter("fasaPermohonan.ulasan");
        }
        }
         addSimpleMessage("Rekod Telah Disimpan") ;
         display = Boolean.TRUE;
         rehydrate() ;
          return new JSP("pelupusan/batuan/laporan_tanah_ns1.jsp").addParameter("tab", "true");
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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Date getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(Date tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public KodBahanBatu getJenisBahanBatu() {
        return jenisBahanBatu;
    }

    public void setJenisBahanBatu(KodBahanBatu jenisBahanBatu) {
        this.jenisBahanBatu = jenisBahanBatu;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public List<PermohonanJentera> getListJentera() {
        return listJentera;
    }

    public void setListJentera(List<PermohonanJentera> listJentera) {
        this.listJentera = listJentera;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Character getKodMilik() {
        return kodMilik;
    }

    public void setKodMilik(Character kodMilik) {
        this.kodMilik = kodMilik;
    }

    public List<PermohonanManual> getPermohonanManualList() {
        return permohonanManualList;
    }

    public void setPermohonanManualList(List<PermohonanManual> permohonanManualList) {
        this.permohonanManualList = permohonanManualList;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanJentera getPermohonanJentera() {
        return permohonanJentera;
    }

    public void setPermohonanJentera(PermohonanJentera permohonanJentera) {
        this.permohonanJentera = permohonanJentera;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public BigDecimal getJumlahIsipadu() {
        return jumlahIsipadu;
    }

    public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
        this.jumlahIsipadu = jumlahIsipadu;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public KodUOM getJumlahIsipaduUOM() {
        return jumlahIsipaduUOM;
    }

    public void setJumlahIsipaduUOM(KodUOM jumlahIsipaduUOM) {
        this.jumlahIsipaduUOM = jumlahIsipaduUOM;
    }

    public Integer getTempohKeluar() {
        return tempohKeluar;
    }

    public void setTempohKeluar(Integer tempohKeluar) {
        this.tempohKeluar = tempohKeluar;
    }

    public Character getUnitTempohKeluar() {
        return unitTempohKeluar;
    }

    public void setUnitTempohKeluar(Character unitTempohKeluar) {
        this.unitTempohKeluar = unitTempohKeluar;
    }

    public String getKpsn2() {
        return kpsn2;
    }

    public void setKpsn2(String kpsn2) {
        this.kpsn2 = kpsn2;
    }

    public String getTempohSyorUOM() {
        return tempohSyorUOM;
    }

    public void setTempohSyorUOM(String tempohSyorUOM) {
        this.tempohSyorUOM = tempohSyorUOM;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
        
     
}

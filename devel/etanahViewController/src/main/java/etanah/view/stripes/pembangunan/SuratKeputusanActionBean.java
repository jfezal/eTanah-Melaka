/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author khairul.hazwan
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
//import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Dokumen;
import etanah.model.KodDokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanPlotPelan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodAgensi;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.report.ReportUtil;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.manager.TabManager;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import net.sourceforge.stripes.action.RedirectResolution;
import java.io.File;
import java.math.BigDecimal;
import java.util.StringTokenizer;

@UrlBinding("/pembangunan/melaka/surat_keputusan_jkbb")
public class SuratKeputusanActionBean extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(SuratKeputusanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanRujukanLuarService permohonanRujService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    TabManager tabManager;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPlotPelanDAO plotPelanDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    @Inject
    KodNegeriDAO kodNegeriDAO;

    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private LaporanTanah laporanTanah;
    private PermohonanTandatanganDokumen mohonDokTt;
    private String persidangan;
    private String idPermohonan;
    private String tarikhPermohonan;
    private String tajuk;
    private String lokasiTanah;    
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private int rowCount;
    private String perakuan1;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    
    private List<Pemohon> listPemohon;
    private Pengguna pengguna;
    private String kodUrusan;
    private String pt;
    private KodDokumen kd;
    private List<PermohonanPlotPelan> listplotpelan;
    private String workflowId;
    private String idFolder = "";
    private String taskId;
    private String stageId;
    private Task task = null;
    private BPelService service;
    private String keputusan;
    private String tujuan;
    private List<PermohonanKertasKandungan> senaraiTindakan;
    private int rowCount2;
    private PermohonanRujukanLuar ulasanAdun;
    private List<PermohonanRujukanLuar> ulasanList;
    private String pejabat;
    private String ulasanptd;
    private String ulasanptg;
    private String perakuanPengarah;
    private String kertasBil;
    private String bilangan;
    private String tarikhSidang;
    private String keputusanMesyuarat;
    private String tindakan;
    private BigDecimal luasTotal=BigDecimal.ZERO;
    private String lokasi;
   
    
    

    @DefaultHandler
    public Resolution showForm() {
       LOG.info("showForm");
       getContext().getRequest().setAttribute("edit", Boolean.TRUE);       
       return new JSP("pembangunan/melaka/surat_keputusanJkbb.jsp").addParameter("tab", "true");       
    }

    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        LOG.info("rehydrate start");
        rowCount2 = 1;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        PermohonanKertas kertasP = new PermohonanKertas();
        kertasP = devService.findPermohonanKertasByKod(idPermohonan, "SJKBB");
        LOG.info("---------kertasP-------SJKBB--------:" + kertasP);
            if(kertasP!=null){                                
                     //bilangan = kertasP.getNomborRujukan();
                     bilangan = kertasP.getTajuk();
                     if(kertasP.getTarikhSidang()!=null){
                        tarikhSidang =sdf.format(kertasP.getTarikhSidang());
                     }
                     for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                         LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                         kertasK = new PermohonanKertasKandungan();
                         kertasK = kertasP.getSenaraiKandungan().get(j);

                         if (kertasK.getBil() == 1) {
                             tajuk = kertasK.getKandungan();
                         } else if (kertasK.getBil() == 2) {
                             keputusanMesyuarat = kertasK.getKandungan();
                         } else if (kertasK.getBil() == 4) {
                             tujuan = kertasK.getKandungan();
                         } else if (kertasK.getBil() == 5) {
                             pejabat = kertasK.getKandungan();
                         } 
                     }
                     senaraiTindakan = new ArrayList<PermohonanKertasKandungan>();
                     senaraiTindakan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                     rowCount2 = senaraiTindakan.size();
                     
                     if(permohonan.getCawangan().getKod().equals("01")){
                        pejabat = "Melaka Tengah";
                     }else if (permohonan.getCawangan().getKod().equals("02")){
                        pejabat = "Jasin";
                     }else if (permohonan.getCawangan().getKod().equals("03")){
                        pejabat = "Alor Gajah";
                     }
                     
                LOG.info("---------Pejabat---------------:"+pejabat);
             }else{
                kertasP = devService.findPermohonanKertasByKod(idPermohonan, "JKBB");
                LOG.info("-------default data Kertas-----JKBB-------:"+kertasP);
                   if (kertasP != null) {
                       LOG.info("------------JKBB----kertasP-----:"+kertasP.getIdKertas());
                       //bilangan = kertasP.getNomborRujukan();
                       bilangan = kertasP.getTajuk();
                       if(kertasP.getTarikhSidang()!=null){
                          LOG.info("------------JKBB----kertasP.getTarikhSidang()-----:"+kertasP.getTarikhSidang()); 
                          tarikhSidang =sdf.format(kertasP.getTarikhSidang());
                          LOG.info("------------JKBB----tarikhSidang-----:"+tarikhSidang); 
                       }
                       
                     for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                         LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                         kertasK = new PermohonanKertasKandungan();
                         kertasK = kertasP.getSenaraiKandungan().get(j);

                         if (kertasK.getBil() == 1) {
                             LOG.info("-----------tajuk---kand-----" + kertasK.getKandungan());
                             tajuk = kertasK.getKandungan();
                         } 
                     }
                   }//if
                   
                // For tindakan part                   
                if(permohonan.getKodUrusan().getKod().equals("PPCB")){

                    tindakan = "Sila kemukakan 6 salinan cadangan pelan perenggan dan salinan resit cukai "
                                + "semasa kepada Pejabat Tanah Melaka Tengah, Melaka dalam tempoh "
                                + "30 hari dari tarikh surat ini kepada tujuan endorsan.";                         
                }
                else if (permohonan.getKodUrusan().getKod().equals("PYTN") || 
                        permohonan.getKodUrusan().getKod().equals("PPCS") || 
                        permohonan.getKodUrusan().getKod().equals("TSPSS")){

                    tindakan = "Sila kemukakan 18 salinan cadangan pelan tatatur kepada Pusat Setempat "
                                + "(OSC) di Pihak Berkuasa Tempatan di daerah masing-masing dalam tempoh "
                                + "30 hari dari tarikh surat ini kepada tujuan endorsan.";   
                } 
                else if (permohonan.getKodUrusan().getKod().equals("SBMS")){

                    tindakan = "Sila kemukakan cadangan pelan tatatur kepada semua Jabatan Teknikal serta "
                                + "4 salinan cadangan pelan tatatur kepada Pusat Setempat (OSC) di Pihak "
                                + "Berkuasa Setempat di daerah masing-masing dalam tempoh 30 hari dari "
                                + "tarikh surat ini.";
                }
                else{
                    tindakan="-";
                }                
                LOG.info("---------Tindakan---------------:" + tindakan);
                // For keputusan part
                if (permohonan.getKeputusan().getKod().equals("L")) {                               
                    keputusanMesyuarat =  "bersetuju meluluskan";
                    tujuan = "bagi tujuan" +" "+ tujuanDefaultData();
                    
                }else if (permohonan.getKeputusan().getKod().equals("T")){                 
                    keputusanMesyuarat = "menolak";
                }
                LOG.info("---------Keputusan Mesyuarat---------------:"+keputusanMesyuarat);
                
                //pejabat               
                if(permohonan.getCawangan().getKod().equals("01")){
                   pejabat = "Melaka Tengah";
                }else if (permohonan.getCawangan().getKod().equals("02")){
                   pejabat = "Jasin";
                }else if (permohonan.getCawangan().getKod().equals("03")){
                   pejabat = "Alor Gajah";
                }
                LOG.info("---------Pejabat---------------:"+pejabat);
                
                senaraiTindakan = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan kand= new PermohonanKertasKandungan();
                kand.setKandungan(tindakan);
                senaraiTindakan.add(kand);
                rowCount2 = senaraiTindakan.size();
                LOG.info("-----------rowCount2--------" + rowCount2);
                   
            }//else
          stageId = currentStageId();
          LOG.info("---------stageId---------------:"+stageId);
          LOG.info("-----------tajuk--------" + tajuk);
          
//          FasaPermohonan mohonFasa = devService.findFasaPermohonanByIdAliran(idPermohonan, "cetakjkbbrekodkpsn");        
//          if(mohonFasa!=null){
           
          mohonDokTt = devService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SJKBB"); 
           
           if(mohonDokTt!=null && mohonDokTt.getTrhTt()!=null){
               tarikhPermohonan = sdf.format(mohonDokTt.getTrhTt());
               LOG.info("---------tarikhPermohonan1---------------:"+tarikhPermohonan);
           }
          
        LOG.info("rehydrate finish");
    }

    
    public Resolution simpan() throws ParseException {
        LOG.info("simpan start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("SJKBB");

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }
        if (keputusanMesyuarat == null || keputusanMesyuarat.equals("")) {
            keputusanMesyuarat = "TIADA DATA.";
        }
        if (bilangan == null || bilangan.equals("")) {
            bilangan = "";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "";
        }
        if (pejabat == null || pejabat.equals("")) {
            pejabat = "";
        }
        

        listUlasan.add(tajuk);
        listUlasan.add(keputusanMesyuarat);
        listUlasan.add(tujuan);
        listUlasan.add(pejabat);

        permohonanKertas = devService.findPermohonanKertasByKod(idPermohonan, "SJKBB");
        if (permohonanKertas != null) {

            
            LOG.info("kemaskini start");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = permohonanKertas.getSenaraiKandungan().get(j);
                    LOG.info("senarai kandungan:" + permohonanKertas.getSenaraiKandungan().size());

                    if (kertasK.getBil() == 1) {
                        kertasK.setKandungan(tajuk);
                    } else if (kertasK.getBil() == 2) {
                        kertasK.setKandungan(keputusanMesyuarat);
                    } else if (kertasK.getBil() == 4) {
                        kertasK.setKandungan(tujuan);
                    } else if (kertasK.getBil() == 5) {
                        kertasK.setKandungan(pejabat);
                    } 
                    
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);                    
                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            
            LOG.info("kemaskini finish");
        } else {
            permohonanKertas=new PermohonanKertas();
            LOG.info("simpan new entry");
            for (int i = 0; i < listUlasan.size(); i++) {

                String dataulasan = (String) listUlasan.get(i);               
                kertasK = new PermohonanKertasKandungan();
                
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk(bilangan);
                //permohonanKertas.setNomborRujukan(bilangan);
                LOG.info("------------Simpan----tarikhSidang-----:"+tarikhSidang); 
                if(tarikhSidang!=null){
                    Date tarikhsidang1 = (Date)sdf.parse(tarikhSidang);
                    permohonanKertas.setTarikhSidang(tarikhsidang1);
                }
                
                kertasK.setKertas(permohonanKertas);                
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(dataulasan);
                if(i==2){
                    kertasK.setBil(i+2);
                }else{
                 kertasK.setBil(i+1);
                }
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }
        if(!((permohonan.getKodUrusan().getKod().equals("TSKKT") && permohonan.getKeputusan().getKod().equals("L"))
            ||(permohonan.getKodUrusan().getKod().equals("TSPSN") && permohonan.getKeputusan().getKod().equals("L"))
            ||(permohonan.getKodUrusan().getKod().equals("TSKSN") && permohonan.getKeputusan().getKod().equals("L")))){
          
        if(permohonan.getKeputusan().getKod().equalsIgnoreCase("L")){    
        senaraiTindakan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),3);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("----------count2--------:"+kira2);
        for (int i = 1; i <= kira2; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiTindakan.size() != 0 && i <= senaraiTindakan.size()) {
                Long id = senaraiTindakan.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("tindakan" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        }
        }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        
        LOG.info("simpan finish");

         
         senaraiTindakan = new ArrayList<PermohonanKertasKandungan>();
         senaraiTindakan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
         rowCount2 = senaraiTindakan.size();

//        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/surat_keputusanJkbb.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try{
        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        }catch(Exception e){
            LOG.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(SuratKeputusanActionBean.class, "locate");
    }


    public String currentStageId(){
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        return stageId;
    }
    
    
    public String tujuanDefaultData(){
        
        List<PermohonanPlotPelan> mppList=new ArrayList<PermohonanPlotPelan>();
                mppList = devService.findPermohonanPlotPelanByIdPermohonanKodMLK(idPermohonan); 
                StringBuffer tujuanInfo = new StringBuffer();
                StringBuffer tujuanDetail = new StringBuffer();
                int milikCount =0;
                String kodKatgTanah="";
                int bil=0;
                if(mppList!=null && !mppList.isEmpty()){                    
                   for(PermohonanPlotPelan mpp:mppList){
                        bil++;
                        tujuanDetail.append(bil+") "+mpp.getNoPlot()+" seluas "+mpp.getLuas()+" "+mpp.getKodUnitLuas().getNama()+" (   ) \n"); 
                        if(mpp.getPemilikan()!=null && mpp.getPemilikan().getKod()== 'H'){
                          milikCount++;                          
                        }
                        if(mpp.getKategoriTanah()!=null){
                            kodKatgTanah = mpp.getKategoriTanah().getNama();
                        }
                   } 
                   tujuanInfo.append(milikCount+" unit Lot "+kodKatgTanah+" \n");
                   tujuanInfo.append(tujuanDetail.toString());
                }else{
                    tujuanInfo.append("");
                }
           return tujuanInfo.toString();
    }
    
    

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }


    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }


    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }


    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }


    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }


    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }


    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }


    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }


    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }


    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }


    public SimpleDateFormat getTdf() {
        return tdf;
    }

    public void setTdf(SimpleDateFormat tdf) {
        this.tdf = tdf;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getPerakuan1() {
        return perakuan1;
    }

    public void setPerakuan1(String perakuan1) {
        this.perakuan1 = perakuan1;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public List<PermohonanKertasKandungan> getSenaraiTindakan() {
        return senaraiTindakan;
    }

    public void setSenaraiTindakan(List<PermohonanKertasKandungan> senaraiTindakan) {
        this.senaraiTindakan = senaraiTindakan;
    }

    

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public List<PermohonanRujukanLuar> getUlasanList() {
        return ulasanList;
    }

    public void setUlasanList(List<PermohonanRujukanLuar> ulasanList) {
        this.ulasanList = ulasanList;
    }

    public String getPejabat() {
        return pejabat;
    }

    public void setPejabat(String pejabat) {
        this.pejabat = pejabat;
    }

    public String getUlasanptd() {
        return ulasanptd;
    }

    public void setUlasanptd(String ulasanptd) {
        this.ulasanptd = ulasanptd;
    }

    public String getUlasanptg() {
        return ulasanptg;
    }

    public void setUlasanptg(String ulasanptg) {
        this.ulasanptg = ulasanptg;
    }

    public String getPerakuanPengarah() {
        return perakuanPengarah;
    }

    public void setPerakuanPengarah(String perakuanPengarah) {
        this.perakuanPengarah = perakuanPengarah;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getBilangan() {
        return bilangan;
    }

    public void setBilangan(String bilangan) {
        this.bilangan = bilangan;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getKeputusanMesyuarat() {
        return keputusanMesyuarat;
    }

    public void setKeputusanMesyuarat(String keputusanMesyuarat) {
        this.keputusanMesyuarat = keputusanMesyuarat;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public PermohonanTandatanganDokumen getMohonDokTt() {
        return mohonDokTt;
    }

    public void setMohonDokTt(PermohonanTandatanganDokumen mohonDokTt) {
        this.mohonDokTt = mohonDokTt;
    }
 
}


    


package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.KodKeputusan;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.logging.Level;

/**
 *
 * @author Orogenic Group Edited By Shaib Rassul On 12 Sept 2012 - remove radio
 * button
 */
@UrlBinding("/pelupusan/keputusanPTD_PRIZ")
public class KeputusanPTD_PRIZActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    private String idPermohonan;
    private FasaPermohonan fasaMohon;
    private Pengguna pengguna;
    private String stageId;
    private Permohonan permohonan;
    private boolean openPTD = true;   
    private String keputusan;
    private String ulasan;
    private Pengguna pguna;
    private String stage;
    
    private static final Logger log = Logger.getLogger(KeputusanPTD_PRIZActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        log.info("-----Show Form Methode-----");
        return new JSP("pelupusan/keputusan_ptd.jsp").addParameter("tab", "true");
    }
    public Resolution viewForm() {
        openPTD = Boolean.FALSE;
        viewOnly = Boolean.TRUE;
        
        return new JSP("pelupusan/keputusan_ptd.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.info("-----Pengguna Rehydrate-----");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!(permohonan.getSenaraiFasa().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {

                fasaMohon = new FasaPermohonan();
                fasaMohon = permohonan.getSenaraiFasa().get(i);
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBPTG")) {
                    if (fasaMohon.getIdAliran().equals("keputusan_ptd")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                        log.info("-----Keputusan Stage keputusan_ptd : " +keputusan);
                        if(fasaMohon != null){
                           ulasan = fasaMohon.getUlasan(); 
                        }else{
                           ulasan = "Tiada ulasan"; 
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRIZ")) {
                    if (fasaMohon.getIdAliran().equals("014")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                    if (fasaMohon.getIdAliran().equals("21TrmKptsnMMK")) {
                        keputusan = fasaMohon.getKeputusan().getNama();
                    }
                    if (fasaMohon.getIdAliran().equals("31SedSrtKptsn")) { //To cater ulasan from stage sedia surat keputusan
                        if(fasaMohon != null){
                           ulasan = fasaMohon.getUlasan(); 
                        }else{
                           ulasan = "Tiada ulasan"; 
                        }
                      
                    }
                }
                 if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
                                         
                    if(fasaMohon.getIdAliran().equals("42SedSrtKptsn") || fasaMohon.getIdAliran().equals("43SmkSrtKptsn") || fasaMohon.getIdAliran().equals("44SmkShknCtk") || fasaMohon.getIdAliran().equals("45SedSrtKptsnTlk") || fasaMohon.getIdAliran().equals("46SmkSrtKptsnTlk") || fasaMohon.getIdAliran().equals("47SmkShknCtkTlk")|| fasaMohon.getIdAliran().equals("sedia_surat_makluman")|| fasaMohon.getIdAliran().equals("Semak_surat_makluman")) {
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "40TrmImbsMsyrtMMK");
                        keputusan = mohonFasa.getKeputusan().getNama();
                        log.info("-----Keputusan Stage 40TrmImbsMsyrtMMK : " +keputusan);
                        
//                        if(keputusan.equalsIgnoreCase("XT")){
//                           
//                            if (fasaMohon.getIdAliran().equals("34PerakudanBantahan")) {
//                                keputusan = fasaMohon.getKeputusan().getNama();
//                                log.info("-----Keputusan Stage 34PerakudanBantahan : " +keputusan);
//                            
//                            if (keputusan.equalsIgnoreCase("BM")) {
//                                keputusan = fasaMohon.getKeputusan().getNama();
//                                log.info("-----Keputusan Stage 34PerakudanBantahan is BM : " +keputusan);
//                            }
//                            
//                          }   
//                        
//                        }
                    }
                    if(fasaMohon.getIdAliran().equals("55SedSrt") || fasaMohon.getIdAliran().equals("56SmkSrtKptsn") || fasaMohon.getIdAliran().equals("57SmkShknCtk")){
                        keputusan = "Lulus";
                        
                    }
                 }
            }
        }

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
    }

    public Resolution simpan() {
        log.info("-----Simpan Methode-----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRIZ")) {
            stageId = "015";
        }else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
            stageId = "31SedSrtKptsn";
        }else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
            
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            BPelService serviceBpel = new BPelService();
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = serviceBpel.getTaskFromBPel(taskId, pguna);
                stageId = t.getSystemAttributes().getStage();
            }
            //stageId = "55SedSrt";
        }
        

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        fasaMohon.setIdPengguna(pengguna.getIdPengguna());
        fasaMohon.setCawangan(pengguna.getKodCawangan());
        fasaMohon.setPermohonan(permohonan);
        fasaMohon.setInfoAudit(infoAudit);
        fasaMohon.setIdAliran(stageId);
        pelupusanService.simpanFasaPermohonan(fasaMohon);
        
        String KPSN = "";
        
           if(keputusan.equalsIgnoreCase("Lulus")){
            KPSN = "L";
            }
            else if(keputusan.equalsIgnoreCase("Tolak")){
                KPSN = "T";
            }else if(keputusan.equalsIgnoreCase("Tiada Bantahan")){
                KPSN = "L";
            }  
                
        log.info("KPSN Ialah: " + KPSN);
        KodKeputusan kodKeputusan = new KodKeputusan();
        kodKeputusan = kodKeputusanDAO.findById(KPSN);
        permohonan.setKeputusan(kodKeputusan);
        permohonan.setKeputusanOleh(pengguna);  
        pelupusanService.simpanPermohonan(permohonan);
        
        log.info("Keputusan Kod " + kodKeputusan.getKod());
        log.info("Keputusan Nama " + permohonan.getKeputusan());
        log.info("Pengguna " +  pengguna.getNama());        

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/keputusan_ptd.jsp").addParameter("tab", "true");
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    public boolean isOpenPTD() {
        return openPTD;
    }

    public void setOpenPTD(boolean openPTD) {
        this.openPTD = openPTD;
    }
    private boolean viewOnly = false;

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }
    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}

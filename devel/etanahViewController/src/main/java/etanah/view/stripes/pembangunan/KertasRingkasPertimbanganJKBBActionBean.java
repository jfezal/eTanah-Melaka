/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;


/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/kertasRingkasPertimbanganJKBB")
public class KertasRingkasPertimbanganJKBBActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(KertasRingkasPertimbanganJKBBActionBean.class);

    @Inject
    BPelService service;    
    @Inject
    KodDokumenDAO kodDokumenDAO;    
    @Inject
    PermohonanDAO permohonanDAO;    
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;    
    @Inject
    PembangunanService devService;    
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;    
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;    
    private Pemohon pemohon;
    private PermohonanKertasKandungan kertasK;
    private String stageId;
    private HakmilikPermohonan hp;
    private List<Pemohon> listPemohon;
    private String komponenPembangunan;
    private String kuota;    
    private List<PermohonanKertasKandungan> senaraiJenisPermohonan;
    private String bilangan;
    private String taskId;
    private Task task = null;
    private String tarikhMesyuarat;
    private int checkValue;



    @DefaultHandler
    public Resolution showForm() {
        if(!stageId.equals("sediajkbbrekodkpsn")){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_PelanKuota.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_PelanKuota.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        senaraiJenisPermohonan = new ArrayList<PermohonanKertasKandungan>();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hp = permohonan.getSenaraiHakmilik().get(0);        
        listPemohon = permohonan.getSenaraiPemohon();
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if(kertasP.getKodDokumen()!=null && kertasP.getKodDokumen().getKod().equals("KRP")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        
                        if (kertasK.getBil() == 1) {
                            komponenPembangunan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            kuota = kertasK.getKandungan();
                        } 
                       
                        if (kertasP!=null ){ 
                            bilangan = kertasP.getNomborRujukan(); 
                            if(kertasP.getTarikhSidang() != null)
                                 tarikhMesyuarat =sdf.format(kertasP.getTarikhSidang());
                        }
                    }
                    senaraiJenisPermohonan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(),8);
                    if(senaraiJenisPermohonan!=null && !senaraiJenisPermohonan.isEmpty()){
                        for(int j=0;j<senaraiJenisPermohonan.size();j++){          
                          PermohonanKertasKandungan kertasKand= senaraiJenisPermohonan.get(j);
                          LOG.info((j+1)+" --- "+kertasKand.getKandungan());
                          if(j>7 && (j+1)%6==0){
                              LOG.info("-----Loop1-----------");
                              if(kertasKand.getKandungan()!=null && !kertasKand.getKandungan().equals(" ")){                                 
                                 checkValue = j-6;
                                 LOG.info("-----Loop2------checkValue-----:"+checkValue);
                                 break;
                              }
                          }
                        }
                    }
                }//if
                             
            }
        }
        
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
        // testing purpose
//        stageId = "sediacdgnpelankuota";
    }


        public Resolution simpan() throws ParseException{

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        
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

        if (komponenPembangunan == null || komponenPembangunan.equals("")){
            komponenPembangunan = " - ";
        }
        if (kuota == null || kuota.equals("")) {
            kuota = " - ";
        }
      
        listUlasan.add(komponenPembangunan);
        listUlasan.add(kuota);

       
        System.out.println("---------listUlasan--Size----" + listUlasan.size());


        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                    if(!stageId.equals("sediajkbbrekodkpsn")){
                        if (kertasKandungan.getBil() == 1) {
                            kertasKandungan.setKandungan(komponenPembangunan);
                        } else if (kertasKandungan.getBil() == 2) {
                            kertasKandungan.setKandungan(kuota);
                        } 
                         permohonanKertas.setNomborRujukan(bilangan);
                    }
                   

                      
//                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
//                    permohonanKertas.setPermohonan(permohonan);
//                    permohonanKertas.setTajuk("KERTAS RINGKAS PERTIMBANGAN JKBB PELAN KUOTA");
                     //Date tarikhMesyuarat = (Date)formatter.parse(tarikhMesyuarat);
                    
                    if(tarikhMesyuarat!=null && !tarikhMesyuarat.equals("")){
                    permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                    }
                    permohonanKertas.setInfoAudit(infoAudit);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
//                String jabatan = (String) listSubtajuk.get(i);

                System.out.println("-----ulasan----:" + ulasan);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setNomborRujukan(bilangan);
                if(tarikhMesyuarat!=null && !tarikhMesyuarat.equals("")){
                    permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                }
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KERTAS RINGKAS PERTIMBANGAN JKBB PELAN KUOTA");
                permohonanKertas.setKodDokumen(kodDokumenDAO.findById("KRP"));
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(i + 1);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }
        if(!stageId.equals("sediajkbbrekodkpsn")){
        senaraiJenisPermohonan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),8);
        int kira = Integer.parseInt(getContext().getRequest().getParameter("count8"));        
        kira = kira *6;
        LOG.info("----------count*6--------:"+kira);
        for (int i = 1; i <= kira; i++) {
             LOG.info("----------i--------:"+i);
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
            if (senaraiJenisPermohonan.size() != 0 && i <= senaraiJenisPermohonan.size()) {
                Long id = senaraiJenisPermohonan.get(i - 1).getIdKandungan();
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
            permohonanKertasKandungan1.setBil(8);
            String kandungan = getContext().getRequest().getParameter("8." + i);
            
            if(kandungan == null || kandungan.equals("")){            
                kandungan = " ";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("8." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        
        senaraiJenisPermohonan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(),8);
        if(senaraiJenisPermohonan!=null && !senaraiJenisPermohonan.isEmpty()){
            for(int j=0;j<senaraiJenisPermohonan.size();j++){          
              PermohonanKertasKandungan kertasKand= senaraiJenisPermohonan.get(j);
              LOG.info((j+1)+" --- "+kertasKand.getKandungan());
              if(j>7 && (j+1)%6==0){
                  LOG.info("-----Loop1-----------");
                  if(kertasKand.getKandungan()!=null && !kertasKand.getKandungan().equals(" ")){                                 
                     checkValue = j-6;
                     LOG.info("-----Loop2------checkValue-----:"+checkValue);
                     break;
                  }
              }
            }
        }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        
        if(!stageId.equals("sediajkbbrekodkpsn")){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new RedirectResolution(KertasRingkasPertimbanganJKBBActionBean.class,"showForm");
        
        //getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        //return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_PelanKuota.jsp").addParameter("tab", "true");
    }


     public Resolution deleteSingle() {

         try{
         List<PermohonanKertasKandungan> senaraiKand =new ArrayList<PermohonanKertasKandungan>();
         senaraiKand = devService.findKertasKandByIdKertasDesc(kertasK.getKertas().getIdKertas(),8);
         if(!senaraiKand.isEmpty()){          
             for(int i=0;i<6;i++){
                LOG.info("---------senaraiKand.get(i)-------"+senaraiKand.get(i));
                 devService.deleteKertasKandungan(senaraiKand.get(i));
             }
         }
         }catch(Exception e){
             e.printStackTrace();
         }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasRingkasPertimbanganJKBBActionBean.class, "locate");
    }
   

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getKomponenPembangunan() {
        return komponenPembangunan;
    }

    public void setKomponenPembangunan(String komponenPembangunan) {
        this.komponenPembangunan = komponenPembangunan;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
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

    public List<PermohonanKertasKandungan> getSenaraiJenisPermohonan() {
        return senaraiJenisPermohonan;
    }

    public void setSenaraiJenisPermohonan(List<PermohonanKertasKandungan> senaraiJenisPermohonan) {
        this.senaraiJenisPermohonan = senaraiJenisPermohonan;
    }

    public String getBilangan() {
        return bilangan;
    }

    public void setBilangan(String bilangan) {
        this.bilangan = bilangan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }
   
    public int getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(int checkValue) {
        this.checkValue = checkValue;
    }
}

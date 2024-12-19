/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.LanjutanBayaran;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sitifariza.hanim
 */

         
@UrlBinding("/pelupusan/rayuan")
public class PermohonanRAYTActionBean extends AbleActionBean {
    
    private static final Logger logger = Logger.getLogger(PermohonanRAYTActionBean.class);
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    private String idPermohonan;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum ;
    private HakmilikPermohonan hakmilikPermohonan;
    private BigDecimal kadarPremium;
    private BigDecimal cukaiPerMeterPersegi;
    private Integer tempoh ;
    private String sebab;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanTuntutanKos permohonanTuntutanKosSemasa;
    private LanjutanBayaran lanjutBayaran ;
    private boolean editPTD;
    private String kodNegeri;
    private String stageId ;
    private boolean simpan=false;

    @DefaultHandler
    public Resolution showForm() {
       return new JSP("pelupusan/rayuan_RAYT.jsp").addParameter("tab", "true");
    }

    public Resolution premium() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getKodUrusan().getKod().equals("RAYK")){
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                  }
                }
              }
              if(permohonan.getKodUrusan().getKod().equals("RYKN")){
                  permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                 if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                 }
               }
               editPTD = Boolean.FALSE;
              }
        }
          getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }
    
    public Resolution premiumView() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getKodUrusan().getKod().equals("RAYK")){
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                  }
                }
              }
              if(permohonan.getKodUrusan().getKod().equals("RYKN")){
                  permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                 if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                 }
               }
               editPTD = Boolean.FALSE;
              }
        }
          getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }
    
    public Resolution premiumPTD() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getKodUrusan().getKod().equals("RAYK")){
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                  }
                }
              }
              if(permohonan.getKodUrusan().getKod().equals("RYKN")){
                 permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                 if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                 }
               }
               editPTD = Boolean.TRUE;
              }
        }
          getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }
    
    public Resolution premiumViewPTD() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getKodUrusan().getKod().equals("RAYK")){
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                  }
                }
              }
              if(permohonan.getKodUrusan().getKod().equals("RYKN")){
                  permohonanTuntutanKosSemasa = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DISPRM");
                 if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
                  if (permohonanSebelum != null) {
                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                          addSimpleError("Tiada Lagi Bayaran Premium");
                      }
                 }
               }
               editPTD = Boolean.TRUE;
              }
        }
          getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
    }

    public Resolution lanjutan_tempoh() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
//                  if (permohonanSebelum != null) {
//                      permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
//                      if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
//                          addSimpleError("Tiada Lagi Bayaran Premium");
//                      }
//                  }
              }
        }
          getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }
    
     public Resolution lanjutan_tempohView() {
        
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
          if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            
              if (permohonan.getPermohonanSebelum() != null) {
                  permohonanSebelum = permohonan.getPermohonanSebelum();
              }
        }
           getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }
     
    public Resolution showFormPTD() {
//        edit = Boolean.FALSE;
//        edit = Boolean.TRUE;
//        ptd = Boolean.FALSE;
//        openPTG = Boolean.TRUE;
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
            if (permohonanSebelum != null) {
                permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
                if (permohonanTuntutanKos.getAmaunTuntutan() == null) {
                    addSimpleError("Tiada Lagi Bayaran Premium");
                }
            }
        }
        editPTD = Boolean.TRUE;
//        editPTG = Boolean.TRUE;
        return new JSP("pelupusan/rayuan_lanjut_tempoh_bayaran.jsp").addParameter("tab", "true");
    }

 @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        stageId="002_Makluman";
        logger.info("stageId : "+stageId);
        if(stageId.equals("01DaftarPermohonan") || stageId.equals("02SmkKrtsdanSedHuraianSyor") || stageId.equals("001_Kemasukan") || stageId.equals("kemasukan")){
            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        }else{
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        }
               
        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
//            if(permohonan.getPermohonanSebelum() != null){
//                hakmilikPermohonan = 
//            }
//            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        }
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if(stageId.equals("02KmsknAduan"))
        {
            sebab = getContext().getRequest().getParameter("permohonan.sebab").toLowerCase();
        }
        else
        {
            sebab = getContext().getRequest().getParameter("permohonan.sebab");
        }
        
        logger.info("-------------sebab---------------------" + sebab);
        permohonan.setSebab(sebab);

        pelupusanService.simpanPermohonan(permohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/rayuan_RAYT.jsp").addParameter("tab", "true");

    }

    public Resolution simpanLanjut() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        lanjutBayaran = pelupusanService.findLanjutBayaranByIdMohon(idPermohonan);
//        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        if(lanjutBayaran != null){
//        InfoAudit ia = lanjutBayaran.getInfoAudit();   
//            ia.setDiKemaskiniOleh(pengg);
//            ia.setTarikhKemaskini(new java.util.Date());
//            lanjutBayaran.setInfoAudit(ia);
//        
//        }
//        else{     
//            InfoAudit ia = new InfoAudit();
//            ia.setDimasukOleh(pengg);
//            ia.setTarikhMasuk(new Date());
//            lanjutBayaran.setInfoAudit(ia);
//        
//        }
//    
//        lanjutBayaran.setPermohonan(permohonan);
//        java.math.BigDecimal s1 = (BigDecimal)hakmilikPermohonan.getCukaiPerMeterPersegi();
//        hakmilikPermohonan.setCukaiPerMeterPersegi(s1);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }
    
        tempoh = (Integer)permohonan.getTempohBulan();
        logger.info(tempoh);
        permohonan.setTempohBulan(tempoh);
        pelupusanService.simpanPermohonan(permohonan);
        
        logger.debug("tess2 :" + permohonan.getIdPermohonan());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return lanjutan_tempoh();

    }

    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if(permohonan != null)
        permohonanSebelum = permohonan.getPermohonanSebelum();
        if(permohonan.getPermohonanSebelum() != null)
        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
//        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        String kadarRayuan = permohonan.getNoMahkamah();
//        if (permohonan.getKodUrusan().getKod().equals("RYKN")){
//            if (editPTD){
//                if(kadarRayuan.equals("N")){
//                    if (permohonan.getNilaiDipohon() == null){
//                        kadarPremium = BigDecimal.ZERO;
//                        kadarPremium = (BigDecimal)permohonan.getNilaiDipohon();
//                    }
//                }
//            }
//        }
//        else {
            if(kadarRayuan != null){
                if(kadarRayuan.equals("0.25") || kadarRayuan.equals("0.5")){
                    logger.info(kadarRayuan);
                    double test = Double.parseDouble(kadarRayuan) ;
                    logger.info(test);
                    BigDecimal a = BigDecimal.valueOf(test);
                    logger.info(a);
                    kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().multiply(a);
                     kadarPremium = permohonanTuntutanKos.getAmaunTuntutan().subtract(kadarPremium);
                }
                else if(kadarRayuan.equals("N")){
                    kadarPremium = (BigDecimal)permohonan.getNilaiDipohon();
                 }
//            }
        }
        logger.info(kadarPremium);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }
    

       if (permohonan.getKodUrusan().getKod().equals("RYKN")){
           if (editPTD){
                logger.info(kadarPremium);
                permohonan.setNilaiDipohon(kadarPremium);
           }
       }
       else {
            logger.info(kadarPremium);
            permohonan.setNilaiDipohon(kadarPremium);
       }
       
       BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
            if(permohonan.getKodUrusan().getKod().equals("RYKN")){
                if (stageId.equals("03SemakSyorKertas")) {
                    pelupusanService.simpanPermohonan(permohonan);
                    addSimpleMessage("Maklumat telah berjaya disimpan.");
                    return premiumPTD();
                }
                else {
                    pelupusanService.simpanPermohonan(permohonan);
                    addSimpleMessage("Maklumat telah berjaya disimpan.");
                    return premium() ;
                }
                     
            }
            
            else if(!permohonan.getKodUrusan().getKod().equals("RYKN")){
                logger.info(kadarPremium);
                if(kadarPremium != null)
                    permohonan.setNilaiDipohon(kadarPremium);
                else
                    permohonan.setNilaiDipohon(null);
                pelupusanService.simpanPermohonan(permohonan);
                addSimpleMessage("Maklumat telah berjaya disimpan.");
                return premium() ;
            }
        
     //   return new JSP("pelupusan/rayuan_pengurangan_premium.jsp").addParameter("tab", "true");
      
        return premium() ;
    }


    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(BigDecimal kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public BigDecimal getCukaiPerMeterPersegi() {
        return cukaiPerMeterPersegi;
    }

    public void setCukaiPerMeterPersegi(BigDecimal cukaiPerMeterPersegi) {
        this.cukaiPerMeterPersegi = cukaiPerMeterPersegi;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public LanjutanBayaran getLanjutBayaran() {
        return lanjutBayaran;
    }

    public void setLanjutBayaran(LanjutanBayaran lanjutBayaran) {
        this.lanjutBayaran = lanjutBayaran;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKosSemasa() {
        return permohonanTuntutanKosSemasa;
    }

    public void setPermohonanTuntutanKosSemasa(PermohonanTuntutanKos permohonanTuntutanKosSemasa) {
        this.permohonanTuntutanKosSemasa = permohonanTuntutanKosSemasa;
    }
    
    


    
}
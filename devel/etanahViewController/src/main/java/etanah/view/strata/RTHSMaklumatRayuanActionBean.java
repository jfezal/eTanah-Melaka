/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Murali
 */

@UrlBinding("/strata/rthsmaklumat_rayuan")
public class RTHSMaklumatRayuanActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService mohonSericevvice;
    @Inject
    PembangunanService devService;
    private String tempohlanjutan;
    private String lanjutantempo;
    private Permohonan permohonan;
    private Integer tempohHari;
    private String sebab;
    private String idPermohonan;


    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(Integer tempohHari) {
        this.tempohHari = tempohHari;
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }


    public String getLanjutantempo() {
        return lanjutantempo;
    }

    public void setLanjutantempo(String lanjutantempo) {
        this.lanjutantempo = lanjutantempo;
    }

    public String getTempohlanjutan() {
        return tempohlanjutan;
    }

    public void setTempohlanjutan(String tempohlanjutan) {
        this.tempohlanjutan = tempohlanjutan;
    }

    public Resolution editPermohonanPopup(){
        idPermohonan=(String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("strata/Lanjutan_Tempo_Mohon/edit_maklumatrayuan.jsp").addParameter("popup", "true");
    }

    @DefaultHandler
    public Resolution showForm() {

        idPermohonan=(String)getContext().getRequest().getSession().getAttribute("idPermohonan");
       // tempohHari=(Integer)getContext().getRequest().getSession().getAttribute("tempohHari");
       // sebab=(String)getContext().getRequest().getSession().getAttribute("sebab");

         permohonan = permohonanDAO.findById(idPermohonan);
         tempohHari = permohonan.getTempohHari();
         sebab = permohonan.getSebab();

         stageId = "keputusanPTG";
         Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
          BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            String idPermohonanTerdahulu = (String) getContext().getRequest().getParameter("idPermohonanTerdahulu");
            String idPermohonanTerdahulu2=(String)getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        if(idPermohonanTerdahulu != null && !idPermohonanTerdahulu.trim().equals("")||(idPermohonanTerdahulu2!=null)){
        permohonan = permohonanDAO.findById(idPermohonanTerdahulu2);
        if(permohonan==null){
             addSimpleError("Id Permohonan ini tidak di dalam pangkalan data.");
                getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
              return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
        }
            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu2, stageId);
             System.out.println("FASA PERMOHONAN"+fp);
            if(fp!=null){
                addSimpleError("IDPermohonan ini telah diproses");
                getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
              return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
            }

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
            }

            if(stageId.equals("semakdrafRayuan")){
                getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }else{
                getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
            }
       return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
    }
        else{
                addSimpleError("Sila masukkan ID Permohonan terdahulu.");
                getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.FALSE);
              return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
        }
    }

    public Resolution showForm2(){
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

         permohonan = permohonanDAO.findById(idPermohonan);


         getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
    }

        // old code for maklumat_rayuan
    
//    public Resolution simpan(){
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        getContext().getRequest().getSession().removeAttribute("tempohHari");
//        getContext().getRequest().getSession().removeAttribute("sebab");
//
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(peng);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//
//        permohonan = permohonanDAO.findById(idPermohonan);
//
//        permohonan.setTempohHari(tempohHari);
//        permohonan.setSebab(sebab);
//        permohonan.setInfoAudit(infoAudit);
//
//        boolean flag=true;
//        if(tempohHari !=null && tempohHari!=0 && sebab != null){
//            getContext().getRequest().getSession().setAttribute("tempohHari", tempohHari);
//            getContext().getRequest().getSession().setAttribute("sebab", sebab);
//            mohonSericevvice.saveOrUpdate(permohonan);
//        }else if(tempohHari ==null || tempohHari ==0){
//            flag=false;
//            addSimpleError("Sila masukkan Lanjutan Yang Dipohon.");
//        }else if(sebab == null){
//            flag=false;
//            addSimpleError(" Sila masukkan sebab-sebab lanjutan tempoh.");
//        }
//
//        if(flag)
//         addSimpleMessage("Maklumat Permohonan berjaya disimpan.");
//
//        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
//        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//
//       return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
//    }


    
    public Resolution simpan(){

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        getContext().getRequest().getSession().removeAttribute("tempohHari");
        getContext().getRequest().getSession().removeAttribute("sebab");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        permohonan = permohonanDAO.findById(idPermohonan);

        permohonan.setTempohHari(tempohHari);
        permohonan.setSebab(sebab);
        permohonan.setInfoAudit(infoAudit);

        boolean flag=true;
        if(tempohHari ==null || tempohHari==0){
            flag=false;
            addSimpleError("Sila masukkan Lanjutan Yang Dipohon.");
        }

        if(tempohHari!=null && tempohHari!=0){
            try{
                if(tempohHari>90){
                    permohonan.setTempohHari(0);
                    permohonan=mohonSericevvice.saveOrUpdate(permohonan);
                    flag=false;
                    addSimpleError("Lanjutan tempoh mesti tidak kurang dari 90 hari.");
                }
            }catch(Exception e){
                flag=false;
                addSimpleError("sila masukkan nombor.");
            }
        }else{
            flag=false;
            addSimpleError("Sila masukkan Lanjutan Yang Dipohon.");
        }
        if(sebab == null){
            permohonan.setSebab(null);
            permohonan=mohonSericevvice.saveOrUpdate(permohonan);
            flag=false;
            addSimpleError(" Sila masukkan sebab-sebab lanjutan tempoh.");
        }
        if(flag)
            addSimpleMessage("Maklumat Permohonan berjaya disimpan.");

        if(tempohHari!=null)
            getContext().getRequest().getSession().setAttribute("tempohHari", tempohHari);

        if(sebab !=null)
            getContext().getRequest().getSession().setAttribute("sebab", sebab);

         System.out.println("------tempoharii-------"+tempohHari);
         System.out.println("------sebab-------"+sebab);
        if(tempohHari==null){
             permohonan.setTempohHari(0);
        }else if(tempohHari>90 || tempohHari==0){
             System.out.println("------tempoharii-------"+tempohHari);
             System.out.println("------sebab-------"+sebab);
               permohonan.setTempohHari(0);
         }else{
            permohonan.setTempohHari(tempohHari);
         }
        permohonan.setSebab(sebab);
        permohonan=mohonSericevvice.saveOrUpdate(permohonan);

        if(tempohHari!=null && tempohHari!=0 && tempohHari<=90 && sebab !=null ){

        permohonan=mohonSericevvice.saveOrUpdate(permohonan);

        permohonan = permohonanDAO.findById(idPermohonan);
        tempohHari = permohonan.getTempohHari();
        sebab = permohonan.getSebab();

         getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
         getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
         }else{
             getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
             getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//            return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
         }

        permohonan = permohonanDAO.findById(idPermohonan);
        tempohHari = permohonan.getTempohHari();
        sebab = permohonan.getSebab();
     return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");
    }




     public Resolution update(){

         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        getContext().getRequest().getSession().removeAttribute("tempohHari");
        getContext().getRequest().getSession().removeAttribute("sebab");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        permohonan = permohonanDAO.findById(idPermohonan);

        permohonan.setTempohHari(tempohHari);
        permohonan.setSebab(sebab);
        permohonan.setInfoAudit(infoAudit);

        boolean flag=true;
        if(tempohHari!=null && tempohHari!=0){
            try{
                if(tempohHari>90){
                    flag=false;
                    addSimpleError("Lanjutan tempoh mesti tidak kurang dari 90 hari.");
                }
            }catch(Exception e){
                flag=false;
                addSimpleError("sila masukkan nombor.");
            }
        }else{
            flag=false;
            addSimpleError("Sila masukkan Lanjutan Yang Dipohon.");
        }
        if(sebab == null){
            flag=false;
            addSimpleError(" Sila masukkan sebab-sebab lanjutan tempoh.");
        }
        if(flag)
             addSimpleMessage("Sukses pelaksanaan maklumat terkini.");

        if(tempohHari!=null)
            getContext().getRequest().getSession().setAttribute("tempohHari", tempohHari);

        if(sebab !=null)
            getContext().getRequest().getSession().setAttribute("sebab", sebab);

        if(tempohHari!=null && tempohHari!=0 && tempohHari<=90 && sebab !=null ){
        permohonan=mohonSericevvice.saveOrUpdate(permohonan);
         getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
         getContext().getRequest().setAttribute("edit", Boolean.FALSE);
//        return new JSP("strata/Lanjutan_Tempo_Mohon/maklumat_rayuan.jsp").addParameter("tab", "true");
         }else{
             getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
             getContext().getRequest().setAttribute("edit", Boolean.FALSE);
         }

        permohonan = permohonanDAO.findById(idPermohonan);
        tempohHari = permohonan.getTempohHari();
        sebab = permohonan.getSebab();

     return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_rayuan.jsp").addParameter("tab", "true");

    }

}


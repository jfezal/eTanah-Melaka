
package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/laporan_sempadan_po")
public class LaporanSempadanActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    private LaporanTanahSempadan laporanTanahSempadan;
    private InfoAudit ia;
    private Pengguna peng;
    private Hakmilik hakmilik;
    private String milik;
    private String hakmilik_ref;
    private String edit;
    private String idPermohonan;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private List listLaporTanahSpdnU = new ArrayList();
    private List listLaporTanahSpdnB = new ArrayList();
    private List listLaporTanahSpdnS = new ArrayList();
    private List listLaporTanahSpdnT = new ArrayList();
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    private String idKandungan;
    @DefaultHandler
    public Resolution showForm() {
     
       if(getIdKandungan() != null){
           laporanTanahSempadan = laporanTanahSempadanDAO.findById(new Long(getIdKandungan()));
           hakmilik_ref = laporanTanahSempadan.getHakmilik().getIdHakmilik();
       }

        return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }

    public Resolution sempadanSimpanan(){
            //System.out.println("laporanTanahSempadan.getHakmilik()---"+getContext().getRequest().getParameter("hakmilik"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        laporanTanah = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);
        ia = new InfoAudit();
            LaporanTanahSempadan laporanTanahSempadanRef = new LaporanTanahSempadan();
            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            laporanTanahSempadanRef.setInfoAudit(ia);
            LaporanTanah lt = new LaporanTanah();
            lt = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);

            if(lt==null){

                lt.setInfoAudit(ia);
                lt.setPermohonan(permohonan);
                pelupusanService.simpanLaporanTanah(lt);
            }
            lt = new LaporanTanah();
            lt = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);

            milik = getHakmilik_ref();
            if(milik == null){milik = "";}
            hakmilik = hakmilikDAO.findById(milik);
            if(hakmilik != null){
               laporanTanahSempadanRef.setHakmilik(hakmilik);
               laporanTanahSempadanRef.setJenisSempadan(laporanTanahSempadan.getJenisSempadan());
               laporanTanahSempadanRef.setKeadaanTanah(laporanTanahSempadan.getKeadaanTanah());
               laporanTanahSempadanRef.setCatatan(laporanTanahSempadan.getCatatan());
               laporanTanahSempadanRef.setMilikKerajaan(laporanTanahSempadan.getMilikKerajaan());
               laporanTanahSempadanRef.setLaporanTanah(lt);
               laporanTanahSempadanRef.setGuna("kegunaan tanah");
               pelupusanService.saveOrUpdateSempadan(laporanTanahSempadanRef);
               edit = "1";
               laporanTanahSempadan = new LaporanTanahSempadan();
            }else{
                addSimpleError("Hakmilik No. Ini adalah tidak sah");
                edit = "2";
            }

            
            return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");
    }

    public Resolution updateKandungan(){


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
           milik = getHakmilik_ref();
           if(milik == null){milik = "";}
           hakmilik = hakmilikDAO.findById(milik);
            LaporanTanah lt = new LaporanTanah();
            lt = new LaporanTanah();
            lt = pelupusanService.findLaporanTanahByIdPermohonan(idPermohonan);

            if(lt!=null){
                if(hakmilik!=null){
                    LaporanTanahSempadan lts = new LaporanTanahSempadan();

                    ia = new InfoAudit();
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                        lts = laporanTanahSempadanDAO.findById(laporanTanahSempadan.getIdLaporTanahSpdn());
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setTarikhMasuk(lts.getInfoAudit().getTarikhMasuk());
                        ia.setDimasukOleh(lts.getInfoAudit().getDimasukOleh());

                    }else{
                        ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    lts.setInfoAudit(ia);
                    lts.setLaporanTanah(lt);
                    lts.setHakmilik(hakmilik);
                    lts.setGuna("kegunaan tanah");
                    lts.setJenisSempadan(laporanTanahSempadan.getJenisSempadan());
                    lts.setKeadaanTanah(laporanTanahSempadan.getKeadaanTanah());
                    lts.setCatatan(laporanTanahSempadan.getCatatan());
                    lts.setMilikKerajaan(laporanTanahSempadan.getMilikKerajaan());
                    if(laporanTanahSempadan.getIdLaporTanahSpdn() > 0){
                    //lts.setIdLaporTanahSpdn(laporanTanahSempadan.getIdLaporTanahSpdn());
                    edit = "3";
                    }else{
                        edit = "1";
                    }
                    pelupusanService.simpanLaporanTanahSempadan(lts);
                    
                    laporanTanahSempadan = new LaporanTanahSempadan();
                }else{
                addSimpleError("Hakmilik No. Ini adalah tidak sah");
                edit = "2";
               }

            }

                 return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("tab", "true");

 
    }



     // click on edit button
    public Resolution showEditSempadan() {
        laporanTanahSempadan = new LaporanTanahSempadan();
        hakmilik_ref = "";
        setHakmilik_ref("");
        return new JSP("pelupusan/laporan_sempadan_popup.jsp").addParameter("popup", "true");
    }
   public Resolution closeWindow() {
       return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate");
       //return new RedirectResolution(LaporanTanahTemplateActionBean.class, "locate").addParameter("noLaporan", "1");
    }

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public String getHakmilik_ref() {
        return hakmilik_ref;
    }

    public void setHakmilik_ref(String hakmilik_ref) {
        this.hakmilik_ref = hakmilik_ref;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }






}

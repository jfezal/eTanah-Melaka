/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanService;
//import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.PemohonanRayuanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanRayuan;
import etanah.service.CalcTax;
import etanah.service.NotifikasiService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/rayuan/maklumatPermohonanRayuan")
public class MaklumatPermohonanRayuanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatPermohonanRayuanActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    PermohonanService permohonanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PemohonanRayuanDAO permohonanRayuanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;


    private HakmilikPermohonan hakHakmilikPermohonan;
    private List<HakmilikPermohonan> listhakHakmilikPermohonan1;
    private List<HakmilikPermohonan> list = new ArrayList<HakmilikPermohonan>();
    private Permohonan permohonanSblm;
    private Permohonan permohonan;
    private String idMohonSebelum;
    private Hakmilik hakmilik;
    private String idPermohonan;
    private String idMHSblm;
    private boolean popap;
    private Integer sizeList;
    private String hakmilikBaru;
    private boolean tambahHakmilik;
    private List<PermohonanRayuan> listPermohonanRayuan;
    private List<PermohonanRayuan> listMhnRayuanIdSblm;
    private String idRayuan;
    private PermohonanRayuan permohonanRayuan;
    private List<Permohonan> listpermohonanIdSblm;
    private List<PermohonanRayuan> listRY = new ArrayList<PermohonanRayuan>();
    private List<Map> listAllRY = new ArrayList<Map>();

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idMHSblm == null) {
            permohonanSblm = permohonanService.findById(idPermohonan);
            listhakHakmilikPermohonan1 = hakmilikPermohonanService.findByIdPermohonanOnly(permohonanSblm.getIdPermohonan());
            
            //get list rayuan
            listPermohonanRayuan = permohonanService.findRayuanByIdmohon(idPermohonan);
          
        }
        
        return new JSP("pembangunan/rayuan/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        listhakHakmilikPermohonan1 = hakmilikPermohonanService.findByIdPermohonanOnly(idPermohonan);
         if (idMHSblm != null) {
            if (permohonan.getPermohonanSebelum() != null) {
                tambahHakmilik = Boolean.FALSE;
            }else{
                tambahHakmilik = Boolean.TRUE;
            }
        }
        else{
            tambahHakmilik = Boolean.TRUE;
        }
    }

    public Resolution search() {
//        String idMHSblm = getContext().getRequest().getParameter("ID_MH_SBLM");

        LOG.info("idMHSblm>>>>>>>" + idMHSblm);

        if (idMHSblm != null) {
            //hakHakmilikPermohonan = hakmilikPermohonanService.findIdhakmilikbyIdPermohonanENF(idMHSblm);
            permohonanSblm = permohonanService.findById(idMHSblm);
            if(permohonanSblm != null){
                 listhakHakmilikPermohonan1 = hakmilikPermohonanService.findByIdPermohonanOnly(permohonanSblm.getIdPermohonan());
                
            }
             listpermohonanIdSblm = permohonanService.findRayuanByIdmohonSebelum(idMHSblm);
                for(Permohonan p : listpermohonanIdSblm){                 
                    listMhnRayuanIdSblm = permohonanService.findRayuanByIdmohon(p.getIdPermohonan());
                     listRY.addAll(listMhnRayuanIdSblm);
                }
             listPermohonanRayuan = permohonanService.findRayuanByIdmohon(idPermohonan);     
           
             tambahHakmilik = Boolean.FALSE;
                
             if (permohonan.getPermohonanSebelum() != null) {
                tambahHakmilik = Boolean.FALSE;
            }else{
                tambahHakmilik = Boolean.TRUE;
            }
        }else{
            tambahHakmilik = Boolean.TRUE;
        }
        //return showForm();
   return new JSP("pembangunan/rayuan/maklumat_permohonan.jsp").addParameter("tab", "true");
//        return new RedirectResolution(MaklumatPermohonanActionBean.class, "showForm").addParameter("tab", "true");
    }

    public Resolution saveRayuan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        // String idMHSblm = getContext().getRequest().getParameter("idMHSblm");
        String catatan = getContext().getRequest().getParameter("catatan");
        String JnsRayuan = getContext().getRequest().getParameter("JnsRayuan");
        String rujDaerah = getContext().getRequest().getParameter("rujDaerah");
        String rujPTG = getContext().getRequest().getParameter("rujPTG");
        String bilRayuan = getContext().getRequest().getParameter("bilRayuan");
        
        LOG.info("catatan>>>>>>>" + catatan);
        LOG.info("idPermohonan>>>>>>>" + idPermohonan);
        LOG.info("idMHSblm>>>>>>>" + idMHSblm);
        
        //update mohon table
        InfoAudit ia = new InfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
        if(idMHSblm != null){
            permohonanSblm = permohonanService.findById(idMHSblm);
            permohonan.setPermohonanSebelum(permohonanSblm);
            
            permohonan = permohonanService.findById(idPermohonan);
            permohonan.setInfoAudit(ia);
            permohonanService.saveOrUpdate(permohonan);
        }
        


        //insert mohon hakmilik
        listhakHakmilikPermohonan1 = hakmilikPermohonanService.findByIdPermohonanOnly(idMHSblm);
        for (HakmilikPermohonan hplist : listhakHakmilikPermohonan1) {

            HakmilikPermohonan hp = new HakmilikPermohonan();
            InfoAudit ia1 = new InfoAudit();
                ia1.setTarikhMasuk(new java.util.Date());
                ia1.setDimasukOleh(peng);
            hp.setHakmilik(hplist.getHakmilik());
            hp.setPermohonan(permohonan);
            hp.setLuasTerlibat(hplist.getLuasTerlibat());
            hp.setKodUnitLuas(hplist.getKodUnitLuas());
            hp.setKodHakmilik(hplist.getKodHakmilik());
            hp.setNoLot(hplist.getNoLot());
            hp.setInfoAudit(ia1);
          
            hakmilikPermohonanService.save(hp);

        }
        
        //insert rayuan
//        listPermohonanRayuan = permohonanService.findRayuanByIdmohon(idPermohonan);
//        for (PermohonanRayuan rylist : listPermohonanRayuan) {
            
            PermohonanRayuan rayuan = new PermohonanRayuan();
            
                InfoAudit ia2 = new InfoAudit();
                    ia2.setTarikhMasuk(new java.util.Date());
                    ia2.setDimasukOleh(peng);
                rayuan.setInfoAudit(ia2);
                rayuan.setBilRayuan(Integer.valueOf(bilRayuan));
                rayuan.setCatatan(catatan);
                rayuan.setJenisRayuan(JnsRayuan);
                rayuan.setNoRujFilePTD(rujDaerah);
                rayuan.setNoRujFilePTG(rujPTG);
                rayuan.setPermohonan(permohonan);
                
                permohonanService.saveOrUpdate(rayuan);
        //}

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return showForm();
    }

    public Resolution saveMH() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        // String idMHSblm = getContext().getRequest().getParameter("idMHSblm");
        String catatan = getContext().getRequest().getParameter("catatanMH");

        InfoAudit ia = new InfoAudit();
         ia.setTarikhMasuk(new java.util.Date());
         ia.setDimasukOleh(peng);
//        LOG.info("catatan @ MH not exist in table>>>>>>>" + catatan);
//        LOG.info("idPermohonan @ MH not exist in table>>>>>>>" + idPermohonan);
        permohonan.setIdPermohonan(idPermohonan);
        permohonan.setCatatan(catatan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp.setPermohonan(permohonan);
        hp.setInfoAudit(ia);
        hakmilikPermohonanService.save(hp);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return showForm();
    }

    public Resolution saveHM() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikBaru = getContext().getRequest().getParameter("idHM");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        hakmilik = hakmilikDAO.findById(hakmilikBaru);
        permohonan = permohonanService.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
         ia.setTarikhMasuk(new java.util.Date());
         ia.setDimasukOleh(peng);
         
        if (hakmilik != null) {
            HakmilikPermohonan hp = new HakmilikPermohonan();
             hp.setPermohonan(permohonan);
             hp.setHakmilik(hakmilik);
             hp.setInfoAudit(ia);
            hakmilikPermohonanService.save(hp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            return showForm();
        } else {
            addSimpleError("Maklumat Hakmilik Tidak Dijumpai.");
            return showForm();
        }

    }

    public Resolution kemaskiniUlasanPopup() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        //String idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        //hakHakmilikPermohonan = hakmilikPermohonanService.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        popap = true;

        return new JSP("pembangunan/rayuan/popup_hakmilik.jsp").addParameter("popup", true);
    }
    
    public Resolution deleteRayuan(){
        idRayuan = getContext().getRequest().getParameter("idRayuan");
        permohonanRayuan = permohonanRayuanDAO.findById(Long.valueOf(idRayuan));
        
        permohonanService.deletePermohonanRayuan(permohonanRayuan);
        
        return showForm();
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public HakmilikPermohonan getHakHakmilikPermohonan() {
        return hakHakmilikPermohonan;
    }

    public void setHakHakmilikPermohonan(HakmilikPermohonan hakHakmilikPermohonan) {
        this.hakHakmilikPermohonan = hakHakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getListhakHakmilikPermohonan1() {
        return listhakHakmilikPermohonan1;
    }

    public void setListhakHakmilikPermohonan1(List<HakmilikPermohonan> listhakHakmilikPermohonan1) {
        this.listhakHakmilikPermohonan1 = listhakHakmilikPermohonan1;
    }

    public String getIdMohonSebelum() {
        return idMohonSebelum;
    }

    public void setIdMohonSebelum(String idMohonSebelum) {
        this.idMohonSebelum = idMohonSebelum;
    }

    public String getIdMHSblm() {
        return idMHSblm;
    }

    public void setIdMHSblm(String idMHSblm) {
        this.idMHSblm = idMHSblm;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public List<HakmilikPermohonan> getList() {
        return list;
    }

    public void setList(List<HakmilikPermohonan> list) {
        this.list = list;
    }

    public boolean isPopap() {
        return popap;
    }

    public void setPopap(boolean popap) {
        this.popap = popap;
    }

    public Integer getSizeList() {
        return sizeList;
    }

    public void setSizeList(Integer sizeList) {
        this.sizeList = sizeList;
    }

    public String getHakmilikBaru() {
        return hakmilikBaru;
    }

    public void setHakmilikBaru(String hakmilikBaru) {
        this.hakmilikBaru = hakmilikBaru;
    }

    public boolean isTambahHakmilik() {
        return tambahHakmilik;
    }

    public void setTambahHakmilik(boolean tambahHakmilik) {
        this.tambahHakmilik = tambahHakmilik;
    }

    public List<PermohonanRayuan> getListPermohonanRayuan() {
        return listPermohonanRayuan;
    }

    public void setListPermohonanRayuan(List<PermohonanRayuan> listPermohonanRayuan) {
        this.listPermohonanRayuan = listPermohonanRayuan;
    }

    public PermohonanRayuan getPermohonanRayuan() {
        return permohonanRayuan;
    }

    public void setPermohonanRayuan(PermohonanRayuan permohonanRayuan) {
        this.permohonanRayuan = permohonanRayuan;
    }

    public List<Permohonan> getListpermohonanIdSblm() {
        return listpermohonanIdSblm;
    }

    public void setListpermohonanIdSblm(List<Permohonan> listpermohonanIdSblm) {
        this.listpermohonanIdSblm = listpermohonanIdSblm;
    }

    public List<PermohonanRayuan> getListMhnRayuanIdSblm() {
        return listMhnRayuanIdSblm;
    }

    public void setListMhnRayuanIdSblm(List<PermohonanRayuan> listMhnRayuanIdSblm) {
        this.listMhnRayuanIdSblm = listMhnRayuanIdSblm;
    }

    public List<PermohonanRayuan> getListRY() {
        return listRY;
    }

    public void setListRY(List<PermohonanRayuan> listRY) {
        this.listRY = listRY;
    }

   
    
    

}

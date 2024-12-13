/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/senarai_mohon_ruj_luar_dok")
public class CarianDokumenActionBean extends AbleActionBean {
    
    private Long idruj;
    private String catat;
    private Long idFolder;
    private String idMohon;
    private String jabatan;
    private String jabatan1;
    private String catatan1;
    private String keyword;
    private Pengguna pguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CarianDokumenActionBean.class);
    private List<PermohonanRujukanLuarDokumen> rujLuarDok;
    private List<Dokumen> listAllDokumen;
    private List<Dokumen> listSearchDok;
    private List<Dokumen> listFilterDokumen = new ArrayList<Dokumen>();
    private List<Dokumen> findFolderDok;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    private CommonService service;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/senaraiRujLuarDok.jsp");
    }
    
    public Resolution findMohonRujLuarDok() throws Exception {


      idMohon = getContext().getRequest().getParameter("idMohon");
        String tempIdRuj = getContext().getRequest().getParameter("idRujukan");
        logger.debug("IdRujukan :" + tempIdRuj);
        idruj = Long.valueOf(tempIdRuj);
        jabatan = getContext().getRequest().getParameter("jabatan");
        catat = getContext().getRequest().getParameter("catatan");
        rujLuarDok = service.findPermohonanLuarDok(idruj);
        
        
        
        
        return new ForwardResolution("/WEB-INF/jsp/utiliti/senaraiRujLuarDok.jsp");
        
    }
    
    public Resolution deleteMohonRujLuarDok() throws Exception {
        
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
         jabatan = getContext().getRequest().getParameter("jabatan");
        catat = getContext().getRequest().getParameter("catatan");
        PermohonanRujukanLuarDokumen pdok = service.findDokumen(idDokumen, idRujukan);
        if(pdok!=null){
            logger.debug("Dokumen to be deleted exist. "+pdok.getDokumen().getKodDokumen());
        pelupusanService.deleteDokumen(pdok);
        }
        
         addSimpleMessage("Dokumen telah berjaya dihapuskan.");
   return new RedirectResolution(CarianDokumenActionBean.class, "findMohonRujLuarDok").addParameter("idRujukan", idRujukan).addParameter("catatan", catat).addParameter("jabatan", jabatan).addParameter("idMohon", this.getIdMohon());
        
    }
    
    public Resolution tambahMohonRujLuarDok() throws Exception {
        
         jabatan = getContext().getRequest().getParameter("jabatan");
        catat = getContext().getRequest().getParameter("catatan");
        logger.debug("Id Permohonan  :"+idMohon);
        
        findFolderDok = service.findFolDok(idMohon, null);
        //listAllDokumen =service.allDokumen();
        logger.debug("Id Rujukan :" + idruj);
        rujLuarDok = service.findPermohonanLuarDok(idruj);
        
        logger.debug("jabatan ==>"+jabatan);
        logger.debug("catatan"+catat);
       
        
        
        for (int j = 0; j < rujLuarDok.size(); j++) {
            for (int k = 0; k < findFolderDok.size(); k++) {
                if ((findFolderDok.get(k).getIdDokumen() == rujLuarDok.get(j).getDokumen().getIdDokumen())) {
                    findFolderDok.remove(k);
                    break;
                    
                }
            }
            
            logger.debug("List all Dokumen :" + findFolderDok.size());
            logger.debug("List mohonRujLuarDok :" + rujLuarDok.size());
            logger.debug("List FilterDok :" + listFilterDokumen.size());
            
            
        }
        listFilterDokumen = findFolderDok;
        
        return new ForwardResolution("/WEB-INF/jsp/utiliti/senaraiTambahDok.jsp");
        
    }
    
    public Resolution cariFilterTambahDok() throws Exception {
        
        
        logger.debug("Id Permohonan :" + idMohon);
        logger.debug("Keyword :" + keyword);
        if(keyword.equals("")){
            keyword = null;
        }
        findFolderDok = service.findFolDok(idMohon, keyword);
        //listAllDokumen =service.allDokumen();
        logger.debug("Id Rujukan :" + idruj);
        rujLuarDok = service.findPermohonanLuarDok(idruj);
        
        
        for (int j = 0; j < rujLuarDok.size(); j++) {
            for (int k = 0; k < findFolderDok.size(); k++) {
                if ((findFolderDok.get(k).getIdDokumen() == rujLuarDok.get(j).getDokumen().getIdDokumen())) {
                    findFolderDok.remove(k);
                    break;
                    
                }
            }
            
            logger.debug("List all Dokumen :" + findFolderDok.size());
            logger.debug("List mohonRujLuarDok :" + rujLuarDok.size());
            logger.debug("List FilterDok :" + listFilterDokumen.size());
            
            
        }
        listFilterDokumen = findFolderDok;
        
        return new ForwardResolution("/WEB-INF/jsp/utiliti/senaraiTambahDok.jsp");
    }
    
    public Resolution simpanDokumen() {
        
        String[] param = getContext().getRequest().getParameterValues("idDokumen");
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
             jabatan = getContext().getRequest().getParameter("jabatan");
        catat = getContext().getRequest().getParameter("catatan");
       
        
        for (String idDokumen : param) {
            
            logger.info(idDokumen);
            InfoAudit ia = new InfoAudit();
            //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonanRujukanLuar = pelupusanService.findByIdRujukan(Long.valueOf(idRujukan));
            PermohonanRujukanLuarDokumen pdok = service.findDokumen(idDokumen, idRujukan);
            if (pdok == null) {
                PermohonanRujukanLuarDokumen pe = new PermohonanRujukanLuarDokumen();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setCawangan(pguna.getKodCawangan());
                Dokumen kd = new Dokumen();
                kd.setIdDokumen(Long.valueOf(idDokumen));
                pe.setDokumen(kd);
                logger.info(kd.getIdDokumen());
                pe.setHaluan("H");
                pe.setPermohonanRujukanLuar(permohonanRujukanLuar);
                pelupusanService.simpanDokumen(pe);
                
                
            } else {
                addSimpleError("Dokumen  ini telah diHantar");
            }
            
            
            
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(CarianDokumenActionBean.class, "findMohonRujLuarDok").addParameter("idRujukan", idRujukan).addParameter("catatan", this.getCatat()).addParameter("jabatan", this.getJabatan()).addParameter("idMohon", this.getIdMohon());
    }
    
    public Long getIdruj() {
        return idruj;
    }
    
    public void setIdruj(Long idruj) {
        this.idruj = idruj;
    }
    
    public List<PermohonanRujukanLuarDokumen> getRujLuarDok() {
        return rujLuarDok;
    }
    
    public void setRujLuarDok(List<PermohonanRujukanLuarDokumen> rujLuarDok) {
        this.rujLuarDok = rujLuarDok;
    }
    
    public CommonService getService() {
        return service;
    }
    
    public void setService(CommonService service) {
        this.service = service;
    }
    
    public String getCatat() {
        return catat;
    }
    
    public void setCatat(String catat) {
        this.catat = catat;
    }
    
    public String getJabatan() {
        return jabatan;
    }
    
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    
    public List<Dokumen> getListAllDokumen() {
        return listAllDokumen;
    }
    
    public void setListAllDokumen(List<Dokumen> listAllDokumen) {
        this.listAllDokumen = listAllDokumen;
    }
    
    public List<Dokumen> getFindFolderDok() {
        return findFolderDok;
    }
    
    public void setFindFolderDok(List<Dokumen> findFolderDok) {
        this.findFolderDok = findFolderDok;
    }
    
    public Long getIdFolder() {
        return idFolder;
    }
    
    public void setIdFolder(Long idFolder) {
        this.idFolder = idFolder;
    }
    
    public String getIdMohon() {
        return idMohon;
    }
    
    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }
    
    public List<Dokumen> getListFilterDokumen() {
        return listFilterDokumen;
    }
    
    public void setListFilterDokumen(List<Dokumen> listFilterDokumen) {
        this.listFilterDokumen = listFilterDokumen;
    }
    
    public List<Dokumen> getListSearchDok() {
        return listSearchDok;
    }
    
    public void setListSearchDok(List<Dokumen> listSearchDok) {
        this.listSearchDok = listSearchDok;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }
    
    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }
    
    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }
    
    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    
    public Pengguna getPguna() {
        return pguna;
    }
    
    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public String getJabatan1() {
        return jabatan1;
    }

    public void setJabatan1(String jabatan1) {
        this.jabatan1 = jabatan1;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.PengambilanService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import etanah.service.common.TanahRizabService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.strata.CommonService;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.PembangunanService;
/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/laporanimejterdahulu")
public class imej_laporan_tanah_terdahuluActionBean extends AbleActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    CommonService commonService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    private static final Logger LOG = Logger.getLogger(imej_laporan_tanah_terdahuluActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idHakmilik;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private FileBean fileToBeUpload;
    private boolean view = false;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    private String idSblm;
    private Permohonan permohonanSebelum;
    private String imageFolderPath;
    private List<ImejLaporan> senaraiImejLaporan;
    private Dokumen dokumen;
    private ImejLaporan imejLaporan = new ImejLaporan();
    private ImejLaporan imejLaporanUP;

        @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        return new JSP("pengambilan/negerisembilan/penarikanbalik/imej_laporan_tanah_terdahulu.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanSebelum=permohonan.getPermohonanSebelum();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            
        }


        String format1 = "image/jpeg";
        String format2 = "image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pguna.getNama());

    }

    public Resolution hakmilikDetails() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan(); 

        if (idSblm != null && idPermohonan != null && idHakmilik != null) {
            LOG.info("idSebelum "+idSblm);
            LOG.info("idPermohonan "+idPermohonan);
            LOG.info("idHakmilik "+idHakmilik);
            
            permohonanSebelum = permohonan.getPermohonanSebelum();
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
            LOG.info("idMH "+hakmilikPermohonan.getId());
            
                if (permohonanSebelum != null) 
                {
                    try{
                    LOG.info("Loop 1 ");
                    laporanTanah = laporanTanahService.findLaporanTanahByIdMohonMH(idSblm, hakmilikPermohonan.getId());
                     LOG.info("Laporan Tanah :"+laporanTanah.getIdLaporan());
                    }
                    catch(Exception e){
                        
                    }
                    if (laporanTanah != null) 
                    {       LOG.info("Loop 2 ");
                            LOG.info("Id Folder PermohonanSebelum :"+permohonanSebelum.getFolderDokumen().getFolderId());
                            String dokumenPath = conf.getProperty("document.path");
                            imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonanSebelum.getFolderDokumen().getFolderId() + File.separator;
                            LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
                            FolderDokumen fd = permohonan.getFolderDokumen();

                            String docPath = conf.getProperty("document.path");

                            senaraiImejLaporan = new ArrayList<ImejLaporan>();

                            senaraiImejLaporan = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan());



                            LOG.info("###### senaraiImejLaporan getsize :" + senaraiImejLaporan.size());
                            LOG.info("idPermohonan" + idPermohonan);
                            LOG.info("permohonanSebelum " + permohonanSebelum);
                    }
                }
                    
        
 }
        else if(idSblm == null && idPermohonan != null && idHakmilik != null)
 {
            LOG.info("idPermohonan "+idPermohonan);
            LOG.info("idHakmilik "+idHakmilik);
            
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            LOG.info("idMH "+hakmilikPermohonan.getId());
            
                if (permohonan != null) 
                {
                    LOG.info("Loop 1 ");
                    laporanTanah = laporanTanahService.findLaporanTanahByIdMohonMH(idPermohonan, hakmilikPermohonan.getId());
                     LOG.info("Laporan Tanah :"+laporanTanah.getIdLaporan());
                    if (laporanTanah != null) 
                    {       LOG.info("Loop 2 ");
                            LOG.info("Id Folder PermohonanSebelum :"+permohonan.getFolderDokumen().getFolderId());
                            String dokumenPath = conf.getProperty("document.path");
                            imageFolderPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId() + File.separator;
                            LOG.info("----------imageFolderPath--------------: " + imageFolderPath);
                            FolderDokumen fd = permohonan.getFolderDokumen();

                            String docPath = conf.getProperty("document.path");

                            senaraiImejLaporan = new ArrayList<ImejLaporan>();

                            senaraiImejLaporan = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan());



                            LOG.info("###### senaraiImejLaporan getsize :" + senaraiImejLaporan.size());
                            LOG.info("idPermohonan" + idPermohonan);
                            LOG.info("permohonanSebelum " + permohonan);
                    }
                }
 
 
 
 }
        return new JSP("pengambilan/negerisembilan/penarikanbalik/imej_laporan_tanah_terdahulu.jsp").addParameter("tab", "true");
    }
    public Resolution viewImejPopup() {
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String idDok = getContext().getRequest().getParameter("idDokumen");
        LOG.info("###### viewImejPopup idPermohonan : " + idPermohonan);
        LOG.info("###### viewImejPopup idDok : " + idDok);

        laporanTanah = laporanTanahService.findLaporanTanahByIdMohon(idPermohonan);
        dokumen = dokumenDAO.findById(Long.valueOf(idDok));
        imejLaporan = imejLaporanDAO.findById(Long.valueOf(idDok));

        imejLaporanUP = new ImejLaporan();

        imejLaporanUP = pembangunanService.findImejlaporan(laporanTanah.getIdLaporan(), dokumen.getIdDokumen());
        LOG.info("###### viewImejPopup imejLaporanUP : " + imejLaporanUP);
        return new JSP("pembangunan/common/muat_naik_imej_pop.jsp").addParameter("popup", "true");
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }
    
    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
  
    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }
    
    public String getImageFolderPath() {
        return imageFolderPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }
    
    public List<ImejLaporan> getSenaraiImejLaporan() {
        return senaraiImejLaporan;
    }

    public void setSenaraiImejLaporan(List<ImejLaporan> senaraiImejLaporan) {
        this.senaraiImejLaporan = senaraiImejLaporan;
    }
    

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public ImejLaporan getImejLaporan() {
        return imejLaporan;
    }

    public void setImejLaporan(ImejLaporan imejLaporan) {
        this.imejLaporan = imejLaporan;
    }

    public ImejLaporan getImejLaporanUP() {
        return imejLaporanUP;
    }

    public void setImejLaporanUP(ImejLaporan imejLaporanUP) {
        this.imejLaporanUP = imejLaporanUP;
    }
    
}


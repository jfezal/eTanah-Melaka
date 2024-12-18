/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.KodDokumen;
import etanah.model.PermohonanKertas;
import etanah.service.BPelService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Murali
 */

@UrlBinding("/pelupusan/rekod_keputusan_mmk")
public class KeputusanMMKActionBean extends AbleActionBean {

    @Inject
    private Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pservice;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    
    private static final Logger LOG = Logger.getLogger(KertasMaklumanMMKActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertas;
    private KodDokumen kd;
    private String ulasan;
    private String keputusan;
    private Pengguna peng;
    private String stageId;
    private String taskId;
    private String trhksidang;


    @DefaultHandler
    public Resolution showForm() {

     //   getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_mmkNS.jsp").addParameter("tab", "true");
    }


    public Resolution showForm1() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("pelupusan/rekod_keputusan_mmkNS.jsp").addParameter("tab", "true");
    }


    public Resolution showForm2() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);


        return new JSP("pelupusan/rekod_keputusan_mmkNS.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");

        if(permohonanKertas.getTarikhSidang() != null){
        trhksidang = sdf.format(permohonanKertas.getTarikhSidang());
        }

        stageId(taskId);
   
    }

    public Resolution simpan() {


        LOG.info("--------------rehydrate() Started--------------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("---------idPermohonan---------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("---------permohonan---------::" + permohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKS");
        LOG.info("----------Kod Dokumen----------::" + kd);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "MMKS");
        LOG.info("-------Simpan-------permohonankertas--------------::");


        if (permohonanKertas != null) {
            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pservice.simpanPermohonanKertas(permohonanKertas);

            
        }

        if ((permohonan != null) && (permohonan.getKeputusan() != null) && (permohonan.getUlasan() != null)){

            LOG.info("------if------KOD KEPUTUSAN AND ULASAN NOT Null--------------::" + permohonan);
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
//            permohonan.setKeputusan(permohonan.getKeputusan());
//            permohonan.setUlasan(permohonan.getUlasan());
            pservice.simpanPermohonan(permohonan);
        }else {
            
            LOG.info("------if------KOD KEPUTUSAN AND ULASAN Null--------------::" + permohonan);
            infoAudit = permohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
           // permohonan.setKeputusan(null);
//            permohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
//            permohonan.setUlasan(ulasan);
            pservice.simpanPermohonan(permohonan);
            
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/rekod_keputusan_mmkNS.jsp").addParameter("tab", "true");
        
    }

    
    public String stageId(String taskId) {
       BPelService service = new BPelService();
       String stageId = null;
       if (StringUtils.isNotBlank(taskId)) {
           Task t = null;
           t = service.getTaskFromBPel(taskId, peng);
           stageId = t.getSystemAttributes().getStage();
       }
       return stageId;
    }

    public String getTrhksidang() {
        return trhksidang;
    }

    public void setTrhksidang(String trhksidang) {
        this.trhksidang = trhksidang;
    }

    

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

   

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
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

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public PelupusanService getPservice() {
        return pservice;
    }

    public void setPservice(PelupusanService pservice) {
        this.pservice = pservice;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

  

}

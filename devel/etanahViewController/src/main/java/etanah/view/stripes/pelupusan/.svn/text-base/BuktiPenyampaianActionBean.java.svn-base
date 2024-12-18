/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodJabatan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.Pihak;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * @Modified by Akmal
 * @author sitifariza.hanim
 */

@UrlBinding("/pelupusan/bukti_penyampaian")
public class BuktiPenyampaianActionBean extends AbleActionBean {

     private static Logger logger = Logger.getLogger(BuktiPenyampaianActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    BPelService service;
    @Inject
    etanah.Configuration conf;
    
    private Notis notis;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PermohonanKertas permohonanKertas;
    private KodDokumen kodDokumen;
    private Dokumen dokumen;
    private KodStatusTerima kodStatus; 
    private Pihak pihak;
    private KodJabatan jabatan;
    private FasaPermohonan fasaPermohonan;
    private String catatanPenerimaan;
    private String idPermohonan;
    private Date tarikhTerima;
    private String kodStatusTerima;
    private String stageId;
    private String kodDok;
    private Long idPihak;
    private String idNotis;
    private Long notisId;
    private String tajukDok;
    private String kodNegeri;
   //private List<Notis> listKodNotis;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/buktipenyampaian.jsp").addParameter("tab", "true");
    }
    
    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
    
    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(BuktiPenyampaianActionBean.class, "locate");
    }

@Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);  
        kodNegeri = conf.getProperty("kodNegeri");
        //stageId = "rayuan_bayaran";
        logger.info("----stage Id-----"+stageId);
        
        if(permohonan != null){
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        
        //permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "JKTD");
        //kodDok = permohonanKertas.getKodDokumen().getKod();
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan,stageId);
        logger.info("----kod Dokumen-----"+kodDok);
//        tajukDok = permohonanKertas.getKodDokumen().getNama();
//        logger.info("----Tajuk Dokumen-----"+tajukDok);
        jabatan = permohonan.getKodUrusan().getJabatan();
        logger.info("----Kod Jabatan-----"+jabatan);
//        kodDokumen = permohonanKertas.getKodDokumen();
//        logger.info("----Kod Dokumen-----"+kodDokumen);
        
        notis = pelupusanService.findNotisByIdPermohonanNKodDokumen(idPermohonan, "N5A");
        logger.info("----Notis-----"+notis);
        if(notis != null){
            tarikhTerima = notis.getTarikhTerima();
            catatanPenerimaan = notis.getCatatanPenerimaan();
            kodStatusTerima = notis.getKodStatusTerima().getKod();
            kodStatus = notis.getKodStatusTerima();
            notisId = notis.getIdNotis();
            kodDok = notis.getDokumenNotis().getKodDokumen().getNama();
        }

        }
    }

    public Resolution simpan() throws ParseException{
        InfoAudit infoAudit = new InfoAudit() ;
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            idNotis = getContext().getRequest().getParameter("idNotis");
            notis = new Notis(); 
            KodDokumen dokNotis = new KodDokumen();
            
            if(catatanPenerimaan != null || tarikhTerima != null || kodStatusTerima != null){
            dokumen = new Dokumen();
            dokNotis.setKod(kodDok);
            //dokNotis.setNama(tajukDok);
            dokumen.setTajuk("Bukti Penyampaian");
            dokumen.setKodDokumen(dokNotis);
            dokumen.setInfoAudit(infoAudit);
            dokumenDAO.saveOrUpdate(dokumen);
            
            notis.setJabatan(jabatan);
            notis.setInfoAudit(infoAudit);
            notis.setDokumenNotis(dokumen);
            notis.setPermohonan(permohonan);
            notis.setTarikhTerima(tarikhTerima);
            notis.setCatatanPenerimaan(catatanPenerimaan);
            kodStatus = new KodStatusTerima();
            kodStatus.setKod(kodStatusTerima);
            notis.setKodStatusTerima(kodStatus);
            //notis.setKodStatusTerima(notisDAO.findById(kodStatus));
            
            pelupusanService.simpanNotis(notis);
            //notisDAO.saveOrUpdate(notis);
            
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        return new JSP("pelupusan/buktipenyampaian.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(String catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getKodDok() {
        return kodDok;
    }

    public void setKodDok(String kodDok) {
        this.kodDok = kodDok;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public Long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(Long idPihak) {
        this.idPihak = idPihak;
    }

    public KodStatusTerima getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(KodStatusTerima kodStatus) {
        this.kodStatus = kodStatus;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public KodJabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(KodJabatan jabatan) {
        this.jabatan = jabatan;
    }

    public Long getNotisId() {
        return notisId;
    }

    public void setNotisId(Long notisId) {
        this.notisId = notisId;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getTajukDok() {
        return tajukDok;
    }

    public void setTajukDok(String tajukDok) {
        this.tajukDok = tajukDok;
    }

   
  
    
}

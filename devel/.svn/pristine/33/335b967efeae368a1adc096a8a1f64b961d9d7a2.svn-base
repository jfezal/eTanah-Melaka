/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Author : Shazwan 15/6/2011
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodWarnaKPDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodWarnaKP;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/syor_pulangwangcagar")
public class SyorPulangWangCagar extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodWarnaKPDAO kodWarnaDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    Logger logger = Logger.getLogger(DrafMMKNActionBean.class);
    @Inject
    BPelService service;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private String stageId;
    private Pengguna peng;
    private String idPermohonan;
    private String idPemohon;
    private String kodNeg;
    private String namaNegeri;
    private List<PermohonanKertasKandungan> senaraiLaporanKetuaMenteri = new Vector();
    private List<PermohonanBahanBatuan> senaraiBahanBatu;
    private HakmilikPermohonan hakmilikPermohonan;
    private String syorUlasan;
    private String tajuk;
    private String checkStage;
    
    @DefaultHandler
    public Resolution showForm1() {
        checkStage = "sedia_laporan_pantau";
        return new JSP("pelupusan/batuan/syor_pulangWangCagar.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {
        checkStage = "syor_tolak";
        return new JSP("pelupusan/batuan/syor_pulangWangCagar.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
//        stageId = "sedia_laporan_pantau" ;
        FasaPermohonan mohonFasa = new FasaPermohonan();            
        permohonan = permohonanDAO.findById(idPermohonan);
        if(stageId.equals("sedia_laporan_pantau")||stageId.equals("semak_laporan_pantau")||stageId.equals("semak_syor")||stageId.equals("keputusan_ptd")||stageId.equals("semakan_surat_pulang")||stageId.equals("pulang_wang_cagaran"))
            tajuk = "SYOR PULANGAN ATAU RAMPAS WANG CAGARAN";
        if(stageId.equals("sejarah_permohonan_syor_tolak")||stageId.equals("semakan_arahan_tolak")||stageId.equals("sedia_surat_tolak"))
            tajuk = "SYOR TOLAK";
        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_laporan_pantau");
        syorUlasan = mohonFasa.getUlasan()!=null&&!("").equals(mohonFasa.getUlasan())?mohonFasa.getUlasan():new String();
    }

    public Resolution SimpanPulangCagar() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan mohon = new Permohonan();
        mohon = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "sedia_laporan_pantau");
        if(mohonFasa!=null){
            mohonFasa.setUlasan(syorUlasan);            
        }else{
            mohonFasa = new FasaPermohonan();
            mohonFasa.setIdAliran(stageId);
            mohonFasa.setCawangan(mohon.getCawangan());
            mohonFasa.setInfoAudit(info);
        }
        pelupusanService.simpanFasaPermohonan(mohonFasa);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pelupusan/batuan/syor_pulangWangCagar.jsp").addParameter("tab", "true");
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKetuaMenteri() {
        return senaraiLaporanKetuaMenteri;
    }

    public void setSenaraiLaporanKetuaMenteri(List<PermohonanKertasKandungan> senaraiLaporanKetuaMenteri) {
        this.senaraiLaporanKetuaMenteri = senaraiLaporanKetuaMenteri;
    }

    public List<PermohonanBahanBatuan> getSenaraiBahanBatu() {
        return senaraiBahanBatu;
    }

    public void setSenaraiBahanBatu(List<PermohonanBahanBatuan> senaraiBahanBatu) {
        this.senaraiBahanBatu = senaraiBahanBatu;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }

    public String getCheckStage() {
        return checkStage;
    }

    public void setCheckStage(String checkStage) {
        this.checkStage = checkStage;
    }

    public String getSyorUlasan() {
        return syorUlasan;
    }

    public void setSyorUlasan(String syorUlasan) {
        this.syorUlasan = syorUlasan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    
}

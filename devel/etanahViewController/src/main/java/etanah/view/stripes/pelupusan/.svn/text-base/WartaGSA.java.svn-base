/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
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
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodRizab;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanPengguna;
import etanah.model.TanahRizabPermohonan;
import etanah.service.BPelService;
import etanah.service.common.TanahRizabService;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Akmal
 */

@UrlBinding("/pelupusan/warta_GSA")
public class WartaGSA extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(RekodKeputusanJKTDActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
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
    PelupusanService pelupusanService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    BPelService service;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    TanahRizabService tanahRizabService;
    
    
    private PermohonanLaporanKawasan permohonanLaporanKawasan;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private TanahRizabPermohonan tanahRizabPermohonan;
    private KodRizab kodRizab;
    private long idTanahRizab;
    private String idPermohonan;
    private KodCawangan kodCaw;
    private Long idMohonlaporKws;
    private String noWarta;
    private Date tarikhWarta;
    private String noPelan;
    private String pbt1;
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/gsa/warta_GSA.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan);
    if(permohonan != null){
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        kodCaw = permohonan.getCawangan();
        //tanahRizabPermohonan =tanahRizabService.findByidPermohonan(idPermohonan);
        //kodRizab = tanahRizabPermohonan.getRizab();
        //pbt1 = kodRizab.getNama();
        //permohonanLaporanKawasan = pelupusanService.findByidMohonKodRizab(idPermohonan, pbt1);
        
        }

    }

    public Resolution simpanWarta(){
        InfoAudit infoAudit = new InfoAudit() ;
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        
        permohonanLaporanKawasan = new PermohonanLaporanKawasan();
        permohonanLaporanKawasan.setPermohonan(permohonan);
        permohonanLaporanKawasan.setInfoAudit(infoAudit);
        permohonanLaporanKawasan.setNoWartaDahulu(noWarta);
        permohonanLaporanKawasan.setTarikhWartaDahulu(tarikhWarta);
        permohonanLaporanKawasan.setNoPelanWarta(noWarta);
        permohonanLaporanKawasan.setKodCawangan(kodCaw);
        pelupusanService.simpanPermohonanLaporKawasan(permohonanLaporanKawasan);
        
       addSimpleMessage("Maklumat telah berjaya disimpan") ;
       return new JSP("pelupusan/gsa/warta_GSA.jsp").addParameter("tab", "true");
    }
    
    
    public String getNoPelan() {
        return noPelan;
    }

    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public Long getIdMohonlaporKws() {
        return idMohonlaporKws;
    }

    public void setIdMohonlaporKws(Long idMohonlaporKws) {
        this.idMohonlaporKws = idMohonlaporKws;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }

    public long getIdTanahRizab() {
        return idTanahRizab;
    }

    public void setIdTanahRizab(long idTanahRizab) {
        this.idTanahRizab = idTanahRizab;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
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

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getPbt1() {
        return pbt1;
    }

    public void setPbt1(String pbt1) {
        this.pbt1 = pbt1;
    }
    
    
}

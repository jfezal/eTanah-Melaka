/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author khairul.hazwan
 */
@UrlBinding("/pembangunan/melaka/suratKelulusanTukarSyarat")
public class SuratKelulusanTukarSyaratActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(SuratKelulusanTukarSyaratActionBean.class);   
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hp;
    private PermohonanKertasKandungan kertasK;
    private KodDokumen kd;           
    private HakmilikPermohonan hpTemp; 
    private List<HakmilikPermohonan> hpList;   
    private PermohonanKertas mohonKertas;
    private String sebabPenolakan;


    @DefaultHandler
    public Resolution showForm() {
        LOG.info("----------showForm----------");
        return new JSP("pembangunan/melaka/suratKelulusanTukarSyarat.jsp").addParameter("tab", "true");
    }
    
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------rehydrate----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("----------Permohonan----------:"+permohonan);
        
        mohonKertas = devService.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "STT");
        if(mohonKertas != null){           
            sebabPenolakan = mohonKertas.getTajuk();
        }else{
            sebabPenolakan = new String();
        }
    }

 
    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan caw = pengguna.getKodCawangan();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();       
       
        kd = kodDokumenDAO.findById("STT");
       
        if (mohonKertas != null) {            
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } 

        if (sebabPenolakan == null || sebabPenolakan.equals("")){
            sebabPenolakan = " Tiada Disokong Oleh Pihak Atasan. ";
        }

        if (mohonKertas != null) {
                          
            mohonKertas.setTajuk(sebabPenolakan);           
            mohonKertas.setInfoAudit(infoAudit);                 
            devService.simpanPermohonanKertas(mohonKertas); 
            addSimpleMessage("Maklumat telah berjaya disimpan.");
            
        }else{
            mohonKertas = new PermohonanKertas();         
            mohonKertas.setTajuk(sebabPenolakan);
            mohonKertas.setKodDokumen(kd);
            mohonKertas.setInfoAudit(infoAudit);
            mohonKertas.setCawangan(pengguna.getKodCawangan());
            mohonKertas.setPermohonan(permohonan);           
            devService.simpanPermohonanKertas(mohonKertas);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/suratKelulusanTukarSyarat.jsp").addParameter("tab", "true");
    }
    
    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
   
    public HakmilikPermohonan getHpTemp() {
        return hpTemp;
    }

    public void setHpTemp(HakmilikPermohonan hpTemp) {
        this.hpTemp = hpTemp;
    }

    public List<HakmilikPermohonan> getHpList() {
        return hpList;
    }

    public void setHpList(List<HakmilikPermohonan> hpList) {
        this.hpList = hpList;
    }
   
    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public String getSebabPenolakan() {
        return sebabPenolakan;
    }

    public void setSebabPenolakan(String sebabPenolakan) {
        this.sebabPenolakan = sebabPenolakan;
    }

   
}


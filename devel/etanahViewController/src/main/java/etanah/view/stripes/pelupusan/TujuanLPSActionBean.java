/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nurul.izza
 * Modified By Murali
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodItemPermit;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.LupusPermit;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/tujuan_lps")
public class TujuanLPSActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    PermitDAO permitDAO ;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO ;
    @Inject
    KodItemPermitDAO kodItemPermitDAO ;

    private PermohonanPermitItem ppi;
    private PermohonanTuntutanButiran permohonanTuntutanButiran;
//    private LupusPermit lupusPermit;
    private List<KodItemPermit> senaraiKodItemPermit;
    private Permit permit;
    private PermitSahLaku sahLaku;
    private BigDecimal bayaran;
    private Date tarikhPermitMula;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private BigDecimal amaunTuntutan;
    private String idPermohonan;
    private String stageId;
    private  FasaPermohonan mohonFasa;
    private String kodkpsn;

    private static final Logger log = Logger.getLogger(TujuanLPSActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/tujuanLPS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.info("----------------------rehydrate()--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = pelupusanService.findById(idPermohonan);

        if(p.getKodUrusan().getKod().equalsIgnoreCase("PPRU")) {
            senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermitPPRU();
        } else {
            senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermitPLPTD();
        }        

        log.info("Id Permohonan: " + idPermohonan);
        bayaran = BigDecimal.ZERO;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        stageId = stageId(taskId, pguna);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            mohonFasa = new FasaPermohonan();
            
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info(("----------------------permit-------------------"+permit));
            if(permit!=null){
                sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

                if(sahLaku != null){
                    tarikhPermitMula = sahLaku.getTarikhPermitMula() ;
                }
            }
                
        
        }
    }


    public Resolution simpanKelulusan() {

         log.info("-------Simpan Started--------------------");
         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        if (catatan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pelupusanService.simpanPermohonan(permohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
          KodCawangan caw = peng.getKodCawangan();
          log.info("-------KodCawangan caw--------------------::"+caw);

        InfoAudit ia = peng.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        String kodPermit = getContext().getRequest().getParameter("kodPermit.kod");
        if (!kodPermit.equals("")) {
            
            KodItemPermit kitem = new KodItemPermit();
            permit = new Permit();
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            kitem.setKod(kodPermit);
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);
            if (ppi != null) {

                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);

            } else {
                ppi = new PermohonanPermitItem();
                ppi.setPermohonan(permohonan);
                ppi.setInfoAudit(ia);
                ppi.setKodCawangan(peng.getKodCawangan());
                ppi.setKodItemPermit(kitem);
                pelupusanService.saveOrUpdate(ppi);
            }
            KodItemPermit kitemtemp = new KodItemPermit();
            kitemtemp = kodItemPermitDAO.findById(kodPermit);
            
            if(permit!=null){
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if(StringUtils.isNotBlank(kodPermit)){
                    if(kodPermit.equals("LL")||kodPermit.equals("LN"))
                         permit.setKeterangan(permohonan.getCatatan());
                    else{
                         permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            }else {
                permit = new Permit();
                permit.setPermohonan(permohonan);
                permit.setInfoAudit(ia);
                permit.setKodCawangan(peng.getKodCawangan());
                if(StringUtils.isNotBlank(kodPermit)){
                    if(kodPermit.equals("LL")||kodPermit.equals("LN"))
                         permit.setKeterangan(permohonan.getCatatan());
                    else{
                         permit.setKeterangan(kitemtemp.getNama());
                    }
                }
                pelupusanService.saveOrUpdate(permit);
            }
            
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());

            if (sahLaku != null) {
                 log.info("-------sahLaku Not Null---------------::");
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());

            } else {
                 log.info("-------sahLaku is Null--------::");
                 //String tarikhMula = getContext().getRequest().getParameter("sahLaku.tarikhPermitMula");
                sahLaku = new PermitSahLaku();
                sahLaku.setPermit(permit);
                sahLaku.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
                sahLaku.setInfoAudit(ia);
                sahLaku.setKodCawangan(peng.getKodCawangan());
                if(tarikhPermitMula != null)
                sahLaku.setTarikhPermitMula(tarikhPermitMula);
            }
             pelupusanService.simpanPermitSahLaku(sahLaku);


             log.info("-------Mohon Tuntutukos List Updating--------::");

             
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        else
        {
            addSimpleError("Sila Masukkan Tujuan LPS");
        }
        
         return new JSP("pelupusan/tujuanLPS.jsp").addParameter("tab", "true");

    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPermitItem getPpi() {
        return ppi;
    }

    public void setPpi(PermohonanPermitItem ppi) {
        this.ppi = ppi;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return senaraiKodItemPermit;
    }

    public void setSenaraiKodItemPermit(List<KodItemPermit> senaraiKodItemPermit) {
        this.senaraiKodItemPermit = senaraiKodItemPermit;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public PermitSahLaku getSahLaku() {
        return sahLaku;
    }

    public void setSahLaku(PermitSahLaku sahLaku) {
        this.sahLaku = sahLaku;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public PermohonanTuntutanButiran getPermohonanTuntutanButiran() {
        return permohonanTuntutanButiran;
    }

    public void setPermohonanTuntutanButiran(PermohonanTuntutanButiran permohonanTuntutanButiran) {
        this.permohonanTuntutanButiran = permohonanTuntutanButiran;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    public String getKodkpsn() {
        return kodkpsn;
    }

    public void setKodkpsn(String kodkpsn) {
        this.kodkpsn = kodkpsn;
    }
}

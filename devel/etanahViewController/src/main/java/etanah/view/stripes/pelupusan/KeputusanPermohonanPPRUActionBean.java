/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Shazwan
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodItemPermit;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
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
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.EnforceService;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@UrlBinding("/pelupusan/surat_kelulusan_ppru")
public class KeputusanPermohonanPPRUActionBean extends AbleActionBean {

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
    PermitDAO permitDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    KodItemPermitDAO kodItemPermitDAO;
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
    private HakmilikPermohonan hakmilikPermohonan;
    private BigDecimal amaunTuntutan;
    private String idPermohonan;
    private String tarikhMulaLesen;
    private String kodU;
    private String stageId;
    private Pengguna peng;
    private FasaPermohonan mohonFasa;
    private String kodkpsn;
    private static final Logger log = Logger.getLogger(KeputusanPermohonanPPRUActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        return new JSP("pelupusan/surat_keputusan_PPRU.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/surat_keputusan_PPRU.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.info("----------------------rehydrate()--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiKodItemPermit = pelupusanService.getSenaraiKodItemPermit();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        log.info("Id Permohonan: " + idPermohonan);
        bayaran = BigDecimal.ZERO;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            ppi = pelupusanService.findByIdMohonPermitItem(idPermohonan);

            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            log.info(("----------------------permit-------------------" + permit));
            if (permit != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if(permit.getTarikhPermitMula()!=null)
                    tarikhMulaLesen = sdf.format(permit.getTarikhPermitMula());
//                sahLaku = pelupusanService.findPermitSahLakuByIdMohonIdPermit(idPermohonan, permit.getIdPermit());
            }

            listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
            log.info(("----------------------listtuntutankos--------------" + listtuntutankos));
            if (!(listtuntutankos.isEmpty())) {
                log.info(("----------------------listtuntutankos not empty--------------------" + listtuntutankos));
                for (int i = 0; i < listtuntutankos.size(); i++) {
                    log.info(("----------------------listtuntutankos----for----------" + listtuntutankos));
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos = listtuntutankos.get(i);
                    if (permohonantuntutkos.getKodTuntut() != null) {
                        log.info(("----------------------listtuntutankos---if-----------" + listtuntutankos));
                        if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                            if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4E")) {
                            log.info(("----------------------listtuntutankos-----if 2---------" + listtuntutankos));
                            bayaran = permohonantuntutkos.getAmaunTuntutan();
                            }
                        }else{
                            if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4D")) {
                            log.info(("----------------------listtuntutankos-----if 2---------" + listtuntutankos));
                            bayaran = permohonantuntutkos.getAmaunTuntutan();
                            }
                        }
                        
                    }
                }
            }
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            if(hakmilikPermohonan!=null){
                if(hakmilikPermohonan.getLuasLulusUom()!=null)
                    kodU = hakmilikPermohonan.getLuasLulusUom().getKod();
            }
        }
        
        mohonFasa = new FasaPermohonan();
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPRU")) {
            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "rekod_keputusan_mmkn");
        }

        if (mohonFasa != null) {
            if (mohonFasa.getKeputusan() != null) {                
                kodkpsn = mohonFasa.getKeputusan().getKod();
            }
        }
    }

    public Resolution simpanKelulusan() throws ParseException {

        log.info("-------Simpan Started--------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String catatan = getContext().getRequest().getParameter("permohonan.catatan");
        if (catatan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pelupusanService.simpanPermohonan(permohonan);
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        log.info("-------KodCawangan caw--------------------::" + caw);



        InfoAudit ia = peng.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        // Added new Code
        PermohonanTuntutan permohonanTuntutan;
//             permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "Bayaran Borang 4A (LPS)");
        if(permohonan.getKodUrusan().getKod().equals("PPTR")){
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransKod(idPermohonan, "DIS4E");
        }else{
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransKod(idPermohonan, "DIS4D");
        }
        

        if (permohonanTuntutan != null) {
            log.info("-------Mohonan Tuntutan Not Null-------------------");
            ia = permohonanTuntutan.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            permohonanTuntutan.setInfoAudit(ia);
            pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
        } else {

            log.info("-------Mohonan Tuntutan is Null-------------------");

            Calendar c = Calendar.getInstance();
            log.info("-------Todays Date::-------------------" + c);
            Date date = new Date();
            c.add(Calendar.MONTH, 3);
            log.info("-------After 3 months Date::-------------------" + c);
            date = c.getTime();
            log.info("-------After 3 months Date::-----date--------------" + date);
//            String itemList = "DISB4A";
//            BigDecimal amaunTuntutanList = bayaran;


//            kodTransTuntut = pelupusanService.findKodTransTuntutByName("DIS4A");
            KodCawangan test = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
            permohonanTuntutan = new PermohonanTuntutan();
//            permohonanTuntutan.setCawangan(test);
            permohonanTuntutan.setCawangan(caw);
            permohonanTuntutan.setPermohonan(permohonan);
            permohonanTuntutan.setInfoAudit(ia);
            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
            if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                kodTransTuntut = kodTransaksiTuntutDAO.findById("DIS4E");
            }else{
                kodTransTuntut = kodTransaksiTuntutDAO.findById("DIS4D");
            }
            
            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
            permohonanTuntutan.setTarikhAkhirBayaran(date);
            pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
        }
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
        if (bayaran == null) {
            bayaran = new BigDecimal(0.00);
        }

        log.info("-------Mohon Tuntutukos List Updating--------::");

//        KodTransaksi kt = new KodTransaksi();
//        kt.setKod("12104");
         KodTuntut kt = new KodTuntut();
         kt = kodTuntutDAO.findById("DISB4D");
        if (!(listtuntutankos.isEmpty())) {
            log.info("-------permohonantuntutkos--Not Empty--------::" + permohonantuntutkos);
            for (int i = 0; i < listtuntutankos.size(); i++) {
                log.info("-------listtuntutankos.size()----------" + listtuntutankos.size());
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
                    log.info("-------permohonantuntutkos-----getKodTuntut() Not Null-------------" + permohonantuntutkos);
                    if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4E")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaran);
                        permohonantuntutkos.setKodTransaksi(kt.getKodKewTrans());
                        }
                    }else{
                        if (permohonantuntutkos.getKodTuntut().getKod().equals("DISB4D")) {
                        permohonantuntutkos.setAmaunTuntutan(bayaran);
                        permohonantuntutkos.setKodTransaksi(kt.getKodKewTrans());
                        }
                    }
                    
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(peng);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(peng.getKodCawangan());
                    pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);

                }
            }
        } else {
            log.info("-------permohonantuntutkos-----getKodTuntut() Not Null-------------" + permohonantuntutkos);
            if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                permohonanTuntutan = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(idPermohonan, "DIS4E");
                if (permohonanTuntutan.getKodTransaksiTuntut().getKod().equals("DIS4E")) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos.setAmaunTuntutan(bayaran);
                    permohonantuntutkos.setKodTransaksi(kt.getKodKewTrans());
                    permohonantuntutkos.setKodTuntut(kt);
                }
            }else{
                 permohonanTuntutan = pelupusanService.findPermohonanTuntutanByIdMohonAndKodTransTuntut(idPermohonan, "DIS4D");
                if (permohonanTuntutan.getKodTransaksiTuntut().getKod().equals("DIS4D")) {
                    permohonantuntutkos = new PermohonanTuntutanKos();
                    permohonantuntutkos.setAmaunTuntutan(bayaran);
                    permohonantuntutkos.setKodTransaksi(kt.getKodKewTrans());
                    permohonantuntutkos.setKodTuntut(kt);
                }
            }
           
            ia = new InfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(peng);
            permohonantuntutkos.setInfoAudit(ia);
            permohonantuntutkos.setPermohonan(permohonan);
            permohonantuntutkos.setCawangan(peng.getKodCawangan());
            if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                permohonantuntutkos.setItem("Bayaran Permit Tanah Rizab");
            }else{
                 permohonantuntutkos.setItem("Bayaran Permit Ruang Udara");
            }
           
            pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
        }



        // added new code
        if(permohonan.getKodUrusan().getKod().equals("PPTR")){
            permohonantuntutkos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4E");
        }else{
             permohonantuntutkos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4D");
        }
       
        log.info("-------Mohonantuntan Butiran Saving--------------------");
        permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
        if (permohonanTuntutanButiran != null) {

            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanTuntutanButiran.setInfoAudit(ia);

        } else {
            PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
            permohonanTuntutanButiran.setPermohonanTuntutan(permohonanTuntutan);
            permohonanTuntutanButiran.setPermohonanTuntutanKos(permohonantuntutkos);
            permohonanTuntutanButiran.setInfoAudit(ia);
            pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
        }
        
        if(tarikhMulaLesen!=null){
            permit = new Permit();
            permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
            if(permit!=null){                
                ia = permit.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permit.setInfoAudit(ia);
            }else{
                ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permit = new Permit();
                permit.setInfoAudit(ia);            
                permit.setKodCawangan(permohonan.getCawangan());
                if(permohonan.getKodUrusan().getKod().equals("PPTR")){
                     permit.setKodJenisPermit(kodJenisPermitDAO.findById("E"));
                }else{
                    permit.setKodJenisPermit(kodJenisPermitDAO.findById("D"));
                }
                
                permit.setPermohonan(permohonan);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");            
            permit.setTarikhPermitMula(sdf.parse(tarikhMulaLesen));            
            pelupusanService.saveOrUpdate(permit);
        }
        
        permit = pelupusanService.findPermitByIdPermohonan(idPermohonan);
//        permit.setTarikhPermitMula(tarikhPermitMula);
        
        /*
         * SAVING HAKMILIKPERMOHONAN
         */
        if(hakmilikPermohonan!=null){
            ia = new InfoAudit();
            ia = hakmilikPermohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);
            if(!StringUtils.isBlank(kodU))
                hakmilikPermohonan.setLuasLulusUom(kodUOMDAO.findById(kodU));
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);            
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("view", Boolean.FALSE);
        return new JSP("pelupusan/surat_keputusan_PPRU.jsp").addParameter("tab", "true");

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
    
    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public String getTarikhMulaLesen() {
        return tarikhMulaLesen;
    }

    public void setTarikhMulaLesen(String tarikhMulaLesen) {
        this.tarikhMulaLesen = tarikhMulaLesen;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKodU() {
        return kodU;
    }

    public void setKodU(String kodU) {
        this.kodU = kodU;
    }
    
    public String getKodkpsn() {
        return kodkpsn;
    }

    public void setKodkpsn(String kodkpsn) {
        this.kodkpsn = kodkpsn;
    }
}

/*
 * @author  : Hayyan
 * @txn     : PJTK N9
 */

package etanah.view.stripes.pelupusan;

import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.Date;
import com.google.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.LelonganAwam;
import etanah.model.TanahRizabPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.KodBandarPekanMukim;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;

@UrlBinding("/pelupusan/maklumat_pelelongan")
public class MaklumatPelelonganActionBean extends AbleActionBean {
    
    @Inject
    BPelService bpelService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;

//    private ActionBeanContext context;
    private static final Logger LOG = Logger.getLogger(MaklumatPelelonganActionBean.class);
    private String idPermohonan;
    private String stageId;
    private Pengguna peng;
    private Permohonan permohonan;
    // TanahRizabPermohonan
    TanahRizabPermohonan trp;
    private String tujuanRezab;    
    private Character aktif = 'Y';
    private String noWarta;
    private Date tarikhWarta;
    // LelonganAwam
    private LelonganAwam LA;
    private BigDecimal bayaranBorang;
    private BigDecimal deposit;
    private BigDecimal hargaRizab;
    private BigDecimal hargaBidaan;
    private BigDecimal depositBayaran;
    private BigDecimal bakiBayaran;
    private Date tarikhMulaBorang;
    private Date tarikhAkhirBorang;
    private String tarikhLawatanTapak;
    private String waktuLawatanTapak;
    private String tarikhTamatPendaftaran;
    private String waktuTamatPendaftaran;
    private String tarikhLelongan;
    private String waktuLelongan;
    private String tempatLelongan;
    // date purposes
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");    
    private String jam, minit, ampm;
    private String jam1, minit1, ampm1;
    private String jam2, minit2, ampm2;
    // PermohonanTuntutanKos
    private PermohonanTuntutanKos mohonTuntutKos;
    // KodBandarPekanMukim
    private KodBandarPekanMukim bandarPekanMukim;    
    private String nama;
    
    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }    
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        retrieveData();
    }

    public void retrieveData() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        
//        Permohonan p = pelupusanService.findById(idPermohonan);
//        if (p != null) {
//            this.setTujuanRezab(p.getSebab());            
//        } else {
//            this.setTujuanRezab("");            
//        }
        
        LA = pelupusanService.findLelonganAwamById(idPermohonan);        
        if(LA != null) {
            this.setBayaranBorang(LA.getBayaranBorang());
            this.setTarikhMulaBorang(LA.getTarikhMulaBorang());
            this.setTarikhAkhirBorang(LA.getTarikhAkhirBorang());
            
            tarikhLawatanTapak = sdf.format(LA.getTarikhLawatanTapak());
            tarikhLawatanTapak = sdf1.format(LA.getTarikhLawatanTapak()).substring(0,10);
            jam = sdf1.format(LA.getTarikhLawatanTapak()).substring(11,13);
            minit = sdf1.format(LA.getTarikhLawatanTapak()).substring(14,16);
            ampm = sdf1.format(LA.getTarikhLawatanTapak()).substring(17,19);
            
            tarikhTamatPendaftaran = sdf.format(LA.getTarikhTamatPendaftaran());
            tarikhTamatPendaftaran = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(0,10);
            jam1 = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(11,13);
            minit1 = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(14,16);
            ampm1 = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(17,19);
            
            tarikhLelongan = sdf.format(LA.getTarikhLelongan());
            tarikhLelongan = sdf1.format(LA.getTarikhLelongan()).substring(0,10);
            jam2 = sdf1.format(LA.getTarikhLelongan()).substring(11,13);
            minit2 = sdf1.format(LA.getTarikhLelongan()).substring(14,16);
            ampm2 = sdf1.format(LA.getTarikhLelongan()).substring(17,19);
            
            this.setTempatLelongan(LA.getTempatLelongan());            
            this.setWaktuLawatanTapak(callWLT());
            this.setWaktuTamatPendaftaran(callWTP());
            this.setWaktuLelongan(callWL());            
            this.setHargaRizab(LA.getHargaRizab());
            this.setDeposit(LA.getDeposit());
            this.setHargaBidaan(LA.getHargaBidaan());
            
            if(LA.getDepositBayaran() != null) {
                this.setDepositBayaran(LA.getDepositBayaran());
            } else {
                this.setDepositBayaran(cal());
            }
            if(LA.getBakiBayaran() != null) {
                this.setBakiBayaran(LA.getBakiBayaran());
            } else {
                this.setBakiBayaran(cal2());
            }
        }
        
        trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        if(trp != null){
            this.setNoWarta(trp.getNoWarta());
            this.setTarikhWarta(trp.getTarikhWarta());
        }
    }
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab","true");
    }
    
    public Resolution viewForm() {        
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        waktuLawatanTapak = callWLT();
        waktuTamatPendaftaran = callWTP();
        waktuLelongan = callWL();
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab","true");
    }
    
    public Resolution viewplusForm() {        
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        waktuLawatanTapak = callWLT();
        waktuTamatPendaftaran = callWTP();
        waktuLelongan = callWL();
        getContext().getRequest().setAttribute("viewplus", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab","true");
    }
    
    public String convMeridiem(String ap) {        
        if (ap.equalsIgnoreCase("AM")) {
                ap = "pagi";
            } else if (ap.equalsIgnoreCase("PM")) {
                ap = "petang";
            }
        return ap;
    }
    
    public String cut0 (String h) {
        if (h.substring(0).equals("0")) {
            h = h.substring(1);
        }
        return h;
    }
    
    public String callWLT() {
        waktuLawatanTapak = sdf1.format(LA.getTarikhLawatanTapak()).substring(11,13)+":"+ sdf1.format(LA.getTarikhLawatanTapak()).substring(14,16)+" ";
        String waktu = sdf1.format(LA.getTarikhLawatanTapak()).substring(17,19);
            if (waktu.equalsIgnoreCase("AM")) {
                waktu = "Pagi";
            } else if (waktu.equalsIgnoreCase("PM")) {
                waktu = "Petang";
            }
        return waktuLawatanTapak + waktu;
    }
    
    public String callWTP() {
        waktuTamatPendaftaran = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(11,13)+":"+ sdf1.format(LA.getTarikhTamatPendaftaran()).substring(14,16)+" ";
        String waktu = sdf1.format(LA.getTarikhTamatPendaftaran()).substring(17,19);
            if (waktu.equalsIgnoreCase("AM")) {
                waktu = "Pagi";
            } else if (waktu.equalsIgnoreCase("PM")) {
                waktu = "Petang";
            }
        return waktuTamatPendaftaran + waktu;
    }
    
    public String callWL() {
        waktuLelongan = sdf1.format(LA.getTarikhLelongan()).substring(11,13)+":"+ sdf1.format(LA.getTarikhLelongan()).substring(14,16)+" ";
        String waktu = sdf1.format(LA.getTarikhLelongan()).substring(17,19);
            if (waktu.equalsIgnoreCase("AM")) {
                waktu = "Pagi";
            } else if (waktu.equalsIgnoreCase("PM")) {
                waktu = "Petang";
            }
        return waktuLelongan + waktu;
    }
    
    public Resolution calc() {
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        
        if (stageId.equals("24KtpByrnDepst")) {
            BigDecimal dep = new BigDecimal (0.25);        
            depositBayaran = LA.getHargaBidaan().multiply(dep).subtract(LA.getDeposit());            
        } else if (stageId.equals("26PenyediaanSrtKelulusan")) {
            bakiBayaran = LA.getHargaBidaan().subtract(LA.getDepositBayaran()).subtract(LA.getDeposit());            
        }
        getContext().getRequest().setAttribute("calc", Boolean.TRUE);
        return new JSP("pelupusan/kira_bayaran.jsp").addParameter("tab","true");
    }
    
    // calculate and display harga bayaran deposit for user to save
    public BigDecimal cal() {
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        
        if (stageId.equals("24KtpByrnDepst")) {
            BigDecimal dep = new BigDecimal (0.25);        
            depositBayaran = LA.getHargaBidaan().multiply(dep).subtract(LA.getDeposit());           
        } 
        return depositBayaran;
    }
    
    // calculate and display harga bayaran baki for user to save
    public BigDecimal cal2() {
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        
        if (stageId.equals("26PenyediaanSrtKelulusan")) {
            bakiBayaran = LA.getHargaBidaan().subtract(LA.getDepositBayaran()).subtract(LA.getDeposit());            
        }
        return bakiBayaran;
    }
     
     public Resolution saveHarga() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
        LA = new LelonganAwam();
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        if(LA != null) {
            LA.setInfoAudit(disLaporanTanahService.findAudit(LA,"update",pguna));
            LA.setHargaRizab(hargaRizab);
            LA.setDeposit(deposit);
            pelupusanService.simpanLelonganAwam(LA);
            addSimpleMessage("Amaun Harga Rizab dan Deposit berjaya disimpan.");
        } else {
            addSimpleError("Maaf, tiada Maklumat Pelelongan yang telah dijumpai. Sila masukkan Maklumat Pelelongan terlebih dahulu.");
        }
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab", "true");
    }
    
    public Resolution saveCalc() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
        LA = new LelonganAwam();
        LA = pelupusanService.findLelonganAwamById(idPermohonan);
        Permohonan p = pelupusanService.findById(idPermohonan);
        mohonTuntutKos = new PermohonanTuntutanKos();
        
        if(LA != null) {
            LA.setInfoAudit(disLaporanTanahService.findAudit(LA,"update",peng));
            
            if (stageId.equals("24KtpByrnDepst")) {
                LA.setDepositBayaran(depositBayaran);
                
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBD25");        
                if(mohonTuntutKos != null) {            
                    mohonTuntutKos.setCawangan(p.getCawangan());
                    mohonTuntutKos.setPermohonan(p);
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBD25").getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBD25"));
                    mohonTuntutKos.setItem("Bayaran Deposit 25% Harga Bidaan Lelong");
                    mohonTuntutKos.setAmaunTuntutan(LA.getDepositBayaran());                    
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos,"update",peng));
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
                } else {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setCawangan(p.getCawangan());
                    mohonTuntutKos.setPermohonan(p);
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBD25").getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBD25"));
                    mohonTuntutKos.setItem("Bayaran Deposit 25% Harga Bidaan Lelong");
                    mohonTuntutKos.setAmaunTuntutan(LA.getDepositBayaran());
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos,"new",peng));
                    permohonanTuntutanKosDAO.save(mohonTuntutKos);
                }
            } 
            else if (stageId.equals("26PenyediaanSrtKelulusan")) {
                LA.setBakiBayaran(bakiBayaran);
                
                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBD75");        
                if(mohonTuntutKos != null) {            
                    mohonTuntutKos.setCawangan(p.getCawangan());
                    mohonTuntutKos.setPermohonan(p);
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBD75").getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBD75"));
                    mohonTuntutKos.setItem("Bayaran Baki 75% Harga Bidaan Lelong");
                    mohonTuntutKos.setAmaunTuntutan(LA.getBakiBayaran());
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos,"update",peng));
                    pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
                } else {
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setCawangan(p.getCawangan());
                    mohonTuntutKos.setPermohonan(p);
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBD75").getKodKewTrans());
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBD75"));
                    mohonTuntutKos.setItem("Bayaran Baki 75% Harga Bidaan Lelong");
                    mohonTuntutKos.setAmaunTuntutan(LA.getBakiBayaran());
                    mohonTuntutKos.setInfoAudit(disLaporanTanahService.findAudit(mohonTuntutKos,"new",peng));
                    permohonanTuntutanKosDAO.save(mohonTuntutKos);
                }
            }            
            pelupusanService.simpanLelonganAwam(LA);
            getContext().getRequest().setAttribute("calcDep", Boolean.TRUE);
            addSimpleMessage("Amaun bayaran berjaya disimpan.");
        }        
        return new JSP("pelupusan/kira_bayaran.jsp").addParameter("tab","true");
    }
    
    public Resolution saveData() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan p = pelupusanService.findById(idPermohonan);        
//        p.setSebab(this.getTujuanRezab());

        try {
//            pelupusanService.simpanPermohonan(p);
            LA = new LelonganAwam();
            LA = pelupusanService.findLelonganAwamById(idPermohonan);
            if(LA != null) {
                LA.setInfoAudit(disLaporanTanahService.findAudit(LA,"update",pguna));
                LA.setBayaranBorang(bayaranBorang);
                LA.setTarikhMulaBorang(tarikhMulaBorang);
                LA.setTarikhAkhirBorang(tarikhAkhirBorang);
                LA.setTarikhLawatanTapak(sdf1.parse(tarikhLawatanTapak + " " + jam + ":" + minit + " " + ampm));                
                LA.setWaktuLawatanTapak(cut0(jam) + ":" + minit + " " + convMeridiem(ampm));
                LA.setTarikhTamatPendaftaran(sdf1.parse(tarikhTamatPendaftaran + " " + jam1 + ":" + minit1 + " " + ampm1));                
                LA.setWaktuTamatPendaftaran(cut0(jam1) + ":" + minit1 + " " + convMeridiem(ampm1));
                LA.setTarikhLelongan(sdf1.parse(tarikhLelongan + " " + jam2 + ":" + minit2 + " " + ampm2));
                LA.setWaktuLelongan(cut0(jam2) + ":" + minit2 + " " + convMeridiem(ampm2));
                LA.setTempatLelongan(tempatLelongan);
                pelupusanService.simpanLelonganAwam(LA);
                addSimpleMessage("Maklumat Pelelongan berjaya dikemaskini.");
            } else {
                LelonganAwam lelonganAwam = new LelonganAwam();
                lelonganAwam = (LelonganAwam) disLaporanTanahService.findObject(lelonganAwam,new String[]{idPermohonan},1);
                if(lelonganAwam != null) {
                    LA = new LelonganAwam();
                    LA.setPermohonan(p);
                    LA.setBayaranBorang(bayaranBorang);
                    LA.setTarikhMulaBorang(tarikhMulaBorang);
                    LA.setTarikhMulaBorang(tarikhMulaBorang);
                    LA.setTarikhAkhirBorang(tarikhAkhirBorang);
                    LA.setTarikhLawatanTapak(sdf1.parse(tarikhLawatanTapak + " " + jam + ":" + minit + " " + ampm));
                    LA.setWaktuLawatanTapak(cut0(jam) + ":" + minit + " " + convMeridiem(ampm));
                    LA.setTarikhTamatPendaftaran(sdf1.parse(tarikhTamatPendaftaran + " " + jam1 + ":" + minit1 + " " + ampm1));                
                    LA.setWaktuTamatPendaftaran(cut0(jam1) + ":" + minit1 + " " + convMeridiem(ampm1));
                    LA.setTarikhLelongan(sdf1.parse(tarikhLelongan + " " + jam2 + ":" + minit2 + " " + ampm2));
                    LA.setWaktuLelongan(cut0(jam2) + ":" + minit2 + " " + convMeridiem(ampm2));
                    LA.setTempatLelongan(tempatLelongan);
                    LA.setInfoAudit(disLaporanTanahService.findAudit(LA,"new",pguna));
                    pelupusanService.simpanLelonganAwam(LA);
                    addSimpleMessage("Maklumat Pelelongan berjaya disimpan.");
                } else {
                    addSimpleError("Maaf, maklumat tidak berjaya disimpan.");
                }
            }          
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab","true");
    }

    public Resolution saveWarta () {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = pelupusanService.findById(idPermohonan);
        
        try {
            trp = new TanahRizabPermohonan();
            trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if(trp != null){
                trp.setInfoAudit(disLaporanTanahService.findAudit(trp,"update",pguna));
                trp.setNoWarta(noWarta);
                trp.setTarikhWarta(tarikhWarta);
                pelupusanService.simpanTanahRizabPermohonan(trp);
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
                addSimpleMessage("Nombor dan Tarikh Warta telah berjaya disimpan.");
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik,new String[]{idPermohonan},1);
                if(mohonHakmilik != null){
                    trp = new TanahRizabPermohonan();
                    trp.setPermohonan(p);
                    trp.setCawangan(p.getCawangan());
                    trp.setDaerah(p.getCawangan().getDaerah());
                    trp.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru()!=null?mohonHakmilik.getBandarPekanMukimBaru():mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    trp.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot())?mohonHakmilik.getNoLot():mohonHakmilik.getHakmilik().getNoLot());
                    trp.setNoWarta(noWarta);
                    trp.setTarikhWarta(tarikhWarta);
                    trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "new", pguna));                    
                    trp.setAktif(aktif);
                    pelupusanService.simpanTanahRizabPermohonan(trp);
                    getContext().getRequest().setAttribute("view", Boolean.TRUE);
                    addSimpleMessage("Maklumat Warta berjaya disimpan.");
                }
            }        
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        return new JSP("pelupusan/maklumat_pelelongan.jsp").addParameter("tab","true");            
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
    
    public String getJam() {
        return jam;
    }
    
    public void setJam(String jam) {
        this.jam = jam;
    }
    
    public String getMinit() {
        return minit;
    }
    
    public void setMinit(String minit) {
        this.minit = minit;
    }
    
    public String getAmpm() {
        return ampm;
    }
    
    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }
           
    public String getJam1() {
        return jam1;
    }
    
    public void setJam1(String jam1) {
        this.jam1 = jam1;
    }
    
    public String getMinit1() {
        return minit1;
    }
    
    public void setMinit1(String minit1) {
        this.minit1 = minit1;
    }
    
    public String getAmpm1() {
        return ampm1;
    }
    
    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }
    
    public String getJam2() {
        return jam2;
    }
    
    public void setJam2(String jam2) {
        this.jam2 = jam2;
    }
    
    public String getMinit2() {
        return minit2;
    }
    
    public void setMinit2(String minit2) {
        this.minit2 = minit2;
    }
    
    public String getAmpm2() {
        return ampm2;
    }
    
    public void setAmpm2(String ampm2) {
        this.ampm2 = ampm2;
    }    
    
    // LelonganAwam
    
    public BigDecimal getBayaranBorang() {
        return bayaranBorang;
    }

    public void setBayaranBorang(BigDecimal bayaranBorang) {
        this.bayaranBorang = bayaranBorang;
    }
    
    public Date getTarikhMulaBorang() {
        return tarikhMulaBorang;
    }

    public void setTarikhMulaBorang(Date tarikhMulaBorang) {
        this.tarikhMulaBorang = tarikhMulaBorang;
    }
    
    public Date getTarikhAkhirBorang() {
        return tarikhAkhirBorang;
    }

    public void setTarikhAkhirBorang(Date tarikhAkhirBorang) {
        this.tarikhAkhirBorang = tarikhAkhirBorang;
    }
    
    public String getTarikhLawatanTapak() {
        return tarikhLawatanTapak;
    }

    public void setTarikhLawatanTapak(String tarikhLawatanTapak) {
        this.tarikhLawatanTapak = tarikhLawatanTapak;
    }
    
    public String getWaktuLawatanTapak() {
        return waktuLawatanTapak;
    }

    public void setWaktuLawatanTapak(String waktuLawatanTapak) {
        this.waktuLawatanTapak = waktuLawatanTapak;
    }
    
    public String getTarikhTamatPendaftaran() {
        return tarikhTamatPendaftaran;
    }

    public void setTarikhTamatPendaftaran(String tarikhTamatPendaftaran) {
        this.tarikhTamatPendaftaran = tarikhTamatPendaftaran;
    }
    
    public String getWaktuTamatPendaftaran() {
        return waktuTamatPendaftaran;
    }

    public void setWaktuTamatPendaftaran(String waktuTamatPendaftaran) {
        this.waktuTamatPendaftaran = waktuTamatPendaftaran;
    }
    
    public String getTarikhLelongan() {
        return tarikhLelongan;
    }

    public void setTarikhLelongan(String tarikhLelongan) {
        this.tarikhLelongan = tarikhLelongan;
    }
    
    public String getWaktuLelongan() {
        return waktuLelongan;
    }

    public void setWaktuLelongan(String waktuLelongan) {
        this.waktuLelongan = waktuLelongan;
    }
    
    public String getTempatLelongan() {
        return tempatLelongan;
    }

    public void setTempatLelongan(String tempatLelongan) {
        this.tempatLelongan = tempatLelongan;
    }
    
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    
    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }
    
    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }
    
    public BigDecimal getDepositBayaran() {
        return depositBayaran;
    }

    public void setDepositBayaran(BigDecimal depositBayaran) {
        this.depositBayaran = depositBayaran;
    }
    
    public BigDecimal getBakiBayaran() {
        return bakiBayaran;
    }

    public void setBakiBayaran(BigDecimal bakiBayaran) {
        this.bakiBayaran = bakiBayaran;
    }
    
    // Permohonan
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    // HakmilikPermohonan
    
    public KodBandarPekanMukim getBandarPekanMukimBaru() {
        return bandarPekanMukim;
    }
    
    // TanahRizabPermohonan
    
    public String getTujuanRezab() {
        return tujuanRezab;
    }

    public void setTujuanRezab(String tujuanRezab) {
        this.tujuanRezab = tujuanRezab;
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
    
    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }
    
    // KodBandarPekanMukim
    
    public String getNama() {
        return nama;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO ;
import etanah.dao.PermohonanJenteraDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/butiran_jentera")
public class ButiranJenteraActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(ButiranBahanBatuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO ;
    @Inject
    etanah.Configuration conf;
    
    private KodBandarPekanMukim kodBPM;
    private PermohonanBahanBatuan permohonanBahanBatuan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanJentera permohonanJentera ;
    private KodBahanBatu jenisBahanBatu;
    private String kawasanAmbilBatuan;
    private String kawasanPindahBatuan;
    private String jalanDilalui;
    private String tarikhMula;
    private String tarikhTamat;
    private Integer tempohKeluar;
    private Character unitTempohKeluar;
    private BigDecimal jumlahIsipadu;
    private BigDecimal anggaranMuatan;
    private BigDecimal jumlahIsipaduDipohon;
    private BigDecimal lebarBangunanSementara;
    private BigDecimal panjangBangunanSementara;
    private String bilanganPekerja;
    private String idPermohonan ;
    private String id ;
    private List<PermohonanJentera> listJentera ;
    private Pengguna pengguna ;
    private String pemilik ;
    private String jenisJentera ;
    private String pndftran ;
    private Long idJentera ;
    private String stageId;
    private String kodNegeri;
    private boolean view;

    
//    public Resolution showForm() {
//        return new JSP("pelupusan/bahan_batuan_ns.jsp").addParameter("tab", "true");
//    }
//
//    public Resolution showForm1() {
//        return new JSP("pelupusan/butiran_bahan_batuan.jsp").addParameter("tab", "true");
//    }
    @DefaultHandler
    public Resolution showForm() {
//		getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/batuan/butiran_mesin_jentera_mlk.jsp").addParameter("tab", "true");
    }
    
    public Resolution viewForm() {
        view = Boolean.TRUE ;
        return new JSP("pelupusan/batuan/butiran_mesin_jentera_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("pelupusan/batuan/add_butiran_mesin_jentera_mlk.jsp").addParameter("popup", "true");
    }
    
    public Resolution updateJenteraPopup() {
        String id = getContext().getRequest().getParameter("idJentera") ;
        idJentera = Long.parseLong(id) ;
        permohonanJentera = permohonanJenteraDAO.findById(idJentera) ;
        if(permohonanJentera != null){
            jenisJentera = permohonanJentera.getJenisJentera() ;
            pndftran=permohonanJentera.getNoPendaftaranJentera();
            pemilik=permohonanJentera.getKepunyaan();
        }
      return new JSP("pelupusan/batuan/edit_mesin_jentera_mlk.jsp").addParameter("popup", "true");
      }

//    public Resolution editJentera() {
//        return new JSP("pelupusan/batuan/butiran_mesin_jentera2.jsp").addParameter("popup", "true");
//    }
//    
    public Resolution refreshpage() {
        
        rehydrate();
//        return new RedirectResolution(ButiranBahanBatuanActionBean.class, "locate");
         return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/butiran_mesin_jentera_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
//    if(permohonan != null){
        permohonanBahanBatuan = pelupusanService.findByIdMBB(idPermohonan) ;
//        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        listJentera = permohonanJenteraDAO.findByEqualCriterias(tname, tvalue, null) ;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
//        }

    }

    
    
    public Resolution simpanJentera(){
       // permohonanJentera = pelupusanService.findJenteraById(idPermohonan) ;
        permohonanJentera = new PermohonanJentera();
        InfoAudit infoAudit = new InfoAudit() ;
        
            
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanJentera.setInfoAudit(infoAudit) ;
            permohonanJentera.setPermohonan(permohonan);
            permohonanJentera.setJenisJentera(jenisJentera);
            permohonanJentera.setNoPendaftaranJentera(pndftran);
            permohonanJentera.setKepunyaan(pemilik);

            //permohonanJenteraDAO.saveOrUpdate(permohonanJentera) ;
            pelupusanService.simpanJentera(permohonanJentera) ;
        
        addSimpleMessage("Maklumat telah berjaya disimpan") ;
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/butiran_mesin_jentera_mlk.jsp").addParameter("tab", "true");
    }
    
     public Resolution deleteJentera()  {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanJentera pj = new PermohonanJentera();
        InfoAudit infoAudit = new InfoAudit() ;
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        
    //    String noJentera = getContext().getRequest().getParameter("noPendaftaranJentera");
        String id = getContext().getRequest().getParameter("idJentera") ;
        idJentera = Long.parseLong(id) ;
        permohonanJentera = permohonanJenteraDAO.findById(idJentera) ;
        if(permohonanJentera != null){
//            ia = peng.getInfoAudit();
//           infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            pj.setInfoAudit(infoAudit) ;
//            pj.setPermohonan(permohonanJentera.getPermohonan());
//            pj.setJenisJentera(permohonanJentera.getJenisJentera());
//            pj.setNoPendaftaranJentera(permohonanJentera.getNoPendaftaranJentera());
//            pj.setKepunyaan(permohonanJentera.getKepunyaan());
            
            
            pelupusanService.deleteJentera(permohonanJentera);
        }
        addSimpleMessage("Rekod telah dihapuskan");
       return new RedirectResolution(ButiranJenteraActionBean.class, "locate");
            
      }
     
    public Resolution updateJentera() {
        addSimpleMessage("Maklumat telah berjaya disimpan") ;
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanJentera pj = new PermohonanJentera();
        InfoAudit infoAudit = new InfoAudit() ;
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        
    // String noJentera = getContext().getRequest().getParameter("noPendaftaranJentera");
        String a = getContext().getRequest().getParameter("idJentera") ;
        jenisJentera = getContext().getRequest().getParameter("jenisJentera") ;
        pndftran = getContext().getRequest().getParameter("pndftran") ;
        pemilik = getContext().getRequest().getParameter("pemilik") ;
        idJentera = Long.parseLong(a) ;
            System.out.println(idJentera);
            System.out.println(jenisJentera);
            System.out.println(pndftran);
            System.out.println(pemilik);
        permohonanJentera = permohonanJenteraDAO.findById(idJentera) ;
        if(permohonanJentera != null){
//            ia = peng.getInfoAudit();
           infoAudit.setDimasukOleh(permohonanJentera.getInfoAudit().getDimasukOleh());
            infoAudit.setTarikhMasuk(permohonanJentera.getInfoAudit().getTarikhMasuk());
            permohonanJentera.setPermohonan(permohonanJentera.getPermohonan());
            permohonanJentera.setInfoAudit(infoAudit) ;
            permohonanJentera.setJenisJentera(jenisJentera);
            permohonanJentera.setNoPendaftaranJentera(pndftran);
            permohonanJentera.setKepunyaan(pemilik);


//            permohonanJenteraDAO.saveOrUpdate(permohonanJentera) ;
        pelupusanService.simpanJentera(permohonanJentera) ;
        
    
        }
        
         addSimpleMessage("Maklumat telah berjaya disimpan") ;
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/batuan/butiran_mesin_jentera_mlk.jsp").addParameter("tab", "true");
        
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

    public BigDecimal getAnggaranMuatan() {
        return anggaranMuatan;
    }

    public void setAnggaranMuatan(BigDecimal anggaranMuatan) {
        this.anggaranMuatan = anggaranMuatan;
    }

    public String getBilanganPekerja() {
        return bilanganPekerja;
    }

    public void setBilanganPekerja(String bilanganPekerja) {
        this.bilanganPekerja = bilanganPekerja;
    }

    public String getJalanDilalui() {
        return jalanDilalui;
    }

    public void setJalanDilalui(String jalanDilalui) {
        this.jalanDilalui = jalanDilalui;
    }

    public KodBahanBatu getJenisBahanBatu() {
        return jenisBahanBatu;
    }

    public void setJenisBahanBatu(KodBahanBatu jenisBahanBatu) {
        this.jenisBahanBatu = jenisBahanBatu;
    }

    public BigDecimal getJumlahIsipadu() {
        return jumlahIsipadu;
    }

    public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
        this.jumlahIsipadu = jumlahIsipadu;
    }

    public BigDecimal getJumlahIsipaduDipohon() {
        return jumlahIsipaduDipohon;
    }

    public void setJumlahIsipaduDipohon(BigDecimal jumlahIsipaduDipohon) {
        this.jumlahIsipaduDipohon = jumlahIsipaduDipohon;
    }

    public String getKawasanAmbilBatuan() {
        return kawasanAmbilBatuan;
    }

    public void setKawasanAmbilBatuan(String kawasanAmbilBatuan) {
        this.kawasanAmbilBatuan = kawasanAmbilBatuan;
    }

    public String getKawasanPindahBatuan() {
        return kawasanPindahBatuan;
    }

    public void setKawasanPindahBatuan(String kawasanPindahBatuan) {
        this.kawasanPindahBatuan = kawasanPindahBatuan;
    }

    public BigDecimal getLebarBangunanSementara() {
        return lebarBangunanSementara;
    }

    public void setLebarBangunanSementara(BigDecimal lebarBangunanSementara) {
        this.lebarBangunanSementara = lebarBangunanSementara;
    }

    public BigDecimal getPanjangBangunanSementara() {
        return panjangBangunanSementara;
    }

    public void setPanjangBangunanSementara(BigDecimal panjangBangunanSementara) {
        this.panjangBangunanSementara = panjangBangunanSementara;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public Integer getTempohKeluar() {
        return tempohKeluar;
    }

    public void setTempohKeluar(Integer tempohKeluar) {
        this.tempohKeluar = tempohKeluar;
    }

    public Character getUnitTempohKeluar() {
        return unitTempohKeluar;
    }

    public void setUnitTempohKeluar(Character unitTempohKeluar) {
        this.unitTempohKeluar = unitTempohKeluar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanBahanBatuan getPermohonanBahanBatuan() {
        return permohonanBahanBatuan;
    }

    public void setPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        this.permohonanBahanBatuan = permohonanBahanBatuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public List<PermohonanJentera> getListJentera() {
        return listJentera;
    }

    public void setListJentera(List<PermohonanJentera> listJentera) {
        this.listJentera = listJentera;
    }

    public PermohonanJentera getPermohonanJentera() {
        return permohonanJentera;
    }

    public void setPermohonanJentera(PermohonanJentera permohonanJentera) {
        this.permohonanJentera = permohonanJentera;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getPndftran() {
        return pndftran;
    }

    public void setPndftran(String pndftran) {
        this.pndftran = pndftran;
    }

    public String getJenisJentera() {
        return jenisJentera;
    }

    public void setJenisJentera(String jenisJentera) {
        this.jenisJentera = jenisJentera;
    }

    public Long getIdJentera() {
        return idJentera;
    }

    public void setIdJentera(Long idJentera) {
        this.idJentera = idJentera;
    }
    
    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

   public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    
}



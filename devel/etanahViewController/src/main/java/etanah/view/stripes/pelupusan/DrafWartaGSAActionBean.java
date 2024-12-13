/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.service.PermohonanLaporanPelanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/*
 * @author Navin
 */
@UrlBinding("/pelupusan/draf_wartaGSA")
public class DrafWartaGSAActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanLaporanPelanService permohonanLaporanPelanService;
    private InfoAudit ia;
    private Pengguna peng;
    private String idPermohonan;
    private PermohonanLaporanKawasan permohonanLaporanKawasan;
    private String stageId;
    private String taskId;
    private Pengguna pguna;
    private static final Logger LOG = Logger.getLogger(DrafWartaGSAActionBean.class);
    private TanahRizabPermohonan MTR;
    private String noWarta;
    private Date tarikhWarta;
    private Pengguna pengguna;        
    private KodCawangan kodCaw;   
    private String nWarta;
    private Date tWarta;
    private Date terimaWarta;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        return new JSP("pelupusan/draf_warta_gsa.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanLaporanKawasan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
        return new JSP("pelupusan/draf_warta_gsa.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanLaporanKawasan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
        if (permohonan != null) {
            permohonanLaporanKawasan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
        }
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);

        if (permohonan.getKodUrusan().getKod().equals("PWGSA")) {
            rehydratePWGSA();
        }
        
        else {
            rehydrateOther();}
            
    }

    public void rehydratePWGSA() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        MTR = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);

        if(MTR != null){
        this.setNoWarta(MTR.getNoWarta());
        this.setTarikhWarta(MTR.getTarikhWarta());
        }
    }
    
     public void rehydrateOther() {
        if (permohonanLaporanKawasan != null) {
            nWarta = permohonanLaporanKawasan.getNoWarta();
            tWarta = permohonanLaporanKawasan.getTarikhWarta();
            terimaWarta = permohonanLaporanKawasan.getTarikhTerimaWarta();
        }
        
        else
        {
            nWarta = "";
            tWarta = null;
            terimaWarta = null;
        }
   
    }
    
    public Resolution sediaDraftWartaSimpan() {
        
        ia = new InfoAudit();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);   
        
        LOG.info("-----------------id mohon------------------ " + permohonan.getIdPermohonan());
        LOG.info("-----------------kod cawangan------------------ " + permohonan.getCawangan().getKod());
        LOG.info("-----------------kod daerah------------------ "+permohonan.getCawangan().getDaerah().getKod());
        
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {

            MTR = new TanahRizabPermohonan();
            MTR = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            
            if (MTR != null) {               
                MTR.setInfoAudit(disLaporanTanahService.findAudit(MTR, "update", pguna));                
                MTR.setNoWarta(noWarta);
                MTR.setTarikhWarta(tarikhWarta);
                pelupusanService.simpanTanahRizabPermohonan(MTR);
                addSimpleMessage("Maklumat Warta berjaya dikemaskini.");
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                
                MTR = new TanahRizabPermohonan();
                MTR.setPermohonan(permohonan);
                MTR.setCawangan(permohonan.getCawangan());
                MTR.setDaerah(permohonan.getCawangan().getDaerah());
                MTR.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                MTR.setInfoAudit(disLaporanTanahService.findAudit(MTR, "new", pguna));
                MTR.setNoWarta(noWarta);
                MTR.setTarikhWarta(tarikhWarta);
                pelupusanService.simpanTanahRizabPermohonan(MTR);
                addSimpleMessage("Maklumat Warta berjaya disimpan.");
//                addSimpleError(idPermohonan + "Maklumat tidak dijumpai.");
            }
        } else {            
//             ia.setDimasukOleh(peng);
//             ia.setTarikhMasuk(new java.util.Date());
            PermohonanLaporanKawasan permohonanLaporanKawasanTemp = null;
                       
            if (Validation()) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                return new JSP("pelupusan/draf_warta_gsa.jsp").addParameter("tab", "true");
            }
                
            if (permohonanLaporanKawasan != null) {
                ia = permohonanLaporanKawasan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanKawasanTemp = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
                if (permohonanLaporanKawasanTemp != null) {
                    permohonanLaporanKawasanTemp.setTarikhWarta(tWarta);
                    permohonanLaporanKawasanTemp.setTarikhTerimaWarta(terimaWarta);
                    permohonanLaporanKawasanTemp.setNoWarta(nWarta);
                    permohonanLaporanKawasanTemp.setInfoAudit(ia);
                    permohonanLaporanKawasanTemp.setPermohonan(permohonan);
                    permohonanLaporanKawasanTemp = permohonanLaporanPelanService.saveOrUpdate(permohonanLaporanKawasanTemp);
                    addSimpleMessage("Maklumat telah berjaya dikemaskini");
                }
            }
            
            else {     
                    InfoAudit infoAudit = new InfoAudit() ;
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());

                    permohonanLaporanKawasan = new PermohonanLaporanKawasan();
                    permohonanLaporanKawasan.setPermohonan(permohonan);
                    permohonanLaporanKawasan.setInfoAudit(infoAudit);
                    permohonanLaporanKawasan.setNoWarta(nWarta);
                    permohonanLaporanKawasan.setTarikhWarta(tWarta);
                    permohonanLaporanKawasan.setTarikhTerimaWarta(terimaWarta);
                    permohonanLaporanKawasan.setKodCawangan(permohonan.getCawangan());
                    pelupusanService.simpanPermohonanLaporKawasan(permohonanLaporanKawasan);
                    addSimpleMessage("Maklumat telah berjaya disimpan") ;
            }
      
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/draf_warta_gsa.jsp").addParameter("tab", "true");
    }

    public boolean Validation() {
        boolean flag = false;

//        if ((permohonanLaporanKawasan != null) && (terimaWarta == null)) {
//            flag = true;
//            addSimpleError("Sila Masukkan Tarikh Terima Warta");
//        } else if ((permohonanLaporanKawasan != null) && (tWarta == null)) {
//            flag = true;
//            addSimpleError("Sila Masukkan Tarikh Warta");
//        } else if ((permohonanLaporanKawasan != null) && (nWarta == null)) {
//            flag = true;
//            addSimpleError("Sila Masukkan No. Warta");
//        }
        
        if (terimaWarta == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Terima Warta");
        } else if (tWarta == null) {
            flag = true;
            addSimpleError("Sila Masukkan Tarikh Warta");
        } else if (nWarta == null) {
            flag = true;
            addSimpleError("Sila Masukkan No. Warta");
        }
        return flag;
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        LOG.info(" ---stageId : " + stageId);
//      else {
//            stageId = "g_penyediaan_pu";
//        }
        return stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    // MTR
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
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getnWarta() {
        return nWarta;
    }

    public void setnWarta(String nWarta) {
        this.nWarta = nWarta;
    }

    public Date gettWarta() {
        return tWarta;
    }

    public void settWarta(Date tWarta) {
        this.tWarta = tWarta;
    }

    public Date getTerimaWarta() {
        return terimaWarta;
    }

    public void setTerimaWarta(Date terimaWarta) {
        this.terimaWarta = terimaWarta;
    }
    
}

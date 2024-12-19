/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRizabDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRizab;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.TanahRizabPermohonan;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.PermohonanLaporanPelanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author User
 */

@UrlBinding("/pelupusan/sedia_draf_war")
public class SediaDraftWartaActionBean extends AbleActionBean {
    
    private Permohonan permohonan;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanLaporanPelanService permohonanLaporanPelanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikDAO hakmilikDAO;
    
    private static final Logger LOG = Logger.getLogger(SediaDraftWartaActionBean.class);
    private InfoAudit ia;
    private Pengguna peng;
    private String idPermohonan;
    private String sebab;
    private String catatan;
    private String nilaiKeputusan;
    private String ulasan;
    private String kodNegeri;
    private PermohonanLaporanKawasan permohonanLaporanPelan;
    private List<HakmilikPermohonan> mohanHakmilikList;
    private HakmilikPermohonan hakmilikPermohonan;
    private TanahRizabPermohonan trizabPermohonan;
    private String noPW;
    private String stageId;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        permohonanLaporanPelan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
        if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
            if (permohonan.getPermohonanSebelum() != null) {
                mohanHakmilikList = permohonanLaporanPelanService.getHakmilikPermohan(permohonan.getIdPermohonan());
            }
        }
        else {
            mohanHakmilikList = permohonanLaporanPelanService.getHakmilikPermohan(permohonan.getIdPermohonan());
        }
        return new JSP("pelupusan/sedia_draft_warta.jsp").addParameter("tab", "true");
    }

    public Resolution view() {
                
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
//        return new JSP("pelupusan/sedia_draft_warta.jsp").addParameter("tab","true");
        return new JSP("pelupusan/view_draft_warta.jsp").addParameter("tab","true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.debug("______________________________ entering rehydrate() ______________________________");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);        
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        mohanHakmilikList = pelupusanService.getHakmilikPermohonan(idPermohonan);
        permohonanLaporanPelan = permohonanLaporanPelanService.getPermohonanLaporanKawasan(idPermohonan);
        trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTGSA")) {
            PermohonanLaporanKawasan laporKWS = new PermohonanLaporanKawasan();
            laporKWS = pelupusanService.findLaporanKawasanByIdPermohonan(idPermohonan);
            if (laporKWS != null) {
                if (laporKWS.getNoWartaDahulu() == null) { laporKWS.setNoWartaDahulu(!StringUtils.isEmpty(laporKWS.getNoWarta()) ? laporKWS.getNoWarta() : laporKWS.getNoWarta()); }
                if (laporKWS.getTarikhWartaDahulu() == null) { laporKWS.setTarikhWartaDahulu(laporKWS.getTarikhWarta() != null ? laporKWS.getTarikhWarta() : laporKWS.getTarikhWarta()); }
            }
        }
        
        if(permohonan.getKodUrusan().getKod().equals("PWGSA") || permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//            HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
//            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//            Hakmilik hak = new Hakmilik();
//            hak = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            trizabPermohonan = new TanahRizabPermohonan();
            trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            
//            if(trizabPermohonan.getCatatan() != null) {
//                sebab = trizabPermohonan.getCatatan();                        
//            }
            
            if(trizabPermohonan != null && trizabPermohonan.getNoPW() != null) {
                noPW = trizabPermohonan.getNoPW();
            }
//        } else {
            sebab = permohonan.getSebab();
        }
     }

    public Resolution sediaDraftWartaSimpan() {
            try {
             ia = new InfoAudit();
             peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
             ia.setDimasukOleh(peng);
             ia.setTarikhMasuk(new java.util.Date());
             idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
             permohonan = permohonanService.findById(idPermohonan);                
             
//             if(permohonan.getKodUrusan().getKod().equals("PWGSA")) {
//                TanahRizabPermohonan mtr = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
//                mtr.setCatatan(sebab);
//             } else {
                 permohonan.setSebab(sebab);
//             }            
             
             permohonanLaporanPelan.setPermohonan(permohonan);
             KodCawangan kodCawangan = kodCawanganDAO.findById(permohonan.getCawangan().getKod());
             permohonanLaporanPelan.setKodCawangan(kodCawangan);
             //KodRizab kodRizab = kodRizabDAO.findById(1);
             //permohonanLaporanPelan.setKodRizab(kodRizab);
             hakmilikPermohonan = pelupusanService.findByIdPermohonan(permohonan.getIdPermohonan());
             permohonanLaporanPelan.setInfoAudit(ia);
             permohonanLaporanPelan = permohonanLaporanPelanService.saveOrUpdate(permohonanLaporanPelan);
             if (!permohonan.getKodUrusan().getKod().equalsIgnoreCase("PWGSA")) {
                if(permohonan.getPermohonanSebelum() != null){
                    mohanHakmilikList = permohonanLaporanPelanService.getHakmilikPermohan(permohonan.getIdPermohonan());
                }
             } else {
               mohanHakmilikList = permohonanLaporanPelanService.getHakmilikPermohan(permohonan.getIdPermohonan());  
             }
             addSimpleMessage("Maklumat telah berjaya disimpan");
            } catch(Exception e) {
                LOG.error(e.getMessage());
            }
            return new JSP("pelupusan/sedia_draft_warta.jsp").addParameter("tab", "true");
    }

    public String stageId(String taskId) {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNilaiKeputusan() {
        return nilaiKeputusan;
    }

    public void setNilaiKeputusan(String nilaiKeputusan) {
        this.nilaiKeputusan = nilaiKeputusan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanKawasan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public PermohonanLaporanPelanService getPermohonanLaporanPelanService() {
        return permohonanLaporanPelanService;
    }

    public void setPermohonanLaporanPelanService(PermohonanLaporanPelanService permohonanLaporanPelanService) {
        this.permohonanLaporanPelanService = permohonanLaporanPelanService;
    }

    public List<HakmilikPermohonan> getMohanHakmilikList() {
        return mohanHakmilikList;
    }

    public void setMohanHakmilikList(List<HakmilikPermohonan> mohanHakmilikList) {
        this.mohanHakmilikList = mohanHakmilikList;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    public TanahRizabPermohonan getTrizabPermohonan() {
        return trizabPermohonan;
    }

    public void setTrizabPermohonan(TanahRizabPermohonan trizabPermohonan) {
        this.trizabPermohonan = trizabPermohonan;
    }
    
    public String getNoPW() {
        return noPW;
    }

    public void setNoPW(String noPW) {
        this.noPW = noPW;
    }    
}

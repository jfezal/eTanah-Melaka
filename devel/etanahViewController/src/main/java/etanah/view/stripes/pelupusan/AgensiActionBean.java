/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodAgensiDAO;
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
import etanah.service.AgensiService;
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
import etanah.model.KodAgensi;
import etanah.model.KodUrusanAgensi;
import etanah.model.PermohonanRujukanLuar;
import org.apache.log4j.Logger;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.model.KodJabatan;
import etanah.model.KodRujukan;

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/masuk_agensi")
public class AgensiActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(AgensiActionBean.class);
    private Permohonan permohonan;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    etanah.Configuration conf;
    @Inject
    AgensiService agensiService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    
    private String agensi;    
    private KodUrusanAgensi kodUrusanAgensi;
    private InfoAudit ia;
    private Pengguna peng;
    private String idPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<KodUrusanAgensi> senaraiKodUrusanAgensi;
    private List<KodAgensi> senaraiKodAgensi;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private InfoAudit infoAudit;
    private Pengguna pengguna;
    private KodAgensi kodAgensi;
    
    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
       return new JSP("pelupusan/masukagensi.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        senaraiKodAgensi = agensiService.getSenaraiAgensiWarta();
        //KodAgensi kodAgensi = kodAgensiDAO.findById(kodUrusanAgensi.getKodAgensi().getKod());
        permohonanRujukanLuar = agensiService.findPermohonanRujByIdPermohonan(idPermohonan);
        if(permohonanRujukanLuar != null)
        {
            agensi = permohonanRujukanLuar.getAgensi().getKod();
            LOG.info("getKod().............. " + permohonanRujukanLuar.getAgensi().getKod());
        }
     }
    
    public Resolution SimpanAgensi() {
            try
            {
                permohonanRujukanLuar = agensiService.findPermohonanRujByIdPermohonan(idPermohonan);
                pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//                Hakmilik hak = new Hakmilik();
//                hak = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                infoAudit = pengguna.getInfoAudit();
                kodAgensi = agensiService.findNameAgensi(agensi);
                LOG.info("kodAgensi.getNama().............. " + kodAgensi.getNama());
                if(permohonanRujukanLuar != null)
                {
                    permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                    permohonanRujukanLuar.setPermohonan(permohonan);
                    KodAgensi kodAgensi = new KodAgensi();
                    kodAgensi = kodAgensiDAO.findById(agensi);
                    permohonanRujukanLuar.setAgensi(kodAgensi);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    KodRujukan r = kodRujukanDAO.findById("WT");
                    permohonanRujukanLuar.setKodRujukan(r);
                    permohonanRujukanLuar.setInfoAudit(infoAudit);
//                    permohonanRujukanLuar.setHakmilik(hak);
                    permohonanRujukanLuar.setNamaAgensi(kodAgensi.getNama());
                    //KodJabatan kj = kodJabatanDAO.findById(kodJabatan);
                    //permohonanRujukanLuar.setJabatan(kj);
                    agensiService.simpanpermohonanRujukanLuar(permohonanRujukanLuar);
                }
                else
                {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                    permohonanRujukanLuar.setPermohonan(permohonan);
                    infoAudit.setDimasukOleh(pengguna);
                    KodAgensi kodAgensi = new KodAgensi();
                    kodAgensi = kodAgensiDAO.findById(agensi);
                    permohonanRujukanLuar.setAgensi(kodAgensi);
                    KodRujukan r = kodRujukanDAO.findById("WT");
                    permohonanRujukanLuar.setKodRujukan(r);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohonanRujukanLuar.setInfoAudit(infoAudit);
//                    permohonanRujukanLuar.setHakmilik(hak);
                    permohonanRujukanLuar.setNamaAgensi(kodAgensi.getNama());
                    agensiService.simpanpermohonanRujukanLuar(permohonanRujukanLuar);
                }
                addSimpleMessage("Maklumat telah berjaya disimpan");
            }catch(Exception e){
                e.printStackTrace();
            }
             return new JSP("pelupusan/masukagensi.jsp").addParameter("tab", "true");
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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    public List<KodAgensi> getSenaraiKodAgensi() {
        return senaraiKodAgensi;
    }

    public void setSenaraiKodAgensi(List<KodAgensi> senaraiKodAgensi) {
        this.senaraiKodAgensi = senaraiKodAgensi;
    }

    public List<KodUrusanAgensi> getSenaraiKodUrusanAgensi() {
        return senaraiKodUrusanAgensi;
    }

    public void setSenaraiKodUrusanAgensi(List<KodUrusanAgensi> senaraiKodUrusanAgensi) {
        this.senaraiKodUrusanAgensi = senaraiKodUrusanAgensi;
    }
    
    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }
    
}

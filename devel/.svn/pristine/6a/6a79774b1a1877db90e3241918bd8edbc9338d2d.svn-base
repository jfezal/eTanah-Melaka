/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.text.ParseException;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.SyaratPendudukanDAO;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.LaporanTandaSempadan;
import etanah.model.Permohonan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/syaratPendudukan")
public class KemasukanSyaratPendudukanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KemasukanSyaratPendudukanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikDAO hakmilikDAO;

    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idHakmilik;
    private KodCawangan cawangan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private LaporanTandaSempadan laporanTandaSempadan;
    private String sebabPendudukan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/kemasukanSyaratPendudukan_Seksyen59.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/rundinganTawaranBayaranPampasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
    }

    public Resolution hakmilikDetails() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        if (idPermohonan != null && idHakmilik != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMH(hakmilikPermohonan.getId());
            if(laporanTandaSempadan != null && laporanTandaSempadan.getSebabPendudukan() != null){
                sebabPendudukan = laporanTandaSempadan.getSebabPendudukan();
            }else {
                sebabPendudukan = permohonan.getSebab();
            }
        }

        return new JSP("pengambilan/kemasukanSyaratPendudukan_Seksyen59.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        InfoAudit ia;
        if (idPermohonan != null && idHakmilik != null) {
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            laporanTandaSempadan = pengambilanService.getLaporanTandaSempadanByidMH(hakmilikPermohonan.getId());
            if(laporanTandaSempadan == null){
                laporanTandaSempadan = new LaporanTandaSempadan();
                laporanTandaSempadan.setPermohonan(permohonan);
                laporanTandaSempadan.setHakmilikPermohonan(hakmilikPermohonan);
                ia = new InfoAudit();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(peng);
                laporanTandaSempadan.setInfoAudit(ia);
            }else{
                ia = laporanTandaSempadan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                laporanTandaSempadan.setInfoAudit(ia);
            }
            laporanTandaSempadan.setSebabPendudukan(sebabPendudukan);
            pengambilanService.simpanLaporanTandaSempadan(laporanTandaSempadan);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/kemasukanSyaratPendudukan_Seksyen59.jsp").addParameter("tab", "true");
        }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(KemasukanSyaratPendudukanActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public LaporanTandaSempadan getLaporanTandaSempadan() {
        return laporanTandaSempadan;
    }

    public void setLaporanTandaSempadan(LaporanTandaSempadan laporanTandaSempadan) {
        this.laporanTandaSempadan = laporanTandaSempadan;
    }

    public String getSebabPendudukan() {
        return sebabPendudukan;
    }

    public void setSebabPendudukan(String sebabPendudukan) {
        this.sebabPendudukan = sebabPendudukan;
    }



}

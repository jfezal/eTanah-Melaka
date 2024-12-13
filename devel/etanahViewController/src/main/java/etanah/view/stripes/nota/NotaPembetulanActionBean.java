/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;


/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/nota/nota_daftar_betul")
public class NotaPembetulanActionBean extends AbleActionBean{
@Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    NotaService notaService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodUOMDAO koduomDAO;
    private static final Logger LOG = Logger.getLogger(NotaDaftarActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    private InfoAudit info;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private String tarikhSidang;
    private String tarikhRujukan;
    private Date trhSidang;
    private Date trhRujukan;

    @DefaultHandler
    public Resolution pengambilanbatal() {
        return new JSP("daftar/nota/nota_daftar_pengambilanbatal.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

    }

    public Resolution simpanMaklumat() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());
        tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        LOG.info("Tracing: Mula");
        if (IS_LOG_DEBUG) {
            LOG.debug("Id Permohonan: " + permohonan.getIdPermohonan() + " rekod ditemui.");
            LOG.info("Tracing: Dari JSP");
            LOG.debug("tarikhSidang Trace: " + tarikhSidang + " rekod ditemui.");
            LOG.debug("tarikhRUjukan Trace: " + tarikhRujukan + " rekod ditemui.");
        }
        LOG.info("Tracing: Tamat");

        if (idPermohonan != null) {
            try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                trhSidang = dateFormat.parse(tarikhSidang);
                trhRujukan = dateFormat.parse(tarikhRujukan);
            } catch (ParseException pe) {
                LOG.info("Parse Exception : " + pe);
            }

//            info = peng.getInfoAudit();
//            info.setDimasukOleh(peng);
//            info.setTarikhMasuk(new java.util.Date());
//            permohonanRujLuar.setInfoAudit(info);
//            permohonanRujLuar.setCawangan(peng.getKodCawangan());
            permohonanRujLuar.setTarikhRujukan(trhRujukan);
            permohonanRujLuar.setTarikhSidang(trhSidang);
            permohonanRujLuar.setPermohonan(permohonan);
            notaService.saveOrUpdate(permohonanRujLuar, peng, permohonan);

            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");

        }
        return new JSP("daftar/nota/nota_daftar_pengambilanbatal.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }
}

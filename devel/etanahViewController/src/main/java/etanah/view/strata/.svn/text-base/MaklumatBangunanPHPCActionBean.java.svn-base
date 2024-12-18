/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.StrataPtService;
import org.apache.commons.lang.StringUtils;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/badanPengurusanPHPC")
public class MaklumatBangunanPHPCActionBean extends BasicTabActionBean {

    @Inject
    PermohonanRujukanLuarDAO rujukanLuarDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strService;
    private PermohonanRujukanLuar rujukanLuar;
    private Permohonan permohonan;
    private String idPermohonan;
    private String Ulasan;
    private Date tarikhSidang;
    private int norujukan;
    private int kodAgensi;

    public String getUlasan() {
        return Ulasan;
    }

    public void setUlasan(String Ulasan) {
        this.Ulasan = Ulasan;
    }

    public int getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(int kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public int getNorujukan() {
        return norujukan;
    }

    public void setNorujukan(int norujukan) {
        this.norujukan = norujukan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public PermohonanRujukanLuar getRujukanLuar() {
        return rujukanLuar;
    }

    public void setRujukanLuar(PermohonanRujukanLuar rujukanLuar) {
        this.rujukanLuar = rujukanLuar;
    }

    public PermohonanRujukanLuarDAO getRujukanLuarDAO() {
        return rujukanLuarDAO;
    }

    public void setRujukanLuarDAO(PermohonanRujukanLuarDAO rujukanLuarDAO) {
        this.rujukanLuarDAO = rujukanLuarDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/pecahPetak/maklumatBangunanPHPC.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            List<PermohonanRujukanLuar> listRujukanluar = rujukanLuarDAO.findByEqualCriterias(tname, model, null);
            if (listRujukanluar.isEmpty()) {
                rujukanLuar = null;
            } else {
                rujukanLuar = listRujukanluar.get(0);

            }
        }

    }

    public Resolution simpanPerbadanan() {
        String error = null;
        String msg = null;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        KodAgensi kodagensi = new KodAgensi();
        KodRujukan kodrujukan = new KodRujukan();
        kodrujukan = kodRujukanDAO.findById("NF");
        kodagensi = (KodAgensi) kodAgensiDAO.findById(rujukanLuar.getAgensi().getKod());
        KodCawangan kodcawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        rujukanLuar.setPermohonan(permohonan);
        rujukanLuar.setAgensi(kodagensi);
        rujukanLuar.setKodRujukan(kodrujukan);
        InfoAudit ia = rujukanLuar.getInfoAudit();
        ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        rujukanLuar.setInfoAudit(ia);
        rujukanLuar.setCawangan(kodcawangan);
        strService.SimpanMohonRujukLuar(rujukanLuar);
        msg ="Maklumat berjaya disimpan";
        return new RedirectResolution(MaklumatBangunanPHPCActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public String validationPHPC() {
        String error = "Sila semak maklumat anda";
        if (!rujukanLuar.equals(null)) {
            error = "";
            if (StringUtils.isEmpty(rujukanLuar.getTarikhSidang().toString())) {
                error += "tarikh ";
            }

            if (StringUtils.isEmpty(rujukanLuar.getUlasan())) {
                error += "Ulasan ";
            }
            if (StringUtils.isEmpty(rujukanLuar.getNoRujukan().toString())) {
                error += "No Rujukan ";
            }
        }
        return error;
    }
}

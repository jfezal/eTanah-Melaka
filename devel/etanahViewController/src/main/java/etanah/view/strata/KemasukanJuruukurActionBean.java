/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.JUBLDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanJUBL;
import etanah.service.StrataPtService;
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
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/kemasukanJuruukur")
public class KemasukanJuruukurActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanJUBLDAO permohonanJUBLDAO;
    @Inject
    JUBLDAO jublDAO;
    @Inject
    StrataPtService strService;
    private PermohonanJUBL mohonJUBL;
    private Pemohon pemohon;
    private Permohonan permohonan;
    private String namaSyarikat;
    private String idJUBL;
    private String namaJUBL;
    private Date date;
    private int noRujukan;
    private String keterangan;
    private String error = "";


    public PermohonanJUBL getMohonJUBL() {
        return mohonJUBL;
    }

    public void setMohonJUBL(PermohonanJUBL mohonJUBL) {
        this.mohonJUBL = mohonJUBL;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdJUBL() {
        return idJUBL;
    }

    public void setIdJUBL(String idJUBL) {
        this.idJUBL = idJUBL;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaJUBL() {
        return namaJUBL;
    }

    public void setNamaJUBL(String namaJUBL) {
        this.namaJUBL = namaJUBL;
    }

    public String getNamaSyarikat() {
        return namaSyarikat;
    }

    public void setNamaSyarikat(String namaSyarikat) {
        this.namaSyarikat = namaSyarikat;
    }

    public int getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(int noRujukan) {
        this.noRujukan = noRujukan;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] tname = {"permohonan"};
        Object[] tvalue = {permohonan};
        List vec = pemohonDAO.findByEqualCriterias(tname, tvalue, null);
        List<PermohonanJUBL> listJUBL = permohonanJUBLDAO.findByEqualCriterias(tname, tvalue, null);
        if (listJUBL.isEmpty()) {
            mohonJUBL = null;
        } else {
            mohonJUBL = listJUBL.get(0);
        }

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
        return new JSP("strata/pecahPetak/kemasukanJuruukurberlesen.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMaklumat() {
        String msg = null, error = null;
        JUBL jubl = jublDAO.findById(mohonJUBL.getJurukur().getIdJubl());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        mohonJUBL.setJurukur(jubl);
        mohonJUBL.setInfoAudit(ia);
        mohonJUBL.setPermohonan(permohonan);
        mohonJUBL.setCawangan(strService.getkodCawangan(peng.getKodCawangan().getKod()));
        strService.simpanMohonJUBL(mohonJUBL);
        msg = "Maklumat berjaya disimpan";
        return new RedirectResolution(KemasukanJuruukurActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public boolean validate() {
        boolean t = false;
        if (!StringUtils.isEmpty(String.valueOf(mohonJUBL.getJurukur().getIdJubl()))
                && !StringUtils.isEmpty(mohonJUBL.getTarikhRujukan().toString())
                && !StringUtils.isEmpty(mohonJUBL.getNamaJUBL())
                && !StringUtils.isEmpty(mohonJUBL.getNoRujukan())
                && !StringUtils.isEmpty(mohonJUBL.getUlasan())) {
            t = true;
        }

        return t;
    }
}

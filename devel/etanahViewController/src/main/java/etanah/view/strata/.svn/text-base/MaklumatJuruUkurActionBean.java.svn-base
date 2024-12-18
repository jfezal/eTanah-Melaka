/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.JUBL;
import etanah.model.KodCawangan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanJUBL;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author User
 */
@UrlBinding("/strata/maklumat_juruUkur")
public class MaklumatJuruUkurActionBean extends BasicTabActionBean {

    @Inject
    StrataPtService ptService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    private PermohonanJUBL mohonJUBL = new PermohonanJUBL();
    Permohonan permohonan = new Permohonan();
    private JUBL juruUkur = new JUBL();
    KodCawangan kodCawangan = new KodCawangan();
    KodNegeri kodNegeri = new KodNegeri();
 @Inject
    etanah.Configuration conf;
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        mohonJUBL = ptService.findMohonJUBL(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (mohonJUBL != null) {
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            juruUkur = mohonJUBL.getJurukur();
            kodNegeri = juruUkur.getNegeri();
        } else {
            mohonJUBL = new PermohonanJUBL();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonJUBL.setInfoAudit(infoAudit);
            kodCawangan = peng.getKodCawangan();
            mohonJUBL.setCawangan(kodCawangan);

        }
    }

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("strata/Ruang_Udara/maklumat_juruUkur.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
String kodNegeri1 = conf.getProperty("document.path");
        kodNegeri = kodNegeriDAO.findById(kodNegeri1);
        juruUkur.setNama(mohonJUBL.getJurukur().getNama());
        juruUkur.setNegeri(mohonJUBL.getJurukur().getNegeri());
        juruUkur.setCawangan(mohonJUBL.getJurukur().getCawangan());
        juruUkur.setInfoAudit(mohonJUBL.getInfoAudit());
        juruUkur.setNoPengenalan(mohonJUBL.getJurukur().getNoPengenalan());
        juruUkur.setPoskod(mohonJUBL.getJurukur().getPoskod());
        juruUkur.setAlamat1(mohonJUBL.getJurukur().getAlamat1());
        juruUkur.setAlamat2(mohonJUBL.getJurukur().getAlamat2());
        juruUkur.setAlamat3(mohonJUBL.getJurukur().getAlamat3());
        juruUkur.setAlamat4(mohonJUBL.getJurukur().getAlamat4());
        juruUkur.setCawangan(mohonJUBL.getCawangan());
        juruUkur.setNegeri(kodNegeri);
        juruUkur = ptService.savaJuruUkur(juruUkur);
        mohonJUBL.setJurukur(juruUkur);
        mohonJUBL.setPermohonan(permohonan);
        ptService.simpanMohonJUBL(mohonJUBL);
//        ptService.saveMohonJUBL(mohonJUBL);
        return new JSP("strata/Ruang_Udara/maklumat_juruUkur.jsp").addParameter("tab", "true");

    }

    public JUBL getJuruUkur() {
        return juruUkur;
    }

    public void setJuruUkur(JUBL juruUkur) {
        this.juruUkur = juruUkur;
    }

    public PermohonanJUBL getMohonJUBL() {
        return mohonJUBL;
    }

    public void setMohonJUBL(PermohonanJUBL mohonJUBL) {
        this.mohonJUBL = mohonJUBL;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}

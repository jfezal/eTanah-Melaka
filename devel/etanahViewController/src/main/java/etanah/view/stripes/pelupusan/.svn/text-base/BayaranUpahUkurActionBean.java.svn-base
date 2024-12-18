/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodTransaksi;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author sitinorajar

 **/
@UrlBinding("/pelupusan/bayaran_Upah_Ukur")
public class BayaranUpahUkurActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PelupusanService pelupusanService;
    private static final Logger LOG = Logger.getLogger(BayaranUpahUkurActionBean.class);
    private PermohonanTuntutanKos permohonanTuntutKos;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pguna;
    private PermohonanTuntutanKos mohonTuntutKos;
    private String amaunTuntutan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/Maklumat_bayaran_upah_ukur.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanService.findById(idPermohonan);
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPU");
        if(mohonTuntutKos != null){
            amaunTuntutan = mohonTuntutKos.getAmaunTuntutan().toString();
            System.out.println("amaunTuntutan"+amaunTuntutan);
        }
        

    }

    public Resolution SimpanMaklumatBayaranUpahUkur() {
        LOG.info("SimpanMaklumatBayaranUpahUkur");
        InfoAudit infoAudit = new InfoAudit();
        if ((mohonTuntutKos != null)) {
            infoAudit = mohonTuntutKos.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonTuntutKos = new PermohonanTuntutanKos();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        BigDecimal amaun = new BigDecimal (amaunTuntutan);
        mohonTuntutKos.setPermohonan(permohonan);
        mohonTuntutKos.setInfoAudit(infoAudit);
        mohonTuntutKos.setCawangan(pguna.getKodCawangan());
        mohonTuntutKos.setItem(idPermohonan);
        mohonTuntutKos.setAmaunTuntutan(amaun);
        KodTransaksi kt = new KodTransaksi();
        kt.setKod("71711");
        mohonTuntutKos.setKodTransaksi(kt);
        KodTuntut tk = new KodTuntut();
        tk.setKod("DISPU");
        mohonTuntutKos.setKodTuntut(tk);
        pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
//        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/Maklumat_bayaran_upah_ukur.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutKos() {
        return permohonanTuntutKos;
    }

    public void setPermohonanTuntutKos(PermohonanTuntutanKos permohonanTuntutKos) {
        this.permohonanTuntutKos = permohonanTuntutKos;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(String amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package etanah.view.stripes.pelupusan;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
/**
 *
 * @author Rohan
 */
@UrlBinding("/pelupusan/maklumat_bayaran")
public class MaklumatBayaranActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(MaklumatBayaranActionBean.class);
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pguna;
    private Transaksi transaksi;
    @Inject
    PelupusanService pelupusanService;
    

   @DefaultHandler
        public Resolution showform()
        {
       LOG.info(":----------------------------Inside ShowForm ");
            return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_bayaran.jsp");
        }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("-------rehydrate----MaklumatBayaranActionBean-----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        transaksi = pelupusanService.findTransaksiByIdPermohonan(idPermohonan);
        LOG.info("--------------------transaksi----------------------"+transaksi.getIdTransaksi());
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }


}

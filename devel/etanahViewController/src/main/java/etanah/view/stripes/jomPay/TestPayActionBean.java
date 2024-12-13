/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.KodAgensiKutipanCawanganDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Akaun;
import etanah.model.InfoAudit;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.view.portal.ws.TerimaBayaranServices;
import etanah.view.portal.ws.TransaksiService;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/jomPay/test_bayar")
public class TestPayActionBean extends AbleActionBean {

    @Inject
    TransaksiService transaksiService;
    @Inject
    TerimaBayaranServices terimaBayaranServices;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodAgensiKutipanCawanganDAO kodAgensiKutipanCawanganDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    AkaunDAO akaunDAO;
    String noAkaun;
        BigDecimal amaun;

    @DefaultHandler
    public Resolution showForm() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        return new JSP("jomPay/test_bayar.jsp");
    }

    public Resolution bayar() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        String kodCaw = "01";
        String noResit= "123";
        String noRuj= "123";
        String jenisBayaran= "cukai";
        Date trhTransaksi = new Date();
        Akaun akaunKutip;
        KodAgensiKutipanCawangan agensiKutipan;
        InfoAudit ia = new InfoAudit();
        KodCaraBayaran kodCaraBayaran;
        String namaPembayar= "test";
        kodCaw = "01";
        Pengguna pengguna = penggunaDAO.findById("jompay_mt");
        akaunKutip = akaunDAO.findById("MTJOMPY");
        agensiKutipan = kodAgensiKutipanCawanganDAO.findById("100");
        kodCaraBayaran = kodCaraBayaranDAO.findById("E");

        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        terimaBayaranServices.terimaBayaranCukaiAgensi(noAkaun, kodCaw, amaun, noResit, noRuj, jenisBayaran, trhTransaksi, akaunKutip, agensiKutipan, ia, kodCaraBayaran, namaPembayar);
        return new JSP("jomPay/test_bayar.jsp");
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }
    
    
}

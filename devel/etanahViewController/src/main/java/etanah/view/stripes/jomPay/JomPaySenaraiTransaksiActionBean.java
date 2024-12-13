/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.JomPayFailMuatnaikDAO;
import etanah.model.Pengguna;
import etanah.model.jompay.JomPayFailMuatnaik;
import etanah.service.jompay.JomPayServices;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/jomPay/senarai_transaksi")
public class JomPaySenaraiTransaksiActionBean extends AbleActionBean {

    @Inject
    JomPayServices jomPayServices;
    @Inject
    JomPayFailMuatnaikDAO jomPayFailMuatnaikDAO;
    List<SenaraiTransaksiForm> senaraiTransaksi = new ArrayList<SenaraiTransaksiForm>();

    @DefaultHandler
    public Resolution showForm() {

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        senaraiTransaksi = jomPayServices.senaraiTransaksi(p.getKodCawangan().getKod());
        return new JSP("jomPay/transaksi.jsp");
    }

    public Resolution transaksi() {
        String idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        JomPayFailMuatnaik jomPayFailMuatnaik = jomPayFailMuatnaikDAO.findById(Long.valueOf(idUploadFile));

        senaraiTransaksi = jomPayServices.senaraiTransaksi(p.getKodCawangan().getKod(), jomPayFailMuatnaik.getIdJomPayUpload());
        return new JSP("jomPay/transaksi.jsp");
    }

    public List<SenaraiTransaksiForm> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<SenaraiTransaksiForm> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }
}

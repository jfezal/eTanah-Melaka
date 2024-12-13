/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.JomPayFailDetailsDAO;
import etanah.dao.JomPayFailMuatnaikDAO;
import etanah.model.Akaun;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.jompay.JomPayFailDetails;
import etanah.model.jompay.JomPayFailMuatnaik;
import etanah.service.jompay.JomPayServices;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/jomPay/details_transaction")
public class JomPayDetailsActionBean extends AbleActionBean {

    @Inject
    JomPayFailDetailsDAO jomPayFailDetailsDAO;
    @Inject
    JomPayServices jomPayServices;
    @Inject
    JomPayFailMuatnaikDAO jomPayFailMuatnaikDAO;
    List<JomPayFailDetails> listJompayDetails = new ArrayList<JomPayFailDetails>();
    String[] item;
    private String idUploadFile = "";
    private String edit = "true";

    @DefaultHandler
    public Resolution showForm() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        //listJompayDetails = jomPayFailDetailsDAO.findAll();
        listJompayDetails = jomPayServices.findListFailDetailByStatus(p.getIdPengguna());
        return new JSP("jomPay/jompay_details.jsp");
    }

    public Resolution kembali() {
        return new RedirectResolution(JomPayUploadActionBean.class, "showForm");
    }

    public Resolution papar() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        listJompayDetails = jomPayServices.findListFailDetailByIdUploadFile(Long.valueOf(idUploadFile));
        return new JSP("jomPay/jompay_details.jsp");
    }

    public Resolution paparListDelete() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        listJompayDetails = jomPayServices.findListFailDetailByIdUploadFile(Long.valueOf(idUploadFile));
        return new JSP("jomPay/jompay_details_delete.jsp");
    }

    public Resolution paparAllSucessFile() {
        edit = "false";
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        listJompayDetails = jomPayServices.findListFailDetailBySuccessFile(Long.valueOf(idUploadFile));
        return new JSP("jomPay/jompay_details.jsp");
    }

    public Resolution paparByFileId() {
        String filename = getContext().getRequest().getParameter("filename");
        listJompayDetails = jomPayServices.findListFailDetailByIdUploadFile(Long.valueOf(filename));
        return new JSP("jomPay/jompay_details.jsp");
    }

    public Resolution reconcile() {
        String[] item = getContext().getRequest().getParameterValues("item");
        InfoAudit ia = new InfoAudit();
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        for (int i = 0; i < item.length; i++) {
            JomPayFailDetails d = jomPayFailDetailsDAO.findById(Long.valueOf(item[i]));
            Akaun a = jomPayServices.findTranslateNoAkaun(d, d.getRujukanBayaran(), p);;
            if (a != null) {
                //need to check and translate if strata akaun
                String noAkaun = a.getNoAkaun();
                //based on biller code
                String vaNo = "" + d.getAkaunNo().replaceFirst("^0+(?!$)", "");
                BigDecimal amaun = d.getTransAmaun();
                //transaction no
                String noResit = d.getNoTrans();
                String noRuj = d.getRujukanDetails();
                Date trhTransaksi = d.getTarikhTrans();
                jomPayServices.terimaCukaiJomPay(noAkaun, vaNo, amaun, noResit, noRuj, trhTransaksi, d.getNamaPembayar(), p);

                //set flag after succes reconcile
                d.setStatus("Y");
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                d.setInfoAudit(ia);
                jomPayServices.simpanJomPayFailDetails(d);
            }

        }
//        showForm();
        return new RedirectResolution(JomPaySenaraiTransaksiActionBean.class, "transaksi").addParameter("idUploadFile", idUploadFile);
    }

    public Resolution deleteFail() {
        idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        String[] item = getContext().getRequest().getParameterValues("item");

        for (int i = 0; i < item.length; i++) {
            JomPayFailDetails d = jomPayFailDetailsDAO.findById(Long.valueOf(item[i]));

            if (d != null) {
                jomPayServices.deleteFailDetails(d);
            }
//            JomPayFailMuatnaik failMuatnaik = jomPayFailMuatnaikDAO.findById(Long.valueOf(idUploadFile));
//            if (failMuatnaik != null) {
//                jomPayServices.deleteFailUpload(failMuatnaik);
//            }
        }

        addSimpleMessage("Maklumat berjaya dihapuskan.");

        return showForm();
    }

    public List<JomPayFailDetails> getListJompayDetails() {
        return listJompayDetails;
    }

    public void setListJompayDetails(List<JomPayFailDetails> listJompayDetails) {
        this.listJompayDetails = listJompayDetails;
    }

    public String[] getItem() {
        return item;
    }

    public void setItem(String[] item) {
        this.item = item;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getIdUploadFile() {
        return idUploadFile;
    }

    public void setIdUploadFile(String idUploadFile) {
        this.idUploadFile = idUploadFile;
    }

}

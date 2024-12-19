/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.JomPayFailDetailsDAO;
import etanah.dao.JomPayFailMuatnaikDAO;
import etanah.dao.JomPayFailedRecordDAO;
import etanah.dao.JomPayFailedRecordsDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.jompay.JomPayFailDetails;
import etanah.model.jompay.JomPayFailMuatnaik;
import etanah.model.jompay.JomPayFailedRecord;
import etanah.model.jompay.JomPayFailedRecords;
import etanah.service.jompay.JomPayServices;
import etanah.util.DateUtil;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/jomPay/failed_transaction")
public class JomPayFailedActionBean extends AbleActionBean {

    @Inject
    JomPayServices jomPayServices;
    @Inject
    JomPayFailedRecordsDAO jomPayFailedRecordsDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    JomPayFailDetailsDAO jomPayFailDetailsDAO;
    @Inject
    JomPayFailMuatnaikDAO jomPayFailMuatnaikDAO;
    List<JomPayFailedRecords> listFailed;
    String idUploadFile;

    @DefaultHandler
    public Resolution showForm() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        String idUploadFile = getContext().getRequest().getParameter("idUploadFile");
        Pengguna p = ctx.getUser();
        //listJompayDetails = jomPayFailDetailsDAO.findAll();
//        JomPayFailDetails jomPayFailDetails = jomPayFailDetailsDAO.findById(Long.valueOf(idUploadFile));
        JomPayFailMuatnaik jomPayFailMuatnaik = jomPayFailMuatnaikDAO.findById(Long.valueOf(idUploadFile));
        listFailed = jomPayServices.findFailedjomPayFailDetails(jomPayFailMuatnaik.getIdJomPayUpload());
        return new JSP("jomPay/jompay_failed_record.jsp");
    }

    public Resolution popup() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        return new JSP("jomPay/jompay_failed_record_popup.jsp").addParameter("popup", true);
    }

    public Resolution reconcile() {
        String[] item = getContext().getRequest().getParameterValues("item");
        String[] idHakmilik = getContext().getRequest().getParameterValues("idHakmilik");
        String hakmilikx = "";
        InfoAudit ia = new InfoAudit();
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();

        for (int i = 0; i < item.length; i++) {
            JomPayFailedRecords f = jomPayFailedRecordsDAO.findById(Long.valueOf(item[i]));
            Hakmilik ha = hakmilikDAO.findById(idHakmilik[i].trim());
            if (ha != null) {
                Akaun a = ha.getAkaunCukai();
                if (a != null) {
                    //need to check and translate if strata akaun
                    String noAkaun = a.getNoAkaun();
                    //based on biller code
                    String vaNo = "" + f.getJomPayFailDetails().getAkaunNo().replaceFirst("^0+(?!$)", "");
                    BigDecimal amaun = f.getJomPayFailDetails().getTransAmaun();
                    //transaction no
                    String noResit = f.getJomPayFailDetails().getNoTrans();
                    String noRuj = f.getJomPayFailDetails().getRujukanDetails();
                    Date trhTransaksi = f.getJomPayFailDetails().getTarikhTrans();
                    jomPayServices.terimaCukaiJomPay(noAkaun, vaNo, amaun, noResit, noRuj, trhTransaksi, f.getJomPayFailDetails().getNamaPembayar(), p);
                    f.setHakmilik(ha);
                    f.setStatus("Y");
                    //set flag after succes reconcile
                    f.getJomPayFailDetails().setStatus("Y");
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    f.getJomPayFailDetails().setInfoAudit(ia);
                    jomPayServices.simpanJomPayFailDetails(f.getJomPayFailDetails());
                    jomPayServices.saveJomPayFailedRecord(f);
                }
            } else {
                hakmilikx = hakmilikx + " " + Long.valueOf(item[i]);
            }
        }
        if (StringUtils.isNotEmpty(hakmilikx)) {
            addSimpleError("Hakmilik tidak Wujud:" + hakmilikx);
        }
        return new RedirectResolution(JomPaySenaraiTransaksiActionBean.class, "transaksi").addParameter("idUploadFile", idUploadFile);
    }

    public List<JomPayFailedRecords> getListFailed() {
        return listFailed;
    }

    public void setListFailed(List<JomPayFailedRecords> listFailed) {
        this.listFailed = listFailed;
    }

    public String getIdUploadFile() {
        return idUploadFile;
    }

    public void setIdUploadFile(String idUploadFile) {
        this.idUploadFile = idUploadFile;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.uam.CPOnlineService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/utiliti/carian_persendirian_online")
public class CPOnlineUtilitiActionBean extends AbleActionBean {
    
    @Inject
    CPOnlineService cPOnlineService;
    List<CPTransaksi> listCPTransaksi;
    String noResitEbayar;
    String noTrans;
    String idHakmilik;
    
    String accountNo;
    String transId;
    String paymentDateTime;
    String amaun;
    String idPengguna;
    String resitNo;
    String namaPenyerah;
    String idmohon;
    
    @DefaultHandler
    public Resolution showForm() {
        listCPTransaksi = cPOnlineService.findFailedGenerate();
        
        return new JSP("uam/carian_online_utiliti.jsp");
    }
    
    public Resolution generateCarian()  {
        String error = cPOnlineService.janaCarian(accountNo, transId, paymentDateTime, amaun, idPengguna, resitNo, namaPenyerah);
        if(StringUtils.isEmpty(error)){
            addSimpleMessage("Maklumat berjaya disimpan");
        }else{
        addSimpleError(error);
        }
        return new JSP("uam/carian_online_utiliti.jsp");
        
    }
    
    public Resolution generateCarianPOrtalTrans()  {
        
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        String idPermohonan = getContext().getRequest().getParameter("idmohon");
        String idHM = getContext().getRequest().getParameter("idHakmilik");
        
        String error = cPOnlineService.janaPortalTrans(idDokumen, idPermohonan, idHM);
        if(StringUtils.isEmpty(error)){
            addSimpleMessage("Maklumat berjaya disimpan");
        }else{
        addSimpleError(error);
        }
        return new ForwardResolution("uam/carian_online_utiliti.jsp");
        
    }
    
    public List<CPTransaksi> getListCPTransaksi() {
        return listCPTransaksi;
    }
    
    public void setListCPTransaksi(List<CPTransaksi> listCPTransaksi) {
        this.listCPTransaksi = listCPTransaksi;
    }
    
    public String getNoResitEbayar() {
        return noResitEbayar;
    }
    
    public void setNoResitEbayar(String noResitEbayar) {
        this.noResitEbayar = noResitEbayar;
    }
    
    public String getNoTrans() {
        return noTrans;
    }
    
    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }
    
    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
    
    public CPOnlineService getcPOnlineService() {
        return cPOnlineService;
    }
    
    public void setcPOnlineService(CPOnlineService cPOnlineService) {
        this.cPOnlineService = cPOnlineService;
    }
    
    public String getAccountNo() {
        return accountNo;
    }
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
    public String getTransId() {
        return transId;
    }
    
    public void setTransId(String transId) {
        this.transId = transId;
    }
    
    public String getPaymentDateTime() {
        return paymentDateTime;
    }
    
    public void setPaymentDateTime(String paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }
    
    public String getAmaun() {
        return amaun;
    }
    
    public void setAmaun(String amaun) {
        this.amaun = amaun;
    }
    
    public String getIdPengguna() {
        return idPengguna;
    }
    
    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    public String getResitNo() {
        return resitNo;
    }
    
    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }
    
    public String getNamaPenyerah() {
        return namaPenyerah;
    }
    
    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public String getIdmohon() {
        return idmohon;
    }

    public void setIdmohon(String idmohon) {
        this.idmohon = idmohon;
    }
    
    
    
}

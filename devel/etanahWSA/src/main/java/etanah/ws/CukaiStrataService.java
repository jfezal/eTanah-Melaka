/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.ws.form.log.CheckAccountStrataByNoLotForm;
import etanah.ws.form.log.CheckAccountHakmilikStrataForm;
import etanah.ws.form.log.UpdateAccountUserForm;
import etanah.ws.form.log.CheckAccountStrataByNoHakmilikForm;
import com.etanah.portal.carian.UtilKodView;
import etanah.client.wslog.CukaiKelompokForm;
import etanah.ws.agent.AccountAgentService;
import etanah.ws.agent.AccountInfoStrataAgent;
import etanah.ws.carian.service.ListService;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.strata.AccountStrataService;
import etanah.ws.utility.StatusInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author mohd.faidzal
 */
@WebService()
public class CukaiStrataService {

    /**
     * This is a sample web service operation
     */
    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkAccountIdStrata")
    public List<AccountInfoStrataAgent> checkAccountHakmilikStrata(@WebParam(name = "idHakmilik") String idHakmilik) throws AccountInfoFaultException, ParseException, MalformedURLException {
             ObjectMapper obj = new ObjectMapper();
        CheckAccountHakmilikStrataForm form = new CheckAccountHakmilikStrataForm();
        String json = "";
        try {
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI_STRATA-checkAccountHakmilikStrata");
        return (new AccountStrataService()).getAccountDetails(idHakmilik);
    }

    @WebMethod(operationName = "checkAccountStrataByNoLot")
    public List<AccountInfoStrataAgent> checkAccountStrataByNoLot(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodLot") String kodLot,
            @WebParam(name = "noLot") Integer noLot,
            @WebParam(name = "noBangunan") String noBangunan,
            @WebParam(name = "noTingkat") String noTingkat,
            @WebParam(name = "noPetak") String noPetak) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CheckAccountStrataByNoLotForm form = new CheckAccountStrataByNoLotForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodLot(kodLot);
            form.setNoBangunan(noBangunan);
            form.setNoLot(noLot);
            form.setNoPetak(noPetak);
            form.setNoTingkat(noTingkat);
            form.setSeksyen(seksyen);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI_STRATA-checkAccountStrataByNoLot");
        return new AccountStrataService().checkAccountStrataByNoLot(daerah, bpm, seksyen, kodLot, noLot, noBangunan, noTingkat, noPetak);
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @param daerah
     * @param bpm
     * @param seksyen
     * @param kodJenishakmilik
     * @param noHakmilik
     * @param noBangunan
     * @param noTingkat
     * @param noPetak
     * @return
     */
    @WebMethod(operationName = "checkAccountStrataByNoHakmilik")
    public List<AccountInfoStrataAgent> checkAccountStrataByNoHakmilik(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodJenishakmilik") String kodJenishakmilik,
            @WebParam(name = "noHakmilik") String noHakmilik,
            @WebParam(name = "noBangunan") String noBangunan,
            @WebParam(name = "noTingkat") String noTingkat,
            @WebParam(name = "noPetak") String noPetak) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CheckAccountStrataByNoHakmilikForm form = new CheckAccountStrataByNoHakmilikForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodJenishakmilik(kodJenishakmilik);
            form.setNoBangunan(noBangunan);
            form.setNoHakmilik(noHakmilik);
            form.setNoPetak(noPetak);
            form.setNoTingkat(noTingkat);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI_STRATA-checkAccountStrataByNoHakmilik");
        return new AccountStrataService().checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik, noBangunan, noTingkat, noPetak);
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @param idHakmilik
     * @param amount
     * @param dimasuk
     * @param paymentType
     * @param resitNo
     * @throws etanah.ws.fault.AccountInfoFaultException
     */
    @WebMethod(operationName = "updateUserAccount")
    public StatusInfo updateAccountUser(@WebParam(name = "idHakmilik") String idHakmilik, @WebParam(name = "amount") BigDecimal amount, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
               ObjectMapper obj = new ObjectMapper();
        UpdateAccountUserForm form = new UpdateAccountUserForm();
        String json = "";
        try {
            form.setAmount(amount);
            form.setDimasuk(dimasuk);
            form.setFpxNo(fpxNo);
            form.setIdHakmilik(idHakmilik);
            form.setPaymentTime(paymentTime);
            form.setPaymentType(paymentType);
            form.setResitNo(resitNo);
            form.setTransactionNo(transactionNo);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI_STRATA-updateAccountUser");
        return (new AccountAgentService()).updatePortalStage(idHakmilik, amount, fpxNo,
                transactionNo, paymentTime, paymentType, resitNo, dimasuk);
    }
    
    @WebMethod(operationName = "updateCukaiStrataPukal")
    public StatusInfo updateCukaiStrataPukal(@WebParam(name = "listAccountForm") List<AkaunForm> listAccountForm, @WebParam(name = "amount") BigDecimal amount, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
               ObjectMapper obj = new ObjectMapper();
        CukaiKelompokForm form = new CukaiKelompokForm();
        String json = "";
        try {
            form.setAmount(amount);
            form.setDimasuk(dimasuk);
            form.setFpxNo(fpxNo);
            form.setListAccountForm(listAccountForm);
            form.setPaymentTime(paymentTime);
            form.setPaymentType(paymentType);
            form.setResitNo(resitNo);
            form.setTransactionNo(transactionNo);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI_STRATA-updateAccountUser");
        return (new AccountAgentService()).updatePortalStageKelompok(listAccountForm, amount, fpxNo,
                transactionNo, paymentTime, paymentType, resitNo, dimasuk);
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listDaerah")
    @SuppressWarnings("empty-statement")
    public List<UtilKodView> listDaerah() throws Exception {
        //TODO write your implementation code here:
//        if (authentication()) {
        return new ListService().listKodDaerah();
//        }
//        return null;

    }

    /**
     * Web service operation
     *
     * @param kodDaerah
     * @return
     */
    @WebMethod(operationName = "listBandarMukim")
    public List<UtilKodView> listBandarMukim(@WebParam(name = "kodDaerah") String kodDaerah) throws Exception {
        //TODO write your implementation code here:
//        if (authentication()) {
        return new ListService().listKodMukim(kodDaerah);
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @param kodBpm
     * @return
     */
    @WebMethod(operationName = "listSeksyen")
    public List<UtilKodView> listSeksyen(@WebParam(name = "kodBpm") String kodBpm) throws Exception {
        //TODO write your implementation code here:
//        if (authentication()) {
        return new ListService().listKodSeksyen(kodBpm);
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listKodLot")
    public List<UtilKodView> listKodLot() throws Exception {
        //TODO write your implementation code here:
//        if (authentication()) {
        return new ListService().listKodLot();
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listKodHakmilik")
    public List<UtilKodView> listKodHakmilik() throws Exception {
        //TODO write your implementation code here:
//        if (authentication()) {
        return new ListService().listKodHakmilik();
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Hello")
    public String Hello() {
        //TODO write your implementation code here:
        return null;
    }
}

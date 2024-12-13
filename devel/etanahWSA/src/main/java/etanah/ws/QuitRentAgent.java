/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.client.wslog.CukaiKelompokForm;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.etanah.portal.dao.PortalUsersDao;
import com.etanah.portal.pojo.PortalUsers;
import com.etanah.portal.trans.TransactionInfo;
import com.etanah.portal.trans.TransactionService;

import etanah.ws.agent.AccountAgentService;
import etanah.ws.agent.AccountAgentServiceTest;
import etanah.ws.agent.AccountHakmilikInfo;
import etanah.ws.agent.AccountInfoAgent;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.form.log.CheckAkaunForm;
import etanah.ws.form.log.UpdateUserAccountForm;
import etanah.ws.jtek.EmailInfo;
import etanah.ws.jtek.MohonService;
import etanah.ws.utility.StatusInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Izam
 */
@WebService()
public class QuitRentAgent {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkAccountKPHM")
    public List<AccountInfoAgent> checkAccountKPHM(@WebParam(name = "accountNo") String accountNo, @WebParam(name = "idHakmilik") String idHakmilik) throws AccountInfoFaultException, ParseException, MalformedURLException {
        ObjectMapper obj = new ObjectMapper();
        CheckAkaunForm form = new CheckAkaunForm();
        form.setAccountNo(accountNo);
        form.setIdHakmilik(idHakmilik);
        String json = "";
        try {
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-checkAccountKPHM");
        return (new AccountAgentService()).getAccountDetails(accountNo, idHakmilik);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateUserAccount")
    public StatusInfo updateUserAccount(@WebParam(name = "accountNo") String accountNo, @WebParam(name = "amount") BigDecimal amount, 
            @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, 
            @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, 
            @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
        ObjectMapper obj = new ObjectMapper();
        UpdateUserAccountForm form = new UpdateUserAccountForm();
        String json = "";
        try {
            form.setAccountNo(accountNo);
            form.setAmount(amount);
            form.setDimasuk(dimasuk);
            form.setFpxNo(fpxNo);
            form.setPaymentTime(paymentTime);
            form.setPaymentType(paymentType);
            form.setResitNo(resitNo);
            form.setTransactionNo(transactionNo);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-updateUserAccount");
        return (new AccountAgentService()).updatePortalStage(accountNo, amount, fpxNo,
                transactionNo, paymentTime, paymentType, resitNo, dimasuk);
    }

    @WebMethod(operationName = "updateCarianAccount")
    public StatusInfo updateCarianAccount(@WebParam(name = "idHakmilik") String idHakmilik, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
        return (new AccountAgentService()).updateCarianStage(idHakmilik, fpxNo,
                transactionNo, resitNo, dimasuk);
    }

    @WebMethod(operationName = "updateUserAccountTest")
    public StatusInfo updateUserAccountTest(@WebParam(name = "accountNo") String accountNo, @WebParam(name = "amount") BigDecimal amount, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
        return (new AccountAgentServiceTest()).updatePortalStage(accountNo, amount, fpxNo,
                transactionNo, paymentTime, paymentType, resitNo, dimasuk);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEmailList")
    public List<EmailInfo> getEmailList(@WebParam(name = "kodAgency") String kodAgency) throws AccountInfoFaultException {
        return (new MohonService()).getEmailList(kodAgency);
    }

    @WebMethod(operationName = "findAccount")
    public List<AccountHakmilikInfo> findAccount(
            @WebParam(name = "accountNo") String accountNo,
            @WebParam(name = "idHakmilik") String idHakmilik)
            throws AccountInfoFaultException {
        return (new AccountAgentService()).findHakmilikValid(accountNo,
                idHakmilik);
    }

    @WebMethod(operationName = "fetchPortalUsrsInfo")
    public List<PortalUsers> fetchPortalUsrsInfo() {
        return new PortalUsersDao().findById();
    }

    @WebMethod(operationName = "updateTransaction")
    public String updateTransaction(
            @WebParam(name = "accountNo") String accountNo,
            @WebParam(name = "amaun") long amaun,
            @WebParam(name = "userName") String userName,
            @WebParam(name = "paymentType") String paymentType) throws AccountInfoFaultException {
        return (new AccountAgentService().updateTransaction(accountNo, amaun, userName, paymentType));
    }

    @WebMethod(operationName = "listTransaction")
    public List<TransactionInfo> listTransaction(
            @WebParam(name = "userName") String userName,
            @WebParam(name = "dari") String dari,
            @WebParam(name = "hingga") String hingga) throws AccountInfoFaultException, ParseException {
        return (new TransactionService().listTransaction(userName, dari, hingga));
    }

    @WebMethod(operationName = "updateStsComplete")
    public String updateStatusTransaction(
            @WebParam(name = "fpxNo") String fpxNo,
            @WebParam(name = "transactionNo") String transactionNo,
            @WebParam(name = "idTrans") String idTrans) throws AccountInfoFaultException {
        return (new TransactionService().updateTransactionStatus(fpxNo, transactionNo, idTrans));
    }

    @WebMethod(operationName = "updateReconcileComplete")
    public String updateReconcileTransaction(
            @WebParam(name = "idTrans") String idTrans) throws AccountInfoFaultException {
        return (new TransactionService().findTransactionFail(idTrans));
    }

    @WebMethod(operationName = "updateCukaiPukal")
    public StatusInfo updateCukaiPukal(@WebParam(name = "listAccountForm") List<AkaunForm> listAccountForm, @WebParam(name = "amount") BigDecimal amount, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
        ObjectMapper obj = new ObjectMapper();
//        BigDecimal amount = new BigDecimal(BigInteger.ONE);
        String json = "";
        CukaiKelompokForm form = new CukaiKelompokForm();
        form.setListAccountForm(listAccountForm);
        form.setAmount(amount);
        form.setDimasuk(dimasuk);
        form.setFpxNo(fpxNo);
        form.setPaymentTime(paymentTime);
        form.setPaymentType(paymentType);
        form.setResitNo(resitNo);
        form.setTransactionNo(transactionNo);
        try {
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-updateCukaiKelompok");

        if (!form.isNull()) {
            StatusInfo info = new StatusInfo();
            return info;
        } else {
            return (new AccountAgentService()).updatePortalStageKelompok(listAccountForm, amount, fpxNo,
                    transactionNo, paymentTime, paymentType, resitNo, dimasuk);
        }

    }
    
    @WebMethod(operationName = "updateCukaiTanahPukal")
    public StatusInfo updateCukaiTanahPukal(@WebParam(name = "listAccountForm") List<AkaunForm> listAccountForm, @WebParam(name = "totalAmount") BigDecimal amount, @WebParam(name = "fpxNo") String fpxNo, @WebParam(name = "transactionNo") String transactionNo, @WebParam(name = "paymentTime") String paymentTime, @WebParam(name = "paymentType") String paymentType, @WebParam(name = "resitNo") String resitNo, @WebParam(name = "dimasuk") String dimasuk) throws AccountInfoFaultException, MalformedURLException {
        ObjectMapper obj = new ObjectMapper();
//        BigDecimal amount = new BigDecimal(BigInteger.ONE);
        String json = "";
        CukaiKelompokForm form = new CukaiKelompokForm();
        form.setListAccountForm(listAccountForm);
        form.setAmount(amount);
        form.setDimasuk(dimasuk);
        form.setFpxNo(fpxNo);
        form.setPaymentTime(paymentTime);
        form.setPaymentType(paymentType);
        form.setResitNo(resitNo);
        form.setTransactionNo(transactionNo);
        try {
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-updateCukaiKelompok");

        if (form.isNull()) {
            StatusInfo info = new StatusInfo();
            return info;
        } else {
            return (new AccountAgentService()).updatePortalStageKelompok(listAccountForm, amount, fpxNo,
                    transactionNo, paymentTime, paymentType, resitNo, dimasuk);
        }

    }
    
}

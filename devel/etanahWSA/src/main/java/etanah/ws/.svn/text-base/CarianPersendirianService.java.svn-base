/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.ws.form.log.UpdateBayaranCarianForm;
import etanah.ws.form.log.CarianHakmilikStrataByNoLotForm;
import etanah.ws.form.log.CarianHakmilikByNoLotForm;
import etanah.ws.form.log.CarianHakmilikForm;
import etanah.ws.form.log.CarianHakmilikByNoHakmilikForm;
import etanah.ws.form.log.CarianHakmilikStrataByNoHakmilikForm;
import com.etanah.portal.carian.CarianInfoView;
import com.etanah.portal.carian.DokumenCarianPersendirianInfoView;
import com.etanah.portal.carian.DokumenInfoView;
import com.etanah.portal.carian.UtilKodView;
import etanah.ws.carian.service.CarianHakmilikService;
import etanah.ws.carian.service.ListService;
import etanah.ws.carian.service.TerimaBayaranCarianService;
import etanah.ws.carian.service.TransaksiService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author faizah
 */
@WebService(serviceName = "CarianPersendirianService")
public class CarianPersendirianService {

    @Resource
    WebServiceContext wsctx;


    /**
     * Web service operation
     *
     * @param accountNo
     * @param idHakmilik
     * @return list akaun
     */
    @WebMethod(operationName = "carianHakmilik")
    public List<CarianInfoView> carianHakmilik(@WebParam(name = "accountNo") String accountNo,
            @WebParam(name = "idHakmilik") String idHakmilik) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CarianHakmilikForm form = new CarianHakmilikForm();
        String json = "";
        try {
            form.setAccountNo(accountNo);
            form.setIdHakmilik(idHakmilik);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-carianHakmilik");
            return new CarianHakmilikService().checkAccount(accountNo, idHakmilik);
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
     * @param kodLot
     * @param noLot
     * @return
     */
    @WebMethod(operationName = "carianHakmilikByNoLot")
    public List<CarianInfoView> carianHakmilikByNoLot(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodLot") String kodLot,
            @WebParam(name = "noLot") Integer noLot) throws Exception {
// ?       if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CarianHakmilikByNoLotForm form = new CarianHakmilikByNoLotForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodLot(kodLot);
            form.setNoLot(noLot);
            form.setSeksyen(seksyen);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CARIAN-carianHakmilikByNoLot");
            return new CarianHakmilikService().checkAccountByNoLot(daerah, bpm, seksyen, kodLot, noLot);
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
     * @param kodLot
     * @param noLot
     * @param noBangunan
     * @param noTingkat
     * @return
     */
    @WebMethod(operationName = "carianHakmilikStrataByNoLot")
    public List<CarianInfoView> carianHakmilikStrataByNoLot(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodLot") String kodLot,
            @WebParam(name = "noLot") Integer noLot,
            @WebParam(name = "noBangunan") String noBangunan,
            @WebParam(name = "noTingkat") String noTingkat,
            @WebParam(name = "noPetak") String noPetak) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CarianHakmilikStrataByNoLotForm form = new CarianHakmilikStrataByNoLotForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodLot(kodLot);
            form.setNoBangunan(noBangunan);
            form.setNoLot(noLot);
            form.setNoPetak(noPetak);
            form.setNoTingkat(noTingkat);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CARIAN-carianHakmilikStrataByNoLot");
            return new CarianHakmilikService().checkAccountStrataByNoLot(daerah, bpm, seksyen, kodLot, noLot, noBangunan, noTingkat, noPetak);
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
     * @return
     */
    @WebMethod(operationName = "carianHakmilikByNoHakmilik")
    public List<CarianInfoView> carianHakmilikByNoHakmilik(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodJenishakmilik") String kodJenishakmilik,
            @WebParam(name = "noHakmilik") String noHakmilik) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CarianHakmilikByNoHakmilikForm form = new CarianHakmilikByNoHakmilikForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodJenishakmilik(kodJenishakmilik);
            form.setNoHakmilik(noHakmilik);
            form.setSeksyen(seksyen);
            
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-carianHakmilikByNoHakmilik");
            return new CarianHakmilikService().checkAccountByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik);
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
    @WebMethod(operationName = "carianHakmilikStrataByNoHakmilik")
    public List<CarianInfoView> carianHakmilikStrataByNoHakmilik(@WebParam(name = "daerah") String daerah,
            @WebParam(name = "bpm") String bpm,
            @WebParam(name = "seksyen") String seksyen,
            @WebParam(name = "kodJenishakmilik") String kodJenishakmilik,
            @WebParam(name = "noHakmilik") String noHakmilik,
            @WebParam(name = "noBangunan") String noBangunan,
            @WebParam(name = "noTingkat") String noTingkat,
            @WebParam(name = "noPetak") String noPetak) throws Exception {
//        if (authentication()) {
       ObjectMapper obj = new ObjectMapper();
        CarianHakmilikStrataByNoHakmilikForm form = new CarianHakmilikStrataByNoHakmilikForm();
        String json = "";
        try {
            form.setBpm(bpm);
            form.setDaerah(daerah);
            form.setKodJenishakmilik(kodJenishakmilik);
            form.setNoBangunan(noBangunan);
            form.setNoHakmilik(noHakmilik);
            form.setNoHakmilik(noHakmilik);
            form.setNoPetak(noPetak);
            form.setNoTingkat(noTingkat);
            
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LogService().insertLog(json, "etanahwsa", "CUKAI-carianHakmilikStrataByNoHakmilik");
            return new CarianHakmilikService().checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik, noBangunan, noTingkat, noPetak);
//        } else {
//            return null;
//        }
    }

    /**
     * Web service operation
     *
     * @param idPengguna
     * @return
     */
    @WebMethod(operationName = "senaraiDokumen")
    public List<DokumenCarianPersendirianInfoView> senaraiDokumen(@WebParam(name = "idPengguna") String idPengguna) throws Exception {
//        if (authentication()) {
            return new TransaksiService().senaraiDokumen(idPengguna, null, null);
//        } else {
//            return null;
//        }
    }

        @WebMethod(operationName = "senaraiDokumenIdHakmilik")
    public List<DokumenCarianPersendirianInfoView> senaraiDokumenIdHakmilik(@WebParam(name = "idPengguna") String idPengguna,@WebParam(name = "idHakmilik") String idHakmilik) throws Exception {
//        if (authentication()) {
            return new TransaksiService().senaraiDokumenIdHakmilik(idPengguna, idHakmilik);
//        } else {
//            return null;
//        }
    }
    /**
     * Web service operation
     *
     * @param idPortalTrans
     * @return
     */
    @WebMethod(operationName = "muatTurunDokumen")
    public DokumenInfoView muatTurunDokumen(@WebParam(name = "idPortalTrans") long idPortalTrans) throws Exception {
//        if (authentication()) {
            return new TransaksiService().muatturundokumen(idPortalTrans);
//        } else {
//            return null;
//        }
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
     *
     * @param accountNo
     * @param transId
     * @param paymentDateTime
     * @param amaun
     * @param idPengguna
     * @param resitNo
     * @param namaPenyerah
     * @return
     */
    @WebMethod(operationName = "updateBayaranCarian")
    public String updateBayaranCarian(@WebParam(name = "accountNo") String accountNo,
            @WebParam(name = "transId") String transId,
            @WebParam(name = "paymentDateTime") String paymentDateTime,
            @WebParam(name = "amaun") BigDecimal amaun,
            @WebParam(name = "idPengguna") String idPengguna,
            @WebParam(name = "resitNo") String resitNo,
            @WebParam(name = "namaPenyerah") String namaPenyerah) throws Exception {
//        if (authentication()) {
                Logger.getLogger(CarianPersendirianService.class.getName()).log(Level.SEVERE,"updateBayaranCarian()"+accountNo);
 ObjectMapper obj = new ObjectMapper();
        UpdateBayaranCarianForm form = new UpdateBayaranCarianForm();
        String json = "";
        try {
            form.setAccountNo(accountNo);
            form.setAmaun(amaun);
            form.setIdPengguna(idPengguna);
            form.setNamaPenyerah(namaPenyerah);
            form.setPaymentDateTime(paymentDateTime);
            form.setResitNo(resitNo);
            form.setTransId(transId);
            json = obj.writeValueAsString(form);
        } catch (IOException ex) {
            Logger.getLogger(QuitRentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }        new LogService().insertLog(json, "etanahwsa", "CARIAN-updateBayaranCarian");

            return new TerimaBayaranCarianService().updateCarianAccount(accountNo, transId, paymentDateTime, amaun, idPengguna, resitNo, namaPenyerah);
//        }
//        return null;

    }
//@WebMethod(operationName = "updateBayaranCarian")
//        public String updateBayaranCarian(@WebParam(name = "accountNo") String accountNo,
//            @WebParam(name = "transId") String transId,
//            @WebParam(name = "paymentDateTime") String paymentDateTime,
//            @WebParam(name = "amaun") BigDecimal amaun,
//            @WebParam(name = "idPengguna") String idPengguna,
//            @WebParam(name = "resitNo") String resitNo,
//            @WebParam(name = "namaPenyerah") String namaPenyerah) throws Exception {
//        MessageContext mctx = wsctx.getMessageContext();
//
//        //get detail from request headers
//        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
//        List userList = (List) http_headers.get("Username");
//        List passList = (List) http_headers.get("Password");
//
//        String username = "";
//        String password = "";
//
//        if (userList != null) {
//            //get username
//            username = userList.get(0).toString();
//        }
//
//        if (passList != null) {
//            //get password
//            password = passList.get(0).toString();
//        }
//        if (username.equals("mkyong") && password.equals("password")) {
//            return "";
//        } else {
//            throw new Exception("Invalid Username or Password");
//        }
//
//    }

}

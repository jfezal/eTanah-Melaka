/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.client.etapp.ArrayOfHakmilikPermohonanMyEtaPP;
import etanah.client.etapp.ArrayOfLampiranMyEtaPP;
import etanah.client.etapp.EtappPermohonan;
import etanah.client.etapp.EtappPermohonanPortType;
import etanah.client.etapp.LampiranMyEtaPP;
import etanah.client.etapp.MaklumatHakmilikMyEtaPP;
import etanah.client.etapp.MaklumatPermohonanSek4MyEtaPP;
import etanah.client.etapp.MaklumatPermohonanSek8MyEtaPP;
import etanah.ws.etapp.service.EtappPengambilanService;
import etanah.ws.fault.AccountInfoFaultException;
import etanah.ws.myetapp.form.HakmilikForm;
import etanah.ws.myetapp.form.LampiranForm;
import etanah.ws.myetapp.form.MaklumatHakmilikForm;
import etanah.ws.myetapp.form.MaklumatPermohonanSek4Form;
import etanah.ws.myetapp.form.MaklumatPermohonanSek8Form;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mohd.faidzal
 */
@WebService()
public class MyEtappPengambilan {

    /**
     * Web service operation
     *
     * @param noResit
     * @param idHakmilik
     * @return
     */
    @WebMethod(operationName = "hakmilikDetailByCarianResit")
    public HakmilikForm hakmilikDetailByCarianResit(@WebParam(name = "noResit") String noResit, @WebParam(name = "idHakmilik") String idHakmilik) throws AccountInfoFaultException, MalformedURLException {
        HakmilikForm hakmilikForm = new HakmilikForm();

        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        MaklumatHakmilikMyEtaPP ha = port.maklumatHakmilik(noResit, noResit);
        hakmilikForm = etappPengambilanService.setHakmilik(ha);
        return hakmilikForm;
    }

    @WebMethod(operationName = "daftarPermohonanBorangAMMk")
    public String daftarPermohonanBorangAMMk(@WebParam(name = "maklumatPermohonan") MaklumatPermohonanSek4Form maklumatPermohonan,
            @WebParam(name = "maklumatHakmilik") MaklumatHakmilikForm[] maklumatHakmilikForm,
            @WebParam(name = "drafMMk") LampiranForm mmkPaper,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String idPermohonan = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        MaklumatPermohonanSek4MyEtaPP in0 = etappPengambilanService.setMaklumatPermohonanSek4MyEtaPP(maklumatPermohonan);
        ArrayOfHakmilikPermohonanMyEtaPP in1 = etappPengambilanService.setArrayOfHakmilikPermohonanMyEtaPP(maklumatHakmilikForm);
        LampiranMyEtaPP in2 = etappPengambilanService.setLampiranMyEtaPP(mmkPaper);
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        idPermohonan = port.daftarPermohonanMyEtaPPBorangAMMk(in0, in1, in2, in3);
        return idPermohonan;
    }

    @WebMethod(operationName = "maklumatWartaborangB")
    public String maklumatWartaBorangB(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "tarikhWarta") String tarikhWarta,
            @WebParam(name = "noWarta") String noWarta,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.maklumatWartaBorangBMyEtaPP(idPermohonan, tarikhWarta, noWarta, in3);
        return status;
    }

    @WebMethod(operationName = "daftarPermohonanSek8")
    public String daftarPermohonanSek8(@WebParam(name = "maklumatPermohonan") MaklumatPermohonanSek8Form maklumatPermohonan,
            @WebParam(name = "maklumatHakmilik") MaklumatHakmilikForm[] maklumatHakmilikForm,
            @WebParam(name = "digitalcharting") LampiranForm digitalcharting,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String idPermohonan = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        MaklumatPermohonanSek8MyEtaPP in0 = etappPengambilanService.setMaklumatPermohonanSek8MyEtaPP(maklumatPermohonan);
        ArrayOfHakmilikPermohonanMyEtaPP in1 = etappPengambilanService.setArrayOfHakmilikPermohonanMyEtaPP(maklumatHakmilikForm);
        LampiranMyEtaPP in2 = etappPengambilanService.setLampiranMyEtaPP(digitalcharting);
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        idPermohonan = port.daftarPermohonanSek8MyEtaPP(in0, in1, in2, in3);
        return idPermohonan;
    }

    @WebMethod(operationName = "borangCdanMMK")
    public String borangCdanMMK(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "drafMMk") LampiranForm mmkPaper,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        LampiranMyEtaPP in1 = etappPengambilanService.setLampiranMyEtaPP(mmkPaper);
        ArrayOfLampiranMyEtaPP in2 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.borangCdanMMKMyEtaPP(idPermohonan, in1, in2);
        return status;
    }

    @WebMethod(operationName = "endorsBorangDMaklumatWarta")
    public String endorsBorangDMaklumatWarta(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "tarikhWarta") String tarikhWarta,
            @WebParam(name = "noWarta") String noWarta,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.endorsBorangDMaklumatWartaMyEtaPP(idPermohonan, tarikhWarta, noWarta, in3);
        return status;
    }

    @WebMethod(operationName = "endorsBorangK")
    public String endorsBorangK(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "tarikhWarta") String tarikhWarta,
            @WebParam(name = "noWarta") String noWarta,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.endorsBorangKMyEtaPP(idPermohonan, tarikhWarta, noWarta, in3);
        return status;
    }

    @WebMethod(operationName = "sijilPembebasanUkur")
    public String sijilPembebasanUkur(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "tarikh") String tarikh,
            @WebParam(name = "noPU") String noPU,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.sijilPembebasanUkurMyEtaPP(idPermohonan, tarikh, noPU, in3);
        return status;
    }

    @WebMethod(operationName = "maklumanPenghantaranPU")
    public String maklumanPenghantaranPU(@WebParam(name = "idPermohonan") String idPermohonan,
            @WebParam(name = "tarikhHantar") String tarikhHantar,
            @WebParam(name = "noRujukan") String noRujukan,
            @WebParam(name = "attachment") LampiranForm[] attchment) throws AccountInfoFaultException, MalformedURLException {
        String status = new String();
        URLClient u = new URLClient();
        URL url = new URL(u.etapp);
        EtappPermohonan service = new EtappPermohonan(url);
        EtappPermohonanPortType port = service.getEtappPermohonanHttpPort();
        EtappPengambilanService etappPengambilanService = new EtappPengambilanService();
        ArrayOfLampiranMyEtaPP in3 = etappPengambilanService.setArrayOfLampiranMyEtaPP(attchment);
        status = port.maklumanPenghantaranPUMyEtaPP(idPermohonan, tarikhHantar, noRujukan, in3);
        return status;
    }
}

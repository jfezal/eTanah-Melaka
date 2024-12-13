/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws;

import etanah.etapp.ws.client.Permohonan;
import etanah.etapp.ws.form.PermohonanForm;
import etanah.etapp.ws.form.StatusForm;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author mohd.faidzal
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EtappClientService {

    @WebMethod
    StatusForm hantarBorangA(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarBorangB(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarMMK(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarBorangD(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarBorangK(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarSijilUkur(String idPermohonan, String form);

    @WebMethod
    StatusForm hantarHakmilikSambungan(String idPermohonan, String form);
}

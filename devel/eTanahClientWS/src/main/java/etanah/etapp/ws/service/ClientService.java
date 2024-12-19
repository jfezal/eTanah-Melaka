/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws.service;

import etanah.ws.URLForm;
import etanah.etapp.ws.client.MyeTappService;
import etanah.etapp.ws.client.Permohonan;
import etanah.etapp.ws.client.TanahApplicationResponse;
import etanah.etapp.ws.client.WebServices;
import etanah.etapp.ws.form.PermohonanForm;
import etanah.etapp.ws.form.StatusForm;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author mohd.faidzal
 */
public class ClientService {

    String username = "etpapi01";
    String password = "etpapi01";

    public StatusForm hantarBorangA(String idPermohonan, PermohonanForm form) throws MalformedURLException {
        StatusForm status = new StatusForm();
        MyeTappService service = service();
        WebServices port = service.getUpdateApplication();
        System.out.println("idPermohonan" + idPermohonan);
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        TanahApplicationResponse res = port.eTanahPPTUpdateFormA(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        System.out.println("Status Detail" + res.getDetail());

        return status;
    }

    public StatusForm hantarBorangB(String idPermohonan, PermohonanForm form) throws MalformedURLException {
        StatusForm status = new StatusForm();
        MyeTappService service =service();
        WebServices port = service.getUpdateApplication();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        TanahApplicationResponse res = port.eTanahPPTUpdateFormB(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    public StatusForm hantarMMK(String idPermohonan, PermohonanForm form) throws MalformedURLException {
        StatusForm status = new StatusForm();
        MyeTappService service = service();
        WebServices port = service.getUpdateApplication();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        TanahApplicationResponse res = port.eTanahPPTUpdateMMK(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    public StatusForm hantarBorangD(String idPermohonan, PermohonanForm form) throws MalformedURLException {
        StatusForm status = new StatusForm();
        MyeTappService service = service();
        WebServices port = service.getUpdateApplication();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        TanahApplicationResponse res = port.eTanahPPTUpdateFormD(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    public StatusForm hantarBorangK(String idPermohonan, PermohonanForm form) {
        StatusForm status = new StatusForm();
        MyeTappService service = new MyeTappService();
        WebServices port = service.getUpdateApplication();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        TanahApplicationResponse res = port.eTanahPPTUpdateFormK(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    public StatusForm hantarSijilUkur(String idPermohonan, PermohonanForm form) throws MalformedURLException {
        StatusForm status = new StatusForm();
        MyeTappService service = service();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        WebServices port = service.getUpdateApplication();
        TanahApplicationResponse res = port.eTanahPPTUpdateCert(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    public StatusForm hantarHakmilikSambungan(String idPermohonan, PermohonanForm form) throws MalformedURLException {

        StatusForm status = new StatusForm();
        MyeTappService service = service();
        PermohonanService permohonanService = new PermohonanService();
        Permohonan p = permohonanService.setForm(form);
        WebServices port = service.getUpdateApplication();
        TanahApplicationResponse res = port.eTanahPPTUpdatePU(username, password, idPermohonan, p);
        status.setCode(res.getCode());
        status.setDescription(res.getDescription());
        status.setDetail(res.getDetail());
        return status;
    }

    MyeTappService service() throws MalformedURLException {
        URLForm u = new URLForm();
        URL url = new URL(u.urlEtappServer);
        MyeTappService service = new MyeTappService(url);
        return service;
    }

}

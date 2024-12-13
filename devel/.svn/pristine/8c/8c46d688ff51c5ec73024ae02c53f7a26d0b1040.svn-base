/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws;

import com.google.gson.Gson;
import etanah.etapp.ws.client.Permohonan;
import etanah.etapp.ws.form.PermohonanForm;
import etanah.etapp.ws.form.StatusForm;
import etanah.etapp.ws.service.ClientService;
import javax.jws.WebService;

/**
 *
 * @author mohd.faidzal
 */
@WebService(endpointInterface = "etanah.etapp.ws.EtappClientService")
public class EtappClientServiceImpl implements EtappClientService {

    @Override
    public StatusForm hantarBorangA(String idPermohonan, String jsonString) {
        System.out.println("hantarBorangA----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);
        status = service.hantarBorangA(idPermohonan, form);
        System.out.println("hantarBorangA result info" + status.getCode());
        System.out.println("hantarBorangA result info" + status.getDescription());
        System.out.println("hantarBorangA result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarBorangB(String idPermohonan, String jsonString) {
        System.out.println("hantarBorangB----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
                System.out.print(jsonString);

        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarBorangB(idPermohonan, form);
        System.out.println("hantarBorangB result info" + status.getCode());
        System.out.println("hantarBorangB result info" + status.getDescription());
        System.out.println("hantarBorangB result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarMMK(String idPermohonan, String jsonString) {
        System.out.println("hantarMMK----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarMMK(idPermohonan, form);
        System.out.println("hantarMMK result info" + status.getCode());
        System.out.println("hantarMMK result info" + status.getDescription());
        System.out.println("hantarMMK result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarBorangD(String idPermohonan, String jsonString) {
        System.out.println("hantarBorangD----------------------------------");

        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarBorangD(idPermohonan, form);
        System.out.println("hantarBorangD result info" + status.getCode());
        System.out.println("hantarBorangD result info" + status.getDescription());
        System.out.println("hantarBorangD result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarBorangK(String idPermohonan, String jsonString) {
        System.out.println("hantarBorangK----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarBorangK(idPermohonan, form);
        System.out.println("hantarBorangK result info" + status.getCode());
        System.out.println("hantarBorangK result info" + status.getDescription());
        System.out.println("hantarBorangK result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarSijilUkur(String idPermohonan, String jsonString) {
        System.out.println("hantarSijilUkur----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarSijilUkur(idPermohonan, form);
        System.out.println("hantarSijilUkur result info" + status.getCode());
        System.out.println("hantarSijilUkur result info" + status.getDescription());
        System.out.println("hantarSijilUkur result info" + status.getDetail());
        return status;
    }

    @Override
    public StatusForm hantarHakmilikSambungan(String idPermohonan, String jsonString) {
        System.out.println("hantarHakmilikSambungan----------------------------------");
        ClientService service = new ClientService();
        StatusForm status = new StatusForm();
        Gson g = new Gson();
        System.out.print(jsonString);
        PermohonanForm form = g.fromJson(jsonString, PermohonanForm.class);

        status = service.hantarHakmilikSambungan(idPermohonan, form);
        System.out.println("hantarHakmilikSambungan result info" + status.getCode());
        System.out.println("hantarHakmilikSambungan result info" + status.getDescription());
        System.out.println("hantarHakmilikSambungan result info" + status.getDetail());
        return status;    }

}

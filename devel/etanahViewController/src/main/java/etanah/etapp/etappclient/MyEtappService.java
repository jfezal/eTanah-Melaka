/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.etappclient;

import com.google.inject.Inject;
import etanah.etapp.etappclient.form.DokumenForm;
import etanah.etapp.etappclient.form.HakmilikForm;
import etanah.etapp.etappclient.form.PermohonanForm;
import etanah.etapp.etappclient.service.Sek4Service;
import etanah.etapp.etappclient.service.Sek8Service;
import etanah.integration.myetapp.StatusForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author faidzal
 */
public class MyEtappService {

    @Inject
    Sek8Service sek8Service;
    @Inject
    Sek4Service sek4Service;
    String username = "etpapi01";
    String password = "etpapi01";

    public StatusForm hantarBorangA(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek4Service.borangA(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarBorangB(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek4Service.borangB(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarBorangC(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek8Service.borangC(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarBorangD(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek8Service.borangD(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarBorangK(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek8Service.borangK(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarsijilPembebasanUkur(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek8Service.sijilPembebasanUkur(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public StatusForm hantarmaklumanPU(String idPermohonan, PermohonanForm permohonan, List<HakmilikForm> listHakmilikForm, List<DokumenForm> listDokumenForm) throws IOException {
        return sek8Service.maklumanPU(idPermohonan, permohonan, listHakmilikForm, listDokumenForm);

    }

    public static void main(String[] args) throws IOException {
        MyEtappService a = new MyEtappService();
//        Permohonan permohonan = new Permohonan();
        PermohonanForm f = new PermohonanForm();
        List<DokumenForm> listDokumenForm = new ArrayList<DokumenForm>();
        List<HakmilikForm> listHakmilikForm = new ArrayList<HakmilikForm>();
        a.hantarBorangA("0402ACQ2020000008", f, listHakmilikForm, listDokumenForm);
        // TODO code application logic here
    }
}

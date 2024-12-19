/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanEtapp;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.ambil.MyEtappService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/etapp/sijil_ukur")
public class SijilUkurActionBean extends BasicTabActionBean {
  @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;
    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    MyEtappService myEtappService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    String idPermohonan;
    Permohonan permohonan;
    String noPU;
    String tarikhPU;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

     @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        noPU = mohonEtapp.getNoPu();
        tarikhPU = sdf.format(mohonEtapp.getTrhPu());
        String kodDokumen[] = new String[2];
        kodDokumen[0] = "SPU";
        kodDokumen[1] = "SIPU";
        for (int i = 0; i < kodDokumen.length; i++) {
            String kodDokumen1[] = new String[1];
            kodDokumen1[0] = kodDokumen[i];
            senaraiKandungan = myEtappService.senaraiDokumen(idPermohonan, kodDokumen1);
            if (senaraiKandungan.isEmpty()) {
                KodDokumen kod = kodDokumenDAO.findById(kodDokumen1[0]);
                myEtappService.createDokumen(kod, mohon);

            }

        }
        senaraiKandungan = myEtappService.senaraiDokumen(idPermohonan, kodDokumen);

        return new JSP("etapp/sijil_ukur.jsp").addParameter("tab", "true");
    }
         public Resolution simpan() {

        return new JSP("etapp/hantar_borang_d.jsp");
    }

    public Resolution selesai() throws IOException {
        RefPeringkat ref = new RefPeringkat();
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        myeTappIntegrationFlowService.completeTask(ref.SEK8_HANTAR_SPU, permohonan, pengguna, permohonan.getCawangan().getDaerah());
        return new JSP("etapp/hantar_borang_d.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoPU() {
        return noPU;
    }

    public void setNoPU(String noPU) {
        this.noPU = noPU;
    }

    public String getTarikhPU() {
        return tarikhPU;
    }

    public void setTarikhPU(String tarikhPU) {
        this.tarikhPU = tarikhPU;
    }

}

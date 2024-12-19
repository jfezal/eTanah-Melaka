/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanSurat;
import etanah.model.AmbilPampasan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPB;
import etanah.ref.pengambilan.sek4aduan.RefPeringkat;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/rekod_bayaran_sedia_surat")
public class RekodBayaranSediaSuratActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService;
    String idPermohonan;
    Date tarikhTerimaBayaran;
    String noRujukan;
    BigDecimal jumlahBayaran;
    String carabayar;
    Long idperpb;
    List<BorangPerPB> listBorangPerPB;
    List<AmbilPampasan> listAmbilPampasan;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listBorangPerPB = aduanService.findByidMohonNotAmbilPampasan(idPermohonan);
        listAmbilPampasan = aduanService.findAmbilPampasanByIdPermohonan(idPermohonan);
        return new JSP("pengambilan/aduan_kerosakan/rekodBayaranSediaSurat.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        BorangPerPB pb = borangPerPBDAO.findById(idperpb);
        AmbilPampasan ambil = new AmbilPampasan();
        ambil.setBorangPerPb(pb);
        ambil.setJumTerimaPampasan(jumlahBayaran);
        KodCaraBayaran kodCaraBayaran = kodCaraBayaranDAO.findById(carabayar);
        ambil.setKodCaraBayaran(kodCaraBayaran);
        ambil.setNoRujukan(noRujukan);
        ambil.setTarikhDok(tarikhTerimaBayaran);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        ambil.setInfoAudit(ia);
        aduanService.saveAmbilPampasan(ambil);
        return new RedirectResolution(RekodBayaranSediaSuratActionBean.class);

    }

    public Resolution hantar() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        RefPeringkat ref = new RefPeringkat();
        sek4AduanIntegrationFlowService.completeTask(ref.SEDIA_SURAT_AMBIL_BAYARAN, mohon, pengguna);

        return new RedirectResolution("/senaraiTugasan");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Date getTarikhTerimaBayaran() {
        return tarikhTerimaBayaran;
    }

    public void setTarikhTerimaBayaran(Date tarikhTerimaBayaran) {
        this.tarikhTerimaBayaran = tarikhTerimaBayaran;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    public String getCarabayar() {
        return carabayar;
    }

    public void setCarabayar(String carabayar) {
        this.carabayar = carabayar;
    }

    public Long getIdperpb() {
        return idperpb;
    }

    public void setIdperpb(Long idperpb) {
        this.idperpb = idperpb;
    }

    public List<BorangPerPB> getListBorangPerPB() {
        return listBorangPerPB;
    }

    public void setListBorangPerPB(List<BorangPerPB> listBorangPerPB) {
        this.listBorangPerPB = listBorangPerPB;
    }

    public List<AmbilPampasan> getListAmbilPampasan() {
        return listAmbilPampasan;
    }

    public void setListAmbilPampasan(List<AmbilPampasan> listAmbilPampasan) {
        this.listAmbilPampasan = listAmbilPampasan;
    }

}

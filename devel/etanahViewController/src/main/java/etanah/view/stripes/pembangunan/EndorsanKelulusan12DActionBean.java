package etanah.view.stripes.pembangunan;

/**
 *
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasUlasanDAO;
import etanah.model.PermohonanKertas;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasUlasan;
import etanah.service.PembangunanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/pembangunan/melaka/EndorsanKelulusan12D")
public class EndorsanKelulusan12DActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasUlasanDAO permohonanKertasUlasanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private String idPengguna;
    private String catatan;
    private String tarikhEndors;
    private String kpsn;
    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;
    @Inject
    PembangunanService pembangunanService;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/endorsan_Kelulusan_12D.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanKertas kertas = pembangunanService.findKertasByIdPermohonan(idPermohonan, "D12");
        if (kertas != null) {
            PermohonanKertasUlasan ulasan = pembangunanService.findKertasUlasanByIdKertas(kertas.getIdKertas());
            if (ulasan != null) {
                idPengguna = ulasan.getLabel();
                kpsn = ulasan.getKpsn().getKod();
                catatan = ulasan.getUlasan();
                tarikhEndors = sdf.format(kertas.getTarikhSidang());
            }
        }
    }

    public Resolution simpan() throws ParseException {
        Pengguna p = new Pengguna();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        simpanData(idPermohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/endorsan_Kelulusan_12D.jsp").addParameter("tab", "true");
    }

    public void simpanData(String idPermohonan) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        PermohonanKertas kertas = pembangunanService.findKertasByIdPermohonan(idPermohonan, "D12");
        if (kertas != null) {
        } else {
            kertas = new PermohonanKertas();
        }
        InfoAudit ia = new InfoAudit();
        KodDokumen kodDokumen = kodDokumenDAO.findById("D12");
        kertas.setPermohonan(permohonan);
        kertas.setKodDokumen(kodDokumen);
        kertas.setTajuk("Endorsan Borang 12D");
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setInfoAudit(ia);
        kertas.setTarikhSidang(sdf.parse(tarikhEndors));
        pembangunanService.saveKertas(kertas);
        PermohonanKertasUlasan ulasan = pembangunanService.findKertasUlasanByIdKertas(kertas.getIdKertas());
        if (ulasan != null) {
        } else {
            ulasan = new PermohonanKertasUlasan();
        }

        ulasan.setKertas(kertas);
        ulasan.setLabel(idPengguna);
        ulasan.setKpsn(kodKeputusanDAO.findById(kpsn));
        ulasan.setUlasan(catatan);
        ulasan.setInfoAudit(ia);
        pembangunanService.saveKertasUlasan(ulasan);
    }

    public Resolution selesai() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        simpanData(idPermohonan);

        sBMSIntegrationFlowService.initiateEndorsanPelanHitung(permohonan);
        sBMSIntegrationFlowService.deleteTugasanTable(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/endorsan_Kelulusan_12D.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTarikhEndors() {
        return tarikhEndors;
    }

    public void setTarikhEndors(String tarikhEndors) {
        this.tarikhEndors = tarikhEndors;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

}

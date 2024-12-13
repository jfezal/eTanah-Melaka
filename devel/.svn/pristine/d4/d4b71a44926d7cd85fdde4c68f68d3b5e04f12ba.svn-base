/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/tandatanganDokumen")
public class TandatanganActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    StrataPtService strService;
    private Permohonan permohonan;
    private PermohonanTandatanganDokumen mohonDokTT;
    private String idPengguna;

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        mohonDokTT = strService.findMohonDokTT(permohonan.getIdPermohonan(), "SKRU");
        if (mohonDokTT != null) {
            idPengguna = mohonDokTT.getDiTandatangan();
        }
        return new JSP("strata/Ruang_Udara/tandatanganSurat.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = mohonDAO.findById(idPermohonan);
        if (stageId.equals("sediasuratmakluman")) {
            FasaPermohonan mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    mohonDokTT = strService.findMohonDokTT(permohonan.getIdPermohonan(), "SKRU");
                    if (mohonDokTT != null) {
                    } else {
                        mohonDokTT = new PermohonanTandatanganDokumen();
                    }
                    mohonDokTT.setKodDokumen(kodDokumenDAO.findById("SKRU"));
                } else {
                    mohonDokTT = strService.findMohonDokTT(permohonan.getIdPermohonan(), "STRU");
                    if (mohonDokTT != null) {
                    } else {
                        mohonDokTT = new PermohonanTandatanganDokumen();
                    }
                    mohonDokTT.setKodDokumen(kodDokumenDAO.findById("STRU"));
                }
            }
        }

        if (stageId.equals("semakpermit")) {
            mohonDokTT = strService.findMohonDokTT(permohonan.getIdPermohonan(), "SMPRU");
            if (mohonDokTT != null) {
            } else {
                mohonDokTT = new PermohonanTandatanganDokumen();
            }
            mohonDokTT.setKodDokumen(kodDokumenDAO.findById("SMPRU"));
        }

        mohonDokTT.setPermohonan(permohonan);

        mohonDokTT.setDiTandatangan(idPengguna);

        mohonDokTT.setCawangan(permohonan.getCawangan());
        mohonDokTT.setInfoAudit(permohonan.getInfoAudit());
        mohonDokTT = strService.saveDokumenTT(mohonDokTT);
        addSimpleMessage("Maklumat Berjaya Disimpan");

        return new JSP("strata/Ruang_Udara/tandatanganSurat.jsp").addParameter("tab", "true");
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
}

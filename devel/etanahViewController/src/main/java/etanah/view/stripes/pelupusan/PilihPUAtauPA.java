package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.dao.KodKeputusanDAO;
import etanah.model.KodKeputusan;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Orogenic Group
 */
@UrlBinding("/pelupusan/pilihPUPA")
public class PilihPUAtauPA extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodkeputusanDAO;
    private String idPermohonan;
    private FasaPermohonan fasaMohon;
    private Pengguna pengguna;
    private String stageId;
    private Permohonan permohonan;
    private String keputusan;
    private String ulasan;
    private static final Logger log = Logger.getLogger(PilihPUAtauPA.class);

    @DefaultHandler
    public Resolution showForm() {
        log.info("-----Show Form Methode-----");
        return new JSP("pelupusan/Pilih_PU_atau_PA.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        log.info("-----Pengguna Rehydrate-----");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findById(idPermohonan);
        fasaMohon = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "017");

        if (fasaMohon != null) {
            log.info("-----IF Keputusan Kod-----");

            if (fasaMohon.getKeputusan() == null) {
                log.info("-keputusanFrmDb.isEmpty()");
                this.setKeputusan("UP");
            } else {
                this.setKeputusan(fasaMohon.getKeputusan().getKod().toString());
            }
            this.setUlasan(fasaMohon.getUlasan());
        } else {
            log.info("-----Else Keputusan Kod Empty-----");
            this.setKeputusan("");
            this.setUlasan("");
        }
    }

    public Resolution simpan() {
        log.info("-----Simpan Methode-----");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        fasaMohon = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "017");

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodKeputusan kodKeputusan = kodkeputusanDAO.findById(keputusan);
        if (fasaMohon == null) {
            FasaPermohonan fasaMohon = new FasaPermohonan();

            fasaMohon.setIdPengguna(pengguna.getIdPengguna());
            fasaMohon.setCawangan(pengguna.getKodCawangan());
            fasaMohon.setPermohonan(permohonan);
            fasaMohon.setInfoAudit(infoAudit);
            fasaMohon.setIdAliran("017");
            fasaMohon.setKeputusan(kodKeputusan);
            fasaMohon.setUlasan(ulasan);

            log.info("-----ID Pengguna-----" + pengguna.getIdPengguna());
            log.info("-----ID Aliran-----" + fasaMohon.getIdAliran().toString());
            log.info("-----Keputusan-----" + kodKeputusan);
            log.info("-----Ulasan-----" + ulasan);

            pelupusanService.simpanFasaPermohonan(fasaMohon);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            fasaMohon.setIdPengguna(pengguna.getIdPengguna());
            fasaMohon.setCawangan(pengguna.getKodCawangan());
            fasaMohon.setPermohonan(permohonan);
            fasaMohon.setInfoAudit(infoAudit);
            fasaMohon.setIdAliran(fasaMohon.getIdAliran().toString());
            fasaMohon.setKeputusan(kodKeputusan);
            fasaMohon.setUlasan(ulasan);

            log.info("-----ID Pengguna-----" + pengguna.getIdPengguna());
            log.info("-----ID Aliran-----" + fasaMohon.getIdAliran().toString());
            log.info("-----Keputusan-----" + kodKeputusan);
            log.info("-----Ulasan-----" + ulasan);

            pelupusanService.simpanFasaPermohonan(fasaMohon);
            addSimpleMessage("Maklumat telah berjaya kemaskini.");
        }
        return new JSP("pelupusan/Pilih_PU_atau_PA.jsp").addParameter("tab", "true");

    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}

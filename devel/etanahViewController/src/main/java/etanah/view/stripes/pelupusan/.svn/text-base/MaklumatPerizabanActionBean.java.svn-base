package etanah.view.stripes.pelupusan;

import able.stripes.JSP;
import net.sourceforge.stripes.action.ActionBean;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.TanahRizabPermohonan;
import able.stripes.AbleActionBean;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import net.sourceforge.stripes.controller.LifecycleStage;

import etanah.model.Permohonan;
import etanah.model.PermohonanHakmilikUrusanSebelum;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import oracle.bpel.services.workflow.task.model.Task;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.Before;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/maklumat_perizaban")
public class MaklumatPerizabanActionBean extends AbleActionBean {

    private ActionBeanContext context;
    private String tujuanRezab;
    private String pengawalRezab;
    private String idPermohonan;
    private Character aktif = 'Y';
    TanahRizabPermohonan trp;
    private static final Logger LOG = Logger.getLogger(MaklumatPerizabanActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private Permohonan permohonan;
    private String stageId;
    private Pengguna peng;

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public String getTujuanRezab() {
        return tujuanRezab;
    }

    public void setTujuanRezab(String tujuanRezab) {
        this.tujuanRezab = tujuanRezab;
    }

    public String getPengawalRezab() {
        return pengawalRezab;
    }

    public void setPengawalRezab(String pengawalRezab) {
        this.pengawalRezab = pengawalRezab;
    }

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("pelupusan/maklumat_perizaban.jsp").addParameter("tab",
                "true");

    }

    public Resolution reset() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);

        permohonan.setSebab("");
        pelupusanService.simpanPermohonan(permohonan);

        tujuanRezab = permohonan.getSebab();

        return new JSP("pelupusan/maklumat_perizaban.jsp").addParameter("tab",
                "true");
    }

    public Resolution saveData() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        permohonan.setSebab(this.getTujuanRezab());
        LOG.info("tujuan rizab " + this.getTujuanRezab());
        LOG.info("Pegawai Pengawal " + this.getPengawalRezab());


        // Transaction start
        // autocommit 
        try {
            pelupusanService.simpanPermohonan(permohonan);
            trp = new TanahRizabPermohonan();
            trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trp != null) {
                trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "update", pguna));
                trp.setNamaPenjaga(pengawalRezab);
                pelupusanService.simpanTanahRizabPermohonan(trp);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                if (mohonHakmilik != null) {
                        if (!(permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS"))) {

                            trp = new TanahRizabPermohonan();
                            trp.setPermohonan(permohonan);
                            trp.setCawangan(permohonan.getCawangan());
                            trp.setDaerah(permohonan.getCawangan().getDaerah());
                            trp.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                            if (StringUtils.isNotBlank(mohonHakmilik.getNoLot())) {
                                trp.setNoLot(mohonHakmilik.getNoLot());
                            } else {
                                if (mohonHakmilik.getHakmilik() != null) {
                                    trp.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                                }

                            }
//                    trp.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
                            trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "new", pguna));
                            trp.setNamaPenjaga(pengawalRezab);
                            trp.setAktif(aktif);
                            pelupusanService.simpanTanahRizabPermohonan(trp);
                    }
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleError("Maaf, Tiada Maklumat Tanah Dijumpai, Sila Masukkan Perihal Tanah Terlebih Dahulu.");
                }
            }

            // commit
        } catch (Exception ex) {
            ex.printStackTrace();
            // rollback
        }
        return new JSP("pelupusan/maklumat_perizaban.jsp").addParameter("tab",
                "true");

    }

    public Resolution saveDataJadual() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
        permohonan.setSebab(this.getTujuanRezab());
        LOG.info("tujuan rizab " + this.getTujuanRezab());
        LOG.info("Pegawai Pengawal " + this.getPengawalRezab());


        // Transaction start
        // autocommit 
        try {
            pelupusanService.simpanPermohonan(permohonan);
            trp = new TanahRizabPermohonan();
            trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
            if (trp != null) {
                trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "update", pguna));
                trp.setNamaPenjaga(pengawalRezab);
                pelupusanService.simpanTanahRizabPermohonan(trp);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            } else {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik = (HakmilikPermohonan) disLaporanTanahService.findObject(mohonHakmilik, new String[]{idPermohonan}, 1);
                if (mohonHakmilik != null) {
                    trp = new TanahRizabPermohonan();
                    trp.setPermohonan(permohonan);
                    trp.setCawangan(permohonan.getCawangan());
                    trp.setDaerah(permohonan.getCawangan().getDaerah());
                    trp.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru() != null ? mohonHakmilik.getBandarPekanMukimBaru() : mohonHakmilik.getHakmilik().getBandarPekanMukim());
                    if (StringUtils.isNotBlank(mohonHakmilik.getNoLot())) {
                        trp.setNoLot(mohonHakmilik.getNoLot());
                    } else {
                        if (mohonHakmilik.getHakmilik() != null) {
                            trp.setNoLot(mohonHakmilik.getHakmilik().getNoLot());
                        }

                    }
//                    trp.setNoLot(!StringUtils.isEmpty(mohonHakmilik.getNoLot()) ? mohonHakmilik.getNoLot() : mohonHakmilik.getHakmilik().getNoLot());
                    trp.setInfoAudit(disLaporanTanahService.findAudit(trp, "new", pguna));
                    trp.setNamaPenjaga(pengawalRezab);
                    trp.setAktif(aktif);
                    pelupusanService.simpanTanahRizabPermohonan(trp);
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else {
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
            }

            // commit
        } catch (Exception ex) {
            ex.printStackTrace();
            // rollback
        }
        return new JSP("pelupusan/maklumat_perizaban.jsp").addParameter("tab",
                "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        retrieveData();

    }

    public void retrieveData() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = pelupusanService.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
        if (permohonan != null) {
            this.setTujuanRezab(permohonan.getSebab());
            //this.setTujuanRezab(permohonan.getSebab());
        } else {
            this.setTujuanRezab("");
            //this.setTujuanRezab(permohonan.getSebab());
        }
        trp = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
        if (trp != null) {
            this.setPengawalRezab(trp.getNamaPenjaga());
        } else {
            trp = new TanahRizabPermohonan();
            this.setPengawalRezab("");
            //this.setPengawalRezab(trp.getNamaPenjaga());
        }
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "01Kemasukan";
        }
        return stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}

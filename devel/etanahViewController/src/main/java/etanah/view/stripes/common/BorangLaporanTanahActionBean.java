/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/common/laporan_tanah")
public class BorangLaporanTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    private etanah.Configuration conf;
    List<HakmilikPermohonan> senaraiHakmilik;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static final Logger LOG = Logger.getLogger(BorangLaporanTanahActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String stageId;
    private String kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/borang_laporan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP("consent/borang_laporan_tanah.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = listLaporanTanah.get(0);
            }

            List<FasaPermohonan> listFasa;
            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
            kodNegeri = conf.getProperty("kodNegeri");

            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (kodNegeri.equals("05")) {
                        if (fp.getIdAliran().equals("Stage2")) {
                            fasaPermohonan = listFasa.get(i);
                        }
                    } else {
                        if (fp.getIdAliran().equals("Stage1")) {
                            fasaPermohonan = listFasa.get(i);
                        }
                    }
                }
            }

            List<PermohonanRujukanLuar> listRujukanLuar;
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);

            if (!(listRujukanLuar.isEmpty())) {
                for (int i = 0; i < listRujukanLuar.size(); i++) {
                    PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                    rujL = listRujukanLuar.get(i);
                    if (rujL.getKodDokumenRujukan() != null) {
                        if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                            permohonanRujukanLuar = listRujukanLuar.get(i);
                        }
                    }
                }
            }
        }
    }

    public Resolution simpanLaporanTanah() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (laporanTanah != null) {

            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            kodNegeri = conf.getProperty("kodNegeri");
            if (kodNegeri.equals("05")) {
                stageId = "Stage2";
            } else {
                stageId = "Stage1";
            }
        }

        if (fasaPermohonan != null) {

            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());

                KodDokumen kodDokumenWarta = new KodDokumen();
                kodDokumenWarta.setKod("WRKN");

                KodRujukan kodRujukan = new KodRujukan();
                kodRujukan.setKod("FL");
                
                permohonanRujukanLuar.setKodRujukan(kodRujukan);
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/borang_laporan_tanah.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}

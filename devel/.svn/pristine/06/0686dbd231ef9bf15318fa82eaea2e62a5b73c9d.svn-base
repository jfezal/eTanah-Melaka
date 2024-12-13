/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/strata/keputusan_mmk")
public class KeputusanMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private FasaPermohonan fasaPermohonan;
    private Pengguna pengguna;
    private String keputusanSUMMK;
    private String ulasanSUMMK;
    private Permohonan permohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhSidang;
    private PermohonanKertas mohonKertas;
    private String stageId;
    private String kodNegeri;
    private KodDokumen kd = new KodDokumen();
    private String bilKertas;
    private static final Logger LOG = Logger.getLogger(KeputusanMMKActionBean.class);

    public Resolution showForm() {
        return new JSP("strata/kos_rendah/keputusan_summk.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution keputusanMmk() {
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        fasaPermohonan = strService.findFasaPermohonanByIdAliran(idMohon, "keputusanmmk");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan() != null) {
                keputusanSUMMK = fasaPermohonan.getKeputusan().getKod();
            }
            ulasanSUMMK = fasaPermohonan.getUlasan();
            LOG.debug("ulasan : " + ulasanSUMMK);
        }

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

        permohonan = permohonanDAO.findById(idMohon);
        LOG.debug("FIND MOHON KERTAS BY ID MOHON : " + idMohon + " KOD DOKUMEN : " + kd.getKod());
        mohonKertas = strService.findKertasByKod(idMohon, kd.getKod());
        if (mohonKertas != null) {
            LOG.debug("ID_Kertas : " + mohonKertas.getIdKertas());
            if (mohonKertas.getTarikhSidang() != null) {
                tarikhSidang = sdf.format(mohonKertas.getTarikhSidang());
            }
            bilKertas = mohonKertas.getNomborRujukan();
        }

        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/keputusan_summk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info(":: rehydrate ::");

        mohonKertas = strService.findKertas(permohonan.getIdPermohonan());
        InfoAudit info = new InfoAudit();
        if (mohonKertas != null) {
            info = mohonKertas.getInfoAudit();
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());

            if (mohonKertas.getNomborRujukan() != null) {
                bilKertas = mohonKertas.getNomborRujukan();

            } else {
                LOG.info("NomborRujukan is null");

                Integer mhnkertas = strService.findMohonKertasMax();

                if (mhnkertas == null) {
                    LOG.info("mhnkertas :::::::::::::::::::::::: " + mhnkertas);
                    mohonKertas.setNomborRujukan("1");
                    mohonKertas.setInfoAudit(info);
                    strService.simpanPermohonanKertas(mohonKertas);
                } else {
                    LOG.info("mhnkertas :::::::::::::::::::::::: " + mhnkertas);
                    Integer maxMohonKertas = mhnkertas + 1;
                    LOG.info("NEW mhnkertas :::::::::::::::::::::::: " + maxMohonKertas);
                    mohonKertas.setNomborRujukan(String.valueOf(maxMohonKertas));
                    mohonKertas.setInfoAudit(info);
                    strService.simpanPermohonanKertas(mohonKertas);
                    //permohonanKertasDAO.saveOrUpdate(mohonKertas);
                }
            }
        }

        kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("KOD NEGERI : " + kodNegeri);
    }

    public Resolution simpanKeputusanSUMMK() {
        LOG.info("------simpan keputusan PTG-------");

        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        LOG.info("kodUrusan : " + permohonan.getKodUrusan().getKod());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        //retrieve stage ID from BPEL service.
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("stage ID :" + stageId);
        }



        //save in mohon_fasa
        if (permohonan != null) {

            InfoAudit info = new InfoAudit();
            LOG.info("FIND KOD KEPUTUSAN by keputusan SUMMK = " + keputusanSUMMK);
            KodKeputusan kodKeputusan = kodKeputusanDAO.findById(keputusanSUMMK);

            fasaPermohonan = strService.findFasaPermohonanByIdAliran(idMohon, "keputusanmmk");
            if (fasaPermohonan != null) {
                info = fasaPermohonan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
            } else {
                fasaPermohonan = new FasaPermohonan();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
            }


            fasaPermohonan.setInfoAudit(info);
            LOG.info("id pguna : " + pengguna.getIdPengguna());
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setIdAliran("keputusanmmk");
            fasaPermohonan.setUlasan(ulasanSUMMK);
            fasaPermohonan.setKeputusan(kodKeputusan);

            LOG.info(".: IN SAVING MOHON_FASA :.");
            fasaPermohonan = strService.saveFasaMohon(fasaPermohonan);
            LOG.info("CHECK id fasa from insaving : " + fasaPermohonan.getIdFasa());


            //update tarikh sidang dalam table Mohon_Kertas
            mohonKertas = strService.findKertas(permohonan.getIdPermohonan());
            if (mohonKertas != null) {
                LOG.info("ID_Kertas : " + mohonKertas.getIdKertas());

                try {
                    mohonKertas.setTarikhSidang(sdf.parse(tarikhSidang));
                } catch (java.text.ParseException ex) {
                    LOG.error(ex);
                }
                mohonKertas.setNomborRujukan(bilKertas);
                strService.simpanPermohonanKertas(mohonKertas);
                LOG.info("Update tarikh sidang dalam Mohon_Kertas.");


            } else {
                LOG.info("ID_Kertas is null");
            }
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setUlasan(ulasanSUMMK);
            try {
                permohonan.setTarikhKeputusan(sdf.parse(tarikhSidang));
            } catch (java.text.ParseException ex) {
                LOG.error(ex);
            }
            strService.savePermohonan(permohonan);
            LOG.info("UPDATE : trh_kpsn + kod_kpsn + ulasan dalam table Mohon.");

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new RedirectResolution(KeputusanMMKActionBean.class, "keputusanMmk");

    }

    public String getKeputusanSUMMK() {
        return keputusanSUMMK;
    }

    public void setKeputusanSUMMK(String keputusanSUMMK) {
        this.keputusanSUMMK = keputusanSUMMK;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
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

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getUlasanSUMMK() {
        return ulasanSUMMK;
    }

    public void setUlasanSUMMK(String ulasanSUMMK) {
        this.ulasanSUMMK = ulasanSUMMK;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getBilKertas() {
        return bilKertas;
    }

    public void setBilKertas(String bilKertas) {
        this.bilKertas = bilKertas;
    }
}

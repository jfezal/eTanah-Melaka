/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

//import able.stripes.JSP;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.BadanPengurusan;
import etanah.service.common.*;
import etanah.service.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.*;
import etanah.report.ReportUtil;
import etanah.view.*;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.StreamingResolution;

@UrlBinding("/strata/sijilFUS")
public class MaklumatSijilFormulaUS extends AbleActionBean {

    @Inject
    private etanah.Configuration conf;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    StrataPtService strataPtService;
    @Inject
    BPelService service;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Projek projek;
    private Permohonan permohonan;
    private String idPermohonan;
    private String gunaBngn;
    private String stageId;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(MaklumatSijilFormulaUS.class);

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        stageId = getBPLStageId();
        projek = strataPtService.findSifusbyIdPermohonan(idPermohonan);
        if (projek != null) {
            if (projek.getGunaBngn() != null) {
                gunaBngn = projek.getGunaBngn().getKod();
            }
        }
        return new JSP("strata/sijilFormulaUS.jsp").addParameter("tab", "true");
    }

    public String getBPLStageId() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("--stageId--:" + stageId);
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan  = permohonanDAO.findById(idPermohonan);
        projek = strataPtService.findSifusbyIdPermohonan(idPermohonan);
    }

    public Resolution save() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Projek pr = strataPtService.findSifusbyIdPermohonan(idPermohonan);

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new Date());

        String msg = "";
        String error = "";

        if (pr == null) {
            Projek pj = new Projek();
            pj.setFormula(projek.getFormula());//fromula
            if (gunaBngn != null) {
                pj.setGunaBngn(kodKegunaanBangunanDAO.findById(gunaBngn));//guna_bngn
            } else {
                pj.setKegunaanLain(projek.getKegunaanLain());
            }
            pj.setIdHakmilik(hp.getHakmilik().getIdHakmilik());//id_jhakmilik
            pj.setInfoAudit(ia);
            pj.setIdPermohonan(idPermohonan);
            pj.setJenisProjek(projek.getJenisProjek());//nama skim
            pj.setJumlahSemuaUnit(projek.getJumlahSemuaUnit());//bil_petak
            pj.setJumlahUnitSementara(projek.getJumlahUnitSementara());//bil_blok_sementara
            pj.setNamaPemaju(projek.getNamaPemaju());//pemohon
            pj.setNoRujFail(projek.getNoRujFail());//ruj_fail
            pj.setPemilik(projek.getPemilik());//pemilik
            pj.setMaksimumUnit(projek.getMaksimumUnit());//siri
            pj.setNoRujukanProjek(projek.getNoRujukanProjek());//ruj projek
            pj.setUnitDiJual(projek.getUnitDiJual());
            pj.setAktif("M");
            pj.setArkitek(projek.getArkitek());

            strataPtService.simpanProjek(pj);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            pr.setInfoAudit(ia2);
            pr.setFormula(projek.getFormula());//fromula
            if (gunaBngn != null) {
                pr.setGunaBngn(kodKegunaanBangunanDAO.findById(gunaBngn));//guna_bngn
            } else {
                pr.setKegunaanLain(projek.getKegunaanLain());
            }
            pr.setJenisProjek(projek.getJenisProjek());//nama skim
            pr.setJumlahSemuaUnit(projek.getJumlahSemuaUnit());//bil_petak
            pr.setJumlahUnitSementara(projek.getJumlahUnitSementara());//bil_blok_sementara
            LOG.info("--UNIT SEMENTARA 2--:" + projek.getJumlahUnitSementara());
            pr.setNamaPemaju(projek.getNamaPemaju());//pemohon
            pr.setNoRujFail(projek.getNoRujFail());//ruj_fail
            pr.setPemilik(projek.getPemilik());//pemilik
            pr.setMaksimumUnit(projek.getMaksimumUnit());//siri
            pr.setNoRujukanProjek(projek.getNoRujukanProjek());
            pr.setUnitDiJual(projek.getUnitDiJual());
            pr.setArkitek(projek.getArkitek());
            strataPtService.simpanProjek(pr);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

//        return new RedirectResolution("strata/sijilFormulaUS.jsp").addParameter("tab", "true");
//        return new JSP("strata/sijilFormulaUS.jsp");
//        return new JSP("strata/sijilFormulaUS.jsp").addParameter("tab", "true");
        return showForm();
//        return new RedirectResolution(MaklumatSijilFormulaUS.class,"showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution delete() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        List<PermohonanBangunan> listBngn = new ArrayList<PermohonanBangunan>();
        List<BangunanTingkat> ringkasanTgkt = new ArrayList<BangunanTingkat>();
        List<BangunanTingkat> listTgkt = new ArrayList<BangunanTingkat>();
        List<BangunanPetak> listPetak = new ArrayList<BangunanPetak>();
        List<BangunanPetak> ringkasanPetak = new ArrayList<BangunanPetak>();
        List<BangunanPetakAksesori> ringkasanPetakAksesori = new ArrayList<BangunanPetakAksesori>();
        
        if (idPermohonan != null) {
            List<PermohonanBangunan> ringkasanBngn = strService.checkMhnBangunanExist(idPermohonan);
            for (PermohonanBangunan bngn : ringkasanBngn) {
                listBngn.add(bngn);
                ringkasanTgkt = strService.findByIDBangunan(bngn.getIdBangunan());
                for (BangunanTingkat tgkt : ringkasanTgkt) {
                    listTgkt.add(tgkt);
                    ringkasanPetak = strService.findPetakByIdTingKat(tgkt.getIdTingkat());
                    for (BangunanPetak petak : ringkasanPetak) {
                        listPetak.add(petak);
                    }
                }
                ringkasanPetakAksesori = strService.findPtkAksrByIdTgkt(bngn.getIdBangunan());
                for (BangunanPetakAksesori aksr : ringkasanPetakAksesori) {
                    strService.deleteAksesori(aksr);
                }
            }

            int count = 0;
            for (BangunanPetak petak : listPetak) {
                strService.deletePetak(petak);
                count = 1;
            }
            for (BangunanTingkat tgkt : listTgkt) {
                strService.deleteTgkt(tgkt);
                count = 1;
            }
            for (PermohonanBangunan bngn : listBngn) {
                strService.deleteBngn(bngn);
                count = 1;
            }

            if (count == 1) {
                addSimpleMessage("Jadual Petak Berjaya DiHapuskan");
            } else {
                addSimpleError("Jadual Petak Tidak Berjaya DiHapuskan");
            }
        }

        return new JSP("strata/sijilFormulaUS.jsp").addParameter("tab", "true");
    }

    public Projek getProjek() {
        return projek;
    }

    public void setProjek(Projek projek) {
        this.projek = projek;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getGunaBngn() {
        return gunaBngn;
    }

    public void setGunaBngn(String gunaBngn) {
        this.gunaBngn = gunaBngn;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}

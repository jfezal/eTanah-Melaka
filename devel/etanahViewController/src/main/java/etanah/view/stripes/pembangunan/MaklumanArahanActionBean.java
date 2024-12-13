package etanah.view.stripes.pembangunan;
/**
 *
 * @author NageswaraRao
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.service.PembangunanService;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.service.BPelService;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import java.util.ArrayList;
import java.util.List;

@UrlBinding("/pembangunan/melaka/MaklumanArahan")
public class MaklumanArahanActionBean extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(MaklumanArahanActionBean.class);
    @Inject
    PembangunanService devServ;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;

    private String arahan;
    private FasaPermohonan fp;
    private BPelService service;
    private String stageId;
    private String ulasan;

    @DefaultHandler
    public Resolution showForm() {
        logger.info("showForm Edit");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/makluman_Arahan.jsp").addParameter("tab", "true");
    }


    public Resolution showForm2() {
        logger.info("showForm View");
        return new JSP("pembangunan/melaka/makluman_Arahan.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        logger.info("rhydrate start.");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        if (StringUtils.isBlank(taskId)) {
//            taskId = getContext().getRequest().getParameter("taskId");
//        }
//        Task task = service.getTaskFromBPel(taskId, pengguna);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
//        }
//
        stageId="maklumanarahan";
        if (idPermohonan != null) {
              fp = devServ.findFasaPermohonanByIdAliran(idPermohonan, stageId);
        }
        if(fp==null){
            logger.info("--- test---:"+permohonan.getKodUrusan().getKod());
            if(permohonan.getKodUrusan().getKod().equals("PBTL")){
                logger.info("-true----");
                ulasan = " PPD(72) / PHG7 \n Surat seperti di kandungan (  ) dirujuk untuk Noting Pembatalan Permohonan. \n Terima Kasih.";
            }
        }else{
            ulasan = fp.getUlasan();
        }
        logger.info("rhydrate end.");

    }
    public Resolution simpan(){
        logger.info("simpan start.");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        List<FasaPermohonan> senaraiMohonFasa = new ArrayList<FasaPermohonan>();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setKeputusan(kodKeputusanDAO.findById("BT"));
        if(permohonan.getPermohonanSebelum()!=null){
            logger.info("--- permohonan.getPermohonanSebelum().getIdPermohonan()---:"+permohonan.getPermohonanSebelum().getIdPermohonan());
            senaraiMohonFasa = devServ.findFasaPermohonanByIdAliranGIS(permohonan.getPermohonanSebelum().getIdPermohonan());
            logger.info("--- Mohon fasa by IdAliran Size---:"+senaraiMohonFasa.size());
            if(senaraiMohonFasa.size() == 1){
               permohonan.setStageGis("P");
            }else if(senaraiMohonFasa.size() == 2){
                permohonan.setStageGis("K");
            }
        }
        devServ.simpanPermohonan(permohonan);

        if(fp != null){
            infoAudit = fp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            fp.setPermohonan(permohonan);
            fp.setUlasan(ulasan);
            fp.setIdAliran(stageId);
            fp.setInfoAudit(infoAudit);
            fp.setCawangan(pengguna.getKodCawangan());
            devServ.simpanFasaPermohonan(fp);

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            FasaPermohonan fMohon = new FasaPermohonan();
            fMohon.setPermohonan(permohonan);
            fMohon.setUlasan(ulasan);
            fMohon.setIdAliran(stageId);
            fMohon.setInfoAudit(infoAudit);
            fMohon.setCawangan(pengguna.getKodCawangan());
            devServ.simpanFasaPermohonan(fMohon);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/makluman_Arahan.jsp").addParameter("tab", "true");
    }

    public String getArahan() {
        return arahan;
    }

    public void setArahan(String arahan) {
        this.arahan = arahan;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }


}




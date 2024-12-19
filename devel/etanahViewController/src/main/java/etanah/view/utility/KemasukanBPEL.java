/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.InfoAuditBaru;
import etanah.model.KodCawangan;
import etanah.workflow.WorkFlowService;
import etanah.model.Pengguna;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author solahuddin
 */
@HttpCache(allow = false)
@UrlBinding("/utility/initiate")
public class KemasukanBPEL extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(KemasukanBPEL.class);
    private Permohonan mohon;
    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    @Inject
    private PermohonanService service;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        getContext().getRequest().setAttribute("cari", Boolean.TRUE);
        return new JSP("utiliti/InitiateBPEL.jsp");
    }

    public Resolution initiateBPEL() {
        logger.info("Initiating BPEL....");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohon = mohonDAO.findById(mohon.getIdPermohonan());
        String pcNama = System.getProperty("user.home");
        if (mohon != null) {
            FolderDokumen fd = mohon.getFolderDokumen();
            if (fd == null) {
                fd = new FolderDokumen();
                fd.setTajuk("-");
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                fd.setInfoAudit(ia);

                InfoAuditBaru iaBaru = new InfoAuditBaru();
                iaBaru.setDikemaskiniOlehBaru(pengguna);
                iaBaru.setTarikhKemaskiniBaru(new Date());

                service.saveOrUpdateFolder(fd);
                mohon.setFolderDokumen(fd);
                service.saveOrUpdate(mohon);
            }
        }
        try {
            if (mohon.getKodUrusan().getKePTG() == 'Y') {
                WorkFlowService.initiateTask(mohon.getKodUrusan().getIdWorkflow(),
                        mohon.getIdPermohonan(), mohon.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        mohon.getKodUrusan().getNama());
//                mohon.setInfoAuditBaru(infoAuditBaru);
                mohon.setCatatan(pcNama);
                service.saveOrUpdate(mohon);
                List<HakmilikPermohonan> listHP = mohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : listHP) {
                    hp.setCatatan(pcNama);
                    hakmilikPermohonanDAO.saveOrUpdate(hp);
                }
            } else if (mohon.getKodUrusan().getKePTG() == 'T') {
                WorkFlowService.initiateTask(mohon.getKodUrusan().getIdWorkflow(),
                        mohon.getIdPermohonan(), mohon.getCawangan().getKod(), pengguna.getIdPengguna(),
                        mohon.getKodUrusan().getNama());
                mohon.setCatatan(pcNama);
                service.saveOrUpdate(mohon);
                List<HakmilikPermohonan> listHP = mohon.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : listHP) {
                    hp.setCatatan(pcNama);
                    hakmilikPermohonanDAO.saveOrUpdate(hp);
                }
            }
            addSimpleMessage("Kemasukan Permohonan Berjaya");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("Initiating BPEL Success....");
        return new ForwardResolution("/WEB-INF/jsp/utiliti/InitiateBPEL.jsp");
    }

    public Resolution searchMohon() throws Exception {
        logger.info("Searching task....");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {

            if (StringUtils.isNotBlank(mohon.getNoPerserahanSptb())) {
                String id = "";
                if (mohon.getNoPerserahanSptb().indexOf("/") > 0) {
                    String[] SplitSlash = mohon.getNoPerserahanSptb().split("/");
                    String SptbID = SplitSlash[0];
                    String SptbYear = SplitSlash[1];

                    while (SptbID.length() < 6) {
                        SptbID = "0" + SptbID;
                    }
                    id = id.concat(SptbYear).concat(SptbID);
                }
                KodCawangan caw = pengguna.getKodCawangan();
                mohon = service.getPermohonanByIdSptb(id.equals("") ? mohon.getNoPerserahanSptb() : id, caw.getKod(),
                        mohon.getKodUrusan().getKodPerserahan().getKod());
            } else {
                mohon = mohonDAO.findById(mohon.getIdPermohonan());
            }

            if (mohon != null) {
                IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
                List taskBPEL = WorkFlowService.queryTasksByIdMohon(ctx, mohon.getIdPermohonan());
                if (taskBPEL.size() > 0) {
                    addSimpleError("Tugasan telah wujud");
                } else {
                    //TODO:tukar ayat proper.
                    addSimpleMessage("Utiliti boleh diteruskan");
                    getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                    getContext().getRequest().setAttribute("cari", Boolean.FALSE);
                }

            } else {
                addSimpleError("ID Permohonan tidak wujud!");
                getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                getContext().getRequest().setAttribute("cari", Boolean.TRUE);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

//        getContext().getRequest().setAttribute("cari", Boolean.FALSE);
//        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        logger.info("Searching task Success....");
        return new ForwardResolution("/WEB-INF/jsp/utiliti/InitiateBPEL.jsp");
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

//import able.stripes.JSP;
import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
//import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
//import etanah.model.KodUrusan;
import etanah.model.PenggunaPeranan;
//import etanah.view.stripes.fail;
//import etanah.view.stripes.faildao;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import javax.servlet.http.HttpSession;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
//import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanRujukanLuarService;
import java.util.Date;

/**
 *
 * @author w.fairul
 */
@UrlBinding("/strata/agihTugasan")
public class AgihTugasanActionBean extends AbleActionBean {

    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<Pengguna> listPp1;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private boolean isBerangkai = Boolean.FALSE;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihTugasanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private boolean isBerhalang = Boolean.FALSE;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    private String stageId;


    @Inject
    public AgihTugasanActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();

        if (lhp.size() > 0) {
            Hakmilik h = lhp.get(0).getHakmilik();
//            isBerhalang = regService.periksaHalangan(permohonan, h);
            logger.debug("isBerhalang:" + isBerhalang);
            if (isBerhalang) {
                addSimpleError("Hakmilik ini Mempunyai Halangan Mahkamah");
            }
        }

        KodCawangan kod = pguna.getKodCawangan();
        // kene cater ikut peranan..
//        List<PenggunaPeranan> list = penggunaperananDao.findAll();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();

        for (Pengguna p : lp) {
//            System.out.println("p : " + p.getIdPengguna());
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    //TODO : change peranan utama
                    // Melaka kod PerananUtama = 60 - PT Strata
                    // Office kod PerananUtama = 1 - PT Strata
                    if (p.getPerananUtama().getKod().equals("60")
                            || (p.getIdPengguna().equals("ptstrata1") || p.getIdPengguna().equals("ptstrata2") || p.getIdPengguna().equals("ptstrata3"))) {
                        if (p.getStatus().getKod().equals("A") ){
                        listPp.add(p);
                        }
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/pbbm/agih_tugasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        System.out.println("IdPermohonan :" + idPermohonan);
        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);

        }

    }


    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idMohon);

        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idMohon);
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                logger.info("-----Stage ID:" + stageId + "-----");
            }
            } catch (Exception e) {
                logger.error("error ::" + e.getMessage());
            }

        StringBuilder sb = new StringBuilder();
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID();
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if(sb.length() > 0){
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                Task task = null;
                String stageID = null;
                task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                stageID = task.getSystemAttributes().getStage();
                syslog.info(pguna.getIdPengguna()+" agih tugasan untuk permohonan :"+idPermohonan+" kepada "+pengguna.getIdPengguna());

                logger.info("stage from task: " + stageId);
                Permohonan permohonan = mohonDao.findById(idPermohonan);

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equals(stageID)
                            && fp.getIdPengguna().equals(pengguna.getIdPengguna())) {
                        _fp = fp;
                        break;
                    }
                }

                logger.debug("cawangan " + pengguna.getKodCawangan());

                if (_fp != null) {
                    au = _fp.getInfoAudit();
                    au.setTarikhKemaskini(new Date());
                    au.setDiKemaskiniOleh(pguna);
                    _fp.setInfoAudit(au);
                } else {
                    _fp = new FasaPermohonan();
                    au.setDimasukOleh(pguna);
                    au.setTarikhMasuk(new Date());
                    _fp.setInfoAudit(au);
                    _fp.setPermohonan(mohon);
                    _fp.setCawangan(pengguna.getKodCawangan());
                    _fp.setIdAliran(stageID);
                    _fp.setIdPengguna(pengguna.getIdPengguna());
                }
                _fp.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(_fp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        //return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " : Agihan tugasan kepada " + pengguna.getNama() + " (" + pengguna.getIdPengguna()+ ") " + " telah Berjaya.");
    }


    private List<Map<String, String>> getPermohonanWithTaskID() throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;

        logger.info("Urusan tidak berangkai");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", mohon.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);

        return list;
    }


    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if (isBerangkai) {
            logger.info("Urusan Berangkai");
            List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            logger.info("taskList :: " + taskList.size());
            for (int i = 0; i < taskList.size(); i++) {
                Task impl = (Task) taskList.get(i);
                String taskId = impl.getSystemAttributes().getTaskId();
                String idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
                System.out.println("taskID::" + taskId);
                Task temp = WorkFlowService.getTask(taskId, ctx);

                if (temp.getSystemAttributes().getAcquiredBy() == null) {
                    WorkFlowService.acquireTask(taskId, ctx);
                }

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    logger.info("idPermohonan :: " + idPermohonan);
                    logger.info("p.idPermohonan :: " + p.getIdPermohonan());
                    if (p.getIdPermohonan().equals(idPermohonan)) {
                        map = new HashMap<String, String>();
                        map.put("idPermohonan", p.getIdPermohonan());
                        map.put("taskId", taskId);
                        list.add(map);
                    }
                }
            }
        } else {
            //standalone
            logger.info("Urusan tidak berangkai");
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            map = new HashMap<String, String>();
            map.put("idPermohonan", mohon.getIdPermohonan());
            map.put("taskId", taskId);
            list.add(map);
        }
        return list;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public List<Pengguna> getListPp() {
        return listPp;
    }

    public void setListPp(List<Pengguna> listPp) {
        this.listPp = listPp;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public List<Permohonan> getSenaraiPermohonanBerangkai() {
        return senaraiPermohonanBerangkai;
    }

    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
    }
}
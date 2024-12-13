package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.PenggunaPeranan;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.stripes.SenaraiTugasanActionBean;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/pindaanAgihan")
public class PindaanAgihanActionBean extends AbleActionBean{
    PenggunaDAO penggunaDao;
    PermohonanDAO mohonDao;
    PenggunaPerananDAO penggunaperananDao;
    private Permohonan mohon;
    String urusan;
    Pengguna pengguna;
    private Pengguna pguna;
    String IdPermohonan;
    private List<Pengguna> listPp;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    private String arahan;

    @Inject
    PermohonanService permohonanService;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(PindaanAgihanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");

    @Inject
    public PindaanAgihanActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

    @DefaultHandler
    public Resolution showForm() {

//        KodCawangan kod = pguna.getKodCawangan();
//        List<PenggunaPeranan> list = penggunaperananDao.findAll();
//        List<Pengguna> lp = penggunaDao.findAll();
//        listPp = new LinkedList<Pengguna>();
//
//        for (Pengguna p : lp) {
//            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
//                if (p.getPerananUtama() != null) {
//                    if (p.getPerananUtama().getKod().equals("41")) {
//                        listPp.add(p);
//                    }
//                }
//            }
//        }

        System.out.println("---------ShowForm-------------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/pindaan_Agihan.jsp").addParameter("tab", "true");
        
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        try {
//            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
//        } catch (Exception e) {
//            logger.error("error ::" + e.getMessage());
//        }
//        System.out.println("IdPermohonan :" + idPermohonan);
//
//        if (idPermohonan != null) {
//            mohon = mohonDao.findById(idPermohonan);
//        }
    }

    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL
        StringBuilder sb = new StringBuilder();
        try {

            List<Map<String, String>> list = getPermohonanWithTaskID();
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if(sb.length() > 0) sb.append(",");
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                syslog.info(pguna.getIdPengguna()+" agih tugasan untuk permohonan :"+idPermohonan+" kepada "+pengguna.getIdPengguna());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class)
                    .addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        return new RedirectResolution(SenaraiTugasanActionBean.class)
                .addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
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

    public String getArahan() {
        return arahan;
    }

    public void setArahan(String arahan) {
        this.arahan = arahan;
    }

//    public List<Permohonan> getSenaraiPermohonanBerangkai() {
//        return senaraiPermohonanBerangkai;
//    }
//
//    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
//        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
//    }

    
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;


import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
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
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.PenggunaPeranan;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.RegService;
import etanah.service.common.FasaPermohonanService;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import net.sourceforge.stripes.action.StreamingResolution;


/**
 *
 * @author khairil
 */
@UrlBinding("/agihTugasan")
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
     @Inject
    FasaPermohonanService fasaPermohonanManager;
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihTugasanActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    private boolean isBerhalang = Boolean.FALSE;

    @Inject
    public AgihTugasanActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }
    
    @DefaultHandler
    public Resolution showForm() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();
        
        if (lhp.size() > 0) {
            
            String[] URUSAN_CONVERT = {
                "HSTHK",
                "HKTHK",
                "HMSC",
                "HTIR"
            };

//            if (!ArrayUtils.contains(URUSAN_CONVERT, permohonan.getKodUrusan().getKod())) {
//
//                List<Hakmilik> senaraiHakmilikPerluConvert = new ArrayList<Hakmilik>();
//
//                for (HakmilikPermohonan hp : lhp) {
//                    if (hp == null || hp.getHakmilik() == null) {
//                        continue;
//                    }
//                    Hakmilik hm = hp.getHakmilik();
//                    if (hm.getNoVersiDhde() != null && hm.getNoVersiDhde() == 0) {
//                        senaraiHakmilikPerluConvert.add(hm);
//                    }
//                }
//
//                if (!senaraiHakmilikPerluConvert.isEmpty()) {
//                    StringBuilder sb = new StringBuilder();
//                    for (Hakmilik hm : senaraiHakmilikPerluConvert) {
//                        sb.append(hm.getIdHakmilik());
//                    }
//
//                    StringBuilder msg = new StringBuilder("Hakmilik ")
//                            .append(sb.toString())
//                            .append(" perlu ditukarganti.");
//
//                    if (StringUtils.isNotBlank(permohonan.getCatatan())) {
//                        msg.append("\n")
//                                .append("ID Permohonan ")
//                                .append(permohonan.getCatatan());
//                    }
//
//                    addSimpleError(msg.toString());
//                    return new ForwardResolution("/WEB-INF/jsp/daftar/agih_tugasan.jsp").addParameter("tab", "true");
//                }
//
//            }

            
            
            Hakmilik h = lhp.get(0).getHakmilik();
            isBerhalang = regService.periksaHalangan(permohonan,h);
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
            if (p == null) continue;
//            System.out.println("p : " + p.getIdPengguna());
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null && p.getStatus() != null) {
                    //TODO : change peranan utama
                    if (p.getStatus().getKod().equals("A")) {
                        if ( p.getPerananUtama().getKod().equals("1") ) {
                            listPp.add(p);
                        } else if ( p.getSenaraiPeranan() != null
                                && !p.getSenaraiPeranan().isEmpty() ) {
                            for (PenggunaPeranan pp : p.getSenaraiPeranan()) {
                                if (pp == null || pp.getPeranan() == null) continue;
                                if ( pp.getPeranan().getKod().equals("1")) {
                                    listPp.add(p);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        Comparator c = new Comparator<Pengguna>() {
            @Override
            public int compare(Pengguna p1, Pengguna p2) {
                String penggunaName1 = p1.getNama();
                String penggunaName2 = p2.getNama();
                return penggunaName1.compareTo(penggunaName2);
            }
        };
        Collections.sort(listPp, c);
        return new ForwardResolution("/WEB-INF/jsp/daftar/agih_tugasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiPermohonanBerangkai = new ArrayList<Permohonan>();
        try {
            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        logger.debug("IdPermohonan :" + idPermohonan);

        if (idPermohonan != null) {
            mohon = mohonDao.findById(idPermohonan);
            String idKumpulan = mohon.getIdKumpulan();
            if (StringUtils.isNotBlank(idKumpulan)) {
                isBerangkai = Boolean.TRUE;
                senaraiPermohonanBerangkai = permohonanService.getPermohonanByIdKump(idKumpulan);
            } else {
                senaraiPermohonanBerangkai.add(mohon);
            }
        }
    }

    public Resolution agihPT() {
        //TODO INTEGRATION WITH BPEL 
        //        idPermohonan = mohon.getIdPermohonan();
//        mohon = mohonDao.findById(idPermohonan);
        StringBuilder sb = new StringBuilder();
        try {
            //TODO urusan berangkai
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            System.out.println("taskId :" + taskId);
//            System.out.println("pguna :" + pguna.getIdPengguna());
//            ctx = WorkFlowService.authenticate(pguna.getIdPengguna());
//            if (ctx != null) {
//                System.out.println("hantar kepada " + pengguna.getIdPengguna());
//                //update outcome to move to next stage
//                WorkFlowService.updateTaskOutcome(taskId, ctx, "APPROVE");
//                WorkFlowService.reassignTask(ctx, taskId, pengguna.getIdPengguna(), "user");
//            }

            List<Map<String, String>> list = getPermohonanWithTaskID(pguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                String idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);
//                WorkFlowService.updateTaskOutcome(taskID, ctx, "APPROVE");
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                Task task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                String stageId = task.getSystemAttributes().getStage();

                Permohonan permohonan = mohonDao.findById(idPermohonan);

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
                FasaPermohonan _fp = null;
                InfoAudit au = new InfoAudit();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equals(stageId)
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
                    _fp.setPermohonan(permohonan);
                    _fp.setCawangan(pengguna.getKodCawangan());
                    _fp.setIdAliran(stageId);
                    _fp.setIdPengguna(pengguna.getIdPengguna());                    
                }                
                _fp.setTarikhHantar(new Date());
                fasaPermohonanManager.saveOrUpdate(_fp);
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanAction.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanAction.class).addParameter("message", sb.toString() + " :Agihan Tugasan Berjaya.");
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

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    logger.info("idPermohonan :: " + idPermohonan);
                    logger.info("p.idPermohonan :: " + p.getIdPermohonan());
                    if (p.getIdPermohonan().equals(idPermohonan)) {
                        logger.debug("taskID::" + taskId);
                        Task temp = WorkFlowService.getTask(taskId, ctx);

                        if (temp.getSystemAttributes().getAcquiredBy() == null) {
                            WorkFlowService.acquireTask(taskId, ctx);
                        }
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

    public Resolution query() {

        String result = "0";
        String idPengguna = getContext().getRequest().getParameter("idPengguna");
        
        if (StringUtils.isNotBlank(idPengguna)) {

            try{

                pengguna = penggunaDao.findById(idPengguna);
                ctx = WorkFlowService.authenticate(idPengguna);

                List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
                result = String.valueOf(taskList.size());
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }            
        }
        
        return new StreamingResolution("text/plain", result);
    }

    public boolean isIsBerhalang() {
        return isBerhalang;
    }

    public void setIsBerhalang(boolean isBerhalang) {
        this.isBerhalang = isBerhalang;
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

/**
 *
 * @author w.fairul
 * @modify Mohd Hairudin Mansur 21042011
 * modify by Siti Fariza Hanim
 */

package etanah.view.stripes.pelupusan;

import etanah.view.stripes.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Date;
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
import etanah.service.common.HakmilikUrusanService;
import etanah.service.RegService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PelupusanService;


@UrlBinding("/pelupusan/agihTugasan/{currentStage}")
public class AgihanTugasDisActionBean extends AbleActionBean {

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
    IWorkflowContext ctx = null;
    Logger logger = Logger.getLogger(AgihanTugasDisActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    //private boolean isBerhalang = Boolean.FALSE;
    private String nota;
    private String stageID;
    private String participant;
    
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    PermohonanRujukanLuarService mohonRujukLuarService;
    @Inject
    RegService regService;
    @Inject
    PelupusanService perlupusanService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    private etanah.Configuration conf;
    
    @Inject
    public AgihanTugasDisActionBean(PenggunaDAO penggunaDao, PermohonanDAO mohonDao, PenggunaPerananDAO penggunaperananDao) {
        this.penggunaDao = penggunaDao;
        this.mohonDao = mohonDao;
        this.penggunaperananDao = penggunaperananDao;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String currentStage = getContext().getRequest().getParameter("currentStage");
        logger.info("currentStage: " + currentStage);

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = mohonDao.findById(idPermohonan);
        String kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri: " + kodNegeri);
        urusan = permohonan.getKodUrusan().getKod();
        List<HakmilikPermohonan> lhp = permohonan.getSenaraiHakmilik();

        /*if (lhp.size() > 0) {
            Hakmilik h = lhp.get(0).getHakmilik();
            // isBerhalang = regService.periksaHalangan(permohonan, h);
            logger.debug("isBerhalang:" + isBerhalang);
            if (isBerhalang) {
                addSimpleError("Hakmilik ini Mempunyai Halangan Mahkamah");
            }
        }*/

        KodCawangan kod = pguna.getKodCawangan();
        // kene cater ikut peranan..
        // List<PenggunaPeranan> list = penggunaperananDao.findAll();
        List<Pengguna> lp = penggunaDao.findAll();
        listPp = new LinkedList<Pengguna>();
        String kodPerananUtama = pguna.getPerananUtama().getKod().toString();

        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            for (Task t : l) {
                stageID = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getParticipantName();
            logger.info("-----Stage ID:" + stageID + "-----");
            }
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        
        logger.info(pguna.getNama());
        logger.info(kodPerananUtama);
        
        // get the pengguna list for agihan tugas
        if(kodNegeri.equalsIgnoreCase("04")){ // melaka
        	this.getListPgunaMelaka(lp, kod, kodPerananUtama, currentStage);
        } else if(kodNegeri.equalsIgnoreCase("05")) { // negeri sembilan
        	this.getListPgunaNS(lp, kod, kodPerananUtama, currentStage);
        }
        
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/common/agih_tugasan.jsp").addParameter("tab", "true");
    }
    
    /**
     * 
     * @param lp
     * @param kod
     * @param kodPerananUtama
     * @param currentStage
     * @author Mohd Hairudin Mansur
     * @version 21042011
     */
    private void getListPgunaMelaka(List<Pengguna> lp, KodCawangan kod, String kodPerananUtama, String currentStage) {
        logger.info("THIS IS PERANANUTAMA->"+kodPerananUtama);
    	for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (kodPerananUtama.equalsIgnoreCase("227")) { // ppt(k)
                        if((stageID.equalsIgnoreCase("agihan_tugas")) 
                        		|| (stageID.equalsIgnoreCase("agihan_tugas4")) 
                        		|| (stageID.equalsIgnoreCase("agihan_tugas5"))
                        		|| (stageID.equalsIgnoreCase("agihan_tugas6"))) {
	                        if (p.getPerananUtama().getKod().equals("226")) { // ppelan
	                            listPp.add(p);
	                        }
                        } else if((stageID.equalsIgnoreCase("agihan_tugas3")) && 
                        			(urusan.equals("PBMT"))) {
                        	if (p.getPerananUtama().getKod().equals("226")) { // ppelan
	                            listPp.add(p);
	                        }
                        }
                        else if (((stageID.equalsIgnoreCase("agihan_tugas2")) || (stageID.equalsIgnoreCase("agihan_tugas3")))
                                && (urusan.equals("PRMP"))) {
                            if (p.getPerananUtama().getKod().equals("228")) { // pptanah1
                                listPp.add(p);
                            }
                        }
                        else if (((stageID.equalsIgnoreCase("agihan_tugas2")))
                                && (urusan.equals("PPTR"))) {
                            if (p.getPerananUtama().getKod().equals("228")) { // pptanah1
                                listPp.add(p);
                            }
                        }
                        else if((stageID.equalsIgnoreCase("14ArahanTugas")) && 
                        			(urusan.equals("LPSP"))) {
                        	if (p.getPerananUtama().getKod().equals("226")) { // ppelan
	                            listPp.add(p);
	                        }
                        }else if((stageID.equalsIgnoreCase("agihan_tugas3")) && 
                        			(urusan.equals("LPSP"))) {
                        	if (p.getPerananUtama().getKod().equals("228")) { // mtpptanah1
	                            listPp.add(p);
	                        }
                        } 
                        else if((stageID.equalsIgnoreCase("agihan_tugas3")) && 
                        			(urusan.equals("PLPS"))) {
                        	if (p.getPerananUtama().getKod().equals("226")) { // pptanah(perubahan ke ppelan)
	                            listPp.add(p);
	                        }
                        }
                        else if((stageID.equalsIgnoreCase("agihan_tugas3")) && 
                        			(urusan.equals("PPRU"))) {
                        	if (p.getPerananUtama().getKod().equals("226")) { // pptanah(perubahan ke ppelan)
	                            listPp.add(p);
	                        }
                        }
                        else if(stageID.equalsIgnoreCase("arahan_tugas")){
	                         if (p.getPerananUtama().getKod().equals("220")) { //pt
	                            listPp.add(p);
	                        }
                        } else if((stageID.equalsIgnoreCase("arahan_sedia_draf_mmkn")) || (stageID.equalsIgnoreCase("arahan_tugas3"))){
	                         if (p.getPerananUtama().getKod().equals("228")) { //Penolong Pegawai Tanah
	                            listPp.add(p);
	                         }
                        }
                         else if((stageID.equalsIgnoreCase("agihan_tugas2")) && ((urusan.equals("PPBB")) || (urusan.equals("LPSP")) || (urusan.equals("PBPTD")) || urusan.equals("PBPTG"))){
	                         if (p.getPerananUtama().getKod().equals("228")) { //Penolong Pegawai Tanah
	                            listPp.add(p);
	                         }
                        }
                         else if(stageID.equalsIgnoreCase("agihan_tugasan7") && ((urusan.equals("PPBB")) || (urusan.equals("PBPTD")) || urusan.equals("PBPTG"))) {
                            if(p.getPerananUtama().getKod().equals("226")){ //Pelukis Pelan
                             listPp.add(p);
                            }
                         }
                           else if(stageID.equalsIgnoreCase("agihan_tugas8") && ((urusan.equals("PPBB")))) {
                                if(p.getPerananUtama().getKod().equals("226")){ //Pelukis Pelan
                                 listPp.add(p);
                                }
                            }      
                            else if(stageID.equalsIgnoreCase("agihan_tugas3") && ((urusan.equals("PPBB")) || (urusan.equals("PBPTD")) || urusan.equals("PBPTG"))) {
                                 if (p.getPerananUtama().getKod().equals("228")) { //Penolong Pegawai Tanah
	                            listPp.add(p);
	                         }
                            }
                       }
                     else if((kodPerananUtama.equalsIgnoreCase("223")) || (kodPerananUtama.equalsIgnoreCase("222"))){
                     if(stageID.equalsIgnoreCase("terima_keputusan_mmkn")){
                         if (p.getPerananUtama().getKod().equals("227")) { //ppt(k)
                            listPp.add(p);
                         }
                     }else if(stageID.equalsIgnoreCase("arahan_tugas2")){
                         if (p.getPerananUtama().getKod().equals("220")) { //pt
                            listPp.add(p);
                        }
                         
                     }
                   }
                    else if(kodPerananUtama.equalsIgnoreCase("108") && urusan.equals("PPBB")){
                        if(stageID.equalsIgnoreCase("arahan_tugasan4")){
                           if(p.getPerananUtama().getKod().equals("35")){
                               listPp.add(p);
                           }
                       }
                    }         
                   else if(((kodPerananUtama.equalsIgnoreCase("227"))||(kodPerananUtama.equalsIgnoreCase("240")) || kodPerananUtama.equalsIgnoreCase("PPTT") || kodPerananUtama.equalsIgnoreCase("220") || kodPerananUtama.equalsIgnoreCase("108") || kodPerananUtama.equalsIgnoreCase("236")) && ((urusan.equals("PPBB")) || (urusan.equals("PBPTD")) || urusan.equals("PBPTG")))
                   {
                       if(stageID.equalsIgnoreCase("agihan_tugas") || stageID.equalsIgnoreCase("agihan_tugas8") || stageID.equalsIgnoreCase("agihan_tugas5") || stageID.equalsIgnoreCase("agihan_tugas6")) {
                            if(p.getPerananUtama().getKod().equals("226")){ //Pelukis Pelan
                             listPp.add(p);
                            }
                       }
//                       else if((stageID.equalsIgnoreCase("agihan_tugas2")) || (stageID.equalsIgnoreCase("agihan_tugas3"))){
//                            if (p.getPerananUtama().getKod().equals("228")) { //Penolong Pegawai Tanah
//	                    listPp.add(p);
//                            }
//                       }
                        else if(stageID.equalsIgnoreCase("agihan_tugas4") && urusan.equals("PBPTD")){
                           if(p.getPerananUtama().getKod().equals("226")){
                               listPp.add(p);
                           }
                       }
                       else if(stageID.equalsIgnoreCase("agihan_tugas4")){
                           if(p.getPerananUtama().getKod().equals("237")){
                               listPp.add(p);
                           }
                       }
                       else if(stageID.equalsIgnoreCase("arahan_tugasan4")){
                           if(p.getPerananUtama().getKod().equals("237")){
                               listPp.add(p);
                           }
                       }
                       else if(stageID.equalsIgnoreCase("arahan_tugasan6")){
                           if(p.getPerananUtama().getKod().equals("227")){
                               listPp.add(p);
                           }
                       }
                                            
//                   }else if(kodPerananUtama.equalsIgnoreCase("220")){ // pt
//                     if(currentStage.equalsIgnoreCase("2a")){
//                         if (p.getPerananUtama().getKod().equals("228")) { //Penolong Pegawai Tanah
//                            listPp.add(p);
//                         }
//                     }else if(currentStage.equalsIgnoreCase("2b")){
//                        if (p.getPerananUtama().getKod().equals("220")) { //pt
//                            listPp.add(p);
//                        }
//                     }
//                   }
                }
                      else if(kodPerananUtama.equalsIgnoreCase("225") && (urusan.equals("RAYL") || urusan.equals("RAYK"))){
                        if(stageID.equalsIgnoreCase("02MaklumArah")){
                           if(p.getPerananUtama().getKod().equals("220")){
                               listPp.add(p);
                           }
                       }
                    }
                    //Add for carigali and pajakan lombong
                    else if(kodPerananUtama.equalsIgnoreCase("240") && (urusan.equals("LMCRG") || (urusan.equals("PJLB")))){
                        if(stageID.equalsIgnoreCase("agihan_tugas")){
//                           if(p.getPerananUtama().getKod().equals("35")){
//                               listPp.add(p);
//                           }
                            if(p.getPerananUtama().getKod().equals("237")){
                               listPp.add(p);
                           }
                       }
                    }
                            //Add for PJLB - to ptgptlupus from pptg
                     else if(kodPerananUtama.equalsIgnoreCase("108") && (urusan.equals("PJLB"))){
                        if(stageID.equalsIgnoreCase("arahan_tugasan")){
                            if(p.getPerananUtama().getKod().equals("229")){
                               listPp.add(p);
                           }
                       }
                    }
                    
                   
                   }
                }
            }
        }
  
    
    /**
     * 
     * @param lp
     * @param kod
     * @param kodPerananUtama
     * @param currentStage
     * @author Mohd Hairudin Mansur
     * @version 21042011
     */
    private void getListPgunaNS(List<Pengguna> lp, KodCawangan kod, String kodPerananUtama, String currentStage) {
    	for (Pengguna p : lp) {
            if (p.getKodCawangan().getKod().equals(kod.getKod())) {
                if (p.getPerananUtama() != null) {
                    if (kodPerananUtama.equalsIgnoreCase("227")) { // ppt(k)
                        if((stageID.equalsIgnoreCase("arahan_laporan_tanah")) 
                        		|| (stageID.equalsIgnoreCase("arahan_pu")) 
                        		|| (stageID.equalsIgnoreCase("arahan_rekod_lot_index"))
                        		|| (stageID.equalsIgnoreCase("arahan_rekod_lot_index2"))) {
	                        if (p.getPerananUtama().getKod().equals("228")) { // pptanah
	                            listPp.add(p);
	                        }
                        } else if((stageID.equalsIgnoreCase("arah_sedia_draf_ptd"))
                        		|| (stageID.equalsIgnoreCase("arahan_sedia_draf_mmk")) 
                                        || (stageID.equalsIgnoreCase("sedia_draf_ptd"))) {
                        	if (p.getPerananUtama().getKod().equals("220")) { // ptlupus
	                            listPp.add(p);
	                        }
                        } else{
                        //DO: Nothing
                        }
                                               
                    } else if((kodPerananUtama.equalsIgnoreCase("223"))) { // pptd
                	    if((stageID.equalsIgnoreCase("arahan_charting")) 
                        		|| (stageID.equalsIgnoreCase("terima_keputusan_mmk"))
                                        || (stageID.equalsIgnoreCase("02ArahanCharting"))) {
                		    if (p.getPerananUtama().getKod().equals("226")) { // ppelan
                		 	   listPp.add(p);
   	                        }
                        } else if((stageID.equalsIgnoreCase("arahan_sdp"))
                        		|| (stageID.equalsIgnoreCase("arah_tuntut_byrn_tmbhn3"))
                                        ||(stageID.equalsIgnoreCase("sedia_srt_byrn_tmbhn2"))) {
                        	if (p.getPerananUtama().getKod().equals("220")) { // ptlupus
	                            listPp.add(p);
	                        }
                        } else if((stageID.equalsIgnoreCase("arah_tuntut_byrn_tmbhn1"))) {
                        	if (p.getPerananUtama().getKod().equals("222")) { // kptlupus
	                            listPp.add(p);
	                        }
                        } else{
                        //DO: Nothing
                        }
                    } else if((kodPerananUtama.equalsIgnoreCase("222"))) { // kptlupus
                	    if((stageID.equalsIgnoreCase("arah_tuntut_byrn_tmbhn2"))) {
                		    if (p.getPerananUtama().getKod().equals("220")) { // ptlupus
                		 	   listPp.add(p);
   	                        }
                        } else{
                        //DO: Nothing
                        }
                    } else if((kodPerananUtama.equalsIgnoreCase("231"))) { // kptptglupus
                	    if((stageID.equalsIgnoreCase("semak_keputusan_tangguh"))) {
                		    if (p.getPerananUtama().getKod().equals("235")) { // penptlupus
                		 	   listPp.add(p);
   	                        }
                        }
                          if((stageID.equalsIgnoreCase("09TerimaArahTangguh"))) {
                		    if (p.getPerananUtama().getKod().equals("80")) { // pptanahptg1
                		 	   listPp.add(p);
   	                        }
                        }else{
                        //DO: Nothing
                        }
                    }
                }
            }
        }
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

        String msg = "";
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = mohonDao.findById(idMohon);
        String kodUrusan = mohon.getKodUrusan().getKod();
        String kodNegeri = conf.getProperty("kodNegeri");
        
        // get stageID
        try {
            List<Task> l = WorkFlowService.queryTasksByAdmin(idMohon);
            for (Task t : l) {
                stageID = t.getSystemAttributes().getStage();
                logger.info("-----Stage ID:" + stageID + "-----");
            }
        } catch (Exception e) {
            logger.error("error ::" + e.getMessage());
        }
        
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
                pengguna = penggunaDao.findById(pengguna.getIdPengguna());
                syslog.info(pguna.getIdPengguna() + " agih tugasan untuk permohonan :" + idPermohonan + " kepada " + pengguna.getIdPengguna());
                
                Task task = null;
                String stageId = null;
                if(kodNegeri.equalsIgnoreCase("04")) { // melaka
                	if(kodUrusan.equals("PBMT") && stageID.equals("arahan_tugas")) {
                		if(mohon.getKeputusan().getKod().equals("L")) {
                			task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user", mohon.getKeputusan().getKod());
                		}
                		else {
                			task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                		}
                    	stageId = task.getSystemAttributes().getStage();
                    }
                    /*else if(kodUrusan.equals("PLPS") && stageID.equals("g_charting_keputusan")) {
                    	task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user", "L");
                    	stageId = task.getSystemAttributes().getStage();
                    }*/
                    else {
                    	task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                    	stageId = task.getSystemAttributes().getStage();
                    }
                } else if(kodNegeri.equalsIgnoreCase("05")) { // negeri sembilan
                	task = WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
                	stageId = task.getSystemAttributes().getStage();
                }
                
                logger.info("stage from task: " + stageId);
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
                
//                WorkFlowService.reassignTask(ctx, taskID, pengguna.getIdPengguna(), "user");
//                ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
//                WorkFlowService.acquireTask(taskID, ctx);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }

        msg = sb.toString() + " : Agihan tugasan kepada " + pengguna.getIdPengguna() + " telah Berjaya.";
//        return new ForwardResolution("/WEB-INF/jsp/daftar/senarai_tugasan.jsp").addParameter("popup", "true");
        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", msg);
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
}


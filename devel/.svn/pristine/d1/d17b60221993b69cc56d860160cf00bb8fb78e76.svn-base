/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdKumpulanPermohonan;
import etanah.sequence.GeneratorKumpulanAkaun;
import etanah.service.RegService;
import etanah.service.common.PermohonanService;
import etanah.view.BasicTabActionBean;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.UrusanValue;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 *
 * @author wazer
 */
@UrlBinding("/daftar/utilitiBerangkaiActionBean")
public class utilitiBerangkaiActionBean extends AbleActionBean {

    private String idPerserahan = "";
    private String idPerserahan1 = "";
    private String idPerserahan2 = "";
    private String idPerserahan3 = "";
    private String idPerserahan4 = "";
    private String idPerserahan5 = "";
    ArrayList<String> listIdperserahan = new ArrayList<String>();
//    ArrayList<String> listIdkump1 = new ArrayList<String>();
    private String listIdkump1;
    ArrayList<Permohonan> senaraiPermohonan1 = new ArrayList<Permohonan>();
    private List<Permohonan> senaraiPermohonan;
    private List<Permohonan> listPermohonan;
    private List<Permohonan> senaraiIdKumpulan;
//    private List<String> listIdperserahan;
    private String idPermohonan;
    private Permohonan permohonan1;
    private String idKump2;
    private String idPermohonan2;
    private int idKump4;
    private Permohonan perserahan6;
    private String taskId;
    public String link = "";
    private String size = "";
    public List listValue = new ArrayList();
    private boolean red = false;
    private boolean red2 = false;
    private boolean green = false;
//    private String jabatan = null;
    static final long ONE_HOUR = 60 * 60 * 1000L;
    private boolean flag = true;
    private boolean flag2 = true;
    private static final Logger LOG = Logger.getLogger(WithdrawTaskActionBean.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    private TabManager tm;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    BasicTabActionBean bt;
    @Inject
    RegService regService;
    @Inject
    PermohonanService permohonanService;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private static String idKump = null;
    @Inject
    private GeneratorIdKumpulanPermohonan generatorIdKumpulan;
    @Inject
    private etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("daftar/Utiliti_berangkai.jsp");
    }

    @HandlesEvent("search")
    public Resolution search() throws WorkflowException {
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        idPerserahan1 = getContext().getRequest().getParameter("idPerserahan1");
        idPerserahan2 = getContext().getRequest().getParameter("idPerserahan2");
        idPerserahan3 = getContext().getRequest().getParameter("idPerserahan3");
        idPerserahan4 = getContext().getRequest().getParameter("idPerserahan4");
        idPerserahan5 = getContext().getRequest().getParameter("idPerserahan5");



        listIdperserahan.add(idPerserahan);
        listIdperserahan.add(idPerserahan1);
        if (idPerserahan2 != null) {
            if (idPerserahan2 != "") {
                listIdperserahan.add(idPerserahan2);
            }
        }
        if (idPerserahan3 != null) {
            if (idPerserahan3 != "") {
                listIdperserahan.add(idPerserahan3);
            }
        }
        if (idPerserahan4 != null) {
            if (idPerserahan4 != "") {

                listIdperserahan.add(idPerserahan4);
            }
        }
        if (idPerserahan5 != null) {
            if (idPerserahan5 != "") {
                listIdperserahan.add(idPerserahan5);
            }
        }
        for (String idMohon : listIdperserahan) {
            idPermohonan = idMohon;
            if (!idPermohonan.equals(null)) {
//                permohonan1 = permohonanDAO.findById(idPermohonan);
                senaraiPermohonan = permohonanService.findByIdmohon(idPermohonan);
//                senaraiPermohonan1.add(permohonan1);
            }
        }
        for (Permohonan mohon : senaraiPermohonan) {
            if (mohon.getIdKumpulan() != null) {
                red = true;
            }
            if (!mohon.getKodUrusan().getKodPerserahan().getKod().equals("SC")) {
                red2 = true;
            }
            if (!mohon.getKodUrusan().getKodPerserahan().getKod().equals("SC")) {
                if (!mohon.getKodUrusan().getKodPerserahan().getKod().equals("B")) {
                    red2 = true;
                }
            }
        }
        if (red == false || (red2 == false)) {
            green = true;
//            janaIdKumpulan();
            janaNoKumpulan();
            flag = true;
        }
        if (flag == true) {
            janaNoKumpulan();
        }

//        return new JSP("main/java/etanah/viewdaftar/utiliti/Utiliti_berangkai.jsp");
        return showForm();
    }

    private void janaIdKumpulan() {
        int i = 1;
        for (String idMohon : listIdperserahan) {
            idPerserahan = idMohon;
            senaraiPermohonan = permohonanService.findByIdmohon(idPerserahan);
            i = i + 1;
//            senaraiPermohonan.add(permohonan1);
            for (Permohonan mohon : senaraiPermohonan) {
                if (idKump2 == null) {
                    List<Permohonan> senaraiIdKumpulan1 = permohonanService.getPermohonanByIdKumpAll();
                    senaraiIdKumpulan = senaraiIdKumpulan1.subList(Integer.valueOf(0), Integer.valueOf(1));
                }
                LOG.info("id Permohonan = " + mohon);
                idKump2 = senaraiIdKumpulan.toString();
                LOG.info("id kumpulan2 = " + idKump2);
                idKump2 = idKump2.substring(1, 6);
                idKump4 = Integer.parseInt(idKump2);
//                idKump4 = idKump2.compareTo(idKump2);
                idKump4 = idKump4 + 1;
                idKump2 = String.valueOf(idKump4);
                mohon.setIdKumpulan(idKump2);
                mohon.setKumpulanNo(i);
                LOG.info("id kumpulan = " + idKump2);
                LOG.info("no kumpulan = " + i);
                permohonanService.saveOrUpdate(mohon);
                LOG.info("id kumpulan = " + mohon.getIdKumpulan());
            }
        }
    }

    private void janaNoKumpulan() {
        String[] idKumpulan = new String[]{null};
        if (idKumpulan[0] == null) {
//            idKumpulan[0] = idPermohonan;

            etanahActionBeanContext ctx = new etanahActionBeanContext();
            ctx = (etanahActionBeanContext) getContext();

            Pengguna pengguna = ctx.getUser();
            KodCawangan caw = pengguna.getKodCawangan();
            Date now = new Date();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            idKump = generatorIdKumpulan.generate(conf.getProperty("kodNegeri"), pengguna.getKodCawangan(), null);
            LOG.info("Id Kumpulan::" + idKumpulan);
            for (String idMohon : listIdperserahan) {
                Permohonan mohon = permohonanDAO.findById(idMohon);
                mohon.setIdKumpulan(idKump);
                permohonanService.saveOrUpdate(mohon);
            }
        }
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public String getIdPerserahan1() {
        return idPerserahan1;
    }

    public void setIdPerserahan1(String idPerserahan1) {
        this.idPerserahan1 = idPerserahan1;
    }

    public String getIdPerserahan2() {
        return idPerserahan2;
    }

    public void setIdPerserahan2(String idPerserahan2) {
        this.idPerserahan2 = idPerserahan2;
    }

    public String getIdPerserahan3() {
        return idPerserahan3;
    }

    public void setIdPerserahan3(String idPerserahan3) {
        this.idPerserahan3 = idPerserahan3;
    }

    public String getIdPerserahan4() {
        return idPerserahan4;
    }

    public void setIdPerserahan4(String idPerserahan4) {
        this.idPerserahan4 = idPerserahan4;
    }

    public String getIdPerserahan5() {
        return idPerserahan5;
    }

    public void setIdPerserahan5(String idPerserahan5) {
        this.idPerserahan5 = idPerserahan5;
    }

    public ArrayList<String> getListIdperserahan() {
        return listIdperserahan;
    }

    public void setListIdperserahan(ArrayList<String> listIdperserahan) {
        this.listIdperserahan = listIdperserahan;
    }

    public String getListIdkump1() {
        return listIdkump1;
    }

    public void setListIdkump1(String listIdkump1) {
        this.listIdkump1 = listIdkump1;
    }

    public ArrayList<Permohonan> getSenaraiPermohonan1() {
        return senaraiPermohonan1;
    }

    public void setSenaraiPermohonan1(ArrayList<Permohonan> senaraiPermohonan1) {
        this.senaraiPermohonan1 = senaraiPermohonan1;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan1() {
        return permohonan1;
    }

    public void setPermohonan1(Permohonan permohonan1) {
        this.permohonan1 = permohonan1;
    }

    public String getIdKump2() {
        return idKump2;
    }

    public void setIdKump2(String idKump2) {
        this.idKump2 = idKump2;
    }

    public int getIdKump4() {
        return idKump4;
    }

    public void setIdKump4(int idKump4) {
        this.idKump4 = idKump4;
    }

    public Permohonan getPerserahan6() {
        return perserahan6;
    }

    public void setPerserahan6(Permohonan perserahan6) {
        this.perserahan6 = perserahan6;
    }

    public List<Permohonan> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<Permohonan> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }

    public List<Permohonan> getSenaraiIdKumpulan() {
        return senaraiIdKumpulan;
    }

    public void setSenaraiIdKumpulan(List<Permohonan> senaraiIdKumpulan) {
        this.senaraiIdKumpulan = senaraiIdKumpulan;
    }

    public List getListValue() {
        return listValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public boolean isRed2() {
        return red2;
    }

    public void setRed2(boolean red2) {
        this.red2 = red2;
    }

    public boolean isGreen() {
        return green;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }
}

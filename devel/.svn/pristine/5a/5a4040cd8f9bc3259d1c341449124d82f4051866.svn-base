/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.FasaPermohonan;
import java.util.Date;
import etanah.model.Permohonan;
import etanah.service.common.EnforcementService;
import etanah.service.common.HakmilikUrusanService;
import etanah.view.BasicTabActionBean;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 *
 * @author 
 */
@UrlBinding("/penguatkuasaan/senaraiTugasan")
public class SenaraiTugasanPenguatkuasaanActionBean extends AbleActionBean {

    @Inject
    private TabManager tm;
    @Inject
    private PermohonanDAO pDAO;
    @Inject
    BasicTabActionBean bt;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    EnforcementService enforcementService;
    private static final Logger log = Logger.getLogger(SenaraiTugasanPenguatkuasaanActionBean.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    public List listValue = new ArrayList();
    public Date fromDate;
    public Date untilDate;
    public String idInsert = new String();
    public String link = "";
    private String size = "";
    private List taskList;
    private boolean red = false;
    private String jabatan = null;
    static final long ONE_HOUR = 60 * 60 * 1000L;
    private boolean flag = true;
    private boolean flag2 = true;
    private String idPermohonan;
    private List<FasaPermohonan> senaraiFasaPermohonan;
    private List<FasaPermohonan> senaraiFasaPermohonan1;
    private String checked;
    DateFormat formatter;


    @DefaultHandler
    public Resolution showForm() throws Exception {

        //message and error handling from StageActionBean

        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(message)) {
            addSimpleMessage(message);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

//        setDefault();

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_tugasan.jsp");
    }

    private long daysBetween(Calendar ca) {

        GregorianCalendar da1 = new GregorianCalendar();
        long milPerDay = 1000 * 60 * 60 * 24;
        long d1 = da1.getTime().getTime();
        long d2 = ca.getTime().getTime();
        long difMil = d1 - d2;
        long d = ((d2 - d1) / milPerDay);
        if (d < 0) {
            red = Boolean.TRUE;
        } else {
            red = Boolean.FALSE;
        }

        return (difMil / milPerDay);
    }



    public Resolution searchAllPermohonan() throws WorkflowException, ParseException {
        HttpSession ses = getContext().getRequest().getSession();
        Pengguna pengguna = (Pengguna) ses.getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        Permohonan p = pDAO.findById(idPermohonan);
        FasaPermohonan mohonFasa = new FasaPermohonan();
//        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        if (idPermohonan != null) {

            mohonFasa = enforcementService.IdMohon(idPermohonan);

        }
        
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_tugasan.jsp");
    }

     public Resolution searchAllPermohonan2() throws ParseException {

        log.debug("searchAllPermohonan :" + getFromDate());
        System.out.println(" FromDate---------" + fromDate);
        System.out.println(" idInsert---------" + idInsert);
        System.out.println(" UntilDate---------" + untilDate);

        if (idInsert != null && fromDate == null) {
            senaraiFasaPermohonan = enforcementService.getSenaraiFasaPermohonanByIdPermohonan(idInsert);
        }else{
            senaraiFasaPermohonan = enforcementService.getSenaraiFasaPermohonanByIdDate(idInsert, fromDate, untilDate);
        }
//        if (idInsert != null && fromDate != null && untilDate != null) {
//            addSimpleError("Sila masukkan samada ID Mohon ATAU Tarikh Dari");
//        }
        if(checked.equalsIgnoreCase("N")){
            List<String> senaraidMohon = new ArrayList<String>();
            for(FasaPermohonan fp : senaraiFasaPermohonan){
                /*
                 * TO GET HOW MANY IDMOHON IN THE LIST
                 */
                if(senaraidMohon.isEmpty()){
                    senaraidMohon.add(fp.getPermohonan().getIdPermohonan());
                }else{
                    boolean checkIDExist = false;
                    for(String s : senaraidMohon){
                        if(s.equalsIgnoreCase(fp.getPermohonan().getIdPermohonan())){
                            checkIDExist = true;
                        }
                    }
                    if(!checkIDExist){
                        senaraidMohon.add(fp.getPermohonan().getIdPermohonan());
                    }
                }
                /*
                 * END 
                 */
            }
            /*
             * SEPERATE IDMOHON FROM SENARAIFASAPERMOHONAN
             */
            List<FasaPermohonan> senaraiTotalNewFasaPermohonan = new ArrayList<FasaPermohonan>();
            for(String idMohon : senaraidMohon){
                List<FasaPermohonan> senaraiFasaPermohonanSpecific = new ArrayList<FasaPermohonan>();
                for(FasaPermohonan fp : senaraiFasaPermohonan){
                    if(fp.getPermohonan().getIdPermohonan().equalsIgnoreCase(idMohon)){
                        senaraiFasaPermohonanSpecific.add(fp);
                    }
                }
                senaraiTotalNewFasaPermohonan.add(senaraiFasaPermohonanSpecific.get(senaraiFasaPermohonanSpecific.size()-1));
            }
            senaraiFasaPermohonan = new ArrayList<FasaPermohonan>();
            for(FasaPermohonan fp : senaraiTotalNewFasaPermohonan){
                senaraiFasaPermohonan.add(fp);
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_tugasan.jsp");
    }



    public List getListValue() {
        return listValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }

    public String getIdInsert() {
        return idInsert;
    }

    public void setIdInsert(String idInsert) {
        this.idInsert = idInsert;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan1() {
        return senaraiFasaPermohonan1;
    }

    public void setSenaraiFasaPermohonan1(List<FasaPermohonan> senaraiFasaPermohonan1) {
        this.senaraiFasaPermohonan1 = senaraiFasaPermohonan1;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }


  }

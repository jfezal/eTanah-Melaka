/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangQDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.BorangQ;
import etanah.service.common.PendudukanSementaraService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;

@UrlBinding("/pengambilan/pulihTanahSemakBorangQ")
public class PemulihanTanahSemakanBorangQActionBean extends AbleActionBean
{
    private static Logger logger = Logger.getLogger(PemulihanTanahSemakanBorangQActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraService pendudukanSementaraService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    BorangQDAO  borangQDAO;

    private KodCawangan cawangan;
    private Permohonan permohonan;
    private String idPermohonan;
    private Permohonan p;
    private BorangQ borangQ;
    private BigDecimal pampasan = new BigDecimal(0.00);
    private Integer tempoh;
    private Date tarikhMula;
    private String tarikhHadir;
    private String maksud;
//    private char waktuHadir;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private String jam;
    private String minit;
    private String pagiPetang;
    private String idSblm;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution semakanBorangQ() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);

        if(p.getPermohonanSebelum() != null)
            idSblm = p.getPermohonanSebelum().getIdPermohonan();
        if(idSblm != null){
            getContext().getRequest().setAttribute("form", Boolean.TRUE);
        }else{
            addSimpleError("Haraf maaf sila masukkan id Permohonan Terdahulu");
            getContext().getRequest().setAttribute("form", Boolean.FALSE);
        }
        return new JSP("pengambilan/semakanBorangQ.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = "";
        if(permohonan.getPermohonanSebelum() != null){
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        if (idSblm != null) {
             Permohonan p= permohonanDAO.findById(idSblm);
             if(p != null){
            logger.debug("idSblm :" + idSblm);
            borangQ = pendudukanSementaraService.findByIdPermohonan4BorangQ(idSblm);
            logger.debug(idSblm + " ni id terdahulu");
            if(borangQ != null) {
                tempoh = borangQ.getTempoh();
                tarikhMula= borangQ.getTarikhMulaMendudukiTanah();
                maksud = borangQ.getMaksud();
                pampasan = borangQ.getTawaranPampasan();

                if(borangQ.getTarikhHadir() != null) {
                    String tarikhHadirObj = dateFormat.format(borangQ.getTarikhHadir());
                    tarikhHadir = tarikhHadirObj.substring(0, 10);
                    jam = tarikhHadirObj.substring(11, 13);
                    minit = tarikhHadirObj.substring(14, 16);
                    String ampm = tarikhHadirObj.substring(20, 22);
                    if(ampm.equalsIgnoreCase("AM")){
                        pagiPetang = "AM";
                    }else if(ampm.equalsIgnoreCase("PM")){
                        pagiPetang = "PM";
                            }
                        }

                    }

                }
            }
        }
    }

    public BorangQ getBorangQ() {
        return borangQ;
    }

    public void setBorangQ(BorangQ borangQ) {
        this.borangQ = borangQ;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getMaksud() {
        return maksud;
    }

    public void setMaksud(String maksud) {
        this.maksud = maksud;
    }

    public BigDecimal getPampasan() {
        return pampasan;
    }

    public void setPampasan(BigDecimal pampasan) {
        this.pampasan = pampasan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhHadir() {
        return tarikhHadir;
    }

    public void setTarikhHadir(String tarikhHadir) {
        this.tarikhHadir = tarikhHadir;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public PendudukanSementaraService getPendudukanSementaraService() {
        return pendudukanSementaraService;
    }

    public void setPendudukanSementaraService(PendudukanSementaraService pendudukanSementaraService) {
        this.pendudukanSementaraService = pendudukanSementaraService;
    }
}

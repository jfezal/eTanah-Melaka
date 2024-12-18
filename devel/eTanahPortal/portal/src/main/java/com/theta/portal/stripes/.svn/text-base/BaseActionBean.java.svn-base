package com.theta.portal.stripes;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import com.theta.portal.dao.HelpdeskReportDAO;
import com.theta.portal.dao.HelpdeskReportLogDAO;
import com.theta.portal.dao.RefHelpdeskStageDAO;
import com.theta.portal.model.HelpdeskReport;
import com.theta.portal.model.HelpdeskReportLog;
import com.theta.portal.model.RefHelpdeskStage;
import com.theta.portal.model.UserTable;
import com.theta.portal.service.ReportLogService;
import com.theta.portal.stripes.config.ableActionBeanContext;
import com.theta.portal.stripes.form.aduan.AduanForm;
import com.theta.portal.stripes.form.aduan.AgihanUserForm;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.util.Base64;
import net.sourceforge.stripes.validation.SimpleError;

public abstract class BaseActionBean extends AbleActionBean {

    //Default paging implementation - use in all pages
    Integer __pg_max_records = 10;
    Integer __pg_start = 0;
    Integer __pg_total_records = 0;
    List<AgihanUserForm> listAgihanKontraktor;
    List<AduanForm> listPulangSemula;
    Long reportId;

    Boolean user;
    Boolean userSelesai;
    Boolean vendor;
    Boolean pTeknikal;
    Boolean pTeknikalSemak;
    Boolean agih;
    Boolean pulangsemula;
    String noteKontraktor;
    String noteTeknikal;
    String notaAgih;
    String notaUser;

    @Inject
    ReportLogService reportLogService;

    public Resolution pulangSemulaTeknikal() {
        UserTable userId = getContext().getCurrentUser();
        reportLogService.saveRecord(reportId, noteTeknikal, userId, "PST");
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    public Resolution pulangSemulaKontraktor() {
        UserTable userId = getContext().getCurrentUser();
        reportLogService.saveRecord(reportId, noteKontraktor, userId, "PSC");
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }
    public Resolution pulangSemulaKontraktorSemak() {
        UserTable userId = getContext().getCurrentUser();
        reportLogService.saveRecord(reportId, noteKontraktor, userId, "PSK");
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }
     public Resolution pulangSemulaAgih() {
        UserTable userId = getContext().getCurrentUser();
        reportLogService.saveRecord(reportId, notaAgih, userId, "PSA");
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }
      public Resolution pulangSemulaUser() {
        UserTable userId = getContext().getCurrentUser();
        reportLogService.saveRecord(reportId, notaUser, userId, "PSU");
        return new RedirectResolution(HelpdeskMainActionBean.class);
    }

    @Override
    public ableActionBeanContext getContext() {
        return (ableActionBeanContext) super.getContext();
    }

    public String getUserId() {
        if (getContext() != null) {
            if (getContext().getCurrentUser() != null) {
                return getContext().getCurrentUser().getUserName();
            }
        }
        return "ADMIN";
    }

    public String getCounter() {
        if (getContext() != null) {
            if (!"".equals(getContext().getCounter()) && getContext().getCounter() != null) {
                return getContext().getCounter();
            }
        }
        return "";
    }

    public synchronized String encrypt(String plainText) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            String msg = "SHA not found, encryption cannot continue, no recovery possible";
            throw new RuntimeException(msg, e);
        }

        try {
            md.update(plainText.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            String msg = "UTF-8 encoding not found, no recovery possible";
            throw new RuntimeException(msg, e);
        }

        byte[] raw = md.digest();

        //String pwd = new BASE64Encoder().encode(raw);
        String pwd = Base64.encodeBytes(raw);
        return pwd.length() > 50 ? pwd.substring(0, 50) : pwd;
    }

    public String generatePasswordAuto() {
        String dCase = "abcdefghijklmnopqrstuvwxyz";
        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String sChar = "!@#$^*";
        String intChar = "23456789";
        Random r = new Random();
        String pass = "";

        while (pass.length() != 10) {
            int rPick = r.nextInt(3);
            if (rPick == 0) {
                int spot = r.nextInt(25);
                pass += dCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt(25);
                pass += uCase.charAt(spot);
            } //            else if (rPick == 2) {
            //                int spot = r.nextInt (5);
            //                pass += sChar.charAt(spot);
            //            } 
            else if (rPick == 2) {
                int spot = r.nextInt(7);
                pass += intChar.charAt(spot);
            }
        }

        return pass;

    }

    public Boolean getLoggedIn() {
        return getContext().isLoggedIn();
    }

    public Integer get__pg_max_records() {
        return __pg_max_records;
    }

    public void set__pg_max_records(Integer __pg_max_records) {
        this.__pg_max_records = __pg_max_records;
    }

    public Integer get__pg_start() {
        return __pg_start;
    }

    public void set__pg_start(Integer __pg_start) {
        this.__pg_start = __pg_start;
    }

    public Integer get__pg_total_records() {
        return __pg_total_records;
    }

    public void set__pg_total_records(Integer __pg_total_records) {
        this.__pg_total_records = __pg_total_records;
    }

    protected void addSimpleError(String msg) {
        getContext().getValidationErrors().addGlobalError(
                new SimpleError(msg));
    }

    protected void addSimpleMessage(String msg) {
        getContext().getMessages().add(new SimpleMessage(msg));
    }

    public List<AgihanUserForm> getListAgihanKontraktor() {
        return listAgihanKontraktor;
    }

    public void setListAgihanKontraktor(List<AgihanUserForm> listAgihanKontraktor) {
        this.listAgihanKontraktor = listAgihanKontraktor;
    }

    public Boolean getGroup() {
        Boolean t = false;
        UserTable user = getContext().getCurrentUser();

        return t;
    }

    public Boolean getUser() {
        return user;
    }

    public void setUser(Boolean user) {
        this.user = user;
    }

    public Boolean getVendor() {
        return vendor;
    }

    public void setVendor(Boolean vendor) {
        this.vendor = vendor;
    }

    public Boolean getpTeknikal() {
        return pTeknikal;
    }

    public void setpTeknikal(Boolean pTeknikal) {
        this.pTeknikal = pTeknikal;
    }

    public Boolean getAgih() {
        return agih;
    }

    public void setAgih(Boolean agih) {
        this.agih = agih;
    }

    public Boolean getPulangsemula() {
        return pulangsemula;
    }

    public void setPulangsemula(Boolean pulangsemula) {
        this.pulangsemula = pulangsemula;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getNoteKontraktor() {
        return noteKontraktor;
    }

    public void setNoteKontraktor(String noteKontraktor) {
        this.noteKontraktor = noteKontraktor;
    }

    public String getNoteTeknikal() {
        return noteTeknikal;
    }

    public void setNoteTeknikal(String noteTeknikal) {
        this.noteTeknikal = noteTeknikal;
    }

    public String getNotaAgih() {
        return notaAgih;
    }

    public void setNotaAgih(String notaAgih) {
        this.notaAgih = notaAgih;
    }

    public String getNotaUser() {
        return notaUser;
    }

    public void setNotaUser(String notaUser) {
        this.notaUser = notaUser;
    }

    public Boolean getUserSelesai() {
        return userSelesai;
    }

    public void setUserSelesai(Boolean userSelesai) {
        this.userSelesai = userSelesai;
    }

    public Boolean getpTeknikalSemak() {
        return pTeknikalSemak;
    }

    public void setpTeknikalSemak(Boolean pTeknikalSemak) {
        this.pTeknikalSemak = pTeknikalSemak;
    }

    public List<AduanForm> getListPulangSemula() {
        return listPulangSemula;
    }

    public void setListPulangSemula(List<AduanForm> listPulangSemula) {
        this.listPulangSemula = listPulangSemula;
    }
    
    

}

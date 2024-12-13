/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.rayuan;


import able.stripes.AbleActionBean;
import com.google.inject.Inject;
//import etanah.Configuration;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
//import etanah.model.PenggunaPeranan;
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
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
//import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.stripes.SenaraiTugasanActionBean;
//import etanah.Configuration;
import etanah.model.InfoAudit;
import etanah.service.BPelService;
import etanah.view.utility.JupemInIntegration;
import java.io.IOException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.common.FasaPermohonanService;
import etanah.model.FasaPermohonan;
import etanah.service.PembangunanService;
import java.util.Date;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.service.common.HakmilikPermohonanService;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.log4j.Logger;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/rayuan/maklumatPermohonan2")
public class MaklumatPermohonanActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(MaklumatPermohonanActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    private HakmilikPermohonan hakHakmilikPermohonan;
    private List<HakmilikPermohonan> listhakHakmilikPermohonan1;
    private String idMohonSebelum;
    
    private String idMHSblm;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/rayuan/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    //@HandlesEvent("search")  
    public Resolution search() {
//        String idMHSblm = getContext().getRequest().getParameter("ID_MH_SBLM");

        LOGGER.info("idMHSblm>>>>>>>" + idMHSblm);

        if (idMHSblm != null) {
            hakHakmilikPermohonan = hakmilikPermohonanService.findIdhakmilikbyIdPermohonanENF(idMHSblm);
            idMohonSebelum = hakHakmilikPermohonan.getPermohonan().getIdPermohonan();
//          listhakHakmilikPermohonan1 = new ArrayList<HakmilikPermohonan>();
//          listhakHakmilikPermohonan1.add(hakHakmilikPermohonan);

            LOGGER.info("idMohonSebelum>>>>>>>" + idMohonSebelum);
        }
        return showForm();
//        return new JSP("pembangunan/rayuan/maklumat_permohonan.jsp").addParameter("tab");
//        return new RedirectResolution(MaklumatPermohonanActionBean.class, "showForm").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakHakmilikPermohonan() {
        return hakHakmilikPermohonan;
    }

    public void setHakHakmilikPermohonan(HakmilikPermohonan hakHakmilikPermohonan) {
        this.hakHakmilikPermohonan = hakHakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getListhakHakmilikPermohonan1() {
        return listhakHakmilikPermohonan1;
    }

    public void setListhakHakmilikPermohonan1(List<HakmilikPermohonan> listhakHakmilikPermohonan1) {
        this.listhakHakmilikPermohonan1 = listhakHakmilikPermohonan1;
    }

    public String getIdMohonSebelum() {
        return idMohonSebelum;
    }

    public void setIdMohonSebelum(String idMohonSebelum) {
        this.idMohonSebelum = idMohonSebelum;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        MaklumatPermohonanActionBean.isDebug = isDebug;
    }

    public HakmilikPermohonanService getHakmilikPermohonanService() {
        return hakmilikPermohonanService;
    }

    public void setHakmilikPermohonanService(HakmilikPermohonanService hakmilikPermohonanService) {
        this.hakmilikPermohonanService = hakmilikPermohonanService;
    }

    public String getIdMHSblm() {
        return idMHSblm;
    }

    public void setIdMHSblm(String idMHSblm) {
        this.idMHSblm = idMHSblm;
    }
    

}

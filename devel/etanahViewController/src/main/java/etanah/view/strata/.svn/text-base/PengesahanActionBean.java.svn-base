/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.JUBLDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanJUBL;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.view.BasicTabActionBean;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/pengesahan")
public class PengesahanActionBean extends BasicTabActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanJUBLDAO permohonanJUBLDAO;
    @Inject
    JUBLDAO jublDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    private PermohonanJUBL mohonJUBL;
    private BadanPengurusan badanUrus;
    private KodCawangan kodCawangan;
    private Hakmilik hakmilik;
    HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
     private static final Logger logger = Logger.getLogger(PengesahanActionBean.class);
    @DefaultHandler
    public Resolution showForm() {
        logger.info("idHakmilik : "+mohonHakmilik.getHakmilik().getIdHakmilik());
        hakmilik = hakmilikDAO.findById(mohonHakmilik.getHakmilik().getIdHakmilik());
        badanUrus = badanPengurusanDAO.findById(hakmilik.getBadanPengurusan().getIdBadan());
        return new JSP("strata/pecahPetak/pengesahanPHPC.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonHakmilik =strService.findMohonHakmilik(idPermohonan);
        kodCawangan = peng.getKodCawangan();
        badanUrus = strService.findbyId(idPermohonan);
    }

    public Resolution simpanMaklumat(){
        addSimpleMessage("Maklumat berjaya disimpan");
    return new ForwardResolution(PengesahanActionBean.class,"showForm").addParameter("tab", "true");
    }

    public JUBLDAO getJublDAO() {
        return jublDAO;
    }

    public void setJublDAO(JUBLDAO jublDAO) {
        this.jublDAO = jublDAO;
    }

    public PermohonanJUBL getMohonJUBL() {
        return mohonJUBL;
    }

    public void setMohonJUBL(PermohonanJUBL mohonJUBL) {
        this.mohonJUBL = mohonJUBL;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanJUBLDAO getPermohonanJUBLDAO() {
        return permohonanJUBLDAO;
    }

    public void setPermohonanJUBLDAO(PermohonanJUBLDAO permohonanJUBLDAO) {
        this.permohonanJUBLDAO = permohonanJUBLDAO;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public BadanPengurusan getBadanUrus() {
        return badanUrus;
    }

    public void setBadanUrus(BadanPengurusan badanUrus) {
        this.badanUrus = badanUrus;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }


    
}

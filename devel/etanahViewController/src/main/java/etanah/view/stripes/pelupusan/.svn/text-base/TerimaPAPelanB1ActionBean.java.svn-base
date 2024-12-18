/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.PermohonanUkur;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Orogenic Group
 */
@UrlBinding("/pelupusan/terimaPaPelanB1")
public class TerimaPAPelanB1ActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    private static final Logger LOG = Logger.getLogger(TerimaPAPelanB1ActionBean.class);
    private Pengguna pguna;
    private PermohonanUkur permohonanUkur;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private NoPt noPt;
    private String noPU;
    private String luas;
    private String luasDiluluskan;
    private String stageId;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/terima_pa_pelanb1.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("Start rehydrate");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
        permohonanUkur = pelupusanService.findPermohonanUkurByIdPermohonan(idPermohonan);
        noPU = permohonanUkur.getNoPermohonanUkur();
        luas = hakmilikPermohonan.getLuasTerlibat().toString();
//        luasDiluluskan = hakmilikPermohonan.getLuasDiluluskan().toString();
        LOG.info("idPermohonan :" + idPermohonan + " noPt :" + noPt + " noPU :" + noPU + " luas :" + luas + " luasDiluluskan :" + luasDiluluskan);

    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getLuasDiluluskan() {
        return luasDiluluskan;
    }

    public void setLuasDiluluskan(String luasDiluluskan) {
        this.luasDiluluskan = luasDiluluskan;
    }

    public String getNoPU() {
        return noPU;
    }

    public void setNoPU(String noPU) {
        this.noPU = noPU;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    
    
}

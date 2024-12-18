/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/maklumat_lain")
public class MaklumatLainActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(MaklumatLainActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private String LainSebab;
    private String display;
    private boolean flag = false;
    private BigDecimal nilai = new BigDecimal(0.00);

    @Inject
    RemisyenManager manager;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/maklumat_lain.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm1() {
//        display = "display:none;";
        flag = true;
        return new JSP("hasil/maklumat_lain.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("hasil/maklumat_lain.jsp").addParameter("tab", "true");
    }
	
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan.getSebab() != null){
                LOG.debug("permohonan.getSebab() ="+permohonan.getSebab());
                LOG.debug("permohonan.getSebab().substring(0, 3) ="+permohonan.getSebab().substring(0, 3));
                if(permohonan.getSebab().substring(0, 3).equals("LLS")){
//                    this.setLainSebab(permohonan.getSebab().substring(3));
                    if(LainSebab == null){
                        LainSebab = permohonan.getSebab().substring(3,permohonan.getSebab().length());
                        LOG.info("LainSebab :"+LainSebab);
                        setLainSebab(permohonan.getSebab().substring(3,permohonan.getSebab().length()));
                        LOG.info("getLainSebab :"+getLainSebab());
                        permohonan.setSebab("LLS");
                    }                    
                }
            }
//            else{
//                permohonan.setSebab("TMB");
//                LOG.debug("permohonan.getSebab() = null :: set default value is 'TMB'");
//            }
        }
        LOG.info("rehydrate:finish");
    }
    
    public Resolution saveOrUpdate(){
        LOG.info("saveOrUpdate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if(permohonan.getSebab().equals("LLS")){
            permohonan.setSebab(permohonan.getSebab()+getLainSebab());
        }
//        permohonan.setNilaiKeputusan(nilai.divide(new BigDecimal(100)));

        manager.save(permohonan, peng);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        LOG.info("Maklumat Telah Berjaya Disimpan..");
        LOG.info("saveOrUpdate:finish");
        return new RedirectResolution(MaklumatLainActionBean.class, "showForm");
    }

//    //not sure by fikri
//    @ValidationMethod(on = "saveOrUpdate")
//    public void validateRadio(ValidationErrors errors) {
//        if (permohonan.getSebab().equals("LLS") && (this.LainSebab == null))
//            errors.add(" ", new SimpleError("Sila Nyatakan Lain-Lain Sebab"));
//    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getLainSebab() {
        return LainSebab;
    }

    public void setLainSebab(String LainSebab) {
        this.LainSebab = LainSebab;
    }
	
	public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }	

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PeguamDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPeguamDAO;
import etanah.model.Peguam;
import etanah.model.Permohonan;
import etanah.model.PermohonanPeguam;
import etanah.service.common.EnkuiriService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.common.LelongService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/maklumat_permohonan")
public class MaklumatPermohonanLActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(MaklumatPermohonanLActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService ES;
    @Inject
    private PeguamDAO peguamDAO;
    @Inject
    private PermohonanPeguamDAO mohonpeguamDAO;
    private Permohonan permohonan;
    private boolean displayBtn = false;
    private boolean displayle = false;
    private boolean boton = false;
    private String idPermohonan;
    private PermohonanPeguam permohonanPeguam;
    private Peguam peguam;
    private List<PermohonanPeguam> peguamList;
    private String idPenyerah;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/maklumat_permohonanL.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        boton = true;
        displayBtn = true;
        return new JSP("lelong/maklumat_permohonanL.jsp").addParameter("tab", "true");
    }

    public Resolution showFormA() {
        displayBtn = true;
        return new JSP("lelong/maklumat_permohonanL.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            logger.info("----id Permohonan--- : " + idPermohonan);
            peguamList = ES.listPeguamY(permohonan.getIdPermohonan());
            logger.info("peguamLIsttttt : " + peguamList.size());
        }
    }

    public Resolution simpanPermohonan() {
        lelongService.saveOrUpdate(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/maklumat_permohonanL.jsp").addParameter("tab", "true");
    }

    public boolean isDisplayle() {
        return displayle;
    }

    public void setDisplayle(boolean displayle) {
        this.displayle = displayle;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isDisplayBtn() {
        return displayBtn;
    }

    public void setDisplayBtn(boolean displayBtn) {
        this.displayBtn = displayBtn;
    }

    public boolean isBoton() {
        return boton;
    }

    public void setBoton(boolean boton) {
        this.boton = boton;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }

    public List<PermohonanPeguam> getPeguamList() {
        return peguamList;
    }

    public void setPeguamList(List<PermohonanPeguam> peguamList) {
        this.peguamList = peguamList;
    }

    public PermohonanPeguam getPermohonanPeguam() {
        return permohonanPeguam;
    }

    public void setPermohonanPeguam(PermohonanPeguam permohonanPeguam) {
        this.permohonanPeguam = permohonanPeguam;
    }
}

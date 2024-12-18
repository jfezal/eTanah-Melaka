package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author ${user}
 */
@UrlBinding("/strata/RBHSMaklumatPemohon")
public class RBHSMaklumatPemohonActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    Permohonan permohonan = new Permohonan();
    PermohonanStrata pemilik = new PermohonanStrata();
    private String idPermohonan = new String();
    private String idPermohonanTerdahulu = new String();
    private Pemohon pemohon = new Pemohon();
    private static final Logger LOG = Logger.getLogger(RBHSMaklumatPemohonActionBean.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        this.permohonan = permohonanDAO.findById(idPermohonan);
        if (this.permohonan.getPermohonanSebelum() != null) {
            this.permohonan = permohonanDAO.findById(this.permohonan.getPermohonanSebelum().getIdPermohonan());
            pemilik = strService.findID(this.permohonan.getIdPermohonan());
            LOG.info("Permohonan : " + this.permohonan.getIdPermohonan());
        }
    }

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        this.permohonan = permohonanDAO.findById(idPermohonan);
        if (this.permohonan.getPermohonanSebelum() != null) {
            this.permohonan = permohonanDAO.findById(this.permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("Permohonan : " + this.permohonan.getIdPermohonan());
            pemilik = strService.findID(this.permohonan.getIdPermohonan());
            pemohon = strService.findById(this.permohonan.getIdPermohonan());
        }
        getContext().getRequest().getSession().setAttribute("readOnly", true);
        getContext().getRequest().getSession().setAttribute("idPermohonanTerdahulu", this.permohonan.getIdPermohonan());
        return new JSP("strata/LanjutanTempohBayar/RBHSMaklumatPemohon.jsp").addParameter("tab", "true");
    }

    public Resolution terus() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        this.permohonan = permohonanDAO.findById(idPermohonan);
        Permohonan permohonanS = new Permohonan();
        permohonanS = permohonanDAO.findById(idPermohonanTerdahulu);
        this.permohonan.setPermohonanSebelum(permohonanS);
        strService.savePermohonan(this.permohonan);
        pemohon = strService.findById(this.permohonan.getPermohonanSebelum().getIdPermohonan());


        getContext().getRequest().getSession().setAttribute("readOnly", true);
        getContext().getRequest().getSession().setAttribute("idPermohonanTerdahulu", idPermohonanTerdahulu);
        return new JSP("strata/LanjutanTempohBayar/RBHSMaklumatPemohon.jsp").addParameter("tab", "true");
    }

    public Resolution readOnly() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        this.permohonan = permohonanDAO.findById(idPermohonan);
        if (this.permohonan.getPermohonanSebelum() != null) {
            this.permohonan = permohonanDAO.findById(this.permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("Permohonan : " + this.permohonan.getIdPermohonan());
            pemilik = strService.findID(this.permohonan.getIdPermohonan());
            pemohon = strService.findById(this.permohonan.getIdPermohonan());
        }
        getContext().getRequest().getSession().setAttribute("idPermohonanTerdahulu", this.permohonan.getIdPermohonan());
        getContext().getRequest().getSession().setAttribute("readOnly", false);
        return new JSP("strata/LanjutanTempohBayar/RBHSMaklumatPemohon.jsp").addParameter("tab", "true");

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public PermohonanStrata getPemilik() {
        return pemilik;
    }

    public void setPemilik(PermohonanStrata pemilik) {
        this.pemilik = pemilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}

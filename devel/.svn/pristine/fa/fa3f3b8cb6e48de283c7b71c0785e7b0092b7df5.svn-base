/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanSiasatanDAO;
import etanah.model.AduanSiasatan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;

import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_siasatan")
public class AduanSiasatanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AduanSiasatanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanSiasatanDAO aduanSiasatanDAO;
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private AduanSiasatan aduanSiasatan;
    private KodCawangan cawangan;
    private String idSiasatan;
    private String tarikhSiasat;
    private List<AduanSiasatan> senaraiAduanSiasatan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_lawatan_tapak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_lawatan_tapak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idSiasatan = getContext().getRequest().getParameter("idSiasatan");
        if (idPermohonan != null) {
            logger.debug("idPermohonan :" + idPermohonan);
            aduanSiasatan = enforceService.findByIDPermohonan(idPermohonan);
            if (aduanSiasatan != null) {
                if (aduanSiasatan.getTarikhSiasat() != null) {
                    tarikhSiasat = sdf.format(aduanSiasatan.getTarikhSiasat()).substring(0, 10);
                }
            }
        }
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        if (aduanSiasatan == null) {
            aduanSiasatan = new AduanSiasatan();
        }
        if (getContext().getRequest().getParameter("tarikhSiasat").isEmpty()) {
            aduanSiasatan.setTarikhSiasat(null);
        } else {
            tarikhSiasat = getContext().getRequest().getParameter("tarikhSiasat");
            aduanSiasatan.setTarikhSiasat(sdf.parse(tarikhSiasat));
        }
        aduanSiasatan.setPermohonan(permohonan);
        aduanSiasatan.setCawangan(cawangan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = aduanSiasatan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanSiasatan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforceService.simpanAduanSiasatan(aduanSiasatan);
        logger.debug("tess1 :" + aduanSiasatan.getIdSiasatan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanSiasatan.getIdSiasatan());
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_lawatan_tapak.jsp").addParameter("tab", "true");

    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhSiasat() {
        return tarikhSiasat;
    }

    public void setTarikhSiasat(String tarikhSiasat) {
        this.tarikhSiasat = tarikhSiasat;
    }

    public AduanSiasatan getAduanSiasatan() {
        return aduanSiasatan;
    }

    public void setAduanSiasatan(AduanSiasatan aduanSiasatan) {
        this.aduanSiasatan = aduanSiasatan;
    }

    public AduanSiasatanDAO getAduanSiasatanDAO() {
        return aduanSiasatanDAO;
    }

    public void setAduanSiasatanDAO(AduanSiasatanDAO aduanSiasatanDAO) {
        this.aduanSiasatanDAO = aduanSiasatanDAO;
    }

    public EnforceService getEnforceService() {
        return enforceService;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public List<AduanSiasatan> getSenaraiAduanSiasatan() {
        return senaraiAduanSiasatan;
    }

    public void setSenaraiAduanSiasatan(List<AduanSiasatan> senaraiAduanSiasatan) {
        this.senaraiAduanSiasatan = senaraiAduanSiasatan;
    }
}

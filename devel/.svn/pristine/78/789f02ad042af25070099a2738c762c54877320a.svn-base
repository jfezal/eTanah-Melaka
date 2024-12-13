/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BangunanPetakDAO;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanTingkat;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/urusan_pkkr")
public class MaintainBngnKosRendahActionBean extends BasicTabActionBean {

    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    BangunanPetakDAO bangunanPetakDAO;
    @Inject
    StrataPtService strService;
    private List<PermohonanBangunan> pBangunanL;
    private PermohonanBangunan bangunan;
    private BangunanTingkat bangunanTingkat;
    private Permohonan permohonan;
    private String idPermohonan;
    private String namaBangunan;
    private String tingkat;
    private String idTingkat;
    private String idBangunan;
    private static final Logger LOG = Logger.getLogger(MaintainBngnKosRendahActionBean.class);

   

    public BangunanTingkat getBangunanTingkat() {
        return bangunanTingkat;
    }

    public void setBangunanTingkat(BangunanTingkat bangunanTingkat) {
        this.bangunanTingkat = bangunanTingkat;
    }

    public String getIdBangunan() {
        return idBangunan;
    }

    public void setIdBangunan(String idBangunan) {
        this.idBangunan = idBangunan;
    }

    public String getIdTingkat() {
        return idTingkat;
    }

    public void setIdTingkat(String idTingkat) {
        this.idTingkat = idTingkat;
    }

    public String getNamaBangunan() {
        return namaBangunan;
    }

    public void setNamaBangunan(String namaBangunan) {
        this.namaBangunan = namaBangunan;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public PermohonanBangunan getBangunan() {
        return bangunan;
    }

    public void setBangunan(PermohonanBangunan bangunan) {
        this.bangunan = bangunan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        return new JSP("strata/kos_rendah/maklumat_jadualpetak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        namaBangunan = getContext().getRequest().getParameter("namaBangunan");
        tingkat = getContext().getRequest().getParameter("tingkat");
        LOG.debug("Masuk");
        LOG.debug(namaBangunan + tingkat);
        return new JSP("strata/kos_rendah/tambah_maklumatpetak.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pBangunanL = strService.findByIDPermohonan(idPermohonan);


        }
    }

    public Resolution tambahBangunan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        String error = "";
        String msg = "";


        String nama = bangunan.getNama();


        if (!nama.isEmpty()) {
            PermohonanBangunan pb = strService.findByNama(nama, idPermohonan);
            if ((pb != null)) {
                error = "Sila Masukkan Nama Bangunan yang berlainan.";
            } else {
                bangunan.setPermohonan(permohonan);
                bangunan.setInfoAudit(infoAudit);
                bangunan.setCawangan(peng.getKodCawangan());
                strService.simpanBangunan(bangunan);
                LOG.debug(bangunan.getNama());
                LOG.debug(bangunan.getIdBangunan());
                msg = "Maklumat telah berjaya disimpan.";
            }
        }

        return new RedirectResolution(MaintainBngnKosRendahActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution deleteBngn() {

        idBangunan = getContext().getRequest().getParameter("idBangunan");

        if (idBangunan != null) {

            PermohonanBangunan bgnn = permohonanBangunanDAO.findById(Long.parseLong(idBangunan));
            if (bgnn != null) {
                strService.deleteBngn(bgnn);
            }
        }
        rehydrate();
        return new RedirectResolution(MaintainBngnKosRendahActionBean.class, "showForm");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/kodSekatanDetails")
public class KodSekatanDetailsActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(KodSekatanDetailsActionBean.class);
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    PembangunanService devService;

    private Pengguna pengguna;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kodSekatanDetails.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------rehydrate------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
     }

    public Resolution simpan(){
        InfoAudit infoAudit = new InfoAudit();
        hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        infoAudit = hp.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        hp.setInfoAudit(infoAudit);
        String sekatanBaru1 = getContext().getRequest().getParameter("sekatanBaru1");
        LOG.info("-----sekatanBaru----:" + sekatanBaru1);
        if (sekatanBaru1 != null && !sekatanBaru1.equals("")) {
            LOG.info("--if---sekatanBaru----:" + sekatanBaru1);
            hp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(sekatanBaru1));
        } else {
            LOG.info("--else---sekatanBaru----:" + sekatanBaru1);
            hp.setSekatanKepentinganBaru(null);
        }
        devService.simpanHakmilikPermohonan(hp);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
       return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kodSekatanDetails.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
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



}

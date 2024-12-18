/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.*;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author siti.mudmainnah
 */
@UrlBinding("/strata/KemasukanSijilMC")
public class KemasukanSijilMC extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KemasukanSijilMC.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Pengguna pengguna;
    private BadanPengurusan bdnUrus;
    private String idHakmilik;
    @Inject
    StrataPtService strataPtService;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

    }

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("addNew", Boolean.FALSE);
        bdnUrus = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSijilMC.jsp");
    }

    public Resolution cari() {
        LOG.info(idHakmilik);
        Hakmilik hm = strataPtService.findIdBdn(idHakmilik);
        if (hm != null) {
            bdnUrus = strataPtService.getidPihakByIdBdn(hm.getBadanPengurusan().getIdBadan());
            if (bdnUrus != null) {
                if (bdnUrus.getPengurusanNoSijil() == null) {
                    bdnUrus = new BadanPengurusan();
                    getContext().getRequest().setAttribute("addNew", Boolean.TRUE);
                    addSimpleError("Sila masukkan maklumat Sijil MC");
                } else {
                    addSimpleMessage("No siri Sijil MC : "+ bdnUrus.getPengurusanNoSijil()+ " telah didaftarkan ke atas hakmilik " + hm.getIdHakmilikInduk() );
                }
            }else{
                addSimpleError("Hakmilik ini tidak mempunyai Perbadanan Pengurusan");
            }
        }else{
                addSimpleError("Hakmilik ini tidak memohon strata");
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSijilMC.jsp");
    }

    public Resolution addNew() {
        Hakmilik hm = strataPtService.findIdBdn(idHakmilik);
        getContext().getRequest().setAttribute("addNew", Boolean.FALSE);
        if (hm != null) {
            bdnUrus = strataPtService.getidPihakByIdBdn(hm.getBadanPengurusan().getIdBadan());
            if (bdnUrus != null) {
                if (bdnUrus.getPengurusanNoSijil() != null) {
                    addSimpleError("Hakmilik ini telah memohon Sijil MC.");
                } else {
                    bdnUrus = new BadanPengurusan();
                    getContext().getRequest().setAttribute("addNew", Boolean.TRUE);
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSijilMC.jsp");
    }

    public Resolution save() {
        Hakmilik hm = strataPtService.findIdBdn(idHakmilik);
        getContext().getRequest().setAttribute("addNew", Boolean.FALSE);

        if (hm != null) {
            BadanPengurusan badanUrus = strataPtService.getidPihakByIdBdn(hm.getBadanPengurusan().getIdBadan());
            if (badanUrus != null) {
                if (bdnUrus != null) {
                    badanUrus.setCawangan(pengguna.getKodCawangan());
                    badanUrus.setPengurusanNoSijil(bdnUrus.getPengurusanNoSijil());
                    badanUrus.setPengurusanNoRujukan(bdnUrus.getPengurusanNoRujukan());
                    badanUrus.setPengurusanTarikhRujukan(bdnUrus.getPengurusanTarikhRujukan());
                    badanUrus.setPengurusanTarikhSijil(bdnUrus.getPengurusanTarikhSijil());
                    badanUrus.setNama(bdnUrus.getNama());
                    badanUrus.setNoPengenalan(bdnUrus.getNoPengenalan());
                    badanUrus.setAlamat4(bdnUrus.getAlamat4());

                    InfoAudit ia = new InfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new Date());

                    badanUrus.setInfoAudit(ia);
                    strataPtService.simpanMaklumatBangunan(badanUrus);
                    addSimpleMessage("Maklumat berjaya disimpan.");
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemasukanSijilMC.jsp");
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BadanPengurusan getBdnUrus() {
        return bdnUrus;
    }

    public void setBdnUrus(BadanPengurusan bdnUrus) {
        this.bdnUrus = bdnUrus;
    }

}

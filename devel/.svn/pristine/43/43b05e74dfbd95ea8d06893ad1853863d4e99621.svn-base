/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.tugasan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.service.dev.integrationflow.TugasanUtilitiService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/utiliti/tugasan")
public class TugasanActionBean extends AbleActionBean {

    @Inject
    TugasanUtilitiService tugasanUtilitiService;
    String idPermohonan;
    List<TugasanUtiliti> listPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        listPermohonan = tugasanUtilitiService.findListTugasan(ctx.getUser().getIdPengguna());
        return new JSP("pembangunan/utiliti/tugasan.jsp");
    }

    public Resolution countTugasan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        long total = tugasanUtilitiService.findTotalTugasanForPengguna(ctx.getUser());

        return new StreamingResolution("text/plain", String.valueOf(total));
    }

    public List<TugasanUtiliti> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<TugasanUtiliti> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

}

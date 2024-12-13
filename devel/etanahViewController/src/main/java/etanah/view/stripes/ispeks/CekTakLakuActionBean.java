/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.CekTakLakuViewDAO;
import etanah.dao.IspeksCekTakLakuDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksCekTakLaku;
import etanah.model.view.CekTakLakuView;
import etanah.service.ispeks.IspeksCekTakLakuService;
import etanah.service.ispeks.IspeksService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.GantianCekTakLakuActionBean;
import etanah.view.stripes.hasil.PembatalanResitActionBean;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/cek_tak_laku")
public class CekTakLakuActionBean extends AbleActionBean {

    @Inject
    IspeksService ispeksService;
    @Inject
    IspeksCekTakLakuService ispeksCekTakLakuService;
    @Inject
    IspeksCekTakLakuDAO ispeksCekTakLakuDAO;
    @Inject
    CekTakLakuViewDAO cekTakLakuViewDAO;
    List<IspeksCekTakLaku> listCekTakLaku;
    List<CekTakLakuView> listCektaklakuView;
    String idKewDok;

    @DefaultHandler
    public Resolution showForm() {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        listCekTakLaku = ispeksCekTakLakuDAO.findAll();
        listCektaklakuView = ispeksCekTakLakuService.findByKodCawangan(pengguna.getKodCawangan().getKod());
        return new JSP("ispeks/cek_takLaku.jsp");
    }

    public Resolution batalResit() {

        return new RedirectResolution(PembatalanResitActionBean.class, "search").addParameter("dokumenKewangan.idDokumenKewangan", idKewDok);
    }

    public Resolution gantiCek() {

        return new RedirectResolution(GantianCekTakLakuActionBean.class, "Step2").addParameter("dokumenKewangan.idDokumenKewangan", idKewDok);
    }

    public List<IspeksCekTakLaku> getListCekTakLaku() {
        return listCekTakLaku;
    }

    public void setListCekTakLaku(List<IspeksCekTakLaku> listCekTakLaku) {
        this.listCekTakLaku = listCekTakLaku;
    }

    public List<CekTakLakuView> getListCektaklakuView() {
        return listCektaklakuView;
    }

    public void setListCektaklakuView(List<CekTakLakuView> listCektaklakuView) {
        this.listCektaklakuView = listCektaklakuView;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

}

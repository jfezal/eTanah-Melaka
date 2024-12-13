/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Date;

import etanah.model.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import etanah.service.common.PermohonanService;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Administrator
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/batal_perserahan_utiliti_tukar_ganti")
public class BatalPerserahanUtilitiTukarGanti extends AbleActionBean {

    private Pengguna peng;
    String idTukarGanti;
    Permohonan permohonan;
    private String sebab;
    private String idPerserahan;
    boolean showDetailPermohonan = false;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanService permohonanService;
    private String aktif;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        return new ForwardResolution("/WEB-INF/jsp/utiliti/batal_perserahan_utiliti_tukar_ganti.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Resolution saveUrusanNBatal() throws ParseException {
        aktif = getContext().getRequest().getParameter("aktif");
//          String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAuditBaru ia = new InfoAuditBaru();
        ia.setDikemaskiniOlehBaru(peng);
        ia.setTarikhKemaskiniBaru(new Date());
        
        permohonan = permohonanService.findById(idTukarGanti);
        permohonan.setStatus(aktif);
        permohonan.setInfoAuditBaru(ia);
        permohonanService.saveOrUpdate(permohonan);
        addSimpleMessage("Kemasukan telah Berjaya");
        return new RedirectResolution(BatalPerserahanUtilitiTukarGanti.class, "cari");
    }

    public Resolution cari() {
        String message = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");

        if (StringUtils.isNotBlank(idTukarGanti)) {

            Session s = sessionProvider.get();
            Query q1 = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan");
            q1.setString("idPermohonan", idTukarGanti);
            permohonan = (Permohonan) q1.uniqueResult();

            if (permohonan != null) {
                showDetailPermohonan = true;
                return new JSP("utiliti/batal_perserahan_utiliti_tukar_ganti.jsp");
            } else {
                addSimpleError("Id Perserahan Tukar Ganti tidak wujud");
            }
            return new JSP("utiliti/batal_perserahan_utiliti_tukar_ganti.jsp");
        } else {
//            addSimpleError("Sila Masukkan Id Perserahan Tukar Ganti");
            return new JSP("utiliti/batal_perserahan_utiliti_tukar_ganti.jsp");
        }
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        sebab = getContext().getRequest().getParameter("sebab");

        permohonan = permohonanService.findById(idPerserahan);
        permohonan.setSebab(sebab);
        permohonan.setStatus("TK");
        InfoAudit ia = new InfoAudit();
        ia = permohonan.getInfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new Date());
        permohonanService.saveOrUpdate(permohonan);

        return new ForwardResolution("/WEB-INF/jsp/utiliti/simpan_berjaya.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        Session s = sessionProvider.get();
        Query q1 = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan and p.kodUrusan.kod in ('HSTHK','HKTHK') and p.status in ('TA','TK')");
        q1.setString("idPermohonan", idPerserahan);
        permohonan = (Permohonan) q1.uniqueResult();
        showDetailPermohonan = true;
        return new JSP("utiliti/batal_perserahan_utiliti_tukar_ganti.jsp");
    }

    public Resolution saveBatal() {
        idPerserahan = getContext().getRequest().getParameter("idPerserahan");
        getContext().getRequest().setAttribute("idPerserahan", idPerserahan);
        return new JSP("utiliti/batal_perserahan_utiliti_tukar_ganti_popup.jsp").addParameter("popup", "true");
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getIdTukarGanti() {
        return idTukarGanti;
    }

    public void setIdTukarGanti(String idTukarGanti) {
        this.idTukarGanti = idTukarGanti;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isShowDetailPermohonan() {
        return showDetailPermohonan;
    }

    public void setShowDetailPermohonan(boolean showDetailPermohonan) {
        this.showDetailPermohonan = showDetailPermohonan;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PermohonanService getPermohonanService() {
        return permohonanService;
    }

    public void setPermohonanService(PermohonanService permohonanService) {
        this.permohonanService = permohonanService;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
}

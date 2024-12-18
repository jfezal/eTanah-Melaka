/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.service.common.PermohonanPihakService;
import org.hibernate.Session;
import etanah.view.ListUtil;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kehadiran_waris")
public class KehadiranWarisActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KehadiranWarisActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    ListUtil listUtil;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> warisList;
    private List<PermohonanPihak> warisHadirList;
    private String idPermohonan;
    private Permohonan permohonan;
    boolean duplicateFlag = false;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/kemasukan_kehadiran_waris.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            warisList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "WAR");
            warisHadirList = permohonanPihakService.getSenaraiPmohonPihakByKodAndNilai(idPermohonan, "WAR", "HADIR BICARA");
        }
    }

    public Resolution pilihWaris() {
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        return new JSP("consent/pilih_waris.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {

        String idPihak = getContext().getRequest().getParameter("idPihak");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        List<PermohonanPihak> listTemp;
        listTemp = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "WAR");

        if (!(listTemp.isEmpty())) {

            for (PermohonanPihak perPihak : listTemp) {
                if (perPihak.getPihak().getIdPihak() == Long.parseLong(idPihak)) {

                    perPihak.setDalamanNilai1("HADIR BICARA");
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit = perPihak.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    perPihak.setInfoAudit(infoAudit);
                    permohonanPihakService.saveOrUpdate(perPihak);
                    break;
                }
            }
        }

        return refreshpage();
    }

    public Resolution refreshpage() {

        return new JSP("consent/kemasukan_kehadiran_waris.jsp").addParameter("tab", "true");
    }

    public Resolution getSenaraiWaris() {

        return new JSP("consent/kemasukan_kehadiran_waris.jsp").addParameter("tab", "true");
    }

    public Resolution deleteWarisHadir() {

        String idMohonPihak = getContext().getRequest().getParameter("idMohonPihak");

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        PermohonanPihak perPihak = new PermohonanPihak();
        perPihak = permohonanPihakDAO.findById(Long.parseLong(idMohonPihak));

        perPihak.setDalamanNilai1("");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit = perPihak.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pengguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        perPihak.setInfoAudit(infoAudit);
        permohonanPihakService.saveOrUpdate(perPihak);

        rehydrate();
        return getSenaraiWaris();
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanPihak> getWarisList() {
        return warisList;
    }

    public void setWarisList(List<PermohonanPihak> warisList) {
        this.warisList = warisList;
    }

    public List<PermohonanPihak> getWarisHadirList() {
        return warisHadirList;
    }

    public void setWarisHadirList(List<PermohonanPihak> warisHadirList) {
        this.warisHadirList = warisHadirList;
    }

    public boolean isDuplicateFlag() {
        return duplicateFlag;
    }

    public void setDuplicateFlag(boolean duplicateFlag) {
        this.duplicateFlag = duplicateFlag;
    }
}

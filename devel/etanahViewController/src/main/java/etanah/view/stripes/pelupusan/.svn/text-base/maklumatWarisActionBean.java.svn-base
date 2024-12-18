/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.service.PelupusanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author Srinivas
 */
@UrlBinding("/pelupusan/maklumatWaris")
public class maklumatWarisActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;

    private List<PermohonanPihak> mohonPihakList;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private String idPermohonan;
    private Permohonan permohonan;
    private InfoAudit ia;
    private Pengguna peng;
    private String edit;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            mohonPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
        }
         return new JSP("pelupusan/maklumat_waris.jsp").addParameter("tab", "true");
    }
    public Resolution showForm1() {
        String idPermohonanPihak = getContext().getRequest().getParameter("idPermohonanPihak");
        if(idPermohonanPihak != null){
            permohonanPihak = permohonanPihakDAO.findById(new Long(idPermohonanPihak));
            pihak = pihakDAO.findById(permohonanPihak.getPihak().getIdPihak());
        }
         return new JSP("pelupusan/maklumat_waris_popup.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }

    public Resolution simpanMaklumatWaris() {
            //permohonanPihak = new PermohonanPihak();
            //pihak = new Pihak();
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            Long idMohonPihak = permohonanPihak.getIdPermohonanPihak();
         //if (idMohonPihak != null && idMohonPihak > 0 ) {
             permohonanPihak = permohonanPihakDAO.findById(permohonanPihak.getIdPermohonanPihak());
             if(permohonanPihak != null){
             pihak = pihakDAO.findById(permohonanPihak.getPihak().getIdPihak());
             }
          //}
            if(permohonanPihak != null){
             peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
             //permohonanPihak = permohonanPihakDAO.findById(idMohonPihak);
             //pihak = pihakDAO.findById(permohonanPihak.getPihak().getIdPihak());
             ia = permohonanPihak.getInfoAudit();
             ia.setTarikhKemaskini(new java.util.Date());
             ia.setDiKemaskiniOleh(peng);
             permohonanPihak.setInfoAudit(ia);
             ia = new InfoAudit();
             ia = pihak.getInfoAudit();
             ia.setTarikhKemaskini(new java.util.Date());
             ia.setDiKemaskiniOleh(peng);
             pihak.setInfoAudit(ia);
             edit = "3";
            }else{
             permohonanPihak = new PermohonanPihak();
             pihak = new Pihak();
             ia = new InfoAudit();
             peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
             ia.setDimasukOleh(peng);
             ia.setTarikhMasuk(new java.util.Date());
             pihak.setInfoAudit(ia);
             ia = new InfoAudit();
             ia.setDimasukOleh(peng);
             ia.setTarikhMasuk(new java.util.Date());
             permohonanPihak.setInfoAudit(ia);
             edit = "1";
            }
            pihak.setNama(getContext().getRequest().getParameter("pihak.nama").toString());
            pihak.setKodJantina(getContext().getRequest().getParameter("pihak.kodJantina").toString());
            pihak.setAlamat1(getContext().getRequest().getParameter("pihak.alamat1").toString());
            pihak.setAlamat2(getContext().getRequest().getParameter("pihak.alamat2").toString());
            pihak.setAlamat3(getContext().getRequest().getParameter("pihak.alamat3").toString());
            pihak = pelupusanService.saveOrUpdate(pihak);
            String kod_pihak = (pihak.getKodJantina().equals("1"))?"WAL":(pihak.getKodJantina().equals("2"))?"WAP":(pihak.getKodJantina().equals("0"))?"WKM":"";
            KodJenisPihakBerkepentingan kodJenisPihakBerkepentingan = pelupusanService.findKodJenisPihakBerkepentingan(kod_pihak);
            permohonanPihak.setJenis(kodJenisPihakBerkepentingan);
            permohonanPihak.setUmur(Integer.parseInt(getContext().getRequest().getParameter("permohonanPihak.umur")));
            permohonanPihak.setPihak(pihak);
            permohonanPihak.setInfoAudit(ia);
            permohonanPihak.setPermohonan(permohonan);
            permohonanPihak.setCawangan(permohonan.getCawangan());
            pelupusanService.saveOrUpdatePermohonanPihak(permohonanPihak);

            permohonanPihak = new PermohonanPihak();
            pihak = new Pihak();
        return new JSP("pelupusan/maklumat_waris_popup.jsp").addParameter("tab", "true");
    }

      // added by Srinvas
    public Resolution closeWindow() {
       return new RedirectResolution(maklumatWarisActionBean.class, "locate");
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }





}

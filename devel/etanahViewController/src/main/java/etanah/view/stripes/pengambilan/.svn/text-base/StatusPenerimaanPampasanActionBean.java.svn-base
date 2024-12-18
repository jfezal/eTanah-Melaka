/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusMohonPihakDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusMohonPihak;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh Reddy
 */

@UrlBinding("/pengambilan/status_penerimaan_pampasan")
public class StatusPenerimaanPampasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    KodStatusMohonPihakDAO kodStatusMohonPihakDAO;
    @Inject
    KodStatusMohonPihak kodStatusMohonPihak;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private String idPermohonan;
    private String idHakmilik;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<KodStatusMohonPihak> senaraiKodStatusMohonPihak;
    private List<String> statusMohonPihak =  new ArrayList<String>();


    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if(permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        return new JSP("pengambilan/Status_Penerimaan_Pampasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
    }

    public Resolution hakmilikDetails() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiKodStatusMohonPihak = notisPenerimaanService.getSenaraiKodStatusMohonPihak();
        System.out.println("senaraiKodStatusMohonPihak---------"+senaraiKodStatusMohonPihak.size());
//        senaraiKodStatusMohonPihak = kodStatusMohonPihakDAO.findAll();
       // senaraiKodStatusMohonPihak = kodStatusMohonPihak.findAll();

        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByHakmilik(idPermohonan, idHakmilik);
        statusMohonPihak =  new ArrayList<String>();


        for(PermohonanPihak permohonanPihak : senaraiPermohonanPihak) {
            KodStatusMohonPihak kod = permohonanPihak.getStatusMohonPihak();
            if(kod != null) {
                statusMohonPihak.add(kod.getKod());
            }else {
                statusMohonPihak.add(" ");
            }
        }
        
        return new JSP("pengambilan/Status_Penerimaan_Pampasan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByHakmilik(idPermohonan, idHakmilik);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        for(int i=0; i<senaraiPermohonanPihak.size() ; i++) {
            PermohonanPihak permohonanPihak = senaraiPermohonanPihak.get(i);
            InfoAudit info = permohonanPihak.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permohonanPihak.setInfoAudit(info);
            if(!statusMohonPihak.get(i).equals("0")){
            permohonanPihak.setStatusMohonPihak(kodStatusMohonPihakDAO.findById(statusMohonPihak.get(i)));
            permohonanPihakDAO.saveOrUpdate(permohonanPihak);
            }

        }

        tx.commit();

        senaraiKodStatusMohonPihak = kodStatusMohonPihakDAO.findAll();
        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByHakmilik(idPermohonan, idHakmilik);
        statusMohonPihak =  new ArrayList<String>();
        for(PermohonanPihak permohonanPihak : senaraiPermohonanPihak) {
            KodStatusMohonPihak kod = permohonanPihak.getStatusMohonPihak();
            if(kod != null) {
                statusMohonPihak.add(kod.getKod());
            }else {
                statusMohonPihak.add(" ");
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Status_Penerimaan_Pampasan.jsp").addParameter("tab", "true");
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<KodStatusMohonPihak> getSenaraiKodStatusMohonPihak() {
        return senaraiKodStatusMohonPihak;
    }

    public void setSenaraiKodStatusMohonPihak(List<KodStatusMohonPihak> senaraiKodStatusMohonPihak) {
        this.senaraiKodStatusMohonPihak = senaraiKodStatusMohonPihak;
    }

    public List<String> getStatusMohonPihak() {
        return statusMohonPihak;
    }

    public void setStatusMohonPihak(List<String> statusMohonPihak) {
        this.statusMohonPihak = statusMohonPihak;
    }

    
}

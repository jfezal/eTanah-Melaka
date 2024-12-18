/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerPermohonanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.HantarBorangPermohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.TampalBorangPermohonan;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.BPelService;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.service.acq.service.BorangACQService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BorangABForm;
import etanah.view.stripes.pengambilan.share.form.BorangAForm;
import etanah.view.stripes.pengambilan.share.form.BorangBForm;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/borang_a_b")
public class PenyampaianBorangAActionBean extends AbleActionBean {

    @Inject
    BorangACQService borangACQService;
    @Inject
    BorangPerPermohonanDAO borangPerPermohonanDAO;
    @Inject
    Sek4IntegrationFlowService sek4IntegrationFlowService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;

    List<BorangAForm> listBorangA;
    List<BorangBForm> listBorangB;
    BorangABForm borangABForm;
    String tarikhTampalA;
    String tempatTampalA;
    String penghantarNotisA;
    String catatanA;

    String tarikhHantarB;
    String tempatHantarB;
    String penghantarB;
    String catatanB;

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        borangABForm = borangACQService.findInfoBorangAB(idPermohonan);
        listBorangA = borangACQService.findInfoBorangA(borangABForm.getIdBorangPerPermohonanA());
        listBorangB = borangACQService.findInfoBorangB(borangABForm.getIdBorangPerPermohonanB());

        return new JSP("/pengambilan/APT/sampai_borangAB.jsp");
    }

    public Resolution hapusA() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String id = (String) getContext().getRequest().getParameter("id");
        borangACQService.hapusTampalBorangPermohonan(id);
        borangABForm = borangACQService.findInfoBorangAB(idPermohonan);
        listBorangA = borangACQService.findInfoBorangA(borangABForm.getIdBorangPerPermohonanA());
        listBorangB = borangACQService.findInfoBorangB(borangABForm.getIdBorangPerPermohonanB());

        return new JSP("/pengambilan/APT/sampai_borangAB.jsp");
    }

    public Resolution hapusB() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String id = (String) getContext().getRequest().getParameter("id");
        borangACQService.hapusHantarBorangPermohonan(id);
        borangABForm = borangACQService.findInfoBorangAB(idPermohonan);
        listBorangA = borangACQService.findInfoBorangA(borangABForm.getIdBorangPerPermohonanA());
        listBorangB = borangACQService.findInfoBorangB(borangABForm.getIdBorangPerPermohonanB());

        return new JSP("/pengambilan/APT/sampai_borangAB.jsp");
    }

    public Resolution showForm2() {
        return new JSP(" pengambilan/Penerimaan_Notis_Borang.jsp");
    }

    public Resolution tambahBorangA() throws ParseException {

        return new JSP("/pengambilan/APT/borangApopup.jsp").addParameter("popup", true);

    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

    public Resolution simpanBorangA() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idBorangPerPermohonanA = (String) getContext().getRequest().getParameter("idBorangPerPermohonanA");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        BorangPerPermohonan borangPerPermohonan = borangPerPermohonanDAO.findById(Long.parseLong(idBorangPerPermohonanA));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        TampalBorangPermohonan bm = new TampalBorangPermohonan();
        bm.setBorangPerPermohonan(borangPerPermohonan);
        bm.setTarikhTampal(formatSDF(tarikhTampalA));
        bm.setTempatTampal(tempatTampalA);
        bm.setCatatan(catatanA);
        bm.setInfoAudit(infoAudit);
        bm.setPenghantarNotis(penghantarNotisA);
        borangACQService.simpanTampalBorangPermohonan(bm);
        addSimpleMessage("Maklumat Berjaya disimpan");

        return new JSP("/pengambilan/APT/borangApopup.jsp").addParameter("popup", true);

    }

    public Resolution simpanBorangB() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idBorangPerPermohonanB = (String) getContext().getRequest().getParameter("idBorangPerPermohonanB");
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        BorangPerPermohonan borangPerPermohonan = borangPerPermohonanDAO.findById(Long.parseLong(idBorangPerPermohonanB));
        InfoAudit infoAudit = setInfoAudit(pengguna);
        HantarBorangPermohonan bm = new HantarBorangPermohonan();
        bm.setBorangPerPermohonan(borangPerPermohonan);
        bm.setTarikhHantar(formatSDF(tarikhHantarB));
//        bm.set
//        bm.set(tempatHantarB);
        bm.setInfoAudit(infoAudit);
        bm.setCatatan(catatanB);
        bm.setPenghantarNotis(penghantarB);
        borangACQService.simpanHantarBorangPermohonan(bm);
        addSimpleMessage("Maklumat Berjaya disimpan");
        return new JSP("/pengambilan/APT/borangBpopup.jsp").addParameter("popup", true);

    }

    public Resolution selesai() {
        
         String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        RefPeringkat ref = new RefPeringkat();

        sek4IntegrationFlowService.completeTask(ref.SELESAI, mohon, pengguna);
        
        //KodKeputusan kodKeputusan = kodKeputusanDAO.findById("SL");
        mohon.setStatus("SL");
        permohonanDAO.saveOrUpdate(mohon);

        return new RedirectResolution("/senaraiTugasan");
    }

    public Resolution tambahBorangB() {
        return new JSP("/pengambilan/APT/borangBpopup.jsp").addParameter("popup", true);

    }

    public List<BorangAForm> getListBorangA() {
        return listBorangA;
    }

    public void setListBorangA(List<BorangAForm> listBorangA) {
        this.listBorangA = listBorangA;
    }

    public List<BorangBForm> getListBorangB() {
        return listBorangB;
    }

    public void setListBorangB(List<BorangBForm> listBorangB) {
        this.listBorangB = listBorangB;
    }

    public BorangABForm getBorangABForm() {
        return borangABForm;
    }

    public void setBorangABForm(BorangABForm borangABForm) {
        this.borangABForm = borangABForm;
    }

    private InfoAudit setInfoAudit(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public String getTarikhTampalA() {
        return tarikhTampalA;
    }

    public void setTarikhTampalA(String tarikhTampalA) {
        this.tarikhTampalA = tarikhTampalA;
    }

    public String getTempatTampalA() {
        return tempatTampalA;
    }

    public void setTempatTampalA(String tempatTampalA) {
        this.tempatTampalA = tempatTampalA;
    }

    public String getPenghantarNotisA() {
        return penghantarNotisA;
    }

    public void setPenghantarNotisA(String penghantarNotisA) {
        this.penghantarNotisA = penghantarNotisA;
    }

    public String getCatatanA() {
        return catatanA;
    }

    public void setCatatanA(String catatanA) {
        this.catatanA = catatanA;
    }

    public String getTarikhHantarB() {
        return tarikhHantarB;
    }

    public void setTarikhHantarB(String tarikhHantarB) {
        this.tarikhHantarB = tarikhHantarB;
    }

    public String getTempatHantarB() {
        return tempatHantarB;
    }

    public void setTempatHantarB(String tempatHantarB) {
        this.tempatHantarB = tempatHantarB;
    }

    public String getPenghantarB() {
        return penghantarB;
    }

    public void setPenghantarB(String penghantarB) {
        this.penghantarB = penghantarB;
    }

    public String getCatatanB() {
        return catatanB;
    }

    public void setCatatanB(String catatanB) {
        this.catatanB = catatanB;
    }

}

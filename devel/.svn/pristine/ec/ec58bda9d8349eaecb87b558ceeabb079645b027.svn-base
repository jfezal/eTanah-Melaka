/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanPerbincangan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.TuntutanPerPB;
import etanah.ref.pengambilan.sek4.RefPeringkat;
import etanah.service.BPelService;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8BCIntegrationFlowService;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.service.acq.service.BorangACQService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zipzap
 */
@UrlBinding("/pengambilan/borang_e_f_pampasan")
public class MaklumatBorangEPampasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BorangACQService borangACQService;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService;
    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    @Inject
    Sek8BCIntegrationFlowService sek8BCIntegrationFlowService;

    List<HakmilikPermohonan> listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    List<TuntutanPerPB> listTuntutan;
    String urlKembali;
    BorangEForm e;
    String idPermohonan;
    String idborangperpb;
    String urusan;
    String tujuan;
    String ketPerbincgn;
    String statusPerbincgn;
    BigDecimal amaunTuntutan;
    String itemTuntutan;

    @DefaultHandler
    public Resolution aduanKerosakan() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();
        urlKembali = "aduanKerosakan";
        listHakmilikPermohonan = p.getSenaraiHakmilik();
//        borangABForm = borangACQService.findInfoBorangAB(idPermohonan);
//        listBorangA = borangACQService.findInfoBorangA(borangABForm.getIdBorangPerPermohonanA());
//        listBorangB = borangACQService.findInfoBorangB(borangABForm.getIdBorangPerPermohonanB());

        return new JSP("/pengambilan/APT/borang_e_item_pampasan.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Resolution viewBorangE() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();

        e = borangACQService.findBorangEByIdMohonAndIdHakmilik(idPermohonan, idHakmilik);
        return new JSP("/pengambilan/APT/borang_f_item_pampasan.jsp");
    }

    public Resolution popupItem() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        idborangperpb = (String) getContext().getRequest().getParameter("idborangperpb");
        BorangPerPB pb = borangPerPBDAO.findById(Long.parseLong(idborangperpb));
        listTuntutan = new ArrayList<TuntutanPerPB>();
        listTuntutan = borangACQService.listTuntutanPerPB(pb);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();

        return new JSP("/pengambilan/APT/item_popup.jsp").addParameter("popup", true);
    }

    public Resolution savepopupItem() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        idborangperpb = (String) getContext().getRequest().getParameter("idborangperpb");
        BorangPerPB pb = borangPerPBDAO.findById(Long.parseLong(idborangperpb));
        TuntutanPerPB tu = new TuntutanPerPB();
        tu.setBorangPerPB(pb);
        tu.setAmaun(amaunTuntutan);
        tu.setBorangPerPB(pb);
        tu.setItemTuntutan(itemTuntutan);
        borangACQService.saveTuntutan(tu);
        listTuntutan = borangACQService.listTuntutanPerPB(pb);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        urusan = p.getKodUrusan().getNama();

        return new JSP("/pengambilan/APT/item_popup.jsp").addParameter("popup", true);
    }

    public Resolution hantar() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        PermohonanPengambilan ambil = pengambilanAPTService.findPermohonanPengambilanByIdMH(mohon.getIdPermohonan());
        if (mohon.getKodUrusan().getKod().equals("SEK4A")) {
            AduanPerbincangan aduanPerbincangan = new AduanPerbincangan();
            aduanPerbincangan.setKetPerbincgn(ketPerbincgn);
            aduanPerbincangan.setStatusPerbincgn(statusPerbincgn);
            aduanPerbincangan.setPermohonan(mohon);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
            aduanPerbincangan.setInfoAudit(ia);
            pengambilanAPTService.saveAduanPerbincangan(aduanPerbincangan);
            etanah.ref.pengambilan.sek4aduan.RefPeringkat ref = new etanah.ref.pengambilan.sek4aduan.RefPeringkat();
            if (statusPerbincgn.equals("P")) {
                sek4AduanIntegrationFlowService.completeTask(ref.REKOD_KPSN_MMK, mohon, pengguna);

            } else {
                sek4AduanIntegrationFlowService.completeTask(ref.SURAT_BAYAR, mohon, pengguna);
            }
        } else if (mohon.getKodUrusan().getKod().equals("831")) {
            if (ambil.getKatPengambilan().equals("1")) {
                etanah.ref.pengambilan.sek8a.RefPeringkat ref = new etanah.ref.pengambilan.sek8a.RefPeringkat();
                sek8aIntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_MMK, mohon, pengguna);
            } else {
                etanah.ref.pengambilan.sek8bc.RefPeringkat ref = new etanah.ref.pengambilan.sek8bc.RefPeringkat();
                sek8BCIntegrationFlowService.completeTask(ref.SEDIA_TERIMA_KPSN_MMK_8BC, mohon, pengguna);

            }
        }

        return new RedirectResolution("/utiliti/tugasan");
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public BorangEForm getE() {
        return e;
    }

    public void setE(BorangEForm e) {
        this.e = e;
    }

    public String getIdborangperpb() {
        return idborangperpb;
    }

    public void setIdborangperpb(String idborangperpb) {
        this.idborangperpb = idborangperpb;
    }

    public BorangACQService getBorangACQService() {
        return borangACQService;
    }

    public void setBorangACQService(BorangACQService borangACQService) {
        this.borangACQService = borangACQService;
    }

    public BorangPerPBDAO getBorangPerPBDAO() {
        return borangPerPBDAO;
    }

    public void setBorangPerPBDAO(BorangPerPBDAO borangPerPBDAO) {
        this.borangPerPBDAO = borangPerPBDAO;
    }

    public List<TuntutanPerPB> getListTuntutan() {
        return listTuntutan;
    }

    public void setListTuntutan(List<TuntutanPerPB> listTuntutan) {
        this.listTuntutan = listTuntutan;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getItemTuntutan() {
        return itemTuntutan;
    }

    public void setItemTuntutan(String itemTuntutan) {
        this.itemTuntutan = itemTuntutan;
    }

    public String getUrlKembali() {
        return urlKembali;
    }

    public void setUrlKembali(String urlKembali) {
        this.urlKembali = urlKembali;
    }

    public String getKetPerbincgn() {
        return ketPerbincgn;
    }

    public void setKetPerbincgn(String ketPerbincgn) {
        this.ketPerbincgn = ketPerbincgn;
    }

    public String getStatusPerbincgn() {
        return statusPerbincgn;
    }

    public void setStatusPerbincgn(String statusPerbincgn) {
        this.statusPerbincgn = statusPerbincgn;
    }
    
    

}

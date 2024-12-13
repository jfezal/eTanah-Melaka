/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BilDAO;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.AkaunBank;
import etanah.model.ispek.Bil;
import etanah.model.ispek.TugasanIspeks;
import etanah.service.ispeks.BilService;
import etanah.service.ispeks.IDDiSpeksService;
import etanah.service.ispeks.IspeksStatus;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
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
@UrlBinding("/ispeks/lulus_bil")
public class LulusBilActionBean extends AbleActionBean {

    @Inject
    BilService bilService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    BilDAO bilDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    IDDiSpeksService iDDiSpeksService;
    String noPenyata;
    Long idTugasan;
    Long idBil;
    Long jumlah;
    List<BilForm> senaraiPenyata;
    String noBil;

    List<AkaunBank> senaraiBank;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        String id = getContext().getRequest().getParameter("id");
        Bil bil = bilDAO.findById(Long.parseLong(id));
        TugasanIspeks t = bilService.findByIdRef(bil.getId());
        idTugasan = t.getId();
        idBil = bil.getId();
        noBil = bil.getNoBil();

        jumlah = bilService.findSumBil(idBil);

        senaraiPenyata = bilService.paparPenyata(bil.getId());
        return new JSP("ispeks/lulus_bil.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        simpanData(bil, pengguna);
        return new RedirectResolution(LulusBilActionBean.class, "showForm").addParameter("id", bil.getId());

    }

    public Bil simpanData(Bil bil, Pengguna pengguna) throws ParseException {

        bil.setPelulus(pengguna.getNama());
        bil.setJawatanPelulus(pengguna.getJawatan());
        bil.setTarikhLulus(new Date());
        bil.setTarikhHantar(new Date());
        bil = bilService.simpanBil(bil);
        return bil;
    }

    public Resolution selesai() throws IOException, ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        simpanData(bil, pengguna);
        bilService.deleteTugasan(t);
        IspeksStatus status = iDDiSpeksService.janaIddBil(bil.getId(), bil.getKodCawangan(), pengguna);
        if ("Y".equals(status.getStatusGPG()) && "Y".equals(status.getStatusIdd()) && "Y".equals(status.getStatusSftp())) {
            addSimpleMessage("Proses telah berjaya dan dihantar");

        } else {
        }
//        addSimpleError(bank);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public Long getIdBil() {
        return idBil;
    }

    public void setIdBil(Long idBil) {
        this.idBil = idBil;
    }

    public Long getIdTugasan() {
        return idTugasan;
    }

    public void setIdTugasan(Long idTugasan) {
        this.idTugasan = idTugasan;
    }

    public KodPeringkatIspeksDAO getKodPeringkatIspeksDAO() {
        return kodPeringkatIspeksDAO;
    }

    public void setKodPeringkatIspeksDAO(KodPeringkatIspeksDAO kodPeringkatIspeksDAO) {
        this.kodPeringkatIspeksDAO = kodPeringkatIspeksDAO;
    }

    public TugasanIspeksDAO getTugasanIspeksDAO() {
        return tugasanIspeksDAO;
    }

    public void setTugasanIspeksDAO(TugasanIspeksDAO tugasanIspeksDAO) {
        this.tugasanIspeksDAO = tugasanIspeksDAO;
    }

    public IDDiSpeksService getiDDiSpeksService() {
        return iDDiSpeksService;
    }

    public void setiDDiSpeksService(IDDiSpeksService iDDiSpeksService) {
        this.iDDiSpeksService = iDDiSpeksService;
    }

    public List<BilForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<BilForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public String getNoBil() {
        return noBil;
    }

    public void setNoBil(String noBil) {
        this.noBil = noBil;
    }

}

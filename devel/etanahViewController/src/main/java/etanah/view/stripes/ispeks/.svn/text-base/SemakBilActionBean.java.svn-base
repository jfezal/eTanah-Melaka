/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunBankDAO;
import etanah.dao.BilDAO;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.AkaunBank;
import etanah.model.ispek.Bil;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.PenyataPemungutTunai;
import etanah.service.ispeks.BilService;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/semak_bil")
public class SemakBilActionBean extends AbleActionBean {

    @Inject
    BilService bilService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    BilDAO bilDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    AkaunBankDAO akaunBankDAO;
    String noPenyata;
    Long idTugasan;
    Long idBil;
    Long jumlah;
    String noBil;
   
    List<BilForm> senaraiPenyata;
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
        return new JSP("ispeks/semak_bil.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        simpanData(bil, pengguna);
        return new RedirectResolution(SemakBilActionBean.class, "showForm").addParameter("id", bil.getId());

    }

    public Bil simpanData(Bil bil, Pengguna pengguna) throws ParseException {
       
        bil.setPenyemak(pengguna.getNama());
        bil.setJawatanPenyemak(pengguna.getJawatan());
        bil.setTarikhSemak(new Date());
        bil = bilService.simpanBil(bil);
        return bil;
    }

    public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        KodPeringkatIspeks kodPeringkat = kodPeringkatIspeksDAO.findById("BLUL");
        t.setKodPeringkat(kodPeringkat);
        t.setTarikhTerima(new Date());
        simpanData(bil, pengguna);
        bilService.simpanTugasanIspeks(t);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public Long getIdTugasan() {
        return idTugasan;
    }

    public void setIdTugasan(Long idTugasan) {
        this.idTugasan = idTugasan;
    }

    public BilService getBilService() {
        return bilService;
    }

    public void setBilService(BilService bilService) {
        this.bilService = bilService;
    }

    public KodPeringkatIspeksDAO getKodPeringkatIspeksDAO() {
        return kodPeringkatIspeksDAO;
    }

    public void setKodPeringkatIspeksDAO(KodPeringkatIspeksDAO kodPeringkatIspeksDAO) {
        this.kodPeringkatIspeksDAO = kodPeringkatIspeksDAO;
    }

    public BilDAO getBilDAO() {
        return bilDAO;
    }

    public void setBilDAO(BilDAO bilDAO) {
        this.bilDAO = bilDAO;
    }

    public TugasanIspeksDAO getTugasanIspeksDAO() {
        return tugasanIspeksDAO;
    }

    public void setTugasanIspeksDAO(TugasanIspeksDAO tugasanIspeksDAO) {
        this.tugasanIspeksDAO = tugasanIspeksDAO;
    }

    public AkaunBankDAO getAkaunBankDAO() {
        return akaunBankDAO;
    }

    public void setAkaunBankDAO(AkaunBankDAO akaunBankDAO) {
        this.akaunBankDAO = akaunBankDAO;
    }

    public Long getIdBil() {
        return idBil;
    }

    public void setIdBil(Long idBil) {
        this.idBil = idBil;
    }

    public List<BilForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<BilForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
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

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
import etanah.model.DokumenKewangan;
import etanah.model.Pengguna;
import etanah.model.ispek.AkaunBank;
import etanah.model.ispek.Bil;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.PenyataPemungutTunai;
import etanah.model.view.ResitPenyataPemungut;
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
@UrlBinding("/ispeks/sedia_bil")
public class SediaBilActionBean extends AbleActionBean {

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
    String noBil;
    Long idTugasan;
    Long idBil;
    String noSlipBank;
    String namaBank;
    String noAkaunBank;
    String linkPenyataPemungut;
    String tarikhMula;
    String tarikhAkhir;
    String bank;
    String tarikhBank;
    Long jumlah;
    List<AkaunBank> senaraiBank;
    List<BilForm> senaraiPenyata;

    @DefaultHandler
    public Resolution showForm() {
        String id = getContext().getRequest().getParameter("id");
        Bil bil = bilDAO.findById(Long.parseLong(id));
        TugasanIspeks t = bilService.findByIdRef(bil.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 idTugasan = t.getId();
 idBil = bil.getId();
 noBil = bil.getNoBil();
 jumlah = bilService.findSumBil(idBil);
        senaraiPenyata = bilService.paparPenyata(bil.getId());
        return new JSP("ispeks/sedia_bil.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        bil = simpanData(bil, pengguna);
        senaraiPenyata = bilService.paparPenyata(bil.getId());
        return new RedirectResolution(SediaBilActionBean.class, "showForm").addParameter("id", bil.getId());
    }

    public Bil simpanData(Bil bil, Pengguna pengguna) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
bil.setNoBil(noBil);
        bil.setPenyedia(pengguna.getNama());
        bil.setJawatanPenyedia(pengguna.getJawatan());
        bil.setTarikhSedia(new Date());

        bil = bilService.simpanBil(bil);
        return bil;
    }

    public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Bil bil = bilDAO.findById(idBil);
        bil = simpanData(bil, pengguna);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("BLSEM");
        t.setKodPeringkat(kodPeringkat);
//        t.setNoRef(pp.getNoPenyata());
        t.setTarikhTerima(new Date());
        bilService.simpanTugasanIspeks(t);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public AkaunBankDAO getAkaunBankDAO() {
        return akaunBankDAO;
    }

    public void setAkaunBankDAO(AkaunBankDAO akaunBankDAO) {
        this.akaunBankDAO = akaunBankDAO;
    }

    public List<BilForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<BilForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
    }

    public String getNoBil() {
        return noBil;
    }

    public void setNoBil(String noBil) {
        this.noBil = noBil;
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

    public Long getIdBil() {
        return idBil;
    }

    public void setIdBil(Long idBil) {
        this.idBil = idBil;
    }

  
    public TugasanIspeksDAO getTugasanIspeksDAO() {
        return tugasanIspeksDAO;
    }

    public void setTugasanIspeksDAO(TugasanIspeksDAO tugasanIspeksDAO) {
        this.tugasanIspeksDAO = tugasanIspeksDAO;
    }

    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoAkaunBank() {
        return noAkaunBank;
    }

    public void setNoAkaunBank(String noAkaunBank) {
        this.noAkaunBank = noAkaunBank;
    }

    public String getLinkPenyataPemungut() {
        return linkPenyataPemungut;
    }

    public void setLinkPenyataPemungut(String linkPenyataPemungut) {
        this.linkPenyataPemungut = linkPenyataPemungut;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(String tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public List<AkaunBank> getSenaraiBank() {
        return senaraiBank;
    }

    public void setSenaraiBank(List<AkaunBank> senaraiBank) {
        this.senaraiBank = senaraiBank;
    }

    public String getTarikhBank() {
        return tarikhBank;
    }

    public void setTarikhBank(String tarikhBank) {
        this.tarikhBank = tarikhBank;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

}

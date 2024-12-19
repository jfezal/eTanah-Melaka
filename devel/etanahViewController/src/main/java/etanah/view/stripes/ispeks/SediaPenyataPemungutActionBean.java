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
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.DokumenKewangan;
import etanah.model.Pengguna;
import etanah.model.ispek.AkaunBank;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.PenyataPemungutTunai;
import etanah.model.view.ResitPenyataPemungut;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
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
@UrlBinding("/ispeks/sedia_penyata_pemungut")
public class SediaPenyataPemungutActionBean extends AbleActionBean {

    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    AkaunBankDAO akaunBankDAO;
    String noPenyata;
    Long idTugasan;
    Long idPenyataPemungut;
    String noSlipBank;
    String namaBank;
    String noAkaunBank;
    String linkPenyataPemungut;
    String tarikhMula;
    String tarikhAkhir;
    String bank;
    String tarikhBank;

    BigDecimal totalAmaun;
    List<AkaunBank> senaraiBank;
    List<PenyataPemungutForm> senaraiPenyata;

    @DefaultHandler
    public Resolution showForm() {
        String id = getContext().getRequest().getParameter("id");
        PenyataPemungut pp = penyataPemungutDAO.findById(Long.parseLong(id));
        TugasanIspeks t = penyataPemungutService.findByIdRef(pp.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        senaraiBank = akaunBankDAO.findAll();
        idTugasan = t.getId();
        idPenyataPemungut = pp.getId();
        noSlipBank = pp.getNoSlipBank();
        namaBank = pp.getNamaBank();
        noAkaunBank = pp.getNoAkaunBank();
        noPenyata = pp.getNoPenyata();
        if (pp.getAkaunBank() != null) {
            bank = pp.getAkaunBank().getKod();
        }
        if (pp.getTarikhBank() != null) {
            tarikhBank = sdf.format(pp.getTarikhBank());
        }
        linkPenyataPemungut = "";
        DokumenKewangan dkMula = penyataPemungutService.findTarikhMulaPP(pp.getId());
        DokumenKewangan dkAkhir = penyataPemungutService.findTarikhAkhirPP(pp.getId());

        tarikhMula = sdf.format(dkMula.getTarikhTransaksi());
        tarikhAkhir = sdf.format(dkAkhir.getTarikhTransaksi());
        senaraiPenyata = penyataPemungutService.paparPenyata(pp.getNoPenyata(), pp.getCaraBayar());
        totalAmaun = penyataPemungutService.sumAmaunKeseluruhanPP(pp.getNoPenyata(), pp.getCaraBayar());
        return new JSP("ispeks/sedia_pp.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        if (StringUtils.isEmpty(bank)) {
            addSimpleError("Sila Pilih Bank.");
        } else {
            pp = simpanData(pp, pengguna);
            senaraiPenyata = penyataPemungutService.paparPenyata(pp.getNoPenyata(), pp.getCaraBayar());
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        return new RedirectResolution(SediaPenyataPemungutActionBean.class, "showForm").addParameter("id", pp.getId());

    }

    public PenyataPemungut simpanData(PenyataPemungut pp, Pengguna pengguna) throws ParseException {
        String noAkaunBankPembayar[] = getContext().getRequest().getParameterValues("noAkaunBankPembayar");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AkaunBank akaunBank = akaunBankDAO.findById(bank);
        namaBank = akaunBank.getNamaBank();
        noAkaunBank = akaunBank.getNoAkaun();
        pp.setNamaBank(namaBank);
        pp.setNoAkaunBank(noAkaunBank);
        pp.setAkaunBank(akaunBank);
        pp.setPenyedia(pengguna.getNama());
        pp.setJawatanPenyedia(pengguna.getJawatan());
        pp.setTarikhSedia(new Date());
        pp.setTarikhMula(sdf.parse(tarikhMula));
        pp.setTarikhAkhir(sdf.parse(tarikhAkhir));
        if (StringUtils.isNotBlank(tarikhBank)) {
            pp.setTarikhBank(sdf.parse(tarikhBank));
        }
        pp = penyataPemungutService.simpanPenyata(pp);
        return pp;
    }

    public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        pp = simpanData(pp, pengguna);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("PPSEM");
        t.setKodPeringkat(kodPeringkat);
//        t.setNoRef(pp.getNoPenyata());
        t.setTarikhTerima(new Date());
        penyataPemungutService.simpanTugasanIspeks(t);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public Resolution batal() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        penyataPemungutService.batalPP(pp, pengguna);
        penyataPemungutService.deleteTugasan(t);
        addSimpleMessage("Penyata Pemungut telah Berjaya diBatalkan");
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public AkaunBankDAO getAkaunBankDAO() {
        return akaunBankDAO;
    }

    public void setAkaunBankDAO(AkaunBankDAO akaunBankDAO) {
        this.akaunBankDAO = akaunBankDAO;
    }

    public List<PenyataPemungutForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<PenyataPemungutForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public Long getIdPenyataPemungut() {
        return idPenyataPemungut;
    }

    public void setIdPenyataPemungut(Long idPenyataPemungut) {
        this.idPenyataPemungut = idPenyataPemungut;
    }

    public Long getIdTugasan() {
        return idTugasan;
    }

    public void setIdTugasan(Long idTugasan) {
        this.idTugasan = idTugasan;
    }

    public PenyataPemungutService getPenyataPemungutService() {
        return penyataPemungutService;
    }

    public void setPenyataPemungutService(PenyataPemungutService penyataPemungutService) {
        this.penyataPemungutService = penyataPemungutService;
    }

    public KodPeringkatIspeksDAO getKodPeringkatIspeksDAO() {
        return kodPeringkatIspeksDAO;
    }

    public void setKodPeringkatIspeksDAO(KodPeringkatIspeksDAO kodPeringkatIspeksDAO) {
        this.kodPeringkatIspeksDAO = kodPeringkatIspeksDAO;
    }

    public PenyataPemungutDAO getPenyataPemungutDAO() {
        return penyataPemungutDAO;
    }

    public void setPenyataPemungutDAO(PenyataPemungutDAO penyataPemungutDAO) {
        this.penyataPemungutDAO = penyataPemungutDAO;
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

    public BigDecimal getTotalAmaun() {
        return totalAmaun;
    }

    public void setTotalAmaun(BigDecimal totalAmaun) {
        this.totalAmaun = totalAmaun;
    }

}

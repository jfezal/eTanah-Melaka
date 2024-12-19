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
import etanah.service.ispeks.IDDiSpeksService;
import etanah.service.ispeks.IspeksStatus;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/hantar_penyata_pemungut")
public class HantarPenyataPemungutActionBean extends AbleActionBean {

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
    @Inject
    IDDiSpeksService iDDiSpeksService;
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
        return new JSP("ispeks/hantar_pp.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        pp = simpanData(pp, pengguna);
        senaraiPenyata = penyataPemungutService.paparPenyata(pp.getNoPenyata(), pp.getCaraBayar());
        addSimpleMessage("Maklumat Berjaya Disimpan");

        return new RedirectResolution(HantarPenyataPemungutActionBean.class, "showForm").addParameter("id", pp.getId());

    }

    public PenyataPemungut simpanData(PenyataPemungut pp, Pengguna pengguna) throws ParseException {
        String noAkaunBankPembayar[] = getContext().getRequest().getParameterValues("noAkaunBankPembayar");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AkaunBank akaunBank = akaunBankDAO.findById(bank);
        namaBank = akaunBank.getNamaBank();
        noAkaunBank = akaunBank.getNoAkaun();
        pp.setNoSlipBank(noSlipBank);
        pp.setAkaunBank(akaunBank);
        if (StringUtils.isNotBlank(tarikhBank)) {
            pp.setTarikhBank(sdf.parse(tarikhBank));
        }
        pp = penyataPemungutService.simpanPenyata(pp);
        return pp;
    }

    public Resolution selesai() throws IOException, ParseException, MessagingException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        simpanData(pp, pengguna);
        penyataPemungutService.deleteTugasan(t);
        IspeksStatus status = iDDiSpeksService.janaIddPP(pp.getId(), pp.getKodCaw(), pengguna);
        if ("Y".equals(status.getStatusGPG()) && "Y".equals(status.getStatusIdd()) && "Y".equals(status.getStatusSftp())) {
            addSimpleMessage("Proses telah berjaya dan dihantar");

        } else {
        }
//        addSimpleError("Terdapat Masalah Teknikal");
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

    public PenyataPemungutService getPenyataPemungutService() {
        return penyataPemungutService;
    }

    public void setPenyataPemungutService(PenyataPemungutService penyataPemungutService) {
        this.penyataPemungutService = penyataPemungutService;
    }

    public PenyataPemungutDAO getPenyataPemungutDAO() {
        return penyataPemungutDAO;
    }

    public void setPenyataPemungutDAO(PenyataPemungutDAO penyataPemungutDAO) {
        this.penyataPemungutDAO = penyataPemungutDAO;
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

    public Long getIdPenyataPemungut() {
        return idPenyataPemungut;
    }

    public void setIdPenyataPemungut(Long idPenyataPemungut) {
        this.idPenyataPemungut = idPenyataPemungut;
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

    public List<AkaunBank> getSenaraiBank() {
        return senaraiBank;
    }

    public void setSenaraiBank(List<AkaunBank> senaraiBank) {
        this.senaraiBank = senaraiBank;
    }

    public List<PenyataPemungutForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<PenyataPemungutForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
    }

}

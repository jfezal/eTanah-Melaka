/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.TugasanIspeksDAO;
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

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/lulus_penyata_pemungut")
public class LulusPenyataPemungutActionBean extends AbleActionBean {

    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
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
    String tarikhBank;
    String bank;
    List<AkaunBank> senaraiBank;
    List<PenyataPemungutForm> senaraiPenyata;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    BigDecimal totalAmaun;

    @DefaultHandler
    public Resolution showForm() {
        String id = getContext().getRequest().getParameter("id");
        PenyataPemungut pp = penyataPemungutDAO.findById(Long.parseLong(id));
        TugasanIspeks t = penyataPemungutService.findByIdRef(pp.getId());
        idTugasan = t.getId();
        idPenyataPemungut = pp.getId();
        if (pp.getTarikhMula() != null) {
            tarikhMula = sdf.format(pp.getTarikhMula());
        }
        if (pp.getTarikhAkhir() != null) {
            tarikhAkhir = sdf.format(pp.getTarikhAkhir());
        }
        noSlipBank = pp.getNoSlipBank();
        namaBank = pp.getNamaBank();
        noAkaunBank = pp.getNoAkaunBank();
        noPenyata = pp.getNoPenyata();
        if (pp.getTarikhBank() != null) {
            tarikhBank = sdf.format(pp.getTarikhBank());
        }

        senaraiPenyata = penyataPemungutService.paparPenyata(pp.getNoPenyata(), pp.getCaraBayar());
        totalAmaun = penyataPemungutService.sumAmaunKeseluruhanPP(pp.getNoPenyata(), pp.getCaraBayar());

        return new JSP("ispeks/lulus_pp.jsp");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        simpanData(pp, pengguna);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new RedirectResolution(LulusPenyataPemungutActionBean.class, "showForm").addParameter("id", pp.getId());

    }

    public PenyataPemungut simpanData(PenyataPemungut pp, Pengguna pengguna) throws ParseException {

        pp.setPelulus(pengguna.getNama());
        pp.setJawatanPelulus(pengguna.getJawatan());
        pp.setNoSlipBank(noSlipBank);        
        pp.setTarikhLulus(new Date());
        pp.setTarikhHantar(new Date());
        pp = penyataPemungutService.simpanPenyata(pp);
        return pp;
    }

        public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        PenyataPemungut pp = penyataPemungutDAO.findById(idPenyataPemungut);
        pp = simpanData(pp, pengguna);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("PPHTR");
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

    public IDDiSpeksService getiDDiSpeksService() {
        return iDDiSpeksService;
    }

    public void setiDDiSpeksService(IDDiSpeksService iDDiSpeksService) {
        this.iDDiSpeksService = iDDiSpeksService;
    }

    public List<PenyataPemungutForm> getSenaraiPenyata() {
        return senaraiPenyata;
    }

    public void setSenaraiPenyata(List<PenyataPemungutForm> senaraiPenyata) {
        this.senaraiPenyata = senaraiPenyata;
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

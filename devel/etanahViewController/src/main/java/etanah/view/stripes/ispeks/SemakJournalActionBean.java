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
import etanah.dao.JournalDAO;
import etanah.dao.KodJenisJurnalDAO;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodVotDanaDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.KodTransaksi;
import etanah.model.KodVotDana;
import etanah.model.Pengguna;
import etanah.model.ispek.AkaunBank;
import etanah.model.ispek.Journal;
import etanah.model.ispek.JournalDetail;
import etanah.model.ispek.KodJenisJurnal;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.PenyataPemungutTunai;
import etanah.service.ispeks.JournalService;
import etanah.service.ispeks.PenyataPemungutService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@UrlBinding("/ispeks/semak_journal")
public class SemakJournalActionBean extends AbleActionBean {

    @Inject
    JournalService journalService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    JournalDAO journalDAO;
    @Inject
    KodVotDanaDAO kodVotDanaDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    AkaunBankDAO akaunBankDAO;
    @Inject
    KodJenisJurnalDAO kodJenisJurnalDAO;
    Long idTugasan;
    Long idJournal;
    String noJournal;
    String noRujukan;
    String perihal;
    String jenisJournal;
    String linkJournal;
    List<KodVotDana> listKodVotDana = new ArrayList<KodVotDana>();
    List<KodTransaksi> listKodTransaksi = new ArrayList<KodTransaksi>();
    List<KodJenisJurnal> listKodJurnal = new ArrayList<KodJenisJurnal>();
    BigDecimal debit;
    BigDecimal kredit;
    String trans;
    String vot;
    String tarikhRujukan;
    List<JournalForm> senaraiJournal;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Journal journalBatal;

    @DefaultHandler
    public Resolution showForm() {
        listKodJurnal = kodJenisJurnalDAO.findAll();
        String id = getContext().getRequest().getParameter("id");
        listKodVotDana = kodVotDanaDAO.findAll();
        listKodTransaksi = kodTransaksiDAO.findAll();
        if (StringUtils.isNotBlank(id)) {
            Journal j = journalDAO.findById(Long.parseLong(id));
            TugasanIspeks t = journalService.findByIdRef(j.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            noJournal = j.getNoJournal();
            noRujukan = j.getNoRujukan();
            jenisJournal = j.getJenisJournal();
            tarikhRujukan = sdf.format(j.getTarikhRujukan());
            perihal = j.getPerihal();
            idTugasan = t.getId();
            idJournal = j.getId();

            linkJournal = "";
            journalBatal = j.getJournalBatal();
            senaraiJournal = journalService.paparPenyata(j.getNoJournal());
        }
        return new JSP("ispeks/semak_journal.jsp");
    }

    public Resolution paparBatalJournal() throws ParseException {
        String noJournal = getContext().getRequest().getParameter("noJournal");
        journalBatal = journalService.findByNoJurnal(noJournal);

        senaraiJournal = journalService.paparPenyata(journalBatal.getNoJournal());
        return new JSP("ispeks/journal_batal.jsp").addParameter("popup", true);
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Journal j = journalDAO.findById(idJournal);
        simpanData(j, pengguna);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new RedirectResolution(SemakJournalActionBean.class, "showForm").addParameter("id", j.getId());
    }

    public Journal simpanData(Journal pp, Pengguna pengguna) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        pp.setPenyemak(pengguna.getNama());
        pp.setJawatanPenyemak(pengguna.getJawatan());
        pp.setTarikhSemak(new Date());
        pp = journalService.simpanJournal(pp);
        return pp;
    }

    public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Journal pp = journalDAO.findById(idJournal);
        pp = simpanData(pp, pengguna);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("JRLUL");
        t.setKodPeringkat(kodPeringkat);
//        t.setNoRef(pp.getNoPenyata());
        t.setTarikhTerima(new Date());
        journalService.simpanTugasanIspeks(t);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public JournalService getJournalService() {
        return journalService;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    public KodPeringkatIspeksDAO getKodPeringkatIspeksDAO() {
        return kodPeringkatIspeksDAO;
    }

    public void setKodPeringkatIspeksDAO(KodPeringkatIspeksDAO kodPeringkatIspeksDAO) {
        this.kodPeringkatIspeksDAO = kodPeringkatIspeksDAO;
    }

    public JournalDAO getJournalDAO() {
        return journalDAO;
    }

    public void setJournalDAO(JournalDAO journalDAO) {
        this.journalDAO = journalDAO;
    }

    public KodVotDanaDAO getKodVotDanaDAO() {
        return kodVotDanaDAO;
    }

    public void setKodVotDanaDAO(KodVotDanaDAO kodVotDanaDAO) {
        this.kodVotDanaDAO = kodVotDanaDAO;
    }

    public KodTransaksiDAO getKodTransaksiDAO() {
        return kodTransaksiDAO;
    }

    public void setKodTransaksiDAO(KodTransaksiDAO kodTransaksiDAO) {
        this.kodTransaksiDAO = kodTransaksiDAO;
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

    public String getNoJournal() {
        return noJournal;
    }

    public void setNoJournal(String noJournal) {
        this.noJournal = noJournal;
    }

    public Long getIdTugasan() {
        return idTugasan;
    }

    public void setIdTugasan(Long idTugasan) {
        this.idTugasan = idTugasan;
    }

    public Long getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Long idJournal) {
        this.idJournal = idJournal;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getJenisJournal() {
        return jenisJournal;
    }

    public void setJenisJournal(String jenisJournal) {
        this.jenisJournal = jenisJournal;
    }

    public String getLinkJournal() {
        return linkJournal;
    }

    public void setLinkJournal(String linkJournal) {
        this.linkJournal = linkJournal;
    }

    public List<KodVotDana> getListKodVotDana() {
        return listKodVotDana;
    }

    public void setListKodVotDana(List<KodVotDana> listKodVotDana) {
        this.listKodVotDana = listKodVotDana;
    }

    public List<KodTransaksi> getListKodTransaksi() {
        return listKodTransaksi;
    }

    public void setListKodTransaksi(List<KodTransaksi> listKodTransaksi) {
        this.listKodTransaksi = listKodTransaksi;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getVot() {
        return vot;
    }

    public void setVot(String vot) {
        this.vot = vot;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public List<JournalForm> getSenaraiJournal() {
        return senaraiJournal;
    }

    public void setSenaraiJournal(List<JournalForm> senaraiJournal) {
        this.senaraiJournal = senaraiJournal;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public Journal getJournalBatal() {
        return journalBatal;
    }

    public void setJournalBatal(Journal journalBatal) {
        this.journalBatal = journalBatal;
    }

    public List<KodJenisJurnal> getListKodJurnal() {
        return listKodJurnal;
    }

    public void setListKodJurnal(List<KodJenisJurnal> listKodJurnal) {
        this.listKodJurnal = listKodJurnal;
    }

}

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
import etanah.dao.KodTransIspeksDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodVotDanaDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.KodTransIspeks;
import etanah.model.KodTransaksi;
import etanah.model.KodVotDana;
import etanah.model.Pengguna;
import etanah.model.ispek.Journal;
import etanah.model.ispek.JournalDetail;
import etanah.model.ispek.KodJenisJurnal;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.TugasanIspeks;
import etanah.service.ispeks.JournalService;
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
@UrlBinding("/ispeks/sedia_journal")
public class SediaJournalActionBean extends AbleActionBean {

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
    KodTransIspeksDAO kodTransIspeksDAO;
    @Inject
    KodJenisJurnalDAO kodJenisJurnalDAO;
    String noPenyata;
    Long idTugasan;
    Long idJournal;
    String noRujukan;
    String perihal;
    String jenisJournal;
    String linkJournal;
    List<KodVotDana> listKodVotDana = new ArrayList<KodVotDana>();
    List<KodTransIspeks> listKodTransaksi = new ArrayList<KodTransIspeks>();
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
        String id = getContext().getRequest().getParameter("id");
        listKodJurnal = kodJenisJurnalDAO.findAll();
        listKodVotDana = kodVotDanaDAO.findAll();
        listKodTransaksi = journalService.findAll();
        if (StringUtils.isNotBlank(id)) {
            Journal j = journalDAO.findById(Long.parseLong(id));
            TugasanIspeks t = journalService.findByIdRef(j.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            noPenyata = j.getNoJournal();
            noRujukan = j.getNoRujukan();
            jenisJournal = j.getJenisJournal();
            tarikhRujukan = j.getTarikhRujukan() != null ? sdf.format(j.getTarikhRujukan()) : "";
            perihal = j.getPerihal();
            idTugasan = t.getId();
            idJournal = j.getId();

            linkJournal = "";
            journalBatal = j.getJournalBatal();
            senaraiJournal = journalService.paparPenyata(j.getNoJournal());
        }
        return new JSP("ispeks/sedia_journal.jsp");
    }

    public Resolution filterKodTrans() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String id = getContext().getRequest().getParameter("id");
        String vot = getContext().getRequest().getParameter("votdana");
        listKodJurnal = kodJenisJurnalDAO.findAll();

        listKodVotDana = kodVotDanaDAO.findAll();
        if (StringUtils.isNotBlank(vot)) {
            listKodTransaksi = journalService.findByVotDana(vot);
        }
        if (StringUtils.isNotBlank(id)) {
            Journal j = journalDAO.findById(Long.parseLong(id));
            if (StringUtils.isNotBlank(jenisJournal)) {
                j.setJenisJournal(jenisJournal);
            }
            if (StringUtils.isNotBlank(noRujukan)) {
                j.setNoRujukan(noRujukan);

            }
            if (StringUtils.isNotBlank(tarikhRujukan)) {
                j.setTarikhRujukan(sdf.parse(tarikhRujukan));
            }
            if (StringUtils.isNotBlank(perihal)) {
                j.setPerihal(perihal);
            }
            j = simpanData(j, pengguna);
            TugasanIspeks t = journalService.findByIdRef(j.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            noPenyata = j.getNoJournal();
            noRujukan = j.getNoRujukan();
            jenisJournal = j.getJenisJournal();
            tarikhRujukan = j.getTarikhRujukan() != null ? sdf.format(j.getTarikhRujukan()) : "";
            perihal = j.getPerihal();
            idTugasan = t.getId();
            idJournal = j.getId();
            journalBatal = j.getJournalBatal();
            linkJournal = "";

            senaraiJournal = journalService.paparPenyata(j.getNoJournal());
        }
        return new JSP("ispeks/sedia_journal.jsp");
    }

    public Resolution baru() {
        listKodJurnal = kodJenisJurnalDAO.findAll();

        return new JSP("ispeks/cipta_journal.jsp");
    }

    public Resolution simpanCipta() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = null;
        Journal j = new Journal();
        j.setNoJournal(journalService.generatNoPenyata(pengguna.getKodCawangan().getKod()));
        j.setKodCawangan(pengguna.getKodCawangan());
        j.setTarikhJana(new Date());
        j.setNoRujukan(noRujukan);
        j.setPerihal(perihal);
        j.setJenisJournal(jenisJournal);
        j.setTarikhRujukan(sdf.parse(tarikhRujukan));
        j = simpanData(j, pengguna);
        t = new TugasanIspeks();
        t.setKodCaw(pengguna.getKodCawangan());
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("JRSED");
        t.setKodPeringkat(kodPeringkat);
        t.setNoRef(j.getId() + "");
        t.setTarikhTerima(new Date());
        journalService.simpanTugasanIspeks(t);
                addSimpleMessage("Maklumat Berjaya Disimpan");

        return new RedirectResolution(SediaJournalActionBean.class, "showForm").addParameter("id", j.getId());

    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = null;
        if (idTugasan != null) {
            t = tugasanIspeksDAO.findById(idTugasan);
        } else {
            t = new TugasanIspeks();
        }
        Journal j = journalDAO.findById(idJournal);
        if (StringUtils.isNotBlank(jenisJournal)) {
            j.setJenisJournal(jenisJournal);
        }
        if (StringUtils.isNotBlank(noRujukan)) {
            j.setNoRujukan(noRujukan);

        }
        if (StringUtils.isNotBlank(tarikhRujukan)) {
            j.setTarikhRujukan(sdf.parse(tarikhRujukan));
        }
        if (StringUtils.isNotBlank(perihal)) {
            j.setPerihal(perihal);
        }
        JournalDetail jd = new JournalDetail();
        BigDecimal a = new BigDecimal(BigInteger.ZERO);
        if (debit != null) {
            a = debit;
            jd.setJenisAmaun("D");
        }
        if (kredit != null) {
            jd.setJenisAmaun("K");
            a = kredit;
        }
        jd.setAmaun(a);
        jd.setJournal(j);
        KodTransaksi kodTransaksi = kodTransaksiDAO.findById(trans);
        jd.setKodTransaksi(kodTransaksi);
        KodVotDana votDana = kodVotDanaDAO.findById(vot);
        jd.setVotDana(votDana);
        j.setJawatanPenyedia(pengguna.getJawatan());
        j.setPenyedia(pengguna.getNama());
        j.setTarikhSedia(new Date());
        j = simpanData(j, pengguna);
        jd = journalService.simpanJournalDetail(jd);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("JRSED");
        t.setKodPeringkat(kodPeringkat);
//        t.setNoRef(pp.getNoPenyata());
        t.setTarikhTerima(new Date());
        journalService.simpanTugasanIspeks(t);
        senaraiJournal = journalService.paparPenyata(j.getNoJournal());
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new RedirectResolution(SediaJournalActionBean.class, "showForm").addParameter("id", j.getId());
    }

    public Journal simpanData(Journal pp, Pengguna pengguna) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        pp = journalService.simpanJournal(pp);
        return pp;
    }

    public Resolution selesai() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        TugasanIspeks t = tugasanIspeksDAO.findById(idTugasan);
        Journal pp = journalDAO.findById(idJournal);
        pp = simpanData(pp, pengguna);
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("JRSEM");
        t.setKodPeringkat(kodPeringkat);
//        t.setNoRef(pp.getNoPenyata());
        t.setTarikhTerima(new Date());
        journalService.simpanTugasanIspeks(t);
        return new RedirectResolution(TugasanIspeksActionBean.class);
    }

    public Resolution paparBatalJournal() throws ParseException {
        String noJournal = getContext().getRequest().getParameter("noJournal");
        journalBatal = journalService.findByNoJurnal(noJournal);
        
        senaraiJournal = journalService.paparPenyata(journalBatal.getNoJournal());
        return new JSP("ispeks/journal_batal.jsp").addParameter("popup", true);
    }

    public AkaunBankDAO getAkaunBankDAO() {
        return akaunBankDAO;
    }

    public void setAkaunBankDAO(AkaunBankDAO akaunBankDAO) {
        this.akaunBankDAO = akaunBankDAO;
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

    public Long getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Long idJournal) {
        this.idJournal = idJournal;
    }

    public String getLinkJournal() {
        return linkJournal;
    }

    public void setLinkJournal(String linkJournal) {
        this.linkJournal = linkJournal;
    }

    public List<JournalForm> getSenaraiJournal() {
        return senaraiJournal;
    }

    public void setSenaraiJournal(List<JournalForm> senaraiJournal) {
        this.senaraiJournal = senaraiJournal;
    }

    public List<KodVotDana> getListKodVotDana() {
        return listKodVotDana;
    }

    public void setListKodVotDana(List<KodVotDana> listKodVotDana) {
        this.listKodVotDana = listKodVotDana;
    }

    public List<KodTransIspeks> getListKodTransaksi() {
        return listKodTransaksi;
    }

    public void setListKodTransaksi(List<KodTransIspeks> listKodTransaksi) {
        this.listKodTransaksi = listKodTransaksi;
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

    public List<KodJenisJurnal> getListKodJurnal() {
        return listKodJurnal;
    }

    public void setListKodJurnal(List<KodJenisJurnal> listKodJurnal) {
        this.listKodJurnal = listKodJurnal;
    }

    public Journal getJournalBatal() {
        return journalBatal;
    }

    public void setJournalBatal(Journal journalBatal) {
        this.journalBatal = journalBatal;
    }

}

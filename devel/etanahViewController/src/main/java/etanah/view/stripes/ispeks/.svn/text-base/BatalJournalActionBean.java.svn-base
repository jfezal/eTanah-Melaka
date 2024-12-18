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
import etanah.dao.IspeksBatalJurnalDAO;
import etanah.dao.IspeksResitPerbendaharaanDAO;
import etanah.dao.JournalDAO;
import etanah.dao.KodJenisJurnalDAO;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.KodTransIspeksDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodVotDanaDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.KodTransIspeks;
import etanah.model.KodVotDana;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksBatalJurnal;
import etanah.model.ispek.IspeksResitPerbendaharaan;
import etanah.model.ispek.Journal;
import etanah.model.ispek.KodJenisJurnal;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.TugasanIspeks;
import etanah.service.ispeks.IspeksService;
import etanah.service.ispeks.JournalService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@UrlBinding("/ispeks/batal_journal")
public class BatalJournalActionBean extends AbleActionBean {

    @Inject
    IspeksService ispeksService;
    @Inject
    IspeksBatalJurnalDAO ispeksBatalJurnalDAO;
    List<IspeksBatalJurnal> listBatalJurnal;
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
    String idJournal;
    String noRujukan;
    String perihal;
    String jenisJournal;
    String linkJournal;
    List<KodVotDana> listKodVotDana = new ArrayList<KodVotDana>();
    List<KodTransIspeks> listKodTransaksi = new ArrayList<KodTransIspeks>();
    List<KodJenisJurnal>listKodJurnal = new ArrayList<KodJenisJurnal>();
    BigDecimal debit;
    BigDecimal kredit;
    String trans;
    String vot;
    String tarikhRujukan;
    List<JournalForm> senaraiJournal;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        listBatalJurnal = ispeksBatalJurnalDAO.findAll();
        return new JSP("ispeks/batal_journal.jsp");
    }
    
    public Resolution simpanCipta() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    
        TugasanIspeks t = null;
        Journal j = new Journal();
        j.setNoJournal(journalService.generatNoPenyata(pengguna.getKodCawangan().getKod()));
        j.setKodCawangan(pengguna.getKodCawangan());
        j.setTarikhJana(new Date());
        j.setPerihal("");
        Journal journalBatal = journalService.findByNoJurnal(idJournal);
        j.setJournalBatal(journalBatal);
        j = simpanData(j, pengguna);
        t = new TugasanIspeks();
        t.setKodCaw(pengguna.getKodCawangan());
        KodPeringkatIspeks kodPeringkat = new KodPeringkatIspeks();
        kodPeringkat = kodPeringkatIspeksDAO.findById("JRSED");
        t.setKodPeringkat(kodPeringkat);
        t.setNoRef(j.getId() + "");
        t.setTarikhTerima(new Date());
        journalService.simpanTugasanIspeks(t);
        return new RedirectResolution(SediaJournalActionBean.class, "showForm").addParameter("id", j.getId());

    }
    public Journal simpanData(Journal pp, Pengguna pengguna) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        pp = journalService.simpanJournal(pp);
        return pp;
    }
    public List<IspeksBatalJurnal> getListBatalJurnal() {
        return listBatalJurnal;
    }

    public void setListBatalJurnal(List<IspeksBatalJurnal> listBatalJurnal) {
        this.listBatalJurnal = listBatalJurnal;
    }

    public String getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(String idJournal) {
        this.idJournal = idJournal;
    }


    
    
    
}

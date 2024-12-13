/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.BilDAO;
import etanah.dao.JournalDAO;
import etanah.dao.JournalDetailDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.PenyataResitDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.DokumenKewangan;
import etanah.model.KodCawangan;
import etanah.model.KodTransIspeks;
import etanah.model.KodTransaksi;
import etanah.model.ispek.Bil;
import etanah.model.ispek.Journal;
import etanah.model.ispek.JournalDetail;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.PenyataResit;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.IddJurnalContent;
import etanah.model.view.IddJurnalHeader;
import etanah.model.view.IddPPContent;
import etanah.model.view.IddPPContentCekDraf;
import etanah.model.view.IddPPHeader;
import etanah.model.view.PenyataPemungutCekBankDraft;
import etanah.model.view.PenyataPemungutTunai;
import etanah.model.view.ResitPenyataPemungut;
import etanah.sequence.GeneratorPenyataIspeks;
import etanah.sequence.GeneratorPenyataJurnalIspeks;
import etanah.view.stripes.ispeks.JournalForm;
import etanah.view.stripes.ispeks.PenyataPemungutForm;
import etanah.view.stripes.ispeks.TugasanIspeksForm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class JournalService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PenyataResitDAO penyataResitDAO;
    @Inject
    GeneratorPenyataJurnalIspeks generatorPenyataJurnalIspeks;
    @Inject
    JournalDAO journalDAO;
    @Inject
    JournalDetailDAO journalDetailDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    @Inject
    BilDAO bilDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<ResitPenyataPemungut> testView() {
        String query = "Select p FROM etanah.model.view.ResitPenyataPemungut p";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<ResitPenyataPemungut> paparRekod(String caraBayar, String kodCaw, Date tarikh, String idPenggunaKaunter, Integer limit) {
        String query = "Select p FROM etanah.model.view.ResitPenyataPemungut p ";
        if (StringUtils.isNotBlank(caraBayar) || StringUtils.isNotBlank(kodCaw) || tarikh != null || StringUtils.isNotBlank(idPenggunaKaunter) || limit == 0) {
            query = query + "where ";
            if (StringUtils.isNotBlank(caraBayar)) {
                query = query + "p.jnsBayar = :caraBayar and ";
            }
            if (StringUtils.isNotBlank(kodCaw)) {
                query = query + "p.kodCaw = :kodCaw  ";
            }
            if (tarikh != null) {
                query = query + " and trim(p.trhResit) =:tarikh";
            }
            if (StringUtils.isNotBlank(idPenggunaKaunter)) {
                query = query + " and p.idPenggunaKaunter =:idPenggunaKaunter ";
            }

        }
        Query q = sessionProvider.get().createQuery(query);
        if (StringUtils.isNotBlank(caraBayar)) {
            q.setString("caraBayar", caraBayar);
        }
        if (StringUtils.isNotBlank(kodCaw)) {
            q.setString("kodCaw", kodCaw);
        }
        if (tarikh != null) {
            q.setDate("tarikh", tarikh);
        }
        if (StringUtils.isNotBlank(idPenggunaKaunter)) {
            q.setString("idPenggunaKaunter", idPenggunaKaunter);
        }
        if (limit != null) {
            q.setFirstResult(0);
            q.setMaxResults(limit);

        }
        return q.list();
    }

    public String generatNoPenyata(String kodCaw) {
        KodCawangan kodCawangan = kodCawanganDAO.findById(kodCaw);
        String no = generatorPenyataJurnalIspeks.generate("", kodCawangan, null);
        return no;
    }

    @Transactional
    public void simpanTugasanIspeks(TugasanIspeks t) {
        tugasanIspeksDAO.save(t);
    }

    public List<TugasanIspeksForm> paparTugasan(String caw) {
        List<TugasanIspeksForm> list = new ArrayList<TugasanIspeksForm>();
        String query = "Select p FROM etanah.model.ispek.TugasanIspeks p ";
        Query q = sessionProvider.get().createQuery(query);
//        q.setString("noPenyata", noPenyata);
        for (int i = 0; i < q.list().size(); i++) {
            TugasanIspeks t = new TugasanIspeks();
            t = (TugasanIspeks) q.list().get(i);
            TugasanIspeksForm form = new TugasanIspeksForm();
            form.setId(setIdByKategori(t.getNoRef(), t.getKodPeringkat()));
            form.setPeringkat(t.getKodPeringkat().getNama());
            form.setUrl(t.getKodPeringkat().getUrl() + t.getNoRef());
            form.setTarikh(sdf.format(t.getTarikhTerima()));
            list.add(form);
        }
        return list;
    }

    private String setIdByKategori(String noRef, KodPeringkatIspeks kodPeringkat) {
        String id = "";
        if (kodPeringkat.getKat().equals("PP")) {
            PenyataPemungut pp = penyataPemungutDAO.findById(Long.parseLong(noRef));
            id = pp.getNoPenyata();
        } else if (kodPeringkat.getKat().equals("BIL")) {
            Bil bil = bilDAO.findById(Long.parseLong(noRef));
            if (StringUtils.isEmpty(bil.getNoBil())) {
                id = bil.getId() + "";
            } else {
                id = bil.getNoBil();
            }
        } else if (kodPeringkat.getKat().equals("JOR")) {
            Journal j = journalDAO.findById(Long.parseLong(noRef));
            id = j.getNoJournal();
        }
        return id;
    }

    @Transactional
    public void deleteTugasan(TugasanIspeks t) {
        tugasanIspeksDAO.delete(t);
    }

    public TugasanIspeks findByIdRef(Long id) {
        String query = "Select p FROM etanah.model.ispek.TugasanIspeks p where "
                + "p.noRef =:id and p.kodPeringkat.kat = 'JOR'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (TugasanIspeks) q.uniqueResult();
    }

    IddPPHeader findHeader(String noPenyata) {
        String query = "Select p FROM etanah.model.view.IddPPHeader p "
                + "where p.noPenyata = :noPenyata";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyata", noPenyata);
        return (IddPPHeader) q.uniqueResult();
    }

    List<IddPPContent> findContent(String noPenyata) {
        String query = "Select p FROM etanah.model.view.IddPPContent p "
                + "where p.noPenyata = :noPenyata";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyata", noPenyata);
        return q.list();
    }

    List<IddPPContentCekDraf> findContentCekDraf(String noPenyata) {
        String query = "Select p FROM etanah.model.view.IddPPContentCekDraf p "
                + "where p.noPenyata = :noPenyata";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyata", noPenyata);
        return q.list();
    }
    
    IddJurnalHeader findHeaderJurnal(String noJournal) {
        String query = "Select p FROM etanah.model.view.IddJurnalHeader p "
                + "where p.noJournal = :noJournal";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noJournal", noJournal);
        return (IddJurnalHeader) q.uniqueResult();
    }

    List<IddJurnalContent> findContentJurnal(String noJournal) {
        String query = "Select p FROM etanah.model.view.IddJurnalContent p "
                + "where p.noJournal = :noJournal";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noJournal", noJournal);
        return q.list();
    }

    @Transactional
    public Journal simpanJournal(Journal pp) {
        return journalDAO.saveOrUpdate(pp);
    }

    public List<JournalForm> paparPenyata(String noJournal) {
        List<JournalForm> list = new ArrayList<JournalForm>();

        String query = "Select p FROM etanah.model.ispek.JournalDetail p "
                + "where p.journal.noJournal = :noJournal";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noJournal", noJournal);

        for (int i = 0; i < q.list().size(); i++) {
            JournalDetail t = (JournalDetail) q.list().get(i);
            JournalForm f = new JournalForm();
            f.setKodTransaksi(t.getKodTransaksi());
            f.setKodVotDana(t.getVotDana());
            if (t.getJenisAmaun().equals("K")) {
                f.setKredit(t.getAmaun());
            } else {
                f.setDebit(t.getAmaun());

            }
            list.add(f);
        }

        return list;
    }

    @Transactional
    public JournalDetail simpanJournalDetail(JournalDetail jd) {
        return journalDetailDAO.saveOrUpdate(jd);
    }

    public List<KodTransIspeks> findByVotDana(String vot) {

        String query = "Select p FROM etanah.model.KodTransIspeks p "
                + "where p.kodTransaksi in (Select k FROM etanah.model.KodTransaksi k ) and p.kodVotDana.id = :vot";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("vot", vot);    
    return q.list();
    }
    
    public List<KodTransIspeks> findAll() {

        String query = "Select p FROM etanah.model.KodTransIspeks p "
                + "where p.kodTransaksi in (Select k FROM etanah.model.KodTransaksi k )";
        Query q = sessionProvider.get().createQuery(query);   
    return q.list();
    }

    public Journal findByNoJurnal(String idJournal) {
String query = "Select p FROM etanah.model.ispek.Journal p "
                + "where p.noJournal = :noJournal";
        Query q = sessionProvider.get().createQuery(query);  
        q.setString("noJournal", idJournal);
return (Journal) q.uniqueResult();
    }

}

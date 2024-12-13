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
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenyataPemungutDAO;
import etanah.dao.PenyataResitDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.DokumenKewangan;
import etanah.model.KodCawangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
import etanah.model.ispek.Bil;
import etanah.model.ispek.Journal;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.PenyataResit;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.IddPPContent;
import etanah.model.view.IddPPContentCekDraf;
import etanah.model.view.IddPPHeader;
import etanah.model.view.PenyataPemungutCekBankDraft;
import etanah.model.view.PenyataPemungutTunai;
import etanah.model.view.ResitPenyataPemungut;
import etanah.sequence.GeneratorPenyataIspeks;
import etanah.view.stripes.ispeks.KategoriForm;
import etanah.view.stripes.ispeks.PenyataPemungutForm;
import etanah.view.stripes.ispeks.SenaraiPPForm;
import etanah.view.stripes.ispeks.TugasanIspeksForm;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PenyataPemungutService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PenyataResitDAO penyataResitDAO;
    @Inject
    JournalDAO journalDAO;
    @Inject
    GeneratorPenyataIspeks generatorNoPenyataIspeks;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;
    @Inject
    BilDAO bilDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    TugasanIspekService tugasanIspekService;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;

    private static final Logger LOG = Logger.getLogger(PenyataPemungutService.class);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<ResitPenyataPemungut> testView() {
        String query = "Select p FROM etanah.model.view.ResitPenyataPemungut p";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<ResitPenyataPemungut> paparRekod(String caraBayar, String kodCaw, Date tarikh, String idPenggunaKaunter, String noKaunter, String modBayaran, String noResit, Integer limit) {
        String query = "Select p FROM etanah.model.view.ResitPenyataPemungut p ";
        if (StringUtils.isNotBlank(caraBayar) || StringUtils.isNotBlank(kodCaw) || tarikh != null || StringUtils.isNotBlank(idPenggunaKaunter) || StringUtils.isNotBlank(noKaunter) || StringUtils.isNotBlank(noResit) || limit == 0) {
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
            if (StringUtils.isNotBlank(noKaunter)) {
                query = query + " and p.noKaunter =:noKaunter ";
            }
            if (StringUtils.isNotBlank(modBayaran)) {
                query = query + " and p.modBayaran =:modBayaran ";
            }
            if (StringUtils.isNotBlank(noResit)) {
                query = query + " and p.idKewDok =:noResit ";
            }

        }
        Query q = sessionProvider.get().createQuery(query + " order by p.trhResit");
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
        if (StringUtils.isNotBlank(noKaunter)) {
            q.setString("noKaunter", noKaunter);
        }
        if (StringUtils.isNotBlank(modBayaran)) {
            q.setString("modBayaran", modBayaran);
        }
        if (StringUtils.isNotBlank(noResit)) {
            q.setString("noResit", noResit);
        }
        if (limit != null) {
            q.setFirstResult(0);
            q.setMaxResults(limit);

        }
        return q.list();
    }

    public List<PenyataPemungutForm> paparPenyata(String noPenyata, String caraBayar) {
        List<PenyataPemungutForm> list = new ArrayList<PenyataPemungutForm>();
        if (caraBayar.equals("T")) {
            String query = "Select p FROM etanah.model.view.PenyataPemungutTunai p "
                    + "where p.pKTunai.noPenyata = :noPenyata";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noPenyata", noPenyata);

            for (int i = 0; i < q.list().size(); i++) {
                PenyataPemungutTunai t = (PenyataPemungutTunai) q.list().get(i);
                PenyataPemungutForm f = new PenyataPemungutForm();
                f.setAmaun(t.getAmaun());
                f.setCaraBayar(t.getCaraBayar());
                f.setCaw(t.getpKTunai().getCaw());
                f.setNoPenyata(t.getpKTunai().getNoPenyata());
                KodTransaksi kodTrans = kodTransaksiDAO.findById(t.getpKTunai().getKodTrans());
                f.setKodTrans(kodTrans);
                list.add(f);
            }
        } else if (caraBayar.equals("C") || caraBayar.equals("CT") || caraBayar.equals("DB") || caraBayar.equals("CL") || caraBayar.equals("KK")) {
            String query = "Select p FROM etanah.model.view.PenyataPemungutCekBankDraft p "
                    + "where p.pPPKCekBankDraft.noPenyata = :noPenyata";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noPenyata", noPenyata);

            List<PenyataPemungutCekBankDraft> l = q.list();
            if (!l.isEmpty()) {
                for (PenyataPemungutCekBankDraft t : l) {
//                PenyataPemungutCekBankDraft t = (PenyataPemungutCekBankDraft) q.list().get(i);
                    PenyataPemungutForm f = new PenyataPemungutForm();
                    f.setAmaun(t.getAmaun());
                    f.setCaraBayar(t.getCaraBayar());
                    f.setCaw(t.getpPPKCekBankDraft().getCaw());
                    f.setNoPenyata(t.getpPPKCekBankDraft().getNoPenyata());
                    KodTransaksi kodTrans = kodTransaksiDAO.findById(t.getpPPKCekBankDraft().getKodTrans());
                    f.setKodTrans(kodTrans);
                    f.setCbAmaun(t.getpPPKCekBankDraft().getCbAmaun());
                    f.setCbBankSwift(t.getpPPKCekBankDraft().getCbBankSwift());
                    f.setCbCaw(t.getCbCaw());
                    f.setCbNoRujukan(t.getpPPKCekBankDraft().getCbNoRuj());
                    list.add(f);
                }
            }

        } else {
            String query = "Select p FROM etanah.model.view.PenyataPemungutCekBankDraft p "
                    + "where p.pPPKCekBankDraft.noPenyata = :noPenyata";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noPenyata", noPenyata);

            List<PenyataPemungutCekBankDraft> l = q.list();
            if (!l.isEmpty()) {
                for (PenyataPemungutCekBankDraft t : l) {
//                PenyataPemungutCekBankDraft t = (PenyataPemungutCekBankDraft) q.list().get(i);
                    PenyataPemungutForm f = new PenyataPemungutForm();
                    f.setAmaun(t.getAmaun());
                    f.setCaraBayar(t.getCaraBayar());
                    f.setCaw(t.getpPPKCekBankDraft().getCaw());
                    f.setNoPenyata(t.getpPPKCekBankDraft().getNoPenyata());
                    KodTransaksi kodTrans = kodTransaksiDAO.findById(t.getpPPKCekBankDraft().getKodTrans());
                    f.setKodTrans(kodTrans);
                    f.setCbAmaun(t.getpPPKCekBankDraft().getCbAmaun());
                    f.setCbBankSwift(t.getpPPKCekBankDraft().getCbBankSwift());
                    f.setCbCaw(t.getCbCaw());
                    f.setCbNoRujukan(t.getpPPKCekBankDraft().getCbNoRuj());
                    list.add(f);
                }
            }

        }
        return list;
    }

    public String generatNoPenyata(String kodCaw) {
        KodCawangan kodCawangan = kodCawanganDAO.findById(kodCaw);
        String no = generatorNoPenyataIspeks.generate("", kodCawangan, null);
        return no;
    }

    @Transactional
    public void simpanPenyataResit(PenyataResit p) {
        penyataResitDAO.save(p);
    }

    @Transactional
    public PenyataPemungut simpanPenyata(PenyataPemungut pp) {
        return penyataPemungutDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void simpanTugasanIspeks(TugasanIspeks t) {
        tugasanIspeksDAO.save(t);
    }

    public List<TugasanIspeksForm> paparTugasan(Pengguna pguna, String caw, String kat) {
        List<TugasanIspeksForm> list = new ArrayList<TugasanIspeksForm>();

        List<TugasanIspeks> listT = tugasanIspekService.findTugasanIspeks(pguna.getIdPengguna(), caw, kat);
        for (TugasanIspeks t : listT) {
//            t = (TugasanIspeks) q.list().get(i);
            TugasanIspeksForm form = new TugasanIspeksForm();
            KategoriForm f = setIdByKategori(t.getNoRef(), t.getKodPeringkat());
            form.setId(f.getId());
            form.setJenisBayar(f.getJenisBayar());
            form.setUrusan(f.getUrusan());
            form.setAmaun(f.getAmaun());
//            form.setUrusan(query);
//            form.setJenisBayar(query);
            form.setKodPeringkat(t.getKodPeringkat().getKod());
            form.setPeringkat(t.getKodPeringkat().getNama());
            form.setUrl(t.getKodPeringkat().getUrl() + t.getNoRef());
            form.setTarikh(sdf.format(t.getTarikhTerima()));
            list.add(form);
        }
        return list;
    }

    private KategoriForm setIdByKategori(String noRef, KodPeringkatIspeks kodPeringkat) {
        KategoriForm form = new KategoriForm();
        String id = "";
        if (kodPeringkat.getKat().equals("PP")) {
            PenyataPemungut pp = penyataPemungutDAO.findById(Long.parseLong(noRef));
            form.setId(pp.getNoPenyata());
            form.setUrusan("Penyata Pemungut");
            form.setJenisBayar(kodCaraBayaranDAO.findById(pp.getCaraBayar()).getNama());
            form.setAmaun(sumAmaunKeseluruhanPP(pp.getNoPenyata(), pp.getCaraBayar()));
        } else if (kodPeringkat.getKat().equals("BIL")) {
            Bil bil = bilDAO.findById(Long.parseLong(noRef));
            if (StringUtils.isEmpty(bil.getNoBil())) {
                form.setId(bil.getId() + "");
                form.setUrusan("Bil");
            } else {
                form.setId(bil.getNoBil());
                form.setUrusan("Bil");
            };
        } else if (kodPeringkat.getKat().equals("JOR")) {
            Journal pp = journalDAO.findById(Long.parseLong(noRef));
            form.setId(pp.getNoJournal());
            form.setUrusan("Jurnal");
        }
        return form;
    }

    @Transactional
    public void deleteTugasan(TugasanIspeks t) {
        tugasanIspeksDAO.delete(t);
    }

    public TugasanIspeks findByIdRef(Long id) {
        String query = "Select p FROM etanah.model.ispek.TugasanIspeks p where "
                + "p.noRef =:id and p.kodPeringkat.kat = 'PP'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (TugasanIspeks) q.uniqueResult();
    }

    public DokumenKewangan findTarikhMulaPP(Long idPenyata) {
        String query = "Select d FROM etanah.model.ispek.PenyataResit p, etanah.model.DokumenKewangan d where "
                + "p.idKewDok = d.idDokumenKewangan and p.idPenyata =:idPenyata order by d.tarikhTransaksi asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPenyata", idPenyata);
        return (DokumenKewangan) q.list().get(0);
    }

    public DokumenKewangan findTarikhAkhirPP(Long idPenyata) {
        String query = "Select d FROM etanah.model.ispek.PenyataResit p, etanah.model.DokumenKewangan d where "
                + "p.idKewDok = d.idDokumenKewangan and p.idPenyata =:idPenyata order by d.tarikhTransaksi desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPenyata", idPenyata);
        return (DokumenKewangan) q.list().get(0);
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

    public BigDecimal sumAmaun(String caraBayar, String kodCaw, Date tarikh, String idPenggunaKaunter, String noKaunter, String modBayaran, String noResit, Integer limit) {
        BigDecimal a = new BigDecimal(BigInteger.ZERO);
        String query = "Select p FROM etanah.model.view.ResitPenyataPemungut p ";
        if (StringUtils.isNotBlank(caraBayar) || StringUtils.isNotBlank(kodCaw) || tarikh != null || StringUtils.isNotBlank(idPenggunaKaunter) || StringUtils.isNotBlank(noKaunter) || StringUtils.isNotBlank(noResit) || limit == 0) {
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
            if (StringUtils.isNotBlank(noKaunter)) {
                query = query + " and p.noKaunter =:noKaunter ";
            }
            if (StringUtils.isNotBlank(modBayaran)) {
                query = query + " and p.modBayaran =:modBayaran ";
            }
            if (StringUtils.isNotBlank(noResit)) {
                query = query + " and p.idKewDok =:noResit ";
            }

        }
        Query q = sessionProvider.get().createQuery(query + " order by p.trhResit");
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
        if (StringUtils.isNotBlank(noKaunter)) {
            q.setString("noKaunter", noKaunter);
        }
        if (StringUtils.isNotBlank(modBayaran)) {
            q.setString("modBayaran", modBayaran);
        }
        if (StringUtils.isNotBlank(noResit)) {
            q.setString("noResit", noResit);
        }

        if (limit != null) {
            q.setFirstResult(0);
            q.setMaxResults(limit);

        }
        List<ResitPenyataPemungut> list = q.list();
        for (ResitPenyataPemungut r : list) {
            a = a.add(r.getAmaun());
        }
        return a;
    }

    public BigDecimal sumAmaunKeseluruhanPP(String noPenyata, String caraBayar) {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        List<PenyataPemungutForm> list = new ArrayList<PenyataPemungutForm>();
        if (caraBayar.equals("T")) {
            String query = "Select sum(p.amaun) FROM etanah.model.view.PenyataPemungutTunai p "
                    + "where p.pKTunai.noPenyata = :noPenyata";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noPenyata", noPenyata);
            sum = (BigDecimal) q.uniqueResult();

        } else {
            String query = "Select sum(p.amaun) FROM etanah.model.view.PenyataPemungutCekBankDraft p "
                    + "where p.pPPKCekBankDraft.noPenyata = :noPenyata";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("noPenyata", noPenyata);
            sum = (BigDecimal) q.uniqueResult();

        }
        return sum;
    }

    public void batalPP(PenyataPemungut pp, Pengguna pengguna) {
        List<PenyataResit> listResit = findPenyataResitByPP(pp.getId());
        for (PenyataResit pf : listResit) {
            deletePenyataResit(pf);
        }
    }

    @Transactional
    public void deletePenyataResit(PenyataResit pr) {
        penyataResitDAO.delete(pr);
    }

    private List<PenyataResit> findPenyataResitByPP(Long id) {
        String query = "Select p FROM etanah.model.ispek.PenyataResit p "
                + "where p.idPenyata = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public List<SenaraiPPForm> paparPenyataPemungut(String noPenyata, String tarikhMula, String tarikhAkhir, String caraBayar, String kodCaw) throws ParseException {
        List<SenaraiPPForm> l = new ArrayList<SenaraiPPForm>();
        String query = "Select p FROM etanah.model.ispek.PenyataPemungut p where p.kodCaw.kod = :kodCaw";
        if (!StringUtils.isEmpty(noPenyata)) {
            query = query + " and p.noPenyata =:noPenyata ";
        }
        if (!StringUtils.isEmpty(tarikhMula)) {
            query = query + " and p.tarikhJana Between :tarikhMula AND :tarikhAkhir ";
        }
        if (!StringUtils.isEmpty(caraBayar)) {
            query += "";
        }
        //untuk sorting
        query = query + " order by p.noPenyata asc ";

        Query q = sessionProvider.get().createQuery(query);
        if (!StringUtils.isEmpty(noPenyata)) {
            q.setString("noPenyata", noPenyata);
        }
        if (!StringUtils.isEmpty(tarikhMula)) {
            q.setDate("tarikhMula", sdf.parse(tarikhMula));
            q.setDate("tarikhAkhir", sdf.parse(tarikhAkhir));
        }
        if (!StringUtils.isEmpty(caraBayar)) {
            q.setString("caraBayar", caraBayar);
        }
        q.setString("kodCaw", kodCaw);
        List<PenyataPemungut> a = q.list();
        if (!a.isEmpty()) {
            for (PenyataPemungut p : a) {
                SenaraiPPForm f = new SenaraiPPForm();
                f.setNoPenyata(p.getNoPenyata());
                if (p.getTarikhJana() != null) {
                    f.setTarikhJana(sdf.format(p.getTarikhJana()));
                }
                l.add(f);
            }
        }
        return l;
    }

    public PenyataPemungut findByNoPenyata(String noPenyataR) {
        String query = "Select p FROM etanah.model.ispek.PenyataPemungut p where p.noPenyata =:noPenyataR ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyataR", noPenyataR);
        return (PenyataPemungut) q.uniqueResult();

    }


}

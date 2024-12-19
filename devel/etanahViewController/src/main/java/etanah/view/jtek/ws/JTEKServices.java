/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.jtek.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JteknikalDokumenTerima;
import etanah.model.KodAgensi;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PortalPengguna;
import etanah.model.UlasanJabatanTeknikal;
import etanah.service.common.PgunaService;
import etanah.view.etanahContextListener;
import etanah.view.uam.PenggunaPortalActionBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class JTEKServices {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PortalPenggunaDAO portalPenggunaDAO;
    @Inject
    UlasanJabatanTeknikalDAO ulasanJabatanTeknikalDAO;
    @Inject
    PermohonanRujukanLuarDAO mohonRujukanLuarDAO;
    @Inject
            PenggunaDAO penggunaDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<UlasanJabatanTeknikal> getTugasan(String idJtek) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod <> 'SEL'";
        //Session session = sessionProvider.get();
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJtek);
        return q.list();
    }

    List<UlasanJabatanTeknikal> getTugasan(String idJTEK, String daerah, String tarikhMula, String tarikhTamat) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod <> 'SEL'";
        //Session session = sessionProvider.get();
        if (StringUtils.isNotBlank(daerah)) {
            query += " and b.permohonan.cawangan.kod = :daerah";
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isNotBlank(tarikhTamat)) {
            query += " and (b.permohonan.infoAudit.tarikhMasuk BETWEEN to_date(:tarikhMula,'dd/MM/yyyy') "
                    + "AND to_date(:tarikhTamat,'dd/MM/yyyy'))";
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isBlank(tarikhTamat)) {
            tarikhTamat = sdf.format(new Date());
            query += " and (b.permohonan.infoAudit.tarikhMasuk BETWEEN to_date(:tarikhMula,'dd/MM/yyyy') "
                    + "AND to_date(:tarikhTamat,'dd/MM/yyyy'))";
        }
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
        if (StringUtils.isNotBlank(daerah)) {
            q.setString("daerah", daerah);
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isNotBlank(tarikhTamat)) {
            q.setString("tarikhMula", tarikhMula);
            q.setString("tarikhTamat", tarikhTamat);
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isBlank(tarikhTamat)) {
            q.setString("tarikhMula", tarikhMula);
            q.setString("tarikhTamat", tarikhTamat);
        }
        return q.list();
    }

    List<UlasanJabatanTeknikal> getTugasan(String idJTEK, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod <> 'SEL'";
        //Session session = sessionProvider.get();
        if (StringUtils.isNotBlank(idPermohonan)) {
            query += " and b.permohonan.idPermohonan = :idPermohonan";
        }
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
        if (StringUtils.isNotBlank(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }
        return q.list();
    }

    public List<Pemohon> getPemohon(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.HakmilikPermohonan p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> getDokumenList(String idjtekulas) {
        String query = "SELECT d FROM etanah.model.Dokumen d,"
                + "etanah.model.UlasanJabatanTeknikal ujt,"
                + "etanah.model.PermohonanRujukanLuar prl,"
                + "etanah.model.PermohonanRujukanLuarDokumen prld"
                + " where ujt.idJtekUlas = :idjtekulas "
                + "and ujt.permohonan.idPermohonan = prl.permohonan.idPermohonan "
                + "and prl.idRujukan = prld.permohonanRujukanLuar.idRujukan "
                + "and prld.dokumen.idDokumen = d.idDokumen and ujt.kodAgensi.kod = prl.agensi.kod";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idjtekulas", idjtekulas);
        return q.list();
    }

    public List<JteknikalDokumenTerima> getDokumenHantar(String idjtekulas) {
        String query = "SELECT d FROM etanah.model.JteknikalDokumenTerima d,"
                + "etanah.model.UlasanJabatanTeknikal ujt,"
                + "etanah.model.PermohonanRujukanLuar prl,"
                + "etanah.model.PermohonanRujukanLuarDokumen prld"
                + " where ujt.permohonan.idPermohonan = :idPermohonan "
                + "and ujt.permohonan.idPermohonan = prl.permohonan.idPermohonan "
                + "and prl.idRujukan = prld.permohonanRujukanLuar.idRujukan "
                + "and prld.dokumen.idDokumen = d.idDokumen";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idjtekulas", idjtekulas);
        return q.list();
    }

    public List<LaporanTanahSempadan> getLaporanTanah(String idPermohonan, String idHakmilik) {
        String query = "SELECT lts FROM etanah.model.HakmilikPermohonan mh,"
                + "etanah.model.LaporanTanah lt,"
                + "etanah.model.LaporanTanahSempadan lts"
                + " where mh.permohonan.idPermohonan = :idPermohonan "
                + "and mh.hakmilik.idHakmilik = :idHakmilik "
                + "and mh.id = lt.hakmilikPermohonan.id "
                + "and lt.idLaporan = lts.laporanTanah.idLaporan";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public UlasanJabatanTeknikal getJtekInfo(String idjtekulas) {
        String query = "SELECT ujt FROM etanah.model.UlasanJabatanTeknikal ujt"
                + " where ujt.idJtekUlas = :idjtekulas ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idjtekulas", idjtekulas);
        return (UlasanJabatanTeknikal) q.uniqueResult();
    }

    public PortalPengguna getPenggunPortal(String idPengguna) {
        return portalPenggunaDAO.findById(idPengguna);
    }

    public UlasanJabatanTeknikal setUlasanJabatanTeknikal(UlasanJabatanTeknikal ujt) {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        ujt = ulasanJabatanTeknikalDAO.saveOrUpdate(ujt);
        if (ujt != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return ujt;
    }

    PortalPengguna UpdatePenggunPortal(PortalPengguna pp) {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        pp = portalPenggunaDAO.saveOrUpdate(pp);
        if (pp != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
        return pp;
    }

    public void updateMohonRujLuar(UlasanJabatanTeknikal ujt1) {
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        PermohonanRujukanLuar moRujukanLuar = findMohonRujLuar(ujt1.getPermohonan().getIdPermohonan(), ujt1.getKodAgensi().getKod());
        moRujukanLuar.setUlasan(ujt1.getUlasan());
        moRujukanLuar.setNoRujukan(ujt1.getNoRuj());
        moRujukanLuar.setNamaPenyedia(ujt1.getNamaPenyelia());
        int s = Integer.parseInt(ujt1.getKodSyorUlasanJabatanTeknikal().getKod());
        char ss = String.valueOf(s).charAt(0);
        moRujukanLuar.setDiSokong(ss);
        if (ujt1.getTrhSelesai() != null) {
            moRujukanLuar.setTarikhTerima(ujt1.getTrhSelesai());
        }

        moRujukanLuar = mohonRujukanLuarDAO.saveOrUpdate(moRujukanLuar);
        if (moRujukanLuar != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public PermohonanRujukanLuar findMohonRujLuar(String idPermohonan, String kodAgensi) {
        String query = "SELECT p FROM etanah.model.PermohonanRujukanLuar p where p.permohonan.idPermohonan = :idPermohonan "
                + "and p.agensi.kod = :kodAgensi";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodAgensi", kodAgensi);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    List<UlasanJabatanTeknikal> getTugasanSelesai(String idJTEK) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod = 'SEL'";
        //Session session = sessionProvider.get();
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
        return q.list();
    }

    public String daftarPengguna(InfoPenggunaPortal ipp) {
        PortalPengguna pp = new PortalPengguna();
        pp.setIdPguna(ipp.getIdPguna());
        pp.setEmail(ipp.getEmail());
        KodAgensi kod = new KodAgensi();
        pp.setKodAgensi(kod);
        pp.setKodSts(null);
        pp.setNama(ipp.getNama());
        pp.setNoKp(ipp.getNoKp());
        pp.setNoTel(ipp.getNoTel());
        pp.setPasswd(ipp.getPasswd());
        InfoAudit ia = new InfoAudit();
        Pengguna pengguna = penggunaDAO.findById("portal");
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        pp.setInfoAudit(ia);
        savePortalPengguna(pp);
        return "";

    }

    @Transactional
    public PortalPengguna savePortalPengguna(PortalPengguna p) {
        return portalPenggunaDAO.saveOrUpdate(p);
    }

    List<UlasanJabatanTeknikal> getTugasanSelesai(String idJTEK, String daerah, String tarikhMula, String tarikhTamat) {
         String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod = 'SEL'";
        //Session session = sessionProvider.get();
        if (StringUtils.isNotBlank(daerah)) {
            query += " and b.permohonan.cawangan.kod = :daerah";
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isNotBlank(tarikhTamat)) {
            query += " and (b.permohonan.infoAudit.tarikhMasuk BETWEEN to_date(:tarikhMula,'dd/MM/yyyy') "
                    + "AND to_date(:tarikhTamat,'dd/MM/yyyy'))";
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isBlank(tarikhTamat)) {
            tarikhTamat = sdf.format(new Date());
            query += " and (b.permohonan.infoAudit.tarikhMasuk BETWEEN to_date(:tarikhMula,'dd/MM/yyyy') "
                    + "AND to_date(:tarikhTamat,'dd/MM/yyyy'))";
        }
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
        if (StringUtils.isNotBlank(daerah)) {
            q.setString("daerah", daerah);
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isNotBlank(tarikhTamat)) {
            q.setString("tarikhMula", tarikhMula);
            q.setString("tarikhTamat", tarikhTamat);
        }
        if (StringUtils.isNotBlank(tarikhMula) && StringUtils.isBlank(tarikhTamat)) {
            q.setString("tarikhMula", tarikhMula);
            q.setString("tarikhTamat", tarikhTamat);
        }
        return q.list();
    }
    List<UlasanJabatanTeknikal> getTugasanSelesai(String idJTEK, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod = 'SEL'";
        //Session session = sessionProvider.get();
        if (StringUtils.isNotBlank(idPermohonan)) {
            query += " and b.permohonan.idPermohonan = :idPermohonan";
        }
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
        if (StringUtils.isNotBlank(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }
        return q.list();
    }
}

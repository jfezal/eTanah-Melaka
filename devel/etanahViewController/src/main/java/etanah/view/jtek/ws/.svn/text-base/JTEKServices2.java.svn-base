/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.jtek.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.JteknikalDokumenTerima;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pemohon;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PortalPengguna;
import etanah.model.UlasanJabatanTeknikal;
import etanah.view.etanahContextListener;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class JTEKServices2 {

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
    PermohonanRujukanLuarDokumenDAO permohonRujukanLuarDokumenDAO;

    public List<UlasanJabatanTeknikal> getTugasan(String idJtek) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod <> 'SEL'";
        //Session session = sessionProvider.get();
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJtek);
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


    //added by nurashidah for utility JTEK
    public List<KodAgensi> getSenaraiAgensiJTEK() {
        String query = "Select ka from etanah.model.KodAgensi ka where ka.kategoriAgensi.kod = 'JTK' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<UlasanJabatanTeknikal> getSenaraiUlasan() {
        String query = "Select u from etanah.model.UlasanJabatanTeknikal u where u.kodStatusUlasanJabatanTeknikal.kod = 'BAR' "
                + " or u.kodStatusUlasanJabatanTeknikal.kod = 'SED' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiDokumen(String idRujukan) {
        String query = "Select prld from etanah.model.PermohonanRujukanLuarDokumen prld where prld.permohonanRujukanLuar.idRujukan LIKE :idRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", "%" + idRujukan + "%");
        return q.list();
    }

    public List<UlasanJabatanTeknikal> searchIdMohonKodAgensi(String idMohon, String kodAgensi) {
        String query = "Select u from etanah.model.UlasanJabatanTeknikal u WHERE u.permohonan.idPermohonan LIKE :idMohon"
                + " and u.kodAgensi.kod LIKE :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", "%" + idMohon + "%");
        q.setString("kodAgensi", "%" + kodAgensi + "%");

        return q.list();
    }

    public List<UlasanJabatanTeknikal> searchIdMohon(String idMohon) {
        String query = "Select u from etanah.model.UlasanJabatanTeknikal u WHERE u.permohonan.idPermohonan LIKE :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", "%" + idMohon + "%");

        return q.list();
    }

    public List<UlasanJabatanTeknikal> searchkodAgensi(String kodAgensi) {
        String query = "Select u from etanah.model.UlasanJabatanTeknikal u WHERE u.kodAgensi.kod LIKE :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodAgensi", "%" + kodAgensi + "%");

        return q.list();
    }

    public PermohonanRujukanLuar searchIdRujukan(String idMohon, String kodAgensi) {
        String query = "Select prl from etanah.model.PermohonanRujukanLuar prl WHERE prl.permohonan.idPermohonan LIKE :idMohon"
                + " and prl.agensi.kod LIKE :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", "%" + idMohon + "%");
        q.setString("kodAgensi", "%" + kodAgensi + "%");
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<KandunganFolder> findFolderDok(String idFolder) {
        String query = "Select kd from etanah.model.KandunganFolder kd where kd.folder.folderId LIKE :idFolder ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idFolder", "%" + idFolder + "%");
        return q.list();
    }

    public PermohonanRujukanLuarDokumen findDokumen(String idDokumen, String idRujukan) {
        String query = "SELECT pd FROM etanah.model.PermohonanRujukanLuarDokumen pd where pd.dokumen.idDokumen = :idDokumen "
                + "and pd.permohonanRujukanLuar.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idDokumen", idDokumen);
        q.setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanRujLuarDokumen(PermohonanRujukanLuarDokumen permohonanRujukanLuarDokumen) {
        permohonRujukanLuarDokumenDAO.saveOrUpdate(permohonanRujukanLuarDokumen);

    }

    List<UlasanJabatanTeknikal> getTugasanSelesai(String idJTEK) {
               String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.kodAgensi.kod = :idJtek and b.kodStatusUlasanJabatanTeknikal.kod = 'SEL'";
        //Session session = sessionProvider.get();
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idJtek", idJTEK);
         return q.list();
    }

}

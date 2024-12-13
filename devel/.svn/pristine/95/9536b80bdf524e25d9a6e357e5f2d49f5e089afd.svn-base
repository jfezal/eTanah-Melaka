/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermohonanMahkamahDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.model.AmbilPampasan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Rajesh
 */
public class PermohonanSupayaBantahanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanMahkamahDAO permohonanMahkamahDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PengambilanService pengambilanService;
    PermohonanKertas permohonanKertas;

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihakD(String idMohon, String idHakmilik, Long idPihak, String kodPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak and m.jenis.kod=:kodPihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        q.setParameter("kodPihak", kodPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanPihakD(Permohonan permohonan, Pengguna peng) {

        String idPermohonan = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : listHp) {
            if (hp.getHakmilik() != null) {
                System.out.println("id permohonan : " + idPermohonan);
                System.out.println("id hakmilik >> " + hp.getHakmilik().getIdHakmilik());
                List<PermohonanPihak> permohonanPihaks = pengambilanService.findMohonPihak(idPermohonan, hp.getHakmilik().getIdHakmilik());
                if (permohonanPihaks.size() == 0) {
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {

//                    PermohonanPihak pp = getSenaraiPmohonPihakByIdHakmilikIdPihakD(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak(),hakmilikPihak.getJenis().getKod());
                        PermohonanPihak newPP = new PermohonanPihak();
                        newPP.setPermohonan(permohonan);
                        newPP.setHakmilik(hakmilikPihak.getHakmilik());
                        newPP.setPihak(hakmilikPihak.getPihak());
                        newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());

                        if (hakmilikPihak.getSyerPembilang() != null) {
                            newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                        }
                        if (hakmilikPihak.getSyerPenyebut() != null) {
                            newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                        }
                        newPP.setCawangan(permohonan.getCawangan());
                        newPP.setInfoAudit(ia);
                        KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                        kodPihak.setKod(hakmilikPihak.getJenis().getKod());
                        newPP.setJenis(kodPihak);
//                        newPP.setJenis(findByIdKodPihak("PJK"));
                        permohonanPihakDAO.save(newPP);

                    }
                }
            }

        }

    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihak(String idMohon, String idHakmilik, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdPihak(String idMohon, Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanMahkamah findPermohonanMahkamahByidMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanMahkamah m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanMahkamah) q.uniqueResult();

    }

    public PermohonanKertas findPermohonanKertasByidMP(long idPermohonanPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanKertas m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanKertas) q.uniqueResult();

    }

    public PermohonanKertas findPermohonanKertasByidMP2(long idPermohonanPihak, String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanKertas m WHERE m.permohonanPihak.idPermohonanPihak = :idPermohonanPihak and m.permohonan.idPermohonan =:idMohon ");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setString("idMohon", idMohon);
        return (PermohonanKertas) q.uniqueResult();

    }

    @Transactional
    public PermohonanMahkamah saveOrupdatePermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        return permohonanMahkamahDAO.saveOrUpdate(permohonanMahkamah);

    }

    @Transactional
    public void simpanPermohonanPihak(Permohonan permohonan, Pengguna peng) {

        String idPermohonan = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp : listHp) {
            System.out.println("id permohonan : " + idPermohonan);
            System.out.println("id hakmilik >> " + hp.getHakmilik().getIdHakmilik());
            try {
                List<PermohonanPihak> permohonanPihaks = pengambilanService.findMohonPihak(idPermohonan, hp.getHakmilik().getIdHakmilik());
                if (permohonanPihaks.size() == 0) {
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilikPihakList) {
                        if (hakmilikPihak.getAktif() == 'Y') {
                            PermohonanPihak pp = getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            PermohonanPihak newPP = new PermohonanPihak();
                            newPP.setPermohonan(permohonan);
                            newPP.setHakmilik(hakmilikPihak.getHakmilik());
                            newPP.setPihak(hakmilikPihak.getPihak());
                            newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                            newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                            newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                            newPP.setCawangan(permohonan.getCawangan());
                            newPP.setInfoAudit(ia);
                            KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                            kodPihak.setKod(hakmilikPihak.getJenis().getKod());
                            newPP.setJenis(kodPihak);
//                        newPP.setJenis(findByIdKodPihak("PJK"));
                            permohonanPihakDAO.save(newPP);
                        }
                    }
                }
            } catch (Exception j) {
            }
        }

    }

    public KodJenisPihakBerkepentingan findByIdKodPihak(String kodPihak) {
        String query = "SELECT b FROM etanah.model.KodJenisPihakBerkepentingan b WHERE b.kod = :kodPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPihak", kodPihak);
        return (KodJenisPihakBerkepentingan) q.uniqueResult();
    }

    public AmbilPampasan getAmbilPampasanByidMP(long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b where b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (AmbilPampasan) q.uniqueResult();

    }

    @Transactional
    public void saveOrupdateAmbilPampasan(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.saveOrUpdate(ambilPampasan);
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMPidBicara(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public List<AmbilPampasan> getAmbilPampasanListByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    public AmbilPampasan getAmbilPampasanListByidHadir1(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return (AmbilPampasan) q.uniqueResult();
    }

    public DasarTuntutanCukaiHakmilik getDasarTuntutanCukaiHakmilikByIdDasar(String idDasar) {
        String query = "SELECT b FROM etanah.model.DasarTuntutanCukaiHakmilik b WHERE b.dasarTuntutanCukai.idDasar = :idDasar";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idDasar", idDasar);
        return (DasarTuntutanCukaiHakmilik) q.uniqueResult();
    }

    @Transactional
    public HakmilikPerbicaraan saveOrUpdateHakmilikPerbicaraan(HakmilikPerbicaraan n) {
        return hakmilikPerbicaraanDAO.saveOrUpdate(n);
    }

    @Transactional
    public PerbicaraanKehadiran saveOrUpdatePerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        return perbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
    }

    @Transactional
    public DasarTuntutanCukaiHakmilik saveOrUpdateDasarTuntutanCukaiHakmilik(DasarTuntutanCukaiHakmilik n) {
        return dasarTuntutanCukaiHakmilikDAO.saveOrUpdate(n);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import etanah.dao.KodPeringkatIspeksDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.Bil;
import etanah.model.ispek.Journal;
import etanah.model.ispek.KodPeringkatIspeks;
import etanah.model.ispek.PenyataPemungut;
import etanah.model.ispek.TugasanIspeks;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class TugasanIspekService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPeringkatIspeksDAO kodPeringkatIspeksDAO;
    @Inject
    PenyataPemungutService penyataPemungutService;
    @Inject
    JournalService journalService;
    @Inject
    BilService bilService;

    List<TugasanIspeks> findTugasanIspeks(String idPguna, String caw, String kat) {
        String query = "";
        if (kat.equals("PP")) {
            query = " Select p FROM etanah.model.ispek.TugasanIspeks p, etanah.model.ispek.PenyataPemungut pe, "
                    + "etanah.model.ispek.KodPeringkatIspeks kpi,"
                    + "etanah.model.ispek.KodPeringkatPerananIspeks kppi,"
                    + "etanah.model.PenggunaPeranan pp,etanah.model.KodPeranan kp"
                    + "  where ";
        } else if (kat.equals("BIL")) {
            query = " Select p FROM etanah.model.ispek.TugasanIspeks p, etanah.model.ispek.Bil bl,"
                    + "etanah.model.ispek.KodPeringkatIspeks kpi,"
                    + "etanah.model.ispek.KodPeringkatPerananIspeks kppi,"
                    + "etanah.model.PenggunaPeranan pp,etanah.model.KodPeranan kp"
                    + "  where ";
        } else if (kat.equals("JOR")) {
            query = " Select p FROM etanah.model.ispek.TugasanIspeks p, etanah.model.ispek.Journal jr,"
                    + "etanah.model.ispek.KodPeringkatIspeks kpi,"
                    + "etanah.model.ispek.KodPeringkatPerananIspeks kppi,"
                    + "etanah.model.PenggunaPeranan pp,etanah.model.KodPeranan kp"
                    + "  where ";
        }

        query = query + " p.kodCaw.kod = :caw "
                + " and p.kodPeringkat.kod = kpi.kod and kpi.kod = kppi.kodPeringkat "
                + " and kppi.kodPeranan.kod = kp.kod and kp.kod = pp.peranan.kod and pp.pengguna.idPengguna = :idPguna";

        if (kat.equals("PP")) {
            query = query + " and kpi.kat = 'PP' and pe.id = p.noRef";
        } else if (kat.equals("BIL")) {
            query = query + " and kpi.kat = 'BIL' and bl.id = p.noRef";

        } else if (kat.equals("JOR")) {
            query = query + " and kpi.kat = 'JOR' and jr.id = p.noRef ";

        }
        Query q = sessionProvider.get().createQuery(query);
        q.setString("caw", caw);
        q.setString("idPguna", idPguna);
        return q.list();
    }

    TugasanIspeks findByNoRef(String id, String kat, String caw) {
        String query = "Select p FROM etanah.model.ispek.TugasanIspeks p,"
                + "etanah.model.ispek.KodPeringkatIspeks kpi"
                + "  where p.kodCaw.kod = :caw "
                + "and p.kodPeringkat.kod = kpi.kod and kpi.kat = :kat and p.noRef = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", id);
        q.setString("kat", kat);
        q.setString("caw", caw);
        return (TugasanIspeks) q.uniqueResult();
    }

    public String validatePeringkatUser(String kodPeringkat, Pengguna pengguna, String noPenyata) {
        KodPeringkatIspeks kpi = kodPeringkatIspeksDAO.findById(kodPeringkat);
        if (kpi.getKat().equals("PP")) {
            PenyataPemungut pp = penyataPemungutService.findByNoPenyata(noPenyata);
            if (pp != null) {
                String kodP = kpi.getKod();
                if (kodP.equals("PPSED")) {
                } else if (kodP.equals("PPSEM")) {
                    if (pp.getPenyedia().equals(pengguna.getNama())) {
                        return "1";
                    }
                } else if (kodP.equals("PPLUL")) {
                    if (pp.getPenyedia().equals(pengguna.getNama()) || pp.getPenyemak().equals(pengguna.getNama())) {
                        return "2";
                    }
                }
//                else if (kodP.equals("PPHTR")) {
//                    if (pp.getPenyedia().equals(pengguna.getNama()) || pp.getPenyemak().equals(pengguna.getNama())) {
//                        return "2";
//                    }
//                }

            }
        } else if (kpi.getKat().equals("BIL")) {
            Bil bil = bilService.findByNoPenyata(noPenyata);
            if (bil != null) {
                String kodP = kpi.getKod();
                if (kodP.equals("BLSED")) {
                } else if (kodP.equals("BLSEM")) {
                    if (bil.getPenyedia().equals(pengguna.getNama())) {
                        return "1";
                    }
                } else if (kodP.equals("BLLUL")) {
                    if (bil.getPenyedia().equals(pengguna.getNama()) || bil.getPenyemak().equals(pengguna.getNama())) {
                        return "1";
                    }
                }

            }
        } else if (kpi.getKat().equals("JOR")) {
            Journal pp = journalService.findByNoJurnal(noPenyata);
            if (pp != null) {
                String kodP = kpi.getKod();
                if (kodP.equals("JRSED")) {
                } else if (kodP.equals("JRSEM")) {
                    if (pp.getPenyedia().equals(pengguna.getNama())) {
                        return "1";
                    }
                } else if (kodP.equals("JRLUL")) {
                    if (pp.getPenyedia().equals(pengguna.getNama()) || pp.getPenyemak().equals(pengguna.getNama())) {
                        return "1";
                    }
                }

            }

        }
        return "";
    }
}

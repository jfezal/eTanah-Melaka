/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanPihakKemaskiniDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.Pemohon;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.gis.GISPelan;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author khairil
 */
public class PermohonanPihakKemaskiniService {

    @Inject
    PermohonanPihakKemaskiniDAO mohonPihakKemaskiniDAO;
    @Inject
    GISPelanDAO gISPelanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public void save(PermohonanPihakKemaskini mohonPihakKemaskini) {
        mohonPihakKemaskiniDAO.save(mohonPihakKemaskini);
    }

    @Transactional
    public void save(List<PermohonanPihakKemaskini> list) {
        for (PermohonanPihakKemaskini permohonanPihakKemaskini : list) {
            mohonPihakKemaskiniDAO.save(permohonanPihakKemaskini);
        }
    }

    @Transactional
    public void delete(PermohonanPihakKemaskini mohonPihakKemaskini) {
        mohonPihakKemaskiniDAO.delete(mohonPihakKemaskini);
    }

    @Transactional
    public void delete(GISPelan gISPelan) {
        gISPelanDAO.delete(gISPelan);
    }

    @Transactional
    public void delete(List<PermohonanPihakKemaskini> senarai) {
        for (PermohonanPihakKemaskini ppk : senarai) {
            mohonPihakKemaskiniDAO.delete(ppk);
        }
    }

    public PermohonanPihakKemaskini findByNamaMedan(String nama, String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanPihakKemaskini p where p.namaMedan = :namaMedan"
                + "and p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("nama", nama).setString("idPermohonan", idPermohonan);
        return (PermohonanPihakKemaskini) q.uniqueResult();
    }

    public List<PermohonanPihakKemaskini> findByIdPermohonanAndIdAtasPihak(String idPermohonan, String idAtasPihak) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.atasPihakBerkepentingan.idAtasPihak = :idAtasPihak");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAtasPihak", idAtasPihak);

        return q.list();
    }

    public List<PermohonanPihakKemaskini> findByIdPermohonan(String idPermohonan) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan");
        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);

        return q.list();
    }

    public List<PermohonanPihakKemaskini> findByNamaMedanList(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT p FROM etanah.model.PermohonanPihakKemaskini where p.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> getSenaraiPihakKemaskini(String idPermohonan, String idHakmilik, long idPemohon, long idPihak) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.pemohon.hakmilik.idHakmilik = :idHakmilik");
        if (idPemohon > 0) {
            sb.append(" and p.pemohon.idPemohon = :idPemohon");
        }

        if (idPihak > 0) {
            sb.append(" and p.pemohon.pihak.idPihak = :idPihak");
        }

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        if (idPemohon > 0) {
            q.setParameter("idPemohon", idPemohon);
        }
        if (idPihak > 0) {
            q.setParameter("idPihak", idPihak);
        }
        return q.list();
    }

    public List<PermohonanPihakKemaskini> getSenaraiPihakKemaskiniTN(String idPermohonan, String idHakmilik, long idPemohon, long idPihak) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.pemohon.hakmilik.idHakmilik = :idHakmilik");
        if (idPemohon > 0) {
            sb.append(" and p.pemohon.idPemohon = :idPemohon");
        }

        if (idPihak > 0) {
            sb.append(" and p.pemohon.pihak.idPihak = :idPihak");
        }

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        if (idPemohon > 0) {
            q.setLong("idPemohon", idPemohon);
        }
        if (idPihak > 0) {
            q.setParameter("idPihak", idPihak);
        }
        return q.list();
    }

    public String getNilaiBaru(String idPermohonan, String idHakmilik, String idPihak, String namaMedan, String idKump, String kumpNo) {
        StringBuilder sb = new StringBuilder("SELECT mkk FROM etanah.model.PermohonanPihakKemaskini mkk,")
                .append("etanah.model.Permohonan m,")
                .append("etanah.model.Pemohon p ")
                .append("WHERE mkk.permohonan.idPermohonan = m.idPermohonan ")
                .append("AND p.permohonan.idPermohonan = m.idPermohonan ")
                .append("AND mkk.pemohon.idPemohon = p.idPemohon ")
                .append("AND p.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND m.idKumpulan = :idKump ")
                .append("AND p.permohonan.idPermohonan not in (:idPermohonan) ")
                .append("AND m.kumpulanNo <= :kumpNo ")
                .append("AND p.pihak.idPihak = :idPihak ")
                .append("AND mkk.namaMedan = :namaMedan ")
                .append("ORDER BY m.kumpulanNo DESC");

        Query query = sessionProvider.get().createQuery(sb.toString())
                .setString("idKump", idKump)
                .setParameter("idHakmilik", idHakmilik)
                .setParameter("idPermohonan", idPermohonan)
                .setParameter("kumpNo", Integer.parseInt(kumpNo))
                .setParameter("idPihak", Long.valueOf(idPihak))
                .setParameter("namaMedan", namaMedan);

        if (query.list().isEmpty()) {
            return null;
        }

        PermohonanPihakKemaskini kk = (PermohonanPihakKemaskini) query.list().get(0);

        return kk != null ? kk.getNilaiBaru() : null;

    }

    public List<PermohonanPihakKemaskini> getKemaskiniWaris(String idPermohonan, String idHpw) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.warisTerlibat.idWaris = :idWaris");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idWaris", idHpw);

        return q.list();
    }

    public List<PermohonanPihakKemaskini> getKemaskiniWaris2(String idPermohonan, String idHp) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.pihakTerlibat.idHakmilikPihakBerkepentingan = :idHp");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHp", idHp);

        return q.list();
    }

    public List<PermohonanPihakKemaskini> findKemaskiniWarisByIdHPW(String idPermohonan, String idHp, String idWaris) {

        StringBuilder sb = new StringBuilder("Select p from etanah.model.PermohonanPihakKemaskini p")
                .append(" where p.permohonan.idPermohonan = :idPermohonan")
                .append(" and p.warisTerlibat.idWaris = :idWaris")
                .append(" and p.pihakTerlibat.idHakmilikPihakBerkepentingan = :idHp");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("idWaris", idWaris);
        q.setString("idHp", idHp);

        return q.list();
    }

    public PermohonanPihakKemaskini findKemaskiniWarisByIdHPW2(String idPermohonan, String idHp, String idWaris) {

        String query = "SELECT p FROM etanah.model.PermohonanPihakKemaskini p where "
                + "p.warisTerlibat.idWaris = :idWaris "
                + "and p.permohonan.idPermohonan = :idPermohonan "
                + "and p.pihakTerlibat.idHakmilikPihakBerkepentingan = :idHp";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan)
                .setString("idHp", idHp)
                .setString("idWaris", idWaris);
        return (PermohonanPihakKemaskini) q.uniqueResult();
    }
}

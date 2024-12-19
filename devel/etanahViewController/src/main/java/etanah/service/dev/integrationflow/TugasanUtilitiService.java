/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dev.integrationflow;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.view.stripes.pembangunan.tugasan.TugasanForm;
import etanah.view.stripes.tugasanutiliti.FormStatMMKN;
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
public class TugasanUtilitiService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
        private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");


    public List<TugasanUtiliti> findListTugasan(String idPengguna) {
        String query = "Select p FROM etanah.model.TugasanUtiliti p, "
                + "etanah.model.KodPeringkatPeranan kp, "
                + " etanah.model.PenggunaPeranan pp, "
                + " etanah.model.Pengguna pg, "
                + " etanah.model.Permohonan m "
                + " where p.kodPeringkat.kod = kp.kodPeringkat.kod "
                + " and kp.kodPeranan.kod = pp.peranan.kod "
                + " and pp.pengguna.idPengguna = :idPengguna"
                + " and p.idPermohonan = m.idPermohonan "
                + " and pp.pengguna.idPengguna = pg.idPengguna "
                + " and (pg.kodCawangan.kod = m.cawangan.kod or pg.kodCawangan.kod='00')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public Long findTotalTugasanForPengguna(Pengguna user) {
        String query = "Select count(p) FROM etanah.model.TugasanUtiliti p, "
                + "etanah.model.KodPeringkatPeranan kp, "
                + " etanah.model.PenggunaPeranan pp, "
                + " etanah.model.Pengguna pg, "
                + " etanah.model.Permohonan m "
                + " where p.kodPeringkat.kod = kp.kodPeringkat.kod "
                + " and kp.kodPeranan.kod = pp.peranan.kod "
                + " and pp.pengguna.idPengguna = :idPengguna"
                + " and p.idPermohonan = m.idPermohonan "
                + " and pp.pengguna.idPengguna = pg.idPengguna "
                + " and (pg.kodCawangan.kod = m.cawangan.kod or pg.kodCawangan.kod='00')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", user.getIdPengguna());
        return (Long) q.iterate().next();
    }

    public FormStatMMKN setStatistikPermohonan() {
        FormStatMMKN mmkn = new FormStatMMKN();
        mmkn.setTotalSek4(findTotalPermohonan("SEK4") + "");
        mmkn.setTotalSek8(findTotalPermohonan("SEK8") + "");
        mmkn.setTotalAduan(findTotalPermohonan("SEK4A") + "");
        mmkn.setMyetappSek4(findTotalPermohonan("SEK4") + "");
        mmkn.setMyetappSek8(findTotalPermohonan("SEK4") + "");
        return mmkn;
    }

    public FormStatMMKN setStatistiLulusTolak() {
        FormStatMMKN mmkn = new FormStatMMKN();
        mmkn.setLulus(findStatistikKelulusan("Y") + "");
        mmkn.setTolak(findStatistikKelulusan("T") + "");
        mmkn.setKiv(findStatistikKelulusanKIV() + "");
        return mmkn;
    }

    private Long findTotalPermohonan(String kodUrusan) {
        String query = "Select count(p) FROM etanah.model.Permohonan p "
                + " where p.kodUrusan.kod = :kodUrusan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return (Long) q.iterate().next();
    }

    private Long findStatistikKelulusan(String stat) {
        String query = "Select count(p) FROM etanah.model.InfoMmkn p "
                + " where p.kodKpsn =:stat ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("stat", stat);
        return (Long) q.iterate().next();
    }

    private Long findStatistikKelulusanKIV() {
        String query = "Select count(p) FROM etanah.model.InfoMmkn p "
                + " where p.kodKpsn not in ('Y','T') ";
        Query q = sessionProvider.get().createQuery(query);
        return (Long) q.iterate().next();
    }

    public List<TugasanUtiliti> findListTugasan(String idPengguna, String idPermohonan, String kodUrusan) {
        String query = "Select p FROM etanah.model.TugasanUtiliti p, "
                + "etanah.model.KodPeringkatPeranan kp, "
                + " etanah.model.PenggunaPeranan pp, "
                + " etanah.model.Pengguna pg, "
                + " etanah.model.Permohonan m "
                + " where p.kodPeringkat.kod = kp.kodPeringkat.kod "
                + " and kp.kodPeranan.kod = pp.peranan.kod "
                + " and pp.pengguna.idPengguna = :idPengguna"
                + " and p.idPermohonan = m.idPermohonan "
                + " and pp.pengguna.idPengguna = pg.idPengguna "
                + " and (pg.kodCawangan.kod = m.cawangan.kod or pg.kodCawangan.kod='00')";
        if (StringUtils.isNotEmpty(idPermohonan)) {
            query = query + " and m.idPermohonan like :idPermohonan";
        }
        if (StringUtils.isNotEmpty(kodUrusan)) {
            query = query + " and m.kodUrusan.kod = :kodUrusan";

        }
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPengguna", idPengguna);
        if (StringUtils.isNotEmpty(idPermohonan)) {
            q.setString("idPermohonan", "%" +idPermohonan+"%");

        }
        if (StringUtils.isNotEmpty(kodUrusan)) {
            q.setString("kodUrusan", kodUrusan);

        }
        return q.list();
    }

    public List<KodUrusan> senaraiUrusan(String[] list) {
        String query = "Select p FROM etanah.model.KodUrusan p "
                + " where p.kod in (:list) ";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("list", list);
        return q.list();
    }

    public List<TugasanForm> findListTugasan2(String idPengguna, String idPermohonan, String kodUrusan) {
    List<TugasanForm> list = new ArrayList<TugasanForm>();
    for(TugasanUtiliti t :findListTugasan(idPengguna, idPermohonan, kodUrusan)){
    TugasanForm f = new TugasanForm();
        Permohonan m = permohonanDAO.findById(t.getIdPermohonan());
    f.setIdPermohonan(t.getIdPermohonan());
    f.setLink(t.getKodPeringkat().getUrl()+"&idPermohonan="+t.getIdPermohonan());
    f.setTajuk(m.getKodUrusan().getNama());
    f.setTindakan(t.getKodPeringkat().getNama());
    f.setTarikhMula(sdf.format(t.getInfoAudit().getTarikhMasuk()));
    f.setTarikhTamat(sdf.format(t.getInfoAudit().getTarikhMasuk()));
    f.setBil("0");
    list.add(f);
    }
    return list;
    }

}

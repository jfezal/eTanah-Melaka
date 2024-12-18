/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ws;

import etanah.view.portal.service.ws.SenaraiDokumen;
import com.google.inject.Inject;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodDokumenDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.UrusanDokumen;
import etanah.service.RegService;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.portal.service.ws.SenaraiBandarPekanMukim;
import etanah.view.portal.service.ws.SenaraiKodDaerah;
import etanah.view.portal.service.ws.SenaraiKodHakmilik;
import etanah.view.portal.service.ws.SenaraiKodSeksyen;
import etanah.view.portal.service.ws.SenaraiUrusan;
import etanah.view.portal.service.ws.StatusPermohonan;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author zipzap
 */
public class SenaraiKodService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;

    public List<SenaraiUrusan> findByKeyWord(String key) {
        SenaraiUrusan form = new SenaraiUrusan();
        List<SenaraiUrusan> list = new ArrayList<SenaraiUrusan>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodUrusan u where u.nama like :key");
        q.setString("key", "%" + key + "%");
        List<KodUrusan> listUrusan = q.list();
        for (KodUrusan urusan : listUrusan) {
            form = new SenaraiUrusan();
            form.setNamUrusan(urusan.getNama());
            form.setKodUrusan(urusan.getKod());
            list.add(form);

        }
        return list;
    }

    public List<SenaraiKodDaerah> findAllKodDaerah() {
        SenaraiKodDaerah form = new SenaraiKodDaerah();
        List<SenaraiKodDaerah> list = new ArrayList<SenaraiKodDaerah>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodDaerah where aktif = 'Y'");
        List<KodDaerah> listDaerah = q.list();
        for (KodDaerah daerah : listDaerah) {
            form = new SenaraiKodDaerah();
            form.setKodDaerah(daerah.getKod());
            form.setNamaDaerah(daerah.getNama());
            list.add(form);
        }
        return list;
    }

    public List<SenaraiBandarPekanMukim> findAllBandarPekanMukim(String kodDaerah) {
        SenaraiBandarPekanMukim form = new SenaraiBandarPekanMukim();
        List<SenaraiBandarPekanMukim> list = new ArrayList<SenaraiBandarPekanMukim>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah");
        q.setString("kodDaerah", kodDaerah);
        List<KodBandarPekanMukim> listBPM = q.list();
        for (KodBandarPekanMukim BPM : listBPM) {
            form = new SenaraiBandarPekanMukim();
            form.setBandarPekanMukim(BPM.getbandarPekanMukim());
            form.setCawangan(BPM.getCawangan().getName());
            form.setDaerah(BPM.getDaerah().getNama());
            form.setNama(BPM.getNama());
            list.add(form);

        }
        return list;
    }

    public List<SenaraiKodHakmilik> findAllKodHakmilik() {
        SenaraiKodHakmilik form = new SenaraiKodHakmilik();
        List<SenaraiKodHakmilik> list = new ArrayList<SenaraiKodHakmilik>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodHakmilik");
//        q.uniqueResult();
        List<KodHakmilik> listKodHakmilik = q.list();
        for (KodHakmilik kodHakmilik : listKodHakmilik) {
            form = new SenaraiKodHakmilik();
            form.setKodHakmilik(kodHakmilik.getKod());
            form.setMilikDaerah(Character.toString(kodHakmilik.getMilikDaerah()));
            form.setNama(kodHakmilik.getNama());
            list.add(form);

        }
        return list;
    }

    public List<SenaraiDokumen> findbyKodUrusan(String kodUrusan) {
        SenaraiDokumen form = new SenaraiDokumen();
        List<SenaraiDokumen> list = new ArrayList<SenaraiDokumen>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.UrusanDokumen u where u.kodUrusan.kod = :kodUrusan");
        q.setString("kodUrusan", kodUrusan);
        List<UrusanDokumen> listDokumen = q.list();

        for (UrusanDokumen dokumen : listDokumen) {
            form = new SenaraiDokumen();
            form.setMandatori(convertYT(Character.toString(dokumen.getWajib())));
            form.setMuatTurun(convertYT(dokumen.getKodDokumen().getFail()!=null?"Y":"T"));
            form.setNamaDokumen(dokumen.getKodDokumen().getNama());
            form.setSah(convertYT(Character.toString(dokumen.getPerluDisah())));
            list.add(form);

        }
        return list;
    }

    public String convertYT(String flag) {
        if ("Y".equals(flag)) {
            return "YA";
        } else {
            return "TIDAK";
        }
    }

    public List<SenaraiKodSeksyen> findKodSekyenbyBpm(String kodBpm) {
        SenaraiKodSeksyen form = new SenaraiKodSeksyen();
        List<SenaraiKodSeksyen> list = new ArrayList<SenaraiKodSeksyen>();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah");
        q.setString("kodBpm", kodBpm);
        List<KodSeksyen> listSeksyen = q.list();
        for (KodSeksyen ss : listSeksyen) {
            form = new SenaraiKodSeksyen();
            form.setBpm(ss.getKodBandarPekanMukim().getKod() + "");
            form.setKod(ss.getKod() + "");
            form.setNama(ss.getNama());
            form.setSeksyen(ss.getSeksyen());
            list.add(form);

        }
        return list;
    }

    public DokumenInfo muatTurunDokumen(String kodDokumen) throws SQLException {
        DokumenInfo info = new DokumenInfo();
        KodDokumen dokumen = kodDokumenDAO.findById(kodDokumen);
        int bloblength = (int) dokumen.getFail().length();

        info.setBytes(dokumen.getFail().getBytes(1, bloblength));
//        dokumen.getFail().free();
        info.setDocType("application/pdf");
        info.setFileName(dokumen.getKod()+".pdf");

        return info;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.KumpulanAkaunDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.gis.UrusanCMSDAO;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.KumpulanAkaun;
import etanah.model.gis.UrusanCMS;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AgensiKutipanDokumenDAO;
import etanah.dao.AkaunDAO;
import etanah.dao.BilCukaiDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.DokumenKewanganSiriDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodKadarCukaiDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.Versi4kDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LogAkaunKewanganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SejarahDokumenKewanganDAO;
import etanah.dao.SejarahTransaksiDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.hasil.LaporanPenyataPemungutDAO;
import etanah.dao.hasil.TagAkaunAhliDAO;
import etanah.dao.hasil.TagAkaunDAO;
import etanah.model.AgensiKutipanDokumen;
import etanah.model.Akaun;
import etanah.model.BilCukai;
import etanah.model.Hakmilik;
import etanah.model.Dokumen;
import etanah.model.DokumenKewanganSiri;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KandunganFolder;
import etanah.model.KodKadarCukai;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.LaporanTanah;
import etanah.model.LogAkaunKewangan;
import etanah.model.Permohonan;
import etanah.model.SejarahDokumenKewangan;
import etanah.model.SejarahTransaksi;
import etanah.model.Transaksi;
import etanah.model.Versi4k;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.model.hasil.TagAkaun;
import etanah.model.hasil.TagAkaunAhli;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 *
 * @author abdul.hakim
 */
public class KutipanHasilManager {

    private static final Logger LOG = Logger.getLogger(KutipanHasilManager.class);

    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;

    @Inject
    DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;

    @Inject
    UrusanCMSDAO urusanCMSDAO;

    @Inject
    KandunganFolderDAO kandunganFolderDAO;

    @Inject
    LaporanPenyataPemungutDAO laporanPenyataPemungutDAO;

    @Inject
    LaporanTanahDAO laporanTanahDAO;

    @Inject
    KumpulanAkaunDAO kumpulanAkaunDAO;

    @Inject
    CaraBayaranDAO caraBayaranDAO;

    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;

    @Inject
    private PermohonanDAO permohonanDAO;

    @Inject
    TransaksiDAO transakasiDAO;

    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;

    @Inject
    AkaunDAO akaunDAO;

    @Inject
    DokumenDAO dokumenDAO;

    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    Versi4kDAO versi4kDAO;

    @Inject
    KodSekatanKepentinganDAO kodSekatanDAO;

    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;

    @Inject
    LogAkaunKewanganDAO logAkaunDAO;

    @Inject
    AgensiKutipanDokumenDAO agensiKutipanDokumenDAO;

    @Inject
    KodKadarCukaiDAO kodKadarCukaiDAO;

    @Inject
    DokumenKewanganSiriDAO dokumenKewanganSiriDAO;

    @Inject
    SejarahDokumenKewanganDAO sejarahDokumenKewanganDAO;

    @Inject
    SejarahTransaksiDAO sejarahTransaksiDAO;

    @Inject
    BilCukaiDAO bilCukaiDAO;

    /**
     * start method for kumpulan akaun table TAG_AKAUN and TAG_AKAUN_AHLI*
     */
    @Inject
    TagAkaunDAO tagAkaunDAO;

    @Inject
    TagAkaunAhliDAO tagAkaunAhliDAO;
    /**
     * finish method for kumpulan akaun table TAG_AKAUN and TAG_AKAUN_AHLI*
     */

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<Akaun> searchAkaun(String no_akaun) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Akaun u where u.noAkaun LIKE :noAkaun");
            q.setString("noAkaun", "%" + no_akaun + "%");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Hakmilik> searchHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Hakmilik u where u.idHakmilik LIKE :idHakmilik");
            q.setString("idHakmilik", "%" + idHakmilik + "%");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<DokumenKewangan> searchNoResit(String idKewDok) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.DokumenKewangan u where u.idDokumenKewangan LIKE :idKewDoc");
            q.setString("idKewDoc", "%" + idKewDok + "%");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<SejarahDokumenKewangan> searchNoResitSejarah(String idKewDok) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.SejarahDokumenKewangan u where u.idDokumenKewangan LIKE :idKewDoc");
            q.setString("idKewDoc", "%" + idKewDok + "%");
            return q.list();
        } finally {
            //session.close();
        }
    }

    @Transactional
    public void delete(KumpulanAkaun kumpulanAkaun) {
        kumpulanAkaunDAO.delete(kumpulanAkaun);
    }

    @Transactional
    public void save(KumpulanAkaun kumpulanAkaun) {
        kumpulanAkaunDAO.save(kumpulanAkaun);
    }

    @Transactional
    public void save(AgensiKutipanDokumen agensiKutipanDokumen) {
        agensiKutipanDokumenDAO.save(agensiKutipanDokumen);
    }

    @Transactional
    public void saveOrUpdate(KodKadarCukai kodKadarCukai) {
        kodKadarCukaiDAO.update(kodKadarCukai);
    }

    @Transactional
    public void save(SejarahDokumenKewangan sejarahDokumenKewangan) {
        sejarahDokumenKewanganDAO.save(sejarahDokumenKewangan);
    }

    @Transactional
    public void save(SejarahTransaksi sejarahTransaksi) {
        sejarahTransaksiDAO.save(sejarahTransaksi);
    }

    @Transactional
    public void delete(DokumenKewangan dokumenKewangan) {
        dokumenKewanganDAO.delete(dokumenKewangan);
    }

    @Transactional
    public void delete(Transaksi transaksi) {
        transakasiDAO.delete(transaksi);
    }

    @Transactional
    public void save(KodKadarCukai kodKadarCukai) {
        kodKadarCukaiDAO.save(kodKadarCukai);
    }

    @Transactional
    public void save(LogAkaunKewangan logAkaunKewangan) {
        logAkaunDAO.save(logAkaunKewangan);
    }

    @Transactional
    public void save(LaporanPenyataPemungutItem laporanPenyataPemungutItem) {
        laporanPenyataPemungutDAO.save(laporanPenyataPemungutItem);
    }

    @Transactional
    public void saveOrUpdate(DokumenKewangan dokumenKewangan) {

        dokumenKewanganDAO.save(dokumenKewangan);
    }

    @Transactional
    public void delete(Akaun akaun) {
        akaunDAO.delete(akaun);
    }

    @Transactional
    public void update(DokumenKewangan dokumenKewangan) {

        dokumenKewanganDAO.update(dokumenKewangan);
    }

    @Transactional
    public void saveOrUpdate(DokumenKewanganBayaran dokumenKewanganBayaran) {

        dokumenKewanganBayaranDAO.save(dokumenKewanganBayaran);
    }

    @Transactional
    public void updateDokumenKewanganBayaran(DokumenKewanganBayaran dokumenKewanganBayaran) {

        dokumenKewanganBayaranDAO.update(dokumenKewanganBayaran);
    }

    @Transactional
    public void save(LaporanTanah laporanTanah) {
        laporanTanahDAO.save(laporanTanah);
    }

    @Transactional
    public void update(LaporanTanah laporanTanah) {
        laporanTanahDAO.update(laporanTanah);
    }

    @Transactional
    public void saveOrUpdate(CaraBayaran caraBayaran) {
        caraBayaranDAO.save(caraBayaran);
    }

    @Transactional
    public void updateCaraBayaran(CaraBayaran caraBayaran) {
        caraBayaranDAO.saveOrUpdate(caraBayaran);
    }

    @Transactional
    public void saveOrUpdate(Transaksi transaksi) {
        transakasiDAO.saveOrUpdate(transaksi);
    }

    @Transactional
    public void save(Transaksi transaksi) {
        transakasiDAO.saveOrUpdate(transaksi);
    }

    @Transactional
    public void saveTrans(Transaksi transaksi) {
        transakasiDAO.save(transaksi);
    }

    @Transactional
    public void saveOrUpdate(Akaun akaun) {
        akaunDAO.update(akaun);
    }

    @Transactional
    public void saveOrUpdate(UrusanCMS urusanCMS) {
        urusanCMSDAO.update(urusanCMS);
    }

    @Transactional
    public void saveAkaun(Akaun akaun) {
        akaunDAO.save(akaun);
    }

    @Transactional
    public void deleteTransaksi(Transaksi tr) {
        transakasiDAO.delete(tr);
    }

    @Transactional
    public void deleteCMS(UrusanCMS cms) {
        urusanCMSDAO.delete(cms);
    }

    @Transactional
    public void save(Dokumen dokumen) {
        dokumenDAO.save(dokumen);
    }

    @Transactional
    public void saveOrUpdate(Dokumen dokumen) {
        dokumenDAO.update(dokumen);
    }

    @Transactional
    public void saveOrUpdate(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);
    }

    @Transactional
    public void saveOrUpdate(Versi4k versi4k) {
        versi4kDAO.saveOrUpdate(versi4k);
    }

    @Transactional
    public void saveOrUpdate(KodSekatanKepentingan kodSekatan) {
        kodSekatanDAO.saveOrUpdate(kodSekatan);
    }

    @Transactional
    public void saveOrUpdate(KodSyaratNyata kodSyaratNyata) {
        kodSyaratNyataDAO.saveOrUpdate(kodSyaratNyata);
    }

    /**
     * start method for kumpulan akaun table TAG_AKAUN and TAG_AKAUN_AHLI*
     */
    @Transactional
    public void saveOrUpdateTagAkaun(TagAkaun tagAkaun) {
        tagAkaunDAO.saveOrUpdate(tagAkaun);
        LOG.info("tagAkaun :" + tagAkaun.getIdTag());
    }

    @Transactional
    public void saveorUpdateTagAkaunAhli(List<TagAkaunAhli> senaraiTagAkaunAhli) {
        for (TagAkaunAhli tagAkaunAhli : senaraiTagAkaunAhli) {
            tagAkaunAhliDAO.save(tagAkaunAhli);
            LOG.info("(saveorUpdateTagAkaunAhli) tagAkaunAhli :" + tagAkaunAhli.getIdAhli());
        }
    }

    @Transactional
    public void saveorUpdateAkaun(List<Akaun> senaraiAkaun) {
        for (Akaun akaun : senaraiAkaun) {
            akaunDAO.saveOrUpdate(akaun);
        }
    }

    @Transactional
    public void deleteKumpulanAkaunTag(TagAkaun tagAkaun, List<TagAkaunAhli> senaraiAhliTag) {
        for (TagAkaunAhli tagAkaunAhli : senaraiAhliTag) {
            LOG.info("(delete) idAhli :" + tagAkaunAhli.getIdAhli());
            tagAkaunAhliDAO.delete(tagAkaunAhli);
        }
        LOG.info("(delete) idTag :" + tagAkaun.getIdTag());
        tagAkaunDAO.delete(tagAkaun);
    }

    @Transactional
    public void deleteAkaunAhli(TagAkaunAhli akaunAhli) {
        LOG.info("(delete) idAhli :" + akaunAhli.getIdAhli());
        tagAkaunAhliDAO.delete(akaunAhli);
    }

    /**
     * finish method for kumpulan akaun table TAG_AKAUN and TAG_AKAUN_AHLI*
     */
    @Transactional
    public void saveOrUpdate(DokumenKewanganSiri dokumenKewanganSiri) {
        dokumenKewanganSiriDAO.update(dokumenKewanganSiri);
    }

    @Transactional
    public void saveOrUpdate(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void saveOrUpdate(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    @Transactional
    public void saveOrUpdate(HakmilikUrusan hakmilikUrusan) {
        hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
    }

    @Transactional
    public void deleteHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        LOG.info("(delete) idHakmilikPermohonan :" + hakmilikPermohonan.getId());
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    @Transactional
    public void deleteHakmilikPermohonanList(List<HakmilikPermohonan> list) {
        for (HakmilikPermohonan hp : list) {
            hakmilikPermohonanDAO.delete(hp);
        }
    }

    @Transactional
    public void deleteKandunganFolder(List<KandunganFolder> list) {
        for (KandunganFolder k : list) {
            LOG.info("(delete) idAhli :" + k.getIdKandunganFolder());
            kandunganFolderDAO.delete(k);
        }
    }

    @Transactional
    public void delTrans(List<Transaksi> list) {
        for (Transaksi trans : list) {
//            LOG.info("(delete) idAhli :"+k.getIdKandunganFolder());
            transakasiDAO.delete(trans);
        }
    }

//     @Transactional
//    public void delete (Transaksi transaksi){
//        transakasiDAO.delete(transaksi);
//    }
    @Transactional
    public void deletePermohonan(Permohonan permohonan) {
        permohonanDAO.delete(permohonan);
    }

    @Transactional
    public void delete(BilCukai bilCukai) {
        bilCukaiDAO.delete(bilCukai);
    }

    List<CaraBayaran> findListCaraBayarByID(long idCaraBayaran) {
  try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.CaraBayaran u where u.idCaraBayaran = :idCaraBayaran");
            q.setLong("idCaraBayaran", idCaraBayaran);
            return q.list();
        } finally {
            //session.close();
        }    }

    List<DokumenKewanganBayaran> findListDokumenBayaran(long idCaraBayaran) {
try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.DokumenKewanganBayaran u where u.caraBayaran.idCaraBayaran = :idCaraBayaran");
            q.setLong("idCaraBayaran", idCaraBayaran);
            return q.list();
        } finally {
            //session.close();
        }     }
}

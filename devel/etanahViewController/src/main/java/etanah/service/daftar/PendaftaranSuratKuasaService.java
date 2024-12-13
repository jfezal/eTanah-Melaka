  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikUrusanSuratDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilKuasaHakmilikDAO;
import etanah.dao.WakilKuasaDAO;
//import etanah.dao.WakilPihakDAO;
import etanah.dao.WakilKuasaHakmilikDAO;
import etanah.dao.WakilKuasaPemberiDAO;
import etanah.dao.WakilKuasaPenerimaDAO;
import etanah.dao.PermohonanDokumenDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikUrusanSurat;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumen;
import etanah.model.Pihak;
import etanah.model.WakilHakmilik;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaHakmilik;
import etanah.model.WakilKuasaPemberi;
import etanah.model.WakilKuasaPenerima;
//import etanah.model.WakilPihak;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.fairul
 */
public class PendaftaranSuratKuasaService {
//@Inject
// WakilPihakDAO wakilPihakDAO;

    private static final Logger LOG = Logger.getLogger(PendaftaranSuratKuasaService.class);
    @Inject
    PihakDAO pihakDAO;
    @Inject
    WakilKuasaHakmilikDAO wakilKuasaHakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    WakilKuasaDAO wakilKuasaDAO;
    @Inject
    WakilKuasaPemberiDAO wakilKuasaPemberiDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    @Inject
    PermohonanDokumenDAO permohonanDokumenDAO;
    @Inject
    HakmilikUrusanSuratDAO hakmilikUrusanSuratDAO;

//@Transactional
//    public void save(WakilPihak wakilPihak) {
//        wakilPihakDAO.saveOrUpdate(wakilPihak);
//    }
//@Transactional
//    public void update(WakilPihak wakilPihak) {
//        wakilPihakDAO.update(wakilPihak);
//    }
//new added follow new Table Structure
    @Transactional
    public void saveWakilKuasa(WakilKuasa wakilKuasa) {
        wakilKuasaDAO.save(wakilKuasa);
    }

//Added by Aizuddin to save urusan SW
    @Transactional
    public WakilKuasa saveOrUpdateWakilKuasa(WakilKuasa wakilKuasa) {
        return wakilKuasaDAO.saveOrUpdate(wakilKuasa);
    }

    @Transactional
    public void updatePD(PermohonanDokumen pD) {
        permohonanDokumenDAO.update(pD);

    }

    @Transactional
    public void savePD(PermohonanDokumen pD) {
        permohonanDokumenDAO.save(pD);
    }

    @Transactional
    public void updateWakilKuasa(WakilKuasa wakilKuasa) {
        wakilKuasaDAO.update(wakilKuasa);
    }

    @Transactional
    public void saveWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        wakilKuasaPemberiDAO.save(wakilKuasaPemberi);
    }

    @Transactional
    public void updateWakilKuasaPemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        wakilKuasaPemberiDAO.update(wakilKuasaPemberi);
    }

    @Transactional
    public void saveWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        wakilKuasaPenerimaDAO.save(wakilKuasaPenerima);
    }

//Added by Aizuddin to save urusan SW
    @Transactional
    public WakilKuasaPenerima saveOrUpdateWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        return wakilKuasaPenerimaDAO.saveOrUpdate(wakilKuasaPenerima);
    }

    @Transactional
    public void updateWakilKuasaPenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        wakilKuasaPenerimaDAO.update(wakilKuasaPenerima);
    }

    @Transactional
    public void savePihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void saveWakilKuasaHakmilik(WakilKuasaHakmilik wakilKuasaHakmilik) {
        wakilKuasaHakmilikDAO.save(wakilKuasaHakmilik);
    }

    @Transactional
    public void updateWakilKuasaHakmilik(WakilKuasaHakmilik wakilKuasaHakmilik) {
        wakilKuasaHakmilikDAO.update(wakilKuasaHakmilik);
    }

    @Transactional
    public void deleteAll(WakilKuasaHakmilik wakilKuasaHakmilik) {
        wakilKuasaHakmilikDAO.delete(wakilKuasaHakmilik);
    }

    @Transactional
    public void deletePenerima(WakilKuasaPenerima wakilKuasaPenerima) {
        wakilKuasaPenerimaDAO.delete(wakilKuasaPenerima);
    }

    @Transactional
    public void deletePemberi(WakilKuasaPemberi wakilKuasaPemberi) {
        wakilKuasaPemberiDAO.delete(wakilKuasaPemberi);
    }

    @Transactional
    public void deleteWakilKuasa(WakilKuasa wakilKuasa) {
        wakilKuasaDAO.delete(wakilKuasa);
    }

    @Transactional
    public void saveHakmilikUrusanS(HakmilikUrusanSurat hUrusanSurat) {
        hakmilikUrusanSuratDAO.save(hUrusanSurat);
    }

    @Transactional
    public void updateHakmilikUrusanS(HakmilikUrusanSurat hUrusanSurat) {
        hakmilikUrusanSuratDAO.save(hUrusanSurat);
    }

    @Transactional
    public void deleteTentukan(PermohonanDokumen pD) {
        permohonanDokumenDAO.delete(pD);
    }
    private HakmilikUrusanSurat hakmilikUrusanSurat;

    public WakilKuasa findWakilKuasa(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.idWakil = :idPermohonan";
        return (WakilKuasa) sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan).uniqueResult();
    }

// add by azri 2/7/2013 : serviced used in 'tentukanhakmiik.java'
    public WakilKuasa findWakilKuasaSB(String idPermohonan) {
        String query = "Select wk FROM etanah.model.WakilKuasa wk WHERE wk.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (WakilKuasa) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findListHakmilikPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
// add by azri 2/7/2013 : END

    public PermohonanDokumen findPermohonanDokumen(String idHakmilik, String noRujukan) {
        LOG.info("Test Find Mohon Dokumen :: ID HAKMILIK" + idHakmilik + " NO RUJUKAN :: " + noRujukan);
        String s = "Select pd FROM etanah.model.PermohonanDokumen pd WHERE pd.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik"
                + " and pd.noRujukan = :noRujukan";
        Query q = sessionProvider.get().createQuery(s);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noRujukan", noRujukan);
        LOG.info("SQL STATEMENT :: " + s);
        LOG.info("Test Result:: " + q.uniqueResult());
        return (PermohonanDokumen) q.uniqueResult();
    }

    public List<PermohonanDokumen> findPermohonanDokumenByIdPermohonan(String noRujukan) {
        String s = "Select pd FROM etanah.model.PermohonanDokumen pd WHERE pd.noRujukan = :noRujukan";
        Query q = sessionProvider.get().createQuery(s);
        q.setString("noRujukan", noRujukan);
        return q.list();
    }

    public WakilKuasaHakmilik findWakilKuasaHakmilikByIdWakilHakmilik(String idWakil, String idH) {
        String query = "Select p FROM etanah.model.WakilKuasaHakmilik p WHERE p.wakilKuasa.idWakil = :idWakil"
                + " and p.hakmilik.idHakmilik = :idH";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idWakil", idWakil);
        q.setString("idH", idH);
        return (WakilKuasaHakmilik) q.uniqueResult();
    }

    public HakmilikPermohonan findPH(String idHakmilik, String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmilik.idHakmilik = :idHakmilik"
                + " and p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);

        return (HakmilikPermohonan) q.uniqueResult();

    }

//new added
    public List<WakilKuasa> findWakilKuasaList(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.idWakil = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WakilKuasa> findWakilByIdPermohonanKuasaList(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WakilKuasa> findCertificatePerson(String idCP, String idPemohonan1) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.idWakil = :idCP AND p.permohonan.idPermohonan = :idPemohonan1";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idCP", idCP)
                .setString("idPemohonan1", idPemohonan1);
        return q.list();
    }

    public List<PermohonanDokumen> findLisPermohonanDokumen(String idPermohonan) {
        String stmt = "SELECT pdl FROM etanah.model.PermohonanDokumen pdl WHERE pdl.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(stmt).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WakilKuasaPemberi> findWakilKuasaListPemberi(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE  p.wakilKuasa.idWakil = :idWakil";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idWakil", idWakil);
        return q.list();
    }

    public List<WakilKuasaPemberi> findWakilKuasaListPemberiUsingName(String idWakil, String namaPemberi) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE  p.wakilKuasa.idWakil = :idWakil and p.nama LIKE:namaPemberi";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idWakil", idWakil).setString("namaPemberi", "%" + namaPemberi + "%");
        return q.list();
    }

    public List<WakilKuasaPenerima> findWakilKuasaListPenerima(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idWakil";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idWakil", idWakil);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByIDpihak(String idPihak) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak"
                + " and h.aktif = 'Y'";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPihak", idPihak);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikOnly(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik"
                + " and h.aktif = 'Y'";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilik(String idHakmilik, String jKp, String noKp) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik"
                + " and h.aktif = 'Y'";
        if (StringUtils.isNotBlank(jKp)) {
            query = query + " and h.pihak.jenisPengenalan.kod= :jKp";
        }
        if (StringUtils.isNotBlank(noKp)) {
            query = query + " and h.pihak.noPengenalan= :noKp";
        }

        Query q = sessionProvider.get().createQuery(query)
                .setString("idHakmilik", idHakmilik);
        if (StringUtils.isNotBlank(jKp)) {
            q.setString("jKp", jKp);
        }
        if (StringUtils.isNotBlank(noKp)) {
            q.setString("noKp", noKp);
        }
        return q.list();
    }

    public List<WakilKuasaHakmilik> findWakilHakmilikByIdWakil(String idWakil) {
        String query = "Select h from etanah.model.WakilKuasaHakmilik h where h.wakilKuasa.idWakil = :idWakil"
                + " and h.aktif = 'T'";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idWakil", idWakil);
        return q.list();
    }

    public WakilKuasaPemberi findWakilPemberi(String idWakil, String idPihak) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE p.wakilKuasa.idWakil = :idWakil"
                + " and p.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idWakil", idWakil);
        q.setString("idPihak", idPihak);
        return (WakilKuasaPemberi) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilPenerima(String idWakil, String noPengenalan) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idWakil"
                + " and p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idWakil", idWakil);
        q.setString("noPengenalan", noPengenalan);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public WakilKuasaPemberi findWakilPemberibyIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE p.wakilKuasa.idWakil = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (WakilKuasaPemberi) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilPenerimabyIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (WakilKuasaPenerima) q.uniqueResult();
    }
    
    public List<WakilKuasaPemberi> findWakilPemberibyIdPermohonanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE p.wakilKuasa.idWakil = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WakilKuasaPenerima> findWakilPenerimabyIdPermohonanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public WakilKuasaPenerima findWKPnerimabyIdPnerima(long idPenerima) { // add by azri 17/7/2013: used in "utiliti pembetulan SW/SA/SB"
        String query = "Select wkp FROM etanah.model.WakilKuasaPenerima wkp WHERE wkp.idPenerima = :idPenerima";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPenerima", idPenerima);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public WakilKuasaPemberi findWKPemberibyIdPberi(long idPberi) { // add by azri 17/7/2013: used in "utiliti pembetulan SW/SA/SB"
        String query = "Select wkp FROM etanah.model.WakilKuasaPemberi wkp WHERE wkp.idPemberi = :idPemberi";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPemberi", idPberi);
        return (WakilKuasaPemberi) q.uniqueResult();
    }

    public List<Pihak> findExistPihak(String noPengenalan, String jenisPengenalan, String nama) {
        String query = "Select h from etanah.model.Pihak h where h.noPengenalan = :noPengenalan"
                + " and h.jenisPengenalan.kod = :jenisPengenalan and h.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        q.setString("jenisPengenalan", jenisPengenalan);
        q.setString("nama", nama);
        return q.list();
    }

    public HakmilikUrusan findHakmilikUrusan(String idPermohonan, String idHakmilik) {
        String query = "Select hu from etanah.model.HakmilikUrusan hu where hu.idPerserahan = :idPermohonan"
                + " and hu.hakmilik.idHakmilik = :idHakmilik";

        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);

        return (HakmilikUrusan) q.uniqueResult();
    }

    public HakmilikUrusanSurat findHakmilikUrusanSurat(String idUrusan, String noRujukanSurat) {
        String query = "Select hu FROM etanah.model.HakmilikUrusanSurat hu WHERE hu.urusan.idUrusan = :idUrusan "
                + "and hu.noSurat = :noSurat";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idUrusan", idUrusan);
        q.setString("noSurat", noRujukanSurat);
        return (HakmilikUrusanSurat) q.uniqueResult();
    }

    public List<WakilKuasaPenerima> findWakilKuasaPListUsingNameKodCaw(String namaPenerima, String kodCawangan) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.aktif in ('M','Y') and p.wakilKuasa.aktif is NOT NULL and p.nama LIKE:namaPenerima and p.cawangan.kod = :kodCawangan order by p.wakilKuasa.idWakil asc";
        Query q = sessionProvider.get().createQuery(query).setString("namaPenerima", "%" + namaPenerima + "%").setString("kodCawangan", kodCawangan);
        return q.list();
    }

    public List<WakilKuasaPemberi> findWakilKuasaListPemberiUsingNameKodCaw(String namaPemberi, String kodCawangan) {
        String query = "Select p FROM etanah.model.WakilKuasaPemberi p WHERE p.wakilKuasa.aktif in ('M','Y') and p.wakilKuasa.aktif is NOT NULL and p.nama LIKE:namaPemberi and p.cawangan.kod = :kodCawangan order by p.wakilKuasa.idWakil asc";
        Query q = sessionProvider.get().createQuery(query)
                .setString("namaPemberi", "%" + namaPemberi + "%").setString("kodCawangan", kodCawangan);
        return q.list();
    }

    public void saveHakmilikUrusanSurat(Permohonan permohonan, Pengguna peng) {

        LOG.info("Enter Saving Area");
        HakmilikUrusan hUrusan;
        HakmilikUrusanSurat hUrusanSurat;
        InfoAudit ia = new InfoAudit();
        List<PermohonanDokumen> mohonDokumen = findLisPermohonanDokumen(permohonan.getIdPermohonan());

        LOG.info("Permohonan id: " + permohonan.getIdPermohonan());
        LOG.info("Permohonan id: " + permohonan.getInfoAudit());

        for (PermohonanDokumen pd : mohonDokumen) {
            hUrusan = findHakmilikUrusan(pd.getPermohonan().getIdPermohonan(), pd.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
            LOG.info("hUrusan:" + hUrusan);
            LOG.info("idMohon:" + pd.getPermohonan().getIdPermohonan());
            LOG.info("ID Hakmilik:" + pd.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
            if (hUrusan != null) {
                LOG.info("Enter looping");
                hUrusanSurat = findHakmilikUrusanSurat(String.valueOf(hUrusan.getIdUrusan()), pd.getNoRujukan());
                if (hUrusanSurat != null) {
                    ia = hUrusanSurat.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hUrusanSurat.setInfoAudit(ia);
                    hUrusanSurat.setUrusan(hUrusan);
                    hUrusanSurat.setKodDokumen(pd.getDokumen().getKodDokumen());
                    hUrusanSurat.setNoSurat(pd.getNoRujukan());
                    saveHakmilikUrusanS(hUrusanSurat);
                    LOG.info("Complete Save hakmilikUrusanSurat");
                } else {
//                                                     ia = new InfoAudit();
                    hUrusanSurat = new HakmilikUrusanSurat();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    hUrusanSurat.setInfoAudit(ia);
                    hUrusanSurat.setUrusan(hUrusan);
                    hUrusanSurat.setKodDokumen(pd.getDokumen().getKodDokumen());
                    hUrusanSurat.setNoSurat(pd.getNoRujukan());
                    updateHakmilikUrusanS(hUrusanSurat);
                    LOG.info("Complete Update hakmilikUrusanSurat");
                }

            } else {
                LOG.info("HakmilikUrusan Null");
            }
        }
    }
}

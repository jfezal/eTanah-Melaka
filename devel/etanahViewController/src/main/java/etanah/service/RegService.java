/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import java.text.ParseException;
import java.util.List;
//import etanah.util.StringUtils;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.KodAgensi;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.Pihak;
import etanah.model.Permohonan;
import etanah.model.SejarahHakmilik;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.model.KodSekatanKepentingan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodSyaratNyata;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.KodKadarCukai;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodSeksyen;
import etanah.model.KodSekatanKepentingan;
import org.apache.log4j.Logger;
import etanah.model.KodKegunaanTanah;
import etanah.model.HakmilikUrusan;
import etanah.model.KodHakmilik;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import etanah.model.Akaun;
import etanah.dao.AkaunDAO;
import etanah.model.KodDaerah;
import etanah.dao.KodDaerahDAO;
import etanah.service.common.HakmilikUrusanService;
import org.apache.commons.lang.StringUtils;
import etanah.model.gis.PelanGIS;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.model.KodUOM;
import etanah.model.PermohonanPihak;
import etanah.model.HakmilikAsal;
import etanah.dao.HakmilikAsalDAO;
import etanah.dao.HakmilikLamaDAO;
import etanah.dao.HakmilikPetakAksesoriDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.model.HakmilikSebelum;
import etanah.dao.HakmilikSebelumDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikLama;
import etanah.model.HakmilikPetakAksesori;
import etanah.model.KodCawanganJabatan;
import etanah.model.Notis;
import etanah.model.Pemohon;
import etanah.model.PermohonanBangunan;
import etanah.model.WakilPihak;
import java.util.ArrayList;

/**
 *
 * @author khairil
 */
public class RegService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    HakmilikAsalDAO hakmilikAsalDao;
    @Inject
    NotisDAO notisDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDao;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDao;
    @Inject
    PihakDAO pihakDao;
    @Inject
    HakmilikPihakBerkepentinganDAO pihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikAsalPermohonanDAO mohonHakmilikAsalDAO;
    @Inject
    HakmilikSebelumPermohonanDAO mohonHakmilikSblmDAO;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    HakmilikUrusanService huService;
    @Inject
    KodBandarPekanMukimDAO kodBPMDAO;
    @Inject
    PermohonanPihakDAO mohonPihakDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    HakmilikPetakAksesoriDAO hpaDAO;
    @Inject
    HakmilikLamaDAO hakmilikLamaDAO;
//    @Inject
//    StringUtils sUtils;
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    public KodCawanganJabatan getJabatanPendaftaranByCaw(String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodCawanganJabatan u where u.cawangan.kod =:kodCaw and u.jabatan.kod ='16'");
        q.setString("kodCaw", kodCaw);
        List<KodCawanganJabatan> lkcj = q.list();
        KodCawanganJabatan kcj = new KodCawanganJabatan();
        if (lkcj.size() > 0) {
            kcj = lkcj.get(0);
        } else {
            kcj = null;
        }
        return kcj;
    }

    public List<HakmilikAsal> cariHakmilikAsal(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikAsal u where u.hakmilikAsal =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public KodDaerah findKodDaerahbyNama(String namaDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodDaerah kd where kd.nama =:namaDaerah");
        q.setString("namaDaerah", namaDaerah);
        List<KodDaerah> lkd = q.list();
        KodDaerah kd = new KodDaerah();
        if (lkd.size() > 0) {
            kd = lkd.get(0);
        } else {
            kd = null;
        }
        return kd;
    }

    public KodBandarPekanMukim findKodBPMbyNama(String namaBPM) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim kd where kd.nama =:namaBPM");
        q.setString("namaBPM", namaBPM);
        List<KodBandarPekanMukim> lkd = q.list();
        KodBandarPekanMukim kd = new KodBandarPekanMukim();
        if (lkd.size() > 0) {
            kd = lkd.get(0);
        } else {
            kd = null;
        }
        return kd;
    }

    public List<HakmilikAsal> cariHakmilikAsalDariHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikAsal u where u.hakmilik.idHakmilik =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanBangunan> senaraiBangunan(Permohonan p) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.PermohonanBangunan u where u.permohonan.idPermohonan =:idPermohonan");
        q.setString("idPermohonan", p.getIdPermohonan());
        return q.list();
    }

    public List<HakmilikSebelum> cariHakmilikSebelum(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikSebelum u where u.hakmilikSebelum =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikSebelum> cariHakmilikSebelumDariHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikSebelum u where u.hakmilik.idHakmilik =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilikStrata(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.hakmilik.idHakmilik =:idHakmilik and h.kodStatusHakmilik.kod = 'D'");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

//    public PelanGIS cariPelan(String kodNegeri, String kodDaerah, String kodBPM, String noLot,String jenisPelan) {
//        logger.debug(":cariPelan: kodBPM " + kodBPM + " noLot: " + noLot + " jenisPelan: " + jenisPelan);
//         //KodBandarPekanMukim kbpm = kodBPMDAO.findById(Integer.parseInt(kodBPM));
//        //String kodBPMOG = kbpm.getbandarPekanMukim();
////        String tmp = etanah.util.StringUtils.removeLeadingZeros(noLot);
//        StringBuilder b = new StringBuilder("Select u from etanah.model.gis.PelanGIS u");
//        Session s = sessionProvider.get();
////        Query q = s.createQuery("from etanah.model.gis.PelanGIS u where u.pelanGISPK.kodNegeri.kod =:kodNegeri and u.pelanGISPK.kodDaerah.kod =:kodDaerah and u.pelanGISPK.kodMukim =:kodBPM and u.pelanGISPK.noLot =:noLot and u.jenisPelan =:jenisPelan");
//        Query q = s.createQuery(b.toString());
////        q.setString("kodNegeri", kodNegeri);
////        q.setString("kodDaerah", kodDaerah);
////        q.setString("kodBPM", kodBPM);
////        q.setString("noLot", noLot.trim());
////        q.setString("jenisPelan", jenisPelan);
//        logger.debug("::: FINISH :::");
//        //return (PelanGIS) q.uniqueResult();
//        List<PelanGIS> lp = q.list();
//        PelanGIS p = new PelanGIS();
//        if (lp.size() > 0) {
//            p = lp.get(0);
//        } else {
//            p = null;
//        }
//        return p;
//    }
    public String cariPathPelan(String kodNegeri, String kodDaerah, String kodBPM, String kodSeksyen, String noLot, String jenisPelan) {
        String path = "";
        logger.debug(":cariPelan: kodBPM " + kodBPM + "kodSeksyen" + kodSeksyen + " noLot: " + noLot + " jenisPelan: " + jenisPelan);
        //KodBandarPekanMukim kbpm = kodBPMDAO.findById(Integer.parseInt(kodBPM));
        //String kodBPMOG = kbpm.getbandarPekanMukim();
//        String tmp = etanah.util.StringUtils.removeLeadingZeros(noLot);
        StringBuilder b = new StringBuilder("Select u.pelanTif from etanah.model.gis.PelanGIS u where u.pelanGISPK.kodNegeri.kod =:kodNegeri and u.pelanGISPK.kodDaerah.kod =:kodDaerah and u.pelanGISPK.kodMukim =:kodBPM and u.pelanGISPK.kodSeksyen =:kodSeksyen and u.pelanGISPK.noLot =:noLot and u.jenisPelan =:jenisPelan");
        Session s = sessionProvider.get();
//        Query q = s.createQuery("from etanah.model.gis.PelanGIS u where u.pelanGISPK.kodNegeri.kod =:kodNegeri and u.pelanGISPK.kodDaerah.kod =:kodDaerah and u.pelanGISPK.kodMukim =:kodBPM and u.pelanGISPK.noLot =:noLot and u.jenisPelan =:jenisPelan");
        Query q = s.createQuery(b.toString());
        q.setString("kodNegeri", kodNegeri);
        q.setString("kodDaerah", kodDaerah);
        q.setString("kodBPM", kodBPM);
        q.setString("kodSeksyen", kodSeksyen);
        q.setString("noLot", noLot.trim());
        q.setString("jenisPelan", jenisPelan);
        logger.debug("::: FINISH :::");
        //return (PelanGIS) q.uniqueResult();
        List<String> paths = q.list();
        if (paths.size() > 0) {
            path = paths.get(0);
        }
        return path;
    }

    public Hakmilik checkNoLot(String kodNegeri, String kodDaerah, String kodBPM, String noLot, String jenisLot) {
        logger.debug(":cariNoLot: kodBPM " + kodBPM + " noLot: " + noLot);
//        KodBandarPekanMukim kbpm = kodBPMDAO.findById(Integer.parseInt(kodBPM));
//        String kodBPMOG = kbpm.getbandarPekanMukim();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.daerah.kod =:kodDaerah and u.bandarPekanMukim.bandarPekanMukim =:kodBPM and u.noLot =:noLot and u.lot.kod =:jenisLot");
        //q.setString("kodNegeri", kodNegeri);
        q.setString("kodDaerah", kodDaerah);
        q.setString("kodBPM", kodBPM);
        q.setString("noLot", noLot);
        q.setString("jenisLot", jenisLot);
        logger.debug("::: FINISH :::");
        //return (Hakmilik) q.uniqueResult();
        List<Hakmilik> lh = q.list();
//        HakmilikUrusan hu = (HakmilikUrusan) q.uniqueResult();
        Hakmilik h = new Hakmilik();
        if (lh.size() > 0) {
            h = lh.get(0);
        } else {
            h = null;
        }
        return h;
    }

    public Hakmilik checkNoLot1(String kodNegeri, String kodDaerah, String kodBPM, String noLot, String kodSeksyen, String jenisLot) {
        logger.debug(":cariNoLot: kodBPM " + kodBPM + " noLot: " + noLot);
//        KodBandarPekanMukim kbpm = kodBPMDAO.findById(Integer.parseInt(kodBPM));
//        String kodBPMOG = kbpm.getbandarPekanMukim();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.daerah.kod =:kodDaerah and u.seksyen.kod =:kodSeksyen and u.bandarPekanMukim.bandarPekanMukim =:kodBPM and u.noLot =:noLot and u.kodStatusHakmilik.kod in ('D','T') and u.lot.kod =:jenisLot");
        //q.setString("kodNegeri", kodNegeri);
        q.setString("kodDaerah", kodDaerah);
        q.setString("kodBPM", kodBPM);
        q.setString("kodSeksyen", kodSeksyen);
        q.setString("noLot", noLot);
        q.setString("jenisLot", jenisLot);
        logger.debug("::: FINISH :::");
        //return (Hakmilik) q.uniqueResult();
        List<Hakmilik> lh = q.list();
//        HakmilikUrusan hu = (HakmilikUrusan) q.uniqueResult();
        Hakmilik h = new Hakmilik();
        if (lh.size() > 0) {
            h = lh.get(0);
        } else {
            h = null;
        }
        return h;
    }

    public Hakmilik checkNoLot3(String kodNegeri, String kodDaerah, String kodBPM, String noLot, String kodSeksyen, String jenisLot) {
        logger.debug(":cariNoLot: kodBPM " + kodBPM + " noLot: " + noLot);
//        KodBandarPekanMukim kbpm = kodBPMDAO.findById(Integer.parseInt(kodBPM));
//        String kodBPMOG = kbpm.getbandarPekanMukim();
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Hakmilik u where u.daerah.kod =:kodDaerah and u.seksyen.kod =:kodSeksyen and u.bandarPekanMukim.bandarPekanMukim =:kodBPM and u.noLot =:noLot and u.lot.kod =:jenisLot");
        //q.setString("kodNegeri", kodNegeri);
        q.setString("kodDaerah", kodDaerah);
        q.setString("kodBPM", kodBPM);
        q.setString("kodSeksyen", kodSeksyen);
        q.setString("noLot", noLot);
        q.setString("jenisLot", jenisLot);
        logger.debug("::: FINISH :::");
        //return (Hakmilik) q.uniqueResult();
        List<Hakmilik> lh = q.list();
//        HakmilikUrusan hu = (HakmilikUrusan) q.uniqueResult();
        Hakmilik h = new Hakmilik();
        if (lh.size() > 0) {
            h = lh.get(0);
        } else {
            h = null;
        }
        return h;
    }

    public List<KodUOM> senaraiKodUOMByKatTanah(String kategoriTanah) {

        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "from etanah.model.KodUOM";
            if (kategoriTanah.equals("3")) {
                query += " where kod in ('H','M','K','E')";
            } else if (kategoriTanah.equals("1") || kategoriTanah.equals("2")) {
                query += " where kod in ('M','K','H','E')";
            } else {
                query += " where kod in ('K','M','H','E','D','P')";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodUOM> senaraiKodUOMByJarak() {

        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('H','M','K','E')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    @Transactional
    public void saveOrUpdate(Akaun akaun) {
        akaunDAO.saveOrUpdate(akaun);
    }

    @Transactional
    public void saveOrUpdate(PermohonanRujukanLuar mrl) {
        permohonanRujukanLuarDAO.saveOrUpdate(mrl);
    }
    @Transactional
    public void saveOrUpdate(HakmilikAsalPermohonan hap) {
        hakmilikAsalPermohonanDAO.saveOrUpdate(hap);
    }

    public void saveOrUpdate(List<Akaun> akauns) {
        for (Akaun akaun : akauns) {
            akaunDAO.saveOrUpdate(akaun);
        }
    }

    @Transactional
    public void saveOrUpdateWTrans(Akaun akaun) {
        akaunDAO.saveOrUpdate(akaun);
    }

    @Transactional
    public void saveOrUpdateNotis(Notis notis) {
        notisDAO.saveOrUpdate(notis);
    }

    public boolean periksaHalangan(Permohonan permohonan, Hakmilik h) {
        logger.debug("::checking halangan::");
        logger.debug("kod serah : " + permohonan.getKodUrusan().getKodPerserahan().getKod());
        logger.debug("kod urusan : " + permohonan.getKodUrusan().getKod());
        //logger.debug("halangan : " + huService.findHalanganHakmilikUrusanByIdHakmilik(h.getIdHakmilik()).size());
        //PMHUK
        List<HakmilikUrusan> lhu = huService.findHalanganUrusniagaKavietHakmilikUrusanByIdHakmilik(h.getIdHakmilik());
        if (lhu.size() > 0) {
            Date tarikhDaftar = lhu.get(0).getTarikhDaftar();
            Calendar calendar = new GregorianCalendar();
            Calendar calendarNow = new GregorianCalendar();
            calendarNow.setTime(new java.util.Date());
            calendar.setTime(tarikhDaftar);

            int tahun = lhu.get(0).getTempohTahun();
            int bulan = lhu.get(0).getTempohBulan();
            int hari = lhu.get(0).getTempohHari();
            int totalHari = tahun * 365 + bulan * 12 + hari;
            calendar.add(Calendar.DATE, totalHari);
            logger.debug("totalHari : " + totalHari);
            logger.debug("tarikh daftar : " + tarikhDaftar);
            logger.debug("date now :" + calendarNow.getTime());
            logger.debug("tempoh + tarikhdaftar :" + calendar.getTime());
            //logger.debug("compare date :"+calendar.getTime().compareTo(calendarNow.getTime()));

            if (permohonan.getKodUrusan().getKodPerserahan().getKod().equals("SC")
                    || permohonan.getKodUrusan().getKod().equals("KVST")
                    || permohonan.getKodUrusan().getKod().equals("KVAS")
                    || permohonan.getKodUrusan().getKod().equals("KVAT")
                    || permohonan.getKodUrusan().getKod().equals("KVLK")
                    || permohonan.getKodUrusan().getKod().equals("KVLP")
                    || permohonan.getKodUrusan().getKod().equals("KVLS")
                    || permohonan.getKodUrusan().getKod().equals("KVLT")
                    || permohonan.getKodUrusan().getKod().equals("KVPK")
                    || permohonan.getKodUrusan().getKod().equals("KVPPT")
                    || permohonan.getKodUrusan().getKod().equals("KVPS")
                    || permohonan.getKodUrusan().getKod().equals("KVPT")
                    || permohonan.getKodUrusan().getKod().equals("KVSK")
                    || permohonan.getKodUrusan().getKod().equals("KVSPT")
                    || permohonan.getKodUrusan().getKod().equals("KVSS")
                    || permohonan.getKodUrusan().getKod().equals("KVST")
                    && huService.findHalanganUrusniagaKavietHakmilikUrusanByIdHakmilik(h.getIdHakmilik()).size() > 0
                    || calendar.getTime().after(calendarNow.getTime())) {
                return true;
                //PMHBE
            } else if (permohonan.getKodUrusan().getKodPerserahan().getKod().equals("SC") && huService.findHalanganUrusniagaHakmilikUrusanByIdHakmilik(h.getIdHakmilik()).size() > 0) {
                return true;
                //PMHUN
            } else if (permohonan.getKodUrusan().getKod().equals("GDL")
                    || permohonan.getKodUrusan().getKod().equals("GDCEL")
                    || permohonan.getKodUrusan().getKod().equals("GDB")
                    || permohonan.getKodUrusan().getKod().equals("KVPB")
                    || permohonan.getKodUrusan().getKod().equals("KVSTB")
                    || permohonan.getKodUrusan().getKod().equals("KVLB")
                    || permohonan.getKodUrusan().getKod().equals("KVAB")
                    || permohonan.getKodUrusan().getKod().equals("PMTB")
                    || permohonan.getKodUrusan().getKod().equals("MGGB")
                    && huService.findHalanganBatalEndorsanHakmilikUrusanByIdHakmilik(h.getIdHakmilik()).size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<KodDaerah> cariDaerah(String kodDaerah) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodDaerah u where u.kod =:kodDaerah");
        q.setString("kodDaerah", kodDaerah);
        logger.debug("::: FINISH :::");
        return q.list();
    }

    public KodKegunaanTanah cariKegunaanTanah(String kodGunaTanah, String kodKatTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kod =:kodGunaTanah and u.kategoriTanah.kod =:kodKatTanah");
        q.setString("kodGunaTanah", kodGunaTanah);
        q.setString("kodKatTanah", kodKatTanah);
        logger.debug("::: FINISH :::");
        return (KodKegunaanTanah) q.uniqueResult();
    }

    public List<WakilPihak> CariSubBadanUrus(String idPermohonan, String idPihak, String idBadan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.WakilPihak u where u.permohonan.idPermohonan =:idPermohonan and u.pihak.idPihak =:idPihak and u.noRujukan =:idBadan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPihak", idPihak);
        q.setString("idBadan", idBadan);
        logger.debug("::: FINISH :::");
        return q.list();
    }

    public KodBandarPekanMukim cariBPM(String BPM, String kodDaerah) {
        logger.debug("::: START SEARCHING FOR : " + BPM + " AND : " + kodDaerah + " :::");
        Session s = sessionProvider.get();
//        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim u where u.kod = :BPM and u.daerah.kod = :kodDaerah");
        Query q = s.createQuery("from etanah.model.KodBandarPekanMukim u where u.bandarPekanMukim =:BPM and u.daerah.kod =:kodDaerah");
        q.setString("BPM", BPM);
        q.setString("kodDaerah", kodDaerah);
        logger.debug("::: FINISH :::");
        return (KodBandarPekanMukim) q.uniqueResult();
    }

    public boolean checkAge(String tarikhLahir) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        Calendar calendarToday = new GregorianCalendar();
        calendarToday.setTime(new java.util.Date());
        Date tarikhLahir1 = sdf.parse(tarikhLahir);
        calendar.setTime(tarikhLahir1);
        int age = calendarToday.get(calendarToday.YEAR) - calendar.get(calendar.YEAR);
        if (age > 18) {
            return true;
        } else {
            return false;
        }
    }
//    public boolean checkPemohonMelayu(String tarikhLahir) {
//        Calendar calendar = new GregorianCalendar();
//        Calendar calendarToday = new GregorianCalendar();
//        calendarToday.setTime(new java.util.Date());
//        Date tarikhLahir1 = sdf.parse(tarikhLahir);
//        calendar.setTime(tarikhLahir1);
//        int age = calendarToday.get(calendarToday.YEAR) - calendar.get(calendar.YEAR);
//        if (age > 18) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public String getFormattedNoPengenalan(String noPengenalan, String jenisPengenalan) {
        if (noPengenalan != null && noPengenalan.length() == 12 && jenisPengenalan.equals("B")) {
            return noPengenalan.substring(1, 6) + "-" + noPengenalan.substring(7, 2) + "-" + noPengenalan.substring(9, 4);
        }
        return noPengenalan;
    }

    public List<HakmilikPermohonan> senaraiHakmilikPermohonanByIDHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.hakmilik.idHakmilik =:idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<KodHakmilik> senaraiAllKodHakmilikPTG(String kodUrusan) {
        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "select u from etanah.model.KodHakmilik";
            query += " u where u.kod in ('HSD','GRN','PN')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> senaraiAllKodHakmilikPTD(String kodUrusan) {
        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            //HK - GM,PM,GMM ... HS - HSM,HMM
            String query = "select u from etanah.model.KodHakmilik";
            query += " u where u.kod in ('HSM','HMM','GM','PM' ,'GMM')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> senaraiKodHakmilikPTG(String kodUrusan) {
        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "select u from etanah.model.KodHakmilik";
            if (kodUrusan.startsWith("HS")) {
                query += " u where u.kod in ('HSD')";
            } else {
                query += " u where u.kod in ('GRN','PN')";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> senaraiKodHakmilikPTD(String kodUrusan) {
        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "select u from etanah.model.KodHakmilik";
            if (kodUrusan.startsWith("HS")) {
                query += " u where u.kod in ('HSM','HMM')";
            } else {
                query += " u where u.kod in ('GM','PM' ,'GMM')";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    // add by azri 30/7/2013 : for PTD N9 only
    public List<KodHakmilik> listKodHakmilikPTDns(String kodUrusan) {
        try {
            String query = "select u from etanah.model.KodHakmilik";
            if (kodUrusan.startsWith("HS")) {
                query += " u where u.kod in ('HSM')";
            } else {
                query += " u where u.kod in ('GM','PM')";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> listAllKodHakmilikPTDns(String kodUrusan) { //PTD N9 only
        try {
            String query = "select u from etanah.model.KodHakmilik";
            query += " u where u.kod in ('HSM','GM','PM')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> listKodHakmilikByKod(String[] kodHakmilik) { //PTD N9 only
        try {
            String query = "select u from etanah.model.KodHakmilik";
            query += " u where u.kod in (:kod)";
            Query q = sessionProvider.get().createQuery(query).setParameterList("kod", kodHakmilik);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodKegunaanTanah> getSenaraiKodGunaTanahByKatTanah(String kodKatTanah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = :kodKatTanah and u.aktif = 'Y' order by u.nama ASC");
            q.setString("kodKatTanah", kodKatTanah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodBandarPekanMukim> findByIdMhn(String KodDaerah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :KodDaerah");
            q.setString("KodDaerah", KodDaerah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMByDaerah(String kodDaerah) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodBandarPekanMukim u";
            if (StringUtils.isNotBlank(kodDaerah)) {
                query += " where u.daerah.kod = :kodDaerah";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(kodDaerah)) {
                q.setString("kodDaerah", kodDaerah);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSeksyen> getSenaraiKodSeksyenByBPM(String kodBpm, String kodDaerah) {
        logger.debug("kodBpm at regService :" + kodBpm);
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select o from etanah.model.KodSeksyen o,etanah.model.KodBandarPekanMukim p where "
                    + "p.kod = o.kodBandarPekanMukim.kod "
                    + "and p.bandarPekanMukim=:kodBpm "
                    + "and p.daerah.kod = :kodDaerah order by o.nama");
//            select * from KICKER.KOD_SEKSYEN ,kod_bpm where kod_bpm.kod = kod_seksyen.KOD_BPM and bpm='40'
            q.setString("kodBpm", kodBpm);
            q.setString("kodDaerah", kodDaerah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodKadarCukai> searchKadarCukai(String kodBpm, String kodTanah, Long luas) {
        logger.debug("::start search kadar cukai");
        logger.debug("kodBpm" + kodBpm);
        logger.debug("kodTanah" + kodTanah);
        logger.debug("luas" + luas);
        try {
            Session s = sessionProvider.get();
//            Query q = s.createQuery("from etanah.model.KodKadarCukai u where u.kegunaanTanah.kod =:kodTanah and u.kodSyaratNyata.kod =:kodSyaratNyata");
            Query q = s.createQuery("from etanah.model.KodKadarCukai u where u.bandarPekanMukim =:kodBpm and u.kegunaanTanah.kod =:kodTanah and u.luas=:luas");
            q.setString("kodBpm", kodBpm);
            q.setString("kodTanah", kodTanah);
            q.setLong("luas", luas);
            logger.debug("size" + q.list().size());
            return q.list();
        } finally {
        }
    }

    public List<KodKadarCukai> searchKadarCukai(String kodBpm, String kodGunaTanah, String kodTanah, Long luas) {
        logger.debug("::start search kadar cukai");
        logger.debug("kodBpm" + kodBpm);
        //logger.debug("kodTanah" + kodTanah);
        logger.debug("luas" + luas);
        try {
            Session s = sessionProvider.get();
//            Query q = s.createQuery("from etanah.model.KodKadarCukai u where u.kegunaanTanah.kod =:kodTanah and u.kodSyaratNyata.kod =:kodSyaratNyata");
            Query q = s.createQuery("from etanah.model.KodKadarCukai u where u.bandarPekanMukim =:kodBpm and u.kegunaanTanah =:kodGunaTanah and u.luas=:luas and u.aktif=:aktif");
            q.setString("kodBpm", kodBpm);
            q.setString("kodGunaTanah", kodGunaTanah);
            //q.setString("kodTanah", kodTanah);
            q.setLong("luas", luas);
            q.setString("aktif", "Y");
            logger.debug("size" + q.list().size());
            return q.list();
        } finally {
        }
    }

    public Hakmilik searchHakmilik(int kodBPM, String kodDaerah, String jenisLot, String noLot,
            String kodHakmilik, String noHakmilik, String kodSeksyen) {
        try {
//            Session s = sessionProvider.get();
            logger.debug("in searchHakmilik");
            String query = "Select u from etanah.model.Hakmilik u where";

            if (StringUtils.isNotBlank(kodDaerah)) {
                query += " u.daerah.kod = :kodDaerah";
            }

            if (kodBPM != 0) {
                query += " AND u.bandarPekanMukim.kod = :kodBPM";
            }

            if (StringUtils.isNotBlank(jenisLot)) {
                query += " AND u.lot.kod = :jenisLot";
            }

            if (StringUtils.isNotBlank(noLot)) {
                query += " AND u.noLot =:noLot";
            }

            if (StringUtils.isNotBlank(kodHakmilik)) {
                query += " AND u.kodHakmilik.kod =:kodHakmilik";
            }

            if (StringUtils.isNotBlank(noHakmilik)) {
                query += " AND u.noHakmilik =:noHakmilik AND u.idHakmilikInduk is null";
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                query += " AND u.seksyen.kod =:kodSeksyen";
            } else {
                query += " AND u.seksyen is null";
            }

            logger.debug("query :" + query);

            Query q = sessionProvider.get().createQuery(query);

            if (StringUtils.isNotBlank(kodDaerah)) {
                q.setString("kodDaerah", kodDaerah);
            }

            if (kodBPM != 0) {
                q.setInteger("kodBPM", kodBPM);
            }

            if (StringUtils.isNotBlank(jenisLot)) {
                q.setString("jenisLot", jenisLot);
            }
            if (StringUtils.isNotBlank(noLot)) {
                q.setString("noLot", noLot);
            }
            if (StringUtils.isNotBlank(kodHakmilik)) {
                q.setString("kodHakmilik", kodHakmilik);
            }
            if (StringUtils.isNotBlank(noHakmilik)) {
                q.setString("noHakmilik", noHakmilik);
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                q.setString("kodSeksyen", kodSeksyen);
            }
            //return q.list();
            logger.debug("out searchHakmilik:q.size :" + q.list().size());
            return (Hakmilik) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public Hakmilik searchHakmilikStrata(int kodBPM, String kodDaerah, String jenisLot, String noLot,
            String kodHakmilik, String noHakmilik, String kodSeksyen, String noBangunan, String noTingkat, String noPetak) {
        try {

            logger.debug("in searchHakmilik");
            String query = "Select u from etanah.model.Hakmilik u where";

            if (StringUtils.isNotBlank(kodDaerah)) {
                query += " u.daerah.kod = :kodDaerah";
            }

            if (kodBPM != 0) {
                query += " AND u.bandarPekanMukim.kod = :kodBPM";
            }

            if (StringUtils.isNotBlank(jenisLot)) {
                query += " AND u.lot.kod = :jenisLot";
            }

            if (StringUtils.isNotBlank(noLot)) {
                query += " AND u.noLot =:noLot";
            }

            if (StringUtils.isNotBlank(kodHakmilik)) {
                query += " AND u.kodHakmilik.kod =:kodHakmilik";
            }

            if (StringUtils.isNotBlank(noHakmilik)) {
                query += " AND u.noHakmilik =:noHakmilik";
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                query += " AND u.seksyen.kod =:kodSeksyen";
            } else {
                query += " AND u.seksyen is null";
            }

            if (StringUtils.isNotBlank(noBangunan)) {
                query += " AND u.noBangunan =:noBangunan";
            }
            if (StringUtils.isNotBlank(noTingkat)) {
                query += " AND u.noTingkat =:noTingkat";
            }
            if (StringUtils.isNotBlank(noPetak)) {
                query += " AND u.noPetak =:noPetak";
            }

            query += " AND u.idHakmilikInduk is not null";

            logger.debug("query :" + query);

            Query q = sessionProvider.get().createQuery(query);

            if (StringUtils.isNotBlank(kodDaerah)) {
                q.setString("kodDaerah", kodDaerah);
            }

            if (kodBPM != 0) {
                q.setInteger("kodBPM", kodBPM);
            }

            if (StringUtils.isNotBlank(jenisLot)) {
                q.setString("jenisLot", jenisLot);
            }
            if (StringUtils.isNotBlank(noLot)) {
                q.setString("noLot", noLot);
            }
            if (StringUtils.isNotBlank(kodHakmilik)) {
                q.setString("kodHakmilik", kodHakmilik);
            }
            if (StringUtils.isNotBlank(noHakmilik)) {
                q.setString("noHakmilik", noHakmilik);
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                q.setString("kodSeksyen", kodSeksyen);
            }
            if (StringUtils.isNotBlank(noBangunan)) {
                q.setString("noBangunan", noBangunan);
            }
            if (StringUtils.isNotBlank(noTingkat)) {
                q.setString("noTingkat", noTingkat);
            }
            if (StringUtils.isNotBlank(noPetak)) {
                q.setString("noPetak", noPetak);
            }
            //return q.list();
            logger.debug("out searchHakmilik:q.size :" + q.list().size());
            return (Hakmilik) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public List<KodSyaratNyata> searchKodSyaratNyata(String kod, String kod_caw, String syaratNyata, String kodKatTanah) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw ";

            if (kod != null) {
                query += "AND u.kodSyarat LIKE :kod ";
            }

            if (syaratNyata != null) {
                query += "AND lower(u.syarat) LIKE :syaratNyata ";
            }

            if (kodKatTanah != null) {
                query += "AND u.kategoriTanah.kod =:kodKatTanah ";
            }

            query += "order by u.kodSyarat ASC";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("kod_caw", kod_caw);

            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }

            if (syaratNyata != null) {
                q.setString("syaratNyata", "%" + syaratNyata.toLowerCase() + "%");
            }

            if (kodKatTanah != null) {
                q.setString("kodKatTanah", kodKatTanah);
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSekatanKepentingan> searchKodSekatan(String kod, String kod_caw, String sekatan) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSekatanKepentingan u where u.cawangan.kod = :kod_caw ";

            if (kod != null) {
                query += " AND lower(u.kodSekatan) LIKE :kod ";
            }

            if (sekatan != null) {
                query += " AND lower(u.sekatan) LIKE :sekatan";
            }

            query += " order by u.kodSekatan";

            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }
            if (sekatan != null) {
                q.setString("sekatan", "%" + sekatan.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public KodSekatanKepentingan searchKodSekatanByCaw(String kod, String kod_caw) {
        try {
            String query;
            logger.debug("kodSekatan :" + kod);
            logger.debug("kodCaw :" + kod_caw);
//            Session s = sessionProvider.get();
            if (("04".equals(conf.getProperty("kodNegeri")))) {
                query = "Select u from etanah.model.KodSekatanKepentingan u where u.aktif = 'Y' AND u.cawangan.kod =:kod_caw AND u.kodSekatan =:kod ";
            } else {
                query = "Select u from etanah.model.KodSekatanKepentingan u where u.aktif = 'Y' AND u.cawangan.kod =:kod_caw AND u.kodSekatan =:kod ";
            }

            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            q.setString("kod", kod);

            return (KodSekatanKepentingan) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public KodSekatanKepentingan searchKodSekatanNULLByCaw(String kod_caw) {
        try {
            logger.debug("kodCaw :" + kod_caw);
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSekatanKepentingan u where u.cawangan.kod =:kod_caw AND u.kodSekatan = '0000000' ";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            return (KodSekatanKepentingan) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public KodSyaratNyata searchKodSyaratByCaw(String kod, String kod_caw) {
        try {
            logger.debug("kodSyarat :" + kod);
            logger.debug("kodCaw :" + kod_caw);
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw AND u.kodSyarat =:kod ";

            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            q.setString("kod", kod);

            return (KodSyaratNyata) q.uniqueResult();
        } finally {
            //session.close();
        }
    }
//    public List KodSyaratNyata searchKodSyaratByCawAndKodKATG(String kodKATG, String kod_caw) {
//        try {
//            logger.debug("kodKATG :" + kodKATG);
//            logger.debug("kodCaw :" + kod_caw);
////            Session s = sessionProvider.get();
//            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw AND u.kategoriTanah.kod =:kodKATG order by u.kodSyarat desc";
//
//            Query q = sessionProvider.get().createQuery(query);
//            q.setString("kod_caw", kod_caw);
//            q.setString("kodKATG", kodKATG);
//
//            return q.list();
////            return (KodSyaratNyata) q.uniqueResult();
//        } finally {
//            //session.close();
//        }
//    }

    public List<KodSyaratNyata> searchKodSyaratByCawAndKodKATG(String kodKATG, String kod_caw) {
        String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw "
                + "AND u.kategoriTanah.kod =:kodKATG "
                + "order by u.kod desc";
        Query q = sessionProvider.get().createQuery(query)
                .setString("kodKATG", kodKATG)
                .setString("kod_caw", kod_caw);
        return q.list();
    }

    public List<KodSyaratNyata> searchKodSyaratByCawOnly(String kodCaw) {
        String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kodCaw "
                + "AND u.aktif='Y' "
                + "order by u.kod desc";
        Query q = sessionProvider.get().createQuery(query)
                .setString("kodCaw", kodCaw);
        return q.list();
    }

    public KodSyaratNyata searchKodSyaratByCawAndkodSyarat(String kodSyarat, String kod_caw) {
        try {
            logger.debug("kodSyarat :" + kodSyarat);
            logger.debug("kodCaw :" + kod_caw);
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw AND u.kodSyarat =:kod ";

            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            q.setString("kod", kodSyarat);

            return (KodSyaratNyata) q.uniqueResult();
        } finally {
            //session.close();
        }
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public List<HakmilikAsal> searchHakmilikAsal(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikAsal u where u.hakmilik = :hakmilik");
            q.setString("hakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<Hakmilik> searchHakmilikByidHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Hakmilik u where u.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikUrusan> searchHakmilikUrusan(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikUrusan u where u.hakmilik = :hakmilik");
            q.setString("hakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikUrusan> searchHakmilikUrusanActive(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikUrusan u where u.hakmilik = :hakmilik and u.aktif ='Y'");
            q.setString("hakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<SejarahHakmilik> searchSejHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.SejarahHakmilik u where u.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikAsalPermohonan> searchMohonHakmilikAsalByID(Long idMh) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikAsalPermohonan u where u.hakmilikPermohonan.id = :idMh");
            q.setLong("idMh", idMh);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public HakmilikSebelumPermohonan searchMohonHakmilikSblmByMhs(Long idMhs) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.idHakmilikSebelumPermohonan = :idMhs");
        q.setLong("idMhs", idMhs);
        return (HakmilikSebelumPermohonan) q.uniqueResult();

    }

    public HakmilikSebelumPermohonan searchMohonHakmilikSblmByIdMohonIdHakmilikSej(String idHakmilikSej, String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilikSejarah = :idHakmilikSej and u.permohonan.idPermohonan = :idPermohonan");
        q.setString("idHakmilikSej", idHakmilikSej);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikSebelumPermohonan) q.uniqueResult();

    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByID(Long idMh) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilikPermohonan.id = :idMh");
            q.setLong("idMh", idMh);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByIDPermohonan(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select distinct u from etanah.model.HakmilikSebelumPermohonan u where u.permohonan.idPermohonan = :idPermohonan");
            q.setString("idPermohonan", idPermohonan);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByIDHakmilik(String idHakmilik, String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilik.idHakmilik = :idHakmilik and u.permohonan.idPermohonan =:idPermohonan");
            q.setString("idHakmilik", idHakmilik);
            q.setString("idPermohonan", idPermohonan);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByIDHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilikSejarah = :idHakmilik and u.hakmilikPermohonan.id != null");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByIDHakmilikNew(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<HakmilikSebelumPermohonan> searchMohonHakmilikSblmByIDHakmilikSej(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikSebelumPermohonan u where u.hakmilikSejarah = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();

    }

    public List<HakmilikPermohonan> searchMohonHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPermohonan u where u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> searchMohonHakmilikByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPermohonan> searchMohonHakmilikByIdPermohonan(String idPermohonan, String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan and u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idPermohonan", idPermohonan);
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<PermohonanRujukanLuar> searchMohonRujLuarByIdHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar u where u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<PermohonanRujukanLuar> searchMohonRujLuarByIdMohonAndIdHakmilik(String idPermohonan, String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.PermohonanRujukanLuar u where u.permohonan.idPermohonan= :idPermohonan and u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idPermohonan", idPermohonan);
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<Pemohon> searchPemohonByIdhakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pemohon u where u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> searchEditMohonHakmilik(String idPermohonan, String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.hakmilik.idHakmilik = :idHakmilik and u.permohonan.idPermohonan=:idPermohonan");
            q.setString("idPermohonan", idPermohonan);
            q.setString("idHakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public HakmilikPermohonan searchMohonHakmilikObject(String idHakmilik, String idPermohonan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikPermohonan u where u.hakmilik.idHakmilik = :idHakmilik and u.permohonan.idPermohonan = :idPermohonan");
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();

    }
    
     public KodSeksyen findKodSeksyenBandarWithKodBPM(String kodSeksyen, Integer kodBPM) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select ks from etanah.model.KodSeksyen ks where ks.seksyen = :kodSeksyen and ks.kodBandarPekanMukim.kod = :kodBPM");
        q.setString("kodSeksyen", kodSeksyen);
        q.setInteger("kodBPM", kodBPM);
        return (KodSeksyen) q.uniqueResult();

    }
     
     public KodBandarPekanMukim findKodBPMbyKodBPMAndKodDaerah(String kodBPM, String kodDaerah) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select bpm from etanah.model.KodBandarPekanMukim bpm where bpm.bandarPekanMukim = :kodBPM and bpm.daerah.kod = :kodDaerah");
        q.setString("kodBPM", kodBPM);
        q.setString("kodDaerah", kodDaerah);
        return (KodBandarPekanMukim) q.uniqueResult();

    }

    public HakmilikPermohonan searchMohonHakmilikByIdMhIdMohon(Long idMh, String idPermohonan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikPermohonan u where u.id = :idMh and u.permohonan.idPermohonan = :idPermohonan");
        q.setLong("idMh", idMh);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();

    }

    public HakmilikLama searchHakmiliklama(String idHakmilik) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.HakmilikLama u where u.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikLama) q.uniqueResult();

    }

    //Comment out by Aizuddin bcoz notis before this does not fill idMH field
//    public Notis findnotisf(String idPermohonan,String idMH) {
//
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("select u from etanah.model.Notis u where u.permohonan.idPermohonan = :idPermohonan and u.kodNotis.kod='5F' and u.hakmilikPermohonan.id=:idMH");
//        q.setString("idPermohonan", idPermohonan);
//        q.setString("idMH", idMH);
//        return (Notis) q.uniqueResult();
//
//    }
    //New notis services find notis 5f
    public Notis findnotis5f(String idPermohonan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.Notis u where u.permohonan.idPermohonan = :idPermohonan and u.kodNotis.kod='5F'");
        q.setString("idPermohonan", idPermohonan);
        return (Notis) q.uniqueResult();

    }

    public List<HakmilikPermohonan> searchMohonHakmilik(String idHakmilik, String idPermohonan) {
        logger.debug("idHakmilik:" + idHakmilik);
        logger.debug("idPermohonan:" + idPermohonan);
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.hakmilik.idHakmilik = :idHakmilik and u.permohonan.idPermohonan = :idPermohonan");
            q.setString("idHakmilik", idHakmilik);
            q.setString("idPermohonan", idPermohonan);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> senaraiMohonHakmilikMenguasai(String idPermohonan) {
        try {

            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan and u.hubunganHakmilik.kod = 'LK'");
            q.setString("idPermohonan", idPermohonan);
            logger.debug(q.list().size());
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> senaraiMohonHakmilik(String idPermohonan) {
        try {

            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan");
            q.setString("idPermohonan", idPermohonan);
            logger.debug(q.list().size());
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> senaraiMohonHakmilikOrderByASC(String idPermohonan) {
        try {

            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan order by u.hakmilik.idHakmilik asc");
            q.setString("idPermohonan", idPermohonan);
            logger.debug(q.list().size());
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPermohonan> senaraiMohonHakmilikMenanggung(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select  u from etanah.model.HakmilikPermohonan u where u.permohonan.idPermohonan = :idPermohonan and u.hubunganHakmilik.kod ='LT' or u.permohonan.idPermohonan = :idPermohonan and u.hubunganHakmilik.kod = null");
            q.setString("idPermohonan", idPermohonan);
            logger.debug(q.list().size());
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikAsal> searchHakmilikAsalByIDHakmilikAsal(Long idAsal) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikAsal u where u.idAsal = :idAsal");
            q.setLong("idAsal", idAsal);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikSebelum> searchHakmilikSebelumByIDHakmilikSebelum(Long idSebelum) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelum u where u.idSebelum = :idSebelum");
            q.setLong("idSebelum", idSebelum);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikSebelum> searchHakmilikSebelum(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikSebelum u where u.hakmilik = :hakmilik");
            q.setString("hakmilik", idHakmilik);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentingan(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik and u.aktif = 'Y'");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentinganPemilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik and u.jenis='PM' and u.aktif = 'Y'");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentinganPemilikKelompok(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik ");
                    //                    + "and u.jenis='PM' "
                    //+ "and u.aktif = 'Y' "
                    //+ "and u.pihakKongsiBersama is null");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentinganPemilikKelompokDone(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik "
                    //                    + "and u.jenis='PM' "
                    + "and u.aktif = 'Y' "
                    + "and u.pihakKongsiBersama is not null");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentinganSelainPemilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik and u.jenis != 'PM' and u.aktif = 'Y'");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikPihakBerkepentingan> searchPihakBerKepentinganSelainPemilikWarisCucuCicit(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikPihakBerkepentingan u where u.hakmilik = :hakmilik and u.jenis not in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS') and u.aktif = 'Y'");
            q.setString("hakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<Pihak> searchPihak(String nama) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak u where u.nama = :nama");
            q.setString("nama", nama);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<HakmilikAsalPermohonan> searchMohonHakmilikAsalByIDHakmilik(String idHakmilik) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikAsalPermohonan u where u.hakmilik.idHakmilik = :idHakmilik");
            q.setString("idHakmilik", idHakmilik);
            System.out.println(q.list().size());
            return q.list();

        } finally {
            //session.close();
        }

    }
    
     public List<HakmilikAsalPermohonan> searchMohonHakmilikAsalByIDHakmilikAndHakmilikAsal(String idHakmilik, String idHakmilikLama) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.HakmilikAsalPermohonan u where u.hakmilik.idHakmilik = :idHakmilik and u.hakmilikSejarah = :idHakmilikLama");
            q.setString("idHakmilik", idHakmilik);
            System.out.println(q.list().size());
            return q.list();

        } finally {
            //session.close();
        }

    }

    @Transactional
    public Pihak simpanPihak(Pihak pihak) {
        return pihakDao.saveOrUpdate(pihak);
    }

    @Transactional
    public void updatePihak(Pihak pihak) {
        pihakDao.update(pihak);
    }

    @Transactional
    public void simpanPihak2(Pihak pihak, HakmilikPihakBerkepentingan pihakBerkepentingan) {
        pihakDao.saveOrUpdate(pihak);
        pihakBerkepentinganDAO.saveOrUpdate(pihakBerkepentingan);

    }

    @Transactional
    public void simpanSejarahHakmilik(List<SejarahHakmilik> sH) {
        for (SejarahHakmilik sejarahHakmilik : sH) {
            sejarahHakmilikDAO.saveOrUpdate(sejarahHakmilik);
        }
    }

    @Transactional
    public void simpanHakmilikPetakAksesori(List<HakmilikPetakAksesori> hpa) {
        for (HakmilikPetakAksesori hakmilikPetakAksesori : hpa) {
            hpaDAO.saveOrUpdate(hakmilikPetakAksesori);
        }

    }

    @Transactional
    public void simpanHakmilikPihak(HakmilikPihakBerkepentingan pihakBerkepentingan) {
        pihakBerkepentinganDAO.saveOrUpdate(pihakBerkepentingan);
    }

    @Transactional
    public void simpanHakmilikPihak(List<HakmilikPihakBerkepentingan> pB) {
        for (HakmilikPihakBerkepentingan pihakBerkepentingan : pB) {
            pihakBerkepentinganDAO.saveOrUpdate(pihakBerkepentingan);
        }
    }

//    @Transactional
    public void simpanHakmilik(Hakmilik hakmilik) {
        hakmilikDao.saveOrUpdate(hakmilik);
    }

    public void simpanMohonHakmilikDanHakmilik(Hakmilik hakmilik, HakmilikPermohonan hakmilikPermohonan) {
        if (hakmilik != null) {
            hakmilikDao.saveOrUpdate(hakmilik);
        }
        if (hakmilikPermohonan != null) {
            hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
        }
    }

    @Transactional
    public void simpanMohonHakmilik(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    //@Transactional
    public void simpanMohonHakmilik(List<HakmilikPermohonan> hp) {
        for (HakmilikPermohonan hakmilikPermohonan : hp) {
            hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
        }
    }

    @Transactional
    public void simpanHakmilikList(List<Hakmilik> list) {
        for (Hakmilik hakmilik : list) {
            hakmilikDao.saveOrUpdate(hakmilik);
        }
    }

    @Transactional
    public void simpanHakmilikLamaList(List<HakmilikLama> list) {
        for (HakmilikLama hakmilikLama : list) {
            hakmilikLamaDAO.saveOrUpdate(hakmilikLama);
        }
    }

    public void saveHmGroup(List<Hakmilik> list) {
        for (Hakmilik hakmilik : list) {
            hakmilikDao.saveOrUpdate(hakmilik);
        }
    }

    @Transactional
    public void simpanMohonHakmilikList(List<HakmilikPermohonan> list) {
        for (HakmilikPermohonan hakmilikPermohonan : list) {
            hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
        }
    }

    @Transactional
    public void simpanMohonHakmilikAsal(HakmilikAsalPermohonan mohonHakmilikAsal) {
        mohonHakmilikAsalDAO.save(mohonHakmilikAsal);
    }

    @Transactional
    public void simpanMohonHakmilikAsal(List<HakmilikAsalPermohonan> list) {
        for (HakmilikAsalPermohonan hakmilikAsalPermohonan : list) {
            mohonHakmilikAsalDAO.save(hakmilikAsalPermohonan);
        }

    }

    @Transactional
    public void simpanMohonHakmilikSebelum(HakmilikSebelumPermohonan mohonHakmilikSblm) {
        mohonHakmilikSblmDAO.save(mohonHakmilikSblm);
    }

    @Transactional
    public void simpanMohonHakmilikSebelum(List<HakmilikSebelumPermohonan> list) {
        for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : list) {
            mohonHakmilikSblmDAO.save(hakmilikSebelumPermohonan);
        }

    }

    public void simpanMohonHakmilikSebelumWOT(HakmilikSebelumPermohonan mohonHakmilikSblm) {
        mohonHakmilikSblmDAO.save(mohonHakmilikSblm);
    }

    public void simpanMohonHakmilikSebelumWOT(List<HakmilikSebelumPermohonan> list) {
        for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : list) {
            mohonHakmilikSblmDAO.save(hakmilikSebelumPermohonan);
        }

    }

//    @Transactional
    public void simpanHakmilikAsal(HakmilikAsal hakmilikAsal) {
        hakmilikAsalDao.save(hakmilikAsal);
    }

    @Transactional
    public void simpanHakmilikAsal(List<HakmilikAsal> list) {
        for (HakmilikAsal hakmilikAsal : list) {
            hakmilikAsalDao.save(hakmilikAsal);
        }
    }

//    @Transactional
    public void simpanHakmilikSebelum(HakmilikSebelum hakmilikSebelum) {
        hakmilikSebelumDao.save(hakmilikSebelum);
    }

    @Transactional
    public void simpanHakmilikSebelum(List<HakmilikSebelum> list) {
        for (HakmilikSebelum hakmilikSebelum : list) {
            hakmilikSebelumDao.save(hakmilikSebelum);
        }

    }

    @Transactional
    public void deletePihakBerkepentingan(HakmilikPihakBerkepentingan pihakBerkepentingan) {
        pihakBerkepentinganDAO.delete(pihakBerkepentingan);
    }

    @Transactional
    public void deleteMohonPihak(PermohonanPihak mohonPihak) {
        mohonPihakDAO.delete(mohonPihak);
    }

    @Transactional
    public void deleteHakmilik(Hakmilik hakmilik) {
        hakmilikDao.delete(hakmilik);
    }

    public void deleteHakmilikWOT(Hakmilik hakmilik) {
        hakmilikDao.delete(hakmilik);
    }

    @Transactional
    public void deleteHakmilikAsal(HakmilikAsal hakmilikAsal) {
        hakmilikAsalDao.delete(hakmilikAsal);
    }

    @Transactional
    public void deleteMohonHakmilik(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    @Transactional
    public void deleteMohonHakmilik(List<HakmilikPermohonan> hakmilikPermohonan) {
        for (HakmilikPermohonan hp : hakmilikPermohonan) {
            hakmilikPermohonanDAO.delete(hp);
        }

    }

    public void deleteMohonHakmilikWOT(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    public void deleteMohonHakmilikWOT(List<HakmilikPermohonan> hakmilikPermohonan) {
        for (HakmilikPermohonan hp : hakmilikPermohonan) {
            hakmilikPermohonanDAO.delete(hp);
        }

    }

//    @Transactional
    public void deleteMohonHakmilikAsal(List<HakmilikAsalPermohonan> mohonHakmilikAsal) {
        for (HakmilikAsalPermohonan hakmilikAsalPermohonan : mohonHakmilikAsal) {
            mohonHakmilikAsalDAO.delete(hakmilikAsalPermohonan);
        }

    }

    @Transactional
    public void deleteMohonHakmilikAsal(HakmilikAsalPermohonan mohonHakmilikAsal) {
        mohonHakmilikAsalDAO.delete(mohonHakmilikAsal);
    }

    @Transactional
    public void deleteMohonHakmilikSblm(HakmilikSebelumPermohonan mohonHakmilikSblm) {
        mohonHakmilikSblmDAO.delete(mohonHakmilikSblm);
    }

    @Transactional
    public void deleteMohonHakmilikSblm(List<HakmilikSebelumPermohonan> list) {
        for (HakmilikSebelumPermohonan hakmilikSebelumPermohonan : list) {
            mohonHakmilikSblmDAO.delete(hakmilikSebelumPermohonan);
        }

    }

    @Transactional
    public void deleteHakmilikSblm(HakmilikSebelum hakmilikSblm) {
        hakmilikSebelumDao.delete(hakmilikSblm);
    }

    @Transactional
    public void simpanKodAgensi(KodAgensi kodAgensi) {
        kodAgensiDAO.saveOrUpdate(kodAgensi);

    }

    @Transactional
    public void simpanKodSekatan(KodSekatanKepentingan kodSekatanKepentingan) {
        kodSekatanKepentinganDAO.saveOrUpdate(kodSekatanKepentingan);

    }

    @Transactional
    public void simpanKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        kodSyaratNyataDAO.saveOrUpdate(kodSyaratNyata);

    }

    @Transactional
    public void simpanHakmilikLama(HakmilikLama hakmilikLama) {
        hakmilikLamaDAO.saveOrUpdate(hakmilikLama);
    }

    @Transactional
    public void deleteHakmilikLama(HakmilikLama hakmilikLama) {
        hakmilikLamaDAO.delete(hakmilikLama);
    }

    public List<KodAgensi> searchKodAgensi(String kod, String kodAgensiNama) {

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE ";

            if (kod != null) {
                query += " u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchKodAgensiLupus(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN' , 'JTK') AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

// add by Azri: list all hakmilik pihak 
    public List<HakmilikPihakBerkepentingan> getPemilikOnly(String idPermohonan, List<String> list) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.idHakmilik in (:list) and m.hakmilik = hp.hakmilik and m.aktif = 'Y' and m.jenis.kod in ('PM','PP') order by hp.hakmilik.idHakmilik asc");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameterList("list", list);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPemilikOnlyPMPP(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m where m.hakmilik.idHakmilik =:idHakmilik and m.aktif = 'Y' and m.jenis.kod in ('PM','PP')");
        q.setParameter("idHakmilik", idHakmilik);

        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPihakNotPemilik(String idPermohonan, List<String> list) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m , etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.idHakmilik in (:list) and m.hakmilik = hp.hakmilik and m.aktif = 'Y' and m.jenis.kod not in ('PM','PP') order by hp.hakmilik.idHakmilik asc");
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameterList("list", list);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getPihakNotPemilikPPPM(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPihakBerkepentingan m where m.hakmilik.idHakmilik =:idHakmilik and m.aktif = 'Y' and m.jenis.kod not in ('PM','PP')");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }
// add by Azri: END

    public List<String> findIdHakmilikAsalByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u.hakmilikAsal from etanah.model.HakmilikAsal u where u.hakmilik.idHakmilik =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikAsalPermohonan> findIdHakmilikAsalByIdMH(Long idMh) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u.hakmilikAsal from etanah.model.HakmilikAsal u where u.hakmilikPermohonan.id =:id");
        q.setLong("idMh", idMh);
        return q.list();
    }

    public List<String> findIdHakmilikSebelumByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u.hakmilikSebelum from etanah.model.HakmilikSebelum u where u.hakmilik.idHakmilik =:idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public Akaun getAkaunLama(String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select a from Akaun a ")
                .append("WHERE hakmilik.idHakmilik = :idHakmilik ")
                .append("AND kodAkaun.kod = :kod ")
                .append("AND status.kod = :status");

        Session s = sessionProvider.get();
        Query query = s.createQuery(sb.toString())
                .setParameter("idHakmilik", idHakmilik)
                .setParameter("kod", "AC")
                .setParameter("status", "A");

        return (Akaun) query.uniqueResult();
    }
    
    public Akaun getAkaunFreeze(String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select a from Akaun a ")
                .append("WHERE hakmilik.idHakmilik = :idHakmilik ")
                .append("AND kodAkaun.kod = :kod ")
                .append("AND status.kod = :status");

        Session s = sessionProvider.get();
        Query query = s.createQuery(sb.toString())
                .setParameter("idHakmilik", idHakmilik)
                .setParameter("kod", "AC")
                .setParameter("status", "F");

        return (Akaun) query.uniqueResult();
    }

    public List<Akaun> findAkaunByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select a from etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :idHakmilik order by a.hakmilik.idHakmilik ASC");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    public Hakmilik findHakmilikPajakanByIdHAkmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select a from etanah.model.Hakmilik a WHERE a.idHakmilik = :idHakmilik "
                + "and a.kodStatusHakmilik.kod = 'D' "
                + "and a.pegangan = 'P' "
                + "and a.tempohPegangan is not null "
                + "and a.tarikhLuput is not null "
                + "and a.kodHakmilik.kod not in ('GRN','GMM','GM')");
        q.setString("idHakmilik", idHakmilik);
         return (Hakmilik) q.uniqueResult();
    }

    public List<Pemohon> findPemohonByIdPermohonanAndHakmilik(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
      public Permohonan findpermohonanByidSmbngan(String idMohonSbgn) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.idMohonSbgn = :idMohonSbgn";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonSbgn", idMohonSbgn);
        return (Permohonan) q.uniqueResult();
    }

    public List<PermohonanPihak> findMohonPihakByIdPermohonanAndHakmilik(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByHakmilikAndIdMohon(String idHakmilik, String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.hakmilik.idHakmilik = :idHakmilik and p.permohonan.idPermohonan =:idPermohonan");
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByHakmilikAndIdMohonAndIdPihak(String idHakmilik, String idPermohonan, Long idPihak) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPihak p where p.hakmilik.idHakmilik = :idHakmilik and p.permohonan.idPermohonan =:idPermohonan and p.pihak.idPihak =:idPihak");
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    public List<HakmilikSebelumPermohonan> findIdHakmilikSebelumMohonByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select m from etanah.model.HakmilikSebelumPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikAsalPermohonan> findIdHakmilikAsalMohonByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select m from etanah.model.HakmilikAsalPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikAsalPermohonan> findIdHakmilikAsalMohonByIdHakmilikSej(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select m from etanah.model.HakmilikAsalPermohonan m WHERE m.hakmilikSejarah = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
}

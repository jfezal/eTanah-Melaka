/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author md.nurfikri
 */
public class HakmilikPihakKepentinganService {

    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakKepentinganDAO;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(HakmilikPihakKepentinganService.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private final static String[] JENIS_TUAN_TANAH = {
        "PM",
        "PA",
        "WAR",
        "ASL",
        "JA",
        "JK",
        "KL",
        "PDP",
        "PK",
        "PLK",
        "PP",
        "RP",
        "WKL",
        "WPA",
        "PAS",
        "ROS",
        "PB",
        "WS",
        "PL",
        "CP",
        "PPM",
        "WP",
        "PH"
    };

    @Transactional
    public HakmilikPihakBerkepentingan save(HakmilikPihakBerkepentingan hpk) {
        return hakmilikPihakKepentinganDAO.saveOrUpdate(hpk);
    }

    public void saveWOT(HakmilikPihakBerkepentingan hpk) {
        hakmilikPihakKepentinganDAO.saveOrUpdate(hpk);
    }

    @Transactional
    public void saveList(List<HakmilikPihakBerkepentingan> pBlist) {
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : pBlist) {
            hakmilikPihakKepentinganDAO.saveOrUpdate(hakmilikPihakBerkepentingan);
        }
    }

    @Transactional
    public HakmilikPihakBerkepentingan findById(String id) {
        return hakmilikPihakKepentinganDAO.findById(Long.parseLong(id));
    }

    public void saveProvidedConn(HakmilikPihakBerkepentingan hpk) {
        LOG.debug("save provided conn");
        hakmilikPihakKepentinganDAO.saveOrUpdate(hpk);
    }

    @Transactional
    public void deleteHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> pBList) {
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : pBList) {
            hakmilikPihakKepentinganDAO.delete(hakmilikPihakBerkepentingan);
        }

    }

    public void save(List<HakmilikPihakBerkepentingan> hpkList, List<Pemohon> pemohonList, Hakmilik hakmilik,
            Permohonan p, InfoAudit info) {
        LOG.info("save :: start");
        String urusan = p.getKodUrusan().getKod();
        LOG.info("Urusan ::" + urusan);
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : hpkList) {
            hakmilikPihakKepentinganDAO.saveOrUpdate(hakmilikPihakBerkepentingan);
        }
        Pengguna pg = info.getDikemaskiniOleh();
        HakmilikUrusan hu = hakmilikUrusanService.findByIdPerserahanIdHakmilik(p.getIdPermohonan(), hakmilik.getIdHakmilik());
        if (hu == null) {
            return;
        }

        KodUrusan ku = p.getKodUrusan();
        HakmilikPihakBerkepentingan h = null;

        for (Pemohon pemohon : pemohonList) {

            if ("PMP".equals(ku.getKod())
                    || "PMG".equals(ku.getKod())
                    || "PNPAB".equals(ku.getKod())
                    || "PMPJK".equals(ku.getKod())) {
                String kod = "";
                if ("PMP".equals(ku.getKod())) {
                    kod = "PJ";
                } else if ("PNPAB".equals(ku.getKod())) {
                    kod = "PA";
                } else if ("PMPJK".equals(ku.getKod())) {
                    kod = "PJK";
                } else {
                    kod = "PG";
                }
                h = findHakmilikPihakByIdPihakPMPPMG(pemohon.getPihak(), hakmilik, kod);
            } //            PH30B kena tukar pemilik!!
            //            else if (p.getKodUrusan().getKod().equals("PH30B")) {
            //                h = findOtherHakmilikPihakByPihak(pemohon.getPihak().getIdPihak(), hakmilik);
            //            } 
            else if ("PMBUD".equals(ku.getKod()) || "PHMM".equals(ku.getKod())) {
                h = findOtherHakmilikPihakByPihak(pemohon.getPihak().getIdPihak(), hakmilik, h);
            } else if ("TMAMT".equals(ku.getKod()) || "TMAME".equals(ku.getKod()) || "TMAMG".equals(ku.getKod())) {
                if (pemohon.getHakmilikPihak() != null) {
                    h = findHakmilikPihakByIdPihak_PESAKA(pemohon.getPihak(), hakmilik, pemohon.getHakmilikPihak());
                }
            } else {
                h = findHakmilikPihakByIdPihak(pemohon.getPihak(), hakmilik);
            }

            if (h != null) {
                h.setPerserahanBatal(hu);
                info = h.getInfoAudit();
                info.setTarikhKemaskini(new Date());
                info.setDiKemaskiniOleh(pg);
//            h.setPermohonanBatal(p);
                //TODO : insert auditinfo            
                h.setInfoAudit(info);
                if (!"CPB".equals(ku.getKod())) {
                    h.setAktif('T');
                }
                if (ku.getKod().equals("416C")){
                    h.setAktif('Y');
                }
                hakmilikPihakKepentinganDAO.saveOrUpdate(h);
            }
        }
        LOG.info("save :: finish");

    }

    public void save(List<HakmilikPihakBerkepentingan> hpkList) {
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : hpkList) {
            hakmilikPihakKepentinganDAO.saveOrUpdate(hakmilikPihakBerkepentingan);
        }
    }

    public void save(List<HakmilikPihakBerkepentingan> hpkList, Hakmilik hakmilik,
            Permohonan p, InfoAudit info) {
        LOG.info("save :: start");
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : hpkList) {
            hakmilikPihakKepentinganDAO.saveOrUpdate(hakmilikPihakBerkepentingan);
        }
        LOG.info("save :: finish");
    }

    public void update(List<HakmilikPihakBerkepentingan> hpkList, Hakmilik hakmilik,
            Permohonan p, InfoAudit info) {
        LOG.info("save :: start");
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : hpkList) {
            hakmilikPihakKepentinganDAO.update(hakmilikPihakBerkepentingan);
        }
        LOG.info("save :: finish");
    }

    public List<HakmilikPihakBerkepentingan> findListHakmilikByIDHakmilik(String idHakmilik) {
        String query = "Select m from etanah.model.HakmilikPihakBerkepentingan m WHERE m.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }
    //added

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBerkepentinganByIDHakmilik(String idHakmilik) {
        String query = "Select m from etanah.model.HakmilikPihakBerkepentingan m WHERE m.hakmilik.idHakmilik = :idHakmilik order by case when m.jenis.kod = 'PM' then 1 else 2 end, m.jenis.kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBerkepentinganByIDHakmilikGeran(String idHakmilik) {
        String query = "Select m from etanah.model.HakmilikPihakBerkepentingan m WHERE m.hakmilik.idHakmilik = :idHakmilik "
                + "and m.aktif='Y' and m.jenis.kod in ('PM','WS','WPA','WKL','RP','PP','PLK','PK','PDP','PA','KL','JK','JA','ASL','PPC','PPH','PPM','PL','PAT','PAW','PAP','WKF','PML','CP','PH','ROS','BP')";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihak(Pihak pihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod in (:list)";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik())
                .setParameterList("list", JENIS_TUAN_TANAH);

        //data migration. id pihak sama, jenis sama, tp hakmilik pihak berlainan.
        List list = q.list();
        if (list.isEmpty()) {
            return null;
        }
        return (HakmilikPihakBerkepentingan) list.get(0);
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihak_PESAKA(Pihak pihak, Hakmilik hakmilik, HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik())
                .setLong("idHakmilikPihakBerkepentingan", hakmilikPihakBerkepentingan.getIdHakmilikPihakBerkepentingan());

        //data migration. id pihak sama, jenis sama, tp hakmilik pihak berlainan.
        List list = q.list();
        if (list.isEmpty()) {
            return null;
        }
        return (HakmilikPihakBerkepentingan) list.get(0);
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihakTMAME_TMAMG(Pihak pihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod in ('PM','WAR','ASL','JA','JK','KL')";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik());

        //data migration. id pihak sama, jenis sama, tp hakmilik pihak berlainan.
        List list = q.list();
        if (list.isEmpty()) {
            return null;
        }
        return (HakmilikPihakBerkepentingan) list.get(0);
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihakBukanPM(Pihak pihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y'";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        List list = q.list();
        if (list.isEmpty()) {
            return null;
        }
        return (HakmilikPihakBerkepentingan) list.get(0);
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihakBETPB(Pihak pihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='T'";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        List list = q.list();
        if (list.isEmpty()) {
            return null;
        }
        return (HakmilikPihakBerkepentingan) list.get(0);
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakTidakAktif(Pihak pihak, Hakmilik hakmilik, String kod) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='T' and h.jenis.kod= :kod";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik())
                .setString("kod", kod);
        return q.list();
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByPihak(Long idPihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod in ('PM','PA')";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + idPihak);
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", idPihak)
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    //new added @mohd.fairul :return 'TB' type pihak berkepentingan
    public HakmilikPihakBerkepentingan findHakmilikPihakByPihakTB(String idPihak, Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod in ('TB')";
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + idPihak);
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPihak", idPihak)
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findOtherHakmilikPihakByPihak(Long idPihak, Hakmilik hakmilik,
            HakmilikPihakBerkepentingan hpk) {

        StringBuilder query = new StringBuilder("Select h from etanah.model.HakmilikPihakBerkepentingan h ")
                .append("WHERE h.pihak.idPihak = :idPihak ")
                .append("AND h.hakmilik.idHakmilik = :idHakmilik ")
                .append("AND h.aktif='Y' ")
                .append("AND h.jenis.kod not in ('PM') ");

//    StringBuilder query = new StringBuilder("Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
//            + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod not in ('PM')");
        if (hpk != null) {
            query.append(" AND h.idHakmilikPihakBerkepentingan not in (:id)");
        }

        LOG.info("query ::" + query.toString());
        LOG.debug("idPihak :: " + idPihak);
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query.toString())
                .setLong("idPihak", idPihak)
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        if (hpk != null) {
            q.setParameter("id", hpk.getIdHakmilikPihakBerkepentingan());
        }
        return q.list().isEmpty() ? null : (HakmilikPihakBerkepentingan) q.list().get(0);
    }

    public HakmilikPihakBerkepentingan findHakmilikPihakByIdPihakPMPPMG(Pihak pihak, Hakmilik hakmilik, String kod) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak "
                + "and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' ";
        if (StringUtils.isNotBlank(kod)) {
            query = query + "and h.jenis.kod = :kod";
        }
        LOG.info("query ::" + query);
        LOG.debug("idPihak :: " + pihak.getIdPihak());
        LOG.debug("idHakmilik :: " + hakmilik.getIdHakmilik());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idPihak", pihak.getIdPihak())
                .setString("idHakmilik", hakmilik.getIdHakmilik());
        if (StringUtils.isNotBlank(kod)) {
            q.setString("kod", kod);
        }
        List list = q.list();
        if (list.isEmpty()) {
            return (HakmilikPihakBerkepentingan) q.uniqueResult();
        }
//        return (HakmilikPihakBerkepentingan) q.uniqueResult();
        return (HakmilikPihakBerkepentingan) list.get(0);
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByIdUrusan(HakmilikUrusan hu) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.aktif='Y' "
                + "and h.perserahan.idUrusan = :idUrusan";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByIdUrusan(HakmilikUrusan hu, Hakmilik hm) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.aktif='Y' "
                + "and h.perserahan.idUrusan = :idUrusan and h.hakmilik.idHakmilik = :idHakmilik";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan())
                .setParameter("idHakmilik", hm.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findAllHakmilikPihakByIdUrusan(HakmilikUrusan hu, Hakmilik hm) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where "
                + "h.perserahan.idUrusan = :idUrusan and h.hakmilik.idHakmilik = :idHakmilik";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan())
                .setParameter("idHakmilik", hm.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPBByidHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBETPB(String idHakmilik, String idPermohonan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.idPermohonan = :idPermohonan "
                + "and h.aktif = 'T'";
//        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        Query q = sessionProvider.get().createQuery(query)
                .setString("idHakmilik", idHakmilik)
                .setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBatalByIdUrusan(HakmilikUrusan hu) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.aktif='T' "
                + "and h.perserahanBatal.idUrusan = :idUrusan";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBatalByIdUrusan(HakmilikUrusan hu, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.aktif='T' "
                + "and h.perserahanBatal.idUrusan = :idUrusan and h.hakmilik.idHakmilik = :idHakmilik";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan()).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    // add by azri 8/7/2013 : used in PemohonActionBean (test)
    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBatalByIdUrusan1(HakmilikUrusan hu, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h "
                + "where h.perserahan.idUrusan = :idUrusan and h.hakmilik.idHakmilik = :idHakmilik";
        LOG.info("query ::" + query);
        LOG.debug("ID Urusan :: " + hu.getIdUrusan());
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", hu.getIdUrusan()).setString("idHakmilik", idHakmilik);
        return q.list();
    }
    // add by azri 8/7/2013 : End

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByKod(Hakmilik h, String jenisPihak) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod like :kod";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idHakmilik", h.getIdHakmilik())
                .setString("kod", jenisPihak + "%");
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikAllPihakActiveByHakmilik(Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByHakmilik(Hakmilik hakmilik) {
        //fikri :: return only tuan tanah and pemegang amanah        
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','BP')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBETPBByHakmilikAndIdMohon(String idHakmilik, String idPermohonan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where "
                + "h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.idPermohonan = :idPermohonan "
                + "and h.aktif='T' ";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idHakmilik", idHakmilik)
                .setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakNotActiveByHakmilik(Hakmilik hakmilik) {
        //fikri :: return only tuan tanah and pemegang amanah
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='T' and h.jenis.kod in ('PM','PA')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPertanyaanHmkActiveByHakmilik(Hakmilik hakmilik) {
        //for pertanyaan Hakmilik - Aktif
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','PML','BP','PH') order by h.nama";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPertanyaanHmNotActiveByHakmilik(Hakmilik hakmilik) {
        //for pertanyaan Hakmilik - Tidak Aktif
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='T' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','PML','BP','PH') order by h.nama";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    //dyana::return only pemilik
    public List<HakmilikPihakBerkepentingan> findPihakActiveByHakmilik(Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    //dyana add:: return pihak berkepentingan selain daripada tuan tanah dan pemegang amanah
    public List<HakmilikPihakBerkepentingan> findPihakBerkepentinganByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod not in ('PM')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    //Added by Aizuddin to find all HP
    public List<HakmilikPihakBerkepentingan> findAllPihakBerkepentinganByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' ";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(Hakmilik hakmilik) {
        //khairil :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS', 'CP','WP','PH','PL','PG','PMB','PML')";
        //               + "and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS', 'CP','WP','PH','PL','PG')";   //revert kes pihak di tab selain tambah waris keluar yg tidak aktif - yus 02102018             
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveAndWarisCucuCicitByHakmilikBETPB(Hakmilik hakmilik) { // yus tambah : utk selesai kes atas 02102018
        //khairil :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                //               + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS', 'CP','WP','PH','PL','PG')";
                + "and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS', 'CP','WP','PH','PL','PG')";   //revert kes pihak di tab selain tambah waris keluar yg tidak aktif - yus 02102018             
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveAndWarisCucuCicitByHakmilikSemua(Hakmilik hakmilik) {
        //m.fairul :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya dan ditambah dengan yg aktif dan tidak aktif
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    //fikri add:: return pihak berkepentingan selain daripada tuan tanah dan pemegang amanah
    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBerkepentinganByIdHakmilik(String idHakmilik, List list) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod not in ('PM','PA','PG','PMB','PJ')";
        if (list != null && list.size() > 0) {
            query += " and h.jenis.kod in (:listKod)";
        }
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        if (list != null && list.size() > 0) {
            q.setParameterList("listKod", list);
        }

        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakBerkepentinganByIdHakmilik2(String idHakmilik, List list) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod not in ('PM','PA', 'PMB','PJ')";
        if (list != null && list.size() > 0) {
            query += " and h.jenis.kod in (:listKod)";
        }
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        if (list != null && list.size() > 0) {
            q.setParameterList("listKod", list);
        }

        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> doCheckPihakKaviet(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PKS','PKL','PKA','PKD','PPK')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> doCheckUrusan(String idHakmilik, List kod) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y'";
        if (kod != null && kod.size() > 0) {
            query += " and h.jenis.kod in (:kod)";
        }
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        if (kod != null && kod.size() > 0) {
            q.setParameterList("kod", kod);
        }
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> doCheckPihakTenansi(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.nama like '%Tenansi%'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> senaraiPBtidakBerkaitan(String idHakmilik, String idKumpulan,
            String idPermohonan, boolean bukanTuanTanah, boolean semuaPihak) {
        String query = "Select p from etanah.model.HakmilikPihakBerkepentingan p where p.hakmilik.idHakmilik = :idHakmilik "
                + "and p.pihak.idPihak not in (Select pm.pihak.idPihak from etanah.model.Pemohon pm, etanah.model.Permohonan m "
                + "where pm.permohonan.idPermohonan = m.idPermohonan "
                + "and pm.permohonan.idPermohonan not in (:idPermohonan) "
                + "and m.idKumpulan = :idKumpulan and pm.hakmilik.idHakmilik = :idHakmilik and pm.jenisPemohon in ('X') ) "
                + //                "and pm.permohonan.idPermohonan in " +
                //                "(Select mh.idPermohonan from etanah.model.Permohonan mh where mh.idKumpulan = :idKumpulan ) )" +
                "and p.aktif='Y' ";
        if (!semuaPihak) {
            if (bukanTuanTanah) {
                query = query + "and p.jenis.kod not in (:senaraiPihak)";
            } else {
                query = query + "and p.jenis.kod in (:senaraiPihak)";
            }
        }

        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik)
                .setString("idKumpulan", idKumpulan)
                .setString("idPermohonan", idPermohonan);
        if (!semuaPihak) {
            q.setParameterList("senaraiPihak", JENIS_TUAN_TANAH);
        }
        return q.list();
    }

    public HakmilikPihakBerkepentingan searchById(String id) {
        Query query = sessionProvider.get()
                .createQuery("Select hp from etanah.model.HakmilikPihakBerkepentingan hp where hp.idHakmilikPihakBerkepentingan = :id")
                .setLong("id", Long.valueOf(id));
        LOG.debug("id =" + id);
        return (HakmilikPihakBerkepentingan) query.uniqueResult();
    }

    public KodSeksyen searchBySeksyen(String kod) {
        Query query = sessionProvider.get()
                .createQuery("Select ks from etanah.model.KodSeksyen ks where ks.seksyen = :kod")
                .setString("kod", kod);
        return (KodSeksyen) query.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> searchByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h "
                + "where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod = 'PG'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
        public List<HakmilikPihakBerkepentingan> findPemegangGadaianByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hp from etanah.model.HakmilikPihakBerkepentingan hp, etanah.model.HakmilikPermohonan p, etanah.model.HakmilikUrusan hu"
                + " where hp.hakmilik.idHakmilik = p.hakmilik.idHakmilik"
                + " and hp.perserahan.idUrusan = hu.idUrusan"
                + " and hp.jenis.kod = 'PG' and hp.aktif = 'Y' and p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
}

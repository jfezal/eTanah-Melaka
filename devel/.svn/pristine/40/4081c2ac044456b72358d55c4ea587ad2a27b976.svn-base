/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodHakmilik;
import etanah.model.Permohonan;
import etanah.model.Versi4k;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.strata.BadanPengurusan;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author md.nurfikri
 */
public class HakmilikService {

    HakmilikDAO hakmilikDAO;
    PermohonanDAO permohonanDAO;
    HakmilikPermohonanDAO hakmilikpermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(HakmilikService.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Inject
    public HakmilikService(HakmilikDAO hakmilikDAO, PermohonanDAO permohonanDAO, HakmilikPermohonanDAO hakmilikpermohonanDAO) {
        this.hakmilikDAO = hakmilikDAO;
        this.permohonanDAO = permohonanDAO;
        this.hakmilikpermohonanDAO = hakmilikpermohonanDAO;
    }

    @Transactional
    public void simpanAmbil(Hakmilik hakmilik, Permohonan p) {
        hakmilikDAO.saveOrUpdate(hakmilik);
        permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdate(Hakmilik h, Permohonan p) {
        hakmilikDAO.saveOrUpdate(h);
        permohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatehakmilikpermohonan(HakmilikPermohonan p) {
        hakmilikpermohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void savehakmilikpermohonan(List<HakmilikPermohonan> list) {
        for (HakmilikPermohonan hp : list) {
            hakmilikpermohonanDAO.saveOrUpdate(hp);
        }
    }

    @Transactional
    public void deletehakmilikpermohonan(List<HakmilikPermohonan> list) {
        for (HakmilikPermohonan hp : list) {
            hakmilikpermohonanDAO.delete(hp);
        }
    }

    public Hakmilik findById(String idHakmilik) {
        return hakmilikDAO.findById(idHakmilik);
    }

    @Transactional
    public void saveHakmilik(Hakmilik h) {
        hakmilikDAO.saveOrUpdate(h);
    }

    public void saveHakmilikWithoutConnection(Hakmilik h) {
        hakmilikDAO.saveOrUpdate(h);
    }

    @Transactional
    public void deleteAll(HakmilikPermohonan h) {
        hakmilikpermohonanDAO.delete(h);
    }

    @Transactional
    public HakmilikPermohonan findHakmilikByIdHakmilik(Long id) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.id= :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        //q.setString("idH", idH);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    @Transactional
    public void saveHakmilik(List<Hakmilik> senaraiHakmilik) {
        for (Hakmilik hm : senaraiHakmilik) {
            hakmilikDAO.saveOrUpdate(hm);
        }
    }

    public List<Hakmilik> findHakmilikByParameter(Map<String, String[]> param) {
        boolean flag = false;
        String query = "SELECT h FROM etanah.model.Hakmilik h WHERE h.idHakmilik = h.idHakmilik";
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.idHakmilik"))) {
            query += " AND h.idHakmilik = :idHakmilik";
            flag = true;
        }

        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.daerah.kod"))) {
            query += " AND h.daerah.kod = :daerah";
            flag = true;
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.bandarPekanMukim.kod"))) {
            query += " AND h.bandarPekanMukim.kod = :bpm";
            flag = true;
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.lot.kod"))) {
            query += " AND h.lot.kod = :lot";
            flag = true;
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("noLotDari")) && etanah.util.StringUtils.isNotBlank(param.get("noLotHingga"))) {
            query += " AND h.noLot between :dari and :hingga";
            flag = true;
//            query += " ORDER By h.noLot";
        }
        if (!flag) {
            List<Hakmilik> list = new LinkedList<Hakmilik>();
            return list;
        }
        LOG.info("query ::" + query);

        Query q = sessionProvider.get().createQuery(query);
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.idHakmilik"))) {
            q.setString("idHakmilik", param.get("hakmilik.idHakmilik")[0]);
        }

        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.daerah.kod"))) {
            q.setString("daerah", param.get("hakmilik.daerah.kod")[0]);
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.bandarPekanMukim.kod"))) {
            q.setString("bpm", param.get("hakmilik.bandarPekanMukim.kod")[0]);
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("hakmilik.lot.kod"))) {
            q.setString("lot", param.get("hakmilik.lot.kod")[0]);
        }
        if (etanah.util.StringUtils.isNotBlank(param.get("noLotDari")) && etanah.util.StringUtils.isNotBlank(param.get("noLotHingga"))) {
            q.setString("dari", param.get("noLotDari")[0]);
            q.setString("hingga", param.get("noLotHingga")[0]);
        }

        return q.list();

    }

    /**
     * Find Hakmilik in given Daerah which status are only 'D'.
     *
     * @param idHakmilik
     * @param kodDaerah
     * @return
     */
    public Hakmilik findValidHakmilikInDaerah(String idHakmilik, String kodDaerah) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik "
                + "and h.kodStatusHakmilik.id in ('D')";
        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        } else {
            hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
        }
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        Hakmilik h = (Hakmilik) q.uniqueResult();

        // check pegangan
        if (h != null && h.getPegangan() == 'P') { // pajakan
            if (h.getTempohPegangan() == null) {
                throw new java.lang.IllegalStateException("Hakmilik.pegangan Pajakan tetapi  tempoh pegangan nil");
            }
            if (h.getTarikhLuput() != null) {
                if (h.getTarikhLuput().before(new Date())) {
                    LOG.warn("Hakmilik " + idHakmilik + " cuba dicari walaupun telah tamat tempoh pegangan");
                    h = null;
                }
            } else {
                // calculate tarikh luput
                long luput = h.getTarikhDaftar().getTime() + (h.getTempohPegangan()
                        * 31556926000l /*ms in a year*/);
                if (luput < System.currentTimeMillis()) {
                    LOG.warn("Hakmilik " + idHakmilik + " cuba dicari walaupun telah tamat tempoh pegangan");
                    h = null;
                }
            }
        }

        return h;
    }

    /**
     * Find Hakmilik in given Daerah.
     *
     * @param idHakmilik
     * @param kodDaerah
     * @return
     */
    public Hakmilik findHakmilikInDaerah(String idHakmilik, String kodDaerah) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik ";
        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        } else {
            hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
        }
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        Hakmilik h = (Hakmilik) q.uniqueResult();

        return h;
    }

    // utk user GEMAS ; simulasi 11-Nov-2014 START
    public Hakmilik findHakmilikInDaerahGemas(String idHakmilik, String kodDaerah) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik ";
        if (kodDaerah != null) {
            hql += " and h.cawangan.daerah.id = :kodDaerah";
        } else {
            hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
        }
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        Hakmilik h = (Hakmilik) q.uniqueResult();
        return h;
    }
    // END

    //for Strata
    public Hakmilik findHakmilikInDaerahStrata(String idHakmilik) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik ";
//    	if (kodDaerah != null){
//    		hql += " and h.daerah.id = :kodDaerah";
//    	} else{
//    		hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
//    	}
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        Hakmilik h = (Hakmilik) q.uniqueResult();

        return h;
    }

    public Hakmilik findHakmilikIndukByIdHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik ";
//    	if (kodDaerah != null){
//    		hql += " and h.daerah.id = :kodDaerah";
//    	} else{
//    		hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
//    	}
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        Hakmilik h = (Hakmilik) q.uniqueResult();

        return h;
    }

    public Hakmilik findHakmilikInDaerah(String idHakmilik, String kodDaerah, String milikDaerah) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik = :idHakmilik ";
        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        }
        hql += " and h.kodHakmilik.milikDaerah = :milikDaerah"; // hakmilik pendaftar sahaja
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        q.setString("milikDaerah", milikDaerah);
        Hakmilik h = (Hakmilik) q.uniqueResult();

        return h;
    }

    public List<Hakmilik> findHakmilikInDaerah(String idHakmilik, String kodDaerah, String petak, String bangunan, String tingkat) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilikInduk = :idHakmilik ";

        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        } else {
            hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
        }

        if (StringUtils.isNotBlank(petak)) {
            hql += " and h.noPetak = :petak";
        }

        if (StringUtils.isNotBlank(bangunan)) {
            hql += " and h.noBangunan = :bangunan";
        }

        if (StringUtils.isNotBlank(tingkat)) {
            hql += " and h.noTingkat LIKE :tingkat";
        }

        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setString("idHakmilik", idHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        if (StringUtils.isNotBlank(petak)) {
            q.setString("petak", petak);
        }
        if (StringUtils.isNotBlank(bangunan)) {
            q.setString("bangunan", bangunan);
        }
        if (StringUtils.isNotBlank(tingkat)) {
//            q.setString("tingkat", tingkat);
            q.setString("tingkat", "%" + tingkat + "%");
        }

//        Hakmilik h = (Hakmilik) q.uniqueResult();
        return q.list();
    }

    public List<Hakmilik> findValidHakmilikInDaerah(List<String> listIdHakmilik, String kodDaerah) {
        Session s = sessionProvider.get();
        String hql = "select h from Hakmilik h where h.idHakmilik h.idHakmilik in (:listHakmilik) "
                + " and h.kodStatusHakmilik.id in ('D')";
        if (kodDaerah != null) {
            hql += " and h.daerah.id = :kodDaerah";
        } else {
            hql += " and h.kodHakmilik.milikDaerah = 'T'"; // hakmilik pendaftar sahaja
        }
        if (debug) {
            LOG.debug(hql);
        }

        Query q = (Query) s.createQuery(hql).setParameterList("listHakmilik", listIdHakmilik);
        if (kodDaerah != null) {
            q.setString("kodDaerah", kodDaerah);
        }
        List<Hakmilik> listHakmilik = q.list();

        // check pegangan
        for (Hakmilik h : listHakmilik) {
            if (h != null && h.getPegangan() == 'P') { // pajakan
                if (h.getTempohPegangan() == null) {
                    throw new java.lang.IllegalStateException("Hakmilik.pegangan Pajakan tetapi  tempoh pegangan nil");
                }
                if (h.getTarikhLuput() != null) {
                    if (h.getTarikhLuput().before(new Date())) {
                        LOG.warn("Hakmilik " + h.getIdHakmilik() + " cuba dicari walaupun telah tamat tempoh pegangan");
                        listHakmilik.remove(h);
                    }
                } else {
                    // calculate tarikh luput
                    long luput = h.getTarikhDaftar().getTime() + (h.getTempohPegangan()
                            * 31556926000l /*ms in a year*/);
                    if (luput < System.currentTimeMillis()) {
                        LOG.warn("Hakmilik " + h.getIdHakmilik() + " cuba dicari walaupun telah tamat tempoh pegangan");
                        listHakmilik.remove(h);
                    }
                }
            }
        }

        return listHakmilik;
    }

    /**
     * Get the Hakmilik sambungan for given idHakmilik
     */
    public List<Hakmilik> getHakmilikSambungan(String idHakmilik) {
        String hql = "select h from Hakmilik h, HakmilikSebelum hs "
                + "where hs.hakmilikSebelum = :idHakmilik and "
                + "hs.hakmilik.id = h.idHakmilik";

        Session s = sessionProvider.get();
        return s.createQuery(hql).setString("idHakmilik", idHakmilik).list();
    }

    public boolean checkHakmilikBatal(Hakmilik h) {
        if ("B".equals(h.getKodStatusHakmilik().getKod())) {
            return true;
        }

        // check pegangan
        if (h != null && h.getPegangan() == 'P') { // pajakan
            if (h.getTempohPegangan() == null) {
                throw new java.lang.IllegalStateException("Hakmilik.pegangan Pajakan tetapi tempoh pegangan nil");
            }
            if (h.getTarikhLuput() != null) {
                if (h.getTarikhLuput().before(new Date())) {
                    return true;
                }
            } else {
                // calculate tarikh luput
                long luput = h.getTarikhDaftar().getTime() + (h.getTempohPegangan()
                        * 31556926000l /*ms in a year*/);
                if (luput < System.currentTimeMillis()) {
                    return true;
                }
            }
        }

        return false;

    }

    public List<HakmilikPermohonan> findMHSebahagian(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.luasTerlibat != mh.hakmilik.luas "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.penarikBalikan='0' ";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHSebahagian2(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.luasTerlibat != mh.hakmilik.luas "
                + "and mh.permohonan.idPermohonan = :idPermohonan";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHKeseluruhan(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.luasTerlibat = mh.hakmilik.luas "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.penarikBalikan='0' ";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHKeseluruhan2(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.luasTerlibat = mh.hakmilik.luas "
                + "and mh.permohonan.idPermohonan = :idPermohonan ";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHL4(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.nomborRujukan = 'L4' "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.penarikBalikan='0'";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHL4L(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.noRujukan = 'L4' "
                + "and mh.ukurHalus > '10000' "
                + "and mh.permohonan.idPermohonan = :idPermohonan";
        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findMHX4(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "select mh from HakmilikPermohonan mh,Hakmilik h"
                + " where mh.hakmilik.idHakmilik = h.idHakmilik "
                + "and mh.nomborRujukan = 'X4' "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.penarikBalikan='0' ";

        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> findBantahElektrik1(long idPerbicaraan) {
        Session s = sessionProvider.get();
        String query = "select pk from etanah.model.ambil.PerbicaraanKehadiran pk, etanah.model.ambil.HakmilikPerbicaraan hp, etanah.model.HakmilikPermohonan hm"
                + " where hp.idPerbicaraan = pk.perbicaraan.idPerbicaraan "
                + "and hm.id = hp.hakmilikPermohonan.id "
                + "and pk.perbicaraan.idPerbicaraan = :idPerbicaraan "
                + "and pk.bantahElektrik = '1'";

        Query q = s.createQuery(query);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public List<PerbicaraanKehadiran> findBantahElektrik0(long idPerbicaraan) {
        Session s = sessionProvider.get();
        String query = "select pk from etanah.model.ambil.PerbicaraanKehadiran pk, etanah.model.ambil.HakmilikPerbicaraan hp, etanah.model.HakmilikPermohonan hm"
                + " where hp.idPerbicaraan = pk.perbicaraan.idPerbicaraan "
                + "and hm.id = hp.hakmilikPermohonan.id "
                + "and pk.perbicaraan.idPerbicaraan = :idPerbicaraan "
                + "and pk.bantahElektrik = '0'";

        Query q = s.createQuery(query);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return q.list();
    }

    public KodHakmilik getKodHakmilik(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m2 FROM etanah.model.Hakmilik m, etanah.model.KodHakmilik m2 WHERE m2.kod=m.kodHakmilik.kod AND m.idHakmilik = :idHakmilikInduk";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return (KodHakmilik) q.uniqueResult();
    }

    public BadanPengurusan getIDBadan(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.strata.BadanPengurusan m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = s.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (BadanPengurusan) q.uniqueResult();
    }

    public List<Hakmilik> getIDHakmilikByInduk(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk order by m.idHakmilik asc";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Versi4k> getVersiIDHakmilikByInduk(String idhakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Versi4k m WHERE m.hakmilikInduk.idHakmilik = :idhakmilikInduk order by m.idHakmilikStrata asc";
        Query q = s.createQuery(query);
        q.setString("idhakmilikInduk", idhakmilikInduk);
        return q.list();
    }
    
      public Versi4k getVersiIDHakmilikByStrata(String idHakmilikStrata) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Versi4k m WHERE m.idHakmilikStrata = :idHakmilikStrata";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikStrata", idHakmilikStrata);
        return (Versi4k) q.uniqueResult();
    }

    public List<Hakmilik> getIDHakmilikNoBukuSTRATAByInduk(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk and m.noBukuDaftarStrata IS NOT NULL";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getNoBukuSTRATAByInduk(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk AND m.noBukuDaftarStrata IS NULL";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getDaerahMukimbyIDHakmilik(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getHakmilikStratanInduk(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk";
        Query q = s.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> getFilterDaerahMukim(String kodDaerah, int kodMukim) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.daerah.kod = :kodDaerah AND m.bandarPekanMukim.kod = :kodMukim  AND m.syaratNyata.kod IN (SELECT s.kod FROM etanah.model.KodSyaratNyata s) AND m.sekatanKepentingan.kod IN (SELECT k.kod FROM etanah.model.KodSekatanKepentingan k)";
        Query q = s.createQuery(query);
        q.setParameter("kodDaerah", kodDaerah);
        q.setInteger("kodMukim", kodMukim);
        return q.list();
    }

    public List<Hakmilik> getHighestNoBukuSTRATAPTD(String kodDaerah, int kodMukim, String milikD) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m, etanah.model.KodHakmilik m2 WHERE m.daerah.kod = :kodDaerah AND m.bandarPekanMukim.kod = :kodMukim AND m2.kod=m.kodHakmilik.kod AND m2.milikDaerah= :milikD AND m.noBukuDaftarStrata = (SELECT MAX(m3.noBukuDaftarStrata) from etanah.model.Hakmilik m3, etanah.model.KodHakmilik m4  WHERE m3.daerah.kod = :kodDaerah AND m3.bandarPekanMukim.kod = :kodMukim AND m4.kod=m3.kodHakmilik.kod AND m4.milikDaerah= :milikD)";
        Query q = s.createQuery(query);
        q.setParameter("kodDaerah", kodDaerah);
        q.setParameter("milikD", milikD);
        q.setInteger("kodMukim", kodMukim);
        return q.list();
    }

    public List<Hakmilik> getHighestNoBukuSTRATAPTG(String milikD) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m, etanah.model.KodHakmilik m2 WHERE m2.kod=m.kodHakmilik.kod AND m2.milikDaerah= :milikD AND m.noBukuDaftarStrata = (SELECT MAX(m3.noBukuDaftarStrata) from etanah.model.Hakmilik m3, etanah.model.KodHakmilik m4 WHERE m4.kod=m3.kodHakmilik.kod AND m4.milikDaerah= :milikD)";
        Query q = s.createQuery(query);
        q.setParameter("milikD", milikD);
        return q.list();
    }

    public List<HakmilikPermohonan> getIDHakmilikByIDMohon(String idMohon) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idMohon";
        Query q = s.createQuery(query);
        q.setParameter("idMohon", idMohon);
        return q.list();
    }
}

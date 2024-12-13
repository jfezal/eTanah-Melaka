/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.cukaipetak;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.SkimStrataDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.SkimStrata;
import etanah.service.AkaunService;
import etanah.util.StringUtils;
import etanah.view.strata.cukaipetak.MaklumatSkim;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author john
 */
public class CukaiPetakService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    AkaunService akaunService;
    private static final Logger LOG = Logger.getLogger(CukaiPetakService.class);

    public List<String> listSkimToMigrate() {
        String query = "select distinct p.idHakmilikInduk from etanah.model.Hakmilik p "
                + "where p.idHakmilikInduk is not null "
                + "and p.idHakmilikInduk not in (select s.hakmilikInduk.idHakmilik from etanah.model.SkimStrata s)";
        Query q = sessionProvider.get().createQuery(query);

        return q.list();
    }

    @Transactional
    public void validateSkim() {
        for (String idHakmilik : listSkimToMigrate()) {
            Hakmilik h = new Hakmilik();
            h = hakmilikDAO.findById(idHakmilik);

            SkimStrata s = new SkimStrata();
            s.setHakmilikInduk(h);
            s.setFlagCukai("T");
            s.setFlagJana("T");
            skimStrataDAO.save(s);

        }
    }

    public Long findCountSkimStrata(StrataParam param) {
        String query = querySkim(param, true);

//        query = "select count(p) from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
//                + " where p.kategoriBangunan "
//                + "is null and p.kelasTanah is null and p.hakmilikInduk.idHakmilik = h.idHakmilik ";
//        if (!StringUtils.isBlank(param.getIdHakmilik())) {
//            query = "select count(p) from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
//                    + " where p.hakmilikInduk.idHakmilik = h.idHakmilik ";
//            query += "and h.idHakmilik = :idHakmilik ";
//
//        } else {
//            if (!StringUtils.isBlank(param.getDaerah())) {
//
//                query += "and h.daerah.kod = :daerah ";
//            }
//
//            if (!StringUtils.isBlank(param.getBpm())) {
//                query += "and h.bandarPekanMukim.kod = :bpm ";
//
//            }
//            query += " order by p.hakmilikInduk.bandarPekanMukim";
//        }
        Query q = sessionProvider.get().createQuery(query);
        if (!StringUtils.isBlank(param.getIdHakmilik())) {
            q.setParameter("idHakmilik", param.getIdHakmilik());
        } else {

            if (!StringUtils.isBlank(param.getDaerah())) {
                q.setParameter("daerah", param.getDaerah());
            }

            if (!StringUtils.isBlank(param.getBpm())) {
                q.setParameter("bpm", Integer.parseInt(param.getBpm()));

            }
        }
        return (Long) q.iterate().next();
    }

    public String querySkim(StrataParam param, boolean count) {
        String query = "";
        if (count) {
            query = "select count(p)";
        } else {
            query = "select p ";

        }
        query += " from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
                + " where p.kategoriBangunan "
                + "is null and p.kelasTanah is null and p.hakmilikInduk.idHakmilik = h.idHakmilik ";
        if (!StringUtils.isBlank(param.getIdHakmilik())) {
            if (count) {
                query = "select count (p) from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
                        + " where p.hakmilikInduk.idHakmilik = h.idHakmilik ";
                query += "and h.idHakmilik = :idHakmilik ";
            } else {
                query = "select p from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
                        + " where p.hakmilikInduk.idHakmilik = h.idHakmilik ";
                query += "and h.idHakmilik = :idHakmilik ";

            }

        } else {
            if (!StringUtils.isBlank(param.getDaerah())) {

                query += "and h.daerah.kod = :daerah ";
            }

            if (!StringUtils.isBlank(param.getBpm())) {
                query += "and h.bandarPekanMukim.kod = :bpm ";

            }
            query += " order by p.hakmilikInduk.bandarPekanMukim";
        }
        Query q = sessionProvider.get().createQuery(query);
        if (!StringUtils.isBlank(param.getIdHakmilik())) {
            q.setParameter("idHakmilik", param.getIdHakmilik());
        } else {

            if (!StringUtils.isBlank(param.getDaerah())) {
                q.setParameter("daerah", param.getDaerah());
            }

            if (!StringUtils.isBlank(param.getBpm())) {
                q.setParameter("bpm", Integer.parseInt(param.getBpm()));

            }
        }

        return query;
    }

    public List<MaklumatSkim> findSkimStrata(StrataParam param, int i, int a) {
        String query = querySkim(param, false);

//        query = "select p from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
//                + " where p.kategoriBangunan "
//                + "is null and p.kelasTanah is null and p.hakmilikInduk.idHakmilik = h.idHakmilik ";
//        if (!StringUtils.isBlank(param.getIdHakmilik())) {
//            query = "select p from etanah.model.SkimStrata p, etanah.model.Hakmilik h"
//                    + " where p.hakmilikInduk.idHakmilik = h.idHakmilik ";
//            query += "and h.idHakmilik = :idHakmilik ";
//
//        } else {
//            if (!StringUtils.isBlank(param.getDaerah())) {
//
//                query += "and h.daerah.kod = :daerah ";
//            }
//
//            if (!StringUtils.isBlank(param.getBpm())) {
//                query += "and h.bandarPekanMukim.kod = :bpm ";
//
//            }
//            query += " order by p.hakmilikInduk.bandarPekanMukim";
//        }
        Query q = sessionProvider.get().createQuery(query);
        if (!StringUtils.isBlank(param.getIdHakmilik())) {
            q.setParameter("idHakmilik", param.getIdHakmilik());
        } else {

            if (!StringUtils.isBlank(param.getDaerah())) {
                q.setParameter("daerah", param.getDaerah());
            }

            if (!StringUtils.isBlank(param.getBpm())) {
                q.setParameter("bpm", Integer.parseInt(param.getBpm()));

            }
        }
        q.setFirstResult(i);
        q.setMaxResults(a);
        List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();
            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }
            ms.add(mss);
        }
        return ms;
    }

    public List<MaklumatSkim> findSkimStrataJanaCukai() {

        String query = "select p from etanah.model.SkimStrata p where p.kategoriBangunan is not null and p.kelasTanah is not null "
                + "and (p.flagCukai = 'T' or p.flagJana ='T')";
        Query q = sessionProvider.get().createQuery(query);

        List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();
            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            mss.setKeluasanTanah("" + s.getHakmilikInduk().getLuas() + " " + s.getHakmilikInduk().getKodUnitLuas().getNama());
            //mss.setKeluasanPembangunan("" + totalLuasPerSkim(s.getHakmilikInduk().getIdHakmilik()));
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }
            if (s.getFlagCukai().equals("Y")) {
                mss.setFlagCukai(true);
            }
            mss.setFlagJana(s.getFlagJana().equals("Y"));
            ms.add(mss);
        }
        return ms;
    }

    public List<MaklumatSkim> findSkimStrataJanaCukai(String idHakmilik) {

        String query = "select p from etanah.model.SkimStrata p where p.kategoriBangunan is not null and p.kelasTanah is not null "
                + "and p.hakmilikInduk.idHakmilik = :idHakmilik and (p.flagCukai = 'T' or p.flagJana ='T' )";

        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();
            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            mss.setKeluasanTanah("" + s.getHakmilikInduk().getLuas() + " " + s.getHakmilikInduk().getKodUnitLuas().getNama());
            //mss.setKeluasanPembangunan("" + totalLuasPerSkim(s.getHakmilikInduk().getIdHakmilik()));
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }
            if (s.getFlagCukai().equals("Y")) {
                mss.setFlagCukai(true);
            }
            mss.setFlagJana(s.getFlagJana().equals("Y"));
            ms.add(mss);
        }
        return ms;
    }

    private Long totalLuasPerSkim(String idHakmilikInduk) {
        long total = 0;
        String query = "select sum(p.luas) from etanah.model.Hakmilik p where p.idHakmilikInduk =:idHakmilikInduk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);

        long totalLuaspetak = (Long) q.uniqueResult();

        String query2 = "select sum(p.luas) from etanah.model.HakmilikPetakAksesori p where p.hakmilik.idHakmilikInduk =:idHakmilikInduk ";
        Query q2 = sessionProvider.get().createQuery(query2);
        q.setString("idHakmilikInduk", idHakmilikInduk);

        long totalLuaspetakAks = (Long) q2.uniqueResult();
        total = totalLuaspetak + totalLuaspetakAks;
        return total;
    }

    public List<Hakmilik> findHakmilikStrataByIdInduk(String idHakmilikInduk) {
        String query = "select p from etanah.model.Hakmilik p where p.idHakmilikInduk =:idHakmilikInduk and p.kodStatusHakmilik.kod = 'D'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public java.math.BigDecimal luasHakmilikStrata(String idHakmilik) {
        String query = "select sum(p.luas) from etanah.model.Hakmilik p where p.idHakmilik =:idHakmilik and p.kodStatusHakmilik.kod = 'D'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (BigDecimal) q.uniqueResult();
    }

    public BigDecimal luasHakmilikAksByidStrata(String idHakmilik) {
        String query = "select sum(p.luas) from etanah.model.HakmilikPetakAksesori p where p.hakmilik.idHakmilik =:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (BigDecimal) q.uniqueResult();
    }

    @Transactional
    public void saveSkimStrata(SkimStrata skim) {
        skimStrataDAO.save(skim);
    }

    public List<MaklumatSkim> findSkimStrataByParameter(String daerah) {
        String query = "";
        if (StringUtils.isBlank(daerah)) {
            query = "select p from etanah.model.SkimStrata p where p.kategoriBangunan "
                    + "is null and p.kelasTanah is null";
        } else {
            query = "select p from etanah.model.SkimStrata p , etanah.model.Hakmilik h where p.kategoriBangunan "
                    + "is null or p.kelasTanah is null and p.hakmilikInduk.idHakmilik = h.idHakmilik "
                    + " and h.daerah.kod = :daerah";
        }
        query += " order by p.hakmilikInduk.bandarPekanMukim";

        Query q = sessionProvider.get().createQuery(query);
        if (StringUtils.isBlank(daerah)) {
        } else {
            q.setParameter("daerah", daerah);
        }
        List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();
            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }
            ms.add(mss);
        }
        return ms;
    }

    public SkimStrata findBySkimId(String id) {
        String query = "select s from etanah.model.SkimStrata s where s.id =:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", id);
        return (SkimStrata) q.uniqueResult();
    }

    public String findKadar(String katgBangunan, String kelasTanah) {
        String query = "select s.kadar from etanah.model.CukaiPetak s where s.kategoriBangunan.kod =:katgBangunan "
                + "and s.kelasTanah.kod =:kelasTanah";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("katgBangunan", katgBangunan);
        q.setString("kelasTanah", kelasTanah);

        return (String) q.uniqueResult();
    }

    public String findKadarLanded(String katgBangunan, String kelasTanah) {
        String query = "select s.kadarLanded from etanah.model.CukaiPetak s where s.kategoriBangunan.kod =:katgBangunan "
                + "and s.kelasTanah.kod =:kelasTanah";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("katgBangunan", katgBangunan);
        q.setString("kelasTanah", kelasTanah);

        return (String) q.uniqueResult();
    }

    Long getTotalUnitSyerPerSkim(String idHakmilik) {
        long total = 0;
        String query = "select sum(p.unitSyer) from etanah.model.Hakmilik p where p.idHakmilikInduk =:idHakmilikInduk "
                + "and p.kodStatusHakmilik.kod ='D'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilik);
        Object test = q.uniqueResult();

        BigDecimal ret = null;
        if (test != null) {
            if (test instanceof BigDecimal) {
                ret = (BigDecimal) test;
            } else if (test instanceof String) {
                ret = new BigDecimal((String) test);
            } else if (test instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) test);
            } else if (test instanceof Number) {
                ret = new BigDecimal(((Number) test).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + test + "] from class " + test.getClass() + " into a BigDecimal.");
            }

        }
        Long totalUnitSyer = ret.longValue();

//        BigDecimal totalUnitSyer = Object
//        String query2 = "select sum(p.luas) from etanah.model.HakmilikPetakAksesori p where p.hakmilik.idHakmilikInduk =:idHakmilik "
//                + "and p.kodStatusHakmilik.kod ='D'";
//        Query q2 = sessionProvider.get().createQuery(query2);
//        q.setString("idHakmilikInduk", idHakmilik);
//        
//        long totalLuaspetakAks = (Long) q2.uniqueResult();
//        total = totalLuaspetak + totalLuaspetakAks;
        return totalUnitSyer;
    }

    List<Hakmilik> findHakmilikByIdHakmilikInduk(String idHakmilik) {
        long total = 0;
        String query = "select p from etanah.model.Hakmilik p where p.idHakmilikInduk =:idHakmilikInduk "
                + "and p.kodStatusHakmilik.kod ='D'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilik);
        return q.list();
    }

    public List<MaklumatSkim> findSkimTunggakan(int i, int a) {
        String query = "select p from etanah.model.SkimStrata p , etanah.model.Hakmilik h, etanah.model.Akaun a where "
                + "p.hakmilikInduk.idHakmilik = h.idHakmilik "
                + "and a.hakmilik.idHakmilik = h.idHakmilik "
                + "and a.baki <> 0 and a.isJana = 'T' ";

        query += " order by p.hakmilikInduk.bandarPekanMukim";

        Query q = sessionProvider.get().createQuery(query);
//        q.setFirstResult(i);
//        q.setMaxResults(a);
       
        List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();

            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setJumlahUnitSyer(findHakmilikByIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik()).isEmpty() ? 0 : findHakmilikByIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik()).get(0).getJumlahUnitSyer());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }
           

            BigDecimal cukai = getCukai(s.getHakmilikInduk().getCawangan().getKod(), s.getHakmilikInduk().getIdHakmilik());
            if (cukai.compareTo(BigDecimal.ZERO) < 0) {
            } else {
                mss.setCukai(getCukai(s.getHakmilikInduk().getCawangan().getKod(), s.getHakmilikInduk().getIdHakmilik()));
                ms.add(mss);
            }
        }
        return ms;
    }

    public Long findCountSkimTunggakan() {
        String query = "select count(p) from etanah.model.SkimStrata p , etanah.model.Hakmilik h, etanah.model.Akaun a where "
                + "p.hakmilikInduk.idHakmilik = h.idHakmilik "
                + "and a.hakmilik.idHakmilik = h.idHakmilik "
                + "and a.baki <> 0 and a.isJana = 'T' ";

        query += " order by p.hakmilikInduk.bandarPekanMukim";

        Query q = sessionProvider.get().createQuery(query);

        return (Long) q.iterate().next();
    }

    private BigDecimal getCukai(String kodCaw, String idHakmilik) {
//        LOG.info("hakmilik" + hakmilik.getIdHakmilik());
//        LOG.info("kod caw>>>" + hakmilik.getCawangan().getKod());

        Akaun a = akaunService.getAkaunCukaiActiveForStrataHakmilik(kodCaw, idHakmilik);

        LOG.info("hakmilik" + idHakmilik);
//        LOG.info("kod caw>>>" + a.getBaki());
        if (a != null) {
            if (a.getBaki() != null) {
                return a.getBaki();
            } else {
                return new BigDecimal(0);
            }
        } else {

            return new BigDecimal(0);
        }
    }

    public List<MaklumatSkim> findSkimTunggakanIdHakmilik(String idHakmilik) {
 String query = "select p from etanah.model.SkimStrata p , etanah.model.Hakmilik h, etanah.model.Akaun a where "
                + "p.hakmilikInduk.idHakmilik = h.idHakmilik "
                + "and a.hakmilik.idHakmilik = h.idHakmilik and h.idHakmilik = :idHakmilik "
                + "and a.baki <> 0 and a.isJana = 'T'";

        query += " order by p.hakmilikInduk.bandarPekanMukim";

        Query q = sessionProvider.get().createQuery(query);
q.setString("idHakmilik", idHakmilik);
     List<MaklumatSkim> ms = new ArrayList<MaklumatSkim>();
        for (Iterator it = q.list().iterator(); it.hasNext();) {
            SkimStrata s = (SkimStrata) it.next();
            MaklumatSkim mss = new MaklumatSkim();
            mss.setBpmMaster(s.getHakmilikInduk().getBandarPekanMukim().getNama());
            mss.setId(s.getId());
            mss.setIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik());
            if (s.getKelasTanah() != null) {
                mss.setKelasTanah(s.getKelasTanah().getKod());
                mss.setKelasTanahNama(s.getKelasTanah().getNama());
            }
            mss.setNoBukuStrata(s.getHakmilikInduk().getNoBukuDaftarStrata());
            mss.setNoSkim(s.getHakmilikInduk().getNoSkim());
            mss.setJumlahUnitSyer(findHakmilikByIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik()).isEmpty() ? 0 : findHakmilikByIdHakmilikInduk(s.getHakmilikInduk().getIdHakmilik()).get(0).getJumlahUnitSyer());
            mss.setSyaratNyataMaster(s.getHakmilikInduk().getSyaratNyata().getSyarat());
            if (s.getKategoriBangunan() != null) {
                mss.setKatgBangunan(s.getKategoriBangunan().getKod());
                mss.setKatgBangunanNama(s.getKategoriBangunan().getNama());

            }
            if (!StringUtils.isBlank(mss.getKatgBangunan()) && !StringUtils.isBlank(mss.getKelasTanah())) {
                mss.setStatusKemaskini("Ya");
            } else {
                mss.setStatusKemaskini("Tidak");
            }

            BigDecimal cukai = getCukai(s.getHakmilikInduk().getCawangan().getKod(), s.getHakmilikInduk().getIdHakmilik());
            if (cukai.compareTo(BigDecimal.ZERO) < 0) {
            } else {
                mss.setCukai(getCukai(s.getHakmilikInduk().getCawangan().getKod(), s.getHakmilikInduk().getIdHakmilik()));
                ms.add(mss);
            }

        }
return ms;
    }

}

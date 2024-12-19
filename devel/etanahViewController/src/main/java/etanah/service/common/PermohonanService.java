/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import static com.tangosol.net.DefaultCacheServer.start;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonanRayuanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.CarianHakmilik;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KodKadarBayaran;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanRayuan;
import etanah.model.PermohonanUrusan;
import etanah.model.Transaksi;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.util.StringUtils;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author md.nurfikri
 */
public class PermohonanService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private GeneratorIdPermohonan idGenerator;
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOGGER = Logger.getLogger(PermohonanService.class);
    private boolean isDebug = LOGGER.isDebugEnabled();
    @Inject
    private PermohonanHubunganDAO permohonanHubunganDAO;
    private Map<String, Object> map;
    private List<Map> listMap = new ArrayList<Map>();
    private List<FasaPermohonan> listFasaPermohonan;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    PemohonanRayuanDAO permohonanRayuanDAO;

    @Transactional
    public Permohonan saveOrUpdate(Permohonan p) {
        p = permohonanDAO.saveOrUpdate(p);
        return p;
    }

    @Transactional
    public void saveOrUpdateFolder(FolderDokumen fd) {
        folderDokumenDAO.saveOrUpdate(fd);
    }

    @Transactional
    public PermohonanUrusan saveOrUpdate(PermohonanUrusan p) {
        p = permohonanUrusanDAO.saveOrUpdate(p);
        return p;
    }

    @Transactional
    public void update(Permohonan p) {
        permohonanDAO.update(p);
    }

    public Permohonan saveOrUpdateConn(Permohonan p) {
        p = permohonanDAO.saveOrUpdate(p);
        return p;
    }

    @Transactional
    public void savePermohonanHubungan(List<PermohonanHubungan> list) {
        for (PermohonanHubungan ph : list) {
            permohonanHubunganDAO.save(ph);
        }
    }

    @Transactional
    public void deletePermohonan(Permohonan p) {
        permohonanDAO.delete(p);
    }

    public Permohonan findById(String id) {
        return permohonanDAO.findById(id);
    }

    public List<Permohonan> findByIdmohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getSenaraiPermohonanByIdPermohonanIdHakmilik(String idPermohonan, String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp");
        sb.append(" WHERE p.idPermohonan = hp.permohonan.idPermohonan");
        if (org.apache.commons.lang.StringUtils.isNotBlank(idHakmilik)) {
            sb.append(" AND hp.hakmilik.idHakmilik = :idHakmilik");
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            sb.append(" AND p.idPermohonan = :idPermohonan");
        }

        Query q = sessionProvider.get().createQuery(sb.toString());
        if (org.apache.commons.lang.StringUtils.isNotBlank(idHakmilik)) {
            q.setString("idHakmilik", idHakmilik);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }

        return q.list();
    }

    //Added by Aizuddin for find urusan dibatalkan urusan JMGD
    public List<HakmilikUrusan> getUrusanDibatalkanIdPermohonan(String idPermohonan) {
        StringBuilder sb = new StringBuilder("Select hu from etanah.model.HakmilikUrusan hu, etanah.model.PermohonanAtasPerserahan pap");
        sb.append(" WHERE pap.urusan.idUrusan = hu.idUrusan");
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            sb.append(" AND pap.permohonan.idPermohonan = :idPermohonan");
        }
        Query q = sessionProvider.get().createQuery(sb.toString());
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q.setString("idPermohonan", idPermohonan);
        }
        return q.list();
    }

    public List<Permohonan> searchPermohonanGantung(Map<String, String[]> param) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.keputusan.kod = 'G'";
        if (StringUtils.isNotBlank(param.get("permohonan.idPermohonan"))) {
            query += " AND p.idPermohonan = :idPermohonan";
        }
        LOGGER.info("query ::" + query);
        Query q = sessionProvider.get().createQuery(query);
        if (StringUtils.isNotBlank(param.get("permohonan.idPermohonan"))) {
            q.setString("idPermohonan", param.get("permohonan.idPermohonan")[0]);
        }
        return q.list();
    }

    public List<Permohonan> getSenaraiPermohonan(Date d, String idHakmilik) {
        long s = System.currentTimeMillis();
        String tarikh = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(d);
        StringBuilder query = new StringBuilder("SELECT p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp");
        query.append(" WHERE p.idPermohonan = hp.permohonan.idPermohonan");
        query.append(" AND hp.hakmilik.idHakmilik = :idHakmilik");
//        query.append(" AND hp.hubunganHakmilik is null"); //ismen using hubunganHakmilik
        query.append(" AND p.idPermohonan not in ");
        query.append(" (SELECT hu.idPerserahan FROM etanah.model.HakmilikUrusan hu ");
        query.append(" where hu.hakmilik.idHakmilik = :idHakmilik)");
        query.append(" order by p.infoAudit.tarikhMasuk");
//                query.append(" AND p.keputusan is null");//taksemestinya keputusan not null
//                query.append(" AND to_char(p.infoAudit.tarikhMasuk, 'YYYY-MM-DD HH:MI:SS') <= :date");
        LOGGER.debug(query.toString() + "\nidHakmilik =" + idHakmilik + ",date=" + tarikh);
        Query q = sessionProvider.get().createQuery(query.toString());
        q.setString("idHakmilik", idHakmilik);
//        q.setString("date", tarikh);
        LOGGER.debug("finish::" + (System.currentTimeMillis() - s));
        return q.list();
    }

    public List<Permohonan> getSenaraiMohonByIdHakmilik(String idHakmilik) {

        StringBuilder query = new StringBuilder("SELECT p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp");
        query.append(" WHERE p.idPermohonan = hp.permohonan.idPermohonan");
        query.append(" AND hp.hakmilik.idHakmilik = :idHakmilik");
        query.append(" AND p.status != 'SL'");
        query.append(" AND p.kodUrusan.kod != 'N'");
//        query.append(" (SELECT hu.idPerserahan FROM etanah.model.HakmilikUrusan hu ");
//        query.append(" where hu.hakmilik.idHakmilik = :idHakmilik)");
        query.append(" order by p.infoAudit.tarikhMasuk");
        Query q = sessionProvider.get().createQuery(query.toString());
        q.setString("idHakmilik", idHakmilik);
//        q.setString("date", tarikh);
        return q.list();
    }

    public List<Permohonan> getSenaraiPermohonanByIdHakmilik(String idHakmilik) {

        StringBuilder query = new StringBuilder("SELECT p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp");
        query.append(" WHERE p.idPermohonan = hp.permohonan.idPermohonan");
        query.append(" AND hp.hakmilik.idHakmilik = :idHakmilik");
        query.append(" AND p.keputusan is null");
//        query.append(" (SELECT hu.idPerserahan FROM etanah.model.HakmilikUrusan hu ");
//        query.append(" where hu.hakmilik.idHakmilik = :idHakmilik)");
        query.append(" order by p.infoAudit.tarikhMasuk");
        Query q = sessionProvider.get().createQuery(query.toString());
        q.setString("idHakmilik", idHakmilik);
//        q.setString("date", tarikh);
        return q.list();
    }

    public List<Permohonan> searchPermohonanByKodUrusanIDPermohonan(Map<String, String[]> param) {
        boolean flag = false;
        String query = "Select p FROM etanah.model.Permohonan p where 1=1";
        if (StringUtils.isNotBlank(param.get("permohonan.idPermohonan"))) {
            query += " AND p.idPermohonan = :idPermohonan";
            flag = true;
        }
        if (StringUtils.isNotBlank(param.get("permohonan.kodUrusan.kod"))) {
            query += " AND p.kodUrusan.kod = :kod";
            flag = true;
        }

        if (!flag) {
            List<Permohonan> list = new LinkedList<Permohonan>();
            return list;
        }

        Query q = sessionProvider.get().createQuery(query);
        if (StringUtils.isNotBlank(param.get("permohonan.idPermohonan"))) {
            q.setString("idPermohonan", param.get("permohonan.idPermohonan")[0]);
        }
        if (StringUtils.isNotBlank(param.get("permohonan.idPermohonan"))) {
            q.setString("kod", param.get("permohonan.kodUrusan.kod")[0]);
        }
        return q.list();
    }

    public boolean isPrevUrusanBerangkaiNotComplete(String idHakmilik, String idPermohonanSblm) {
//        String tarikh = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sssss")).format(date);
        String q = "select m, hp from etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp";
        q += " where m.idPermohonan = hp.permohonan.idPermohonan";
        q += " and m.keputusan is null";
        q += " and hp.hakmilik.idHakmilik = :idHakmilik";
        q += " and m.idPermohonan = :idPermohonan";
//        q += " and m.idPermohonan not in( :idPermohonan )" ;
//        q += " and to_char(m.infoAudit.tarikhMasuk, 'YYYY-MM-DD HH:MI:SS:SSSSS') < :date";
        //TODO : check against tkh_mohon
        LOGGER.info("query ::" + q);
        LOGGER.info("idHakmilik ::" + idHakmilik);
        LOGGER.info("idPermohonanSblm ::" + idPermohonanSblm);
//        LOGGER.info("date :;" + tarikh);
        Query query = sessionProvider.get().createQuery(q).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonanSblm);
        return (query.list().size() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    public boolean isPrevUrusanNotComplete(String idHakmilik, Date date) {
        String tarikh = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sssss")).format(date);
        String q = "select m, hp from etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp";
        q += " where m.idPermohonan = hp.permohonan.idPermohonan";
        q += " and m.keputusan is null";
        q += " and hp.hakmilik.idHakmilik = :idHakmilik";
        q += " and to_char(m.infoAudit.tarikhMasuk, 'YYYY-MM-DD HH24:MI:SS:SSSSS') < :date";
        //TODO : check against tkh_mohon
        LOGGER.info("query ::" + q);
        LOGGER.info("idHakmilik ::" + idHakmilik);
        LOGGER.info("idPermohonanSblm ::" + tarikh);
//        LOGGER.info("date :;" + tarikh);
        Query query = sessionProvider.get().createQuery(q).setString("idHakmilik", idHakmilik).setString("date", tarikh);
        return (query.list().size() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    public List<Permohonan> getUrusanNotComplete(String idHakmilik, Date date) {
        String tarikh = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sssss")).format(date);
        String q = "select m from etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp";
        q += " where m.idPermohonan = hp.permohonan.idPermohonan";
        q += " and m.keputusan is null";
        q += " and hp.hakmilik.idHakmilik = :idHakmilik";
        q += " and to_char(m.infoAudit.tarikhMasuk, 'YYYY-MM-DD HH24:MI:SS:SSSSS') < :date";
        //TODO : check against tkh_mohon
        LOGGER.info("query ::" + q);
        LOGGER.info("idHakmilik ::" + idHakmilik);
        LOGGER.info("idPermohonanSblm ::" + tarikh);
//        LOGGER.info("date :;" + tarikh);
        Query query = sessionProvider.get().createQuery(q).setString("idHakmilik", idHakmilik).setString("date", tarikh);
        return query.list();
    }

    public List<Permohonan> getPermohonanByIdKump(String idKumpulan) {
//        String q = "Select m from etanah.model.Permohonan m where m.idKumpulan = :id order by m.infoAudit.tarikhMasuk, m.permohonanSebelum.idPermohonan";
        String q = "Select m from etanah.model.Permohonan m where m.idKumpulan = :id order by m.kumpulanNo, m.permohonanSebelum.idPermohonan desc";
        LOGGER.info("query ::" + q);
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        return query.list();
    }

    public List<Permohonan> getPermohonanByIdKumpAll() {
        String q = "select distinct(u.idKumpulan)\n"
                + "from etanah.model.Permohonan u \n"
                + "where u.idKumpulan is not null \n"
                + "and idKumpulan not in ('13A','12A','11A')\n"
                + "order by to_number(idKumpulan) desc";
        LOGGER.info("query ::" + q);
        q.valueOf(0);
        Query query = sessionProvider.get().createQuery(q);
        return query.list();
    }

    public List<Permohonan> getPermohonanByIdKumpTrhMasuk(String idKumpulan, String trh) {
        String q = "Select m from etanah.model.Permohonan m where m.idKumpulan = :id";

        if (org.apache.commons.lang.StringUtils.isNotBlank(trh)) {
            q += " and to_char(m.infoAudit.tarikhMasuk, 'DD/MM/YYYY') = :date";
        }
        q += " order by m.kumpulanNo";
        LOGGER.info("query ::" + q);
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        if (org.apache.commons.lang.StringUtils.isNotBlank(trh)) {
            query.setParameter("date", trh);
        }
        return query.list();
    }

    public List<PermohonanHubungan> getByIdSumber(String idPermohonan, String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ph FROM etanah.model.PermohonanHubungan ph WHERE ph.permohonanSumber.idPermohonan = :idPermohonan"
                + "and ph.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
     public List<PermohonanHubungan> getByIdSumberOnly(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT ph FROM etanah.model.PermohonanHubungan ph WHERE ph.permohonanSumber.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> findByIdKump(String idKumpulan) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.idKumpulan = :id "
                + "order by m.idPermohonan asc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        return query.list();
    }
    public List<Permohonan> findByIdSblm(String idsblm) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.permohonanSebelum = :id "
                + "order by m.idPermohonan asc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idsblm);
        return query.list();
    }
    
    public List<Permohonan> findByIdSblmRyn(String idsblm) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.permohonanSebelum = :id and m.kodUrusan.kod = 'RYN' "
                + "order by m.idPermohonan asc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idsblm);
        return query.list();
    }
    public List<Permohonan> findByIdKumpPHMM(String idKumpulan) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.idKumpulan = :id "
                + "and m.kodUrusan.kod ='PHMM' "
                + "order by m.kumpulanNo desc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        return query.list();
    }
    public List<Permohonan> findByIdKumpGDL(String idKumpulan) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.idKumpulan = :id "
                + "and m.kodUrusan.kod ='GDL' "
                + "order by m.kumpulanNo desc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        return query.list();
    }
    
    public List<Permohonan> findByIdKumpByKodrusanPHMM(String idKumpulan) {
        String q = "Select m from etanah.model.Permohonan m "
                + "where m.idKumpulan = :id "
                + "and m.kodUrusan = 'PHMM' "
                + "order by m.kumpulanNo desc";
        Query query = sessionProvider.get().createQuery(q).setString("id", idKumpulan);
        return query.list();
    }

    public Long getTotalRecord(String idPermohonan, String idHakmilik, String kodCaw) {
        String q = "";
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select count(m) from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        } else {
            q = "SELECT count(m) FROM etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp ";
            q += "WHERE m.idPermohonan = hp.permohonan.idPermohonan ";
            q += "AND hp.hakmilik.idHakmilik = :idHakmilik";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);
        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            query.setString("idPermohonan", "%" + idPermohonan + "%");
        } else {
            query.setString("idHakmilik", idHakmilik);
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return (Long) query.iterate().next();
    }

    public List<Permohonan> getSenaraiPermohonanPartial(String idPermohonan, String idHakmilik, String kodCaw, int start, int max) {
        if (isDebug) {
            LOGGER.debug("from record [" + start + "]");
            LOGGER.debug("to record [" + max + "]");
        }
        String q = "";

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            q = "Select m from etanah.model.Permohonan m where m.idPermohonan like :idPermohonan";
        } else {
            q = "SELECT m FROM etanah.model.Permohonan m, etanah.model.HakmilikPermohonan hp ";
            q += "WHERE m.idPermohonan = hp.permohonan.idPermohonan ";
            q += "AND hp.hakmilik.idHakmilik = :idHakmilik";
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            q += " and m.cawangan.kod = :caw";
        }
        Query query = sessionProvider.get().createQuery(q);

        if (org.apache.commons.lang.StringUtils.isNotBlank(idPermohonan)) {
            query.setString("idPermohonan", "%" + idPermohonan + "%");
        } else {
            query.setString("idHakmilik", idHakmilik);
        }

        query.setFirstResult(start);
        query.setMaxResults(max);
        if (org.apache.commons.lang.StringUtils.isNotBlank(kodCaw)) {
            query.setParameter("caw", kodCaw);
        }
        return query.list();
    }

    /*
   *  for permohonan that not start with spoc
   *  generate id permohonan and send to bpel process
   *  @param KodUrusan
   *  @param Pengguna
   *  @param hakmilikList
     */
    public boolean generateIdPermohonanWorkflow(KodUrusan ku, Pengguna pengguna, List<Hakmilik> hakmilikList) {
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        if (ku == null) {
            return false;
        }
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        long idFolder = Long.parseLong(tarikh); // TODO create seq

        try {

            // open folder for storing submitted documents
            // only one folder for all submission
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk("TEST_" + tarikh); // TODO
            fd.setInfoAudit(ia);
            fd.setFolderId(idFolder);
            folderDokumenDAO.save(fd);

            Permohonan p = new Permohonan();
            p.setStatus("TA");
            p.setIdPermohonan(idGenerator.generate(conf.getProperty("kodNegeri"), caw, ku));
            p.setCawangan(pengguna.getKodCawangan());
            p.setKodUrusan(ku);
            p.setFolderDokumen(fd);
            p.setInfoAudit(ia);
            permohonanDAO.save(p);

            for (Hakmilik hm : hakmilikList) {
                String id = hm.getIdHakmilik();
                hm = hakmilikDAO.findById(id);
                if (hm == null) {
                    throw new RuntimeException("ID Hakmilik " + id + " tidak dijumpai.");
                }
                HakmilikPermohonan hpa = new HakmilikPermohonan();
                hpa.setHakmilik(hm);
                hpa.setInfoAudit(ia);
                hpa.setPermohonan(p);
                hakmilikPermohonanDAO.save(hpa);
            }

            WorkFlowService.initiateTask(p.getKodUrusan().getIdWorkflow(),
                    p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                    p.getKodUrusan().getNama());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOGGER.error(t);
            return false;
        }
        return true;
    }

    public Permohonan findMohonActive(String idPermohonan) {
        String query = "Select h from etanah.model.Permohonan h where h.idPermohonan = :idPermohonan" + " and h.status in ('TS','TA','AK','GB','TP', 'SS')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

//    add by azri 12/7/2013:
    public Permohonan findByIdKumpAndKumpNo(String idKump, int kumpNo) {
        String query = "Select h from etanah.model.Permohonan h where h.idKumpulan = :idKumpulan"
                + " and h.status in ('TS','TA','AK','GB','TP', 'SS') and h.kumpulanNo = :kumpulanNo";
        Query q = sessionProvider.get().createQuery(query).setString("idKumpulan", idKump).setInteger("kumpulanNo", kumpNo);
        return (Permohonan) q.uniqueResult();
    }

    public Permohonan searchPermohonanTolakGantung(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.idPermohonan = :idPermohonan and p.keputusan.kod in ('G','T')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public Permohonan searchPermohonanSebelum(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan and p.status not in ('TK','SL')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }
    
    public Permohonan searchPermohonanSebelum1(String idPermohonan, String kodUrusan) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan and p.status not in ('TK') and p.kodUrusan.kod = :kodUrusan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("kodUrusan", kodUrusan);
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanUrusan findPermohonanUrusan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanUrusan p where p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (PermohonanUrusan) q.uniqueResult();
    }

    public void updateStsPermohonan(Permohonan permohonan, String sts, Pengguna pengguna) {
        InfoAudit ia = permohonan.getInfoAudit();
        ia.setTarikhKemaskini(new Date());
        ia.setDiKemaskiniOleh(pengguna);
        permohonan.setStatus(sts);
        permohonan.setInfoAudit(ia);
        saveOrUpdate(permohonan);
    }

//public List<Permohonan> findMohonByCawAndDate1(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
//        String query = "Select h from etanah.model.Permohonan h, etanah.model.HakmilikPermohonan m, etanah.model.CarianHakmilik c where h.idPermohonan = m.permohonan and m.permohonan = c.idHakmilik and h.cawangan = c.cawangan and h.cawangan = :kod_caw and h.kodUrusan.jabatan = c.kodUrusan.jabatan and h.kodUrusan.jabatan = :kod_jab and h.kodUrusan.kodPerserahan = :kod_serah and c.kodUrusan.kodPerserahan = h.kodUrusan.kodPerserahan and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
//        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("kod_serah",kod_serah).setString("date1", d1).setString("date2", d2);
//        return q.list();
//    }
//public List<Permohonan> findMohonByCawAndDateAll(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
//        String query = "Select h from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
//        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);
//        
//        String queryHM = "Select c from etanah.model.CarianHakmilik c where c.cawangan = :kod_caw and c.kodUrusan.jabatan = :kod_jab and c.infoAudit.tarikhMasuk between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by c.infoAudit.tarikhMasuk ASC";
//        Query q2 = sessionProvider.get().createQuery(queryHM).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);
//        
//        List<Permohonan> a = new ArrayList<Permohonan>(q.list());
//        List<Permohonan> b = new ArrayList<Permohonan>(q2.list());
//             
//        a.addAll(b);
//        
//        List<Permohonan> c=new ArrayList<Permohonan>(a);
//        return c;
//    }
    public List<Permohonan> findMohonByCawAndDateAll(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String query = "Select h from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

        String queryHM = "Select c from etanah.model.CarianHakmilik c where c.cawangan = :kod_caw and c.kodUrusan.jabatan = :kod_jab and c.infoAudit.tarikhMasuk between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by c.infoAudit.tarikhMasuk ASC";
        Query q2 = sessionProvider.get().createQuery(queryHM).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

        List<Permohonan> a = new ArrayList<Permohonan>(q.list());
        List<Permohonan> b = new ArrayList<Permohonan>(q2.list());

        a.addAll(b);

        List<Permohonan> c = new ArrayList<Permohonan>(a);
        return c;
    }

    public List<Permohonan> findMohonByCawAndDatePermohonan(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String query = "Select h from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.kodUrusan.kodPerserahan = :kod_serah and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("kod_serah", kod_serah).setString("date1", d1).setString("date2", d2);
        return q.list();
    }

    public List<CarianHakmilik> findMohonByCawAndDateCarianHakmilik(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String query = "Select h from etanah.model.CarianHakmilik h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.kodUrusan.kodPerserahan = :kod_serah and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("kod_serah", kod_serah).setString("date1", d1).setString("date2", d2);
        return q.list();
    }

//     public List<Permohonan> findMohonByCawAndDate3(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
//        String query = "Select h from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.kodUrusan.kodPerserahan = :kod_serah and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by infoAudit.tarikhMasuk";
//        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("kod_serah",kod_serah).setString("date1", d1).setString("date2", d2);
//        return q.list();
//    }
//     public List<Permohonan> findMohonByCawAndDate(String kod_caw, String kod_jab, Date d1, Date d2) {
//        String query = "Select h from etanah.model.Permohonan h where  h.cawangan = :kod_caw and  h.kodUrusan.jabatan = :kod_jab and h.infoAudit.tarikhMasuk  between :date1 and :date2 order by infoAudit.tarikhMasuk";
//        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setDate("date1", d1).setDate("date2", d2);
//        return q.list();
//    }
    public List<PermohonanHubungan> getSenaraiHubungan(String idPermohonan, String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select m from etanah.model.PermohonanHubungan m ")
                .append("WHERE m.permohonanSumber.idPermohonan = :idPermohonan ");
        if (org.apache.commons.lang.StringUtils.isNotBlank(idHakmilik)) {
            sb.append("AND m.hakmilik.idHakmilik = :idHakmilik");
        }
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idPermohonan", idPermohonan);
        if (org.apache.commons.lang.StringUtils.isNotBlank(idHakmilik)) {
            q.setParameter("idHakmilik", idHakmilik);
        }
        return q.list();
    }

    public List<PermohonanHubungan> validatePerhubunganPermohonan(List<String> list, String idHakmilik) {
        StringBuilder sb = new StringBuilder("Select m from etanah.model.PermohonanHubungan m ")
                .append("WHERE m.permohonanSumber.idPermohonan in (:list) ")
                .append("AND m.hakmilik.idHakmilik = :idHakmilik");
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameterList("list", list)
                .setParameter("idHakmilik", idHakmilik);

        return q.list();
    }

    public List<Permohonan> getPermohonanSebelum(String idKumpulan, int kumpulanNo) {
        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.idKumpulan = :idKumpulan ")
                .append("AND m.kumpulanNo < :kumpulanNo ")
                .append("ORDER BY m.kumpulanNo DESC");
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idKumpulan", idKumpulan)
                .setParameter("kumpulanNo", kumpulanNo);
        return q.list();
    }

    //Added by Aizuddin to filter SMK
    public Permohonan findSyarikatSMDaftar(String idPermohonan) {
        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.idPermohonan = :idPermohonan ")
                .append("AND m.keputusan = 'D' ")
                .append("AND m.status = 'SL' ")
                .append("AND m.kodUrusan = 'SMD'");
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public List<Map> findMohonAndCarianByCawAndDateAll(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String query = "Select h.idPermohonan, h.kodUrusan.nama, h.infoAudit.tarikhMasuk, h.status, h.infoAudit.dimasukOleh.nama from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

        String queryHM = "Select c.permohonanCarian.idCarian, c.permohonanCarian.urusan.nama, c.infoAudit.tarikhMasuk, c.infoAudit.dimasukOleh.nama, c.infoAudit.dimasukOleh.nama from etanah.model.CarianHakmilik c where c.cawangan = :kod_caw and c.kodUrusan.jabatan = :kod_jab and c.infoAudit.tarikhMasuk between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by c.infoAudit.tarikhMasuk ASC";
        Query q2 = sessionProvider.get().createQuery(queryHM).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

//        Query 1 : Mohon
        Iterator iter = q.iterate();
        map = new HashMap<String, Object>();

        listMap = new ArrayList<Map>();
        while (iter.hasNext()) {
            map = new HashMap<String, Object>();
            Object[] row = (Object[]) iter.next();
            int count = 0;
            for (Object object : row) {
                if (count == 0) {
                    map.put("idPermohonan", object.toString());
                }
                if (count == 1) {
                    map.put("urusan", object.toString());
                }
                if (count == 2) {
                    map.put("tarikh", object);
                }
                if (count == 3) {
                    map.put("status", object.toString());
                }
                if (count == 4) {
//                    Object id = map.get("idPermohonan");
//                    listFasaPermohonan = fasaPermohonanService.findStatus(id.toString());
//
//                    if (!listFasaPermohonan.isEmpty()) {
//                        for (FasaPermohonan fp : listFasaPermohonan) {
//                            if (fp.getIdAliran().contains("kemasukan")) {
//                                Pengguna pengguna = penggunaDAO.findById(fp.getIdPengguna());
//                                map.put("dimasuk", pengguna.getNama());
//                            }
//                            else map.put("dimasuk", object.toString());
//                        }
//                    }
//                    else 
                    map.put("dimasuk", object.toString());
                }
                count++;
            }
            listMap.add(map);
        }

//        Query 2 : Carian
        Iterator iter2 = q2.iterate();
        map = new HashMap<String, Object>();

        while (iter2.hasNext()) {
            map = new HashMap<String, Object>();
            Object[] row = (Object[]) iter2.next();
            int count = 0;
            for (Object object : row) {
                if (count == 0) {
                    map.put("idPermohonan", object.toString());
                }
                if (count == 1) {
                    map.put("urusan", object.toString());
                }
                if (count == 2) {
                    map.put("tarikh", object);
                }
                if (count == 3) {
                    map.put("status", "-");
                }
                if (count == 4) {
                    map.put("dimasuk", object.toString());
                }
                count++;
            }
            listMap.add(map);
        }
        return listMap;
    }

    public List<Map> findMohonCawAndDateAll(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String query = "Select h.idPermohonan, h.kodUrusan.nama, h.infoAudit.tarikhMasuk, h.status, h.infoAudit.dimasukOleh.nama from etanah.model.Permohonan h where h.cawangan = :kod_caw and h.kodUrusan.jabatan = :kod_jab and h.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by h.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(query).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

        Iterator iter = q.iterate();
        map = new HashMap<String, Object>();

        listMap = new ArrayList<Map>();
        while (iter.hasNext()) {
            map = new HashMap<String, Object>();
            Object[] row = (Object[]) iter.next();
            int count = 0;
            for (Object object : row) {
                if (count == 0) {
                    map.put("idPermohonan", object.toString());
                }
                if (count == 1) {
                    map.put("urusan", object.toString());
                }
                if (count == 2) {
                    map.put("tarikh", object);
                }
                if (count == 3) {
                    map.put("status", object.toString());
                }
                if (count == 4) {
                    map.put("dimasuk", object.toString());
                }

                count++;
            }
            listMap.add(map);
        }
        return listMap;
    }

    public List<Map> findCarianByCawAndDateAll(String kod_caw, String kod_jab, String kod_serah, String d1, String d2) {
        String queryHM = "Select c.permohonanCarian.idCarian, c.permohonanCarian.urusan.nama, c.infoAudit.tarikhMasuk, c.infoAudit.dimasukOleh.nama, c.infoAudit.dimasukOleh.nama from etanah.model.CarianHakmilik c where c.cawangan = :kod_caw and c.kodUrusan.jabatan = :kod_jab and c.infoAudit.tarikhMasuk between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss') order by c.infoAudit.tarikhMasuk ASC";
        Query q = sessionProvider.get().createQuery(queryHM).setString("kod_caw", kod_caw).setString("kod_jab", kod_jab).setString("date1", d1).setString("date2", d2);

        Iterator iter = q.iterate();
        map = new HashMap<String, Object>();

        listMap = new ArrayList<Map>();
        while (iter.hasNext()) {
            map = new HashMap<String, Object>();
            Object[] row = (Object[]) iter.next();
            int count = 0;
            for (Object object : row) {
                if (count == 0) {
                    map.put("idPermohonan", object.toString());
                }
                if (count == 1) {
                    map.put("urusan", object.toString());
                }
                if (count == 2) {
                    map.put("tarikh", object);
                }
                if (count == 3) {
                    map.put("status", "-");
                }
                if (count == 4) {
                    map.put("dimasuk", object.toString());
                }
                count++;
            }
            listMap.add(map);
        }
        return listMap;
    }

    public List<Permohonan> getPermohonanByPermohonanSebelum(String idPermohonanSblm) {

        StringBuilder id = new StringBuilder("%")
                .append(idPermohonanSblm)
                .append("%");

        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.permohonanSebelum.idPermohonan LIKE :idPermohonan ");

        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idPermohonan", id.toString());

        return q.list();
    }

    public List<Permohonan> getPermohonanByNoResit(String noResit) {

        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();

        StringBuilder sb = new StringBuilder("Select t from etanah.model.Transaksi t ")
                .append("WHERE t.dokumenKewangan.idDokumenKewangan = :noResit ");

        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("noResit", noResit);

        List<Transaksi> senaraiTransaksi = q.list();
        for (Transaksi trans : senaraiTransaksi) {
            if (trans != null && trans.getPermohonan() != null) {
                senaraiPermohonan.add(trans.getPermohonan());
            }
        }

        return senaraiPermohonan;
    }

    public List<Permohonan> getPermohonanList(List<String> list, String kodJabatan,
            String kodSerah, boolean berangkai) {

        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.idPermohonan in (:list) ")
                .append("AND m.status not in ('SD') ");

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodJabatan)) {
            sb.append(" AND m.kodUrusan.jabatan.kod = :kodJabatan ");
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodSerah)) {
            sb.append(" AND m.kodUrusan.kodPerserahan.kod = :kodSerah ");
        }

        if (berangkai) {
            sb.append(" AND m.idKumpulan is not null ")
                    .append(" ORDER BY m.idKumpulan");
        }

        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameterList("list", list);

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodJabatan)) {
            q.setParameter("kodJabatan", kodJabatan);
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(kodSerah)) {
            q.setParameter("kodSerah", kodSerah);
        }

        return q.list();
    }

    public Permohonan getPermohonanByIdSptb(String idSptb) {

        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.noPerserahanSptb = :idSptb ");

        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idSptb", idSptb);

        if (q.list().isEmpty()) {
            return null;
        }
        if (q.list().size() > 1) {
            Permohonan permohonan = (Permohonan) q.list().get(0);
            return permohonan;
        } else {
            Permohonan permohonan = (Permohonan) q.uniqueResult();
            return permohonan;
        }
    }

    public Permohonan getPermohonanByIdSptb(String idSptb, String kodCawangan, String kodSerah) {

        StringBuilder sb = new StringBuilder("Select m from etanah.model.Permohonan m ")
                .append("WHERE m.noPerserahanSptb = :idSptb ")
                .append("AND m.cawangan.kod = :kodCaw ")
                .append("AND m.kodUrusan.kodPerserahan.kod = :kodSerah ");

        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idSptb", idSptb)
                .setParameter("kodCaw", kodCawangan)
                .setParameter("kodSerah", kodSerah);

        List<Permohonan> senarai = q.list();

        if (senarai.isEmpty()) {
            return null;
        } else {
            return senarai.get(0);
        }
//         if (senarai.size() > 1) {
//              Permohonan permohonan = senarai.get(0);
//              return permohonan;
//         } else {
//             Permohonan permohonan = (Permohonan)q.uniqueResult();
//             return permohonan;
//         }
    }
    
    //added for pindaan on 27/7/2017
    public Permohonan getMohonByIdAndKodDokumen(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("select distinct m from etanah.model.Permohonan m, etanah.model.FolderDokumen f, etanah.model.KandunganFolder fd, etanah.model.Dokumen d "
                + "where m.folderDokumen.folderId = f.folderId "
                + "and f.folderId = fd.folder.folderId "
                + "and fd.dokumen.idDokumen = d.idDokumen "
                + "and d.noRujukan is not null "
                + "and d.kodDokumen.kod = 'SKW' "
                + "AND m.idPermohonan = :idPermohonan");

        q.setParameter("idPermohonan", idPermohonan);
     

        List<Permohonan> list = q.list();
        if (list.size() > 0) {
            return (Permohonan) q.list().get(0);
        }
        return (Permohonan) q.uniqueResult();
    }
     public List<Permohonan> findRayuanByIdmohonSebelum(String idMohonSblm) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idMohonSblm);
        return q.list();
    }
     
     public List<PermohonanRayuan> findRayuanByIdmohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanRayuan p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
     
     public PermohonanRayuan findRayuan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanRayuan p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
       return (PermohonanRayuan) q.uniqueResult();
    }
     
      public FasaPermohonan findKeputusan(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.FasaPermohonan p where p.permohonan.idPermohonan = :idPermohonan and p.idAliran = :idAliran order by p.infoAudit.tarikhMasuk desc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        
        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
       //return (FasaPermohonan) q.uniqueResult();
    }
     
     @Transactional
    public PermohonanRayuan saveOrUpdate(PermohonanRayuan pr) {
        pr = permohonanRayuanDAO.saveOrUpdate(pr);
        return pr;
    }
    
     @Transactional
    public void deletePermohonanRayuan(PermohonanRayuan p) {
        permohonanRayuanDAO.delete(p);
    }
    
    public List<KodKadarBayaran> findByKodUrusan(String kodUrusan) {
        String q = "Select m from etanah.model.KodKadarBayaran m "
                + "where m.kodUrusan.kod = :kodUrusan "
                + "order by m.kod asc";
        Query query = sessionProvider.get().createQuery(q).setString("kodUrusan", kodUrusan);
        return query.list();
    }


}

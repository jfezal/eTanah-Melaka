/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Kehadiran;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakCawangan;
import etanah.model.PihakPengarah;
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
public class PihakService {

    private static final Logger logger = Logger.getLogger(PihakService.class);
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hpbDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Pihak saveOrUpdate(Pihak p) {
        return pihakDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdate(List<Pihak> li) {
        for (Pihak pihak : li) {
            pihakDAO.saveOrUpdate(pihak);
        }
    }

    @Transactional
    public void savePihakPengarah(List<PihakPengarah> li) {
        for (PihakPengarah pihakPengarah : li) {
            pihakPengarahDAO.saveOrUpdate(pihakPengarah);
        }
    }

    @Transactional
    public void saveOrUpdatePihak(Pihak p) {
        logger.debug("saving pihak in pihak service");
        pihakDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOrUpdatePihakKepentinganPihak(Pihak p, PermohonanPihak pp) {
        logger.debug("saving pihak n mohon pihak in pihak service");
        pihakDAO.saveOrUpdate(p);
        permohonanPihakDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdateHakmilikPihakKepentinganPihak(Pihak p, HakmilikPihakBerkepentingan hpb) {
        logger.debug("saving pihak n hakmilik pihak in pihak service");
        pihakDAO.saveOrUpdate(p);
        hpbDAO.saveOrUpdate(hpb);
        //hak.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdatePihakKepentinganKehadiran(Pihak p, PermohonanPihak pp, Kehadiran k) {
        logger.debug("saving pihak n mohon pihak n kehadiran in pihak service");
        pihakDAO.saveOrUpdate(p);
        permohonanPihakDAO.saveOrUpdate(pp);
        kehadiranDAO.saveOrUpdate(k);
    }

    @Transactional
    public void saveMultipleIdHakmilik(Pihak p, PermohonanPihak pp) {
        logger.debug("saving multiple MOHON_PIHAK in pihak service");
        pihakDAO.saveOrUpdate(p);

        Permohonan permohonan = pp.getPermohonan();

        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {

            PermohonanPihak perPihakTemp = new PermohonanPihak();
            perPihakTemp.setPermohonan(pp.getPermohonan());
            perPihakTemp.setCawangan(pp.getCawangan());
            perPihakTemp.setPihak(pp.getPihak());
            perPihakTemp.setPihakCawangan(pp.getPihakCawangan());
            perPihakTemp.setJenis(pp.getJenis());
            perPihakTemp.setHakmilik(hakmilikPermohonan.getHakmilik());
            perPihakTemp.setSyerPembilang(pp.getSyerPembilang());
            perPihakTemp.setSyerPenyebut(pp.getSyerPenyebut());
            perPihakTemp.setUmur(pp.getUmur());
            perPihakTemp.setPekerjaan(pp.getPekerjaan());
            perPihakTemp.setStatusKahwin(pp.getStatusKahwin());
            perPihakTemp.setPendapatan(pp.getPendapatan());
            perPihakTemp.setKaitan(pp.getKaitan());
            perPihakTemp.setTangungan(pp.getTangungan());
            perPihakTemp.setInfoAudit(pp.getInfoAudit());
            permohonanPihakDAO.saveOrUpdate(perPihakTemp);
        }
    }

    @Transactional
    public void saveMohonPihakMultipleHakmilik(Pihak p, PermohonanPihak pp) {
        logger.debug("saving multiple MOHON_PIHAK in pihak service");

        Permohonan permohonan = pp.getPermohonan();

        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {

            PermohonanPihak perPihakTemp = new PermohonanPihak();
            perPihakTemp.setPermohonan(pp.getPermohonan());
            perPihakTemp.setCawangan(pp.getCawangan());
            perPihakTemp.setPihak(pp.getPihak());
            perPihakTemp.setPihakCawangan(pp.getPihakCawangan());
            perPihakTemp.setJenis(pp.getJenis());
            perPihakTemp.setHakmilik(hakmilikPermohonan.getHakmilik());

            if (pp.getSyerPembilang() != null) {
                perPihakTemp.setSyerPembilang(pp.getSyerPembilang());
            }
            if (pp.getSyerPenyebut() != null) {
                perPihakTemp.setSyerPenyebut(pp.getSyerPenyebut());
            }

            perPihakTemp.setUmur(pp.getUmur());
            perPihakTemp.setPekerjaan(pp.getPekerjaan());
            perPihakTemp.setStatusKahwin(pp.getStatusKahwin());
            perPihakTemp.setPendapatan(pp.getPendapatan());
            perPihakTemp.setKaitan(pp.getKaitan());
            perPihakTemp.setTangungan(pp.getTangungan());
            perPihakTemp.setInfoAudit(pp.getInfoAudit());
            perPihakTemp.setNama(pp.getNama());
            perPihakTemp.setJenisPengenalan(pp.getJenisPengenalan());
            perPihakTemp.setNoPengenalan(pp.getNoPengenalan());
            perPihakTemp.setWargaNegara(pp.getWargaNegara());
            perPihakTemp.setAlamat(pp.getAlamat());
            perPihakTemp.setAlamatSurat(pp.getAlamatSurat());

            permohonanPihakDAO.saveOrUpdate(perPihakTemp);
        }
    }

    @Transactional
    public void updateMultipleIdHakmilik(Pihak p, PermohonanPihak pp) {
        logger.debug("saving multiple MOHON_PIHAK in pihak service");
        pihakDAO.saveOrUpdate(p);

        Permohonan permohonan = pp.getPermohonan();

        for (PermohonanPihak perPihakTemp : permohonan.getSenaraiPihak()) {

            if (perPihakTemp.getPihak().getIdPihak() == pp.getPihak().getIdPihak() && !perPihakTemp.getJenis().getKod().equals("WAR") && !perPihakTemp.getJenis().getKod().equals("TER")) {

                perPihakTemp.setPermohonan(pp.getPermohonan());
                perPihakTemp.setCawangan(pp.getCawangan());
                perPihakTemp.setPihak(pp.getPihak());
                perPihakTemp.setPihakCawangan(pp.getPihakCawangan());
                perPihakTemp.setJenis(pp.getJenis());
                perPihakTemp.setSyerPembilang(pp.getSyerPembilang());
                perPihakTemp.setSyerPenyebut(pp.getSyerPenyebut());
                perPihakTemp.setUmur(pp.getUmur());
                perPihakTemp.setPekerjaan(pp.getPekerjaan());
                perPihakTemp.setStatusKahwin(pp.getStatusKahwin());
                perPihakTemp.setPendapatan(pp.getPendapatan());
                perPihakTemp.setKaitan(pp.getKaitan());
                perPihakTemp.setTangungan(pp.getTangungan());
                perPihakTemp.setInfoAudit(pp.getInfoAudit());
                perPihakTemp.setNama(pp.getNama());
                perPihakTemp.setJenisPengenalan(pp.getJenisPengenalan());
                perPihakTemp.setNoPengenalan(pp.getNoPengenalan());
                perPihakTemp.setWargaNegara(pp.getWargaNegara());
                perPihakTemp.setAlamat(pp.getAlamat());
                perPihakTemp.setAlamatSurat(pp.getAlamatSurat());

                permohonanPihakDAO.saveOrUpdate(perPihakTemp);
            }
        }
    }

    @Transactional
    public void savePermohonanPihak(PermohonanPihak pPihak) {
        permohonanPihakDAO.save(pPihak);
    }

    @Transactional
    public void saveOrUpdatePermohonanPihak(PermohonanPihak pPihak) {
        permohonanPihakDAO.saveOrUpdate(pPihak);
    }

    @Transactional
    public void saveOrUpdatePihakCawangan(PihakCawangan pc) {
        pihakCawanganDAO.saveOrUpdate(pc);
    }

    public void saveOrUpdatePihakCawangan(List<PihakCawangan> senarai) {
        for (PihakCawangan pc : senarai) {
            saveOrUpdatePihakCawangan(pc);
        }
    }

    @Transactional
    public void saveOrUpdatePihakPengarah(PihakPengarah pp) {
        pihakPengarahDAO.saveOrUpdate(pp);
    }

    @Transactional
    public void saveOrUpdatePihakHubungan(PermohonanPihakHubungan ppHubungan) {
        permohonanPihakHubunganDAO.saveOrUpdate(ppHubungan);
    }

    @Transactional
    public void saveMultiplePihakHubungan(PermohonanPihakHubungan ppHubungan, Permohonan permohonan) {
        for (PermohonanPihak permohonanPihak : permohonan.getSenaraiPihak()) {
            ppHubungan.setPihak(permohonanPihak);
            permohonanPihakHubunganDAO.saveOrUpdate(ppHubungan);
        }
    }

    @Transactional
    public void deletePengarah(PihakPengarah pihakPengarah) {
        pihakPengarahDAO.delete(pihakPengarah);
    }

    @Transactional
    public void deletePihakCaw(PihakCawangan pihakCaw) {
        pihakCawanganDAO.delete(pihakCaw);
    }

    public PihakPengarah findPengarahById(String idPengarah) {
        return pihakPengarahDAO.findById(Long.valueOf(idPengarah));
    }

    public Pihak findById(String id) {
        return pihakDAO.findById(Long.parseLong(id));
    }

    public PihakCawangan findPihakCawangan(String id) {
        return pihakCawanganDAO.findById(Long.valueOf(id));
    }

    public List<Pihak> findAll(Map<String, String[]> param) {

        String query = "SELECT p FROM etanah.model.Pihak p WHERE p.idPihak = p.idPihak ";

        if (isNotBlank(param.get("pihak.nama"))) {
            query += "AND upper(p.nama) LIKE :nama ";
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            query += "AND p.jenisPengenalan.kod = :kod ";
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            query += "AND p.noPengenalan LIKE :noKp ";
        }

        Query q = sessionProvider.get().createQuery(query);
        //to find pihak containing pattern regardless of case
        if (isNotBlank(param.get("pihak.nama"))) {
            q.setString("nama", "%" + param.get("pihak.nama")[0].toUpperCase() + "%");
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            q.setString("kod", param.get("pihak.jenisPengenalan.kod")[0]);
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            q.setString("noKp", param.get("pihak.noPengenalan")[0]);
        }

        return q.list();
    }

    public List<Pihak> findAllByDimasuk(Map<String, String[]> param, String dimasuk) {

        String query = "SELECT p FROM etanah.model.Pihak p WHERE p.idPihak = p.idPihak AND p.infoAudit.dimasukOleh = :dimasuk ";

        if (isNotBlank(param.get("pihak.nama"))) {
            query += "AND upper(p.nama) LIKE :nama ";
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            query += "AND p.jenisPengenalan.kod = :kod ";
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            query += "AND p.noPengenalan LIKE :noKp ";
        }

        Query q = sessionProvider.get().createQuery(query);
        //to find pihak containing pattern regardless of case
        if (isNotBlank(param.get("pihak.nama"))) {
            q.setString("nama", "%" + param.get("pihak.nama")[0].toUpperCase() + "%");
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            q.setString("kod", param.get("pihak.jenisPengenalan.kod")[0]);
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            q.setString("noKp", param.get("pihak.noPengenalan")[0]);
        }

        if (!isBlank(dimasuk)) {
            q.setString("dimasuk", dimasuk);
        }

        return q.list();
    }

    public List<Pihak> findAllByFlagPihak(Map<String, String[]> param) {

        String query = "SELECT p FROM etanah.model.Pihak p WHERE p.idPihak = p.idPihak AND p.kodFlagPihak.kod not in ('P', 'TK')";

        if (isNotBlank(param.get("pihak.nama"))) {
            query += "AND upper(p.nama) LIKE :nama ";
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            query += "AND p.jenisPengenalan.kod = :kod ";
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            query += "AND p.noPengenalan LIKE :noKp ";
        }

        Query q = sessionProvider.get().createQuery(query);
        //to find pihak containing pattern regardless of case
        if (isNotBlank(param.get("pihak.nama"))) {
            q.setString("nama", "%" + param.get("pihak.nama")[0].toUpperCase() + "%");
        }

        if (isNotBlank(param.get("pihak.jenisPengenalan.kod"))) {
            q.setString("kod", param.get("pihak.jenisPengenalan.kod")[0]);
        }

        if (isNotBlank(param.get("pihak.noPengenalan"))) {
            q.setString("noKp", param.get("pihak.noPengenalan")[0]);
        }

        return q.list();
    }

    public Pihak findPihak(String kodPengenalan, String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.jenisPengenalan.kod = :kod and p.noPengenalan = :no";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("kod", kodPengenalan).setString("no", noPengenalan).list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public Pihak findPihakByDimasuk(String kodPengenalan, String noPengenalan, String dimasuk) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.jenisPengenalan.kod = :kod and p.noPengenalan = :no and p.infoAudit.dimasukOleh = :dimasuk";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("kod", kodPengenalan).setString("no", noPengenalan).setString("dimasuk", dimasuk).list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public Pihak findPihakByFlagPihak(String kodPengenalan, String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.jenisPengenalan.kod = :kod and p.noPengenalan = :no and p.kodFlagPihak.kod not in ('P', 'TK')";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("kod", kodPengenalan).setString("no", noPengenalan).list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public List<Pihak> findPihakByName(String namaPihak, String kodPengenalan) {
        try {
            String query = "Select p from etanah.model.Pihak p where upper(p.nama) LIKE :nama";
            if (StringUtils.isNotBlank(kodPengenalan)) {
                query = query + " and p.jenisPengenalan.kod = :kod";
            }
            Session s = sessionProvider.get();
            Query q = s.createQuery(query);
            q.setString("nama", "%" + namaPihak.toUpperCase() + "%");
            if (StringUtils.isNotBlank(kodPengenalan)) {
                q.setString("kod", kodPengenalan);
            }
            return q.list();
        } finally {
        }
    }

    public Pihak findPihakByNameEqual(String namaPihak) {

        String query = "Select p FROM etanah.model.Pihak p WHERE p.nama = :nama ";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("nama", "%" + namaPihak + "%").list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public Pihak findPihakByNameKp(String kodPengenalan, String namaPihak) {

        String query = "Select p FROM etanah.model.Pihak p WHERE p.jenisPengenalan.kod = :kod and p.nama = :nama ";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("kod", kodPengenalan).setString("nama", "%" + namaPihak + "%").list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public HakmilikPihakBerkepentingan getMaklumatByidHMidPhk(Hakmilik h, long idPhk) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik = :idHM AND b.pihak.idPihak = :idPhk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idHM", h);
        q.setLong("idPhk", idPhk);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();

    }

    public List<Pihak> findPihakByKodKpAndName(String kodPengenalan, String namaPihak) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak p where p.jenisPengenalan.kod = :kod and p.nama LIKE :nama");
            q.setString("kod", kodPengenalan).setString("nama", "%" + namaPihak + "%");
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakByKodKpAndNameAndDimasuk(String kodPengenalan, String namaPihak, String dimasuk) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak p where p.jenisPengenalan.kod = :kod and p.nama LIKE :nama and  p.infoAudit.dimasukOleh = :dimasuk");
            q.setString("kod", kodPengenalan).setString("nama", "%" + namaPihak + "%").setString("dimasuk", dimasuk);
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakByKodKpAndNameAndFlagPihak(String kodPengenalan, String namaPihak) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak p where p.jenisPengenalan.kod = :kod and p.nama LIKE :nama and p.kodFlagPihak.kod not in ('P', 'TK')");
            q.setString("kod", kodPengenalan).setString("nama", "%" + namaPihak + "%");
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakByNama(String namaPihak) {
        try {
//            if(namaPihak != null && namaPihak.length()==1){
//                logger.info("--namaPihak-if-");
//                // temperoryly added for PAT by murali
//                if(namaPihak.equals("D")||namaPihak.equals("H")||namaPihak.equals("N")||namaPihak.equals("0")||namaPihak.equals("P")){
//                    namaPihak="E"+namaPihak;
//                }else{
//                    namaPihak=namaPihak + " " ;
//                }
//            }else if(namaPihak != null && namaPihak.length()>1){
//                namaPihak=namaPihak + " " ;
//            }
            logger.info("--namaPihak--" + namaPihak);
            Session s = sessionProvider.get();
            Query q = s.createQuery("select p from etanah.model.Pihak p where p.nama LIKE :nama");
            logger.info("--query--" + q);
            q.setString("nama", "%" + namaPihak + "%");
            logger.info("--query---- data----:" + q.toString());
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakByNamaByDimasuk(String namaPihak, String dimasuk) {
        try {

            logger.info("--namaPihak--" + namaPihak);
            Session s = sessionProvider.get();
            Query q = s.createQuery("select p from etanah.model.Pihak p where p.nama LIKE :nama and p.infoAudit.dimasukOleh = :dimasuk");
            logger.info("--query--" + q);
            q.setString("nama", "%" + namaPihak + "%").setString("dimasuk", dimasuk);
            logger.info("--query---- data----:" + q.toString());
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakByNamaByFlagPihak(String namaPihak) {
        try {

            logger.info("--namaPihak--" + namaPihak);
            Session s = sessionProvider.get();
            Query q = s.createQuery("select p from etanah.model.Pihak p where p.nama LIKE :nama and p.kodFlagPihak.kod not in ('P', 'TK')");
            logger.info("--query--" + q);
            q.setString("nama", "%" + namaPihak + "%");
            logger.info("--query---- data----:" + q.toString());
            return q.list();
        } finally {
        }
    }

    public List<Pihak> findPihakBynoKPkodKP(String kodPengenalan, String noPengenalan) {
        try {
            logger.info("No Pengenalan" + noPengenalan);
            logger.info("Kod Pengenalan" + kodPengenalan);
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pihak p where p.noPengenalan LIKE :noPengenalan AND p.jenisPengenalan.kod LIKE :kodPengenalan");
            q.setString("noPengenalan", noPengenalan);
            q.setString("kodPengenalan", kodPengenalan);
            return q.list();
        } finally {
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

// ravi changes
    public List<PihakPengarah> findPengarahByIDPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.PihakPengarah b where b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    @Transactional
    public void simpanPemohonHbgn(PihakPengarah pihakPengarah, Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        pihakPengarahDAO.saveOrUpdate(pihakPengarah);
    }

    @Transactional
    public void saveOrUpdate(PihakPengarah pihakPengarah, Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        pihakPengarahDAO.saveOrUpdate(pihakPengarah);
    }

    @Transactional
    public void deletePihakPengarah(PihakPengarah pihakPengarah) {
        pihakPengarahDAO.delete(pihakPengarah);
    }

    //Added by Aizuddin to filter SyarikatSM aktif TODO
    public Pihak findSyarikatMCLAktifByIdPihak(Long idPihak) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.Pihak p ")
                .append("WHERE p.idPihak = :idPihak ")
                .append("AND p.kodFlagPihak = 'AK'");
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idPihak", idPihak);
        return (Pihak) q.uniqueResult();
    }

    public PihakAlamatTamb getAlamatTambahan(Pihak pihak) {
        StringBuilder sb = new StringBuilder("Select p from etanah.model.PihakAlamatTamb p ")
                .append("WHERE p.pihak.idPihak = :idPihak ");
        Query q = sessionProvider.get()
                .createQuery(sb.toString())
                .setParameter("idPihak", pihak.getIdPihak());
        return (q.list().isEmpty() ? null : (PihakAlamatTamb) q.list().get(0));
    }
    
    
    public Pihak findPihakByNamaPengenalanKodPengenalan(String nama, String noPengenalan, String kodPengenalan) {
        
        StringBuilder sb = new StringBuilder("SELECT P FROM etanah.model.Pihak P WHERE ")
                .append("P.nama = :nama ")
                .append("AND P.noPengenalan = :noPengenalan ")
                .append("AND P.jenisPengenalan.kod = :kodPengenalan");
        Query q = sessionProvider.get().createQuery(sb.toString())
                .setParameter("nama", nama)
                .setParameter("noPengenalan", noPengenalan)
                .setParameter("kodPengenalan", kodPengenalan);
        
        return (q.list().isEmpty() ? null : (Pihak) q.list().get(0));
        
    }
    
}

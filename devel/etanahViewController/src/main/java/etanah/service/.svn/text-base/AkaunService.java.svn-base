/**
 * AkaunService class.
 * Currently available service(s):
 *  - Find Akaun record by multiple fields/criterias
 *  - Save or Update Akaun record
 *  - Find Akaun record by Kod Cawangan
 *
 * @author ahmad.hisham
 * @author Mohd Hairudin Mansur
 * @version 1.0 4 Dec 2009
 */
package etanah.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.AkaunDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.PermohonanAnsuranDAO;
import etanah.model.Akaun;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.PermohonanAnsuran;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class AkaunService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    protected AkaunDAO akaunDAO;
    @Inject
    PermohonanAnsuranDAO permohonanAnsuranDAO;

    protected KodAkaunDAO kodAkaunDAO;

    private static final Logger LOG = Logger.getLogger(AkaunService.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    public Akaun getAkaunForCawangan(String kodAkaun, KodCawangan cawangan) {
        Session s = sessionProvider.get();
        return (Akaun) s.createQuery("select a from Akaun a where a.kodAkaun.id = :kodAkaun and "
                + "a.cawangan.id = :kodCawangan")
                .setString("kodAkaun", kodAkaun)
                .setString("kodCawangan", cawangan.getKod()).uniqueResult();

    }

    /**
     * Retrieve Akaun Cukai for the given ID Hakmilik
     *
     * @param kodCaw
     * @param idHakmilik
     * @return Akaun
     * @author Mohd Hairudin Mansur
     * @version 1.0 29 Dec 2009
     */
    public Akaun getAkaunCukaiForHakmilik(String kodCaw, String idHakmilik) {
        String kodAkaunCukai = "AC";
        String queryStr = "SELECT a FROM Akaun a WHERE a.cawangan.id = :kodCawangan AND a.kodAkaun.id = :kodAkaun AND "
                + "a.hakmilik.id = :idHakmilik ";
        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("kodCawangan", kodCaw);
        query.setString("kodAkaun", kodAkaunCukai);
        query.setString("idHakmilik", idHakmilik);

        return (Akaun) query.uniqueResult();
    }

    public Akaun getAkaunCukaiActiveForHakmilik(String kodCaw, String idHakmilik) {
        String kodAkaunCukai = "AC";
        String queryStr = "SELECT a FROM Akaun a WHERE a.cawangan.id = :kodCawangan AND a.kodAkaun.id = :kodAkaun AND "
                + "a.hakmilik.id = :idHakmilik and a.status.kod = 'A'";
        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("kodCawangan", kodCaw);
        query.setString("kodAkaun", kodAkaunCukai);
        query.setString("idHakmilik", idHakmilik);

        return (Akaun) query.uniqueResult();
    }

    public Akaun getAkaunCukaiActiveForStrataHakmilik(String kodCaw, String idHakmilik) {
        String kodAkaunCukai = "AC";
        String queryStr = "SELECT a FROM Akaun a WHERE a.kodAkaun.id = :kodAkaun AND "
                + "a.hakmilik.id = :idHakmilik ";
        Query query = sessionProvider.get().createQuery(queryStr);
        //query.setString("kodCawangan", kodCaw);
        query.setString("kodAkaun", kodAkaunCukai);
        query.setString("idHakmilik", idHakmilik);
        LOG.info("hakmilik" + idHakmilik);

        return (Akaun) query.uniqueResult();
    }

    /**
     * Find akaun by ID(No. akaun)
     *
     * @param noAkaun
     * @return Akaun
     * @author Mohd Hairudin Mansur
     * @version 1.0 4 Dec 2009
     */
    public Akaun findById(String noAkaun) {

        return akaunDAO.findById(noAkaun);
    }

    /**
     * Find Akaun Amanah by multiple criteria(s)
     *
     * @param param
     * @return List<Akaun>
     * @author Mohd Hairudin Mansur
     * @version 1.0 9 Dec 2009
     */
    public List<Akaun> findAll(String kodCaw, String kodAkaun, String noAkaun,
            String idHakmilik, String noLot, String kodBPM, String noFail, String idPermohonan, String jenisPengenalan,
            String noPengenalan, String pemegang) {

        String queryStr = "SELECT a FROM Akaun a WHERE a.cawangan.id = :cawangan "
                + "AND a.kodAkaun.id = :kodAkaun ";
        String pengenalanTidakBerkenaan = "0";

        if (!isBlank(noAkaun)) {
            queryStr += "AND a.noAkaun LIKE :noAkaun ";
        }

        if (!isBlank(idHakmilik)) {
            queryStr += "AND a.hakmilik.id LIKE :idHakmilik ";
        }

        if (!isBlank(noLot)) {
            queryStr += "AND a.hakmilik.noLot LIKE :lot ";
        }

        if (!isBlank(kodBPM)) {
            queryStr += "AND a.hakmilik.bandarPekanMukim.kod = :bpm ";
        }

        if (!isBlank(noFail)) {
            queryStr += "AND a.catatan LIKE :fail ";
        }

        if (!isBlank(idPermohonan)) {
            queryStr += "AND a.permohonan.id LIKE :idPermohonan ";
        }

        if (!isBlank(pemegang)) {
            queryStr += "AND a.pemegang.nama LIKE :pemegang ";
        }

        if (!jenisPengenalan.equals(pengenalanTidakBerkenaan) && !isBlank(noPengenalan)) {
            queryStr += "AND a.pemegang.jenisPengenalan.id = :jenisPengenalan AND a.pemegang.noPengenalan = :noPengenalan ";
        }

        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("cawangan", kodCaw);
        query.setString("kodAkaun", kodAkaun);

        if (!isBlank(noAkaun)) {
            query.setString("noAkaun", noAkaun + "%");
        }

        if (!isBlank(idHakmilik)) {
            query.setString("idHakmilik", idHakmilik + "%");
        }

        if (!isBlank(noLot)) {
            query.setString("lot", "%" + noLot + "%");
        }

        if (!isBlank(kodBPM)) {
            query.setInteger("bpm", Integer.parseInt(kodBPM));
        }

        if (!isBlank(noFail)) {
            query.setString("fail", "%" + noFail + "%");
        }

        if (!isBlank(idPermohonan)) {
            query.setString("idPermohonan", idPermohonan + "%");
        }

        if (!isBlank(pemegang)) {
            query.setString("pemegang", "%" + pemegang + "%");
        }

        if (!jenisPengenalan.equals(pengenalanTidakBerkenaan) && !isBlank(noPengenalan)) {
            query.setString("jenisPengenalan", jenisPengenalan);
            query.setString("noPengenalan", noPengenalan);
        }

        return query.list();
    }

    /**
     * Find Akaun Deposit Tidak Tertuntut
     *
     * @param kodCaw
     * @param noAkaunDeposit
     * @param tarikhDari
     * @param tarikhHingga
     * @return List<Akaun>
     * @author Mohd Hairudin Mansur
     * @version 1.0 28 Dec 2009
     */
    public List<Akaun> findAkaunDepositTidakDituntut(String kodCaw, String noAkaunDeposit, String tarikhDari, String tarikhHingga) {
        String queryStr = "SELECT a FROM Akaun a WHERE a.cawangan.id = :cawangan AND a.kodAkaun.id = :kodAkaunDeposit "
                + "AND a.baki > 0 AND MONTHS_BETWEEN(SYSDATE, a.tarikhMatang) > :tempohAkaunMatang ";
        String kodAkaunDeposit = "AD";
        String tempohAkaunMatang = "3";

        if (!isBlank(noAkaunDeposit)) {
            queryStr += "AND a.noAkaun = :noAkaunDeposit ";
        }

        if (!isBlank(tarikhDari) && !isBlank(tarikhHingga)) {
            queryStr += "AND a.infoAudit.tarikhMasuk >= :tarikhDari AND a.infoAudit.tarikhMasuk <= :tarikhHingga ";
        }

        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("cawangan", kodCaw);
        query.setString("kodAkaunDeposit", kodAkaunDeposit);
        query.setString("tempohAkaunMatang", tempohAkaunMatang);

        if (!isBlank(noAkaunDeposit)) {
            query.setString("noAkaunDeposit", noAkaunDeposit);
        }

        if (!isBlank(tarikhDari) && !isBlank(tarikhHingga)) {
            query.setString("tarikhDari", tarikhDari);
            query.setString("tarikhHingga", tarikhHingga);
        }

        return query.list();
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

    /**
     * Service method to save Akaun record into database
     *
     * @param akaun
     * @author Mohd Hairudin Mansur
     * @version 1.0 16 Dec 2009
     */
    @Transactional
    public void saveOrUpdate(Akaun akaun) {
        akaunDAO.saveOrUpdate(akaun);
    }

    @Transactional
    public void savePermohonanAnsuran(PermohonanAnsuran permohonanAnsuran) {
        permohonanAnsuranDAO.saveOrUpdate(permohonanAnsuran);
    }

    public DokumenKewangan findResitByIdkewDokAndAkaun(String teks1, String noAkaun) {

        String queryStr = "SELECT a FROM DokumenKewangan a WHERE a.idDokumenKewangan = :idDokumenKewangan AND "
                + "a.akaun.noAkaun = :noAkaun and a.status.kod = 'A'";
        Query query = sessionProvider.get().createQuery(queryStr);
        query.setString("idDokumenKewangan", teks1);
        query.setString("noAkaun", noAkaun);
        return (DokumenKewangan) query.uniqueResult();
    }

    public PermohonanAnsuran setAnsuran(String teks1, String nilai1, Hakmilik hm, PermohonanAnsuran ansuran) {
        DokumenKewangan dok = findResitByIdkewDokAndAkaun(teks1, hm.getAkaunCukai().getNoAkaun());
        BigDecimal amaunsblm = hm.getAkaunCukai().getBaki().add(dok.getAmaunBayaran());
        ansuran.setIdResitDepo(dok.getIdDokumenKewangan());
        if (nilai1.equals("Syarikat")) {
            int i = amaunsblm.compareTo(BigDecimal.valueOf(20000));
            if (i == 0 || i == 1) {
                ansuran.setKatgLulus("PTG");
            } else {
                ansuran.setKatgLulus("PTD");
            }
        } else {
            int i = amaunsblm.compareTo(BigDecimal.valueOf(5000));
            if (i == 0 || i == 1) {
                ansuran.setKatgLulus("PTG");
            } else {
                ansuran.setKatgLulus("PTD");
            }
        }

        ansuran.setJenisPemohon(nilai1);
        ansuran.setAmaunDepo(dok.getAmaunBayaran());
        ansuran.setBaki(hm.getAkaunCukai().getBaki());
        return ansuran;
    }

}

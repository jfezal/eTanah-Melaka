/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.jompay;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.AkaunStrataDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.JomPayBillerCodeDAO;
import etanah.dao.JomPayFailDetailsDAO;
import etanah.dao.JomPayFailMuatnaikDAO;
import etanah.dao.JomPayFailedRecordsDAO;
import etanah.dao.KodAgensiKutipanCawanganDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Akaun;
import etanah.model.AkaunStrata;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.jompay.JomPayBillerCode;
import etanah.model.jompay.JomPayFailDetails;
import etanah.model.jompay.JomPayFailMuatnaik;
import etanah.model.jompay.JomPayFailedRecords;
import etanah.view.portal.ws.BayarValue;
import etanah.view.portal.ws.TerimaBayaranServices;
import etanah.view.stripes.jomPay.SenaraiTransaksiForm;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
public class JomPayServices {

    @Inject
    JomPayFailMuatnaikDAO jomPayFailMuatnaikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    JomPayFailDetailsDAO jomPayFailDetailsDAO;
    @Inject
    JomPayFailedRecordsDAO jomPayFailedRecordDAO;
    @Inject
    TerimaBayaranServices terimaBayaranServices;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    KodAgensiKutipanCawanganDAO kodAgensiKutipanCawanganDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    AkaunStrataDAO akaunStrataDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    JomPayBillerCodeDAO jomPayBillerCodeDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public BayarValue terimaCukaiJomPay(String noAkaun, String vaNo, BigDecimal amaun, String noResit, String noRuj, Date trhTransaksi, String namaPembayar, Pengguna p) {
        String kodCaw = "";
        Akaun akaunKutip = null;
        KodAgensiKutipanCawangan agensiKutipan = null;
        KodCaraBayaran kodCaraBayaran = null;
        InfoAudit ia = new InfoAudit();
        Pengguna pengguna = new Pengguna();
        JomPayBillerCode bc = jomPayBillerCodeDAO.findById(vaNo);
        if (bc != null) {
            kodCaw = bc.getKodCaw();
            if ("00".equals(kodCaw)) {
            } else if ("01".equals(kodCaw)) {
                kodCaw = "01";
                pengguna = penggunaDAO.findById("jompay_mt");
                akaunKutip = akaunDAO.findById("MTJOMPY");
                agensiKutipan = kodAgensiKutipanCawanganDAO.findById("100");
                kodCaraBayaran = kodCaraBayaranDAO.findById("E");

            } else if ("02".equals(kodCaw)) {
                kodCaw = "02";
                pengguna = penggunaDAO.findById("jompay_jsn");
                akaunKutip = akaunDAO.findById("JSJOMPY");
                agensiKutipan = kodAgensiKutipanCawanganDAO.findById("100");
                kodCaraBayaran = kodCaraBayaranDAO.findById("E");
            } else if ("03".equals(kodCaw)) {
                kodCaw = "03";
                pengguna = penggunaDAO.findById("jompay_ag");
                akaunKutip = akaunDAO.findById("AGJOMPY");
                agensiKutipan = kodAgensiKutipanCawanganDAO.findById("100");
                kodCaraBayaran = kodCaraBayaranDAO.findById("E");
            }
            if (pengguna != null) {
                ia.setDimasukOleh(pengguna);
            } else {
                ia.setDimasukOleh(p);
            }

            ia.setTarikhMasuk(new Date());
        }

        return terimaBayaranServices.terimaBayaranCukaiAgensi(noAkaun, kodCaw, amaun, noResit, noRuj, "cukai", trhTransaksi, akaunKutip, agensiKutipan, ia, kodCaraBayaran, namaPembayar);
    }

    @Transactional
    public JomPayFailMuatnaik simpanFail(JomPayFailMuatnaik file) {
        return jomPayFailMuatnaikDAO.saveOrUpdate(file);
    }

    @Transactional
    public void simpanJomPayFailDetails(JomPayFailDetails file) {
        jomPayFailDetailsDAO.saveOrUpdate(file);
    }

    public void extractToStore(JomPayFailMuatnaik jomPayFailMuatnaik, InputStream inputStream, InfoAudit ia) throws ParseException {
        Scanner sc = new Scanner(inputStream);

        SimpleDateFormat originalFormat = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");

        //print text file with delimeters
        int i = 1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (i != 1) {
                String[] strArray = line.split("\\,");
                if (strArray[19].equals("CR")) {
                    JomPayFailDetails jomPayFailDetails = new JomPayFailDetails();
//                    PAYMENT REFERENCE 16 PAYMENT DETAILS 17 CR_DR 19 TRXN AMOUNT 20 TRXN DATE 2

                    jomPayFailDetails.setAkaunNo(strArray[0].trim());
                    jomPayFailDetails.setJenisBayaran(strArray[19].trim());
                    jomPayFailDetails.setJomPayFailMuatnaik(jomPayFailMuatnaik);
                    jomPayFailDetails.setRujukanBayaran(strArray[16].trim());
                    jomPayFailDetails.setRujukanDetails(strArray[17].trim());
//                    jomPayFailDetails.setStatus(line);
                    Date tarikhTrans = originalFormat.parse(strArray[2].trim());
                    String targetDate = targetFormat.format(tarikhTrans);

                    //Date tarikhTrans = sdf.parse(strArray[2].trim());
                    jomPayFailDetails.setTarikhTrans(targetFormat.parse(targetDate));
                    jomPayFailDetails.setNoTrans(strArray[6].trim());
                    jomPayFailDetails.setNamaPembayar(strArray[15].trim());
                    jomPayFailDetails.setTransAmaun(new BigDecimal(strArray[20].trim()));
                    jomPayFailDetails.setStatus("T");
                    jomPayFailDetails.setInfoAudit(ia);
                    simpanJomPayFailDetails(jomPayFailDetails);

                }
            }
            i++;
        }
    }

    public Akaun findTranslateNoAkaun(JomPayFailDetails d, String rujukanBayaran, Pengguna p) {
        Akaun a = null;
        AkaunStrata as = akaunStrataDAO.findById(rujukanBayaran);
        Hakmilik hm = hakmilikDAO.findById(rujukanBayaran);
        if (as != null) {
            a = as.getHakmilik().getAkaunCukai();
        } else {
            if (hm != null) {
                a = hm.getAkaunCukai();
            } else {
                a = null;
            }
            //a = findValidAkaun(rujukanBayaran);
            if (a == null) {
                //store to table to record 

                InfoAudit ia = d.getInfoAudit();
                d.setStatus("F");
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                d.setInfoAudit(ia);
                simpanJomPayFailDetails(d);
                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                JomPayFailedRecords jomPayFailedRecord = new JomPayFailedRecords();
                jomPayFailedRecord.setJomPayFailDetails(d);
                jomPayFailedRecord.setStatus("F");
                InfoAudit ia2 = new InfoAudit();
                ia2.setDimasukOleh(p);
                ia2.setTarikhMasuk(new Date());
                jomPayFailedRecord.setInfoAudit(ia2);
                saveJomPayFailedRecord(jomPayFailedRecord);
                tx.commit();
            }
        }

        return a;
    }

    public List<JomPayFailDetails> findListFailDetailByStatus(String idPengguna) {
        String query = "SELECT b FROM etanah.model.jompay.JomPayFailDetails b, etanah.model.jompay.JomPayBillerCode c, "
                + "etanah.model.Pengguna p where LTRIM(b.akaunNo,'0') = c.VaNo and c.kodCaw = p.kodCawangan.kod and b.status = 'T' "
                + "and p.idPengguna = :idPengguna ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public List<JomPayFailMuatnaik> findListFailUpload(String pgunaCaw) {
        String query = "SELECT c FROM etanah.model.jompay.JomPayFailMuatnaik c, etanah.model.Pengguna p "
                + "where c.idJomPayUpload = (select distinct(b.jomPayFailMuatnaik.idJomPayUpload) from etanah.model.jompay.JomPayFailDetails b where b.jomPayFailMuatnaik.idJomPayUpload = c.idJomPayUpload "
                + "and b.status = 'T') "
                + "and c.infoAudit.dimasukOleh = p.idPengguna "
                + "and p.kodCawangan.kod = :pgunaCaw order by c.tarikhMuatTurun";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("pgunaCaw", pgunaCaw);
        return q.list();
    }

    public List<JomPayFailMuatnaik> findListFailSuccess(String pgunaCaw) {
        String query = "SELECT c FROM etanah.model.jompay.JomPayFailMuatnaik c, etanah.model.Pengguna p "
                + "where c.idJomPayUpload = (select distinct(b.jomPayFailMuatnaik.idJomPayUpload) from etanah.model.jompay.JomPayFailDetails b where b.jomPayFailMuatnaik.idJomPayUpload = c.idJomPayUpload "
                + "and b.status = 'Y') "
                + "and c.infoAudit.dimasukOleh = p.idPengguna "
                + "and p.kodCawangan.kod = :pgunaCaw ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("pgunaCaw", pgunaCaw);
        return q.list();
    }

    public List<JomPayFailDetails> findListFailDetailByIdUploadFile(Long idFile) {
        String query = "SELECT b FROM etanah.model.jompay.JomPayFailDetails b, etanah.model.jompay.JomPayFailMuatnaik c "
                + "where b.jomPayFailMuatnaik.idJomPayUpload = c.idJomPayUpload and b.status = 'T' "
                + "and c.idJomPayUpload = :idFile ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFile", idFile);
        return q.list();
    }

    public List<JomPayFailDetails> findListFailDetailBySuccessFile(Long idFile) {
        String query = "SELECT b FROM etanah.model.jompay.JomPayFailDetails b, etanah.model.jompay.JomPayFailMuatnaik c "
                + "where b.jomPayFailMuatnaik.idJomPayUpload = c.idJomPayUpload and b.status = 'Y' "
                + "and c.idJomPayUpload = :idFile ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFile", idFile);
        return q.list();
    }

    @Transactional
    public void saveJomPayFailedRecord(JomPayFailedRecords jomPayFailedRecord) {
        jomPayFailedRecordDAO.saveOrUpdate(jomPayFailedRecord);
    }

    public List<SenaraiTransaksiForm> senaraiTransaksi(String kod, Long idJomPayUpload) {
        List<SenaraiTransaksiForm> s = new ArrayList<SenaraiTransaksiForm>();
        String query = "SELECT c FROM etanah.model.jompay.JomPayFailMuatnaik c, etanah.model.Pengguna p "
                + "where c.idJomPayUpload = :idJomPayUpload "
                + "and c.infoAudit.dimasukOleh = p.idPengguna "
                + "and p.kodCawangan.kod = :pgunaCaw ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("pgunaCaw", kod);
        q.setLong("idJomPayUpload", idJomPayUpload);

        for (int i = 0; i < q.list().size(); i++) {
            JomPayFailMuatnaik j = (JomPayFailMuatnaik) q.list().get(i);
            SenaraiTransaksiForm ss = new SenaraiTransaksiForm();
            ss.setJ(j);
            ss.setTarikhMuatNaik(sdf.format(j.getInfoAudit().getTarikhMasuk()));
            ss.setOk(countSuccessTransaction(j.getIdJomPayUpload()));
            ss.setXok(countFailedTransaction(j.getIdJomPayUpload()));
            s.add(ss);
        }
        return s;
    }

    public List<SenaraiTransaksiForm> senaraiTransaksi(String kod) {
        List<SenaraiTransaksiForm> s = new ArrayList<SenaraiTransaksiForm>();
        String query = "SELECT c FROM etanah.model.jompay.JomPayFailMuatnaik c, etanah.model.Pengguna p "
                + "where c.infoAudit.dimasukOleh = p.idPengguna "
                + "and p.kodCawangan.kod = :pgunaCaw ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("pgunaCaw", kod);

        for (int i = 0; i < q.list().size(); i++) {
            JomPayFailMuatnaik j = (JomPayFailMuatnaik) q.list().get(i);
            SenaraiTransaksiForm ss = new SenaraiTransaksiForm();
            ss.setJ(j);
            ss.setTarikhMuatNaik(sdf.format(j.getInfoAudit().getTarikhMasuk()));
            ss.setOk(countSuccessTransaction(j.getIdJomPayUpload()));
            ss.setXok(countFailedTransaction(j.getIdJomPayUpload()));
            s.add(ss);
        }
        return s;
    }

    public List<JomPayFailedRecords> findFailedjomPayFailDetails(Long idJomPayUpload) {
        String query = "SELECT b FROM etanah.model.jompay.JomPayFailedRecords b, etanah.model.jompay.JomPayFailDetails c,etanah.model.jompay.JomPayFailMuatnaik d  "
                + "where b.jomPayFailDetails.idJomPayDetails = c.idJomPayDetails and c.jomPayFailMuatnaik.idJomPayUpload = d.idJomPayUpload and d.idJomPayUpload= :idJomPayUpload and b.status = 'F'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idJomPayUpload", idJomPayUpload);
        return q.list();
    }

    public Long countFailedTransaction(Long idJomPayUpload) {
        String query = "Select count(b) FROM etanah.model.jompay.JomPayFailedRecords b, etanah.model.jompay.JomPayFailDetails c "
                + "where b.jomPayFailDetails.idJomPayDetails = c.idJomPayDetails and c.jomPayFailMuatnaik.idJomPayUpload =:idJomPayUpload and b.status = 'F'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idJomPayUpload", idJomPayUpload);
        return (Long) q.uniqueResult();
    }

    public Long countSuccessTransaction(Long idJomPayUpload) {
        String query = "Select count(b) FROM etanah.model.jompay.JomPayFailDetails b "
                + " where  b.jomPayFailMuatnaik.idJomPayUpload =:idJomPayUpload and b.status = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idJomPayUpload", idJomPayUpload);
        return (Long) q.uniqueResult();
    }

    @Transactional
    public void deleteFailDetails(JomPayFailDetails p) {
        jomPayFailDetailsDAO.delete(p);
    }
    
    @Transactional
    public void deleteFailUpload(JomPayFailMuatnaik p) {
        jomPayFailMuatnaikDAO.delete(p);
    }
}

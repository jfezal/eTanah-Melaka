
package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
public class KutipanHasilService {

    private static final Logger logger = Logger.getLogger(KutipanHasilService.class);
    private static boolean debug = logger.isDebugEnabled();
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KutipanHasilManager manager;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.kodHasilConfig hasil;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;

    public DokumenKewangan saveIdKewDokumen(DokumenKewangan dokumenKewangan, String idKewDok,
            BigDecimal ac, InfoAudit ia, KodCawangan caw) {

        DokumenKewangan dk = new DokumenKewangan();
        dk.setIdDokumenKewangan(idKewDok);
        dk.setAmaunBayaran(ac);
        dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
        dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
        dk.setInfoAudit(ia);
        dk.setCawangan(caw);

        String query = "Select p FROM etanah.model.AduanLokasi p WHERE p.idAduanLokasi = :idAduanLokasi";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idAduanLokasi", idKewDok);
        return dk;
    }

    public Transaksi bayarKurang(List<Transaksi> tList, Pengguna pengguna, KodCawangan caw, DokumenKewangan dk, Akaun acc,
            String account, BigDecimal jumCaraBayar) {

        Transaksi t = new Transaksi();
        BigDecimal bd = jumCaraBayar;
        for (Transaksi transaksi : tList) {
            bd = bd.subtract(transaksi.getAmaun());

            t = new Transaksi();
            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            int year = Integer.parseInt(yy.format(now));
            Akaun akaun = new Akaun();
            akaun.setNoAkaun(account);

            if (bd.doubleValue() > 0.0) {
                t.setAkaunKredit(akaun);
                t.setAkaunDebit(acc);
                t.setAmaun(transaksi.getAmaun());
                t.setCawangan(caw);
                t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
                t.setKodTransaksi(transaksi.getKodTransaksi());
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                t.setUntukTahun(transaksi.getUntukTahun());
                t.setTahunKewangan(year);
                t.setBayaranAgensi("N");
                t.setPerihal("T");
                manager.save(t);
            }

            if (bd.doubleValue() == 0.0) {
                t.setAkaunKredit(akaun);
                t.setAkaunDebit(acc);
                t.setAmaun(transaksi.getAmaun());
                t.setCawangan(caw);
                t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
                t.setKodTransaksi(transaksi.getKodTransaksi());
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                t.setUntukTahun(transaksi.getUntukTahun());
                t.setTahunKewangan(year);
                t.setBayaranAgensi("N");
                t.setPerihal("T");
                manager.save(t);
                break;
            }

            if (bd.doubleValue() < 0.0) {
                t.setAkaunKredit(akaun);
                t.setAkaunDebit(acc);
                t.setAmaun(transaksi.getAmaun().subtract(bd.multiply(new BigDecimal(-1))));
                t.setCawangan(caw);
                t.setDokumenKewangan(dokumenKewanganDAO.findById(dk.getIdDokumenKewangan()));
                t.setKodTransaksi(transaksi.getKodTransaksi());
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
                t.setUntukTahun(transaksi.getUntukTahun());
                t.setTahunKewangan(year);
                t.setBayaranAgensi("N");
                t.setPerihal("T");
                manager.save(t);
                break;
            }

        }

        return t;
    }

    public Transaksi bayarKurang1(List<Transaksi> tList, Pengguna pengguna, KodCawangan caw, DokumenKewangan dk, Akaun akh,
            String account, BigDecimal jumCaraBayar, int year, InfoAudit ia) {

        Transaksi t = new Transaksi();
        logger.info("-------------------------kurang bayar----------------------");
        BigDecimal bd = jumCaraBayar;
//        BigDecimal bakiTrans = bayaran;
        BigDecimal amaunTemp = new BigDecimal(0);
        BigDecimal bakiBayar = jumCaraBayar;
        logger.info("bayaran : " + bakiBayar);

        logger.info(account);
        Akaun kr = akaunDAO.findById(account);
        if (tList != null) {
            for (Transaksi tr : tList) {
                logger.info("1 : " + tr.getKodTransaksi().getKod());
                t = new Transaksi();
                amaunTemp = tr.getAmaun();
                logger.info("------------ amaunTemp :" + amaunTemp);

                transactions(t, dk.getIdDokumenKewangan(), pengguna, ia, year, tr);
                t.setCawangan(pengguna.getKodCawangan());
                t.setKodTransaksi(tr.getKodTransaksi());
                t.setPerihal("T");

//                amaunTemp = bakiTrans.subtract(tr.getAmaun());
//                logger.info("2. Baki Trans : "+bakiTrans);

                if (bakiBayar.compareTo(tr.getAmaun()) == -1) {
                    logger.info("5. bakiTrans == -1 ");
                    t.setKodTransaksi(tr.getKodTransaksi());
                    t.setAmaun(bakiBayar);
                    t.setAkaunDebit(akh);
                    t.setAkaunKredit(kr);
                    manager.save(t);
                    break;
                }
                if (bakiBayar.compareTo(tr.getAmaun()) == 0) {
                    logger.info("3. bakiTrans == 0 ");
                    t.setAmaun(tr.getAmaun());
                    t.setAkaunDebit(akh);
                    t.setAkaunKredit(kr);
                    manager.save(t);
                    break;
                }
                if (bakiBayar.compareTo(tr.getAmaun()) == 1) {
                    logger.info("4. bakiTrans == 1 " + tr.getAmaun());
                    t.setAmaun(tr.getAmaun());
                    t.setAkaunDebit(akh);
                    t.setAkaunKredit(kr);
                    manager.save(t);
                    bakiBayar = bakiBayar.subtract(amaunTemp);
                    logger.info("4.1 bakiTrans == 1 " + bakiBayar);
                }
            }
        }

        return t;
    }

    public void transactions(Transaksi t, String resit, Pengguna pengguna, InfoAudit ia, int year, Transaksi transA) {
        Date now = new Date();
        t.setDokumenKewangan(dokumenKewanganDAO.findById(resit));
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        t.setTahunKewangan(year);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(now);
        t.setUntukTahun(transA.getUntukTahun());
        t.setInfoAudit(ia);
    }

    public void saveSecondPayment(List<Transaksi> listDebit, List<Transaksi> listKredit, Hakmilik hm, BigDecimal total,
            BigDecimal caj, BigDecimal bayar, String resit, Pengguna pguna, Akaun akh) {

        BigDecimal cukaiDebit = new BigDecimal(0);
        BigDecimal dendaDebit = new BigDecimal(0);
        BigDecimal tunggakanDebit = new BigDecimal(0);

        BigDecimal cukaiKredit = new BigDecimal(0);
        BigDecimal dendaKredit = new BigDecimal(0);
        BigDecimal tunggakanKredit = new BigDecimal(0);

        for (Transaksi t : listDebit) {
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa"))) && (t.getStatus().getKod() == 'A')) {
                cukaiDebit = cukaiDebit.add(t.getAmaun());
            }
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahTunggakan"))) && (t.getStatus().getKod() == 'A')) {
                tunggakanDebit = tunggakanDebit.add(t.getAmaun());
            }
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) && (t.getStatus().getKod() == 'A')) {
                dendaDebit = dendaDebit.add(t.getAmaun());
            }
        }

        for (Transaksi t : listKredit) {
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa"))) && (t.getStatus().getKod() == 'T')) {
                cukaiKredit = cukaiKredit.add(t.getAmaun());
            }
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahTunggakan"))) && (t.getStatus().getKod() == 'T')) {
                tunggakanKredit = tunggakanKredit.add(t.getAmaun());
            }
            if ((t.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) && (t.getStatus().getKod() == 'T')) {
                dendaKredit = dendaKredit.add(t.getAmaun());
            }
        }

        BigDecimal checking = cukaiDebit.subtract(cukaiKredit);
        BigDecimal denda = dendaDebit.subtract(dendaKredit);

        if (checking.doubleValue() > 0) {
            createTransaction(checking, listDebit, resit, pguna, akh);
        } else if ((checking.doubleValue() == 0.0) && (denda.doubleValue() > 0.0)) {
            createDendaTransaction(denda, listDebit, resit, pguna, akh);
        }
    }

    public void createTransaction(BigDecimal bal, List<Transaksi> senaraiTransaksi, String resit, Pengguna pguna,
            Akaun akh) {
        DokumenKewangan dk = dokumenKewanganDAO.findById(resit);
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        for (Transaksi transaksi : senaraiTransaksi) {
            Transaksi t = new Transaksi();
            t.setCawangan(transaksi.getCawangan());
            t.setDokumenKewangan(dk);
            t.setKodTransaksi(transaksi.getKodTransaksi());
            t.setUntukTahun(transaksi.getUntukTahun());
            t.setTahunKewangan(year);
            t.setAkaunDebit(akh);
            t.setAkaunKredit(transaksi.getAkaunDebit());
            if (transaksi.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa"))) {
                t.setAmaun(bal);
            } else {
                t.setAmaun(transaksi.getAmaun());
            }
            t.setKuantiti(transaksi.getKuantiti());
            t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            t.setInfoAudit(ia);
            t.setBayaranAgensi("N");

            manager.save(t);
        }
    }

    public void createDendaTransaction(BigDecimal bal, List<Transaksi> senaraiTransaksi, String resit, Pengguna pguna,
            Akaun akh) {
        DokumenKewangan dk = dokumenKewanganDAO.findById(resit);
        Date now = new Date();
        int year = Integer.parseInt(yy.format(now));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        for (Transaksi transaksi : senaraiTransaksi) {
            if (!transaksi.getKodTransaksi().getKod().equals(hasil.getProperty("cukaiTanahSemasa"))) {
                Transaksi t = new Transaksi();
                t.setCawangan(transaksi.getCawangan());
                t.setDokumenKewangan(dk);
                t.setKodTransaksi(transaksi.getKodTransaksi());
                t.setUntukTahun(transaksi.getUntukTahun());
                t.setTahunKewangan(year);
                t.setAkaunDebit(akh);
                t.setAkaunKredit(transaksi.getAkaunDebit());
                if (transaksi.getKodTransaksi().getKod().equals(hasil.getProperty("dendaLewat"))) {
                    t.setAmaun(bal);
                } else {
                    t.setAmaun(transaksi.getAmaun());
                }
                t.setKuantiti(transaksi.getKuantiti());
                t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                t.setInfoAudit(ia);
                t.setBayaranAgensi("N");

                manager.save(t);
            }
        }
    }

    public int addHakmilik(etanahActionBeanContext ctx, List<Hakmilik> ku) {
        ArrayList<Hakmilik> au = null;
        Object obj = ctx.getWorkData();
        int pos = 0;

        if (obj == null || !(obj instanceof ArrayList)) {
            if (debug) {
                logger.debug("creating a new list of Urusan in session");
            }
            au = new ArrayList<Hakmilik>();
        } else {
            au = (ArrayList<Hakmilik>) obj;
            if (debug) {
                logger.debug("there are already " + au.size()
                        + " registered in session listed below:");
                for (Hakmilik u1 : au) {
                    logger.debug(u1.getIdHakmilik());
                }
            }
            pos = au.size();
        }
        au.addAll(ku);
        ctx.setWorkData(au);

        return pos;
    }

    public final ArrayList<Hakmilik> getAllHakmilikFromSession(etanahActionBeanContext ctx) {
        ArrayList<Hakmilik> listKu = null;

        Object obj = ctx.getWorkData();
        if (obj == null || !(obj instanceof ArrayList)) {
            if (debug) {
                logger.debug("creating a new ArrayList of Urusan in session");
            }
            listKu = new ArrayList<Hakmilik>();
            ctx.setWorkData(listKu);
        } else {
            listKu = (ArrayList<Hakmilik>) obj;
            if (debug) {
                logger.debug("there are already " + listKu.size() + " registered in session listed below:");
                for (Hakmilik u1 : listKu) {
                    logger.debug(u1.getIdHakmilik());
                }
            }
        }
        return listKu;
    }

    public void resetAll(etanahActionBeanContext ctx) {
        ctx.removeWorkdata();
    }

    public void bayaranKedua(List<Transaksi> senaraiTransaksiDebit, List<Transaksi> senaraiTransaksiKredit, Pengguna pengguna,
            InfoAudit ia, Akaun akh, Date now, DokumenKewangan dokumenKewangan, int tahun, Akaun akaun, BigDecimal jumCaraBayar) {

        BigDecimal bakiTrans = new BigDecimal(0);
        String kod = null;
        List<Transaksi> listTemp = new ArrayList<Transaksi>();
        for (Transaksi t : senaraiTransaksiDebit) {
            BigDecimal temp = new BigDecimal(0);

            for (Transaksi tr : senaraiTransaksiKredit) {
                if ((tr.getKodTransaksi().getKod().equals(t.getKodTransaksi().getKod())) && (tr.getUntukTahun() == t.getUntukTahun())) {
                    temp = temp.add(tr.getAmaun());
                    bakiTrans = t.getAmaun().subtract(temp);
                    kod = t.getKodTransaksi().getKod();
                    listTemp.remove(t);
                }
            }
            if (temp.compareTo(t.getAmaun()) < 0) {
                listTemp.add(t);
            }
        }
        installment(listTemp, bakiTrans, pengguna, ia, akh, now, dokumenKewangan, kod, tahun, akaun, jumCaraBayar);
    }

    public void installment(List<Transaksi> listTemp, BigDecimal bakiTrans, Pengguna pengguna, InfoAudit ia, Akaun akh, Date now,
            DokumenKewangan dk, String kod, int tahun, Akaun akaun, BigDecimal jumCaraBayar) {
        BigDecimal bakiBayar = jumCaraBayar;
        BigDecimal tempValue = new BigDecimal(0);
        Transaksi transaksi = new Transaksi();

        for (Transaksi t : listTemp) {
            transaksi = new Transaksi();
            tempValue = t.getAmaun();
            if (bakiTrans.compareTo(new BigDecimal(0)) > 0) {
                tempValue = bakiTrans;
            }

            if ((bakiBayar.compareTo(tempValue) == 0) || (bakiBayar.compareTo(tempValue) == -1)) {
                transaksi.setCawangan(dk.getCawangan());
                transaksi.setDokumenKewangan(dk);
                transaksi.setKodTransaksi(t.getKodTransaksi());
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setUntukTahun(t.getUntukTahun());
                transaksi.setTahunKewangan(tahun);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaun);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                transaksi.setInfoAudit(ia);
                transaksi.setAmaun(bakiBayar);

                bakiBayar = bakiBayar.subtract(tempValue);
                bakiTrans = bakiTrans.subtract(tempValue);
                if (bakiTrans.compareTo(new BigDecimal(0)) < 0) {
                    bakiTrans = new BigDecimal(0);
                }
                manager.save(transaksi);
                if (bakiBayar.compareTo(new BigDecimal(0)) <= 0) {
                    break;
                }
            }

            if (bakiBayar.compareTo(tempValue) == 1) {
                transaksi.setCawangan(dk.getCawangan());
                transaksi.setDokumenKewangan(dk);
                transaksi.setKodTransaksi(t.getKodTransaksi());
                transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                transaksi.setUntukTahun(t.getUntukTahun());
                transaksi.setTahunKewangan(tahun);
                transaksi.setAkaunDebit(akh);
                transaksi.setAkaunKredit(akaun);
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(now);
                transaksi.setInfoAudit(ia);
                transaksi.setAmaun(tempValue);

                manager.save(transaksi);
                bakiBayar = bakiBayar.subtract(tempValue);
                bakiTrans = bakiTrans.subtract(tempValue);
                if (bakiBayar.compareTo(new BigDecimal(0)) <= 0) {
                    break;
                }
            }
        }
        if (bakiBayar.compareTo(new BigDecimal(0)) > 0) {
            transaksi = new Transaksi();
            String t = new SimpleDateFormat("yyyy").format(now);

            transaksi.setCawangan(dk.getCawangan());
            transaksi.setDokumenKewangan(dk);
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa")));
            transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
            transaksi.setUntukTahun(Integer.parseInt(t));
            transaksi.setTahunKewangan(tahun);
            transaksi.setAkaunDebit(akh);
            transaksi.setAkaunKredit(akaun);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
            transaksi.setInfoAudit(ia);
            transaksi.setAmaun(bakiBayar);
            manager.save(transaksi);
        }
    }

    public List<Akaun> findAll(Map<String, String[]> param, int start, int max, String caw) {

        if (debug) {
            logger.debug("from record [" + start + "]");
            logger.debug("to record [" + max + "]");
        }


        String query = "SELECT distinct a FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik "
                + "AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','BP') ";

        String query1 = "SELECT distinct a FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik "
                + "AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','BP') ";
//        if (caw.equals("00")) {
//            query += "AND a.hakmilik.kodHakmilik in( 'GRN', 'PN', 'HSD')";
//
//        }
//AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA')
//        if (isNotBlank(param.get("namaPemilik"))) {
//            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
//        }
//        if (isNotBlank(param.get("noPengenalan"))) {
//            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
//        }
//        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
//            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
//        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }

        if (isNotBlank(param.get("daerah"))) {
//            query += "AND a.hakmilik.cawangan.kod = :daerah ";
            query += "AND a.hakmilik.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }
        
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }

        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }


        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik LIKE :noHakmilik ";
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }

        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot LIKE :noLot ";
        }
        
        if (debug) {
            logger.debug("query : " + query);
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setFirstResult(start);
        q.setMaxResults(max);
        
        
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0].trim());
        }
        
        if (isNotBlank(param.get("daerah"))) {
            logger.info("----------------" + param.get("daerah")[0].trim());
            q.setString("daerah", param.get("daerah")[0].trim());
        }
        
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            logger.info("==========================================="+param.get("kodStatusHakmilik")[0].trim());
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%" + param.get("noHakmilik")[0].trim() + "%");
        }
        
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%" + param.get("noLot")[0].trim() + "%");
        }

        return q.list();
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

    public Long getTotalRecord(Map<String, String[]> param, String caw) {


//        String query = "SELECT count(distinct a) FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk "
//                + "WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' "
//                + "AND hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS') ";
        
        String query = "SELECT count(distinct a) FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk "
                + "WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik "
                +" AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL') ";


//        if (!caw.equals("00")) {
//            query += "AND a.hakmilik.kodHakmilik in( 'GRN', 'PN', 'HSD')";
//
//        }
        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot = :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik = :noHakmilik ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND a.hakmilik.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND a.hakmilik.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND a.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND a.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND a.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }

        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idHakmilik"))) {
            logger.debug("....::" + param.get("idHakmilik")[0].trim());
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", param.get("noLot")[0].trim());
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", param.get("noHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0].trim());
        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }

        return (Long) q.iterate().next();
    }
}

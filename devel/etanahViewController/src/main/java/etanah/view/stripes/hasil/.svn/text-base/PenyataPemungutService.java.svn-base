package etanah.view.stripes.hasil;

import etanah.model.*;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PenyataPemungutDAO;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.sequence.GeneratorIdLaporanPenyataPemungut;
import java.math.BigInteger;

/**
 *
 * @author abdul.hakim
 */
public class PenyataPemungutService extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(PenyataPemungutService.class);
    private static boolean isDebug = logger.isDebugEnabled();
    @Inject
    KutipanHasilManager manager;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    GeneratorIdLaporanPenyataPemungut generator;
    @Inject
    PenyataPemungutDAO penyataPemungutDAO;
    private BigDecimal amaunTemp = new BigDecimal(0);

    public void savePenyataPemungut(DokumenKewangan dk) {
        logger.info("...::: savePenyataPemungut START :::...");

        Pengguna pengguna = dk.getInfoAudit().getDimasukOleh();
        List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
        String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit"
                + //                " AND t.status.kod = 'T' order by t.kodTransaksi.keutamaan";                // asal
                " AND t.status.kod = 'T'  AND t.amaun >0 order by t.kodTransaksi.keutamaan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("resit", dk.getIdDokumenKewangan());
        senaraiTransaksi = q.list();
        logger.info("senaraiTransaksi.size() : " + senaraiTransaksi.size());

        List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
        dkbList = dk.getSenaraiBayaran();
        if (dkbList == null) {
            dkbList = findKewDokumenBayarList(dk.getIdDokumenKewangan());
        }

        LaporanPenyataPemungutItem lpp = new LaporanPenyataPemungutItem();

        List<Transaksi> senaraiTransaksiAktif = senaraiTransaksi;
        List<Transaksi> senaraiTransaksiTemp = new ArrayList<Transaksi>();
        BigDecimal bakiTrans = new BigDecimal(0);
        if (dkbList != null) {
            for (DokumenKewanganBayaran dokumenKewanganBayaran : dkbList) {
                BigDecimal bakiBayar = new BigDecimal(0);
                logger.debug("dokumenKewanganByaran 1:" + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama());
                logger.info("dokumenbyar amaun :" + dokumenKewanganBayaran.getAmaun());
                bakiBayar = dokumenKewanganBayaran.getAmaun();
                logger.info("bakiBayar 2:" + bakiBayar);

                logger.info("senaraiTransaksiTemp.size :" + senaraiTransaksiTemp.size());
                for (Transaksi transaksi : senaraiTransaksiTemp) {
                    senaraiTransaksiAktif.remove(transaksi);
                    logger.info("transaksiRemove 3:" + transaksi.getKodTransaksi().getKod());
                }

                for (Transaksi trans : senaraiTransaksiAktif) {
                    amaunTemp = trans.getAmaun();
                    lpp = new LaporanPenyataPemungutItem();
                    logger.debug("trans 4:" + trans.getKodTransaksi().getKod() + " trans.getAmaun() : " + trans.getAmaun());
                    if (!bakiTrans.equals(new BigDecimal(0))) {
                        amaunTemp = bakiTrans;
                        logger.info("---- trans.getAmaun() :" + trans.getAmaun());
                    }
                    if (bakiBayar.compareTo(amaunTemp) == 0) {
                        logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                        bakiBayar = bakiBayar.subtract(amaunTemp);
                        logger.info("bakiBayar 5:" + bakiBayar);

                        Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                        lpp.setIdLaporan(id);
                        lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                        lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                        lpp.setIdTransaksi(trans.getIdTransaksi());
                        lpp.setAmaun(amaunTemp);
                        lpp.setStatus('A');
                        manager.save(lpp);

                        senaraiTransaksiTemp.add(trans);
                        bakiTrans = new BigDecimal(0);
                        break;
                    }
                    if (bakiBayar.compareTo(amaunTemp) == -1) {
                        logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                        bakiTrans = trans.getAmaun().subtract(bakiBayar);
                        logger.info("bakiTrans 6:" + bakiTrans);

                        Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                        lpp.setIdLaporan(id);
                        lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                        lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                        lpp.setIdTransaksi(trans.getIdTransaksi());
                        lpp.setAmaun(bakiBayar);
                        lpp.setStatus('A');
                        manager.save(lpp);

                        bakiBayar = new BigDecimal(0);
                        logger.info("bakiBayar 7:" + bakiBayar);
                        break;
                    }
                    if (bakiBayar.compareTo(amaunTemp) == 1) {
                        logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                        bakiBayar = bakiBayar.subtract(amaunTemp);
                        logger.info("trans.getAmaun() 2: " + trans.getAmaun());
                        logger.info("bakiBayar 8:" + bakiBayar);

                        Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                        lpp.setIdLaporan(id);
                        lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                        lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                        lpp.setIdTransaksi(trans.getIdTransaksi());
                        lpp.setAmaun(amaunTemp);
                        lpp.setStatus('A');
                        manager.save(lpp);

                        senaraiTransaksiTemp.add(trans);
                        bakiTrans = new BigDecimal(0);
                    }
                }
            }
        }
        logger.info("...::: savePenyataPemungut FINISH :::...");
    }

    public List<Transaksi> findKewTransByIdKewTrans(String idDokumenKewangan, String noAkaunKr) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select trans from etanah.model.Transaksi trans where trans.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan and trans.akaunKredit.noAkaun = :noAkaunKr");
        q.setString("idDokumenKewangan", idDokumenKewangan);
        q.setString("noAkaunKr", noAkaunKr);
        return q.list();
    }

    public DokumenKewanganBayaran findKewDokumenBayar(String idDokumenKewangan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select db from etanah.model.DokumenKewanganBayaran db where db.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan");
        q.setString("idDokumenKewangan", idDokumenKewangan);
        return (DokumenKewanganBayaran) q.uniqueResult();
    }

    public List<DokumenKewanganBayaran> findKewDokumenBayarList(String idDokumenKewangan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select db from etanah.model.DokumenKewanganBayaran db where db.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan");
        q.setString("idDokumenKewangan", idDokumenKewangan);
        return q.list();
    }

    public LaporanPenyataPemungutItem findIdTrans(String idTrans) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pp from etanah.model.hasil.LaporanPenyataPemungutItem pp where pp.idTransaksi = :idTrans");
        q.setString("idTrans", idTrans);
        return (LaporanPenyataPemungutItem) q.uniqueResult();
    }

    public List<LaporanPenyataPemungutItem> findSenaraiPenyataPemungut(String idDok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pp from etanah.model.hasil.LaporanPenyataPemungutItem pp where pp.idDokumenKewangan = :idDok");
        q.setString("idDok", idDok);
        return q.list();
    }

    public void savePenyataPemungut1(List<DokumenKewangan> list, DokumenKewangan dk) {
        logger.info("...::: savePenyataPemungut1 FINISH :::...");
        Pengguna pengguna = dk.getInfoAudit().getDimasukOleh();
        List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
        List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
//        dkbList = dk.getSenaraiBayaran();
        LaporanPenyataPemungutItem lpp = new LaporanPenyataPemungutItem();

        for (DokumenKewangan dokKew : list) {
            logger.info("dokKew.getIdDokumenKewangan() : " + dokKew.getIdDokumenKewangan());
            List<Transaksi> senaraiT = new ArrayList<Transaksi>();
            String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit"
                    + //                    " AND t.status.kod = 'T' order by t.kodTransaksi.keutamaan";              // asal
                    " AND t.status.kod = 'T' AND t.amaun > 0order by t.kodTransaksi.keutamaan";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("resit", dokKew.getIdDokumenKewangan());
            senaraiT = q.list();
            senaraiTransaksi.addAll(senaraiT);
            dkbList = dokKew.getSenaraiBayaran();
        }
        List<Transaksi> senaraiTransaksiAktif = senaraiTransaksi;
        List<Transaksi> senaraiTransaksiTemp = new ArrayList<Transaksi>();
        BigDecimal bakiTrans = new BigDecimal(0);

        for (DokumenKewanganBayaran dkb : dkbList) {
            logger.info("1. dkb.getDokumenKewangan().getIdDokumenKewangan() : " + dkb.getDokumenKewangan().getIdDokumenKewangan());
            BigDecimal bakiBayar = new BigDecimal(0);
            bakiBayar = dkb.getCaraBayaran().getAmaun();
//            bakiBayar = dkb.getAmaun();
            logger.info("2. bakiBayar : " + bakiBayar);

            for (Transaksi tr : senaraiTransaksiTemp) {
                senaraiTransaksiAktif.remove(tr);
            }

            logger.info("3. senaraiTransaksiAktif.size() : " + senaraiTransaksiAktif.size());
            for (Transaksi tr : senaraiTransaksiAktif) {
//                logger.info("4. tr.getAkaunKredit().getNoAkaun() : "+tr.getAkaunKredit().getNoAkaun());
                logger.info("5. tr.getAmaun() : " + tr.getAmaun());
                amaunTemp = tr.getAmaun();
                lpp = new LaporanPenyataPemungutItem();

                logger.info("6. bakiTrans : " + bakiTrans);
                if (!bakiTrans.equals(new BigDecimal(0))) {
                    logger.info("7. Xsama baki " + bakiTrans);
                    amaunTemp = bakiTrans;
                }
                logger.info("8. amaunTemp : " + amaunTemp);
                logger.info("9. bakiBayar : " + bakiBayar);

                if (bakiBayar.compareTo(amaunTemp) == 0) {                          // cara bayar == transaksi
                    logger.info("10. sama baki " + bakiBayar);
                    bakiBayar = bakiBayar.subtract(tr.getAmaun());

                    Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                    lpp.setIdLaporan(id);
                    lpp.setIdKewanganBayaran(dkb.getIdKewanganBayaran());
                    lpp.setIdDokumenKewangan(tr.getDokumenKewangan().getIdDokumenKewangan());
                    lpp.setIdTransaksi(tr.getIdTransaksi());
                    lpp.setAmaun(amaunTemp);
                    lpp.setStatus('A');
                    manager.save(lpp);

                    senaraiTransaksiTemp.add(tr);
                    bakiTrans = new BigDecimal(0);
                    break;
                }

                if (bakiBayar.compareTo(amaunTemp) == -1) {                     // cara bayar < trasnasksi
                    logger.info("11. kurang baki " + bakiBayar);
                    bakiTrans = amaunTemp.subtract(bakiBayar);

                    Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                    lpp.setIdLaporan(id);
                    lpp.setIdKewanganBayaran(dkb.getIdKewanganBayaran());
                    lpp.setIdDokumenKewangan(tr.getDokumenKewangan().getIdDokumenKewangan());
                    lpp.setIdTransaksi(tr.getIdTransaksi());
                    lpp.setAmaun(bakiBayar);
                    lpp.setStatus('A');
                    manager.save(lpp);

                    bakiBayar = new BigDecimal(0);
                    break;
                }

                if (bakiBayar.compareTo(amaunTemp) == 1) {                  // cara bayar >transaksi
                    logger.info("12. lebih baki " + bakiBayar);
                    bakiBayar = bakiBayar.subtract(amaunTemp);

                    Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                    lpp.setIdLaporan(id);
                    lpp.setIdKewanganBayaran(dkb.getIdKewanganBayaran());
                    lpp.setIdDokumenKewangan(tr.getDokumenKewangan().getIdDokumenKewangan());
                    lpp.setIdTransaksi(tr.getIdTransaksi());
                    lpp.setAmaun(amaunTemp);
                    lpp.setStatus('A');
                    manager.save(lpp);

                    senaraiTransaksiTemp.add(tr);
                    bakiTrans = new BigDecimal(0);
                }
            }
        }
    }

    public void savePenyataPemungutKutipanLuar(List<DokumenKewangan> list) {
        logger.info("...::: savePenyataPemungut START :::...");

        for (DokumenKewangan dk : list) {
            Pengguna pengguna = dk.getInfoAudit().getDimasukOleh();
            List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();
            String query = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :resit"
                    + " AND t.status.kod = 'T' order by t.kodTransaksi.keutamaan";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("resit", dk.getIdDokumenKewangan());
            senaraiTransaksi = q.list();
            logger.info("senaraiTransaksi.size() : " + senaraiTransaksi.size());

            List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
            String queryDKB = "SELECT kdb FROM etanah.model.DokumenKewanganBayaran kdb where kdb.dokumenKewangan.idDokumenKewangan = :resit";
            Query qDKB = sessionProvider.get().createQuery(queryDKB);
            qDKB.setString("resit", dk.getIdDokumenKewangan());
            dkbList = qDKB.list();
            logger.info("dkbList.size() : " + dkbList.size());

            LaporanPenyataPemungutItem lpp = new LaporanPenyataPemungutItem();

            List<Transaksi> senaraiTransaksiAktif = senaraiTransaksi;
            List<Transaksi> senaraiTransaksiTemp = new ArrayList<Transaksi>();
            BigDecimal bakiTrans = new BigDecimal(0);
            if (dkbList != null) {
                for (DokumenKewanganBayaran dokumenKewanganBayaran : dkbList) {
                    BigDecimal bakiBayar = new BigDecimal(0);
                    logger.debug("dokumenKewanganByaran 1:" + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama());
                    logger.info("dokumenbyar amaun :" + dokumenKewanganBayaran.getAmaun());
                    bakiBayar = dokumenKewanganBayaran.getAmaun();
                    logger.info("bakiBayar 2:" + bakiBayar);

                    logger.info("senaraiTransaksiTemp.size :" + senaraiTransaksiTemp.size());
                    for (Transaksi transaksi : senaraiTransaksiTemp) {
                        senaraiTransaksiAktif.remove(transaksi);
                        logger.info("transaksiRemove 3:" + transaksi.getKodTransaksi().getKod());
                    }

                    for (Transaksi trans : senaraiTransaksiAktif) {
                        amaunTemp = trans.getAmaun();
                        lpp = new LaporanPenyataPemungutItem();
                        logger.debug("trans 4:" + trans.getKodTransaksi().getKod() + " trans.getAmaun() : " + trans.getAmaun());
                        if (!bakiTrans.equals(new BigDecimal(0))) {
                            amaunTemp = bakiTrans;
                            logger.info("---- trans.getAmaun() :" + trans.getAmaun());
                        }
                        if (bakiBayar.compareTo(amaunTemp) == 0) {
                            logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                            bakiBayar = bakiBayar.subtract(amaunTemp);
                            logger.info("bakiBayar 5:" + bakiBayar);

                            Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                            lpp.setIdLaporan(id);
                            lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                            lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                            lpp.setIdTransaksi(trans.getIdTransaksi());
                            lpp.setAmaun(amaunTemp);
                            lpp.setStatus('A');
                            manager.save(lpp);

                            senaraiTransaksiTemp.add(trans);
                            bakiTrans = new BigDecimal(0);
                            break;
                        }
                        if (bakiBayar.compareTo(amaunTemp) == -1) {
                            logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                            bakiTrans = trans.getAmaun().subtract(bakiBayar);
                            logger.info("bakiTrans 6:" + bakiTrans);

                            Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                            lpp.setIdLaporan(id);
                            lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                            lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                            lpp.setIdTransaksi(trans.getIdTransaksi());
                            lpp.setAmaun(bakiBayar);
                            lpp.setStatus('A');
                            manager.save(lpp);

                            bakiBayar = new BigDecimal(0);
                            logger.info("bakiBayar 7:" + bakiBayar);
                            break;
                        }
                        if (bakiBayar.compareTo(amaunTemp) == 1) {
                            logger.debug("bayar " + dokumenKewanganBayaran.getCaraBayaran().getKodCaraBayaran().getNama() + " kpd " + trans.getKodTransaksi().getKod());
                            bakiBayar = bakiBayar.subtract(amaunTemp);
                            logger.info("trans.getAmaun() 2: " + trans.getAmaun());
                            logger.info("bakiBayar 8:" + bakiBayar);

                            Long id = Long.parseLong(generator.generate(pengguna.getKodCawangan().getKod(), dk.getCawangan(), pengguna));
                            lpp.setIdLaporan(id);
                            lpp.setIdKewanganBayaran(dokumenKewanganBayaran.getIdKewanganBayaran());
                            lpp.setIdDokumenKewangan(trans.getDokumenKewangan().getIdDokumenKewangan());
                            lpp.setIdTransaksi(trans.getIdTransaksi());
                            lpp.setAmaun(amaunTemp);
                            lpp.setStatus('A');
                            manager.save(lpp);

                            senaraiTransaksiTemp.add(trans);
                            bakiTrans = new BigDecimal(0);
                        }
                    }
                }
            }
        }
        logger.info("...::: savePenyataPemungut FINISH :::...");
    }

    public BigDecimal getAmaunTemp() {
        return amaunTemp;
    }

    public void setAmaunTemp(BigDecimal amaunTemp) {
        this.amaunTemp = amaunTemp;
    }

    @Transactional
    public void savePenyataPemungut(LaporanPenyataPemungutItem lpp) {
        manager.save(lpp);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodAgensiKutipanCawanganDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodStatusDokumenKewanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TransaksiDAO;
import etanah.dao.hasil.LaporanPenyataPemungutDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.InfoAudit;
import etanah.model.KodAgensiKutipanCawangan;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.Transaksi;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.sequence.GeneratorNoResit2;
import etanah.view.etanahContextListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author faidzal
 */
public class TerimaBayaranServices {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    GeneratorNoResit2 genresit;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodAgensiKutipanCawanganDAO kodAgensiKutipanCawanganDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    CaraBayaranDAO caraBayaranDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    DokumenKewanganBayaranDAO dokumenKewanganBayaranDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    LaporanPenyataPemungutDAO laporanPenyataPemungutDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodStatusDokumenKewanganDAO kodStatusDokumenKewanganDAO;
    @Inject
    TransaksiService transaksiService;
    private static final Logger LOG = Logger.getLogger(TerimaBayaranServices.class);

        public BayarValue terimaBayaranCukaiAgensi(String noAkaun, 
                String kodCaw, BigDecimal amaun, 
                String noResit, String noRuj, 
                String jenisBayaran, Date trhTransaksi,Akaun akaunKutip,KodAgensiKutipanCawangan agensiKutipan,InfoAudit ia,KodCaraBayaran kodCaraBayaran, String namaPembayar) {
//     kew_dokumen
//    cara_bayar EP
//    kew_dokumen_bayar
//    kew_trans
        
        BayarValue ba = new BayarValue();
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        try {

            Akaun akaun = akaunDAO.findById(noAkaun);
            KodCawangan kodCawangan = kodCawanganDAO.findById(kodCaw);
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(genresit.getAndLockSerialNo(ia.getDimasukOleh()));
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setCawangan(kodCawangan);
            dk.setAmaunBayaran(amaun);
            dk.setAkaun(akaun);
            dk.setAmaunTunai(new BigDecimal(BigInteger.ZERO));
            dk.setIdKaunter(ia.getDimasukOleh().getIdKaunter());
            dk.setAgensiKutipan(agensiKutipan);
            dk.setNoRujukan(noRuj);
            dk.setNoRujukanManual(noResit);
            dk.setInfoAudit(ia);
            dk.setTarikhTransaksi(trhTransaksi);
            dk.setIsuKepada(namaPembayar);

            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk = dokumenKewanganDAO.saveOrUpdate(dk);

            CaraBayaran carabayar = new CaraBayaran();
            carabayar.setAmaun(amaun);
            carabayar.setCawangan(kodCawangan);
            carabayar.setKodCaraBayaran(kodCaraBayaran);
            carabayar.setAktif('Y');
            carabayar.setInfoAudit(ia);

            carabayar = caraBayaranDAO.saveOrUpdate(carabayar);

            DokumenKewanganBayaran dokkewbayar = new DokumenKewanganBayaran();
            dokkewbayar.setAmaun(amaun);
            dokkewbayar.setCaraBayaran(carabayar);
            dokkewbayar.setDokumenKewangan(dk);
            dokkewbayar.setAktif('Y');
            dokkewbayar.setInfoAudit(ia);

            dokkewbayar = dokumenKewanganBayaranDAO.saveOrUpdate(dokkewbayar);

            if ("cukai".equals(jenisBayaran)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int year = cal.get(Calendar.YEAR);
                Boolean strata = false;
             if(StringUtils.isEmpty(akaun.getHakmilik().getIdHakmilikInduk())){
             strata = false;
             }else{
                 strata = true;
             }
                transaksiService.updateTransaksi( akaun.getNoAkaun(),  akaun.getHakmilik().getIdHakmilik(),  akaunKutip,  dk,  kodCawangan,  ia, amaun,strata);
                akaun.setBaki(akaun.getBaki().subtract(amaun));
                akaun.setInfoAudit(ia);
                akaun = akaunDAO.saveOrUpdate(akaun);
                ba.setIdKewdok(dk.getIdDokumenKewangan());
            } 
            akaunKutip.setBaki(akaunKutip.getBaki().add(amaun));
            akaunKutip = akaunDAO.saveOrUpdate(akaunKutip);
            tx.commit();
        } catch (Exception e) {
            LOG.error(e);
            genresit.rollbackAndUnlockSerialNo(ia.getDimasukOleh());
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
//            addSimpleError(t.getMessage());
//            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
        } finally {
            genresit.commitAndUnlockSerialNo(ia.getDimasukOleh());
        }
        return ba;
    }
    
    public BayarValue terimaBayaran(String noAkaun, String kodCaw, BigDecimal amaun, String noResit, String noRuj, String jenisBayaran, Date trhTransaksi) {
//     kew_dokumen
//    cara_bayar EP
//    kew_dokumen_bayar
//    kew_trans
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(penggunaDAO.findById("portal"));
        ia.setTarikhMasuk(new Date());
        BayarValue ba = new BayarValue();
        Session sess = injector.getProvider(Session.class).get();
        Transaction tx = sess.beginTransaction();
        tx.begin();
        try {

            Akaun akaun = akaunDAO.findById(noAkaun);
            Akaun akaunKutip = akaunDAO.findById("AGPYT");
            KodCawangan kodCawangan = kodCawanganDAO.findById("00");
            DokumenKewangan dk = new DokumenKewangan();
            dk.setIdDokumenKewangan(genresit.getAndLockSerialNo(ia.getDimasukOleh()));
            dk.setKodDokumen(kodDokumenDAO.findById("RBY"));
            dk.setCawangan(kodCawangan);
            dk.setAmaunBayaran(amaun);
            dk.setAkaun(akaun);
            dk.setAmaunTunai(new BigDecimal(BigInteger.ZERO));
            dk.setIdKaunter(ia.getDimasukOleh().getIdKaunter());
            dk.setAgensiKutipan(kodAgensiKutipanCawanganDAO.findById("99"));
            dk.setNoRujukan(noRuj);
            dk.setNoRujukanManual(noResit);
            dk.setInfoAudit(ia);
            dk.setTarikhTransaksi(trhTransaksi);

            dk.setStatus(kodStatusDokumenKewanganDAO.findById("A"));
            dk = dokumenKewanganDAO.saveOrUpdate(dk);

            CaraBayaran carabayar = new CaraBayaran();
            carabayar.setAmaun(amaun);
            carabayar.setCawangan(kodCawangan);
            carabayar.setKodCaraBayaran(kodCaraBayaranDAO.findById("EP"));
            carabayar.setAktif('Y');
            carabayar.setInfoAudit(ia);

            carabayar = caraBayaranDAO.saveOrUpdate(carabayar);

            DokumenKewanganBayaran dokkewbayar = new DokumenKewanganBayaran();
            dokkewbayar.setAmaun(amaun);
            dokkewbayar.setCaraBayaran(carabayar);
            dokkewbayar.setDokumenKewangan(dk);
            dokkewbayar.setAktif('Y');
            dokkewbayar.setInfoAudit(ia);

            dokkewbayar = dokumenKewanganBayaranDAO.saveOrUpdate(dokkewbayar);

            if ("cukai".equals(jenisBayaran)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                int year = cal.get(Calendar.YEAR);
                Boolean strata = false;
             if(StringUtils.isEmpty(akaun.getHakmilik().getIdHakmilikInduk())){
             strata = false;
             }else{
                 strata = true;
             }
                transaksiService.updateTransaksi( akaun.getNoAkaun(),  akaun.getHakmilik().getIdHakmilik(),  akaunKutip,  dk,  kodCawangan,  ia, amaun,strata);
//                for (Transaksi trans : akaun.getSenaraiTransaksiDebit()) {
//                    Transaksi transaksi = new Transaksi();
//                    transaksi.setAmaun(trans.getAmaun());
//                    transaksi.setKodTransaksi(trans.getKodTransaksi());
//                    transaksi.setPermohonan(trans.getPermohonan());
//                    if (akaun.getBaki().setScale(2).equals(BigDecimal.ZERO.setScale(2)) || isNegative(akaun.getBaki())) {
//                        transaksi.setUntukTahun(year + 1);
//                    } else {
//                        transaksi.setUntukTahun(trans.getUntukTahun());
//                    }
//                    transaksi.setKuantiti(trans.getKuantiti());
//                    transaksi.setTahunKewangan(trans.getTahunKewangan());
//                    transaksi.setBayaranAgensi(trans.getBayaranAgensi());
//                    transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
//                    transaksi.setInfoAudit(ia);
//                    transaksi.setAkaunKredit(akaun);
//                    transaksi.setAkaunDebit(akaunKutip);
//                    transaksi.setDokumenKewangan(dk);
//                    transaksi.setCawangan(kodCawangan);
//                    transaksi = transaksiDAO.saveOrUpdate(transaksi);
//
////                LaporanPenyataPemungutItem pnyata = new LaporanPenyataPemungutItem();
////                pnyata.setIdDokumenKewangan(dk.getIdDokumenKewangan());
////                pnyata.setAmaun(trans.getAmaun());
////                pnyata.setIdKewanganBayaran(dokkewbayar.getIdKewanganBayaran());
////                pnyata.setIdTransaksi(transaksi.getIdTransaksi());
////                pnyata.setStatus('A');
////                pnyata = laporanPenyataPemungutDAO.saveOrUpdate(pnyata);
//                }
                akaun.setBaki(akaun.getBaki().subtract(amaun));
                akaun.getInfoAudit().setDimasukOleh(penggunaDAO.findById("portal"));
                akaun.getInfoAudit().setTarikhKemaskini(new Date());
                akaun = akaunDAO.saveOrUpdate(akaun);
                ba.setIdKewdok(dk.getIdDokumenKewangan());
            } else {

                Transaksi trans = new Transaksi();
                trans.setKodTransaksi(kodTransaksiDAO.findById("30390"));
                trans.setAmaun(amaun);
                trans.setAkaunDebit(akaunKutip);
                trans.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
                trans.setInfoAudit(ia);
                trans.setUntukTahun(Calendar.YEAR);
                trans.setKuantiti(1);
                trans.setDokumenKewangan(dk);
                trans.setCawangan(kodCawangan);
                trans.setPerihal("Carian Persendirian Hakmilik");
                trans = transaksiDAO.saveOrUpdate(trans);
                ba.setIdKewdok(dk.getIdDokumenKewangan());
                ba.setTrans(trans);
            }
            akaunKutip.setBaki(akaunKutip.getBaki().add(amaun));
            akaunKutip = akaunDAO.saveOrUpdate(akaunKutip);
            tx.commit();
        } catch (Exception e) {
            LOG.error(e);
            genresit.rollbackAndUnlockSerialNo(ia.getDimasukOleh());
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
//            addSimpleError(t.getMessage());
//            return new ForwardResolution("/WEB-INF/jsp/daftar/carian/bayaran.jsp");
        } finally {
            genresit.commitAndUnlockSerialNo(ia.getDimasukOleh());
        }
        return ba;
    }
    
        public static boolean isNegative(BigDecimal b) {
        boolean tru = false;
        if (b.signum() == -1) {
            tru = true;
        } else {
            tru = false;
        }
        return tru;
    }
}

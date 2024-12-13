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
import etanah.dao.*;
import etanah.model.hasil.LaporanPenyataPemungutItem;
import etanah.sequence.GeneratorIdLaporanPenyataPemungut;
import java.math.BigInteger;
import java.util.Date;

/**
 *Transaction Resit Manual
 * @author abdul.hakim
 */
public class TransactionService extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(TransactionService.class);
    private static boolean isDebug = logger.isDebugEnabled();
    
    @Inject
    KutipanHasilManager manager;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;

    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    

    public void saveTransaction(List<Transaksi> tList, BigDecimal bayaran, Akaun akh,
                                              String resit, Pengguna pengguna, InfoAudit ia, int year) {
        logger.info("...::: saveTransaction START :::...");

        BigDecimal bakiTrans = bayaran;
        BigDecimal amaunTemp = new BigDecimal(0);
        BigDecimal bakiBayar = bayaran;
        logger.info("bayaran : "+bakiBayar);
        if(tList != null){
            for (Transaksi tr : tList) {
                if(tr.getAmaun().compareTo(new BigDecimal(0)) > 0){
                    logger.info("1 : "+tr.getKodTransaksi().getKod());
                    Transaksi t = new Transaksi();
                    amaunTemp = tr.getAmaun();
                    logger.info("------------ amaunTemp :"+amaunTemp);

                    transactions(t, resit, pengguna, ia, year);
                    t.setCawangan(pengguna.getKodCawangan());
                    t.setKodTransaksi(tr.getKodTransaksi());
                    t.setBayaranAgensi("N");

    //                amaunTemp = bakiTrans.subtract(tr.getAmaun());
    //                logger.info("2. Baki Trans : "+bakiTrans);

                    if(bakiBayar.compareTo(tr.getAmaun()) == -1){
                        logger.info("5. bakiTrans == -1 ");
                        t.setKodTransaksi(tr.getKodTransaksi());
                        t.setAmaun(bakiBayar);
                        t.setAkaunDebit(akh);
                        t.setAkaunKredit(tr.getAkaunDebit());
                        manager.save(t);
                        break;
                    }
                    if(bakiBayar.compareTo(tr.getAmaun()) == 0){
                        logger.info("3. bakiTrans == 0 ");
                        t.setAmaun(tr.getAmaun());
                        t.setAkaunDebit(akh);
                        t.setAkaunKredit(tr.getAkaunDebit());
                        manager.save(t);
                        break;
                    }
                    if(bakiBayar.compareTo(tr.getAmaun()) == 1){
                        logger.info("4. bakiTrans == 1 "+tr.getAmaun());
                        t.setAmaun(tr.getAmaun());
                        t.setAkaunDebit(akh);
                        t.setAkaunKredit(tr.getAkaunDebit());
                        manager.save(t);
                        bakiBayar = bakiBayar.subtract(amaunTemp);
                        logger.info("4.1 bakiTrans == 1 "+bakiBayar);
                    }
                }
            }
        }
        logger.info("...::: saveTransaction FINISH :::...");
    }

    public void transactions(Transaksi t, String resit, Pengguna pengguna, InfoAudit ia, int year){
        Date now = new Date();
        t.setDokumenKewangan(dokumenKewanganDAO.findById(resit));
        t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
        t.setTahunKewangan(year);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(now);
        t.setUntukTahun(year);
        t.setInfoAudit(ia);
    }

}

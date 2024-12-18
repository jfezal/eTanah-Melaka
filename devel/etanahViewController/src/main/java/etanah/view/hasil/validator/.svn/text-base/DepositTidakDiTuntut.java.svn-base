package etanah.view.hasil.validator;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import etanah.workflow.*;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.view.stripes.hasil.KutipanHasilManager;
import java.math.BigDecimal;

/**
 *
 * @author haqqiem
 * 19 Dec 2012
 *
 */
public class DepositTidakDiTuntut implements StageListener {
    private static final Logger LOG = Logger.getLogger(DepositTidakDiTuntut.class);

    @Inject protected com.google.inject.Provider<Session> sessionProvider;
    @Inject KutipanHasilManager manager;
    @Inject KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject KodTransaksiDAO kodTransaksiDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");

        Date now = new Date();
        Permohonan permohonan = context.getPermohonan();
        Pengguna pguna = context.getPengguna();
        InfoAudit ia = new InfoAudit();
        
        List<Akaun> senaraiAkaun = new ArrayList<Akaun>();

        String query = "SELECT a FROM etanah.model.Akaun a WHERE a.permohonan.idPermohonan = :idPermohonan AND a.status.kod = 'B'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", permohonan.getIdPermohonan());
        senaraiAkaun  = q.list();
        LOG.info("senaraiAkaun.size() : "+senaraiAkaun.size());

//        String queryAKH = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun.kod = 'AKH' AND a.cawangan.kod =:caw";
//        Query qAKH = sessionProvider.get().createQuery(queryAKH);
//        qAKH.setString("caw", pguna.getKodCawangan().getKod());
//        Akaun akh  = (Akaun) qAKH.uniqueResult();

        for (Akaun akaun : senaraiAkaun) {
            List<Transaksi> transaksiKredit = akaun.getSenaraiTransaksiKredit();
            if(akaun.getStatus().getKod().equals("B")){
                for (Transaksi transaksi : transaksiKredit) {
                    Transaksi t = new Transaksi();

                    t.setAmaun(transaksi.getAmaun());
                    t.setAkaunDebit(akaun);
                    t.setCawangan(akaun.getCawangan());
                        ia.setDimasukOleh(pguna);
                        ia.setTarikhMasuk(now);
                        ia.setDiKemaskiniOleh(pguna);
                        ia.setTarikhKemaskini(now);
                    t.setInfoAudit(ia);
                    t.setKodTransaksi(transaksi.getKodTransaksi());
                    t.setKuantiti(1);
                    t.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                    t.setTahunKewangan(now.getYear()+ 1900);
                    t.setUntukTahun(now.getYear()+ 1900);

                    manager.save(t);
                }

                akaun.setBaki(new BigDecimal(0));
                    ia.setDimasukOleh(akaun.getInfoAudit().getDimasukOleh());
                    ia.setTarikhMasuk(akaun.getInfoAudit().getTarikhMasuk());
                    ia.setDimasukOleh(pguna);
                    ia.setTarikhKemaskini(now);
                akaun.setInfoAudit(ia);

                manager.saveOrUpdate(akaun);
            }
        }
        
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

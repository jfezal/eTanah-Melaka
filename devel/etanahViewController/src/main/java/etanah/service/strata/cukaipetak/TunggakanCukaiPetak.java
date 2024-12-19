/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.cukaipetak;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SkimStrataDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
import etanah.model.SkimStrata;
import etanah.model.Transaksi;
import etanah.service.AkaunService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
public class TunggakanCukaiPetak {

    private static final Logger LOG = Logger.getLogger(JanaCukaiPetak.class);
    @Inject
    CukaiPetakService cukaiPetakService;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    AkaunService akaunService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    @Inject
    KalkulatorCukaiPetak kalkulatorCukaiPetak;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    String idHakmilikInduk;
    String idPengguna;
    long idSkimStrata;
    Pengguna pengguna = new Pengguna();
    InfoAudit ia;
    
public void setTunggakanCukaiPetak(String idHakmilikInduk, String idPengguna, long idSkimStrata) {
        this.idHakmilikInduk = idHakmilikInduk;
        this.idPengguna = idPengguna;
        this.idSkimStrata = idSkimStrata;
        this.pengguna = penggunaDAO.findById(idPengguna);
        this.ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        ia.setTarikhKemaskini(new Date());
        ia.setDiKemaskiniOleh(pengguna);

    }
    public void janaTunggakan() {

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        SkimStrata skim = skimStrataDAO.findById(idSkimStrata);
        Akaun master = akaunService.getAkaunCukaiActiveForStrataHakmilik( skim.getHakmilikInduk().getCawangan().getKod(),skim.getHakmilikInduk().getIdHakmilik());
        BigDecimal tunggakanTanah = new BigDecimal("0.00");
        if(master != null){
            tunggakanTanah = master.getBaki();
        }else{
            tunggakanTanah = BigDecimal.ZERO;
            
        }

        Long totalUnitSyer = cukaiPetakService.getTotalUnitSyerPerSkim(skim.getHakmilikInduk().getIdHakmilik());
        try {
            List<Hakmilik> listHakmilik = cukaiPetakService.findHakmilikStrataByIdInduk(skim.getHakmilikInduk().getIdHakmilik());
            for (int a = 0; a < listHakmilik.size(); a++) {
                Hakmilik ha = listHakmilik.get(a);
                Akaun akaun = akaunDAO.findById(ha.getIdHakmilik());
                BigDecimal bd = new BigDecimal("0.00");
                if(ha.getUnitSyer() == null){
                    ha.setUnitSyer(bd);
                }
                BigDecimal amaunTunggakan = kiraTunggakanPerPetak(ha.getUnitSyer(), new BigDecimal(totalUnitSyer), tunggakanTanah);
                updateKewTrans(akaun, amaunTunggakan);
                akaun.setBaki(akaun.getBaki().add(amaunTunggakan));
                akaun.setIsJana('Y');
                updateAkaun(akaun);
            }
            master.setIsJana('Y');
            updateAkaun(master);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            throw new RuntimeException(t);
        } finally {
//            Transaction txs = s.beginTransaction();
            //sendNotification and update flag
//            KodCawangan cawangan = kodCawanganDAO.findById("00");
//            KodPeranan peranan = kodPerananDAO.findById("23");
//            Notifikasi notifikasi = new Notifikasi();
//            notifikasi.setCawangan(cawangan);
//            notifikasi.setMesej("Keseluruah Cukai Petak bagi Hakmilik Induk "+skim.getHakmilikInduk().getIdHakmilik()+" telah berjaya dijana dan bersedia untuk disemak.");
////            notifikasi.setPengguna(pengguna);
//            notifikasi.setTajuk("Cukai Petak Telah Siap dijana");
//            notifikasi.setInfoAudit(ia);
//            notifikasiService.addRoleToNotifikasi(notifikasi, cawangan, peranan);
//            txs.commit();

        }
    }

    private BigDecimal kiraTunggakanPerPetak(BigDecimal unitSyer, BigDecimal totalUnitSyer, BigDecimal tunggakanTanah) {
        BigDecimal a = null;
        double ab = unitSyer.doubleValue();
        double b = totalUnitSyer.doubleValue();
        double c = tunggakanTanah.doubleValue();
        double d;
        d = (ab/b)*c;
        a = new BigDecimal(d).setScale(1, RoundingMode.UP);
        LOG.info("Tunggakan Cukai Petak>>>"+a);
        return a;
    }

    @Transactional
    private void updateKewTrans(Akaun akaun, BigDecimal amaunTunggakan) {
        Transaksi t = new Transaksi();
        KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');
        KodTransaksi kodTransaksi = kodTransaksiDAO.findById("61502");
        t.setAkaunDebit(akaun);
        t.setAmaun(amaunTunggakan);
        t.setKodTransaksi(kodTransaksi);
        t.setKuantiti(0);
        t.setInfoAudit(ia);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        t.setTahunKewangan(year);
        t.setUntukTahun(year-1);
        t.setCawangan(akaun.getCawangan());
        t.setStatus(status);
        transaksiDAO.save(t);
    }

    @Transactional
    private void updateAkaun(Akaun akaun) {
        akaunDAO.save(akaun);
    }

}

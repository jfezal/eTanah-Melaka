/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.RemisyenManager;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author wan.fairul
 */
public class PelarasanCukaiService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Hakmilik hakmilik;
    private Akaun akaun;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KutipanHasilManager manager;
     @Inject
    RemisyenManager managerHasil;
     @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
     @Inject
    private  etanah.kodHasilConfig khconf;
        private static final Logger LOG = Logger.getLogger(PelarasanCukaiService.class);

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    public void larasCukai(String IdHakmilik, String cukai, String kodTrans) {

        hakmilik = hakmilikDAO.findById(IdHakmilik);
        akaun = hakmilik.getAkaunCukai();
        if (akaun != null) {

            List<Transaksi> trList = akaun.getSemuaTransaksi();
            for (Transaksi tr : trList) {
                if (tr.getKodTransaksi().getKod().equals(kodTrans)) {
                    tr.setAmaun(new BigDecimal(cukai));
                    manager.saveOrUpdate(tr);
                }

            }
             if((akaun.getBaki().doubleValue()) > 0){
                akaun.setBaki(new BigDecimal(cukai));
                manager.saveOrUpdate(akaun);
            }
        }
    }


        public void larasCukaiBaki(Permohonan permohonan, String IdHakmilik, String nilai, String kodTrans,  Pengguna pengguna) {
         String notis = khconf.getProperty(kodTrans);
        hakmilik = hakmilikDAO.findById(IdHakmilik);
        akaun = hakmilik.getAkaunCukai();
        BigDecimal nilaiDec = new BigDecimal(nilai);
     
        if (akaun != null) {
  LOG.info("enter save kew");

            //create new record KEW_TRANS
            Transaksi t = new Transaksi();
            KodTransaksi kt = new KodTransaksi();
            KodCawangan kc = new KodCawangan();
            kc.setKod(akaun.getCawangan().getKod());
            KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');
            kt.setKod(notis);
            t.setKodTransaksi(kt);
            t.setAmaun(nilaiDec);
            t.setCawangan(hakmilik.getCawangan());
            t.setPermohonan(permohonan);
            t.setStatus(status);
            t.setAkaunDebit(akaun);
            t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
            t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));
//            manager.saveOrUpdate(t);
            managerHasil.saveAndUpdate(t,null,null,null,null,akaun,pengguna);





//             if((akaun.getBaki().doubleValue()) > 0){
                akaun.setBaki(akaun.getBaki().add(nilaiDec));
                manager.saveOrUpdate(akaun);
//            }
        }
    }

}

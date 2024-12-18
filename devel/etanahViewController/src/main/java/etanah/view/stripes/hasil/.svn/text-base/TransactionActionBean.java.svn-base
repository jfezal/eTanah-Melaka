/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author abdul.hakim
 */
@UrlBinding ("/hasil/create_transaction")
public class TransactionActionBean extends AbleActionBean{

    private Transaksi transaksi;
    private String idTransaksi;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject
    private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    @Inject
    private KodTransaksiDAO kodTransaksiDAO;

    @Inject
    KutipanHasilManager manager;


    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    @Inject
    private etanah.kodHasilConfig hasil;

    public String generate(KodCawangan caw, Pengguna pguna, Akaun acc) {
        transaksi = new Transaksi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());
        int year = Integer.parseInt(sdf.format(new java.util.Date()));

        transaksi.setCawangan(caw);
        transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("cukaiTanahSemasa")));
        transaksi.setAmaun(acc.getBaki());
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        transaksi.setInfoAudit(ia);
        transaksi.setAkaunDebit(acc);
        transaksi.setUntukTahun(year);

        manager.save(transaksi);

        return idTransaksi;
    }

    public String createTransaction6A(Pengguna pguna, Hakmilik hm) {
        transaksi = new Transaksi();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        Akaun ak = null;
        ak = hm.getAkaunCukai();

        transaksi.setCawangan(pguna.getKodCawangan());//caw sape??pguna or hakmilik
        if("04".equals(conf.getProperty("kodNegeri"))){
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("notis6AMelaka")));
        }if("05".equals(conf.getProperty("kodNegeri"))){
            transaksi.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("notis6A")));
        }
        transaksi.setAmaun(new BigDecimal(20));
        transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
        transaksi.setInfoAudit(ia);
        transaksi.setAkaunDebit(ak);
        transaksi.setUntukTahun((new java.util.Date().getYear() + 1900));

        manager.save(transaksi);

        updateAkaunCukai(hm, pguna);

        return idTransaksi;
    }

    @Inject
    private etanah.Configuration conf;

    public void updateAkaunCukai(Hakmilik hm, Pengguna pguna){
        Akaun ak = hm.getAkaunCukai();
        InfoAudit ia = new InfoAudit();
        ia = ak.getInfoAudit();
        ia.setDiKemaskiniOleh(pguna);
        ia.setTarikhKemaskini(new java.util.Date());

        if("04".equals(conf.getProperty("kodNegeri"))){
            ak.setBaki(ak.getBaki().add(new BigDecimal(20)));
        }else if("05".equals(conf.getProperty("kodNegeri"))){
            ak.setBaki(ak.getBaki().add(new BigDecimal(10)));
        }
        ak.setInfoAudit(ia);

        manager.saveOrUpdate(ak);
    }
}

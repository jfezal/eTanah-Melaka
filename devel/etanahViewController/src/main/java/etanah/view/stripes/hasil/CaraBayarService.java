package etanah.view.stripes.hasil;

import etanah.dao.*;
import etanah.model.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.text.ParseException;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;

/**
 *
 * @author abdul.hakim
 */
public class CaraBayarService {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");

    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;

    @Inject
    KodBankDAO kodBankDAO;

    @Inject
    AkaunDAO akaunDAO;

    @Inject
    KutipanHasilManager manager;

    public ArrayList<DokumenKewanganBayaran> saveCaraBayaran(List<CaraBayaran> senaraiCaraBayaran, List<String> tarikhCek, InfoAudit ia,
            Pengguna pguna, String noAkaun, boolean bakiFlag, BigDecimal bg, DokumenKewangan dk,
                                ArrayList<DokumenKewanganBayaran> adkb, BigDecimal jumcaj)throws ParseException {
        for (int f = 0; f < senaraiCaraBayaran.size(); f++) {
            CaraBayaran cb = senaraiCaraBayaran.get(f);
            if (cb.getAmaun() != null) {
                if (cb.getAmaun().intValue() > 0) {
                    KodCaraBayaran crByr = kodCaraBayaranDAO.findById(cb.getKodCaraBayaran().getKod());
                    if (!crByr.getKod().equals("T")) {
                        Date d = sdf.parse(tarikhCek.get(f));
                        cb.setTarikhCek(d);
                    }
                    if (cb.getBank() != null && cb.getBank().getKod().equals("0")) {  // clear if null
                        cb.setBank(null);
                        cb.setBankCawangan(null);
                    }
                    if (cb.getBank() != null) {
                        KodBank bank = kodBankDAO.findById(cb.getBank().getKod());
                        cb.setBank(bank);
                    }
                    if ((crByr.getKod().equals("KW")) || (crByr.getKod().equals("WP"))) {
                        cb.setBank(kodBankDAO.findById("PMB"));
                    }
                    cb.setKodCaraBayaran(crByr);
                    cb.setCawangan(pguna.getKodCawangan());
                    cb.setInfoAudit(ia);
                    cb.setAktif('Y');
                    if (crByr.getKod().equals("T")) {
                        if (bakiFlag == true) {
//                            Akaun x = akaunDAO.findById(noAkaun);
                            cb.setAmaun(jumcaj);
//                            bg = x.getBaki();
                        }
                    }
                    manager.saveOrUpdate(cb);
                    DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();
                    for (int i = 0; i < senaraiCaraBayaran.size(); i++) {
                        CaraBayaran c = senaraiCaraBayaran.get(i);
                        if (c.getKodCaraBayaran() != null) {
                            if (c.getKodCaraBayaran().getKod().equals("T")) {
                                dk.setAmaunTunai(c.getAmaun());
                            }
                        }
                    }
                    dkb.setCaraBayaran(cb);
                    dkb.setDokumenKewangan(dk);
                    if (bakiFlag == true) {
                        Akaun x = akaunDAO.findById(noAkaun);
                        dkb.setAmaun(x.getBaki());
                    }
                    if (bakiFlag == false) {
                        dkb.setAmaun(cb.getAmaun());
                    }
                    dkb.setInfoAudit(ia);
                    dkb.setAktif('Y');
                    adkb.add(dkb);
                }
            }
        }
        return adkb;
    }

    public CaraBayaran saveCaraBayaran(Pengguna pguna, KodCaraBayaran kodCaraBayar, String noRujukan, KodBank kodBank,
            BigDecimal amount, DokumenKewangan dk, Akaun akaun) {
        
        CaraBayaran cb = new CaraBayaran();

        Date now = new Date();
        InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(now);

        cb.setCawangan(pguna.getKodCawangan());
        cb.setKodCaraBayaran(kodCaraBayar);
        cb.setAmaun(amount);
        cb.setAktif('Y');
        cb.setInfoAudit(ia);
        if (!cb.getKodCaraBayaran().getKod().equals("T")) {
            cb.setNoRujukan(noRujukan);
            cb.setBank(kodBank);
        }
        manager.saveOrUpdate(cb);

        DokumenKewanganBayaran dkb = new DokumenKewanganBayaran();

        dkb.setDokumenKewangan(dk);
        dkb.setCaraBayaran(cb);
        dkb.setAmaun(amount);
        dkb.setAktif('Y');
        dkb.setInfoAudit(ia);

        manager.saveOrUpdate(dkb);

        return cb;
    }
}

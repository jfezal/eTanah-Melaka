/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Injector;
import etanah.model.PortalPengguna;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faidzal
 */
public class BayaranOnlineService implements IBayaranOnlineService {

    private static Injector injector = etanahContextListener.getInjector();
    CukaiOnlineServices cukaiService = injector.getInstance(CukaiOnlineServices.class);
    UAMPortal uam = injector.getInstance(UAMPortal.class);
    CarianPersendirianServices carian = injector.getInstance(CarianPersendirianServices.class);

    @Override
    public String updateTransaction(String idHakmilik, BigDecimal jumlahBayaran, String idPengguna, String jenisTrans) {
        return cukaiService.updateTransaction(idHakmilik, jumlahBayaran, idPengguna, jenisTrans);
    }
//TODO

    @Override
    public String updateUserAccount(String idHakmilik, BigDecimal bd, String PAYMENT_TRANS_ID,
            String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String FPX, String idPengguna) {
        String resit = null;
        try {
            resit = cukaiService.updateCukaiAccount(idHakmilik, bd, PAYMENT_TRANS_ID, PAYMENT_DATETIME, PAYMENT_MODE, idPengguna, TRANS_ID, FPX); //To change body of generated methods, choose Tools | Templates.
        } catch (ParseException ex) {
            Logger.getLogger(BayaranOnlineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resit;
    }
//TODO

    @Override
    public String updateCarianAccount(String accountNo, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String PAYMENT_TRANS_ID1, String idPengguna, String RECEIPT_NO) {
        try {
            return carian.updateCarianAccount(accountNo, PAYMENT_TRANS_ID, PAYMENT_DATETIME, bd, idPengguna, TRANS_ID, RECEIPT_NO);
        } catch (ParseException ex) {
            Logger.getLogger(BayaranOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public StatusLogin logMasuk(String idPengguna, String katalaluan) {
        StatusLogin sts = new StatusLogin();
        //UAMPortal uam = new UAMPortal();
        PortalPengguna pp = uam.login(idPengguna, katalaluan);
        if (pp != null) {
            sts.setSts("A");
            sts.setTarikhterakhirlogin(pp.getTrhLogin());
            sts.setNama(pp.getNama());
            sts.setIdPengguna(idPengguna);
            pp.setTrhLogin(new Date());
            uam.savePortalPengguna(pp);
        } else {
            sts.setSts("T");
        }
        return sts;
    }

    @Override
    public void daftarPenggunaBaru(FormDaftarPengguna fd) {
        try {
            uam.daftarPengguna(fd);
        } catch (IOException ex) {
            Logger.getLogger(BayaranOnlineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SejarahTransaksiPortal> sejarahTransaksi(String idPengguna, String tarikhmula, String tarikhtamat) {
        return uam.sejarahTransaksi(idPengguna, tarikhmula, tarikhtamat);
    }

    @Override
    public List<DokumenCarianPersendirian> senaraiDokumen(String idPengguna, String tarikhmula, String tarikhtamat) {
        return carian.senaraiDokumen(idPengguna, tarikhmula, tarikhtamat); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AccountInfo> checkAccount(String accountNo, String idHakmilik) {
        return new CukaiOnlineServices().checkAccount(accountNo, idHakmilik);
    }

    @Override
    public List<UtilKod> listKodDaerah() {
        return uam.listKod("daerah", ""); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UtilKod> listKodMukim(String kodDaerah) {
        return uam.listKod("mukim", kodDaerah);
    }

    @Override
    public List<UtilKod> listKodHakmilik() {
        return uam.listKod("hakmilik", "");
    }

    @Override
    public FormDaftarPengguna profilPengguna(String idPengguna) {
        return uam.profilPengguna(idPengguna);
    }

    @Override
    public Konfigurasi config(String key) {
        Konfigurasi conf = new Konfigurasi();
        conf = uam.setConfig(key);
        return conf;
    }

    @Override
    public boolean lupaKataLaluan(String idPengguna) {
        try {
            return uam.lupakatalaluan(idPengguna);
        } catch (IOException ex) {
            Logger.getLogger(BayaranOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public DokumenInfo muatturundokumen(Long id) {
        return carian.getdokumencarian(id);
    }

    @Override
    public String updateAccountPukal(List<AkaunForm> listAkaunForm, BigDecimal bd, String PAYMENT_TRANS_ID,
            String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String FPX, String idPengguna) {
        String resit = null;
        try {
            resit = cukaiService.updateCukaiAccountPukal(listAkaunForm, bd, PAYMENT_TRANS_ID, PAYMENT_DATETIME, PAYMENT_MODE, idPengguna, TRANS_ID, FPX); //To change body of generated methods, choose Tools | Templates.
        } catch (ParseException ex) {
            Logger.getLogger(BayaranOnlineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resit;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Injector;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.etanahContextListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class CarianPersendirianOnlineService implements ICarianPersendirianOnlineService {

    private static Injector injector = etanahContextListener.getInjector();
    CukaiOnlineServices cukaiService = injector.getInstance(CukaiOnlineServices.class);
    UAMPortal uam = injector.getInstance(UAMPortal.class);
    CarianPersendirianEbayarService carian = injector.getInstance(CarianPersendirianEbayarService.class);

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
    public List<UtilKod> listKodSeksyen(String kodBpm) {
        return uam.listKod("seksyen", kodBpm);
    }

    @Override
    public List<UtilKod> listKodLot() {
        return uam.listKod("lot", "");
    }

    @Override
    public List<CarianInfo> checkAccount(String accountNo, String idHakmilik) {
        return carian.checkAccount(accountNo, idHakmilik);
    }

    @Override
    public List<CarianInfo> checkAccountByNoLot(String daerah,String bpm,String seksyen,String kodLot,Integer noLot) {
        try {
            return carian.checkAccountByNoLot(daerah, bpm,seksyen,kodLot, noLot);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CarianInfo> checkAccountByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik) {
        try {
            return carian.checkAccountByParam( daerah,  bpm, seksyen, kodJenishakmilik,noHakmilik);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<DokumenCarianPersendirian> senaraiDokumen(String idPengguna, String tarikhmula, String tarikhtamat) {
        try {
            return carian.senaraiDokumen(idPengguna, tarikhmula, tarikhtamat);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public List<DokumenCarianPersendirian> senaraiDokumenIdHakmilik(String idPengguna, String idHakmilik) {
try {
            return carian.senaraiDokumenIdHakmilik(idPengguna,null,null, idHakmilik);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }

    @Override
    public DokumenInfo muatturundokumen(Long id) {
        try {
            return carian.getdokumencarian(id);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String updateCarianAccount(String accountNo, String PAYMENT_TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String userName, String RECEIPT_NO, String namaPenyerah) {
        try {
                            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, "updateCarianAccount()"+accountNo);
            return carian.updateCarianAccount(accountNo,
                    PAYMENT_TRANS_ID, PAYMENT_DATETIME,
                    bd, userName,
                    RECEIPT_NO, namaPenyerah
            );
        } catch (ParseException ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CarianInfo> checkAccountStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) {
     try {
            return carian.checkAccountStrataByNoLot(daerah, bpm,seksyen,kodLot, noLot, noBangunan, noTingkat, noPetak);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }

    @Override
    public List<CarianInfo> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) {
         try {
            return carian.checkAccountStrataByParam( daerah,  bpm, seksyen, kodJenishakmilik,noHakmilik, noBangunan, noTingkat, noPetak);
        } catch (Exception ex) {
            Logger.getLogger(CarianPersendirianOnlineService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } }

    
}

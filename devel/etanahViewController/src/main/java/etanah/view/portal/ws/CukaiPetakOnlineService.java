/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Injector;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.cukaistrata.CukPetakOnlineService;
import etanah.view.portal.ws.form.CukaiStrataForm;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class CukaiPetakOnlineService implements ICukaiPetakOnlineService {

    private static Injector injector = etanahContextListener.getInjector();
    CukPetakOnlineService cukPetakOnlineService = injector.getInstance(CukPetakOnlineService.class);

    @Override
    public List<CukaiStrataForm> semakCukaiStrataIdHakmilik(String idHakmilik) {
        return cukPetakOnlineService.checkAccount(idHakmilik);    }

    @Override
    public List<CukaiStrataForm> semakCukaiStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak) {
        String idHakmilik = new String();
        return cukPetakOnlineService.checkAccountStrataByNoLot(daerah, bpm, seksyen, kodLot, noLot, noBangunan, noTingkat, noPetak);
    }

    @Override
    public List<CukaiStrataForm> semakCukaiStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) {
        return cukPetakOnlineService.checkAccountStrataByParam(daerah, bpm, seksyen, kodJenishakmilik, noHakmilik, noBangunan, noTingkat, noPetak);

    }

    @Override
    public String terimaBayaran(String accountNo, String PAYMENT_TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String userName, String RECEIPT_NO, String namaPenyerah) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

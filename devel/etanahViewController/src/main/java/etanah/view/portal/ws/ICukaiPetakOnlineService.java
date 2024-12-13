/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import etanah.view.portal.ws.form.CukaiStrataForm;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public interface ICukaiPetakOnlineService {
    
    List<CukaiStrataForm> semakCukaiStrataIdHakmilik(String idHakmilik);
    List<CukaiStrataForm> semakCukaiStrataByNoLot(String daerah, String bpm, String seksyen, String kodLot, Integer noLot, String noBangunan, String noTingkat, String noPetak);
    List<CukaiStrataForm> semakCukaiStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak);
    
    String terimaBayaran(String accountNo, String PAYMENT_TRANS_ID, String PAYMENT_DATETIME, BigDecimal bd, String userName, String RECEIPT_NO, String namaPenyerah);
}

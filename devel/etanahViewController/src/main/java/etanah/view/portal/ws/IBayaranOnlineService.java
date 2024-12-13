/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import etanah.view.dokumen.ws.DokumenInfo;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author faidzal
 */
public interface IBayaranOnlineService {

    public List<AccountInfo> checkAccount(String accountNo, String idHakmilik);

    public StatusLogin logMasuk(String idPengguna, String katalaluan);

    public void daftarPenggunaBaru(FormDaftarPengguna fd);
    
    public FormDaftarPengguna profilPengguna(String idPengguna);

    public List<SejarahTransaksiPortal> sejarahTransaksi(String idPengguna, String tarikhmula, String tarikhtamat);
    
    public List<UtilKod>listKodDaerah();
    
    public List<UtilKod>listKodMukim(String kodDaerah);
    
    public List<UtilKod>listKodHakmilik();

    public List<DokumenCarianPersendirian> senaraiDokumen(String idPengguna,String tarikhmula, String tarikhtamat);
    
    public DokumenInfo muatturundokumen(Long id);

    public String updateTransaction(String accountNo, BigDecimal jumlahBayaran, String idPengguna, String jenisTrans);

    public String updateUserAccount(String accountNo, BigDecimal bd, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String PAYMENT_TRANS_ID1, String userName);
    
    public String updateAccountPukal(List<AkaunForm> listAkaunForm, BigDecimal bd, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME, String PAYMENT_MODE, String PAYMENT_TRANS_ID1, String userName);

    public String updateCarianAccount(String accountNo, String PAYMENT_TRANS_ID, String TRANS_ID, String PAYMENT_DATETIME,BigDecimal bd, String PAYMENT_TRANS_ID1, String userName, String RECEIPT_NO);
    
    public Konfigurasi config(String key);
    
    public boolean lupaKataLaluan(String idPengguna);
}

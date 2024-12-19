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
 * @author john
 */
public interface ICarianPersendirianOnlineService {
    
    public List<UtilKod>listKodDaerah();
    
    public List<UtilKod>listKodMukim(String kodDaerah);
    
    public List<UtilKod>listKodSeksyen(String kodBpm);
    
    public List<UtilKod>listKodLot();
    
    public List<UtilKod>listKodHakmilik();
    
    public List<CarianInfo> checkAccount(String accountNo, String idHakmilik);
    
    public List<CarianInfo> checkAccountByNoLot(String daerah,String bpm,String seksyen,String kodLot,Integer noLot);
    
    public List<CarianInfo> checkAccountByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik);
    
    public List<CarianInfo> checkAccountStrataByNoLot(String daerah,String bpm,String seksyen,String kodLot,Integer noLot, String noBangunan, String noTingkat, String noPetak);
    
    public List<CarianInfo> checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak);
   
    public List<DokumenCarianPersendirian> senaraiDokumen(String idPengguna,String tarikhmula, String tarikhtamat);
    
    public List<DokumenCarianPersendirian> senaraiDokumenIdHakmilik(String idPengguna,String idHakmilik);
    
    public DokumenInfo muatturundokumen(Long id);    
    
    public String updateCarianAccount(String accountNo , String TRANS_ID, String PAYMENT_DATETIME,BigDecimal bd , String userName, String RECEIPT_NO,String namaPenyerah);
    

    

}

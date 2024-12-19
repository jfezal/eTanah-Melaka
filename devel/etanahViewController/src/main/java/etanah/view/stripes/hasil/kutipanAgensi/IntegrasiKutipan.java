/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author fikri
 */
public interface IntegrasiKutipan {

    public String uploadKutipanAgensi(String bytes, String filename, String kodAgensi);

    public List<AgensiView> sejarahTransaksi(String kodAgensi);

    public LoginInfo login(String idPengguna, String password);

    public String uploadFileKutipan(String bytes, String filename, String kodAgensi, String koddokumen, String format, String catatan);
    
    public List<DokumenInfo> senaraiDokumen(String kodAgensi);
    
    public LoginInfo kemaskiniProfil(LoginInfo loginInfo);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import etanah.view.etapp.ws.form.HakmilikPermohonanMyEtaPP;
import etanah.view.etapp.ws.form.LampiranMyEtaPP;
import etanah.view.etapp.ws.form.MaklumatHakmilikMyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek4MyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek8MyEtaPP;
import java.util.List;

/**
 *
 * @author faidzal
 */
public interface IntegrationEtappServices {

    public MaklumatPermohonan generatePermohonan(String kodUrusan,
            String nama,
            String alamat1,
            String alamat2,
            String alamat3,
            String alamat4,
            String kodNegeriP,
            String poskod,
            String idHakmilik,
            String kodNegeri) throws RuntimeException;

    public MaklumatHakmilikMyEtaPP maklumatHakmilik(String noResit, String idHakmilik);
    
    
    public String daftarPermohonanMyEtaPPBorangAMMk(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form,
            List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm,
            LampiranMyEtaPP mmkPaper,
            List<LampiranMyEtaPP> attachment);
    
    public String maklumatWartaBorangBMyEtaPP(String idPermohonan, 
            String tarikhWarta, 
            String noWarta,
            List<LampiranMyEtaPP> attachment);
    
    public String daftarPermohonanSek8MyEtaPP( MaklumatPermohonanSek8MyEtaPP maklumatPermohonan,
            List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm,
            LampiranMyEtaPP digitalcharting,
            List<LampiranMyEtaPP> attachment);

    public String borangCdanMMKMyEtaPP(String idPermohonan, 
            LampiranMyEtaPP mmkPaper,
            List<LampiranMyEtaPP> attchment) ;
    

    public String endorsBorangDMaklumatWartaMyEtaPP(String idPermohonan,
            String tarikhWarta,
            String noWarta,
            List<LampiranMyEtaPP> attchment);
    
    public String endorsBorangKMyEtaPP( String idPermohonan,
            String idHakmilik,
            String tarikhWarta,
           String noWarta,
           List<LampiranMyEtaPP> attchment);
    
    public String sijilPembebasanUkurMyEtaPP(String idPermohonan,
            String tarikh,
            String noPU,
            List<LampiranMyEtaPP> attchment);
    
    
    public String maklumanPenghantaranPUMyEtaPP( String idPermohonan,
            String tarikhHantar,
            String noRujukan,
            List<LampiranMyEtaPP> attchment);
    
    public String hakmilikSambungan(String idPermohonan, 
            String idHakmilik, 
            List<HakmilikSambunganFormEtapp> senaraiHakmilikSambungan);
    
    
    
    
    
//    public String uploadDokumenDMS(String idMohon, String tajuk, String convertBacktoNormalString, String kodDokumen, String fileName, String contentType, String catatan);
//
//    public String updateHakmilikAgensi(HakmilikAgensi hakmilikAgensi);
//
//    public etanah.view.etapp.htp.ws.Hakmilik getHakmilik(String idHakmilik);
//
//    public String terimaPerintah(PesakaDataSet1 pesakaDataSet1);
//    public MaklumatBayaran[] getMaklumatBayaran(MaklumatBayaran maklumatBayaran);
//       public MaklumatCukai getMaklumatBCukai(MaklumatCukai maklumatCukai);
}

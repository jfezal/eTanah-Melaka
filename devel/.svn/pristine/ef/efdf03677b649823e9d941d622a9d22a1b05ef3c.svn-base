package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shazwan 28052013 1335 PM @purpose : To Cater Data Based On Their
 * Urusan And Stages
 */
public class DisPermohonanData {

   

    public ArrayList getProposedByCasePBMT(int numUrusan, String stageID, String negeri, Permohonan p,DisLaporanTanahService disLaporanTanahService) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */
        ArrayList proposedList = new ArrayList();
        proposedList.clear();
        switch (Integer.valueOf(negeri)) {
            case 04: //MELAKA
                if (stageID.contentEquals("kemasukan")) {
                    Pemohon pemohon = new Pemohon();
                    pemohon = (Pemohon) disLaporanTanahService.findObject(pemohon, new String[]{p.getIdPermohonan()}, 0);
                    List<PihakPengarah> pihakPengarahList = new ArrayList<PihakPengarah>();
                    List<PemohonHubungan> pemohonHubunganList = new ArrayList<PemohonHubungan>();
                    PemohonHubungan pemohonHubunganBapa = new PemohonHubungan();
                    PemohonHubungan pemohonHubunganIbu = new PemohonHubungan();
                    if(pemohon==null){
                        proposedList.add("Sila Masukkan Maklumat Pemohon");
                    }else{
                        Pihak pihak = pemohon.getPihak();
                    
                        if(pihak==null){
                            proposedList.add("Sila Masukkan Maklumat Pemohon");
                        }else{
                            if(pihak.getJenisPengenalan().getKod().equals("U")||pihak.getJenisPengenalan().getKod().equals("D")||pihak.getJenisPengenalan().getKod().equals("S")){
                                pihakPengarahList = disLaporanTanahService.pihakService.findPengarahByIDPihak(pihak.getIdPihak());
                                if(pihakPengarahList.isEmpty()){
                                        proposedList.add("Sila Masukkan Maklumat Ahli Lembaga Pengarah");
                                }                                
                            }else if(pihak.getJenisPengenalan().getKod().equals("B")||pihak.getJenisPengenalan().getKod().equals("L")||pihak.getJenisPengenalan().getKod().equals("P")||pihak.getJenisPengenalan().getKod().equals("T")||pihak.getJenisPengenalan().getKod().equals("I")||pihak.getJenisPengenalan().getKod().equals("O")||pihak.getJenisPengenalan().getKod().equals("N")||pihak.getJenisPengenalan().getKod().equals("F")){
                                pemohonHubunganList = disLaporanTanahService.pelupusanService.findHubunganByIDPemohon(pemohon.getIdPemohon());
                                if(pemohon.getStatusKahwin().equals("Berkahwin")){
                                    if (pemohonHubunganList.isEmpty()) {
                                        proposedList.add("Sila Masukkan Maklumat Suami/Isteri");
                                    }
                                }
                                pemohonHubunganBapa = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubunganBapa,new String[]{String.valueOf(pemohon.getIdPemohon())},0);                                
                                pemohonHubunganIbu = (PemohonHubungan) disLaporanTanahService.findObject(pemohonHubunganIbu,new String[]{String.valueOf(pemohon.getIdPemohon())},1);
                                if (pemohonHubunganIbu == null || pemohonHubunganBapa == null) {
                                        proposedList.add("Sila Masukkan Maklumat Ibu dan Bapa");
                                }
                            }
                        }
                    }
                } else if (stageID.contentEquals("semak_surat_tolak")) {
                    
                } else if (stageID.contentEquals("g_charting_permohonan")) {
                    
                }
                break;
            case 05:
                //NEGERI SEMBILAN
                if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    
                } else if (stageID.contentEquals("sedia_surat_peringatan")) {
                    
                } 
                break;
        }
        return proposedList;
    }
}

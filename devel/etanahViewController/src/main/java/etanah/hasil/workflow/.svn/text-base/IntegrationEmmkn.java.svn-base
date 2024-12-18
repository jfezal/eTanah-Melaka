/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import etanah.emmkn.ws.EMMKNService;
import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.kodHasilConfig;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham
 */
public class IntegrationEmmkn implements StageListener {
    @Inject
    PelupusanService plpservice;
    @Inject
    kodHasilConfig hasilCfg;
    @Inject
    LaporanTanahDAO laporanTanahDAO;

    @Override
    public boolean beforeStart(StageContext context) {
       return true ;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true ;
    }

    @Override
    public void onGenerateReports(StageContext context) {

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = context.getPengguna();
        List<PermohonanKertasKandungan> mohonKertasKandunganList = null;
        PermohonanKertas mohonKertas = null;
//        List<PemohonHubungan> pemohonHubunganList = null;
//        List<PemohonHubungan> pemohonHubunganList2 = null;
        PermohonanKertasKandungan mohonKertasKandungan;
        Pemohon pemohon = null;
        //PemohonHubungan pemohonHubunganTemp;
         LaporanTanah mohonLaporTanah = null ;
        String result = null;
        EMMKNService service = new EMMKNService();

        mohonKertas = plpservice.findByIdKrts(permohonan.getIdPermohonan(), "MMK" + permohonan.getIdPermohonan());
        mohonKertasKandunganList = mohonKertas.getSenaraiKandungan();

        pemohon = plpservice.findPemohonByIdPemohon(permohonan.getIdPermohonan());
        

         String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            List<LaporanTanah> listLaporanTanah;
            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);
             if (!(listLaporanTanah.isEmpty())) {
                mohonLaporTanah = listLaporanTanah.get(0);
             }



        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");

        if (mohonKertasKandunganList != null) {
            for (int a = 0; a < mohonKertasKandunganList.size(); a++) {
                mohonKertasKandungan = mohonKertasKandunganList.get(a);

                int caw = Integer.parseInt(mohonKertasKandungan.getCawangan().getKod());
                if(mohonKertasKandungan.getBil() == 3){
                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
                }
                if(mohonKertasKandungan.getBil() == 8){
                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
                }
                if(mohonKertasKandungan.getBil() == 9){
                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
                }
                if(mohonKertasKandungan.getBil() == 10){
                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
                }
                if(mohonKertasKandungan.getBil() == 11){
                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
                }

            }
        }

//        //Isteri atau Suami
//        if (!pemohonHubunganList.isEmpty()) {
//            String alamat = "";
//            for (int a = 0; a < pemohonHubunganList.size(); a++) {
//                pemohonHubunganTemp = pemohonHubunganList.get(a);
//                alamat = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + ".";
//                service.addTanggungan(alamat, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
//                        pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
//
//            }
//        }
//
//        // Ibu dan Bapa
//        if (!pemohonHubunganList2.isEmpty()) {
//            String alamatIbuBapa = "";
//            for (int a = 0; a < pemohonHubunganList2.size(); a++) {
//                pemohonHubunganTemp = pemohonHubunganList2.get(a);
//                alamatIbuBapa = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + ".";
//                service.addTanggungan(alamatIbuBapa, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
//                        pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
//            }
//        }

        if (pemohon != null) {
            String alamatPjabt = pemohon.getInstitusiAlamat1() + "," + pemohon.getInstitusiAlamat2() + "," + pemohon.getInstitusiAlamat3() + "," + pemohon.getInstitusiAlamat4() + ".";
            String alamatRmh = pemohon.getPihak().getAlamat1() + "," + pemohon.getPihak().getAlamat2() + "," + pemohon.getPihak().getAlamat3() + "," + pemohon.getPihak().getAlamat4() + ".";
            if (alamatPjabt.equals(",,,.")) {
                alamatPjabt = "Tiada";
            }
            boolean anak = false;
            if (pemohon.getPihak().getAnakTempatan() == 'Y') {
                anak = true;
            }
            if (pemohon.getPihak().getBangsa().getKod().equals("T")) {
                pemohon.getPihak().getBangsa().setKod("LN");
            }

            service.addIndividual(alamatPjabt, alamatRmh, anak, pemohon.getPihak().getEmail(),
                    pemohon.getPihak().getBangsa().getKod(), pemohon.getPihak().getKodJantina(), pemohon.getPihak().getWargaNegara().getKod(),
                    pemohon.getTempohMastautin(), pemohon.getPihak().getNama(), pemohon.getPihak().getNoPengenalan(), pemohon.getPihak().getNoTelefonBimbit(),
                    pemohon.getPekerjaan(), pemohon.getPendapatan(), pemohon.getPihak().getTempatLahir(), pemohon.getUmur().toString());

        }

        HakmilikPermohonan hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
        service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod() + "", hakmilik.getNoHakmilik(),
                hakmilik.getNoLot(), "");

        String kategoriPermohonan = permohonan.getKodUrusan().getKod();
        String kod = "";
        if (kategoriPermohonan.equals("REMRI")) {
            kod = hasilCfg.getProperty("mmknREMRI");
        } else if (kategoriPermohonan.equals("PRCT")) {
            kod = hasilCfg.getProperty("mmknPRCT");
        } else if (kategoriPermohonan.equals("PPDL")) {
            kod = hasilCfg.getProperty("mmknPPDL");
        } else if (kategoriPermohonan.equals("RPPP")) {
            kod = hasilCfg.getProperty("mmknRPPP");
        }else if (kategoriPermohonan.equals("PPPT")) {
            kod = hasilCfg.getProperty("mmknPPPT");
        }
        service.addLandApplication(kod, hakmilik.getKategoriTanah().getKod());


           String tanahB = "" ;
        String tanahS = "" ;
        String tanahT = "" ;
        String tanahU = "" ;
        if(mohonLaporTanah.getSempadanBaratMilikKerajaan() != null){
            if(mohonLaporTanah.getSempadanBaratMilikKerajaan() == 'Y'){
            tanahB = "Tanah Kerajaan" ;
            }
            else {
                if(StringUtils.isBlank(mohonLaporTanah.getSempadanBaratNoLot() )){
                    tanahB = "Nombor Lot Tiada" ;
                }
                else {
                    
                    tanahB = "Nombor Lot " + mohonLaporTanah.getSempadanBaratNoLot() ;
                }
            }
        }
         if(mohonLaporTanah.getSempadanSelatanMilikKerajaan() != null){
            if(mohonLaporTanah.getSempadanSelatanMilikKerajaan() == 'Y'){
            tanahS = "Tanah Kerajaan" ;
            }
            else {
                if(StringUtils.isBlank(mohonLaporTanah.getSempadanSelatanNoLot())){
                    tanahS = "Nombor Lot Tiada" ;
                }
                else {
                    tanahS = "Nombor Lot " + mohonLaporTanah.getSempadanSelatanNoLot() ;
                }

            }
        }
         if(mohonLaporTanah.getSempadanTimurMilikKerajaan() != null){
            if(mohonLaporTanah.getSempadanTimurMilikKerajaan() == 'Y'){
            tanahT = "Tanah Kerajaan" ;
            }
            else {
                if(StringUtils.isBlank(mohonLaporTanah.getSempadanTimurNoLot())){
                    tanahT = "Nombor Lot Tiada" ;
                }
                else{
                    tanahT = "Nombor Lot " + mohonLaporTanah.getSempadanTimurNoLot() ;
                }

            }
        }
         if(mohonLaporTanah.getSempadanUtaraMilikKerajaan() != null){
            if(mohonLaporTanah.getSempadanUtaraMilikKerajaan() == 'Y'){
            tanahU = "Tanah Kerajaan" ;
            }
            else {
                if(StringUtils.isBlank(mohonLaporTanah.getSempadanUtaraNoLot())){
                    tanahU = "Nombor Lot Tiada" ;
                }
                else{
                    tanahU = "Nombor Lot " + mohonLaporTanah.getSempadanUtaraNoLot() ;
                }

            }
        }
        String meter = "" ;
        String kaki = "" ;
        if(hakmilik.getKodUnitLuas().getKod() != null){
            if(hakmilik.getKodUnitLuas().getKod().equals("K")){
                 meter = hakmilik.getLuas().toString() ;
                 kaki = null ;
            }
            else{
                meter = null ;
                 kaki = hakmilik.getLuas().toString() ;
            }

        }
        else{
            meter = null ;
            kaki = null ;
        }



        service.addLandDetails(tanahB, mohonLaporTanah.getSempadanBaratKegunaan() , hakmilikPermohonan.getJarakDari(), "", mohonLaporTanah.getKeadaanTanah(),
                hakmilik.getLokasi(), "", "", kaki, meter, "",
                tanahS, mohonLaporTanah.getSempadanSelatanKegunaan(), "", tanahT, mohonLaporTanah.getSempadanTimurKegunaan(), tanahU, mohonLaporTanah.getSempadanUtaraKegunaan());

        String tajuk = permohonan.getKodUrusan().getNama().toUpperCase() + " BAGI HAKMILIK " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik() + " "
                + hakmilik.getLot().getKod() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama();
         String sks = "" ;
        if(hakmilik.getSeksyen().getNama() != null){
            sks = hakmilikPermohonan.getKodSeksyen().getNama() ;
        }
        service.createRisalat(permohonan.getKodUrusan().getRujukanKanun(), hakmilik.getBandarPekanMukim().getNama(), hakmilik.getDaerah().getKod(),
                permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks,
                tajuk);

        result = service.sendData();

        if (!"SUCCESS".equals(result)) {
            context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
            context.addMessage("Message : " + service.getStatusMessage());
            return null;
        } else {
            context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {

    }

 @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}

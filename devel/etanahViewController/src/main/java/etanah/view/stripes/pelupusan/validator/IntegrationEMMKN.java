package etanah.view.stripes.pelupusan.validator ;


import etanah.emmkn.ws.EMMKNService;
import etanah.model.Permohonan;
import java.math.BigDecimal;

import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import java.util.List;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.commons.lang.StringUtils;


/**
 *
 * afham
 *
 */

public class IntegrationEMMKN implements StageListener {
    @Inject
    PelupusanService plpservice ;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    LaporanTanahDAO laporanTanahDAO ;
    
    

    @Override
    public boolean beforeStart(StageContext context) {
        return true ;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan ;
    HakmilikPermohonan hakmilikPermohonan = null ;
    LaporanTanah mohonLaporTanah = null ;
    Pemohon pemohon = null ;
    Pihak pihak = null ;
    List<PemohonHubungan> pemohonHubunganList = null;
    List<PemohonHubungan> pemohonHubunganList1 = null;
    List<PemohonHubungan> pemohonHubunganList2 = null;
     FasaPermohonan fasaPermohonanptd = null ;
    FasaPermohonan fasaPermohonanptg = null ;
    List<PermohonanRujukanLuar> mohonRujukLuarList = null ;
    List<PermohonanKertasKandungan> mohonKertasKandunganList = null ;
    PermohonanRujukanLuar mohonRujukLuar ;
    PemohonHubungan pemohonHubunganTemp ;
    PermohonanKertas mohonKertas = null ;
    String result = null ;
    PermohonanKertasKandungan mohonKertasKandungan ;
    PermohonanPermitItem permitItem = null ;



        permohonan = context.getPermohonan() ;
        String idPermohonan = permohonan.getIdPermohonan() ;
        if(idPermohonan != null){
            hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan) ;
            mohonKertas = plpservice.findKertasByKod(idPermohonan, "RMN") ;
            mohonKertasKandunganList = mohonKertas.getSenaraiKandungan() ;
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

             if (!(listLaporanTanah.isEmpty())) {
                mohonLaporTanah = listLaporanTanah.get(0);
             }

             pemohon = plpservice.findPemohonByIdPemohon(idPermohonan);
            if (pemohon != null) {
                pihak = pemohon.getPihak();
            }

            if (pemohon != null) {

                pemohonHubunganList =  plpservice.findHubunganByIDPemohon(pemohon.getIdPemohon());
                pemohonHubunganList1 = plpservice.findHubunganByIDANAK(pemohon.getIdPemohon());
                pemohonHubunganList2 = plpservice.findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());

            }

            fasaPermohonanptd = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "perakuan_ptd_risalat_mmkn") ;
            fasaPermohonanptg = plpservice.findMohonFasaByIdMohonIdPengguna(idPermohonan, "perakuan_ptg_risalat_mmkn") ;
            mohonRujukLuarList = plpservice.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan) ;




        }
        String html = null;

        EMMKNService service = new EMMKNService();

        /* PLEASE FOLLOW THE SEQUENCE WHEN ADDING THE METHODS */

        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
//         service.setUserAndPassword("mohdtaib", "password01");

        //Set Ulasan for Jabatan Teknikal, PTD and PTD
        /*  'T' for ulasan Jabatan Teknikal
            'D' for ulasan PTD
            'G' for ulasan PTG */
        //Ulasan PTD
        if(fasaPermohonanptd != null){
            int caw = Integer.parseInt(permohonan.getCawangan().getKod());
            //caw = caw + 1 ;
            service.addUlasan("D", caw+"", fasaPermohonanptd.getUlasan());
        }
//        if(mohonKertasKandunganList != null){
//        for(int z = 0 ; z < mohonKertasKandunganList.size() ; z++){
//            int caw = Integer.parseInt(permohonan.getCawangan().getKod());
//            //caw = caw + 1 ;
//                mohonKertasKandungan = mohonKertasKandunganList.get(z);
//                if(mohonKertasKandungan.getBil() == 3){
//                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
//                }
//                if(mohonKertasKandungan.getBil() == 8){
//                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
//                }
//                if(mohonKertasKandungan.getBil() == 9){
//                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
//                }
//                if(mohonKertasKandungan.getBil() == 10){
//                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
//                }
//                if(mohonKertasKandungan.getBil() == 11){
//                service.addUlasan("D", caw+"", mohonKertasKandungan.getKandungan());
//                }
//            }
//        }
        //Ulasan PTG
        int k = 0 ;
         if(fasaPermohonanptg != null){
             service.addUlasan("G", k+"", fasaPermohonanptg.getUlasan());
         }
//        for(int z = 0 ; z < mohonKertasKandunganList.size() ; z++){
//            mohonKertasKandungan = mohonKertasKandunganList.get(z);
//            if(mohonKertasKandungan.getBil() == 5){
//                service.addUlasan("G", k+"", mohonKertasKandungan.getKandungan());
//            }
//        }
        //Ulasan Jabatan Teknikal
        if(mohonRujukLuarList != null){
            for(int i = 0 ; i < mohonRujukLuarList.size() ; i++){
                mohonRujukLuar = mohonRujukLuarList.get(i) ;
                if(mohonRujukLuar.getUlasan() != null){
                    service.addUlasan("T", "2", mohonRujukLuar.getUlasan());
                }
                
            }
        }
        //Isteri atau Suami
        if(!pemohonHubunganList.isEmpty()){
            String test = "" ;
            for(int a = 0 ; a < pemohonHubunganList.size() ; a++){
                pemohonHubunganTemp = pemohonHubunganList.get(a);
                test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + "." ;
                service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                 pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());

            }

        }
        // Ibu dan Bapa
        if(!pemohonHubunganList2.isEmpty()){
            String test = "" ;
            for(int a = 0 ; a < pemohonHubunganList2.size() ; a++){
                pemohonHubunganTemp = pemohonHubunganList2.get(a);
                test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + "." ;
                service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                 pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
            }
        }
     //    Anak-anak
         if(!pemohonHubunganList1.isEmpty()){
            String test = "" ;
            for(int a = 0 ; a < pemohonHubunganList1.size() ; a++){
                pemohonHubunganTemp = pemohonHubunganList1.get(a);
                test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + "." ;
                service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                 pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
            }
        }



        //Set individual applicant
        if(pemohon != null){
            String alamatPjabt = pemohon.getInstitusiAlamat1() + "," + pemohon.getInstitusiAlamat2() + "," + pemohon.getInstitusiAlamat3() + "," + pemohon.getInstitusiAlamat4() + "." ;
            String alamatRmh = pihak.getAlamat1() + "," + pihak.getAlamat2() + "," + pihak.getAlamat3() + "," + pihak.getAlamat4() + "." ;
            if(alamatPjabt.equals(",,,.")){
                alamatPjabt = "Tiada" ;
            }
            boolean anak ;
            if(pihak.getAnakTempatan() == 'Y'){
                anak = true ;
            }
            else{
                anak = false ;
            }

            service.addIndividual(alamatPjabt, alamatRmh, anak, pihak.getEmail(),
                pihak.getBangsa().getKod(), pihak.getKodJantina() ,pihak.getWargaNegara().getKod() , pemohon.getTempohMastautin(), pihak.getNama(), pihak.getNoPengenalan(), pihak.getNoTelefonBimbit(),
                pemohon.getPekerjaan(), pemohon.getPendapatan(), pihak.getTempatLahir(), pemohon.getUmur().toString());

        }
        String kodLot = "" ;
        String noLot = "" ;
//        if(StringUtils.isBlank(hakmilikPermohonan.getLot().getNama())){
//            System.out.println("Tiada Kod Lot") ;
//        }
//        else{
//            kodLot =  hakmilikPermohonan.getLot().getNama() ;
//        }
//
//        if(StringUtils.isNotBlank(hakmilikPermohonan.getNoLot())){
//            noLot = hakmilikPermohonan.getNoLot() ;
//        }
//        else{
//             System.out.println("Tiada No Lot") ;
//        }
        //Set lot information
        service.addLotInformation(null, String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod()), "", "L", "0" );

        //Set land application
       
//        if(context.getPermohonan().getKodUrusan().getKod().equals("PLPS")){
//            PermohonanPermitItem ppi = plpservice.findByIdMohonPermitItem(idPermohonan);
//            main = ppi.getKodItemPermit().getKod() ;
//        }
//        else if(context.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
//            main = hakmilikPermohonan.getKategoriTanahBaru().getKod() ;
//        }
        service.addLandApplication("GRANT", "4");

        //Set land general details
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
        if(hakmilikPermohonan.getKodUnitLuas() != null){
            if(hakmilikPermohonan.getKodUnitLuas().getKod().equals("K")){
                 meter = hakmilikPermohonan.getLuasTerlibat().toString() ;
                 kaki = null ;
            }
            else{
                meter = null ;
                 kaki = hakmilikPermohonan.getLuasTerlibat().toString() ;
            }

        }
        else{
            meter = null ;
            kaki = null ;
        }



        service.addLandDetails(tanahB, mohonLaporTanah.getSempadanBaratKegunaan() , hakmilikPermohonan.getJarakDari(), "", mohonLaporTanah.getKeadaanTanah(),
                hakmilikPermohonan.getLokasi(), "", "", kaki, meter, "",
                tanahS, mohonLaporTanah.getSempadanSelatanKegunaan(), "", tanahT, mohonLaporTanah.getSempadanTimurKegunaan(), tanahU, mohonLaporTanah.getSempadanUtaraKegunaan());
        String sks = "" ;
        if(hakmilikPermohonan.getKodSeksyen() != null){
            sks = hakmilikPermohonan.getKodSeksyen().getNama() ;
        }
        //Set kertas risalat
        service.createRisalat("", hakmilikPermohonan.getBandarPekanMukimBaru().getNama(), permohonan.getCawangan().getDaerah().getKod(),
                permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks,
                mohonKertas.getTajuk());

        //Invoke the service and send the data
        result = service.sendData();

         if(!"SUCCESS".equals(result)){
             context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
             context.addMessage("Message : " + service.getStatusMessage());
            return null;
        }
         else{
            context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
         }
      //  if (html == null) html = "Please check the log for more information about the status.";
        //return null ;
       return proposedOutcome ;
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
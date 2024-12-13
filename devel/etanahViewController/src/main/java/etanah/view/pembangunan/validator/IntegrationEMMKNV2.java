/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import etanah.emmkn.ws.EMMKNService;
import etanah.model.Permohonan;
import java.math.BigDecimal;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.report.ReportUtilMMKN;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.SemakDokumenService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.List;
import java.util.Calendar;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * Shazwan 23 April 2012 11:41 AM
 *
 */
public class IntegrationEMMKNV2 implements StageListener {

    @Inject
    PelupusanService plpservice;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    ReportUtilMMKN reportUtilMMKN;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOGGER = Logger.getLogger(IntegrationEMMKNV2.class);
    private static Pengguna pengguna;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    public int settingUrusan(String kodUrusan) {
        int numUrusan = kodUrusan.equals("PPRU") ? 1
                : kodUrusan.equals("BMBT") ? 2
                : kodUrusan.equals("PPTR") ? 3
                : kodUrusan.equals("LMCRG") ? 4
                : kodUrusan.equals("PJLB") ? 5
                : kodUrusan.equals("PTGSA") ? 6
                : kodUrusan.equals("MCMCL") ? 7
                : kodUrusan.equals("MMMCL") ? 8
                : kodUrusan.equals("PHLP") ? 9
                : kodUrusan.equals("RYKN") ? 10
                : kodUrusan.equals("PBPTD") ? 11
                : kodUrusan.equals("PLPS") ? 12
                : kodUrusan.equals("PLPTD") ? 13
                : kodUrusan.equals("RLPS") ? 14
                : kodUrusan.equals("PBMT") ? 15
                : kodUrusan.equals("PPBB") ? 16
                : kodUrusan.equals("PBPTG") ? 17
                : kodUrusan.equals("RAYK") ? 18
                : kodUrusan.equals("PBRZ") ? 19
                : kodUrusan.equals("PRIZ") ? 20
                : kodUrusan.equals("PTNB") ? 21
                : kodUrusan.equals("SEK4") ? 22
                : kodUrusan.equals("SEK4A") ? 23
                : kodUrusan.equals("PTSP") ? 24
                : kodUrusan.equals("127") ? 25
                : kodUrusan.equals("425") ? 26
                : kodUrusan.equals("425A") ? 27
                : kodUrusan.equals("PBSK") ? 28
                : kodUrusan.equals("RLTB") ? 29
                : kodUrusan.equals("RPMMK") ? 30
                : kodUrusan.equals("RPP") ? 31
                : 0;
        return numUrusan;
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        System.out.println("::::::::" + context.getPermohonan().getIdPermohonan());
        Permohonan permohonan;
//        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
        LaporanTanah mohonLaporTanah = new LaporanTanah();
        Pemohon pemohon = new Pemohon();
        Pihak pihak = new Pihak();
        List<PemohonHubungan> pemohonHubunganList = new ArrayList<PemohonHubungan>();
        List<PemohonHubungan> pemohonHubunganList1 = new ArrayList<PemohonHubungan>();
        List<PemohonHubungan> pemohonHubunganList2 = new ArrayList<PemohonHubungan>();
        FasaPermohonan fasaPermohonanptd = new FasaPermohonan();
        FasaPermohonan fasaPermohonanptg = new FasaPermohonan();
        List<PermohonanRujukanLuar> mohonRujukLuarList = new ArrayList<PermohonanRujukanLuar>();
        List<PermohonanKertasKandungan> mohonKertasKandunganList = new ArrayList<PermohonanKertasKandungan>();
        List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1 = new ArrayList<PermohonanKertasKandungan>();
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        PemohonHubungan pemohonHubunganTemp = new PemohonHubungan();
        PermohonanKertas mohonKertas = new PermohonanKertas();
        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
        String result = new String();
        PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
        PermohonanPermitItem permitItem = new PermohonanPermitItem();
        List<LaporanTanah> listLaporanTanah = new ArrayList<LaporanTanah>();
        List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah = new ArrayList<PermohonanKertasKandungan>();
        List<String[]> landApplicantList = new ArrayList<String[]>();
        permohonan = context.getPermohonan();
        if (permohonan.getIdPermohonan() != null) {
            int numUrusan = settingUrusan(permohonan.getKodUrusan().getKod());
            if (numUrusan > 0) {
                List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//                if(hakmilikPermohonanList.size()==1){
//                    hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);                
//                }
                String drafType = new String();
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
                    drafType = "MMKN";
                } else {
                    drafType = "MMKN";
                }
                mohonKertas = (PermohonanKertas) disLaporanTanahService.findObject(mohonKertas, new String[]{permohonan.getIdPermohonan(), drafType}, 0);
                mohonKertasKand = disLaporanTanahService.getPelupusanService().findByBilNIdKertasNSubtajuk(6, mohonKertas.getIdKertas(), "");
                mohonKertasKandunganList = mohonKertas.getSenaraiKandungan();
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};
                listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);
                if (!(listLaporanTanah.isEmpty())) {
                    mohonLaporTanah = listLaporanTanah.get(0);
                }

                if(!permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBSK")){
                if (permohonan.getPermohonanSebelum() != null) {
                    LOGGER.info("id sebelum :" + permohonan.getPermohonanSebelum().getIdPermohonan());
                    Permohonan p = permohonan.getPermohonanSebelum();
                    if (p != null) {
                        List<Pemohon> listPemohon = p.getSenaraiPemohon();
                        if (listPemohon.size() != 0) {
                            pemohon = listPemohon.get(0);
                            LOGGER.info("::: pemohon is not empty :::");
                        }

                    }
                }}
                
                if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBSK")){
                if (permohonan.getIdPermohonan() != null) {
                    LOGGER.info("id sebelum :" + permohonan.getIdPermohonan());
                    Permohonan p = permohonan;
                    if (p != null) {
                        List<Pemohon> listPemohon = p.getSenaraiPemohon();
                        if (listPemohon.size() != 0) {
                            pemohon = listPemohon.get(0);
                            LOGGER.info("::: pemohon is not empty :::");
                        }

                    }
                }}
                
                LOGGER.info("pemohon : " + pemohon);
                //pemohon = plpservice.findPemohonByIdPemohon(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (pemohon != null) {
                    pihak = pemohon.getPihak();
                }
                if (pemohon != null) {

                    pemohonHubunganList = plpservice.findHubunganByIDPemohon(pemohon.getIdPemohon());
                    pemohonHubunganList1 = plpservice.findHubunganByIDANAK(pemohon.getIdPemohon());
                    pemohonHubunganList2 = plpservice.findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());

                }
//                mohonRujukLuarList = plpservice.getSenaraiRujLuarByIDPermohonanAgensi(permohonan.getIdPermohonan());
                mohonRujukLuarList = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(permohonan.getIdPermohonan());
                String html = null;
                EMMKNService service = new EMMKNService();

                /* PLEASE FOLLOW THE SEQUENCE WHEN ADDING THE METHODS 
                 * SET USERNAME AND PASSWORD
                 * ->service.setUserAndPassword("mohdtaib", "password01");
                 * 
                 * SET ULASAN FOR JABATAN TEKNIKAL, PTD AND PTG
                 * -> T - ULASAN JABATAN TEKNIKAL
                 * -> D - ULASAN PTD
                 * -> G - ULASAN PTG
                 */
                service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
                /*
                 * SETTING LAND APPLICANT
                 * landApplicant[0] refer to etanah TYPE exp : GRANT,TEMP_LISCENSE etc..
                 * landApplicant[1] refer TO KOD_KATG_TANAH.KOD..if hakmilik null set to 4 (Tiada)
                 */
                String[] landApplicant = new String[2];
                String kategoriTanah = new String();
                for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
                    if (hakmilikMohon.getHakmilik() != null) {
                        kategoriTanah = hakmilikMohon.getHakmilik().getKategoriTanah().getKod();
                    } else {
                        kategoriTanah = "4";
                    }
                    /*
                     * DYNAMIC LAND APPLICATION FOR MULTIPLE HAKMILIK
                     */
                    switch (numUrusan) {
                        case 1: //PPRU
                            landApplicant[0] = "PERMIT";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 2: //BMBT
                            landApplicant[0] = "STRATUM_DISPOSAL";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 3: //PPTR
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 4: //LMCRG                        
                            landApplicant[0] = "DISCOVER_DISPOSAL";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 5: //PJLB                        
                            landApplicant[0] = "QUARRY_DISPOSAL";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 6: //PTGSA                        
                            landApplicant[0] = "GSA_DISPOSAL";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 7: //MCMCL
                            //NOT APPLICABLE
                            break;
                        case 8: //MMMCL
                            //NOT APPLICABLE
                            break;
                        case 9: //PHLP
                            //NOT APPLICABLE
                            break;
                        case 10: //RYKN
                            //NOT APPLICABLE (N9 ONLY)
                            break;
                        case 11: //PBPTD
                            //NOT APPLICABLE
                            break;
                        case 12: //PLPS
                            landApplicant[0] = "TEMP_LISCENSE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 13: //PLPTD
                            //NOT APPLICABLE (N9 ONLY)
                            break;
                        case 14: //RLPS
                            break;
                        case 15: //PBMT
                            landApplicant[0] = "GRANT";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 16: //PPBB
                            //NOT APPLICABLE
                            break;
                        case 17: //PBPTG
                            //NOT APPLICABLE
                            break;
                        case 18: //RAYK                        
                            landApplicant[0] = "LESS_PREMIUM";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 19: //PBRZ                        
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 20: //PRIZ                        
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 21: //PTNB
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 22: //SEK4
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 23: //SEK4A
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 24: //PTSP
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 25: //127
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 26: //425
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 27: //425A
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 28: //PBSK
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 29: //RLTB
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 30: //RPMMK
                            landApplicant[0] = "RESERVE";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                        case 31: //RPP
                            landApplicant[0] = "LESS_PREMIUM";
                            landApplicant[1] = kategoriTanah;
                            //NOT APPLICABLE
                            break;
                    }
                    landApplicantList.add(landApplicant);
                }


                switch (numUrusan) {

                    case 1: //PPRU
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 2: //BMBT
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 3: //PPTR
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 4: //LMCRG                        
//                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 10); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 5: //PJLB                        
//                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 10); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN        
                        break;
                    case 6: //PTGSA                        
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //FOR No 5 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //For No6 PERAKUAN PENTADBIR TANAH DAN GALIAN           
                        break;
                    case 7: //MCMCL
                        //NOT APPLICABLE
                        break;
                    case 8: //MMMCL
                        //NOT APPLICABLE
                        break;
                    case 9: //PHLP
                        //NOT APPLICABLE
                        break;
                    case 10: //RYKN
                        //NOT APPLICABLE (N9 ONLY)
                        break;
                    case 11: //PBPTD
                        //NOT APPLICABLE
                        break;
                    case 12: //PLPS
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN 
                        break;
                    case 13: //PLPTD
                        //NOT APPLICABLE (N9 ONLY)
                        break;
                    case 14: //RLPS
                        break;
                    case 15: //PBMT
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN 
                        break;
                    case 16: //PPBB
                        //NOT APPLICABLE
                        break;
                    case 17: //PBPTG
                        //NOT APPLICABLE
                        break;
                    case 18: //RAYK                        
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN 
                        break;
                    case 19: //PBRZ                        
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN 
                        break;
                    case 20: //PRIZ                        
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN 
                        break;
                    case 21: //PTNB
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 3); //FOR No 3 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 4); //For No4 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 22: //SEK4
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //FOR No 6 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 23: //SEK4A
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //FOR No 6 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 8); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 24: //PTSP
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 7); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 9); //For No9 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 25: //127
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 3); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No9 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 26: //425
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 3); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No9 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 27: //425A
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 3); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No9 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;

                    case 28: //PBSK
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 3); //FOR No 4 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 4); //For No5 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 29: //RLTB
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 4); //FOR No 4 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No5 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 30: //RPMMK
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 4); //FOR No 4 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No5 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 31: //RPP
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 4); //FOR No 4 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //For No5 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                }
                LOGGER.info("senarai ulasan PTD :::::" + senaraiLaporanKandunganpbtanah.size());
                LOGGER.info("senarai ulasan PTG :::::" + senaraiLaporanKandunganptg1.size());

                /*
                 * SETTING ULASAN PTD
                 */
                for (PermohonanKertasKandungan pkk : senaraiLaporanKandunganpbtanah) {
                    service.addUlasan("D", Integer.parseInt(permohonan.getCawangan().getKod()) + "", pkk.getKandungan());
                }

                /*
                 * SETTING ULASAN PTG
                 */
                for (PermohonanKertasKandungan pkk : senaraiLaporanKandunganptg1) {
                    service.addUlasan("G", 0 + "", pkk.getKandungan());
                }

                /*
                 * SETTING ULASAN JABATAN TEKNIKAL
                 * service.addUlasan(A, B, C);
                 * A-> T(JABATAN TEKNIKAL)
                 * B-> Kod JABATAN in MMKN
                 * C-> MOHONRUJLUAR ( ULASAN)
                 */
                if (!(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTSP"))) {
                    if (mohonRujukLuarList != null) {
                        for (PermohonanRujukanLuar prl : mohonRujukLuarList) {
                            if (prl.getUlasan() != null) {
                                if (prl.getAgensi() != null && prl.getAgensi().getKategoriAgensi().getKod().equals("JTK")) {
                                    service.addUlasan("T", prl.getAgensi().getKodEmmkn(), prl.getUlasan());
                                }
                            }
                        }
                    }
                }

                /*
                 * SETTING SUAMI ISTERI
                 */
                if (!pemohonHubunganList.isEmpty()) {
                    String test = "";
                    for (int a = 0; a < pemohonHubunganList.size(); a++) {
                        pemohonHubunganTemp = pemohonHubunganList.get(a);
                        test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + ".";
                        service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                                pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());

                    }
                }

                /*
                 * SETTING IBU DAN BAPA
                 */
                if (!pemohonHubunganList2.isEmpty()) {
                    String test = "";
                    for (int a = 0; a < pemohonHubunganList2.size(); a++) {
                        pemohonHubunganTemp = pemohonHubunganList2.get(a);
                        test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + ".";
                        service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                                pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
                    }
                }

                /*
                 * SETTING ANAK-ANAK
                 */
                if (!pemohonHubunganList1.isEmpty()) {
                    String test = "";
                    for (int a = 0; a < pemohonHubunganList1.size(); a++) {
                        pemohonHubunganTemp = pemohonHubunganList1.get(a);
                        test = pemohonHubunganTemp.getAlamat1() + "," + pemohonHubunganTemp.getAlamat2() + "," + pemohonHubunganTemp.getAlamat3() + "," + pemohonHubunganTemp.getAlamat4() + ".";
                        service.addTanggungan(test, pemohonHubunganTemp.getKaitan(), pemohonHubunganTemp.getNama(), pemohonHubunganTemp.getNoPengenalan(),
                                pemohonHubunganTemp.getPekerjaan(), pemohonHubunganTemp.getGaji());
                    }
                }

                /*
                 * SETTING INDIVIDU APPLICANT
                 */
                if (pemohon != null) {
                    String alamatPjabt = pemohon.getInstitusiAlamat1() + "," + pemohon.getInstitusiAlamat2() + "," + pemohon.getInstitusiAlamat3() + "," + pemohon.getInstitusiAlamat4() + ".";
                    System.out.println("::::::::::::remove");
//                    String alamatRmh = pihak.getAlamat1() + "," + pihak.getAlamat2() + "," + pihak.getAlamat3() + "," + pihak.getAlamat4() + ".";
                    if (alamatPjabt.equals(",,,.")) {
                        alamatPjabt = "Tiada";
                    }
//                    boolean anak;
//                    
//                    if (pihak.getAnakTempatan() != null) {
//                        if (pihak.getAnakTempatan() == 'Y') {
//                            anak = true;
//                        } else {
//                            anak = false;
//                        }
//                    } else {
//                        anak = true; // TEMPORARY
//                    }

                    String alamat = "";
                    String email = "";
                    String namaSyarikat = pemohon.getNama();
                    String ekutiSyarikatAsing = null;
                    String ekutiSyarikatBukanBumiputera = null;
                    String ekutiSyarikatBumiputra = null;
                    BigDecimal modalBerbayar = null;
                    BigDecimal modalPusingan = null;
                    String noPendaftaran = "";
                    String noTelefon = "";
                    if (pemohon.getPihak() != null) {
                        alamat = pemohon.getPihak().getAlamat1() + ", "
                                + pemohon.getPihak().getAlamat2() + ", "
                                + pemohon.getPihak().getAlamat3() + ", "
                                + pemohon.getPihak().getAlamat4() + ", "
                                + pemohon.getPihak().getPoskod() + ", "
                                + pemohon.getPihak().getNegeri() + ".";

                        email = pemohon.getPihak().getEmail();
                        namaSyarikat = pemohon.getPihak().getNama();
                        noPendaftaran = pemohon.getPihak().getNoPengenalan();
                        noTelefon = pemohon.getPihak().getNoTelefon1();

                        Calendar c1 = Calendar.getInstance();
                        try {
                            DatatypeFactory df = DatatypeFactory.newInstance();
                            Date date = pemohon.getPihak().getInfoAudit().getTarikhMasuk();
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTimeInMillis(date.getTime());
                            c1.setTime(pemohon.getPihak().getInfoAudit().getTarikhMasuk());
                            LOGGER.info("Tarikh lahir :" + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR));
                        } catch (DatatypeConfigurationException ex) {
                            java.util.logging.Logger.getLogger(IntergrationMMKN.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (StringUtils.isBlank(noPendaftaran)) {
                        noPendaftaran = "AL0190222";
                    }
                    LOGGER.info("::::: no pendaftaran :" + noPendaftaran);




//        String trkhTubuh = pemohon.getPermohonan().get
//            LOG.info("###### pemohon alamat " + alamat);
//            LOG.info("###### pemohon email " + email);
//            LOG.info("###### pemohon namaSyarikat " + namaSyarikat);
//            LOG.info("###### pemohon noPendaftaran " + noPendaftaran);
//            LOG.info("###### pemohon noTelefon " + noTelefon);

                    Calendar c1 = Calendar.getInstance();
                    try {
                        DatatypeFactory df = DatatypeFactory.newInstance();
                        GregorianCalendar gc = new GregorianCalendar();
                        LOGGER.info("Tarikh lahir :" + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR));

                        XMLGregorianCalendar tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                        service.addCompany(alamat, ekutiSyarikatAsing,
                                ekutiSyarikatBukanBumiputera, ekutiSyarikatBumiputra,
                                email, modalBerbayar, modalPusingan,
                                namaSyarikat, noPendaftaran, noTelefon, tarikhDitubuhkan);
                    } catch (DatatypeConfigurationException ex) {
                        java.util.logging.Logger.getLogger(IntergrationMMKN.class.getName()).log(Level.SEVERE, null, ex);
                    }




//                    int tempohMastautin = 0;
//                    String umurPemohon = new String();
//                    BigDecimal pendapatanPemohon = new BigDecimal(0);
//                    String bangsaPihak = new String();
//                    String wargaNegara = new String();
//                    tempohMastautin = pemohon.getTempohMastautin() != null ? pemohon.getTempohMastautin() : 0;
//                    umurPemohon = pemohon.getUmur() != null ? pemohon.getUmur().toString() : new String();
//                    pendapatanPemohon = pemohon.getPendapatan() != null ? pemohon.getPendapatan() : new BigDecimal(0);
//                    bangsaPihak = pihak.getBangsa() != null ? pihak.getBangsa().getKod() : new String();
//                    wargaNegara = pihak.getWargaNegara() != null ? pihak.getWargaNegara().getKod() : new String();
//
//                    service.addIndividual(alamatPjabt, alamatRmh, anak, pihak.getEmail(),
//                            bangsaPihak, pihak.getKodJantina(), wargaNegara, tempohMastautin, pihak.getNama(), pihak.getNoPengenalan(), pihak.getNoTelefonBimbit(),
//                            pemohon.getPekerjaan(), pendapatanPemohon, pihak.getTempatLahir(), umurPemohon);

                }


                /*
                 * SETTING LOT APPLICATION
                 * service.addLotInformation(null, String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod()), "", "L", "0" );
                 * NOPT AND NOLOT SHARED THE SAME VARIABLE IN ETANAH MODULE, DIFF BY LOT ONLY
                 * typeLotPT = 1(LOT), 2(PT)
                 */
                String sks = new String();
                String bpm = new String();
                List<HakmilikPermohonan> listHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
                listHakmilikPermohonan = disLaporanTanahService.getPelupusanService().getHakmilikPermohonan(permohonan.getIdPermohonan());
                for (HakmilikPermohonan hakmilikMohon : listHakmilikPermohonan) {
                    HakmilikPermohonan hakMohon = new HakmilikPermohonan();
                    if (hakmilikMohon.getHakmilik() != null) {
                        String idHakmilik = hakmilikMohon.getHakmilik().getIdHakmilik();
                        hakMohon = (HakmilikPermohonan) disLaporanTanahService.findObject(hakMohon, new String[]{permohonan.getIdPermohonan(), idHakmilik}, 0);
                    } else {
                        hakMohon = hakmilikMohon;
                    }
                    String typeLotPT = new String();
                    String noLotPT = new String();
                    if (hakMohon.getHakmilik() != null) {
                        if (hakMohon.getHakmilik().getLot().getKod().equals("1")) {
                            typeLotPT = "1";
                        } else if (hakMohon.getHakmilik().getLot().getKod().equals("2")) {
                            typeLotPT = "2";
                        }
                        noLotPT = hakMohon.getHakmilik().getNoLot();
                        service.addLotInformation(null, String.valueOf(hakMohon.getBandarPekanMukimBaru() != null ? hakMohon.getBandarPekanMukimBaru().getKod() : hakMohon.getHakmilik().getBandarPekanMukim().getKod()), hakMohon.getHakmilik() != null ? hakMohon.getHakmilik().getNoHakmilik() : "", typeLotPT.equals("1") ? noLotPT : "", typeLotPT.equals("2") ? noLotPT : "");
                    } else if (hakMohon.getHakmilik() == null) {
                        if (hakMohon.getLot() != null) {
                            if (hakMohon.getLot().getKod().equals("1")) {
                                typeLotPT = "1";
                            } else if (hakMohon.getLot().getKod().equals("2")) {
                                typeLotPT = "2";
                            }
                            noLotPT = hakMohon.getNoLot();
                            service.addLotInformation("",
                                    String.valueOf(hakMohon.getBandarPekanMukimBaru() != null ? hakMohon.getBandarPekanMukimBaru().getKod() : hakMohon.getHakmilik().getBandarPekanMukim().getKod()),
                                    hakMohon.getHakmilik() != null ? hakMohon.getHakmilik().getNoHakmilik() : "",
                                    typeLotPT.equals("1") ? noLotPT : "",
                                    typeLotPT.equals("2") ? noLotPT : "");

                        } else {
                            service.addLotInformation(null, String.valueOf(hakMohon.getBandarPekanMukimBaru() != null ? hakMohon.getBandarPekanMukimBaru().getKod() : hakMohon.getHakmilik().getBandarPekanMukim().getKod()), hakMohon.getHakmilik() != null ? hakMohon.getHakmilik().getNoHakmilik() : "", "1234", "");
                        }

                    }

                    //Set land general details
                    String tanahB = "";
                    String tanahS = "";
                    String tanahT = "";
                    String tanahU = "";
                    if (mohonLaporTanah.getSempadanBaratMilikKerajaan() != null) {
                        if (mohonLaporTanah.getSempadanBaratMilikKerajaan() == 'Y') {
                            tanahB = "Tanah Kerajaan";
                        } else {
                            if (StringUtils.isBlank(mohonLaporTanah.getSempadanBaratNoLot())) {
                                tanahB = "Nombor Lot Tiada";
                            } else {
                                tanahB = "Nombor Lot " + mohonLaporTanah.getSempadanBaratNoLot();
                            }
                        }
                    }

                    if (mohonLaporTanah.getSempadanSelatanMilikKerajaan() != null) {
                        if (mohonLaporTanah.getSempadanSelatanMilikKerajaan() == 'Y') {
                            tanahS = "Tanah Kerajaan";
                        } else {
                            if (StringUtils.isBlank(mohonLaporTanah.getSempadanSelatanNoLot())) {
                                tanahS = "Nombor Lot Tiada";
                            } else {
                                tanahS = "Nombor Lot " + mohonLaporTanah.getSempadanSelatanNoLot();
                            }

                        }
                    }
                    if (mohonLaporTanah.getSempadanTimurMilikKerajaan() != null) {
                        if (mohonLaporTanah.getSempadanTimurMilikKerajaan() == 'Y') {
                            tanahT = "Tanah Kerajaan";
                        } else {
                            if (StringUtils.isBlank(mohonLaporTanah.getSempadanTimurNoLot())) {
                                tanahT = "Nombor Lot Tiada";
                            } else {
                                tanahT = "Nombor Lot " + mohonLaporTanah.getSempadanTimurNoLot();
                            }

                        }
                    }
                    if (mohonLaporTanah.getSempadanUtaraMilikKerajaan() != null) {
                        if (mohonLaporTanah.getSempadanUtaraMilikKerajaan() == 'Y') {
                            tanahU = "Tanah Kerajaan";
                        } else {
                            if (StringUtils.isBlank(mohonLaporTanah.getSempadanUtaraNoLot())) {
                                tanahU = "Nombor Lot Tiada";
                            } else {
                                tanahU = "Nombor Lot " + mohonLaporTanah.getSempadanUtaraNoLot();
                            }

                        }
                    }
                    String meter = "";
                    String kaki = "";
                    if (hakMohon.getKodUnitLuas() != null) {
                        if (hakMohon.getKodUnitLuas().getKod().equals("K")) {
                            meter = hakMohon.getLuasTerlibat().toString();
                            kaki = null;
                        } else {
                            meter = null;
                            kaki = hakMohon.getLuasTerlibat().toString();
                        }

                    } else {
                        meter = null;
                        kaki = null;
                    }



                    service.addLandDetails(tanahB, mohonLaporTanah.getSempadanBaratKegunaan(), hakMohon.getJarakDari(), "", mohonLaporTanah.getKeadaanTanah(),
                            hakMohon.getLokasi(), "", "", kaki, meter, "",
                            tanahS, mohonLaporTanah.getSempadanSelatanKegunaan(), "", tanahT, mohonLaporTanah.getSempadanTimurKegunaan(), tanahU, mohonLaporTanah.getSempadanUtaraKegunaan());
                    if (hakMohon.getKodSeksyen() != null) {
                        if (sks.isEmpty()) {
                            sks = hakMohon.getKodSeksyen().getNama();
                        }
                    } else {
                        if (hakMohon.getHakmilik() != null) {
                            if (sks.isEmpty()) {
                                if (hakMohon.getHakmilik().getSeksyen() != null) {
                                    sks = hakMohon.getHakmilik().getSeksyen().getNama();
                                }
                            }
                        }
                    }
                    if (hakMohon.getBandarPekanMukimBaru() != null) {
                        if (bpm.isEmpty()) {
                            bpm = hakMohon.getBandarPekanMukimBaru().getNama();
                        }
                    } else {
                        if (hakMohon.getHakmilik() != null) {
                            if (bpm.isEmpty()) {
                                bpm = hakMohon.getHakmilik().getBandarPekanMukim().getNama();
                            }
                        }
                    }
                }


                /*
                 * SETTING LAND APPLICANT
                 * landApplicant[0] refer to etanah TYPE exp : GRANT,TEMP_LISCENSE etc..
                 * landApplicant[0] refer TO KOD_KATG_TANAH.KOD..if hakmilik null set to 4 (Tiada)
                 */
                //
                for (String[] landApp : landApplicantList) {
                    service.addLandApplication(landApp[0], landApp[1]);
                }

                /**
                 * New service for sending file
                 *
                 * @Afham
                 */
                pengguna = context.getPengguna();
                String kodNegeri = conf.getProperty("kodNegeri");
                FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                byte[] reportMMKN = null;
                byte[] reportRingkasanMMKN = null;
                if (permohonan != null) {
                    String gen1 = null;
                    String code1 = null;
                    String gen2 = null;
                    String code2 = null;
                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{permohonan.getIdPermohonan()};
                    String path = "";
                    String dokumenPath = conf.getProperty("document.path");
                    Dokumen d = null;

                    KodDokumen kd = null;

                    switch (numUrusan) {

                        case 1: //PPRU                        
                            break;
                        case 2: //BMBT
                            break;
                        case 3: //PPTR
                            break;
                        case 4: //LMCRG                        
                            break;
                        case 5: //PJLB                              
                            break;
                        case 6: //PTGSA                                 
                            break;
                        case 7: //MCMCL
                            //NOT APPLICABLE
                            break;
                        case 8: //MMMCL
                            //NOT APPLICABLE
                            break;
                        case 9: //PHLP
                            //NOT APPLICABLE
                            break;
                        case 10: //RYKN
                            //NOT APPLICABLE (N9 ONLY)
                            break;
                        case 11: //PBPTD
                            //NOT APPLICABLE
                            break;
                        case 12: //PLPS 
                            break;
                        case 13: //PLPTD
                            //NOT APPLICABLE (N9 ONLY)
                            break;
                        case 14: //RLPS
                            break;
                        case 15: //PBMT
                            gen1 = "DISKMMKNRTFPTG_MLK_PBMT.rdf";
                            code1 = "RMN";

                            gen2 = "DISSUMMMKN_MLK.rdf";
                            code2 = "KMN"; //KIA
                            break;
                        case 16: //PPBB
                            //NOT APPLICABLE
                            break;
                        case 17: //PBPTG
                            //NOT APPLICABLE
                            break;
                        case 18: //RAYK                        
                            break;
                        case 19: //PBRZ                        
                            break;
                        case 20: //PRIZ                        
                            break;
                        case 21: //PTNB   
                            gen1 = "ACQMMKN_IZINLALU_MLK.rdf";
                            code1 = "MMKN";
                            break;
                        case 22: //SEK4
                            gen1 = "ACQKertasMMKNSEK431A_MLK.rdf";
                            code1 = "MMKN";
                            break;
                        case 23: //SEK4A
                            gen1 = "ACQKertasMMKNSEK431A_MLK.rdf";
                            code1 = "MMKN";
                            break;
                        case 24: //PTSP
                            gen1 = "ACQKertasMMKNPTSP_MLK.rdf";
                            code1 = "MMKN";

                            gen2 = "ACQSUMMMKN_MLK.rdf";
                            code2 = "8PAQ";
                            break;

                        case 25: //127
                            gen1 = "ENFMMKN_MLK.rdf";
                            code1 = "MMKN";
                            break;
                        case 26: //425
                            gen1 = "ENFMMKN_MLK.rdf";
                            code1 = "MMKN";
                            break;

                        case 27: //425A
                            gen1 = "ENFMMKN_MLK.rdf";
                            code1 = "MMKN";
                            break;

                        case 28: //PBSK                          
                            gen1 = "DEVDRAFPBSKMMKNPTG_MLK.rdf"; //DEVDRAFPBSKMMKNPTG_MLK.rdf
                            code1 = "MMKNG";
                            
                            gen2 = "DEVDRAFRMMKN_MLK.rdf"; //DEVDRAFRMMKN_MLK.rdf
                            code2 = "RMN";
                            break;

                        case 29: //RLTB                           
                            gen1 = "DEVDRAFKMMKNPTG_MLK.rdf"; //DEVDRAFKMMKNPTG_MLK.rdf
                            code1 = "MMKNG";
                            
                            gen2 = "DEVDRAFRMMKN_MLK.rdf"; //DEVDRAFRMMKN_MLK.rdf
                            code2 = "RMN";
                            break;

                        case 30: //RPMMK                          
                            gen1 = "DEVDRAFKMMKNPTG_MLK.rdf"; //DEVDRAFKMMKNPTG_MLK.rdf
                            code1 = "MMKNG";
                            
                            gen2 = "DEVDRAFRMMKN_MLK.rdf"; //DEVDRAFRMMKN_MLK.rdf
                            code2 = "RMN";
                            break;

                        case 31: //RPP                       
                            gen1 = "DEVDRAFKMMKNPTG_MLK.rdf"; //DEVDRAFKMMKNPTG_MLK.rdf
                            code1 = "MMKNG";
                             
                            gen2 = "DEVDRAFRMMKN_MLK.rdf"; //DEVDRAFRMMKN_MLK.rdf
                            code2 = "RMN";
                            break;

                    }

//                    kd = kodDokumenDAO.findById(code1);
//                    LOGGER.info(kd);
//                    d = semakDokumenService.semakDokumen(kd, fd, permohonan.getIdPermohonan());
//                    if (d != null) {
//                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    }
                    reportMMKN = reportUtilMMKN.generateReport(gen1, params, values, null, pengguna);


//                    kd = kodDokumenDAO.findById(code2);
//                    LOGGER.info(kd);
//                    d = semakDokumenService.semakDokumen(kd, fd, permohonan.getIdPermohonan());
//                    if (d != null) {
//                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    }
//                    reportRingkasanMMKN = reportUtilMMKN.generateReport(gen2, params, values, null, pengguna);
                    if (gen2 != null && code2 != null) {
                        reportRingkasanMMKN = reportUtilMMKN.generateReport(gen2, params, values, null, pengguna);
                    }



//                    if (reportMMKN != null && reportRingkasanMMKN != null) {
                    if (reportMMKN != null && reportRingkasanMMKN == null) {
                        LOGGER.info("Byte Array of Risalat MMKN=" + reportMMKN.toString());


//                            service.addRisalat(mohonKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), service.createFile(reportRingkasanMMKN, "Ringkasan MMKN"));
//                        service.addRisalat(mohonKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), null);

                    } else if (reportMMKN != null && reportRingkasanMMKN != null) {
//                        service.addRisalat(mohonKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), service.createFile(reportRingkasanMMKN, "Ringkasan MMKN"));
                    }
                }

                /**
                 * End of new features
                 */
//        //Set kertas risalat
                service.createRisalat("", bpm, permohonan.getCawangan().getDaerah().getKod(),
                        permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks, mohonKertasKand != null ? mohonKertasKand.getKandungan() : mohonKertas.getTajuk());
//        //Invoke the service and send the data
                result = service.sendData();

                if (!"SUCCESS".equals(result)) {
                    context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
                    context.addMessage("Message : " + service.getStatusMessage());
                    return null;
                } else {
//                    context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
                    context.addMessage(" Penghantaran Ke e-MMKN Berjaya.");
                }
            }

        }
        return proposedOutcome;
//        return null;
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

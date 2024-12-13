package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.report.ReportUtilMMKN;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.uam.UamService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * Shazwan 23 April 2012 11:41 AM
 *
 */
public class IntegrationEMMKN implements StageListener {

    @Inject
    PelupusanService plpservice;
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
    UamService uamService;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOGGER = Logger.getLogger(IntegrationEMMKN.class);
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
        int numUrusan = kodUrusan.equals("PPDL") ? 1
                : kodUrusan.equals("REMSB") ? 2
                : kodUrusan.equals("REMRI") ? 3
                : kodUrusan.equals("RPPP") ? 4
                : 0;
        return numUrusan;
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        LOGGER.info("Start Integration EMMKN....");

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
        List<PermohonanKertasKandungan> senaraiLaporanKandunganpbtanah = new ArrayList<PermohonanKertasKandungan>();
        List<PermohonanKertasKandungan> senaraiLaporanKandunganptg1 = new ArrayList<PermohonanKertasKandungan>();
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        PemohonHubungan pemohonHubunganTemp = new PemohonHubungan();
        PermohonanKertas mohonKertas = new PermohonanKertas();
        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
        String result = new String();
        PermohonanKertasKandungan mohonKertasKandungan = new PermohonanKertasKandungan();
        PermohonanPermitItem permitItem = new PermohonanPermitItem();
        List<LaporanTanah> listLaporanTanah = new ArrayList<LaporanTanah>();

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
                String drafType = "RIS";
                mohonKertas = (PermohonanKertas) disLaporanTanahService.findObject(mohonKertas, new String[]{permohonan.getIdPermohonan(), drafType}, 0);
                mohonKertasKand = disLaporanTanahService.getPelupusanService().findByBilNIdKertasNSubtajuk(0, mohonKertas.getIdKertas(), "1");
                mohonKertasKandunganList = mohonKertas.getSenaraiKandungan();
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};
                listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);
                if (!(listLaporanTanah.isEmpty())) {
                    mohonLaporTanah = listLaporanTanah.get(0);
                }
                pemohon = plpservice.findPemohonByIdPemohon(permohonan.getIdPermohonan());
                if (pemohon != null) {
                    pihak = pemohon.getPihak();
                }
                if (pemohon != null) {

                    pemohonHubunganList = plpservice.findHubunganByIDPemohon(pemohon.getIdPemohon());
                    pemohonHubunganList1 = plpservice.findHubunganByIDANAK(pemohon.getIdPemohon());
                    pemohonHubunganList2 = plpservice.findHubunganByIDPemohonBAPA(pemohon.getIdPemohon());

                }
                mohonRujukLuarList = plpservice.getSenaraiRujLuarByIDPermohonanAgensi(permohonan.getIdPermohonan());
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
                        case 1: //PPDL
                            landApplicant[0] = "LESS_LATE_PENALTY";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 2: //REMSB
                            landApplicant[0] = "WORSHIP_HOUSE_REVENUE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 3: //REMRI
                            landApplicant[0] = "WORSHIP_HOUSE_REVENUE";
                            landApplicant[1] = kategoriTanah;
                            break;
                        case 4: //RPPP                        
                            landApplicant[0] = "APPEAL_CONFISCATED_LAND";
                            landApplicant[1] = kategoriTanah;
                            break;
                    }
                    landApplicantList.add(landApplicant);
                }

                LOGGER.info("---- Integration EMMKN KOD URUSAN : " + permohonan.getKodUrusan().getKod() + " : " + landApplicant[0]);

                switch (numUrusan) {

                    case 1: //PPDL
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 2: //REMSB
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 3: //REMRI
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                    case 4: //RPPP                        
                        senaraiLaporanKandunganpbtanah = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 5); //FOR No 7 PERAKUAN PENTADBIR TANAH DAERAH
                        senaraiLaporanKandunganptg1 = disLaporanTanahService.getPelupusanService().findByIdbyBil(mohonKertas.getIdKertas(), 6); //For No8 PERAKUAN PENTADBIR TANAH DAN GALIAN
                        break;
                }

                LOGGER.info("---- Integration EMMKN : ULASAN");

                //SETTING ULASAN PTD
                for (PermohonanKertasKandungan pkk : senaraiLaporanKandunganpbtanah) {
                    LOGGER.info("::: ULASAN PTD : " + pkk.getKandungan());
                    service.addUlasan("D", Integer.parseInt(permohonan.getCawangan().getKod()) + "", pkk.getKandungan());
                }

                if (senaraiLaporanKandunganpbtanah.isEmpty()) {
                    LOGGER.info("::: ULASAN PTD : TIADA ULASAN ");
                    service.addUlasan("D", Integer.parseInt(permohonan.getCawangan().getKod()) + "", "TIADA ULASAN");
                }

                //SETTING ULASAN PTG
                for (PermohonanKertasKandungan pkk : senaraiLaporanKandunganptg1) {
                    LOGGER.info("::: ULASAN PTG : " + pkk.getKandungan());
                    service.addUlasan("G", 0 + "", pkk.getKandungan());
                }

                if (senaraiLaporanKandunganptg1.isEmpty()) {
                    LOGGER.info("::: ULASAN PTG : TIADA ULASAN ");
                    service.addUlasan("G", 0 + "", "TIADA ULASAN");
                }

                /*
                 * SETTING ULASAN JABATAN TEKNIKAL
                 * service.addUlasan(A, B, C);
                 * A-> T(JABATAN TEKNIKAL)
                 * B-> Kod JABATAN in MMKN
                 * C-> MOHONRUJLUAR ( ULASAN)
                 */
                if (mohonRujukLuarList != null) {
                    for (PermohonanRujukanLuar prl : mohonRujukLuarList) {
                        if (prl.getUlasan() != null) {
                            if (prl.getAgensi() != null && prl.getAgensi().getKategoriAgensi().getKod().equals("JTK")) {
                                LOGGER.info("::: ULASAN JTEK : " + prl.getUlasan());
                                service.addUlasan("T", prl.getAgensi().getKodEmmkn(), prl.getUlasan());
                            }
                        }
                    }
                }

                LOGGER.info("---- Integration EMMKN : SUAMI ISTERI ");
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
                LOGGER.info("---- Integration EMMKN : PEMOHON ");

                /*
                 * SETTING PEMOHON 
                 */
                if (pemohon != null) {
                    String alamatPjabt = pemohon.getInstitusiAlamat1() + "," + pemohon.getInstitusiAlamat2() + "," + pemohon.getInstitusiAlamat3() + "," + pemohon.getInstitusiAlamat4() + ".";
                    String alamatRmh = pihak.getAlamat1() + "," + pihak.getAlamat2() + "," + pihak.getAlamat3() + "," + pihak.getAlamat4() + ".";
                    if (alamatPjabt.equals(",,,.")) {
                        alamatPjabt = "Tiada";
                    }

                    if (pemohon.getPihak().getJenisPengenalan() != null) {
                        //syarikat dan pertubuhan
                        if (pemohon.getPihak().getJenisPengenalan().getKod().equals("S") || pemohon.getPihak().getJenisPengenalan().getKod().equals("U") || pemohon.getPihak().getJenisPengenalan().getKod().equals("D") || pemohon.getPihak().getJenisPengenalan().getKod().equals("O") || pemohon.getPihak().getJenisPengenalan().getKod().equals("N") || pemohon.getPihak().getJenisPengenalan().getKod().equals("0")) {
                            LOGGER.info("---- Integration EMMKN : JENIS PEMOHON - SYARIKAT ");
                            if (pemohon.getPihak().getNoPengenalan() == null) {
                                pemohon.getPihak().setNoPengenalan("TIADA");
                            }
                            if (pemohon.getPihak().getTarikhLahir() == null) {
                                pemohon.getPihak().setTarikhLahir(pemohon.getPihak().getInfoAudit().getTarikhMasuk());
                            }

                            LOGGER.info("Add Syarikat.....");
                            LOGGER.info("NO KP : " + pemohon.getPihak().getNoPengenalan());
                            LOGGER.info("TARIKH TUBUH : " + pemohon.getPihak().getTarikhLahir());

                            service.addCompany(alamatRmh, "", "", "", pemohon.getPihak().getEmail(), BigDecimal.ZERO, BigDecimal.ZERO, pemohon.getPihak().getNama(), pemohon.getPihak().getNoPengenalan(), pemohon.getPihak().getNoTelefon1(), service.convertDate(pemohon.getPihak().getTarikhLahir()));

                        } else {
                            LOGGER.info("---- Integration EMMKN : JENIS PEMOHON - INDIVIDU ");
                            //PEMOHON ADALAH INDIVIDU
                            boolean anak = false;
                            if (pemohon.getPihak().getWargaNegara() != null) {
                                if (pemohon.getPihak().getWargaNegara().getKod().equals("MAL")) {
                                    anak = true;
                                }
                            }

                            if (pemohon.getPihak().getBangsa() != null) {
                                if (pemohon.getPihak().getBangsa().getKod().equals("T")) {
                                    pemohon.getPihak().getBangsa().setKod("LN");
                                }
                            }

                            String noKP = "TIADA";
                            String umur = "TIADA";
                            String tempatLahir = "TIADA";
                            String warganegara;
                            String bangsa = "";
                            String jantina = "1";
                            Integer tempohMastautin = 0;
                            BigDecimal pendapatan = new BigDecimal("0.00");

                            if (pemohon.getPihak().getNoPengenalan() != null) {
                                noKP = pemohon.getPihak().getNoPengenalan();
                            }
                            if (pemohon.getUmur() != null) {
                                umur = pemohon.getUmur().toString();
                            }
                            if (pemohon.getPihak().getTempatLahir() != null) {
                                tempatLahir = pemohon.getPihak().getTempatLahir();
                            }
                            if (pemohon.getTempohMastautin() != null) {
                                tempohMastautin = pemohon.getTempohMastautin();
                            }
                            if (pemohon.getPendapatan() != null) {
                                pendapatan = pemohon.getPendapatan();
                            }
                            if (pemohon.getPihak().getWargaNegara() != null) {
                                warganegara = pemohon.getPihak().getWargaNegara().getKod();
                            } else {
                                warganegara = "MAL";
                            }
                            if (pemohon.getPihak().getBangsa() != null) {
                                bangsa = pemohon.getPihak().getBangsa().getKod();
                            }
                            if (pemohon.getPihak().getKodJantina() != null) {
                                jantina = pemohon.getPihak().getKodJantina();
                            }

                            LOGGER.info("Add individual.....");
                            LOGGER.info("No KP : " + noKP);
                            LOGGER.info("alamat pejabat : " + alamatPjabt);
                            LOGGER.info("anak : " + anak);
                            LOGGER.info("emel :" + pemohon.getPihak().getEmail());
                            LOGGER.info("warganegara :" + warganegara);
                            LOGGER.info("bangsa : " + bangsa);
                            LOGGER.info("jantina : " + jantina);
                            LOGGER.info("mastautin : " + tempohMastautin);
                            LOGGER.info("no pengenalan : " + pemohon.getPihak().getNoPengenalan());
                            LOGGER.info("no hp : " + pemohon.getPihak().getNoTelefonBimbit());
                            LOGGER.info("pekerjaan : " + pemohon.getPekerjaan());
                            LOGGER.info("pendapatan : " + pendapatan);
                            LOGGER.info("tempat lahir :" + tempatLahir);
                            LOGGER.info("umur : " + umur);

                            service.addIndividual(alamatPjabt, alamatRmh, anak, pemohon.getPihak().getEmail(), bangsa, jantina, warganegara,
                                    tempohMastautin, pemohon.getPihak().getNama(), noKP, pemohon.getPihak().getNoTelefonBimbit(),
                                    pemohon.getPekerjaan(), pendapatan, tempatLahir, umur);

                        }
                    }
                }
                LOGGER.info("---- Integration EMMKN : HAKMILIK ");

                //Set Maklumat Hakmilik
                for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
                    LOGGER.info("Hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());

                    if (hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getSenaraiSeksyen().isEmpty()) {
                        LOGGER.info("---- SET KOD BPM = " + hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getKod());
                        service.addLotInformation(hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod(), hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getKod() + "", hakmilikPermohonan.getHakmilik().getNoHakmilik(),
                                hakmilikPermohonan.getHakmilik().getNoLot(), "");
                    } else {
                        LOGGER.info("---- SET TIPU KOD BPM = 11 ");
                        service.addLotInformation(hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod(), "11", hakmilikPermohonan.getHakmilik().getNoHakmilik(),
                                hakmilikPermohonan.getHakmilik().getNoLot(), "");
                    }

                    if (hakmilikPermohonan.getSenaraiLaporanTanah().isEmpty()) {

                        String ekar = "";
                        String hektar = "";
                        String kaki = "";
                        String meter = "";

                        if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("A")) {
                            ekar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                            hektar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("F")) {
                            kaki = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                            meter = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        }

                        LOGGER.info("Add land detail application.....");
                        service.addLandDetails("Tiada Data", "Tiada Data", hakmilikPermohonan.getJarakDari(), hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama(), "Tiada Data", hakmilikPermohonan.getLokasi(), ekar, hektar, kaki, meter, hakmilikPermohonan.getCatatan(), "Tiada Data", "Tiada Data", hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama(), "Tiada Data", "Tiada Data", "Tiada Data", "Tiada Data");
                    } else {
                        LaporanTanah laporanTanah = hakmilikPermohonan.getSenaraiLaporanTanah().get(0);

                        String ekar = "";
                        String hektar = "";
                        String kaki = "";
                        String meter = "";

                        if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("A")) {
                            ekar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                            hektar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("F")) {
                            kaki = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                            meter = hakmilikPermohonan.getHakmilik().getLuas().toString();
                        }

                        LOGGER.info("Add land detail application.....");
                        service.addLandDetails(laporanTanah.getSempadanBaratNoLot(), laporanTanah.getSempadanBaratKegunaan(), hakmilikPermohonan.getJarakDari(), hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama(), laporanTanah.getKeadaanTanah(), hakmilikPermohonan.getLokasi(), ekar, hektar, kaki, meter, hakmilikPermohonan.getCatatan(), laporanTanah.getSempadanSelatanNoLot(), laporanTanah.getSempadanSelatanKegunaan(), hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama(), laporanTanah.getSempadanTimurNoLot(), laporanTanah.getSempadanTimurKegunaan(), laporanTanah.getSempadanUtaraNoLot(), laporanTanah.getSempadanUtaraKegunaan());

                    }
                }

                /*
                 * SETTING LOT APPLICATION
                 * service.addLotInformation(null, String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod()), "", "L", "0" );
                 * NOPT AND NOLOT SHARED THE SAME VARIABLE IN ETANAH MODULE, DIFF BY LOT ONLY
                 * typeLotPT = 1(LOT), 2(PT)
                 */

                LOGGER.info("---- Integration EMMKN : addLandApplication ");

                /*
                 * SETTING LAND APPLICANT
                 * landApplicant[0] refer to etanah TYPE exp : GRANT,TEMP_LISCENSE etc..
                 * landApplicant[0] refer TO KOD_KATG_TANAH.KOD..if hakmilik null set to 4 (Tiada)
                 */

                for (String[] landApp : landApplicantList) {
                    LOGGER.info(":::Land App : " + landApp[0]);
                    LOGGER.info(":::Kategori : " + landApp[1]);
                    service.addLandApplication(landApp[0], landApp[1]);
                }

                LOGGER.info("---- Integration EMMKN : CREATE RISALAT ");
                /*
                 * New service for sending file
                 */
                pengguna = context.getPengguna();
                uamService.createRisalatService(permohonan, service, pengguna, mohonKertas);

                String bpm = "";
                String sks = "";
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim() != null) {
                    bpm = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama();
                }
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen() != null) {
                    sks = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen().getNama();
                }

                //Set kertas risalat
                service.createRisalat("", bpm, permohonan.getCawangan().getDaerah().getKod(),
                        permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks, mohonKertasKand != null ? mohonKertasKand.getKandungan() : mohonKertas.getTajuk());
                //Invoke the service and send the data
                result = service.sendData();

                LOGGER.info("---- Integration EMMKN : SENDING DATA ");
                if (!"SUCCESS".equals(result)) {
                    context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
                    context.addMessage("Message : " + service.getStatusMessage());
                    return null;
                } else {
                    context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
                }
            }

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

package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shazwan 31102011 1404 PM
 */
public class DisAgihanTugas {

    @Inject
    PelupusanService pservice;

    public List<Pengguna> getPengguna(int numUrusan, String stageID, String negeri, String kodCaw) {
        List listPp = new ArrayList<Pengguna>();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PPRU
                listPp = getPenggunaByBPELCasePPRU(stageID, numnegeri, kodCaw);
                break;
            case 2:
                //BMBT
                listPp = getPenggunaByBPELCaseBMBT(stageID, numnegeri, kodCaw);
                break;
            case 3:
                //PPTR
                listPp = getPenggunaByBPELCasePPTR(stageID, numnegeri, kodCaw);
                break;
            case 4:
                //LMCRG
                listPp = getPenggunaByBPELCaseLMCRG(stageID, numnegeri, kodCaw);
                break;
            case 5:
                //PJLB
                listPp = getPenggunaByBPELCasePJLB(stageID, numnegeri, kodCaw);
                break;
            case 6:
                //PTGSA
                listPp = getPenggunaByBPELCasePTGSA(stageID, numnegeri, kodCaw);
                break;
            case 7:
                //MCMCL
                listPp = getPenggunaByBPELCaseMCMCL(stageID, numnegeri, kodCaw);
                break;
            case 8:
                //MMMCL
                listPp = getPenggunaByBPELCaseMMMCL(stageID, numnegeri, kodCaw);
                break;
            case 9:
                //PHLP
                listPp = getPenggunaByBPELCasePHLP(stageID, numnegeri, kodCaw);
                break;
            case 10:
                //RYKN
                listPp = getPenggunaByBPELCaseRYKN(stageID, numnegeri, kodCaw);
                break;
            case 11:
                //PBPTD
                listPp = getPenggunaByBPELCasePBPTD(stageID, numnegeri, kodCaw);
                break;
            case 12:
                //PLPS
                listPp = getPenggunaByBPELCasePLPS(stageID, numnegeri, kodCaw);
                break;
            case 13:
                //PLPTD
                listPp = getPenggunaByBPELCasePLPTD(stageID, numnegeri, kodCaw);
                break;

            case 14:
                //RLPS
                listPp = getPenggunaByBPELCaseRLPS(stageID, numnegeri, kodCaw);
                break;
            case 15:
                //PBMT
                listPp = getPenggunaByBPELCasePBMT(stageID, numnegeri, kodCaw);
                break;
            case 16:
                //PPBB
                listPp = getPenggunaByBPELCasePPBB(stageID, numnegeri, kodCaw);
                break;
            case 17:
                //PBPTG
                listPp = getPenggunaByBPELCasePBPTG(stageID, numnegeri, kodCaw);
                break;
            case 18:
                //RLPSK
                listPp = getPenggunaByBPELCaseRLPSK(stageID, numnegeri, kodCaw);
                break;
            case 19:
                //PWGSA
                listPp = getPenggunaByBPELCasePWGSA(stageID, numnegeri, kodCaw);
                break;
            case 20:
                //PBGSA
                listPp = getPenggunaByBPELCasePBGSA(stageID, numnegeri, kodCaw);
                break;
            case 21:
                //PRIZ
                listPp = getPenggunaByBPELCasePRIZ(stageID, numnegeri, kodCaw);
                break;
            case 22:
                //PHLA
                listPp = getPenggunaByBPELCasePHLA(stageID, numnegeri, kodCaw);
                break;
            case 23:
                //LPSP
                listPp = getPenggunaByBPELCaseLPSP(stageID, numnegeri, kodCaw);
                break;
            case 24:
                //PBRZ
                listPp = getPenggunaByBPELCasePBRZ(stageID, numnegeri, kodCaw);
                break;
            case 25:
                //RAYT
                listPp = getPenggunaByBPELCaseRAYT(stageID, numnegeri, kodCaw);
                break;
            case 26:
                //PBHL
                listPp = getPenggunaByBPELCasePBHL(stageID, numnegeri, kodCaw);
                break;
            case 27:
                //MLCRG
                listPp = getPenggunaByBPELCaseMLCRG(stageID, numnegeri, kodCaw);
                break;
            case 28:
                //MPJLB
                listPp = getPenggunaByBPELCaseMPJLB(stageID, numnegeri, kodCaw);
                break;
            case 29:
                //PJBTR
                listPp = getPenggunaByBPELCasePJBTR(stageID, numnegeri, kodCaw);
                break;
            case 30:
                //PBMMK
                listPp = getPenggunaByBPELCasePBMMK(stageID, numnegeri, kodCaw);
                break;
			case 31:
                //PRMP  Added by Iskandar 17 April 2013 1129PM
                listPp = getPenggunaByBPELCasePRMP(stageID, numnegeri, kodCaw);
                break;
           case 32:
                //PTPBP
                listPp = getPenggunaByBPELCasePTPBP(stageID, numnegeri, kodCaw);
                break;
           case 33:
                //PRMMK
                listPp = getPenggunaByBPELCasePRMMK(stageID, numnegeri, kodCaw);
                break;
           case 34:
                //PCRG
                listPp = getPenggunaByBPELCasePCRG(stageID, numnegeri, kodCaw);
                break;
           case 35:
                //MPCRG
                listPp = getPenggunaByBPELCaseMPCRG(stageID, numnegeri, kodCaw);
                break;
           case 36:
                //RAYK
                listPp = getPenggunaByBPELCaseRAYK(stageID, numnegeri, kodCaw);
                break;
           case 37:
                //MMRE
                listPp = getPenggunaByBPELCaseMMRE(stageID, numnegeri, kodCaw);
                break;
           case 38:
                //PTBTC
                listPp = getPenggunaByBPELCasePTBTC(stageID, numnegeri, kodCaw);
                break;
           case 39:
                //PTBTS
                listPp = getPenggunaByBPELCasePTBTS(stageID, numnegeri, kodCaw);
                break;
           case 40:
                //BMBT
                listPp = getPenggunaByBPELCaseBMBT(stageID, numnegeri, kodCaw);
                break;
           case 41:
                //PJTK
                listPp = getPenggunaByBPELCasePJTK(stageID, numnegeri, kodCaw);
                break;
           case 42:
                //WMRE
                listPp = getPenggunaByBPELCaseWMRE(stageID, numnegeri, kodCaw);
                break;
           case 43:
                //BMRE
                listPp = getPenggunaByBPELCaseBMRE(stageID, numnegeri, kodCaw);
                break;
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePPRU(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("terima_keputusan_mmkn")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPelan");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04agihan_tugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("23terima_keputusan")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("27Tandatangan_Surat")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

//    public List<Pengguna> getPenggunaByBPELCaseBMBT(String stageID, int numnegeri, String kodCaw) {
//        List listPp = new ArrayList<Pengguna>();
//        /*
//         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
//         * current user..
//         */
//        List<String> bpelName = new ArrayList<String>();
//
//        switch (numnegeri) {
//            case 1:
//                //MELAKA
//                if (stageID.equalsIgnoreCase("agihan_tugas")) {
//                    bpelName.add("PPelan");
//                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
//                    bpelName.add("PPTanah");
//                } else if (stageID.equalsIgnoreCase("semak_agihan_tugas")) {
//                    bpelName.add("PPelan");
//                } else if (stageID.equalsIgnoreCase("lot_index_agih_tugas")) {
//                    bpelName.add("PPelan");
//                }
//
//                break;
//            case 2:
//                //NEGERI SEMBILAN
//                if (stageID.equalsIgnoreCase("02ArahanCharting")) {
//                    bpelName.add("PPelan");
//                } else if (stageID.equalsIgnoreCase("05arah_sdp")) {
//                    bpelName.add("ptlupus");
//                } else if (stageID.equalsIgnoreCase("08agihan_tugas")) {
//                    bpelName.add("PPTanah");
//                } else if (stageID.equalsIgnoreCase("28ArahanPenangguhan")) {
//                    bpelName.add("penptlupus");
//                } else if (stageID.equalsIgnoreCase("33TerimaKpsnArahChart")) {
//                    bpelName.add("PPelan");
//                }
//                break;
//        }
//
//        if (bpelName.size() > 0) {
//            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
//        }
//        return listPp;
//    }

    public List<Pengguna> getPenggunaByBPELCasePPTR(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");	// modified: hairudin (14112011), salah appoint peranan
                } else if (stageID.equalsIgnoreCase("agihan_tugas_ck")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas_cmr")) {
                    bpelName.add("PPelan");
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02ArhnChartg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("20Smk_Kptsn")) {
                    bpelName.add("penptlupus");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCaseLMCRG(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW
                } else if (stageID.equalsIgnoreCase("agihan_tugas_charting")) {
                    bpelName.add("ptgppelan");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW
                } else if (stageID.equalsIgnoreCase("agihan_tugas_charting2")) {
                    bpelName.add("ptgppelan");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //TO DO HERE              
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePJLB(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";
                } else if (stageID.equalsIgnoreCase("rekod_keputusan_jkm")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";
                } else if (stageID.equalsIgnoreCase("arahan_tugas")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //TO DO HERE                
                if (stageID.equalsIgnoreCase("03ArhChrtg")) {
                    bpelName.add("PPelan");
//                  kodCaw = "00";
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePTGSA(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("g_charting_pemohonan")) {
                    bpelName.add("pptkanan");
                } else if (stageID.equalsIgnoreCase("02AgihanTugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("03ArahLaporanTanah")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("20AgihanTugas")) {
                    bpelName.add("PPelan");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02ArhnChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("35ArhChrtg")) {
                    bpelName.add("PPelan");
                } 
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCaseMCMCL(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                //TO DO HERE
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCaseMMMCL(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas_kpsn")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("semak_charting")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agihan_tugas_lot")) {
                    bpelName.add("PPelan");
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //TO DO HERE
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePHLP(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("02agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04agihan_tugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("25agihan_tugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("30terima_pa")) {
                    bpelName.add("PPTanah");
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //TO DO HERE
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCaseRYKN(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("10TrmArhnAtsPenangguhan")) {
                    bpelName.add("PPTanahPTG");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePBPTD(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas") || stageID.equalsIgnoreCase("agihan_tugas4") || stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2") || stageID.equalsIgnoreCase("agihan_tugas3") || stageID.equalsIgnoreCase("agihan_tugas6")) {
                    bpelName.add("PPTanah");
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("agihan_tugas") || stageID.equalsIgnoreCase("agihan_tugas4") || stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2") || stageID.equalsIgnoreCase("agihan_tugas3") || stageID.equalsIgnoreCase("agihan_tugas6")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("semakan_agihan_charting")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("sdp")) {
                    bpelName.add("ptlupus");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePLPS(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("terima_keputusan_mmkn")) {
                    bpelName.add("PPTanah");
                }


                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02ArhknChartg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("06ArhknSDP")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("09TrmdanArh")) {
                    bpelName.add("PPTanah");
                } 

                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePLPTD(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("03ArhChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("07ArhknSDP")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("10AghnTgs")) {
                    bpelName.add("PPTanah");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCaseRLPS(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("01ArhSedLap")) {
                    bpelName.add("pptkanan");
                } else if (stageID.equalsIgnoreCase("02ArhLapTnh")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("05ArhSedKrts")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("10TrmKptsn")) {
                    bpelName.add("ptlupus");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePBMT(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("terima_keputusan_mmkn")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("arahan_tugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("arahan_sedia_draf_mmkn")) {
                    bpelName.add("PPTanah");
                }


                break;
            case 2:
                //NEGERI SEMBILAN 
                if (stageID.equalsIgnoreCase("agih_tugasPP")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agih_tugas_T1")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agih_tugasLT")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agih_tugas_chart_kpsn")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agih_chart_hakmilik")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("arahan_pu")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("arah_precomp")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("arahan_rekod_lot_index")) {
                    bpelName.add("PPelan");
                }

                break;
        }
        if (bpelName.size()
                > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }




        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePPBB(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas8")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas6")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("arahan_tugasan4")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("arahan_tugasan4a")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("agihan_tugasan7")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("semak_laporan_tanahptg")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("semakan_agihan_charting")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPTanah");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePBPTG(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPTanahPTG");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas6")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas7")) {
                    bpelName.add("PPTanah");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_tlk")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_lls")) {
                    bpelName.add("ptlupus");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCaseRLPSK(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE

                break;


            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("arahan_sedia_laporan")) {
                    bpelName.add("pptkanan");


                } else if (stageID.equalsIgnoreCase("arahan_laporan_tanah")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("arahan_sedia_draf_ptd")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("terima_keputusan_ptd")) {
                    bpelName.add("ptlupus");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePWGSA(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("01Kemasukan")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("04ArhnPenyediaanPU")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("18MklmnWartaRkdIndx")) {
                    bpelName.add("PPelan");


                }


                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("03ArhnUtkPnydianPU")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("20MklmnWrtadanArhnChrtg")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("08ArhSedPU")) {
                    bpelName.add("PPTanah");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePBGSA(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("00ArahDptknSBU")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("01Kemasukan")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("05AgihanTugas")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("13MklmArahan")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("35AghanTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("37AghanTgsPnptnPes")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("26AghanTgsPnptnPes")) {
                    bpelName.add("PPTanah");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                
                if (stageID.equalsIgnoreCase("02ArhnPemilihanPsrta")) {
                    bpelName.add("pptd");
                } else if (stageID.equalsIgnoreCase("03BuatTemuduga")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("26AghnTgs")) {
                    bpelName.add("PPTanah");
                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePRIZ(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugasan4")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas5")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas6")) {
                    bpelName.add("PPelan");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("008")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("002")) {
                    bpelName.add("PPelan");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePHLA(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("02AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("05AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("07SmkLap")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("18PrnthWdjknHL")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("21AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("25AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("31AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("37AghnTgs")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("39SmkdanAghnTgs")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("45TrmSalinanPA")) {
                    bpelName.add("PPelan");


                }
                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCaseLPSP(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("14ArahanTugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("semak_laporan_tanah")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("21AgihanChartingKemaskini")) {
                    bpelName.add("PPelan");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                
                if (stageID.equalsIgnoreCase("02AghnTgs")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("06ArahSdp")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("09TrmdanArh")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("34SSmkShknCtk")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("36CSSmkShknCtk")) {
                    bpelName.add("PPelan");
                } 
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePBRZ(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("003_AgihanTugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("006_AgihanTugas")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("027_ArahanBagiPenyediaan")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00";


                } else if (stageID.equalsIgnoreCase("037_AgihanTugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("042_ArahkanPenyediaanPU")) {
                    bpelName.add("PPTanah");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhnChrtg")) {
                    bpelName.add("pptkanan");
                } else if (stageID.equalsIgnoreCase("03PengagihanSemula")) {
                    bpelName.add("PPelan");
                }

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCaseRAYT(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("002_Makluman")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("006_AgihanTugas")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("037_AgihanTugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("016_AgihanTugas")) {
                    bpelName.add("PPelan");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePBHL(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("05AghnTgs")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("24ArhSedknPU")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("27MklmPln")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("18PrntahTlk")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("20PrntahBtlHL")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("18AghnTgs")) {
                    bpelName.add("PPelan");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCaseMLCRG(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("008_Arah")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("017_Terimaarahan")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                } else if (stageID.equalsIgnoreCase("031_ArahPPT")) {
                    bpelName.add("tptg");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                } else if (stageID.equalsIgnoreCase("033_ArahPT")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCaseMPJLB(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("007_Maklum")) {
                    bpelName.add("pptt");


                } else if (stageID.equalsIgnoreCase("008_AgihanTugas")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                } else if (stageID.equalsIgnoreCase("013_ArahPTG")) {
                    bpelName.add("pptt");


                } else if (stageID.equalsIgnoreCase("014_AgihanTugas")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePJBTR(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("maklum_agihan_JKBB")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                } else if (stageID.equalsIgnoreCase("arah_sedia_PU")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_chart_kemaskini")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_kpsn")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("arhn_smkn_lprn_tanah_D1")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("kenalpasti_JTEK_LT_P1")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("arahan_TPTG_PT_D")) {
                    bpelName.add("ptptglupus");
                    kodCaw = "00"; //NEEDED TO HARDCODE SINCE URUSAN FROM PTD TO PTG, CANT GET FROM MOHON.KODCAW


                } else if (stageID.equalsIgnoreCase("kenalpasti_LT_P1")) {
                    bpelName.add("PPTanah");


                }

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO

                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;


    }

    public List<Pengguna> getPenggunaByBPELCasePBMMK(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();



        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE

                break;


            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");


                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_tlk")) {
                    bpelName.add("ptlupus");


                } else if (stageID.equalsIgnoreCase("agihan_tugas_lls")) {
                    bpelName.add("ptlupus");


                }
                break;


        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);


        }
        return listPp;

    }
	
	public List<Pengguna> getPenggunaByBPELCasePRMP(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agihan_tugas")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("agihan_tugas2")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
                    bpelName.add("PPelan");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("03AgihanTugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("22TerimaKeputusan")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
        
     public List<Pengguna> getPenggunaByBPELCasePTPBP(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArahanCharting")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("08agihan_tugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("33TerimaKpsnArahChart")) {
                    bpelName.add("PPelan");
                } 
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }  
     
     public List<Pengguna> getPenggunaByBPELCasePRMMK(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhknChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("09AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("37AgihTgs")) {
                    bpelName.add("PPelan");
                } 
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     
     public List<Pengguna> getPenggunaByBPELCasePCRG(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("03ArhRjkJbtnTknkl")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     
     public List<Pengguna> getPenggunaByBPELCaseMPCRG(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("03ArhRjkJbtnTknkl")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     
     public List<Pengguna> getPenggunaByBPELCaseRAYK(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("10TrmArhnAtsPenangguhan")) {
                    bpelName.add("PPTanahPTG");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     public List<Pengguna> getPenggunaByBPELCaseMMRE(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04ArhSedLapTnh")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("22PenangguhanMsyrt")) {
                    bpelName.add("PPTanah");
                }               
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     
     public List<Pengguna> getPenggunaByBPELCaseWMRE(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04ArhSedLapTnh")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("22PenangguhanMsyrt")) {
                    bpelName.add("PPTanah");
                }               
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
     
     public List<Pengguna> getPenggunaByBPELCaseBMRE(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhChartg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04Agh_Tgs")) {
                    bpelName.add("PPTanah");
                }              
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
        
    public List<Pengguna> getPenggunaByBPELCasePTBTC(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhnChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("06ArhknSDP")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("09AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("36TrmKptsnMMK")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
    
    public List<Pengguna> getPenggunaByBPELCasePTBTS(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhnChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("06ArhknSDP")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("09AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("36TrmKptsnMMK")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
    
    public List<Pengguna> getPenggunaByBPELCaseBMBT(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhnChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("06ArhknSDP")) {
                    bpelName.add("ptlupus");
                } else if (stageID.equalsIgnoreCase("09AghnTgs")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("36TrmKptsnMMK")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
    
    public List<Pengguna> getPenggunaByBPELCasePJTK(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //TO DO HERE
                break;

            case 2:
                //NEGERI SEMBILAN
                //TO DO
                if (stageID.equalsIgnoreCase("02ArhChrtg")) {
                    bpelName.add("PPelan");
                } else if (stageID.equalsIgnoreCase("04ArhSedLapTnhdanDrafWrtaPel")) {
                    bpelName.add("PPTanah");
                } 
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
	
}

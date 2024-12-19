package etanah.view.stripes.pembangunan.disClass;

import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shazwan 31102011 1404 PM @purpose : To Cater Report Based On Their
 * Urusan And Stages
 */
public class DisReport {

    @Inject
    PelupusanService pservice;
    private HashMap reportMap;

    public HashMap getReportMap(int numUrusan, String stageID, String negeri, Permohonan p, HakmilikPermohonan hp) {
        reportMap = new HashMap();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PBMT
                reportMap = getReportByCasePBMT(stageID, numnegeri);
                break;
            case 2:
                //PLPS
                reportMap = getReportByCasePLPS(stageID, numnegeri);
                break;
            case 3:
                //MCMCL
                reportMap = getReportByCaseMCMCL(stageID, numnegeri);
                break;
            case 4:
                //PHLP
                reportMap = getReportByCasePHLP(stageID, numnegeri);
                break;
            case 5:
                //LPSP
                reportMap = getReportByCaseLPSP(stageID, numnegeri, p);
                break;
            case 6:
                //MMMCL
                reportMap = getReportByCaseMMMCL(stageID, numnegeri, hp);
                break;
            case 7:
                //PPTR
                reportMap = getReportByCasePPTR(stageID, numnegeri);
                break;
            case 8:
                //RLPS
                reportMap = getReportByCaseRLPS(stageID, numnegeri);
                break;
            case 9:
                //PTGSA
                reportMap = getReportByCasePTGSA(stageID, numnegeri);
                break;
            case 10:
                //PPBB
                reportMap = getReportByCasePPBB(stageID, numnegeri, p);
                break;
            case 11:
                //PBPTD
                reportMap = getReportByCasePBPTD(stageID, numnegeri, p);
                break;
            case 12:
                //PBPTG
                reportMap = getReportByCasePBPTG(stageID, numnegeri, p);
                break;
            case 13:
                //PPRU
                reportMap = getReportByCasePPRU(stageID, numnegeri, p);
                break;
            case 14:
                //PRMP
                reportMap = getReportByCasePRMP(stageID, numnegeri, p);
                break;
            case 15:
                //LMCRG
                reportMap = getReportByCaseLMCRG(stageID, numnegeri, p);
                break;
            case 16:
                //PJLB
                reportMap = getReportByCasePJLB(stageID, numnegeri, p);
                break;
            case 17:
                //PBRZ
                reportMap = getReportByCasePBRZ(stageID, numnegeri, p);
                break;
            case 18:
                //PRIZ
                reportMap = getReportByCasePRIZ(stageID, numnegeri, p);
                break;
            case 19:
                //RAYA
                reportMap = getReportByCaseRAYA(stageID, numnegeri, p);
                break;
            case 20:
                //RAYL
                reportMap = getReportByCaseRAYL(stageID, numnegeri, p);
                break;
            case 21:
                //RAYK
                reportMap = getReportByCaseRAYK(stageID, numnegeri, p);
                break;
            case 22:
                //RAYT
                reportMap = getReportByCaseRAYT(stageID, numnegeri, p);
                break;
            case 23:
                //JMRE
                reportMap = getReportByCaseJMRE(stageID, numnegeri, p);
                break;
            case 24:
                //PJTK
                reportMap = getReportByCasePJTK(stageID, numnegeri, p);
                break;
            case 25:
                //PLPTD
                reportMap = getReportByCasePLPTD(stageID, numnegeri, p);
                break;
            case 26:
                //MMRE
                reportMap = getReportByCaseMMRE(stageID, numnegeri, p);
                break;
            case 27:
                //MPCRG
                reportMap = getReportByCaseMPCRG(stageID, numnegeri, p);
                break;
            case 28:
                //PTBTC
                reportMap = getReportByCasePTBTC(stageID, numnegeri, p);
                break;
            case 29:
                //PTBTS
                reportMap = getReportByCasePTBTS(stageID, numnegeri, p);
                break;
            case 30:
                //BMBT
                reportMap = getReportByCaseBMBT(stageID, numnegeri, p);
                break;
            case 31:
                //PBGSA
                reportMap = getReportByCasePBGSA(stageID, numnegeri, p);
                break;
            case 32:
                //PBMMK
                reportMap = getReportByCasePBMMK(stageID, numnegeri, p);
                break;
            case 33:
                //PTPBP
                reportMap = getReportByCasePTPBP(stageID, numnegeri, p);
                break;
            case 34:
                //PCRG
                reportMap = getReportByCasePCRG(stageID, numnegeri, p);
                break;
            case 35:
                //PRMMK
                reportMap = getReportByCasePRMMK(stageID, numnegeri, p);
                break;
            case 36:
                //WMRE
                reportMap = getReportByCaseWMRE(stageID, numnegeri, p);
                break;
            case 37:
                //PWGSA
                reportMap = getReportByCasePWGSA(stageID, numnegeri, p);
                break;
            case 38:
                //PWGSA
                reportMap = getReportByCaseRLPTG(stageID, numnegeri, p);
                break;
            case 39:
                //RYKN
                reportMap = getReportByCaseRYKN(stageID, numnegeri, p);
                break;
            case 40:
                //PTMTA
                reportMap = getReportByCasePTMTA(stageID, numnegeri, p);
                break;
            case 41:
                //MLCRG
                reportMap = getReportByCaseMLCRG(stageID, numnegeri, p);
                break;
            case 42:
                //MPJLB
                reportMap = getReportByCaseMPJLB(stageID, numnegeri, p);
                break;
            case 43:
                //PJBTR
                reportMap = getReportByCasePJBTR(stageID, numnegeri, p);
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePLPS(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("tolak_ringkas")) {
                    reportMap.put("reportName1", "DISMB_MLK.rdf");
                    reportMap.put("reportKod1", "MINB");
                } else if (stageID.contentEquals("semak_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPLPSPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("semak_draf_mmkn2")) {
                    reportMap.put("reportName1", "DISKMMKNPLPSandLPSPPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn3_3")) {
                    reportMap.put("reportName1", "DISKMMKNPLPSandLPSPPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("semak_surat_tolak2")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("semak_lulus_borang_5a")) {
                    reportMap.put("reportName1", "DISSrtKelulusan_MLK.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (stageID.contentEquals("semak_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISSPLPSL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("semak_borang_4a")) {
                    reportMap.put("reportName1", "DISB4AePLPS_MLK.rdf");
                    reportMap.put("reportKod1", "4A");
                    reportMap.put("reportName2", "DISBL1e_MLK.rdf");
                    reportMap.put("reportKod2", "L1e");
                } else if (stageID.contentEquals("tandatangan_borang_4a")) {
                    reportMap.put("reportName1", "DISB4AePLPS_MLK.rdf");
                    reportMap.put("reportKod1", "4A");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTPBMT1_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (stageID.contentEquals("sedia_surat_peringatan")) {
                    reportMap.put("reportName1", "DISSUTPBMT2_NS.rdf");
                    reportMap.put("reportKod1", "PER");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("semak_draf_jktd1")) {
                    reportMap.put("reportName1", "DISKJKTDPTD_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (stageID.contentEquals("semak_draf_jktd2")) {
                    reportMap.put("reportName1", "DISDrafJKTD_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (stageID.contentEquals("semak_draf_mmk1")) {
                    reportMap.put("reportName1", "DISKMMKNPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_draf_mmk2")) {
                    reportMap.put("reportName1", "DISKertasMMKNPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_huraian_syor")) {
                    reportMap.put("reportName1", "DISKMMKNPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_huraian_syor2_1")) {
                    reportMap.put("reportName1", "DISKMMKNPKPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_huraian_syor3")) {
                    reportMap.put("reportName1", "DISKertasMMKNPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("sedia_kertas_makluman")) {
                    reportMap.put("reportName1", "DISKertasMakluman.rdf");
                    reportMap.put("reportKod1", "MKLM");
                } else if (stageID.contentEquals("tandatangan_5a")) {
                    reportMap.put("reportName1", "DISSuratKelulusan_MLK.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (stageID.contentEquals("g_penyediaan_pu_pt")) {
                    reportMap.put("reportName1", "DISBPU_NS.rdf");
                    reportMap.put("reportKod1", "SIPU");
                    reportMap.put("reportName2", "DISSIPU.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (stageID.contentEquals("semak_draf_ptd1")) {
                    reportMap.put("reportName1", "DISKPPindaPTD_NS.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (stageID.contentEquals("semak_draf_ptd2")) {
                    reportMap.put("reportName1", "DISKertasPTD_PindaLuas.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (stageID.contentEquals("sedia_srt_byrn_tmbhn")) {
                    reportMap.put("reportName1", "DISSTBT_NS.rdf");
                    reportMap.put("reportKod1", "STPT");
                } else if (stageID.contentEquals("semak_draf_mmk2_1")) {
                    reportMap.put("reportName1", "DISKMMKNPKPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_draf_mmk2_2")) {
                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("semak_huraian_syor2_3")) {
                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (stageID.contentEquals("sedia_srt_byrn_tmbhn2")) {
                    reportMap.put("reportName1", "DISSrtBayaranTambahan.rdf");
                    reportMap.put("reportKod1", "STPT");
                } else if (stageID.contentEquals("semak_surat_keputusan")) {
                    reportMap.put("reportName1", "DISSrtKeputusanLPS.rdf");
                    reportMap.put("reportKod1", "SKPN");
                } else if (stageID.contentEquals("semak_borang_4a")) {
                    reportMap.put("reportName1", "DISBorang4A.rdf");
                    reportMap.put("reportKod1", "4A");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePBMT(String stageID, int numnegeri) {

        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.contentEquals("tolak_ringkas")) {
                    reportMap.put("reportName1", "DISMB_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "MINB");
                } else if (stageID.contentEquals("semak_surat_tolak")) {
                    reportMap.put("reportName1", "DISSrtMaklumanTolakPTD_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPLPS_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("semak_draf_mmkn2")) {
                    reportMap.put("reportName1", "DISKMMKNPBMTPTD_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("perakuan_ptd_risalat_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPBMTPTD_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn3_3")) {
                    reportMap.put("reportName1", "DISKMMKNPBMTPTG_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "RMN");
                    reportMap.put("reportName2", "DISSUMMMKN_MLK.rdf");
                    reportMap.put("reportKod2", "KMN");
                } else if (stageID.contentEquals("perakuan_ptg_risalat_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPBMTPTG_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPLPS_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("semak_surat_tolak2")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("semak_lulus_borang_5a")) {
                    reportMap.put("reportName1", "DISSrtKelulusan_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (stageID.contentEquals("semak_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISSPLPSL_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("g_penyediaan_pu_pt")) {
                    reportMap.put("reportName1", "DISSrtIringanPUPBMT_MLK.rdf");
                    reportMap.put("reportKod1", "OC");
                    reportMap.put("reportName2", "DISBorangPUPBMT_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                }
                break;
            case 2:
                //NEGERI SEMBILAN

                if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSPBMTGAWAL_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("sedia_surat_tolak_MMK")) {
                    reportMap.put("reportName1", "DISSPBMTG_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLT_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                }  else if (stageID.contentEquals("semak_laporan_tanah")) {
                    reportMap.put("reportName1", "DISLT_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPBMT_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTPBMT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (stageID.contentEquals("sedia_surat_peringatan")) {
                    reportMap.put("reportName1", "DISSUTPBMTNOTIS_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (stageID.contentEquals("cetak_kertas_jktd")) {
                    reportMap.put("reportName1", "DISJKTDPBMT_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (stageID.contentEquals("cetak_kertas_mmkKPTPTG")) {
                    reportMap.put("reportName1", "DISMMKNPBMT_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("tandatangan_5a")) {
                    reportMap.put("reportName1", "DISSPLPSL_PBMT_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("rekod_keputusan_MMK")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPBMT_NS.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("sedia_borang_5A")) {
                    reportMap.put("reportName1", "DISSrtKelulusan_PBMT_NS.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (stageID.contentEquals("semak_pu")) {
                    reportMap.put("reportName1", "DISSrtIringanPU_PBMT_NS.rdf");
                    reportMap.put("reportKod1", "OC");
                    reportMap.put("reportName2", "DISBPU_PBMT_NS.rdf");
                    reportMap.put("reportKod2", "PU");
                }


//                if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
//                    reportMap.put("reportName1", "DISSUTPBMT1_NS.rdf");
//                    reportMap.put("reportKod1", "SUT");
//                } else if (stageID.contentEquals("sedia_surat_peringatan")) {
//                    reportMap.put("reportName1", "DISSUTPBMT2_NS.rdf");
//                    reportMap.put("reportKod1", "PER");
//                } else if (stageID.contentEquals("sedia_surat_tolak")) {
//                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
//                    reportMap.put("reportKod1", "SMT");
//                } else if (stageID.contentEquals("semak_draf_jktd1")) {
//                    reportMap.put("reportName1", "DISKJKTDPTD_NS.rdf");
//                    reportMap.put("reportKod1", "JKTD");
//                } else if (stageID.contentEquals("semak_draf_jktd2")) {
//                    reportMap.put("reportName1", "DISDrafJKTD_NS.rdf");
//                    reportMap.put("reportKod1", "JKTD");
//                } else if (stageID.contentEquals("semak_draf_mmk1")) {
//                    reportMap.put("reportName1", "DISKMMKNPTD_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_draf_mmk2")) {
//                    reportMap.put("reportName1", "DISKertasMMKNPTD_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_huraian_syor")) {
//                    reportMap.put("reportName1", "DISKMMKNPTG_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_huraian_syor2_1")) {
//                    reportMap.put("reportName1", "DISKMMKNPKPTG_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_huraian_syor3")) {
//                    reportMap.put("reportName1", "DISKertasMMKNPTG_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("sedia_kertas_makluman")) {
//                    reportMap.put("reportName1", "DISKertasMakluman.rdf");
//                    reportMap.put("reportKod1", "MKLM");
//                } else if (stageID.contentEquals("g_penyediaan_pu_pt")) {
//                    reportMap.put("reportName1", "DISBPU_NS.rdf");
//                    reportMap.put("reportKod1", "SIPU");
//                    reportMap.put("reportName2", "DISSIPU.rdf");
//                    reportMap.put("reportKod2", "PU");
//                } else if (stageID.contentEquals("semak_draf_ptd1")) {
//                    reportMap.put("reportName1", "DISKPPindaPTD_NS.rdf");
//                    reportMap.put("reportKod1", "KPTD");
//                } else if (stageID.contentEquals("semak_draf_ptd2")) {
//                    reportMap.put("reportName1", "DISKertasPTD_PindaLuas.rdf");
//                    reportMap.put("reportKod1", "KPTD");
//                } else if (stageID.contentEquals("sedia_srt_byrn_tmbhn")) {
//                    reportMap.put("reportName1", "DISSTBT_NS.rdf");
//                    reportMap.put("reportKod1", "STPT");
//                } else if (stageID.contentEquals("semak_draf_mmk2_1")) {
//                    reportMap.put("reportName1", "DISKMMKNPKPTD_NS.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_draf_mmk2_2")) {
//                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("semak_huraian_syor2_3")) {
//                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
//                    reportMap.put("reportKod1", "MMKS");
//                } else if (stageID.contentEquals("sedia_srt_byrn_tmbhn2")) {
//                    reportMap.put("reportName1", "DISSrtBayaranTambahan.rdf");
//                    reportMap.put("reportKod1", "STPT");
//                } else if (stageID.contentEquals("semak_surat_keputusan")) {
//                    reportMap.put("reportName1", "DISSrtKeputusanLPS.rdf");
//                    reportMap.put("reportKod1", "SKPN");
//                } else if (stageID.contentEquals("semak_borang_4a")) {
//                    reportMap.put("reportName1", "DISBorang4A.rdf");
//                    reportMap.put("reportKod1", "4A");
//                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseMCMCL(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("semak_kertas_rencana")) {
                    reportMap.put("reportName1", "DISKRPMCMCL_MLK.rdf");
                    reportMap.put("reportKod1", "RENC");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISMCMCLG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("sedia_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISMCMCLL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePHLP(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPHLP_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("05kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUP_MLK.rdf");
                    reportMap.put("reportKod1", "SUP");
                } else if (stageID.contentEquals("07laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPHLP_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("13sediakan_notis2a")) {
                    reportMap.put("reportName1", "DISSMBStn_MLK.rdf");
                    reportMap.put("reportKod1", "MBST");
                    reportMap.put("reportName2", "DISSMStn_MLK.rdf");
                    reportMap.put("reportKod2", "SMST");
                    reportMap.put("reportName3", "DISSMStn1_MLK.rdf");
                    reportMap.put("reportKod3", "SMST1");
                    reportMap.put("reportName4", "DISB2A_MLK.rdf");
                    reportMap.put("reportKod4", "B2A");
                } else if (stageID.contentEquals("16siasatan_dan_perintah")) {
                    reportMap.put("reportName1", "DISKSTN_MLK.rdf");
                    reportMap.put("reportKod1", "KST");
                } else if (stageID.contentEquals("18surat_penolakan")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("20surat_pampasan")) {
                    reportMap.put("reportName1", "DISSTBPP_MLK.rdf");
                    reportMap.put("reportKod1", "SBPM");
                } else if (stageID.contentEquals("22surat_terima_bayaran")) {
                    reportMap.put("reportName1", "DISSBPP_MLK.rdf");
                    reportMap.put("reportKod1", "SBTT");
                } else if (stageID.contentEquals("18surat_penolakan")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("26sedia_pu")) {
                    reportMap.put("reportName1", "DISSMBU_MLK.rdf");
                    reportMap.put("reportKod1", "SBU");
                    reportMap.put("reportName2", "DISBPU_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (stageID.contentEquals("27surat_bayaran_upah_ukur")) {
                    reportMap.put("reportName1", "DISSBPU_MLK.rdf");
                    reportMap.put("reportKod1", "SBUU");
                } else if (stageID.contentEquals("29surat_sah_bayaran")) {
                    reportMap.put("reportName1", "DISSPBU_MLK.rdf");
                    reportMap.put("reportKod1", "SSB");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseLPSP(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTLPSPPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("perakuan_draf_rencana_ptg")) {
                    reportMap.put("reportName1", "DISKMMKNLPSPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("06TerimaMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNLPSPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("09SemakanMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNLPSPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("10PindaanMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNLPSPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("12RekodKeputusan")) {
                    reportMap.put("reportName1", "DISSrtKpsnMMKN_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("15SediaSurat")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSLPSPL_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSLPSPG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }
                } else if (stageID.contentEquals("16SemakSurat")) {
                    reportMap.put("reportName1", "DISB5A_MLK.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (stageID.contentEquals("18SediaPermit")) {
                    reportMap.put("reportName1", "DISBrg4Be_MLK.rdf");
                    reportMap.put("reportKod1", "4Be");
                    reportMap.put("reportName2", "DISBL2e_MLK.rdf");
                    reportMap.put("reportKod2", "L2e");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMMMCL(String stageID, int numnegeri, HakmilikPermohonan hp) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPMCMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("kemasukan")) {
                    reportMap.put("reportName1", "DISSrtProsesMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "SMM");
                } else if (stageID.contentEquals("laporan_tanah")) {
//                    reportMap.put("reportName1", "DISLTPLPSH_MLK.rdf");
                    reportMap.put("reportName1", "DISLTMCMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("semak_rencana")) {
//                    reportMap.put("reportName1", "DISSrtProsesMCMMMCL_MLK.rdf");
//                    reportMap.put("reportKod1", "SMM");
                    reportMap.put("reportName1", "DISKRPMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "RENC");
                } else if (stageID.contentEquals("perakuan_ptd")) {
                    reportMap.put("reportName1", "DISKRPMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "RENC");
                } else if (stageID.contentEquals("surat_kelulusan_brg5A")) {
                    reportMap.put("reportName1", "DISBrg5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "5A");
                    reportMap.put("reportName2", "DISNotisB5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod2", "5AA");
//                    reportMap.put("reportName3", "DISSBTAMCMMMCL_MLK.rdf");
//                    reportMap.put("reportKod3", "SBTA");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISMCMCLG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("dapatkan_penilaian")) {
                    reportMap.put("reportName1", "DISSSMPT_MLK.rdf");
                    reportMap.put("reportKod1", "SMPT");
                } else if (stageID.contentEquals("semak_kelulusan_5a")) {
                    reportMap.put("reportName1", "DISSMMMCLL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                    reportMap.put("reportName2", "DISB5A_MLK.rdf");
                    reportMap.put("reportKod2", "N5A");
                } else if (stageID.contentEquals("g_penyediaan_pu_pt")) {
                    reportMap.put("reportName1", "DISSrtIringanPU_MLK.rdf");
                    reportMap.put("reportKod1", "SIPU");
                    reportMap.put("reportName2", "DISBPU_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (stageID.contentEquals("sedia_jadual")) {
                    if (hp != null) {
                        if (hp.getHakmilik().getKodHakmilik().getKod().equals("GRN") || hp.getHakmilik().getKodHakmilik().getKod().equals("GM") || hp.getHakmilik().getKodHakmilik().getKod().equals("GMM") || hp.getHakmilik().getKodHakmilik().getKod().equals("PN") || hp.getHakmilik().getKodHakmilik().getKod().equals("PM")) {
                            reportMap.put("reportName1", "DISSBTASHKMCMMMCL_MLK.rdf");
                            reportMap.put("reportKod1", "SBHK");
                        } else if (hp.getHakmilik().getKodHakmilik().getKod().equals("HSM") || hp.getHakmilik().getKodHakmilik().getKod().equals("HMM") || hp.getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                            reportMap.put("reportName1", "DISSBTASHSMCMMMCL_MLK.rdf");
                            reportMap.put("reportKod1", "SBHS");
                        }
                    } else {
                        reportMap.put("reportName1", "DISSwwwwBTASHKMCMMMCL_MLK.rdf");
                        reportMap.put("reportKod1", "S12");
                    }



                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPTR(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("sedia_surat_ulasan_pengawal")) {
                    reportMap.put("reportName1", "DISSUBPR_MLK.rdf");
                    reportMap.put("reportKod1", "SUBPR");
                } else if (stageID.contentEquals("sedia_draf_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPPTRPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn3")) {
                    reportMap.put("reportName1", "DISKMMKNPPTRPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("terima_kpsn_mmkn_t")) {
                    reportMap.put("reportName1", "DISSKpsnG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("terima_kpsn_mmkn_l")) {
                    reportMap.put("reportName1", "DISSPPTRL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("sedia_borang_4e")) {
                    reportMap.put("reportName1", "DISB4E_MLK.rdf");
                    reportMap.put("reportKod1", "4E");
                }
                break;

            case 2: //NEGERI SEMBILAN

                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("sedia_kertas_makluman")) {
                    reportMap.put("reportName1", "");
                    reportMap.put("reportKod1", "MKLM");
                } else if (stageID.contentEquals("semak_borang_4e2")) {
                    reportMap.put("reportName1", "DISB4E_MLK.rdf");
                    reportMap.put("reportKod1", "4E");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRLPS(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                break;

            case 2: //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTGSA(String stageID, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_pemohonan")) {
                    reportMap.put("reportName1", "DISLPPPTGSA_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("08SemakSyorMMK")) {
                    reportMap.put("reportName1", "DISKMMKNPTGSAPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("13SemakSyorDraf")) {
                    reportMap.put("reportName1", "DISKMMKNPTGSAPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("RekodKeputusanMMK")) {
                    reportMap.put("reportName1", "DISSrtKpsnMMKN_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("16SemakDraf")) {
                    reportMap.put("reportName1", "DISDWP_MLK.rdf");
                    reportMap.put("reportKod1", "DWP");
                } else if (stageID.contentEquals("20CetakHantarWarta")) {
                    reportMap.put("reportName1", "DISSPTGSAL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("23SediaDrafWarta")) {
                    reportMap.put("reportName1", "DIS_PWRKNS.rdf");
                    reportMap.put("reportKod1", "DWPM");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPBB(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPBB_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("perakuan_draf_rencana_km")) {
                    reportMap.put("reportName1", "DISRJKBBPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("sedia_dokumen")) {
                    reportMap.put("reportName1", "DISRJKBBPTG_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("semak_surat_kelulusan")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("A")) {
                        reportMap.put("reportName1", "DISSKpsnLPPBB_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("XA")) {
                        reportMap.put("reportName1", "DISSKpsnG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }
                } else if (stageID.contentEquals("semak_borang_4c")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                    reportMap.put("reportName2", "DIS_BorangP1e_MLK.rdf");
                    reportMap.put("reportKod2", "P1e");
                } else if (stageID.contentEquals("tandatangan_borang_4c")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                } else if (stageID.contentEquals("keluar_surat")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBPTD(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPBB_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("semak_draf_rencana_ptd2")) {
                    reportMap.put("reportName1", "DISRJKBBPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("sedia_surat_lulus")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSKpsnLPBPTD_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSKpsnGPBPTD_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }

                } else if (stageID.contentEquals("rekodbayaran_sediapermit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                    reportMap.put("reportName2", "DIS_BorangP1e_MLK.rdf");
                    reportMap.put("reportKod2", "P1e");
                } else if (stageID.contentEquals("tandatangan_permit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                } else if (stageID.contentEquals("keluar_surat")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                }

                break;

            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportKod1", "LPE");
                    reportMap.put("reportName1", "DISLPPLPS_NS.rdf");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("sedia_laporan")) {
                    reportMap.put("reportName1", "DISLTPPBB_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("perakuan_draf_rencana_ptd")) {
                    reportMap.put("reportName1", "DISRJKBBPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("sedia_surat_lulus")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSKpsnLPBPTD_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSKpsnGPBPTD_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }

                } else if (stageID.contentEquals("rekodbayaran_sediapermit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                    reportMap.put("reportName2", "DIS_BorangP1e_MLK.rdf");
                    reportMap.put("reportKod2", "P1e");
                } else if (stageID.contentEquals("tandatangan_permit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                } else if (stageID.contentEquals("keluar_surat")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                }
                break;




        }
        return reportMap;
    }

    public HashMap getReportByCasePBPTG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPBB_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("perakuan_draf_rencana_ptg")) {
                    reportMap.put("reportName1", "DISRJKBBPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("semakan_rencana2")) {
                    reportMap.put("reportName1", "DISRJKBBPTG_MLK.rdf");
                    reportMap.put("reportKod1", "JKBB");
                } else if (stageID.contentEquals("sedia_surat_kelulusan")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNPBPTGL_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNPBPTGG_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }

                } else if (stageID.contentEquals("sedia_surat_lulustolak")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSKpsnLPBPPTG_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSKpsnGPBPPTG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }

                } else if (stageID.contentEquals("semak_permit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                    reportMap.put("reportName2", "DIS_BorangP1e_MLK.rdf");
                    reportMap.put("reportKod2", "P1e");
                } else if (stageID.contentEquals("tandatangan_permit")) {
                    reportMap.put("reportName1", "DISBrg4Ce_MLK.rdf");
                    reportMap.put("reportKod1", "4Ce");
                } else if (stageID.contentEquals("keluar_surat")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                }

                break;

            case 2: //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPRU(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("tolak_ringkas")) {
                    reportMap.put("reportName1", "DISMB_MLK.rdf");
                    reportMap.put("reportKod1", "MINB");
                } else if (stageID.contentEquals("semak_surat_tolak")) {
                    reportMap.put("reportName1", "DISSrtMaklumanTolakPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBPPRU_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (stageID.contentEquals("semak_draf_mmkn2")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn3_3")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("semak_surat_tolak2")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("semak_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISSPPRUL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("semak_permit")) {
                    reportMap.put("reportName1", "DISB4De_MLK.rdf");
                    reportMap.put("reportKod1", "4De");
                    reportMap.put("reportName2", "DIS_BorangP2e_MLK.rdf");
                    reportMap.put("reportKod2", "P2e");
                } else if (stageID.contentEquals("tandatangan_borang_4d")) {
                    reportMap.put("reportName1", "DISB4De_MLK.rdf");
                    reportMap.put("reportKod1", "4De");
                    reportMap.put("reportName2", "DIS_BorangP2e_MLK.rdf");
                    reportMap.put("reportKod2", "P2e");
                }
                break;

            case 2: //NEGERI SEMBILAN

                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPPRU_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } 
//                else if (stageID.contentEquals("laporan_tanah")) {
//                    reportMap.put("reportName1", "DISLTPPRU_NS.rdf");
//                    reportMap.put("reportKod1", "LT");
//                } 
                else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLT_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                }  else if (stageID.contentEquals("semak_laporan_tanah")) {
                    reportMap.put("reportName1", "DISLT_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                }
                else if (stageID.contentEquals("07kenalpasti_JT")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                    
                } else if (stageID.contentEquals("07BSedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                   
                } else if (stageID.contentEquals("10semak__kertas_mmk")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTD_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                    
                } else if (stageID.contentEquals("14semakhurai_draf")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                    
                } else if (stageID.contentEquals("16cetak_kertas_mmk")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                    
                } else if (stageID.contentEquals("17terima_keputusan_mmk")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPPRU_NS.rdf");
                    reportMap.put("reportKod1", "SKM");
                    
                } else if (stageID.contentEquals("18Siasatan")) {
                    reportMap.put("reportName1", "DISKS_NS.rdf");
                    reportMap.put("reportKod1", "LPK");
                    
                } else if (stageID.contentEquals("22cetak_kertas_maklum")) {
                    reportMap.put("reportName1", "DISKM_NS.rdf");
                    reportMap.put("reportKod1", "MLM");
                    
                } else if (stageID.contentEquals("27Tandatangan_Surat")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSPPRUL_NS.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    } else {
                       reportMap.put("reportName1", "DISSPPRUL_NS.rdf");
                       reportMap.put("reportKod1", "SL");
                    }
                } else if (stageID.contentEquals("30semak_borang4de")) {
                    reportMap.put("reportName1", "DISB4De_NS.rdf");
                    reportMap.put("reportKod1", "4De");
                    reportMap.put("reportName2", "DIS_BorangP2e_NS.rdf");
                    reportMap.put("reportKod2", "P2e");
                } else if (stageID.contentEquals("31tandatangan_4de")) {
                    reportMap.put("reportName1", "DISB4De_NS.rdf");
                    reportMap.put("reportKod1", "4De");
                    reportMap.put("reportName2", "DIS_BorangP2e_NS.rdf");
                    reportMap.put("reportKod2", "P2e");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRMP(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPSH_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTDH_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTH_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBH_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPLPSH_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("sedia_draf_rencana_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPRMPPTDH_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("terima_semak_risalat_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPRMPPTGH_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn3")) {
                    reportMap.put("reportName1", "DISKMMKNPRMPPTGH_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    reportMap.put("reportName1", "DISSrtKpsnMMKNH_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (stageID.contentEquals("sedia_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISSPRMPLH_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("sedia_permit")) {
                    reportMap.put("reportName1", "DISPRMPH_MLK.rdf");
                    reportMap.put("reportKod1", "PRMP");
                }
                break;



        }
        return reportMap;
    }

    public HashMap getReportByCaseLMCRG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("agihan_tugas")) {
                    reportMap.put("reportName1", "DISSUTPTG_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBPTG_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTLMCRG_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                    reportMap.put("reportName2", "DISKJKMLMCRG_MLK.rdf");
                    reportMap.put("reportKod2", "JKM");
                } else if (stageID.contentEquals("sedia_dokumen")) {
                    reportMap.put("reportName1", "DISKJKMLMCRG_MLK.rdf");
                    reportMap.put("reportKod1", "JKM");
                } else if (stageID.contentEquals("semak_draf_mmkn3")) {
                    reportMap.put("reportName1", "DISKMMKNLMCRG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("pindaan_draf_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNLMCRG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("rekod_keputusan_mmkn")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSLMCRGL_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSLMCRGG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }

                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePJLB(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("agihan_tugas")) {
                    reportMap.put("reportName1", "DISSUTPJLBPTG_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBPJLBPTG_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                    reportMap.put("reportName2", "DISKJKMLMCRG_MLK.rdf");
                    reportMap.put("reportKod2", "JKM");
                } else if (stageID.contentEquals("sedia_dokumen")) {
                    reportMap.put("reportName1", "DISKJKMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "JKM");
                } else if (stageID.contentEquals("sedia_draf_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("semak_draf_mmkn4")) {
                    reportMap.put("reportName1", "DISKMMKNPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("pindaan_draf_mmkn")) {
                    reportMap.put("reportName1", "DISKMMKNPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSPJLBG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("sedia_borang_f")) {
                    reportMap.put("reportName1", "DISBF_MLK.rdf");
                    reportMap.put("reportKod1", "BFE");
                } else if (stageID.contentEquals("pindaan_borang_f")) {
                    reportMap.put("reportName1", "DISBF_MLK.rdf");
                    reportMap.put("reportKod1", "BFE");
                } else if (stageID.contentEquals("sedia_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISSPJLBL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }

                break;

            case 2://NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBRZ(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("005_UlasanJabatanTeknikal")) {
                    reportMap.put("reportName1", "DISSUTPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "(DISSMUYBPBRZ_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("010_Semak")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("015_drafPerakuan")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (stageID.contentEquals("018_KeputusanMMKN") || stageID.contentEquals("057_RekodkanKeputusanMMKN")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPBRZL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPBRZG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "MMKT");
                    }
                } else if (stageID.contentEquals("022_SediaDrafWarta")) {
                    reportMap.put("reportName1", "DISdrafWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "DWP");
                } else if (stageID.contentEquals("023_suratDanWarta")) {
                    reportMap.put("reportName1", "DISSrtIringanWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "8PNB");
                } else if (stageID.contentEquals("031_TerimadanSediaDraf")) {
                    reportMap.put("reportName1", "DISdrafWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "DWP");
                } else if (stageID.contentEquals("032_SediaSuratdanWarta")) {
                    reportMap.put("reportName1", "DISSrtIringanWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "8PNB");
                } else if (stageID.contentEquals("040_SalinanWarta")) {
                    reportMap.put("reportName1", "DISSALINANWARTAPBRZ.rdf");
                    reportMap.put("reportKod1", "SLPBS");
                } else if (stageID.contentEquals("g_hantar_pu")) {
                    reportMap.put("reportName1", "DISSIPUPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "8JUPE");
                    reportMap.put("reportName2", "DISSBUU_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (stageID.contentEquals("054_SemakdanSediakanDraf")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (stageID.contentEquals("060_TerimaKeputusanSediaSurat")) {
                    reportMap.put("reportName1", "DISSALINANWARTAPBRZ.rdf");
                    reportMap.put("reportKod1", "SLPBS");
                } else if (stageID.contentEquals("066_SemakResalat")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("066A_SemakResalat")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("029_SemakNotaSiasatan")) {
                    reportMap.put("reportName1", "DIS_NOTIS_SIASATAN_MLK.rdf ");
                    reportMap.put("reportKod1", "NSIA");
                }
                break;

            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRIZ(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPRIZ_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPRIZ_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("RekodKeputusanMMK")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPRIZL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPRIZG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (stageID.contentEquals("sedia_surat_kelulusan")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSKpsnLPRIZ_MLK.rdf");
                            reportMap.put("reportKod1", "SL");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSPRIZG_MLK.rdf");
                            reportMap.put("reportKod1", "STP");
                        }

                    }
                } else if (stageID.contentEquals("sedia_draf_warta")) {
                    reportMap.put("reportName1", "DISdrafWartaRizab_MLK.rdf");
                    reportMap.put("reportKod1", "DWTR");
                    reportMap.put("reportName2", "DISSrtIringanWartaRizab_MLK.rdf");
                    reportMap.put("reportKod2", "8PNB");
                } else if (stageID.contentEquals("sedia_surat")) {
                    reportMap.put("reportName1", "DISSIWARTAPTD_MLK.rdf");
                    reportMap.put("reportKod1", "DSWD");
                } else if (stageID.contentEquals("terima_warta_rizab")) {
                    reportMap.put("reportName1", "DISSIWARTAPTG_MLK.rdf");
                    reportMap.put("reportKod1", "DSWG");
                }

                break;

            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLaporanPelukisPelan_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("007")) {
                    reportMap.put("reportName1", "DISSuratTolak_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("010")) {
                    reportMap.put("reportName1", "DISLaporanTanah_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("014")) {
                    reportMap.put("reportName1", "DISKertasPertimbanganPTD_NS.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (stageID.contentEquals("019")) {
                    reportMap.put("reportName1", "DISSijilBebasUpahUkur_NS.rdf");
                    reportMap.put("reportKod1", "SPPTG");
                } else if (stageID.contentEquals("g_penyediaan_pu")) {
                    reportMap.put("reportName1", "DISSrtIringanPU_NS.rdf");
                    reportMap.put("reportKod1", "8JUPE");
                    reportMap.put("reportName2", "DISBPU_NS.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (stageID.contentEquals("027")) {
                    reportMap.put("reportName1", "DISdrafWartaRizab_NS.rdf");
                    reportMap.put("reportKod1", "DWTR");
                } else if (stageID.contentEquals("029")) {
                    reportMap.put("reportName1", "DISdrafWartaRizab_NS.rdf");
                    reportMap.put("reportKod1", "DWTR");
                    reportMap.put("reportName2", "DISSrtIringanWartaRizab_NS.rdf");
                    reportMap.put("reportKod2", "8PNB");
                } else if (stageID.contentEquals("036")) {
                    reportMap.put("reportName1", "DISSuratLulus_NS.rdf");
                    reportMap.put("reportKod1", "ST");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYA(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("02SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYAL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("05SediaSuratBatal")) {
                    reportMap.put("reportName1", "DISSAPRAYA_MLK.rdf");
                    reportMap.put("reportKod1", "SBA");
                }


                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYL(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("04SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("05SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYLL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }


                break;

            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("01KemasukanKertasRingkas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTDH_NS.rdf");
                    reportMap.put("reportKod1", "KRPTD");
                } else if (stageID.contentEquals("03SemakSyorKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTD_NS.rdf");
                    reportMap.put("reportKod1", "KRPTD");
                } else if (stageID.contentEquals("05TerimaKeputusanL")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("07TerimaKeputusanT")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }


                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYK(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("04SemakMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNRAYKPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("05PerakuanMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNRAYKPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("09SemakanMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNRAYKPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("10PindaanMMKN")) {
                    reportMap.put("reportName1", "DISKMMKNRAYKPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("10PindaanMMKN")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNRAYKL_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNG_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (stageID.contentEquals("14SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("16SediaSuratBorang5A")) {
                    reportMap.put("reportName1", "DISSRAYKL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYT(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("004_Semak")) {
                    reportMap.put("reportName1", "DISKMMKNRAYTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("009_Semak_MMKN")) {
                    reportMap.put("reportName1", "DISKMMKNRAYTPTG_MLK.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("012_KeputusanMMKN")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNRAYTL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNRAYTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (stageID.contentEquals("018_SuratPenolakan")) {
                    reportMap.put("reportName1", "DISSrtTolakPTDRAYT_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("022_Semak")) {
                    reportMap.put("reportName1", "DISSKpsnLRAYT_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                    reportMap.put("reportName2", "DISB5ARAYT_MLK.rdf");
                    reportMap.put("reportKod2", "N5A");
                } else if (stageID.contentEquals("g_penyediaan_pu_pt")) {
                    reportMap.put("reportName1", "DISSrtIringanPURAYT_MLK.rdf");
                    reportMap.put("reportKod1", "0C");
                    reportMap.put("reportName2", "DISBorangPURAYT_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseJMRE(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePJTK(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePLPTD(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMMRE(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMPCRG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTBTC(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTBTS(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseBMBT(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("semak_draf_MMKN_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("semak_pindaan_MMKN_PTD2")) {
                    //NOTE : semak_draf_MMKN_PTD 2nd generate surat MMKN PTD (stage pembetulan MMKN)
                    //if this change, the report at semak_pindaan_MMKN_PTD must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("semak_syor_draf_MMKN_PTG")) {
                    reportMap.put("reportName1", "DISKMMKNBMBTPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (stageID.contentEquals("rekod_keputusan_MMKN_PTG")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    } else {
                        reportMap.put("reportName1", "AAA.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (stageID.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSBMBTG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("sedia_surat_lulus_Brg5A")) {
                    reportMap.put("reportName1", "DISSKpsnLBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                    reportMap.put("reportName2", "DISB5A_MLK.rdf");
                    reportMap.put("reportKod2", "N5A");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    for (PermohonanRujukanLuar mohonRujukLuar : p.getSenaraiRujukanLuar()) {
                        if (mohonRujukLuar.getAgensi() != null) {
                            if (mohonRujukLuar.getAgensi().getKategoriAgensi().getKod().equals("ADN")) {
                                reportMap.put("reportName2", "DISSMUYBBMBT_MLK.rdf");
                                reportMap.put("reportKod2", "SUA");
                            }
                        }
                    }
                }

                break;
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBGSA(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("07Semakan")) {
                    reportMap.put("reportName1", "DISBorangPU_MLK.rdf");
                    reportMap.put("reportKod1", "PU");
                }

                break;
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBMMK(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTPBP(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("07kpsn_pemohonan_dahulu")) {
                    reportMap.put("reportName1", "DISSTPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("11KenalPastiJabTek")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (stageID.contentEquals("13SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (stageID.contentEquals("19SemakSyorHuraiMMK")) {
                    reportMap.put("reportName1", "DISKMMKNPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (stageID.contentEquals("24SemakSyorDraf")) {
                    reportMap.put("reportName1", "DISKMMKNPTPBPPTG_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                }else if (stageID.contentEquals("30TerimaKeputusanSiasatan")) {
                    reportMap.put("reportName1", "DISKM_NS.rdf");
                    reportMap.put("reportKod1", "MLM");
                }else if (stageID.contentEquals("38SemakSahCetakBorang")) {
                    reportMap.put("reportName1", "DISPRMPH_NS.rdf");
                    reportMap.put("reportKod1", "PRMP");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePCRG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRMMK(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePWGSA(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseWMRE(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRLPTG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("01SediaKertasRingkas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTGH_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (stageID.contentEquals("04TerimaKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (stageID.contentEquals("06SemakSyorKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (stageID.contentEquals("11SediaKertasPTG")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (stageID.contentEquals("14TerimaKeputusanL")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (stageID.contentEquals("16SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("20SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRYKN(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("01SediaKertasRingkas")) {
                    reportMap.put("reportName1", "DISKMMKNRAYK_NS.rdf");
                    reportMap.put("reportKod1", "MMK");
                } else if (stageID.contentEquals("08JanaCetakKertas")) {
                    reportMap.put("reportName1", "DISKMMKNRAYK_NS.rdf");
                    reportMap.put("reportKod1", "MMK");
                } else if (stageID.contentEquals("16SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYKPTGL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (stageID.contentEquals("20SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYG_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTMTA(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("02SediaLaporan")) {
                    reportMap.put("reportName1", "DISLTPTMTA_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("04SediaNotisBicara")) {
                    reportMap.put("reportName1", "DISBN_NS.rdf");
                    reportMap.put("reportKod1", "NB");
                } else if (stageID.contentEquals("08SediaNotisBicara")) {
                    reportMap.put("reportName1", "DISBN_NS.rdf");
                    reportMap.put("reportKod1", "NB");
                } else if (stageID.contentEquals("11TerimaBayaranPerintah")) {
                    reportMap.put("reportName1", "DISSPerintahL_NS.rdf");
                    reportMap.put("reportKod1", "SPR");
                } else if (stageID.contentEquals("12SediaPerintahTolak")) {
                    reportMap.put("reportName1", "DISSPerintahT_NS.rdf");
                    reportMap.put("reportKod1", "SPR");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMLCRG(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("009_Cetak") || stageID.contentEquals("010_Semak")) {
                    reportMap.put("reportName1", "DISKJKMMLCRG_MLK.rdf");
                    reportMap.put("reportKod1", "JKM");
                } else if (stageID.contentEquals("027_Sediakansurat")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNMLCRGL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNMLCRGG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "MMKT");
                    }
                }
                break;
            case 2: //NEGERI SEMBILAN
                //TODO HERE
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseMPJLB(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("003_SediaDraf")) {
                    reportMap.put("reportName1", "DISKJKMMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "KPTG");
                } else if (stageID.contentEquals("009_SuratTolak")) {
                    reportMap.put("reportName1", "DISSrtTolakPTDMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("015_SediakanSurat")) {
                    reportMap.put("reportName1", "DISSKpsnLMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;
            case 2: //NEGERI SEMBILAN
                if (stageID.contentEquals("009")) {
                    reportMap.put("reportName1", "DISSrtTolakPTD_MLK.rdf");
                    reportMap.put("reportKod1", "STLK");
                } else if (stageID.contentEquals("015")) {
                    reportMap.put("reportName1", "DISSPJLBL_MLK.rdf");
                    reportMap.put("reportKod1", "SLSB");
                } else if (stageID.contentEquals("053")) {
                    reportMap.put("reportName1", "DISSrtKpsn_MLK.rdf");
                    reportMap.put("reportKod1", "SMM");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePJBTR(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (stageID.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (stageID.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (stageID.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBPJBTR_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (stageID.contentEquals("semak_draf_JKBB_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKJKBBPJBTRPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBBD");
                } else if (stageID.contentEquals("semak_masuk_bil_kertas_JKBB")) {
                    reportMap.put("reportName1", "DISKJKBBPJBTRPTG_MLK.rdf");
                    reportMap.put("reportKod1", "JKBBG");
                } else if (stageID.contentEquals("semak_draf_MMKN_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("semak_pindaan_MMKN_PTD2")) {
                    //NOTE : semak_draf_MMKN_PTD 2nd generate surat MMKN PTD (stage pembetulan MMKN)
                    //if this change, the report at semak_pindaan_MMKN_PTD must also change
                    reportMap.put("reportName1", "DISKMMKNPJBTRPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (stageID.contentEquals("semak_syor_draf_MMKN_PTG")) {
                    reportMap.put("reportName1", "DISKMMKNPJBTRPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (stageID.contentEquals("rekod_keputusan_MMKN_PTG")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPJBTRL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    } else {
                        reportMap.put("reportName1", "AAA.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (stageID.contentEquals("sedia_surat_tolak_PTD")) {
                    reportMap.put("reportName1", "DISSPJBTRG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (stageID.contentEquals("jana_surat_tolak_ringkas")) {
                    reportMap.put("reportName1", "DISSTLKPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "STLK");
                } else if (stageID.contentEquals("sedia_surat_lulus")) {
                    reportMap.put("reportName1", "DISSPJBTRL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
//                    reportMap.put("reportName1", "DISB4E_MLK.rdf");
//                    reportMap.put("reportKod1", "4E");
                }

//                else if (stageID.contentEquals("sedia_surat_lulus_Brg5A")) {
//                    reportMap.put("reportName1", "DISSKpsnLBMBT_MLK.rdf");
//                    reportMap.put("reportKod1", "SL");
//                    reportMap.put("reportName2", "DISB5A_MLK.rdf");
//                    reportMap.put("reportKod2", "N5A");
//                }

                break;
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }
}

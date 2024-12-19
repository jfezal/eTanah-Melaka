package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;
import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shazwan 14122011 0930 AM
 */
public class DisTandatanganManual {

    @Inject
    PelupusanService pservice;

    public String getKodDokByCasePBMT(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_draf_mmkn3_3")) {
                    kodDok = "RMN";
                } else if (stageId.equals("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_tolak2")) {
                    kodDok = "STP";
                } else if (stageId.equals("semak_lulus_borang_5a")) {
                    kodDok = "N5A";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = "SL";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("sedia_surat_tolak_MMK")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("sedia_surat_tolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    kodDok = "LT";
//                } else if (stageId.equalsIgnoreCase("g_charting_permohonan")) {
//                    kodDok = "LPE";
                } else if (stageId.equalsIgnoreCase("semak_laporan_tanah")) {
                    kodDok = "LT";
                } else if (stageId.equalsIgnoreCase("kenalpasti_jabatan_teknikal")) {
                    kodDok = "SUT";
                } else if (stageId.equalsIgnoreCase("cetak_kertas_jktd")) {
                    kodDok = "JKTD";
                } else if (stageId.equalsIgnoreCase("cetak_kertas_mmkKPTPTG")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("cetak_kertas_makluman")) {
                    kodDok = "MMKT";
                } else if (stageId.equalsIgnoreCase("tandatangan_5a")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_MMK")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("sedia_borang_5A")) {
                    kodDok = "N5A";
                } else if (stageId.equalsIgnoreCase("semak_pu")) {
                    kodDok = "OC";
                } else if (stageId.equalsIgnoreCase("semak_precomp")) {
                    kodDok = "OC";
                } else if (stageId.equalsIgnoreCase("sedia_srt_byrn_tmbhn")) {
                    kodDok = "STPT";
                } else if (stageId.equalsIgnoreCase("sedia_srt_byrn_tmbhn2")) {
                    kodDok = "STPT";
                } else if (stageId.equalsIgnoreCase("cetak_kertas_mmk2")) {
                    kodDok = "PMMK";
                }

                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePLPS(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_draf_mmkn3_3")) {
                    kodDok = "RMN";
                } else if (stageId.equals("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_tolak2")) {
                    kodDok = "STP";
                } else if (stageId.equals("semak_lulus_borang_5a")) {
                    kodDok = "N5A";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = "SL";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("08SdaSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("17SmknDrafUlsn")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("22SmkdanSyorPrkuanPTD")) {
                    kodDok = "RMN";
                } else if (stageId.equals("27SmkHuraianSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("34Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("39SmkSahkanCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("39CSmkSahkanCtk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("44SmkSahkanCtkBrg4Ae")) {
                    kodDok = "4A";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPBB(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, String kodKpsn) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semak_draf_rencana_km2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
                    kodDok = kodKpsn;
                } else if (stageId.equals("sedia_surat_tolak")) {
                    kodDok = "SWCTP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBPTD(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semak_draf_rencana_ptd2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
//                            String idAliranMhonFasa = "perakuan_draf_rencana_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
//                            FasaPermohonan mohonFasa = new FasaPermohonan();
//                            mohonFasa = pservice.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
//                            String keputusan = mohonFasa.getKeputusan().getKod(); 
//                            kodDok = keputusan.equals("L")?"SL":keputusan.equals("T")?"STP":new String(); 
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_tolak")) {
                    kodDok = "SWCTP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("semakhurai_draf_ptd")) {
                    kodDok = "RPPTD";
                } else if (stageId.equals("semak_surat_cetak_tlk")) {
                    kodDok = "STP";
                } else if (stageId.equals("sediasurattolak")) {
                    kodDok = "STP";
                } else if (stageId.equals("semak_surat_cetak_lls")) {
                    kodDok = "SL";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBPTG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, String kodKpsn) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semak_draf_rencana_ptg2")) {
                    kodDok = "JKBB";
                } //                         else if(stageId.equals("perakuan_draf_rencana_ptg")){
                //                            kodDok = "JKBB";
                //                         }
                else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = kodKpsn; //ORIGINAL DATA
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
                    kodDok = kodKpsn;
                } else if (stageId.equals("sedia_surat_tolak")) {
                    kodDok = "SWCTP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = permohonan.getKeputusan() != null ? "STP" : "SMT";
                } else if (stageId.equals("semak_laporan_tanah")) {
                    kodDok = "LTPD";
                } else if (stageId.equals("sedia_dokumen")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakhurai_draf_ptg")) {
                    kodDok = "RPPTD";
                } else if (stageId.equals("semakan_syor_draf")) {
                    kodDok = "RPPTG";
                } else if (stageId.equals("perakuan_draf")) {
                    kodDok = "RPPTG";
                } else if (stageId.equals("semak_cetak_surat_lls")) {
                    kodDok = "SL";
                } else if (stageId.equals("semak_cetak_surat_tlk")) {
                    kodDok = "STP";
                } else if (stageId.equals("sediasurattolak")) {
                    kodDok = "STP";
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semakan_rencana2")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("keluar_surat")) {
                    kodDok = "SWCTP";
                } else if (stageId.equals("sedia_surat_kelulusan")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equals("semak_surat_lulus")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRAYA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("04SemakMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("09SemakanMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("14SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("04SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("16SediaSuratBorang5A")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("02SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratBatal")) {
                    kodDok = "SBA";
                } else if (stageId.equalsIgnoreCase("12RekodKeputusan")) {
                    kodDok = "SKM";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRAYK(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("04SemakMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("09SemakanMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("14SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("04SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("16SediaSuratBorang5A")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("02SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratBatal")) {
                    kodDok = "SBA";
                } else if (stageId.equalsIgnoreCase("12RekodKeputusan")) {
                    kodDok = "SKM";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("03SmkKrtsRngks")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("06SmkHurdanSyor")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("13Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equalsIgnoreCase("16SedSrtMklmKptsnLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("19SedSrtMklmKptsnTlk")) {
                    kodDok = "STT";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRAYL(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("04SemakMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("09SemakanMMKN")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("14SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("04SediaSuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("16SediaSuratBorang5A")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("02SediaSuratLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("05SediaSuratBatal")) {
                    kodDok = "SBA";
                } else if (stageId.equalsIgnoreCase("12RekodKeputusan")) {
                    kodDok = "SKM";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("05TrmKptsndanSedSrtLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("08TrmKptsndanSedSrtTlk")) {
                    kodDok = "STT";
                } else if (stageId.equalsIgnoreCase("01DaftarPermohonan")) {
                    kodDok = "KRPTD";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePRMP(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("sedia_draf_rencana_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("terima_semak_risalat_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn3")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("pindaan_draf_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("sedia_surat_kelulusan")) {
                    kodDok = permohonan.getKeputusan().getKod().equals("L") ? "SL" : permohonan.getKeputusan().getKod().equals("T") ? "STP" : new String();
                } else if (stageId.equalsIgnoreCase("sedia_permit")) {
                    kodDok = "PRMP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseLMCRG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("sedia_dokumen")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("sedia_draf_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn3")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn4")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("pindaan_draf_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("sedia_surat_tolak")) {
                    kodDok = "STP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePJLB(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("laporan_tanah")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("sedia_dokumen")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("sedia_draf_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn4")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("pindaan_draf_mmkn")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("sedia_surat_tolak")) {
                    kodDok = "STP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                if (stageId.equalsIgnoreCase("10SmkdanSyorPerakuanPTG")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("16SmkdanKmkniKrtsJKSMN")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("19SmkdanPerakukanMnitJKSMN")) {
                    kodDok = "MNMJ";
                } else if (stageId.equalsIgnoreCase("27SmkdanMklmMklmtKmskni")) {
                    kodDok = "JKM";
                } else if (stageId.equalsIgnoreCase("33SmkDrfKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("40SmkdanKmskniKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("48SmkdanMklmMklmtKmskni")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("55SedSrtMklmPemohonMembayar")) {
                    kodDok = "MDP";
                } else if (stageId.equalsIgnoreCase("70SmkSrtBtlPermohonan")) {
                    kodDok = "SMT";
                } else if (stageId.equalsIgnoreCase("78SedSrtMklmKptsn")) {
                    kodDok = "SMT";
                } else if (stageId.equalsIgnoreCase("85SedSrtMklmKptsnLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("94SmkdanKemukaUntkDiperakukan")) {
                    kodDok = "F";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPRU(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn3_3")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("semak_surat_tolak2")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("semak_surat_kelulusan")) {
                    kodDok = "SLPRU";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("10semak__kertas_mmk")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("14semakhurai_draf")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("17terima_keputusan_mmk")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("21perakuan_kertas_maklum")) {
                    kodDok = "MLM";
                } else if (stageId.equalsIgnoreCase("27Tandatangan_Surat")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("30semak_borang4de")) {
                    kodDok = "4De";
                } else if (stageId.equalsIgnoreCase("31tandatangan_4de")) {
                    kodDok = "4De";
                }

                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPTR(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("sedia_surat_ulasan_pengawal")) {
                    kodDok = "SUBPR";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkn3")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("rekod_keputusan_mmkn")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("RekodKeputusanMMK")) {
                    kodDok = "SKM";
                } else if (stageId.equalsIgnoreCase("terima_kpsn_mmkn_l")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("sedia_surat_lulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("terima_kpsn_mmkn_t")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("sedia_surat_tolak")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("13SemakSyorDraf")) {
                    kodDok = "RMN";
                }



                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                if (stageId.equalsIgnoreCase("11Smk_Draf_MMK")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("16Smk_Huraian")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("23Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equalsIgnoreCase("28Smk_Sahkan_Ctk")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("28CSmk_Sahkan_Ctk")) {
                    kodDok = "SMT";
                } else if (stageId.equalsIgnoreCase("34Pengesahan_Brg4E")) {
                    kodDok = "4E";
                } else {
                }

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRLPTG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //INSERT CODE HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("02SedKrtsRngksPTG")) {
                    kodDok = "KRPTG";
                } else if (stageId.equalsIgnoreCase("05TrmSedHuraian")) {
                    kodDok = "KPTG";
                } else if (stageId.equalsIgnoreCase("11TrmKptsnSiasatan")) {
                    kodDok = "KPTG";
                } else if (stageId.equalsIgnoreCase("15SedSrtKptsnLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("18SedSrtKptsnTlk")) {
                    kodDok = "STT";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRYKN(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //INSERT CODE HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("03SmkKrtsRngks")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("06SmkHurdanSyor")) {
                    kodDok = "MMK";
                } else if (stageId.equalsIgnoreCase("13Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equalsIgnoreCase("16SedSrtMklmKptsnLulus")) {
                    kodDok = "SL";
                } else if (stageId.equalsIgnoreCase("19SedSrtMklmKptsnTlk")) {
                    kodDok = "STT";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePTMTA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //INSERT CODE HERE
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("04SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equalsIgnoreCase("06SedNotisPerbicaraan")) {
                    kodDok = "NB";
                } else if (stageId.equalsIgnoreCase("10TrmByrn")) {
                    kodDok = "SPR";
                } else if (stageId.equalsIgnoreCase("16SedNotisPerbicaraan")) {
                    kodDok = "NB";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePRIZ(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, String kodKpsn) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                //INSERT CODE HERE
                if (stageId.equalsIgnoreCase("semak_draf_mmkn1")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("13SemakSyorDraf")) {
                    kodDok = "RMN";
                } else if (stageId.equalsIgnoreCase("sedia_surat_kelulusan")) {
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        kodDok = "SL";
                    } else if (permohonan.getKeputusan().getKod().equals("T")) {
                        kodDok = "STP";
                    }
                } else if (stageId.equalsIgnoreCase("sedia_bebas_byrn_ukur")) {
                    kodDok = "SPU";
                } else if (stageId.equalsIgnoreCase("trm_bebas_byrn_ukur")) {
                    kodDok = "SIPBU";
                } else if (stageId.equalsIgnoreCase("trm_bebas_byrn_ukur2")) {
                    kodDok = "8SIJU";
                } else if (stageId.equalsIgnoreCase("sedia_draf_warta")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kodDok = "SKM";
                } else if (stageId.equals("sedia_draf_warta")) {
                    kodDok = "8PNB";
                } else if (stageId.equals("sedia_surat2")) {
                    kodDok = "SIBPJ";
                } else if (stageId.equals("sedia_surat")) {
                    kodDok = "DSWD";
                } else if (stageId.equals("terima_warta_rizab")) {
                    kodDok = "DSWG";
                }


                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("007")) {
                    kodDok = "STP";
                } else if (stageId.equalsIgnoreCase("011")) {
                    kodDok = "KPTD";
                } else if (stageId.equalsIgnoreCase("017")) {
                    kodDok = "SPPTG";
                } else if (stageId.equalsIgnoreCase("021")) {
                    kodDok = "8JUPE";
                } else if (stageId.equalsIgnoreCase("029")) {
                    kodDok = "8DRA";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseLPSP(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, String kodKpsn) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("15SediaSurat")) {
                    kodDok = kodKpsn;
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kodDok = "SKM";
                } else if (stageId.equals("13SemakSyorDraf")) {
                    kodDok = "JKBB";
                } else if (stageId.equals("semak_draf_rencana_ptg2")) {
                    kodDok = "JKBB";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("08SdaSrTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("17SmkDrafMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("22SmkHuraian")) {
                    kodDok = "RMN";
                } else if (stageId.equals("29Perakukan")) {
                    kodDok = "MLM";
                } else if (stageId.equals("34SSmkShknCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("40SmkShknCtk4B")) {
                    kodDok = "4Be";
                }

                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBGSA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("02TrmPsbu")) {
                    kodDok = "SBU";
                } else if (stageId.equals("07Semakan")) {
                    kodDok = "OC";
                } else if (stageId.equals("16SemakdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("21SmkdanSdkanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("33Rekodkputsan")) {
                    kodDok = "SKM";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("06SmkdanSyor")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("11SmkdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("16SmkDraf")) {
                    kodDok = "RMN";
                } else if (stageId.equals("23Perakuan")) {
                    kodDok = "KSM";
                }
                
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePHLA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("05SedSrtPnlkn")) {
                    kodDok = "SMT";
                } else if (stageId.equals("06SmkCtkdanTndtngn")) {
                    kodDok = "SMT";
                } else if (stageId.equals("14SmkdanJana")) {
                    kodDok = "B2A";
                } else if (stageId.equals("19SmkdanSyor")) {
                    kodDok = "KST";
                } else if (stageId.equals("24SedSrtPnlkn")) {
                    kodDok = "STP";
                } else if (stageId.equals("25SmkdanCtkTndtngn")) {
                    kodDok = "STP";
                } else if (stageId.equals("27SmkdanCtkTndtngn")) {
                    kodDok = "SBPM";
                } else if (stageId.equals("29SmkCtkdanTndtngn")) {
                    kodDok = "SMT";
                } else if (stageId.equals("30SmkCtkTndtngn")) {
                    kodDok = "SBTT";
                } else if (stageId.equals("43SmkdanShknPU")) {
                    kodDok = "OC";
                }
                break;
            case 2:
                //NEGERI SEMBILAN

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseMMMCL(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, HakmilikPermohonan hakmilikPermohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("kemasukan")) {
                    kodDok = "SMM";
                } else if (stageId.equals("perakuan_ptd")) {
                    kodDok = "RENC";
                } else if (stageId.equals("sedia_surat_penilaian")) {
                    kodDok = "8JPP";
                } else if (stageId.equals("surat_kelulusan_brg5A")) {
                    kodDok = "5A";
                } else if (stageId.equals("sedia_jadual")) {
                    if (hakmilikPermohonan != null) {
                        if (hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GM") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("GMM") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PN") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("PM")) {
                            kodDok = "SBHK";
                        } else if (hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("HSM") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("HMM") || hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod().equals("HSD")) {
                            kodDok = "SBHS";
                        }
                    } else {
                        kodDok = "SS";
                    }
//                    kodDok = "SL"; //Repot x de lagi (Data sementara shj)
                } else if (stageId.equals("sedia_surat_tolak")) {
                    kodDok = "STP"; //Repot x de lagi (Data sementara shj)
                }
                break;
            case 2:
                //NEGERI SEMBILAN

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRAYT(String stageId, int numnegeri, String kodCaw, Permohonan permohonan, String kodKpsn) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("004_Semak")) {
                    kodDok = "RMN"; //Repot x de lagi (Data sementara shj)
                } else if (stageId.equals("009_Semak_MMKN")) {
                    kodDok = "RMN"; //Repot x de lagi (Data sementara shj)
                } else if (stageId.equals("012_KeputusanMMKN")) {
                    kodDok = "SKM"; //Repot x de lagi (Data sementara shj)
                } else if (stageId.equals("018_SuratPenolakan")) {
                    kodDok = "STP"; //Repot x de lagi (Data sementara shj)
                } else if (stageId.equals("022_Semak")) {
                    kodDok = kodKpsn; //Repot x de lagi (Data sementara shj)
                }

                break;
            case 2:
                //NEGERI SEMBILAN

                break;
        }
        return kodDok;
    }

    public String getKodDokByCasePBRZ(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("010_Semak")) {
                    kodDok = "RMN";
                } else if (stageId.equals("015_drafPerakuan")) {
                    kodDok = "RMN";
                } else if (stageId.equals("018_KeputusanMMKN")) {
                    kodDok = "SKM";
                } else if (stageId.equals("022_SediaDrafWarta")) {
                    kodDok = "DWP";
                } else if (stageId.equals("023_suratDanWarta")) {
                    kodDok = "8PNB";
                } else if (stageId.equals("029_SemakNotaSiasatan")) {
                    kodDok = "NSIA";
                } else if (stageId.equals("031_TerimadanSediaDraf")) {
                    kodDok = "DWP";
                } else if (stageId.equals("032_SediaSuratdanWarta")) {
                    kodDok = "8PNB";
                } else if (stageId.equals("054_SemakdanSediakanDraf")) {
                    kodDok = "RMN";
                } else if (stageId.equals("057_RekodkanKeputusanMMKN")) {
                    kodDok = "SKM";
                } else if (stageId.equals("060_TerimaKeputusanSediaSurat")) {
                    kodDok = "SLPBS";
                } else if (stageId.equals("066A_SemakResalat")) {
                    kodDok = "RMN";
                } else if (stageId.equals("066_SemakResalat")) {
                    kodDok = "RMN";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("09SmkSyrDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("13SmkHuraianSyr")) {
                    kodDok = "RMN";
                } else if (stageId.equals("g_hantar_pu")) {
                    kodDok = "8JUPE";
                } else if (stageId.equals("24SmkDrfWrt")) {
                    kodDok = "DWP";
                } else if (stageId.equals("27SmkDrfWrta")) {
                    kodDok = "DWP";
                } else if (stageId.equals("g_hantar_pu")) {
                    kodDok = "PU";
                } else if (stageId.equals("33SmkNotisSiasatan")) {
                    kodDok = "NSIA";
                } else if (stageId.equals("37SmkDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("44SmkShknCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("47SmkShknCtkTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("57SmkShknCtk")) {
                    kodDok = "SL";
                } 
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBHL(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("14HntrKptsn")) {
                    kodDok = "SMT";
                } else if (stageId.equals("16MnyrhdanMnyiarkn")) {
                    kodDok = "B2A";
                } else if (stageId.equals("19SdknSrt")) {
                    kodDok = "STP";
                } else if (stageId.equals("26Semakan")) {
                    kodDok = "PU";
                } else if (stageId.equals("12SmkKrtsPrtmbgn")) {
                    kodDok = "RENC";
                }

                break;
            case 2:
                //NEGERI SEMBILAN

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseBMBT(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("semak_draf_MMKN_PTD")) {
                    kodDok = "MMKND";
                } else if (stageId.equals("semak_pindaan_MMKN_PTD2")) {
                    kodDok = "MMKND";
                } else if (stageId.equals("semak_syor_draf_MMKN_PTG")) {
                    kodDok = "MMKNG";
                } else if (stageId.equals("rekod_keputusan_MMKN_PTG")) {
                    kodDok = "SKM";
                } else if (stageId.equals("sedia_surat_tolak")) {
                    kodDok = "STP";
                } else if (stageId.equals("sedia_surat_lulus_Brg5A")) {
                    kodDok = "SL";
                } else if (stageId.equals("sdia_drf_pngcualian_ukr")) {
                    kodDok = "SPU";
                } else if (stageId.equals("terima_pu_dari_ptd")) {
                    kodDok = "SIPBU";
                } else if (stageId.equals("terima_pngcualian_ukur")) {
                    kodDok = "8SIJU";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("08SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("laporan_tanah")) {
                    kodDok = "LT";
                } else if (stageId.equals("12KnlpstiJbtnTnkl")) {
                    kodDok = "SUT";
                } else if (stageId.equals("14SedSrtPeringatan")) {
                    kodDok = "SUT";
                } else if (stageId.equals("17SmkDrfJKTD")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("22SmkSyorPerakuanPTD")) {
                    kodDok = "RMN";
                } else if (stageId.equals("27SmkDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("33TrmKptsnSiasatan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("39SedSyrtKelulusan")) {
                    kodDok = "5AA";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseMLCRG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("009_Cetak")) {
                    kodDok = "RMN";
                } else if (stageId.equals("010_Semak")) {
                    kodDok = "RMN";
                } else if (stageId.equals("027_Sediakansurat")) {
                    kodDok = "MMKT";
                }

                break;
            case 2:
                //NEGERI SEMBILAN

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseMPJLB(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("003_SediaDraf")) {
                    kodDok = "JKM";
                } else if (stageId.equals("009_SuratTolak")) {
                    kodDok = "STP";
                } else if (stageId.equals("015_SediakanSurat")) {
                    kodDok = "SL";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("07MklmSyorPerakuanPTG")) {
                    kodDok = "KPTG";
                }else if (stageId.equals("12SedSrtKelulusandanEndorsan")) {
                    kodDok = "SL";
                }else if (stageId.equals("18SedSrtTlk")) {
                    kodDok = "SMT";
                }else if (stageId.equals("29SmkDrafKrtsJKSMN")) {
                    kodDok = "JKM";
                }else if (stageId.equals("35SmkdanKmskniKrtsJKSMN")) {
                    kodDok = "JKM";
                }else if (stageId.equals("35ASmkdanKmskniKrtsMMK")) {
                    kodDok = "RMN";
                }else if (stageId.equals("38SmkMinitMsyrtJKSMN")) {
                    kodDok = "MNMJ";
                }else if (stageId.equals("48SmkdanMklm")) {
                    kodDok = "JKM";
                }else if (stageId.equals("58SmkdanMklm")) {
                    kodDok = "RMN";
                }else if (stageId.equals("65SmkDraf")) {
                    kodDok = "RMN";
                }else if (stageId.equals("72SmkKmskniKrtsMMK")) {
                    kodDok = "RMN";
                }else if (stageId.equals("76SedSrtMklmKptsnTlk")) {
                    kodDok = "SMT";
                }else if (stageId.equals("83SedSrtKptsnLulus")) {
                    kodDok = "SL";
                }else if (stageId.equals("91SmkdanKemuka")) {
                    kodDok = "F";
                }

                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseMPWGSA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("04SedPermhnanSBU")) {
                    kodDok = "SBU";
                } else if (stageId.equals("08SedPUUtkPW")) {
                    kodDok = "PU";
                } else if (stageId.equals("17PerakudanCtkWrta")) {
                    kodDok = "DW";
                } else if (stageId.equals("18KmskniMklmnAgensi")) {
                    kodDok = "SRAGN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePJBTR(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("jana_surat_tolak_ringkas")) {
                    kodDok = "STLK";
                } else if (stageId.equals("semak_draf_JKBB_PTD")) {
                    kodDok = "JKBBD";
                } else if (stageId.equals("semak_masuk_bil_kertas_JKBB")) {
                    kodDok = "JKBBG";
                } else if (stageId.equals("rekod_keputusan_MMKN_PTG")) {
                    kodDok = "SKM";
                } else if (stageId.equals("semak_syor_draf_MMKN_PTG")) {
                    kodDok = "RMN";
                } else if (stageId.equals("sedia_surat_tolak_PTD")) {
                    kodDok = "STP";
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = "SL";
                } else if (stageId.equals("cetak_borang_pajakan")) {
                    kodDok = "DWP";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("jana_surat_tolak_ringkas")) {
                    kodDok = "STLK";
                } else if (stageId.equals("semak_draf_JKBB_PTD")) {
                    kodDok = "JKBBD";
                } else if (stageId.equals("semak_masuk_bil_kertas_JKBB")) {
                    kodDok = "JKBBG";
                } else if (stageId.equals("sedia_surat_kpd_PTD")) {
                    kodDok = "SKM";
                } else if (stageId.equals("sedia_surat_tolak_PTD")) {
                    kodDok = "STLK";
                } else if (stageId.equals("sedia_surat_lulus")) {
                    kodDok = "SL";
                } else if (stageId.equals("cetak_borang_pajakan")) {
                    kodDok = "DWP";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePLPTD(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("09SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("15SmkHurSyorPTD")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("20SmkShkandanCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("23SmkSahkndanCtk")) {
                    kodDok = "4A";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBMMK(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semakhurai_draf_ptg")) {
                    kodDok = "MMK";
                } else if (stageId.equals("cetak_kertas_mmk")) {
                    kodDok = "MMK";
                } else if (stageId.equals("semak_cetak_surat_tlk")) {
                    kodDok = "STP";
                } else if (stageId.equals("sediasurattolak")) {
                    kodDok = "STP";
                } else if (stageId.equals("semak_cetak_surat_lls")) {
                    kodDok = "SL";
                } else if (stageId.equals("perakuan_kertas_maklum")) {
                    kodDok = "KSM";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePTPBP(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("19SemakSyorHuraiMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("07kpsn_pemohonan_dahulu")) {
                    kodDok = "SMT";
                } else if (stageId.equals("21TerimaDraf")) {
                    kodDok = "RMN";
                } else if (stageId.equals("30TerimaKeputusanSiasatan")) {
                    kodDok = "RMN";
                } else if (stageId.equals("38SemakSahCetakBorang")) {
                    kodDok = "PRMP";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRLPS(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("08SmkHurSyorPerakn")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("13SmkShknCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("13CSmkShknCtkSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("17PengshnBrg4A")) {
                    kodDok = "4A";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePRMMK(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("08SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("14SmkDrfKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("18SmkHuraiandanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("25Perakuan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("27SedSBU")) {
                    kodDok = "SBU";
                } else if (stageId.equals("33SmkShknCtk")) {
                    kodDok = "SL";
                } else if (stageId.equals("36SmkShknCtkTlk")) {
                    kodDok = "SMT";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePCRG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("08SmkdanSyor")) {
                    kodDok = "JKM";
                } else if (stageId.equals("12SmkdanKmsknKrtsJKSMN")) {
                    kodDok = "JKM";
                } else if (stageId.equals("14SedMinitMsyrtJKSMN")) {
                    kodDok = "MNMJ";
                } else if (stageId.equals("21SmkdanMklm")) {
                    kodDok = "JKM";
                } else if (stageId.equals("27SmkDrafMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("30CSmkdanKmskniKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("37SmkdanPerakukanSrt")) {
                    kodDok = "SMT";
                } else if (stageId.equals("43SmkdanPerakukanLulus")) {
                    kodDok = "SL";
                } else if (stageId.equals("49SedSrtKptsnBtl")) {
                    kodDok = "SMT";
                } else if (stageId.equals("54SmkdanKemukaPTG")) {
                    kodDok = "BRD";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseMPCRG(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("07SmkdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("12CtkKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("19SmkdanMsknSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("26SmkSrtKptsn")) {
                    kodDok = "SMT";
                } else if (stageId.equals("33SmkSrtKptsnLulus")) {
                    kodDok = "SL";
                } else if (stageId.equals("38CSedSrtKptsnBtl")) {
                    kodDok = "SMT";
                } else if (stageId.equals("44SmkdanKemuka")) {
                    kodDok = "BRD";
                }
                break;
        }

        return kodDok;
    }
    
    public String getKodDokByCaseMMRE(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("09SmkdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("14SmkHuraiandanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("21Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("25TdtgnSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("30SmkdanTndtgn")) {
                    kodDok = "SBU";
                } else if (stageId.equals("g_hantar_surat")) {
                    kodDok = "SBU";
                } else if (stageId.equals("33PenyediaanDrfWrt")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("37SmkSmlDrfWrt")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("41PenyediaanWrt")) {
                    kodDok = "DW";
                } else if (stageId.equals("46PenyediaanBrgJdlPertama")) {
                    kodDok = "BA";
                }
                
                break;
        }

        return kodDok;
    }
    public String getKodDokByCasePTGSA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("09SmkDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("14SmkDrfKrtsMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("20TrmKptsnSiasatan")) {
                    kodDok = "KSM";
                }else if (stageId.equals("24SedDrfWrtPmbtln")) {
                    kodDok = "DWPG";
                }else if (stageId.equals("30SmkDrfWrtPenamatan")) {
                    kodDok = "DWPG";
                }                
                break;
        }

        return kodDok;
    }
    
    public String getKodDokByCasePTBTS(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("08SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("laporan_tanah")) {
                    kodDok = "LT";
                } else if (stageId.equals("12KnlpstiJbtnTnkl")) {
                    kodDok = "SUT";
                } else if (stageId.equals("14SedSrtPeringatan")) {
                    kodDok = "SUT";
                } else if (stageId.equals("17SmkDrfJKTD")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("22SmkSyorPerakuanPTD")) {
                    kodDok = "RMN";
                } else if (stageId.equals("27SmkDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("33TrmKptsnSiasatan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("39SedSyrtKelulusan")) {
                    kodDok = "5AA";
                }
                break;
        }

        return kodDok;
    }
    
    public String getKodDokByCasePTBTC(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("08SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("laporan_tanah")) {
                    kodDok = "LT";
                } else if (stageId.equals("12KnlpstiJbtnTnkl")) {
                    kodDok = "SUT";
                } else if (stageId.equals("14SedSrtPeringatan")) {
                    kodDok = "SUT";
                } else if (stageId.equals("17SmkDrfJKTD")) {
                    kodDok = "JKTD";
                } else if (stageId.equals("22SmkSyorPerakuanPTD")) {
                    kodDok = "RMN";
                } else if (stageId.equals("27SmkDrfMMK")) {
                    kodDok = "RMN";
                } else if (stageId.equals("33TrmKptsnSiasatan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("39SedSyrtKelulusan")) {
                    kodDok = "5AA";
                }
                break;
        }

        return kodDok;
    }
    
    public String getKodDokByCaseWMRE(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("10SmkdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("15SmkHuraiandanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("29Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("32SedSrtTlk")) {
                    kodDok = "SMT";
                } else if (stageId.equals("41PenyediaanDrfWrt")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("43SmkDrfWrt")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("47TrmdanSedWrt")) {
                    kodDok = "DW";
                } else if (stageId.equals("g_penyediaan_pu")) {
                    kodDok = "OC";
                }
                break;
        }

        return kodDok;
    }
    
    public String getKodDokByCaseJMRE(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("04SmkLapDok")) {
                    kodDok = "RMN";
                } else if (stageId.equals("11Perakuan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("13TrmKptsnMMK")) {
                    kodDok = "DWTR";
                } else if (stageId.equals("16TndkanPmbtln")) {
                    kodDok = "DWTR";
                } 
                break;
        }

        return kodDok;
    }
    public String getKodDokByCaseBMRE(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equals("10SmkdanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("15SmkHuraiandanSyor")) {
                    kodDok = "RMN";
                } else if (stageId.equals("22Perakukan")) {
                    kodDok = "KSM";
                } else if (stageId.equals("g_penyediaan_pu")) {
                    kodDok = "OC";
                } else if (stageId.equals("32PenyediaanDrfWrt")) {
                    kodDok = "DW";
                } else if (stageId.equals("34SmkSemulaDrfWrt")) {
                    kodDok = "DW";
                } else if (stageId.equals("39TrmdanSedWrt")) {
                    kodDok = "DW";
                } else if (stageId.equals("45PenyediaanBrgJdlPertama")) {
                    kodDok = "BA";
                }
                break;
        }

        return kodDok;
    }
}

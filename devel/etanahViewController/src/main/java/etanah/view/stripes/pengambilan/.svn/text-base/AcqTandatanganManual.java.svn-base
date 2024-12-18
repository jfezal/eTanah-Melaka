package etanah.view.stripes.pengambilan;

import etanah.view.stripes.pelupusan.disClass.*;
import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodDokumen;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import etanah.dao.KodDokumenDAO;

/**
 * @author Erra 24122014 1230 AM
 */
public class AcqTandatanganManual {

    @Inject
    PelupusanService pservice;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private HashSet kodDokumen;

    public String getKodDokByCasePBMT(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("sedia_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkKPT")) {
                    kodDok = "RMN";
                } else if (stageId.equals("perakuan_kertas_mmkPTD")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_draf_MMKN_PTG2")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_syor_draf_MMKN_PTG")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_pindaan_MMKN_PTD2")) {
                    kodDok = "RMN";
                } else if (stageId.equals("semak_pindaan_MMKN_PTD_3_1")) {
                    kodDok = "RMN";
                } else if (stageId.equals("rekod_keputusan_MMKN_PTG")) {
                    kodDok = "RMN";
                } else if (stageId.equals("sedia_surat_tolak_MMK")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_surat_tolak_MMK")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_Brg5A")) {
                    kodDok = "N5A";
                } else if (stageId.equals("g_penyediaan_pu_pt")) {
                    kodDok = "OC";
                } else if (stageId.equals("terima_pu_dari_ptd")) {
                    kodDok = "SIPBU";
                } else if (stageId.equals("sdia_drf_pngcualian_ukr")) {
                    kodDok = "SIPU";
                } else if (stageId.equals("terima_pngcualian_ukur")) {
                    kodDok = "8SIJU";
                } else if (stageId.equals("sedia_srt_byrn_tmbhn2")) {
                    kodDok = "STPT";
                } else if (stageId.equals("025semak_Brg5A")) {
                    kodDok = "N5A";
                } else if (stageId.equals("g_penyediaan_pu_pt")) {
                    kodDok = "OC";
                } else if (stageId.equals("semak_precomp")) {
                    kodDok = "PU";
                } else if (stageId.equals("peraku_draf_MMKN_PTG")) {
                    kodDok = "RMN";
                }



//                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
//                    kodDok = "SMT";
//                } else if (stageId.equals("semak_draf_mmkn2")) {
//                    kodDok = "RMN";
//                } else if (stageId.equals("semak_draf_mmkn3_3")) {
//                    kodDok = "RMN";
//                } else if (stageId.equals("rekod_keputusan_mmkn")) {
//                    kodDok = "SKM";
//                } else if (stageId.equals("semak_surat_tolak2")) {
//                    kodDok = "STP";
//                } else if (stageId.equals("semak_lulus_borang_5a")) {
//                    kodDok = "N5A";
//                } else if (stageId.equals("semak_surat_kelulusan")) {
//                    kodDok = "SL";
//                }
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
                } else if (stageId.equalsIgnoreCase("semak_draf_jktdKPT")) {
                    kodDok = "JKTD";
                } else if (stageId.equalsIgnoreCase("cetak_kertas_jktd")) {
                    kodDok = "JKTD";
                } else if (stageId.equalsIgnoreCase("semak_draf_mmkKPT")) {
                    kodDok = "RMN";
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
                } else if (stageId.equalsIgnoreCase("g_penyediaan_pu")) {
                    kodDok = "OC";
                } else if (stageId.equalsIgnoreCase("g_penyediaan_pu_pt")) {
                    kodDok = "OC";
                } else if (stageId.equalsIgnoreCase("semak_draf_ptd1")) {
                    kodDok = "KPTD";
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

    public List<String> getKodDokByCase831A(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("06LaporanTanahMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("22SuratIringanAB")) {
                    kodDokumen.add("SIIA");
                } else if (stageId.equalsIgnoreCase("24ImbasWarta")) {
                    kodDokumen.add("8KUK");
                } else if (stageId.equalsIgnoreCase("30PembetulanWarta")) {
                    kodDokumen.add("8KUP");
                    kodDokumen.add("8KUS");
                } else if (stageId.equalsIgnoreCase("A01RekodSampaiTampal")) {
                    kodDokumen.add("C");
                    kodDokumen.add("D");
                } else if (stageId.equalsIgnoreCase("A06SrtIringBrgD")) {
                    kodDokumen.add("SIID");
                } else if (stageId.equalsIgnoreCase("A08SrtIringWarta")) {
                    kodDokumen.add("8WRTD");
                } else if (stageId.equalsIgnoreCase("A13PembetulanWarta")) {
                    kodDokumen.add("8KUP");
                    kodDokumen.add("8KUS");
                } else if (stageId.equalsIgnoreCase("A17SediaSrtPenandaan")) {
                    kodDokumen.add("8TAN");
                } else if (stageId.equalsIgnoreCase("A21TetapTarikhBicara")) {
                    kodDokumen.add("E");
                    kodDokumen.add("F");
                } else if (stageId.equalsIgnoreCase("A22SediaSrtJPPH")) {
                    kodDokumen.add("JPPH");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("A22SediaSrtJPPH")) {
                    kodDokumen.add("JPPH");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("29SediaBrgI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("30TandatanganI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("31SemakRekod")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("33SemakBrgI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("44CetakNotaSiasatan")) {
                    kodDokumen.add("NSIA");
                } else if (stageId.equalsIgnoreCase("46CetakNotaSiasatan")) {
                    kodDokumen.add("G");
                    kodDokumen.add("H");
                } else if (stageId.equalsIgnoreCase("45_1JanaBorangM")) {
                    kodDokumen.add("M");
                } else if (stageId.equalsIgnoreCase("01KemasukanK3")) {
                    kodDokumen.add("8AMA");
                    kodDokumen.add("8ARB");
                } else if (stageId.equalsIgnoreCase("02MaklumanSemakanK3")) {
                    kodDokumen.add("8AMA");
                    kodDokumen.add("8ARB");
                } else if (stageId.equalsIgnoreCase("07PengesahanLaporanK3")) {
                    kodDokumen.add("8AMA");
                    kodDokumen.add("8ARB");
                } else if (stageId.equalsIgnoreCase("01DepositCekK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("02SediaSuratK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("04TerimaSamanK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("05HadirMahkamahK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("06TerimaDariMahkamahK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
//                if (stageId.equalsIgnoreCase("B03CetakJPPH")) {
//                    kodDok = "JPPHA";
//                } else if (stageId.equalsIgnoreCase("16SmkdanKmkniKrtsJKSMN")) {
//                    kodDok = "JKM";
//                }
                break;
        }

        return kodDokumen;
//        return kodDok;
    }

    public List<String> getKodDokByCase831B(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();
//        kodDokumen = new HashSet();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("B03CetakJPPH")) {
                    kodDokumen.add("JPPHA");
                } else if (stageId.equalsIgnoreCase("B07SemakanJPPH")) {
                    kodDokumen.add("D125");
                } else if (stageId.equalsIgnoreCase("B08BayaranDeposit125")) {
                    kodDokumen.add("D125");
                } else if (stageId.equalsIgnoreCase("06LaporanTanahMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("07SemakanMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("08Semakan")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("B16SediaSrtUPEN")) {
                    kodDokumen.add("UPEN");
                    kodDokumen.add("PTGA");
                } else if (stageId.equalsIgnoreCase("B19SediaKertasRencana")) {
                    kodDokumen.add("RENC");
                } else if (stageId.equalsIgnoreCase("PerakuanMMK")) {
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("22SuratIringanAB")) {
                    kodDokumen.add("SIIA");
                } else if (stageId.equalsIgnoreCase("24ImbasWarta")) {
                    kodDokumen.add("8KUK");
                } else if (stageId.equalsIgnoreCase("A01RekodSampaiTampal")) {
                    kodDokumen.add("C");
                    kodDokumen.add("D");
                } else if (stageId.equalsIgnoreCase("A06SrtIringBrgD")) {
                    kodDokumen.add("SIID");
                } else if (stageId.equalsIgnoreCase("A08SrtIringWarta")) {
                    kodDokumen.add("8WRTD");
                } else if (stageId.equalsIgnoreCase("A09SemakSrtIring")) {
                    kodDokumen.add("8WRTD");
                } else if (stageId.equalsIgnoreCase("A21TetapTarikhBicara")) {
                    kodDokumen.add("E");
                    kodDokumen.add("F");
                } else if (stageId.equalsIgnoreCase("A22SediaSrtJPPH")) {
                    kodDokumen.add("JPPH");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("29SediaBrgI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("30TandatanganI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("43_3Tandatangan")) {
                    kodDokumen.add("E");
                    kodDokumen.add("F");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("46CetakNotaSiasatan")) {
                    kodDokumen.add("NSIA");
                    kodDokumen.add("G");
                    kodDokumen.add("H");
                } else if (stageId.equalsIgnoreCase("45_1JanaBorangM")) {
                    kodDokumen.add("M");
                } else if (stageId.equalsIgnoreCase("01KemasukanK3")) {
                    kodDokumen.add("8AMA");
                    kodDokumen.add("8ARB");
                } else if (stageId.equalsIgnoreCase("01DepositCekK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("08SemakDanTandatanganK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

//        return kodDok;
        return kodDokumen;
    }

    public List<String> getKodDokByCase831C(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("B03CetakJPPH")) {
                    kodDokumen.add("JPPHA");
                } else if (stageId.equalsIgnoreCase("06LaporanTanahMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("07SemakanMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("08Semakan")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("B19SediaKertasRencana")) {
                    kodDokumen.add("RENC");
                } else if (stageId.equalsIgnoreCase("B26SediaKertasMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("PerakuanMMK")) {
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("22SuratIringanAB")) {
                    kodDokumen.add("SIIA");
                } else if (stageId.equalsIgnoreCase("24ImbasWarta")) {
                    kodDokumen.add("8KUK");
                } else if (stageId.equalsIgnoreCase("A01RekodSampaiTampal")) {
                    kodDokumen.add("C");
                    kodDokumen.add("D");
                } else if (stageId.equalsIgnoreCase("A06SrtIringBrgD")) {
                    kodDokumen.add("SIID");
                } else if (stageId.equalsIgnoreCase("A08SrtIringWarta")) {
                    kodDokumen.add("8WRTD");
                } else if (stageId.equalsIgnoreCase("A17SediaSrtPenandaan")) {
                    kodDokumen.add("8TAN");
                } else if (stageId.equalsIgnoreCase("A21TetapTarikhBicara")) {
                    kodDokumen.add("8TAN");
                } else if (stageId.equalsIgnoreCase("A21TetapTarikhBicara")) {
                    kodDokumen.add("E");
                    kodDokumen.add("F");
                } else if (stageId.equalsIgnoreCase("A22SediaSrtJPPH")) {
                    kodDokumen.add("JPPH");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("29SediaBrgI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("30TandatanganI")) {
                    kodDokumen.add("I");
                } else if (stageId.equalsIgnoreCase("43_3Tandatangan")) {
                    kodDokumen.add("E");
                    kodDokumen.add("F");
                    kodDokumen.add("SIIE");
                } else if (stageId.equalsIgnoreCase("44CetakNotaSiasatan")) {
                    kodDokumen.add("NSIA");
                } else if (stageId.equalsIgnoreCase("46CetakNotaSiasatan")) {
                    kodDokumen.add("G");
                    kodDokumen.add("H");
                } else if (stageId.equalsIgnoreCase("45_1JanaBorangM")) {
                    kodDokumen.add("M");
                } else if (stageId.equalsIgnoreCase("g_penyediaan_pu")) {
                    kodDokumen.add("SIPU");
                } else if (stageId.equalsIgnoreCase("01KemasukanK3")) {
                    kodDokumen.add("8AMA");
                    kodDokumen.add("8ARB");
                } else if (stageId.equalsIgnoreCase("01DepositCekK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                } else if (stageId.equalsIgnoreCase("08SemakDanTandatanganK2")) {
                    kodDokumen.add("8SDK");
                    kodDokumen.add("DA");
                    kodDokumen.add("8DRD");
                    kodDokumen.add("SML");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
//                if (stageId.equalsIgnoreCase("B03CetakJPPH")) {
//                    kodDok = "JPPHA";
//                } else if (stageId.equalsIgnoreCase("16SmkdanKmkniKrtsJKSMN")) {
//                    kodDok = "JKM";
//                }
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCaseSEK4(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("01Kemasukan")) {
                    kodDokumen.add("8PAN");
                } else if (stageId.equalsIgnoreCase("06LaporanTanahMMK")) {
                    kodDokumen.add("MMKN");
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                } else if (stageId.equalsIgnoreCase("PerakuanMMK")) {
                    kodDokumen.add("MMKN");
                    kodDokumen.add("A");
                    kodDokumen.add("B");
                } else if (stageId.equalsIgnoreCase("22SuratIringanAB")) {
                    kodDokumen.add("SIIA");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCaseSEK4A(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("09DrafSurat")) {
                    kodDokumen.add("PB");
                    kodDokumen.add("SPAS");
                } else if (stageId.equalsIgnoreCase("12DrafSuratBayar")) {
                    kodDokumen.add("AB");
                } else if (stageId.equalsIgnoreCase("15SediaDrafMMK")) {
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("PerakuanMMK")) {
                    kodDokumen.add("MMKN");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCasePTSP(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
//                if (stageId.equalsIgnoreCase("06SediaLaporan")) {
//                    kodDokumen.add("MPT");
//                } else
                    if (stageId.equalsIgnoreCase("15DrafSuratBorangQ")) {
                    kodDokumen.add("Q");
                } else if (stageId.equalsIgnoreCase("01SediaSuratPR")) {
                    kodDokumen.add("8MAN");
                    kodDokumen.add("SDKM");
                    kodDokumen.add("8MAS");
                    kodDokumen.add("8MAJ");
                    kodDokumen.add("M");
                } else if (stageId.equalsIgnoreCase("18SrtArahByrPC")) {
                    kodDokumen.add("SEAP");
                } else if (stageId.equalsIgnoreCase("20RekodTerimaCekPC")) {
                    kodDokumen.add("SBPC");
                } else if (stageId.equalsIgnoreCase("PerakuanMMK")) {
                    kodDokumen.add("MPT");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCaseBMAHK(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("03JanaSrtTolak")) {
                    kodDokumen.add("STLK");
                } else if (stageId.equalsIgnoreCase("04JanaSrtDeposit")) {
                    kodDokumen.add("8SDI");
                    kodDokumen.add("8N");
                } else if (stageId.equalsIgnoreCase("07CetakBrgO")) {
                    kodDokumen.add("O");
                    kodDokumen.add("8MAN");
                } else if (stageId.equalsIgnoreCase("09SediaSrtTarikBalik")) {
                    kodDokumen.add("SPBTL");
                } else if (stageId.equalsIgnoreCase("10SemakanTandatangan")) {
                    kodDokumen.add("SPBTL");
                } else if (stageId.equalsIgnoreCase("11JanaSrtMaklum")) {
                    kodDokumen.add("SEAP");
                } else if (stageId.equalsIgnoreCase("15JanaSrtPeringatan")) {
                    kodDokumen.add("PER");
                } else if (stageId.equalsIgnoreCase("16SemakanTandatangan")) {
                    kodDokumen.add("PER");
                } else if (stageId.equalsIgnoreCase("18TerimaCek")) {
                    kodDokumen.add("SBPC");
                } else if (stageId.equalsIgnoreCase("19SemakanTandatangan")) {
                    kodDokumen.add("SBPC");
                } else if (stageId.equalsIgnoreCase("18_1TerimaEFT")) {
                    kodDokumen.add("SBPC");
                } else if (stageId.equalsIgnoreCase("19_1SemakanTandatangan")) {
                    kodDokumen.add("SBPC");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCasePTPT(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("10TetapBicara")) {
                    kodDokumen.add("SPBG");
                } else if (stageId.equalsIgnoreCase("14JanaSurat")) {
                    kodDokumen.add("SBPC");
                    kodDokumen.add("SPBG");
                } else if (stageId.equalsIgnoreCase("15SediaAffidavit")) {
                    kodDokumen.add("8MAN");
                    kodDokumen.add("8MAS");
                    kodDokumen.add("SEAP");
                    kodDokumen.add("SBPC");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCasePTNB(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("08KemukaJadual")) {
                    kodDokumen.add("8ELW");
                } else if (stageId.equalsIgnoreCase("09TetapkanTarikh")) {
                    kodDokumen.add("NSST");
                } else if (stageId.equalsIgnoreCase("11TetapkanPampasan")) {
                    kodDokumen.add("NSIA");
                    kodDokumen.add("8CAL");
                    kodDokumen.add("8ELZ");
                } else if (stageId.equalsIgnoreCase("12SediakanMMKN")) {
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("19PerakuanMMKN")) {
                    kodDokumen.add("MMKN");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }

    public List<String> getKodDokByCasePBA(String stageId, int numnegeri, String kodCaw, Permohonan permohonan) {

//        String kodDok = new String();
        List<String> kodDokumen = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("02SediaSrtTolak")) {
                    kodDokumen.add("STLK");
                } else if (stageId.equalsIgnoreCase("19PerakuanMMKN")) {
                    kodDokumen.add("MMKN");
                } else if (stageId.equalsIgnoreCase("07CetakTanganWarta")) {
                    kodDokumen.add("SKAN");
                    kodDokumen.add("DWPB");
                } else if (stageId.equalsIgnoreCase("14TandatgnSrt")) {
                    kodDokumen.add("SPBG");
                } else if (stageId.equalsIgnoreCase("A01MaklumTiada")) {
                    kodDokumen.add("STG");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                //INSERT CODE HERE
                
                break;
        }

        return kodDokumen;
    }



}

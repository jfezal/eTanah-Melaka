/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author NageswaraRao
 */
public class DevTandatanganManual {

    @Inject
    PembangunanService pservice;

    public String getKodDokByCaseTSPSS(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA tested
                if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("cetaksuratimbasprahitung")) {
                    kodDok = "SLPH";
                } else if (stageId.equals("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("semakrencanaringkasptg")) {
                    kodDok = "RPPTG,RRPTG";
                } else if (stageId.equals("cetaknilaianpremiumcetaklulus")) {
                    kodDok = "SJPPH,SLSB";
                } else if (stageId.equals("cetaknotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("rekodtkhperakuannotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("sediahantarborang7c")) {
                    kodDok = "7C";
                } else if (stageId.equals("cetaksrtmintaterimadokumen")) {
                    kodDok = "SMD";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("cetaksuratmintadokumen")) {
                    kodDok = "SMDOK";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseSBMS(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("derafperakuanjkbbptd")) {
                    kodDok = "JKBBD";
                } else if (stageId.equals("ulasanadunteksediajkbb")) {
                    kodDok = "JKBBD";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBD";
                } else if (stageId.equals("cetakrencanajkbb")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("sediarencanajkbb")) {
                    kodDok = "JKBBG";
                } else if (stageId.equals("cetakjkbbrekodkpsn")) {
                    kodDok = "RKSP,JKBBG";
                } else if (stageId.equals("cetaksrtjkbbrekodtkhtt")) {
                    kodDok = "SSBMS";
                } else if (stageId.equals("semakderafperakuanyabptd")) {
                    kodDok = "RYABD,SRYAB";
                } else if (stageId.equals("perakuanyabptd")) {
                    kodDok = "RYABD,SRYAB";
                } else if (stageId.equals("sediaderafctksrtnilaianlampa")) {
                    kodDok = "RYABG,SJPPH,RRPTG";
                } else if (stageId.equals("perakuanyabptg")) {
                    kodDok = "RYABG,SJPPH,RRPTG";
                } else if (stageId.equals("terimahitungtatatur12d")) {
                    kodDok = "SBMSD";
                } else if (stageId.equals("rekodkpsnyabkdrjpphtkhkpsn")) {
                    kodDok = "SLA";
                } else if (stageId.equals("cetaksuratlulusberimilik5a")) {
                    kodDok = "N5A,SKN5A";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("cetaksuratmintadokumen")) {
                    kodDok = "SMDOK";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPT(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA tested
                if (stageId.equalsIgnoreCase("cetakrekodkpsnrayuanjkbb")) {
                    kodDok = "RYN,RYS";
                } else if (stageId.equals("cetaksuratkpsnrayuan")) {
                    kodDok = "SRYN";
                } else if (stageId.equals("sediasuratrencanayab")) {
                    kodDok = "SSBMS,SRYAB,RYABD";
                } else if (stageId.equals("sediaderafcetaksuratnilaian")) {
                    kodDok = "RYABG,SJPPH";
                } else if (stageId.equals("perakuanptgyab")) {
                    kodDok = "RYABG,SJPPH";
                } else if (stageId.equals("sediasuratkelulusan5a")) {
                    kodDok = "N5A,SKN5A";
                } else if (stageId.equals("cetaksuratkelulusan5a")) {
                    kodDok = "SKN5A,N5A";
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
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPCS(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("cetaksrtdokimbaspelansediayab")) {
                    kodDok = "SLPH";
                } else if (stageId.equals("cetaksuratmintadokumen")) {
                    kodDok = "SMD";
                } else if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("sediarencanajkbb")) {
                    kodDok = "JKBBG";
                }
                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "SUT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPCB(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("cetaksuratimbasprahitung")) {
                    kodDok = "SLPH";
                } else if (stageId.equals("cetaksuratmintadokumen")) {
                    kodDok = "SMD";
                } else if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("sediarencanajkbb")) {
                    kodDok = "JKBBG";
                }
                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPCBA(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePYTN(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("cetaksuratimbasprahitung")) {
                    kodDok = "SLPH";
                } else if (stageId.equals("cetaksuratmintadokumen")) {
                    kodDok = "SMD";
                } else if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBS,JKBBD";
                } else if (stageId.equals("sediarencanajkbb")) {
                    kodDok = "JKBBG";
                }
                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSKKT(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("cetaknotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("rekodtkhperakuannotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "JKBBD,JKBBS";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "JKBBD,JKBBS";
                } else if (stageId.equals("cetaknilaianpremiumcetaklulus")) {
                    kodDok = "SLSB,SJPPH";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSPSN(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("cetaksuratkpsnjkbbrekodtkhtt")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("sedianotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("cetaknotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("perakuannotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("rekodtkhperakuannotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("sediasrtkpsntolak") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    kodDok = "STT";
                } else if (stageId.equals("derafperakuanjkbbptd")) {
                    kodDok = "DPPTG";
                } else if (stageId.equals("perakuanjkbbptd")) {
                    kodDok = "DPPTG";
                } else if (stageId.equals("cetakrencanajkbb")) {
                    kodDok = "DPPTG";
                } else if (stageId.equals("cetaknilaianpremiumcetaklulus") && permohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    kodDok = "SLSB,SJPPH";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSBSN(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("rekodtkhperakuannotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("cetaknotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("sediasrtkpsn") && permohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    kodDok = "SLSB";
                } else if (stageId.equals("sediasrtkpsn") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    kodDok = "STT";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSKSN(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("rekodtkhtt")) {
                    kodDok = "SLSB";
                } else if (stageId.equals("cetaknotis7g")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("rekodtkhperakuan")) {
                    kodDok = "NTS7G,SRT7G";
                } else if (stageId.equals("sediacetaksrtkpsn") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    kodDok = "STT";
                } else if (stageId.equals("sediacetaksrtkpsn") && permohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    kodDok = "SLSB,SJPPH";
                } else if (stageId.equals("semakderafperakuanpertimb")) {
                    kodDok = "DPPTG";
                } else if (stageId.equals("perakuanptdpertimb")) {
                    kodDok = "DPPTG";
                } else if (stageId.equals("cetakrencanapertimbptg")) {
                    kodDok = "DPPTG";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePPK(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equals("sediasuratkelulusan")) {
                    kodDok = "SLPK";
                } else if (stageId.equals("sediajkbbrekodkpsn")) {
                    kodDok = "KRP";
                } else if (stageId.equals("sediasuratiringanendos")) {
                    kodDok = "SIPK";
                }

                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBTL(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetakrekodtrkhsuratmakluman")) {
                    kodDok = "SPBTL";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePBSK(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetakrisalatmmkn")) {
                    kodDok = "MMKND";
                } else if (stageId.equals("rekodkpsnmmkncetaksurat")) {
                    kodDok = "RMN,MMKNG,SKM";
                } else if (stageId.equals("semaksuratkelulusanb5a")) {
                    kodDok = "SLPBS";
                } else if (stageId.equals("cetaksurattolak")) {
                    kodDok = "STPBS";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRBPA(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetaksuratkelulusan")) {
                    kodDok = "SRBPA";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRLTB(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetakrisalatmmkn")) {
                    kodDok = "MMKND";
                } else if (stageId.equals("rekodkpsnmmkncetaksurat")) {
                    kodDok = "MMKNG,RMN,SKM";
                } else if (stageId.equals("semakhantarsuratkelulusan")) {
                    kodDok = "SLRY";
                } else if (stageId.equals("cetaksurattolak")) {
                    kodDok = "STLK";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRNS(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetaksuratmohonrayuan")) {
                    kodDok = "SPRNS";
                } else if (stageId.equalsIgnoreCase("cetaksuratjpphpremium")) {
                    kodDok = "JPPHN";
                } else if (stageId.equalsIgnoreCase("rekodkpsncetaksurat")) {
                    kodDok = "SRNSG,STRNG";
                } else if (stageId.equalsIgnoreCase("sediasuratkelulusanb5a")) {
                    if (permohonan.getKeputusan().getKod().equals("NB")) {
                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                            kodDok = "SRNSD,N5A";
                        } else {
                            kodDok = "SRNSD,NTS7G";
                        }
                    } else if (permohonan.getKeputusan().getKod().equals("NK")) {
                        kodDok = "STRNS";
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRPP(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("rekodkpsnmmkncetaksurat")) {
                    kodDok = "RMN,MMKNG,SKM";
                } else if (stageId.equalsIgnoreCase("semaksuratkelulusanb5a")) {
                    if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
                        kodDok = "SLRY,N5A";
                    } else {
                        kodDok = "SLRY,NTS7G";
                    }
                } else if (stageId.equalsIgnoreCase("cetakrisalatmmkn")) {
                    kodDok = "MMKND";
                } else if (stageId.equalsIgnoreCase("cetaksurattolak")) {
                    kodDok = "STLK";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRPMMK(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("rekodkpsnmmkncetaksurat")) {
                    kodDok = "RMN,MMKNG,SKM";
                } else if (stageId.equalsIgnoreCase("semakhantarsuratkelulusan")) {
                    kodDok = "SLRY";
                } else if (stageId.equalsIgnoreCase("cetaksurattolak")) {
                    kodDok = "STLK";
                } else if (stageId.equalsIgnoreCase("cetakrisalatmmkn")) {
                    kodDok = "MMKND";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("semak_surat_tolak")) {
                    kodDok = "SMT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePSBT(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("rekodtkhtt")) {
                    kodDok = "SPSBT";
                } else if (stageId.equalsIgnoreCase("cetaksrtkpsn")) {
                    kodDok = "SPSBT";
                } else if (stageId.equalsIgnoreCase("cetaksrttolak")) {
                    kodDok = "SPSBT";
                } else if (stageId.equalsIgnoreCase("ctksrtkecualiupahukur")) {
                    kodDok = "SIPBU";
                } else if (stageId.equalsIgnoreCase("rekodtkhtthantarptd")) {
                    kodDok = "SIPBU";
                } else if (stageId.equalsIgnoreCase("rekodtkhtthantarptg")) {
                    kodDok = "SBUUD";
                } else if (stageId.equalsIgnoreCase("perakuansijilkecualiupahukur")) {
                    kodDok = "SBUUD";
                } else if (stageId.equalsIgnoreCase("terimaderafkecualiupahukur")) {
                    kodDok = "SBUUD";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCasePSMT(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageId.equalsIgnoreCase("cetaksrttolak")) {
                    kodDok = "SPSMT";
                } else if (stageId.equalsIgnoreCase("cetaksrtkpsn")) {
                    kodDok = "SPSMT";
                } else if (stageId.equalsIgnoreCase("rekodtkhtt")) {
                    kodDok = "SPSMT";
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "SUT";
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    kodDok = "RMN";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseRTLK(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("semaksuratkpsnptg")) {
                    kodDok = "SJKBB";
                } else if (stageId.equals("rekodtkhtt")) {
                    kodDok = "SJKBB";
                }

                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("")) {
                    kodDok = "";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSPTD(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                if (stageId.equalsIgnoreCase("")) {
                    kodDok = "";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }

                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSPTG(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA 
                //MELAKA 
                if (stageId.equalsIgnoreCase("")) {
                    kodDok = "";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }


            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseTSMMK(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                ///MELAKA 
                if (stageId.equalsIgnoreCase("")) {
                    kodDok = "";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }

                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }
                break;
        }

        return kodDok;
    }

    public String getKodDokByCaseSBPS(String stageId, int numnegeri, Permohonan permohonan) {

        String kodDok = new String();

        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                ///MELAKA 
                if (stageId.equalsIgnoreCase("")) {
                    kodDok = "";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }

                break;

            case 2:
                //NEGERI SEMBILAN
                if (stageId.equalsIgnoreCase("kemasukan")) {
                    kodDok = "JPPH,SUT";
                } else if (stageId.equals("")) {
                    kodDok = "";
                }
                break;
        }

        return kodDok;
    }
//    public String checkJKTL(String idMohon) {
//
//        String value = "";
//        BigDecimal luas = BigDecimal.ZERO;
//        BigDecimal luasMax = BigDecimal.ZERO;
//        BigDecimal limit = new BigDecimal(40);
//        //Permohonan permohonan = p.getIdPermohonan();
//        HakmilikPermohonan hakmilik;
//
//        List<HakmilikPermohonan> hm = pservice.findHakmilikPermohonanByIdPermohonan(idMohon);
//        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
//
//            hakmilik = hm.get(ad1);
//
//            if (ad1 == 0) {
//                luasMax = hakmilik.getHakmilik().getLuas();
//            } else if (luasMax.compareTo(hakmilik.getHakmilik().getLuas()) < 0) {
//                luasMax = hakmilik.getHakmilik().getLuas();
//            }
//        }
//
//        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
//            hakmilik = hm.get(ad1);
//            if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
//
//                luas = luasMax.divide(new BigDecimal(10000));
//
//                if (luas.compareTo(limit) >= 0) {
//                    value = "LD";
//                } else {
//                    value = "NM";
//                }
//            } else if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
//
//                luas = luasMax;
//
//                if (luas.compareTo(limit) >= 0) {
//                    value = "LD";
//                } else {
//                    value = "NM";
//                }
//            }
//        }
//        return value;
//    }
}

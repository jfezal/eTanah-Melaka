package etanah.view.penguatkuasaan;

import etanah.view.stripes.pelupusan.disClass.*;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shazwan 31102011 1404 PM
 */
public class EnfClassAgihanTugas {

    @Inject
    EnforcementService enforcementService;

    public List<Pengguna> getPengguna(int numUrusan, String stageID, String negeri, String kodCaw) {
        List listPp = new ArrayList<Pengguna>();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //422
                listPp = getPenggunaByBPELCase422(stageID, numnegeri, kodCaw);
                break;
            case 2:
                //423
                listPp = getPenggunaByBPELCase422(stageID, numnegeri, kodCaw);
                break;
            case 3:
                //424
                listPp = getPenggunaByBPELCase422(stageID, numnegeri, kodCaw);
                break;
            case 4:
                //425
                listPp = getPenggunaByBPELCase425(stageID, numnegeri, kodCaw);
                break;
            case 5:
                //425A
                listPp = getPenggunaByBPELCase425(stageID, numnegeri, kodCaw);
                break;
            case 6:
                //426
                listPp = getPenggunaByBPELCase426(stageID, numnegeri, kodCaw);
                break;
            case 7:
                //427
                listPp = getPenggunaByBPELCase427(stageID, numnegeri, kodCaw);
                break;
            case 8:
                //428
                listPp = getPenggunaByBPELCase428(stageID, numnegeri, kodCaw);
                break;
            case 9:
                //429
                listPp = getPenggunaByBPELCase422(stageID, numnegeri, kodCaw);
                break;
            case 10:
                //351
                listPp = getPenggunaByBPELCase351(stageID, numnegeri, kodCaw);
                break;
            case 11:
                //352
                listPp = getPenggunaByBPELCase351(stageID, numnegeri, kodCaw);
                break;
            case 12:
                //49
                listPp = getPenggunaByBPELCase49(stageID, numnegeri, kodCaw);
                break;
            case 13:
                //127
                listPp = getPenggunaByBPELCase127(stageID, numnegeri, kodCaw);
                break;
            case 14:
                //128
                listPp = getPenggunaByBPELCase127(stageID, numnegeri, kodCaw);
                break;
            case 15:
                //129
                listPp = getPenggunaByBPELCase127(stageID, numnegeri, kodCaw);
                break;
            case 16:
                //130
                listPp = getPenggunaByBPELCase127(stageID, numnegeri, kodCaw);
                break;
            case 17:
                //130
                listPp = getPenggunaByBPELCase127(stageID, numnegeri, kodCaw);
                break;

        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase422(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan")) {
                    bpelName.add("pptd");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase425(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan1") || stageID.equalsIgnoreCase("agih_tugasan2")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("pptptgkuasa"); // PPT
                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        bpelName.add("PPTanah"); //PPT
                    }
                }
                if (stageID.equalsIgnoreCase("agih_tugasan3")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("ptptgkuasa"); // PT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        bpelName.add("ptptdkuasa"); //PT
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase426(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan_jualan")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("ptptgkuasa"); // PT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        bpelName.add("ptptdkuasa"); //PT
                    }
                }

                if (stageID.equalsIgnoreCase("agih_tugasan")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("pptptgkuasa"); // PPT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        bpelName.add("PPTanah"); //PPT
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase427(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("maklum_laporan_awal")) {
//                    bpelName.add("pptptdkuasa");
                    bpelName.add("PPTanah");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase428(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("maklum_laporan_tnh") || stageID.equalsIgnoreCase("arah_pemantauan")) {
//                    bpelName.add("pptptdkuasa");
                    bpelName.add("PPTanah");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase49(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan1") || stageID.equalsIgnoreCase("agih_tugasan2")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("ppptgkuasa"); // PP

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                        bpelName.add("ppptdkuasa1"); //PP
                        bpelName.add("PPelan"); //PP
                    }
                }
                if (stageID.equalsIgnoreCase("g_charting_semak") || stageID.equalsIgnoreCase("g_semak_permohonan")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("pptptgkuasa"); // PPT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                        bpelName.add("pptptdkuasa"); //PPT
                        bpelName.add("PPTanah"); //PPT
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase351(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan1") || stageID.equalsIgnoreCase("agih_tugasan2")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("pptptgkuasa"); // PPT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        bpelName.add("PPTanah"); //PPT  pptptdkuasa
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase127(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("agih_tugasan")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("pptptgkuasa"); // PPT

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                        bpelName.add("pptptdkuasa"); //PPT
                        bpelName.add("PPTanah"); //PPT -training 22/5/2014
                    }
                }

                if (stageID.equalsIgnoreCase("agih_charting")) {
                    if (kodCaw.equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        bpelName.add("ppptgkuasa"); // PP

                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                        bpelName.add("ppptdkuasa1"); //PP
                        bpelName.add("PPelan");//Pelukis Pelan 12/5/2015
                    }
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
                    bpelName.add("PPelan");
                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = enforcementService.findPenggunaAgihTugasanByBPEL(bpelName, kodCaw);
        }
        return listPp;
    }
}

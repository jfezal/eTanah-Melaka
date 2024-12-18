/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.service.common.LelongService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazurahayati.yusop
 */
public class AgihanTugasPerananActionBean {

    @Inject
    PelupusanService pservice;
    @Inject
    LelongService lelongservice;

    public List<Pengguna> getPenggunaL(int numUrusan, String stageID, String negeri) {
        List listPp = new ArrayList<Pengguna>();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PPJP
                listPp = getPenggunaByBPELCasePPJP(stageID, numnegeri);
                break;
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCasePPJP(String stageID, int numnegeri) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("kmskJurulelong")) {
                    bpelName.add("JLB");
                } else if (stageID.equalsIgnoreCase("semakPembida")) {
                    bpelName.add("JLB");
                }
//                } else if (stageID.equalsIgnoreCase("agihan_tugas3")) {
//                    bpelName.add("PPelan");
//                } else if (stageID.equalsIgnoreCase("agihan_tugas4")) {
//                    bpelName.add("PPelan");
//                }
//                break;
//            case 2:
//                //NEGERI SEMBILAN
//                if (stageID.equalsIgnoreCase("02PerakuAgihanCharting")) {
//                    bpelName.add("PPelan");
//                } else if (stageID.equalsIgnoreCase("03AgihanTugas")) {
//                    bpelName.add("PPTanah");
//                } else if (stageID.equalsIgnoreCase("22TerimaKeputusan")) {
//                    bpelName.add("PPelan");
//                }
                break;
        }

        if (bpelName.size() > 0) {
            listPp = lelongservice.findPenggunaByBPEL(bpelName);
//            java.util.Collections.sort(listPp);
        }
        return listPp;
    }
}

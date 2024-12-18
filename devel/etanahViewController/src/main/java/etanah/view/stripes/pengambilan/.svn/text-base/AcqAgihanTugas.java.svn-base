/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodUrusan;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nurul.hazirah
 */
public class AcqAgihanTugas {

    @Inject
    PelupusanService pservice;
    Logger logger = Logger.getLogger(AcqAgihanTugas.class);

    public List<Pengguna> getPengguna(int numUrusan, String stageID, String negeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PPRU
                listPp = getPenggunaByBPELCase831B(stageID, numnegeri, kodCaw);
                break;
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase831B(String stageID, int numnegeri, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("29_3Agihan")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("58AgihanTugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("59AgihanTugas")) {
                    bpelName.add("PPTanah");
                } else if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
                    bpelName.add("PPTanah");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                break;
        }

        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL831A(bpelName, kodCaw);
        }
        return listPp;
    }

     public List<Pengguna> getPenggunaByBPELCaseSEK4(String stageID, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("29_3Agihan")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("58AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("59AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("semak_charting")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("agih_so")) {
            bpelName.add("PPTanahPTG");
            //bpelName.add("pptkanan_ptg");
        }
        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL831A(bpelName, kodCaw);
        }
         return listPp;
    }
    
    public List<Pengguna> getPenggunaByBPELCase831A(String stageID, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("29_3Agihan")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("58AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("59AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("semak_charting")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("agihan_tugas_ptg")) {
            bpelName.add("PPTanahPTG");
//            bpelName.add("pptkanan_ptg");
        }else if (stageID.equalsIgnoreCase("MaklumSediaSrt")) {
            bpelName.add("PTPengambilan");
            //bpelName.add("pptkanan_ptg");
        }
        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL831A(bpelName, kodCaw);
        }
        return listPp;
    }

    public List<Pengguna> getPenggunaByBPELCase831C(String stageID, String kodCaw) {

        List listPp = new ArrayList<Pengguna>();
        /*
         * NOTE : bpelName refer to BPELGroup of the appointed user..not the
         * current user..
         */
        List<String> bpelName = new ArrayList<String>();

        if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("29_3Agihan")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("58AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("59AgihanTugas")) {
            bpelName.add("PPTanah");
        } else if (stageID.equalsIgnoreCase("05SemakanAgihTugas")) {
            bpelName.add("PPTanah");
        }
        if (bpelName.size() > 0) {
            listPp = pservice.findPenggunaByBPEL831A(bpelName, kodCaw);
        }
        return listPp;
    }
}

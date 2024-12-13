package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.KodDokumen;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodUrusanDAO;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PihakPengarah;
import java.text.SimpleDateFormat;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pembangunan/melaka/rencanaPertimbanganMenteri")
public class RencanaPertimbanganMenteriActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RencanaPertimbanganMenteriActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    BPelService service;
    @Inject
    PembangunanUtility pu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private LaporanTanah laporanTanah;
    private String stageId;
    private String tujuan;
    private String perihalPemohon;
    private String perihalTanah;
    private String harga;
    private String perakuan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn = true;
    private PermohonanKertasKandungan kandunganK;
    private String tajuk;
    private String lokasi;
    private String nama;
    private String pejTanah;
    private PermohonanRujukanLuar ulasanAdun;
    private KodDokumen kd;
    private int saizList;
    private Hakmilik hakmilikSingle;
    private String nolot;
    private String nohakmilik;
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan4;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private int rowCount4;
    private String perakuanptg;
//    private boolean ptg = true;
    private Pengguna pengguna;
    private PermohonanAsal permohonanAsal;
    private List<PermohonanPlotPelan> senaraiPermohonanPlotPelan;
    private List<PermohonanPlotPelan> senaraiPermohonanPlotPelanForK;
    private String perihalPermohonan;
    private List<Pemohon> pemohonList;
    private List<PihakPengarah> pihakPengarahList;
    private String sementara;
    private String keputusanDipohon;
    private String ulasan;
    private String taskId;
    private Task task = null;
    private List<HakmilikPermohonan> hakmilikPermohonanListHM;
    private List<String> noLotList = new ArrayList<String>();
    String idHakmilik;
    private String noLot;
    private String perakuanptgmelaka;
    private String tarikhMeeting;
    private Permohonan havePPT;
    private String lokasi1;
    private List<PermohonanKertas> tarikhPersidangan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_Menteri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        ptg = false;
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_Menteri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_Menteri.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = "";
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan permohonan1 = permohonanDAO.findById(idMohon);

        if (permohonan1.getKodUrusan().getKod().equals("PPT") && permohonan1.getPermohonanSebelum() != null) {
            idPermohonan = permohonan1.getPermohonanSebelum().getIdPermohonan();
            permohonan = permohonanDAO.findById(idPermohonan);
        } else {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanListHM = p.getSenaraiHakmilik();
        }

        noLotList = new ArrayList<String>();
        for (HakmilikPermohonan hp : hakmilikPermohonanListHM) {
            noLotList.add(hp.getHakmilik().getNoLot().replace("0", ""));
        }
        //System.out.println("--noLotList--:" + noLotList);

        /* To display PermohonanPlotPelan List */
//        senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
//        senaraiPermohonanPlotPelan = devService.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);

        senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
        senaraiPermohonanPlotPelan = devService.senaraiPermohonanPlotPelanByIdPermohonanForH(idPermohonan);

        senaraiPermohonanPlotPelanForK = new ArrayList<PermohonanPlotPelan>();
        senaraiPermohonanPlotPelanForK = devService.senaraiPermohonanPlotPelanByIdPermohonanForK(idPermohonan);

        /* Extract Ulasan Adun details */
        ulasanAdun = devService.findUlasanAdun(idPermohonan);
        if (ulasanAdun != null) {
            ulasan = ulasanAdun.getUlasan();
        }

        /* Get HakmilikPermohonanList based on IdPermohonan  */
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        saizList = hakmilikPermohonanList.size();

        if (!(hakmilikPermohonanList.isEmpty())) {
            hp = hakmilikPermohonanList.get(0);
            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            nolot = hakmilikSingle.getLot().getNama() + " " + hakmilikSingle.getNoLot();
            nohakmilik = hakmilikSingle.getKodHakmilik().getKod() + " " + hakmilikSingle.getNoHakmilik();
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
            String noLot = hakmilik.getNoLot().replaceAll("^0*", "");

            if (w == 0) {
                lokasi = "Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", di " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka ";
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + "Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", di " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka , ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + "Hakmilik " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", di " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Melaka ";
                }
            }
        }

        /* Get pemohonList based on IdPermohonan  */
        List<Pemohon> listPemohon = new ArrayList<Pemohon>();
        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);
        String suratPemohon = "";
        //LOG.info(" ***** ListPemohon Size *****:" + listPemohon.size());
        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            if (j == 0) {
                nama = pm.getPihak().getNama();
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
            if (pm != null && pm.getSurat() != null && pm.getSurat() == 'Y') {
                suratPemohon = suratPemohon + " " + pm.getPihak().getNama() + ",";
            }

            //LOG.info("***** Pemohon Name *******:" + nama);
        }
        nama = pu.convertStringtoInitCap(nama);
        //LOG.info("***** Pemohon Name Converter *******:" + nama);

        kertasK = null;
        if (!(permohonan1.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan1.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan1.getSenaraiKertas().get(i);

                if (kertasP.getKodDokumen().getKod().equals("RPYAB")) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);

                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.1")) {
                            perihalPermohonan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.2")) {
                            perihalPemohon = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.4")) {
                            perihalTanah = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.8")) {
                            harga = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4) {
                            perakuan = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 5) {
//                            perakuanptgmelaka = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6) {
                            tajuk = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8) {
                            sementara = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 10) {
                            keputusanDipohon = kertasK.getKandungan();
                        }
                    }
                    senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan1 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.3");
                    senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                    senaraiKandungan3 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                    senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 9);
                    rowCount1 = senaraiKandungan1.size();
                    rowCount2 = senaraiKandungan2.size();
                    rowCount3 = senaraiKandungan3.size();
                    rowCount4 = senaraiKandungan4.size();
                }
            }

        }

        /* Get Daerah Names based on kod   */
        if (pengguna.getKodCawangan().getDaerah() != null) {
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("01")) {
                pejTanah = "Melaka Tengah";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("02")) {
                pejTanah = "Jasin";
            }
            if (pengguna.getKodCawangan().getDaerah().getKod().equals("03")) {
                pejTanah = "Alor Gajah";
            }
        } else {
            pejTanah = "Melaka Tengah";
        }

        /* first time login to display default data */
        if (kertasK == null) {
            /* To Get some data from JKBB  */
            PermohonanKertas kertasP1 = new PermohonanKertas();
            List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
//            if(permohonan.getKodUrusan().getKod().equals("PPT")){
//                if(permohonan.getPermohonanSebelum()!=null){
//                    senaraiKertas = devService.findSenaraiKertasByKod(permohonan.getPermohonanSebelum().getIdPermohonan(), "JKBB");
//                }
//            }else{
//
//            }

            senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "JKBB");

            if (senaraiKertas.size() > 0) {
                kertasP1 = senaraiKertas.get(0);
            } else {
                kertasP1 = null;
            }

            String tarikh = sdf.format(permohonan1.getInfoAudit().getTarikhMasuk());
            String bil = "";
            if (kertasP1 != null && kertasP1.getTajuk() != null) {
                bil = kertasP1.getTajuk();
            }
            //LOG.info(" ******* Pemohon Name ******:" + nama);

            /* get rujuan Kanun based on KodUrusan  */
            String rujukanKanun = "";
            if (permohonan1.getKodUrusan().getKod().equals("TSPSS")) {
                rujukanKanun = kodUrusanDAO.findById("TSPSS").getRujukanKanun();
            } else if (permohonan1.getKodUrusan().getKod().equals("SBMS") || permohonan1.getKodUrusan().getKod().equals("PPT")) {
                rujukanKanun = kodUrusanDAO.findById("SBMS").getRujukanKanun();
            }
//            if(permohonan1.getKodUrusan().getKod().equals("PPT")){
//                if(permohonan.getPermohonanSebelum()!=null){
//                    LOG.info(" ******* Kod Urusan PPT ******:");
//                    tajuk = "Permohonan Untuk "+ permohonan.getPermohonanSebelum().getKodUrusan().getNama() +" Di Bawah " +rujukanKanun+" Kanun Tanah Negeri bagi "+  lokasi +" kepada "+ nama ;
//                    tujuan = "Rencana ini ialah untuk mendapatkan pertimbangan YAB Ketua Menteri Melaka untuk " + permohonan.getPermohonanSebelum().getKodUrusan().getNama() + " bagi "
//                            + lokasi + " kepada " + nama + ".";
//                }
//             }else{
            //LOG.info(" ******* Kod Urusan other than PPT ******:");
            tajuk = "Permohonan Untuk " + permohonan.getKodUrusan().getNama() + " Di Bawah " + rujukanKanun + " Kanun Tanah Negeri bagi " + lokasi + " kepada " + nama;
            tujuan = "Rencana ini ialah untuk mendapatkan pertimbangan YAB Ketua Menteri Melaka untuk " + permohonan.getKodUrusan().getNama() + " bagi "
                    + lokasi + " kepada " + nama + ".";

            //perakuanPTG
            //Tarikh Persidangan      
            PermohonanKertas kertas = new PermohonanKertas();
            kertas = devService.findPermohonanKertasByKod(idPermohonan, "JKBB");
            if (kertas!=null && kertas.getTarikhSidang() != null) {
                tarikhMeeting = sdf.format(kertas.getTarikhSidang());
            } else {
                tarikhMeeting = "-";
            }

            //LOG.info("TaRIKH MEETiNG " + tarikhMeeting);

            //mohonSebelum
            havePPT = devService.findPermohonanMHByIdSebelumAndKodUrusan(idPermohonan, "PPT");
            if (havePPT != null) {
                if (havePPT.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    List<HakmilikPermohonan> list1 = havePPT.getSenaraiHakmilik();
                    HakmilikPermohonan mh = list1.get(0);
                    String x = "mengikut pindaan pelan tatatur bil. " + mh.getCatatan() + " rujukan " + mh.getNomborRujukan() + ".";//" bertarikh " + d + ".";

                    perakuanptgmelaka = "Pengarah Tanah dan Galian, Melaka dengan ini memperakukan supaya tanah " + lokasi + " disempurnakan proses serahan"
                            + " dan berimilik semula " + x + "Tarikh kelulusan asal pada " + tarikhMeeting + ".Kelulusan Pemberimilikan semula ini adalah seperti dalam pelan"
                            + " pra - hitung dan ringkasan yang mengandungi butir-butir sseperti berikut:- ";
                } else {
                    perakuanptgmelaka = "Pengarah Tanah dan Galian, Melaka dengan ini memperakukan supaya tanah " + lokasi + " disempurnakan proses serahan"
                            + " dan berimilik semula.Tarikh kelulusan asal pada " + tarikhMeeting + ".Kelulusan Pemberimilikan semula ini adalah seperti dalam pelan"
                            + " pra - hitung dan ringkasan yang mengandungi butir-butir sseperti berikut:- ";
                }
            } else {
                perakuanptgmelaka = "Pengarah Tanah dan Galian, Melaka dengan ini memperakukan supaya tanah " + lokasi + " disempurnakan proses serahan"
                        + " dan berimilik semula.Tarikh kelulusan asal pada " + tarikhMeeting + ".Kelulusan Pemberimilikan semula ini adalah seperti dalam pelan"
                        + " pra - hitung dan ringkasan yang mengandungi butir-butir sseperti berikut:- ";
            }


            perihalPermohonan = "Pentadbir Tanah " + pejTanah + " telah menerima Permohonan ini pada " + tarikh + " lanya telah diluluskan di dalam "
                    + "Mesyuarat Jawatankuasa Belah Bahagi Tanah Negeri Melaka bil " + bil + " pada";
            perihalPemohon = " Pemohon ialah " + nama;

            if (kertasP1 != null) {
                for (int j = 0; j < kertasP1.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                    kertasK1 = kertasP1.getSenaraiKandungan().get(j);
                    if (kertasK1.getBil() == 3 && !kertasK1.getKandungan().equals("TIADA DATA.")) {
                        perihalTanah = kertasK1.getKandungan();
                    }
//                        else if (kertasK1.getBil() == 5 && !kertasK1.getKandungan().equals("TIADA DATA.")){
//                            perakuan = kertasK1.getKandungan();
//                        } else if(kertasK1.getBil() == 6 && !kertasK1.getKandungan().equals("TIADA DATA.")){
//                            perakuan = perakuan + " " +kertasK1.getKandungan();
//                        }
                }
            }
            senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();

            /*Get data from PihakPengarah table based on Pemohon's pihak */
            pihakPengarahList = new ArrayList<PihakPengarah>();
            pihakPengarahList = devService.findPihakPengarahByIdPermohonan(idPermohonan);
            if (!pihakPengarahList.isEmpty()) {
                for (int i = 0; i < pihakPengarahList.size(); i++) {
                    PihakPengarah pp = new PihakPengarah();
                    pp = pihakPengarahList.get(i);
                    StringBuffer address = new StringBuffer();
                    if (pp.getNama() != null) {
                        address = address.append(pp.getNama() + ". ");
                    }
                    if (pp.getNoPengenalan() != null) {
                        address = address.append("No K/P: " + pp.getNoPengenalan() + ". \n");
                    }
                    if (pp.getAlamat1() != null) {
                        address = address.append(pp.getAlamat1() + ", ");
                    }
                    if (pp.getAlamat2() != null) {
                        address = address.append(pp.getAlamat2() + ", \n");
                    }
                    if (pp.getAlamat3() != null) {
                        address = address.append(pp.getAlamat3() + ", \n");
                    }
                    if (pp.getPoskod() != null) {
                        address = address.append(pp.getPoskod() + " ");
                    }
                    if (pp.getAlamat4() != null) {
                        address = address.append(pp.getAlamat4() + ", ");
                    }
                    if (pp.getKodNegeri() != null) {
                        address = address.append(pp.getKodNegeri().getNama() + " ");
                    }
                    PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
                    kertasK2.setKandungan(address.toString());
                    senaraiKandungan1.add(kertasK2);
                }
            } else {
                PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
                kertasK2.setKandungan("");
                senaraiKandungan1.add(kertasK2);
            }



            /* Get data from Mesyuarat JKBB */
            PermohonanKertas kertasP2 = new PermohonanKertas();
//            if(permohonan.getKodUrusan().getKod().equals("PPT")){
//                if(permohonan.getPermohonanSebelum()!=null){
//                    kertasP2 = devService.findKertasByKod(permohonan.getPermohonanSebelum().getIdPermohonan(), "KMJBB");
//                }
//            }else{
//
//            }

            kertasP2 = devService.findKertasByKod(idPermohonan, "KMJBB");
            if (kertasP2 != null) {
                for (int j = 0; j < kertasP2.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                    kertasK1 = kertasP2.getSenaraiKandungan().get(j);
                    senaraiKandungan2.add(kertasK1);
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("PPT")) {
                PermohonanKertas kertasP3 = new PermohonanKertas();
                kertasP3 = devService.findKertasByKod(permohonan1.getIdPermohonan(), "KMJBB");
                if (kertasP3 != null) {
                    for (int j = 0; j < kertasP3.getSenaraiKandungan().size(); j++) {
                        PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                        kertasK1 = kertasP3.getSenaraiKandungan().get(j);
                        senaraiKandungan2.add(kertasK1);
                    }
                }
            } else {
                PermohonanKertasKandungan kertasK3 = new PermohonanKertasKandungan();
                kertasK3.setKandungan(" ");
                senaraiKandungan2.add(kertasK3);
            }

            PermohonanKertasKandungan kertasK4 = new PermohonanKertasKandungan();
            kertasK4.setKandungan(" ");
            senaraiKandungan3.add(kertasK4);

            PermohonanKertasKandungan kertasK5 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertasK6 = new PermohonanKertasKandungan();
            kertasK5.setKandungan("Pemohon tidak dibenarkan menjual lot-lot kosong (dengan tiada bangunan)");
            kertasK6.setKandungan("Pemohon dikehendaki mendapatkan lesen Pemaju dari Kementerian Perumahan");
            senaraiKandungan4.add(kertasK5);
            senaraiKandungan4.add(kertasK6);
            sementara = "Pemohon hendaklah mengambil tindakan untuk memperenggankan tanah-tanah ini Jurukur Berlesen dan keluasan dan tiap-tiap "
                    + "plot untuk hakmilik sementara yang dikeluarkan adalah sama dengan keluasan dalam pelan pra-hitung.";
            keputusanDipohon = "Yang Amat Berhormat ketua Menteri Melaka adalah dipohon untuk menimbang dan seterusnya membuat keputusan samada bersetuju "
                    + "atau sebaliknya dengan perakuan Pengarah Tanah dan Galian Melaka seperti diperenggan 5 dan 7 di atas";
            harga = "Senarai harga tidak disertakan kerana tujuan adalah untuk pelancongan dan tiada bangunan akan dijual.";
            perakuan = "Pentadbir Tanah " + pejTanah + " dengan ini memperakukan supaya permohonan daripada "
                    + suratPemohon + " untuk memajukan tanah " + lokasi + " melalui proses penyerahan balik dan pemberimilikan semula diluluskan.";
            rowCount1 = senaraiKandungan1.size();
            rowCount2 = senaraiKandungan2.size();
            rowCount3 = senaraiKandungan3.size();
            rowCount4 = senaraiKandungan4.size();
        }

    }

    public Resolution hakmilikDetail() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        noLot = hakmilik.getNoLot().replace("0", "");
        getContext().getRequest().setAttribute("noLot", noLot);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/dev_maklumat_hakmilik_detail.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/common/maklumat_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RPYAB");

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (perihalPemohon == null || perihalPemohon.equals("")) {
            perihalPemohon = "TIADA DATA.";
        }
        if (perihalTanah == null || perihalTanah.equals("")) {
            perihalTanah = "TIADA DATA.";
        }
        if (perihalPermohonan == null || perihalPermohonan.equals("")) {
            perihalPermohonan = "TIADA DATA.";
        }
        if (harga == null || harga.equals("")) {
            harga = "TIADA DATA.";
        }
        if (perakuan == null || perakuan.equals("")) {
            perakuan = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }
        if (perakuanptgmelaka == null || perakuanptgmelaka.equals("")) {
            perakuanptgmelaka = "TIADA DATA.";
        }
        if (sementara == null || sementara.equals("")) {
            sementara = "TIADA DATA.";
        }
        if (keputusanDipohon == null || keputusanDipohon.equals("")) {
            keputusanDipohon = "TIADA DATA.";
        }


        listUlasan.add(tujuan);
        listUlasan.add(perihalPermohonan);
        listUlasan.add(perihalPemohon);
        listUlasan.add(perihalTanah);
        listUlasan.add(harga);
        listUlasan.add(perakuan);
        listUlasan.add(perakuanptgmelaka);
        listUlasan.add(tajuk);
        listUlasan.add(sementara);
        listUlasan.add(keputusanDipohon);


        listSubtajuk.add("");
        listSubtajuk.add("2.1");
        listSubtajuk.add("2.2");
        listSubtajuk.add("2.4");
        listSubtajuk.add("2.8");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
        listBill.add(new Integer(2));
        listBill.add(new Integer(2));
        listBill.add(new Integer(2));
        listBill.add(new Integer(2));
        listBill.add(new Integer(4));
        listBill.add(new Integer(5));
        listBill.add(new Integer(6));
        listBill.add(new Integer(8));
        listBill.add(new Integer(10));

        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 2 && kertasKandungan.getSubtajuk().equals("2.1")) {
                        kertasKandungan.setKandungan(perihalPermohonan);
                    } else if (kertasKandungan.getBil() == 2 && kertasKandungan.getSubtajuk().equals("2.2")) {
                        kertasKandungan.setKandungan(perihalPemohon);
                    } else if (kertasKandungan.getBil() == 2 && kertasKandungan.getSubtajuk().equals("2.4")) {
                        kertasKandungan.setKandungan(perihalTanah);
                    } else if (kertasKandungan.getBil() == 2 && kertasKandungan.getSubtajuk().equals("2.8")) {
                        kertasKandungan.setKandungan(harga);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(perakuan);
//                    } else if (kertasKandungan.getBil() == 5) {
//                        kertasKandungan.setKandungan(perakuanptgmelaka);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(tajuk);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(sementara);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(keputusanDipohon);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("RENCANA PERTIMBANGAN YAB");
                    permohonanKertas.setKodDokumen(kd);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String data = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RENCANA PERTIMBANGAN YAB");
                permohonanKertas.setKodDokumen(kd);
                kk.setKertas(permohonanKertas);
                kk.setBil(bil);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(data);
                kk.setSubtajuk(subtajuk);
                kk.setCawangan(pengguna.getKodCawangan());
                kk.setAktif('Y');
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }
        }
        if (!pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan1 = devService.findKertasKandByIdKertasSubtajuk(permohonanKertas.getIdKertas(), 2, "2.3");
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan1.size() != 0 && i <= senaraiKandungan1.size()) {
                    Long id = senaraiKandungan1.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDiKemaskiniOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(2);
                String kandungan = getContext().getRequest().getParameter("ahliLembaga" + i);
                //LOG.info("--------kandungan-------:" + kandungan);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("2.3." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

            senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
            kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan2.size() != 0 && i <= senaraiKandungan2.size()) {
                    Long id = senaraiKandungan2.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDiKemaskiniOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(3);
                String kandungan = getContext().getRequest().getParameter("ulasan" + i);
                //LOG.info("--------kandungan-------:" + kandungan);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("3." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                    Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDiKemaskiniOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(5);
                String kandungan = getContext().getRequest().getParameter("perakuanPengarah" + i);
                // LOG.info("--------kandungan-------:"+kandungan);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("5." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan4 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
            int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int i = 1; i <= kira; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan4.size() != 0 && i <= senaraiKandungan4.size()) {
                    Long id = senaraiKandungan4.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                        iaP = permohonanKertasKandungan1.getInfoAudit();
                        iaP.setTarikhKemaskini(new Date());
                        iaP.setDiKemaskiniOleh(pengguna);
                    }
                } else {
                    permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(pengguna);
                }
                permohonanKertasKandungan1.setKertas(permohonanKertas);
                permohonanKertasKandungan1.setBil(9);
                String kandungan = getContext().getRequest().getParameter("syarat" + i);
                //LOG.info("--------kandungan-------:" + kandungan);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("9." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        // modify ulasa Adun
        //LOG.info("======Simpan====ulasanAdun=============:" + ulasanAdun);
        if (ulasanAdun != null) {
            ulasanAdun.setUlasan(ulasan);
            devService.simpanPermohonanRujukanLuar(ulasanAdun);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/rencana_Pertimbangan_Menteri.jsp").addParameter("tab", "true");
        return new RedirectResolution(RencanaPertimbanganMenteriActionBean.class, "showForm");
    }

    public Resolution deleteSingle() {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(RencanaPertimbanganMenteriActionBean.class, "locate");
    }

    public String currentStageId(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        LOG.info(" ******* StageId *******:" + stageId);
        return stageId;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPerihalPemohon() {
        return perihalPemohon;
    }

    public void setPerihalPemohon(String perihalPemohon) {
        this.perihalPemohon = perihalPemohon;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public int getSaizList() {
        return saizList;
    }

    public void setSaizList(int saizList) {
        this.saizList = saizList;
    }

    public Hakmilik getHakmilikSingle() {
        return hakmilikSingle;
    }

    public void setHakmilikSingle(Hakmilik hakmilikSingle) {
        this.hakmilikSingle = hakmilikSingle;
    }

    public String getNohakmilik() {
        return nohakmilik;
    }

    public void setNohakmilik(String nohakmilik) {
        this.nohakmilik = nohakmilik;
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<PermohonanKertasKandungan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public int getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(int rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public String getPerakuanptg() {
        return perakuanptg;
    }

    public void setPerakuanptg(String perakuanptg) {
        this.perakuanptg = perakuanptg;
    }

//    public boolean isPtg() {
//        return ptg;
//    }
//
//    public void setPtg(boolean ptg) {
//        this.ptg = ptg;
//    }
    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanAsal getPermohonanAsal() {
        return permohonanAsal;
    }

    public void setPermohonanAsal(PermohonanAsal permohonanAsal) {
        this.permohonanAsal = permohonanAsal;
    }

    public List<PermohonanPlotPelan> getSenaraiPermohonanPlotPelan() {
        return senaraiPermohonanPlotPelan;
    }

    public void setSenaraiPermohonanPlotPelan(List<PermohonanPlotPelan> senaraiPermohonanPlotPelan) {
        this.senaraiPermohonanPlotPelan = senaraiPermohonanPlotPelan;
    }

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public String getKeputusanDipohon() {
        return keputusanDipohon;
    }

    public void setKeputusanDipohon(String keputusanDipohon) {
        this.keputusanDipohon = keputusanDipohon;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public int getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(int rowCount4) {
        this.rowCount4 = rowCount4;
    }

    public String getSementara() {
        return sementara;
    }

    public void setSementara(String sementara) {
        this.sementara = sementara;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan4() {
        return senaraiKandungan4;
    }

    public void setSenaraiKandungan4(List<PermohonanKertasKandungan> senaraiKandungan4) {
        this.senaraiKandungan4 = senaraiKandungan4;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<PermohonanPlotPelan> getSenaraiPermohonanPlotPelanForK() {
        return senaraiPermohonanPlotPelanForK;
    }

    public void setSenaraiPermohonanPlotPelanForK(List<PermohonanPlotPelan> senaraiPermohonanPlotPelanForK) {
        this.senaraiPermohonanPlotPelanForK = senaraiPermohonanPlotPelanForK;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListHM() {
        return hakmilikPermohonanListHM;
    }

    public void setHakmilikPermohonanListHM(List<HakmilikPermohonan> hakmilikPermohonanListHM) {
        this.hakmilikPermohonanListHM = hakmilikPermohonanListHM;
    }

    public List<String> getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(List<String> noLotList) {
        this.noLotList = noLotList;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getPerakuanptgmelaka() {
        return perakuanptgmelaka;
    }

    public void setPerakuanptgmelaka(String perakuanptgmelaka) {
        this.perakuanptgmelaka = perakuanptgmelaka;
    }

    public String getTarikhMeeting() {
        return tarikhMeeting;
    }

    public void setTarikhMeeting(String tarikhMeeting) {
        this.tarikhMeeting = tarikhMeeting;
    }

    public Permohonan getHavePPT() {
        return havePPT;
    }

    public void setHavePPT(Permohonan havePPT) {
        this.havePPT = havePPT;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public List<PermohonanKertas> getTarikhPersidangan() {
        return tarikhPersidangan;
    }

    public void setTarikhPersidangan(List<PermohonanKertas> tarikhPersidangan) {
        this.tarikhPersidangan = tarikhPersidangan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.PermohonanPlotPelan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pembangunan/melaka/rencanaPertimbanganPTG")
public class RencanaPertimbanganPTGActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RencanaPertimbanganPTGActionBean.class);
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
    BPelService service;
    @Inject
    PembangunanUtility pembangunanUtility;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
//    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private Pengguna pengguna;
    private KodDokumen kd;
    private PermohonanKertasKandungan kandunganK;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanRujukanLuar> ulasanList;
    private List<Pemohon> listPemohon;
    private List<Pihak> uniquePemohonList2;
    private String stageId;
    private String tajuk;
    private String tujuan;
    private String ulasanPentadbir;
    private String pejTanah;
    private String perakuan;
    private String gunaTanah;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String lokasi;
    private String nama;
    private List<PermohonanPlotPelan> listplotpelan;
    private PermohonanRujukanLuar ulasanAdun;
    private String keputusanjkbb;
    private String syor;
    private String kpsnPohon;
    private String taskId;
    private Task task = null;
    private String ulasan;
    private List<Pihak> uniquePemohonList;
    private List<PihakPengarah> listPengarah;
    private String tarikhPermohonan;
    private String noResit;
    private String pihakBerkepentingan;
    private String tarikhCukai;
    private String lokasiTanah;
    private String maklumatLain;
    private List<Pemohon> selectedPemohon;
     private List<Pihak> selectedPemohonPihak;
     private List<LaporanTanah> listLaporanTanah;
     private List<PermohonanKertasKandungan> senaraiKandungan2;
     private PermohonanKertasKandungan kertasK2;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_PTG.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_PTG.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Pertimbangan_PTG.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        selectedPemohon = devService.getSelectedPemohon(idPermohonan);
        
        //added by eda on 26/12/2017 because nama n alamat NULL in table pemohon then find at table pihak 
        if(selectedPemohon.isEmpty()){
            selectedPemohonPihak = devService.getSelectedPemohonPihak(idPermohonan);            
        }
        
        //uniquePemohonList = devService.findUniquePemohonByIdPermohonan(idPermohonan);
        uniquePemohonList = devService.findUniquePemohonByIdPermohonan(idPermohonan);
        uniquePemohonList2 = devService.findUniquePemohonByIdPermohonan2(idPermohonan);
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        /* Get pemohonList based on IdPermohonan  */
        listPemohon = new ArrayList<Pemohon>();
        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);
        if (uniquePemohonList2 != null && !uniquePemohonList2.isEmpty()) {
            LOG.info("------size-------:" + uniquePemohonList2.size());
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }

        /* To display PermohonanPlotPelan List */
        listplotpelan = new ArrayList<PermohonanPlotPelan>();
        listplotpelan = devService.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);
        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            StringBuffer kodBPM = new StringBuffer();
            StringBuffer daerah = new StringBuffer();

            // make first letter Caps in each word
            if (hakmilik.getBandarPekanMukim() != null) {
                String str1 = hakmilik.getBandarPekanMukim().getNama();
                StringTokenizer st1 = new StringTokenizer(str1);
                while (st1.hasMoreTokens()) {
                    String t1 = st1.nextToken();
                    t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                    kodBPM.append(t1 + " ");
                }
            }
            // make first letter Caps in each word
            if (hakmilik.getDaerah() != null) {
                String str1 = hakmilik.getDaerah().getNama();
                StringTokenizer st1 = new StringTokenizer(str1);
                while (st1.hasMoreTokens()) {
                    String t1 = st1.nextToken();
                    t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                    daerah.append(t1 + " ");
                }
            }

            String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
            String noLot = hakmilik.getNoLot().replaceAll("^0*", "");

            if (w == 0) {
                lokasi = hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah;
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah + ", ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + " " + hakmilik.getLot().getNama() + " " + noLot + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", " + kodBPM + ", Daerah " + daerah;
                }
            }
        }

//        for (int a = 0; a < hakmilikPermohonanList.size(); a++) {
//            hp = permohonan.getSenaraiHakmilik().get(a);
//            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//
//            if (a == 0) {
//                gunaTanah = hakmilik.getKegunaanTanah().getNama();
//            }
//
//            if (a > 0) {
//                if (a <= ((hakmilikPermohonanList.size() + 2) - 4)) {
//                    gunaTanah = gunaTanah + ", " + hakmilik.getKegunaanTanah().getNama() + ", ";
//                } else if (a == (hakmilikPermohonanList.size() - 1)) {
//                    gunaTanah = gunaTanah + " dan " + hakmilik.getKegunaanTanah().getNama();
//                }
//            }
//
//        }

        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            if (j == 0) {
                nama = pm.getPihak().getNama();
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama() + ", ";
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
        }

        nama = pembangunanUtility.convertStringtoInitCap(nama);
        tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
        // Need to display Ulasan Jabatan List
        ulasanList = devService.findUlasanJabatanTek(idPermohonan);
        ulasanAdun = devService.findUlasanAdun(idPermohonan);
        if (ulasanAdun != null) {
            ulasan = ulasanAdun.getUlasan();
        }

        PermohonanKertas kertasP = new PermohonanKertas();
        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "RPPTG");
        if (senaraiKertas.size() > 0) {
            kertasP = senaraiKertas.get(0);
            LOG.info(" **********Extract RPPTG details************:" + kertasP);
            if (kertasP != null) {

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                    LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                    if (kertasK.getBil() == 1) {
                        tajuk = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3) {
                        ulasanPentadbir = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        perakuan = kertasK.getKandungan();
                        pejTanah = kertasK.getSubtajuk();
                    } else if (kertasK.getBil() == 5) {
                        keputusanjkbb = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6) {
                        syor = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7) {
                        kpsnPohon = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8) {
                        tarikhCukai = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 9) {
                        noResit = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 10) {
                        pihakBerkepentingan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 11) {
                        lokasiTanah = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 12) {
                        maklumatLain = kertasK.getKandungan();
                    }
                }
            }

        } else {
            /* first time login display default data from JKBB */
            String temp1 = "";
            String temp2 = "";
            List<PermohonanKertas> senaraiKertas1 = new ArrayList<PermohonanKertas>();
            senaraiKertas1 = devService.findSenaraiKertasByKod(idPermohonan, "JKBB");
            if (senaraiKertas1.size() > 0) {
                kertasP = senaraiKertas1.get(0);

                if (kertasP != null) {
                    LOG.info(" ******Extract JKBB details********:" + kertasP);

                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                        LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                        PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
                        kertasK2 = kertasP.getSenaraiKandungan().get(j);
                        if (kertasK2.getBil() == 5) {
                            ulasanPentadbir = kertasK2.getKandungan();
                        }
                    }
                }
            }
        }

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
        }
        // Need to display Laporan Tanah details details
//        laporanTanah = devService.findLaporanTanahByIdPermohonan(idPermohonan);
        listLaporanTanah = devService.findLaporanTanahByIdPermohonan3(idPermohonan);
         if (listLaporanTanah.size() > 0) {
            laporanTanah = listLaporanTanah.get(0);
        }
        List<PermohonanKertas> senaraiKertas1 = new ArrayList<PermohonanKertas>();
        senaraiKertas1 = devService.findSenaraiKertasByKod(idPermohonan, "RPPTG");
        if (senaraiKertas1.size() == 0) {
            if (laporanTanah != null) {
            lokasiTanah = "Keadaan semasa tanah adalah " + (laporanTanah.getKeadaanTanah() != null ? laporanTanah.getKeadaanTanah() : null) + ". Klasifikasi tanah adalah "
                    + (laporanTanah.getStrukturTanah() != null ? laporanTanah.getStrukturTanah().getNama() : null)
                    + " dan dilintasi oleh " + dilintasiOleh(laporanTanah) + "." + "\n" + "Tanah terletak di "
                    + (laporanTanah.getKedudukanTanah() != null ? laporanTanah.getKedudukanTanah() : null);
            
//            lokasiTanah = "Keadaan semasa tanah adalah " + laporanTanah.getKeadaanTanah() + ". Klasifikasi tanah adalah "
//                    + (laporanTanah.getStrukturTanah() != null ? laporanTanah.getStrukturTanah().getNama() : null)
//                    + " dan dilintasi oleh " + dilintasiOleh(laporanTanah) + "." + "\n" + "Tanah terletak di "
//                    + (laporanTanah.getKedudukanTanah() != null ? laporanTanah.getKedudukanTanah() : null);
            }else{
                lokasiTanah ="";
            }
        }
        
        if (tajuk == null && tujuan == null) {
            if (permohonan.getSebab() != null) {
                tajuk = permohonan.getSebab();
                tujuan = "Tujuan rencana ini ialah untuk mendapatkan kelulusan Pengarah Tanah dan Galian Melaka bagi " + permohonan.getSebab();
            } else {
                tajuk = permohonan.getKodUrusan().getNama() + " di bawah " + permohonan.getKodUrusan().getRujukanKanun() + " oleh " + nama + " di atas hakmilik " + lokasi + ".";
                tujuan = "Tujuan rencana ini ialah untuk mendapatkan kelulusan Pengarah Tanah dan Galian Melaka bagi " + permohonan.getKodUrusan().getNama() + " daripada " + nama + " di atas hakmilik " + lokasi + ".";
            }
        }

        if (ulasanPentadbir == null) {
            String syaratNyataBaru="";
                if(hp.getSyaratNyataBaru() != null){
                    syaratNyataBaru = hp.getSyaratNyataBaru().getSyarat();
                }
                else{
                    syaratNyataBaru="";
                }
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSKSN")) {
                ulasanPentadbir = "Adalah disahkan bahawa permohonan ini telah disediakan serta disemak dengan teliti dan Pentadbir Tanah " + pejTanah + " dengan ini memperakukan agar "
                        + permohonan.getKodUrusan().getNama() + " di bawah " + permohonan.getKodUrusan().getRujukanKanun() + " daripada '" + hp.getHakmilik().getSyaratNyata().getSyarat()
                        + "' kepada '" + hp.getSyaratNyataBaru().getSyarat() + "' oleh " + nama + " di atas hakmilik " + lokasi + " adalah ";

            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSBSN")) {
                ulasanPentadbir = "Adalah disahkan bahawa permohonan ini telah disediakan serta disemak dengan teliti dan Pentadbir Tanah " + pejTanah + " dengan ini memperakukan agar "
                        + permohonan.getKodUrusan().getNama() + " di bawah " + permohonan.getKodUrusan().getRujukanKanun() + " untuk membatalkan ungkapan '" + hp.getCatatan()
                        + "' oleh " + nama + " di atas hakmilik " + lokasi + " adalah ";

            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSN")) {
                ulasanPentadbir = "Adalah disahkan bahawa permohonan ini telah disediakan serta disemak dengan teliti dan Pentadbir Tanah " + pejTanah + " dengan ini memperakukan agar "
                        + permohonan.getKodUrusan().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun() + " daripada '" + hp.getHakmilik().getSyaratNyata().getSyarat()
                        + "' kepada '" + syaratNyataBaru + "' oleh " + nama + " di atas " + lokasi + " adalah ";

            } else {
                ulasanPentadbir = " ";
            }
        }


//        keputusan = "Pengarah Tanah dan Galian Melaka adalah dipohon untuk menimbangkan dan membuat keputusan samada bersetuju atau sebaliknya dengan perakuan seperti di para 8 di atas.";

        //taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        //stageId = currentStageId(taskId);
        stageId = "ulasanadunteksediajkbb";
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RPPTG");

//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        stageId = currentStageId(taskId);
        stageId = "ulasanadunteksediajkbb";

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

        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (ulasanPentadbir == null || ulasanPentadbir.equals("")) {
            ulasanPentadbir = "TIADA DATA.";
        }
        if (perakuan == null || perakuan.equals("")) {
            perakuan = "TIADA DATA.";
        }

        if (noResit == null || noResit.equals("")) {
            noResit = "Tiada";
        }

        if (tarikhCukai == null || tarikhCukai.equals("")) {
            tarikhCukai = "Tiada";
        }

        if (lokasiTanah == null || lokasiTanah.equals("")) {
            lokasiTanah = "Tiada";
        }

        if (maklumatLain == null || maklumatLain.equals("")) {
            maklumatLain = "Tiada";
        }

        if (pihakBerkepentingan == null || pihakBerkepentingan.equals("")) {
            pihakBerkepentingan = "Tiada";
        }
        if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {
            if (keputusanjkbb == null || keputusanjkbb.equals("")) {
                keputusanjkbb = "TIADA DATA.";
            }
            if (syor == null || syor.equals("")) {
                syor = "TIADA DATA.";
            }
            if (kpsnPohon == null || kpsnPohon.equals("")) {
                kpsnPohon = "TIADA DATA.";
            }
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(ulasanPentadbir);
        listUlasan.add(perakuan);


        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add(pejTanah);




        if (permohonan.getKodUrusan().getKod().equals("TSPSS")) {
            listUlasan.add(keputusanjkbb);
            listUlasan.add(syor);
            listUlasan.add(kpsnPohon);
            listUlasan.add(tarikhCukai);
            listUlasan.add(noResit);
            listUlasan.add(pihakBerkepentingan);
            listUlasan.add(lokasiTanah);
            listUlasan.add(maklumatLain);

            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");

        } else {
            listUlasan.add("X");
            listUlasan.add("X");
            listUlasan.add("X");
            listUlasan.add(tarikhCukai);
            listUlasan.add(noResit);
            listUlasan.add(pihakBerkepentingan);
            listUlasan.add(lokasiTanah);
            listUlasan.add(maklumatLain);

            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");
            listSubtajuk.add("");

        }

        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "RPPTG");
        if (senaraiKertas.size() > 0) {
            permohonanKertas = senaraiKertas.get(0);
        } else {
            permohonanKertas = null;
        }

        if (permohonanKertas != null) {
            /*  update datails  */
           if (kertasK != null) {
                if (!kertasK.getKandungan().isEmpty()) {
                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                        kertasK = new PermohonanKertasKandungan();
                        kertasK = permohonanKertas.getSenaraiKandungan().get(j);
                        if (kertasK.getBil() == 1) {
                            kertasK.setKandungan(tajuk);
                        } else if (kertasK.getBil() == 2) {
                            kertasK.setKandungan(tujuan);
                        } else if (kertasK.getBil() == 3) {
                            kertasK.setKandungan(ulasanPentadbir);
                        } else if (kertasK.getBil() == 4) {
                            kertasK.setKandungan(perakuan);
                        } else if (kertasK.getBil() == 5) {
                            kertasK.setKandungan(keputusanjkbb);
                        } else if (kertasK.getBil() == 6) {
                            kertasK.setKandungan(syor);
                        } else if (kertasK.getBil() == 7) {
                            kertasK.setKandungan(kpsnPohon);
                        } else if (kertasK.getBil() == 8) {
                            kertasK.setKandungan(tarikhCukai);
                        } else if (kertasK.getBil() == 9) {
                            kertasK.setKandungan(noResit);
                        } else if (kertasK.getBil() == 10) {
                            kertasK.setKandungan(pihakBerkepentingan);
                        } else if (kertasK.getBil() == 11) {
                            kertasK.setKandungan(lokasiTanah);
                        } else if (kertasK.getBil() == 12) {
                            kertasK.setKandungan(maklumatLain);
                        }
                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("RENCANA PERTIMBANGAN PTG");
                        kertasK.setInfoAudit(infoAudit);
                        devService.simpanPermohonanKertas(permohonanKertas);
                        devService.simpanPermohonanKertasKandungan(kertasK);
                    }                                               
                        
                }
            }    

        } else {
            /* first time save details   */
            permohonanKertas = new PermohonanKertas();
            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String jabatan = (String) listSubtajuk.get(i);
                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RENCANA PERTIMBANGAN PTG");
                permohonanKertas.setKodDokumen(kd);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(i + 1);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setSubtajuk(jabatan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }
        
        //checking missing listUlasan
            String check3 = null;
            String check4 = null;
            String check8 = null;
            String check9 = null;
            String check10 = null;
            String check11 = null;
            String check12 = null;
            for (int x = 0; x < permohonanKertas.getSenaraiKandungan().size(); x++) {
                kertasK = permohonanKertas.getSenaraiKandungan().get(x);
                if (kertasK.getBil() == 3) {
                    check3 = "ada3";
                }
                if (kertasK.getBil() == 4) {
                    check4 = "ada4";
                }
                if (kertasK.getBil() == 8) {
                    check8 = "ada8";
                }
                if (kertasK.getBil() == 9) {
                    check9 = "ada9";
                }
                if (kertasK.getBil() == 10) {
                    check9 = "ada10";
                }
                if (kertasK.getBil() == 11) {
                    check9 = "ada11";
                }
                if (kertasK.getBil() == 12) {
                    check9 = "ada12";
                }

            }
        //if(check == null){
            if (check3 == null) {
                //senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
                   // if (senaraiKandungan2.isEmpty()){
                        if (StringUtils.isNotBlank(ulasanPentadbir)) {
                        kertasK = new PermohonanKertasKandungan();
                        kertasK.setBil(3);
                        kertasK.setKandungan(ulasanPentadbir);
                        kertasK.setKertas(permohonanKertas);
                        kertasK.setInfoAudit(infoAudit);
                        //kertasK.setSubtajuk(pejTanah);
                        kertasK.setCawangan(pengguna.getKodCawangan());
                        devService.simpanPermohonanKertasKandungan(kertasK);
                    }
               // }

            }
            if (check4 == null) {                        
                if (StringUtils.isNotBlank(perakuan)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(4);
                    kertasK.setKandungan(perakuan);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            if (check8 == null) {
                if (StringUtils.isNotBlank(tarikhCukai)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(8);
                    kertasK.setKandungan(tarikhCukai);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    //kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            if (check9 == null) {
                //kertasK = new PermohonanKertasKandungan();
                if (StringUtils.isNotBlank(noResit)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(9);
                    kertasK.setKandungan(noResit);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    //kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            if (check10 == null) {
                //kertasK = new PermohonanKertasKandungan();
                if (StringUtils.isNotBlank(pihakBerkepentingan)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(10);
                    kertasK.setKandungan(pihakBerkepentingan);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    //kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            if (check11 == null) {
                //kertasK = new PermohonanKertasKandungan();
                if (StringUtils.isNotBlank(lokasiTanah)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(11);
                    kertasK.setKandungan(lokasiTanah);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    //kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            if (check12 == null) {
                //kertasK = new PermohonanKertasKandungan();
                if (StringUtils.isNotBlank(maklumatLain)) {
                    kertasK = new PermohonanKertasKandungan();
                    kertasK.setBil(12);
                    kertasK.setKandungan(maklumatLain);
                    kertasK.setKertas(permohonanKertas);
                    kertasK.setInfoAudit(infoAudit);
                    //kertasK.setSubtajuk(pejTanah);
                    kertasK.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }

        ulasanList = new ArrayList<PermohonanRujukanLuar>();
        ulasanList = devService.findUlasanJabatanTek(idPermohonan);
        ulasanAdun = devService.findUlasanAdun(idPermohonan);
        LOG.info("==========ulasan=============:" + ulasan);
        if (ulasanAdun != null) {
            LOG.info("==========ulasanAdun=============:" + ulasanAdun);
            ulasanAdun.setUlasan(ulasan);
            devService.simpanPermohonanRujukanLuar(ulasanAdun);
        }
        if (!ulasanList.isEmpty()) {
            for (int i = 1; i <= ulasanList.size(); i++) {
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                String ulasanJabatan = getContext().getRequest().getParameter("ulasanJabatan" + i);
                LOG.info("==========ulasanJabatan=============:" + ulasanJabatan);
                mohonRujLuar = ulasanList.get(i - 1);
                mohonRujLuar.setUlasan(ulasanJabatan);
                //mohonRujLuar.setNamaAgensi(pembangunanUtility.convertStringtoInitCap(mohonRujLuar.getNamaAgensi())); 
                devService.simpanPermohonanRujukanLuar(mohonRujLuar);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/rencana_Pertimbangan_PTG.jsp").addParameter("tab", "true");
    }

    public String currentStageId(String taskId) {
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
            //stageId = "ulasanadunteksediajkbb";
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

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
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

    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    public String getUlasanPentadbir() {
        return ulasanPentadbir;
    }

    public void setUlasanPentadbir(String ulasanPentadbir) {
        this.ulasanPentadbir = ulasanPentadbir;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getGunaTanah() {
        return gunaTanah;
    }

    public void setGunaTanah(String gunaTanah) {
        this.gunaTanah = gunaTanah;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<PermohonanRujukanLuar> getUlasanList() {
        return ulasanList;
    }

    public void setUlasanList(List<PermohonanRujukanLuar> ulasanList) {
        this.ulasanList = ulasanList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public String getKeputusanjkbb() {
        return keputusanjkbb;
    }

    public void setKeputusanjkbb(String keputusanjkbb) {
        this.keputusanjkbb = keputusanjkbb;
    }

    public String getKpsnPohon() {
        return kpsnPohon;
    }

    public void setKpsnPohon(String kpsnPohon) {
        this.kpsnPohon = kpsnPohon;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<Pihak> getUniquePemohonList() {
        return uniquePemohonList;
    }

    public void setUniquePemohonList(List<Pihak> uniquePemohonList) {
        this.uniquePemohonList = uniquePemohonList;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTarikhCukai() {
        return tarikhCukai;
    }

    public void setTarikhCukai(String tarikhCukai) {
        this.tarikhCukai = tarikhCukai;
    }

    public String getPihakBerkepentingan() {
        return pihakBerkepentingan;
    }

    public void setPihakBerkepentingan(String pihakBerkepentingan) {
        this.pihakBerkepentingan = pihakBerkepentingan;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public String getMaklumatLain() {
        return maklumatLain;
    }

    public void setMaklumatLain(String maklumatLain) {
        this.maklumatLain = maklumatLain;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }

    public List<Pemohon> getSelectedPemohon() {
        return selectedPemohon;
    }

    public void setSelectedPemohon(List<Pemohon> selectedPemohon) {
        this.selectedPemohon = selectedPemohon;
    }
    
    
    
    

    private String dilintasiOleh(LaporanTanah laporanTanah) {
        String caption = new String();
        if (laporanTanah.getDilintasLaluanGas() != null) {
            if (laporanTanah.getDilintasLaluanGas().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Laluan Gas";
                } else {
                    caption = ",Laluan Gas";
                }
            }
        }

        if (laporanTanah.getDilintasPaip() != null) {
            if (laporanTanah.getDilintasPaip().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Paip Air";
                } else {
                    caption = ",Paip Air";
                }
            }
        }
        if (laporanTanah.getDilintasParit() != null) {
            if (laporanTanah.getDilintasParit().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Parit";
                } else {
                    caption = ",Parit";
                }
            }
        }
        if (laporanTanah.getDilintasSungai() != null) {
            if (laporanTanah.getDilintasSungai().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Sungai";
                } else {
                    caption = ",Sungai";
                }
            }
        }
        if (laporanTanah.getDilintasTaliar() != null) {
            if (laporanTanah.getDilintasTaliar().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Taliair";
                } else {
                    caption = ",Taliair";
                }
            }
        }

        if (laporanTanah.getDilintasTiangElektrik() != null) {
            if (laporanTanah.getDilintasTiangElektrik().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Tiang Elektrik";
                } else {
                    caption = ",Tiang Elektrik";
                }
            }
        }
        if (laporanTanah.getDilintasTiangTelefon() != null) {
            if (laporanTanah.getDilintasTiangTelefon().equals('Y')) {
                if (caption.isEmpty()) {
                    caption = " Tiang Telefon";
                } else {
                    caption = ",Tiang Telefon";
                }
            }
        }
        if (laporanTanah.getDilintasiLain() != null) {

            if (caption.isEmpty()) {
                caption = laporanTanah.getDilintasiLain();
            } else {
                caption = "," + laporanTanah.getDilintasiLain();
            }

        }

        return caption + " ";
    }

    /**
     * @return the selectedPemohonPihak
     */
    public List<Pihak> getSelectedPemohonPihak() {
        return selectedPemohonPihak;
    }

    /**
     * @param selectedPemohonPihak the selectedPemohonPihak to set
     */
    public void setSelectedPemohonPihak(List<Pihak> selectedPemohonPihak) {
        this.selectedPemohonPihak = selectedPemohonPihak;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public PermohonanKertasKandungan getKertasK2() {
        return kertasK2;
    }

    public void setKertasK2(PermohonanKertasKandungan kertasK2) {
        this.kertasK2 = kertasK2;
    }
    
    
    
    
}

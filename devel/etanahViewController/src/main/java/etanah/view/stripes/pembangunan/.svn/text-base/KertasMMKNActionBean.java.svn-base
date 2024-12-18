package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pembangunan/melaka/Kertas_MMKN")
public class KertasMMKNActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasMMKNActionBean.class);
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PembangunanUtility pu;
    @Inject
    BPelService service;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertas kertasP;
    private FasaPermohonan fasaMohon;
    private List<PihakPengarah> listPengarah;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private LaporanTanah laporanTanah;
    private PermohonanKertasKandungan kandunganK;
    private String stageId;
    private String tarikhMesyuarat;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
    private String tujuan;
    private boolean btn = true;
    private String tajuk;
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan4;
    private String perihalRayuan;
    private String perakuanptg;
    private String pejTanah;
    private String persidangan;
    private String masasidang;
    private String tempatsidang;
    private String lokasi;
    private String lokasi1;
    private String nama;
    private Hakmilik hakmilikSingle;
    private Pengguna pengguna;
    private KodDokumen kd;
    private Task task = null;
    private String taskId;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private int rowCount4;
    private String jam;
    private String minit;
    private String pagiPetang;
    private HakmilikPihakBerkepentingan hakmilikPihak;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/melaka/Kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        return new JSP("pembangunan/melaka/Kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (kertasK != null) {
            btn = false;
        }
        if (stageId.equals("ptd1")) {
            btn = true;
        }

        if (stageId.contentEquals("terimarisalatmmkn") || stageId.contentEquals("terimapindaan") || stageId.contentEquals("pindaankertasmmkn")) {
            btn = true;
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        } else {
            if (!pengguna.getKodCawangan().getKod().equals("00")) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                btn = false;
                getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
            }
        }
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/melaka/Kertas_MMKN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------rehydrate------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        laporanTanah = devService.findLaporanTanahByIdPermohonan(idPermohonan);
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

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        String kodPihak = "PM";
        List<HakmilikPihakBerkepentingan> hakmilikPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
        hakmilikPihakList = devService.findHakmilikPihakByKod(hakmilik.getIdHakmilik(), kodPihak);
        LOG.info("-------idPermohonan----:" + idPermohonan);
//        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);
//        listPemohon = permohonan.getSenaraiPemohon();

//        for (Pemohon p : listPemohon) {
//            listPengarah = p.getPihak().getSenaraiPengarah();
//        }
//        LOG.info("-------listPemohon----:"+listPemohon);
//        if (!(listPemohon.isEmpty())) {
//            LOG.info("-------pemohon1----:"+pemohon);
//            pemohon = listPemohon.get(0);
//        }
        if (!(hakmilikPihakList.isEmpty())) {
            hakmilikPihak = hakmilikPihakList.get(0);
        }

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        // testing purpose
//        stageId = "sediarisalatmmkn";
//       stageId = "";

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        if (!(hakmilikPermohonanList.isEmpty())) {
            hp = hakmilikPermohonanList.get(0);
            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
            String noLot = hakmilik.getNoLot().replaceAll("^0*", "");

            if (w == 0) {
                lokasi = hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                        + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                lokasi1 = hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    lokasi1 = lokasi + ", " + hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";

                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    lokasi1 = lokasi + " dan " + hakmilik.getLot().getNama() + " " + noLot + ", " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                }
            }
        }

        nama = "";
        for (int j = 0; j < hakmilikPihakList.size(); j++) {
            HakmilikPihakBerkepentingan pm = new HakmilikPihakBerkepentingan();
            pm = hakmilikPihakList.get(j);

            if (j == 0) {
                nama = pm.getPihak().getNama();
            }
            if (j > 0) {
                if (j <= ((hakmilikPihakList.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if (j == (hakmilikPihakList.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
        }

        LOG.info("------Nama----" + nama);
        nama = pu.convertStringtoInitCap(nama);

        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "MMKN");
        kertasP = new PermohonanKertas();
        LOG.info("-------senaraiKertas----:" + senaraiKertas.size());
        if (senaraiKertas.size() > 0) {
            kertasP = senaraiKertas.get(0);
            if (kertasP.getTarikhSidang() != null) {
                try {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                    masasidang = tdf.format(kertasP.getTarikhSidang());
                    jam = masasidang.substring(0, 2);
                    minit = masasidang.substring(3, 5);
                    pagiPetang = masasidang.substring(6, 8);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tempatsidang = kertasP.getTempatSidang();
            persidangan = kertasP.getTajuk();

            List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
            kandList = devService.findSenaraiKertasKandunganByIdKertas(kertasP.getIdKertas());
            LOG.info("------rehydrate--getvalues----" + kandList.size());
            for (int j = 0; j < kandList.size(); j++) {
                kertasK = new PermohonanKertasKandungan();
                kertasK = kandList.get(j);
                if (kertasK.getBil() == 1) {
                    tujuan = kertasK.getKandungan();
                } else if (kertasK.getBil() == 3) {
                    perihalRayuan = kertasK.getKandungan();
                } else if (kertasK.getBil() == 5) {
                    perakuanptg = kertasK.getKandungan();
                } else if (kertasK.getBil() == 6) {
                    tajuk = kertasK.getKandungan();
                }
            }

            senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan1 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.1");
            senaraiKandungan2 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.2");
            senaraiKandungan3 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.3");
            senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 4);
            rowCount1 = senaraiKandungan1.size();
            rowCount2 = senaraiKandungan2.size();
            rowCount3 = senaraiKandungan3.size();
            rowCount4 = senaraiKandungan4.size();
        } else {
            tajuk = "PERMOHONAN " + permohonan.getKodUrusan().getNama().toUpperCase() + " DARIPADA " + nama.toUpperCase() + " DI " + lokasi.toUpperCase() + ".";
            tujuan = "Tujuan Kertas ini ialah untuk mendapat pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri untuk " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
            tempatsidang = "Bilik Mesyuarat Tun Perak, Blok Bendahara, Aras 4 (Suite), Seri Negeri Ayer Keroh, Melaka.";
            perakuanptg = "Pengarah Tanah dan Galian Melaka bersetuju dengan perakuan Pentadbir Tanah " + pejTanah + " di para 4 dan diangkat untuk pertimbangan majlis.";
            if (laporanTanah != null && laporanTanah.getHakmilikPermohonan() != null) {
                perihalRayuan = laporanTanah.getHakmilikPermohonan().getHakmilik().getLokasi();
            }
            senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan4 = new ArrayList<PermohonanKertasKandungan>();
            PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertasK3 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertasK4 = new PermohonanKertasKandungan();
            StringBuffer permohonanStr = new StringBuffer("Pentadbir Tanah ");
            // make first letter Caps in each word
            if (pengguna.getKodCawangan() != null) {
                //String str1 = pengguna.getKodCawangan().getName();
                String str1 = pejTanah;
                StringTokenizer st1 = new StringTokenizer(str1);
                while (st1.hasMoreTokens()) {
                    String t1 = st1.nextToken();
                    t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                    permohonanStr.append(t1 + " ");
                }
            }
            permohonanStr.append(" telah menerima permohonan ini pada " + formatter1.format(new Date()));

            StringBuffer pemohonStr = new StringBuffer("Pemohon ialah ");
            LOG.info("-------hakmilikPihak----:" + hakmilikPihak);
            // make first letter Caps in each word
            if (hakmilikPihak != null && hakmilikPihak.getPihak() != null) {
                String str1 = hakmilikPihak.getPihak().getNama();
                StringTokenizer st1 = new StringTokenizer(str1);
                while (st1.hasMoreTokens()) {
                    String t1 = st1.nextToken();
                    t1 = Character.toUpperCase(t1.charAt(0)) + t1.toLowerCase().substring(1);
                    pemohonStr.append(t1 + " ");
                }
            }
            pemohonStr.append(" beralamat " + lokasi1);

            kertasK1.setKandungan(permohonanStr.toString());

            String noHakmilik = hakmilik.getNoHakmilik().replaceAll("^0*", "");
            String noLot = hakmilik.getNoLot().replaceAll("^0*", "");
            kertasK2.setKandungan("No. Hakmilik : " + hakmilik.getKodHakmilik().getKod() + " " + noHakmilik + "\n"
                    + "No. Lot : " + hakmilik.getLot().getNama() + " " + noLot + "\n"
                    + "Mukim : " + hakmilik.getBandarPekanMukim().getNama() + "\n"
                    + "Daerah : " + hakmilik.getDaerah().getNama());

            kertasK3.setKandungan(pemohonStr.toString());
            String sekatan = "";
            String sekatanBaru = "";
            if (hakmilik != null && hakmilik.getSekatanKepentingan() != null) {
                sekatan = hakmilik.getSekatanKepentingan().getSekatan();
            }
            if (hp != null && hp.getSekatanKepentinganBaru() != null) {
                sekatanBaru = hp.getSekatanKepentinganBaru().getSekatan();
            }
            kertasK4.setKandungan("Pentadbir Tanah " + pejTanah + " dengan ini memperakukan supaya permohonan untuk ");
//             "yang berbunyi \n\n"+ sekatan +"\n\n"+sekatanBaru);
            senaraiKandungan1.add(kertasK1);
            senaraiKandungan2.add(kertasK2);
            senaraiKandungan3.add(kertasK3);
            senaraiKandungan4.add(kertasK4);
            rowCount1 = senaraiKandungan1.size();
            rowCount2 = senaraiKandungan2.size();
            rowCount3 = senaraiKandungan3.size();
            rowCount4 = senaraiKandungan4.size();
        }

    }

    public Resolution simpan() throws ParseException {
        LOG.info("------simpan------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKN");

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }

        //Testing Purpose
//        stageId = "sediarisalatmmkn";

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
        ArrayList listBill = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (perihalRayuan == null || perihalRayuan.equals("")) {
            perihalRayuan = "TIADA DATA.";
        }
        if (perakuanptg == null || perakuanptg.equals("")) {
            perakuanptg = "TIADA DATA.";
        }
        if (persidangan == null || persidangan.equals("")) {
            persidangan = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listUlasan.add(perihalRayuan);
        listUlasan.add(perakuanptg);
        listUlasan.add(tajuk);

        listBill.add(new Integer(1));
        listBill.add(new Integer(3));
        listBill.add(new Integer(5));
        listBill.add(new Integer(6));

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        kertasK.setKandungan(tujuan);
                    } else if (kertasK.getBil() == 3) {
                        kertasK.setKandungan(perihalRayuan);
                    } else if (kertasK.getBil() == 5) {
                        kertasK.setKandungan(perakuanptg);
                    } else if (kertasK.getBil() == 6) {
                        kertasK.setKandungan(tajuk);
                    }

                    LOG.info("--------tarikhMesyuarat--------:" + tarikhMesyuarat);
                    LOG.info("--------masa--------:" + (jam + ":" + minit + " " + pagiPetang));
                    try {
                        if (tarikhMesyuarat != null && !jam.equals("00") && !minit.equals("00") && !pagiPetang.equals("00")) {
                            masasidang = jam + ":" + minit + " " + pagiPetang;
                            Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                            permohonanKertas.setTarikhSidang(tarikhsidang);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (permohonan.getSebab() != null) {
                        permohonanKertas.setTajuk(permohonan.getSebab());
                    } else {
                        permohonanKertas.setTajuk(permohonan.getKodUrusan().getNama());
                    }
                    permohonanKertas.setTempatSidang(tempatsidang);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);

                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        } else {
            try {
                if (tarikhMesyuarat != null && !jam.equals("00") && !minit.equals("00") && !pagiPetang.equals("00")) {
                    masasidang = jam + ":" + minit + " " + pagiPetang;
                    Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                    permohonanKertas.setTarikhSidang(tarikhsidang);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            LOG.info("--------tarikhMesyuarat--------:" + tarikhMesyuarat);
            LOG.info("--------masa--------:" + (jam + ":" + minit + " " + pagiPetang));
            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                LOG.info("---------- i ----------:" + i);
                Integer bil = (Integer) listBill.get(i);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kd);
                if (permohonan.getSebab() != null) {
                    permohonanKertas.setTajuk(permohonan.getSebab());
                } else {
                    permohonanKertas.setTajuk(permohonan.getKodUrusan().getNama());
                }
                permohonanKertas.setTempatSidang(tempatsidang);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bil);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
            LOG.debug("record updated successfully........");
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

//      if(!pengguna.getKodCawangan().getKod().equals("00")){
        senaraiKandungan1 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.1");
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
            String kandungan = getContext().getRequest().getParameter("perihalPermohonan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan2 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.2");
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
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("perihalTanah" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.2." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan3 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.3");
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
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
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("perihalPemohon" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 4);
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
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
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("perakuanPtd" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
//       } // if
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        rehydrate();
        return showForm3();
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/Kertas_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution kertasMMKNBaru() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKN");

        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, pengguna);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }

        //Testing Purpose
//        stageId = "ptd1";
//        stageId = "rekodkpsnmmkncetaksurat";

        PermohonanKertas mohonKertas1 = new PermohonanKertas();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (perihalRayuan == null || perihalRayuan.equals("")) {
            perihalRayuan = "TIADA DATA.";
        }
        if (perakuanptg == null || perakuanptg.equals("")) {
            perakuanptg = "TIADA DATA.";
        }
        if (persidangan == null || persidangan.equals("")) {
            persidangan = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }


        listUlasan.add(tujuan);
        listUlasan.add(perihalRayuan);
        listUlasan.add(perakuanptg);
        listUlasan.add(tajuk);

        listBill.add(new Integer(1));
        listBill.add(new Integer(3));
        listBill.add(new Integer(5));
        listBill.add(new Integer(6));

        LOG.info(" start listUlasan.size() :" + listUlasan.size());
        LOG.info(" start listBill.size() :" + listBill.size());

        for (int i = 0; i < listUlasan.size(); i++) {
            String ulasan = (String) listUlasan.get(i);
            Integer bil = (Integer) listBill.get(i);
            kertasK = new PermohonanKertasKandungan();

//                LOG.info("--------tarikhMesyuarat--------:"+tarikhMesyuarat);
//                LOG.info("--------masa--------:"+(jam+":"+minit+" "+pagiPetang));
            try {
                if (tarikhMesyuarat != null && !jam.equals("0") && !minit.equals("0") && !pagiPetang.equals("0")) {
                    masasidang = jam + ":" + minit + " " + pagiPetang;
                    Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                    mohonKertas1.setTarikhSidang(tarikhsidang);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mohonKertas1.setInfoAudit(infoAudit);
            mohonKertas1.setCawangan(pengguna.getKodCawangan());
            mohonKertas1.setPermohonan(permohonan);
            mohonKertas1.setKodDokumen(kd);
            if (permohonan.getSebab() != null) {
                mohonKertas1.setTajuk(permohonan.getSebab());
            } else {
                mohonKertas1.setTajuk(permohonan.getKodUrusan().getNama());
            }
            //mohonKertas1.setTajuk(persidangan);
            mohonKertas1.setTempatSidang(tempatsidang);
            kertasK.setKertas(mohonKertas1);
            kertasK.setBil(bil);
            kertasK.setInfoAudit(infoAudit);
            kertasK.setKandungan(ulasan);
            kertasK.setCawangan(pengguna.getKodCawangan());
            devService.simpanPermohonanKertas(mohonKertas1);
            devService.simpanPermohonanKertasKandungan(kertasK);
        }

        senaraiKandungan1 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.1");
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
            permohonanKertasKandungan1.setKertas(mohonKertas1);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("perihalPermohonan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan2 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.2");
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
            permohonanKertasKandungan1.setKertas(mohonKertas1);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("perihalTanah" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.2." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan3 = devService.findKertasKandByIdKertasSubtajuk(kertasP.getIdKertas(), 2, "2.3");
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
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
            permohonanKertasKandungan1.setKertas(mohonKertas1);
            permohonanKertasKandungan1.setBil(2);
            String kandungan = getContext().getRequest().getParameter("perihalPemohon" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan4 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 4);
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
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
            permohonanKertasKandungan1.setKertas(mohonKertas1);
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("perihalPemohon" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        LOG.debug("record saved successfully........");
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        btn = false;
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/Kertas_MMKN.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(KertasMMKNActionBean.class, "locate");
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public PembangunanService getDevService() {
        return devService;
    }

    public void setDevService(PembangunanService devService) {
        this.devService = devService;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getMasasidang() {
        return masasidang;
    }

    public void setMasasidang(String masasidang) {
        this.masasidang = masasidang;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getPerakuanptg() {
        return perakuanptg;
    }

    public void setPerakuanptg(String perakuanptg) {
        this.perakuanptg = perakuanptg;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTempatsidang() {
        return tempatsidang;
    }

    public void setTempatsidang(String tempatsidang) {
        this.tempatsidang = tempatsidang;
    }

    public Hakmilik getHakmilikSingle() {
        return hakmilikSingle;
    }

    public void setHakmilikSingle(Hakmilik hakmilikSingle) {
        this.hakmilikSingle = hakmilikSingle;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public PermohonanKertas getKertasP() {
        return kertasP;
    }

    public void setKertasP(PermohonanKertas kertasP) {
        this.kertasP = kertasP;
    }

    public String getPerihalRayuan() {
        return perihalRayuan;
    }

    public void setPerihalRayuan(String perihalRayuan) {
        this.perihalRayuan = perihalRayuan;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public int getRowCount4() {
        return rowCount4;
    }

    public void setRowCount4(int rowCount4) {
        this.rowCount4 = rowCount4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan4() {
        return senaraiKandungan4;
    }

    public void setSenaraiKandungan4(List<PermohonanKertasKandungan> senaraiKandungan4) {
        this.senaraiKandungan4 = senaraiKandungan4;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }
}

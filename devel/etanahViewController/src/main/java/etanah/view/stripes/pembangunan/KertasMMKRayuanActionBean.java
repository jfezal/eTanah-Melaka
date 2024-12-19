package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.KodDokumen;
import etanah.dao.KodDokumenDAO;
import etanah.service.BPelService;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pembangunan/kertas_mmk_rayuan")
public class KertasMMKRayuanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasMMKRayuanActionBean.class);
    @Inject
    PembangunanUtility pu;
    @Inject
    BPelService service;
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
    KodDokumenDAO kodDokumenDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private String tujuan;
    private String asas1;
    private String kanunTanah;
    private String latarBelakang1;
    private String huraianPentadbir1;
    private String syorPentadbir1;
    private String huraianPtg1;
    private String syorPtg1;
    private String ulasanPTG;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private String pejTanah;
    private String tajuk;
    private String nama;
    private String noRujukan;
    private String taskId;
    private String kmno;
    private Pengguna pengguna;
    private String noIdPermohonanBaru;
    private List<PermohonanKertasKandungan> senaraiLatarBelakang = new ArrayList<PermohonanKertasKandungan>();
    private boolean btn;
    private int rowCount2;
    private int rowCount3;
    private int rowCount5;
    private int rowCount6;
    private int rowCount7;
    private int rowCount8;
    private String asas;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private List<PermohonanKertasKandungan> senaraiKandungan6;
    private List<PermohonanKertasKandungan> senaraiKandungan7;
    private List<PermohonanKertasKandungan> senaraiKandungan8;
    private Date tarikhMesyuarat;
    private String penutup;
    private Boolean opFlag = false;

    @DefaultHandler
    public Resolution showForm() {
//        if (opFlag == false) {
//            addSimpleError("Sila isikan maklumat sebelumnya terlebih dahulu");
//        }
        btn = Boolean.FALSE;
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//         if (opFlag == false) {
//            addSimpleError("Sila isikan maklumat sebelumnya terlebih dahulu");
//        }
        //guna utk PTD
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
//         if (opFlag == false) {
//            addSimpleError("Sila isikan maklumat sebelumnya terlebih dahulu");
//        }
        //guna utk PTG
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
//         if (opFlag == false) {
//            addSimpleError("Sila isikan maklumat sebelumnya terlebih dahulu");
//        }
        //guna utk PTG
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());


        rowCount2 = 1;
        rowCount5 = 1;
        rowCount6 = 1;
        rowCount7 = 1;
        rowCount8 = 1;

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                }
            }
        }

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
        }


        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
                senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "MMKNR");
                if (senaraiKertas.size() > 0) {
                    kertasP = senaraiKertas.get(0);
                } else {
                    kertasP = null;
                }

                if (kertasP != null) {
                    if (kertasP.getTarikhSidang() != null) {
                        tarikhMesyuarat = kertasP.getTarikhSidang();
                    }
                }

                if (kertasP != null) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);

                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.1")) {
                            latarBelakang1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3 && kertasK.getSubtajuk().equals("3.1") || (kertasK.getBil() == 3) ) {
                            asas1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5 && kertasK.getSubtajuk().equals("5.1")) {
                            huraianPentadbir1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                            syorPentadbir1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 7) {
                            penutup = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8 && kertasK.getSubtajuk().equals("8.1")) {
                            huraianPtg1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
                            syorPtg1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 10) {
                            ulasanPTG = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 11) {
                            tajuk = kertasK.getKandungan();
                        }

                    }
                    kmno = kertasP.getTajuk();

                    senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();

                    senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                    senaraiKandungan3 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                    senaraiKandungan5 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                    senaraiKandungan6 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                    senaraiKandungan7 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 8);
                    senaraiKandungan8 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 9);

                    rowCount2 = senaraiKandungan2.size();
                    rowCount3 = senaraiKandungan3.size();
                    rowCount5 = senaraiKandungan5.size();
                    rowCount6 = senaraiKandungan6.size();
                    rowCount7 = senaraiKandungan7.size();
                    rowCount8 = senaraiKandungan8.size();

                    if (rowCount2 == 0) {
                        rowCount2 = 1;
                    }
                    if (rowCount3 == 0) {
                        rowCount3 = 1;
                    }
                    if (rowCount5 == 0) {
                        rowCount5 = 1;
                    }
                    if (rowCount6 == 0) {
                        rowCount6 = 1;
                    }
                    if (rowCount7 == 0) {
                        rowCount7 = 1;
                    }
                    if (rowCount8 == 0) {
                        rowCount8 = 1;
                    }

                }
            }

            noIdPermohonanBaru = permohonan.getIdPermohonan();
            noRujukan = permohonan.getPermohonanSebelum().getIdPermohonan();

            service = new BPelService();
            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
            }
            //stageId = "rekodkeputusanmmk";

        }
        if (permohonan.getPermohonanSebelum() != null) {
            if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null && !pengguna.getKodCawangan().getDaerah().getKod().equals("00")) {
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("01")) {
                    pejTanah = "Jelebu";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("02")) {
                    pejTanah = "Kuala Pilah";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("03")) {
                    pejTanah = "Port Dickson";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("04")) {
                    pejTanah = "Rembau";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("05")) {
                    pejTanah = "Seremban";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("06")) {
                    pejTanah = "Tampin";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("07")) {
                    pejTanah = "Jempol";
                }
                if (pengguna.getKodCawangan().getDaerah().getKod().equals("08")) {
                    pejTanah = "Kecil Gemas";
                }
                noIdPermohonanBaru = permohonan.getIdPermohonan();
                noRujukan = permohonan.getPermohonanSebelum().getIdPermohonan();
                tajuk = "Kertas MAJLIS MESYUARAT KERAJAAN bagi " + pu.convertStringtoInitCap(permohonan.getKodUrusan().getNama()) + " daripada " + pu.convertStringtoInitCap(nama) + " di " + pu.convertStringtoInitCap(lokasi) + ".";
                tujuan = "Tujuan kertas ini adalah untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan Negeri Sembilan bagi " + pu.convertStringtoInitCap(nama) + " untuk " + pu.convertStringtoInitCap(permohonan.getKodUrusan().getNama()) + " di " + pu.convertStringtoInitCap(lokasi) + ".";
                latarBelakang1 = "Pentadbir Tanah " + pu.convertStringtoInitCap(pejTanah) + " telah menerima satu permohonan daripada " + pu.convertStringtoInitCap(nama) + " untuk " + pu.convertStringtoInitCap(permohonan.getKodUrusan().getNama()) + " di " + pu.convertStringtoInitCap(lokasi) + ".";
                penutup = "Dikemukakan permohonan ini untuk pertimbangan serta kelulusan tuan";
            }
        }
//        } else addSimpleError("masukan ikot turn la !!!");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        KodDokumen kd = kodDokumenDAO.findById("MMKNR");
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

        if (tajuk == null || tajuk.equals("")) {
            tajuk = " ";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = " ";
        }
        if (asas == null || asas.equals("")) {
            asas = " ";
        }
        if (ulasanPTG == null || ulasanPTG.equals("")) {
            ulasanPTG = " ";
        }
        if (penutup == null || penutup.equals("")) {
            penutup = " ";
        }

        listUlasan.add(tujuan);
//        listUlasan.add(asas);
        listUlasan.add(penutup);
        listUlasan.add(ulasanPTG);
        listUlasan.add(tajuk);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
//        listBill.add(new Integer(3));
        listBill.add(new Integer(7));
        listBill.add(new Integer(10));
        listBill.add(new Integer(11));


        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(asas1);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(penutup);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(ulasanPTG);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(tajuk);
                    }
//                    devService.simpanPermohonanKertas(permohonanKertas);
//                    devService.simpanPermohonanKertasKandungan(kertasKandungan);

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    if (kmno != null) {
                        permohonanKertas.setTajuk(kmno);
                    } else {
                        permohonanKertas.setTajuk("..");
                    }

                    if (tarikhMesyuarat != null) {
                        permohonanKertas.setTarikhSidang(tarikhMesyuarat);
                    }
                    permohonanKertas.setNomborRujukan(noRujukan);
                    kertasKandungan.setInfoAudit(infoAudit);
//                    kertasK.setKandungan(asas);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }

            }
        } else {
            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bill = (Integer) listBill.get(i);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setKodDokumen(kd);
                if (kmno != null) {
                    permohonanKertas.setTajuk(kmno);
                } else {
                    permohonanKertas.setTajuk("..");
                }
                if (tarikhMesyuarat != null) {
                    permohonanKertas.setTarikhSidang(tarikhMesyuarat);
                }
                kk.setKertas(permohonanKertas);
                kk.setBil(bill);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(ulasan);
                kk.setSubtajuk(subtajuk);
                kk.setCawangan(pengguna.getKodCawangan());
//                kk.setKandungan(asas);
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
//                devService.simpanPermohonanKertasKandungan(listUlasan);
            }

        }

//        if (!pengguna.getKodCawangan().getKod().equals("00") && !stageId.equals("rekodkeputusanmmk")) {
        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count 2------------:" + kira2);
        for (int i = 1; i <= kira2; i++) {
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
            String kandungan = getContext().getRequest().getParameter("latarBelakang" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }

            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("-----------count 3------------:" + kira2);
        for (int i = 1; i <= kira2; i++) {
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
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("asas" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }

            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        LOG.info("-----------count------------:" + kira5);
        for (int i = 1; i <= kira5; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan5.size() != 0 && i <= senaraiKandungan5.size()) {
                Long id = senaraiKandungan5.get(i - 1).getIdKandungan();
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
            String kandungan = getContext().getRequest().getParameter("huraianPentadbir" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        LOG.info("-----------count 6------------:" + kira6);
        for (int i = 1; i <= kira6; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan6.size() != 0 && i <= senaraiKandungan6.size()) {
                Long id = senaraiKandungan6.get(i - 1).getIdKandungan();
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
            permohonanKertasKandungan1.setBil(6);
            String kandungan = getContext().getRequest().getParameter("syorPentadbir" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
//        }

        if (pengguna.getKodCawangan().getKod().equals("00") && !stageId.equals("rekodkeputusanmmk")) {
            senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
            int kira7 = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
            LOG.info("-----------count 7------------:" + kira7);
            for (int i = 1; i <= kira7; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan7.size() != 0 && i <= senaraiKandungan7.size()) {
                    Long id = senaraiKandungan7.get(i - 1).getIdKandungan();
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
                permohonanKertasKandungan1.setBil(8);
                String kandungan = getContext().getRequest().getParameter("huraianPtg" + i);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = permohonanKertasKandungan1.getKandungan();
                    if (kandungan == null || kandungan.equals("")) {
                        kandungan = " ";
                    }
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("8." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }

            senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
            int kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));
            LOG.info("-----------count 8------------:" + kira8);
            for (int i = 1; i <= kira8; i++) {
                InfoAudit iaP = new InfoAudit();
                PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                if (senaraiKandungan8.size() != 0 && i <= senaraiKandungan8.size()) {
                    Long id = senaraiKandungan8.get(i - 1).getIdKandungan();
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
                String kandungan = getContext().getRequest().getParameter("syorPtg" + i);
               if (kandungan == null || kandungan.equals("")) {
                    kandungan = permohonanKertasKandungan1.getKandungan();
                    if (kandungan == null || kandungan.equals("")) {
                        kandungan = " ";
                    }
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("9." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        rowCount2 = senaraiKandungan2.size();

        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        rowCount3 = senaraiKandungan3.size();

        senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        rowCount5 = senaraiKandungan5.size();

        senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        rowCount6 = senaraiKandungan6.size();

        senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan7 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
        rowCount7 = senaraiKandungan7.size();

        senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan8 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        rowCount8 = senaraiKandungan8.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            if (stageId.equals("rekodkeputusanmmk")) {
                getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        btn = Boolean.TRUE;
        return new JSP("pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSingle() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
            e.printStackTrace();
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        rehydrate();
        btn = Boolean.TRUE;
        if (pengguna.getKodCawangan().getKod().equals("00")) {
            if (stageId.equals("rekodkeputusanmmk")) {
                getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
            }
        } else {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_kertasMMK_rayuan.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getHuraianPtg1() {
        return huraianPtg1;
    }

    public void setHuraianPtg1(String huraianPtg1) {
        this.huraianPtg1 = huraianPtg1;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public String getSyorPtg1() {
        return syorPtg1;
    }

    public void setSyorPtg1(String syorPtg1) {
        this.syorPtg1 = syorPtg1;
    }

    public String getHuraianPentadbir1() {
        return huraianPentadbir1;
    }

    public void setHuraianPentadbir1(String huraianPentadbir1) {
        this.huraianPentadbir1 = huraianPentadbir1;
    }

    public String getSyorPentadbir1() {
        return syorPentadbir1;
    }

    public void setSyorPentadbir1(String syorPentadbir1) {
        this.syorPentadbir1 = syorPentadbir1;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKanunTanah() {
        return kanunTanah;
    }

    public void setKanunTanah(String kanunTanah) {
        this.kanunTanah = kanunTanah;
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

    public String getUlasanPTG() {
        return ulasanPTG;
    }

    public void setUlasanPTG(String ulasanPTG) {
        this.ulasanPTG = ulasanPTG;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
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

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getNoIdPermohonanBaru() {
        return noIdPermohonanBaru;
    }

    public void setNoIdPermohonanBaru(String noIdPermohonanBaru) {
        this.noIdPermohonanBaru = noIdPermohonanBaru;
    }

    public List<PermohonanKertasKandungan> getSenaraiLatarBelakang() {
        return senaraiLatarBelakang;
    }

    public void setSenaraiLatarBelakang(List<PermohonanKertasKandungan> senaraiLatarBelakang) {
        this.senaraiLatarBelakang = senaraiLatarBelakang;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(int rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public int getRowCount6() {
        return rowCount6;
    }

    public void setRowCount6(int rowCount6) {
        this.rowCount6 = rowCount6;
    }

    public int getRowCount7() {
        return rowCount7;
    }

    public void setRowCount7(int rowCount7) {
        this.rowCount7 = rowCount7;
    }

    public int getRowCount8() {
        return rowCount8;
    }

    public void setRowCount8(int rowCount8) {
        this.rowCount8 = rowCount8;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
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

    public List<PermohonanKertasKandungan> getSenaraiKandungan5() {
        return senaraiKandungan5;
    }

    public void setSenaraiKandungan5(List<PermohonanKertasKandungan> senaraiKandungan5) {
        this.senaraiKandungan5 = senaraiKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan6() {
        return senaraiKandungan6;
    }

    public void setSenaraiKandungan6(List<PermohonanKertasKandungan> senaraiKandungan6) {
        this.senaraiKandungan6 = senaraiKandungan6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan7() {
        return senaraiKandungan7;
    }

    public void setSenaraiKandungan7(List<PermohonanKertasKandungan> senaraiKandungan7) {
        this.senaraiKandungan7 = senaraiKandungan7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan8() {
        return senaraiKandungan8;
    }

    public void setSenaraiKandungan8(List<PermohonanKertasKandungan> senaraiKandungan8) {
        this.senaraiKandungan8 = senaraiKandungan8;
    }

    public String getAsas() {
        return asas;
    }

    public void setAsas(String asas) {
        this.asas = asas;
    }

    public Date getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(Date tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getPenutup() {
        return penutup;
    }

    public void setPenutup(String penutup) {
        this.penutup = penutup;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public String getAsas1() {
        return asas1;
    }

    public void setAsas1(String asas1) {
        this.asas1 = asas1;
    }
}

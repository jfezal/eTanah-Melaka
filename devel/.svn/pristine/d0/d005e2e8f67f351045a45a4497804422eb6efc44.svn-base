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

@UrlBinding("/pembangunan/KertasRingkasPTD")
public class KertasRingkasPTDActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasRingkasPTDActionBean.class);
    @Inject
    PembangunanUtility pu;
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
    private Pemohon senaraipemohon ;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
//    private Pihak pihak ;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private String tujuan;
    private String kanunTanah;
    private String latarBelakang1;
    private String huraianPentadbir1;
    private String syorPentadbir1;
    private String huraianPtg;
    private String syorPtg;
    private String keputusanPTD;
    private String ulasanPTD;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private String pejTanah;
    private String tajuk;
    private String nama;
    private String asas;
    private int rowCount2;
    private int rowCount5;
    private int rowCount6;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private List<PermohonanKertasKandungan> senaraiKandungan6;
    private boolean btn;
    private Pengguna pengguna;
    private String penutup;

    @DefaultHandler
    public Resolution showForm() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayuanLanjutTempohBayaran/kertas_ringkas_PTD.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = Boolean.FALSE;
        return new JSP("pembangunan/rayuanLanjutTempohBayaran/kertas_ringkas_PTD.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        rowCount2 = 1;
        rowCount5 = 1;
        rowCount6 = 1;

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
                senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "MMKN");
                if (senaraiKertas.size() > 0) {
                    kertasP = senaraiKertas.get(0);
                } else {
                    kertasP = null;
                }

                if (kertasP != null) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);


                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.1")) {
                            latarBelakang1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            asas = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5 && kertasK.getSubtajuk().equals("5.1")) {
                            huraianPentadbir1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                            syorPentadbir1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 7) {
                            penutup = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8) {
                            ulasanPTD = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9) {
                            tajuk = kertasK.getKandungan();
                        }
                    }

                    senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();

                    senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                    senaraiKandungan5 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                    senaraiKandungan6 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);

                    rowCount2 = senaraiKandungan2.size();
                    rowCount5 = senaraiKandungan5.size();
                    rowCount6 = senaraiKandungan6.size();

                    if (rowCount2 == 0) {
                        rowCount2 = 1;
                    }
                    if (rowCount5 == 0) {
                        rowCount5 = 1;
                    }
                    if (rowCount6 == 0) {
                        rowCount6 = 1;
                    }
                }
            }
        }



        if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null) {
            if (peng.getKodCawangan().getDaerah().getKod().equals("01")) {
                pejTanah = "Jelebu";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("02")) {
                pejTanah = "Kuala Pilah";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("03")) {
                pejTanah = "Port Dickson";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("04")) {
                pejTanah = "Rembau";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("05")) {
                pejTanah = "Seremban";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("06")) {
                pejTanah = "Tampin";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("07")) {
                pejTanah = "Jempol";
            }
            if (peng.getKodCawangan().getDaerah().getKod().equals("08")) {
                pejTanah = "Kecil Gemas";
            }
            tajuk = "Kertas untuk pertimbangan Pentadbir Tanah " + pu.convertStringtoInitCap(pejTanah) + " bagi " + pu.convertStringtoInitCap(permohonan.getKodUrusan().getNama())+ " daripada " + pu.convertStringtoInitCap(nama) + " di " + pu.convertStringtoInitCap(lokasi) + ".";
            tujuan = "Tujuan kertas ini adalah untuk mendapat pertimbangan Pentadbir Tanah " + pu.convertStringtoInitCap(pejTanah) + " bagi " + pu.convertStringtoInitCap(permohonan.getKodUrusan().getNama()) + " daripada " + pu.convertStringtoInitCap(nama) + " di " + pu.convertStringtoInitCap(lokasi) + ".";
//            tujuan.toUpperCase();
            latarBelakang1 = "Pentadbir Tanah " + pu.convertStringtoInitCap(pejTanah) + " telah menerima satu permohonan daripada " + pu.convertStringtoInitCap(nama) + " untuk " + permohonan.getKodUrusan().getNama() + " di " + pu.convertStringtoInitCap(lokasi) + ".";
            penutup = "Dikemukakan permohonan ini untuk pertimbangan serta kelulusan tuan";
        }
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        KodDokumen kd = kodDokumenDAO.findById("MMKN");
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
            tajuk = "TIADA DATA.";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (ulasanPTD == null || ulasanPTD.equals("")) {
            ulasanPTD = "TIADA DATA.";
        }
        if (asas == null || asas.equals("")) {
            asas = "TIADA DATA.";
        }
        if (penutup == null || penutup.equals("")) {
            penutup = "TIADA DATA.";
        }


        listUlasan.add(tujuan);
        listUlasan.add(asas);
        listUlasan.add(penutup);
        listUlasan.add(ulasanPTD);
        listUlasan.add(tajuk);


        listBill.add(new Integer(1));
        listBill.add(new Integer(3));
        listBill.add(new Integer(7));
        listBill.add(new Integer(8));
        listBill.add(new Integer(9));

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        System.out.println("list ulasan: " + listUlasan.size());
        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(asas);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(penutup);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(ulasanPTD);
                    } else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(tajuk);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk("KERTAS RINGKAS PTD");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);

                    BPelService service = new BPelService();
                    String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                    if (StringUtils.isNotBlank(taskId)) {
                        Task t = null;
                        t = service.getTaskFromBPel(taskId, pengguna);
                        stageId = t.getSystemAttributes().getStage();
                        System.out.println("-------------stageId: BPEL ON ----" + stageId);
                    }
                    if (stageId != null) {
                        if (stageId.equals("keputusanptd3bln")) {
                            permohonanKertas.setTarikhSidang(new java.util.Date());
                            devService.simpanPermohonanKertas(permohonanKertas);
                        }
                    }
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
                permohonanKertas.setTajuk("KERTAS RINGKAS PTD");
                kk.setKertas(permohonanKertas);
                kk.setBil(bill);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(ulasan);
                kk.setSubtajuk(subtajuk);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }
        }

        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        if (kira2 == 0) {
            kira2 = 1;
        }
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

        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        if (kira5 == 0) {
            kira5 = 1;
        }
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
        if (kira6 == 0) {
            kira6 = 1;
        }
        LOG.info("-----------count------------:" + kira6);
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

        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        rowCount2 = senaraiKandungan2.size();
        rowCount5 = senaraiKandungan5.size();
        rowCount6 = senaraiKandungan6.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        btn = Boolean.TRUE;
        return new JSP("pembangunan/rayuanLanjutTempohBayaran/kertas_ringkas_PTD.jsp").addParameter("tab", "true");

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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/rayuanLanjutTempohBayaran/kertas_ringkas_PTD.jsp").addParameter("tab", "true");
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

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
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

    public String getKeputusanPTD() {
        return keputusanPTD;
    }

    public void setKeputusanPTD(String keputusanPTD) {
        this.keputusanPTD = keputusanPTD;
    }

    public String getUlasanPTD() {
        return ulasanPTD;
    }

    public void setUlasanPTD(String ulasanPTD) {
        this.ulasanPTD = ulasanPTD;
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

    public String getAsas() {
        return asas;
    }

    public void setAsas(String asas) {
        this.asas = asas;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
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

    public String getPenutup() {
        return penutup;
    }

    public void setPenutup(String penutup) {
        this.penutup = penutup;
    }

    public Pemohon getSenaraipemohon() {
        return senaraipemohon;
    }

    public void setSenaraipemohon(Pemohon senaraipemohon) {
        this.senaraipemohon = senaraipemohon;
    }
}

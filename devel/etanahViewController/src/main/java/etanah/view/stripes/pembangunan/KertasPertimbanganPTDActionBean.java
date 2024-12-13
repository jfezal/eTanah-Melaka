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
import etanah.dao.KodDokumenDAO;
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
import etanah.model.KodDokumen;
import etanah.model.PermohonanPlotPelan;
import etanah.service.BPelService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Rohan
 * @Modified By : NageswaraRao
 */
@UrlBinding("/pembangunan/kertas_pertimbangan_ptd")
public class KertasPertimbanganPTDActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasPertimbanganPTDActionBean.class);
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
    private KodDokumen kd;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private String tujuan;
    private String kanunTanah;
    private String butir1;
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
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private int rowCount2;
    private Pengguna peng;
    private Task t;
    private boolean btn;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private List<PermohonanKertasKandungan> senaraiKandungan6;
    private int rowCount3;
    private int rowCount5;
    private int rowCount6;

    @DefaultHandler
    public Resolution showForm() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/dev_Kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/pecahBahagian/dev_Kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    // hide Tambah & Hapus Button
    public Resolution showForm3() {
        btn = Boolean.TRUE;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/dev_Kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        rowCount2 = 1;
        rowCount3 = 1;
        rowCount5 = 1;
        rowCount6 = 1;

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

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
        
        PermohonanPlotPelan mpp = new PermohonanPlotPelan();
        if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
            mpp = (PermohonanPlotPelan) devService.findluasPlotPelanByIdMohon(permohonan.getIdPermohonan());
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                } else {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                }
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    } else {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    }

                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    } else {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    }
                }
            }
        }

        lokasi = lokasi + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " di bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();

        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);
            LOG.info("-----------pm---------:" + pm);
            if (j == 0) {
                nama = pm.getPihak().getNama();
                LOG.info("-----------nama---------:" + nama);
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
                LOG.info("-----------nama2---------:" + nama);
            }
        }


        PermohonanKertas kertasP = new PermohonanKertas();

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            kertasP = devService.findKertasByKod(idPermohonan, "KPTD");

            if (kertasP != null) {

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2 && kertasK.getSubtajuk().equals("2.1")) {
                        latarBelakang1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3 && kertasK.getSubtajuk().equals("3.1")) {
                        butir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5 && kertasK.getSubtajuk().equals("5.1")) {
                        huraianPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                        syorPentadbir1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7) {
                        ulasanPTD = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8) {
                        tajuk = kertasK.getKandungan();
                    }
                }

                senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                rowCount2 = senaraiKandungan2.size();

                senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan3 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                rowCount3 = senaraiKandungan3.size();

                senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan5 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                rowCount5 = senaraiKandungan5.size();

                senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan6 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                rowCount6 = senaraiKandungan6.size();
            }
        }

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

        if (peng.getKodCawangan().getDaerah() != null) {
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
        }

        if ((permohonan.getSenaraiKertas().isEmpty()) || (kertasP == null)) {
            if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                tajuk = "Kertas untuk pertimbangan Pengarah Tanah dan Galian Negeri Sembilan Darul Khusus bagi " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
            } else if (permohonan.getKodUrusan().getKod().equals("TSPTD") || permohonan.getKodUrusan().getKod().equals("TSPTG") || permohonan.getKodUrusan().getKod().equals("TSMMK")) {
                tajuk = "Permohonan daripada " + WordUtils.capitalizeFully(nama) + " untuk Menukar Syarat Nyata Tanah di " + lokasi + ", daripada " + hakmilik.getSyaratNyata().getSyarat() + " kepada " + hp.getSyaratNyataBaru().getSyarat();
            } else {
                tajuk = "Kertas untuk pertimbangan Pentadbir Tanah " + pejTanah + " bagi " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
            }
            tujuan = "Tujuan kertas ini adalah untuk mendapat pertimbangan Pentadbir Tanah " + pejTanah + " bagi permohonan daripada " + WordUtils.capitalizeFully(nama) + " untuk " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
            latarBelakang1 = "Pentadbir Tanah " + pejTanah + " telah menerima satu permohonan daripada " + WordUtils.capitalizeFully(nama) + " untuk " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";
        }

    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("KPTD");

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
        if (ulasanPTD == null || ulasanPTD.equals("")) {
            ulasanPTD = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }


        listUlasan.add(tujuan);
        listUlasan.add(ulasanPTD);
        listUlasan.add(tajuk);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
        listBill.add(new Integer(7));
        listBill.add(new Integer(8));



        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(ulasanPTD);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(tajuk);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {


            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bill = (Integer) listBill.get(i);

                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                permohonanKertas.setKodDokumen(kd);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bill);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setSubtajuk(subtajuk);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }


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
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new java.util.Date());
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
        LOG.info("-----------count 3------------:" + kira3);
        for (int i = 1; i <= kira3; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new java.util.Date());
                iaP.setDimasukOleh(pengguna);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("butir" + i);
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
        LOG.info("-----------count 5------------:" + kira5);
        for (int i = 1; i <= kira5; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan5.size() != 0 && i <= senaraiKandungan5.size()) {
                Long id = senaraiKandungan5.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new java.util.Date());
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
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new java.util.Date());
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


        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        rowCount2 = senaraiKandungan2.size();

        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
        rowCount3 = senaraiKandungan3.size();

        senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan5 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        rowCount5 = senaraiKandungan5.size();

        senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan6 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        rowCount6 = senaraiKandungan6.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("pembangunan/pecahBahagian/dev_Kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");

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
        return new RedirectResolution(KertasPertimbanganPTDActionBean.class, "locate");
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
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

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public String getButir1() {
        return butir1;
    }

    public void setButir1(String butir1) {
        this.butir1 = butir1;
    }
}

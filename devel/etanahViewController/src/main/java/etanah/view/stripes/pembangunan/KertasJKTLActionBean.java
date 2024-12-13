/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.BPelService;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/kertasJKTL")
public class KertasJKTLActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasJKTLActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PembangunanService pBangunService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PembangunanService devService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kandunganK;
    private List<PermohonanKertas> senaraiKertas;
    private String tajuk;
    private String latarBelakang;
    private String tujuan;
    private String kanunTanah;
    private boolean btn = true;
    private String syorPentadbir1;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private Pengguna peng;
    private String nama;
    private String pejTanah;
    private FasaPermohonan fasaPermohonan;
    private String kmno;
    private List<PermohonanRujukanLuar> listUlasan2;
    private String taskId;
    private Task task = null;
    private BPelService service;
    private String stageId;
    private KodDokumen kd;
    private String huraianPentadbir1;
    private String huraianPtg1;
    private String syorPtg1;
    private List<PermohonanKertasKandungan> senaraiKandungan5;
    private List<PermohonanKertasKandungan> senaraiKandungan6;
    private List<PermohonanKertasKandungan> senaraiKandungan7;
    private List<PermohonanKertasKandungan> senaraiKandungan8;
    private int rowCount5;
    private int rowCount6;
    private int rowCount7;
    private int rowCount8;
    private BPelService serviceBPel;
    private PermohonanPlotPelan mpp;

    @DefaultHandler
    public Resolution showForm() {

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (kandunganK != null) {
            btn = false;
        }
        return new JSP("pembangunan/pecahSempadan/dev_kertas_JKTL_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (!(pengguna.getKodCawangan().getKod().equals("00"))) {
            LOG.info("-----------showForm2------------");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        return new JSP("pembangunan/pecahSempadan/dev_kertas_JKTL_full.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        //if (!(pengguna.getKodCawangan().getKod().equals("00"))) {
        //LOG.info("-----------showForm3------------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        //}
        if ((pengguna.getKodCawangan().getKod().equals("00"))) {
            //LOG.info("-----------showForm3------------");
            getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
        }
        return new JSP("pembangunan/pecahSempadan/dev_kertas_JKTL_full.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        rowCount5 = 1;
        rowCount6 = 1;
        rowCount7 = 1;
        rowCount8 = 1;
        service = new BPelService();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);



        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();


        fasaPermohonan = devService.findFasaPermohonanByIdAliran(idPermohonan, "rekodkpsnjktl");

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        String[] tname = {"permohonan"};
//        Object[] model = {permohonan};

        List<Pemohon> listPemohon = new ArrayList<Pemohon>();
//        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        listPemohon = permohonan.getSenaraiPemohon();

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        LOG.debug("--Size : " + listPemohon.size());
        for (int j = 0; j < listPemohon.size(); j++) {
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            if (j == 0) {
                nama = pm.getPihak().getNama();
                LOG.debug("--nama : " + nama);
            }
            if (j > 0) {
                if (j <= ((listPemohon.size() + 2) - 4)) {
                    nama = nama + ", " + pm.getPihak().getNama() + ", ";
                } else if (j == (listPemohon.size() - 1)) {
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
            LOG.debug("--j : " + j);
        }
        LOG.debug("--nama 1 : " + nama);

        listUlasan2 = pBangunService.findUlasanJabatanTek(idPermohonan);

        PermohonanPlotPelan mpp = new PermohonanPlotPelan();
        if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
            mpp = (PermohonanPlotPelan) devService.findluasPlotPelanByIdMohon(permohonan.getIdPermohonan());
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                } else if (permohonan.getKodUrusan().getKod().equals("PYTN")) {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                } else {
                    lokasi = hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                }
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun() + ", ";
                    } else if (permohonan.getKodUrusan().getKod().equals("PYTN")) {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    } else {
                        lokasi = lokasi + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun() + ", ";
                    }

                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    if (permohonan.getKodUrusan().getKod().equals("PSBT") || permohonan.getKodUrusan().getKod().equals("PSMT")) {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + mpp.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                    } else {
                        lokasi = lokasi + " dan " + hakmilik.getKodHakmilik().getKod() + " " + hakmilik.getNoHakmilik().replaceAll("^0+", "") + ", " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot().replaceAll("^0+", "") + " " + WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + " di bawah seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                    }
                }
            }
        }

        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            kertasP = pBangunService.findKertasByKod(idPermohonan, "JKTL");

            if (kertasP != null) {
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                }
                kmno = kertasP.getTajuk();
                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kandunganK = kertasP.getSenaraiKandungan().get(j);
                    if (kandunganK.getBil() == 1) {
                        tujuan = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 2) {
                        latarBelakang = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 5 && kandunganK.getSubtajuk().equals("5.1")) {
                        huraianPentadbir1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 6 && kandunganK.getSubtajuk().equals("6.1")) {
                        syorPentadbir1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 7 && kandunganK.getSubtajuk().equals("7.1")) {
                        huraianPtg1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 8 && kandunganK.getSubtajuk().equals("8.1")) {
                        syorPtg1 = kandunganK.getKandungan();
                    } else if (kandunganK.getBil() == 10) {
                        tajuk = kandunganK.getKandungan();
                    }
                }


                senaraiKandungan5 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan6 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan7 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan8 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan5 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 5);
                senaraiKandungan6 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                senaraiKandungan7 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 7);
                senaraiKandungan8 = pBangunService.findKertasKandByIdKertas(kertasP.getIdKertas(), 8);
                rowCount5 = senaraiKandungan5.size();
                rowCount6 = senaraiKandungan6.size();
                rowCount7 = senaraiKandungan7.size();
                rowCount8 = senaraiKandungan8.size();
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
        LOG.debug("--nama 2 : " + nama);
        if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null) {
            if (hakmilikPermohonanList.size() > 5) {
                tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Jawatankuasa Lembaga Tanah Ladang Negeri Sembilan Darul Khusus, terhadap " + permohonan.getKodUrusan().getNama().replaceAll("-", "") + " seksyen " + permohonan.getKodUrusan().getRujukanKanun() + hakmilikPermohonanList.size() + " hamilik seperti di jadual butir-butir tanah.";
                tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + " bagi " + hakmilikPermohonanList.size() + " hakmilik seperti di jadual Butir-butir Tanah.";
            } else {
                tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Jawatankuasa Lembaga Tanah Ladang Negeri Sembilan Darul Khusus, terhadap " + permohonan.getKodUrusan().getNama().replaceAll("-", "") + " di " + lokasi + ".";
                tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " daripada " + UpperCaseWords(nama) + " di " + lokasi + ".";
                //Di Bawah Seksyen "+permohonan.getKodUrusan().getRujukanKanun();
            }
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
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        kd = kodDokumenDAO.findById("JKTL");

        if (kandunganK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kandunganK.getKertas().getIdKertas());
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

        LOG.info("TAJUK--------------------: " + tajuk);

        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (latarBelakang == null || latarBelakang.equals("")) {
            latarBelakang = "TIADA DATA.";
        }
        if (kmno == null || kmno.equals("")) {
            kmno = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(tajuk);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        listBill.add(new Integer(1));
        listBill.add(new Integer(2));
        listBill.add(new Integer(10));

        if (kandunganK != null) {

            if (!kandunganK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKand = new PermohonanKertasKandungan();
                    kertasKand = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKand.getBil() == 1) {
                        kertasKand.setKandungan(tujuan);
                    } else if (kertasKand.getBil() == 2) {
                        kertasKand.setKandungan(latarBelakang);
                    } else if (kertasKand.getBil() == 10) {
                        kertasKand.setKandungan(tajuk);
                    }
                    permohonanKertas.setTajuk(kmno);
                    try {
                        if (tarikhMesyuarat != null) {
                            permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    kertasKand.setInfoAudit(infoAudit);
                    pBangunService.simpanPermohonanKertas(permohonanKertas);
                    pBangunService.simpanPermohonanKertasKandungan(kertasKand);
                }

            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                String jabatan = (String) listSubtajuk.get(i);
                Integer bill = (Integer) listBill.get(i);
                PermohonanKertasKandungan kandunganKertas = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk(kmno);
                try {
                    LOG.info("----------tarikhMesyuarat----------:" + tarikhMesyuarat);
                    if (tarikhMesyuarat != null) {
                        permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                permohonanKertas.setKodDokumen(kd);
                kandunganKertas.setKertas(permohonanKertas);
                kandunganKertas.setBil(bill);
                kandunganKertas.setInfoAudit(infoAudit);
                kandunganKertas.setKandungan(ulasan);
                kandunganKertas.setSubtajuk(jabatan);
                kandunganKertas.setCawangan(pengguna.getKodCawangan());
                pBangunService.simpanPermohonanKertas(permohonanKertas);
                pBangunService.simpanPermohonanKertasKandungan(kandunganKertas);
            }
        }

        senaraiKandungan5 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 5);
        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        LOG.info("-----------count----kira5--------:" + kira5);
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
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }


        senaraiKandungan6 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        LOG.info("-----------count----kira6--------:" + kira6);
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
            pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan7 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 7);
            int kira7 = Integer.parseInt(getContext().getRequest().getParameter("rowCount7"));
            LOG.info("-----------count------kira7------:" + kira7);
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
                permohonanKertasKandungan1.setBil(7);
                String kandungan = getContext().getRequest().getParameter("huraianPtg" + i);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("7." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        if (pengguna.getKodCawangan().getKod().equals("00")) {
            senaraiKandungan8 = pBangunService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 8);
            int kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));
            LOG.info("-----------count------kira8------:" + kira8);
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
                permohonanKertasKandungan1.setBil(8);
                String kandungan = getContext().getRequest().getParameter("syorPtg" + i);
                if (kandungan == null || kandungan.equals("")) {
                    kandungan = "TIADA DATA.";
                }
                permohonanKertasKandungan1.setKandungan(kandungan);
                permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan1.setSubtajuk("8." + i);
                permohonanKertasKandungan1.setInfoAudit(iaP);
                pBangunService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        /*
         if (!(pengguna.getKodCawangan().getKod().equals("00"))) {
         LOG.info("-----------edit------------");
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         }*/
        //stageId="sediakertasringkas";
        BpelServices(stageId, pengguna);
        if (stageId.equals("sediakertasringkas")) {
            LOG.debug("sediakertasringkas");
            return new RedirectResolution(KertasJKTLActionBean.class, "showForm");
        } else {
            LOG.debug("not eq sediakertasringkas");
            return new RedirectResolution(KertasJKTLActionBean.class, "showForm3");
        }

    }

    public String BpelServices(String taskId, Pengguna pengguna) {
        serviceBPel = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBPel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
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
            pBangunService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasJKTLActionBean.class, "locate");
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

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public List<PermohonanKertas> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertas> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    /**
     * @return the tujuan
     */
    public String getTujuan() {
        return tujuan;
    }

    /**
     * @param tujuan the tujuan to set
     */
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKanunTanah() {
        return kanunTanah;
    }

    public void setKanunTanah(String kanunTanah) {
        this.kanunTanah = kanunTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
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

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public List<PermohonanRujukanLuar> getListUlasan2() {
        return listUlasan2;
    }

    public void setListUlasan2(List<PermohonanRujukanLuar> listUlasan2) {
        this.listUlasan2 = listUlasan2;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public int getRowCount5() {
        return rowCount5;
    }

    public void setRowCount5(int rowCount5) {
        this.rowCount5 = rowCount5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan5() {
        return senaraiKandungan5;
    }

    public void setSenaraiKandungan5(List<PermohonanKertasKandungan> senaraiKandungan5) {
        this.senaraiKandungan5 = senaraiKandungan5;
    }

    public String getHuraianPentadbir1() {
        return huraianPentadbir1;
    }

    public void setHuraianPentadbir1(String huraianPentadbir1) {
        this.huraianPentadbir1 = huraianPentadbir1;
    }

    public String getHuraianPtg1() {
        return huraianPtg1;
    }

    public void setHuraianPtg1(String huraianPtg1) {
        this.huraianPtg1 = huraianPtg1;
    }

    public int getRowCount7() {
        return rowCount7;
    }

    public void setRowCount7(int rowCount7) {
        this.rowCount7 = rowCount7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan7() {
        return senaraiKandungan7;
    }

    public void setSenaraiKandungan7(List<PermohonanKertasKandungan> senaraiKandungan7) {
        this.senaraiKandungan7 = senaraiKandungan7;
    }

    public int getRowCount8() {
        return rowCount8;
    }

    public void setRowCount8(int rowCount8) {
        this.rowCount8 = rowCount8;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan8() {
        return senaraiKandungan8;
    }

    public void setSenaraiKandungan8(List<PermohonanKertasKandungan> senaraiKandungan8) {
        this.senaraiKandungan8 = senaraiKandungan8;
    }

    public String getSyorPtg1() {
        return syorPtg1;
    }

    public void setSyorPtg1(String syorPtg1) {
        this.syorPtg1 = syorPtg1;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan6() {
        return senaraiKandungan6;
    }

    public void setSenaraiKandungan6(List<PermohonanKertasKandungan> senaraiKandungan6) {
        this.senaraiKandungan6 = senaraiKandungan6;
    }

    public int getRowCount6() {
        return rowCount6;
    }

    public void setRowCount6(int rowCount6) {
        this.rowCount6 = rowCount6;
    }

    public String getSyorPentadbir1() {
        return syorPentadbir1;
    }

    public void setSyorPentadbir1(String syorPentadbir1) {
        this.syorPentadbir1 = syorPentadbir1;
    }

    public String UpperCaseWords(String line) {
        line = line.trim().toLowerCase();
        String data[] = line.split("\\s");
        line = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i].length() > 1) {
                line = line + data[i].substring(0, 1).toUpperCase() + data[i].substring(1) + " ";
            } else {
                line = line + data[i].toUpperCase();
            }
        }
        return line.trim();
    }
}

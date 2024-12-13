/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.model.KodSyaratNyata;
import etanah.model.KodSekatanKepentingan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Notis;
import etanah.model.PermohonanAsal;

@UrlBinding("/pembangunan/kertas_mmk_tangguh")
public class KertasMMKTangguhActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasMMKTangguhActionBean.class);
    @Inject
    BPelService service;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private HakmilikPermohonan hp;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //add by wani
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn;
    private String lokasi;
    private String kmno;
    private String pejTanah;
    private String nama;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String taskId;
    private Task task = null;
    private Pengguna pengguna;
    private KodDokumen kd;
    private String kodHakmilikSementara;
    private String kodHakmilikTetap;
    private String kategoriTanahBaru;
    private String jenisPenggunaanTanah;
    private String kodSyaratNyataBaru;
    private String kodSekatanKepentinganBaru;
    private String idPermohonan;
    private List<Integer> tempohTamat;
    private boolean check = true;
    private String tajuk;
    private String tujuan;
    private String keputusan;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private int rowCount2;
    private int rowCount3;
    private PermohonanKertas permohonanKertas;
    private String noRujukan;
    private Date tarikhSidang;
    //private String latarBelakang1;
    @Inject
    PembangunanService pembangunanServ;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("-----------showForm---------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/common/kertasMMK_tangguh.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("-----------showForm2---------");
        return new JSP("pembangunan/common/kertasMMK_tangguh.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        HakmilikPermohonan hakp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        hp = permohonan.getSenaraiHakmilik().get(0);

        listPemohon = permohonan.getSenaraiPemohon();
        permohonanKertas = pembangunanServ.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "MMKTG");

        //wani
        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        tempohTamat = new ArrayList<Integer>();
        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
            hakp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hakp.getHakmilik().getIdHakmilik());

            if (w == 0) {
                lokasi = hakmilik.getNoHakmilik() + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();
            }

            if (w > 0) {
                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                    lokasi = lokasi + ", " + hakmilik.getNoHakmilik() + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun() + ", ";
                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                    lokasi = lokasi + " dan " + hakmilik.getNoHakmilik() + " " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", Seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", Di Bawah Seksyen " + permohonan.getKodUrusan().getRujukanKanun();
                }
            }

            // calculate difference between dates
            int yrs = 0;
            if (hakmilik.getTarikhLuput() != null) {
                Date date = hakmilik.getTarikhLuput();
                Calendar cal = new GregorianCalendar(1900 + date.getYear(), date.getMonth(), date.getDate());
                Calendar now = new GregorianCalendar();
                yrs = cal.get(Calendar.YEAR) - now.get(Calendar.YEAR);
                if ((now.get(Calendar.MONTH) > cal.get(Calendar.MONTH))
                        || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) < now
                        .get(Calendar.DAY_OF_MONTH))) {
                    yrs--;
                }
                tempohTamat.add(new Integer(yrs));
            }
        }

        nama = "";
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
        LOG.info("-----------Nama---------:" + nama);

        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            kertasP = devService.findKertasByKod(idPermohonan, "MMKTG");
            PermohonanKertas kertasLama = devService.findKertasByKod(idPermohonan, "MMK");
            if (kertasP != null) {
                kmno = kertasP.getTajuk();
                noRujukan = kertasLama.getNomborRujukan();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                    kertasK = kertasP.getSenaraiKandungan().get(j);
                    if (kertasK.getBil() == 1) {
                        tujuan = kertasK.getKandungan();
                    } /*else if (kertasK.getBil() == 2) {
                     latarBelakang1 = kertasK.getKandungan();
                     }*/ else if (kertasK.getBil() == 4) {
                        keputusan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5) {
                        tajuk = kertasK.getKandungan();
                    }
                }

                senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan2 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 2);
                senaraiKandungan3 = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 3);
                rowCount2 = senaraiKandungan2.size();
                rowCount3 = senaraiKandungan3.size();
            }
        }

        PermohonanKertas kertas = devService.findKertasByKod(idPermohonan, "MMK");


        String noMesyuarat = "";
        String tarikh = "";

        if (kertas != null) {
            noMesyuarat = kertas.getTajuk();
            if (kertas.getTarikhSidang() != null) {
                tarikh = sdf.format(kertas.getTarikhSidang());
            }
            List<PermohonanKertasKandungan> permohonanKertasKandList = new ArrayList<PermohonanKertasKandungan>();
            permohonanKertasKandList = devService.findKertasKandByIdKertas(kertas.getIdKertas(), 11);
            for (PermohonanKertasKandungan pkk : permohonanKertasKandList) {
                tajuk = pkk.getKandungan();
            }

            List<PermohonanKertasKandungan> permohonanKertasKandList1 = new ArrayList<PermohonanKertasKandungan>();
            permohonanKertasKandList1 = devService.findKertasKandByIdKertas(kertas.getIdKertas(), 1);
            for (PermohonanKertasKandungan pkk : permohonanKertasKandList1) {
                tujuan = pkk.getKandungan();
            }
        }



        if (permohonan.getSenaraiKertas().isEmpty() || kertasP == null || kertasK == null) {
            if (hakmilikPermohonanList.size() > 5) {
                //tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " Kanun Tanah Negara 1965 daripada " + nama + " bagi " + hakmilikPermohonanList.size() + " hakmilik seperti di jadual butir-butir hakmilik.";
                //tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan semula Majlis Mesyuarat Negeri Sembilan Darul Khusus, bagi permohonan daripada" + nama + " Kanun Tanah Negara 1965 bagi " + hakmilikPermohonanList.size() + " hakmilik seperti di jadual butir-butir hakmilik.";               
            } else {
                //tajuk = permohonan.getKodUrusan().getNama().replaceAll("-", "") + " Kanun Tanah Negara 1965 daripada " + nama + " di " + lokasi + ".";
                //tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan semula Majlis Mesyuarat Negeri Sembilan Darul Khusus, bagi permohonan daripada " + nama + " Kanun Tanah Negara 1965 di " + lokasi + ".";
            }

            //latarBelakang1 = "Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus Bersidang pada "+tarikh+" melalui KM No "+noMesyuarat+" telah mengambil keputusan menangguhkan permohonan ini untuk siasatan semula.";

            keputusan = "Dibentangkan semula Peringatan Mesyuarat dan Kertas Mesyuarat No." + noMesyuarat + " bertarikh " + tarikh + " seperti "
                    + "dilampirkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.";

            senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
            PermohonanKertasKandungan kertasKand1 = new PermohonanKertasKandungan();
            PermohonanKertasKandungan kertasKand2 = new PermohonanKertasKandungan();
            kertasKand1.setKandungan("");
            kertasKand2.setKandungan("");
            senaraiKandungan2.add(kertasKand1);
            senaraiKandungan3.add(kertasKand2);
            rowCount2 = senaraiKandungan2.size();
            rowCount3 = senaraiKandungan3.size();
            System.out.println("Masuk Null kEtas");
        }
        LOG.info("****** Tajuk *******:" + tajuk);
        LOG.info("****** rowCount2 *******:" + rowCount2);
        LOG.info("****** rowCount3 *******:" + rowCount3);

        if (pengguna.getKodCawangan().getDaerah() != null) {
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
        }
    }

    public Resolution simpan() {
        LOG.info("-------simpan--------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("MMKTG");

        //kmno = permohonanKertas.getTajuk();
        //tarikhSidang = permohonanKertas.getTarikhSidang();

        PermohonanKertas kertasLama = devService.findKertasByKod(idPermohonan, "MMK");
        noRujukan = kertasLama.getNomborRujukan();

        PermohonanKertas permohonanKertas = new PermohonanKertas();
        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            PermohonanKertas kertasP = devService.findPermohonanKertasByIdMohonAndKodDokumen(idPermohonan, "MMK");
            permohonanKertas.setTajuk(kertasP.getTajuk());
            //permohonanKertas.setTarikhSidang(kertasP.getTarikhSidang());
            permohonanKertas.setTarikhSahKeputusan(kertasP.getTarikhSahKeputusan());
        }


        ArrayList listUlasan = new ArrayList();
        ArrayList listBil = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = "TIADA DATA.";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = "TIADA DATA.";
        }
        /*
         if (kmno == null || kmno.equals("")) {
         kmno = " ";
         }*/

        listUlasan.add(tujuan);
        listUlasan.add(keputusan);
        listUlasan.add(tajuk);

        listBil.add(1);
        listBil.add(4);
        listBil.add(5);

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(keputusan);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(tajuk);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("Tiada");
                    permohonanKertas.setNomborRujukan(noRujukan);
                    /*
                     try {
                     if (tarikhSidang != null && !tarikhSidang.equals("")) {
                     permohonanKertas.setTarikhSidang(tarikhSidang);
                     }
                     } catch (Exception e) {
                     e.printStackTrace();
                     }*/
                    permohonanKertas.setKodDokumen(kd);
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                Integer bil = (Integer) listBil.get(i);
                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("Tiada");
                permohonanKertas.setNomborRujukan(noRujukan);
                /*
                 try {
                 if (tarikhSidang != null && !tarikhSidang.equals("")) {
                 permohonanKertas.setTarikhSidang(tarikhSidang);
                 }
                 } catch (Exception e) {
                 e.printStackTrace();
                 }
                 */
                permohonanKertas.setKodDokumen(kd);
                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(bil);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(ulasan);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }


        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("-----------count----kira2--------:" + kira2);
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
        LOG.info("-----------count----kira3--------:" + kira3);
        for (int i = 1; i <= kira3; i++) {
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
            String kandungan = getContext().getRequest().getParameter("pengarahTanah" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan2 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 2);
        senaraiKandungan3 = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 3);
        rowCount2 = senaraiKandungan2.size();
        rowCount3 = senaraiKandungan3.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/common/kertasMMK_tangguh.jsp").addParameter("tab", "true");
//        return showForm();
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
        return new RedirectResolution(KertasMMKTangguhActionBean.class, "locate");
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

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    //add by wani
    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(String kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public String getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(String kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public String getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setKategoriTanahBaru(String kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public String getJenisPenggunaanTanah() {
        return jenisPenggunaanTanah;
    }

    public void setJenisPenggunaanTanah(String jenisPenggunaanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunaanTanah;
    }

    public String getKodSekatanKepentinganBaru() {
        return kodSekatanKepentinganBaru;
    }

    public void setKodSekatanKepentinganBaru(String kodSekatanKepentinganBaru) {
        this.kodSekatanKepentinganBaru = kodSekatanKepentinganBaru;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Integer> getTempohTamat() {
        return tempohTamat;
    }

    public void setTempohTamat(List<Integer> tempohTamat) {
        this.tempohTamat = tempohTamat;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }
    /*
     public String getLatarBelakang1() {
     return latarBelakang1;
     }

     public void setLatarBelakang1(String latarBelakang1) {
     this.latarBelakang1 = latarBelakang1;
     }*/
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanStrata;
import etanah.dao.PermohonanStrataDAO;
import etanah.service.StrataPtService;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanBangunanDAO;
import etanah.model.PermohonanBangunan;
import etanah.model.BangunanTingkat;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.BangunanPetak;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author zadhirul.farihim
 */
@UrlBinding("/strata/kertas_MMK")
public class KertasMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    private Configuration conf;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertas;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanStrata permohonanStrata;
    private String tajuk;
    private String tujuan1;
    private String latarbelakang1;
    private String latarbelakang2;
    private String asaspermohon1;
    private String asaspermohon2;
    private String asaspermohon3;
    private String ulasapengarah1;
    private String syorpengarah1;
    private String keputusan1;
    private String subAsasPermohon1;
    private String subAsasPermohon2;
    private String subAsasPermohon3;
    private List<PermohonanBangunan> pBangunanL;
    private List<BangunanTingkat> bangunanTingkatList;
    private List<BangunanPetak> bangunanPetakList;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan3;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan4;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan5;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan6;
    private KodDokumen kd = new KodDokumen();
    private String kodNegeri;
    private String tarikhMesyuarat;
    NumberFormat money = new DecimalFormat("##,##,##0.00");
    private static final Logger LOG = Logger.getLogger(KertasMMKActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/kertas_mmk2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution showKemasukanTarikh() {

        return new JSP("strata/kos_rendah/kemasukan_tarikh_MMK.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("--------- rehydrate : idPermohonan---------::" + idPermohonan);
        pemohon = strService.findPemohonPihak(idPermohonan);
        permohonanStrata = strService.findID(idPermohonan);
        mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            permohonanKertas = strService.findKertasByKod(idPermohonan, "MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
        }

        if (permohonanKertas != null) {
            LOG.info("----rehydrate : permohonankertas---::" + permohonanKertas.getIdKertas() + " " + permohonanKertas.getKodDokumen());
            tajuk = permohonanKertas.getTajuk();
            LOG.info("TAJUK: " + tajuk);
            if (permohonanKertas.getTarikhSidang() != null) {
                tarikhMesyuarat = sdf.format(permohonanKertas.getTarikhSidang());
            }

            if (pemohon != null) {

                String pnama = " ";
                if (pemohon.getPihak() != null) {
                    pnama = pemohon.getPihak().getNama();

                }
                String lot = " ";
                if (mohonHakmilik.getHakmilik().getLot() != null) {
                    lot = mohonHakmilik.getHakmilik().getLot().getNama();

                }
                String nlot = " ";
                if (mohonHakmilik.getHakmilik().getNoLot() != null) {
                    nlot = mohonHakmilik.getHakmilik().getNoLot().replaceAll("^0*", "");
                    LOG.info("nlot" + nlot);
                }
                String kbpm = " ";
                if (mohonHakmilik.getHakmilik().getBandarPekanMukim() != null) {
                    kbpm = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
                }
                String daerah = " ";
                if (mohonHakmilik.getHakmilik().getDaerah() != null) {
                    daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
                }
                String date = " ";
                if (permohonan.getInfoAudit().getTarikhMasuk() != null) {
                    date = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
                }
                String katbngn = " ";
                if (permohonanStrata.getLaporanKategoriBangunani() != null) {
                    katbngn = permohonanStrata.getLaporanKategoriBangunani();
                }
                String cfdate = " ";
                if (permohonanStrata.getCfTarikhKeluar() != null) {
                    cfdate = sdf.format(permohonanStrata.getCfTarikhKeluar());
                }
                List<PermohonanBangunan> senaraiMhnBngn = strService.findByIDPermohonan(idPermohonan);

                pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
                if (senaraiMhnBngn != null || !senaraiMhnBngn.isEmpty()) {
                }
                int bngnTotal = 0;
                bngnTotal = pBangunanL.size();
                int tingkatTotal = 0;
                int petakTotal = 0;
                for (PermohonanBangunan pb : pBangunanL) {
                    tingkatTotal += pb.getBilanganTingkat();
                }
                bngnTotal = permohonanStrata.getLaporanBilBangunan();
                petakTotal = permohonanStrata.getLaporanBilPetak();



                senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);
                if (senaraiLaporanKandungan1.isEmpty() || senaraiLaporanKandungan1 == null) {
                    tujuan1 = "Tujuan kertas mesyuarat ini adalah untuk mendapat kelulusan Majlis Mesyuarat Kerajaan supaya Bangunan" + " "
                            + permohonanStrata.getLaporanKategoriBangunani() + " atas " + mohonHakmilik.getHakmilik().getLot().getNama() + " "
                            + nlot + " " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " " + "Daerah" + " "
                            + mohonHakmilik.getHakmilik().getDaerah().getNama() + " "
                            + "dikelaskan sebagai Bangunan Kos Rendah di bawah Seksyen 10B(2) Akta Hakmilik Strata 1985(Pindaan 2007 A 1290).";
                }
                senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);
                if (senaraiLaporanKandungan2.isEmpty() || senaraiLaporanKandungan2 == null) {
                    latarbelakang1 = pnama + " " + " (Pemaju) sebagai pemegang surat kuasa wakil pemilik-pemilik "
                            + lot + " " + nlot + " " + kbpm + " " + "Daerah" + " " + daerah + " " + "telah mengemukakan permohonan pada "
                            + date + " " + "di mana bangunan yang didirikan adalah Bangunan Kos Rendah dan mengikut Seksyen 10B(1) "
                            + "Akta Hakmilik Strata 1985 (Pindaan 2007 A 1290) supaya Bangunan "
                            + permohonanStrata.getLaporanKategoriBangunani() + " " + "yang telah dibina di atas" + " " + lot + " "
                            + nlot + " " + "dikelaskan sebagai Bangunan Kos Rendah.";

                    latarbelakang2 = "Bangunan ini merupakan" + " " + katbngn + " " + bngnTotal + " blok " + tingkatTotal + " tingkat yang mengandungi "
                            + petakTotal + " petak. " + "Bangunan ini telah memperolehi Sijil Perakuan Kelayakan Menduduki yang diluluskan pada "
                            + cfdate + "." + " Kesemua petak-petak tersebut telah dijual dan didiami.";
                }
                senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
                if (senaraiLaporanKandungan3.isEmpty() || senaraiLaporanKandungan3 == null) {

                    asaspermohon1 = "Peruntukan Seksyen 10B(2) Akta Hakmilik Strata 1985 (Pindaan 2007 A 1290) mengkehendaki bangunan tersebut "
                            + "dikelaskan sebagai Bangunan Kos Rendah terlebih dahulu bagi membolehkan pemilik bangunan mengemukakan permohonan "
                            + "pecah bahagi bangunan berbilang tingkat ini.";

//            asaspermohon2 = "PERKARA-PERKARA UTAMA YANG PERLU DIURUSKAN BAGI BANGUNAN KOS RENDAH ADALAH SEPERTI BERIKUT:-";
                    asaspermohon2 = "Perkara-perkara utama yang perlu diuruskan bagi Bangunan Kos Rendah adalah seperti berikut :-" + "\n\n"
                            + "\t" + "3.2.1: Mengawal, mengurus, mentadbir dan menyelenggara harta bersama supaya berada dalam keadaan yang baik;" + "\n\n"
                            + "\t" + "3.2.2: Membayar cukai tanah, membeli polisi insuran kebakaran dan membaik pulih jika berlaku kerosakan akibatnya; dan " + "\n\n"
                            + "\t" + "3.2.3: Membolehkan Kumpulan Wang Pengurusan ditubuhkan, membeli, menyewa atau memperolehi harta alih sebagai "
                            + "harta bersama dan lain-lain yang menjadi kepentingan bangunan atau pengguna.";


                    asaspermohon3 = "Oleh kerana pemilik-pemilik petak terdiri daripada mereka yang berpendapatan rendah, maka adalah sukar bagi mereka "
                            + "mentadbir dan menguruskan bangunan tersebut di peringkat awal dengan sempurna.";

                }
                senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
                if (senaraiLaporanKandungan4.isEmpty() || senaraiLaporanKandungan4 == null) {
                    ulasapengarah1 = "Pengarah Tanah dan Galian berpendapat permohonan ini wajar diluluskan berdasarkan kepada asas-asas berikut :-" + "\n\n"
                            + "\t" + "4.1.1: Harga jualan petak adalah RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "seunit, iaitu harga yang dikelaskan sebagai Bangunan Kos Rendah;" + "\n\n"
                            + "\t" + "4.1.2: Pembeli-pembeli adalah terdiri daripada mereka yang berpendapatan rendah;" + "\n\n"
                            + "\t" + "4.1.3: Pengkelasan jenis bangunan ini akan membantu pembeli dari segi aspek pengurusan bangunan."
                            + " Ini adalah kerana sebelum Perbadanan Pengurusan ditubuhkan, Pengurusan Bangunan akan ditanggung oleh pemaju dan pembeli (Badan Pengurusan Bersama).";

                }
                senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
                if (senaraiLaporanKandungan5.isEmpty() || senaraiLaporanKandungan5 == null) {

                    syorpengarah1 = "Pengarah Tanah dan Galian mengesyorkan supaya DILULUSKAN bangunan " + " " + katbngn
                            + " atas " + lot + " "
                            + nlot + " " + kbpm + " daerah" + " "
                            + daerah + " "
                            + " dikelaskan sebagai Bangunan Kos Rendah di bawah Seksyen 10B(2) akta Hakmilik Strata 1985 (Pindaan 2007 A 1290)";
                }
                senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
                if (senaraiLaporanKandungan6.isEmpty() || senaraiLaporanKandungan6 == null) {

                    if (kodNegeri.equals("04")) {
                        LOG.info("===========keputusan melaka.=========");
                        keputusan1 = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka.";
                    } else {
                        LOG.info("===========keputusan negeri sembilan.========");
                        keputusan1 = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.";
                    }
                }
            }

        } else {
            LOG.info("ELSE : permohonanKertas is null");

            if (pemohon != null) {

                String nLot = mohonHakmilik.getHakmilik().getNoLot().replaceAll("^0*", "");
                if ((mohonHakmilik != null) && (permohonanStrata != null)) {
                    tajuk = "PERMOHONAN SUPAYA BANGUNAN " + permohonanStrata.getLaporanKategoriBangunani().toUpperCase() + " " + "ATAS" + " " + mohonHakmilik.getHakmilik().getLot().getNama().toUpperCase()
                            + " " + nLot + " "
                            + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama().toUpperCase() + " DAERAH " + mohonHakmilik.getHakmilik().getDaerah().getNama().toUpperCase()
                            + " DIKELASKAN SEBAGAI BANGUNAN KOS RENDAH DI BAWAH SEKSYEN 10B(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290)";

                    tujuan1 = "Tujuan kertas mesyuarat ini adalah untuk mendapat kelulusan Majlis Mesyuarat Kerajaan supaya Bangunan" + " "
                            + permohonanStrata.getLaporanKategoriBangunani() + " atas " + mohonHakmilik.getHakmilik().getLot().getNama() + " "
                            + nLot + " " + mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama() + " " + "Daerah" + " "
                            + mohonHakmilik.getHakmilik().getDaerah().getNama() + " "
                            + "dikelaskan sebagai Bangunan Kos Rendah di bawah Seksyen 10B(2) Akta Hakmilik Strata 1985(Pindaan 2007 A 1290).";


                }

                String pnama = " ";
                if (pemohon.getPihak() != null) {
                    pnama = pemohon.getPihak().getNama();

                }
                String lot = " ";
                if (mohonHakmilik.getHakmilik().getLot() != null) {
                    lot = mohonHakmilik.getHakmilik().getLot().getNama();

                }
                String nlot = " ";
                if (mohonHakmilik.getHakmilik().getNoLot() != null) {
                    nlot = mohonHakmilik.getHakmilik().getNoLot().replaceAll("^0*", "");
                }
                String kbpm = " ";
                if (mohonHakmilik.getHakmilik().getBandarPekanMukim() != null) {
                    kbpm = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
                }
                String daerah = " ";
                if (mohonHakmilik.getHakmilik().getDaerah() != null) {
                    daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
                }
                String date = " ";
                if (permohonan.getInfoAudit().getTarikhMasuk() != null) {
                    date = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
                }

                latarbelakang1 = pnama + " " + " (Pemaju) sebagai pemegang surat kuasa wakil pemilik-pemilik "
                        + lot + " " + nlot + " " + kbpm + " " + "Daerah" + " " + daerah + " " + "telah mengemukakan permohonan pada "
                        + date + " " + "di mana bangunan yang didirikan adalah Bangunan Kos Rendah dan mengikut Seksyen 10B(1) "
                        + "Akta Hakmilik Strata 1985 (Pindaan 2007 A 1290) supaya Bangunan "
                        + permohonanStrata.getLaporanKategoriBangunani() + " " + "yang telah dibina di atas" + " " + lot + " "
                        + nlot + " " + "dikelaskan sebagai Bangunan Kos Rendah.";

                String katbngn = " ";
                if (permohonanStrata.getLaporanKategoriBangunani() != null) {
                    katbngn = permohonanStrata.getLaporanKategoriBangunani();
                }
                String cfdate = " ";
                if (permohonanStrata.getCfTarikhKeluar() != null) {
                    cfdate = sdf.format(permohonanStrata.getCfTarikhKeluar());
                }

                syorpengarah1 = "Pengarah Tanah dan Galian mengesyorkan supaya DILULUSKAN bangunan " + " " + katbngn
                        + " atas " + lot + " "
                        + nlot + " " + kbpm + " daerah" + " "
                        + daerah + " "
                        + "dikelaskan sebagai Bangunan Kos Rendah di bawah Seksyen 10B(2) akta Hakmilik Strata 1985 (Pindaan 2007 A 1290)";

                int bngnTotal = 0;
                pBangunanL = strService.getSenaraiMohonBangunan(idPermohonan);
                bngnTotal = pBangunanL.size();
                int tingkatTotal = 0;
                int petakTotal = 0;
                for (PermohonanBangunan pb : pBangunanL) {
                    tingkatTotal += pb.getBilanganTingkat();
                }
                bngnTotal = permohonanStrata.getLaporanBilBangunan();
                petakTotal = permohonanStrata.getLaporanBilPetak();

                latarbelakang2 = "Bangunan ini merupakan" + " " + katbngn + " " + bngnTotal + " blok " + tingkatTotal + " tingkat yang mengandungi "
                        + petakTotal + " petak. " + "Bangunan ini telah memperolehi Sijil Perakuan Kelayakan Menduduki yang diluluskan pada "
                        + cfdate + "." + " Kesemua petak-petak tersebut telah dijual dan didiami.";



            }

            asaspermohon1 = "Peruntukan Seksyen 10B(2) Akta Hakmilik Strata 1985 (Pindaan 2007 A 1290) mengkehendaki bangunan tersebut "
                    + "dikelaskan sebagai Bangunan Kos Rendah terlebih dahulu bagi membolehkan pemilik bangunan mengemukakan permohonan "
                    + "pecah bahagi bangunan berbilang tingkat ini.";

//            asaspermohon2 = "PERKARA-PERKARA UTAMA YANG PERLU DIURUSKAN BAGI BANGUNAN KOS RENDAH ADALAH SEPERTI BERIKUT:-";
            asaspermohon2 = "Perkara-perkara utama yang perlu diuruskan bagi Bangunan Kos Rendah adalah seperti berikut :-" + "\n\n"
                    + "\t" + "3.2.1: Mengawal, mengurus, mentadbir dan menyelenggara harta bersama supaya berada dalam keadaan yang baik;" + "\n\n"
                    + "\t" + "3.2.2: Membayar cukai tanah, membeli polisi insuran kebakaran dan membaik pulih jika berlaku kerosakan akibatnya; dan " + "\n\n"
                    + "\t" + "3.2.3: Membolehkan Kumpulan Wang Pengurusan ditubuhkan, membeli, menyewa atau memperolehi harta alih sebagai "
                    + "harta bersama dan lain-lain yang menjadi kepentingan bangunan atau pengguna.";

            asaspermohon3 = "Oleh kerana pemilik-pemilik petak terdiri daripada mereka yang berpendapatan rendah, maka adalah sukar bagi mereka "
                    + "mentadbir dan menguruskan bangunan tersebut di peringkat awal dengan sempurna.";

            if (permohonanStrata != null && permohonanStrata.getHargaPetak() != null) {
                ulasapengarah1 = "Pengarah Tanah dan Galian berpendapat permohonan ini wajar diluluskan berdasarkan kepada asas-asas berikut :-" + "\n\n"
                        + "\t" + "4.1.1: Harga jualan petak adalah RM " + money.format(permohonanStrata.getHargaPetak()) + " " + "seunit, iaitu harga yang dikelaskan sebagai Bangunan Kos Rendah;" + "\n\n"
                        + "\t" + "4.1.2: Pembeli-pembeli adalah terdiri daripada mereka yang berpendapatan rendah;" + "\n\n"
                        + "\t" + "4.1.3: Pengkelasan jenis bangunan ini akan membantu pembeli dari segi aspek pengurusan bangunan."
                        + " Ini adalah kerana sebelum Perbadanan Pengurusan ditubuhkan, Pengurusan Bangunan akan ditanggung oleh pemaju dan pembeli (Badan Pengurusan Bersama).";
            }


            if (kodNegeri.equals("04")) {
                LOG.info("===========keputusan melaka.=========");
                keputusan1 = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka.";
            } else {
                LOG.info("===========keputusan negeri sembilan.========");
                keputusan1 = "Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.";
            }


            //FIRST TIME : AUTO SAVE
            InfoAudit infoAudit = new InfoAudit();

            if (kodNegeri.equals("04")) {
                LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
                kd = kodDokumenDAO.findById("MMKN");
            } else {
                LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
                kd = kodDokumenDAO.findById("MMK");
            }

            ArrayList<String> listUlasan = new ArrayList();
            ArrayList<String> listSubtajuk = new ArrayList<String>();
            ArrayList<String> billNo = new ArrayList<String>();

            listUlasan.add(tujuan1);
            listUlasan.add(latarbelakang1);
            listUlasan.add(latarbelakang2);
            listUlasan.add(asaspermohon1);
            listUlasan.add(asaspermohon2);
            listUlasan.add(asaspermohon3);
            listUlasan.add(ulasapengarah1);
            listUlasan.add(syorpengarah1);
            listUlasan.add(keputusan1);

            listSubtajuk.add("1.1");
            listSubtajuk.add("2.1");
            listSubtajuk.add("2.2");
            listSubtajuk.add("3.1");
            listSubtajuk.add("3.2");
            listSubtajuk.add("3.3");
            listSubtajuk.add("4.1");
            listSubtajuk.add("5.1");
            listSubtajuk.add("6.1");

            billNo.add("1");
            billNo.add("2");
            billNo.add("2");
            billNo.add("3");
            billNo.add("3");
            billNo.add("3");
            billNo.add("4");
            billNo.add("5");
            billNo.add("6");
            if (tajuk == null) {
                tajuk = "Tiada Data";
            }

            permohonanKertas = new PermohonanKertas();
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setTajuk(tajuk);
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String billNo1 = billNo.get(i);
                String subTajuk = listSubtajuk.get(i);

                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(Integer.parseInt(billNo1));
                kertasKdgn.setSubtajuk(subTajuk);
                kertasKdgn.setInfoAudit(infoAudit);
                if (ulasan == null) {
                    ulasan = "Tiada Data";
                }
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                kertasKdgn = strService.simpanPermohonanKertasKandungan(kertasKdgn);
                LOG.info("===DEFAULT : AUTO SIMPAN permohonanKertas BERJAYA " + kertasKdgn.getIdKandungan());

            }


        }


    }

    public Resolution simpanTujuan() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan1.isEmpty() && j <= senaraiLaporanKandungan1.size()) {
                LOG.info("IF : senaraiKertasKandungan1 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan1.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan1 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 1);
            String tujuan = getContext().getRequest().getParameter("tujua" + j);
            LOG.info("==TUJUAN :" + tujuan);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Perakuan 1." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("1." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Gagal Disimpan");
        }

        senaraiLaporanKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 1);
        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTujuan() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("read", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanLatar() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();


        kd = kodDokumenDAO.findById("MMK");
        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan2.isEmpty() && j <= senaraiLaporanKandungan2.size()) {
                LOG.info("IF : senaraiKertasKandungan1 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan2.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan1 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 2);
            String tujuan = getContext().getRequest().getParameter("latarblkg" + j);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Latar Belakang 2." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("2." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Latar Belakang Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Latar Belakang Gagal Disimpan");
        }

        senaraiLaporanKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 2);
        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteLatarbelakang() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();

        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanAsasMohon() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan3.isEmpty() && j <= senaraiLaporanKandungan3.size()) {
                LOG.info("IF : senaraiKertasKandungan3 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan3.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan3 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 3);
            String tujuan = getContext().getRequest().getParameter("asaspermohonan" + j);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Asas Permohonan 3." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("3." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Asas Permohonan Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Asas Permohonan Gagal Disimpan");
        }

        senaraiLaporanKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteAsasMohon() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();

        return new JSP("strata/kos_rendah/kertas_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasanPengarah() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan4.isEmpty() && j <= senaraiLaporanKandungan4.size()) {
                LOG.info("IF : senaraiKertasKandungan4 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan4.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan4 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 4);
            String tujuan = getContext().getRequest().getParameter("ulasanpengarah" + j);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Asas Permohonan 4." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("4." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Ulasan Pengarah Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Ulasan Pengarah Gagal Disimpan");
        }

        senaraiLaporanKandungan4 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution deleteUlasanPengarah() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSyorPengarah() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan5.isEmpty() && j <= senaraiLaporanKandungan5.size()) {
                LOG.info("IF : senaraiKertasKandungan5 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan5.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan5 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 5);
            String tujuan = getContext().getRequest().getParameter("syorpngarah" + j);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Asas Permohonan 5." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("5." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Syor Pengarah Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Syor Pengarah Gagal Disimpan");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        senaraiLaporanKandungan5 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSyorPengarah() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusan() {
        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        InfoAudit infoAudit = new InfoAudit();

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            kd = kodDokumenDAO.findById("MMK");
        }

//        PermohonanKertas permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
//        if (permohonanKertas != null) {
//            LOG.info("::-- IF permohonanKertas is not NULL --:");
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//
//
//        } else {
//            LOG.info("::-- ELSE > permohonanKertas is NULL --:");
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            infoAudit.setDimasukOleh(peng);
//            permohonanKertas.setInfoAudit(infoAudit);
//
//
//        }
//        permohonanKertas.setPermohonan(permohonan);
//        permohonanKertas.setCawangan(cawangan);
//        permohonanKertas.setTajuk(tajuk);
//        permohonanKertas.setKodDokumen(kd);
//        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
//        LOG.info("::-- SIMPAN MOHON_KERTAS BERJAYA : id = " + permohonanKertas.getIdKertas());
//
//        ArrayList listUlasan = new ArrayList();
//        ArrayList<String> listSubtajuk = new ArrayList<String>();
//        ArrayList<String> billNo = new ArrayList<String>();

        senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
        PermohonanKertasKandungan permohonanKertasKand;

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        LOG.info("COUNT = " + count);
        PermohonanKertasKandungan pkk = null;
        for (int j = 1; j <= count; j++) {
            if (!senaraiLaporanKandungan6.isEmpty() && j <= senaraiLaporanKandungan6.size()) {
                LOG.info("IF : senaraiKertasKandungan6 not null");
                permohonanKertasKand = (PermohonanKertasKandungan) senaraiLaporanKandungan6.get(j - 1);
                if (permohonanKertasKand != null) {
                    infoAudit = permohonanKertas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            } else {
                LOG.info("ELSE : senaraiKertasKandungan6 is null");
                permohonanKertasKand = new PermohonanKertasKandungan();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertasKand.setInfoAudit(infoAudit);
            }
            permohonanKertasKand.setKertas(permohonanKertas);
            permohonanKertasKand.setBil((int) 6);
            String tujuan = getContext().getRequest().getParameter("kputusan" + j);
            if (StringUtils.isEmpty(tujuan)) {
                addSimpleError("Sila isi maklumat pada Asas Permohonan 6." + j + ".");
                break;
            } else {
                permohonanKertasKand.setKandungan(tujuan);
                permohonanKertasKand.setSubtajuk("6." + j);
                permohonanKertasKand.setInfoAudit(infoAudit);
                permohonanKertasKand.setCawangan(cawangan);
                pkk = strService.simpanPermohonanKertasKandungan(permohonanKertasKand);

            }
        }
        if (pkk != null) {
            addSimpleMessage("Maklumat Keputusan Telah Berjaya Disimpan.");
        } else {
            addSimpleError("Maklumat Keputusan Gagal Disimpan");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        senaraiLaporanKandungan6 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 6);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKeputusan() {
        LOG.info("-----------deleteKandungan---------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("-----------deleteSingle---------" + idKandungan);
        if (idKandungan != null) {
            PermohonanKertasKandungan permohonanKertasKand = new PermohonanKertasKandungan();
            permohonanKertasKand = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            if (permohonanKertasKand != null) {

                try {
                    strService.deleteKertasKandungan(permohonanKertasKand);
                    addSimpleMessage("Maklumat telah berjaya dipadam.");
                } catch (Exception e) {
                    LOG.error(e);
                    addSimpleError("Terdapat masalah teknikal.");
                }
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("display", Boolean.FALSE);
        return new JSP("strata/kos_rendah/kertas_mmk3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTarikhMesyuarat() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        kodNegeri = conf.getProperty("kodNegeri");
        String tajukKertas = "";

        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
            permohonanKertas = strService.findKertasByKod(idPermohonan, "MMKN");
            kd = kodDokumenDAO.findById("MMKN");
        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
            permohonanKertas = strService.findKertasByKod(idPermohonan, "MMK");
            kd = kodDokumenDAO.findById("MMK");
        }

        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            tajukKertas = permohonanKertas.getTajuk();

        } else {
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            permohonanKertas = new PermohonanKertas();

        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk(tajukKertas);
        permohonanKertas.setKodDokumen(kd);
        permohonanKertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
        permohonanKertas = strService.simpanPermohonanKertasObject(permohonanKertas);
        addSimpleMessage("Tarikh telah berjaya disimpan.");

        return new JSP("strata/kos_rendah/kemasukan_tarikh_MMK.jsp").addParameter("tab", "true");
    }

    public static String toProperCase(String name) {
        return name.substring(0, 1).toUpperCase()
                + name.substring(1).toLowerCase();
    }

    public String getAsaspermohon1() {
        return asaspermohon1;
    }

    public void setAsaspermohon1(String asaspermohon1) {
        this.asaspermohon1 = asaspermohon1;
    }

    public String getAsaspermohon2() {
        return asaspermohon2;
    }

    public void setAsaspermohon2(String asaspermohon2) {
        this.asaspermohon2 = asaspermohon2;
    }

    public String getAsaspermohon3() {
        return asaspermohon3;
    }

    public void setAsaspermohon3(String asaspermohon3) {
        this.asaspermohon3 = asaspermohon3;
    }

    public String getKeputusan() {
        return keputusan1;
    }

    public void setKeputusan(String keputusan1) {
        this.keputusan1 = keputusan1;
    }

    public String getLatarbelakang1() {
        return latarbelakang1;
    }

    public void setLatarbelakang1(String latarbelakang1) {
        this.latarbelakang1 = latarbelakang1;
    }

    public String getLatarbelakang2() {
        return latarbelakang2;
    }

    public void setLatarbelakang2(String latarbelakang2) {
        this.latarbelakang2 = latarbelakang2;
    }

    public String getSyorpengarah() {
        return syorpengarah1;
    }

    public void setSyorpengarah(String syorpengarah1) {
        this.syorpengarah1 = syorpengarah1;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTujuan1() {
        return tujuan1;
    }

    public void setTujuan1(String tujuan1) {
        this.tujuan1 = tujuan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan3() {
        return senaraiLaporanKandungan3;
    }

    public void setSenaraiLaporanKandungan3(List<PermohonanKertasKandungan> senaraiLaporanKandungan3) {
        this.senaraiLaporanKandungan3 = senaraiLaporanKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan4() {
        return senaraiLaporanKandungan4;
    }

    public void setSenaraiLaporanKandungan4(List<PermohonanKertasKandungan> senaraiLaporanKandungan4) {
        this.senaraiLaporanKandungan4 = senaraiLaporanKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan5() {
        return senaraiLaporanKandungan5;
    }

    public void setSenaraiLaporanKandungan5(List<PermohonanKertasKandungan> senaraiLaporanKandungan5) {
        this.senaraiLaporanKandungan5 = senaraiLaporanKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan6() {
        return senaraiLaporanKandungan6;
    }

    public void setSenaraiLaporanKandungan6(List<PermohonanKertasKandungan> senaraiLaporanKandungan6) {
        this.senaraiLaporanKandungan6 = senaraiLaporanKandungan6;
    }

    public String getKeputusan1() {
        return keputusan1;
    }

    public void setKeputusan1(String keputusan1) {
        this.keputusan1 = keputusan1;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getSubAsasPermohon1() {
        return subAsasPermohon1;
    }

    public void setSubAsasPermohon1(String subAsasPermohon1) {
        this.subAsasPermohon1 = subAsasPermohon1;
    }

    public String getSubAsasPermohon2() {
        return subAsasPermohon2;
    }

    public void setSubAsasPermohon2(String subAsasPermohon2) {
        this.subAsasPermohon2 = subAsasPermohon2;
    }

    public String getSubAsasPermohon3() {
        return subAsasPermohon3;
    }

    public void setSubAsasPermohon3(String subAsasPermohon3) {
        this.subAsasPermohon3 = subAsasPermohon3;
    }

    public String getSyorpengarah1() {
        return syorpengarah1;
    }

    public void setSyorpengarah1(String syorpengarah1) {
        this.syorpengarah1 = syorpengarah1;
    }

    public String getUlasapengarah1() {
        return ulasapengarah1;
    }

    public void setUlasapengarah1(String ulasapengarah1) {
        this.ulasapengarah1 = ulasapengarah1;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }
}

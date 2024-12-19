/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AnsuranDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Ansuran;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.LanjutanBayaran;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.BPelService;
import etanah.service.PelupusanPtService;
import etanah.service.PembangunanService;
import etanah.view.pengambilan.validator.pelukispelanValidator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrator
 */
@UrlBinding("/pelupusan/surat_rayuanNS")
public class SuratRayuanNSActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pservice;
    @Inject
    PelupusanPtService ppservice;
    @Inject
    StrataPtService sservice;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    AnsuranDAO ansuranDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PembangunanService devServices;
    @Inject
    private etanah.Configuration conf;
    private String idPermohonan;
    private String tajuk;
    private String bay;
    private String kandunganPertama;
    private Long idKertas;
    private KodCawangan cawangan;
    private Permohonan permohonan;
    private KodDokumen kodDok;
    private PermohonanKertasKandungan mohonKertasKand;
    private List<PermohonanKertasKandungan> mohonKertasKandList = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> mohonKertasKandList0 = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> mohonKertasKandTmbhn = new ArrayList<PermohonanKertasKandungan>();
    private PermohonanKertas mohonKertas;
    private String kandungan;
    private String kodNegeri;
    private String stageId;
    private static final String lulus = "SL";
    private static final String tolak = "STP";
    private static final String batal = "SBA";
    private LanjutanBayaran lanjutBayaran;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private List<Ansuran> ansuranList;
    private static Logger logger = Logger.getLogger(SuratRayuanNSActionBean.class);
    private List<Notis> listNotis;
    private List<KandunganFolder> listKandunganFolder;
    private Notis n;
    private String tarikhAkhirBayaran;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        permohonan = permohonanDAO.findById(idPermohonan);
        lanjutBayaran = pservice.findLanjutBayaranByIdMohon(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = pservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM");
            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            ansuranList = ansuranDAO.findByEqualCriterias(tname, tvalue, null);
        }
        BPelService service = new BPelService();
        //stageId = "16SediaSuratLulus";
        //stageId = null;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        } else {
            stageId = "15SedSrtKptsnLulus";
        }

        kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri: " + kodNegeri);
//        stageId = "16SedSrtMklmKptsnLulus";
        if (stageId == null) {
            addSimpleError("Stage Tidak Dikenali Atau Ralat");
        } else {
            if (kodNegeri.equals("04")) { //Melaka
                if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                    if (stageId.equals("04SediaSuratTolak")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                        kodDok = kodDokumenDAO.findById(tolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    } else if (stageId.equals("05SediaSuratLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk melanjutkan tempoh bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri. Tempoh lanjutan yang diluluskan adalah sebanyak " + lanjutBayaran.getTempoh() + " bulan.";
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                    if (stageId.equals("16SediaSuratBorang5A")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk mengurangkan bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri.";
                        bay = "Bayaran premium yang diluluskan adalah sebanyak RM " + permohonan.getNilaiKeputusan();
                    } else if (stageId.equals("14SediaSuratTolak")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                        kodDok = kodDokumenDAO.findById(tolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    }

                } else if (permohonan.getKodUrusan().getKod().equals("RAYA")) {
                    if (stageId.equals("02SediaSuratLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk menjelaskan bayaran premium sebanyak RM " + permohonanTuntutanKos.getAmaunSebenar() + " secara ansuran selama " + ansuranList.size() + " bulan telah diluluskan oleh Pihak Berkuasa Negeri. Berikut disertakan jadual bayaran ansuran yang telah diluluskan :";
                    } else if (stageId.equals("05SediaSuratBatal")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, batal);
                        kodDok = kodDokumenDAO.findById(batal);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    }
                }
            } else if (kodNegeri.equals("05")) { //Negeri Sembilan
                if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                    if (stageId.equals("09SmkSrtKptsnTlk") || stageId.equals("08TrmKptsndanSedSrtTlk")) {
                        String kodTolak = new String();
                        kodTolak = "STT";
                        mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                        kodDok = kodDokumenDAO.findById(kodTolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    } else if (stageId.equals("06SmkSrtKptsnLulus") || stageId.equals("05TrmKptsndanSedSrtLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk melanjutkan tempoh bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri. Tempoh lanjutan yang diluluskan adalah sebanyak " + lanjutBayaran.getTempoh() + " bulan";

                        updateTempohBulan();
                        logger.info("::: tarikh akhir bayaran :" + tarikhAkhirBayaran);
                        if (StringUtils.isNotBlank(tarikhAkhirBayaran)) {

                            kandunganPertama += " iaitu sehingga " + tarikhAkhirBayaran;
                        }

                        logger.info("::: kandunganPertama :" + kandunganPertama);

                    }
                } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                    if (stageId.equals("16SedSrtMklmKptsnLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk mengurangkan bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri";
                        bay = "Bayaran premium yang diluluskan adalah sebanyak RM " + permohonan.getNilaiKeputusan();
                    } else if (stageId.equals("19SedSrtMklmKptsnTlk")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                        kodDok = kodDokumenDAO.findById(tolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    }

                } else if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                    if (stageId.equals("16SedSrtMklmKptsnLulus") || stageId.equals("17SmkSrtKptsnLulus") || stageId.equals("18TdtgnSrtKptsnLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk mengurangkan bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri";
                        bay = "Bayaran premium yang diluluskan adalah sebanyak RM " + permohonan.getNilaiKeputusan();


                        if (permohonan.getPermohonanSebelum() != null) {
                            //update table mohon_tuntut_kos for PBMT
                            List<PermohonanTuntutanKos> listPermohonanTuntutanKos = pservice.findMohonTuntutKosByIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM");
                            if (!listPermohonanTuntutanKos.isEmpty()) {
                                PermohonanTuntutanKos ptk = listPermohonanTuntutanKos.get(0);
                                if (ptk != null) {
                                    infoAudit = ptk.getInfoAudit();
                                    infoAudit.setTarikhKemaskini(new java.util.Date());
                                    infoAudit.setDiKemaskiniOleh(pengguna);
                                    //update value in amaun_tuntut & amaun_sebenar
                                    if (ptk.getAmaunSebenar() == null && permohonan.getNilaiKeputusan() != null) {
                                        logger.info(":::: update table mohon_tuntut_kos for PBMT ::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                                        ptk.setAmaunSebenar(ptk.getAmaunTuntutan());
                                        ptk.setAmaunTuntutan(permohonan.getNilaiKeputusan());
                                        ptk.setInfoAudit(infoAudit);
                                        pservice.simpanPermohonanTuntutanKos(ptk);
                                    }
                                }

                            }

                        }



                    } else if (stageId.equals("19SedSrtMklmKptsnTlk") || stageId.equals("20SmkSrtKptsnTlk") || stageId.equals("21TdtgnSrtKptsnTlk")) {
                        String kodTolak = new String();
                        kodTolak = "STT";
                        mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                        kodDok = kodDokumenDAO.findById(kodTolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    }

                } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
                    if (stageId.equals("15SedSrtKptsnLulus") || stageId.equals("16SmkSrtKptsnLulus") || stageId.equals("17TndtgnSrtKptsnLulus")) {
                        mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                        kodDok = kodDokumenDAO.findById(lulus);
                        kandunganPertama = "Adalah dengan ini dimaklumkan bahawa permohonan tuan untuk melanjutkan tempoh bayaran premium telah diluluskan oleh Pihak Berkuasa Negeri. Tempoh lanjutan yang diluluskan adalah sebanyak " + lanjutBayaran.getTempoh() + " bulan.";
                        updateTempohBulanKuasaPTG();

                        logger.info("::: tarikh akhir bayaran :" + tarikhAkhirBayaran);
                        if (StringUtils.isNotBlank(tarikhAkhirBayaran)) {

                            kandunganPertama += " iaitu sehingga " + tarikhAkhirBayaran;
                        }

                        logger.info("::: kandunganPertama :" + kandunganPertama);
                    } else if (stageId.equals("18SedSrtKptsnTlk") || stageId.equals("19SmkSrtKptsnTlk") || stageId.equals("20TndtgnSrtKptsnTlk")) {
                        String kodTolak = new String();
                        kodTolak = "STT";
                        mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                        kodDok = kodDokumenDAO.findById(kodTolak);
                        kandunganPertama = "Dukacita dimaklumkan bahawa permohonan tuan bagi tujuan di atas telah ditolak oleh Pihak Berkuasa Negeri";
                    }
                }
            }

//        mohonKertas = sservice.findKertas(idPermohonan);
//        mohonKertas = pservice.findKertasByKod(idPermohonan, "SRY");
            cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());

            if (mohonKertas != null) {

                infoAudit = mohonKertas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                mohonKertas = new PermohonanKertas();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }
            mohonKertas.setCawangan(cawangan);
            mohonKertas.setTajuk("Surat Rayuan");
//        KodDokumen kod = kodDokumenDAO.findById("SRY");
            mohonKertas.setKodDokumen(kodDok);
            mohonKertas.setInfoAudit(infoAudit);
            mohonKertas.setPermohonan(permohonan);
            sservice.simpanPermohonanKertas(mohonKertas);

            mohonKertasKandList = sservice.findByIdLapor(mohonKertas.getIdKertas(), 1);
            mohonKertasKandList0 = sservice.findByIdLapor(mohonKertas.getIdKertas(), 0);
            mohonKertasKandTmbhn = ppservice.findByIdLapor(mohonKertas.getIdKertas(), 2);

            if (mohonKertasKandList0.isEmpty()) {
                PermohonanKertasKandungan kertasK0 = new PermohonanKertasKandungan();
                String kodDok2 = new String();
                if (permohonan.getKodUrusan().getKod().equals("RYKN") || permohonan.getKodUrusan().getKod().equals("RAYK")) {
                    kodDok2 = "MMK";
                } else if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                    kodDok2 = "KRPTD";
                } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
                    kodDok2 = "KRPTG";
                }

                tajuk = new String();
                if (!StringUtils.isEmpty(kodDok2)) {
                    PermohonanKertas permohonanKertas = pservice.findKertasByKod(idPermohonan, kodDok2);
                    if (permohonanKertas != null) {
                        PermohonanKertasKandungan permohonanKertasKandungan = pservice.findByBilNIdKertas(0, permohonanKertas.getIdKertas());
                        tajuk = permohonanKertasKandungan.getKandungan();
                    }
                }

                if (StringUtils.isBlank(tajuk)) {
                    tajuk = permohonan.getKodUrusan().getKod();
                }

                kertasK0.setCawangan(cawangan);
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                kertasK0.setInfoAudit(infoAudit);
                kertasK0.setKertas(mohonKertas);
                kertasK0.setBil(0);
                kertasK0.setSubtajuk("1");
                kertasK0.setKandungan(tajuk);
                pservice.simpanPermohonanKertasKandungan(kertasK0);

            } else {
                tajuk = mohonKertasKandList0.get(0).getKandungan();
            }
            if (mohonKertasKandList.isEmpty()) {
                PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();

                kertasK1.setCawangan(cawangan);
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                kertasK1.setInfoAudit(infoAudit);
                kertasK1.setKertas(mohonKertas);
                kertasK1.setBil(1);
                kertasK1.setSubtajuk("2");
                kertasK1.setKandungan(kandunganPertama);
                pservice.simpanPermohonanKertasKandungan(kertasK1);

            } else {
                kandunganPertama = mohonKertasKandList.get(0).getKandungan();
            }
        }
    }

    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    sservice.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();

        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        String kand = getContext().getRequest().getParameter("kandungan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        if (kodNegeri.equals("04")) { //Melaka
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("04SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                    kodDok = kodDokumenDAO.findById(tolak);
                } else if (stageId.equals("05SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SediaSuratBorang5A")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("14SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                    kodDok = kodDokumenDAO.findById(tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYA")) {
                if (stageId.equals("02SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("05SediaSuratBatal")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, batal);
                    kodDok = kodDokumenDAO.findById(batal);
                }
            }
        } else if (kodNegeri.equals("05")) { //Negeri Sembilan
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("08TrmKptsndanSedSrtTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                } else if (stageId.equals("05TrmKptsndanSedSrtLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                if (stageId.equals("16SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("20SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                    kodDok = kodDokumenDAO.findById(tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SedSrtMklmKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("19SedSrtMklmKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
                if (stageId.equals("15SedSrtKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("18SedSrtKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }
            }
        }
        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        mohonKertas.setTajuk("Surat Rayuan");
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setKodDokumen(kodDok);
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        sservice.simpanPermohonanKertas(mohonKertas);

        long a = mohonKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = sservice.findByIdLapor(a, 2);
        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();

        if (plk.isEmpty()) {
            pLK.setSubtajuk("2");
        } else {
            int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;

            pLK.setSubtajuk(String.valueOf(n));
        }
        pLK.setBil((short) 2);
        pLK.setKandungan(kand);
        pLK.setKertas(mohonKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        sservice.simpanPermohonanKertasKandungan(pLK);
        rehydrate();
        addSimpleMessage("Maklumat Telah Disimpan");
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        logger.info("CHECKING:..... index :" + index + " kand :" + kand + " idKandungan :" + idKand);
        InfoAudit ia = new InfoAudit();
        if (idKand != null) {
            PermohonanKertasKandungan plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {
                ia = plk.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                plk.setInfoAudit(ia);
                plk.setKandungan(kand);
                pservice.simpanPermohonanKertasKandungan(plk);
            }
        }
        addSimpleMessage("Maklumat Berjaya Dikemaskini");
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        pkk = new PermohonanKertasKandungan();
        pkk.setBil((short) 2);
        mohonKertasKandTmbhn.add(pkk);
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniTajuk() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (kodNegeri.equals("04")) { //Melaka
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("04SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                } else if (stageId.equals("05SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SediaSuratBorang5A")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                } else if (stageId.equals("14SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYA")) {
                if (stageId.equals("02SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                } else if (stageId.equals("05SediaSuratBatal")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, batal);
                }
            }
        } else if (kodNegeri.equals("05")) { //Negeri Sembilan
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("08TrmKptsndanSedSrtTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                } else if (stageId.equals("05TrmKptsndanSedSrtLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                if (stageId.equals("16SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("20SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                    kodDok = kodDokumenDAO.findById(tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SedSrtMklmKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("19SedSrtMklmKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
                if (stageId.equals("15SedSrtKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("18SedSrtKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }
            }
        }
//        mohonKertas = pservice.findKertasByKod(idPermohonan, "SRY");
        if (mohonKertas != null) {
            List<PermohonanKertasKandungan> plk0 = sservice.findByIdLapor(mohonKertas.getIdKertas(), 0);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            PermohonanKertasKandungan kertasK0 = plk0.get(0);
            infoAudit = kertasK0.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            kertasK0.setCawangan(cawangan);
            kertasK0.setInfoAudit(infoAudit);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(0);
            kertasK0.setSubtajuk("1");
            kertasK0.setKandungan(tajuk.toUpperCase());
            pservice.simpanPermohonanKertasKandungan(kertasK0);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniKandunganPertama() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (kodNegeri.equals("04")) { //Melaka
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("04SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                } else if (stageId.equals("05SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SediaSuratBorang5A")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                } else if (stageId.equals("14SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYA")) {
                if (stageId.equals("02SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                } else if (stageId.equals("05SediaSuratBatal")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, batal);
                }
            }
        } else if (kodNegeri.equals("05")) { //Negeri Sembilan
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (stageId.equals("08TrmKptsndanSedSrtTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                } else if (stageId.equals("05TrmKptsndanSedSrtLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                }
            } else if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                if (stageId.equals("16SediaSuratLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("20SediaSuratTolak")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, tolak);
                    kodDok = kodDokumenDAO.findById(tolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RAYK")) {
                if (stageId.equals("16SedSrtMklmKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("19SedSrtMklmKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }

            } else if (permohonan.getKodUrusan().getKod().equals("RLPTG")) {
                if (stageId.equals("15SedSrtKptsnLulus")) {
                    mohonKertas = pservice.findKertasByKod(idPermohonan, lulus);
                    kodDok = kodDokumenDAO.findById(lulus);
                } else if (stageId.equals("18SedSrtKptsnTlk")) {
                    String kodTolak = new String();
                    kodTolak = "STT";
                    mohonKertas = pservice.findKertasByKod(idPermohonan, kodTolak);
                    kodDok = kodDokumenDAO.findById(kodTolak);
                }
            }
        }
//        mohonKertas = pservice.findKertasByKod(idPermohonan, "SRY");
        if (mohonKertas != null) {
            List<PermohonanKertasKandungan> plk0 = sservice.findByIdLapor(mohonKertas.getIdKertas(), 1);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            KodCawangan cawangan = new KodCawangan();
            cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
            PermohonanKertasKandungan kertasK0 = plk0.get(0);
            infoAudit = kertasK0.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            kertasK0.setCawangan(cawangan);
            kertasK0.setInfoAudit(infoAudit);
            kertasK0.setKertas(mohonKertas);
            kertasK0.setBil(1);
            kertasK0.setSubtajuk("2");
            kertasK0.setKandungan(kandunganPertama);
            pservice.simpanPermohonanKertasKandungan(kertasK0);
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rayuan/kertas_surat_ns.jsp").addParameter("tab", "true");
    }

    public String updateTempohBulan() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        //update table mohon for PBMT
        Permohonan permohonanPBMT = new Permohonan();
        if (permohonan.getPermohonanSebelum() != null) {
            LanjutanBayaran lb = pservice.findLanjutBayaranByIdMohon(permohonan.getIdPermohonan());
            if (lb != null) {
                permohonanPBMT = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (permohonanPBMT != null) {
                    InfoAudit ia = permohonanPBMT.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());

                    permohonanPBMT.setInfoAudit(ia);
                    permohonanPBMT.setTempohBulan(lb.getTempoh());
                    pservice.simpanPermohonan(permohonanPBMT);
                }


            }

            listKandunganFolder = pservice.getListDokumen5A(permohonanPBMT.getFolderDokumen().getFolderId());
            logger.info("######listKandunganFolder :" + listKandunganFolder.size());

            if (listKandunganFolder.size() != 0) {

                Dokumen dokumen = new Dokumen();

                for (KandunganFolder kk : listKandunganFolder) {
                    dokumen = kk.getDokumen();
                }

                logger.info("Kod Jabatan : " + permohonan.getKodUrusan().getJabatan());

                if (dokumen.getKodDokumen() != null) {

                    if (dokumen.getKodDokumen().getKod().equals("5AA")) {

                        logger.info("###### Kod Dokumen : " + dokumen.getKodDokumen().getKod());

                        //insert table notis
                        listNotis = new ArrayList<Notis>();
                        listNotis = pservice.getListNotis2(permohonanPBMT.getIdPermohonan(), "N5A");

                        if (!listNotis.isEmpty()) {
                            n = listNotis.get(0);
                        }
                        InfoAudit ia = new InfoAudit();
                        if (n != null) {
                            logger.info("::: update notis");
                            ia = n.getInfoAudit();
                            ia.setTarikhKemaskini(new java.util.Date());
                            ia.setDiKemaskiniOleh(pengguna);

                            n.setPermohonan(permohonanPBMT);
                            n.setInfoAudit(ia);
                            n.setTarikhNotis(new java.util.Date());
                            n.setKodNotis(kodNotisDAO.findById("N5A"));
                            n.setDokumenNotis(dokumen);
                            n.setJabatan(permohonan.getKodUrusan().getJabatan());
                            if (n.getTempohBulan() != 0 && lb.getTempoh() != null) {
                                if (StringUtils.isBlank(n.getBilangan())) {
                                    n.setTempohBulan(n.getTempohBulan() + lb.getTempoh());
                                }
                            }
                            n.setBilangan("1");
                            logger.info("::: update tempoh : " + n.getTempohBulan());
                            devServices.saveOrUpdate(n);

                            if (n.getTarikhTerima() != null) {
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(n.getTarikhTerima());
                                cal.add(Calendar.MONTH, n.getTempohBulan());

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                tarikhAkhirBayaran = dateFormat.format(cal.getTime());
                            }


                        }


                    }
                }


            }
        }
        return tarikhAkhirBayaran;
    }

    public String updateTempohBulanKuasaPTG() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LanjutanBayaran lb = pservice.findLanjutBayaranByIdMohon(permohonan.getIdPermohonan());
        //update table mohon for PBMT
        if (permohonan.getPermohonanSebelum() != null) {
            //get id RAYL - find by id sebelum
            Permohonan idPermohonanRAYL = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            System.out.println("::::::::::" + idPermohonanRAYL.getIdPermohonan());
            if (idPermohonanRAYL != null) {
                if (idPermohonanRAYL.getPermohonanSebelum() != null) {
                    Permohonan idPermohonanPBMT = permohonanDAO.findById(idPermohonanRAYL.getPermohonanSebelum().getIdPermohonan());
                    if (idPermohonanPBMT != null) {

                        //update table notis
                        listNotis = new ArrayList<Notis>();
                        listNotis = pservice.getListNotis2(idPermohonanPBMT.getIdPermohonan(), "N5A");

                        if (!listNotis.isEmpty()) {
                            n = listNotis.get(0);
                        }
                        InfoAudit ia = new InfoAudit();
                        if (n != null) {
                            logger.info("::: update notis");
                            ia = n.getInfoAudit();
                            ia.setTarikhKemaskini(new java.util.Date());
                            ia.setDiKemaskiniOleh(pengguna);

                            n.setInfoAudit(ia);
                            if (n.getTempohBulan() != 0 && lb.getTempoh() != null) {
                                if (StringUtils.isNotBlank(n.getBilangan())) {
                                    if (n.getBilangan().equalsIgnoreCase("1")) {
                                        n.setTempohBulan(n.getTempohBulan() + lb.getTempoh());

                                    }
                                }
                            }
                            n.setBilangan("2");
                            logger.info("::: update tempoh : " + n.getTempohBulan());
                            devServices.saveOrUpdate(n);

                            if (n.getTarikhTerima() != null) {
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(n.getTarikhTerima());
                                cal.add(Calendar.MONTH, n.getTempohBulan());

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                tarikhAkhirBayaran = dateFormat.format(cal.getTime());
                            }


                        }
                    }
                }
            }

        }






        return tarikhAkhirBayaran;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(Long idKertas) {
        this.idKertas = idKertas;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public PermohonanKertasKandungan getMohonKertasKand() {
        return mohonKertasKand;
    }

    public void setMohonKertasKand(PermohonanKertasKandungan mohonKertasKand) {
        this.mohonKertasKand = mohonKertasKand;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandList() {
        return mohonKertasKandList;
    }

    public void setMohonKertasKandList(List<PermohonanKertasKandungan> mohonKertasKandList) {
        this.mohonKertasKandList = mohonKertasKandList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public KodDokumen getKodDok() {
        return kodDok;
    }

    public void setKodDok(KodDokumen kodDok) {
        this.kodDok = kodDok;
    }

    public String getKandunganPertama() {
        return kandunganPertama;
    }

    public void setKandunganPertama(String kandunganPertama) {
        this.kandunganPertama = kandunganPertama;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandList0() {
        return mohonKertasKandList0;
    }

    public void setMohonKertasKandList0(List<PermohonanKertasKandungan> mohonKertasKandList0) {
        this.mohonKertasKandList0 = mohonKertasKandList0;
    }

    public List<PermohonanKertasKandungan> getMohonKertasKandTmbhn() {
        return mohonKertasKandTmbhn;
    }

    public void setMohonKertasKandTmbhn(List<PermohonanKertasKandungan> mohonKertasKandTmbhn) {
        this.mohonKertasKandTmbhn = mohonKertasKandTmbhn;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public List<Ansuran> getAnsuranList() {
        return ansuranList;
    }

    public void setAnsuranList(List<Ansuran> ansuranList) {
        this.ansuranList = ansuranList;
    }

    public LanjutanBayaran getLanjutBayaran() {
        return lanjutBayaran;
    }

    public void setLanjutBayaran(LanjutanBayaran lanjutBayaran) {
        this.lanjutBayaran = lanjutBayaran;
    }

    public String getBay() {
        return bay;
    }

    public void setBay(String bay) {
        this.bay = bay;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }

    public Notis getN() {
        return n;
    }

    public void setN(Notis n) {
        this.n = n;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }
}

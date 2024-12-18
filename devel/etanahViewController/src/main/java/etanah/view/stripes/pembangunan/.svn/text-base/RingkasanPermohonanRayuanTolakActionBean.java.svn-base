/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author khairul.hazwan
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
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Dokumen;
import etanah.model.KodDokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanPlotPelan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodAgensi;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.report.ReportUtil;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.manager.TabManager;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import net.sourceforge.stripes.action.RedirectResolution;
import java.io.File;

@UrlBinding("/pembangunan/melaka/ringkasanPermohonanJKBB_RayuanTolak")
public class RingkasanPermohonanRayuanTolakActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RingkasanPermohonanRayuanTolakActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanRujukanLuarService permohonanRujService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    TabManager tabManager;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanPlotPelanDAO plotPelanDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    private Permohonan permohonan;
    private Permohonan permohonanBaru;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private LaporanTanah laporanTanah;
    private String persidangan;
    private String idPermohonan;
    private String idPermohonanBaru;
    private String tarikhPermohonan;
    private String tajuk;
    private String lokasiTanah;
    private String tujuan;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private int rowCount;
    private String perakuan1;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> listPemohon;
    private List<Pihak> uniquePemohonList;
    private List<Pihak> uniquePemohonList2;
    private Pengguna pengguna;
    private String kodUrusan;
    private String pt;
    private KodDokumen kd;
    private List<PermohonanPlotPelan> listplotpelan;
    private String workflowId;
    private String idFolder = "";
    private String taskId;
    private String stageId;
    private Task task = null;
    private BPelService service;
    private String keputusan;
    private String tujuan1;
    private List<PermohonanKertasKandungan> senaraiTujuan;
    private int rowCount2;
    private PermohonanRujukanLuar ulasanAdun;
    private List<PermohonanRujukanLuar> ulasanList;
    private String pejabat;
    private String ulasanptd;
    private String ulasanptg;
    private String perakuanPengarah;
    private String kertasBil;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/ringkasan_permohonan_rayuanTolak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("editForm");
        return new JSP("pembangunan/melaka/ringkasan_permohonan_rayuanTolak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        LOG.info("rehydrate start");
        rowCount = 1;
        rowCount2 = 1;

        //idPermohonanBaru
        idPermohonanBaru = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanBaru = permohonanDAO.findById(idPermohonanBaru);

        //idPermohonanSebelum
        idPermohonan = permohonanBaru.getPermohonanSebelum().getIdPermohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //String[] tname = {"permohonan"};
        //Object[] model = {permohonan};

        //pemohon
        uniquePemohonList2 = devService.findUniquePemohonByIdPermohonan2(idPermohonan);
        if (uniquePemohonList2 != null && !uniquePemohonList2.isEmpty()) {
            LOG.info("------size-------:" + uniquePemohonList2.size());
        }

        listPemohon = permohonan.getSenaraiPemohon();
        listplotpelan = devService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        //ulasan Adun & JTEK
        ulasanList = devService.findUlasanJabatanTek(idPermohonan);
        ulasanAdun = devService.findUlasanAdun(idPermohonan);

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            senaraiRujukanLuar = devService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
        }

        //tarikhPermohonan
        FasaPermohonan mohonFasa = devService.findFasaPermohonanByIdAliran(idPermohonan, "cetakrencanajkbb");
        if (mohonFasa != null) {
            if (mohonFasa.getInfoAudit().getTarikhKemaskini() != null) {
                tarikhPermohonan = sdf.format(mohonFasa.getInfoAudit().getTarikhKemaskini());
                LOG.info("---------tarikhPermohonan1---------------:" + tarikhPermohonan);
            } else if (mohonFasa.getInfoAudit().getTarikhMasuk() != null) {
                tarikhPermohonan = sdf.format(mohonFasa.getInfoAudit().getTarikhMasuk());
                LOG.info("---------tarikhPermohonan2---------------:" + tarikhPermohonan);
            }
        }

        //textForm Pengarah
        perakuanPengarah = "9. PERAKUAN PENGARAH TANAH DAN GALIAN";

        PermohonanKertas kertasP = new PermohonanKertas();
        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonanBaru, "RKSP");

        if (senaraiKertas.size() > 0) {
            LOG.info("------ID BARU-------");
            kertasP = senaraiKertas.get(0);
            LOG.info("------------RKSP----kertasP-----:" + kertasP);
            if (kertasP != null) {
                persidangan = kertasP.getTajuk();
                kertasBil = kertasP.getNomborRujukan();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                    LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        tajuk = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2) {
                        lokasiTanah = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3) {
                        keputusan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        pejabat = kertasK.getSubtajuk();
                        ulasanptd = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5) {
                        ulasanptg = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                        tujuan1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
                        perakuan1 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 10) {
                        perakuanPengarah = kertasK.getKandungan();
                    }

                }
                senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
                senaraiKandungan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 9);
                rowCount = senaraiKandungan.size();
                senaraiTujuan = new ArrayList<PermohonanKertasKandungan>();
                senaraiTujuan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                rowCount2 = senaraiTujuan.size();
                //  }

            }
        } else {
            List<PermohonanKertas> senaraiKertas1 = new ArrayList<PermohonanKertas>();
            senaraiKertas1 = devService.findSenaraiKertasByKod(idPermohonan, "RKSP");

            if (senaraiKertas1.size() > 0) {
                LOG.info("------ID LAMA-------");
                LOG.info("------------RKSP----kertasP-----:" + kertasP);
                kertasP = senaraiKertas1.get(0);
                if (kertasP != null) {
                    persidangan = kertasP.getTajuk();
                    kertasBil = kertasP.getNomborRujukan();

                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                        LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);

                        if (kertasK.getBil() == 1) {
                            tajuk = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            lokasiTanah = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            keputusan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4) {
                            pejabat = kertasK.getSubtajuk();
                            ulasanptd = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5) {
                            ulasanptg = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6 && kertasK.getSubtajuk().equals("6.1")) {
                            tujuan1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
                            perakuan1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 10) {
                            perakuanPengarah = kertasK.getKandungan();
                        }

                    }
                    senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 9);
                    rowCount = senaraiKandungan.size();
                    senaraiTujuan = new ArrayList<PermohonanKertasKandungan>();
                    senaraiTujuan = devService.findKertasKandByIdKertas(kertasP.getIdKertas(), 6);
                    rowCount2 = senaraiTujuan.size();
                }
            }
        }
        LOG.info("rehydrate finish");
    }

    public Resolution simpan() throws ParseException {
        LOG.info("simpan start");
        idPermohonanBaru = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanBaru = permohonanDAO.findById(idPermohonanBaru);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("RKSP");

        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "perakuanjkbbptg");
        if (fp != null && fp.getKeputusan() != null) {
            ulasanptg = fp.getKeputusan().getKod();
        }

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

        if (persidangan == null || persidangan.equals("")) {
            persidangan = " - ";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = " - ";
        }

        if (lokasiTanah == null || lokasiTanah.equals("")) {
            lokasiTanah = " - ";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = " - ";
        }
        if (ulasanptd == null || ulasanptd.equals("")) {
            ulasanptd = " - ";
        }
        if (ulasanptg == null || ulasanptg.equals("")) {
            ulasanptg = " - ";
        }
        if (perakuanPengarah == null || perakuanPengarah.equals("")) {
            perakuanPengarah = " - ";
        }
        if (kertasBil == null) {
            kertasBil = "";
        }


        listUlasan.add(tajuk);
        listUlasan.add(lokasiTanah);
        listUlasan.add(keputusan);
        listUlasan.add(ulasanptd);
        listUlasan.add(ulasanptg);
        listUlasan.add(perakuanPengarah);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add(pejabat);
        listSubtajuk.add("");
        listSubtajuk.add("");

        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
        senaraiKertas = devService.findSenaraiKertasByKod(idPermohonanBaru, "RKSP");
        if (senaraiKertas.size() > 0) {
            permohonanKertas = senaraiKertas.get(0);
        } else {
            permohonanKertas = null;
        }

        if (permohonanKertas != null) {

            if (!kertasK.getKandungan().isEmpty()) {
                LOG.info("kemaskini start");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = permohonanKertas.getSenaraiKandungan().get(j);
                    LOG.info("senarai kandungan:" + permohonanKertas.getSenaraiKandungan().size());

                    if (kertasK.getBil() == 1) {
                        kertasK.setKandungan(tajuk);
                    } else if (kertasK.getBil() == 2) {
                        kertasK.setKandungan(lokasiTanah);
                    } else if (kertasK.getBil() == 3) {
                        kertasK.setKandungan(keputusan);
                    } else if (kertasK.getBil() == 4) {
                        kertasK.setKandungan(ulasanptd);
                    } else if (kertasK.getBil() == 5) {
                        kertasK.setKandungan(ulasanptg);
                    } else if (kertasK.getBil() == 10) {
                        kertasK.setKandungan(perakuanPengarah);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonanBaru);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk(persidangan);
                    permohonanKertas.setNomborRujukan(kertasBil);
                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
            }
            LOG.info("kemaskini finish");
        } else {
            permohonanKertas = new PermohonanKertas();
            LOG.info("simpan new entry");
            for (int i = 0; i < listUlasan.size(); i++) {

                String dataulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                kertasK = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonanBaru);
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk(persidangan);
                permohonanKertas.setNomborRujukan(kertasBil);
                kertasK.setKertas(permohonanKertas);
                if (i == 5) {
                    kertasK.setBil(10);
                } else {
                    kertasK.setBil(i + 1);
                }
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(dataulasan);
                kertasK.setSubtajuk(subtajuk);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
        }
        senaraiKandungan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        LOG.info("----------count2--------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan.size() != 0 && i <= senaraiKandungan.size()) {
                Long id = senaraiKandungan.get(i - 1).getIdKandungan();
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
            String kandungan = getContext().getRequest().getParameter("perakuan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("9." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }
        senaraiTujuan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("----------count2--------:" + kira2);
        for (int i = 1; i <= kira2; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiTujuan.size() != 0 && i <= senaraiTujuan.size()) {
                Long id = senaraiTujuan.get(i - 1).getIdKandungan();
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
            String kandungan = getContext().getRequest().getParameter("tujuan" + i);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("6." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        LOG.info("simpan finish");

        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 9);
        rowCount = senaraiKandungan.size();
        senaraiTujuan = new ArrayList<PermohonanKertasKandungan>();
        senaraiTujuan = devService.findKertasKandByIdKertas(permohonanKertas.getIdKertas(), 6);
        rowCount2 = senaraiTujuan.size();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/ringkasan_permohonan_rayuanTolak.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(RingkasanPermohonanRayuanTolakActionBean.class, "locate");
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

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public SimpleDateFormat getTdf() {
        return tdf;
    }

    public void setTdf(SimpleDateFormat tdf) {
        this.tdf = tdf;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getPerakuan1() {
        return perakuan1;
    }

    public void setPerakuan1(String perakuan1) {
        this.perakuan1 = perakuan1;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
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

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public List<PermohonanKertasKandungan> getSenaraiTujuan() {
        return senaraiTujuan;
    }

    public void setSenaraiTujuan(List<PermohonanKertasKandungan> senaraiTujuan) {
        this.senaraiTujuan = senaraiTujuan;
    }

    public String getTujuan1() {
        return tujuan1;
    }

    public void setTujuan1(String tujuan1) {
        this.tujuan1 = tujuan1;
    }

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public List<PermohonanRujukanLuar> getUlasanList() {
        return ulasanList;
    }

    public void setUlasanList(List<PermohonanRujukanLuar> ulasanList) {
        this.ulasanList = ulasanList;
    }

    public String getPejabat() {
        return pejabat;
    }

    public void setPejabat(String pejabat) {
        this.pejabat = pejabat;
    }

    public String getUlasanptd() {
        return ulasanptd;
    }

    public void setUlasanptd(String ulasanptd) {
        this.ulasanptd = ulasanptd;
    }

    public String getUlasanptg() {
        return ulasanptg;
    }

    public void setUlasanptg(String ulasanptg) {
        this.ulasanptg = ulasanptg;
    }

    public String getPerakuanPengarah() {
        return perakuanPengarah;
    }

    public void setPerakuanPengarah(String perakuanPengarah) {
        this.perakuanPengarah = perakuanPengarah;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public List<Pihak> getUniquePemohonList() {
        return uniquePemohonList;
    }

    public void setUniquePemohonList(List<Pihak> uniquePemohonList) {
        this.uniquePemohonList = uniquePemohonList;
    }

    public List<Pihak> getUniquePemohonList2() {
        return uniquePemohonList2;
    }

    public void setUniquePemohonList2(List<Pihak> uniquePemohonList2) {
        this.uniquePemohonList2 = uniquePemohonList2;
    }

    public Permohonan getPermohonanBaru() {
        return permohonanBaru;
    }

    public void setPermohonanBaru(Permohonan permohonanBaru) {
        this.permohonanBaru = permohonanBaru;
    }

    public String getIdPermohonanBaru() {
        return idPermohonanBaru;
    }

    public void setIdPermohonanBaru(String idPermohonanBaru) {
        this.idPermohonanBaru = idPermohonanBaru;
    }
}
//    public void modifyPerakuanPengarah(){        
//        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonan,"perakuanjkbbptg");
//        String kodKpsn = "";
//        String ulasan = "";
//        String s1 = "";
//	String s2 = "";
//	String s3 = "";
//	String s4 = "";
//        String s5 = "";
//        if(fp!=null && fp.getKeputusan()!=null){
//            kodKpsn =  fp.getKeputusan().getKod();
//            ulasan = fp.getUlasan();
//            if(ulasan!=null && !ulasan.equals("")){
//               ulasan = ulasan.toLowerCase(); 
//            }                
//        } 
//        LOG.info("----kodKpsn-----"+kodKpsn+"---ulasan---"+ulasan);
//        PermohonanKertas mohonKertas = new PermohonanKertas();
//        mohonKertas = devService.findPermohonanKertasByKod(idPermohonan, "RKSP");
//        if(mohonKertas!=null){
//            for(int i=0;i<mohonKertas.getSenaraiKandungan().size();i++){
//                kertasK = new PermohonanKertasKandungan();
//                    kertasK = mohonKertas.getSenaraiKandungan().get(i);
//                    if (kertasK.getBil() == 9 && kertasK.getSubtajuk().equals("9.1")) {
//                        perakuan1 = kertasK.getKandungan();                         
//                        s1 = perakuan1;
//                        if(s1.indexOf("tidak disokong")!=-1){
//                                s2 = s1.substring(0, s1.indexOf("tidak disokong"));
//                        }else{
//                                s2 = s1;
//                        }
//                        if(s2.indexOf("disokong")!=-1){
//                                s3 = s2.substring(0, s2.indexOf("disokong"));
//                        }else{
//                                s3 = s2;
//                        }
//                        if(s3.indexOf("ada pindaan")!=-1){
//                          s4 = s3.substring(0, s3.indexOf("ada pindaan"));
//                        }else{
//                           s4 = s3;	
//                        }
//                        if(s4.indexOf("sokong bersyarat")!=-1){
//                          s5 = s4.substring(0, s4.indexOf("sokong bersyarat"));
//                        }else{
//                           s5 = s4;	
//                        }
//                         
//                        if(kodKpsn.equalsIgnoreCase("LK")){
//                            perakuan1 = s5+" disokong.";
//                        }else if(kodKpsn.equalsIgnoreCase("XA")){
//                            perakuan1 = s5+" tidak disokong kerana "+ulasan;
//                        }else if(kodKpsn.equalsIgnoreCase("TL")){
//                            perakuan1 = s5+" ada pindaan kerana "+ulasan;
//                        }else if(kodKpsn.equalsIgnoreCase("KS")){
//                            perakuan1 = s5+" sokong bersyarat kerana "+ulasan;
//                        }
//                        
//                        LOG.info("---after---perakuanPengarah---------:"+perakuan1);
//                        kertasK.setKandungan(perakuan1);
//                        devService.simpanPermohonanKertasKandungan(kertasK);                        
//               }
//            }
//        }// if
//    }  
//    public String currentStageId(){
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        if (StringUtils.isBlank(taskId)) {
//            taskId = getContext().getRequest().getParameter("taskId");
//        }
//        task = service.getTaskFromBPel(taskId, pengguna);
//        if (task != null) {
//            stageId = task.getSystemAttributes().getStage();
//        } else {
//            stageId = getContext().getRequest().getParameter("stageId");
//        }
//        return stageId;
//    }
//    public List<PermohonanRujukanLuar> addPengarahTanah(List<PermohonanRujukanLuar> senaraiRujukanLuar){
//        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonan,"perakuanjkbbptg");
//        if(fp!=null && fp.getKeputusan()!=null){
//           ulasanptg = fp.getKeputusan().getKod(); 
//        }
//        LOG.info("------------addPengarahTanah--ulasanptg---:"+ulasanptg);
//        PermohonanRujukanLuar mohonRujLuar1 = new PermohonanRujukanLuar();        
//        mohonRujLuar1.setNamaAgensi("Pentadbir Tanah "+pejabat);
//        if(ulasanptd!=null && ulasanptd.equalsIgnoreCase("tidak disokong")){
//            mohonRujLuar1.setDiSokong('2');
//        }else if(ulasanptd!=null && ulasanptd.equalsIgnoreCase("disokong")){
//            mohonRujLuar1.setDiSokong('1');
//        }else{
//            mohonRujLuar1.setDiSokong('-');
//        }
//        
//        PermohonanRujukanLuar mohonRujLuar2 = new PermohonanRujukanLuar();
//        mohonRujLuar2.setNamaAgensi("Pengarah Tanah Dan Galian Melaka");
//        if(ulasanptg!=null && ulasanptg.equalsIgnoreCase("XA")){
//            mohonRujLuar2.setDiSokong('2');
//        }else if(ulasanptg!=null && ulasanptg.equalsIgnoreCase("LK")){
//            mohonRujLuar2.setDiSokong('1');
//        }else if(ulasanptg!=null && ulasanptg.equalsIgnoreCase("KS")){
//            mohonRujLuar2.setDiSokong('3');
//        }else if(ulasanptg!=null && ulasanptg.equalsIgnoreCase("TL")){
//            mohonRujLuar2.setDiSokong('5');
//        }else{
//            mohonRujLuar2.setDiSokong('-');
//        }
//        
//        if(senaraiRujukanLuar==null){
//            senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();            
//        }       
//        senaraiRujukanLuar.add(mohonRujLuar1);
//        senaraiRujukanLuar.add(mohonRujLuar2);            
//        for(PermohonanRujukanLuar mohonRujLuar:senaraiRujukanLuar){
//             mohonRujLuar.setNamaAgensi(pembangunanUtility.convertStringtoInitCap(mohonRujLuar.getNamaAgensi())); 
//         }
//        return senaraiRujukanLuar;
//    }
//    public Resolution genReport() {
//        LOG.info("genReport :: start");
//        System.out.println("generate report start.");
//        String msg = "Laporan telah dijana. Sila buat semakan sebelum membawanya ke Mesyuarat JKBB.";
////        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        if (StringUtils.isBlank(stageId)) {
//            LOG.error("::Stage Id NULL::");
//            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
//        }
//
//        try {
//                LOG.info("genReportFromXML");
//                genReportFromXml();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//        }
//        LOG.info("genReport :: finish");
//        return new StreamingResolution("text/plain", msg);
//    }
//    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
//        InfoAudit ia = new InfoAudit();
//        Dokumen doc = null;
//        doc = semakDokumenService.semakDokumen(kd, fd, id);
//        if (doc == null) {
//            doc = new Dokumen();
//            ia.setDimasukOleh(pengguna);
//            ia.setTarikhMasuk(new java.util.Date());
//            doc.setBaru('Y');
//        } else {
//            ia = doc.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengguna);
//            ia.setTarikhKemaskini(new java.util.Date());
//            doc.setBaru('T');
//        }
//        doc.setFormat("application/pdf");
//        doc.setInfoAudit(ia);
//        //TODO : increase versi if regenarate report
//        doc.setNoVersi("1.0");
//        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
//        doc.setKlasifikasi(klasifikasi_AM);
//        doc.setTajuk(kd.getNama());
//        doc.setKodDokumen(kd);
//        doc.setDalamanNilai1(id);
//        doc = dokumenService.saveOrUpdate(doc);
//        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
//        if (kf == null) {
//            kf = new KandunganFolder();
//        } else {
//            ia = kf.getInfoAudit();
//            ia.setDiKemaskiniOleh(pengguna);
//            ia.setTarikhKemaskini(new java.util.Date());
//        }
//        kf.setInfoAudit(ia);
//        kf.setFolder(fd);
//        kf.setDokumen(doc);
//        dokumenService.saveKandunganWithDokumen(kf);
//
//        return doc;
//    }
//
//    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
//        Dokumen d = dokumenService.findById(idDokumen);
//        d.setNamaFizikal(namaFizikal);
//        dokumenService.saveOrUpdate(d);
//    }
//
//    private void genReportFromXml() throws Exception {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String dokumenPath = conf.getProperty("document.path");
//        dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator);
//        String path = "";
//        stageId = task.getSystemAttributes().getStage();
////        stageId = "cetakjkbbrekodkpsn";
//        Dokumen d = null;
//        
//        if (workflowId != null && stageId != null) {
//                String gen = "DEVRJKBBPTD_MLK.rdf";
//                String prefix = "VDOC";
//                String code = "JKBBD";
//                String[] params = null;
//                String[] values = null;
//                KodDokumen kd = kodDokumenDAO.findById(code);
//                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//                    params = new String[]{"p_id_mohon"};
//                    values = new String[]{idPermohonan};
//                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//                    HakmilikPermohonan hakmilikPermohonan = hk.get(0);
//                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    LOG.info("::Path To save :: " + (dokumenPath + path));
//                    LOG.info("::Report Name ::" + gen);
//                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                    if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
//                        hakmilikPermohonan.setDokumen1(d);
//                    }
//                    if (kd.getKod().equals("DHKE")) {
//                        hakmilikPermohonan.setDokumen3(d);
//                    }
//                    if (kd.getKod().equals("DHDE")) {
//                        hakmilikPermohonan.setDokumen2(d);
//                    }
//                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//        }
//
//        if (workflowId != null && stageId != null) {
//                String gen = "DEVRJKBBPTG_MLK.rdf";
//                String prefix = "VDOC";
//                String code = "JKBBG";
//                String[] params = null;
//                String[] values = null;
//                KodDokumen kd = kodDokumenDAO.findById(code);
//                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//                    params = new String[]{"p_id_mohon"};
//                    values = new String[]{idPermohonan};
//                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//                    HakmilikPermohonan hakmilikPermohonan = hk.get(0);
//                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    LOG.info("::Path To save :: " + (dokumenPath + path));
//                    LOG.info("::Report Name ::" + gen);
//                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                    if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
//                        hakmilikPermohonan.setDokumen1(d);
//                    }
//                    if (kd.getKod().equals("DHKE")) {
//                        hakmilikPermohonan.setDokumen3(d);
//                    }
//                    if (kd.getKod().equals("DHDE")) {
//                        hakmilikPermohonan.setDokumen2(d);
//                    }
//                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//        }
//        if (workflowId != null && stageId != null) {
//                String gen = "DEVRPJKBBPTG_MLK.rdf";
//                String prefix = "VDOC";
//                String code = "RKSP";
//                String[] params = null;
//                String[] values = null;
//                KodDokumen kd = kodDokumenDAO.findById(code);
//                FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//                    params = new String[]{"p_id_mohon"};
//                    values = new String[]{idPermohonan};
//                    List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//                    HakmilikPermohonan hakmilikPermohonan = hk.get(0);
//                    d = saveOrUpdateDokumen(fd, kd, idPermohonan);
//                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    LOG.info("::Path To save :: " + (dokumenPath + path));
//                    LOG.info("::Report Name ::" + gen);
//                    reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
//                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//                    if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
//                        hakmilikPermohonan.setDokumen1(d);
//                    }
//                    if (kd.getKod().equals("DHKE")) {
//                        hakmilikPermohonan.setDokumen3(d);
//                    }
//                    if (kd.getKod().equals("DHDE")) {
//                        hakmilikPermohonan.setDokumen2(d);
//                    }
//                    hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//        }
//    }
//    public String tujuanDefaultData(){
//        
//        List<PermohonanPlotPelan> mppList=new ArrayList<PermohonanPlotPelan>();
//                mppList = devService.findPermohonanPlotPelanByIdPermohonanKodMLK(idPermohonan); 
//                StringBuffer tujuanInfo = new StringBuffer();
//                StringBuffer tujuanDetail = new StringBuffer();
//                int milikCount =0;
//                String kodKatgTanah="";
//                int bil=0;
//                if(mppList!=null && !mppList.isEmpty()){                    
//                   for(PermohonanPlotPelan mpp:mppList){
//                        bil++;
//                        tujuanDetail.append(bil+") "+mpp.getNoPlot()+" seluas "+mpp.getLuas()+" "+mpp.getKodUnitLuas().getNama()+" (   ) \n"); 
//                        if(mpp.getPemilikan()!=null && mpp.getPemilikan().getKod()== 'H'){
//                          milikCount++;                          
//                        }
//                        if(mpp.getKategoriTanah()!=null){
//                            kodKatgTanah = mpp.getKategoriTanah().getNama();
//                        }
//                   } 
//                   tujuanInfo.append(milikCount+" unit Lot "+kodKatgTanah+" \n");
//                   tujuanInfo.append(tujuanDetail.toString());
//                }else{
//                    tujuanInfo.append("");
//                }
//           return tujuanInfo.toString();
//    }


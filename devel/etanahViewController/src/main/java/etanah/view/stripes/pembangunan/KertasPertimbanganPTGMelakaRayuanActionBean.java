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
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
//import etanah.model.HakmilikPihakBerkepentingan;
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
import java.io.File;
import java.text.SimpleDateFormat;
import etanah.report.ReportUtil;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.manager.TabManager;
import etanah.model.HakmilikUrusan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PihakPengarah;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanCerun;
import etanah.model.Pihak;
import etanah.model.HakmilikPihakBerkepentingan;
import java.math.BigDecimal;
import java.util.StringTokenizer;

@UrlBinding("/pembangunan/melaka/kertasPertimbanganPTG_RayuanTolak")
public class KertasPertimbanganPTGMelakaRayuanActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasPertimbanganPTGMelakaRayuanActionBean.class);
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
    KodNegeriDAO kodNegeriDAO;
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
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    private Permohonan permohonan;
    private Permohonan permohonanBaru;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private List<HakmilikPihakBerkepentingan> listHakmilikBerkepentinganAktif;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private LaporanTanah laporanTanah;
    private List<Pemohon> listPemohon;
    private Pengguna pengguna;
    private Task task = null;
    private BPelService service;
    private Hakmilik hakmilikSingle;
    private List<PermohonanPlotPelan> listplotpelan;
    private PermohonanPlotPelan plotpelan;
    private List<PermohonanRujukanLuar> ulasanList;
    private List<PihakPengarah> listPengarah;
    private PermohonanRujukanLuar ulasanAdun;
    private PermohonanRujukanLuar rujukanLuar;
    private KodDokumen kd;
    List<PermohonanPlotPelanBean> plotList = new ArrayList<PermohonanPlotPelanBean>();
    private String stageId;
    private String tajuk;
    private String perihal;
    private String tujuan;
    private String perakuan;
    private String lokasiTanah;
    private String tarikhMesyuarat;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat tdf = new SimpleDateFormat("hh:mm a");
    private SimpleDateFormat tdf1 = new SimpleDateFormat("hh:mm:ss");
    private SimpleDateFormat df = new SimpleDateFormat("a");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String persidangan;
    private String masasidang;
    private String tempatsidang;
    private String lokasi;
    private String workflowId;
    private String idFolder = "";
    private String kodUrusan;
    private String taskId;
    private String pt;
    private boolean ptg = true;
    private String tarikhPermohonan;
    private String nolot;
    private String nohakmilik;
    private String nama;
    private String pejTanah;
    private String perakuan2;
    private int saizList;
    private String maklumat;
    private String jam;
    private String minit;
    private String pagiPetang;
    private List<PermohonanLaporanCerun> findListlaporCerun;
    private List<PermohonanLaporanBangunan> permohonanLaporanBangunanList;
    private List<PermohonanLaporanBangunan> permohonanLaporanTanamanList;
    private List<HakmilikUrusan> hakUrusan;
    private String kertasBil;
    private List<String> keluasanLot = new ArrayList<String>();
    private int bilUnitTotal = 0;
    private BigDecimal keluasanLotTotal = BigDecimal.ZERO;
    private BigDecimal luasTotal = BigDecimal.ZERO;
    private String kodUnitLuas;
    private List<PermohonanPlotPelan> mppList = new ArrayList<PermohonanPlotPelan>();
    private String daerah;
    private List<Pihak> uniquePemohonList;
    private List<Pihak> uniquePemohonList2;
    private String bebananBerdafter;
    private String suratPersetujuan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kertas_pertimbangan_PTG_rayuanTolak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("editForm");
        return new JSP("pembangunan/melaka/kertas_pertimbangan_PTG_rayuanTolak.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        service = new BPelService();
        listPemohon = new ArrayList<Pemohon>();
        LOG.info("rehydrate start");

        //idmohonBaru
        String idPermohonanBaru = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanBaru = permohonanDAO.findById(idPermohonanBaru);

        //idmohonLama
        String idPermohonan = permohonanBaru.getPermohonanSebelum().getIdPermohonan();
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //Perihal Pemohon (No Editing)
        listPemohon = devService.findPemohonByIdPermohonan(idPermohonan);
        uniquePemohonList2 = devService.findUniquePemohonByIdPermohonan2(idPermohonan);
        hakUrusan = new ArrayList<HakmilikUrusan>();
        hakUrusan = devService.findHakmilikUrusanByHakmilik(idPermohonan);

        if (uniquePemohonList2 != null && !uniquePemohonList2.isEmpty()) {
            LOG.info("------size-------:" + uniquePemohonList2.size());
        }
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }
        for (Pemohon pemohon1 : listPemohon) {
            listPengarah = pemohon1.getPihak().getSenaraiPengarah();
        }

        LOG.info("------listPemohon Size------:" + listPemohon.size());
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
        LOG.info("------Pemohon Nama------:" + nama);

        tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());

        //tujuanPermohonan (No Editing)
        mppList = new ArrayList<PermohonanPlotPelan>();
        mppList = devService.findPermohonanPlotPelanByIdPermohonanKodMLK(idPermohonan);
        if (mppList != null) {
            for (int i = 0; i < mppList.size(); i++) {
                PermohonanPlotPelan mpp = mppList.get(i);
                if (mpp.getPemilikan().getKod() == 'H') {
                    bilUnitTotal = bilUnitTotal + mpp.getBilanganPlot();
                }
                keluasanLotTotal = keluasanLotTotal.add(mpp.getLuas().multiply(new BigDecimal(mpp.getBilanganPlot())));
                kodUnitLuas = mpp.getKodUnitLuas().getNama();
            }
        }

        //Butir-Butir Hakmilik (No Editing)   
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            saizList = hakmilikPermohonanList.size();
        }
        HakmilikPermohonan hp = new HakmilikPermohonan();
        if (!(hakmilikPermohonanList.isEmpty())) {
            hp = hakmilikPermohonanList.get(0);
            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            nolot = hakmilikSingle.getLot().getNama() + " " + hakmilikSingle.getNoLot().replaceAll("^0*", "");
            nohakmilik = hakmilikSingle.getKodHakmilik().getKod() + " " + hakmilikSingle.getNoHakmilik().replaceAll("^0*", "");
        }

        //Keadaan Tanah (No Editing) 
        findListlaporCerun = new ArrayList<PermohonanLaporanCerun>();
        permohonanLaporanTanamanList = new ArrayList<PermohonanLaporanBangunan>();
        permohonanLaporanBangunanList = new ArrayList<PermohonanLaporanBangunan>();
        findListlaporCerun = devService.findLaporCerunListByIdPermohonan(permohonan.getIdPermohonan());
        permohonanLaporanTanamanList = devService.getLaporTnmnByIdLaporan(idPermohonan);
        permohonanLaporanBangunanList = devService.getLaporBngnByIdLaporan(idPermohonan);


        //Ulasan YB and JTEK (No Editing) 
        ulasanList = devService.findUlasanJabatanTek(idPermohonan);
        ulasanAdun = devService.findUlasanAdun(idPermohonan);

       
        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonanBaru.getSenaraiKertas().isEmpty())) {
            //idmohonBaru
            List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
            senaraiKertas = devService.findSenaraiKertasByKod(idPermohonanBaru, "JKBB");
            if (senaraiKertas.size() > 0) {
                kertasP = senaraiKertas.get(0);
            } else {
                kertasP = null;
            }

            if (kertasP != null) {
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                    masasidang = tdf.format(kertasP.getTarikhSidang());
                    jam = masasidang.substring(0, 2);
                    minit = masasidang.substring(3, 5);
                    pagiPetang = masasidang.substring(6, 8);
                }
                LOG.info("--------jam--------:" + jam);
                LOG.info("--------minit--------:" + minit);
                LOG.info("--------pagiPetang--------:" + pagiPetang);
                tempatsidang = kertasP.getTempatSidang();
                persidangan = kertasP.getTajuk();
                kertasBil = kertasP.getNomborRujukan();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        tajuk = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3) {
                        lokasiTanah = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        maklumat = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5) {
                        pejTanah = kertasK.getSubtajuk();
                        perakuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6) {
                        perakuan2 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7) {
                        bebananBerdafter = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8) {
                        suratPersetujuan = kertasK.getKandungan();
                    }
                }
            }
        } else {
            //idmohonSebelum
            List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
            senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "JKBB");
            if (senaraiKertas.size() > 0) {
                kertasP = senaraiKertas.get(0);
            } else {
                kertasP = null;
            }

            if (kertasP != null) {
                if (kertasP.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(kertasP.getTarikhSidang());
                    masasidang = tdf.format(kertasP.getTarikhSidang());
                    jam = masasidang.substring(0, 2);
                    minit = masasidang.substring(3, 5);
                    pagiPetang = masasidang.substring(6, 8);
                }
                LOG.info("--------jam--------:" + jam);
                LOG.info("--------minit--------:" + minit);
                LOG.info("--------pagiPetang--------:" + pagiPetang);
                tempatsidang = kertasP.getTempatSidang();
                persidangan = kertasP.getTajuk();
                kertasBil = kertasP.getNomborRujukan();

                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = kertasP.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        tajuk = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 2) {
                        tujuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 3) {
                        lokasiTanah = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 4) {
                        maklumat = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 5) {
                        pejTanah = kertasK.getSubtajuk();
                        perakuan = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 6) {
                        perakuan2 = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 7) {
                        bebananBerdafter = kertasK.getKandungan();
                    } else if (kertasK.getBil() == 8) {
                        suratPersetujuan = kertasK.getKandungan();
                    }
                }
            }

        }

        LOG.info("rehydrate finish");
    }

    public String bebananBerdafterDefaultData(String idPermohonan) {
        hakUrusan = new ArrayList<HakmilikUrusan>();
        hakUrusan = devService.findHakmilikUrusanByHakmilik(idPermohonan);
        StringBuffer bebananBerdafterTemp = new StringBuffer();
        LOG.info("-----hakUrusan-----:" + hakUrusan.size());
        for (int i = 0; i < hakUrusan.size(); i++) {
            HakmilikUrusan hu = hakUrusan.get(i);
            bebananBerdafterTemp.append("No Pers   " + hu.getIdPerserahan() + " ");
            if (hu.getFolderDokumen() != null) {
                if (hu.getFolderDokumen().getNoJilid() != null) {
                    bebananBerdafterTemp.append("jil." + (hu.getFolderDokumen().getNoJilid().replaceFirst("^0+(?!$)", "")) + " ");
                }
                if (hu.getFolderDokumen().getNoFolio() != null) {
                    bebananBerdafterTemp.append("Fol." + (hu.getFolderDokumen().getNoFolio().replaceFirst("^0+(?!$)", "")) + " ");
                }
            }
            bebananBerdafterTemp.append(" " + hu.getKodUrusan().getNama() + "\n");
            Permohonan permohonan = permohonanDAO.findById(hu.getIdPerserahan());
            // Pemohon details
            List<Pemohon> pemohonList = new ArrayList<Pemohon>();
            pemohonList = permohonan.getSenaraiPemohon();
            if (pemohonList != null && !pemohonList.isEmpty()) {
                bebananBerdafterTemp.append("Oleh   ");
            }
            for (int j = 0; j < pemohonList.size(); j++) {
                Pemohon pemohon = pemohonList.get(j);
                if (j == 0) {
                    bebananBerdafterTemp.append(pemohon.getNama() + ", ");
                } else {
                    bebananBerdafterTemp.append("       " + pemohon.getNama() + ", ");
                }
                if (pemohon.getJenisPengenalan() != null && !pemohon.getJenisPengenalan().getKod().equals("0")) {
                    bebananBerdafterTemp.append(pemohon.getJenisPengenalan().getNama() + ":" + pemohon.getNoPengenalan() + ", ");
                }
                bebananBerdafterTemp.append(pemohon.getSyerPenyebut() + "/" + pemohon.getSyerPembilang() + " bhgn.\n");
            }//for
            //PermohonanPihak details
            List<PermohonanPihak> mohonPihakList = new ArrayList<PermohonanPihak>();
            mohonPihakList = permohonan.getSenaraiPihak();
            if (mohonPihakList != null && !mohonPihakList.isEmpty()) {
                bebananBerdafterTemp.append("kepada   ");
            }
            for (int k = 0; k < mohonPihakList.size(); k++) {
                PermohonanPihak mohonPihak = mohonPihakList.get(k);
                if (k == 0) {
                    bebananBerdafterTemp.append(mohonPihak.getNama() + ", ");
                } else {
                    bebananBerdafterTemp.append("         " + mohonPihak.getNama() + ", ");
                }
                if (mohonPihak.getJenisPengenalan() != null && !mohonPihak.getJenisPengenalan().getKod().equals("0")) {
                    bebananBerdafterTemp.append(mohonPihak.getJenisPengenalan().getNama() + ":" + mohonPihak.getNoPengenalan());
                }
                bebananBerdafterTemp.append("\n");
                if (mohonPihak.getAlamat() != null) {
                    bebananBerdafterTemp.append("         ");
                    if (mohonPihak.getAlamat().getAlamat1() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getAlamat1() + ",");
                    }
                    if (mohonPihak.getAlamat().getAlamat2() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getAlamat2() + ",");
                    }
                    if (mohonPihak.getAlamat().getAlamat3() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getAlamat3() + ",");
                    }
                    if (mohonPihak.getAlamat().getAlamat4() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getAlamat4() + ",");
                    }
                    if (mohonPihak.getAlamat().getPoskod() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getPoskod() + ",");
                    }
                    if (mohonPihak.getAlamat().getNegeri() != null) {
                        bebananBerdafterTemp.append(mohonPihak.getAlamat().getNegeri().getNama());
                    }
                    bebananBerdafterTemp.append("\n");
                }
            }//for
            bebananBerdafterTemp.append("Didaftarkan pada ");

            if (hu.getTarikhDaftar() != null) {
                String date = sdf.format(hu.getTarikhDaftar());
                String time = tdf1.format(hu.getTarikhDaftar());
                String pg = df.format(hu.getTarikhDaftar());

                String day = date.substring(0, 2);
                String month = getMonth(Integer.parseInt(date.substring(3, 5)));
                String year = date.substring(6);

                bebananBerdafterTemp.append(day + " " + month + " " + year + " jam " + time);
                System.out.print(day + " " + month + " " + year + " " + time);
                if (pg.equals("AM")) {
                    bebananBerdafterTemp.append(" pagi \n\n");
                } else {
                    bebananBerdafterTemp.append(" petang \n\n");
                }
            } else {
                bebananBerdafterTemp.append("\n\n");
            }

        }
        return bebananBerdafterTemp.toString();
    }

    public String getMonth(int i) {
        switch (i) {
            case 1:
                return "Januari";
            case 2:
                return "Februari";
            case 3:
                return "Mac";
            case 4:
                return "April";
            case 5:
                return "Mei";
            case 6:
                return "Jun";
            case 7:
                return "Julai";
            case 8:
                return "Ogos";
            case 9:
                return "September";
            case 10:
                return "Oktober";
            case 11:
                return "November";
            case 12:
                return "Disember";
        }
        return null;
    }

    public Resolution simpan() throws ParseException {
        LOG.info("simpan start");
        String idPermohonanBaru = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanBaru = permohonanDAO.findById(idPermohonanBaru);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("JKBB");

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
        if (kertasBil == null) {
            kertasBil = " - ";
        }
        if (tajuk == null || tajuk.equals("")) {
            tajuk = " - ";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = " - ";
        }
        if (perakuan == null || perakuan.equals("")) {
            perakuan = " - ";
        }
        if (lokasiTanah == null || lokasiTanah.equals("")) {
            lokasiTanah = " - ";
        }
        if (perakuan2 == null || perakuan2.equals("")) {
            perakuan2 = " - ";
        }
        if (maklumat == null || maklumat.equals("")) {
            maklumat = " - ";
        }
        if (bebananBerdafter == null || bebananBerdafter.equals("")) {
            bebananBerdafter = " - ";
        }
        if (suratPersetujuan == null || suratPersetujuan.equals("")) {
            suratPersetujuan = " - ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(lokasiTanah);
        listUlasan.add(maklumat);
        listUlasan.add(perakuan);
        listUlasan.add(perakuan2);
        listUlasan.add(bebananBerdafter);
        listUlasan.add(suratPersetujuan);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add(pejTanah);
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                LOG.info("kemaskini start");

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    kertasK = new PermohonanKertasKandungan();
                    kertasK = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasK.getBil() == 1) {
                        kertasK.setKandungan(tajuk);
                    } else if (kertasK.getBil() == 2) {
                        kertasK.setKandungan(tujuan);
                    } else if (kertasK.getBil() == 3) {
                        kertasK.setKandungan(lokasiTanah);
                    } else if (kertasK.getBil() == 4) {
                        kertasK.setKandungan(maklumat);
                    } else if (kertasK.getBil() == 5) {
                        kertasK.setKandungan(perakuan);
                    } else if (kertasK.getBil() == 6) {
                        kertasK.setKandungan(perakuan2);
                    } else if (kertasK.getBil() == 7) {
                        kertasK.setKandungan(bebananBerdafter);
                    } else if (kertasK.getBil() == 8) {
                        kertasK.setKandungan(suratPersetujuan);
                    }

                    try {
                        LOG.info("--------tarikhMesyuarat--------:" + tarikhMesyuarat);
                        LOG.info("--------masa--------:" + (jam + ":" + minit + " " + pagiPetang));
                        if (tarikhMesyuarat != null && !jam.equals("0") && !minit.equals("0") && !pagiPetang.equals("0")) {
                            masasidang = jam + ":" + minit + " " + pagiPetang;
                            Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                            permohonanKertas.setTarikhSidang(tarikhsidang);
                        }
                    } catch (Exception e) {
                        LOG.info("Exception:" + e);
                        e.printStackTrace();
                    }
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonanBaru);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk(persidangan);
                    permohonanKertas.setNomborRujukan(kertasBil);
                    permohonanKertas.setTempatSidang(tempatsidang);

                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
            LOG.info("kemaskini finish");
        } else {

            LOG.info("simpan new entry");
            for (int i = 0; i < listUlasan.size(); i++) {

                String dataulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);

                kertasK = new PermohonanKertasKandungan();
                try {
                    LOG.info("--------tarikhMesyuarat--------:" + tarikhMesyuarat);
                    LOG.info("--------masa--------:" + (jam + ":" + minit + " " + pagiPetang));
                    if (tarikhMesyuarat != null && !jam.equals("0") && !minit.equals("0") && !pagiPetang.equals("0")) {
                        masasidang = jam + ":" + minit + " " + pagiPetang;
                        Date tarikhsidang = (Date) formatter.parse(tarikhMesyuarat + " " + masasidang);
                        permohonanKertas.setTarikhSidang(tarikhsidang);
                    }
                } catch (Exception e) {
                    LOG.info("Exception:" + e);
                    e.printStackTrace();
                }
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonanBaru);
                permohonanKertas.setKodDokumen(kd);
                permohonanKertas.setTajuk(persidangan);
                permohonanKertas.setNomborRujukan(kertasBil);
                permohonanKertas.setTempatSidang(tempatsidang);

                kertasK.setKertas(permohonanKertas);
                kertasK.setBil(i + 1);
                kertasK.setInfoAudit(infoAudit);
                kertasK.setKandungan(dataulasan);
                kertasK.setSubtajuk(subtajuk);
                kertasK.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kertasK);
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        LOG.info("simpan finish");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kertas_pertimbangan_PTG_rayuanTolak.jsp").addParameter("tab", "true");
    }

//    public Resolution genReport() {
//        LOG.info("genReport :: start");
//        System.out.println("generate report start.");
//        String msg = "Laporan telah dijana. Sila buat semakan sebelum membawanya ke Mesyuarat JKBB.";
//
//        if (StringUtils.isBlank(stageId)) {
//            LOG.error("::Stage Id NULL::");
//            return new StreamingResolution("text/plain", "Ralat BPEL. Sila hubungi Pentadbir Sistem.");
//        }
//
//        try {
//            LOG.info("genReportFromXML");
//            genReportFromXml();
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
//        doc.setTajuk(kd.getKod());
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
//        Dokumen d = null;
//
//
//        if (workflowId != null && stageId != null) {
//            String gen = "DEVSRJKBBPTD_MLK.rdf";
//            String prefix = "VDOC";
//            String code = "JKBBS";
//            String[] params = null;
//            String[] values = null;
//            KodDokumen kd = kodDokumenDAO.findById(code);
//            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//            params = new String[]{"p_id_mohon"};
//            values = new String[]{idPermohonan};
//            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
//            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
//            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//            LOG.info("::Path To save :: " + (dokumenPath + path));
//            LOG.info("::Report Name ::" + gen);
//            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
//            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
//                hakmilikPermohonan.setDokumen1(d);
//            }
//            if (kd.getKod().equals("DHKE")) {
//                hakmilikPermohonan.setDokumen3(d);
//            }
//            if (kd.getKod().equals("DHDE")) {
//                hakmilikPermohonan.setDokumen2(d);
//            }
//            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//        }
//        if (workflowId != null && stageId != null) {
//            String gen = "DEVRJKBBPTD_MLK.rdf";
//            String prefix = "VDOC";
//            String code = "JKBBD";
//            String[] params = null;
//            String[] values = null;
//            KodDokumen kd = kodDokumenDAO.findById(code);
//            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
//            params = new String[]{"p_id_mohon"};
//            values = new String[]{idPermohonan};
//            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//            HakmilikPermohonan hakmilikPermohonan = hk.get(0);
//            d = saveOrUpdateDokumen(fd, kd, idPermohonan);
//            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//            LOG.info("::Path To save :: " + (dokumenPath + path));
//            LOG.info("::Report Name ::" + gen);
//            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
//            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
//            if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
//                hakmilikPermohonan.setDokumen1(d);
//            }
//            if (kd.getKod().equals("DHKE")) {
//                hakmilikPermohonan.setDokumen3(d);
//            }
//            if (kd.getKod().equals("DHDE")) {
//                hakmilikPermohonan.setDokumen2(d);
//            }
//            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//        }
//    }
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

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
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

    public String getMasasidang() {
        return masasidang;
    }

    public void setMasasidang(String masasidang) {
        this.masasidang = masasidang;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public String getTempatsidang() {
        return tempatsidang;
    }

    public void setTempatsidang(String tempatsidang) {
        this.tempatsidang = tempatsidang;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public BPelService getService() {
        return service;
    }

    public void setService(BPelService service) {
        this.service = service;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getNohakmilik() {
        return nohakmilik;
    }

    public void setNohakmilik(String nohakmilik) {
        this.nohakmilik = nohakmilik;
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public Hakmilik getHakmilikSingle() {
        return hakmilikSingle;
    }

    public void setHakmilikSingle(Hakmilik hakmilikSingle) {
        this.hakmilikSingle = hakmilikSingle;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public PermohonanPlotPelan getPlotpelan() {
        return plotpelan;
    }

    public void setPlotpelan(PermohonanPlotPelan plotpelan) {
        this.plotpelan = plotpelan;
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

    public String getPerakuan2() {
        return perakuan2;
    }

    public void setPerakuan2(String perakuan2) {
        this.perakuan2 = perakuan2;
    }

    public int getSaizList() {
        return saizList;
    }

    public void setSaizList(int saizList) {
        this.saizList = saizList;
    }

    public String getMaklumat() {
        return maklumat;
    }

    public void setMaklumat(String maklumat) {
        this.maklumat = maklumat;
    }

    public List<PermohonanRujukanLuar> getUlasanList() {
        return ulasanList;
    }

    public void setUlasanList(List<PermohonanRujukanLuar> ulasanList) {
        this.ulasanList = ulasanList;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setListPengarah(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public PermohonanRujukanLuar getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(PermohonanRujukanLuar ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public PermohonanRujukanLuar getRujukanLuar() {
        return rujukanLuar;
    }

    public void setRujukanLuar(PermohonanRujukanLuar rujukanLuar) {
        this.rujukanLuar = rujukanLuar;
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

    public List<PermohonanLaporanCerun> getFindListlaporCerun() {
        return findListlaporCerun;
    }

    public void setFindListlaporCerun(List<PermohonanLaporanCerun> findListlaporCerun) {
        this.findListlaporCerun = findListlaporCerun;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanBangunanList() {
        return permohonanLaporanBangunanList;
    }

    public void setPermohonanLaporanBangunanList(List<PermohonanLaporanBangunan> permohonanLaporanBangunanList) {
        this.permohonanLaporanBangunanList = permohonanLaporanBangunanList;
    }

    public List<PermohonanLaporanBangunan> getPermohonanLaporanTanamanList() {
        return permohonanLaporanTanamanList;
    }

    public void setPermohonanLaporanTanamanList(List<PermohonanLaporanBangunan> permohonanLaporanTanamanList) {
        this.permohonanLaporanTanamanList = permohonanLaporanTanamanList;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public List<PermohonanPlotPelanBean> getPlotList() {
        return plotList;
    }

    public void setPlotList(List<PermohonanPlotPelanBean> plotList) {
        this.plotList = plotList;
    }

    public int getBilUnitTotal() {
        return bilUnitTotal;
    }

    public void setBilUnitTotal(int bilUnitTotal) {
        this.bilUnitTotal = bilUnitTotal;
    }

    public BigDecimal getKeluasanLotTotal() {
        return keluasanLotTotal;
    }

    public void setKeluasanLotTotal(BigDecimal keluasanLotTotal) {
        this.keluasanLotTotal = keluasanLotTotal;
    }

    public String getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(String kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikBerkepentinganAktif() {
        return listHakmilikBerkepentinganAktif;
    }

    public void setListHakmilikBerkepentinganAktif(List<HakmilikPihakBerkepentingan> listHakmilikBerkepentinganAktif) {
        this.listHakmilikBerkepentinganAktif = listHakmilikBerkepentinganAktif;
    }

    public List<PermohonanPlotPelan> getMppList() {
        return mppList;
    }

    public void setMppList(List<PermohonanPlotPelan> mppList) {
        this.mppList = mppList;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public BigDecimal getLuasTotal() {
        return luasTotal;
    }

    public void setLuasTotal(BigDecimal luasTotal) {
        this.luasTotal = luasTotal;
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

    public String getBebananBerdafter() {
        return bebananBerdafter;
    }

    public void setBebananBerdafter(String bebananBerdafter) {
        this.bebananBerdafter = bebananBerdafter;
    }

    public String getSuratPersetujuan() {
        return suratPersetujuan;
    }

    public void setSuratPersetujuan(String suratPersetujuan) {
        this.suratPersetujuan = suratPersetujuan;
    }

    public List<HakmilikUrusan> getHakUrusan() {
        return hakUrusan;
    }

    public void setHakUrusan(List<HakmilikUrusan> hakUrusan) {
        this.hakUrusan = hakUrusan;
    }

    public Permohonan getPermohonanBaru() {
        return permohonanBaru;
    }

    public void setPermohonanBaru(Permohonan permohonanBaru) {
        this.permohonanBaru = permohonanBaru;
    }
}


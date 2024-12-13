               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKeputusan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pihak;
import etanah.service.BPelService;

import etanah.service.ConsentPtdService;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 * amended by izza -26102010
 */
@UrlBinding("/pelupusan/draf_pertimbangan_jktd")
public class DrafPertimbanganJktdActionBean extends AbleActionBean {

    Logger LOG = Logger.getLogger(DrafPertimbanganJktdActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
//    @Inject
//    RegService regService;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PelupusanUtiliti pelupusanUtiliti;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
//    @Inject
//    BPelService service;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    String latarBelakang;
    String ulasanJabatanKebajikan;
    String ulasanJabatanTenagaKerja;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String display;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPermitItem permitItem;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pihak pihak;
    private String alamatPenuhPihak;
    private String alamatPenuhPhbgn;
    private Pengguna peng;
    private String tujuan;
    private String latarBelakang1;
    private LaporanTanah laporanTanah;
    private PermohonanLaporanPelan permohonanLaporPelan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private PermohonanKertasKandungan kertasK;
    private String sekatKpntgn2;
    private String stageId;
    private String syaratNyata2;
    private String kodSktn;
    private String kod;
    private String index;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan;
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan1;
    private List<PermohonanLaporanKawasan> permohonanLaporKSWList;
    private List<PermohonanLaporanKawasan> senaraiLaporanKawasan;
    private List senaraiLaporanKandunganUtil;
    private KodDokumen kd;
    private String kodHmlk;
    private String kodHmlk1;
    private String jenishakmilik;
    private String kodKategori;
    private FasaPermohonan fasaPermohonan;
    private PermohonanKertas permohonanKertas;
    private KodKeputusan kodKeputusan;
    private String kodKepu;
    private String tajuk1;
    private String taskId;
    private String syor;
    private String syaratNyata;
    private String syaratNyata1;
    private String convNama;
    private String convTempat;
    private String keadaantanah;
    private PermohonanKertasKandungan kertasKandungan;
    private PemohonHubungan pemohonHubungan;
    private String huraianPentadbir;
    private Boolean edit;
    private boolean editPTD;
    private boolean editPTG;
    private boolean openPTG;
    private boolean openPTD;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private PermohonanTuntutanButiran permohonanTuntutanButiran;
    private BigDecimal bayaran;
    private DisLaporanTanahSempadan disLaporanTanahSempadan;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.TRUE;
        editPTG = Boolean.FALSE;
        if (permohonanKertas != null) {
            LOG.info("-------showForm--------senaraiLaporanKandungan1------------------------------" + senaraiLaporanKandungan1);
            senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
            if (senaraiLaporanKandungan1.isEmpty()) {
                senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            }

        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
    }

    public Resolution viewFormForPTD() {
        edit = Boolean.TRUE;
        openPTD = Boolean.TRUE;
        openPTG = Boolean.FALSE;
        editPTD = Boolean.FALSE;
        editPTG = Boolean.FALSE;
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
    }

    public Resolution search() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyata.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/common/searchSekatanNyataKpntngn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatanKepentingan != null) {
            listKodSekatan = pelupusanService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = pelupusanService.searchKodSekatan("%", kc, sekatKpntgn2);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//         stageId(taskId);
        if (kodSyaratNyata != null) {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = pelupusanService.searchKodSyaratNyata("%", kc, syaratNyata2);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        if (pemohon != null) {
            pihak = pemohon.getPihak();
            if (pihak != null) {
                if (pihak.getAlamat1() != null && !("").equals(pihak.getAlamat1())) {
                    alamatPenuhPihak = pihak.getAlamat1();
                }
                if (pihak.getAlamat2() != null && !("").equals(pihak.getAlamat2())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat2();
                }
                if (pihak.getAlamat3() != null && !("").equals(pihak.getAlamat3())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat3();
                }
                if (pihak.getAlamat4() != null && !("").equals(pihak.getAlamat4())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getAlamat4();
                }
                if (pihak.getPoskod() != null && !("").equals(pihak.getPoskod())) {
                    alamatPenuhPihak = alamatPenuhPihak + ", " + pihak.getPoskod();
                }
                if (pihak.getNegeri() != null) {
                    alamatPenuhPihak = alamatPenuhPihak + " " + pihak.getNegeri().getNama();
                }
            }
        }
        permitItem = pelupusanService.findByIdMohonPermitItem(idPermohonan);
        permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findPermohonanRujByIdPermohonan(idPermohonan);
//        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
//REMOVE
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            stageId = "15TrmLapSdDrafJKTD";
        } else if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            stageId = "04PmlhnPsrta";
        } else {
            stageId = "draf_jktd"; // must get since to get kodkeputusan jktd
        }

//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        stageId = stageId(taskId);
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        pemohonHubungan = pelupusanService.findHubunganByIDSuamiIsteri(pemohon.getIdPemohon());
        if (pemohonHubungan != null) {
            if (pemohonHubungan.getAlamat1() != null && !("").equals(pemohonHubungan.getAlamat1())) {
                alamatPenuhPhbgn = pemohonHubungan.getAlamat1();
            }
            if (pemohonHubungan.getAlamat2() != null && !("").equals(pemohonHubungan.getAlamat2())) {
                alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat2();
            }
            if (pemohonHubungan.getAlamat3() != null && !("").equals(pemohonHubungan.getAlamat3())) {
                alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat3();
            }
            if (pemohonHubungan.getAlamat4() != null && !("").equals(pemohonHubungan.getAlamat4())) {
                alamatPenuhPhbgn = alamatPenuhPhbgn + ", " + pemohonHubungan.getAlamat4();
            }
        }
        senaraiLaporanKawasan = pelupusanService.findLaporanKawasanByIdPermohonanNKodRizab(idPermohonan);
        LOG.info("-------------fasaPermohonan---------------------" + fasaPermohonan);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            fasaPermohonan.getKeputusan().getNama();
        }
        if (hakmilikPermohonan != null) {
            System.out.println("idPermohonan : " + idPermohonan);
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
                syaratNyata = hakmilikPermohonan.getSyaratNyataBaru().getSyarat();
            }
            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
                syaratNyata1 = hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan();
            }
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                kodKategori = hakmilikPermohonan.getKategoriTanahBaru().getKod();
            }
            if (hakmilikPermohonan.getKodHakmilikSementara() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilikSementara().getKod();
            }
            if (hakmilikPermohonan.getKodHakmilikTetap() != null) {
                kodHmlk1 = hakmilikPermohonan.getKodHakmilikTetap().getKod();
            }
        }
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<LaporanTanah> listLaporanTanah;
        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

        if (!(listLaporanTanah.isEmpty())) {
            laporanTanah = listLaporanTanah.get(0);
        }

        String ktanah = "";
        String sbb = "";
        String namaP = "";
        String kodUnitLuas = "";
        String kodUnitLuas2 = "";
        String lokasi = "";
        String ursan = "";
        String bpm = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        BigDecimal luas2 = BigDecimal.ZERO;
        String jarak = "";
        String unitJarak = "";
        String jarakDari = "";
        String kws = "";
        String ursan1 = "";


        if (permohonan.getSebab() != null) {
            sbb = permohonan.getSebab();
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan = " Untuk memiliki tanah Kerajaan secara hakmilik tetap.";
        }
        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan1 = " MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965.";
        }
//        if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
//            ursan = " Untuk memiliki tanah.";
//            ursan1 = " MEMILIKI TANAH.";
//        }
        if (pemohon != null) {
            if (pemohon.getPihak() != null) {
                namaP = pemohon.getPihak().getNama();
            }
        }
        if (permohonanLaporPelan != null) {
            if (permohonanLaporPelan.getKawasanPihakBerkuasa() != null) {
                kws = permohonanLaporPelan.getKawasanPihakBerkuasa();
            }
        }
        if (hakmilikPermohonan != null) {
            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                    if (hakmilikPermohonan.getKedalamanTanahUOM() != null) {
                        kodUnitLuas2 = hakmilikPermohonan.getKedalamanTanahUOM().getNama();
                        luas2 = hakmilikPermohonan.getKedalamanTanah();
                    }
                }
            }
            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                kodUnitLuas = hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getLokasi() != null) {
                lokasi = hakmilikPermohonan.getLokasi();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }
            if (hakmilikPermohonan.getJarak() != null) {
                jarak = hakmilikPermohonan.getJarak();
            }
            if (hakmilikPermohonan.getUnitJarak() != null) {
                unitJarak = hakmilikPermohonan.getUnitJarak().getNama();
            }
            if (hakmilikPermohonan.getJarakDari() != null) {
                jarakDari = hakmilikPermohonan.getJarakDari();
            }
        }
        if (permitItem != null) {
            sbb = permitItem.getKodItemPermit().getNama();
        }
        if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
            if (conf.getProperty("kodNegeri").equals("05")) {
                tajuk1 = "\n" + "PERMOHONAN DARIPADA " + namaP + " UNTUK PEMBERIMILIKAN TANAH BAWAH TANAH" + ursan1 + "\n"
                        + "  " + "YANG BERISIPADU " + luas + " " + kodUnitLuas + " DENGAN KEDALAMAN " + luas2 + " " + kodUnitLuas2 + "\n"
                        + "  " + "DI " + lokasi + "," + bpm + "," + "DAERAH " + daerah + "\n"
                        + "  " + "UNTUK TUJUAN " + sbb + " UNTUK " + permohonan.getKodUrusan().getNama() + ".";

                Long idlapor = laporanTanah.getIdLaporan();
                LOG.info("id lapor....." + idlapor);
                if (laporanTanah.getKodKeadaanTanah().getKod().equals("LL")) {
                    PermohonanLaporanKandungan mlk = pelupusanService.findByIdLaporSubtajuk("Lain-lain Keadaan Tanah", idlapor);
                    keadaantanah = mlk.getKand();
                }
            } else {
                tajuk1 = "\n" + "PERMOHONAN DARIPADA " + namaP + " UNTUK MEMILIKI TANAH" + ursan1 + "\n"
                        + "  " + "SELUAS LEBIH KURANG " + luas + " " + kodUnitLuas + "\n"
                        + "  " + "DI " + lokasi + "," + bpm + "," + "DAERAH " + daerah + "\n"
                        + "  " + "UNTUK TUJUAN " + sbb + ".";
            }
        } else {
            tajuk1 = "\n" + "PERMOHONAN DARIPADA " + namaP + " UNTUK MEMILIKI TANAH" + ursan1 + "\n"
                    + "  " + "SELUAS LEBIH KURANG " + luas + " " + kodUnitLuas + "\n"
                    + "  " + "DI " + lokasi + "," + bpm + "," + "DAERAH " + daerah + "\n"
                    + "  " + "UNTUK TUJUAN " + sbb + ".";
        }



        tujuan = "\n" + "Tujuan kertas ini disediakan i = alah untuk  mendapatkan pertimbangan Jawatankuasa Tanah Daerah Seremban bagi permohonan daripada :- " + "\n"
                + " " + " i)" + namaP + "." + "\n"
                + " " + " ii)" + ursan + "\n"
                + " " + " iii)" + " Seluas lebihkurang" + luas + " " + kodUnitLuas + "." + "\n"
                + " " + " iv)" + "Di" + lokasi + "," + "Mukim" + bpm + "," + "Daerah" + daerah + "." + "\n"
                + " " + " v)" + "Untuk kegunaan " + sbb + ".";


        latarBelakang1 = "\n" + "Tanah yang dimaksudkan itu seperti bertanda merah di dalam pelan letaknya :-" + "\n"
                + " " + " i) Mukim :" + bpm + "." + "\n"
                + " " + " ii)" + lokasi + "." + "\n"
                + " " + " iii)" + "Lebihkurang" + jarak + " " + unitJarak + "dari" + " " + jarakDari + "." + "\n"
                + " " + " iv)" + "Jenis Tanah Desa" + "." + "\n"
                + " " + " v)" + kws + "Kawasan Rizab Melayu" + "." + "\n"
                + " " + " vi)" + kws + "Kawasan GSA" + "." + "\n"
                + " " + " vii)" + kws + "Kawasan GSA" + ".";

        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "JKTD");

        LOG.info("---------rehydrate-----permohonankertas--------------::" + permohonanKertas);
//        if (permohonanKertas != null) {
//            if(permohonanKertas.getSenaraiKandungan()!=null){
//                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
//                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
//
//                    if (kertasKandungan.getBil() == 0) {
//                        tajuk1 = kertasKandungan.getKandungan();
//                    }else  if (kertasKandungan.getBil() == 1) {
//                        tujuan = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("2.1")) {
//                        latarBelakang1 = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getSubtajuk() != null && kertasKandungan.getSubtajuk().equals("4.1")) {
//                        syor = kertasKandungan.getKandungan();
//                    }
//
//                }
//            }            
//        }
        if (permohonanKertas != null) {
            senaraiLaporanKandunganUtil = new Vector();
            //senaraiLaporanKandungan2 = pelupusanService.findByIdLapor(permohonankertas.getIdKertas(), 3);
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiPermohonanRujLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan);
            for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                //PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();
                //kand1 = senaraiLaporanKandungan2.get(i);
                PermohonanRujukanLuar kand1 = new PermohonanRujukanLuar();
                kand1 = senaraiPermohonanRujLuar.get(i);
                PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                pelUtil.setPermohonanRujukanLuar(kand1);
                if (kand1.getAgensi().getKategoriAgensi().getKod().equals("JTK")) {
                    senaraiLaporanKandunganUtil.add(pelUtil);
                }
            }
        } else {
            List<PermohonanRujukanLuar> senaraiPermohonanRujLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiLaporanKandunganUtil = new Vector();
            senaraiPermohonanRujLuar = pelupusanService.senaraiPermohonanRujLuarByIdPermohonan(idPermohonan);
            if (!senaraiPermohonanRujLuar.isEmpty()) {
                for (int i = 0; i < senaraiPermohonanRujLuar.size(); i++) {
                    PermohonanRujukanLuar rujLuar = senaraiPermohonanRujLuar.get(i);
                    PermohonanKertasKandungan kand1 = new PermohonanKertasKandungan();

                    PelupusanUtiliti pelUtil = new PelupusanUtiliti();
                    if (rujLuar.getUlasan() == null) {
                        rujLuar.setUlasan("Tiada ulasan diterima");
                    }
                    pelUtil.setPermohonanRujukanLuar(rujLuar);
                    //kand1.setKandungan(kandVal);
                    //kand1.setSubtajuk(rujLuar.getAgensi().getNama());
                    if (rujLuar.getAgensi().getKategoriAgensi().getKod().equals("JTK")) //senaraiLaporanKandungan2.add(kand1);
                    {
                        senaraiLaporanKandunganUtil.add(pelUtil);
                    }
                }
            }


        }
        System.out.println("--------------permohonanKertas--------:" + permohonanKertas);
        if (permohonanKertas != null) {
            senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 4);

            if (senaraiLaporanKandungan1.isEmpty()) {
                senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
            }
        } else {
            senaraiLaporanKandungan1 = new ArrayList<PermohonanKertasKandungan>();
        }
        if (permohonanKertas != null) {
            List senaraiLaporanKandunganTolak = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganTolak = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
            if (!senaraiLaporanKandunganTolak.isEmpty()) {
                PermohonanKertasKandungan mkk = new PermohonanKertasKandungan();
                mkk = (PermohonanKertasKandungan) senaraiLaporanKandunganTolak.get(0);
                syor = mkk.getKandungan();
            }
        }
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            kodKepu = fasaPermohonan.getKeputusan().getKod();
        }

        LOG.info("----------Kod hakmilik----------::" + kodHmlk);
        convNama = pelupusanUtiliti.convertStringtoInitCap(pihak.getNama());
        if (!hakmilikPermohonan.getLokasi().isEmpty()) {
            convTempat = pelupusanUtiliti.convertStringtoInitCap(hakmilikPermohonan.getLokasi());
        }

        permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
        if (permohonanTuntutanKos != null) {
            bayaran = permohonanTuntutanKos.getAmaunTuntutan();
        }

        disLaporanTanahSempadan = new DisLaporanTanahSempadan();
        disLaporanTanahSempadan.setListLaporTanahSpdnU(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnB(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnS(new ArrayList());
        disLaporanTanahSempadan.setListLaporTanahSpdnT(new ArrayList());
        LaporanTanah laporanTanah = new LaporanTanah();
        laporanTanah = (LaporanTanah) disLaporanTanahService.findObject(laporanTanah, new String[]{String.valueOf(hakmilikPermohonan.getId())}, 0);
        if (laporanTanah != null) {
            List<LaporanTanahSempadan> listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "U"); // UTARA
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'U', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnU().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "S"); // SELATAN
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'S', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnS().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "T"); // TIMUR
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'T', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnT().add(ls);
            }
            listLaporTanahSpdnTemp = new ArrayList<LaporanTanahSempadan>();
            listLaporTanahSpdnTemp = disLaporanTanahService.getPlpservice().findLaporTanahSmpdnByIdLaporNJSmpdn(laporanTanah.getIdLaporan(), "B"); // BARAT
            for (LaporanTanahSempadan lts : listLaporTanahSpdnTemp) {
                LotSempadan ls = new LotSempadan();
                ls.setLaporanTanahSmpdn(lts);
                ls.setListImejLaporan(disLaporanTanahService.getLaporanTanahService().getLaporImejByPndgnImejNlaporTnhSmpdn(laporanTanah, 'B', lts));
                disLaporanTanahSempadan.getListLaporTanahSpdnB().add(ls);
            }
        }
    }

    public Resolution simpan() {

        LOG.info("------------Simpan started-----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("------------idPermohonan-----------::" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("JKTD");
        LOG.info("----------Kod Dokumen----------::" + kd);
        permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "JKTD");
        LOG.info("-------Simpan-------permohonankertas--------------::");


        String ktanah = "";
        String sbb = "";
        String namaP = "";
        String kodUnitLuas = "";
        String lokasi = "";
        String bpm = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String ursan1 = "";


        if (permohonan.getSebab() != null) {
            sbb = permohonan.getSebab();
        }

        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            ursan1 = " MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965.";
        }
        if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            ursan1 = " MEMILIKI TANAH DI KAWASAN PENEMPATAN BERKELOMPOK DI BAWAH SEKSYEN 44 AKTA TANAH (KAWASAN-KAWASAN PENEMPATAN BERKELOMPOK) 1960.";
        }
        if (pemohon != null) {
            if (pemohon.getPihak() != null) {
                namaP = pemohon.getPihak().getNama();
            }
        }

        if (hakmilikPermohonan != null) {
            if (hakmilikPermohonan.getLuasTerlibat() != null) {
                luas = hakmilikPermohonan.getLuasTerlibat();
            }
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                kodUnitLuas = hakmilikPermohonan.getKodUnitLuas().getNama();
            }
            if (hakmilikPermohonan.getLokasi() != null) {
                lokasi = hakmilikPermohonan.getLokasi();
            }

            if (hakmilikPermohonan.getBandarPekanMukimBaru() != null) {
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }

            if (peng.getKodCawangan() != null && peng.getKodCawangan().getDaerah() != null) {
                daerah = peng.getKodCawangan().getDaerah().getNama();
            }

            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                ktanah = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }

        }

        tajuk1 = "\n" + "PERMOHONAN DARIPADA" + namaP + "UNTUK" + ursan1 + "\n"
                + "  " + "SELUAS LEBIHKURANG" + luas + " " + kodUnitLuas + "\n"
                + "  " + "DI" + lokasi + "," + "MUKIM" + bpm + "," + "DAERAH" + daerah + "\n"
                + "  " + "UNTUK TUJUAN" + ktanah + " " + sbb + ".";




/// fasa permohon
        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            stageId = "15TrmLapSdDrafJKTD";
        } else {
            stageId = "draf_jktd";
        }

        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan == null) {
            LOG.info("------------if----FasaPermohonan--------------");
            fasaPermohonan = new FasaPermohonan();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        } else {
            LOG.info("------------else------FasaPermohonan------------");
            infoAudit = fasaPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        fasaPermohonan.setInfoAudit(infoAudit);
        fasaPermohonan.setIdPengguna(peng.getIdPengguna());
        fasaPermohonan.setCawangan(peng.getKodCawangan());
        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setIdAliran(stageId);
        fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(kodKepu));

        if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            if (syor != null) {
                fasaPermohonan.setUlasan(syor);
            }
        }
        if (permohonan.getKodUrusan().getKod().equals("PBGSA")) {
            if (syor != null) {
                fasaPermohonan.setUlasan(syor);
            }
        }
        pelupusanService.simpanFasaPermohonan(fasaPermohonan);

/// hakmilik permohonan

        HakmilikPermohonan hakmilikPermohonanTemp = new HakmilikPermohonan();
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBMT")) {
                    hakmilikPermohonanTemp = pelupusanService.findByIdPermohonan(idPermohonan);
                    if (hakmilikPermohonanTemp != null) {
                        infoAudit = hakmilikPermohonanTemp.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    } else {
                        hakmilikPermohonanTemp = new HakmilikPermohonan();
                        infoAudit.setDimasukOleh(peng);
                        infoAudit.setTarikhMasuk(new java.util.Date());
                    }

                    if (kodHmlk != null) {
                        KodHakmilik khm = kodHakmilikDAO.findById(kodHmlk);
                        hakmilikPermohonanTemp.setKodHakmilikSementara(khm);
                        LOG.info("----------Kod hakmilik----------::" + hakmilikPermohonanTemp.getKodHakmilikSementara());
                    }
                    if (kodHmlk1 != null) {
                        KodHakmilik khm1 = kodHakmilikDAO.findById(kodHmlk1);
                        hakmilikPermohonanTemp.setKodHakmilikTetap(khm1);
                    }
                    if (kod != null) {
                        KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
                        hakmilikPermohonanTemp.setSyaratNyataBaru(kodSyarat);
                    }

                    if (kodSktn != null) {
                        KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
                        hakmilikPermohonanTemp.setSekatanKepentinganBaru(sekatan);
                    }

                    if (jenishakmilik != null) {
                        KodHakmilik jenh = kodHakmilikDAO.findById(jenishakmilik);
                        hakmilikPermohonanTemp.setKodHakmilik(jenh);
                    }
                    hakmilikPermohonanTemp.setInfoAudit(infoAudit);
                    hakmilikPermohonanTemp.setPermohonan(permohonan);

                    if (kodSktn != null && !("").equals(kodSktn)) {
                        hakmilikPermohonanTemp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(kodSktn));
                    }
                    if (kod != null && !("").equals(kod)) {
                        hakmilikPermohonanTemp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(kod));
                    }
                    hakmilikPermohonanTemp.setTempohHakmilik(hakmilikPermohonan.getTempohHakmilik());
                    //hakmilikPermohonanTemp.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
                    //hakmilikPermohonanTemp.setCatatan(hakmilikPermohonan.getCatatan());
                    pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);
                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPTD") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLPSK")) {
                    KodTransaksi kt = new KodTransaksi();
                    kt.setKod("12101");
                    InfoAudit ia = new InfoAudit();
                    permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISB4A");
                    if (permohonanTuntutanKos == null) {

                        permohonanTuntutanKos = new PermohonanTuntutanKos();
                        // ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanTuntutanKos.setPermohonan(permohonan);
                        permohonanTuntutanKos.setAmaunTuntutan(bayaran);
                        permohonanTuntutanKos.setInfoAudit(ia);
                        permohonanTuntutanKos.setKodTransaksi(kt);
                        permohonanTuntutanKos.setCawangan(peng.getKodCawangan());
                        permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
                        permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonanTuntutanKos);
                    } else {
                        ia = permohonanTuntutanKos.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        permohonanTuntutanKos.setPermohonan(permohonan);
                        permohonanTuntutanKos.setAmaunTuntutan(bayaran);
                        permohonanTuntutanKos.setKodTransaksi(kt);
                        permohonanTuntutanKos.setCawangan(peng.getKodCawangan());
                        permohonanTuntutanKos.setInfoAudit(ia);
                        permohonanTuntutanKos.setKodTuntut(kodTuntutDAO.findById("DISB4A"));
                        permohonanTuntutanKos.setItem(kodTuntutDAO.findById("DISB4A").getNama());
                        pelupusanService.simpanPermohonanTuntutanKos1(permohonanTuntutanKos);
                    }

                    PermohonanTuntutan permohonanTuntutan;
//             permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "Bayaran Borang 4A (LPS)");
                    permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "DIS4A");

                    if (permohonanTuntutan != null) {
                        LOG.info("-------Mohonan Tuntutan Not Null-------------------");
                        ia = permohonanTuntutan.getInfoAudit();
                        ia.setTarikhKemaskini(new java.util.Date());
                        ia.setDiKemaskiniOleh(peng);
                        permohonanTuntutan.setInfoAudit(ia);
                        pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
                    } else {

                        LOG.info("-------Mohonan Tuntutan is Null-------------------");

                        Calendar c = Calendar.getInstance();
                        LOG.info("-------Todays Date::-------------------" + c);
                        Date date = new Date();
                        c.add(Calendar.MONTH, 3);
                        LOG.info("-------After 3 months Date::-------------------" + c);
                        date = c.getTime();
                        LOG.info("-------After 3 months Date::-----date--------------" + date);



                        KodCawangan test = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
                        permohonanTuntutan = new PermohonanTuntutan();
                        permohonanTuntutan.setCawangan(permohonan.getCawangan());
                        permohonanTuntutan.setPermohonan(permohonan);
                        ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                        permohonanTuntutan.setInfoAudit(ia);
                        KodTransaksiTuntut kodTransTuntut = kodTransaksiTuntutDAO.findById("DIS4A");
                        permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                        permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                        permohonanTuntutan.setTarikhAkhirBayaran(date);
                        pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
                    }


                    // added new code

                    LOG.info("-------Mohonantuntan Butiran Saving--------------------");
                    permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonanTuntutanKos.getIdKos(), permohonanTuntutan.getIdTuntut());
                    if (permohonanTuntutanButiran != null) {

                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        permohonanTuntutanButiran.setInfoAudit(ia);

                    } else {
                        PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
                        permohonanTuntutanButiran.setPermohonanTuntutan(permohonanTuntutan);
                        permohonanTuntutanButiran.setPermohonanTuntutanKos(permohonanTuntutanKos);
                        permohonanTuntutanButiran.setInfoAudit(ia);
                        pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
                    }
                }
            }
        }

        ArrayList listUlasan = new ArrayList();
        ArrayList<String> listSubtajuk = new ArrayList<String>();
        ArrayList<String> billNo = new ArrayList<String>();
        LOG.info("------------tajuk----------------------" + tajuk1);
        if (tajuk1 == null || tajuk1.equals("")) {
            tajuk1 = "TIADA DATA";
        }

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA";
        }
        if (latarBelakang1 == null || latarBelakang1.equals("")) {
            latarBelakang1 = "TIADA DATA";
        }
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());
        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("PBGSA") || permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("RLPS") || permohonan.getKodUrusan().getKod().equals("RLPSK")) {
            permohonanKertas.setTajuk("Draf JKTD");
            KodDokumen kod = kodDokumenDAO.findById("JKTD");
            permohonanKertas.setKodDokumen(kod);
        }
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonanKertas);

//        if (syor == null || syor.equals("")) {
//            syor = "TIADA DATA";
//        }
//
//
////        listUlasan.add(tajuk1);
////        listUlasan.add(tujuan);
////        listUlasan.add(latarBelakang1);
//        if(syor!=null&&!("").equals(syor)){
//            updateKandungan(4, syor);
////            listUlasan.add(syor);
////            listSubtajuk.add("1");
////            billNo.add("5");
//        }
//
//        listSubtajuk.add("");
//        listSubtajuk.add("1.1");
//        listSubtajuk.add("2.1");
//        listSubtajuk.add("5.1");

//        billNo.add("0");
//        billNo.add("1");
//        billNo.add("2");
//        billNo.add("4");

//        if (permohonanKertas != null) {
//            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
//            infoAudit = permohonanKertas.getInfoAudit();
//            infoAudit.setDiKemaskiniOleh(peng);
//            infoAudit.setTarikhKemaskini(new java.util.Date());
//            pelupusanService.simpanPermohonanKertas(permohonanKertas);
//        }else {
//            LOG.info("------else------permohonankertas:: NULL--------------::" + permohonanKertas);
//
//            permohonanKertas = new PermohonanKertas();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            permohonanKertas.setInfoAudit(infoAudit);
//            permohonanKertas.setPermohonan(permohonan);
//            permohonanKertas.setCawangan(permohonan.getCawangan());
//            permohonanKertas.setTajuk("Kertas JKTD");
//            kd = kodDokumenDAO.findById("JKTD");
//            permohonanKertas.setKodDokumen(kd);
//            pelupusanService.simpanPermohonanKertas(permohonanKertas);
//        }


        //  senaraiLaporanKandungan1 = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(),4);

//        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

//        for (int i = 1; i <= kira; i++) {
//            PermohonanKertasKandungan permohonanKertasKandungan1=new PermohonanKertasKandungan();
//            if (senaraiLaporanKandungan1.size() != 0 && i <= senaraiLaporanKandungan1.size()) {
//                Long id = senaraiLaporanKandungan1.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan1 = pelupusanService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan1.setKertas(permohonanKertas);
//            permohonanKertasKandungan1.setBil(3);
//            String kandungan = getContext().getRequest().getParameter("kandungan3" + i);
//            permohonanKertasKandungan1.setKandungan(kandungan);
//            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan1.setSubtajuk("3." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan1.setInfoAudit(iaP);
//            pelupusanService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
//           
//        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("popup", "true");

    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
//            case 1:
//                break;
//            case 2:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 2);
//                senaraiLaporanKandungan1.add(pkk);
//                break;
//            case 3:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 3);
//                listKertasHuraianPTD.add(pkk);
//                break;
            case 4: //FOR HURAIAN PENTADBIR TANAH 
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                senaraiLaporanKandungan1.add(pkk);
                break;
//            case 5:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 5);
//                listKertasPerakuanPTG.add(pkk);
//                break;
//            case 6:
//                pkk = new PermohonanKertasKandungan();
//                pkk.setBil((short) 6);
//                listKertasKeputusanPTG.add(pkk);
//                break;
//              case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 7);
//                  senaraiLaporanKandunganpbtanah.add(pkk);
//                  break;
//              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 8);
//                  senaraiLaporanKandunganptg1.add(pkk);
//                  break;
//              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                  pkk = new PermohonanKertasKandungan();
//                  pkk.setBil((short) 9);
//                  senaraiLaporanKandunganptg2.add(pkk);
//                  break;   
            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
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
                    pelPtService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());


        if (permohonanKertas != null) {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        if (permohonan.getKodUrusan().getKod().equals("PBGSA") || permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("RLPSK") || permohonan.getKodUrusan().getKod().equals("RLPS")) {
            permohonanKertas.setTajuk("Draf JKTD");
            KodDokumen kod = kodDokumenDAO.findById("JKTD");
            permohonanKertas.setKodDokumen(kod);
        }
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        pelPtService.simpanPermohonanKertas(permohonanKertas);

        long a = permohonanKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);

        if (i == 5) {
            pLK = pelupusanService.findByBilNIdKertas(5, permohonanKertas.getIdKertas());
            if (pLK == null) {
                pLK = new PermohonanKertasKandungan();
                pLK.setKertas(permohonanKertas);
                pLK.setInfoAudit(infoAudit);
                pLK.setCawangan(cawangan);
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setSubtajuk("1");
        } else {
            if (plk.isEmpty()) {
                pLK.setSubtajuk("1");
//            LOG.info("PLK" + pLK.getSubtajuk());
            } else {
                int n = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk()) + 1;
                //    int test = Integer.parseInt(plk.get(plk.size() - 1).getSubtajuk().substring(2, 3)) + 1;

                pLK.setSubtajuk(String.valueOf(n));
            }
            pLK.setBil((short) i);
            pLK.setKandungan(kand);
            pLK.setKertas(permohonanKertas);
            pLK.setInfoAudit(infoAudit);
            pLK.setCawangan(cawangan);
        }


        if (i == 5) {
            List senaraiLaporanKandunganTolak = new ArrayList<PermohonanKertasKandungan>();
            senaraiLaporanKandunganTolak = pelupusanService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
            if (senaraiLaporanKandunganTolak.isEmpty()) {
                pelPtService.simpanPermohonanKertasKandungan(pLK);
            } else {
                pelPtService.simpanUpdateOnlyPermohonanKertasKandungan(pLK);
            }
        } else {
            pelPtService.simpanPermohonanKertasKandungan(pLK);
        }

    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1:

                break;
//            case 2: //FOR Perihal Permohonan
//                updateKandungan(2, kand);
//
//                break;
//            case 3:
//
//                updateKandungan(3, kand);
//
//                break;
            case 4:  // FOR HURAIAN PENTADBIR TANAH DAERAH 

                updateKandungan(4, kand);

                break;
//            case 5:
//
//                updateKandungan(5, kand);
//
//                break;
//            case 6:
//
//                updateKandungan(6, kand);
//                break;
//              case 7: // FOR PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH
//                  updateKandungan(7, kand);
//                  break;
//              case 8: // FOR HURAIAN PENGARAH TANAH DAN GALIAN
//                  updateKandungan(8, kand);
//                  break;
//              case 9: // FOR SYOR PENGARAH TANAH DAN GALIAN
//                  updateKandungan(9, kand);
//                  break;
//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", edit);
        getContext().getRequest().setAttribute("openPTG", openPTG);
        getContext().getRequest().setAttribute("openPTD", openPTD);
        getContext().getRequest().setAttribute("editPTG", editPTG);
        getContext().getRequest().setAttribute("editPTD", editPTD);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/draf_pertimbangan_jktd2.jsp").addParameter("tab", "true");
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

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getUlasanJabatanKebajikan() {
        return ulasanJabatanKebajikan;
    }

    public void setUlasanJabatanKebajikan(String ulasanJabatanKebajikan) {
        this.ulasanJabatanKebajikan = ulasanJabatanKebajikan;
    }

    public String getUlasanJabatanTenagaKerja() {
        return ulasanJabatanTenagaKerja;
    }

    public void setUlasanJabatanTenagaKerja(String ulasanJabatanTenagaKerja) {
        this.ulasanJabatanTenagaKerja = ulasanJabatanTenagaKerja;
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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getLatarBelakang1() {
        return latarBelakang1;
    }

    public void setLatarBelakang1(String latarBelakang1) {
        this.latarBelakang1 = latarBelakang1;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohonanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
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

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan() {
        return senaraiLaporanKandungan;
    }

    public void setSenaraiLaporanKandungan(List<PermohonanKertasKandungan> senaraiLaporanKandungan) {
        this.senaraiLaporanKandungan = senaraiLaporanKandungan;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getKodKategori() {
        return kodKategori;
    }

    public void setKodKategori(String kodKategori) {
        this.kodKategori = kodKategori;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodKepu() {
        return kodKepu;
    }

    public void setKodKepu(String kodKepu) {
        this.kodKepu = kodKepu;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List<PermohonanKertasKandungan> senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getKodHmlk1() {
        return kodHmlk1;
    }

    public void setKodHmlk1(String kodHmlk1) {
        this.kodHmlk1 = kodHmlk1;
    }

    public String getTajuk1() {
        return tajuk1;
    }

    public void setTajuk1(String tajuk1) {
        this.tajuk1 = tajuk1;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public PermohonanPermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermohonanPermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public List<PermohonanLaporanKawasan> getPermohonanLaporKSWList() {
        return permohonanLaporKSWList;
    }

    public void setPermohonanLaporKSWList(List<PermohonanLaporanKawasan> permohonanLaporKSWList) {
        this.permohonanLaporKSWList = permohonanLaporKSWList;
    }

    public List<PermohonanLaporanKawasan> getSenaraiLaporanKawasan() {
        return senaraiLaporanKawasan;
    }

    public void setSenaraiLaporanKawasan(List<PermohonanLaporanKawasan> senaraiLaporanKawasan) {
        this.senaraiLaporanKawasan = senaraiLaporanKawasan;
    }

    public PemohonHubungan getPemohonHubungan() {
        return pemohonHubungan;
    }

    public void setPemohonHubungan(PemohonHubungan pemohonHubungan) {
        this.pemohonHubungan = pemohonHubungan;
    }

    public String getAlamatPenuhPihak() {
        return alamatPenuhPihak;
    }

    public void setAlamatPenuhPihak(String alamatPenuhPihak) {
        this.alamatPenuhPihak = alamatPenuhPihak;
    }

    public String getAlamatPenuhPhbgn() {
        return alamatPenuhPhbgn;
    }

    public void setAlamatPenuhPhbgn(String alamatPenuhPhbgn) {
        this.alamatPenuhPhbgn = alamatPenuhPhbgn;
    }

    public List getSenaraiLaporanKandunganUtil() {
        return senaraiLaporanKandunganUtil;
    }

    public void setSenaraiLaporanKandunganUtil(List senaraiLaporanKandunganUtil) {
        this.senaraiLaporanKandunganUtil = senaraiLaporanKandunganUtil;
    }

    public boolean isEditPTD() {
        return editPTD;
    }

    public void setEditPTD(boolean editPTD) {
        this.editPTD = editPTD;
    }

    public boolean isEditPTG() {
        return editPTG;
    }

    public void setEditPTG(boolean editPTG) {
        this.editPTG = editPTG;
    }

    public boolean isOpenPTD() {
        return openPTD;
    }

    public void setOpenPTD(boolean openPTD) {
        this.openPTD = openPTD;
    }

    public boolean isOpenPTG() {
        return openPTG;
    }

    public void setOpenPTG(boolean openPTG) {
        this.openPTG = openPTG;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public String getConvNama() {
        return convNama;
    }

    public void setConvNama(String convNama) {
        this.convNama = convNama;
    }

    public String getConvTempat() {
        return convTempat;
    }

    public void setConvTempat(String convTempat) {
        this.convTempat = convTempat;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public BigDecimal getBayaran() {
        return bayaran;
    }

    public void setBayaran(BigDecimal bayaran) {
        this.bayaran = bayaran;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public DisLaporanTanahSempadan getDisLaporanTanahSempadan() {
        return disLaporanTanahSempadan;
    }

    public void setDisLaporanTanahSempadan(DisLaporanTanahSempadan disLaporanTanahSempadan) {
        this.disLaporanTanahSempadan = disLaporanTanahSempadan;
    }

    public String getKeadaantanah() {
        return keadaantanah;
    }

    public void setKeadaantanah(String keadaantanah) {
        this.keadaantanah = keadaantanah;
    }
}


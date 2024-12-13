/*
 * To change this template, choose Tools | Templates
 * and open the template in the .creator (???).
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanPtService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author user
 */
@UrlBinding("/pelupusan/syaratTambahan")
public class SyaratTambahanPCRGActionBean extends AbleActionBean {

    Logger logger = Logger.getLogger(DrafJKSMNActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanUtiliti pelUtiliti;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    HakmilikPermohonanDAO mohonHakmilikDAO;
    @Inject
    PelupusanPtService pelPtService;
    @Inject
    PelupusanService pservice;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private KodCawangan cawangan;
    private Pengguna peng;
    private String stageId;
    private String tajukUtama;
    private String tajuk;
    private String tajuk2;
    private String tajuk3;
    private String tajuk4;
//    Logger logger = Logger.getLogger(DrafJKMActionBean.class);
    private String tujuan;
    private String tujuan2;
    private String tujuan3;
    private String tujuan4;
    private String tujuan5;
    private String mukimNama;
    private String milikNama;
    private String perihaltanah1;
    private String perihaltanah12;
    private String perihaltanah13;
    private String perihaltanah14;
    private String perihaltanah15;
    private String perihaltanah16;
    private String perihaltanah17;
    private String perihaltanah2;
    private String perihaltanah21;
    private String perihaltanah22;
    private String tajukPerihalPermohonan;
    private String perakuan;
    private String ulasan;
    private String ulasan2;
    private String ulasan3;
    private String persidangan;
    private Date tarikh;
    private String masa;
    private String tempat;
    private String tempoh;
    private String kadarBayar;
    private String kadarBayar2;
    private String jumlahBayar;
    private String jumlahBayar2;
    private String jumlahBayar3;
    private String wangCagar;
    private String wangCagar2;
    private String noktah;
    private String idPermohonan;
    private String idPemohon;
    private String noktahbertindih;
    private Long x;
    private String kodNeg;
    private Permohonan permohonan;
    private PermohonanRujukanLuar mohonRujukLuar;
    private PermohonanRujukanLuar mohonRujukLuarPTD;
    private String urusanStatus;
    private String drafDaerah;
    private String tajukHeader;
    private boolean openPTD = false;
    private boolean openPTG = false;
    private boolean viewOnlyPTD = true;
    private boolean viewOnlyPTG = true;
    private Boolean edit;
    private Boolean viewForMMKN = false;
    private List<PihakPengarah> pihakPengarahList;
    private HakmilikPermohonan mohonHakmilik;
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalPermohonan = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandungan2 = new Vector();
    private List<PermohonanRujukanLuar> senaraiMohonRujukLuar = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD = new Vector();
    private List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG = new Vector();
    private List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal = new Vector();
    private List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakBerkepentingan = new Vector();
    private String tajukPerakuanPTD;
    private String tajukPerakuanPTG;
    private LaporanTanah mohonLaportanah;
    private PermohonanKertas mohonKertas;
    private PermohonanRujukanLuar mohonRujukluar;
    private Pemohon pemohon;
    private String namaPemohon;
    private String alamatSuratPemohon;
    private Date tarikhPermohonan;
    private String daerah;
    private String namaPemilik;
    private String mukim;
    private String tanah;
    private String tujuanPermohonan;
    private String ulasanPerihalTanah1;
    private String ulasanPerihalTanah2;
    private String ulasanYBADUNkawasan;
    private Hakmilik hakmilik;
    private Long idHakmilik;
    private String idHakmiliktemp;
    private HakmilikPihakBerkepentingan hakmilikPihak;
    private String namaAgensi;
    private KodDUN adunKawasan;
    private String ptg;
    private String bil_bngn;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<PermohonanRujukanLuar> senaraiYBAdun;
    private List<PermohonanLaporanUlasan> senaraiLaporanUlas = new Vector();
    private PermohonanRujukanLuar mohonRujLuarJKM;
    private KodDUN kodDun;
    private boolean viewMMKN;
    //Add for changing urusan PJLB because of HakmilikPermohonan is getting from permohonan.getPermohonanSebelum
    private Permohonan permohonanSebelum;

    @DefaultHandler
    public Resolution showForm1() {
        edit = Boolean.FALSE;
        viewMMKN = Boolean.FALSE;
        viewForMMKN = Boolean.FALSE;
        return new JSP("pelupusan/pcrg/syarat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormView() {
        edit = Boolean.FALSE;
        viewMMKN = Boolean.FALSE;
        viewForMMKN = Boolean.TRUE;
        return new JSP("pelupusan/pcrg/syarat_tambahan.jsp").addParameter("tab", "true");
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

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPemohon = String.valueOf(x);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(pengguna.getKodCawangan().getKod());
        String currentStage = getContext().getRequest().getParameter("currentStage");
        logger.info("currentStage: " + currentStage);
        if (!StringUtils.isBlank(currentStage) && currentStage.equals("mmknPage")) {
            viewForMMKN = Boolean.TRUE;
        } else if (!StringUtils.isBlank(currentStage) && currentStage.equals("jkmPage")) {
            viewForMMKN = Boolean.FALSE;
        } else {
            viewForMMKN = Boolean.FALSE;
        }
        logger.info("currentStage: " + currentStage);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("PJLB")) {
            mohonHakmilik = pservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else if (permohonan.getPermohonanSebelum() != null && permohonan.getKodUrusan().getKod().equals("MPJLB")) {
            mohonHakmilik = pservice.findByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else {
            mohonHakmilik = pservice.findByIdPermohonan(idPermohonan);
        }
        pemohon = pservice.findPemohonByIdPemohon(idPermohonan);

        mohonRujukLuar = pservice.findPermohonanRujByIdPermohonan(idPermohonan);
        mohonKertas = pservice.findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, "STKM");
        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            KodDokumen kodDokumen = kodDokumenDAO.findById("STKM");

            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonKertas.setCawangan(cawangan);
            mohonKertas.setKodDokumen(kodDokumen);
            permohonan.setIdPermohonan(idPermohonan);
            mohonKertas.setPermohonan(permohonan);
            mohonKertas.setTajuk("Syarat Tambahan Keputusan MMK ");
            mohonKertas.setInfoAudit(infoAudit);
            pservice.simpanPermohonanKertas(mohonKertas);
        }

        namaPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama());

        settingBil0();
        settingBil2();

//        namaPemilik = hakmilikPihak.getNama();

        if (mohonKertas != null) {
            /*
             * FOR BIL 2 AND 2.3.%
             * TUJUAN PERMOHONAN
             */
            senaraiLaporanKandunganPerihalPermohonan = pservice.findByIdLapor(mohonKertas.getIdKertas(), 1);
        }
        if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("PCRG")) {
            senaraiMohonRujukLuar = pservice.findPermohonanRujLuarByIdPermohonanWithPTD(idPermohonan);
        }

        logger.info("-------mohon kertas----" + mohonKertas.getIdKertas());
    }

    /*
     * SETTING TAJUK DRAF WHICH BIL =0;
     */

    public void settingBil0() {
        namaPemohon = pemohon.getPihak().getNama();
        if (mohonHakmilik.getKodMilik() != null) {
            tanah = mohonHakmilik.getKodMilik().getNama().toUpperCase();
        }
        mukim = mohonHakmilik.getBandarPekanMukimBaru().getNama();
        daerah = mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama();
        tajukUtama = tajuk + tanah + tajuk2 + " DI " + mukim + tajuk3 + daerah + ", NEGERI SEMBILAN" + noktah;

    }

    public void settingBil2() {
        namaPemohon = pelUtiliti.convertStringtoInitCap(pemohon.getPihak().getNama());
        if (mohonHakmilik.getKodMilik() != null) {
            tanah = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getKodMilik().getNama());
        }
        mukim = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getNama());
        daerah = pelUtiliti.convertStringtoInitCap(mohonHakmilik.getBandarPekanMukimBaru().getDaerah().getNama());
        tujuanPermohonan = tujuan + tujuan2 + tanah + tujuan3 + " Lot " + mohonHakmilik.getNoLot() + "," + mukim + tujuan4 + daerah + tujuan5 + noktah;

    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        switch (index) {
            case 1://SYARAT TAMBAHAN
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 1);
                senaraiLaporanKandunganPerihalPermohonan.add(pkk);

                break;
            
            default:
        }
        System.out.println(index);

        return new JSP("pelupusan/pcrg/syarat_tambahan.jsp").addParameter("tab", "true");
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
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new JSP("pelupusan/pcrg/syarat_tambahan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandungan() throws ParseException {


        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");

//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        switch (index) {
            case 1://SYARAT TAMBAHAN
                updateKandungan(1, kand);
                break;

//            default:
//                LOG.info("alamak!! tiada index");
        }
        rehydrate();
        String viewMMKNBool = getContext().getRequest().getParameter("viewMMKN");
        if (!viewMMKNBool.isEmpty()) {
            viewMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
            viewForMMKN = viewMMKNBool.equalsIgnoreCase("true") ? Boolean.TRUE : viewMMKNBool.equalsIgnoreCase("false") ? Boolean.FALSE : Boolean.FALSE;
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/pcrg/syarat_tambahan.jsp").addParameter("tab", "true");
    }

    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());


        if (mohonKertas != null) {
            infoAudit = mohonKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
//        if (permohonan.getKodUrusan().getKod().equals("JPLB")) {
//            mohonKertas.setTajuk("KERTAS JKM");
//            KodDokumen kod = kodDokumenDAO.findById("RMN");
//            mohonKertas.setKodDokumen(kod);
//        } 
        permohonan.setIdPermohonan(idPermohonan);
        mohonKertas.setCawangan(cawangan);
        mohonKertas.setIdKertas(mohonKertas.getIdKertas());
        mohonKertas.setInfoAudit(infoAudit);
        mohonKertas.setPermohonan(permohonan);
        mohonKertas.setTajuk("Syarat Tambahan Keputusan MMK ");

        pelPtService.simpanPermohonanKertas(mohonKertas);

        long a = mohonKertas.getIdKertas();
        List<PermohonanKertasKandungan> plk = pelPtService.findByIdLapor(a, i);

        PermohonanKertasKandungan pLK = new PermohonanKertasKandungan();
//        LOG.info("index :" + i + " kand :" + kand + " id_lapor :" + a);




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
//        System.out.println(pLK.getIdKandungan());


        mohonKertas.setIdKertas(mohonKertas.getIdKertas());
        pLK.setKertas(mohonKertas);
        pLK.setInfoAudit(infoAudit);
        pLK.setCawangan(cawangan);
        pelPtService.simpanPermohonanKertasKandungan(pLK);

    }

    public String getAlamatSuratPemohon() {
        return alamatSuratPemohon;
    }

    public void setAlamatSuratPemohon(String alamatSuratPemohon) {
        this.alamatSuratPemohon = alamatSuratPemohon;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getDrafDaerah() {
        return drafDaerah;
    }

    public void setDrafDaerah(String drafDaerah) {
        this.drafDaerah = drafDaerah;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public Long getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(Long idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(String idPemohon) {
        this.idPemohon = idPemohon;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(String jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getJumlahBayar2() {
        return jumlahBayar2;
    }

    public void setJumlahBayar2(String jumlahBayar2) {
        this.jumlahBayar2 = jumlahBayar2;
    }

    public String getJumlahBayar3() {
        return jumlahBayar3;
    }

    public void setJumlahBayar3(String jumlahBayar3) {
        this.jumlahBayar3 = jumlahBayar3;
    }

    public String getKadarBayar() {
        return kadarBayar;
    }

    public void setKadarBayar(String kadarBayar) {
        this.kadarBayar = kadarBayar;
    }

    public String getKadarBayar2() {
        return kadarBayar2;
    }

    public void setKadarBayar2(String kadarBayar2) {
        this.kadarBayar2 = kadarBayar2;
    }

    public String getKodNeg() {
        return kodNeg;
    }

    public void setKodNeg(String kodNeg) {
        this.kodNeg = kodNeg;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public LaporanTanah getMohonLaportanah() {
        return mohonLaportanah;
    }

    public void setMohonLaportanah(LaporanTanah mohonLaportanah) {
        this.mohonLaportanah = mohonLaportanah;
    }

    public PermohonanRujukanLuar getMohonRujukLuar() {
        return mohonRujukLuar;
    }

    public void setMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        this.mohonRujukLuar = mohonRujukLuar;
    }

    public PermohonanRujukanLuar getMohonRujukluar() {
        return mohonRujukluar;
    }

    public void setMohonRujukluar(PermohonanRujukanLuar mohonRujukluar) {
        this.mohonRujukluar = mohonRujukluar;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiHakmilikPihakBerkepentingan() {
        return senaraiHakmilikPihakBerkepentingan;
    }

    public void setSenaraiHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakBerkepentingan) {
        this.senaraiHakmilikPihakBerkepentingan = senaraiHakmilikPihakBerkepentingan;
    }

    public String getNoktah() {
        return noktah;
    }

    public void setNoktah(String noktah) {
        this.noktah = noktah;
    }

    public String getNoktahbertindih() {
        return noktahbertindih;
    }

    public void setNoktahbertindih(String noktahbertindih) {
        this.noktahbertindih = noktahbertindih;
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

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getPerihaltanah1() {
        return perihaltanah1;
    }

    public void setPerihaltanah1(String perihaltanah1) {
        this.perihaltanah1 = perihaltanah1;
    }

    public String getPerihaltanah12() {
        return perihaltanah12;
    }

    public void setPerihaltanah12(String perihaltanah12) {
        this.perihaltanah12 = perihaltanah12;
    }

    public String getPerihaltanah13() {
        return perihaltanah13;
    }

    public void setPerihaltanah13(String perihaltanah13) {
        this.perihaltanah13 = perihaltanah13;
    }

    public String getPerihaltanah14() {
        return perihaltanah14;
    }

    public void setPerihaltanah14(String perihaltanah14) {
        this.perihaltanah14 = perihaltanah14;
    }

    public String getPerihaltanah15() {
        return perihaltanah15;
    }

    public void setPerihaltanah15(String perihaltanah15) {
        this.perihaltanah15 = perihaltanah15;
    }

    public String getPerihaltanah16() {
        return perihaltanah16;
    }

    public void setPerihaltanah16(String perihaltanah16) {
        this.perihaltanah16 = perihaltanah16;
    }

    public String getPerihaltanah17() {
        return perihaltanah17;
    }

    public void setPerihaltanah17(String perihaltanah17) {
        this.perihaltanah17 = perihaltanah17;
    }

    public String getPerihaltanah2() {
        return perihaltanah2;
    }

    public void setPerihaltanah2(String perihaltanah2) {
        this.perihaltanah2 = perihaltanah2;
    }

    public String getPerihaltanah21() {
        return perihaltanah21;
    }

    public void setPerihaltanah21(String perihaltanah21) {
        this.perihaltanah21 = perihaltanah21;
    }

    public String getPerihaltanah22() {
        return perihaltanah22;
    }

    public void setPerihaltanah22(String perihaltanah22) {
        this.perihaltanah22 = perihaltanah22;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public String getPtg() {
        return ptg;
    }

    public void setPtg(String ptg) {
        this.ptg = ptg;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandungan2() {
        return senaraiLaporanKandungan2;
    }

    public void setSenaraiLaporanKandungan2(List<PermohonanKertasKandungan> senaraiLaporanKandungan2) {
        this.senaraiLaporanKandungan2 = senaraiLaporanKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTD() {
        return senaraiLaporanKandunganPerakuanPTD;
    }

    public void setSenaraiLaporanKandunganPerakuanPTD(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTD) {
        this.senaraiLaporanKandunganPerakuanPTD = senaraiLaporanKandunganPerakuanPTD;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerakuanPTG() {
        return senaraiLaporanKandunganPerakuanPTG;
    }

    public void setSenaraiLaporanKandunganPerakuanPTG(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerakuanPTG) {
        this.senaraiLaporanKandunganPerakuanPTG = senaraiLaporanKandunganPerakuanPTG;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerihalPermohonan() {
        return senaraiLaporanKandunganPerihalPermohonan;
    }

    public void setSenaraiLaporanKandunganPerihalPermohonan(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalPermohonan) {
        this.senaraiLaporanKandunganPerihalPermohonan = senaraiLaporanKandunganPerihalPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiLaporanKandunganPerihalTanah() {
        return senaraiLaporanKandunganPerihalTanah;
    }

    public void setSenaraiLaporanKandunganPerihalTanah(List<PermohonanKertasKandungan> senaraiLaporanKandunganPerihalTanah) {
        this.senaraiLaporanKandunganPerihalTanah = senaraiLaporanKandunganPerihalTanah;
    }

    public List<PermohonanRujukanLuar> getSenaraiUlasanJabatanTeknikal() {
        return senaraiUlasanJabatanTeknikal;
    }

    public void setSenaraiUlasanJabatanTeknikal(List<PermohonanRujukanLuar> senaraiUlasanJabatanTeknikal) {
        this.senaraiUlasanJabatanTeknikal = senaraiUlasanJabatanTeknikal;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTajuk2() {
        return tajuk2;
    }

    public void setTajuk2(String tajuk2) {
        this.tajuk2 = tajuk2;
    }

    public String getTajuk3() {
        return tajuk3;
    }

    public void setTajuk3(String tajuk3) {
        this.tajuk3 = tajuk3;
    }

    public String getTajuk4() {
        return tajuk4;
    }

    public void setTajuk4(String tajuk4) {
        this.tajuk4 = tajuk4;
    }

    public String getTajukHeader() {
        return tajukHeader;
    }

    public void setTajukHeader(String tajukHeader) {
        this.tajukHeader = tajukHeader;
    }

    public String getTajukPerakuanPTD() {
        return tajukPerakuanPTD;
    }

    public void setTajukPerakuanPTD(String tajukPerakuanPTD) {
        this.tajukPerakuanPTD = tajukPerakuanPTD;
    }

    public String getTajukPerakuanPTG() {
        return tajukPerakuanPTG;
    }

    public void setTajukPerakuanPTG(String tajukPerakuanPTG) {
        this.tajukPerakuanPTG = tajukPerakuanPTG;
    }

    public String getTajukPerihalPermohonan() {
        return tajukPerihalPermohonan;
    }

    public void setTajukPerihalPermohonan(String tajukPerihalPermohonan) {
        this.tajukPerihalPermohonan = tajukPerihalPermohonan;
    }

    public String getTajukUtama() {
        return tajukUtama;
    }

    public void setTajukUtama(String tajukUtama) {
        this.tajukUtama = tajukUtama;
    }

    public String getTanah() {
        return tanah;
    }

    public void setTanah(String tanah) {
        this.tanah = tanah;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getBil_bngn() {
        return bil_bngn;
    }

    public void setBil_bngn(String bil_bngn) {
        this.bil_bngn = bil_bngn;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getIdHakmiliktemp() {
        return idHakmiliktemp;
    }

    public void setIdHakmiliktemp(String idHakmiliktemp) {
        this.idHakmiliktemp = idHakmiliktemp;
    }

    public KodDUN getAdunKawasan() {
        return adunKawasan;
    }

    public void setAdunKawasan(KodDUN adunKawasan) {
        this.adunKawasan = adunKawasan;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTujuan2() {
        return tujuan2;
    }

    public void setTujuan2(String tujuan2) {
        this.tujuan2 = tujuan2;
    }

    public String getTujuan3() {
        return tujuan3;
    }

    public void setTujuan3(String tujuan3) {
        this.tujuan3 = tujuan3;
    }

    public String getTujuan4() {
        return tujuan4;
    }

    public void setTujuan4(String tujuan4) {
        this.tujuan4 = tujuan4;
    }

    public String getTujuan5() {
        return tujuan5;
    }

    public void setTujuan5(String tujuan5) {
        this.tujuan5 = tujuan5;
    }

    public String getTujuanPermohonan() {
        return tujuanPermohonan;
    }

    public void setTujuanPermohonan(String tujuanPermohonan) {
        this.tujuanPermohonan = tujuanPermohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUlasan2() {
        return ulasan2;
    }

    public void setUlasan2(String ulasan2) {
        this.ulasan2 = ulasan2;
    }

    public String getUlasan3() {
        return ulasan3;
    }

    public void setUlasan3(String ulasan3) {
        this.ulasan3 = ulasan3;
    }

    public String getUlasanPerihalTanah1() {
        return ulasanPerihalTanah1;
    }

    public void setUlasanPerihalTanah1(String ulasanPerihalTanah1) {
        this.ulasanPerihalTanah1 = ulasanPerihalTanah1;
    }

    public String getUlasanPerihalTanah2() {
        return ulasanPerihalTanah2;
    }

    public void setUlasanPerihalTanah2(String ulasanPerihalTanah2) {
        this.ulasanPerihalTanah2 = ulasanPerihalTanah2;
    }

    public String getUlasanYBADUNkawasan() {
        return ulasanYBADUNkawasan;
    }

    public void setUlasanYBADUNkawasan(String ulasanYBADUNkawasan) {
        this.ulasanYBADUNkawasan = ulasanYBADUNkawasan;
    }

    public String getUrusanStatus() {
        return urusanStatus;
    }

    public void setUrusanStatus(String urusanStatus) {
        this.urusanStatus = urusanStatus;
    }

    public boolean isViewOnlyPTD() {
        return viewOnlyPTD;
    }

    public void setViewOnlyPTD(boolean viewOnlyPTD) {
        this.viewOnlyPTD = viewOnlyPTD;
    }

    public boolean isViewOnlyPTG() {
        return viewOnlyPTG;
    }

    public void setViewOnlyPTG(boolean viewOnlyPTG) {
        this.viewOnlyPTG = viewOnlyPTG;
    }

    public String getWangCagar() {
        return wangCagar;
    }

    public void setWangCagar(String wangCagar) {
        this.wangCagar = wangCagar;
    }

    public String getWangCagar2() {
        return wangCagar2;
    }

    public void setWangCagar2(String wangCagar2) {
        this.wangCagar2 = wangCagar2;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<PermohonanRujukanLuar> getSenaraiYBAdun() {
        return senaraiYBAdun;
    }

    public void setSenaraiYBAdun(List<PermohonanRujukanLuar> senaraiYBAdun) {
        this.senaraiYBAdun = senaraiYBAdun;
    }

    public KodDUN getKodDun() {
        return kodDun;
    }

    public void setKodDun(KodDUN kodDun) {
        this.kodDun = kodDun;
    }

    public boolean isViewMMKN() {
        return viewMMKN;
    }

    public void setViewMMKN(boolean viewMMKN) {
        this.viewMMKN = viewMMKN;
    }

    public PermohonanRujukanLuar getMohonRujLuarJKM() {
        return mohonRujLuarJKM;
    }

    public void setMohonRujLuarJKM(PermohonanRujukanLuar mohonRujLuarJKM) {
        this.mohonRujLuarJKM = mohonRujLuarJKM;
    }

    public String getMilikNama() {
        return milikNama;
    }

    public void setMilikNama(String milikNama) {
        this.milikNama = milikNama;
    }

    public String getMukimNama() {
        return mukimNama;
    }

    public void setMukimNama(String mukimNama) {
        this.mukimNama = mukimNama;
    }

    public PermohonanRujukanLuar getMohonRujukLuarPTD() {
        return mohonRujukLuarPTD;
    }

    public void setMohonRujukLuarPTD(PermohonanRujukanLuar mohonRujukLuarPTD) {
        this.mohonRujukLuarPTD = mohonRujukLuarPTD;
    }

    public List<PermohonanRujukanLuar> getSenaraiMohonRujukLuar() {
        return senaraiMohonRujukLuar;
    }

    public void setSenaraiMohonRujukLuar(List<PermohonanRujukanLuar> senaraiMohonRujukLuar) {
        this.senaraiMohonRujukLuar = senaraiMohonRujukLuar;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanUlas() {
        return senaraiLaporanUlas;
    }

    public void setSenaraiLaporanUlas(List<PermohonanLaporanUlasan> senaraiLaporanUlas) {
        this.senaraiLaporanUlas = senaraiLaporanUlas;
    }

    public Boolean getViewForMMKN() {
        return viewForMMKN;
    }

    public void setViewForMMKN(Boolean viewForMMKN) {
        this.viewForMMKN = viewForMMKN;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }
}


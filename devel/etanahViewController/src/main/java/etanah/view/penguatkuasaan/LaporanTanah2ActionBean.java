/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKecerunanTanahDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodPBTDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import etanah.dao.KodRizabDAO;
import etanah.dao.KodStrukturTanahDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import etanah.service.common.TanahRizabService;
import etanah.model.KodRizab;
import etanah.model.KodKecerunanTanah;
import etanah.model.TanahRizabPermohonan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikWaris;
import etanah.model.ImejLaporan;
import etanah.model.KodDUN;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodPeranan;
import etanah.model.KodStrukturTanah;
import etanah.model.KodUOM;
import etanah.model.LaporanTanahSempadan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/laporan_tanah2")
public class LaporanTanah2ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodDUNDAO kodDunDAO;
    @Inject
    KodPBTDAO kodPBTDAO;
    private Hakmilik hakmilik;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikUrusan hakmilikUrusan;
    private List<HakmilikUrusan> senaraiHakmilikUrusan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static final Logger LOG = Logger.getLogger(LaporanTanah2ActionBean.class);
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    PengambilanService service;
    @Inject
    private etanah.Configuration conf;
    private String noLot;
    private String noWarta;
    private String lokasi;
    private KodRizab rizab;
    private String tarikhWarta;
    private String idtanahrizabPermohonan;
    private String ulasan;
    private char pandanganImej;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private String jenisjalan;
    private String adaBangunan;
    private Pengguna pengguna;
    private String ulasanPPT;
    private String ulasanPPTK;
    private String tarikhLawatan;
    private String minit;
    private String jam;
    private String ampm;
    private String kodDunLaporanTanah;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private static Logger logger = Logger.getLogger(LaporanTanah2ActionBean.class);
    private String tarikhDiusahakan;
    private String paparKodDun;
    private String kodNegeri;
    private String noWartaKerajaan;
    private String taskId;
    private List<LaporanTanahSempadan> senaraiLaporanTanahSempadan;
    private List<PermohonanLaporanBangunan> senaraiPermohonanLaporanBangunan;
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private Pengguna pguna;
    private String idPermohonan;
    private List<HakmilikPermohonan> senaraiTanahMilik;
    private String id;
    private List<HakmilikPihakBerkepentingan> pihakBerkepentinganList;
    private List<TanahRizabPermohonan> tanahRizabList;
    private List<HakmilikWaris> hakmilikWarisList;
    private String kodLot;
    private String kategoriTanah;
    private String luas;
    private String kodLuas;
    private String catatan;
    private String idHakmilik;
    private String kodDun;
    private String kodKecerunanTanah;
    private String kodStrukturTanah;
    private String jarakJalanUOM;
    private String jarakSungaiUOM;
    private String jarakKeretapiUOM;
    private String jarakLautUOM;
    private List<LaporanTanah> listLaporanTanah = new ArrayList<LaporanTanah>();
    private PermohonanLaporanUlasan mohonLaporUlas;
    private PermohonanLaporanUlasan mohonLaporUlasPPT;
    private PermohonanLaporanUlasan mohonLaporUlasPPTK;
    private String tarikhSidang;
    private Date tarikhDaftar;
    private Date tarikhBatal;
    private String statusTanah;
    private String badanPengawal;
    private HakmilikPermohonan hakmilikPermohonanKerajaan;
    private Boolean maklumatTanahSek49 = Boolean.FALSE;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idMH;
    private Boolean statusEdit = Boolean.FALSE;
    private String kodKategoriTanah;
    private String jenisHakisan;
    private String statusCarian;
    private String kodLotCarian;
    private String kodKategoriTanahCarian;
    private String kodLuasCarian;
    private String kodDunHakisan;
    private HakmilikPermohonan hakmilikPermohonanHakisan;
    private Boolean statusView = Boolean.FALSE;
    private Boolean statusUlasan = Boolean.FALSE;
    private List<HakmilikPihakBerkepentingan> pihakBerkepentinganListSek49;
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private String anggaranLuasUOM;
    private String kodPBT;
    private String luasHakisan;
    private String kodLuasHakisan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonanForPihakSek49;
    private Boolean multipleHakmilik = Boolean.FALSE;
    private HakmilikPermohonan multipleHakmilikPermohonan;
    private String fromPage;
    private String jenisLaporan;
    etanahActionBeanContext ctx;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("syorPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("syorPPTD", Boolean.TRUE);
        return new JSP("penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution bangunanPopup() {
        return new JSP("penguatkuasaan/popup_maklumat_bangunan.jsp").addParameter("popup", "true");
    }

    public Resolution showForm5() {
        return new JSP("penguatkuasaan/laporan_hakmilik.jsp").addParameter("tab", "true");
    }
    

//    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        ctx = (etanahActionBeanContext) getContext();
        jenisLaporan = "L2TH";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        senaraiPermohonanLaporanBangunan = new ArrayList<PermohonanLaporanBangunan>();
        senaraiLaporanTanahSempadan = new ArrayList<LaporanTanahSempadan>();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        BPelService bpelService = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "sedia_laporan1";
            logger.info("-------------stageId: BPEL OFF ----" + stageId);
        }

        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        pihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        senaraiHakmilikPermohonanForPihakSek49 = new ArrayList<HakmilikPermohonan>();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (kodNegeri.equalsIgnoreCase("04")) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    maklumatTanahSek49 = true;

                    if (stageId.equalsIgnoreCase("g_charting_permohonan") || stageId.equalsIgnoreCase("g_semak_permohonan")) {
                        statusUlasan = true;
                    }
                }
            } else {
                //for NS
                multipleHakmilik = true;
//                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
//                }

            }

            System.out.println("maklumatTanahSek49 : " + maklumatTanahSek49);
            System.out.println("multipleHakmilik : " + multipleHakmilik);
            
            
            if (permohonan.getPermohonanSebelum() != null) {
                //1) After create new IP
                logger.info(":::: id permohonan sebelum exist ::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                permohonan = permohonan.getPermohonanSebelum();
            }
            if (permohonan != null) {
                tanahRizabList = permohonan.getSenaraiTanahRizab();
                senaraiPermohonanLaporanBangunan = enforceService.findByIDLaporBangunan(permohonan.getIdPermohonan());

                ArrayList senaraiHakmilik = new ArrayList<String>();
                Long id = null;

                if (maklumatTanahSek49 == true) {
                    senaraiHakmilikPermohonanForPihakSek49 = enforceService.findListHakmilik(permohonan.getIdPermohonan());
                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                    System.out.println("senaraiHakmilikPermohonan before looping :" + senaraiHakmilikPermohonan.size());
                    if (!senaraiHakmilikPermohonan.isEmpty()) {
                        for (int j = 0; j < senaraiHakmilikPermohonan.size(); j++) {
                            if (senaraiHakmilikPermohonan.get(j).getNomborRujukan() != null) {
                                statusNorujukan = true;
                                System.out.println("::::::::::: value j :" + j);
                                HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(j);
                                listIdPermohonan.add(hp.getNomborRujukan());

                                ArrayList<String> data = listIdPermohonan;

                                System.out.println("size data ::::::" + data.size());


                                for (String a : data) {
                                    senaraiIdPermohonan = a.split(",");
                                    LOG.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                    if (senaraiIdPermohonan.length > 1) {
                                        idPertama = senaraiIdPermohonan[0];
                                        idKedua = senaraiIdPermohonan[1];

                                    }
                                }
                                LOG.info("::: idPertama : " + idPertama);
                                LOG.info("::: idKedua : " + idKedua);

                                String idMohon = "";
                                if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                                    if (idPertama.equalsIgnoreCase(idPermohonan)) {
                                        System.out.println("[" + j + "] : idPertama sama dengan " + idPermohonan);
                                        idMohon = idPertama;
                                        id = senaraiHakmilikPermohonan.get(j).getId();
                                        System.out.println("id MH (1): " + id);
                                    } else if (idKedua.equalsIgnoreCase(idPermohonan)) {
                                        System.out.println("[" + j + "] : idKedua sama dengan " + idPermohonan);
                                        idMohon = idKedua;
                                        id = senaraiHakmilikPermohonan.get(j).getId();
                                        System.out.println("id MH (2): " + id);
                                    }
                                }
                                listIdPermohonan.clear();
                                idPertama = "";
                                idKedua = "";
                            }
                        }

                    }

                    System.out.println("::: id : " + id);
                    System.out.println("::: statusNorujukan : " + statusNorujukan);

                    if (statusNorujukan == true) {
                        if (id != null) {
                            LOG.info("::: using id MH");
                            senaraiHakmilikPermohonan = enforceService.findListMohonHakmilikById(id);
                        } else {
                            LOG.info("::: using id idPermohonan");
                            senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                        }
                    }


                    for (int j = 0; j < senaraiHakmilikPermohonan.size(); j++) {
                        HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(j);
                        if (hp.getHakmilik() != null) {
                            senaraiHakmilik.add(hp.getHakmilik().getIdHakmilik());
                        }
                    }

                    System.out.println("size senarai hakmilik : " + senaraiHakmilik.size());
                    if (!senaraiHakmilik.isEmpty()) {
                        pihakBerkepentinganListSek49 = enforcementService.findPihakTerlibat(senaraiHakmilik);
                        logger.info("pihakBerkepentinganListSek49 size :" + pihakBerkepentinganListSek49.size());
                    }

                    logger.info("size hakmilik permohonan for seksyen 49 : " + senaraiHakmilikPermohonan.size());
                }

                try {
                    hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
                    if (!hakmilikPermohonanList.isEmpty()) {
                        logger.info("hakmilikPermohonan size : " + hakmilikPermohonanList.size());
                        hakmilikPermohonan = hakmilikPermohonanList.get(0);
                        if (hakmilikPermohonan.getHakmilik() != null) {
                            logger.info("id hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            //senaraiHakmilikUrusan = enforceService.findByIDHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            pihakBerkepentinganList = enforceService.findSenaraiPihak(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            hakmilikWarisList = enforceService.findByIdHp(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            pihakKepentinganList = enforceService.findSenaraiPihakBerkepentingan(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            logger.info("pihakKepentinganList size :" + pihakKepentinganList.size());
                        }

                        if (hakmilikPermohonan != null) {
                            if (kodNegeri.equalsIgnoreCase("04")) {
                                if (hakmilikPermohonan.getKodDUN() != null) {
                                    kodDunLaporanTanah = hakmilikPermohonan.getKodDUN().getKod();
                                    logger.info("value dun : " + kodDunLaporanTanah);
                                    paparKodDun = hakmilikPermohonan.getKodDUN().getNama();
                                }
                            }
                        }
                    } else {
                        senaraiTanahMilik = enforceService.findSenaraiTanahMilik(permohonan.getIdPermohonan());
                        logger.info("size senarai tanah milik : " + senaraiTanahMilik.size());

                    }

                    //properties for multiple hakmilik

                    if (multipleHakmilik == true) {
                        hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
                        if (!hakmilikPermohonanList.isEmpty()) {
                            for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                                HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                                if (hp.getHakmilik() != null) {
                                    senaraiHakmilik.add(hp.getHakmilik().getIdHakmilik());
                                }
                            }

                            System.out.println("size senarai hakmilik : " + senaraiHakmilik.size());
                            if (!senaraiHakmilik.isEmpty()) {
                                hakmilikWarisList = enforcementService.findWarisByIdHakmilik(senaraiHakmilik);
                                pihakKepentinganList = enforcementService.findPihakBerkepentingan(senaraiHakmilik, "ne");
                                pihakBerkepentinganList = enforcementService.findPihakBerkepentingan(senaraiHakmilik, "eq");
                            }

                            System.out.println("size senarai listWaris : " + hakmilikWarisList.size());
                            System.out.println("size senarai listPihakBerkepentingan : " + pihakKepentinganList.size());
                            System.out.println("size senarai listPemilik : " + pihakBerkepentinganList.size());

                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error(ex);
                }

                //Hafifi : replace function with new one
                listLaporanTanah = laporanTanahService.getListLaporanTanah(permohonan.getIdPermohonan(),jenisLaporan);
                System.out.println("size list laporan tanah : " + listLaporanTanah.size());
                System.out.println("listLaporanTanah : " + listLaporanTanah.toString());

                if (!(listLaporanTanah.isEmpty())) {
                    laporanTanah = listLaporanTanah.get(0);

                    imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());
                    senaraiLaporanTanahSempadan = enforceService.findByIDTanahSempadan(laporanTanah.getIdLaporan());
                    statusTanah = laporanTanah.getJenisTanah() != null && !Character.isWhitespace(laporanTanah.getJenisTanah()) ? laporanTanah.getJenisTanah().toString() : new String();

                    logger.info("Jenis tanah : " + statusTanah);

                    try {
                        if (laporanTanah.getTarikhSiasat() != null) {
                            tarikhLawatan = sdf.format(laporanTanah.getTarikhSiasat());
                            jam = sdf1.format(laporanTanah.getTarikhSiasat()).substring(11, 13);
                            if (jam.startsWith("0")) {
                                jam = sdf1.format(laporanTanah.getTarikhSiasat()).substring(12, 13);
                            }
                            minit = sdf1.format(laporanTanah.getTarikhSiasat()).substring(14, 16);
                            ampm = sdf1.format(laporanTanah.getTarikhSiasat()).substring(17, 19);
                        }

                        if (laporanTanah.getTarikhMulaUsaha2() != null) {
                            tarikhDiusahakan = sdf.format(laporanTanah.getTarikhMulaUsaha2());
                        }
                        if (laporanTanah.getKecerunanTanah() != null) {
                            kodKecerunanTanah = laporanTanah.getKecerunanTanah().getKod();
                        }
                        if (laporanTanah.getStrukturTanah() != null) {
                            kodStrukturTanah = laporanTanah.getStrukturTanah().getKod();
                        }
                        if (laporanTanah.getJarakSempadanJalanrayaUOM() != null) {
                            jarakJalanUOM = laporanTanah.getJarakSempadanJalanrayaUOM().getKod();
                        }
                        if (laporanTanah.getJarakSempadanKeretapiUOM() != null) {
                            jarakKeretapiUOM = laporanTanah.getJarakSempadanKeretapiUOM().getKod();
                        }
                        if (laporanTanah.getJarakSempadanLautUOM() != null) {
                            jarakLautUOM = laporanTanah.getJarakSempadanLautUOM().getKod();
                        }
                        if (laporanTanah.getJarakSempadanSungaiUOM() != null) {
                            jarakSungaiUOM = laporanTanah.getJarakSempadanSungaiUOM().getKod();
                        }
                        if (laporanTanah.getUsahaLuasUom() != null) {
                            anggaranLuasUOM = laporanTanah.getUsahaLuasUom().getKod();
                        }


                    } catch (Exception ex) {
                        System.err.println("ERROR: this date format threw an exception:\n " + sdf1.toString());
                        logger.error(ex);
                    }
                }

                List<FasaPermohonan> listFasa;
                listFasa = enforceService.getSenaraiFasa(permohonan.getIdPermohonan(), stageId);
                if (listFasa.size() != 0) {
                    fasaPermohonan = listFasa.get(0);
                }

                List<PermohonanRujukanLuar> listRujukanLuar;
                listRujukanLuar = permohonan.getSenaraiRujukanLuar();

                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                        rujL = listRujukanLuar.get(i);
                        if (rujL.getKodDokumenRujukan() != null) {
                            if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                                permohonanRujukanLuar = listRujukanLuar.get(i);
                                if (permohonanRujukanLuar != null) {
                                    noWartaKerajaan = permohonanRujukanLuar.getNoRujukan();
                                    logger.info("tarikh sidang exist or not :" + permohonanRujukanLuar.getTarikhSidang());
                                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
                                        tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
                                    }
                                    logger.info("no warta kerajaan : " + noWartaKerajaan);
                                    logger.info("tarikh Sidang : " + tarikhSidang);
                                }
                            }
                        }
                    }
                }

                //Hafifi : replace function with new one
                List<PermohonanLaporanUlasan> listLaporUlas;
                listLaporUlas = enforceService.getListUlasan(permohonan.getIdPermohonan(), jenisLaporan);
                if (!(listLaporUlas.isEmpty())) {
                    for (int i = 0; i < listLaporUlas.size(); i++) {
                        PermohonanLaporanUlasan laporUlas = new PermohonanLaporanUlasan();
                        laporUlas = listLaporUlas.get(i);
                        if (laporUlas.getKodPeranan() != null) {
                            if (laporUlas.getJenisUlasan().equalsIgnoreCase("PPT")) {
                                ulasanPPT = listLaporUlas.get(i).getUlasan();
                                mohonLaporUlasPPT = listLaporUlas.get(i);
                                logger.info(":::: id mohonLaporUlasPPT : " + mohonLaporUlasPPT.getIdLaporUlas());
                            } else if (laporUlas.getJenisUlasan().equalsIgnoreCase("PPTK")) {
                                ulasanPPTK = listLaporUlas.get(i).getUlasan();
                                mohonLaporUlasPPTK = listLaporUlas.get(i);
                                logger.info(":::: id mohonLaporUlasPPTK : " + mohonLaporUlasPPTK.getIdLaporUlas());
                            }
                        }
                    }
                }
            }


        }
    }

    public Resolution simpanLaporanTanah() throws Exception {

        InfoAudit infoAudit = new InfoAudit();

        if (StringUtils.isNotBlank(tarikhLawatan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
            tarikhLawatan = tarikhLawatan + " " + jam + ":" + minit + " " + ampm;
            LOG.info("tarikh lawatan :" + tarikhLawatan);
        }


        if (laporanTanah != null) {
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }

            }
            try {
                if (tarikhLawatan != null && StringUtils.isNotBlank(tarikhLawatan)) {
                    laporanTanah.setTarikhSiasat(sdf1.parse(tarikhLawatan));
                }
                if (tarikhDiusahakan != null) {
                    laporanTanah.setTarikhMulaUsaha2(sdf.parse(tarikhDiusahakan));
                }
                if (laporanTanah.getUsaha() == 'T') {
                    laporanTanah.setTarikhMulaUsaha2(null);
                    laporanTanah.setDiusaha(null);
                    laporanTanah.setUsahaTanam(null);
                }
                KodKecerunanTanah cerunTanah = new KodKecerunanTanah();
                cerunTanah.setKod(kodKecerunanTanah);

                KodStrukturTanah strukturTanah = new KodStrukturTanah();
                strukturTanah.setKod(kodStrukturTanah);

                KodUOM jarakSungai = new KodUOM();
                jarakSungai.setKod(jarakSungaiUOM);

                KodUOM jarakKeretapi = new KodUOM();
                jarakKeretapi.setKod(jarakKeretapiUOM);

                KodUOM jarakJalan = new KodUOM();
                jarakJalan.setKod(jarakJalanUOM);

                KodUOM jarakLaut = new KodUOM();
                jarakLaut.setKod(jarakLautUOM);

                laporanTanah.setKecerunanTanah(kodKecerunanTanahDAO.findById(kodKecerunanTanah));
                laporanTanah.setStrukturTanah(strukturTanah);
                laporanTanah.setJarakSempadanJalanrayaUOM(jarakJalan);
                laporanTanah.setJarakSempadanKeretapiUOM(jarakKeretapi);
                laporanTanah.setJarakSempadanLautUOM(jarakLaut);
                laporanTanah.setJarakSempadanSungaiUOM(jarakSungai);

            } catch (Exception ex) {
                logger.error(ex);
                ex.printStackTrace();
            }
            laporanTanah.setPermohonan(permohonan);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);

            try {
                if (tarikhLawatan != null && StringUtils.isNotBlank(tarikhLawatan)) {
                    laporanTanah.setTarikhSiasat(sdf1.parse(tarikhLawatan));
                }
                if (tarikhDiusahakan != null) {
                    laporanTanah.setTarikhMulaUsaha2(sdf.parse(tarikhDiusahakan));
                }
                if (laporanTanah.getUsaha() == 'T') {
                    laporanTanah.setTarikhMulaUsaha2(null);
                    laporanTanah.setDiusaha(null);
                    laporanTanah.setUsahaTanam(null);
                }

                KodKecerunanTanah cerunTanah = new KodKecerunanTanah();
                cerunTanah.setKod(kodKecerunanTanah);

                KodStrukturTanah strukturTanah = new KodStrukturTanah();
                strukturTanah.setKod(kodStrukturTanah);

                KodUOM jarakSungai = new KodUOM();
                jarakSungai.setKod(jarakSungaiUOM);

                KodUOM jarakKeretapi = new KodUOM();
                jarakKeretapi.setKod(jarakKeretapiUOM);

                KodUOM jarakJalan = new KodUOM();
                jarakJalan.setKod(jarakJalanUOM);

                KodUOM jarakLaut = new KodUOM();
                jarakLaut.setKod(jarakLautUOM);

                laporanTanah.setKecerunanTanah(cerunTanah);
                laporanTanah.setStrukturTanah(strukturTanah);
                laporanTanah.setJarakSempadanJalanrayaUOM(jarakJalan);
                laporanTanah.setJarakSempadanKeretapiUOM(jarakKeretapi);
                laporanTanah.setJarakSempadanLautUOM(jarakLaut);
                laporanTanah.setJarakSempadanSungaiUOM(jarakSungai);

            } catch (Exception ex) {
                logger.error(ex);
                ex.printStackTrace();
            }
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);

        }

        //update mohon_lapor_bngn : set idLapor(LaporanTanah)

        senaraiPermohonanLaporanBangunan = enforceService.findByIDLaporBangunan(idPermohonan);

        if (senaraiPermohonanLaporanBangunan.size() != 0) {
            for (int i = 0; i < senaraiPermohonanLaporanBangunan.size(); i++) {
                permohonanLaporanBangunan = senaraiPermohonanLaporanBangunan.get(i);
                InfoAudit info = permohonanLaporanBangunan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(infoAudit);
                permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
                enforceService.simpanBangunan(permohonanLaporanBangunan);
            }
        }


        if (fasaPermohonan != null) {

            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
        }

        if (permohonanRujukanLuar != null) {
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            permohonanRujukanLuar.setInfoAudit(infoAudit);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            permohonanRujukanLuar.setNoRujukan(noWartaKerajaan);
            if (tarikhSidang != null) {
                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
            }
            KodRujukan kodRuj = new KodRujukan();
            kodRuj.setKod("NF");
            permohonanRujukanLuar.setKodRujukan(kodRuj);

            KodDokumen kodDokumenWarta = new KodDokumen();
            kodDokumenWarta.setKod("WRKN");
            permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
            laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
        } else {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            permohonanRujukanLuar.setInfoAudit(infoAudit);
            permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
            permohonanRujukanLuar.setNoRujukan(noWartaKerajaan);
            if (tarikhSidang != null) {
                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
            }
            KodRujukan kodRuj = new KodRujukan();
            kodRuj.setKod("NF");
            permohonanRujukanLuar.setKodRujukan(kodRuj);

            KodDokumen kodDokumenWarta = new KodDokumen();
            kodDokumenWarta.setKod("WRKN");
            permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
            laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
        }

        String kodPeranan = pengguna.getPerananUtama().getKod();
        mohonLaporUlas = laporanTanahService.findMohonLaporUlas(idPermohonan, kodPeranan);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            if (mohonLaporUlas != null) {
                infoAudit = mohonLaporUlas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                mohonLaporUlas = new PermohonanLaporanUlasan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            mohonLaporUlas.setPermohonan(permohonan);
            mohonLaporUlas.setCawangan(permohonan.getCawangan());
            mohonLaporUlas.setInfoAudit(infoAudit);

            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            mohonLaporUlas.setKodPeranan(kp);
            mohonLaporUlas.setJenisUlasan("PPT");
            mohonLaporUlas.setUlasan(ulasanPPT);

            permohonanLaporanUlasanDAO.saveOrUpdate(mohonLaporUlas);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        //save kod DUN only for MELAKA
        if (kodNegeri.equalsIgnoreCase("04")) {
            try {

                if (kodDunLaporanTanah != null) {
                    KodDUN kodDunMohon = new KodDUN();
                    kodDunMohon.setKod(kodDunLaporanTanah);
//                    kodDunMohon = KodDUNDAO.findById(kodDunLaporanTanah);

                    hakmilikPermohonan = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);
                    if (hakmilikPermohonan != null) {
                        infoAudit = hakmilikPermohonan.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                    hakmilikPermohonan.setKodDUN(kodDunMohon);

                    hakmilikPermohonan.setInfoAudit(infoAudit);
                    enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanLaporanTanah1() throws Exception {
        LOG.info("-----------------simpanLaporanTanah1-----------------");
        System.out.println(jenisLaporan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (StringUtils.isNotBlank(tarikhLawatan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
            tarikhLawatan = tarikhLawatan + " " + jam + ":" + minit + " " + ampm;
            LOG.info("tarikh lawatan :" + tarikhLawatan);
        }

        listLaporanTanah = laporanTanahService.getListLaporanTanah(idPermohonan, jenisLaporan);
        System.out.println("size list laporan tanah -- simpanLaporanTanah1: " + listLaporanTanah.size());
        System.out.println("listLaporanTanah -- simpanLaporanTanah1: " + listLaporanTanah);

        try {
            if (!(listLaporanTanah.isEmpty())) {
                System.out.println("id laporan tanah : " + laporanTanah.getIdLaporan());
                LOG.info("Update Laporan Tanah");
                infoAudit = listLaporanTanah.get(0).getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah.setJenisLaporan(jenisLaporan);

            if (kodKecerunanTanah != null) {
                laporanTanah.setKecerunanTanah(kodKecerunanTanahDAO.findById(kodKecerunanTanah));
            } else {
                laporanTanah.setKecerunanTanah(null);
            }
            if (kodStrukturTanah != null) {
                laporanTanah.setStrukturTanah(kodStrukturTanahDAO.findById(kodStrukturTanah));
            } else {
                laporanTanah.setStrukturTanah(null);
            }
            if (jarakJalanUOM != null) {
                laporanTanah.setJarakSempadanJalanrayaUOM(kodUOMDAO.findById(jarakJalanUOM));
            } else {
                laporanTanah.setJarakSempadanJalanrayaUOM(null);
            }
            if (jarakKeretapiUOM != null) {
                laporanTanah.setJarakSempadanKeretapiUOM(kodUOMDAO.findById(jarakKeretapiUOM));
            } else {
                laporanTanah.setJarakSempadanKeretapiUOM(null);
            }
            if (jarakLautUOM != null) {
                laporanTanah.setJarakSempadanLautUOM(kodUOMDAO.findById(jarakLautUOM));
            } else {
                laporanTanah.setJarakSempadanLautUOM(null);
            }
            if (jarakSungaiUOM != null) {
                laporanTanah.setJarakSempadanSungaiUOM(kodUOMDAO.findById(jarakSungaiUOM));
            } else {
                laporanTanah.setJarakSempadanSungaiUOM(null);
            }
            if (anggaranLuasUOM != null) {
                laporanTanah.setUsahaLuasUom(kodUOMDAO.findById(anggaranLuasUOM));
            } else {
                laporanTanah.setUsahaLuasUom(null);
            }


            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
                    laporanTanah.setBilanganBangunan(0);
                }
            }

            if (tarikhLawatan != null && StringUtils.isNotBlank(tarikhLawatan)) {
                laporanTanah.setTarikhSiasat(sdf1.parse(tarikhLawatan));
            }
            if (tarikhDiusahakan != null) {
                laporanTanah.setTarikhMulaUsaha2(sdf.parse(tarikhDiusahakan));
            }

            if (laporanTanah.getUsaha() != null) {
                if (laporanTanah.getUsaha() == 'T') {
                    laporanTanah.setTarikhMulaUsaha2(null);
                    laporanTanah.setDiusaha(null);
                    laporanTanah.setUsahaTanam(null);
                }
            }

            if (!hakmilikPermohonanList.isEmpty()) {
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
                if (hakmilikPermohonan.getHakmilik() != null) {
                    laporanTanah.setJenisTanah('H');
                }

            } else {
                senaraiTanahMilik = enforceService.findSenaraiTanahMilik(permohonan.getIdPermohonan());
                if (!senaraiTanahMilik.isEmpty()) {
                    laporanTanah.setJenisTanah('K');
                }

            }

            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        //update mohon_lapor_bngn : set idLapor(LaporanTanah)

        senaraiPermohonanLaporanBangunan = enforceService.findByIDLaporBangunan(idPermohonan);

        if (senaraiPermohonanLaporanBangunan.size() != 0) {
            for (int i = 0; i < senaraiPermohonanLaporanBangunan.size(); i++) {
                permohonanLaporanBangunan = senaraiPermohonanLaporanBangunan.get(i);
                InfoAudit info = permohonanLaporanBangunan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                permohonanLaporanBangunan.setInfoAudit(infoAudit);
                permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
                enforceService.simpanBangunan(permohonanLaporanBangunan);
            }
        }


        if (fasaPermohonan != null) {
            infoAudit = fasaPermohonan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            fasaPermohonan = new FasaPermohonan();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        fasaPermohonan.setPermohonan(permohonan);
        fasaPermohonan.setCawangan(permohonan.getCawangan());
        fasaPermohonan.setInfoAudit(infoAudit);
        fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
        fasaPermohonan.setIdAliran(stageId);
        laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

        if (permohonanRujukanLuar != null) {
            infoAudit = permohonanRujukanLuar.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        permohonanRujukanLuar.setInfoAudit(infoAudit);
        permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
        permohonanRujukanLuar.setNoRujukan(noWartaKerajaan);
        if (tarikhSidang != null) {
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        KodRujukan kodRuj = new KodRujukan();
        kodRuj.setKod("NF");
        permohonanRujukanLuar.setKodRujukan(kodRuj);

        KodDokumen kodDokumenWarta = new KodDokumen();
        kodDokumenWarta.setKod("WRKN");
        permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
        laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);

        String kodPeranan = pengguna.getPerananUtama().getKod();
        mohonLaporUlas = laporanTanahService.findMohonLaporUlas(idPermohonan, "PPT", jenisLaporan);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            if (StringUtils.isNotBlank(ulasanPPT)) {
                if (mohonLaporUlas != null) {
                    infoAudit = mohonLaporUlas.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    mohonLaporUlas = new PermohonanLaporanUlasan();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                mohonLaporUlas.setPermohonan(permohonan);
                mohonLaporUlas.setCawangan(permohonan.getCawangan());
                mohonLaporUlas.setInfoAudit(infoAudit);

                KodPeranan kp = kodPerananDAO.findById(kodPeranan);
                mohonLaporUlas.setKodPeranan(kp);
                mohonLaporUlas.setJenisUlasan("PPT");
                mohonLaporUlas.setLaporanTanah(laporanTanah);
                mohonLaporUlas.setUlasan(ulasanPPT);

                mohonLaporUlas.setJenisLaporan(jenisLaporan);
                permohonanLaporanUlasanDAO.saveOrUpdate(mohonLaporUlas);
                tx.commit();
            }


        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        //save kod DUN only for MELAKA
        if (kodNegeri.equalsIgnoreCase("04")) {
            try {

                if (kodDunLaporanTanah != null) {
                    KodDUN kodDunMohon = new KodDUN();
                    kodDunMohon.setKod(kodDunLaporanTanah);
//                    kodDunMohon = KodDUNDAO.findById(kodDunLaporanTanah);

                    hakmilikPermohonan = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);
                    if (hakmilikPermohonan != null) {
                        infoAudit = hakmilikPermohonan.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                    hakmilikPermohonan.setKodDUN(kodDunMohon);

                    hakmilikPermohonan.setInfoAudit(infoAudit);
                    enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanUlasanPPTK() throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        String kodPeranan = pengguna.getPerananUtama().getKod();
        mohonLaporUlas = laporanTanahService.findMohonLaporUlas(idPermohonan, "PPTK", jenisLaporan);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        try {
            if (mohonLaporUlas != null) {
                infoAudit = mohonLaporUlas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                mohonLaporUlas = new PermohonanLaporanUlasan();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            mohonLaporUlas.setPermohonan(permohonan);
            mohonLaporUlas.setCawangan(permohonan.getCawangan());
            mohonLaporUlas.setInfoAudit(infoAudit);

            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            mohonLaporUlas.setKodPeranan(kp);
            mohonLaporUlas.setJenisUlasan("PPTK");
            mohonLaporUlas.setUlasan(ulasanPPTK);
            mohonLaporUlas.setLaporanTanah(laporanTanah);

            mohonLaporUlas.setJenisLaporan(jenisLaporan);
            permohonanLaporanUlasanDAO.saveOrUpdate(mohonLaporUlas);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            addSimpleError(t.getMessage());
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("syorPPTK", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Long idImej = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        Long idDokumen = Long.parseLong(getContext().getRequest().getParameter("idDokumen"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            ImejLaporan imejLaporan = imejLaporanDAO.findById(idImej);
            imejLaporanDAO.delete(imejLaporan);

            Dokumen dok1 = dokumenDAO.findById(idDokumen);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution carianHakmilik() {
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idHakmilik != null && StringUtils.isNotBlank(idHakmilik)) {

            hakmilik = hakmilikDAO.findById(idHakmilik);

            if (hakmilik == null) {
                addSimpleError("Maklumat hakmilik tidak dijumpai");
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                return new JSP("penguatkuasaan/Laporan_Tanah2.jsp").addParameter("tab", "true");

            } else {

                hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(idPermohonan);

                InfoAudit ia = new InfoAudit();
                if (hakmilikPermohonanList.size() != 0) {
                    hakmilikPermohonan = hakmilikPermohonanList.get(0);
                }
                if (hakmilikPermohonan == null) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                } else {
                    ia = hakmilikPermohonan.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                }
                hakmilikPermohonan.setHakmilik(hakmilik);
                hakmilikPermohonan.setPermohonan(permohonan);
                hakmilikPermohonan.setInfoAudit(ia);
                hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
                hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
                hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
                hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
                enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);

                if (!(listLaporanTanah.isEmpty())) {
                    System.out.println("id laporan tanah : " + laporanTanah.getIdLaporan());
                    LOG.info("Update Laporan Tanah ::: update tanah hakmilik");
                    ia = listLaporanTanah.get(0).getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    LOG.info("New Laporan Tanah ::: add tanah hakmilik");
                    laporanTanah = new LaporanTanah();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }

                laporanTanah.setJenisTanah('H');
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setInfoAudit(ia);
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);


                senaraiTanahMilik = enforceService.findSenaraiTanahMilik(idPermohonan);
                logger.info("size senarai tanah milik masa carian hakmilik: " + senaraiTanahMilik.size());
                for (int i = 0; i < senaraiTanahMilik.size(); i++) {
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(senaraiTanahMilik.get(i).getId());
                    enforceService.deleteHakmilikPermohonan(hakmilikPermohonan);
                }
                addSimpleMessage("Maklumat hakmilik berjaya dijumpai.");
            }
        }


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");

    }

    public Resolution addRecordHakmilik() {
        logger.info("::addRecordHakmilik");
        return new JSP("penguatkuasaan/popup_tambah_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution simpanTanahMilik() {
        logger.info("::simpanTanahMilik");

        InfoAudit ia = new InfoAudit();

        //delete from hakmilikPermohonan for tanah milik
        if (multipleHakmilik == true) {
            logger.info("::delete hakmilikPermohonan for multiple id hakmilik : " + hakmilikPermohonanList.size());
            for (HakmilikPermohonan h : hakmilikPermohonanList) {
                enforceService.deleteHakmilikPermohonan(h);
            }
        } else {
            logger.info("::delete hakmilikPermohonan for single id hakmilik");
            if (hakmilikPermohonan != null) {
                enforceService.deleteHakmilikPermohonan(hakmilikPermohonan);
            }
        }

        id = getContext().getRequest().getParameter("id");
        if (id != null && StringUtils.isNotBlank(id)) {
            hakmilikPermohonanKerajaan = hakmilikPermohonanDAO.findById(Long.parseLong(id));
        }

        if (hakmilikPermohonanKerajaan != null) {
            System.out.println("::: update existing tanah milik");
            ia = hakmilikPermohonanKerajaan.getInfoAudit();
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
        } else {
            System.out.println("::: add new tanah milik");
            hakmilikPermohonanKerajaan = new HakmilikPermohonan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }


        hakmilikPermohonanKerajaan.setPermohonan(permohonan);
        hakmilikPermohonanKerajaan.setInfoAudit(ia);
        hakmilikPermohonanKerajaan.setNoLot(noLot);

        if (kodLot != null && StringUtils.isNotBlank(kodLot)) {
            hakmilikPermohonanKerajaan.setLot(kodLotDAO.findById(kodLot));
        } else {
            hakmilikPermohonanKerajaan.setLot(null);
        }

        if (luas != null) {
            BigDecimal luasTanah = new BigDecimal(luas);
            System.out.println("luasTanah : " + luasTanah);
            hakmilikPermohonanKerajaan.setLuasTerlibat(luasTanah);
        } else {
            hakmilikPermohonanKerajaan.setLuasTerlibat(null);
        }

        if (kodLuas != null && StringUtils.isNotBlank(kodLuas)) {
            hakmilikPermohonanKerajaan.setKodUnitLuas(kodUOMDAO.findById(kodLuas));
        } else {
            hakmilikPermohonanKerajaan.setKodUnitLuas(null);
        }

        if (kategoriTanah != null && StringUtils.isNotBlank(kategoriTanah)) {
            hakmilikPermohonanKerajaan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(kategoriTanah));
        } else {
            hakmilikPermohonanKerajaan.setKategoriTanahBaru(null);
        }

        if (kodDun != null && StringUtils.isNotBlank(kodDun)) {
            hakmilikPermohonanKerajaan.setKodDUN(kodDunDAO.findById(kodDun));
        } else {
            hakmilikPermohonanKerajaan.setKodDUN(null);
        }

        if (kodPBT != null && StringUtils.isNotBlank(kodPBT)) {
            hakmilikPermohonanKerajaan.setKodPbt(kodPBTDAO.findById(kodPBT));
        } else {
            hakmilikPermohonanKerajaan.setKodPbt(null);
        }

        hakmilikPermohonanKerajaan.setCatatan(catatan);
        hakmilikPermohonanKerajaan.setLokasi(badanPengawal);

        enforceService.simpanhakmilikPermohonan(hakmilikPermohonanKerajaan);

        if (!(listLaporanTanah.isEmpty())) {
            System.out.println("id laporan tanah : " + laporanTanah.getIdLaporan());
            LOG.info("Update Laporan Tanah ::: add tanah kerajaan");
            ia = listLaporanTanah.get(0).getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("New Laporan Tanah ::: add tanah kerajaan");
            laporanTanah = new LaporanTanah();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }

        laporanTanah.setJenisTanah('K');
        laporanTanah.setPermohonan(permohonan);
        laporanTanah.setInfoAudit(ia);
        laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution editRecordHakmilik() {
        logger.info("::editRecordHakmilik");

        id = getContext().getRequest().getParameter("id");
        if (id != null && StringUtils.isNotBlank(id)) {
            hakmilikPermohonanKerajaan = hakmilikPermohonanDAO.findById(Long.parseLong(id));

            if (hakmilikPermohonanKerajaan != null) {
                noLot = hakmilikPermohonanKerajaan.getNoLot();
                if (hakmilikPermohonanKerajaan.getLot() != null) {
                    kodLot = hakmilikPermohonanKerajaan.getLot().getKod();
                }
                if (hakmilikPermohonanKerajaan.getKategoriTanahBaru() != null) {
                    kategoriTanah = hakmilikPermohonanKerajaan.getKategoriTanahBaru().getKod();
                }
                if (hakmilikPermohonanKerajaan.getKodUnitLuas() != null) {
                    kodLuas = hakmilikPermohonanKerajaan.getKodUnitLuas().getKod();
                }
                if (hakmilikPermohonanKerajaan.getKodDUN() != null) {
                    kodDun = hakmilikPermohonanKerajaan.getKodDUN().getKod();
                }
                if (hakmilikPermohonanKerajaan.getLuasTerlibat() != null) {
                    luas = hakmilikPermohonanKerajaan.getLuasTerlibat().toString();
                }
                if (hakmilikPermohonanKerajaan.getKodPbt() != null) {
                    kodPBT = hakmilikPermohonanKerajaan.getKodPbt().getKod();
                }

                catatan = hakmilikPermohonanKerajaan.getCatatan();
                badanPengawal = hakmilikPermohonanKerajaan.getLokasi();
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution deleteRecordHakmilik() {
        logger.info("::deleteRecordHakmilik");

        id = getContext().getRequest().getParameter("id");
        if (id != null && StringUtils.isNotBlank(id)) {
            hakmilikPermohonanKerajaan = hakmilikPermohonanDAO.findById(Long.parseLong(id));
            enforceService.deleteHakmilikPermohonan(hakmilikPermohonanKerajaan);

        }
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution addRecordJenisHakisan() {
        logger.info(":::::addRecordJenisHakisan");
        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution editRecordJenisHakisan() {
        logger.info(":::::editRecordJenisHakisan");
        idMH = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(idMH)) {
            statusEdit = true;
            hakmilikPermohonanHakisan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
            if (hakmilikPermohonanHakisan != null) {
                if (hakmilikPermohonanHakisan.getHakmilik() != null) {
                    idHakmilik = hakmilikPermohonanHakisan.getHakmilik().getIdHakmilik();
                    statusTanah = "H";
                } else {
                    statusTanah = "K";
                }

                if (hakmilikPermohonanHakisan.getJenisPenggunaanTanah() != null) {
                    kodKategoriTanah = hakmilikPermohonanHakisan.getJenisPenggunaanTanah().getKod();
                }
                if (hakmilikPermohonanHakisan.getLot() != null) {
                    kodLot = hakmilikPermohonanHakisan.getLot().getKod();
                }
                if (hakmilikPermohonanHakisan.getKodUnitLuas() != null) {
                    kodLuas = hakmilikPermohonanHakisan.getKodUnitLuas().getKod();
                }

                if (hakmilikPermohonanHakisan.getKodDUN() != null) {
                    kodDunHakisan = hakmilikPermohonanHakisan.getKodDUN().getKod();
                }

                if (hakmilikPermohonanHakisan.getLuasUkurHalusUom() != null) {
                    kodLuasHakisan = hakmilikPermohonanHakisan.getLuasUkurHalusUom().getKod();
                }
                catatan = hakmilikPermohonanHakisan.getCatatan();
                badanPengawal = hakmilikPermohonanHakisan.getLokasi();
                noLot = hakmilikPermohonanHakisan.getNoLot();
                luas = hakmilikPermohonanHakisan.getLuasTerlibat().toString();
                jenisHakisan = hakmilikPermohonanHakisan.getJenisHakisan();
                luasHakisan = hakmilikPermohonanHakisan.getLuasUkurHalus().toString();
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution viewRecordJenisHakisan() {
        logger.info(":::::viewRecordJenisHakisan");

        statusView = true;

        idMH = getContext().getRequest().getParameter("idMH");
        if (StringUtils.isNotBlank(idMH)) {
            hakmilikPermohonanHakisan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
        }
        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idMH = getContext().getRequest().getParameter("idMH");
        System.out.println("id hakmilik: " + idHakmilik);
        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        if (StringUtils.isNotBlank(idMH)) {
            hakmilikPermohonanHakisan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
        }

        if (hakmilikPermohonanHakisan != null) {
            ia = hakmilikPermohonanHakisan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            hakmilikPermohonanHakisan = new HakmilikPermohonan();
        }

        hakmilikPermohonanHakisan.setPermohonan(permohonan);
        hakmilikPermohonanHakisan.setInfoAudit(ia);
        hakmilikPermohonanHakisan.setNoLot(noLot);
        hakmilikPermohonanHakisan.setLuasTerlibat(BigDecimal.valueOf(Double.parseDouble(luas)));


        if (hakmilik != null) {
            System.out.println("hakmilik save : " + hakmilik.getIdHakmilik());
            hakmilikPermohonanHakisan.setHakmilik(hakmilik);
        } else {
            hakmilikPermohonanHakisan.setHakmilik(null);
        }
        if (kodKategoriTanah != null) {
            KodKategoriTanah kttn = kodKategoriTanahDAO.findById(kodKategoriTanah);
            hakmilikPermohonanHakisan.setJenisPenggunaanTanah(kttn);
        } else {
            hakmilikPermohonanHakisan.setJenisPenggunaanTanah(null);
        }

        if (kodLot != null) {
            KodLot kl = kodLotDAO.findById(kodLot);
            hakmilikPermohonanHakisan.setLot(kl);
        } else {
            hakmilikPermohonanHakisan.setLot(null);
        }

        if (kodLuas != null) {
            KodUOM k = kodUOMDAO.findById(kodLuas);
            hakmilikPermohonanHakisan.setKodUnitLuas(k);
        } else {
            hakmilikPermohonanHakisan.setKodUnitLuas(null);
        }

        if (kodDunHakisan != null && StringUtils.isNotBlank(kodDunHakisan)) {
            hakmilikPermohonanHakisan.setKodDUN(kodDunDAO.findById(kodDunHakisan));
        } else {
            hakmilikPermohonanHakisan.setKodDUN(null);
        }

        if (StringUtils.isNotBlank(luasHakisan)) {
            hakmilikPermohonanHakisan.setLuasUkurHalus(BigDecimal.valueOf(Double.parseDouble(luasHakisan)));
        } else {
            hakmilikPermohonanHakisan.setLuasUkurHalus(null);
        }
        if (StringUtils.isNotBlank(kodLuasHakisan)) {
            hakmilikPermohonanHakisan.setLuasUkurHalusUom(kodUOMDAO.findById(kodLuasHakisan));
        } else {
            hakmilikPermohonanHakisan.setLuasUkurHalusUom(null);
        }
        hakmilikPermohonanHakisan.setCatatan(catatan);
        hakmilikPermohonanHakisan.setLokasi(badanPengawal);

        hakmilikPermohonanHakisan.setJenisHakisan(jenisHakisan);

        enforceService.simpanhakmilikPermohonan(hakmilikPermohonanHakisan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution findHakmilikLTNH() {
        logger.info("-----------findHakmilikLTNH-------------");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.info("id hakmilik carian :" + idHakmilik);
        fromPage = "LTNH";

        List<HakmilikPermohonan> listHp = enforceService.checkExistingHakmilikPermohonan(idPermohonan, idHakmilik);
        if (listHp.size() == 1) {
            statusCarian = "E"; //exist
            addSimpleError("Id hakmilik ini sudah wujud. Sila masukkan id hakmilik yang lain");
            return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");
        }

        try {
            hakmilik = enforceService.semakIdHakmilik(idHakmilik);
            if (hakmilik != null) {
                statusCarian = "W"; //W = wujud

            } else {
                statusCarian = "TW"; //TW = Tidak Wujud
                addSimpleError("Maklumat hakmilik tidak dijumpai");
                return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");

            }
            System.out.println(":::::: keputusan carian hakmilik ::::::");
            System.out.println("statusCarian :" + statusCarian);
            System.out.println("no lot :" + hakmilik.getNoLot());
            System.out.println("kodLotCarian :" + hakmilik.getLot().getKod());
        } catch (Exception ex) {
            addSimpleError(ex.getMessage());

        }
        return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution findHakmilik() {
        logger.info("-----------findHakmilik-------------");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.info("id hakmilik carian :" + idHakmilik);

        try {
            hakmilik = enforceService.semakIdHakmilik(idHakmilik);
            if (hakmilik != null) {
                statusCarian = "W"; //W = wujud
                noLot = hakmilik.getNoLot();
                luas = hakmilik.getLuas().toString();
                if (hakmilik.getLot() != null) {
                    kodLotCarian = hakmilik.getLot().getKod();
                }
                if (hakmilik.getKegunaanTanah().getKategoriTanah().getKod() != null) {
                    kodKategoriTanahCarian = hakmilik.getKegunaanTanah().getKategoriTanah().getKod();
                }

                if (hakmilik.getKodUnitLuas() != null) {
                    kodLuasCarian = hakmilik.getKodUnitLuas().getKod();
                }
            } else {
                statusCarian = "TW"; //TW = Tidak Wujud
            }
            System.out.println(":::::: keputusan carian hakmilik ::::::");
            System.out.println("statusCarian :" + statusCarian);
            System.out.println("no lot :" + noLot);
            System.out.println("kodLotCarian :" + kodLotCarian);
            System.out.println("kodKategoriTanahCarian :" + kodKategoriTanahCarian);
            System.out.println("luas :" + luas);
            System.out.println("kodLuasCarian :" + kodLuasCarian);
        } catch (Exception ex) {
            statusCarian = "E";
            addSimpleError(ex.getMessage());

        }

        if (multipleHakmilik == true) {
            logger.info("::edit page");
            return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");
        }

        return new JSP("penguatkuasaan/popup_tambah_hakisan.jsp").addParameter("popup", "true");
    }

    public Resolution deleteJenisHakisan() {
        logger.info(":::: deleteJenisHakisan");
        idMH = getContext().getRequest().getParameter("id");


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(idMH)) {
                hakmilikPermohonanHakisan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
                if (hakmilikPermohonanHakisan != null) {
                    hakmilikPermohonanDAO.delete(hakmilikPermohonanHakisan);
                }
            }

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Resolution addHakmilik() {
        logger.info("::addHakmilik");
        fromPage = "LTNH";
        return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution simpanHakmilik() {
        logger.info("::simpanHakmilik");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        jenisLaporan = "L2TH";

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idMH = getContext().getRequest().getParameter("idMH");

        if (idHakmilik != null && StringUtils.isNotBlank(idHakmilik)) {

            hakmilik = hakmilikDAO.findById(idHakmilik);

            System.out.println("id hakmilik: " + idHakmilik);
            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
            }

            if (StringUtils.isNotBlank(idMH)) {
                multipleHakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
            }

            if (multipleHakmilikPermohonan == null) {
                multipleHakmilikPermohonan = new HakmilikPermohonan();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = multipleHakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            multipleHakmilikPermohonan.setHakmilik(hakmilik);
            multipleHakmilikPermohonan.setPermohonan(permohonan);
            multipleHakmilikPermohonan.setInfoAudit(ia);
            multipleHakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            multipleHakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            multipleHakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            multipleHakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            enforceService.simpanhakmilikPermohonan(multipleHakmilikPermohonan);

            if (!(listLaporanTanah.isEmpty())) {
                System.out.println("id laporan tanah : " + laporanTanah.getIdLaporan());
                LOG.info("Update Laporan Tanah ::: update tanah hakmilik");
                ia = listLaporanTanah.get(0).getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                LOG.info("New Laporan Tanah ::: add tanah hakmilik");
                laporanTanah = new LaporanTanah();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            }

            laporanTanah.setJenisTanah('H');
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(ia);
            laporanTanah.setJenisLaporan(jenisLaporan);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);


            senaraiTanahMilik = enforceService.findSenaraiTanahMilik(idPermohonan);
            logger.info("size senarai tanah milik masa carian hakmilik: " + senaraiTanahMilik.size());
            for (int i = 0; i < senaraiTanahMilik.size(); i++) {
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(senaraiTanahMilik.get(i).getId());
                enforceService.deleteHakmilikPermohonan(hakmilikPermohonan);
            }
        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanTanah2ActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public KodRizabDAO getKodRizabDAO() {
        return kodRizabDAO;
    }

    public void setKodRizabDAO(KodRizabDAO kodRizabDAO) {
        this.kodRizabDAO = kodRizabDAO;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public String getAdaBangunan() {
        return adaBangunan;
    }

    public void setAdaBangunan(String adaBangunan) {
        this.adaBangunan = adaBangunan;
    }

    public String getJenisjalan() {
        return jenisjalan;
    }

    public void setJenisjalan(String jenisjalan) {
        this.jenisjalan = jenisjalan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getUlasanPPT() {
        return ulasanPPT;
    }

    public void setUlasanPPT(String ulasanPPT) {
        this.ulasanPPT = ulasanPPT;
    }

    public String getUlasanPPTK() {
        return ulasanPPTK;
    }

    public void setUlasanPPTK(String ulasanPPTK) {
        this.ulasanPPTK = ulasanPPTK;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
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

    public String getTarikhLawatan() {
        return tarikhLawatan;
    }

    public void setTarikhLawatan(String tarikhLawatan) {
        this.tarikhLawatan = tarikhLawatan;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public String getTarikhDiusahakan() {
        return tarikhDiusahakan;
    }

    public void setTarikhDiusahakan(String tarikhDiusahakan) {
        this.tarikhDiusahakan = tarikhDiusahakan;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getkodDunLaporanTanah() {
        return kodDunLaporanTanah;
    }

    public void setkodDunLaporanTanah(String kodDunLaporanTanah) {
        this.kodDunLaporanTanah = kodDunLaporanTanah;
    }

    public String getPaparKodDun() {
        return paparKodDun;
    }

    public void setPaparKodDun(String paparKodDun) {
        this.paparKodDun = paparKodDun;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoWartaKerajaan() {
        return noWartaKerajaan;
    }

    public void setNoWartaKerajaan(String noWartaKerajaan) {
        this.noWartaKerajaan = noWartaKerajaan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<LaporanTanahSempadan> getSenaraiLaporanTanahSempadan() {
        return senaraiLaporanTanahSempadan;
    }

    public void setSenaraiLaporanTanahSempadan(List<LaporanTanahSempadan> senaraiLaporanTanahSempadan) {
        this.senaraiLaporanTanahSempadan = senaraiLaporanTanahSempadan;
    }

    public List<PermohonanLaporanBangunan> getSenaraiPermohonanLaporanBangunan() {
        return senaraiPermohonanLaporanBangunan;
    }

    public void setSenaraiPermohonanLaporanBangunan(List<PermohonanLaporanBangunan> senaraiPermohonanLaporanBangunan) {
        this.senaraiPermohonanLaporanBangunan = senaraiPermohonanLaporanBangunan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getKodDunLaporanTanah() {
        return kodDunLaporanTanah;
    }

    public void setKodDunLaporanTanah(String kodDunLaporanTanah) {
        this.kodDunLaporanTanah = kodDunLaporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiTanahMilik() {
        return senaraiTanahMilik;
    }

    public void setSenaraiTanahMilik(List<HakmilikPermohonan> senaraiTanahMilik) {
        this.senaraiTanahMilik = senaraiTanahMilik;
    }

    public List<HakmilikWaris> getHakmilikWarisList() {
        return hakmilikWarisList;
    }

    public void setHakmilikWarisList(List<HakmilikWaris> hakmilikWarisList) {
        this.hakmilikWarisList = hakmilikWarisList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakBerkepentinganList() {
        return pihakBerkepentinganList;
    }

    public void setPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> pihakBerkepentinganList) {
        this.pihakBerkepentinganList = pihakBerkepentinganList;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public String getKodDun() {
        return kodDun;
    }

    public void setKodDun(String kodDun) {
        this.kodDun = kodDun;
    }

    public String getKodStrukturTanah() {
        return kodStrukturTanah;
    }

    public void setKodStrukturTanah(String kodStrukturTanah) {
        this.kodStrukturTanah = kodStrukturTanah;
    }

    public String getJarakJalanUOM() {
        return jarakJalanUOM;
    }

    public void setJarakJalanUOM(String jarakJalanUOM) {
        this.jarakJalanUOM = jarakJalanUOM;
    }

    public String getJarakKeretapiUOM() {
        return jarakKeretapiUOM;
    }

    public void setJarakKeretapiUOM(String jarakKeretapiUOM) {
        this.jarakKeretapiUOM = jarakKeretapiUOM;
    }

    public String getJarakLautUOM() {
        return jarakLautUOM;
    }

    public void setJarakLautUOM(String jarakLautUOM) {
        this.jarakLautUOM = jarakLautUOM;
    }

    public String getJarakSungaiUOM() {
        return jarakSungaiUOM;
    }

    public void setJarakSungaiUOM(String jarakSungaiUOM) {
        this.jarakSungaiUOM = jarakSungaiUOM;
    }

    public String getKodKecerunanTanah() {
        return kodKecerunanTanah;
    }

    public void setKodKecerunanTanah(String kodKecerunanTanah) {
        this.kodKecerunanTanah = kodKecerunanTanah;
    }

    public List<LaporanTanah> getListLaporanTanah() {
        return listLaporanTanah;
    }

    public void setListLaporanTanah(List<LaporanTanah> listLaporanTanah) {
        this.listLaporanTanah = listLaporanTanah;
    }

    public PermohonanLaporanUlasan getMohonLaporUlas() {
        return mohonLaporUlas;
    }

    public void setMohonLaporUlas(PermohonanLaporanUlasan mohonLaporUlas) {
        this.mohonLaporUlas = mohonLaporUlas;
    }

    public PermohonanLaporanUlasan getMohonLaporUlasPPT() {
        return mohonLaporUlasPPT;
    }

    public void setMohonLaporUlasPPT(PermohonanLaporanUlasan mohonLaporUlasPPT) {
        this.mohonLaporUlasPPT = mohonLaporUlasPPT;
    }

    public PermohonanLaporanUlasan getMohonLaporUlasPPTK() {
        return mohonLaporUlasPPTK;
    }

    public void setMohonLaporUlasPPTK(PermohonanLaporanUlasan mohonLaporUlasPPTK) {
        this.mohonLaporUlasPPTK = mohonLaporUlasPPTK;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
        return senaraiHakmilikUrusan;
    }

    public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
        this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
    }

    public Date getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(Date tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getStatusTanah() {
        return statusTanah;
    }

    public void setStatusTanah(String statusTanah) {
        this.statusTanah = statusTanah;
    }

    public String getBadanPengawal() {
        return badanPengawal;
    }

    public void setBadanPengawal(String badanPengawal) {
        this.badanPengawal = badanPengawal;
    }

    public HakmilikPermohonan getHakmilikPermohonanKerajaan() {
        return hakmilikPermohonanKerajaan;
    }

    public void setHakmilikPermohonanKerajaan(HakmilikPermohonan hakmilikPermohonanKerajaan) {
        this.hakmilikPermohonanKerajaan = hakmilikPermohonanKerajaan;
    }

    public Boolean getMaklumatTanahSek49() {
        return maklumatTanahSek49;
    }

    public void setMaklumatTanahSek49(Boolean maklumatTanahSek49) {
        this.maklumatTanahSek49 = maklumatTanahSek49;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public Boolean getStatusEdit() {
        return statusEdit;
    }

    public void setStatusEdit(Boolean statusEdit) {
        this.statusEdit = statusEdit;
    }

    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public String getJenisHakisan() {
        return jenisHakisan;
    }

    public void setJenisHakisan(String jenisHakisan) {
        this.jenisHakisan = jenisHakisan;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }

    public String getKodLotCarian() {
        return kodLotCarian;
    }

    public void setKodLotCarian(String kodLotCarian) {
        this.kodLotCarian = kodLotCarian;
    }

    public String getKodKategoriTanahCarian() {
        return kodKategoriTanahCarian;
    }

    public void setKodKategoriTanahCarian(String kodKategoriTanahCarian) {
        this.kodKategoriTanahCarian = kodKategoriTanahCarian;
    }

    public String getKodLuasCarian() {
        return kodLuasCarian;
    }

    public void setKodLuasCarian(String kodLuasCarian) {
        this.kodLuasCarian = kodLuasCarian;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getKodDunHakisan() {
        return kodDunHakisan;
    }

    public void setKodDunHakisan(String kodDunHakisan) {
        this.kodDunHakisan = kodDunHakisan;
    }

    public HakmilikPermohonan getHakmilikPermohonanHakisan() {
        return hakmilikPermohonanHakisan;
    }

    public void setHakmilikPermohonanHakisan(HakmilikPermohonan hakmilikPermohonanHakisan) {
        this.hakmilikPermohonanHakisan = hakmilikPermohonanHakisan;
    }

    public Boolean getStatusView() {
        return statusView;
    }

    public void setStatusView(Boolean statusView) {
        this.statusView = statusView;
    }

    public Boolean getStatusUlasan() {
        return statusUlasan;
    }

    public void setStatusUlasan(Boolean statusUlasan) {
        this.statusUlasan = statusUlasan;
    }

    public List<HakmilikPihakBerkepentingan> getPihakBerkepentinganListSek49() {
        return pihakBerkepentinganListSek49;
    }

    public void setPihakBerkepentinganListSek49(List<HakmilikPihakBerkepentingan> pihakBerkepentinganListSek49) {
        this.pihakBerkepentinganListSek49 = pihakBerkepentinganListSek49;
    }

    public String getAnggaranLuasUOM() {
        return anggaranLuasUOM;
    }

    public void setAnggaranLuasUOM(String anggaranLuasUOM) {
        this.anggaranLuasUOM = anggaranLuasUOM;
    }

    public String getKodPBT() {
        return kodPBT;
    }

    public void setKodPBT(String kodPBT) {
        this.kodPBT = kodPBT;
    }

    public String getLuasHakisan() {
        return luasHakisan;
    }

    public void setLuasHakisan(String luasHakisan) {
        this.luasHakisan = luasHakisan;
    }

    public String getKodLuasHakisan() {
        return kodLuasHakisan;
    }

    public void setKodLuasHakisan(String kodLuasHakisan) {
        this.kodLuasHakisan = kodLuasHakisan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonanForPihakSek49() {
        return senaraiHakmilikPermohonanForPihakSek49;
    }

    public void setSenaraiHakmilikPermohonanForPihakSek49(List<HakmilikPermohonan> senaraiHakmilikPermohonanForPihakSek49) {
        this.senaraiHakmilikPermohonanForPihakSek49 = senaraiHakmilikPermohonanForPihakSek49;
    }

    public Boolean getMultipleHakmilik() {
        return multipleHakmilik;
    }

    public void setMultipleHakmilik(Boolean multipleHakmilik) {
        this.multipleHakmilik = multipleHakmilik;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }
}

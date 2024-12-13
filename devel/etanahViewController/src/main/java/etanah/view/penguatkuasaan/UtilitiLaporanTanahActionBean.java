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
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import etanah.model.KodBandarPekanMukim;
import etanah.service.common.TanahRizabService;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
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
import etanah.model.PermohonanManual;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_laporan_tanah")
public class UtilitiLaporanTanahActionBean extends AbleActionBean {

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
    LaporanTanahService laporanTanahService;
    @Inject
    EnforceService enforceService;
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
    KodLotDAO kodLotDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    private Hakmilik hakmilik;
    List<HakmilikPermohonan> senaraiHakmilik;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikUrusan hakmilikUrusan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakmilikList;
    private TanahRizabPermohonan tanahrizabpermohonan;
    private TanahRizabPermohonan trizabpermohonan;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<TanahRizabPermohonan> tanahRizabList;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    KodRizabDAO kodRizabDAO;
    @Inject
    PengambilanService service;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodDUNDAO kodDunDAO;
    @Inject
    KodPBTDAO kodPBTDAO;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    private KodBandarPekanMukim bandarPekanMukim;
    private KodKecerunanTanah kecerunanTanah;
    private KodDaerah daerah;
    private KodCawangan cawangan;
    private String noLot;
    private String noLitho;
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
    private Boolean laporanTanahFlag = false;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private static Logger logger = Logger.getLogger(UtilitiLaporanTanahActionBean.class);
    private String tarikhDiusahakan;
    private String paparKodDun;
    private String kodNegeri;
    private String noWartaKerajaan;
    private String idPermohonan;
    etanahActionBeanContext ctx;
    private String jenisLaporan;
    private String taskId;
    private String idMohonHakmilik;
    private String idLaporanTanah;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private Boolean statusNorujukan = Boolean.FALSE;
    private List<LaporanTanah> listLaporanTanah = new ArrayList<LaporanTanah>();
    private PermohonanManual permohonanManual;
    private String noFail;
    private String kodKategoriTanahSempadan;
    private String kodLotTanahSempadan;
    private String noLotTanahSempadan;
    private String idValue;
    private String idHakmilikTanahSempadan;
    private String statusTanah;
    private List<HakmilikPermohonan> senaraiTanahMilik = new ArrayList<HakmilikPermohonan>();
    private List<HakmilikWaris> hakmilikWarisList = new ArrayList<HakmilikWaris>();
    private List<HakmilikPihakBerkepentingan> pihakBerkepentinganListSek49;
    private List<HakmilikPihakBerkepentingan> pihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
    private List<HakmilikUrusan> senaraiHakmilikUrusan = new ArrayList<HakmilikUrusan>();
    private String kodDun;
    private String kodKecerunanTanah;
    private String kodStrukturTanah;
    private String jarakJalanUOM;
    private String jarakSungaiUOM;
    private String jarakKeretapiUOM;
    private String jarakLautUOM;
    private String anggaranLuasUOM;
    private PermohonanLaporanUlasan mohonLaporUlas;
    private PermohonanLaporanUlasan mohonLaporUlasPPT;
    private PermohonanLaporanUlasan mohonLaporUlasPPTK;
    private String tarikhSidang;
    private List<LaporanTanahSempadan> senaraiLaporanTanahSempadan = new ArrayList<LaporanTanahSempadan>();
    private List<PermohonanLaporanBangunan> senaraiPermohonanLaporanBangunan = new ArrayList<PermohonanLaporanBangunan>();
    private PermohonanLaporanBangunan permohonanLaporanBangunan;
    private String jenisKemasukan;
    private String hakmilikCarian;
    private Boolean multipleHakmilik = Boolean.FALSE;
    private HakmilikPermohonan multipleHakmilikPermohonan;
    private String kodPBT;
    private String id;
    private HakmilikPermohonan hakmilikPermohonanKerajaan;
    private String kodLot;
    private String luas;
    private String kodLuas;
    private String kategoriTanah;
    private String catatan;
    private String badanPengawal;
    private String jenisBangunan;
    private BigDecimal ukuran;
    private String uomUkuran;
    private String namaPemunya;
    private String namaPenyewa;
    private String idLaporBangunan;
    private String ukuranTemp; //save at nama penyewa for temp
    private LaporanTanahSempadan laporanTanahSempadan;
    private BigDecimal nilai;
    private String idLaporTanahSpdn;
    private String jenisSempadan;
    private String milikKerajaan;
    private String statusDisplay;
    private String statusCarian;

//    class PermohonanCache implements Serializable {
//
//        Permohonan permohonan;
//        String idPermohonan;
//    }
//
//    class LaporanTanahCache implements Serializable {
//
//        String idLaporanTanah;
//        LaporanTanah laporanTanah;
//    }
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_laporan_tanah.jsp");
    }

    public Resolution searchLaporanTanah() {
        logger.info("------------searchLaporanTanah-------------");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("idPermohonan : " + idPermohonan);

        if (idPermohonan != null) {
            kodNegeri = conf.getProperty("kodNegeri");
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                //get mohon_hakmilik (all data which have id hakmilik or not)

                if (permohonan.getPermohonanSebelum() != null) {
                    //1) After create new IP
                    logger.info(":::: id permohonan sebelum exist ::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                    permohonan = permohonan.getPermohonanSebelum();

                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                        Long id = null;
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
                                        logger.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                        if (senaraiIdPermohonan.length > 1) {
                                            idPertama = senaraiIdPermohonan[0];
                                            idKedua = senaraiIdPermohonan[1];

                                        }
                                    }
                                    logger.info("::: idPertama : " + idPertama);
                                    logger.info("::: idKedua : " + idKedua);

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
                                logger.info("::: using id MH");
                                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilikById(id);
                            } else {
                                logger.info("::: using id idPermohonan");
                                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                            }
                        }

                        listLaporanTanah = enforceService.findListLaporanTanah(permohonan.getIdPermohonan(), id.toString());
                    }
                } else {
                    listLaporanTanah = laporanTanahService.getListLaporanTanah(permohonan.getIdPermohonan(), "LTNH");
                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                }

                //get mohon_lapor_tanah info (ex:size,idLapor)
                logger.info("size list laporan tanah : " + listLaporanTanah.size());


//                etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//                savePermohonanToSession(ctx);

                if (!(listLaporanTanah.isEmpty())) {
                    System.out.println("gud luck");
                    getInformation(Long.toString(listLaporanTanah.get(0).getIdLaporan()), idPermohonan);
                }

            } else {
                addSimpleError("Id Permohonan " + idPermohonan + " ini tidak wujud.");
                return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_laporan_tanah.jsp");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
            return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_laporan_tanah.jsp");
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        }


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat laporan tanah untuk " + idPermohonan + " telah dijumpai.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian_laporan_tanah.jsp");
    }

    public final void getInformation(String idLaporanTanah, String idPermohonan) {

        jenisLaporan = "LTNH";
//        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        logger.info(":::::::::::::::::::::::::::::::: getInformation (Id Laporan Tanah) :::" + idLaporanTanah);
        logger.info(":::::::::::::::::::::::::::::::: getInformation (Id Permohonan) :::" + idPermohonan);

//        getUrusanfromSession();

        if (StringUtils.isBlank(idLaporanTanah)) {
            idLaporanTanah = getContext().getRequest().getParameter("idLaporanTanah");
        }
        idMohonHakmilik = idLaporanTanah;
        logger.info(":::::::::::::::::::::::::::::::: idMohonHakmilik getInformation :::" + idMohonHakmilik);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() != null) {
                //1) After create new IP
                logger.info(":::: id permohonan sebelum exist ::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                permohonan = permohonan.getPermohonanSebelum();

                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    Long id = null;
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
                                    logger.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                    if (senaraiIdPermohonan.length > 1) {
                                        idPertama = senaraiIdPermohonan[0];
                                        idKedua = senaraiIdPermohonan[1];

                                    }
                                }
                                logger.info("::: idPertama : " + idPertama);
                                logger.info("::: idKedua : " + idKedua);

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
                            logger.info("::: using id MH");
                            senaraiHakmilikPermohonan = enforceService.findListMohonHakmilikById(id);
                        } else {
                            logger.info("::: using id idPermohonan");
                            senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                        }
                    }

                    listLaporanTanah = enforceService.findListLaporanTanah(permohonan.getIdPermohonan(), id.toString());
                }
            } else {
                listLaporanTanah = laporanTanahService.getListLaporanTanah(permohonan.getIdPermohonan(), jenisLaporan);
                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
            }

            //get mohon_lapor_tanah info (ex:size,idLapor)
            logger.info("size list laporan tanah : " + listLaporanTanah.size());

            permohonanManual = enforceService.findPermohonanManual(permohonan.getIdPermohonan());
            if (permohonanManual != null) {
                noFail = permohonanManual.getNoFail();
            }
        }
        logger.info("::::::::::::::::::::::::::::::::id lapor tanah getInformation :::" + idLaporanTanah);
        if (StringUtils.isNotBlank(idLaporanTanah)) {
            laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporanTanah));
//            saveLaporanTanahToSession(ctx);
            if (laporanTanah != null) {
                statusTanah = laporanTanah.getJenisTanah() != null && !Character.isWhitespace(laporanTanah.getJenisTanah()) ? laporanTanah.getJenisTanah().toString() : new String();
                logger.info("Jenis tanah : " + statusTanah);
                ArrayList senaraiHakmilik = new ArrayList<String>();
                if (laporanTanah.getHakmilikPermohonan() != null) {
                    hakmilikPermohonanList = enforceService.findListHakmilikPermohonan(laporanTanah.getHakmilikPermohonan().getId(), "not null");
                    logger.info("size hakmilikPermohonanList (ada hakmilik): " + hakmilikPermohonanList.size());

                    //get information for senarai tanah kerajaan
                    senaraiTanahMilik = enforceService.findListHakmilikPermohonan(laporanTanah.getHakmilikPermohonan().getId(), "null");
                    logger.info("size hakmilikPermohonanList (tiada hakmilik): " + senaraiTanahMilik.size());

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
                            senaraiHakmilikUrusan = enforcementService.findHakmilikUrusan(senaraiHakmilik);

                            logger.info("size senarai listWaris : " + hakmilikWarisList.size());
                            logger.info("size senarai listPihakBerkepentingan : " + pihakKepentinganList.size());
                            logger.info("size senarai listPemilik : " + pihakBerkepentinganList.size());
                        }



                    }
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
                if (laporanTanah.getTarikhMulaUsaha2() != null) {
                    tarikhDiusahakan = sdf.format(laporanTanah.getTarikhMulaUsaha2());
                }
                if (laporanTanah.getKecerunanTanah() != null) {
                    kodKecerunanTanah = laporanTanah.getKecerunanTanah().getKod();
                }
                if (laporanTanah.getStrukturTanah() != null) {
                    kodStrukturTanah = laporanTanah.getStrukturTanah().getKod();
                }
                if (laporanTanah.getUsahaLuasUom() != null) {
                    anggaranLuasUOM = laporanTanah.getUsahaLuasUom().getKod();
                }

                senaraiPermohonanLaporanBangunan = enforceService.findByIDLaporTanah(laporanTanah.getIdLaporan());
                senaraiLaporanTanahSempadan = enforceService.findByIDTanahSempadan(laporanTanah.getIdLaporan());
                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());

            }


        }

        //to get data from mohon_ruj_luar (no warta & tarikh siasat)
        List<PermohonanRujukanLuar> listRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        if (permohonan != null) {
            if (permohonan.getSenaraiRujukanLuar() != null) {
                listRujukanLuar = permohonan.getSenaraiRujukanLuar();
            }
        }


        if (!(listRujukanLuar.isEmpty())) {
            for (int i = 0; i < listRujukanLuar.size(); i++) {
                PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                rujL = listRujukanLuar.get(i);
                if (rujL.getKodDokumenRujukan() != null) {
                    if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                        permohonanRujukanLuar = listRujukanLuar.get(i);
                        if (permohonanRujukanLuar != null) {
                            noWartaKerajaan = permohonanRujukanLuar.getNoRujukan();
                            if (permohonanRujukanLuar.getTarikhSidang() != null) {
                                tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
                            }
                            if (permohonanRujukanLuar.getTarikhKuatkuasa() != null) {
                                tarikhLawatan = sdf.format(permohonanRujukanLuar.getTarikhKuatkuasa());
                                jam = sdf1.format(permohonanRujukanLuar.getTarikhKuatkuasa()).substring(11, 13);
                                if (jam.startsWith("0")) {
                                    jam = sdf1.format(permohonanRujukanLuar.getTarikhKuatkuasa()).substring(12, 13);
                                }
                                minit = sdf1.format(permohonanRujukanLuar.getTarikhKuatkuasa()).substring(14, 16);
                                ampm = sdf1.format(permohonanRujukanLuar.getTarikhKuatkuasa()).substring(17, 19);
                            }
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

        logger.info("no warta kerajaan : " + noWartaKerajaan);
        logger.info("tarikhLawatan : " + tarikhLawatan);

    }

    public Resolution addMaklumatTanah() {
        logger.info("::::::::::::::::addMaklumatTanah::::::::::::::::");

        statusDisplay = getContext().getRequest().getParameter("statusDisplay");
        jenisKemasukan = getContext().getRequest().getParameter("jenisKemasukan");
        idLaporanTanah = getContext().getRequest().getParameter("idLaporanTanah");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idValue = getContext().getRequest().getParameter("idValue");

        logger.info("::::::::::::idPermohonan : " + idPermohonan);
        logger.info("::::::::::::idLaporanTanah : " + idLaporanTanah);

        if (StringUtils.isNotBlank(idLaporanTanah)) {
            laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporanTanah));
        }

        if (StringUtils.isNotBlank(idPermohonan)) {
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        }

        if (StringUtils.isNotBlank(statusDisplay) && StringUtils.isNotBlank(jenisKemasukan)) {
            if (statusDisplay.equalsIgnoreCase("edit")) {//PT = Perihal Tanah
                if (jenisKemasukan.equalsIgnoreCase("PT")) {
                    getContext().getRequest().setAttribute("PT", Boolean.TRUE);
                } else if (jenisKemasukan.equalsIgnoreCase("KT")) {//KT = Keadaan Tanah
                    if (laporanTanah != null) {
                        if (laporanTanah.getKecerunanTanah() != null) {
                            kodKecerunanTanah = laporanTanah.getKecerunanTanah().getKod();
                        }

                        if (laporanTanah.getStrukturTanah() != null) {
                            kodStrukturTanah = laporanTanah.getStrukturTanah().getKod();
                        }
                    }

                    getContext().getRequest().setAttribute("KT", Boolean.TRUE);
                } else if (jenisKemasukan.equalsIgnoreCase("TH")) {//TH = Tanah Hakmilik
                    if (StringUtils.isNotBlank(idValue)) {
                        logger.info("::::::::: idValue :" + idValue);
                        HakmilikPermohonan hm = hakmilikPermohonanDAO.findById(Long.parseLong(idValue));
                        if (hm.getHakmilik() != null) {
                            hakmilik = hm.getHakmilik();
                        }
                        if (hm.getKodDUN() != null) {
                            kodDun = hm.getKodDUN().getKod();
                        }
                        if (hm.getKodPbt() != null) {
                            kodPBT = hm.getKodPbt().getKod();
                        }
                        lokasi = hm.getLokaliti();


                    }
                } else if (jenisKemasukan.equalsIgnoreCase("TK")) {//TK = Tanah Kerajaan
                    if (StringUtils.isNotBlank(idValue)) {
                        logger.info("::::::::: idValue :" + idValue);
                        hakmilikPermohonanKerajaan = hakmilikPermohonanDAO.findById(Long.parseLong(idValue));

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

                } else if (jenisKemasukan.equalsIgnoreCase("TS")) {
                    if (StringUtils.isNotBlank(idValue)) {
                        logger.info("::::::::: idValue :" + idValue);
                        laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idValue));
                        if (laporanTanahSempadan != null) {
                            if (laporanTanahSempadan.getHakmilik() != null) {
                                idHakmilikTanahSempadan = laporanTanahSempadan.getHakmilik().getIdHakmilik();
                            }
                            jenisSempadan = laporanTanahSempadan.getJenisSempadan();
                            milikKerajaan = laporanTanahSempadan.getMilikKerajaan();
                            if (laporanTanahSempadan.getKodKategoriTanah() != null) {
                                kodKategoriTanahSempadan = laporanTanahSempadan.getKodKategoriTanah().getKod();
                            }
                            noLotTanahSempadan = laporanTanahSempadan.getNoLot();

                            if (laporanTanahSempadan.getKodLot() != null) {
                                kodLotTanahSempadan = laporanTanahSempadan.getKodLot().getKod();
                            }

                            catatan = laporanTanahSempadan.getCatatan();
                        }
                    }
                } else if (jenisKemasukan.equalsIgnoreCase("JB")) {
                    if (StringUtils.isNotBlank(idValue)) {
                        logger.info("::::::::: idValue :" + idValue);
                        permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idValue));
                        if (permohonanLaporanBangunan != null) {
                            jenisBangunan = permohonanLaporanBangunan.getJenisBangunan();
                            ukuran = permohonanLaporanBangunan.getUkuran();
                            ukuranTemp = permohonanLaporanBangunan.getKeteranganTahunBinaan();
                            if (permohonanLaporanBangunan.getUomUkuran() != null) {
                                uomUkuran = permohonanLaporanBangunan.getUomUkuran().getKod();
                            }
                            nilai = permohonanLaporanBangunan.getNilai();
                            namaPemunya = permohonanLaporanBangunan.getNamaPemunya();
                            namaPenyewa = permohonanLaporanBangunan.getNamaPenyewa();
                        }
                    }
                }
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        }


//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        saveToSession(ctx);

//        getUrusanfromSession();
        getInformation(idLaporanTanah, idPermohonan);

        return new JSP("penguatkuasaan/popup_utiliti_maklumat_tanah.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!searchLaporanTanah", "!showForm", "!reload", "!simpanLaporanTanah", "!addMaklumatTanah"})
    public void rehydrate() {
        logger.info("::::::::::::START OF REHYDRATE");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        jenisLaporan = "LTNH";
        idMohonHakmilik = ctx.getRequest().getParameter("idMohonHakmilik");
        logger.info("id MH data:: dropdown :" + idMohonHakmilik + "--- " + getContext().getRequest().getParameter("idMohonHakmilik"));
        BPelService bpelService = new BPelService();


//        getUrusanfromSession();

        System.out.println(":::::::::::id laporan tanah :" + idLaporanTanah);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "sedia_laporan1";
            logger.info("-------------stageId: BPEL OFF ----" + stageId);
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            //get mohon_hakmilik (all data which have id hakmilik or not)

            if (permohonan != null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    //1) After create new IP
                    logger.info(":::: id permohonan sebelum exist ::::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                    permohonan = permohonan.getPermohonanSebelum();

                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                        Long id = null;
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
                                        logger.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                        if (senaraiIdPermohonan.length > 1) {
                                            idPertama = senaraiIdPermohonan[0];
                                            idKedua = senaraiIdPermohonan[1];

                                        }
                                    }
                                    logger.info("::: idPertama : " + idPertama);
                                    logger.info("::: idKedua : " + idKedua);

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
                                logger.info("::: using id MH");
                                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilikById(id);
                            } else {
                                logger.info("::: using id idPermohonan");
                                senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan(), idPermohonan);
                            }
                        }

                        listLaporanTanah = enforceService.findListLaporanTanah(permohonan.getIdPermohonan(), id.toString());
                    }
                } else {
                    listLaporanTanah = laporanTanahService.getListLaporanTanah(permohonan.getIdPermohonan(), jenisLaporan);
                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(permohonan.getIdPermohonan());
                }
            }







            //get mohon_lapor_tanah info (ex:size,idLapor)
            logger.info("size list laporan tanah : " + listLaporanTanah.size());

            if (!(listLaporanTanah.isEmpty())) {
                System.out.println("gud luck");
                getInformation(Long.toString(listLaporanTanah.get(0).getIdLaporan()), idPermohonan);
            }
        }

        logger.info("::::::::::::END OF REHYDRATE");
    }

//    public final void getPermohonanfromSession() {
//        logger.debug(":::::::::::::: START OF getPermohonanfromSession");
//
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        try {
//            PermohonanCache u = (PermohonanCache) ctx.getWorkData();
//            if (u != null) {
//                permohonan = u.permohonan;
//                if (u.permohonan != null) {
//                    idPermohonan = u.permohonan.getIdPermohonan();
//                }
//
//                logger.info("idPermohonan : " + idPermohonan);
//            } else {
//                logger.debug("no data in session");
//            }
//        } catch (Exception ex) {
//            logger.error(ex);
//            ctx.removeWorkdata();
//        }
//
//        logger.debug(":::::::::::::: END OF getPermohonanfromSession");
//    }
//
//    public final void getLaporanTanahfromSession() {
//        logger.debug(":::::::::::::: START OF getLaporanTanahfromSession");
//
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        try {
//            LaporanTanahCache u = (LaporanTanahCache) ctx.getWorkData();
//            if (u != null) {
//                laporanTanah = u.laporanTanah;
//                if (u.laporanTanah != null) {
//                    idLaporanTanah = Long.toString(u.laporanTanah.getIdLaporan());
//                }
//
//                logger.info("idLaporTanah : " + idLaporanTanah);
//            } else {
//                logger.debug("no data in session");
//            }
//        } catch (Exception ex) {
//            logger.error(ex);
//            ctx.removeWorkdata();
//        }
//
//        logger.debug(":::::::::::::: END OF getLaporanTanahfromSession");
//    }
    public Resolution simpanLaporanTanah() throws Exception {
        logger.info("-----------------simpanLaporanTanah-----------------");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        jenisLaporan = "LTNH";

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanManual = enforceService.findPermohonanManual(permohonan.getIdPermohonan());

            //to get data from mohon_ruj_luar (no warta & tarikh siasat)
            List<PermohonanRujukanLuar> listRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
            if (permohonan != null) {
                if (permohonan.getSenaraiRujukanLuar() != null) {
                    listRujukanLuar = permohonan.getSenaraiRujukanLuar();
                }
            }
            if (!(listRujukanLuar.isEmpty())) {
                for (int i = 0; i < listRujukanLuar.size(); i++) {
                    PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                    rujL = listRujukanLuar.get(i);
                    if (rujL.getKodDokumenRujukan() != null) {
                        if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
                            permohonanRujukanLuar = listRujukanLuar.get(i);

                        }
                    }
                }
            }
        }

        jenisKemasukan = getContext().getRequest().getParameter("jenisKemasukan");
        logger.info("::::::::::::jenisKemasukan : " + jenisKemasukan);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        idLaporanTanah = getContext().getRequest().getParameter("idLaporanTanah");
        logger.info("::::::::::::idLaporanTanah : " + idLaporanTanah);

        idValue = getContext().getRequest().getParameter("idValue");
        logger.info("::::::::::::id primary key for (" + jenisKemasukan + ") : " + idValue);


        if (StringUtils.isNotBlank(idLaporanTanah)) {
            laporanTanah = laporanTanahDAO.findById(Long.parseLong(idLaporanTanah));
        }

        if (StringUtils.isNotBlank(jenisKemasukan)) {
            if (jenisKemasukan.equalsIgnoreCase("TH")) {
                hakmilikCarian = getContext().getRequest().getParameter("hakmilikCarian");

                if (StringUtils.isNotBlank(hakmilikCarian)) {

                    logger.info("id hakmilikCarian Tanah Hakmilik:::: " + hakmilikCarian);
                    if (StringUtils.isNotBlank(hakmilikCarian)) {
                        hakmilik = hakmilikDAO.findById(hakmilikCarian);
                    }

                    if (StringUtils.isNotBlank(idValue)) {
                        multipleHakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idValue));
                    }

                    if (multipleHakmilikPermohonan == null) {
                        multipleHakmilikPermohonan = new HakmilikPermohonan();

                        logger.info("New Laporan Tanah ::: add tanah hakmilik");
                        laporanTanah = new LaporanTanah();

                    } else {
                        infoAudit = multipleHakmilikPermohonan.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(pengguna);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                    multipleHakmilikPermohonan.setHakmilik(hakmilik);
                    multipleHakmilikPermohonan.setPermohonan(permohonan);
                    multipleHakmilikPermohonan.setInfoAudit(infoAudit);
                    multipleHakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
                    multipleHakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
                    multipleHakmilikPermohonan.setNoLot(hakmilik.getNoLot());
                    multipleHakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
                    if (StringUtils.isNotBlank(kodPBT)) {
                        multipleHakmilikPermohonan.setKodPbt(kodPBTDAO.findById(kodPBT));
                    } else {
                        multipleHakmilikPermohonan.setKodPbt(null);
                    }
                    if (StringUtils.isNotBlank(kodDun)) {
                        multipleHakmilikPermohonan.setKodDUN(kodDunDAO.findById(kodDun));
                    } else {
                        multipleHakmilikPermohonan.setKodDUN(null);
                    }
                    multipleHakmilikPermohonan.setLokaliti(lokasi);
                    enforceService.simpanhakmilikPermohonan(multipleHakmilikPermohonan);

                    laporanTanah.setJenisTanah('H');
                    laporanTanah.setHakmilikPermohonan(multipleHakmilikPermohonan);
                    laporanTanah.setPermohonan(permohonan);
                    laporanTanah.setInfoAudit(infoAudit);
                    laporanTanah.setJenisLaporan(jenisLaporan);
                    laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);

                }
            } else if (jenisKemasukan.equalsIgnoreCase("TK")) {
                id = getContext().getRequest().getParameter("id");
                if (StringUtils.isNotBlank(idValue)) {
                    hakmilikPermohonanKerajaan = hakmilikPermohonanDAO.findById(Long.parseLong(idValue));
                }

                if (hakmilikPermohonanKerajaan != null) {
                    System.out.println("::: update existing tanah milik");
                    infoAudit = hakmilikPermohonanKerajaan.getInfoAudit();
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    infoAudit.setDiKemaskiniOleh(pengguna);
                } else {
                    System.out.println("::: add new tanah milik");
                    hakmilikPermohonanKerajaan = new HakmilikPermohonan();

                    laporanTanah = new LaporanTanah();
                }


                hakmilikPermohonanKerajaan.setPermohonan(permohonan);
                hakmilikPermohonanKerajaan.setInfoAudit(infoAudit);
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

                if (StringUtils.isNotBlank(kodLuas)) {
                    hakmilikPermohonanKerajaan.setKodUnitLuas(kodUOMDAO.findById(kodLuas));
                } else {
                    hakmilikPermohonanKerajaan.setKodUnitLuas(null);
                }

                if (StringUtils.isNotBlank(kategoriTanah)) {
                    hakmilikPermohonanKerajaan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(kategoriTanah));
                } else {
                    hakmilikPermohonanKerajaan.setKategoriTanahBaru(null);
                }

                if (StringUtils.isNotBlank(kodDun)) {
                    hakmilikPermohonanKerajaan.setKodDUN(kodDunDAO.findById(kodDun));
                } else {
                    hakmilikPermohonanKerajaan.setKodDUN(null);
                }

                if (StringUtils.isNotBlank(kodPBT)) {
                    hakmilikPermohonanKerajaan.setKodPbt(kodPBTDAO.findById(kodPBT));
                } else {
                    hakmilikPermohonanKerajaan.setKodPbt(null);
                }

                hakmilikPermohonanKerajaan.setCatatan(catatan);
                hakmilikPermohonanKerajaan.setLokasi(badanPengawal);

                enforceService.simpanhakmilikPermohonan(hakmilikPermohonanKerajaan);

                laporanTanah.setJenisTanah('K');
                laporanTanah.setHakmilikPermohonan(hakmilikPermohonanKerajaan);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah.setInfoAudit(infoAudit);
                laporanTanah.setJenisLaporan(jenisLaporan);
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }

            if (!jenisKemasukan.equalsIgnoreCase("TH") && !jenisKemasukan.equalsIgnoreCase("TK")) {
                if (laporanTanah == null) {
                    logger.info("New Laporan Tanah ::: add tanah hakmilik");
                    laporanTanah = new LaporanTanah();

                    laporanTanah.setPermohonan(permohonan);
                    laporanTanah.setInfoAudit(infoAudit);
                    laporanTanah.setJenisLaporan(jenisLaporan);
                    laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
                }
            }

            if (jenisKemasukan.equalsIgnoreCase("PT")) {

                if (StringUtils.isNotBlank(tarikhLawatan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                    tarikhLawatan = tarikhLawatan + " " + jam + ":" + minit + " " + ampm;
                    logger.info("tarikh lawatan :" + tarikhLawatan);
                }
                if (permohonanRujukanLuar != null) {
                    infoAudit = permohonanRujukanLuar.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();

                }
                if (tarikhLawatan != null && StringUtils.isNotBlank(tarikhLawatan)) {
                    permohonanRujukanLuar.setTarikhKuatkuasa(sdf1.parse(tarikhLawatan));
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

                laporanTanah.setNamaSempadanJalanraya(getContext().getRequest().getParameter("laporanTanah.namaSempadanJalanraya"));
                laporanTanah.setNamaSempadanKeretapi(getContext().getRequest().getParameter("laporanTanah.namaSempadanKeretapi"));
                laporanTanah.setNamaSempadanLaut(getContext().getRequest().getParameter("laporanTanah.namaSempadanLaut"));
                laporanTanah.setNamaSempadanSungai(getContext().getRequest().getParameter("laporanTanah.namaSempadanSungai"));

                laporanTanah.setJarakSempadanLaut(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.jarakSempadanLaut")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.jarakSempadanLaut")) : null);
                laporanTanah.setJarakSempadanJalanraya(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.jarakSempadanJalanraya")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.jarakSempadanJalanraya")) : null);
                laporanTanah.setJarakSempadanSungai(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.jarakSempadanSungai")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.jarakSempadanSungai")) : null);
                laporanTanah.setJarakSempadanKeretapi(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.jarakSempadanKeretapi")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.jarakSempadanKeretapi")) : null);

                laporanTanah.setJenisJalan(getContext().getRequest().getParameter("laporanTanah.jenisJalan"));
                laporanTanah.setAdaJalanMasuk(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.adaJalanMasuk")) ? getContext().getRequest().getParameter("laporanTanah.adaJalanMasuk").charAt(0) : null);
                laporanTanah.setCatatanJalanMasuk(getContext().getRequest().getParameter("laporanTanah.catatanJalanMasuk"));

                laporanTanah.setUsahaLuas(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.usahaLuas")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.usahaLuas")) : null);
                if (StringUtils.isNotBlank(anggaranLuasUOM)) {
                    laporanTanah.setUsahaLuasUom(kodUOMDAO.findById(anggaranLuasUOM));
                } else {
                    laporanTanah.setUsahaLuasUom(null);
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

                //save to permohonan manual
                if (permohonanManual != null) {
                    infoAudit = permohonanManual.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    permohonanManual = new PermohonanManual();
                }

                permohonanManual.setCawangan(permohonan.getCawangan());
                permohonanManual.setIdPermohonan(permohonan);
                permohonanManual.setInfoAudit(infoAudit);
                permohonanManual.setNoFail(noFail);
                enforceService.savePermohonanManual(permohonanManual);

            } else if (jenisKemasukan.equalsIgnoreCase("KT")) {
                if (StringUtils.isNotBlank(kodKecerunanTanah)) {
                    laporanTanah.setKecerunanTanah(kodKecerunanTanahDAO.findById(kodKecerunanTanah));
                }
                if (StringUtils.isNotBlank(kodStrukturTanah)) {
                    laporanTanah.setStrukturTanah(kodStrukturTanahDAO.findById(kodStrukturTanah));
                }

                laporanTanah.setKetinggianDariJalan(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.ketinggianDariJalan")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.ketinggianDariJalan")) : null);
                laporanTanah.setKecerunanBukit(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.kecerunanBukit")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.kecerunanBukit")) : null);
                laporanTanah.setParasAir(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.parasAir")) ? new BigDecimal(getContext().getRequest().getParameter("laporanTanah.parasAir")) : null);
                laporanTanah.setKeadaanTanah(getContext().getRequest().getParameter("laporanTanah.keadaanTanah"));
                laporanTanah.setDilintasTiangElektrik(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasTiangElektrik")) ? getContext().getRequest().getParameter("laporanTanah.dilintasTiangElektrik").charAt(0) : null);
                laporanTanah.setDilintasTiangTelefon(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasTiangTelefon")) ? getContext().getRequest().getParameter("laporanTanah.dilintasTiangTelefon").charAt(0) : null);
                laporanTanah.setDilintasLaluanGas(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasLaluanGas")) ? getContext().getRequest().getParameter("laporanTanah.dilintasLaluanGas").charAt(0) : null);
                laporanTanah.setDilintasPaip(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasPaip")) ? getContext().getRequest().getParameter("laporanTanah.dilintasPaip").charAt(0) : null);
                laporanTanah.setDilintasTaliar(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasTaliar")) ? getContext().getRequest().getParameter("laporanTanah.dilintasTaliar").charAt(0) : null);
                laporanTanah.setDilintasSungai(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasSungai")) ? getContext().getRequest().getParameter("laporanTanah.dilintasSungai").charAt(0) : null);
                laporanTanah.setDilintasParit(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.dilintasParit")) ? getContext().getRequest().getParameter("laporanTanah.dilintasParit").charAt(0) : null);

            } else if (jenisKemasukan.equalsIgnoreCase("LBT")) {
                laporanTanah.setDiusaha(getContext().getRequest().getParameter("laporanTanah.diusaha"));
                laporanTanah.setUsaha(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.usaha")) ? getContext().getRequest().getParameter("laporanTanah.usaha").charAt(0) : null);
                laporanTanah.setUsahaTanam(getContext().getRequest().getParameter("laporanTanah.usahaTanam"));
                laporanTanah.setAdaBangunan(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.adaBangunan")) ? getContext().getRequest().getParameter("laporanTanah.adaBangunan").charAt(0) : null);
                laporanTanah.setBilanganBangunan(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.bilanganBangunan")) ? Integer.parseInt(getContext().getRequest().getParameter("laporanTanah.bilanganBangunan")) : null);
                laporanTanah.setBangunanTahunDibina(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.bangunanTahunDibina")) ? Integer.parseInt(getContext().getRequest().getParameter("laporanTanah.bangunanTahunDibina")) : null);
                laporanTanah.setBangunanDidiami(StringUtils.isNotBlank(getContext().getRequest().getParameter("laporanTanah.bangunanDidiami")) ? getContext().getRequest().getParameter("laporanTanah.bangunanDidiami").charAt(0) : null);
                laporanTanah.setRancanganKerajaan(getContext().getRequest().getParameter("laporanTanah.rancanganKerajaan"));
                if (StringUtils.isNotBlank(tarikhDiusahakan)) {
                    laporanTanah.setTarikhMulaUsaha2(sdf.parse(tarikhDiusahakan));
                }
            } else if (jenisKemasukan.equalsIgnoreCase("JB")) {
                //JB = Jenis Bangunan

                if (StringUtils.isNotBlank(idValue)) {
                    permohonanLaporanBangunan = permohonanLaporanBangunanDAO.findById(Long.parseLong(idValue));
                }

                if (permohonanLaporanBangunan != null) {
                    infoAudit = permohonanLaporanBangunan.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    permohonanLaporanBangunan = new PermohonanLaporanBangunan();
                }

                permohonanLaporanBangunan.setPermohonan(permohonan);
                permohonanLaporanBangunan.setCawangan(pengguna.getKodCawangan());
                permohonanLaporanBangunan.setInfoAudit(infoAudit);
                permohonanLaporanBangunan.setJenisBangunan(jenisBangunan);
                permohonanLaporanBangunan.setLaporanTanah(laporanTanah);
                permohonanLaporanBangunan.setUkuran(ukuran);
                permohonanLaporanBangunan.setKeteranganTahunBinaan(ukuranTemp);
                if (uomUkuran != null) {
                    KodUOM kuom = kodUOMDAO.findById(uomUkuran);
                    permohonanLaporanBangunan.setUomUkuran(kuom);
                } else {
                    permohonanLaporanBangunan.setUomUkuran(null);
                }

                permohonanLaporanBangunan.setNilai(nilai);
                permohonanLaporanBangunan.setNamaPemunya(namaPemunya);
                permohonanLaporanBangunan.setNamaPenyewa(namaPenyewa);
                enforceService.simpanBangunan(permohonanLaporanBangunan);
            } else if (jenisKemasukan.equalsIgnoreCase("TS")) {
                //Tanah Sekeliling
                if (StringUtils.isNotBlank(idValue)) {
                    laporanTanahSempadan = laporanTanahSempadanDAO.findById(Long.parseLong(idValue));
                }

                if (laporanTanahSempadan != null) {
                    infoAudit = laporanTanahSempadan.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    laporanTanahSempadan = new LaporanTanahSempadan();
                }
                idHakmilikTanahSempadan = getContext().getRequest().getParameter("idHakmilikTanahSempadan");
                String chooseHakmilik = getContext().getRequest().getParameter("chooseHakmilik");
                logger.info("::: chooseHakmilik: " + chooseHakmilik);
                logger.info("::: idHakmilikTanahSempadan: " + idHakmilikTanahSempadan);


                Hakmilik hakmilikTanahSempadan = new Hakmilik();
                if (StringUtils.isNotBlank(chooseHakmilik)) {
                    if (chooseHakmilik.equalsIgnoreCase("Y")) {
                        if (StringUtils.isNotBlank(idHakmilikTanahSempadan)) {
                            hakmilikTanahSempadan = hakmilikDAO.findById(idHakmilikTanahSempadan);
                            if (hakmilikTanahSempadan != null) {
                                laporanTanahSempadan.setHakmilik(hakmilikTanahSempadan);
                                if (hakmilikTanahSempadan.getKategoriTanah() != null) {
                                    laporanTanahSempadan.setKodKategoriTanah(hakmilikTanahSempadan.getKategoriTanah());
                                } else {
                                    laporanTanahSempadan.setKodKategoriTanah(null);
                                }

                                if (hakmilikTanahSempadan.getLot() != null) {
                                    laporanTanahSempadan.setKodLot(hakmilikTanahSempadan.getLot());
                                } else {
                                    laporanTanahSempadan.setKodLot(null);
                                }
                            }
                        }
                    } else {
                        laporanTanahSempadan.setHakmilik(null);

                        if (StringUtils.isNotBlank(kodKategoriTanahSempadan)) {
                            KodKategoriTanah kttn = kodKategoriTanahDAO.findById(kodKategoriTanahSempadan);
                            laporanTanahSempadan.setKodKategoriTanah(kttn);
                        } else {
                            laporanTanahSempadan.setKodKategoriTanah(null);
                        }

                        if (StringUtils.isNotBlank(kodLotTanahSempadan)) {
                            KodLot kl = kodLotDAO.findById(kodLotTanahSempadan);
                            laporanTanahSempadan.setKodLot(kl);
                        } else {
                            laporanTanahSempadan.setKodLot(null);
                        }
                    }
                }

                laporanTanahSempadan.setInfoAudit(infoAudit);
                laporanTanahSempadan.setLaporanTanah(laporanTanah);
                laporanTanahSempadan.setJenisSempadan(jenisSempadan);
                laporanTanahSempadan.setMilikKerajaan(milikKerajaan);
                laporanTanahSempadan.setNoLot(noLotTanahSempadan);
                laporanTanahSempadan.setCatatan(catatan);

                enforceService.simpanSempadan(laporanTanahSempadan);
            } else if (jenisKemasukan.equalsIgnoreCase("UPPT")) {
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

            } else if (jenisKemasukan.equalsIgnoreCase("UPPTK")) {
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
            }

            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
        }

        if (laporanTanah != null) {
            idLaporanTanah = Long.toString(laporanTanah.getIdLaporan());
            if (StringUtils.isNotBlank(idLaporanTanah)) {
                getContext().getRequest().setAttribute("idLaporanTanah", idLaporanTanah);
            }
            System.out.println("id laporan selepas save : " + idLaporanTanah);
        }

//        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_utiliti_maklumat_tanah.jsp").addParameter("popup", "true");
//        return new RedirectResolution(MohonLaporTanahActionBean.class, "locate");
    }

    public Resolution findHakmilikLTNH() {
        logger.info("-----------findHakmilikLTNH-------------");
        hakmilikCarian = getContext().getRequest().getParameter("hakmilikCarian");
        logger.info(":::::::::::::::id hakmilik carian :" + hakmilikCarian);

        List<HakmilikPermohonan> listHp = enforceService.checkExistingHakmilikPermohonan(idPermohonan, hakmilikCarian);
        if (listHp.size() == 1) {
            statusCarian = "E"; //exist
            addSimpleError("Id hakmilik ini sudah wujud. Sila masukkan id hakmilik yang lain");
            return new JSP("penguatkuasaan/popup_utiliti_maklumat_tanah.jsp").addParameter("popup", "true");
        }

        try {
            hakmilik = enforceService.semakIdHakmilik(hakmilikCarian);
            if (hakmilik != null) {
                statusCarian = "W"; //W = wujud

            } else {
                statusCarian = "TW"; //TW = Tidak Wujud
                addSimpleError("Maklumat hakmilik tidak dijumpai");
                return new JSP("penguatkuasaan/popup_utiliti_maklumat_tanah.jsp").addParameter("popup", "true");

            }
            logger.info(":::::: keputusan carian hakmilik ::::::");
            logger.info("statusCarian :" + statusCarian);
            logger.info("no lot :" + hakmilik.getNoLot());
            logger.info("kodLotCarian :" + hakmilik.getLot().getKod());
        } catch (Exception ex) {
            addSimpleError(ex.getMessage());

        }
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_utiliti_maklumat_tanah.jsp").addParameter("popup", "true");
    }

//    public final void saveLaporanTanahToSession(etanahActionBeanContext ctx) {
//        logger.debug(":::::::::: START OF saveLaporanTanahToSession");
//        LaporanTanahCache u = (LaporanTanahCache) ctx.getWorkData();
//        if (u == null) {
//            u = new LaporanTanahCache();
//        }
//        u.laporanTanah = laporanTanah;
//
//
//        if (u.laporanTanah != null) {
////            u.idLaporanTanah = idLaporanTanah;
//            idLaporanTanah = Long.toString(u.laporanTanah.getIdLaporan());
//        }
//
//        logger.debug("::idLaporanTanah :" + idLaporanTanah);
//        ctx.setWorkData(u);
//
//        logger.debug(":::::::::: END OF saveLaporanTanahToSession");
//    }
//
//    public final void savePermohonanToSession(etanahActionBeanContext ctx) {
//        logger.debug(":::::::::: START OF savePermohonanToSession");
//        PermohonanCache u = (PermohonanCache) ctx.getWorkData();
//        if (u == null) {
//            u = new PermohonanCache();
//        }
//        u.permohonan = permohonan;
//
//        if (u.permohonan != null) {
////            u.idPermohonan = idPermohonan;
//            idPermohonan = u.permohonan.getIdPermohonan();
//        }
//
//        logger.debug("::idPermohonan : " + idPermohonan);
//        ctx.setWorkData(u);
//
//        logger.debug(":::::::::: END OF savePermohonanToSession");
//    }
    public Resolution refreshpage() {
        return new RedirectResolution(UtilitiLaporanTanahActionBean.class, "locate");
    }

    public Resolution reload() {
        logger.info("::::::::::::START OF RELOAD::::::::::::");
        idLaporanTanah = getContext().getRequest().getParameter("idLaporanTanah");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        String status = getContext().getRequest().getParameter("statusOp");
        logger.info("::::::::::::idLaporanTanah : " + idLaporanTanah);
        logger.info("::::::::::::idPermohonan : " + idPermohonan);

        getInformation(idLaporanTanah, idPermohonan);

        logger.info("::::::::::::END OF RELOAD::::::::::::");
        getContext().getRequest().setAttribute(status, Boolean.TRUE);
//        return new RedirectResolution(MohonLaporTanahActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/keputusan_carian_laporan_tanah.jsp").addParameter("tab", "true");
//        return new JSP("penguatkuasaan/mohon_lapor_tanah.jsp").addParameter("tab", "true");

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
        return new RedirectResolution(UtilitiLaporanTanahActionBean.class, "locate");
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

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public KodKecerunanTanah getKodkecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKodkecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
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

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
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

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
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

    public TanahRizabPermohonan getTrizabpermohonan() {
        return trizabpermohonan;
    }

    public void setTrizabpermohonan(TanahRizabPermohonan trizabpermohonan) {
        this.trizabpermohonan = trizabpermohonan;
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

    public Boolean getLaporanTanahFlag() {
        return laporanTanahFlag;
    }

    public void setLaporanTanahFlag(Boolean laporanTanahFlag) {
        this.laporanTanahFlag = laporanTanahFlag;
    }

    public List<HakmilikPermohonan> getHakmilikList() {
        return hakmilikList;
    }

    public void setHakmilikList(List<HakmilikPermohonan> hakmilikList) {
        this.hakmilikList = hakmilikList;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public ImejLaporanDAO getImejLaporanDAO() {
        return imejLaporanDAO;
    }

    public void setImejLaporanDAO(ImejLaporanDAO imejLaporanDAO) {
        this.imejLaporanDAO = imejLaporanDAO;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public String getKodDunLaporanTanah() {
        return kodDunLaporanTanah;
    }

    public void setKodDunLaporanTanah(String kodDunLaporanTanah) {
        this.kodDunLaporanTanah = kodDunLaporanTanah;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getIdMohonHakmilik() {
        return idMohonHakmilik;
    }

    public void setIdMohonHakmilik(String idMohonHakmilik) {
        this.idMohonHakmilik = idMohonHakmilik;
    }

    public String getIdLaporanTanah() {
        return idLaporanTanah;
    }

    public void setIdLaporanTanah(String idLaporanTanah) {
        this.idLaporanTanah = idLaporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public ArrayList getListIdPermohonan() {
        return listIdPermohonan;
    }

    public void setListIdPermohonan(ArrayList listIdPermohonan) {
        this.listIdPermohonan = listIdPermohonan;
    }

    public String[] getSenaraiIdPermohonan() {
        return senaraiIdPermohonan;
    }

    public void setSenaraiIdPermohonan(String[] senaraiIdPermohonan) {
        this.senaraiIdPermohonan = senaraiIdPermohonan;
    }

    public String getIdPertama() {
        return idPertama;
    }

    public void setIdPertama(String idPertama) {
        this.idPertama = idPertama;
    }

    public String getIdKedua() {
        return idKedua;
    }

    public void setIdKedua(String idKedua) {
        this.idKedua = idKedua;
    }

    public Boolean getStatusNorujukan() {
        return statusNorujukan;
    }

    public void setStatusNorujukan(Boolean statusNorujukan) {
        this.statusNorujukan = statusNorujukan;
    }

    public List<LaporanTanah> getListLaporanTanah() {
        return listLaporanTanah;
    }

    public void setListLaporanTanah(List<LaporanTanah> listLaporanTanah) {
        this.listLaporanTanah = listLaporanTanah;
    }

    public PermohonanManual getPermohonanManual() {
        return permohonanManual;
    }

    public void setPermohonanManual(PermohonanManual permohonanManual) {
        this.permohonanManual = permohonanManual;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getKodKategoriTanahSempadan() {
        return kodKategoriTanahSempadan;
    }

    public void setKodKategoriTanahSempadan(String kodKategoriTanahSempadan) {
        this.kodKategoriTanahSempadan = kodKategoriTanahSempadan;
    }

    public String getKodLotTanahSempadan() {
        return kodLotTanahSempadan;
    }

    public void setKodLotTanahSempadan(String kodLotTanahSempadan) {
        this.kodLotTanahSempadan = kodLotTanahSempadan;
    }

    public String getNoLotTanahSempadan() {
        return noLotTanahSempadan;
    }

    public void setNoLotTanahSempadan(String noLotTanahSempadan) {
        this.noLotTanahSempadan = noLotTanahSempadan;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public String getIdHakmilikTanahSempadan() {
        return idHakmilikTanahSempadan;
    }

    public void setIdHakmilikTanahSempadan(String idHakmilikTanahSempadan) {
        this.idHakmilikTanahSempadan = idHakmilikTanahSempadan;
    }

    public String getStatusTanah() {
        return statusTanah;
    }

    public void setStatusTanah(String statusTanah) {
        this.statusTanah = statusTanah;
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

    public List<HakmilikPihakBerkepentingan> getPihakBerkepentinganListSek49() {
        return pihakBerkepentinganListSek49;
    }

    public void setPihakBerkepentinganListSek49(List<HakmilikPihakBerkepentingan> pihakBerkepentinganListSek49) {
        this.pihakBerkepentinganListSek49 = pihakBerkepentinganListSek49;
    }

    public List<HakmilikPihakBerkepentingan> getPihakBerkepentinganList() {
        return pihakBerkepentinganList;
    }

    public void setPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> pihakBerkepentinganList) {
        this.pihakBerkepentinganList = pihakBerkepentinganList;
    }

    public List<HakmilikUrusan> getSenaraiHakmilikUrusan() {
        return senaraiHakmilikUrusan;
    }

    public void setSenaraiHakmilikUrusan(List<HakmilikUrusan> senaraiHakmilikUrusan) {
        this.senaraiHakmilikUrusan = senaraiHakmilikUrusan;
    }

    public String getKodDun() {
        return kodDun;
    }

    public void setKodDun(String kodDun) {
        this.kodDun = kodDun;
    }

    public String getKodKecerunanTanah() {
        return kodKecerunanTanah;
    }

    public void setKodKecerunanTanah(String kodKecerunanTanah) {
        this.kodKecerunanTanah = kodKecerunanTanah;
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

    public String getJarakSungaiUOM() {
        return jarakSungaiUOM;
    }

    public void setJarakSungaiUOM(String jarakSungaiUOM) {
        this.jarakSungaiUOM = jarakSungaiUOM;
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

    public String getAnggaranLuasUOM() {
        return anggaranLuasUOM;
    }

    public void setAnggaranLuasUOM(String anggaranLuasUOM) {
        this.anggaranLuasUOM = anggaranLuasUOM;
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

    public PermohonanLaporanBangunan getPermohonanLaporanBangunan() {
        return permohonanLaporanBangunan;
    }

    public void setPermohonanLaporanBangunan(PermohonanLaporanBangunan permohonanLaporanBangunan) {
        this.permohonanLaporanBangunan = permohonanLaporanBangunan;
    }

    public String getJenisKemasukan() {
        return jenisKemasukan;
    }

    public void setJenisKemasukan(String jenisKemasukan) {
        this.jenisKemasukan = jenisKemasukan;
    }

    public Boolean getMultipleHakmilik() {
        return multipleHakmilik;
    }

    public void setMultipleHakmilik(Boolean multipleHakmilik) {
        this.multipleHakmilik = multipleHakmilik;
    }

    public HakmilikPermohonan getMultipleHakmilikPermohonan() {
        return multipleHakmilikPermohonan;
    }

    public void setMultipleHakmilikPermohonan(HakmilikPermohonan multipleHakmilikPermohonan) {
        this.multipleHakmilikPermohonan = multipleHakmilikPermohonan;
    }

    public String getKodPBT() {
        return kodPBT;
    }

    public void setKodPBT(String kodPBT) {
        this.kodPBT = kodPBT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HakmilikPermohonan getHakmilikPermohonanKerajaan() {
        return hakmilikPermohonanKerajaan;
    }

    public void setHakmilikPermohonanKerajaan(HakmilikPermohonan hakmilikPermohonanKerajaan) {
        this.hakmilikPermohonanKerajaan = hakmilikPermohonanKerajaan;
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

    public String getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(String kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getBadanPengawal() {
        return badanPengawal;
    }

    public void setBadanPengawal(String badanPengawal) {
        this.badanPengawal = badanPengawal;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public BigDecimal getUkuran() {
        return ukuran;
    }

    public void setUkuran(BigDecimal ukuran) {
        this.ukuran = ukuran;
    }

    public String getUomUkuran() {
        return uomUkuran;
    }

    public void setUomUkuran(String uomUkuran) {
        this.uomUkuran = uomUkuran;
    }

    public String getNamaPemunya() {
        return namaPemunya;
    }

    public void setNamaPemunya(String namaPemunya) {
        this.namaPemunya = namaPemunya;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getIdLaporBangunan() {
        return idLaporBangunan;
    }

    public void setIdLaporBangunan(String idLaporBangunan) {
        this.idLaporBangunan = idLaporBangunan;
    }

    public String getUkuranTemp() {
        return ukuranTemp;
    }

    public void setUkuranTemp(String ukuranTemp) {
        this.ukuranTemp = ukuranTemp;
    }

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getIdLaporTanahSpdn() {
        return idLaporTanahSpdn;
    }

    public void setIdLaporTanahSpdn(String idLaporTanahSpdn) {
        this.idLaporTanahSpdn = idLaporTanahSpdn;
    }

    public String getJenisSempadan() {
        return jenisSempadan;
    }

    public void setJenisSempadan(String jenisSempadan) {
        this.jenisSempadan = jenisSempadan;
    }

    public String getMilikKerajaan() {
        return milikKerajaan;
    }

    public void setMilikKerajaan(String milikKerajaan) {
        this.milikKerajaan = milikKerajaan;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getHakmilikCarian() {
        return hakmilikCarian;
    }

    public void setHakmilikCarian(String hakmilikCarian) {
        this.hakmilikCarian = hakmilikCarian;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }
    
    
}

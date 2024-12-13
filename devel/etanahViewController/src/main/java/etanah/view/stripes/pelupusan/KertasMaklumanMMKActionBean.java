/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;
import etanah.util.FileUtil;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.util.Collections;
import etanah.service.BPelService;
import etanah.service.CalcTaxPelupusan;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisKertasMMKController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import java.util.Vector;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author Afham
 */
@UrlBinding("/pelupusan/kertas_makluman_mmkV2")
public class KertasMaklumanMMKActionBean extends AbleActionBean {

    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    DisKertasMMKController disKertasMMKController;
    private String kodNegeri;
    private String stageId;
    private String idPermohonan;
    private String defaultKandunganTajuk;
    private String kandunganTajuk; //Bil - 0
    private String idMh;
    private Pengguna peng;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private LaporanTanah laporanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodCawangan cawangan;
    etanahActionBeanContext ctx;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanKertasKandungan> senaraiKandunganTujuan; //Bil - 1
    private List<PermohonanKertasKandungan> senaraiKandunganLatarBelakang; //Bil - 2
    private List<PermohonanKertasKandungan> senaraiKandunganSiasatan; //Bil - 3
    private List<PermohonanKertasKandungan> senaraiKandunganMakluman; //Bil - 4
    private List<PermohonanKertasKandungan> senaraiKandunganKeputusan; // Bil - 5
    private List<PermohonanKertasKandungan> senaraiKandunganTemp;
    private static final Logger LOG = Logger.getLogger(KertasMaklumanMMKActionBean.class);

    @DefaultHandler
    public Resolution editOnlyKertasMaklumanMMK() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.editKertasMMKPT();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKMMKT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public Resolution viewOnlyKertasMaklumanMMK() {
        HttpSession httpSession = getContext().getRequest().getSession();
        disKertasMMKController = new DisKertasMMKController();
        disKertasMMKController = disKertasMMKController.viewKertasMMKPTD();
        httpSession.setAttribute("disKertasMMKController", disKertasMMKController);
        return new JSP(DisPermohonanPage.getKMMKT_MAIN_PAGE()).addParameter("tab", "true");
    }

    public void setDefaultKandungan(String caw) {
        String daerah = new String();
        if (caw != null) {
            daerah = disLaporanTanahService.getPelUtiliti().convertStringtoInitCap(caw);
        }

        LOG.info("Set Default Kandungan");
    }

    public void setObjectPermohonanKertas(Permohonan permohonan, String kodDok) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cawangan = new KodCawangan();
        cawangan = (KodCawangan) disLaporanTanahService.getPlpservice().findDAOByPK(pengguna.getKodCawangan(), pengguna.getKodCawangan().getKod());
        permohonanKertas = new PermohonanKertas();
        permohonanKertas.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertas, "new", pengguna));
        permohonanKertas.setTajuk("Kertas Makluman");
        KodDokumen kod = kodDokumenDAO.findById(kodDok);
        permohonanKertas.setKodDokumen(kod);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setPermohonan(permohonan);
        disLaporanTanahService.getPelPtService().simpanPermohonanKertas(permohonanKertas);
        LOG.info("Saving Default Object Permohonan Kertas");
    }

    public void setDefaultKandunganKertasTajuk(String caw) {
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

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanKandungan", "!deleteRow"})
    public void rehydrate() {
        LOG.info("In rehydrate");
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        stageId = stageId(taskId, peng);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");



        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
            if (permohonan != null) {
                setDefaultKandungan(permohonan.getCawangan().getDaerah().getNama());
            }



            /**
             * Hakmilik or Not Have Hakmilik
             */
            if (idPermohonan != null) {

                permohonan = new Permohonan();
                permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);

                senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();

                if (permohonan.getKodUrusan().getKod().equals("PJLB") && permohonan.getPermohonanSebelum() != null) {
//              senaraiHakmilikPermohonan = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                } else if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                } else if (permohonan.getKodUrusan().getKod().equals("PTGSA")) {
//                senaraiHakmilikPermohonan = disLaporanTanahService.getPlpservice().getHakmilikPermohonanListByCatatanNIdMohon(new String(), idPermohonan);
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                } else {
                    senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
                }

                if (senaraiHakmilikPermohonan.size() > 0) {
                    if (senaraiHakmilikPermohonan.size() == 1) {
                        hakmilikPermohonan = senaraiHakmilikPermohonan.get(0);
                    } else {
                        //Not yet implemented
                    }
                }

            }

            /**
             * Permohonan Kertas
             */
            String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
            if (StringUtils.isNotBlank(kodDok)) {
                permohonanKertas = new PermohonanKertas();
                permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                if (permohonanKertas != null) {
                    senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                    if (!senaraiKandunganTemp.isEmpty()) {
                        kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                    }
                    senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                    senaraiKandunganLatarBelakang = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                    senaraiKandunganSiasatan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                    senaraiKandunganMakluman = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                    senaraiKandunganKeputusan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                } else {
                    setDefaultKandunganTajuk(permohonan.getCawangan().getDaerah().getKod());
                    setObjectPermohonanKertas(permohonan, kodDok);
                    copyDataFromKertasMMK("RMN", 0); //Copy Tajuk
                    copyDataFromKertasMMK("RMN", 1); //Copy Tujuan
                    permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
                    if (permohonanKertas != null) {
                        senaraiKandunganTemp = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 0);
                        if (!senaraiKandunganTemp.isEmpty()) {
                            kandunganTajuk = senaraiKandunganTemp.get(0).getKandungan();
                        }
                        senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                    }
                }

            }
        }
    }

    public void copyDataFromKertasMMK(String kodDokumen, int index) {
        PermohonanKertasKandungan pkkTemp = new PermohonanKertasKandungan();
        PermohonanKertas pkTemp = new PermohonanKertas();
        List<PermohonanKertasKandungan> pkkTempList = new ArrayList<PermohonanKertasKandungan>();
        pkTemp = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDokumen);
        if (pkTemp != null) {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
            String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
            permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            pkkTempList = disLaporanTanahService.getPelPtService().findByIdLapor(pkTemp.getIdKertas(), index);
            cawangan = new KodCawangan();
            cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());

            if (!pkkTempList.isEmpty()) {
                for (PermohonanKertasKandungan pkk : pkkTempList) {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                    permohonanKertasKandungan.setKertas(permohonanKertas);
                    permohonanKertasKandungan.setCawangan(cawangan);
                    permohonanKertasKandungan.setBil(pkk.getBil());
                    permohonanKertasKandungan.setSubtajuk(pkk.getSubtajuk());
                    permohonanKertasKandungan.setKandungan(pkk.getKandungan());
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }

        }
    }

    public Resolution refreshpage() {
        return new JSP(refreshData(ctx.getRequest().getParameter("type"))).addParameter("popup", Boolean.TRUE);
    }

    public Resolution deleteRow() throws ParseException {

        String idKand = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKand != null && tName != null) {
            forwardJSP = refreshData(disLaporanTanahService.delObject(tName, new String[]{idKand}, typeSyor));
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new ForwardResolution("/WEB-INF/jsp/" + forwardJSP).addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/gsa/laporan_tanahGSALotSmpdn.jsp").addParameter("tab", "true");
    }

    public Resolution openFrame() {
        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String forwardJSP = new String();
        String type = ctx.getRequest().getParameter("type");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = new Permohonan();
        permohonan = (Permohonan) disLaporanTanahService.findObject(permohonan, new String[]{idPermohonan}, 0);
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = new PermohonanKertas();
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);

        if (StringUtils.isNotBlank(type)) {
            /*
             * FORWARD TO DIFFERENT POPUP
             */
            forwardJSP = refreshData(type);
        }
//        rehydrate();
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
    }

    public String refreshData(String type) {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
//        permohonanKertas = new PermohonanKertas();
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        String forwardJSP = new String();
        int typeNum = type.equals("kTujuan") ? 1
                : type.equals("kLatarBelakang") ? 2
                : type.equals("kSiasatan") ? 3
                : type.equals("kMakluman") ? 4
                : type.equals("kKeputusan") ? 5
                : type.equals("main") ? 6
                : 9;

        switch (typeNum) {
            case 1:
                senaraiKandunganTujuan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 1);
                forwardJSP = DisPermohonanPage.getKMMKT_TUJUAN();
                break;
            case 2:
                senaraiKandunganLatarBelakang = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 2);
                forwardJSP = DisPermohonanPage.getKMMKT_LATAR_BELAKANG();
                break;
            case 3:
                senaraiKandunganSiasatan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 3);
                forwardJSP = DisPermohonanPage.getKMMKT_SIASATAN();
                break;
            case 4:
                senaraiKandunganMakluman = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 4);
                forwardJSP = DisPermohonanPage.getKMMKT_MAKLUMAN();
                break;
            case 5:
                senaraiKandunganKeputusan = disLaporanTanahService.getPelupusanService().findByIdLapor(permohonanKertas.getIdKertas(), 5);
                forwardJSP = DisPermohonanPage.getKMMKT_KEPUTUSAN();
                break;
            case 6:
                rehydrate();
                disKertasMMKController = (DisKertasMMKController) getContext().getRequest().getSession().getAttribute("disKertasMMKController");
                forwardJSP = DisPermohonanPage.getKMMKT_MAIN_PAGE();
                break;
        }
        return forwardJSP;
    }

    /**
     *
     * Purpose to cater kod dokumen based on urusan and negeri
     */
    public String kertasDok(String urusan, String kodNegeri) {
        String kodDok = new String();
        int typeUrusan = urusan.equals("BMBT") ? 1
                : urusan.equals("PJBTR") ? 2
                : urusan.equals("PBMT") ? 3
                : 0;

        switch (typeUrusan) {
            case 1: //BMBT
                if (kodNegeri.equals("04")) {
                    kodDok = "MMKT";
                } else {
                    kodDok = "MMKT";
                }
                break;
            case 2: //PJBTR
                if (kodNegeri.equals("04")) {
                    kodDok = "MMKT";
                } else {
                    kodDok = "MMKT";
                }
                break;
            case 3: //PBMT
                if (kodNegeri.equals("04")) {
                    kodDok = "MMKT";
                } else {
                    kodDok = "MMKT";
                }
                break;
            default:
                if (kodNegeri.equals("04")) {
                    kodDok = "MMKT";
                } else {
                    kodDok = "MMKT";
                }
                break;
        }
        return kodDok;
    }

    public Resolution simpanKandungan() throws ParseException {

        ctx = (etanahActionBeanContext) getContext();
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        int index = 0;
        boolean updateDB = false;
        boolean updateDB2 = false;
        index = Integer.parseInt(getContext().getRequest().getParameter("indexKertas"));
        String kand = getContext().getRequest().getParameter("kandungan");
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        String forwardJSP = new String();

        switch (index) {
            case 1:
                forwardJSP = DisPermohonanPage.getKMMKT_TUJUAN();
                updateKandungan(kand, "kTujuan", permohonanKertas, 1);
                break;
            case 2:
                forwardJSP = DisPermohonanPage.getKMMKT_LATAR_BELAKANG();
                updateKandungan(kand, "kLatarBelakang", permohonanKertas, 2);
                break;
             case 3:
                forwardJSP = DisPermohonanPage.getKMMKT_SIASATAN();
                updateKandungan(kand, "kSiasatan", permohonanKertas, 3);
                break;    
            case 4:
                forwardJSP = DisPermohonanPage.getKMMKT_MAKLUMAN();
                updateKandungan(kand, "kMakluman", permohonanKertas, 4);
                break;
            case 5:
                forwardJSP = DisPermohonanPage.getKMMKT_KEPUTUSAN();
                updateKandungan(kand, "kKeputusan", permohonanKertas, 5);
                break;
            default:
                break;
        }
        rehydrate();
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP(forwardJSP).addParameter("tab", Boolean.TRUE);
    }

    public Boolean updateKandungan(String kand, String typeSyor, PermohonanKertas mohonKertas, int bil) {

        boolean updateDB = false;
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        sizeSenaraiLaporUlas = ctx.getRequest().getParameter("sizeSenaraiLaporUlas");
//        List<PermohonanLaporanUlasan> senaraiLaporanKandungan2 = new ArrayList<PermohonanLaporanUlasan>();
        List<PermohonanKertasKandungan> senaraiPermohonanKertasKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiPermohonanKertasKandungan = disLaporanTanahService.getPelupusanService().findByIdLapor(mohonKertas.getIdKertas(), bil);
        if (!StringUtils.isEmpty(typeSyor)) {
            for (PermohonanKertasKandungan pkk : senaraiPermohonanKertasKandungan) {
                String checkKand = String.valueOf(pkk.getIdKandungan()) + "kandunganKertas";
                String kandungan = ctx.getRequest().getParameter(checkKand);
//                    String idK = ctx.getRequest().getParameter("idKandungan1");
//                    if (pkk.getIdKandungan() == Long.valueOf(idK)) {
                if (!StringUtils.isEmpty(kandungan)) {
                    pkk.setKandungan(kandungan);
                    pkk.setInfoAudit(disLaporanTanahService.findAudit(pkk, "update", pengguna));
                    disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(pkk);
                }
            }

        }

        return updateDB;
    }

    public Resolution tambahRow() {

        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        String forwardJSP = new String();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        String kodDok = kertasDok(permohonan.getKodUrusan().getKod(), kodNegeri);
        permohonanKertas = disLaporanTanahService.getPelupusanService().findPermohonanKertasByIdPermohonanNKodDokumen(idPermohonan, kodDok);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKandunganTemp = disLaporanTanahService.getPelPtService().findByIdLapor(permohonanKertas.getIdKertas(), index);
        permohonanKertasKandungan = new PermohonanKertasKandungan();
        if (senaraiKandunganTemp.isEmpty()) {
            permohonanKertasKandungan.setSubtajuk("1");
        } else {
            int n = Integer.parseInt(senaraiKandunganTemp.get(senaraiKandunganTemp.size() - 1).getSubtajuk()) + 1;
            permohonanKertasKandungan.setSubtajuk(String.valueOf(n));
        }
        permohonanKertasKandungan.setKandungan("Sila Tambah Ayat Di Sini"); //Temporary 

        switch (index) {
            case 1:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kTujuan");
                break;
            case 2:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kLatarBelakang");
                break;
            case 3:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kSiasatan");
                break;
            case 4:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kMakluman");
                break;
            case 5:
                cawangan = new KodCawangan();
                cawangan = disLaporanTanahService.getKodCawanganDAO().findById(pengguna.getKodCawangan().getKod());
                permohonanKertasKandungan.setInfoAudit(disLaporanTanahService.findAudit(permohonanKertasKandungan, "new", pengguna));
                permohonanKertasKandungan.setBil(index);
                permohonanKertasKandungan.setCawangan(cawangan);
                permohonanKertasKandungan.setKertas(permohonanKertas);
                disLaporanTanahService.getPelupusanService().simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                rehydrate();
                forwardJSP = refreshData("kKeputusan");
                break;    
            default:
                break;
        }
        return new JSP(forwardJSP).addParameter("popup", Boolean.TRUE);
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKandunganTajuk() {
        return kandunganTajuk;
    }

    public void setKandunganTajuk(String kandunganTajuk) {
        this.kandunganTajuk = kandunganTajuk;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganTujuan() {
        return senaraiKandunganTujuan;
    }

    public void setSenaraiKandunganTujuan(List<PermohonanKertasKandungan> senaraiKandunganTujuan) {
        this.senaraiKandunganTujuan = senaraiKandunganTujuan;
    }

    public String getDefaultKandunganTajuk() {
        return defaultKandunganTajuk;
    }

    public void setDefaultKandunganTajuk(String defaultKandunganTajuk) {
        this.defaultKandunganTajuk = defaultKandunganTajuk;
    }

    public DisKertasMMKController getDisKertasMMKController() {
        return disKertasMMKController;
    }

    public void setDisKertasMMKController(DisKertasMMKController disKertasMMKController) {
        this.disKertasMMKController = disKertasMMKController;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganKeputusan() {
        return senaraiKandunganKeputusan;
    }

    public void setSenaraiKandunganKeputusan(List<PermohonanKertasKandungan> senaraiKandunganKeputusan) {
        this.senaraiKandunganKeputusan = senaraiKandunganKeputusan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganTemp() {
        return senaraiKandunganTemp;
    }

    public void setSenaraiKandunganTemp(List<PermohonanKertasKandungan> senaraiKandunganTemp) {
        this.senaraiKandunganTemp = senaraiKandunganTemp;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganLatarBelakang() {
        return senaraiKandunganLatarBelakang;
    }

    public void setSenaraiKandunganLatarBelakang(List<PermohonanKertasKandungan> senaraiKandunganLatarBelakang) {
        this.senaraiKandunganLatarBelakang = senaraiKandunganLatarBelakang;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganMakluman() {
        return senaraiKandunganMakluman;
    }

    public void setSenaraiKandunganMakluman(List<PermohonanKertasKandungan> senaraiKandunganMakluman) {
        this.senaraiKandunganMakluman = senaraiKandunganMakluman;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandunganSiasatan() {
        return senaraiKandunganSiasatan;
    }

    public void setSenaraiKandunganSiasatan(List<PermohonanKertasKandungan> senaraiKandunganSiasatan) {
        this.senaraiKandunganSiasatan = senaraiKandunganSiasatan;
    }
    
    
}

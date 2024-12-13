package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKementerian;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.SuratRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.ListUtil;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author w.fairul
 */
@UrlBinding("/pengambilan/jabatan_teknikal12")
public class JabatanTeknikal2ActionBean extends AbleActionBean {

    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    RegService regService;
    @Inject
    NotaService notaService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    ListUtil listUtil;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikal2ActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private boolean viewOnly;
    private String idPermohonan;
    private String tarikhPermohonan;
    private Permohonan permohonan;
    private List<KodAgensi> listKodAgensi;
    // hidden fields
    private String kod;
    private String kodAgensiNama;
    // New kodAgensi details
    private KodAgensi kodAgensi;
    private KodNegeri kodNegeri;
    private String radio_;
    private String index;
    private String recordCount;
    private List<PermohonanLaporanPelan> permohonanLaporanPelanList;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanRujukanLuarDokumen> senaraiRujukanDok;
    private List<KodAgensi> kodAgensis = new ArrayList<KodAgensi>();
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuar mohonRujLuar;
    private PermohonanLaporanPelan permohonanLaporPelan;
    private FasaPermohonan fasaPermohonan;
    private String rowCount1;
    private String kategoriAgensi;
    private String Penyelerasan;
    private String syor;
    private String kNegeri;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna pguna;
    private Pengguna peng;
    private String stageId;
    private FolderDokumen folderDokumen;
    private String workflowId;
    @Inject
    private ReportUtil reportUtil;
    private String idFolder;
    private String idRujukan;
    private String[] mandatori;
    private List<KodKementerian> senaraiKodKementerian;
    private String kodKatgAgensi;
    private String catatan;
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private String ditundatangan;
    private String ditundatangan1;
    private List<SuratRujukanLuar> suratRujukanLuartemp;
    private SuratRujukanLuar suratRujukanLuar;

    @DefaultHandler
    public Resolution showForm() {
        if (idPermohonan != null) {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
            if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                }
            }
        }
        return new JSP("pengambilan/melaka/pendudukansementara/pilih_jabatan_teknikal_2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        if (idPermohonan != null) {
            ditundatangan1 = "225";
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi1(idPermohonan);
            if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                }
            }
        }
        return new JSP("pengambilan/melaka/pendudukansementara/pilih_jabatan_teknikal_NS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pelupusan/pilih_jabatan_teknikal_2.jsp").addParameter("tab", "true");
    }

    //new added by @mr5rule usage for syor_SDP.jsp
    public Resolution showSyorSDP() {
        viewOnly = Boolean.FALSE;
        permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        return new JSP("pengambilan/melaka/pendudukansementara/syor_SDP.jsp").addParameter("tab", "true");
    }

    public Resolution viewSyorSDP() {
        viewOnly = Boolean.TRUE;
        permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        return new JSP("pengambilan/melaka/pendudukansementara/syor_SDP.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSyorSDP() {
        LOG.info("-------------------------Enter Save Syor");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("------------------------------------" + idPermohonan + "------------------------------------");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        InfoAudit info = new InfoAudit();
        if (idPermohonan != null) {
            permohonanLaporPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
            LOG.info("-------------------------permohonanLaporPelan" + permohonanLaporPelan);
            if (permohonanLaporPelan != null) {
                info = permohonanLaporPelan.getInfoAudit();
                info.setTarikhKemaskini(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
            } else {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanLaporPelan = new PermohonanLaporanPelan();
            }

            permohonanLaporPelan.setInfoAudit(info);
            permohonanLaporPelan.setCawangan(peng.getKodCawangan());
            permohonanLaporPelan.setPermohonan(permohonan);
            permohonanLaporPelan.setSyor(syor);
            pelupusanService.saveOrUpdate(permohonanLaporPelan);
        }
        LOG.info("--------------------End Save Syor");
        addSimpleMessage("Syor Berjaya Disimpan");
        return new JSP("pengambilan/melaka/pendudukansementara/syor_SDP.jsp").addParameter("tab", "true");

    }

    // click on edit button
    public Resolution showEditJabatanTeknikal() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        mohonRujLuar = new PermohonanRujukanLuar();
        if (idRujukan != null) {
            mohonRujLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        }
        return new JSP("pengambilan/melaka/pendudukansementara/surat_ulasan_teknikal.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
    // click on edit button

    public Resolution showEditJabatanTeknikal1() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        mohonRujLuar = new PermohonanRujukanLuar();
        if (idRujukan != null) {
            mohonRujLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        }
        return new JSP("pengambilan/melaka/pendudukansementara/surat_ulasan_teknikalView.jsp").addParameter("popup", "true");
    }

    // click on save button in edit page
    public Resolution simpanJabatanTeknikal() {
        PermohonanRujukanLuar mohonRujLuarTemp = new PermohonanRujukanLuar();
        Long idRujukan = mohonRujLuar.getIdRujukan();
        LOG.info("-----------idRujukan------------" + idRujukan);
        if (idRujukan != null) {
            LOG.info("-----------simpanJabatanTeknikal----if--------");
            mohonRujLuarTemp = permohonanRujukanLuarDAO.findById(idRujukan);
            LOG.info("-----------simpanJabatanTeknikal----mohonRujLuarTemp--------" + mohonRujLuarTemp);
            mohonRujLuarTemp.setNamaPenyedia(mohonRujLuar.getNamaPenyedia());
            mohonRujLuarTemp.setNoRujukan(mohonRujLuar.getNoRujukan());
            mohonRujLuarTemp.setDiSokong(mohonRujLuar.getDiSokong());
            mohonRujLuarTemp.setUlasan(mohonRujLuar.getUlasan());
            if (mohonRujLuar.getTarikhTerima() != null) {
                mohonRujLuarTemp.setTarikhTerima(mohonRujLuar.getTarikhTerima());
            }
            java.util.Date curDate = new java.util.Date();
            if (mohonRujLuar.getUlasan() != null) {
//                mohonRujLuarTemp.setTarikhTerima(curDate);
            }

            Date jangka = mohonRujLuar.getTarikhJangkaTerima();

            int dif = jangka.compareTo(curDate);
            if (mohonRujLuar.getUlasan() != null) {
                mohonRujLuarTemp.setStatusUlasan('L');
            } else if (mohonRujLuar.getUlasan().equals("") && dif < 0) {
                mohonRujLuarTemp.setStatusUlasan('T');
            } else if (mohonRujLuar.getUlasan().equals("") && dif > 0) {
                mohonRujLuarTemp.setStatusUlasan('P');
            }
            notaService.simpanPermohonanRujLuar(mohonRujLuarTemp);
        }

        return new JSP("pengambilan/melaka/pendudukansementara/terima_ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution terimaUlasan() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       stageId ="terima_ulasan_teknikal";
        // tarikhPermohonan = permohonan.getInfoAudit().getTarikhMasuk().toString();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(idPermohonan);
            if (senaraiRujukanLuar != null) {
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                }
            }
        }
        return new JSP("pengambilan/melaka/pendudukansementara/terima_ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId = stageId(taskId);
//        stageId="g_kemaskini_charting";
        LOG.info("---------------------------stageID---------------------" + stageId);
        permohonan = permohonanDAO.findById(idPermohonan);

        LOG.info("pguna.getIdPengguna():" + pguna.getIdPengguna() + " pguna.getNama(): " + pguna.getNama() + " pguna.getJawatan(): " + pguna.getJawatan());
        peng = new Pengguna();
        peng.setIdPengguna(pguna.getIdPengguna());
        peng.setNama(pguna.getNama());
        peng.setJawatan(pguna.getJawatan());


        if (!permohonan.getKodUrusan().getKod().equals("PTGSA")) {
            permohonanLaporanPelanList = pengambilanService.findByIdPermohonanPelan(idPermohonan);
        }
        String filter = getContext().getRequest().getParameter("filterBy");
        for (PermohonanLaporanPelan permohonanLaporPelan : permohonanLaporanPelanList) {
            if (permohonanLaporPelan != null) {
                syor = permohonanLaporPelan.getSyor();
            }
        }

        kNegeri = conf.getProperty("kodNegeri");
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        kodAgensis = pelupusanService.getSenaraikodAgensi();
        if (idPermohonan != null) {
            LOG.info("--------------senaraiRujukanLuar----------before--------------" + senaraiRujukanLuar);
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
            LOG.info("--------------senaraiRujukanLuar----------after--------------" + senaraiRujukanLuar);
            for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
            }
            senaraiKandungan = new ArrayList<KandunganFolder>();
            folderDokumen = permohonan.getFolderDokumen();
            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                if (kf == null || kf.getDokumen() == null) {
                    continue;
                }
                KodDokumen kd = kf.getDokumen().getKodDokumen();

                if (StringUtils.isNotBlank(filter)) {
                    if (kd.getKod().equals(filter)) {
                        senaraiKandungan.add(kf);
                    }
                } else {
                    senaraiKandungan.add(kf);
                }
            }

            LOG.info("--------------senaraiRujukanLuar.size()------------------------" + senaraiRujukanLuar.size());
            if (senaraiRujukanLuar.size() == 0) {
                LOG.info("--------------senaraiRujukanLuar.size()----inside--------------------" + senaraiRujukanLuar.size());
                InfoAudit info;
                KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
                for (KodAgensi kod : kodAgensis) {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    info = new InfoAudit();
                    info.setDimasukOleh(pguna);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanRujukanLuar.setPermohonan(permohonan);
                    permohonanRujukanLuar.setInfoAudit(info);
                    permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                    permohonanRujukanLuar.setTarikhDisampai(new java.util.Date());
                    permohonanRujukanLuar.setAgensi(kod);
                    permohonanRujukanLuar.setKodRujukan(kodRujukan);
                    permohonanRujukanLuar.setNamaAgensi(kod.getNama());
                    permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                    permohonanRujukanLuar.setTempohHari(14);
                    permohonanRujukanLuar.setTarikhJangkaTerima(addBusinessDays(14));
                    pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);

                }
                senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
            }

//              tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonanrehy(idPermohonan);
            List<PermohonanTandatanganDokumen> ttDokList = new ArrayList<PermohonanTandatanganDokumen>();
            ttDokList = pelupusanService.findPermohonanDokTTByIdPermohonanrehy(idPermohonan);
            LOG.info("----------------ttDokList-------------------" + ttDokList.size());
            if (ttDokList.size() > 0) {
                tandatanganDokumen = ttDokList.get(0);
                ditundatangan = tandatanganDokumen.getDiTandatangan();
                LOG.info("----------------ditundatangan-------------------" + ditundatangan);
            }
            LOG.info("----------------tandatanganDokumen-------------------" + tandatanganDokumen);
//             if(tandatanganDokumen!=null){
//                 LOG.info("----------------ditundatangan-------------------"+ditundatangan);
//                 ditundatangan= tandatanganDokumen.getDiTandatangan();
//             }
        }


        if (senaraiRujukanLuar != null) {
            recordCount = String.valueOf(senaraiRujukanLuar.size());
        }
    }

    public static Date addBusinessDays(int numberOfDays) {

        Date baseDate = new Date();

        Calendar baseDateCal = Calendar.getInstance();
        baseDateCal.setTime(baseDate);

        for (int i = 0; i < numberOfDays; i++) {

            baseDateCal.add(Calendar.DATE, 1);
            if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                baseDateCal.add(Calendar.DATE, 1);
            }
            if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                baseDateCal.add(Calendar.DATE, 1);
            }
        }
        return baseDateCal.getTime();
    }

    /**
     * Get the date java.util.Date object for days after current date
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days); // +days
        return cal.getTime();
    }

    // Click on Hapus button in HomePage
    public Resolution deleteRujukan() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        String idDokTt;
        idDokTt = getContext().getRequest().getParameter("idDokTt");
        try {
            if (idRujukan != null) {
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumenRujukan(idRujukan);
                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                if (pdok != null) {
                    pelupusanService.deleteDokumen(pdok);
                }
                if (prs != null) {
                    pelupusanService.deleteSalinan(prs);
                }
                enforcementService.deleteMesy(permohonanRujukanLuar);
                addSimpleMessage("nota dihapuskan berjaya");
            }
            if (idDokTt != null) {
                tandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.valueOf(idDokTt));
                System.out.println("-----deleteing tandatanganDokumen----" + tandatanganDokumen.getIdDokTt());
                pelupusanService.deletePermohonanDokTT(tandatanganDokumen);
                addSimpleMessage("dihapuskan berjaya");
            }


        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/melaka/pendudukansementara/pilih_jabatan_teknikal_2.jsp").addParameter("tab", "true");
    }

    public Resolution deleteDok() {

        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        LOG.info(idRujukan);
        if (idDokumen != null && idRujukan != null) {
            PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumen(idDokumen, idRujukan);
            LOG.info(pdok);
            if (pdok != null) {
                pelupusanService.deleteDokumen(pdok);
            }

        }
        rehydrate();
        return new RedirectResolution(JabatanTeknikal2ActionBean.class, "addDokumen");
    }

    // Click on Cari button in Home Page to display SearchPage
    public Resolution kodAgensiPopup() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
        return new JSP("pengambilan/melaka/pendudukansementara/search_Page.jsp").addParameter("popup", "true");
    }

    public Resolution kodAgensiPopup2() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("pengambilan/melaka/pendudukansementara/search_Page.jsp").addParameter("popup", "true");
    }

    public Resolution addDokumen() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        if (idRujukan != null) {
            senaraiRujukanDok = pelupusanService.findListDokumenRujukan(idRujukan);
            senaraiKandungan = new ArrayList<KandunganFolder>();
            folderDokumen = permohonan.getFolderDokumen();
            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                if (kf == null || kf.getDokumen() == null) {
                    continue;
                }
                senaraiKandungan.add(kf);
            }
        }
        return new JSP("pengambilan/melaka/pendudukansementara/tambah_dokumen.jsp").addParameter("popup", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariKodAgensi() {
        listKodAgensi = new ArrayList<KodAgensi>();

//code changed by rohan
        if (kod != null) {
            listKodAgensi = pelupusanService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/melaka/pendudukansementara/kodAgensi_Page.jsp").addParameter("popup", "true");
                //addSimpleError("Kod Agensi Tidak Sah");
            }
        } else {

            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/melaka/pendudukansementara/kodAgensi_Page.jsp").addParameter("popup", "true");
                // addSimpleError("Kod Agensi Nama Tidak Sah");
            }
        }

        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
        return new JSP("pengambilan/melaka/pendudukansementara/search_Page.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodAgensi2() {
        listKodAgensi = new ArrayList<KodAgensi>();

        if (kod != null) {
            listKodAgensi = regService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/melaka/pendudukansementara/kodAgensi_Page.jsp").addParameter("popup", "true");
                //addSimpleError("Kod Agensi Tidak Sah");
            }
        } else {
            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("pengambilan/melaka/pendudukansementara/kodAgensi_Page.jsp").addParameter("popup", "true");
                // addSimpleError("Kod Agensi Nama Tidak Sah");
            }
        }

        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("pengambilan/melaka/pendudukansementara/search_Page.jsp").addParameter("popup", "true");
    }

    // Save KodAgensi details
    public Resolution simpanKodAgensi() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            kodAgensi.setInfoAudit(info);
//            kodAgensi.setKodKementerian(1); //TODO: list kod_kementerian
            kodAgensi.setKategoriAgensi(kodKategoriAgensiDAO.findById(kodKatgAgensi));
            kodAgensi.setAktif('Y');
            LOG.info("---------kodAgensi--------" + kodAgensi);
            regService.simpanKodAgensi(kodAgensi);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan");

        return new JSP("pengambilan/melaka/pendudukansementara/search_Page.jsp").addParameter("popup", "true");

    }

    // Save PermohonanRujukanLuar details in Home Page
    public Resolution simpan() throws ParseException {
        String kod1;
        String namaJabatan1;
        String jangkamasa1;
        String jangTerima1;
        String tarikhHantar1;
        String salinanKepada1;
        String salinanKod1;
        String mandatori1;
        String catatan;
        String ditundatangan;


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        InfoAudit ia = new InfoAudit();
//        info.setDimasukOleh(peng);
//        info.setTarikhMasuk(new java.util.Date());
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
//        tandatanganDokumenTemp = new ArrayList<PermohonanTandatanganDokumen>();
//        tandatanganDokumenTemp = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan);
        int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        boolean flag = true;

        for (int i = 0; i < rowCount; i++) {
            if (senaraiRujukanLuar.size() != 0 && i < senaraiRujukanLuar.size()) {
                Long id = senaraiRujukanLuar.get(i).getIdRujukan();
                LOG.info("------------id ---------" + id);
                if (id != null) {
                    permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(id);
                    LOG.info("------------permohonanRujukanLuar ---------" + permohonanRujukanLuar);
                    info = permohonanRujukanLuar.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                }
            } else {
                LOG.info("-------permohonanRujukanLuar-----New ---------");
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());

            }

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(info);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            permohonanRujukanLuar.setTarikhDisampai(new java.util.Date());

            kod1 = getContext().getRequest().getParameter("kod" + i);
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            catatan = getContext().getRequest().getParameter("catatan" + i);
            ditundatangan = getContext().getRequest().getParameter("ditundatangan");
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            tarikhHantar1 = getContext().getRequest().getParameter("tarikhHantar" + i);
            jangkamasa1 = getContext().getRequest().getParameter("jangkamasa" + i);
            jangTerima1 = getContext().getRequest().getParameter("jangTerima" + i);
            salinanKepada1 = getContext().getRequest().getParameter("salinanKepada" + i);
            salinanKod1 = getContext().getRequest().getParameter("salinanKod" + i);
            mandatori1 = getContext().getRequest().getParameter("mandatori" + i);
            LOG.info("------------------Mandatori" + mandatori1);

//            LOG.info("-------ditundatangan----simpan ---------" + ditundatangan);
//
//            ia = new InfoAudit();
//            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, ditundatangan);
//            LOG.info("-------tandatanganDokumen----- ---------" + tandatanganDokumen);
//            if (tandatanganDokumen != null) {
//                ia = tandatanganDokumen.getInfoAudit();
////                ia.setDiKemaskiniOleh(peng);
////                ia.setTarikhKemaskini(new java.util.Date());
//            } else {
//                LOG.info("-------tandatanganDokumen-----New ---------"+tandatanganDokumen);
//                tandatanganDokumen = new PermohonanTandatanganDokumen();
//                tandatanganDokumen.setCawangan(permohonan.getCawangan());
//                tandatanganDokumen.setPermohonan(permohonan);
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//
//            }
//            tandatanganDokumen.setInfoAudit(ia);
//            LOG.info("---------kodAgensi----out--------------------" + kod1);
//            KodAgensi kodAgensi = kodAgensiDAO.findById(kod1);
//            LOG.info("---------kodAgensi---------in---------------" + kodAgensi);
//            String kod22 = "SUA";
//            String kod23 = "SUT";
//
////            if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("ADN")) {
////                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
////            }
////            else if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
////                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
////            }
////            if(tandatanganDokumen.getDiTandatangan().equals("225")){
//                if(ditundatangan.equals("225")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
//            }else if(ditundatangan.equals("223")){
////                if(tandatanganDokumen.getDiTandatangan().equals("223")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
//            }
//
////            String ditundatangan1 = getContext().getRequest().getParameter("ditundatangan");
//            if (StringUtils.isNotBlank(ditundatangan)) {
//                tandatanganDokumen.setDiTandatangan(ditundatangan);
//            }
//            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen);

            if ((namaJabatan1 == null) || (jangkamasa1 == null) || (jangTerima1 == null) || (tarikhHantar1 == null)
                    || (namaJabatan1.trim().equals("")) || (jangkamasa1.trim().equals(""))
                    || (jangTerima1.trim().equals("")) || (tarikhHantar1.trim().equals(""))) {
                LOG.info("-------Test Fail ---------");
                addSimpleError("Semua medan wajib");
                flag = false;
                continue;
            }
            LOG.info("---------------end-----");
            KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
            KodAgensi kod = kodAgensiDAO.findById(kod1);
            permohonanRujukanLuar.setAgensi(kod);
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setNamaAgensi(namaJabatan1);
            permohonanRujukanLuar.setCatatan(catatan);
            permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhHantar1));
            permohonanRujukanLuar.setTarikhRujukan(new Date());
            LOG.info("---------Mandatori----------" + mandatori1);
            if (mandatori1 != null) {
                permohonanRujukanLuar.setUlasanMandatori("Y");
            } else {
                permohonanRujukanLuar.setUlasanMandatori("T");
            }


            try {
                StringTokenizer tokenizer = new StringTokenizer(jangkamasa1);
                permohonanRujukanLuar.setTempohHari(Integer.parseInt((String) tokenizer.nextElement()));
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError("Semua medan wajib");
            }
            permohonanRujukanLuar.setTarikhJangkaTerima(sdf.parse(jangTerima1));
            LOG.info("------Save---Mandatori----------" + permohonanRujukanLuar.getUlasanMandatori());
            pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);
            if (StringUtils.isNotBlank(salinanKod1)) {
                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                if (prs != null) {
                    prs.setCawangan(permohonan.getCawangan());
                    prs.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs.setKodAgensi(ks);
                    prs.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs);

                } else {
                    PermohonanRujukanLuarSalinan prs2 = new PermohonanRujukanLuarSalinan();
                    prs2.setCawangan(permohonan.getCawangan());
                    prs2.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs2.setKodAgensi(ks);
                    prs2.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs2);
                }
            }
        }
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("-------ditundatangan----simpan ---------" + ditundatangan);
        ia = new InfoAudit();

        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUA");
        PermohonanTandatanganDokumen tandatanganDokumen2 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUT");
        if (tandatanganDokumen1 != null) {
            ia = tandatanganDokumen1.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("-------tandatanganDokumen1-------------" + tandatanganDokumen1);
            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
            tandatanganDokumen1.setPermohonan(permohonan);
            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUA"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen1.setInfoAudit(ia);
        LOG.info("-------ditundatangan-------------" + ditundatangan);
        tandatanganDokumen1.setDiTandatangan(ditundatangan);
        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

        if (tandatanganDokumen2 != null) {
            ia = tandatanganDokumen2.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("-------tandatanganDokumen2----- ---------" + tandatanganDokumen2);
            tandatanganDokumen2 = new PermohonanTandatanganDokumen();
            tandatanganDokumen2.setCawangan(permohonan.getCawangan());
            tandatanganDokumen2.setPermohonan(permohonan);
            tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("SUT"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen2.setInfoAudit(ia);
        tandatanganDokumen2.setDiTandatangan(ditundatangan);
        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen2);


//            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, ditundatangan);
//            LOG.info("-------tandatanganDokumen----- ---------" + tandatanganDokumen);
//            if (tandatanganDokumen != null) {
//                ia = tandatanganDokumen.getInfoAudit();
//                ia.setDiKemaskiniOleh(peng);
//                ia.setTarikhKemaskini(new java.util.Date());
//            } else {
//                LOG.info("-------tandatanganDokumen-----New ---------"+tandatanganDokumen);
//                tandatanganDokumen = new PermohonanTandatanganDokumen();
//                tandatanganDokumen.setCawangan(permohonan.getCawangan());
//                tandatanganDokumen.setPermohonan(permohonan);
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//
//            }
//            tandatanganDokumen.setInfoAudit(ia);
//            String kod22 = "SUA";
//            String kod23 = "SUT";
//
//                if(ditundatangan.equals("225")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
//            }else if(ditundatangan.equals("223")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
//            }
//            if (StringUtils.isNotBlank(ditundatangan)) {
//                tandatanganDokumen.setDiTandatangan(ditundatangan);
//            }
//            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen);
        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new RedirectResolution(JabatanTeknikal2ActionBean.class, "showForm");
    }

    // Save PermohonanRujukanLuar details in Home Page
    public Resolution simpanNS() throws ParseException {
        String kod1;
        String namaJabatan1;
        String jangkamasa1;
        String jangTerima1;
        String tarikhHantar1;
        String salinanKepada1;
        String salinanKod1;
        String mandatori1;
        String catatan;
        String ditundatangan;

        LOG.info("---------------NS----------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        InfoAudit ia = new InfoAudit();
//        info.setDimasukOleh(peng);
//        info.setTarikhMasuk(new java.util.Date());
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi1(idPermohonan);
//        tandatanganDokumenTemp = new ArrayList<PermohonanTandatanganDokumen>();
//        tandatanganDokumenTemp = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan);
        int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        boolean flag = true;

        for (int i = 0; i < rowCount; i++) {
            if (senaraiRujukanLuar.size() != 0 && i < senaraiRujukanLuar.size()) {
                Long id = senaraiRujukanLuar.get(i).getIdRujukan();
                LOG.info("------------id ---------" + id);
                if (id != null) {
                    permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(id);
                    LOG.info("------------permohonanRujukanLuar ---------" + permohonanRujukanLuar);
                    info = permohonanRujukanLuar.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                }
            } else {
                LOG.info("-------permohonanRujukanLuar-----New ---------");
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());

            }

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(info);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            permohonanRujukanLuar.setTarikhDisampai(new java.util.Date());

            kod1 = getContext().getRequest().getParameter("kod" + i);
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            catatan = getContext().getRequest().getParameter("catatan" + i);
            ditundatangan = getContext().getRequest().getParameter("ditundatangan");
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            tarikhHantar1 = getContext().getRequest().getParameter("tarikhHantar" + i);
            jangkamasa1 = getContext().getRequest().getParameter("jangkamasa" + i);
            jangTerima1 = getContext().getRequest().getParameter("jangTerima" + i);
            salinanKepada1 = getContext().getRequest().getParameter("salinanKepada" + i);
            salinanKod1 = getContext().getRequest().getParameter("salinanKod" + i);
            mandatori1 = getContext().getRequest().getParameter("mandatori" + i);
            LOG.info("------------------Mandatori" + mandatori1);


            ia = new InfoAudit();
            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, ditundatangan);
            LOG.info("-------tandatanganDokumen----- ---------" + tandatanganDokumen);
            if (tandatanganDokumen != null) {
                ia = tandatanganDokumen.getInfoAudit();
//                ia.setDiKemaskiniOleh(peng);
//                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                LOG.info("-------tandatanganDokumen-----New ---------" + tandatanganDokumen);
                tandatanganDokumen = new PermohonanTandatanganDokumen();
                tandatanganDokumen.setCawangan(permohonan.getCawangan());
                tandatanganDokumen.setPermohonan(permohonan);
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());

            }
            tandatanganDokumen.setInfoAudit(ia);
            LOG.info("---------kodAgensi----out--------------------" + kod1);
            KodAgensi kodAgensi = kodAgensiDAO.findById(kod1);
            LOG.info("---------kodAgensi---------in---------------" + kodAgensi);
            String kod22 = "SUA";
            String kod23 = "SUT";

            if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("ADN")) {
                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
            } else if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
            }
            String ditundatangan1 = getContext().getRequest().getParameter("ditundatangan");
            if (StringUtils.isNotBlank(ditundatangan1)) {
                tandatanganDokumen.setDiTandatangan(ditundatangan1);
            }
            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen);

            if ((namaJabatan1 == null) || (jangkamasa1 == null) || (jangTerima1 == null) || (tarikhHantar1 == null)
                    || (namaJabatan1.trim().equals("")) || (jangkamasa1.trim().equals(""))
                    || (jangTerima1.trim().equals("")) || (tarikhHantar1.trim().equals(""))) {
                LOG.info("-------Test Fail ---------");
                addSimpleError("Semua medan wajib");
                flag = false;
                continue;
            }
            LOG.info("---------------end-----");
            KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
            permohonanRujukanLuar.setAgensi(kodAgensi);
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setNamaAgensi(namaJabatan1);
            permohonanRujukanLuar.setCatatan(catatan);
            permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhHantar1));
            permohonanRujukanLuar.setTarikhRujukan(new Date());
            LOG.info("---------Mandatori----------" + mandatori1);
            if (mandatori1 != null) {
                permohonanRujukanLuar.setUlasanMandatori("Y");
            } else {
                permohonanRujukanLuar.setUlasanMandatori("T");
            }


            try {
                StringTokenizer tokenizer = new StringTokenizer(jangkamasa1);
                permohonanRujukanLuar.setTempohHari(Integer.parseInt((String) tokenizer.nextElement()));
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError("Semua medan wajib");
            }
            permohonanRujukanLuar.setTarikhJangkaTerima(sdf.parse(jangTerima1));
            LOG.info("------Save---Mandatori----------" + permohonanRujukanLuar.getUlasanMandatori());
            pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);

            InfoAudit infoaudit = new InfoAudit();
            SuratRujukanLuar suratRujukanLuar;
            String id = String.valueOf(permohonanRujukanLuar.getIdRujukan());
            if (permohonanRujukanLuar != null) {
                suratRujukanLuar = pelupusanService.getSenaraiSuratRujukanLuarByIDRujLuar(id);
                LOG.info("-----------------suratRujukanLuar--------------------------" + suratRujukanLuar);
                if (suratRujukanLuar == null) {
                    suratRujukanLuar = new SuratRujukanLuar();
                    infoaudit.setDimasukOleh(peng);
                    infoaudit.setTarikhMasuk(new java.util.Date());
                } else {
                    infoaudit = suratRujukanLuar.getInfoAudit();
                    infoaudit.setDiKemaskiniOleh(peng);
                    infoaudit.setTarikhKemaskini(new java.util.Date());
                }
                suratRujukanLuar.setCawangan(permohonan.getCawangan());
                suratRujukanLuar.setPermohonanRujukanLuar(permohonanRujukanLuar);
                suratRujukanLuar.setKodDokumen(kodDokumenDAO.findById("PER"));
                suratRujukanLuar.setInfoAudit(infoaudit);
                pelupusanService.simpanSuratRujukanLuar(suratRujukanLuar);
            }

            if (StringUtils.isNotBlank(salinanKod1)) {
                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                if (prs != null) {
                    prs.setCawangan(permohonan.getCawangan());
                    prs.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs.setKodAgensi(ks);
                    prs.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs);

                } else {
                    PermohonanRujukanLuarSalinan prs2 = new PermohonanRujukanLuarSalinan();
                    prs2.setCawangan(permohonan.getCawangan());
                    prs2.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs2.setKodAgensi(ks);
                    prs2.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs2);
                }
            }
        }
        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new JSP("pengambilan/melaka/pendudukansementara/pilih_jabatan_teknikal_NS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanDokumen() {

        String[] param = getContext().getRequest().getParameterValues("idDokumen");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        for (String idDokumen : param) {

            LOG.info(idDokumen);
            InfoAudit ia = new InfoAudit();
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonanRujukanLuar = pelupusanService.findByIdRujukan(Long.valueOf(idRujukan));
            PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumen(idDokumen, idRujukan);
            if (pdok == null) {
                PermohonanRujukanLuarDokumen pe = new PermohonanRujukanLuarDokumen();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setCawangan(peng.getKodCawangan());
                Dokumen kd = new Dokumen();
                kd.setIdDokumen(Long.valueOf(idDokumen));
                pe.setDokumen(kd);
                LOG.info(kd.getIdDokumen());
                pe.setHaluan("H");
                pe.setPermohonanRujukanLuar(permohonanRujukanLuar);
                pelupusanService.simpanDokumen(pe);


            } else {
                addSimpleError("Dokumen  ini telah diHantar");
            }



        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new RedirectResolution(JabatanTeknikal2ActionBean.class, "addDokumen");
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getRadio_() {
        return radio_;
    }

    public void setRadio_(String radio_) {
        this.radio_ = radio_;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(String rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public String getPenyelerasan() {
        return Penyelerasan;
    }

    public void setPenyelerasan(String Penyelerasan) {
        this.Penyelerasan = Penyelerasan;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public String getKategoriAgensi() {
        return kategoriAgensi;
    }

    public void setKategoriAgensi(String kategoriAgensi) {
        this.kategoriAgensi = kategoriAgensi;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohoanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getkNegeri() {
        return kNegeri;
    }

    public void setkNegeri(String kNegeri) {
        this.kNegeri = kNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiRujukanDok() {
        return senaraiRujukanDok;
    }

    public void setSenaraiRujukanDok(List<PermohonanRujukanLuarDokumen> senaraiRujukanDok) {
        this.senaraiRujukanDok = senaraiRujukanDok;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String[] getMandatori() {
        return mandatori;
    }

    public void setMandatori(String[] mandatori) {
        this.mandatori = mandatori;
    }

    public List<KodKementerian> getSenaraiKodKementerian() {
        return senaraiKodKementerian;
    }

    public void setSenaraiKodKementerian(List<KodKementerian> senaraiKodKementerian) {
        this.senaraiKodKementerian = senaraiKodKementerian;
    }

    public String getKodKatgAgensi() {
        return kodKatgAgensi;
    }

    public void setKodKatgAgensi(String kodKatgAgensi) {
        this.kodKatgAgensi = kodKatgAgensi;
    }

    public List<KodAgensi> getKodAgensis() {
        return kodAgensis;
    }

    public void setKodAgensis(List<KodAgensi> kodAgensis) {
        this.kodAgensis = kodAgensis;
    }

    public PermohonanTandatanganDokumen getTandatanganDokumen() {
        return tandatanganDokumen;
    }

    public void setTandatanganDokumen(PermohonanTandatanganDokumen tandatanganDokumen) {
        this.tandatanganDokumen = tandatanganDokumen;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public String getDitundatangan1() {
        return ditundatangan1;
    }

    public void setDitundatangan1(String ditundatangan1) {
        this.ditundatangan1 = ditundatangan1;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<PermohonanLaporanPelan> getPermohonanLaporanPelanList() {
        return permohonanLaporanPelanList;
    }

    public void setPermohonanLaporanPelanList(List<PermohonanLaporanPelan> permohonanLaporanPelanList) {
        this.permohonanLaporanPelanList = permohonanLaporanPelanList;
    }
}

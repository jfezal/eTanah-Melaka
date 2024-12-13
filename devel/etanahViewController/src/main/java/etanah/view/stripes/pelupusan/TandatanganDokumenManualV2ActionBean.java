/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.service.BPelService;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.model.PenggunaPeranan;
import etanah.service.PelupusanService;
import etanah.service.ConsentPtdService;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.Dokumen;
import etanah.service.common.DokumenService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisTandatanganManual;
import java.text.DateFormat;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Shazwan
 */
@UrlBinding("/pelupusan/tandatangan_dokumenV2")
public class TandatanganDokumenManualV2ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    ConsentPtdService consentService;
    @Inject
    DokumenService dokService;
    private List<Pengguna> penggunaList;
    private List<PenggunaPeranan> penggunaPerananList;
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private Dokumen dokumen_;
    private String ditundatangan;
    private String ditundatangan1;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private String stageId;
    private String negeri;
    private boolean ptd;
    private boolean ptdKanan;
    private boolean ptg;
    private boolean ptgpuu;
    private boolean ptundang;
    private boolean flag_perakuan;
    private String tarikh;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(TandatanganDokumenManualV2ActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        ptd = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            BPelService service = new BPelService();
            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pguna);
                stageId = t.getSystemAttributes().getStage();
//                stageId = "kemasukan";
            }
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                if (negeri.equals("05")) {
                    if (stageId.equals("01DaftarPermohonan") || stageId.equals("05TrmKptsndanSedSrtLulus") || stageId.equals("08TrmKptsndanSedSrtTlk")) {
                        String[] kumpBPEL = {"pptd", "ptd", "tptd"};
                        penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    } else {
                        String[] kumpBPEL = {"pptd", "ptd", "pttptdbangun"};
                        penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                    }
                } else {
                    String[] kumpBPEL = {"pptd", "ptd", "kptlupus", "pttptdbangun"};
                    penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
                }
            } //            else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
            //                String[] kumpBPEL = {"pptd", "ptd"};
            //                penggunaPerananList = getSenaraiPenggunaPerananPLPS(permohonan.getCawangan(), kumpBPEL, pguna.getKodJabatan().getKod());
            //            }
            else if (permohonan.getKodUrusan().getKod().equals("831B")) {
                String[] kumpBPEL = {"pptd", "ptd", "KPTPengambilan"};
                penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
//                penggunaPerananList = getSenaraiPenggunaPerananPLPS(permohonan.getCawangan(), kumpBPEL, pguna.getKodJabatan().getKod());
            } else {
                String[] kumpBPEL = {"pptd", "ptd", "kptlupus", "pttptdbangun"};
                penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            }

            if (stageId.equals("perakuan_ptd")) {
                flag_perakuan = true;
                dokumen_ = dokService.findDokumenRENCbyDnilai1(idPermohonan);
                if (dokumen_ != null) {
                    if (dokumen_.getInfoAudit().getTarikhKemaskini() != null) {
                        tarikh = sdf.format(dokumen_.getInfoAudit().getTarikhKemaskini());
                    }
                }
            }
        }

        LOG.info("--------------PTD Tandatangan1--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTarikh() {
        LOG.info("simpan tarikh tandatangan start");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan caw = pguna.getKodCawangan();

        dokumen_ = dokService.findDokumenRENCbyDnilai1(idPermohonan);

        if (dokumen_ != null) {
            try {
                Date trktt = (Date) formatter.parse(tarikh);

                infoAudit = dokumen_.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(trktt);
                dokumen_.setInfoAudit(infoAudit);

                dokService.saveOrUpdate(dokumen_);
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            } catch (ParseException e) {
                LOG.info("Exception:" + e);
            }
        } else {
            addSimpleError("Sila simpan tandatangan dahulu");

        }

        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormKPTLupus() {
        ptd = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                String[] kumpBPEL = {"pptd", "ptd", "kptlupus", "pttptdbangun"};
                penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            } else {
                String[] kumpBPEL = {"pptd", "ptd", "kptlupus", "pttptdbangun"};
                penggunaPerananList = getSenaraiPenggunaPerananPLPS(permohonan.getCawangan(), kumpBPEL, pguna.getKodJabatan().getKod());
            }
        }
        LOG.info("--------------PTD Tandatangan2--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPPTKanan() {
        ptdKanan = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            String[] kumpBPEL = {"pptkanan"};
            penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
        }
        LOG.info("--------------PTD Tandatangan3--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormForPTG() {
        ptg = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        negeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {
            KodCawangan kodCaw = kodCawanganDAO.findById("00");
//            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
//                String[] kumpBPEL = {"ptg", "puu", "tptg"};
//                penggunaPerananList = getSenaraiPenggunaPerananPLPS(kodCaw, kumpBPEL, pguna.getKodJabatan().getKod());
//            }
//            else {
            String[] kumpBPEL = {"ptg", "puu", "tptg"};
            penggunaPerananList = getSenaraiPenggunaPeranan(kodCaw, kumpBPEL);
//            }
        }
        LOG.info("--------------PTG Tandatangan--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormForPUU() {
        ptg = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        negeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {
            KodCawangan kodCaw = kodCawanganDAO.findById("00");
//            String[] kumpBPEL = {"puu"};
            String[] kumpBPEL = {"ptundang"};
            penggunaPerananList = getSenaraiPenggunaPeranan(kodCaw, kumpBPEL);
        }
        LOG.info("--------------PTG Tandatangan--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("--------------" + idPermohonan);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
//        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
//        hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1); Comment sementara
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        stageId = "kemasukan";
        if (stageId == null) {
            addSimpleError("Tidak boleh tandatangan kerana stage tidak dikenali atau ralat");
        } else {
            negeri = conf.getProperty("kodNegeri");
            int numUrusan = this.getNumUrusan(permohonan);
            this.getTandatangan(numUrusan, stageId, negeri, permohonan.getCawangan().getKod());
        }
    }

    public int getNumUrusan(Permohonan permohonan) {
        int numUrusan = permohonan.getKodUrusan().getKod().equals("PBMT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 2
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 3
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 4
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 5
                : permohonan.getKodUrusan().getKod().equals("RAYA") ? 6
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 7
                : permohonan.getKodUrusan().getKod().equals("RAYL") ? 8
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 9
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 10
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 11
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 12
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 13
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 14
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 15
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 16
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 18
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 19
                : permohonan.getKodUrusan().getKod().equals("PHLA") ? 20
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 21
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 22
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 23
                : permohonan.getKodUrusan().getKod().equals("PBHL") ? 24
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 25
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 26
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 27
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 28
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 29
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 30
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 31
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 32
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 33
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 34
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 35
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 36
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 37
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 38
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 39
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 40
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 41
                : permohonan.getKodUrusan().getKod().equals("JMRE") ? 42
                : permohonan.getKodUrusan().getKod().equals("BMRE") ? 43
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 44
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 45
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 46
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 35
                : permohonan.getKodUrusan().getKod().equals("LSTP") ? 47
                : permohonan.getKodUrusan().getKod().equals("831B") ? 48
                : permohonan.getKodUrusan().getKod().equals("PLPT") ? 49
                : 0;
        return numUrusan;
    }

    public void getTandatangan(int numUrusan, String stageID, String negeri, String kodCaw) {

        Pengguna pguna = new Pengguna();
        String kodDok = new String();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;

        kodDok = this.getKodDokumen(numUrusan, stageID, numnegeri, kodCaw);
        if (!StringUtils.isEmpty(kodDok)) {
            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDok);
            if (tandatanganDokumen != null) {
                ditundatangan = tandatanganDokumen.getDiTandatangan();
            }
        }
        if (negeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
                if (stageId.equals("27SmkDrfMMK")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "MMK");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                }
            }
        }
    }

    public void getTandatanganSimpan(int numUrusan, String stageID, String negeri, String kodCaw) {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pengguna pguna = new Pengguna();
        String kodDok = new String();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;

        kodDok = this.getKodDokumen(numUrusan, stageID, numnegeri, kodCaw);
        if (permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC") || permohonan.getKodUrusan().getKod().equals("PTBTS")) {
            if (stageId.equals("27SmkDrfMMK")) {
                PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "MMK");
                InfoAudit ia = new InfoAudit();
                if (tandatanganDokumen1 != null) {
                    ia = tandatanganDokumen1.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                    tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                    tandatanganDokumen1.setPermohonan(permohonan);
                    tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("MMK"));
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                tandatanganDokumen1.setInfoAudit(ia);
                tandatanganDokumen1.setDiTandatangan(ditundatangan);
                pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
            } else {
                PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDok);
                InfoAudit ia = new InfoAudit();
                if (tandatanganDokumen1 != null) {
                    ia = tandatanganDokumen1.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                    tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                    tandatanganDokumen1.setPermohonan(permohonan);
                    tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById(kodDok));
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                tandatanganDokumen1.setInfoAudit(ia);
                tandatanganDokumen1.setDiTandatangan(ditundatangan);
                pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
            }
        } else if (!StringUtils.isEmpty(kodDok)) {
            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDok);
            InfoAudit ia = new InfoAudit();
            if (tandatanganDokumen1 != null) {
                ia = tandatanganDokumen1.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                tandatanganDokumen1.setPermohonan(permohonan);
                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById(kodDok));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen1.setInfoAudit(ia);
            tandatanganDokumen1.setDiTandatangan(ditundatangan);
            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
        }
    }

    public String getKodDokumen(int numUrusan, String stageID, int numnegeri, String kodCaw) {
        String kodDok = new String();
        String kodKpsn = new String();
        DisTandatanganManual dtm = new DisTandatanganManual();
        switch (numUrusan) {
            case 1:
                //PBMT
                kodDok = dtm.getKodDokByCasePBMT(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 2:
                //PLPS
                kodDok = dtm.getKodDokByCasePLPS(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 3:
                //PPBB
                String kodKpsnPPBB = new String();
                if (stageId.equals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = new String();
                    idAliranMhonFasa = "perakuan_rencana_jkbb"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasa == null) {
                        idAliranMhonFasa = "perakuan_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    }
                    kodKpsnPPBB = mohonFasa.getKeputusan().getKod().equals("L") ? "SL" : mohonFasa.getKeputusan().getKod().equals("T") ? "STP" : new String();
                }
                kodDok = dtm.getKodDokByCasePPBB(stageID, numnegeri, kodCaw, permohonan, kodKpsnPPBB);
                break;
            case 4:
                //PBPTD
                kodDok = dtm.getKodDokByCasePBPTD(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 5:
                //PBPTG
                kodKpsn = new String();
                if (stageId.equals("sedia_surat_kelulusan")) {
                    String idAliranMhonFasa = "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    String keputusan = mohonFasa.getKeputusan().getKod();
                    kodKpsn = keputusan.equals("L") ? "SKM" : keputusan.equals("T") ? "STP" : new String();
                } else if (stageId.equals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    String keputusan = mohonFasa.getKeputusan().getKod();
                    kodKpsn = keputusan.equals("L") ? "SL" : keputusan.equals("T") ? "STP" : new String();
                }
                kodDok = dtm.getKodDokByCasePBPTG(stageID, numnegeri, kodCaw, permohonan, kodKpsn);
                break;
            case 6:
                //RAYA
                kodDok = dtm.getKodDokByCaseRAYA(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 7:
                //RAYK
                kodDok = dtm.getKodDokByCaseRAYK(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 8:
                //RAYL
                kodDok = dtm.getKodDokByCaseRAYL(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 9:
                //PRMP
                kodDok = dtm.getKodDokByCasePRMP(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 10:
                //LMCRG
                kodDok = dtm.getKodDokByCaseLMCRG(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 11:
                //PJLB
                kodDok = dtm.getKodDokByCasePJLB(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 12:
                //PPRU
                kodDok = dtm.getKodDokByCasePPRU(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 13:
                //PPTR
                kodDok = dtm.getKodDokByCasePPTR(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 14:
                //RLPTG
                kodDok = dtm.getKodDokByCaseRLPTG(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 15:
                //RYKN
                kodDok = dtm.getKodDokByCaseRYKN(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 16:
                //PTMTA
                kodDok = dtm.getKodDokByCasePTMTA(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 17:
                //PRIZ
                //LPSP
                kodKpsn = new String();
                if (stageId.equals("sedia_surat_kelulusan")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    String keputusan = mohonFasa.getKeputusan().getKod();
                    kodKpsn = keputusan.equals("L") ? "SL" : keputusan.equals("T") ? "STP" : new String();
                }
                kodDok = dtm.getKodDokByCasePRIZ(stageID, numnegeri, kodCaw, permohonan, kodKpsn);
                break;
            case 18:
                //LPSP
                kodKpsn = new String();
                if (stageId.equals("15SediaSurat")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    String keputusan = mohonFasa.getKeputusan().getKod();
                    kodKpsn = keputusan.equals("L") ? "SL" : keputusan.equals("T") ? "STP" : new String();
                }
                kodDok = dtm.getKodDokByCaseLPSP(stageID, numnegeri, kodCaw, permohonan, kodKpsn);
                break;
            case 19:
                //PBGSA 
                kodDok = dtm.getKodDokByCasePBGSA(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 20:
                //PHLA 
                kodDok = dtm.getKodDokByCasePHLA(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 21:
                //MMMCL
                HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
                kodDok = dtm.getKodDokByCaseMMMCL(stageId, numnegeri, kodCaw, permohonan, hakmilikPermohonan);
                break;

            case 22:
                //RAYT
                kodKpsn = new String();
                if (stageId.equals("022_Semak")) {
                    String idAliranMhonFasa = "012_KeputusanMMKN"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    String keputusan = mohonFasa.getKeputusan().getKod();
                    kodKpsn = keputusan.equals("L") ? "SL" : keputusan.equals("T") ? new String() : new String();
                }
                kodDok = dtm.getKodDokByCaseRAYT(stageID, numnegeri, kodCaw, permohonan, kodKpsn);
                break;
            case 23:
                //PBRZ
                kodDok = dtm.getKodDokByCasePBRZ(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 24:
                //PBHL
                kodDok = dtm.getKodDokByCasePBHL(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 25:
                //BMBT
                kodDok = dtm.getKodDokByCaseBMBT(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 26:
                //MLCRG
                kodDok = dtm.getKodDokByCaseMLCRG(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 27:
                //MLCRG
                kodDok = dtm.getKodDokByCaseMPJLB(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 28:
                //PWGSA
                kodDok = dtm.getKodDokByCaseMPWGSA(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 29:
                //PJBTR
                kodDok = dtm.getKodDokByCasePJBTR(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 30:
                //PLPTD
                kodDok = dtm.getKodDokByCasePLPTD(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 31:
                //PBMMK
                kodDok = dtm.getKodDokByCasePBMMK(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 32:
                //PTPBP
                kodDok = dtm.getKodDokByCasePTPBP(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 33:
                //RLPS
                kodDok = dtm.getKodDokByCaseRLPS(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 34:
                //PRMMK
                kodDok = dtm.getKodDokByCasePRMMK(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 35:
                //PCRG
                kodDok = dtm.getKodDokByCasePCRG(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 36:
                //MPCRG
                kodDok = dtm.getKodDokByCaseMPCRG(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 37:
                //MMRE
                kodDok = dtm.getKodDokByCaseMMRE(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 38:
                //PTGSA
                kodDok = dtm.getKodDokByCasePTGSA(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 39:
                //PTBTC
                kodDok = dtm.getKodDokByCasePTBTC(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 40:
                //PTBTS
                kodDok = dtm.getKodDokByCasePTBTS(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 41:
                //WMRE
                kodDok = dtm.getKodDokByCaseWMRE(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 42:
                //JMRE
                kodDok = dtm.getKodDokByCaseJMRE(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 43:
                //BMRE
                kodDok = dtm.getKodDokByCaseBMRE(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 44:
                //PJTK
                kodDok = dtm.getKodDokByCasePJTK(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 45:
                //PHLA 
                kodDok = dtm.getKodDokByCasePHLP(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 46:
                //PTPBP 
                kodDok = dtm.getKodDokByCasePTPBP(stageId, numnegeri, kodCaw, permohonan);
                break;
            case 47:
                //LSTP
                kodDok = dtm.getKodDokByCaseLSTP(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 48:
                //831B
                kodDok = dtm.getKodDokByCase831B(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 49:
                kodDok = dtm.getKodDokByCasePLPT(stageID, numnegeri, kodCaw, permohonan);
                break;
        }
        return kodDok;
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, String[] listKod) {
        try {
            String kumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    kumpBPEL = "('";
                    kumpBPEL = kumpBPEL + pu;
                } else {
                    kumpBPEL = kumpBPEL + "','" + pu;
                }

                if (count == listKod.length) {
                    kumpBPEL = kumpBPEL + "')";
                }
                count++;
            }
            String query = "Select u.pengguna from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status ='A' order by u.pengguna.nama";
            LOG.info("QUERY->" + query);
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPerananPLPS(KodCawangan kod, String[] listKod, String kodJabatan) {
        try {
            String kumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    kumpBPEL = "('";
                    kumpBPEL = kumpBPEL + pu;
                } else {
                    kumpBPEL = kumpBPEL + "','" + pu;
                }

                if (count == listKod.length) {
                    kumpBPEL = kumpBPEL + "')";
                }
                count++;
            }
            String query = "Select distinct u from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status.kod = 'A' and u.pengguna.kodJabatan.kod = :kodJabatan";
            LOG.info("QUERY->" + query);
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            q.setString("kodJabatan", kodJabatan);
            return q.list();

        } finally {
        }
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. " + ditundatangan);
        negeri = conf.getProperty("kodNegeri");
        if (ditundatangan != null) {
            int numUrusan = this.getNumUrusan(permohonan);
            this.getTandatanganSimpan(numUrusan, stageId, negeri, permohonan.getCawangan().getKod());
            addSimpleMessage("Rekod tandatangan telah dimasukkan");
        } else {
            addSimpleMessage("Tiada Rekod telah dimasukkan");
        }

        rehydrate();
        if (ptg == true) {
            return new RedirectResolution(TandatanganDokumenManualV2ActionBean.class, "showFormForPTG");
        } else if (ptundang == true) {
            return new RedirectResolution(TandatanganDokumenManualV2ActionBean.class, "showFormForPUU");
        } else if (ptdKanan == true) {
            return new RedirectResolution(TandatanganDokumenManualV2ActionBean.class, "showFormPPTKanan");
        } else if (ptd == true) {
            return new RedirectResolution(TandatanganDokumenManualV2ActionBean.class, "showForm");
        } else {
            return new RedirectResolution(TandatanganDokumenManualV2ActionBean.class, "showForm");
        }
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public boolean isPtd() {
        return ptd;
    }

    public void setPtd(boolean ptd) {
        this.ptd = ptd;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }

    public List<PenggunaPeranan> getPenggunaPerananList() {
        return penggunaPerananList;
    }

    public void setPenggunaPerananList(List<PenggunaPeranan> penggunaPerananList) {
        this.penggunaPerananList = penggunaPerananList;
    }

    public boolean isPtgpuu() {
        return ptgpuu;
    }

    public void setPtgpuu(boolean ptgpuu) {
        this.ptgpuu = ptgpuu;
    }

    public boolean isPtdKanan() {
        return ptdKanan;
    }

    public void setPtdKanan(boolean ptdKanan) {
        this.ptdKanan = ptdKanan;
    }

    public boolean isPtundang() {
        return ptundang;
    }

    public void setPtundang(boolean ptundang) {
        this.ptundang = ptundang;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public boolean isFlag_perakuan() {
        return flag_perakuan;
    }

    public void setFlag_perakuan(boolean flag_perakuan) {
        this.flag_perakuan = flag_perakuan;
    }

    public Dokumen getDokumen_() {
        return dokumen_;
    }

    public void setDokumen_(Dokumen dokumen_) {
        this.dokumen_ = dokumen_;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public DateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DateFormat formatter) {
        this.formatter = formatter;
    }

}

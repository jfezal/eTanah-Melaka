/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.service.BPelService;
import etanah.model.KodCawangan;
import etanah.model.PenggunaPeranan;
import etanah.service.PelupusanService;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.common.PengambilanDepositService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurul.hazirah
 */
@UrlBinding("/pengambilan/tandatangan_dokumenV2")
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
    PengambilanDepositService service;
    private List<Pengguna> penggunaList;
    private List<PenggunaPeranan> penggunaPerananList;
    private List<PermohonanTandatanganDokumen> tandatanganDokumenList;
    List<PermohonanTuntutanKos> senaraiDeposit;
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private String ditundatangan;
    private String ditundatangan1;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private String idPermohonan;
    private Pengguna pguna;
    private String stageId;
    private String negeri;
    private String bulan;
    private boolean ptd;
    private boolean ptdKanan;
    private boolean ptg;
    private boolean ptgpuu;
    private boolean ptundang;
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
            }
//            stageId = "B08BayaranDeposit125";
            String[] kumpBPEL = {"pptd", "ptd", "tptd"};
            KodCawangan kod = pguna.getKodCawangan();
            //String[] kumpBPEL = {"PPTD", "PTD", "TPTD"};
//            penggunaList = getSenaraiPengguna("pptd", "ptd", "tptd", kod.getKod());
            penggunaPerananList = getSenaraiPenggunaPeranan(kod, kumpBPEL);
            LOG.info("--------------penggunaPerananList--------------" + penggunaPerananList.size());
            //penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
//                penggunaPerananList = getSenaraiPenggunaPerananPLPS(permohonan.getCawangan(), kumpBPEL, pguna.getKodJabatan().getKod());

        }
        LOG.info("--------------PTD Tandatangan--------------");
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormKPTLupus() {
        ptd = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                String[] kumpBPEL = {"pptd", "ptd", "kptlupus"};
                penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            } else {
                String[] kumpBPEL = {"pptd", "ptd", "kptlupus"};
                penggunaPerananList = getSenaraiPenggunaPerananPLPS(permohonan.getCawangan(), kumpBPEL, pguna.getKodJabatan().getKod());
            }
        }
        LOG.info("--------------PTD Tandatangan--------------");
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
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
        LOG.info("--------------PTD Tandatangan--------------");
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
    }

    public Resolution showFormForPTGPTD() {
        ptg = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        negeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {

            String[] kumpBPEL = {"ptg", "puu", "tptg"};
            KodCawangan kodCaw = kodCawanganDAO.findById("00");
            penggunaPerananList = getSenaraiPenggunaPeranan(kodCaw, kumpBPEL);
            LOG.info("penggunaPerananList == "+ penggunaPerananList.size());
        }
        LOG.info("--------------PTG Tandatangan--------------");
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
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
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
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
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
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
        senaraiDeposit = service.findByIDMohon(idPermohonan);
        if (!senaraiDeposit.isEmpty()) {
            bulan = senaraiDeposit.get(0).getNoLesen();
        }
        
        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        stageId = "08Semakan";
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
                : permohonan.getKodUrusan().getKod().equals("831A") ? 2
                : permohonan.getKodUrusan().getKod().equals("831B") ? 3
                : permohonan.getKodUrusan().getKod().equals("831C") ? 4
                : permohonan.getKodUrusan().getKod().equals("SEK4") ? 5
                : permohonan.getKodUrusan().getKod().equals("SEK4A") ? 6
                : permohonan.getKodUrusan().getKod().equals("PTSP") ? 7
                : permohonan.getKodUrusan().getKod().equals("BMAHK") ? 8
                : permohonan.getKodUrusan().getKod().equals("PTPT") ? 9
                : permohonan.getKodUrusan().getKod().equals("PTNB") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBA") ? 11
                : 0;
        return numUrusan;
    }

    public void getTandatangan(int numUrusan, String stageID, String negeri, String kodCaw) {

        Pengguna pguna = new Pengguna();
        String kodDok = new String();
        List<String> kodDoc = new ArrayList<String>();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;

        kodDok = this.getKodDokumen(numUrusan, stageID, numnegeri, kodCaw);
        kodDoc = this.getKodDoc(numUrusan, stageID, numnegeri, kodCaw);
        if (!StringUtils.isEmpty(kodDok)) {
            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDok);
            if (tandatanganDokumen != null) {
                ditundatangan = tandatanganDokumen.getDiTandatangan();
            }
        } else if (!kodDoc.isEmpty()) {
            for (int i = 0; i < kodDoc.size(); i++) {
                tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDoc.get(i));
                if (tandatanganDokumen != null) {
                    ditundatangan = tandatanganDokumen.getDiTandatangan();
                }
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
        List<String> kodDoc = new ArrayList<String>();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;

        kodDok = this.getKodDokumen(numUrusan, stageID, numnegeri, kodCaw);
        kodDoc = this.getKodDoc(numUrusan, stageID, numnegeri, kodCaw);
        LOG.info("kodDok : " + kodDok);
        LOG.info("kodDoc : " + kodDoc);
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
        } else if (!kodDoc.isEmpty()) {
            for (int i = 0; i < kodDoc.size(); i++) {

                PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, kodDoc.get(i));
                InfoAudit ia = new InfoAudit();
                if (tandatanganDokumen1 != null) {
                    ia = tandatanganDokumen1.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                    tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                    tandatanganDokumen1.setPermohonan(permohonan);
                    tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById(kodDoc.get(i)));
                    LOG.info("kodDoc.i : " + kodDoc.get(i));
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                }
                tandatanganDokumen1.setInfoAudit(ia);
                tandatanganDokumen1.setDiTandatangan(ditundatangan);
                pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
            }
        }
    }

    public String getKodDokumen(int numUrusan, String stageID, int numnegeri, String kodCaw) {
        String kodDok = new String();
        String kodKpsn = new String();
        AcqTandatanganManual dtm = new AcqTandatanganManual();
        switch (numUrusan) {
//            case 1:
//                //PBMT
//                kodDok = dtm.getKodDokByCasePBMT(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 2:
//                //831A
//                kodDok = dtm.getKodDokByCase831A(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 3:
//                //831B
////                kodDok = dtm.getKodDokByCase831B(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 4:
//                //831C
//                kodDok = dtm.getKodDokByCase831C(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 5:
//                //SEK4
//                kodDok = dtm.getKodDokByCaseSEK4(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 6:
//                //SEK4A
//                kodDok = dtm.getKodDokByCaseSEK4A(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 7:
//                //PTSP
//                kodDok = dtm.getKodDokByCasePTSP(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 8:
//                //BMAHK
//                kodDok = dtm.getKodDokByCaseBMAHK(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 9:
//                //PTPT
//                kodDok = dtm.getKodDokByCasePTPT(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 10:
//                //PTNB
//                kodDok = dtm.getKodDokByCasePTNB(stageID, numnegeri, kodCaw, permohonan);
//                break;
//            case 11:
//                //PBA
//                kodDok = dtm.getKodDokByCasePBA(stageID, numnegeri, kodCaw, permohonan);
//                break;
        }
        return kodDok;
    }

    public List<String> getKodDoc(int numUrusan, String stageID, int numnegeri, String kodCaw) {
        List<String> kodDoc = new ArrayList<String>();
        String kodKpsn = new String();
        AcqTandatanganManual dtm = new AcqTandatanganManual();
        switch (numUrusan) {
            case 1:
                //PBMT
//                kodDok = dtm.getKodDokByCasePBMT(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 2:
                //831A
                kodDoc = dtm.getKodDokByCase831A(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 3:
                //831B
                kodDoc = dtm.getKodDokByCase831B(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 4:
                //831C
                kodDoc = dtm.getKodDokByCase831C(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 5:
                //SEK4
                kodDoc = dtm.getKodDokByCaseSEK4(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 6:
                //SEK4A
                kodDoc = dtm.getKodDokByCaseSEK4A(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 7:
                //PTSP
                kodDoc = dtm.getKodDokByCasePTSP(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 8:
                //BMAHK
                kodDoc = dtm.getKodDokByCaseBMAHK(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 9:
                //PTPT
                kodDoc = dtm.getKodDokByCasePTPT(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 10:
                //PTNB
                kodDoc = dtm.getKodDokByCasePTNB(stageID, numnegeri, kodCaw, permohonan);
                break;
            case 11:
                //PBA
                kodDoc = dtm.getKodDokByCasePBA(stageID, numnegeri, kodCaw, permohonan);
                break;
        }
        return kodDoc;
    }

    public List<Pengguna> getSenaraiPengguna(String kumpBPEL1, String kumpBPEL2, String kumpBPEL3, String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pengguna p where p.idPengguna in (select pp.pengguna.idPengguna from etanah.model.PenggunaPeranan pp "
                + "where pp.peranan.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2,:kumpBPEL3)) AND p.status.kod = 'A' AND p.kodCawangan.kod = :kodCaw");
        //Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.perananUtama.kumpBPEL = :kumpBPEL1 AND ppp.kodCawangan.kod = :kodCaw");
        q.setString("kumpBPEL1", kumpBPEL1);
        q.setString("kumpBPEL2", kumpBPEL2);
        q.setString("kumpBPEL3", kumpBPEL3);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

//    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, String[] listKod) {
//        try {
//            String kumpBPEL = new String();
//            int count = 1;
//            for (String pu : listKod) {
//                if (count == 1) {
//                    kumpBPEL = "('";
//                    kumpBPEL = kumpBPEL + pu;
//                } else {
//                    kumpBPEL = kumpBPEL + "','" + pu;
//                }
//
//                if (count == listKod.length) {
//                    kumpBPEL = kumpBPEL + "')";
//                }
//                count++;
//            }
//
//            String query = "Select distinct b from etanah.model.Pengguna b ,etanah.model.PenggunaPeranan a where a.peranan in " + kumpBPEL + "AND b.perananUtama in"
//                    + kumpBPEL + "AND a.peranan = b.perananUtama AND b.status ='A'";
//            //String query = "Select distinct u from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status.kod = 'A'";
//            LOG.info("QUERY->" + query);
//            Session session = sessionProvider.get();
//            Query q = session.createQuery(query);
//           // q.setParameter("kod", kod);
//            return q.list();
//
//        } finally {
//        }
//    }
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
            String query = "Select distinct u from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod and u.pengguna.status.kod = 'A'";
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

    public Resolution simpanBulan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        senaraiDeposit = service.findByIDMohon(idPermohonan);

        for (int i = 0; i < senaraiDeposit.size(); i++) {

            if (idPermohonan != null) {
                if (senaraiDeposit.isEmpty()) {
//                        mohontuntut = new PermohonanTuntutanKos();
//                        InfoAudit info = peng.getInfoAudit();
//                        info.setDimasukOleh(peng);
//                        info.setTarikhMasuk(new java.util.Date());
//                        mohontuntut.setInfoAudit(info);
//                        mohontuntut.setPermohonan(p);
//                        mohontuntut.setCawangan(cawangan);
//                        KodTransaksi kodTransaksi = kodTransaksiDAO.findById(kod);
//                        mohontuntut.setKodTransaksi(kodTransaksi);
//                        mohontuntut.setItem("Deposit 125%");
//                        mohontuntut.setAmaunSebenar(nilaitanah);
//                        mohontuntut.setAmaunTuntutan(getAmaun());
//                        mohontuntut.setHakmilikPermohonan(hakmilikPermohonan);
//                        plpservice.simpanPermohonanTuntutanKos(mohontuntut);
                } else {
                    InfoAudit info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    senaraiDeposit.get(i).setInfoAudit(info);
                    senaraiDeposit.get(i).setPermohonan(permohonan);
                    senaraiDeposit.get(i).setCawangan(cawangan);
                    senaraiDeposit.get(i).setNoLesen(bulan);
                    pelupusanService.simpanPermohonanTuntutanKos(senaraiDeposit.get(i));
                }
            }
        }
        showForm();
        rehydrate();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan.");
        return new JSP("pengambilan/melaka/tandatangan_dokumen_manualV2.jsp").addParameter("tab", "true");
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

    public List<PermohonanTandatanganDokumen> getTandatanganDokumenList() {
        return tandatanganDokumenList;
    }

    public void setTandatanganDokumenList(List<PermohonanTandatanganDokumen> tandatanganDokumenList) {
        this.tandatanganDokumenList = tandatanganDokumenList;
    }

    public List<PermohonanTuntutanKos> getSenaraiDeposit() {
        return senaraiDeposit;
    }

    public void setSenaraiDeposit(List<PermohonanTuntutanKos> senaraiDeposit) {
        this.senaraiDeposit = senaraiDeposit;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
}
